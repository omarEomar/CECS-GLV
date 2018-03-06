package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.deploy.HolidaysSession;
import com.beshara.csc.inf.business.dto.holidays.IHolidaysSimpleDTO;
import com.beshara.csc.inf.business.entity.HolidaysEntity;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.SystemException;

import java.rmi.RemoteException;

import java.sql.Date;

import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Implements a specified Client Interface as Session Bean * and Generated through the Client Factory Class Based on the Key Returned from the Properties File . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Taha El-Fitiany , Sherif El-Rabbat
 * @version 1.0
 * @since 03/09/2007
 */
public class HolidaysClientImpl extends BaseClientImpl3 implements IHolidaysClient {
    public HolidaysClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return HolidaysSession.class;
    }

    @Override
    protected HolidaysSession SESSION() {
        return (HolidaysSession)super.SESSION();
    }

    public List<IBasicDTO> getOfficialVacation(Long yearCode) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().getOfficialVacation(RI(), yearCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * return a module relation DTO to join holidays with regulation * @param holidaysEntityKey
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IBasicDTO getModuleRelationDTO(IEntityKey holidaysEntityKey) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            return SESSION().getModuleRelationDTO(RI(), holidaysEntityKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * return a module relation DTO to join holidays with regulation In Center * @param holidaysEntityKey
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IBasicDTO getModuleRelationDTOInCenter(IEntityKey holidaysEntityKey) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            return SESSION().getModuleRelationDTO(RI(), holidaysEntityKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * add regulation to be joined with holidays * @param regulationsDTO
     * @param holidaysEntityKey
     * @return IBasicDTO
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public IBasicDTO addRegulation(IRegulationsDTO regulationsDTO,
                                   IEntityKey holidaysEntityKey) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().addRegulation(RI(), regulationsDTO, holidaysEntityKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * list avialable types to an exist year * @param yearCode
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> listAvailable(Long yearCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().listAvailable(RI(), yearCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> getOfficialVacationByPeriod(Date fromDate,
                                                       Date untilDate) throws SharedApplicationException,
                                                                              DataBaseException {
        try {
            return SESSION().getOfficialVacationByPeriod(RI(), fromDate, untilDate);
        } catch (RemoteException e) {
            SystemException se = new SystemException();
            throw new DataBaseException(se.getSQLExceptionMessage());
        }
    }

    public List<IBasicDTO> searchVacOfficial(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().searchVacOfficial(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public boolean updateHoliday(IBasicDTO holidaysDTO1 , Date oldFromDate) throws DataBaseException,  SharedApplicationException {
        
        try {
            return SESSION().updateHoliday(RI(), holidaysDTO1 ,  oldFromDate) ;
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    
    public Boolean updateSimpleHoliday(IBasicDTO holidaysDTO1) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().updateSimpleHoliday(RI(), holidaysDTO1 ) ;
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    
    public List<IBasicDTO> getAllByYearAndTypeCode(Long yearCode , Long typeCode) throws DataBaseException, SharedApplicationException {
        
        try {
            return SESSION().getAllByYearAndTypeCode(RI(), yearCode ,  typeCode) ;
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    public IHolidaysSimpleDTO addHolidays(IBasicDTO holidaysSimpleDTO1) throws DataBaseException, SharedApplicationException{
        
        try {
            return SESSION().addHolidays(RI(),holidaysSimpleDTO1) ;
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

}
