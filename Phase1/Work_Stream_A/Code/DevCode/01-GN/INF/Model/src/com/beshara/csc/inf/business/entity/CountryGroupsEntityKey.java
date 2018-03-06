package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class CountryGroupsEntityKey extends EntityKey implements ICountryGroupsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long cntrygrpCode;
    public CountryGroupsEntityKey() {        super();
    }    public CountryGroupsEntityKey(Long cntrygrpCode) {        super(new Object[] { cntrygrpCode });
        this.cntrygrpCode = cntrygrpCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getCntrygrpCode() {        return cntrygrpCode;
    }}
