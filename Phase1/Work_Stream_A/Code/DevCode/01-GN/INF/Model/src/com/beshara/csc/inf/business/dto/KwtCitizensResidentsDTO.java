package com.beshara.csc.inf.business.dto;


import com.beshara.base.dto.TreeDTO;
import com.beshara.csc.hr.crs.business.dto.IPersonRevisionsDTO;
import com.beshara.csc.hr.crs.business.dto.IRegRejectionReasonsDTO;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.HandicapStatusEntityKey;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.MaritalStatusEntityKey;
import com.beshara.csc.inf.business.entity.ReligionsEntityKey;
import com.beshara.csc.inf.business.entity.SpecialCaseTypesEntityKey;

import java.math.BigDecimal;

import java.sql.Timestamp;

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
public class KwtCitizensResidentsDTO extends TreeDTO implements IKwtCitizensResidentsDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long civilId;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String lastName;
    private String familyName;
    private String englishName;
    private Timestamp birthDate;
    private Long gentypeCode;
    private Long maritalStatusCode;
    //private Long marstatusCode ;
    private IGenderTypesDTO genderTypesDTO;
    private IResidentTypeDTO residentTypeDTO;
    private IMaritalStatusDTO maritalStatusDTO;
    private Long religionCode; //////////////////////////////////////////
    private IReligionsDTO religionsDTO;
    private Long nationality; //////////////////////////////////////////
    private IGenderCountryDTO countriesDTO;
    private Timestamp nationalityDate;
    private String phonesNo;
    private String mobileNo;
    private String eMail;
    private Long nonStatus;
    private Long restypeCode;
    private Long passportCntryCode;
    private String passportNo;
    private Timestamp passportIssueDate;
    private Timestamp passportExpiredDate;
    private String mapCode;
    private IKwMapDTO kwMapDTO;
    private Long streetCode;
    private String buildingNo;
    private Long floorNo;
    private Long flatNo;
    private String addressInDetails;
    private Long bldgroupCode;
    private Long mltstatusCode;
    private Timestamp deathDate;
    private Timestamp endResidentDate;
    private Long capstatusCode;
    private IHandicapStatusDTO handicapStatusDTO;
    private Long spccsetypeCode;
    private ISpecialCaseTypesDTO specialCaseTypesDTO;
    private Long activeFlag;
    private Long auditStatus;
    private Long tabrecSerial;
    private List<IPersonQualificationsDTO> personQualificationsDTOList;
    private List<IRegRejectionReasonsDTO> exceptionCasesDTOList;
    /*Added By Taha Abdul Mejid*/
    private List<ISeekerLanguageSkillsDTO> seekerLanguageSkillsDTOList;
    private List<IPersonsInformationDTO> personsInformationDTOList;
    private List<IKwtWorkDataDTO> kwtWorkDataDTOList;
    private List<IPersonRevisionsDTO> personRevisionsDTOList;
    private String fullNameColumn;
    private Long mobCompanyCode;
    private Long sendSmsFlag;
    private String bloodGroupName;
    private String flatType;
    private String adrsPartNo;
    private String governrate;
    private String area ;
    private String partNo ;

    /**
     * KwtCitizensResidentsDTO Default Constructor */
    public KwtCitizensResidentsDTO() {
        super();
    }

    public KwtCitizensResidentsDTO(Long civilId, String fName, String sName, String tName, String lName) {
        setCode(new KwtCitizensResidentsEntityKey(civilId));
        setFirstName(fName);
        setSecondName(sName);
        setThirdName(tName);
        setLastName(lName);
    }

    public KwtCitizensResidentsDTO(List list) {
        setCode(new KwtCitizensResidentsEntityKey(((BigDecimal)list.get(0)).longValue()));
        setFirstName((String)list.get(1));
        setSecondName((String)list.get(2));
        setThirdName((String)list.get(3));
        setLastName((String)list.get(4));
        setFamilyName((String)list.get(5));
        setBirthDate((Timestamp)list.get(6));

    }

    public KwtCitizensResidentsDTO(Long civilId, String fName, String sName, String tName, String lName,
                                   String familyName) {
        setCode(new KwtCitizensResidentsEntityKey(civilId));
        setFirstName(fName);
        setSecondName(sName);
        setThirdName(tName);
        setLastName(lName);
        setFamilyName(familyName);

    }

    /**
     * @param kwtCitizensResidentsEntity
     */
    public KwtCitizensResidentsDTO(KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        this.civilId = kwtCitizensResidentsEntity.getCivilId();
        setCode(new KwtCitizensResidentsEntityKey(kwtCitizensResidentsEntity.getCivilId()));
        //        setName(kwtCitizensResidentsEntity.getFirstName()+" "+kwtCitizensResidentsEntity.getSecondName()+" "+kwtCitizensResidentsEntity.getThirdName()+" "+
        //                kwtCitizensResidentsEntity.getLastName()+" "+kwtCitizensResidentsEntity.getFamilyName());
        String fullCivilName =
            kwtCitizensResidentsEntity.getFirstName() + " " + kwtCitizensResidentsEntity.getSecondName() + " " +
            kwtCitizensResidentsEntity.getThirdName() + " " + kwtCitizensResidentsEntity.getLastName();
        if (kwtCitizensResidentsEntity.getFamilyName() != null) {
            fullCivilName = fullCivilName + " " + kwtCitizensResidentsEntity.getFamilyName();
        }

        setName(fullCivilName);
        this.firstName = kwtCitizensResidentsEntity.getFirstName();
        this.secondName = kwtCitizensResidentsEntity.getSecondName();
        this.thirdName = kwtCitizensResidentsEntity.getThirdName();
        this.lastName = kwtCitizensResidentsEntity.getLastName();
        this.familyName = kwtCitizensResidentsEntity.getFamilyName();
        this.englishName = kwtCitizensResidentsEntity.getEnglishName();
        this.birthDate = kwtCitizensResidentsEntity.getBirthDate();
        this.gentypeCode = kwtCitizensResidentsEntity.getGentypeCode();
        if (kwtCitizensResidentsEntity.getGenderTypesEntity() != null)
            this.setGenderTypesDTO(new GenderTypesDTO(kwtCitizensResidentsEntity.getGenderTypesEntity()));
        if (kwtCitizensResidentsEntity.getRestypeCode() != null)
            this.setResidentTypeDTO(new ResidentTypeDTO(kwtCitizensResidentsEntity.getResidentTypeEntity()));
        //this.marstatusCode = kwtCitizensResidentsEntity.getMarstatusCode ( ) ;
        if (kwtCitizensResidentsEntity.getMaritalStatusEntity() != null)
            this.setMaritalStatusDTO(new MaritalStatusDTO(kwtCitizensResidentsEntity.getMaritalStatusEntity()));
        this.religionCode = kwtCitizensResidentsEntity.getReligionCode();
        if (kwtCitizensResidentsEntity.getReligionsEntity() != null)
            this.religionsDTO = new ReligionsDTO(kwtCitizensResidentsEntity.getReligionsEntity());
        this.nationality = kwtCitizensResidentsEntity.getNationality();
        if (kwtCitizensResidentsEntity.getCountriesEntity() != null)
            this.countriesDTO = new GenderCountryDTO(kwtCitizensResidentsEntity.getCountriesEntity());
        this.nationalityDate = kwtCitizensResidentsEntity.getNationalityDate();
        this.phonesNo = kwtCitizensResidentsEntity.getPhonesNo();
        if (kwtCitizensResidentsEntity.getNonStatus() != null) {
            this.nonStatus = kwtCitizensResidentsEntity.getNonStatus();
        }
        this.mobileNo = kwtCitizensResidentsEntity.getMobileNo();
        this.eMail = kwtCitizensResidentsEntity.getEMail();
        this.restypeCode = kwtCitizensResidentsEntity.getRestypeCode();
        this.passportCntryCode = kwtCitizensResidentsEntity.getPassportCntryCode();
        this.passportNo = kwtCitizensResidentsEntity.getPassportNo();
        this.passportIssueDate = kwtCitizensResidentsEntity.getPassportIssueDate();
        this.passportExpiredDate = kwtCitizensResidentsEntity.getPassportExpiredDate();
        this.mapCode = kwtCitizensResidentsEntity.getMapCode();
        if (kwtCitizensResidentsEntity.getKwMapEntity() != null)
            this.kwMapDTO = new KwMapDTO(kwtCitizensResidentsEntity.getKwMapEntity());
        this.streetCode = kwtCitizensResidentsEntity.getStreetCode();
        this.buildingNo = kwtCitizensResidentsEntity.getBuildingNo();
        this.floorNo = kwtCitizensResidentsEntity.getFloorNo();
        this.flatNo = kwtCitizensResidentsEntity.getFlatNo();
        this.addressInDetails = kwtCitizensResidentsEntity.getAddressInDetails();
        this.bldgroupCode = kwtCitizensResidentsEntity.getBldgroupCode();
        this.mltstatusCode = kwtCitizensResidentsEntity.getMltstatusCode();
        this.deathDate = kwtCitizensResidentsEntity.getDeathDate();
        this.endResidentDate = kwtCitizensResidentsEntity.getEndResidentDate();
        this.capstatusCode = kwtCitizensResidentsEntity.getCapstatusCode();
        if (kwtCitizensResidentsEntity.getHandicapStatusEntity() != null)
            this.handicapStatusDTO = new HandicapStatusDTO(kwtCitizensResidentsEntity.getHandicapStatusEntity());
        this.spccsetypeCode = kwtCitizensResidentsEntity.getSpccsetypeCode();
        if (kwtCitizensResidentsEntity.getSpecialCaseTypesEntity() != null)
            this.specialCaseTypesDTO = new SpecialCaseTypesDTO(kwtCitizensResidentsEntity.getSpecialCaseTypesEntity());
        this.activeFlag = kwtCitizensResidentsEntity.getActiveFlag();
        this.mobCompanyCode = kwtCitizensResidentsEntity.getMobCompanyCode();
        this.sendSmsFlag = kwtCitizensResidentsEntity.getSendSmsFlag();
        this.setCreatedBy(kwtCitizensResidentsEntity.getCreatedBy());
        this.setCreatedDate(kwtCitizensResidentsEntity.getCreatedDate());
        this.setLastUpdatedBy(kwtCitizensResidentsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(kwtCitizensResidentsEntity.getLastUpdatedDate());
        this.auditStatus = kwtCitizensResidentsEntity.getAuditStatus();
        this.tabrecSerial = kwtCitizensResidentsEntity.getTabrecSerial();
        // added By M.abdelsabour
        if (kwtCitizensResidentsEntity.getBldgroupCode() != null) {
            this.setBloodGroupName(kwtCitizensResidentsEntity.getBloodGroupsEntity().getBldgroupName());
        }
        if (kwtCitizensResidentsEntity.getFlatType() != null) {
            this.setFlatType(kwtCitizensResidentsEntity.getFlatType());
        }

        if (kwtCitizensResidentsEntity.getAdrsPartNo() != null) {
            this.setAdrsPartNo(kwtCitizensResidentsEntity.getAdrsPartNo());
        }

    } ///**
    //* @return Long
    //*/
    //public Long getCivilId ( ) {
    // return civilId ;
    // }

    public IKwtCitizensResidentsDTO getSimpleKwtCitizensResidentsDTO(KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        KwtCitizensResidentsDTO kwtCitizensResidentsDTO = new KwtCitizensResidentsDTO();
        kwtCitizensResidentsDTO.civilId = kwtCitizensResidentsEntity.getCivilId();
        kwtCitizensResidentsDTO.setCode(new KwtCitizensResidentsEntityKey(kwtCitizensResidentsEntity.getCivilId()));
        String fullCivilName =
            kwtCitizensResidentsEntity.getFirstName() + " " + kwtCitizensResidentsEntity.getSecondName() + " " +
            kwtCitizensResidentsEntity.getThirdName() + " " + kwtCitizensResidentsEntity.getLastName();
        if (kwtCitizensResidentsEntity.getFamilyName() != null) {
            fullCivilName = fullCivilName + " " + kwtCitizensResidentsEntity.getFamilyName();
        }
        kwtCitizensResidentsDTO.setName(fullCivilName);
        kwtCitizensResidentsDTO.firstName = kwtCitizensResidentsEntity.getFirstName();
        kwtCitizensResidentsDTO.secondName = kwtCitizensResidentsEntity.getSecondName();
        kwtCitizensResidentsDTO.thirdName = kwtCitizensResidentsEntity.getThirdName();
        kwtCitizensResidentsDTO.lastName = kwtCitizensResidentsEntity.getLastName();
        kwtCitizensResidentsDTO.familyName = kwtCitizensResidentsEntity.getFamilyName();
        kwtCitizensResidentsDTO.englishName = kwtCitizensResidentsEntity.getEnglishName();
        kwtCitizensResidentsDTO.birthDate = kwtCitizensResidentsEntity.getBirthDate();
        kwtCitizensResidentsDTO.gentypeCode = kwtCitizensResidentsEntity.getGentypeCode();
        if (kwtCitizensResidentsEntity.getGenderTypesEntity() != null)
            kwtCitizensResidentsDTO.setGenderTypesDTO(new GenderTypesDTO(kwtCitizensResidentsEntity.getGenderTypesEntity()));
        kwtCitizensResidentsDTO.mobileNo = kwtCitizensResidentsEntity.getMobileNo();
        kwtCitizensResidentsDTO.eMail = kwtCitizensResidentsEntity.getEMail();
        kwtCitizensResidentsDTO.restypeCode = kwtCitizensResidentsEntity.getRestypeCode();
        kwtCitizensResidentsDTO.passportCntryCode = kwtCitizensResidentsEntity.getPassportCntryCode();
        kwtCitizensResidentsDTO.passportNo = kwtCitizensResidentsEntity.getPassportNo();
        kwtCitizensResidentsDTO.passportIssueDate = kwtCitizensResidentsEntity.getPassportIssueDate();
        kwtCitizensResidentsDTO.passportExpiredDate = kwtCitizensResidentsEntity.getPassportExpiredDate();
        kwtCitizensResidentsDTO.streetCode = kwtCitizensResidentsEntity.getStreetCode();
        kwtCitizensResidentsDTO.buildingNo = kwtCitizensResidentsEntity.getBuildingNo();
        kwtCitizensResidentsDTO.floorNo = kwtCitizensResidentsEntity.getFloorNo();
        kwtCitizensResidentsDTO.flatNo = kwtCitizensResidentsEntity.getFlatNo();
        kwtCitizensResidentsDTO.addressInDetails = kwtCitizensResidentsEntity.getAddressInDetails();
        kwtCitizensResidentsDTO.bldgroupCode = kwtCitizensResidentsEntity.getBldgroupCode();
        kwtCitizensResidentsDTO.mltstatusCode = kwtCitizensResidentsEntity.getMltstatusCode();
        kwtCitizensResidentsDTO.deathDate = kwtCitizensResidentsEntity.getDeathDate();
        kwtCitizensResidentsDTO.endResidentDate = kwtCitizensResidentsEntity.getEndResidentDate();
        kwtCitizensResidentsDTO.activeFlag = kwtCitizensResidentsEntity.getActiveFlag();
        kwtCitizensResidentsDTO.mobCompanyCode = kwtCitizensResidentsEntity.getMobCompanyCode();
        kwtCitizensResidentsDTO.sendSmsFlag = kwtCitizensResidentsEntity.getSendSmsFlag();
        kwtCitizensResidentsDTO.setCreatedBy(kwtCitizensResidentsEntity.getCreatedBy());
        kwtCitizensResidentsDTO.setCreatedDate(kwtCitizensResidentsEntity.getCreatedDate());
        kwtCitizensResidentsDTO.setLastUpdatedBy(kwtCitizensResidentsEntity.getLastUpdatedBy());
        kwtCitizensResidentsDTO.setLastUpdatedDate(kwtCitizensResidentsEntity.getLastUpdatedDate());
        kwtCitizensResidentsDTO.auditStatus = kwtCitizensResidentsEntity.getAuditStatus();
        kwtCitizensResidentsDTO.tabrecSerial = kwtCitizensResidentsEntity.getTabrecSerial();
        return kwtCitizensResidentsDTO;
    }

    /**
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return String
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * @return String
     */
    public String getThirdName() {
        return thirdName;
    }

    /**
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return String
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * @return String
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getBirthDate() {
        return birthDate;
    }

    /**
     * @return Long
     */
    public Long getGentypeCode() {
        return gentypeCode;
    } //
    // /**
    // * @return Long
    // */
    // public Long getMarstatusCode ( ) {
    // return marstatusCode ;
    // }

    /**
     * @return Long
     */
    public Long getReligionCode() {
        return religionCode;
    }

    /**
     * @return Long
     */
    public Long getNationality() {
        return nationality;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getNationalityDate() {
        return nationalityDate;
    }

    /**
     * @return String
     */
    public String getPhonesNo() {
        return phonesNo;
    }

    /**
     * @return String
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @return String
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * @return Long
     */
    public Long getRestypeCode() {
        return restypeCode;
    }

    /**
     * @return Long
     */
    public Long getPassportCntryCode() {
        return passportCntryCode;
    }

    /**
     * @return String
     */
    public String getPassportNo() {
        return passportNo;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getPassportIssueDate() {
        return passportIssueDate;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getPassportExpiredDate() {
        return passportExpiredDate;
    }

    /**
     * @return String
     */
    public String getMapCode() {
        return mapCode;
    }

    /**
     * @return Long
     */
    public Long getStreetCode() {
        return streetCode;
    }

    /**
     * @return String
     */
    public String getBuildingNo() {
        return buildingNo;
    }

    /**
     * @return Long
     */
    public Long getFloorNo() {
        return floorNo;
    }

    /**
     * @return Long
     */
    public Long getFlatNo() {
        return flatNo;
    }

    /**
     * @return String
     */
    public String getAddressInDetails() {
        return addressInDetails;
    }

    /**
     * @return Long
     */
    public Long getBldgroupCode() {
        return bldgroupCode;
    }

    /**
     * @return Long
     */
    public Long getMltstatusCode() {
        return mltstatusCode;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getDeathDate() {
        return deathDate;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getEndResidentDate() {
        return endResidentDate;
    }

    /**
     * @return Long
     */
    public Long getCapstatusCode() {
        return capstatusCode;
    }

    /**
     * @return Long
     */
    public Long getSpccsetypeCode() {
        return spccsetypeCode;
    }

    /**
     * @return Long
     */
    public Long getActiveFlag() {
        return activeFlag;
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
    } ///**
    //* @param civilId
    //*/
    //public void setCivilId ( Long civilId ) {
    // this.civilId=civilId ;
    // }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param secondName
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * @param thirdName
     */
    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param familyName
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * @param englishName
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /**
     * @param birthDate
     */
    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @param gentypeCode
     */
    public void setGentypeCode(Long gentypeCode) {
        this.gentypeCode = gentypeCode;
    } //
    // /**
    // * @param marstatusCode
    // */
    // public void setMarstatusCode ( Long marstatusCode ) {
    // this.marstatusCode = marstatusCode ;
    // }

    /**
     * @param religionCode
     */
    public void setReligionCode(Long religionCode) {
        this.religionCode = religionCode;
    } //

    /**
     * @param nationality
     */
    public void setNationality(Long nationality) {
        this.nationality = nationality;
    }

    /**
     * @param nationalityDate
     */
    public void setNationalityDate(Timestamp nationalityDate) {
        this.nationalityDate = nationalityDate;
    }

    /**
     * @param phonesNo
     */
    public void setPhonesNo(String phonesNo) {
        this.phonesNo = phonesNo;
    }

    /**
     * @param mobileNo
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @param eMail
     */
    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * @param restypeCode
     */
    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
    }

    /**
     * @param passportCntryCode
     */
    public void setPassportCntryCode(Long passportCntryCode) {
        this.passportCntryCode = passportCntryCode;
    }

    /**
     * @param passportNo
     */
    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    /**
     * @param passportIssueDate
     */
    public void setPassportIssueDate(Timestamp passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    /**
     * @param passportExpiredDate
     */
    public void setPassportExpiredDate(Timestamp passportExpiredDate) {
        this.passportExpiredDate = passportExpiredDate;
    }

    /**
     * @param mapCode
     */
    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    /**
     * @param streetCode
     */
    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    /**
     * @param buildingNo
     */
    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    /**
     * @param floorNo
     */
    public void setFloorNo(Long floorNo) {
        this.floorNo = floorNo;
    }

    /**
     * @param flatNo
     */
    public void setFlatNo(Long flatNo) {
        this.flatNo = flatNo;
    }

    /**
     * @param addressInDetails
     */
    public void setAddressInDetails(String addressInDetails) {
        this.addressInDetails = addressInDetails;
    }

    /**
     * @param bldgroupCode
     */
    public void setBldgroupCode(Long bldgroupCode) {
        this.bldgroupCode = bldgroupCode;
    }

    /**
     * @param mltstatusCode
     */
    public void setMltstatusCode(Long mltstatusCode) {
        this.mltstatusCode = mltstatusCode;
    }

    /**
     * @param deathDate
     */
    public void setDeathDate(Timestamp deathDate) {
        this.deathDate = deathDate;
    }

    /**
     * @param endResidentDate
     */
    public void setEndResidentDate(Timestamp endResidentDate) {
        this.endResidentDate = endResidentDate;
    }

    /**
     * @param capstatusCode
     */
    public void setCapstatusCode(Long capstatusCode) {
        this.capstatusCode = capstatusCode;
    }

    /**
     * @param spccsetypeCode
     */
    public void setSpccsetypeCode(Long spccsetypeCode) {
        this.spccsetypeCode = spccsetypeCode;
    }

    /**
     * @param activeFlag
     */
    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
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

    public void setPersonQualificationsDTOList(List<IPersonQualificationsDTO> personQualificationsDTOList) {
        this.personQualificationsDTOList = personQualificationsDTOList;
    }

    public List<IPersonQualificationsDTO> getPersonQualificationsDTOList() {
        //        try{
        //         Collections.sort((List)personQualificationsDTOList);
        //        }catch(Throwable e){
        //            System.out.print(e.getMessage());
        //        }
        return personQualificationsDTOList;
    }

    public void setExceptionCasesDTOList(List<IRegRejectionReasonsDTO> exceptionCasesDTOList) {
        this.exceptionCasesDTOList = exceptionCasesDTOList;
    }

    public List<IRegRejectionReasonsDTO> getExceptionCasesDTOList() {
        return exceptionCasesDTOList;
    } //Added by Amir Nasr @ 25-05-2008

    public String getFullName() {
        if (fullNameColumn == null) {
            if (firstName != null) //Add family name by Taha Abdul Mejid @ 31/1/09
                return firstName + " " + secondName + " " + thirdName + " " + lastName + " " +
                    (familyName == null ? "" : familyName);
        } else {
            return fullNameColumn;
        }
        return null;
    }

    public void setSeekerLanguageSkillsDTOList(List<ISeekerLanguageSkillsDTO> seekerLanguageSkillsDTOList) {
        this.seekerLanguageSkillsDTOList = seekerLanguageSkillsDTOList;
    }

    public List<ISeekerLanguageSkillsDTO> getSeekerLanguageSkillsDTOList() {
        return seekerLanguageSkillsDTOList;
    }

    public void setPersonsInformationDTOList(List<IPersonsInformationDTO> personsInformationDTOList) {
        this.personsInformationDTOList = personsInformationDTOList;
    }

    public List<IPersonsInformationDTO> getPersonsInformationDTOList() {
        return personsInformationDTOList;
    }

    public void setKwtWorkDataDTOList(List<IKwtWorkDataDTO> kwtWorkDataDTOList) {
        this.kwtWorkDataDTOList = kwtWorkDataDTOList;
    }

    public List<IKwtWorkDataDTO> getKwtWorkDataDTOList() {
        return kwtWorkDataDTOList;
    }

    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO) {
        this.genderTypesDTO = genderTypesDTO;
    }

    public IGenderTypesDTO getGenderTypesDTO() {
        return genderTypesDTO;
    }

    public void setMaritalStatusDTO(IMaritalStatusDTO maritalStatusDTO) {
        this.maritalStatusDTO = maritalStatusDTO;
    }

    public IMaritalStatusDTO getMaritalStatusDTO() {
        return maritalStatusDTO;
    }

    public void setSpecialCaseTypesDTO(ISpecialCaseTypesDTO specialCaseTypesDTO) {
        this.specialCaseTypesDTO = specialCaseTypesDTO;
    }

    public ISpecialCaseTypesDTO getSpecialCaseTypesDTO() {
        return specialCaseTypesDTO;
    }

    public void setReligionsDTO(IReligionsDTO religionsDTO) {
        this.religionsDTO = religionsDTO;
    }

    public IReligionsDTO getReligionsDTO() {
        return religionsDTO;
    }

    public void setCountriesDTO(IGenderCountryDTO countriesDTO) {
        this.countriesDTO = countriesDTO;
    }

    public IGenderCountryDTO getCountriesDTO() {
        return countriesDTO;
    }

    public void setKwMapDTO(IKwMapDTO kwMapDTO) {
        this.kwMapDTO = kwMapDTO;
    }

    public IKwMapDTO getKwMapDTO() {
        return kwMapDTO;
    }

    public void setHandicapStatusDTO(IHandicapStatusDTO handicapStatusDTO) {
        this.handicapStatusDTO = handicapStatusDTO;
    }

    public IHandicapStatusDTO getHandicapStatusDTO() {
        return handicapStatusDTO;
    }

    public void setPersonRevisionsDTOList(List<IPersonRevisionsDTO> personRevisionsDTOList) {
        this.personRevisionsDTOList = personRevisionsDTOList;
    }

    public List<IPersonRevisionsDTO> getPersonRevisionsDTOList() {
        return personRevisionsDTOList;
    }

    public void setFullNameColumn(String fullNameColumn) {
        this.fullNameColumn = fullNameColumn;
    }

    public String getFullNameColumn() {
        return fullNameColumn;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setResidentTypeDTO(IResidentTypeDTO residentTypeDTO) {
        this.residentTypeDTO = residentTypeDTO;
    }

    public IResidentTypeDTO getResidentTypeDTO() {
        return residentTypeDTO;
    }

    public void setNonStatus(Long nonStatus) {
        this.nonStatus = nonStatus;
    }

    public Long getNonStatus() {
        return nonStatus;
    }

    public void setMaritalStatusCode(Long maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }

    public Long getMaritalStatusCode() {
        return maritalStatusCode;
    }

    public void setMobCompanyCode(Long mobCompanyCode) {
        this.mobCompanyCode = mobCompanyCode;
    }

    public Long getMobCompanyCode() {
        return mobCompanyCode;
    }

    public void setSendSmsFlag(Long sendSmsFlag) {
        this.sendSmsFlag = sendSmsFlag;
    }

    public Long getSendSmsFlag() {
        return sendSmsFlag;
    }

    public void setBloodGroupName(String bloodGroupName) {
        this.bloodGroupName = bloodGroupName;
    }

    public String getBloodGroupName() {
        return bloodGroupName;
    }

    public void setFlatType(String flatType) {
        this.flatType = flatType;
    }

    public String getFlatType() {
        return flatType;
    }

    public void setAdrsPartNo(String adrsPartNo) {
        this.adrsPartNo = adrsPartNo;
    }

    public String getAdrsPartNo() {
        return adrsPartNo;
    }

    public void setGovernrate(String governrate) {
        this.governrate = governrate;
    }

    public String getGovernrate() {
        return governrate;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    //Builder Pattern. Start
    //A.Mostafa

    public static class Builder {

        private Long civilId;
        private Long gentypeCode;
        private Long maritalStatusCode;
        private Timestamp birthDate;
        private Long spccsetypeCode;
        private String phonesNo;
        private String mobileNo;
        private String mapCode;
        private Long streetCode;
        private String buildingNo;
        private Long floorNo;
        private Long flatNo;
        private Long nonStatus;
        private Long nationality;
        private String firstName;
        private String secondName;
        private String thirdName;
        private String lastName;
        private String familyName;
        private String englishName;
        private Long capstatusCode;
        private Long religionCode;
        private Timestamp nationalityDate;
        private String eMail;
        private Long restypeCode;
        private Long passportCntryCode;
        private String passportNo;
        private Timestamp passportIssueDate;
        private Timestamp passportExpiredDate;
        private String addressInDetails;
        private Long bldgroupCode;
        private Long mltstatusCode;
        private Timestamp deathDate;
        private Timestamp endResidentDate;
        private Long activeFlag;

        //Build Illegal Accomodation

        public Builder civilId(Long civilId) {
            this.civilId = civilId;
            return this;
        }

        public Builder nationality(Long nationality) {
            this.nationality = nationality;
            return this;
        }

        public Builder gentypeCode(Long gentypeCode) {
            this.gentypeCode = gentypeCode;
            return this;
        }

        public Builder maritalStatusCode(Long maritalStatusCode) {
            this.maritalStatusCode = maritalStatusCode;
            return this;
        }

        public Builder birthDate(Timestamp birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder spccsetypeCode(Long spccsetypeCode) {
            this.spccsetypeCode = spccsetypeCode;
            return this;
        }

        public Builder phonesNo(String phonesNo) {
            this.phonesNo = phonesNo;
            return this;
        }

        public Builder mobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
            return this;
        }

        public Builder mapCode(String mapCode) {
            this.mapCode = mapCode;
            return this;
        }

        public Builder streetCode(Long streetCode) {
            this.streetCode = streetCode;
            return this;
        }

        public Builder buildingNo(String buildingNo) {
            this.buildingNo = buildingNo;
            return this;
        }

        public Builder floorNo(Long floorNo) {
            this.floorNo = floorNo;
            return this;
        }

        public Builder flatNo(Long flatNo) {
            this.flatNo = flatNo;
            return this;
        }

        public Builder nonStatus(Long nonStatus) {
            this.nonStatus = nonStatus;
            return this;
        }

        public Builder nationalityDate(Timestamp nationalityDate) {
            this.nationalityDate = nationalityDate;
            return this;
        }

        public Builder eMail(String eMail) {
            this.eMail = eMail;
            return this;
        }

        public Builder restypeCode(Long restypeCode) {
            this.restypeCode = restypeCode;
            return this;
        }

        public Builder passportCntryCode(Long passportCntryCode) {
            this.passportCntryCode = passportCntryCode;
            return this;
        }

        public Builder passportNo(String passportNo) {
            this.passportNo = passportNo;
            return this;
        }

        public Builder passportIssueDate(Timestamp passportIssueDate) {
            this.passportIssueDate = passportIssueDate;
            return this;
        }

        public Builder passportExpiredDate(Timestamp passportExpiredDate) {
            this.passportExpiredDate = passportExpiredDate;
            return this;
        }

        public Builder addressInDetails(String addressInDetails) {
            this.addressInDetails = addressInDetails;
            return this;
        }

        public Builder bldgroupCode(Long bldgroupCode) {
            this.bldgroupCode = bldgroupCode;
            return this;
        }

        public Builder mltstatusCode(Long mltstatusCode) {
            this.mltstatusCode = mltstatusCode;
            return this;
        }

        public Builder deathDate(Timestamp deathDate) {
            this.deathDate = deathDate;
            return this;
        }

        public Builder endResidentDate(Timestamp endResidentDate) {
            this.endResidentDate = endResidentDate;
            return this;
        }

        public Builder activeFlag(Long activeFlag) {
            this.activeFlag = activeFlag;
            return this;
        }

        //Build Mandatory Data

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder secondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public Builder thirdName(String thirdName) {
            this.thirdName = thirdName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder familyName(String familyName) {
            this.familyName = familyName;
            return this;
        }

        public Builder englishName(String englishName) {
            this.englishName = englishName;
            return this;
        }

        //DTOs

        public Builder capstatusCode(Long capstatusCode) {
            this.capstatusCode = capstatusCode;
            return this;
        }

        public Builder religionCode(Long religionCode) {
            this.religionCode = religionCode;
            return this;
        }

        public KwtCitizensResidentsDTO build() {
            return new KwtCitizensResidentsDTO(this);
        }

    }

    private KwtCitizensResidentsDTO(Builder builder) {

        this.civilId = builder.civilId;
        this.gentypeCode = builder.gentypeCode;
        this.maritalStatusCode = builder.maritalStatusCode;
        this.birthDate = builder.birthDate;
        this.spccsetypeCode = builder.spccsetypeCode;
        this.phonesNo = builder.phonesNo;
        this.mobileNo = builder.mobileNo;
        this.mapCode = builder.mapCode;
        this.streetCode = builder.streetCode;
        this.buildingNo = builder.buildingNo;
        this.floorNo = builder.floorNo;
        this.flatNo = builder.flatNo;
        this.nonStatus = builder.nonStatus;
        this.nationality = builder.nationality;
        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
        this.thirdName = builder.thirdName;
        this.lastName = builder.lastName;
        this.familyName = builder.familyName;
        this.englishName = builder.englishName;
        this.capstatusCode = builder.capstatusCode;
        this.religionCode = builder.religionCode;
        this.nationalityDate = builder.nationalityDate;
        this.eMail = builder.eMail;
        this.restypeCode = builder.restypeCode;
        this.passportCntryCode = builder.passportCntryCode;
        this.passportNo = builder.passportNo;
        this.passportIssueDate = builder.passportIssueDate;
        this.passportExpiredDate = builder.passportExpiredDate;
        this.addressInDetails = builder.addressInDetails;
        this.bldgroupCode = builder.bldgroupCode;
        this.mltstatusCode = builder.mltstatusCode;
        this.deathDate = builder.deathDate;
        this.endResidentDate = builder.endResidentDate;
        this.activeFlag = builder.activeFlag;


        //Set DTOs and Code
        this.setCode(new KwtCitizensResidentsEntityKey(this.civilId));
        //GenderTypesDTO
        GenderTypesDTO genderTypesDTO = new GenderTypesDTO();
        genderTypesDTO.setCode(new GenderTypesEntityKey(this.gentypeCode));
        this.setGenderTypesDTO(genderTypesDTO);

        //MaritalStatusDTO
        MaritalStatusDTO maritalStatusDTO = new MaritalStatusDTO();
        maritalStatusDTO.setCode(new MaritalStatusEntityKey(this.maritalStatusCode));
        this.setMaritalStatusDTO(maritalStatusDTO);

        //HandicapStatusDTO
        HandicapStatusDTO handicapStatusDTO = new HandicapStatusDTO();
        handicapStatusDTO.setCode(new HandicapStatusEntityKey(this.capstatusCode));
        this.setHandicapStatusDTO(handicapStatusDTO);

        //ReligionsDTO
        ReligionsDTO religionsDTO = new ReligionsDTO();
        religionsDTO.setCode(new ReligionsEntityKey(this.religionCode));
        this.setReligionsDTO(religionsDTO);

        //SpecialCaseTypesDTO
        SpecialCaseTypesDTO specialCaseTypesDTO = new SpecialCaseTypesDTO();
        specialCaseTypesDTO.setCode(new SpecialCaseTypesEntityKey(this.spccsetypeCode));
        this.setSpecialCaseTypesDTO(specialCaseTypesDTO);
    }
    //Builder Pattern. End
}
