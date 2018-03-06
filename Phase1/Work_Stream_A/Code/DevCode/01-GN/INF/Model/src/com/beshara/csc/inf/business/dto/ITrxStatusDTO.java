package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.TrxStatusEntity;
import com.beshara.csc.inf.business.entity.TrxStatusEntityKey;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface ITrxStatusDTO extends IInfDTO {
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

}
