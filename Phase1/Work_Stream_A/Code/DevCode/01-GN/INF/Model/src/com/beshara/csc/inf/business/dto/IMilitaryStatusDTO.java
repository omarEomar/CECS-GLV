package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.MilitaryStatusEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IMilitaryStatusDTO extends IInfDTO {
    public Long getMltstatusCode();

    public String getMltstatusName();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setMltstatusCode(Long mltstatusCode);

    public void setMltstatusName(String mltstatusName);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
