package com.beshara.csc.inf.business.dto;

import java.util.List;


public interface IReligionsDTO extends IInfDTO {  
    
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();
    public void setGenderReligionList(List<IGenderReligionDTO> genderReligionList);

    public List<IGenderReligionDTO> getGenderReligionList() ;

}
