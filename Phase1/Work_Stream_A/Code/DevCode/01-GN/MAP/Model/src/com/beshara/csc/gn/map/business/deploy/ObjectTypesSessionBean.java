package com.beshara.csc.gn.map.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.gn.map.business.dao.ObjectTypesDAO;
import com.beshara.csc.gn.map.business.entity.ObjectTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
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
@Stateless(name = "ObjectTypesSession", mappedName = "Map-Model-ObjectTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(ObjectTypesSession.class)
public class ObjectTypesSessionBean extends MapBaseSessionBean implements ObjectTypesSession {
    public ObjectTypesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return ObjectTypesEntity.class;
    }

    @Override
    protected ObjectTypesDAO DAO() {
        return (ObjectTypesDAO)super.DAO();
    }

    /**
     * removes a list of Object types * @param objectTypesDTOList
     * @return List
     */
    @Override
    public List remove(IRequestInfoDTO ri, List<IBasicDTO> objectTypesDTOList) throws DataBaseException,
                                                                                      SharedApplicationException {
        List resultList = new ArrayList();
        for (IBasicDTO objectTypeDTO : objectTypesDTOList) {
            try {
                super.remove(ri, objectTypeDTO);
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(objectTypeDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (ItemNotFoundException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(objectTypeDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(objectTypeDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(objectTypeDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                resultList.add(resultDTO);
            }
        }
        return resultList;
    }


    @Override
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws DataBaseException,
                                                                                SharedApplicationException {
        List<IBasicDTO> objList = null;
        if (code instanceof Long) {
            objList = DAO().searchByCode(code);
            if (objList == null || objList.size() == 0) {
                throw new NoResultException();
            }
            return objList;
        } else {
            throw new InvalidDataEntryException();
        }
    }

    /**
     * get all ObjectTypesDTOs with code and name set * @return List of IBasicDTO
     * @throws RemoteException
     */
    @Override
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }

    @Override
    public List<IBasicDTO> getObjectTypeSearchCrieteria(IRequestInfoDTO ri, String searchQuery,
                                                        String searchType) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return DAO().getObjectTypeSearchCrieteria(searchQuery, searchType);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

}
