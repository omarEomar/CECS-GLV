package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.CurrenciesEntityKey;
import com.beshara.csc.inf.business.entity.YearsEntity;
import com.beshara.csc.inf.business.entity.YearsEntityKey;

import java.sql.Date;
import java.sql.Timestamp;

public class YearsSearchDTO extends InfDTO implements IYearsSearchDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    private Long yearCode;
    private String yearName;
    private Long auditStatus;
    private Timestamp budgetEndDate;
    private Timestamp budgetStartDate;
    private Timestamp endDate;
    private Timestamp startDate;
    private Long tabrecSerial;


    public YearsSearchDTO() {
    }


    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setBudgetEndDate(Timestamp budgetEndDate) {
        this.budgetEndDate = budgetEndDate;
    }

    public Timestamp getBudgetEndDate() {
        return budgetEndDate;
    }

    public void setBudgetStartDate(Timestamp budgetStartDate) {
        this.budgetStartDate = budgetStartDate;
    }

    public Timestamp getBudgetStartDate() {
        return budgetStartDate;
    }



    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setYearCode(Long yearCode) {
        this.yearCode = yearCode;
    }

    public Long getYearCode() {
        return yearCode;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public String getYearName() {
        return yearName;
    }


    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }
}
