package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IMilitaryStatusDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IMilitaryStatusEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.MilitaryStatusEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class MilitaryStatusDAO extends InfBaseDAO {
    public MilitaryStatusDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new MilitaryStatusDAO();
    }

    public List<IMilitaryStatusDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<MilitaryStatusEntity> list =
                EM().createNamedQuery("MilitaryStatusEntity.findAll").setHint("toplink.refresh",
                                                                              "true").getResultList();
            for (MilitaryStatusEntity militaryStatus : list) {
                arrayList.add(InfDTOFactory.createMilitaryStatusDTO(militaryStatus));
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
     * Create a New MilitaryStatusEntity * @param militaryStatusDTO
     * @return IMilitaryStatusDTO
     */
    public IMilitaryStatusDTO add(IBasicDTO militaryStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            MilitaryStatusEntity militaryStatusEntity = new MilitaryStatusEntity();
            IMilitaryStatusDTO militaryStatusDTO = (IMilitaryStatusDTO)militaryStatusDTO1;
            militaryStatusDTO.setCode(InfEntityKeyFactory.createMilitaryStatusEntityKey(findNewId())); 
            militaryStatusEntity.setMltstatusCode(((IMilitaryStatusEntityKey)militaryStatusDTO.getCode()).getMltstatusCode());
            militaryStatusEntity.setMltstatusName(militaryStatusDTO.getName());
            militaryStatusEntity.setCreatedBy(militaryStatusDTO.getCreatedBy());
            militaryStatusEntity.setCreatedDate(militaryStatusDTO.getCreatedDate());
            militaryStatusEntity.setAuditStatus(militaryStatusDTO.getAuditStatus());
            militaryStatusEntity.setTabrecSerial(militaryStatusDTO.getTabrecSerial());
            doAdd(militaryStatusEntity);
            return militaryStatusDTO;
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
     * Update an Existing MilitaryStatusEntity * @param militaryStatusDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO militaryStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IMilitaryStatusDTO militaryStatusDTO = (IMilitaryStatusDTO)militaryStatusDTO1;
            MilitaryStatusEntity militaryStatusEntity =
                EM().find(MilitaryStatusEntity.class, militaryStatusDTO.getCode());
            militaryStatusEntity.setMltstatusCode(((IMilitaryStatusEntityKey)militaryStatusDTO.getCode()).getMltstatusCode());
            militaryStatusEntity.setMltstatusName(militaryStatusDTO.getName());
            militaryStatusEntity.setCreatedBy(militaryStatusDTO.getCreatedBy());
            militaryStatusEntity.setCreatedDate(militaryStatusDTO.getCreatedDate());
            militaryStatusEntity.setLastUpdatedBy(militaryStatusDTO.getLastUpdatedBy());
            militaryStatusEntity.setLastUpdatedDate(militaryStatusDTO.getLastUpdatedDate());
            militaryStatusEntity.setAuditStatus(militaryStatusDTO.getAuditStatus());
            militaryStatusEntity.setTabrecSerial(militaryStatusDTO.getTabrecSerial());
            doUpdate(militaryStatusEntity);
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
     * Remove an Existing MilitaryStatusEntity * @param militaryStatusDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO militaryStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IMilitaryStatusDTO militaryStatusDTO = (IMilitaryStatusDTO)militaryStatusDTO1;
            MilitaryStatusEntity militaryStatusEntity =
                EM().find(MilitaryStatusEntity.class, militaryStatusDTO.getCode());
            if (militaryStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(militaryStatusEntity);
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
     * Get MilitaryStatusEntity By Primary Key * @param id
     * @return IMilitaryStatusDTO
     */
    public IMilitaryStatusDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IMilitaryStatusEntityKey id = (IMilitaryStatusEntityKey)id1;
            MilitaryStatusEntity militaryStatusEntity = EM().find(MilitaryStatusEntity.class, id);
            if (militaryStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            IMilitaryStatusDTO militaryStatusDTO = InfDTOFactory.createMilitaryStatusDTO(militaryStatusEntity);
            return militaryStatusDTO;
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
            Long id = (Long)EM().createNamedQuery("MilitaryStatusEntity.findNewId").getSingleResult();
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
            return EM().createNamedQuery("MilitaryStatusEntity.getCodeName").getResultList();
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
            List<MilitaryStatusEntity> list =
                EM().createNamedQuery("MilitaryStatusEntity.searchByCode").setParameter("mltstatusCode",
                                                                                        (Long)code).getResultList();
            for (MilitaryStatusEntity entity : list) {
                arrayList.add(InfDTOFactory.createMilitaryStatusDTO(entity));
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

    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            StringBuilder ejbQL =
                new StringBuilder("select o from MilitaryStatusEntity   o where o.mltstatusName like '%");
            ejbQL.append(searchName);
            ejbQL.append("%' order by o.mltstatusName");
            List<MilitaryStatusEntity> list =
                EM().createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (MilitaryStatusEntity entity : list) {
                arrayList.add(InfDTOFactory.createMilitaryStatusDTO(entity));
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
    public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
        
        try {
            List list =
                EM().createNamedQuery("MilitaryStatusEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createMilitaryStatusDTO((MilitaryStatusEntity)list.get(0)));
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
