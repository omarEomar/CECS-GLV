package com.beshara.csc.inf.business.dto;


import com.beshara.base.paging.impl.PagingRequestDTO;

import java.sql.Date;


public class KwtCitizensResidentsSearchDTO extends InfDTO implements IKwtCitizensResidentsSearchDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long civilId;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String lastName;
    private String familyName;
    private Date birthDate;
    private Long nationality;
    private Long maritalStatusCode;
    private IRequestDTO requestDTO;
    private IMaritalStatusDTO maritalStatusDTO;

    private Long nonStatus;

    private ISpecialCaseTypesDTO specialCaseTypesDTO;

    private IPersonQualificationsDTO personQualificationsDTO;

    private PagingRequestDTO pagingRequestDTO;

    private String fullNameColumn;

    public KwtCitizensResidentsSearchDTO() {
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

    public void setRequestDTO(IRequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }

    public IRequestDTO getRequestDTO() {
        return requestDTO;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setNationality(Long nationality) {
        this.nationality = nationality;
    }

    public Long getNationality() {
        return nationality;
    }

    public void setMaritalStatusDTO(IMaritalStatusDTO maritalStatusDTO) {
        this.maritalStatusDTO = maritalStatusDTO;
    }

    public IMaritalStatusDTO getMaritalStatusDTO() {
        return maritalStatusDTO;
    }

    public void setMaritalStatusCode(Long maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }

    public Long getMaritalStatusCode() {
        return maritalStatusCode;
    }

    public void setNonStatus(Long nonStatus) {
        this.nonStatus = nonStatus;
    }

    public Long getNonStatus() {
        return nonStatus;
    }


    public void setSpecialCaseTypesDTO(ISpecialCaseTypesDTO specialCaseTypesDTO) {
        this.specialCaseTypesDTO = specialCaseTypesDTO;
    }

    public ISpecialCaseTypesDTO getSpecialCaseTypesDTO() {
        return specialCaseTypesDTO;
    }

    public void setPersonQualificationsDTO(IPersonQualificationsDTO personQualificationsDTO) {
        this.personQualificationsDTO = personQualificationsDTO;
    }

    public IPersonQualificationsDTO getPersonQualificationsDTO() {
        return personQualificationsDTO;
    }

    public void setPagingRequestDTO(PagingRequestDTO pagingRequestDTO) {
        this.pagingRequestDTO = pagingRequestDTO;
    }

    public PagingRequestDTO getPagingRequestDTO() {
        return pagingRequestDTO;
    }


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


    public void setFullNameColumn(String fullNameColumn) {
        this.fullNameColumn = fullNameColumn;
    }

    public String getFullNameColumn() {
        return fullNameColumn;
    }
}
