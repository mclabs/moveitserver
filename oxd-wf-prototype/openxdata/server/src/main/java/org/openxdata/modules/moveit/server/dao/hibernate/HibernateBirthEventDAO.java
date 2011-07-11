/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.dao.hibernate;

import java.util.List;
import org.openxdata.modules.moveit.server.dao.BirthEventDAO;
import org.openxdata.server.admin.model.BirthReport;
import org.openxdata.server.dao.hibernate.BaseDAOImpl;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmaina
 */

@Repository("birthEventDAO")
public class HibernateBirthEventDAO extends BaseDAOImpl implements BirthEventDAO
{

    public void saveBirthEvent(BirthReport birthReport) {
        save(birthReport);
    }

    public void deleteBirthEvent(BirthReport birthReport) {
        remove(birthReport);
    }

    public List getBirthEvents() {
        return findAll();
    }

    /**
     * to be implemented in the service layer
     * @return 
     */
    public boolean savedBirthEvents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
