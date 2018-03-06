package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dao.DAOFactoryUtil;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.client.IPersonQualificationsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dao.InfGradeTypesDAO;
import com.beshara.csc.inf.business.dao.InfGradeValuesDAO;
import com.beshara.csc.inf.business.dao.PersonQualificationsDAO;
import com.beshara.csc.inf.business.dao.PersonsInformationDAO;
import com.beshara.csc.inf.business.dto.IInfGradeValuesDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.inf.business.dto.PersonQualificationsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntity;
import com.beshara.csc.inf.business.entity.InfGradeValuesEntity;
import com.beshara.csc.inf.business.entity.PersonQualificationsEntity;
import com.beshara.csc.inf.business.entity.PersonsInformationEntity;
import com.beshara.csc.inf.business.shared.IInfConstant;
import com.beshara.csc.nl.qul.business.client.QulClientFactory;
import com.beshara.csc.nl.qul.business.dto.ICenterQualificationsDTO;
import com.beshara.csc.nl.qul.business.entity.QualificationsEntityKey;
import com.beshara.csc.nl.qul.business.entity.QulEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemCanNotBeDeletedException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ICRSConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.cementj.base.trans.TransactionException;


@Stateless(name = "PersonsInformationSession", mappedName = "Inf-Model-PersonsInformationSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(PersonsInformationSession.class)
public class PersonsInformationSessionBean extends InfBaseSessionBean implements PersonsInformationSession {


    public PersonsInformationSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return PersonsInformationEntity.class;
    }

    @Override
    protected PersonsInformationDAO DAO() {
        return (PersonsInformationDAO)super.DAO();
    }

    public String getSocietyCodeByCenter(IRequestInfoDTO ri, String centerCode) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().getSocietyCodeByCenter(centerCode);

    }

    public List<IBasicDTO> getAll(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                           SharedApplicationException {
        return DAO().getAll(civilId);

    }

    public IBasicDTO getQulDto(IRequestInfoDTO ri,
                               ICenterQualificationsDTO centerQualificationsDTO) throws DataBaseException,
                                                                                        SharedApplicationException {
        String qualKey = "";
        qualKey = DAO().getQualKey(centerQualificationsDTO);
        if (!qualKey.equals("null")) {
            try {

                IEntityKey key = QulEntityKeyFactory.createQualificationsEntityKey(Long.valueOf(qualKey));
                return QulClientFactory.getQualificationsClient().getById(key);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private Double calculateDegree(IPersonsInformationDTO personsInformationDTO) throws DataBaseException,
                                                                                        SharedApplicationException {
        Double finalDegree = 0D;
        Long percentageValue = 0L;
        Long gradeTypesCode = Long.valueOf(personsInformationDTO.getGradeTypeDto().getCode().getKey());

        Double qulDegree = personsInformationDTO.getDegree();
        InfGradeTypesDAO infGradeTypesDAO = (InfGradeTypesDAO)newDAOInstance(InfGradeTypesEntity.class);
        // DecimalFormat df = new DecimalFormat("#.###");
        if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_PERCENTAGE)) {
            if (qulDegree < 0 || qulDegree > 100) {
                throw new InvalidDataEntryException();
            }
            finalDegree = qulDegree;
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_LATIN) ||
                   gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_LITERAL)) {
            if (personsInformationDTO.getGradeTypeDto().getCode() != null) {
                IInfGradeValuesDTO gradeValuesDTO = null;
                try {


                    gradeValuesDTO =
                            (IInfGradeValuesDTO)((InfGradeValuesDAO)newDAOInstance(InfGradeValuesEntity.class)).getById(InfEntityKeyFactory.createInfGradeValuesEntityKey(gradeTypesCode,
                                                                                                                                                                          personsInformationDTO.getGradeValue()));
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
            finalDegree =
                    infGradeTypesDAO.getFormulaByGradeType(gradeTypesCode, personsInformationDTO.getGradeValue());
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_POINT_FOUR)) {
            if (qulDegree < 0 || qulDegree > 4) {
                throw new InvalidDataEntryException();
            }
            finalDegree =
                    infGradeTypesDAO.getFormulaByGradeType(gradeTypesCode, personsInformationDTO.getGradeValue());
        } else if (gradeTypesCode.equals(ICRSConstant.GRADE_TYPE_POINT_NINE)) {
            if (qulDegree < 0 || qulDegree > 9) {
                throw new InvalidDataEntryException();
            }
            finalDegree =
                    infGradeTypesDAO.getFormulaByGradeType(gradeTypesCode, personsInformationDTO.getGradeValue());
        }
        return finalDegree;
    }

    public IBasicDTO addCMT(IRequestInfoDTO ri, IBasicDTO personsInformationDTO1) throws DataBaseException,
                                                                                         SharedApplicationException {
        IPersonsInformationDTO personsInformationDTO = (IPersonsInformationDTO)personsInformationDTO1;
        Double finalDegree = 0D;
        try {
            if (personsInformationDTO.getPersonQualificationsDTO() != null &&
                ((QualificationsEntityKey)personsInformationDTO.getPersonQualificationsDTO().getQualificationsDTO().getCode()).getQualificationKey() !=
                null) {
                IPersonQualificationsClient personQualificationsClient =
                    InfClientFactory.getPersonQualificationsClient();

                personsInformationDTO.setPersonQualificationsDTO((IPersonQualificationsDTO)personQualificationsClient.add(personsInformationDTO.getPersonQualificationsDTO()));
            }
            if (personsInformationDTO.getDegree() == null) {
                finalDegree = calculateDegree(personsInformationDTO);
                personsInformationDTO.setDegree(finalDegree);
            }
            personsInformationDTO = (IPersonsInformationDTO)DAO().add(personsInformationDTO);
            return personsInformationDTO;
        } catch (ItemNotFoundException e) {
            throw new InvalidDataEntryException();
        } catch (SharedApplicationException e) {
            throw e;
        } catch (TransactionException e) {
            throw e;
        }
    }

    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO personsInformationDTO1) throws DataBaseException,
                                                                                      SharedApplicationException {
        IPersonsInformationDTO personsInformationDTO = (IPersonsInformationDTO)personsInformationDTO1;
        Double finalDegree = 0D;
        try {
            if (personsInformationDTO.getPersonQualificationsDTO() != null &&
                ((QualificationsEntityKey)personsInformationDTO.getPersonQualificationsDTO().getQualificationsDTO().getCode()).getQualificationKey() !=
                null) {
                IPersonQualificationsClient personQualificationsClient =
                    InfClientFactory.getPersonQualificationsClient();
                personsInformationDTO.setPersonQualificationsDTO((IPersonQualificationsDTO)personQualificationsClient.add(personsInformationDTO.getPersonQualificationsDTO()));
            }
            finalDegree = calculateDegree(personsInformationDTO);
            personsInformationDTO.setDegree(finalDegree);
            personsInformationDTO = (IPersonsInformationDTO)DAO().add(personsInformationDTO);
            //personsInformationDAO.getById(personsInformationDTO.getCode());
            return personsInformationDTO;
        } catch (DataBaseException e) {
            throw e;
        } catch (SharedApplicationException e) {
            throw e;
        }
    }

    public String getQualKey(IRequestInfoDTO ri,
                             ICenterQualificationsDTO centerQualificationsDTO) throws DataBaseException,
                                                                                      SharedApplicationException {
        return DAO().getQualKey(centerQualificationsDTO);
    }

    public List<IResultDTO> removePersonInfoAndPersonQual(IRequestInfoDTO iRequestInfoDTO,
                                                    List<IBasicDTO> list) throws DataBaseException,
                                                                                 SharedApplicationException {
        List resultList = new ArrayList();
        PersonQualificationsDAO personQualificationsDAO =
            (PersonQualificationsDAO)DAOFactoryUtil.getInstance(PersonQualificationsEntity.class);

        for (IBasicDTO dto : list) {
            IPersonsInformationDTO personInfo = (IPersonsInformationDTO)dto;
            try {

                if (DAO().remove(dto) &&
                    (personInfo.getPersonQualificationsDTO() != null && personInfo.getPersonQualificationsDTO().getCode() !=
                     null)) {
                    personQualificationsDAO.remove(personInfo.getPersonQualificationsDTO());
}
                IResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(dto);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (ItemCanNotBeDeletedException e) {
                IResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(dto);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultList.add(resultDTO);
                //                    rollbackTransaction();
            } catch (SharedApplicationException e) {
                IResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(dto);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                //                    rollbackTransaction();
            } catch (DataBaseException e) {
                IResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(dto);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                resultList.add(resultDTO);
            }


        }

        return resultList;
    }

    public List<IBasicDTO> getQualForEmp(IRequestInfoDTO ri, Long civilID,
                                         boolean currentQul) throws DataBaseException, SharedApplicationException {
        return DAO().getQualForEmp(civilID, currentQul);
}


    public Boolean checkQualificationForPerson(IRequestInfoDTO ri, Long realCivil, Long centerCode,
                                               String centerQualCode) throws SharedApplicationException,
                                                                             DataBaseException {
        return DAO().checkQualificationForPerson(realCivil, centerCode, centerQualCode);

    }
    
    
    /**
     * @ author Ahmed Mamdouh
     */
    public IPersonsInformationDTO updatePersonQualificationAndInformation(IRequestInfoDTO ri,IBasicDTO dto) throws DataBaseException,SharedApplicationException{

        Double finalDegree = 0D;
        IPersonsInformationDTO personsInformationDTO = (IPersonsInformationDTO)dto;
        //IPersonQualificationsClient QualficationClient = InfClientFactory.getPersonQualificationsClient();
        PersonQualificationsDAO personQualificationsDAO =
            (PersonQualificationsDAO)newDAOInstance(PersonQualificationsEntity.class);

        //geting the last qulification for that person
        IKwtCitizensResidentsEntityKey kwtCitizensResidentsEntityKey =
        InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(personsInformationDTO.getKwtCitizensResidentsDTO().getCivilId());
        IPersonQualificationsDTO personQualificationsDTO =
            (IPersonQualificationsDTO)personQualificationsDAO.getLastPersonQualification(kwtCitizensResidentsEntityKey);

        // updating the last qulification current_Qual = 0
        personQualificationsDTO.setCurrentQual(IInfConstant.CURRENT_QAULIFICATION_STATUS_ZERO);
        personQualificationsDAO.update(personQualificationsDTO);
        

        try {
            if (personsInformationDTO.getPersonQualificationsDTO() != null &&
                ((QualificationsEntityKey)personsInformationDTO.getPersonQualificationsDTO().getQualificationsDTO().getCode()).getQualificationKey() !=
                null) {
               
                //*******************************************************************************
                // adding new records in INF_PERSONS_INFORMATION & INF_PERSON_QUALIFICATIONS and setting current_Qual = 1
                
                PersonQualificationsDTO personQualificationsDTOAdd = (PersonQualificationsDTO)personsInformationDTO.getPersonQualificationsDTO();
                personQualificationsDTOAdd.setCurrentQual(IInfConstant.CURRENT_QAULIFICATION_STATUS_ONE);
                if (personQualificationsDTOAdd.getCrsRegistrationOrder() == null) {
                    if (personQualificationsDTOAdd.getKwtCitizensResidentsDTO().getPersonQualificationsDTOList() != null &&
                        personQualificationsDTOAdd.getKwtCitizensResidentsDTO().getPersonQualificationsDTOList().size() > 0) {
                        personQualificationsDTOAdd.setCrsRegistrationOrder(0L);
                    } else {
                        personQualificationsDTOAdd.setCrsRegistrationOrder(1L);

                    }
                }

                if (personQualificationsDTOAdd.getQualificationDegree() == null) {
                    finalDegree = calculateDegree(ri, personQualificationsDTOAdd);
                    personQualificationsDTOAdd.setQualificationDegree(finalDegree);
                }
                personsInformationDTO.setPersonQualificationsDTO((IPersonQualificationsDTO)personQualificationsDAO.add(personQualificationsDTOAdd));
                
                //*******************************************************************************
            }
            finalDegree = calculateDegree(personsInformationDTO);
            personsInformationDTO.setDegree(finalDegree);
            personsInformationDTO = (IPersonsInformationDTO)DAO().add(personsInformationDTO);

            IPersonsInformationDTO personsInformationDTO2 = (IPersonsInformationDTO)DAO().add(personsInformationDTO);

            return personsInformationDTO2;
        } catch (DataBaseException e) {
            throw e;
        } catch (SharedApplicationException e) {
            throw e;
        }
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
}
