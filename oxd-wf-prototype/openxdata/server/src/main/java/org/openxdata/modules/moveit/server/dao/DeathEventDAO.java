/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.dao;

import java.util.List;
import org.openxdata.modules.moveit.server.model.DeathReport;
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
    public boolean saveDeathEvent(DeathReport deathEvent);
    
    
    /**
     * Get a specific Death Record
     * 
     * @param deathReport
     * @return 
     */
    public DeathReport getDeathEvent(DeathReport deathReport);
    
    /**
     * deletes an instance of a death event
     * @param deathEvent 
     */
    public boolean deleteDeathEvent(DeathReport deathEvent);
    
    
    
    /**
     * Returns a list of saved Death Events
     * @return 
     */
    public List<DeathReport>  getDeathEvents();
    
    
  
    public List<DeathReport> getDeathEventsByReporter(int reporterId);
    
    
}
