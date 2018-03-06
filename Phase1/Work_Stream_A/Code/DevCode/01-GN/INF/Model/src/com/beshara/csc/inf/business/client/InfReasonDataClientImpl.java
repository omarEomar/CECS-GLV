package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.InfReasonDataSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class InfReasonDataClientImpl extends BaseClientImpl3 implements IInfReasonDataClient {

    public InfReasonDataClientImpl() {
        super();

    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfReasonDataSession.class;
    }

    @Override
    protected InfReasonDataSession SESSION() {
        return (InfReasonDataSession)super.SESSION();
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

    public List<IBasicDTO> getReasonDataByType(long reasontype) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getReasonDataByType(RI(),reasontype);
           
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
    }

    public List<IBasicDTO> searchReasonData(IBasicDTO infReasonDataDTO) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            return SESSION().searchReasonData(RI(),infReasonDataDTO);
            //        } catch (RemoteException e) {
            //            SystemException se = new SystemException(e);
            //            throw new DataBaseException(se.getSQLExceptionMessage());
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
    }

}
