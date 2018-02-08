package com.beshara.csc.gn.grs.integration.presentation.backingbean.addconditions;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.security.SecurityInfoHelper;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.IConditionsClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionAppModulesDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.dto.sec.IGrsSecApplicationModulesDTO;
import com.beshara.csc.gn.grs.business.entity.IConditionAppModulesEntityKey;
import com.beshara.csc.gn.grs.business.entity.IConditionsEntityKey;
import com.beshara.csc.gn.grs.business.entity.sec.IGrsSecApplicationModulesEntityKey;
import com.beshara.csc.gn.grs.business.shared.exception.ConditionHasActiveOperationsException;
import com.beshara.csc.gn.grs.integration.business.joincond.ITargetForJoinConditionDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.TargetForJoinConditionDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class ConditionsMaintainBean extends ManyToManyMaintainBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    protected static final String BEAN_NAME = "conditionsIntgMaintainBean";
    protected static final String BUNDLE_NAME =
        "com.beshara.csc.gn.grs.integration.presentation.resources.integration";

    private boolean hasActiveOperations;
    private List<IBasicDTO> activeOperationsList;
    private List<IBasicDTO> availableOperationsList;
    private List<IBasicDTO> toBeJoinedOperationsList;
    private List<String> toBeAddedOperationsList;
    private List<String> toBeRemovedOperationsList;

    private Object listBeanObject;
    private HashMap hmObjects = new HashMap();
    private String beanName;
    private String backAction;
    private String navigationCase;

    private ITargetForJoinConditionDTO targetForJoinConditionDTO;
    private boolean allowMultiCondition;
    private String transactionName;
    private String restoreMethod;

    private Long objectCode;

    public ConditionsMaintainBean() {
        setClient((IConditionsClient)GrsClientFactory.getConditionsClient());
        setCurrentStep("conditionMainData"); //map key for first step(exist in wizard bar)
        setNextNavigationCase("conditionLines"); //map key for second step(exist in wizard bar)
        setFinishNavigationCase("benefitlist"); //navigation case exist in faces config
    }

    public static ConditionsMaintainBean getInstance() {
        return (ConditionsMaintainBean)JSFHelper.getValue(BEAN_NAME);
    }

    public String finish() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        ConditionLineDetailBean conditionLineDetailBean = ConditionLineDetailBean.getInstance();
        if (conditionLineDetailBean.getCurrentListSize() != 0) {
            if (getIntegrationBean() != null && getIntegrationBean().getModuleFrom() != null &&
                !getIntegrationBean().getModuleFrom().equals("")) {
                getIntegrationBean().getSelectedDTOTo().add(getPageDTO());
                return getIntegrationBean().goFrom();
            } else {
                return defaultAddCondition();
            }
        } else {
            getSharedUtil().setErrMsgValue(getSharedUtil().getResourceBundle(BUNDLE_NAME).getString("global_noLinesAdded"));
        }
        return null;
    }

    public String defaultAddCondition() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        addCurrentAppModuleToCondition();
        String defultFinish = super.finish();
        if (defultFinish != null && !isShowErrorMsg()) {
            getTargetForJoinConditionDTO().setConditionsDTO((IConditionsDTO)getPageDTO());
            join();
            this.restoreListBean();
        } else {
            defultFinish = null;
        }

        return defultFinish;
    }

    public void join() {
        ITargetForJoinConditionDTO target = getTargetForJoinConditionDTO();
        if (target != null) {
            SharedUtilBean sharedUtilBean = getSharedUtil();
            try {
                GrsClientFactory.getConditionRelationsClient().joinCondition(getTargetForJoinConditionDTO(),
                                                                             getTransactionName(),
                                                                             allowMultiCondition);

                sharedUtilBean.handleSuccMsg("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                             "SuccesJoinCondition");
            } catch (Exception e) {
                sharedUtilBean.setLocalBundle("com.beshara.csc.gn.grs.integration.presentation.resources.integration");
                sharedUtilBean.handleException(e);
            }
        }
    }

    public void addCurrentAppModuleToCondition() {

        IGrsSecApplicationModulesDTO grsSecApplicationModulesDTO =
            GrsDTOFactory.createGrsSecApplicationModulesDTO(SecurityInfoHelper.getModuleCode(), "");
        Long conditionCode = 20984L;
        IConditionsDTO mainPageDTO = (IConditionsDTO)getPageDTO();
        if (mainPageDTO != null && mainPageDTO.getCode() != null) {
            conditionCode = ((IConditionsEntityKey)mainPageDTO.getCode()).getConditionCode();
        }
        Long appModuleCode =
            ((IGrsSecApplicationModulesEntityKey)grsSecApplicationModulesDTO.getCode()).getAppModuleCode();
        IConditionAppModulesDTO dto = GrsDTOFactory.createConditionAppModulesDTO(conditionCode, appModuleCode);
        dto.setAppModulesDTO(grsSecApplicationModulesDTO);

        if (mainPageDTO.getConditionAppModulesDTOList() == null) {
            mainPageDTO.setConditionAppModulesDTOList(new ArrayList());

        }
        boolean isCondAppModuleExist = false;
        IConditionAppModulesDTO oldConditionAppModulesDTO = null;
        List list = mainPageDTO.getConditionAppModulesDTOList();
        IConditionAppModulesEntityKey key1 = null, key2 = ((IConditionAppModulesEntityKey)dto.getCode());
        for (int i = 0; i < list.size(); i++) {
            oldConditionAppModulesDTO = (IConditionAppModulesDTO)list.get(i);
            key1 = ((IConditionAppModulesEntityKey)oldConditionAppModulesDTO.getCode());
            if (key1.getConditionCode().equals(key2.getConditionCode()) &&
                key1.getAppModuleCode().equals(key2.getAppModuleCode()) ){
                isCondAppModuleExist = true;
                break;
                }
        }

        if(!isCondAppModuleExist)mainPageDTO.getConditionAppModulesDTOList().add(dto);


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

    private void restoreListBean() {
        if (restoreMethod != null && !restoreMethod.equals("")) {
            try {
                Object[] objects = new Object[2];
                objects[0] = objectCode;
                objects[1] = hmObjects;

                Class[] classes = new Class[2];
                classes[0] = Long.class;
                classes[1] = Object.class;
                executeMethodBindingWithParams(restoreMethod, objects, classes);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }

    public String back() {
        if (getIntegrationBean() != null && getIntegrationBean().getModuleFrom() != null &&
            !getIntegrationBean().getModuleFrom().equals("")) {
            return getIntegrationBean().cancelGoTO();
        } else
        // to add back method in my bean (ex.GrsExceptionsHelperBean) to call getSaveState method
        if ((getBeanName() != null && !getBeanName().equals("")) &&
            (getBackAction() != null && !getBackAction().equals(""))) {
            return (String)JSFHelper.callMethod(getBeanName() + "." + getBackAction());
        }
        this.restoreListBean();
        return super.back();
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

    public void setTargetForJoinConditionDTO(ITargetForJoinConditionDTO targetForJoinConditionDTO) {
        this.targetForJoinConditionDTO = targetForJoinConditionDTO;
    }

    public ITargetForJoinConditionDTO getTargetForJoinConditionDTO() {
        return targetForJoinConditionDTO;
    }

    public void setAllowMultiCondition(boolean allowMultiCondition) {
        this.allowMultiCondition = allowMultiCondition;
    }

    public boolean isAllowMultiCondition() {
        return allowMultiCondition;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setRestoreMethod(String restoreMethod) {
        this.restoreMethod = restoreMethod;
    }

    public String getRestoreMethod() {
        return restoreMethod;
    }

    public void setObjectCode(Long objectCode) {
        this.objectCode = objectCode;
    }

    public Long getObjectCode() {
        return objectCode;
    }
}
