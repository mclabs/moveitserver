/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.service.impl;

import java.util.List;
import org.openxdata.server.admin.model.DeathReport;
import org.openxdata.server.service.DeathEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jmaina
 */

@Transactional
@Service("deathEventService")
public class DeathEventServiceImpl implements DeathEventService
{

    public DeathReport getDeathEvent(DeathReport deathReport) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<DeathReport> getAllDeathReports() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<DeathReport> getDeathEventsByReporter(int reporterId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean saveDeathEvent(DeathReport deathEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean deleteDeathEvent(DeathReport deathEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<DeathReport> getDeathEvents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isSavedDeathEvents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
