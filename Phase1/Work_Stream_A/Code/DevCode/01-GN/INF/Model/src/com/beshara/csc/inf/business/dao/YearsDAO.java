package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.inf.business.dto.IYearsSearchDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IGenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.IYearsEntityKey;
import com.beshara.csc.inf.business.entity.YearsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class YearsDAO extends InfBaseDAO {
    public YearsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new YearsDAO();
    }

    public List<IYearsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<YearsEntity> list =
                EM().createNamedQuery("YearsEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (YearsEntity year : list) {
                arrayList.add(InfDTOFactory.createYearsDTO(year));
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
            Query query = EM().createNamedQuery("YearsEntity.findNewId");
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

    public IYearsDTO add(IBasicDTO yearDTO1) throws DataBaseException, SharedApplicationException {
        try {
            YearsEntity yearsEntity = new YearsEntity();
            IYearsDTO yearDTO = (IYearsDTO)yearDTO1;
            yearsEntity.setYearCode(((IYearsEntityKey)yearDTO.getCode()).getYearCode());
            yearsEntity.setYearName(yearDTO.getYearName());
            yearsEntity.setStartDate(yearDTO.getStartDate());
            yearsEntity.setEndDate(yearDTO.getEndDate());
            yearsEntity.setBudgetStartDate(yearDTO.getBudgetStartDate());
            yearsEntity.setBudgetEndDate(yearDTO.getBudgetEndDate());
            doAdd(yearsEntity);
            // Set PK after creation
            return yearDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO yearDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IYearsDTO yearDTO = (IYearsDTO)yearDTO1;
            YearsEntity yearsEntity = EM().find(YearsEntity.class, (IYearsEntityKey)yearDTO.getCode());
            yearsEntity.setYearCode(((IYearsEntityKey)yearDTO.getCode()).getYearCode());
            yearsEntity.setYearName(yearDTO.getYearName());
            yearsEntity.setStartDate(yearDTO.getStartDate());
            yearsEntity.setEndDate(yearDTO.getEndDate());
            yearsEntity.setBudgetStartDate(yearDTO.getBudgetStartDate());
            yearsEntity.setBudgetEndDate(yearDTO.getBudgetEndDate());
            yearsEntity.setCreatedBy(yearDTO.getCreatedBy());
            yearsEntity.setCreatedDate(yearDTO.getCreatedDate());
            yearsEntity.setLastUpdatedBy(yearDTO.getLastUpdatedBy());
            yearsEntity.setLastUpdatedDate(yearDTO.getLastUpdatedDate());
            yearsEntity.setAuditStatus(yearDTO.getAuditStatus());
            yearsEntity.setTabrecSerial(yearDTO.getTabrecSerial());
            doUpdate(yearsEntity);
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

    public Boolean remove(IBasicDTO yearDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IYearsDTO yearDTO = (IYearsDTO)yearDTO1;
            YearsEntity yearsEntity = EM().find(YearsEntity.class, (IGenderTypesEntityKey)yearDTO.getCode());
            if (yearDTO == null) {
                throw new FinderException();
            }
            doRemove(yearsEntity);
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

    public IYearsDTO getById(IEntityKey entityKey) throws DataBaseException, SharedApplicationException {
        try {
            IYearsEntityKey id = (IYearsEntityKey)entityKey;
            YearsEntity yearsEntity = EM().find(YearsEntity.class, id);
            if (yearsEntity == null) {
                throw new NoResultException();
            }
            IYearsDTO yearsDTO = InfDTOFactory.createYearsDTO(yearsEntity);
            return yearsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    public IYearsDTO getByIdSimple(IEntityKey entityKey) throws DataBaseException, SharedApplicationException {
        try {
            IYearsEntityKey id = (IYearsEntityKey)entityKey;
            YearsEntity yearsEntity = EM().find(YearsEntity.class, id);
            if (yearsEntity == null) {
                throw new NoResultException();
            }
            IYearsDTO yearsDTO = InfDTOFactory.createYearsDTO(yearsEntity ,true);
            return yearsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        //        return super.search(basicDTO);
        try {
            IYearsSearchDTO dto = (IYearsSearchDTO)basicDTO;
            StringBuffer filterQuery = new StringBuffer("select o from YearsEntity o where o.yearCode = o.yearCode");
            if (dto.getYearCode() != null) {
                filterQuery.append(" and o.yearCode = " + dto.getYearCode());
            }
            if (dto.getYearName() != null && !dto.getYearName().equals("")) {
                filterQuery.append("  and o.yearName like '" + dto.getYearName() + "'");
            }
            if (dto.getStartDate() != null && dto.getEndDate() != null) {
                filterQuery.append(" and ((o.startDate >= '" + dto.getStartDate() +
                                   "' and o.endDate is null) or ( o.endDate  <= '" + dto.getEndDate() +
                                   "' and o.startDate is null ) or (o.endDate <= '" + dto.getEndDate() +
                                   "' and o.startDate >='" + dto.getStartDate() + "' )) ");
            } else if (dto.getStartDate() != null) {
                filterQuery.append(" and o.startDate >= '" + dto.getStartDate() + "'");
            } else if (dto.getEndDate() != null) {
                filterQuery.append(" and o.endDate <= '" + dto.getEndDate() + "'");
            }
            System.out.println(filterQuery);
            Query query = EM().createQuery(filterQuery.toString());
            System.out.println(query);
            List<YearsEntity> enlist = query.getResultList();
            if (enlist == null || enlist.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            for (YearsEntity entity : enlist) {
                list.add(InfDTOFactory.createYearsDTO(entity));
            }

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

    /**
     * Get list of code and name * return list of IYearsDTO initialize with code and name only * @return List
     * @throws RemoteException
     */
    public List<IYearsDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("YearsEntity.getCodeName").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IYearsDTO> getCodeNameUntil(Long yearCode) throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("YearsEntity.getCodeNameUntil").setParameter("yearCode",
                                                                                      yearCode).getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getYearByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<YearsEntity> list =
                EM().createNamedQuery("YearsEntity.getYearByName").setParameter("name", name).getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            for (YearsEntity entity : list) {
                arrayList.add(InfDTOFactory.createYearsDTO(entity));
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

    public Long getYearCountByName(String name) throws DataBaseException, SharedApplicationException {
        Long count = 0L;
        try {
            count =
                    (Long)EM().createNamedQuery("YearsEntity.getYearCountByName").setParameter("yearName", name).getSingleResult();

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return count;
    }
    
    public List<IBasicDTO> getAllExcludedList(List<String> excludedList) throws DataBaseException, SharedApplicationException {
        
        try {
            /*
             * get List
             */
            StringBuilder appendedYears=new StringBuilder("");
            int index=1;
            for (String year:excludedList) {
                appendedYears.append(year);
                if(index!=excludedList.size()){
                    appendedYears.append(",");
                }
                index++;
            }
            
            StringBuilder filterQuery = new StringBuilder("select o from YearsEntity o ");
            if(!appendedYears.toString().equals("")){
                filterQuery.append("where o.yearCode not in (");
                filterQuery.append(appendedYears);
                filterQuery.append(")");
            }
            System.out.println(filterQuery);
            Query query = EM().createQuery(filterQuery.toString());
            System.out.println(query);
            List<YearsEntity> enlist = query.getResultList();
            if (enlist == null || enlist.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            for (YearsEntity entity : enlist) {
                list.add(InfEntityConverter.getYearsDTO(entity));
            }

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
    
    /**
     * Get list of code and name greeter than specific date 
     * @return List
     * @author I.Omar
     */
    public List<IYearsDTO> getCodeNameAfterDate(Timestamp date) throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("YearsEntity.getCodeNameAfterDate").setParameter("date", date).getResultList();
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
