package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.GenderMaritalDAO;
import com.beshara.csc.inf.business.dao.MaritalStatusDAO;
import com.beshara.csc.inf.business.dto.IGenderMaritalDTO;
import com.beshara.csc.inf.business.dto.MaritalStatusDTO;
import com.beshara.csc.inf.business.entity.GenderMaritalEntity;
import com.beshara.csc.inf.business.entity.GenderMaritalEntityKey;
import com.beshara.csc.inf.business.entity.IMaritalStatusEntityKey;
import com.beshara.csc.inf.business.entity.MaritalStatusEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

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
@Stateless(name = "MaritalStatusSession", mappedName = "Inf-Model-MaritalStatusSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class MaritalStatusSessionBean extends InfBaseSessionBean implements MaritalStatusSession {

    public MaritalStatusSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return MaritalStatusEntity.class;
    }

    @Override
    protected MaritalStatusDAO DAO() {
        return (MaritalStatusDAO)super.DAO();
    }

    @Override
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }

    /**
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    @Override
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().searchByCode(code);
    }

    /**
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    @Override
    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, String name) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().searchByName(name);
    }

    @Override
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        MaritalStatusDTO maritalStatusDTO = (MaritalStatusDTO)basicDTO;
        GenderMaritalDAO genderMaritalDAO = (GenderMaritalDAO)(super.newDAOInstance(GenderMaritalEntity.class));
        Long maritalStatusCode = 0L;
        if (DAO().checkDublicateName(basicDTO.getName()).size() > 0) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        try {
            maritalStatusDTO = (MaritalStatusDTO)DAO().add(maritalStatusDTO);
            maritalStatusCode = ((IMaritalStatusEntityKey)(maritalStatusDTO).getCode()).getMarstatusCode();


            for (IBasicDTO dto : maritalStatusDTO.getGenderMaritalDTOList()) {
                IGenderMaritalDTO genderMaritalDTO = (IGenderMaritalDTO)dto;
                genderMaritalDTO.setCode(new GenderMaritalEntityKey(genderMaritalDTO.getGentypeCode(),
                                                                    maritalStatusCode));
                genderMaritalDTO.setMarstatusCode(maritalStatusCode);
                genderMaritalDAO.add(genderMaritalDTO);

            }
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        }
        return maritalStatusDTO;
    }

    @Override
    public Boolean update(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                         SharedApplicationException {
        MaritalStatusDTO maritalStatusDTO = (MaritalStatusDTO)basicDTO;
        GenderMaritalDAO genderMaritalDAO = (GenderMaritalDAO)(super.newDAOInstance(GenderMaritalEntity.class));
        List<IBasicDTO> nameList = DAO().checkDublicateName(basicDTO.getName());
        if (nameList != null && nameList.size() > 0) {
            if (!nameList.get(0).getCode().getKey().equals(basicDTO.getCode().getKey())) {
                throw new DataBaseException("EnteredNameAlreadyExist");
            }
        }
        try {
            super.update(ri, maritalStatusDTO);
            for (IBasicDTO dto : maritalStatusDTO.getGenderMaritalDTOList()) {
                IGenderMaritalDTO genderMaritalDTO = (IGenderMaritalDTO)dto;
                genderMaritalDAO.update(genderMaritalDTO);
            }
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

}
