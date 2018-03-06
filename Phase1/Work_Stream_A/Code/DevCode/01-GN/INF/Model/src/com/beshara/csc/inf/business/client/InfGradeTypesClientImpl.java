package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.csc.inf.business.deploy.InfGradeTypesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;


public class InfGradeTypesClientImpl extends BaseClientImpl3 implements IInfGradeTypesClient {
    public InfGradeTypesClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfGradeTypesSession.class;
    }

    @Override
    protected InfGradeTypesSession SESSION() {
        return (InfGradeTypesSession)super.SESSION();
    }

    public Double getFormulaByGradeType(Long gradeTypeCode, String gradeValue) throws SharedApplicationException,
                                                                                      DataBaseException {
        try {
            return SESSION().getFormulaByGradeType(RI(), gradeTypeCode, gradeValue);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


}
