package com.beshara.csc.inf.business.entity;

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
@NamedQuery(name = "InfTmpDataEntity.findAll", query = "select o from InfTmpDataEntity o"),
@NamedQuery(name = "InfTmpDataEntity.findNewId", query = "select MAX(o.datatypCode) from InfTmpDataEntity o")


})
@Table(name = "INF_TMP_DATA")
@IdClass(IInfTmpDataEntityKey.class)

public class InfTmpDataEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


@Id
@Column(name="DATATYP_CODE", nullable=false)
private Long datatypCode;
@Id
@Column(name="DISK_CODE", nullable=false)
private Long diskCode;
@Id
@Column(name="CIVIL_ID", nullable=false)
private Long civilId;
@Column(name="FIELD6", nullable=true)
private String field6;
@Column(name="FIELD7", nullable=true)
private String field7;
@Column(name="FIELD4", nullable=true)
private String field4;
@Column(name="FIELD5", nullable=true)
private String field5;
@Column(name="FIELD1", nullable=true)
private String field1;
@Column(name="FIELD3", nullable=true)
private String field3;
@Column(name="FIELD2", nullable=true)
private String field2;
@Column(name="FIELD8", nullable=true)
private String field8;
@Column(name="LOAD_STATUS", nullable=false)
private String loadStatus;
@Column(name="MIGRATION_STATUS", nullable=false)
private String migrationStatus;
@Column(name="DATATYP_CODE_MIGR", nullable=false)
private Long datatypCodeMigr;
@Column(name="CASE_CODE", nullable=false)
private Long caseCode;
//@ManyToOne 
//@JoinColumn(name="CIVIL_ID", referencedColumnName="CIVIL_ID")
//private InfKwtCitizensResidentsEntity infKwtCitizensResidentsEntity;
//@ManyToOne 
//@JoinColumn(name="DATATYP_CODE", referencedColumnName="DATATYP_CODE")
//private InfTmpDataDisksEntity infTmpDataDisksEntity;
//@ManyToOne 
//@JoinColumn(name="DISK_CODE", referencedColumnName="DISK_CODE")
//private InfTmpDataDisksEntity infTmpDataDisksEntity;
//@ManyToOne 
//@JoinColumn(name="CASE_CODE", referencedColumnName="CASE_CODE")
//private InfTmpMigrCasesEntity infTmpMigrCasesEntity;
//@ManyToOne 
//@JoinColumn(name="DATATYP_CODE_MIGR", referencedColumnName="DATATYP_CODE")
//private InfTmpMigrCasesEntity infTmpMigrCasesEntity;
 

    /**
     * InfTmpDataEntity Default Constructor
     */    
    public InfTmpDataEntity() {
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
