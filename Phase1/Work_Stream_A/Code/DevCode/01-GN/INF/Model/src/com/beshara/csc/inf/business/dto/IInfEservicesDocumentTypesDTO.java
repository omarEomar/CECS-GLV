package com.beshara.csc.inf.business.dto;


public interface IInfEservicesDocumentTypesDTO extends IInfDTO {

    public Long getServicesId();

    public void setDocTypeCode(Long docTypeCode);

    public Long getDocTypeCode();

    public void setAttachmentRequiredFlag(Long attachmentRequiredFlag);

    public Long getAttachmentRequiredFlag();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

    public void setInfEservicesTypesDTO(IInfEservicesTypesDTO infEservicesTypesDTO);

    public IInfEservicesTypesDTO getInfEservicesTypesDTO();
}
