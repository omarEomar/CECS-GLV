package com.beshara.csc.hr.scp.integration.presentation.backingbean.empmonthsalaryquery;


import com.beshara.base.deploy.SessionBeanProviderException;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.ded.integration.business.dto.CSCDeductionEnquiryDTO;
import com.beshara.csc.hr.ded.integration.presentation.backingbean.deductionenquiry.DeductionEnquiryBean;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStatusEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision.FifthStepBean;
import com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision.FirstStepBean;
import com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision.ForthStepBean;
import com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision.GovEmpMaintainBean;
import com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision.MerRaiseMaintainBean;
import com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision.SecondStepBean;
import com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision.SixthStepBean;
import com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision.ThirdStepBean;
import com.beshara.csc.hr.emp.integration.presentation.utils.EmployeeHelper;
import com.beshara.csc.hr.mer.integration.presentation.backingbean.empsettenquiry.EmpSettEnquiryBeanHelper;
import com.beshara.csc.hr.sal.business.client.ISalEmpMonSalariesClient;
import com.beshara.csc.hr.sal.business.client.ISalPaymentMethodsClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSalariesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpPayslipsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementTypesEntityKey;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.hr.scp.integration.presentation.backingbean.empvacenquiry.ListBean;
import com.beshara.csc.inf.business.client.IYearsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.bnk.business.dto.BankBranchesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.wizardbar2.state.WizardInfo;

import java.math.BigDecimal;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


//import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSalariesDTO ;
public class PaySlipMaintainQueryBean extends LookUpBaseBean {
    private static final String RESOURCE_PATH_SCP_BUNDLE = "com.beshara.csc.hr.scp.integration.presentation.resources.integration";
    private static final String RESOURCE_KEY_MONTH_PRE = "month_key_";
    private static final Long MINISTRY_CODE = 13L;
    private static final String SEARCH_BY_CODE = "0";
    private static final String SEARCH_BY_NAME = "1";
    private Long civilId;
    private Long realCivilId;
    private String degree;
    private List months;
    private String month;
    private List years;
    private String year;
    private List<IBasicDTO> meritsList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> deductionsList = new ArrayList<IBasicDTO>();
    private boolean empHired = true;
    private boolean payrollInfoExist = true;
    private boolean enableEmpLovDiv;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
    private ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO;
    // private ISalEmpMonSalariesDTO salEmpMonSalariesDTO=new ISalEmpMonSalariesDTO ( ) ;
    private ISalEmpPayslipsDTO salEmpPayslipsDTO = SalDTOFactory.createSalEmpPayslipsDTO();
    private BigDecimal totalDeductions;
    private BigDecimal totalMerits;
    private BigDecimal actualSalary;
    private int showPaySlipPanel = 0; // has 3 values 1 if there is data and 2 if there is no data after enter civil id
    private IEmployeesDTO empDTO;
    private ISalEmpMonSalariesDTO monSalDTO;
    private Long sheetCode;

    EmployeeHelper employeeHelper = new EmployeeHelper();
    public static final String BEAN_NAME = "paySlipMaintainQueryBean";
    public static final String PAGE_NAVE_CASE="paySlipMaintainQueryBean";
    
    private HtmlDataTable deductionTable = new HtmlDataTable();
    private EmpSettEnquiryBeanHelper empSettEnquiryBeanHelper;
    private boolean hasIntervals = false;
    private String settlemenetType = "1";
    private String settWithDecsType = "1";
    private String retroactive_SettType = "2";
    private boolean settler = false;
    private String monYearDegree = "";
    private String monYearJob = "";
    private String monYearBgtPrg = "";
    private String monYearBgtType = "";
    private String monYearWorkCenter = "";
    private String currBank = "";
    private String currBranch = "";
    private String currAccountNo = "";
    public static final String nav_case = "paySlipMaintainQueryBean";
    private static final String METHOD_NAME_RESTORE = "restorePage";
    
    private List<IBasicDTO> paymentMethods;
    private String paymentMethodCode;
    private boolean showPaymentMethod ;
    private String  advancedPaymentMonthYear="";
    private Boolean empEndedService = false;
    
    public PaySlipMaintainQueryBean() throws DataBaseException, RemoteException,
                                             Exception { // setClient ( AprClientFactory.getAppraisalStatusClient ( ) ) ;
        // setPageDTO ( AprDTOFactory.createAppraisalStatusDTO ( ) ) ;
        // setSelectedPageDTO ( AprDTOFactory.createAppraisalStatusDTO ( ) ) ;
        setClient(SalClientFactory.getSalEmpMonSalariesClient());
        setEmpSettEnquiryBeanHelper(new EmpSettEnquiryBeanHelper());
        setCustomDiv2("m2mEditDivClass");
        setContent1Div("paySlipDivContent1");
        if (getEmpListOfValues() == null)setEmpListOfValues(new EmployeeListOfValues());
        if (month == null && year == null)
            setCurrentMonthYear();
       //// System.out.println("PaySlipMaintainQueryBean bean initialized");
        if(paymentMethodCode==null){
            paymentMethodCode=IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE.toString();
        }
        initEmpDTO();
        initEmpHelper();
    }

    public void setCurrentMonthYear() {
        Calendar currentDate = Calendar.getInstance();
        month = Integer.toString(currentDate.get(Calendar.MONTH) + 1);
        year = Integer.toString(currentDate.get(Calendar.YEAR));
       //// System.out.println(month + "--" + year);
    }

    /**
     * Purpose:overiedd to support changing layout of current page * Creation/Modification History : * 1.1 - Developer Name: Ashraf Gaber * 1.2 - Date: 28/6/2009 * 1.3 - Creation/Modification:Creation * 1.4- Description: Overrided Method */
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowSearch(false);
        app.setShowbar(false);
        app.setShowpaging(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowdatatableContent(false);
        app.setShowCommonData(true);
        app.setShowContent1(true);
        app.setShowEmpSrchDiv(true);
        app.setShowScirptGenerator(true);
        app.setShowshortCut(true);
        app.setShowCustomDiv1(true);
        app.setShowCustomDiv2(true);
        return app;
    }

//    public void showSearchForEmployeeDiv() {
//        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
//        employeeListOfValuesBean.setReturnMethodName("paySlipMaintainQueryBean.returnMethodAction");
//        //employeeListOfValuesBean.setSearchMethod("paySlipMaintainQueryBean.searchEmpLovDiv");
//        employeeListOfValuesBean.getOkCommandButton().setReRender("mainDataEmpPanel , scriptGenerator , displayBtnPanel, civil_div_dataT_data");
//        employeeListOfValuesBean.resetData();
//        enableEmpLovDiv = true;
//    }
//    
    public void showSearchForEmployeeDiv() {        
        EmployeeListOfValues empListOfValuesBean = ((EmployeeListOfValues)getEmpListOfValues());       
            empListOfValuesBean.setUseCustomSearch(true);
            empListOfValuesBean.setSearchMethod(BEAN_NAME + ".searchEmpCandidates");      
        empListOfValuesBean.setEmpListOfValuesStyle("m2mAddDivClass");
        empListOfValuesBean.setReturnMethodName(BEAN_NAME + ".returnMethodAction");
        empListOfValuesBean.resetData();
        empListOfValuesBean.getOkCommandButton().setReRender("mainDataEmpPanel , scriptGenerator , displayBtnPanel, civil_div_dataT_data");
        enableEmpLovDiv = true;
    }

    public void searchEmpCandidates(Object searchType, Object searchQuery) {
        EmployeeListOfValues empListOfValuesBean = ((EmployeeListOfValues)getEmpListOfValues());
        if (searchQuery != null && !searchQuery.toString().equals("") && searchType != null &&
            !searchType.equals("")) {
            empListOfValuesBean.setSearchMode(true);
            IEmpEmployeeSearchDTO empEmployeeSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
            //                empEmployeeSearchDTO.setEmployment(!searchAtAllEmployees); // employment default value was true so i negated the searchAtAllEmployees attribute
            if (searchType.toString().equals("0"))
                empEmployeeSearchDTO.setCivilId(Long.valueOf(searchQuery.toString()));
            else if (searchType.toString().equals("1"))
                empEmployeeSearchDTO.setEmpName(searchQuery.toString());

            empEmployeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            List hireStatusList = new ArrayList();            
                hireStatusList.add(IEMPConstant.EMP_STATUS_END_SERVICE);           
                hireStatusList.add(IEMPConstant.EMP_STATUS_EMPLOYMENT);
                hireStatusList.add(IEMPConstants.EMP_STATUS_DELEGATION_TO_OUT);                
                hireStatusList.add(IEMPConstants.EMP_STATUS_DELEGATION_OUT_TO_IT);                
                hireStatusList.add(IEMPConstants.EMP_STATUS_FREEZING);
           
            empEmployeeSearchDTO.setEmpHireStatusList(hireStatusList);
            try {
                if (isUsingPaging()) {
                    setUpdateMyPagedDataModel(true);
                    setPagingRequestDTO(new PagingRequestDTO("filterSearchByEmpWithPaging"));
                    setOldPageIndex(0);
                    setCurrentPageIndex(1);
                } else {
                    empListOfValuesBean.setMyTableData(EmpClientFactory.getEmployeesClient().simpleSearch(empEmployeeSearchDTO));
                }
            } catch (SessionBeanProviderException e) {
                e.printStackTrace();
                empListOfValuesBean.setMyTableData(new ArrayList());
                //                    cantLocateSession = true;
                empListOfValuesBean.setSearchMode(false);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                empListOfValuesBean.setMyTableData(new ArrayList());
                empListOfValuesBean.setSearchMode(false);
            } catch (DataBaseException e) {
                e.printStackTrace();
                empListOfValuesBean.setMyTableData(new ArrayList());
                empListOfValuesBean.setSearchMode(false);
            }
        } else {
            ResourceBundle bundle =
                ResourceBundle.getBundle("com.beshara.jsfbase.csc.msgresources.global", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            empListOfValuesBean.setErrorMsgValue(bundle.getString("missingField"));
            empListOfValuesBean.setSearchMode(false);
        }
        empListOfValuesBean.repositionPage(0);
        empListOfValuesBean.setSelectedDTOS(new ArrayList<IBasicDTO>(0));
    }



    public void returnMethodAction() {
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        List<IBasicDTO> selectedDTOsList = employeeListOfValuesBean.getSelectedDTOS();
        if (selectedDTOsList != null && selectedDTOsList.get(0) != null && selectedDTOsList.get(0).getCode() != null) {
            setEmpDTO((IEmployeesDTO)selectedDTOsList.get(0));
            setCivilId(((IEmployeesEntityKey)empDTO.getCode()).getCivilId());
            setRealCivilId(empDTO.getRealCivilId());
            employeeHelper.setRealCivilId(empDTO.getRealCivilId().toString());
            filterByCivilId();
        }
        enableEmpLovDiv = false;
    }

    private void resetMessages() {
        empHired = true;
        payrollInfoExist = true;
        validCivilId = true;
        civilExist = false;
    }
    
    public void initEmpHelper() {
        employeeHelper.setBackBeanNameFrom(BEAN_NAME );
        employeeHelper.setResetButtonMethod("reSetData");
        employeeHelper.setShowPayrollInfo(true);
        employeeHelper.setShowBankAccData(true);
    }

    public void filterByCivilId() {
        showPaymentMethod=false;
        monYearDegree = "";
        settler = false;
        hasIntervals = false;
        showPaySlipPanel = 0;
        //        if (realCivilId != null) {
        resetMessages();
        empDTO = null;
        //            try{
        employeeHelper.setEmpHireStatus("111");
        empDTO = employeeHelper.filterByCivilId();
        if (empDTO != null) {
            civilExist = true;
            validCivilId = true;
            setCivilId(((IEmployeesEntityKey)(empDTO.getCode())).getCivilId());
            setEmployeesDTO(empDTO);
            setRealCivilId(empDTO.getRealCivilId());
            //csc-19378
//            try{
//            showPaymentMethod=checkEmpHasSeparatedPayMethod();
//            }catch(Exception e){
//                e.printStackTrace();
//            }
            salEmpSalaryElementsDTO = empDTO.getSalEmpSalaryElementsDTO();
      
            try {
                ISalEmpMonSearchCriteriaDTO salEmpMonSearchCriteriaDTO=SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
                salEmpMonSearchCriteriaDTO.setSalaryMonth(Long.valueOf(month));
                salEmpMonSearchCriteriaDTO.setYear(Long.valueOf(year));
                salEmpMonSearchCriteriaDTO.setCivilId(getRealCivilId());
                salEmpMonSearchCriteriaDTO.setFilterByPayMethod(paymentMethodCode);
                salEmpMonSearchCriteriaDTO.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
                monSalDTO =
                        (ISalEmpMonSalariesDTO)SalClientFactory.getSalEmpMonSalariesClient().getSalMonForSalaryEnquiry(salEmpMonSearchCriteriaDTO);
                //                        checkEmpHasIntervals(monSalDTO);
                if ( monSalDTO != null && monSalDTO.getFormNo() != null ) {
                    sheetCode =monSalDTO.getFormNo();
                           // ((ISalSheetDetailsEntityKey)monSalDTO.getSalSheetDetailsList().get(0).getCode()).getSalsheetCode();
                }                
                //to display the bgtPrg , bgtType, jobTitle, empDegree & WirkCeneter.. of the month and year that user wants to enquery with;
                //CSC-17743
                if (monSalDTO != null && monSalDTO.getCalcJobDTO() != null && monSalDTO.getCalcWorkDTO() != null &&
                    monSalDTO.getCalcElmguideDTO() != null && monSalDTO.getCalcBgtPrgDTO() != null &&
                    monSalDTO.getCalcBgtTypeDTO() != null) {
                    monYearWorkCenter = monSalDTO.getCalcWorkDTO().getName();
                    monYearBgtPrg = monSalDTO.getCalcBgtPrgDTO().getName();
                    monYearBgtType = monSalDTO.getCalcBgtTypeDTO().getName();
                    if( monSalDTO.getCalcElmguideDTO() !=null &&  monSalDTO.getCalcElmguideDTO().getCode() !=null){
                        monYearDegree = monSalDTO.getCalcElmguideDTO().getFullName();
                            //fillDegreeName(Long.valueOf ( monSalDTO.getCalcElmguideDTO().getCode().getKey()));
                    }
                    monYearJob = monSalDTO.getCalcJobDTO().getName();
                } else {
                    monYearWorkCenter = getEmployeesDTO().getWorkCenterDTO().getName();
                    monYearBgtPrg = getEmpDTO().getBgtProgramsDTO().getName();
                    monYearBgtType = getEmpDTO().getBgtTypesDTO().getName();
                    if (salEmpSalaryElementsDTO != null && salEmpSalaryElementsDTO.getSalElementGuidesDTO() !=null &&salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode() !=null ) {
                        // monYearDegree = fillDegreeName(Long.valueOf (salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode().getKey()));
                         monYearDegree =salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFullName();
                        }
                    monYearJob = getEmployeesDTO().getJobDTO().getName();
                }
                realCivilId = Long.parseLong(employeeHelper.getRealCivilId());
            } catch (Exception e) {
                e.printStackTrace();
                initMonSalDTO();
            }
        } else {
            settler = false;
            hasIntervals = false;
            civilExist = false;
            validCivilId = false;
            empHired = false;
            employeesDTO = EmpDTOFactory.createEmployeesDTO();
        }
        
       
       
    }
    
    public void openEmpCurrentInfoDiv() {
        currBank = "";
        currBranch = "";
        currAccountNo = "";
        try {
            setDegree(getSalEmpSalaryElementsDTO().getSalElementGuidesDTO().getFullName());
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

        try {
            
            BankBranchesDTO bankBranchesDTO =
                (BankBranchesDTO)SalClientFactory.getSalEmpMonSalariesClient().getValidCurrentEmpBnkBranchINFO(Long.parseLong(getEmployeeHelper().getRealCivilId()));
            if (bankBranchesDTO != null) {
                currBank = bankBranchesDTO.getName();
                currBranch = bankBranchesDTO.getPart();
                currAccountNo = bankBranchesDTO.getBuildingNo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void reSetData(){
        reSetData(null);
    }

    /**
     * Purpose:used to reset civil id and remain data related to it * Creation/Modification History : * 1.1 - Developer Name: Ashraf Gaber * 1.2 - Date: 28-06-2009 * 1.3 - Creation/Modification:Creation * 1.4- Description: */
    public void reSetData(ActionEvent ae) {
        ae = null;
        settler = false;
        hasIntervals = false;
        showPaySlipPanel = 0;
        employeesDTO = EmpDTOFactory.createEmployeesDTO();
        salEmpSalaryElementsDTO = null;
        degree = null;
        civilId = null;
        empHired = true;
        payrollInfoExist = true;
        enableEmpLovDiv = false;
        validCivilId = true;
        civilExist = false;
        setCurrentMonthYear();
        // salEmpMonSalariesDTO=new ISalEmpMonSalariesDTO ( ) ;
        meritsList = new ArrayList<IBasicDTO>();
        deductionsList = new ArrayList<IBasicDTO>();
        actualSalary = new BigDecimal(0L);
        totalMerits = new BigDecimal(0L);
        totalDeductions = new BigDecimal(0L);
        reIntializeMsgs();

        initEmpDTO();
        initMonSalDTO();
        realCivilId = null;
        getEmployeeHelper().setRealCivilId("");
        monYearDegree = "";
        monYearJob = "";
        monYearBgtPrg = "";
        monYearBgtType = "";
        monYearWorkCenter = "";
        showPaymentMethod = false ;
        paymentMethodCode=IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE.toString();
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setEmpHired(boolean empHired) {
        this.empHired = empHired;
    }

    public boolean isEmpHired() {
        return empHired;
    }

    public void setPayrollInfoExist(boolean payrollInfoExist) {
        this.payrollInfoExist = payrollInfoExist;
    }

    public boolean isPayrollInfoExist() {
        return payrollInfoExist;
    }

    public void setEnableEmpLovDiv(boolean enableEmpLovDiv) {
        this.enableEmpLovDiv = enableEmpLovDiv;
    }

    public boolean isEnableEmpLovDiv() {
        return enableEmpLovDiv;
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

    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

    public void setSalEmpSalaryElementsDTO(ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO) {
        this.salEmpSalaryElementsDTO = salEmpSalaryElementsDTO;
    }

    public ISalEmpSalaryElementsDTO getSalEmpSalaryElementsDTO() {
//        if (salEmpSalaryElementsDTO == null && getRealCivilId() != null && civilExist) {
//            try {
//                salEmpSalaryElementsDTO =
//                        (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(getRealCivilId(),
//                                                                                                                             ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        return salEmpSalaryElementsDTO;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
//        if (degree == null) {
//            if (getSalEmpSalaryElementsDTO() != null) {
//                ISalElementGuidesDTO firstParent = null;
//                try {
//                    //                    firstParent =
//                    //                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getById(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFirstParent());
//                    List list =
//                        SalClientFactory.getSalElementGuidesClient().searchPayrollByCode(((SalElementGuidesEntityKey)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFirstParent()).getElmguideCode());
//                    if (list.size() > 0) {
//                        firstParent = (ISalElementGuidesDTO)list.get(0);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                if (firstParent != null) {
//                    degree = firstParent.getName();
//                    degree += " / ";
//                }
//                degree +=
//                        ((((ISalElementGuidesDTO)(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject())).getParentObject())).getName();
//                degree += " / ";
//                degree += ((salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject())).getName();
//
//            }
//
//        }
       return degree;
    }

    public void setMonths(List months) {
        this.months = months;
    }

    public List getMonths() {
        if (months == null) {
            months = new ArrayList();
            IBasicDTO basicDto = null;
            String monthFromBun = null;
            SharedUtilBean sharedUtilBean = getSharedUtil();
            for (int i = 1; i <= 12; i++) {
                basicDto = new BasicDTO();
                basicDto.setCode(new EntityKey(new String[] { "" + i + "" }));
                monthFromBun = sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, RESOURCE_KEY_MONTH_PRE + i);
                basicDto.setName(monthFromBun);
                months.add(basicDto);
            }
        }
        return months;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setYears(List years) {
        this.years = years;
    }

    public List getYears() {
        if (years == null) {
            years = new ArrayList();
            try { // InfMappedFactory factory = ( InfMappedFactory ) MappedFactory.getFactory ( InfMappedFactory.class ) ;
                // IYearsClient client = factory.getYearsClient ( ) ;
                IYearsClient client = InfClientFactory.getYearsClient();
                setYears(client.getCodeName());
                years = client.getCodeName();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return years;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setMeritsList(List<IBasicDTO> meritsList) {
        this.meritsList = meritsList;
    }

    public List<IBasicDTO> getMeritsList() {
        return meritsList;
    }

    public void setDeductionsList(List<IBasicDTO> deductionsList) {
        this.deductionsList = deductionsList;
    }

    public List<IBasicDTO> getDeductionsList() {
        return deductionsList;
    }
    //CSC-19824 nora ismail 
    public void reCreateMonSalBySeperatedPaymentMethodType() {
//        ISalEmpMonSalariesEntityKey code =
//            SalEntityKeyFactory.createSalEmpMonSalariesEntityKey(getRealCivilId(), Long.valueOf(year),
//                                                                 Long.valueOf(month), null);
        try {
            ISalEmpMonSearchCriteriaDTO salEmpMonSearchCriteriaDTO=SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
            salEmpMonSearchCriteriaDTO.setSalaryMonth(Long.valueOf(month));
            salEmpMonSearchCriteriaDTO.setYear(Long.valueOf(year));
            salEmpMonSearchCriteriaDTO.setCivilId(getRealCivilId());
            salEmpMonSearchCriteriaDTO.setFilterByPayMethod(IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE_2.toString());
            salEmpMonSearchCriteriaDTO.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            monSalDTO =
                    (ISalEmpMonSalariesDTO)SalClientFactory.getSalEmpMonSalariesClient().getSalMonForSalaryEnquiry(salEmpMonSearchCriteriaDTO);
            
            //monSalDTO = (ISalEmpMonSalariesDTO)SalClientFactory.getSalEmpMonSalariesClient().getByCivilMonYearPaymethod(code,Long.parseLong(IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE_2.toString()));
           if ( monSalDTO != null && monSalDTO.getFormNo() != null ) {
                    sheetCode =monSalDTO.getFormNo();
                           // ((ISalSheetDetailsEntityKey)monSalDTO.getSalSheetDetailsList().get(0).getCode()).getSalsheetCode();
                }
            else {
                sheetCode = null;
            }
            //to display the bgtPrg , bgtType, jobTitle, empDegree & WirkCeneter.. of the month and year that user wants to enquery with;
            //CSC-17743
            if (monSalDTO != null && monSalDTO.getCalcJobDTO() != null && monSalDTO.getCalcWorkDTO() != null &&
                monSalDTO.getCalcElmguideDTO() != null && monSalDTO.getCalcBgtPrgDTO() != null &&
                monSalDTO.getCalcBgtTypeDTO() != null) {
                monYearWorkCenter = monSalDTO.getCalcWorkDTO().getName();
                monYearBgtPrg = monSalDTO.getCalcBgtPrgDTO().getName();
                monYearBgtType = monSalDTO.getCalcBgtTypeDTO().getName();
                if( monSalDTO.getCalcElmguideDTO() !=null &&  monSalDTO.getCalcElmguideDTO().getCode() !=null){
                    monYearDegree =   monSalDTO.getCalcElmguideDTO().getFullName();
                        //fillDegreeName(Long.valueOf ( monSalDTO.getCalcElmguideDTO().getCode().getKey()));
                }
                monYearJob = monSalDTO.getCalcJobDTO().getName();
            } else {
                monYearWorkCenter = getEmployeesDTO().getWorkCenterDTO().getName();
                monYearBgtPrg = getEmpDTO().getBgtProgramsDTO().getName();
                monYearBgtType = getEmpDTO().getBgtTypesDTO().getName();
                if (salEmpSalaryElementsDTO != null && salEmpSalaryElementsDTO.getSalElementGuidesDTO() !=null &&salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode() !=null ) {
                     monYearDegree = salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFullName();
                         //fillDegreeName(Long.valueOf (salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode().getKey()));
                }
                monYearJob = getEmployeesDTO().getJobDTO().getName();
            }
        } catch (Exception e) {
            initMonSalDTO();
        }
        if (monSalDTO == null) {
            initMonSalDTO();
        }
        checkEmpHasIntervals();

    }
    public void fillPaySlipData() {
        showPaymentMethod=false;
        // csc-19378
        paymentMethodCode=IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE.toString();        
        
        recalculateMonSal();
        if (monSalDTO != null && monSalDTO.getPaymentStatusDTO() != null &&
            monSalDTO.getPaymentStatusDTO().getPaystatusCode() != null &&
            !monSalDTO.getPaymentStatusDTO().getPaystatusCode().equals(IScpConstants.SAL_PAY_STATUS_SAL_CALC_STOPPED)) {
            showPaymentMethod = checkEmpHasSeparatedPayMethod();
        }
        showPaySlipPanel = 2;
        if(empDTO!= null && empDTO.getHireStatusDTO() != null && empDTO.getHireStatusDTO().getCode() != null){
            empEndedService = ((IHireStatusEntityKey)empDTO.getHireStatusDTO().getCode()).getHirstatusCode().equals(9L);
        }
        //boolean hasMonSalariesDTO=true ;
        ISalEmpMonSearchCriteriaDTO searchDTO = SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
        searchDTO.setCivilId(realCivilId);
        searchDTO.setSalaryMonth(Long.valueOf(month));
        searchDTO.setYear(Long.valueOf(year));
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        searchDTO.setMinCode(minCode);
        checkEmpHasIntervals();
        String filterByPayType = IScpConstants.MON_SAL_PAY_TYPE_SALARIES.toString() + "," +
                                    IScpConstants.MON_SAL_PAY_TYPE_OTHERS.toString();
        String filterByPayMethod =  "";
        if(paymentMethodCode!=null) 
            filterByPayMethod=paymentMethodCode;
        //We will exclude all status that the fin will not be with salary and the status is also added
        // as it should not be view if we didn't calculate the salary yet (no records with type 1)
        String excludePayStatus = IScpConstants.NO_CALC_PAYSTATUS_CODE.toString() + "," +
                                    IScpConstants.MON_SAL_STATUS_FIN_SINGLE_NOT_SIGNED.toString() + "," +
                                    IScpConstants.MON_SAL_STATUS_FIN_SINGLE_NOT_PAYED.toString() + "," +
                                    IScpConstants.MON_SAL_STATUS_FIN_SINGLE_PAYED.toString() + "," +
                                    IScpConstants.SCP_MER_UNSIGNED.toString() + "," +
                                    IScpConstants.SCP_MER__ENTERED.toString() + "," +
                                    IScpConstants.SAL_PAY_STATUS_SAL_CALC_STOPPED.toString() + "," +
                                    IScpConstants.MON_SAL_STATUS_FIN_WITH_SALARY_NOT_SIGNED.toString();
        
        searchDTO.setFilterByPayType(filterByPayType);
        searchDTO.setExcludePayStatus(excludePayStatus);
        searchDTO.setFilterByPayMethod(filterByPayMethod);
        // if ( hasMonSalariesDTO ) {
        try {
            meritsList = SalClientFactory.getSalEmpPayslipsClient().getMerDedList(searchDTO);
            showPaySlipPanel = 1;
        } catch (Exception e) {
            e.printStackTrace();
            meritsList = new ArrayList<IBasicDTO>();
        }
        try {
            searchDTO.setSalElementType(IScpConstants.MON_SAL_TYPE_DED);
            //searchDTO.setSalElementType(Long.valueOf(getManagedConstantsBean().getELEMENT_GUIDE_TYPE_DEDUCT_ROOT()));
            deductionsList = SalClientFactory.getSalEmpPayslipsClient().getMerDedList(searchDTO);
            showPaySlipPanel = 1;
        } catch (Exception e) {
            e.printStackTrace();
            deductionsList = new ArrayList<IBasicDTO>();
        }
        // ----------CSC-19824 added by n.ismail to handle Payment Method 5 
        if(!showPaymentMethod && meritsList.isEmpty() &&  deductionsList.isEmpty() && filterByPayMethod!=null && filterByPayMethod.equals(IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE.toString())){
            
            searchDTO.setSalElementType(IScpConstants.MON_SAL_TYPE_MERIT);
            searchDTO.setFilterByPayMethod(IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE_2.toString());
            // if ( hasMonSalariesDTO ) {
            try {
                meritsList = SalClientFactory.getSalEmpPayslipsClient().getMerDedList(searchDTO);
                showPaySlipPanel = 1;
            } catch (Exception e) {
                e.printStackTrace();
                meritsList = new ArrayList<IBasicDTO>();
            }
            try {
                searchDTO.setSalElementType(IScpConstants.MON_SAL_TYPE_DED);
                deductionsList = SalClientFactory.getSalEmpPayslipsClient().getMerDedList(searchDTO);
                showPaySlipPanel = 1;
            } catch (Exception e) {
                e.printStackTrace();
                deductionsList = new ArrayList<IBasicDTO>();
            }

            if (!meritsList.isEmpty() || !deductionsList.isEmpty()) {

                reCreateMonSalBySeperatedPaymentMethodType();
                 paymentMethodCode = IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE_2.toString();
            }
        }
        if(meritsList.isEmpty() &&  deductionsList.isEmpty()){
         try {
                boolean hasAdvancePayment =
                    SalClientFactory.getSalEmpMonSalariesClient().checkSalaryMonthAdvancedPayment(searchDTO);
            } catch (InvalidDataEntryException e) {
                showPaySlipPanel = 3;
               advancedPaymentMonthYear =e.getExtraInformation().get(0).toString();
                e.printStackTrace();
            } catch (Exception e) {
                // TODO: Add catch code
                e.printStackTrace();
            }
         
            }
        calcuateValues();
        // }
    }
    
    public void fillPaySlipData2() {     
        recalculateMonSal();
        showPaySlipPanel = 2;
        //boolean hasMonSalariesDTO=true ;
        ISalEmpMonSearchCriteriaDTO searchDTO = SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
        searchDTO.setCivilId(realCivilId);
        searchDTO.setSalaryMonth(Long.valueOf(month));
        searchDTO.setYear(Long.valueOf(year));
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        searchDTO.setMinCode(minCode);
        checkEmpHasIntervals();
        String filterByPayType = IScpConstants.MON_SAL_PAY_TYPE_SALARIES.toString() + "," +
                                    IScpConstants.MON_SAL_PAY_TYPE_OTHERS.toString();
        String filterByPayMethod =  "";
        if(paymentMethodCode!=null) 
            filterByPayMethod=paymentMethodCode;
        //We will exclude all status that the fin will not be with salary and the status is also added
        // as it should not be view if we didn't calculate the salary yet (no records with type 1)
        String excludePayStatus = IScpConstants.NO_CALC_PAYSTATUS_CODE.toString() + "," +
                                    IScpConstants.MON_SAL_STATUS_FIN_SINGLE_NOT_SIGNED.toString() + "," +
                                    IScpConstants.MON_SAL_STATUS_FIN_SINGLE_NOT_PAYED.toString() + "," +
                                    IScpConstants.MON_SAL_STATUS_FIN_SINGLE_PAYED.toString() + "," +
                                    IScpConstants.SCP_MER_UNSIGNED.toString() + "," +
                                    IScpConstants.SCP_MER__ENTERED.toString() + "," +
                                    IScpConstants.SAL_PAY_STATUS_SAL_CALC_STOPPED.toString() + "," +
                                    IScpConstants.MON_SAL_STATUS_FIN_WITH_SALARY_NOT_SIGNED.toString();
        
        searchDTO.setFilterByPayType(filterByPayType);
        searchDTO.setExcludePayStatus(excludePayStatus);
        searchDTO.setFilterByPayMethod(filterByPayMethod);
        // if ( hasMonSalariesDTO ) {
        try {
            meritsList = SalClientFactory.getSalEmpPayslipsClient().getMerDedList(searchDTO);
            showPaySlipPanel = 1;
        } catch (Exception e) {
            e.printStackTrace();
            meritsList = new ArrayList<IBasicDTO>();
        }
        try {
            searchDTO.setSalElementType(IScpConstants.MON_SAL_TYPE_DED);
            //searchDTO.setSalElementType(Long.valueOf(getManagedConstantsBean().getELEMENT_GUIDE_TYPE_DEDUCT_ROOT()));
            deductionsList = SalClientFactory.getSalEmpPayslipsClient().getMerDedList(searchDTO);
            showPaySlipPanel = 1;
        } catch (Exception e) {
            e.printStackTrace();
            deductionsList = new ArrayList<IBasicDTO>();
        }
        calcuateValues();
     
        // }
    }
    //    public void fillPaySlipData2() {
    //        showPaySlipPanel = 2;
    //        //boolean hasMonSalariesDTO=true ;
    //        ISalEmpMonSearchCriteriaDTO searchDTO = SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
    //        searchDTO.setCivilId(realCivilId);
    //        searchDTO.setSalaryMonth(Long.valueOf(month));
    //        searchDTO.setYear(Long.valueOf(year));
    //        // if ( hasMonSalariesDTO ) {
    //        try {
    //            searchDTO.setSalElementType(getManagedConstantsBean().getMeritesConstant());
    //            meritsList = SalClientFactory.getSalEmpPayslipsClient().getSalaryDetailData(searchDTO);
    //            showPaySlipPanel = 1;
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            meritsList = new ArrayList<IBasicDTO>();
    //        }
    //        try {
    //            searchDTO.setSalElementType(Long.valueOf(getManagedConstantsBean().getELEMENT_GUIDE_TYPE_DEDUCT_ROOT()));
    //            deductionsList = SalClientFactory.getSalEmpPayslipsClient().getSalaryDetailData(searchDTO);
    //            showPaySlipPanel = 1;
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            deductionsList = new ArrayList<IBasicDTO>();
    //        }
    //        calcuateValues();
    //        // }
    //    }

    public void calcuateValues() {
        settler = false;
        
        Map<IEntityKey, ISalElementGuidesDTO> merMap;
        Map<IEntityKey, ISalEmpPayslipsDTO> dedMap;
        
        merMap = new TreeMap<IEntityKey, ISalElementGuidesDTO>();
        dedMap = new TreeMap<IEntityKey, ISalEmpPayslipsDTO>();
        ISalElementGuidesDTO elementGuidesDTO;
        ISalEmpPayslipsDTO salEmpPayslipsDTO;
        BigDecimal totalDed = new BigDecimal(0L);
        BigDecimal totalMer = new BigDecimal(0L);
        for (Object dto1 : (ArrayList)meritsList) {
            ISalEmpPayslipsDTO dto = (ISalEmpPayslipsDTO)dto1;
            if (dto.getElementGuidesDTO().getSalElementGuidesDTO() == null) {
                dto.getElementGuidesDTO().setSalElementGuidesDTO(dto.getElementGuidesDTO());
            }
            dto.getElementGuidesDTO().getSalElementGuidesDTO().setBigValue(dto.getValue());
            if (dto.isIsSettler()) {
                setSettler(true);
            }
            if (dto.getValue() != null)
                totalMer = totalMer.add(dto.getValue());
            if (dto.getElementGuidesDTO().getSalElementGuidesDTO() != null) {
                elementGuidesDTO = dto.getElementGuidesDTO().getSalElementGuidesDTO();

                if (merMap.get(elementGuidesDTO.getCode()) != null) {
                    elementGuidesDTO = merMap.get(dto.getElementGuidesDTO().getSalElementGuidesDTO().getCode());
                    if (dto.getValue() != null) {
                        elementGuidesDTO.setBigValue(elementGuidesDTO.getBigValue().add(dto.getValue()));
                    }
                } else {
                    merMap.put(dto.getElementGuidesDTO().getSalElementGuidesDTO().getCode(),
                               dto.getElementGuidesDTO().getSalElementGuidesDTO());
                }
            }

        }
        meritsList = new ArrayList(merMap.values());
        for (Object dto1 : (ArrayList)deductionsList) {
            ISalEmpPayslipsDTO dto = (ISalEmpPayslipsDTO)dto1;
            if (dto.getElementGuidesDTO().getSalElementGuidesDTO() == null) {
                dto.getElementGuidesDTO().setSalElementGuidesDTO(dto.getElementGuidesDTO());
            }
            if (dto.isIsSettler()) {
                setSettler(true);
            }
//            dto.getElementGuidesDTO().getSalElementGuidesDTO().setBigValue(dto.getValue());
            if (dto.getValue() != null)
                totalDed = totalDed.add(dto.getValue());
            
            salEmpPayslipsDTO =
                    dedMap.get(dto.getElementGuidesDTO().getSalElementGuidesDTO() == null ? dto.getElementGuidesDTO().getCode() :
                               dto.getElementGuidesDTO().getSalElementGuidesDTO().getCode());
            if (salEmpPayslipsDTO != null) {
                Long type =
                    ((ISalElementTypesEntityKey)dto.getElementGuidesDTO().getSalElementTypesDTO().getCode()).getElmtypeCode();
                if (!type.equals(ISalConstants.ELEMENT_GUIDE_TYPE_DEDUCT) && dto.getValue() != null) {
                    salEmpPayslipsDTO.setValue(salEmpPayslipsDTO.getValue().add(dto.getValue()));
                } else {
                    dedMap.put(dto.getCode(), dto);
                }
            } else {
                dedMap.put(dto.getElementGuidesDTO().getSalElementGuidesDTO() == null ?
                           dto.getElementGuidesDTO().getCode() :
                           dto.getElementGuidesDTO().getSalElementGuidesDTO().getCode(), dto);
            }
            

        }
        deductionsList = new ArrayList(dedMap.values());
        //BigDecimal x = new BigDecimal(0L);
        actualSalary = totalMer.add(totalDed);
        totalMerits = totalMer;
        totalDeductions = totalDed;
        
        merMap.clear();
        dedMap.clear();
        merMap = null;
        dedMap = null;
    } // public void setSalEmpMonSalariesDTO ( ISalEmpMonSalariesDTO salEmpMonSalariesDTO ) {
    // this.salEmpMonSalariesDTO = salEmpMonSalariesDTO ;
    // }
    //
    // public ISalEmpMonSalariesDTO getSalEmpMonSalariesDTO ( ) {
    // return salEmpMonSalariesDTO ;
    // }

    public void initEmpDTO() {
        empDTO = EmpDTOFactory.createEmployeesDTO();
        empDTO.setBgtProgramsDTO(BgtDTOFactory.createBgtProgramsDTO());
        empDTO.setBgtTypesDTO(BgtDTOFactory.createBgtTypesDTO());
    }


    public void initMonSalDTO() {
        monSalDTO = SalDTOFactory.createSalEmpMonSalariesDTO();
        monSalDTO.setPaymentStatusDTO(SalDTOFactory.createSalPaymentStatusDTO());
        sheetCode = null;
    }
  

    public void recalculateMonSal() {
        monYearDegree="";

        try {
           // monSalDTO = (ISalEmpMonSalariesDTO)SalClientFactory.getSalEmpMonSalariesClient().getByCivilMonYearPaymethod(code,Long.parseLong(paymentMethodCode));
              
            ISalEmpMonSearchCriteriaDTO salEmpMonSearchCriteriaDTO=SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
            salEmpMonSearchCriteriaDTO.setSalaryMonth(Long.valueOf(month));
            salEmpMonSearchCriteriaDTO.setYear(Long.valueOf(year));
            salEmpMonSearchCriteriaDTO.setCivilId(getRealCivilId());
            salEmpMonSearchCriteriaDTO.setFilterByPayMethod(paymentMethodCode);
            salEmpMonSearchCriteriaDTO.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            monSalDTO =
                    (ISalEmpMonSalariesDTO)SalClientFactory.getSalEmpMonSalariesClient().getSalMonForSalaryEnquiry(salEmpMonSearchCriteriaDTO);
                if ( monSalDTO != null && monSalDTO.getFormNo() != null ) {
                    sheetCode =monSalDTO.getFormNo();
                           // ((ISalSheetDetailsEntityKey)monSalDTO.getSalSheetDetailsList().get(0).getCode()).getSalsheetCode();
                }  else {
                sheetCode = null;
            }
            //to display the bgtPrg , bgtType, jobTitle, empDegree & WirkCeneter.. of the month and year that user wants to enquery with;
            //CSC-17743
            if (monSalDTO != null && monSalDTO.getCalcJobDTO() != null && monSalDTO.getCalcWorkDTO() != null &&
                monSalDTO.getCalcElmguideDTO() != null && monSalDTO.getCalcBgtPrgDTO() != null &&
                monSalDTO.getCalcBgtTypeDTO() != null) {
                monYearWorkCenter = monSalDTO.getCalcWorkDTO().getName();
                monYearBgtPrg = monSalDTO.getCalcBgtPrgDTO().getName();
                monYearBgtType = monSalDTO.getCalcBgtTypeDTO().getName();
                if( monSalDTO.getCalcElmguideDTO() !=null &&  monSalDTO.getCalcElmguideDTO().getCode() !=null){
                    monYearDegree = monSalDTO.getCalcElmguideDTO().getFullName();
                }
                monYearJob = monSalDTO.getCalcJobDTO().getName();
            } else {
                monYearWorkCenter = getEmployeesDTO().getWorkCenterDTO().getName();
                monYearBgtPrg = getEmpDTO().getBgtProgramsDTO().getName();
                monYearBgtType = getEmpDTO().getBgtTypesDTO().getName();
                if (salEmpSalaryElementsDTO != null && salEmpSalaryElementsDTO.getSalElementGuidesDTO() !=null &&salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode() !=null ) {
                     monYearDegree = salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFullName();
                         //fillDegreeName(Long.valueOf (salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode().getKey()));
                }
                monYearJob = getEmployeesDTO().getJobDTO().getName();
            }
        } catch (Exception e) {
            initMonSalDTO();
        }
        if (monSalDTO == null) {
            initMonSalDTO();
        }
        meritsList = new ArrayList(0);
        deductionsList = new ArrayList(0);

    }

    public String queryDeduction() {
        ISalEmpPayslipsDTO dto = (ISalEmpPayslipsDTO)deductionTable.getRowData();
        CSCDeductionEnquiryDTO searchDTO = new CSCDeductionEnquiryDTO();
        searchDTO.setId(dto.getElmSerial());
        DeductionEnquiryBean bean = DeductionEnquiryBean.getInstance();

        String backMethod = "paySlipMaintainQueryBean.restoreSaveStateParams";

        bean.init(fillSaveStateParams(), PAGE_NAVE_CASE, backMethod);
        bean.searchDeductions(searchDTO);

        return "deductionEnquiry";
    }
    
    private Object fillSaveStateParams() {
        Map params = new HashMap();
        params.put("pageDTO", getPageDTO());
        params.put("selectedPageDTO", getSelectedPageDTO());
        params.put("selectedDTOS", getSelectedDTOS());
        params.put("civilId", getCivilId());
        params.put("realCivilId", realCivilId);
        params.put("empDTO", empDTO);
        params.put("civilExist", isCivilExist());
        params.put("validCivilId", isValidCivilId());
        params.put("empHired", isEmpHired());
        params.put("payrollInfoExist", isPayrollInfoExist());
        params.put("singleSelection", isSingleSelection());
        params.put("highlightedDTOS", getHighlightedDTOS());
        params.put("searchMode", isSearchMode());
        params.put("fullColumnName", getFullColumnName());
        params.put("sortAscending", isSortAscending());
        params.put("selectedRadio", getSelectedRadio());
        params.put("year", year);
        params.put("month", month);
        params.put("meritsList", meritsList);
        params.put("deductionsList", deductionsList);
        params.put("salEmpPayslipsDTO", salEmpPayslipsDTO);
        params.put("totalDeductions", totalDeductions);
        params.put("totalMerits", totalMerits);
        params.put("actualSalary", actualSalary);
        params.put("showPaySlipPanel",showPaySlipPanel);
        params.put("employeeHelper",employeeHelper);
        params.put("degree",degree);
        params.put("monSalDTO",monSalDTO);
        params.put("settler", settler);
        params.put("hasIntervals", hasIntervals);
        params.put("settlemenetType", settlemenetType);
        params.put("settWithDecsType", settWithDecsType);
        params.put("retroactive_SettType", retroactive_SettType);
        params.put("empSettEnquiryBeanHelper", empSettEnquiryBeanHelper);
        params.put("monYearWorkCenter", monYearWorkCenter);
        params.put("monYearBgtType", monYearBgtType);
        params.put("monYearBgtPrg", monYearBgtPrg);
        params.put("monYearJob", monYearJob);
        params.put("monYearDegree", monYearDegree);
        params.put("currBank", currBank);
        params.put("currBranch", currBranch);
        params.put("currAccountNo", currAccountNo);
        params.put("employeesDTO", employeesDTO);
        params.put("sheetCode", sheetCode);
        params.put("showPaymentMethod", showPaymentMethod);
        params.put("paymentMethodCode", paymentMethodCode);

        params.put("salEmpSalaryElementsDTO", salEmpSalaryElementsDTO);
        params.put("degree", degree);
      
        return params;
    }

    public void restoreSaveStateParams(Object obj) {
        if (obj != null) {
            try {
                Map params = (Map)obj;
                setPageDTO((IBasicDTO)params.get("pageDTO"));
                setSelectedPageDTO((IBasicDTO)params.get("selectedPageDTO"));
                setSelectedDTOS((List)params.get("selectedDTOS"));
                realCivilId = (Long)params.get("realCivilId");
                civilId = (Long)params.get("civilId");
                empDTO = (IEmployeesDTO)params.get("empDTO");
                setYear((String)params.get("year") );
                setMonth((String)params.get("month") ); 
                setCivilExist((Boolean)params.get("civilExist"));
                setValidCivilId((Boolean)params.get("validCivilId"));
                setEmpHired((Boolean)params.get("empHired"));
                setPayrollInfoExist((Boolean)params.get("payrollInfoExist"));
                setSingleSelection((Boolean)params.get("singleSelection"));
                setHighlightedDTOS((List)params.get("highlightedDTOS"));
                setSearchMode((Boolean)params.get("searchMode"));
                setFullColumnName((String)params.get("fullColumnName"));
                setSortAscending((Boolean)params.get("sortAscending"));
                setSelectedRadio((String)params.get("selectedRadio"));
                setDeductionsList((List)params.get("deductionsList") );
                setMeritsList((List)params.get("meritsList") );
                setSalEmpPayslipsDTO((ISalEmpPayslipsDTO)params.get("salEmpPayslipsDTO") );
                setTotalDeductions((BigDecimal)params.get("totalDeductions") );
                setTotalMerits((BigDecimal)params.get("totalMerits") );
                setActualSalary((BigDecimal)params.get("actualSalary") );
                showPaySlipPanel = ((Integer)params.get("showPaySlipPanel") );
                employeeHelper = (EmployeeHelper)params.get("employeeHelper");
                degree = (String)params.get("degree");
                monSalDTO = (ISalEmpMonSalariesDTO)params.get("monSalDTO");
                settler = ((Boolean)params.get("settler") );
                hasIntervals = ((Boolean)params.get("hasIntervals") );
                settlemenetType = ((String)params.get("settlemenetType") );
                settWithDecsType = ((String)params.get("settWithDecsType") );
                retroactive_SettType = ((String)params.get("retroactive_SettType") );
                empSettEnquiryBeanHelper = ((EmpSettEnquiryBeanHelper)params.get("empSettEnquiryBeanHelper") );
                monYearJob = ((String)params.get("monYearJob") );
                monYearBgtPrg = ((String)params.get("monYearBgtPrg") );
                monYearDegree = ((String)params.get("monYearDegree") );
                monYearBgtType = ((String)params.get("monYearBgtType") );
                monYearWorkCenter = ((String)params.get("monYearWorkCenter") );
                currBank = ((String)params.get("currBank") );
                currBranch = ((String)params.get("currBranch") );
                currAccountNo = ((String)params.get("currAccountNo") );
                employeesDTO = ((IEmployeesDTO)params.get("employeesDTO") );
                sheetCode = ((Long)params.get("sheetCode") );
                showPaymentMethod = ((Boolean)params.get("showPaymentMethod") );
                paymentMethodCode= ((String)params.get("paymentMethodCode") );
                salEmpSalaryElementsDTO = (ISalEmpSalaryElementsDTO)params.get("salEmpSalaryElementsDTO");
                degree=((String)params.get("degree") );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is responsible for displaying Div of All Decisions
     * which the selected employee has as (Merits or Deductions);
     */
    public void showAllSettlementsDicisionsDiv() {
        //ISalEmpMonSalariesDTO selectedDTO = (ISalEmpMonSalariesDTO)getSelectedPageDTO();
        //Long realCivilId = selectedDTO.getEmployeesDTO().getRealCivilId();
        //IEmployeesDTO empDTO = selectedDTO.getEmployeesDTO();
        getEmpSettEnquiryBeanHelper().setApprovedStatusOnly(true);
        getEmpSettEnquiryBeanHelper().setYear(year);
        getEmpSettEnquiryBeanHelper().setMonth(month);
        getEmpSettEnquiryBeanHelper().setCivilId(realCivilId.toString());
        empDTO.setName(empDTO.getCitizensResidentsDTO().getName());
        getEmpSettEnquiryBeanHelper().setEmployeesDTO(empDTO);
        getEmpSettEnquiryBeanHelper().setSettlemenetType(settlemenetType);
        getEmpSettEnquiryBeanHelper().setSettWithDecsType(settWithDecsType);
        getEmpSettEnquiryBeanHelper().setRetroactive_SettType(retroactive_SettType);
        getEmpSettEnquiryBeanHelper().viewDecisionsAndSettlementsDetails();
    }

    private void checkEmpHasIntervals() {
        hasIntervals = false;
        //Long empRealCivilID = salEmpMonSalariesDTO.getEmployeesDTO().getRealCivilId() ;
        Long civilId = ((IEmployeesEntityKey)empDTO.getCode()).getCivilId();
        try {
            Long salaryMonth = Long.valueOf(month);
            Long salaryYear = Long.valueOf(year);
            if ( monSalDTO != null && monSalDTO.getPaymentStatusDTO()!=null && monSalDTO.getPaymentStatusDTO().getPaystatusCode()!= null && !monSalDTO.getPaymentStatusDTO().getPaystatusCode().equals(IScpConstants.SAL_PAY_STATUS_SAL_CALC_STOPPED)) {
                hasIntervals = ((ISalEmpMonSalariesClient)getClient()).getEmpVacationsCountByCivilId(civilId,salaryMonth,salaryYear);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String showEmpIntervals() {
    
        ListBean listBean = ListBean.getInstance();
        Long realCivilId = empDTO.getRealCivilId();

        listBean.getEmployeeHelper().setRealCivilId(realCivilId.toString());
        listBean.setRealCivilId(realCivilId);
        try {
            Long salaryMonth = Long.parseLong(month);
            Long yearCode = Long.parseLong(year);
            listBean.setYearCode(yearCode);
            listBean.setSalaryMonth(salaryMonth);
        } catch (NumberFormatException nfe) {
            // TODO: Add catch code
            nfe.printStackTrace();
        }
        listBean.filterByCivilId();
        listBean.setComeFromSalReveiw(true);
        listBean.getSavedDataObjects()[0] = fillSaveStateParams();
        listBean.setNavigationBack(nav_case);

        return "EmpVacEnquiry";
    }

    public static PaySlipMaintainQueryBean getInstance() {
        return (PaySlipMaintainQueryBean)JSFHelper.getValue(BEAN_NAME);
    }
    
    public void setSalEmpPayslipsDTO(ISalEmpPayslipsDTO salEmpPayslipsDTO) {
        this.salEmpPayslipsDTO = salEmpPayslipsDTO;
    }

    public ISalEmpPayslipsDTO getSalEmpPayslipsDTO() {
        return salEmpPayslipsDTO;
    }

    public void setTotalDeductions(BigDecimal totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public BigDecimal getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalMerits(BigDecimal totalMerits) {
        this.totalMerits = totalMerits;
    }

    public BigDecimal getTotalMerits() {
        return totalMerits;
    }

    public void setActualSalary(BigDecimal actualSalary) {
        this.actualSalary = actualSalary;
    }

    public BigDecimal getActualSalary() {
        return actualSalary;
    }

    public void setShowPaySlipPanel(int showPaySlipPanel) {
        this.showPaySlipPanel = showPaySlipPanel;
    }

    public int getShowPaySlipPanel() {
        return showPaySlipPanel;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setEmpDTO(IEmployeesDTO empDTO) {
        this.empDTO = empDTO;
    }

    public IEmployeesDTO getEmpDTO() {
        return empDTO;
    }

    public void setMonSalDTO(ISalEmpMonSalariesDTO monSalDTO) {
        this.monSalDTO = monSalDTO;
    }

    public ISalEmpMonSalariesDTO getMonSalDTO() {
        if (monSalDTO == null) {
            initMonSalDTO();
        }
        return monSalDTO;
    }


    public void setSheetCode(Long sheetCode) {
        this.sheetCode = sheetCode;
    }

    public Long getSheetCode() {
        return sheetCode;
    }

    public void setEmployeeHelper(EmployeeHelper employeeHelper) {
        this.employeeHelper = employeeHelper;
    }

    public EmployeeHelper getEmployeeHelper() {
        return employeeHelper;
    }

    public void setDeductionTable(HtmlDataTable deductionTable) {
        this.deductionTable = deductionTable;
    }

    public HtmlDataTable getDeductionTable() {
        return deductionTable;
    }

    public void setEmpSettEnquiryBeanHelper(EmpSettEnquiryBeanHelper empSettEnquiryBeanHelper) {
        this.empSettEnquiryBeanHelper = empSettEnquiryBeanHelper;
    }

    public EmpSettEnquiryBeanHelper getEmpSettEnquiryBeanHelper() {
        return empSettEnquiryBeanHelper;
    }

    public void setHasIntervals(boolean hasIntervals) {
        this.hasIntervals = hasIntervals;
    }

    public boolean isHasIntervals() {
        return hasIntervals;
    }

    public void setSettlemenetType(String settlemenetType) {
        this.settlemenetType = settlemenetType;
    }

    public String getSettlemenetType() {
        return settlemenetType;
    }

    public void setSettWithDecsType(String settWithDecsType) {
        this.settWithDecsType = settWithDecsType;
    }

    public String getSettWithDecsType() {
        return settWithDecsType;
    }

    public void setRetroactive_SettType(String retroactive_SettType) {
        this.retroactive_SettType = retroactive_SettType;
    }

    public String getRetroactive_SettType() {
        return retroactive_SettType;
    }

    public void setSettler(boolean settler) {
        this.settler = settler;
    }

    public boolean isSettler() {
        return settler;
    }

    public void setMonYearDegree(String monYearDegree) {
        this.monYearDegree = monYearDegree;
    }

//    private String fillDegreeName(Long elementGuideCode) {
//        try {
//            String degreename = SalClientFactory.getSalElementGuidesClient().getDegreeName(elementGuideCode);
//            return degreename;
//        } catch (Exception e) {
//            // TODO: Add catch code
//            e.printStackTrace();
//        }
//        return null;
//    }
    public String fillMonYearDegree() {
////        if (monYearDegree== null  || monYearDegree.equals("")) {
//            if (monSalDTO != null && monSalDTO.getCalcElmguideDTO()!= null) {
//                
//                ISalElementGuidesDTO firstParent = null;
//                try {
//                    List list =
//                        SalClientFactory.getSalElementGuidesClient().searchPayrollByCode(((SalElementGuidesEntityKey)monSalDTO.getCalcElmguideDTO().getFirstParent()).getElmguideCode());
//                    if (list.size() > 0) {
//                        firstParent = (ISalElementGuidesDTO)list.get(0);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                if (firstParent != null) {
//                    monYearDegree = firstParent.getName();
//                    monYearDegree += " / ";
//                }
//                monYearDegree +=
//                        ((((ISalElementGuidesDTO)(monSalDTO.getCalcElmguideDTO().getParentObject())).getParentObject())).getName();
//                monYearDegree += " / ";
//                monYearDegree += ((monSalDTO.getCalcElmguideDTO().getParentObject())).getName();
//
////            }
//
//        }
            
        return monYearDegree;
   }
    private void GetPaymentMethods(){
        
        try{
            ISalPaymentMethodsClient payMethodsClient = SalClientFactory.getSalPaymentMethodsClient();
            paymentMethods = payMethodsClient.getPaymentMethodsForFinDues();
            if(paymentMethods!=null & paymentMethods.size()>0){
                for(IBasicDTO dto : paymentMethods){
                    if(dto.getCode().getKey().equals("1")){
                        paymentMethods.remove(dto);
                        break;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setMonYearJob(String monYearJob) {
        this.monYearJob = monYearJob;
    }

    public String getMonYearJob() {
        return monYearJob;
    }

    public void setMonYearWorkCenter(String monYearWorkCenter) {
        this.monYearWorkCenter = monYearWorkCenter;
    }

    public String getMonYearWorkCenter() {
        return monYearWorkCenter;
    }

    public void setMonYearBgtPrg(String monYearBgtPrg) {
        this.monYearBgtPrg = monYearBgtPrg;
    }

    public String getMonYearBgtPrg() {
        return monYearBgtPrg;
    }

    public void setMonYearBgtType(String monYearBgtType) {
        this.monYearBgtType = monYearBgtType;
    }

    public String getMonYearBgtType() {
        return monYearBgtType;
    }

    public void setCurrBank(String currBank) {
        this.currBank = currBank;
    }

    public String getCurrBank() {
        return currBank;
    }

    public void setCurrBranch(String currBranch) {
        this.currBranch = currBranch;
    }

    public String getCurrBranch() {
        return currBranch;
    }

    public void setCurrAccountNo(String currAccountNo) {
        this.currAccountNo = currAccountNo;
    }

    public String getCurrAccountNo() {
        return currAccountNo;
    }

    public void changeMonth(ActionEvent ae){        
        showPaymentMethod=false;
        reset_Data();  
//        // csc-19378
//        paymentMethodCode=IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE.toString(); 
//        showPaymentMethod=checkEmpHasSeparatedPayMethod(); 
    }
   //must be removed and replaced with Checked method boolean
    public boolean checkEmpHasSeparatedPayMethod() {
  
            try {  
                ISalEmpMonSearchCriteriaDTO salEmpMonSearchCriteriaDTO=SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
                salEmpMonSearchCriteriaDTO.setSalaryMonth(Long.valueOf(month));
                salEmpMonSearchCriteriaDTO.setYear(Long.valueOf(year));
                salEmpMonSearchCriteriaDTO.setCivilId(getRealCivilId());
                salEmpMonSearchCriteriaDTO.setFilterByPayMethod(IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE_2.toString());
                salEmpMonSearchCriteriaDTO.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
                boolean exists =SalClientFactory.getSalEmpMonSalariesClient().countSalMonForSalaryEnquiry(salEmpMonSearchCriteriaDTO);
                
                
          
                return exists;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }
    public void reset_Data(){
       //// System.out.println(">>>>>>>>> change Month <<<<<<<");
            meritsList = new ArrayList<IBasicDTO>();
            deductionsList = new ArrayList<IBasicDTO>();
            showPaySlipPanel=0 ;
            recalculateMonSal();
            setSettler(false);
            setHasIntervals(false);
    }
    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public void setPaymentMethods(List<IBasicDTO> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<IBasicDTO> getPaymentMethods() {
        GetPaymentMethods();
        return paymentMethods;
    }

    public void setShowPaymentMethod(boolean showPaymentMethod) {
        this.showPaymentMethod = showPaymentMethod;
    }

    public boolean isShowPaymentMethod() {
        return showPaymentMethod;
    }


    public void setAdvancedPaymentMonthYear(String advancedPaymentMonthYear) {
        this.advancedPaymentMonthYear = advancedPaymentMonthYear;
    }

    public String getAdvancedPaymentMonthYear() {
        return advancedPaymentMonthYear;
    }



    public String viewEmpDetails() {
        try {
            String civil = getEmpDTO().getCode().getKey();
            Long realCivilId = getEmpDTO().getRealCivilId();
            
            getIntegrationBean().reInitializeBean();
            getIntegrationBean().setBeanNameTo(GovEmpMaintainBean.BEAN_NAME);
            getIntegrationBean().setActionTo(GovEmpMaintainBean.METHOD_NAME_VIEW);
            getIntegrationBean().setNavgationCaseFrom(nav_case);
            getIntegrationBean().getHmBaseBeanFrom().put(BEAN_NAME, evaluateValueBinding(BEAN_NAME));
            getIntegrationBean().setBeanNameFrom(BEAN_NAME);
            getIntegrationBean().setActionFrom(METHOD_NAME_RESTORE);
            // getIntegrationBean().getHmObjects().put(GovEmpMaintainBean.MAP_KEY_CIVIL_ID, emp.getCode().getKey());
            getIntegrationBean().getHmObjects().put(GovEmpMaintainBean.MAP_KEY_CIVIL_ID, civil);
            getIntegrationBean().getHmObjects().put("realCivil", realCivilId);
            GovEmpMaintainBean govEmpMaintainBean = (GovEmpMaintainBean)evaluateValueBinding("govEmpMaintainBean");
            govEmpMaintainBean.setBundleName("com.beshara.csc.hr.scp.integration.presentation.resources.integration");
            govEmpMaintainBean.setTitlePageKey("Gov_emp_data_revision_title");
            
            return getIntegrationBean().goTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

       
    public String restorePage() {
     return nav_case;
    }

    public void navigateSteps(WizardInfo wizardInfo) {
     
        Long realcivil = (Long)getIntegrationBean().getHmObjects().get("realCivil");

        if (wizardInfo.getTargetStep() != null) {
            if (wizardInfo.getTargetStep().equals("step1")) {
                FirstStepBean.getInstance().init();
            } else if (wizardInfo.getTargetStep().equals("step2")) {
                SecondStepBean.getInstance().init();
                //executeMethodBinding("govEmpSecondStepBean.init", null);
            } else if (wizardInfo.getTargetStep().equals("step3")) {
                ThirdStepBean.getInstance().init();
            } else if (wizardInfo.getTargetStep().equals("step4")) {
                ForthStepBean.getInstance();
            } else if (wizardInfo.getTargetStep().equals("step5")) {
                FifthStepBean.getInstance().init();
            } else if (wizardInfo.getTargetStep().equals("step6")) {
                SixthStepBean.getInstance().init();
            }else if (wizardInfo.getTargetStep().equals("WivesStep")){
                
                MerRaiseMaintainBean merRaiseMaintainBean = MerRaiseMaintainBean.getInstance();
                merRaiseMaintainBean.setCivilId("" + realcivil);
                merRaiseMaintainBean.filterByCivilId();  
                executeMethodBinding("raiseWivesBean.filterByCivilId", null);
              
            }
            else if ( wizardInfo.getTargetStep().equals("KidsStep")){
                MerRaiseMaintainBean merRaiseMaintainBean = MerRaiseMaintainBean.getInstance();
                merRaiseMaintainBean.setCivilId("" + realcivil);
                merRaiseMaintainBean.filterByCivilId();  
                executeMethodBinding("raiseKidsBean.filterByCivilId", null);
            }
        }
    }


    public String getMonYearDegree() {
        return monYearDegree;
    }


    public void setEmpEndedService(Boolean empEndedService) {
        this.empEndedService = empEndedService;
    }

    public Boolean getEmpEndedService() {
        return empEndedService;
    }
}
