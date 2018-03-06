package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.NewspapersDAO;
import com.beshara.csc.inf.business.dto.INewspapersDTO;
import com.beshara.csc.inf.business.dto.NewspapersDTO;
import com.beshara.csc.inf.business.entity.NewspapersEntity;
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
@Stateless(name = "NewspapersSession", mappedName = "Inf-Model-NewspapersSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class NewspapersSessionBean extends InfBaseSessionBean implements NewspapersSession {
    /**
     * JobsSession Default Constructor */
    public NewspapersSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return NewspapersEntity.class;
    }

    @Override
    protected NewspapersDAO DAO() {
        return (NewspapersDAO)super.DAO();
    }

    public List<INewspapersDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }
    
    private boolean isNameExist(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            return (DAO().getByName(((NewspapersDTO)basicDTO).getPaperName()) != null);
        } catch (ItemNotFoundException e) {
            return true;
        }
    }
    
    private boolean isNameExistForEdit(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            IBasicDTO dto =DAO().getByName(((NewspapersDTO)basicDTO).getPaperName());
            if(dto == null){
                   return false;
            }else if(basicDTO.getName().equals(dto.getName())){
                return false;
            }else if(dto != null){
                return true;
            }
            
        } catch (ItemNotFoundException e) {
            return true;
        }
        return false;
    }
    
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO newspapersDTO) throws DataBaseException,
                                                                              SharedApplicationException {

        if (isNameExist(newspapersDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.add(ri, newspapersDTO);
    }

    public Boolean update(IRequestInfoDTO ri,IBasicDTO  newspapersDTO) throws DataBaseException, SharedApplicationException {
        if (isNameExistForEdit(newspapersDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.update(ri,newspapersDTO);
    }
}
