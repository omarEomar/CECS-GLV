package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.SpecialCaseTypesEntity;

public class SpecialCaseTypesDTO extends InfDTO implements ISpecialCaseTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long auditStatus;
    //private Long spccsetypeCode ; 
    private String spccsetypeName ; 
    private Long tabrecSerial;
    public SpecialCaseTypesDTO() {    }    public SpecialCaseTypesDTO(Long code, String name) {        setCode(InfEntityKeyFactory.createSpecialCaseTypesEntityKey(code));
        setName(name);
    }    public SpecialCaseTypesDTO(SpecialCaseTypesEntity specialCaseTypesEntity) {        setCode(InfEntityKeyFactory.createSpecialCaseTypesEntityKey(specialCaseTypesEntity.getSpccsetypeCode()));
        setName(specialCaseTypesEntity.getSpccsetypeName());
        this.setCreatedBy(specialCaseTypesEntity.getCreatedBy());
        this.setCreatedDate(specialCaseTypesEntity.getCreatedDate());
        this.setLastUpdatedBy(specialCaseTypesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(specialCaseTypesEntity.getLastUpdatedDate());
        this.auditStatus = specialCaseTypesEntity.getAuditStatus();
        this.tabrecSerial = specialCaseTypesEntity.getTabrecSerial();
        this.setSpccsetypeName(specialCaseTypesEntity.getSpccsetypeName());
    }    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    public Long getAuditStatus() {        return auditStatus;
    }    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public Long getTabrecSerial() {        return tabrecSerial;
    }

    public void setSpccsetypeName(String spccsetypeName) {
        this.spccsetypeName = spccsetypeName;
    }

    public String getSpccsetypeName() {
        return spccsetypeName;
    }
}
