package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.*;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.base.entity.*;

import java.io.Serializable;

public class YearsEntityKey extends EntityKey implements IYearsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long yearCode;

    public YearsEntityKey(Long yearCode) {
        super(new Object[] { yearCode });
        this.yearCode = yearCode;
    }

    public YearsEntityKey() {
    }

    public Long getYearCode() {
        return yearCode;
    }
}
