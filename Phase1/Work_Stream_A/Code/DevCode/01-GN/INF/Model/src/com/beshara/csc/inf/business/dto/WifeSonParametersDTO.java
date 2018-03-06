package com.beshara.csc.inf.business.dto;

import java.sql.Date;


public class WifeSonParametersDTO extends InfDTO implements IWifeSonParametersDTO{

    @SuppressWarnings("compatibility:-51364903653886458")
    private static final long serialVersionUID = 1L;
    
    private Long empCivilId;
    private Long barncheCivilId;
    private Long relationType;
    private Date marriageDate;
    
    public WifeSonParametersDTO(){
    }


    public void setEmpCivilId(Long empCivilId) {
        this.empCivilId = empCivilId;
    }

    public Long getEmpCivilId() {
        return empCivilId;
    }

    public void setBarncheCivilId(Long barncheCivilId) {
        this.barncheCivilId = barncheCivilId;
    }

    public Long getBarncheCivilId() {
        return barncheCivilId;
    }

    public void setRelationType(Long relationType) {
        this.relationType = relationType;
    }

    public Long getRelationType() {
        return relationType;
    }

    public void setMarriageDate(Date marriageDate) {
        this.marriageDate = marriageDate;
    }

    public Date getMarriageDate() {
        return marriageDate;
    }
}
