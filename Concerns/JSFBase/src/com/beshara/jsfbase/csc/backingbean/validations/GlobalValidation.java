package com.beshara.jsfbase.csc.backingbean.validations;

import com.beshara.csc.inf.business.client.IKwtCitizensResidentsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class GlobalValidation {

    public GlobalValidation() {
    }


    public static SharedUtilBean getSharedUtil() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (SharedUtilBean)app.createValueBinding("#{shared_util}").getValue(ctx);
    }

    /**
     * Purpose: this method return true if the civil id exist  
     * Creation/Modification History :
     * 1.1 - Developer Name:  Ahmed Abd El-Fatah
     * 1.2 - Date:  28-10-2008 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public static boolean isValidCivilId(Long civilId) {
        try {
//            InfMappedFactory factory = (InfMappedFactory)MappedFactory.getFactory(InfMappedFactory.class);            
//            IKwtCitizensResidentsClient iClient = factory.getKwtCitizensResidentsClient();
//            return iClient.checkCivilIdExist(civilId);

            IKwtCitizensResidentsClient kwtCitizensClient = InfClientFactory.getKwtCitizensResidentsClient();
            kwtCitizensClient.checkCivilIdExist(civilId);
            return true;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            return false;
        } catch (DataBaseException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }        
    }
}
