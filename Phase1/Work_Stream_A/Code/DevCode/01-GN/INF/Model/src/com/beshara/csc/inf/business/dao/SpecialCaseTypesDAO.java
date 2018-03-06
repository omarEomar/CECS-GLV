package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.ISpecialCaseTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.GenderTypesEntity;
import com.beshara.csc.inf.business.entity.ISpecialCaseTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.SpecialCaseTypesEntity;
import com.beshara.csc.inf.business.entity.SpecialCaseTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class SpecialCaseTypesDAO extends InfBaseDAO {
    public SpecialCaseTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new SpecialCaseTypesDAO();
    }

    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<SpecialCaseTypesEntity> list =
                EM().createNamedQuery("SpecialCaseTypesEntity.findAll").setHint("toplink.refresh",
                                                                                "true").getResultList();
            for (SpecialCaseTypesEntity specialCaseTypes : list) {
                arrayList.add(InfDTOFactory.createSpecialCaseTypesDTO(specialCaseTypes));
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
             Query query = EM().createNamedQuery("SpecialCaseTypesEntity.findNewId");
             Long id = (Long)query.getSingleResult();
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
     * Create a New SpecialCaseTypesEntity * @param specialCaseTypesDTO1
     * @return ISpecialCaseTypesDTO
     */
    public IBasicDTO add(IBasicDTO specialCaseTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            SpecialCaseTypesEntity specialCaseTypesEntity = new SpecialCaseTypesEntity();
            ISpecialCaseTypesDTO specialCaseTypesDTO = (ISpecialCaseTypesDTO)specialCaseTypesDTO1;
            ISpecialCaseTypesEntityKey ent=new SpecialCaseTypesEntityKey(findNewId());
            specialCaseTypesEntity.setSpccsetypeCode(ent.getSpccsetypeCode());
            specialCaseTypesEntity.setSpccsetypeName(specialCaseTypesDTO.getName());
            specialCaseTypesEntity.setCreatedBy(specialCaseTypesDTO.getCreatedBy());
            specialCaseTypesEntity.setCreatedDate(specialCaseTypesDTO.getCreatedDate());
            specialCaseTypesEntity.setAuditStatus(specialCaseTypesDTO.getAuditStatus());
            specialCaseTypesEntity.setTabrecSerial(specialCaseTypesDTO.getTabrecSerial());
            doAdd(specialCaseTypesEntity);
            // Set PK after creation
            specialCaseTypesDTO.setCode(ent);
            return specialCaseTypesDTO;
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
     * Update an Existing SpecialCaseTypesEntity * @param specialCaseTypesDTO1
     * @return Boolean
     */
    public Boolean update(IBasicDTO specialCaseTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ISpecialCaseTypesDTO specialCaseTypesDTO = (ISpecialCaseTypesDTO)specialCaseTypesDTO1;
            SpecialCaseTypesEntity specialCaseTypesEntity =
                EM().find(SpecialCaseTypesEntity.class, (ISpecialCaseTypesEntityKey)specialCaseTypesDTO.getCode());

            specialCaseTypesEntity.setSpccsetypeCode(((ISpecialCaseTypesEntityKey)specialCaseTypesDTO.getCode()).getSpccsetypeCode());
            specialCaseTypesEntity.setSpccsetypeName(specialCaseTypesDTO.getName());
            specialCaseTypesEntity.setCreatedBy(specialCaseTypesDTO.getCreatedBy());
            specialCaseTypesEntity.setCreatedDate(specialCaseTypesDTO.getCreatedDate());
            specialCaseTypesEntity.setLastUpdatedBy(specialCaseTypesDTO.getLastUpdatedBy());
            specialCaseTypesEntity.setLastUpdatedDate(specialCaseTypesDTO.getLastUpdatedDate());
            specialCaseTypesEntity.setAuditStatus(specialCaseTypesDTO.getAuditStatus());
            specialCaseTypesEntity.setTabrecSerial(specialCaseTypesDTO.getTabrecSerial());
            doUpdate(specialCaseTypesEntity);
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
     * Remove an Existing SpecialCaseTypesEntity * @param specialCaseTypesDTO1
     * @return Boolean
     */
    public Boolean remove(IBasicDTO specialCaseTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ISpecialCaseTypesDTO specialCaseTypesDTO = (ISpecialCaseTypesDTO)specialCaseTypesDTO1;
            SpecialCaseTypesEntity specialCaseTypesEntity =
                EM().find(SpecialCaseTypesEntity.class, (ISpecialCaseTypesEntityKey)specialCaseTypesDTO.getCode());
            if (specialCaseTypesEntity == null) {
                throw new FinderException();
            }
            doRemove(specialCaseTypesEntity);
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
     * Get SpecialCaseTypesEntity By Primary Key * @param id1
     * @return ISpecialCaseTypesDTO
     */
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            ISpecialCaseTypesEntityKey id = (ISpecialCaseTypesEntityKey)id1;
            SpecialCaseTypesEntity specialCaseTypesEntity = EM().find(SpecialCaseTypesEntity.class, id);
            if (specialCaseTypesEntity == null) {
                throw new NoResultException();
            }
            ISpecialCaseTypesDTO specialCaseTypesDTO = InfDTOFactory.createSpecialCaseTypesDTO(specialCaseTypesEntity);
            return specialCaseTypesDTO;
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
     * Search for SpecialCaseTypesEntity * <br> * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            return super.search(basicDTO);
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
     * search Period Typesby name * @param name
     * @return List of IBasicDTO
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            StringBuilder query = new StringBuilder("select o from SpecialCaseTypesEntity o where  ");

            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.spccsetypeName", name));
            List<SpecialCaseTypesEntity> list = EM().createQuery(query.toString()).getResultList();

            //        List<SpecialCaseTypesEntity> list =
            //            em.createNamedQuery("SpecialCaseTypesEntity.searchByName").setParameter("name",
            //                                                                                    name).getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            for (SpecialCaseTypesEntity specialCaseType : list) {
                arrayList.add(InfDTOFactory.createSpecialCaseTypesDTO(specialCaseType));
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
     * search Period types by specific code * @param code
     * @return List of IBasicDTO
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            list.add(getById(InfEntityKeyFactory.createSpecialCaseTypesEntityKey((Long)code)));
            return list;
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
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try{
        return EM().createNamedQuery("SpecialCaseTypesEntity.getCodeName").getResultList();
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
    public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
        
        try {
            List list =
                EM().createNamedQuery("SpecialCaseTypesEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createSpecialCaseTypesDTO((SpecialCaseTypesEntity)list.get(0)));
            }
            return null;
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
