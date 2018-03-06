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
@NamedQueries( { @NamedQuery(name = "InfEservicesDocumentTypesEntity.findAll",
                             query = "select o from InfEservicesDocumentTypesEntity o"),
                 @NamedQuery(name = "InfEservicesDocumentTypesEntity.getByServicesId",
                             query = "select new com.beshara.csc.inf.business.dto.InfEservicesDocumentTypesDTO(o) from InfEservicesDocumentTypesEntity o where o.servicesId= :servicesId") })
@Table(name = "INF_ESERVICES_DOCUMENT_TYPES")
@IdClass(IInfEservicesDocumentTypesEntityKey.class)
public class InfEservicesDocumentTypesEntity extends BasicEntity implements Serializable {

    @SuppressWarnings("compatibility:3589739014193081283")
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "SERVICES_ID", nullable = true, insertable = false, updatable = false)
    private Long servicesId;
    @Id
    @Column(name = "DOCTYPE_CODE", nullable = true, insertable = false, updatable = false)
    private Long docTypeCode;
    @Column(name = "ATTACHMENT_REQUIRD_FLAG", nullable = false)
    private Long attachmentRequiredFlag;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;

    @ManyToOne
    @JoinColumn(name = "DOCTYPE_CODE", referencedColumnName = "DOCTYPE_CODE")
    private InfDocumentTypesEntity infDocumentTypesEntity;

    @ManyToOne
    @JoinColumn(name = "SERVICES_ID", referencedColumnName = "SERVICES_ID")
    private InfEservicesTypesEntity infEservicesTypesEntity;

    public InfEservicesDocumentTypesEntity() {
    }

    public void setServicesId(Long servicesId) {
        this.servicesId = servicesId;
    }

    public Long getServicesId() {
        return servicesId;
    }

    public void setDocTypeCode(Long docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public Long getDocTypeCode() {
        return docTypeCode;
    }

    public void setAttachmentRequiredFlag(Long attachmentRequiredFlag) {
        this.attachmentRequiredFlag = attachmentRequiredFlag;
    }

    public Long getAttachmentRequiredFlag() {
        return attachmentRequiredFlag;
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

    public void setInfDocumentTypesEntity(InfDocumentTypesEntity infDocumentTypesEntity) {
        this.infDocumentTypesEntity = infDocumentTypesEntity;
    }

    public InfDocumentTypesEntity getInfDocumentTypesEntity() {
        return infDocumentTypesEntity;
    }

    public void setInfEservicesTypesEntity(InfEservicesTypesEntity infEservicesTypesEntity) {
        this.infEservicesTypesEntity = infEservicesTypesEntity;
    }

    public InfEservicesTypesEntity getInfEservicesTypesEntity() {
        return infEservicesTypesEntity;
    }
}
