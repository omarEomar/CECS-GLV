package com.beshara.csc.inf.business.entity;


import com.beshara.base.enhanced.entity.BaseEntity;

import java.io.Serializable;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "PersonsInformationEntity.findAll",
                             query = "select o from PersonsInformationEntity o"),
                 @NamedQuery(name = "PersonsInformationEntity.findAllByCivilId",
                             query = "select o from PersonsInformationEntity o WHERE o.kwtCitizensResidentsEntity.civilId=:civilId"),
                 @NamedQuery(name = "PersonsInformationEntity.getByCivilId",
                             query = "select o from PersonsInformationEntity o WHERE o.kwtCitizensResidentsEntity.civilId=:civilId and o.cntqualificationCode is not null",
                             hints = { @QueryHint(name = "toplink.refresh", value = "true") }) })
@Table(name = "INF_PERSONS_INFORMATION")
@IdClass(IPersonsInformationEntityKey.class)
public class PersonsInformationEntity extends  BaseEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "CENTER_CODE", insertable = true, updatable = true)
    private Long centerCode;
    @Id
    @Column(name = "CIVIL_ID", nullable = false, insertable = false, updatable = false)
    private Long civilId;
    @Column(name = "CNTQUALIFICATION_CODE", insertable = true, updatable = true)
    private String cntqualificationCode;
    @Column(name = "CREATED_BY")
    private Long createdBy;
    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;
    @Column(name = "DEGREE", nullable = false)
    private Double degree;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @Id
    @Column(name = "REGISTRATION_DATE", nullable = false)
    private Date registrationDate;
    @Id
    @Column(name = "SOC_CODE", nullable = false, insertable = true, updatable = true)
    private Long socCode;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    @Column(name = "UNTIL_DATE")
    private Date untilDate;
    @Column(name = "GRADE_VALUE")
    private String gradeValue;
    @Column(name = "GRDTYPE_CODE", insertable = false, updatable = false)
    private Long gradeTypeCode;

    @ManyToOne
    @JoinColumn(name = "GRDTYPE_CODE", referencedColumnName = "GRDTYPE_CODE")
    private InfGradeTypesEntity gradeTypeEntity;

//    @ManyToOne
//    @JoinColumns( { @JoinColumn(name = "CNTQUALIFICATION_CODE", referencedColumnName = "CNTQUALIFICATION_CODE"),
//                    @JoinColumn(name = "CENTER_CODE", referencedColumnName = "CENTER_CODE") })
//    private CenterQualificationsEntity centerQualificationsEntity;
//    @ManyToOne
//    @JoinColumn(name = "SOC_CODE", referencedColumnName = "SOC_CODE")
//    private SocietiesEntity societiesEntity;
    @ManyToOne
    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID")
    private KwtCitizensResidentsEntity kwtCitizensResidentsEntity;


    public PersonsInformationEntity() {
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

    public String getCntqualificationCode() {
        return cntqualificationCode;
    }

    public void setCntqualificationCode(String cntqualificationCode) {
        this.cntqualificationCode = cntqualificationCode;
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

    public Double getDegree() {
        return degree;
    }

    public void setDegree(Double degree) {
        this.degree = degree;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getSocCode() {
        return socCode;
    }

    public void setSocCode(Long socCode) {
        this.socCode = socCode;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

//    public void setCenterQualificationsEntity(CenterQualificationsEntity centerQualificationsEntity) {
//        this.centerQualificationsEntity = centerQualificationsEntity;
//    }
//
//    public CenterQualificationsEntity getCenterQualificationsEntity() {
//        return centerQualificationsEntity;
//    }
//
//    public void setSocietiesEntity(SocietiesEntity societiesEntity) {
//        this.societiesEntity = societiesEntity;
//    }
//
//    public SocietiesEntity getSocietiesEntity() {
//        return societiesEntity;
//    }

    public void setKwtCitizensResidentsEntity(KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        this.kwtCitizensResidentsEntity = kwtCitizensResidentsEntity;
    }

    public KwtCitizensResidentsEntity getKwtCitizensResidentsEntity() {
        return kwtCitizensResidentsEntity;
    }

    public void setGradeValue(String gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String getGradeValue() {
        return gradeValue;
    }

    public void setGradeTypeCode(Long gradeTypeCode) {
        this.gradeTypeCode = gradeTypeCode;
    }

    public Long getGradeTypeCode() {
        return gradeTypeCode;
    }

    public void setGradeTypeEntity(InfGradeTypesEntity gradeTypeEntity) {
        this.gradeTypeEntity = gradeTypeEntity;
    }

    public InfGradeTypesEntity getGradeTypeEntity() {
        return gradeTypeEntity;
    }
}
