package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class MaritalStatusEntityKey extends EntityKey implements IMaritalStatusEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long marstatusCode;

    public MaritalStatusEntityKey(Long marstatusCode) {
        super(new Object[] { marstatusCode });
        this.marstatusCode = marstatusCode;
    }

    public Long getMarstatusCode() {
        return marstatusCode;
    }
}
