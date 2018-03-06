package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class GroupCountriesEntityKey extends EntityKey implements IGroupCountriesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long cntrygrpCode;
    private Long cntryCode;
    public GroupCountriesEntityKey() {        super();
    }    public GroupCountriesEntityKey(Long cntrygrpCode, Long cntryCode) {        super(new Object[] { cntrygrpCode, cntryCode });
        this.cntrygrpCode = cntrygrpCode;
        this.cntryCode = cntryCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getCntrygrpCode() {        return cntrygrpCode;
    }    public Long getCntryCode() {        return cntryCode;
    }}
