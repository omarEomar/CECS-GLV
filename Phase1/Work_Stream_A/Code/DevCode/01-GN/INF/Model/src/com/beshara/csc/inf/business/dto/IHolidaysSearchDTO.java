package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;

import java.sql.Date;

import com.beshara.base.dto.*;

public interface IHolidaysSearchDTO extends IInfDTO {
    public void setYearCode(Long yearCode);

    public Long getYearCode();

    public void setHoltypeCode(Long holtypeCode);

    public Long getHoltypeCode();

    public void setFromDate(Date fromDate);

    public Date getFromDate();

    public void setUntilDate(Date untilDate);

    public Date getUntilDate();

}
