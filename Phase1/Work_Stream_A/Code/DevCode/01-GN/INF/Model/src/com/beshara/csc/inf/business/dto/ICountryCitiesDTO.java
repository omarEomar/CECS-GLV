package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.CountryCitiesEntity;
import com.beshara.csc.inf.business.entity.CountryCitiesEntityKey;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface ICountryCitiesDTO extends IInfDTO {
    public Long getCntryCode();

    public Long getCntrycityCode();

    public String getCntrycityName();

    public Long getCapitalFlag();

    public Long getLongitude();

    public Long getLatitude();

    public Long getCreatedBy();

    public Timestamp getCreatedDate();

    public Long getLastUpdatedBy();

    public Timestamp getLastUpdatedDate();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCntryCode(Long cntryCode);

    public void setCntrycityCode(Long cntrycityCode);

    public void setCntrycityName(String cntrycityName);

    public void setCapitalFlag(Long capitalFlag);

    public void setLongitude(Long longitude);

    public void setLatitude(Long latitude);

    public void setCreatedBy(Long createdBy);

    public void setCreatedDate(Timestamp createdDate);

    public void setLastUpdatedBy(Long lastUpdatedBy);

    public void setLastUpdatedDate(Timestamp lastUpdatedDate);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setCountriesDTO(ICountriesDTO countriesDTO);

    public ICountriesDTO getCountriesDTO();

    public boolean isBooleanCapitalFlag();

    public void setBooleanCapitalFlag(boolean capitalFlag);

}
