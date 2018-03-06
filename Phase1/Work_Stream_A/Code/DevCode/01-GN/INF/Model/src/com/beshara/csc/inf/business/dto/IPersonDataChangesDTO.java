package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.PersonDataChangesEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IPersonDataChangesDTO extends IInfDTO {
    public Long getCivilId();

    public Long getParameterCode();

    public Timestamp getChangeDatetime();

    public Long getDmlstatypeCode();

    public String getOldValue();

    public String getNewValue();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCivilId(Long civilId);

    public void setParameterCode(Long parameterCode);

    public void setChangeDatetime(Timestamp changeDatetime);

    public void setDmlstatypeCode(Long dmlstatypeCode);

    public void setOldValue(String oldValue);

    public void setNewValue(String newValue);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
