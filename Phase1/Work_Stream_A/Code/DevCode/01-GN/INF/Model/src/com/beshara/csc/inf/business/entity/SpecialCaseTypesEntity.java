package com.beshara.csc.inf.business.entity;

import com.beshara.base.enhanced.entity.BaseEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "SpecialCaseTypesEntity.findNewId", 
                             query = "select MAX(o.spccsetypeCode) from SpecialCaseTypesEntity o")
        ,
        @NamedQuery(name = "SpecialCaseTypesEntity.findAll", query = "select o from SpecialCaseTypesEntity o order by o.spccsetypeName")
        , 
        @NamedQuery(name = "SpecialCaseTypesEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.SpecialCaseTypesDTO(o.spccsetypeCode,o.spccsetypeName) from SpecialCaseTypesEntity o order by o.spccsetypeName")
        , 
        @NamedQuery(name = "SpecialCaseTypesEntity.searchByName", query = "select o from SpecialCaseTypesEntity o where o.spccsetypeName like :name")
        ,
        @NamedQuery(name = "SpecialCaseTypesEntity.searchByCode", query = "select o from SpecialCaseTypesEntity o where o.spccsetypeCode=:spccsetypeCode order by o.spccsetypeName")
        ,
        @NamedQuery(name = "SpecialCaseTypesEntity.getByName", query = "select o from SpecialCaseTypesEntity o where o.spccsetypeName =:name")         
        } )
@Table(name = "INF_SPECIAL_CASE_TYPES")
@IdClass(ISpecialCaseTypesEntityKey.class)
public class SpecialCaseTypesEntity extends BaseEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = false)
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @Id
    @Column(name = "SPCCSETYPE_CODE", nullable = false)
    private Long spccsetypeCode;
    @Column(name = "SPCCSETYPE_NAME", nullable = false)
    private String spccsetypeName;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;

    public SpecialCaseTypesEntity() {
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
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

    public Long getSpccsetypeCode() {
        return spccsetypeCode;
    }

    public void setSpccsetypeCode(Long spccsetypeCode) {
        this.spccsetypeCode = spccsetypeCode;
    }

    public String getSpccsetypeName() {
        return spccsetypeName;
    }

    public void setSpccsetypeName(String spccsetypeName) {
        this.spccsetypeName = spccsetypeName;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }
}
