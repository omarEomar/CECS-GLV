package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.GenderMaritalEntity;
import com.beshara.csc.inf.business.entity.GenderMaritalEntityKey;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

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
public class GenderMaritalDTO extends InfDTO implements IGenderMaritalDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long gentypeCode;
    private Long marstatusCode;
    private String genmarName;
    private Long auditStatus;
    private Long tabrecSerial;
    private IGenderTypesDTO genderTypesDTO;
    private IMaritalStatusDTO maritalStatusDTO;

    /**
     * GenderMaritalDTO Default Constructor */
    public GenderMaritalDTO() {
        super();
    }

    /**
     * @param genderMaritalEntity
     */
    public GenderMaritalDTO(GenderMaritalEntity genderMaritalEntity) {
        this.setCode(new GenderMaritalEntityKey(genderMaritalEntity.getGenderTypesEntity().getGentypeCode(), genderMaritalEntity.getMaritalStatusEntity().getMarstatusCode()));
        this.gentypeCode = genderMaritalEntity.getGentypeCode();
        this.marstatusCode = genderMaritalEntity.getMarstatusCode();
        this.genmarName = genderMaritalEntity.getGenmarName();
        this.setCreatedBy(genderMaritalEntity.getCreatedBy());
        this.setCreatedDate(genderMaritalEntity.getCreatedDate());
        this.setLastUpdatedBy(genderMaritalEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(genderMaritalEntity.getLastUpdatedDate());
        this.auditStatus = genderMaritalEntity.getAuditStatus();
        this.tabrecSerial = genderMaritalEntity.getTabrecSerial();
        if (genderMaritalEntity.getGenderTypesEntity() != null) {
            this.genderTypesDTO = new GenderTypesDTO(genderMaritalEntity.getGenderTypesEntity());
        }

        if (genderMaritalEntity.getMaritalStatusEntity() != null) {
            this.maritalStatusDTO = new MaritalStatusDTO(genderMaritalEntity.getMaritalStatusEntity());
        }


    }

    /**
     * @return Long
     */
    public Long getGentypeCode() {
        return gentypeCode;
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
    public String getGenmarName() {
        return genmarName;
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
     * @param gentypeCode
     */
    public void setGentypeCode(Long gentypeCode) {
        this.gentypeCode = gentypeCode;
    }

    /**
     * @param marstatusCode
     */
    public void setMarstatusCode(Long marstatusCode) {
        this.marstatusCode = marstatusCode;
    }

    /**
     * @param genmarName
     */
    public void setGenmarName(String genmarName) {
        this.genmarName = genmarName;
    } /**
 * @param createdBy
 */

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

    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO) {
        this.genderTypesDTO = genderTypesDTO;
    }

    public IGenderTypesDTO getGenderTypesDTO() {
        return genderTypesDTO;
    }

    public String getGenderTypeKey() {
        try {
            if (this.genderTypesDTO != null)
                return (String)genderTypesDTO.getCode().getKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setGenderTypeKey(String key) {
        if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE + "")) {
            GenderTypesEntityKey entityKey = new GenderTypesEntityKey(Long.parseLong(key));
            this.genderTypesDTO = new GenderTypesDTO();
            this.genderTypesDTO.setCode(entityKey);
        } else {
            this.genderTypesDTO = null;
        }
    }

    public void setMaritalStatusDTO(IMaritalStatusDTO maritalStatusDTO) {
        this.maritalStatusDTO = maritalStatusDTO;
    }

    public IMaritalStatusDTO getMaritalStatusDTO() {
        return maritalStatusDTO;
    }
}
