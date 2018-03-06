package com.beshara.csc.gn.inf.business.client;

import com.beshara.base.client.BaseClientImpl3;
import com.beshara.csc.gn.inf.business.dto.InfOldDataAuditDTO;
import com.beshara.csc.gn.inf.business.deploy.InfOldDataAuditSession;
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

public class InfOldDataAuditClientImpl extends BaseClientImpl3 implements IInfOldDataAuditClient {

    private InfOldDataAuditSession infOldDataAuditSession;

    /**
     * @param InfOldDataAuditSession
     */
    public InfOldDataAuditClientImpl() {
    }
	
	@Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfOldDataAuditSession.class;
    }

    @Override
    protected InfOldDataAuditSession SESSION() {
        return (InfOldDataAuditSession)super.SESSION();
    }

}
