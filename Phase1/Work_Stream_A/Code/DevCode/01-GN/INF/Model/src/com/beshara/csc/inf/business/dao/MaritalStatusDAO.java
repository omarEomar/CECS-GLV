package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IMaritalStatusDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IMaritalStatusEntityKey;
import com.beshara.csc.inf.business.entity.MaritalStatusEntity;
import com.beshara.csc.inf.business.entity.MaritalStatusEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;


public class MaritalStatusDAO extends InfBaseDAO {
    public MaritalStatusDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new MaritalStatusDAO();
    }

    /**<code>select o from MaritalStatusEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        try {
            List<MaritalStatusEntity> list =
                EM().createNamedQuery("MaritalStatusEntity.findAll").setHint("toplink.refresh",
                                                                             "true").getResultList();
            for (MaritalStatusEntity maritalStatus : list) {
                arrayList.add(InfDTOFactory.createMaritalStatusDTO(maritalStatus));
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
            Long id = (Long)EM().createNamedQuery("MaritalStatusEntity.findNewId").getSingleResult();
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
     * Create a New MaritalStatusEntity * @param maritalStatusDTO1
     * @return IMaritalStatusDTO
     */
    @Override
    public IMaritalStatusDTO add(IBasicDTO maritalStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            MaritalStatusEntity maritalStatusEntity = new MaritalStatusEntity();
            Long code = findNewId();
            IMaritalStatusDTO maritalStatusDTO = (IMaritalStatusDTO)maritalStatusDTO1;
            maritalStatusEntity.setMarstatusCode(code);
            maritalStatusEntity.setMarStatusName(maritalStatusDTO.getName());
            doAdd(maritalStatusEntity);
            maritalStatusDTO.setCode(new MaritalStatusEntityKey(code));
            maritalStatusDTO.setMarstatusCode(code);
            return maritalStatusDTO;
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
     * Update an Existing MaritalStatusEntity * @param maritalStatusDTO1
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO maritalStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IMaritalStatusDTO maritalStatusDTO = (IMaritalStatusDTO)maritalStatusDTO1;
            MaritalStatusEntity maritalStatusEntity =
                EM().find(MaritalStatusEntity.class, (IMaritalStatusEntityKey)maritalStatusDTO.getCode());
            maritalStatusEntity.setMarstatusCode(((IMaritalStatusEntityKey)maritalStatusDTO.getCode()).getMarstatusCode());
            maritalStatusEntity.setMarStatusName(maritalStatusDTO.getName());

            doUpdate(maritalStatusEntity);
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
     * Remove an Existing MaritalStatusEntity * @param maritalStatusDTO1
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO maritalStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IMaritalStatusDTO maritalStatusDTO = (IMaritalStatusDTO)maritalStatusDTO1;
            MaritalStatusEntity maritalStatusEntity =
                EM().find(MaritalStatusEntity.class, (IMaritalStatusEntityKey)maritalStatusDTO.getCode());
            if (maritalStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(maritalStatusEntity);
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
     * Get MaritalStatusEntity By Primary Key * @param id
     * @return IMaritalStatusDTO
     */
    @Override
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IMaritalStatusEntityKey id = (IMaritalStatusEntityKey)id1;
            MaritalStatusEntity maritalStatusEntity = EM().find(MaritalStatusEntity.class, id);
            if (maritalStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            IMaritalStatusDTO maritalStatusDTO = InfDTOFactory.createMaritalStatusDTO(maritalStatusEntity);
            return maritalStatusDTO;
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
            return EM().createNamedQuery("MaritalStatusEntity.getCodeName").getResultList();
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
     * @throws Finder Exception
     */
    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<MaritalStatusEntity> list =
                EM().createNamedQuery("MaritalStatusEntity.searchByCode").setParameter("maritalStatusCode",
                                                                                       (Long)code).getResultList();
            for (MaritalStatusEntity entity : list) {
                arrayList.add(InfDTOFactory.createMaritalStatusDTO(entity));
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
     * Search for HandicapStatusEntity * <br> * @return List
     */
    @Override
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }

    /**
     * Get all by name like string * @param name
     * @return list
     * @throws RemoteException
     * @throws Finder Exception
     */
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        List<BasicEntity> list = searchByName("MaritalStatusEntity", "marStatusName", name);

        if (list != null) {
            for (BasicEntity obj : list) {
                MaritalStatusEntity entity = (MaritalStatusEntity)obj;
                arrayList.add(InfDTOFactory.createMaritalStatusDTO(entity));
            }
        }
        if (arrayList.size() == 0)
            throw new NoResultException();
        return arrayList;

    }

    public List<IBasicDTO> checkDublicateName(String name) throws DataBaseException, SharedApplicationException {
        try {
            List<MaritalStatusEntity> list =
                EM().createNamedQuery("MaritalStatusEntity.checkDublicateName").setParameter("name",
                                                                                             name).getResultList();
            ArrayList arrayList = new ArrayList();
            for (MaritalStatusEntity maritalStatusEntity : list) {
                arrayList.add(InfDTOFactory.createMaritalStatusDTO(maritalStatusEntity));
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
