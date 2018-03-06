package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.MapTypesEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IMapTypesDTO extends IInfDTO {
    public Long getTypeCode();

    public String getTypeName();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setTypeCode(Long typeCode);

    public void setTypeName(String typeName);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
