package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.SystemSettingsEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface ISystemSettingsDTO extends IInfDTO {
    public Long getSyssettingCode();

    public String getSyssettingName();

    public String getSyssettingValue();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setSyssettingCode(Long syssettingCode);

    public void setSyssettingName(String syssettingName);

    public void setSyssettingValue(String syssettingValue);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
