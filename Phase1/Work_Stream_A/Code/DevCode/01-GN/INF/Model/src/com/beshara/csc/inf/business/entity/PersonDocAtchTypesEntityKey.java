package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class PersonDocAtchTypesEntityKey extends EntityKey implements IPersonDocAtchTypesEntityKey{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long docAtcTypeCode;
    public PersonDocAtchTypesEntityKey() {
       
    }
    public PersonDocAtchTypesEntityKey(Long docAtcTypeCode) {
        super(new Object[] { docAtcTypeCode});
        this.docAtcTypeCode = docAtcTypeCode;
    }


    public int hashCode() {
        return super.hashCode();
    }

    public Long getDocAtcTypeCode() {
        return docAtcTypeCode;
    }

}
