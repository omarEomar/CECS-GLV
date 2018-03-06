package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.*;

import java.io.Serializable;

public class InfOperationTypesEntityKey extends EntityKey implements IInfOperationTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long operationTypeCode;

    public InfOperationTypesEntityKey() {
        super();
    }

    public InfOperationTypesEntityKey(Long operationType) {
        super(new Object[] { operationType });
        this.operationTypeCode = operationType;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getOperationTypeCode() {
        return operationTypeCode;
    }
}
