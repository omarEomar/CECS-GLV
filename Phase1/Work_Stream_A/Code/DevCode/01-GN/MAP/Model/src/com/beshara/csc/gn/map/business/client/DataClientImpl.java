package com.beshara.csc.gn.map.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.gn.map.business.deploy.DataSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.SystemException;

import java.rmi.RemoteException;

import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Implements a specified Client Interface as Session Bean * and Generated through the Client Factory Class Based on the Key Returned from the Properties File I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Taha El-Fitiany , Sherif El-Rabbat
 * @version 1.0
 * @since 03/09/2007
 */
public class DataClientImpl extends BaseClientImpl3 implements IDataClient {
    public DataClientImpl() {
        super();
    }
    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return DataSession.class;
    }

    @Override
    protected DataSession SESSION() {
        return (DataSession)super.SESSION();
    }



    /**
     * get the string sql query from database for data with specific objectTypeCode and society code , * execute this query and return a list of MappedValueDTO's , each element has code and name * @param objtypeCode
     * @param socCode
     * @return List IMappedValueDTO
     * @throws DataBaseException
     */
    public List<IBasicDTO> listByObjectTypeAndSoc(Long objtypeCode, Long socCode) throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().listByObjectTypeAndSoc(RI(),objtypeCode, socCode);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

   
    /**
     * get the string sql query from database for data with specific objectTypeCode and society code , * add where condition for the returned query to filter result by specific name * execute this query and return a list of PopupDTO , each element has code and name filtered by name * @param objtypeCode
     * @param socCode
     * @param name
     * @return list of returned PopupDTOs
     * @throws DataBaseException
     */
    public List<IBasicDTO> ListByTypeAndSocFiltered(Long objtypeCode, Long socCode,
                                                    String name)  throws DataBaseException,SharedApplicationException{
        try {
            return SESSION().ListByTypeAndSocFiltered(RI(),objtypeCode, socCode, name);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    /**
     * get the string sql query from database for data with specific objectTypeCode and society code , * add where condition for the returned query to filter result by specific name * execute this query and return a list of PopupDTO , each element has code and name filtered by code * @param objtypeCode
     * @param socCode
     * @param code
     * @return list of returned PopupDTOs
     * @throws DataBaseException
     */
    public List<IBasicDTO> ListByTypeAndSocFilteredCode(Long objtypeCode, Long socCode,
                                                        Long code)  throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().ListByTypeAndSocFilteredCode(RI(),objtypeCode, socCode, code);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    /**
     * get all Values executed from sql query execluding values selected before * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws DataBaseException
     */
    public List<IBasicDTO> ListByTypeAndSoc2Code(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                 String soc1Value)  throws DataBaseException,SharedApplicationException{
        try {
            return SESSION().ListByTypeAndSoc2Code(RI(),objtypeCode, soc2Code, soc1Code, soc1Value);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }
    
    public List<IBasicDTO> getTreeByTypeAndSoc2Code(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                 String soc1Value)  throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().getTreeByTypeAndSoc2Code(RI(),objtypeCode, soc2Code, soc1Code, soc1Value);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }
    
    
    public IPagingResponseDTO ListByTypeAndSoc2CodeWithPaging(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                              String soc1Value,
                                                              IPagingRequestDTO requestDTO)  throws DataBaseException,SharedApplicationException{
        try {
            return SESSION().ListByTypeAndSoc2CodeWithPaging(RI(),objtypeCode, soc2Code, soc1Code, soc1Value,
                                                                    requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    /**
     * get all Values executed from sql query execluding values selected before filtered by name * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws DataBaseException
     */
    public List<IBasicDTO> ListByTypeAndSoc2CodeFiltered(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                         String soc1Value, String name)  throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().ListByTypeAndSoc2CodeFiltered(RI(),objtypeCode, soc2Code, soc1Code, soc1Value, name);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    public List<IBasicDTO> ListByTypeAndSoc2CodeFilteredByCode(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                               String soc1Value,
                                                               String code)  throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().ListByTypeAndSoc2CodeFilteredByCode(RI(),objtypeCode, soc2Code, soc1Code, soc1Value,
                                                                        code);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    /** Edited By Hany Omar
     * get all soc2Value ( code and name ) for specific mapped value in mapped data table * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListMappedT0ByTypeAndSoc2Code(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                         String soc1Value)  throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().ListMappedT0ByTypeAndSoc2Code(RI(),objtypeCode, soc2Code, soc1Code, soc1Value, null);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    /**
     * Hany Omar 9/11/2014
     * @param objtypeCode
     * @param soc2Code
     * @param soc1Code
     * @param soc1Value
     * @param mapStatus
     * @return
     * @throws DataBaseException
     */

    public List<IBasicDTO> ListMappedT0ByTypeAndSoc2Code(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                         String soc1Value, String mapStatus)  throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().ListMappedT0ByTypeAndSoc2Code(RI(),objtypeCode, soc2Code, soc1Code, soc1Value,
                                                                  mapStatus);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    /**
     * Get all values , depend on type and soc1 , and also get if these values have already mapped before with soc2 * @param objtypeCode
     * @param socCode1
     * @param socCode2
     * @return list of returned MappedValueDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSoc1AndSoc2(Long objtypeCode, Long socCode1,
                                                    Long socCode2)  throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().ListByTypeAndSoc1AndSoc2(RI(),objtypeCode, socCode1, socCode2);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    public List<IBasicDTO> ListByTypeAndSoc1AndSoc2AndMappedFilter(Long objtypeCode, Long socCode1,
                                                    Long socCode2,Boolean mapped)  throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().ListByTypeAndSoc1AndSoc2AndMappedFilter(RI(),objtypeCode, socCode1, socCode2,mapped);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }
    
    /**
     * * @param objtypeCode
     * @param socCode1
     * @param socCode2
     * @param name
     * @return
     * @throws RemoteException
     */
    public List<IBasicDTO> listByTypeAndSoc1FilteredByName(Long objtypeCode, Long socCode1, Long socCode2,
                                                           String name)  throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().listByTypeAndSoc1FilteredByName(RI(),objtypeCode, socCode1, socCode2, name);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    /**
     * * @param objtypeCode
     * @param socCode1
     * @param socCode2
     * @param code
     * @return
     * @throws RemoteException
     */
    public List<IBasicDTO> listByTypeAndSoc1FilteredByCode(Long objtypeCode, Long socCode1, Long socCode2,
                                                           String code)  throws DataBaseException,SharedApplicationException{
        try {
            return SESSION().listByTypeAndSoc1FilteredByCode(RI(),objtypeCode, socCode1, socCode2, code);
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    

    public IPagingResponseDTO ListByTypeAndSoc2CodeFilteredByCodeWithPaging(IBasicDTO _srchDTO) throws DataBaseException,
                                                                                                       SharedApplicationException {
        try {
            return SESSION().ListByTypeAndSoc2CodeFilteredByCodeWithPaging(RI(),_srchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    public IPagingResponseDTO ListByTypeAndSoc2CodeFilteredWithPaging(IBasicDTO _srchDTO) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            return SESSION().ListByTypeAndSoc2CodeFilteredWithPaging(RI(),_srchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }
    
    public Boolean DisplayTreeFlag(Long objtypeCode, Long soc2Code)  throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().DisplayTreeFlag(RI(),objtypeCode,soc2Code);
        }catch (RemoteException e) {
            SystemException se = new SystemException(e);
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }
}
