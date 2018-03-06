package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.CurrenciesDAO;
import com.beshara.csc.inf.business.dto.CurrenciesDTO;
import com.beshara.csc.inf.business.entity.CurrenciesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

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
@Stateless(name = "CurrenciesSession", mappedName = "Inf-Model-CurrenciesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class CurrenciesSessionBean extends InfBaseSessionBean implements CurrenciesSession {
    /**
     * JobsSession Default Constructor */
    public CurrenciesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return CurrenciesEntity.class;
    }

    @Override
    protected CurrenciesDAO DAO() {
        return (CurrenciesDAO)super.DAO();
    }


    @Override
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }

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
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                              SharedApplicationException {

        if (isNameExist(basicDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.add(ri, basicDTO);
    }   
    
    private boolean isNameExist(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            CurrenciesDTO dto = (CurrenciesDTO)basicDTO;
            return (DAO().getByName(dto.getCurrencyName()) != null);
        } catch (ItemNotFoundException e) {
            return true;
        }
    }
    
    private boolean isNameExistForEdit(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            CurrenciesDTO _dto = (CurrenciesDTO)basicDTO;
            IBasicDTO dto =DAO().getByName(_dto.getCurrencyName());
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
    
    @Override
    public Boolean update(IRequestInfoDTO ri,IBasicDTO  basicDTO) throws DataBaseException, SharedApplicationException {
        if (isNameExistForEdit(basicDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.update(ri,basicDTO);
    }
}
