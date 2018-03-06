package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.crs.business.client.CrsClientFactory;
import com.beshara.csc.hr.crs.business.dto.IRegistrationPeriodsDTO;
import com.beshara.csc.hr.crs.business.dto.JobSeekersDTO;
import com.beshara.csc.hr.crs.business.dto.RegStatusDTO;
import com.beshara.csc.hr.crs.business.entity.JobSeekersEntityKey;
import com.beshara.csc.hr.crs.business.entity.RegStatusEntityKey;
import com.beshara.csc.hr.crs.business.entity.RegistrationPeriodsEntityKey;
import com.beshara.csc.inf.business.client.IPersonQualificationsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dao.InfGradeTypesDAO;
import com.beshara.csc.inf.business.dao.InfGradeValuesDAO;
import com.beshara.csc.inf.business.dao.PersonQualificationsDAO;
import com.beshara.csc.inf.business.dto.IInfGradeValuesDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.IPersonQualificationsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntity;
import com.beshara.csc.inf.business.entity.InfGradeValuesEntity;
import com.beshara.csc.inf.business.entity.PersonQualificationsEntity;
import com.beshara.csc.nl.qul.business.dto.IQualificationsDTO;
import com.beshara.csc.nl.qul.business.entity.IEducationLevelsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.crs.CrsPersonQualificationException;
import com.beshara.csc.sharedutils.business.exception.crs.DiffQulsEducationLevelException;
import com.beshara.csc.sharedutils.business.exception.crs.LrgQulDateNoPeriodFoundException;
import com.beshara.csc.sharedutils.business.exception.crs.LrgQulDatePeriodFoundException;
import com.beshara.csc.sharedutils.business.exception.crs.SameQulsEduLevelCandidateException;
import com.beshara.csc.sharedutils.business.exception.crs.SameQulsEduLevelRejectedException;
import com.beshara.csc.sharedutils.business.util.ICRSConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.rmi.RemoteException;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import oracle.toplink.essentials.exceptions.TransactionException;


@Stateless(name = "PersonQualificationsSession", mappedName = "Inf-Model-PersonQualificationsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(PersonQualificationsSession.class)
public class PersonQualificationsSessionBean extends InfBaseSessionBean implements PersonQualificationsSession {


    public PersonQualificationsSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return PersonQualificationsEntity.class;
    }

    @Override
    protected PersonQualificationsDAO DAO() {
        return (PersonQualificationsDAO)super.DAO();
    }

    /**
     * Get all Qualification of this person * @param civilId
     * @return list
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    public List<IBasicDTO> getAll(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                           SharedApplicationException {

        return DAO().getAll(civilId);

    }

    /**
     * validate personQualification data CR HR-406 * @param personQualificationsDTO1
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public void validatePersonQualification(IRequestInfoDTO ri,
                                            IBasicDTO personQualificationsDTO1) throws DataBaseException,
                                                                                       SharedApplicationException {
        IPersonQualificationsDTO personQualificationsDTO = (IPersonQualificationsDTO)personQualificationsDTO1;
        IPersonQualificationsDTO dbPersonQual =
            (IPersonQualificationsDTO)DAO().getById(personQualificationsDTO.getCode());
        IQualificationsDTO dbQualDTO = dbPersonQual.getQualificationsDTO();
        IKwtCitizensResidentsDTO citizenDTO = dbPersonQual.getKwtCitizensResidentsDTO();
        Long civilId = ((IKwtCitizensResidentsEntityKey)citizenDTO.getCode()).getCivilId();
        IBasicDTO defaultPersonQual = null;
        try {
            defaultPersonQual = DAO().getCentralEmpPersonQul(civilId);
        } catch (ItemNotFoundException e) { //no personQualification assigned -- continue update
        }
        if (defaultPersonQual != null) {
            JobSeekersDTO jobSeekersDTO = null;


            try {
                jobSeekersDTO =
                        (JobSeekersDTO)CrsClientFactory.getJobSeekersClient().getJobSeekerWithRegStatus(civilId);
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
            if (jobSeekersDTO != null) { //ADDED BY TAHA TO AVOID NULL  EXCEPTOION
                if (jobSeekersDTO.getRegistrationPeriodsDTO().getEndDate() != null)
                    if (dbPersonQual.getQualificationDate().compareTo(jobSeekersDTO.getRegistrationPeriodsDTO().getEndDate()) >
                        0) {
                        IRegistrationPeriodsDTO regPeriodDTO = null;
                        try {
                            regPeriodDTO =
                                    CrsClientFactory.getRegistrationPeriodsClient().getRegPeriodforPersonQualification(dbPersonQual.getQualificationDate());
                        } catch (DataBaseException e) {
                            e.printStackTrace();
                        }
                        if (regPeriodDTO != null) {
                            throw new LrgQulDatePeriodFoundException();
                        } else {
                            throw new LrgQulDateNoPeriodFoundException();
                        }
                    }
                if (!((IEducationLevelsEntityKey)dbQualDTO.getEducationLevelsDTO().getCode()).getLevelCode().equals(((IEducationLevelsEntityKey)((IPersonQualificationsDTO)defaultPersonQual).getQualificationsDTO().getEducationLevelsDTO().getCode()).getLevelCode())) { //second case
                    throw new DiffQulsEducationLevelException();
                } else {
                    if (((RegStatusEntityKey)jobSeekersDTO.getRegStatusDTO().getCode()).getRegstatusCode().equals(ICRSConstant.REG_CANDIDATE)) {
                        throw new SameQulsEduLevelCandidateException();
                    } else if (((RegStatusEntityKey)jobSeekersDTO.getRegStatusDTO().getCode()).getRegstatusCode().equals(ICRSConstant.REG_CANDIDATE_REJECT)) {
                        throw new SameQulsEduLevelRejectedException();
                    }
                }
            }
        }
    }


    /**
     * update for personQualification data case UPDATE_CASE_LRG_QUAL_DATE CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean updateCaseLrgQualDate(IRequestInfoDTO ri,
                                         IPersonQualificationsDTO personQualificationsDTO) throws SharedApplicationException,
                                                                                                  DataBaseException {
        Long validationCase = ICRSConstant.UPDATE_CASE_LRG_QUAL_DATE;


        try {
            if (isTransactionBegun()) {
                suspendTransaction();
            }
            beginTransaction();
            IKwtCitizensResidentsDTO citizenDTO = personQualificationsDTO.getKwtCitizensResidentsDTO();
            Long civilId = ((IKwtCitizensResidentsEntityKey)citizenDTO.getCode()).getCivilId();
            IBasicDTO defaultPersonQual = null;

            try {
                defaultPersonQual = DAO().getCentralEmpPersonQul(civilId);
            } catch (ItemNotFoundException e) { //no personQualification assigned -- continue update
                e.printStackTrace();
            }

            if (defaultPersonQual != null) { //swap registration order
                ((IPersonQualificationsDTO)defaultPersonQual).setCrsRegistrationOrder(null);
                super.update(ri, defaultPersonQual);
                JobSeekersDTO jobSeekersDTO = null;
                try {
                    jobSeekersDTO =
                            (JobSeekersDTO)CrsClientFactory.getJobSeekersClient().getJobSeekerWithRegStatus(civilId);
                } catch (ItemNotFoundException e) { // No job seeker found
                    e.printStackTrace();
                }


                if (jobSeekersDTO != null) {
                    RegStatusDTO regStatusDTO = new RegStatusDTO();
                    regStatusDTO.setCode(new RegStatusEntityKey(ICRSConstant.REG_CANDIDATE_INVALID_AUTHORITY));
                    RegStatusDTO qualifiedRegStatusDTO = new RegStatusDTO();
                    qualifiedRegStatusDTO.setCode(new RegStatusEntityKey(ICRSConstant.REG_QULIFIED_FOR_CANDIDACY));
                    //update for case1
                    if (validationCase.equals(ICRSConstant.UPDATE_CASE_LRG_QUAL_DATE)) {
                        jobSeekersDTO.setRegStatusDTO(regStatusDTO);
                        CrsClientFactory.getJobSeekersClient().update(jobSeekersDTO);
                        //get registration period with start date < qualification date
                        IRegistrationPeriodsDTO regPeriodDTO =
                            CrsClientFactory.getRegistrationPeriodsClient().getRegPeriodforPersonQualification(personQualificationsDTO.getQualificationDate());
                        // add new jobseeker with the same data and with reg status REG_QULIFIED_FOR_CANDIDACY
                        if (regPeriodDTO !=
                            null) { //Check if the job seeker already register on theat central employment period before ( Added by Amir Nasr )
                            JobSeekersDTO newJobSeekersDTO = null;
                            try {
                                newJobSeekersDTO =
                                        (JobSeekersDTO)CrsClientFactory.getJobSeekersClient().getById(new JobSeekersEntityKey(civilId,
                                                                                                                              ((RegistrationPeriodsEntityKey)regPeriodDTO.getCode()).getRegperiodCode()));
                                newJobSeekersDTO.setActiveFlag(ISystemConstant.ACTIVE_FLAG);
                                CrsClientFactory.getJobSeekersClient().update(newJobSeekersDTO);
                            } catch (ItemNotFoundException e) {
                                newJobSeekersDTO = new JobSeekersDTO();
                                newJobSeekersDTO.setRegistrationPeriodsDTO(regPeriodDTO);
                                newJobSeekersDTO.setActiveFlag(ISystemConstant.ACTIVE_FLAG);
                                newJobSeekersDTO.setJobSeekerOrder(jobSeekersDTO.getJobSeekerOrder());
                                newJobSeekersDTO.setKwtCitizensResidentsDTO(jobSeekersDTO.getKwtCitizensResidentsDTO());
                                newJobSeekersDTO.setLastCancelDate(jobSeekersDTO.getLastCancelDate());
                                newJobSeekersDTO.setLastReRegDate(jobSeekersDTO.getLastReRegDate());
                                newJobSeekersDTO.setRegDate(jobSeekersDTO.getRegDate());
                                newJobSeekersDTO.setRegRefrence(jobSeekersDTO.getRegRefrence());
                                newJobSeekersDTO.setRegStatusDTO(qualifiedRegStatusDTO);
                                newJobSeekersDTO.setTrySourceDTO(jobSeekersDTO.getTrySourceDTO());
                                CrsClientFactory.getJobSeekersClient().add(newJobSeekersDTO);
                            }
                        }
                    } //update for case2
                    else if (validationCase.equals(ICRSConstant.UPDATE_CASE_DIFF_EDU_LEVEL)) {
                        jobSeekersDTO.setRegStatusDTO(regStatusDTO);
                        CrsClientFactory.getJobSeekersClient().update(jobSeekersDTO);
                    } //update for case3
                    else if (validationCase.equals(ICRSConstant.UPDATE_CASE_SAME_EDU_LEVEL)) {
                        if (((RegStatusEntityKey)jobSeekersDTO.getRegStatusDTO().getCode()).getRegstatusCode().equals(ICRSConstant.REG_CANDIDATE) ||
                            ((RegStatusEntityKey)jobSeekersDTO.getRegStatusDTO().getCode()).getRegstatusCode().equals(ICRSConstant.REG_CANDIDATE_REJECT)) {
                            jobSeekersDTO.setRegStatusDTO(qualifiedRegStatusDTO);
                            CrsClientFactory.getJobSeekersClient().update(jobSeekersDTO);
                        }
                    }
                }
            }
            Long oldValue = personQualificationsDTO.getCrsRegistrationOrder();
            personQualificationsDTO.setCrsRegistrationOrder(1L);
            super.update(ri, personQualificationsDTO);
            if (defaultPersonQual != null) {
                ((IPersonQualificationsDTO)defaultPersonQual).setCrsRegistrationOrder(oldValue);
                super.update(ri, defaultPersonQual);
            }
            commitTransaction();
            return Boolean.TRUE;
        } catch (ItemNotFoundException e) {
            rollbackTransaction();
            throw new InvalidDataEntryException();
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            rollbackTransaction();

        }
        return null;
    }


    /**
     * update for personQualification data case UPDATE_CASE_LRG_QUAL_DATE_NO_PERIOD CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean updateCaseLrgQualDateNoPeriod(IRequestInfoDTO ri,
                                                 IPersonQualificationsDTO personQualificationsDTO) throws SharedApplicationException,
                                                                                                          DataBaseException {

        return updateForPersonQualificationsData(ri, personQualificationsDTO, ICRSConstant.UPDATE_CASE_LRG_QUAL_DATE);

    }

    /**
     * update for personQualification data case UPDATE_CASE_SAME_EDU_LEVEL_CANDIDATE CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean updateCaseSameEduLevelCandidate(IRequestInfoDTO ri,
                                                   IPersonQualificationsDTO personQualificationsDTO) throws SharedApplicationException,
                                                                                                            DataBaseException {

        return updateForPersonQualificationsData(ri, personQualificationsDTO, ICRSConstant.UPDATE_CASE_SAME_EDU_LEVEL);

    }

    /**
     * update for personQualification data case UPDATE_CASE_SAME_EDU_LEVEL_REJECT CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean updateCaseSameEduLevelReject(IRequestInfoDTO ri,
                                                IPersonQualificationsDTO personQualificationsDTO) throws SharedApplicationException,
                                                                                                         DataBaseException {

        return updateForPersonQualificationsData(ri, personQualificationsDTO, ICRSConstant.UPDATE_CASE_SAME_EDU_LEVEL);

    }

    /**
     * update for personQualification data case UPDATE_CASE_DIFF_EDU_LEVEL CR HR-406 * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean updateCaseDiffEduLevel(IRequestInfoDTO ri,
                                          IPersonQualificationsDTO personQualificationsDTO) throws SharedApplicationException,
                                                                                                   DataBaseException {

        return updateForPersonQualificationsData(ri, personQualificationsDTO, ICRSConstant.UPDATE_CASE_DIFF_EDU_LEVEL);

    }

    /**
     * update for personQualification data defaultCase * @param personQualificationsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean updateDefaultCase(IRequestInfoDTO ri,
                                     IPersonQualificationsDTO personQualificationsDTO) throws SharedApplicationException,
                                                                                              DataBaseException {

        return updateForPersonQualificationsData(ri, personQualificationsDTO, -1L);
    }


    /**
     * update list of Person Qualification * @param kwtCitizensResidentsDTO1
     * @return IKwtCitizensResidentsDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public IBasicDTO updatePersonQualificationList(IRequestInfoDTO ri,
                                                   IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException,
                                                                                              SharedApplicationException {

        try {
            beginTransaction();
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)kwtCitizensResidentsDTO1;
            //set PersonQualificationsDTOList
            if (kwtCitizensResidentsDTO.getPersonQualificationsDTOList() != null) {
                IBasicDTO defaultPersonQual = null;
                IPersonQualificationsEntityKey defaultKey = null;
                try {
                    defaultPersonQual =
                            DAO().getCentralEmpPersonQul(((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId());
                    defaultKey = (IPersonQualificationsEntityKey)defaultPersonQual.getCode();
                } catch (ItemNotFoundException e) { //no personQualification assigned -- continue update
                }
                for (IBasicDTO personQualificationsDTO : kwtCitizensResidentsDTO.getPersonQualificationsDTOList()) {
                    IPersonQualificationsEntityKey key =
                        (IPersonQualificationsEntityKey)personQualificationsDTO.getCode();
                    if (personQualificationsDTO.getStatusFlag() == null) {
                        if (((IPersonQualificationsDTO)personQualificationsDTO).getCrsRegistrationOrder() != null &&
                            ((IPersonQualificationsDTO)personQualificationsDTO).getCrsRegistrationOrder().equals(1L)) {
                            if (defaultPersonQual != null && defaultKey != null &&
                                !key.getQualificationKey().equals(defaultKey.getQualificationKey())) {
                                throw new InvalidDataEntryException();
                            }
                        }
                        DAO().update(personQualificationsDTO);
                    }
                }
            }
            commitTransaction();
            return kwtCitizensResidentsDTO;
        } catch (ItemNotFoundException e) {
            rollbackTransaction();
            throw new InvalidDataEntryException();
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            rollbackTransaction();
        }
        return null;
    }


    public List<IBasicDTO> listAvailableEntities(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                          SharedApplicationException {

        return DAO().listAvailableEntities(civilId);

    }

    /**
     * get last person qualification * @param civilId
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     * @author Ashraf Gaber
     */
    public IBasicDTO getLastPersonQualification(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                         SharedApplicationException {

        return DAO().getLastPersonQualification(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilId));

    }

    public IBasicDTO getPersonQualificationInfo(IRequestInfoDTO ri, Long civilId) throws SharedApplicationException,
                                                                                         DataBaseException {


        IPersonQualificationsClient personQualificationsClient = InfClientFactory.getPersonQualificationsClient();
        IPersonQualificationsDTO dto = null;
        try {
            dto =
(IPersonQualificationsDTO)DAO().getLastPersonQualification(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilId));
        } catch (Exception e) {
            dto = (IPersonQualificationsDTO)personQualificationsClient.getLastPersonQualificationInCenter(civilId);
        }

        return dto;
    }

    public IBasicDTO getCentralEmpPersonQul(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                     SharedApplicationException {

        return DAO().getCentralEmpPersonQul(civilId);

    }

    public Boolean updateForPersonQualificationsData(IRequestInfoDTO ri,
                                                     IPersonQualificationsDTO personQualificationsDTO,
                                                     Long validationCase) throws SharedApplicationException,
                                                                                 DataBaseException {

        try {
            if (isTransactionBegun()) {
                suspendTransaction();
            }
            beginTransaction();
            IKwtCitizensResidentsDTO citizenDTO = personQualificationsDTO.getKwtCitizensResidentsDTO();
            Long civilId = ((IKwtCitizensResidentsEntityKey)citizenDTO.getCode()).getCivilId();
            IBasicDTO defaultPersonQual = null;

            try {
                defaultPersonQual = DAO().getCentralEmpPersonQul(civilId);
            } catch (ItemNotFoundException e) { //no personQualification assigned -- continue update
                e.printStackTrace();
            }

            if (defaultPersonQual != null) { //swap registration order
                ((IPersonQualificationsDTO)defaultPersonQual).setCrsRegistrationOrder(null);
                super.update(ri, defaultPersonQual);
                JobSeekersDTO jobSeekersDTO = null;
                try {
                    jobSeekersDTO =
                            (JobSeekersDTO)CrsClientFactory.getJobSeekersClient().getJobSeekerWithRegStatus(civilId);
                } catch (ItemNotFoundException e) { // No job seeker found
                    e.printStackTrace();
                }


                if (jobSeekersDTO != null) {
                    RegStatusDTO regStatusDTO = new RegStatusDTO();
                    regStatusDTO.setCode(new RegStatusEntityKey(ICRSConstant.REG_CANDIDATE_INVALID_AUTHORITY));
                    RegStatusDTO qualifiedRegStatusDTO = new RegStatusDTO();
                    qualifiedRegStatusDTO.setCode(new RegStatusEntityKey(ICRSConstant.REG_QULIFIED_FOR_CANDIDACY));
                    //update for case1
                    if (validationCase.equals(ICRSConstant.UPDATE_CASE_LRG_QUAL_DATE)) {
                        jobSeekersDTO.setRegStatusDTO(regStatusDTO);
                        CrsClientFactory.getJobSeekersClient().update(jobSeekersDTO);
                        //get registration period with start date < qualification date
                        IRegistrationPeriodsDTO regPeriodDTO =
                            CrsClientFactory.getRegistrationPeriodsClient().getRegPeriodforPersonQualification(personQualificationsDTO.getQualificationDate());
                        // add new jobseeker with the same data and with reg status REG_QULIFIED_FOR_CANDIDACY
                        if (regPeriodDTO !=
                            null) { //Check if the job seeker already register on theat central employment period before ( Added by Amir Nasr )
                            JobSeekersDTO newJobSeekersDTO = null;
                            try {
                                newJobSeekersDTO =
                                        (JobSeekersDTO)CrsClientFactory.getJobSeekersClient().getById(new JobSeekersEntityKey(civilId,
                                                                                                                              ((RegistrationPeriodsEntityKey)regPeriodDTO.getCode()).getRegperiodCode()));
                                newJobSeekersDTO.setActiveFlag(ISystemConstant.ACTIVE_FLAG);
                                CrsClientFactory.getJobSeekersClient().update(newJobSeekersDTO);
                            } catch (ItemNotFoundException e) {
                                newJobSeekersDTO = new JobSeekersDTO();
                                newJobSeekersDTO.setRegistrationPeriodsDTO(regPeriodDTO);
                                newJobSeekersDTO.setActiveFlag(ISystemConstant.ACTIVE_FLAG);
                                newJobSeekersDTO.setJobSeekerOrder(jobSeekersDTO.getJobSeekerOrder());
                                newJobSeekersDTO.setKwtCitizensResidentsDTO(jobSeekersDTO.getKwtCitizensResidentsDTO());
                                newJobSeekersDTO.setLastCancelDate(jobSeekersDTO.getLastCancelDate());
                                newJobSeekersDTO.setLastReRegDate(jobSeekersDTO.getLastReRegDate());
                                newJobSeekersDTO.setRegDate(jobSeekersDTO.getRegDate());
                                newJobSeekersDTO.setRegRefrence(jobSeekersDTO.getRegRefrence());
                                newJobSeekersDTO.setRegStatusDTO(qualifiedRegStatusDTO);
                                newJobSeekersDTO.setTrySourceDTO(jobSeekersDTO.getTrySourceDTO());
                                CrsClientFactory.getJobSeekersClient().add(newJobSeekersDTO);
                            }
                        }
                    } //update for case2
                    else if (validationCase.equals(ICRSConstant.UPDATE_CASE_DIFF_EDU_LEVEL)) {
                        jobSeekersDTO.setRegStatusDTO(regStatusDTO);
                        CrsClientFactory.getJobSeekersClient().update(jobSeekersDTO);
                    } //update for case3
                    else if (validationCase.equals(ICRSConstant.UPDATE_CASE_SAME_EDU_LEVEL)) {
                        if (((RegStatusEntityKey)jobSeekersDTO.getRegStatusDTO().getCode()).getRegstatusCode().equals(ICRSConstant.REG_CANDIDATE) ||
                            ((RegStatusEntityKey)jobSeekersDTO.getRegStatusDTO().getCode()).getRegstatusCode().equals(ICRSConstant.REG_CANDIDATE_REJECT)) {
                            jobSeekersDTO.setRegStatusDTO(qualifiedRegStatusDTO);
                            CrsClientFactory.getJobSeekersClient().update(jobSeekersDTO);
                        }
                    }
                }
            }
            Long oldValue = personQualificationsDTO.getCrsRegistrationOrder();
            personQualificationsDTO.setCrsRegistrationOrder(1L);
            super.update(ri, personQualificationsDTO);
            if (defaultPersonQual != null) {
                ((IPersonQualificationsDTO)defaultPersonQual).setCrsRegistrationOrder(oldValue);
                super.update(ri, defaultPersonQual);
            }
            commitTransaction();
            return Boolean.TRUE;
        } catch (ItemNotFoundException e) {
            rollbackTransaction();
            throw new InvalidDataEntryException();
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            rollbackTransaction();

        }
        return null;
    }

    public Boolean update(IRequestInfoDTO ri, IBasicDTO personQualificationsDTO1) throws SharedApplicationException,
                                                                                         DataBaseException {

        IPersonQualificationsDTO personQualificationsDTO = (IPersonQualificationsDTO)personQualificationsDTO1;
        InfGradeValuesDAO gradeValuesDAO = (InfGradeValuesDAO)(super.newDAOInstance(InfGradeValuesEntity.class));
        Double finalDegree = 0D;
        Long gradeValue = 0L;
        Long gradeTypesCode = personQualificationsDTO.getGradeTypeCode();
        if (gradeTypesCode != null && personQualificationsDTO.getGradeValue() != null &&
            (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_LATIN) ||
             gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_LITERAL))) {
            IInfGradeValuesDTO gradeValuesDTO = null;
            try {
                gradeValuesDTO =
                        (IInfGradeValuesDTO)gradeValuesDAO.getById(InfEntityKeyFactory.createInfGradeValuesEntityKey(gradeTypesCode,
                                                                                                                     personQualificationsDTO.getGradeValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (gradeValuesDTO == null) {
                throw new ItemNotFoundException();
            }
            gradeValue = gradeValuesDTO.getPercentageValue();
        }

        Double qulDegree = personQualificationsDTO.getQualificationDegree();
        DecimalFormat df = new DecimalFormat("#.###");
        if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_PERCENTAGE)) {
            if (qulDegree < 0 || qulDegree > 100) {
                throw new InvalidDataEntryException();
            }
            finalDegree = qulDegree;
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_LATIN) ||
                   gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_LITERAL)) {
            finalDegree = gradeValue.doubleValue();
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_POINT_FIVE)) {
            if (qulDegree < 0 || qulDegree > 5) {
                throw new InvalidDataEntryException();
            }
            Double number = 65 + ((qulDegree - 1) * 35 / 4);
            finalDegree = Double.valueOf(df.format(number));
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_POINT_FOUR)) {
            if (qulDegree < 0 || qulDegree > 4) {
                throw new InvalidDataEntryException();
            }
            Double number = 65 + ((qulDegree - 1) * 35 / 3);
            finalDegree = Double.valueOf(df.format(number));
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_POINT_NINE)) {
            if (qulDegree < 0 || qulDegree > 9) {
                throw new InvalidDataEntryException();
            }
            Double number = 65 + ((qulDegree - 1) * 35 / 8);
            finalDegree = Double.valueOf(df.format(number));
        }

        personQualificationsDTO.setQualificationDegree(finalDegree);

        return DAO().update(personQualificationsDTO);


    }

    private Double calculateDegree(IRequestInfoDTO ri,
                                   IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException,
                                                                                            SharedApplicationException {

        InfGradeValuesDAO gradeValuesDAO = (InfGradeValuesDAO)(super.newDAOInstance(InfGradeValuesEntity.class));
        InfGradeTypesDAO gradeTypesDAO = (InfGradeTypesDAO)(super.newDAOInstance(InfGradeTypesEntity.class));
        Double finalDegree = 0D;
        Long percentageValue = 0L;
        Long gradeTypesCode = Long.valueOf(personQualificationsDTO.getGradeTypeDto().getCode().getKey());
        Double qulDegree = personQualificationsDTO.getQualificationDegree();
        //DecimalFormat df = new DecimalFormat("#.###");
        if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_PERCENTAGE)) {
            if (qulDegree < 0 || qulDegree > 100) {
                throw new InvalidDataEntryException();
            }
            finalDegree = qulDegree;
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_LATIN) ||
                   gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_LITERAL)) {
            if (personQualificationsDTO.getGradeTypeDto().getCode() != null) {
                IInfGradeValuesDTO gradeValuesDTO = null;
                try {
                    gradeValuesDTO =
                            (IInfGradeValuesDTO)gradeValuesDAO.getById(InfEntityKeyFactory.createInfGradeValuesEntityKey(gradeTypesCode,
                                                                                                                         personQualificationsDTO.getGradeValue()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                percentageValue = gradeValuesDTO.getPercentageValue();
            }
            finalDegree = percentageValue.doubleValue();
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_POINT_FIVE)) {
            if (qulDegree < 0 || qulDegree > 5) {
                throw new InvalidDataEntryException();
            }
            finalDegree = gradeTypesDAO.getFormulaByGradeType(gradeTypesCode, personQualificationsDTO.getGradeValue());
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_POINT_FOUR)) {
            if (qulDegree < 0 || qulDegree > 4) {
                throw new InvalidDataEntryException();
            }
            finalDegree = gradeTypesDAO.getFormulaByGradeType(gradeTypesCode, personQualificationsDTO.getGradeValue());
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_POINT_NINE)) {
            if (qulDegree < 0 || qulDegree > 9) {
                throw new InvalidDataEntryException();
            }
            finalDegree = gradeTypesDAO.getFormulaByGradeType(gradeTypesCode, personQualificationsDTO.getGradeValue());
        }
        return finalDegree;
    }

    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO personQualificationsDTO1) throws DataBaseException,
                                                                                        SharedApplicationException {
        IPersonQualificationsDTO personQualificationsDTO = (IPersonQualificationsDTO)personQualificationsDTO1;
        Double finalDegree = 0D;
        if (personQualificationsDTO.getCrsRegistrationOrder() == null) {
            if (personQualificationsDTO.getKwtCitizensResidentsDTO().getPersonQualificationsDTOList() != null &&
                personQualificationsDTO.getKwtCitizensResidentsDTO().getPersonQualificationsDTOList().size() > 0) {
                personQualificationsDTO.setCrsRegistrationOrder(0L);
            } else {
                personQualificationsDTO.setCrsRegistrationOrder(1L);

            }
        }


        if (personQualificationsDTO.getQualificationDegree() == null) {
            finalDegree = calculateDegree(ri, personQualificationsDTO);
            personQualificationsDTO.setQualificationDegree(finalDegree);
        }
        return DAO().add(personQualificationsDTO);
    }


    public Boolean remove(IRequestInfoDTO ri, IBasicDTO personQualificationsDTO1) throws SharedApplicationException,
                                                                                         DataBaseException { /*CR HR-385
        * cannot delete person qualification with crsRegistrationOrder 1 */
        IPersonQualificationsDTO personQualificationsDTO = (IPersonQualificationsDTO)personQualificationsDTO1;
        if (personQualificationsDTO.getCrsRegistrationOrder() != null &&
            personQualificationsDTO.getCrsRegistrationOrder().equals(1L)) {
            throw new CrsPersonQualificationException();
        }
        return super.remove(ri, personQualificationsDTO);
    }

    /**
     * * @param list
     * @return
     */
    public List<IResultDTO> remove(IRequestInfoDTO ri, List<IBasicDTO> list) throws DataBaseException {
        List resultList = new ArrayList();
        for (IBasicDTO personQualificationsDTO : list) {
            try {
                beginTransaction();
                remove(ri, personQualificationsDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(personQualificationsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (ItemNotFoundException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(personQualificationsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (CrsPersonQualificationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(personQualificationsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setBusinessException(e);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(personQualificationsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(personQualificationsDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                resultList.add(resultDTO);
            }
        }
        return resultList;
    }

    public Boolean updateRegisterationOrderCMT(IRequestInfoDTO ri,
                                               IPersonQualificationsDTO personQualificationsDTO) throws SharedApplicationException,
                                                                                                        DataBaseException {
        System.out.println("INF.PersonQulifications.updateRegisterationOrderCMT ::Starting ");
        try {
            IKwtCitizensResidentsDTO citizenDTO = personQualificationsDTO.getKwtCitizensResidentsDTO();
            System.out.println(" personQualificationsDTO.getKwtCitizensResidentsDTO() ::Starting ");
            Long civilId = ((IKwtCitizensResidentsEntityKey)citizenDTO.getCode()).getCivilId();
            System.out.println(" ((IKwtCitizensResidentsEntityKey)citizenDTO.getCode()).getCivilId() ::Starting ");
            IBasicDTO defaultPersonQual = null;
            try {
                System.out.println(" personQualificationsDAO.getCentralEmpPersonQul ::Starting ");
                defaultPersonQual = DAO().getCentralEmpPersonQul(civilId);
                System.out.println(" personQualificationsDAO.getCentralEmpPersonQul ::End ");
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
            }

            if (defaultPersonQual != null &&
                defaultPersonQual.getCode().getKey().toString().equals(personQualificationsDTO.getCode().getKey().toString())) {
                return true;
            }

            if (defaultPersonQual != null) { //swap registration order
                System.out.println(" Updating Default Qualifictaion ::Starting ");
                ((IPersonQualificationsDTO)defaultPersonQual).setCrsRegistrationOrder(null);
                super.update(defaultPersonQual);
                System.out.println(" Updating Default Qualifictaion ::End ");
            }
            System.out.println(" Updating Submitted Qualifictaion ::Starting ");
            personQualificationsDTO.setCrsRegistrationOrder(1L);
            super.update(personQualificationsDTO);
            System.out.println(" Updating Submitted Qualifictaion ::End ");

            return Boolean.TRUE;
        } catch (ItemNotFoundException e) {
            rollbackTransaction();
            throw new InvalidDataEntryException();
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            rollbackTransaction();
        }
        return null;
    }


    public IBasicDTO getCurrentCentralEmpPersonQul(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                     SharedApplicationException {

        return DAO().getCurrentCentralEmpPersonQul(civilId);

}


}


