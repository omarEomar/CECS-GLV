package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
public class HandicapStatusEntityKey extends EntityKey implements IHandicapStatusEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long capstatusCode;
    public HandicapStatusEntityKey(Long capstatusCode) {        super(new Object[] { capstatusCode });
        this.capstatusCode = capstatusCode;
    }    public Long getCapstatusCode() {        return capstatusCode;
    }}
