package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.dto.*;

public interface IPersonDocAttachemntsEntityKey extends IEntityKey {

    public int hashCode();

    public Long getCivilId();

    public Long getEmpDocSerial();

    public Long getSerial();
}