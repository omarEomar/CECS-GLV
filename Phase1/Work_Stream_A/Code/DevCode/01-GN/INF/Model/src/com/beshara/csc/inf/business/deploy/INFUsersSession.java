package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dto.UserDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import javax.ejb.Remote;


@Remote
public interface INFUsersSession extends BasicSession {
    public UserDTO getUserByCivilIdForPortal(IRequestInfoDTO ri, Long civilid,
                                             Long isForPortal) throws RemoteException, DataBaseException,
                                                                      SharedApplicationException;

    public void UpdateUserForPortal(IRequestInfoDTO ri, Long civilid, Long isForPortal) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;
}
