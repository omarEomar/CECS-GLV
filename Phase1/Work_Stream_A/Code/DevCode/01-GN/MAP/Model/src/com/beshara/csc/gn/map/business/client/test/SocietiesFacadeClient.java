package com.beshara.csc.gn.map.business.client.test;

import com.beshara.base.entity.*;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.map.business.client.ISocietiesClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.gn.map.business.entity.ISocietiesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SocietiesFacadeClient {
    ISocietiesClient socClient = MapClientFactory.getSocietiesClient();

    public static void main(String[] args) {
        try {
            SocietiesFacadeClient client = new SocietiesFacadeClient();
            // Call any of the Remote methods below to access the EJB 
            // societiesFacade.mergeEntity ( entity ) ; 
            // societiesFacade.persistEntity ( entity ) ; 
            //client.getAll ( ) ; 
            //client.createSocieties ( ) ; 
            //client.updateSocieties ( ) ; 
            //client.removeSocieties ( ) ; 
            client.listByObjectType();
            //client.searchByName ( ) ; 
            //client.searchByCode ( ) ; 
            // societiesFacade.getById ( id ) ; 
            // societiesFacade.ListByObjectType ( objtypeCode ) ; 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ISocietiesClient iSocietiesClient = 
            MapClientFactory.getSocietiesClient();
        //getAll ( ) ; 
        //getById ( iSocietiesClient ) ; 
        // removeSociefties ( iSocietiesClient ) ; 
        // updateSocieties ( iSocietiesClient ) ; 
        // listByObjectType ( iSocietiesClient ) ; 
    }

    private static Context getInitialContext() throws NamingException { // Get InitialContext for Embedded OC4J 
        // The embedded server must be running for lookups to IsIuIcIcIeIeIdI.I 
        return new InitialContext();
    }

    private void getAll() {
        try {
            for (IBasicDTO society: socClient.getAll()) {
                ISocietiesDTO societiesDTO = (ISocietiesDTO)society;
                System.out.println(societiesDTO.getCode().getKey() + " - " + 
                                   societiesDTO.getName() + '\t' + 
                                   societiesDTO.getCreatedBy() + '\t' + 
                                   societiesDTO.getCreatedDate() + '\t' + 
                                   societiesDTO.getLastUpdatedBy() + '\t' + 
                                   societiesDTO.getLastUpdatedDate());
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (SharedApplicationException e) { // TODO 
        }
        System.out.println("--------------------------------------------------getById");
    }

    private static void getById(ISocietiesClient iSocietiesClient) {
        ISocietiesDTO societiesDTO;
        try {
            societiesDTO = 
                    (ISocietiesDTO)iSocietiesClient.getById(MapEntityKeyFactory.createSocietiesEntityKey(6666L));
            System.out.println(societiesDTO.getCode().getKey() + " - " + 
                               societiesDTO.getName() + '\t' + 
                               societiesDTO.getCreatedBy() + '\t' + 
                               societiesDTO.getCreatedDate() + '\t' + 
                               societiesDTO.getLastUpdatedBy() + '\t' + 
                               societiesDTO.getLastUpdatedDate());
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (DataBaseException e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------getById");
    }

    private void createSocieties() {
        ISocietiesDTO societiesDTO = MapDTOFactory.createSocietiesDTO();
        societiesDTO.setName("Amir society");
        // societiesDTO.setCreatedBy ( "Amir Nasr" ) ; 
        societiesDTO.setCreatedDate(SharedUtils.getCurrentTimestamp());
        // societiesDTO.setLastUpdatedBy ( "Amir Nasr" ) ; 
        societiesDTO.setLastUpdatedDate(SharedUtils.getCurrentTimestamp());
        try {
            ISocietiesDTO societiesDTOCreated = 
                (ISocietiesDTO)socClient.add(societiesDTO);
            System.out.println(societiesDTOCreated.getCode().getKey() + " - " + 
                               societiesDTOCreated.getName() + '\t' + 
                               societiesDTOCreated.getCreatedBy() + '\t' + 
                               societiesDTOCreated.getCreatedDate() + '\t' + 
                               societiesDTOCreated.getLastUpdatedBy() + '\t' + 
                               societiesDTOCreated.getLastUpdatedDate());
        } catch (DataBaseException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------createSocieties");
    }

    void searchByName() {
        try {
            List<IBasicDTO> searchList = socClient.searchByName("%");
            for (IBasicDTO societies: searchList) {
                System.out.println(societies.getName());
            }
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void searchByCode() {
        try {
            List<IBasicDTO> searchList = socClient.searchByCode(24L);
            for (IBasicDTO societies: searchList) {
                System.out.println(societies.getName());
            }
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    private void removeSocieties() {
        ISocietiesDTO societiesDTO = MapDTOFactory.createSocietiesDTO();
        societiesDTO.setCode(MapEntityKeyFactory.createSocietiesEntityKey(24L));
        try {
            socClient.remove(societiesDTO);
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (DataBaseException e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------createSocieties");
    }

    private void updateSocieties() {
        ISocietiesDTO societiesDTO;
        try {
            societiesDTO = 
                    (ISocietiesDTO)socClient.getById(MapEntityKeyFactory.createSocietiesEntityKey(24L));
            System.out.println(societiesDTO.getCode().getKey() + " - " + 
                               societiesDTO.getName() + '\t' + 
                               societiesDTO.getCreatedBy() + '\t' + 
                               societiesDTO.getCreatedDate() + '\t' + 
                               societiesDTO.getLastUpdatedBy() + '\t' + 
                               societiesDTO.getLastUpdatedDate());
            //societiesDTO.setCode ( MapEntityKeyFactory.createSocietiesEntityKey ( 11L ) ) ; 
            societiesDTO.setName("Amir Amir Amir");
            socClient.update(societiesDTO);
        } catch (SharedApplicationException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (DataBaseException e) {
            System.out.println("-------------------2>" + e.getMessage() + "<");
        }
        System.out.println("--------------------------------------------------updateSocieties");
    }

    private void listByObjectType() {
        try {
            for (IBasicDTO society: socClient.listByObjectType(new Long(1))) {
                ISocietiesDTO societiesDTO = (ISocietiesDTO)society;
                System.out.println(societiesDTO.getCode().getKey() + " - " + 
                                   societiesDTO.getName() + '\t' + 
                                   societiesDTO.getCreatedBy() + '\t' + 
                                   societiesDTO.getCreatedDate() + '\t' + 
                                   societiesDTO.getLastUpdatedBy() + '\t' + 
                                   societiesDTO.getLastUpdatedDate());
            }
        } catch (DataBaseException e) {
            System.out.println("-------------------1>" + e.getMessage() + "<");
        } catch (SharedApplicationException e) { // TODO 
        }
        System.out.println("--------------------------------------------------listByObjectType");
    }
}
