package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.InfTmpDataEntity;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
 
public class InfTmpDataDTO extends InfDTO implements IInfTmpDataDTO{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    private Long datatypCode;
private Long diskCode;
private Long civilId;
private String field6;
private String field7;
private String field4;
private String field5;
private String field1;
private String field3;
private String field2;
private String field8;
private String loadStatus;
private String migrationStatus;
private Long datatypCodeMigr;
private Long caseCode;


    /**
     * InfTmpDataDTO Default Constructor
     */
    public InfTmpDataDTO() {
        super();
    }



/**
* @return Long
*/
public Long getDatatypCode() {
   return datatypCode;
}
/**
* @return Long
*/
public Long getDiskCode() {
   return diskCode;
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
public String getField6() {
   return field6;
}
/**
* @return String
*/
public String getField7() {
   return field7;
}
/**
* @return String
*/
public String getField4() {
   return field4;
}
/**
* @return String
*/
public String getField5() {
   return field5;
}
/**
* @return String
*/
public String getField1() {
   return field1;
}
/**
* @return String
*/
public String getField3() {
   return field3;
}
/**
* @return String
*/
public String getField2() {
   return field2;
}
/**
* @return String
*/
public String getField8() {
   return field8;
}
/**
* @return String
*/
public String getLoadStatus() {
   return loadStatus;
}
/**
* @return String
*/
public String getMigrationStatus() {
   return migrationStatus;
}
/**
* @return Long
*/
public Long getDatatypCodeMigr() {
   return datatypCodeMigr;
}
/**
* @return Long
*/
public Long getCaseCode() {
   return caseCode;
}

    
/**
* @param datatypCode
*/
public void setDatatypCode(Long datatypCode) {
   this.datatypCode=datatypCode;
}
/**
* @param diskCode
*/
public void setDiskCode(Long diskCode) {
   this.diskCode=diskCode;
}
/**
* @param civilId
*/
public void setCivilId(Long civilId) {
   this.civilId=civilId;
}
/**
* @param field6
*/
public void setField6(String field6) {
   this.field6=field6;
}
/**
* @param field7
*/
public void setField7(String field7) {
   this.field7=field7;
}
/**
* @param field4
*/
public void setField4(String field4) {
   this.field4=field4;
}
/**
* @param field5
*/
public void setField5(String field5) {
   this.field5=field5;
}
/**
* @param field1
*/
public void setField1(String field1) {
   this.field1=field1;
}
/**
* @param field3
*/
public void setField3(String field3) {
   this.field3=field3;
}
/**
* @param field2
*/
public void setField2(String field2) {
   this.field2=field2;
}
/**
* @param field8
*/
public void setField8(String field8) {
   this.field8=field8;
}
/**
* @param loadStatus
*/
public void setLoadStatus(String loadStatus) {
   this.loadStatus=loadStatus;
}
/**
* @param migrationStatus
*/
public void setMigrationStatus(String migrationStatus) {
   this.migrationStatus=migrationStatus;
}
/**
* @param datatypCodeMigr
*/
public void setDatatypCodeMigr(Long datatypCodeMigr) {
   this.datatypCodeMigr=datatypCodeMigr;
}
/**
* @param caseCode
*/
public void setCaseCode(Long caseCode) {
   this.caseCode=caseCode;
}

    
}

