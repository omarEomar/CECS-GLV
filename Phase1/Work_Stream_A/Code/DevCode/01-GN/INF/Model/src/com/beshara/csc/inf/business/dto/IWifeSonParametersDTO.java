package com.beshara.csc.inf.business.dto;

import java.sql.Date;


public interface IWifeSonParametersDTO extends IInfDTO{
    

    public void setEmpCivilId(Long empCivilId);

    public Long getEmpCivilId();

    public void setBarncheCivilId(Long barncheCivilId);

    public Long getBarncheCivilId();

    public void setRelationType(Long relationType);

    public Long getRelationType();

    public void setMarriageDate(Date marriageDate);

    public Date getMarriageDate();
}
