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
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Remote Interface Contains All the Methods which are Implemented By Session Bean . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Remote
public interface CountryCitiesSession extends BasicSession {
    List<IBasicDTO> getCities(IRequestInfoDTO ri, Long countryCode) throws RemoteException, DataBaseException,
                                                                           SharedApplicationException;

    IBasicDTO getCapital(IRequestInfoDTO ri, Long countryCode) throws RemoteException, DataBaseException,
                                                                      SharedApplicationException;

    void validateHasCapital(IRequestInfoDTO ri, Long countryCode) throws RemoteException, DataBaseException,
                                                                         SharedApplicationException;

    List<com.beshara.base.dto.IBasicDTO> searchByCode(IRequestInfoDTO ri, Long cntryCode,
                                                      Long cntrycityCode) throws RemoteException, DataBaseException,
                                                                                 SharedApplicationException;

    List<com.beshara.base.dto.IBasicDTO> searchByName(IRequestInfoDTO ri, Long cntryCode,
                                                      String cntrycityName) throws RemoteException, DataBaseException,
                                                                                   SharedApplicationException;
}
