package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.ApprovalMakersDAO;
import com.beshara.csc.inf.business.dto.ApprovalMakersDTO;
import com.beshara.csc.inf.business.entity.ApprovalMakersEntity;
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
@Stateless(name = "ApprovalMakersSession", mappedName = "Inf-Model-ApprovalMakersSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class ApprovalMakersSessionBean extends InfBaseSessionBean implements ApprovalMakersSession { //@PersistenceContext ( unitName = "Inf" )

    /**
     * JobsSession Default Constructor */
    public ApprovalMakersSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return ApprovalMakersEntity.class;
    }

    @Override
    protected ApprovalMakersDAO DAO() {
        return (ApprovalMakersDAO)super.DAO();
    }


    @Override
    public List<IBasicDTO> simpleSearch(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().simpleSearch(basicDTO);
    }


    @Override
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }

    /**
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    @Override
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().searchByCode(code);

    }

    /**
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    @Override
    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, String name) throws DataBaseException,
                                                                                SharedApplicationException {

        return DAO().searchByName(name);

    }

    @Override
    public List<IBasicDTO> getApprovalmakerForMov(IRequestInfoDTO ri) throws DataBaseException,
                                                                             SharedApplicationException {
        return DAO().getApprovalmakerForMov();
    }


    private boolean isNameExist(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            ApprovalMakersDTO dto = (ApprovalMakersDTO)basicDTO;
            return (DAO().getByName(dto.getAprmakerName()) != null);
        } catch (ItemNotFoundException e) {
            return true;
        }
    }

    private boolean isNameExistForEdit(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            ApprovalMakersDTO _dto = (ApprovalMakersDTO)basicDTO;
            IBasicDTO dto = DAO().getByName(_dto.getAprmakerName());
            if (dto == null) {
                return false;
            } else if (basicDTO.getName().equals(dto.getName())) {
                return false;
            } else if (dto != null) {
                return true;
            }

        } catch (ItemNotFoundException e) {
            return true;
        }
        return false;
    }

    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO iBasicDTO) throws DataBaseException,
                                                                         SharedApplicationException {
        if (isNameExist(iBasicDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.add(ri, iBasicDTO);
    }


    public Boolean update(IRequestInfoDTO iRequestInfoDTO, IBasicDTO iBasicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        if (isNameExistForEdit(iBasicDTO)) {
                   throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.update(iRequestInfoDTO, iBasicDTO);
    }
}
