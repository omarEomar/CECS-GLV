package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IPersonDataChangesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IPersonDataChangesEntityKey;
import com.beshara.csc.inf.business.entity.PersonDataChangesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class PersonDataChangesDAO extends InfBaseDAO {
    public PersonDataChangesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new PersonDataChangesDAO();
    }

    public List<IPersonDataChangesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<PersonDataChangesEntity> list =
                EM().createNamedQuery("PersonDataChangesEntity.findAll").setHint("toplink.refresh",
                                                                                 "true").getResultList();
            for (PersonDataChangesEntity personDataChanges : list) {
                arrayList.add(InfDTOFactory.createPersonDataChangesDTO(personDataChanges));
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
     * Create a New PersonDataChangesEntity * @param personDataChangesDTO
     * @return IPersonDataChangesDTO
     */
    public IPersonDataChangesDTO add(IBasicDTO personDataChangesDTO1) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            PersonDataChangesEntity personDataChangesEntity = new PersonDataChangesEntity();
            IPersonDataChangesDTO personDataChangesDTO = (IPersonDataChangesDTO)personDataChangesDTO1;
            personDataChangesEntity.setCivilId(((IPersonDataChangesEntityKey)personDataChangesDTO.getCode()).getCivilId());
            personDataChangesEntity.setParameterCode(((IPersonDataChangesEntityKey)personDataChangesDTO.getCode()).getParameterCode());
            personDataChangesEntity.setChangeDatetime(((IPersonDataChangesEntityKey)personDataChangesDTO.getCode()).getChangeDatetime());
            personDataChangesEntity.setDmlstatypeCode(personDataChangesDTO.getDmlstatypeCode());
            personDataChangesEntity.setOldValue(personDataChangesDTO.getOldValue());
            personDataChangesEntity.setNewValue(personDataChangesDTO.getNewValue());
            personDataChangesEntity.setCreatedBy(personDataChangesDTO.getCreatedBy());
            personDataChangesEntity.setCreatedDate(personDataChangesDTO.getCreatedDate());
            personDataChangesEntity.setAuditStatus(personDataChangesDTO.getAuditStatus());
            personDataChangesEntity.setTabrecSerial(personDataChangesDTO.getTabrecSerial());
            doAdd(personDataChangesEntity);
            // Set PK after creation
            return personDataChangesDTO;
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
     * Update an Existing PersonDataChangesEntity * @param personDataChangesDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO personDataChangesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonDataChangesDTO personDataChangesDTO = (IPersonDataChangesDTO)personDataChangesDTO1;
            PersonDataChangesEntity personDataChangesEntity =
                EM().find(PersonDataChangesEntity.class, (IPersonDataChangesEntityKey)personDataChangesDTO.getCode());
            personDataChangesEntity.setCivilId(((IPersonDataChangesEntityKey)personDataChangesDTO.getCode()).getCivilId());
            personDataChangesEntity.setParameterCode(((IPersonDataChangesEntityKey)personDataChangesDTO.getCode()).getParameterCode());
            personDataChangesEntity.setChangeDatetime(((IPersonDataChangesEntityKey)personDataChangesDTO.getCode()).getChangeDatetime());
            personDataChangesEntity.setDmlstatypeCode(personDataChangesDTO.getDmlstatypeCode());
            personDataChangesEntity.setOldValue(personDataChangesDTO.getOldValue());
            personDataChangesEntity.setNewValue(personDataChangesDTO.getNewValue());
            personDataChangesEntity.setCreatedBy(personDataChangesDTO.getCreatedBy());
            personDataChangesEntity.setCreatedDate(personDataChangesDTO.getCreatedDate());
            personDataChangesEntity.setLastUpdatedBy(personDataChangesDTO.getLastUpdatedBy());
            personDataChangesEntity.setLastUpdatedDate(personDataChangesDTO.getLastUpdatedDate());
            personDataChangesEntity.setAuditStatus(personDataChangesDTO.getAuditStatus());
            personDataChangesEntity.setTabrecSerial(personDataChangesDTO.getTabrecSerial());
            doUpdate(personDataChangesEntity);
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
     * Remove an Existing PersonDataChangesEntity * @param personDataChangesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO personDataChangesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonDataChangesDTO personDataChangesDTO = (IPersonDataChangesDTO)personDataChangesDTO1;
            PersonDataChangesEntity personDataChangesEntity =
                EM().find(PersonDataChangesEntity.class, personDataChangesDTO.getCode());
            if (personDataChangesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(personDataChangesEntity);
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
     * Get PersonDataChangesEntity By Primary Key * @param id
     * @return IPersonDataChangesDTO
     */
    public IPersonDataChangesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonDataChangesEntityKey id = (IPersonDataChangesEntityKey)id1;
            PersonDataChangesEntity personDataChangesEntity = EM().find(PersonDataChangesEntity.class, id);
            if (personDataChangesEntity == null) {
                throw new ItemNotFoundException();
            }
            IPersonDataChangesDTO personDataChangesDTO =
                InfDTOFactory.createPersonDataChangesDTO(personDataChangesEntity);
            return personDataChangesDTO;
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
            Long id = (Long)EM().createNamedQuery("PersonDataChangesEntity.findNewId").getSingleResult();
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

}
