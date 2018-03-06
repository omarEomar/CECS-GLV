package com.beshara.csc.inf.business.entity;

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
@NamedQueries( { @NamedQuery(name = "TrxStatusEntity.findAll", 
            query = "select o from TrxStatusEntity o order by o.trxstatusName")
              , 
        @NamedQuery(name = "TrxStatusEntity.findNewId", query = "select MAX(o.trxstatusCode) from TrxStatusEntity o")
        , 
        @NamedQuery(name = "TrxStatusEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.TrxStatusDTO(o.trxstatusCode,o.trxstatusName) from TrxStatusEntity o order by o.trxstatusName")
        , 
        @NamedQuery(name = "TrxStatusEntity.searchByName", query = "select o from TrxStatusEntity o where o.trxstatusName like :trxstatusName order by o.trxstatusName")
        , 
        @NamedQuery(name = "TrxStatusEntity.searchByCode", query = "select o from TrxStatusEntity o where o.trxstatusCode = :trxstatusCode order by o.trxstatusName")
        } )
@Table(name = "INF_TRX_STATUS")
@IdClass(ITrxStatusEntityKey.class)
public class TrxStatusEntity extends BasicEntity  implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = false)
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @Id
    @Column(name = "TRXSTATUS_CODE", nullable = false)
    private String trxstatusCode;
    @Column(name = "TRXSTATUS_NAME", nullable = false)
    private String trxstatusName;

    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;

    public TrxStatusEntity() {
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

    public String getTrxstatusCode() {
        return trxstatusCode;
    }

    public void setTrxstatusCode(String trxstatusCode) {
        this.trxstatusCode = trxstatusCode;
    }

    public String getTrxstatusName() {
        return trxstatusName;
    }

    public void setTrxstatusName(String trxstatusName) {
        this.trxstatusName = trxstatusName;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }
}
