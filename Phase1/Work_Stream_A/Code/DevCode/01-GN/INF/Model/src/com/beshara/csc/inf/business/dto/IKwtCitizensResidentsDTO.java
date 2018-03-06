package com.beshara.csc.inf.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.crs.business.dto.IPersonRevisionsDTO;
import com.beshara.csc.hr.crs.business.dto.IRegRejectionReasonsDTO;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;

import java.sql.Timestamp;

import java.util.List;


public interface IKwtCitizensResidentsDTO extends IBasicDTO {
    public String getFirstName();

    public String getSecondName();

    public String getThirdName();

    public String getLastName();

    public String getFamilyName();

    public String getEnglishName();

    public Timestamp getBirthDate();

    public Long getGentypeCode();

    public Long getReligionCode();

    public Long getNationality();

    public void setCivilId(Long civilId);

    public Long getCivilId();

    public Timestamp getNationalityDate();

    public String getPhonesNo();

    public String getMobileNo();

    public String getEMail();

    public Long getRestypeCode();

    public Long getPassportCntryCode();

    public String getPassportNo();

    public Timestamp getPassportIssueDate();

    public Timestamp getPassportExpiredDate();

    public String getMapCode();

    public Long getStreetCode();

    public String getBuildingNo();

    public Long getFloorNo();

    public Long getFlatNo();

    public String getAddressInDetails();

    public Long getBldgroupCode();

    public Long getMltstatusCode();

    public Timestamp getDeathDate();

    public Timestamp getEndResidentDate();

    public Long getCapstatusCode();

    public Long getSpccsetypeCode();

    public Long getActiveFlag();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setFirstName(String firstName);

    public void setSecondName(String secondName);

    public void setThirdName(String thirdName);

    public void setLastName(String lastName);

    public void setFamilyName(String familyName);

    public void setEnglishName(String englishName);

    public void setBirthDate(Timestamp birthDate);

    public void setGentypeCode(Long gentypeCode);

    public void setReligionCode(Long religionCode);

    public void setNationality(Long nationality);

    public void setNationalityDate(Timestamp nationalityDate);

    public void setPhonesNo(String phonesNo);

    public void setMobileNo(String mobileNo);

    public void setEMail(String eMail);

    public void setRestypeCode(Long restypeCode);

    public void setPassportCntryCode(Long passportCntryCode);

    public void setPassportNo(String passportNo);

    public void setPassportIssueDate(Timestamp passportIssueDate);

    public void setPassportExpiredDate(Timestamp passportExpiredDate);

    public void setMapCode(String mapCode);

    public void setStreetCode(Long streetCode);

    public void setBuildingNo(String buildingNo);

    public void setFloorNo(Long floorNo);

    public void setFlatNo(Long flatNo);

    public void setAddressInDetails(String addressInDetails);

    public void setBldgroupCode(Long bldgroupCode);

    public void setMltstatusCode(Long mltstatusCode);

    public void setDeathDate(Timestamp deathDate);

    public void setEndResidentDate(Timestamp endResidentDate);

    public void setCapstatusCode(Long capstatusCode);

    public void setSpccsetypeCode(Long spccsetypeCode);

    public void setActiveFlag(Long activeFlag);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setPersonQualificationsDTOList(List<IPersonQualificationsDTO> personQualificationsDTOList);

    public List<IPersonQualificationsDTO> getPersonQualificationsDTOList();

    public void setExceptionCasesDTOList(List<IRegRejectionReasonsDTO> exceptionCasesDTOList);

    public List<IRegRejectionReasonsDTO> getExceptionCasesDTOList();

    public String getFullName();

    public void setSeekerLanguageSkillsDTOList(List<ISeekerLanguageSkillsDTO> seekerLanguageSkillsDTOList);

    public List<ISeekerLanguageSkillsDTO> getSeekerLanguageSkillsDTOList();

    public void setPersonsInformationDTOList(List<IPersonsInformationDTO> personsInformationDTOList);

    public List<IPersonsInformationDTO> getPersonsInformationDTOList();

    public void setKwtWorkDataDTOList(List<IKwtWorkDataDTO> kwtWorkDataDTOList);

    public List<IKwtWorkDataDTO> getKwtWorkDataDTOList();

    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO);

    public IGenderTypesDTO getGenderTypesDTO();

    public void setMaritalStatusDTO(IMaritalStatusDTO maritalStatusDTO);

    public IMaritalStatusDTO getMaritalStatusDTO();

    public void setSpecialCaseTypesDTO(ISpecialCaseTypesDTO specialCaseTypesDTO);

    public ISpecialCaseTypesDTO getSpecialCaseTypesDTO();

    public void setReligionsDTO(IReligionsDTO religionsDTO);

    public IReligionsDTO getReligionsDTO();

    public void setCountriesDTO(IGenderCountryDTO countriesDTO);

    public IGenderCountryDTO getCountriesDTO();

    public void setKwMapDTO(IKwMapDTO kwMapDTO);

    public IKwMapDTO getKwMapDTO();

    public void setHandicapStatusDTO(IHandicapStatusDTO handicapStatusDTO);

    public IHandicapStatusDTO getHandicapStatusDTO();

    public void setPersonRevisionsDTOList(List<IPersonRevisionsDTO> personRevisionsDTOList);

    public List<IPersonRevisionsDTO> getPersonRevisionsDTOList();

    public void setFullNameColumn(String fullNameColumn);

    public String getFullNameColumn();

    public void setNonStatus(Long nonStatus);

    public Long getNonStatus();

    public IKwtCitizensResidentsDTO getSimpleKwtCitizensResidentsDTO(KwtCitizensResidentsEntity kwtCitizensResidentsEntity);

    public void setMobCompanyCode(Long mobCompanyCode);

    public Long getMobCompanyCode();

    public void setSendSmsFlag(Long sendSmsFlag);

    public Long getSendSmsFlag();

    public void setBloodGroupName(String bloodGroupName);

    public String getBloodGroupName();

    public void setFlatType(String flatType);

    public String getFlatType();

    public void setAdrsPartNo(String adrsPartNo);

    public String getAdrsPartNo();

    public void setGovernrate(String governrate);

    public String getGovernrate();

    public void setPartNo(String partNo);

    public String getPartNo();

    public void setArea(String area);

    public String getArea();
}
