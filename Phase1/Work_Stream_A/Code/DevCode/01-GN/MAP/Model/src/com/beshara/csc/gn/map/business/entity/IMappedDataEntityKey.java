package com.beshara.csc.gn.map.business.entity;

import com.beshara.base.entity.EntityKey;

import java.io.Serializable;

import com.beshara.base.entity.IEntityKey;
import com.beshara.base.dto.*;

public interface IMappedDataEntityKey extends IEntityKey {
    public Long getObjtype1Code();

    public Long getObjtype2Code();

    public Long getSoc1Code();

    public String getSoc1Value();

    public Long getSoc2Code();

    public String getSoc2Value();

}
