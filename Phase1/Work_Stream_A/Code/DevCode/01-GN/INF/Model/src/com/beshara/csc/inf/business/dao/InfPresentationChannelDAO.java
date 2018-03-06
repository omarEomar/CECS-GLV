package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfPresentationChannelDTO;
import com.beshara.csc.inf.business.entity.IInfPresentationChannelEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfPresentationChannelEntity;
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

public class InfPresentationChannelDAO extends InfBaseDAO {

    /**
     * InfPresentationChannelDAO Default Constructor
     */
    public InfPresentationChannelDAO() {
        super();
    }


    @Override
    protected BaseDAO newInstance() {
        return new InfPresentationChannelDAO();
    }


    /**<code>select o from InfPresentationChannelEntity o</code>.
     * @return List
     */
    public List<IInfPresentationChannelDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfPresentationChannelEntity> list =
                EM().createNamedQuery("InfPresentationChannelEntity.findAll").getResultList();
            for (InfPresentationChannelEntity infPresentationChannel : list) {
                arrayList.add(InfEntityConverter.getInfPresentationChannelDTO(infPresentationChannel));

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
     * Create a New InfPresentationChannelEntity
     * @param infPresentationChannelDTO
     * @return IBasicDTO
     */
    public IBasicDTO add(IBasicDTO infPresentationChannelDTO1) throws DataBaseException, SharedApplicationException {
        try {
            InfPresentationChannelEntity infPresentationChannelEntity = new InfPresentationChannelEntity();

            IInfPresentationChannelDTO infPresentationChannelDTO =
                (IInfPresentationChannelDTO)infPresentationChannelDTO1;

            Long serialCode = findNewId();
            infPresentationChannelEntity.setChannelId(serialCode);
            infPresentationChannelEntity.setName(infPresentationChannelDTO.getName());
            infPresentationChannelEntity.setChannelNameEn(infPresentationChannelDTO.getChannelNameEn());


            doAdd(infPresentationChannelEntity);
            IInfPresentationChannelEntityKey ek =
                InfEntityKeyFactory.createInfPresentationChannelEntityKey(infPresentationChannelEntity.getChannelId());
            infPresentationChannelDTO.setCode(ek);

            // Set PK after creation
            return infPresentationChannelDTO;
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
     * Update an Existing InfPresentationChannelEntity
     * @param infPresentationChannelDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO infPresentationChannelDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfPresentationChannelDTO infPresentationChannelDTO =
                (IInfPresentationChannelDTO)infPresentationChannelDTO1;

            InfPresentationChannelEntity infPresentationChannelEntity =
                EM().find(InfPresentationChannelEntity.class, (IInfPresentationChannelEntityKey)infPresentationChannelDTO.getCode());

            infPresentationChannelEntity.setName(infPresentationChannelDTO.getName());
            infPresentationChannelEntity.setChannelNameEn(infPresentationChannelDTO.getChannelNameEn());

            doUpdate(infPresentationChannelEntity);
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
     * Remove an Existing InfPresentationChannelEntity
     * @param infPresentationChannelDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO infPresentationChannelDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfPresentationChannelDTO infPresentationChannelDTO =
                (IInfPresentationChannelDTO)infPresentationChannelDTO1;

            InfPresentationChannelEntity infPresentationChannelEntity =
                EM().find(InfPresentationChannelEntity.class, (IInfPresentationChannelEntityKey)infPresentationChannelDTO.getCode());

            if (infPresentationChannelEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(infPresentationChannelEntity);
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
     * Get InfPresentationChannelEntity By Primary Key
     * @param id
     * @return InfPresentationChannelDTO
     */
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfPresentationChannelEntityKey id = (IInfPresentationChannelEntityKey)id1;

            InfPresentationChannelEntity infPresentationChannelEntity =
                EM().find(InfPresentationChannelEntity.class, id);
            if (infPresentationChannelEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfPresentationChannelDTO infPresentationChannelDTO =
                InfEntityConverter.getInfPresentationChannelDTO(infPresentationChannelEntity);
            return infPresentationChannelDTO;
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
     * Get the MaxId of InfPresentationChannelEntity
     * <br>
     * @return Object
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("InfPresentationChannelEntity.findNewId").getSingleResult();
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
            List<InfPresentationChannelEntity> list =
                EM().createNamedQuery("InfPresentationChannelEntity.searchByCode").setParameter("code",
                                                                                                code).getResultList();
            for (InfPresentationChannelEntity entity : list) {
                arrayList.add(InfEntityConverter.getInfPresentationChannelDTO(entity));
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
            List<InfPresentationChannelEntity> list =
                EM().createNamedQuery("InfPresentationChannelEntity.searchByName").setParameter("name",
                                                                                                "%" + searchName +
                                                                                                "%").getResultList();
            for (InfPresentationChannelEntity entity : list) {
                arrayList.add(InfEntityConverter.getInfPresentationChannelDTO(entity));
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
