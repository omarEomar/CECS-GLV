package com.beshara.csc.inf.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.ISystemSettingsDTO;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.ISystemSettingsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.SystemSettingsEntity;

import com.beshara.csc.inf.business.entity.YearsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;

public class SystemSettingsDAO extends InfBaseDAO {
    public SystemSettingsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new SystemSettingsDAO();
    }

    /**<code>select o from SystemSettingsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<ISystemSettingsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<SystemSettingsEntity> list =
                EM().createNamedQuery("SystemSettingsEntity.findAll").setHint("toplink.refresh",
                                                                              "true").getResultList();
            for (SystemSettingsEntity systemSettings : list) {
                arrayList.add(InfDTOFactory.createSystemSettingsDTO(systemSettings));
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
            Query query = EM().createNamedQuery("SystemSettingsEntity.findNewId");
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
     * Create a New SystemSettingsEntity * @param systemSettingsDTO
     * @return ISystemSettingsDTO
     */

    public ISystemSettingsDTO add(IBasicDTO systemSettingsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            SystemSettingsEntity systemSettingsEntity = new SystemSettingsEntity();
            ISystemSettingsDTO systemSettingsDTO = (ISystemSettingsDTO)systemSettingsDTO1;
            Long maxID = findNewId();
            systemSettingsEntity.setSyssettingCode(maxID);

            systemSettingsEntity.setSyssettingName(systemSettingsDTO.getSyssettingName());
            systemSettingsEntity.setSyssettingValue(systemSettingsDTO.getSyssettingValue());
            systemSettingsEntity.setCreatedBy(systemSettingsDTO.getCreatedBy());
            systemSettingsEntity.setCreatedDate(systemSettingsDTO.getCreatedDate());
            systemSettingsEntity.setAuditStatus(systemSettingsDTO.getAuditStatus());
            systemSettingsEntity.setTabrecSerial(systemSettingsDTO.getTabrecSerial());
            doAdd(systemSettingsEntity);
            systemSettingsDTO.setCode(InfEntityKeyFactory.createSystemSettingsEntityKey(maxID));
            // Set PK after creation
            return systemSettingsDTO;
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
     * Update an Existing SystemSettingsEntity * @param systemSettingsDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO systemSettingsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ISystemSettingsDTO systemSettingsDTO = (ISystemSettingsDTO)systemSettingsDTO1;
            SystemSettingsEntity systemSettingsEntity =
                EM().find(SystemSettingsEntity.class, (ISystemSettingsEntityKey)systemSettingsDTO.getCode());

            systemSettingsEntity.setSyssettingCode(systemSettingsDTO.getSyssettingCode());
            systemSettingsEntity.setSyssettingName(systemSettingsDTO.getSyssettingName());
            systemSettingsEntity.setSyssettingValue(systemSettingsDTO.getSyssettingValue());
            systemSettingsEntity.setCreatedBy(systemSettingsDTO.getCreatedBy());
            systemSettingsEntity.setCreatedDate(systemSettingsDTO.getCreatedDate());
            systemSettingsEntity.setLastUpdatedBy(systemSettingsDTO.getLastUpdatedBy());
            systemSettingsEntity.setLastUpdatedDate(systemSettingsDTO.getLastUpdatedDate());
            systemSettingsEntity.setAuditStatus(systemSettingsDTO.getAuditStatus());
            systemSettingsEntity.setTabrecSerial(systemSettingsDTO.getTabrecSerial());
            doUpdate(systemSettingsEntity);
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
     * Remove an Existing SystemSettingsEntity * @param systemSettingsDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO systemSettingsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ISystemSettingsDTO systemSettingsDTO = (ISystemSettingsDTO)systemSettingsDTO1;
            SystemSettingsEntity systemSettingsEntity =
                EM().find(SystemSettingsEntity.class, (ISystemSettingsEntityKey)systemSettingsDTO.getCode());
            if (systemSettingsEntity == null) {
                throw new NoResultException();
            }
            doRemove(systemSettingsEntity);
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
     * Get SystemSettingsEntity By Primary Key * @param id
     * @return ISystemSettingsDTO
     */
    public ISystemSettingsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            ISystemSettingsEntityKey id = (ISystemSettingsEntityKey)id1;
            SystemSettingsEntity systemSettingsEntity = EM().find(SystemSettingsEntity.class, id);
            if (systemSettingsEntity == null) {
                throw new NoResultException();
            }
            ISystemSettingsDTO systemSettingsDTO = InfDTOFactory.createSystemSettingsDTO(systemSettingsEntity);
            return systemSettingsDTO;
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
     * Search for SystemSettingsEntity * <br> * @return List
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


    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("SystemSettingsEntity.getCodeName").getResultList();
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
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<SystemSettingsEntity> list =
                EM().createNamedQuery("SystemSettingsEntity.searchByCode").setParameter("syssettingCode",
                                                                                        (Long)code).getResultList();
            for (SystemSettingsEntity entity : list) {
                arrayList.add(InfDTOFactory.createSystemSettingsDTO(entity));
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
     * @author Aly Noor @since 06/29/2014 eidted to use generic method InfBaseFacadeBean.searchByName
     */
    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            //        List<SystemSettingsEntity> list =
            //            em.createNamedQuery("SystemSettingsEntity.searchByName").setParameter("syssettingName",
            //                                                                                  name).getResultList();
            List<BasicEntity> list = searchByName("SystemSettingsEntity", "syssettingName", searchName);
            //em.createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list != null) {
                for (BasicEntity obj : list) {
                    SystemSettingsEntity entity = (SystemSettingsEntity)obj;
                    //for (SystemSettingsEntity entity : list) {
                    arrayList.add(InfDTOFactory.createSystemSettingsDTO(entity));
                }
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

    public List<BasicEntity> searchByName(String entityName, String colName,
                                          String searchName) throws DataBaseException, SharedApplicationException {
        try {

            StringBuilder ejbQL = new StringBuilder("select o from ");
            ejbQL.append(entityName);
            ejbQL.append(" o where ( ");
            ejbQL.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o." + colName, searchName));
            ejbQL.append(" ) order by o.");
            ejbQL.append(colName);
            List<BasicEntity> list =
                EM().createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();

            if (list == null || list.size() == 0)
                throw new NoResultException();
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

}
