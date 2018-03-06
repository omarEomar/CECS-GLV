package com.beshara.csc.inf.business.dto;

public interface IInfGovernoratesDTO extends IInfDTO {
    public Long getGovernorateCode();

    public String getGovernorateName();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setGovernorateCode(Long governorateCode);

    public void setGovernorateName(String governorateName);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
