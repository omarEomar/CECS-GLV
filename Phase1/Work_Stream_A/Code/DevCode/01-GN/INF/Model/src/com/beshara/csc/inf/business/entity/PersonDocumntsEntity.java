package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

import java.io.Serializable;


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
@NamedQueries( { @NamedQuery(name = "PersonDocumntsEntity.findAll", query = "select o from PersonDocumntsEntity o"), 
                 @NamedQuery(name = "PersonDocumntsEntity.getMaxId", query = "select max(o.empDocSerial) from PersonDocumntsEntity o where o.civilId = :civilId"),
                 @NamedQuery(name = "PersonDocumntsEntity.getAllByCivilId", query = "select o from PersonDocumntsEntity o where o.civilId = :civilid"),
                 @NamedQuery(name = "PersonDocumntsEntity.getAllByCivilAndDocType",
                             query = "select o from PersonDocumntsEntity o where o.civilId = :civilId and o.doctypeCode = :doctypeCode")})
@Table(name = "INF_PERSON_DOCUMENTS")
@IdClass(IPersonDocumntsEntityKey.class)
public class PersonDocumntsEntity extends BasicEntity{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CIVIL_ID", nullable = false, insertable = false, updatable = false)
    private Long civilId;
    @Id
    @Column(name = "EMPDOC_SERIAL", nullable = false)
    private Long empDocSerial;
    @Column(name = "DOCTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private String doctypeCode;
    @Column(name = "STATUS")
    private Long status;


    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "REFERENCE_TABLE_NAME")
    private String referenceTableName;
    @Column(name = "REFERENCE_TABREC_SERIAL")
    private Long referenceTableSerial;

    @ManyToOne
    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID")
    private KwtCitizensResidentsEntity kwtCitizensResidentsEntity;
    
    @ManyToOne
    @JoinColumn(name = "DOCTYPE_CODE", referencedColumnName = "DOCTYPE_CODE")
    private InfDocumentTypesEntity infDocumentTypesEntity;
    public PersonDocumntsEntity() {
    }


    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setEmpDocSerial(Long empDocSerial) {
        this.empDocSerial = empDocSerial;
    }

    public Long getEmpDocSerial() {
        return empDocSerial;
    }

    public void setDoctypeCode(String doctypeCode) {
        this.doctypeCode = doctypeCode;
    }

    public String getDoctypeCode() {
        return doctypeCode;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setReferenceTableName(String referenceTableName) {
        this.referenceTableName = referenceTableName;
    }

    public String getReferenceTableName() {
        return referenceTableName;
    }

    public void setReferenceTableSerial(Long referenceTableSerial) {
        this.referenceTableSerial = referenceTableSerial;
    }

    public Long getReferenceTableSerial() {
        return referenceTableSerial;
    }

    public void setKwtCitizensResidentsEntity(KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        this.kwtCitizensResidentsEntity = kwtCitizensResidentsEntity;
    }

    public KwtCitizensResidentsEntity getKwtCitizensResidentsEntity() {
        return kwtCitizensResidentsEntity;
    }

    public void setInfDocumentTypesEntity(InfDocumentTypesEntity infDocumentTypesEntity) {
        this.infDocumentTypesEntity = infDocumentTypesEntity;
    }

    public InfDocumentTypesEntity getInfDocumentTypesEntity() {
        return infDocumentTypesEntity;
    }
}
