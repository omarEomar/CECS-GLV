package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface HandicapStatusSession extends BasicSession {

    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                  SharedApplicationException;

    public void postRecord(IRequestInfoDTO ri, IBasicDTO dto1) throws RemoteException, DataBaseException,
                                                                      SharedApplicationException;

    public List<IBasicDTO> getCodeNameExceptOne(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                           SharedApplicationException;
}
