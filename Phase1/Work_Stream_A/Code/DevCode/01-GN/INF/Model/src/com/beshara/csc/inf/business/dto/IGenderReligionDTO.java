package com.beshara.csc.inf.business.dto;


public interface IGenderReligionDTO extends IInfDTO {
    public Long getGentypeCode();

    public Long getReligionCode();

    public String getGenregName();
    
    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO);

    public IGenderTypesDTO getGenderTypesDTO();
    
    public String getGenderTypesKey();

    public void setGenderTypesKey(String key);

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setGentypeCode(Long gentypeCode);

    public void setReligionCode(Long religionCode);

    public void setGenregName(String genregName);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
