package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class SpecialPeriodTypesEntityKey extends EntityKey implements ISpecialPeriodTypesEntityKey{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

   
    private Long spcprdtypeCode;  
    public SpecialPeriodTypesEntityKey() {
    }
    public SpecialPeriodTypesEntityKey(Long spcprdtypeCode) {
        super(new Object[] { spcprdtypeCode });
        this.spcprdtypeCode = spcprdtypeCode;
    }

    public Long getSpcprdtypeCode() {
        return spcprdtypeCode;
    }
}
