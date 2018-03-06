package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.LanguagesEntity;
import com.beshara.csc.inf.business.entity.LanguagesEntityKey;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface ILanguagesDTO extends IInfDTO {
    public Long getLanguageCode();

    public String getLanguageName();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setLanguageCode(Long languageCode);

    public void setLanguageName(String languageName);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
