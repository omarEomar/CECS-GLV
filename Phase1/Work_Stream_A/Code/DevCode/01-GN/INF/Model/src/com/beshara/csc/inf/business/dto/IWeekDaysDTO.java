package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.WeekDaysEntity;
import com.beshara.csc.inf.business.entity.WeekDaysEntityKey;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IWeekDaysDTO extends IInfDTO {
    public Long getCreatedBy();

    public Timestamp getCreatedDate();

    public Long getLastUpdatedBy();

    public Timestamp getLastUpdatedDate();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCreatedBy(Long createdBy);

    public void setCreatedDate(Timestamp createdDate);

    public void setLastUpdatedBy(Long lastUpdatedBy);

    public void setLastUpdatedDate(Timestamp lastUpdatedDate);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
