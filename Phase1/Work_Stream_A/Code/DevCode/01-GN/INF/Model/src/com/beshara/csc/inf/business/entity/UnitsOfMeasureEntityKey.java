package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class UnitsOfMeasureEntityKey extends EntityKey implements IUnitsOfMeasureEntityKey {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long unitCode;

    public UnitsOfMeasureEntityKey() {
    }

    public UnitsOfMeasureEntityKey(Long unitCode) {
        super(new Object[] { unitCode });
        this.unitCode = unitCode;

    }


    public Long getUnitCode() {
        return unitCode;
    }
}
