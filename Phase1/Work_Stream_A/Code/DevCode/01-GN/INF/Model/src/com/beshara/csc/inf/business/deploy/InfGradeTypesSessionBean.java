package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.InfGradeTypesDAO;
import com.beshara.csc.inf.business.dto.InfGradeTypesDTO;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "InfGradeTypesSession", mappedName = "Inf-Model-InfGradeTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote

public class InfGradeTypesSessionBean extends InfBaseSessionBean implements InfGradeTypesSession {
    public InfGradeTypesSessionBean() {
    }


    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfGradeTypesEntity.class;
    }

    @Override
    protected InfGradeTypesDAO DAO() {
        return (InfGradeTypesDAO)super.DAO();
    }

    @Override
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {

        if (DAO().checkDublicateName(basicDTO.getName()).size() > 0) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.add(ri, basicDTO);
    }

    public Double getFormulaByGradeType(IRequestInfoDTO ri, Long gradeTypeCode,
                                        String gradeValue) throws DataBaseException, SharedApplicationException {
        return DAO().getFormulaByGradeType(gradeTypeCode, gradeValue);
    }
    
    private boolean isNameExistForEdit(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
      
             List<IBasicDTO> nameList = DAO().checkDublicateName(basicDTO.getName());
            if(nameList != null && nameList.size() > 0 ){
              if(((InfGradeTypesDTO)basicDTO).getGradeTypeName().equals(nameList.get(0).getName())){
                return false;
               }else {
                return true;
               }
            }
       
        return false;
    }
    
    public Boolean update(IRequestInfoDTO iRequestInfoDTO, IBasicDTO iBasicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        if (isNameExistForEdit(iBasicDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.update(iRequestInfoDTO, iBasicDTO);
    }
}
