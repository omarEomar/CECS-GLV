package com.beshara.csc.inf.business.client;

import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.csc.inf.business.deploy.PersonDocAtchTypesSession;
import com.beshara.csc.inf.business.deploy.PersonDocAttachemntsSession;

public class PersonDocAtchTypesClientImpl extends BaseClientImpl3 implements IPersonDocAtchTypesClient{
    public PersonDocAtchTypesClientImpl() {
        super();
    }
    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return PersonDocAtchTypesSession.class;
    }

    @Override
    protected PersonDocAtchTypesSession SESSION() {
        return (PersonDocAtchTypesSession)super.SESSION();
    }
}
