package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IDmlStatmentTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.DmlStatmentTypesEntity;
import com.beshara.csc.inf.business.entity.DmlStatmentTypesEntityKey;
import com.beshara.csc.inf.business.entity.IDmlStatmentTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class DmlStatmentTypesDAO extends InfBaseDAO {
    public DmlStatmentTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new DmlStatmentTypesDAO();
    }

    public IDmlStatmentTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IDmlStatmentTypesEntityKey id = (IDmlStatmentTypesEntityKey)id1;
            DmlStatmentTypesEntity dmlStatmentTypesEntity = EM().find(DmlStatmentTypesEntity.class, id);
            if (dmlStatmentTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IDmlStatmentTypesDTO dmlStatmentTypesDTO = InfDTOFactory.createDmlStatmentTypesDTO(dmlStatmentTypesEntity);
            return dmlStatmentTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IDmlStatmentTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<DmlStatmentTypesEntity> list =
                EM().createNamedQuery("DmlStatmentTypesEntity.findAll").setHint("toplink.refresh",
                                                                                "true").getResultList();

            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (DmlStatmentTypesEntity dmlStatmentTypes : list) {
                arrayList.add(InfDTOFactory.createDmlStatmentTypesDTO(dmlStatmentTypes));
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
            Query query = EM().createNamedQuery("DmlStatmentTypesEntity.findNewId");
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


    @Override
    public IDmlStatmentTypesDTO add(IBasicDTO dmlStatmentTypesDTO1) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            DmlStatmentTypesEntity dmlStatmentTypesEntity = new DmlStatmentTypesEntity();
            IDmlStatmentTypesDTO dmlStatmentTypesDTO = (IDmlStatmentTypesDTO)dmlStatmentTypesDTO1;
            Long newID = findNewId();
            IDmlStatmentTypesEntityKey ent = new DmlStatmentTypesEntityKey(newID);
            dmlStatmentTypesDTO.setCode(ent);
            dmlStatmentTypesDTO.getCode().setKey(((IDmlStatmentTypesEntityKey)dmlStatmentTypesDTO.getCode()).getDmlstatypeCode().toString());
            dmlStatmentTypesDTO.setName(dmlStatmentTypesDTO.getName());
            dmlStatmentTypesEntity.setDmlstatypeCode(((IDmlStatmentTypesEntityKey)dmlStatmentTypesDTO.getCode()).getDmlstatypeCode());
            dmlStatmentTypesEntity.setDmlstatypeName(dmlStatmentTypesDTO.getName());
            dmlStatmentTypesEntity.setCreatedBy(dmlStatmentTypesDTO.getCreatedBy());
            dmlStatmentTypesEntity.setCreatedDate(dmlStatmentTypesDTO.getCreatedDate());
            dmlStatmentTypesEntity.setAuditStatus(dmlStatmentTypesDTO.getAuditStatus());
            dmlStatmentTypesEntity.setTabrecSerial(dmlStatmentTypesDTO.getTabrecSerial());
            doAdd(dmlStatmentTypesEntity);
            // Set PK after creation
            return dmlStatmentTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO dmlStatmentTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IDmlStatmentTypesDTO dmlStatmentTypesDTO = (IDmlStatmentTypesDTO)dmlStatmentTypesDTO1;
            DmlStatmentTypesEntity dmlStatmentTypesEntity =
                EM().find(DmlStatmentTypesEntity.class, (IDmlStatmentTypesEntityKey)dmlStatmentTypesDTO.getCode());
            if (dmlStatmentTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            dmlStatmentTypesEntity.setDmlstatypeCode(((IDmlStatmentTypesEntityKey)dmlStatmentTypesDTO.getCode()).getDmlstatypeCode());
            dmlStatmentTypesEntity.setDmlstatypeName(dmlStatmentTypesDTO.getName());
            dmlStatmentTypesEntity.setCreatedBy(dmlStatmentTypesDTO.getCreatedBy());
            dmlStatmentTypesEntity.setCreatedDate(dmlStatmentTypesDTO.getCreatedDate());
            dmlStatmentTypesEntity.setLastUpdatedBy(dmlStatmentTypesDTO.getLastUpdatedBy());
            dmlStatmentTypesEntity.setLastUpdatedDate(dmlStatmentTypesDTO.getLastUpdatedDate());
            dmlStatmentTypesEntity.setAuditStatus(dmlStatmentTypesDTO.getAuditStatus());
            dmlStatmentTypesEntity.setTabrecSerial(dmlStatmentTypesDTO.getTabrecSerial());
            doUpdate(dmlStatmentTypesEntity);
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

    public Boolean remove(IBasicDTO dmlStatmentTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IDmlStatmentTypesDTO dmlStatmentTypesDTO = (IDmlStatmentTypesDTO)dmlStatmentTypesDTO1;
            DmlStatmentTypesEntity dmlStatmentTypesEntity =
                EM().find(DmlStatmentTypesEntity.class, (IDmlStatmentTypesEntityKey)dmlStatmentTypesDTO.getCode());
            if (dmlStatmentTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(dmlStatmentTypesEntity);

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

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("DmlStatmentTypesEntity.getCodeName").getResultList();
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

    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<DmlStatmentTypesEntity> list =
                EM().createNamedQuery("DmlStatmentTypesEntity.searchByCode").setParameter("dmlstatypeCode",
                                                                                          (Long)code).getResultList();
            for (DmlStatmentTypesEntity entity : list) {
                arrayList.add(InfDTOFactory.createDmlStatmentTypesDTO(entity));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
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
            //        List<DmlStatmentTypesEntity> list =
            //            em.createNamedQuery("DmlStatmentTypesEntity.searchByName").setParameter("dmlstatypeName",
            //                                                                                    searchName).getResultList();
            List<BasicEntity> list = searchByName("DmlStatmentTypesEntity", "dmlstatypeName", searchName);
            //em.createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list != null) {
                for (BasicEntity obj : list) {
                    DmlStatmentTypesEntity entity = (DmlStatmentTypesEntity)obj;
                    //for (DmlStatmentTypesEntity entity : list) {
                    arrayList.add(InfDTOFactory.createDmlStatmentTypesDTO(entity));
                }
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else
                throw (SharedApplicationException)e;
        }
    }
    
    public List<BasicEntity> searchByName(String entityName, String colName,
                                          String searchName) throws DataBaseException, SharedApplicationException {

        StringBuilder ejbQL = new StringBuilder("select o from ");
        ejbQL.append(entityName);
        ejbQL.append(" o where ( ");
        ejbQL.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o." + colName, searchName));
        ejbQL.append(" ) order by o.");
        ejbQL.append(colName);
        List<BasicEntity> list = EM().createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();

        if (list == null || list.size() == 0)
            throw new NoResultException();
        return list;
    }
}


