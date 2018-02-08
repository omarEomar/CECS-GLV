package com.beshara.csc.hr.scp.integration.presentation.backingbean.reviewempmonthsal;


//import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.ded.integration.business.dto.CSCDeductionEnquiryDTO;
import com.beshara.csc.hr.ded.integration.presentation.backingbean.deductionenquiry.DeductionEnquiryBean;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredInMinException;
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
import com.beshara.csc.hr.sal.business.client.ISalElementGuidesClient;
import com.beshara.csc.hr.sal.business.client.ISalEmpMonSalariesClient;
import com.beshara.csc.hr.sal.business.client.SalCalcExceptionsClientImpl;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.client.SalEmpMonSalariesClientImpl;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalElmguideMinDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpCalcSalSearchDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSalariesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpPayslipsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryQueryDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalEmpCalcSalSearchDTO;
import com.beshara.csc.hr.sal.business.dto.SalEmpMonSalariesDTO;
import com.beshara.csc.hr.sal.business.dto.SalEmpSalaryQueryDTO;
import com.beshara.csc.hr.sal.business.entity.ISalElementTypesEntityKey;
import com.beshara.csc.hr.sal.business.entity.ISalEmpMonSalariesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEmpMonSalariesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.hr.scp.integration.presentation.backingbean.empvacenquiry.ListBean;
import com.beshara.csc.hr.vac.business.client.VacClientFactory;
import com.beshara.csc.hr.vac.business.dto.IVacEmpVacationSearchDTO;
import com.beshara.csc.hr.vac.business.dto.VacDTOFactory;
import com.beshara.csc.inf.business.client.IYearsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.exception.EmployeeCivilIdNotExistException;
import com.beshara.csc.nl.bnk.business.client.BnkClientFactory;
import com.beshara.csc.nl.bnk.business.dto.PersonBankAccountsDTO;
import com.beshara.csc.nl.bnk.business.entity.BanksEntityKey;
import com.beshara.csc.nl.org.business.client.IMinistriesClient;
import com.beshara.csc.nl.org.business.client.IWorkCentersClient;
import com.beshara.csc.nl.org.business.client.IWrkLevelsClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersSearchCriteriaDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.wizardbar2.state.WizardInfo;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class ReviewEmpMonthSalBean extends LookUpBaseBean {
    public static final String BEAN_NAME = "reviewEmpMonthSalBean";
    private static final String RESOURCE_PATH_SCP_BUNDLE = "com.beshara.csc.hr.scp.presentation.resources.scp";
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
    private List<IBasicDTO> meritsList;
    private List<IBasicDTO> deductionsList;
    private List<IBasicDTO> meritsListCopy;
    private List<IBasicDTO> deductionsListCopy;

    private boolean empHired = true;
    private boolean payrollInfoExist = true;
    private boolean validCivilId = true;
    private boolean empHiredInMin = true;
    private boolean enableEmpLovDiv;
    private boolean civilExist = false;

    private ISalEmpPayslipsDTO salEmpPayslipsDTO = SalDTOFactory.createSalEmpPayslipsDTO();
    private BigDecimal totalDeductions;
    private BigDecimal totalMerits;
    private BigDecimal actualSalary;
    //private boolean disableReveiwBtn = true;
    private static final BigDecimal limitSalary = BigDecimal.valueOf(10000L);
    private int showPaySlipPanel = 0; // has 3 values 1 if there is data and 2 if there is no data after enter civil id
    private IEmployeesDTO empDTO;
    private ISalEmpMonSalariesDTO monSalDTO;

    private List<IBasicDTO> monSalList;
    private List<String> notes;

    private Long selStatus;
    private String selWrkCenter;
    private String selWrkLevel;
    private String selbgtProgram;

    private List<IBasicDTO> bgtListPrograms;
    private List<IBasicDTO> wrkCenters;
    private List<IBasicDTO> wrkLevels;
    private boolean invalidDedRatio = false;
    private Byte bnkAccountStatus = 1; // 0 : has not valid bnk acc , 1 : his sal bnk acc is valid
    //  , 2 : has valid bnk acc but not his sal bnk acc
    private String invalidDedRatioMsg;
    private boolean expiredChildRaise = false;
    private boolean expiredHiredChildRaise = false;
    private boolean marriedFemaleChild = false;
    private boolean empDead = false;
    private boolean empChildrenDead = false;
    private boolean existApprovedMangDiscount = false;
    private boolean hasNote = false;

    private boolean disabledAutoReview = true;
    private Long totalAutoReviewCount = 0L;
    private EmpSettEnquiryBeanHelper empSettEnquiryBeanHelper;
    private transient HtmlDataTable meritsDataTable = new HtmlDataTable();
    private transient HtmlDataTable deductionsDataTable = new HtmlDataTable();
    private boolean settler = false;
    private boolean hasIntervals = false;
    private String settlemenetType = "1";
    private String settWithDecsType = "1";
    private String retroactive_SettType = "2";
    private List<String> calcExceptionsList = new ArrayList<String>();
    private int calcExceptionsListSize;
    private Boolean errors = false;
    private Long basicSalaryCode = IScpConstants.SAL_ELEM_GUIDE_BASIC_SALARY;
    private Float degreeFirstValue;
    private Float raisesTotalValue;
    private boolean merDedChanged = false;
    Long merDedChangeVal = 0L;
    private Long selNationality;
    private Date calcDate = null;
    public static final String nav_case = "BackToSalReview";

    //private static final String NAVIGATION_KEY = "nav_to_rev_sal";
    private static final String METHOD_NAME_RESTORE = "restorePage";
    private boolean hasBasicData = false;
    //private boolean filterPnlCollapsed = false;
    ISalEmpCalcSalSearchDTO salEmpCalcSalSearchDTO;
    private boolean relatedWorkCentersFlag=false;
    
    private boolean hasUnsignedSett = false;
    private boolean hasUnsignedDeduction = false;
    private boolean totalSalaryLessThanHalfEmpMerits;
    private boolean hasMissionStarted = false;
    private boolean hasMissionStoped = false;
    private boolean hasMissionFinished = false;
    private boolean hasSickVac = false;
    public ReviewEmpMonthSalBean() {
        setContent1Div("paySlipDivContent1");
        setCustomDiv2("m2mEditDivClass");
        setEmpSettEnquiryBeanHelper(new EmpSettEnquiryBeanHelper());
        setClient(SalClientFactory.getSalEmpMonSalariesClient());

        if (month == null && year == null)
            setCurrentMonthYear();
        monSalList = new ArrayList<IBasicDTO>(0);
        setMyTableData(monSalList);
        initEmpDTO();
        setSingleSelection(true);
        setSelectedPageDTO(SalDTOFactory.createSalEmpMonSalariesDTO());
        this.setSelectedRadio("");

        meritsList = new ArrayList<IBasicDTO>();
        deductionsList = new ArrayList<IBasicDTO>();
        meritsListCopy = new ArrayList<IBasicDTO>();
        deductionsListCopy = new ArrayList<IBasicDTO>();


        //        loadMonths();
        //        loadYears();
        //        loadBgtListPrograms();
        //        loadWrkLevels();

        // start of  CSC-18622
        if (getEmpListOfValues() == null)
            setEmpListOfValues(new EmployeeListOfValues());
    }


    public void showSearchForEmployeeDiv() {

        EmployeeListOfValues empListOfValuesBean = ((EmployeeListOfValues)getEmpListOfValues());
        empListOfValuesBean.setEmpListOfValuesStyle("m2mAddDivClass");
        empListOfValuesBean.setSearchMethod(BEAN_NAME + ".searchEmpLovDiv");
        empListOfValuesBean.setUseCustomSearch(true);
        empListOfValuesBean.setReturnMethodName(BEAN_NAME + ".returnMethodAction");
        empListOfValuesBean.resetData();
        empListOfValuesBean.getOkCommandButton().setReRender("emp_panel");
    }

    public String searchEmpLovDiv(Object searchType,
                                  Object searchQuery) { // evaluateValueBinding ( detailBeanName ) I.I
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        employeeListOfValuesBean.setSearchMode(true);
        try {
            IEmpEmployeeSearchDTO searchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
            //searchDTO.setEmployment(true);
            if (searchType.toString().equals(SEARCH_BY_CODE)) {
                searchDTO.setCivilId(Long.valueOf((String)searchQuery));
            } else if (searchType.toString().equals(SEARCH_BY_NAME)) {
                searchDTO.setEmpName(searchQuery.toString());
            }
            searchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            List hireStatusList = new ArrayList();
            hireStatusList.add(IEMPConstant.EMP_STATUS_END_SERVICE);
            hireStatusList.add(IEMPConstant.EMP_STATUS_EMPLOYMENT);
            hireStatusList.add(IEMPConstants.EMP_STATUS_DELEGATION_TO_OUT);
            hireStatusList.add(IEMPConstants.EMP_STATUS_DELEGATION_OUT_TO_IT);
            hireStatusList.add(IEMPConstants.EMP_STATUS_FREEZING);

            searchDTO.setEmpHireStatusList(hireStatusList);
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

    public void returnMethodAction() {
        if (getEmpListOfValues().getSelectedDTOS() != null && getEmpListOfValues().getSelectedDTOS().get(0) != null &&
            getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
            IEmployeesDTO employeesDTO = (IEmployeesDTO)getEmpListOfValues().getSelectedDTOS().get(0);
            realCivilId = employeesDTO.getRealCivilId();

        }
    }


    public void setCurrentMonthYear() {
        Calendar currentDate = Calendar.getInstance();
        month = Integer.toString(currentDate.get(Calendar.MONTH) + 1);
        year = Integer.toString(currentDate.get(Calendar.YEAR));
        System.out.println(month + "--" + year);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowSearch(false);
        app.setShowbar(true);
        app.setShowpaging(false);
        app.setShowLookupAdd(true);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowdatatableContent(false);
        app.setShowCommonData(true);
        app.setShowContent1(true);

        app.setShowScirptGenerator(true);
        app.setShowshortCut(true);
        app.setShowCustomDiv1(true);
        app.setShowCustomDiv2(true);
        app.setShowCustomDiv3(true);
        app.setShowIntegrationDiv1(true);
        // modified by Nora Ismail as it is not exist at screen
        // app.setShowEmpSrchDiv(false);

        // start of  CSC-18622
        app.setShowEmpSrchDiv(true);
        return app;
    }


    public void displayEmpSalaryDetails() {
        if (meritsListCopy != null && !meritsListCopy.isEmpty()) {
            for (IBasicDTO dto1 : meritsListCopy) {
                ISalEmpPayslipsDTO dto = (ISalEmpPayslipsDTO)dto1;
                Long financialGuide = dto.getElementGuidesDTO().getFinancialGuide();
                if (financialGuide != null && financialGuide.equals(basicSalaryCode)) {
                    Long ElmguideCodeDegree = dto.getElementGuidesDTO().getElmguideCodeDegree();
                    ISalElementGuidesClient salElementGuidesClient = SalClientFactory.getSalElementGuidesClient();
                    if (ElmguideCodeDegree != null) {
                        try {
                            degreeFirstValue = salElementGuidesClient.getValueByElmGuideCode(ElmguideCodeDegree);

                            if (degreeFirstValue != null) {
                                raisesTotalValue = dto.getValue().floatValue() - degreeFirstValue;
                            }
                        } catch (DataBaseException e) {
                            e.printStackTrace();
                        } catch (SharedApplicationException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            }
        }

    }

    private void resetMessages() {
        empHired = true;
        empHiredInMin = true;
        payrollInfoExist = true;
        validCivilId = true;
        civilExist = false;

    }


    public void filterByRealCivilId() {
        resetMessages();
        if (realCivilId != null) {
            IEmployeesDTO empDTO = null;
            try {

                //Changed by Ayman to use getHiredAndHavePayrollInfoEmp instead of getEmployeeByStatus
                //So that if the employee financial data is incomplete we got an exception
                empDTO = EmployeeHelper.getHiredAndHavePayrollInfoEmp(Long.valueOf(realCivilId));
                //                ((SalEmpMonSalariesDTO)getPageDTO()).setEmployeesDTO(empDTO);
                //                getAll();
                civilExist = true;
                validCivilId = true;

            } catch (EmployeeNotHiredInMinException e) {
                empHiredInMin = false;
            } catch (EmployeeNotHiredException e) {
                empHired = false;
            } catch (EmployeePayrollInfoNotExistException e) {
                payrollInfoExist = false;
            } catch (EmployeeCivilIdNotExistException e) {
                civilExist = false;
                validCivilId = false;
            } catch (Exception e) {
                civilExist = false;
                validCivilId = false;
            }
        }
    }

    /**
    public void filterByCivilId() {
        showPaySlipPanel = 0;
        if (realCivilId != null) {

            empHired = true;
            payrollInfoExist = true;
            if (GlobalValidation.isValidCivilId(realCivilId)) {
                validCivilId = true;
                civilExist = true;
                try {
                    empDTO =
                            EmpClientFactory.getEmployeesClient().getByRealCivilId(getRealCivilId(), CSCSecurityInfoHelper.getLoggedInMinistryCode());
                    setCivilId(((IEmployeesEntityKey)(empDTO.getCode())).getCivilId());


                } catch (RemoteException e) {
                    e.printStackTrace();
                    initEmpDTO();
                } catch (FinderException e) {
                    e.printStackTrace();
                    initEmpDTO();
                }
                ISalEmpMonSalariesEntityKey code =
                    SalEntityKeyFactory.createSalEmpMonSalariesEntityKey(getRealCivilId(), Long.valueOf(year),
                                                                         Long.valueOf(month), null);
                try {
                    monSalDTO =
                            (ISalEmpMonSalariesDTO)SalClientFactory.getSalEmpMonSalariesClient().getByCivilMonYear(code);
                } catch (Exception e) {
                    initMonSalDTO();
                }
                try {
                    if (empDTO != null && empDTO.getCode() != null) {
                        empHired = EmpClientFactory.getEmployeesClient().isEmployeeHired(empDTO.getCode());
                    } else {
                        empHired = false;
                        civilExist = false;
                    }
                } catch (Exception e) {
                    empHired = false;
                    civilExist = false;
                    e.printStackTrace();
                }
                if (empHired) {
                    Boolean employeeInMinistry = true;
                    try {
                        employeeInMinistry =
                                EmpClientFactory.getEmployeesClient().isEmployeeInMinistry(realCivilId, MINISTRY_CODE);
                    } catch (Exception e) {
                        employeeInMinistry = false;
                        e.printStackTrace();
                    }
                    if (employeeInMinistry) {
                        try {
                            employeesDTO =
                                    (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getEmployeeInfoByElmType(((IEmployeesEntityKey)empDTO.getCode()).getCivilId());
                        } catch (Exception e) {
                            civilExist = false;
                            payrollInfoExist = false;
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                validCivilId = false;
                civilExist = false;
                employeesDTO = EmpDTOFactory.createEmployeesDTO();
            }
        }
    }

    public void reSetData(ActionEvent ae) {
        ae = null;
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
    }


    public ISalEmpSalaryElementsDTO getSalEmpSalaryElementsDTO() {
        if (salEmpSalaryElementsDTO == null && getRealCivilId() != null && civilExist) {
            try {
                salEmpSalaryElementsDTO =
                        (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(getRealCivilId(),
                                                                                                                             ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return salEmpSalaryElementsDTO;
    }
     */


    public void reSetData2(ActionEvent ae) {
        System.out.println("************** reSetData2 *********************");
        ae = null;
        showPaySlipPanel = 0;
        //  employeesDTO = EmpDTOFactory.createEmployeesDTO();
        //   salEmpSalaryElementsDTO = null;
        payrollInfoExist = true;
        enableEmpLovDiv = false;
        validCivilId = true;
        civilExist = false;
        // salEmpMonSalariesDTO=new ISalEmpMonSalariesDTO ( ) ;
        meritsList = new ArrayList<IBasicDTO>();
        deductionsList = new ArrayList<IBasicDTO>();
        actualSalary = new BigDecimal(0L);
        totalMerits = new BigDecimal(0L);
        totalDeductions = new BigDecimal(0L);
        hasBasicData = false ;
        reIntializeMsgs();
    }

    public void applyReviewEmpMonSalCalc() {
        SharedUtilBean sharedUtilBean = getSharedUtil();

        try {
            //IEmployeesDTO selected = (IEmployeesDTO)((ISalEmpMonSalariesDTO)this.getSelectedPageDTO()).getEmployeesDTO();//getEmpDTO();
            ISalEmpMonSalariesDTO selectedSalEmpMonSalariesDTO = (ISalEmpMonSalariesDTO)getSelectedPageDTO();
            if (selectedSalEmpMonSalariesDTO == null || selectedSalEmpMonSalariesDTO.getPaymentStatusDTO() == null) {
                System.out.println("******** applyReviewEmpMonSalCalc  >>>  selectedSalEmpMonSalariesDTO is null or  selectedSalEmpMonSalariesDTO.getPaymentStatusDTO()  is null *********** ");
                this.setShowErrorMsg(true);
                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "selectedSalEmpMonSalariesNullError"));
                setJavaScriptCaller("adjustDataTable('meritsDiv',125); adjustDataTable('deductionsDiv',125);");
                return;
            }
            System.out.println("applyReviewEmpMonSalCalc  >>>  actualSalary = "+actualSalary+" , totalMerits = "+totalMerits+" , totalDeductions = "+totalDeductions);
            if (actualSalary == null || actualSalary.doubleValue() <= 0D) {
                this.setShowErrorMsg(true);
                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "notValidActualSalary"));
                setJavaScriptCaller("adjustDataTable('meritsDiv',125); adjustDataTable('deductionsDiv',125);");
                return;
            }
            
//            if (actualSalary.compareTo(totalMerits.divide(new BigDecimal("2"), 3, BigDecimal.ROUND_HALF_UP) )< 0) {
//                this.setShowErrorMsg(true);
//                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "cannotreviewempsal"));
//                setJavaScriptCaller("adjustDataTable('meritsDiv',125); adjustDataTable('deductionsDiv',125);");
//                return;
//            }
            
            Long currPayStatusCode = selectedSalEmpMonSalariesDTO.getPaymentStatusDTO().getPaystatusCode();
            //            Long currPayStatusCode = IScpConstants.EMP_MON_SAL_CALC_NOT_REVIEWED;
            System.out.println("applyReviewEmpMonSalCalc :::  currPayStatusCode = " + currPayStatusCode);
            Long newPaystatusCodeActual = IScpConstants.EMP_MON_SAL_CALC_REVIEWED;
            if (IScpConstants.EMP_MON_SAL_CALC_NOT_REVIEWED.equals(currPayStatusCode)) {
                newPaystatusCodeActual = IScpConstants.EMP_MON_SAL_CALC_REVIEWED;
            } else if (IScpConstants.EMP_MON_SAL_CALC_DURATIONS_NOT_REVIEWED.equals(currPayStatusCode)) {
                newPaystatusCodeActual = IScpConstants.EMP_MON_SAL_CALC_DURATIONS_REVIEWED;
            } else {
                this.setShowErrorMsg(true);
                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE,
                                                                            "reviewedFail_payStatusCodeError"));
                return;
            }
            Boolean updatePayStatusCode = updateEmpMonSalPayStatusCode(true);
            if (updatePayStatusCode) {
                setSelectedRadio("");
                getSelectedDTOS().clear();
                setSelectedPageDTO(SalDTOFactory.createSalEmpMonSalariesDTO());
                settler = false;
                hasIntervals = false;
                totalSalaryLessThanHalfEmpMerits=false;
                sharedUtilBean.setSuccessMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "reviewedSuccess"));
                
//                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "reviewedSuccess"));
            } else {
                this.setShowErrorMsg(true);
                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "reviewedFail"));
            }
        } catch (DataBaseException de) {
            // TODO: Add catch code
            de.printStackTrace();
            this.setShowErrorMsg(true);
            sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "reviewedFail"));
        } catch (SharedApplicationException se) {
            se.printStackTrace();
            this.setShowErrorMsg(true);
            sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "reviewedFail"));
        }
    }

    public void applyCancelReviewEmpMonSalCalc() {
        SharedUtilBean sharedUtilBean = getSharedUtil();
        try {

            SalEmpMonSalariesDTO selectedSalEmpMonSalariesDTO = (SalEmpMonSalariesDTO)getSelectedPageDTO();
            if(selectedSalEmpMonSalariesDTO == null || selectedSalEmpMonSalariesDTO.getPaymentStatusDTO() == null ){
                this.setShowErrorMsg(true);
                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "selectedSalEmpMonSalariesNullError"));
                setJavaScriptCaller("adjustDataTable('meritsDiv',125); adjustDataTable('deductionsDiv',125);");
               return;   
            }

            System.out.println("applyCancelReviewEmpMonSalCalc actualSalary = "+actualSalary+" , totalMerits = "+totalMerits+" , totalDeductions = "+totalDeductions);
            if (actualSalary == null || actualSalary.doubleValue() <= 0D) {
                this.setShowErrorMsg(true);
                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "notValidActualSalary"));
                setJavaScriptCaller("adjustDataTable('meritsDiv',125); adjustDataTable('deductionsDiv',125);");
                return;
            }

            Long currPayStatusCode = selectedSalEmpMonSalariesDTO.getPaymentStatusDTO().getPaystatusCode();

            //            Long currPayStatusCode = IScpConstants.EMP_MON_SAL_CALC_NOT_REVIEWED;

            System.out.println("applyCancelReviewEmpMonSalCalc :::  currPayStatusCode = " + currPayStatusCode);
            Long newPaystatusCodeActual = IScpConstants.EMP_MON_SAL_CALC_NOT_REVIEWED;
            if (IScpConstants.EMP_MON_SAL_CALC_REVIEWED.equals(currPayStatusCode)) {
                newPaystatusCodeActual = IScpConstants.EMP_MON_SAL_CALC_NOT_REVIEWED;
            } else if (IScpConstants.EMP_MON_SAL_CALC_DURATIONS_REVIEWED.equals(currPayStatusCode)) {
                newPaystatusCodeActual = IScpConstants.EMP_MON_SAL_CALC_DURATIONS_NOT_REVIEWED;
            } else {
                this.setShowErrorMsg(true);
                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE,
                                                                            "cancelReviewedFail_payStatusCodeError"));
                return;
            }

            Boolean updatePayStatusCode = updateEmpMonSalPayStatusCode(false);
            if (updatePayStatusCode) {
                sharedUtilBean.setSuccessMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "cancelReviewedSuccess"));
                
//                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE,
//                                                                            "cancelReviewedSuccess"));
            setSelectedRadio("");
            getSelectedDTOS().clear();
            setSelectedPageDTO(SalDTOFactory.createSalEmpMonSalariesDTO());
            settler = false;
            hasIntervals = false;
            totalSalaryLessThanHalfEmpMerits=false;
            } else {
                this.setShowErrorMsg(true);
                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE,
                                                                            "cancelReviewedFail"));
            }
        } catch (DataBaseException de) {
            // TODO: Add catch code
            de.printStackTrace();
            this.setShowErrorMsg(true);
            sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE,
                                                                        "cancelReviewedFail"));
        } catch (SharedApplicationException se) {
            se.printStackTrace();
            this.setShowErrorMsg(true);
            sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE,
                                                                        "cancelReviewedFail"));
        }
    }

    public void refreshData() {
        System.out.println("************** refreshData *********************");
//        fillPaySlipData();/* no need to call it after reveiw or cancel reveiw because we reset data */
        loadEmpListOnly();//loadEmpList();
//        reSetData2(null);/* no need to call it again because it was called inside loadEmpList() */
        //        try {
        //            setSelectedPageDTO(getClient().getById(getSelectedPageDTO().getCode()));
        //        } catch (DataBaseException e) {
        //        } catch (SharedApplicationException e) {
        //        }
    }

    /**
     * update PayStatusCode for Emp Mon Sal
     * used in review emp sal calc with value newPayStatusCode = new Long(9)
     * and used in cancel review emp sal calc with value newPayStatusCode = new Long(16)
     * @return true if updated else return false
     * @throws SharedApplicationException
     * @throws DataBaseException
     * @author Ayman Mahmoud
     * @since 03/10/2014
     *
     * updated by: Ayman Mahmoud - 10/10/2015
     * the update was to deal with updating more that one record for the same employee in the same month & year
     * if that employee has more than on record to be reviewed (ex: base salary & findues)
     * So, the method will take a boolean true if the items are to be reviewed, false to cancel the review
     */
    public Boolean updateEmpMonSalPayStatusCode(Boolean reviewEmpSalary) throws DataBaseException,
                                                                                SharedApplicationException {
        System.out.println("************** updateEmpMonSalPayStatusCode ********************* reviewEmpSalary = "+reviewEmpSalary);
        try {
            SalEmpMonSalariesClientImpl salEmpMonSalariesClient =
                (SalEmpMonSalariesClientImpl)SalClientFactory.getSalEmpMonSalariesClient();

            SalEmpMonSalariesDTO selectedSalEmpMonSalariesDTO = (SalEmpMonSalariesDTO)getSelectedPageDTO();
            ISalEmpMonSalariesEntityKey salEmpMonSalariesEntityKey = null;


            if (selectedSalEmpMonSalariesDTO != null)
                salEmpMonSalariesEntityKey = (ISalEmpMonSalariesEntityKey)selectedSalEmpMonSalariesDTO.getCode();


            //        SalEmpMonSalariesEntityKey salEmpMonSalariesEntityKey = (SalEmpMonSalariesEntityKey)SalEntityKeyFactory
            //            .createSalEmpMonSalariesEntityKey(new Long("306090300366"), new Long("2014"), new Long("7"), new Long("1"));
            //IEmployeesEntityKey selIEmployeesEntityKey = (IEmployeesEntityKey)getEmpDTO().getCode();//((IEmployeesDTO)selectedDTOsList.get(0));

            if (salEmpMonSalariesEntityKey != null) {
                Long monthCode = salEmpMonSalariesEntityKey.getSalaryMonth(); //searchDTO.getMonthCode();
                Long yearCode = salEmpMonSalariesEntityKey.getYearCode(); //searchDTO.getYearCode();
                Long salaryCivilId = salEmpMonSalariesEntityKey.getCivilId();
                List<IBasicDTO> civilSalaryItems =
                    salEmpMonSalariesClient.getSalMonByCivilAndDateAndPayMethod(salaryCivilId, monthCode, yearCode,
                                                                                reviewEmpSalary,
                                                                                IScpConstants.MONTHLY_SALARIES_PAYMETHOD);
                //Long serial = salEmpMonSalariesEntityKey.getSerial();
                //            System.out.println(">>>>  monthCode = "+ monthCode);
                //            System.out.println(">>>>  civilId1  = "+civilId1 );
                //            System.out.println(">>>>  yearCode  = "+yearCode);
                //            System.out.println(">>>>  serial  = "+serial);
                Boolean updatePayStatusCode =
                    salEmpMonSalariesClient.updateEmpMonSalListPayStatusCode(civilSalaryItems, true, false,
                                                                             reviewEmpSalary);
                refreshData();
                return updatePayStatusCode;
            } else {
                // error
                return Boolean.FALSE;
            }
        } catch (DataBaseException de) {
            de.printStackTrace();
            throw de;
        } catch (SharedApplicationException se) {
            se.printStackTrace();
            throw se;
        }
    }

    /* public String getDegree() {
        if (degree == null) {
            if (getSalEmpSalaryElementsDTO() != null) {
                ISalElementGuidesDTO firstParent = null;
                try {
                    //                    firstParent =
                    //                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getById(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFirstParent());
                    List list =
                        SalClientFactory.getSalElementGuidesClient().searchPayrollByCode(((SalElementGuidesEntityKey)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFirstParent()).getElmguideCode());
                    if (list.size() > 0) {
                        firstParent = (ISalElementGuidesDTO)list.get(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (firstParent != null) {
                    degree = firstParent.getName();
                    degree += " / ";
                }
                degree +=
                        ((((ISalElementGuidesDTO)(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject())).getParentObject())).getName();
                degree += " / ";
                degree += ((salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject())).getName();
                //                degree += " / ";
                //                degree += salEmpSalaryElementsDTO.getSalElementGuidesDTO().getName();
            }

        }
        return degree;
    }
*/

    public void loadMonths() {
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
    }

    public void loadYears() {
        if (years == null) {
            years = new ArrayList();
            try {
                IYearsClient client = InfClientFactory.getYearsClient();
                setYears(client.getCodeName());
                years = client.getCodeName();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadEmpListOnly() {
        System.out.println("************** loadEmpListOnly *********************");
        this.reSetData2(null);
        clearData();
        filterByRealCivilId();
        if ((civilExist && validCivilId && realCivilId != null) || (realCivilId == null)) {
            showPaySlipPanel = 0;
            ISalEmpCalcSalSearchDTO salEmpCalcSalSearchDTO = SalDTOFactory.createSalEmpCalcSalSearchDTO();
            salEmpCalcSalSearchDTO.setBgtProgramsCode(selbgtProgram == null || selbgtProgram.equals("-100") ? null :
                                                      selbgtProgram.toString());
            salEmpCalcSalSearchDTO.setWorkCenterCode(selWrkCenter == null || selWrkCenter.equals("-100") ? null :
                                                     selWrkCenter.toString());
            salEmpCalcSalSearchDTO.setRelatedWorkCentersFlag(selWrkCenter == null || selWrkCenter.equals("-100") ? false :
                                                     relatedWorkCentersFlag);
            salEmpCalcSalSearchDTO.setWorkLevelCode(selWrkLevel == null || selWrkLevel.equals("-100") ? null :
                                                    selWrkLevel.toString());
            salEmpCalcSalSearchDTO.setStatusFlag(Long.valueOf(selStatus));

            salEmpCalcSalSearchDTO.setNationalityType(selNationality);
            salEmpCalcSalSearchDTO.setYearCode(Long.valueOf(year));
            salEmpCalcSalSearchDTO.setMonthCode(Long.valueOf(month));
            salEmpCalcSalSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            if (civilExist && validCivilId && realCivilId != null) {
                salEmpCalcSalSearchDTO.setRealCivilId(realCivilId);
            }
            salEmpCalcSalSearchDTO.setPaymethodCode(IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE.toString());


            try {
                //this.setSelectedPageDTO(SalDTOFactory.createSalEmpMonSalariesDTO() );
                this.setSelectedRadio(""); // by Aly Nour to reset selected dto
                monSalList =
                        SalClientFactory.getSalEmpMonSalariesClient().getCalcEmployeesToReview(salEmpCalcSalSearchDTO);
//                if (monSalList != null && monSalList.size() == 1 && realCivilId != null) {
//                    IClientDTO selected = (IClientDTO)this.monSalList.get(0);
//                    this.getSelectedDTOS().clear();
//                    this.getSelectedDTOS().add(selected);
//                    setSelectedPageDTO(selected);
//                    loadSelectedEmployeeData();
//                    setSelectedRadio(monSalList.get(0).getCode().getKey());
//                    hasBasicData= true ;
//
//                }
                setMyTableData(monSalList);
                
                /*if review or cancel review last record in page other than first page and filter with specified pay status not All
                 * then show the previous page */
                if (!selStatus.equals(getAllStatus() ) ) {
                    int recordsNo = monSalList.size();
                    if(recordsNo % getSharedUtil().getNoOfTableRows() == 0 ){
                        int currPageIndex = getCurrentPageIndex();
                        System.out.println("loadEmpListOnly currPageIndex = "+currPageIndex);
                        if(currPageIndex > 1){
                            setCurrentPageIndex(currPageIndex - 1);
                            setOldPageIndex(currPageIndex - 2);
                            getMyDataTable().setFirst((currPageIndex - 2) * 14);
                        }
                    }
                }
                String dateStr = year + "-" + String.format("%02d", Integer.parseInt(month)) + "-01";
                calcDate = Date.valueOf(dateStr);

                if (selStatus.equals(getNotReviewed()) && !monSalList.isEmpty()) {
                    disabledAutoReview = false;
                } else {
                    disabledAutoReview = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                monSalList = new ArrayList();
                setMyTableData(monSalList);
                disabledAutoReview = true;
            }
        } else {
            monSalList = new ArrayList();
            setMyTableData(monSalList);
        }
    }

    public void loadEmpList() {
        System.out.println("************** loadEmpList *********************");
        this.reSetData2(null);
        //resetPageIndex();
        clearData();
        filterByRealCivilId();
        //setFilterPnlCollapsed(true);
        if ((civilExist && validCivilId && realCivilId != null) || (realCivilId == null)) {
            showPaySlipPanel = 0;
            salEmpCalcSalSearchDTO = SalDTOFactory.createSalEmpCalcSalSearchDTO();
            salEmpCalcSalSearchDTO.setBgtProgramsCode(selbgtProgram == null || selbgtProgram.equals("-100") ? null :
                                                      selbgtProgram.toString());
            salEmpCalcSalSearchDTO.setWorkCenterCode(selWrkCenter == null || selWrkCenter.equals("-100") ? null :
                                                     selWrkCenter.toString());
            salEmpCalcSalSearchDTO.setRelatedWorkCentersFlag(selWrkCenter == null || selWrkCenter.equals("-100") ? false :
                                                     relatedWorkCentersFlag);
            salEmpCalcSalSearchDTO.setWorkLevelCode(selWrkLevel == null || selWrkLevel.equals("-100") ? null :
                                                    selWrkLevel.toString());
            salEmpCalcSalSearchDTO.setStatusFlag(Long.valueOf(selStatus));

            salEmpCalcSalSearchDTO.setNationalityType(selNationality);
            salEmpCalcSalSearchDTO.setYearCode(Long.valueOf(year));
            salEmpCalcSalSearchDTO.setMonthCode(Long.valueOf(month));
            salEmpCalcSalSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            if (civilExist && validCivilId && realCivilId != null) {
                salEmpCalcSalSearchDTO.setRealCivilId(realCivilId);
            }
            salEmpCalcSalSearchDTO.setPaymethodCode(IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE.toString());


            try {
                //this.setSelectedPageDTO(SalDTOFactory.createSalEmpMonSalariesDTO() );
                this.setSelectedRadio(""); // by Aly Nour to reset selected dto
                monSalList =
                        SalClientFactory.getSalEmpMonSalariesClient().getCalcEmployeesToReview(salEmpCalcSalSearchDTO);
                if (monSalList != null && monSalList.size() == 1 && realCivilId != null) {
                    IClientDTO selected = (IClientDTO)this.monSalList.get(0);
                    this.getSelectedDTOS().clear();
                    this.getSelectedDTOS().add(selected);
                    setSelectedPageDTO(selected);
                    loadSelectedEmployeeData();
                    setSelectedRadio(monSalList.get(0).getCode().getKey());
                    hasBasicData= true ;

                }
                setMyTableData(monSalList);

                String dateStr = year + "-" + String.format("%02d", Integer.parseInt(month)) + "-01";
                calcDate = Date.valueOf(dateStr);

                if (selStatus.equals(getNotReviewed()) && !monSalList.isEmpty()) {
                    disabledAutoReview = false;
                } else {
                    disabledAutoReview = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                monSalList = new ArrayList();
                setMyTableData(monSalList);
                disabledAutoReview = true;
            }
        } else {
            monSalList = new ArrayList();
            setMyTableData(monSalList);
        }
    }

    public void clearData() {
        System.out.println("************** clearData *********************");
            
        deductionsList = new ArrayList<IBasicDTO>();
        totalDeductions = new BigDecimal(0L);
        totalMerits = new BigDecimal(0L);
        meritsList = new ArrayList<IBasicDTO>();
        setSelectedPageDTO(SalDTOFactory.createSalEmpMonSalariesDTO());
        setExpiredChildRaise(false);
        expiredHiredChildRaise=false;
        setMarriedFemaleChild(false);
        empDead=false;
        empChildrenDead=false;
        hasUnsignedDeduction=false;
        setMerDedChanged(false);
        setMerDedChangeVal(0L);
        setCalcDate(null);
        existApprovedMangDiscount = false;
        bnkAccountStatus = 1;
        hasIntervals = false;
        totalSalaryLessThanHalfEmpMerits=false;
        actualSalary = null;
        setSettler(false);
        setErrors(false);
        calcExceptionsList = new ArrayList();
        this.setSelectedRadio("");
        setHasUnsignedSett(false);
        hasMissionStarted=false;
        hasMissionStoped=false;
        hasMissionFinished=false;
        hasSickVac=false;
    }

    public void fillPaySlipData() {
        showPaySlipPanel = 2;
        ISalEmpMonSearchCriteriaDTO searchDTO = SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
        searchDTO.setCivilId(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getRealCivilId());
        searchDTO.setSalaryMonth(Long.valueOf(month));
        searchDTO.setYear(Long.valueOf(year));
        String filterByPayType =
            IScpConstants.MON_SAL_PAY_TYPE_SALARIES.toString() + "," + IScpConstants.MON_SAL_PAY_TYPE_OTHERS.toString();
        //We will get review or not reviewed
        String filterByPayStatus =
            IScpConstants.EMP_MON_SAL_CALC_NOT_REVIEWED.toString() + "," + IScpConstants.EMP_MON_SAL_CALC_DURATIONS_NOT_REVIEWED.toString() +
            "," + IScpConstants.EMP_MON_SAL_CALC_REVIEWED.toString() + "," +
            IScpConstants.EMP_MON_SAL_CALC_DURATIONS_REVIEWED.toString();

        searchDTO.setFilterByPayType(filterByPayType);
        searchDTO.setFilterByPayStatus(filterByPayStatus);
        try {
            meritsList =
                    SalClientFactory.getSalEmpPayslipsClient().getMerDedList_new(searchDTO, IScpConstants.MONTHLY_SALARIES_PAYMETHOD);
            meritsListCopy = (List<IBasicDTO>)getSharedUtil().deepCopyObject(meritsList);
            showPaySlipPanel = 1;

        } catch (Exception e) {
            e.printStackTrace();
            meritsList = new ArrayList<IBasicDTO>();
        }
        try {
            searchDTO.setSalElementType(IScpConstants.MON_SAL_TYPE_DED);
            deductionsList =
                    SalClientFactory.getSalEmpPayslipsClient().getMerDedList_new(searchDTO, IScpConstants.MONTHLY_SALARIES_PAYMETHOD);
            deductionsListCopy = (List<IBasicDTO>)getSharedUtil().deepCopyObject(deductionsList);
            showPaySlipPanel = 1;
        } catch (Exception e) {
            e.printStackTrace();
            deductionsList = new ArrayList<IBasicDTO>();
        }
        calcuateValues();
    }

    public void selectedRadioButton(ActionEvent event) throws Exception {
        settler = false;
        hasIntervals = false;
        hasBasicData= true ;
        totalSalaryLessThanHalfEmpMerits=false;
        //setFilterPnlCollapsed(true);
        setIndexOfSelectedDataInDataTable(getMyDataTable().getRowIndex());
        this.getSelectedDTOS().clear();
        IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();
        this.getSelectedDTOS().add(selected);
        setSelectedPageDTO(selected);
        loadSelectedEmployeeData();
    }

    public void loadSelectedEmployeeData() {
        settler = false;
        hasIntervals = false;
        hasUnsignedSett = false ;
        totalSalaryLessThanHalfEmpMerits=false;
        System.out.println("loadSelectedEmployeeData 1 - "+new java.util.Date());
        fillPaySlipData();
        System.out.println("loadSelectedEmployeeData 2 - "+new java.util.Date());
        checkDeductionsRatio();
        System.out.println("loadSelectedEmployeeData 3 - "+new java.util.Date());
        checkValidBnkAccount((ISalEmpMonSalariesDTO)getSelectedPageDTO());
        System.out.println("loadSelectedEmployeeData 4 - "+new java.util.Date());
        //checkExistApprovedMangDiscount( (ISalEmpMonSalariesDTO)getSelectedPageDTO() );
        checkEmpHasIntervals((ISalEmpMonSalariesDTO)getSelectedPageDTO());
        System.out.println("loadSelectedEmployeeData 5 - "+new java.util.Date());
        checkExpiredChildRaise(Long.valueOf(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getCode().getKey()));
        checkExpiredHiredChildRaise(Long.valueOf(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getCode().getKey()));
        checkMarriedFemaleChild(Long.valueOf(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getCode().getKey()));
        checkEmpDead(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getRealCivilId());
        checkEmpChildrenDead(Long.valueOf(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getCode().getKey()));
        checkEmpHasUnsignedDeduction(Long.valueOf(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getCode().getKey()));
        checkEmpHasMissions(Long.valueOf(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getCode().getKey()));
        checkEmpHasSickVac();
        System.out.println("loadSelectedEmployeeData 6 - "+new java.util.Date());
        checkMerDedChanged();
        System.out.println("loadSelectedEmployeeData 7 - "+new java.util.Date());
        checkHasUnsignedSett(getSelectedPageDTO());
        
        if(!hasIntervals){
            totalSalaryLessThanHalfEmpMerits=checkHasTotalSalaryLessThanHalfEmpMerits();
        }
        
    }
    public boolean checkHasTotalSalaryLessThanHalfEmpMerits() {       
        List<ISalElementGuidesDTO>  benfitsList = new ArrayList<ISalElementGuidesDTO>();
        List<ISalElementGuidesDTO>  deductionsList = new ArrayList<ISalElementGuidesDTO>();
        float benfitsTotalValue=0F;
        float dedectionsTotalValue=0F;
        float totalSalary=0F;
        ISalElementGuidesDTO elementGuidesDTO;
        Map<IEntityKey, ISalElementGuidesDTO> merMap = new TreeMap<IEntityKey, ISalElementGuidesDTO>();
        Map<IEntityKey, ISalElementGuidesDTO> dedMap = new TreeMap<IEntityKey, ISalElementGuidesDTO>();
       
        ISalEmpSalaryQueryDTO salEmpSalaryQueryDTO = new SalEmpSalaryQueryDTO();
        Long civilId = null;
        if (getSelectedPageDTO()==null)
            return false;
            
            IEmployeesDTO employeesDTO=((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO();       
            try {
            civilId = Long.valueOf(employeesDTO.getCode().getKey());           
            salEmpSalaryQueryDTO.setCivilId(civilId);        
            salEmpSalaryQueryDTO.setFromDate(getFromDate_());
            salEmpSalaryQueryDTO.setUntilDate(getUntilDate_());      
        
            salEmpSalaryQueryDTO.setType("1");            
            salEmpSalaryQueryDTO.setMinCode(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getCalcMinCode());
            benfitsList = SalClientFactory.getSalElementGuidesClient().filterEmpSalaryQueryList(salEmpSalaryQueryDTO);
            benfitsTotalValue = 0;
            if (benfitsList != null && benfitsList.size() != 0) {

                for (ISalElementGuidesDTO dto : benfitsList) {
                    if (dto.getValue() != null) {
                        //benfitsTotalValue += dto.getValue();
                        /**by saad**/
                        if (dto.getFromDate().before(salEmpSalaryQueryDTO.getFromDate()))
                            dto.setFromDate(new Timestamp(salEmpSalaryQueryDTO.getFromDate().getTime()));
                        if (dto.getUntilDate() == null ||
                            dto.getUntilDate().after(salEmpSalaryQueryDTO.getUntilDate()))
                            dto.setUntilDate(new Timestamp(salEmpSalaryQueryDTO.getUntilDate().getTime()));
                        /****/
                        float actualVal = actualValue(dto.getValue(), dto.getFromDate(), dto.getUntilDate());
                        dto.setActualValue(actualVal);                       
                        benfitsTotalValue += dto.getActualValue();
                    }
                    // by saad                    
                    if (merMap.get(dto.getCode()) != null) {
                        elementGuidesDTO = merMap.get(dto.getCode());
                        
                        if (dto.getValue() != null) {
                            elementGuidesDTO.setValue(elementGuidesDTO.getValue() + (dto.getValue()));
                            elementGuidesDTO.setActualValue(elementGuidesDTO.getActualValue() +
                                                            (dto.getActualValue()));                            
                           
                        }
                    } else {
                        merMap.put(dto.getCode(), dto);
                    }

                }
                benfitsList = new ArrayList(merMap.values());                
                ///////////////
            }


        } catch (SharedApplicationException e) {
            benfitsList = new ArrayList<ISalElementGuidesDTO>();
        } catch (DataBaseException e) {
            benfitsList = new ArrayList<ISalElementGuidesDTO>();
        } catch (ParseException e) {
            benfitsList = new ArrayList<ISalElementGuidesDTO>();
        }
        try {
            salEmpSalaryQueryDTO.setType("2");
            dedectionsTotalValue = 0;
            salEmpSalaryQueryDTO.setRealCivilId(employeesDTO.getRealCivilId());
            deductionsList = SalClientFactory.getSalElementGuidesClient().getEmpSalaryDeductList(salEmpSalaryQueryDTO);

            if (deductionsList != null && deductionsList.size() != 0) {

                for (ISalElementGuidesDTO dto : deductionsList) {
                    dedectionsTotalValue += dto.getValue(); // by saad
                    if (dedMap.get(dto.getCode()) != null) {
                        elementGuidesDTO = dedMap.get(dto.getCode());
                        if (dto.getValue() != null) {
                            elementGuidesDTO.setValue(elementGuidesDTO.getValue() + (dto.getValue()));
                        }
                    } else {
                        dedMap.put(dto.getCode(), dto);
                    }

                }
                deductionsList = new ArrayList(dedMap.values());                
                ///////////////
            }
        } catch (SharedApplicationException e) {
            deductionsList = new ArrayList<ISalElementGuidesDTO>();
        } catch (DataBaseException e) {
            deductionsList = new ArrayList<ISalElementGuidesDTO>();
        }
       // totalSalary = benfitsTotalValue - dedectionsTotalValue;
        if (actualSalary.compareTo((new BigDecimal(benfitsTotalValue)).divide(new BigDecimal("2"), 3, BigDecimal.ROUND_HALF_UP) )< 0) {           
           return true;
        }else{
            return false;
        }
    }
    private float actualValue(float value, java.util.Date fromDate, java.util.Date untilDate) {
        //            if(fromDate.before(salEmpSalaryQueryDTO.getFromDate()))
        //                fromDate=salEmpSalaryQueryDTO.getFromDate();
        //            if(untilDate==null || untilDate.after(salEmpSalaryQueryDTO.getUntilDate()))
        //                untilDate=salEmpSalaryQueryDTO.getUntilDate();
        float actualVal = 0F;
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(fromDate);
        int fromDay = fromCal.get(Calendar.DATE);
        int fromMonth = fromCal.get(Calendar.MONTH) + 1;
        int fromYear = fromCal.get(Calendar.YEAR);
        int fromMonthDays = fromCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (untilDate == null) {
            actualVal = (fromMonthDays - fromDay + 1) * value / fromMonthDays;
            actualVal=Float.parseFloat(String.format("%.3f", actualVal));
            return actualVal;
        }

        Calendar toCal = Calendar.getInstance();
        toCal.setTime(untilDate);
        int toDay = toCal.get(Calendar.DATE);
        int toMonth = toCal.get(Calendar.MONTH) + 1;
        int toYear = toCal.get(Calendar.YEAR);
        int toMonthDays = toCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (toYear > fromYear || toMonth > fromMonth) {
            actualVal = (fromMonthDays - fromDay + 1) * value / fromMonthDays;
            actualVal=Float.parseFloat(String.format("%.3f", actualVal));
            return actualVal;
        }
        if (toYear == fromYear && toMonth == fromMonth) {
            actualVal = (toDay - fromDay + 1) * value / fromMonthDays;
            actualVal=Float.parseFloat(String.format("%.3f", actualVal));
            return actualVal;
        }
       actualVal=Float.parseFloat(String.format("%.3f", actualVal));
        return actualVal;
    }
    private java.util.Date getFromDate_() throws ParseException {
        String fromDateStr = "01/" + month + "/" + year;
        java.util.Date fromDate = new SimpleDateFormat("dd/M/yyyy").parse(fromDateStr);
        return fromDate;
    }

    private java.util.Date getUntilDate_() throws ParseException {
        String untilDateStr =
            String.valueOf(getlastDateofThisMonth(Integer.valueOf(month), Integer.valueOf(year))) + "/" + month + "/" +
            year;
        java.util.Date untilDate = new SimpleDateFormat("dd/M/yyyy").parse(untilDateStr);
        return untilDate;
    }

    public static final int getlastDateofThisMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        if (year > -1) {
            calendar.set(Calendar.YEAR, year);
        }
        if (month > -1) {
            month--;
            calendar.set(Calendar.MONTH, month);
        }
        return calendar.getActualMaximum(Calendar.DATE);
    }

    private void checkExpiredChildRaise(Long parentCivilID) {
        setExpiredChildRaise(false);
        Long count = 0L;
        try {
            count = SalClientFactory.getSalEmpChildrenClient().countExcludedExceedAgeEmpChildern(parentCivilID);
            System.out.println("checkExpiredChildRaise parentCivilID = "+parentCivilID+" , count = "+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count > 0) {
            setExpiredChildRaise(true);
        }
    }
    private void checkExpiredHiredChildRaise(Long parentCivilID) {
        setExpiredHiredChildRaise(false);
        Long count = 0L;
        try {
            count = SalClientFactory.getSalEmpChildrenClient().countExcludedHiredEmpChildern(parentCivilID);
            System.out.println("checkExpiredHiredChildRaise parentCivilID = "+parentCivilID+" , count = "+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count > 0) {
            setExpiredHiredChildRaise(true);
        }
    }
    private void checkMarriedFemaleChild(Long parentCivilID) {
        setMarriedFemaleChild(false);
        Long count = 0L;
        try {
            count = SalClientFactory.getSalEmpChildrenClient().countExcludedEmpFemaleChildern(parentCivilID);
            System.out.println("checkMarriedFemaleChild parentCivilID = "+parentCivilID+" , count = "+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count > 0) {
            setMarriedFemaleChild(true);
        }
    }
    private void checkEmpDead(Long empRealCivilID) {
        setEmpDead(false);        
        try {
            empDead = SalClientFactory.getSalEmpChildrenClient().checkIfEmpDead(empRealCivilID);
            System.out.println("checkEmpDead empRealCivilID = "+empRealCivilID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    private void checkEmpChildrenDead(Long parentCivilID) {
        setEmpChildrenDead(false);
        Long count = 0L;
        try {
            count = SalClientFactory.getSalEmpChildrenClient().countExcludedEmpDeadChildern(parentCivilID);
            System.out.println("checkEmpChildrenDead parentCivilID = "+parentCivilID+" , count = "+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count > 0) {
            setEmpChildrenDead(true);
        }
    }
    private void checkEmpHasMissions(Long civilId) {
        hasMissionStarted=false;
        hasMissionStoped=false;
        hasMissionFinished=false;
        String dateStr = year + "-" + String.format("%02d", Integer.parseInt(month)) + "-01";
        Date calc_Date = Date.valueOf(dateStr);
        Long status = 0L;
        try {
            status = SalClientFactory.getSalEmpMonSalariesClient().getEmpMissionStatus(civilId,Long.parseLong(month),Long.parseLong(year));
            if(status.equals(6L))
                hasMissionStarted=true;
            else if(status.equals(11L) || status.equals(16L))
                hasMissionStoped=true;
            else if(status.equals(22L))
                hasMissionFinished=true;
            System.out.println("checkEmpHasMissions civilId = "+civilId);
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    private void checkEmpHasSickVac() {
        hasSickVac=false;
        try {
            IVacEmpVacationSearchDTO searchVacDTO=VacDTOFactory.createVacEmpVacationSearchDTO();
            initSearchDTO(searchVacDTO);
        
            boolean found = VacClientFactory.getVacEmployeeVacationsClient().checkEmployeeHasSickVac(searchVacDTO);
            if(found)
                hasSickVac=true;
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    private void initSearchDTO(IVacEmpVacationSearchDTO searchVacDTO){
       
        String fromDateStr = year + "-" + String.format("%02d", Integer.parseInt(month)) + "-01";        
        Long lastDayOfMonth=SharedUtils.getLastDayOfMonth(Long.parseLong(month), Long.parseLong(year));
        String untilDateStr = year + "-" + String.format("%02d", Integer.parseInt(month)) +"-"+ lastDayOfMonth.toString();
        Date calcDate = Date.valueOf(fromDateStr);
        searchVacDTO.setFromDate(calcDate);
        calcDate=Date.valueOf(untilDateStr);
        searchVacDTO.setUntilDate(calcDate);
        searchVacDTO.setStatusCode(IScpConstants.VAC_VACATION_TYPE_APPROVED);
        searchVacDTO.setCivilId(Long.valueOf(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getCode().getKey()));        
        searchVacDTO.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        String vacFinStatusCode="0";
        searchVacDTO.setFinancialStatus(Byte.valueOf(vacFinStatusCode));       
    //        if (vacAddIntervalFrom != null)
    //            searchVacDTO.setRequestDateFrom(new java.sql.Date(vacAddIntervalFrom.getTime()));
    //        else
    //            searchVacDTO.setRequestDateFrom(null);
    //
    //        if (vacAddIntervalTo != null)
    //            searchVacDTO.setRequestDateTo(new java.sql.Date(vacAddIntervalTo.getTime()));
    //        else
    //            searchVacDTO.setRequestDateTo(null);

    }

    private void checkEmpHasUnsignedDeduction(Long empCivilID) {
        setHasUnsignedDeduction(false);        
        try {
            hasUnsignedDeduction = SalClientFactory.getSalEmpMonSalariesClient().checkEmpHasUnsignedDeductions(empCivilID);
            System.out.println("checkEmpHasUnsignedDeduction empCivilID = "+empCivilID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    private void checkMerDedChanged() {
        setMerDedChanged(false);
        ISalEmpCalcSalSearchDTO salEmpCalcSalSearchDTO = new SalEmpCalcSalSearchDTO();
        salEmpCalcSalSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        salEmpCalcSalSearchDTO.setCivilId(Long.valueOf(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getCode().getKey()));
        salEmpCalcSalSearchDTO.setRealCivilId(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getRealCivilId());
        //        String dateStr=year+"-"+String.format("%02d",Integer.parseInt(month)) +"-01";
        //        Date calcDate=Date.valueOf(dateStr);
        //(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getCalcDate().getTime());
        String dateStr = year + "-" + String.format("%02d", Integer.parseInt(month)) + "-01";
        System.out.println("checkMerDedChanged : dateStr = " + dateStr);
        calcDate = Date.valueOf(dateStr);
        salEmpCalcSalSearchDTO.setCalculationDate(calcDate);
        merDedChangeVal = 0L;
        try {
            merDedChangeVal = SalClientFactory.getSalEmpMonSalariesClient().checkMerDedChanged(salEmpCalcSalSearchDTO);
            System.out.println("checkMerDedChanged : merDedChangeVal = " + merDedChangeVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (merDedChangeVal > 0) {
            setMerDedChanged(true);
        }
    }

    private void checkDeductionsRatio() {
        invalidDedRatio = false;
        IBasicDTO dto;
        try {
            dto =
SalClientFactory.getSalElmguideMinClient().getValidByElmGuideAndMinCode(ISalConstants.ELEMENT_GUIDE_TYPE_DEDUCT_ROOT,
                                                                        CSCSecurityInfoHelper.getLoggedInMinistryCode());

            if (dto != null) {
                Long ratio = ((ISalElmguideMinDTO)dto).getMaxDeductRatio();
                if (totalMerits != null && totalDeductions != null) {
                    BigDecimal totalMeritsCalc =
                        totalMerits.multiply(new BigDecimal(ratio)).divide(BigDecimal.valueOf(100L));
                    if (totalDeductions.compareTo(totalMeritsCalc) == 1) {
                        invalidDedRatio = true;
                        SharedUtilBean sharedUtil = SharedUtilBean.getInstance();
                        String msg = sharedUtil.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "invalid_deduction_ratio");
                        invalidDedRatioMsg = msg.replace("#", ratio.toString());
                        System.out.println("Message : " + invalidDedRatioMsg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkEmpHasIntervals(ISalEmpMonSalariesDTO salEmpMonSalariesDTO) {
        hasIntervals = false;
        //Long empRealCivilID = salEmpMonSalariesDTO.getEmployeesDTO().getRealCivilId() ;
        Long civilId = Long.valueOf(salEmpMonSalariesDTO.getEmployeesDTO().getCode().getKey());
        try {
            Long salaryMonth = Long.valueOf(month);
            Long salaryYear = Long.valueOf(year);
            hasIntervals =
                    ((ISalEmpMonSalariesClient)getClient()).getEmpVacationsCountByCivilId(civilId, salaryMonth, salaryYear);
            System.out.println("checkEmpHasIntervals civilId = "+civilId+" , salaryMonth = "+salaryMonth+" , salaryYear = "+salaryYear+" , hasIntervals = "+hasIntervals);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkExistApprovedMangDiscount(ISalEmpMonSalariesDTO salEmpMonSalariesDTO) {
        existApprovedMangDiscount = false;
        //Long empRealCivilID = salEmpMonSalariesDTO.getEmployeesDTO().getRealCivilId() ;
        Long civilId = Long.valueOf(salEmpMonSalariesDTO.getEmployeesDTO().getCode().getKey());
        SalEmpMonSalariesEntityKey salEmpMonSalariesEntityKey =
            (SalEmpMonSalariesEntityKey)salEmpMonSalariesDTO.getCode();
        Long year = salEmpMonSalariesEntityKey.getYearCode();
        Long month = salEmpMonSalariesEntityKey.getSalaryMonth();
        int discounts_no = 0;
        try {
            discounts_no =
                    ((ISalEmpMonSalariesClient)getClient()).checkExistApprovedMangDiscount(civilId, month, year);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        if (discounts_no > 0)
            existApprovedMangDiscount = true;
    }

    private void checkValidBnkAccount(ISalEmpMonSalariesDTO salEmpMonSalariesDTO) {
        bnkAccountStatus = 1;
        List<IBasicDTO> bnkAccountsList;
        PersonBankAccountsDTO personBankAccountsDTO = null;
        Long empRealCivilID = salEmpMonSalariesDTO.getEmployeesDTO().getRealCivilId();
        if (salEmpMonSalariesDTO.getBankBranchesDTO() != null) {
            Long bnkCode =
                ((BanksEntityKey)salEmpMonSalariesDTO.getBankBranchesDTO().getBanksDTO().getCode()).getBankCode();
            Long bnkBranchCode = salEmpMonSalariesDTO.getBankBranchesDTO().getBnkbranchCode();
            String accNo = salEmpMonSalariesDTO.getAccountChekNo();
            System.out.println("checkValidBnkAccount bnkCode = "+bnkCode+" , bnkBranchCode = "+bnkBranchCode+" , accNo = "+accNo);
            //salEmpMonSalariesDTO.geta
            try {
                if (bnkCode == null || bnkBranchCode == null || accNo == null) {
                    bnkAccountStatus = 2;
                    return;
                }
                bnkAccountsList =
                        BnkClientFactory.getPersonBankAccountsClient().getValidPersonBankAccountsByCivilID_forSalReview(empRealCivilID);

                if (bnkAccountsList != null && bnkAccountsList.size() > 0) {
                    for (int i = 0; i < bnkAccountsList.size(); i++) {
                        personBankAccountsDTO = (PersonBankAccountsDTO)bnkAccountsList.get(i);
                        if (bnkCode.equals(personBankAccountsDTO.getBankCode()) &&
                            bnkBranchCode.equals(personBankAccountsDTO.getBnkBranchCode()) &&
                            accNo.equals(personBankAccountsDTO.getAccountNo())) {
                            bnkAccountStatus = 1;
                            return;
                        }
                    }
                    bnkAccountStatus = 2;
                    return;
                } else {
                    bnkAccountStatus = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            bnkAccountStatus = 2;
            return;
        }
    }

    public void calcuateValues() {
        System.out.println("************** calcuateValues *********************");
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

            /*--Set the settlement flag with the retrieved value from DB to display button
             *--corresponding to the paySlip record that has settlement in case of Merits;
             */
            dto.getElementGuidesDTO().getSalElementGuidesDTO().setBigValue(dto.getValue());
            dto.getElementGuidesDTO().getSalElementGuidesDTO().setIsSettler(dto.isIsSettler());
            if (dto.isIsSettler()) {
                setSettler(true);
            }
            if (dto.getValue() != null)
                totalMer = totalMer.add(dto.getValue());
            if (dto.getElementGuidesDTO().getSalElementGuidesDTO() != null) {
                elementGuidesDTO = dto.getElementGuidesDTO().getSalElementGuidesDTO();
                if (merMap.get(elementGuidesDTO.getCode()) != null) {
                    elementGuidesDTO = merMap.get(elementGuidesDTO.getCode());
                    if (dto.getValue() != null) {
                        elementGuidesDTO.setBigValue(elementGuidesDTO.getBigValue().add(dto.getValue()));
                    }
                } else {
                    merMap.put(elementGuidesDTO.getCode(), elementGuidesDTO);
                }
            }

        }
        meritsList = new ArrayList(merMap.values());
        for (Object dto1 : (ArrayList)deductionsList) {
            ISalEmpPayslipsDTO dto = (ISalEmpPayslipsDTO)dto1;
            if (dto.getElementGuidesDTO().getSalElementGuidesDTO() == null) {
                dto.getElementGuidesDTO().setSalElementGuidesDTO(dto.getElementGuidesDTO());
            }

            /*--Set the settlement flag with the retrieved value from DB to display button
             *--corresponding to the paySlip record that has settlement in case of Deductions;
             */
            // dto.getElementGuidesDTO().getSalElementGuidesDTO().setBigValue(dto.getValue());
            dto.getElementGuidesDTO().getSalElementGuidesDTO().setIsSettler(dto.isIsSettler());
            if (dto.isIsSettler()) {
                setSettler(true);
            }
            //---------------------------------------------------------------------------------
            if (dto.getValue() != null)
                totalDed = totalDed.add(dto.getValue());
            /*  if (dto.getElementGuidesDTO().getSalElementGuidesDTO() != null) {
                elementGuidesDTO = dto.getElementGuidesDTO().getSalElementGuidesDTO();
                if (dedMap.get(elementGuidesDTO.getCode()) != null) {
                    elementGuidesDTO = dedMap.get(dto.getElementGuidesDTO().getSalElementGuidesDTO().getCode());
                    if (dto.getValue() != null) {
                        elementGuidesDTO.setBigValue(elementGuidesDTO.getBigValue().add(dto.getValue()));
                    }
                } else {
                    dedMap.put(dto.getElementGuidesDTO().getSalElementGuidesDTO().getCode(),
                               dto.getElementGuidesDTO().getSalElementGuidesDTO());
                }
            }*/
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
        System.out.println("actualSalary = "+actualSalary+" , totalMer = "+totalMer
                           +" , totalDed = "+totalDed+" , totalMerits = "+totalMerits+" , totalDeductions = "+totalDeductions);
        merMap.clear();
        dedMap.clear();
        merMap = null;
        dedMap = null;
    }

    public void initEmpDTO() {
        empDTO = EmpDTOFactory.createEmployeesDTO();
        empDTO.setBgtProgramsDTO(BgtDTOFactory.createBgtProgramsDTO());
        empDTO.setBgtTypesDTO(BgtDTOFactory.createBgtTypesDTO());
    }


    public void initMonSalDTO() {
        monSalDTO = SalDTOFactory.createSalEmpMonSalariesDTO();
        monSalDTO.setPaymentStatusDTO(SalDTOFactory.createSalPaymentStatusDTO());
    }

    public void recalculateMonSal() {
        ISalEmpMonSalariesEntityKey code =
            SalEntityKeyFactory.createSalEmpMonSalariesEntityKey(getRealCivilId(), Long.valueOf(year),
                                                                 Long.valueOf(month), null);
        try {
            monSalDTO = (ISalEmpMonSalariesDTO)SalClientFactory.getSalEmpMonSalariesClient().getByCivilMonYear(code);
        } catch (Exception e) {
            initMonSalDTO();
        }
        if (monSalDTO == null) {
            initMonSalDTO();
        }
        meritsList = new ArrayList(0);
        deductionsList = new ArrayList(0);

    }

    public Long getNotReviewed() {
        return IScpConstants.MON_SAL_STATUS_NOT_REVIEWED;
    }

    public Long getReviewed() {
        return IScpConstants.MON_SAL_STATUS_REVIEWED;
    }

    public Long getAllStatus() {
        return IScpConstants.MON_SAL_STATUS_ALL;
    }

    public Long getKwaity() {
        return IScpConstants.NATIONALITY_KWAITY;
    }

    public Long getNotKwaity() {
        return IScpConstants.NATIONALITY_NOT_KWAITY;
    }

    public Long getAllNationalityStatus() {
        return IScpConstants.NATIONALITY_ALL;
    }

    public void loadWrkLevels() {
        if (wrkLevels == null) {
            try {
                IWrkLevelsClient client = OrgClientFactory.getWrkLevelsClient();
                IMinistriesEntityKey key =
                    OrgEntityKeyFactory.createMinistriesEntityKey(CSCSecurityInfoHelper.getLoggedInMinistryCode());
                wrkLevels = client.getCodeNameByMinistry(key);
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
        }
    }

    public void loadWrkCenters() {
        try {
            wrkCenters = OrgClientFactory.getWorkCentersClient().getAll();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }

    public void loadBgtListPrograms() {
        if (bgtListPrograms == null) {
            try {
                IMinistriesClient client = OrgClientFactory.getMinistriesClient();
                bgtListPrograms = client.getMinRelatedCurrentPrograms(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void resetWorkCenterFlag(ActionEvent ae) {
        if(selWrkCenter==null || selWrkCenter.equals(getVirtualConstValue()))
        relatedWorkCentersFlag=false;
    }
    public void getWorkCentersByOrganizationLevel(ActionEvent ae) {
        ae = null; //unused
        wrkCenters = null;
        selWrkCenter = null;
        relatedWorkCentersFlag=false;
        if (getSelWrkLevel() != null && !getSelWrkLevel().equals(getVirtualConstValue())) {
            try {
                IWorkCentersClient client = OrgClientFactory.getWorkCentersClient();
                IWorkCentersSearchCriteriaDTO searchDTO = OrgDTOFactory.createWorkCentersSearchCriteriaDTO();
                searchDTO.setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
                searchDTO.setWorkLevelCode(Long.valueOf(selWrkLevel));
                wrkCenters = client.search(searchDTO);
            } catch (Exception e) {
                wrkCenters = new ArrayList<IBasicDTO>(0);
                e.printStackTrace();
            }
        }
    }

    //---------------------Setters and getters----------------------

    public Integer getListSize() {
        if (monSalList == null) {
            return 0;
        } else {
            return monSalList.size();
        }
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


    public void setMonths(List months) {
        this.months = months;
    }

    public List getMonths() {

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

        return years;
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


    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setMonSalList(List<IBasicDTO> monSalList) {
        this.monSalList = monSalList;
    }

    public List<IBasicDTO> getMonSalList() {
        return monSalList;
    }

    public void setSelStatus(Long selStatus) {
        this.selStatus = selStatus;
    }

    public Long getSelStatus() {
        return selStatus;
    }

    public void setBgtListPrograms(List<IBasicDTO> bgtListPrograms) {
        this.bgtListPrograms = bgtListPrograms;
    }

    public List<IBasicDTO> getBgtListPrograms() {

        return bgtListPrograms;
    }

    public void setWrkCenters(List<IBasicDTO> wrkCenters) {
        this.wrkCenters = wrkCenters;
    }

    public List<IBasicDTO> getWrkCenters() {
        return wrkCenters;
    }

    public void setWrkLevels(List<IBasicDTO> wrkLevels) {
        this.wrkLevels = wrkLevels;
    }

    public List<IBasicDTO> getWrkLevels() {

        return wrkLevels;
    }

    public void setSelWrkCenter(String selWrkCenter) {
        this.selWrkCenter = selWrkCenter;
    }

    public String getSelWrkCenter() {
        return selWrkCenter;
    }

    public void setSelWrkLevel(String selWrkLevel) {
        this.selWrkLevel = selWrkLevel;
    }

    public String getSelWrkLevel() {
        return selWrkLevel;
    }

    public void setSelbgtProgram(String selbgtProgram) {
        this.selbgtProgram = selbgtProgram;
    }

    public String getSelbgtProgram() {
        return selbgtProgram;
    }


    public void setInvalidDedRatio(boolean invalidDedRatio) {
        this.invalidDedRatio = invalidDedRatio;
    }

    public boolean isInvalidDedRatio() {
        return invalidDedRatio;
    }

    public void setInvalidDedRatioMsg(String invalidDedRatioMsg) {
        this.invalidDedRatioMsg = invalidDedRatioMsg;
    }

    public String getInvalidDedRatioMsg() {
        return invalidDedRatioMsg;
    }

    public void setDisabledAutoReview(boolean disabledAutoReview) {
        this.disabledAutoReview = disabledAutoReview;
    }

    public boolean isDisabledAutoReview() {
        return disabledAutoReview;
    }

    public String getAutoReviewDivTitle() {
        String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "salAutoReviewTitle");
        //message = message.replace("#", totalAutoReviewCount.toString());
        return message;
    }


    public void showAutoReviewAlertDiv() {

        try {
            List empList = getMyTableData();
            if (empList != null && !empList.isEmpty()) {
                totalAutoReviewCount = new Long(empList.size());
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void autoReviewEmployeesSal() {
        System.out.println("************** autoReviewEmployeesSal *********************");
        try {
            setErrors(false);
            List empList = getMyTableData();
            int empNo = empList.size();
            List<String> errDescList = new ArrayList<String>();
            errDescList.add(getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "Emp_Has_Sett_mon_Msg"));
            errDescList.add(getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "Emp_Has_vac_peroids_Msg"));
            //errDescList.add(getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "notValidActualSalary"));
            if (empList != null && !empList.isEmpty()) {
                Long totalUnSettlerReviewed =
                    ((ISalEmpMonSalariesClient)getClient()).autoReviewEmployeesSal(salEmpCalcSalSearchDTO,empList, Long.valueOf(year),
                                                                                   Long.valueOf(month), errDescList);
                loadEmpListOnly();//loadEmpList();
                String message =
                    getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "salAutoReviewSuccessMessage");
                message = message.replace("#", totalUnSettlerReviewed.toString());
                message = message.replace("*", empNo + "");
                getSharedUtil().setSuccessMsgValue(message);
                if (totalUnSettlerReviewed != null && totalUnSettlerReviewed.intValue() != empNo) {
                    setErrors(true);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setExpiredChildRaise(boolean expiredChildRaise) {
        this.expiredChildRaise = expiredChildRaise;
    }

    public boolean isExpiredChildRaise() {
        return expiredChildRaise;
    }


    /**
     * This method is responsible for displaying Div of All Decisions
     * which the selected employee has as (Merits or Deductions);
     */
    public void showAllSettlementsDicisionsDiv() {
        ISalEmpMonSalariesDTO selectedDTO = (ISalEmpMonSalariesDTO)getSelectedPageDTO();
        Long realCivilId = selectedDTO.getEmployeesDTO().getRealCivilId();
        IEmployeesDTO empDTO = selectedDTO.getEmployeesDTO();
        getEmpSettEnquiryBeanHelper().setApprovedStatusOnly(true);
        getEmpSettEnquiryBeanHelper().setYear(year);
        getEmpSettEnquiryBeanHelper().setMonth(month);
        getEmpSettEnquiryBeanHelper().setCivilId(realCivilId.toString());
        getEmpSettEnquiryBeanHelper().setEmployeesDTO(empDTO);
        getEmpSettEnquiryBeanHelper().setSettlemenetType(settlemenetType);
        getEmpSettEnquiryBeanHelper().setSettWithDecsType(settWithDecsType);
        getEmpSettEnquiryBeanHelper().setRetroactive_SettType(retroactive_SettType);
        getEmpSettEnquiryBeanHelper().viewDecisionsAndSettlementsDetails();

    }

    public static ReviewEmpMonthSalBean getInstance() {
        return (ReviewEmpMonthSalBean)JSFHelper.getValue(BEAN_NAME);
    }

    private Object fillSaveStateParams() {
        Map params = new HashMap();
        params.put("pageDTO", getPageDTO());
        params.put("selectedPageDTO", getSelectedPageDTO());
        params.put("selectedDTOS", getSelectedDTOS());
        params.put("civilId", getCivilId());
        params.put("realCivilId", realCivilId);
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

        params.put("monSalList", monSalList);

        params.put("notes", notes);

        params.put("selStatus", selStatus);
        params.put("selWrkCenter", selWrkCenter);
        params.put("selWrkLevel", selWrkLevel);
        params.put("selbgtProgram", selbgtProgram);


        params.put("bgtListPrograms", bgtListPrograms);
        params.put("wrkCenters", wrkCenters);
        params.put("wrkLevels", wrkLevels);
        params.put("invalidDedRatio", invalidDedRatio);
        params.put("year", year);
        params.put("month", month);
        params.put("disabledAutoReview", disabledAutoReview);
        params.put("meritsList", meritsList);
        params.put("deductionsList", deductionsList);
        params.put("salEmpPayslipsDTO", salEmpPayslipsDTO);
        params.put("totalDeductions", totalDeductions);
        params.put("totalMerits", totalMerits);
        params.put("actualSalary", actualSalary);
        params.put("empSettEnquiryBeanHelper", empSettEnquiryBeanHelper);
        //params.put("meritsDataTable", meritsDataTable);
        //params.put("deductionsDataTable", deductionsDataTable);
        params.put("settler", settler);
        params.put("hasIntervals", hasIntervals);
        params.put("totalSalaryLessThanHalfEmpMerits", totalSalaryLessThanHalfEmpMerits);        
        params.put("settlemenetType", settlemenetType);
        params.put("settWithDecsType", settWithDecsType);
        params.put("retroactive_SettType", retroactive_SettType);
        params.put("calcExceptionsList", calcExceptionsList);
        params.put("errors", errors);
        params.put("degreeFirstValue", degreeFirstValue);
        params.put("raisesTotalValue", raisesTotalValue);

        params.put("showPaySlipPanel", showPaySlipPanel);
        params.put("expiredChildRaise", expiredChildRaise);
        params.put("expiredHiredChildRaise", expiredHiredChildRaise);
        params.put("marriedFemaleChild", marriedFemaleChild);
        params.put("empDead", empDead);
        params.put("empChildrenDead", empChildrenDead);
        params.put("hasUnsignedDeduction", hasUnsignedDeduction);
        params.put("bnkAccountStatus", bnkAccountStatus);
        params.put("existApprovedMangDiscount", existApprovedMangDiscount);

        params.put("meritsListCopy", meritsListCopy);
        params.put("merDedChanged", merDedChanged);
        params.put("merDedChangeVal", merDedChangeVal);
        params.put("selNationality", selNationality);
        params.put("calcDate", calcDate);
        params.put("hasBasicData", hasBasicData);
        params.put("relatedWorkCentersFlag", relatedWorkCentersFlag);
        params.put("hasUnsignedSett", hasUnsignedSett);
        params.put("hasMissionStarted", hasMissionStarted);
        params.put("hasMissionStoped", hasMissionStoped);
        params.put("hasMissionFinished", hasMissionFinished);
        params.put("hasSickVac", hasSickVac);
        

        return params;
    };

    public void restoreSaveStateParams(Object obj) {
        if (obj != null) {
            try {
                Map params = (Map)obj;
                realCivilId = (Long)params.get("realCivilId");
                setMonSalList((List)params.get("monSalList"));
                setMyTableData((List)params.get("monSalList"));

                setSelStatus((Long)params.get("selStatus"));
                setSelbgtProgram((String)params.get("selbgtProgram"));
                setSelWrkCenter((String)params.get("selWrkCenter"));
                setSelWrkLevel((String)params.get("selWrkLevel"));
                bgtListPrograms = ((List)params.get("bgtListPrograms"));
                wrkCenters = ((List)params.get("wrkCenters"));
                wrkLevels = ((List)params.get("wrkLevels"));
                invalidDedRatio = ((Boolean)params.get("invalidDedRatio"));


                setYear((String)params.get("year"));
                setMonth((String)params.get("month"));

                empSettEnquiryBeanHelper = ((EmpSettEnquiryBeanHelper)params.get("empSettEnquiryBeanHelper"));
                //                setMeritsDataTable((HtmlDataTable)params.get("meritsDataTable") );
                //                setDeductionsDataTable((HtmlDataTable)params.get("deductionsDataTable") );

                meritsListCopy = (List<IBasicDTO>)params.get("meritsListCopy");
                merDedChanged = ((Boolean)params.get("merDedChanged"));
                merDedChangeVal = ((Long)params.get("merDedChangeVal"));
                setSelNationality((Long)params.get("selNationality"));
                setCalcDate((Date)params.get("calcDate"));
                hasBasicData = (Boolean)params.get("hasBasicData");
                relatedWorkCentersFlag = ((Boolean)params.get("relatedWorkCentersFlag"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void restoreSaveStateParams2(Object obj) {
        System.out.println("************** restoreSaveStateParams2 *********************");
        if (obj != null) {
            try {
                Map params = (Map)obj;
                setPageDTO((IBasicDTO)params.get("pageDTO"));
                setSelectedPageDTO((IBasicDTO)params.get("selectedPageDTO"));
                setSelectedDTOS((List)params.get("selectedDTOS"));
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
                notes = ((List)params.get("notes"));
                setDisabledAutoReview((Boolean)params.get("disabledAutoReview"));
                setDeductionsList((List)params.get("deductionsList"));
                setMeritsList((List)params.get("meritsList"));
                setSalEmpPayslipsDTO((ISalEmpPayslipsDTO)params.get("salEmpPayslipsDTO"));
                setTotalDeductions((BigDecimal)params.get("totalDeductions"));
                setTotalMerits((BigDecimal)params.get("totalMerits"));
                setActualSalary((BigDecimal)params.get("actualSalary"));
                settler = ((Boolean)params.get("settler"));
                hasIntervals = ((Boolean)params.get("hasIntervals"));
                totalSalaryLessThanHalfEmpMerits = ((Boolean)params.get("totalSalaryLessThanHalfEmpMerits"));               
                expiredChildRaise = ((Boolean)params.get("expiredChildRaise"));
                expiredHiredChildRaise = ((Boolean)params.get("expiredHiredChildRaise"));
                marriedFemaleChild = ((Boolean)params.get("marriedFemaleChild"));
                empDead= ((Boolean)params.get("empDead"));
                empChildrenDead= ((Boolean)params.get("empChildrenDead"));
                hasUnsignedDeduction= ((Boolean)params.get("hasUnsignedDeduction"));
                bnkAccountStatus = ((Byte)params.get("bnkAccountStatus"));
                existApprovedMangDiscount = ((Boolean)params.get("existApprovedMangDiscount"));
                settlemenetType = ((String)params.get("settlemenetType"));
                settWithDecsType = ((String)params.get("settWithDecsType"));
                retroactive_SettType = ((String)params.get("retroactive_SettType"));
                calcExceptionsList = ((List)params.get("calcExceptionsList"));
                errors = ((Boolean)params.get("errors"));
                degreeFirstValue = ((Float)params.get("degreeFirstValue"));
                raisesTotalValue = ((Float)params.get("raisesTotalValue"));
                showPaySlipPanel = ((Integer)params.get("showPaySlipPanel"));
                merDedChanged = ((Boolean)params.get("merDedChanged"));
                merDedChangeVal = ((Long)params.get("merDedChangeVal"));
                setSelNationality((Long)params.get("selNationality"));
                setCalcDate((Date)params.get("calcDate"));
                hasBasicData = (Boolean)params.get("hasBasicData");
                relatedWorkCentersFlag = ((Boolean)params.get("relatedWorkCentersFlag"));
                hasUnsignedSett = ((Boolean)params.get("hasUnsignedSett"));
                hasMissionStarted = ((Boolean)params.get("hasMissionStarted"));
                hasMissionStoped = ((Boolean)params.get("hasMissionStoped"));
                hasMissionFinished = ((Boolean)params.get("hasMissionFinished"));
                hasSickVac=((Boolean)params.get("hasSickVac"));

                //                setMeritsDataTable((HtmlDataTable)params.get("meritsDataTable") );
                //                setDeductionsDataTable((HtmlDataTable)params.get("deductionsDataTable") );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void restoreSaveStateParams3(Object obj) {
        System.out.println("************** restoreSaveStateParams3 *********************");
        if (obj != null) {
            try {
                Map params = (Map)obj;
                setPageDTO((IBasicDTO)params.get("pageDTO"));
                setSelectedPageDTO((IBasicDTO)params.get("selectedPageDTO"));
                setSelectedDTOS((List)params.get("selectedDTOS"));
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
                notes = ((List)params.get("notes"));
                setDisabledAutoReview((Boolean)params.get("disabledAutoReview"));
                setDeductionsList((List)params.get("deductionsList"));
                setMeritsList((List)params.get("meritsList"));
                setSalEmpPayslipsDTO((ISalEmpPayslipsDTO)params.get("salEmpPayslipsDTO"));
                setTotalDeductions((BigDecimal)params.get("totalDeductions"));
                setTotalMerits((BigDecimal)params.get("totalMerits"));
                setActualSalary((BigDecimal)params.get("actualSalary"));
                settler = ((Boolean)params.get("settler"));
                hasIntervals = ((Boolean)params.get("hasIntervals"));
                totalSalaryLessThanHalfEmpMerits = ((Boolean)params.get("totalSalaryLessThanHalfEmpMerits")); 
                expiredChildRaise = ((Boolean)params.get("expiredChildRaise"));
                expiredHiredChildRaise = ((Boolean)params.get("expiredHiredChildRaise"));
                marriedFemaleChild = ((Boolean)params.get("marriedFemaleChild"));
                empDead= ((Boolean)params.get("empDead"));
                empChildrenDead= ((Boolean)params.get("empChildrenDead"));
                hasUnsignedDeduction= ((Boolean)params.get("hasUnsignedDeduction"));
                bnkAccountStatus = ((Byte)params.get("bnkAccountStatus"));
                existApprovedMangDiscount = ((Boolean)params.get("existApprovedMangDiscount"));
                settlemenetType = ((String)params.get("settlemenetType"));
                settWithDecsType = ((String)params.get("settWithDecsType"));
                retroactive_SettType = ((String)params.get("retroactive_SettType"));
                calcExceptionsList = ((List)params.get("calcExceptionsList"));
                errors = ((Boolean)params.get("errors"));
                degreeFirstValue = ((Float)params.get("degreeFirstValue"));
                raisesTotalValue = ((Float)params.get("raisesTotalValue"));
                showPaySlipPanel = ((Integer)params.get("showPaySlipPanel"));

                realCivilId = (Long)params.get("realCivilId");
                setMonSalList((List)params.get("monSalList"));
                setMyTableData((List)params.get("monSalList"));
                setSelStatus((Long)params.get("selStatus"));
                //bgtListPrograms = ((List)params.get("bgtListPrograms") );
                wrkCenters = ((List)params.get("wrkCenters"));
                //wrkLevels = ((List)params.get("wrkLevels") );
                setSelbgtProgram((String)params.get("selbgtProgram"));
                setSelWrkCenter((String)params.get("selWrkCenter"));
                setSelWrkLevel((String)params.get("selWrkLevel"));

                invalidDedRatio = ((Boolean)params.get("invalidDedRatio"));
                setYear((String)params.get("year"));
                setMonth((String)params.get("month"));
                empSettEnquiryBeanHelper = ((EmpSettEnquiryBeanHelper)params.get("empSettEnquiryBeanHelper"));
                meritsListCopy = (List<IBasicDTO>)params.get("meritsListCopy");
                merDedChanged = ((Boolean)params.get("merDedChanged"));
                merDedChangeVal = ((Long)params.get("merDedChangeVal"));
                setSelNationality((Long)params.get("selNationality"));
                setCalcDate((Date)params.get("calcDate"));
                hasBasicData = (Boolean)params.get("hasBasicData");
                relatedWorkCentersFlag = ((Boolean)params.get("relatedWorkCentersFlag"));
                hasUnsignedSett = ((Boolean)params.get("hasUnsignedSett"));
                hasMissionStarted = ((Boolean)params.get("hasMissionStarted"));
                hasMissionStoped = ((Boolean)params.get("hasMissionStoped"));
                hasMissionFinished = ((Boolean)params.get("hasMissionFinished"));
                hasSickVac=((Boolean)params.get("hasSickVac"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public String showEmpIntervals() {
        ListBean listBean = ListBean.getInstance();
        Long realCivilId = ((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getRealCivilId();

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

    /**
     * This method is responsible for displaying Div of Notes
     * which the selected employee has as (deds exceess 50% , children raise ends);
     */
    public void showNotesDiv() {
        notes = new ArrayList();
        if (invalidDedRatio) {
            notes.add(invalidDedRatioMsg);
        }
        if (expiredChildRaise) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "expired_exceed_age_child_msg");
            notes.add(message);
        }
        if (expiredHiredChildRaise) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "expired_hired_child_msg");
            notes.add(message);
        }
        if (marriedFemaleChild) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "marriedFemaleChild_msg");
            notes.add(message);
        }
        if (empDead) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "empDead_msg");
            notes.add(message);
        }
        if (empChildrenDead) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "empChildrenDead_msg");
            notes.add(message);
        }

        if (bnkAccountStatus.equals(Byte.parseByte("0"))) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "invalidBnkAccount_msg");
            notes.add(message);
        } else if (bnkAccountStatus.equals(Byte.parseByte("2"))) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "invalidBnkAccount_msg_2");
            notes.add(message);
        }

        if ((actualSalary != null && actualSalary.compareTo(limitSalary) > 0)) {
            String message =
                getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "actualSalary_exceeds_limitSalary_msg");
            notes.add(message);
        }

        if (existApprovedMangDiscount) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "existApprovedMangDiscount_msg");
            notes.add(message);
        }
        if (merDedChanged) {
            String message =
                getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "merDedChanged_msg").replaceFirst("#",
                                                                                                           merDedChangeVal.toString());
            notes.add(message);
        }
        if (hasUnsignedSett) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "there_is_unsigned_sett");
            notes.add(message);
        }
        if (hasMissionStarted) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "MissionStarted");
            notes.add(message);
        }
        if (hasMissionStoped) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "MissionStoped");
            notes.add(message);
        }
        if (hasMissionFinished) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "MissionFinished");
            notes.add(message);
        }
        if (hasSickVac) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "there_is_sick_vac");
            notes.add(message);
        }
        if (hasUnsignedDeduction) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "there_is_unsigned_deduction");
            notes.add(message);
        }
        if (totalSalaryLessThanHalfEmpMerits) {           
            String message =getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "cannotreviewempsal");
            notes.add(message);
        }
    }

    public String queryDeduction() {
        ISalEmpPayslipsDTO dto = (ISalEmpPayslipsDTO)deductionsDataTable.getRowData();
        CSCDeductionEnquiryDTO searchDTO = new CSCDeductionEnquiryDTO();
        searchDTO.setId(dto.getElmSerial());
        DeductionEnquiryBean bean = DeductionEnquiryBean.getInstance();

        String backMethod = "reviewEmpMonthSalBean.restoreSaveStateParams3";

        bean.init(fillSaveStateParams(), nav_case, backMethod);
        bean.searchDeductions(searchDTO);

        return "deductionEnquiry";
    }    
    public void setEmpSettEnquiryBeanHelper(EmpSettEnquiryBeanHelper empSettEnquiryBeanHelper) {
        this.empSettEnquiryBeanHelper = empSettEnquiryBeanHelper;
    }

    public EmpSettEnquiryBeanHelper getEmpSettEnquiryBeanHelper() {
        return empSettEnquiryBeanHelper;
    }

    public void setMeritsDataTable(HtmlDataTable meritsDataTable) {
        this.meritsDataTable = meritsDataTable;
    }

    public HtmlDataTable getMeritsDataTable() {
        return meritsDataTable;
    }

    public void setDeductionsDataTable(HtmlDataTable deductionsDataTable) {
        this.deductionsDataTable = deductionsDataTable;
    }

    public HtmlDataTable getDeductionsDataTable() {
        return deductionsDataTable;
    }

    public void setMeritsListCopy(List<IBasicDTO> meritsListCopy) {
        this.meritsListCopy = meritsListCopy;
    }

    public List<IBasicDTO> getMeritsListCopy() {
        return meritsListCopy;
    }

    public void setDeductionsListCopy(List<IBasicDTO> deductionsListCopy) {
        this.deductionsListCopy = deductionsListCopy;
    }

    public List<IBasicDTO> getDeductionsListCopy() {
        return deductionsListCopy;
    }

    public void setSettler(boolean settler) {
        this.settler = settler;
    }

    public boolean isSettler() {
        return settler;
    }

    public boolean isHasNote() {
        hasNote =
                invalidDedRatio || expiredChildRaise || expiredHiredChildRaise || marriedFemaleChild || empDead || empChildrenDead || hasUnsignedDeduction || bnkAccountStatus.equals(Byte.parseByte("0")) || bnkAccountStatus.equals(Byte.parseByte("2")) ||
                (actualSalary != null && actualSalary.compareTo(limitSalary) > 0) || existApprovedMangDiscount ||
                merDedChanged || hasUnsignedSett || totalSalaryLessThanHalfEmpMerits || hasMissionStarted || hasMissionStoped || hasMissionFinished || hasSickVac;
        return hasNote;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void initiateBeanOnce() {

        //setEmpListOfValues(new EmployeeListOfValues());
        //        if (month == null && year == null)
        //            setCurrentMonthYear();
        //        monSalList = new ArrayList<IBasicDTO>(0);
        //        setMyTableData(monSalList);
        //        initEmpDTO();
        //        setSingleSelection(true);
        //        setSelectedPageDTO(SalDTOFactory.createSalEmpMonSalariesDTO());
        //        this.setSelectedRadio("");
        //
        //
        //        meritsList = new ArrayList<IBasicDTO>();
        //        meritsList = new ArrayList<IBasicDTO>();
        //        deductionsList = new ArrayList<IBasicDTO>();
        //        meritsListCopy = new ArrayList<IBasicDTO>();
        //        deductionsListCopy = new ArrayList<IBasicDTO>();
        //
        loadMonths();
        loadYears();
        loadBgtListPrograms();
        loadWrkLevels();
    }

    public void setSettlemenetType(String settlemenetType) {
        this.settlemenetType = settlemenetType;
    }

    public String getSettlemenetType() {
        return settlemenetType;
    }


    public void setRetroactive_SettType(String retroactive_SettType) {
        this.retroactive_SettType = retroactive_SettType;
    }

    public String getRetroactive_SettType() {
        return retroactive_SettType;
    }

    public void setSettWithDecsType(String settWithDecsType) {
        this.settWithDecsType = settWithDecsType;
    }

    public String getSettWithDecsType() {
        return settWithDecsType;
    }

    public void showCalcErr(ActionEvent ae) {
        ae = null;
        calcExceptionsList = new ArrayList();
        SalCalcExceptionsClientImpl salCalcExceptionsClient =
            (SalCalcExceptionsClientImpl)SalClientFactory.getSalCalcExceptionsClient();

        ISalEmpMonSalariesDTO selectedSalEmpMonSalariesDTO = (ISalEmpMonSalariesDTO)this.getMyDataTable().getRowData();
        //SalEmpMonSalariesDTO selectedSalEmpMonSalariesDTO = (SalEmpMonSalariesDTO)selected.getSalEmpMonSalariesDTO();
        SalEmpMonSalariesEntityKey salEmpMonSalariesEntityKey = null;

        if (selectedSalEmpMonSalariesDTO != null)
            salEmpMonSalariesEntityKey = (SalEmpMonSalariesEntityKey)selectedSalEmpMonSalariesDTO.getCode();

        if (salEmpMonSalariesEntityKey != null) {
            Long monthCode = salEmpMonSalariesEntityKey.getSalaryMonth(); //searchDTO.getMonthCode();
            Long yearCode = salEmpMonSalariesEntityKey.getYearCode(); //searchDTO.getYearCode();
            Long civilId1 =
                salEmpMonSalariesEntityKey.getCivilId(); //new Long("306090300366"); //searchDTO.getCivilId();
            Long serial = salEmpMonSalariesEntityKey.getSerial();
            //            System.out.println(">>>>  monthCode = " + monthCode);
            //            System.out.println(">>>>  civilId1  = " + civilId1);
            //            System.out.println(">>>>  yearCode  = " + yearCode);
            //            System.out.println(">>>>  serial  = " + serial);
            try {
                calcExceptionsList =
                        salCalcExceptionsClient.getEmpYearMonthAutoReviewExceptions(civilId1, serial, yearCode,
                                                                                    monthCode);
            } catch (Exception de) {

                de.printStackTrace();
            }
        } else {
            try {
                //calcExceptionsList = salCalcExceptionsClient.getEmpYearMonthSalCalcExceptions(new Long("306090300366"), new Long("1") , new Long("2014"), new Long("7"));
                calcExceptionsList = new ArrayList();
            } catch (Exception de) {
                de.printStackTrace();
            }

        }
        //setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv2);"); //,'lookupAddDivclose');");
    }

    public void setCalcExceptionsListSize(int calcExceptionsListSize) {
        this.calcExceptionsListSize = calcExceptionsListSize;
    }

    public int getCalcExceptionsListSize() {
        return calcExceptionsList.size();
    }


    public void setErrors(Boolean errors) {
        this.errors = errors;
    }

    public Boolean getErrors() {
        return errors;
    }

    public void setCalcExceptionsList(List<String> calcExceptionsList) {
        this.calcExceptionsList = calcExceptionsList;
    }

    public List<String> getCalcExceptionsList() {
        return calcExceptionsList;
    }

    public void setBasicSalaryCode(Long basicSalaryCode) {
        this.basicSalaryCode = basicSalaryCode;
    }

    public Long getBasicSalaryCode() {
        return basicSalaryCode;
    }

    public void setDegreeFirstValue(Float degreeFirstValue) {
        this.degreeFirstValue = degreeFirstValue;
    }

    public Float getDegreeFirstValue() {
        return degreeFirstValue;
    }

    public void setRaisesTotalValue(Float raisesTotalValue) {
        this.raisesTotalValue = raisesTotalValue;
    }

    public Float getRaisesTotalValue() {
        return raisesTotalValue;
    }

    public void setBnkAccountStatus(Byte bnkAccountStatus) {
        this.bnkAccountStatus = bnkAccountStatus;
    }

    public Byte getBnkAccountStatus() {
        return bnkAccountStatus;
    }

    public void setExistApprovedMangDiscount(boolean existApprovedMangDiscount) {
        this.existApprovedMangDiscount = existApprovedMangDiscount;
    }

    public boolean isExistApprovedMangDiscount() {
        return existApprovedMangDiscount;
    }

    public void setHasIntervals(boolean hasIntervals) {
        this.hasIntervals = hasIntervals;
    }

    public boolean isHasIntervals() {
        return hasIntervals;
    }

    public void setEmpHiredInMin(boolean empHiredInMin) {
        this.empHiredInMin = empHiredInMin;
    }

    public boolean isEmpHiredInMin() {
        return empHiredInMin;
    }

    public void setMerDedChanged(boolean merDedChanged) {
        this.merDedChanged = merDedChanged;
    }

    public boolean isMerDedChanged() {
        return merDedChanged;
    }

    public void setMerDedChangeVal(Long merDedChangeVal) {
        this.merDedChangeVal = merDedChangeVal;
    }

    public Long getMerDedChangeVal() {
        return merDedChangeVal;
    }

    public void setSelNationality(Long selNationality) {
        this.selNationality = selNationality;
    }

    public Long getSelNationality() {
        return selNationality;
    }

    public void setCalcDate(Date calcDate) {
        this.calcDate = calcDate;
    }

    public Date getCalcDate() {
        return calcDate;
    }

  

    public String viewEmpDetails() {
        try {

            ISalEmpMonSalariesDTO selectedDTO = (ISalEmpMonSalariesDTO)getSelectedPageDTO();

            String civil = selectedDTO.getEmployeesDTO().getCode().getKey();
            Long realCivilId = selectedDTO.getEmployeesDTO().getRealCivilId();
           
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
            govEmpMaintainBean.setBundleName("com.beshara.csc.hr.scp.presentation.resources.scp");
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

    public void setHasBasicData(boolean hasBasicData) {
        this.hasBasicData = hasBasicData;
    }

    public boolean isHasBasicData() {
        return hasBasicData;
    }

    /*public void setDisableReveiwBtn(boolean disableReveiwBtn) {
        this.disableReveiwBtn = disableReveiwBtn;
}

    public boolean isDisableReveiwBtn() {
        ISalEmpMonSalariesDTO selectedSalEmpMonSalariesDTO = (ISalEmpMonSalariesDTO)getSelectedPageDTO();
        Long currPayStatusCode = null;
        if(selectedSalEmpMonSalariesDTO != null && selectedSalEmpMonSalariesDTO.getPaymentStatusDTO() != null )            
            currPayStatusCode = selectedSalEmpMonSalariesDTO.getPaymentStatusDTO().getPaystatusCode();

        if(currPayStatusCode == null || (!currPayStatusCode.equals(IScpConstants.EMP_MON_SAL_CALC_NOT_REVIEWED)
            && !currPayStatusCode.equals(IScpConstants.EMP_MON_SAL_CALC_DURATIONS_NOT_REVIEWED) ) 
           ){
             disableReveiwBtn = true;
        }
        else{
             disableReveiwBtn = false;
        }
        return disableReveiwBtn;
    }

    @Override
    public void changePageIndex(ActionEvent event) {
        super.changePageIndex(event);
        reSetData2(null);
        clearData();
        setFilterPnlCollapsed(true);        
    }
    
    public void showHideFilterPnl() {
        if (filterPnlCollapsed) {
            filterPnlCollapsed = false;
        } else {
            filterPnlCollapsed = true;
        }
    }

    public void setFilterPnlCollapsed(boolean filterPnlCollapsed) {
        this.filterPnlCollapsed = filterPnlCollapsed;
    }

    public boolean isFilterPnlCollapsed() {
        return filterPnlCollapsed;
    }*/

    public void setSalEmpCalcSalSearchDTO(ISalEmpCalcSalSearchDTO salEmpCalcSalSearchDTO) {
        this.salEmpCalcSalSearchDTO = salEmpCalcSalSearchDTO;
    }

    public ISalEmpCalcSalSearchDTO getSalEmpCalcSalSearchDTO() {
        return salEmpCalcSalSearchDTO;
    }

    public void setRelatedWorkCentersFlag(boolean relatedWorkCentersFlag) {
        this.relatedWorkCentersFlag = relatedWorkCentersFlag;
    }

    public boolean isRelatedWorkCentersFlag() {
        return relatedWorkCentersFlag;
    }

    public void setHasUnsignedSett(boolean hasUnsignedSett) {
        this.hasUnsignedSett = hasUnsignedSett;
    }

    public boolean isHasUnsignedSett() {
        return hasUnsignedSett;
    }

    public void checkHasUnsignedSett(IBasicDTO dto){
        
            Long civil = ((ISalEmpMonSalariesEntityKey)dto.getCode()).getCivilId();
            try {
                hasUnsignedSett =  SalClientFactory.getSalEmpSettelmentsClient().checkUnsignedSett(civil, month , year);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        
        }

    public void setTotalSalaryLessThanHalfEmpMerits(boolean totalSalaryLessThanHalfEmpMerits) {
        this.totalSalaryLessThanHalfEmpMerits = totalSalaryLessThanHalfEmpMerits;
    }

    public boolean isTotalSalaryLessThanHalfEmpMerits() {
        return totalSalaryLessThanHalfEmpMerits;
    }

    public void setMarriedFemaleChild(boolean marriedFemaleChild) {
        this.marriedFemaleChild = marriedFemaleChild;
    }

    public boolean isMarriedFemaleChild() {
        return marriedFemaleChild;
    }

    public void setEmpDead(boolean empDead) {
        this.empDead = empDead;
    }

    public boolean isEmpDead() {
        return empDead;
    }

    public void setEmpChildrenDead(boolean empChildrenDead) {
        this.empChildrenDead = empChildrenDead;
    }

    public boolean isEmpChildrenDead() {
        return empChildrenDead;
    }

    public void setHasUnsignedDeduction(boolean hasUnsignedDeduction) {
        this.hasUnsignedDeduction = hasUnsignedDeduction;
    }

    public boolean isHasUnsignedDeduction() {
        return hasUnsignedDeduction;
    }

    public void setExpiredHiredChildRaise(boolean expiredHiredChildRaise) {
        this.expiredHiredChildRaise = expiredHiredChildRaise;
    }

    public boolean isExpiredHiredChildRaise() {
        return expiredHiredChildRaise;
    }

    public void setHasMissionStarted(boolean hasMissionStarted) {
        this.hasMissionStarted = hasMissionStarted;
    }

    public boolean isHasMissionStarted() {
        return hasMissionStarted;
    }

    public void setHasMissionStoped(boolean hasMissionStoped) {
        this.hasMissionStoped = hasMissionStoped;
    }

    public boolean isHasMissionStoped() {
        return hasMissionStoped;
    }

    public void setHasMissionFinished(boolean hasMissionFinished) {
        this.hasMissionFinished = hasMissionFinished;
    }

    public boolean isHasMissionFinished() {
        return hasMissionFinished;
    }

    public void setHasSickVac(boolean hasSickVac) {
        this.hasSickVac = hasSickVac;
    }

    public boolean isHasSickVac() {
        return hasSickVac;
    }
}

