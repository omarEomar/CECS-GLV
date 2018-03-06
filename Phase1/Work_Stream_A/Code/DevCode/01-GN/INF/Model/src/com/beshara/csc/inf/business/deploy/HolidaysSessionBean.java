package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.HolidaysDAO;
import com.beshara.csc.inf.business.dto.IHolidaysDTO;
import com.beshara.csc.inf.business.dto.holidays.IHolidaysSimpleDTO;
import com.beshara.csc.inf.business.entity.HolidaysEntity;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IModuleRelationsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationStatusDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDateException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.sql.Date;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Represents the Business Object Wrapper ( as Session Bean ) for Business Component IpIuIbIlIiIsIhIiInIgI.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Stateless(name = "HolidaysSession", mappedName = "Inf-Model-HolidaysSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class HolidaysSessionBean extends InfBaseSessionBean implements HolidaysSession {

    /**
     * JobsSession Default Constructor */
    public HolidaysSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return HolidaysEntity.class;
    }

    @Override
    protected HolidaysDAO DAO() {
        return (HolidaysDAO)super.DAO();
    }

    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO holidaysDTO1) throws DataBaseException,
                                                                            SharedApplicationException {
        IHolidaysDTO holidaysDTO = (IHolidaysDTO)holidaysDTO1;
        Date frmDate = holidaysDTO.getFromDate();
        Date untilDate = holidaysDTO.getUntilDate();
        holidaysDTO.setStatus(ISystemConstant.ACTIVE_FLAG);
        if (frmDate.after(untilDate))
            throw new InvalidDateException();
        return DAO().add(holidaysDTO);
    }

    public List<IBasicDTO> searchVacOfficial(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                            SharedApplicationException {
        try {
            return DAO().searchVacOfficial(basicDTO);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    public List<IBasicDTO> getOfficialVacation(IRequestInfoDTO ri, Long yearCode) throws DataBaseException,
                                                                                         SharedApplicationException {
        try {
            return DAO().getOfficialVacation(yearCode);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    public IBasicDTO getModuleRelationDTO(IRequestInfoDTO ri, IEntityKey holidaysEntityKey) throws DataBaseException,
                                                                                                   SharedApplicationException {
        IHolidaysDTO holidaysDTO = DAO().getById(holidaysEntityKey);
        Long tabRecSerial = holidaysDTO.getTabrecSerial();
        //TODO remove comment after adding constant
        String tableName = ISystemConstant.TABLE_INF_HOLIDAYS;
        IModuleRelationsDTO moduleRelationsDTO = RegDTOFactory.createModuleRelationsDTO();
        moduleRelationsDTO.setTableName(tableName);
        moduleRelationsDTO.setTabrecSerialRef(tabRecSerial);
        return moduleRelationsDTO;
    }

    public IBasicDTO addRegulation(IRequestInfoDTO ri, IRegulationsDTO regulationsDTO,
                                   IEntityKey holidaysEntityKey) throws DataBaseException, SharedApplicationException {
        IModuleRelationsDTO moduleRelationsDTO = (IModuleRelationsDTO)this.getModuleRelationDTO(ri, holidaysEntityKey);
        IRegulationStatusDTO regulationStatusDTO = RegDTOFactory.createRegulationStatusDTO();
        regulationStatusDTO.setCode(RegEntityKeyFactory.createRegulationStatusEntityKey(ISystemConstant.REGULATION_STATUS_VALID));
        regulationsDTO.setStatusDto(regulationStatusDTO);
        IRegulationsDTO addedRegulationsDTO =
            (IRegulationsDTO)RegClientFactory.getRegulationsClient().add(regulationsDTO, false);
        moduleRelationsDTO.setRegulationsDTO(addedRegulationsDTO);
        IBasicDTO addedDTO = RegClientFactory.getModuleRelationsClient().add(moduleRelationsDTO);
        return addedDTO;
    }

    public List<IBasicDTO> listAvailable(IRequestInfoDTO ri, Long yearCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return DAO().listAvailable(yearCode);
        } catch (ItemNotFoundException e) {
            throw new SharedApplicationException();
        }
    }

    public List<IBasicDTO> getOfficialVacationByPeriod(IRequestInfoDTO ri, Date fromDate,
                                                       Date untilDate) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            return DAO().getOfficialVacationByPeriod(fromDate, untilDate);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }

    }
    
    public boolean updateHoliday(IRequestInfoDTO ri,IBasicDTO holidaysDTO1 , Date oldFromDate) throws DataBaseException,  SharedApplicationException {
        
        try {
            return DAO().updateHoliday(holidaysDTO1 ,  oldFromDate) ;
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }
    
    
    public Boolean updateSimpleHoliday(IRequestInfoDTO ri,IBasicDTO holidaysDTO1) throws DataBaseException, SharedApplicationException {
        try {
            return DAO().updateSimpleHoliday(holidaysDTO1) ;
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }
    
    public List<IBasicDTO> getAllByYearAndTypeCode(IRequestInfoDTO ri,Long yearCode , Long typeCode) throws DataBaseException, SharedApplicationException {
        
        try {
            return DAO().getAllByYearAndTypeCode(yearCode ,  typeCode) ;
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }
    
    public IHolidaysSimpleDTO addHolidays(IRequestInfoDTO ri,IBasicDTO holidaysSimpleDTO1) throws DataBaseException, SharedApplicationException {
        
        try {
            return DAO().addHolidays(holidaysSimpleDTO1) ;
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }
}
