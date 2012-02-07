/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.exceptions;

/**
 *
 * @author jmaina
 */
public class CHWNotSavedException extends Exception
{
    
    public CHWNotSavedException(String message)
    {
        super(message + "The CHW not created"); 
    }
    
    public CHWNotSavedException()
    {
        super("CHW not created");
    }
    
}
