package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.CurrenciesEntityKey;
import com.beshara.csc.inf.business.entity.SystemSettingsEntity;
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
public class SystemSettingsDTO extends InfDTO implements ISystemSettingsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long syssettingCode;
    private String syssettingName;
    private String syssettingValue;
    private Long auditStatus;
    private Long tabrecSerial;

    /**
     * SystemSettingsDTO Default Constructor */
    public SystemSettingsDTO() {
        super();
    }
    public SystemSettingsDTO(Long syssettingCode , String syssettingName) {
        this.setCode(new SystemSettingsEntityKey(syssettingCode));
        this.setName(syssettingName);
    }

    /**
     * @param systemSettingsEntity
     */
    public SystemSettingsDTO(SystemSettingsEntity systemSettingsEntity) {
        this.syssettingCode = systemSettingsEntity.getSyssettingCode();
        this.setCode(new SystemSettingsEntityKey(systemSettingsEntity.getSyssettingCode()));
        this.setName(systemSettingsEntity.getSyssettingName()); 
        this.syssettingName = systemSettingsEntity.getSyssettingName();
        this.syssettingValue = systemSettingsEntity.getSyssettingValue();
        this.setCreatedBy(systemSettingsEntity.getCreatedBy());
        this.setCreatedDate(systemSettingsEntity.getCreatedDate());
        this.setLastUpdatedBy(systemSettingsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(systemSettingsEntity.getLastUpdatedDate());
        this.auditStatus = systemSettingsEntity.getAuditStatus();
        this.tabrecSerial = systemSettingsEntity.getTabrecSerial();
    }

    /**
     * @return Long
     */
    public Long getSyssettingCode() {
        return syssettingCode;
    }

    /**
     * @return String
     */
    public String getSyssettingName() {
        return syssettingName;
    }

    /**
     * @return String
     */
    public String getSyssettingValue() {
        return syssettingValue;
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
     * @param syssettingCode
     */
    public void setSyssettingCode(Long syssettingCode) {
        this.syssettingCode = syssettingCode;
    }

    /**
     * @param syssettingName
     */
    public void setSyssettingName(String syssettingName) {
        this.syssettingName = syssettingName;
    }

    /**
     * @param syssettingValue
     */
    public void setSyssettingValue(String syssettingValue) {
        this.syssettingValue = syssettingValue;
    }

    /**
     * @param auditStatus
     */
    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }
}
