package com.beshara.csc.gn.map.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "RelationsEntity.findAll", query = "select o from RelationsEntity o") })
@Table(name = "GN_MAP_RELATIONS")
@IdClass(IRelationsEntityKey.class)
public class RelationsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
 
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @Id
    @Column(name = "OBJTYPE_CODE", nullable = false)
    private Long objtypeCode;
    @Id
    @Column(name = "SOC1_CODE", nullable = false)
    private Long soc1Code;
    @Id
    @Column(name = "SOC2_CODE", nullable = false)
    private Long soc2Code;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    
    @Column(name = "RELTYPE_CODE", nullable = false)
    private Long reltypeCode;
    
    
    public RelationsEntity() {
    }

    public RelationsEntity(Long auditStatus, Long createdBy, Timestamp createdDate, Long lastUpdatedBy,
                           Timestamp lastUpdatedDate, Long objtypeCode, Long reltypeCode,
                           Long soc1Code, Long soc2Code, Long tabrecSerial) {
        this.auditStatus = auditStatus;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDate = lastUpdatedDate;
        this.objtypeCode = objtypeCode;
        this.reltypeCode = reltypeCode;
        this.soc1Code = soc1Code;
        this.soc2Code = soc2Code;
        this.tabrecSerial = tabrecSerial;
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

    public Long getObjtypeCode() {
        return objtypeCode;
    }

    public void setObjtypeCode(Long objtypeCode) {
        this.objtypeCode = objtypeCode;
    }


    public Long getSoc1Code() {
        return soc1Code;
    }

    public void setSoc1Code(Long soc1Code) {
        this.soc1Code = soc1Code;
    }

    public Long getSoc2Code() {
        return soc2Code;
    }

    public void setSoc2Code(Long soc2Code) {
        this.soc2Code = soc2Code;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public void setReltypeCode(Long reltypeCode) {
        this.reltypeCode = reltypeCode;
    }

    public Long getReltypeCode() {
        return reltypeCode;
    }
}
