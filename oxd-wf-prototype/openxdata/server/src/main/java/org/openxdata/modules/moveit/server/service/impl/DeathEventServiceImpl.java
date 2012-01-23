/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.service.impl;

import java.util.List;
import org.openxdata.modules.moveit.server.dao.DeathEventDAO;
import org.openxdata.modules.moveit.server.model.DeathReport;
import org.openxdata.modules.moveit.server.service.DeathEventService;
import org.openxdata.server.dao.hibernate.BaseDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jmaina
 */

@Transactional
@Service("deathEventService")
public class DeathEventServiceImpl  extends BaseDAOImpl<DeathReport> implements DeathEventService
{
    @Autowired
    private DeathEventDAO deathEventDAO;

    @Override
    public DeathReport getDeathEvent(DeathReport deathReport) {
        
        return deathEventDAO.getDeathEvent(deathReport);
    }

    /**
     * 
     * we need an indicator whether saving was successful or not
     * 
     * @param deathEvent
     * @return 
     */
    @Override
    public boolean saveDeathEvent(DeathReport deathEvent) {
        
        return deathEventDAO.saveDeathEvent(deathEvent);
    }

    @Override
    public boolean deleteDeathEvent(DeathReport deathEvent) {
        
        return deathEventDAO.deleteDeathEvent(deathEvent);
    }

    @Override
    public List<DeathReport> getAllDeathEvents() {
        
        return deathEventDAO.getAllDeathEvents();
    }

    @Override
    public DeathReport getDeathEvent(int deathReportId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DeathReport getDeathEventByEventId(String eventId) {
        DeathReport deathReport;
        deathReport = deathEventDAO.getDeathEventByEventId(eventId);
        
        return deathReport;
    }

    
    
}
