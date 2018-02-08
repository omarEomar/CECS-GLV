package com.beshara.csc.gn.map.business.deploy;


import com.beshara.base.deploy.BasicSessionBean;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.gn.map.business.dao.RelationsDAO;
import com.beshara.csc.gn.map.business.dao.SocietiesDAO;
import com.beshara.csc.gn.map.business.entity.RelationsEntity;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
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
 * @author Beshara Group
 * @author Heba.Ahmed
 * @version 1.0
 * @since 31/12/2014
 */
@Stateless(name = "RelationsSession", mappedName = "Map-Model-RelationsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(RelationsSession.class)
public class RelationsSessionBean extends MapBaseSessionBean implements RelationsSession {
    public RelationsSessionBean() {

    }


    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return RelationsEntity.class;
    }

    @Override
    protected RelationsDAO DAO() {
        return (RelationsDAO)super.DAO();
    }

    /**
     * removes a list of Object types * @param objectTypesDTOList
     * @return List
     */
    public List remove(IRequestInfoDTO ri, List<IBasicDTO> relationDTOList) throws DataBaseException,
                                                                                   SharedApplicationException {
        List resultList = new ArrayList();
        boolean tansactionBegin = isTransactionBegun();
        if (tansactionBegin) {
            suspendTransaction();
        }
        for (IBasicDTO relationDTO : relationDTOList) {
            try {
                beginTransaction();
                DAO().remove(relationDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(relationDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (DataBaseException e) {
                SystemException se = new SystemException(e);
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(relationDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(se.getSQLExceptionMessage()));
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(relationDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(relationDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                resultList.add(resultDTO);
                rollbackTransaction();
            } finally {
                if (tansactionBegin) {
                    resumeTransaction();
                }

            }
        }
        return resultList;
    }

}
