package com.beshara.csc.inf.business.entity;

import com.beshara.base.enhanced.entity.BaseEntity;
import com.beshara.csc.inf.business.entity.FieldsEntity;

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
@NamedQueries( { @NamedQuery(name = "RelatedFieldsEntity.findAll", 
                             query = "select o from RelatedFieldsEntity o")
        , 
        @NamedQuery(name = "RelatedFieldsEntity.getReferenceFieldsOrdered", query = 
                    "select o from RelatedFieldsEntity o where o.fldCode = :fieldCode order by o.referOrder")
        } )
@Table(name = "INF_RELATED_FIELDS")
@IdClass(IRelatedFieldsEntityKey.class)
public class RelatedFieldsEntity extends  BaseEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;
    @Id
    @Column(name = "FLD_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long fldCode;
    @Id
    @Column(name = "FLD_CODE_REFERENCED", nullable = false, insertable = false, 
            updatable = false)
    private Long fldCodeReferenced;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @Column(name = "REFER_ORDER", nullable = false)
    private Long referOrder;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "FLD_CODE", referencedColumnName = "FLD_CODE")
    private FieldsEntity fieldEntity;
    @ManyToOne
    @JoinColumn(name = "FLD_CODE_REFERENCED", 
                referencedColumnName = "FLD_CODE")
    private FieldsEntity fieldReferencedEntity;

    public RelatedFieldsEntity() {
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

    public Long getFldCode() {
        return fldCode;
    }

    public void setFldCode(Long fldCode) {
        this.fldCode = fldCode;
    }

    public Long getFldCodeReferenced() {
        return fldCodeReferenced;
    }

    public void setFldCodeReferenced(Long fldCodeReferenced) {
        this.fldCodeReferenced = fldCodeReferenced;
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

    public Long getReferOrder() {
        return referOrder;
    }

    public void setReferOrder(Long referOrder) {
        this.referOrder = referOrder;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public void setFieldEntity(FieldsEntity fieldEntity) {
        this.fieldEntity = fieldEntity;
    }

    public FieldsEntity getFieldEntity() {
        return fieldEntity;
    }

    public void setFieldReferencedEntity(FieldsEntity fieldReferencedEntity) {
        this.fieldReferencedEntity = fieldReferencedEntity;
    }

    public FieldsEntity getFieldReferencedEntity() {
        return fieldReferencedEntity;
    }
}
