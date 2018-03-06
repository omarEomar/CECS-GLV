package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.PersonQualificationsSession;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;


public class PersonQualificationsClientImpl extends BaseClientImpl3 implements IPersonQualificationsClient {

    public PersonQualificationsClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return PersonQualificationsSession.class;
    }

    @Override
    protected PersonQualificationsSession SESSION() {
        return (PersonQualificationsSession)super.SESSION();
    }

    /**
     * Get all Qualification of this person * @param civilId
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAll(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getAll(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * validate personQualification data CR HR-406 * @param personQualificationsDTO1
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public void validatePersonQualification(IBasicDTO personQualificationsDTO1) throws DataBaseException,
                                                                                       SharedApplicationException {

        try {
            SESSION().validatePersonQualification(RI(), personQualificationsDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * update for personQualification data case UPDATE_CASE_LRG_QUAL_DATE CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean updateCaseLrgQualDate(IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException,
                                                                                                  SharedApplicationException {

        try {
            return SESSION().updateCaseLrgQualDate(RI(), personQualificationsDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * update for personQualification data case UPDATE_CASE_LRG_QUAL_DATE_NO_PERIOD CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean updateCaseLrgQualDateNoPeriod(IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException,
                                                                                                          SharedApplicationException {

        try {
            return SESSION().updateCaseLrgQualDateNoPeriod(RI(), personQualificationsDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * update for personQualification data case UPDATE_CASE_SAME_EDU_LEVEL_CANDIDATE CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean updateCaseSameEduLevelCandidate(IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException,
                                                                                                            SharedApplicationException {
        try {
            return SESSION().updateCaseSameEduLevelCandidate(RI(), personQualificationsDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * update for personQualification data case UPDATE_CASE_SAME_EDU_LEVEL_REJECT CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean updateCaseSameEduLevelReject(IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException,
                                                                                                         SharedApplicationException {

        try {
            return SESSION().updateCaseSameEduLevelReject(RI(), personQualificationsDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * update for personQualification data case UPDATE_CASE_DIFF_EDU_LEVEL CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean updateCaseDiffEduLevel(IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException,
                                                                                                   SharedApplicationException {

        try {
            return SESSION().updateCaseDiffEduLevel(RI(), personQualificationsDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * update list of Person Qualification * @param kwtCitizensResidentsDTO1
     * @return IKwtCitizensResidentsDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IBasicDTO updatePersonQualificationList(IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException,
                                                                                              SharedApplicationException {

        try {
            return SESSION().updatePersonQualificationList(RI(), kwtCitizensResidentsDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * update for personQualification data defaultCase * @param personQualificationsDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean updateDefaultCase(IPersonQualificationsDTO personQualificationsDTO) throws SharedApplicationException,
                                                                                              DataBaseException {

        try {
            return SESSION().updateDefaultCase(RI(), personQualificationsDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> listAvailableEntities(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().listAvailableEntities(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * get last person qualification * @param civilId
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @author Ashraf Gaber
     */
    public IBasicDTO getLastPersonQualification(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getLastPersonQualification(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO getPersonQulificationInfo(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getPersonQualificationInfo(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO getLastPersonQualificationInCenter(Long civilId) throws DataBaseException,
                                                                             SharedApplicationException {

        try {
            return SESSION().getLastPersonQualification(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public IBasicDTO getCentralEmpPersonQul(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getCentralEmpPersonQul(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public IBasicDTO getCurrentCentralEmpPersonQul(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getCurrentCentralEmpPersonQul(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
