package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.*;

public class SpecialPeriodsEntityKey extends EntityKey implements ISpecialPeriodsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;



    private Long serial;

    public SpecialPeriodsEntityKey() {
    }

    public SpecialPeriodsEntityKey(Long serial) {
        super(new Object[] { serial });
        this.serial = serial;
    }

    public Long getSerial() {
        return serial;
    }
}
