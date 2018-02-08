package com.beshara.csc.gn.map.business.client.test;

import com.beshara.base.entity.*;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.map.business.client.IDataClient;
import com.beshara.csc.gn.map.business.client.IMappedDataClient;
import com.beshara.csc.gn.map.business.client.IObjectTypesClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IDataDTO;
import com.beshara.csc.gn.map.business.dto.IMappedDataDTO;
import com.beshara.csc.gn.map.business.dto.IMappedValueDTO;
import com.beshara.csc.gn.map.business.dto.IObjectTypesDTO;
import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.gn.map.business.entity.IObjectTypesEntityKey;
import com.beshara.csc.gn.map.business.entity.ISocietiesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class MyDataClientTest {
    public MyDataClientTest() {
    }

    public static void main(String[] args) { ///////test insert object type//////////// 
        IObjectTypesDTO objDto = MapDTOFactory.createObjectTypesDTO();
        objDto.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(3L));
        objDto.setName("new Object");
        // objDto.setCreatedBy ( "Dina" ) ; 
        objDto.setCreatedDate(Timestamp.valueOf("2007-10-28 4:56:10"));
        IObjectTypesClient objClient = MapClientFactory.getObjectTypesClient();
        //objClient.createObjectTypes ( objDto ) ; 
        ////////test update objectType 
        objDto.setName("ObjectUpdated");
        // objClient.updateObjectTypes ( objDto ) ; 
        ///// test remove ObjectType 
        // objClient.removeObjectTypes ( objDto ) ; 
        IDataClient dataClient = MapClientFactory.getDataClient();
        IDataDTO dto = MapDTOFactory.createDataDTO();
        dto.setObjtypeCode(new Long(1));
        dto.setSocCode(new Long(2));
        dto.setSqlStatement("select OBJTYPE_CODE , OBJTYPE_NAME from GN_MAP_OBJECT_TYPES");
        // dto.setCreatedBy ( "Dina" ) ; 
        dto.setCreatedDate(Timestamp.valueOf("2007-10-28 12:56:10"));
        IDataDTO dto2 = MapDTOFactory.createDataDTO();
        dto2.setObjtypeCode(new Long(2));
        dto2.setSocCode(new Long(1));
        dto2.setSqlStatement("select OBJTYPE_CODE , OBJTYPE_NAME from GN_MAP_OBJECT_TYPES");
        // dto2.setCreatedBy ( "Dina" ) ; 
        dto2.setCreatedDate(Timestamp.valueOf("2007-10-28 12:56:10"));
        IDataDTO dto3 = MapDTOFactory.createDataDTO();
        dto3.setObjtypeCode(new Long(2));
        dto3.setSocCode(new Long(2));
        dto3.setSqlStatement("select OBJTYPE_CODE , OBJTYPE_NAME from GN_MAP_OBJECT_TYPES");
        // dto3.setCreatedBy ( "Dina" ) ; 
        dto3.setCreatedDate(Timestamp.valueOf("2007-10-28 12:56:10"));
        try { //DataDTO createdDto = dataClient.createData ( dto ) ; 
            // IDataDTO createdDto2 = dataClient.createData ( dto2 ) ; 
            // IDataDTO createdDto3 = dataClient.createData ( dto3 ) ; 
            //List<ISocietiesDTO> dataList = dataClient.listByObjectType ( new Long ( 1 ) ) ; 
            //for ( ISocietiesDTO data:dataList ) { 
            //System.out.println ( data.getSocName ( ) ) ; 
            // } 
            List<IBasicDTO> resultList = 
                dataClient.listByObjectTypeAndSoc(new Long(1), new Long(1));
            for (IBasicDTO item: resultList) {
                System.out.println(((IMappedValueDTO)item).getStrCode());
                System.out.println(((IMappedValueDTO)item).getName());
            } ////////////test createMappedDataList///////////////// 
            IObjectTypesDTO mappedObjDto = 
                MapDTOFactory.createObjectTypesDTO();
            mappedObjDto.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
            ISocietiesDTO mappedSocDto = MapDTOFactory.createSocietiesDTO();
            mappedSocDto.setCode(MapEntityKeyFactory.createSocietiesEntityKey(1L));
            IObjectTypesDTO mappedObjDto2 = 
                MapDTOFactory.createObjectTypesDTO();
            mappedObjDto2.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
            ISocietiesDTO mappedSocDto2 = MapDTOFactory.createSocietiesDTO();
            mappedSocDto2.setCode(MapEntityKeyFactory.createSocietiesEntityKey(2L));
            IObjectTypesDTO mappedObjDto3 = 
                MapDTOFactory.createObjectTypesDTO();
            mappedObjDto3.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(2L));
            ISocietiesDTO mappedSocDto3 = MapDTOFactory.createSocietiesDTO();
            mappedSocDto3.setCode(MapEntityKeyFactory.createSocietiesEntityKey(2L));
            IMappedDataDTO mappedDto = MapDTOFactory.createMappedDataDTO();
            mappedDto.setObjtype1Code(mappedObjDto);
            mappedDto.setSoc1Code(mappedSocDto);
            mappedDto.setSoc1Value("main value1");
            mappedDto.setObjtype2Code(mappedObjDto2);
            mappedDto.setSoc2Code(mappedSocDto2);
            mappedDto.setSoc2Value("updated final2");
            mappedDto.setMapStatus(new Long(1));
            // mappedDto.setCreatedBy ( "Dina" ) ; 
            mappedDto.setCreatedDate(Timestamp.valueOf("2007-10-28 12:56:10"));
            IMappedDataDTO mappedDto2 = MapDTOFactory.createMappedDataDTO();
            mappedDto2.setObjtype1Code(mappedObjDto);
            mappedDto2.setSoc1Code(mappedSocDto);
            mappedDto2.setSoc1Value("new value11");
            mappedDto2.setObjtype2Code(mappedObjDto3);
            mappedDto2.setSoc2Code(mappedSocDto3);
            mappedDto2.setSoc2Value("updated final");
            mappedDto2.setMapStatus(new Long(1));
            // mappedDto2.setCreatedBy ( "Dina" ) ; 
            mappedDto2.setCreatedDate(Timestamp.valueOf("2007-10-28 12:56:10"));
            IMappedDataDTO mappedDto3 = MapDTOFactory.createMappedDataDTO();
            mappedDto3.setObjtype1Code(mappedObjDto);
            mappedDto3.setSoc1Code(mappedSocDto);
            mappedDto3.setSoc1Value("new value11");
            mappedDto3.setObjtype2Code(mappedObjDto2);
            mappedDto3.setSoc2Code(mappedSocDto2);
            mappedDto3.setSoc2Value("new update2");
            mappedDto3.setMapStatus(new Long(1));
            // mappedDto3.setCreatedBy ( "Dina" ) ; 
            mappedDto3.setCreatedDate(Timestamp.valueOf("2007-10-28 12:56:10"));
            ArrayList<IBasicDTO> list = new ArrayList();
            mappedDto.setObjtype1Code(mappedObjDto);
            mappedDto.setSoc1Code(mappedSocDto);
            mappedDto.setSoc2Code(mappedSocDto2);
            mappedDto.setSoc1Value("main value1");
            list.add(mappedDto);
            //list.add ( mappedDto2 ) ; 
            //list.add ( mappedDto3 ) ; 
            IMappedDataClient mappedClient = 
                MapClientFactory.getMappedDataClient();
            ////////////test update for mappedDataClient.updateMappedData ( ) /////////// 
            System.out.println(mappedClient.updateMappedDataList(list));
        } catch (DataBaseException dbe) {
            dbe.printStackTrace();
        } catch (ItemNotFoundException e) { // TODO 
        } catch (SharedApplicationException e) { // TODO 
        } //catch ( FinderException e ) { 
        //// TODO 
        // } 
    }
}
