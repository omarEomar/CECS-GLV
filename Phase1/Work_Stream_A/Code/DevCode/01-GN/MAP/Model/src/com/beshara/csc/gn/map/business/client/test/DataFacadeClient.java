package com.beshara.csc.gn.map.business.client.test;

import com.beshara.base.entity.*;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.map.business.client.IDataClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IDataDTO;
import com.beshara.csc.gn.map.business.dto.IMappedValueDTO;
import com.beshara.csc.gn.map.business.entity.IDataEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DataFacadeClient {
    IDataClient iDataClient = MapClientFactory.getDataClient();

    public static void main(String[] args) {
        try {
            DataFacadeClient test = new DataFacadeClient();
            final Context context = getInitialContext();
            //DataFacade dataFacade = ( DataFacade ) context.lookup ( "DataFacade" ) ; 
            // Call any of the Remote methods below to access the EJB 
            // dataFacade.mergeEntity ( entity ) ; 
            // dataFacade.persistEntity ( entity ) ; 
            //System.out.println ( dataFacade.getAll ( ) ) ; 
            // dataFacade.createData ( dataDTO ) ; 
            // dataFacade.updateData ( dataDTO ) ; 
            // dataFacade.removeData ( dataDTO ) ; 
            // dataFacade.getById ( id ) ; 
            // dataFacade.ListByTypeAndSoc ( objtypeCode , socCode ) ; 
            //IDataClient iDataClient = MapClientFactory.getDataClient ( ) ; 
            //listByObjectTypeAndSoc ( iDataClient ) ; 
            //for ( IBasicDTO dto: iDataClient.getAll ( 1L , 1L , 1L ) ) { 
            //System.out.println ( ( ( IMappedValueDTO ) dto ) .getStrCode ( ) ) ; 
            //System.out.println ( dto.getName ( ) ) ; 
            // } 
            // listMappedToByObjectTypeAndSoc2 ( iDataClient ) ; 
            //test.getAll ( ) ; 
            //test.listByObjectTypeAndSoc ( ) ; 
            test.listMappedToByObjectTypeAndSoc2();
            // getById ( iDataClient ) ; 
            // createData ( iDataClient ) ; 
            // removeData ( iDataClient ) ; 
            // updateData ( iDataClient ) ; 
            // listByObjectTypeAndSoc ( iDataClient ) ; 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Context getInitialContext() throws NamingException { // Get InitialContext for Embedded OC4J 
        // The embedded server must be running for lookups to IsIuIcIcIeIeIdI.I 
        return new InitialContext();
    }

    private void getAll() {
        try {
            for (IBasicDTO data: iDataClient.getAll()) {
                IDataDTO dataDTO = (IDataDTO)data;
                System.out.println(dataDTO.getSocCode() + " - " + 
                                   dataDTO.getObjtypeCode() + '\t' + 
                                   dataDTO.getSqlStatement() + '\t' + 
                                   dataDTO.getCreatedBy() + '\t' + 
                                   dataDTO.getCreatedDate() + '\t' + 
                                   dataDTO.getLastUpdatedBy() + '\t' + 
                                   dataDTO.getLastUpdatedDate());
            }
            System.out.println("--------------------------------------------------getAll");
        } catch (DataBaseException e) {
            System.out.println("-------------------->" + e.getMessage() + "<");
        } catch (SharedApplicationException e) { // TODO 
        }
    }

    private static void getById(IDataClient iDataClient) {
        IDataDTO dataDTO;
        try {
            dataDTO = 
                    (IDataDTO)iDataClient.getById(MapEntityKeyFactory.createDataEntityKey(1L, 
                                                                                          1L));
        } catch (DataBaseException e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------getById");
    }

    private static void createData(IDataClient iDataClient) {
        IDataDTO dataDTO = MapDTOFactory.createDataDTO();
        dataDTO.setObjtypeCode(new Long(3));
        dataDTO.setSocCode(new Long(1));
        dataDTO.setSqlStatement("select * from *");
        //dataDTO.setCreatedBy ( "CreatedBy" ) ; 
        dataDTO.setCreatedDate(SharedUtils.getCurrentTimestamp());
        // dataDTO.setLastUpdatedBy ( "LastUpdatedBy" ) ; 
        dataDTO.setLastUpdatedDate(SharedUtils.getCurrentTimestamp());
        try {
            IDataDTO dataDTOCreated = (IDataDTO)iDataClient.add(dataDTO);
            System.out.println(dataDTOCreated.getSocCode() + " - " + 
                               dataDTOCreated.getObjtypeCode() + '\t' + 
                               dataDTOCreated.getSqlStatement() + '\t' + 
                               dataDTOCreated.getCreatedBy() + '\t' + 
                               dataDTOCreated.getCreatedDate() + '\t' + 
                               dataDTOCreated.getLastUpdatedBy() + '\t' + 
                               dataDTOCreated.getLastUpdatedDate());
        } catch (DataBaseException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (Exception e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------createData");
    }

    private static void removeData(IDataClient iDataClient) {
        IDataDTO dataDTO = MapDTOFactory.createDataDTO();
        dataDTO.setObjtypeCode(new Long(3));
        dataDTO.setSocCode(new Long(2));
        try {
            Boolean b = iDataClient.remove(dataDTO);
            System.out.println("removeData = " + b);
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (DataBaseException e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------removeData");
    }

    private static void updateData(IDataClient iDataClient) {
        IDataDTO dataDTO = MapDTOFactory.createDataDTO();
        dataDTO.setObjtypeCode(new Long(3));
        dataDTO.setSocCode(new Long(1));
        // dataDTO.setSqlStatement ( "select" ) ; 
        try {
            iDataClient.remove(dataDTO);
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (DataBaseException e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------updateData");
    }

    public void listByObjectTypeAndSoc() {
        try {
            List<IBasicDTO> list = 
                iDataClient.ListByTypeAndSoc1AndSoc2(3L, 4L, 1L);
            for (IBasicDTO mappedValueDTO: list) {
                System.out.println(((IMappedValueDTO)mappedValueDTO).getStrCode() + 
                                   " - " + 
                                   ((IMappedValueDTO)mappedValueDTO).getName());
            }
        } catch (DataBaseException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (SharedApplicationException e) {
        }
        System.out.println("--------------------------------------------------listByObjectTypeAndSoc");
    }

    private void listMappedToByObjectTypeAndSoc2() {
        try {
            System.out.println(iDataClient.ListByTypeAndSoc2CodeFiltered(1L, 
                                                                         1L, 
                                                                         2L, 
                                                                         "1", 
                                                                         "%%"));
            // for ( IBasicDTO mappedValueDTO: 
            // //iDataClient.ListMappedT0ByTypeAndSoc2Code ( new Long ( 1 ) , new Long ( 2 ) , new Long ( 1 ) , new String ( "5" ) ) ) { 
            // //iDataClient.listByTypeAndSoc1FilteredByCode ( 1L , 1L , 1L , "1" ) 
            // //iDataClient.listByTypeAndSoc1FilteredByName ( 1L , 1L , 1L , "%" ) 
            // //iDataClient.ListByTypeAndSoc2Code ( 1L , 1L , 1L , "1" ) 
            // //iDataClient.ListByTypeAndSoc2CodeFiltered ( 1L , 1L , 1L , "1" , "%" ) 
            // //iDataClient.ListByTypeAndSocFiltered ( 1L , 1L , "1" ) 
            // //iDataClient.ListByTypeAndSocFilteredCode ( 1L , 1L , 1L ) 
            // iDataClient.ListMappedT0ByTypeAndSoc2Code ( 1L , 1L , 1L , "1" ) 
            // 
            // ) { 
            // System.out.println ( ( ( IMappedValueDTO ) mappedValueDTO ) .getStrCode ( ) + " - " + 
            // mappedValueDTO.getName ( ) ) ; 
            // } 
        } catch (DataBaseException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (SharedApplicationException e) {
        }
        System.out.println("--------------------------------------------------listMappedToByObjectTypeAndSoc2");
    }
}
