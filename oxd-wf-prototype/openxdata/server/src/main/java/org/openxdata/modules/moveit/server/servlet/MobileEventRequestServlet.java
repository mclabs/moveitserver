/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.servlet;

import com.google.inject.Singleton;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openxdata.server.Context;
import org.openxdata.server.admin.model.FormData;
import org.openxdata.server.admin.model.FormDef;
import org.openxdata.server.admin.model.FormDefVersion;
import org.openxdata.server.service.FormService;

/**
 *
 * @author jmaina
 * 
 * 
 * The purpose of this servlet is to send the FormData
 * object with all the prefilled data to the mobile client. 
 * 
 * When the mobile client requests for this it is given this 
 * information
 * 
 * 
 * TODO clean up code and make it well encapsulated.
 */

@Singleton
public class MobileEventRequestServlet extends HttpServlet
{
    
    FormService formService;
    
    @Override
    public void init() throws ServletException
    {
        formService = (FormService)Context.getBean("formService");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        
        doPost(req, resp);  
    }
       

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
               
        List<FormDef> listedData = formService.getFormsForCurrentUser();
        
        /**
         * the tricky part is how to link this with the mobile service
         * so that they download the right forms to the right user in 
         * this case being the chief.
         */
        
        DataOutputStream out = new DataOutputStream(resp.getOutputStream());
        
        //p.print(p); /** the object to be sent being formdata */
        //p.close();    
        
   
        for (FormDef formDef : listedData){            
            if (formDef != null )
            {
                  /**
                     * this is for deaths
                     */
                    List<FormDefVersion> versions = formDef. getVersions();
                    FormDefVersion vrs = versions.get(0);
                    vrs.getId();
                    
                   List <FormData> forms= formService.getFormData(vrs.getId());
                   for (FormData formData: forms){
                       
                       if (formData.getFormDataId() == 5)
                       {
                            out.write(formData.getFormDataId());
                            out.writeUTF(formData.getData()); 
                            out.write(formData.getFormDefVersionId());
                            //out.writeBytes(formData.getCreator()); /** mechanism to write an object to an output stream */
                       }
                       
                       else if (formData.getFormDataId() == 7 )
                       {                           
                           out.write(formData.getFormDataId());
                           out.writeUTF(formData.getData()); 
                           out.write(formData.getFormDefVersionId());
                           //out.writeBytes(formData.getCreator()); /** mechanism to write an object to an output stream */
                       }
                   }
            }
       
        }           
                      
        out.flush();
        
   
    }  
    
    
}
