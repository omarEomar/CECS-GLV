package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.PersonParameterValuesBkEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IPersonParameterValuesBkDTO extends IInfDTO {
    public Long getCivilId();

    public Long getParameterCode();

    public String getParamValue();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCivilId(Long civilId);

    public void setParameterCode(Long parameterCode);

    public void setParamValue(String paramValue);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
