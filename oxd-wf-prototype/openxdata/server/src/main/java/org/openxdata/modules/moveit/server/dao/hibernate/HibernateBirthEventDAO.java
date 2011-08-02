/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.dao.hibernate;

import com.trg.dao.hibernate.GenericDAOImpl;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openxdata.modules.moveit.server.dao.BirthEventDAO;
import org.openxdata.modules.moveit.server.model.BirthReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmaina
 */

@Repository("birthEventDAO")
public class HibernateBirthEventDAO extends GenericDAOImpl<BirthReport, Integer> implements BirthEventDAO
{
    
        @Autowired
        @Override
        public void setSessionFactory(SessionFactory sessionFactory)
        {
                super.setSessionFactory(sessionFactory);
        }

    @Override
    public boolean saveBirthEvent(BirthReport birthReport) {
       
        boolean success = false;
        
        try{
            save(birthReport);
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
    public boolean deleteBirthEvent(BirthReport birthReport) {
               
        boolean success = false;
        
        try{
            remove(birthReport);
            success = true;
        }
        catch (Exception ets)
        {
            /** 
             * to log reason for failure
             */
        }
        
        return success;       
    }

    @Override
    public List<BirthReport> getBirthEvents() {
       return findAll();
    }
   
   

    @Override
    public List<BirthReport> getDeathEventsByReporter(int reporterId) {
        
        Criteria birthCriteria = getSession().createCriteria(BirthReport.class).
                                                add(Restrictions.eq("reporterId",reporterId ));
        
        return birthCriteria.list();
    }

    @Override
    public BirthReport getBirthEvent(BirthReport birthReport) {
        
        Criteria birthCriteria = getSession().createCriteria(BirthReport.class).
                                                add(Restrictions.eq("birthReportId", birthReport.getBirthReportId()));
        
        return (BirthReport) birthCriteria.list().get(0);
    }

    
}
