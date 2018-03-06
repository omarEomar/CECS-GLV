package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface PersonQualificationsSession extends BasicSession {
    /**
     * Get all Qualification of this person * @param civilId
     * @return list
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    List<IBasicDTO> getAll(IRequestInfoDTO ri,Long civilId) throws RemoteException, DataBaseException,
                                                SharedApplicationException;

    /**
     * validate personQualification data CR HR-406 * @param personQualificationsDTO1
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    void validatePersonQualification(IRequestInfoDTO ri, IBasicDTO personQualificationsDTO1) throws RemoteException,
                                                                                                    DataBaseException,
                                                SharedApplicationException;

    /**
     * update for personQualification data case UPDATE_CASE_LRG_QUAL_DATE CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    Boolean updateCaseLrgQualDate(IRequestInfoDTO ri,
                                  IPersonQualificationsDTO personQualificationsDTO) throws RemoteException,
                                                                                           SharedApplicationException, 
                                                                                           DataBaseException;

    /**
     * update for personQualification data case UPDATE_CASE_LRG_QUAL_DATE_NO_PERIOD CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    Boolean updateCaseLrgQualDateNoPeriod(IRequestInfoDTO ri,
                                          IPersonQualificationsDTO personQualificationsDTO) throws RemoteException,
                                                                                                   SharedApplicationException, 
                                                                                                   DataBaseException;
    /**
 * update for personQualification data case UPDATE_CASE_SAME_EDU_LEVEL_CANDIDATE CR HR-406 * @param personQualificationsDTO
 * @return Boolean
 * @throws RemoteException
 * @throws SharedApplicationException
 */

    /**
     * update for personQualification data case UPDATE_CASE_SAME_EDU_LEVEL_CANDIDATE CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    Boolean updateCaseSameEduLevelCandidate(IRequestInfoDTO ri,
                                            IPersonQualificationsDTO personQualificationsDTO) throws RemoteException,
                                                                                                     SharedApplicationException, 
                                                                                                     DataBaseException;

    /**
     * update for personQualification data case UPDATE_CASE_SAME_EDU_LEVEL_REJECT CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    Boolean updateCaseSameEduLevelReject(IRequestInfoDTO ri,
                                         IPersonQualificationsDTO personQualificationsDTO) throws RemoteException,
                                                                                                  SharedApplicationException, 
                                                                                                  DataBaseException;

    /**
     * update for personQualification data case UPDATE_CASE_DIFF_EDU_LEVEL CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    Boolean updateCaseDiffEduLevel(IRequestInfoDTO ri,
                                   IPersonQualificationsDTO personQualificationsDTO) throws RemoteException,
                                                                                            SharedApplicationException, 
                                                                                            DataBaseException;

    /**
     * update list of Person Qualification * @param kwtCitizensResidentsDTO1
     * @return IKwtCitizensResidentsDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    IBasicDTO updatePersonQualificationList(IRequestInfoDTO ri,
                                            IBasicDTO kwtCitizensResidentsDTO1) throws RemoteException,
                                                                                       DataBaseException,
                                                SharedApplicationException;

    /**
     * update for personQualification data defaultCase * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    Boolean updateDefaultCase(IRequestInfoDTO ri,
                              IPersonQualificationsDTO personQualificationsDTO) throws RemoteException,
                                                                                       SharedApplicationException, 
                                                                                       DataBaseException;

    List<IBasicDTO> listAvailableEntities(IRequestInfoDTO ri,Long civilId)  throws RemoteException, DataBaseException,
                                                SharedApplicationException;

    /**
     * get last person qualification * @param civilId
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     * @author Ashraf Gaber
     */
    IBasicDTO getLastPersonQualification(IRequestInfoDTO ri,Long civilId)  throws RemoteException, DataBaseException,
                                                SharedApplicationException;

    IBasicDTO getPersonQualificationInfo(IRequestInfoDTO ri,Long civilId) throws RemoteException, DataBaseException,
                                                SharedApplicationException;

    IBasicDTO getCentralEmpPersonQul(IRequestInfoDTO ri,Long civilId)  throws RemoteException, DataBaseException,
                                                SharedApplicationException;

    public Boolean updateRegisterationOrderCMT(IRequestInfoDTO ri,
                                               IPersonQualificationsDTO personQualificationsDTO) throws RemoteException,
                                                                                                        SharedApplicationException,
                                                                                                        DataBaseException;

    IBasicDTO getCurrentCentralEmpPersonQul(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                                     DataBaseException,
                                                                                     SharedApplicationException;
}
