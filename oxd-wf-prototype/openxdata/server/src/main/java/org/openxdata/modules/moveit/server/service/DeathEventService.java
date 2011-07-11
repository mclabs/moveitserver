/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.service;

import java.util.List;
import org.openxdata.server.admin.model.DeathReport;

/**
 *
 * @author jmaina
 */
public interface DeathEventService 
{
    
    /**
     * Get a specific Death Record
     * 
     * @param deathReport
     * @return 
     */
    DeathReport getDeathEvent(DeathReport deathReport);
    
    
    /**
     * Get a list of All DeathRecords returned in the system
     * @return 
     */
    List<DeathReport> getAllDeathReports();
    
    
    /**
     * Get a list of all Death Records Reported by a specific user of 
     * RapidSMS that are saved in the system
     * 
     * @param reporterId
     * @return 
     */
    List<DeathReport> getDeathEventsByReporter(int reporterId);
    
    
    
    /**
     * Save an instance of a death event
     * 
     * @param deathEvent 
     */
    boolean saveDeathEvent(DeathReport deathEvent);
    
    
    /**
     * deletes an instance of a death event
     * @param deathEvent 
     */
    boolean deleteDeathEvent(DeathReport deathEvent);
    
    
    /**
     * Returns a list of saved Death Events
     * @return 
     */
    List <DeathReport> getDeathEvents();
    
    
    /**
     * TODO -> this should be in the service layer.
     * 
     * this is a handler for returning to the service layer a success status
     * whether saving was successful or not. It is to be used when replying to 
     * the RapidSMS system that the sms has been successfully saved.
     * @return 
     */
    
    boolean isSavedDeathEvents();
    
}
