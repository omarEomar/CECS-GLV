package com.beshara.csc.gn.inf.business.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.sql.Timestamp;

import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.beshara.base.entity.BasicEntity;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

@Entity
@NamedQueries({
@NamedQuery(name = "InfOldDataAuditEntity.findAll", query = "select o from InfOldDataAuditEntity o"),
@NamedQuery(name = "InfOldDataAuditEntity.findNewId", query = "select MAX(o.serial) from InfOldDataAuditEntity o")
,
   @NamedQuery(name = "InfOldDataAuditEntity.searchByCode", query = "select o from InfOldDataAuditEntity o where o.serial=:code")

,
   @NamedQuery(name = "InfOldDataAuditEntity.searchByName", query = "select o from InfOldDataAuditEntity o where o.name like :name order by o.name")

})
@Table(name = "INF_EMP_OLD_DATA_AUDIT")
@IdClass(IInfOldDataAuditEntityKey.class)

public class InfOldDataAuditEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-5265398664739853615")
    private static final long serialVersionUID = 1L;
    @Column(name="REC_SERIAL", nullable=false)
private Long recSerial;
@Id
@Column(name="SERIAL", nullable=false)
private Long serial;
@Column(name="PROCESS_DATE", nullable=false)
private Timestamp processDate;
@Column(name="PROCESS_USER", nullable=false)
private String processUser;
@Column(name="EXCEPTION_TYPE", nullable=true)
private Long exceptionType;
@Column(name="EXCP_CODE", nullable=true)
private String excpCode;
@Column(name="SCREEN_NAME", nullable=true)
private String name ; 
@Column(name="SCREEN_TITLE", nullable=true)
private String screenTitle;
@Column(name="CIVIL_ID", nullable=false)
private Long civilId;
@Column(name="OLD_ORG_CAT_CODE", nullable=true)
private String oldOrgCatCode;
@Column(name="OLD_MINISTRY_CODE", nullable=true)
private String oldMinistryCode;
@Column(name="OLD_DEPT_CODE", nullable=true)
private String oldDeptCode;
@Column(name="OLD_JOB_CODE", nullable=true)
private String oldJobCode;
@Column(name="OLD_FROM_DATE", nullable=true)
private Timestamp oldFromDate;
@Column(name="OLD_TO_DATE", nullable=true)
private Timestamp oldToDate;
@Column(name="OLD_AUDITED", nullable=true)
private String oldAudited;
@Column(name="OLD_PER_FLAG", nullable=true)
private String oldPerFlag;
@Column(name="NEW_ORG_CAT_CODE", nullable=true)
private String newOrgCatCode;
@Column(name="NEW_MINISTRY_CODE", nullable=true)
private String newMinistryCode;
@Column(name="NEW_DEPT_CODE", nullable=true)
private String newDeptCode;
@Column(name="NEW_JOB_CODE", nullable=true)
private String newJobCode;
@Column(name="NEW_FROM_DATE", nullable=true)
private Timestamp newFromDate;
@Column(name="NEW_TO_DATE", nullable=true)
private Timestamp newToDate;
@Column(name="NEW_AUDITED", nullable=true)
private String newAudited;
@Column(name="NEW_PER_FLAG", nullable=true)
private String newPerFlag;
@Column(name="MODIFY_EXCEPTION_TYPE", nullable=true)
private Long modifyExceptionType;
@Column(name="MODIFY_EXCP_CODE", nullable=true)
private String modifyExcpCode;
 

    /**
     * InfOldDataAuditEntity Default Constructor
     */    
    public InfOldDataAuditEntity() {
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
