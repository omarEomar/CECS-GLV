package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobWorkCentersDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;

import java.util.ArrayList;
import java.util.List;


public class FirstStepBean extends LookupMaintainBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private static final String BEAN_NAME = "govEmpFirstStepBean";
    private static final String NAVIGATION_KEY = "govempfirststep";
    private static final String NAVIGATION_KEY_REDIRECT = "govempfirststep_redirect";

    private String civilId;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private boolean validEmpStatus = true;
    private ISalEmpSalaryElementsDTO salaryElementDTO;
    private IPersonQualificationsDTO personQulDTO = InfDTOFactory.createPersonQualificationsDTO();
    GovEmpMaintainBean maintainBean = GovEmpMaintainBean.getInstance();
    private static final String SEARCH_BY_CODE = "0";
    private static final String SEARCH_BY_NAME = "1";
    private boolean enableEmpLovDiv;
    private String jobTechName = null;


    public FirstStepBean() {
        setPageDTO(EmpDTOFactory.createEmployeesDTO());
        this.setEmpListOfValues(new EmployeeListOfValues());
        setEmpListOfValues(new EmployeeListOfValues());
        setContent1Div("module_tabs_cont");

    }

    public static FirstStepBean getInstance() {
        return (FirstStepBean)JSFHelper.getValue(BEAN_NAME);
    }

    public static String getNavigationKey() {
        return NAVIGATION_KEY_REDIRECT;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowWizardBar(true);
        app.setShowEmpSrchDiv(true);
        return app;
    }

    public String init() {
        maintainBean.setEnableTabs(false);
        Long maintainBeanCivilID = maintainBean.getCivilID();
        if ((civilId != null && !civilId.equals("")) || maintainBeanCivilID != null) {
            if (maintainBeanCivilID != null) {
                civilId = maintainBeanCivilID.toString();
            }
            IEmployeesDTO empDTO = null;
            if (maintainBean.isPreSelectedCivilId()) { // no need to validate
                empDTO = getEmployeesDTO(maintainBeanCivilID);
            } else {
                validEmpStatus = true;
                getHighlightedDTOS().clear();
                if (GlobalValidation.isValidCivilId(Long.parseLong(civilId))) {
                    validCivilId = true;
                    civilExist = true;
                    empDTO = getEmployeesDTO(Long.valueOf(civilId));
                } else {
                    validCivilId = false;
                    civilExist = false;
                }
            }
            if (empDTO != null) {
                setPageDTO(empDTO);
                maintainBean.setCivilID(Long.parseLong(empDTO.getCode().getKey()));
                maintainBean.setEnableTabs(true);
                try {
                    maintainBean.setEmpName(((IKwtCitizensResidentsDTO)empDTO.getCitizensResidentsDTO()).getFullName());
                } catch (Throwable e) {
                    e.printStackTrace();
                }

                jobTechName = empDTO.getJobDTO().getName();
                //                try{ // commented by Ashraf Gaber 28092010
                //                    IEntityKey entityKey = JobEntityKeyFactory.createJobWorkCentersEntityKey(empDTO.getJobDTO().getCode().getKey(),getLoggedInMinistryCode(),empDTO.getWorkCenterDTO().getCode().getKey());
                //                    String jobDescription = getJobWorkCenterDescription(entityKey);
                //                    if(jobDescription != null && jobDescription.length() != 0){
                //                        jobTechName = jobDescription;
                //                    }
                //                }catch(Throwable e){e.printStackTrace();}
            }
        }
        return NAVIGATION_KEY;
      
    }

    public void getAll() throws DataBaseException {
    }

    private IEmployeesDTO getEmployeesDTO(Long empCivilId) {
        IEmployeesDTO empDTO = null;
        try {
            if (maintainBean.isPreSelectedCivilId()) {
                if (maintainBean.getMinistryCode() != null) {
                    //                    empDTO =
                    //                            EmpClientFactory.getEmployeesClient().getByRealCivilId(empCivilId, maintainBean.getMinistryCode());
                    empDTO =
                            (IEmployeesDTO)EmpClientFactory.getEmployeesClient(maintainBean.getMinistryCode()).getEmployeByCivilId(empCivilId).get(0);
                } else {
                    //                    empDTO = EmpClientFactory.getEmployeesClient().getByRealCivilIdAllMinistries(empCivilId);
                    empDTO =
                            (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getEmployeByCivilId(empCivilId).get(0);
                }
            } else {
                IEmpEmployeeSearchDTO searchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
                List<Long> empHireStatusList = maintainBean.getEmpHireStatusList();
                if (empHireStatusList == null || empHireStatusList.size() == 0) {
                    //set default search criteria... employed only
                    searchDTO.setEmpHireStatusList(null);
                    searchDTO.setEmployment(true);
                } else {
                    searchDTO.setEmpHireStatusList(empHireStatusList);
                    searchDTO.setEmployment(false);
                }

                searchDTO.setCivilId(Long.valueOf(empCivilId));
                searchDTO.setEmpName(null);
                empDTO = (IEmployeesDTO)EmpClientFactory.getEmployeesClient().simpleSearch(searchDTO).get(0);

            }
            maintainBean.setRealCivilId(empDTO.getRealCivilId());
        } catch (Throwable e) {
            civilExist = false;
            validEmpStatus = false;
            e.printStackTrace();
        }
        return empDTO;
    }

    public String reSetData() {
        setPageDTO(EmpDTOFactory.createEmployeesDTO());
        setCivilId(null);
        validCivilId = true;
        civilExist = false;
        validEmpStatus = true;
        salaryElementDTO = null;
        enableEmpLovDiv = false;
        personQulDTO = InfDTOFactory.createPersonQualificationsDTO();
        maintainBean.setCivilID(null);
        maintainBean.setEmpName(null);
        return getNavigationKey();
    }

    public void showSearchForEmployeeDiv() {
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        employeeListOfValuesBean.setEmpListOfValuesStyle("newEmpListOfValuesStyle");
        employeeListOfValuesBean.setReturnMethodName(BEAN_NAME + ".returnMethodAction");
        employeeListOfValuesBean.setSearchMethod(BEAN_NAME + ".searchEmpLovDiv");

        employeeListOfValuesBean.getOkCommandButton().setReRender("stepsPanel,mainDataEmpPanel,scriptGenerator,displayBtnPanel,dataT_data_panel,paging_panel,empLovDivContainer,emp_query_panel_id");
        employeeListOfValuesBean.resetData();
        enableEmpLovDiv = true;
    }

    public void returnMethodAction() {

        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        List<IBasicDTO> selectedDTOsList = employeeListOfValuesBean.getSelectedDTOS();

        if (selectedDTOsList != null && selectedDTOsList.get(0) != null && selectedDTOsList.get(0).getCode() != null) {
            setCivilId((String)(selectedDTOsList.get(0).getCode().getKey()));
            init();
        }
        enableEmpLovDiv = false;

    }

    public String searchEmpLovDiv(Object searchType, Object searchQuery) {
        //  evaluateValueBinding(detailBeanName).
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        try {
            //IEmpEmployeeSearchDTO searchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
            //searchDTO.setEmployment(true);
            IEmpEmployeeSearchDTO searchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
            List<Long> empHireStatusList = maintainBean.getEmpHireStatusList();
            if (empHireStatusList == null || empHireStatusList.size() == 0) {
                //set default search criteria... employed only
                searchDTO.setEmpHireStatusList(null);
                searchDTO.setEmployment(true);
            } else {
                searchDTO.setEmpHireStatusList(empHireStatusList);
                searchDTO.setEmployment(false);
            }
            if (searchType.toString().equals(SEARCH_BY_CODE)) {
                searchDTO.setCivilId(Long.valueOf((String)searchQuery));
                searchDTO.setEmpName(null);
            } else if (searchType.toString().equals(SEARCH_BY_NAME)) {
                searchDTO.setCivilId(null);
                searchDTO.setEmpName(searchQuery.toString());
            }
            IEmployeesClient client = EmpClientFactory.getEmployeesClient();
            List<IBasicDTO> searchResult = client.simpleSearch(searchDTO);

            employeeListOfValuesBean.setMyTableData(searchResult);

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            employeeListOfValuesBean.setMyTableData(new ArrayList(0));
        } catch (DataBaseException e) {
            e.printStackTrace();
            employeeListOfValuesBean.setMyTableData(new ArrayList(0));
        }
        return "";
    }

    public void cancelSearch() throws DataBaseException {

        System.out.println("Calling cancelSearch()...");

        this.setSearchQuery("");
        this.setSearchType(0);
        this.setSearchMode(false);

        this.init();
    }

    public String getStatusMsgString() {
        return getSharedUtil().messageLocator(maintainBean.getBundleName(), maintainBean.getStatusMsgKey());
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setValidCivilId(boolean validCivilId) {
        this.validCivilId = validCivilId;
    }

    public boolean isValidCivilId() {
        return validCivilId;
    }

    public void setCivilExist(boolean civilExist) {
        this.civilExist = civilExist;
    }

    public boolean isCivilExist() {
        return civilExist;
    }

    public void setValidEmpStatus(boolean validEmpStatus) {
        this.validEmpStatus = validEmpStatus;
    }

    public boolean isValidEmpStatus() {
        return validEmpStatus;
    }

    public void setSalaryElementDTO(ISalEmpSalaryElementsDTO salaryElementDTO) {
        this.salaryElementDTO = salaryElementDTO;
    }

    public ISalEmpSalaryElementsDTO getSalaryElementDTO() {
        return salaryElementDTO;
    }

    public void setPersonQulDTO(IPersonQualificationsDTO personQulDTO) {
        this.personQulDTO = personQulDTO;
    }

    public IPersonQualificationsDTO getPersonQulDTO() {
        return personQulDTO;
    }

    public void setEnableEmpLovDiv(boolean enableEmpLovDiv) {
        this.enableEmpLovDiv = enableEmpLovDiv;
    }

    public boolean isEnableEmpLovDiv() {
        return enableEmpLovDiv;
    }

    public String getJobWorkCenterDescription(IEntityKey jobWorkCentersEntityKey) {

        IJobWorkCentersDTO jobWorkCentersDTO = null;
        try {
            jobWorkCentersDTO =
                    (IJobWorkCentersDTO)JobClientFactory.getJobWorkCentersClient().getById(jobWorkCentersEntityKey);
            return jobWorkCentersDTO.getJobDescription();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setJobTechName(String jobTechName) {
        this.jobTechName = jobTechName;
    }

    public String getJobTechName() {
        return jobTechName;
    }


  
}
