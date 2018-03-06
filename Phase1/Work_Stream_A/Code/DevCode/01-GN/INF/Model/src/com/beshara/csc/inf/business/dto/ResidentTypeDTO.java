package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.ResidentTypeEntity;

public class ResidentTypeDTO extends InfDTO implements IResidentTypeDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

        
    private Long residentTypeCode;
    private String residentTypeName;
    private Long auditStatus;
    private Long tabrecSerial;
    
    public ResidentTypeDTO() {  
        super();  
    }    
    
    public ResidentTypeDTO(Long code, String name) {        
        setCode(InfEntityKeyFactory.createResidentTypeEntityKey(code));
           setName(name);
    }  
    
    public ResidentTypeDTO(ResidentTypeEntity residentTypeEntity) {  
        this.residentTypeCode = residentTypeEntity.getRestypeCode();
        setCode(InfEntityKeyFactory.createResidentTypeEntityKey(residentTypeEntity.getRestypeCode()));
        setName(residentTypeEntity.getRestypeName());
        this.residentTypeName = residentTypeEntity.getRestypeName();
        this.setCreatedBy(residentTypeEntity.getCreatedBy());
        this.setCreatedDate(residentTypeEntity.getCreatedDate());
        this.setLastUpdatedBy(residentTypeEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(residentTypeEntity.getLastUpdatedDate());
        this.auditStatus = residentTypeEntity.getAuditStatus();
        this.tabrecSerial = residentTypeEntity.getTabrecSerial();
    }     
    public void setAuditStatus(Long auditStatus) {      
        this.auditStatus = auditStatus;
    }    
    public Long getAuditStatus() {        
        return auditStatus;
    }    
    public void setTabrecSerial(Long tabrecSerial) {        
        this.tabrecSerial = tabrecSerial;
    }    
    public Long getTabrecSerial() {        
        return tabrecSerial;
    }

    public void setResidentTypeCode(Long residentTypeCode) {
        this.residentTypeCode = residentTypeCode;
    }

    public Long getResidentTypeCode() {
        return residentTypeCode;
    }

    public void setResidentTypeName(String residentTypeName) {
        this.residentTypeName = residentTypeName;
    }

    public String getResidentTypeName() {
        return residentTypeName;
    }
}
