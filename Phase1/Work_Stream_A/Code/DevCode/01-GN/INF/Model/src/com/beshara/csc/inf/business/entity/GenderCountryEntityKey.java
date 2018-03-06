package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.EntityKey;

public class GenderCountryEntityKey extends EntityKey implements IGenderCountryEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long gentypeCode;
    private Long cntryCode;

    public GenderCountryEntityKey() {
        super();
    }

    public GenderCountryEntityKey(Long gentypeCode, Long cntryCode) {
        super(new Object[] { gentypeCode, cntryCode });
        this.gentypeCode = gentypeCode;
        this.cntryCode = cntryCode;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getGentypeCode() {
        return gentypeCode;
    }

    public Long getCntryCode() {
        return cntryCode;
    }
}
