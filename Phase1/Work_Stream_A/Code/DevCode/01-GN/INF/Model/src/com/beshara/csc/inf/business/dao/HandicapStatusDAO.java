package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IHandicapStatusDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.HandicapStatusEntity;
import com.beshara.csc.inf.business.entity.HandicapStatusEntityKey;
import com.beshara.csc.inf.business.entity.IHandicapStatusEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class HandicapStatusDAO extends InfBaseDAO {
    public HandicapStatusDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new HandicapStatusDAO();
    }

    public IHandicapStatusDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IHandicapStatusEntityKey id = (IHandicapStatusEntityKey)id1;
            HandicapStatusEntity handicapStatusEntity = EM().find(HandicapStatusEntity.class, id);
            if (handicapStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            IHandicapStatusDTO handicapStatusDTO = InfDTOFactory.createHandicapStatusDTO(handicapStatusEntity);
            return handicapStatusDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<HandicapStatusEntity> list =
                EM().createNamedQuery("HandicapStatusEntity.findAll").setHint("toplink.refresh",
                                                                              "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (HandicapStatusEntity handicapStatus : list) {
                arrayList.add(InfDTOFactory.createHandicapStatusDTO(handicapStatus));
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
            Query query = EM().createNamedQuery("HandicapStatusEntity.findNewId");
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

    public IHandicapStatusDTO add(IBasicDTO handicapStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            HandicapStatusEntity handicapStatusEntity = new HandicapStatusEntity();
            IHandicapStatusDTO handicapStatusDTO = (IHandicapStatusDTO)handicapStatusDTO1;
            handicapStatusEntity.setCapstatusName(handicapStatusDTO.getName());
            Long newID = findNewId();
            IHandicapStatusEntityKey ent = new HandicapStatusEntityKey(newID);
            handicapStatusEntity.setCapstatusCode(newID);
            handicapStatusEntity.setHandicapRatio(handicapStatusDTO.getHandicapRatio());
            handicapStatusEntity.setCreatedBy(handicapStatusDTO.getCreatedBy());
            handicapStatusEntity.setCreatedDate(handicapStatusDTO.getCreatedDate());
            handicapStatusEntity.setAuditStatus(handicapStatusDTO.getAuditStatus());
            handicapStatusEntity.setTabrecSerial(handicapStatusDTO.getTabrecSerial());
            doAdd(handicapStatusEntity);
            // Set PK after creation
            handicapStatusDTO.setCode(ent);
            return handicapStatusDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO handicapStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IHandicapStatusDTO handicapStatusDTO = (IHandicapStatusDTO)handicapStatusDTO1;
            HandicapStatusEntity handicapStatusEntity =
                EM().find(HandicapStatusEntity.class, (IHandicapStatusEntityKey)handicapStatusDTO.getCode());
            if (handicapStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            handicapStatusEntity.setCapstatusName(handicapStatusDTO.getName());
            handicapStatusEntity.setCapstatusCode(((IHandicapStatusEntityKey)handicapStatusDTO.getCode()).getCapstatusCode());
            handicapStatusEntity.setHandicapRatio(handicapStatusDTO.getHandicapRatio());
            handicapStatusEntity.setCreatedBy(handicapStatusDTO.getCreatedBy());
            handicapStatusEntity.setCreatedDate(handicapStatusDTO.getCreatedDate());
            handicapStatusEntity.setLastUpdatedBy(handicapStatusDTO.getLastUpdatedBy());
            handicapStatusEntity.setLastUpdatedDate(handicapStatusDTO.getLastUpdatedDate());
            handicapStatusEntity.setAuditStatus(handicapStatusDTO.getAuditStatus());
            handicapStatusEntity.setTabrecSerial(handicapStatusDTO.getTabrecSerial());
            doUpdate(handicapStatusEntity);
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

    public Boolean remove(IBasicDTO handicapStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IHandicapStatusDTO handicapStatusDTO = (IHandicapStatusDTO)handicapStatusDTO1;
            HandicapStatusEntity handicapStatusEntity =
                EM().find(HandicapStatusEntity.class, (IHandicapStatusEntityKey)handicapStatusDTO.getCode());
            if (handicapStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(handicapStatusEntity);
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
            return EM().createNamedQuery("HandicapStatusEntity.getCodeName").setParameter("notAduiting",
                                                                                          0).setParameter("accepted",
                                                                                                          3).getResultList();

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getCodeNameExceptOne() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("HandicapStatusEntity.getCodeNameExceptOne").setParameter("natural",
                                                                                                   1).getResultList();
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
            List<HandicapStatusEntity> list =
                EM().createNamedQuery("HandicapStatusEntity.searchByCode").setParameter("capStatusCode",
                                                                                        (Long)code).getResultList();
            for (HandicapStatusEntity entity : list) {
                arrayList.add(InfDTOFactory.createHandicapStatusDTO(entity));
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
            //        List<HandicapStatusEntity> list =
            //            em.createNamedQuery("HandicapStatusEntity.searchByName").setParameter("capStatusName",
            //                                                                                  searchName).getResultList();
            List<BasicEntity> list = searchByName("HandicapStatusEntity", "capstatusName", searchName);
            //em.createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list != null) {
                for (BasicEntity obj : list) {
                    HandicapStatusEntity entity = (HandicapStatusEntity)obj;
                    //for (HandicapStatusEntity entity : list) {
                    arrayList.add(InfDTOFactory.createHandicapStatusDTO(entity));
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
    
    @Override
    public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
        
        try {
            List list =
                EM().createNamedQuery("HandicapStatusEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createHandicapStatusDTO((HandicapStatusEntity)list.get(0)));
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
