package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.InfPersonQualificationsEntity;
import com.beshara.csc.nl.qul.business.dto.QualificationsDTO;

import java.sql.Timestamp;

import com.beshara.base.dto.*;
import com.beshara.csc.nl.qul.business.dto.IQualificationsDTO;

public interface IInfPersonQualificationsDTO extends IInfDTO {
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setCenterCode(Long centerCode);

    public Long getCenterCode();

    public void setCrsRegistrationOrder(Long crsRegistrationOrder);

    public Long getCrsRegistrationOrder();

    public void setQualificationDate(Timestamp qualificationDate);

    public Timestamp getQualificationDate();

    public void setQualificationDegree(Double qualificationDegree);

    public Double getQualificationDegree();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

    public void setQualificationsDTO(IQualificationsDTO qualificationsDTO);

    public IQualificationsDTO getQualificationsDTO();

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO);

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO();

}
