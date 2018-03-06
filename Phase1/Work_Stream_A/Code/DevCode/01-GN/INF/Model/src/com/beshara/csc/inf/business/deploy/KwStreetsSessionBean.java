package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.KwStreetsDAO;
import com.beshara.csc.inf.business.dto.IKwStreetsDTO;
import com.beshara.csc.inf.business.dto.KwStreetsDTO;
import com.beshara.csc.inf.business.entity.KwStreetsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.rmi.RemoteException;

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
@Stateless(name = "KwStreetsSession", mappedName = "Inf-Model-KwStreetsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class KwStreetsSessionBean extends InfBaseSessionBean implements KwStreetsSession {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    /**
     * JobsSession Default Constructor */
    public KwStreetsSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return KwStreetsEntity.class;
    }

    @Override
    protected KwStreetsDAO DAO() {
        return (KwStreetsDAO)super.DAO();
    }

    private boolean isNameExist(IBasicDTO basicDTO) {
        try {
            return (DAO().getByName(basicDTO.getName()) != null);
        } catch (DataBaseException e) {
            return true;
        } catch (ItemNotFoundException e) {
            return true;
        } catch (SharedApplicationException e) {
            return true;
        }
    }
    
    private boolean isNameExistForEdit(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            IKwStreetsDTO dto =(IKwStreetsDTO)DAO().getByName(basicDTO.getName());
            if(dto == null){
                   return false;
            }else if(((KwStreetsDTO)basicDTO).getKwStreetsName().equals(dto.getName())){
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
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO kwStreetDTO) throws DataBaseException,
                                                                           SharedApplicationException {

        if (isNameExist(kwStreetDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.add(ri, kwStreetDTO);
    }
    
    public Boolean update(IRequestInfoDTO ri,IBasicDTO  kwStreetDTO) throws DataBaseException, SharedApplicationException {
        if (isNameExistForEdit(kwStreetDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.update(ri,kwStreetDTO);
    }

    /**
     * get all classifications from Classifications table * @return List of IClassificationsDTO
     * @throws RemoteException
     */
    @Override
    public List<IKwStreetsDTO> getAll(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getAll();
    }

    @Override
    public List remove(IRequestInfoDTO ri, List<IBasicDTO> kwStreetsDTOList) throws DataBaseException,
                                                                                    SharedApplicationException {
        boolean transactionBegun = isTransactionBegun();
        if (transactionBegun) {
            suspendTransaction();
        }

        List resultList = new ArrayList();
        for (IBasicDTO kwStreetsDTO : kwStreetsDTOList) {
            try {
                beginTransaction();
                super.remove(ri, kwStreetsDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(kwStreetsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (ItemNotFoundException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(kwStreetsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(kwStreetsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(kwStreetsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                resultList.add(resultDTO);
            }
        }
        if (transactionBegun) {
            resumeTransaction();
        }

        return resultList;
    }

    /**
     * @return List
     */
    @Override
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }

    
}
