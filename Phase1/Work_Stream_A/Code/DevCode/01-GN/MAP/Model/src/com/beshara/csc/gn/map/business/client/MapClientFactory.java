package com.beshara.csc.gn.map.business.client;

import com.beshara.common.factory.Factory;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Represents The Factory Which Generates appropriate Implementation
 * Based on the Key Returned from the Properties File .
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */
public abstract class MapClientFactory {
    private static MapClientFactory instance;

    private IDataClient dataClient;
    private IMappedDataClient mappedDataClient;
    private IObjectTypesClient objectTypesClient;
    private ISocietiesClient societiesClient;
    private IRelationsClient relationsClient;
    private ISocietyRelationTypesClient societyRelationTypesClient;
    
    
    
    public MapClientFactory() {
    }


    public static MapClientFactory getInstance() {
        if (instance == null) {
            if (Factory.containsInstance(MapClientFactory.class)) {
                instance = 
                        (MapClientFactory)Factory.getImplInstance(MapClientFactory.class);
            } else {
                instance = new MapSessionBeanClientFactory();
            }
        }
        return instance;
    }


    public static IDataClient getDataClient() {
        return getInstance().getDataClientImpl();
    }

    public IDataClient getDataClientImpl() {
        if (dataClient == null) {
            dataClient = createDataClient();
        }
        return dataClient;
    }

    protected abstract IDataClient createDataClient();


    public static IMappedDataClient getMappedDataClient() {
        return getInstance().getMappedDataClientImpl();
    }

    public IMappedDataClient getMappedDataClientImpl() {
        if (mappedDataClient == null) {
            mappedDataClient = createMappedDataClient();
        }
        return mappedDataClient;
    }

    protected abstract IMappedDataClient createMappedDataClient();


    public static IObjectTypesClient getObjectTypesClient() {
        return getInstance().getObjectTypesClientImpl();
    }

    public IObjectTypesClient getObjectTypesClientImpl() {
        if (objectTypesClient == null) {
            objectTypesClient = createObjectTypesClient();
        }
        return objectTypesClient;
    }

    protected abstract IObjectTypesClient createObjectTypesClient();


    public static ISocietiesClient getSocietiesClient() {
        return getInstance().getSocietiesClientImpl();
    }

    public ISocietiesClient getSocietiesClientImpl() {
        if (societiesClient == null) {
            societiesClient = createSocietiesClient();
        }
        return societiesClient;
    }

    protected abstract ISocietiesClient createSocietiesClient();
    
    
    public static IRelationsClient getRelationsClient() {
        return getInstance().getRelationsClientImpl();
    }

    public IRelationsClient getRelationsClientImpl() {
        if (relationsClient == null) {
            relationsClient = createRelationsClient();
        }
        return relationsClient;
    }

    protected abstract IRelationsClient createRelationsClient();
    
    
    public static ISocietyRelationTypesClient getSocietyRelationTypesClient() {
        return getInstance().getSocietyRelationTypesClientImpl();
    }

    public ISocietyRelationTypesClient getSocietyRelationTypesClientImpl() {
        if (societyRelationTypesClient == null) {
            societyRelationTypesClient = createSocietyRelationTypesClient();
        }
        return societyRelationTypesClient;
    }

    protected abstract ISocietyRelationTypesClient createSocietyRelationTypesClient();
    
    
    
}
