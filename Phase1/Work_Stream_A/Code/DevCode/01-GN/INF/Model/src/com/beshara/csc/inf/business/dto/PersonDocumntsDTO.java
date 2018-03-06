package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.PersonDocumntsEntity;
import com.beshara.csc.inf.business.entity.PersonDocumntsEntityKey;

public class PersonDocumntsDTO extends InfDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String doctypeCode;
    private Long status;
    private String comments;
    private String referenceTableName;
    private Long referenceTableSerial;
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO;
    private InfDocumentTypesDTO infDocumentTypesDTO; 
    private String doctypeName;

    public PersonDocumntsDTO(PersonDocumntsEntity PersonDocumnts) {
        setCode(new PersonDocumntsEntityKey(PersonDocumnts.getCivilId(), PersonDocumnts.getEmpDocSerial()));
        this.doctypeCode = PersonDocumnts.getDoctypeCode();
        this.comments = PersonDocumnts.getComments();
        this.referenceTableName = PersonDocumnts.getReferenceTableName();
        this.referenceTableSerial = PersonDocumnts.getReferenceTableSerial();
       
        this.kwtCitizensResidentsDTO = InfDTOFactory.createKwtCitizensResidentsDTO(PersonDocumnts.getKwtCitizensResidentsEntity());
        this.infDocumentTypesDTO = (InfDocumentTypesDTO)InfDTOFactory.createInfDocumentTypesDTO(PersonDocumnts.getInfDocumentTypesEntity());
        this.status = PersonDocumnts.getStatus();
        if(PersonDocumnts != null && PersonDocumnts.getInfDocumentTypesEntity() != null){
            this.setDoctypeName(PersonDocumnts.getInfDocumentTypesEntity().getDoctypeName());
        }
    }

    public PersonDocumntsDTO(Long civilid, Long empDocSerial) {
        setCode(new PersonDocumntsEntityKey(civilid, empDocSerial));
    }

    public PersonDocumntsDTO() {
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

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {
        return kwtCitizensResidentsDTO;
    }

    public void setInfDocumentTypesDTO(InfDocumentTypesDTO infDocumentTypesDTO) {
        this.infDocumentTypesDTO = infDocumentTypesDTO;
    }

    public InfDocumentTypesDTO getInfDocumentTypesDTO() {
        return infDocumentTypesDTO;
    }
    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setDoctypeName(String doctypeName) {
        this.doctypeName = doctypeName;
    }

    public String getDoctypeName() {
        return doctypeName;
    }
}
