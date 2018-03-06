package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.SpecialCaseTypesDAO;
import com.beshara.csc.inf.business.dto.SpecialCaseTypesDTO;
import com.beshara.csc.inf.business.entity.SpecialCaseTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.cementj.base.trans.TransactionException;


@Stateless(name = "SpecialCaseTypesSession", mappedName = "Inf-Model-SpecialCaseTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(SpecialCaseTypesSession.class)
public class SpecialCaseTypesSessionBean extends InfBaseSessionBean implements SpecialCaseTypesSession {

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return SpecialCaseTypesEntity.class;
    }

    @Override
    protected SpecialCaseTypesDAO DAO() {
        return (SpecialCaseTypesDAO)super.DAO();
    }

    public SpecialCaseTypesSessionBean() {
        super();
    }

    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws SharedApplicationException, DataBaseException {
        return DAO().getCodeName();

    }


    public List remove(IRequestInfoDTO ri, List<IBasicDTO> specialCasesList) throws SharedApplicationException,
                                                                                    DataBaseException {
        Boolean tansactionBegin = isTransactionBegun();
        if (tansactionBegin) {
            suspendTransaction();
        }

        List resultList = new ArrayList();
        for (IBasicDTO specialCaseDTO : specialCasesList) {
            try {
                beginTransaction();
                DAO().remove(specialCaseDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(specialCaseDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(specialCaseDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (DataBaseException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(specialCaseDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(specialCaseDTO);
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


    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws DataBaseException,
                                                                                SharedApplicationException {
        if (code instanceof Long) {
            try {
                return DAO().searchByCode(code);
            } catch (ItemNotFoundException e) {
                throw new NoResultException();
            }
        } else {
            throw new InvalidDataEntryException();
        }

    }


    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, String name) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().searchByName(name);


    }


    private boolean isNameExist(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            return (DAO().getByName(basicDTO.getName()) != null);
        } catch (ItemNotFoundException e) {
            return true;
        }
    }
    
    private boolean isNameExistForEdit(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            IBasicDTO dto =DAO().getByName(basicDTO.getName());
            if(dto == null){
                   return false;
            }else if(((SpecialCaseTypesDTO)basicDTO).getSpccsetypeName().equals(dto.getName())){
                return false;
            }else if(dto != null){
                return true;
            }
            
        } catch (ItemNotFoundException e) {
            return true;
        }
        return false;
    }
    
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO specialCaseTypesDTO) throws DataBaseException,
                                                                              SharedApplicationException {

        if (isNameExist(specialCaseTypesDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.add(ri, specialCaseTypesDTO);
    }

    public Boolean update(IRequestInfoDTO ri,IBasicDTO  specialCaseTypesDTO) throws DataBaseException, SharedApplicationException {
        if (isNameExistForEdit(specialCaseTypesDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.update(ri,specialCaseTypesDTO);
    }
}
