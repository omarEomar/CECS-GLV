package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.*;

import java.io.Serializable;

public class MilitaryStatusEntityKey extends EntityKey implements IMilitaryStatusEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long mltstatusCode;

    public MilitaryStatusEntityKey() {
        super();
    }

    public MilitaryStatusEntityKey(Long mltstatusCode) {
        super(new Object[] { mltstatusCode });
        this.mltstatusCode = mltstatusCode;
    }

    public int hashCode() {
        return super.hashCode();
    }


    public Long getMltstatusCode() {
        return mltstatusCode;
    }
}
