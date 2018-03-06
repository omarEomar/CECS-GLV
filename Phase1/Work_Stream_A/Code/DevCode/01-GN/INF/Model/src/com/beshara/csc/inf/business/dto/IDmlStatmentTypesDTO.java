package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.DmlStatmentTypesEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IDmlStatmentTypesDTO extends IInfDTO {
    public Long getDmlstatypeCode();

    public String getDmlstatypeName();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setDmlstatypeCode(Long dmlstatypeCode);

    public void setDmlstatypeName(String dmlstatypeName);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
