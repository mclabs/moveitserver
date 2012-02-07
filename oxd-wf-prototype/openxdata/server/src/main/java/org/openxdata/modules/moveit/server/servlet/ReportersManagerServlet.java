/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.servlet;


import com.google.inject.Singleton;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openxdata.modules.moveit.server.exceptions.ParamNotSetException;
import org.openxdata.modules.moveit.server.model.UserReporters;
import org.openxdata.modules.moveit.server.service.UserEventReporterService;
import org.openxdata.server.Context;


/**
 *
 * @author jmaina
 * 
 * I will have to recieve the list of users and create them then I will need to get the list
 * of reporters so that they can be mapped to the users.
 * 
 */

@Singleton
public class ReportersManagerServlet extends HttpServlet {
    
    private UserReporters userReportersObj;
    private Calendar calendar; 
    private UserEventReporterService userReporterService;
    
    @Override
    public void init() throws ServletException {
        
        userReporterService = (UserEventReporterService) Context.getBean("userReporterService");
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        try {
            processRequest(req, resp);
        } catch (ParamNotSetException ex) {
            Logger.getLogger(ReportersManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        try {
            processRequest(req, resp);
        } catch (ParamNotSetException ex) {
            Logger.getLogger(ReportersManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) 
            throws ParamNotSetException {
           
        userReportersObj = new UserReporters();

        checkParameters(req, userReportersObj);
        
        
        userReporterService.saveReportersByUser(userReportersObj);
    }

    private void checkParameters(HttpServletRequest req, UserReporters userReportersObj) 
            throws ParamNotSetException {
        
    }
    
}
