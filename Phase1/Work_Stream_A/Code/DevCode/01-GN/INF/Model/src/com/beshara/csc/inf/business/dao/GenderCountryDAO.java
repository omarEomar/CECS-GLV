package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IGenderCountryDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.CountriesEntity;
import com.beshara.csc.inf.business.entity.GenderCountryEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntity;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.inf.business.entity.IGenderCountryEntityKey;
import com.beshara.csc.inf.business.entity.IGenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;


public class GenderCountryDAO extends InfBaseDAO {
    public GenderCountryDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new GenderCountryDAO();
    }

    public List<IGenderCountryDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GenderCountryEntity> list =
                EM().createNamedQuery("GenderCountryEntity.findAll").setHint("toplink.refresh",
                                                                             "true").getResultList();
            for (GenderCountryEntity genderCountry : list) {
                arrayList.add(InfDTOFactory.createGenderCountryDTO(genderCountry));
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
     * Create a New GenderCountryEntity * @param genderCountryDTO1
     * @return IGenderCountryDTO
     */
    public IGenderCountryDTO add(IBasicDTO genderCountryDTO1) throws DataBaseException, SharedApplicationException {
        try {
            GenderCountryEntity genderCountryEntity = new GenderCountryEntity();
            Long countryCode =
                ((ICountriesEntityKey)((IGenderCountryDTO)genderCountryDTO1).getCountriesDTO().getCode()).getCntryCode();
            Long genderTypeCode =
                ((IGenderTypesEntityKey)((IGenderCountryDTO)genderCountryDTO1).getGenderTypesDTO().getCode()).getGentypeCode();
            genderCountryDTO1.setCode(InfEntityKeyFactory.createGenderCountryEntityKey(countryCode, genderTypeCode));
            IGenderCountryDTO genderCountryDTO = (IGenderCountryDTO)genderCountryDTO1;
            genderCountryEntity.setGentypeCode(((IGenderCountryEntityKey)genderCountryDTO.getCode()).getGentypeCode());
            genderCountryEntity.setCntryCode(((IGenderCountryEntityKey)genderCountryDTO.getCode()).getCntryCode());

            if (genderCountryDTO.getCountriesDTO() == null) {
                throw new ItemNotFoundException();
            } else {
                CountriesEntity countriesEntity =
                    EM().find(CountriesEntity.class, genderCountryDTO.getCountriesDTO().getCode());
                if (countriesEntity != null) {
                    genderCountryEntity.setCountriesEntity(countriesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            if (genderCountryDTO.getGenderTypesDTO() == null) {
                throw new ItemNotFoundException();
            } else {
                GenderTypesEntity genderTypesEntity =
                    EM().find(GenderTypesEntity.class, genderCountryDTO.getGenderTypesDTO().getCode());
                if (genderTypesEntity != null) {
                    genderCountryEntity.setGenderTypesEntity(genderTypesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            genderCountryEntity.setGencntryName(genderCountryDTO.getName());
            genderCountryEntity.setCreatedBy(genderCountryDTO.getCreatedBy());
            genderCountryEntity.setCreatedDate(genderCountryDTO.getCreatedDate());
            genderCountryEntity.setAuditStatus(genderCountryDTO.getAuditStatus());
            genderCountryEntity.setTabrecSerial(genderCountryDTO.getTabrecSerial());
            doAdd(genderCountryEntity);
            // Set PK after creation
            return genderCountryDTO;
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
     * Update an Existing GenderCountryEntity * @param genderCountryDTO1
     * @return Boolean
     */
    public Boolean update(IBasicDTO genderCountryDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderCountryDTO genderCountryDTO = (IGenderCountryDTO)genderCountryDTO1;
            GenderCountryEntity genderCountryEntity = EM().find(GenderCountryEntity.class, genderCountryDTO.getCode());
            genderCountryEntity.setGentypeCode(((IGenderCountryEntityKey)genderCountryDTO.getCode()).getGentypeCode());
            genderCountryEntity.setCntryCode(((IGenderCountryEntityKey)genderCountryDTO.getCode()).getCntryCode());
            if (genderCountryDTO.getCountriesDTO() == null) {
                throw new ItemNotFoundException();
            } else {
                CountriesEntity countriesEntity =
                    EM().find(CountriesEntity.class, genderCountryDTO.getCountriesDTO().getCode());
                if (countriesEntity != null) {
                    genderCountryEntity.setCountriesEntity(countriesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            if (genderCountryDTO.getGenderTypesDTO() == null) {
                throw new ItemNotFoundException();
            } else {
                GenderTypesEntity genderTypesEntity =
                    EM().find(GenderTypesEntity.class, genderCountryDTO.getGenderTypesDTO().getCode());
                if (genderTypesEntity != null) {
                    genderCountryEntity.setGenderTypesEntity(genderTypesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            genderCountryEntity.setGencntryName(genderCountryDTO.getName());
            genderCountryEntity.setCreatedBy(genderCountryDTO.getCreatedBy());
            genderCountryEntity.setCreatedDate(genderCountryDTO.getCreatedDate());
            genderCountryEntity.setLastUpdatedBy(genderCountryDTO.getLastUpdatedBy());
            genderCountryEntity.setLastUpdatedDate(genderCountryDTO.getLastUpdatedDate());
            genderCountryEntity.setAuditStatus(genderCountryDTO.getAuditStatus());
            genderCountryEntity.setTabrecSerial(genderCountryDTO.getTabrecSerial());
            doUpdate(genderCountryEntity);
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
     * Remove an Existing GenderCountryEntity * @param genderCountryDTO1
     * @return Boolean
     */
    public Boolean remove(IBasicDTO genderCountryDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderCountryDTO genderCountryDTO = (IGenderCountryDTO)genderCountryDTO1;
            GenderCountryEntity genderCountryEntity = EM().find(GenderCountryEntity.class, genderCountryDTO.getCode());
            if (genderCountryEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(genderCountryEntity);
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
     * Get GenderCountryEntity By Primary Key * @param id1
     * @return IGenderCountryDTO
     */
    public IGenderCountryDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderCountryEntityKey id = (IGenderCountryEntityKey)id1;
            GenderCountryEntity genderCountryEntity = EM().find(GenderCountryEntity.class, id);
            if (genderCountryEntity == null) {
                throw new ItemNotFoundException();
            }
            IGenderCountryDTO genderCountryDTO = InfDTOFactory.createGenderCountryDTO(genderCountryEntity);
            return genderCountryDTO;
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
            Long id = (Long)EM().createNamedQuery("GenderCountryEntity.findNewId").getSingleResult();
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
     * List gender names by country code * @param
     * @return List of IGenderCountryDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> getGenderNames(Long countryCode) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<GenderCountryEntity> list =
                EM().createNamedQuery("GenderCountryEntity.getGenderNames").setParameter("countryCode",
                                                                                         countryCode).getResultList();
            for (GenderCountryEntity entity : list) {
                arrayList.add(InfDTOFactory.createGenderCountryDTO(entity));
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
//////////////////////////////////////////////////////////////////////
        /**
     * List Country Names by Gender Type * @param
     * @return List of IGenderCountryDTO
     * @throws RemoteException
     */
     public List<IBasicDTO> getCountriesByGenderType(Long genderType) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<GenderCountryEntity> list =
                EM().createNamedQuery("GenderCountryEntity.getCountriesByGenderType").setParameter("gentypeCode",
                                                                                         genderType).getResultList();
            for (GenderCountryEntity entity : list) {
                arrayList.add(InfDTOFactory.createGenderCountryDTO(entity));
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
    
    
    
    
    
    
    
    
    
//////////////////////////////////////////////////////////////////////    
    
    
    
}
