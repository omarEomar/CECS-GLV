package com.beshara.csc.inf.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.ITrxStatusDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.ITrxStatusEntityKey;
import com.beshara.csc.inf.business.entity.TrxStatusEntity;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;

public class TrxStatusDAO extends InfBaseDAO {
    public TrxStatusDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new TrxStatusDAO();
    }

    /**<code>select o from TrxStatusEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<TrxStatusEntity> list =
                EM().createNamedQuery("TrxStatusEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (TrxStatusEntity trxStatus : list) {
                arrayList.add(InfDTOFactory.createTrxStatusDTO(trxStatus));
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
            Query query = EM().createNamedQuery("TrxStatusEntity.findNewId");
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
     * Create a New TrxStatusEntity * @param trxStatusDTO1
     * @return ITrxStatusDTO
     */
    public ITrxStatusDTO add(IBasicDTO trxStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            TrxStatusEntity trxStatusEntity = new TrxStatusEntity();
            ITrxStatusDTO trxStatusDTO = (ITrxStatusDTO)trxStatusDTO1;
            trxStatusEntity.setTrxstatusCode(String.valueOf(findNewId()));
            trxStatusEntity.setTrxstatusName(trxStatusDTO.getName());
            trxStatusEntity.setCreatedBy(trxStatusDTO.getCreatedBy());
            trxStatusEntity.setCreatedDate(trxStatusDTO.getCreatedDate());
            trxStatusEntity.setAuditStatus(trxStatusDTO.getAuditStatus());
            trxStatusEntity.setTabrecSerial(trxStatusDTO.getTabrecSerial());
            doAdd(trxStatusEntity);
            // Set PK after creation
            return trxStatusDTO;
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
     * Update an Existing TrxStatusEntity * @param trxStatusDTO1
     * @return Boolean
     */
    public Boolean update(IBasicDTO trxStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ITrxStatusDTO trxStatusDTO = (ITrxStatusDTO)trxStatusDTO1;
            TrxStatusEntity trxStatusEntity =
                EM().find(TrxStatusEntity.class, (ITrxStatusEntityKey)trxStatusDTO.getCode());
            trxStatusEntity.setTrxstatusCode(((ITrxStatusEntityKey)trxStatusDTO.getCode()).getTrxstatusCode());
            trxStatusEntity.setTrxstatusName(trxStatusDTO.getName());
            trxStatusEntity.setCreatedBy(trxStatusDTO.getCreatedBy());
            trxStatusEntity.setCreatedDate(trxStatusDTO.getCreatedDate());
            trxStatusEntity.setLastUpdatedBy(trxStatusDTO.getLastUpdatedBy());
            trxStatusEntity.setLastUpdatedDate(trxStatusDTO.getLastUpdatedDate());
            trxStatusEntity.setAuditStatus(trxStatusDTO.getAuditStatus());
            trxStatusEntity.setTabrecSerial(trxStatusDTO.getTabrecSerial());
            doUpdate(trxStatusEntity);
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
     * Remove an Existing TrxStatusEntity * @param trxStatusDTO1
     * @return Boolean
     */
    public Boolean remove(IBasicDTO trxStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ITrxStatusDTO trxStatusDTO = (ITrxStatusDTO)trxStatusDTO1;
            TrxStatusEntity trxStatusEntity =
                EM().find(TrxStatusEntity.class, (ITrxStatusEntityKey)trxStatusDTO.getCode());
            if (trxStatusEntity == null) {
                throw new NoResultException();
            }
            doRemove(trxStatusEntity);
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
     * Search for TrxStatusEntity * <br> * @return List
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
     * Get TrxStatusEntity By Primary Key * @param id1
     * @return ITrxStatusDTO
     */
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            ITrxStatusEntityKey id = (ITrxStatusEntityKey)id1;
            TrxStatusEntity trxStatusEntity = EM().find(TrxStatusEntity.class, id);
            if (trxStatusEntity == null) {
                throw new NoResultException();
            }
            ITrxStatusDTO trxStatusDTO = InfDTOFactory.createTrxStatusDTO(trxStatusEntity);
            return trxStatusDTO;
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
            return EM().createNamedQuery("GenderTypesEntity.getCodeName").getResultList();
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
            List<TrxStatusEntity> list =
                EM().createNamedQuery("TrxStatusEntity.searchByCode").setParameter("trxstatusCode",
                                                                                   String.valueOf(code)).getResultList();
            for (TrxStatusEntity entity : list) {
                arrayList.add(InfDTOFactory.createTrxStatusDTO(entity));
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
            List<BasicEntity> list = searchByName("TrxStatusEntity", "trxstatusName", name);

            if (list != null) {
                for (BasicEntity obj : list) {
                    TrxStatusEntity entity = (TrxStatusEntity)obj;
                    //for (HandicapStatusEntity entity : list) {
                    arrayList.add(InfDTOFactory.createTrxStatusDTO(entity));
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

    /**
     * <b>Description:</b>
     * <br>&nbsp;&nbsp;&nbsp;
     * This Method searchByName is generic method for standard search by name, to search for value of searchName
     * on column whose name colName on the Entity whose name entityName returns result as list of BasicEntity
     * <br><br>
     * @param entityName
     * @param colName
     * @param searchName
     * @return List<BasicEntity>
     * @author   Aly Noor
     * @since    06/25/2014
     */
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
