/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.service;

import java.util.List;

/**
 *
 * @author jmaina
 * 
 */

public interface EventStatusService 
{
    
    /**
     * querry whether the specific event has successfully been filled from the 
     * mobile. Takes an object it could be a birthevent or deathevent, as we have
     * to determine at runtime which event it is.
     * 
     * @return 
     */
    public String getEventStatus(int eventId);
    
    /**
     * this is for the user interface
     * also to be combined when data is successfully sent back from mobile
     * back to server. We will need to add a field to the birth_Event and death_event tables
     * to be used to determine status
     * 
     * the eventTypeId is for determining if its a birth of death event to be determined from oxd settings
     * the eventId is for determining the specific type of event
     * 
     * to be used in WIRDownloadHandler
     * to be used in WIRUploadHandler
     * @return 
     */
    public void setEventStatus(int eventTypeId, String eventId);
    
    
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
    public List<Object> getIncompletedEvents();
    
    
    
    /**
     * 
     * the purpose of this method is to listen whether there are new incoming events. It is
     * important as this is the method that will trigger all these other methods to perform
     * their respective functions
     * 
     * @return 
     */
    
    public String eventStatusListener();
    
    
    /**
     * to alert the mobile that there are new events. based on the date. 'newness' also
     * depends until the mobile downloads the app, then the eventStatusLister removes newness
     * 
     * @return 
     */
    
    public String newEventAlert();
    
    
    
}
