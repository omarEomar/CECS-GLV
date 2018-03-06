package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

import java.io.Serializable;

import com.beshara.base.entity.IEntityKey;
import com.beshara.base.dto.*;

public interface ISystemSettingsEntityKey extends IEntityKey {
    public int hashCode();

    public Long getSyssettingCode();

}
