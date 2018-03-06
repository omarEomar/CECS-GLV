package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.client.BasicClientImpl;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.SpecialCaseTypesSession;
import com.beshara.csc.inf.business.deploy.YearsSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.SystemException;

import java.rmi.RemoteException;

import java.util.List;


public class SpecialCaseTypesClientImpl extends BaseClientImpl3 implements ISpecialCaseTypesClient {

    public SpecialCaseTypesClientImpl() {
        super();
    }
    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return SpecialCaseTypesSession.class;
    }

    @Override
    protected SpecialCaseTypesSession SESSION() {
        return (SpecialCaseTypesSession)super.SESSION();
    }


    public List<IBasicDTO> getCodeName() throws DataBaseException,SharedApplicationException {
            try {
                return SESSION().getCodeName(RI());
            } catch (RemoteException e) {
                SystemException se = new SystemException(e);
                throw new DataBaseException(se.getSQLExceptionMessage());
            }
      
    }

   
}
