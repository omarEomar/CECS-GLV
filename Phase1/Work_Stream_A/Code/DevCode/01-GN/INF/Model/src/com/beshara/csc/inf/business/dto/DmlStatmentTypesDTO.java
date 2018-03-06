package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.DmlStatmentTypesEntity;
import com.beshara.csc.inf.business.entity.DmlStatmentTypesEntityKey;
import com.beshara.csc.inf.business.entity.SystemSettingsEntityKey;

import java.io.Serializable;
import java.sql.Timestamp;
/** 
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ; 
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ; 
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ; 
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ; 
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group 
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany 
 * @version 1.0 
 * @since 03/09/2007 
 */
public class DmlStatmentTypesDTO extends InfDTO implements IDmlStatmentTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long dmlstatypeCode;
    private String dmlstatypeName;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * DmlStatmentTypesDTO Default Constructor */
    public DmlStatmentTypesDTO() {        super();
    }    /** 
     * @param dmlStatmentTypesEntity 
     */
         
         
     public DmlStatmentTypesDTO(Long dmlstatypeCode , String dmlstatypeName) {
         
         setCode(new DmlStatmentTypesEntityKey(dmlstatypeCode));
         setName(dmlstatypeName);
     }
   
         
         
         
         
         
    public DmlStatmentTypesDTO(DmlStatmentTypesEntity dmlStatmentTypesEntity) {       
         this.dmlstatypeCode = dmlStatmentTypesEntity.getDmlstatypeCode();
        this.dmlstatypeName = dmlStatmentTypesEntity.getDmlstatypeName();
         setCode(new DmlStatmentTypesEntityKey(dmlStatmentTypesEntity.getDmlstatypeCode()));
         setName(dmlStatmentTypesEntity.getDmlstatypeName());
         
        this.setCreatedBy(dmlStatmentTypesEntity.getCreatedBy());
        this.setCreatedDate(dmlStatmentTypesEntity.getCreatedDate());
        this.setLastUpdatedBy(dmlStatmentTypesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(dmlStatmentTypesEntity.getLastUpdatedDate());
        this.auditStatus = dmlStatmentTypesEntity.getAuditStatus();
        this.tabrecSerial = dmlStatmentTypesEntity.getTabrecSerial();
               
    }    /** 
     * @return Long 
     */
    public Long getDmlstatypeCode() {        return dmlstatypeCode;
    }    /** 
     * @return String 
     */
    public String getDmlstatypeName() {        return dmlstatypeName;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param dmlstatypeCode 
     */
    public void setDmlstatypeCode(Long dmlstatypeCode) {        this.dmlstatypeCode = dmlstatypeCode;
    }    /** 
     * @param dmlstatypeName 
     */
    public void setDmlstatypeName(String dmlstatypeName) {        this.dmlstatypeName = dmlstatypeName;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }}
