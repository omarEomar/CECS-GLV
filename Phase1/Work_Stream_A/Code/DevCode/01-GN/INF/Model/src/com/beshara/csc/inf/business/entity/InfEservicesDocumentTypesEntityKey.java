package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.EntityKey;

public class InfEservicesDocumentTypesEntityKey extends EntityKey implements IInfEservicesDocumentTypesEntityKey {


    @SuppressWarnings("compatibility:-1009262004819182646")
    private static final long serialVersionUID = 1L;
    
    private Long servicesId;
    private Long doctypeCode;
    public InfEservicesDocumentTypesEntityKey() {
        super();
    }

    public InfEservicesDocumentTypesEntityKey(Long doctypeCode,Long servicesId) {
        super(new Object[] { doctypeCode,servicesId });
        this.doctypeCode = doctypeCode;
        this.servicesId = servicesId;
    }

    public void setServicesId(Long servicesId) {
        this.servicesId = servicesId;
    }

    public Long getServicesId() {
        return servicesId;
    }

    public void setDoctypeCode(Long doctypeCode) {
        this.doctypeCode = doctypeCode;
    }

    public Long getDoctypeCode() {
        return doctypeCode;
    }
}
