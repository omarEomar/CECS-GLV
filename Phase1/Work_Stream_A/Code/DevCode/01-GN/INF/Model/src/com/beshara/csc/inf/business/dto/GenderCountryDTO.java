package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.GenderCountryEntity;
import com.beshara.csc.inf.business.entity.GenderCountryEntityKey;


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
public class GenderCountryDTO extends InfDTO implements IGenderCountryDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    //private Long gentypeCode ;
    //private Long cntryCode ;
    //private String gencntryName ;
    private Long auditStatus;
    private Long tabrecSerial;
    private ICountriesDTO countriesDTO;
    private IGenderTypesDTO genderTypesDTO;

    /**
     * GenderCountryDTO Default Constructor */
    public GenderCountryDTO() {
        super();
    }

    /**
     * @param genderCountryEntity
     */
    public GenderCountryDTO(GenderCountryEntity genderCountryEntity) {
        setCode(new GenderCountryEntityKey(genderCountryEntity.getGentypeCode(), genderCountryEntity.getCntryCode()));
        setName(genderCountryEntity.getGencntryName());
        //this.gentypeCode = genderCountryEntity.getGentypeCode ( ) ;
        //this.cntryCode = genderCountryEntity.getCntryCode ( ) ;
        //this.gencntryName = genderCountryEntity.getGencntryName ( ) ;
        this.countriesDTO = InfDTOFactory.createCountriesDTO(genderCountryEntity.getCountriesEntity());
        this.genderTypesDTO = InfDTOFactory.createGenderTypesDTO(genderCountryEntity.getGenderTypesEntity());
        this.setCreatedBy(genderCountryEntity.getCreatedBy());
        this.setCreatedDate(genderCountryEntity.getCreatedDate());
        this.setLastUpdatedBy(genderCountryEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(genderCountryEntity.getLastUpdatedDate());
        this.auditStatus = genderCountryEntity.getAuditStatus();
        this.tabrecSerial = genderCountryEntity.getTabrecSerial();
    } // /**
    // * @return Long
    // */
    // public Long getGentypeCode ( ) {
    // return gentypeCode ;
    // }
    //
    // /**
    // * @return Long
    // */
    // public Long getCntryCode ( ) {
    // return cntryCode ;
    // }
    //
    // /**
    // * @return String
    // */
    // public String getGencntryName ( ) {
    // return gencntryName ;
    // }

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
    } // /**
    // * @param gentypeCode
    // */
    // public void setGentypeCode ( Long gentypeCode ) {
    // this.gentypeCode = gentypeCode ;
    // }
    //
    // /**
    // * @param cntryCode
    // */
    // public void setCntryCode ( Long cntryCode ) {
    // this.cntryCode = cntryCode ;
    // }
    //
    // /**
    // * @param gencntryName
    // */
    // public void setGencntryName ( String gencntryName ) {
    // this.gencntryName = gencntryName ;
    // }

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

    public void setCountriesDTO(ICountriesDTO countriesDTO) {
        this.countriesDTO = countriesDTO;
    }

    public ICountriesDTO getCountriesDTO() {
        return countriesDTO;
    }

    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO) {
        this.genderTypesDTO = genderTypesDTO;
    }

    public IGenderTypesDTO getGenderTypesDTO() {
        return genderTypesDTO;
    }
}
