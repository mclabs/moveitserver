/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.service;

import java.util.List;
import org.openxdata.server.admin.model.BirthReport;

/**
 *
 * @author jmaina
 */
public interface BirthEventService
{
   
    /**
     * getting a specific Birth record
     * 
     * @param birthReport 
     */
    public BirthReport getBirthEvent(BirthReport birthReport);
    
    
    /**
     * getting all Birth records received by the system
     */
    public List getAllBirthEvents();
    
    
    /**
     * Get all the BirthRecords that a specific user sent
     * @param birthReport
     * @return 
     */
    public List findBirthEventsByReporter(int reporterId);
    
    
    /**
     * This method is responsible for returning a success code to the servlet this 
     * is to respond whether the record received from RapidSMS saved successfully.
     * 
     * It returns an abstraction of a status code. Either true for success or 
     * false for failure.
     * 
     * @param birthReport
     * @return 
     */
    
    public boolean isBirthEventSaved(BirthReport birthReport);
    
    
    /**
     * Save an instance of a birth event 
     * @param birthReport 
     */
    
    public boolean saveBirthEvent(BirthReport birthReport);
     
     
     /**
      * Delete an instance of a birth event
      * @param birthReport 
      */
    
    public boolean deleteBirthEvent(BirthReport birthReport);
     
     
     /**
      * Returns a list of Birth Events
      * @return 
      */
    
    public List getBirthEvents();
     
     
    
    
}
