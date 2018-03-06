package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.BloodGroupsDAO;
import com.beshara.csc.inf.business.dto.BloodGroupsDTO;
import com.beshara.csc.inf.business.entity.BloodGroupsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

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
@Stateless(name = "BloodGroupsSession", mappedName = "Inf-Model-BloodGroupsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class BloodGroupsSessionBean extends InfBaseSessionBean implements BloodGroupsSession { //@PersistenceContext ( unitName = "Inf" )

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return BloodGroupsEntity.class;
    }

    @Override
    protected BloodGroupsDAO DAO() {
        return (BloodGroupsDAO)super.DAO();
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
            }else if(((BloodGroupsDTO)basicDTO).getBldgroupName().equals(dto.getName())){
                return false;
            }else if(dto != null){
                return true;
            }
            
        } catch (ItemNotFoundException e) {
            return true;
        }
        return false;
    }
    
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO bloodGroupDTO) throws DataBaseException,
                                                                              SharedApplicationException {

        if (isNameExist(bloodGroupDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.add(ri, bloodGroupDTO);
    }

    public Boolean update(IRequestInfoDTO ri,IBasicDTO  bloodGroupDTO) throws DataBaseException, SharedApplicationException {
        if (isNameExistForEdit(bloodGroupDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.update(ri,bloodGroupDTO);
    }
}
