package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.PersonsInformationSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class PersonsInformationCMTClientImpl extends BaseClientImpl3 implements IPersonsInformationCMTClient {

    public PersonsInformationCMTClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return PersonsInformationSession.class;
    }

    @Override
    protected PersonsInformationSession SESSION() {
        return (PersonsInformationSession)super.SESSION();
    }

    public IBasicDTO addCMT(IBasicDTO personsInformationDTO1) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().addCMT(RI(), personsInformationDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
