/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.dao.hibernate;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.openxdata.modules.moveit.server.dao.DeathEventDAO;
import org.openxdata.modules.moveit.server.model.DeathReport;
import org.openxdata.server.dao.hibernate.BaseDAOImpl;
import org.springframework.stereotype.Repository;

/**
 *To check on the inheritance mechanism
 * 
 * 
 * @author jmaina
 */

@Repository("deathEventDAO")
public class HibernateDeathEventDAO extends BaseDAOImpl<DeathReport> implements DeathEventDAO
{
    
    /**
     * we need an indicator as to whether saving of the event was successful or not
     * @param deathEvent
     * @return 
     */

    @Override
    public boolean saveDeathEvent(DeathReport deathEvent) {
        
        boolean success = false;
        try{
            save(deathEvent);
            success = true;
        }
        catch (Exception ets)
        {
            /**
             * to log the reason for failure
             */
        }
        
        return success;     
    }

    @Override
    public boolean deleteDeathEvent(DeathReport deathEvent) {
        
        boolean success = false;
        try{
            remove(deathEvent);
            success = true;
        }
        catch (Exception ets)
        {
            /**
             * to log the reason for failure
             */
        }
        
        return success;
    }

    @Override
    public List<DeathReport> getDeathEvents() {
        return findAll();
    }

    
    /**
     * @param deathReport
     * @return 
     */
    @Override
    public DeathReport getDeathEvent(DeathReport deathReport) {
        
        Criteria eventCriteria = getSession().createCriteria(DeathReport.class).
                                               add(Restrictions.eq("deathReportId", deathReport.getId()));
        
        return (DeathReport) eventCriteria.list().get(0);
        
    }

    /**
     * this method retrieves all the reports entered by an individual reporter. identified by 
     * the reporter id. 
     * 
     * @param reporterId
     * @return 
     */
    @Override
    public List<DeathReport> getDeathEventsByReporter(int reporterId) {
        
        Criteria eventCriteria = getSession().createCriteria(DeathReport.class).
                                               add(Restrictions.eq("reporterId",reporterId ));
        
        List reporterEvents = eventCriteria.list();
        
        return reporterEvents;
        
        
    }
    
}
