package com.beshara.csc.inf.business.dto;


import com.beshara.csc.nl.qul.business.dto.ICentersDTO;
import com.beshara.csc.nl.qul.business.dto.IQualificationsDTO;

import java.sql.Date;


public interface IPersonQualificationsDTO extends IInfDTO {
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setCrsRegistrationOrder(Long crsRegistrationOrder);

    public Long getCrsRegistrationOrder();

    public void setQualificationDate(Date qualificationDate);

    public Date getQualificationDate();

    public void setQualificationDegree(Double qualificationDegree);

    public Double getQualificationDegree();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

    public void setQualificationsDTO(IQualificationsDTO qualificationsDTO);

    public IQualificationsDTO getQualificationsDTO();

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO);

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO();

    public void setCentersDTO(ICentersDTO centersDTO);

    public ICentersDTO getCentersDTO();

    public void setGradeValueCode(Long gradeValueCode);

    public Long getGradeValueCode();

    public void setGradeTypeCode(Long gradeTypeCode);

    public Long getGradeTypeCode();

    public String getCentersKey();

    public void setCentersKey(String key);

    public String getQualificationsKey();

    public void setQualificationsKey(String key);

    public void setGradeTypeDto(IInfGradeTypesDTO gradeTypeDto);

    public IInfGradeTypesDTO getGradeTypeDto();

    public void setGradeValue(String gradeValue);

    public String getGradeValue();
    
    public void setCurrentQual(Long currentQual);

    public Long getCurrentQual();

}
