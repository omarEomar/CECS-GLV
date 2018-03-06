package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IBuildingOwnerTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.BuildingOwnerTypesEntity;
import com.beshara.csc.inf.business.entity.BuildingOwnerTypesEntityKey;
import com.beshara.csc.inf.business.entity.GenderTypesEntity;
import com.beshara.csc.inf.business.entity.IBuildingOwnerTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class BuildingOwnerTypesDAO extends InfBaseDAO {
    public BuildingOwnerTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new BuildingOwnerTypesDAO();
    }

    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            BuildingOwnerTypesEntity buildingOwnerTypesEntity =
                EM().find(BuildingOwnerTypesEntity.class, (IBuildingOwnerTypesEntityKey)id);
            if (buildingOwnerTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IBuildingOwnerTypesDTO buildingOwnerTypesDTO =
                InfDTOFactory.createBuildingOwnerTypesDTO(buildingOwnerTypesEntity);
            return buildingOwnerTypesDTO;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBuildingOwnerTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<BuildingOwnerTypesEntity> list =
                EM().createNamedQuery("BuildingOwnerTypesEntity.findAll").setHint("toplink.refresh",
                                                                                  "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (BuildingOwnerTypesEntity buildingOwnerTypes : list) {
                arrayList.add(InfDTOFactory.createBuildingOwnerTypesDTO(buildingOwnerTypes));
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
            Query query = EM().createNamedQuery("BuildingOwnerTypesEntity.findNewId");
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

    public IBuildingOwnerTypesDTO add(IBasicDTO buildingOwnerTypesDTO1) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            BuildingOwnerTypesEntity buildingOwnerTypesEntity = new BuildingOwnerTypesEntity();
            IBuildingOwnerTypesDTO buildingOwnerTypesDTO = (IBuildingOwnerTypesDTO)buildingOwnerTypesDTO1;
            Long newID = findNewId();
            IBuildingOwnerTypesEntityKey ent = new BuildingOwnerTypesEntityKey(newID);
            buildingOwnerTypesEntity.setOwntypeCode(newID);
            buildingOwnerTypesEntity.setOwntypeName(buildingOwnerTypesDTO.getName());
            buildingOwnerTypesEntity.setCreatedBy(buildingOwnerTypesDTO.getCreatedBy());
            buildingOwnerTypesEntity.setCreatedDate(buildingOwnerTypesDTO.getCreatedDate());
            buildingOwnerTypesEntity.setLastUpdatedBy(buildingOwnerTypesDTO.getLastUpdatedBy());
            buildingOwnerTypesEntity.setLastUpdatedDate(buildingOwnerTypesDTO.getLastUpdatedDate());
            doAdd(buildingOwnerTypesEntity);
            buildingOwnerTypesDTO.setCode(ent);
            return buildingOwnerTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO buildingOwnerTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IBuildingOwnerTypesDTO buildingOwnerTypesDTO = (IBuildingOwnerTypesDTO)buildingOwnerTypesDTO1;
            BuildingOwnerTypesEntity buildingOwnerTypesEntity =
                EM().find(BuildingOwnerTypesEntity.class, (IBuildingOwnerTypesEntityKey)buildingOwnerTypesDTO.getCode());

            buildingOwnerTypesEntity.setOwntypeCode(((IBuildingOwnerTypesEntityKey)buildingOwnerTypesDTO.getCode()).getOwntypeCode());
            buildingOwnerTypesEntity.setOwntypeName(buildingOwnerTypesDTO.getName());
            buildingOwnerTypesEntity.setCreatedBy(buildingOwnerTypesDTO.getCreatedBy());
            buildingOwnerTypesEntity.setCreatedDate(buildingOwnerTypesDTO.getCreatedDate());
            buildingOwnerTypesEntity.setLastUpdatedBy(buildingOwnerTypesDTO.getLastUpdatedBy());
            buildingOwnerTypesEntity.setLastUpdatedDate(buildingOwnerTypesDTO.getLastUpdatedDate());
            buildingOwnerTypesEntity.setAuditStatus(buildingOwnerTypesDTO.getAuditStatus());
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

    public Boolean remove(IBasicDTO buildingOwnerTypesDTO) throws DataBaseException, SharedApplicationException {
        try {
            BuildingOwnerTypesEntity buildingOwnerTypesEntity =
                EM().find(BuildingOwnerTypesEntity.class, (IBuildingOwnerTypesEntityKey)buildingOwnerTypesDTO.getCode());
            if (buildingOwnerTypesEntity == null) {
                throw new FinderException();
            }
            doRemove(buildingOwnerTypesEntity);

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

    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            list.add(getById(InfEntityKeyFactory.createBuildingOwnerTypesEntityKey((Long)code)));
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

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("BuildingOwnerTypesEntity.getCodeName").getResultList();
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
            // List<BuildingOwnerTypesEntity> list =
            //    em.createNamedQuery("BuildingOwnerTypesEntity.searchByName").setParameter("owntypeName",
            //                                                                              name).getResultList();
            List<BasicEntity> list = searchByName("BuildingOwnerTypesEntity", "owntypeName", searchName);
            //em.createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list != null) {
                for (BasicEntity obj : list) {
                    BuildingOwnerTypesEntity entity = (BuildingOwnerTypesEntity)obj;
                    //for (BuildingOwnerTypesEntity entity : list) {
                    arrayList.add(InfDTOFactory.createBuildingOwnerTypesDTO(entity));
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
    
    
    @Override
    public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
        
        try {
            List list =
                EM().createNamedQuery("BuildingOwnerTypesEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createBuildingOwnerTypesDTO((BuildingOwnerTypesEntity)list.get(0)));
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
