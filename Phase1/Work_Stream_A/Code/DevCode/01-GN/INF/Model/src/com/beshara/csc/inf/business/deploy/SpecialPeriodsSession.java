package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface SpecialPeriodsSession extends BasicSession {
    public List<IBasicDTO> getAllByTypeAndMinCode(IRequestInfoDTO ri, BasicDTO dto) throws RemoteException,
                                                                                            DataBaseException,
                                                                                            SharedApplicationException;
}
