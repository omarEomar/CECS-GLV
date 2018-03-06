package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IPersonParameterValuesBkDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IPersonParameterValuesBkEntityKey;
import com.beshara.csc.inf.business.entity.PersonParameterValuesBkEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class PersonParameterValuesBkDAO extends InfBaseDAO {
    public PersonParameterValuesBkDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new PersonParameterValuesBkDAO();
    }

    public List<IPersonParameterValuesBkDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<PersonParameterValuesBkEntity> list =
                EM().createNamedQuery("PersonParameterValuesBkEntity.findAll").setHint("toplink.refresh",
                                                                                       "true").getResultList();
            for (PersonParameterValuesBkEntity personParameterValuesBk : list) {
                arrayList.add(InfDTOFactory.createPersonParameterValuesBkDTO(personParameterValuesBk));
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
     * Create a New PersonParameterValuesBkEntity * @param personParameterValuesBkDTO
     * @return IPersonParameterValuesBkDTO
     */
    public IPersonParameterValuesBkDTO add(IBasicDTO personParameterValuesBkDTO1) throws DataBaseException,
                                                                                         SharedApplicationException {
        try {
            PersonParameterValuesBkEntity personParameterValuesBkEntity = new PersonParameterValuesBkEntity();
            IPersonParameterValuesBkDTO personParameterValuesBkDTO =
                (IPersonParameterValuesBkDTO)personParameterValuesBkDTO1;
            personParameterValuesBkEntity.setCivilId(((IPersonParameterValuesBkEntityKey)personParameterValuesBkDTO.getCode()).getCivilId());
            personParameterValuesBkEntity.setParameterCode(((IPersonParameterValuesBkEntityKey)personParameterValuesBkDTO.getCode()).getParameterCode());
            personParameterValuesBkEntity.setParamValue(personParameterValuesBkDTO.getParamValue());
            personParameterValuesBkEntity.setAuditStatus(personParameterValuesBkDTO.getAuditStatus());
            personParameterValuesBkEntity.setTabrecSerial(personParameterValuesBkDTO.getTabrecSerial());
            doAdd(personParameterValuesBkEntity);
            // Set PK after creation
            return personParameterValuesBkDTO;
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
     * Update an Existing PersonParameterValuesBkEntity * @param personParameterValuesBkDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO personParameterValuesBkDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonParameterValuesBkDTO personParameterValuesBkDTO =
                (IPersonParameterValuesBkDTO)personParameterValuesBkDTO1;
            PersonParameterValuesBkEntity personParameterValuesBkEntity =
                EM().find(PersonParameterValuesBkEntity.class, personParameterValuesBkDTO.getCode());
            personParameterValuesBkEntity.setCivilId(((IPersonParameterValuesBkEntityKey)personParameterValuesBkDTO.getCode()).getCivilId());
            personParameterValuesBkEntity.setParameterCode(((IPersonParameterValuesBkEntityKey)personParameterValuesBkDTO.getCode()).getParameterCode());
            personParameterValuesBkEntity.setParamValue(personParameterValuesBkDTO.getParamValue());
            personParameterValuesBkEntity.setAuditStatus(personParameterValuesBkDTO.getAuditStatus());
            personParameterValuesBkEntity.setTabrecSerial(personParameterValuesBkDTO.getTabrecSerial());
            doUpdate(personParameterValuesBkEntity);
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
     * Remove an Existing PersonParameterValuesBkEntity * @param personParameterValuesBkDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO personParameterValuesBkDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonParameterValuesBkDTO personParameterValuesBkDTO =
                (IPersonParameterValuesBkDTO)personParameterValuesBkDTO1;
            PersonParameterValuesBkEntity personParameterValuesBkEntity =
                EM().find(PersonParameterValuesBkEntity.class, personParameterValuesBkDTO.getCode());
            if (personParameterValuesBkEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(personParameterValuesBkEntity);
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
     * Get PersonParameterValuesBkEntity By Primary Key * @param id
     * @return IPersonParameterValuesBkDTO
     */
    public IPersonParameterValuesBkDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonParameterValuesBkEntityKey id = (IPersonParameterValuesBkEntityKey)id1;
            PersonParameterValuesBkEntity personParameterValuesBkEntity =
                EM().find(PersonParameterValuesBkEntity.class, id);
            if (personParameterValuesBkEntity == null) {
                throw new ItemNotFoundException();
            }
            IPersonParameterValuesBkDTO personParameterValuesBkDTO =
                InfDTOFactory.createPersonParameterValuesBkDTO(personParameterValuesBkEntity);
            return personParameterValuesBkDTO;
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
            Long id = (Long)EM().createNamedQuery("PersonParameterValuesBkEntity.findNewId").getSingleResult();
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
