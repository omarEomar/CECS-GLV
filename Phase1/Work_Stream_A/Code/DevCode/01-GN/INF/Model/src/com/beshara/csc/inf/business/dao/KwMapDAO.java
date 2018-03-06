package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IKwMapDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.KwMapDTO;
import com.beshara.csc.inf.business.entity.IKwMapEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.KwMapEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.FinderException;


public class KwMapDAO extends InfBaseDAO {
    public KwMapDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new KwMapDAO();
    }

    public List<IKwMapDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<KwMapEntity> list =
                EM().createNamedQuery("KwMapEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (KwMapEntity kwMap : list) {
                arrayList.add(InfDTOFactory.createKwMapDTO(kwMap));
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
     * Create a New KwMapEntity * @param kwMapDTO1
     * @return IKwMapDTO
     */
    public IKwMapDTO add(IBasicDTO kwMapDTO1) throws DataBaseException, SharedApplicationException {
        try {
            KwMapEntity kwMapEntity = new KwMapEntity();
            IKwMapDTO kwMapDTO = (IKwMapDTO)kwMapDTO1;
            kwMapEntity.setMapName(kwMapDTO.getName());
            kwMapEntity.setMapCode(kwMapDTO1.getName());
            kwMapEntity.setTreeLevel(kwMapDTO.getTreeLevel());
            kwMapEntity.setLeafFlag(kwMapDTO.getLeafFlag());
            if (kwMapDTO.getParentObject() != null) {
                IBasicDTO parentDTO = kwMapDTO.getParentObject();
                KwMapEntity parent = EM().find(KwMapEntity.class, parentDTO.getCode());
                if (parent == null)
                    throw new ItemNotFoundException();
                kwMapEntity.setKwMapEntity(parent);
                kwMapEntity.setFirstParent(parent.getFirstParent());
                kwMapEntity.setMapCode(kwMapDTO.getName());
            } else {
                kwMapEntity.setFirstParent(((IKwMapEntityKey)kwMapDTO.getCode()).getMapCode());
            }
            kwMapEntity.setCreatedBy(kwMapDTO.getCreatedBy());
            kwMapEntity.setCreatedDate(kwMapDTO.getCreatedDate());
            kwMapEntity.setAuditStatus(kwMapDTO.getAuditStatus());
            kwMapEntity.setTabrecSerial(kwMapDTO.getTabrecSerial());
            return InfDTOFactory.createKwMapDTO(doAdd(kwMapEntity));
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
     * Update an Existing KwMapEntity * @param kwMapDTO1
     * @return Boolean
     */
    public Boolean update(IBasicDTO kwMapDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IKwMapDTO kwMapDTO = (IKwMapDTO)kwMapDTO1;
            KwMapEntity kwMapEntity = EM().find(KwMapEntity.class, kwMapDTO.getCode());
            kwMapEntity.setMapCode(((IKwMapEntityKey)kwMapDTO.getCode()).getMapCode());
            kwMapEntity.setMapName(kwMapDTO.getName());
            kwMapEntity.setTreeLevel(kwMapDTO.getTreeLevel());
            kwMapEntity.setLeafFlag(kwMapDTO.getLeafFlag());
            if (kwMapDTO.getParentObject() != null) {
                IBasicDTO parentDTO = kwMapDTO.getParentObject();
                KwMapEntity parent = EM().find(KwMapEntity.class, parentDTO.getCode());
                if (parent == null)
                    throw new ItemNotFoundException();
                kwMapEntity.setKwMapEntity(parent);
                kwMapEntity.setFirstParent(parent.getFirstParent());
            }
            kwMapEntity.setCreatedBy(kwMapDTO.getCreatedBy());
            kwMapEntity.setCreatedDate(kwMapDTO.getCreatedDate());
            kwMapEntity.setLastUpdatedBy(kwMapDTO.getLastUpdatedBy());
            kwMapEntity.setLastUpdatedDate(kwMapDTO.getLastUpdatedDate());
            kwMapEntity.setAuditStatus(kwMapDTO.getAuditStatus());
            kwMapEntity.setTabrecSerial(kwMapDTO.getTabrecSerial());
            doUpdate(kwMapEntity);
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
     * Remove an Existing KwMapEntity * @param kwMapDTO1
     * @return Boolean
     */
    public Boolean remove(IBasicDTO kwMapDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IKwMapDTO kwMapDTO = (IKwMapDTO)kwMapDTO1;
            KwMapEntity kwMapEntity = EM().find(KwMapEntity.class, kwMapDTO.getCode());
            if (kwMapEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(kwMapEntity);
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

    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("KwMapEntity.findNewId").getSingleResult();
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
     * Get KwMapEntity By Primary Key * @param id1
     * @return IKwMapDTO
     */
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IKwMapEntityKey id = (IKwMapEntityKey)id1;
            KwMapEntity kwMapEntity = EM().find(KwMapEntity.class, id);
            if (kwMapEntity == null) {
                throw new ItemNotFoundException();
            }
            IKwMapDTO kwMapDTO = InfDTOFactory.createKwMapDTO(kwMapEntity);
            Long childern =
                (Long)EM().createNamedQuery("KwMapEntity.countChildren").setParameter("mapCode", kwMapEntity.getMapCode()).getSingleResult();
            kwMapDTO.setChildernNumbers(new Long(childern != null ? childern : 0));
            return kwMapDTO;
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
            return EM().createNamedQuery("KwMapEntity.getCodeName").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long getChildrenNumber(IEntityKey entityKey) throws DataBaseException, SharedApplicationException {
        try {
            Object ChildrenCount =
                EM().createNamedQuery("KwMapEntity.countChildren").setParameter("mapCode", ((IKwMapEntityKey)entityKey).getMapCode()).setHint("toplink.refresh",
                                                                                                                                              "true").getSingleResult();
            if (ChildrenCount.equals(null))
                return new Long(0);
            else
                return (Long)ChildrenCount;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getFirstLevel() throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> firstLevelDtos = new ArrayList();
            List firstLevelEntities =
                EM().createNamedQuery("KwMapEntity.getFirstLevel").setParameter("treeLevel", ISystemConstant.FIRST_LEVEL_IN_TREE).getResultList();
            for (Object entity : firstLevelEntities) {
                IKwMapDTO kwMapDTO = InfDTOFactory.createKwMapDTO((KwMapEntity)entity);
                kwMapDTO.setChildernNumbers(getChildrenNumber(kwMapDTO.getCode()));
                firstLevelDtos.add(kwMapDTO);
            }
            return firstLevelDtos;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getChildrenList(IEntityKey entityKey) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> childrenListDtos = new ArrayList();

            String parentCode = ((IKwMapEntityKey)entityKey).getMapCode();
            if (parentCode == "") {
                parentCode = null;
            }
            List<KwMapEntity> childrenListEntities = null;
            try {
                childrenListEntities =
                        EM().createNamedQuery("KwMapEntity.getChildrenList").setParameter("mapCode", parentCode).setHint("toplink.refresh",
                                                                                                                         "true").getResultList();
            } catch (Throwable e) {
                throw new NoResultException();
            }
            for (KwMapEntity entity : childrenListEntities) {
                IKwMapDTO dto = InfDTOFactory.createKwMapDTO(entity);
                dto.setChildernNumbers(getChildrenNumber(dto.getCode()));
                childrenListDtos.add(dto);
            }
            return childrenListDtos;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Integer getTotalNumber() throws DataBaseException, SharedApplicationException {
        try {
            Long count =
                (Long)EM().createNamedQuery("KwMapEntity.getTotalNumber").setHint("toplink.refresh", "true").getSingleResult();
            if (count.equals(null))
                return new Integer(0);
            else
                return count.intValue();
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
     * get list of all classifications with name like a given string * @param name
     * @return List of IClassificationsDTO
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            StringBuilder query = new StringBuilder("select o from KwMapEntity o WHERE ");
            String condition = QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.mapName", name);
            query.append(condition);
            query.append("  ORDER BY o.mapCode   ");
            List<KwMapEntity> list = EM().createQuery(query.toString()).getResultList();
            for (KwMapEntity entity : list) {
                listDTO.add(new KwMapDTO(entity));
            }
            if (listDTO.size() == 0)
                throw new NoResultException();
            return listDTO;
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
     * Get all classification match search code * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            list.add(getById(InfEntityKeyFactory.createKwMapEntityKey(code.toString())));
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

    public List<IBasicDTO> searchByTypeCode(Long typeCode) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            List<KwMapEntity> entityList = new ArrayList<KwMapEntity>();
            entityList =
                    EM().createNamedQuery("KwMapEntity.searchByTypeCode").setParameter("typeCode", typeCode).setHint("toplink.refresh",
                                                                                                                     "true").getResultList();
            for (KwMapEntity entity : entityList) {
                list.add(new KwMapDTO(entity));
            }
            if (list.size() == 0)
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

    public List getAddress(Long mapCode) throws SharedApplicationException, DataBaseException {
        try {
            StringBuilder queryString = new StringBuilder("");
            queryString.append(" select GET_MAP_DESC(" + mapCode + ",1),   GET_MAP_DESC(" + mapCode +
                               ",2) ,  GET_MAP_DESC(" + mapCode + ",3) ");
            queryString.append(" From  dual  ");

            System.out.println("getAddress ==>  " + queryString.toString());

            List<Vector> list = EM().createNativeQuery(queryString.toString()).getResultList();
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
