package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.HolidayTypesDTO;
import com.beshara.csc.inf.business.dto.IHolidaysDTO;
import com.beshara.csc.inf.business.dto.IHolidaysSearchDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.holidays.IHolidaysSimpleDTO;
import com.beshara.csc.inf.business.entity.HolidayTypesEntity;
import com.beshara.csc.inf.business.entity.HolidaysEntity;
import com.beshara.csc.inf.business.entity.IHolidayTypesEntityKey;
import com.beshara.csc.inf.business.entity.IHolidaysEntityKey;
import com.beshara.csc.inf.business.entity.IYearsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.YearsEntity;
import com.beshara.csc.sharedutils.business.exception.BatchNotCompletedException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.rmi.RemoteException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class HolidaysDAO extends InfBaseDAO {
    public HolidaysDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new HolidaysDAO();
    }

    public List<IHolidaysDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<HolidaysEntity> list =
                EM().createNamedQuery("HolidaysEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (HolidaysEntity holidays : list) {
                arrayList.add(InfDTOFactory.createHolidaysDTO(holidays));
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
     * Create a New HolidaysEntity * @param holidaysDTO
     * @return IHolidaysDTO
     */
    public IHolidaysDTO add(IBasicDTO holidaysDTO1) throws DataBaseException, SharedApplicationException {
        try {
            HolidaysEntity holidaysEntity = new HolidaysEntity();
            IHolidaysDTO holidaysDTO = (IHolidaysDTO)holidaysDTO1;
            if (holidaysDTO.getYearsDTO() != null) {
                YearsEntity yearsEntity = EM().find(YearsEntity.class, holidaysDTO.getYearsDTO().getCode());
                if (yearsEntity == null)
                    throw new ItemNotFoundException();
                holidaysEntity.setYearsEntity(yearsEntity);
            } else {
                throw new ItemNotFoundException();
            }
            if (holidaysDTO.getHolidayTypesDTO() != null) {
                HolidayTypesEntity holidayTypesEntity =
                    EM().find(HolidayTypesEntity.class, holidaysDTO.getHolidayTypesDTO().getCode());
                if (holidayTypesEntity == null)
                    throw new ItemNotFoundException();
                holidaysEntity.setHolidayTypesEntity(holidayTypesEntity);
            } else {
                throw new ItemNotFoundException();
            }
            holidaysEntity.setFromDate(holidaysDTO.getFromDate());
            holidaysEntity.setUntilDate(holidaysDTO.getUntilDate());
            holidaysEntity.setStatus(holidaysDTO.getStatus());
            holidaysEntity.setCreatedBy(holidaysDTO.getCreatedBy());
            holidaysEntity.setCreatedDate(holidaysDTO.getCreatedDate());
            holidaysEntity.setAuditStatus(holidaysDTO.getAuditStatus());
            holidaysEntity.setTabrecSerial(holidaysDTO.getTabrecSerial());
            doAdd(holidaysEntity);
            holidaysDTO.setCode(InfEntityKeyFactory.createHolidaysEntityKey(holidaysEntity.getYearsEntity().getYearCode(),
                                                                            holidaysEntity.getHolidayTypesEntity().getHoltypeCode() , holidaysEntity.getFromDate()));
            // Set PK after creation
            return holidaysDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    public IHolidaysSimpleDTO addHolidays(IBasicDTO holidaysSimpleDTO1) throws DataBaseException, SharedApplicationException {
        try {
            HolidaysEntity holidaysEntity = new HolidaysEntity();
            IHolidaysSimpleDTO holidaysSimpleDTO = (IHolidaysSimpleDTO)holidaysSimpleDTO1;
            if (holidaysSimpleDTO.getYearCode() != null) {
                IYearsEntityKey yearEk = InfEntityKeyFactory.createYearsEntityKey(holidaysSimpleDTO.getYearCode());
                YearsEntity yearsEntity = EM().find(YearsEntity.class, yearEk);
                if (yearsEntity == null)
                    throw new ItemNotFoundException();
                holidaysEntity.setYearsEntity(yearsEntity);
            } else {
                throw new ItemNotFoundException();
            }
            
            if (holidaysSimpleDTO.getHoltypeCode() != null) {
            IHolidayTypesEntityKey ek = InfEntityKeyFactory.createHolidayTypesEntityKey(holidaysSimpleDTO.getHoltypeCode());
                HolidayTypesEntity holidayTypesEntity =
                    EM().find(HolidayTypesEntity.class, ek);
                if (holidayTypesEntity == null)
                    throw new ItemNotFoundException();
                holidaysEntity.setHolidayTypesEntity(holidayTypesEntity);
            } else {
                throw new ItemNotFoundException();
            }
            holidaysEntity.setFromDate(holidaysSimpleDTO.getFromDate());
            holidaysEntity.setUntilDate(holidaysSimpleDTO.getUntilDate());
            holidaysEntity.setStatus(holidaysSimpleDTO.getStatus());
            holidaysEntity.setHolidayDescribtion(holidaysSimpleDTO.getHolidayDesc());
            doAdd(holidaysEntity);
            holidaysSimpleDTO.setCode(InfEntityKeyFactory.createHolidaysEntityKey(holidaysSimpleDTO.getYearCode(),
                                                                            holidaysSimpleDTO.getHoltypeCode() , holidaysEntity.getFromDate()));
            // Set PK after creation
            return holidaysSimpleDTO;
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
     * Update an Existing HolidaysEntity * @param holidaysDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO holidaysDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IHolidaysDTO holidaysDTO = (IHolidaysDTO)holidaysDTO1;
            HolidaysEntity holidaysEntity = EM().find(HolidaysEntity.class, holidaysDTO.getCode());
            holidaysEntity.setFromDate(holidaysDTO.getFromDate());
            holidaysEntity.setUntilDate(holidaysDTO.getUntilDate());
            holidaysEntity.setStatus(holidaysDTO.getStatus());
            holidaysEntity.setCreatedBy(holidaysDTO.getCreatedBy());
            holidaysEntity.setCreatedDate(holidaysDTO.getCreatedDate());
            holidaysEntity.setLastUpdatedBy(holidaysDTO.getLastUpdatedBy());
            holidaysEntity.setLastUpdatedDate(holidaysDTO.getLastUpdatedDate());
            holidaysEntity.setAuditStatus(holidaysDTO.getAuditStatus());
            holidaysEntity.setTabrecSerial(holidaysDTO.getTabrecSerial());
            holidaysEntity.setHolidayDescribtion(holidaysDTO.getHolidayDesc());
            doUpdate(holidaysEntity);
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
    
    public Boolean updateSimpleHoliday(IBasicDTO holidaysDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IHolidaysSimpleDTO holidaysDTO = (IHolidaysSimpleDTO)holidaysDTO1;
            HolidaysEntity holidaysEntity = EM().find(HolidaysEntity.class, holidaysDTO.getCode());
            holidaysEntity.setUntilDate(holidaysDTO.getUntilDate());
            holidaysEntity.setStatus(holidaysDTO.getStatus());
            holidaysEntity.setHolidayDescribtion(holidaysDTO.getHolidayDesc());
            doUpdate(holidaysEntity);
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
    
    public boolean updateHoliday(IBasicDTO holidaysDTO1 , Date oldFromDate) throws DataBaseException,  SharedApplicationException {
        IHolidaysDTO hDTO = (IHolidaysDTO)holidaysDTO1;
        boolean status = false;
        ResultSet rs = null;
        PreparedStatement stmt=null;
           try {
               SimpleDateFormat dmyFormat = new SimpleDateFormat("dd/MM/yyyy");
               
                 stmt = getConnection().prepareStatement("update INF_HOLIDAYS set FROM_DATE =TO_DATE('"+dmyFormat.format(hDTO.getFromDate())+"','DD/MM/YYYY'), UNTIL_DATE = TO_DATE('"+dmyFormat.format(hDTO.getUntilDate())+"','DD/MM/YYYY') ,STATUS = "+hDTO.getStatus()+ "  where YEAR_CODE ="+hDTO.getYearsDTO().getCode().getKey()+"  and  HOLTYPE_CODE ='"+hDTO.getHolidayTypesDTO().getCode().getKey()+"' and FROM_DATE = TO_DATE('"+dmyFormat.format(oldFromDate)+"','DD/MM/YYYY')");

                 System.out.println("===executeBatch==="+stmt.toString());
               stmt.executeUpdate();
               status = true;
               
            
           } catch (SQLException e) {
               e.printStackTrace();
               throw new BatchNotCompletedException();
           }finally{
               close(stmt);
           }
          return status;
    }


    /**
     * Remove an Existing HolidaysEntity * @param holidaysDTO
     * @return Boolean/D:/CSC01/Phase1/Work_Stream_A/Code/DevCode/01-GN/INF/Model/src/com/beshara/csc/inf/presentation/pages/index.jsp
     */
    public Boolean remove(IBasicDTO holidaysDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IHolidaysDTO holidaysDTO = (IHolidaysDTO)holidaysDTO1;
            HolidaysEntity holidaysEntity = EM().find(HolidaysEntity.class, holidaysDTO.getCode());
            if (holidaysEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(holidaysEntity);
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
     * Get HolidaysEntity By Primary Key * @param id
     * @return IHolidaysDTO
     */
    public IHolidaysDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IHolidaysEntityKey id = (IHolidaysEntityKey)id1;
            List result =
                EM().createNamedQuery("HolidaysEntity.findEntityById").setParameter("yearCode", id.getYearCode()).setParameter("holtypeCode",
                                                                                                                               id.getHoltypeCode()).setHint("toplink.refresh",
                                                                                                                                                            "true").getResultList();
            if (result == null || result.size() == 0) {
                throw new ItemNotFoundException();
            }
            HolidaysEntity holidaysEntity = (HolidaysEntity)result.get(0);
            IHolidaysDTO holidaysDTO = InfDTOFactory.createHolidaysDTO(holidaysEntity);
            return holidaysDTO;
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
            Long id = (Long)EM().createNamedQuery("HolidaysEntity.findNewId").getSingleResult();
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
     * * @param yearCode
     * @return
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getOfficialVacation(Long yearCode) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<HolidaysEntity> list =
                EM().createNamedQuery("HolidaysEntity.findOfficialVacation").setParameter("yearCode",
                                                                                          yearCode).setHint("toplink.refresh",
                                                                                                            "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (HolidaysEntity holidaysEntity : list) {
                IHolidaysDTO holidaysDTO = InfDTOFactory.createHolidaysDTO(holidaysEntity);
                arrayList.add(holidaysDTO);
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
     * Search for HolidaysEntity * <br> * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            IHolidaysSearchDTO holidaysSearchDTO = (IHolidaysSearchDTO)basicDTO;
            StringBuilder ejbql = new StringBuilder("select o from HolidaysEntity o WHERE o.yearCode = o.yearCode and o.holidayTypesEntity.realFlag=1 ");
            if (holidaysSearchDTO.getYearCode() != null) {
                ejbql.append(" AND o.yearCode = " + holidaysSearchDTO.getYearCode());
            }
            if (holidaysSearchDTO.getHoltypeCode() != null) {
                ejbql.append(" AND o.holtypeCode = " + holidaysSearchDTO.getHoltypeCode());
            }

            if (holidaysSearchDTO.getUntilDate() != null || holidaysSearchDTO.getFromDate() != null) {
                ejbql.append(" AND (");
                ejbql.append(SharedUtils.getDatesRangeSearchString("o.fromDate", "o.untilDate",
                                                                   holidaysSearchDTO.getFromDate(),
                                                                   holidaysSearchDTO.getUntilDate(), true));
                ejbql.append(" )");
            }
            List<HolidaysEntity> list = EM().createQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (HolidaysEntity entity : list) {
                listDTO.add(InfDTOFactory.createHolidaysDTO(entity));
            }
            return listDTO;
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
     * list avialable types to an exist year * @param yearCode
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> listAvailable(Long yearCode) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> dtoList = new ArrayList();
            List<HolidayTypesEntity> list =
                EM().createNamedQuery("HolidayTypesEntity.listAvailabeEntity").setParameter("yearCode",
                                                                                            yearCode).setHint("toplink.refresh",
                                                                                                              "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (HolidayTypesEntity entity : list) {
                dtoList.add(new HolidayTypesDTO(entity));
            }
            return dtoList;
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
     * Get all vacation intersection with passing dates * @param fromDate
     * @param untilDate
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getOfficialVacationByPeriod(Date fromDate, Date untilDate) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<HolidaysEntity> list =
                EM().createNamedQuery("HolidaysEntity.getVacation").setParameter("status", ISystemConstant.ACTIVE_FLAG).setParameter("fromDate",
                                                                                                                                     fromDate).setParameter("untilDate",
                                                                                                                                                            untilDate).setHint("toplink.refresh",
                                                                                                                                                                               "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (HolidaysEntity holidaysEntity : list) {
                arrayList.add(InfDTOFactory.createHolidaysDTO(holidaysEntity));
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

    public List<IBasicDTO> searchVacOfficial(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            IHolidaysSearchDTO holidaysSearchDTO = (IHolidaysSearchDTO)basicDTO;
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<HolidaysEntity> list =
                EM().createNamedQuery("HolidaysEntity.search").setParameter("yearCode", holidaysSearchDTO.getYearCode()).setParameter("holtypeCode",
                                                                                                                                      holidaysSearchDTO.getHoltypeCode()).setParameter("fromDate",
                                                                                                                                                                                       holidaysSearchDTO.getFromDate()).setParameter("untilDate",
                                                                                                                                                                                                                                     holidaysSearchDTO.getUntilDate()).setHint("toplink.refresh",
                                                                                                                                                                                                                                                                               "true").getResultList();


            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (HolidaysEntity holidaysEntity : list) {
                arrayList.add(InfDTOFactory.createHolidaysDTO(holidaysEntity));
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
      
    public List<IBasicDTO> getAllByYearAndTypeCode(Long yearCode , Long typeCode) throws DataBaseException, SharedApplicationException {
        try {
          
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<HolidaysEntity> list =
                EM().createNamedQuery("HolidaysEntity.getAllByYearAndTypeCode").setParameter("yearCode", yearCode).setParameter("holtypeCode",
                                                                                                                                      typeCode).getResultList();


            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (HolidaysEntity holidaysEntity : list) {
                arrayList.add(InfEntityConverter.getHolidaysSimpleDTO(holidaysEntity));
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
      
}
