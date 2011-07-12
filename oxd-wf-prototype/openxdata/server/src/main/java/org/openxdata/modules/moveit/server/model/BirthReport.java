/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jmaina
 */
public class BirthReport implements Serializable
{

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

    
    
    public void setBirthReportId(int birthReportId) {
        this.birthReportId = birthReportId;
    }
    
    public int getBirthReportId() {
        return birthReportId;
    }
        

    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }
    
    public Date getDateOfEvent() {
        return dateOfEvent;
    }   
    

    public void setDateOfReport(Date dateOfReport) {
        this.dateOfReport = dateOfReport;
    }
    
    public Date getDateOfReport() {
        return dateOfReport;
    }

    public void setDateTimeStamp(Date dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
    }
    
    public Date getDateTimeStamp() {
        return dateTimeStamp;
    }

    
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    
    public String getEventId() {
        return eventId;
    }

    
     public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    public String getEventName() {
        return eventName;
    }
  

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    public String getEventType() {
        return eventType;
    }

    
    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public int getReporterId() {
        return reporterId;
    }

    public int getId() {
        return birthReportId;
    }

    
    
}
