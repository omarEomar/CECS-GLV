package com.beshara.csc.inf.business.entity;

import java.io.Serializable;

import com.beshara.base.entity.EntityKey;

import java.sql.Timestamp;

import com.beshara.base.entity.IEntityKey;
import com.beshara.base.dto.*;

public interface IInfCitizensResidentsDataEntityKey extends IEntityKey {
    public int hashCode();

    public Long getSerial();

}
