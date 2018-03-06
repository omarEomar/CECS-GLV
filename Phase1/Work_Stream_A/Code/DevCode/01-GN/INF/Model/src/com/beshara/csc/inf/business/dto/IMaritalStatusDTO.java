package com.beshara.csc.inf.business.dto;

import java.util.List;


public interface IMaritalStatusDTO extends IInfDTO {

    void setMarstatusName(String marstatusMasName);

    String getMarstatusName();

    void setAuditStatus(Long auditStatus);

    Long getAuditStatus();

    void setTabrecSerial(Long tabrecSerial);

    Long getTabrecSerial();

    public Long getMarstatusCode();
    
    public void setMarstatusCode(Long marstatusCode);

    public void setGenderMaritalDTOList(List<IGenderMaritalDTO> genderMaritalDTOList);

    public List<IGenderMaritalDTO> getGenderMaritalDTOList();


}
