/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.server.dao.hibernate;

import java.util.List;
import org.openxdata.server.admin.model.DeathReport;
import org.openxdata.server.dao.DeathEventDAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmaina
 */

@Repository("deathEventDAO")
public class HibernateDeathEventDAO extends BaseDAOImpl<DeathReport> implements DeathEventDAO
{
    
    

    public void saveDeathEvent(DeathReport deathEvent) {
        save(deathEvent);
    }

    public void deleteDeathEvent(DeathReport deathEvent) {
        remove(deathEvent);
    }

    public List<DeathReport> getDeathEvents() {
        return findAll();
    }

    /**
     * to be moved to the service layer
     * @return 
     */
    public boolean savedDeathEvents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
