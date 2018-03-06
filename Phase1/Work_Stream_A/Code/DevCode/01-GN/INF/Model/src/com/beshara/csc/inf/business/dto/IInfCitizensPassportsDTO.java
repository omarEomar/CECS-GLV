package com.beshara.csc.inf.business.dto;


import com.beshara.base.dto.IBasicDTO;

import java.sql.Date;


public interface IInfCitizensPassportsDTO extends IBasicDTO {
    public Long getCivilId();

    public String getPassportNo();

    public Long getIssueCountry();

    public Date getIssueDate();

    public Date getExpDate();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCivilId(Long civilId);

    public void setPassportNo(String passportNo);

    public void setIssueCountry(Long issueCountry);

    public void setIssueDate(Date issueDate);

    public void setExpDate(Date expDate);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
