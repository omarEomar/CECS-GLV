package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.KwtCitizensResidentsSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class KwtCitizensResidentsCMTClientImpl extends BaseClientImpl3 implements IKwtCitizensResidentsCMTClient {

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return KwtCitizensResidentsSession.class;
    }

    @Override
    protected KwtCitizensResidentsSession SESSION() {
        return (KwtCitizensResidentsSession)super.SESSION();
    }

    public KwtCitizensResidentsCMTClientImpl() {
        super();
    }

    public IBasicDTO addInLocal(IBasicDTO p1) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().add(RI(), p1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
}
