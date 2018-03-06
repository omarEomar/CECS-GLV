package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.BloodGroupsEntity;
import com.beshara.csc.inf.business.entity.BloodGroupsEntityKey;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;

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
public class BloodGroupsDTO extends InfDTO implements IBloodGroupsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long bldgroupCode;
    private String bldgroupName;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * BloodGroupsDTO Default Constructor */
    public BloodGroupsDTO() {        super();
    }    /** 
     * @param bloodGroupsEntity 
     */
    public BloodGroupsDTO(BloodGroupsEntity bloodGroupsEntity) {        
        setCode(new BloodGroupsEntityKey(bloodGroupsEntity.getBldgroupCode()));
        setName(bloodGroupsEntity.getBldgroupName());
        this.bldgroupCode = bloodGroupsEntity.getBldgroupCode();
        this.bldgroupName = bloodGroupsEntity.getBldgroupName();
        this.setCreatedBy(bloodGroupsEntity.getCreatedBy());
        this.setCreatedDate(bloodGroupsEntity.getCreatedDate());
        this.setLastUpdatedBy(bloodGroupsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(bloodGroupsEntity.getLastUpdatedDate());
        this.auditStatus = bloodGroupsEntity.getAuditStatus();
        this.tabrecSerial = bloodGroupsEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getBldgroupCode() {        return bldgroupCode;
    }    /** 
     * @return String 
     */
    public String getBldgroupName() {        return bldgroupName;
    } /**
 * @return Timestamp
 */
    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param bldgroupCode 
     */
    public void setBldgroupCode(Long bldgroupCode) {        this.bldgroupCode = bldgroupCode;
    }    /** 
     * @param bldgroupName 
     */
    public void setBldgroupName(String bldgroupName) {        this.bldgroupName = bldgroupName;
    } /**
 * @param createdBy
 */
    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }}
