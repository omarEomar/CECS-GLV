package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IPersonParameterValuesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IPersonParameterValuesEntityKey;
import com.beshara.csc.inf.business.entity.PersonParameterValuesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class PersonParameterValuesDAO extends InfBaseDAO {
    public PersonParameterValuesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new PersonParameterValuesDAO();
    }


    public List<IPersonParameterValuesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<PersonParameterValuesEntity> list =
                EM().createNamedQuery("PersonParameterValuesEntity.findAll").setHint("toplink.refresh",
                                                                                     "true").getResultList();
            for (PersonParameterValuesEntity personParameterValues : list) {
                arrayList.add(InfDTOFactory.createPersonParameterValuesDTO(personParameterValues));
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
     * Create a New PersonParameterValuesEntity * @param personParameterValuesDTO
     * @return IPersonParameterValuesDTO
     */
    public IPersonParameterValuesDTO add(IBasicDTO personParameterValuesDTO1) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            PersonParameterValuesEntity personParameterValuesEntity = new PersonParameterValuesEntity();
            IPersonParameterValuesDTO personParameterValuesDTO = (IPersonParameterValuesDTO)personParameterValuesDTO1;
            personParameterValuesEntity.setCivilId(personParameterValuesDTO.getCivilId());
            personParameterValuesEntity.setParameterCode(personParameterValuesDTO.getParameterCode());
            personParameterValuesEntity.setParamValue(personParameterValuesDTO.getParamValue());
            personParameterValuesEntity.setParamValueDate(personParameterValuesDTO.getParamValueDate());
            personParameterValuesEntity.setValueDesc(personParameterValuesDTO.getValueDesc());
            personParameterValuesEntity.setAuditStatus(personParameterValuesDTO.getAuditStatus());
            personParameterValuesEntity.setTabrecSerial(personParameterValuesDTO.getTabrecSerial());
            doAdd(personParameterValuesEntity);
            return personParameterValuesDTO;
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
     * Update an Existing PersonParameterValuesEntity * @param personParameterValuesDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO personParameterValuesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonParameterValuesDTO personParameterValuesDTO = (IPersonParameterValuesDTO)personParameterValuesDTO1;
            PersonParameterValuesEntity personParameterValuesEntity =
                EM().find(PersonParameterValuesEntity.class, personParameterValuesDTO.getCode());
            personParameterValuesEntity.setCivilId(personParameterValuesDTO.getCivilId());
            personParameterValuesEntity.setParameterCode(personParameterValuesDTO.getParameterCode());
            personParameterValuesEntity.setParamValue(personParameterValuesDTO.getParamValue());
            personParameterValuesEntity.setParamValueDate(personParameterValuesDTO.getParamValueDate());
            personParameterValuesEntity.setValueDesc(personParameterValuesDTO.getValueDesc());
            personParameterValuesEntity.setAuditStatus(personParameterValuesDTO.getAuditStatus());
            personParameterValuesEntity.setTabrecSerial(personParameterValuesDTO.getTabrecSerial());
            doUpdate(personParameterValuesEntity);
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
     * Remove an Existing PersonParameterValuesEntity * @param personParameterValuesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO personParameterValuesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonParameterValuesDTO personParameterValuesDTO = (IPersonParameterValuesDTO)personParameterValuesDTO1;
            PersonParameterValuesEntity personParameterValuesEntity =
                EM().find(PersonParameterValuesEntity.class, personParameterValuesDTO.getCode());
            if (personParameterValuesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(personParameterValuesEntity);
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
     * Get PersonParameterValuesEntity By Primary Key * @param id
     * @return IPersonParameterValuesDTO
     */
    public IPersonParameterValuesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonParameterValuesEntityKey id = (IPersonParameterValuesEntityKey)id1;
            PersonParameterValuesEntity personParameterValuesEntity = EM().find(PersonParameterValuesEntity.class, id);
            if (personParameterValuesEntity == null) {
                throw new ItemNotFoundException();
            }
            IPersonParameterValuesDTO personParameterValuesDTO =
                InfDTOFactory.createPersonParameterValuesDTO(personParameterValuesEntity);
            return personParameterValuesDTO;
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
            Long id = (Long)EM().createNamedQuery("PersonParameterValuesEntity.findNewId").getSingleResult();
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
