package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dto.PersonDocAttachemntsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface PersonDocAttachemntsSession extends BasicSession {
    public List<IBasicDTO> getAllByCivilAndDocType(IRequestInfoDTO ri, Long civilId,
                                                   String doctypeCode) throws RemoteException, DataBaseException,
                                                                              SharedApplicationException;

    public IBasicDTO addPersonDocAttachment(IRequestInfoDTO ri, IBasicDTO dtoParam) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException;
    
    public List<IBasicDTO> searchAllDocuments(IRequestInfoDTO ri, IBasicDTO searchDTO) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException;
}
