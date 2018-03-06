package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.EntityKey;

import java.sql.Date;


public class HolidaysEntityKey extends EntityKey implements IHolidaysEntityKey {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long yearCode;
    private Long holtypeCode;
    private Date fromDate;

    public HolidaysEntityKey() {
        super();
    }

    public HolidaysEntityKey(Long yearCode, Long holtypeCode , Date fromDate) {
        super(new Object[] { yearCode, holtypeCode ,fromDate});
        this.yearCode = yearCode;
        this.holtypeCode = holtypeCode;
        this.fromDate = fromDate;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getYearCode() {
        return yearCode;
    }

    public Long getHoltypeCode() {
        return holtypeCode;
    }

    public Date getFromDate() {
        return fromDate;
    }
}
