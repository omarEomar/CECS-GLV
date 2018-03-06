package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

import java.io.Serializable;

import java.sql.Timestamp;

import com.beshara.base.entity.IEntityKey;
import com.beshara.base.dto.*;

public interface IPersonDataChangesEntityKey extends IEntityKey {
    public int hashCode();

    public Long getCivilId();

    public Long getParameterCode();

    public Timestamp getChangeDatetime();

}
