package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfOperationTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.InfOperationTypesDTO;
import com.beshara.csc.inf.business.entity.IInfOperationTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfOperationTypesEntity;
import com.beshara.csc.inf.business.entity.InfOperationTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class InfOperationTypesDAO extends InfBaseDAO {
    public InfOperationTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfOperationTypesDAO();
    }

    public List<InfOperationTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfOperationTypesEntity> list =
                EM().createNamedQuery("InfOperationTypesEntity.findAll").setHint("toplink.refresh",
                                                                                 "true").getResultList();
            for (InfOperationTypesEntity infOperationTypes : list) {
                arrayList.add(new InfOperationTypesDTO(infOperationTypes));
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

    /**
     * Create a New InfOperationTypesEntity
     * @param infOperationTypesDTO
     * @return InfOperationTypesDTO
     */
    public IInfOperationTypesDTO add(IBasicDTO infOperationTypesDTO1) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            InfOperationTypesEntity infOperationTypesEntity = new InfOperationTypesEntity();
            InfOperationTypesDTO infOperationTypesDTO = (InfOperationTypesDTO)infOperationTypesDTO1;
            infOperationTypesDTO.setCode(InfEntityKeyFactory.createInfOperationTypesEntityKey(findNewId()));
            infOperationTypesEntity.setOperationTypeCode(((InfOperationTypesEntityKey)infOperationTypesDTO.getCode()).getOperationTypeCode());
            infOperationTypesEntity.setOperationTypeName(infOperationTypesDTO.getName());
            infOperationTypesEntity.setCreatedBy(infOperationTypesDTO.getCreatedBy());
            infOperationTypesEntity.setCreatedDate(infOperationTypesDTO.getCreatedDate());
            infOperationTypesEntity.setAuditStatus(infOperationTypesDTO.getAuditStatus());
            infOperationTypesEntity.setTabrecSerial(infOperationTypesDTO.getTabrecSerial());
            doAdd(infOperationTypesEntity);
            // Set PK after creation
            return infOperationTypesDTO;
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
     * Update an Existing InfOperationTypesEntity
     * @param infOperationTypesDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO infOperationTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            InfOperationTypesDTO infOperationTypesDTO = (InfOperationTypesDTO)infOperationTypesDTO1;
            InfOperationTypesEntity infOperationTypesEntity =
                EM().find(InfOperationTypesEntity.class, infOperationTypesDTO.getCode());
            infOperationTypesEntity.setOperationTypeCode(((InfOperationTypesEntityKey)infOperationTypesDTO.getCode()).getOperationTypeCode());
            infOperationTypesEntity.setOperationTypeName(infOperationTypesDTO.getName());
            infOperationTypesEntity.setCreatedBy(infOperationTypesDTO.getCreatedBy());
            infOperationTypesEntity.setCreatedDate(infOperationTypesDTO.getCreatedDate());
            infOperationTypesEntity.setLastUpdatedBy(infOperationTypesDTO.getLastUpdatedBy());
            infOperationTypesEntity.setLastUpdatedDate(infOperationTypesDTO.getLastUpdatedDate());
            infOperationTypesEntity.setAuditStatus(infOperationTypesDTO.getAuditStatus());
            infOperationTypesEntity.setTabrecSerial(infOperationTypesDTO.getTabrecSerial());
            doUpdate(infOperationTypesEntity);
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
     * Remove an Existing InfOperationTypesEntity
     * @param infOperationTypesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO infOperationTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfOperationTypesDTO infOperationTypesDTO = (IInfOperationTypesDTO)infOperationTypesDTO1;
            InfOperationTypesEntity infOperationTypesEntity =
                EM().find(InfOperationTypesEntity.class, infOperationTypesDTO.getCode());
            if (infOperationTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(infOperationTypesEntity);
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
     * Get InfOperationTypesEntity By Primary Key
     * @param id
     * @return InfOperationTypesDTO
     */
    public IInfOperationTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfOperationTypesEntityKey id = (IInfOperationTypesEntityKey)id1;
            InfOperationTypesEntity infOperationTypesEntity = EM().find(InfOperationTypesEntity.class, id);
            if (infOperationTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfOperationTypesDTO infOperationTypesDTO =
                InfDTOFactory.createInfOperationTypesDTO(infOperationTypesEntity);
            return infOperationTypesDTO;
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
            Long id = (Long)EM().createNamedQuery("InfOperationTypesEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(1);
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

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("InfOperationTypesEntity.getCodeName").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfOperationTypesEntity> list =
                EM().createNamedQuery("InfOperationTypesEntity.searchByCode").setParameter("operationTypeCode",
                                                                                           (Long)code).getResultList();
            for (InfOperationTypesEntity entity : list) {
                arrayList.add(InfDTOFactory.createInfOperationTypesDTO(entity));
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
     * @throws FinderException
     */
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfOperationTypesEntity> list =
                EM().createNamedQuery("InfOperationTypesEntity.searchByName").setParameter("operationTypeName",
                                                                                           name).getResultList();
            for (InfOperationTypesEntity entity : list) {
                arrayList.add(InfDTOFactory.createInfOperationTypesDTO(entity));
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
