package com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.details.advanceSearch;

import com.beshara.base.client.ServiceNotAvailableException;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.paging.impl.PagingResponseDTO;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.EmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStatusDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.inf.business.client.IKwtCitizensResidentsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.bnk.business.client.BnkClientFactory;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobSearchCriteriaDTO;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersSearchCriteriaDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.reg.business.client.IEmpDecisionsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.details.DecisionEmployeesModel;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.datascroller.HtmlDataScroller;

public class AdvanceEmpSearch extends ManyToManyDetailsMaintain {

    private Long categoryID;
    private Long ministryID;
    private Long kuwityType;
    private Long bankID;

    private IEmployeeSearchDTO employeeSearchDTO = EmpDTOFactory.createEmployeeSearchDTO();
    EmpEmployeeSearchDTO empEmployeeSearchDTO = new EmpEmployeeSearchDTO();
    private Integer newnoOfTableRows = 10;
    private List<IBasicDTO> categories = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> ministries = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> workMinistries = new ArrayList<IBasicDTO>();
    private List<IJobsDTO> technicalJobs = new ArrayList<IJobsDTO>();
    private List<IBasicDTO> hireCurrentStatuses = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> hireStatuses = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> banks = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> branches = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> jobCategories = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> jobGroupes = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> currentDegrees = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> budgetTypes = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> resdientTypes = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> specialCaseTyps = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> nationalties = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> capTyps = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> governments = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> genderTyps = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> maritalStatusTyps = new ArrayList<IBasicDTO>();

    private List<IBasicDTO> relgionTyps = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> hireTypes = new ArrayList<IBasicDTO>();

    private static final Long KUWITY = 1L;
    private static final Long NON_KUWITY = 0L;
    private static final Long NATIONLALTY_KUWITY = ISystemConstant.KUWAIT_NATIONALITY;

    // To allow generic in search page by sherif.omar 28-07-2008
    //  private String backBtnMethod = "";
    private List savedData = new ArrayList();
    private boolean singleSelection = false;
    private Long tempCodeKey;
    private String tempCodeKeyStr = "";
    private boolean backBtnClicked = false;
    private String returnMethod;

    // added by Nora to generlize el search page
    // flag to make result of search at the same page or open search result with another page 
    private boolean showResultWithinPage = true;
    //if you use simple search @ your main screen and use wants to use result search page so u have to define what is the method u wants to call after cancel seach 
    private String cancelSimpleSearchMethod = "";
    //if you use bussiness search method different from default one exist here u must set this method 
    private String searchMethod = "";
    private String backMethod = "";

    //set of flags based on it =>we render some fileds @ 3 tabs As Change request requirements
    private boolean showCategoryCB = true;
    private boolean showMinistryCB = true;
    private boolean showWorkEndDate = true;
    private boolean showHireStatus = true;
    private boolean showCurrentHireStatus = true;
    private boolean showWorkCenterLovDiv = false;
    private boolean showJobLovDiv = false;
    // added by Nora to active third tab that contains financial data 
    private String selectedCaderCode;
    private String selectedGroupCode;
    // added by Nora to enable scrolling
    private int pageIndexAdd = 0;
    private HtmlDataScroller dataScroller = new HtmlDataScroller();

    // added by nora to refers to paths of complete search page and result search paths 
    private static final String completeSearchPath = 
        "/module/jsps/decisionCycle/shared/employeesearch/employeeadd.jsp";
    private static final String resultSearchPath = 
        "/module/jsps/decisionCycle/shared/employeesearch/searchresult.jsp";
    private PagingResponseDTO bsnPagingResponseDTO;
    private int availableListSizeBuffer = 0;
    private boolean searchInf;

    public AdvanceEmpSearch() {

        //set default value on first time and make el default kuwity
        if (kuwityType == null) {
            kuwityType = KUWITY;
            employeeSearchDTO.setKuwaiti(true);
            employeeSearchDTO.setNationality(null);
        }

        if (getCurrentDetails() == null)
            setCurrentDetails(new ArrayList());
        // added by nora for adding lov div 
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList());
        setDivMainContentMany("");
        setUsingBsnPaging(true);
    }


    public AppMainLayout appMainLayoutBuilder() {

        AppMainLayout layout = super.appMainLayoutBuilder();

        layout.setShowLookupAdd(false);
        layout.setShowSearch(false);
        layout.setShowLookupEdit(false);
        layout.setShowDelAlert(false);
        layout.setShowDelConfirm(false);
        layout.setShowbar(false);
        layout.setShowNavigation(false);

        layout.setShowContent1(true);

        if ((showWorkCenterLovDiv || showJobLovDiv) && (FacesContext.getCurrentInstance().getViewRoot().getViewId().equals(completeSearchPath)))
            layout.setShowLovDiv(true);

        if (!showResultWithinPage && (FacesContext.getCurrentInstance().getViewRoot().getViewId().equals(completeSearchPath))) {
            layout.setShowdatatableContent(false);
            layout.setShowNavigation(true);


        } else if (!showResultWithinPage && (FacesContext.getCurrentInstance().getViewRoot().getViewId().equals(resultSearchPath))) {
            layout.setShowContent1(false);
            layout.setShowNavigation(true);
            layout.setShowpaging(true);
            layout.setShowCustomDiv1(true);

        }
        return layout;
    }


    public com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO getAllWithPaging(PagingRequestDTO pagingRequestDTO) {

        return new com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO();

    }

    public void setPageIndexAdd(int pageIndexAdd) {
        this.pageIndexAdd = pageIndexAdd;
    }

    public Integer getPageIndexAdd() {
        if (dataScroller != null) {
            pageIndexAdd = dataScroller.getPageIndex();
        }
        return pageIndexAdd;
    }


    /**
     * Purpose:called when user open  work center div 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void openWorkCenter() {


        if (!showCategoryCB && !showMinistryCB && (workMinistries == null))
            ministryID = getManagedConstantsBean().getMinCode();

        fillWorkMinistries();
        getLovBaseBean().setMyTableData(workMinistries);

        getLovBaseBean().getOkCommandButton().setReRender("employees_work_ministry");
        getLovBaseBean().setReturnMethodName("empDecisionCycleAddBean.addWorkCenter");
        getLovBaseBean().setSearchMethod("empDecisionCycleAddBean.searchWorkCenter");
        getLovBaseBean().setSelectedDTOS(new ArrayList());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("empDecisionCycleAddBean.cancelWorkSearch");
    }

    /**
     * Purpose:called when user open  Job div 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  11-1-2009
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void openJob() {

        getLovBaseBean().setMyTableData(getTechnicalJobs());

        getLovBaseBean().getOkCommandButton().setReRender("employees_special_job");
        getLovBaseBean().setReturnMethodName("empDecisionCycleAddBean.addJob");
        getLovBaseBean().setSearchMethod("empDecisionCycleAddBean.searchJob");
        getLovBaseBean().setSelectedDTOS(new ArrayList());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("empDecisionCycleAddBean.cancelJobSearch");
    }

    /**
     * Purpose:called when user select one row and press ok to add object
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  11-1-2009
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void addWorkCenter() {
        if (getLovBaseBean().getSelectedDTOS() != null && 
            getLovBaseBean().getSelectedDTOS().get(0) != null && 
            getLovBaseBean().getSelectedDTOS().get(0).getCode() != null) {
            employeeSearchDTO.setWorkCenterCode(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString());
        }
    }

    /**
     * Purpose:called when user select one row and press ok to add object
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  11-1-2009
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void addJob() {
        if (getLovBaseBean().getSelectedDTOS() != null && 
            getLovBaseBean().getSelectedDTOS().get(0) != null && 
            getLovBaseBean().getSelectedDTOS().get(0).getCode() != null) {
            employeeSearchDTO.setTechJobCode(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString());
        }
    }

    /**
     * Purpose:called when user search by name or code 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  11-1-2009
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void searchJob(Object searchType, Object searchQuery) {

        if (searchQuery != null && !searchQuery.toString().equals("") && 
            searchType != null && !searchType.equals("")) {
            getLovBaseBean().setSearchMode(true);
            JobSearchCriteriaDTO jobSearchDTO = new JobSearchCriteriaDTO();

            try {

                if (searchType.toString().equals("0")) {
                    jobSearchDTO.setJobCode(searchQuery.toString());
                    getLovBaseBean().setMyTableData(JobClientFactory.getJobsClient().search(jobSearchDTO));
                } else if (searchType.toString().equals("1")) {
                    jobSearchDTO.setJobName(searchQuery.toString());
                    getLovBaseBean().setMyTableData(JobClientFactory.getJobsClient().search(jobSearchDTO));
                }
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

    /**
     * Purpose:called when user cancel search 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  11-1-2009
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public String cancelJobSearch() {
        getLovBaseBean().setMyTableData(getTechnicalJobs());
        return "";
    }

    /**
     * Purpose:called when user search by name or code 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  11-1-2009
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void searchWorkCenter(Object searchType, Object searchQuery) {

        if (searchQuery != null && !searchQuery.toString().equals("") && 
            searchType != null && !searchType.equals("")) {
            getLovBaseBean().setSearchMode(true);

            IWorkCentersSearchCriteriaDTO searchDTO = OrgDTOFactory.createWorkCentersSearchCriteriaDTO();
            searchDTO.setMinCode(ministryID);
            try {

                if (searchType.toString().equals("0")) {
                    searchDTO.setWorkCode(searchQuery.toString());
                    getLovBaseBean().setMyTableData(OrgClientFactory.getWorkCentersClient().search(searchDTO));
                } else if (searchType.toString().equals("1")) {
                    searchDTO.setWorkName(searchQuery.toString());
                    getLovBaseBean().setMyTableData(OrgClientFactory.getWorkCentersClient().search(searchDTO));
                }
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

    /**
     * Purpose:called when user cancel search 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  11-1-2009
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public String cancelWorkSearch() {
        getLovBaseBean().setMyTableData(workMinistries);
        return "";
    }


    public Boolean backBtnClicked() { // need to invoke from backFromSearch() Method.
        if (isBackBtnClicked()) {
            super.getSelectedDTOS().clear();
            return true;
        }
        return false;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setMinistryID(Long ministryID) {
        this.ministryID = ministryID;
    }

    public Long getMinistryID() {
        return ministryID;
    }

    public void selectedRadioButton(ActionEvent event) throws Exception {

        this.getSelectedAvailableDetails().clear();
        IBasicDTO selected = 
            (IBasicDTO)this.getAvailableDataTable().getRowData();
        this.getSelectedAvailableDetails().add(selected);

    }


    ///////////////// start of related combo boxes methods that fill related combo boxs ,categories->ministries->work ministry/////////////////////////////////////////

    /**
     * Purpose:fill cattegories  combo box 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora
     * 1.2 - Date:   21/7/2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public

    void setCategories(List<IBasicDTO> categories) {
        this.categories = categories;
    }

    public List<IBasicDTO> getCategories() {
        if (categories.size() == 0) {
            try {
                categories = OrgClientFactory.getCatsClient().getCodeName();
            } catch (DataBaseException e) {
                categories = new ArrayList();
                e.printStackTrace();
            }

            catch (Exception e) {
                categories = new ArrayList();
                e.printStackTrace();
            }
        }
        if (categories == null) {
            categories = new ArrayList();
        }
        return categories;
    }

    /**
     * Purpose:is value change listener method that fill ministries combo box for selected category 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora
     * 1.2 - Date:   21/7/2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void fillMinistries() {

        try {
            if (categoryID != null)
                ministries = OrgClientFactory.getMinistriesClient().getAllByCategory(categoryID);
            else
                ministries = new ArrayList();

        } catch (SharedApplicationException e) {
            ministries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        } catch (DataBaseException e) {
            ministries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        } catch (Exception e) {
            ministries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        }
        if (ministries == null)
            ministries = new ArrayList();

        setMinistryID(null);
        workMinistries = new ArrayList();
        setEmployeeSearchDTO(EmpDTOFactory.createEmployeeSearchDTO());
    }

    /**
     * Purpose:is value change listener method that fill work ministries combo box for selected ministry 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora
     * 1.2 - Date:   21/7/2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void fillWorkMinistries() {
        //new MinistriesEntityKey()
        try {
            if (ministryID != null)
                workMinistries = OrgClientFactory.getWorkCentersClient().getAllByMinistry(OrgEntityKeyFactory.createMinistriesEntityKey(ministryID));
            else
                workMinistries = new ArrayList();


        } catch (SharedApplicationException e) {
            workMinistries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        } catch (DataBaseException e) {
            workMinistries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        } catch (Exception e) {
            workMinistries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        }

        if (workMinistries == null)
            workMinistries = new ArrayList();

        setEmployeeSearchDTO(EmpDTOFactory.createEmployeeSearchDTO());

    }


    public void setMinistries(List<IBasicDTO> ministries) {
        this.ministries = ministries;
    }

    public List<IBasicDTO> getMinistries() {
        return ministries;
    }

    public void setWorkMinistries(List<IBasicDTO> workMinistries) {
        this.workMinistries = workMinistries;
    }

    /**
     * Purpose:
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  06-1-2009
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public List<IBasicDTO> getWorkMinistries() {
        if (!showCategoryCB && !showMinistryCB && 
            ((workMinistries == null) || (workMinistries.size() == 0))) {
            ministryID = getManagedConstantsBean().getMinCode();
            fillWorkMinistries();
        }
        return workMinistries;
    }
    ///////////////////End Of related Combo boxes//////////////////////////////////////////////////////////////////


    public void setTechnicalJobs(List<IJobsDTO> technicalJobs) {
        this.technicalJobs = technicalJobs;
    }

    public List<IJobsDTO> getTechnicalJobs() {
        if (technicalJobs.size() == 0) {
            try {
                technicalJobs = JobClientFactory.getJobsClient().getCodeName();
            } catch (DataBaseException e) {
                technicalJobs = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                technicalJobs = new ArrayList();
                e.printStackTrace();
            }
        }
        if (technicalJobs == null)
            technicalJobs = new ArrayList();
        return technicalJobs;
    }


    public void setHireCurrentStatuses(List<IBasicDTO> hireCurrentStatuses) {
        this.hireCurrentStatuses = hireCurrentStatuses;
    }

    public List<IBasicDTO> getHireCurrentStatuses() {
        if (hireCurrentStatuses.size() == 0) {
            try {
                hireCurrentStatuses = EmpClientFactory.getHireStagesClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
                hireCurrentStatuses = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                hireCurrentStatuses = new ArrayList();
            }
        }
        if (hireCurrentStatuses == null)
            hireCurrentStatuses = new ArrayList();
        return hireCurrentStatuses;
    }

    public void setHireStatuses(List<IBasicDTO> hireStatuses) {
        this.hireStatuses = hireStatuses;
    }

    public List<IBasicDTO> getHireStatuses() {
        if (hireStatuses.size() == 0) {
            try {
                hireStatuses = EmpClientFactory.getHireStatusClient().getCodeName();
            } catch (DataBaseException e) {
                hireStatuses = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                hireStatuses = new ArrayList();
                e.printStackTrace();
            }
        }
        if (hireStatuses == null)
            hireStatuses = new ArrayList();
        return hireStatuses;
    }


    ///////////////////start of related methods  fill related combo boxes banks ->its branches///////////////////////////////

    public void setBanks(List<IBasicDTO> banks) {
        this.banks = banks;
    }

    public List<IBasicDTO> getBanks() {
        if (banks.size() == 0) {
            try {
                banks = BnkClientFactory.getBanksClient().getCodeName();
            } catch (DataBaseException e) {
                banks = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            } catch (Exception e) {
                banks = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            }
        }
        if (banks == null)
            banks = new ArrayList();
        return banks;
    }

    public void setBranches(List<IBasicDTO> branches) {
        this.branches = branches;
    }

    public List<IBasicDTO> getBranches() {
        return branches;
    }

    public void fillBranches() {
        try {
            if (bankID != null && !bankID.equals(""))
                branches = BnkClientFactory.getBankBranchesClient().getCodeName(bankID);
            else
                branches = new ArrayList<IBasicDTO>();
        } catch (DataBaseException e) {
            branches = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        } catch (Exception e) {
            branches = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        }
        if (branches == null)
            branches = new ArrayList();
    }
    ///////////////////End  of related methods  fill related combo boxes banks ->its branches///////////////////////////////

    public void setJobCategories(List<IBasicDTO> jobCategories) {
        this.jobCategories = jobCategories;
    }

    public List<IBasicDTO> getJobCategories() {

        if (jobCategories == null || jobCategories.size() == 0) {
            try {
                jobCategories = SalClientFactory.getSalElementGuidesClient().getCaderCodeName();
            } catch (DataBaseException e) {
                jobCategories = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                jobCategories = new ArrayList();
                e.printStackTrace();
            }
        }
        return jobCategories;
    }

    /**
     * Purpose:filter group list using selected cader code 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora
     * 1.2 - Date:  11-1-2009
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void filterByCader() {

        if (selectedCaderCode != null && !selectedCaderCode.equals("")) {
            try {
                // jobGroupes=SalClientFactory.getSalElementGuidesClient().getGroupCodeName(Long.valueOf(selectedCaderCode));
                // set selected cader to mapped attribute in selectedPromotionTrxProcessDTO
                jobGroupes = SalClientFactory.getSalElementGuidesClient().getGroupCodeName(Long.valueOf(selectedCaderCode));
                selectedGroupCode = "";
                employeeSearchDTO.setElmguideCode(null);
                currentDegrees = new ArrayList();
            } catch (DataBaseException e) {

                selectedGroupCode = "";
                employeeSearchDTO.setElmguideCode(null);
                jobGroupes = new ArrayList();
                currentDegrees = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                selectedGroupCode = "";
                employeeSearchDTO.setElmguideCode(null);
                jobGroupes = new ArrayList();
                currentDegrees = new ArrayList();
                e.printStackTrace();
            }
        } else {
            selectedGroupCode = "";
            employeeSearchDTO.setElmguideCode(null);
            jobGroupes = new ArrayList();
            currentDegrees = new ArrayList();

        }

    }

    /**
     * Purpose:filter degrees with selected group
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  11-1-2009
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void obtainDegreeByGroup() {

        if (selectedGroupCode != null && !selectedGroupCode.equals("")) {
            try {
                currentDegrees = SalClientFactory.getSalElementGuidesClient().getDegreesCodeNameByGroup(Long.valueOf(selectedGroupCode));
            }

            catch (DataBaseException e) {
                currentDegrees = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                currentDegrees = new ArrayList();
                e.printStackTrace();
            }
        } else {
            currentDegrees = new ArrayList();
        }
    }

    public void setJobGroupes(List<IBasicDTO> jobGroupes) {
        this.jobGroupes = jobGroupes;
    }

    public List<IBasicDTO> getJobGroupes() {
        return jobGroupes;
    }

    public void setCurrentDegrees(List<IBasicDTO> currentDegrees) {
        this.currentDegrees = currentDegrees;
    }

    public List<IBasicDTO> getCurrentDegrees() {
        return currentDegrees;
    }

    public void setBudgetTypes(List<IBasicDTO> budgetTypes) {
        this.budgetTypes = budgetTypes;
    }

    public List<IBasicDTO> getBudgetTypes() {
        if (budgetTypes == null || budgetTypes.size() == 0) {
            try {
                budgetTypes = BgtClientFactory.getBgtTypesClient().getCodeName();
            } catch (Exception e) {
                budgetTypes = new ArrayList();
                e.printStackTrace();
            }
        }
        return budgetTypes;
    }

    public List<IBasicDTO> getResdientTypes() {
        if (resdientTypes.size() == 0) {
            try {
                resdientTypes = InfClientFactory.getResidentTypeClient().getCodeName();
            } catch (DataBaseException e) {
                resdientTypes = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            } catch (Exception e) {
                resdientTypes = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            }
        }
        if (resdientTypes == null)
            resdientTypes = new ArrayList();
        return resdientTypes;
    }

    public void setSpecialCaseTyps(List<IBasicDTO> specialCaseTyps) {
        this.specialCaseTyps = specialCaseTyps;
    }

    public List<IBasicDTO> getSpecialCaseTyps() {
        if (specialCaseTyps.size() == 0) {
            try {
                specialCaseTyps = InfClientFactory.getSpecialCaseTypesClient().getCodeName();
            } catch (DataBaseException e) {
                specialCaseTyps = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            } catch (Exception e) {
                specialCaseTyps = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            }
        }
        if (specialCaseTyps == null)
            specialCaseTyps = new ArrayList();
        return specialCaseTyps;
    }

    public void setNationalties(List<IBasicDTO> nationalties) {
        this.nationalties = nationalties;
    }

    public List<IBasicDTO> getNationalties() {
        if (nationalties.size() == 0) {
            try {
                nationalties = InfClientFactory.getCountriesClient().getCodeName();
            } catch (DataBaseException e) {
                nationalties = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            } catch (Exception e) {
                nationalties = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            }
        }
        if (nationalties == null)
            nationalties = new ArrayList();
        return nationalties;
    }

    public void setCapTyps(List<IBasicDTO> capTyps) {
        this.capTyps = capTyps;
    }

    public List<IBasicDTO> getCapTyps() {
        if (capTyps.size() == 0) {
            try {
                capTyps = InfClientFactory.getHandicapStatusClient().getCodeName();
            } catch (DataBaseException e) {
                capTyps = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            } catch (Exception e) {
                capTyps = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            }
        }
        if (capTyps == null)
            capTyps = new ArrayList();
        return capTyps;
    }


    public void setGovernments(List<IBasicDTO> governments) {
        this.governments = governments;
    }

    public List<IBasicDTO> getGovernments() {
        if (governments.size() == 0) {
            try {
                governments = InfClientFactory.getKwMapClient().getCodeName();
            } catch (DataBaseException e) {
                governments = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            } catch (Exception e) {
                governments = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            }
        }
        if (governments == null)
            governments = new ArrayList<IBasicDTO>();
        return governments;
    }

    public void setGenderTyps(List<IBasicDTO> genderTyps) {
        this.genderTyps = genderTyps;
    }

    public List<IBasicDTO> getGenderTyps() {
        if (genderTyps.size() == 0) {
            try {
                genderTyps = InfClientFactory.getGenderTypesClient().getCodeName();
            } catch (DataBaseException e) {
                genderTyps = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            } catch (Exception e) {
                genderTyps = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            }
        }
        if (genderTyps == null)
            genderTyps = new ArrayList<IBasicDTO>();
        return genderTyps;
    }

    public void setMaritalStatusTyps(List<IBasicDTO> maritalStatusTyps) {
        this.maritalStatusTyps = maritalStatusTyps;
    }

    public List<IBasicDTO> getMaritalStatusTyps() {
        if (maritalStatusTyps.size() == 0) {
            try {
                maritalStatusTyps = InfClientFactory.getMaritalStatusClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
                maritalStatusTyps = new ArrayList<IBasicDTO>();
            } catch (Exception e) {
                e.printStackTrace();
                maritalStatusTyps = new ArrayList<IBasicDTO>();
            }
        }
        if (maritalStatusTyps == null)
            maritalStatusTyps = new ArrayList<IBasicDTO>();
        return maritalStatusTyps;
    }

    public void setResdientTypes(List<IBasicDTO> resdientTypes) {
        this.resdientTypes = resdientTypes;
    }

    public void setRelgionTyps(List<IBasicDTO> relgionTyps) {
        this.relgionTyps = relgionTyps;
    }

    public List<IBasicDTO> getRelgionTyps() {
        if (relgionTyps.size() == 0) {
            try {
                relgionTyps = InfClientFactory.getReligionsClient().getCodeName();
            } catch (DataBaseException e) {
                relgionTyps = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                relgionTyps = new ArrayList();
                e.printStackTrace();
            }
        }
        if (relgionTyps == null)
            relgionTyps = new ArrayList();
        return relgionTyps;
    }

    public void setHireTypes(List<IBasicDTO> hireTypes) {
        this.hireTypes = hireTypes;
    }

    public List<IBasicDTO> getHireTypes() {
        if (hireTypes.size() == 0) {
            try {
                hireTypes = (List<IBasicDTO>)EmpClientFactory.getHireTypesClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
                hireTypes = new ArrayList();
            } catch (Exception e) {
                e.printStackTrace();
                hireTypes = new ArrayList();
            }
        }
        if (hireTypes == null)
            hireTypes = new ArrayList();
        return hireTypes;
    }


    public void setEmployeeSearchDTO(IEmployeeSearchDTO employeeSearchDTO) {
        this.employeeSearchDTO = employeeSearchDTO;
    }

    public IEmployeeSearchDTO getEmployeeSearchDTO() {
        return employeeSearchDTO;
    }

    public void changeKuwityType() {
        if (employeeSearchDTO.getNationality() == KUWITY) {
            resetNonKuwityData();
            employeeSearchDTO.setKuwaiti(true);
        } else {
            employeeSearchDTO.setKuwaiti(false);
        }
    }

    private void resetNonKuwityData() {
        employeeSearchDTO.setNationality(null);
        employeeSearchDTO.setPassportNo(null);
        employeeSearchDTO.setResidentTypeCode(null);
        employeeSearchDTO.setEndResidentDate(null);
        employeeSearchDTO.setKuwaiti(null);
    }

    public void resetSearchCriteria() {
        //TODO
    }

    public void setKuwityType(Long kuwityType) {
        this.kuwityType = kuwityType;
    }

    public Long getKuwityType() {
        return kuwityType;
    }

    //    public void getListAvailable() throws DataBaseException, SharedApplicationException {
    //        if(getMasterEntityKey()!=null){
    //         setAvailableDetails(new ArrayList());
    //        }
    //    }

    /**
     * Purpose:called when user press search button and if you put searchMethod it will make method binding
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  11-1-2009
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void searchAvailable() throws DataBaseException, 
                                         SharedApplicationException {
        super.setSearchMode(true);
        setUpdateMyPagedDataModel(true);
        //setUpdateMyPagedDataModel(true);
        setPagingRequestDTO(new PagingRequestDTO("filterSearchByEmpWithPaging"));
        setOldPageIndex(0);
        setCurrentPageIndex(1);
        if (!showResultWithinPage) {
            getSharedUtil().handleNavigation("Advanced_Result_EmpDecision_Search_Page");
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO filterSearchByEmpWithPaging(PagingRequestDTO pagingRequest) {
        IPagingResponseDTO bsnResponseDTO;
        if (getPagingRequestDTO() != null) {
            getPagingRequestDTO().setSearchDTO(employeeSearchDTO);
        }
        if (searchInf) {
            bsnResponseDTO = getSearchByInfWithPaging(pagingRequest);
        } else {
            bsnResponseDTO = getSearchByEmpWithPaging(pagingRequest);
        }

        com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO pagingResponseDTO = 
            null;

        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = 
                    new com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = 
                    new com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO(bsnResponseDTO.getBasicDTOList());
            if (getCurrentPageIndex() == 1) {
                pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                setAvailableListSizeBuffer(bsnResponseDTO.getCount().intValue());
                setAvailableDetails(bsnResponseDTO.getBasicDTOList());
                // pagingRequest.setParams(new Object[] { bsnResponseDTO.getCount() });
                getPagingRequestDTO().setParams(new Object[] { bsnResponseDTO.getCount() });
            } else {
                setAvailableDetails(bsnResponseDTO.getBasicDTOList());
                pagingResponseDTO.setTotalListSize(((Long)getPagingRequestDTO().getParams()[0]).intValue());
            }
        }
        setSearchMode(true);
        this.removeCurrentFromAvailable();
        //            this.goFirstPage(this.getAvailableDataTable());
        /// setCheckAllFlagAvailable(false);
        this.getFrm();
        return pagingResponseDTO;
    }


    private PagingResponseDTO getSearchByEmpWithPaging(PagingRequestDTO pagingRequestDTO) {

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
            //add ur sorting columns
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }
        try {
            bsnPagingResponseDTO = (PagingResponseDTO)(((IEmpDecisionsClient)RegClientFactory.getEmpDecisionsClient()).filterAvailableEntitiesUsingPaging(getPagingRequestDTO().getSearchDTO(), 
                                                                                                                                                          bsnPagingRequestDTO));
            List<IBasicDTO> result = bsnPagingResponseDTO.getBasicDTOList();
            for (int y = 0; y < result.size(); y++) {
                for (int i = 0; i < getSelectedAvailableDetails().size(); 
                     i++) {
                    if ((((IEmployeesEntityKey)((IEmployeesDTO)getSelectedAvailableDetails().get(i)).getCode()).getCivilId()).equals((((IEmployeesEntityKey)((IEmployeesDTO)result.get(y)).getCode()).getCivilId()))) {
                        ((IEmployeesDTO)result.get(y)).setChecked(true);
                    }
                }
            }
            bsnPagingResponseDTO.setBasicDTOList(result);
        } catch (NoResultException ne) {
            openConfirmMsgDiv();
            bsnPagingResponseDTO = new PagingResponseDTO();
            ne.printStackTrace();
        } catch (ServiceNotAvailableException e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "ServiceNotAvailableException");
            bsnPagingResponseDTO = new PagingResponseDTO();
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            bsnPagingResponseDTO = new PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            bsnPagingResponseDTO = new PagingResponseDTO();
            e.printStackTrace();
        } catch (Throwable e) {
            bsnPagingResponseDTO = new PagingResponseDTO();
            e.printStackTrace();
        }
        return bsnPagingResponseDTO;
    }

    private PagingResponseDTO getSearchByInfWithPaging(PagingRequestDTO pagingRequestDTO) {
        setSearchInf(true);
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
            //add ur sorting columns
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }
        try {
            bsnPagingResponseDTO = (PagingResponseDTO)(((IKwtCitizensResidentsClient)InfClientFactory.getKwtCitizensResidentsClientForCenter()).filterAvailableInfUsingPaging(getPagingRequestDTO().getSearchDTO(), 
                                                                                                                                                                              bsnPagingRequestDTO));
            List<IBasicDTO> result = bsnPagingResponseDTO.getBasicDTOList();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (IBasicDTO kwDTO: result) {
                IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
                employeesDTO.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(((IKwtCitizensResidentsEntityKey)kwDTO.getCode()).getCivilId()));
                employeesDTO.setCitizensResidentsDTO((IKwtCitizensResidentsDTO)kwDTO);
                employeesDTO.setAuditStatus(kwDTO.getAuditStatus());
                employeesDTO.setCreatedBy(kwDTO.getCreatedBy());
                employeesDTO.setCreatedDate(kwDTO.getCreatedDate());
                employeesDTO.setLastUpdatedBy(kwDTO.getLastUpdatedBy());
                employeesDTO.setLastUpdatedDate(kwDTO.getLastUpdatedDate());
                employeesDTO.setTabrecSerial(((IKwtCitizensResidentsDTO)kwDTO).getTabrecSerial());
                IHireStatusDTO hireStatusDTO = EmpDTOFactory.createHireStatusDTO();
                hireStatusDTO.setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.NOT_EMPLOYEE));
                employeesDTO.setHireStatusDTO(hireStatusDTO);
                for (int i = 0; i < getSelectedAvailableDetails().size(); 
                     i++) {
                    if ((((IEmployeesEntityKey)((IEmployeesDTO)getSelectedAvailableDetails().get(i)).getCode()).getCivilId()).equals(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId())) {
                        employeesDTO.setChecked(true);
                    }

                }
                listDTO.add(employeesDTO);
            }
            bsnPagingResponseDTO.setBasicDTOList(listDTO);
        } catch (NoResultException ne) {
            bsnPagingResponseDTO = new PagingResponseDTO();
            ne.printStackTrace();
        } catch (ServiceNotAvailableException e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "ServiceNotAvailableException");
            bsnPagingResponseDTO = new PagingResponseDTO();
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            bsnPagingResponseDTO = new PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            bsnPagingResponseDTO = new PagingResponseDTO();
            e.printStackTrace();
        } catch (Throwable e) {
            bsnPagingResponseDTO = new PagingResponseDTO();
            e.printStackTrace();
        }

        return bsnPagingResponseDTO;

    }


    public int getAvailableListSize() {
        return availableListSizeBuffer;
    }

    public void openConfirmMsgDiv() {
        if (CheckEnterHRInformation() == false && 
            CheckEnterSalInformation() == false) {
            setSearchInf(true);
            setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
        } else {
            setSearchInf(false);
        }
    }


    public boolean CheckEnterHRInformation() {
        if (employeeSearchDTO.getMinistryFileNo() != null || 
            employeeSearchDTO.getCscFileNo() != null || 
            employeeSearchDTO.getHireDate() != null || 
            employeeSearchDTO.getEmpHireTypes() != null || 
            employeeSearchDTO.getStartWorkingDate() != null || 
            employeeSearchDTO.getTechJobCode() != null) {
            return true;
        } else {
            return false;
        }

    }

    public boolean CheckEnterSalInformation() {
        if (employeeSearchDTO.getBankbranchCode() != null || 
            employeeSearchDTO.getElmguideCode() != null || 
            employeeSearchDTO.getBgtTypesCode() != null) {
            return true;
        } else {
            return false;
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public String addEmployees() {
        super.add();

        if (returnMethod != null && !returnMethod.equals(""))
            return (String)executeMethodBinding(getReturnMethod(), null);
        return "";
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        //return ((NoSignDTO)dto).getEmployeesDTO();
        return ((IEmployeesDTO)dto);
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {

        //NoSignDTO noSignDTO = new NoSignDTO();
        //noSignDTO.setEmployeesDTO(dto);
        return dto;
    }

    public String backFromSearch() {
        setBackBtnClicked(true);
        if (backMethod != null && !backMethod.equals(""))
            return (String)executeMethodBinding(backMethod, null);
        return "";
    }

    public Long getKuwity() {
        return KUWITY;
    }

    public Long getNonKuwity() {
        return NON_KUWITY;
    }


    public void setSingleSelection(boolean singleSelection) {
        this.singleSelection = singleSelection;
    }

    public boolean isSingleSelection() {
        return singleSelection;
    }

    public void setSavedData(List savedData) {
        this.savedData = savedData;
    }

    public List getSavedData() {
        return savedData;
    }

    public void setTempCodeKey(Long tempCodeKey) {
        this.tempCodeKey = tempCodeKey;
    }

    public Long getTempCodeKey() {
        return tempCodeKey;
    }

    public void setTempCodeKeyStr(String tempCodeKeyStr) {
        this.tempCodeKeyStr = tempCodeKeyStr;
    }

    public String getTempCodeKeyStr() {
        return tempCodeKeyStr;
    }

    public void setBackBtnClicked(boolean backBtnClicked) {
        this.backBtnClicked = backBtnClicked;
    }

    public boolean isBackBtnClicked() {
        return backBtnClicked;
    }

    public void setReturnMethod(String returnMethod) {
        this.returnMethod = returnMethod;
    }

    public String getReturnMethod() {
        return returnMethod;
    }


    /**
     * 
     * Purpose: this method created to reset the search results  
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:   
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     * ======================================
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   
     * 1.3 - Creation/Modification:Modification      
     * 1.4-  Description: if you use only result search page to  display your result from simple search not generic 3 tabs search 
     * so this method enable you to navigate to your target page and call suitable action after cancel 
     */
    public String cancelSearchMethod() {

        setAvailableDetails(new ArrayList());
        setSearchMode(false);
        employeeSearchDTO = EmpDTOFactory.createEmployeeSearchDTO();
        selectedCaderCode = "";
        selectedGroupCode = "";
        setCategoryID(null);
        setMinistryID(null);
        setSearchInf(false);
        getEmployeeSearchDTO().setBankbranchCode("");
        setBankID(null);
        setMinistries(new ArrayList());
        kuwityType = KUWITY;
        employeeSearchDTO.setKuwaiti(true);
        if ((getCancelSimpleSearchMethod() == null) || 
            getCancelSimpleSearchMethod().equals(""))
            return "Advanced_Add_Employees_cycle";
        else
            return (String)executeMethodBinding(getCancelSimpleSearchMethod(), 
                                                null);
    }


    public void setShowResultWithinPage(boolean showResultWithinPage) {
        this.showResultWithinPage = showResultWithinPage;
    }

    public boolean isShowResultWithinPage() {
        return showResultWithinPage;
    }

    public void setSearchMethod(String searchMethod) {
        this.searchMethod = searchMethod;
    }

    public String getSearchMethod() {
        return searchMethod;
    }

    public void setCancelSimpleSearchMethod(String cancelSimpleSearchMethod) {
        this.cancelSimpleSearchMethod = cancelSimpleSearchMethod;
    }

    public String getCancelSimpleSearchMethod() {
        return cancelSimpleSearchMethod;
    }

    public void setShowCategoryCB(boolean showCategoryList) {
        this.showCategoryCB = showCategoryList;
    }

    public boolean isShowCategoryCB() {
        return showCategoryCB;
    }

    public void setShowMinistryCB(boolean showMinistryList) {
        this.showMinistryCB = showMinistryList;
    }

    public boolean isShowMinistryCB() {
        return showMinistryCB;
    }

    public void setShowWorkEndDate(boolean showWorkEndDate) {
        this.showWorkEndDate = showWorkEndDate;
    }

    public boolean isShowWorkEndDate() {
        return showWorkEndDate;
    }

    public void setShowHireStatus(boolean showHireStatus) {
        this.showHireStatus = showHireStatus;
    }

    public boolean isShowHireStatus() {
        return showHireStatus;
    }

    public void setShowCurrentHireStatus(boolean showCurrentHireStatus) {
        this.showCurrentHireStatus = showCurrentHireStatus;
    }

    public boolean isShowCurrentHireStatus() {
        return showCurrentHireStatus;
    }

    public void setShowWorkCenterLovDiv(boolean showWorkCenterLovDiv) {
        this.showWorkCenterLovDiv = showWorkCenterLovDiv;
    }

    public boolean isShowWorkCenterLovDiv() {
        return showWorkCenterLovDiv;
    }

    public void setShowJobLovDiv(boolean showJobLovDiv) {
        this.showJobLovDiv = showJobLovDiv;
    }

    public boolean isShowJobLovDiv() {
        return showJobLovDiv;
    }

    public void setSelectedCaderCode(String selectedCaderCode) {


        this.selectedCaderCode = selectedCaderCode;
    }

    public String getSelectedCaderCode() {

        return selectedCaderCode;
    }

    public void setSelectedGroupCode(String selectedGroupCode) {

        this.selectedGroupCode = selectedGroupCode;
    }

    public String getSelectedGroupCode() {
        return selectedGroupCode;
    }


    public void setBankID(Long bankID) {
        this.bankID = bankID;
    }

    public Long getBankID() {
        return bankID;
    }

    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
    }

    public void setBackMethod(String backMethod) {
        this.backMethod = backMethod;
    }

    public String getBackMethod() {
        return backMethod;
    }
    /*
     * overrrided to exclude deletionPart from removeCurrentFromAvailable as it is getted from database
     * added by nora
     * */

    public void removeCurrentFromAvailable() {

        ////            List currentDetailList = getCurrentDetails();
        ////            if (currentDetailList != null && availableDetails != null)
        ////            for (int j = 0; j < currentDetailList.size(); j++) {
        ////            IBasicDTO dto = (IBasicDTO)currentDetailList.get(j);
        //            IBasicDTO codeNameDTO = this.mapToCodeNameDTO(dto);
        DecisionEmployeesModel decisionEmployeesModelSessionBean = 
            (DecisionEmployeesModel)evaluateValueBinding("decisionEmployeesModel");
        boolean exist = false;
        for (int i = 0; i < availableDetails.size(); i++) {
            IBasicDTO availableDTO = availableDetails.get(i);
            //    if (codeNameDTO.getCode().getKey().equals(availableDTO.getCode().getKey())) {
            exist = 
                    decisionEmployeesModelSessionBean.checkExistData(availableDTO);
            if (exist) {
                if (availableDTO.getStatusFlag() != null && 
                    availableDTO.getStatusFlag().longValue() != 
                    getDeleted().longValue()) {
                    ((IEmployeesDTO)availableDetails.get(i)).setDisableIfFound(true);
                    //availableDetails.remove(availableDTO);
                }

                if (availableDTO.getStatusFlag() == null) {
                    //availableDetails.remove(availableDTO);
                    ((IEmployeesDTO)availableDetails.get(i)).setDisableIfFound(true);
                }
            }
            //          }
        }
        //  }

    }

    public void setNewnoOfTableRows(Integer newnoOfTableRows) {
        this.newnoOfTableRows = newnoOfTableRows;
    }

    public Integer getNewnoOfTableRows() {
        return newnoOfTableRows;
    }

    public void setAvailableListSizeBuffer(int availableListSizeBuffer) {
        this.availableListSizeBuffer = availableListSizeBuffer;
    }

    public int getAvailableListSizeBuffer() {
        return availableListSizeBuffer;
    }

    public void setSearchInf(boolean searchInf) {
        this.searchInf = searchInf;
    }

    public boolean isSearchInf() {
        return searchInf;
    }
}
