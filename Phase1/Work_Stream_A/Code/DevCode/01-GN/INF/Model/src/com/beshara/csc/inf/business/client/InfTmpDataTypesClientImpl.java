package com.beshara.csc.inf.business.client;

import com.beshara.base.client.BaseClientImpl3;
import com.beshara.csc.inf.business.dto.InfTmpDataTypesDTO;
import com.beshara.csc.inf.business.deploy.InfTmpDataTypesSession;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.deploy.BasicSession;
import java.util.Collection;
import java.util.List;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class InfTmpDataTypesClientImpl extends BaseClientImpl3 implements IInfTmpDataTypesClient {

    private InfTmpDataTypesSession infTmpDataTypesSession;

    /**
     * @param InfTmpDataTypesSession
     */
    public InfTmpDataTypesClientImpl() {
    }
	
	@Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfTmpDataTypesSession.class;
    }

    @Override
    protected InfTmpDataTypesSession SESSION() {
        return (InfTmpDataTypesSession)super.SESSION();
    }

}
