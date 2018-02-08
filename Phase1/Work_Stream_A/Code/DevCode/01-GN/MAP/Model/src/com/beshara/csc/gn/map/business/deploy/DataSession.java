package com.beshara.csc.gn.map.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
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
public interface DataSession extends BasicSession {
    /**
     * get the string sql query from database for data with specific objectTypeCode and society code , * execute this query and return a list of IMappedValueDTO , each element has code and name * @param objtypeCode
     * @param socCode
     * @return List of IMappedValueDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> getAll(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                             SharedApplicationException;

    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO dataDTO) throws RemoteException, DataBaseException,
                                                                       SharedApplicationException;

    public List<IBasicDTO> listByObjectTypeAndSoc(IRequestInfoDTO ri, Long objtypeCode,
                                                  Long socCode) throws RemoteException, DataBaseException,
                                                                       SharedApplicationException;


    /**
     * get the string sql query from database for data with specific objectTypeCode and society code , * add where condition for the returned query to filter result by specific name * execute this query and return a list of PopupDTO , each element has code and name filtered by name * @param objtypeCode
     * @param socCode
     * @param name
     * @return list of returned PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSocFiltered(IRequestInfoDTO ri, Long objtypeCode, Long socCode,
                                                    String name) throws RemoteException, DataBaseException,
                                                                        SharedApplicationException;

    /**
     * Get all values , depend on type and soc1 , and also get if these values have already mapped before with soc2 * @param objtypeCode
     * @param socCode1
     * @param socCode2
     * @return list of returned MappedValueDTOs
     * @throws RemoteException
     */
    List<IBasicDTO> ListByTypeAndSoc1AndSoc2(IRequestInfoDTO ri, Long objtypeCode, Long socCode1,
                                             Long socCode2) throws RemoteException, DataBaseException,
                                                                   SharedApplicationException;


    List<IBasicDTO> ListByTypeAndSoc1AndSoc2AndMappedFilter(IRequestInfoDTO ri, Long objtypeCode, Long socCode1,
                                                            Long socCode2, Boolean mapped) throws RemoteException,
                                                                                                  DataBaseException,
                                                                                                  SharedApplicationException;

    /**
     * get the string sql query from database for data with specific objectTypeCode and society code , * add where condition for the returned query to filter result by specific name * execute this query and return a list of PopupDTO , each element has code and name filtered by code * @param objtypeCode
     * @param socCode
     * @param code
     * @return list of returned PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSocFilteredCode(IRequestInfoDTO ri, Long objtypeCode, Long socCode,
                                                        Long code) throws RemoteException, DataBaseException,
                                                                          SharedApplicationException;

    /**
     * get all Values executed from sql query execluding values selected before * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSoc2Code(IRequestInfoDTO ri, Long objtypeCode, Long soc2Code, Long soc1Code,
                                                 String soc1Value) throws RemoteException, DataBaseException,
                                                                          SharedApplicationException;

    public List<IBasicDTO> getTreeByTypeAndSoc2Code(IRequestInfoDTO ri, Long objtypeCode, Long soc2Code, Long soc1Code,
                                                    String soc1Value) throws RemoteException, DataBaseException,
                                                                             SharedApplicationException;

    public IPagingResponseDTO ListByTypeAndSoc2CodeWithPaging(IRequestInfoDTO ri, Long objtypeCode, Long soc2Code,
                                                              Long soc1Code, String soc1Value,
                                                              IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                                   DataBaseException,
                                                                                                   SharedApplicationException;

    /**
     * get all Values executed from sql query execluding values selected before filtered by name * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSoc2CodeFiltered(IRequestInfoDTO ri, Long objtypeCode, Long soc2Code,
                                                         Long soc1Code, String soc1Value,
                                                         String name) throws RemoteException, DataBaseException,
                                                                             SharedApplicationException;

    /**
     * get all Values executed from sql query execluding values selected before filtered by name * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSoc2CodeFilteredByCode(IRequestInfoDTO ri, Long objtypeCode, Long soc2Code,
                                                               Long soc1Code, String soc1Value,
                                                               String code) throws RemoteException, DataBaseException,
                                                                                   SharedApplicationException;

    /**
     * get all soc2Value ( code and name ) for specific mapped value in mapped data table * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListMappedT0ByTypeAndSoc2Code(IRequestInfoDTO ri, Long objtypeCode, Long soc2Code,
                                                         Long soc1Code, String soc1Value,
                                                         String mapStatus) throws RemoteException, DataBaseException,
                                                                                  SharedApplicationException;

    /**
     * * @param objtypeCode
     * @param socCode1
     * @param socCode2
     * @param name
     * @return
     * @throws RemoteException
     */
    public List<IBasicDTO> listByTypeAndSoc1FilteredByName(IRequestInfoDTO ri, Long objtypeCode, Long socCode1,
                                                           Long socCode2, String name) throws RemoteException,
                                                                                              DataBaseException,
                                                                                              SharedApplicationException;

    /**
     * * @param objtypeCode
     * @param socCode1
     * @param socCode2
     * @param code
     * @return
     * @throws RemoteException
     */
    public List<IBasicDTO> listByTypeAndSoc1FilteredByCode(IRequestInfoDTO ri, Long objtypeCode, Long socCode1,
                                                           Long socCode2, String code) throws RemoteException,
                                                                                              DataBaseException,
                                                                                              SharedApplicationException;

    public IPagingResponseDTO ListByTypeAndSoc2CodeFilteredByCodeWithPaging(IRequestInfoDTO ri,
                                                                            IBasicDTO _srchDTO) throws RemoteException,
                                                                                                       DataBaseException,
                                                                                                       SharedApplicationException;


    public IPagingResponseDTO ListByTypeAndSoc2CodeFilteredWithPaging(IRequestInfoDTO ri,
                                                                      IBasicDTO _srchDTO) throws RemoteException,
                                                                                                 DataBaseException,
                                                                                                 SharedApplicationException;

    Boolean DisplayTreeFlag(IRequestInfoDTO ri, Long objtypeCode, Long soc2Code) throws RemoteException,
                                                                                        DataBaseException,
                                                                                        SharedApplicationException;
}
