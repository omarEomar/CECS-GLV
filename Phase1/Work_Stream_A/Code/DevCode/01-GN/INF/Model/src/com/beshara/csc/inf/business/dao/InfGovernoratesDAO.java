package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfGovernoratesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IInfGovernoratesEntityKey;
import com.beshara.csc.inf.business.entity.InfGovernoratesEntity;
import com.beshara.csc.inf.business.entity.InfGovernoratesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class InfGovernoratesDAO extends InfBaseDAO {
    public InfGovernoratesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfGovernoratesDAO();
    }

    /**<code>select o from InfGovernoratesEntity o</code>.
     * @return List
     */
    @Override
    public List<IInfGovernoratesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfGovernoratesEntity> list =
                EM().createNamedQuery("InfGovernoratesEntity.findAll").setHint("toplink.refresh",
                                                                               "true").getResultList();
            for (InfGovernoratesEntity infGovernorates : list) {
                arrayList.add(InfDTOFactory.createGovernoratesDTO(infGovernorates));
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


    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("InfGovernoratesEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(0);
            } else {
                return id + 1L;
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * Create a New InfGovernoratesEntity
     * @param infGovernoratesDTO
     * @return InfGovernoratesDTO
     */
    @Override
    public IInfGovernoratesDTO add(IBasicDTO infGovernoratesDTO1) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            InfGovernoratesEntity infGovernoratesEntity = new InfGovernoratesEntity();
            Long code = findNewId();
            IInfGovernoratesDTO infGovernoratesDTO = (IInfGovernoratesDTO)infGovernoratesDTO1;
            infGovernoratesEntity.setGovernorateCode(code);
            infGovernoratesEntity.setGovernorateName(infGovernoratesDTO.getName());
            doAdd(infGovernoratesEntity);

            infGovernoratesDTO.setCode(new InfGovernoratesEntityKey(code));
            infGovernoratesDTO.setGovernorateCode(code);
            return infGovernoratesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * Update an Existing InfGovernoratesEntity
     * @param infGovernoratesDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO infGovernoratesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfGovernoratesDTO infGovernoratesDTO = (IInfGovernoratesDTO)infGovernoratesDTO1;
            InfGovernoratesEntity infGovernoratesEntity =
                EM().find(InfGovernoratesEntity.class, (IInfGovernoratesEntityKey)infGovernoratesDTO.getCode());

            infGovernoratesEntity.setGovernorateCode(((IInfGovernoratesEntityKey)infGovernoratesDTO.getCode()).getGovernorateCode());
            infGovernoratesEntity.setGovernorateName(infGovernoratesDTO.getName());
            doUpdate(infGovernoratesEntity);
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

    /**
     * Remove an Existing InfGovernoratesEntity
     * @param infGovernoratesDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO infGovernoratesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfGovernoratesDTO infGovernoratesDTO = (IInfGovernoratesDTO)infGovernoratesDTO1;
            InfGovernoratesEntity infGovernoratesEntity =
                EM().find(InfGovernoratesEntity.class, (InfGovernoratesEntityKey)infGovernoratesDTO.getCode());
            if (infGovernoratesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(infGovernoratesEntity);
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

    /**
     * Get InfGovernoratesEntity By Primary Key
     * @param id
     * @return InfGovernoratesDTO
     */
    @Override
    public IInfGovernoratesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfGovernoratesEntityKey id = (InfGovernoratesEntityKey)id1;

            InfGovernoratesEntity infGovernoratesEntity = EM().find(InfGovernoratesEntity.class, id);
            if (infGovernoratesEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfGovernoratesDTO infGovernoratesDTO = InfDTOFactory.createGovernoratesDTO(infGovernoratesEntity);
            return infGovernoratesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * Search for InfGovernoratesEntity
     * <br>
     * @return List
     */
    @Override
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }

    /**
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("InfGovernoratesEntity.getCodeName").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    /**
     * Get all by code equal code * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfGovernoratesEntity> list =
                EM().createNamedQuery("InfGovernoratesEntity.searchByCode").setParameter("governorateCode",
                                                                                         (Long)code).getResultList();
            for (InfGovernoratesEntity entity : list) {
                arrayList.add(InfDTOFactory.createGovernoratesDTO(entity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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

    /**
     * Get all by name like string * @param name
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfGovernoratesEntity> list =
                EM().createNamedQuery("InfGovernoratesEntity.searchByName").setParameter("governorateName",
                                                                                         name).getResultList();
            for (InfGovernoratesEntity entity : list) {
                arrayList.add(InfDTOFactory.createGovernoratesDTO(entity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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
}
