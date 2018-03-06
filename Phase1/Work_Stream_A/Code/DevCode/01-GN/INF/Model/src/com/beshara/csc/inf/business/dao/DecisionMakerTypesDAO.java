package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IDecisionMakerTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.DecisionMakerTypesEntity;
import com.beshara.csc.inf.business.entity.DecisionMakerTypesEntityKey;
import com.beshara.csc.inf.business.entity.IDecisionMakerTypesEntityKey;
import com.beshara.csc.inf.business.facade.InfEntityConvertr;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class DecisionMakerTypesDAO extends InfBaseDAO {
    public DecisionMakerTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new DecisionMakerTypesDAO();
    }

    /**<code>select o from DecisionMakerTypesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IDecisionMakerTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<DecisionMakerTypesEntity> list =
                EM().createNamedQuery("DecisionMakerTypesEntity.findAll").setHint("toplink.refresh",
                                                                                  true).getResultList();
            for (DecisionMakerTypesEntity decisionMakerTypes : list) {
                arrayList.add(InfDTOFactory.createDecisionMakerTypesDTO(decisionMakerTypes));
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
            Long id = (Long)EM().createNamedQuery("DecisionMakerTypesEntity.findNewId").getSingleResult();
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
     * Create a New DecisionMakerTypesEntity * @param decisionMakerTypesDTO
     * @return IDecisionMakerTypesDTO
     */
    @Override
    public IDecisionMakerTypesDTO add(IBasicDTO decisionMakerTypesDTO1) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            DecisionMakerTypesEntity decisionMakerTypesEntity = new DecisionMakerTypesEntity();
            Long code = findNewId();
            IDecisionMakerTypesDTO decisionMakerTypesDTO = (IDecisionMakerTypesDTO)decisionMakerTypesDTO1;
            decisionMakerTypesEntity.setDecmkrtypeCode(code);
            decisionMakerTypesEntity.setDecmkrtypeName(decisionMakerTypesDTO.getName());
            doAdd(decisionMakerTypesEntity);
            decisionMakerTypesDTO.setDecmkrtypeCode(code);
            decisionMakerTypesDTO.setCode(new DecisionMakerTypesEntityKey(code));
            return decisionMakerTypesDTO;
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
     * Update an Existing DecisionMakerTypesEntity * @param decisionMakerTypesDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO decisionMakerTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IDecisionMakerTypesDTO decisionMakerTypesDTO = (IDecisionMakerTypesDTO)decisionMakerTypesDTO1;
            DecisionMakerTypesEntity decisionMakerTypesEntity =
                EM().find(DecisionMakerTypesEntity.class, (IDecisionMakerTypesEntityKey)decisionMakerTypesDTO.getCode());
            decisionMakerTypesEntity.setDecmkrtypeCode(((IDecisionMakerTypesEntityKey)decisionMakerTypesDTO.getCode()).getDecmkrtypeCode());
            decisionMakerTypesEntity.setDecmkrtypeName(decisionMakerTypesDTO.getName());
            doUpdate(decisionMakerTypesEntity);
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
     * Remove an Existing DecisionMakerTypesEntity * @param decisionMakerTypesDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO decisionMakerTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IDecisionMakerTypesDTO decisionMakerTypesDTO = (IDecisionMakerTypesDTO)decisionMakerTypesDTO1;
            DecisionMakerTypesEntity decisionMakerTypesEntity =
                EM().find(DecisionMakerTypesEntity.class, (IDecisionMakerTypesEntityKey)decisionMakerTypesDTO.getCode());
            if (decisionMakerTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(decisionMakerTypesEntity);
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
     * Get DecisionMakerTypesEntity By Primary Key * @param id
     * @return IDecisionMakerTypesDTO
     */
    @Override
    public IDecisionMakerTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IDecisionMakerTypesEntityKey id = (IDecisionMakerTypesEntityKey)id1;
            DecisionMakerTypesEntity decisionMakerTypesEntity = EM().find(DecisionMakerTypesEntity.class, id);
            if (decisionMakerTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IDecisionMakerTypesDTO decisionMakerTypesDTO =
                InfDTOFactory.createDecisionMakerTypesDTO(decisionMakerTypesEntity);
            return decisionMakerTypesDTO;
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
     * Search for DecisionMakerTypesEntity * <br> * @return List
     */
    @Override
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }

    /**
     * Get list of code and name * return list of IDecisionMakerTypesDTO initialize with code and name only * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("DecisionMakerTypesEntity.getCodeName").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getCodeNameByMin(Long minCode) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            StringBuilder nativeQuery = new StringBuilder();
            if (minCode != null) {
                nativeQuery.append("select INF_DECISION_MAKER_TYPES.DECMKRTYPE_CODE,INF_DECISION_MAKER_TYPES.DECMKRTYPE_NAME " +
                                   "from  INF_DECISION_MAKER_TYPES   " +
                                   "where INF_DECISION_MAKER_TYPES.DECMKRTYPE_CODE  " + "IN ( " +
                                   "select NL_REG_MIN_DECMKR_TYPES.DECMKRTYPE_CODE from  " +
                                   "NL_REG_MIN_DECMKR_TYPES where MIN_CODE = " + minCode + ") ");
            }

            System.out.println(nativeQuery.toString());

            Query query = EM().createNativeQuery(nativeQuery.toString(), DecisionMakerTypesEntity.class);
            List<DecisionMakerTypesEntity> list = query.getResultList();

            for (DecisionMakerTypesEntity decisionMakerTypesEntity : list) { //Modified
                arrayList.add(InfDTOFactory.createDecisionMakerTypesDTO(decisionMakerTypesEntity));
            }
            if (arrayList.size() == 0)
                throw new FinderException();

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
            List<DecisionMakerTypesEntity> list =
                EM().createNamedQuery("DecisionMakerTypesEntity.searchByCode").setParameter("decmkrtypeCode",
                                                                                            (Long)code).getResultList();
            for (DecisionMakerTypesEntity entity : list) {
                arrayList.add(InfDTOFactory.createDecisionMakerTypesDTO(entity));
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
     * Get all by name like string * @param searchName
     * @return list
     * @throws RemoteException
     * @throws FinderException
     * @EditedBy @author Aly Noor @since 06/26/2014 eidted to use generic method InfBaseFacadeBean.searchByName
     */
    @Override
    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<BasicEntity> list = searchByName("DecisionMakerTypesEntity", "decmkrtypeName", searchName);
            //em.createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list != null) {
                for (BasicEntity obj : list) {
                    DecisionMakerTypesEntity entity = (DecisionMakerTypesEntity)obj;
                    //for (DecisionMakerTypesEntity entity: list) {
                    arrayList.add(InfDTOFactory.createDecisionMakerTypesDTO(entity));
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

    /*
     * Date Created:11-09-2014
     * Author: H.Mounir
     * Purpose: this method used in regualation Div
     * */

    public List<IBasicDTO> getDecisionMakerTypesByRecType(Long regtypeCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            StringBuilder nativeQuery = new StringBuilder("select * from INF_DECISION_MAKER_TYPES decType ");
            nativeQuery.append(" where decType.DECMKRTYPE_CODE ");
            nativeQuery.append(" in( ");
            nativeQuery.append(" select DECMKRTYPE_CODE  ");
            nativeQuery.append(" from NL_REG_MIN_REG_TYPES ");
            nativeQuery.append(" where REGTYPE_CODE = ");
            nativeQuery.append(regtypeCode);
            nativeQuery.append(") order by DECMKRTYPE_NAME ");

            List<DecisionMakerTypesEntity> list =
                EM().createNativeQuery(nativeQuery.toString(), DecisionMakerTypesEntity.class).setHint("toplink.refresh",
                                                                                                       "true").getResultList();
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();

            for (DecisionMakerTypesEntity decisionMakerTypesEntity : list) {
                arrayList.add(InfEntityConvertr.getDecisionMakerTypesDTO(decisionMakerTypesEntity));
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
