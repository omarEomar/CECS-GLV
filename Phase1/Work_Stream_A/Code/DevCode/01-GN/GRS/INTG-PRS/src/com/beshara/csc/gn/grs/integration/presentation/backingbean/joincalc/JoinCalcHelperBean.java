package com.beshara.csc.gn.grs.integration.presentation.backingbean.joincalc;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.ICalculationsClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.ICalculationDetailsDTO;
import com.beshara.csc.gn.grs.business.dto.ICalculationRelationsDTO;
import com.beshara.csc.gn.grs.business.dto.ICalculationsDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionAppModulesDTO;
import com.beshara.csc.gn.grs.business.dto.ISearchCalculationsDTO;
import com.beshara.csc.gn.grs.business.entity.CalculationRelationsEntityKey;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.integration.presentation.backingbean.calculations.CalculationsMaintainBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class JoinCalcHelperBean extends BaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private ISearchCalculationsDTO searchCalcDTO;
    private String calcType = "1";
    private String fixedValueCalcType = "1";
    private String selectCalcType = "2";
    private String addJoinCalcType = "3";
    private boolean showJoinAlert;
    private String containerBeanName;
    private String divId;

    private BigDecimal value;
    private boolean searchMode;

    private Long exculdedCalcList[];
    private Long searchInStatusList[];
    private boolean operationSucceeded;

    private String newCalcName;
    private String savedNewCalcName;
    private boolean joinedBefore;
    private String fullURL;
    private boolean displayRelatedModules;
    private Double viewedValue;
    private Long tabricSerialRef;
    private Long selectedElmGuidecode;
    private String tableName ;
    private String backNavCase;
    private String backMethod;
    private Object savedDataObj;
   
    public JoinCalcHelperBean() {
        setClient(GrsClientFactory.getConditionRelationsClient());
        setPageDTO(GrsDTOFactory.createCalculationRelationsDTO());
    }

    public void openJoinDiv(Long tabRecSerial, String tableName, String catName, String containerBeanName,
                            String divId) {
        System.out.println("**************************** open CalcJoinDiv ***********************************");
        resetData();
        this.tableName=tableName;
        this.tabricSerialRef=tabRecSerial;         
        this.containerBeanName = containerBeanName;
        this.newCalcName = catName;
        this.savedNewCalcName = catName;
        this.divId = divId;
        IBasicDTO activeCalc = getActiveCalc(tabRecSerial);
        if (activeCalc != null) {
            addToMyTableData(activeCalc);
            setShowJoinAlert(true);
            setJoinedBefore(true);
            setExculdedCalc(((CalculationRelationsEntityKey)activeCalc.getCode()).getCalculationCode());
        }

        initJoinDiv(tabRecSerial, tableName);
        writeShowDivJScript();
        System.out.println("**************************** CalcJoinDiv opened  ***********************************");

    }

    public void hideJoinAlert() {
        setShowJoinAlert(false);
        calcType = fixedValueCalcType;
        changeCalcType(null);
    }

    public void initJoinDiv(Long tabRecSerial, String tableName) {
        calcType = fixedValueCalcType;
        setSearchInStatus(ISystemConstant.ACTIVE_FLAG);

        ICalculationRelationsDTO calcRelDTO = (ICalculationRelationsDTO)getPageDTO();
        calcRelDTO.setRTabrecSerial(tabRecSerial);
        calcRelDTO.setTableName(tableName);

    }

    public void execute() {

        preJoin();

        join();

        //post();
    }

    public void preJoin() {
        ICalculationRelationsDTO calcRelDTO = (ICalculationRelationsDTO)getPageDTO();
        calcRelDTO.setFromDate(SharedUtils.getCurrentDate());
        calcRelDTO.setStatus(ISystemConstant.ACTIVE_FLAG);

        if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty())
            calcRelDTO.setCalculationsDTO(getSelectedDTOS().get(0));
    }

    public void join() {
        ICalculationRelationsDTO calcRelDTO = (ICalculationRelationsDTO)getPageDTO();
        if (calcRelDTO != null) {
            SharedUtilBean sharedUtilBean = getSharedUtil();
            try {
                applyJoin();
                setOperationSucceeded(true);
                sharedUtilBean.handleSuccMsg("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                             "SuccesJoinCondition");
            } catch (Exception e) {
                sharedUtilBean.setLocalBundle("com.beshara.csc.gn.grs.integration.presentation.resources.integration");
                sharedUtilBean.handleException(e);
            }
        }
    }

    public void applyJoin() throws SharedApplicationException, DataBaseException {
        boolean fixedValue = false;
        if (calcType.equals(fixedValueCalcType))
            fixedValue = true;

        GrsClientFactory.getCalculationRelationsClient().joinCalculation(getPageDTO(), fixedValue, newCalcName, value,
                                                                         joinedBefore);
        resetData();
    }


    public IBasicDTO getActiveCalc(Long tabRecSerial) {
        try {
            viewedValue=null;  
            IBasicDTO dto =
                GrsClientFactory.getCalculationRelationsClient().getCurrentActiveByRelTabrecSerial(tabRecSerial);
            if (dto != null && dto.getCode() != null){
                ICalculationRelationsDTO calculationRelationsDTO = (ICalculationRelationsDTO)dto;
                if(calculationRelationsDTO.getCalculationsDTO()!=null){
                   List list= ((ICalculationsDTO)calculationRelationsDTO.getCalculationsDTO()).getCalculationDetailsDTOList();
                   if(list!=null && list.size()>0){
                       if(list.size()==1 && ((ICalculationDetailsDTO)list.get(0)).getOperatorSign()==null){
                          viewedValue= ((ICalculationDetailsDTO)list.get(0)).getValue();
                       }
                   }
                }
                return dto;
            }
        } catch (Exception e) {
            ;
        }

        return null;
    }
    public String addAndJoinCalc(){
        //ManyToManyMaintainBaseBean maintainBean = 
        //    (ManyToManyMaintainBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{calculationsMaintainBean}").getValue(FacesContext.getCurrentInstance());
        CalculationsMaintainBean maintainBean =  CalculationsMaintainBean.getInstance();        
        maintainBean.setMaintainMode(0);   
        ICalculationsDTO calculationsDTO=GrsDTOFactory.createCalculationsDTO();
        calculationsDTO.setCalculationDetailsDTOList(new ArrayList<IBasicDTO>(0));
        IConditionAppModulesDTO condAppModuleDTO = GrsDTOFactory.createConditionAppModulesDTO();
        IEntityKey key= GrsEntityKeyFactory.createConditionAppModulesEntityKey(null,CSCSecurityInfoHelper.getModuleCode());
        condAppModuleDTO.setCode(key);
        List conditionAppModulesList=new ArrayList<IBasicDTO>();
        conditionAppModulesList.add(condAppModuleDTO);
        calculationsDTO.setConditionAppModulesList(conditionAppModulesList);
        maintainBean.setPageDTO(calculationsDTO);  
        
//        String navCase = "benefitlist";
//        String backMethod = "benefitListBean"+ ".backFromJoinBudget";

//        Map savedDataMap = buildSavedDataObj();
        maintainBean.setSavedDataObj(savedDataObj);
        maintainBean.setHideAppModulesList(true);
        maintainBean.setTableName(tableName);
       
       maintainBean.setJoinedBefore(joinedBefore); 
       maintainBean.setNewCalcName(newCalcName);
        
        maintainBean.initPage(selectedElmGuidecode, tabricSerialRef, backNavCase, backMethod);       
        
        
        
        return maintainBean.PAGE_NAVIGATION_CASE;
       
    }    

    public void changeCalcType(ActionEvent ae) {
        searchCalcDTO = GrsDTOFactory.createSearchCalculationsDTO();
        if (calcType.equals(fixedValueCalcType)) {
            setMyTableData(null);
            getSelectedDTOS().clear();
            setSelectedRadio("");
            setValue(null);
            setNewCalcName(new String(savedNewCalcName));
        } else {
            repositionPage(0);
            search();
            setSearchMode(false);
        }

    }

    public void writeShowDivJScript() {
        StringBuilder jsCaller = new StringBuilder("changeVisibilityDiv(window.blocker,window.");
        jsCaller.append(getDivId()).append(");");
        getContainerBean().setJavaScriptCaller(jsCaller.toString());
    }

    public void resetData() {
        setPageDTO(GrsDTOFactory.createCalculationRelationsDTO());
        setShowJoinAlert(false);
        setJoinedBefore(false);
        setNewCalcName(null);
        setSavedNewCalcName(null);
        setValue(null);
        setCalcType(fixedValueCalcType);
        setSelectedRadio("");
        repositionPage(0);
        setExculdedCalcList(null);
        setMyTableData(null);
        if (getSelectedDTOS() != null)
            getSelectedDTOS().clear();
    }

    public void search() {
        List temp = new ArrayList(0);
        try {
            ICalculationsClient calculationsClient = GrsClientFactory.getCalculationsClient();
            if (exculdedCalcList != null && exculdedCalcList.length > 0) {
                searchCalcDTO.setExceptCalcsCode(exculdedCalcList);
            } else {
                Long dumyExcludCondition[] = new Long[1];
                dumyExcludCondition[0] = getVirtualLongValue();
                searchCalcDTO.setExceptCalcsCode(dumyExcludCondition);
            }
            searchCalcDTO.setSearchInStatus(searchInStatusList);
            Long moduleCode = getModuleCode();
            if (moduleCode != null)
                searchCalcDTO.setAppModuleCode(String.valueOf(moduleCode));
            temp = calculationsClient.searchForMer(searchCalcDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setMyTableData(temp);
        setSearchMode(true);
    }

    public void cancelSearch() {
        setSearchMode(false);
        setSearchCalcDTO(GrsDTOFactory.createSearchCalculationsDTO());
        setMyTableData(new ArrayList(0));
        search();
        setSearchMode(false);
    }
    
    public void viewCalculationDetails(ActionEvent actionEvent)
    {
        String calculationKey = (String)actionEvent.getComponent().getAttributes().get("calculationCode");
        constructCalculationDetailsPagePath(calculationKey);
    }

    public void constructCalculationDetailsPagePath(String calculationCode)
    {
        fullURL = CalculationDetailsBean.constructPageURL(calculationCode, isDisplayRelatedModules());
        System.out.println(fullURL);
    }

    private void setSearchInStatus(Long status) {
        if (searchInStatusList == null)
            searchInStatusList = new Long[1];
        searchInStatusList[0] = status;
    }

    public void setExculdedCalc(Long exculdedCalcCode) {
        if (exculdedCalcList == null)
            exculdedCalcList = new Long[1];
        exculdedCalcList[0] = exculdedCalcCode;
    }

    public void addToMyTableData(IBasicDTO iBasicDTO) {
        try {
            if (getMyDataTable() == null)
                setMyTableData(new ArrayList(1));

            getMyTableData().add(iBasicDTO);
        } catch (Exception e) {
            setMyTableData(null);
        }
    }

    public void scrollerAction(ActionEvent ae) {
        writeShowDivJScript();
    }

    public Long getModuleCode() {
        return CSCSecurityInfoHelper.getModuleCode();
    }


    public void getAll() throws DataBaseException {
    }

    public BaseBean getContainerBean() {
        return (BaseBean)JSFHelper.getValue(getContainerBeanName());
    }

    public void setContainerBeanName(String containerBeanName) {
        this.containerBeanName = containerBeanName;
    }

    public String getContainerBeanName() {
        return containerBeanName;
    }

    public void setDivId(String divId) {
        this.divId = divId;
    }

    public String getDivId() {
        return divId;
    }

    public void setShowJoinAlert(boolean showJoinAlert) {
        this.showJoinAlert = showJoinAlert;
    }

    public boolean isShowJoinAlert() {
        return showJoinAlert;
    }

    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    public String getCalcType() {
        return calcType;
    }

    public void setFixedValueCalcType(String fixedValueCalcType) {
        this.fixedValueCalcType = fixedValueCalcType;
    }

    public String getFixedValueCalcType() {
        return fixedValueCalcType;
    }

    public void setSelectCalcType(String selectCalcType) {
        this.selectCalcType = selectCalcType;
    }

    public String getSelectCalcType() {
        return selectCalcType;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setSearchCalcDTO(ISearchCalculationsDTO searchCalcDTO) {
        this.searchCalcDTO = searchCalcDTO;
    }

    public ISearchCalculationsDTO getSearchCalcDTO() {
        return searchCalcDTO;
    }

    public void setSearchMode(boolean searchMode) {
        this.searchMode = searchMode;
    }

    public boolean isSearchMode() {
        return searchMode;
    }

    public void setExculdedCalcList(Long[] exculdedCalcList) {
        this.exculdedCalcList = exculdedCalcList;
    }

    public Long[] getExculdedCalcList() {
        return exculdedCalcList;
    }

    public void setSearchInStatusList(Long[] searchInStatusList) {
        this.searchInStatusList = searchInStatusList;
    }

    public Long[] getSearchInStatusList() {
        return searchInStatusList;
    }

    public void setOperationSucceeded(boolean operationSucceeded) {
        this.operationSucceeded = operationSucceeded;
    }

    public boolean isOperationSucceeded() {
        return operationSucceeded;
    }

    public void setNewCalcName(String newCalcName) {
        this.newCalcName = newCalcName;
    }

    public String getNewCalcName() {
        return newCalcName;
    }

    public void setJoinedBefore(boolean joinedBefore) {
        this.joinedBefore = joinedBefore;
    }

    public boolean isJoinedBefore() {
        return joinedBefore;
    }

    public void setSavedNewCalcName(String savedNewCalcName) {
        this.savedNewCalcName = savedNewCalcName;
    }

    public String getSavedNewCalcName() {
        return savedNewCalcName;
    }

    public void setFullURL(String fullURL) {
        this.fullURL = fullURL;
    }

    public String getFullURL() {
        return fullURL;
    }

    public void setDisplayRelatedModules(boolean displayRelatedModules) {
        this.displayRelatedModules = displayRelatedModules;
    }

    public boolean isDisplayRelatedModules() {
        return displayRelatedModules;
    }


    public void setViewedValue(Double viewedValue) {
        this.viewedValue = viewedValue;
    }

    public Double getViewedValue() {
        return viewedValue;
    }

    public void setTabricSerialRef(Long tabricSerialRef) {
        this.tabricSerialRef = tabricSerialRef;
    }

    public Long getTabricSerialRef() {
        return tabricSerialRef;
    }

    public void setSelectedElmGuidecode(Long selectedElmGuidecode) {
        this.selectedElmGuidecode = selectedElmGuidecode;
    }

    public Long getSelectedElmGuidecode() {
        return selectedElmGuidecode;
    }

    public void setAddJoinCalcType(String addJoinCalcType) {
        this.addJoinCalcType = addJoinCalcType;
    }

    public String getAddJoinCalcType() {
        return addJoinCalcType;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
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
}
