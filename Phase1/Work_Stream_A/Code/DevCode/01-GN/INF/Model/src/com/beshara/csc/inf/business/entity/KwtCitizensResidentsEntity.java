package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "KwtCitizensResidentsEntity.findAll", 
                             query = 
                             "select o from KwtCitizensResidentsEntity o order by o.firstName ")
        , 
        @NamedQuery(name = "KwtCitizensResidentsEntity.getCitizenInformation", 
                    query = 
                    "select distinct o from KwtCitizensResidentsEntity o LEFT JOIN o.personQualificationsEntityList list Where o.civilId=:civilId AND (list.crsRegistrationOrder IS NULL OR list.crsRegistrationOrder=1)")
        , 
        @NamedQuery(name = "KwtCitizensResidentsEntity.getByCivilId", query = "select o from KwtCitizensResidentsEntity o Where o.civilId = :civilId")
        , 
        @NamedQuery(name = "KwtCitizensResidentsEntity.getCitizenName", query = 
                    "select new com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO(o.civilId, o.firstName, o.secondName, o.thirdName, o.lastName) from KwtCitizensResidentsEntity o Where o.civilId = :civilId")
        , 
        @NamedQuery(name = "KwtCitizensResidentsEntity.countCitizensWithCivil", 
                    query = 
                    "select count(o.civilId) from KwtCitizensResidentsEntity o where o.civilId = :civilId ")
        , 
        @NamedQuery(name = "KwtCitizensResidentsEntity.search", query = "select o from KwtCitizensResidentsEntity o where (:civilId is null or o.civilId = :civilId) AND (:name1 IS NULL or o.firstName LIKE :name1) AND  (:name2 IS NULL or o.secondName LIKE :name2) AND (:name3 IS NULL or o.thirdName LIKE :name3) AND (:name4 IS NULL or o.lastName LIKE :name4) AND  (:name5 IS NULL or o.familyName LIKE :name5)")
        , 
        @NamedQuery(name = "KwtCitizensResidentsEntity.getCitizenCodeFullName", query = 
                    "select new com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO(o.civilId, o.firstName, o.secondName, o.thirdName, o.lastName, o.familyName) from KwtCitizensResidentsEntity o Where o.civilId = :civilId")
       , 
        @NamedQuery(name = "KwtCitizensResidentsEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO(o.civilId,o.firstName) from KwtCitizensResidentsEntity o order by o.firstName")
        , 
        @NamedQuery(name = "KwtCitizensResidentsEntity.searchByName", query = "select o from KwtCitizensResidentsEntity o where o.firstName like :firstName order by o.firstName")
        , 
        @NamedQuery(name = "KwtCitizensResidentsEntity.searchByCode", query = "select o from KwtCitizensResidentsEntity o where o.civilId=:civilId order by o.firstName")
     
        
        
        } )
@Table(name = "INF_KWT_CITIZENS_RESIDENTS")
@IdClass(IKwtCitizensResidentsEntityKey.class)
public class KwtCitizensResidentsEntity extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Long activeFlag;
    @Column(name = "ADDRESS_IN_DETAILS")
    private String addressInDetails;
    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "BIRTH_DATE")
    private Timestamp birthDate;
    @Column(name = "BLDGROUP_CODE")
    private Long bldgroupCode;
    @Column(name = "BUILDING_NO")
    private String buildingNo;

    @Column(name = "CAPSTATUS_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long capstatusCode;
    @ManyToOne
    @JoinColumn(name = "CAPSTATUS_CODE", 
                referencedColumnName = "CAPSTATUS_CODE")
    private HandicapStatusEntity handicapStatusEntity;

    @Id
    @Column(name = "CIVIL_ID", nullable = false)
    private Long civilId;
    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;    
    
    @Column(name = "NON_STATUS")
    private Long nonStatus;    
    @Column(name = "CREATED_DATE", nullable = false)
    private Timestamp createdDate;
    @Column(name = "DEATH_DATE")
    private Timestamp deathDate;
    @Column(name = "END_RESIDENT_DATE")
    private Timestamp endResidentDate;
    @Column(name = "ENGLISH_NAME")
    private String englishName;
    @Column(name = "E_MAIL")
    private String eMail;
    @Column(name = "FAMILY_NAME")
    private String familyName;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "FLAT_NO")
    private Long flatNo;
    @Column(name = "FLOOR_NO")
    private Long floorNo;

    @Column(name = "GENTYPE_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long gentypeCode;
    @ManyToOne
    @JoinColumn(name = "GENTYPE_CODE", referencedColumnName = "GENTYPE_CODE")
    private GenderTypesEntity genderTypesEntity;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;

    @Column(name = "MAP_CODE", insertable = false, updatable = false)
    private String mapCode;
    @ManyToOne
    @JoinColumn(name = "MAP_CODE", referencedColumnName = "MAP_CODE")
    private KwMapEntity kwMapEntity;
    @Column(name = "MARSTATUS_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long marstatusCode;
    @ManyToOne
    @JoinColumn(name = "MARSTATUS_CODE", 
                referencedColumnName = "MARSTATUS_CODE")
    private MaritalStatusEntity maritalStatusEntity;

    @Column(name = "MLTSTATUS_CODE")
    private Long mltstatusCode;
    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name = "NATIONALITY", nullable = false)
    private Long nationality;
    @ManyToOne
    //@JoinColumn(name = "NATIONALITY", referencedColumnName = "CNTRY_CODE")
    @JoinColumns( { @JoinColumn(name = "GENTYPE_CODE", 
                                referencedColumnName = "GENTYPE_CODE", 
                                insertable = false, updatable = false)
            , 
            @JoinColumn(name = "NATIONALITY", referencedColumnName = "CNTRY_CODE", 
                        insertable = false, updatable = false)
            } )
    private GenderCountryEntity countriesEntity;


    @Column(name = "NATIONALITY_DATE")
    private Timestamp nationalityDate;
    @Column(name = "PASSPORT_CNTRY_CODE")
    private Long passportCntryCode;
    @Column(name = "PASSPORT_EXPIRED_DATE")
    private Timestamp passportExpiredDate;
    @Column(name = "PASSPORT_ISSUE_DATE")
    private Timestamp passportIssueDate;
    @Column(name = "PASSPORT_NO")
    private String passportNo;
    @Column(name = "PHONES_NO")
    private String phonesNo;

    @Column(name = "RELIGION_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long religionCode;
    @ManyToOne
    @JoinColumn(name = "RELIGION_CODE", referencedColumnName = "RELIGION_CODE")
    private ReligionsEntity religionsEntity;
    
    
    @Column(name = "RESTYPE_CODE", nullable = true, insertable = false, 
            updatable = false)
    private Long restypeCode;
    
    @ManyToOne
    @JoinColumn(name = "RESTYPE_CODE", referencedColumnName = "RESTYPE_CODE")
    private ResidentTypeEntity residentTypeEntity;
    
    

    
    @Column(name = "SECOND_NAME", nullable = false)
    private String secondName;
    @Column(name = "SPCCSETYPE_CODE", insertable = false, updatable = false)
    private Long spccsetypeCode;
    @ManyToOne
    @JoinColumn(name = "SPCCSETYPE_CODE", 
                referencedColumnName = "SPCCSETYPE_CODE")
    private SpecialCaseTypesEntity specialCaseTypesEntity;
    @Column(name = "STREET_CODE")
    private Long streetCode;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    @Column(name = "THIRD_NAME", nullable = false)
    private String thirdName;
    
    @Column(name = "MOBCOMPANY_CODE")
    private Long mobCompanyCode;
    
    
    @Column(name = "SEND_SMS_FLAG")
    private Long sendSmsFlag;
    /*Added By Taha Abdul Mejid*/
    @OneToMany(mappedBy = "kwtCitizensResidentsEntity")
    private List<SeekerLanguageSkillsEntity> seekerLanguageSkillsEntityList;
    @OneToMany(mappedBy = "kwtCitizensResidentsEntity")
    private List<PersonQualificationsEntity> personQualificationsEntityList;
    @OneToMany(mappedBy = "kwtCitizensResidentsEntity")
    private List<PersonsInformationEntity> personsInformationEntityList;
    @OneToMany(mappedBy = "kwtCitizensResidentsEntity")
    private List<KwtWorkDataEntity> kwtWorkDataEntityList;

    @ManyToOne
    @JoinColumn(name = "BLDGROUP_CODE", referencedColumnName = "BLDGROUP_CODE" , insertable = false, updatable = false)
    private BloodGroupsEntity bloodGroupsEntity;
    
    @Column(name = "FLAT_TYPE")
    private String flatType;
    
    @Column(name = "ADRS_PART_NO")
    private String adrsPartNo;
    
    
    public KwtCitizensResidentsEntity() {
    }

    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getAddressInDetails() {
        return addressInDetails;
    }

    public void setAddressInDetails(String addressInDetails) {
        this.addressInDetails = addressInDetails;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public Long getBldgroupCode() {
        return bldgroupCode;
    }

    public void setBldgroupCode(Long bldgroupCode) {
        this.bldgroupCode = bldgroupCode;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public Long getCapstatusCode() {
        return capstatusCode;
    }

    public void setCapstatusCode(Long capstatusCode) {
        this.capstatusCode = capstatusCode;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Timestamp deathDate) {
        this.deathDate = deathDate;
    }

    public Timestamp getEndResidentDate() {
        return endResidentDate;
    }

    public void setEndResidentDate(Timestamp endResidentDate) {
        this.endResidentDate = endResidentDate;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(Long flatNo) {
        this.flatNo = flatNo;
    }

    public Long getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Long floorNo) {
        this.floorNo = floorNo;
    }

    public Long getGentypeCode() {
        return gentypeCode;
    }

    public void setGentypeCode(Long gentypeCode) {
        this.gentypeCode = gentypeCode;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public Long getMarstatusCode() {
        return marstatusCode;
    }

    public void setMarstatusCode(Long marstatusCode) {
        this.marstatusCode = marstatusCode;
    }

    public Long getMltstatusCode() {
        return mltstatusCode;
    }

    public void setMltstatusCode(Long mltstatusCode) {
        this.mltstatusCode = mltstatusCode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Long getNationality() {
        return nationality;
    }

    public void setNationality(Long nationality) {
        this.nationality = nationality;
    }

    public Timestamp getNationalityDate() {
        return nationalityDate;
    }

    public void setNationalityDate(Timestamp nationalityDate) {
        this.nationalityDate = nationalityDate;
    }


    public Long getPassportCntryCode() {
        return passportCntryCode;
    }

    public void setPassportCntryCode(Long passportCntryCode) {
        this.passportCntryCode = passportCntryCode;
    }

    public Timestamp getPassportExpiredDate() {
        return passportExpiredDate;
    }

    public void setPassportExpiredDate(Timestamp passportExpiredDate) {
        this.passportExpiredDate = passportExpiredDate;
    }

    public Timestamp getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(Timestamp passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPhonesNo() {
        return phonesNo;
    }

    public void setPhonesNo(String phonesNo) {
        this.phonesNo = phonesNo;
    }

    public Long getReligionCode() {
        return religionCode;
    }

    public void setReligionCode(Long religionCode) {
        this.religionCode = religionCode;
    }

    public Long getRestypeCode() {
        return restypeCode;
    }

    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Long getSpccsetypeCode() {
        return spccsetypeCode;
    }

    public void setSpccsetypeCode(Long spccsetypeCode) {
        this.spccsetypeCode = spccsetypeCode;
    }

    public Long getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public void setPersonQualificationsEntityList(List<PersonQualificationsEntity> personQualificationsEntityList) {
        this.personQualificationsEntityList = personQualificationsEntityList;
    }

    public List<PersonQualificationsEntity> getPersonQualificationsEntityList() {
        return personQualificationsEntityList;
    }

    public void setSeekerLanguageSkillsEntityList(List<SeekerLanguageSkillsEntity> seekerLanguageSkillsEntityList) {
        this.seekerLanguageSkillsEntityList = seekerLanguageSkillsEntityList;
    }

    public List<SeekerLanguageSkillsEntity> getSeekerLanguageSkillsEntityList() {
        return seekerLanguageSkillsEntityList;
    }

    public void setPersonsInformationEntityList(List<PersonsInformationEntity> personsInformationEntityList) {
        this.personsInformationEntityList = personsInformationEntityList;
    }

    public List<PersonsInformationEntity> getPersonsInformationEntityList() {
        return personsInformationEntityList;
    }

    public void setKwtWorkDataEntityList(List<KwtWorkDataEntity> kwtWorkDataEntityList) {
        this.kwtWorkDataEntityList = kwtWorkDataEntityList;
    }

    public List<KwtWorkDataEntity> getKwtWorkDataEntityList() {
        return kwtWorkDataEntityList;
    }

    public void setGenderTypesEntity(GenderTypesEntity genderTypesEntity) {
        this.genderTypesEntity = genderTypesEntity;
    }

    public GenderTypesEntity getGenderTypesEntity() {
        return genderTypesEntity;
    }

    public void setMaritalStatusEntity(MaritalStatusEntity maritalStatusEntity) {
        this.maritalStatusEntity = maritalStatusEntity;
    }

    public MaritalStatusEntity getMaritalStatusEntity() {
        return maritalStatusEntity;
    }

    public void setSpecialCaseTypesEntity(SpecialCaseTypesEntity specialCaseTypesEntity) {
        this.specialCaseTypesEntity = specialCaseTypesEntity;
    }

    public SpecialCaseTypesEntity getSpecialCaseTypesEntity() {
        return specialCaseTypesEntity;
    }

    public void setHandicapStatusEntity(HandicapStatusEntity handicapStatusEntity) {
        this.handicapStatusEntity = handicapStatusEntity;
    }

    public HandicapStatusEntity getHandicapStatusEntity() {
        return handicapStatusEntity;
    }

    public void setReligionsEntity(ReligionsEntity religionsEntity) {
        this.religionsEntity = religionsEntity;
    }

    public ReligionsEntity getReligionsEntity() {
        return religionsEntity;
    }

    public void setCountriesEntity(GenderCountryEntity countriesEntity) {
        this.countriesEntity = countriesEntity;
    }

    public GenderCountryEntity getCountriesEntity() {
        return countriesEntity;
    }

    public void setKwMapEntity(KwMapEntity kwMapEntity) {
        this.kwMapEntity = kwMapEntity;
    }

    public KwMapEntity getKwMapEntity() {
        return kwMapEntity;
    }

    public void setResidentTypeEntity(ResidentTypeEntity residentTypeEntity) {
        this.residentTypeEntity = residentTypeEntity;
    }

    public ResidentTypeEntity getResidentTypeEntity() {
        return residentTypeEntity;
    }

    public void setNonStatus(Long nonStatus) {
        this.nonStatus = nonStatus;
    }

    public Long getNonStatus() {
        return nonStatus;
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

    public void setBloodGroupsEntity(BloodGroupsEntity bloodGroupsEntity) {
        this.bloodGroupsEntity = bloodGroupsEntity;
}

    public BloodGroupsEntity getBloodGroupsEntity() {
        return bloodGroupsEntity;
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
}
