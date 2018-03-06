package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class InfMonthsEntityKey extends EntityKey implements IInfMonthsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @SuppressWarnings("compatibility:-2075460043738349614")
    private Long monthCode;
    public InfMonthsEntityKey() {        super();
    }    public InfMonthsEntityKey(Long monthCode) {        super(new Object[] { monthCode });
        this.monthCode = monthCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getMonthCode() {        return monthCode;
    }}
