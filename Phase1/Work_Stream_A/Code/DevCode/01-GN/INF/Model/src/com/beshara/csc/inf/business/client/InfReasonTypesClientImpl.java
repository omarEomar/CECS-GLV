package com.beshara.csc.inf.business.client;

//import com.beshara.csc.inf.business.deploy.BanksSession;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.InfReasonTypesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class InfReasonTypesClientImpl extends BaseClientImpl3 implements IInfReasonTypesClient {

    public InfReasonTypesClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfReasonTypesSession.class;
    }

    @Override
    protected InfReasonTypesSession SESSION() {
        return (InfReasonTypesSession)super.SESSION();
    }

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCodeName(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
}
