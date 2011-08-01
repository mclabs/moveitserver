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
 * 
 * to be altered so that DeathhReport becomes of type Event.
 * Event becomes the superclass. 
 * 
 */
public class DeathReport implements Serializable {
    
    /** this is the id birthReport entry */ 
    private int deathReportId;
    
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
    
    /** this represents the event whether its a birth or death*/
    private String eventType;
    
    /** this represents identification of the person concerned with the event */
    private String eventName;
    
    /** this represents the contact number for the bereaved */
    private String contactPhone;
    
    /**this represents the village where the event occured */
    private String village;

    
    
    public void setDeathReportId(int deathReportId) {
        this.deathReportId = deathReportId;
    }

    public int getDeathReportId() {
        return deathReportId;
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
        return deathReportId;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() {
        return contactPhone;
    }
    
    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }
    
     
}
