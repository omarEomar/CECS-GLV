package com.beshara.csc.inf.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IReligionsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IReligionsEntityKey;
import com.beshara.csc.inf.business.entity.ReligionsEntity;

import com.beshara.csc.inf.business.entity.ReligionsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;

public class ReligionsDAO extends InfBaseDAO {
    public ReligionsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new ReligionsDAO();
    }

    /**<code>select o from ReligionsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<IReligionsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<ReligionsEntity> list =
                EM().createNamedQuery("ReligionsEntity.findAll").setHint("toplink.refresh", true).getResultList();
            for (ReligionsEntity religions : list) {
                arrayList.add(InfDTOFactory.createReligionsDTO(religions));
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
            Query query = EM().createNamedQuery("ReligionsEntity.findNewId");
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
     * Create a New ReligionsEntity * @param religionsDTO
     * @return IReligionsDTO
     */

    public IReligionsDTO add(IBasicDTO religionsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            long maxId = findNewId();
            ReligionsEntity religionsEntity = new ReligionsEntity();
            IReligionsDTO religionsDTO = (IReligionsDTO)religionsDTO1;
            religionsEntity.setReligionCode(maxId);
            religionsEntity.setReligionName(religionsDTO.getName());
            religionsEntity.setCreatedBy(religionsDTO.getCreatedBy());
            religionsEntity.setCreatedDate(religionsDTO.getCreatedDate());
            religionsEntity.setAuditStatus(religionsDTO.getAuditStatus());
            religionsEntity.setTabrecSerial(religionsDTO.getTabrecSerial());
            doAdd(religionsEntity);
            religionsDTO.setCode(new ReligionsEntityKey(maxId));
            // Set PK after creation
            return religionsDTO;
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
     * Update an Existing ReligionsEntity * @param religionsDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO religionsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IReligionsDTO religionsDTO = (IReligionsDTO)religionsDTO1;
            ReligionsEntity religionsEntity =
                EM().find(ReligionsEntity.class, (IReligionsEntityKey)religionsDTO.getCode());
            religionsEntity.setReligionCode(((IReligionsEntityKey)religionsDTO.getCode()).getReligionCode());
            religionsEntity.setReligionName(religionsDTO.getName());
            religionsEntity.setCreatedBy(religionsDTO.getCreatedBy());
            religionsEntity.setCreatedDate(religionsDTO.getCreatedDate());
            religionsEntity.setLastUpdatedBy(religionsDTO.getLastUpdatedBy());
            religionsEntity.setLastUpdatedDate(religionsDTO.getLastUpdatedDate());
            religionsEntity.setAuditStatus(religionsDTO.getAuditStatus());
            religionsEntity.setTabrecSerial(religionsDTO.getTabrecSerial());
            doUpdate(religionsEntity);
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
     * Remove an Existing ReligionsEntity * @param religionsDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO religionsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IReligionsDTO religionsDTO = (IReligionsDTO)religionsDTO1;
            ReligionsEntity religionsEntity =
                EM().find(ReligionsEntity.class, (IReligionsEntityKey)religionsDTO.getCode());
            if (religionsEntity == null) {
                throw new NoResultException();
            }
            doRemove(religionsEntity);
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
     * Get ReligionsEntity By Primary Key * @param id1
     * @return IReligionsDTO
     */
    public IReligionsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IReligionsEntityKey id = (IReligionsEntityKey)id1;
            ReligionsEntity religionsEntity = EM().find(ReligionsEntity.class, id);
            if (religionsEntity == null) {
                throw new FinderException();
            }
            IReligionsDTO religionsDTO = InfDTOFactory.createReligionsDTO(religionsEntity);
            return religionsDTO;
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
        try {
            return EM().createNamedQuery("ReligionsEntity.getCodeName").getResultList();
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
     * Search for ReligionsEntity * <br> * @return List
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
     * Get all by code equal code * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<ReligionsEntity> list =
                EM().createNamedQuery("ReligionsEntity.searchByCode").setParameter("religionCode",
                                                                                   (Long)code).getResultList();
            for (ReligionsEntity entity : list) {
                arrayList.add(InfDTOFactory.createReligionsDTO(entity));
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
     */
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<BasicEntity> list = searchByName("ReligionsEntity", "religionName", name);

            if (list != null) {
                for (BasicEntity obj : list) {
                    ReligionsEntity entity = (ReligionsEntity)obj;
                    //for (HandicapStatusEntity entity : list) {
                    arrayList.add(InfDTOFactory.createReligionsDTO(entity));
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

    public List<IBasicDTO> checkDublicateName(String name) throws DataBaseException, SharedApplicationException {
        try {
            List<ReligionsEntity> list =
                EM().createNamedQuery("ReligionsEntity.checkDublicateName").setParameter("name", name).getResultList();
            ArrayList arrayList = new ArrayList();
            for (ReligionsEntity religionsEntity : list) {
                arrayList.add(InfDTOFactory.createReligionsDTO(religionsEntity));
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
}
