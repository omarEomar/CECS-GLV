package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.DecisionMakerTypesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class DecisionMakerTypesClientImpl extends BaseClientImpl3 implements IDecisionMakerTypesClient {
    public DecisionMakerTypesClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return DecisionMakerTypesSession.class;
    }
    @Override
    protected DecisionMakerTypesSession SESSION() {
        return (DecisionMakerTypesSession)super.SESSION();
    }

    /**
     * Get list of code and name * return list of IDecisionMakerTypesDTO initialize with code and name only * @return List
     * @throws DataBaseException
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

    /**
     * Get list of code and name * return list of IDecisionMakerTypesDTO initialize with code and name only * @return List
     * @throws DataBaseException
     */
    public List<IBasicDTO> getCodeNameInCenter() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCodeName(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getCodeNameByMin(Long minCode) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getCodeName(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }
 
    public List getAllInCenter() throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getAll(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> getDecisionMakerTypesByRecType(Long regtypeCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return SESSION().getDecisionMakerTypesByRecType(RI(), regtypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
