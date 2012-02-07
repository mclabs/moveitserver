/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openxdata.modules.moveit.server.model;

import java.io.Serializable;
import java.util.Date;
import org.openxdata.server.admin.model.User;

/**
 *
 * @author jmaina
 * 
 */

public class CHWModel implements Serializable
{
    
    /**
     * 
     * Role name = Assistant Chief,  CHW Manager, CHW, Registrar
     * 
     * //-> this is to be linked with the user of oxd
     * 
     * 
     */
    
    private int chwId;
    private String fullName; //-> full name of the chw
    private String mobileNumber; //-> mobile number of chw
    private String chwManagerName;  //-> name of the manager of chw
    private String chwManagerNumber; //->number of the manager of chw
    private String chwLocation; //-> location of operations for chw
    private String role; 
    private Date date; // -> date registered into the system
    
    private User user;
    
    

    public int getChwId() {
        return chwId;
    }

    public void setChwId(int chwId) {
        this.chwId = chwId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getChwLocation() {
        return chwLocation;
    }

    public void setChwLocation(String chwLocation) {
        this.chwLocation = chwLocation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getChwManagerName() {
        return chwManagerName;
    }

    public void setChwManagerName(String chwManagerName) {
        this.chwManagerName = chwManagerName;
    }

    public String getChwManagerNumber() {
        return chwManagerNumber;
    }

    public void setChwManagerNumber(String chwManagerNumber) {
        this.chwManagerNumber = chwManagerNumber;
    }

    
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
