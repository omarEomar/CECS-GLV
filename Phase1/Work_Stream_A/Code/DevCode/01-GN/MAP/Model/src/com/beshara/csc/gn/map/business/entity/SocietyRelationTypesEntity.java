package com.beshara.csc.gn.map.business.entity;


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
@NamedQueries( { @NamedQuery(name = "SocietyRelationTypesEntity.findAll",
                             query = "select o from SocietyRelationTypesEntity o") })
@Table(name = "GN_MAP_REL_TYPES")
@IdClass(ISocietyRelationTypesEntityKey.class)
public class SocietyRelationTypesEntity extends BasicEntity {

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
    @Column(name = "RELTYPE_CODE", nullable = false)
    private Long reltypeCode;
    @Column(name = "RELTYPE_NAME", nullable = false, length = 200)
    private String reltypeName;
    @Column(name = "RELTYPE_SYMBOLE", nullable = false, length = 20)
    private String reltypeSymbole;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;


    public SocietyRelationTypesEntity() {
    }

    public SocietyRelationTypesEntity(Long auditStatus, Long createdBy, Timestamp createdDate, Long lastUpdatedBy,
                                      Timestamp lastUpdatedDate, Long reltypeCode, String reltypeName,
                                      String reltypeSymbole, Long tabrecSerial) {
        this.auditStatus = auditStatus;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDate = lastUpdatedDate;
        this.reltypeCode = reltypeCode;
        this.reltypeName = reltypeName;
        this.reltypeSymbole = reltypeSymbole;
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

    public Long getReltypeCode() {
        return reltypeCode;
    }

    public void setReltypeCode(Long reltypeCode) {
        this.reltypeCode = reltypeCode;
    }

    public String getReltypeName() {
        return reltypeName;
    }

    public void setReltypeName(String reltypeName) {
        this.reltypeName = reltypeName;
    }

    public String getReltypeSymbole() {
        return reltypeSymbole;
    }

    public void setReltypeSymbole(String reltypeSymbole) {
        this.reltypeSymbole = reltypeSymbole;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


}
