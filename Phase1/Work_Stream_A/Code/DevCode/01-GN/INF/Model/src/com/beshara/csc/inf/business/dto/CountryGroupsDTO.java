package com.beshara.csc.inf.business.dto;
import com.beshara.csc.inf.business.entity.CountryGroupsEntity;
import com.beshara.csc.inf.business.entity.CountryGroupsEntityKey;
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
public class CountryGroupsDTO extends InfDTO implements ICountryGroupsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long cntrygrpCode;
    private String cntrygrpName;
    private Long parentCntrygrp;
    private ICountryGroupsDTO countryGroupsDTO;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * CountryGroupsDTO Default Constructor */
    public CountryGroupsDTO() {        super();
    }    /** 
     * @param countryGroupsEntity 
     */
    public CountryGroupsDTO(CountryGroupsEntity countryGroupsEntity) {        setCode(new CountryGroupsEntityKey(countryGroupsEntity.getCntrygrpCode()));
        setName(countryGroupsEntity.getCntrygrpName());
        if (countryGroupsEntity.getCountryGroupsEntity() != null) {            countryGroupsDTO =                     new CountryGroupsDTO(countryGroupsEntity.getCountryGroupsEntity());
        }        this.cntrygrpCode = countryGroupsEntity.getCntrygrpCode();
        this.cntrygrpName = countryGroupsEntity.getCntrygrpName();
        this.parentCntrygrp = countryGroupsEntity.getParentCntrygrp();
        this.setCreatedBy(countryGroupsEntity.getCreatedBy());
        this.setCreatedDate(countryGroupsEntity.getCreatedDate());
        this.setLastUpdatedBy(countryGroupsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(countryGroupsEntity.getLastUpdatedDate());
        this.auditStatus = countryGroupsEntity.getAuditStatus();
        this.tabrecSerial = countryGroupsEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getCntrygrpCode() {        return cntrygrpCode;
    }    /** 
     * @return String 
     */
    public String getCntrygrpName() {        return cntrygrpName;
    }    /** 
     * @return Long 
     */
    public Long getParentCntrygrp() {        return parentCntrygrp;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param cntrygrpCode 
     */
    public void setCntrygrpCode(Long cntrygrpCode) {        this.cntrygrpCode = cntrygrpCode;
    }    /** 
     * @param cntrygrpName 
     */
    public void setCntrygrpName(String cntrygrpName) {        this.cntrygrpName = cntrygrpName;
    }    /** 
     * @param parentCntrygrp 
     */
    public void setParentCntrygrp(Long parentCntrygrp) {        this.parentCntrygrp = parentCntrygrp;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public void setCountryGroupsDTO(ICountryGroupsDTO countryGroupsDTO) {        this.countryGroupsDTO = countryGroupsDTO;
    }    public ICountryGroupsDTO getCountryGroupsDTO() {        return countryGroupsDTO;
    }}
