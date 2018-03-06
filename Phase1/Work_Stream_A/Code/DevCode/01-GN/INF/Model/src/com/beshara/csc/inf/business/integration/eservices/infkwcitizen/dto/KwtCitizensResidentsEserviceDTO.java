package com.beshara.csc.inf.business.integration.eservices.infkwcitizen.dto;


import java.io.Serializable;

import java.sql.Date;
import java.sql.Timestamp;


public class KwtCitizensResidentsEserviceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long civilId;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String lastName;
    private String familyName;
    private String englishName;
    private Date birthDate;
    private Long gentypeCode;
    private String gentypeName;
    private Long residentTypeCode;
    private String residentTypeName;
    private String marstatusName;
    private Long marstatusCode;
    private Long religionCode;
    private String religionName;
    private Long nationality;
    private String nationalityName;
    private String phonesNo;
    private String mobileNo;
    private String eMail;
    private Long restypeCode;
    private Long passportCntryCode;
    private String passportNo;
    private String mapCode;
    private Long streetCode;
    private String buildingNo;
    private Long floorNo;
    private Long flatNo;
    private String addressInDetails;
    private Long bldgroupCode;
    private Long mltstatusCode;
    private Long capstatusCode;
    private Long spccsetypeCode;
    private Long activeFlag;
    private Long auditStatus;
    private Long tabrecSerial;
    private String fullNameColumn;
    private Long cntryCode;
    private String gencntryName;
    private Long kwMapCode;
    private String kwMapName;
    private String statusCode;
    private String statusName;
    private String cityCode;
    private String cityName;
    private String pieceName;
    private String streetName;
    
    public KwtCitizensResidentsEserviceDTO() {
        super();
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setGentypeCode(Long gentypeCode) {
        this.gentypeCode = gentypeCode;
    }

    public Long getGentypeCode() {
        return gentypeCode;
    }

    public void setGentypeName(String gentypeName) {
        this.gentypeName = gentypeName;
    }

    public String getGentypeName() {
        return gentypeName;
    }

    public void setResidentTypeCode(Long residentTypeCode) {
        this.residentTypeCode = residentTypeCode;
    }

    public Long getResidentTypeCode() {
        return residentTypeCode;
    }

    public void setResidentTypeName(String residentTypeName) {
        this.residentTypeName = residentTypeName;
    }

    public String getResidentTypeName() {
        return residentTypeName;
    }

    public void setMarstatusName(String marstatusName) {
        this.marstatusName = marstatusName;
    }

    public String getMarstatusName() {
        return marstatusName;
    }

    public void setMarstatusCode(Long marstatusCode) {
        this.marstatusCode = marstatusCode;
    }

    public Long getMarstatusCode() {
        return marstatusCode;
    }

    public void setReligionCode(Long religionCode) {
        this.religionCode = religionCode;
    }

    public Long getReligionCode() {
        return religionCode;
    }

    public void setReligionName(String religionName) {
        this.religionName = religionName;
    }

    public String getReligionName() {
        return religionName;
    }

    public void setNationality(Long nationality) {
        this.nationality = nationality;
    }

    public Long getNationality() {
        return nationality;
    }

    public void setPhonesNo(String phonesNo) {
        this.phonesNo = phonesNo;
    }

    public String getPhonesNo() {
        return phonesNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getEMail() {
        return eMail;
    }

    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
    }

    public Long getRestypeCode() {
        return restypeCode;
    }

    public void setPassportCntryCode(Long passportCntryCode) {
        this.passportCntryCode = passportCntryCode;
    }

    public Long getPassportCntryCode() {
        return passportCntryCode;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    public Long getStreetCode() {
        return streetCode;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setFloorNo(Long floorNo) {
        this.floorNo = floorNo;
    }

    public Long getFloorNo() {
        return floorNo;
    }

    public void setFlatNo(Long flatNo) {
        this.flatNo = flatNo;
    }

    public Long getFlatNo() {
        return flatNo;
    }

    public void setAddressInDetails(String addressInDetails) {
        this.addressInDetails = addressInDetails;
    }

    public String getAddressInDetails() {
        return addressInDetails;
    }

    public void setBldgroupCode(Long bldgroupCode) {
        this.bldgroupCode = bldgroupCode;
    }

    public Long getBldgroupCode() {
        return bldgroupCode;
    }

    public void setMltstatusCode(Long mltstatusCode) {
        this.mltstatusCode = mltstatusCode;
    }

    public Long getMltstatusCode() {
        return mltstatusCode;
    }

    public void setCapstatusCode(Long capstatusCode) {
        this.capstatusCode = capstatusCode;
    }

    public Long getCapstatusCode() {
        return capstatusCode;
    }

    public void setSpccsetypeCode(Long spccsetypeCode) {
        this.spccsetypeCode = spccsetypeCode;
    }

    public Long getSpccsetypeCode() {
        return spccsetypeCode;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getActiveFlag() {
        return activeFlag;
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

    public void setFullNameColumn(String fullNameColumn) {
        this.fullNameColumn = fullNameColumn;
    }

    public String getFullNameColumn() {
        return fullNameColumn;
    }

    public void setCntryCode(Long cntryCode) {
        this.cntryCode = cntryCode;
    }

    public Long getCntryCode() {
        return cntryCode;
    }

    public void setGencntryName(String gencntryName) {
        this.gencntryName = gencntryName;
    }

    public String getGencntryName() {
        return gencntryName;
    }

    public void setKwMapCode(Long kwMapCode) {
        this.kwMapCode = kwMapCode;
    }

    public Long getKwMapCode() {
        return kwMapCode;
    }

    public void setKwMapName(String kwMapName) {
        this.kwMapName = kwMapName;
    }

    public String getKwMapName() {
        return kwMapName;
    }

    void setBirthDate(Timestamp timestamp) {
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetName() {
        return streetName;
    }
}
