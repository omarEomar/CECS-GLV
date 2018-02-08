package com.beshara.csc.gn.map.business.client.test;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.map.business.client.IMappedDataClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IMappedDataDTO;
import com.beshara.csc.gn.map.business.dto.IObjectTypesDTO;
import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class MappedDataFacadeClient {
    IMappedDataClient client = MapClientFactory.getMappedDataClient();

    public static void main(String[] args) {
        MappedDataFacadeClient c = new MappedDataFacadeClient();
        try { // Call any of the Remote methods below to access the EJB 
            // mappedDataFacade.mergeEntity ( entity ) ; 
            // mappedDataFacade.persistEntity ( entity ) ; 
            //System.out.println ( mappedDataFacade.getAll ( ) ) ; 
            // mappedDataFacade.createMappedData ( mappedDataDTO ) ; 
            // mappedDataFacade.updateMappedData ( mappedDataDTO ) ; 
            // mappedDataFacade.removeMappedData ( mappedDataDTO ) ; 
            // mappedDataFacade.getById ( id ) ; 
            // mappedDataFacade.getMappedDataList ( mappedDataDto ) ; 
            // mappedDataFacade.flushData ( ) ; 
            //c.getAll ( ) ; 
            //c.getMappedDataList ( ) ; 
            c.createMappedDataList();
            //c.removeMappedData ( ) ; 
            //c.updateMappedData ( ) ; 
            c.removeMappedData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("--------------------------------------------------");
        //getAll ( iMappedDataClient ) ; 
        // getById ( iMappedDataClient ) ; 
        // createMappedData ( iMappedDataClient ) ; 
        // createMappedDataList ( iMappedDataClient ) ; 
        // removeMappedData ( iMappedDataClient ) ; 
        // updateMappedData ( iMappedDataClient ) ; 
        //updateMappedDataList ( iMappedDataClient ) ; 
    }

    private static Context getInitialContext() throws NamingException { // Get InitialContext for Embedded OC4J 
        // The embedded server must be running for lookups to IsIuIcIcIeIeIdI.I 
        return new InitialContext();
    }

    private void getAll() {
        try {
            for (IBasicDTO dto: client.getAll()) {
                IMappedDataDTO mappedDataDTO = (IMappedDataDTO)dto;
                System.out.println(mappedDataDTO.getObjtype1Code().getCode().getKey() + 
                                   " - " + 
                                   mappedDataDTO.getSoc1Code().getCode().getKey() + 
                                   " - " + mappedDataDTO.getSoc1Value() + 
                                   " - " + 
                                   mappedDataDTO.getObjtype2Code().getCode().getKey() + 
                                   " - " + 
                                   mappedDataDTO.getSoc2Code().getCode().getKey() + 
                                   " - " + mappedDataDTO.getSoc2Value() + 
                                   " - " + mappedDataDTO.getMapStatus() + 
                                   " - " + mappedDataDTO.getCreatedBy() + 
                                   " - " + mappedDataDTO.getCreatedDate() + 
                                   " - " + mappedDataDTO.getLastUpdatedBy() + 
                                   " - " + mappedDataDTO.getLastUpdatedDate());
            }
        } catch (DataBaseException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (SharedApplicationException e) { // TODO 
        }
        System.out.println("--------------------------------------------------getAll");
    } /*private static void getById ( IMappedDataClient iMappedDataClient ) {
 try { iMappedDataClient.getById ( new Long ( 1 ) ) ;
 } catch ( ItemNotFoundException e ) { System.out.println ( "-------------------1>" + e.getMessage ( ) + "<" ) ;
 } catch ( DataBaseException e ) { System.out.println ( "-------------------2>" + e.getMessage ( ) + "<" ) ;
 } System.out.println ( "--------------------------------------------------getById" ) ;
 } */

    private void getMappedDataList() {
        IMappedDataDTO mappedDataDTO = MapDTOFactory.createMappedDataDTO();
        IObjectTypesDTO objectTypesDTO1 = MapDTOFactory.createObjectTypesDTO();
        objectTypesDTO1.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
        // IObjectTypesDTO objectTypesDTO2 = MapDTOFactory.createObjectTypesDTO ( ) ; 
        // objectTypesDTO2.setObjtypeCode ( new Long ( 1 ) ) ; 
        ISocietiesDTO societiesDTO1 = MapDTOFactory.createSocietiesDTO();
        societiesDTO1.setCode(MapEntityKeyFactory.createSocietiesEntityKey(1L));
        ISocietiesDTO societiesDTO2 = MapDTOFactory.createSocietiesDTO();
        societiesDTO2.setCode(MapEntityKeyFactory.createSocietiesEntityKey(2L));
        mappedDataDTO.setObjtype1Code(objectTypesDTO1);
        // mappedDataDTO.setObjtype2Code ( objectTypesDTO2 ) ; 
        mappedDataDTO.setSoc1Code(societiesDTO1);
        mappedDataDTO.setSoc2Code(societiesDTO2);
        mappedDataDTO.setSoc1Value("%");
        try {
            for (IBasicDTO mappedDataDTOList: 
                 client.getMappedDataList(mappedDataDTO)) {
                System.out.println(((IMappedDataDTO)mappedDataDTOList).getSoc2Value());
            }
        } catch (ItemNotFoundException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (DataBaseException e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        } catch (SharedApplicationException e) {
        }
        System.out.println("--------------------------------------------------getMappedDataList");
    }

    private void createMappedData() {
        IMappedDataDTO mappedDataDTO = MapDTOFactory.createMappedDataDTO();
        IObjectTypesDTO objectTypesDTO1 = MapDTOFactory.createObjectTypesDTO();
        objectTypesDTO1.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(4L));
        ISocietiesDTO societiesDTO1 = MapDTOFactory.createSocietiesDTO();
        societiesDTO1.setCode(MapEntityKeyFactory.createSocietiesEntityKey(4L));
        IObjectTypesDTO objectTypesDTO2 = MapDTOFactory.createObjectTypesDTO();
        objectTypesDTO2.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(5L));
        ISocietiesDTO societiesDTO2 = MapDTOFactory.createSocietiesDTO();
        societiesDTO2.setCode(MapEntityKeyFactory.createSocietiesEntityKey(5L));
        mappedDataDTO.setObjtype1Code(objectTypesDTO1);
        mappedDataDTO.setSoc1Code(societiesDTO1);
        mappedDataDTO.setSoc1Value("pop4");
        mappedDataDTO.setObjtype2Code(objectTypesDTO2);
        mappedDataDTO.setSoc2Code(societiesDTO2);
        mappedDataDTO.setSoc2Value("pop5");
        mappedDataDTO.setMapStatus(new Long(1));
        // try { 
        // IMappedDataDTO mappedDataDTOCreated = 
        // iMappedDataClient.createMappedData ( mappedDataDTO ) ; 
        // System.out.println ( mappedDataDTOCreated.getObjtype1Code ( ) .getObjtypeCode ( ) + 
        // " - " + 
        // mappedDataDTOCreated.getSoc1Code ( ) .getSocCode ( ) + 
        // " - " + mappedDataDTOCreated.getSoc1Value ( ) + 
        // " - " + 
        // mappedDataDTOCreated.getObjtype2Code ( ) .getObjtypeCode ( ) + 
        // " - " + 
        // mappedDataDTOCreated.getSoc2Code ( ) .getSocCode ( ) + 
        // " - " + mappedDataDTOCreated.getSoc2Value ( ) + 
        // " - " + mappedDataDTOCreated.getMapStatus ( ) + 
        // " - " + mappedDataDTOCreated.getCreatedBy ( ) + 
        // " - " + mappedDataDTOCreated.getCreatedDate ( ) + 
        // " - " + 
        // mappedDataDTOCreated.getLastUpdatedBy ( ) + 
        // " - " + 
        // mappedDataDTOCreated.getLastUpdatedDate ( ) ) ; 
        // } catch ( DataBaseException e ) { 
        // System.out.println ( "-------------------1>" + e.getMessage ( ) + "<" ) ; 
        // } 
        System.out.println("--------------------------------------------------createMappedData");
    }

    private void createMappedDataList() {
        List<IBasicDTO> mappedDataDTOList = new ArrayList<IBasicDTO>();
        IMappedDataDTO mappedDataDTO;
        {
            mappedDataDTO = MapDTOFactory.createMappedDataDTO();
            IObjectTypesDTO objectTypesDTO1 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO1.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
            ISocietiesDTO societiesDTO1 = MapDTOFactory.createSocietiesDTO();
            societiesDTO1.setCode(MapEntityKeyFactory.createSocietiesEntityKey(1L));
            IObjectTypesDTO objectTypesDTO2 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO2.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
            ISocietiesDTO societiesDTO2 = MapDTOFactory.createSocietiesDTO();
            societiesDTO2.setCode(MapEntityKeyFactory.createSocietiesEntityKey(2L));
            mappedDataDTO.setObjtype1Code(objectTypesDTO1);
            mappedDataDTO.setSoc1Code(societiesDTO1);
            mappedDataDTO.setSoc1Value("NEW VALUEEEEEE_19_4");
            mappedDataDTO.setObjtype2Code(objectTypesDTO2);
            mappedDataDTO.setSoc2Code(societiesDTO2);
            mappedDataDTO.setSoc2Value("NEW VALUEEEEEE_19_4");
            mappedDataDTO.setMapStatus(new Long(1));
            mappedDataDTOList.add(mappedDataDTO);
        }
        { /*mappedDataDTO = MapDTOFactory.createMappedDataDTO ( ) ;
 IObjectTypesDTO objectTypesDTO1 = MapDTOFactory.createObjectTypesDTO ( ) ;
 objectTypesDTO1.setCode ( MapEntityKeyFactory.createObjectTypesEntityKey ( 3L ) ) ;
 ISocietiesDTO societiesDTO1 = MapDTOFactory.createSocietiesDTO ( ) ;
 societiesDTO1.setCode ( MapEntityKeyFactory.createSocietiesEntityKey ( 3L ) ) ;
 IObjectTypesDTO objectTypesDTO2 = MapDTOFactory.createObjectTypesDTO ( ) ;
 objectTypesDTO2.setCode ( MapEntityKeyFactory.createObjectTypesEntityKey ( 2L ) ) ;
 ISocietiesDTO societiesDTO2 = MapDTOFactory.createSocietiesDTO ( ) ;
 societiesDTO2.setCode ( MapEntityKeyFactory.createSocietiesEntityKey ( 3L ) ) ;
 mappedDataDTO.setObjtype1Code ( objectTypesDTO1 ) ;
 mappedDataDTO.setSoc1Code ( societiesDTO1 ) ;
 mappedDataDTO.setSoc1Value ( "pop6" ) ;
 mappedDataDTO.setObjtype2Code ( objectTypesDTO2 ) ;
 mappedDataDTO.setSoc2Code ( societiesDTO2 ) ;
 mappedDataDTO.setSoc2Value ( "pop7" ) ;
 mappedDataDTO.setMapStatus ( new Long ( 1 ) ) ;
 mappedDataDTOList.add ( mappedDataDTO ) ; */
        }
        try {
            System.out.println("mappedDataDTOList.size-------------------------" + 
                               mappedDataDTOList.size());
            client.createMappedDataList(mappedDataDTOList);
        } catch (DataBaseException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (SharedApplicationException e) { // TODO 
        }
        System.out.println("--------------------------------------------------createMappedDataList");
    }

    private void removeMappedData() {
        List<IBasicDTO> mappedDataDTOList = new ArrayList<IBasicDTO>();
        {
            IMappedDataDTO mappedDataDTO = MapDTOFactory.createMappedDataDTO();
            IObjectTypesDTO objectTypesDTO1 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO1.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
            ISocietiesDTO societiesDTO1 = MapDTOFactory.createSocietiesDTO();
            societiesDTO1.setCode(MapEntityKeyFactory.createSocietiesEntityKey(1L));
            IObjectTypesDTO objectTypesDTO2 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO2.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
            ISocietiesDTO societiesDTO2 = MapDTOFactory.createSocietiesDTO();
            societiesDTO2.setCode(MapEntityKeyFactory.createSocietiesEntityKey(2L));
            mappedDataDTO.setObjtype1Code(objectTypesDTO1);
            mappedDataDTO.setSoc1Code(societiesDTO1);
            mappedDataDTO.setSoc1Value("NEW VALUEEEEEE777");
            mappedDataDTO.setObjtype2Code(objectTypesDTO2);
            mappedDataDTO.setSoc2Code(societiesDTO2);
            mappedDataDTO.setSoc2Value("NEW VALUEEEEEE888");
            mappedDataDTOList.add(mappedDataDTO);
        }
        {
            IMappedDataDTO mappedDataDTO = MapDTOFactory.createMappedDataDTO();
            IObjectTypesDTO objectTypesDTO1 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO1.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
            ISocietiesDTO societiesDTO1 = MapDTOFactory.createSocietiesDTO();
            societiesDTO1.setCode(MapEntityKeyFactory.createSocietiesEntityKey(1L));
            IObjectTypesDTO objectTypesDTO2 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO2.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
            ISocietiesDTO societiesDTO2 = MapDTOFactory.createSocietiesDTO();
            societiesDTO2.setCode(MapEntityKeyFactory.createSocietiesEntityKey(2L));
            mappedDataDTO.setObjtype1Code(objectTypesDTO1);
            mappedDataDTO.setSoc1Code(societiesDTO1);
            mappedDataDTO.setSoc1Value("NEW VALUEEEEEE5");
            mappedDataDTO.setObjtype2Code(objectTypesDTO2);
            mappedDataDTO.setSoc2Code(societiesDTO2);
            mappedDataDTO.setSoc2Value("NEW VALUEEEEEE6");
            mappedDataDTOList.add(mappedDataDTO);
        }
        try {
            client.remove((mappedDataDTOList));
            System.out.println("removeMappedData = ssda");
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (DataBaseException e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------removeMappedData");
    }

    private void updateMappedData() {
        IMappedDataDTO mappedDataDTO;
        {
            mappedDataDTO = MapDTOFactory.createMappedDataDTO();
            IObjectTypesDTO objectTypesDTO1 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO1.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
            ISocietiesDTO societiesDTO1 = MapDTOFactory.createSocietiesDTO();
            societiesDTO1.setCode(MapEntityKeyFactory.createSocietiesEntityKey(1L));
            IObjectTypesDTO objectTypesDTO2 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO2.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(1L));
            ISocietiesDTO societiesDTO2 = MapDTOFactory.createSocietiesDTO();
            societiesDTO2.setCode(MapEntityKeyFactory.createSocietiesEntityKey(2L));
            mappedDataDTO.setObjtype1Code(objectTypesDTO1);
            mappedDataDTO.setSoc1Code(societiesDTO1);
            mappedDataDTO.setSoc1Value("popssssss4");
            mappedDataDTO.setObjtype2Code(objectTypesDTO2);
            mappedDataDTO.setSoc2Code(societiesDTO2);
            mappedDataDTO.setSoc2Value("popsssss5");
            // mappedDataDTO.setMapStatus ( new Long ( 0 ) ) ; 
        } // try { 
        // iMappedDataClient.updateMappedData ( mappedDataDTO ) ; 
        // } catch ( ItemNotFoundException e ) { 
        // System.out.println ( "-------------------1>" + e.getMessage ( ) + "<" ) ; 
        // } catch ( DataBaseException e ) { 
        // System.out.println ( "-------------------2>" + e.getMessage ( ) + "<" ) ; 
        // } 
        System.out.println("--------------------------------------------------updateMappedData");
    }

    private void updateMappedDataList() {
        List<IBasicDTO> mappedDataDTOList = new ArrayList<IBasicDTO>();
        IMappedDataDTO mappedDataDTO;
        {
            mappedDataDTO = MapDTOFactory.createMappedDataDTO();
            IObjectTypesDTO objectTypesDTO1 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO1.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(4L));
            ISocietiesDTO societiesDTO1 = MapDTOFactory.createSocietiesDTO();
            societiesDTO1.setCode(MapEntityKeyFactory.createSocietiesEntityKey(4L));
            IObjectTypesDTO objectTypesDTO2 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO2.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(5L));
            ISocietiesDTO societiesDTO2 = MapDTOFactory.createSocietiesDTO();
            societiesDTO2.setCode(MapEntityKeyFactory.createSocietiesEntityKey(5L));
            mappedDataDTO.setObjtype1Code(objectTypesDTO1);
            mappedDataDTO.setSoc1Code(societiesDTO1);
            mappedDataDTO.setSoc1Value("pop4");
            mappedDataDTO.setObjtype2Code(objectTypesDTO2);
            mappedDataDTO.setSoc2Code(societiesDTO2);
            mappedDataDTO.setSoc2Value("pop5");
            mappedDataDTO.setMapStatus(new Long(1));
            // mappedDataDTOList.add ( mappedDataDTO ) ; 
        }
        {
            mappedDataDTO = MapDTOFactory.createMappedDataDTO();
            IObjectTypesDTO objectTypesDTO1 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO1.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(6L));
            ISocietiesDTO societiesDTO1 = MapDTOFactory.createSocietiesDTO();
            societiesDTO1.setCode(MapEntityKeyFactory.createSocietiesEntityKey(6L));
            IObjectTypesDTO objectTypesDTO2 = 
                MapDTOFactory.createObjectTypesDTO();
            objectTypesDTO2.setCode(MapEntityKeyFactory.createObjectTypesEntityKey(7L));
            ISocietiesDTO societiesDTO2 = MapDTOFactory.createSocietiesDTO();
            societiesDTO2.setCode(MapEntityKeyFactory.createSocietiesEntityKey(7L));
            mappedDataDTO.setObjtype1Code(objectTypesDTO1);
            mappedDataDTO.setSoc1Code(societiesDTO1);
            mappedDataDTO.setSoc1Value("pop6");
            mappedDataDTO.setObjtype2Code(objectTypesDTO2);
            mappedDataDTO.setSoc2Code(societiesDTO2);
            mappedDataDTO.setSoc2Value("pop7");
            mappedDataDTO.setMapStatus(new Long(1));
            // mappedDataDTOList.add ( mappedDataDTO ) ; 
        }
        try {
            client.updateMappedDataList(mappedDataDTOList);
        } catch (ItemNotFoundException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (DataBaseException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (SharedApplicationException e) { // TODO 
        }
        System.out.println("--------------------------------------------------updateMappedDataList");
    }
}
