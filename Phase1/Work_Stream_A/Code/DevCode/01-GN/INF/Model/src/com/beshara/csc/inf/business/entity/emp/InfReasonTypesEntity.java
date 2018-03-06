package com.beshara.csc.inf.business.entity.emp;


import com.beshara.base.entity.BasicEntity;

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
@NamedQueries( {
        @NamedQuery(name = "InfReasonTypesEntity.findAll",query = "select o from InfReasonTypesEntity o  order by o.restypeName") ,
        @NamedQuery(name = "InfReasonTypesEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.InfReasonTypesDTO(o.restypeCode,o.restypeName) from InfReasonTypesEntity o")
        } )
@Table(name = "HR_EMP_REASON_TYPES")
@IdClass(IInfReasonTypesEntityKey.class)
public class InfReasonTypesEntity extends BasicEntity implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name="AUDIT_STATUS")
    private Long auditStatus;
    @Column(name="CREATED_BY")
    private Long createdBy;
    @Column(name="CREATED_DATE")
    private Timestamp createdDate;
    @Column(name="LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name="LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @Id
    @Column(name="RESTYPE_CODE", nullable = false)
    private Long restypeCode;
    @Column(name="RESTYPE_NAME", nullable = false)
    private String restypeName;
    @Column(name="TABREC_SERIAL")
    private Long tabrecSerial;

    public InfReasonTypesEntity() {
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

    public Long getRestypeCode() {
        return restypeCode;
    }

    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
    }

    public String getRestypeName() {
        return restypeName;
    }

    public void setRestypeName(String restypeName) {
        this.restypeName = restypeName;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }
}
