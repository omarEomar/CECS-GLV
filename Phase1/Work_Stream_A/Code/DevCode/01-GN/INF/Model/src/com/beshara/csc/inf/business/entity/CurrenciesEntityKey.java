package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;


public class CurrenciesEntityKey extends EntityKey implements ICurrenciesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long currencyCode;

    public CurrenciesEntityKey(Long currencyCode) {
        super(new Object[] { currencyCode });
        this.currencyCode = currencyCode;
    }

    public Long getCurrencyCode() {
        return currencyCode;
    }
}
