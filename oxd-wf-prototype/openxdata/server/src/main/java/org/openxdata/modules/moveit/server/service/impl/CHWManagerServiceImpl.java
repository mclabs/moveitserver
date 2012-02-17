/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.service.impl;

import java.util.List;

import org.openxdata.modules.moveit.server.dao.CHWManagerDAO;
import org.openxdata.modules.moveit.server.model.CHWModel;
import org.openxdata.modules.moveit.server.service.CHWManagerService;

import org.openxdata.server.admin.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jmaina
 */

@Transactional
@Service("chwManagerService")
public class CHWManagerServiceImpl implements CHWManagerService
{
    
    @Autowired
    private CHWManagerDAO chwManagerDao;

    @Override
    public List<CHWModel> getAllCHWByManager(User user) {
        
        List<CHWModel> allCHWList = chwManagerDao.getAllCHWByManager(user);
        
        return allCHWList;
    }

    @Override
    public User getManagerByCHW(CHWModel chwModel) {
        
        User user = chwManagerDao.getManagerByCHW(chwModel);
        
        return user;
    }
    
    

    @Override
    public void saveCHW(CHWModel chwModel) {
        
        chwManagerDao.save(chwModel);
    }

    @Override
    public void deleteCHW(CHWModel chwModel) {
        
        chwManagerDao.deleteCHW(chwModel);
    }
    
    

    @Override
    public CHWModel retrieveCHW(CHWModel chwModel) {
        
        CHWModel chw = chwManagerDao.retrieveCHW(chwModel);
        
        return chw;
        
    }

    @Override
    public CHWModel retrieveCHW(String phoneNumber) {
        
        CHWModel chw = chwManagerDao.retrieveCHW(phoneNumber);
        
        return chw;
    }

    @Override
    public List<CHWModel> retrieveCHWByManagerNumber(String managerNumber) {
       
      
        List<CHWModel> chwsUnderMAnager 
                = chwManagerDao.retrieveCHWByManagerNumber(managerNumber);
        
        return chwsUnderMAnager;
    }
    
}
