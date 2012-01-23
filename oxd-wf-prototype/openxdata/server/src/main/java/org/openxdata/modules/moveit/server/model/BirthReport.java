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
 * to be altered so that BirthReport becomes of type Event.
 * Event  becomes the superclass
 * 
 * 
 * 
 *  /**
     * 
     *  Reporter (identified by telephone no)  =>  reporter_id
        Event ID => event_id
        Date event reported => event_report_date
        Death of Birth => dob
        Contact => contact_phone
        Sex =>sex
        Location code =>  loc
        Full Name => event_name
        Place of event (Hospital/Clinic or Home) =>  place
        Notification no. ( Incase Place is hospital and there is D1 filled) => nid
     * 
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
    private Long reporterId;
    
    /**this represents the unique id of the particular event */
    private String eventId;
     
    /** this represents identification of the person concerned with the event */
    private String eventName;
    
    private String contactPhone;
    
    private String sex;
    
    /** location code whether its a location or a sublocation */
    private String location;
    
    /** Place of event (Hospital/Clinic or Home) =>  place */
    private String place_of_event;
    
    /** Notification no. ( Incase Place is hospital and there is D1 filled) => nid */
    private String notificationNumber;
    
    public String status; 
    
   
    
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
    
    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    public Long getReporterId() {
        return reporterId;
    }

    public int getId() {
        return birthReportId;
    }

     public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getLocation() {
        return location;
    }

    

    public String getPlace_of_event() {
        return place_of_event;
    }

    public String getSex() {
        return sex;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPlace_of_event(String place_of_event) {
        this.place_of_event = place_of_event;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }
    

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotificationNumber() {
        return notificationNumber;
    }

    public void setNotificationNumber(String notificationNumber) {
        this.notificationNumber = notificationNumber;
    }
    
    
     
    
}
