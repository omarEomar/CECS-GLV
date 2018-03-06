package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.ICountryGroupsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.CountryGroupsEntity;
import com.beshara.csc.inf.business.entity.CountryGroupsEntityKey;
import com.beshara.csc.inf.business.entity.ICountryGroupsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class CountryGroupsDAO extends InfBaseDAO {
    public CountryGroupsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new CountryGroupsDAO();
    }

    /**<code>select o from CountryGroupsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<ICountryGroupsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<CountryGroupsEntity> list =
                EM().createNamedQuery("CountryGroupsEntity.findAll").setHint("toplink.refresh",
                                                                             "true").getResultList();
            for (CountryGroupsEntity countryGroups : list) {
                arrayList.add(InfDTOFactory.createCountryGroupsDTO(countryGroups));
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
            Long id = (Long)EM().createNamedQuery("CountryGroupsEntity.findNewId").getSingleResult();
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
     * Create a New CountryGroupsEntity * @param countryGroupsDTO1
     * @return ICountryGroupsDTO
     */
    @Override
    public ICountryGroupsDTO add(IBasicDTO countryGroupsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            CountryGroupsEntity countryGroupsEntity = new CountryGroupsEntity();
            Long code = findNewId();
            ICountryGroupsDTO countryGroupsDTO = (ICountryGroupsDTO)countryGroupsDTO1;
            countryGroupsEntity.setCntrygrpCode(code);
            countryGroupsEntity.setCntrygrpName(countryGroupsDTO.getName());
            countryGroupsEntity.setParentCntrygrp(countryGroupsDTO.getParentCntrygrp());
            if (countryGroupsDTO.getCountryGroupsDTO() != null) {
                CountryGroupsEntity parentCountryGroupsEntity =
                    EM().find(CountryGroupsEntity.class, countryGroupsDTO.getCountryGroupsDTO().getCode());
                if (parentCountryGroupsEntity != null) {
                    countryGroupsEntity.setCountryGroupsEntity(parentCountryGroupsEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }

            doAdd(countryGroupsEntity);
            countryGroupsDTO.setCntrygrpCode(code);
            countryGroupsDTO.setCode(new CountryGroupsEntityKey(code));
            return countryGroupsDTO;
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
     * Update an Existing CountryGroupsEntity * @param countryGroupsDTO1
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO countryGroupsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ICountryGroupsDTO countryGroupsDTO = (ICountryGroupsDTO)countryGroupsDTO1;
            CountryGroupsEntity countryGroupsEntity =
                EM().find(CountryGroupsEntity.class, (ICountryGroupsEntityKey)countryGroupsDTO.getCode());
            countryGroupsEntity.setCntrygrpCode(((ICountryGroupsEntityKey)countryGroupsDTO.getCode()).getCntrygrpCode());
            countryGroupsEntity.setCntrygrpName(countryGroupsDTO.getName());
            countryGroupsEntity.setParentCntrygrp(countryGroupsDTO.getParentCntrygrp());
            if (countryGroupsDTO.getCountryGroupsDTO() != null) {
                CountryGroupsEntity parentCountryGroupsEntity =
                    EM().find(CountryGroupsEntity.class, (ICountryGroupsEntityKey)countryGroupsDTO.getCountryGroupsDTO().getCode());
                if (parentCountryGroupsEntity != null) {
                    countryGroupsEntity.setCountryGroupsEntity(parentCountryGroupsEntity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            doUpdate(countryGroupsEntity);
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
     * Remove an Existing CountryGroupsEntity * @param countryGroupsDTO1
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO countryGroupsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ICountryGroupsDTO countryGroupsDTO = (ICountryGroupsDTO)countryGroupsDTO1;
            CountryGroupsEntity countryGroupsEntity = EM().find(CountryGroupsEntity.class, countryGroupsDTO.getCode());
            if (countryGroupsEntity == null) {
                throw new FinderException();
            }
            doRemove(countryGroupsEntity);
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
     * Get CountryGroupsEntity By Primary Key * @param id1
     * @return ICountryGroupsDTO
     */
    @Override
    public ICountryGroupsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            ICountryGroupsEntityKey id = (ICountryGroupsEntityKey)id1;
            CountryGroupsEntity countryGroupsEntity = EM().find(CountryGroupsEntity.class, id);
            if (countryGroupsEntity == null) {
                throw new ItemNotFoundException();
            }
            ICountryGroupsDTO countryGroupsDTO = InfDTOFactory.createCountryGroupsDTO(countryGroupsEntity);
            return countryGroupsDTO;
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
     * Search for CountryGroupsEntity * <br> * @return List
     */
    @Override
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }

    /**
     * List all cats * @param
     * @return List of ICountryGroupsDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> getCats() throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<CountryGroupsEntity> list =
                EM().createNamedQuery("CountryGroupsEntity.getCats").setHint("toplink.refresh",
                                                                             "true").getResultList();
            for (CountryGroupsEntity entity : list) {
                arrayList.add(InfDTOFactory.createCountryGroupsDTO(entity));
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
     * List all groups by cat code * @param
     * @return List of ICountryGroupsDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> getGroups(Long catCode) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<CountryGroupsEntity> list =
                EM().createNamedQuery("CountryGroupsEntity.getGroups").setParameter("catCode",
                                                                                    catCode).setHint("toplink.refresh",
                                                                                                     "true").getResultList();
            for (CountryGroupsEntity entity : list) {
                arrayList.add(InfDTOFactory.createCountryGroupsDTO(entity));
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
    public List<IBasicDTO> searchByCode(Object grpCode) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<CountryGroupsEntity> list =
                EM().createNamedQuery("CountryGroupsEntity.searchCatsByCode").setParameter("catCode",
                                                                                           grpCode).getResultList();

            for (CountryGroupsEntity entity : list) {
                arrayList.add(InfDTOFactory.createCountryGroupsDTO(entity));
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

   

    public List<IBasicDTO> searchByName(String grpName) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<CountryGroupsEntity> list =
                EM().createNamedQuery("CountryGroupsEntity.searchCatsByName").setParameter("catName",
                                                                                           "%" + grpName + "%").getResultList();
            if (list != null) {
                for (BasicEntity obj : list) {
                    CountryGroupsEntity entity = (CountryGroupsEntity)obj;
                    arrayList.add(InfDTOFactory.createCountryGroupsDTO(entity));
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

  

    public List<IBasicDTO> searchGrpByCode(Long grpCode, Long catCode) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<CountryGroupsEntity> list =
                EM().createNamedQuery("CountryGroupsEntity.searchGroupsByCode").setParameter("gpCode",
                                                                                             grpCode).setParameter("catCode",
                                                                                                                   catCode).getResultList();

            for (CountryGroupsEntity entity : list) {
                arrayList.add(InfDTOFactory.createCountryGroupsDTO(entity));
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

  

    public List<IBasicDTO> searchGrpByName(Long catCode, String catName) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<CountryGroupsEntity> list =
                EM().createNamedQuery("CountryGroupsEntity.searchGroupsByName").setParameter("catCode",
                                                                                             catCode).setParameter("catName",
                                                                                                                   "%" +
                                                                                                                   catName +
                                                                                                                   "%").getResultList();
            for (CountryGroupsEntity entity : list) {
                arrayList.add(InfDTOFactory.createCountryGroupsDTO(entity));
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
                EM().createNamedQuery("CountryGroupsEntity.getByNameForRoot").setParameter("name", name).setHint("toplink.refresh",
                                                                                                                 "true").getResultList();
            if (list.size() > 0) {
                return InfDTOFactory.createCountryGroupsDTO((CountryGroupsEntity)list.get(0));
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

    public IBasicDTO getByNameForChildren(String name, Long parentCntrygrp) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            List list =
                EM().createNamedQuery("CountryGroupsEntity.getByNameForChildren").setParameter("name", name).setParameter("parentCntrygrp",
                                                                                                                          parentCntrygrp).setHint("toplink.refresh",
                                                                                                                                                  "true").getResultList();
            if (list.size() > 0) {
                return InfDTOFactory.createCountryGroupsDTO((CountryGroupsEntity)list.get(0));
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
