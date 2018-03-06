package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.StreetZonesEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IStreetZonesDTO extends IInfDTO {
    public String getMapCode();

    public Long getStreetCode();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setMapCode(String mapCode);

    public void setStreetCode(Long streetCode);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
