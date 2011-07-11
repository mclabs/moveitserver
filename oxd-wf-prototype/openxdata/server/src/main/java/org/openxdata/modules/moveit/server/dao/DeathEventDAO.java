/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.dao;

import java.util.List;
import org.openxdata.server.admin.model.DeathReport;
import org.openxdata.server.dao.BaseDAO;

/**
 *
 * @author jmaina
 */
public interface DeathEventDAO extends BaseDAO<DeathReport> 
{
    
    /**
     * Save an instance of a death event
     * 
     * @param deathEvent 
     */
    void saveDeathEvent(DeathReport deathEvent);
    
    
    /**
     * deletes an instance of a death event
     * @param deathEvent 
     */
    void deleteDeathEvent(DeathReport deathEvent);
    
    
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
    
    boolean savedDeathEvents();
    
}
