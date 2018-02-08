package com.beshara.csc.gn.map.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.paging.impl.PagingResponseDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.gn.map.business.dao.DataDAO;
import com.beshara.csc.gn.map.business.dto.IDataSearchDTO;
import com.beshara.csc.gn.map.business.entity.DataEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.fil.MaxNoOfRecordsRequiredException;
import com.beshara.csc.sharedutils.business.exception.fil.PageNumRequiredException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Represents the Business Object Wrapper ( as Session Bean ) for Business Component IpIuIbIlIiIsIhIiInIgI.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Stateless(name = "DataSession", mappedName = "Map-Model-DataSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(DataSession.class)
public class DataSessionBean extends MapBaseSessionBean implements DataSession {
    public DataSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return DataEntity.class;
    }

    @Override
    protected DataDAO DAO() {
        return (DataDAO)super.DAO();
    }

    public List<IBasicDTO> getAll(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {

        return DAO().getAll();

    }

    /**
     * get the string sql query from database for data with specific objectTypeCode and society code , * execute this query and return a list of MappedValueDTO's , each element has code and name * @param objtypeCode
     * @param socCode
     * @return List of IMappedValueDTO
     * @throws RemoteException
     */
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO dataDTO) throws DataBaseException, SharedApplicationException {

        return DAO().add(dataDTO);
    }

    public List<IBasicDTO> listByObjectTypeAndSoc(IRequestInfoDTO ri, Long objtypeCode,
                                                  Long socCode) throws DataBaseException, SharedApplicationException {
        return DAO().ListByTypeAndSoc(objtypeCode, socCode);
    }

    /**
     * get the string sql query from database for data with specific objectTypeCode and society code , * add where condition for the returned query to filter result by specific name * execute this query and return a list of PopupDTO , each element has code and name filtered by name * @param objtypeCode
     * @param socCode
     * @param name
     * @return list of returned PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSocFiltered(IRequestInfoDTO ri, Long objtypeCode, Long socCode,
                                                    String name) throws DataBaseException, SharedApplicationException {
        return DAO().ListByTypeAndSocFiltered(objtypeCode, socCode, name);
    }

    /**
     * get the string sql query from database for data with specific objectTypeCode and society code , * add where condition for the returned query to filter result by specific name * execute this query and return a list of PopupDTO , each element has code and name filtered by code * @param objtypeCode
     * @param socCode
     * @param code
     * @return list of returned PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSocFilteredCode(IRequestInfoDTO ri, Long objtypeCode, Long socCode,
                                                        Long code) throws DataBaseException,
                                                                          SharedApplicationException {
        return DAO().ListByTypeAndSocFilteredCode(objtypeCode, socCode, code);
    }

    /**
     * get all Values executed from sql query execluding values selected before * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSoc2Code(IRequestInfoDTO ri, Long objtypeCode, Long soc2Code, Long soc1Code,
                                                 String soc1Value) throws DataBaseException,
                                                                          SharedApplicationException {
        return DAO().ListByTypeAndSoc2Code(objtypeCode, soc2Code, soc1Code, soc1Value);
    }

    public List<IBasicDTO> getTreeByTypeAndSoc2Code(IRequestInfoDTO ri, Long objtypeCode, Long soc2Code, Long soc1Code,
                                                    String soc1Value) throws DataBaseException,
                                                                             SharedApplicationException {
        return DAO().getTreeByTypeAndSoc2Code(objtypeCode, soc2Code, soc1Code, soc1Value);
    }


    public IPagingResponseDTO ListByTypeAndSoc2CodeWithPaging(IRequestInfoDTO ri, Long objtypeCode, Long soc2Code,
                                                              Long soc1Code, String soc1Value,
                                                              IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                   SharedApplicationException {
        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().getTotalCount(objtypeCode, soc2Code, soc1Code, soc1Value));
            }

            List<IBasicDTO> result = null;
            result = DAO().ListByTypeAndSoc2CodeWithPaging(objtypeCode, soc2Code, soc1Code, soc1Value, requestDTO);
            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }
        return responseDTO;
    }


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
                                                         String name) throws DataBaseException,
                                                                             SharedApplicationException {
        return DAO().ListByTypeAndSoc2CodeFiltered(objtypeCode, soc2Code, soc1Code, soc1Value, name);
    }

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
                                                               String code) throws DataBaseException,
                                                                                   SharedApplicationException {
        return DAO().ListByTypeAndSoc2CodeFilteredByCode(objtypeCode, soc2Code, soc1Code, soc1Value, code);
    }

    /**
     * get all Values executed from sql query execluding values selected before filtered by name * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws RemoteException
     */
    public IPagingResponseDTO ListByTypeAndSoc2CodeFilteredByCodeWithPaging(IRequestInfoDTO ri,
                                                                            IBasicDTO _srchDTO) throws DataBaseException,
                                                                                                       SharedApplicationException {
        IDataSearchDTO srchDTO = (IDataSearchDTO)_srchDTO;
        IPagingResponseDTO responseDTO = null;
        if (srchDTO.getRequestDTO() != null) {
            Long pageNum = srchDTO.getRequestDTO().getPageNum();
            Long maxNoOfRecords = srchDTO.getRequestDTO().getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    srchDTO.getRequestDTO().setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();


            List<IBasicDTO> result = null;
            result = DAO().ListByTypeAndSoc2CodeFilteredByCodeWithPaging(srchDTO);
            if (srchDTO.getRequestDTO().isCountRequired()) {
                responseDTO.setCount(new Long(result.size()));
            }
            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(srchDTO.getRequestDTO());
        }
        return responseDTO;
    }

    public IPagingResponseDTO ListByTypeAndSoc2CodeFilteredWithPaging(IRequestInfoDTO ri,
                                                                      IBasicDTO _srchDTO) throws DataBaseException,
                                                                                                 SharedApplicationException {
        IPagingResponseDTO responseDTO = null;
        IDataSearchDTO srchDTO = (IDataSearchDTO)_srchDTO;
        if (srchDTO.getRequestDTO() != null) {
            Long pageNum = srchDTO.getRequestDTO().getPageNum();
            Long maxNoOfRecords = srchDTO.getRequestDTO().getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    srchDTO.getRequestDTO().setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();


            List<IBasicDTO> result = null;
            result = DAO().ListByTypeAndSoc2CodeFilteredWithPaging(srchDTO);
            if (srchDTO.getRequestDTO().isCountRequired()) {
                responseDTO.setCount(new Long(result.size()));
            }
            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(srchDTO.getRequestDTO());
        }
        return responseDTO;
    }


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
                                                         String mapStatus) throws DataBaseException,
                                                                                  SharedApplicationException {
        return DAO().ListMappedT0ByTypeAndSoc2Code(objtypeCode, soc2Code, soc1Code, soc1Value, mapStatus);
    }

    /**
     * Get all values , depend on type and soc1 , and also get if these values have already mapped before with soc2 * @param objtypeCode
     * @param socCode1
     * @param socCode2
     * @return list of returned MappedValueDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSoc1AndSoc2(IRequestInfoDTO ri, Long objtypeCode, Long socCode1,
                                                    Long socCode2) throws DataBaseException,
                                                                          SharedApplicationException {
        return DAO().ListByTypeAndSoc1AndSoc2(objtypeCode, socCode1, socCode2);
    }

    public List<IBasicDTO> ListByTypeAndSoc1AndSoc2AndMappedFilter(IRequestInfoDTO ri, Long objtypeCode, Long socCode1,
                                                                   Long socCode2,
                                                                   Boolean mapped) throws DataBaseException,
                                                                                          SharedApplicationException {
        return DAO().ListByTypeAndSoc1AndSoc2AndMappedFilter(objtypeCode, socCode1, socCode2, mapped);
    }


    /**
     * * @param objtypeCode
     * @param socCode1
     * @param socCode2
     * @param name
     * @return
     * @throws RemoteException
     */
    public List<IBasicDTO> listByTypeAndSoc1FilteredByName(IRequestInfoDTO ri, Long objtypeCode, Long socCode1,
                                                           Long socCode2, String name) throws DataBaseException,
                                                                                              SharedApplicationException {
        return DAO().listByTypeAndSoc1FilteredByName(objtypeCode, socCode1, socCode2, name);
    }

    /**
     * * @param objtypeCode
     * @param socCode1
     * @param socCode2
     * @param code
     * @return
     * @throws RemoteException
     */
    public List<IBasicDTO> listByTypeAndSoc1FilteredByCode(IRequestInfoDTO ri, Long objtypeCode, Long socCode1,
                                                           Long socCode2, String code) throws DataBaseException,
                                                                                              SharedApplicationException {
        return DAO().listByTypeAndSoc1FilteredByCode(objtypeCode, socCode1, socCode2, code);
    }

    public Boolean DisplayTreeFlag(IRequestInfoDTO ri, Long objtypeCode, Long soc2Code) throws RemoteException, DataBaseException,
                                                                                               SharedApplicationException {
        return DAO().DisplayTreeFlag(objtypeCode, soc2Code);
    }


}
