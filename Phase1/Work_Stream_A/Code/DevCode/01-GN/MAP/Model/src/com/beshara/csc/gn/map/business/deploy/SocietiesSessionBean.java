package com.beshara.csc.gn.map.business.deploy;


import com.beshara.base.deploy.BasicSessionBean;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.gn.map.business.dao.SocietiesDAO;
import com.beshara.csc.gn.map.business.dao.SocietyRelationTypesDAO;
import com.beshara.csc.gn.map.business.entity.SocietiesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import com.beshara.csc.sharedutils.business.exception.SystemException;
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
@Stateless(name = "SocietiesSession", mappedName = "Map-Model-SocietiesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(SocietiesSession.class)
public class SocietiesSessionBean extends MapBaseSessionBean implements SocietiesSession {
    public SocietiesSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return SocietiesEntity.class;
    }

    @Override
    protected SocietiesDAO DAO() {
        return (SocietiesDAO)super.DAO();
    }

    /**
     * update an existing ISocietiesDTO * @param soc1DTO the dto to update
     * @return Boolean
     * @throws SharedApplicationException
     */
    public Boolean update(IRequestInfoDTO ri, IBasicDTO societiesDTO) throws SharedApplicationException,
                                                                             DataBaseException {
        return DAO().update(societiesDTO);
    }

    /**
     * Remove an existing dto * @param soc1DTO the dto to be removed
     * @return Boolean
     * @throws SharedApplicationException
     */
    public Boolean remove(IRequestInfoDTO ri, IBasicDTO societiesDTO) throws SharedApplicationException,
                                                                             DataBaseException {
        return DAO().remove(societiesDTO);
    }

    /**
     * Get SocietiesEntity By Primary Key * @param id Id for the requiredDto
     * @return ISocietiesDTO
     * @throws ItemNotFoundException
     */
    public IBasicDTO getById(IRequestInfoDTO ri, IEntityKey id) throws SharedApplicationException, DataBaseException {
        return DAO().getById(id);
    }

    /**
     * get all data with specific objectTypeCode * @param objtypeCode
     * @return List ISocietiesDTO
     */
    public List<IBasicDTO> listByObjectType(IRequestInfoDTO ri, Long objtypeCode) throws DataBaseException,
                                                                                         SharedApplicationException {

        return DAO().ListByObjectType(objtypeCode);
    }



    public List<IBasicDTO> getAllByFalg(IRequestInfoDTO ri, Long flag) throws DataBaseException,
                                                                              SharedApplicationException {

        return DAO().getAllByFalg(flag);

    }

    /**
     * removes a list of societoies * @param societiesDTOList
     * @return List
     */
    public List remove(List<IBasicDTO> societiesDTOList) throws DataBaseException {
        List resultList = new ArrayList();
        boolean tansactionBegin = isTransactionBegun();
        if (tansactionBegin) {
            suspendTransaction();
        }
        for (IBasicDTO societoiesDTO : societiesDTOList) {
            try {
                beginTransaction();
                DAO().remove(societoiesDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(societoiesDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            }  catch (DataBaseException e) {
                SystemException se = new SystemException(e);
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(societoiesDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(se.getSQLExceptionMessage()));
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(societoiesDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(societoiesDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                resultList.add(resultDTO);
            } finally {
                if (tansactionBegin) {
                    resumeTransaction();
                }

            }
        }
        return resultList;
    }

    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, String name) throws SharedApplicationException,
                                                                                DataBaseException {
        return DAO().searchByName(name);

    }

    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws DataBaseException,
                                                                                SharedApplicationException {
        List<IBasicDTO> socList = null;
        if (code instanceof Long) {
            socList = DAO().searchByCode(code);
            if (socList == null || socList.size() == 0) {
                throw new NoResultException();
            }
            return socList;
        } else {
            throw new InvalidDataEntryException();
        }
    }


    public List<IBasicDTO> searchByMinCode(IRequestInfoDTO ri, Long minCode) throws DataBaseException,
                                                                                    SharedApplicationException {
        return DAO().searchByMinCode(minCode);

    }

    public List<IBasicDTO> searchByNameAndStatus(IRequestInfoDTO ri, String name,
                                                 String SystemOrCenterFlag) throws DataBaseException,
                                                                                   SharedApplicationException {

        return DAO().searchByNameAndStatus(name, SystemOrCenterFlag);

    }

}
