package com.beshara.csc.integration.presentation.backingbean.reg;

import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegulationStatusDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegulationStatusEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

public class RegulationAddBean extends LookUpBaseBean{
    // it represents action method that will be called before adding reg , and it will carry shared code to initalize before save like case that exist in sal
    private String initializeBeforeSaveMethod;
    // object that will be saved so you must set it from your use case bean
    private IRegulationsDTO regulationsDTO=null;
    
    // this method will be called after save method in div to reset or reintialize before return to calling use-case
    private String resetButtonMethod;

    public RegulationAddBean() {
    }

    /**
     * Purpose: called @ action method that add regulation from your bean 
     * u must initialize regulationsDTO @ your bean by setting initializeBeforeSaveMethod or any method  
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   14-10-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     * reference : Sal Module (social raise ,increases,rewards,..)
     */  
    public void save(){
    
        if(initializeBeforeSaveMethod != null){
            executeMethodBinding(getInitializeBeforeSaveMethod(), null); 
        }
        if(regulationsDTO!=null){
                try{
                        IRegulationStatusDTO regulationStatusDTO = RegDTOFactory.createRegulationStatusDTO();
                       
                        regulationStatusDTO.setCode( RegEntityKeyFactory.createRegulationStatusEntityKey(ISystemConstant.REGULATION_STATUS_VALID));
                        regulationsDTO.setStatusDto(regulationStatusDTO);
                        
                        RegClientFactory.getRegulationsClient().add(regulationsDTO) ;
                        getSharedUtil().handleSuccMsg("SuccessAddsRegulation");
                        
                    } catch (SharedApplicationException e) {
                         getSharedUtil().handleException(e,"com.beshara.jsfbase.csc.msgresources.globalexceptions","FailureInAddForRegulation");
                    } catch (DataBaseException e) {
                         getSharedUtil().handleException(e,"com.beshara.jsfbase.csc.msgresources.globalexceptions","FailureInAddForRegulation");
                    }catch (Exception e) {
                         getSharedUtil().handleException(e,"com.beshara.jsfbase.csc.msgresources.globalexceptions","FailureInAddForRegulation");
                    }
        }
        else  
                setErrorMsg("FailureInAddForRegulation");
                
        if(resetButtonMethod != null)
            executeMethodBinding(getResetButtonMethod(), null);         
    }
    
    public void setInitializeBeforeSaveMethod(String initializeBeforeSaveMethod) {
        this.initializeBeforeSaveMethod = initializeBeforeSaveMethod;
    }

    public String getInitializeBeforeSaveMethod() {
        return initializeBeforeSaveMethod;
    }

    public void setRegulationsDTO(IRegulationsDTO regulationsDTO) {
        this.regulationsDTO = regulationsDTO;
    }

    public IRegulationsDTO getRegulationsDTO() {
        return regulationsDTO;
    }


    public void setResetButtonMethod(String resetButtonMethod) {
        this.resetButtonMethod = resetButtonMethod;
    }

    public String getResetButtonMethod() {
        return resetButtonMethod;
    }
}
