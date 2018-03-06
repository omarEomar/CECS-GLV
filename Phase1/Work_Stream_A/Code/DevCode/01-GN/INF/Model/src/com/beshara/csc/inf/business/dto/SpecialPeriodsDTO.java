package com.beshara.csc.inf.business.dto;


import com.beshara.csc.hr.bgt.business.dto.BgtProgramsDTO;

import java.sql.Date;


public class SpecialPeriodsDTO extends InfDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long serial;
    private Long spcprdtypeCode;
    private Long yearCode;
    private Long minCode;
    private String prgCode;
    private String periodDesc;
    private Date fromDate;
    private Date untilDate;
    private Long status;
    private BgtProgramsDTO bgtProgramsDTO;


    public SpecialPeriodsDTO() {
        super();
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSpcprdtypeCode(Long spcprdtypeCode) {
        this.spcprdtypeCode = spcprdtypeCode;
    }

    public Long getSpcprdtypeCode() {
        return spcprdtypeCode;
    }

    public void setYearCode(Long yearCode) {
        this.yearCode = yearCode;
    }

    public Long getYearCode() {
        return yearCode;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setPrgCode(String prgCode) {
        this.prgCode = prgCode;
    }

    public String getPrgCode() {
        return prgCode;
    }

    public void setPeriodDesc(String periodDesc) {
        this.periodDesc = periodDesc;
    }

    public String getPeriodDesc() {
        return periodDesc;
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

    public void setBgtProgramsDTO(BgtProgramsDTO bgtProgramsDTO) {
        this.bgtProgramsDTO = bgtProgramsDTO;
    }

    public BgtProgramsDTO getBgtProgramsDTO() {
        return bgtProgramsDTO;
    }
}
