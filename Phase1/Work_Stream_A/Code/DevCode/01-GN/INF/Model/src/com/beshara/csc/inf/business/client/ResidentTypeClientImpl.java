package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.ResidentTypeSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class ResidentTypeClientImpl extends BaseClientImpl3 implements IResidentTypeClient {

    public ResidentTypeClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return ResidentTypeSession.class;
    }
    
    @Override
    protected ResidentTypeSession SESSION() {
        return (ResidentTypeSession)super.SESSION();
    }
    public List<IBasicDTO> getCodeName() throws DataBaseException ,SharedApplicationException{
            try {
                return SESSION().getCodeName(RI());
                } catch (SharedApplicationException e) {
                    throw e;
                } catch (Exception e) {
                    throw getWrappedException(e);
                }
       
    }
    
    public List<IBasicDTO> getCodeNameInCenter() throws DataBaseException ,SharedApplicationException{
            try {
                return SESSION().getCodeName(RI());
                } catch (SharedApplicationException e) {
                    throw e;
                } catch (Exception e) {
                    throw getWrappedException(e);
                }
       
    }
    
    public List getAllInCenter() throws DataBaseException, 
                                            SharedApplicationException {

            try {
                return SESSION().getAll(RI());
                } catch (SharedApplicationException e) {
                    throw e;
                } catch (Exception e) {
                    throw getWrappedException(e);
                }
                
        }

 
}
