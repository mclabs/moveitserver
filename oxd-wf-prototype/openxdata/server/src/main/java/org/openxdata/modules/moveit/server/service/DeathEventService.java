/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.service;

import java.util.List;
import org.openxdata.modules.moveit.server.model.DeathReport;
import org.openxdata.server.dao.BaseDAO;

/**
 *
 * @author jmaina
 */
public interface DeathEventService extends BaseDAO<DeathReport>
{
    
    /**
     * Get a specific Death Record
     * 
     * @param deathReport
     * @return 
     */
    public DeathReport getDeathEvent(DeathReport deathReport);
    
    public DeathReport getDeathEvent(int deathReportId);
       
    
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
    public boolean deleteDeathEvent(DeathReport deathEvent);
    
    
    public List<DeathReport> getAllDeathEvents();
    
    public DeathReport getDeathEventByEventId(String eventId);
     
}
