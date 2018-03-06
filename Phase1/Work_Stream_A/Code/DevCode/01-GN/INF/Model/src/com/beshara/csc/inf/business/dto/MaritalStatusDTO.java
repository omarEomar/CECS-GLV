package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.MaritalStatusEntity;
import com.beshara.csc.inf.business.entity.MaritalStatusEntityKey;

import java.util.List;

/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class MaritalStatusDTO extends InfDTO implements IMaritalStatusDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String marstatusName;
    private Long marstatusCode;
    private Long auditStatus;
    private Long tabrecSerial;
    List<IGenderMaritalDTO> genderMaritalDTOList;


    /**
     * MaritalStatusDTO Default Constructor */
    public MaritalStatusDTO() {
    }

    public MaritalStatusDTO(Long code, String marstatusMasName, String marstatusFemName) {
        setCode(new MaritalStatusEntityKey(code));
        setMarstatusName(marstatusName);
        setName(marstatusName);

    }

    /**
     * @param maritalStatusEntity
     */
    public MaritalStatusDTO(MaritalStatusEntity maritalStatusEntity) {
        setCode(new MaritalStatusEntityKey(maritalStatusEntity.getMarstatusCode()));
        setName(maritalStatusEntity.getMarStatusName());
        this.setMarstatusCode(maritalStatusEntity.getMarstatusCode());
        this.setMarstatusName(maritalStatusEntity.getMarStatusName());
        this.setCreatedBy(maritalStatusEntity.getCreatedBy());
        this.setCreatedDate(maritalStatusEntity.getCreatedDate());
        this.setLastUpdatedBy(maritalStatusEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(maritalStatusEntity.getLastUpdatedDate());
        this.auditStatus = maritalStatusEntity.getAuditStatus();
        this.tabrecSerial = maritalStatusEntity.getTabrecSerial();
    }

    /**
     * @return Long
     */
    public Long getMarstatusCode() {
        return marstatusCode;
    }

    /**
     * @return String
     */
    public String getMarstatusName() {
        return marstatusName;
    }

    /**
     * @param marstatusCode
     */
    public void setMarstatusCode(Long marstatusCode) {
        this.marstatusCode = marstatusCode;
    }

    /**
     * @param marstatusName
     */
    public void setMarstatusName(String marstatusName) {
        this.marstatusName = marstatusName;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setGenderMaritalDTOList(List<IGenderMaritalDTO> genderMaritalDTOList) {
        this.genderMaritalDTOList = genderMaritalDTOList;
    }

    public List<IGenderMaritalDTO> getGenderMaritalDTOList() {
        return genderMaritalDTOList;
    }
}
