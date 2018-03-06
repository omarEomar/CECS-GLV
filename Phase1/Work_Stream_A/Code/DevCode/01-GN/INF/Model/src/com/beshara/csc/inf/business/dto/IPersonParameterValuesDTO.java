package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.PersonParameterValuesEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IPersonParameterValuesDTO extends IInfDTO {
    public Long getCivilId();

    public Long getParameterCode();

    public String getParamValue();

    public Timestamp getParamValueDate();

    public String getValueDesc();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCivilId(Long civilId);

    public void setParameterCode(Long parameterCode);

    public void setParamValue(String paramValue);

    public void setParamValueDate(Timestamp paramValueDate);

    public void setValueDesc(String valueDesc);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
