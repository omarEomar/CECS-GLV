package com.beshara.csc.inf.business.entity.emp;

import com.beshara.base.entity.EntityKey;

public class InfReasonTypesEntityKey extends EntityKey implements IInfReasonTypesEntityKey{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long restypeCode;
    
    public InfReasonTypesEntityKey() {
        super();
    }

    public InfReasonTypesEntityKey(Long restypeCode) {
            super(new Object[] { restypeCode });
            this.restypeCode = restypeCode;
        }

    public Long getRestypeCode() {
        return restypeCode;
    }
}
