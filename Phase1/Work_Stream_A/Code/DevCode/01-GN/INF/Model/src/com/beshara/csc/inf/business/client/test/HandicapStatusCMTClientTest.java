package com.beshara.csc.inf.business.client.test;

import com.beshara.csc.inf.business.client.IHandicapStatusCMTClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IHandicapStatusDTO;
import com.beshara.csc.inf.business.entity.IHandicapStatusEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;


public class HandicapStatusCMTClientTest {
    IHandicapStatusCMTClient client;

    public HandicapStatusCMTClientTest() {
        client = InfClientFactory.getHandicapStatusCMTClient();
    }

    public static void main(String... args) {
        new HandicapStatusCMTClientTest().test();
    }

    public void test() {
        try {
            IHandicapStatusEntityKey ek = 
                InfEntityKeyFactory.createHandicapStatusEntityKey(2L);
            IHandicapStatusDTO dto = (IHandicapStatusDTO)client.getById(ek);
            client.postRecord(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
