package com.beshara.csc.gn.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.inf.business.dto.IInfOldDataAuditDTO;
import com.beshara.csc.gn.inf.business.entity.IInfOldDataAuditEntityKey;
import com.beshara.csc.gn.inf.business.entity.InfOldDataAuditEntity;
import com.beshara.csc.inf.business.dao.InfBaseDAO;
import com.beshara.csc.inf.business.dao.InfEntityConverter;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
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

public class InfOldDataAuditDAO extends InfBaseDAO {

    /**
     * InfOldDataAuditDAO Default Constructor
     */
    public InfOldDataAuditDAO() {
        super();
    }


    @Override
    protected BaseDAO newInstance() {
        return new InfOldDataAuditDAO();
    }


    /**<code>select o from InfOldDataAuditEntity o</code>.
     * @return List
     */
    public List<IInfOldDataAuditDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfOldDataAuditEntity> list = EM().createNamedQuery("InfOldDataAuditEntity.findAll").getResultList();
            for (InfOldDataAuditEntity infOldDataAudit : list) {
                arrayList.add(InfEntityConverter.getInfOldDataAuditDTO(infOldDataAudit));

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
     * Create a New InfOldDataAuditEntity
     * @param infOldDataAuditDTO
     * @return IBasicDTO
     */
    public IBasicDTO add(IBasicDTO infOldDataAuditDTO1) throws DataBaseException, SharedApplicationException {
        try {
            InfOldDataAuditEntity infOldDataAuditEntity = new InfOldDataAuditEntity();

            IInfOldDataAuditDTO infOldDataAuditDTO = (IInfOldDataAuditDTO)infOldDataAuditDTO1;

            Long serialCode = findNewId();
            infOldDataAuditEntity.setRecSerial(infOldDataAuditDTO.getRecSerial());
            infOldDataAuditEntity.setSerial(serialCode);
            infOldDataAuditEntity.setProcessDate(infOldDataAuditDTO.getProcessDate());
            infOldDataAuditEntity.setProcessUser(infOldDataAuditDTO.getProcessUser());
            infOldDataAuditEntity.setExceptionType(infOldDataAuditDTO.getExceptionType());
            infOldDataAuditEntity.setExcpCode(infOldDataAuditDTO.getExcpCode());
            infOldDataAuditEntity.setName(infOldDataAuditDTO.getName());
            infOldDataAuditEntity.setScreenTitle(infOldDataAuditDTO.getScreenTitle());
            infOldDataAuditEntity.setCivilId(infOldDataAuditDTO.getCivilId());
            infOldDataAuditEntity.setOldOrgCatCode(infOldDataAuditDTO.getOldOrgCatCode());
            infOldDataAuditEntity.setOldMinistryCode(infOldDataAuditDTO.getOldMinistryCode());
            infOldDataAuditEntity.setOldDeptCode(infOldDataAuditDTO.getOldDeptCode());
            infOldDataAuditEntity.setOldJobCode(infOldDataAuditDTO.getOldJobCode());
            infOldDataAuditEntity.setOldFromDate(infOldDataAuditDTO.getOldFromDate());
            infOldDataAuditEntity.setOldToDate(infOldDataAuditDTO.getOldToDate());
            infOldDataAuditEntity.setOldAudited(infOldDataAuditDTO.getOldAudited());
            infOldDataAuditEntity.setOldPerFlag(infOldDataAuditDTO.getOldPerFlag());
            infOldDataAuditEntity.setNewOrgCatCode(infOldDataAuditDTO.getNewOrgCatCode());
            infOldDataAuditEntity.setNewMinistryCode(infOldDataAuditDTO.getNewMinistryCode());
            infOldDataAuditEntity.setNewDeptCode(infOldDataAuditDTO.getNewDeptCode());
            infOldDataAuditEntity.setNewJobCode(infOldDataAuditDTO.getNewJobCode());
            infOldDataAuditEntity.setNewFromDate(infOldDataAuditDTO.getNewFromDate());
            infOldDataAuditEntity.setNewToDate(infOldDataAuditDTO.getNewToDate());
            infOldDataAuditEntity.setNewAudited(infOldDataAuditDTO.getNewAudited());
            infOldDataAuditEntity.setNewPerFlag(infOldDataAuditDTO.getNewPerFlag());
            infOldDataAuditEntity.setModifyExceptionType(infOldDataAuditDTO.getModifyExceptionType());
            infOldDataAuditEntity.setModifyExcpCode(infOldDataAuditDTO.getModifyExcpCode());


            doAdd(infOldDataAuditEntity);
            IInfOldDataAuditEntityKey ek =
                InfEntityKeyFactory.createInfOldDataAuditEntityKey(infOldDataAuditEntity.getSerial());
            infOldDataAuditDTO.setCode(ek);

            // Set PK after creation
            return infOldDataAuditDTO;
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
     * Update an Existing InfOldDataAuditEntity
     * @param infOldDataAuditDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO infOldDataAuditDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfOldDataAuditDTO infOldDataAuditDTO = (IInfOldDataAuditDTO)infOldDataAuditDTO1;

            InfOldDataAuditEntity infOldDataAuditEntity =
                EM().find(InfOldDataAuditEntity.class, (IInfOldDataAuditEntityKey)infOldDataAuditDTO.getCode());

            infOldDataAuditEntity.setRecSerial(infOldDataAuditDTO.getRecSerial());
            infOldDataAuditEntity.setProcessDate(infOldDataAuditDTO.getProcessDate());
            infOldDataAuditEntity.setProcessUser(infOldDataAuditDTO.getProcessUser());
            infOldDataAuditEntity.setExceptionType(infOldDataAuditDTO.getExceptionType());
            infOldDataAuditEntity.setExcpCode(infOldDataAuditDTO.getExcpCode());
            infOldDataAuditEntity.setName(infOldDataAuditDTO.getName());
            infOldDataAuditEntity.setScreenTitle(infOldDataAuditDTO.getScreenTitle());
            infOldDataAuditEntity.setCivilId(infOldDataAuditDTO.getCivilId());
            infOldDataAuditEntity.setOldOrgCatCode(infOldDataAuditDTO.getOldOrgCatCode());
            infOldDataAuditEntity.setOldMinistryCode(infOldDataAuditDTO.getOldMinistryCode());
            infOldDataAuditEntity.setOldDeptCode(infOldDataAuditDTO.getOldDeptCode());
            infOldDataAuditEntity.setOldJobCode(infOldDataAuditDTO.getOldJobCode());
            infOldDataAuditEntity.setOldFromDate(infOldDataAuditDTO.getOldFromDate());
            infOldDataAuditEntity.setOldToDate(infOldDataAuditDTO.getOldToDate());
            infOldDataAuditEntity.setOldAudited(infOldDataAuditDTO.getOldAudited());
            infOldDataAuditEntity.setOldPerFlag(infOldDataAuditDTO.getOldPerFlag());
            infOldDataAuditEntity.setNewOrgCatCode(infOldDataAuditDTO.getNewOrgCatCode());
            infOldDataAuditEntity.setNewMinistryCode(infOldDataAuditDTO.getNewMinistryCode());
            infOldDataAuditEntity.setNewDeptCode(infOldDataAuditDTO.getNewDeptCode());
            infOldDataAuditEntity.setNewJobCode(infOldDataAuditDTO.getNewJobCode());
            infOldDataAuditEntity.setNewFromDate(infOldDataAuditDTO.getNewFromDate());
            infOldDataAuditEntity.setNewToDate(infOldDataAuditDTO.getNewToDate());
            infOldDataAuditEntity.setNewAudited(infOldDataAuditDTO.getNewAudited());
            infOldDataAuditEntity.setNewPerFlag(infOldDataAuditDTO.getNewPerFlag());
            infOldDataAuditEntity.setModifyExceptionType(infOldDataAuditDTO.getModifyExceptionType());
            infOldDataAuditEntity.setModifyExcpCode(infOldDataAuditDTO.getModifyExcpCode());

            doUpdate(infOldDataAuditEntity);
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
     * Remove an Existing InfOldDataAuditEntity
     * @param infOldDataAuditDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO infOldDataAuditDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfOldDataAuditDTO infOldDataAuditDTO = (IInfOldDataAuditDTO)infOldDataAuditDTO1;

            InfOldDataAuditEntity infOldDataAuditEntity =
                EM().find(InfOldDataAuditEntity.class, (IInfOldDataAuditEntityKey)infOldDataAuditDTO.getCode());

            if (infOldDataAuditEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(infOldDataAuditEntity);
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
     * Get InfOldDataAuditEntity By Primary Key
     * @param id
     * @return InfOldDataAuditDTO
     */
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfOldDataAuditEntityKey id = (IInfOldDataAuditEntityKey)id1;

            InfOldDataAuditEntity infOldDataAuditEntity = EM().find(InfOldDataAuditEntity.class, id);
            if (infOldDataAuditEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfOldDataAuditDTO infOldDataAuditDTO = InfEntityConverter.getInfOldDataAuditDTO(infOldDataAuditEntity);
            return infOldDataAuditDTO;
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
     * Get the MaxId of InfOldDataAuditEntity
     * <br>
     * @return Object
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("InfOldDataAuditEntity.findNewId").getSingleResult();
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
            List<InfOldDataAuditEntity> list =
                EM().createNamedQuery("InfOldDataAuditEntity.searchByCode").setParameter("code", code).getResultList();
            for (InfOldDataAuditEntity entity : list) {
                arrayList.add(InfEntityConverter.getInfOldDataAuditDTO(entity));
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
            List<InfOldDataAuditEntity> list =
                EM().createNamedQuery("InfOldDataAuditEntity.searchByName").setParameter("name",
                                                                                         "%" + searchName + "%").getResultList();
            for (InfOldDataAuditEntity entity : list) {
                arrayList.add(InfEntityConverter.getInfOldDataAuditDTO(entity));
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
