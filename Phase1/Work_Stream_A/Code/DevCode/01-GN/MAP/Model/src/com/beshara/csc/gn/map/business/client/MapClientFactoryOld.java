package com.beshara.csc.gn.map.business.client;


import com.beshara.csc.gn.map.business.deploy.DataSession;
import com.beshara.csc.gn.map.business.deploy.MappedDataSession;
import com.beshara.csc.gn.map.business.deploy.ObjectTypesSession;
import com.beshara.csc.gn.map.business.deploy.SocietiesSession;
import com.beshara.csc.sharedutils.business.util.ServiceLocator;

import javax.naming.NamingException;

/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Represents The Factory Which Generates appropriate Implementation * Based on the Key Returned from the Properties File I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class MapClientFactoryOld {
    /** 
     * DataClientFactory Default Constructor */
    public MapClientFactoryOld() {
    }

    /** 
     * @return IDataClient 
     */
    public static IDataClient getDataClient() {
        try {
            ServiceLocator serviceLocator = ServiceLocator.getInstance();
            DataSession dataSession = 
                (DataSession)serviceLocator.lookup("DataSession", 
                                                   DataSession.class);
            if (dataSession != null) {
                return new DataClientImpl();
            }
        } catch (NamingException e) {
            System.out.println(e);
        }
        return null;
    }

    /** 
     * @return IMappedDataClient 
     */
    public static IMappedDataClient getMappedDataClient() {
        try {
            ServiceLocator serviceLocator = ServiceLocator.getInstance();
            MappedDataSession mappedDataSession = 
                (MappedDataSession)serviceLocator.lookup("MappedDataSession", 
                                                         MappedDataSession.class);
            if (mappedDataSession != null) {
                return new MappedDataClientImpl();
            }
        } catch (NamingException e) {
            System.out.println(e);
        }
        return null;
    }

    /** 
     * @return IObjectTypesClient 
     */
    public static IObjectTypesClient getObjectTypesClient() {
        try {
            ServiceLocator serviceLocator = ServiceLocator.getInstance();
            ObjectTypesSession objectTypesSession = 
                (ObjectTypesSession)serviceLocator.lookup("ObjectTypesSession", 
                                                          ObjectTypesSession.class);
            if (objectTypesSession != null) {
                return new ObjectTypesClientImpl();
            }
        } catch (NamingException e) {
            System.out.println(e);
        }
        return null;
    }

    /** 
     * @return ISocietiesClient 
     */
    public static ISocietiesClient getSocietiesClient() {
        try {
            ServiceLocator serviceLocator = ServiceLocator.getInstance();
            SocietiesSession societiesSession = 
                (SocietiesSession)serviceLocator.lookup("SocietiesSession", 
                                                        SocietiesSession.class);
            if (societiesSession != null) {
                return new SocietiesClientImpl();
            }
        } catch (NamingException e) {
            System.out.println(e);
        }
        return null;
    }
}
