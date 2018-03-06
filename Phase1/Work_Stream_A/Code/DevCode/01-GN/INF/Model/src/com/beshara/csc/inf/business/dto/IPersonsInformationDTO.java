package com.beshara.csc.inf.business.dto;


import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.nl.qul.business.dto.ICenterQualificationsDTO;

import java.sql.Date;


public interface IPersonsInformationDTO extends IInfDTO {
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setDegree(Double degree);

    public Double getDegree();

    public void setRegistrationDate(Date registrationDate);

    public Date getRegistrationDate();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

    public void setUntilDate(Date untilDate);

    public Date getUntilDate();

    public void setCenterQualificationsDTO(ICenterQualificationsDTO centerQualificationsDTO);

    public ICenterQualificationsDTO getCenterQualificationsDTO();

    public void setSocietiesDTO(ISocietiesDTO societiesDTO);

    public ISocietiesDTO getSocietiesDTO();

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO);

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO();

    void setGradeTypeCode(Long gradeTypeCode);

    Long getGradeTypeCode();

    void setGradeValueCode(Long gradeValueCode);

    Long getGradeValueCode();

    public void setGradeValue(String gradeValue);

    public String getGradeValue();

    public void setGradeTypeDto(IInfGradeTypesDTO gradeTypeDto);

    public IInfGradeTypesDTO getGradeTypeDto();

    public void setPersonQualificationsDTO(IPersonQualificationsDTO personQualificationsDTO);

    public IPersonQualificationsDTO getPersonQualificationsDTO();

    public void setCntqualificationCode(String cntqualificationCode);

    public String getCntqualificationCode();

    public void setCenterCode(Long centerCode);

    public Long getCenterCode();

    public void setCntqualificationName(String cntqualificationName);

    public String getCntqualificationName();

    public void setCntryCode(Long cntryCode);

    public Long getCntryCode();

    public void setCenterName(String centerName);

    public String getCenterName();

    public void setCntryName(String cntryName);

    public String getCntryName();

}
