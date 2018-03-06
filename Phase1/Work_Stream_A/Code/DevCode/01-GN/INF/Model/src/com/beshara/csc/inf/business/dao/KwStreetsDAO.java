package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IKwStreetsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IKwStreetsEntityKey;
import com.beshara.csc.inf.business.entity.KwStreetsEntity;
import com.beshara.csc.inf.business.entity.KwStreetsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemCanNotBeDeletedException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class KwStreetsDAO extends InfBaseDAO {
    public KwStreetsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new KwStreetsDAO();
    }

    /**<code>select o from KwStreetsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IKwStreetsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<KwStreetsEntity> list =
                EM().createNamedQuery("KwStreetsEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            System.out.println("Size is >> " + list.size());
            if (list != null && list.size() == 0) {
                throw new NoResultException();
            }
            for (KwStreetsEntity kwStreetsEntity : list) {
                arrayList.add(InfDTOFactory.createKwStreetsDTO(kwStreetsEntity));
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
            Long id = (Long)EM().createNamedQuery("KwStreetsEntity.findNewId").getSingleResult();
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
     * Create a New KwStreetsEntity * @param kwStreetsDTO
     * @return IKwStreetsDTO
     */
    @Override
    public IBasicDTO add(IBasicDTO kwStreetsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IKwStreetsDTO kwStreetsDTO = (IKwStreetsDTO)kwStreetsDTO1;
            Long code = findNewId();
            KwStreetsEntity kwStreetsEntity = new KwStreetsEntity();
            kwStreetsEntity.setStreetCode(code);
            kwStreetsEntity.setStreetName(kwStreetsDTO.getName());
            kwStreetsEntity.setStreetLengthInKm(kwStreetsDTO.getStreetLengthInKm());

            doAdd(kwStreetsEntity);
            kwStreetsDTO.setCode(new KwStreetsEntityKey(code));

            return kwStreetsDTO;
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
     * Update an Existing KwStreetsEntity * @param kwStreetsDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO kwStreetsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IKwStreetsDTO kwStreetsDTO = (IKwStreetsDTO)kwStreetsDTO1;
            KwStreetsEntity kwStreetsEntity =
                EM().find(KwStreetsEntity.class, (IKwStreetsEntityKey)kwStreetsDTO.getCode());
            if (kwStreetsEntity == null) {
                throw new ItemNotFoundException();
            }
            kwStreetsEntity.setStreetName(kwStreetsDTO.getName());
            kwStreetsEntity.setStreetLengthInKm(kwStreetsDTO.getStreetLengthInKm());
            doUpdate(kwStreetsEntity);
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
     * Remove an Existing KwStreetsEntity * @param kwStreetsDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO kwStreetsDTO) throws DataBaseException, SharedApplicationException {
        try {
            KwStreetsEntity kwStreetsEntity =
                EM().find(KwStreetsEntity.class, (IKwStreetsEntityKey)kwStreetsDTO.getCode());
            if (kwStreetsEntity == null) {
                throw new ItemCanNotBeDeletedException();
            }
            doRemove(kwStreetsEntity);
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
     * Get KwStreetsEntity By Primary Key * @param id
     * @return IKwStreetsDTO
     */
    @Override
    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            KwStreetsEntity kwStreetsEntity = EM().find(KwStreetsEntity.class, ((IKwStreetsEntityKey)id));
            if (kwStreetsEntity == null) {
                throw new ItemNotFoundException();
            }
            IKwStreetsDTO kwStreetsDTO = InfDTOFactory.createKwStreetsDTO(kwStreetsEntity);
            return kwStreetsDTO;
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
     * get list of KwStreets with code and name ordered by name * @return List of KwStreets
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("KwStreetsEntity.getCodeName").getResultList();
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
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<BasicEntity> list = searchByName("KwStreetsEntity", "streetName", name);

            if (list != null) {
                for (BasicEntity obj : list) {
                    KwStreetsEntity entity = (KwStreetsEntity)obj;
                    //for (HandicapStatusEntity entity : list) {
                    arrayList.add(InfDTOFactory.createKwStreetsDTO(entity));
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
     * Get all by code equal code * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<KwStreetsEntity> list =
                EM().createNamedQuery("KwStreetsEntity.searchByCode").setParameter("streetCode",
                                                                                   (Long)code).getResultList();
            for (KwStreetsEntity entity : list) {
                arrayList.add(InfDTOFactory.createKwStreetsDTO(entity));
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

    @Override
    public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            List list =
                EM().createNamedQuery("KwStreetsEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                      "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createKwStreetsDTO((KwStreetsEntity)list.get(0)));
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

