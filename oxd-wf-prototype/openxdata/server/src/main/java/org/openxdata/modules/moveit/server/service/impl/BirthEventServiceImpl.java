/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.service.impl;

import java.util.List;
import org.openxdata.modules.moveit.server.dao.BirthEventDAO;
import org.openxdata.modules.moveit.server.model.BirthReport;
import org.openxdata.modules.moveit.server.service.BirthEventService;
import org.openxdata.server.admin.model.Editable;
import org.openxdata.server.dao.hibernate.BaseDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jmaina
 */

@Transactional
@Service("birthEventService")
public class BirthEventServiceImpl extends BaseDAOImpl<Editable> implements BirthEventService
{
    
    @Autowired
    private BirthEventDAO birthEventDAO;


    @Override
    public boolean saveBirthEvent(BirthReport birthReport) {
        
        return birthEventDAO.saveBirthEvent(birthReport);
    }

    @Override
    public boolean deleteBirthEvent(BirthReport birthReport) {
        
        return birthEventDAO.deleteBirthEvent(birthReport);
    }


    @Override
    public List<BirthReport> getAllBirthEvents() {
        
        return birthEventDAO.getBirthEvents();
     
    }

    
    @Override
    public BirthReport getBirthEventById(int birthReport) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> getAllCompletedEvents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BirthReport getBirthEventByEventId(String eventId) {
        
        BirthReport birthReport;
        
        birthReport = birthEventDAO.getBirthEventByEventId(eventId);
        
        return birthReport;
    }

    @Override
    public List<BirthReport> getBirthEventByReporterId(Long reporterId) {
        
        List<BirthReport> birthReportList 
                = birthEventDAO.getBirthEventByReporterId(reporterId);
        
        return birthReportList;
    }

    @Override
    public List<BirthReport> getIncompletedEvents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    

}
