package com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata.add;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata.WorkDataListBean;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.ITrxTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.inf.business.client.IKwtWorkDataClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.integration.presentation.backingbean.search.JobFilter;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.org.integration.presentation.backingbean.ministry.SearchMinistryWithPagingHelper;
import com.beshara.csc.nl.org.integration.presentation.backingbean.workcenters.WorkCentersHelperBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class AddExperienceListBean extends LookUpBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    private static final String BACK_BEAN_NAME = "addExperienceListBean";
    public static final String METHOD_NAME_ADD_SELECTED_JOBS = "addExperienceListBean.jobReturnMethod";
    public static final int JOB_DIV_MODE = 1;
    public static final int OTHER_JOB_DIV_MODE = 2;
    private Long govFlag = ISystemConstant.GOVERNMENT_FLAG;
    private Long nonGovFlag = ISystemConstant.NON_GOVERNMENT_FLAG;
    private List<IBasicDTO> categoryList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> ministryList = new ArrayList<IBasicDTO>();
    private String selectedMinistry;
    private String selectedCategory;
    private Long govTypeCode;
    private JobFilter jobFilter = new JobFilter();
    private WorkCentersHelperBean wcIntegrationBean =
        new WorkCentersHelperBean(BACK_BEAN_NAME, "integrationDiv1", false, false, true, true, null);
    private String jobExcludedCode;
    private String gov_Flag = ISystemConstant.GOVERNMENT_FLAG.toString();
    private String nonGov_Flag = ISystemConstant.NON_GOVERNMENT_FLAG.toString();
    private String workCenterCode;
    private Boolean errorWorkCenter = false;
    private int jobDivMode;
    private String jobCode;
    private String jobName;
    private String otherJobCode;
    private String otherJobName;
    private Boolean errorJobKey = false;
    private Boolean errorOtherJobKey = false;
    private String jobType;
    private boolean perFlag;
    private boolean renderJobs;
    private boolean renderExperDuration;
    private boolean renderTechJob;
    private boolean renderJobtypeRadio;
    private IKwtCitizensResidentsDTO kwtCitizenDTO;
    private Long rcivilId;
    private List<Long> trxCodList = new ArrayList<Long>();
    private boolean includeTrx;
    private WorkDataListBean workDataListBean;
    private Long trxTypesCode;

    private SearchMinistryWithPagingHelper searchMinistryHelper;
    private boolean wrongMinCode;
    private String selectedMiniName;
    private Long activeDurationYears;
    private Long activeDurationMonths;
    private Long activeDurationDays;
    private boolean renderDurationErrMsg;

    public AddExperienceListBean() {
        setPageDTO(InfDTOFactory.createKwtWorkDataTreeDTO());
        setUsingPaging(true);
        setUsingBsnPaging(true);
        setCustomDiv1(getCustomDiv1() + " extraTooWideDiv");
        wcIntegrationBean.setSingleSelectionFlag(true);
        setIntegrationDiv1TitleKey("module_div_title");
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        workDataListBean = (WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean");
    }

    public void initiateBeanOnce() {
        govTypeCode = govFlag;
        jobType = "0";
        prepareByGovType();
        initJobFilter();
    }

    public static AddExperienceListBean getInstance() {
        return (AddExperienceListBean)JSFHelper.getValue(BACK_BEAN_NAME);
    }

    private void initJobFilter() {
        jobFilter.setOkButtonMethod(METHOD_NAME_ADD_SELECTED_JOBS);
        jobFilter.setSingleSelection(true);
    }

    public void showJobsDiv() {
        setJobDivMode(JOB_DIV_MODE);
        showJobDiv();
    }

    public void showOtherJobsDiv() {
        setJobDivMode(OTHER_JOB_DIV_MODE);
        showJobDiv();
    }

    private void showJobDiv() {
        List<String> excludedJobCodeList = new ArrayList<String>();
        if (this.getJobCode() != null && !this.getJobCode().trim().isEmpty()) {
            excludedJobCodeList.add(this.getJobCode());
        }
        if (this.getOtherJobCode() != null && !this.getOtherJobCode().trim().isEmpty()) {
            excludedJobCodeList.add(this.getOtherJobCode());
        }
        jobFilter.setExcludedJobList(excludedJobCodeList);
        try {
            jobFilter.cancelSearch();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        jobFilter.setResetMode(false);
        jobFilter.setLoadSecAccessibleJobsOnly(true);
    }

    public void jobReturnMethod() {
        if (jobFilter != null && jobFilter.getSelectedDTOS() != null && !jobFilter.getSelectedDTOS().isEmpty()) {
            if (this.jobDivMode == JOB_DIV_MODE) {
                this.setJobCode(((IJobsDTO)jobFilter.getSelectedDTOS().get(0)).getJobKey());
                this.setJobName(jobFilter.getSelectedDTOS().get(0).getName());
                ((IKwtWorkDataTreeDTO)getPageDTO()).setJobsDTO((IJobsDTO)jobFilter.getSelectedDTOS().get(0));
                errorJobKey = false;
            } else if (this.jobDivMode == OTHER_JOB_DIV_MODE) {
                this.setOtherJobCode(((IJobsDTO)jobFilter.getSelectedDTOS().get(0)).getJobKey());
                this.setOtherJobName(jobFilter.getSelectedDTOS().get(0).getName());
                ((IKwtWorkDataTreeDTO)getPageDTO()).setOtherJobsDTO((IJobsDTO)jobFilter.getSelectedDTOS().get(0));
                errorOtherJobKey = false;
            }
        }
    }

    public void changeJobs(ActionEvent event) {
        IJobsDTO jobDTO = JobDTOFactory.createJobsDTO();
        ((IKwtWorkDataTreeDTO)getPageDTO()).setJobsDTO(null);
        if (jobCode != null && !jobCode.trim().isEmpty()) {
            jobDTO.setJobKey(jobCode);
            try {
                jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getByJobKey(jobDTO);
                ((IKwtWorkDataTreeDTO)getPageDTO()).setJobsDTO(jobDTO);
                setJobName(jobDTO.getName());
                errorJobKey = false;
            } catch (DataBaseException e) {
                errorJobKey = true;
            } catch (SharedApplicationException e) {
                errorJobKey = true;
            }
        }

    }

    public void changeotherJobs(ActionEvent event) {
        IJobsDTO jobDTO = JobDTOFactory.createJobsDTO();
        ((IKwtWorkDataTreeDTO)getPageDTO()).setOtherJobsDTO(null);
        if (otherJobCode != null && !otherJobCode.trim().isEmpty()) {
            jobDTO.setJobKey(otherJobCode);
            try {
                jobDTO = (IJobsDTO)JobClientFactory.getJobsClient().getByJobKey(jobDTO);
                ((IKwtWorkDataTreeDTO)getPageDTO()).setOtherJobsDTO(jobDTO);
                setOtherJobName(jobDTO.getName());
                errorOtherJobKey = false;
            } catch (DataBaseException e) {
                errorOtherJobKey = true;
            } catch (SharedApplicationException e) {
                errorOtherJobKey = true;
            }
        }

    }

    public void filterByCategory(ActionEvent event) {
        event = null;
        setSelectedMinistry(null);
        selectedMiniName = null;
        wrongMinCode = false;
    }

    public void openWorkCentersIntegDiv() {
        initIntegration();
        IMinistriesDTO dto = null;
        try {
            dto =
(IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(OrgEntityKeyFactory.createMinistriesEntityKey(Long.valueOf(getSelectedMinistry())));
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        wcIntegrationBean.setMinName(dto.getName());
        wcIntegrationBean.setCatName(dto.getCatsDTO().getName());
        wcIntegrationBean.setSelectedMinCode(getSelectedMinistry());
        wcIntegrationBean.setSelectedCatCode(getSelectedCategory());
        wcIntegrationBean.setSingleSelectionFlag(true);
    }

    public void initIntegration() {
        wcIntegrationBean.getOkCommandButton().setReRender("integrationDiv1,workCenterPnl,workCenterName,divaddExperPage");
        wcIntegrationBean.setSingleSelectionFlag(true);
        wcIntegrationBean.setReturnMethodName(BACK_BEAN_NAME + ".updateSelectedWorkCenter");

    }

    public void updateSelectedWorkCenter() {
        if (!wcIntegrationBean.getSelectedDTOSList().get(0).equals("")) {
            ((IKwtWorkDataTreeDTO)getPageDTO()).setWorkCentersDTO(null);
            IBasicDTO basic = wcIntegrationBean.getSelectedDTOSList().get(0);
            ((IKwtWorkDataTreeDTO)getPageDTO()).setWorkCentersDTO((IWorkCentersDTO)basic);
            String[] str = basic.getCode().getKey().split("\\*");
            setWorkCenterCode(str[1]);
            errorWorkCenter = false;
        }

    }

    public void changeWorkCenter(ActionEvent ae) {
        IWorkCentersEntityKey workCentersEntityKey =
            OrgEntityKeyFactory.createWorkCentersEntityKey(Long.valueOf(getSelectedMinistry()), workCenterCode);
        IWorkCentersDTO workCentersDTO;
        try {
            workCentersDTO = (IWorkCentersDTO)OrgClientFactory.getWorkCentersClient().getById(workCentersEntityKey);
            ((IKwtWorkDataTreeDTO)getPageDTO()).setWorkCentersDTO(workCentersDTO);
            errorWorkCenter = false;
        } catch (DataBaseException e) {
            errorWorkCenter = true;
        } catch (SharedApplicationException e) {
            errorWorkCenter = true;
        }
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowContent1(true);
        app.setShowLookupAdd(false);
        app.setShowLovDiv(false);
        app.setShowSearch(false);
        app.setShowCustomDiv1(true);
        app.setShowIntegrationDiv1(true);
        return app;
    }

    public void reSetButtonErase() {

        ((IKwtWorkDataTreeDTO)getPageDTO()).setJobsDTO(null);

        System.out.println("tempComponent");


    }

    public void prepareByGovType() {
        ministryList.clear();
        setSelectedMinistry("");
        setSelectedCategory("");
        setPageDTO(InfDTOFactory.createKwtWorkDataTreeDTO());
        setJobCode(null);
        setJobName(null);
        setOtherJobCode(null);
        setOtherJobName(null);
        setWorkCenterCode("");
        perFlag = false;
        fillCatByGovFlag();
        renderDurationErrMsg = false;
        if (govTypeCode.equals(ISystemConstant.ACTIVE_FLAG)) {
            renderJobs = true;
            renderExperDuration = false;
            renderTechJob = true;
            renderJobtypeRadio = false;
        } else {
            jobType = "0";
            renderJobs = true;
            renderExperDuration = true;
            renderTechJob = false;
            renderJobtypeRadio = true;
        }
    }

    public void fillCatByGovFlag() {
        try {
            categoryList = OrgClientFactory.getCatsClient().getCatsByGovFlag(govTypeCode);
            ministryList.clear();
            setSelectedMinistry("");
            setSelectedCategory("");
            ((IKwtWorkDataTreeDTO)getPageDTO()).setWorkCentersDTO(null);
            setWorkCenterCode("");
            setJobType("0");
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

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

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
        return categoryList;
    }

    public void setMinistryList(List<IBasicDTO> ministryList) {
        this.ministryList = ministryList;
    }

    public List<IBasicDTO> getMinistryList() {
        return ministryList;
    }

    private boolean checkExperiencesConflict(Long realCivilId, java.sql.Date fromDate, java.sql.Date untilDate,
                                             List<Long> trxCodList, boolean includeTrx) {
        boolean conflictFound = false;
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        IKwtWorkDataClient kwtWorkDataClient = InfClientFactory.getKwtWorkDataClient();
        ArrayList<IKwtWorkDataTreeDTO> unsavedlist =
            ((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")).getKwtWorkDataDTOList();
        if (unsavedlist != null && unsavedlist.size() > 0) {
            for (IKwtWorkDataTreeDTO dto : unsavedlist) {
                System.out.println(fromDate.toString() + " :::fromDate::: " + dto.getFromDate().toString());
                if (untilDate != null && dto.getUntilDate() != null)
                    System.out.println(untilDate.toString() + " :::untilDate::: " + dto.getUntilDate().toString());
                if (untilDate == null) {
                    if ((dto.getUntilDate() != null &&
                         (fromDate.before(dto.getUntilDate()) || fromDate.equals(dto.getUntilDate()))) ||
                        dto.getUntilDate() == null) {
                        conflictFound = true;
                        break;
                    }
                } else if ((dto.getUntilDate() == null &&
                            ((untilDate.after(dto.getFromDate()) || untilDate.equals(dto.getFromDate())))) ||
                           (dto.getUntilDate() != null &&
                            (fromDate.before(dto.getUntilDate()) || fromDate.equals(dto.getUntilDate())) &&
                            (untilDate.after(dto.getFromDate()) || untilDate.equals(dto.getFromDate())))) {
                    conflictFound = true;
                    break;
                }
            }
        } else {
            try {
                conflictFound =
                        kwtWorkDataClient.checkExperiencesConflict(realCivilId, fromDate, untilDate, trxCodList,
                                                                   includeTrx);
            } catch (DataBaseException e) {

            } catch (SharedApplicationException e) {
            }
        }
        return conflictFound;
    }

    public void calcActualDaysPerRow() {
        IKwtWorkDataTreeDTO dto = ((IKwtWorkDataTreeDTO)getPageDTO());
        if (dto != null && isRenderExperDuration()) {
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
                    setActiveDurationYears(years);
                } else {
                    setActiveDurationYears(0L);
                }
                if (totaldays >= 30) {
                    Long months = totaldays / 30;
                    totaldays = totaldays % 30;
                    if (months.equals(12L)) {
                        setActiveDurationMonths(0L);
                        setActiveDurationYears(1L);
                    } else {
                        setActiveDurationMonths(months);
                    }

                } else {
                    setActiveDurationMonths(0L);
                }
                if (totaldays != null) {
                    setActiveDurationDays(totaldays);
                } else {
                    setActiveDurationDays(0L);
                }

            }
        }
    }

    private void fillPageDTO(WorkDataListBean workDataListBean) {
        IKwtWorkDataTreeDTO savedDto = (IKwtWorkDataTreeDTO)getPageDTO();

        try {
            kwtCitizenDTO =
                    (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenCodeName(getRcivilId());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            if (e.getMessage().equals("NoResultException")) {
                try {
                    kwtCitizenDTO =
                            (IKwtCitizensResidentsDTO)getSharedUtil().deepCopyObject(workDataListBean.getKwtCitizenDTO());
                } catch (Exception f) {
                    f.printStackTrace();
                }
            }
        }
        savedDto.setKwtCitizensResidentsDTO(kwtCitizenDTO);
        try {
            if (selectedMinistry != null && !selectedMinistry.equals("")) {
                savedDto.setMinistriesDTO((IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(OrgEntityKeyFactory.createMinistriesEntityKey(Long.valueOf(selectedMinistry))));
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        if (govTypeCode.equals(ISystemConstant.ACTIVE_FLAG)) {
            //            calcActualDaysPerRow((IKwtWorkDataTreeDTO)getPageDTO());
            savedDto.setActualExpDays(ISystemConstant.NON_ACTIVE_FLAG);
            savedDto.setActualExpMonths(ISystemConstant.NON_ACTIVE_FLAG);
            savedDto.setActualExpYears(ISystemConstant.NON_ACTIVE_FLAG);
        } else {
            if (savedDto.getActualExpDays() == null) {
                savedDto.setActualExpDays(ISystemConstant.NON_ACTIVE_FLAG);
            }
            if (savedDto.getActualExpMonths() == null) {
                savedDto.setActualExpMonths(ISystemConstant.NON_ACTIVE_FLAG);
            }
            if (savedDto.getActualExpYears() == null) {
                savedDto.setActualExpYears(ISystemConstant.NON_ACTIVE_FLAG);
            }
        }
        savedDto.setFirstParent(((IKwtWorkDataTreeDTO)getPageDTO()).getMinistriesDTO().getCode());
        //        savedDto.setTreeLevel(ISystemConstant.ACTIVE_FLAG);
        //        savedDto.setLeafFlag(ISystemConstant.ACTIVE_FLAG);
        //        savedDto.setPisFlag(ISystemConstant.ACTIVE_FLAG);
        savedDto.setPerFlag((perFlag ? 1L : 0L));
        if (perFlag) {
            if (govTypeCode.equals(1L) ||
                (savedDto.getActualExpDays() == null && savedDto.getActualExpMonths() == null &&
                 savedDto.getActualExpYears() == null)) {
                GregorianCalendar fromCal = new GregorianCalendar();
                fromCal.setTime(savedDto.getFromDate());
                GregorianCalendar untilCal = new GregorianCalendar();
                if (savedDto.getUntilDate() == null) {
                    untilCal.setTime(SharedUtils.getCurrentDate());
                } else {
                    untilCal.setTime(savedDto.getUntilDate());
                }
                long diffInMillis = untilCal.getTimeInMillis() - fromCal.getTimeInMillis();
                int ONE_DAY = 24 * 60 * 60 * 1000;
                Long totaldays = (diffInMillis / ONE_DAY);
                if (totaldays >= 365) {
                    Long years = totaldays / 365;
                    totaldays = totaldays % 365;
                    savedDto.setServiceYears(years.intValue());
                }
                if (totaldays >= 30) {
                    Long months = totaldays / 30;
                    totaldays = totaldays % 30;
                    savedDto.setServiceMonths(months.intValue());
                }
                savedDto.setServiceDays(totaldays.intValue());
            } else {
                savedDto.setServiceDays(savedDto.getActualExpDays().intValue());
                savedDto.setServiceMonths(savedDto.getActualExpMonths().intValue());
                savedDto.setServiceYears(savedDto.getActualExpYears().intValue());
            }
        }

        ITrxTypesDTO trxTypesDTO = EmpDTOFactory.createTrxTypesDTO();
        IEntityKey trxTypesEK = EmpEntityKeyFactory.createTrxTypesEntityKey(trxTypesCode);

        trxTypesDTO.setCode(trxTypesEK);
        savedDto.setTrxTypesDTO(trxTypesDTO);
    }


    private boolean validateDuration(IKwtWorkDataTreeDTO savedDto) {
        boolean render = false;
        if (getGovTypeCode().equals(nonGovFlag)) {
            if (savedDto.getActualExpYears() == null)
                savedDto.setActualExpYears(0L);
            if (savedDto.getActualExpMonths() == null)
                savedDto.setActualExpMonths(0L);
            if (savedDto.getActualExpDays() == null)
                savedDto.setActualExpDays(0L);
            if (savedDto.getActualExpYears() > getActiveDurationYears()) {
                render = true;
            } else if (savedDto.getActualExpYears().equals(getActiveDurationYears())) {
                if (savedDto.getActualExpMonths() > getActiveDurationMonths()) {
                    render = true;
                } else if (savedDto.getActualExpMonths().equals(getActiveDurationMonths())) {
                    if (savedDto.getActualExpDays() > getActiveDurationDays()) {
                        render = true;
                    }
                }
            }
        }

        return render;
    }

    public void add() {
        SharedUtilBean shared_util = getSharedUtil();
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());

        try {
            fillPageDTO(((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")));
            if (((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")).isSaveInDB()) {
                setPageDTO(InfClientFactory.getKwtWorkDataClient().addKwtWorkDataTreeDTO(getPageDTO()));
            } else {
                Long serial = null;
                ArrayList<IKwtWorkDataTreeDTO> list =
                    ((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")).getKwtWorkDataDTOList();
                try {
                    if (list != null && list.size() > 0) {
                        serial = Long.valueOf(list.get(list.size() - 1).getCode().getKey()) + 1L;
                    } else {
                        serial = InfClientFactory.getKwtWorkDataClient().findNewId();
                    }
                    getPageDTO().setCode(InfEntityKeyFactory.createKwtWorkDataEntityKey(serial));
                } catch (DataBaseException e) {
                    e.printStackTrace();
                }
                IKwtWorkDataTreeDTO addedDTO = (IKwtWorkDataTreeDTO)getPageDTO();
                addedDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
                if (((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")).getKwtWorkDataDTOList() ==
                    null) {
                    ((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")).setKwtWorkDataDTOList(new ArrayList());
                }
                ((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")).getKwtWorkDataDTOList().add(addedDTO);
                try {
                    workDataListBean.getAll();
                    workDataListBean.buildTree();
                } catch (DataBaseException e) {
                    workDataListBean.setMyTableData(new ArrayList());
                    e.printStackTrace();
                }
            }

            this.setSuccess(true);
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            setSuccess(false);
            this.setErrorMsg(e.getMessage());
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

    public String saveExperience() {
        SharedUtilBean shared_util = getSharedUtil();
        renderDurationErrMsg = false;
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        try {
            if ((getGovTypeCode().equals(govFlag) && ((IKwtWorkDataTreeDTO)getPageDTO()).getJobsDTO() == null) ||
                (getJobType().equals("0") && ((IKwtWorkDataTreeDTO)getPageDTO()).getJobsDTO() == null)) {
                String errorMsgValue =
                    getSharedUtil().messageLocator("com.beshara.csc.gn.inf.integration.presentation.resources.infintegration",
                                                   "job_not_entered");
                shared_util.setErrMsgValue(errorMsgValue);
                return "";
            }
            if (validateDuration(((IKwtWorkDataTreeDTO)getPageDTO()))) {
                renderDurationErrMsg = true;
                return null;
            }

            if (checkExperiencesConflict(getRcivilId(), ((IKwtWorkDataTreeDTO)getPageDTO()).getFromDate(),
                                         ((IKwtWorkDataTreeDTO)getPageDTO()).getUntilDate(), trxCodList, includeTrx)) {
                String errorMsgValue =
                    getSharedUtil().messageLocator("com.beshara.csc.gn.inf.integration.presentation.resources.infintegration",
                                                   "conflict_found_in_experience");
                shared_util.setErrMsgValue(errorMsgValue);
                return "";
            }
            this.add();
            if (isSuccess()) {
                if (((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")).isSaveInDB()) {
                    shared_util.setSuccessMsgValue("SuccessAdds");
                }
                return back();
            }

        } catch (Exception e) {
            e.printStackTrace();
            shared_util.setErrMsgValue("SystemFailureException");
        }
        return null;
    }

    public void saveAndNew() throws DataBaseException {
        SharedUtilBean shared_util = getSharedUtil();
        if ((getGovTypeCode().equals(govFlag) && ((IKwtWorkDataTreeDTO)getPageDTO()).getJobsDTO() == null) ||
            (getJobType().equals("0") && ((IKwtWorkDataTreeDTO)getPageDTO()).getJobsDTO() == null)) {
            String errorMsgValue =
                getSharedUtil().messageLocator("com.beshara.csc.gn.inf.integration.presentation.resources.infintegration",
                                               "job_not_entered");
            shared_util.setErrMsgValue(errorMsgValue);
            return;
        }
        if (validateDuration(((IKwtWorkDataTreeDTO)getPageDTO()))) {
            renderDurationErrMsg = true;
            return;
        }
        if (checkExperiencesConflict(getRcivilId(), ((IKwtWorkDataTreeDTO)getPageDTO()).getFromDate(),
                                     ((IKwtWorkDataTreeDTO)getPageDTO()).getUntilDate(), trxCodList, includeTrx)) {
            String errorMsgValue =
                getSharedUtil().messageLocator("com.beshara.csc.gn.inf.integration.presentation.resources.infintegration",
                                               "conflict_found_in_experience");
            shared_util.setErrMsgValue(errorMsgValue);
            setSuccess(false);
            return;
        } else {
            this.add();
            this.reInitializePageDTO();
            resetFields();
            cancelSearch();
            govTypeCode = govFlag;
            jobType = "0";
            prepareByGovType();
            initJobFilter();
        }

    }

    public void reInitializePageDTO() {
        setPageDTO(InfDTOFactory.createKwtWorkDataTreeDTO());
    }

    public void resetFields() {
        setSelectedCategory("");
        setCategoryList(new ArrayList<IBasicDTO>());
        setSelectedMinistry("");
        setMinistryList(new ArrayList<IBasicDTO>());
        setWorkCenterCode("");
        setJobCode("");
        setJobName("");
        setOtherJobCode("");
        setPerFlag(false);
        renderDurationErrMsg = false;
    }

    public String back() {
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());

        return integrationBean.goFrom();
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

    public void setJobExcludedCode(String jobExcludedCode) {
        this.jobExcludedCode = jobExcludedCode;
    }

    public String getJobExcludedCode() {
        return jobExcludedCode;
    }


    public void setGovTypeCode(Long govTypeCode) {
        this.govTypeCode = govTypeCode;
    }

    public Long getGovTypeCode() {
        return govTypeCode;
    }

    public void setWorkCenterCode(String workCenterCode) {
        this.workCenterCode = workCenterCode;
    }

    public String getWorkCenterCode() {
        return workCenterCode;
    }

    public void setErrorWorkCenter(Boolean errorWorkCenter) {
        this.errorWorkCenter = errorWorkCenter;
    }

    public Boolean getErrorWorkCenter() {
        return errorWorkCenter;
    }

    public void setJobDivMode(int jobDivMode) {
        this.jobDivMode = jobDivMode;
    }

    public int getJobDivMode() {
        return jobDivMode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setOtherJobCode(String otherJobCode) {
        this.otherJobCode = otherJobCode;
    }

    public String getOtherJobCode() {
        return otherJobCode;
    }

    public void setOtherJobName(String otherJobName) {
        this.otherJobName = otherJobName;
    }

    public String getOtherJobName() {
        return otherJobName;
    }

    public void setErrorJobKey(Boolean errorJobKey) {
        this.errorJobKey = errorJobKey;
    }

    public Boolean getErrorJobKey() {
        return errorJobKey;
    }

    public void setErrorOtherJobKey(Boolean errorOtherJobKey) {
        this.errorOtherJobKey = errorOtherJobKey;
    }

    public Boolean getErrorOtherJobKey() {
        return errorOtherJobKey;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobType() {
        return jobType;
    }

    public void setPerFlag(boolean perFlag) {
        this.perFlag = perFlag;
    }

    public boolean isPerFlag() {
        return perFlag;
    }

    public void setRenderJobs(boolean renderJobs) {
        this.renderJobs = renderJobs;
    }

    public boolean isRenderJobs() {
        return renderJobs;
    }

    public void setRenderExperDuration(boolean renderExperDuration) {
        this.renderExperDuration = renderExperDuration;
    }

    public boolean isRenderExperDuration() {
        return renderExperDuration;
    }

    public void setRenderTechJob(boolean renderTechJob) {
        this.renderTechJob = renderTechJob;
    }

    public boolean isRenderTechJob() {
        return renderTechJob;
    }

    public void setRenderJobtypeRadio(boolean renderJobtypeRadio) {
        this.renderJobtypeRadio = renderJobtypeRadio;
    }

    public boolean isRenderJobtypeRadio() {
        return renderJobtypeRadio;
    }

    public void setTrxCodList(List<Long> trxCodList) {
        this.trxCodList = trxCodList;
    }

    public List<Long> getTrxCodList() {
        return trxCodList;
    }

    public void setIncludeTrx(boolean includeTrx) {
        this.includeTrx = includeTrx;
    }

    public boolean isIncludeTrx() {
        return includeTrx;
    }


    public void setKwtCitizenDTO(IKwtCitizensResidentsDTO kwtCitizenDTO) {
        this.kwtCitizenDTO = kwtCitizenDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizenDTO() {
        return kwtCitizenDTO;
    }

    public void setRcivilId(Long rcivilId) {
        this.rcivilId = rcivilId;
    }

    public Long getRcivilId() {
        return rcivilId;
    }

    public void setTrxTypesCode(Long trxTypesCode) {
        this.trxTypesCode = trxTypesCode;
    }

    public Long getTrxTypesCode() {
        return trxTypesCode;
    }

    public void setWorkDataListBean(WorkDataListBean workDataListBean) {
        this.workDataListBean = workDataListBean;
    }

    public WorkDataListBean getWorkDataListBean() {
        return workDataListBean;
    }

    public void openSearchMinistryDiv() {

        String returnSearchMethodName = BACK_BEAN_NAME + ".returnSearchMethod";
        getSearchMinistryHelper().setRenderedCmpIds("ministryName,minFilterationId,errorCodePanelId2,workCenterPnl");

        getSearchMinistryHelper().initDiv(Long.valueOf(selectedCategory), returnSearchMethodName);
    }

    public void returnSearchMethod() {
        List list = getSearchMinistryHelper().getSelectedDTOS();
        if (list != null && !list.isEmpty()) {
            IMinistriesDTO ministryDTO = (IMinistriesDTO)list.get(0);
            selectedMinistry = ministryDTO.getCode().getKey();
            selectedMiniName = ministryDTO.getName();
        }

        changeMinistry(null);
    }

    public void changeMinistry(ActionEvent ae) {
        wrongMinCode = false;
        if (selectedCategory != null && !selectedCategory.equals("") && selectedMinistry != null &&
            !selectedMinistry.equals("")) {
            if (ae != null) {
                try {
                    IMinistriesDTO ministryDTO =
                        (IMinistriesDTO)OrgClientFactory.getMinistriesClient().getCodeNameByCatCodeAndMinCode(Long.valueOf(selectedCategory),
                                                                                                              Long.valueOf(selectedMinistry));
                    if (ministryDTO != null) {
                        selectedMiniName = ministryDTO.getName();
                        selectedMinistry = ministryDTO.getCode().getKey();
                    } else {
                        wrongMinCode = true;
                        selectedMiniName = null;
                        return;
                    }
                } catch (Exception e) {
                    wrongMinCode = true;
                    selectedMiniName = null;
                    return;
                }
            }

        }
    }

    public void setSearchMinistryHelper(SearchMinistryWithPagingHelper SearchMinistryHelper) {
        this.searchMinistryHelper = SearchMinistryHelper;
    }

    public SearchMinistryWithPagingHelper getSearchMinistryHelper() {
        if (searchMinistryHelper == null) {
            searchMinistryHelper = new SearchMinistryWithPagingHelper();
        }
        return searchMinistryHelper;
    }

    public void setWrongMinCode(boolean wrongMinCode) {
        this.wrongMinCode = wrongMinCode;
    }

    public boolean isWrongMinCode() {
        return wrongMinCode;
    }

    public void setSelectedMiniName(String selectedMiniName) {
        this.selectedMiniName = selectedMiniName;
    }

    public String getSelectedMiniName() {
        return selectedMiniName;
    }

    public void setActiveDurationYears(Long activeDurationYears) {
        this.activeDurationYears = activeDurationYears;
    }

    public Long getActiveDurationYears() {
        return activeDurationYears;
    }

    public void setActiveDurationMonths(Long activeDurationMonths) {
        this.activeDurationMonths = activeDurationMonths;
    }

    public Long getActiveDurationMonths() {
        return activeDurationMonths;
    }

    public void setActiveDurationDays(Long activeDurationDays) {
        this.activeDurationDays = activeDurationDays;
    }

    public Long getActiveDurationDays() {
        return activeDurationDays;
    }

    public void setRenderDurationErrMsg(boolean renderDurationErrMsg) {
        this.renderDurationErrMsg = renderDurationErrMsg;
    }

    public boolean isRenderDurationErrMsg() {
        return renderDurationErrMsg;
    }
}
