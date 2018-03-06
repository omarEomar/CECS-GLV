package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.IEntityKey;

import java.sql.Date;


public interface IHolidaysEntityKey extends IEntityKey {
    public int hashCode();

    public Long getYearCode();

    public Long getHoltypeCode();
    
    public Date getFromDate();

}
