/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.servlet;

import com.google.inject.Singleton;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openxdata.modules.moveit.server.exceptions.CHWNotSavedException;
import org.openxdata.modules.moveit.server.exceptions.EventNotSavedException;
import org.openxdata.modules.moveit.server.exceptions.ParamNotSetException;
import org.openxdata.modules.moveit.server.model.CHWModel;

import org.openxdata.modules.moveit.server.service.CHWManagerService;
import org.openxdata.server.admin.model.User;
import org.openxdata.server.admin.model.exception.OpenXDataValidationException;

import org.openxdata.modules.moveit.server.util.Constants;
import org.openxdata.server.Context;

/**
 *
 * @author jmaina
 * 
 * 
 * when recieved the parameters will be passed to a CHW object
 * 
 * 
 * 
 */

@Singleton
public class CHWManagerServlet extends HttpServlet 
{
    
    /**
     * 
     * this model will also represent the database representation of the chw's. To 
     * be matched with all b&d events
     * 
     * 
     */
    
    //TODO 
    
    /**
     * data sent by CHW. code that loads this will have to be modified based on this model
     * as now information will be loaded principally based on the chws that fall under a 
     * specific manager. Tables for birthReport and deathReport will have to be modified to 
     * fit into this logic.
     * 
     */
    
    private User user;
    private CHWModel chw; //represents a one to many relationship with user;
    private Calendar calender; //date added to the system
    private CHWManagerService chwManagerService;
    
    
    @Override
    public void init() throws ServletException
    {
        super.init();
        //services to be loaded are userService;
        chwManagerService = (CHWManagerService) Context.getBean("chwManagerService");
        
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, 
                   IOException
    {
        try 
        {          
            processRequest(req, resp);        
        } 
        catch (CHWNotSavedException ex) {
            Logger.getLogger(CHWManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(OpenXDataValidationException oxdErr){
            Logger.getLogger(CHWManagerServlet.class.getName()).log(Level.SEVERE, null, oxdErr);
        }
        catch (Exception ex) {
                Logger.getLogger(CHWManagerServlet.class.getName()).log(Level.SEVERE, null, ex);    
        }
        
    }
    
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, 
                   IOException
    {
        
        try 
        {          
            processRequest(req, resp);        
        } 
        catch (CHWNotSavedException ex) {
            Logger.getLogger(CHWManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(OpenXDataValidationException oxdErr){
            Logger.getLogger(CHWManagerServlet.class.getName()).log(Level.SEVERE, null, oxdErr);
        }
        catch (Exception ex) {
                Logger.getLogger(CHWManagerServlet.class.getName()).log(Level.SEVERE, null, ex);    
        }
        
    }
     
     public void processRequest(HttpServletRequest req,HttpServletResponse resp) 
             throws EventNotSavedException,
                    IOException, 
                    OpenXDataValidationException, 
                    Exception
     {
         
         resp.setContentType("text/plain");
         PrintWriter out = resp.getWriter();
                 
         try
         {
             chw = new CHWModel();
             checkParams(req, chw);
         }
         catch (ParamNotSetException ex) {
            out.print("FAIL");
            Logger.getLogger(CHWModel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         chwManagerService.saveCHW(chw);
         
         
         
     }
     
     
     private void checkParams(HttpServletRequest req, CHWModel chw) 
             throws ParamNotSetException, 
                    ParseException
     {
          
        String tmpParam=null;
        
        calender = Calendar.getInstance();
        
        if((tmpParam=req.getParameter(Constants.FULLNAME))!=null){

            chw.setFullName(tmpParam);
            System.out.println(tmpParam);

            //reset tmpParam variable for next check
            tmpParam = null;
        }
        else
        {
            throw new ParamNotSetException(Constants.FULLNAME);
        }

        if((tmpParam=req.getParameter(Constants.MOBILENUMBER))!=null){
           chw.setMobileNumber(tmpParam);
            System.out.println(tmpParam);

            //reset tmpParam variable for next check
            tmpParam = null;
        }
        else
        {
            throw new ParamNotSetException(Constants.MOBILENUMBER);
        }

        if((tmpParam=req.getParameter(Constants.CHWMANAGERNAME))!=null)
        {
          
            chw.setChwManagerName(tmpParam);  
            System.out.println(tmpParam);
            tmpParam = null;
        }
        else
        {
            throw new ParamNotSetException(Constants.CHWMANAGERNAME);
        }
        
        if((tmpParam=req.getParameter(Constants.CHWMANAGERNUMBER))!=null)
        {
          
            chw.setChwManagerNumber(tmpParam);  
            System.out.println(tmpParam);
            tmpParam = null;
        }
        else
        {
            throw new ParamNotSetException(Constants.CHWMANAGERNUMBER);
        }

        
        if((tmpParam=req.getParameter(Constants.CHWLOCATION))!=null)
        {
            
            chw.setChwLocation(tmpParam);
            System.out.println(tmpParam);
            tmpParam = null;
        }
        else
        {
            throw new ParamNotSetException(Constants.CHWLOCATION);
        }
        
        if((tmpParam=req.getParameter(Constants.ROLE))!=null)
        {
            
            chw.setRole(tmpParam);
            System.out.println(tmpParam);
            tmpParam = null;
        }
        else
        {
            throw new ParamNotSetException(Constants.ROLE);
        }
        
        chw.setDate(calender.getTime());
         
     }
     
     
}
