package com.beshara.csc.inf.business.integration.eservices.eservicesdocument.dto;


import java.io.Serializable;

public class EservicesDocumentDTO implements Serializable {
    @SuppressWarnings("compatibility:4952573161078329652")
    private static final long serialVersionUID = 1L;

    private Long servicesId;
    private Long docTypeCode;
    private String servicesName;
    private String docTypeName;
    private Long attachmentRequiredFlag;

    public EservicesDocumentDTO() {
        super();
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

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }

    public String getDocTypeName() {
        return docTypeName;
    }

    public void setAttachmentRequiredFlag(Long attachmentRequiredFlag) {
        this.attachmentRequiredFlag = attachmentRequiredFlag;
    }

    public Long getAttachmentRequiredFlag() {
        return attachmentRequiredFlag;
    }
}
