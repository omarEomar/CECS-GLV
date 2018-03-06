package com.beshara.csc.inf.business.dto;


import java.util.List;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
public interface IInfTmpDataDTO extends IInfDTO {

    /**
* @return Long
*/
Long getDatatypCode() ;
/**
* @return Long
*/
Long getDiskCode() ;
/**
* @return Long
*/
Long getCivilId() ;
/**
* @return String
*/
String getField6() ;
/**
* @return String
*/
String getField7() ;
/**
* @return String
*/
String getField4() ;
/**
* @return String
*/
String getField5() ;
/**
* @return String
*/
String getField1() ;
/**
* @return String
*/
String getField3() ;
/**
* @return String
*/
String getField2() ;
/**
* @return String
*/
String getField8() ;
/**
* @return String
*/
String getLoadStatus() ;
/**
* @return String
*/
String getMigrationStatus() ;
/**
* @return Long
*/
Long getDatatypCodeMigr() ;
/**
* @return Long
*/
Long getCaseCode() ;

    
    /**
* @param datatypCode
*/
public void setDatatypCode(Long datatypCode) ;
/**
* @param diskCode
*/
public void setDiskCode(Long diskCode) ;
/**
* @param civilId
*/
public void setCivilId(Long civilId) ;
/**
* @param field6
*/
public void setField6(String field6) ;
/**
* @param field7
*/
public void setField7(String field7) ;
/**
* @param field4
*/
public void setField4(String field4) ;
/**
* @param field5
*/
public void setField5(String field5) ;
/**
* @param field1
*/
public void setField1(String field1) ;
/**
* @param field3
*/
public void setField3(String field3) ;
/**
* @param field2
*/
public void setField2(String field2) ;
/**
* @param field8
*/
public void setField8(String field8) ;
/**
* @param loadStatus
*/
public void setLoadStatus(String loadStatus) ;
/**
* @param migrationStatus
*/
public void setMigrationStatus(String migrationStatus) ;
/**
* @param datatypCodeMigr
*/
public void setDatatypCodeMigr(Long datatypCodeMigr) ;
/**
* @param caseCode
*/
public void setCaseCode(Long caseCode) ;

    
}
