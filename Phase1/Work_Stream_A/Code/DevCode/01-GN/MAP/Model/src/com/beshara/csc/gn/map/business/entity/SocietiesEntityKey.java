package com.beshara.csc.gn.map.business.entity;

import com.beshara.base.entity.*;

public class SocietiesEntityKey extends EntityKey implements ISocietiesEntityKey {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long socCode;

    public SocietiesEntityKey() {
    }

    public SocietiesEntityKey(Long socCode) {
        super(new Object[] { socCode });
        this.socCode = socCode;
        //key=socCode.toString ( ) ;
    }

    public Long getSocCode() {
        return socCode;
    }
}
