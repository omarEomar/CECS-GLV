package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.YearsEntity;
import com.beshara.csc.inf.business.entity.YearsEntityKey;

import java.sql.Timestamp;

import com.beshara.base.dto.*;

import java.sql.Date;

public interface IYearsSearchDTO extends IInfDTO {
    public void setYearCode(Long yearCode);
    
    public void setYearName(String yearName);
    
    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setBudgetEndDate(Timestamp budgetEndDate);

    public Timestamp getBudgetEndDate();

    public void setBudgetStartDate(Timestamp budgetStartDate);

    public Timestamp getBudgetStartDate();

    public void setEndDate(Timestamp endDate);

    public Timestamp getEndDate();

    public void setStartDate(Timestamp startDate);

    public Timestamp getStartDate();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();
    
    public Long getYearCode();
    
    public String getYearName();

}
