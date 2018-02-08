package com.beshara.csc.gn.map.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.gn.map.business.dao.MappedDataDAO;
import com.beshara.csc.gn.map.business.dto.IMappedDataDTO;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.gn.map.business.entity.MappedDataEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.cementj.base.trans.TransactionException;


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
@Stateless(name = "MappedDataSession", mappedName = "Map-Model-MappedDataSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(MappedDataSession.class)
public class MappedDataSessionBean extends MapBaseSessionBean implements MappedDataSession {
    public MappedDataSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return MappedDataEntity.class;
    }

    @Override
    protected MappedDataDAO DAO() {
        return (MappedDataDAO)super.DAO();
    }

    @Override
    public Boolean remove(IRequestInfoDTO ri, IBasicDTO mappedDataDTO) throws DataBaseException,
                                                                              SharedApplicationException {
        IMappedDataDTO mappedDataDTO1 = (IMappedDataDTO)mappedDataDTO;
        IMappedDataDTO mappedDataDTO2 = MapDTOFactory.createMappedDataDTO();
        mappedDataDTO2.setObjtype1Code(mappedDataDTO1.getObjtype2Code());
        mappedDataDTO2.setObjtype2Code(mappedDataDTO1.getObjtype1Code());
        mappedDataDTO2.setSoc1Code(mappedDataDTO1.getSoc2Code());
        mappedDataDTO2.setSoc2Code(mappedDataDTO1.getSoc1Code());
        mappedDataDTO2.setSoc1Value(mappedDataDTO1.getSoc2Value());
        mappedDataDTO2.setSoc2Value(mappedDataDTO1.getSoc1Value());
        DAO().remove(mappedDataDTO);
        try {
            DAO().remove(mappedDataDTO2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

    /**
     * removes a list of Mapped Data * @param mappedDataList
     * @return List
     */
    @Override
    public List remove(IRequestInfoDTO ri, List<IBasicDTO> mappedDataList) throws DataBaseException,
                                                                                  SharedApplicationException {
        List resultList = new ArrayList();
        for (IBasicDTO mappedDataDTO : mappedDataList) {
            try {
                remove(ri, mappedDataDTO);
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(mappedDataDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (ItemNotFoundException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(mappedDataDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(mappedDataDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(mappedDataDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                resultList.add(resultDTO);
            }
        }
        return resultList;
    }

    /**
     * Create list of IMappedDataDTO mapped to a specific IMappedDataDTO * @param mappedDataList
     * @return Boolean
     * @throws RemoteException
     */
    @Override
    public Boolean createMappedDataList(IRequestInfoDTO ri, List<IBasicDTO> mappedDataList) throws DataBaseException,
                                                                                                   SharedApplicationException {
        if (mappedDataList != null && mappedDataList.size() > 0) {
            try {
                for (IBasicDTO mappedDto : mappedDataList) {
                    DAO().add(mappedDto);
                }
                return Boolean.TRUE;
            } catch (ItemNotFoundException e) {
                throw new SharedApplicationException();
            } catch (SharedApplicationException e) {
                throw new SharedApplicationException();
            } catch (TransactionException e) {
                throw new SharedApplicationException();
            }
        }
        return Boolean.FALSE;
    }

    /**
     * get list of IMappedDataDTO that maps specific IMappedDataDTO * the given mappedDataDto should have objectType1Code , * objectType2Code soc1Code and soc1Value filled * @param mappedDataDto
     * @return List IMappedDataDTO
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
    @Override
    public List<IBasicDTO> getMappedDataList(IRequestInfoDTO ri, IBasicDTO mappedDataDto) throws DataBaseException,
                                                                                                 SharedApplicationException {
        return DAO().getMappedDataList(mappedDataDto);
    }

    /**
     * update MappedData for specific Dto , the list items should have objectType1Code , * objectType2Code soc1Code and soc1Value filled * @param mappedDataList
     * @return Boolean
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
    @Override
    public Boolean updateMappedDataList(IRequestInfoDTO ri, List<IBasicDTO> mappedDataList) throws DataBaseException,
                                                                                                   SharedApplicationException {
        return DAO().updateMappedDataList(mappedDataList);
    }

    @Override
    public Boolean isValueAlreadyMapped(IRequestInfoDTO ri, Long objtypeCode, Long socCode1, Long socCode2,
                                        String socValue) throws DataBaseException, SharedApplicationException {
        return DAO().isValueAlreadyMapped(objtypeCode, socCode1, socCode2, socValue);
    }

}
