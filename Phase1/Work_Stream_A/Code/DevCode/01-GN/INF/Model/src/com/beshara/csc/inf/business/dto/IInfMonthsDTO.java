package com.beshara.csc.inf.business.dto;

public interface IInfMonthsDTO extends IInfDTO {
    public Long getMonthCode();

    public String getMonthName();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setMonthCode(Long monthCode);

    public void setMonthName(String monthName);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
