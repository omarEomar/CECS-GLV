package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class PersonParameterValuesEntityKey extends EntityKey implements IPersonParameterValuesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    public PersonParameterValuesEntityKey() {        super(new Object[] { });
    }    public int hashCode() {        return super.hashCode();
    }    public Long getCivilId() {        return civilId;
    }}
