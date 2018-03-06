package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.csc.inf.business.deploy.InfMobileCompaniesSession;


/**
 * @author       Beshara Group
 * @author       Black Hourse [E.Saber]
 * @version      1.0
 * @since        03/11/2016
 */

public class InfMobileCompaniesClientImpl extends BaseClientImpl3 implements IInfMobileCompaniesClient {
    public InfMobileCompaniesClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfMobileCompaniesSession.class;
    }

    @Override
    protected InfMobileCompaniesSession SESSION() {
        return (InfMobileCompaniesSession)super.SESSION();
    }

}
