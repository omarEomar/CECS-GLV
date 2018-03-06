package com.beshara.csc.gn.inf.business.dto;


import com.beshara.csc.inf.business.dto.IInfDTO;

import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
public interface IInfOldDataAuditDTO extends IInfDTO {

    /**
* @return Long
*/
Long getRecSerial() ;
/**
* @return Long
*/
Long getSerial() ;
/**
* @return Timestamp
*/
Timestamp getProcessDate() ;
/**
* @return String
*/
String getProcessUser() ;
/**
* @return Long
*/
Long getExceptionType() ;
/**
* @return String
*/
String getExcpCode() ;
/**
* @return String
*/
String getName();
/**
* @return String
*/
String getScreenTitle() ;
/**
* @return Long
*/
Long getCivilId() ;
/**
* @return String
*/
String getOldOrgCatCode() ;
/**
* @return String
*/
String getOldMinistryCode() ;
/**
* @return String
*/
String getOldDeptCode() ;
/**
* @return String
*/
String getOldJobCode() ;
/**
* @return Timestamp
*/
Timestamp getOldFromDate() ;
/**
* @return Timestamp
*/
Timestamp getOldToDate() ;
/**
* @return String
*/
String getOldAudited() ;
/**
* @return String
*/
String getOldPerFlag() ;
/**
* @return String
*/
String getNewOrgCatCode() ;
/**
* @return String
*/
String getNewMinistryCode() ;
/**
* @return String
*/
String getNewDeptCode() ;
/**
* @return String
*/
String getNewJobCode() ;
/**
* @return Timestamp
*/
Timestamp getNewFromDate() ;
/**
* @return Timestamp
*/
Timestamp getNewToDate() ;
/**
* @return String
*/
String getNewAudited() ;
/**
* @return String
*/
String getNewPerFlag() ;
/**
* @return Long
*/
Long getModifyExceptionType() ;
/**
* @return String
*/
String getModifyExcpCode() ;

    
    /**
* @param recSerial
*/
public void setRecSerial(Long recSerial) ;
/**
* @param serial
*/
public void setSerial(Long serial) ;
/**
* @param processDate
*/
public void setProcessDate(Timestamp processDate) ;
/**
* @param processUser
*/
public void setProcessUser(String processUser) ;
/**
* @param exceptionType
*/
public void setExceptionType(Long exceptionType) ;
/**
* @param excpCode
*/
public void setExcpCode(String excpCode) ;
/**
* @param name
*/
public void setName( String name );
/**
* @param screenTitle
*/
public void setScreenTitle(String screenTitle) ;
/**
* @param civilId
*/
public void setCivilId(Long civilId) ;
/**
* @param oldOrgCatCode
*/
public void setOldOrgCatCode(String oldOrgCatCode) ;
/**
* @param oldMinistryCode
*/
public void setOldMinistryCode(String oldMinistryCode) ;
/**
* @param oldDeptCode
*/
public void setOldDeptCode(String oldDeptCode) ;
/**
* @param oldJobCode
*/
public void setOldJobCode(String oldJobCode) ;
/**
* @param oldFromDate
*/
public void setOldFromDate(Timestamp oldFromDate) ;
/**
* @param oldToDate
*/
public void setOldToDate(Timestamp oldToDate) ;
/**
* @param oldAudited
*/
public void setOldAudited(String oldAudited) ;
/**
* @param oldPerFlag
*/
public void setOldPerFlag(String oldPerFlag) ;
/**
* @param newOrgCatCode
*/
public void setNewOrgCatCode(String newOrgCatCode) ;
/**
* @param newMinistryCode
*/
public void setNewMinistryCode(String newMinistryCode) ;
/**
* @param newDeptCode
*/
public void setNewDeptCode(String newDeptCode) ;
/**
* @param newJobCode
*/
public void setNewJobCode(String newJobCode) ;
/**
* @param newFromDate
*/
public void setNewFromDate(Timestamp newFromDate) ;
/**
* @param newToDate
*/
public void setNewToDate(Timestamp newToDate) ;
/**
* @param newAudited
*/
public void setNewAudited(String newAudited) ;
/**
* @param newPerFlag
*/
public void setNewPerFlag(String newPerFlag) ;
/**
* @param modifyExceptionType
*/
public void setModifyExceptionType(Long modifyExceptionType) ;
/**
* @param modifyExcpCode
*/
public void setModifyExcpCode(String modifyExcpCode) ;

    
}
