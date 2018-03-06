package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class CountriesEntityKey extends EntityKey implements ICountriesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long cntryCode;

    public CountriesEntityKey(Long cntryCode) {
        super(new Object[] { cntryCode });
        this.cntryCode = cntryCode;
        //key=cntryCode.toString ( ) ;
    }

    public Long getCntryCode() {
        return cntryCode;
    }
}
