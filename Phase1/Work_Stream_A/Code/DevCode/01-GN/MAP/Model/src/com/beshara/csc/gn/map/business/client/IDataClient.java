package com.beshara.csc.gn.map.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
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
public interface IDataClient extends IBasicClient { /**
 * get all data with specific objectTypeCode * @param objtypeCode
 * @return List
 * @throws DataBaseException,SharedApplicationException
 */
    //List<ISocietiesDTO> listByObjectType ( Long objtypeCode ) throws DataBaseException,SharedApplicationException ; 

    /** 
     * get the string sql query from database for data with specific objectTypeCode and society code , * execute this query and return a list of MappedValueDTO's , each element has code and name * @param objtypeCode 
     * @param socCode 
     * @return List of IMappedValueDTO 
     * @throws DataBaseException,SharedApplicationException 
     */
    List<IBasicDTO> listByObjectTypeAndSoc(Long objtypeCode, 
                                           Long socCode) throws DataBaseException,SharedApplicationException,SharedApplicationException;
    
   
    /** 
     * Get all values , depend on type and soc1 , and also get if these values have already mapped before with soc2 * @param objtypeCode 
     * @param socCode1 
     * @param socCode2 
     * @return list of returned MappedValueDTOs 
     * @throws RemoteException 
     */
    List<IBasicDTO> ListByTypeAndSoc1AndSoc2(Long objtypeCode, Long socCode1, 
                                             Long socCode2) throws DataBaseException,SharedApplicationException,SharedApplicationException;

    List<IBasicDTO> ListByTypeAndSoc1AndSoc2AndMappedFilter(Long objtypeCode, Long socCode1,
                                                        Long socCode2,Boolean mapped) throws DataBaseException,SharedApplicationException,SharedApplicationException;
    /** 
     * get the string sql query from database for data with specific objectTypeCode and society code , * add where condition for the returned query to filter result by specific name * execute this query and return a list of PopupDTO , each element has code and name filtered by name * @param objtypeCode 
     * @param socCode 
     * @param name 
     * @return list of returned PopupDTOs 
     * @throws DataBaseException,SharedApplicationException 
     */
    public List<IBasicDTO> ListByTypeAndSocFiltered(Long objtypeCode, 
                                                    Long socCode, 
                                                    String name) throws DataBaseException,SharedApplicationException;

    /** 
     * get the string sql query from database for data with specific objectTypeCode and society code , * add where condition for the returned query to filter result by specific name * execute this query and return a list of PopupDTO , each element has code and name filtered by code * @param objtypeCode 
     * @param socCode 
     * @param code 
     * @return list of returned PopupDTOs 
     * @throws DataBaseException,SharedApplicationException 
     */
    public List<IBasicDTO> ListByTypeAndSocFilteredCode(Long objtypeCode, 
                                                        Long socCode, 
                                                        Long code) throws DataBaseException,SharedApplicationException;

    /** 
     * get all Values executed from sql query execluding values selected before * @param objtypeCode 
     * @param soc1Code 
     * @param soc2Code 
     * @param soc1Value 
     * @return list of PopupDTOs 
     * @throws DataBaseException,SharedApplicationException 
     */
    public List<IBasicDTO> ListByTypeAndSoc2Code(Long objtypeCode, 
                                                 Long soc2Code, Long soc1Code, 
                                                 String soc1Value) throws DataBaseException,SharedApplicationException;
    
    public List<IBasicDTO> getTreeByTypeAndSoc2Code(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                     String soc1Value) throws DataBaseException,SharedApplicationException;                                             
    
    
    public IPagingResponseDTO ListByTypeAndSoc2CodeWithPaging(Long objtypeCode, 
                                                 Long soc2Code, Long soc1Code, 
                                                 String soc1Value, 
                                                 IPagingRequestDTO requestDTO) throws DataBaseException,SharedApplicationException,SharedApplicationException;

    /** 
     * get all Values executed from sql query execluding values selected before filtered by name * @param objtypeCode 
     * @param soc1Code 
     * @param soc2Code 
     * @param soc1Value 
     * @return list of PopupDTOs 
     * @throws DataBaseException,SharedApplicationException 
     */
    public List<IBasicDTO> ListByTypeAndSoc2CodeFiltered(Long objtypeCode, 
                                                         Long soc2Code, 
                                                         Long soc1Code, 
                                                         String soc1Value, 
                                                         String param) throws DataBaseException,SharedApplicationException;
                                                         
    public List<IBasicDTO> ListByTypeAndSoc2CodeFilteredByCode(Long objtypeCode, 
                                                         Long soc2Code, 
                                                         Long soc1Code, 
                                                         String soc1Value, 
                                                         String code) throws DataBaseException,SharedApplicationException;
                                                         
    

    /** Edited By Hany Omar 9/11/2014
     * get all soc2Value ( code and name ) for specific mapped value in mapped data table * @param objtypeCode 
     * @param soc1Code 
     * @param soc2Code 
     * @param soc1Value 
     * @return list of PopupDTOs 
     * @throws RemoteException 
     */
    public List<IBasicDTO> ListMappedT0ByTypeAndSoc2Code(Long objtypeCode, 
                                                         Long soc2Code, 
                                                         Long soc1Code, 
                                                         String soc1Value) throws DataBaseException,SharedApplicationException;
    /**
     * Author : Hany Omar 9/11/2014
     * @param objtypeCode
     * @param soc2Code
     * @param soc1Code
     * @param soc1Value
     * @param mapStatus
     * @return
     * @throws DataBaseException,SharedApplicationException
     */
    public List<IBasicDTO> ListMappedT0ByTypeAndSoc2Code(Long objtypeCode, 
                                                         Long soc2Code, 
                                                         Long soc1Code, 
                                                         String soc1Value,
                                                         String mapStatus) throws DataBaseException,SharedApplicationException;

    /** 
     * * @param objtypeCode 
     * @param socCode1 
     * @param socCode2 
     * @param name 
     * @return 
     * @throws RemoteException 
     */
    public List<IBasicDTO> listByTypeAndSoc1FilteredByName(Long objtypeCode, 
                                                           Long socCode1, 
                                                           Long socCode2, 
                                                           String name) throws DataBaseException,SharedApplicationException;

    /** 
     * * @param objtypeCode 
     * @param socCode1 
     * @param socCode2 
     * @param code 
     * @return 
     * @throws RemoteException 
     */
    public List<IBasicDTO> listByTypeAndSoc1FilteredByCode(Long objtypeCode, 
                                                           Long socCode1, 
                                                           Long socCode2, 
                                                           String code) throws DataBaseException,SharedApplicationException;
                                                           
    public IPagingResponseDTO ListByTypeAndSoc2CodeFilteredByCodeWithPaging(IBasicDTO _srchDTO) throws DataBaseException,SharedApplicationException,SharedApplicationException;
                                                         
    
    
    public IPagingResponseDTO ListByTypeAndSoc2CodeFilteredWithPaging(IBasicDTO _srchDTO) throws DataBaseException,SharedApplicationException,SharedApplicationException;
    
    Boolean DisplayTreeFlag(Long objtypeCode, Long soc2Code) throws DataBaseException,SharedApplicationException;
}
