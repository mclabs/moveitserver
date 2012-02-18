/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.service;

import java.util.List;
import org.openxdata.modules.moveit.server.model.BirthReport;

/**
 *
 * @author jmaina
 */
public interface BirthEventService
{  
    /**
     * Save an instance of a birth event 
     * @param birthReport 
     */
    
    public BirthReport getBirthEventById(int birthReport);
    
    
    
    public boolean saveBirthEvent(BirthReport birthReport);
     
     
     /**
      * Delete an instance of a birth event
      * @param birthReport 
      */
    
    public boolean deleteBirthEvent(BirthReport birthReport);
    
    
    
    public List<BirthReport> getAllBirthEvents();
    
    
    /**
     * will provide useful for the interface
     * 
     * retrieving all finished entries. Works by compairing all accomplised events
     * and 
     * @return 
     */
    public List<Object> getAllCompletedEvents();
    
    
    /**
     * 
     * useful for the user interface
     * 
     * gets a list of remaining events. This is done by comparing all the events 
     * which have been sent to mobile phone and filled agaisnt those which havent 
     * been completed. This will involve comparing two tables. 
     * 
     * to determine whether it can update the repective events table
     * 
     * 
     * @return 
     */
    public List<BirthReport> getIncompletedEvents();
    
    
     public BirthReport getBirthEventByEventId(String eventId);
     
     public List<BirthReport> getBirthEventByReporterId(Long reporterId);
}
