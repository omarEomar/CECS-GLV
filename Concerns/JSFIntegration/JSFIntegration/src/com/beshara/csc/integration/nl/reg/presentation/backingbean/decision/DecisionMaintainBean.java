package com.beshara.csc.nl.reg.presentation.backingbean.decision;

import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.entity.IDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.details.DecisionEmployeesModelSessionBean;
import com.beshara.csc.nl.reg.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.nl.reg.presentation.backingbean.util.RegConfig;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import com.beshara.jsfbase.csc.util.wizardbar.WizardStep;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


public class DecisionMaintainBean extends ManyToManyMaintainBaseBean {
    private static final String BEAN_NAME = "decisionMaintainBean";
    private static final String INTEGRATION_PAGE_SUFFIX = "-integration";
    private Map<String, Object> detailsSavedStates;
    String finishNavigationCase=null;
    private boolean cancelDecisionMode;
    private boolean  copyDecisionWithEmployeesMode=false;
    
    public DecisionMaintainBean() {
        super.setClient(RegClientFactory.getDecisionsClient());
       
        if(isIntegrationPage()){
            setCurrentStep("employeesadd");
            setNextNavigationCase("decisionmaindata"+INTEGRATION_PAGE_SUFFIX);
            setFinishNavigationCase("decisionlist"+INTEGRATION_PAGE_SUFFIX);
        }
        else
        {
            setCurrentStep("decisionadd");
            setNextNavigationCase("decisionemployeesmaintain");
            setFinishNavigationCase("decisionlist");
        }
    }
    
    public static DecisionMaintainBean getInstance(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (DecisionMaintainBean)app.createValueBinding("#{" + BEAN_NAME + "}").getValue(ctx);
    }
    
    public static boolean isIntegrationPage(){
        try{
            String url = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString();
            return url != null && url.contains(INTEGRATION_PAGE_SUFFIX);
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public String getNavigationCase(String mapKey) {
        String nc = super.getNavigationCase(mapKey);
        if (isCancelDecisionMode()) {
        }
        if (getMaintainMode() == 1) {
            if(isIntegrationPage()){
                nc = nc.substring(0,nc.indexOf("-"))+ "edit" + INTEGRATION_PAGE_SUFFIX;
            }else{
                nc += "edit";
            }
        }
        return nc;
    }

    public boolean isRenderFinish() {
        if (copyDecisionWithEmployeesMode)
            return true;
        else
           return super.isRenderFinish();
    }

    public boolean isCancelDecisionMode() {
        
            if(getPageDTO() != null &&(((IDecisionsDTO)getPageDTO()).getDecisionsDTOList() != null && ((IDecisionsDTO)getPageDTO()).getDecisionsDTOList().size() != 0)){
               
                DecisionListBean decisionListBean = (DecisionListBean)evaluateValueBinding("decisionListBean");
                if(decisionListBean.isCancelDescisionFlag()==false){
                setCurrentStep("decisionadd");
                setNextNavigationCase("decisionemployeesmaintain");
                setFinishNavigationCase("decisionlist");
                }
                else if(decisionListBean.isCancelDescisionFlag()==true && cancelDecisionMode == false){
                    setCurrentStep("canceldecisionadd");
                    setNextNavigationCase("decisionreferencesmaintain");
                    setFinishNavigationCase("decisionlist");
                    cancelDecisionMode = true;
                }
               
//                if (getCurrentStep().equals("canceldecisionadd")){
//                    if(isIntegrationPage()){
//                        setNextNavigationCase("decisionreferencesmaintain"+INTEGRATION_PAGE_SUFFIX);
//                    }else{
//                        setNextNavigationCase("decisionreferencesmaintain");
//                    }
//                    cancelDecisionMode = true;
//                } 
                
//                if (!getCurrentStep().equals("cancelreferencesadd")){
//                    setCurrentStep("canceldecisionadd");
//                    if(isIntegrationPage()){
//                        setNextNavigationCase("decisionreferencesmaintain"+INTEGRATION_PAGE_SUFFIX);
//                    }else{
//                        setNextNavigationCase("decisionreferencesmaintain");
//                    }
//                    cancelDecisionMode = true;
//                } else{
//                    setNextNavigationCase(null);
//                    if(isIntegrationPage()){
//                        setPreviousNavigationCase("decisionmaindatacancel"+INTEGRATION_PAGE_SUFFIX);
//                    }else{
//                        setPreviousNavigationCase("decisionmaindatacancel");
//                    }
//                }

            }
        
        return cancelDecisionMode;
    }

    public boolean isAddDecisionMode() {
        return getMaintainMode() == 0 && !isCancelDecisionMode();
    }
    public IntegrationBean getIntegrationBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (IntegrationBean)app.createValueBinding("#{integrationBean}").getValue(ctx);
    }
    public String finish() throws DataBaseException, ItemNotFoundException, 
                                  SharedApplicationException {
        if (getPageDTO().getCode() == null) {
            getPageDTO().setCode(RegEntityKeyFactory.createDecisionsEntityKey());
        }
     if(getIntegrationBean() != null && getIntegrationBean().getModuleFrom() != null){
            String currentStepID=getCurrentStep();
            if(currentStepID!=null && !currentStepID.equals("")){
                try{
                      String detailBeanName = getStepBeanName(getCurrentStep());
                      Boolean isValid = (Boolean)executeMethodBinding(detailBeanName+".validate",null);
                      
                      if( isValid!=null && (!isValid.booleanValue()))
                         return null;
                     
                  }catch(Exception e){e.printStackTrace();}
                }
                
                if(getIntegrationBean().getModuleFrom().equals("mov")){
                    getIntegrationBean().getSelectedDTOTo().add(getPageDTO());
                    return getIntegrationBean().goFrom();
                }
                if(getIntegrationBean().getModuleFrom().equals("prm")){
                    getIntegrationBean().getSelectedDTOTo().add(getPageDTO());
                    return getIntegrationBean().goFrom();
                }
                if(getIntegrationBean().getModuleFrom().equals("emp")){
                    //super.finish();
                    getIntegrationBean().getSelectedDTOTo().add(getPageDTO());
                    return getIntegrationBean().goFrom();
                }
                if(getIntegrationBean().getModuleFrom().equals("sal")){
                    getIntegrationBean().getSelectedDTOTo().add(getPageDTO());
                    return getIntegrationBean().goFrom();
                }
                if(getIntegrationBean().getModuleFrom().equals("vac")){
                    getIntegrationBean().getSelectedDTOTo().add(getPageDTO());
                    return getIntegrationBean().goFrom();
                }
                if(getIntegrationBean().getModuleFrom().equals("eos")){
                    getIntegrationBean().getSelectedDTOTo().add(getPageDTO());
                    return getIntegrationBean().goFrom();
                }
            return null;    
        }
        else{
            return defaultAddDecisoin();
        }
        //return null;
    }

    public String defaultAddDecisoin() throws DataBaseException, 
                                          ItemNotFoundException, 
                                          SharedApplicationException {
        DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean = (DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
        finishNavigationCase = null;
        String currentstepId = getCurrentStep();
        try {
            if (getFinishButtonOverride(currentstepId) == 1) {
                setRenderFinish(getFinishButtonStatus(currentstepId));
            } else if (getFinishButtonOverride(currentstepId) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }

            decisionEmployeesModelSessionBean.sendListtoBussiens();
         
            if (validateStep(currentstepId, null)) {
                if (this.getMaintainMode() == 0) {
                    this.add();
                } else if (this.getMaintainMode() == 1) {
                    this.edit();
                    //   setPageDTO(super.getClient().getById(getPageDTO().getCode()));
                }

                updateStepData(currentstepId);
                finishNavigationCase = getFinishNavigationCase();
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return "decisionlist";
        }


        if (finishNavigationCase != null && !isShowErrorMsg()) {
            //      DecisionListBean decisionListBean = (DecisionListBean)evaluateValueBinding("decisionListBean");

            //            decisionListBean.getHighlightedDTOS().add(getPageDTO());
            //            
            //            if (decisionListBean.isUsingPaging()) {
            //                if (decisionListBean!= null) {
            //                
            //                     decisionListBean.setRepositionTable(true);
            //                     decisionListBean.setSortedTable(false);
            //                     decisionListBean.generatePagingRequestDTO((String)getPageDTO().getCode().getKey());
            //                     
            //                }
            //            } else {
            //                 decisionListBean.getPageIndex((String)getPageDTO().getCode().getKey());
            //            }
            //            
            highlighDataTable("decisionListBean");
            return finishNavigationCase;
        }
        decisionEmployeesModelSessionBean.resetSessionData();
        return "decisionlist";
    }

    

    public String back(){
        if(getIntegrationBean() != null && getIntegrationBean().getModuleFrom() != null && !getIntegrationBean().getModuleFrom().equals("")){
            return getIntegrationBean().cancelGoTO();
        }
        DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean =(DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
        decisionEmployeesModelSessionBean.resetSessionData();
        return super.back();
    }
    public void add() throws DataBaseException {
        handleDecisionImage();
        super.add();
    }

    public void edit() throws DataBaseException, ItemNotFoundException, 
                              SharedApplicationException {
        handleDecisionImage();
        super.edit();
    }

    public void setDetailsSavedStates(Map<String, Object> detailsSavedStates) {
        this.detailsSavedStates = detailsSavedStates;
    }

    public Map<String, Object> getDetailsSavedStates() {
        if (detailsSavedStates == null) {
            detailsSavedStates = new HashMap<String, Object>();
        }
        return detailsSavedStates;
    }

    private void handleDecisionImage() {
        String tempFilePath = 
            (String)getDetailsSavedStates().get(BeansUtil.UPLOADED_IMAGE_BINDING_KEY);
        if (tempFilePath != null) {
            String imageFileName = getCurrentDecisionKey();
            try {
                String relativeFileName = 
                    BeansUtil.saveRegImage(tempFilePath, RegConfig.getInstance().getDecisionUploadedImagesPath(), imageFileName);
                ((IDecisionsDTO)getPageDTO()).setDecisionImage(relativeFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getCurrentDecisionKey() {
        String decisionNumber = "";
        String decisionTypeCode = "";
        String issuanceYearCode = "";
        if (getMaintainMode() == 0) { // add
            decisionNumber = 
                    ((IDecisionsDTO)getPageDTO()).getDecisionNumber().toString();
        } else if (getMaintainMode() == 1) { // edit
            decisionNumber = 
                    ((IDecisionsEntityKey)(getPageDTO().getCode())).getDecisionNumber().toString();
        }
        decisionTypeCode = 
                ((IDecisionsDTO)getPageDTO()).getTypesDTO().getCode().getKey().toString();
        issuanceYearCode = 
                ((IDecisionsDTO)getPageDTO()).getYearsDTO().getCode().getKey().toString();

        return decisionNumber + "_" + decisionTypeCode + "_" + 
            issuanceYearCode;
    }

    public void setCancelDecisionMode(boolean cancelDecisionMode) {
        this.cancelDecisionMode = cancelDecisionMode;
    }

    public void setCopyDecisionWithEmployeesMode(boolean copyDecisionWithEmployeesMode) {
        this.copyDecisionWithEmployeesMode = copyDecisionWithEmployeesMode;
    }

    public boolean isCopyDecisionWithEmployeesMode() {
        return copyDecisionWithEmployeesMode;
    }
    public String getCurrentStepJSValidation() {
        String clientSideJavaScript = null;
        WizardStep currentStep = 
            (WizardStep)(getWizardBar().getWizardSteps()).get(getCurrentStep());

        if (currentStep != null)
            clientSideJavaScript = "if(vaildateIssuanceYearWithRegDate()){ block(); return true;  } else { return false; }";

        if (clientSideJavaScript == null)
            clientSideJavaScript = "if( stepValidation()){ block(); return true;  } else { return false; }";
        return clientSideJavaScript; 
        }
  
}
