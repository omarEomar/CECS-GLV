package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.CountriesEntity;
import com.beshara.csc.inf.business.entity.CountryGroupsEntity;
import com.beshara.csc.inf.business.entity.GroupCountriesEntity;
import com.beshara.csc.inf.business.entity.GroupCountriesEntityKey;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IGroupCountriesDTO extends IInfDTO {
    public Long getCntrygrpCode();

    public Long getCntryCode();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCntrygrpCode(Long cntrygrpCode);

    public void setCntryCode(Long cntryCode);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setCountriesDTO(ICountriesDTO countriesDTO);

    public ICountriesDTO getCountriesDTO();

    public void setCountryGroupsDTO(ICountryGroupsDTO countryGroupsDTO);

    public ICountryGroupsDTO getCountryGroupsDTO();

}
