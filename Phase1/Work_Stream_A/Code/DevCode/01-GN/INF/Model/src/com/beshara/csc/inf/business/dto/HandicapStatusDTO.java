package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.HandicapStatusEntity;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;

public class HandicapStatusDTO extends InfDTO implements IHandicapStatusDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long auditStatus;
    private Double handicapRatio;
    private Long tabrecSerial;
    private String capstatusName;
    public HandicapStatusDTO() {    }    public HandicapStatusDTO(Long code, String name) {        setCode(InfEntityKeyFactory.createHandicapStatusEntityKey(code));
        setName(name);
    }    public HandicapStatusDTO(HandicapStatusEntity handicapStatusEntity) {        setName(handicapStatusEntity.getCapstatusName());
        setCode(InfEntityKeyFactory.createHandicapStatusEntityKey(handicapStatusEntity.getCapstatusCode()));
        this.handicapRatio = handicapStatusEntity.getHandicapRatio();
        this.setCreatedBy(handicapStatusEntity.getCreatedBy());
        this.setCreatedDate(handicapStatusEntity.getCreatedDate());
        this.setLastUpdatedBy(handicapStatusEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(handicapStatusEntity.getLastUpdatedDate());
        this.auditStatus = handicapStatusEntity.getAuditStatus();
        this.tabrecSerial = handicapStatusEntity.getTabrecSerial();
        this.setCapstatusName(handicapStatusEntity.getCapstatusName());
    }    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    public Long getAuditStatus() {        return auditStatus;
    }    public void setHandicapRatio(Double handicapRatio) {        this.handicapRatio = handicapRatio;
    }    public Double getHandicapRatio() {        return handicapRatio;
    }    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public Long getTabrecSerial() {        return tabrecSerial;
    }

    public void setCapstatusName(String capstatusName) {
        this.capstatusName = capstatusName;
    }

    public String getCapstatusName() {
        return capstatusName;
    }
}
