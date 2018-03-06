package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "PersonDocAttachemntsEntity.findAll",
                             query = "select o from PersonDocAttachemntsEntity o"),
                 @NamedQuery(name = "PersonDocAttachemntsEntity.getMaxId",
                             query = "select MAX(o.serial) from PersonDocAttachemntsEntity o where o.civilId = :civilId"),
                 @NamedQuery(name = "PersonDocAttachemntsEntity.getAllByCivilAndDocType",
                             query = "select o from PersonDocAttachemntsEntity o where o.personDocumntsEntity.civilId = :civilId and o.personDocumntsEntity.doctypeCode = :doctypeCode") })
@Table(name = "INF_PERSON_DOC_ATTACHMENTS")
@IdClass(IPersonDocAttachemntsEntityKey.class)
public class PersonDocAttachemntsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CIVIL_ID", nullable = false, insertable = false, updatable = false)
    private Long civilId;
    @Id
    @Column(name = "EMPDOC_SERIAL", nullable = false, insertable = false, updatable = false)
    private Long empDocSerial;
    @Id
    @Column(name = "SERIAL", nullable = false)
    private Long serial;
    @Column(name = "STATUS", nullable = false)
    private Long status;
    @Column(name = "ATTACHMENT_DATE", nullable = false)
    private Date attachmentDate;
    @Column(name = "DOCATCTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long docAtcType;
    @Column(name = "ATTACHMENT_DESC")
    private String attachmentDesc;
    @Column(name = "ATTACHMENT_FILE")
    private String fileId;
    @Column(name = "VALID_FROM")
    private Date validFrom;
    @Column(name = "VALID_UNTIL")
    private Date validUntil;
    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID"),
                    @JoinColumn(name = "EMPDOC_SERIAL", referencedColumnName = "EMPDOC_SERIAL") })
    private PersonDocumntsEntity personDocumntsEntity;

    @ManyToOne
    @JoinColumn(name = "DOCATCTYPE_CODE", referencedColumnName = "DOCATCTYPE_CODE")
    private PersonDocAtchTypesEntity personDocAtchTypesEntity;

    public PersonDocAttachemntsEntity() {
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


    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public Long getSerial() {
        return serial;
    }

    public void setAttachmentDate(Date attachmentDate) {
        this.attachmentDate = attachmentDate;
    }

    public Date getAttachmentDate() {
        return attachmentDate;
    }


    public void setAttachmentDesc(String attachmentDesc) {
        this.attachmentDesc = attachmentDesc;
    }

    public String getAttachmentDesc() {
        return attachmentDesc;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setPersonDocumntsEntity(PersonDocumntsEntity personDocumntsEntity) {
        this.personDocumntsEntity = personDocumntsEntity;
    }

    public PersonDocumntsEntity getPersonDocumntsEntity() {
        return personDocumntsEntity;
    }

    public void setPersonDocAtchTypesEntity(PersonDocAtchTypesEntity personDocAtchTypesEntity) {
        this.personDocAtchTypesEntity = personDocAtchTypesEntity;
    }

    public PersonDocAtchTypesEntity getPersonDocAtchTypesEntity() {
        return personDocAtchTypesEntity;
    }

    public void setDocAtcType(Long docAtcType) {
        this.docAtcType = docAtcType;
    }

    public Long getDocAtcType() {
        return docAtcType;
    }
}
