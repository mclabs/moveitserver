/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.server.service;

import java.util.List;
import org.openxdata.server.admin.model.BirthReport;

/**
 *
 * @author jmaina
 */
public interface BirthEventService 
{
   
    /**
     * getting a specific Birth record
     * 
     * @param birthReport 
     */
    BirthReport getBirthEvent(BirthReport birthReport);
    
    
    /**
     * Save an instance of a birth event 
     * @param birthReport 
     */
    
    boolean saveBirthEvent(BirthReport birthReport);
     
     
     /**
      * Delete an instance of a birth event
      * @param birthReport 
      */
    
    boolean deleteBirthEvent(BirthReport birthReport);
     
    
}
