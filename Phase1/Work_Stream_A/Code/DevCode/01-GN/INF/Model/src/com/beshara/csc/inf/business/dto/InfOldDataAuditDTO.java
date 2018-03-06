package com.beshara.csc.gn.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.dto.InfDTO;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
 
public class InfOldDataAuditDTO extends InfDTO implements IInfOldDataAuditDTO{

    @SuppressWarnings("compatibility:8705909730215068651")
    private static final long serialVersionUID = 1L;
    private Long recSerial;
private Long serial;
private Timestamp processDate;
private String processUser;
private Long exceptionType;
private String excpCode;
private String name ; 
private String screenTitle;
private Long civilId;
private String oldOrgCatCode;
private String oldMinistryCode;
private String oldDeptCode;
private String oldJobCode;
private Timestamp oldFromDate;
private Timestamp oldToDate;
private String oldAudited;
private String oldPerFlag;
private String newOrgCatCode;
private String newMinistryCode;
private String newDeptCode;
private String newJobCode;
private Timestamp newFromDate;
private Timestamp newToDate;
private String newAudited;
private String newPerFlag;
private Long modifyExceptionType;
private String modifyExcpCode;


    /**
     * InfOldDataAuditDTO Default Constructor
     */
    public InfOldDataAuditDTO() {
        super();
    }



/**
* @return Long
*/
public Long getRecSerial() {
   return recSerial;
}
/**
* @return Long
*/
public Long getSerial() {
   return serial;
}
/**
* @return Timestamp
*/
public Timestamp getProcessDate() {
   return processDate;
}
/**
* @return String
*/
public String getProcessUser() {
   return processUser;
}
/**
* @return Long
*/
public Long getExceptionType() {
   return exceptionType;
}
/**
* @return String
*/
public String getExcpCode() {
   return excpCode;
}
/**
* @return String 
*/
public String getName(){
   return name ;
}
/**
* @return String
*/
public String getScreenTitle() {
   return screenTitle;
}
/**
* @return Long
*/
public Long getCivilId() {
   return civilId;
}
/**
* @return String
*/
public String getOldOrgCatCode() {
   return oldOrgCatCode;
}
/**
* @return String
*/
public String getOldMinistryCode() {
   return oldMinistryCode;
}
/**
* @return String
*/
public String getOldDeptCode() {
   return oldDeptCode;
}
/**
* @return String
*/
public String getOldJobCode() {
   return oldJobCode;
}
/**
* @return Timestamp
*/
public Timestamp getOldFromDate() {
   return oldFromDate;
}
/**
* @return Timestamp
*/
public Timestamp getOldToDate() {
   return oldToDate;
}
/**
* @return String
*/
public String getOldAudited() {
   return oldAudited;
}
/**
* @return String
*/
public String getOldPerFlag() {
   return oldPerFlag;
}
/**
* @return String
*/
public String getNewOrgCatCode() {
   return newOrgCatCode;
}
/**
* @return String
*/
public String getNewMinistryCode() {
   return newMinistryCode;
}
/**
* @return String
*/
public String getNewDeptCode() {
   return newDeptCode;
}
/**
* @return String
*/
public String getNewJobCode() {
   return newJobCode;
}
/**
* @return Timestamp
*/
public Timestamp getNewFromDate() {
   return newFromDate;
}
/**
* @return Timestamp
*/
public Timestamp getNewToDate() {
   return newToDate;
}
/**
* @return String
*/
public String getNewAudited() {
   return newAudited;
}
/**
* @return String
*/
public String getNewPerFlag() {
   return newPerFlag;
}
/**
* @return Long
*/
public Long getModifyExceptionType() {
   return modifyExceptionType;
}
/**
* @return String
*/
public String getModifyExcpCode() {
   return modifyExcpCode;
}

    
/**
* @param recSerial
*/
public void setRecSerial(Long recSerial) {
   this.recSerial=recSerial;
}
/**
* @param serial
*/
public void setSerial(Long serial) {
   this.serial=serial;
}
/**
* @param processDate
*/
public void setProcessDate(Timestamp processDate) {
   this.processDate=processDate;
}
/**
* @param processUser
*/
public void setProcessUser(String processUser) {
   this.processUser=processUser;
}
/**
* @param exceptionType
*/
public void setExceptionType(Long exceptionType) {
   this.exceptionType=exceptionType;
}
/**
* @param excpCode
*/
public void setExcpCode(String excpCode) {
   this.excpCode=excpCode;
}
/**
* @param name
*/
public void setName( String name ){
   this.name = name;
}
/**
* @param screenTitle
*/
public void setScreenTitle(String screenTitle) {
   this.screenTitle=screenTitle;
}
/**
* @param civilId
*/
public void setCivilId(Long civilId) {
   this.civilId=civilId;
}
/**
* @param oldOrgCatCode
*/
public void setOldOrgCatCode(String oldOrgCatCode) {
   this.oldOrgCatCode=oldOrgCatCode;
}
/**
* @param oldMinistryCode
*/
public void setOldMinistryCode(String oldMinistryCode) {
   this.oldMinistryCode=oldMinistryCode;
}
/**
* @param oldDeptCode
*/
public void setOldDeptCode(String oldDeptCode) {
   this.oldDeptCode=oldDeptCode;
}
/**
* @param oldJobCode
*/
public void setOldJobCode(String oldJobCode) {
   this.oldJobCode=oldJobCode;
}
/**
* @param oldFromDate
*/
public void setOldFromDate(Timestamp oldFromDate) {
   this.oldFromDate=oldFromDate;
}
/**
* @param oldToDate
*/
public void setOldToDate(Timestamp oldToDate) {
   this.oldToDate=oldToDate;
}
/**
* @param oldAudited
*/
public void setOldAudited(String oldAudited) {
   this.oldAudited=oldAudited;
}
/**
* @param oldPerFlag
*/
public void setOldPerFlag(String oldPerFlag) {
   this.oldPerFlag=oldPerFlag;
}
/**
* @param newOrgCatCode
*/
public void setNewOrgCatCode(String newOrgCatCode) {
   this.newOrgCatCode=newOrgCatCode;
}
/**
* @param newMinistryCode
*/
public void setNewMinistryCode(String newMinistryCode) {
   this.newMinistryCode=newMinistryCode;
}
/**
* @param newDeptCode
*/
public void setNewDeptCode(String newDeptCode) {
   this.newDeptCode=newDeptCode;
}
/**
* @param newJobCode
*/
public void setNewJobCode(String newJobCode) {
   this.newJobCode=newJobCode;
}
/**
* @param newFromDate
*/
public void setNewFromDate(Timestamp newFromDate) {
   this.newFromDate=newFromDate;
}
/**
* @param newToDate
*/
public void setNewToDate(Timestamp newToDate) {
   this.newToDate=newToDate;
}
/**
* @param newAudited
*/
public void setNewAudited(String newAudited) {
   this.newAudited=newAudited;
}
/**
* @param newPerFlag
*/
public void setNewPerFlag(String newPerFlag) {
   this.newPerFlag=newPerFlag;
}
/**
* @param modifyExceptionType
*/
public void setModifyExceptionType(Long modifyExceptionType) {
   this.modifyExceptionType=modifyExceptionType;
}
/**
* @param modifyExcpCode
*/
public void setModifyExcpCode(String modifyExcpCode) {
   this.modifyExcpCode=modifyExcpCode;
}

    
}

