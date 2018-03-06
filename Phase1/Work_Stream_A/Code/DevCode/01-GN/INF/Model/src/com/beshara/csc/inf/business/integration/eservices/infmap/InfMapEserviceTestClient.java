package com.beshara.csc.inf.business.integration.eservices.infmap;


import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class InfMapEserviceTestClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            IInfMapEservice iInfMapEservice =
                (IInfMapEservice)context.lookup("InfInfMapEservice#com.beshara.csc.inf.business.integration.eservices.infmap.IInfMapEservice");
            iInfMapEservice.getAllStreet();
            iInfMapEservice.getFirstLevel();
            iInfMapEservice.getChildrenList("3");
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
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
