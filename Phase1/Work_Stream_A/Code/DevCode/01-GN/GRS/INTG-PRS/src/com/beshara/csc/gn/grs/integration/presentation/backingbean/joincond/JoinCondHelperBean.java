package com.beshara.csc.gn.grs.integration.presentation.backingbean.joincond;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.IConditionsClient;
import com.beshara.csc.gn.grs.business.client.ILinesClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionRelationsDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.dto.ISearchConditionsDTO;
import com.beshara.csc.gn.grs.business.entity.IConditionsEntityKey;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondTargetDTO;
import com.beshara.csc.gn.grs.integration.presentation.backingbean.conditions.ConditionDetailsBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.awt.event.ActionEvent;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public abstract class JoinCondHelperBean implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private static final String RESOURCE_BUNDLE_NAME =
        "com.beshara.csc.gn.grs.integration.presentation.resources.integration";
    private static final String MSG_KEY_JOIN_SUCCEEDED = "SuccesJoinCondition";
    private static final String MSG_KEY_CANCEL_SUCCEEDED = "SuccesCancelCondition";
    private static final String MSG_KEY_HAS_NO_ACTIVE_CONDITION = "NoActiveCondException";
    protected static final String DEF_DIV_ID = "customDiv1";
    protected static final String DEF_HELPER_NAME = "jcHelper";
    protected static final int JOIN_MODE = 1;
    protected static final int CANCEL_MODE = 2;
    protected static final int VIEW_MODE = 3;
    private int divMode = JOIN_MODE;

    private String joinConditionPath = "/integration/grs/jsps/joincond/cnt/join.jsp";
    private String activeConditionCntPath = "/integration/grs/jsps/joincond/cnt/activeConditionCnt.jsp";
    private String relatedConditionsPath = "/integration/grs/jsps/joincond/cnt/relatedConditions.jsp";
    private String relatedConditionsCnt1Path = "/integration/grs/jsps/joincond/cnt/relatedConditionsCnt1.jsp";
    private String navigationPath = "/integration/grs/jsps/joincond/cnt/navigation.jsp";

    
    private Boolean renderConditionFilter = false;
    private String changeActiveConditionAlertKey = "changeActiveConditionAlertMsg";
    private String cancelConditionAlertKey = "cancelConditionAlertMsg";


    private String joinContainerStyle = "grsIntgJoinContainerStyle";
    private String relatedConditionsCntStyle = "fullWidthScroll200";
    private String changeActiveConditionAlertStyle = "msg warning";
    private String cancelConditionAlertStyle = "msg warning";

    private boolean activeCondRelationLoaded;
    private boolean showJoinAlert;

    private boolean operationSucceeded;

    private List<IBasicDTO> linesList = null;
    private ISearchConditionsDTO searchConditionsDTO = GrsDTOFactory.createSearchConditionsDTO();
    private Long[] exculdedConditionList;
    private Long[] searchInStatusList;
    private List<IBasicDTO> myTableData;
    private String selectedConditionCode;
    private String fullURL;
    private boolean searchMode;
    private IConditionRelationsDTO activeCondRelation;

    private transient HtmlDataTable myDataTable = new HtmlDataTable();
    
    private boolean displayRelatedModules;
    public String divId = "customDiv1";

    public JoinCondHelperBean() {
    }

    protected abstract String getContainerBeanName();

    public abstract IJoinCondTargetDTO getTarget();

    protected abstract IJoinCondDTO createCondDTO();

    protected abstract void applyJoin() throws SharedApplicationException, DataBaseException;

    protected abstract void applyCancel() throws SharedApplicationException, DataBaseException;

    protected String getHelperName() {
        return DEF_HELPER_NAME;
    }

    protected boolean validForJoin() {
        return true;
    }

    protected boolean validForCancel() {
        return true;
    }

    public void resetData() {
        this.setShowJoinAlert(false);
        this.setOperationSucceeded(false);
        this.setActiveCondRelationLoaded(false);
        this.setActiveCondRelation(null);

        this.setLinesList(null);
        this.setSearchConditionsDTO(GrsDTOFactory.createSearchConditionsDTO());
        this.setExculdedConditionList(null);
        this.setSearchInStatusList(null);
        this.setMyTableData(null);
        if (this.getMyDataTable() != null) {
            this.getMyDataTable().setFirst(0);
        }

        this.setSelectedConditionCode(null);
    }

    public void scrollerAction(ActionEvent ae) {
        this.writeShowDivJScript();
    }

    protected void writeShowDivJScript() {
        if(!myTableData.isEmpty()){
            getContainerBean().setJavaScriptCaller("changeVisibilityDiv(window.blocker,window." + getDivId() +
                                                   ",'grsIntgDivBackBtn');"); 
        }else{
            getSharedUtil().handleException(null, "com.beshara.jsfbase.csc.msgresources.global", "global_noTableResults");
        }
        
    }

    public void viewRelatedConditions() {
        try {
            this.resetData();
            this.setDivMode(VIEW_MODE);
            IJoinCondTargetDTO target = this.getTarget();
            List<Long> relTabrecSerialList = new ArrayList<Long>(1);
            this.loadRelatedConditions(target.getTabrecSerial());
            
            //this.viewRelatedConditions(42811916L);
            this.writeShowDivJScript();
        } catch (Exception e) {
            this.getSharedUtil().handleException(e);
        }
    }

    public void openJoinDiv() {
        this.resetData();
        this.setDivMode(JOIN_MODE);
        if (this.validForJoin()) {
            if (this.getActiveCondCode() != null) {
                this.addToMyTableData(getActiveCondRelation());
                this.setShowJoinAlert(true);
            } else {
                this.initJoinDiv();
            }
            this.writeShowDivJScript();
        }
    }

    public void hideJoinAlert() {
        this.setShowJoinAlert(false);
        this.initJoinDiv();
    }

    public void openCancelDiv() {
        this.resetData();
        this.setDivMode(CANCEL_MODE);
        if (this.getActiveCondCode() != null) {
            if (this.validForCancel()) {
                this.addToMyTableData(getActiveCondRelation());
                this.writeShowDivJScript();
            }
        } else {
            this.getSharedUtil().handleException(null, RESOURCE_BUNDLE_NAME, MSG_KEY_HAS_NO_ACTIVE_CONDITION);
        }
    }

    protected void initJoinDiv() {
        this.fillLinesList();
        this.cancelSearch();

        this.setSearchInStatus(ISystemConstant.ACTIVE_FLAG);
        this.setExculdedConditionList(null);
        if (this.getActiveCondCode() != null) {
            this.setExculdedCondition(this.getActiveCondCode());
        }
        this.search();
        this.setSearchMode(false);
    }

    public void viewConditionDetails() {
        this.setActiveCondRelationLoaded(false);
        if (this.getActiveCondCode() != null) {
            this.constructConditionDetailsPagePath(this.getActiveCondCode().toString());
            this.getContainerBean().setJavaScriptCaller("openGrsIntgConditionDetailsWindow();");
        } else {
            this.getSharedUtil().handleException(null, RESOURCE_BUNDLE_NAME, MSG_KEY_HAS_NO_ACTIVE_CONDITION);
            this.getContainerBean().setJavaScriptCaller("changeVisibilityMsg();");
        }
    }

    public void viewConditionDetails(javax.faces.event.ActionEvent actionEvent) {
        String conditionKey = (String)actionEvent.getComponent().getAttributes().get("conditionCode");
        this.constructConditionDetailsPagePath(conditionKey);
    }

    public void constructConditionDetailsPagePath(String conditionCode) {
        fullURL = ConditionDetailsBean.constructPageURL(conditionCode, this.isDisplayRelatedModules());
        System.out.println(fullURL);
    }

    protected void pre() {

    }

    protected void join() {
        IJoinCondTargetDTO target = this.getTarget();
        if (target != null && selectedConditionCode != null) {
            SharedUtilBean sharedUtilBean = this.getSharedUtil();
            try {
                //set parameters
                IConditionsDTO conditionsDTO = (IConditionsDTO)this.getElementById(myTableData, selectedConditionCode);
                IJoinCondDTO joinCondDTO = this.createCondDTO();

                joinCondDTO.setConditionsDTO(conditionsDTO);
                joinCondDTO.setTarget(target);
                target.setJCNewCond(joinCondDTO);
                this.applyJoin();
                this.setOperationSucceeded(true);

                sharedUtilBean.handleSuccMsg(RESOURCE_BUNDLE_NAME, MSG_KEY_JOIN_SUCCEEDED);
            } catch (Exception e) {
                sharedUtilBean.setLocalBundle(RESOURCE_BUNDLE_NAME);
                sharedUtilBean.handleException(e);
            }
        }
    }

    protected void cancel() {
        IJoinCondTargetDTO target = this.getTarget();
        if (target != null) {
            SharedUtilBean sharedUtilBean = this.getSharedUtil();
            try {
                this.applyCancel();
                this.setOperationSucceeded(true);

                sharedUtilBean.handleSuccMsg(RESOURCE_BUNDLE_NAME, MSG_KEY_CANCEL_SUCCEEDED);
            } catch (Exception e) {
                sharedUtilBean.setLocalBundle(RESOURCE_BUNDLE_NAME);
                sharedUtilBean.handleException(e);
            }
        }
    }

    protected void post() {
        if (isOperationSucceeded()) {
            BaseBean bean = this.getContainerBean();
            bean.setPageDTO(this.getTarget());
            //bean.setJavaScriptCaller("reStyle();");
            StringBuilder js = new StringBuilder("showDivTreeDetails(");
            js.append("null");
            js.append(",");
            js.append(this.getDisplayedCode());
            js.append(",");
            js.append("window.divTreeDetails");
            js.append(");");
            bean.setJavaScriptCaller(js.toString());

            bean.highlighDataTable(this.getContainerBeanName());
        }
    }

    public String getDisplayedCode() {
        IJoinCondTargetDTO dto = getTarget();
        return dto == null ? null : dto.getCode().getKey();
    }

    public String getDisplayedName() {
        IJoinCondTargetDTO dto = getTarget();
        return dto == null ? null : dto.getName();
    }

    public void execute() {
        this.setOperationSucceeded(false);
        pre();
        if (this.isJoinMode()) {
            join();
        } else if (this.isCancelMode()) {
            cancel();
        }
        post();
    }

    public String search() {
        getMyDataTable().setFirst(0);
        JSFHelper.callMethod(getSearchButtonMethod());
        return null;
    }

    public void cancelSearch() {
        this.setSearchMode(false);
        this.setSearchConditionsDTO(GrsDTOFactory.createSearchConditionsDTO());
        this.setMyTableData(new ArrayList<IBasicDTO>(0));
        getSearchConditionsDTO().setRenderConditionFilter(renderConditionFilter);
        this.search();
        this.setSearchMode(false);
    }

    public void searchForConditions() {
        List<IBasicDTO> temp = new ArrayList<IBasicDTO>(0);
        try {
            IConditionsClient iConditionsClient = GrsClientFactory.getConditionsClient();
            searchConditionsDTO.setRenderConditionFilter(renderConditionFilter);
            if (exculdedConditionList != null && exculdedConditionList.length > 0) {
                searchConditionsDTO.setExceptConditionsCode(exculdedConditionList);
            } else {
                Long[] dumyExcludCondition = new Long[1];
                dumyExcludCondition[0] = getVirtualLongValue();
                searchConditionsDTO.setExceptConditionsCode(dumyExcludCondition);
            }
            searchConditionsDTO.setSearchInStatus(searchInStatusList);
            Long moduleCode = getModuleCode();
            if (moduleCode != null) {
                searchConditionsDTO.setAppModuleCode(String.valueOf(moduleCode));
            }
            temp = iConditionsClient.search(getSearchConditionsDTO());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setMyTableData(temp);
        setSearchMode(true);
    }

    private void fillLinesList() {
        try {
            ILinesClient iLinesClient = GrsClientFactory.getLinesClient();
            ISearchConditionsDTO searchLinesDTO = GrsDTOFactory.createSearchConditionsDTO();
            if (exculdedConditionList != null && exculdedConditionList.length > 0) {
                searchLinesDTO.setExceptConditionsCode(exculdedConditionList);
            } else {
                Long[] dumyExcludCondition = new Long[1];
                dumyExcludCondition[0] = ISystemConstant.VIRTUAL_VALUE;
                searchLinesDTO.setExceptConditionsCode(dumyExcludCondition);
            }
            Long moduleCode = getModuleCode();
            if (moduleCode != null) {
                searchLinesDTO.setAppModuleCode(String.valueOf(moduleCode));
                linesList = iLinesClient.getLinesByModule(searchLinesDTO);
            } else {
                linesList = iLinesClient.getCodeName();
            }
        } catch (Exception e) {
            e.printStackTrace();
            linesList = new ArrayList<IBasicDTO>(0);
        }
    }

    public void loadRelatedConditions(Long relTabrecSerial) {
        List<Long> relTabrecSerialList = new ArrayList<Long>(1);
        relTabrecSerialList.add(relTabrecSerial);
        this.loadRelatedConditions(relTabrecSerialList);
    }

    public void loadRelatedConditions(List<Long> relTabrecSerialList) {
        myTableData = new ArrayList<IBasicDTO>(0);
        if (relTabrecSerialList != null && !relTabrecSerialList.isEmpty() && relTabrecSerialList.get(0) != null) {
            try {
                myTableData =
                        GrsClientFactory.getConditionRelationsClient().getAllByRelTabrecSerial(relTabrecSerialList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected SharedUtilBean getSharedUtil() {
        return SharedUtilBean.getInstance();
    }

    public BaseBean getContainerBean() {
        return (BaseBean)JSFHelper.getValue(getContainerBeanName());
    }

    protected String getOkButtonMethod() {
        StringBuilder stringBuilder = new StringBuilder(getContainerBeanName());
        stringBuilder.append(".");
        stringBuilder.append(getHelperName());
        stringBuilder.append(".");
        stringBuilder.append("execute");
        return stringBuilder.toString();
    }

    protected String getSearchButtonMethod() {
        StringBuilder stringBuilder = new StringBuilder(getContainerBeanName());
        stringBuilder.append(".");
        stringBuilder.append(getHelperName());
        stringBuilder.append(".");
        stringBuilder.append("searchForConditions");
        return stringBuilder.toString();
    }

    public String getResetJoinButtonMethod() {
        StringBuilder stringBuilder = new StringBuilder(getContainerBeanName());
        stringBuilder.append(".");
        stringBuilder.append(getHelperName());
        stringBuilder.append(".");
        stringBuilder.append("resetJoinData");
        return stringBuilder.toString();
    }

    public Long getModuleCode() {
        return CSCSecurityInfoHelper.getModuleCode();
    }

    public void resetJoinData() {

    }

    public void setShowJoinAlert(boolean showJoinAlert) {
        this.showJoinAlert = showJoinAlert;
    }

    public boolean isShowJoinAlert() {
        return showJoinAlert;
    }

    public void setActiveCondRelation(IConditionRelationsDTO activeCondRelation) {
        this.activeCondRelation = activeCondRelation;
    }

    public IConditionRelationsDTO getActiveCondRelation() {
        if (!this.isActiveCondRelationLoaded()) {
            activeCondRelation = null;
            try {
                activeCondRelation =
                        (IConditionRelationsDTO)GrsClientFactory.getConditionRelationsClient().getCurrentActiveByRelTabrecSerial(getTarget().getTabrecSerial());
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.setActiveCondRelationLoaded(true);
        }
        return activeCondRelation;
    }

    protected Long getActiveCondCode() {
        if (getActiveCondRelation() != null && getActiveCondRelation().getCode() != null) {
            return ((IConditionsEntityKey)activeCondRelation.getConditionsDTO().getCode()).getConditionCode();
        }
        return null;
    }


    public Long getVirtualLongValue() {
        return ISystemConstant.VIRTUAL_VALUE;
    }

    protected IBasicDTO getElementById(List<IBasicDTO> dataList, String id) {
        for (IBasicDTO iBasicDTO : dataList) {
            if (iBasicDTO.getCode().getKey().equals(id)) {
                return iBasicDTO;
            }
        }
        return null;
    }

    public void setActiveCondRelationLoaded(boolean activeCondRelationLoaded) {
        this.activeCondRelationLoaded = activeCondRelationLoaded;
    }

    public boolean isActiveCondRelationLoaded() {
        return activeCondRelationLoaded;
    }

    public void setOperationSucceeded(boolean operationSucceeded) {
        this.operationSucceeded = operationSucceeded;
    }

    public boolean isOperationSucceeded() {
        return operationSucceeded;
    }

    public void setRelatedConditionsCnt1Path(String relatedConditionsCnt1Path) {
        this.relatedConditionsCnt1Path = relatedConditionsCnt1Path;
    }

    public String getRelatedConditionsCnt1Path() {
        return relatedConditionsCnt1Path;
    }

    public void setRelatedConditionsCntStyle(String relatedConditionsCntStyle) {
        this.relatedConditionsCntStyle = relatedConditionsCntStyle;
    }

    public String getRelatedConditionsCntStyle() {
        return relatedConditionsCntStyle;
    }

    public void setDivMode(int divMode) {
        this.divMode = divMode;
    }

    public int getDivMode() {
        return divMode;
    }

    public boolean isJoinMode() {
        return divMode == JOIN_MODE;
    }

    public boolean isCancelMode() {
        return divMode == CANCEL_MODE;
    }

    public boolean isViewMode() {
        return divMode == VIEW_MODE;
    }

    public void setJoinConditionPath(String joinConditionPath) {
        this.joinConditionPath = joinConditionPath;
    }

    public String getJoinConditionPath() {
        return joinConditionPath;
    }

    public void setActiveConditionCntPath(String activeConditionCntPath) {
        this.activeConditionCntPath = activeConditionCntPath;
    }

    public String getActiveConditionCntPath() {
        return activeConditionCntPath;
    }

    public void setNavigationPath(String navigationPath) {
        this.navigationPath = navigationPath;
    }

    public String getNavigationPath() {
        return navigationPath;
    }

    public void setRelatedConditionsPath(String relatedConditionsPath) {
        this.relatedConditionsPath = relatedConditionsPath;
    }

    public String getRelatedConditionsPath() {
        return relatedConditionsPath;
    }

    public void setChangeActiveConditionAlertKey(String changeActiveConditionAlertKey) {
        this.changeActiveConditionAlertKey = changeActiveConditionAlertKey;
    }

    public String getChangeActiveConditionAlertKey() {
        return changeActiveConditionAlertKey;
    }

    public void setCancelConditionAlertKey(String cancelConditionAlertKey) {
        this.cancelConditionAlertKey = cancelConditionAlertKey;
    }

    public String getCancelConditionAlertKey() {
        return cancelConditionAlertKey;
    }

    public void setChangeActiveConditionAlertStyle(String changeActiveConditionAlertStyle) {
        this.changeActiveConditionAlertStyle = changeActiveConditionAlertStyle;
    }

    public String getChangeActiveConditionAlertStyle() {
        return changeActiveConditionAlertStyle;
    }

    public void setCancelConditionAlertStyle(String cancelConditionAlertStyle) {
        this.cancelConditionAlertStyle = cancelConditionAlertStyle;
    }

    public String getCancelConditionAlertStyle() {
        return cancelConditionAlertStyle;
    }

    public void setJoinContainerStyle(String joinContainerStyle) {
        this.joinContainerStyle = joinContainerStyle;
    }

    public String getJoinContainerStyle() {
        return joinContainerStyle;
    }

    public void setLinesList(List<IBasicDTO> linesList) {
        this.linesList = linesList;
    }

    public List<IBasicDTO> getLinesList() {
        return linesList;
    }

    public void setSearchConditionsDTO(ISearchConditionsDTO searchConditionsDTO) {
        this.searchConditionsDTO = searchConditionsDTO;
    }

    public ISearchConditionsDTO getSearchConditionsDTO() {
        return searchConditionsDTO;
    }

    public void setExculdedConditionList(Long[] exculdedConditionList) {
        this.exculdedConditionList = exculdedConditionList;
    }

    public Long[] getExculdedConditionList() {
        return exculdedConditionList;
    }

    public void setExculdedCondition(Long exculdedConditionCode) {
        if (exculdedConditionList == null) {
            exculdedConditionList = new Long[1];
        }
        this.exculdedConditionList[0] = exculdedConditionCode;
    }

    public void setSearchInStatusList(Long[] searchInStatusList) {
        this.searchInStatusList = searchInStatusList;
    }

    public Long[] getSearchInStatusList() {
        return searchInStatusList;
    }

    public void setSearchInStatus(Long status) {
        if (searchInStatusList == null) {
            searchInStatusList = new Long[1];
        }
        this.searchInStatusList[0] = status;
    }

    public void addToMyTableData(IBasicDTO iBasicDTO) {
        if (myTableData == null) {
            myTableData = new ArrayList<IBasicDTO>(1);
        }
        this.myTableData.add(iBasicDTO);
    }

    public void setFullURL(String fullURL) {
        this.fullURL = fullURL;
    }

    public String getFullURL() {
        return fullURL;
    }

    public void setSelectedConditionCode(String selectedConditionCode) {
        this.selectedConditionCode = selectedConditionCode;
    }

    public String getSelectedConditionCode() {
        return selectedConditionCode;
    }

    public void setMyDataTable(HtmlDataTable myDataTable) {
        this.myDataTable = myDataTable;
    }

    public HtmlDataTable getMyDataTable() {
        return myDataTable;
    }

    public void setMyTableData(List<IBasicDTO> myTableData) {
        this.myTableData = myTableData;
    }

    public List<IBasicDTO> getMyTableData() {
        return myTableData;
    }

    public int getListSize() {
        return myTableData == null ? 0 : myTableData.size();
    }

    public void setSearchMode(boolean searchMode) {
        this.searchMode = searchMode;
    }

    public boolean isSearchMode() {
        return searchMode;
    }

    public void setDisplayRelatedModules(boolean displayRelatedModules) {
        this.displayRelatedModules = displayRelatedModules;
    }

    public boolean isDisplayRelatedModules() {
        return displayRelatedModules;
    }

    public void setDivId(String divId) {
        this.divId = divId;
    }

    public String getDivId() {
        return divId;
    }

    public void setRenderConditionFilter(Boolean renderConditionFilter) {
        this.renderConditionFilter = renderConditionFilter;
}

    public Boolean getRenderConditionFilter() {
        return renderConditionFilter;
    }
}
