package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

import java.sql.Date;

import com.beshara.base.entity.IEntityKey;
import com.beshara.base.dto.*;

public interface IPersonsInformationEntityKey extends IEntityKey {
    public boolean equals(Object other);

    public int hashCode();

    public Long getCivilId();

    public Date getRegistrationDate();

    public Long getSocCode();

}
