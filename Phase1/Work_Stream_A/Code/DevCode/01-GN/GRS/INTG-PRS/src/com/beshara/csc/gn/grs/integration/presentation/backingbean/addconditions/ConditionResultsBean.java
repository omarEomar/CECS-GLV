package com.beshara.csc.gn.grs.integration.presentation.backingbean.addconditions;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.IConditionsClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionResultsResultDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionResultsSearchDTO;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.org.integration.presentation.backingbean.workcenters.WorkCentersHelperBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;


public class ConditionResultsBean extends LookUpBaseBean {
    @SuppressWarnings("compatibility:3824468067872675332")
    private static final long serialVersionUID = 1L;
    private static final String BEAN_NAME = "conditionIntgResultsBean";
    protected final static String PAGE_NAV_KEY = "condition_intg_results_page";
    private static final String BUNDLE_NAME = "com.beshara.csc.gn.grs.integration.presentation.resources.integration";
    protected final static String SEARCH_BY_CIVIL_ID = "1";
    protected final static String SEARCH_BY_BULK = "2";
    private String searchTypeFlag = SEARCH_BY_CIVIL_ID;
    private int allPassedCond = 0;
    private String civilId;
    private int linesListSize;
    private String selectedCategoryId = getVirtualConstValue();
    private String selectedMinistryId = getVirtualConstValue();
    private String selectedWorkCenterId = getVirtualConstValue();
    private String selectedCaderId = getVirtualConstValue();
    private String selectedGroupId = getVirtualConstValue();
    private String selectedDegreeId = getVirtualConstValue();
    private String selectedRaiseId = getVirtualConstValue();
    private String selectedBgtProgramId = getVirtualConstValue();
    private String selectedBgtTypeId = getVirtualConstValue();
    private String selectedCondId = getVirtualConstValue();
    private String selectedWorkCenterName;

    private List<IBasicDTO> categoriesList;
    private List<IBasicDTO> ministriesList;
    private List<IBasicDTO> caderList;
    private List<IBasicDTO> groupsList;
    private List<IBasicDTO> degreesList;
    private List<IBasicDTO> raisesList;
    private List<IBasicDTO> bgtProgramsList;
    private List<IBasicDTO> bgtTypesList;
    private List<IBasicDTO> condtionList;
    private List<IBasicDTO> linesList ;
    
    private final static String FILTER_BY_ALL = "-100";
    private final static String FILTER_BY_PASSED = "1";
    private final static String FILTER_BY_NOTPASSED = "0";
    
    private transient ConditionResultsCaller callerBeanObject;

    private List<IBasicDTO> conditionsList;
    private String conditionName="";

    WorkCentersTreeDivBean treepageDivBean;

    private String resultTableStyleClass = "dataT-With-3-row-filter-and-collapse";
    private WorkCentersHelperBean wcIntegrationBean;

    private String filterBy;    
    private String empName ;
    public ConditionResultsBean() {
        setPageDTO(GrsDTOFactory.createConditionsDTO());
        setSelectedPageDTO(GrsDTOFactory.createConditionsDTO());
        setClient(GrsClientFactory.getConditionsClient());

        treepageDivBean = WorkCentersTreeDivBean.getInstance();
        treepageDivBean.setShowTreeContent(true);
        treepageDivBean.setBundle(ResourceBundle.getBundle(BUNDLE_NAME,
                                                           new Locale(((SharedUtilBean)JSFHelper.getSession().getAttribute(SharedUtilBean.BEAN_NAME)).getLocale())));
        treepageDivBean.setRootName("WorkCenterTitle");
        treepageDivBean.setClient(OrgClientFactory.getWorkCentersClient());
        treepageDivBean.setPageDTO(OrgDTOFactory.createWorkCentersDTO());
        treepageDivBean.setDto(OrgDTOFactory.createWorkCentersDTO());
        treepageDivBean.setDtoDetails(OrgDTOFactory.createWorkCentersDTO());
        treepageDivBean.setEnableSearchByCode(true);
        treepageDivBean.setKeyIndex(1);
        initIntegration(); 
    }

    public static ConditionResultsBean getInstance() {
        return (ConditionResultsBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowNavigation(false);
        app.setShowSearch(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowContent1(true);
        app.setShowTreediv(true);
        app.setShowIntegrationDiv1(true);
        app.setShowCustomDiv1(true);
        return app;
    }

    public String getSearchByCivilIdFlag() {
        return SEARCH_BY_CIVIL_ID;
    }

    public String getSearchByBulkFlag() {
        return SEARCH_BY_BULK;
    }

    public void searchTypeChanged(ActionEvent e) {
        if (searchTypeFlag.equals(SEARCH_BY_CIVIL_ID)) {
            setResultTableStyleClass("dataT-With-4-row-filter-and-collapse");
        } else {
            setResultTableStyleClass("dataT-With-6-row-filter-and-collapse");
        }
        try {
            cancelSearch();
        } catch (DataBaseException f) {
            f.printStackTrace();
        }
    }

    public List getTotalList() {
        return new ArrayList();
    }

    public List getBaseSearchResult() throws DataBaseException {
        List searchResult = new ArrayList(0);
        try {
            IConditionResultsSearchDTO searchDTO = GrsDTOFactory.createConditionResultsSearchDTO();
            if (searchTypeFlag.equals(SEARCH_BY_CIVIL_ID)) {
                if (getCivilId() != null && getCivilId().length() != 0) {
                    searchDTO.setCivilId(Long.parseLong(getCivilId()));
                } else {
                    searchDTO.setCivilId(null);
                }
            } else {
                if (!getVirtualConstValue().equals(getSelectedWorkCenterId())) {
                    searchDTO.setWorkCenterCode(getSelectedWorkCenterId());
                } else if (!getVirtualConstValue().equals(getSelectedMinistryId())) {
                    searchDTO.setMinistryCode(Long.parseLong(getSelectedMinistryId()));
                }

                if (!getVirtualConstValue().equals(getSelectedDegreeId())) {
                    searchDTO.setDegreeCode(Long.parseLong(getSelectedDegreeId()));
                } else if (!getVirtualConstValue().equals(getSelectedGroupId())) {
                    searchDTO.setGroupCode(Long.parseLong(getSelectedGroupId()));
                } else if (!getVirtualConstValue().equals(getSelectedCaderId())) {
                    searchDTO.setCaderCode(Long.parseLong(getSelectedCaderId()));
                }

                if (!getVirtualConstValue().equals(getSelectedBgtTypeId())) {
                    searchDTO.setBgtTypesCode(Long.parseLong(getSelectedBgtTypeId()));
                }
                if (!getVirtualConstValue().equals(getSelectedCondId())) {
                    searchDTO.setConditionCode(Long.valueOf(getSelectedCondId()));
                }
            }
            //searchResult = ((IConditionsClient)getClient()).getConditionResults(conditionsList, searchDTO);
            allPassedCond = 0;
            
            if(getFilterBy().equals(FILTER_BY_ALL)){
                searchResult = ((IConditionsClient)getClient()).getConditionResults_Enhanced(conditionsList, searchDTO);
            }else if(getFilterBy().equals(FILTER_BY_PASSED)){
                searchResult = ((IConditionsClient)getClient()).getConditionResults_EnhancedByPass(conditionsList, searchDTO,true);
                
            }else  if(getFilterBy().equals(FILTER_BY_NOTPASSED)){
                searchResult = ((IConditionsClient)getClient()).getConditionResults_EnhancedByPass(conditionsList, searchDTO,false);
            }
            
            for (int index = 0; index < searchResult.size(); index++)
                allPassedCond += ((IConditionResultsResultDTO)searchResult.get(index)).getPassAllConditions();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    public void cancelSearch() throws DataBaseException {
        super.cancelSearch();
        setCivilId(null);
        setSelectedCategoryId(getVirtualConstValue());
        setSelectedMinistryId(getVirtualConstValue());
        setSelectedWorkCenterId(getVirtualConstValue());
        setSelectedWorkCenterName(null);
        setSelectedCaderId(getVirtualConstValue());
        setSelectedGroupId(getVirtualConstValue());
        setSelectedDegreeId(getVirtualConstValue());
        setSelectedRaiseId(getVirtualConstValue());
        setSelectedBgtProgramId(getVirtualConstValue());
        setSelectedBgtTypeId(getVirtualConstValue());
        setFilterBy(getVirtualConstValue());
        setMinistriesList(null);
        setGroupsList(null);
        setDegreesList(null);
        setRaisesList(null);

    }

    public String back() {
        //        callerBeanObject.restore(callerBeanObject);
        //        return callerBeanObject.getBackNavigationKey();
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        return integrationBean.goFrom();
    }

    public void selectedCategoryChanged(ActionEvent ae) {
        System.out.println("Inside conditionIntgResultsBean.selectedCategoryChanged" + ae);
        setSelectedMinistryId(getVirtualConstValue());
        setMinistriesList(null);
        setSelectedWorkCenterId(getVirtualConstValue());
        setSelectedWorkCenterName(null);
    }

    public void selectedMinistryChanged(ActionEvent ae) {
        System.out.println("Inside conditionIntgResultsBean.selectedMinistryChanged" + ae);
        setSelectedWorkCenterId(getVirtualConstValue());
        setSelectedWorkCenterName(null);
    }

    public void selectedCaderChanged(ActionEvent ae) {
        System.out.println("Inside conditionIntgResultsBean.selectedCaderChanged" + ae);
        setSelectedGroupId(getVirtualConstValue());
        setGroupsList(null);
        setSelectedDegreeId(getVirtualConstValue());
        setDegreesList(null);
        setSelectedRaiseId(getVirtualConstValue());
        setRaisesList(null);
    }

    public void selectedGroupChanged(ActionEvent ae) {
        System.out.println("Inside conditionIntgResultsBean.selectedGroupChanged" + ae);
        setSelectedDegreeId(getVirtualConstValue());
        setDegreesList(null);
        setSelectedRaiseId(getVirtualConstValue());
        setRaisesList(null);
    }

    public void selectedDegreeChanged(ActionEvent ae) {
        System.out.println("Inside conditionIntgResultsBean.selectedDegreeChanged" + ae);
        setSelectedRaiseId(getVirtualConstValue());
        setRaisesList(null);
    }

    public void openWorkCentersTreeDiv() {
        try {
            treepageDivBean.setMinCode(selectedMinistryId);
            treepageDivBean.setSearchMode(false);
            treepageDivBean.cancelSearchTree();
            treepageDivBean.setShowTreeContent(true);
            treepageDivBean.setSearchQuery("");
            treepageDivBean.setSerachResult(false);
            treepageDivBean.setSearchType(0);
            treepageDivBean.setDtoDetails(OrgDTOFactory.createWorkCentersDTO());
            MethodBinding mb = JSFHelper.getMethodBinding(BEAN_NAME + ".updateSelectedWorkCenter");
            treepageDivBean.getOkCommandButton().setAction(mb);
            ValueBinding vb =
                JSFHelper.getValueBinding("(!" + WorkCentersTreeDivBean.BEAN_NAME + ".enabledRoot )&& (" +
                                          WorkCentersTreeDivBean.BEAN_NAME + ".selectionNo==1)");
            treepageDivBean.getOkCommandButton().setValueBinding("rendered", vb);
            treepageDivBean.getOkCommandButton().setReRender("selectedWorkCenter, myForm:conditionResultsErrMsg");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSelectedWorkCenter() {
        if (!treepageDivBean.getDtoDetails().getName().equals("")) { // if Div is opened either in add or edit mode
            selectedWorkCenterId = treepageDivBean.getDtoDetails().getCode().getKey();
            selectedWorkCenterName = treepageDivBean.getDtoDetails().getName();
        }
    }

    public void setCategoriesList(List<IBasicDTO> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public List<IBasicDTO> getCategoriesList() {
        if (categoriesList == null) {
            try {
                categoriesList =
                        OrgClientFactory.getCatsClient().getCodeNameByGovFlag(ISystemConstant.GOVERNMENT_FLAG);
            } catch (DataBaseException e) {
                e.printStackTrace();
                categoriesList = new ArrayList<IBasicDTO>(0);
            }
        }
        return categoriesList;
    }

    public void setMinistriesList(List<IBasicDTO> ministriesList) {
        this.ministriesList = ministriesList;
    }

    public List<IBasicDTO> getMinistriesList() {
        if (ministriesList == null && !getVirtualConstValue().equals(selectedCategoryId)) {
            try {
                //ministriesList = OrgClientFactory.getMinistriesClient().getAllByCategory(Long.valueOf(selectedCategoryId));
                ministriesList =
                        OrgClientFactory.getMinistriesClient().getAllMinistriesByCategoryLeaves(Long.valueOf(selectedCategoryId));
                //String char_label = getSharedUtil().messageLocator(BUNDLE_NAME, "blank");
                //ministriesList = buildMinistryTree(newList, char_label);
            } catch (Exception e) {
                e.printStackTrace();
                ministriesList = new ArrayList<IBasicDTO>(0);
            }
        }
        return ministriesList;
    }

    public void setCaderList(List<IBasicDTO> caderList) {
        this.caderList = caderList;
    }

    public List<IBasicDTO> getCaderList() {
        if (caderList == null) {
            try {
                caderList = ((IConditionsClient)getClient()).getSalCaderCodeName();
            } catch (Exception e) {
                e.printStackTrace();
                caderList = new ArrayList<IBasicDTO>(0);
            }
        }
        return caderList;
    }

    public void setGroupsList(List<IBasicDTO> groupsList) {
        this.groupsList = groupsList;
    }

    public List<IBasicDTO> getGroupsList() {
        if (groupsList == null && !getVirtualConstValue().equals(selectedCaderId)) {
            try {
                groupsList = ((IConditionsClient)getClient()).getSalGroupsCodeName(Long.valueOf(selectedCaderId));
            } catch (Exception e) {
                e.printStackTrace();
                groupsList = new ArrayList<IBasicDTO>(0);
            }
        }
        return groupsList;
    }

    public void setDegreesList(List<IBasicDTO> degreesList) {
        this.degreesList = degreesList;
    }

    public List<IBasicDTO> getDegreesList() {
        if (degreesList == null && !getVirtualConstValue().equals(selectedGroupId)) {
            try {
                degreesList = ((IConditionsClient)getClient()).getSalDegreesCodeName(Long.valueOf(selectedGroupId));
            } catch (Exception e) {
                e.printStackTrace();
                degreesList = new ArrayList<IBasicDTO>(0);
            }
        }
        return degreesList;
    }

    public void setRaisesList(List<IBasicDTO> raisesList) {
        this.raisesList = raisesList;
    }

    public List<IBasicDTO> getRaisesList() {
        if (raisesList == null && !getVirtualConstValue().equals(selectedDegreeId)) {
            try {
                raisesList = ((IConditionsClient)getClient()).getSalRaisesCodeName(Long.valueOf(selectedDegreeId));
            } catch (Exception e) {
                e.printStackTrace();
                raisesList = new ArrayList<IBasicDTO>(0);
            }
        }
        return raisesList;
    }

    public void setBgtProgramsList(List<IBasicDTO> bgtProgramsList) {
        this.bgtProgramsList = bgtProgramsList;
    }

    public List<IBasicDTO> getBgtProgramsList() {
            try {
                if(!getSelectedMinistryId().equals(getVirtualConstValue())){
                    Long minCode=Long.valueOf(getSelectedMinistryId());
                    bgtProgramsList = ((IConditionsClient)getClient()).getBgtProgramsCodeByMinCode(minCode);
                }else{
                bgtProgramsList = ((IConditionsClient)getClient()).getBgtProgramsCodeName();
                }
            } catch (Exception e) {
                e.printStackTrace();
                bgtProgramsList = new ArrayList<IBasicDTO>(0);
           
        }
        return bgtProgramsList;
    }

    public void setBgtTypesList(List<IBasicDTO> bgtTypesList) {
        this.bgtTypesList = bgtTypesList;
    }

    public List<IBasicDTO> getBgtTypesList() {
        if (bgtTypesList == null) {
            try {
                bgtTypesList = ((IConditionsClient)getClient()).getBgtTypesCodeName();
            } catch (Exception e) {
                e.printStackTrace();
                bgtTypesList = new ArrayList<IBasicDTO>(0);
            }
        }
        return bgtTypesList;
    }

    public void setSelectedCategoryId(String selectedCategoryId) {
        this.selectedCategoryId = selectedCategoryId;
    }

    public String getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public void setSelectedMinistryId(String selectedMinistryId) {
        this.selectedMinistryId = selectedMinistryId;
    }

    public String getSelectedMinistryId() {
        return selectedMinistryId;
    }

    public void setSelectedWorkCenterId(String selectedWorkCenterId) {
        this.selectedWorkCenterId = selectedWorkCenterId;
    }

    public String getSelectedWorkCenterId() {
        return selectedWorkCenterId;
    }

    public void setSelectedWorkCenterName(String selectedWorkCenterName) {
        this.selectedWorkCenterName = selectedWorkCenterName;
    }

    public String getSelectedWorkCenterName() {
        return selectedWorkCenterName;
    }

    public void setSelectedCaderId(String selectedCaderId) {
        this.selectedCaderId = selectedCaderId;
    }

    public String getSelectedCaderId() {
        return selectedCaderId;
    }

    public void setSelectedGroupId(String selectedGroupId) {
        this.selectedGroupId = selectedGroupId;
    }

    public String getSelectedGroupId() {
        return selectedGroupId;
    }

    public void setSelectedDegreeId(String selectedDegreeId) {
        this.selectedDegreeId = selectedDegreeId;
    }

    public String getSelectedDegreeId() {
        return selectedDegreeId;
    }

    public void setSelectedRaiseId(String selectedRaiseId) {
        this.selectedRaiseId = selectedRaiseId;
    }

    public String getSelectedRaiseId() {
        return selectedRaiseId;
    }

    public void setSelectedBgtProgramId(String selectedBgtProgramId) {
        this.selectedBgtProgramId = selectedBgtProgramId;
    }

    public String getSelectedBgtProgramId() {
        return selectedBgtProgramId;
    }

    public void setSelectedBgtTypeId(String selectedBgtTypeId) {
        this.selectedBgtTypeId = selectedBgtTypeId;
    }

    public String getSelectedBgtTypeId() {
        return selectedBgtTypeId;
    }

    public void setConditionsList(List<IBasicDTO> conditionsList) {
        this.conditionsList = conditionsList;
    }

    public List<IBasicDTO> getConditionsList() {
        return conditionsList;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setCallerBeanObject(ConditionResultsCaller callerBeanObject) {
        this.callerBeanObject = callerBeanObject;
    }

    public ConditionResultsCaller getCallerBeanObject() {
        return callerBeanObject;
    }

    public void setSearchTypeFlag(String searchTypeFlag) {
        this.searchTypeFlag = searchTypeFlag;
    }

    public String getSearchTypeFlag() {
        return searchTypeFlag;
    }

    public void setResultTableStyleClass(String resultTableStyleClass) {
        this.resultTableStyleClass = resultTableStyleClass;
    }

    public String getResultTableStyleClass() {
        return resultTableStyleClass;
    }

    public void setWcIntegrationBean(WorkCentersHelperBean wcIntegrationBean) {
        this.wcIntegrationBean = wcIntegrationBean;
    }

    public WorkCentersHelperBean getWcIntegrationBean() {
        return wcIntegrationBean;
    }

    public void initIntegration() {
        wcIntegrationBean = new WorkCentersHelperBean(BEAN_NAME, "integrationDiv1", false, false, true, true, null);
        wcIntegrationBean.getOkCommandButton().setReRender("workcenters,searchingPnl,pageStyleClass,selectedWorkCenterName,paging_panel,resultsPnl,dataT_data_panel");
        wcIntegrationBean.setReturnMethodName(BEAN_NAME + ".linkWorkCenter");
        wcIntegrationBean.setPreOpenMethodName(BEAN_NAME + ".openSearchWorkCenter");
        wcIntegrationBean.setSingleSelectionFlag(true);
    }

    public void linkWorkCenter() {
        List<IWorkCentersDTO> linkedWrkCenterlist = new ArrayList<IWorkCentersDTO>(0);
        if (wcIntegrationBean.getSelectedDTOSList() != null && wcIntegrationBean.getSelectedDTOSList().size() != 0) {
            for (IBasicDTO dto : wcIntegrationBean.getSelectedDTOSList()) {
                IWorkCentersDTO minDTO = ((IWorkCentersDTO)dto);
                minDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
                linkedWrkCenterlist.add(minDTO);
            }
            if (wcIntegrationBean.getMinistryDTO().getWorkCentersDTOList() != null &&
                wcIntegrationBean.getMinistryDTO().getWorkCentersDTOList().size() != 0) {
                wcIntegrationBean.getMinistryDTO().getWorkCentersDTOList().addAll(linkedWrkCenterlist);
                wcIntegrationBean.getMinistryDTO().setWorkCentersListSize(wcIntegrationBean.getMinistryDTO().getWorkCentersDTOList().size());
            } else {
                wcIntegrationBean.getMinistryDTO().setWorkCentersDTOList(linkedWrkCenterlist);
                wcIntegrationBean.getMinistryDTO().setWorkCentersListSize(wcIntegrationBean.getMinistryDTO().getWorkCentersDTOList().size());
            }
            // wcIntegrationBean.setExecludedList(wcIntegrationBean.getMinistryDTO().getWorkCentersDTOList());
            selectedWorkCenterId = wcIntegrationBean.getSelectedDTOSList().get(0).getCode().getKey();
            selectedWorkCenterName = wcIntegrationBean.getSelectedDTOSList().get(0).getName();
        }
    }

    public void openSearchWorkCenter() {
        try {
            IMinistriesDTO dto = null;
            List<IWorkCentersDTO> exList = new ArrayList<IWorkCentersDTO>();
            if (selectedMinistryId != null && !selectedMinistryId.equals(getVirtualConstValue())) {
                dto =
(IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(OrgEntityKeyFactory.createMinistriesEntityKey(Long.valueOf(selectedMinistryId)));
                wcIntegrationBean.setMinistryDTO(dto);
                if (dto.getWorkCentersDTOList() != null && dto.getWorkCentersDTOList().size() != 0) {
                    for (IWorkCentersDTO wrkDTO : dto.getWorkCentersDTOList()) {
                        if (wrkDTO != null)
                            exList.add(wrkDTO);
                    }
                    wcIntegrationBean.setExecludedList(exList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
   //added by 3laa.elamsry at 29/12/2014
    public void showLineDiv(ActionEvent event){
     //           List<ILinesResultDTO> list = new ArrayList<ILinesResultDTO>();
                String civilId = (String)event.getComponent().getAttributes().get("civilIdParam");
                String conditionCode = (String)event.getComponent().getAttributes().get("conditionCodeParam");
                String employeeName  =(String)event.getComponent().getAttributes().get("employeeNameParam");
                try{
                    Long condCode = Long.valueOf(conditionCode);
                    linesList =GrsClientFactory.getLinesClient().getLinesAndActualValue(condCode , civilId);
                }catch(Exception e){
                    linesList = new ArrayList<IBasicDTO>();
                   e.printStackTrace();
                }
                setEmpName(employeeName);
                setCivilId(civilId);
                System.out.println(linesList.size());
                System.out.println("<<<<<<<<<<"+civilId+">>>>>>>>>>>"+conditionCode +">>>>>>>>>>");
                setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
        
    }
    
    public void setCondtionList(List<IBasicDTO> condtionList) {
        this.condtionList = condtionList;
    }

    public List<IBasicDTO> getCondtionList() {
        if (condtionList == null) {
            try {
                condtionList = ((IConditionsClient)getClient()).getAllUsingAsedAsFilter();
            } catch (DataBaseException e) {
                e.printStackTrace();
                condtionList = new ArrayList<IBasicDTO>(0);
            }
        }
        return condtionList;
    }

    public void setSelectedCondId(String selectedCondId) {
        this.selectedCondId = selectedCondId;
    }

    public String getSelectedCondId() {
        return selectedCondId;
    }

    public void filterByCategory(ActionEvent ae) {

    }

    public void setAllPassedCond(int allPassedCond) {
        this.allPassedCond = allPassedCond;
    }

    public int getAllPassedCond() {
        return allPassedCond;
    }

    public void setFilterBy(String filterBy) {
        this.filterBy = filterBy;
    }

    public String getFilterBy() {
        return filterBy;
    }
    public String getPassConditionValue(){
        return FILTER_BY_PASSED;
    }
    public String getNonPassConditionValue(){
        return FILTER_BY_NOTPASSED;
    }

    public void setLinesList(List<IBasicDTO> linesList) {
        this.linesList = linesList;
    }

    public List<IBasicDTO> getLinesList() {
        return linesList;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setLinesListSize(int linesListSize) {
        this.linesListSize = linesListSize;
    }

    public int getLinesListSize() {
        if(linesList!= null)
            return this.linesList.size();
        else
            return 0;
    }
}
