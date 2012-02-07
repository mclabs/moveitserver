/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.dao;

import com.trg.dao.hibernate.GenericDAO;

import java.util.List;

import org.openxdata.modules.moveit.server.model.CHWModel;
import org.openxdata.server.admin.model.User;


/**
 *
 * @author jmaina
 */
public interface CHWManagerDAO extends GenericDAO<CHWModel, Integer> {
    
    public List<CHWModel> getAllCHWByManager(User user);
    
    
    public User getManagerByCHW(CHWModel chwModel);
    
    
    public void saveCHW(CHWModel chwModel);
    
    public void deleteCHW(CHWModel chwModel);
    
    
    public CHWModel retrieveCHW(CHWModel chwModel);
       
    public CHWModel retrieveCHW(String phoneNumber);
    
}
