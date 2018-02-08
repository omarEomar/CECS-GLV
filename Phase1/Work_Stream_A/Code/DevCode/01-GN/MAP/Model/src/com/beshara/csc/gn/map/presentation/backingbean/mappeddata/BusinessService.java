package com.beshara.csc.gn.map.presentation.backingbean.mappeddata;


import com.beshara.csc.gn.map.business.client.IDataClient;
import com.beshara.csc.gn.map.business.client.ISocietiesClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IMappedDataDTO;
import com.beshara.csc.gn.map.business.dto.IMappedValueDTO;
import com.beshara.csc.gn.map.business.dto.IObjectTypesDTO;
import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BusinessService {
    private static List calculateMappedValue;
    private static int mappedNo = 0;

    public BusinessService() {
    }

    public static List buildSocitiesList(Long typeId) throws DataBaseException, 
                                                             SharedApplicationException {
        ISocietiesClient societiesClient = 
            MapClientFactory.getSocietiesClient();
        return societiesClient.listByObjectType(typeId);
    }

    public static List buildSocityValueFromFilterd(Long objectType, 
                                                   Long socity1Code, 
                                                   Long socity2Code, 
                                                   String value, 
                                                   Integer type) {
        IDataClient client = MapClientFactory.getDataClient();
        try {
            if (type == 0) {
                return client.listByTypeAndSoc1FilteredByCode(objectType, 
                                                              socity1Code, 
                                                              socity2Code, 
                                                              value);
            } else if (type == 1) {
                return client.listByTypeAndSoc1FilteredByName(objectType, 
                                                              socity1Code, 
                                                              socity2Code, 
                                                              value);
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }

    public static List buildSocitiesValueToList(Long objtypeCode, 
                                                Long soc2Code, Long soc1Code, 
                                                String soc1Value) {
        System.out.println(objtypeCode + " , soc2Code" + soc2Code + 
                           " , soc1Code" + soc1Code + " , " + soc1Value);
        IDataClient client = MapClientFactory.getDataClient();
        try {
            return client.ListMappedT0ByTypeAndSoc2Code(objtypeCode, soc2Code, 
                                                        soc1Code, soc1Value);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List buildDivList(Long objtypeCode, Long soc2Code, 
                                    Long soc1Code, String soc1Value) {
        if (objtypeCode != null && soc2Code != null && soc1Code != null && 
            !soc1Value.equalsIgnoreCase("") && soc1Value != null) {
            IDataClient client = MapClientFactory.getDataClient();
            try {
                return client.ListByTypeAndSoc2Code(objtypeCode, soc2Code, 
                                                    soc1Code, soc1Value);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList();
    }

    public static List buildDivTree(Long objtypeCode, Long soc2Code, 
                                    Long soc1Code, String soc1Value) {
        
        if (objtypeCode != null && soc2Code != null && soc1Code != null && 
            !soc1Value.equalsIgnoreCase("") && soc1Value != null) {
            IDataClient client = MapClientFactory.getDataClient();
            try {
                return client.getTreeByTypeAndSoc2Code(objtypeCode, soc2Code, 
                                                    soc1Code, soc1Value);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList();
    }


    
    public static List searchDivList(Long objtypeCode, Long soc2Code, 
                                     Long soc1Code, String soc1Value, 
                                     String param) {
        if (objtypeCode != null && soc2Code != null && soc1Code != null && 
            !soc1Value.equalsIgnoreCase("") && soc1Value != null && 
            !param.equalsIgnoreCase("") && param != null) {
            IDataClient client = MapClientFactory.getDataClient();
            try {
                return client.ListByTypeAndSoc2CodeFiltered(objtypeCode, 
                                                            soc2Code, soc1Code, 
                                                            soc1Value, param);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList();
    }

    public static IMappedDataDTO buildGraphDTO(Long objtypeCode, Long soc1Code, 
                                               Long soc2Code, 
                                               String soc1Value) {
        IMappedDataDTO insertionDTO = MapDTOFactory.createMappedDataDTO();
        IObjectTypesDTO obj1DTO = MapDTOFactory.createObjectTypesDTO();
        ISocietiesDTO soc_from = MapDTOFactory.createSocietiesDTO();
        ISocietiesDTO soc_to = MapDTOFactory.createSocietiesDTO();
        ISocietiesDTO socfromValue = MapDTOFactory.createSocietiesDTO();
        obj1DTO.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(objtypeCode));
        soc_from.setCode(MapEntityKeyFactory.createSocietiesEntityKey(soc1Code));
        soc_to.setCode(MapEntityKeyFactory.createSocietiesEntityKey(soc2Code));
        insertionDTO.setObjtype1Code(obj1DTO);
        insertionDTO.setObjtype2Code(obj1DTO);
        insertionDTO.setSoc1Code(soc_from);
        insertionDTO.setSoc2Code(soc_to);
        insertionDTO.setSoc1Value(soc1Value);
        insertionDTO.setMapStatus(new Long(1));
        //insertionDTO.setCreatedBy ( "555555" ) ; 
        //insertionDTO.setLastUpdatedBy ( "888888" ) ; 
        //insertionDTO.setCreatedDate ( new Timestamp ( 12 , 22 , 22 , 22 , 55 , 2 , 2 ) ) ; 
        //insertionDTO.setLastUpdatedDate ( new Timestamp ( 12 , 22 , 22 , 22 , 55 , 2 , 2 ) ) ; 
        return insertionDTO;
    }

    public static Boolean saveDivList(List<IMappedDataDTO> toBeInsert) {
        return false;
    }

    public static List buildSocitiesValueList(Long objectTypeId, 
                                              Long societyCode1) {
        IDataClient client = MapClientFactory.getDataClient();
        try {
            return client.listByObjectTypeAndSoc(objectTypeId, societyCode1);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int calculateMappedRecord(List totalList) {
        int number = 0;
        Iterator it = totalList.iterator();
        while (it.hasNext()) {
            Boolean mapped = ((IMappedValueDTO)it.next()).getHasMappedValues();
            if (mapped)
                number++;
        }
        return number;
    }

    public static List buildSocitiesTOValueList(Long objectTypeId, 
                                                Long societyCode1, 
                                                Long societyCode2) {
        IDataClient client = MapClientFactory.getDataClient();
        try {
            calculateMappedValue = 
                    client.ListByTypeAndSoc1AndSoc2(objectTypeId, societyCode1, 
                                                    societyCode2);
            setMappedNo(calculateMappedRecord(calculateMappedValue));
            return calculateMappedValue;
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
        }
        return null;
    }
    // filter by mapped type
    public static List buildSocitiesTOValueList(Long objectTypeId, 
                                                Long societyCode1, 
                                                Long societyCode2,Boolean mapped) {
        IDataClient client = MapClientFactory.getDataClient();
        try {
            calculateMappedValue = 
                    client.ListByTypeAndSoc1AndSoc2AndMappedFilter(objectTypeId, societyCode1, 
                                                    societyCode2,mapped);
            setMappedNo(calculateMappedRecord(calculateMappedValue));
            return calculateMappedValue;
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
        }
        return null;
    }


    public static List buildSocitiesListTo(List<ISocietiesDTO> socities, 
                                           String currentSelected) throws DataBaseException, 
                                                                          SharedApplicationException {
        for (ISocietiesDTO dto: socities) {
            if (dto.getCode().getKey().toString().equals(currentSelected)) {
                socities.remove(dto);
                break;
            }
        }
        return socities;
    }

    public List getCalculateMappedValue() {
        return calculateMappedValue;
    }

    public static void setMappedNo(int mappedNo) {
        BusinessService.mappedNo = mappedNo;
    }

    public static int getMappedNo() {
        return BusinessService.mappedNo;
    }
}
