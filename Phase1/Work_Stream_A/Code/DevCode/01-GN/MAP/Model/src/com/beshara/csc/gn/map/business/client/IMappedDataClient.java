package com.beshara.csc.gn.map.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Interface Contains a set of Methods Which Should be Implemented * with Different Implementation ( SessionBean , Message Driven Bean , RMI Service etc ... ) * and Generated through The Client Factory Class . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Sherif El-Rabbat 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry
 * @version 1.0
 * @since 03/09/2007
 */
public interface IMappedDataClient extends IBasicClient {

    /**
     * Create list of IMappedDataDTO mapped to a specific IMappedDataDTO * @param mappedDataList
     * @return
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean createMappedDataList(List<IBasicDTO> mappedDataList) throws DataBaseException,
                                                                               SharedApplicationException;

    /**
     * get list of IMappedDataDTO that maps specific IMappedDataDTO * the given mappedDataDto should have objectType1Code , * objectType2Code soc1Code and soc1Value filled * @param mappedDataDto
     * @return List IMappedDataDTO
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
    public List<IBasicDTO> getMappedDataList(IBasicDTO mappedDataDto) throws DataBaseException,
                                                                             SharedApplicationException;

    /**
     * update MappedData for specific Dto , the list items should have objectType1Code , * objectType2Code soc1Code and soc1Value filled * Updating MappedData is done through deleting old data and insert new one * @param mappedDataList
     * @return Boolean
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
    public Boolean updateMappedDataList(List<IBasicDTO> mappedDataList) throws DataBaseException,
                                                                               SharedApplicationException;
    /**
 * remove MappedData for specific Dto , the list items should have objectType1Code , * objectType2Code soc1Code and soc1Value filled * @param mappedDataList
 * @return Boolean
 * @throws DataBaseException
 * @throws ItemNotFoundException
 */
    // public Boolean removeMappedDataList ( List<IBasicDTO> mappedDataList ) throws DataBaseException , ItemNotFoundException ;
    /**
 * @param objtype1Code , soc1Code , soc2Code
 * @return List IMappedDataDTO
 * @throws RemoteException
 * @throws FinderException
 */
    //public List<IMappedDataDTO> getMappedDataList ( Long objtype1Code , Long soc1Code , Long soc2Code ) throws DataBaseException ,
    // FinderException ;
    /**
 * @param objtype1Code , soc1Code , soc2Code , soc1Value
 * @return List IMappedDataDTO
 * @throws RemoteException
 * @throws FinderException
 */
    //public List<IMappedDataDTO> getMappedDataListByValue ( Long objtype1Code , Long soc1Code , Long soc2Code , String soc1Value ) throws DataBaseException ,
    //FinderException ;
    /**
 * @param objType1Code , soc1Code , soc2Code , mappedDataList
 * @return Boolean
 * @throws RemoteException
 * @throws FinderException
 */
    //public Boolean updateMappedDataList ( Long objType1Code , Long soc1Code , Long soc2Code , List<IMappedDataDTO> mappedDataList ) throws DataBaseException , FinderException ;

    /**
     * @param objType1Code , soc1Code , soc2Code , socValue , mappedDataList
     * @return Boolean
     * @throws RemoteException
     * @throws FinderException
     */
    //public Boolean updateMappedDataList ( Long objType1Code , Long soc1Code , Long soc2Code , String socValue , List<IMappedDataDTO> mappedDataList ) throws DataBaseException , FinderException ;

    Boolean isValueAlreadyMapped(Long objtypeCode, Long socCode1, Long socCode2,
                                 String socValue) throws DataBaseException, SharedApplicationException;
}
