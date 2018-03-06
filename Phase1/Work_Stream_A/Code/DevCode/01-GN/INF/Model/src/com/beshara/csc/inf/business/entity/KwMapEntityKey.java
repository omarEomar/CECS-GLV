package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.EntityKey;

public class KwMapEntityKey extends EntityKey implements IKwMapEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String mapCode;

    public KwMapEntityKey() {
    }

    public KwMapEntityKey(String mapCode) {
        super(new Object[] { mapCode });
        this.mapCode = mapCode;
        //key = mapCode ;
    }

    public String getMapCode() {
        return mapCode;
    }
}
