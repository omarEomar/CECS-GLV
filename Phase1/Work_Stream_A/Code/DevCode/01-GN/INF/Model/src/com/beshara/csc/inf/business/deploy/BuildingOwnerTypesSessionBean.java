package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.BuildingOwnerTypesDAO;
import com.beshara.csc.inf.business.dto.BuildingOwnerTypesDTO;
import com.beshara.csc.inf.business.dto.GenderTypesDTO;
import com.beshara.csc.inf.business.entity.BuildingOwnerTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
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
@Stateless(name = "BuildingOwnerTypesSession", mappedName = "Inf-Model-BuildingOwnerTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(BuildingOwnerTypesSession.class)
public class BuildingOwnerTypesSessionBean extends InfBaseSessionBean implements BuildingOwnerTypesSession {

    /**
     * JobsSession Default Constructor */
    public BuildingOwnerTypesSessionBean() {
        super();

    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return BuildingOwnerTypesEntity.class;
    }

    @Override
    protected BuildingOwnerTypesDAO DAO() {
        return (BuildingOwnerTypesDAO)super.DAO();
    }
   
    @Override
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO genderTypesDTO) throws DataBaseException,
                                                                              SharedApplicationException {

        if (isNameExist(genderTypesDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.add(ri, genderTypesDTO);
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
            }else if(((BuildingOwnerTypesDTO)basicDTO).getBuildingOwnerName().equals(dto.getName())){
                return false;
            }else if(dto != null){
                return true;
            }
            
        } catch (ItemNotFoundException e) {
            return true;
        }
        return false;
    }
    
    @Override
    public Boolean update(IRequestInfoDTO ri,IBasicDTO  basicDTO) throws DataBaseException, SharedApplicationException {
        if (isNameExistForEdit(basicDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.update(ri,basicDTO);
    }

    /**
     * @return List
     */
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {

        return DAO().getCodeName();

    }

    public List remove(IRequestInfoDTO ri, List<IBasicDTO> buildingOwnerList) throws DataBaseException {
        List resultList = new ArrayList();
        for (IBasicDTO buildingOwnerDTO : buildingOwnerList) {
            try {
                beginTransaction();
                DAO().remove(buildingOwnerDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(buildingOwnerDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (ItemNotFoundException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(buildingOwnerDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(buildingOwnerDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(buildingOwnerDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                resultList.add(resultDTO);
            }
        }
        return resultList;
    }
}
