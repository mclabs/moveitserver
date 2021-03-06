/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openxdata.model.FormData;
import org.openxdata.model.FormDef;
import org.openxdata.model.PageData;
import org.openxdata.model.QuestionData;
import org.openxdata.model.QuestionDef;
import org.openxdata.model.StudyData;
import org.openxdata.model.StudyDataList;
import org.openxdata.modules.moveit.server.service.EventStatusService;
import org.openxdata.modules.workflows.server.context.WFContext;
import org.openxdata.modules.workflows.server.handlers.RequestHandler;
import org.openxdata.modules.workflows.server.service.MapService;
import org.openxdata.modules.workflows.server.service.WorkItemsService;
import org.openxdata.modules.workflows.server.service.impl.LaunchCaseServiceImpl;
import org.openxdata.server.Context;
import org.openxdata.server.admin.model.FormDefVersion;
import org.openxdata.server.admin.model.User;
import org.openxdata.server.serializer.KxmlSerializerUtil;
import org.openxdata.server.serializer.KxmlXformSerializer;
import org.openxdata.server.service.DataExportService;
import org.openxdata.server.service.FormDownloadService;
import org.openxdata.workflow.mobile.model.MWorkItemData;
import org.openxdata.workflow.mobile.model.MWorkItemDataList;

/**
 * 
 * 
 * Handles upload of workItemData and calling the yawl service to submit the
 * the completed workItems.Data is extracted from workItemData and
 * save locally.Then required data for a specific workItem is extracted and sent
 * to the YAWL engine
 * 
 * @author kay
 * 
 * 
 */

@SuppressWarnings("UseOfObsoleteCollectionType")
public class IISWIRUploadHandler implements RequestHandler
{

        private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());
        private FormDownloadService formDownloadService;
        private KxmlXformSerializer serialiser;
        private MapService mapService;
        private WorkItemsService wirService;
        private DataExportService exportService;
        private Map<Integer, String> xformMap;
        private EventStatusService eventStatusService;
		//private boolean continueUpload = false;

        public IISWIRUploadHandler()
        {
                serialiser = new KxmlXformSerializer();
                mapService = WFContext.getSpecStudyService();
                formDownloadService = WFContext.getFormDownloadService();
                wirService = WFContext.getWorkItemsService();
                exportService = (DataExportService) Context.getBean("dataExportService");
                xformMap = formDownloadService.getFormsVersionXmlMap();
                eventStatusService = (EventStatusService) Context.getBean("eventStatusService");
        }

        @Override
        public void handleRequest(User user, InputStream is, OutputStream os) throws IOException
        {
                try {
                        HandlerStreamUtil streamHelper = new HandlerStreamUtil(is, os);

                        MWorkItemDataList wirDataList = new MWorkItemDataList();
                        streamHelper.read(wirDataList);//read the mworktem datalist from the stream

                        processUpload(user, wirDataList);
                        

                        streamHelper.writeSucess();
                        streamHelper.flush();
                } catch (Exception ex) {
                        Logger.getLogger(IISWIRUploadHandler.class.getName()).log(Level.SEVERE, null, ex);
                        
                            //throw new RuntimeException(ex);
                }
        }

        public void processUpload(User user, MWorkItemDataList wirDataList)
        {
               Vector dataList = wirDataList.getDataList();
                for (Object object : dataList) {
                        MWorkItemData wirDat = (MWorkItemData) object;
                        saveXFormData(user, wirDat);//extract xform data and save it locally
                        processWorkItemData(user, wirDat);//extract workItem data and send it to yawl
                }
        }

        /**
         * 
         * Extracts the xform data from the workItem data then calls saves the form
         * data locally
         * @param user user who has submitted the form data
         * @param wirDat workItemdata to submit
         * 
         */
        
        public void saveXFormData(User user, MWorkItemData wirDat)
        {
                List<String> xformModels = deserialise(wirDat.getFormData());
                for (String xformModel : xformModels) {
                        formDownloadService.saveFormData(xformModel, user, new Date());
                }
        }

        /**
         * Deserialises study data to an xform model.
         * @param data
         * @return
         */
        private List<String> deserialise(StudyDataList data)
        {
                List<String> xmlForms = new ArrayList<String>();
                try {
                        Vector<StudyData> studies = data.getStudies();
                        Method deserialiseMethod = 
                                KxmlXformSerializer.class.
                                                    getDeclaredMethod("deSerialize", StudyData.class, List.class, Map.class);
                        
                        deserialiseMethod.setAccessible(true);

                        for (StudyData studyData : studies) {
                                try {
                                        deserialiseMethod.invoke(serialiser, studyData, xmlForms, xformMap);
                                } catch (Exception x) {
                                       log.error("Error while deserialising study data id: "+studyData.getId(),x);
                                }
                        }
                } catch (Exception ex) {
                       log.error(ex);
                        throw new RuntimeException(ex);
                }
                return xmlForms;
        }

        /**
         * 
         * Extracts the question answers for output that were matched to output parameters
         * and submits them to the yawl engine.
         * @param user
         * @param wirDat
         * 
         */
        
        private void processWorkItemData(User user, MWorkItemData wirDat)
        {
                
                //Mimano New
                PageData tmpPage= null;
                Vector pages = new Vector(0);
                Vector questions = new Vector(0);
                QuestionData qd=null;
                boolean toSend=true;
                
                /**
                 * pass the eventypeid to event status service to determine which it belongs to
                 * whether birth or death, determined from the oxd settings
                 * 
                 * 
                 * 
                 */
                
                int eventtypeid = wirDat.getFormId();
                Integer eventid;
                
                FormData tmpFormData = wirDat.getFormDataData();
                pages = tmpFormData.getPages();
                
                String eventanswer="";               
                String date="";
                 /**
                String notes="";
                String done="";
                String reason=""; */
                String username = user.getName();
                
				for (int i = 0; i < pages.size(); i++) {
					tmpPage = (PageData) pages.elementAt(i);
					questions = tmpPage.getQuestions();
					for (int j = 0; j < questions.size(); j++) {
						qd = (QuestionData) questions.elementAt(j);
						if (qd != null) {
							
							QuestionDef qdef = qd.getDef();
							String dataDesc = qd.getDataDescription();
							
							System.out.println("DEF AS =>"+qdef+" --AND DESC AS=> "+dataDesc);
                                                        System.out.println("username => " + username);
                                                        
							String textm = qd.getText();
                                                        
                                                       if (textm != null) {
								 if (qd.getText().equalsIgnoreCase("eventid")) {
									if (qd.getTextAnswer() != null) {
										eventanswer = qd.getTextAnswer();
                                                                                eventid = Integer.valueOf(eventanswer);
                                                                                //qd.getTextAnswer();eve
									} else {
										eventid = new Integer(-1);
									}
                                                                        
                                                                        //eventStatusService.setEventStatus(eventtypeid, eventid);
								} 
		
						}
		
						// System.out.println("UPLOADED DATA FOR QUESTION->"+qd.getText()
						// +"WITH ANSWER->"+qd.getTextAnswer());
						// System.out.println("SECOND UPLOADED DATA FOR QUESTION->"+qd.getText()
						// +"WITH ANSWER->"+qd.getValueAnswer());
						// System.out.println();
		
					}
				}
			
                        }
                //Mimano edit
                //wirService.submitWorkItem(workitem, qnList);
        }
        
        
        private String pushToServer(String url) {
			// TODO Auto-generated method stub
        	StringBuffer response=new StringBuffer();
        	URL upUrl=null;
            try {
                upUrl = new URL(url);
            } catch (MalformedURLException ex) {
                Logger.getLogger(WIRDownloadHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            //BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            InputStream in=null;
            try {
                in = upUrl.openStream();
                int chr;
                
                while((chr=in.read())!=-1){
                	response.append((char)chr);
                	
                }
            } catch (IOException ex) {
                Logger.getLogger(LaunchCaseServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return response.toString();
            
			
		}
        
		private HashMap<Integer, FormDef> formDefVersionCache = new HashMap<Integer, FormDef>();

        private FormDef getMatchingMobileFormDef(MWorkItemData wirDat)
        {
                int formId = wirDat.getFormId();
                FormDef fDef = formDefVersionCache.get(formId);
                if (fDef == null) {
                        FormDefVersion formDefVersion = exportService.getFormDefVersion(formId);
                        fDef = KxmlSerializerUtil.fromXform2FormDef(new StringReader(formDefVersion.getXform()));
                        formDefVersionCache.put(formId, fDef);
                }
                return fDef;
        }
        
        public static String reverseDate(String source) {
    		String [] items = source.split("/");
    		StringBuffer bf = new StringBuffer();
    		
    		for (int i = (items.length-1); i > 0; i--) {
    			bf.append(items[i]+"/");
    		}
    		
    		bf.append(items[0]);
    		
    		return bf.toString();
    	}

}
