package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IGenderTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.GenderTypesEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.IGenderTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class GenderTypesDAO extends InfBaseDAO {
    public GenderTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new GenderTypesDAO();
    }

    public IGenderTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderTypesEntityKey id = (IGenderTypesEntityKey)id1;
            GenderTypesEntity genderTypesEntity = EM().find(GenderTypesEntity.class, id);
            if (genderTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IGenderTypesDTO genderTypesDTO = InfDTOFactory.createGenderTypesDTO(genderTypesEntity);
            return genderTypesDTO;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IGenderTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GenderTypesEntity> list =
                EM().createNamedQuery("GenderTypesEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (GenderTypesEntity genderTypes : list) {
                arrayList.add(InfDTOFactory.createGenderTypesDTO(genderTypes));
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
            Query query = EM().createNamedQuery("GenderTypesEntity.findNewId");
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


    public IGenderTypesDTO add(IBasicDTO genderTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            GenderTypesEntity genderTypesEntity = new GenderTypesEntity();
            IGenderTypesDTO genderTypesDTO = (IGenderTypesDTO)genderTypesDTO1;
            Long newID = findNewId();
            IGenderTypesEntityKey ent = new GenderTypesEntityKey(newID);
            genderTypesEntity.setGentypeCode(newID);
            genderTypesEntity.setGentypeName(genderTypesDTO.getName());
            genderTypesEntity.setCreatedBy(genderTypesDTO.getCreatedBy());
            genderTypesEntity.setCreatedDate(genderTypesDTO.getCreatedDate());
            genderTypesEntity.setAuditStatus(genderTypesDTO.getAuditStatus());
            genderTypesEntity.setTabrecSerial(genderTypesDTO.getTabrecSerial());
            doAdd(genderTypesEntity);
            // Set PK after creation
            genderTypesDTO.setCode(ent);
            return genderTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO genderTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderTypesDTO genderTypesDTO = (IGenderTypesDTO)genderTypesDTO1;
            GenderTypesEntity genderTypesEntity =
                EM().find(GenderTypesEntity.class, (IGenderTypesEntityKey)genderTypesDTO.getCode());
            if (genderTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            genderTypesEntity.setGentypeCode(((IGenderTypesEntityKey)genderTypesDTO.getCode()).getGentypeCode());
            genderTypesEntity.setGentypeName(genderTypesDTO.getName());
            genderTypesEntity.setCreatedBy(genderTypesDTO.getCreatedBy());
            genderTypesEntity.setCreatedDate(genderTypesDTO.getCreatedDate());
            genderTypesEntity.setLastUpdatedBy(genderTypesDTO.getLastUpdatedBy());
            genderTypesEntity.setLastUpdatedDate(genderTypesDTO.getLastUpdatedDate());
            genderTypesEntity.setAuditStatus(genderTypesDTO.getAuditStatus());
            genderTypesEntity.setTabrecSerial(genderTypesDTO.getTabrecSerial());
            doUpdate(genderTypesEntity);
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


    public Boolean remove(IBasicDTO genderTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderTypesDTO genderTypesDTO = (IGenderTypesDTO)genderTypesDTO1;
            GenderTypesEntity genderTypesEntity =
                EM().find(GenderTypesEntity.class, (IGenderTypesEntityKey)genderTypesDTO.getCode());
            if (genderTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(genderTypesEntity);
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

    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GenderTypesEntity> list =
                EM().createNamedQuery("GenderTypesEntity.searchByCode").setParameter("genderTypeCode",
                                                                                     (Long)code).getResultList();
            for (GenderTypesEntity entity : list) {
                arrayList.add(InfDTOFactory.createGenderTypesDTO(entity));
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
            //        List<GenderTypesEntity> list =
            //            em.createNamedQuery("GenderTypesEntity.searchByName").setParameter("genderTypeName", searchName).getResultList();
            List<BasicEntity> list = searchByName("GenderTypesEntity", "gentypeName", searchName);
            //em.createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list != null) {
                for (BasicEntity obj : list) {
                    GenderTypesEntity entity = (GenderTypesEntity)obj;
                    //for (GenderTypesEntity entity : list) {
                    arrayList.add(InfDTOFactory.createGenderTypesDTO(entity));
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
                EM().createNamedQuery("GenderTypesEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createGenderTypesDTO((GenderTypesEntity)list.get(0)));
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
