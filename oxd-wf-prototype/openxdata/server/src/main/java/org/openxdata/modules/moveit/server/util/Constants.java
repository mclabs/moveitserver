/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openxdata.modules.moveit.server.util;

/**
 *
 * @author gmimano
 * 
 */
public class Constants 
{
    
    public final static String EVENT_ID="event_id";
    //public final static String EVENT_TYPE="event_type";
    public final static String EVENT_NAME="event_name";
    public final static String EVENT_DATE= "event_date";
    public final static String EVENT_REPORT_DATE = "event_report_date";
    public final static String EVENT_REPORTER = "reporter_id";
    public final static String EVENT_CONTACT = "contact_phone";
    public final static String LOCATION = "loc";
    public final static String EVENT_PLACE = "place";
    public final static String NOTIFICATION_NO = "nid";
    public final static String SEX = "sex";
    public final static String DOB = "dob";
    
   
    
    /**
     * These are constants for status query service
     */
    
    public final static String COMPLETE = "C";
    public final static String INVALIE = "IN";
    public final static String NOT_STARTED = "NS";
    
    
    /**
     * 
     * These are the constants for creating 
     * 
     * 
     *     CHW FULL NAME
           CHW MOBILE NUMBER
           CHW MANAGER (When sending to ODX will send a CHEW/Manager mobile number as identifier )
           CHW LOCATION (array since CHW might be assigned different location)
     *     OXDHOST:PORT/moveit/?name=xcdv&tel=mobile&manager=manager_mobile&role=role_name
     * 
     */
    public final static String FULLNAME = "name";
    public final static String MOBILENUMBER = "tel";
    public final static String CHWMANAGERNAME = "managername";
    public final static String CHWMANAGERNUMBER = "managernumber";
    public final static String CHWLOCATION = "location";
    public final static String ROLE = "role";
            

}
