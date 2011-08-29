/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.handlers;


import java.io.StringReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;
import org.openxdata.model.FormDef;
import org.openxdata.modules.moveit.server.model.BirthReport;
import org.openxdata.modules.moveit.server.model.DeathReport;
import org.openxdata.server.Context;
import org.openxdata.server.admin.model.User;
import org.openxdata.server.admin.model.exception.OpenXDataValidationException;
import org.openxdata.server.serializer.KxmlSerializerUtil;
import org.openxdata.server.service.AuthenticationService;
import org.openxdata.server.service.FormDownloadService;

/**
 *
 * @author jmaina
 */

public class DataHandlerUtil {
    
	private HashMap<String,String> formXml;

	private HashMap<String,FormDef> formDefs;
        
        private String formid;
	
        org.openxdata.model.FormData formData;
                   
        private Logger log;
        
	private FormDownloadService formDownloadService = 
                (FormDownloadService) Context.getBean("formDownloadService");
        
	private AuthenticationService authenticationService = 
                (AuthenticationService)Context.getBean("authenticationService");
         
               
        public DataHandlerUtil() 
        {
            init();
        }
         
        private void init() {
                log = Logger.getLogger(DataHandlerUtil.class.toString());
		formDefs = new HashMap<String,FormDef>();
		formXml = new HashMap<String,String>();

		//Prefetch a list of all form definitions from the server such that we do
		//not have to do it at each sms received, hence making a performance boost in
		//sms processing. The trade of is that if someone creates a new form after
		//the sms server has already been started, they have to restart it in order
		//to pick up the new form definitions.
                String username = "admin";
                String password = "admin";
                User user = authenticationService.authenticate(username, password);
                System.out.println(user.getName());
		
                //TODO Need to use proper locale
		List<String> forms = formDownloadService.getFormsDefaultVersionXml(user,"en");

		for(String xml : forms){
			try{
                                System.out.println("the data is.... " + xml);
				FormDef formDef = KxmlSerializerUtil.fromXform2FormDef(new StringReader(xml));
				formDefs.put(formDef.getVariableName(), formDef);
				formXml.put(formDef.getVariableName(), xml);
			}
			catch(Exception ex){
				log.info("@... An error occured getting the formdef... DataHandlerUtil");
			}
		}		
	}
        
        /**
         * 
         * Method takes an object which is determined at runtime whether its 
         * a birth or death object. This is necessary to get the right form 
         * for the event
         * 
         * @param obj
         * @return
         * @throws OpenXDataValidationException 
         */
        public org.openxdata.model.FormData initFormData(Object obj) throws OpenXDataValidationException{
            
		//Get the form identifier.
		//get the id from the properties using settingsService

                if (obj instanceof BirthReport){
                    //formid = settingService.getSetting("birthform.id");
                    formid="1";
                    log.info("GETTING FormID->"+formid);
                    System.out.println("the name of the birthid is  " +formid);
                }
                else if (obj instanceof DeathReport){
                    //formid = settingService.getSetting("deathreport.id");
                    formid="2";
                    log.info("GETTING FormID->"+formid);
                    System.out.println("the name of the deathid is  " + formid);
                }
                            
		FormDef formDef = formDefs.get(formid);
		if(formDef == null){
			Integer id = getFormId(formid);
			Collection<FormDef> forms = formDefs.values();
			for(FormDef form : forms){
				if(form.getId() == id.intValue()){
					formDef = form;
					break;
				}
			}
		}
		if(formDef == null){
			System.out.println("there is no formdef by this id");
                        log.info("there is no formdef by this id");
                }

		formData = new org.openxdata.model.FormData(formDef);
    
		return formData;

	}
        
        
        private Integer getFormId(String id){
		try{
			return Integer.parseInt(id);
		}catch(Exception ex){}

		return null;
	}
        
}