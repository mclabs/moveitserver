/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.dao;

import java.util.List;
import org.openxdata.server.admin.model.BirthReport;
import org.openxdata.server.dao.BaseDAO;

/**
 *
 * @author jmaina
 */
public interface BirthEventDAO extends BaseDAO {
    
    
    
    /**
     * Save an instance of a birth event 
     * @param birthReport 
     */
     public void saveBirthEvent(BirthReport birthReport);
     
     
     /**
      * Delete an instance of a birth event
      * @param birthReport 
      */
     public void deleteBirthEvent(BirthReport birthReport);
     
     
     /**
      * Returns a list of Birth Events
      * @return 
      */
     public List getBirthEvents();
     
     
     /**
     * TODO -> this should be in the service layer.
     * 
     * this is a handler for returning to the service layer a success status
     * whether saving was successful or not. It is to be used when replying to 
     * the RapidSMS system that the sms has been successfully saved.
     * @return 
     */
     public boolean savedBirthEvents();
    
}
