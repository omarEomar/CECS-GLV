package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.InfDocumentTypesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class InfDocumentTypesClientImpl extends BaseClientImpl3 implements IInfDocumentTypesClient {

    /**
     * @param InfDocumentTypesSession
     */


    public InfDocumentTypesClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfDocumentTypesSession.class;
    }

    @Override
    protected InfDocumentTypesSession SESSION() {
        return (InfDocumentTypesSession)super.SESSION();
    }

    /**
     * @return List
     */
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
