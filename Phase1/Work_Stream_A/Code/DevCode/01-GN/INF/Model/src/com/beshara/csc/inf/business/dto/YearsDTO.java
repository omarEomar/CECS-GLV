package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.YearsEntity;
import com.beshara.csc.inf.business.entity.YearsEntityKey;

import java.sql.Timestamp;

public class YearsDTO extends InfDTO implements IYearsDTO {

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


    public YearsDTO() {
    }

    public YearsDTO(Long code, String name) {
        setCode(new YearsEntityKey(code));
        setName(name);
    }

    public YearsDTO(YearsEntity yearsEntity) {
        this.setCode(new YearsEntityKey(yearsEntity.getYearCode()));
        this.setYearCode(yearsEntity.getYearCode());
        this.setYearName(yearsEntity.getYearName());
        this.setAuditStatus(yearsEntity.getAuditStatus());
        this.setBudgetEndDate(yearsEntity.getBudgetEndDate());
        this.setBudgetStartDate(yearsEntity.getBudgetStartDate());
        this.setCreatedBy(yearsEntity.getCreatedBy());
        this.setCreatedDate(yearsEntity.getCreatedDate());
        this.setEndDate(getNextDay(yearsEntity.getEndDate()));
        this.setLastUpdatedBy(yearsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(yearsEntity.getLastUpdatedDate());
        this.setStartDate(getNextDay(yearsEntity.getStartDate()));
        this.setTabrecSerial(yearsEntity.getTabrecSerial());
        setCode(new YearsEntityKey(yearsEntity.getYearCode()));
        setName(yearsEntity.getYearName());
    }
    
    
    private Timestamp getNextDay(Timestamp ts){
           long oneDay = 1 * 24 * 60 * 60 * 1000;
           // to add to the timestamp
           ts.setTime(ts.getTime() + oneDay);
           return ts;
    }
    
    public YearsDTO(YearsEntity yearsEntity , boolean simple) {
        this.setCode(new YearsEntityKey(yearsEntity.getYearCode()));
        this.setYearCode(yearsEntity.getYearCode());
        this.setYearName(yearsEntity.getYearName());
        setName(yearsEntity.getYearName());
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
}
