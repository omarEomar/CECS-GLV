package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.inf.business.dao.FieldTypesDAO;
import com.beshara.csc.inf.business.entity.FieldTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.SystemException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.util.ArrayList;
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
@Stateless(name = "FieldTypesSession", mappedName = "Inf-Model-FieldTypesSessionBean")
@TransactionManagement(TransactionManagementType.BEAN)
@Remote
public class FieldTypesSessionBean extends InfBaseSessionBean implements FieldTypesSession {

    /**
     * JobsSession Default Constructor */
    public FieldTypesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return FieldTypesEntity.class;
    }

    @Override
    protected FieldTypesDAO DAO() {
        return (FieldTypesDAO)super.DAO();
    }

    /**
     * @return List
     */
    @Override
    public List<IBasicDTO> getAll(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {

        return DAO().getAll();

    }

    @Override
    public List remove(IRequestInfoDTO ri, List<IBasicDTO> fieldTypesList) throws DataBaseException,
                                                                                  SharedApplicationException {
        boolean transactionBegun = isTransactionBegun();
        if (transactionBegun) {
            suspendTransaction();
        }

        List resultList = new ArrayList();
        for (IBasicDTO fieldTypesDTO : fieldTypesList) {
            try {
                beginTransaction();
                super.remove(ri, fieldTypesDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(fieldTypesDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (ItemNotFoundException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(fieldTypesDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(fieldTypesDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(fieldTypesDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                resultList.add(resultDTO);
            }catch (Exception e) {
                SystemException se = new SystemException(e);
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(fieldTypesDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(se.getSQLExceptionMessage()));
                resultList.add(resultDTO);
                rollbackTransaction();
            } 
        }
        if (transactionBegun) {
            resumeTransaction();
        }

        return resultList;
    }

    /**
     * @return List
     */
    @Override
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }

}
