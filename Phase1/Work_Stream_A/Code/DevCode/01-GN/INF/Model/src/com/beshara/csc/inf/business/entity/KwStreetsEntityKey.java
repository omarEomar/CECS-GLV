package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
public class KwStreetsEntityKey extends EntityKey implements IKwStreetsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long streetCode;
    public KwStreetsEntityKey(Long streetCode) {        super(new Object[] { streetCode });
        this.streetCode = streetCode;
    }    public Long getStreetCode() {        return streetCode;
    }}
