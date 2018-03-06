package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.IEntityKey;

public interface IInfEservicesDocumentTypesEntityKey extends IEntityKey {
    public int hashCode();

    public Long getServicesId();
    public Long getDoctypeCode();
}
