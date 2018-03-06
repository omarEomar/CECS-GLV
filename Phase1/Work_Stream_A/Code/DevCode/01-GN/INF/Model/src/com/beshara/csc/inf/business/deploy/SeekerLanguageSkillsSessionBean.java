package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.SeekerLanguageSkillsDAO;
import com.beshara.csc.inf.business.entity.SeekerLanguageSkillsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
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
@Stateless(name = "SeekerLanguageSkillsSession", mappedName = "Inf-Model-SeekerLanguageSkillsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(SeekerLanguageSkillsSession.class)
public class SeekerLanguageSkillsSessionBean extends InfBaseSessionBean implements SeekerLanguageSkillsSession { //@PersistenceContext ( unitName = "Inf" )

    public SeekerLanguageSkillsSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return SeekerLanguageSkillsEntity.class;
    }

    @Override
    protected SeekerLanguageSkillsDAO DAO() {
        return (SeekerLanguageSkillsDAO)super.DAO();
    }

    public List<IBasicDTO> listAvailableEntities(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                          SharedApplicationException {
        return DAO().listAvailableEntities(civilId);

    }

    public List<ResultDTO> add(IRequestInfoDTO ri, List<IBasicDTO> list) throws SharedApplicationException,
                                                                                DataBaseException {
        Boolean tansactionBegin = isTransactionBegun();
        if (tansactionBegin) {
            suspendTransaction();
        }

        List resultList = new ArrayList();
        for (IBasicDTO seekerLanguageSkillsDTO : list) {
            try {
                beginTransaction();
                super.add(seekerLanguageSkillsDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_ADDED);
                resultList.add(resultDTO);
            } catch (DataBaseException e) {
                SystemException se = new SystemException(e);
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_ADDED);
                resultDTO.setDatabaseException(new DataBaseException(se.getSQLExceptionMessage()));
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_ADDED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_ADDED);
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

    /**
     * @param noSignDTO
     * @return INoSignDTO
     */
    public List<ResultDTO> update(IRequestInfoDTO ri, List<IBasicDTO> list) throws SharedApplicationException,
                                                                                   DataBaseException {
        Boolean tansactionBegin = isTransactionBegun();
        if (tansactionBegin) {
            suspendTransaction();
        }

        List resultList = new ArrayList();
        for (IBasicDTO seekerLanguageSkillsDTO : list) {
            try {
                beginTransaction();
                super.update(seekerLanguageSkillsDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillsDTO);
                resultList.add(resultDTO);
            } catch (DataBaseException e) {
                SystemException se = new SystemException(e);
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillsDTO);
                resultDTO.setDatabaseException(new DataBaseException(se.getSQLExceptionMessage()));
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillsDTO);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillsDTO);
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

    public List remove(IRequestInfoDTO ri, List<IBasicDTO> list) throws SharedApplicationException, DataBaseException {
        Boolean tansactionBegin = isTransactionBegun();
        if (tansactionBegin) {
            suspendTransaction();
        }

        List resultList = new ArrayList();
        for (IBasicDTO seekerLanguageSkillDTO : list) {
            try {
                beginTransaction();
                DAO().remove(seekerLanguageSkillDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (DataBaseException e) {
                SystemException se = new SystemException(e);
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillDTO);
                resultDTO.setDatabaseException(new DataBaseException(se.getSQLExceptionMessage()));
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillDTO);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(seekerLanguageSkillDTO);
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


}
