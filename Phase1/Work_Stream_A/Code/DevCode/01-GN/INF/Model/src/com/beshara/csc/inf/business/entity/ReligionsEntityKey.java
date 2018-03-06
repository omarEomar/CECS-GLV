package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class ReligionsEntityKey extends EntityKey implements IReligionsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long religionCode;
    public ReligionsEntityKey(Long religionCode) {        super(new Object[] { religionCode });
        this.religionCode = religionCode;
    }public int hashCode() {        return super.hashCode();
    }public Long getReligionCode() {        return religionCode;
    }}
