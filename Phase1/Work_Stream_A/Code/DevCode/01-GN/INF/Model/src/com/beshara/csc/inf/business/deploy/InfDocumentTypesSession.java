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

public interface InfDocumentTypesSession extends BasicSession {

    List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                           SharedApplicationException;

    public List<IBasicDTO> search(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                 SharedApplicationException,
                                                                                 DataBaseException;

    public List<IBasicDTO> searchByName(String name) throws RemoteException, DataBaseException,
                                                            SharedApplicationException;

    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws RemoteException, DataBaseException,
                                                                                SharedApplicationException;


}
       
