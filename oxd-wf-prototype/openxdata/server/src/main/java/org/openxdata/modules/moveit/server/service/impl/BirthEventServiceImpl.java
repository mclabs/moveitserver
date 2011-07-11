/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.service.impl;

import java.util.List;
import org.openxdata.server.admin.model.BirthReport;
import org.openxdata.modules.moveit.server.service.BirthEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jmaina
 */

@Transactional
@Service("birthEventService")
public class BirthEventServiceImpl implements BirthEventService
{

    public BirthReport getBirthEvent(BirthReport birthReport) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List getAllBirthEvents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List findBirthEventsByReporter(int reporterId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isBirthEventSaved(BirthReport birthReport) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean saveBirthEvent(BirthReport birthReport) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean deleteBirthEvent(BirthReport birthReport) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List getBirthEvents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
