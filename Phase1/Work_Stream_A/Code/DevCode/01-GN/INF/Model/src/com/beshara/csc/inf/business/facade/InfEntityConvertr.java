package com.beshara.csc.inf.business.facade;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IDecisionMakerTypesDTO;
import com.beshara.csc.inf.business.dto.IGenderCountryDTO;
import com.beshara.csc.inf.business.dto.IGenderTypesDTO;
import com.beshara.csc.inf.business.dto.IInfGradeTypesDTO;
import com.beshara.csc.inf.business.dto.IInfGradeValuesDTO;
import com.beshara.csc.inf.business.dto.IInfReasonDataDTO;
import com.beshara.csc.inf.business.dto.IInfReasonTypesDTO;
import com.beshara.csc.inf.business.dto.IKwMapDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IMaritalStatusDTO;
import com.beshara.csc.inf.business.dto.ISpecialCaseTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.DecisionMakerTypesEntity;
import com.beshara.csc.inf.business.entity.IGenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.ISpecialCaseTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntity;
import com.beshara.csc.inf.business.entity.InfGradeValuesEntity;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.emp.InfReasonDataEntity;
import com.beshara.csc.inf.business.entity.emp.InfReasonTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;


public final class InfEntityConvertr {

    private InfEntityConvertr() {
    }

    public static IInfReasonDataDTO getReasonDataDTO(InfReasonDataEntity reasonDataEntity) {
        IInfReasonDataDTO dto = InfDTOFactory.createReasonDataDTO();
        fillReasonDataDTO(dto, reasonDataEntity);
        return dto;
    }

    private static void fillReasonDataDTO(IInfReasonDataDTO dto, InfReasonDataEntity reasonDataEntity) {
        dto.setCode(InfEntityKeyFactory.createReasonDataEntityKey(reasonDataEntity.getResdatSerial(),
                                                                  reasonDataEntity.getReasonTypesEntity().getRestypeCode()));
        dto.setName(reasonDataEntity.getResdatDesc());
        dto.setResdatDesc(reasonDataEntity.getResdatDesc());
        dto.setCreatedBy(reasonDataEntity.getCreatedBy());
        dto.setCreatedDate(reasonDataEntity.getCreatedDate());
        dto.setLastUpdatedBy(reasonDataEntity.getLastUpdatedBy());
        dto.setLastUpdatedDate(reasonDataEntity.getLastUpdatedDate());
        dto.setTabrecSerial(reasonDataEntity.getTabrecSerial());
        dto.setReasonTypesDTO(getReasonTypesDTO(reasonDataEntity.getReasonTypesEntity()));

    }

    public static IInfReasonTypesDTO getReasonTypesDTO(InfReasonTypesEntity reasonTypesEntity) {
        IInfReasonTypesDTO dto = InfDTOFactory.createReasonTypesDTO();
        fillReasonTypesDTO(dto, reasonTypesEntity);
        return dto;
    }

    private static void fillReasonTypesDTO(IInfReasonTypesDTO dto, InfReasonTypesEntity reasonTypesEntity) {
        dto.setCode(InfEntityKeyFactory.createReasonTypesEntityKey(reasonTypesEntity.getRestypeCode()));
        dto.setName(reasonTypesEntity.getRestypeName());
        dto.setCreatedBy(reasonTypesEntity.getCreatedBy());
        dto.setCreatedDate(reasonTypesEntity.getCreatedDate());
        dto.setLastUpdatedBy(reasonTypesEntity.getLastUpdatedBy());
        dto.setLastUpdatedDate(reasonTypesEntity.getLastUpdatedDate());
        dto.setTabrecSerial(reasonTypesEntity.getTabrecSerial());
        dto.setRestypeCode(reasonTypesEntity.getRestypeCode());
        dto.setRestypeName(reasonTypesEntity.getRestypeName());
    }

    public static IDecisionMakerTypesDTO getDecisionMakerTypesDTO(DecisionMakerTypesEntity decisionMakerTypesEntity) {
        IDecisionMakerTypesDTO dto = InfDTOFactory.createDecisionMakerTypesDTO();
        fillDecisionMakerTypesDTO(dto, decisionMakerTypesEntity);
        return dto;
    }

    private static void fillDecisionMakerTypesDTO(IDecisionMakerTypesDTO dto,
                                                  DecisionMakerTypesEntity decisionMakerTypesEntity) {
        dto.setCode(InfEntityKeyFactory.createDecisionMakerTypesEntityKey(decisionMakerTypesEntity.getDecmkrtypeCode()));
        dto.setName(decisionMakerTypesEntity.getDecmkrtypeName());

        dto.setCreatedBy(decisionMakerTypesEntity.getCreatedBy());
        dto.setCreatedDate(decisionMakerTypesEntity.getCreatedDate());
        dto.setLastUpdatedBy(decisionMakerTypesEntity.getLastUpdatedBy());
        dto.setLastUpdatedDate(decisionMakerTypesEntity.getLastUpdatedDate());
        dto.setTabrecSerial(decisionMakerTypesEntity.getTabrecSerial());
        dto.setAuditStatus(decisionMakerTypesEntity.getAuditStatus());

    }
    
    ////
    
    public static IInfGradeTypesDTO getGradeTypesDTO(InfGradeTypesEntity gradeTypesEntity) {
        IInfGradeTypesDTO dto =  InfDTOFactory.createGradeTypesDTO();
            
        fillGradeTypesDTO(dto, gradeTypesEntity);
        return dto;    
        
        
       
    }
    
    private static void  fillGradeTypesDTO(IInfGradeTypesDTO dto,InfGradeTypesEntity gradeTypesEntity){
        dto.setCode(InfEntityKeyFactory.createGradeTypesEntityKey(gradeTypesEntity.getGradeTypeCode()));
        dto.setName(gradeTypesEntity.getGradeTypeName());
        dto.setGradeTypeName(gradeTypesEntity.getGradeTypeName());
        dto.setReferenceFlag(gradeTypesEntity.getReferenceFlag());
        if (gradeTypesEntity.getGradeTypeValType()!=null) {
            dto.setGradeTypeValType(gradeTypesEntity.getGradeTypeValType().toString());
        }
        dto.setFormula(gradeTypesEntity.getFormula());
        dto.setMinValue(gradeTypesEntity.getMinValue());
        dto.setMaxValue(gradeTypesEntity.getMaxValue());
        
        
        dto.setCreatedBy(gradeTypesEntity.getCreatedBy());
        dto.setCreatedDate(gradeTypesEntity.getCreatedDate());
        dto.setLastUpdatedBy(gradeTypesEntity.getLastUpdatedBy());
        dto.setLastUpdatedDate(gradeTypesEntity.getLastUpdatedDate());
        dto.setAuditStatus(gradeTypesEntity.getAuditStatus());

        
        
        
    }
    
    public static IInfGradeValuesDTO getInfGradeValuesDTO(InfGradeValuesEntity gradeValuesEntity) {
          IInfGradeValuesDTO dto =  InfDTOFactory.createInfGradeValuesDTO();
              
          filInfGradeValuesDTO(dto, gradeValuesEntity);
          return dto;    
                 
      }
    
    public static void  filInfGradeValuesDTO(IInfGradeValuesDTO dto,InfGradeValuesEntity gradeValuesEntity){
           dto.setCode(InfEntityKeyFactory.createInfGradeValuesEntityKey(gradeValuesEntity.getGradeTypeCode(),gradeValuesEntity.getValue()));
           dto.setValue(gradeValuesEntity.getValue());
           dto.setPercentageValue(gradeValuesEntity.getPercentageValue());
           dto.setGradeTypesDTO(getGradeTypesDTO(gradeValuesEntity.getGradeTypeEntity()));   
           dto.setCreatedBy(gradeValuesEntity.getCreatedBy());
           dto.setCreatedDate(gradeValuesEntity.getCreatedDate());
           dto.setLastUpdatedBy(gradeValuesEntity.getLastUpdatedBy());
           dto.setLastUpdatedDate(gradeValuesEntity.getLastUpdatedDate());
           dto.setAuditStatus(gradeValuesEntity.getAuditStatus());

  
       }
    public static IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO(KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        IKwtCitizensResidentsDTO dto = InfDTOFactory.createKwtCitizensResidentsDTO();
        fillkwtCitizensResidentsDTO(dto, kwtCitizensResidentsEntity);
        return dto;
    }

    private static void fillkwtCitizensResidentsDTO(IKwtCitizensResidentsDTO dto,
                                                    KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        dto.setCode(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(kwtCitizensResidentsEntity.getCivilId()));
        
      // the next line commented by M.abdelsabour because this is not the type of EntityKey to fix update problem find entity
      //  dto.setCode(InfEntityKeyFactory.createDecisionMakerTypesEntityKey(kwtCitizensResidentsEntity.getCivilId()));
      dto.setFirstName(kwtCitizensResidentsEntity.getFirstName());
      dto.setSecondName(kwtCitizensResidentsEntity.getSecondName());
      dto.setThirdName(kwtCitizensResidentsEntity.getThirdName());
      dto.setLastName(kwtCitizensResidentsEntity.getLastName());
      dto.setFamilyName(kwtCitizensResidentsEntity.getFamilyName());
      
        dto.setBirthDate(kwtCitizensResidentsEntity.getBirthDate());
        IBasicDTO maritalStatusDTO=null;
        try {
            maritalStatusDTO =
                    InfClientFactory.getMaritalStatusClient().getById(InfEntityKeyFactory.createMaritalStatusEntityKey(kwtCitizensResidentsEntity.getMarstatusCode()));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        dto.setMaritalStatusDTO((IMaritalStatusDTO)maritalStatusDTO);
        IBasicDTO countryDTO=null;
        try {
            countryDTO =
                    InfClientFactory.getGenderCountryClient().getById(InfEntityKeyFactory.createGenderCountryEntityKey(kwtCitizensResidentsEntity.getGentypeCode(),
                                                                                                                   kwtCitizensResidentsEntity.getNationality()));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        dto.setCountriesDTO((IGenderCountryDTO)countryDTO);
        
        if(kwtCitizensResidentsEntity.getSpecialCaseTypesEntity()!=null){
            ISpecialCaseTypesEntityKey ek =InfEntityKeyFactory.createSpecialCaseTypesEntityKey(kwtCitizensResidentsEntity.getSpccsetypeCode());
            ISpecialCaseTypesDTO  specialCaseTypesDTO=null;

            try {
             specialCaseTypesDTO = (ISpecialCaseTypesDTO)InfClientFactory.getSpecialCaseTypesClient().getById(ek);
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
           dto.setSpecialCaseTypesDTO(specialCaseTypesDTO); 
           
            
        }
        
        if(kwtCitizensResidentsEntity.getNonStatus()!=null){
            dto.setNonStatus(kwtCitizensResidentsEntity.getNonStatus());
        }
        
        
        if(kwtCitizensResidentsEntity.getPhonesNo()!=null){
            
            dto.setPhonesNo(kwtCitizensResidentsEntity.getPhonesNo());  
        }
        
        
        if(kwtCitizensResidentsEntity.getMobileNo()!=null){
            
            dto.setMobileNo(kwtCitizensResidentsEntity.getMobileNo());  
        }
        

        if(kwtCitizensResidentsEntity.getAddressInDetails()!=null){
            dto.setAddressInDetails(kwtCitizensResidentsEntity.getAddressInDetails());
        }
        
        if(kwtCitizensResidentsEntity.getMapCode()!=null){
            
            dto.setMapCode(kwtCitizensResidentsEntity.getMapCode());
        }
        
        if(kwtCitizensResidentsEntity.getStreetCode()!=null){
            
            dto.setStreetCode(kwtCitizensResidentsEntity.getStreetCode());
        }
        
        if(kwtCitizensResidentsEntity.getFlatNo()!=null){
            dto.setFlatNo(kwtCitizensResidentsEntity.getFlatNo());
        }
        if(kwtCitizensResidentsEntity.getFloorNo()!=null){
            
            dto.setFloorNo(kwtCitizensResidentsEntity.getFloorNo());
        }
        if(kwtCitizensResidentsEntity.getBuildingNo()!=null){
            
            dto.setBuildingNo(kwtCitizensResidentsEntity.getBuildingNo());
        }
        
        if(kwtCitizensResidentsEntity.getGentypeCode()!=null){
            IGenderTypesEntityKey ek= InfEntityKeyFactory.createGenderTypesEntityKey(kwtCitizensResidentsEntity.getGentypeCode()) ;
           IGenderTypesDTO genderDTO = null;

            try {
                genderDTO = (IGenderTypesDTO)InfClientFactory.getGenderTypesClient().getById(ek);
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
            if(genderDTO!=null)
            dto.setGenderTypesDTO(genderDTO);  
            
        }
        
        if(kwtCitizensResidentsEntity.getMapCode()!=null){
               IKwMapDTO mapDto=null;
               try {
                   mapDto =
                           (IKwMapDTO)InfClientFactory.getKwMapClient().getById(InfEntityKeyFactory.createKwMapEntityKey(kwtCitizensResidentsEntity.getMapCode()));
               } catch (SharedApplicationException e) {
                   e.printStackTrace();
               } catch (DataBaseException e) {
                   e.printStackTrace();
               }
                if(mapDto!=null)
               dto.setKwMapDTO(mapDto);
               
               
               }
        
        
        

    }
    
    public static IKwtCitizensResidentsDTO getSimpleKwtCitizensResidentsDTO(KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        IKwtCitizensResidentsDTO dto = InfDTOFactory.createKwtCitizensResidentsDTO();
        fillSimpleKwtCitizensResidentsDTO(dto, kwtCitizensResidentsEntity);
        return dto;
    }

    private static void fillSimpleKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO dto,
                                                          KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        dto.setCode(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(kwtCitizensResidentsEntity.getCivilId()));

        dto.setFirstName(kwtCitizensResidentsEntity.getFirstName());
        dto.setSecondName(kwtCitizensResidentsEntity.getSecondName());
        dto.setThirdName(kwtCitizensResidentsEntity.getThirdName());
        dto.setLastName(kwtCitizensResidentsEntity.getLastName());
        dto.setFamilyName(kwtCitizensResidentsEntity.getFamilyName());
        dto.setName(dto.getFullName());
        dto.setBirthDate(kwtCitizensResidentsEntity.getBirthDate());

        IBasicDTO countryDTO = null;
        try {
            countryDTO =
                    InfClientFactory.getGenderCountryClient().getById(InfEntityKeyFactory.createGenderCountryEntityKey(kwtCitizensResidentsEntity.getGentypeCode(),
                                                                                                                       kwtCitizensResidentsEntity.getNationality()));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        dto.setCountriesDTO((IGenderCountryDTO)countryDTO);


        if (kwtCitizensResidentsEntity.getGentypeCode() != null) {
            IGenderTypesEntityKey ek =
                InfEntityKeyFactory.createGenderTypesEntityKey(kwtCitizensResidentsEntity.getGentypeCode());
            IGenderTypesDTO genderDTO = null;

            try {
                genderDTO = (IGenderTypesDTO)InfClientFactory.getGenderTypesClient().getById(ek);
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
            if (genderDTO != null)
                dto.setGenderTypesDTO(genderDTO);

        }

    }
}
