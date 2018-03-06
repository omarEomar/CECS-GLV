package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.InfCitizensResidentsDataEntity;
import com.beshara.csc.inf.business.entity.InfCitizensResidentsDataEntityKey;

import java.sql.Date;

import com.beshara.base.dto.*;

public interface IInfCitizensResidentsDataDTO extends IBasicDTO {
    public Long getCivilId();

    public Long getResidentNo();

    public Date getIssueDate();

    public Date getExpDate();

    public Long getRestypeCode();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCivilId(Long civilId);

    public void setResidentNo(Long residentNo);

    public void setIssueDate(Date issueDate);

    public void setExpDate(Date expDate);

    public void setRestypeCode(Long restypeCode);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
