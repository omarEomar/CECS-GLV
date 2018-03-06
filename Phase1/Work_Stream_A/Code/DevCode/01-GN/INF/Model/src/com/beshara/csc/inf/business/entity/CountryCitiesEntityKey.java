package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class CountryCitiesEntityKey extends EntityKey implements ICountryCitiesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long cntryCode;
    private Long cntrycityCode;
    public CountryCitiesEntityKey() {        super();
    }    public CountryCitiesEntityKey(Long cntryCode, Long cntrycityCode) {        super(new Object[] { cntryCode, cntrycityCode });
        this.cntryCode = cntryCode;
        this.cntrycityCode = cntrycityCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getCntryCode() {        return cntryCode;
    }    public Long getCntrycityCode() {        return cntrycityCode;
    }}
