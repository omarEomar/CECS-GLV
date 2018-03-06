package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.*;

public class SpecialCaseTypesEntityKey extends EntityKey implements ISpecialCaseTypesEntityKey {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long spccsetypeCode;

    public SpecialCaseTypesEntityKey(Long spccsetypeCode) {
        super(new Object[] { spccsetypeCode });
        this.spccsetypeCode = spccsetypeCode;
        //key = spccsetypeCode ;
    }

    public Long getSpccsetypeCode() {
        return spccsetypeCode;
    }
}
