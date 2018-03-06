package com.beshara.csc.inf.business.integration.eservices.registration;


import com.beshara.base.integration.eservices.IEService;
import com.beshara.csc.inf.business.integration.eservices.registration.dto.UserDTOEservice;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.ejb.Remote;

import javax.jws.WebService;


@Remote
@WebService
public interface IRegistrationEServiece extends IEService {

    public UserDTOEservice  getUserByCivilIdForPortal(Long civilid,Long isForPortal) throws DataBaseException, SharedApplicationException;
    public UserDTOEservice  UpdateUserForPortal(Long civilid,Long isForPortal) throws DataBaseException, SharedApplicationException;
 
}
