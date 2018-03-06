package com.beshara.csc.inf.business.dto.holidays;


import com.beshara.csc.inf.business.dto.IInfDTO;

import java.sql.Date;

public interface IHolidaysSimpleDTO extends IInfDTO {
    
    public void setFromDate(Date fromDate);

    public Date getFromDate() ;

    public void setUntilDate(Date untilDate) ;

    public Date getUntilDate();

    public void setStatus(Long status) ;

    public Long getStatus();

    public void setHolidayDesc(String holidayDesc);

    public String getHolidayDesc();
    
    public void setYearCode(Long yearCode) ;

    public Long getYearCode();

    public void setHoltypeCode(Long holtypeCode);

    public Long getHoltypeCode();
    
    
}
