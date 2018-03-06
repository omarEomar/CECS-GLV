package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.LanguagesEntity;
import com.beshara.csc.inf.business.entity.SeekerLanguageSkillsEntity;
import com.beshara.csc.inf.business.entity.SeekerLanguageSkillsEntityKey;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface ISeekerLanguageSkillsDTO extends IInfDTO {
    public Long getCivilId();

    public Long getLanguageCode();

    public String getSkillDegree();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCivilId(Long civilId);

    public void setLanguageCode(Long languageCode);

    public void setSkillDegree(String skillDegree);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO);

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO();

    public void setLanguagesDTO(ILanguagesDTO languagesDTO);

    public ILanguagesDTO getLanguagesDTO();

}
