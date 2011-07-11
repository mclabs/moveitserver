/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openxdata.modules.workflows.server.servlet;

import com.google.inject.Singleton;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openxdata.server.Context;
import org.openxdata.server.admin.model.BirthReport;
import org.openxdata.modules.moveit.server.service.BirthEventService;

/**
 *
 * @author gmimano
 */

@Singleton
public class BirthReportServlet extends HttpServlet{
    BirthEventService birthService;
    BirthReport birthReport;

    /** this is the id birthReport entry */
    private int birthReportId;

    /** this represents the date and time when it was reported */
    private Date dateOfReport;

    /** this represents the date and time of birth */
    private Date dateOfEvent;

    /**this represents the time stamp when it was captured in oxd*/
    private Date dateTimeStamp;

    /**this represents the reporter (from RapidSMS). It is usually the phone number  */
    private int reporterId;

    /**this represents the unique id of the particular event */
    private String eventId;

    /** this represents the event whether its a birth */
    private String eventType;

    /** this represents identification of the person concerned with the event */
    private String eventName;

    @Override
    public void init() throws ServletException {
        super.init();
        birthService = (BirthEventService)Context.getBean("birthEventService");
        birthReport = new BirthReport();
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


    //TODO: need to figure out which request will not be handled get vs post
    void processRequest(HttpServletRequest req,HttpServletResponse resp){
        //changing some github code
        //eventId = Integer.valueOf(getParam(""))

    }




}
