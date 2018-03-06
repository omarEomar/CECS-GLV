package com.beshara.csc.inf.presentation.backingbean.maindata.addExperince;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.hr.crs.business.dto.IJobSeekersDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtWorkDataDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.job.business.client.IJobsClient;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobSearchCriteriaDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.integration.presentation.backingbean.search.JobFilter;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IMinistrySearchCriteriaDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.org.integration.presentation.backingbean.ministry.SearchMinistriesHelperBean;
import com.beshara.csc.nl.org.integration.presentation.backingbean.workcenters.WorkCentersHelperBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.sql.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;

public class addExperinceIntegrationBean extends LookUpBaseBean{
   
    private Long govFlag = ISystemConstant.GOVERNMENT_FLAG;
    private Long nonGovFlag = ISystemConstant.NON_GOVERNMENT_FLAG;
    private List<IBasicDTO> categoryList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> ministryList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> myExcludedMinistriesList = new ArrayList<IBasicDTO>();

    private Long categoryTypeCode = govFlag;

    private String selectedMinistry;
    private String selectedJobRadio = "";
    private static final String BACK_BEAN_NAME = "addExperinceIntegrationBean";
    public static final String METHOD_NAME_ADD_SELECTED_JOBS = "addExperinceIntegrationBean.jobReturnMethod";
    private String regDateLabel;
    //NOTE : plz remove this unsed attribute
    ////reply popListSize is used in condition to display dataScrollar in job popup
    private int popListSize = 0;
    //NOTE : plz rename this attrbute to Job NAMES instead of POP
    // reply i only rename file name to jobadddiv but changes these attributes will alot of changes
    private String popName;
    private String searchPopString;
    private List popList = new ArrayList();
    private HtmlDataTable popDataTable = new HtmlDataTable();
    private String gov_Flag = ISystemConstant.GOVERNMENT_FLAG.toString();
    private String nonGov_Flag = ISystemConstant.NON_GOVERNMENT_FLAG.toString();
    private String selectedGovFlag = getVirtualConstValue();
    private String ministryName; //for search in ministry div
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;
    private Date regDate;
    static Long EXPERINECE_FLAG_1 = 1L;
    private String adminSrchType = "0";
    private Boolean validateSrchString = Boolean.FALSE;
    private String categoryBindingValue = "";
    private SearchMinistriesHelperBean ministryHelper = new SearchMinistriesHelperBean(BACK_BEAN_NAME, null);
    private JobFilter jobFilter = new JobFilter();
    private  WorkCentersHelperBean  wcIntegrationBean =  new WorkCentersHelperBean("jobWorkCenterListBean" ,"integrationDiv1",false,false,true,true,null); ;  
    private  SearchMinistriesHelperBean deepCopyMin;
    private String jobExcludedCode;

    public addExperinceIntegrationBean() {
        setPageDTO(InfDTOFactory.createKwtWorkDataDTO());
        if (categoryList != null && categoryList.size() == 0 && categoryTypeCode != null &&
            categoryTypeCode.equals(govFlag)) {
            getCatByGovFlag();
        }
        setUsingPaging(true);
        setUsingBsnPaging(true);
        init();
        initMinistryDiv();
        initJobFilter();
        setCustomDiv1(getCustomDiv1() + " extraTooWideDiv");
        setLovBaseBean(new LOVBaseBean());
        wcIntegrationBean.setSingleSelectionFlag(true);
    }
    private void initJobFilter() {
        jobFilter.setOkButtonMethod(METHOD_NAME_ADD_SELECTED_JOBS);
        jobFilter.setSingleSelection(true);
    }
    private void initMinistryDiv() {
           ministryHelper.setReturnMethodName("addExperinceIntegrationBean.addMinistry");
           ministryHelper.setPreOpenMethodName("addExperinceIntegrationBean.preLinkMinistry");
           ministryHelper.getOkCommandButton().setReRender("myForm:workCenterDiv");
           
        }
    public void openWorkCentersIntegDiv(){
        initIntegration();
        IMinistriesDTO dto = null;
        try {
            dto = (IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(OrgEntityKeyFactory.createMinistriesEntityKey(Long.valueOf(deepCopyMin.getSelectedDTOSList().get(0).getCode().getKey())));
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        wcIntegrationBean.setMinName(dto.getName());
        wcIntegrationBean.setCatName(dto.getCatsDTO().getName());
        wcIntegrationBean.setSelectedMinCode(deepCopyMin.getSelectedDTOSList().get(0).getCode().getKey());
        wcIntegrationBean.setSelectedCatCode(deepCopyMin.getCategoryType());
        wcIntegrationBean.setSingleSelectionFlag(true);
    //        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.integrationDiv1);");
        }
    
    public void initIntegration() {
           wcIntegrationBean.getOkCommandButton().setReRender("integrationDiv2,OperationBar,dataT_data_panel,paging_panel,workCenterName,panelGridId");
           wcIntegrationBean.setReturnMethodName(BACK_BEAN_NAME + ".updateSelectedWorkCenter");
          
       }
    public void updateSelectedWorkCenter() {
        if (!wcIntegrationBean.getSelectedDTOSList().get(0).equals("")) { // if Div is opened either in add or edit mode 
             resetPageData();
            IBasicDTO basic = (IWorkCentersDTO)wcIntegrationBean.getSelectedDTOSList().get(0);
            ((IKwtWorkDataDTO)getPageDTO()).setWorkCentersDTO((IWorkCentersDTO)basic);
        }
       
    }
    public void resetPageData() {
        setSelectedRadio("");;
        unCheck();
        setCheckAllFlag(false);
        ((IKwtWorkDataDTO)getPageDTO()).setWorkCentersDTO(null);
//        setWorkCenterName(null);
//        setWorkMinistrieKey(null);
        setUpdateMyPagedDataModel(true);
        setSearchMode(false);
        getSelectedDTOS().clear();
        if (isUsingBsnPaging()) //Nora
            setPagingRequestDTO(null);
        
        
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowContent1(true);
        app.setShowLookupAdd(false);
        app.setShowLovDiv(true);
        app.setShowSearch(false);
        app.setShowCustomDiv1(true);
        app.setShowIntegrationDiv1(true);
        app.setShowIntegrationDiv2(true);
        return app;
    }

    public void reSetButtonErase() {

        ((IKwtWorkDataDTO)getPageDTO()).setJobsDTO(null);

        System.out.println("tempComponent");


    }
    public void showJobDiv(){
            List<String> excludedJobCodeList = new ArrayList<String>();
            if (this.getJobExcludedCode() != null) {
                excludedJobCodeList.add(this.jobExcludedCode);
            }
            if (excludedJobCodeList.size() > 0) {
                jobFilter.setExcludedJobList(excludedJobCodeList);
            }
            try {
                jobFilter.cancelSearch();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
            jobFilter.setResetMode(false);
            jobFilter.setLoadSecAccessibleJobsOnly(true);
            }
 
    public void showListOfValuesDiv() {
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setMyTableData(new ArrayList());
        getLovBaseBean().setCurrentPageIndex(1);
        getLovBaseBean().setOldPageIndex(0);
        getLovBaseBean().setCodeTypeString(true);
        getLovBaseBean().getMyDataTable().setFirst(0);
        Integer listSize = (Integer)evaluateValueBinding("pageBeanName.lovBaseBean.selectedListSize");
        Boolean flage = listSize == 0 ? true : false;
        getLovBaseBean().getOkBtnLovDiv().setDisabled(flage);

        try {
            getLovBaseBean().setMyTableData(JobClientFactory.getJobsClient().getCodeName());
            if (getLovBaseBean().getMyDataTable() != null)
                getLovBaseBean().getMyDataTable().setFirst(0);

        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        }
        getLovBaseBean().setRenderedDropDown("jobPanelGrp");
        getLovBaseBean().getOkCommandButton().setReRender("jobPanelGrp");
        getLovBaseBean().setReturnMethodName(BACK_BEAN_NAME + ".jobReturnMethod");
        getLovBaseBean().setSearchMethod(BACK_BEAN_NAME + ".searchJob");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod(BACK_BEAN_NAME + ".cancelSearchLovDiv");
    }

    public String jobReturnMethod() {

            if (jobFilter != null && jobFilter.getSelectedDTOS() != null && !jobFilter.getSelectedDTOS().isEmpty()) {
            try {
                    IBasicDTO basic = (IJobsDTO)jobFilter.getSelectedDTOS().get(0);
                    ((IKwtWorkDataDTO)getPageDTO()).setJobsDTO((IJobsDTO)basic);
                    this.setJobExcludedCode(jobFilter.getSelectedDTOS().get(0).getCode().getKey());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public void cancelSearchLovDiv() {
        showListOfValuesDiv();
    }


    public void searchJob(Object searchType, Object searchQuery) {

        if (searchQuery != null && !searchQuery.toString().equals("") && searchType != null &&
            !searchType.equals("")) {

            getLovBaseBean().setSearchMode(true);

            IJobSearchCriteriaDTO srchDTO = JobDTOFactory.createJobSearchCriteriaDTO();


            System.out.println("getLovBaseBean().getSearchTyp()" + getLovBaseBean().getSearchTyp());
            if (searchQuery != null && !searchQuery.equals("")) {

                if (getLovBaseBean().getSearchTyp().toString().equals("0")) {
                    srchDTO.setJobCode(searchQuery.toString());
                } else if (getLovBaseBean().getSearchTyp().toString().equals("1")) {
                    srchDTO.setJobName(searchQuery.toString());
                }


                try {

                    IJobsClient jobsClientClient = JobClientFactory.getJobsClient();

                    getLovBaseBean().setMyTableData(jobsClientClient.search(srchDTO));

                    getLovBaseBean().getMyDataTable().setFirst(0);

                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                    getLovBaseBean().setMyTableData(new ArrayList());
                } catch (DataBaseException e) {
                    e.printStackTrace();
                    getLovBaseBean().setMyTableData(new ArrayList());
                } catch (Exception e) {
                    getLovBaseBean().setMyTableData(new ArrayList());
                    e.printStackTrace();
                }

            }

        }

    }


    private void init() {
        if (((IKwtWorkDataDTO)getPageDTO()).getKwtCitizensResidentsDTO() == null)
            ((IKwtWorkDataDTO)getPageDTO()).setKwtCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
    }

    public void filterByCategoryType(ActionEvent event) {
        if (categoryTypeCode != null) {
            getCatByGovFlag();
        } else {
            categoryList = new ArrayList();
        }
        if (getPageDTO() != null) {
            ((IKwtWorkDataDTO)getPageDTO()).setFromDate(null);
            ((IKwtWorkDataDTO)getPageDTO()).setUntilDate(null);
        }
        popName = "";
    }

    public void getCatByGovFlag() {
        try {
            categoryList = OrgClientFactory.getCatsClient().getCatsByGovFlag(categoryTypeCode);
            ministryList.clear();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            categoryList = new ArrayList();
            ministryList.clear();
        } catch (DataBaseException e) {
            e.printStackTrace();
            categoryList = new ArrayList();
            ministryList.clear();
        } catch (Exception e) {
            e.printStackTrace();
            categoryList = new ArrayList();
            ministryList.clear();
        }
    }

    public void filterByCategory(ActionEvent event) {
        if (categoryBindingValue != null && !(categoryBindingValue.equals(""))) {
            try {
                ministryList =
                        OrgClientFactory.getMinistriesClient().getAllByCategory(Long.valueOf(categoryBindingValue));
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                ministryList = new ArrayList();
            } catch (DataBaseException e) {
                e.printStackTrace();
                ministryList = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                ministryList = new ArrayList();
            }
        } else {
            ministryList = new ArrayList();
        }
    }

    public void filterDataByCategory(ActionEvent ae) {
        try {
            System.out.println(ae + "ssss");
            filterDataTableByCategory(null);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        setMinistryName(null);
        setSearchMode(false);
        setMyTableData(null);
    }

    public void setGovFlag(Long govFlag) {
        this.govFlag = govFlag;
    }

    public Long getGovFlag() {
        return govFlag;
    }

    public void setNonGovFlag(Long nonGovFlag) {
        this.nonGovFlag = nonGovFlag;
    }

    public Long getNonGovFlag() {
        return nonGovFlag;
    }

    //    public void setSelectedCategory(String selectedCategory) {
    //        this.selectedCategory = selectedCategory;
    //    }
    //
    //    public String getSelectedCategory() {
    //        return selectedCategory;
    //    }

    public void setSelectedMinistry(String selectedMinistry) {
        this.selectedMinistry = selectedMinistry;
    }

    public String getSelectedMinistry() {
        return selectedMinistry;
    }

    public void setCategoryList(List<IBasicDTO> categoryList) {
        this.categoryList = categoryList;
    }

    public List<IBasicDTO> getCategoryList() {
        if (!selectedGovFlag.equals("-100") && !selectedGovFlag.equals("")) {
            try {
                categoryList = OrgClientFactory.getCatsClient().getCatsByGovFlag(new Long(selectedGovFlag));
            } catch (SharedApplicationException e) {
                categoryList = new ArrayList();
            } catch (DataBaseException e) {
                categoryList = new ArrayList();
            }
        }
        return categoryList;
    }

    public void setMinistryList(List<IBasicDTO> ministryList) {
        this.ministryList = ministryList;
    }

    public List<IBasicDTO> getMinistryList() {
        return ministryList;
    }

    public void setCategoryTypeCode(Long categoryTypeCode) {
        this.categoryTypeCode = categoryTypeCode;
    }

    public Long getCategoryTypeCode() {
        return categoryTypeCode;
    }

    public void setPopList(List popList) {
        this.popList = popList;
    }

    public List getPopList() {
        if (popList == null || popList.size() == 0) {
            try {
                setPopList(JobClientFactory.getJobsClient().getCodeName());
            } catch (DataBaseException e) {
                e.printStackTrace();
                setPopList(new ArrayList());
            } catch (Exception e) {
                e.printStackTrace();
                setPopList(new ArrayList());
            }
        }
        return popList;
    }

    public void setPopListSize(int popListSize) {
        this.popListSize = popListSize;
    }

    public int getPopListSize() {
        if (this.getPopList() != null)
            return this.getPopList().size();
        return popListSize;
    }

    public void setPopName(String popName) {
        this.popName = popName;
    }

    public String getPopName() {
        return popName;
    }

    public void setSearchPopString(String searchPopString) {
        this.searchPopString = searchPopString;
    }

    public String getSearchPopString() {
        return searchPopString;
    }

    //    public void scrollerAction(ActionEvent ae) {
    //        super.changePageIndex(ae);
    //        setSelectedJobRadio("");
    //        setJavaScriptCaller("changeVisibilityDiv ( window.blocker , window.lookupAddDiv ) ");
    //    }

    public void scrollerActionMinistry(ActionEvent ae) {
        super.changePageIndex(ae);
        //setSelectedRadio("");
        setJavaScriptCaller("changeVisibilityDiv ( window.blocker , window.customDiv1 ) ");
    }

    //    public void addPopItem() {
    //        super.setSuccess(false);
    //        super.setShowErrorMsg(false);
    //
    //        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
    //            ((IKwtWorkDataDTO)getPageDTO()).setJobsDTO((IJobsDTO)getSelectedDTOS().get(0));
    //        }
    //    }

    //    public void radioBtnChecked(ValueChangeEvent v) {
    //      if(selectedJobRadio!=null && !selectedJobRadio.equals("") && selectedJobRadio!="")
    //        {
    //        IBasicDTO b = (IBasicDTO)this.popDataTable.getRowData();
    //        popName = b.getName();
    //        if (getPageDTO() != null) {
    //            ((IKwtWorkDataDTO)getPageDTO()).setJobsDTO(JobDTOFactory.createJobsDTO());
    //            ((IKwtWorkDataDTO)getPageDTO()).getJobsDTO().setCode(b.getCode());
    //        }
    //        }
    //    }

    //    public void setPopDataTable(HtmlDataTable popDataTable) {
    //        this.popDataTable = popDataTable;
    //    }
    //
    //    public HtmlDataTable getPopDataTable() {
    //        return popDataTable;
    //    } //////// search and cancel search //////////

    //    public void searchPopup() {
    //        setSearchMode(true);
    //        if (searchPopString != null) {
    //            try {
    //                popList =
    //                        JobClientFactory.getJobsClient().searchByName(formatSearchString(searchPopString));
    //                if (popDataTable != null)
    //                    popDataTable.setFirst(0);
    //            } catch (SharedApplicationException e) {
    //                e.printStackTrace();
    //                popList = new ArrayList();
    //            } catch (DataBaseException e) {
    //                e.printStackTrace();
    //                popList = new ArrayList();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //                popList = new ArrayList();
    //            }
    //        }
    //        setJavaScriptCaller("changeVisibilityDiv ( window.blocker , window.lookupAddDiv ) ; ");
    //    }
    //
    //    public void cancelSearchPopup() {
    //        setSearchMode(false);
    //        popList = null;
    //        getPopList();
    //        searchPopString = null;
    //        getSelectedDTOS().clear();
    //        setSelectedDTOS(new ArrayList<IBasicDTO>());
    //        try {
    //            getAll();
    //        } catch (DataBaseException e) {
    //            // TODO
    //        }
    //    }

    /**
     * calculate Days by sup untilDate Form fromDate to
     * set actual days,months and Years
     * @param dto
     * @createdBy Kareem Sayed
     */
    protected void calcActualDaysPerRow(IKwtWorkDataDTO dto) {
        if (dto != null) {
            //KwtWorkDataEntityKey kwtWorkDataEntityKey = (KwtWorkDataEntityKey)dto.getCode();
            if (dto.getFromDate() != null) {
                GregorianCalendar fromCal = new GregorianCalendar();
                fromCal.setTime(dto.getFromDate());
                GregorianCalendar untilCal = new GregorianCalendar();
                untilCal.setTime(dto.getUntilDate());

                long diffInMillis = untilCal.getTimeInMillis() - fromCal.getTimeInMillis();
                int ONE_DAY = 24 * 60 * 60 * 1000;
                Long totaldays = (diffInMillis / ONE_DAY);
                if (totaldays >= 365) {
                    Long years = totaldays / 365;
                    totaldays = totaldays % 365;
                    dto.setActualExpYears(years);
                } else {
                    dto.setActualExpYears(0L);
                }
                //fromCal.getActualMaximum()
                if (totaldays >= 30) {
                    Long months = totaldays / 30;
                    totaldays = totaldays % 30;
                    dto.setActualExpMonths(months);
                } else {
                    dto.setActualExpMonths(0L);
                }
                if (totaldays != null) {
                    dto.setActualExpDays(totaldays);
                } else {
                    dto.setActualExpDays(0L);
                }

            }
        }
    }

    public void add() {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            //this column was removed from DB
            //((IKwtWorkDataDTO)getPageDTO()).setExperienceFlag(EXPERINECE_FLAG_1);
            if (selectedMinistry != null && !selectedMinistry.equals("")) {
                ((IKwtWorkDataDTO)getPageDTO()).setMinistriesDTO(OrgDTOFactory.createMinistriesDTO());
                ((IKwtWorkDataDTO)getPageDTO()).getMinistriesDTO().setCode(OrgEntityKeyFactory.createMinistriesEntityKey(Long.valueOf(selectedMinistry)));
            }
            calcActualDaysPerRow((IKwtWorkDataDTO)getPageDTO());
            ((IKwtWorkDataDTO)getPageDTO()).setFirstParent(Long.valueOf(((IKwtWorkDataDTO)getPageDTO()).getMinistriesDTO().getCode().getKey()));
            ((IKwtWorkDataDTO)getPageDTO()).setTreeLevel(1L);
            ((IKwtWorkDataDTO)getPageDTO()).setLeafFlag(1L);
            ((IKwtWorkDataDTO)getPageDTO()).setPerFlag(1L);
            ((IKwtWorkDataDTO)getPageDTO()).setPisFlag(1L);
            setPageDTO(InfClientFactory.getKwtWorkDataClient().add(getPageDTO()));
            this.setSuccess(true);
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            setSuccess(false);
            this.setErrorMsg(e.getMessage());
            //shared_util.setErrMsgValue("FailureInAdd");
            e.printStackTrace();
        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            //getSharedUtil().handleException(e);
            String errorMsgValue = "";
            if (e.getMessage().equals("PrimaryKeyDuplicated")) {
                errorMsgValue =
                        getSharedUtil().messageLocator("com.beshara.csc.hr.crs.presentation.resources.crs", e.getMessage());
                this.setErrorMsg(errorMsgValue);
                shared_util.setErrMsgValue(errorMsgValue);
            }

            if (errorMsgValue.equals("")) {
                errorMsgValue =
                        getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.globalexceptions", e.getMessage());
                this.setErrorMsg(errorMsgValue);
                shared_util.setErrMsgValue(errorMsgValue);
            }

            setSuccess(false);

            //shared_util.setErrMsgValue("FailureInAdd");
            e.printStackTrace();
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            setSuccess(false);
            this.setErrorMsg("FailureInAdd");
            //shared_util.setErrMsgValue("FailureInAdd");
            e.printStackTrace();
        }
    } // overrided

    public void save() {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            this.add();
            bindBean();
            //highlighting part
//            ExperiencesBean experiencesBean =
//                (ExperiencesBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
//                                                                                                       "manualScreenExperiencesBean" +
//                                                                                                       "}").getValue(FacesContext.getCurrentInstance());
//            super.setMyDataTable(experiencesBean.getMyDataTable());
//            List experienceList =
//                (List)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{manualScreenMaintainBean.pageDTO.kwtCitizensResidentsDTO.kwtWorkDataDTOList}").getValue(FacesContext.getCurrentInstance());
//            if (getPageDTO() != null && getPageDTO().getCode() != null) {
//                experiencesBean.getHighlightedDTOS().add(getPageDTO());
//                setMyTableData(experienceList);
//                //  super.getPageIndex((String)getPageDTO().getCode().getKey());
//                shared_util.setSuccessMsgValue("SuccessAdds");
//            }
//            //            else {
//            //                this.setErrorMsg("FailureInAdd");
//            //                shared_util.setErrMsgValue("FailureInAdd");
//            //                setSuccess(false);
//            //            }
        } catch (Exception e) {
            e.printStackTrace();
            shared_util.setErrMsgValue("SystemFailureException");
        }
    }

    public void saveAndNew() throws DataBaseException {
        this.add();
        this.reInitializePageDTO();
        cancelSearch();
    }

    public void reInitializePageDTO() {
        if (((IKwtWorkDataDTO)getPageDTO()) != null) {
            ((IKwtWorkDataDTO)getPageDTO()).setFromDate(null);
            ((IKwtWorkDataDTO)getPageDTO()).setUntilDate(null);
            ((IKwtWorkDataDTO)getPageDTO()).setMinistriesDTO(null);
            ((IKwtWorkDataDTO)getPageDTO()).setJobsDTO(null);
        }
        selectedMinistry = "";
        categoryTypeCode = govFlag;
        getCatByGovFlag();
        popName = null;
    }

    public String saveExperience() throws DataBaseException {
        //        Date untilDate = ((KwtWorkDataDTO)getPageDTO()).getUntilDate();
        //        if(untilDate != null && untilDate.compareTo(getRegDate())<0){
        save();
        this.getWizardBar().setCurrentStep("experiences");
        return "manualscreenexperiences";
        //        }
        //        else{
        //            getSharedUtil().handleException(new Exception(),
        //                "com.beshara.csc.hr.crs.presentation.resources.crs",
        //                                "AvailableOpenPeriodExceptionCenteralPeriod");
        //            return null;
        //        }
    }

    public String back() {
        bindBean();
        this.getWizardBar().setCurrentStep("experiences");
        return "manualscreenexperiences";
    }

    public void bindBean() {
//        if (getPageDTO() != null && ((IKwtWorkDataDTO)getPageDTO()).getKwtCitizensResidentsDTO() != null &&
//            ((IKwtWorkDataDTO)getPageDTO()).getKwtCitizensResidentsDTO().getCode() != null) {
//            MaintainBean maintainBean =
//                (MaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
//                                                                                                    "manualScreenMaintainBean" +
//                                                                                                    "}").getValue(FacesContext.getCurrentInstance());
//            maintainBean.setCivilId(((IKwtCitizensResidentsEntityKey)((IKwtWorkDataDTO)getPageDTO()).getKwtCitizensResidentsDTO().getCode()).getCivilId());
//            maintainBean.getKwtCitizinData();
//        }
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getRegDate() {


//        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("manualScreenMaintainBean");
//        return ((IJobSeekersDTO)maintainBean.getPageDTO()).getRegDate();
      return  null;
    }

    public String getRegDateLabel() {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String s = formatter.format(getRegDate());
            //caderUntilDateLabel = new Date ( ) ;
            return s;
        } catch (Throwable e) {
            System.err.println(e.getMessage());
        }
        return "";
    }

    public void chooseCategoryByGovFlag(ActionEvent ae) {

        super.repositionPage(0);
        setSelectedRadio("");


        setSearchMode(false);
        if (selectedGovFlag != null || !selectedGovFlag.equals(""))
            setCategoryBindingValue(getVirtualConstValue());

        cancelSearch();

        if (selectedGovFlag == null || selectedGovFlag.equals("")) {
            setSelectedGovFlag(gov_Flag);
        }
        setCategoryBindingValue(getVirtualConstValue());
        setMinistryName(getVirtualConstValue());
        setMyTableData(new ArrayList());
        try {
            categoryList = (OrgClientFactory.getCatsClient()).getCatsByGovFlag(Long.parseLong(selectedGovFlag));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            categoryList = new ArrayList();
        } catch (DataBaseException e) {
            e.printStackTrace();
            categoryList = new ArrayList();
        }

    }


    public void filterDataTableByCategory(ValueChangeEvent ce) throws DataBaseException {

        //        String compId;
        //        compId = ((HtmlSelectOneMenu)ce.getSource()).getId();
        //        Long changedValue = (Long)ce.getNewValue();
        //        System.out.println(compId);
        setMinistryName(null);
        setSelectedRadio("");
        if (isSearchMode() == true) {
            setSearchMode(false);
            cancelSearch();
        }
        //if (compId.equals("search_first_inputTxt")) {
        //  if (!changedValue.equals(-100L)) {
        if (isUsingPaging()) {
            generatePagingRequestDTO("addExperinceIntegrationBean", "filterDataTableByCategoryWithPaging");
            Object[] params = new Object[] { getCategoryBindingValue() };
            getPagingRequestDTO().setParams(params);
            resetPageIndex();
        } else {
            setMyTableData(new ArrayList());
        }

        //            } else {
        //                getAll();
        //            }
        //}
        //}

        getSelectedDTOS().clear();
    }


    public PagingResponseDTO filterDataTableByCategoryWithPaging(PagingRequestDTO pagingRequest) {

        IPagingResponseDTO bsnResponseDTO = getDataByCategoryIdWithPaging(pagingRequest);

        PagingResponseDTO pagingResponseDTO = null;
        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());

            if (getCurrentPageIndex() == 1) {
                pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                getPagingRequestDTO().setParams(new Object[] { pagingRequest.getParams()[0],
                                                               bsnResponseDTO.getCount() });
            } else {
                pagingResponseDTO.setTotalListSize(((Long)pagingRequest.getParams()[1]).intValue());
            }
        }

        return pagingResponseDTO;
    }


    private com.beshara.base.paging.impl.PagingResponseDTO getDataByCategoryIdWithPaging(PagingRequestDTO pagingRequestDTO) {

        int pageIndex = getCurrentPageIndex();

        com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO =
            new com.beshara.base.paging.impl.PagingRequestDTO();

        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));

        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));
        if (pageIndex == 1) {
            bsnPagingRequestDTO.setCountRequired(true);
        }

        if (isSorting()) {
            bsnPagingRequestDTO.setSortAscending(pagingRequestDTO.isSortAscending());

            List<String> sortingColumnList = new ArrayList<String>();
            sortingColumnList.add(pagingRequestDTO.getBsnSortingColumnName());
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }

        String catId = (String)pagingRequestDTO.getParams()[0];

        IMinistrySearchCriteriaDTO searchDTO = OrgDTOFactory.createMinistrySearchCriteriaDTO();
        searchDTO.setCategoryCode(new Long(catId));
        searchDTO.setPagingRequestDTO(bsnPagingRequestDTO);

        try {
            bsnPagingResponseDTO =
                    (com.beshara.base.paging.impl.PagingResponseDTO)OrgClientFactory.getMinistriesClient().getAllByCategoryWithPaging(searchDTO);
        } catch (NoResultException ne) { //this means no search results found
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (SharedApplicationException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        } catch (DataBaseException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        }

        return bsnPagingResponseDTO;
    }

    public void getAll() throws DataBaseException {

        if (isUsingPaging()) {
            generatePagingRequestDTO("addExperinceIntegrationBean", "getAllWithPaging");
            //resetPageIndex();
        } else {
            setMyTableData(new ArrayList());
        }
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO pagingRequest) {

        return new PagingResponseDTO(new ArrayList());

    }

    public void cancelSearch() {

        //setSelectedGovFlag(gov_Flag);
        if (categoryBindingValue != null && !categoryBindingValue.equals("")) {
            setMinistryName("");
            searchForMinistry();
            setSearchMode(false);
            setSelectedRadio("");

        } else {
            searchForMinistry();
        }
        setAdminSrchType("1");
        setSearchMode(false);
        setSelectedRadio("");
        setSearchQuery("");


    }

    public void searchForMinistry() {

        if (isUsingPaging()) {

            //            if (!getSelectedCategory().equals(getVirtualConstValue()) &&
            //                !getSelectedCategory().equals("")) {
            setSearchMode(true);
            setSelectedRadio("");
            super.repositionPage(0);
            setCurrentPageIndex(1);
            //            if (!getSelectedCategory().equals(getVirtualConstValue()) &&
            //                                        !getSelectedCategory().equals("")) {
            generatePagingRequestDTO("addExperinceIntegrationBean", "searchForMinistryWithPaging");
            Object[] params = new Object[] { getCategoryBindingValue() };
            getPagingRequestDTO().setParams(params);
            //}


        }

    }

    public PagingResponseDTO searchForMinistryWithPaging(PagingRequestDTO pagingRequest) {
        if (pagingRequest.getParams()[0] != null && !((String)pagingRequest.getParams()[0]).equals("")) {
            IPagingResponseDTO bsnResponseDTO = searchBsnForMinistryWithPaging(pagingRequest);

            PagingResponseDTO pagingResponseDTO = null;
            if (bsnResponseDTO.getBasicDTOList() == null) {
                pagingResponseDTO = new PagingResponseDTO(new ArrayList());
            } else {
                pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());

                if (getCurrentPageIndex() == 1) {
                    pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                    getPagingRequestDTO().setParams(new Object[] { pagingRequest.getParams()[0],
                                                                   bsnResponseDTO.getCount() });

                } else {
                    pagingResponseDTO.setTotalListSize(((Long)pagingRequest.getParams()[1]).intValue());
                }
            }

            return pagingResponseDTO;
        } else {
            return new PagingResponseDTO(new ArrayList());
        }

    }


    private com.beshara.base.paging.impl.PagingResponseDTO searchBsnForMinistryWithPaging(PagingRequestDTO pagingRequestDTO) {

        int pageIndex = getCurrentPageIndex();

        com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO =
            new com.beshara.base.paging.impl.PagingRequestDTO();

        //if (isSearchMode()) {
        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));
        //        }
        //        else{
        //            bsnPagingRequestDTO.setPageNum(new Long(1));
        //            bsnPagingRequestDTO.setCountRequired(true);
        //            setCurrentPageIndex(1);
        //        }
        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));
        if (pageIndex == 1) {
            bsnPagingRequestDTO.setCountRequired(true);
        }
        String catId = (String)pagingRequestDTO.getParams()[0];

        IMinistrySearchCriteriaDTO searchDTO = OrgDTOFactory.createMinistrySearchCriteriaDTO();
        searchDTO.setCategoryCode(new Long(catId));
        if (ministryName != null && !ministryName.equals("")) {
            if (adminSrchType.toString().equals("1")) {
                searchDTO.setMinistryName(ministryName);
            } else if (adminSrchType.toString().equals("0")) {
                try {
                    searchDTO.setMinistryCode(new Long(ministryName));
                } catch (Exception e) {
                    e.printStackTrace();
                    searchDTO.setMinistryCode(-100L);
                }
            }
        } else {

        }


        searchDTO.setPagingRequestDTO(bsnPagingRequestDTO);
        if (!isSearchMode()) {
            setMinistryName(null);
        }
        try {
            bsnPagingResponseDTO =
                    (com.beshara.base.paging.impl.PagingResponseDTO)OrgClientFactory.getMinistriesClient().getAllByCategoryAndNameWithPaging(searchDTO);
        } catch (NoResultException ne) { //this means no search results found
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (SharedApplicationException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        } catch (DataBaseException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        }
        //setSearchMode(true);
        return bsnPagingResponseDTO;
    }

    public void preLinkMinistry() {
              ministryHelper.setExcludedMinistriesList(getMyExcludedMinistriesList());
     }
    public void addMinistry() {
        setMinistryName("");
        setValidateSrchString(Boolean.FALSE);
        super.setSuccess(false);
        super.setShowErrorMsg(false);
        if (ministryHelper.getSelectedDTOSList() != null && ministryHelper.getSelectedDTOSList().size() != 0) {
            ((IKwtWorkDataDTO)getPageDTO()).setMinistriesDTO((IMinistriesDTO)ministryHelper.getSelectedDTOSList().get(0));
            try {
                setDeepCopyMin((SearchMinistriesHelperBean)getSharedUtil().deepCopyObject(ministryHelper));
            } catch (Exception e) {
            }
            setMyExcludedMinistriesList((List)deepCopyMin.getSelectedDTOSList());
        }
    }

    public void cancelAdd() {
        setMinistryName("");
        setValidateSrchString(Boolean.FALSE);
        if (isSearchMode()) {
            setSearchMode(false);
            setCategoryList(new ArrayList());
            setMinistryList(new ArrayList());
            //setSelectedGovFlag(getVirtualConstValue());
            setCategoryBindingValue(getVirtualConstValue());
            setMinistryName("");
            cancelSearch();


        }

    }

    //    public void cancelJobAdd() {
    //        setSelectedJobRadio(null);
    //        setPopList(null);
    //        if (isSearchMode()) {
    //            cancelSearch();
    //            setSearchMode(false);
    //            searchPopString = null;
    //            getSelectedDTOS().clear();
    //        }
    //
    //    }


    public void openAddMinistryDiv() {
        setValidateSrchString(Boolean.TRUE);
        categoryBindingValue = getVirtualConstValue();
        setSelectedRadio("");
        getSelectedDTOS().clear();
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        if (((IKwtWorkDataDTO)getPageDTO()).getMinistriesDTO() == null) {
            selectedGovFlag = gov_Flag;
            getCatByGovFlag();
        } else {
            selectedGovFlag = ((IKwtWorkDataDTO)getPageDTO()).getMinistriesDTO().getCatsDTO().getGovFlag().toString();

        }
        setCategoryList(new ArrayList());
        setSelectedGovFlag("");
        cancelSearch();
        try {
            getAll();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public boolean isDisableWorkCenterDiv(){
        if(deepCopyMin !=null && deepCopyMin.getCategoryType() != null &&!deepCopyMin.getCategoryType().equals("2")){
            return true;
        }
            return false;
        }

    public void setGov_Flag(String gov_Flag) {
        this.gov_Flag = gov_Flag;
    }

    public String getGov_Flag() {
        return gov_Flag;
    }

    public void setNonGov_Flag(String nonGov_Flag) {
        this.nonGov_Flag = nonGov_Flag;
    }

    public String getNonGov_Flag() {
        return nonGov_Flag;
    }

    public void setSelectedGovFlag(String selectedGovFlag) {
        this.selectedGovFlag = selectedGovFlag;
    }

    public String getSelectedGovFlag() {
        return selectedGovFlag;
    }

    public void setMinistryName(String ministryName) {
        this.ministryName = ministryName;
    }

    public String getMinistryName() {
        return ministryName;
    }

    public void setSelectedJobRadio(String selectedJobRadio) {

        this.selectedJobRadio = selectedJobRadio;

    }

    public String getSelectedJobRadio() {
        return selectedJobRadio;
    }

    public void setRegDateLabel(String regDateLabel) {
        this.regDateLabel = regDateLabel;
    }


    public void setAdminSrchType(String adminSrchType) {
        this.adminSrchType = adminSrchType;
    }

    public String getAdminSrchType() {
        return adminSrchType;
    }

    public void setValidateSrchString(Boolean validateSrchString) {
        this.validateSrchString = validateSrchString;
    }

    public Boolean getValidateSrchString() {
        return validateSrchString;
    }


    public void setCategoryBindingValue(String categoryBindingValue) {
        this.categoryBindingValue = categoryBindingValue;
    }

    public String getCategoryBindingValue() {
        return categoryBindingValue;
    }

    public void setMinistryHelper(SearchMinistriesHelperBean ministryHelper) {
        this.ministryHelper = ministryHelper;
    }

    public SearchMinistriesHelperBean getMinistryHelper() {
        return ministryHelper;
    }

    public void setMyExcludedMinistriesList(List<IBasicDTO> myExcludedMinistriesList) {
        this.myExcludedMinistriesList = myExcludedMinistriesList;
    }

    public List<IBasicDTO> getMyExcludedMinistriesList() {
        return myExcludedMinistriesList;
    }
    public void setJobFilter(JobFilter jobFilter) {
        this.jobFilter = jobFilter;
    }

    public JobFilter getJobFilter() {
        return jobFilter;
    }

    public void setWcIntegrationBean(WorkCentersHelperBean wcIntegrationBean) {
        this.wcIntegrationBean = wcIntegrationBean;
    }

    public WorkCentersHelperBean getWcIntegrationBean() {
        return wcIntegrationBean;
    }


    public void setDeepCopyMin(SearchMinistriesHelperBean deepCopyMin) {
        this.deepCopyMin = deepCopyMin;
    }

    public SearchMinistriesHelperBean getDeepCopyMin() {
        return deepCopyMin;
    }

    public void setJobExcludedCode(String jobExcludedCode) {
        this.jobExcludedCode = jobExcludedCode;
    }

    public String getJobExcludedCode() {
        return jobExcludedCode;
    }
}
