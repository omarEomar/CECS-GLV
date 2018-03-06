package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntity;
import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntityKey;

public class PersonDocAtchTypesDTO extends InfDTO{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

   private String docAtcTypeName;
    public PersonDocAtchTypesDTO() {
      
    }
    public PersonDocAtchTypesDTO(PersonDocAtchTypesEntity ent) {
        setCode(new PersonDocAtchTypesEntityKey(ent.getDocAtcTypeCode()));
       
    }
    public PersonDocAtchTypesDTO(Long docAtcTypeCode) {
        setCode(new PersonDocAtchTypesEntityKey(docAtcTypeCode));

    }

    public void setDocAtcTypeName(String docAtcTypeName) {
        this.docAtcTypeName = docAtcTypeName;
    }

    public String getDocAtcTypeName() {
        return docAtcTypeName;
    }
}
