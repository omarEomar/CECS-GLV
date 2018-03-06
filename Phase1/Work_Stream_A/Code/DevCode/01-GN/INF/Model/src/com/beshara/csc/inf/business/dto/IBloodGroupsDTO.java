package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.BloodGroupsEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IBloodGroupsDTO extends IInfDTO {
    public Long getBldgroupCode();

    public String getBldgroupName();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setBldgroupCode(Long bldgroupCode);

    public void setBldgroupName(String bldgroupName);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
