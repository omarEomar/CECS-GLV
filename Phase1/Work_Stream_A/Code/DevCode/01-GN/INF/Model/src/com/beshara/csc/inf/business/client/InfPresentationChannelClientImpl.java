package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.csc.inf.business.deploy.InfPresentationChannelSession;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class InfPresentationChannelClientImpl extends BaseClientImpl3 implements IInfPresentationChannelClient {


    /**
     * @param InfPresentationChannelSession
     */
    public InfPresentationChannelClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfPresentationChannelSession.class;
    }

    @Override
    protected InfPresentationChannelSession SESSION() {
        return (InfPresentationChannelSession)super.SESSION();
    }

}
