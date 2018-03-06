package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
import java.sql.Timestamp;
public class PersonDataChangesEntityKey extends EntityKey implements IPersonDataChangesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long parameterCode;
    private Timestamp changeDatetime;
    public PersonDataChangesEntityKey() {        super();
    }    public PersonDataChangesEntityKey(Long civilId, Long parameterCode,                                       Timestamp changeDatetime) {        super(new Object[] { civilId, parameterCode, changeDatetime });
        this.civilId = civilId;
        this.parameterCode = parameterCode;
        this.changeDatetime = changeDatetime;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getCivilId() {        return civilId;
    }    public Long getParameterCode() {        return parameterCode;
    }    public Timestamp getChangeDatetime() {        return changeDatetime;
    }}
