package com.beshara.csc.gn.grs.integration.presentation.backingbean.calculations;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.ICalculationsClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.ICalculationRelationsDTO;
import com.beshara.csc.gn.grs.business.dto.ICalculationsDTO;
import com.beshara.csc.gn.grs.business.shared.exception.ConditionHasActiveOperationsException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.wizardbar.WizardStep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;


public class CalculationsMaintainBean extends ManyToManyMaintainBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    protected static final String BEAN_NAME = "calculationsMaintainBean";
    protected static final String BUNDLE_NAME = "com.beshara.csc.gn.grs.integration.presentation.resources.integration";
    protected static final String MAIN_DATA_MAINTAIN_BEAN_NAME = "calculationsMainDataMaintainBean";
    public static final String PAGE_NAVIGATION_CASE = "calculation_Main_Data";
    private boolean hasActiveOperations;
    private List<IBasicDTO> activeOperationsList;
    private List<IBasicDTO> availableOperationsList;
    private List<IBasicDTO> toBeJoinedOperationsList;
    private List<String> toBeAddedOperationsList;
    private List<String> toBeRemovedOperationsList;
    private boolean calculationErrMsg;
    private boolean compareMinMax;
    private Object listBeanObject;
    private HashMap hmObjects = new HashMap();
    private String beanName;
    private String backAction;
    private String navigationCase;
    private String nextNavigationCase;
    private boolean renderFinish = true;
    private String currentStep;
    private String previousNavigationCase;
    
    private String backNavCase;
    private String backMethod;
    private Object savedDataObj;
    private Long tabricSerialRef;
    private Long elmGuideCode;
    private Boolean hideAppModulesList=false;  
    private Boolean joinedBefore=false;  
    private String tableName;
    private String newCalcName;
    
    private boolean checkBenefitRewardCondition = false;
    private Long loggedInMinistry = null;
    private Long benefitRewardConditionCode = null;
    private Long BenefitRewardCode = null;
    public CalculationsMaintainBean() {
        setClient((ICalculationsClient)GrsClientFactory.getCalculationsClient());
        setCurrentStep("calculationMainDataMaintain"); //map key for first step(exist in wizard bar)
        setNextNavigationCase("calculationDetailsMaintain"); //map key for second step(exist in wizard bar)
        setFinishNavigationCase("condition_list"); //navigation case exist in faces config
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowCustomDiv2(Boolean.TRUE);
        return app;
    }

    public static CalculationsMaintainBean getInstance() {
        return (CalculationsMaintainBean)JSFHelper.getValue(BEAN_NAME);
    }
    public void initPage(Long selectedElementCode, Long tabricSerialRef, String backNavCase, String backMethod) {
        this.elmGuideCode=selectedElementCode;
        this.tabricSerialRef=tabricSerialRef;
        this.backNavCase = backNavCase;
        this.backMethod = backMethod;        
    }
    public String finish() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        CalculationsDetailBean calculationsDetailBean = CalculationsDetailBean.getInstance();
        if(!calculationsDetailBean.validate()){
            return null;
        }
        // check  CalcAppModulesList
        int calcAppModulesListSize=((ICalculationsDTO)getPageDTO()).getConditionAppModulesList().size();
        if(calcAppModulesListSize>0){
        return finishCalculation();
        }else{
            this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv2);");
            return null;
        }
    }
    public String finishCalculation() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
          return defaultAddCalculation();
    }

    public String next() {
     
        if (getPageDTO().getName() == null || getPageDTO().getName().equals("")) {
            setCalculationErrMsg(true);
            return null;
        }else{
            try {
                ICalculationsDTO calculationsDTO = (ICalculationsDTO)GrsClientFactory.getCalculationsClient().getByName(getPageDTO().getName());
                if(calculationsDTO !=null){
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "calcNameAlreadyExist"));
                    return null;
                }
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
        }
        if(((ICalculationsDTO)getPageDTO()).getMinValue()!=null && ((ICalculationsDTO)getPageDTO()).getMaxValue()!=null){
        if (((ICalculationsDTO)getPageDTO()).getMinValue() > ((ICalculationsDTO)getPageDTO()).getMaxValue()) {
            setCompareMinMax(true);
            return null;
        }
        }
        CalculationsMainDataMaintainBean mainDataMaintain= (CalculationsMainDataMaintainBean)JSFHelper.getValue(MAIN_DATA_MAINTAIN_BEAN_NAME);
        if(!hideAppModulesList){
            ((ICalculationsDTO)getPageDTO()).setConditionAppModulesList( mainDataMaintain.getAllAvailableModulesList());
        }
        
        return "calculation_Details";
    }
    
    public boolean checkStepRelevants(String currentStep) {
        Map wizardSteps = getWizardBar().getWizardSteps();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();
        WizardStep step = (WizardStep)wizardSteps.get(currentStep);
        if (step != null)
            while (it.hasNext()) {
                String key = (String)it.next();
                if (isDependant(currentStep, key)) {
                    if (((Boolean)step.getDependancyMap().get(key)) == false) {
                        return false;
                    }
                }
            }
        return true;
    }
    public void updateStepDependancies(String currentStep) {
        Map wizardSteps = getWizardBar().getWizardSteps();
        Set mapKeys = wizardSteps.keySet();
        Iterator it = mapKeys.iterator();
        while (it.hasNext()) {
            String key = (String)it.next();
            WizardStep step = (WizardStep)wizardSteps.get(key);
            if (isDependant(key, currentStep)) {
                step.getDependancyMap().put(currentStep, 
                                            validateStep(currentStep, key));
            }
        }
    }
    
     public boolean validateStep(String stepKey, String targetStep) {

         boolean valid = true;
         String detailBeanName = getStepBeanName(stepKey);

//             Boolean isValid =
//                 (Boolean)validateMethod.invoke(FacesContext.getCurrentInstance(),
//                                               null);
     //        if (isValid != null)
     //            valid = valid && isValid.booleanValue();
     //
     //
     //        // }

         if (isDependant(targetStep, stepKey)) {

             MethodBinding methodBinding = 
                 FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                        detailBeanName + 
                                                                                        ".validateTarget" + 
                                                                                        "}", 
                                                                                        new Class[] { String.class });

             Boolean isValid2 = 
                 (Boolean)methodBinding.invoke(FacesContext.getCurrentInstance(), 
                                               new Object[] { targetStep });
             valid = valid && isValid2.booleanValue();

         }
         return valid;
     }
    
     public boolean isDependant(String targetStep, String currentStep) {
         boolean dependant = false;
         if (targetStep != null) {
             List<String> relevantSteps = 
                 getWizardStep(targetStep).getRelevantSteps();
             for (String relevant: relevantSteps) {
                 if (relevant.equalsIgnoreCase(currentStep)) {
                     dependant = true;
                 }
             }
         }
         return dependant;
     }

    //*********************************************end************************************

    public String defaultAddCalculation() throws DataBaseException, ItemNotFoundException, SharedApplicationException {       
        /*
         * add CalculationClient Operation
         */
        SharedUtilBean sharedUtilBean = getSharedUtil();
        try {
            setPageDTO(getClient().add(getPageDTO()));
            boolean successJoin=false;
            if(tabricSerialRef!=null) // means join required
            {
                successJoin=join(); 
                if(successJoin)
                    sharedUtilBean.setSuccessMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "SuccesAddJoinCalculation"));  
            }else {
                    sharedUtilBean.setSuccessMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "SuccesJoinCondition"));  
            }
            
         
        } catch (Exception e) {
            sharedUtilBean.setLocalBundle(BUNDLE_NAME);
            sharedUtilBean.handleException(e);
        }
        return back();
    }
   
  
    public boolean join() {
        ICalculationRelationsDTO calcRelDTO = GrsDTOFactory.createCalculationRelationsDTO();
        calcRelDTO.setFromDate(SharedUtils.getCurrentDate());
        calcRelDTO.setStatus(ISystemConstant.ACTIVE_FLAG);
        calcRelDTO.setRTabrecSerial(tabricSerialRef);
        calcRelDTO.setTableName(tableName);
        if (getPageDTO() != null)
            calcRelDTO.setCalculationsDTO(getPageDTO());
        
            SharedUtilBean sharedUtilBean = getSharedUtil();
            try {
                applyJoin(calcRelDTO);
                return true;
                //sharedUtilBean.handleSuccMsg("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
               //                              "SuccesJoinCondition");
            } catch (Exception e) {              
                sharedUtilBean.setLocalBundle(BUNDLE_NAME);
                sharedUtilBean.handleException(e); 
                return false;
            }
    }

//    public void applyJoin(ICalculationRelationsDTO calcRelDTO) throws SharedApplicationException, DataBaseException {
//        boolean fixedValue = false;
//        GrsClientFactory.getCalculationRelationsClient().joinCalculation(calcRelDTO, fixedValue, null, null,joinedBefore);
//       
//    }
    public void applyJoin(ICalculationRelationsDTO calcRelDTO) throws SharedApplicationException, DataBaseException {
        boolean fixedValue = false;    
        System.out.println("benefitRewardConditionCode = "+benefitRewardConditionCode+"*********** BenefitRewardCode = "+BenefitRewardCode);
        GrsClientFactory.getCalculationRelationsClient().joinBenefitsOrRewardsWithCalcMethod(calcRelDTO, fixedValue, newCalcName, null,
                                                                         joinedBefore, checkBenefitRewardCondition, loggedInMinistry, benefitRewardConditionCode, BenefitRewardCode);
    }
    public void resetConditionActivationValues() {
          checkBenefitRewardCondition = false;
          loggedInMinistry = null;
          benefitRewardConditionCode = null;
          BenefitRewardCode = null;
    }
    
    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            setPageDTO(executeEdit());
            hasActiveOperations = false;
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
        } catch (ConditionHasActiveOperationsException e) {
            hasActiveOperations = true;
            System.err.println(e.getMessage());
        } catch (DataBaseException e) {
            hasActiveOperations = false;
            sharedUtil.handleException(e);
        } catch (ItemNotFoundException item) {
            hasActiveOperations = false;
            sharedUtil.handleException(item);
        } catch (SharedApplicationException e) {
            hasActiveOperations = false;
            sharedUtil.handleException(e);
        } catch (Exception e) {
            hasActiveOperations = false;
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
        }
        setSelectedRadio("");
    }

    protected IBasicDTO executeEdit() throws DataBaseException, SharedApplicationException {
        IBasicDTO conditionsDTO = getPageDTO();
        if (hasActiveOperations) {
            conditionsDTO =
                    GrsClientFactory.getConditionsClient().updateCondition(conditionsDTO, toBeJoinedOperationsList);
        } else {
            try {
                conditionsDTO = GrsClientFactory.getConditionsClient().updateCondition(conditionsDTO);
            } catch (ConditionHasActiveOperationsException e) {
                throw e;
            }
        }
        return conditionsDTO;
    }

//    private void restoreListBean() {
//        JSFHelper.setValue(CalculationsListBean.BEAN_NAME, listBeanObject);
//        CalculationsListBean.getInstance().unCheck();
//    }

    public String back() {
        if (backMethod != null && !backMethod.equals("")) {
            try {
                Object[] objects = new Object[2];
                objects[0] = elmGuideCode;
                objects[1] = savedDataObj;

                Class[] classes = new Class[2];
                classes[0] = Long.class;
                classes[1] = Object.class;
                executeMethodBindingWithParams(backMethod, objects, classes);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
        return backNavCase;
    } 
    public void addAllElements() {
        List<IBasicDTO> conditionAppModulesDTOList = this.getToBeJoinedOperationsList();
        for (IBasicDTO basicDTO : availableOperationsList) {
            conditionAppModulesDTOList.add(basicDTO);
        }
        this.toBeAddedOperationsList.clear();
        this.toBeRemovedOperationsList.clear();
        reFillDestinationList();
        this.setAvailableOperationsList(null);
    }

    public void addSelectedElements() {
        List<IBasicDTO> conditionAppModulesDTOList = this.getToBeJoinedOperationsList();
        IBasicDTO basicDTO = null;
        for (String key : toBeAddedOperationsList) {
            basicDTO = this.getElementByKey(availableOperationsList, key);
            conditionAppModulesDTOList.add(basicDTO);
        }
        this.toBeAddedOperationsList.clear();
        this.toBeRemovedOperationsList.clear();
        reFillDestinationList();
        this.setAvailableOperationsList(null);
    }

    public void removeSelectedElements() {
        List<IBasicDTO> conditionAppModulesDTOList = this.getToBeJoinedOperationsList();
        IBasicDTO basicDTO = null;
        for (String key : toBeRemovedOperationsList) {
            basicDTO = this.getElementByKey(conditionAppModulesDTOList, key);
            conditionAppModulesDTOList.remove(basicDTO);
        }

        this.toBeAddedOperationsList.clear();
        this.toBeRemovedOperationsList.clear();
        this.setAvailableOperationsList(null);
    }

    public void removeAllElements() {
        List<IBasicDTO> conditionAppModulesDTOList = this.getToBeJoinedOperationsList();
        conditionAppModulesDTOList.clear();
        this.toBeAddedOperationsList.clear();
        this.toBeRemovedOperationsList.clear();
        this.setAvailableOperationsList(null);
    }

    public void setActiveOperationsList(List<IBasicDTO> activeOperationsList) {
        this.activeOperationsList = activeOperationsList;
    }

    public List<IBasicDTO> getActiveOperationsList() {
        return activeOperationsList;
    }

    public void setToBeJoinedOperationsList(List<IBasicDTO> toBeJoinedOperationsList) {
        this.toBeJoinedOperationsList = toBeJoinedOperationsList;
    }

    public List<IBasicDTO> getToBeJoinedOperationsList() {
        return toBeJoinedOperationsList;
    }

    public void setToBeAddedOperationsList(List<String> toBeAddedOperationsList) {
        this.toBeAddedOperationsList = toBeAddedOperationsList;
    }

    public List<String> getToBeAddedOperationsList() {
        return toBeAddedOperationsList;
    }

    public void setToBeRemovedOperationsList(List<String> toBeRemovedOperationsList) {
        this.toBeRemovedOperationsList = toBeRemovedOperationsList;
    }

    public List<String> getToBeRemovedOperationsList() {
        return toBeRemovedOperationsList;
    }

    public int getAvailableOperationsListSize() {
        if (availableOperationsList != null) {
            return availableOperationsList.size();
        }
        return 0;
    }

    public int getToBeJoinedOperationsListSize() {
        if (toBeJoinedOperationsList != null) {
            return toBeJoinedOperationsList.size();
        }
        return 0;
    }

    public void setAvailableOperationsList(List<IBasicDTO> availableOperationsList) {
        this.availableOperationsList = availableOperationsList;
    }

    public List<IBasicDTO> getAvailableOperationsList() {
        if (hasActiveOperations && availableOperationsList == null) {
            try {
                availableOperationsList = new ArrayList<IBasicDTO>(0);
                List<IBasicDTO> destinationDTOList = getToBeJoinedOperationsList();
                if (destinationDTOList != null && destinationDTOList.size() != 0) {
                    boolean exist = false;
                    for (IBasicDTO codeNameDTO : getActiveOperationsList()) {
                        exist = false;
                        for (IBasicDTO detailDTO : destinationDTOList) {
                            if (codeNameDTO.getCode().getKey().equals(detailDTO.getCode().getKey())) {
                                exist = true;
                                break;
                            }
                        }
                        if (!exist) {
                            availableOperationsList.add(codeNameDTO);
                        }
                    }
                } else {
                    availableOperationsList.addAll(getActiveOperationsList());
                }
            } catch (Exception e) {
                e.printStackTrace();
                availableOperationsList = new ArrayList<IBasicDTO>();
            }
        }
        return availableOperationsList;
    }

    private void reFillDestinationList() {
        try {
            List<IBasicDTO> dataList = new ArrayList<IBasicDTO>(0);
            List<IBasicDTO> conditionAppModulesDTOList = getToBeJoinedOperationsList();
            if (conditionAppModulesDTOList != null && conditionAppModulesDTOList.size() != 0) {
                for (IBasicDTO codeNameDTO : getActiveOperationsList()) {
                    for (IBasicDTO detailDTO : conditionAppModulesDTOList) {
                        if (codeNameDTO.getCode().getKey().equals(detailDTO.getCode().getKey())) {
                            dataList.add(detailDTO);
                            break;
                        }
                    }
                }
                conditionAppModulesDTOList.clear();
                conditionAppModulesDTOList.addAll(dataList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private IBasicDTO getElementByKey(List<IBasicDTO> dataList, String key) {
        for (IBasicDTO basicDTO : dataList) {
            if (basicDTO.getCode().getKey().equals(key)) {
                return basicDTO;
            }
        }
        return null;
    }

    public void setHasActiveOperations(boolean hasActiveOperations) {
        this.hasActiveOperations = hasActiveOperations;
    }

    public boolean isHasActiveOperations() {
        return hasActiveOperations;
    }

    public void setListBeanObject(Object listBeanObject) {
        this.listBeanObject = listBeanObject;
    }

    public Object getListBeanObject() {
        return listBeanObject;
    }

    public void setHmObjects(HashMap hmObjects) {
        this.hmObjects = hmObjects;
    }

    public HashMap getHmObjects() {
        return hmObjects;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBackAction(String backAction) {
        this.backAction = backAction;
    }

    public String getBackAction() {
        return backAction;
    }


    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }

    public String getNavigationCase() {
        return navigationCase;
    }
    
    
    //***********************sakr*********************
    public void setRenderFinish(boolean renderFinish) {
        this.renderFinish = renderFinish;
    }

    public boolean isRenderFinish() {
        return renderFinish;
    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    public String getCurrentStep() {
        return currentStep;
    }

    public void setNextNavigationCase(String nextNavigationCase) {
        this.nextNavigationCase = nextNavigationCase;
    }

    public String getNextNavigationCase() {
        return nextNavigationCase;
    }

    public void setPreviousNavigationCase(String previousNavigationCase) {
        this.previousNavigationCase = previousNavigationCase;
    }

    public String getPreviousNavigationCase() {
        return previousNavigationCase;
    }
    //***********************end***********************

    public void setCalculationErrMsg(boolean calculationErrMsg) {
        this.calculationErrMsg = calculationErrMsg;
    }

    public boolean isCalculationErrMsg() {
        return calculationErrMsg;
    }


    public void setCompareMinMax(boolean compareMinMax) {
        this.compareMinMax = compareMinMax;
    }

    public boolean isCompareMinMax() {
        return compareMinMax;
    }

    public void setBackNavCase(String backNavCase) {
        this.backNavCase = backNavCase;
    }

    public String getBackNavCase() {
        return backNavCase;
    }

    public void setBackMethod(String backMethod) {
        this.backMethod = backMethod;
    }

    public String getBackMethod() {
        return backMethod;
    }

    public void setSavedDataObj(Object savedDataObj) {
        this.savedDataObj = savedDataObj;
    }

    public Object getSavedDataObj() {
        return savedDataObj;
    }

    public void setTabricSerialRef(Long tabricSerialRef) {
        this.tabricSerialRef = tabricSerialRef;
    }

    public Long getTabricSerialRef() {
        return tabricSerialRef;
    }

    public void setElmGuideCode(Long elmGuideCode) {
        this.elmGuideCode = elmGuideCode;
    }

    public Long getElmGuideCode() {
        return elmGuideCode;
    }

    public void setHideAppModulesList(Boolean hideAppModulesList) {
        this.hideAppModulesList = hideAppModulesList;
    }

    public Boolean getHideAppModulesList() {
        return hideAppModulesList;
    }

    public void setJoinedBefore(Boolean joinedBefore) {
        this.joinedBefore = joinedBefore;
    }

    public Boolean getJoinedBefore() {
        return joinedBefore;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setCheckBenefitRewardCondition(boolean checkBenefitRewardCondition) {
        this.checkBenefitRewardCondition = checkBenefitRewardCondition;
    }

    public boolean isCheckBenefitRewardCondition() {
        return checkBenefitRewardCondition;
    }

    public void setLoggedInMinistry(Long loggedInMinistry) {
        this.loggedInMinistry = loggedInMinistry;
    }

    public Long getLoggedInMinistry() {
        return loggedInMinistry;
    }

    public void setBenefitRewardConditionCode(Long benefitRewardConditionCode) {
        this.benefitRewardConditionCode = benefitRewardConditionCode;
    }

    public Long getBenefitRewardConditionCode() {
        return benefitRewardConditionCode;
    }

    public void setBenefitRewardCode(Long BenefitRewardCode) {
        this.BenefitRewardCode = BenefitRewardCode;
    }

    public Long getBenefitRewardCode() {
        return BenefitRewardCode;
    }

    public void setNewCalcName(String newCalcName) {
        this.newCalcName = newCalcName;
    }

    public String getNewCalcName() {
        return newCalcName;
    }
}
