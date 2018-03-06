package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IGroupCountriesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.CountriesEntity;
import com.beshara.csc.inf.business.entity.CountryGroupsEntity;
import com.beshara.csc.inf.business.entity.GroupCountriesEntity;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.inf.business.entity.ICountryGroupsEntityKey;
import com.beshara.csc.inf.business.entity.IGroupCountriesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class GroupCountriesDAO extends InfBaseDAO {
    public GroupCountriesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new GroupCountriesDAO();
    }

    /**<code>select o from GroupCountriesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<IGroupCountriesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GroupCountriesEntity> list =
                EM().createNamedQuery("GroupCountriesEntity.findAll").setHint("toplink.refresh",
                                                                              "true").getResultList();
            for (GroupCountriesEntity groupCountries : list) {
                arrayList.add(InfDTOFactory.createGroupCountriesDTO(groupCountries));
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
     * Create a New GroupCountriesEntity * @param groupCountriesDTO1
     * @return IGroupCountriesDTO
     */
    public IGroupCountriesDTO add(IBasicDTO groupCountriesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            GroupCountriesEntity groupCountriesEntity = new GroupCountriesEntity();
            IGroupCountriesDTO groupCountriesDTO = (IGroupCountriesDTO)groupCountriesDTO1;
            Long countryCode = ((ICountriesEntityKey)groupCountriesDTO.getCountriesDTO().getCode()).getCntryCode();
            Long countryGroupsCode =
                ((ICountryGroupsEntityKey)groupCountriesDTO.getCountryGroupsDTO().getCode()).getCntrygrpCode();
            groupCountriesDTO.setCode(InfEntityKeyFactory.createGroupCountriesEntityKey(countryGroupsCode,
                                                                                        countryCode));
            groupCountriesEntity.setCntrygrpCode(((IGroupCountriesEntityKey)groupCountriesDTO.getCode()).getCntrygrpCode());
            groupCountriesEntity.setCntryCode(((IGroupCountriesEntityKey)groupCountriesDTO.getCode()).getCntryCode());
            if (groupCountriesDTO.getCountriesDTO() == null) {
                throw new ItemNotFoundException();
            } else {
                CountriesEntity countriesEntity =
                    EM().find(CountriesEntity.class, groupCountriesDTO.getCountriesDTO().getCode());
                if (countriesEntity != null) {
                    groupCountriesEntity.setCountriesEntity(countriesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            if (groupCountriesDTO.getCountryGroupsDTO() == null) {
                throw new ItemNotFoundException();
            } else {
                CountryGroupsEntity countryGroupsEntity =
                    EM().find(CountryGroupsEntity.class, groupCountriesDTO.getCountryGroupsDTO().getCode());
                if (countryGroupsEntity != null) {
                    groupCountriesEntity.setCountryGroupsEntity(countryGroupsEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            groupCountriesEntity.setCreatedBy(groupCountriesDTO.getCreatedBy());
            groupCountriesEntity.setCreatedDate(groupCountriesDTO.getCreatedDate());
            groupCountriesEntity.setAuditStatus(groupCountriesDTO.getAuditStatus());
            groupCountriesEntity.setTabrecSerial(groupCountriesDTO.getTabrecSerial());
            doAdd(groupCountriesEntity);
            // Set PK after creation
            return groupCountriesDTO;
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
     * Update an Existing GroupCountriesEntity * @param groupCountriesDTO1
     * @return Boolean
     */
    public Boolean update(IBasicDTO groupCountriesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IGroupCountriesDTO groupCountriesDTO = (IGroupCountriesDTO)groupCountriesDTO1;
            GroupCountriesEntity groupCountriesEntity =
                EM().find(GroupCountriesEntity.class, (IGroupCountriesEntityKey)groupCountriesDTO.getCode());
            groupCountriesEntity.setCntrygrpCode(((IGroupCountriesEntityKey)groupCountriesDTO.getCode()).getCntrygrpCode());
            groupCountriesEntity.setCntryCode(((IGroupCountriesEntityKey)groupCountriesDTO.getCode()).getCntryCode());
            if (groupCountriesDTO.getCountriesDTO() == null) {
                throw new ItemNotFoundException();
            } else {
                CountriesEntity countriesEntity =
                    EM().find(CountriesEntity.class, groupCountriesDTO.getCountriesDTO().getCode());
                if (countriesEntity != null) {
                    groupCountriesEntity.setCountriesEntity(countriesEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            if (groupCountriesDTO.getCountryGroupsDTO() == null) {
                throw new ItemNotFoundException();
            } else {
                CountryGroupsEntity countryGroupsEntity =
                    EM().find(CountryGroupsEntity.class, groupCountriesDTO.getCountryGroupsDTO().getCode());
                if (countryGroupsEntity != null) {
                    groupCountriesEntity.setCountryGroupsEntity(countryGroupsEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            groupCountriesEntity.setCreatedBy(groupCountriesDTO.getCreatedBy());
            groupCountriesEntity.setCreatedDate(groupCountriesDTO.getCreatedDate());
            groupCountriesEntity.setLastUpdatedBy(groupCountriesDTO.getLastUpdatedBy());
            groupCountriesEntity.setLastUpdatedDate(groupCountriesDTO.getLastUpdatedDate());
            groupCountriesEntity.setAuditStatus(groupCountriesDTO.getAuditStatus());
            groupCountriesEntity.setTabrecSerial(groupCountriesDTO.getTabrecSerial());
            doUpdate(groupCountriesEntity);
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
     * Remove an Existing GroupCountriesEntity * @param groupCountriesDTO1
     * @return Boolean
     */
    public Boolean remove(IBasicDTO groupCountriesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IGroupCountriesDTO groupCountriesDTO = (IGroupCountriesDTO)groupCountriesDTO1;
            GroupCountriesEntity groupCountriesEntity =
                EM().find(GroupCountriesEntity.class, groupCountriesDTO.getCode());
            if (groupCountriesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(groupCountriesEntity);
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
     * Get GroupCountriesEntity By Primary Key * @param id1
     * @return IGroupCountriesDTO
     */
    public IGroupCountriesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IGroupCountriesEntityKey id = (IGroupCountriesEntityKey)id1;
            GroupCountriesEntity groupCountriesEntity = EM().find(GroupCountriesEntity.class, id);
            if (groupCountriesEntity == null) {
                throw new ItemNotFoundException();
            }
            IGroupCountriesDTO groupCountriesDTO = InfDTOFactory.createGroupCountriesDTO(groupCountriesEntity);
            return groupCountriesDTO;
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
            Long id = (Long)EM().createNamedQuery("GroupCountriesEntity.findNewId").getSingleResult();
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


    /**<code>select o from GroupCountriesEntity o where IoI.IcInItIrIyIgIrIpICIoIdIeI=I:IcInItIrIyIgIrIpICIoIdIeI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<IBasicDTO> getCountries(Long groupCode) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GroupCountriesEntity> list =
                EM().createNamedQuery("GroupCountriesEntity.getCountriesByGroup").setParameter("cntrygrpCode",
                                                                                               groupCode).getResultList();
            for (GroupCountriesEntity countries : list) {
                arrayList.add(InfDTOFactory.createGroupCountriesDTO(countries));
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
     * list available countries for group * @param catCode
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> getAvailableCountries(Long catCode) throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("GroupCountriesEntity.getAvailableCountries").setParameter("catCode",
                                                                                                    catCode).getResultList();
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
     * search available countries for group * @param catCode
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> searchAvailableCountriesByCode(Long catCode, Long countryCode) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            return EM().createNamedQuery("GroupCountriesEntity.searchAvailableCountriesByCode").setParameter("catCode",
                                                                                                             catCode).setParameter("countryCode",
                                                                                                                                   countryCode).getResultList();
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
     * search available countries for group * @param catCode
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> searchAvailableCountriesByName(Long catCode, String countryName) throws DataBaseException,
                                                                                                   SharedApplicationException {
        try {
            return EM().createNamedQuery("GroupCountriesEntity.searchAvailableCountriesByName").setParameter("catCode",
                                                                                                             catCode).setParameter("countryName",
                                                                                                                                   countryName).getResultList();
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
     * search group countries by group code country code * @param groupCode
     * @param countryCode
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> searchByCode(Long groupCode, Long countryCode) throws DataBaseException,
                                                                                 SharedApplicationException {
        List<GroupCountriesEntity> list =
            EM().createNamedQuery("GroupCountriesEntity.searchByCode").setParameter("groupCode",
                                                                                    groupCode).setParameter("countryCode",
                                                                                                            countryCode).getResultList();
        //SystEM().out.println("GroupCountriesFacadeBean searchByCode() countryCode = "+countryCode+" , groupCode = "+groupCode);
        List<IBasicDTO> searchByCodeResult = new ArrayList<IBasicDTO>();
        for (GroupCountriesEntity entity : list) {
            searchByCodeResult.add(InfDTOFactory.createGroupCountriesDTO(entity));
        }
        if (searchByCodeResult.size() == 0)
            throw new NoResultException();
        return searchByCodeResult;
    }

    /**
     * search group countries by group code country name * @param groupCode
     * @param countryName
     * @return list
     * @throws RemoteException
     * @author Aly Noor @since 06/26/2014 eidted to use generic method InfBaseFacadeBean.searchByName
     */
    public List<IBasicDTO> searchByName(Long groupCode, String countryName) throws DataBaseException,
                                                                                   SharedApplicationException {
        List<GroupCountriesEntity> list = null;
        try {
            Query updateQuery =
                EM().createNamedQuery("GroupCountriesEntity.searchByName").setParameter("groupCode", groupCode).setParameter("countryName",
                                                                                                                             "%" +
                                                                                                                             countryName +
                                                                                                                             "%");
            list = updateQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<IBasicDTO> searchByNameResult = new ArrayList<IBasicDTO>();

        for (BasicEntity obj : list) {
            GroupCountriesEntity entity = (GroupCountriesEntity)obj;
            searchByNameResult.add(InfDTOFactory.createGroupCountriesDTO(entity));
        }
        if (searchByNameResult.size() == 0)
            throw new NoResultException();
        return searchByNameResult;
    }
}
