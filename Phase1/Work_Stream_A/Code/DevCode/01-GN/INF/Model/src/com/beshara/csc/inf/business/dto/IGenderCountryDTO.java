package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.CountriesEntity;
import com.beshara.csc.inf.business.entity.GenderCountryEntity;
import com.beshara.csc.inf.business.entity.GenderCountryEntityKey;
import com.beshara.csc.inf.business.entity.GenderTypesEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface IGenderCountryDTO extends IInfDTO {
    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setCountriesDTO(ICountriesDTO countriesDTO);

    public ICountriesDTO getCountriesDTO();

    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO);

    public IGenderTypesDTO getGenderTypesDTO();

}
