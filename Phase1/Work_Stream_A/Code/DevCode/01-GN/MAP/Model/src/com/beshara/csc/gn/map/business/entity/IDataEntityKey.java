package com.beshara.csc.gn.map.business.entity;

import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.dto.*;

public interface IDataEntityKey extends IEntityKey {
    public Long getObjtypeCode();

    public Long getSocCode();

}
