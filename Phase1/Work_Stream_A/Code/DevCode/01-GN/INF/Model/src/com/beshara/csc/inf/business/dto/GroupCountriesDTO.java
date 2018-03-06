package com.beshara.csc.inf.business.dto;
import com.beshara.csc.inf.business.entity.GroupCountriesEntity;
import com.beshara.csc.inf.business.entity.GroupCountriesEntityKey;
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
public class GroupCountriesDTO extends InfDTO implements IGroupCountriesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long cntrygrpCode;
    private Long cntryCode;
    private Long auditStatus;
    private Long tabrecSerial;
    private ICountriesDTO countriesDTO;
    private ICountryGroupsDTO countryGroupsDTO;
    /** 
     * GroupCountriesDTO Default Constructor */
    public GroupCountriesDTO() {        super();
    }    /** 
     * @param groupCountriesEntity 
     */
    public GroupCountriesDTO(GroupCountriesEntity groupCountriesEntity) { //this.cntrygrpCode = groupCountriesEntity.getCntrygrpCode ( ) ; 
        //this.cntryCode = groupCountriesEntity.getCntryCode ( ) ; 
        setCode(new GroupCountriesEntityKey(groupCountriesEntity.getCntrygrpCode(),                                             groupCountriesEntity.getCntryCode()));
        this.countriesDTO =                 new CountriesDTO(groupCountriesEntity.getCountriesEntity());
        this.countryGroupsDTO =                 new CountryGroupsDTO(groupCountriesEntity.getCountryGroupsEntity());
        this.setCreatedBy(groupCountriesEntity.getCreatedBy());
        this.setCreatedDate(groupCountriesEntity.getCreatedDate());
        this.setLastUpdatedBy(groupCountriesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(groupCountriesEntity.getLastUpdatedDate());
        this.auditStatus = groupCountriesEntity.getAuditStatus();
        this.tabrecSerial = groupCountriesEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getCntrygrpCode() {        return cntrygrpCode;
    }    /** 
     * @return Long 
     */
    public Long getCntryCode() {        return cntryCode;
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
     * @param cntryCode 
     */
    public void setCntryCode(Long cntryCode) {        this.cntryCode = cntryCode;
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
    }    public void setCountryGroupsDTO(ICountryGroupsDTO countryGroupsDTO) {        this.countryGroupsDTO = countryGroupsDTO;
    }    public ICountryGroupsDTO getCountryGroupsDTO() {        return countryGroupsDTO;
    }}
