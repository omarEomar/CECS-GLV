package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.MilitaryStatusEntity;
import com.beshara.csc.inf.business.entity.MilitaryStatusEntityKey;

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
public class MilitaryStatusDTO extends InfDTO implements IMilitaryStatusDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long mltstatusCode;
    private String mltstatusName;
    private Long auditStatus;
    private Long tabrecSerial;

    /**
     * MilitaryStatusDTO Default Constructor */
    public MilitaryStatusDTO() {
        super();
    }

    public MilitaryStatusDTO(Long mltstatusCode, String mltstatusName) {
        this.setCode(new MilitaryStatusEntityKey(mltstatusCode));
        this.setName(mltstatusName);
    }
   

    /**
     * @param militaryStatusEntity
     */
    public MilitaryStatusDTO(MilitaryStatusEntity militaryStatusEntity) {
        this.mltstatusCode = militaryStatusEntity.getMltstatusCode();
        this.setCode(new MilitaryStatusEntityKey(militaryStatusEntity.getMltstatusCode()));
        this.mltstatusName = militaryStatusEntity.getMltstatusName();
        this.setName(militaryStatusEntity.getMltstatusName());
        this.setCreatedBy(militaryStatusEntity.getCreatedBy());
        this.setCreatedDate(militaryStatusEntity.getCreatedDate());
        this.setLastUpdatedBy(militaryStatusEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(militaryStatusEntity.getLastUpdatedDate());
        this.auditStatus = militaryStatusEntity.getAuditStatus();
        this.tabrecSerial = militaryStatusEntity.getTabrecSerial();
    }


    /**
     * @return Long
     */
    public Long getMltstatusCode() {
        return mltstatusCode;
    }

    /**
     * @return String
     */
    public String getMltstatusName() {
        return mltstatusName;
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
     * @param mltstatusCode
     */
    public void setMltstatusCode(Long mltstatusCode) {
        this.mltstatusCode = mltstatusCode;
    }

    /**
     * @param mltstatusName
     */
    public void setMltstatusName(String mltstatusName) {
        this.mltstatusName = mltstatusName;
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
