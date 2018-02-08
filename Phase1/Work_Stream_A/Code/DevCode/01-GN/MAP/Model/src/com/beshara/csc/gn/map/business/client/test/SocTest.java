package com.beshara.csc.gn.map.business.client.test;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.map.business.client.IObjectTypesClient;
import com.beshara.csc.gn.map.business.client.ISocietiesClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public class SocTest {
    public SocTest() {
    }

    public static void main(String[] s) { /*test getAllByName in Societies*/
        ISocietiesClient socClient = MapClientFactory.getSocietiesClient();
        List<IBasicDTO> socList;
        try {
            socList = socClient.searchByName("name");
            for (IBasicDTO data: socList) {
                System.out.println(data.getName());
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) { // TODO 
        } /*test getAllByName in ObjectTypes*/
        IObjectTypesClient objClient = MapClientFactory.getObjectTypesClient();
        List<IBasicDTO> objList;
        try {
            objList = objClient.searchByName("objectTypesDTO");
            for (IBasicDTO data: objList) {
                System.out.println(data.getName());
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }
}
