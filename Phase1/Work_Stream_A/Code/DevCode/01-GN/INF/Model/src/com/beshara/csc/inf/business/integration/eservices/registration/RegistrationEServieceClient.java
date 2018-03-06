package com.beshara.csc.inf.business.integration.eservices.registration;


import com.beshara.base.integration.eservices.EServiceClient;
import com.beshara.base.integration.eservices.IEService;
import com.beshara.csc.inf.business.integration.eservices.registration.dto.UserDTOEservice;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class RegistrationEServieceClient extends EServiceClient {
    public RegistrationEServieceClient() {
        super();
        System.out.println("RegistrationEServieceClientRegistrationEServieceClientRegistrationEServieceClientRegistrationEServieceClientRegistrationEServieceClient");

        try {
            getUserByCivilIdForPortal(282041101009L, 1L);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        System.out.println("ENDENDRegistrationEServieceClientRegistrationEServieceClientRegistrationEServieceClientRegistrationEServieceClientRegistrationEServieceClient");

    }

    @Override
    protected Class<? extends IEService> getEServiceInterface() {
        return IRegistrationEServiece.class;
    }

    @Override
    protected IRegistrationEServiece SERVICE() {
        return (IRegistrationEServiece)super.SERVICE();
    }

    public UserDTOEservice  getUserByCivilIdForPortal(Long civilid,Long isForPortal) throws DataBaseException, SharedApplicationException {
        try {
            return SERVICE().getUserByCivilIdForPortal(civilid,isForPortal); 
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }  

    public UserDTOEservice  UpdateUserForPortal(Long civilid,Long isForPortal) throws DataBaseException, SharedApplicationException {
        try {
            return SERVICE().UpdateUserForPortal(civilid,isForPortal); 
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    } 
  
}

