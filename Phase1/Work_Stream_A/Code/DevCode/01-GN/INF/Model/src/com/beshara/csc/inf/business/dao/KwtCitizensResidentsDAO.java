package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsSearchDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.inf.business.dto.IRequestDTO;
import com.beshara.csc.inf.business.dto.ISeekerLanguageSkillsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.GenderTypesEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.HandicapStatusEntity;
import com.beshara.csc.inf.business.entity.HandicapStatusEntityKey;
import com.beshara.csc.inf.business.entity.IGenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.IHandicapStatusEntityKey;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.IMaritalStatusEntityKey;
import com.beshara.csc.inf.business.entity.IReligionsEntityKey;
import com.beshara.csc.inf.business.entity.ISpecialCaseTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.KwMapEntity;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.KwtWorkDataEntity;
import com.beshara.csc.inf.business.entity.MaritalStatusEntity;
import com.beshara.csc.inf.business.entity.MaritalStatusEntityKey;
import com.beshara.csc.inf.business.entity.PersonQualificationsEntity;
import com.beshara.csc.inf.business.entity.PersonsInformationEntity;
import com.beshara.csc.inf.business.entity.ReligionsEntity;
import com.beshara.csc.inf.business.entity.ReligionsEntityKey;
import com.beshara.csc.inf.business.entity.SeekerLanguageSkillsEntity;
import com.beshara.csc.inf.business.entity.SpecialCaseTypesEntity;
import com.beshara.csc.inf.business.facade.InfEntityConvertr;
import com.beshara.csc.inf.business.shared.IInfConstant;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


public class KwtCitizensResidentsDAO extends InfBaseDAO {
    public KwtCitizensResidentsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new KwtCitizensResidentsDAO();
    }

    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)id);
            if (kwtCitizensResidentsEntity == null) {
                if(id!= null)
                    System.out.println("Citizne not found " + id.getKey());
                else
                    System.out.println("Id is null");
                throw new ItemNotFoundException();
            }
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                InfDTOFactory.createKwtCitizensResidentsDTO(kwtCitizensResidentsEntity);
            //Set kwtWorkDataList
            List<IKwtWorkDataDTO> kwtWorkDataList = new ArrayList<IKwtWorkDataDTO>();
            for (KwtWorkDataEntity entity :
                 (List<KwtWorkDataEntity>)EM().createNamedQuery("KwtWorkDataEntity.getByCivilId").setParameter("civilId",
                                                                                                               kwtCitizensResidentsEntity.getCivilId()).setHint("toplink.refresh",
                                                                                                                                                                "true").getResultList()) {
                kwtWorkDataList.add(InfDTOFactory.createKwtWorkDataDTO(entity));
            }
            kwtCitizensResidentsDTO.setKwtWorkDataDTOList(kwtWorkDataList);
            //Set personQualificationsList
            List<IPersonQualificationsDTO> personQualificationsList = new ArrayList<IPersonQualificationsDTO>();
            for (PersonQualificationsEntity entity :
                 (List<PersonQualificationsEntity>)EM().createNamedQuery("PersonQualificationsEntity.getByCivilId").setParameter("civilId",
                                                                                                                                 kwtCitizensResidentsEntity.getCivilId()).setHint("toplink.refresh",
                                                                                                                                                                                  "true").getResultList()) {
                personQualificationsList.add(InfDTOFactory.createPersonQualificationsDTO(entity));
            }

            kwtCitizensResidentsDTO.setPersonQualificationsDTOList(personQualificationsList);
            //Set PersonsInformationList
            List<IPersonsInformationDTO> personsInformationList = new ArrayList<IPersonsInformationDTO>();
            for (PersonsInformationEntity entity :
                 (List<PersonsInformationEntity>)EM().createNamedQuery("PersonsInformationEntity.getByCivilId").setParameter("civilId",
                                                                                                                             kwtCitizensResidentsEntity.getCivilId()).setHint("toplink.refresh",
                                                                                                                                                                              "true").getResultList()) {
                personsInformationList.add(InfDTOFactory.createPersonsInformationDTO(entity));
            }
            kwtCitizensResidentsDTO.setPersonsInformationDTOList(personsInformationList);
            //Set seekerLanguageSkillsList
            List<ISeekerLanguageSkillsDTO> seekerLanguageSkillsList = new ArrayList<ISeekerLanguageSkillsDTO>();
            for (SeekerLanguageSkillsEntity entity :
                 (List<SeekerLanguageSkillsEntity>)EM().createNamedQuery("SeekerLanguageSkillsEntity.getByCivilId").setParameter("civilId",
                                                                                                                                 kwtCitizensResidentsEntity.getCivilId()).setHint("toplink.refresh",
                                                                                                                                                                                  "true").getResultList()) {
                seekerLanguageSkillsList.add(InfDTOFactory.createSeekerLanguageSkillsDTO(entity));
            }
            kwtCitizensResidentsDTO.setSeekerLanguageSkillsDTOList(seekerLanguageSkillsList);

            return kwtCitizensResidentsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<KwtCitizensResidentsEntity> list =
                EM().createNamedQuery("KwtCitizensResidentsEntity.findAll").setMaxResults(100).setHint("toplink.refresh",
                                                                                                       "true").getResultList();
            for (KwtCitizensResidentsEntity kwtCitizensResidents : list) {
                arrayList.add(InfDTOFactory.createKwtCitizensResidentsDTO(kwtCitizensResidents));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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

    public IBasicDTO add(IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)kwtCitizensResidentsDTO1;
            if(kwtCitizensResidentsDTO!= null && kwtCitizensResidentsDTO.getCode()!=null)
                System.out.println("INF: add citizen with code " + kwtCitizensResidentsDTO.getCode().getKey());
            else
                System.out.println("DTO or code is null");
            
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity = new KwtCitizensResidentsEntity();
            kwtCitizensResidentsEntity.setCivilId(kwtCitizensResidentsDTO.getCivilId());
            kwtCitizensResidentsEntity.setFirstName(kwtCitizensResidentsDTO.getFirstName());
            kwtCitizensResidentsEntity.setSecondName(kwtCitizensResidentsDTO.getSecondName());
            kwtCitizensResidentsEntity.setThirdName(kwtCitizensResidentsDTO.getThirdName());
            kwtCitizensResidentsEntity.setLastName(kwtCitizensResidentsDTO.getLastName());
            kwtCitizensResidentsEntity.setFamilyName(kwtCitizensResidentsDTO.getFamilyName());
            kwtCitizensResidentsEntity.setEnglishName(kwtCitizensResidentsDTO.getEnglishName());
            kwtCitizensResidentsEntity.setBirthDate(kwtCitizensResidentsDTO.getBirthDate());
            //Commented Now By Amir Nasr
            //kwtCitizensResidentsEntity.setGentypeCode ( kwtCitizensResidentsDTO.getGentypeCode ( ) ) ;
            //kwtCitizensResidentsEntity.setMarstatusCode ( kwtCitizensResidentsDTO.getMarstatusCode ( ) ) ;
            //        if (kwtCitizensResidentsDTO.getGenderTypesDTO() != null) {
            //            GenderTypesEntity genderTypesEntity =
            //                em.find(GenderTypesEntity.class, kwtCitizensResidentsDTO.getCountriesDTO().getCode());
            //            kwtCitizensResidentsEntity.setGenderTypesEntity(genderTypesEntity);
            //        } else {
            //            throw new ItemNotFoundException();
            //        }
            if (kwtCitizensResidentsDTO.getGenderTypesDTO() != null) {
                GenderTypesEntity genderTypesEntity =
                    EM().find(GenderTypesEntity.class, kwtCitizensResidentsDTO.getGenderTypesDTO().getCode());
                kwtCitizensResidentsEntity.setGenderTypesEntity(genderTypesEntity);
            } else {
                try{
                    System.out.println("INF: GenderType not found " + kwtCitizensResidentsDTO.getGenderTypesDTO().getCode().getKey());   
                }catch (Exception e){
                    System.out.println("GenderType NULL");
                }
                throw new ItemNotFoundException();
            }

            if (kwtCitizensResidentsDTO.getMaritalStatusDTO() != null) {
                MaritalStatusEntity maritalStatusEntity =
                    EM().find(MaritalStatusEntity.class, kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode());
                kwtCitizensResidentsEntity.setMaritalStatusEntity(maritalStatusEntity);
            } else {
                try{
                    System.out.println("INF: MaritalStatus not found " + kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode().getKey());
                }catch (Exception e){
                    System.out.println("MaritalStatus NULL"); 
                }
                
                throw new ItemNotFoundException();
            }

            if (kwtCitizensResidentsDTO.getHandicapStatusDTO() != null) {
                HandicapStatusEntity handicapStatusEntity =
                    EM().find(HandicapStatusEntity.class, kwtCitizensResidentsDTO.getHandicapStatusDTO().getCode());
                kwtCitizensResidentsEntity.setHandicapStatusEntity(handicapStatusEntity);
            } else {
                try{
                    System.out.println("INF: HandicapStatus not found " + kwtCitizensResidentsDTO.getHandicapStatusDTO().getCode().getKey());
                }catch (Exception e){
                    System.out.println("HandicapStatus NULL"); 
                }
                throw new ItemNotFoundException();
            }

            if (kwtCitizensResidentsDTO.getReligionsDTO() != null) {
                ReligionsEntity religionsEntity =
                    EM().find(ReligionsEntity.class, kwtCitizensResidentsDTO.getReligionsDTO().getCode());
                kwtCitizensResidentsEntity.setReligionsEntity(religionsEntity);
            } else {
                try{
                    System.out.println("INF: Religions not found " + kwtCitizensResidentsDTO.getReligionsDTO().getCode().getKey());
                }catch (Exception e){
                    System.out.println("Religions NULL"); 
                }
                throw new ItemNotFoundException();
            }

            kwtCitizensResidentsEntity.setReligionCode(kwtCitizensResidentsDTO.getReligionCode());
            kwtCitizensResidentsEntity.setNationality(kwtCitizensResidentsDTO.getNationality());
            kwtCitizensResidentsEntity.setNationalityDate(kwtCitizensResidentsDTO.getNationalityDate());
            kwtCitizensResidentsEntity.setPhonesNo(kwtCitizensResidentsDTO.getPhonesNo());
            kwtCitizensResidentsEntity.setMobileNo(kwtCitizensResidentsDTO.getMobileNo());
            kwtCitizensResidentsEntity.setEMail(kwtCitizensResidentsDTO.getEMail());
            kwtCitizensResidentsEntity.setRestypeCode(kwtCitizensResidentsDTO.getRestypeCode());
            kwtCitizensResidentsEntity.setPassportCntryCode(kwtCitizensResidentsDTO.getPassportCntryCode());
            kwtCitizensResidentsEntity.setPassportNo(kwtCitizensResidentsDTO.getPassportNo());
            kwtCitizensResidentsEntity.setPassportIssueDate(kwtCitizensResidentsDTO.getPassportIssueDate());
            kwtCitizensResidentsEntity.setPassportExpiredDate(kwtCitizensResidentsDTO.getPassportExpiredDate());
            kwtCitizensResidentsEntity.setMapCode(kwtCitizensResidentsDTO.getMapCode());
            kwtCitizensResidentsEntity.setStreetCode(kwtCitizensResidentsDTO.getStreetCode());
            kwtCitizensResidentsEntity.setBuildingNo(kwtCitizensResidentsDTO.getBuildingNo());
            kwtCitizensResidentsEntity.setFloorNo(kwtCitizensResidentsDTO.getFloorNo());
            kwtCitizensResidentsEntity.setFlatNo(kwtCitizensResidentsDTO.getFlatNo());
            kwtCitizensResidentsEntity.setAddressInDetails(kwtCitizensResidentsDTO.getAddressInDetails());

            kwtCitizensResidentsEntity.setBldgroupCode(kwtCitizensResidentsDTO.getBldgroupCode());
            kwtCitizensResidentsEntity.setMltstatusCode(kwtCitizensResidentsDTO.getMltstatusCode());
            kwtCitizensResidentsEntity.setDeathDate(kwtCitizensResidentsDTO.getDeathDate());
            kwtCitizensResidentsEntity.setEndResidentDate(kwtCitizensResidentsDTO.getEndResidentDate());
            kwtCitizensResidentsEntity.setCapstatusCode(kwtCitizensResidentsDTO.getCapstatusCode());
            kwtCitizensResidentsEntity.setSpccsetypeCode(kwtCitizensResidentsDTO.getSpccsetypeCode());
            if (kwtCitizensResidentsDTO.getActiveFlag() != null) {
                kwtCitizensResidentsEntity.setActiveFlag(kwtCitizensResidentsDTO.getActiveFlag());
            } else {
                kwtCitizensResidentsEntity.setActiveFlag(ISystemConstant.ACTIVE_FLAG);
            }
            kwtCitizensResidentsEntity.setMobCompanyCode(kwtCitizensResidentsDTO.getMobCompanyCode());
            kwtCitizensResidentsEntity.setCreatedBy(kwtCitizensResidentsDTO.getCreatedBy());
            kwtCitizensResidentsEntity.setCreatedDate(kwtCitizensResidentsDTO.getCreatedDate());
            kwtCitizensResidentsEntity.setLastUpdatedBy(kwtCitizensResidentsDTO.getLastUpdatedBy());
            kwtCitizensResidentsEntity.setLastUpdatedDate(kwtCitizensResidentsDTO.getLastUpdatedDate());
            kwtCitizensResidentsEntity.setAuditStatus(kwtCitizensResidentsDTO.getAuditStatus());
            kwtCitizensResidentsEntity.setTabrecSerial(kwtCitizensResidentsDTO.getTabrecSerial());

            if (kwtCitizensResidentsDTO.getSendSmsFlag() != null)
            kwtCitizensResidentsEntity.setSendSmsFlag(kwtCitizensResidentsDTO.getSendSmsFlag());
            else
                kwtCitizensResidentsEntity.setSendSmsFlag(ISystemConstant.ACTIVE_FLAG);
            //this.persistEntity ( kwtCitizensResidentsEntity ) ;
            doAdd(kwtCitizensResidentsEntity);
            
            System.out.println("INF: Citizen added successfully");

            return kwtCitizensResidentsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    public Boolean update(IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)kwtCitizensResidentsDTO1;
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode());
            if (kwtCitizensResidentsEntity == null) {
                throw new ItemNotFoundException();
            }
            kwtCitizensResidentsEntity.setCivilId(((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId());
            kwtCitizensResidentsEntity.setFirstName(kwtCitizensResidentsDTO.getFirstName());
            kwtCitizensResidentsEntity.setSecondName(kwtCitizensResidentsDTO.getSecondName());
            kwtCitizensResidentsEntity.setThirdName(kwtCitizensResidentsDTO.getThirdName());
            kwtCitizensResidentsEntity.setLastName(kwtCitizensResidentsDTO.getLastName());
            kwtCitizensResidentsEntity.setFamilyName(kwtCitizensResidentsDTO.getFamilyName());
            kwtCitizensResidentsEntity.setEnglishName(kwtCitizensResidentsDTO.getEnglishName());
            kwtCitizensResidentsEntity.setBirthDate(kwtCitizensResidentsDTO.getBirthDate());
            //Commented Now By Amir Nasr
            //kwtCitizensResidentsEntity.setGentypeCode ( kwtCitizensResidentsDTO.getGentypeCode ( ) ) ;
            //kwtCitizensResidentsEntity.setMarstatusCode ( kwtCitizensResidentsDTO.getMarstatusCode ( ) ) ;
            if (kwtCitizensResidentsDTO.getGenderTypesDTO() != null) {
                GenderTypesEntity genderTypesEntity =
                    EM().find(GenderTypesEntity.class, (IGenderTypesEntityKey)kwtCitizensResidentsDTO.getGenderTypesDTO().getCode());
                kwtCitizensResidentsEntity.setGenderTypesEntity(genderTypesEntity);
            } else {
                throw new ItemNotFoundException();
            }
            if (kwtCitizensResidentsDTO.getMaritalStatusDTO() != null) {
                MaritalStatusEntity maritalStatusEntity =
                    EM().find(MaritalStatusEntity.class, (IMaritalStatusEntityKey)kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode());
                kwtCitizensResidentsEntity.setMaritalStatusEntity(maritalStatusEntity);
            } else {
                throw new ItemNotFoundException();
            }
            if (kwtCitizensResidentsDTO.getHandicapStatusDTO() != null) {
                HandicapStatusEntity handicapStatusEntity =
                    EM().find(HandicapStatusEntity.class, (IHandicapStatusEntityKey)kwtCitizensResidentsDTO.getHandicapStatusDTO().getCode());
                kwtCitizensResidentsEntity.setHandicapStatusEntity(handicapStatusEntity);
            } else {
                throw new ItemNotFoundException();
            }
            if (kwtCitizensResidentsDTO.getReligionsDTO() != null) {
                ReligionsEntity religionsEntity =
                    EM().find(ReligionsEntity.class, (IReligionsEntityKey)kwtCitizensResidentsDTO.getReligionsDTO().getCode());
                kwtCitizensResidentsEntity.setReligionsEntity(religionsEntity);
            } else {
                throw new ItemNotFoundException();
            }

            if (kwtCitizensResidentsDTO.getSpecialCaseTypesDTO() != null) {
                SpecialCaseTypesEntity specialCaseTypesEntity =
                    EM().find(SpecialCaseTypesEntity.class, (ISpecialCaseTypesEntityKey)kwtCitizensResidentsDTO.getSpecialCaseTypesDTO().getCode());
                kwtCitizensResidentsEntity.setSpecialCaseTypesEntity(specialCaseTypesEntity);
            }
            //            else {
            //                throw new ItemNotFoundException();
            //            }

            kwtCitizensResidentsEntity.setReligionCode(kwtCitizensResidentsDTO.getReligionCode());
            kwtCitizensResidentsEntity.setNationality(kwtCitizensResidentsDTO.getNationality());
            kwtCitizensResidentsEntity.setNationalityDate(kwtCitizensResidentsDTO.getNationalityDate());
            kwtCitizensResidentsEntity.setPhonesNo(kwtCitizensResidentsDTO.getPhonesNo());
            kwtCitizensResidentsEntity.setMobileNo(kwtCitizensResidentsDTO.getMobileNo());
            kwtCitizensResidentsEntity.setEMail(kwtCitizensResidentsDTO.getEMail());
            kwtCitizensResidentsEntity.setRestypeCode(kwtCitizensResidentsDTO.getRestypeCode());
            kwtCitizensResidentsEntity.setPassportCntryCode(kwtCitizensResidentsDTO.getPassportCntryCode());
            kwtCitizensResidentsEntity.setPassportNo(kwtCitizensResidentsDTO.getPassportNo());
            kwtCitizensResidentsEntity.setPassportIssueDate(kwtCitizensResidentsDTO.getPassportIssueDate());
            kwtCitizensResidentsEntity.setPassportExpiredDate(kwtCitizensResidentsDTO.getPassportExpiredDate());
            if (kwtCitizensResidentsDTO.getMapCode() != null) {
                kwtCitizensResidentsEntity.setKwMapEntity(EM().find(KwMapEntity.class,
                                                                    InfEntityKeyFactory.createKwMapEntityKey(kwtCitizensResidentsDTO.getMapCode())));
            }
            kwtCitizensResidentsEntity.setMapCode(kwtCitizensResidentsDTO.getMapCode());
            kwtCitizensResidentsEntity.setStreetCode(kwtCitizensResidentsDTO.getStreetCode());
            kwtCitizensResidentsEntity.setBuildingNo(kwtCitizensResidentsDTO.getBuildingNo());
            kwtCitizensResidentsEntity.setFloorNo(kwtCitizensResidentsDTO.getFloorNo());
            kwtCitizensResidentsEntity.setFlatNo(kwtCitizensResidentsDTO.getFlatNo());
            kwtCitizensResidentsEntity.setAddressInDetails(kwtCitizensResidentsDTO.getAddressInDetails());
            //kwtCitizensResidentsEntity.setHuspendCivilId ( kwtCitizensResidentsDTO.getKwtCitizensResidentsDTO ( ) .getc ) ;
            // if ( kwtCitizensResidentsDTO.getKwtCitizensResidentsDTO ( ) !=null ) {
            // KwtCitizensResidentsEntity citizensResidentsEntity = em.find ( KwtCitizensResidentsEntity.class , kwtCitizensResidentsDTO.getKwtCitizensResidentsDTO ( ) .getCode ( ) ) ;
            // if ( citizensResidentsEntity == null ) {
            // throw new ItemNotFoundException ( ) ;
            // } else {
            // kwtCitizensResidentsEntity.setKwtCitizensResidentsEntity ( citizensResidentsEntity ) ;
            // }
            // }
            kwtCitizensResidentsEntity.setBldgroupCode(kwtCitizensResidentsDTO.getBldgroupCode());
            kwtCitizensResidentsEntity.setMltstatusCode(kwtCitizensResidentsDTO.getMltstatusCode());
            kwtCitizensResidentsEntity.setDeathDate(kwtCitizensResidentsDTO.getDeathDate());
            kwtCitizensResidentsEntity.setEndResidentDate(kwtCitizensResidentsDTO.getEndResidentDate());
            kwtCitizensResidentsEntity.setCapstatusCode(kwtCitizensResidentsDTO.getCapstatusCode());
            kwtCitizensResidentsEntity.setSpccsetypeCode(kwtCitizensResidentsDTO.getSpccsetypeCode());
            kwtCitizensResidentsEntity.setActiveFlag(kwtCitizensResidentsDTO.getActiveFlag());
            kwtCitizensResidentsEntity.setMobCompanyCode(kwtCitizensResidentsDTO.getMobCompanyCode());
            kwtCitizensResidentsEntity.setCreatedBy(kwtCitizensResidentsDTO.getCreatedBy());
            kwtCitizensResidentsEntity.setCreatedDate(kwtCitizensResidentsDTO.getCreatedDate());
            kwtCitizensResidentsEntity.setLastUpdatedBy(kwtCitizensResidentsDTO.getLastUpdatedBy());
            kwtCitizensResidentsEntity.setLastUpdatedDate(kwtCitizensResidentsDTO.getLastUpdatedDate());
            kwtCitizensResidentsEntity.setAuditStatus(kwtCitizensResidentsDTO.getAuditStatus());
            kwtCitizensResidentsEntity.setTabrecSerial(kwtCitizensResidentsDTO.getTabrecSerial());
            if (kwtCitizensResidentsDTO.getSendSmsFlag() != null)
            kwtCitizensResidentsEntity.setSendSmsFlag(kwtCitizensResidentsDTO.getSendSmsFlag());
            else
                kwtCitizensResidentsEntity.setSendSmsFlag(ISystemConstant.ACTIVE_FLAG);
            doUpdate(kwtCitizensResidentsEntity);
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

    public Boolean remove(IBasicDTO kwtCitizensResidentsDTO) throws DataBaseException, SharedApplicationException {
        try {
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode());

            if (kwtCitizensResidentsEntity == null) {
                throw new ItemNotFoundException();
            }
            EM().remove(kwtCitizensResidentsEntity);
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


    public IBasicDTO getKwtCitizensResidents(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity = EM().find(KwtCitizensResidentsEntity.class, id);
            if (kwtCitizensResidentsEntity == null) {
                throw new ItemNotFoundException();
            }
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                InfDTOFactory.createKwtCitizensResidentsDTO(kwtCitizensResidentsEntity);
            return kwtCitizensResidentsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public IBasicDTO getCitizenInformation(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            KwtCitizensResidentsEntity entity =
                (KwtCitizensResidentsEntity)EM().createNamedQuery("KwtCitizensResidentsEntity.getCitizenInformation").setParameter("civilId",
                                                                                                                                   civilId).getSingleResult();
            IKwtCitizensResidentsDTO dto = InfDTOFactory.createKwtCitizensResidentsDTO(entity);
            List qList = new ArrayList();
            //System.err.println ( entity.getPersonQualificationsEntityList ( ) .size ( ) ) ;
            for (PersonQualificationsEntity qEntity : entity.getPersonQualificationsEntityList()) {
                if (qEntity.getCrsRegistrationOrder() == null || qEntity.getCrsRegistrationOrder().equals(1L)) {
                    qList.add(InfDTOFactory.createPersonQualificationsDTO(qEntity));
                }
            }
            dto.setPersonQualificationsDTOList(qList);
            return dto;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IBasicDTO getCitizenName(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list =
                EM().createNamedQuery("KwtCitizensResidentsEntity.getCitizenName").setParameter("civilId",
                                                                                                civilId).getResultList();
            if (list.size() == 0) {
                throw new NoResultException();
            }
            return list.get(0);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public void checkCivilIdExist(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            Long count =
                (Long)EM().createNamedQuery("KwtCitizensResidentsEntity.countCitizensWithCivil").setParameter("civilId",
                                                                                                              civilId).getSingleResult();
            if (count == null || count.equals(0L)) {
                throw new ItemNotFoundException();
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

    public List<IBasicDTO> searchAboutCitizens(IBasicDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                               SharedApplicationException {
        try {
            IKwtCitizensResidentsSearchDTO dto = (IKwtCitizensResidentsSearchDTO)kwtCitizensResidentsSearchDTO;
            StringBuilder query = new StringBuilder("");
            if (dto.getCivilId() != null) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID = " + dto.getCivilId() + " AND");
            }
            if (dto.getBirthDate() != null) {
                query.append(" to_char(INF_KWT_CITIZENS_RESIDENTS.BIRTH_DATE, 'YYYY-MM-DD') ='" + dto.getBirthDate() +
                             "' AND");
            }
            if (dto.getMaritalStatusCode() != null) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.MARSTATUS_CODE = " + dto.getMaritalStatusCode() + " AND");
            }
            if (dto.getNationality() != null) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.NATIONALITY = " + dto.getNationality() + " AND");
            }
            if (dto.getName() != null && dto.getName().trim().length() != 0) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID IN ( Select INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID From INF_KWT_CITIZENS_RESIDENTS WHERE "); // +
                query.append("((INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME || ' ' || INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME || ' ' || INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME || ' ' || " +
                             "INF_KWT_CITIZENS_RESIDENTS.LAST_NAME  || ' ' ||   INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME ) LIKE '%" +
                             dto.getName() + "%')");
                query.append(") AND");
                // "CONCAT ( CONCAT ( CONCAT ( CONCAT ( INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME , ' ' ) , CONCAT ( INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME , ' ' ) ) , CONCAT ( CONCAT ( INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME , ' ' ) , CONCAT ( INF_KWT_CITIZENS_RESIDENTS.LAST_NAME , ' ' ) ) ) , INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME )
            }

            StringBuilder finalQuery = new StringBuilder(" SELECT * FROM INF_KWT_CITIZENS_RESIDENTS");
            if (query.length() > 0) {
                query = query.replace(query.length() - 4, query.length(), "");
                finalQuery.append(" WHERE" + query);
            }
            System.out.println(finalQuery);
            List<KwtCitizensResidentsEntity> list1;
            Query q = EM().createNativeQuery(finalQuery.toString(), KwtCitizensResidentsEntity.class);
            list1 = q.getResultList();
            ArrayList arrayList = new ArrayList();
            if (list1 != null && list1.size() > 0) {
                for (KwtCitizensResidentsEntity citizen : list1) {
                    arrayList.add(InfEntityConvertr.getKwtCitizensResidentsDTO(citizen));
                }
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

    public List<IBasicDTO> searchAboutCitizensNotEmployees(IBasicDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                                           SharedApplicationException {
        try {
            IKwtCitizensResidentsSearchDTO dto = (IKwtCitizensResidentsSearchDTO)kwtCitizensResidentsSearchDTO;
            StringBuilder query = new StringBuilder("");
            if (dto.getCivilId() != null) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID = " + dto.getCivilId() + " AND");
            }
            if (dto.getBirthDate() != null) {
                query.append(" to_char(INF_KWT_CITIZENS_RESIDENTS.BIRTH_DATE, 'YYYY-MM-DD') ='" + dto.getBirthDate() +
                             "' AND");
            }
            if (dto.getMaritalStatusCode() != null) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.MARSTATUS_CODE = " + dto.getMaritalStatusCode() + " AND");
            }
            if (dto.getNationality() != null) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.NATIONALITY = " + dto.getNationality() + " AND");
            }
            if (dto.getName() != null && dto.getName().trim().length() != 0) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID IN ( Select INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID From INF_KWT_CITIZENS_RESIDENTS WHERE "); // +
                query.append("((INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME || ' ' || INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME || ' ' || INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME || ' ' || " +
                             "INF_KWT_CITIZENS_RESIDENTS.LAST_NAME  || ' ' ||   INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME ) LIKE '%" +
                             dto.getName() + "%')");
                // Not In Emp_Employee
                query.append(" and INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID not in( select E.REAL_CIVIL_ID from hr_emp_employees e where E.HIRSTATUS_CODE=1) ");
                query.append(") AND");
                // "CONCAT ( CONCAT ( CONCAT ( CONCAT ( INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME , ' ' ) , CONCAT ( INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME , ' ' ) ) , CONCAT ( CONCAT ( INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME , ' ' ) , CONCAT ( INF_KWT_CITIZENS_RESIDENTS.LAST_NAME , ' ' ) ) ) , INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME )
            }

            StringBuilder finalQuery = new StringBuilder(" SELECT * FROM INF_KWT_CITIZENS_RESIDENTS");
            if (query.length() > 0) {
                query = query.replace(query.length() - 4, query.length(), "");
                finalQuery.append(" WHERE" + query);
            }
            System.out.println(finalQuery);
            List<KwtCitizensResidentsEntity> list1;
            Query q = EM().createNativeQuery(finalQuery.toString(), KwtCitizensResidentsEntity.class);
            list1 = q.getResultList();
            ArrayList arrayList = new ArrayList();
            if (list1 != null && list1.size() > 0) {
                for (KwtCitizensResidentsEntity citizen : list1) {
                    arrayList.add(InfEntityConvertr.getKwtCitizensResidentsDTO(citizen));
                }
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

    public List<IBasicDTO> searchCitizens(IBasicDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            IKwtCitizensResidentsSearchDTO dto = (IKwtCitizensResidentsSearchDTO)kwtCitizensResidentsSearchDTO;
            StringBuilder query = new StringBuilder("");
            if (dto.getCivilId() != null) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID = " + dto.getCivilId() + " AND");
            }
            if (dto.getName() != null && dto.getName().trim().length() != 0) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID IN ( Select INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID From INF_KWT_CITIZENS_RESIDENTS WHERE "); // +
                query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("((INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME || ' ' || INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME || ' ' || INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME || ' ' || \n" +
                            "INF_KWT_CITIZENS_RESIDENTS.LAST_NAME  || ' ' ||   INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME )",
                            dto.getName()));
                query.append(")) AND");
                // "CONCAT ( CONCAT ( CONCAT ( CONCAT ( INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME , ' ' ) , CONCAT ( INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME , ' ' ) ) , CONCAT ( CONCAT ( INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME , ' ' ) , CONCAT ( INF_KWT_CITIZENS_RESIDENTS.LAST_NAME , ' ' ) ) ) , INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME )
            }
            StringBuilder finalQuery =
                new StringBuilder(" SELECT INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID ," + "INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME ," +
                                  "INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME ," +
                                  "INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME ," +
                                  "INF_KWT_CITIZENS_RESIDENTS.LAST_NAME ," +
                                  "INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME ," +
                                  "INF_KWT_CITIZENS_RESIDENTS.BIRTH_DATE ," +
                                  "INF_KWT_CITIZENS_RESIDENTS.MARSTATUS_CODE ," +
                                  "INF_KWT_CITIZENS_RESIDENTS.NATIONALITY  " + "FROM INF_KWT_CITIZENS_RESIDENTS");
            if (query.length() > 0) {
                query = query.replace(query.length() - 4, query.length(), "");
                finalQuery.append(" WHERE" + query);
            }
            IRequestDTO requestDTO = ((IRequestDTO)dto.getRequestDTO());
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                finalQuery.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = (String)requestDTO.getSortColumnList().get(i);
                    finalQuery.append(column);
                    if (!requestDTO.isSortAscending())
                        finalQuery.append(" DESC");
                    if (i < requestDTO.getSortColumnList().size() - 1)
                        finalQuery.append(" , ");
                }
            }
            System.out.println(finalQuery);
            Query q = EM().createNativeQuery(finalQuery.toString());
            if (requestDTO != null) {
                q.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                q.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            ArrayList arrayList = new ArrayList();
            List<List> list = q.getResultList();
            if (list != null && list.size() > 0) {
                for (List data : list) {
                    arrayList.add(InfDTOFactory.createKwtCitizensResidentsDTO(data));
                }
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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


    public Long getSearchCitizensCount(IBasicDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            IKwtCitizensResidentsSearchDTO dto = (IKwtCitizensResidentsSearchDTO)kwtCitizensResidentsSearchDTO;
            StringBuilder query = new StringBuilder("");
            if (dto.getCivilId() != null) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID = " + dto.getCivilId() + " AND");
            }
            if (dto.getName() != null && dto.getName().trim().length() != 0) {
                query.append(" INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID IN ( Select INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID From INF_KWT_CITIZENS_RESIDENTS WHERE "); // +
                query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME , ' ' ) , CONCAT ( INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME , ' ' ) ) , CONCAT ( CONCAT ( INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME , ' ' ) , CONCAT ( INF_KWT_CITIZENS_RESIDENTS.LAST_NAME , ' ' ) ) ) , INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME )",
                                                                                 dto.getName()));
                query.append(") AND");
                //                         "CONCAT ( CONCAT ( CONCAT ( CONCAT ( INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME , ' ' ) , CONCAT ( INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME , ' ' ) ) , CONCAT ( CONCAT ( INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME , ' ' ) , CONCAT ( INF_KWT_CITIZENS_RESIDENTS.LAST_NAME , ' ' ) ) ) , INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME ) LIKE '" +
                //                         dto.getName() + "' ) AND");
            }
            StringBuilder finalQuery = new StringBuilder("SELECT count ( * ) FROM INF_KWT_CITIZENS_RESIDENTS");
            if (query.length() > 0) {
                query = query.replace(query.length() - 4, query.length(), "");
                finalQuery.append(" WHERE" + query);
            }
            IRequestDTO requestDTO = ((IRequestDTO)dto.getRequestDTO());
            System.out.println(finalQuery);
            try {
                Query q = EM().createNativeQuery(finalQuery.toString());
                java.util.Vector vector = (Vector)q.getSingleResult();
                BigDecimal count = (BigDecimal)vector.get(0);
                if (count != null) {
                    return count.longValue();
                } else {
                    return new Long(0);
                }
            } catch (javax.persistence.NoResultException e) {
                throw new ItemNotFoundException();
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

    public List<IBasicDTO> search(IBasicDTO dto) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            IKwtCitizensResidentsSearchDTO kwtDTO = (IKwtCitizensResidentsSearchDTO)dto;
            List<KwtCitizensResidentsEntity> list =
                EM().createNamedQuery("KwtCitizensResidentsEntity.search").setParameter("civilId",
                                                                                        kwtDTO.getCivilId()).setParameter("name1",
                                                                                                                          kwtDTO.getFirstName()).setParameter("name2",
                                                                                                                                                              kwtDTO.getSecondName()).setParameter("name3",
                                                                                                                                                                                                   kwtDTO.getThirdName()).setParameter("name4",
                                                                                                                                                                                                                                       kwtDTO.getLastName()).setParameter("name5",
                                                                                                                                                                                                                                                                          kwtDTO.getFamilyName()).getResultList();
            for (KwtCitizensResidentsEntity kwtCitizensResidents : list) {
                arrayList.add(InfDTOFactory.createKwtCitizensResidentsDTO(kwtCitizensResidents));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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

    public List<IBasicDTO> filterAvailableInfUsingPaging(IBasicDTO employeeSearchDTO1,
                                                         IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            StringBuilder ejbql =
                new StringBuilder("select o from KwtCitizensResidentsEntity o WHERE o.civilId=o.civilId");
            com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO employeeSearchDTO =
                (IEmployeeSearchDTO)employeeSearchDTO1;
            if (employeeSearchDTO.getCivilId() != null)
                ejbql.append(" AND o.civilId='" + employeeSearchDTO.getCivilId() + "'");
            if (employeeSearchDTO.getFirstName() != null) {
                //            ejbql.append(" AND o.firstName LIKE '" +
                //                         employeeSearchDTO.getFirstName() + "'");
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.firstName", employeeSearchDTO.getFirstName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getSecondName() != null) {
                //          ejbql.append(" AND o.secondName LIKE '" +
                //                         employeeSearchDTO.getSecondName() + "'");
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.secondName", employeeSearchDTO.getSecondName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getThirdName() != null) {
                //          ejbql.append(" AND o.thirdName LIKE '" +
                //                         employeeSearchDTO.getThirdName() + "'");
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.thirdName", employeeSearchDTO.getThirdName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getLastName() != null) {
                //            ejbql.append(" AND o.lastName LIKE '" +
                //                         employeeSearchDTO.getLastName() + "'");
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.lastName", employeeSearchDTO.getLastName());
                ejbql.append(" AND ");
                ejbql.append(condition);

            }
            if (employeeSearchDTO.getFamilyName() != null) {
                //            ejbql.append(" AND o.familyName LIKE '" +
                //                         employeeSearchDTO.getFamilyName() + "'");
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.familyName", employeeSearchDTO.getFamilyName());
                ejbql.append(" AND ");
                ejbql.append(condition);

            }
            if (employeeSearchDTO.getEnglishName() != null)
                ejbql.append(" AND o.englishName LIKE '" + employeeSearchDTO.getEnglishName() + "'");
            if (employeeSearchDTO.getBirthDate() != null)
                ejbql.append(" AND o.birthDate='" + employeeSearchDTO.getBirthDate() + "'");
            if (employeeSearchDTO.getGenderTypeCode() != null)
                ejbql.append(" AND o.gentypeCode=" + employeeSearchDTO.getGenderTypeCode() + "");
            if (employeeSearchDTO.getMaritalStatusCode() != null)
                ejbql.append(" AND o.marstatusCode=" + employeeSearchDTO.getMaritalStatusCode() + "");
            if (employeeSearchDTO.getPhonesNo() != null)
                ejbql.append(" AND o.phonesNo LIKE '" + employeeSearchDTO.getPhonesNo() + "'");
            if (employeeSearchDTO.getMobileNo() != null)
                ejbql.append(" AND o.mobileNo LIKE '" + employeeSearchDTO.getMobileNo() + "'");
            if (employeeSearchDTO.getReligionCode() != null)
                ejbql.append(" AND o.religionCode=" + employeeSearchDTO.getReligionCode() + "");
            if (employeeSearchDTO.getResidentTypeCode() != null)
                ejbql.append(" AND o.restypeCode=" + employeeSearchDTO.getResidentTypeCode() + "");
            if (employeeSearchDTO.getEndResidentDate() != null)
                ejbql.append(" AND o.endResidentDate='" + employeeSearchDTO.getEndResidentDate() + "'");
            if (employeeSearchDTO.getMapCode() != null)
                ejbql.append(" AND o.mapCode='" + employeeSearchDTO.getMapCode() + "'");
            if (employeeSearchDTO.getCapstatusCode() != null)
                ejbql.append(" AND o.capstatusCode=" + employeeSearchDTO.getCapstatusCode() + "");
            if (employeeSearchDTO.getSpecialCaseTypeCode() != null)
                ejbql.append(" AND o.spccsetypeCode=" + employeeSearchDTO.getSpecialCaseTypeCode() + "");
            if (employeeSearchDTO.getPassportNo() != null)
                ejbql.append(" AND o.passportNo LIKE '" + employeeSearchDTO.getPassportNo() + "'");


            List<KwtCitizensResidentsEntity> list = null;
            Query query = null;

            if (ejbql != null) {
                query = EM().createQuery(ejbql.toString());
                if (requestDTO != null) {
                    query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                    query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
                }
            }
            list = query.getResultList();

            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (KwtCitizensResidentsEntity entity : list) {
                listDTO.add(new KwtCitizensResidentsDTO(entity));
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

    public Long filterAvailableInfCountUsingPaging(IBasicDTO employeeSearchDTO1) throws DataBaseException,
                                                                                        SharedApplicationException {
        try {
            StringBuilder ejbql =
                new StringBuilder("select count(o.civilId) from KwtCitizensResidentsEntity o WHERE o.civilId=o.civilId");
            com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO employeeSearchDTO =
                (IEmployeeSearchDTO)employeeSearchDTO1;
            if (employeeSearchDTO.getCivilId() != null)
                ejbql.append(" AND o.civilId='" + employeeSearchDTO.getCivilId() + "'");
            if (employeeSearchDTO.getFirstName() != null) {
                //            ejbql.append(" AND o.firstName LIKE '" +
                //                         employeeSearchDTO.getFirstName() + "'");
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.firstName", employeeSearchDTO.getFirstName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getSecondName() != null) {
                //          ejbql.append(" AND o.secondName LIKE '" +
                //                         employeeSearchDTO.getSecondName() + "'");
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.secondName", employeeSearchDTO.getSecondName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getThirdName() != null) {
                //          ejbql.append(" AND o.thirdName LIKE '" +
                //                         employeeSearchDTO.getThirdName() + "'");
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.thirdName", employeeSearchDTO.getThirdName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getLastName() != null) {
                //            ejbql.append(" AND o.lastName LIKE '" +
                //                         employeeSearchDTO.getLastName() + "'");
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.lastName", employeeSearchDTO.getLastName());
                ejbql.append(" AND ");
                ejbql.append(condition);

            }
            if (employeeSearchDTO.getFamilyName() != null) {
                //            ejbql.append(" AND o.familyName LIKE '" +
                //                         employeeSearchDTO.getFamilyName() + "'");
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.familyName", employeeSearchDTO.getFamilyName());
                ejbql.append(" AND ");
                ejbql.append(condition);

            }
            if (employeeSearchDTO.getEnglishName() != null)
                ejbql.append(" AND o.englishName LIKE '" + employeeSearchDTO.getEnglishName() + "'");
            if (employeeSearchDTO.getBirthDate() != null)
                ejbql.append(" AND o.birthDate='" + employeeSearchDTO.getBirthDate() + "'");
            if (employeeSearchDTO.getGenderTypeCode() != null)
                ejbql.append(" AND o.gentypeCode=" + employeeSearchDTO.getGenderTypeCode() + "");
            if (employeeSearchDTO.getMaritalStatusCode() != null)
                ejbql.append(" AND o.marstatusCode=" + employeeSearchDTO.getMaritalStatusCode() + "");
            if (employeeSearchDTO.getPhonesNo() != null)
                ejbql.append(" AND o.phonesNo LIKE '" + employeeSearchDTO.getPhonesNo() + "'");
            if (employeeSearchDTO.getMobileNo() != null)
                ejbql.append(" AND o.mobileNo LIKE '" + employeeSearchDTO.getMobileNo() + "'");
            if (employeeSearchDTO.getReligionCode() != null)
                ejbql.append(" AND o.religionCode=" + employeeSearchDTO.getReligionCode() + "");
            if (employeeSearchDTO.getResidentTypeCode() != null)
                ejbql.append(" AND o.restypeCode=" + employeeSearchDTO.getResidentTypeCode() + "");
            if (employeeSearchDTO.getEndResidentDate() != null)
                ejbql.append(" AND o.endResidentDate='" + employeeSearchDTO.getEndResidentDate() + "'");
            if (employeeSearchDTO.getMapCode() != null)
                ejbql.append(" AND o.mapCode='" + employeeSearchDTO.getMapCode() + "'");
            if (employeeSearchDTO.getCapstatusCode() != null)
                ejbql.append(" AND o.capstatusCode=" + employeeSearchDTO.getCapstatusCode() + "");
            if (employeeSearchDTO.getSpecialCaseTypeCode() != null)
                ejbql.append(" AND o.spccsetypeCode=" + employeeSearchDTO.getSpecialCaseTypeCode() + "");
            if (employeeSearchDTO.getPassportNo() != null)
                ejbql.append(" AND o.passportNo LIKE '" + employeeSearchDTO.getPassportNo() + "'");
            Query query = EM().createQuery(ejbql.toString());
            return (Long)query.getSingleResult();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IBasicDTO getCitizenCodeName(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list =
                EM().createNamedQuery("KwtCitizensResidentsEntity.getCitizenCodeFullName").setParameter("civilId",
                                                                                                        civilId).getResultList();
            if (list.size() == 0) {
                throw new NoResultException();
            }
            return list.get(0);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("KwtCitizensResidentsEntity.getCodeName").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public List<IBasicDTO> searchCandIllegal(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            List<KwtCitizensResidentsEntity> list = null;
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            try {

                list = buildSearchQueryCandIllegal(basicDTO);

                for (KwtCitizensResidentsEntity entity : list) {
                    listDTO.add(InfDTOFactory.createKwtCitizensResidentsDTO(entity));
                }

            } catch (Exception e) {
                e.printStackTrace();

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

    private List<KwtCitizensResidentsEntity> buildSearchQueryCandIllegal(IBasicDTO basicDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {
        List<KwtCitizensResidentsEntity> list = null;
        try {


            Query query = null;
            IEmployeeSearchDTO vacEmpSearchDTO = (IEmployeeSearchDTO)basicDTO;

            StringBuilder sql = new StringBuilder("SELECT * ");
            sql.append("FROM inf_kwt_citizens_residents where NATIONALITY = 0 AND NON_STATUS IS NULL");


            if (vacEmpSearchDTO.getCivilId() != null)
                sql.append("  AND CIVIL_ID = '" + vacEmpSearchDTO.getCivilId() + "'");
            if (vacEmpSearchDTO.getName() != null && !vacEmpSearchDTO.getName().equals("")) {
                sql.append(" AND CIVIL_ID IN ( Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                                   vacEmpSearchDTO.getName()) + " ) ");
            }

            System.out.print("sql>> " + sql.toString());
            query = EM().createNativeQuery(sql.toString(), KwtCitizensResidentsEntity.class);


            list = query.getResultList();


            if (list == null || list.size() == 0)
                throw new NoResultException();


            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<KwtCitizensResidentsEntity> list =
                EM().createNamedQuery("KwtCitizensResidentsEntity.searchByCode").setParameter("civilId",
                                                                                              (Long)code).getResultList();
            for (KwtCitizensResidentsEntity entity : list) {
                arrayList.add(InfDTOFactory.createKwtCitizensResidentsDTO(entity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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

    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<KwtCitizensResidentsEntity> list =
                EM().createNamedQuery("KwtCitizensResidentsEntity.firstName").setParameter("firstName",
                                                                                           name).getResultList();
            for (KwtCitizensResidentsEntity entity : list) {
                arrayList.add(InfDTOFactory.createKwtCitizensResidentsDTO(entity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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

    public Boolean updateWifeSon(IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)kwtCitizensResidentsDTO1;
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode());
            if (kwtCitizensResidentsEntity == null) {
                throw new ItemNotFoundException("kwtCitizensResidentsEntity");
            }
            kwtCitizensResidentsEntity.setCivilId(((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId());
            if(kwtCitizensResidentsDTO.getFirstName() != null && !kwtCitizensResidentsDTO.getFirstName().isEmpty() )
                kwtCitizensResidentsEntity.setFirstName(kwtCitizensResidentsDTO.getFirstName());
            else if(kwtCitizensResidentsEntity.getFirstName() == null || kwtCitizensResidentsEntity.getFirstName().isEmpty() )
                kwtCitizensResidentsEntity.setFirstName(".");
            
            if(kwtCitizensResidentsDTO.getSecondName() != null && !kwtCitizensResidentsDTO.getSecondName().isEmpty() )
                kwtCitizensResidentsEntity.setSecondName(kwtCitizensResidentsDTO.getSecondName());
            else if(kwtCitizensResidentsEntity.getSecondName() == null || kwtCitizensResidentsEntity.getSecondName().isEmpty() )
                kwtCitizensResidentsEntity.setSecondName(".");
            
            if(kwtCitizensResidentsDTO.getThirdName() != null && !kwtCitizensResidentsDTO.getThirdName().isEmpty() )
                kwtCitizensResidentsEntity.setThirdName(kwtCitizensResidentsDTO.getThirdName());
            else if(kwtCitizensResidentsEntity.getThirdName() == null || kwtCitizensResidentsEntity.getThirdName().isEmpty() )
                kwtCitizensResidentsEntity.setThirdName(".");
            
            if(kwtCitizensResidentsDTO.getLastName() != null && !kwtCitizensResidentsDTO.getLastName().isEmpty() )
                kwtCitizensResidentsEntity.setLastName(kwtCitizensResidentsDTO.getLastName());
            else if(kwtCitizensResidentsEntity.getLastName() == null || kwtCitizensResidentsEntity.getLastName().isEmpty() )
                kwtCitizensResidentsEntity.setLastName(".");
            
            kwtCitizensResidentsEntity.setFamilyName(kwtCitizensResidentsDTO.getFamilyName());
//            kwtCitizensResidentsEntity.setEnglishName(kwtCitizensResidentsDTO.getEnglishName());
            kwtCitizensResidentsEntity.setBirthDate(kwtCitizensResidentsDTO.getBirthDate());

            if (kwtCitizensResidentsDTO.getGenderTypesDTO() != null) {
                GenderTypesEntity genderTypesEntity =
                    EM().find(GenderTypesEntity.class, (IGenderTypesEntityKey)kwtCitizensResidentsDTO.getGenderTypesDTO().getCode());
                kwtCitizensResidentsEntity.setGenderTypesEntity(genderTypesEntity);
            } else {
                throw new ItemNotFoundException("GenderTypes");
            }

            if (kwtCitizensResidentsDTO.getMaritalStatusDTO() != null) {
                MaritalStatusEntity maritalStatusEntity =
                    EM().find(MaritalStatusEntity.class, (IMaritalStatusEntityKey)kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode());
                kwtCitizensResidentsEntity.setMaritalStatusEntity(maritalStatusEntity);
            } else {
                throw new ItemNotFoundException("MaritalStatus");
            }

            if (kwtCitizensResidentsDTO.getHandicapStatusDTO() != null) {
                HandicapStatusEntity handicapStatusEntity =
                    EM().find(HandicapStatusEntity.class, (IHandicapStatusEntityKey)kwtCitizensResidentsDTO.getHandicapStatusDTO().getCode());
                kwtCitizensResidentsEntity.setHandicapStatusEntity(handicapStatusEntity);
            } else {
                throw new ItemNotFoundException("HandicapStatus");
            }
            
            if(kwtCitizensResidentsEntity.getReligionsEntity() == null) {
                if (kwtCitizensResidentsDTO.getReligionsDTO() != null) {
                    ReligionsEntity religionsEntity =
                        EM().find(ReligionsEntity.class, (IReligionsEntityKey)kwtCitizensResidentsDTO.getReligionsDTO().getCode());
                    kwtCitizensResidentsEntity.setReligionsEntity(religionsEntity);
                } else {
                    throw new ItemNotFoundException("Religions");
                }
            }
            
            kwtCitizensResidentsEntity.setNationality(kwtCitizensResidentsDTO.getNationality());
            kwtCitizensResidentsEntity.setDeathDate(kwtCitizensResidentsDTO.getDeathDate());
            kwtCitizensResidentsEntity.setActiveFlag(kwtCitizensResidentsDTO.getActiveFlag());
            
//            if(kwtCitizensResidentsEntity.getReligionCode() == null)
//                kwtCitizensResidentsEntity.setReligionCode(kwtCitizensResidentsDTO.getReligionCode());
//            kwtCitizensResidentsEntity.setNationalityDate(kwtCitizensResidentsDTO.getNationalityDate());
//            kwtCitizensResidentsEntity.setPhonesNo(kwtCitizensResidentsDTO.getPhonesNo());
//            kwtCitizensResidentsEntity.setMobileNo(kwtCitizensResidentsDTO.getMobileNo());
//            kwtCitizensResidentsEntity.setEMail(kwtCitizensResidentsDTO.getEMail());
//            kwtCitizensResidentsEntity.setRestypeCode(kwtCitizensResidentsDTO.getRestypeCode());
//            kwtCitizensResidentsEntity.setPassportCntryCode(kwtCitizensResidentsDTO.getPassportCntryCode());
//            kwtCitizensResidentsEntity.setPassportNo(kwtCitizensResidentsDTO.getPassportNo());
//            kwtCitizensResidentsEntity.setPassportIssueDate(kwtCitizensResidentsDTO.getPassportIssueDate());
//            kwtCitizensResidentsEntity.setPassportExpiredDate(kwtCitizensResidentsDTO.getPassportExpiredDate());
//            if (kwtCitizensResidentsDTO.getMapCode() != null) {
//                kwtCitizensResidentsEntity.setKwMapEntity(EM().find(KwMapEntity.class,
//                                                                    InfEntityKeyFactory.createKwMapEntityKey(kwtCitizensResidentsDTO.getMapCode())));
//            }
//            kwtCitizensResidentsEntity.setMapCode(kwtCitizensResidentsDTO.getMapCode());
//            kwtCitizensResidentsEntity.setStreetCode(kwtCitizensResidentsDTO.getStreetCode());
//            kwtCitizensResidentsEntity.setBuildingNo(kwtCitizensResidentsDTO.getBuildingNo());
//            kwtCitizensResidentsEntity.setFloorNo(kwtCitizensResidentsDTO.getFloorNo());
//            kwtCitizensResidentsEntity.setFlatNo(kwtCitizensResidentsDTO.getFlatNo());
//            kwtCitizensResidentsEntity.setAddressInDetails(kwtCitizensResidentsDTO.getAddressInDetails());

//            kwtCitizensResidentsEntity.setBldgroupCode(kwtCitizensResidentsDTO.getBldgroupCode());
//            kwtCitizensResidentsEntity.setMltstatusCode(kwtCitizensResidentsDTO.getMltstatusCode());
//            kwtCitizensResidentsEntity.setEndResidentDate(kwtCitizensResidentsDTO.getEndResidentDate());
//            kwtCitizensResidentsEntity.setCapstatusCode(kwtCitizensResidentsDTO.getCapstatusCode());
//            kwtCitizensResidentsEntity.setSpccsetypeCode(kwtCitizensResidentsDTO.getSpccsetypeCode());
//            kwtCitizensResidentsEntity.setCreatedBy(kwtCitizensResidentsDTO.getCreatedBy());
//            kwtCitizensResidentsEntity.setCreatedDate(kwtCitizensResidentsDTO.getCreatedDate());
//            kwtCitizensResidentsEntity.setLastUpdatedBy(kwtCitizensResidentsDTO.getLastUpdatedBy());
//            kwtCitizensResidentsEntity.setLastUpdatedDate(kwtCitizensResidentsDTO.getLastUpdatedDate());
//            kwtCitizensResidentsEntity.setAuditStatus(kwtCitizensResidentsDTO.getAuditStatus());
//            kwtCitizensResidentsEntity.setTabrecSerial(kwtCitizensResidentsDTO.getTabrecSerial());

            doUpdate(kwtCitizensResidentsEntity);
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


    public Boolean updateKwtCitizenInfo_WS(IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)kwtCitizensResidentsDTO1;
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode());
            
            if (kwtCitizensResidentsEntity == null) {
                throw new ItemNotFoundException("kwtCitizensResidentsEntity");
            }
            kwtCitizensResidentsEntity.setCivilId(((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId());
            if (kwtCitizensResidentsDTO.getFirstName() != null && !kwtCitizensResidentsDTO.getFirstName().isEmpty())
                kwtCitizensResidentsEntity.setFirstName(kwtCitizensResidentsDTO.getFirstName());
        

            if (kwtCitizensResidentsDTO.getSecondName() != null && !kwtCitizensResidentsDTO.getSecondName().isEmpty())
                kwtCitizensResidentsEntity.setSecondName(kwtCitizensResidentsDTO.getSecondName());
          
            if (kwtCitizensResidentsDTO.getThirdName() != null && !kwtCitizensResidentsDTO.getThirdName().isEmpty())
                kwtCitizensResidentsEntity.setThirdName(kwtCitizensResidentsDTO.getThirdName());
        

            if (kwtCitizensResidentsDTO.getLastName() != null && !kwtCitizensResidentsDTO.getLastName().isEmpty())
                kwtCitizensResidentsEntity.setLastName(kwtCitizensResidentsDTO.getLastName());
          
            if (kwtCitizensResidentsDTO.getFamilyName() != null && !kwtCitizensResidentsDTO.getFamilyName().isEmpty())
                kwtCitizensResidentsEntity.setFamilyName(kwtCitizensResidentsDTO.getFamilyName());
            //            kwtCitizensResidentsEntity.setEnglishName(kwtCitizensResidentsDTO.getEnglishName());
            if( kwtCitizensResidentsDTO.getBirthDate() != null ){
                kwtCitizensResidentsEntity.setBirthDate(kwtCitizensResidentsDTO.getBirthDate());
            }

            if (kwtCitizensResidentsDTO.getGenderTypesDTO() != null) {
                GenderTypesEntity genderTypesEntity =
                    EM().find(GenderTypesEntity.class, (IGenderTypesEntityKey)kwtCitizensResidentsDTO.getGenderTypesDTO().getCode());
                kwtCitizensResidentsEntity.setGenderTypesEntity(genderTypesEntity);
            }

//            if (kwtCitizensResidentsDTO.getMaritalStatusDTO() != null) {
//                MaritalStatusEntity maritalStatusEntity =
//                    EM().find(MaritalStatusEntity.class, (IMaritalStatusEntityKey)kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode());
//                kwtCitizensResidentsEntity.setMaritalStatusEntity(maritalStatusEntity);
//            } else {
//                throw new ItemNotFoundException("MaritalStatus");
//            }

//            if (kwtCitizensResidentsDTO.getHandicapStatusDTO() != null) {
//                HandicapStatusEntity handicapStatusEntity =
//                    EM().find(HandicapStatusEntity.class, (IHandicapStatusEntityKey)kwtCitizensResidentsDTO.getHandicapStatusDTO().getCode());
//                kwtCitizensResidentsEntity.setHandicapStatusEntity(handicapStatusEntity);
//            } else {
//                throw new ItemNotFoundException("HandicapStatus");
//            }
//
//            if (kwtCitizensResidentsEntity.getReligionsEntity() == null) {
//                if (kwtCitizensResidentsDTO.getReligionsDTO() != null) {
//                    ReligionsEntity religionsEntity =
//                        EM().find(ReligionsEntity.class, (IReligionsEntityKey)kwtCitizensResidentsDTO.getReligionsDTO().getCode());
//                    kwtCitizensResidentsEntity.setReligionsEntity(religionsEntity);
//                } else {
//                    throw new ItemNotFoundException("Religions");
//                }
//            }
            if(kwtCitizensResidentsDTO.getNationality() !=null){
                kwtCitizensResidentsEntity.setNationality(kwtCitizensResidentsDTO.getNationality());
            }
            //kwtCitizensResidentsEntity.setDeathDate(kwtCitizensResidentsDTO.getDeathDate());
     //       kwtCitizensResidentsEntity.setActiveFlag(kwtCitizensResidentsDTO.getActiveFlag());

         

            doUpdate(kwtCitizensResidentsEntity);
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
     *this method enhanced to get simple attributes, please create another method if this method doesn't fit your business.
     * @param civilId
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IBasicDTO getKwtCitizensResidentQuls(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            KwtCitizensResidentsEntity entity =
                (KwtCitizensResidentsEntity)EM().createNamedQuery("KwtCitizensResidentsEntity.getByCivilId").setParameter("civilId",
                                                                                                                          civilId).getSingleResult();
            IKwtCitizensResidentsDTO dto = InfEntityConverter.getSimpleKwtCitizensResidentsQulDTO(entity);
            if (dto == null) {
                throw new ItemNotFoundException();
            } else {
                try {
                    List<IPersonQualificationsDTO> personQualificationsList =
                        new ArrayList<IPersonQualificationsDTO>();
                    for (PersonQualificationsEntity personQualificationsEntity :
                         (List<PersonQualificationsEntity>)EM().createNamedQuery("PersonQualificationsEntity.getByCivilId").setParameter("civilId",
                                                                                                                                         civilId).setHint("toplink.refresh",
                                                                                                                                                          "true").getResultList()) {
                        personQualificationsList.add(InfEntityConverter.getSimplePersonQualificationsDTO(personQualificationsEntity));
                    }

                    dto.setPersonQualificationsDTOList(personQualificationsList);
                    //Set PersonsInformationList
                    List<IPersonsInformationDTO> personsInformationList = new ArrayList<IPersonsInformationDTO>();
                    for (PersonsInformationEntity personsInformationEntity :
                         (List<PersonsInformationEntity>)EM().createNamedQuery("PersonsInformationEntity.getByCivilId").setParameter("civilId",
                                                                                                                                     civilId).setHint("toplink.refresh",
                                                                                                                                                      "true").getResultList()) {
                        personsInformationList.add(InfDTOFactory.createPersonsInformationDTO(personsInformationEntity));
                    }
                    dto.setPersonsInformationDTOList(personsInformationList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return dto;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IBasicDTO updateDTO(IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException,
                                                                          SharedApplicationException {
        try {

            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)kwtCitizensResidentsDTO1;
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode());

            if (kwtCitizensResidentsEntity == null) {
                throw new ItemNotFoundException();
            }

            try {

                if (kwtCitizensResidentsDTO.getGenderTypesDTO() != null &&
                    kwtCitizensResidentsDTO.getGenderTypesDTO().getCode() != null &&
                    (kwtCitizensResidentsDTO.getGenderTypesDTO().getCode().getKey() != null &&
                     !kwtCitizensResidentsDTO.getGenderTypesDTO().getCode().getKey().isEmpty())) {
                    GenderTypesEntity genderTypesEntity =
                        EM().find(GenderTypesEntity.class, (IGenderTypesEntityKey)kwtCitizensResidentsDTO.getGenderTypesDTO().getCode());
                    kwtCitizensResidentsEntity.setGenderTypesEntity(genderTypesEntity);
                } else {
                    IGenderTypesEntityKey genderTypesEntityKey =
                        new GenderTypesEntityKey(kwtCitizensResidentsEntity.getGentypeCode());
                    GenderTypesEntity genderTypesEntity = EM().find(GenderTypesEntity.class, genderTypesEntityKey);
                    kwtCitizensResidentsEntity.setGenderTypesEntity(genderTypesEntity);
                }

                if (kwtCitizensResidentsDTO.getMaritalStatusDTO() != null &&
                    kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode() != null &&
                    (kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode().getKey() != null &&
                     !kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode().getKey().isEmpty())) {
                    MaritalStatusEntity maritalStatusEntity =
                        EM().find(MaritalStatusEntity.class, (IMaritalStatusEntityKey)kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode());
                    kwtCitizensResidentsEntity.setMaritalStatusEntity(maritalStatusEntity);
                } else {
                    IMaritalStatusEntityKey maritalStatusEntityKey =
                        new MaritalStatusEntityKey(kwtCitizensResidentsEntity.getMarstatusCode());
                    MaritalStatusEntity maritalStatusEntity =
                        EM().find(MaritalStatusEntity.class, maritalStatusEntityKey);
                    kwtCitizensResidentsEntity.setMaritalStatusEntity(maritalStatusEntity);
                }


                if (kwtCitizensResidentsDTO.getHandicapStatusDTO() != null &&
                    kwtCitizensResidentsDTO.getHandicapStatusDTO().getCode() != null &&
                    (kwtCitizensResidentsDTO.getHandicapStatusDTO().getCode().getKey() != null &&
                     !kwtCitizensResidentsDTO.getHandicapStatusDTO().getCode().getKey().isEmpty())) {
                    HandicapStatusEntity handicapStatusEntity =
                        EM().find(HandicapStatusEntity.class, (IHandicapStatusEntityKey)kwtCitizensResidentsDTO.getHandicapStatusDTO().getCode());
                    kwtCitizensResidentsEntity.setHandicapStatusEntity(handicapStatusEntity);
                } else {
                    IHandicapStatusEntityKey handicapStatusEntityKey =
                        new HandicapStatusEntityKey(kwtCitizensResidentsEntity.getCapstatusCode());
                    HandicapStatusEntity handicapStatusEntity =
                        EM().find(HandicapStatusEntity.class, handicapStatusEntityKey);
                    kwtCitizensResidentsEntity.setHandicapStatusEntity(handicapStatusEntity);
                }


                if (kwtCitizensResidentsDTO.getReligionsDTO() != null &&
                    kwtCitizensResidentsDTO.getReligionsDTO().getCode() != null &&
                    (kwtCitizensResidentsDTO.getReligionsDTO().getCode().getKey() != null &&
                     !kwtCitizensResidentsDTO.getReligionsDTO().getCode().getKey().isEmpty())) {
                    ReligionsEntity religionsEntity =
                        EM().find(ReligionsEntity.class, (IReligionsEntityKey)kwtCitizensResidentsDTO.getReligionsDTO().getCode());
                    kwtCitizensResidentsEntity.setReligionsEntity(religionsEntity);
                } else {
                    IReligionsEntityKey religionsEntityKey =
                        new ReligionsEntityKey(kwtCitizensResidentsEntity.getReligionCode());
                    ReligionsEntity religionsEntity = EM().find(ReligionsEntity.class, religionsEntityKey);
                    kwtCitizensResidentsEntity.setReligionsEntity(religionsEntity);
                }


                if (kwtCitizensResidentsDTO.getSpecialCaseTypesDTO() != null &&
                    kwtCitizensResidentsDTO.getSpecialCaseTypesDTO().getCode() != null &&
                    (kwtCitizensResidentsDTO.getSpecialCaseTypesDTO().getCode().getKey() != null &&
                     !kwtCitizensResidentsDTO.getSpecialCaseTypesDTO().getCode().getKey().isEmpty())) {
                    SpecialCaseTypesEntity specialCaseTypesEntity =
                        EM().find(SpecialCaseTypesEntity.class, (ISpecialCaseTypesEntityKey)kwtCitizensResidentsDTO.getSpecialCaseTypesDTO().getCode());
                    kwtCitizensResidentsEntity.setSpecialCaseTypesEntity(specialCaseTypesEntity);
                }
                else {
//                    ISpecialCaseTypesEntityKey specialCaseTypesEntityKey =
//                        new SpecialCaseTypesEntityKey(kwtCitizensResidentsEntity.getSpccsetypeCode());
//                    SpecialCaseTypesEntity specialCaseTypesEntity =
//                        EM().find(SpecialCaseTypesEntity.class, specialCaseTypesEntityKey);
                    kwtCitizensResidentsEntity.setSpecialCaseTypesEntity(null);
                }

                if (kwtCitizensResidentsDTO.getMapCode() != null) {
                    kwtCitizensResidentsEntity.setKwMapEntity(EM().find(KwMapEntity.class,
                                                                        InfEntityKeyFactory.createKwMapEntityKey(kwtCitizensResidentsDTO.getMapCode())));
                }

                if (((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId() != null) {
                    kwtCitizensResidentsEntity.setCivilId(((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId());
                }

                if (kwtCitizensResidentsDTO.getFirstName() != null) {
                    kwtCitizensResidentsEntity.setFirstName(kwtCitizensResidentsDTO.getFirstName());
                }

                if (kwtCitizensResidentsDTO.getSecondName() != null) {
                    kwtCitizensResidentsEntity.setSecondName(kwtCitizensResidentsDTO.getSecondName());
                }

                if (kwtCitizensResidentsDTO.getThirdName() != null) {
                    kwtCitizensResidentsEntity.setThirdName(kwtCitizensResidentsDTO.getThirdName());
                }

                if (kwtCitizensResidentsDTO.getReligionCode() != null) {
                    kwtCitizensResidentsEntity.setReligionCode(kwtCitizensResidentsDTO.getReligionCode());
                }

                if (kwtCitizensResidentsDTO.getLastName() != null) {
                    kwtCitizensResidentsEntity.setLastName(kwtCitizensResidentsDTO.getLastName());
                }

                if (kwtCitizensResidentsDTO.getFamilyName() != null) {
                    kwtCitizensResidentsEntity.setFamilyName(kwtCitizensResidentsDTO.getFamilyName());
                }

                if (kwtCitizensResidentsDTO.getEnglishName() != null) {
                    kwtCitizensResidentsEntity.setEnglishName(kwtCitizensResidentsDTO.getEnglishName());
                }

                if (kwtCitizensResidentsDTO.getBirthDate() != null) {
                    kwtCitizensResidentsEntity.setBirthDate(kwtCitizensResidentsDTO.getBirthDate());
                }

                if (kwtCitizensResidentsDTO.getNationality() != null) {
                    kwtCitizensResidentsEntity.setNationality(kwtCitizensResidentsDTO.getNationality());
                }

                if (kwtCitizensResidentsDTO.getNationalityDate() != null) {
                    kwtCitizensResidentsEntity.setNationalityDate(kwtCitizensResidentsDTO.getNationalityDate());
                }

                if (kwtCitizensResidentsDTO.getPhonesNo() != null) {
                    kwtCitizensResidentsEntity.setPhonesNo(kwtCitizensResidentsDTO.getPhonesNo());
                }

                if (kwtCitizensResidentsDTO.getMobileNo() != null) {
                    kwtCitizensResidentsEntity.setMobileNo(kwtCitizensResidentsDTO.getMobileNo());
                }

                if (kwtCitizensResidentsDTO.getEMail() != null) {
                    kwtCitizensResidentsEntity.setEMail(kwtCitizensResidentsDTO.getEMail());
                }

                if (kwtCitizensResidentsDTO.getRestypeCode() != null) {
                    kwtCitizensResidentsEntity.setRestypeCode(kwtCitizensResidentsDTO.getRestypeCode());
                }

                if (kwtCitizensResidentsDTO.getPassportCntryCode() != null) {
                    kwtCitizensResidentsEntity.setPassportCntryCode(kwtCitizensResidentsDTO.getPassportCntryCode());
                }

                if (kwtCitizensResidentsDTO.getPassportNo() != null) {
                    kwtCitizensResidentsEntity.setPassportNo(kwtCitizensResidentsDTO.getPassportNo());
                }

                if (kwtCitizensResidentsDTO.getPassportIssueDate() != null) {
                    kwtCitizensResidentsEntity.setPassportIssueDate(kwtCitizensResidentsDTO.getPassportIssueDate());
                }

                if (kwtCitizensResidentsDTO.getPassportExpiredDate() != null) {
                    kwtCitizensResidentsEntity.setPassportExpiredDate(kwtCitizensResidentsDTO.getPassportExpiredDate());
                }


                if (kwtCitizensResidentsDTO.getMapCode() != null) {
                    kwtCitizensResidentsEntity.setMapCode(kwtCitizensResidentsDTO.getMapCode());
                }

                if (kwtCitizensResidentsDTO.getStreetCode() != null) {
                    kwtCitizensResidentsEntity.setStreetCode(kwtCitizensResidentsDTO.getStreetCode());
                }

                if (kwtCitizensResidentsDTO.getBuildingNo() != null) {
                    kwtCitizensResidentsEntity.setBuildingNo(kwtCitizensResidentsDTO.getBuildingNo());
                }

              //  if (kwtCitizensResidentsDTO.getFloorNo() != null) {
                    kwtCitizensResidentsEntity.setFloorNo(kwtCitizensResidentsDTO.getFloorNo());
                //}

                //if (kwtCitizensResidentsDTO.getFlatNo() != null) {
                    kwtCitizensResidentsEntity.setFlatNo(kwtCitizensResidentsDTO.getFlatNo());
                //}

                if (kwtCitizensResidentsDTO.getAddressInDetails() != null) {
                    kwtCitizensResidentsEntity.setAddressInDetails(kwtCitizensResidentsDTO.getAddressInDetails());
                }

                if (kwtCitizensResidentsDTO.getBldgroupCode() != null) {
                    kwtCitizensResidentsEntity.setBldgroupCode(kwtCitizensResidentsDTO.getBldgroupCode());
                }

                if (kwtCitizensResidentsDTO.getMltstatusCode() != null) {
                    kwtCitizensResidentsEntity.setMltstatusCode(kwtCitizensResidentsDTO.getMltstatusCode());
                }

                if (kwtCitizensResidentsDTO.getDeathDate() != null) {
                    kwtCitizensResidentsEntity.setDeathDate(kwtCitizensResidentsDTO.getDeathDate());
                }

                if (kwtCitizensResidentsDTO.getEndResidentDate() != null) {
                    kwtCitizensResidentsEntity.setEndResidentDate(kwtCitizensResidentsDTO.getEndResidentDate());
                }

                if (kwtCitizensResidentsDTO.getCapstatusCode() != null) {
                    kwtCitizensResidentsEntity.setCapstatusCode(kwtCitizensResidentsDTO.getCapstatusCode());
                }


//                if (kwtCitizensResidentsDTO.getSpccsetypeCode() != null) {
                    kwtCitizensResidentsEntity.setSpccsetypeCode(kwtCitizensResidentsDTO.getSpccsetypeCode());
//                }

                if (kwtCitizensResidentsDTO.getActiveFlag() != null) {
                    kwtCitizensResidentsEntity.setActiveFlag(kwtCitizensResidentsDTO.getActiveFlag());
                }

                if (kwtCitizensResidentsDTO.getCreatedBy() != null) {
                    kwtCitizensResidentsEntity.setCreatedBy(kwtCitizensResidentsDTO.getCreatedBy());
                }

                if (kwtCitizensResidentsDTO.getCreatedDate() != null) {
                    kwtCitizensResidentsEntity.setCreatedDate(kwtCitizensResidentsDTO.getCreatedDate());
                }

                if (kwtCitizensResidentsDTO.getLastUpdatedBy() != null) {
                    kwtCitizensResidentsEntity.setLastUpdatedBy(kwtCitizensResidentsDTO.getLastUpdatedBy());
                }

                if (kwtCitizensResidentsDTO.getLastUpdatedDate() != null) {
                    kwtCitizensResidentsEntity.setLastUpdatedDate(kwtCitizensResidentsDTO.getLastUpdatedDate());
                }

                if (kwtCitizensResidentsDTO.getAuditStatus() != null) {
                    kwtCitizensResidentsEntity.setAuditStatus(kwtCitizensResidentsDTO.getAuditStatus());
                }

                if (kwtCitizensResidentsDTO.getTabrecSerial() != null) {
                    kwtCitizensResidentsEntity.setTabrecSerial(kwtCitizensResidentsDTO.getTabrecSerial());
                }

                if (kwtCitizensResidentsDTO.getNonStatus() != null) {
                    kwtCitizensResidentsEntity.setNonStatus(kwtCitizensResidentsDTO.getNonStatus());
                }
                //            if (qualDto != null) {
                //                List<IPersonsInformationDTO> personsInformationDTOList =
                //                    kwtCitizensResidentsDTO.getPersonsInformationDTOList();


                doAdd(kwtCitizensResidentsEntity);

            } catch (Exception ex) {
                throw new ItemNotFoundException();
            }
            return kwtCitizensResidentsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> searchWithPaging(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            IKwtCitizensResidentsSearchDTO searchDTO = (IKwtCitizensResidentsSearchDTO)basicDTO;
            StringBuilder queryStr = new StringBuilder(buildSearchQueryString(searchDTO, false));
            IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();
            requestDTO.setSortColumnList(searchDTO.getPagingRequestDTO().getSortColumnList());

            //TODO apply sorting
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                queryStr.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = requestDTO.getSortColumnList().get(i);
                    queryStr.append(column);
                    if (!requestDTO.isSortAscending()) {
                        queryStr.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        queryStr.append(" , ");
                    }
                }
            } else {
                // queryStr.append(" order by o.fromDate desc");
            }
            System.out.println("...." + queryStr.toString());
            Query query = EM().createQuery(queryStr.toString());
            query.setHint("toplink.refresh", true);
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }

            List<KwtCitizensResidentsEntity> list = query.getResultList();
            if (list == null || list.size() == 0)
                throw new com.beshara.csc.sharedutils.business.exception.NoResultException();
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (KwtCitizensResidentsEntity kwtCitizensResidentsEntity : list) {
                arrayList.add(InfEntityConvertr.getKwtCitizensResidentsDTO(kwtCitizensResidentsEntity));
            }


            return arrayList;
        } catch (Exception e) {

            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long getCountSearchWithPaging(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createQuery(buildSearchQueryString((IKwtCitizensResidentsSearchDTO)basicDTO, true));
            Long result = (Long)query.getSingleResult();
            return result;
        } catch (Exception e) {

            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    private String buildSearchQueryString(IKwtCitizensResidentsSearchDTO dto, boolean toGetCountOnly) {
        try {
            StringBuilder ejbql = new StringBuilder("");
            if (toGetCountOnly) {
                ejbql.append("select count(o.civilId) from KwtCitizensResidentsEntity o where o.civilId is not null ");
            } else {
                ejbql.append("select o from KwtCitizensResidentsEntity o where o.civilId is not null ");
            }


            ejbql.append(" AND  o.nationality = " + IInfConstant.INf_NON_NATIONALITY + " ");

            if (dto.getSpecialCaseTypesDTO() == null) {
                ejbql.append(" AND  ( o.spccsetypeCode IN (" + IInfConstant.CAPTIVE_SON + " , " +
                             IInfConstant.MARTYR_SON + " , " + IInfConstant.WIDOW_OF_A_MARTYR + " , " + IInfConstant.CAPTIVE_WIFE+ " , " + IInfConstant.WIFE_MISSING+ ")");
                ejbql.append(" or  o.spccsetypeCode is null)");
            }

            else {
                ejbql.append(" AND  o.spccsetypeCode = " +
                             Long.valueOf(dto.getSpecialCaseTypesDTO().getCode().getKey()) + " ");
               
            }

            ejbql.append(" AND  o.nonStatus IS NOT NULL ");


            if (dto.getNonStatus() != null && !dto.getNonStatus().equals(IInfConstant.ALL_SELECTIONS)) {
                ejbql.append(" AND o.nonStatus = " + dto.getNonStatus() + " ");
            }


            if (dto.getCivilId() != null)
                ejbql.append(" AND o.civilId='" + dto.getCivilId() + "'");


            if (dto.getName() != null && !dto.getName().equals("")) {
                ejbql.append(" AND o.civilId IN ( Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 dto.getName()) + " ) ");

            }

            ////////////////


            System.out.println(ejbql.toString());


            return ejbql.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Boolean updatekwtCitizensResidentStatus(IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException,
                                                                                              SharedApplicationException {


        try {
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)kwtCitizensResidentsDTO1;
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode());
            if (kwtCitizensResidentsEntity == null) {
                throw new ItemNotFoundException();
            }

            if (kwtCitizensResidentsDTO.getNonStatus() != null) {
                kwtCitizensResidentsEntity.setNonStatus(kwtCitizensResidentsDTO.getNonStatus());

            }

            doUpdate(kwtCitizensResidentsEntity);

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

    public Boolean updatekwtCitizensResidentMaritalStatus(IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException,
                                                                                                     SharedApplicationException {


        try {
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)kwtCitizensResidentsDTO1;
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode());

            if (kwtCitizensResidentsEntity == null) {
                throw new ItemNotFoundException();
            }

            if (kwtCitizensResidentsDTO.getMaritalStatusDTO() != null) {
                MaritalStatusEntity maritalStatusEntity =
                    EM().find(MaritalStatusEntity.class, (IMaritalStatusEntityKey)kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode());
                kwtCitizensResidentsEntity.setMaritalStatusEntity(maritalStatusEntity);
            }

            doUpdate(kwtCitizensResidentsEntity);

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


    public IBasicDTO getKwtCitizensResidentsById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)id);
            if (kwtCitizensResidentsEntity == null) {
                throw new ItemNotFoundException();
            }
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                InfDTOFactory.createKwtCitizensResidentsDTO(kwtCitizensResidentsEntity);
            return kwtCitizensResidentsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    public IBasicDTO getSimpleKwtCitizensResidentsById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)id);
            if (kwtCitizensResidentsEntity == null) {
                throw new ItemNotFoundException();
            }
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
            kwtCitizensResidentsDTO = kwtCitizensResidentsDTO.getSimpleKwtCitizensResidentsDTO(kwtCitizensResidentsEntity);
            
            return kwtCitizensResidentsDTO;
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
     * webService method get maped value that return from services
     * @param wsParm
     * @param type
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy Kareem.Sayed
     */
    public String getMapedValueFromWS(String wsParm, String type) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            String queryString =
                "SELECT gn_map_data_pac.get_maped_value( " + type + " ,2  ," + wsParm + " ,1  ) from DUAL ";
            Query query = EM().createNativeQuery(queryString);
            Vector retVal = (Vector)query.getSingleResult();
            Object value = retVal.get(0);
            if (value != null)
                return value.toString();
            return null;
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
     * Simple Search in KwtCitizensResidents with Paging
     * @param IKwtCitizensResidentsSearchDTO
     * @return List
     * @author Amr Abdel Halim
     * @since 29-AUG-2016
     * */
    public List<IBasicDTO> simpleSearchWithPaging(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            IKwtCitizensResidentsSearchDTO searchDTO = (IKwtCitizensResidentsSearchDTO)basicDTO;
            StringBuilder queryStr = new StringBuilder(buildSimpleSearchQueryString(searchDTO, false));
            IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();
            requestDTO.setSortColumnList(searchDTO.getPagingRequestDTO().getSortColumnList());

            //TODO apply sorting
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                queryStr.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = requestDTO.getSortColumnList().get(i);
                    queryStr.append(column);
                    if (!requestDTO.isSortAscending()) {
                        queryStr.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        queryStr.append(" , ");
                    }
                }
            } else {
                // queryStr.append(" order by o.fromDate desc");
            }
            System.out.println("...." + queryStr.toString());
            Query query = EM().createQuery(queryStr.toString());
            query.setHint("toplink.refresh", true);
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }

            List<KwtCitizensResidentsEntity> list = query.getResultList();
            if (list == null || list.size() == 0)
                throw new com.beshara.csc.sharedutils.business.exception.NoResultException();
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (KwtCitizensResidentsEntity kwtCitizensResidentsEntity : list) {
                arrayList.add(InfEntityConvertr.getSimpleKwtCitizensResidentsDTO(kwtCitizensResidentsEntity));
            }


            return arrayList;
        } catch (Exception e) {

            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    /**
     * get Count for Simple Search in KwtCitizensResidents with Paging
     * @param IKwtCitizensResidentsSearchDTO
     * @return Long
     * @author Amr Abdel Halim
     * @since 29-AUG-2016
     * */
    public Long getCountSimpleSearchWithPaging(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createQuery(buildSimpleSearchQueryString((IKwtCitizensResidentsSearchDTO)basicDTO, true));
            Long result = (Long)query.getSingleResult();
            return result;
        } catch (Exception e) {

            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    /**
     * Simple buildSearchQueryString for Search
     * @param IKwtCitizensResidentsSearchDTO
     * @return Long
     * @author Amr Abdel Halim
     * @since 29-AUG-2016
     * */
    private String buildSimpleSearchQueryString(IKwtCitizensResidentsSearchDTO dto, boolean toGetCountOnly) {
        try {
            StringBuilder ejbql = new StringBuilder("");
            if (toGetCountOnly) {
                ejbql.append("select count(o.civilId) from KwtCitizensResidentsEntity o where o.civilId is not null ");
            } else {
                ejbql.append("select o from KwtCitizensResidentsEntity o where o.civilId is not null ");
            }

            Long nationalityCode = dto.getNationality();
            
            if(nationalityCode != null){
                ejbql.append(" AND  o.nationality = " + nationalityCode + " ");    
            }
            

            if (dto.getCivilId() != null)
                ejbql.append(" AND o.civilId='" + dto.getCivilId() + "'");


            if (dto.getName() != null && !dto.getName().equals("")) {
                ejbql.append(" AND o.civilId IN ( Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 dto.getName()) + " ) ");

            }

            ////////////////


            System.out.println(ejbql.toString());


            return ejbql.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
