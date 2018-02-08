package com.beshara.csc.gn.map.business.client.test;

import com.beshara.base.entity.*;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.map.business.client.IDataClient;
import com.beshara.csc.gn.map.business.client.IMappedDataClient;
import com.beshara.csc.gn.map.business.client.IObjectTypesClient;
import com.beshara.csc.gn.map.business.client.ISocietiesClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IDataDTO;
import com.beshara.csc.gn.map.business.dto.IMappedDataDTO;
import com.beshara.csc.gn.map.business.dto.IMappedValueDTO;
import com.beshara.csc.gn.map.business.dto.IObjectTypesDTO;
import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.gn.map.business.entity.IObjectTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

public class MapModuleTest {
    public MapModuleTest() {
    }

    public static void main(String[] s) {
        try {
            IDataClient dataClient = MapClientFactory.getDataClient();
            List<IBasicDTO> dataList = dataClient.getAll();
            for (IBasicDTO dto: dataList) {
                System.out.println(((IDataDTO)dto).getSqlStatement());
            }
            IDataDTO dataDto = MapDTOFactory.createDataDTO();
            dataDto.setSocCode(new Long(1));
            dataDto.setObjtypeCode(new Long(1));
            dataDto.setSqlStatement("SELECT * FROM GN_MAP_SOCIETIES");
            //dataClient.createData ( dataDto ) ; 
            IDataDTO dataDto3 = MapDTOFactory.createDataDTO();
            dataDto3.setSocCode(new Long(2));
            dataDto3.setObjtypeCode(new Long(2));
            dataDto3.setSqlStatement("SELECT * FROM GN_MAP_OBJECT_TYPES");
            //dataClient.createData ( dataDto3 ) ; 
            //DataDTO dataDto2 = dataClient.getById ( ) 
            List<IBasicDTO> mappedValues = 
                dataClient.listByObjectTypeAndSoc(new Long(1), new Long(1));
            //List<MappedValueDTO>mappedValues = dataClient.ListByTypeAndSocFiltered ( new Long ( 1 ) , new Long ( 1 ) , "f%" ) ; 
            for (IBasicDTO dto: mappedValues) {
                System.out.println(((IMappedValueDTO)dto).getStrCode());
                System.out.println(dto.getName());
                System.out.println(((IMappedValueDTO)dto).getHasMappedValues());
            }
            IObjectTypesClient objClient = 
                MapClientFactory.getObjectTypesClient();
            //ObjectTypesDTO objDto = objClient.getById ( new Long ( 5 ) ) ; 
            //objDto.setObjtypeName ( "NEW OBJECT_UPDATED" ) ; 
            //objClient.removeObjectTypes ( objDto ) ; 
            ISocietiesClient socClient = MapClientFactory.getSocietiesClient();
            //SocietiesDTO socDto = MapDTOFactory.createSocietiesDTO ( ) ; 
            //SocietiesDTO socDto = socClient.getById ( new Long ( 8 ) ) ; 
            //socDto.setSocName ( "NEW SOCIETY_UPDATED" ) ; 
            //socClient.removeSocieties ( socDto ) ; 
            IObjectTypesDTO objDto1 = MapDTOFactory.createObjectTypesDTO();
            objDto1.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
            IObjectTypesDTO objDto2 = MapDTOFactory.createObjectTypesDTO();
            objDto2.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(2L));
            IObjectTypesDTO objDto3 = MapDTOFactory.createObjectTypesDTO();
            objDto3.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(3L));
            ISocietiesDTO socDto1 = MapDTOFactory.createSocietiesDTO();
            //socDto1.setCode ( new Long ( 1 ) ) ; 
            ISocietiesDTO socDto2 = MapDTOFactory.createSocietiesDTO();
            //socDto2.setSocCode ( new Long ( 2 ) ) ; 
            ISocietiesDTO socDto3 = MapDTOFactory.createSocietiesDTO();
            //socDto3.setSocCode ( new Long ( 3 ) ) ; 
            IMappedDataClient mDataClient = 
                MapClientFactory.getMappedDataClient();
            IMappedDataDTO mDataDto1 = MapDTOFactory.createMappedDataDTO();
            mDataDto1.setObjtype1Code(objDto1);
            mDataDto1.setSoc1Code(socDto1);
            mDataDto1.setSoc1Value("VALUE1");
            mDataDto1.setObjtype2Code(objDto1);
            mDataDto1.setSoc2Code(socDto2);
            mDataDto1.setSoc2Value("VALUE5");
            mDataDto1.setMapStatus(new Long(1));
            IMappedDataDTO mDataDto2 = MapDTOFactory.createMappedDataDTO();
            mDataDto2.setObjtype1Code(objDto1);
            mDataDto2.setSoc1Code(socDto1);
            mDataDto2.setSoc1Value("VALUE1");
            mDataDto2.setObjtype2Code(objDto1);
            mDataDto2.setSoc2Code(socDto2);
            mDataDto2.setSoc2Value("VALUE6");
            mDataDto2.setMapStatus(new Long(1));
            IMappedDataDTO mDataDto3 = MapDTOFactory.createMappedDataDTO();
            mDataDto3.setObjtype1Code(objDto1);
            mDataDto3.setSoc1Code(socDto1);
            mDataDto3.setSoc1Value("VALUE1");
            mDataDto3.setObjtype2Code(objDto1);
            mDataDto3.setSoc2Code(socDto2);
            mDataDto3.setSoc2Value("VALUE7");
            mDataDto3.setMapStatus(new Long(1));
            ArrayList<IMappedDataDTO> mappedDataList = 
                new ArrayList<IMappedDataDTO>();
            mappedDataList.add(mDataDto1);
            mappedDataList.add(mDataDto2);
            mappedDataList.add(mDataDto3);
            //mDataClient.updateMappedDataList ( mappedDataList ) ; 
            //mDataClient.updateMappedDataList ( mappedDataList ) ; 
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }
}
