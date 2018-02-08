package com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle;

import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionExcute.DecisionExcuteListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionRevision.DecisionRevisionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionSuggestion.DecisionSuggestionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.details.DecisionEmployeesModel;
import com.beshara.csc.nl.reg.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.nl.reg.presentation.backingbean.util.RegConfig;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;


public class DecisionCycleMaintainBean extends ManyToManyMaintainBaseBean {
    private static final String BEAN_NAME = "decisionCycleMaintainBean";
    private static final String INTEGRATION_PAGE_SUFFIX = "-integration";
    private Map<String, Object> detailsSavedStates;
    String finishNavigationCase=null;
    private boolean cancelDecisionMode;
    private boolean  copyDecisionWithEmployeesMode=false;
    
    private String lisBeanName=null;
    private Long currentStag=1L;
    private  boolean showOnly=false;
    
    public DecisionCycleMaintainBean() {
        super.setClient(RegClientFactory.getDecisionsClient());
       
        if(isIntegrationPage()){
            setCurrentStep("employeesadd");
            setNextNavigationCase("decisionmaindata"+INTEGRATION_PAGE_SUFFIX);
            setFinishNavigationCase("decisionlist"+INTEGRATION_PAGE_SUFFIX);
        }
        else
        {
            setCurrentStep("decisionadd");
            setNextNavigationCase("decisioncycleemployeesmaintain");
            
//            setFinishNavigationCase("decisionlist");
        }
    }
    
    public static DecisionCycleMaintainBean getInstance(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (DecisionCycleMaintainBean)app.createValueBinding("#{" + BEAN_NAME + "}").getValue(ctx);
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
            //nc += "cancel";
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
               
                DecisionListBean decisionListBean = 
                    (DecisionListBean)evaluateValueBinding("decisionListBean");
                    
                if(decisionListBean.isCancelDescisionFlag()==false){
                
                setCurrentStep("decisionadd");
                setNextNavigationCase("decisionemployeesmaintain");
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
        DecisionEmployeesModel decisionEmployeesModelSessionBean = (DecisionEmployeesModel)evaluateValueBinding("decisionEmployeesModel");                                          
//        finishNavigationCase = null;
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
                
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


        if (getFinishNavigationCase()!= null && !isShowErrorMsg()) {
            DecisionCycleMaintainBean maintainBean = 
                (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{decisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
            FacesContext ctx = FacesContext.getCurrentInstance();
            Application app = ctx.getApplication();
            if (maintainBean.getCurrentStag() == 1) {
                DecisionSuggestionListBean decisionListBean = 
                    (DecisionSuggestionListBean)app.createValueBinding("#{decisionSuggestionListBean}").getValue(ctx);
                decisionListBean.setSearchMode(false);
                decisionListBean.getPageIndex((String)getPageDTO().getCode().getKey());
                decisionListBean.getHighlightedDTOS().add(getPageDTO());
                highlighDataTable("decisionSuggestionListBean");
               
            }
            if (maintainBean.getCurrentStag() == 2) {
                DecisionRevisionListBean decisionListBean = 
                    (DecisionRevisionListBean)app.createValueBinding("#{decisionRevisionListBean}").getValue(ctx);
                if (!decisionListBean.isShowAllFlag()) {
                    decisionListBean.setSearchMode(true);
                    decisionListBean.getDecisionSearchDTO().setCurrentStage(2L);
                    decisionListBean.getDecisionSearchDTO().setRegStatusFlage(0L);
                    try {
                        if (decisionListBean.getDecisionSearchDTO() != 
                            null) {
                            if (decisionListBean.getDecisionSearchDTO().getName() != 
                                null) {
                                if (!(decisionListBean.getDecisionSearchDTO().getName().equals(""))) {
                                    decisionListBean.getDecisionSearchDTO().setName(formatSearchString(decisionListBean.getDecisionSearchDTO().getName()));
                                }
                            }

                            decisionListBean.generatePagingRequestDTO("decisionRevisionListBean", 
                                                                        "searchWithPaging");
                            decisionListBean.setStringSearchType("false");
                            decisionListBean.setSelectedRadio("");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    decisionListBean.setSearchMode(true);
                    decisionListBean.setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                    decisionListBean.setStringSearchType("false");
                    decisionListBean.setSelectedRadio("");
                    decisionListBean.getGetAllDTO().setCurrentStage(2L);
                     decisionListBean.getGetAllDTO().setRegStatusFlage(0L);
                      decisionListBean.getAll();
                }

                decisionListBean.getPageIndex((String)getPageDTO().getCode().getKey());
                decisionListBean.getHighlightedDTOS().add(getPageDTO());
                highlighDataTable("decisionRevisionListBean");

            }
            if(maintainBean.getCurrentStag() == 3){
            
                DecisionExcuteListBean decisionListBean = 
                    (DecisionExcuteListBean)app.createValueBinding("#{decisionExcuteListBean}").getValue(ctx);
                    if (!decisionListBean.isShowAllFlag()) {
                    decisionListBean.setSearchMode(true);
                    decisionListBean.getDecisionSearchDTO().setCurrentStage(3L);
                    decisionListBean.getDecisionSearchDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
                    decisionListBean.getDecisionSearchDTO().setRegStatusFlage(3L);

                    try {
                        if (decisionListBean.getDecisionSearchDTO() != 
                            null) {
                            if (decisionListBean.getDecisionSearchDTO().getName() != 
                                null) {
                                if (!(decisionListBean.getDecisionSearchDTO().getName().equals(""))) {
                                    decisionListBean.getDecisionSearchDTO().setName(formatSearchString(decisionListBean.getDecisionSearchDTO().getName()));
                                }
                            }
                            if (decisionListBean.isUsingPaging()) {
                                decisionListBean.generatePagingRequestDTO("decisionExcuteListBean", 
                                                                            "searchWithPaging");
                                decisionListBean.setStringSearchType("false");
                                decisionListBean.setSelectedRadio("");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    decisionListBean.setSearchMode(true);
                    decisionListBean.setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                    decisionListBean.setStringSearchType("false");
                    decisionListBean.setSelectedRadio("");
                    decisionListBean.getGetAllDTO().setCurrentStage(3L);
                    decisionListBean.getGetAllDTO().setRegStatusFlage(3L);
                     decisionListBean.getGetAllDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());

                    decisionListBean.getAll();
                }
                decisionListBean.getPageIndex((String)getPageDTO().getCode().getKey());
                decisionListBean.getHighlightedDTOS().add(getPageDTO());
                highlighDataTable("decisionExcuteListBean");
            }
            return getFinishNavigationCase();
        }
        decisionEmployeesModelSessionBean.resetSessionData();
        return getFinishNavigationCase();
    }

    

    public String back(){
        if(getIntegrationBean() != null && getIntegrationBean().getModuleFrom() != null && !getIntegrationBean().getModuleFrom().equals("")){
            return getIntegrationBean().cancelGoTO();
        }
        DecisionEmployeesModel decisionEmployeesModelSessionBean =(DecisionEmployeesModel)evaluateValueBinding("decisionEmployeesModel");
        decisionEmployeesModelSessionBean.resetSessionData();
         String finishNavigationCase =  super.back();

                if (finishNavigationCase!= null ) {
                    DecisionCycleMaintainBean maintainBean = 
                        (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{decisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
                    FacesContext ctx = FacesContext.getCurrentInstance();
                    Application app = ctx.getApplication();
                    if (maintainBean.getCurrentStag() == 2) {
                        DecisionRevisionListBean decisionListBean = 
                            (DecisionRevisionListBean)app.createValueBinding("#{decisionRevisionListBean}").getValue(ctx);
                        if (!decisionListBean.isShowAllFlag()) {
                            decisionListBean.setSearchMode(true);
                            decisionListBean.getDecisionSearchDTO().setCurrentStage(2L);
                            decisionListBean.getDecisionSearchDTO().setRegStatusFlage(0L);
                            try {
                                if (decisionListBean.getDecisionSearchDTO() != 
                                    null) {
                                    if (decisionListBean.getDecisionSearchDTO().getName() != 
                                        null) {
                                        if (!(decisionListBean.getDecisionSearchDTO().getName().equals(""))) {
                                            decisionListBean.getDecisionSearchDTO().setName(formatSearchString(decisionListBean.getDecisionSearchDTO().getName()));
                                        }
                                    }

                                    decisionListBean.generatePagingRequestDTO("decisionRevisionListBean", 
                                                                                "searchWithPaging");
                                    decisionListBean.setStringSearchType("false");
                                    decisionListBean.setSelectedRadio("");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            decisionListBean.setSearchMode(true);
                            decisionListBean.setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                            decisionListBean.setStringSearchType("false");
                            decisionListBean.setSelectedRadio("");
                            decisionListBean.getGetAllDTO().setCurrentStage(2L);
                             decisionListBean.getGetAllDTO().setRegStatusFlage(0L);
                    try {
                        decisionListBean.getAll();
                    } catch (DataBaseException e) {
                        // TODO
                    }
                }
                        decisionListBean.resetPageIndex();
                        decisionListBean.unCheck();
                        if (decisionListBean.getSelectedDTOS() != null)
                            decisionListBean.getSelectedDTOS().clear();
                        if (decisionListBean.getHighlightedDTOS() != null)
                            decisionListBean.getHighlightedDTOS().clear();
                        decisionListBean.repositionPage(0);
                        decisionListBean.setStringSearchType("false");
                        decisionListBean.setSelectedRadio("");
                    }
                    if(maintainBean.getCurrentStag() == 3){
                    
                        DecisionExcuteListBean decisionListBean = 
                            (DecisionExcuteListBean)app.createValueBinding("#{decisionExcuteListBean}").getValue(ctx);
                            if (!decisionListBean.isShowAllFlag()) {
                            decisionListBean.setSearchMode(true);
                            decisionListBean.getDecisionSearchDTO().setCurrentStage(3L);
                            decisionListBean.getDecisionSearchDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
                            decisionListBean.getDecisionSearchDTO().setRegStatusFlage(3L);

                            try {
                                if (decisionListBean.getDecisionSearchDTO() != 
                                    null) {
                                    if (decisionListBean.getDecisionSearchDTO().getName() != 
                                        null) {
                                        if (!(decisionListBean.getDecisionSearchDTO().getName().equals(""))) {
                                            decisionListBean.getDecisionSearchDTO().setName(formatSearchString(decisionListBean.getDecisionSearchDTO().getName()));
                                        }
                                    }
                                    if (decisionListBean.isUsingPaging()) {
                                        decisionListBean.generatePagingRequestDTO("decisionExcuteListBean", 
                                                                                    "searchWithPaging");
                                        decisionListBean.setStringSearchType("false");
                                        decisionListBean.setSelectedRadio("");
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            decisionListBean.setSearchMode(true);
                            decisionListBean.setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                            decisionListBean.setStringSearchType("false");
                            decisionListBean.setSelectedRadio("");
                            decisionListBean.getGetAllDTO().setCurrentStage(3L);
                            decisionListBean.getGetAllDTO().setRegStatusFlage(3L);
                             decisionListBean.getGetAllDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());

                    try {
                        decisionListBean.getAll();
                    } catch (DataBaseException e) {
                        // TODO
                    }
                }
                        decisionListBean.resetPageIndex();
                        decisionListBean.unCheck();
                        if (decisionListBean.getSelectedDTOS() != null)
                            decisionListBean.getSelectedDTOS().clear();
                        if (decisionListBean.getHighlightedDTOS() != null)
                            decisionListBean.getHighlightedDTOS().clear();
                        decisionListBean.repositionPage(0);
                        decisionListBean.setStringSearchType("false");
                        decisionListBean.setSelectedRadio("");
                    }
            
        return finishNavigationCase;
        }
        return null;
        }
    public void add() throws DataBaseException {
        handleDecisionImage();
        SharedUtilBean sharedUtil = getSharedUtil();

        try {
            setPageDTO (((IDecisionsClient)getClient()).addSuggestedDecision(getPageDTO()));
            if (isUsingPaging()) {

                getPagingBean().clearDTOS();

                generatePagingRequestDTO((String)getPageDTO().getCode().getKey());

            } else {
                getAll();
                getPageIndex((String)getPageDTO().getCode().getKey());
            }

            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().add(getPageDTO());
            }
            this.setSearchQuery("");
            this.setSearchType(0);
            this.setSearchMode(false);
            sharedUtil.setSuccessMsgValue("SuccessAdds");

        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);

            //            sharedUtil.handleException(e, 
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
            //                                       "FailureInAdd");
            sharedUtil.handleException(e);
            this.setErrorMsg(sharedUtil.getErrMsgValue());
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg("FailureInAdd");


        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, 
                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                       "FailureInAdd");

        }
        //added by nora to reset radio if list has radio column
        setSelectedRadio("");
    }


    public void edit() throws DataBaseException, ItemNotFoundException, 
                              SharedApplicationException {
        handleDecisionImage();
        SharedUtilBean sharedUtil = getSharedUtil();

        try {
            if(getCurrentStag()!=3L){
                ((IDecisionsClient)getClient()).updateDecison(getPageDTO());
            }else{
                ((IDecisionsClient)getClient()).excuteDecison(getPageDTO());
            }
            cancelSearch();

            if (isUsingPaging()) {

                setUpdateMyPagedDataModel (true);
                setRepositionTable (true);
                getPagingBean().clearDTOS();
                if (getPagingRequestDTO ()== null) {
                    setPagingRequestDTO (new PagingRequestDTO());
                }
                getPagingRequestDTO().setRepositionKey((String)getPageDTO().getCode().getKey());

            }

            if (getHighlightedDTOS ()!= null) {
                getHighlightedDTOS().add(getPageDTO());
            }

            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            this.getSelectedDTOS().clear();

        } catch (DataBaseException e) {
            sharedUtil.handleException(e);
        } catch (ItemNotFoundException item) {
            sharedUtil.handleException(item);
        } catch (SharedApplicationException e) {
            sharedUtil.handleException(e);
        } catch (Exception e) {
            sharedUtil.handleException(e, 
                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                       "FailureInUpdate");
        }
        //Added By Yassmine to reset the value of radio button after Edit
        setSelectedRadio("");
    }


    public String saveDecision() throws DataBaseException, ItemNotFoundException, 
                              SharedApplicationException {
        handleDecisionImage();
        SharedUtilBean sharedUtil = getSharedUtil();
        DecisionEmployeesModel decisionEmployeesModelSessionBean =(DecisionEmployeesModel)evaluateValueBinding("decisionEmployeesModel");


        try {
            decisionEmployeesModelSessionBean.sendListtoBussiens();
            ((IDecisionsClient)getClient()).updateDecison(getPageDTO());

            cancelSearch();

            if (isUsingPaging()) {

                setUpdateMyPagedDataModel (true);
                setRepositionTable (true);
                getPagingBean().clearDTOS();
                if (getPagingRequestDTO ()== null) {
                    setPagingRequestDTO (new PagingRequestDTO());
                }
                getPagingRequestDTO().setRepositionKey((String)getPageDTO().getCode().getKey());

            }

            if (getHighlightedDTOS ()!= null) {
                getHighlightedDTOS().add(getPageDTO());
            }

            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            this.getSelectedDTOS().clear();

        } catch (DataBaseException e) {
            sharedUtil.handleException(e);
            //            sharedUtil.handleException(e, 
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
            //                                       "FailureInUpdate");
        } catch (ItemNotFoundException item) {
            sharedUtil.handleException(item);
            //            sharedUtil.handleException(item, 
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
            //                                       "FailureInUpdate");
        } catch (SharedApplicationException e) {
            //            sharedUtil.handleException(e, 
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
            //                                       "FailureInUpdate");
            sharedUtil.handleException(e);
        } catch (Exception e) {
            sharedUtil.handleException(e, 
                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                       "FailureInUpdate");
        }
        //Added By Yassmine to reset the value of radio button after Edit
        setSelectedRadio("");
        decisionEmployeesModelSessionBean.resetSessionData();
        return getFinishNavigationCase();
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
                    BeansUtil.saveRegImage(tempFilePath, RegConfig.getInstance().getDecisionUploadedImagesPath(), 
                                           imageFileName);
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


    public void setLisBeanName(String lisBeanName) {
        this.lisBeanName = lisBeanName;
    }

    public String getLisBeanName() {
        return lisBeanName;
    }

    public void setCurrentStag(Long currentStag) {
        this.currentStag = currentStag;
    }

    public Long getCurrentStag() {
        return currentStag;
    }

    public void setShowOnly(boolean showOnly) {
        this.showOnly = showOnly;
    }

    public boolean isShowOnly() {
        return showOnly;
    }
    
    public void navigate(ActionEvent actionEvent) {
        String stepKey = actionEvent.getComponent().getId();
        String currentStepKey=getCurrentStep();
        this.updateStepDependancies(currentStepKey);
        String nCase = null;
        if (validateStep(currentStepKey,stepKey) && checkStepRelevants(stepKey)) {
            //added for finish button status
            setValidated(currentStepKey);
            setVisited(currentStepKey);
            setVisited(stepKey);
           //set the finish button status
            setCurrentStep(stepKey); 
            getWizardBar().setCurrentStep(stepKey);
            if(getFinishButtonOverride(currentStepKey)==1)
            {
            setRenderFinish(getFinishButtonStatus(stepKey));
            }else if(getFinishButtonOverride(currentStepKey)==2)
            {
            setRenderFinish(false);
            }else{
                setRenderFinish(true);
            }
           
           //set the save button status
           
           
            
            //
            nCase = getNavigationCase(stepKey);
            setNextNavigationCase(getNextNavigationCase(stepKey));
            setPreviousNavigationCase(getPreviousNavigationCase(stepKey));
            System.out.println(stepKey);
        }
        
        if (nCase != null)
            handleNavigation(nCase);
    }

}
