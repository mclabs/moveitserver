/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openxdata.modules.moveit.server.servlet;

import com.google.inject.Singleton;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openxdata.modules.moveit.handlers.EventXFormHandlers;
import org.openxdata.modules.moveit.server.exceptions.EventNotSavedException;
import org.openxdata.modules.moveit.server.exceptions.ParamNotSetException;
import org.openxdata.modules.moveit.server.model.BirthReport;
import org.openxdata.server.Context;
import org.openxdata.modules.moveit.server.service.BirthEventService;
import org.openxdata.modules.moveit.server.util.Constants;
import org.openxdata.server.admin.model.FormData;
import org.openxdata.server.admin.model.User;
import org.openxdata.server.service.AuthenticationService;
import org.openxdata.server.service.FormService;

/**
 *
 * @author gmimano
 */

@Singleton
public class BirthReportServlet extends HttpServlet{
    BirthEventService birthService;
    BirthReport birthReport;
    AuthenticationService authService;
    FormService formService;



    private Calendar calendar;

    @Override
    public void init() throws ServletException {
        super.init();
        birthService = (BirthEventService)Context.getBean("birthEventService");
        birthReport = new BirthReport();
        calendar = Calendar.getInstance();
        authService = (AuthenticationService)Context.getBean("authenticationService");
        formService = (FormService)Context.getBean("formService");
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        try {

            processRequest(req, resp);

        } catch (EventNotSavedException ex) {
            Logger.getLogger(BirthReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        try {
            processRequest(req, resp);
        } catch (EventNotSavedException ex) {
            Logger.getLogger(BirthReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    //TODO: need to figure out which request will not be handled get vs post
    
    /**
     * Process triggers to start of the information which will be 
     * sent to the mobile client
     * @param req
     * @param resp
     * @throws EventNotSavedException
     * @throws IOException 
     */
    void processRequest(HttpServletRequest req,HttpServletResponse resp) throws EventNotSavedException,IOException{
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();



        try {
            //changing some github code
            checkParams(req);
            //eventId = Integer.valueOf(getParam(""))
        } catch (ParamNotSetException ex) {
            out.print("FAIL");
            Logger.getLogger(BirthReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }



        if(!birthService.saveBirthEvent(birthReport)){
            throw new EventNotSavedException(birthReport.getEventId());
        }else{
            out.print("SUCCESS");
            EventXFormHandlers eventXFormHanlders = new EventXFormHandlers(birthReport);
            
            String xml = eventXFormHanlders.returnXFormRepresentation(birthReport);
            
            /** 
             * TODO: to be improved using yawl workflows
             * 
             * to save the xml represented as a string data to the database.
             * 
             * to save the xml represented as a string data to the database.
             * 
             *Setting up of the form object
             * 
             * FormData data = new FormData();
		data.setData(xml);
		data.setFormDefVersionId(formData.getDef().getId());
		setFormDataDescription(xml,data);
		data.setDateCreated(new Date());
		data.setCreator(userService.getLoggedInUser());
             * 
             * 
             */
            User user = null;
            user =authService.authenticate("admin", "admin");
            
            FormData data = new FormData();
            data.setFormDataId(7);
            data.setData(xml);
            data.setFormDefVersionId(1);
            data.setCreator(user);
            formService.saveFormData(data);
            
        }

    }

    //check params and set the event details
    private void checkParams(HttpServletRequest req) throws ParamNotSetException {
        String tmpParam=null;
        if((tmpParam=req.getParameter(Constants.EVENT_ID))!=null){

            birthReport.setEventId(tmpParam);

            //reset tmpParam variable for next check
            tmpParam = null;
        }else{
            throw new ParamNotSetException(Constants.EVENT_ID);
        }

        if((tmpParam=req.getParameter(Constants.EVENT_NAME))!=null){
            birthReport.setEventName(tmpParam);

            //reset tmpParam variable for next check
            tmpParam = null;
        }else{
            throw new ParamNotSetException(Constants.EVENT_NAME);
        }

        if((tmpParam=req.getParameter(Constants.EVENT_TYPE))!=null){
            birthReport.setEventType(tmpParam);

            //reset tmpParam variable for next check
            tmpParam = null;
        }else{
            throw new ParamNotSetException(Constants.EVENT_TYPE);
        }


        if((tmpParam=req.getParameter(Constants.EVENT_DATE))!=null){
            //TODO: change to chack date formart of incoming string
            long timestamp = Long.valueOf(tmpParam).longValue();
            birthReport.setDateOfEvent(new Date(timestamp));
            //reset tmpParam variable for next check
            tmpParam = null;
        }else{
            throw new ParamNotSetException(Constants.EVENT_DATE);
        }


        if((tmpParam=req.getParameter(Constants.EVENT_REPORTER))!=null){
            birthReport.setReporterId(Integer.valueOf(tmpParam));
            //reset tmpParam variable for next check
            tmpParam = null;
        }else{
            throw new ParamNotSetException(Constants.EVENT_REPORTER);
        }

        if((tmpParam=req.getParameter(Constants.EVENT_REPORT_DATE))!=null){
            long timestamp = Long.valueOf(tmpParam).longValue();
            birthReport.setDateOfReport(new Date(timestamp));
            //reset tmpParam variable for next check
            tmpParam = null;
        }else{
            throw new ParamNotSetException(Constants.EVENT_REPORT_DATE);
        }

        if((tmpParam=req.getParameter(Constants.EVENT_CONTACT))!=null){
            birthReport.setContactPhone(tmpParam);
            //reset tmpParam variable for next check
            tmpParam = null;
        }else{
            throw new ParamNotSetException(Constants.EVENT_CONTACT);
        }


        //set the date captured in oxd
        birthReport.setDateTimeStamp(calendar.getTime());



    }




}
