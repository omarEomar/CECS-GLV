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
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.business.entity.IConditionsEntityKey;
import com.beshara.csc.gn.grs.business.shared.IGrsConstants;
import com.beshara.csc.gn.grs.integration.business.joincond.ITargetForJoinConditionDTO;
import com.beshara.csc.gn.grs.integration.presentation.backingbean.conditions.ConditionDetailsBean;
import com.beshara.csc.hr.sal.business.client.ISalElementGuidesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.io.Serializable;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class JoinConditionHelperBean implements Serializable
{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private static final String RESOURCE_BUNDLE_NAME = "com.beshara.csc.gn.grs.integration.presentation.resources.integration";
    private static final String MSG_KEY_JOIN_SUCCEEDED = "SuccesJoinCondition";
    private static final String MSG_KEY_CANCEL_SUCCEEDED = "SuccesCancelCondition";
    private static final String MSG_KEY_HAS_NO_ACTIVE_CONDITION = "NoActiveCondException";
    public static final String DEF_DIV_ID = "customDiv1";
    public static final String DEF_HELPER_NAME = "jcHelper";
    public static final int JOIN_MODE = 1;
    public static final int CANCEL_MODE = 2;
    public static final int VIEW_MODE = 3;
    private int divMode;
    private String joinConditionPath;
    private String activeConditionCntPath;
    private String relatedConditionsPath;
    private String relatedConditionsCnt1Path;
    private String navigationPath;
    private String changeActiveConditionAlertKey;
    private String cancelConditionAlertKey;
    private String joinContainerStyle;
    private String relatedConditionsCntStyle;
    private String changeActiveConditionAlertStyle;
    private String cancelConditionAlertStyle;
    private boolean activeCondRelationLoaded;
    private boolean showJoinAlert;
    private boolean operationSucceeded;
    private List linesList;
    private ISearchConditionsDTO    searchConditionsDTO;
    private Long exculdedConditionList[];
    private Long searchInStatusList[];
    private List myTableData;
    private String selectedConditionCode;
    private String fullURL;
    private boolean searchMode;
    private IConditionRelationsDTO activeCondRelation;
    private transient HtmlDataTable myDataTable;
    private boolean displayRelatedModules;
    private boolean allowMultiCondition;
    private ITargetForJoinConditionDTO targetForJoinConditionDTO;
    private String containerBeanName;
    private String divId;
    private String transactionName;

    private Boolean renderConditionFilter = false;
    private  String conditionFilter = "0";
    //start  CSC-19365 دليل البدلات والمكافأت CSC-19554
    private int mode =0; // for join div 1 for add condition 
    private String ADD_CONDITION_NAV_CASE="condition_intg_Main_Data";
    private String  acceptMethodNewConditionAdd=null;
    //End  CSC-19365 دليل البدلات والمكافأت CSC-19554
    public JoinConditionHelperBean()
    {
        divMode = 1;
        joinConditionPath = "/integration/grs/jsps/joincond/cnt/join.jsp";
        activeConditionCntPath = "/integration/grs/jsps/joincond/cnt/activeConditionCnt.jsp";
        relatedConditionsPath = "/integration/grs/jsps/joincond/cnt/relatedConditions.jsp";
        relatedConditionsCnt1Path = "/integration/grs/jsps/joincond/cnt/relatedConditionsCnt1.jsp";
        navigationPath = "/integration/grs/jsps/joincond/cnt/navigation.jsp";
        changeActiveConditionAlertKey = "changeActiveConditionAlertMsg";
        cancelConditionAlertKey = "cancelConditionAlertMsg";
        joinContainerStyle = "grsIntgJoinContainerStyle";
        relatedConditionsCntStyle = "fullWidthScroll200";
        changeActiveConditionAlertStyle = "msg warning";
        cancelConditionAlertStyle = "msg warning";
        linesList = null;
        searchConditionsDTO = GrsDTOFactory.createSearchConditionsDTO();
        myDataTable = new HtmlDataTable();
        allowMultiCondition = false;
        divId = "customDiv1";
    }

    public JoinConditionHelperBean(ITargetForJoinConditionDTO targetForJoinConditionDTO, String divId, String transactionName, Boolean allowMultiCondition)
    {
        divMode = 1;
        joinConditionPath = "/integration/grs/jsps/joincond/cnt/join.jsp";
        activeConditionCntPath = "/integration/grs/jsps/joincond/cnt/activeConditionCnt.jsp";
        relatedConditionsPath = "/integration/grs/jsps/joincond/cnt/relatedConditions.jsp";
        relatedConditionsCnt1Path = "/integration/grs/jsps/joincond/cnt/relatedConditionsCnt1.jsp";
        navigationPath = "/integration/grs/jsps/joincond/cnt/navigation.jsp";
        changeActiveConditionAlertKey = "changeActiveConditionAlertMsg";
        cancelConditionAlertKey = "cancelConditionAlertMsg";
        joinContainerStyle = "grsIntgJoinContainerStyle";
        relatedConditionsCntStyle = "fullWidthScroll200";
        changeActiveConditionAlertStyle = "msg warning";
        cancelConditionAlertStyle = "msg warning";
        linesList = null;
        searchConditionsDTO = GrsDTOFactory.createSearchConditionsDTO();
        myDataTable = new HtmlDataTable();
        this.allowMultiCondition = false;
        this.divId = "customDiv1";
        setTargetForJoinConditionDTO(targetForJoinConditionDTO);
        this.divId = divId;
        this.transactionName = transactionName;
        this.allowMultiCondition = allowMultiCondition.booleanValue();
    }

    public void applyJoin()
        throws SharedApplicationException, DataBaseException
    {
        GrsClientFactory.getConditionRelationsClient().joinCondition(getTargetForJoinConditionDTO(), getTransactionName(),allowMultiCondition);
    }

    public void applyCancel()
        throws SharedApplicationException, DataBaseException
    {
        GrsClientFactory.getConditionRelationsClient().cancelCondition(getTargetForJoinConditionDTO(), getTransactionName(),allowMultiCondition);
    }

    public String getHelperName()
    {
        return "jcHelper";
    }

    public boolean validForJoin()
    {
        return true;
    }

    public boolean validForCancel()
    {
        return true;
    }

    public void resetData()
    {
        setShowJoinAlert(false);
        setOperationSucceeded(false);
        setActiveCondRelationLoaded(false);
        setActiveCondRelation(null);
        setLinesList(null);
        setSearchConditionsDTO(GrsDTOFactory.createSearchConditionsDTO());
        setExculdedConditionList(null);
        setSearchInStatusList(null);
        setMyTableData(null);
        if(getMyDataTable() != null)
            getMyDataTable().setFirst(0);
        setSelectedConditionCode(null);
    }

    public void scrollerAction(ActionEvent ae)
    {
         writeShowDivJScript(null);
    }
    
  
    public void writeShowDivJScript(String msgKey)
    {
        
        
        if(!myTableData.isEmpty())
            getContainerBean().setJavaScriptCaller((new StringBuilder()).append("changeVisibilityDiv(window.blocker,window.").append(getDivId()).append(",'grsIntgDivBackBtn');").toString());
        
        else if (msgKey != null)
            getSharedUtil().handleException(null, "com.beshara.csc.gn.grs.integration.presentation.resources.integration", msgKey );

        else getSharedUtil().handleException(null, "com.beshara.jsfbase.csc.msgresources.global", "global_noTableResults");

    }

    public void viewRelatedConditions()
    {
        try
        {
            resetData();
            setDivMode(3);
            List relTabrecSerialList = new ArrayList(1);
            loadRelatedConditions(getTargetForJoinConditionDTO().getTabrecSerial());
            writeShowDivJScript(null);
        }
        catch(Exception e)
        {
            getSharedUtil().handleException(e);
        }
    }

    public void openJoinDiv()
    {
        System.out.println("************ openJoinDiv()  ******************************");
        resetData();
        setDivMode(1);
        if(validForJoin())
        {
            if(getActiveCondCode() != null)
            {
                addToMyTableData(getActiveCondRelation());
                setShowJoinAlert(true);
            }else if(mode==1){
                 goToNewConditionPage();
                 return ;
             } 
            
            else
            {
                initJoinDiv();
            }
            writeShowDivJScript("NoConditionsToJoin");
        }
    }

    //start  CSC-19365 دليل البدلات والمكافأت CSC-19554
    private void goToNewConditionPage(){
        
        
            if (acceptMethodNewConditionAdd != null && !acceptMethodNewConditionAdd.equals("")) {
                JSFHelper.callMethod(acceptMethodNewConditionAdd);
            }
            JSFHelper.handleNavigation(ADD_CONDITION_NAV_CASE);
        
        }
    public void hideJoinAlert()
    {

        try {
            //CSC-19365 دليل البدلات والمكافأت CSC-19554
            if (mode == 1) {
                goToNewConditionPage();
                return;
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        setShowJoinAlert(false);
        /*if(getTargetForJoinConditionDTO().getAddJoinConditionType() != null && getTargetForJoinConditionDTO().getAddJoinConditionType().equals(IGrsConstants.CONDITION_JOIN_TYPE_NOT_ACTIVE))
        {
            getTargetForJoinConditionDTO().setAddJoinConditionType(IGrsConstants.CONDITION_JOIN_TYPE_NOT_ACTIVE_AND_REPLACE_ACTIVE);
        }*/
        initJoinDiv();
    }

    public void openCancelDiv()
    {
        resetData();
        setDivMode(2);
        if(getActiveCondCode() != null)
        {
            if(validForCancel())
            {
                addToMyTableData(getActiveCondRelation());
                writeShowDivJScript(null);
            }
        } else
        {
            getSharedUtil().handleException(null, "com.beshara.csc.gn.grs.integration.presentation.resources.integration", "NoActiveCondException");
        }
    }

    public void initJoinDiv()
    {
        System.out.println("************ initJoinDiv()  ******************************");
        fillLinesList();
        cancelSearch();
        setSearchInStatus(ISystemConstant.ACTIVE_FLAG);
        setExculdedConditionList(null);
        if(getActiveCondCode() != null)
            setExculdedCondition(getActiveCondCode());
        /** by Aly Nour to exclude non active related conditions from join div story CSC-11806 */
        System.out.println("exclude non active getTargetForJoinConditionDTO().getAddJoinConditionType()  = "+getTargetForJoinConditionDTO().getAddJoinConditionType());
        if(getTargetForJoinConditionDTO().getAddJoinConditionType() != null ) {
            if(getTargetForJoinConditionDTO().getAddJoinConditionType().equals(IGrsConstants.CONDITION_JOIN_TYPE_NOT_ACTIVE)
                || getTargetForJoinConditionDTO().getAddJoinConditionType().equals(IGrsConstants.CONDITION_JOIN_TYPE_NOT_ACTIVE_AND_REPLACE_ACTIVE) )
            {
                List list = null;
                Date currDate = SharedUtils.getCurrentDate();
                try {
                    list = GrsClientFactory.getConditionRelationsClient().getAllConditionsByStatusAndDates(getTargetForJoinConditionDTO().getTabrecSerial() , ISystemConstant.NON_ACTIVE_FLAG , currDate);
                } catch (DataBaseException e) {
                } catch (SharedApplicationException e) {
                }
                if(list != null)setExculdedNonActiveRelatedConditions(list);
            }
        }
        search();
        setSearchMode(false);
    }

    public void viewConditionDetails()
    {
        setActiveCondRelationLoaded(false);
        if(getActiveCondCode() != null)
        {
            constructConditionDetailsPagePath(getActiveCondCode().toString());
            getContainerBean().setJavaScriptCaller("openGrsIntgConditionDetailsWindow();");
        } else
        {
            getSharedUtil().handleException(null, "com.beshara.csc.gn.grs.integration.presentation.resources.integration", "NoActiveCondRelatedException");
            getContainerBean().setJavaScriptCaller("changeVisibilityMsg();");
        }
    }

    public void viewConditionDetails(ActionEvent actionEvent)
    {
        String conditionKey = (String)actionEvent.getComponent().getAttributes().get("conditionCode");
        constructConditionDetailsPagePath(conditionKey);
    }

    public void constructConditionDetailsPagePath(String conditionCode)
    {
        fullURL = ConditionDetailsBean.constructPageURL(conditionCode, isDisplayRelatedModules());
        System.out.println(fullURL);
    }

    public void pre()
    {
        if(getTargetForJoinConditionDTO() != null && selectedConditionCode != null)
        {
            IConditionsDTO selectedConditionDTO = GrsDTOFactory.createConditionsDTO();
            IConditionsEntityKey ek = GrsEntityKeyFactory.createConditionsEntityKey(Long.valueOf(selectedConditionCode));
            selectedConditionDTO.setCode(ek);
            getTargetForJoinConditionDTO().setConditionsDTO(selectedConditionDTO);
        }
    }

    public void join()
    {
        ITargetForJoinConditionDTO target = getTargetForJoinConditionDTO();
        if(target != null && selectedConditionCode != null)
        {
            SharedUtilBean sharedUtilBean = getSharedUtil();
            try
            {
                applyJoin();
                setOperationSucceeded(true);
                sharedUtilBean.handleSuccMsg("com.beshara.csc.gn.grs.integration.presentation.resources.integration", "SuccesJoinCondition");
            }
            catch(Exception e)
            {
                sharedUtilBean.setLocalBundle("com.beshara.csc.gn.grs.integration.presentation.resources.integration");
                sharedUtilBean.handleException(e);
            }
        }
    }

    public void cancel()
    {
        ITargetForJoinConditionDTO target = getTargetForJoinConditionDTO();
        if(target != null)
        {
            SharedUtilBean sharedUtilBean = getSharedUtil();
            try
            {
                applyCancel();
                setOperationSucceeded(true);
                sharedUtilBean.handleSuccMsg("com.beshara.csc.gn.grs.integration.presentation.resources.integration", "SuccesCancelCondition");
            }
            catch(Exception e)
            {
                sharedUtilBean.setLocalBundle("com.beshara.csc.gn.grs.integration.presentation.resources.integration");
                sharedUtilBean.handleException(e);
            }
        }
    }

    public void post()
    {
        if(isOperationSucceeded())
        {
            BaseBean bean = getContainerBean();
            bean.setPageDTO(getTargetForJoinConditionDTO());
            /** by Aly Nour to activate related conditions actiavtion menu from join div story CSC-11806 */
            System.out.println(" *** activate related conditions actiavtion menu from join div getTargetForJoinConditionDTO().getAddJoinConditionType()  = "+getTargetForJoinConditionDTO().getAddJoinConditionType());
            if(getTargetForJoinConditionDTO().getAddJoinConditionType() != null ) {
                if(getTargetForJoinConditionDTO().getAddJoinConditionType().equals(IGrsConstants.CONDITION_JOIN_TYPE_NOT_ACTIVE)
                    || getTargetForJoinConditionDTO().getAddJoinConditionType().equals(IGrsConstants.CONDITION_JOIN_TYPE_NOT_ACTIVE_AND_REPLACE_ACTIVE) )
                {   
                    String methodExpression = containerBeanName+ "."+"checkActivateButtonStatus";
                    Object[] refTabrecSerialArr = {getTargetForJoinConditionDTO().getTabrecSerial()};
                    Class[]refTabrecSerialType = {Long.class};
                    try {
                        bean.executeMethodBindingWithParams(methodExpression, refTabrecSerialArr, refTabrecSerialType);
                    } catch (Exception e) {
                        // TODO: Add catch code
                        e.printStackTrace();
                    }
                }
            }
            /** by Aly Nour to activate related conditions actiavtion menu from join div story CSC-11806 */
            StringBuilder js = new StringBuilder("showDivTreeDetails(");
            js.append("null");
            js.append(",");
            js.append(getDisplayedCode());
            js.append(",");
            js.append("window.divTreeDetails");
            js.append(");");
            bean.setJavaScriptCaller(js.toString());
            bean.highlighDataTable(getContainerBeanName());
        }
    }

    public String getDisplayedCode()
    {
        ITargetForJoinConditionDTO dto = getTargetForJoinConditionDTO();
        return dto != null ? dto.getCode().getKey() : null;
    }

    public String getDisplayedName()
    {
        ITargetForJoinConditionDTO dto = getTargetForJoinConditionDTO();
        return dto != null ? dto.getName() : null;
    }

    public void execute()
    {
      
        setOperationSucceeded(false);
        pre();
        if(isJoinMode())
            join();
        else
        if(isCancelMode())
            cancel();
        post();
    }

    public String search()
    {
        getMyDataTable().setFirst(0);
        JSFHelper.callMethod(getSearchButtonMethod());
        return null;
    }

    public void cancelSearch()
    {
        setSearchMode(false);
        setSearchConditionsDTO(GrsDTOFactory.createSearchConditionsDTO());
        setMyTableData(new ArrayList(0));
        getSearchConditionsDTO().setRenderConditionFilter(renderConditionFilter);
        getSearchConditionsDTO().setConditionFilter(conditionFilter);
        search();
        setSearchMode(false);
    }

    public void searchForConditions()
    {
        List temp = new ArrayList(0);
        try
        {
            IConditionsClient iConditionsClient = GrsClientFactory.getConditionsClient();
            searchConditionsDTO.setRenderConditionFilter(renderConditionFilter);
            if(exculdedConditionList != null && exculdedConditionList.length > 0)
            {
                searchConditionsDTO.setExceptConditionsCode(exculdedConditionList);
            } else
            {
                Long dumyExcludCondition[] = new Long[1];
                dumyExcludCondition[0] = getVirtualLongValue();
                searchConditionsDTO.setExceptConditionsCode(dumyExcludCondition);
            }
            searchConditionsDTO.setSearchInStatus(searchInStatusList);
            Long moduleCode = getModuleCode();
            if(moduleCode != null)
                searchConditionsDTO.setAppModuleCode(String.valueOf(moduleCode));
             
            
            if (!searchConditionsDTO.getRenderConditionFilter()|| (!searchConditionsDTO.getConditionFilter().equals("") && searchConditionsDTO.getConditionFilter().equals("0"))) {
                temp = iConditionsClient.search(getSearchConditionsDTO());
            } else if (searchConditionsDTO.getConditionFilter().equals("1") || searchConditionsDTO.getConditionFilter().equals("2")) {

//                searchConditionsDTO.setConditionFilter(conditionFilter);
                ISalElementGuidesClient salElementGuidesClient = SalClientFactory.getSalElementGuidesClient();
                //                Long selectElmguideTabrecSerial = getTargetForJoinConditionDTO().getTabrecSerial();
                //                Long filterValue = Long.parseLong(conditionFilter);
                  temp =salElementGuidesClient.getCondtionsByFilter(searchConditionsDTO);
                                                  
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        setMyTableData(temp);
        setSearchMode(true);
    }

    private void fillLinesList()
    {
        try
        {
            ILinesClient iLinesClient = GrsClientFactory.getLinesClient();
            ISearchConditionsDTO searchLinesDTO = GrsDTOFactory.createSearchConditionsDTO();
            if(exculdedConditionList != null && exculdedConditionList.length > 0)
            {
                searchLinesDTO.setExceptConditionsCode(exculdedConditionList);
            } else
            {
                Long dumyExcludCondition[] = new Long[1];
                dumyExcludCondition[0] = ISystemConstant.VIRTUAL_VALUE;
                searchLinesDTO.setExceptConditionsCode(dumyExcludCondition);
            }
            Long moduleCode = getModuleCode();
            if(moduleCode != null)
            {
                searchLinesDTO.setAppModuleCode(String.valueOf(moduleCode));
                linesList = iLinesClient.getLinesByModule(searchLinesDTO);
            } else
            {
                linesList = iLinesClient.getCodeName();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            linesList = new ArrayList(0);
        }
    }

    public void loadRelatedConditions(Long relTabrecSerial)
    {
        List relTabrecSerialList = new ArrayList(1);
        relTabrecSerialList.add(relTabrecSerial);
        loadRelatedConditions(relTabrecSerialList);
    }

    public void loadRelatedConditions(List relTabrecSerialList)
    {
        myTableData = new ArrayList(0);
        if(relTabrecSerialList != null && !relTabrecSerialList.isEmpty() && relTabrecSerialList.get(0) != null)
            try
            {
                myTableData = GrsClientFactory.getConditionRelationsClient().getAllByRelTabrecSerial(relTabrecSerialList);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    public SharedUtilBean getSharedUtil()
    {
        return SharedUtilBean.getInstance();
    }

    public BaseBean getContainerBean()
    {
        return (BaseBean)JSFHelper.getValue(getContainerBeanName());
    }

    public String getOkButtonMethod()
    {
        StringBuilder stringBuilder = new StringBuilder(getContainerBeanName());
        stringBuilder.append(".");
        stringBuilder.append(getHelperName());
        stringBuilder.append(".");
        stringBuilder.append("execute");
        return stringBuilder.toString();
    }

    public String getSearchButtonMethod()
    {
        StringBuilder stringBuilder = new StringBuilder(getContainerBeanName());
        stringBuilder.append(".");
        stringBuilder.append(getHelperName());
        stringBuilder.append(".");
        stringBuilder.append("searchForConditions");
        return stringBuilder.toString();
    }

    public String getResetJoinButtonMethod()
    {
        StringBuilder stringBuilder = new StringBuilder(getContainerBeanName());
        stringBuilder.append(".");
        stringBuilder.append(getHelperName());
        stringBuilder.append(".");
        stringBuilder.append("resetJoinData");
        return stringBuilder.toString();
    }

    public Long getModuleCode()
    {
        return CSCSecurityInfoHelper.getModuleCode();
    }

    public void resetJoinData()
    {
    }

    public void setShowJoinAlert(boolean showJoinAlert)
    {
        this.showJoinAlert = showJoinAlert;
    }

    public boolean isShowJoinAlert()
    {
        return showJoinAlert;
    }

    public void setActiveCondRelation(IConditionRelationsDTO activeCondRelation)
    {
        this.activeCondRelation = activeCondRelation;
    }

    public IConditionRelationsDTO getActiveCondRelation()
    {
        if(!isActiveCondRelationLoaded())
        {
            activeCondRelation = null;
            try
            {
                activeCondRelation = (IConditionRelationsDTO)GrsClientFactory.getConditionRelationsClient().getCurrentActiveByRelTabrecSerial(getTargetForJoinConditionDTO().getTabrecSerial());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            setActiveCondRelationLoaded(true);
        }
        return activeCondRelation;
    }

    public Long getActiveCondCode()
    {
        if(getActiveCondRelation() != null && getActiveCondRelation().getCode() != null)
            return ((IConditionsEntityKey)activeCondRelation.getConditionsDTO().getCode()).getConditionCode();
        else
            return null;
    }

    public Long getVirtualLongValue()
    {
        return ISystemConstant.VIRTUAL_VALUE;
    }

    public IBasicDTO getElementById(List dataList, String id)
    {
        for(Iterator i$ = dataList.iterator(); i$.hasNext();)
        {
            IBasicDTO iBasicDTO = (IBasicDTO)i$.next();
            if(iBasicDTO.getCode().getKey().equals(id))
                return iBasicDTO;
        }

        return null;
    }

    public void setActiveCondRelationLoaded(boolean activeCondRelationLoaded)
    {
        this.activeCondRelationLoaded = activeCondRelationLoaded;
    }

    public boolean isActiveCondRelationLoaded()
    {
        return activeCondRelationLoaded;
    }

    public void setOperationSucceeded(boolean operationSucceeded)
    {
        this.operationSucceeded = operationSucceeded;
    }

    public boolean isOperationSucceeded()
    {
        return operationSucceeded;
    }

    public void setRelatedConditionsCnt1Path(String relatedConditionsCnt1Path)
    {
        this.relatedConditionsCnt1Path = relatedConditionsCnt1Path;
    }

    public String getRelatedConditionsCnt1Path()
    {
        return relatedConditionsCnt1Path;
    }

    public void setRelatedConditionsCntStyle(String relatedConditionsCntStyle)
    {
        this.relatedConditionsCntStyle = relatedConditionsCntStyle;
    }

    public String getRelatedConditionsCntStyle()
    {
        return relatedConditionsCntStyle;
    }

    public void setDivMode(int divMode)
    {
        this.divMode = divMode;
    }

    public int getDivMode()
    {
        return divMode;
    }

    public boolean isJoinMode()
    {
        return divMode == 1;
    }

    public boolean isCancelMode()
    {
        return divMode == 2;
    }

    public boolean isViewMode()
    {
        return divMode == 3;
    }

    public void setJoinConditionPath(String joinConditionPath)
    {
        this.joinConditionPath = joinConditionPath;
    }

    public String getJoinConditionPath()
    {
        return joinConditionPath;
    }

    public void setActiveConditionCntPath(String activeConditionCntPath)
    {
        this.activeConditionCntPath = activeConditionCntPath;
    }

    public String getActiveConditionCntPath()
    {
        return activeConditionCntPath;
    }

    public void setNavigationPath(String navigationPath)
    {
        this.navigationPath = navigationPath;
    }

    public String getNavigationPath()
    {
        return navigationPath;
    }

    public void setRelatedConditionsPath(String relatedConditionsPath)
    {
        this.relatedConditionsPath = relatedConditionsPath;
    }

    public String getRelatedConditionsPath()
    {
        return relatedConditionsPath;
    }

    public void setChangeActiveConditionAlertKey(String changeActiveConditionAlertKey)
    {
        this.changeActiveConditionAlertKey = changeActiveConditionAlertKey;
    }

    public String getChangeActiveConditionAlertKey()
    {
        return changeActiveConditionAlertKey;
    }

    public void setCancelConditionAlertKey(String cancelConditionAlertKey)
    {
        this.cancelConditionAlertKey = cancelConditionAlertKey;
    }

    public String getCancelConditionAlertKey()
    {
        return cancelConditionAlertKey;
    }

    public void setChangeActiveConditionAlertStyle(String changeActiveConditionAlertStyle)
    {
        this.changeActiveConditionAlertStyle = changeActiveConditionAlertStyle;
    }

    public String getChangeActiveConditionAlertStyle()
    {
        return changeActiveConditionAlertStyle;
    }

    public void setCancelConditionAlertStyle(String cancelConditionAlertStyle)
    {
        this.cancelConditionAlertStyle = cancelConditionAlertStyle;
    }

    public String getCancelConditionAlertStyle()
    {
        return cancelConditionAlertStyle;
    }

    public void setJoinContainerStyle(String joinContainerStyle)
    {
        this.joinContainerStyle = joinContainerStyle;
    }

    public String getJoinContainerStyle()
    {
        return joinContainerStyle;
    }

    public void setLinesList(List linesList)
    {
        this.linesList = linesList;
    }

    public List getLinesList()
    {
        return linesList;
    }

    public void setSearchConditionsDTO(ISearchConditionsDTO searchConditionsDTO)
    {
        this.searchConditionsDTO = searchConditionsDTO;
    }

    public ISearchConditionsDTO getSearchConditionsDTO()
    {
        return searchConditionsDTO;
    }

    public void setExculdedConditionList(Long exculdedConditionList[])
    {
        this.exculdedConditionList = exculdedConditionList;
    }

    public Long[] getExculdedConditionList()
    {
        return exculdedConditionList;
    }

    public void setExculdedCondition(Long exculdedConditionCode)
    {
        if(exculdedConditionList == null)
            exculdedConditionList = new Long[1];
        exculdedConditionList[0] = exculdedConditionCode;
    }

    public void setExculdedNonActiveRelatedConditions(List<IConditionRelationsDTO> relConditions)
    {
        if(exculdedConditionList == null){
            exculdedConditionList = new Long[relConditions.size() ];
            for (int j = 0; j < relConditions.size() ; j++) {
                exculdedConditionList[j] = ((IConditionsEntityKey)((IConditionsDTO) ((IConditionRelationsDTO)relConditions.get(j) ).getConditionsDTO() ).getCode() ).getConditionCode() ;
            }
        }
        else { // there are active excluded conditions
                Long [] tmp = new Long[relConditions.size() + exculdedConditionList.length ];
                int currExecludedCount = exculdedConditionList.length;
                for (int i = 0; i < currExecludedCount ; i++) {
                    tmp[i] = exculdedConditionList[i];
                }
                for (int j = 0; j < relConditions.size() ; j++) {
                    tmp[j + currExecludedCount] = ((IConditionsEntityKey)((IConditionsDTO) ((IConditionRelationsDTO)relConditions.get(j) ).getConditionsDTO() ).getCode() ).getConditionCode() ;
                }

            exculdedConditionList = tmp;
                
            }
    }
    
    public void setSearchInStatusList(Long searchInStatusList[])
    {
        this.searchInStatusList = searchInStatusList;
    }

    public Long[] getSearchInStatusList()
    {
        return searchInStatusList;
    }

    public void setSearchInStatus(Long status)
    {
        if(searchInStatusList == null)
            searchInStatusList = new Long[1];
        searchInStatusList[0] = status;
    }

    public void addToMyTableData(IBasicDTO iBasicDTO)
    {
        if(myTableData == null)
            myTableData = new ArrayList(1);
        myTableData.add(iBasicDTO);
    }

    public void setFullURL(String fullURL)
    {
        this.fullURL = fullURL;
    }

    public String getFullURL()
    {
        return fullURL;
    }

    public void setSelectedConditionCode(String selectedConditionCode)
    {
        this.selectedConditionCode = selectedConditionCode;
    }

    public String getSelectedConditionCode()
    {
        return selectedConditionCode;
    }

    public void setMyDataTable(HtmlDataTable myDataTable)
    {
        this.myDataTable = myDataTable;
    }

    public HtmlDataTable getMyDataTable()
    {
        return myDataTable;
    }

    public void setMyTableData(List myTableData)
    {
        this.myTableData = myTableData;
    }

    public List getMyTableData()
    {
        return myTableData;
    }

    public int getListSize()
    {
        return myTableData != null ? myTableData.size() : 0;
    }

    public void setSearchMode(boolean searchMode)
    {
        this.searchMode = searchMode;
    }

    public boolean isSearchMode()
    {
        return searchMode;
    }

    public void setDisplayRelatedModules(boolean displayRelatedModules)
    {
        this.displayRelatedModules = displayRelatedModules;
    }

    public boolean isDisplayRelatedModules()
    {
        return displayRelatedModules;
    }

    public void setAllowMultiCondition(boolean allowMultiCondition)
    {
        this.allowMultiCondition = allowMultiCondition;
    }

    public boolean isAllowMultiCondition()
    {
        return allowMultiCondition;
    }

    public void setTargetForJoinConditionDTO(ITargetForJoinConditionDTO targetForJoinConditionDTO)
    {
        this.targetForJoinConditionDTO = targetForJoinConditionDTO;
    }

    public ITargetForJoinConditionDTO getTargetForJoinConditionDTO()
    {
        return targetForJoinConditionDTO;
    }

    public void setContainerBeanName(String containerBeanName)
    {
        this.containerBeanName = containerBeanName;
    }

    public String getContainerBeanName()
    {
        return containerBeanName;
    }

    public void setDivId(String divId)
    {
        this.divId = divId;
    }

    public String getDivId()
    {
        return divId;
    }

    public void setTransactionName(String transactionName)
    {
        this.transactionName = transactionName;
    }

    public String getTransactionName()
    {
        return transactionName;
    }


    public void setRenderConditionFilter(Boolean renderConditionFilter) {
        this.renderConditionFilter = renderConditionFilter;
    }

    public Boolean getRenderConditionFilter() {
        return renderConditionFilter;
    }

    public void setConditionFilter(String conditionFilter) {
        this.conditionFilter = conditionFilter;
    }

    public String getConditionFilter() {
        return conditionFilter;
    }
    
    
    

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public void setAcceptMethodNewConditionAdd(String acceptMethodNewConditionAdd) {
        this.acceptMethodNewConditionAdd = acceptMethodNewConditionAdd;
    }

    public String getAcceptMethodNewConditionAdd() {
        return acceptMethodNewConditionAdd;
    }
}
