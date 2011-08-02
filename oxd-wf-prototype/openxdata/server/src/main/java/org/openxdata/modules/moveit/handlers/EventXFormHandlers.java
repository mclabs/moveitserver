/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.handlers;

import org.openxdata.modules.moveit.server.model.BirthReport;
import org.openxdata.modules.moveit.server.model.DeathReport;

/**
 *
 * @author jmaina
 * 
 * this class represents a mock representation of how the workflow will work.
 * 
 * Functionality of this class is soon to be deliberated upon. As it will be rendered 
 * redundant when the workflows are implemented.
 * 
 */
public class EventXFormHandlers {
    
    private BirthReport birthReport;
    private DeathReport deathReport;
    
    private String xmlRepresentation;
    
    
    public EventXFormHandlers(){}
    
    
    public EventXFormHandlers(BirthReport birthReport){
        
        this.birthReport = birthReport;
    }
    
    public EventXFormHandlers(DeathReport deathReport){
        
        this.deathReport = deathReport;
    }
    
    
    public String returnXFormRepresentation(BirthReport birthReport)
    {
        
        String xform = 
                "<?xml version='1.0' encoding='UTF-8'?> " +
                    "<xf:xforms xmlns:xf='http://www.w3.org/2002/xforms' "
                            + " xmlns:xsd='http://www.w3.org/2001/XMLSchema'>"+
    
                        "<xf:model>"+
                            "<xf:instance id='new_study1_new_study1_form3_v1'>"+
                                "<new_study1_new_study1_form3_v1 name='birth form b1' id='7' formKey='new_study1_new_study1_form3_v1'>"+
                                    "<child_name>"
                                        + birthReport.getEventName() + 
                                    "<child_name/>" +
                                    "<date_of_birth>" 
                                        + birthReport.getDateOfEvent()+ 
                                    "<date_of_birth/>" +
                                    "<sex/>"+
                                    "<type_of_birth/>"+
                                    "<other/>"+
                                    "<nature_of_birth/>"+
                                    "<weight_of_birthkg/>"+
                                    "<place_of_birth/>"+
                                    "<mother_name/>"+
                                    "<age/>"+
                                    "<marital_status/>"+
                                    "<mother_nationality/>"+
                                    "<occupation/>"+
                                    "<mother_id_number/>"+
                                    "<level_of_education/>"+
                                    "<usual_residence>" +
                                    "<usual_residence/>" +
                                    "<previous_born_alive/>"+
                                    "<previous_born_dead/>"+
                                    "<name/>"+
                                    "<father_nationality/>"+
                                    "<father_id_number/>"+
                                    "<capacity_of_informant/>"+
                                    "<certification/>"+
                                    "<date/>"+
                                    "<signature/>"+
                                    "<assistant_date>"
                                        + birthReport.getDateOfReport() + 
                                    "<assistant_date/>" +
                                    "<registration_assistant_for/>"+
                                    "<name_and_signature/>"+
                                    "<registrar_district/>"+
                                    "<registration_number/>"+
                                    "<registrar_date>"
                                        + birthReport.getDateOfReport() + 
                                    "<registrar_date/>" +
                                    "<registrar_name/>"+
                                    "<registrar_signature/>"+
                              "</new_study1_new_study1_form3_v1>"+
                        "</xf:instance>"+    
                   "</xf:model>";
         
        return xform;
    }
    
    public String returnXFormRepresentation(DeathReport deathReport)
    {
        
         String xform = 
                 "<?xml version='1.0' encoding='UTF-8'?>"+
                            "<xf:xforms xmlns:xf='http://www.w3.org/2002/xforms' "
                                    + "xmlns:xsd='http://www.w3.org/2001/XMLSchema'>"+
                       "<xf:model>" +
                            "<xf:instance id='new_study1_new_study1_form1_v1'>" +
                                  "<new_study1_new_study1_form1_v1 name='death form d1' id='5' formKey='new_study1_new_study1_form1_v1'>"+
                                    "<name/>" 
                                            + deathReport.getEventName() + 
                                    "<name/>"+
                                    "<idnumber/>" +
                                    "<nationality/>" +
                                    "<gender/>" +
                                    "<age/>" +
                                    "<dateofdeath>"
                                            + deathReport.getDateOfEvent() + 
                                    "<dateofdeath/>"+
                                    "<maritalstatus/>" +
                                    "<placeofdeath>" +  "<placeofdeath/>" +
                                    "<usualresidence/>" + 
                                    "<levelofeducation/>" +
                                    "<occupation/>" + 
                                    "<immediate_cause_of_death/>"+
                                    "<antecedent_cause/>" +
                                    "<other_cause/>" +
                                    "<certifying/>" +
                                    "<name_of_examiner/>" + 
                                    "<title/>" + 
                                    "<date/>" + 
                                    "<certifier_signature/>" + 
                                    "<registration_date/>" + 
                                    "<healthinstitution/>" + 
                                    "<registration_signature/>" +
                                    "<district>" +
                                    "<district/>" +
                                    "<registration_number/>" +
                                    "<registrar_date>" 
                                            + deathReport.getDateOfReport() +
                                    "<registrar_date/>" +
                                    "<registrar_name/>" +
                                    "<signature/>" +
                                  "</new_study1_new_study1_form1_v1>" +
                          "</xf:instance>" +
                   "</xf:model>";
         
         
        return xform;
    }
    
}
