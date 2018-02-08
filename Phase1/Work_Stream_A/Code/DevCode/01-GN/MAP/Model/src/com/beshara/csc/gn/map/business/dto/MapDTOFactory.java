package com.beshara.csc.gn.map.business.dto;


import com.beshara.csc.gn.map.business.entity.DataEntity;
import com.beshara.csc.gn.map.business.entity.MappedDataEntity;
import com.beshara.csc.gn.map.business.entity.ObjectTypesEntity;
import com.beshara.csc.gn.map.business.entity.SocietiesEntity;


public class MapDTOFactory {
    public static IDataDTO createDataDTO() {
        return new DataDTO();
    }

    public static IDataDTO createDataDTO(DataEntity dataEntity) {
        return new DataDTO(dataEntity);
    }

    public static IMapClientDTO createMapClientDTO() {
        return new MapClientDTO();
    }

    public static IMappedDataDTO createMappedDataDTO() {
        return new MappedDataDTO();
    }

    public static IMappedDataDTO createMappedDataDTO(MappedDataEntity mappedDataEntity) {
        return new MappedDataDTO(mappedDataEntity);
    }

    public static IMappedValueDTO createMappedValueDTO() {
        return new MappedValueDTO();
    }

    public static IMappedValueDTO createMappedValueDTO(Long longCode, 
                                                       String name) {
        return new MappedValueDTO(longCode, name);
    }

    public static IMappedValueDTO createMappedValueDTO(String strCode, 
                                                       String name) {
        return new MappedValueDTO(strCode, name);
    }

    public static IObjectTypesDTO createObjectTypesDTO() {
        return new ObjectTypesDTO();
    }

    public static IObjectTypesDTO createObjectTypesDTO(Long code, 
                                                       String name) {
        return new ObjectTypesDTO(code, name);
    }

    public static IObjectTypesDTO createObjectTypesDTO(ObjectTypesEntity objectTypesEntity) {
        return new ObjectTypesDTO(objectTypesEntity);
    }

    public static ISocietiesDTO createSocietiesDTO() {
        return new SocietiesDTO();
    }

    public static ISocietiesDTO createSocietiesDTO(SocietiesEntity societiesEntity) {
        return new SocietiesDTO(societiesEntity);
    }

    public static ISocietiesDTO createSocietiesDTO(Long socCode, 
                                                   String socName) {
        return new SocietiesDTO(socCode, socName);
    }
    
    public static IDataSearchDTO createDataSearchDTO() {
        return new DataSearchDTO();
    }

    public static IRelationsDTO createRelationsDTO() {
        return new RelationsDTO();
    }

    public static ISocietyRelationTypesDTO createSocietyRelationTypesDTO() {
        return new SocietyRelationTypesDTO();
    }
}
