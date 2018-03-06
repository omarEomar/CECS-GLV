package com.beshara.csc.inf.business.integration.eservices.registration.dto;

import java.io.Serializable;

import java.util.Date;


public class UserDTOEservice implements Serializable{
    @SuppressWarnings("compatibility:8126578148685319369")
        
    private static final long serialVersionUID = 1L;
    private Long code;

       private String userName;

       private String password;

       private Date passwordExpiryDate;

       private Long locked;

       private String dbUser;

       private Long civilNo;

       private Date createdDate;

       private String createdBy; 

       private Long userDeleted;


    
    public UserDTOEservice() {
        super();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordExpiryDate(Date passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }

    public Date getPasswordExpiryDate() {
        return passwordExpiryDate;
    }

    public void setLocked(Long locked) {
        this.locked = locked;
    }

    public Long getLocked() {
        return locked;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setCivilNo(Long civilNo) {
        this.civilNo = civilNo;
    }

    public Long getCivilNo() {
        return civilNo;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }
 

    public void setUserDeleted(Long userDeleted) {
        this.userDeleted = userDeleted;
    }

    public Long getUserDeleted() {
        return userDeleted;
    }
}
