package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class HolidayTypesEntityKey extends EntityKey implements IHolidayTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long holtypeCode;
    public HolidayTypesEntityKey() {        super();
    }    public HolidayTypesEntityKey(Long holtypeCode) {        super(new Object[] { holtypeCode });
        this.holtypeCode = holtypeCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getHoltypeCode() {        return holtypeCode;
    }}
