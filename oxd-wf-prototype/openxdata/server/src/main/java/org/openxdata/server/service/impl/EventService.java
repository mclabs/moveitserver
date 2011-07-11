/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.server.service.impl;

import java.util.List;

/**
 *
 * @author jmaina
 */
public abstract class EventService {
    
    /**
     * TODO -> this should be in the service layer.
     * 
     * this is a handler for returning to the service layer a success status
     * whether saving was successful or not. It is to be used when replying to 
     * the RapidSMS system that the sms has been successfully saved.
     * @return 
     */
    
    public abstract boolean isSavedDeathEvents();
    
    
    
    
    public abstract List getAllEvents();
    
    
    /**
     * Get all the BirthRecords that a specific user sent
     * @param birthReport
     * @return 
     */
    public abstract List findBirthEventsByReporter(int reporterId);
    
}
