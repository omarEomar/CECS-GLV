package com.beshara.csc.inf.business.dto;


//import com.beshara.csc.inf.business.entity.PersonDocAtchTypesDTO;
import com.beshara.csc.flm.flm.business.dto.FileDTO;
import com.beshara.csc.inf.business.entity.PersonDocAttachemntsEntity;
import com.beshara.csc.inf.business.dto.PersonDocAtchTypesDTO;
import com.beshara.csc.inf.business.entity.IPersonDocAtchTypesEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocAttachemntsEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocumntsEntity;
import com.beshara.csc.inf.business.entity.PersonDocumntsEntityKey;

import java.sql.Date;

public class PersonDocAttachemntsDTO extends InfDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long status;
    private Date attachmentDate;
    private Long docAtcType;
    private String attachmentDesc;
    private FileDTO file;
    private Date validFrom;
    private Date validUntil;
    private PersonDocumntsDTO PersonDocumntsDTO;
    private PersonDocAtchTypesDTO personDocAtchTypesDTO; 
    private Long civilId;
    private Long documentType;

    public PersonDocAttachemntsDTO() {
    }
    

    public PersonDocAttachemntsDTO(Long civilid, Long empDocSerial, Long serial) {
        setCode(new PersonDocAttachemntsEntityKey(civilid, empDocSerial, serial));
    }


    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setAttachmentDate(Date attachmentDate) {
        this.attachmentDate = attachmentDate;
    }

    public Date getAttachmentDate() {
        return attachmentDate;
    }

    public void setDocAtcType(Long docAtcType) {
        this.docAtcType = docAtcType;
    }

    public Long getDocAtcType() {
        return docAtcType;
    }

    public void setAttachmentDesc(String attachmentDesc) {
        this.attachmentDesc = attachmentDesc;
    }

    public String getAttachmentDesc() {
        return attachmentDesc;
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

    public void setPersonDocAtchTypesDTO(PersonDocAtchTypesDTO personDocAtchTypesDTO) {
        this.personDocAtchTypesDTO = personDocAtchTypesDTO;
    }

    public PersonDocAtchTypesDTO getPersonDocAtchTypesDTO() {
        return personDocAtchTypesDTO;
    }

    public void setPersonDocumntsDTO(PersonDocumntsDTO PersonDocumntsDTO) {
        this.PersonDocumntsDTO = PersonDocumntsDTO;
    }

    public PersonDocumntsDTO getPersonDocumntsDTO() {
        return PersonDocumntsDTO;
    }

    public void setFile(FileDTO file) {
        this.file = file;
    }

    public FileDTO getFile() {
        return file;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }


    public void setDocumentType(Long documentType) {
        this.documentType = documentType;
    }

    public Long getDocumentType() {
        return documentType;
    }
}
