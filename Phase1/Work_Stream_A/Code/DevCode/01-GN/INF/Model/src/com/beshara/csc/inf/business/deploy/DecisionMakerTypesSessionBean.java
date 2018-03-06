package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.DecisionMakerTypesDAO;
import com.beshara.csc.inf.business.entity.DecisionMakerTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "DecisionMakerTypesSession", mappedName = "Inf-Model-DecisionMakerTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class DecisionMakerTypesSessionBean extends InfBaseSessionBean implements DecisionMakerTypesSession {

    /**
     * DecisionMakerTypesSession Default Constructor */
    public DecisionMakerTypesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return DecisionMakerTypesEntity.class;
    }

    @Override
    protected DecisionMakerTypesDAO DAO() {
        return (DecisionMakerTypesDAO)super.DAO();
    }

    /**
     * Get list of code and name * return list of IDecisionMakerTypesDTO initialize with code and name only * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();

    }

    public List<IBasicDTO> getCodeNameByMin(IRequestInfoDTO ri, Long minCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        return DAO().getCodeNameByMin(minCode);

    }

    /**
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().searchByCode(code);

    }

    /**
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, String name) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().searchByName(name);

    }

    public List<IBasicDTO> getDecisionMakerTypesByRecType(IRequestInfoDTO ri,
                                                          Long regtypeCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        return DAO().getDecisionMakerTypesByRecType(regtypeCode);

    }
}
