package com.beshara.csc.inf.business.dao.emp;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dao.InfBaseDAO;
import com.beshara.csc.inf.business.dto.IInfReasonTypesDTO;
import com.beshara.csc.inf.business.entity.emp.IInfReasonTypesEntityKey;
import com.beshara.csc.inf.business.entity.emp.InfReasonTypesEntity;
import com.beshara.csc.inf.business.facade.InfEntityConvertr;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;


public class InfReasonTypesDAO extends InfBaseDAO {
    public InfReasonTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfReasonTypesDAO();
    }

    @Override
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfReasonTypesEntity> list =
                EM().createNamedQuery("InfReasonTypesEntity.findAll").setHint("toplink.refresh",
                                                                              "true").getResultList();
            for (InfReasonTypesEntity reasonTypes : list) {
                arrayList.add(InfEntityConvertr.getReasonTypesDTO(reasonTypes));
            }
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    @Override
    public IInfReasonTypesDTO add(IBasicDTO reasonTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfReasonTypesDTO reasonTypesDTO = (IInfReasonTypesDTO)reasonTypesDTO1;
            InfReasonTypesEntity reasonTypesEntity = new InfReasonTypesEntity();
            reasonTypesEntity.setRestypeName(reasonTypesDTO.getRestypeName());
            doAdd(reasonTypesEntity);
            return reasonTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    @Override
    public Boolean update(IBasicDTO reasonTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfReasonTypesDTO reasonTypesDTO = (IInfReasonTypesDTO)reasonTypesDTO1;
            InfReasonTypesEntity reasonTypesEntity =
                EM().find(InfReasonTypesEntity.class, (IInfReasonTypesEntityKey)reasonTypesDTO.getCode());
            reasonTypesEntity.setRestypeName(reasonTypesDTO.getRestypeName());
            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    @Override
    public Boolean remove(IBasicDTO reasonTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfReasonTypesDTO reasonTypesDTO = (IInfReasonTypesDTO)reasonTypesDTO1;
            InfReasonTypesEntity reasonTypesEntity =
                EM().find(InfReasonTypesEntity.class, (IInfReasonTypesEntityKey)reasonTypesDTO.getCode());
            if (reasonTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(reasonTypesEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    @Override
    public IBasicDTO getById(IEntityKey entityKey) throws DataBaseException, SharedApplicationException {
        try {
            IInfReasonTypesEntityKey id = (IInfReasonTypesEntityKey)entityKey;
            InfReasonTypesEntity reasonTypesEntity = EM().find(InfReasonTypesEntity.class, id);
            if (reasonTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfReasonTypesDTO reasonTypesDTO = InfEntityConvertr.getReasonTypesDTO(reasonTypesEntity);
            return reasonTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    @Override
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }

    /**
     * Get list of code and name * return list of IReasonTypesDTO initialize with code and name only * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("InfReasonTypesEntity.getCodeName").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

}
