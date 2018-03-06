package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.csc.inf.business.deploy.IdentificationTypesSession;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class IdentificationTypesClientImpl extends BaseClientImpl3 implements IIdentificationTypesClient {

    private IdentificationTypesSession infTypesSession;

    /**
     * @param InfTypesSession
     */
    public IdentificationTypesClientImpl() {
    }
	
	@Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return IdentificationTypesSession.class;
    }

    @Override
    protected IdentificationTypesSession SESSION() {
        return (IdentificationTypesSession)super.SESSION();
    }

}
