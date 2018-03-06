package com.beshara.csc.inf.business.dto.holidays;


import com.beshara.csc.inf.business.dto.InfDTO;

import java.sql.Date;

public class HolidaysSimpleDTO extends InfDTO implements IHolidaysSimpleDTO {
    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    private Long yearCode;
    private Long holtypeCode;
    private Date fromDate;
    private Date untilDate;
    private Long status;
    private String holidayDesc;
    
    public HolidaysSimpleDTO() {
        super();
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    public String getHolidayDesc() {
        return holidayDesc;
    }

    public void setYearCode(Long yearCode) {
        this.yearCode = yearCode;
    }

    public Long getYearCode() {
        return yearCode;
    }

    public void setHoltypeCode(Long holtypeCode) {
        this.holtypeCode = holtypeCode;
    }

    public Long getHoltypeCode() {
        return holtypeCode;
    }
}

