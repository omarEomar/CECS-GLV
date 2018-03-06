package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
@Remote
public interface InfTmpMigrCasesSession extends BasicSession {

    // Add your extra methods

    public List<IBasicDTO> getAllByDataTypeCode(IRequestInfoDTO ri, Long dataType,
                                                Long deskCode) throws DataBaseException, SharedApplicationException,
                                                                      RemoteException;

    public String getCaseCode(IRequestInfoDTO ri, Long dataType, Long diskCode) throws RemoteException,
                                                                                      DataBaseException,
                                                                                      SharedApplicationException;
}
