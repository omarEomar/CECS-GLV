package com.beshara.csc.nl.qul.integration.presentation.backingbean.searchqul;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.nl.qul.business.client.QulClientFactory;
import com.beshara.csc.nl.qul.business.dto.IQualificationsSearchCriteriaDTO;
import com.beshara.csc.nl.qul.business.dto.QulDTOFactory;
import com.beshara.csc.nl.qul.business.entity.QulEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.event.ActionEvent;


import org.apache.myfaces.component.html.ext.HtmlOutputText;
import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


public class QualificationHelperBean extends LookUpBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private static final String BUNDLE_NAME =
        "com.beshara.csc.nl.qul.integration.presentation.resources.qulintegration";
    public static final String INITIAL_FIELD_VALUE_ZERO = "0";
    public static final String INITIAL_FIELD_VALUE_EMPTY = "";
    public static final String DISPAY_NONE = "display:none;";
    public static final String DISPAY_BLOCK = "display:block;";
    public static final int CODE_MAX_LENGHT = 3;
    public static final String VIEW_DATA = "button_view";
    public static final String RESET_VAL = "reset";

    private Boolean filterPnlCollapsed = false;
    private Boolean resetMode = false;
    private int codeMaxLength = CODE_MAX_LENGHT;
    private String showMsg = DISPAY_NONE;
    private transient HtmlOutputText errorMsgBind;
    private boolean showQulFieldValError;
    private String buttonValue = VIEW_DATA;
    private boolean disableSearchBtn = true;
    private List<String> excludedQulList = new ArrayList<String>();
    private String okButtonMethod;
    private static HashMap<String, String> availablejobMap = new HashMap<String, String>();

    private String qulKey = null;
    private String qulName = null;
    private String mainEduLevelCode = INITIAL_FIELD_VALUE_EMPTY;
    private String selectedmainEduLevel = INITIAL_FIELD_VALUE_EMPTY;
    private String levelChildCode = INITIAL_FIELD_VALUE_EMPTY;
    private String selectedLevelChild = INITIAL_FIELD_VALUE_EMPTY;
    private String generalSpecsCode = INITIAL_FIELD_VALUE_EMPTY;
    private String selectedGeneralSpecs = INITIAL_FIELD_VALUE_EMPTY;
    private String majorSpecCode = INITIAL_FIELD_VALUE_EMPTY;
    private String selectedMajorSpec = INITIAL_FIELD_VALUE_EMPTY;
    private String selectedMinorSpecs = INITIAL_FIELD_VALUE_EMPTY;
    private String minorSpecsCode = INITIAL_FIELD_VALUE_EMPTY;
    
    private String containerBeanName;
    private String divId = "integrationDiv1";
    
    private List<IBasicDTO> mainEduLevels;
    private List<IBasicDTO> levelChildList;
    private List<IBasicDTO> generalSpecsList;
    private List<IBasicDTO> majorSpecsList;
    private List<IBasicDTO> minorSpecsList;
    IQualificationsSearchCriteriaDTO searchQualDTO;
    private transient HtmlDataScroller dataScroller = new HtmlDataScroller();
    public QualificationHelperBean() {
        setPageDTO(QulDTOFactory.createQualificationsDTO());
        setClient(QulClientFactory.getQualificationsClient());
        setUsingPaging(true);
        setUsingBsnPaging(true);
        setSaveSortingState(true);
    }
    public void initiateBeanValues() {
        try {
            fillMainEduLevels();
            fillLevelChildList();
            fillGeneralSpecsList();
            fillMajorSpecsList();
            fillMinorSpecsList();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void fillTable() {
        if (isUsingPaging()) {
            resetPageIndex();
            setUpdateMyPagedDataModel(true);
            setPagingRequestDTO(new PagingRequestDTO("baseSearchQualWithPaging"));
        }
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO pagingRequestDTO) {
        return new PagingResponseDTO(new ArrayList());
    }

    public PagingResponseDTO baseSearchQualWithPaging(PagingRequestDTO pagingRequest) {
        IPagingResponseDTO bsnResponseDTO = getQualDataWithPaging(pagingRequest);
        PagingResponseDTO pagingResponseDTO = null;
        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());
            if (getCurrentPageIndex() == 1) {
                pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                getPagingRequestDTO().setParams(new Object[] { bsnResponseDTO.getCount() });
            } else if (pagingRequest != null && pagingRequest.getParams() != null) {
                pagingResponseDTO.setTotalListSize(((Long)pagingRequest.getParams()[0]).intValue());
            }
        }
        return pagingResponseDTO;
    }

    private com.beshara.base.paging.impl.PagingResponseDTO getQualDataWithPaging(PagingRequestDTO pagingRequestDTO) {
        System.out.println(pagingRequestDTO == null);
        int pageIndex = getCurrentPageIndex();
        com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO =
            new com.beshara.base.paging.impl.PagingRequestDTO();
        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));
        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));
        if (pageIndex == 1) {
            bsnPagingRequestDTO.setCountRequired(true);
        }
        if (isSorting() && getBsnSortingColumnName() != null) {
            bsnPagingRequestDTO.setSortAscending(isSortAscending());
            List<String> sortingColumnList = new ArrayList<String>();
            sortingColumnList.add(getBsnSortingColumnName());
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }
        com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;
        try {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            setSearchQualDTO(composeSearchDTO());
            searchQualDTO.setPagingRequestDTO(bsnPagingRequestDTO);
            bsnPagingResponseDTO =
                    (com.beshara.base.paging.impl.PagingResponseDTO)QulClientFactory.getQualificationsClient().searchQualificationsWithPaging(searchQualDTO);
        } catch (NoResultException ne) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (SharedApplicationException e) {
            //getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            //  getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (Throwable e) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        }
        return bsnPagingResponseDTO;
    }

    public void cancelSearch() throws DataBaseException {
        setSearchMode(false);
        resetValues();
        initiateBeanValues();
        fillTable();
    }
    public void search() throws DataBaseException, NoResultException {
        setSearchMode(true);
        fillTable();
    }

    

    public void showHideFilterPnl(){
        if(filterPnlCollapsed){
            filterPnlCollapsed = false;
        } else {
            filterPnlCollapsed = true;
        }
    }
    
    private IQualificationsSearchCriteriaDTO composeSearchDTO() {
        IQualificationsSearchCriteriaDTO qualificationsSearchCriteriaDTO =
            QulDTOFactory.createQualificationsSearchCriteriaDTO();
        if (getQulKey() != null && !getQulKey().equalsIgnoreCase("")) {
            qualificationsSearchCriteriaDTO.setQualificationDtlCode(getQulKey());
        }
        if (getQulName() != null && !getQulName().equalsIgnoreCase("")) {
            qualificationsSearchCriteriaDTO.setName(getQulName());
        }
        if (getLevelChildCode() != null && !getLevelChildCode().equalsIgnoreCase("")) {
            qualificationsSearchCriteriaDTO.setEduLevelCode(Long.valueOf(getLevelChildCode()));
        } else if (getMainEduLevelCode() != null && !getMainEduLevelCode().equalsIgnoreCase("")) {
            qualificationsSearchCriteriaDTO.setMajorEduLevelCode(Long.valueOf(getMainEduLevelCode()));
        }
        if (getGeneralSpecsCode() != null && !getGeneralSpecsCode().equalsIgnoreCase("")) {
            qualificationsSearchCriteriaDTO.setGenSpecsCode(Long.valueOf(getGeneralSpecsCode()));
        }
        if (getMajorSpecCode() != null && !getMajorSpecCode().equalsIgnoreCase("")) {
            qualificationsSearchCriteriaDTO.setMajorSpecsCode(Long.valueOf(getMajorSpecCode()));
        }
        if (getMinorSpecsCode() != null && !getMinorSpecsCode().equalsIgnoreCase("")) {
            qualificationsSearchCriteriaDTO.setMinorSpecsCode(Long.valueOf(getMinorSpecsCode()));
        }
        if (getExcludedQulList() != null) {
            qualificationsSearchCriteriaDTO.setExcludedQulCodeList(getExcludedQulList());
        }
    
        return qualificationsSearchCriteriaDTO;
    }

    protected boolean isValidString(String strValue) {
        return (strValue != null && !strValue.trim().equals(INITIAL_FIELD_VALUE_EMPTY) &&
                !strValue.trim().equals(INITIAL_FIELD_VALUE_ZERO));
    }

    protected void showError(String compontntMsg) {
        setShowMsg(DISPAY_BLOCK);
        errorMsgBind.setValue(compontntMsg);
    }

    public void addQualifications() {
        if (getOkButtonMethod() != null) {
            JSFHelper.callMethod(getOkButtonMethod());
        }
        resetValues();
    }

    public void back() throws DataBaseException {
        //cancelSearch();
        resetValues();
        setButtonValue(VIEW_DATA);
        setMyTableData(new ArrayList<IBasicDTO>());
        reIntializeMsgs();
    }

    public void resetValues() {
        qulKey = null;
        qulName = null;
        mainEduLevelCode = INITIAL_FIELD_VALUE_EMPTY;
        selectedmainEduLevel = INITIAL_FIELD_VALUE_EMPTY;
        levelChildCode = INITIAL_FIELD_VALUE_EMPTY;
        selectedLevelChild = INITIAL_FIELD_VALUE_EMPTY;
        generalSpecsCode = INITIAL_FIELD_VALUE_EMPTY;
        selectedGeneralSpecs = INITIAL_FIELD_VALUE_EMPTY;
        selectedMajorSpec = INITIAL_FIELD_VALUE_EMPTY;
        majorSpecCode = INITIAL_FIELD_VALUE_EMPTY;
        selectedMinorSpecs = INITIAL_FIELD_VALUE_EMPTY;
        minorSpecsCode = INITIAL_FIELD_VALUE_EMPTY;
        setSearchMode(false);
        setSelectedRadio("");
        if (getSelectedDTOS() != null)
            getSelectedDTOS().clear();
    }

    public void mainEduLevelCodeChanged() throws DataBaseException, SharedApplicationException {
        System.out.println("mainEduLevelCodeChanged for input fired");
        levelChildCode = INITIAL_FIELD_VALUE_EMPTY;
        selectedLevelChild = INITIAL_FIELD_VALUE_EMPTY;
        generalSpecsCode = INITIAL_FIELD_VALUE_EMPTY;
        selectedGeneralSpecs = INITIAL_FIELD_VALUE_EMPTY;
        selectedMajorSpec = INITIAL_FIELD_VALUE_EMPTY;
        majorSpecCode = INITIAL_FIELD_VALUE_EMPTY;
        selectedMinorSpecs = INITIAL_FIELD_VALUE_EMPTY;
        minorSpecsCode = INITIAL_FIELD_VALUE_EMPTY;
        fillLevelChildList();
    }

    public void levelChildValChanged() throws DataBaseException, SharedApplicationException {
        System.out.println("levelChildValChanged for input fired");
        generalSpecsCode = INITIAL_FIELD_VALUE_EMPTY;
        selectedGeneralSpecs = INITIAL_FIELD_VALUE_EMPTY;
        selectedMajorSpec = INITIAL_FIELD_VALUE_EMPTY;
        majorSpecCode = INITIAL_FIELD_VALUE_EMPTY;
        selectedMinorSpecs = INITIAL_FIELD_VALUE_EMPTY;
        minorSpecsCode = INITIAL_FIELD_VALUE_EMPTY;
        fillGeneralSpecsList();
        fillMajorSpecsList();
        fillMinorSpecsList();
    }

    public void generalSpecsCodeChanged() throws DataBaseException, SharedApplicationException {
        System.out.println("generalSpecsCodeChanged for input fired");
        selectedMajorSpec = INITIAL_FIELD_VALUE_EMPTY;
        majorSpecCode = INITIAL_FIELD_VALUE_EMPTY;
        selectedMinorSpecs = INITIAL_FIELD_VALUE_EMPTY;
        minorSpecsCode = INITIAL_FIELD_VALUE_EMPTY;
        fillMajorSpecsList();
        fillMinorSpecsList();
    }

    public void majorSpecsCodeChanged() throws DataBaseException {
        System.out.println("generalSpecsCodeChanged for input fired");
    }

    public void minorSpecsCodeChanged() throws DataBaseException {
        System.out.println("minorSpecsCodeChanged for input fired");
    }

    public void fillMainEduLevels() throws SharedApplicationException {
        try {
            mainEduLevels = QulClientFactory.getEducationLevelsClient().getAllParentsCodeName();
        } catch (DataBaseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fillLevelChildList() throws SharedApplicationException {
        if (this.getSelectedmainEduLevel() != null && !this.getSelectedmainEduLevel().equals("")) {
            try {
                levelChildList =
                        QulClientFactory.getEducationLevelsClient().getChildrenListCodeName(QulEntityKeyFactory.createEducationLevelsEntityKey(Long.valueOf(selectedmainEduLevel)));

            } catch (DataBaseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void fillGeneralSpecsList() throws SharedApplicationException {
        try {
            if (this.selectedLevelChild.equals("")) {
                generalSpecsList = QulClientFactory.getGeneralSpecsClient().getAll();
            } else {
                generalSpecsList =
                        QulClientFactory.getGeneralSpecsClient().getGeneralSpecsTreeNodes(Long.valueOf(selectedLevelChild),
                                                                                          null, null);
            }
        } catch (DataBaseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fillMajorSpecsList() throws SharedApplicationException {
        try {
            if (!this.generalSpecsCode.equals("")) {
                majorSpecsList =
                        QulClientFactory.getSpecsClient().getAllByGenSpecs(Long.valueOf(this.generalSpecsCode));
            } else if (!this.levelChildCode.equals("")) {
                majorSpecsList = QulClientFactory.getSpecsClient().getAllByEduLevel(Long.valueOf(levelChildCode));
            } else {
                majorSpecsList = QulClientFactory.getSpecsClient().getAll();
            }
        } catch (DataBaseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fillMinorSpecsList() throws SharedApplicationException {
        try {
            if (!this.generalSpecsCode.equals("")) {
                minorSpecsList =
                        QulClientFactory.getSpecsClient().getAllByGenSpecs(Long.valueOf(this.generalSpecsCode));
            } else if (!this.levelChildCode.equals("")) {
                minorSpecsList = QulClientFactory.getSpecsClient().getAllByEduLevel(Long.valueOf(levelChildCode));
            } else {
                minorSpecsList = QulClientFactory.getSpecsClient().getAll();
            }
        } catch (DataBaseException e) {
            System.out.println(e.getMessage());
        }
    }
    

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public void setErrorMsgBind(HtmlOutputText errorMsgBind) {
        this.errorMsgBind = errorMsgBind;
    }

    public HtmlOutputText getErrorMsgBind() {
        return errorMsgBind;
    }

    public void setShowQulFieldValError(boolean showQulFieldValError) {
        this.showQulFieldValError = showQulFieldValError;
    }

    public boolean isShowQulFieldValError() {
        return showQulFieldValError;
    }

    public void setQulKey(String qulKey) {
        this.qulKey = qulKey;
    }

    public String getQulKey() {
        return qulKey;
    }

    public void setQulName(String qulName) {
        this.qulName = qulName;
    }

    public String getQulName() {
        return qulName;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public void setDisableSearchBtn(boolean disableSearchBtn) {
        this.disableSearchBtn = disableSearchBtn;
    }

    public boolean isDisableSearchBtn() {
        return disableSearchBtn;
    }

    public void setExcludedQulList(List<String> excludedQulList) {
        this.excludedQulList = excludedQulList;
    }

    public List<String> getExcludedQulList() {
        return excludedQulList;
    }

    public void setOkButtonMethod(String okButtonMethod) {
        this.okButtonMethod = okButtonMethod;
    }

    public String getOkButtonMethod() {
        return okButtonMethod;
    }

    public void setCodeMaxLength(int codeMaxLength) {
        this.codeMaxLength = codeMaxLength;
    }

    public int getCodeMaxLength() {
        return codeMaxLength;
    }

    public void setMainEduLevelCode(String mainEduLevelCode) {
        this.mainEduLevelCode = mainEduLevelCode;
    }

    public String getMainEduLevelCode() {
        return mainEduLevelCode;
    }

    public void setSelectedmainEduLevel(String selectedmainEduLevel) {
        this.selectedmainEduLevel = selectedmainEduLevel;
    }

    public String getSelectedmainEduLevel() {
        return selectedmainEduLevel;
    }

    public static void setAvailablejobMap(HashMap<String, String> availablejobMap) {
        QualificationHelperBean.availablejobMap = availablejobMap;
    }

    public static HashMap<String, String> getAvailablejobMap() {
        return availablejobMap;
    }

    public void setLevelChildCode(String levelChildCode) {
        this.levelChildCode = levelChildCode;
    }

    public String getLevelChildCode() {
        return levelChildCode;
    }

    public void setSelectedLevelChild(String selectedLevelChild) {
        this.selectedLevelChild = selectedLevelChild;
    }

    public String getSelectedLevelChild() {
        return selectedLevelChild;
    }

    public void setGeneralSpecsCode(String generalSpecsCode) {
        this.generalSpecsCode = generalSpecsCode;
    }

    public String getGeneralSpecsCode() {
        return generalSpecsCode;
    }

    public void setSelectedGeneralSpecs(String selectedGeneralSpecs) {
        this.selectedGeneralSpecs = selectedGeneralSpecs;
    }

    public String getSelectedGeneralSpecs() {
        return selectedGeneralSpecs;
    }

    public void setMajorSpecCode(String majorSpecCode) {
        this.majorSpecCode = majorSpecCode;
    }

    public String getMajorSpecCode() {
        return majorSpecCode;
    }

    public void setSelectedMajorSpec(String selectedMajorSpec) {
        this.selectedMajorSpec = selectedMajorSpec;
    }

    public String getSelectedMajorSpec() {
        return selectedMajorSpec;
    }

    public void setSelectedMinorSpecs(String selectedMinorSpecs) {
        this.selectedMinorSpecs = selectedMinorSpecs;
    }

    public String getSelectedMinorSpecs() {
        return selectedMinorSpecs;
    }

    public void setMinorSpecsCode(String minorSpecsCode) {
        this.minorSpecsCode = minorSpecsCode;
    }

    public String getMinorSpecsCode() {
        return minorSpecsCode;
    }
   
    protected BaseBean getContainerBean() {
        return (BaseBean)JSFHelper.getValue(getContainerBeanName());
    }

    public void scrollerAction(ActionEvent ae) {

      getContainerBean().setJavaScriptCaller("changeVisibilityDiv(window.blocker,window."  + getDivId()+
                                                   ",'backButtonIntegrationDiv1');");

    }
    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
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
    public void setFilterPnlCollapsed(Boolean filterPnlCollapsed) {
        this.filterPnlCollapsed = filterPnlCollapsed;
    }

    public Boolean getFilterPnlCollapsed() {
        return filterPnlCollapsed;
    }   

    public void setSearchQualDTO(IQualificationsSearchCriteriaDTO searchQualDTO) {
        this.searchQualDTO = searchQualDTO;
    }

    public IQualificationsSearchCriteriaDTO getSearchQualDTO() {
        return searchQualDTO;
    }

    public void setResetMode(Boolean resetMode) {
        this.resetMode = resetMode;
    }

    public Boolean getResetMode() {
        return resetMode;
    }

    public void setMainEduLevels(List<IBasicDTO> mainEduLevels) {
        this.mainEduLevels = mainEduLevels;
    }

    public List<IBasicDTO> getMainEduLevels() {
        return mainEduLevels;
    }

    public void setLevelChildList(List<IBasicDTO> levelChildList) {
        this.levelChildList = levelChildList;
    }

    public List<IBasicDTO> getLevelChildList() {
        return levelChildList;
    }

    public void setGeneralSpecsList(List<IBasicDTO> generalSpecsList) {
        this.generalSpecsList = generalSpecsList;
    }

    public List<IBasicDTO> getGeneralSpecsList() {
        return generalSpecsList;
    }

    public void setMajorSpecsList(List<IBasicDTO> majorSpecsList) {
        this.majorSpecsList = majorSpecsList;
    }

    public List<IBasicDTO> getMajorSpecsList() {
        return majorSpecsList;
    }

    public void setMinorSpecsList(List<IBasicDTO> minorSpecsList) {
        this.minorSpecsList = minorSpecsList;
    }

    public List<IBasicDTO> getMinorSpecsList() {
        return minorSpecsList;
    }
}
