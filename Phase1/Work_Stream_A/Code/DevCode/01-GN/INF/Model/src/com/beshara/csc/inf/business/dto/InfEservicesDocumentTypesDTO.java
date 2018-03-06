package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfEservicesDocumentTypesEntity;


public class InfEservicesDocumentTypesDTO extends InfDTO implements IInfEservicesDocumentTypesDTO {


    @SuppressWarnings("compatibility:-557498265803901703")
    private static final long serialVersionUID = 1L;

    private Long servicesId;
    private Long docTypeCode;
    private Long attachmentRequiredFlag;
    private Long tabrecSerial;
    private IInfEservicesTypesDTO infEservicesTypesDTO;
    private IInfDocumentTypesDTO infDocumentTypesDTO;

    public InfEservicesDocumentTypesDTO() {
        super();
    }


    public InfEservicesDocumentTypesDTO(InfEservicesDocumentTypesEntity documentTypesEntity) {
        setCode(InfEntityKeyFactory.createInfEservicesDocumentTypesEntityKey(documentTypesEntity.getDocTypeCode(),
                                                                             documentTypesEntity.getServicesId()));
        setAttachmentRequiredFlag(documentTypesEntity.getAttachmentRequiredFlag());
        setInfDocumentTypesDTO(new InfDocumentTypesDTO(documentTypesEntity.getInfDocumentTypesEntity()));
        setInfEservicesTypesDTO(new InfEservicesTypesDTO(documentTypesEntity.getInfEservicesTypesEntity()));
        this.tabrecSerial = documentTypesEntity.getTabrecSerial();
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
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

    public void setInfEservicesTypesDTO(IInfEservicesTypesDTO infEservicesTypesDTO) {
        this.infEservicesTypesDTO = infEservicesTypesDTO;
    }

    public IInfEservicesTypesDTO getInfEservicesTypesDTO() {
        return infEservicesTypesDTO;
    }

    public void setInfDocumentTypesDTO(IInfDocumentTypesDTO infDocumentTypesDTO) {
        this.infDocumentTypesDTO = infDocumentTypesDTO;
    }

    public IInfDocumentTypesDTO getInfDocumentTypesDTO() {
        return infDocumentTypesDTO;
    }
}
