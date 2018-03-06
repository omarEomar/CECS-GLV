package com.beshara.csc.inf.business.client.test;

import com.beshara.base.entity.*;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.csc.inf.business.client.ISpecialCaseTypesClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.ISpecialCaseTypesDTO;
import com.beshara.csc.inf.business.entity.ISpecialCaseTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

public class SpecialCaseTypesClientTest {
    ISpecialCaseTypesClient client = 
        InfClientFactory.getSpecialCaseTypesClient();

    public SpecialCaseTypesClientTest() {
    }

    public static void main(String[] args) {
        SpecialCaseTypesClientTest test = new SpecialCaseTypesClientTest();
        try { //test.add ( ) ; 
            //test.update ( ) ; 
            //test.getAll ( ) ; 
            //test.searchByCode ( ) ; 
            //test.searchByName ( ) ; 
            test.remove();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void getAll() throws DataBaseException, SharedApplicationException {
        List<IBasicDTO> list = client.getAll();
        for (IBasicDTO dto: list) {
            System.out.println(dto.getCode().getKey());
            System.out.println(dto.getName());
        }
    }

    public void searchByCode() throws DataBaseException, 
                                      SharedApplicationException {
        List<IBasicDTO> list = client.searchByCode(1L);
        for (IBasicDTO dto: list) {
            System.out.println(dto.getCode().getKey());
            System.out.println(dto.getName());
        }
    }

    public void searchByName() throws DataBaseException, 
                                      SharedApplicationException {
        List<IBasicDTO> list = client.searchByName("%");
        for (IBasicDTO dto: list) {
            System.out.println(dto.getCode().getKey());
            System.out.println(dto.getName());
        }
    }

    public void add() throws DataBaseException, SharedApplicationException {
        ISpecialCaseTypesDTO dto = InfDTOFactory.createSpecialCaseTypesDTO();
        dto.setName("Special Case TYPE FOR TEST");
        client.add(dto);
    }

    public void update() throws DataBaseException, SharedApplicationException {
        ISpecialCaseTypesDTO dto = 
            (ISpecialCaseTypesDTO)client.getById(InfEntityKeyFactory.createSpecialCaseTypesEntityKey(11L));
        dto.setName("Special Case TYPE UPDATED");
        client.update(dto);
    }

    public void remove() throws DataBaseException, SharedApplicationException {
        ISpecialCaseTypesDTO dto = 
            (ISpecialCaseTypesDTO)client.getById(InfEntityKeyFactory.createSpecialCaseTypesEntityKey(11L));
        ISpecialCaseTypesDTO dto2 = 
            (ISpecialCaseTypesDTO)client.getById(InfEntityKeyFactory.createSpecialCaseTypesEntityKey(12L));
        List<IBasicDTO> delList = new ArrayList<IBasicDTO>();
        delList.add(dto);
        delList.add(dto2);
        List<IResultDTO> removedList = client.remove(delList);
        for (IResultDTO res: removedList) {
            System.out.println(res.getStatus());
        }
    }
}
