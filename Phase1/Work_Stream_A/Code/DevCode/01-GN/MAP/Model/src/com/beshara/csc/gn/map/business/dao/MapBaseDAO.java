package com.beshara.csc.gn.map.business.dao;

import com.beshara.base.dao.BaseDAO;

public abstract class MapBaseDAO extends BaseDAO {
    public MapBaseDAO() {
        super();
    }

    @Override
    protected String getEMJNDI() {
        return "em/MAP";
    }
}
