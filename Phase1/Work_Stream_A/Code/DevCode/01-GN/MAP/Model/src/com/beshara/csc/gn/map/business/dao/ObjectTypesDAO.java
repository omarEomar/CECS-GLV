package com.beshara.csc.gn.map.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.dto.IObjectTypesDTO;
import com.beshara.csc.gn.map.business.entity.IObjectTypesEntityKey;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.business.entity.ObjectTypesEntity;
import com.beshara.csc.gn.map.business.entity.ObjectTypesEntityKey;
import com.beshara.csc.gn.map.business.facade.MapEntityConverter;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class ObjectTypesDAO extends MapBaseDAO {
    public ObjectTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new ObjectTypesDAO();
    }

    /**<code>select o from ObjectTypesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     * @throws RemoteException
     */
    @Override
    public List<IObjectTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<ObjectTypesEntity> list =
                EM().createNamedQuery("ObjectTypesEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (ObjectTypesEntity objectTypes : list) {
                arrayList.add(MapEntityConverter.getObjectTypesDTO(objectTypes));
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
            Long id = (Long)EM().createNamedQuery("ObjectTypesEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(0);
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
     * create a MapEntityConverter.getObjectTypesDTO * @param objectTypesDTO the dto to insert
     * @return objectTypesDTO the inserted dto with generated code
     * @throws RemoteException
     */
    @Override
    public IBasicDTO add(IBasicDTO objectTypesDTOParam) throws DataBaseException, SharedApplicationException {
        try {
            IObjectTypesDTO objectTypesDTO = (IObjectTypesDTO)objectTypesDTOParam;
            Long code = findNewId();
            ObjectTypesEntity objectTypesEntity = new ObjectTypesEntity();
            objectTypesEntity.setObjtypeCode(code);
            objectTypesEntity.setObjtypeName(objectTypesDTO.getName());
            doAdd(objectTypesEntity);
            objectTypesDTO.setCode(new ObjectTypesEntityKey(code));
            return objectTypesDTO;
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
     * update an existing IObjectTypesDTO * @param objectTypesDTO the dto to update
     * @return Boolean
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public Boolean update(IBasicDTO objectTypesDTOParam) throws DataBaseException, SharedApplicationException {
        try {
            System.out.println("IN FACADE------");
            IObjectTypesDTO objectTypesDTO = (IObjectTypesDTO)objectTypesDTOParam;
            ObjectTypesEntity objectTypesEntity = EM().find(ObjectTypesEntity.class, objectTypesDTO.getCode());
            if (objectTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            objectTypesEntity.setObjtypeName(objectTypesDTO.getName());
            doUpdate(objectTypesEntity);
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
     * Remove an existing dto * @param objectTypesDTO the dto to be removed
     * @return Boolean
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public Boolean remove(IBasicDTO objectTypesDTO) throws DataBaseException, SharedApplicationException {
        try {
            ObjectTypesEntity objectTypesEntity =
                EM().find(ObjectTypesEntity.class, (IObjectTypesEntityKey)objectTypesDTO.getCode());
            if (objectTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(objectTypesEntity);
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
     * Get ObjectTypesEntity By Primary Key * @param id
     * @return IObjectTypesDTO
     */
    @Override
    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            ObjectTypesEntity objectTypesEntity = EM().find(ObjectTypesEntity.class, (IObjectTypesEntityKey)id);
            if (objectTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IObjectTypesDTO objectTypesDTO = MapEntityConverter.getObjectTypesDTO(objectTypesEntity);
            return objectTypesDTO;
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
     * get all ObjectTypesDTOs with objecttypename like a given name * @return List
     * @throws RemoteException
     */
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();

            StringBuffer query = new StringBuffer("select o from ObjectTypesEntity o ");
            query.append(" where ");
            String condition = QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.objtypeName", name);
            query.append(condition);

            List<ObjectTypesEntity> list = EM().createQuery(query.toString()).getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (ObjectTypesEntity objectTypes : list) {
                arrayList.add(MapEntityConverter.getObjectTypesDTO(objectTypes));
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
     * get ObjectTypesDTOs with specific code * @param code
     * @return
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            list.add(getById(MapEntityKeyFactory.createObjectTypesEntityKey((Long)code)));
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
     * get all ObjectTypesDTOs with code and name set * @return List of IBasicDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("ObjectTypesEntity.getCodeName").setHint("toplink.refresh",
                                                                                  "true").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getObjectTypeSearchCrieteria(String searchQuery,
                                                        String searchType) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();

            StringBuffer query = new StringBuffer("select o from ObjectTypesEntity o ");
            query.append(" where ");
            if (searchType.equals("0")) {
                query.append("o.objtypeCode=");
                query.append(searchQuery);
            } else {
                String condition = QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.objtypeName", searchQuery);
                query.append(condition);
            }
            List<ObjectTypesEntity> list = EM().createQuery(query.toString()).getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (ObjectTypesEntity objectTypes : list) {
                arrayList.add(MapEntityConverter.getObjectTypesDTO(objectTypes));
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
