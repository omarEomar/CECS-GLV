package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.DecisionMakerTypesEntity;
import com.beshara.csc.inf.business.entity.DecisionMakerTypesEntityKey;

public class DecisionMakerTypesDTO extends InfDTO implements IDecisionMakerTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @SuppressWarnings("compatibility:5639369220282382427")
    private Long decmkrtypeCode;
    private String decmkrtypeName ;
    private Long auditStatus;
    private Long tabrecSerial;

    public DecisionMakerTypesDTO() {
        super();
    }

    public DecisionMakerTypesDTO(Long code, String name) {
        this.setCode(new DecisionMakerTypesEntityKey(code));
        setName(name);
    }

    public DecisionMakerTypesDTO(DecisionMakerTypesEntity decisionMakerTypesEntity) {
        setCode(new DecisionMakerTypesEntityKey(decisionMakerTypesEntity.getDecmkrtypeCode()));
        this.decmkrtypeCode = decisionMakerTypesEntity.getDecmkrtypeCode ( ) ; 
        this.decmkrtypeName = decisionMakerTypesEntity.getDecmkrtypeName ( ) ; 
        setName(decisionMakerTypesEntity.getDecmkrtypeName());
        this.setDecmkrtypeName ( decisionMakerTypesEntity.getDecmkrtypeName ( ) ) ; 
        this.setCreatedBy(decisionMakerTypesEntity.getCreatedBy());
        this.setCreatedDate(decisionMakerTypesEntity.getCreatedDate());
        this.setLastUpdatedBy(decisionMakerTypesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(decisionMakerTypesEntity.getLastUpdatedDate());
        this.auditStatus = decisionMakerTypesEntity.getAuditStatus();
        this.tabrecSerial = decisionMakerTypesEntity.getTabrecSerial();
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

    public String getLockEntity() {
        return "INF_DECISION_MAKER_TYPES";
    }

    public String getLockId() {
        return tabrecSerial.toString();
    }

    public void setDecmkrtypeCode(Long decmkrtypeCode) {
        this.decmkrtypeCode = decmkrtypeCode;
    }

    public Long getDecmkrtypeCode() {
        return decmkrtypeCode;
    }

    public void setDecmkrtypeName(String decmkrtypeName) {
        this.decmkrtypeName = decmkrtypeName;
    }

    public String getDecmkrtypeName() {
        return decmkrtypeName;
    }
}
