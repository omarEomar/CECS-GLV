package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import java.io.Serializable;
public class WeekDaysEntityKey extends EntityKey implements IWeekDaysEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long daynum;
    public WeekDaysEntityKey() {        super();
    }    public WeekDaysEntityKey(Long daynum) {        super(new Object[] { daynum });
        this.daynum = daynum;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getDaynum() {        return daynum;
    }}
