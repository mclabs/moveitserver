/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import org.jdom.Document;
import org.jdom.Element;
import org.openxdata.modules.workflows.model.shared.WorkItemQuestion;
import org.openxdata.modules.workflows.server.YawlOXDCustomService;
import org.openxdata.modules.workflows.server.handlers.RequestHandler;
import org.openxdata.server.admin.model.StudyDef;
import org.openxdata.server.service.SettingService;
import org.openxdata.server.service.StudyManagerService;
import org.openxdata.workflow.mobile.model.MQuestionMap;
import org.openxdata.workflow.mobile.model.MWorkItem;
import org.yawlfoundation.yawl.util.JDOMUtil;

import org.openxdata.modules.moveit.server.model.BirthReport;
import org.openxdata.modules.moveit.server.model.CHWModel;
import org.openxdata.modules.moveit.server.model.DeathReport;
import org.openxdata.modules.moveit.server.service.BirthEventService;
import org.openxdata.modules.moveit.server.service.CHWManagerService;
import org.openxdata.modules.moveit.server.service.DeathEventService;
import org.openxdata.modules.moveit.server.service.UserEventReporterService;
import org.openxdata.server.Context;
import org.openxdata.server.admin.model.FormDef;
import org.openxdata.server.admin.model.User;
import org.openxdata.server.service.AuthenticationService;
import org.openxdata.server.service.FormDownloadService;
import org.openxdata.server.service.FormService;

/**
 *
 * @author kay
 * 
 * The code should have status codes to indicate that the phone has been downloaded to the 
 * phone or not. This is to help keep an audit trail of what has been committed to the mobile
 * and what has not.
 * 
 * 
 * 
 * Download all the forms by user. check by reporter_ids. So the trick here then is 
 * to download all the forms pertaining to a particular study, instead of getting 
 * individual forms.
 * 
 * Filter reporter id's belonging to individual supervisers. (New functionality needs to be added)
 * 
 * Issue of dealing of roles. Need to check how roles are handled in the system.
 * 
 * Need to determine the right event so that we can be able to load the right event to a WorkItem
 * 
 * 
 * 
 */

@SuppressWarnings("UseOfObsoleteCollectionType")
public class WIRDownloadHandler implements RequestHandler {

    private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass());
    private SettingService settingService;
    private StudyManagerService studyService;
    private StudyDef moveit_Study;
    private org.openxdata.server.admin.model.FormDef eventForm;
    private List<org.openxdata.server.admin.model.FormDef> formList;
    
    private HashMap<String,String> formXml;
    private List<Element> children;

    private HashMap<String,FormDef> formDefs;
    private String formid;
    org.openxdata.model.FormData formData;
    private FormDownloadService formDownloadService;
    private AuthenticationService authenticationService;
    private UserEventReporterService userEventReporterService;
    private BirthEventService birthEventService;
    private DeathEventService deathEventService;
    FormService formservice;
    CHWManagerService chwManagerService;
    
    String moveitStudy;
    String deathForm;
    String birthForm;
    
    StudyDef moveitDef;
    FormDef birthDef;
    FormDef deathDef;
    
    int birthFID;
    int deathFID;
    List<FormDef> allEvents;
    
    List<BirthReport>  birthReportList;
    List<DeathReport>  deathReportList;
    
    
    /**
     * 
     * TODO find a way of determining how an even occurs
     * 
     * 
     * finding a way to pass the user to this handler so that filtering can be done 
     * according to this specific user
     * 
     * Use the birth/death service to recieve the objects from the database.
     * '
     * Prefilling values as they are being sent to workflow items
     * 
     * 
     * 
     */

    public WIRDownloadHandler() {
        
        settingService = 
                (SettingService) Context.getBean("settingService");
        studyService = 
                (StudyManagerService) Context.getBean("studyManagerService");
        formDownloadService = 
                (FormDownloadService) Context.getBean("formDownloadService");
        authenticationService = 
                (AuthenticationService)Context.getBean("authenticationService");
        userEventReporterService =
                (UserEventReporterService) Context.getBean("userReporterService");
        birthEventService =
                (BirthEventService) Context.getBean("birthEventService");
        deathEventService =
                (DeathEventService) Context.getBean("deathEventService");
        formservice =
                (FormService) Context.getBean("formService");
        chwManagerService =
                (CHWManagerService) Context.getBean("chwManagerService");
        
        
        
    }

    //Constructor.. simply to help me do some unit tests 
    public WIRDownloadHandler(boolean kk) {}
    
    
    /**
     * 
     * @param appoint
     * @return 
     * 
     * 
     * formData.setValue("child_name", birthReport.getEventName());
            formData.setValue("date_of_birth", birthReport.getDateOfEvent());
     * 
     */
    
    public Vector<MWorkItem> toMWorkItems(List<String> formXml, User user) {
        
        Vector<MWorkItem> workItemList = new Vector<MWorkItem>();
        
                    
        //for births
        /**
         * <child_other_name/>
        <child_fathers_name/>
        <c._date_of_birth/>
        <gender/>
        <type_of_birth/>
        <other_type_of_birth/>
        <nature_of_birth/>
        <place_of_birth/>
        <mother_first_name/>
        <father_first_name/>
        <capacity_of_informant/>
        <r.a._date/>
        <registrar._district/>
        <mother_middel_name/>
        <mother_surname/>
        <age/>
        <marital_status/>
        <mother_nationality/>
        <occupation/>
        <id_number/>
        <level_of_education/>
        <usual_residence/>
        <usual_residence_district/>
        <previous_births_born_alive/>
        <previous_births_born_dead/>
        <father_middle_name/>
        <father_surname/>
        <father_nationality/>
        <id_number_slash_passport/>
        <do_you_certify_the_information_is_correct/>
        <informant_id_number_slash_passport/>
        <date/>
        <i._signature/>
        <registration_assistant_for/>
        <r.a_name_and_signature/>
        <r._registration_no./>
        <r._date/>
         */
        
        /**
         * 
         * admin can download all the forms so long as he has the password
         * 
         * code to filter out CHW by location. 
         * 
         * so get the user, get the user's phone number, 
         * get all the CHW's that fall under the given user.
         * then load all the events by reporter id.
         * 
         * 
         * 
         
        String managerPhoneNumber = user.getPhoneNo();
        
        if (!user.getName().equalsIgnoreCase("admin"))
        {
        //String managerPhoneNumber = user.getPhoneNo();
        
        birthReportList = new ArrayList<BirthReport>();
        List <BirthReport> birthList = new ArrayList<BirthReport>();
        
        List<CHWModel> chwModelList 
                = chwManagerService.retrieveCHWByManagerNumber(managerPhoneNumber);
        
        
        
                for (CHWModel chw: chwModelList)
                {
                    Long reporterNumber = Long.valueOf(chw.getMobileNumber());
                    birthList = birthEventService.getBirthEventByReporterId(reporterNumber);
                    
                    for (BirthReport birthReport : birthList)
                    {
                        birthReportList.add(birthReport);
                    }
                }
        }
        else{
            
            //the user is admin so he can dowload all the forms
            birthReportList = birthEventService.getAllBirthEvents();
        } */
      
           birthReportList = birthEventService.getAllBirthEvents();
            
            System.out.println("@toMworkItems request size of birth events"+birthReportList.size());
            
            for (BirthReport birthReport : birthReportList) {
                String birthxml=" <moveit_birthform_v1>"
                        + "<eventid>" +birthReport.getEventId() +  "</eventid>" 
                        + "<child_first_name>"+birthReport.getEventName()+"</child_first_name>" +
                         "<c._date_of_birth>"+birthReport.getDateOfEvent().toString()+"</c._date_of_birth> " +            
                        "</moveit_birthform_v1>";
                Document birthdocument = JDOMUtil.stringToDocument(birthxml);
                System.out.println("@toMworkItems document is like so=>"+birthdocument.toString());
                System.out.println("@prefilled birth xml document " + birthxml);


                    MWorkItem wir = new MWorkItem();


                        List<WorkItemQuestion> workItemQuestions = YawlOXDCustomService.createQuestionListFromXML(birthdocument.getRootElement());
                        Vector<MQuestionMap> quenMaps = toQuestionMaps(workItemQuestions);
                        wir.setStudyId(moveitDef.getStudyId());
                        wir.setFormId(birthDef.getFormId());
                        wir.setTaskName(birthDef.getName()+" -- "+birthReport.getEventName());
                        wir.setPrefilledQns(quenMaps);                       
                        workItemList.add(wir);

            }
   
            System.out.println("@toworkitems gotten birth");
            
     
        /**
         * 
         * formData.setValue("name", deathReport.getEventName());
            formData.setValue("dateofdeath", deathReport.getDateOfEvent());
         * 
         
        
        //for deaths
            
        if (!user.getName().equalsIgnoreCase("admin"))
        {
        
        deathReportList = new ArrayList<DeathReport>();
        List <DeathReport> deathList = new ArrayList<DeathReport>();
        
        List<CHWModel> chwModelListD 
                = chwManagerService.retrieveCHWByManagerNumber(managerPhoneNumber);
        
        
        
                for (CHWModel chw: chwModelListD)
                {
                    Long reporterNumber = Long.valueOf(chw.getMobileNumber());
                    deathList = deathEventService.getDeathEventByReporterId(reporterNumber);
                    
                    for (DeathReport deathReport : deathList)
                    {
                        deathReportList.add(deathReport);
                    }
                }
        }   
        
        else
        {
            //the user is admin so he can dowload all the forms
            deathReportList = deathEventService.getAllDeathEvents();
        } */
            deathReportList = deathEventService.getAllDeathEvents();
            for (DeathReport deathReport : deathReportList) {
                 String deathXML="<moveit_deathform_d2>" 
                         + "<eventid>" +deathReport.getEventId() +  "</eventid>"
                         + "<name>"+deathReport.getEventName()+"</name>"
                         + "<dateofdeath>"+deathReport.getDateOfEvent().toString()+"</dateofdeath>"
                         + "</moveit_deathform_d2>";
            
                    Document deathdocument = JDOMUtil.stringToDocument(deathXML);
                    System.out.println("@toMworkItems document is like so=>"+deathdocument.toString());
                    System.out.println("@prefilled death xml document " + deathXML);
                  
                    MWorkItem wir = new MWorkItem();
                

                    List<WorkItemQuestion> workItemQuestions = YawlOXDCustomService.createQuestionListFromXML(deathdocument.getRootElement());
                    Vector<MQuestionMap> quenMaps = toQuestionMaps(workItemQuestions);
                    
                    System.out.println("@ death form id is" + deathDef.getFormId());
                    
                    wir.setStudyId(moveitDef.getStudyId());
                    wir.setFormId(deathDef.getFormId());
                    wir.setTaskName(deathDef.getName()+" -- "+deathReport.getEventName());
                    wir.setPrefilledQns(quenMaps);                  
                    workItemList.add(wir);
                            
            }
            
     return workItemList;   
        
    }

    private Vector<MQuestionMap> toQuestionMaps(List<WorkItemQuestion> workItemQuestions) {
        Vector<MQuestionMap> quenMaps = new Vector<MQuestionMap>();
        for (WorkItemQuestion workItemQuestion : workItemQuestions) {
            MQuestionMap map = new MQuestionMap();

            map.setQuestion(workItemQuestion.getQuestion());
            map.setValue(workItemQuestion.getAnswer());
            map.setParameter(workItemQuestion.getQuestion());
            quenMaps.add(map);
        }
        return quenMaps;
    }

    private void setStudyAndCaseAttributes(MWorkItem wir) {
        wir.setStudyId(moveit_Study != null ? moveit_Study.getId() : 0);
        wir.setFormId(eventForm != null ? eventForm.getDefaultVersion().getId() : 0);
        
        //Here I set the case ID and taskiD with appoint_id and dose_id to enable uniquely identinfying the
        //MWorkitems uniquely.. Its hack but am sure u can find a way around  this
        //wir.setCaseId(wir.getParam("appointment_id").getValue());
        //wir.setTaskId(wir.getParam("dose_id").getValue());
    }

    /**
     * Initialises the study and formDef for this workItem, the initilizer needs
     * to determine which form to load for which event
     */
    private void setMoveit_STudyAndForm() {
        //Get the IIS studyName from the setting service
        moveitStudy = settingService.getSetting("moveit.study");
        birthForm = settingService.getSetting("birth.form");
        deathForm = settingService.getSetting("death.form");
        
        
        //Get the IIS studyName from the setting service
        System.out.println("Moveit Study:->"+moveitStudy);
        List<StudyDef> studies = studyService.getStudies();
        for (StudyDef studyDef : studies) {
        	System.out.println("STUDY NAME:->"+studyDef.getName());
            if (studyDef.getName().equals(moveitStudy)) {
                moveitDef = studyDef;
                break;
            }
        }
        //Get the birth from from the setting Service
        String birthFormName = settingService.getSetting("birth.form");
        System.out.println("birth Form:->"+birthFormName);
        if (moveitStudy == null) {
            return;
        }
        List<org.openxdata.server.admin.model.FormDef> forms = moveitDef.getForms();
        for (org.openxdata.server.admin.model.FormDef formDef : forms) {
        	System.out.println("Birth FORM NAME:->"+formDef.getName());
            if (formDef.getName().equals(birthFormName)) {
                birthDef = formDef;
                formList.add(birthDef);
                break;
            }
        }
        
        
        String deathFormName = settingService.getSetting("death.form");
        System.out.println("death Form:->"+deathFormName);
        
        for (org.openxdata.server.admin.model.FormDef formDef : forms) {
        	System.out.println("Death FORM NAME:->"+formDef.getName());
            if (formDef.getName().equals(deathFormName)) {
                deathDef = formDef;
                formList.add(deathDef);
                
                System.out.println("@ death from id is..." + deathDef.getFormId());
                        
                break;
            }
        }
    }
    
    /**
     * get the xml representation of the xform and pass it as a string
     * 
     * need to figure out at run time which form to load whether birth or death
     * 
     * @return 
     */
    
        private List<String> getEventXML(User user) {
                
		formDefs = new HashMap<String,FormDef>();
		formXml = new HashMap<String,String>();

		
                String username = user.getName();
                //String password = user.getPassword();
                System.out.println("@handler Username"+username);
                //user = authenticationService.authenticate(username, password);
                System.out.println(user.getName());
		
                //TODO Need to use proper locale
		List<String> forms = formDownloadService.getFormsDefaultVersionXml(user,"en");
                
                log.debug("this is the xml representation... " + forms.get(0).toString());
                
                /**
                 * 
                 * the idea is to save the a Map: key -> value pairs. So for each form it will
                 * be associated with an appropriate key which is the object/event to which it 
                 * belongs /DeathReport/BirthReport.
                 * 
                 */
                
                return forms;
	}
        
        
        
    /**
         * 
         * 
         * Pass the desired object which needs to be downloaded as this is the moethod which is
         * doing the handling. Or alternatively create another interface that does something 
         * similar. It would make more sense to re-invent the wheel.
         * 
         * @param user
         * @param is
         * @param os
         * @throws IOException 
         */

    @Override
    public void handleRequest(User user, InputStream is, OutputStream os) throws IOException {
            allEvents=new ArrayList<FormDef>();
            formList = new ArrayList<FormDef>();
            setMoveit_STudyAndForm();
      
            //Get the list of all xform representation of data. Need a 
            //way of breaking it down to births and deaths
            List<String> formxml = getEventXML(user);
            //Parse the apponintments XML and convert them to mWorkitems
            
                Vector<MWorkItem> workItems = toMWorkItems(formxml, user);
                log.debug("Downloading workitems for User: " + user.getName());
                HandlerStreamUtil streamHelper = new HandlerStreamUtil(is, os);
                streamHelper.writeSucess();
                streamHelper.writeSmallVector(new Vector(workItems));
                streamHelper.flush();
           
            
    }
    
        private Integer getFormId(String id){		
            try{		
                return Integer.parseInt(id);		
            }catch(Exception ex){}
		return null;
	}
    
   
}
