package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class BloodGroupsEntityKey extends EntityKey implements IBloodGroupsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long bldgroupCode;
    public BloodGroupsEntityKey() {        super();
    }    public BloodGroupsEntityKey(Long bldgroupCode) {        super(new Object[] { bldgroupCode });
        this.bldgroupCode = bldgroupCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getBldgroupCode() {        return bldgroupCode;
    }}
