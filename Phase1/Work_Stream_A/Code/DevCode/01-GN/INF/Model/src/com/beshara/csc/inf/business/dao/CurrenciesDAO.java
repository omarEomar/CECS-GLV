package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.ICurrenciesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.BuildingOwnerTypesEntity;
import com.beshara.csc.inf.business.entity.CurrenciesEntity;
import com.beshara.csc.inf.business.entity.CurrenciesEntityKey;
import com.beshara.csc.inf.business.entity.ICurrenciesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;


public class CurrenciesDAO extends InfBaseDAO {
    public CurrenciesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new CurrenciesDAO();
    }

    /**<code>select o from CurrenciesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<CurrenciesEntity> list =
                EM().createNamedQuery("CurrenciesEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (CurrenciesEntity currencies : list) {
                arrayList.add(InfDTOFactory.createCurrenciesDTO(currencies));
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
            Long id = (Long)EM().createNamedQuery("CurrenciesEntity.findNewId").getSingleResult();
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
     * Create a New CurrenciesEntity * @param currenciesDTO1
     * @return ICurrenciesDTO
     */
    @Override
    public ICurrenciesDTO add(IBasicDTO currenciesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            CurrenciesEntity currenciesEntity = new CurrenciesEntity();
            Long code = findNewId();
            ICurrenciesDTO currenciesDTO = (ICurrenciesDTO)currenciesDTO1;
            currenciesEntity.setCurrencyCode(code);
            currenciesEntity.setCurrencyName(currenciesDTO.getCurrencyName());
            currenciesEntity.setCurrencyAbrv1(currenciesDTO.getCurrencyAbrv1());
            currenciesEntity.setCurrencyAbrv2(currenciesDTO.getCurrencyAbrv2());
            doAdd(currenciesEntity);
            currenciesDTO.setCode(new CurrenciesEntityKey(code));
            currenciesDTO.setCurrencyCode(code);
            return currenciesDTO;
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
     * Update an Existing CurrenciesEntity * @param currenciesDTO1
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO currenciesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ICurrenciesDTO currenciesDTO = (ICurrenciesDTO)currenciesDTO1;
            CurrenciesEntity currenciesEntity =
                EM().find(CurrenciesEntity.class, (ICurrenciesEntityKey)currenciesDTO.getCode());
            currenciesEntity.setCurrencyCode(((ICurrenciesEntityKey)currenciesDTO.getCode()).getCurrencyCode());
            currenciesEntity.setCurrencyName(currenciesDTO.getCurrencyName());
            currenciesEntity.setCurrencyAbrv1(currenciesDTO.getCurrencyAbrv1());
            currenciesEntity.setCurrencyAbrv2(currenciesDTO.getCurrencyAbrv2());

            doUpdate(currenciesEntity);
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
     * Remove an Existing CurrenciesEntity * @param currenciesDTO1
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO currenciesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ICurrenciesDTO currenciesDTO = (ICurrenciesDTO)currenciesDTO1;
            CurrenciesEntity currenciesEntity =
                EM().find(CurrenciesEntity.class, (ICurrenciesEntityKey)currenciesDTO.getCode());
            if (currenciesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(currenciesEntity);
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
     * Get CurrenciesEntity By Primary Key * @param id1
     * @return ICurrenciesDTO
     */
    @Override
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            ICurrenciesEntityKey id = (ICurrenciesEntityKey)id1;
            CurrenciesEntity currenciesEntity = EM().find(CurrenciesEntity.class, id);
            if (currenciesEntity == null) {
                throw new ItemNotFoundException();
            }
            ICurrenciesDTO currenciesDTO = InfDTOFactory.createCurrenciesDTO(currenciesEntity);
            return currenciesDTO;
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
     * Search for CurrenciesEntity * <br> * @return List
     */
    @Override
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }

    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<CurrenciesEntity> list =
                EM().createNamedQuery("CurrenciesEntity.searchByCode").setParameter("currencyCode",
                                                                                    (Long)code).getResultList();
            for (CurrenciesEntity entity : list) {
                arrayList.add(InfDTOFactory.createCurrenciesDTO(entity));
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

    // @EditedBy @author Aly Noor @since 06/26/2014 eidted to use generic method InfBaseFacadeBean.searchByName

    @Override
    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();

            List<BasicEntity> list = searchByName("CurrenciesEntity", "currencyName", searchName);
            if (list != null) {
                for (BasicEntity obj : list) {
                    CurrenciesEntity entity = (CurrenciesEntity)obj;
                    //for (CurrenciesEntity entity : list) {
                    arrayList.add(InfDTOFactory.createCurrenciesDTO(entity));
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

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("CurrenciesEntity.getCodeName").getResultList();
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
                EM().createNamedQuery("CurrenciesEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createCurrenciesDTO((CurrenciesEntity)list.get(0)));
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
