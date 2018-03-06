package com.beshara.csc.inf.business.dto;


public interface IHandicapStatusDTO extends IInfDTO {
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setHandicapRatio(Double handicapRatio);

    public Double getHandicapRatio();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();
    
    public void setCapstatusName(String capstatusName);

    public String getCapstatusName();

}
