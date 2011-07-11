/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.server.dao;

import java.util.List;
import org.openxdata.server.admin.model.BirthReport;

/**
 *
 * @author jmaina
 */
public interface BirthEventDAO extends BaseDAO<BirthReport> {
    
    
    
    /**
     * Save an instance of a birth event 
     * @param birthReport 
     */
     void saveBirthEvent(BirthReport birthReport);
     
     
     /**
      * Delete an instance of a birth event
      * @param birthReport 
      */
     void deleteBirthEvent(BirthReport birthReport);
     
     
     /**
      * Returns a list of Birth Events
      * @return 
      */
     List <BirthReport> getBirthEvents();
     
     
     /**
     * TODO -> this should be in the service layer.
     * 
     * this is a handler for returning to the service layer a success status
     * whether saving was successful or not. It is to be used when replying to 
     * the RapidSMS system that the sms has been successfully saved.
     * @return 
     */
     boolean savedBirthEvents();
    
}
