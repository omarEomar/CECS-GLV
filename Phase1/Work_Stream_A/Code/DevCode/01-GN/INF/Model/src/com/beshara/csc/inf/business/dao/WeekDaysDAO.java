package com.beshara.csc.inf.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IWeekDaysDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IWeekDaysEntityKey;
import com.beshara.csc.inf.business.entity.WeekDaysEntity;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;

public class WeekDaysDAO extends InfBaseDAO {
    public WeekDaysDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new WeekDaysDAO();
    }

    /**<code>select o from WeekDaysEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<IWeekDaysDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<WeekDaysEntity> list =
                EM().createNamedQuery("WeekDaysEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (WeekDaysEntity weekDays : list) {
                arrayList.add(InfDTOFactory.createWeekDaysDTO(weekDays));
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
            Query query = EM().createNamedQuery("WeekDaysEntity.findNewId");
            Long id = (Long)query.getSingleResult();
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
     * Create a New WeekDaysEntity * @param weekDaysDTO
     * @return IWeekDaysDTO
     */
    public IWeekDaysDTO add(IBasicDTO weekDaysDTO1) throws DataBaseException, SharedApplicationException {

        try {
            WeekDaysEntity weekDaysEntity = new WeekDaysEntity();
            IWeekDaysDTO weekDaysDTO = (IWeekDaysDTO)weekDaysDTO1;
            weekDaysEntity.setDaynum(findNewId());
            weekDaysEntity.setDayName(weekDaysDTO.getName());
            weekDaysEntity.setCreatedBy(weekDaysDTO.getCreatedBy());
            weekDaysEntity.setCreatedDate(weekDaysDTO.getCreatedDate());
            weekDaysEntity.setAuditStatus(weekDaysDTO.getAuditStatus());
            weekDaysEntity.setTabrecSerial(weekDaysDTO.getTabrecSerial());
            doAdd(weekDaysEntity);
            // Set PK after creation
            return weekDaysDTO;

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
     * Update an Existing WeekDaysEntity * @param weekDaysDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO weekDaysDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IWeekDaysDTO weekDaysDTO = (IWeekDaysDTO)weekDaysDTO1;
            WeekDaysEntity weekDaysEntity = EM().find(WeekDaysEntity.class, (IWeekDaysEntityKey)weekDaysDTO.getCode());
            weekDaysEntity.setDaynum(((IWeekDaysEntityKey)weekDaysDTO.getCode()).getDaynum());
            weekDaysEntity.setDayName(weekDaysDTO.getName());
            weekDaysEntity.setCreatedBy(weekDaysDTO.getCreatedBy());
            weekDaysEntity.setCreatedDate(weekDaysDTO.getCreatedDate());
            weekDaysEntity.setLastUpdatedBy(weekDaysDTO.getLastUpdatedBy());
            weekDaysEntity.setLastUpdatedDate(weekDaysDTO.getLastUpdatedDate());
            weekDaysEntity.setAuditStatus(weekDaysDTO.getAuditStatus());
            weekDaysEntity.setTabrecSerial(weekDaysDTO.getTabrecSerial());
            doUpdate(weekDaysEntity);
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
     * Remove an Existing WeekDaysEntity * @param weekDaysDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO weekDaysDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IWeekDaysDTO weekDaysDTO = (IWeekDaysDTO)weekDaysDTO1;
            WeekDaysEntity weekDaysEntity = EM().find(WeekDaysEntity.class, (IWeekDaysEntityKey)weekDaysDTO.getCode());
            if (weekDaysEntity == null) {
                throw new FinderException();
            }
            doRemove(weekDaysEntity);
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
     * Get WeekDaysEntity By Primary Key * @param id
     * @return IWeekDaysDTO
     */
    public IWeekDaysDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IWeekDaysEntityKey id = (IWeekDaysEntityKey)id1;
            WeekDaysEntity weekDaysEntity = EM().find(WeekDaysEntity.class, id);
            if (weekDaysEntity == null) {
                throw new NoResultException();
            }
            IWeekDaysDTO weekDaysDTO = InfDTOFactory.createWeekDaysDTO(weekDaysEntity);
            return weekDaysDTO;
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
     * Search for WeekDaysEntity * <br> * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            return super.search(basicDTO);
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
     * get all week days code and name * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("WeekDaysEntity.getCodeName").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    // search(code , name ) mothods added by A.Elmasry

    /**
     * Get all by code equal code * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<WeekDaysEntity> list =
                EM().createNamedQuery("WeekDaysEntity.searchByCode").setParameter("WeekDaysCode",
                                                                                  (Long)code).getResultList();
            for (WeekDaysEntity entity : list) {
                arrayList.add(InfDTOFactory.createWeekDaysDTO(entity));
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
     * Get all by name like string * @param name
     * @return list
     * @throws RemoteException
     * @throws FinderException
     * @EditedBy @author Aly Noor @since 06/26/2014 eidted to use generic method InfBaseFacadeBean.searchByName
     */
    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        //List<WeekDaysEntity> list =
        //  em.createNamedQuery("WeekDaysEntity.searchByName").setParameter("WeekDaysName", searchName).getResultList();
        //
        List<BasicEntity> list = searchByName("WeekDaysEntity", "dayName", searchName);
        //em.createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
        if (list != null) {
            for (BasicEntity obj : list) {
                WeekDaysEntity entity = (WeekDaysEntity)obj;
                //for (WeekDaysEntity entity : list) {
                arrayList.add(InfDTOFactory.createWeekDaysDTO(entity));
            }
        }
        if (arrayList.size() == 0)
            throw new NoResultException();
        return arrayList;
    }

    /**
     * <b>Description:</b>
     * <br>&nbsp;&nbsp;&nbsp;
     * This Method searchByName is generic method for standard search by name, to search for value of searchName
     * on column whose name colName on the Entity whose name entityName returns result as list of BasicEntity
     * <br><br>
     * @param entityName
     * @param colName
     * @param searchName
     * @return List<BasicEntity>
     * @author   Aly Noor
     * @since    06/25/2014
     */
    public List<BasicEntity> searchByName(String entityName, String colName,
                                          String searchName) throws DataBaseException, SharedApplicationException {
        //List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
        //List<CountriesEntity> list =
        //   em.createNamedQuery("CountriesEntity.searchByName").setParameter("cntryName", cntryName).getResultList();
        try {

            StringBuilder ejbQL = new StringBuilder("select o from ");
            ejbQL.append(entityName);
            ejbQL.append(" o where ( ");
            ejbQL.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o." + colName, searchName));
            ejbQL.append(" ) order by o.");
            ejbQL.append(colName);
            List<BasicEntity> list =
                EM().createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();

            if (list == null || list.size() == 0)
                throw new NoResultException();
            return list;

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
