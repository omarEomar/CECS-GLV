package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class InfDocumentTypesEntityKey extends EntityKey implements IInfDocumentTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long doctypeCode;

    public InfDocumentTypesEntityKey() {
        super();
    }

    public InfDocumentTypesEntityKey(Long doctypeCode) {
        super(new Object[] { doctypeCode });
        this.doctypeCode = doctypeCode;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getDoctypeCode() {
        return doctypeCode;
    }
}
