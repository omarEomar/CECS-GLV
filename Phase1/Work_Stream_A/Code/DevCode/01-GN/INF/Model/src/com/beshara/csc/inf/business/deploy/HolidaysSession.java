package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dto.holidays.IHolidaysSimpleDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.sql.Date;

import java.util.List;

import javax.ejb.Remote;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Remote Interface Contains All the Methods which are Implemented By Session Bean . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Remote
public interface HolidaysSession extends BasicSession {
    public List<IBasicDTO> getOfficialVacation(IRequestInfoDTO ri, Long yearCode) throws RemoteException,
                                                                                         DataBaseException,
                                                                                         SharedApplicationException;

    public IBasicDTO getModuleRelationDTO(IRequestInfoDTO ri, IEntityKey holidaysEntityKey) throws RemoteException,
                                                                                                   DataBaseException,
                                                                                                   SharedApplicationException;

    public IBasicDTO addRegulation(IRequestInfoDTO ri, IRegulationsDTO regulationsDTO,
                                   IEntityKey holidaysEntityKey) throws RemoteException, SharedApplicationException,
                                                                        DataBaseException;

    public List<IBasicDTO> listAvailable(IRequestInfoDTO ri, Long yearCode) throws RemoteException, DataBaseException,
                                                                                   SharedApplicationException;

    List<IBasicDTO> getOfficialVacationByPeriod(IRequestInfoDTO ri, Date fromDate,
                                                Date untilDate) throws RemoteException, DataBaseException,
                                                                       SharedApplicationException;

    public List<IBasicDTO> searchVacOfficial(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                            DataBaseException,
                                                                                            SharedApplicationException;

    public boolean updateHoliday(IRequestInfoDTO ri,IBasicDTO holidaysDTO1 , Date oldFromDate) throws RemoteException , DataBaseException,  SharedApplicationException ;
    
    public List<IBasicDTO> getAllByYearAndTypeCode(IRequestInfoDTO ri,Long yearCode , Long typeCode) throws RemoteException ,DataBaseException, SharedApplicationException;
    
    public IHolidaysSimpleDTO addHolidays(IRequestInfoDTO ri,IBasicDTO holidaysSimpleDTO1) throws RemoteException ,DataBaseException, SharedApplicationException ;

    public Boolean updateSimpleHoliday(IRequestInfoDTO ri,IBasicDTO holidaysDTO1) throws RemoteException ,DataBaseException, SharedApplicationException; 

}
