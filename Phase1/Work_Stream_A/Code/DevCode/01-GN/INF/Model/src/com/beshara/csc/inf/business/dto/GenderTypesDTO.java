package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.GenderTypesEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;

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
public class GenderTypesDTO extends InfDTO implements IGenderTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long gentypeCode;
    private String gentypeName;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * GenderTypesDTO Default Constructor */
    public GenderTypesDTO() {        super();
    }    public GenderTypesDTO(Long code, String name) {        setCode(new GenderTypesEntityKey(code));
        setName(name);
    }    /** 
     * @param genderTypesEntity 
     */
    public GenderTypesDTO(GenderTypesEntity genderTypesEntity) {        
        setCode(new GenderTypesEntityKey(genderTypesEntity.getGentypeCode()));
        setName(genderTypesEntity.getGentypeName());
        this.gentypeCode = genderTypesEntity.getGentypeCode();
        this.gentypeName = genderTypesEntity.getGentypeName();
        this.setCreatedBy(genderTypesEntity.getCreatedBy());
        this.setCreatedDate(genderTypesEntity.getCreatedDate());
        this.setLastUpdatedBy(genderTypesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(genderTypesEntity.getLastUpdatedDate());
        this.auditStatus = genderTypesEntity.getAuditStatus();
        this.tabrecSerial = genderTypesEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getGentypeCode() {        return gentypeCode;
    }    /** 
     * @return String 
     */
    public String getGentypeName() {        return gentypeName;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param gentypeCode 
     */
    public void setGentypeCode(Long gentypeCode) {        this.gentypeCode = gentypeCode;
    }    /** 
     * @param gentypeName 
     */
    public void setGentypeName(String gentypeName) {        this.gentypeName = gentypeName;
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
