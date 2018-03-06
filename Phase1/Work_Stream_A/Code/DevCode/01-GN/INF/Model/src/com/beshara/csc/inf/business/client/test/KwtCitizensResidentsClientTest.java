package com.beshara.csc.inf.business.client.test;

import com.beshara.base.entity.*;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.client.IKwtCitizensResidentsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IResponseDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsSearchDTO;
import com.beshara.csc.inf.business.dto.IRequestDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public class KwtCitizensResidentsClientTest {
    IKwtCitizensResidentsClient kwtCitizensResidentsClient;

    public KwtCitizensResidentsClientTest() {
        kwtCitizensResidentsClient = 
                InfClientFactory.getKwtCitizensResidentsClient();
    }

    /** 
     * @param args 
     */
    public static void main(String[] args) {
        KwtCitizensResidentsClientTest client = 
            new KwtCitizensResidentsClientTest();
        //client.search ( ) ; 
        client.searchCitezens();
    }

    private void search() {
        IKwtCitizensResidentsSearchDTO searchDTO = 
            InfDTOFactory.createKwtCitizensResidentsSearchDTO();
        searchDTO.setCivilId(249010400028L);
        // searchDTO.setFirstName ( "محمد" ) ; 
        // searchDTO.setSecondName ( null ) ; 
        // searchDTO.setThirdName ( null ) ; sd 
        // searchDTO.setLastName ( null ) ; 
        // searchDTO.setFamilyName ( null ) ; 
        try {
            List<IKwtCitizensResidentsDTO> list = 
                //(List<IKwtCitizensResidentsDTO>)kwtCitizensResidentsClient.search(searchDTO);
                (List)kwtCitizensResidentsClient.search(searchDTO);
            for (IKwtCitizensResidentsDTO dto: list) {
                System.out.println("Name = " + dto.getFullName());
                System.out.println("civilID = " + 
                                   ((IKwtCitizensResidentsEntityKey)dto.getCode()).getCivilId().longValue());
            }
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    private void searchCitezens() { // List<IKwtCitizensResidentsDTO> list=null ; 
        // try { 
        // IKwtCitizensResidentsSearchDTO dto=new IKwtCitizensResidentsSearchDTO ( ) ; 
        // dto.setCivilId ( 249010400028L ) ; 
        // IRequestDTO request=new IRequestDTO ( ) ; 
        // request.setPageNum ( new Long ( "1" ) ) ; 
        // request.setMaxNoOfRecords ( new Long ( 8 ) ) ; 
        // request.setCountRequired ( true ) ; 
        // dto.setRequestDTO ( request ) ; 
        // 
        // IResponseDTO res = kwtCitizensResidentsClient.searchCitizens ( dto ) ; 
        // list= ( List<IKwtCitizensResidentsDTO> ) res.getBasicDTOList ( ) ; 
        // for ( IKwtCitizensResidentsDTO kdto: list ) { 
        // System.out.println ( "Name = "+kdto.getFullName ( ) ) ; 
        // System.out.println ( "civilID = "+ ( ( IKwtCitizensResidentsEntityKey ) kdto.getCode ( ) ) .getCivilId ( ) .longValue ( ) ) ; 
        // } 
        // } catch ( SharedApplicationException e ) { 
        // e.printStackTrace ( ) ; 
        // } catch ( DataBaseException e ) { 
        // e.printStackTrace ( ) ; 
        // } 
        try {
            IKwtCitizensResidentsDTO dto = 
                (IKwtCitizensResidentsDTO)kwtCitizensResidentsClient.getById(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(274080301038L));
            dto.setCode(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(121212121212L));
            dto.setFirstName("TEST ADD");
            dto = 
(IKwtCitizensResidentsDTO)kwtCitizensResidentsClient.add(dto);
            System.out.println(dto.getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
