package com.beshara.csc.inf.business.entity;
import java.io.Serializable;
import com.beshara.base.entity.*;
import java.sql.Timestamp;
public class InfCitizensResidentsDataEntityKey extends EntityKey implements IInfCitizensResidentsDataEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long serial;
    public InfCitizensResidentsDataEntityKey() {        super();
    }    public InfCitizensResidentsDataEntityKey(Long serial) {        super(new Object[] { serial });
        this.serial = serial;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getSerial() {        return serial;
    }}
