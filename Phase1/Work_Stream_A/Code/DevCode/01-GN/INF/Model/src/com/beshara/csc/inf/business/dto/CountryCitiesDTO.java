package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.CountryCitiesEntity;
import com.beshara.csc.inf.business.entity.CountryCitiesEntityKey;
import java.io.Serializable;
import java.sql.Timestamp;
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
public class CountryCitiesDTO extends InfDTO implements ICountryCitiesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long cntryCode;
    private Long cntrycityCode;
    private String cntrycityName;
    private Long capitalFlag;
    private Long longitude;
    private Long latitude;
    private Long createdBy;
    private Timestamp createdDate;
    private Long lastUpdatedBy;
    private Timestamp lastUpdatedDate;
    private Long auditStatus;
    private Long tabrecSerial;
    private ICountriesDTO countriesDTO;
    /** 
     * CountryCitiesDTO Default Constructor */
    public CountryCitiesDTO() {        super();
    }    /** 
     * @param countryCitiesEntity 
     */
    public CountryCitiesDTO(CountryCitiesEntity countryCitiesEntity) { //this.cntryCode = countryCitiesEntity.getCntryCode ( ) ; 
        setCode(new CountryCitiesEntityKey(countryCitiesEntity.getCntryCode(),                                            countryCitiesEntity.getCntrycityCode()));
        //this.cntrycityCode = countryCitiesEntity.getCntrycityCode ( ) ; 
        this.cntrycityName = countryCitiesEntity.getCntrycityName ( ) ; 
        this.countriesDTO =                 InfDTOFactory.createCountriesDTO(countryCitiesEntity.getCountriesEntity());
        setName(countryCitiesEntity.getCntrycityName());
        this.capitalFlag = countryCitiesEntity.getCapitalFlag();
        this.longitude = countryCitiesEntity.getLongitude();
        this.latitude = countryCitiesEntity.getLatitude();
        this.createdBy = countryCitiesEntity.getCreatedBy();
        this.createdDate = countryCitiesEntity.getCreatedDate();
        this.lastUpdatedBy = countryCitiesEntity.getLastUpdatedBy();
        this.lastUpdatedDate = countryCitiesEntity.getLastUpdatedDate();
        this.auditStatus = countryCitiesEntity.getAuditStatus();
        this.tabrecSerial = countryCitiesEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getCntryCode() {        return cntryCode;
    }    /** 
     * @return Long 
     */
    public Long getCntrycityCode() {        return cntrycityCode;
    }    /** 
     * @return String 
     */
    public String getCntrycityName() {        return cntrycityName;
    }    /** 
     * @return Long 
     */
    public Long getCapitalFlag() {        return capitalFlag;
    }    /** 
     * @return Long 
     */
    public Long getLongitude() {        return longitude;
    }    /** 
     * @return Long 
     */
    public Long getLatitude() {        return latitude;
    }    /** 
     * @return Long 
     */
    public Long getCreatedBy() {        return createdBy;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getCreatedDate() {        return createdDate;
    }    /** 
     * @return Long 
     */
    public Long getLastUpdatedBy() {        return lastUpdatedBy;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getLastUpdatedDate() {        return lastUpdatedDate;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param cntryCode 
     */
    public void setCntryCode(Long cntryCode) {        this.cntryCode = cntryCode;
    }    /** 
     * @param cntrycityCode 
     */
    public void setCntrycityCode(Long cntrycityCode) {        this.cntrycityCode = cntrycityCode;
    }    /** 
     * @param cntrycityName 
     */
    public void setCntrycityName(String cntrycityName) {        this.cntrycityName = cntrycityName;
    }    /** 
     * @param capitalFlag 
     */
    public void setCapitalFlag(Long capitalFlag) {        this.capitalFlag = capitalFlag;
    }    /** 
     * @param longitude 
     */
    public void setLongitude(Long longitude) {        this.longitude = longitude;
    }    /** 
     * @param latitude 
     */
    public void setLatitude(Long latitude) {        this.latitude = latitude;
    }    /** 
     * @param createdBy 
     */
    public void setCreatedBy(Long createdBy) {        this.createdBy = createdBy;
    }    /** 
     * @param createdDate 
     */
    public void setCreatedDate(Timestamp createdDate) {        this.createdDate = createdDate;
    }    /** 
     * @param lastUpdatedBy 
     */
    public void setLastUpdatedBy(Long lastUpdatedBy) {        this.lastUpdatedBy = lastUpdatedBy;
    }    /** 
     * @param lastUpdatedDate 
     */
    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {        this.lastUpdatedDate = lastUpdatedDate;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public void setCountriesDTO(ICountriesDTO countriesDTO) {        this.countriesDTO = countriesDTO;
    }    public ICountriesDTO getCountriesDTO() {        return countriesDTO;
    }    public boolean isBooleanCapitalFlag() {        if (capitalFlag != null && capitalFlag.intValue() == 1) {            return true;
        }        return false;
    }    public void setBooleanCapitalFlag(boolean capitalFlag) {        if (capitalFlag) {            setCapitalFlag(new Long(1));
        } else {            setCapitalFlag(new Long(0));
        }    }}
