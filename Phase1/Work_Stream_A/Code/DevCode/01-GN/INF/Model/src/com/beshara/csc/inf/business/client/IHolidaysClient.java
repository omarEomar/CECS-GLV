package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.holidays.IHolidaysSimpleDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.sql.Date;

import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Interface Contains a set of Methods Which Should be Implemented * with Different Implementation ( SessionBean , Message Driven Bean , RMI Service etc ... ) * and Generated through The Client Factory Class . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Sherif El-Rabbat 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry
 * @version 1.0
 * @since 03/09/2007
 */
public interface IHolidaysClient extends IBasicClient {
    public List<IBasicDTO> getOfficialVacation(Long yearCode) throws SharedApplicationException, 
                                                                     DataBaseException;

    /** 
     * return a module relation DTO to join holidays with regulation * @param holidaysEntityKey 
     * @return IBasicDTO 
     * @throws DataBaseException 
     * @throws SharedApplicationException 
     */
    public IBasicDTO getModuleRelationDTO(IEntityKey holidaysEntityKey) throws DataBaseException, 
                                                                               SharedApplicationException;

    /** 
     * add regulation to be joined with holidays * @param regulationsDTO 
     * @param holidaysEntityKey 
     * @return IBasicDTO 
     * @throws SharedApplicationException 
     * @throws DataBaseException 
     */
    public IBasicDTO addRegulation(IRegulationsDTO regulationsDTO, 
                                   IEntityKey holidaysEntityKey) throws SharedApplicationException, 
                                                                        DataBaseException;

    /** 
     * list avialable types to an exist year * @param yearCode 
     * @return List 
     * @throws DataBaseException 
     * @throws SharedApplicationException 
     */
    public List<IBasicDTO> listAvailable(Long yearCode) throws DataBaseException, 
                                                               SharedApplicationException;

    List<IBasicDTO> getOfficialVacationByPeriod(Date fromDate, 
                                                Date untilDate) throws DataBaseException, 
                                                                       SharedApplicationException;

    /** 
     * return a module relation DTO to join holidays with regulation In Center * @param holidaysEntityKey 
     * @return IBasicDTO 
     * @throws DataBaseException 
     * @throws SharedApplicationException 
     */
    public IBasicDTO getModuleRelationDTOInCenter(IEntityKey holidaysEntityKey) throws DataBaseException, 
                                                                                              SharedApplicationException;
    

    public List<IBasicDTO> searchVacOfficial(IBasicDTO basicDTO)throws DataBaseException, 
                                                                                SharedApplicationException;
    
    public boolean updateHoliday(IBasicDTO holidaysDTO1 , Date oldFromDate) throws DataBaseException,  SharedApplicationException ;
    
    public List<IBasicDTO> getAllByYearAndTypeCode(Long yearCode , Long typeCode) throws DataBaseException, SharedApplicationException ;
    
    public IHolidaysSimpleDTO addHolidays(IBasicDTO holidaysSimpleDTO1) throws DataBaseException, SharedApplicationException;
   
    public Boolean updateSimpleHoliday(IBasicDTO holidaysDTO1) throws DataBaseException, SharedApplicationException ;

}
