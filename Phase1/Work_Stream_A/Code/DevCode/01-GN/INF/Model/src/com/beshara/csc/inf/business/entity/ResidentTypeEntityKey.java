package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
public class ResidentTypeEntityKey extends EntityKey implements IResidentTypeEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long restypeCode;
    public ResidentTypeEntityKey(Long restypeCode) {        super(new Object[] { restypeCode });
        this.restypeCode = restypeCode;
    }    public Long getRestypeCode() {        return restypeCode;
    }}
