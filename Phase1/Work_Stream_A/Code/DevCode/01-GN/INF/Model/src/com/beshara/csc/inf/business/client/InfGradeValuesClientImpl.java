package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.InfGradeValuesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class InfGradeValuesClientImpl extends BaseClientImpl3 implements IInfGradeValuesClient {


    /**
     * @param GradeTypesSession
     */
    public InfGradeValuesClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfGradeValuesSession.class;
    }

    @Override
    protected InfGradeValuesSession SESSION() {
        return (InfGradeValuesSession)super.SESSION();
    }

    // Edit By A.kamal to throw right exception from Layer 25/11/2014

    public List<IBasicDTO> getAllByTypeCode(Object code) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getAllByTypeCode(RI(), code);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

}
