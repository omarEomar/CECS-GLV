package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.InfDocumentTypesEntity;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;


public class InfDocumentTypesDTO extends InfDTO implements IInfDocumentTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long doctypeCode;
    private String doctypeName;
    private Long tabrecSerial;

    public InfDocumentTypesDTO() {
        super();
    }

    public  InfDocumentTypesDTO(Long doctypeCode, String doctypeName) {
        this.setCode(InfEntityKeyFactory.createInfDocumentTypesEntityKey(doctypeCode));
        this.setName(doctypeName);
    }

    public InfDocumentTypesDTO(InfDocumentTypesEntity documentTypesEntity) {
        setCode(InfEntityKeyFactory.createInfDocumentTypesEntityKey(documentTypesEntity.getDoctypeCode()));
        setName(documentTypesEntity.getDoctypeName());
        this.tabrecSerial = documentTypesEntity.getTabrecSerial();
    }

    public void setDoctypeCode(Long doctypeCode) {
        this.doctypeCode = doctypeCode;
    }

    public Long getDoctypeCode() {
        return doctypeCode;
    }

    public void setDoctypeName(String doctypeName) {
        this.doctypeName = doctypeName;
    }

    public String getDoctypeName() {
        return doctypeName;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }
}
