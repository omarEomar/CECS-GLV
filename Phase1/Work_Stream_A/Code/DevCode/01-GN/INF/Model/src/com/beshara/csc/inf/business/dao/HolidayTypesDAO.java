package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IHolidayTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.HolidayTypesEntity;
import com.beshara.csc.inf.business.entity.HolidaysEntity;
import com.beshara.csc.inf.business.entity.IHolidayTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class HolidayTypesDAO extends InfBaseDAO {
    public HolidayTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new HolidayTypesDAO();
    }

    public List<IHolidayTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<HolidayTypesEntity> list =
                EM().createNamedQuery("HolidayTypesEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (HolidayTypesEntity holidayTypes : list) {
                arrayList.add(InfDTOFactory.createHolidayTypesDTO(holidayTypes));
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
     * Create a New HolidayTypesEntity * @param holidayTypesDTO
     * @return IHolidayTypesDTO
     */
    public IHolidayTypesDTO add(IBasicDTO holidayTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            HolidayTypesEntity holidayTypesEntity = new HolidayTypesEntity();
            IHolidayTypesDTO holidayTypesDTO = (IHolidayTypesDTO)holidayTypesDTO1;
            holidayTypesDTO.setCode(InfEntityKeyFactory.createHolidayTypesEntityKey(findNewId()));
            holidayTypesEntity.setHoltypeCode(((IHolidayTypesEntityKey)holidayTypesDTO.getCode()).getHoltypeCode());
            holidayTypesEntity.setHoltypeName(holidayTypesDTO.getName());
            holidayTypesEntity.setHoltypeDays(holidayTypesDTO.getHoltypeDays());
            holidayTypesEntity.setCreatedBy(holidayTypesDTO.getCreatedBy());
            holidayTypesEntity.setCreatedDate(holidayTypesDTO.getCreatedDate());
            holidayTypesEntity.setAuditStatus(holidayTypesDTO.getAuditStatus());
            holidayTypesEntity.setTabrecSerial(holidayTypesDTO.getTabrecSerial());
            doAdd(holidayTypesEntity);
            // Set PK after creation
            return holidayTypesDTO;
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
     * Update an Existing HolidayTypesEntity * @param holidayTypesDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO holidayTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IHolidayTypesDTO holidayTypesDTO = (IHolidayTypesDTO)holidayTypesDTO1;
            HolidayTypesEntity holidayTypesEntity = EM().find(HolidayTypesEntity.class, holidayTypesDTO.getCode());
            holidayTypesEntity.setHoltypeCode(((IHolidayTypesEntityKey)holidayTypesDTO.getCode()).getHoltypeCode());
            holidayTypesEntity.setHoltypeName(holidayTypesDTO.getName());
            holidayTypesEntity.setHoltypeDays(holidayTypesDTO.getHoltypeDays());
            holidayTypesEntity.setCreatedBy(holidayTypesDTO.getCreatedBy());
            holidayTypesEntity.setCreatedDate(holidayTypesDTO.getCreatedDate());
            holidayTypesEntity.setLastUpdatedBy(holidayTypesDTO.getLastUpdatedBy());
            holidayTypesEntity.setLastUpdatedDate(holidayTypesDTO.getLastUpdatedDate());
            holidayTypesEntity.setAuditStatus(holidayTypesDTO.getAuditStatus());
            holidayTypesEntity.setTabrecSerial(holidayTypesDTO.getTabrecSerial());
            doUpdate(holidayTypesEntity);
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
     * Remove an Existing HolidayTypesEntity * @param holidayTypesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO holidayTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IHolidayTypesDTO holidayTypesDTO = (IHolidayTypesDTO)holidayTypesDTO1;
            HolidayTypesEntity holidayTypesEntity = EM().find(HolidayTypesEntity.class, holidayTypesDTO.getCode());
            if (holidayTypesEntity == null) {
                throw new NoResultException();
            }
            doRemove(holidayTypesEntity);
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
     * Get HolidayTypesEntity By Primary Key * @param id
     * @return IHolidayTypesDTO
     */
    public IHolidayTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IHolidayTypesEntityKey id = (IHolidayTypesEntityKey)id1;
            HolidayTypesEntity holidayTypesEntity = EM().find(HolidayTypesEntity.class, id);
            if (holidayTypesEntity == null) {
                throw new NoResultException();
            }
            IHolidayTypesDTO holidayTypesDTO = InfDTOFactory.createHolidayTypesDTO(holidayTypesEntity);
            List<HolidaysEntity> list = holidayTypesEntity.getHolidaysEntityList();
            ArrayList arrayList = new ArrayList();
            for (HolidaysEntity holidaysEntity : list) {
                arrayList.add(InfDTOFactory.createHolidaysDTO(holidaysEntity));
            }
            holidayTypesDTO.setHolidaysDTOList(arrayList);
            return holidayTypesDTO;
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
            Long id = (Long)EM().createNamedQuery("HolidayTypesEntity.findNewId").getSingleResult();
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

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("HolidayTypesEntity.getCodeName").getResultList();
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
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<HolidayTypesEntity> list =
                EM().createNamedQuery("HolidayTypesEntity.searchByCode").setParameter("holtypeCode",
                                                                                      (Long)code).getResultList();
            for (HolidayTypesEntity entity : list) {
                arrayList.add(InfDTOFactory.createHolidayTypesDTO(entity));
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

    public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
        
        try {
            List list =
                EM().createNamedQuery("HolidayTypesEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createHolidayTypesDTO((HolidayTypesEntity)list.get(0)));
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

    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            StringBuilder ejbQL =
                new StringBuilder("select o from  HolidayTypesEntity o where  o.holtypeName like '%");
            ejbQL.append(name);
            ejbQL.append("%'  and o.holtypeCode NOT IN (11) and o.realFlag=1 order by o.holtypeName");
            List<HolidayTypesEntity> list =
                EM().createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (HolidayTypesEntity entity : list) {
                arrayList.add(InfDTOFactory.createHolidayTypesDTO(entity));
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
    
   
    public List<IBasicDTO> getAllActiveHolidayTypes() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("HolidayTypesEntity.getCodeNameForActiveHoliday").getResultList();
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
