package com.beshara.csc.gn.map.business.deploy;


import com.beshara.base.deploy.BasicSessionBean3;

import javax.persistence.PersistenceContext;

@PersistenceContext(unitName = "MAP", name = "em/MAP")
public abstract class MapBaseSessionBean extends BasicSessionBean3 {
    public MapBaseSessionBean() {
        super();
    }
}
