package com.beshara.csc.gn.map.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.FinderException;
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
public interface MappedDataSession extends BasicSession {
    /**
     * Create list of IMappedDataDTO mapped to a specific IMappedDataDTO * @param mappedDataList
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean createMappedDataList(IRequestInfoDTO ri, List<IBasicDTO> mappedDataList) throws RemoteException,
                                                                                                   DataBaseException,
                                                                                                   SharedApplicationException;

    /**
     * get list of IMappedDataDTO that maps specific IMappedDataDTO * the given mappedDataDto should have objectType1Code , * objectType2Code soc1Code and soc1Value filled * @param mappedDataDto
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getMappedDataList(IRequestInfoDTO ri, IBasicDTO mappedDataDto) throws RemoteException,
                                                                                                 DataBaseException,
                                                                                                 SharedApplicationException;

    /**
     * update MappedData for specific Dto , the list items should have objectType1Code , * objectType2Code soc1Code and soc1Value filled * @param mappedDataList
     * @return Boolean
     * @throws RemoteException
     * @throws FinderException
     */
    Boolean updateMappedDataList(IRequestInfoDTO ri, List<IBasicDTO> mappedDataList) throws RemoteException,
                                                                                            DataBaseException,
                                                                                            SharedApplicationException;

    /**
     * @param objType1Code , soc1Code , soc2Code , socValue , mappedDataList
     * @return Boolean
     * @throws RemoteException
     * @throws FinderException
     */
    Boolean isValueAlreadyMapped(IRequestInfoDTO ri, Long objtypeCode, Long socCode1, Long socCode2,
                                 String socValue) throws RemoteException, DataBaseException,
                                                         SharedApplicationException;
}
