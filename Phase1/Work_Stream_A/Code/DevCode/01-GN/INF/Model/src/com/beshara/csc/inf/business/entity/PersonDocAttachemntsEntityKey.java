package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.*;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.base.entity.*;

public class PersonDocAttachemntsEntityKey extends EntityKey implements IPersonDocAttachemntsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public Long civilId;
    public Long empDocSerial;
    public Long serial;

    public PersonDocAttachemntsEntityKey() {
    }

    public PersonDocAttachemntsEntityKey(Long civilId, Long empDocSerial, Long serial) {
        super(new Object[] { civilId, empDocSerial, serial });
        this.civilId = civilId;
        this.empDocSerial = empDocSerial;
        this.serial = serial;
    }

    public PersonDocAttachemntsEntityKey(String code) {
        super(new Object[] { code });
        String[] codeArray = code.split("\\*");
        this.civilId = Long.parseLong(codeArray[0]);
        this.empDocSerial = Long.parseLong(codeArray[1]);
        this.serial = Long.parseLong(codeArray[2]);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getCivilId() {
        return civilId;
    }

    public Long getEmpDocSerial() {
        return empDocSerial;
    }

    public Long getSerial() {
        return serial;
    }
}
