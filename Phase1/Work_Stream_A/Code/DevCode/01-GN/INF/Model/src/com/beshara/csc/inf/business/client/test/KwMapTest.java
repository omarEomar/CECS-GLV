package com.beshara.csc.inf.business.client.test;

import com.beshara.base.entity.*;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.client.IKwMapClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwMapDTO;
import com.beshara.csc.inf.business.entity.IKwMapEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class KwMapTest {
    IKwMapClient kwMap;

    public KwMapTest() {
        kwMap = InfClientFactory.getKwMapClient();
    }

    public static void main(String[] args) {
        KwMapTest test = new KwMapTest();
        test.getById();
    }

    void getById() {
        try {
            IKwMapDTO dto = 
                (IKwMapDTO)kwMap.getById(InfEntityKeyFactory.createKwMapEntityKey("1"));
            System.out.println("" + dto);
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }
}
