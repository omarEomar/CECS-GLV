package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.IEntityKey;

public interface IInfDocumentTypesEntityKey extends IEntityKey {
    public int hashCode();

    public Long getDoctypeCode();
}
