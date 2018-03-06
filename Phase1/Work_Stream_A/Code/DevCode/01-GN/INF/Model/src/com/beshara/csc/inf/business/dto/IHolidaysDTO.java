package com.beshara.csc.inf.business.dto;


import java.sql.Date;


public interface IHolidaysDTO extends IInfDTO {
    public Long getStatus();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setStatus(Long status);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setHolidayTypesDTO(IHolidayTypesDTO holidayTypesDTO);

    public IHolidayTypesDTO getHolidayTypesDTO();

    public void setYearsDTO(IYearsDTO yearsDTO);

    public IYearsDTO getYearsDTO();

    public void setFromDate(Date fromDate);

    public Date getFromDate();

    public void setUntilDate(Date untilDate);

    public Date getUntilDate();

    public void setStatusBoolean(boolean statusBoolean);

    public boolean isStatusBoolean();
    
    public void setHolidayDesc(String holidayDesc);

    public String getHolidayDesc();

}
