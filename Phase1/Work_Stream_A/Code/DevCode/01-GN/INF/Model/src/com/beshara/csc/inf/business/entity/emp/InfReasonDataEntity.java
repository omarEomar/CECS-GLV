package com.beshara.csc.inf.business.entity.emp;


import com.beshara.base.entity.BasicEntity;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity

@NamedQueries( { 
        
        @NamedQuery(name = "InfReasonDataEntity.findAll",query = "select o from InfReasonDataEntity o order by o.resdatDesc "), 
        @NamedQuery(name = "InfReasonDataEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.InfReasonDataDTO(o.resdatSerial,o.restypeCode,o.resdatDesc) from InfReasonDataEntity o"),
        @NamedQuery(name = "InfReasonDataEntity.getReasondataByType", query = "select o from InfReasonDataEntity o where o.restypeCode=:restypeCode order by o.resdatDesc"),
         @NamedQuery(name = "ReasonDataEntity.findNewIdByType", query = "select MAX(o.resdatSerial) from InfReasonDataEntity o where o.restypeCode=:restypeCode"),  
        @NamedQuery(name = "InfReasonDataEntity.findNewId", query = "select MAX(o.resdatSerial) from InfReasonDataEntity o ")        } )

@Table(name = "HR_EMP_REASON_DATA")
@IdClass(IInfReasonDataEntityKey.class)
public class InfReasonDataEntity extends BasicEntity implements Serializable {

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
    @Column(name="RESDAT_DESC", nullable = false)
    private String resdatDesc;
    @Id
    @Column(name="RESDAT_SERIAL", nullable = false)
    private Long resdatSerial;
    @Id
    @Column(name="RESTYPE_CODE", nullable = false, updatable=false,
    insertable = false) 
    private Long restypeCode;
    @Column(name="TABREC_SERIAL")
    private Long tabrecSerial;
    
    @ManyToOne
    @JoinColumn(name = "RESTYPE_CODE", referencedColumnName = "RESTYPE_CODE")
    private InfReasonTypesEntity reasonTypesEntity;


    public InfReasonDataEntity() {
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

    public String getResdatDesc() {
        return resdatDesc;
    }

    public void setResdatDesc(String resdatDesc) {
        this.resdatDesc = resdatDesc;
    }

    public Long getResdatSerial() {
        return resdatSerial;
    }

    public void setResdatSerial(Long resdatSerial) {
        this.resdatSerial = resdatSerial;
    }

    public Long getRestypeCode() {
        return restypeCode;
    }

    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public void setReasonTypesEntity(InfReasonTypesEntity reasonTypesEntity) {
        this.reasonTypesEntity = reasonTypesEntity;
    }

    public InfReasonTypesEntity getReasonTypesEntity() {
        return reasonTypesEntity;
    }
}
