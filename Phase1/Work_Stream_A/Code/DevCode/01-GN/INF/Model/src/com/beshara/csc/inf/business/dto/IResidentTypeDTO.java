package com.beshara.csc.inf.business.dto;


public interface IResidentTypeDTO extends IInfDTO {
    
    public Long getResidentTypeCode();
    
    public void setResidentTypeCode(Long residentTypeCode);

    public void setResidentTypeName(String residentTypeName);
    
    public String getResidentTypeName();
    
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

}
