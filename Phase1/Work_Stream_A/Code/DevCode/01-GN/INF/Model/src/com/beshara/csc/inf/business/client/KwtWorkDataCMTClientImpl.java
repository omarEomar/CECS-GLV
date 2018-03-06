package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.KwtWorkDataSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class KwtWorkDataCMTClientImpl extends BaseClientImpl3 implements IKwtWorkDataCMTClient {
    public KwtWorkDataCMTClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return KwtWorkDataSession.class;
    }

    @Override
    protected KwtWorkDataSession SESSION() {
        return (KwtWorkDataSession)super.SESSION();
    }

    public IBasicDTO addCMT(IBasicDTO kwtWorkDataDTO1) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().addCMT(RI(),kwtWorkDataDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
    
}
