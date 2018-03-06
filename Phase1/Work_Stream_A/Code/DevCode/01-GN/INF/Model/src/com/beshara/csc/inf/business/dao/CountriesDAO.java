package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.ICountriesDTO;
import com.beshara.csc.inf.business.dto.ICountriesSearchDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.CountriesEntity;
import com.beshara.csc.inf.business.entity.CountriesEntityKey;
import com.beshara.csc.inf.business.entity.CurrenciesEntity;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.inf.business.entity.ICurrenciesEntityKey;
import com.beshara.csc.inf.business.entity.ILanguagesEntityKey;
import com.beshara.csc.inf.business.entity.LanguagesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class CountriesDAO extends InfBaseDAO {
    public CountriesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new CountriesDAO();
    }

    /**<code>select o from CountriesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<ICountriesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<CountriesEntity> list =
                EM().createNamedQuery("CountriesEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (CountriesEntity countries : list) {
                arrayList.add(InfDTOFactory.createCountriesDTO(countries));
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
            Long id = (Long)EM().createNamedQuery("CountriesEntity.findNewId").getSingleResult();
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
     * Create a New CountriesEntity * @param countriesDTO1
     * @return ICountriesDTO
     */
    @Override
    public IBasicDTO add(IBasicDTO countriesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            CountriesEntity countriesEntity = new CountriesEntity();
            Long code = findNewId();
            ICountriesDTO countriesDTO = (ICountriesDTO)countriesDTO1;
            countriesEntity.setCntryCode(code);
            countriesEntity.setCntryName(countriesDTO.getName());
            if (countriesDTO.getLanguagesDTO() != null) {
                LanguagesEntity languagesEntity =
                    EM().find(LanguagesEntity.class, countriesDTO.getLanguagesDTO().getCode());
                if (languagesEntity != null) {
                    countriesEntity.setLanguagesEntity(languagesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            if (countriesDTO.getCurrenciesDTO() != null) {
                CurrenciesEntity currenciesEntity =
                    EM().find(CurrenciesEntity.class, countriesDTO.getCurrenciesDTO().getCode());
                if (currenciesEntity != null) {
                    countriesEntity.setCurrenciesEntity(currenciesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            countriesEntity.setCreatedBy(countriesDTO.getCreatedBy());
            countriesEntity.setCreatedDate(countriesDTO.getCreatedDate());
            countriesEntity.setLastUpdatedBy(countriesDTO.getLastUpdatedBy());
            countriesEntity.setLastUpdatedDate(countriesDTO.getLastUpdatedDate());
            countriesEntity.setCtyclassCode(countriesDTO.getCtyclassCode());

            doAdd(countriesEntity);
            countriesDTO.setCode(new CountriesEntityKey(code));

            return countriesDTO;
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
     * Update an Existing CountriesEntity * @param countriesDTO1
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO countriesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ICountriesDTO countriesDTO = (ICountriesDTO)countriesDTO1;
            CountriesEntity countriesEntity =
                EM().find(CountriesEntity.class, (ICountriesEntityKey)countriesDTO.getCode());
            countriesEntity.setCntryName(countriesDTO.getName());
            if (countriesDTO.getLanguagesDTO() != null) {
                LanguagesEntity languagesEntity =
                    EM().find(LanguagesEntity.class, (ILanguagesEntityKey)countriesDTO.getLanguagesDTO().getCode());
                if (languagesEntity != null) {
                    countriesEntity.setLanguagesEntity(languagesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            if (countriesDTO.getCurrenciesDTO() != null) {
                CurrenciesEntity currenciesEntity =
                    EM().find(CurrenciesEntity.class, (ICurrenciesEntityKey)countriesDTO.getCurrenciesDTO().getCode());
                if (currenciesEntity != null) {
                    countriesEntity.setCurrenciesEntity(currenciesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            countriesEntity.setCreatedBy(countriesDTO.getCreatedBy());
            countriesEntity.setCreatedDate(countriesDTO.getCreatedDate());
            countriesEntity.setLastUpdatedBy(countriesDTO.getLastUpdatedBy());
            countriesEntity.setLastUpdatedDate(countriesDTO.getLastUpdatedDate());
            countriesEntity.setCtyclassCode(countriesDTO.getCtyclassCode());
            doUpdate(countriesEntity);
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
     * Remove an Existing CountriesEntity * @param countriesDTO1
     * @return Boolean
     */
    public Boolean remove(Object countriesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ICountriesDTO countriesDTO = (ICountriesDTO)countriesDTO1;
            CountriesEntity countriesEntity =
                EM().find(CountriesEntity.class, (ICountriesEntityKey)countriesDTO.getCode());
            if (countriesEntity == null) {
                throw new FinderException();
            }
            doRemove(countriesEntity);
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
     * Get CountriesEntity By Primary Key * @param id
     * @return ICountriesDTO
     */
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            ICountriesEntityKey id = (ICountriesEntityKey)id1;
            CountriesEntity countriesEntity = EM().find(CountriesEntity.class, id);
            if (countriesEntity == null) {
                throw new ItemNotFoundException();
            }
            ICountriesDTO countriesDTO = InfDTOFactory.createCountriesDTO(countriesEntity);
            return countriesDTO;
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
     * Returns list of all countries Name and code only * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("CountriesEntity.getCodeName").getResultList();
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
    public List<IBasicDTO> search(IBasicDTO countriesSearchDTO) throws DataBaseException, SharedApplicationException {
        try {
            ICountriesSearchDTO searchDTO = (ICountriesSearchDTO)countriesSearchDTO;
            StringBuilder str = new StringBuilder("select o from CountriesEntity o WHERE o.cntryCode=o.cntryCode ");
            if (searchDTO.getCntryCode() != null)
                str.append(" AND o.cntryCode=" + searchDTO.getCntryCode());
            if (searchDTO.getCtyclassCode() != null)
                str.append(" AND o.ctyclassCode=" + searchDTO.getCtyclassCode());
            List<CountriesEntity> list = EM().createQuery(str.toString()).getResultList();
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (CountriesEntity entity : list) {
                arrayList.add(InfDTOFactory.createCountriesDTO(entity));
            }
            if (list.size() == 0)
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
    public List<IBasicDTO> searchByCode(Object cntryCode) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<CountriesEntity> list =
                EM().createNamedQuery("CountriesEntity.searchByCode").setParameter("cntryCode",
                                                                                   cntryCode).getResultList();
            for (CountriesEntity entity : list) {
                arrayList.add(InfDTOFactory.createCountriesDTO(entity));
            }
            if (list.size() == 0)
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

    public List<IBasicDTO> searchCountries(Long cntryCode, List<String> excludedList) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            StringBuilder str = new StringBuilder("select o from CountriesEntity o WHERE o.cntryCode = ");
            str.append(" " + cntryCode);
            if (excludedList != null && !excludedList.isEmpty()) {
                str.append(" AND o.cntryCode not in (");
                for (String code : excludedList) {
                    str.append(code).append(",");
                }
                str.deleteCharAt(str.length() - 1);
                str.append(")");
            }
            List<CountriesEntity> list = EM().createQuery(str.toString()).getResultList();

            for (CountriesEntity entity : list) {
                arrayList.add(InfDTOFactory.createCountriesDTO(entity.getCntryCode(), entity.getCntryName()));
            }

            if (list.size() == 0)
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

    public List<IBasicDTO> searchCountries(String searchName, List<String> excludedList) throws DataBaseException,
                                                                                                SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            StringBuilder str =
                new StringBuilder("select o from CountriesEntity o WHERE o.cntryCode = o.cntryCode and ");
            str.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.cntryName", searchName));
            if (excludedList != null && !excludedList.isEmpty()) {
                str.append(" AND o.cntryCode not in (");
                for (String code : excludedList) {
                    str.append(code).append(",");
                }
                str.deleteCharAt(str.length() - 1);
                str.append(")");
            }

            List<CountriesEntity> list = EM().createQuery(str.toString()).getResultList();
            if (list != null) {
                for (CountriesEntity entity : list) {
                    arrayList.add(InfDTOFactory.createCountriesDTO(entity.getCntryCode(), entity.getCntryName()));
                }
            }
            if (list.size() == 0)
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
    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<BasicEntity> list = searchByName("CountriesEntity", "cntryName", searchName);
            if (list != null) {
                for (BasicEntity obj : list) {
                    CountriesEntity entity = (CountriesEntity)obj;
                    arrayList.add(InfDTOFactory.createCountriesDTO(entity));
                }
            }
            if (list.size() == 0)
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

    public List<IBasicDTO> getAllOrderByClass() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<CountriesEntity> list =
                EM().createNamedQuery("CountriesEntity.getAllOrderByClass").setHint("toplink.refresh",
                                                                                    "true").getResultList();
            if (list.size() == 0)
                throw new NoResultException();
            for (CountriesEntity countries : list) {
                arrayList.add(InfDTOFactory.createCountriesDTO(countries));
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


    public List<IBasicDTO> checkDublicateName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<CountriesEntity> list =
                EM().createNamedQuery("CountriesEntity.checkDublicateName").setParameter("name",
                                                                                         name).setHint("toplink.refresh",
                                                                                                       "true").getResultList();
            for (CountriesEntity countries : list) {
                arrayList.add(InfDTOFactory.createCountriesDTO(countries));
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

    public List<IBasicDTO> getAllWithoutExcludedList(List<String> excludedList) throws DataBaseException,
                                                                                       SharedApplicationException {

        try {
            StringBuilder str = new StringBuilder("select o from CountriesEntity o WHERE o.cntryCode=o.cntryCode ");
            if (excludedList != null && !excludedList.isEmpty()) {
                str.append(" AND o.cntryCode not in (");
                for (String cntryCode : excludedList) {
                    str.append(cntryCode).append(",");
                }
                str.deleteCharAt(str.length() - 1);
                str.append(")");
            }
            List<CountriesEntity> list = EM().createQuery(str.toString()).getResultList();
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (CountriesEntity entity : list) {
                arrayList.add(InfDTOFactory.createCountriesDTO(entity.getCntryCode(), entity.getCntryName()));
            }
            if (list.size() == 0)
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
     * Get Code and name only for country
     * @param key
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy Kareem.Sayed
     */
    public IBasicDTO getCodeName(IEntityKey key) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list =
                EM().createNamedQuery("CountriesEntity.getSingleCodeName").setParameter("cntryCode", ((ICountriesEntityKey)key).getCntryCode()).getResultList();
            return list.get(0);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    public List<IBasicDTO> getCodeNameByQulKey(String qulKey) throws DataBaseException, SharedApplicationException{
        StringBuilder queryString = new StringBuilder();
        queryString.append(" SELECT DISTINCT CT.*");
        queryString.append(" FROM NL_QUL_CENTER_QUALIFICATIONS CQ, GN_MAP_MAPPED_DATA MD , NL_QUL_CENTERS C , INF_COUNTRIES CT");
        queryString.append("  WHERE OBJTYPE1_CODE = 3");
        queryString.append("        AND SOC1_VALUE = '" + qulKey + "'");
        queryString.append("        AND SOC1_CODE = 1");
        queryString.append("        AND C.CENTER_CODE = CQ.CENTER_CODE");
        queryString.append("        AND C.CNTRY_CODE = CT.CNTRY_CODE");
        queryString.append("        AND cq.CNTQUALIFICATION_CODE = MD.SOC2_VALUE");
        queryString.append("        AND CSNL_OWNER.HR_QUL_INTERFACE_PACK.GET_CENTER_MAPPED_CODE (C.CENTER_CODE,NULL,NULL) = SOC2_CODE");   
        List<CountriesEntity> list = EM().createNativeQuery(queryString.toString(), CountriesEntity.class).
                                                                                setHint("toplink.refresh", "true").getResultList();
        System.out.println(queryString.toString());
        List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
        for (CountriesEntity entity : list) {
            arrayList.add(InfDTOFactory.createCountriesDTO(entity.getCntryCode() , entity.getCntryName()));
        }
        return arrayList;
    }
}
