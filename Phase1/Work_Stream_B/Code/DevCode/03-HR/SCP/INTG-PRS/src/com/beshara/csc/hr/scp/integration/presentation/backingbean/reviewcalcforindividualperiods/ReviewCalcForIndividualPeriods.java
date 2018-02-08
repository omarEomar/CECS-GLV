package com.beshara.csc.hr.scp.integration.presentation.backingbean.reviewcalcforindividualperiods;


//import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;


//import com.beshara.csc.hr.scp.presentation.backingbean.empvacenquiry.ListBean;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredInMinException;
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
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalEmpMonSalariesDTO;
import com.beshara.csc.hr.sal.business.entity.ISalEmpMonSalariesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEmpMonSalariesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.hr.sal.business.shared.ISALConstants;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.hr.scp.integration.presentation.backingbean.empvacenquiry.ListBean;
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
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class ReviewCalcForIndividualPeriods extends LookUpBaseBean {
    public static final String BEAN_NAME = "reviewCalcForIndividualPeriods";
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
    
    public static final String nav_case = "backTOIndividual";
    
    private String reportUrlLink;
    private String signatureStr;
    private static final String REPORT_PARAM_HOLDER = "#p#";
    private static final String EMP_MERITS_INDIVIDUAL_PERIOD_REPORT_CODE = "1663";
    
    ISalEmpCalcSalSearchDTO salEmpCalcSalSearchDTO;
    private boolean relatedWorkCentersFlag=false;
    public ReviewCalcForIndividualPeriods() {
        setContent1Div("paySlipDivContent1");
        setCustomDiv2("m2mEditDivClass");
        setEmpSettEnquiryBeanHelper(new EmpSettEnquiryBeanHelper());
        setClient(SalClientFactory.getSalEmpMonSalariesClient());
        setEmpListOfValues(new EmployeeListOfValues());
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
        app.setShowIntegrationDiv2(true);
        // modified by Nora Ismail as it is not exist at screen
         app.setShowEmpSrchDiv(true);
        return app;
    }

   // public void showSearchForEmployeeDiv() {
        //        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        //        employeeListOfValuesBean.setReturnMethodName("paySlipMaintainQueryBean.returnMethodAction");
        //        employeeListOfValuesBean.setSearchMethod("paySlipMaintainQueryBean.searchEmpLovDiv");
        //        employeeListOfValuesBean.getOkCommandButton().setReRender("mainDataEmpPanel , scriptGenerator , displayBtnPanel, civil_div_dataT_data");
        //        employeeListOfValuesBean.resetData();
        //        enableEmpLovDiv = true;
  //  }

   // public String searchEmpLovDiv(Object searchType, Object searchQuery) {
        //        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        //        try {
        //            IEmpEmployeeSearchDTO searchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
        //            searchDTO.setEmployment(true);
        //            if (searchType.toString().equals(SEARCH_BY_CODE)) {
        //                searchDTO.setCivilId(Long.valueOf((String)searchQuery));
        //            } else if (searchType.toString().equals(SEARCH_BY_NAME)) {
        //                searchDTO.setEmpName(searchQuery.toString());
        //            }
        //            IEmployeesClient client = EmpClientFactory.getEmployeesClient();
        //            List<IBasicDTO> searchResult = client.simpleSearch(searchDTO);
        //            employeeListOfValuesBean.setMyTableData(searchResult);
        //        } catch (SharedApplicationException e) {
        //            e.printStackTrace();
        //            employeeListOfValuesBean.setMyTableData(new ArrayList(0));
        //        } catch (DataBaseException e) {
        //            e.printStackTrace();
        //            employeeListOfValuesBean.setMyTableData(new ArrayList(0));
        //        }
    //    return "";
   // }

   // public void returnMethodAction() {
        //        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        //        List<IBasicDTO> selectedDTOsList = employeeListOfValuesBean.getSelectedDTOS();
        //        if (selectedDTOsList != null && selectedDTOsList.get(0) != null && selectedDTOsList.get(0).getCode() != null) {
        //            setEmpDTO((IEmployeesDTO)selectedDTOsList.get(0));
        //            setCivilId(((IEmployeesEntityKey)selectedDTOsList.get(0).getCode()).getCivilId());
        //            setRealCivilId(((IEmployeesDTO)selectedDTOsList.get(0)).getRealCivilId());
        //            filterByCivilId();
        //        }
        //        enableEmpLovDiv = false;
  //  }


    public void displayEmpSalaryDetails() {
        if (meritsListCopy != null && !meritsListCopy.isEmpty()) {
            for (IBasicDTO dto1: meritsListCopy) {
                ISalEmpPayslipsDTO dto  = (ISalEmpPayslipsDTO)dto1;
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
        reIntializeMsgs();
    }

    public void applyReviewEmpMonSalCalc() {
        SharedUtilBean sharedUtilBean = getSharedUtil();
       
        try {
            if(actualSalary!=null && actualSalary.doubleValue() <0D) {
                this.setShowErrorMsg(true);
                sharedUtilBean.setErrMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE,
                                                                            "notValidActualSalary"));
                setJavaScriptCaller("adjustDataTable('meritsDiv',125); adjustDataTable('deductionsDiv',125);");
                return;
            }
            //IEmployeesDTO selected = (IEmployeesDTO)((ISalEmpMonSalariesDTO)this.getSelectedPageDTO()).getEmployeesDTO();//getEmpDTO();
            ISalEmpMonSalariesDTO selectedSalEmpMonSalariesDTO = (ISalEmpMonSalariesDTO)getSelectedPageDTO();
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
                sharedUtilBean.setSuccessMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE,
                                                                                "reviewedSuccess"));
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
                sharedUtilBean.setSuccessMsgValue(sharedUtilBean.messageLocator(RESOURCE_PATH_SCP_BUNDLE,
                                                                                "cancelReviewedSuccess"));
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
        fillPaySlipData();
        loadEmpList();
        reSetData2(null);
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
                                                                             reviewEmpSalary,IScpConstants.PAYMENT_METHOD_SEPARATED_ORDER);
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

    public void loadEmpList() {
        this.reSetData2(null);
        clearData();
        filterByRealCivilId();
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
            salEmpCalcSalSearchDTO.setYearCode(Long.valueOf(year));
            salEmpCalcSalSearchDTO.setMonthCode(Long.valueOf(month));
            salEmpCalcSalSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            if (civilExist && validCivilId && realCivilId != null) {
                salEmpCalcSalSearchDTO.setRealCivilId(realCivilId);
            }
            salEmpCalcSalSearchDTO.setPaymethodCode(IScpConstants.SEPARATED_PAYMETHOD_CODE.toString());

//            clearData();
            try {
                //this.setSelectedPageDTO(SalDTOFactory.createSalEmpMonSalariesDTO() );
                this.setSelectedRadio(""); // by Aly Nour to reset selected dto
                monSalList =
                        SalClientFactory.getSalEmpMonSalariesClient().getCalcEmployeesToReview(salEmpCalcSalSearchDTO);
                setMyTableData(monSalList);

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
        deductionsList = new ArrayList<IBasicDTO>();
        totalDeductions = new BigDecimal(0L);
        totalMerits = new BigDecimal(0L);
        meritsList = new ArrayList<IBasicDTO>();
        setSelectedPageDTO(SalDTOFactory.createSalEmpMonSalariesDTO());
        setSelectedDTOS(new ArrayList());
        setExpiredChildRaise(false);
        existApprovedMangDiscount = false;
        bnkAccountStatus = 1;
        hasIntervals = false;
        actualSalary = null;
        setSettler(false);
        setErrors(false);
        calcExceptionsList = new ArrayList();
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
            meritsList = SalClientFactory.getSalEmpPayslipsClient().getMerDedList_new(searchDTO,IScpConstants.PAYMENT_METHOD_SEPARATED_ORDER);
            meritsListCopy = (List<IBasicDTO>)getSharedUtil().deepCopyObject(meritsList);
            showPaySlipPanel = 1;

        } catch (Exception e) {
            e.printStackTrace();
            meritsList = new ArrayList<IBasicDTO>();
        }
        try {
            searchDTO.setSalElementType(IScpConstants.MON_SAL_TYPE_DED);
            deductionsList = SalClientFactory.getSalEmpPayslipsClient().getMerDedList_new(searchDTO,IScpConstants.PAYMENT_METHOD_SEPARATED_ORDER);
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
        setIndexOfSelectedDataInDataTable(getMyDataTable().getRowIndex());
        this.getSelectedDTOS().clear();
        IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();
        this.getSelectedDTOS().add(selected);
        setSelectedPageDTO(selected);
        fillPaySlipData();
        checkDeductionsRatio();
        checkValidBnkAccount( (ISalEmpMonSalariesDTO)getSelectedPageDTO() );
        //checkExistApprovedMangDiscount( (ISalEmpMonSalariesDTO)getSelectedPageDTO() );
        checkEmpHasIntervals( (ISalEmpMonSalariesDTO)getSelectedPageDTO() );
        checkExpiredChildRaise(Long.valueOf(((ISalEmpMonSalariesDTO)getSelectedPageDTO()).getEmployeesDTO().getCode().getKey()));
    }

    private void checkExpiredChildRaise(Long parentCivilID) {
        setExpiredChildRaise(false);
        Long count = 0L;
        try {
            count = SalClientFactory.getSalEmpChildrenClient().countExcludedEmpChildern(parentCivilID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count > 0) {
            setExpiredChildRaise(true);
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
            hasIntervals = ((ISalEmpMonSalariesClient)getClient()).getEmpVacationsCountByCivilId(civilId,salaryMonth,salaryYear);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void checkExistApprovedMangDiscount(ISalEmpMonSalariesDTO salEmpMonSalariesDTO) {
        existApprovedMangDiscount = false;
        //Long empRealCivilID = salEmpMonSalariesDTO.getEmployeesDTO().getRealCivilId() ;
        Long civilId = Long.valueOf(salEmpMonSalariesDTO.getEmployeesDTO().getCode().getKey());
        SalEmpMonSalariesEntityKey salEmpMonSalariesEntityKey = (SalEmpMonSalariesEntityKey)salEmpMonSalariesDTO.getCode();
        Long year = salEmpMonSalariesEntityKey.getYearCode();
        Long month = salEmpMonSalariesEntityKey.getSalaryMonth();
        int discounts_no = 0;
        try {
            discounts_no = ((ISalEmpMonSalariesClient)getClient()).checkExistApprovedMangDiscount(civilId , month , year);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        if(discounts_no > 0 )existApprovedMangDiscount = true;
    }
    
    private void checkValidBnkAccount(ISalEmpMonSalariesDTO salEmpMonSalariesDTO) {
        bnkAccountStatus = 1;
        List<IBasicDTO> bnkAccountsList;
        PersonBankAccountsDTO personBankAccountsDTO = null;
        Long empRealCivilID = salEmpMonSalariesDTO.getEmployeesDTO().getRealCivilId() ;
        if(salEmpMonSalariesDTO.getBankBranchesDTO() != null) {
            Long bnkCode = ((BanksEntityKey)salEmpMonSalariesDTO.getBankBranchesDTO().getBanksDTO().getCode()).getBankCode();
            Long bnkBranchCode = salEmpMonSalariesDTO.getBankBranchesDTO().getBnkbranchCode();
            String accNo = salEmpMonSalariesDTO.getAccountChekNo();
            //salEmpMonSalariesDTO.geta
            try {
                if(bnkCode == null || bnkBranchCode == null || accNo == null){
                    bnkAccountStatus = 2;
                    return;
                }
                bnkAccountsList =
        BnkClientFactory.getPersonBankAccountsClient().getValidPersonBankAccountsByCivilID_forSalReview(empRealCivilID);
    
                if (bnkAccountsList != null && bnkAccountsList.size() > 0) {
                    for (int i = 0; i < bnkAccountsList.size() ; i++) {
                        personBankAccountsDTO = (PersonBankAccountsDTO)bnkAccountsList.get(i);
                        if(bnkCode.equals(personBankAccountsDTO.getBankCode()) 
                           && bnkBranchCode.equals(personBankAccountsDTO.getBnkBranchCode()) 
                           && accNo.equals(personBankAccountsDTO.getAccountNo() ) )
                        {
                            bnkAccountStatus = 1;
                            return;
                        }
                    }
                    bnkAccountStatus = 2;
                    return;
                }
                else{
                    bnkAccountStatus = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            bnkAccountStatus = 2;
            return;
        }
    }
    
    public void calcuateValues() {
        
        Map<IEntityKey, ISalElementGuidesDTO> merMap;
        Map<IEntityKey, ISalElementGuidesDTO> dedMap;
        
        merMap = new TreeMap<IEntityKey, ISalElementGuidesDTO>();
        dedMap = new TreeMap<IEntityKey, ISalElementGuidesDTO>();
        ISalElementGuidesDTO elementGuidesDTO;
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

            /*--Set the settlement flag with the retrieved value from DB to display button
             *--corresponding to the paySlip record that has settlement in case of Deductions;
             */
            dto.getElementGuidesDTO().getSalElementGuidesDTO().setBigValue(dto.getValue());
            dto.getElementGuidesDTO().getSalElementGuidesDTO().setIsSettler(dto.isIsSettler());
            if (dto.isIsSettler()) {
                setSettler(true);
            }
            //---------------------------------------------------------------------------------
            if (dto.getValue() != null)
                totalDed = totalDed.add(dto.getValue());
            if (dto.getElementGuidesDTO().getSalElementGuidesDTO() != null) {
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

        try {
            setErrors(false);
            List empList = getMyTableData();
            int empNo = empList.size();
            List<String> errDescList = new ArrayList<String>();
            errDescList.add(getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "Emp_Has_Sett_mon_Msg"));
            errDescList.add(getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "Emp_Has_vac_peroids_Msg"));
            if (empList != null && !empList.isEmpty()) {
                Long totalUnSettlerReviewed =
                    ((ISalEmpMonSalariesClient)getClient()).autoReviewEmployeesSal(salEmpCalcSalSearchDTO,empList, Long.valueOf(year),
                                                                                   Long.valueOf(month), errDescList);
                loadEmpList();
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
        getEmpSettEnquiryBeanHelper().setYear(year);
        getEmpSettEnquiryBeanHelper().setMonth(month);
        getEmpSettEnquiryBeanHelper().setCivilId(realCivilId.toString());
        getEmpSettEnquiryBeanHelper().setEmployeesDTO(empDTO);
        getEmpSettEnquiryBeanHelper().setSettlemenetType(settlemenetType);
        getEmpSettEnquiryBeanHelper().setSettWithDecsType(settWithDecsType);
        getEmpSettEnquiryBeanHelper().setRetroactive_SettType(retroactive_SettType);
        getEmpSettEnquiryBeanHelper().viewDecisionsAndSettlementsDetails();
    }

    public static ReviewCalcForIndividualPeriods getInstance() {
        return (ReviewCalcForIndividualPeriods)JSFHelper.getValue(BEAN_NAME);
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
        
        params.put("monSalList",monSalList);

            params.put("notes",notes);
 
        params.put("selStatus", selStatus);
        params.put("selWrkCenter", selWrkCenter);
        params.put("selWrkLevel", selWrkLevel);
        params.put("selbgtProgram", selbgtProgram);


        params.put("bgtListPrograms",bgtListPrograms);
        params.put("wrkCenters",wrkCenters);
        params.put("wrkLevels",wrkLevels);
        params.put("invalidDedRatio",invalidDedRatio);
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
        params.put("settlemenetType", settlemenetType);
        params.put("settWithDecsType", settWithDecsType);
        params.put("retroactive_SettType", retroactive_SettType);
        params.put("calcExceptionsList", calcExceptionsList);
        params.put("errors", errors);
        params.put("degreeFirstValue", degreeFirstValue);
        params.put("raisesTotalValue", raisesTotalValue);
        
        params.put("showPaySlipPanel", showPaySlipPanel);
        params.put("expiredChildRaise", expiredChildRaise);
        params.put("bnkAccountStatus", bnkAccountStatus);
        params.put("existApprovedMangDiscount", existApprovedMangDiscount);
        
        params.put("meritsListCopy", meritsListCopy);
        params.put("relatedWorkCentersFlag", relatedWorkCentersFlag);
        return params;
    };

    public void restoreSaveStateParams(Object obj) {
        if (obj != null) {
            try {
                Map params = (Map)obj;
                realCivilId = (Long)params.get("realCivilId");
                setMonSalList((List)params.get("monSalList"));
                setMyTableData((List)params.get("monSalList"));

                setSelStatus((Long)params.get("selStatus") );
                setSelbgtProgram((String)params.get("selbgtProgram") );
                setSelWrkCenter((String)params.get("selWrkCenter") );
                setSelWrkLevel((String)params.get("selWrkLevel") );
                bgtListPrograms = ((List)params.get("bgtListPrograms") );
                wrkCenters = ((List)params.get("wrkCenters") );
                wrkLevels = ((List)params.get("wrkLevels") );
                invalidDedRatio = ((Boolean)params.get("invalidDedRatio") );


                setYear((String)params.get("year") );
                setMonth((String)params.get("month") );

                empSettEnquiryBeanHelper = ((EmpSettEnquiryBeanHelper)params.get("empSettEnquiryBeanHelper") );
//                setMeritsDataTable((HtmlDataTable)params.get("meritsDataTable") );
//                setDeductionsDataTable((HtmlDataTable)params.get("deductionsDataTable") );
                
                meritsListCopy = (List<IBasicDTO>)params.get("meritsListCopy");
                relatedWorkCentersFlag = ((Boolean)params.get("relatedWorkCentersFlag") );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void restoreSaveStateParams2(Object obj) {
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
                notes = ((List)params.get("notes") );
                setDisabledAutoReview((Boolean)params.get("disabledAutoReview") );
                setDeductionsList((List)params.get("deductionsList") );
                setMeritsList((List)params.get("meritsList") );
                setSalEmpPayslipsDTO((ISalEmpPayslipsDTO)params.get("salEmpPayslipsDTO") );
                setTotalDeductions((BigDecimal)params.get("totalDeductions") );
                setTotalMerits((BigDecimal)params.get("totalMerits") );
                setActualSalary((BigDecimal)params.get("actualSalary") );
                settler = ((Boolean)params.get("settler") );
                hasIntervals = ((Boolean)params.get("hasIntervals") );
                expiredChildRaise = ((Boolean)params.get("expiredChildRaise") );
                bnkAccountStatus = ((Byte)params.get("bnkAccountStatus") );
                existApprovedMangDiscount = ((Boolean)params.get("existApprovedMangDiscount") );
                settlemenetType = ((String)params.get("settlemenetType") );
                settWithDecsType = ((String)params.get("settWithDecsType") );
                retroactive_SettType = ((String)params.get("retroactive_SettType") );
                calcExceptionsList = ((List)params.get("calcExceptionsList") );
                errors = ((Boolean)params.get("errors") );
                degreeFirstValue = ((Float)params.get("degreeFirstValue") );
                raisesTotalValue = ((Float)params.get("raisesTotalValue") );
                showPaySlipPanel = ((Integer)params.get("showPaySlipPanel") );
                relatedWorkCentersFlag = ((Boolean)params.get("relatedWorkCentersFlag") );
                //                setMeritsDataTable((HtmlDataTable)params.get("meritsDataTable") );
                //                setDeductionsDataTable((HtmlDataTable)params.get("deductionsDataTable") );
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
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "expired_child_msg");
            notes.add(message);
        }

        if (bnkAccountStatus.equals(Byte.parseByte("0")) ) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "invalidBnkAccount_msg");
            notes.add(message);
        }
        else if (bnkAccountStatus.equals(Byte.parseByte("2")) ) {
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "invalidBnkAccount_msg_2");
            notes.add(message);
        }
        
        if((actualSalary != null && actualSalary.compareTo(limitSalary) > 0 ) ){
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "actualSalary_exceeds_limitSalary_msg");
            notes.add(message);
        }
        
        if(existApprovedMangDiscount){
            String message = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "existApprovedMangDiscount_msg");
            notes.add(message);
        }
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
        hasNote = invalidDedRatio || expiredChildRaise || bnkAccountStatus.equals(Byte.parseByte("0")) 
                  || bnkAccountStatus.equals(Byte.parseByte("2")) || (actualSalary != null && actualSalary.compareTo(limitSalary) > 0 )
                  || existApprovedMangDiscount;
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
    
    public void printEmpMeritsForIndividualPeriods() {
        
        reportUrlLink = ISALConstants.PRINT_EMP_MERIT_DETAILS_LINK;
        ISalEmpMonSalariesDTO selectedDTO = (ISalEmpMonSalariesDTO)getSelectedPageDTO();
        
        String managerSign="" ;
        if(signatureStr!=null)
            managerSign=signatureStr;
        String selectedMonth=String.format("%02d", Integer.parseInt(month)); 
        String paymentDate="01/"+selectedMonth+"/"+year.toString();
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode(); // 2nd param
        Long civilId =selectedDTO.getEmployeesDTO().getRealCivilId();
        
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, EMP_MERITS_INDIVIDUAL_PERIOD_REPORT_CODE);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, managerSign);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, paymentDate);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, civilId.toString());
        System.out.println("generated params -----> " + reportUrlLink);

        System.out.println("generated link -----> " + reportUrlLink);

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

    public void showSearchForEmployeeDiv() {

        EmployeeListOfValues empListOfValuesBean = ((EmployeeListOfValues)getEmpListOfValues());
        //empListOfValuesBean.setEmpListOfValuesStyle("m2mAddDivClass");
//        empListOfValuesBean.setSearchMethod(BEAN_NAME +".searchEmpLovDiv");
//        empListOfValuesBean.setUseCustomSearch(true);
        empListOfValuesBean.setReturnMethodName(BEAN_NAME + ".returnMethodAction");
        empListOfValuesBean.resetData();
        empListOfValuesBean.getOkCommandButton().setReRender("emp_panel");
    }
   
    public void returnMethodAction() {
        if (getEmpListOfValues().getSelectedDTOS() != null && getEmpListOfValues().getSelectedDTOS().get(0) != null &&
            getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
            IEmployeesDTO employeesDTO = (IEmployeesDTO)getEmpListOfValues().getSelectedDTOS().get(0);
            realCivilId = employeesDTO.getRealCivilId();
            loadEmpList();
        }
    }
    
    public void openLovDiv(ActionEvent ae){
        
            EmployeeListOfValues empListOfValuesBean = ((EmployeeListOfValues)getEmpListOfValues());
            empListOfValuesBean.openLovDiv(ae);
           setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lovEmp); adjustDataTable('meritsDiv',125); adjustDataTable('deductionsDiv',125);");
        }

    public void setReportUrlLink(String reportUrlLink) {
        this.reportUrlLink = reportUrlLink;
    }

    public String getReportUrlLink() {
        return reportUrlLink;
    }

    public void setSignatureStr(String signatureStr) {
        this.signatureStr = signatureStr;
    }

    public String getSignatureStr() {
        return signatureStr;
    }

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
}

