package com.beshara.csc.inf.business.client;

import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.PersonDocumntsSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public class PersonDocumntsClientImpl extends BaseClientImpl3 implements IPersonDocumntsClient{
    public PersonDocumntsClientImpl() {
        super();
    }
    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return PersonDocumntsSession.class;
    }

    @Override
    protected PersonDocumntsSession SESSION() {
        return (PersonDocumntsSession)super.SESSION();
    }
    
    public List<IBasicDTO> getAllByCivilAndDocType(Long civilId, String doctypeCode) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            return SESSION().getAllByCivilAndDocType(RI(), civilId, doctypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
