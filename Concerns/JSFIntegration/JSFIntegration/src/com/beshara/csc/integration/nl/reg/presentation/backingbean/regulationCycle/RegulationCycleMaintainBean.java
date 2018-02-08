package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.nl.reg.business.client.IRegulationsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IREGDedignTablesDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegulationsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.abstractDetails.RegulationAttachmentsMaintain;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.regulationExcute.RegulationsExcuteListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.regulationRevision.RegulationsRevisionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.regulationSuggestion.RegulationsSuggestionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.nl.reg.presentation.backingbean.util.RegConfig;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.reg.ColumnsHasNoRecordsException;
import com.beshara.csc.sharedutils.business.exception.reg.DataValueNoMatchColumnTypeException;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.wizardbar.WizardStep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;


public class RegulationCycleMaintainBean extends ManyToManyMaintainBaseBean {
    private static final String BEAN_NAME = "regulationCycleMaintainBean";
    private static final String INTEGRATION_PAGE_SUFFIX = "-integration";
    private Map<String, Object> detailsSavedStates;
    private boolean copyRegualationWithSubject=false;
    private boolean initializeTablesTab=false;
    private boolean copyFlage=false;
    private Long currentStage;
    public RegulationCycleMaintainBean() {
        super.setClient(RegClientFactory.getRegulationsClient());
        if(isIntegrationPage()){
            setCurrentStep("regulationadd");
            setNextNavigationCase("regulationsubjectsmaintain"+INTEGRATION_PAGE_SUFFIX);
            setFinishNavigationCase("regulationlist"+INTEGRATION_PAGE_SUFFIX);
       }
else{
            setCurrentStep("regulationadd");
            setNextNavigationCase("regulationcyclesubjectsmaintain");
        }
    }
    
    public void scrollerAction(ActionEvent ae) {
        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);setFocusOnlyOnElement('system_name')");
    }
    
    public boolean isRenderFinish() {
    if (copyRegualationWithSubject)
            return true;
        else
           return super.isRenderFinish();
    }
    public static RegulationCycleMaintainBean getInstance(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (RegulationCycleMaintainBean)app.createValueBinding("#{" + BEAN_NAME + "}").getValue(ctx);
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
        if (getMaintainMode() == 1) {
            nc += "edit";
        }
        if(isIntegrationPage()){
            nc += INTEGRATION_PAGE_SUFFIX;
        }
        return nc;
    }

    public String finish() throws DataBaseException, ItemNotFoundException, 
                                  SharedApplicationException {
        if (getPageDTO().getCode() == null) {
            getPageDTO().setCode(RegEntityKeyFactory.createRegulationsEntityKey());
        }
        if(getIntegrationBean() != null && getIntegrationBean().getModuleFrom() != null && (getIntegrationBean().getModuleFrom()!=null)){
        
            String currentStepID=getCurrentStep();
            if(currentStepID!=null && !currentStepID.equals("")){
                try{
                      String detailBeanName = getStepBeanName(getCurrentStep());
                      Boolean isValid = (Boolean)executeMethodBinding(detailBeanName+".validate",null);
                      
                      if( isValid!=null && (!isValid.booleanValue()))
                         return null;
                     
                  }catch(Exception e){e.printStackTrace();}
                }

            getIntegrationBean().getSelectedDTOTo().add(getPageDTO());
            return getIntegrationBean().goFrom();
        }
        else{
            return defaultAddRegulation();
        }
       
    }

    public String defaultAddRegulation() throws DataBaseException,ItemNotFoundException,SharedApplicationException {

        if (isCopyFlage() == true) {
            setMaintainMode(0);
        }
        String finishNavigationCase = super.finish();
        if (finishNavigationCase != null) {
            if (!isShowErrorMsg()) {
                RegulationCycleMaintainBean maintainBean = 
                    (RegulationCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{regulationCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
                FacesContext ctx = FacesContext.getCurrentInstance();
                Application app = ctx.getApplication();
                if (maintainBean.getCurrentStage() == 1) {
                    RegulationsSuggestionListBean regulationListBean = 
                        (RegulationsSuggestionListBean)app.createValueBinding("#{regulationsSuggestionListBean}").getValue(ctx);
                    regulationListBean.setSearchMode(false);
                    regulationListBean.getPageIndex((String)getPageDTO().getCode().getKey());
                    regulationListBean.getHighlightedDTOS().add(getPageDTO());
                    highlighDataTable("regulationsSuggestionListBean");
                    if (isCopyFlage() == true) {
                        regulationListBean.setSelectedRegTypeKey(4l);
                    }
                }
                if (maintainBean.getCurrentStage() == 2) {
                    RegulationsRevisionListBean regulationListBean = 
                        (RegulationsRevisionListBean)app.createValueBinding("#{regulationsRevisionListBean}").getValue(ctx);
                    if (!regulationListBean.isShowAllFlag()) {
                        regulationListBean.setSearchMode(true);
                        regulationListBean.getRegulationsSearchDTO().setCurrentStage(2L);
                        regulationListBean.getRegulationsSearchDTO().setRegStatusFlage(0L);
                        try {
                            if (regulationListBean.getRegulationsSearchDTO() != 
                                null) {
                                if (regulationListBean.getRegulationsSearchDTO().getName() != 
                                    null) {
                                    if (!(regulationListBean.getRegulationsSearchDTO().getName().equals(""))) {
                                        regulationListBean.getRegulationsSearchDTO().setName(formatSearchString(regulationListBean.getRegulationsSearchDTO().getName()));
                                    }
                                }

                                regulationListBean.generatePagingRequestDTO("regulationsRevisionListBean", 
                                                                            "searchWithPaging");
                                regulationListBean.setStringSearchType("false");
                                regulationListBean.setSelectedRadio("");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        regulationListBean.setSearchMode(true);
                        regulationListBean.setRegulationsSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                        regulationListBean.setStringSearchType("false");
                        regulationListBean.setSelectedRadio("");
                        regulationListBean.getGetAllDTO().setCurrentStage(2L);
                         regulationListBean.getGetAllDTO().setRegStatusFlage(0L);

                        regulationListBean.getAll();
                    }

                    regulationListBean.getPageIndex((String)getPageDTO().getCode().getKey());
                    regulationListBean.getHighlightedDTOS().add(getPageDTO());
                    highlighDataTable("regulationsRevisionListBean");

                }
                if(maintainBean.getCurrentStage() == 3){
                
                    RegulationsExcuteListBean regulationListBean = 
                        (RegulationsExcuteListBean)app.createValueBinding("#{regulationsExcuteListBean}").getValue(ctx);
                        if (!regulationListBean.isShowAllFlag()) {
                        regulationListBean.setSearchMode(true);
                        regulationListBean.getRegulationsSearchDTO().setCurrentStage(3L);
                        regulationListBean.getRegulationsSearchDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
                        regulationListBean.getRegulationsSearchDTO().setRegStatusFlage(3L);

                        try {
                            if (regulationListBean.getRegulationsSearchDTO() != 
                                null) {
                                if (regulationListBean.getRegulationsSearchDTO().getName() != 
                                    null) {
                                    if (!(regulationListBean.getRegulationsSearchDTO().getName().equals(""))) {
                                        regulationListBean.getRegulationsSearchDTO().setName(formatSearchString(regulationListBean.getRegulationsSearchDTO().getName()));
                                    }
                                }
                                if (regulationListBean.isUsingPaging()) {
                                    regulationListBean.generatePagingRequestDTO("regulationsExcuteListBean", 
                                                                                "searchWithPaging");
                                    regulationListBean.setStringSearchType("false");
                                    regulationListBean.setSelectedRadio("");
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        regulationListBean.setSearchMode(true);
                        regulationListBean.setRegulationsSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                        regulationListBean.setStringSearchType("false");
                        regulationListBean.setSelectedRadio("");
                        regulationListBean.getGetAllDTO().setCurrentStage(3L);
                        regulationListBean.getGetAllDTO().setRegStatusFlage(3L);
                         regulationListBean.getGetAllDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());

                        regulationListBean.getAll();
                    }
                    regulationListBean.getPageIndex((String)getPageDTO().getCode().getKey());
                    regulationListBean.getHighlightedDTOS().add(getPageDTO());
                    highlighDataTable("regulationsExcuteListBean");
                }


            }
            return finishNavigationCase;
        }
        return null;
    }
     
    public String back() {
        if (getIntegrationBean() != null && 
            getIntegrationBean().getModuleFrom() != null && 
            !getIntegrationBean().getModuleFrom().equals("")) {
            return getIntegrationBean().cancelGoTO();
        }
        String finishNavigationCase = super.back();
        if (finishNavigationCase != null) {
            RegulationCycleMaintainBean maintainBean = 
                (RegulationCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{regulationCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
            FacesContext ctx = FacesContext.getCurrentInstance();
            Application app = ctx.getApplication();
            if (maintainBean.getCurrentStage() == 2) {
                RegulationsRevisionListBean regulationListBean = 
                    (RegulationsRevisionListBean)app.createValueBinding("#{regulationsRevisionListBean}").getValue(ctx);
                if (!regulationListBean.isShowAllFlag()) {
                    regulationListBean.setSearchMode(true);
                    regulationListBean.getRegulationsSearchDTO().setCurrentStage(2L);
                    regulationListBean.getRegulationsSearchDTO().setRegStatusFlage(0L);
                    try {
                        if (regulationListBean.getRegulationsSearchDTO() != 
                            null) {
                            if (regulationListBean.getRegulationsSearchDTO().getName() != 
                                null) {
                                if (!(regulationListBean.getRegulationsSearchDTO().getName().equals(""))) {
                                    regulationListBean.getRegulationsSearchDTO().setName(formatSearchString(regulationListBean.getRegulationsSearchDTO().getName()));
                                }
                            }
                            regulationListBean.generatePagingRequestDTO("regulationsRevisionListBean", 
                                                                        "searchWithPaging");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    regulationListBean.setSearchMode(true);
                    regulationListBean.setRegulationsSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                    regulationListBean.getGetAllDTO().setCurrentStage(2L);
                    regulationListBean.getGetAllDTO().setRegStatusFlage(0L);

                    try {
                        regulationListBean.getAll();
                    } catch (DataBaseException e) {
                        e.printStackTrace(); // TODO
                    }
                }
                regulationListBean.resetPageIndex();
                regulationListBean.unCheck();
                if (regulationListBean.getSelectedDTOS() != null)
                    regulationListBean.getSelectedDTOS().clear();
                if (regulationListBean.getHighlightedDTOS() != null)
                    regulationListBean.getHighlightedDTOS().clear();
                regulationListBean.repositionPage(0);
                regulationListBean.setStringSearchType("false");
                regulationListBean.setSelectedRadio("");
            }
            if (maintainBean.getCurrentStage() == 3) {

                RegulationsExcuteListBean regulationListBean = 
                    (RegulationsExcuteListBean)app.createValueBinding("#{regulationsExcuteListBean}").getValue(ctx);
                if (!regulationListBean.isShowAllFlag()) {
                    regulationListBean.setSearchMode(true);
                    regulationListBean.getRegulationsSearchDTO().setCurrentStage(3L);
                    regulationListBean.getRegulationsSearchDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
                    regulationListBean.getRegulationsSearchDTO().setRegStatusFlage(3L);

                    try {
                        if (regulationListBean.getRegulationsSearchDTO() != 
                            null) {
                            if (regulationListBean.getRegulationsSearchDTO().getName() != 
                                null) {
                                if (!(regulationListBean.getRegulationsSearchDTO().getName().equals(""))) {
                                    regulationListBean.getRegulationsSearchDTO().setName(formatSearchString(regulationListBean.getRegulationsSearchDTO().getName()));
                                }
                            }
                            if (regulationListBean.isUsingPaging()) {
                                regulationListBean.generatePagingRequestDTO("regulationsExcuteListBean", 
                                                                            "searchWithPaging");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    regulationListBean.setSearchMode(true);
                    regulationListBean.setRegulationsSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                    regulationListBean.getGetAllDTO().setCurrentStage(3L);
                    regulationListBean.getGetAllDTO().setRegStatusFlage(3L);
                     regulationListBean.getGetAllDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());

                    try {
                        regulationListBean.getAll();
                    } catch (DataBaseException e) {
                        e.printStackTrace(); // TODO
                    }

                }
                regulationListBean.resetPageIndex();
                regulationListBean.unCheck();
                if (regulationListBean.getSelectedDTOS() != null)
                    regulationListBean.getSelectedDTOS().clear();
                if (regulationListBean.getHighlightedDTOS() != null)
                    regulationListBean.getHighlightedDTOS().clear();
                regulationListBean.repositionPage(0);
                regulationListBean.setStringSearchType("false");
                regulationListBean.setSelectedRadio("");
            }

            return finishNavigationCase;
        }
        return null;
    }

    public void add() throws DataBaseException {
        handleRegulationImage();
        SharedUtilBean sharedUtil = getSharedUtil();

        try {

            setPageDTO(((IRegulationsClient)getClient()).addRegulationsCycle(getPageDTO()));

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

        }catch (ColumnsHasNoRecordsException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e, 
                                       "com.beshara.csc.nl.reg.presentation.resources.reg",  "ColumnsHasNoRecordsException");
        
        } 
        catch (DataValueNoMatchColumnTypeException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e, 
                                       "com.beshara.csc.nl.reg.presentation.resources.reg", 
                                       "DataValueNoMatchColumnTypeException");
        
        } 
        catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, 
                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                       "FailureInAdd");
            sharedUtil.handleException(e);
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            //this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e);


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
        handleRegulationImage();
        SharedUtilBean sharedUtil = getSharedUtil();

        try {

            getClient().update(getPageDTO());
            cancelSearch();

            if (isUsingPaging()) {

                setUpdateMyPagedDataModel(true);
                setRepositionTable(true); 
                getPagingBean().clearDTOS();
                if (getPagingRequestDTO() == null) {
                    setPagingRequestDTO(new PagingRequestDTO()); 
                }
                getPagingRequestDTO().setRepositionKey((String)getPageDTO().getCode().getKey());

            }

            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().add(getPageDTO());
            }

            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            this.getSelectedDTOS().clear();

        }catch (ColumnsHasNoRecordsException e) {
            sharedUtil.handleException(e, 
                                       "com.beshara.csc.nl.reg.presentation.resources.reg", 
                                       "ColumnsHasNoRecordsException");
        }
        catch (DataValueNoMatchColumnTypeException e) {
           
            sharedUtil.handleException(e, 
                                       "com.beshara.csc.nl.reg.presentation.resources.reg", 
                                       "DataValueNoMatchColumnTypeException");
        
        }  catch (DataBaseException e) {
            //sharedUtil.handleException(e);
                        sharedUtil.handleException(e, 
                                                   "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                                   "FailureInUpdate");
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

    private void handleRegulationImage() {
        String tempFilePath = 
            (String)getDetailsSavedStates().get(BeansUtil.UPLOADED_IMAGE_BINDING_KEY);
        if (tempFilePath != null) {
            String imageFileName = getCurrentRegulationKey();
            try {
                String relativeFileName = 
                    BeansUtil.saveRegImage(tempFilePath, RegConfig.getInstance().getRegulationUploadedImagesPath(), 
                                           imageFileName);
                ((IRegulationsDTO)getPageDTO()).setRegulationImage(relativeFileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getCurrentRegulationKey() {
        String regulationNumber = "";
        String regulationTypeCode = "";
        String issuanceYearCode = "";

        if (getMaintainMode() == 0) { // add
            regulationNumber = 
                    ((IRegulationsDTO)getPageDTO()).getRegulationNumber().toString();
            regulationTypeCode = 
                    ((IRegulationsDTO)getPageDTO()).getTypesDto().getCode().getKey().toString();
            issuanceYearCode = 
                    ((IRegulationsDTO)getPageDTO()).getYearsDto().getCode().getKey().toString();
        } else if (getMaintainMode() == 1) { // edit
            regulationNumber = 
                    ((IRegulationsEntityKey)(getPageDTO().getCode())).getRegulationNumber().toString();
            regulationTypeCode = 
                    ((IRegulationsEntityKey)(getPageDTO().getCode())).getRegtypeCode().toString();
            issuanceYearCode = 
                    ((IRegulationsEntityKey)(getPageDTO().getCode())).getRegyearCode().toString();
        }

        return regulationNumber + "_" + regulationTypeCode + "_" + 
            issuanceYearCode;
    }

    public void setCopyRegualationWithSubject(boolean copyRegualationWithSubject) {
        this.copyRegualationWithSubject = copyRegualationWithSubject;
    }

    public boolean isCopyRegualationWithSubject() {
        return copyRegualationWithSubject;
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
        
        if(!isInitializeTablesTab() && getCurrentStep().equals("attachadd"))
            initializeTables();
        if (nCase != null)
            handleNavigation(nCase);
    }

    public String next() {
        String nextNavCase= super.next();
        if(!isInitializeTablesTab() && getCurrentStep().equals("attachadd"))
            initializeTables();
        return nextNavCase;
    }

    public String previous() {
        String previousNavCase="";
    try
    {
        previousNavCase= super.previous();
        if(!isInitializeTablesTab() && getCurrentStep().equals("attachadd"))
            initializeTables();
    }
    catch(Exception e)
    {
    e.printStackTrace();
    }
        return previousNavCase;
    }

    public void setInitializeTablesTab(boolean initializeTablesTab) {
        this.initializeTablesTab = initializeTablesTab;
    }

    public boolean isInitializeTablesTab() {
        return initializeTablesTab;
    }
    
    public void initializeTables(){
      if(getPageDTO()!=null && getPageDTO().getCode()!=null){
            try {
                ((IRegulationsDTO)getPageDTO()).setRegDedignTablesDTOList((List)RegClientFactory.getREGDedignTablesClient().getTablesByRegCode(getPageDTO().getCode()));
            } catch (Exception e) {
               e.printStackTrace();
                ((IRegulationsDTO)getPageDTO()).setRegDedignTablesDTOList(new ArrayList());
            }
           RegulationAttachmentsMaintain regulationAttachmentsMaintain =(RegulationAttachmentsMaintain)evaluateValueBinding("regulationCycleAttachmentsMaintain");
           regulationAttachmentsMaintain.setCurrentDetails((List)((IRegulationsDTO)getPageDTO()).getRegDedignTablesDTOList());
            setInitializeTablesTab(true);
        }
    }


    public void setCopyFlage(boolean copyFlage) {
        this.copyFlage = copyFlage;
    }

    public boolean isCopyFlage() {
        return copyFlage;
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

    public void setCurrentStage(Long currentStage) {
        this.currentStage = currentStage;
    }

    public Long getCurrentStage() {
        return currentStage;
    }
    public String executeRegulation() throws DataBaseException, ItemNotFoundException, 
                                  SharedApplicationException {
        String finishNavigationCase = null;
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            if (getFinishButtonOverride(getCurrentStep()) == 1) {
                setRenderFinish(getFinishButtonStatus(getCurrentStep()));
            } else if (getFinishButtonOverride(getCurrentStep()) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }

            if (validateStep(getCurrentStep(), null)) {
                //TODO locking code
                // return to the listing page in case of the item 
                // lock was removed from the locking server
                if (!insureLocked()) {

                    finishNavigationCase = getFinishNavigationCase();
                }
                handleRegulationImage();

                ((IRegulationsClient)getClient()).updateRegulationsCycle(getPageDTO());
                cancelSearch();

                if (isUsingPaging()) {

                    setUpdateMyPagedDataModel(true);
                    setRepositionTable(true);
                    getPagingBean().clearDTOS();
                    if (getPagingRequestDTO() == null) {
                        setPagingRequestDTO(new PagingRequestDTO());
                    }
                    getPagingRequestDTO().setRepositionKey((String)getPageDTO().getCode().getKey());

                }

                if (getHighlightedDTOS() != null) {
                    getHighlightedDTOS().add(getPageDTO());
                }

                sharedUtil.setSuccessMsgValue("SuccesUpdated");
                this.getSelectedDTOS().clear();


                updateStepData(getCurrentStep());

                //TODO locking code
                unlock();

                finishNavigationCase = getFinishNavigationCase();
            }

        } catch (ColumnsHasNoRecordsException e) {
            sharedUtil.handleException(e, 
                                       "com.beshara.csc.nl.reg.presentation.resources.reg", 
                                       "ColumnsHasNoRecordsException");
        } catch (DataValueNoMatchColumnTypeException e) {

            sharedUtil.handleException(e, 
                                       "com.beshara.csc.nl.reg.presentation.resources.reg", 
                                       "DataValueNoMatchColumnTypeException");

        } catch (DataBaseException e) {
            //sharedUtil.handleException(e);
            sharedUtil.handleException(e, 
                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                       "FailureInUpdate");
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
        setSelectedRadio("");
        if (finishNavigationCase != null) {
            if (!isShowErrorMsg()) {
                FacesContext ctx = FacesContext.getCurrentInstance();
                Application app = ctx.getApplication();
                RegulationsExcuteListBean regulationListBean = 
                    (RegulationsExcuteListBean)app.createValueBinding("#{regulationsExcuteListBean}").getValue(ctx);
                if (!regulationListBean.isShowAllFlag()) {
                    regulationListBean.setSearchMode(true);
                    regulationListBean.getRegulationsSearchDTO().setCurrentStage(3L);
                    regulationListBean.getRegulationsSearchDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
                    regulationListBean.getRegulationsSearchDTO().setRegStatusFlage(3L);

                    try {
                        if (regulationListBean.getRegulationsSearchDTO() != 
                            null) {
                            if (regulationListBean.getRegulationsSearchDTO().getName() != 
                                null) {
                                if (!(regulationListBean.getRegulationsSearchDTO().getName().equals(""))) {
                                    regulationListBean.getRegulationsSearchDTO().setName(formatSearchString(regulationListBean.getRegulationsSearchDTO().getName()));
                                }
                            }
                            if (regulationListBean.isUsingPaging()) {
                                regulationListBean.generatePagingRequestDTO("regulationsExcuteListBean", 
                                                                            "searchWithPaging");
                                regulationListBean.setStringSearchType("false");
                                regulationListBean.setSelectedRadio("");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    regulationListBean.setSearchMode(true);
                    regulationListBean.setRegulationsSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                    regulationListBean.setStringSearchType("false");
                    regulationListBean.setSelectedRadio("");
                    regulationListBean.getGetAllDTO().setCurrentStage(3L);
                    regulationListBean.getGetAllDTO().setRegStatusFlage(3L);
                     regulationListBean.getGetAllDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());

                    regulationListBean.getAll();
                }
                regulationListBean.getPageIndex((String)getPageDTO().getCode().getKey());
                regulationListBean.getHighlightedDTOS().add(getPageDTO());
                highlighDataTable("regulationsExcuteListBean");
            }
            return finishNavigationCase;
        }
        return null;
    }
}
