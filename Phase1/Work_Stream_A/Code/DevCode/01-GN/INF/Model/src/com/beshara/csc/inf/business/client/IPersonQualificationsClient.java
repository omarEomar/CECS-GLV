package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;


public interface IPersonQualificationsClient extends IBasicClient {
    /**
     * Get all Qualification of this person * @param civilId
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getAll(Long civilId) throws DataBaseException, SharedApplicationException;

    /**
     * validate personQualification data CR HR-406 * @param personQualificationsDTO1
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    void validatePersonQualification(IBasicDTO personQualificationsDTO1) throws DataBaseException, 
                                                                                SharedApplicationException;

    /**
     * update for personQualification data case UPDATE_CASE_LRG_QUAL_DATE CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    Boolean updateCaseLrgQualDate(IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException, 
                                                                                           SharedApplicationException;

    /**
     * update for personQualification data case UPDATE_CASE_LRG_QUAL_DATE_NO_PERIOD CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    Boolean updateCaseLrgQualDateNoPeriod(IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException, 
                                                                                                   SharedApplicationException;

    /**
     * update for personQualification data case UPDATE_CASE_SAME_EDU_LEVEL_CANDIDATE CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    Boolean updateCaseSameEduLevelCandidate(IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException, 
                                                                                                     SharedApplicationException;

    /**
     * update for personQualification data case UPDATE_CASE_SAME_EDU_LEVEL_REJECT CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    Boolean updateCaseSameEduLevelReject(IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException, 
                                                                                                  SharedApplicationException;

    /**
     * update for personQualification data case UPDATE_CASE_DIFF_EDU_LEVEL CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    Boolean updateCaseDiffEduLevel(IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException, 
                                                                                            SharedApplicationException;

    /**
     * update list of Person Qualification * @param kwtCitizensResidentsDTO1
     * @return IKwtCitizensResidentsDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    IBasicDTO updatePersonQualificationList(IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException, 
                                                                                       SharedApplicationException;

    /**
     * update for personQualification data defaultCase * @param personQualificationsDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    Boolean updateDefaultCase(IPersonQualificationsDTO personQualificationsDTO) throws SharedApplicationException, 
                                                                                       DataBaseException;

    List<IBasicDTO> listAvailableEntities(Long civilId) throws DataBaseException, SharedApplicationException;

    /**
     * get last person qualification * @param civilId
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @author Ashraf Gaber
     */
    IBasicDTO getLastPersonQualification(Long civilId) throws DataBaseException, SharedApplicationException;

    IBasicDTO getPersonQulificationInfo(Long civilId) throws DataBaseException, SharedApplicationException;

    IBasicDTO getLastPersonQualificationInCenter(Long civilId) throws DataBaseException, SharedApplicationException;
                                                                      
    IBasicDTO getCentralEmpPersonQul(Long civilId) throws DataBaseException, SharedApplicationException;

    public IBasicDTO getCurrentCentralEmpPersonQul(Long civilId) throws DataBaseException, SharedApplicationException;

}
