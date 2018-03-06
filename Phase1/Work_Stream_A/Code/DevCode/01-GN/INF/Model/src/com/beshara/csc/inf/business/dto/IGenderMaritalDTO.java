package com.beshara.csc.inf.business.dto;


public interface IGenderMaritalDTO extends IInfDTO {
    
    public Long getGentypeCode();

    public Long getMarstatusCode();

    public String getGenmarName();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setGentypeCode(Long gentypeCode);

    public void setMarstatusCode(Long marstatusCode);

    public void setGenmarName(String genmarName);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);
    
    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO) ;

    public IGenderTypesDTO getGenderTypesDTO() ;
    
    public String getGenderTypeKey() ;

    public void setGenderTypeKey(String key) ;

    public void setMaritalStatusDTO(IMaritalStatusDTO maritalStatusDTO) ;

    public IMaritalStatusDTO getMaritalStatusDTO() ;

}
