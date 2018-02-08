package com.beshara.csc.nl.reg.integration.presentation.backingbean.joindec;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.integration.business.joindec.IJoinDecTargetDTO;
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


public abstract class JoinDecHelperBean implements Serializable {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private static final String RESOURCE_BUNDLE_NAME =
        "com.beshara.csc.nl.reg.integration.presentation.resources.integration";
    private static final String MSG_KEY_JOIN_SUCCEEDED = "SuccesJoinDecision";
    private static final String MSG_KEY_HAS_NO_ACTIVE_DECISION = "NoActiveDecException";

    protected static final String DEF_DIV_ID = "customDiv1";
    protected static final String DEF_HELPER_NAME = "jdHelper";
    protected static final int JOIN_MODE = 1;
    protected static final int VIEW_MODE = 3;
    private int divMode = JOIN_MODE;

    private String joinDecisionPath = "/integration/reg/jsps/joindec/cnt/join.jsp";
    private String activeDecisionCntPath = "/integration/reg/jsps/joindec/cnt/activeDecisionCnt.jsp";
    private String relatedDecisionsPath = "/integration/reg/jsps/joindec/cnt/relatedDecisions.jsp";
    private String relatedDecisionsCnt1Path; // = "/integration/reg/jsps/joindec/cnt/relatedDecisionsCnt1.jsp";
    private String navigationPath = "/integration/reg/jsps/joindec/cnt/navigation.jsp";
    // A.kamal Added NEWPATH OF DECISIONDETAILS
    private String decisionDetailsPageUri =
        "/integration/reg/jsps/viewDecisionDetials/viewDecisionDetials.jsf?decisionKey=";

    private String alreadyHasDecisionAlertKey = "alreadyHasDecisionAlertMsg";

    private String joinContainerStyle = "decIntgJoinContainerStyle";
    private String relatedDecisionsCntStyle = "fullWidthScroll210";
    private String alreadyHasDecisionAlertStyle = "errorMsg msg";

    private List<IBasicDTO> typeList;
    private List<IYearsDTO> yearsList;
    private List<IBasicDTO> decSourceList;
    private List<IBasicDTO> decSubjectList;

    private IRegulationSearchDTO searchDTO = RegDTOFactory.createRegulationSearchDTO();

    private boolean showJoinAlert;
    private boolean operationSucceeded;

    private String fullURL;
    private boolean searchMode;
    private List<IBasicDTO> myTableData;
    private transient HtmlDataTable myDataTable = new HtmlDataTable();
    private String selectedDecisionCode;
    private String operationCode;
    private boolean searchInCandidate = false;

    public JoinDecHelperBean() {
    }

    protected abstract String getContainerBeanName();

    protected abstract IJoinDecTargetDTO getTarget();

    protected abstract List<IJoinDecTargetDTO> getTargetList();

    protected abstract List<Long> getEmpCivilIdList();

    protected abstract List<Long> getRelTabrecSerialList();

    protected abstract void applyJoin(IJoinDecTargetDTO target) throws DataBaseException, SharedApplicationException;

    protected String getDivId() {
        return DEF_DIV_ID;
    }

    protected String getHelperName() {
        return DEF_HELPER_NAME;
    }

    protected boolean validForJoin() {
        return true;
    }

    public void resetData() {
        this.setShowJoinAlert(false);
        this.setOperationSucceeded(false);

        this.setTypeList(null);
        this.setYearsList(null);
        this.setDecSourceList(null);
        this.setDecSubjectList(null);
        this.setSearchDTO(RegDTOFactory.createRegulationSearchDTO());

        this.setMyTableData(null);
        if (this.getMyDataTable() != null) {
            this.getMyDataTable().setFirst(0);
        }

        this.setSelectedDecisionCode(null);
    }

    public void scrollerAction(ActionEvent ae) {
        this.writeShowDivJScript();
    }

    protected void writeShowDivJScript() {
        getContainerBean().setJavaScriptCaller("changeVisibilityDiv(window.blocker,window." + getDivId() +
                                               ",'decIntgDivBackBtn');");
    }

    public void viewRelatedDecisions() {
        try {
            this.resetData();
            this.setDivMode(VIEW_MODE);
            this.loadRelatedDecisions(this.getRelTabrecSerialList());
            this.writeShowDivJScript();
        } catch (Exception e) {
            this.getSharedUtil().handleException(e);
        }
    }

    public void openJoinDiv() {
        this.resetData();
        this.setDivMode(JOIN_MODE);
        if (this.validForJoin()) {
            IBasicDTO decisionsDTO = getCurrentDecision();
            if (decisionsDTO != null) {
                this.addToMyTableData(decisionsDTO);
                this.setShowJoinAlert(true);
            } else {
                this.initJoinDiv();
            }
            this.writeShowDivJScript();
        }
    }

    public void openJoinDivList() {
        this.resetData();
        this.setDivMode(JOIN_MODE);
        if (this.validForJoin()) {
            List decisionsDTOList = getCurrentDecisionList();
            List<IBasicDTO> datTableList = new ArrayList<IBasicDTO>();
            for (int i = 0; i < decisionsDTOList.size(); i++) {
                datTableList.add((IBasicDTO)decisionsDTOList.get(i));
            }
            if (datTableList != null && datTableList.size() > 0) {
                this.setMyTableData(datTableList);
                this.setShowJoinAlert(true);
            } else {
                this.initJoinDiv();
            }
            this.writeShowDivJScript();
        }
    }

    protected void initJoinDiv() {
        this.fillTypeList();
        this.fillYearsList();
        this.fillDecSourceList();
        this.fillDecSubjectList();
        this.cancelSearch();

        this.search();
        this.setSearchMode(false);
    }

    public void resetJoinData() {

    }

    public void viewDecisionDetailsList() {
        List<IBasicDTO> decisionsDTOList = this.getCurrentDecisionList();
        if (decisionsDTOList != null && decisionsDTOList.size() > 0 && decisionsDTOList.get(0) != null) {
            this.constructDecisionDetailsPagePath(decisionsDTOList.get(0).getCode().getKey());
            this.getContainerBean().setJavaScriptCaller("openRegIntgDecisionDetailsWindow();");
        } else {
            this.getSharedUtil().handleException(null, RESOURCE_BUNDLE_NAME, MSG_KEY_HAS_NO_ACTIVE_DECISION);
            this.getContainerBean().setJavaScriptCaller("changeVisibilityMsg();");
        }
    }

    public void viewDecisionDetails() {
        IBasicDTO decisionsDTO = this.getCurrentDecision();
        if (decisionsDTO != null) {
            this.constructDecisionDetailsPagePath(decisionsDTO.getCode().getKey());
            this.getContainerBean().setJavaScriptCaller("openRegIntgDecisionDetailsWindow();");
        } else {
            this.getSharedUtil().handleException(null, RESOURCE_BUNDLE_NAME, MSG_KEY_HAS_NO_ACTIVE_DECISION);
            this.getContainerBean().setJavaScriptCaller("changeVisibilityMsg();");
        }
    }

    public void viewDecisionDetails(javax.faces.event.ActionEvent actionEvent) {
        String decisionKey = (String)actionEvent.getComponent().getAttributes().get("decisionKey");

        this.constructDecisionDetailsPagePath(decisionKey);
    }

    public void constructDecisionDetailsPagePath(String decisionKey) {
        fullURL = JSFHelper.viewIdToUrl(decisionDetailsPageUri + decisionKey);
        System.out.println(fullURL);
    }

    protected void pre() {

    }

    protected void preList() {

    }

    protected void join() {
        IJoinDecTargetDTO target = this.getTarget();
        if (target != null && selectedDecisionCode != null) {
            SharedUtilBean sharedUtilBean = this.getSharedUtil();
            try {
                //set parameters
                IBasicDTO decisionsDTO = this.getElementById(myTableData, selectedDecisionCode);
                target.setDecisionsDTO((IDecisionsDTO)decisionsDTO);
                this.applyJoin(target);
                this.setOperationSucceeded(true);

                sharedUtilBean.handleSuccMsg(RESOURCE_BUNDLE_NAME, MSG_KEY_JOIN_SUCCEEDED);
            } catch (Exception e) {
                sharedUtilBean.setLocalBundle(RESOURCE_BUNDLE_NAME);
                sharedUtilBean.handleException(e);
            }
        }

    }

    protected void joinList() {
        List<IJoinDecTargetDTO> targetList = this.getTargetList();
        if (targetList != null) {
            for (IJoinDecTargetDTO target : targetList) {
                if (target != null && selectedDecisionCode != null) {
                    SharedUtilBean sharedUtilBean = this.getSharedUtil();
                    try {
                        //set parameters
                        IBasicDTO decisionsDTO = this.getElementById(myTableData, selectedDecisionCode);
                        target.setDecisionsDTO((IDecisionsDTO)decisionsDTO);
                        this.applyJoin(target);
                        this.setOperationSucceeded(true);

                        sharedUtilBean.handleSuccMsg(RESOURCE_BUNDLE_NAME, MSG_KEY_JOIN_SUCCEEDED);
                    } catch (Exception e) {
                        sharedUtilBean.setLocalBundle(RESOURCE_BUNDLE_NAME);
                        sharedUtilBean.handleException(e);
                    }
                }
            }
        }
    }

    protected void post() {
        if (isOperationSucceeded()) {
            BaseBean bean = this.getContainerBean();
            //            bean.setPageDTO(this.getTarget());
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


    protected void postList() {
        if (isOperationSucceeded()) {
            BaseBean bean = this.getContainerBean();
            //            bean.setPageDTO(this.getTarget());
            //bean.setJavaScriptCaller("reStyle();");
            StringBuilder js = new StringBuilder("showDivTreeDetails(");
            js.append("null");
            js.append(",");
            js.append(this.getDisplayedCodeList());
            js.append(",");
            js.append("window.divTreeDetails");
            js.append(");");
            bean.setJavaScriptCaller(js.toString());

            bean.highlighDataTable(this.getContainerBeanName());
        }
    }

    public String getDisplayedCode() {
        IJoinDecTargetDTO dto = getTarget();
        return dto == null ? null : dto.getCode().getKey();

    }

    public List<String> getDisplayedCodeList() {
        List<IJoinDecTargetDTO> dtoList = getTargetList();
        List<String> codeList = new ArrayList<String>();
        for (IJoinDecTargetDTO dto : dtoList)
            codeList.add(dto == null ? null : dto.getCode().getKey());
        return codeList;

    }

    public String getDisplayedName() {
        IJoinDecTargetDTO dto = getTarget();
        return dto == null ? null : dto.getName();
    }

    public List<String> getDisplayedNameList() {
        List<IJoinDecTargetDTO> dtoList = getTargetList();
        List<String> nameList = new ArrayList<String>();
        for (IJoinDecTargetDTO dto : dtoList)
            nameList.add(dto == null ? null : dto.getName());
        return nameList;
    }

    public void execute() {
        this.setOperationSucceeded(false);
        pre();

        join();

        post();
    }

    public void executeList() {
        this.setOperationSucceeded(false);
        preList();

        joinList();

        postList();
    }

    public String search() {
        if (this.getMyDataTable() != null) {
            this.getMyDataTable().setFirst(0);
        }
        JSFHelper.callMethod(getSearchButtonMethod());
        return null;
    }

    public void cancelSearch() {
        this.setSearchMode(false);
        this.setSearchDTO(RegDTOFactory.createRegulationSearchDTO());
        this.setMyTableData(new ArrayList<IBasicDTO>(0));
        this.search();
        this.setSearchMode(false);
    }

    public void searchForDecisions() {
        List<IBasicDTO> temp = new ArrayList<IBasicDTO>(0);
        try {
            this.getSearchDTO().setIntegrationMode(true);
            Long modCode = CSCSecurityInfoHelper.getModuleCode();
            Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
            this.getSearchDTO().setAppModuleCode(modCode);
            this.getSearchDTO().setLoggedInMinCode(minCode);
            //System.out.println(" searchForDecisions() modCode = "+modCode + " , minCode = "+minCode+" , getOperationCode() = "+getOperationCode()+" , getEmpCivilIdList().get(0) = "+getEmpCivilIdList().get(0) );
            this.getSearchDTO().setEmpCivilIdList(this.getEmpCivilIdList());
            this.getSearchDTO().setOperationCode(this.getOperationCode());
            //            this.getSearchDTO().setTabRecordSerialRef(this.getTarget().getTabrecSerial());
            if (!searchInCandidate) {
                temp = RegClientFactory.getDecisionsClient().searchNonCancelledDecision(this.getSearchDTO());
            } else {
                temp = RegClientFactory.getDecisionsClient().searchNonCancelledDecisionForPerson(this.getSearchDTO());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setMyTableData(temp);
        setSearchMode(true);
    }

    protected void loadRelatedDecisions(List<Long> relTabrecSerialList) {
        myTableData = new ArrayList<IBasicDTO>(0);
        if (relTabrecSerialList != null && !relTabrecSerialList.isEmpty() && relTabrecSerialList.get(0) != null) {
            try {
                myTableData =
                        RegClientFactory.getDecisionsClient().getDecisionListByTabRecSerialRefList(relTabrecSerialList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public IBasicDTO getCurrentDecision() {
        if (getTarget().getTabrecSerial() != null) {
            try {
                List<IBasicDTO> descionsList =
                    RegClientFactory.getDecisionsClient().getDecisionListByTabRecSerialRef(getTarget().getTabrecSerial());
                return descionsList.get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List getCurrentDecisionList() {
        List descionsList = new ArrayList();
        List<IJoinDecTargetDTO> joinDecTargetDTOList = getTargetList();
        if (joinDecTargetDTOList != null && joinDecTargetDTOList.size() != 0) {
            for (IJoinDecTargetDTO joinDecTargetDTO : joinDecTargetDTOList) {
                if (joinDecTargetDTO.getTabrecSerial() != null) {
                    try {
                        List<IBasicDTO> desList =
                            RegClientFactory.getDecisionsClient().getDecisionListByTabRecSerialRef(joinDecTargetDTO.getTabrecSerial());
                        //                        IJoinDecTargetDTO currentData = joinDecTargetDTO;
                        //                        currentData.setDecisionsDTO((IDecisionsDTO)desList.get(0));
                        descionsList.add(desList.get(0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return descionsList;
        }
        return null;
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
        stringBuilder.append("searchForDecisions");
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

    public boolean isJoinMode() {
        return divMode == JOIN_MODE;
    }

    public boolean isViewMode() {
        return divMode == VIEW_MODE;
    }

    protected SharedUtilBean getSharedUtil() {
        return SharedUtilBean.getInstance();
    }

    public BaseBean getContainerBean() {
        return (BaseBean)JSFHelper.getValue(getContainerBeanName());
    }


    public Long getVirtualLongValue() {
        return ISystemConstant.VIRTUAL_VALUE;
    }

    public IBasicDTO getElementById(List<IBasicDTO> dataList, String id) {
        for (IBasicDTO iBasicDTO : dataList) {
            if (iBasicDTO.getCode().getKey().equals(id)) {
                return iBasicDTO;
            }
        }
        return null;
    }

    public int getListSize() {
        return myTableData == null ? 0 : myTableData.size();
    }

    public void addToMyTableData(IBasicDTO iBasicDTO) {
        if (myTableData == null) {
            myTableData = new ArrayList<IBasicDTO>(1);
        }
        this.myTableData.add(iBasicDTO);
    }

    protected void fillTypeList() {

        try {
            typeList =
                    RegClientFactory.getTypesClient().getCodeNameByMinistry(ISystemConstant.REGULATION_VALIDITY_DECISION,
                                                                            CSCSecurityInfoHelper.getLoggedInMinistryCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (typeList == null) {
            try {
                typeList = RegClientFactory.getTypesClient().getCodeName(ISystemConstant.REGULATION_VALIDITY_DECISION);
            } catch (Exception e) {
                typeList = new ArrayList<IBasicDTO>(0);
                e.printStackTrace();
            }
        }
    }

    protected void fillYearsList() {
        try {
            yearsList = InfClientFactory.getYearsClient().getCodeName();
        } catch (Exception e) {
            yearsList = new ArrayList<IYearsDTO>(0);
            e.printStackTrace();
        }
    }

    protected void fillDecSourceList() {
        try {
            decSourceList =
                    InfClientFactory.getDecisionMakerTypesClient().getCodeNameByMin(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (decSourceList == null) {
            try {
                decSourceList = InfClientFactory.getDecisionMakerTypesClient().getCodeName();
            } catch (Exception e) {
                decSourceList = new ArrayList<IBasicDTO>(0);
                e.printStackTrace();
            }
        }
    }

    protected void fillDecSubjectList() {
        try {
            if (getOperationCode() != null) {
                decSubjectList =
                        RegClientFactory.getSubjectsClient().getCodeNameByModuleAndOperation(getOperationCode(),
                                                                                             CSCSecurityInfoHelper.getModuleCode());
            } else {
                decSubjectList =
                        RegClientFactory.getSubjectsClient().getCodeNameByModuleCode(ISystemConstant.REGULATION_VALIDITY_DECISION,
                                                                                     CSCSecurityInfoHelper.getModuleCode());
            }
        } catch (Exception e) {
            decSubjectList = new ArrayList<IBasicDTO>(0);
            e.printStackTrace();
        }
    }

    public void setTypeList(List<IBasicDTO> typeList) {
        this.typeList = typeList;
    }

    public List<IBasicDTO> getTypeList() {
        return typeList;
    }

    public void setYearsList(List<IYearsDTO> yearsList) {
        this.yearsList = yearsList;
    }

    public List<IYearsDTO> getYearsList() {
        return yearsList;
    }

    public void setDecSourceList(List<IBasicDTO> decSourceList) {
        this.decSourceList = decSourceList;
    }

    public List<IBasicDTO> getDecSourceList() {
        return decSourceList;
    }

    public void setDecSubjectList(List<IBasicDTO> decSubjectList) {
        this.decSubjectList = decSubjectList;
    }

    public List<IBasicDTO> getDecSubjectList() {
        return decSubjectList;
    }

    public void setMyTableData(List<IBasicDTO> myTableData) {
        this.myTableData = myTableData;
    }

    public List<IBasicDTO> getMyTableData() {
        return myTableData;
    }

    public void setMyDataTable(HtmlDataTable myDataTable) {
        this.myDataTable = myDataTable;
    }

    public HtmlDataTable getMyDataTable() {
        return myDataTable;
    }

    public void setOperationSucceeded(boolean operationSucceeded) {
        this.operationSucceeded = operationSucceeded;
    }

    public boolean isOperationSucceeded() {
        return operationSucceeded;
    }

    public void setSearchMode(boolean searchMode) {
        this.searchMode = searchMode;
    }

    public boolean isSearchMode() {
        return searchMode;
    }

    public void setSearchDTO(IRegulationSearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public IRegulationSearchDTO getSearchDTO() {
        return searchDTO;
    }

    public void setSelectedDecisionCode(String selectedDecisionCode) {
        this.selectedDecisionCode = selectedDecisionCode;
    }

    public String getSelectedDecisionCode() {
        return selectedDecisionCode;
    }

    public void setDivMode(int divMode) {
        this.divMode = divMode;
    }

    public int getDivMode() {
        return divMode;
    }

    public void setShowJoinAlert(boolean showJoinAlert) {
        this.showJoinAlert = showJoinAlert;
    }

    public boolean isShowJoinAlert() {
        return showJoinAlert;
    }

    public void setFullURL(String fullURL) {
        this.fullURL = fullURL;
    }

    public String getFullURL() {
        return fullURL;
    }

    public void setJoinDecisionPath(String joinDecisionPath) {
        this.joinDecisionPath = joinDecisionPath;
    }

    public String getJoinDecisionPath() {
        return joinDecisionPath;
    }

    public void setActiveDecisionCntPath(String activeDecisionCntPath) {
        this.activeDecisionCntPath = activeDecisionCntPath;
    }

    public String getActiveDecisionCntPath() {
        return activeDecisionCntPath;
    }

    public void setRelatedDecisionsPath(String relatedDecisionsPath) {
        this.relatedDecisionsPath = relatedDecisionsPath;
    }

    public String getRelatedDecisionsPath() {
        return relatedDecisionsPath;
    }

    public void setRelatedDecisionsCnt1Path(String relatedDecisionsCnt1Path) {
        this.relatedDecisionsCnt1Path = relatedDecisionsCnt1Path;
    }

    public String getRelatedDecisionsCnt1Path() {
        return relatedDecisionsCnt1Path;
    }

    public void setNavigationPath(String navigationPath) {
        this.navigationPath = navigationPath;
    }

    public String getNavigationPath() {
        return navigationPath;
    }

    public void setDecisionDetailsPageUri(String decisionDetailsPageUri) {
        this.decisionDetailsPageUri = decisionDetailsPageUri;
    }

    public String getDecisionDetailsPageUri() {
        return decisionDetailsPageUri;
    }

    public void setAlreadyHasDecisionAlertKey(String alreadyHasDecisionAlertKey) {
        this.alreadyHasDecisionAlertKey = alreadyHasDecisionAlertKey;
    }

    public String getAlreadyHasDecisionAlertKey() {
        return alreadyHasDecisionAlertKey;
    }

    public void setJoinContainerStyle(String joinContainerStyle) {
        this.joinContainerStyle = joinContainerStyle;
    }

    public String getJoinContainerStyle() {
        return joinContainerStyle;
    }

    public void setAlreadyHasDecisionAlertStyle(String alreadyHasDecisionAlertStyle) {
        this.alreadyHasDecisionAlertStyle = alreadyHasDecisionAlertStyle;
    }

    public String getAlreadyHasDecisionAlertStyle() {
        return alreadyHasDecisionAlertStyle;
    }

    public void setRelatedDecisionsCntStyle(String relatedDecisionsCntStyle) {
        this.relatedDecisionsCntStyle = relatedDecisionsCntStyle;
    }

    public String getRelatedDecisionsCntStyle() {
        return relatedDecisionsCntStyle;
    }

    public void setSearchInCandidate(boolean searchInCandidate) {
        this.searchInCandidate = searchInCandidate;
    }

    public boolean isSearchInCandidate() {
        return searchInCandidate;
    }


    protected IBasicDTO getSelectFromDecDIV() {
        //  IJoinDecTargetDTO target = this.getTarget();
        IBasicDTO decisionsDTO = null;
        if (selectedDecisionCode != null) {
            SharedUtilBean sharedUtilBean = this.getSharedUtil();
            try {
                //set parameters
                decisionsDTO = this.getElementById(myTableData, selectedDecisionCode);

                // this.applyJoin(target);


                //  sharedUtilBean.handleSuccMsg(RESOURCE_BUNDLE_NAME, MSG_KEY_JOIN_SUCCEEDED);
            } catch (Exception e) {
                sharedUtilBean.setLocalBundle(RESOURCE_BUNDLE_NAME);
                sharedUtilBean.handleException(e);

            }
        }

        return decisionsDTO;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getOperationCode() {
        return operationCode;
    }
}
