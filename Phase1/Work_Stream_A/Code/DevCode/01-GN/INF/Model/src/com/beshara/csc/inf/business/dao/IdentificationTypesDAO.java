package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IIdentificationTypesDTO;
import com.beshara.csc.inf.business.entity.IIdentificationTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.IdentificationTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class IdentificationTypesDAO extends InfBaseDAO {

    /**
     * IdentificationTypesDAO Default Constructor
     */
    public IdentificationTypesDAO() {
        super();
    }


    @Override
    protected BaseDAO newInstance() {
        return new IdentificationTypesDAO();
    }


    /**<code>select o from IdentificationTypesEntity o</code>.
     * @return List
     */
    public List<IIdentificationTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<IdentificationTypesEntity> list = EM().createNamedQuery("IdentificationTypesEntity.findAll").getResultList();
            for (IdentificationTypesEntity infTypes : list) {
                arrayList.add(InfEntityConverter.getIdentificationTypesDTO(infTypes));

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
     * Create a New IdentificationTypesEntity
     * @param infTypesDTO
     * @return IBasicDTO
     */
    public IBasicDTO add(IBasicDTO infTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IdentificationTypesEntity infTypesEntity = new IdentificationTypesEntity();

            IIdentificationTypesDTO infTypesDTO = (IIdentificationTypesDTO)infTypesDTO1;

            Long serialCode = findNewId();
            infTypesEntity.setIdtypeCode(serialCode);
            infTypesEntity.setName(infTypesDTO.getName());


            doAdd(infTypesEntity);
            IIdentificationTypesEntityKey ek = InfEntityKeyFactory.createIdentificationTypesEntityKey(infTypesEntity.getIdtypeCode());
            infTypesDTO.setCode(ek);

            // Set PK after creation
            return infTypesDTO;
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
     * Update an Existing IdentificationTypesEntity
     * @param infTypesDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO infTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IIdentificationTypesDTO infTypesDTO = (IIdentificationTypesDTO)infTypesDTO1;

            IdentificationTypesEntity infTypesEntity = EM().find(IdentificationTypesEntity.class, (IIdentificationTypesEntityKey)infTypesDTO.getCode());

            infTypesEntity.setName(infTypesDTO.getName());

            doUpdate(infTypesEntity);
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
     * Remove an Existing IdentificationTypesEntity
     * @param infTypesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO infTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IIdentificationTypesDTO infTypesDTO = (IIdentificationTypesDTO)infTypesDTO1;

            IdentificationTypesEntity infTypesEntity = EM().find(IdentificationTypesEntity.class, (IIdentificationTypesEntityKey)infTypesDTO.getCode());

            if (infTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(infTypesEntity);
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
     * Get IdentificationTypesEntity By Primary Key
     * @param id
     * @return InfTypesDTO
     */
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IIdentificationTypesEntityKey id = (IIdentificationTypesEntityKey)id1;

            IdentificationTypesEntity infTypesEntity = EM().find(IdentificationTypesEntity.class, id);
            if (infTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IIdentificationTypesDTO infTypesDTO = InfEntityConverter.getIdentificationTypesDTO(infTypesEntity);
            return infTypesDTO;
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
     * Get the MaxId of IdentificationTypesEntity
     * <br>
     * @return Object
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("IdentificationTypesEntity.findNewId").getSingleResult();
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


    /**
     * Get all by code equal code * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<IdentificationTypesEntity> list =
                EM().createNamedQuery("IdentificationTypesEntity.searchByCode").setParameter("code", code).getResultList();
            for (IdentificationTypesEntity entity : list) {
                arrayList.add(InfEntityConverter.getIdentificationTypesDTO(entity));
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
     * Get all by Name equal code * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<IdentificationTypesEntity> list =
                EM().createNamedQuery("IdentificationTypesEntity.searchByName").setParameter("name", "%" + searchName +
                                                                                  "%").getResultList();
            for (IdentificationTypesEntity entity : list) {
                arrayList.add(InfEntityConverter.getIdentificationTypesDTO(entity));
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
