package com.beshara.csc.gn.map.business.entity;


public class MapEntityKeyFactory {
    public static IDataEntityKey createDataEntityKey() {
        return new DataEntityKey();
    }

    public static IDataEntityKey createDataEntityKey(Long objtypeCode, 
                                                     Long socCode) {
        return new DataEntityKey(objtypeCode, socCode);
    }

    public static IMappedDataEntityKey createMappedDataEntityKey() {
        return new MappedDataEntityKey();
    }

    public static IMappedDataEntityKey createMappedDataEntityKey(Long objtype1Code, 
                                                                 Long objtype2Code, 
                                                                 Long soc1Code, 
                                                                 String soc1Value, 
                                                                 Long soc2Code, 
                                                                 String soc2Value) {
        return new MappedDataEntityKey(objtype1Code, objtype2Code, soc1Code, 
                                       soc1Value, soc2Code, soc2Value);
    }

    public static IObjectTypesEntityKey createObjectTypesEntityKey() {
        return new ObjectTypesEntityKey();
    }

    public static IObjectTypesEntityKey createObjectTypesEntityKey(Long objtypeCode) {
        return new ObjectTypesEntityKey(objtypeCode);
    }

    public static ISocietiesEntityKey createSocietiesEntityKey() {
        return new SocietiesEntityKey();
    }

    public static ISocietiesEntityKey createSocietiesEntityKey(Long socCode) {
        return new SocietiesEntityKey(socCode);
    }

    public static IRelationsEntityKey createRelationsEntityKey(Long objtypeCode, Long soc1Code, Long soc2Code) {
        return new RelationsEntityKey(objtypeCode,soc1Code,soc2Code);
    }
    public static IRelationsEntityKey createRelationsEntityKey() {
        return new RelationsEntityKey();
    }


    public static ISocietyRelationTypesEntityKey createSocietyRelationTypesEntityKey(Long reltypeCode) {
        return new SocietyRelationTypesEntityKey(reltypeCode);
    }
    public static ISocietyRelationTypesEntityKey createSocietyRelationTypesEntityKey() {
        return new SocietyRelationTypesEntityKey();
    }
}
