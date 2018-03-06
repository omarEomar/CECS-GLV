package com.beshara.csc.inf.business.client;


public class InfSessionBeanClientFactory extends InfClientFactory {

//    protected IDecisionMakerTypesClient createDecisionMakerTypesClient() {
//        return new DecisionMakerTypesClientImpl(SessionBeanProviderHelper.getContextSession(DecisionMakerTypesSession.class));
//    }
    // create the default IKwtCitizensResidentsClient instance  
    protected IKwtCitizensResidentsClient createKwtCitizensResidentsClient() {
    return new KwtCitizensResidentsClientImpl();
    }
    // create IKwtCitizensResidentsClient instance for center [optional]   
    protected IKwtCitizensResidentsClient createKwtCitizensResidentsClientForCenter() {       
    return new KwtCitizensResidentsClientImpl();    
    }    
    // create IKwtCitizensResidentsClient instance for ministry [optional]    
    protected IKwtCitizensResidentsClient createKwtCitizensResidentsClientForMinistry(Long ministryCode) {
    return new KwtCitizensResidentsClientImpl();
    
    }
    
        // create InfDocumentTypesClient instance

  
    protected IInfDocumentTypesClient createInfDocumentTypesClient() {
        return new InfDocumentTypesClientImpl();
    }

}
