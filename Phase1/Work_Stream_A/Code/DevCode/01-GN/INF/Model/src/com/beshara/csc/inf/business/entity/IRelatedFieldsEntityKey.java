package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.dto.*;

public interface IRelatedFieldsEntityKey extends IEntityKey {
    public Long getFldCode();

    public Long getFldCodeReferenced();

}
