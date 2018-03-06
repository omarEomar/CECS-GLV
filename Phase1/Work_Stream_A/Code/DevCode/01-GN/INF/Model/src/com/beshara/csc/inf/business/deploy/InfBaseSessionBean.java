package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSessionBean3;

import javax.persistence.PersistenceContext;

@PersistenceContext(unitName = "BSC", name = "em/BSC")
public abstract class InfBaseSessionBean extends BasicSessionBean3 {
    public InfBaseSessionBean() {
        super();
    }
}
