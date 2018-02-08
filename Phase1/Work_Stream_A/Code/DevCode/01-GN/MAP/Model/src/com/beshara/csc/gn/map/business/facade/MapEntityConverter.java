package com.beshara.csc.gn.map.business.facade;

import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IDataDTO;
import com.beshara.csc.gn.map.business.dto.IMappedDataDTO;
import com.beshara.csc.gn.map.business.dto.IObjectTypesDTO;
import com.beshara.csc.gn.map.business.dto.IRelationsDTO;
import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.gn.map.business.dto.ISocietyRelationTypesDTO;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.gn.map.business.dto.ObjectTypesDTO;
import com.beshara.csc.gn.map.business.dto.SocietiesDTO;
import com.beshara.csc.gn.map.business.dto.SocietyRelationTypesDTO;
import com.beshara.csc.gn.map.business.entity.DataEntity;
import com.beshara.csc.gn.map.business.entity.IObjectTypesEntityKey;
import com.beshara.csc.gn.map.business.entity.ISocietiesEntityKey;
import com.beshara.csc.gn.map.business.entity.ISocietyRelationTypesEntityKey;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.business.entity.MappedDataEntity;
import com.beshara.csc.gn.map.business.entity.ObjectTypesEntity;
import com.beshara.csc.gn.map.business.entity.ObjectTypesEntityKey;
import com.beshara.csc.gn.map.business.entity.RelationsEntity;
import com.beshara.csc.gn.map.business.entity.SocietiesEntity;
import com.beshara.csc.gn.map.business.entity.SocietiesEntityKey;
import com.beshara.csc.gn.map.business.entity.SocietyRelationTypesEntity;
import com.beshara.csc.gn.map.business.entity.SocietyRelationTypesEntityKey;


public final class MapEntityConverter {
    private MapEntityConverter() {
    }

    public static IDataDTO getDataDTO(DataEntity dataEntity) {
        IDataDTO dto = MapDTOFactory.createDataDTO();
        fillDataDTO(dto, dataEntity);
        return dto;
    }

    public static void fillDataDTO(IDataDTO dto, DataEntity dataEntity) {
        dto.setCode(MapEntityKeyFactory.createDataEntityKey(dataEntity.getObjtypeCode(), 
                                                            dataEntity.getSocCode()));
        dto.setObjtypeCode(dataEntity.getObjtypeCode());
        dto.setSocCode(dataEntity.getSocCode());
        dto.setSqlStatement(dataEntity.getSqlStatement());
        dto.setCreatedBy(dataEntity.getCreatedBy());
        dto.setCreatedDate(dataEntity.getCreatedDate());
        dto.setLastUpdatedBy(dataEntity.getLastUpdatedBy());
        dto.setLastUpdatedDate(dataEntity.getLastUpdatedDate());
    }

    public static IMappedDataDTO getMappedDataDTO(MappedDataEntity mappedDataEntity) {
        IMappedDataDTO dto = MapDTOFactory.createMappedDataDTO();
        fillMappedDataDTO(dto, mappedDataEntity);
        return dto;
    }

    public static void fillMappedDataDTO(IMappedDataDTO dto, 
                                         MappedDataEntity mappedDataEntity) {
        dto.setObjtype1Code(MapEntityConverter.getObjectTypesDTO(mappedDataEntity.getDataEntity().getObjectTypesEntity()));
        dto.setSoc1Code(MapEntityConverter.getSocietiesDTO(mappedDataEntity.getDataEntity().getSocietiesEntity()));
        dto.setSoc1Value(mappedDataEntity.getSoc1Value());
        dto.setObjtype2Code(MapEntityConverter.getObjectTypesDTO(mappedDataEntity.getDataEntity1().getObjectTypesEntity()));
        dto.setSoc2Code(MapEntityConverter.getSocietiesDTO(mappedDataEntity.getDataEntity1().getSocietiesEntity()));
        dto.setSoc2Value(mappedDataEntity.getSoc2Value());
        dto.setMapStatus(mappedDataEntity.getMapStatus());
        dto.setCreatedBy(mappedDataEntity.getCreatedBy());
        dto.setCreatedDate(mappedDataEntity.getCreatedDate());
        dto.setLastUpdatedBy(mappedDataEntity.getLastUpdatedBy());
        dto.setLastUpdatedDate(mappedDataEntity.getLastUpdatedDate());
    }

    public static IObjectTypesDTO getObjectTypesDTO(ObjectTypesEntity objectTypesEntity) {
        IObjectTypesDTO dto = MapDTOFactory.createObjectTypesDTO();
        fillObjectTypesDTO(dto, objectTypesEntity);
        return dto;
    }

    public static void fillObjectTypesDTO(IObjectTypesDTO dto, 
                                          ObjectTypesEntity objectTypesEntity) {
        dto.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(objectTypesEntity.getObjtypeCode()));
        dto.setName(objectTypesEntity.getObjtypeName());
        dto.setCreatedBy(objectTypesEntity.getCreatedBy());
        dto.setCreatedDate(objectTypesEntity.getCreatedDate());
        dto.setLastUpdatedBy(objectTypesEntity.getLastUpdatedBy());
        dto.setLastUpdatedDate(objectTypesEntity.getLastUpdatedDate());
    }

    public static ISocietiesDTO getSocietiesDTO(SocietiesEntity societiesEntity) {
        ISocietiesDTO dto = MapDTOFactory.createSocietiesDTO();
        fillSocietiesDTO(dto, societiesEntity);
        return dto;
    }

    public static void fillSocietiesDTO(ISocietiesDTO dto, 
                                        SocietiesEntity societiesEntity) {
        dto.setCode(MapEntityKeyFactory.createSocietiesEntityKey(societiesEntity.getSocCode()));
        dto.setName(societiesEntity.getSocName());
        dto.setCreatedBy(societiesEntity.getCreatedBy());
        dto.setCreatedDate(societiesEntity.getCreatedDate());
        dto.setLastUpdatedBy(societiesEntity.getLastUpdatedBy());
        dto.setLastUpdatedDate(societiesEntity.getLastUpdatedDate());
        dto.setMinCode(societiesEntity.getMinCode());
        dto.setSocietiesStatus(societiesEntity.getSocietiesStatus());
        
    }
    
    
    public static IRelationsDTO getRelationsDTO(RelationsEntity relationsEntity) {
        IRelationsDTO dto = MapDTOFactory.createRelationsDTO();
        fillRelationsDTO(dto, relationsEntity);
        return dto;
    }

    public static void fillRelationsDTO(IRelationsDTO dto, RelationsEntity relationsEntity) {
        dto.setCode(MapEntityKeyFactory.createRelationsEntityKey(relationsEntity.getObjtypeCode(), 
                                                            relationsEntity.getSoc1Code(),relationsEntity.getSoc2Code()));
        dto.setObjtypeCode(relationsEntity.getObjtypeCode());
        dto.setSoc1Code(relationsEntity.getSoc1Code());
        dto.setSoc2Code(relationsEntity.getSoc2Code());
        dto.setCreatedBy(relationsEntity.getCreatedBy());
        dto.setLastUpdatedBy(relationsEntity.getLastUpdatedBy());
        if(relationsEntity.getSoc1Code() != null){
            try{
             ISocietiesEntityKey societiesEntityKey = new SocietiesEntityKey(relationsEntity.getSoc1Code());
             SocietiesDTO societiesDTO = (SocietiesDTO)MapClientFactory.getSocietiesClient().getById(societiesEntityKey);
             dto.setSoc1DTO(societiesDTO);
            }catch(Exception e){
             dto.setSoc1DTO(null);
                e.printStackTrace();
            }
        }
        if(relationsEntity.getSoc2Code() != null){
            try{
             ISocietiesEntityKey societiesEntityKey = new SocietiesEntityKey(relationsEntity.getSoc2Code());
             SocietiesDTO societiesDTO = (SocietiesDTO)MapClientFactory.getSocietiesClient().getById(societiesEntityKey);
             dto.setSoc2DTO(societiesDTO);
            }catch(Exception e){
             dto.setSoc2DTO(null);
                e.printStackTrace();
            } 
        }
        if(relationsEntity.getObjtypeCode() != null){
            try{
             IObjectTypesEntityKey objectTypesEntityKey = new ObjectTypesEntityKey(relationsEntity.getObjtypeCode());
             ObjectTypesDTO objectTypesDTO = (ObjectTypesDTO)MapClientFactory.getObjectTypesClient().getById(objectTypesEntityKey);
             dto.setObjectTypesDTO(objectTypesDTO);
            }catch(Exception e){
              dto.setObjectTypesDTO(null);
                e.printStackTrace();
            } 
        }
        
        if(relationsEntity.getReltypeCode() != null){
            try{
             dto.setReltypeCode(relationsEntity.getReltypeCode());
             ISocietyRelationTypesEntityKey relationTypesEntityKey = new SocietyRelationTypesEntityKey(relationsEntity.getReltypeCode());
             SocietyRelationTypesDTO relationTypesDTO = (SocietyRelationTypesDTO)MapClientFactory.getSocietyRelationTypesClient().getById(relationTypesEntityKey);
             dto.setRelTypesDto(relationTypesDTO);
            }catch(Exception e){
              dto.setRelTypesDto(null);
                e.printStackTrace();
            } 
        }
       
    }
    

    public static ISocietyRelationTypesDTO getSocietyRelationTypesDTO(SocietyRelationTypesEntity societyRelationTypesEntity) {
        ISocietyRelationTypesDTO dto = MapDTOFactory.createSocietyRelationTypesDTO();
        fillSocietyRelationTypesDTO(dto, societyRelationTypesEntity);
        return dto;
    }

    public static void fillSocietyRelationTypesDTO(ISocietyRelationTypesDTO dto, SocietyRelationTypesEntity societyRelationTypesEntity) {
        dto.setCode(MapEntityKeyFactory.createSocietyRelationTypesEntityKey(societyRelationTypesEntity.getReltypeCode()));
        dto.setReltypeCode(societyRelationTypesEntity.getReltypeCode());
        dto.setReltypeName(societyRelationTypesEntity.getReltypeName());
        dto.setName(societyRelationTypesEntity.getReltypeName());
        dto.setReltypeSymbole(societyRelationTypesEntity.getReltypeSymbole());
        dto.setCreatedBy(societyRelationTypesEntity.getCreatedBy());
        dto.setLastUpdatedBy(societyRelationTypesEntity.getLastUpdatedBy());
       
    }
    

    
    
}
