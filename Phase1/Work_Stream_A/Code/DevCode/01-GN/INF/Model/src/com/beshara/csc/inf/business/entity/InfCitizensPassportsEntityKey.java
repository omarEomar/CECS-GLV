package com.beshara.csc.inf.business.entity;
import java.io.Serializable;
import com.beshara.base.entity.*;
import java.sql.Timestamp;
public class InfCitizensPassportsEntityKey extends EntityKey implements IInfCitizensPassportsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long serialNo;
    public InfCitizensPassportsEntityKey() {        super();
    }    public InfCitizensPassportsEntityKey(Long serialNo) {        super(new Object[] { serialNo });
        this.serialNo = serialNo;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getSerialNo() {        return serialNo;
    }}
