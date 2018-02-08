package com.beshara.csc.gn.map.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.gn.map.business.entity.MapDataExceptionsEntity;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers .
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Taha Fitiany    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated 
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *     
 * @author       Beshara Group   
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0   
 * @since        03/09/2007   
*/
public class MapDataExceptionsDTO extends BasicDTO implements IMapDataExceptionDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
private Long objtypeCode;
private Long soc1Code;
private Long soc2Code;
private String sqlStatement;
private Long createdBy;
private Timestamp createdDate;
private Long lastUpdatedBy;
private Timestamp lastUpdatedDate;
private Long auditStatus;
private Long tabrecSerial;


    /**
     * MapDataExceptionsDTO Default Constructor
     */
    public MapDataExceptionsDTO() {
        super();
    }

    /**
     * @param mapDataExceptionsEntity
     */
    public MapDataExceptionsDTO (MapDataExceptionsEntity mapDataExceptionsEntity) {
        
        this.objtypeCode = mapDataExceptionsEntity.getObjtypeCode(); 
this.soc1Code = mapDataExceptionsEntity.getSoc1Code(); 
this.soc2Code = mapDataExceptionsEntity.getSoc2Code(); 
this.sqlStatement = mapDataExceptionsEntity.getSqlStatement(); 
this.createdBy = mapDataExceptionsEntity.getCreatedBy(); 
this.createdDate = mapDataExceptionsEntity.getCreatedDate(); 
this.lastUpdatedBy = mapDataExceptionsEntity.getLastUpdatedBy(); 
this.lastUpdatedDate = mapDataExceptionsEntity.getLastUpdatedDate(); 
this.auditStatus = mapDataExceptionsEntity.getAuditStatus(); 
this.tabrecSerial = mapDataExceptionsEntity.getTabrecSerial(); 

        
    }

/**
* @return Long
*/
public Long getObjtypeCode() {
   return objtypeCode;
}
/**
* @return Long
*/
public Long getSoc1Code() {
   return soc1Code;
}
/**
* @return Long
*/
public Long getSoc2Code() {
   return soc2Code;
}
/**
* @return String
*/
public String getSqlStatement() {
   return sqlStatement;
}
/**
* @return Long
*/
public Long getCreatedBy() {
   return createdBy;
}
/**
* @return Timestamp
*/
public Timestamp getCreatedDate() {
   return createdDate;
}
/**
* @return Long
*/
public Long getLastUpdatedBy() {
   return lastUpdatedBy;
}
/**
* @return Timestamp
*/
public Timestamp getLastUpdatedDate() {
   return lastUpdatedDate;
}
/**
* @return Long
*/
public Long getAuditStatus() {
   return auditStatus;
}
/**
* @return Long
*/
public Long getTabrecSerial() {
   return tabrecSerial;
}

    
/**
* @param objtypeCode
*/
public void setObjtypeCode(Long objtypeCode) {
   this.objtypeCode=objtypeCode;
}
/**
* @param soc1Code
*/
public void setSoc1Code(Long soc1Code) {
   this.soc1Code=soc1Code;
}
/**
* @param soc2Code
*/
public void setSoc2Code(Long soc2Code) {
   this.soc2Code=soc2Code;
}
/**
* @param sqlStatement
*/
public void setSqlStatement(String sqlStatement) {
   this.sqlStatement=sqlStatement;
}
/**
* @param createdBy
*/
public void setCreatedBy(Long createdBy) {
   this.createdBy=createdBy;
}
/**
* @param createdDate
*/
public void setCreatedDate(Timestamp createdDate) {
   this.createdDate=createdDate;
}
/**
* @param lastUpdatedBy
*/
public void setLastUpdatedBy(Long lastUpdatedBy) {
   this.lastUpdatedBy=lastUpdatedBy;
}
/**
* @param lastUpdatedDate
*/
public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
   this.lastUpdatedDate=lastUpdatedDate;
}
/**
* @param auditStatus
*/
public void setAuditStatus(Long auditStatus) {
   this.auditStatus=auditStatus;
}
/**
* @param tabrecSerial
*/
public void setTabrecSerial(Long tabrecSerial) {
   this.tabrecSerial=tabrecSerial;
}

    
}

