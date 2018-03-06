package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.HandicapStatusSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class HandicapStatusClientImpl extends BaseClientImpl3 implements IHandicapStatusClient {

    public HandicapStatusClientImpl() {
        super();
       
    }
    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return HandicapStatusSession.class;
    }

    @Override
    protected HandicapStatusSession SESSION() {
        return (HandicapStatusSession)super.SESSION();
    }

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        
            try {
                return SESSION().getCodeName(RI());
                } catch (SharedApplicationException e) {
                    throw e;
                } catch (Exception e) {
                    throw getWrappedException(e);
                }
       
    }
    
    public List<IBasicDTO> getCodeNameExceptOne() throws DataBaseException, SharedApplicationException {
        
            try {
                return SESSION().getCodeNameExceptOne(RI());
                } catch (SharedApplicationException e) {
                    throw e;
                } catch (Exception e) {
                    throw getWrappedException(e);
                }
        
    }

    public void postRecord(IBasicDTO dto1) throws SharedApplicationException, DataBaseException {
        

            try {
                SESSION().postRecord(RI(),dto1);
                } catch (SharedApplicationException e) {
                    throw e;
                } catch (Exception e) {
                    throw getWrappedException(e);
                }}
}
