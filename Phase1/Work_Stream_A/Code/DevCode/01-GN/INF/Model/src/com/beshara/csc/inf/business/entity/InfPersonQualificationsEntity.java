package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.inf.business.entity.qul.InfQualificationsEntity;

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
@NamedQueries( { @NamedQuery(name = "InfPersonQualificationsEntity.findAll", 
                             query = 
                             "select o from InfPersonQualificationsEntity o")
        , 
        @NamedQuery(name = "InfPersonQualificationsEntity.getJobSeekerQualifications", 
                    query = 
                    "select o from InfPersonQualificationsEntity o where o.crsRegistrationOrder is not null and o.kwtCitizensResidentsEntity.civilId =:civilId")
        } )
@Table(name = "INF_PERSON_QUALIFICATIONS")
@IdClass(IInfPersonQualificationsEntityKey.class)
public class InfPersonQualificationsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "CENTER_CODE")
    private Long centerCode;
    @Id
    @Column(name = "CIVIL_ID", nullable = false, insertable = false, 
            updatable = false)
    private Long civilId;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;
    @Column(name = "CRS_REGISTRATION_ORDER")
    private Long crsRegistrationOrder;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @Column(name = "QUALIFICATION_DATE", nullable = false)
    private Timestamp qualificationDate;
    @Column(name = "QUALIFICATION_DEGREE", nullable = false)
    private Double qualificationDegree;
    @Id
    @Column(name = "QUALIFICATION_KEY", nullable = false, insertable = true, 
            updatable = true)
    private String qualificationKey;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "QUALIFICATION_KEY", 
                referencedColumnName = "QUALIFICATION_KEY", insertable = false, 
                updatable = false)
    private InfQualificationsEntity qualificationsEntity;

    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID")
    private KwtCitizensResidentsEntity kwtCitizensResidentsEntity;

    public InfPersonQualificationsEntity() {
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(Long centerCode) {
        this.centerCode = centerCode;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
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

    public Long getCrsRegistrationOrder() {
        return crsRegistrationOrder;
    }

    public void setCrsRegistrationOrder(Long crsRegistrationOrder) {
        this.crsRegistrationOrder = crsRegistrationOrder;
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

    public Timestamp getQualificationDate() {
        return qualificationDate;
    }

    public void setQualificationDate(Timestamp qualificationDate) {
        this.qualificationDate = qualificationDate;
    }

    public Double getQualificationDegree() {
        return qualificationDegree;
    }

    public void setQualificationDegree(Double qualificationDegree) {
        this.qualificationDegree = qualificationDegree;
    }

    public String getQualificationKey() {
        return qualificationKey;
    }

    public void setQualificationKey(String qualificationKey) {
        this.qualificationKey = qualificationKey;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public void setKwtCitizensResidentsEntity(KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        this.kwtCitizensResidentsEntity = kwtCitizensResidentsEntity;
    }

    public KwtCitizensResidentsEntity getKwtCitizensResidentsEntity() {
        return kwtCitizensResidentsEntity;
    }

    public void setQualificationsEntity(InfQualificationsEntity qualificationsEntity) {
        this.qualificationsEntity = qualificationsEntity;
    }

    public InfQualificationsEntity getQualificationsEntity() {
        return qualificationsEntity;
    }
}

