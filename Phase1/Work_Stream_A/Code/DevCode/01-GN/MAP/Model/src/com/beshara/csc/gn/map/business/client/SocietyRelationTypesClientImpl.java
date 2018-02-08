package com.beshara.csc.gn.map.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.client.BasicClientImpl;
import com.beshara.base.deploy.BasicSession;
import com.beshara.csc.gn.map.business.deploy.SocietyRelationTypesSession;


public class SocietyRelationTypesClientImpl extends BaseClientImpl3 implements ISocietyRelationTypesClient {
    public SocietyRelationTypesClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return SocietyRelationTypesSession.class;
    }
    @Override
    protected SocietyRelationTypesSession SESSION() {
        return (SocietyRelationTypesSession)super.SESSION();
    }

}
