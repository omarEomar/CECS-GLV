package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.CountryGroupsEntity;
import com.beshara.csc.inf.business.entity.CountryGroupsEntityKey;
import com.beshara.base.dto.*;

public interface ICountryGroupsDTO extends IInfDTO {
    public Long getCntrygrpCode();

    public String getCntrygrpName();

    public Long getParentCntrygrp();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCntrygrpCode(Long cntrygrpCode);

    public void setCntrygrpName(String cntrygrpName);

    public void setParentCntrygrp(Long parentCntrygrp);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setCountryGroupsDTO(ICountryGroupsDTO countryGroupsDTO);

    public ICountryGroupsDTO getCountryGroupsDTO();

}
