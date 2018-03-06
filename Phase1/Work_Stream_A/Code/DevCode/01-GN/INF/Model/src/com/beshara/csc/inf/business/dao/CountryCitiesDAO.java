package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.ICountryCitiesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.CountriesEntity;
import com.beshara.csc.inf.business.entity.CountryCitiesEntity;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.inf.business.entity.ICountryCitiesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.constants.IInfConstants;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;


public class CountryCitiesDAO extends InfBaseDAO {
    public CountryCitiesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new CountryCitiesDAO();
    }

    public List<ICountryCitiesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<CountryCitiesEntity> list =
                EM().createNamedQuery("CountryCitiesEntity.findAll").setHint("toplink.refresh",
                                                                             "true").getResultList();
            for (CountryCitiesEntity countryCities : list) {
                arrayList.add(InfDTOFactory.createCountryCitiesDTO(countryCities));
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
     * Create a New CountryCitiesEntity * @param countryCitiesDTO1
     * @return ICountryCitiesDTO
     */
    public ICountryCitiesDTO add(IBasicDTO countryCitiesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            CountryCitiesEntity countryCitiesEntity = new CountryCitiesEntity();
            ICountryCitiesDTO countryCitiesDTO = (ICountryCitiesDTO)countryCitiesDTO1;
            Long countryCode = ((ICountriesEntityKey)countryCitiesDTO.getCountriesDTO().getCode()).getCntryCode();
            countryCitiesDTO.setCode(InfEntityKeyFactory.createCountryCitiesEntityKey(countryCode, findNewId()));
            countryCitiesEntity.setCntryCode(((ICountryCitiesEntityKey)countryCitiesDTO.getCode()).getCntryCode());
            countryCitiesEntity.setCntrycityCode(((ICountryCitiesEntityKey)countryCitiesDTO.getCode()).getCntrycityCode());
            if (countryCitiesDTO.getCountriesDTO() == null) {
                throw new ItemNotFoundException();
            } else {
                CountriesEntity countriesEntity =
                    EM().find(CountriesEntity.class, countryCitiesDTO.getCountriesDTO().getCode());
                if (countriesEntity != null) {
                    countryCitiesEntity.setCountriesEntity(countriesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            countryCitiesEntity.setCntrycityName(countryCitiesDTO.getName());
            countryCitiesEntity.setCapitalFlag(countryCitiesDTO.getCapitalFlag());
            countryCitiesEntity.setLongitude(countryCitiesDTO.getLongitude());
            countryCitiesEntity.setLatitude(countryCitiesDTO.getLatitude());
            countryCitiesEntity.setCreatedBy(countryCitiesDTO.getCreatedBy());
            countryCitiesEntity.setCreatedDate(countryCitiesDTO.getCreatedDate());
            countryCitiesEntity.setAuditStatus(countryCitiesDTO.getAuditStatus());
            countryCitiesEntity.setTabrecSerial(countryCitiesDTO.getTabrecSerial());
            doAdd(countryCitiesEntity);
            return countryCitiesDTO;
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
     * Update an Existing CountryCitiesEntity * @param countryCitiesDTO1
     * @return Boolean
     */
    public Boolean update(IBasicDTO countryCitiesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ICountryCitiesDTO countryCitiesDTO = (ICountryCitiesDTO)countryCitiesDTO1;
            CountryCitiesEntity countryCitiesEntity = EM().find(CountryCitiesEntity.class, countryCitiesDTO.getCode());
            countryCitiesEntity.setCntryCode(((ICountryCitiesEntityKey)countryCitiesDTO.getCode()).getCntryCode());
            countryCitiesEntity.setCntrycityCode(((ICountryCitiesEntityKey)countryCitiesDTO.getCode()).getCntrycityCode());
            if (countryCitiesDTO.getCountriesDTO() == null) {
                throw new ItemNotFoundException();
            } else {
                CountriesEntity countriesEntity =
                    EM().find(CountriesEntity.class, countryCitiesDTO.getCountriesDTO().getCode());
                if (countriesEntity != null) {
                    countryCitiesEntity.setCountriesEntity(countriesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            countryCitiesEntity.setCntrycityName(countryCitiesDTO.getName());
            countryCitiesEntity.setCapitalFlag(countryCitiesDTO.getCapitalFlag());
            countryCitiesEntity.setLongitude(countryCitiesDTO.getLongitude());
            countryCitiesEntity.setLatitude(countryCitiesDTO.getLatitude());
            countryCitiesEntity.setCreatedBy(countryCitiesDTO.getCreatedBy());
            countryCitiesEntity.setCreatedDate(countryCitiesDTO.getCreatedDate());
            countryCitiesEntity.setLastUpdatedBy(countryCitiesDTO.getLastUpdatedBy());
            countryCitiesEntity.setLastUpdatedDate(countryCitiesDTO.getLastUpdatedDate());
            countryCitiesEntity.setAuditStatus(countryCitiesDTO.getAuditStatus());
            countryCitiesEntity.setTabrecSerial(countryCitiesDTO.getTabrecSerial());
            doUpdate(countryCitiesEntity);
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
     * Remove an Existing CountryCitiesEntity * @param countryCitiesDTO1
     * @return Boolean
     */
    public Boolean remove(IBasicDTO countryCitiesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ICountryCitiesDTO countryCitiesDTO = (ICountryCitiesDTO)countryCitiesDTO1;
            CountryCitiesEntity countryCitiesEntity = EM().find(CountryCitiesEntity.class, countryCitiesDTO.getCode());
            if (countryCitiesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(countryCitiesEntity);
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
     * Get CountryCitiesEntity By Primary Key * @param id1
     * @return ICountryCitiesDTO
     */
    public ICountryCitiesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            ICountryCitiesEntityKey id = (ICountryCitiesEntityKey)id1;
            CountryCitiesEntity countryCitiesEntity = EM().find(CountryCitiesEntity.class, id);
            if (countryCitiesEntity == null) {
                throw new ItemNotFoundException();
            }
            ICountryCitiesDTO countryCitiesDTO = InfDTOFactory.createCountryCitiesDTO(countryCitiesEntity);
            return countryCitiesDTO;
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
            Long id = (Long)EM().createNamedQuery("CountryCitiesEntity.findNewId").getSingleResult();
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
     * List all groups by countryCode code * @param
     * @return List of ICountryCitiesDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> getCities(Long countryCode) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<CountryCitiesEntity> list =
                EM().createNamedQuery("CountryCitiesEntity.getCities").setParameter("countryCode",
                                                                                    countryCode).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            for (CountryCitiesEntity entity : list) {
                arrayList.add(InfDTOFactory.createCountryCitiesDTO(entity));
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
     * get country capital * @param countryCode
     * @return IBasicDTO
     * @throws RemoteException
     */
    public IBasicDTO getCapital(Long countryCode) throws DataBaseException, SharedApplicationException {
        try {
            List<CountryCitiesEntity> list =
                EM().createNamedQuery("CountryCitiesEntity.getCapital").setParameter("countryCode",
                                                                                     countryCode).setParameter("capitalFlag",
                                                                                                               IInfConstants.CAPITAL_FLAG).getResultList();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (CountryCitiesEntity entity : list) {
                listDTO.add(InfDTOFactory.createCountryCitiesDTO(entity));
            }
            if (listDTO.size() == 0)
                throw new ItemNotFoundException();
            return listDTO.get(0);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> searchByCode(Long cntryCode, Long cntrycityCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            List<CountryCitiesEntity> list =
                EM().createNamedQuery("CountryCitiesEntity.searchCountryByCode").setParameter("cntryCode",
                                                                                              cntryCode).setParameter("cntrycityCode",
                                                                                                                      cntrycityCode).getResultList();
            List<IBasicDTO> searchByCodeResult = new ArrayList<IBasicDTO>();
            for (CountryCitiesEntity entity : list) {
                searchByCodeResult.add(InfDTOFactory.createCountryCitiesDTO(entity));
            }
            if (searchByCodeResult.size() == 0)
                throw new NoResultException();
            return searchByCodeResult;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> searchByName(Long cntryCode, String cntrycityName) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            cntrycityName = "%" + cntrycityName + "%";
            List<CountryCitiesEntity> list =
                EM().createNamedQuery("CountryCitiesEntity.searchCountryByName").setParameter("cntryCode",
                                                                                              cntryCode).setParameter("cntrycityName",
                                                                                                                      cntrycityName).getResultList();
            List<IBasicDTO> searchByNameResult = new ArrayList<IBasicDTO>();
            for (CountryCitiesEntity entity : list) {
                searchByNameResult.add(InfDTOFactory.createCountryCitiesDTO(entity));
            }
            if (searchByNameResult.size() == 0)
                throw new NoResultException();
            return searchByNameResult;
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
                   EM().createNamedQuery("CountryCitiesEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                            "true").getResultList();
               if (list.size() > 0) {
                   return (InfDTOFactory.createCountryCitiesDTO((CountryCitiesEntity)list.get(0)));
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
