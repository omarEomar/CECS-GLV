package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.ResidentTypeDAO;
import com.beshara.csc.inf.business.dto.ResidentTypeDTO;
import com.beshara.csc.inf.business.entity.ResidentTypeEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "ResidentTypeSession", mappedName = "Inf-Model-ResidentTypeSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(ResidentTypeSession.class)
public class ResidentTypeSessionBean extends InfBaseSessionBean implements ResidentTypeSession {
    public ResidentTypeSessionBean() {
        super();
    }


    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return ResidentTypeEntity.class;
    }

    @Override
    protected ResidentTypeDAO DAO() {
        return (ResidentTypeDAO)super.DAO();
    }

    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {

        return DAO().getCodeName();

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
            }else if(((ResidentTypeDTO)basicDTO).getResidentTypeName().equals(dto.getName())){
                return false;
            }else if(dto != null){
                return true;
            }
            
        } catch (ItemNotFoundException e) {
            return true;
        }
        return false;
    }
    
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO residentTypeDTO) throws DataBaseException,
                                                                              SharedApplicationException {

        if (isNameExist(residentTypeDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.add(ri, residentTypeDTO);
    }

    public Boolean update(IRequestInfoDTO ri,IBasicDTO  residentTypeDTO) throws DataBaseException, SharedApplicationException {
        if (isNameExistForEdit(residentTypeDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.update(ri,residentTypeDTO);
    }

}
