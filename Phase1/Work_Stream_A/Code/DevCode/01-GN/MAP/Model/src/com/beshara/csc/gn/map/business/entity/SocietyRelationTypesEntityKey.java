package com.beshara.csc.gn.map.business.entity;

import com.beshara.base.entity.*;

public class SocietyRelationTypesEntityKey extends EntityKey implements ISocietyRelationTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long reltypeCode;

    public SocietyRelationTypesEntityKey() {
    }

    public SocietyRelationTypesEntityKey(Long reltypeCode) {
        super(new Object[] { reltypeCode });
        this.reltypeCode = reltypeCode;
        //key=socCode.toString ( ) ;
    }

    public Long getReltypeCode() {
        return reltypeCode;
    }
}
