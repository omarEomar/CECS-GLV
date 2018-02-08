package com.beshara.csc.gn.map.business.client.test;

import com.beshara.base.entity.*;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.map.business.client.IObjectTypesClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IObjectTypesDTO;
import com.beshara.csc.gn.map.business.entity.IObjectTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

public class ObjectTypesFacadeClient {
    IObjectTypesClient client;

    public ObjectTypesFacadeClient() {
        client = MapClientFactory.getObjectTypesClient();
    }

    public static void main(String[] args) {
        try {
            ObjectTypesFacadeClient c = new ObjectTypesFacadeClient();
            //c.getAll ( ) ; 
            // Call any of the Remote methods below to access the EJB 
            // objectTypesFacade.mergeEntity ( entity ) ; 
            // objectTypesFacade.persistEntity ( entity ) ; 
            //System.out.println ( objectTypesFacade.getAll ( ) ) ; 
            // objectTypesFacade.createObjectTypes ( objectTypesDTO ) ; 
            // objectTypesFacade.updateObjectTypes ( objectTypesDTO ) ; 
            // objectTypesFacade.removeObjectTypes ( objectTypesDTO ) ; 
            // objectTypesFacade.getById ( id ) ; 
            //c.getAll ( ) ; 
            //c.createObjectTypes ( ) ; 
            //c.updateObjectTypes ( ) ; 
            //c.removeObjectTypes ( ) ; 
            // for ( IBasicDTO dto: c.client.getAll ( ) ) { 
            // System.out.println ( dto.getName ( ) ) ; 
            // } 
        } catch (Exception ex) {
            ex.printStackTrace();
        } // createObjectTypes ( iObjectTypesClient ) ; 
        // getById ( iObjectTypesClient ) ; 
        // removeObjectTypes ( iObjectTypesClient ) ; 
    }

    private void getAll() {
        try {
            for (IBasicDTO object: client.getAll()) {
                System.out.println("Object Name:" + object.getName() + 
                                   "_______________Object Code" + 
                                   object.getCode().getKey());
            }
        } catch (DataBaseException e) {
            System.out.println("-------------------->" + e.getMessage() + "<");
        } catch (SharedApplicationException e) { // TODO 
        }
        System.out.println("--------------------------------------------------getAll");
    }

    private void createObjectTypes() {
        IObjectTypesDTO objectTypesDTO = MapDTOFactory.createObjectTypesDTO();
        objectTypesDTO.setName("Amir Amir");
        //objectTypesDTO.setCode ( MapEntityKeyFactory.createObjectTypesEntityKey ( 222L ) ) ; 
        // objectTypesDTO.setCreatedBy ( "createdBy" ) ; 
        objectTypesDTO.setCreatedDate(SharedUtils.getCurrentTimestamp());
        // objectTypesDTO.setLastUpdatedBy ( "lastUpdateBy" ) ; 
        objectTypesDTO.setLastUpdatedDate(SharedUtils.getCurrentTimestamp());
        try {
            IObjectTypesDTO objectTypesDTOCreated = 
                (IObjectTypesDTO)client.add(objectTypesDTO);
            System.out.println(objectTypesDTOCreated.getCode().getKey() + 
                               " - " + objectTypesDTOCreated.getName() + 
                               " - " + objectTypesDTOCreated.getCreatedBy() + 
                               " - " + objectTypesDTOCreated.getCreatedDate() + 
                               " - " + 
                               objectTypesDTOCreated.getLastUpdatedBy() + 
                               " - " + 
                               objectTypesDTOCreated.getLastUpdatedDate());
        } catch (DataBaseException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------createObjectTypes");
    }

    private static void getById(IObjectTypesClient iObjectTypesClient) {
        try {
            IObjectTypesDTO objectTypesDTO = 
                (IObjectTypesDTO)iObjectTypesClient.getById(MapEntityKeyFactory.createObjectTypesEntityKey(91L));
            System.out.println(objectTypesDTO.getCode().getKey() + " - " + 
                               objectTypesDTO.getName() + " - " + 
                               objectTypesDTO.getCreatedBy() + " - " + 
                               objectTypesDTO.getCreatedDate() + " - " + 
                               objectTypesDTO.getLastUpdatedBy() + " - " + 
                               objectTypesDTO.getLastUpdatedDate());
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (DataBaseException e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------getById");
    }

    private void removeObjectTypes() {
        IObjectTypesDTO objectTypesDTO;
        try {
            objectTypesDTO = 
                    (IObjectTypesDTO)client.getById(MapEntityKeyFactory.createObjectTypesEntityKey(17L));
            Boolean b = client.remove(objectTypesDTO);
            System.out.println("removeObjectTypes = " + b);
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (DataBaseException e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------removeObjectTypes");
    }

    private void updateObjectTypes() {
        IObjectTypesDTO objectTypesDTO;
        try {
            objectTypesDTO = 
                    (IObjectTypesDTO)client.getById(MapEntityKeyFactory.createObjectTypesEntityKey(17L));
            objectTypesDTO.setName("UPDATEDDDDDDDDD");
            Boolean b = client.update(objectTypesDTO);
            // objectTypesDTO.setCreatedBy ( "ppppppr" ) ; 
            System.out.println("updateObjectTypes = " + b);
            System.out.println(objectTypesDTO.getCode().getKey() + " - " + 
                               objectTypesDTO.getName() + " - " + 
                               objectTypesDTO.getCreatedBy() + " - " + 
                               objectTypesDTO.getCreatedDate() + " - " + 
                               objectTypesDTO.getLastUpdatedBy() + " - " + 
                               objectTypesDTO.getLastUpdatedDate());
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (DataBaseException e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------updateObjectTypes");
    }
}
