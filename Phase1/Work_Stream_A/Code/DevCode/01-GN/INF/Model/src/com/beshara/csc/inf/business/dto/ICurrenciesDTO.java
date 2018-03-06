package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.CurrenciesEntity;
import com.beshara.csc.inf.business.entity.CurrenciesEntityKey;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

public interface ICurrenciesDTO extends IInfDTO {
    public Long getCurrencyCode();

    public String getCurrencyName();

    public String getCurrencyAbrv1();

    public String getCurrencyAbrv2();

    public Long getCreatedBy();

    public Timestamp getCreatedDate();

    public Long getLastUpdatedBy();

    public Timestamp getLastUpdatedDate();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCurrencyCode(Long currencyCode);

    public void setCurrencyName(String currencyName);

    public void setCurrencyAbrv1(String currencyAbrv1);

    public void setCurrencyAbrv2(String currencyAbrv2);

    public void setCreatedBy(Long createdBy);

    public void setCreatedDate(Timestamp createdDate);

    public void setLastUpdatedBy(Long lastUpdatedBy);

    public void setLastUpdatedDate(Timestamp lastUpdatedDate);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
