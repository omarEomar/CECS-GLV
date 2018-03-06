package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.csc.inf.business.deploy.PersonQualificationsSession;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;


public class PersonQualificationsCMTClientImpl extends BaseClientImpl3 implements IPersonQualificationsCMTClient {


    public PersonQualificationsCMTClientImpl() {
        super();
      

    }
    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return PersonQualificationsSession.class;
    }

    @Override
    protected PersonQualificationsSession SESSION() {
        return (PersonQualificationsSession)super.SESSION();
    }
    public Boolean updateRegisterationOrderCMT(IPersonQualificationsDTO personQualificationsDTO) throws SharedApplicationException, 
                                                                                                        DataBaseException {
            try {
                return SESSION().updateRegisterationOrderCMT(RI(),personQualificationsDTO);
                } catch (SharedApplicationException e) {
                    throw e;
                } catch (Exception e) {
                    throw getWrappedException(e);
                }
    }

}
