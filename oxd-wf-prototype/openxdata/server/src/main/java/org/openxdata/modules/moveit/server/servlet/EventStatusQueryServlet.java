/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openxdata.modules.moveit.server.servlet;

import com.google.inject.Singleton;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openxdata.modules.moveit.server.model.DeathReport;
import org.openxdata.modules.moveit.server.service.BirthEventService;
import org.openxdata.modules.moveit.server.service.DeathEventService;
import org.openxdata.modules.moveit.server.service.EventStatusService;
import org.openxdata.server.Context;
import org.openxdata.server.service.AuthenticationService;
import org.openxdata.server.service.UserService;

/**
 *
 * @author gmimano
 * 
 * 
 */
@Singleton
public class EventStatusQueryServlet extends HttpServlet{
    
    DeathReport deathReport;
    DeathEventService deathService;
    BirthEventService birthEventService;
    AuthenticationService authService;
    Calendar calendar;
    UserService userService;
    EventStatusService eventStatusService;
    
    @Override
    public void init() throws ServletException {
        deathService = (DeathEventService)Context.getBean("deathEventService");
        authService = (AuthenticationService)Context.getBean("authenticationService");
        eventStatusService = (EventStatusService) Context.getBean("eventStatusService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    //TODO: need to figure out which request will not be handled get vs post
    void processRequest(HttpServletRequest req,HttpServletResponse resp){
        
        String message;
        
        String event_id = req.getParameter("event");
        int id = Integer.parseInt(event_id);
        
        String eventStatus = eventStatusService.getEventStatus(id);
        
        if ("Complete".equalsIgnoreCase(eventStatus)){
            message = "C";
        }
        
        if ("Incomplete".equalsIgnoreCase(eventStatus))
        {
            message = "NS";
        }
        
        else
        {
            message = "IN";
        }
        
        resp.setContentType("text/plain");
        resp.setContentLength(message.length());
        
        try {
            
            PrintWriter statusWriter = resp.getWriter();
            statusWriter.println(message);
            statusWriter.close();
            statusWriter.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(EventStatusQueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }

}
