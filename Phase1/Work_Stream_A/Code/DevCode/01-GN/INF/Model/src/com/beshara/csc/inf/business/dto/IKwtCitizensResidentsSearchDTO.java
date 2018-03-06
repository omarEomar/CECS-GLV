package com.beshara.csc.inf.business.dto;


import com.beshara.base.paging.impl.PagingRequestDTO;

import java.sql.Date;


public interface IKwtCitizensResidentsSearchDTO extends IInfDTO {
    public void setCivilId(Long civilId);

    public Long getCivilId();

    public void setFirstName(String firstName);

    public String getFirstName();

    public void setSecondName(String secondName);

    public String getSecondName();

    public void setThirdName(String thirdName);

    public String getThirdName();

    public void setLastName(String lastName);

    public String getLastName();

    public void setFamilyName(String familyName);

    public String getFamilyName();

    public void setRequestDTO(IRequestDTO requestDTO);

    public IRequestDTO getRequestDTO();

    public void setBirthDate(Date birthDate);

    public Date getBirthDate();

    public void setNationality(Long nationality);

    public Long getNationality();

    public void setMaritalStatusDTO(IMaritalStatusDTO maritalStatusDTO);

    public IMaritalStatusDTO getMaritalStatusDTO();

    public void setMaritalStatusCode(Long maritalStatusCode);

    public Long getMaritalStatusCode();


    public void setNonStatus(Long nonStatus);

    public Long getNonStatus();

    public void setSpecialCaseTypesDTO(ISpecialCaseTypesDTO specialCaseTypesDTO);

    public ISpecialCaseTypesDTO getSpecialCaseTypesDTO();

    public void setPersonQualificationsDTO(IPersonQualificationsDTO personQualificationsDTO);

    public IPersonQualificationsDTO getPersonQualificationsDTO();
    
    public void setPagingRequestDTO(PagingRequestDTO pagingRequestDTO) ;

    public PagingRequestDTO getPagingRequestDTO() ;
    
    public String getFullName() ;
    
    public void setFullNameColumn(String fullNameColumn) ;

    public String getFullNameColumn() ;

}
