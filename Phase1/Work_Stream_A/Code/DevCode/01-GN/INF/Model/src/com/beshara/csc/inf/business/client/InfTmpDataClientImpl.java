package com.beshara.csc.inf.business.client;

import com.beshara.base.client.BaseClientImpl3;
import com.beshara.csc.inf.business.dto.InfTmpDataDTO;
import com.beshara.csc.inf.business.deploy.InfTmpDataSession;
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

public class InfTmpDataClientImpl extends BaseClientImpl3 implements IInfTmpDataClient {

    private InfTmpDataSession infTmpDataSession;

    /**
     * @param InfTmpDataSession
     */
    public InfTmpDataClientImpl() {
    }
	
	@Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfTmpDataSession.class;
    }

    @Override
    protected InfTmpDataSession SESSION() {
        return (InfTmpDataSession)super.SESSION();
    }

}
