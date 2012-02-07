/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.dao.hibernate;

import com.trg.dao.hibernate.GenericDAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.openxdata.modules.moveit.server.dao.CHWManagerDAO;
import org.openxdata.modules.moveit.server.model.CHWModel;

import org.openxdata.server.admin.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmaina
 */

@Repository("chwManagerDAO")
public class HibernateCHWManagerDAO 
       extends GenericDAOImpl<CHWModel, Integer> 
       implements CHWManagerDAO
{
    
    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory)
    {
                super.setSessionFactory(sessionFactory);
    }
   
    
    

    @Override
    public List<CHWModel> getAllCHWByManager(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User getManagerByCHW(CHWModel chwModel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveCHW(CHWModel chwModel) 
    {
        
            Session session = getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            tx.begin();
            session.saveOrUpdate(chwModel);
            tx.commit(); 
    }

    @Override
    public void deleteCHW(CHWModel chwModel) {
        
        remove(chwModel);
    }

    @Override
    public CHWModel retrieveCHW(CHWModel chwModel) {
        
        Criteria chwCriteria = getSession().
                                    createCriteria(CHWModel.class).
                                          add(Restrictions.eq("chwId",chwModel.getChwId()));
        
        return (CHWModel) chwCriteria.list().get(0);
        
    }

    @Override
    public CHWModel retrieveCHW(String phoneNumber) {
        
        Criteria chwCriteria = getSession().
                                    createCriteria(CHWModel.class).
                                          add(Restrictions.eq("mobileNumber",phoneNumber));
        
        return (CHWModel) chwCriteria.list().get(0);
    }
    
}
