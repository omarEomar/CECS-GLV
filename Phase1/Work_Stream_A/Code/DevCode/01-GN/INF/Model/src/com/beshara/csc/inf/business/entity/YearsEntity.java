package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "YearsEntity.findAll",
                             query = "select o from YearsEntity o order by o.yearCode desc"),
                 @NamedQuery(name = "YearsEntity.findNewId", query = "select MAX(o.yearCode) from YearsEntity o"),
                 @NamedQuery(name = "YearsEntity.getCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.YearsDTO(o.yearCode, o.yearName) from YearsEntity o order by o.yearCode desc"),
                 @NamedQuery(name = "YearsEntity.getCodeNameUntil",
                             query = "select new com.beshara.csc.inf.business.dto.YearsDTO(o.yearCode, o.yearName) from YearsEntity o where o.yearCode <= :yearCode order by o.yearCode desc"),
                 @NamedQuery(name = "YearsEntity.getYearByName",
                             query = "select o from YearsEntity o where o.yearName = :name"),
                 @NamedQuery(name = "YearsEntity.getYearCountByName",
                             query = "select count (o.yearName) from YearsEntity o where o.yearName = :yearName"),
                 @NamedQuery(name = "YearsEntity.getCodeNameAfterDate",
                             query = "select new com.beshara.csc.inf.business.dto.YearsDTO(o.yearCode, o.yearName) from YearsEntity o where o.startDate > :date order by o.yearCode desc")})

@Table(name = "INF_YEARS")
@IdClass(IYearsEntityKey.class)
public class YearsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "BUDGET_END_DATE", nullable = false)
    private Timestamp budgetEndDate;
    @Column(name = "BUDGET_START_DATE", nullable = false)
    private Timestamp budgetStartDate;
    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = false)
    private Timestamp createdDate;
    @Column(name = "END_DATE", nullable = false)
    private Timestamp endDate;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @Column(name = "START_DATE", nullable = false)
    private Timestamp startDate;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    @Id
    @Column(name = "YEAR_CODE", nullable = false)
    private Long yearCode;
    @Column(name = "YEAR_NAME", nullable = false)
    private String yearName;
//    @OneToMany(mappedBy = "yearsEntity")
//    private List<DecisionsEntity> decisionsEntityList;

    public YearsEntity() {
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }


    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }


    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }


    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getYearCode() {
        return yearCode;
    }

    public void setYearCode(Long yearCode) {
        this.yearCode = yearCode;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

//    public List<DecisionsEntity> getDecisionsEntityList() {
//        return decisionsEntityList;
//    }
//
//    public void setDecisionsEntityList(List<DecisionsEntity> decisionsEntityList) {
//        this.decisionsEntityList = decisionsEntityList;
//    }


    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
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
}

