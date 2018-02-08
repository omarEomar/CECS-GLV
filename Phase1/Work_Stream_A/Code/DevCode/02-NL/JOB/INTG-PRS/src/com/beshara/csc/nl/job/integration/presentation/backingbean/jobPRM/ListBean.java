package com.beshara.csc.nl.job.integration.presentation.backingbean.jobPRM;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.nl.job.business.client.ICatsClient;
import com.beshara.csc.nl.job.business.client.IJobCatLevelsClient;
import com.beshara.csc.nl.job.business.client.IJobCatTypesClient;
import com.beshara.csc.nl.job.business.client.IJobsClient;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.ICatsDTO;
import com.beshara.csc.nl.job.business.dto.IJobSearchCriteriaDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.business.util.IJobConstants;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListBean extends LookUpBaseBean {
    @SuppressWarnings("compatibility:4227256447783918230")
    private static final long serialVersionUID = 1L;

    /****************************************************   Start fields By I.Omar    ****************************************************/
    private static final String JOB_Intg_RESOURCES =
        "com.beshara.csc.nl.job.integration.presentation.resources.integration";
    private String buttonValue = IJobConstants.VIEW_DATA;
    private IJobSearchCriteriaDTO jobSearchCriteriaDTO = JobDTOFactory.createJobSearchCriteriaDTO();
    private List<IBasicDTO> displayedList;
    private int myListSize;
    private ICatsDTO catsDTO = null;
    private String selectedJobCode;
    private String selectedLevelGroupCode;
    private String selectedLevelsCode;
    private String selectedChildTypeCode;
    private String selectedWorkTypeNameCode;
    private Long selectedJobTypeCode;
    private Long jobCatLevelsScaleCode;
    private List<IBasicDTO> jobFieldsList;
    private List<IBasicDTO> catsGroupList;
    private List<IBasicDTO> catsSubList;
    private List<IBasicDTO> childTypeFieldsList;
    private List<IBasicDTO> workTypeNameFieldsList;
    private List<IBasicDTO> jobTypesList;
    private List<IBasicDTO> jobCatLevelsList;
    private List<IBasicDTO> jobsList;
    private String selectedJobToPromote;
    private IJobCatLevelsClient jobCatLevelsClient = JobClientFactory.getJobCatLevelsClient();
    private IEmployeesClient employeesClient = EmpClientFactory.getEmployeesClient();
    private Map<String , Long> jobsMap = new HashMap();


    /****************************************************   End fields By I.Omar    ****************************************************/

    public ListBean() {
        setClient(JobClientFactory.getJobsClient());
        setFilterMode(true);
        setSaveSortingState(true);
        setMyTableData(new ArrayList());
        setSelectedPageDTO(JobDTOFactory.createJobsDTO());
        //        setUsingPaging(true);
        //        setUsingBsnPaging(true);
        //        setSaveSortingState(true);
        //setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));
        setCustomDiv1("divCustomStyle");
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowbar(true);
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowCustomDiv1(true);
        app.setShowpaging(true);
        return app;
    }

    @Override
    public void initiateBeanOnce() {
        fillJobFields();

    }
    /****************************************************   Start fill DrobDown List By I.Omar    ****************************************************/

    private void fillJobFields() {
        if (jobFieldsList == null || jobFieldsList.size() == 0) {
            ICatsClient catsClient = JobClientFactory.getCatsClient();
            try {
                jobFieldsList = catsClient.getFirstLevel();
            } catch (DataBaseException e) {
                jobFieldsList = new ArrayList();
            } catch (SharedApplicationException e) {
                jobFieldsList = new ArrayList();
            }
        }
    }

    public void jobCodeChanged() {
        if (selectedJobCode != null && !selectedJobCode.equals("")) {
            ICatsClient catsClient = JobClientFactory.getCatsClient();
            try {
                catsGroupList = catsClient.getAllByParentCode(Long.valueOf(selectedJobCode));
                catsDTO = JobDTOFactory.createCatsDTO(Long.parseLong(selectedJobCode), "");
                fillJobTypesList(catsDTO);
            } catch (SharedApplicationException f) {
                f.printStackTrace();
                catsGroupList = new ArrayList();
            } catch (DataBaseException e) {
                catsGroupList = new ArrayList();
            }
        }
        selectedLevelGroupCode = "";
        selectedLevelsCode = "";
        selectedChildTypeCode = "";
        selectedWorkTypeNameCode = "";
        selectedJobTypeCode = null;
        jobCatLevelsScaleCode = null;

    }

    public void levelGroupCodeChanged() {
        if (selectedLevelGroupCode != null && !selectedLevelGroupCode.equals("")) {
            ICatsClient catsClient = JobClientFactory.getCatsClient();
            try {
                catsSubList = catsClient.getAllByParentCode(Long.valueOf(selectedLevelGroupCode));
                catsDTO = JobDTOFactory.createCatsDTO(Long.parseLong(selectedLevelGroupCode), "");
                fillJobTypesList(catsDTO);
            } catch (SharedApplicationException f) {
                f.printStackTrace();
                catsSubList = new ArrayList();
            } catch (DataBaseException e) {
                catsSubList = new ArrayList();
            }
        }
        selectedLevelsCode = "";
        selectedChildTypeCode = "";
        selectedWorkTypeNameCode = "";
        selectedJobTypeCode = null;
        jobCatLevelsScaleCode = null;

    }

    public void levelsCodeChanged() {
        if (selectedLevelsCode != null && !selectedLevelsCode.equals("")) {
            ICatsClient catsClient = JobClientFactory.getCatsClient();
            try {
                childTypeFieldsList = catsClient.getAllByParentCode(Long.valueOf(selectedLevelsCode));
                catsDTO = JobDTOFactory.createCatsDTO(Long.parseLong(selectedLevelsCode), "");
                fillJobTypesList(catsDTO);
            } catch (SharedApplicationException f) {
                f.printStackTrace();
                childTypeFieldsList = new ArrayList();
            } catch (DataBaseException e) {
                childTypeFieldsList = new ArrayList();
            }
        }
        selectedChildTypeCode = "";
        selectedWorkTypeNameCode = "";
        selectedJobTypeCode = null;
        jobCatLevelsScaleCode = null;
    }

    public void childTypeCodeChanged() {
        if (selectedChildTypeCode != null && !selectedChildTypeCode.equals("")) {
            ICatsClient catsClient = JobClientFactory.getCatsClient();
            try {
                workTypeNameFieldsList = catsClient.getAllByParentCode(Long.valueOf(selectedChildTypeCode));
                catsDTO = JobDTOFactory.createCatsDTO(Long.parseLong(selectedChildTypeCode), "");
                fillJobTypesList(catsDTO);
            } catch (SharedApplicationException f) {
                workTypeNameFieldsList = new ArrayList();
                f.printStackTrace();
            } catch (DataBaseException e) {
                workTypeNameFieldsList = new ArrayList();
            }
        }
        selectedWorkTypeNameCode = "";
        selectedJobTypeCode = null;
        jobCatLevelsScaleCode = null;
    }

    public void workTypeNameCodeChanged() {
        if (selectedWorkTypeNameCode != null && !selectedWorkTypeNameCode.equals("")) {
            catsDTO = JobDTOFactory.createCatsDTO(Long.parseLong(selectedWorkTypeNameCode), "");
            fillJobTypesList(catsDTO);
        }
        selectedJobTypeCode = null;
        jobCatLevelsScaleCode = null;
    }

    public void jobTypeCodeChanged() {
        if (selectedJobTypeCode != null) {
            try {
                jobCatLevelsList = jobCatLevelsClient.getAllLevelsByCatsAndType(getLatestCatCode(),selectedJobTypeCode);
            } catch (SharedApplicationException f) {
                jobCatLevelsList = new ArrayList();
            } catch (DataBaseException e) {
                jobCatLevelsList = new ArrayList();
            }
        }
        jobCatLevelsScaleCode = null;
    }

    // method to get latest selected cat code

    private Long getLatestCatCode() {
        if (selectedWorkTypeNameCode != null && !selectedWorkTypeNameCode.equals("")) {
            return Long.parseLong(selectedWorkTypeNameCode);
        } else if (selectedChildTypeCode != null && !selectedChildTypeCode.equals("")) {
            return Long.parseLong(selectedChildTypeCode);
        } else if (selectedLevelsCode != null && !selectedLevelsCode.equals("")) {
            return Long.parseLong(selectedLevelsCode);
        } else if (selectedLevelGroupCode != null && !selectedLevelGroupCode.equals("")) {
            return Long.parseLong(selectedLevelGroupCode);
        } else
            return Long.parseLong(selectedJobCode);
    }

    private void fillJobTypesList(IBasicDTO catsDTO) {
        IJobCatTypesClient catTypesClient = JobClientFactory.getJobCatTypeClient();
        try {
            jobTypesList = catTypesClient.getTypeByCategory(catsDTO);
        } catch (DataBaseException e) {
            jobTypesList = new ArrayList();
        } catch (SharedApplicationException e) {
            jobTypesList = new ArrayList();
        }
    }

    /****************************************************   End fill DrobDown List By I.Omar    ****************************************************/
    private void fillDisplayedList() {
        try {
            initJobSearchCriteriaDTO();
            Map conditionMap =  jobCatLevelsClient.getConditionCodeAndTabrecSerialByLevelCode(jobSearchCriteriaDTO.getCategoryCode(),
                                                                                                    jobSearchCriteriaDTO.getLevelsCode(),
                                                                                                    jobSearchCriteriaDTO.getTypesCode());
            displayedList =
                    (List)employeesClient.getAllHiredEmployeesByJobLevelsAndType(jobSearchCriteriaDTO , conditionMap);
            setButtonValue(IJobConstants.RESET_VAL);
            setFilterMode(false);
        } catch (DataBaseException e) {
            displayedList = new ArrayList();
        } catch (SharedApplicationException e) {
            displayedList = new ArrayList();
        }
        if (displayedList != null) {
            setMyListSize(displayedList.size());
        }
        setMyTableData(displayedList);
    }

    public void fillDataTable() {
        if (buttonValue.equals(IJobConstants.VIEW_DATA)) {
            fillDisplayedList();
        } else {
            setFilterMode(true);
            setMyTableData(new ArrayList());
            setDisplayedList(new ArrayList());
            setMyListSize(displayedList.size());
            setButtonValue(IJobConstants.VIEW_DATA);
            getSelectedDTOS().clear();
        }
    }

    private void initJobSearchCriteriaDTO() {
        jobSearchCriteriaDTO.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        jobSearchCriteriaDTO.setCategoryCode(getLatestCatCode());
        jobSearchCriteriaDTO.setTypesCode(selectedJobTypeCode);
        jobSearchCriteriaDTO.setLevelsCode(jobCatLevelsScaleCode);
    }

    public void openExecuteDiv() {
        try {
            initJobSearchCriteriaDTO();
            jobsList = JobClientFactory.getJobsClient().getAllJobsByCatTypeAndLevelCodeName(jobSearchCriteriaDTO);
            for(IBasicDTO jobDTO : jobsList){
                IJobsDTO job = (IJobsDTO)jobDTO;
                jobsMap.put(job.getJobKey(), Long.parseLong(job.getCode().getKey()));
                }
            if (jobsList != null && jobsList.size() == 1) {
                setSelectedJobToPromote(((IJobsDTO)jobsList.get(0)).getJobKey());
            }
        } catch (DataBaseException e) {
            jobsList = new ArrayList();
        } catch (SharedApplicationException e) {
            jobsList = new ArrayList();
        }
    }

    public void doExecuteJobPromotion() {
        try {
            ((IJobsClient)getClient()).doExecuteJobPromotion((List)getSelectedDTOS(),jobsMap.get(selectedJobToPromote));
            getSharedUtil().handleSuccMsg(getSharedUtil().messageLocator(JOB_Intg_RESOURCES,
                                                                         "promotionDoneSuccessfully"));
            fillDisplayedList();
            getSelectedDTOS().clear();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }

    public void setSelectedJobCode(String selectedJobCode) {
        this.selectedJobCode = selectedJobCode;
    }

    public String getSelectedJobCode() {
        return selectedJobCode;
    }

    public void setJobFieldsList(List<IBasicDTO> jobFieldsList) {
        this.jobFieldsList = jobFieldsList;
    }

    public List<IBasicDTO> getJobFieldsList() {
        return jobFieldsList;
    }

    public void setCatsGroupList(List<IBasicDTO> catsGroupList) {
        this.catsGroupList = catsGroupList;
    }

    public List<IBasicDTO> getCatsGroupList() {
        return catsGroupList;
    }

    public void setSelectedLevelGroupCode(String selectedLevelGroupCode) {
        this.selectedLevelGroupCode = selectedLevelGroupCode;
    }

    public String getSelectedLevelGroupCode() {
        return selectedLevelGroupCode;
    }

    public void setCatsSubList(List<IBasicDTO> catsSubList) {
        this.catsSubList = catsSubList;
    }

    public List<IBasicDTO> getCatsSubList() {
        return catsSubList;
    }

    public void setSelectedLevelsCode(String selectedLevelsCode) {
        this.selectedLevelsCode = selectedLevelsCode;
    }

    public String getSelectedLevelsCode() {
        return selectedLevelsCode;
    }

    public void setSelectedChildTypeCode(String selectedChildTypeCode) {
        this.selectedChildTypeCode = selectedChildTypeCode;
    }

    public String getSelectedChildTypeCode() {
        return selectedChildTypeCode;
    }

    public void setChildTypeFieldsList(List<IBasicDTO> childTypeFieldsList) {
        this.childTypeFieldsList = childTypeFieldsList;
    }

    public List<IBasicDTO> getChildTypeFieldsList() {
        return childTypeFieldsList;
    }

    public void setWorkTypeNameFieldsList(List<IBasicDTO> workTypeNameFieldsList) {
        this.workTypeNameFieldsList = workTypeNameFieldsList;
    }

    public List<IBasicDTO> getWorkTypeNameFieldsList() {
        return workTypeNameFieldsList;
    }

    public void setSelectedWorkTypeNameCode(String selectedWorkTypeNameCode) {
        this.selectedWorkTypeNameCode = selectedWorkTypeNameCode;
    }

    public String getSelectedWorkTypeNameCode() {
        return selectedWorkTypeNameCode;
    }

    public void setSelectedJobTypeCode(Long selectedJobTypeCode) {
        this.selectedJobTypeCode = selectedJobTypeCode;
    }

    public Long getSelectedJobTypeCode() {
        return selectedJobTypeCode;
    }

    public void setJobTypesList(List<IBasicDTO> jobTypesList) {
        this.jobTypesList = jobTypesList;
    }

    public List<IBasicDTO> getJobTypesList() {
        return jobTypesList;
    }

    public void setJobCatLevelsList(List<IBasicDTO> jobCatLevelsList) {
        this.jobCatLevelsList = jobCatLevelsList;
    }

    public List<IBasicDTO> getJobCatLevelsList() {
        return jobCatLevelsList;
    }

    public void setCatsDTO(ICatsDTO catsDTO) {
        this.catsDTO = catsDTO;
    }

    public ICatsDTO getCatsDTO() {
        return catsDTO;
    }

    public void setJobCatLevelsScaleCode(Long jobCatLevelsScaleCode) {
        this.jobCatLevelsScaleCode = jobCatLevelsScaleCode;
    }

    public Long getJobCatLevelsScaleCode() {
        return jobCatLevelsScaleCode;
    }

    public void setDisplayedList(List<IBasicDTO> displayedList) {
        this.displayedList = displayedList;
    }

    public List<IBasicDTO> getDisplayedList() {
        return displayedList;
    }

    public void setMyListSize(int myListSize) {
        this.myListSize = myListSize;
    }

    public int getMyListSize() {
        return myListSize;
    }

    public void setJobsList(List<IBasicDTO> jobsList) {
        this.jobsList = jobsList;
    }

    public List<IBasicDTO> getJobsList() {
        return jobsList;
    }

    public void setSelectedJobToPromote(String selectedJobToPromote) {
        this.selectedJobToPromote = selectedJobToPromote;
    }

    public String getSelectedJobToPromote() {
        return selectedJobToPromote;
    }

    public void setJobSearchCriteriaDTO(IJobSearchCriteriaDTO jobSearchCriteriaDTO) {
        this.jobSearchCriteriaDTO = jobSearchCriteriaDTO;
    }

    public IJobSearchCriteriaDTO getJobSearchCriteriaDTO() {
        return jobSearchCriteriaDTO;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public void setJobsMap(Map<String, Long> jobsMap) {
        this.jobsMap = jobsMap;
    }

    public Map<String, Long> getJobsMap() {
        return jobsMap;
    }
}
