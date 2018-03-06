package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class InfEservicesTypesEntityKey extends EntityKey implements IInfEservicesTypesEntityKey {


    @SuppressWarnings("compatibility:-8295237758999002973")
    private static final long serialVersionUID = 1L;
    
    private Long servicesId;
    
    public InfEservicesTypesEntityKey() {
        super();
    }

    public InfEservicesTypesEntityKey(Long servicesId) {
        super(new Object[] { servicesId });
        this.servicesId = servicesId;
    }

    public void setServicesId(Long servicesId) {
        this.servicesId = servicesId;
    }

    public Long getServicesId() {
        return servicesId;
    }
}
