package com.beshara.csc.inf.business.dto;


public interface ISpecialCaseTypesDTO extends IInfDTO {
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();
    
    public void setSpccsetypeName(String spccsetypeName) ;

    public String getSpccsetypeName();

}
