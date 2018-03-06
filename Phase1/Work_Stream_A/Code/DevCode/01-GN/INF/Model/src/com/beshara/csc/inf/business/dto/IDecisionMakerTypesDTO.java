package com.beshara.csc.inf.business.dto;


public interface IDecisionMakerTypesDTO extends IInfDTO {
    
    public void setDecmkrtypeCode(Long decmkrtypeCode );
    
    public void setDecmkrtypeName (String decmkrtypeName );
    
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();
    
    public Long getDecmkrtypeCode(  );
    
    public String getDecmkrtypeName();
    
    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

}
