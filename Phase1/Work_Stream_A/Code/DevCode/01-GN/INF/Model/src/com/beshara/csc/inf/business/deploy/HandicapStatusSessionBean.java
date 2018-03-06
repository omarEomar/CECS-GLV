package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.gn.aud.business.client.AudClientFactory;
import com.beshara.csc.gn.aud.business.client.IAudTabRecordsCMTClient;
import com.beshara.csc.inf.business.dao.HandicapStatusDAO;
import com.beshara.csc.inf.business.dto.HandicapStatusDTO;
import com.beshara.csc.inf.business.dto.IHandicapStatusDTO;
import com.beshara.csc.inf.business.entity.HandicapStatusEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "HandicapStatusSession", mappedName = "Inf-Model-HandicapStatusSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(HandicapStatusSession.class)
public class HandicapStatusSessionBean extends InfBaseSessionBean implements HandicapStatusSession {

    public HandicapStatusSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return HandicapStatusEntity.class;
    }

    @Override
    protected HandicapStatusDAO DAO() {
        return (HandicapStatusDAO)super.DAO();
    }

    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws SharedApplicationException, DataBaseException {

        return DAO().getCodeName();

    }

    public List<IBasicDTO> getCodeNameExceptOne(IRequestInfoDTO ri) throws SharedApplicationException, DataBaseException{
       
            return DAO().getCodeNameExceptOne();
       
    }

    /**
     * @param _handicapStatusDTO
     * @return IHandicapStatusDTO
     */
    public IBasicDTO add(IRequestInfoDTO ri,IBasicDTO _handicapStatusDTO) throws SharedApplicationException,
                                                              DataBaseException {
        IHandicapStatusDTO handicapStatusDTO = (IHandicapStatusDTO)_handicapStatusDTO;
        if (isNameExist(handicapStatusDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        
        handicapStatusDTO.setAuditStatus(1l);

        if (handicapStatusDTO.getHandicapRatio() == null) {
            handicapStatusDTO.setHandicapRatio(0D);
        }
        return DAO().add(handicapStatusDTO);
    }

    /**
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri,Object code) throws DataBaseException, SharedApplicationException {
        try {
            return DAO().searchByCode(code);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    /**
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByName(IRequestInfoDTO ri,String name) throws DataBaseException, SharedApplicationException {
        try {
            return DAO().searchByName(name);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    public java.lang.Boolean update(IRequestInfoDTO ri,IBasicDTO _handicapStatusDTO) throws SharedApplicationException,
                                                                         DataBaseException {
        IHandicapStatusDTO handicapStatusDTO = (IHandicapStatusDTO)_handicapStatusDTO;
        if (isNameExistForEdit(handicapStatusDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        handicapStatusDTO.setAuditStatus(1l);
        return DAO().update(handicapStatusDTO);
    }

    public void postRecord(IRequestInfoDTO ri,IBasicDTO dto1) throws DataBaseException, SharedApplicationException {

        String tableName = "INF_HANDICAP_STATUS";
        IHandicapStatusDTO dto = (IHandicapStatusDTO)dto1;
        dto.setAuditStatus(new Long(2));
        DAO().update(dto);
        IAudTabRecordsCMTClient audTabRecordsCMTClient = AudClientFactory.getAudTabRecordsCMTClient();
        audTabRecordsCMTClient.postRecord(tableName, dto.getTabrecSerial());

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
            }else if(((HandicapStatusDTO)basicDTO).getCapstatusName().equals(dto.getName())){
                return false;
            }else if(dto != null){
                return true;
            }
            
        } catch (ItemNotFoundException e) {
            return true;
        }
        return false;
    }
}
