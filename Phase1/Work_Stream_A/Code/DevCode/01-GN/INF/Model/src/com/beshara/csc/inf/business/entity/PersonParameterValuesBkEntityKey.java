package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class PersonParameterValuesBkEntityKey extends EntityKey implements IPersonParameterValuesBkEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long parameterCode;
    public PersonParameterValuesBkEntityKey() {        super();
    }    public PersonParameterValuesBkEntityKey(Long civilId, Long parameterCode) {        super(new Object[] { civilId, parameterCode });
        this.civilId = civilId;
        this.parameterCode = parameterCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getCivilId() {        return civilId;
    }    public Long getParameterCode() {        return parameterCode;
    }}
