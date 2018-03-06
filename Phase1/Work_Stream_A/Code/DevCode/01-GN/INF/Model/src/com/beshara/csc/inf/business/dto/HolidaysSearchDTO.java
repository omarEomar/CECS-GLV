package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import java.sql.Date;
public class HolidaysSearchDTO extends InfDTO implements IHolidaysSearchDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long yearCode;
    private Long holtypeCode;
    private Date fromDate;
    private Date untilDate;
    public HolidaysSearchDTO() {        super();
    }    public void setYearCode(Long yearCode) {        this.yearCode = yearCode;
    }    public Long getYearCode() {        return yearCode;
    }    public void setHoltypeCode(Long holtypeCode) {        this.holtypeCode = holtypeCode;
    }    public Long getHoltypeCode() {        return holtypeCode;
    }    public void setFromDate(Date fromDate) {        this.fromDate = fromDate;
    }    public Date getFromDate() {        return fromDate;
    }    public void setUntilDate(Date untilDate) {        this.untilDate = untilDate;
    }    public Date getUntilDate() {        return untilDate;
    }}
