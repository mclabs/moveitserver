/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.openxdata.modules.moveit.server.model.BirthReport;
import org.openxdata.modules.moveit.server.model.DeathReport;
import org.openxdata.modules.moveit.server.service.BirthEventService;
import org.openxdata.modules.moveit.server.service.DeathEventService;
import org.openxdata.server.Context;
import org.openxdata.modules.moveit.server.service.EventStatusService;
import org.openxdata.server.service.SettingService;
import org.openxdata.server.service.StudyManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.openxdata.server.admin.model.StudyDef;
import org.openxdata.server.admin.model.FormDef;


/**
 *
 * @author jmaina
 * 
 * when an event has entered the system it is concidered to be incomplete, and this service
 * will be aware of that.
 * 
 * through the listener it will listen to whether any changes have been done to the form data
 * and if there are it will update the birth/death events tables as complete. Then this will 
 * be sent back to the outside systems through the EventStatusServlet.
 * 
 */

@Transactional
@Service("eventStatusService")
public class EventStatusServiceImpl implements EventStatusService {
    
    
    private SettingService settingsService = (SettingService) Context.getBean("settingService");
    private BirthEventService birthEventService = (BirthEventService) Context.getBean("birthEventService");
    private DeathEventService deathEventService = (DeathEventService) Context.getBean("deathEventService");
    private StudyManagerService studyService = (StudyManagerService) Context.getBean("studyManagerService");
    
    private String moveitStudy;
    private String deathFormName;
    private String birthFormName;
    private String status;
    
    private StudyDef moveitDef;
    FormDef birthDef;
    FormDef deathDef;

    @Override
    public String getEventStatus(String eventId) {
        
        /**
         * the purpose of this code is to get the status of an eventid
         */
        
        BirthReport birthReport = birthEventService.getBirthEventByEventId(eventId);
        DeathReport deathReport = deathEventService.getDeathEventByEventId(eventId);
        
        if (birthReport !=null)
        {
            status = birthReport.getStatus();
        } 
        else if (deathReport !=null)
        {
            status = deathReport.getStatus();
        }
        
        System.out.println(status + "from the eventstatus servlet implementation");
        return status; //place holder for the status reponse.
    }

    /**
     * 
     * to re-implement this method
     * 
     * 
     * @param eventTypeId
     * @param eventType
     * @return 
     */
    
    @Override
    public void setEventStatus(int eventTypeId, String eventType) {
        
        BirthReport birthReport;
        DeathReport deathReport;
        int valueOfBirthForm;
        int valueOfDeathForm;
        
        moveitStudy = settingsService.getSetting("moveit.study");
        birthFormName = settingsService.getSetting("birth.form");
        deathFormName = settingsService.getSetting("death.form");
        
       
        
        System.out.println("Moveit Study:->"+moveitStudy);
        List<StudyDef> studies = studyService.getStudies();
        for (StudyDef studyDef : studies) {
        	System.out.println("STUDY NAME:->"+studyDef.getName());
            if (studyDef.getName().equals(moveitStudy)) {
                moveitDef = studyDef;
                break;
            }
        }
        
        System.out.println("birth Form:->"+birthFormName);
        if (moveitStudy == null) {
            return;
        }
        List<org.openxdata.server.admin.model.FormDef> forms = moveitDef.getForms();
        for (org.openxdata.server.admin.model.FormDef formDef : forms) {
        	System.out.println("Birth FORM NAME:->"+formDef.getName());
            if (formDef.getName().equals(birthFormName)) {
                birthDef = formDef;
                      
                break;
            }           
        }
        valueOfBirthForm = birthDef.getFormId();
        
        
        System.out.println("death Form:->"+deathFormName);
        
        for (org.openxdata.server.admin.model.FormDef formDef : forms) {
        	System.out.println("Death FORM NAME:->"+formDef.getName());
            if (formDef.getName().equals(deathFormName)) {
                deathDef = formDef;
                                 
                break;
            }           
        }
        valueOfDeathForm = deathDef.getFormId(); 
        
        if (eventTypeId == valueOfBirthForm)
        {
            if (eventType != null)
            {
                //set to object as complete
                birthReport = birthEventService.getBirthEventByEventId(eventType);
                birthReport.setStatus("Complete");
                birthEventService.saveBirthEvent(birthReport);
            }
            else
            {
                //event status code to update the status as invalid.
            }
            
        } else if (eventTypeId == valueOfDeathForm)  {
            
            if (eventType != null)
            {
                //set to object as complete
                deathReport = deathEventService.getDeathEventByEventId(eventType);
                deathReport.setStatus("Complete");
                deathEventService.saveDeathEvent(deathReport);
            }
            else
            {
                //event status code to update the status as invalid
            }
            
        }
        
        else {
            //the event does not exits
        }
        
    }

    @Override
    public List<Object> getAllCompletedEvents() {
        
        /**
         * 
         * to develop hibernate code that will enable to query either birth or death 
         * logic to follow the signature of that which has their status as completed
         * 
         */
        return new ArrayList<Object>();
    }

    @Override
    public List<Object> getIncompletedEvents() {
        /**
         * 
         * to develop hibernate code that will enable to query either birth or death 
         * logic to follow the signature of not in 
         * 
         */
        return new ArrayList<Object>();
    }

    @Override
    public String eventStatusListener() {
        
        
        return "";
    }

    @Override
    public String newEventAlert() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
      
    
    /**
     * 
     * 
     * 
     */
        
}
