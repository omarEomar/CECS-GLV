package com.beshara.csc.inf.business.integration.eservices.infkwcitizen;


import com.beshara.eservice.emp.request.validation.KwtCitizensResidentsEserviceDTO;

import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class KwtCitizensResidentsEserviceTestClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            IKwtCitizensResidentsEservice iKwtCitizensResidentsEservice =
                (IKwtCitizensResidentsEservice)context.lookup("InfKwtCitizensResidentsEservice#com.beshara.csc.inf.business.integration.eservices.infkwcitizen.IKwtCitizensResidentsEservice");
            KwtCitizensResidentsEserviceDTO dto = new KwtCitizensResidentsEserviceDTO();
            dto = (KwtCitizensResidentsEserviceDTO)iKwtCitizensResidentsEservice.getCitizenName(284072200455L);
            KwtCitizensResidentsEserviceDTO kwtCitizensResidentsEserviceDTO = new KwtCitizensResidentsEserviceDTO();
            kwtCitizensResidentsEserviceDTO.setCivilId(284072200455L);
            kwtCitizensResidentsEserviceDTO.setEMail("khaled@tl01");
            kwtCitizensResidentsEserviceDTO.setMobileNo("55545152");
            kwtCitizensResidentsEserviceDTO.setPhonesNo("22240405");
            kwtCitizensResidentsEserviceDTO.setMapCode("102");
            kwtCitizensResidentsEserviceDTO.setStreetCode(6L);
            kwtCitizensResidentsEserviceDTO.setBuildingNo("4");
            kwtCitizensResidentsEserviceDTO.setFloorNo(2L);
            kwtCitizensResidentsEserviceDTO.setFlatNo(3L);
            iKwtCitizensResidentsEservice.update(kwtCitizensResidentsEserviceDTO);
            
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }
}
