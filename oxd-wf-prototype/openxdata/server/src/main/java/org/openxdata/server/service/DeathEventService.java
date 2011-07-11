/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.server.service;

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
    public DeathReport getDeathEvent(DeathReport deathReport);
      
    /**
     * Save an instance of a death event
     * 
     * @param deathEvent 
     */
    public boolean saveDeathEvent(DeathReport deathEvent);
    
    
    /**
     * deletes an instance of a death event
     * @param deathEvent 
     */
    boolean deleteDeathEvent(DeathReport deathEvent);
       
    
}
