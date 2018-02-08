package com.beshara.csc.gn.map.business.client;

import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.csc.gn.map.business.deploy.DataSession;


public class MapSessionBeanClientFactory extends MapClientFactory {

    public MapSessionBeanClientFactory() {
    }


    protected IDataClient createDataClient() {
        DataSession readDataSession = (DataSession)ClientFactoryUtil.getReadSessionBean(DataSession.class);
        DataSession updateDataSession = (DataSession)ClientFactoryUtil.getUpdateSessionBean(DataSession.class);
        return new DataClientImpl();
    }

    protected IMappedDataClient createMappedDataClient() {
        return new MappedDataClientImpl();
    }

    protected IObjectTypesClient createObjectTypesClient() {
        return new ObjectTypesClientImpl();
    }

    protected ISocietiesClient createSocietiesClient() {
         return new SocietiesClientImpl();
    }
    
    protected IRelationsClient createRelationsClient() {

        return new RelationsClientImpl();
    }
    
    protected ISocietyRelationTypesClient createSocietyRelationTypesClient() {
        return new SocietyRelationTypesClientImpl();
    }
    
}
