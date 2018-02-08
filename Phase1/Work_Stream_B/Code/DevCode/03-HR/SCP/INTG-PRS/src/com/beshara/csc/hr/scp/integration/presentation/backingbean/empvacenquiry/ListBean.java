package com.beshara.csc.hr.scp.integration.presentation.backingbean.empvacenquiry;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStatusEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredInMinException;
import com.beshara.csc.hr.emp.integration.presentation.utils.EmployeeHelper;
import com.beshara.csc.hr.sal.business.client.ISalEmpMonSalariesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.IEmpVacSettlmentDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpCalcSalSearchDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSalariesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalEmpMonSalariesEntityKey;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.hr.sal.business.shared.exception.EmpVacSalCalculated;
import com.beshara.csc.hr.sal.business.shared.exception.EmpVacSalNotPayed;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.hr.sal.business.shared.exception.NoBankAccountData;
import com.beshara.csc.hr.sal.business.shared.exception.NoEmpSalElementsInVac;
import com.beshara.csc.hr.sal.business.shared.exception.VacDurationIntersectMultiOldDurations;
import com.beshara.csc.hr.scp.integration.presentation.backingbean.empmonthsalaryquery.PaySlipMaintainQueryBean;
import com.beshara.csc.hr.scp.integration.presentation.backingbean.reviewcalcforindividualperiods.ReviewCalcForIndividualPeriods;
import com.beshara.csc.hr.scp.integration.presentation.backingbean.reviewempmonthsal.ReviewEmpMonthSalBean;
import com.beshara.csc.hr.vac.business.client.IVacVacationTypesClient;
import com.beshara.csc.hr.vac.business.client.VacClientFactory;
import com.beshara.csc.hr.vac.business.dto.IVacEmpVacTrxsDTO;
import com.beshara.csc.hr.vac.business.dto.IVacEmployeeVacationsDTO;
import com.beshara.csc.hr.vac.business.dto.IVacVacationTypesDTO;
import com.beshara.csc.hr.vac.business.dto.VacDTOFactory;
import com.beshara.csc.hr.vac.business.entity.IVacEmployeeVacationsEntityKey;
import com.beshara.csc.hr.vac.business.shared.IVACConstants;
import com.beshara.csc.inf.business.client.IYearsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.exception.EmployeeCivilIdNotExistException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.sql.Date;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class ListBean extends LookUpBaseBean {

    @SuppressWarnings("compatibility:8854506677521561139")
    private static final long serialVersionUID = 1L;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private boolean comeFromSalReveiw = false;
    // private IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
    private Long realCivilId;
    private String civilIdNotReal;
    private boolean empHired = true;
    private boolean empHiredInMin = true;
    private boolean payrollInfoExist = true;
    private String degree;
    private ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO;
    public static final String BEAN_NAME = "empVacMonSalEnquiryListBean";
    private Object[] savedDataObjects = new Object[1];

    private List<ISalEmpMonSalariesDTO> salEmpMonList;
    private static final String RESOURCE_PATH_SCP_BUNDLE = "com.beshara.csc.hr.scp.presentation.resources.scp";
    private static final String BASE_PATH_SCP_BUNDLE = "com.beshara.jsfbase.csc.msgresources.global";

    /*payslip Div*/

    private transient HtmlDataTable meritsDataTable = new HtmlDataTable();
    private transient HtmlDataTable deductionsDataTable = new HtmlDataTable();
    private transient HtmlDataTable monSalDataTable = new HtmlDataTable();
    private int showPaySlipPanel = 0;
    private List<IBasicDTO> meritsList;
    private List<IBasicDTO> deductionsList;

    private boolean editMode = false;
    private Long salBaseUndefined = IScpConstants.SAL_BASE_UNDEFINED;
    private Long salBaseBasic = IScpConstants.SAL_BASE_BASIC;
    private Long salBaseActual = IScpConstants.SAL_BASE_ACTUAL;
    EmployeeHelper employeeHelper = new EmployeeHelper();

    private int meritsSize = 0;
    private int dedsSize = 0;
    private Long vacTypeEditMode = 7L;
    //========================i=================
    private List<IBasicDTO> settlmentTypesList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> paymentTypesList = new ArrayList<IBasicDTO>();
    private String paymentType = IScpConstants.WITH_SALARY.toString();
    private String settlmentType = IScpConstants.SETTLMENT_IN_ADVANCE.toString();
    private String selVacType;
    private List vacTypeList;
    private List vacTypeList1;
    private List vacTypeList2;
    private Long selVacTypeCode;
    private List months;
    private List years;
    private String month;
    private String year;
    private static final String RESOURCE_KEY_MONTH_PRE = "month_key_";
    private Date dateFrom = null;
    private Date dateTo = null;
    private String divMode = "1";
    private boolean inAdvance;
    /* start CSC-16646 A.Nour */
    private static final int HIDE_DIV = 0;
    private static final int KEEP_DIV = 1;
    private static final int SHOW_CONFIRM_DIV = 2;

    private boolean disableExecute = true;
    private int showCustomMsg = 0;

    private int settlmentResult =
        0; // 1 has calulated sal on month /year ,2 for vac not retroactive  date ,3 settlment exist before for the same month year
    /* end CSC-16646 A.Nour */
    //===========================================================
    /* CSC-16527 */
    private static int START_DAY_OF_MONTH = 1;
    private String upFrontVacConfrmMsg;

    private String navigationBack;
    private Long salaryMonth;
    private Long yearCode; /* used to view specified vac durations */


    private boolean reducedSettVac = false;
    private boolean serviceEnded;
    private static final String SEARCH_BY_CODE = "0";
    private static final String SEARCH_BY_NAME = "1";

    private List<IBasicDTO> vacHistoryList = new ArrayList<IBasicDTO>();
    private Boolean sharedNavigationBack = false;
     
    
    public ListBean() {
        setClient(SalClientFactory.getSalEmpMonSalariesClient());
        if (getEmpListOfValues() == null)
            setEmpListOfValues(new EmployeeListOfValues());
        setSingleSelection(true);
        setCustomDiv1("empVacEnquirypPayslipDivClass");
        initEmpHelper();
        setSelectedPageDTO(VacDTOFactory.createVacEmployeeVacationsDTO());
        setCustomDiv3("m2mEditDivClass");
   
    }


    public void initiateBeanOnce() {
        if (salEmpMonList == null) {
            salEmpMonList = new ArrayList();
        }
        fill_settlmentTypesList();
        loadVacTypeList();
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

    public void setCivilIdNotReal(String civilIdNotReal) {
        this.civilIdNotReal = civilIdNotReal;
    }

    public String getCivilIdNotReal() {
        return civilIdNotReal;
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

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowEmpSrchDiv(true);
        app.setShowbar(true);
        app.setShowpaging(false);
        app.setShowCommonData(true);
        app.setShowshortCut(true);
        app.setShowdatatableContent(true);
        app.setShowCustomDiv1(true);

        app.setShowLookupAdd(false);
        app.setShowLookupEdit(true);
        app.setShowDelAlert(true);
        app.setShowDelConfirm(false);
        app.setShowSearch(false);
        //===========i=======
        app.setShowLookupAdd(true);

        app.setShowCustomDiv2(true);
        app.setShowCustomDiv3(true);
        return app;
    }

    private void resetMessages() {
        empHired = true;
        empHiredInMin = true;
        payrollInfoExist = true;
        validCivilId = true;
        civilExist = false;
    }

    public void initEmpHelper() {
        employeeHelper.setBackBeanNameFrom(BEAN_NAME);
        employeeHelper.setResetButtonMethod("reSetData");
        employeeHelper.setShowPayrollInfo(true);
    }

    public void filterByCivilId() {
        resetMessages();
        //        if (civilId != null && !civilId.equals("")) {

        IEmployeesDTO dto = null;
        serviceEnded = false;
        try {
            //        emp = employeeHelper.filterByCivilId();
            //        if (emp != null) {
            //            civilExist = true;
            //            validCivilId = true;
            //            setCivilIdNotReal(((IEmployeesEntityKey)(emp.getCode())).getCivilId().toString());
            //            realCivilId = emp.getRealCivilId();
            //            salEmpSalaryElementsDTO = emp.getSalEmpSalaryElementsDTO();
            //            salEmpSalaryElementsDTO.setEmployeesDTO(emp);
            //            setDegree(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFullName()); //fillDegree());
            //            Long empSerial = ((IEmployeesEntityKey)emp.getCode()).getCivilId();
            //            fillEmpVac(empSerial);
            //        } else {
            //            civilExist = false;
            //            validCivilId = false;
            //        }

            //dto =
            //    employeeHelper.getHiredAndHavePayrollInfoEmpInMinstry(Long.valueOf(realCivilId), CSCSecurityInfoHelper.getLoggedInMinistryCode());
            dto = employeeHelper.getLastActiveEmp(Long.valueOf(realCivilId));
            Long hireStatus = ((IHireStatusEntityKey)dto.getHireStatusDTO().getCode()).getHirstatusCode();
            if (hireStatus.equals(IEMPConstants.EMP_STATUS_END_SERVICE)) {
                serviceEnded = true;
            }
            if (dto != null) {
                validCivilId = true;
                civilExist = true;
                setCivilIdNotReal(dto.getCode().getKey());
                salEmpSalaryElementsDTO = dto.getSalEmpSalaryElementsDTO();
                //                            (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(Long.parseLong(civilId),
                //                                                                                                                                 ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
                salEmpSalaryElementsDTO.setEmployeesDTO(dto);
                setDegree(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFullName());
                Long empSerial = ((IEmployeesEntityKey)dto.getCode()).getCivilId();
                fillEmpVac(empSerial);
            } else {
                validCivilId = false;
                civilExist = false;

            }
        } catch (EmployeeCivilIdNotExistException e) {
            validCivilId = false;
            civilExist = false;
            e.printStackTrace();
        } catch (EmployeeNotHiredInMinException e) {
            empHiredInMin = false;
            e.printStackTrace();
        } catch (EmployeeNotHiredException e) {
            empHired = false;
            e.printStackTrace();
        } catch (EmployeePayrollInfoNotExistException e) {
            payrollInfoExist = false;
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //        }
    }

    public void showSearchForEmployeeDiv() {

        EmployeeListOfValues empListOfValuesBean = ((EmployeeListOfValues)getEmpListOfValues());
        empListOfValuesBean.setEmpListOfValuesStyle("m2mAddDivClass");
        empListOfValuesBean.setSearchMethod("empVacMonSalEnquiryListBean.searchEmpLovDiv");
        empListOfValuesBean.setUseCustomSearch(true);
        empListOfValuesBean.setReturnMethodName(BEAN_NAME + ".returnMethodAction");
        empListOfValuesBean.resetData();
        empListOfValuesBean.getOkCommandButton().setReRender("cnt1Panel,scriptpanel,OperationBar,dataT_data_panel,paging_panel,displayBtnPanel");
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
            validCivilId = true;
            civilExist = true;
            empHired = true;
            empHiredInMin = true;
            IEmployeesDTO employeesDTO = (IEmployeesDTO)getEmpListOfValues().getSelectedDTOS().get(0);
            realCivilId = employeesDTO.getRealCivilId();
            employeeHelper.setRealCivilId(realCivilId.toString());
            filterByCivilId();

        }
    }

    public void reSetData() {
        reSetData(null);
    }

    public void reSetData(ActionEvent ae) {
        ae = null;
        setSalEmpSalaryElementsDTO(SalDTOFactory.createSalEmpSalaryElementsDTO());
        setRealCivilId(null);
        civilExist = false;
        validCivilId = true;
        empHired = true;
        empHiredInMin = true;
        setMyTableData(new ArrayList());
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        getHighlightedDTOS().clear();
        setErrorMsg(null);
        degree = "";
        salEmpMonList = new ArrayList();
        setSelectedRadio("");

    }

    public List getTotalList() {
        return new ArrayList();
    }

    public void fillEmpVac(Long civilId) {
        List totalList = new ArrayList();
        try {
            totalList = ((ISalEmpMonSalariesClient)getClient()).getEmpVacationsByCivilId(civilId, salaryMonth, yearCode);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        setMyTableData(totalList);

        this.reinitializeSort();

        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
            setSelectedRadio("");
        }

        if (getHighlightedDTOS() != null) {
            getHighlightedDTOS().clear();
        }

    }

    public void setSalEmpSalaryElementsDTO(ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO) {
        this.salEmpSalaryElementsDTO = salEmpSalaryElementsDTO;
    }

    public ISalEmpSalaryElementsDTO getSalEmpSalaryElementsDTO() {
        return salEmpSalaryElementsDTO;
    }

    public void getVacDurations() {
        try {
            //setIndexOfSelectedDataInDataTable(getMyDataTable().getRowIndex());
            //this.getSelectedDTOS().clear();
            if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty()) {
                IVacEmployeeVacationsDTO selected = (IVacEmployeeVacationsDTO)getSelectedDTOS().get(0);
                //this.getMyDataTable().getRowData();
                //this.getSelectedDTOS().add(selected);
                IVacEmployeeVacationsEntityKey key = (IVacEmployeeVacationsEntityKey)selected.getCode();
                salEmpMonList =
                        ((ISalEmpMonSalariesClient)getClient()).getEmpVacMonSalListByCivilId(key.getCivilId(), key.getEmpvacCode());
            } else {
                salEmpMonList = new ArrayList();
            }

        } catch (Exception dbe) {
            dbe.printStackTrace();
            salEmpMonList = new ArrayList();
        }
    }


    public void selectedRadioButton(ActionEvent ae) throws Exception {
        reducedSettVac = false;
        super.selectedRadioButton(ae);
        getVacDurations();
        reducedSettVac = checkReducedSettlmentVac();
    }

    public void setSalEmpMonList(List<ISalEmpMonSalariesDTO> salEmpMonList) {
        this.salEmpMonList = salEmpMonList;
    }

    public List<ISalEmpMonSalariesDTO> getSalEmpMonList() {
        return salEmpMonList;
    }

    public void setShowPaySlipPanel(int showPaySlipPanel) {
        this.showPaySlipPanel = showPaySlipPanel;
    }

    public int getShowPaySlipPanel() {
        return showPaySlipPanel;
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

    public void fillPaySlipData() {
        editMode = false;
        meritsSize = 0;
        dedsSize = 0;
        ISalEmpMonSalariesDTO selected = (ISalEmpMonSalariesDTO)this.getMonSalDataTable().getRowData();

        if (Long.valueOf(selected.getPaymentStatusDTO().getCode().getKey()).equals(vacTypeEditMode) && !serviceEnded) {
            editMode = true;
        }
        ISalEmpMonSearchCriteriaDTO searchDTO = SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
        searchDTO.setCivilId(((ISalEmpMonSalariesEntityKey)selected.getCode()).getCivilId());
        searchDTO.setSalaryMonth(selected.getSalaryMonth());
        searchDTO.setYear(selected.getYearCode());
        searchDTO.setSerial(selected.getSerial());

        try {
            meritsList = SalClientFactory.getSalEmpPayslipsClient().getPayslipDetails(searchDTO);
            meritsSize = meritsList.size();
            showPaySlipPanel = 1;

        } catch (Exception e) {
            e.printStackTrace();
            meritsList = new ArrayList<IBasicDTO>();
        }
        try {
            searchDTO.setSalElementType(IScpConstants.MON_SAL_TYPE_DED);
            deductionsList = SalClientFactory.getSalEmpPayslipsClient().getPayslipDetails(searchDTO);
            dedsSize = deductionsList.size();
            showPaySlipPanel = 1;
        } catch (Exception e) {
            e.printStackTrace();
            deductionsList = new ArrayList<IBasicDTO>();
        }

    }


    public void update() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            List<IBasicDTO> payslipList = meritsList;
            if (!deductionsList.isEmpty())
                for (IBasicDTO dto : deductionsList) {
                    payslipList.add(dto);
                }
            SalClientFactory.getSalEmpPayslipsClient().update(payslipList);
            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            IVacEmployeeVacationsDTO dto = (IVacEmployeeVacationsDTO)getSelectedDTOS().get(0);
            if (super.isUsingPaging()) {
                generatePagingRequestDTO((String)dto.getCode().getKey());

            } else {
                getPageIndex((String)dto.getCode().getKey());
            }

            if (super.getHighlightedDTOS() != null) {
                super.getHighlightedDTOS().clear();
                super.getHighlightedDTOS().add(dto);
            }
        } catch (Exception e) {
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");

        }


    }


    public void setMonSalDataTable(HtmlDataTable monSalDataTable) {
        this.monSalDataTable = monSalDataTable;
    }

    public HtmlDataTable getMonSalDataTable() {
        return monSalDataTable;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setSalBaseUndefined(Long salBaseUndefined) {
        this.salBaseUndefined = salBaseUndefined;
    }

    public Long getSalBaseUndefined() {
        return salBaseUndefined;
    }

    public void setSalBaseBasic(Long salBaseBasic) {
        this.salBaseBasic = salBaseBasic;
    }

    public Long getSalBaseBasic() {
        return salBaseBasic;
    }

    public void setSalBaseActual(Long salBaseActual) {
        this.salBaseActual = salBaseActual;
    }

    public Long getSalBaseActual() {
        return salBaseActual;
    }

    public void setEmployeeHelper(EmployeeHelper employeeHelper) {
        this.employeeHelper = employeeHelper;
    }

    public EmployeeHelper getEmployeeHelper() {
        return employeeHelper;
    }

    public void setMeritsSize(int meritsSize) {
        this.meritsSize = meritsSize;
    }

    public int getMeritsSize() {
        return meritsSize;
    }

    public void setDedsSize(int dedsSize) {
        this.dedsSize = dedsSize;
    }

    public int getDedsSize() {
        return dedsSize;
    }

    public void deleteDiv() throws DataBaseException, ItemNotFoundException {

        if (checkValidMonSalStatus()) {
            this.delete();
            //  this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");
        } else {
            getSharedUtil().handleException(null, RESOURCE_PATH_SCP_BUNDLE, "Emp_Vac_Sal_Cal_Status_MSG");
        }

    }

    public boolean checkValidMonSalStatus() {
        if (salEmpMonList != null && !salEmpMonList.isEmpty()) {
            for (ISalEmpMonSalariesDTO selected : salEmpMonList) {
                if (!Long.valueOf(selected.getPaymentStatusDTO().getCode().getKey()).equals(IScpConstants.NO_CALC_PAYSTATUS_CODE)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String backToSalReview() {
        if (navigationBack.equals(ReviewCalcForIndividualPeriods.nav_case)) {
            ReviewCalcForIndividualPeriods reviewCalcForIndividualPeriods =
                ReviewCalcForIndividualPeriods.getInstance();
            reviewCalcForIndividualPeriods.restoreSaveStateParams(getSavedDataObjects()[0]);
            //reviewEmpMonthSalBean.loadEmpList();
            reviewCalcForIndividualPeriods.restoreSaveStateParams2(getSavedDataObjects()[0]);
            reviewCalcForIndividualPeriods.setJavaScriptCaller("javascript:adjustDataTable('meritsDiv',125); adjustDataTable('deductionsDiv',125);");
        }
        else if (navigationBack.equals(PaySlipMaintainQueryBean.nav_case)) {
            PaySlipMaintainQueryBean paySlipMaintainQueryBean =
                PaySlipMaintainQueryBean.getInstance();
            paySlipMaintainQueryBean.restoreSaveStateParams(getSavedDataObjects()[0]);
            //reviewEmpMonthSalBean.loadEmpList();
            //paySlipMaintainQueryBean.restoreSaveStateParams2(getSavedDataObjects()[0]);
            paySlipMaintainQueryBean.setJavaScriptCaller("javascript:adjustDataTable('meritsDiv',150); adjustDataTable('deductionsDiv',150);");
        } else if (sharedNavigationBack) {
            IntegrationBean integrationBean =
                (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
            return integrationBean.goFrom();
        } else {
            ReviewEmpMonthSalBean reviewEmpMonthSalBean = ReviewEmpMonthSalBean.getInstance();
            reviewEmpMonthSalBean.restoreSaveStateParams(getSavedDataObjects()[0]);
            //reviewEmpMonthSalBean.loadEmpList();
            reviewEmpMonthSalBean.restoreSaveStateParams2(getSavedDataObjects()[0]);
            reviewEmpMonthSalBean.setJavaScriptCaller("javascript:adjustDataTable('meritsDiv',125); adjustDataTable('deductionsDiv',125);");
        }
        return navigationBack;
    }

    public void delete() throws DataBaseException, ItemNotFoundException {
        SharedUtilBean shared_util = getSharedUtil();

        try {
            IVacEmployeeVacationsDTO selected = (IVacEmployeeVacationsDTO)this.getSelectedDTOS().get(0);
            ((ISalEmpMonSalariesClient)getClient()).remove(selected, salEmpMonList);
            shared_util.setSuccessMsgValue("Deleted");
            salEmpMonList = new ArrayList();
            if (getMyDataTable() != null && getMyDataTable().getRowCount() != 0) {
                getMyDataTable().setFirst(0);
            }
        } catch (Exception e) {
            getSharedUtil().handleException(e, BASE_PATH_SCP_BUNDLE, "NotDeleted");
        }

        fillEmpVac(Long.valueOf(civilIdNotReal));
        restoreTablePosition();


    }

    public static ListBean getInstance() {
        return (ListBean)JSFHelper.getValue(BEAN_NAME);
    }

    public void setComeFromSalReveiw(boolean comeFromSalReveiw) {
        this.comeFromSalReveiw = comeFromSalReveiw;
    }

    public boolean isComeFromSalReveiw() {
        return comeFromSalReveiw;
    }

    public void setSavedDataObjects(Object[] savedDataObjects) {
        this.savedDataObjects = savedDataObjects;
    }

    public Object[] getSavedDataObjects() {
        return savedDataObjects;
    }

    public void setSettlmentTypesList(List<IBasicDTO> settlmentTypesList) {
        this.settlmentTypesList = settlmentTypesList;
    }

    private void fill_settlmentTypesList() {
        try {
            if(settlmentTypesList == null)settlmentTypesList = new  ArrayList<IBasicDTO>();
            if (settlmentTypesList.isEmpty()) {
                IBasicDTO dto = new BasicDTO();
                dto.setCode(new EntityKey(new String[] { "" + IScpConstants.SETTLMENT_IN_ADVANCE + "" }));
                dto.setName(getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "SETTLMENT_INTRODUCTION"));
                settlmentTypesList.add(dto);
                dto = new BasicDTO();
                dto.setCode(new EntityKey(new String[] { "" + IScpConstants.SETTLMENT_REDUCED + "" }));
                dto.setName(getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "SETTLMENT_DISCOUNTED"));
                settlmentTypesList.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<IBasicDTO> getSettlmentTypesList() {
        return settlmentTypesList;
    }

    public void setSettlmentType(String settlmentType) {
        this.settlmentType = settlmentType;
    }

    public String getSettlmentType() {
        return settlmentType;
    }

    public void setSelVacType(String selVacType) {
        this.selVacType = selVacType;
    }

    public String getSelVacType() {
        return selVacType;
    }

    public void setVacTypeList(List vacTypeList) {
        this.vacTypeList = vacTypeList;
    }

    public List getVacTypeList() {
        if (settlmentType != null && settlmentType.equals(IScpConstants.SETTLMENT_REDUCED.toString())) {
            return vacTypeList1;
        } else {
            return vacTypeList2;
        }
    }
    public void loadVacTypeList() {
        if (vacTypeList1 == null) {
            try {
                IVacVacationTypesClient client = VacClientFactory.getVacVacationTypesClient();
                vacTypeList1 = client.getAllVacTypes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (vacTypeList2 == null) {
            vacTypeList2 = new ArrayList();
        }
        if (vacTypeList2.isEmpty() && vacTypeList1 != null && !vacTypeList1.isEmpty()) {
            IVacVacationTypesDTO vacTypeDto = null;
            String vacTypeCode = null;
            for (int i = 0; i < vacTypeList1.size(); i++) {
                vacTypeDto = (IVacVacationTypesDTO)vacTypeList1.get(i);
                vacTypeCode = vacTypeDto.getCode().getKey();
                if (vacTypeCode.equals(IScpConstants.PERIODIC_VACATION_TYPE.toString()) ||
                    vacTypeCode.equals(IScpConstants.HAJJ_VACATION_TYPE.toString())) {
                    vacTypeList2.add(vacTypeDto);
                }
                if (vacTypeList2.size() == 2) {
                    break;
                }
            }

        }
    }

    public void setSelVacTypeCode(Long selVacTypeCode) {
        this.selVacTypeCode = selVacTypeCode;
    }

    public Long getSelVacTypeCode() {
        return selVacTypeCode;
    }

    public void setMonths(List months) {
        this.months = months;
    }

    public List getMonths() {
        loadMonths();
        return months;
    }

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
    public void setYears(List years) {
        this.years = years;
    }

    public List getYears() {
        loadYears();
        return years;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDivMode(String divMode) {
        this.divMode = divMode;
    }

    public String getDivMode() {
        return divMode;
    }

    public void setPaymentTypesList(List<IBasicDTO> paymentTypesList) {
        this.paymentTypesList = paymentTypesList;
    }

    private void fill_paymentTypesList() {

    }

    public List<IBasicDTO> getPaymentTypesList() {
        try {
            paymentTypesList.clear();
            IBasicDTO dto = new BasicDTO();

            //**** start story CSC-17409 A.Nour
            dto.setCode(new EntityKey(new String[] { "" + IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE + "" }));
            //**** End story CSC-17409 A.Nour
            dto.setName(getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "with_salary"));
            paymentTypesList.add(dto);

            if (settlmentType.equals(IScpConstants.SETTLMENT_IN_ADVANCE.toString())) {
                dto = new BasicDTO();
                //**** start story CSC-17409 A.Nour
                dto.setCode(new EntityKey(new String[] { "" + IScpConstants.SEPARATED_PAYMETHOD_CODE + "" }));
                //**** End story CSC-17409 A.Nour
                dto.setName(getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "individually"));
                paymentTypesList.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentTypesList;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setInAdvance(boolean inAdvance) {
        this.inAdvance = inAdvance;
    }

    public boolean isInAdvance() {
        return inAdvance;
    }

    /* start CSC-16646 A.Nour */
    public void preAdd() {
        resetAddDiv();
        fill_settlmentTypesList();
        loadVacTypeList();
    }

    private void resetAddDiv() {
        //        System.out.println("Calling preAdd()...");
        setSuccess(false);
        setShowErrorMsg(false);
        setDivMode("0");
        settlmentType = IScpConstants.SETTLMENT_IN_ADVANCE.toString();
        selVacTypeCode = null;
        selVacType = null;
        dateFrom = null;
        dateTo = null;
        showCustomMsg = 1;
        this.setErrorMsg("");
        setCurrentMonthYear();

        //setSearchMode(false);
    }

    public void setCurrentMonthYear() {
        Calendar currentDate = Calendar.getInstance();
        month = Integer.toString(currentDate.get(Calendar.MONTH) + 1);
        year = Integer.toString(currentDate.get(Calendar.YEAR));
        System.out.println(month + "--" + year);
    }

    private boolean validateInAdvanceOrReduced() { //IVacEmployeeVacationsDTO dto) {
        try {
            showCustomMsg = 0;
            this.setErrorMsg("");
            disableExecute = false;
            setSettlmentResult(HIDE_DIV);
            if (!settlmentType.equals(IScpConstants.SETTLMENT_REDUCED.toString())) {
                Long settMonth = Long.valueOf(month);
                Long settYear = Long.valueOf(year);

                Long count = SalClientFactory.getSalEmpMonSalariesClient().checkEmpSalaryCalculated(realCivilId,
                                settYear, settMonth);

                if (count > 0L) {
                    setSettlmentResult(KEEP_DIV);
                    setErrorMsg("Vac_Emp_Salary_Calc_Msg");
                    disableExecute = true;
                    return false;
                }
            }

            if (!SalClientFactory.getSalEmpSettelmentsClient().checkEmpSalaryPayedThroughDuration(realCivilId , dateFrom, dateTo, false)
            //checkEmpVacSalaryPayed(dto, false) 
            ) {
                showCustomMsg = 0;
                this.setErrorMsg("emp_vac_sal_in_advance_payed_error");
                setSettlmentResult(KEEP_DIV);
                disableExecute = true;
                return false;
            }

            List<java.sql.Date> calcMonthes =
                SalClientFactory.getSalEmpSettelmentsClient().checkEmpSalaryCalculatedThroughDuration(realCivilId , dateFrom, dateTo, false);
            //checkEmpSalaryCalculatedDuringVacation(dto);
            if (calcMonthes != null && calcMonthes.size() > 0) {
                String finalMessage =
                    getSharedUtil().getResourceBundle(RESOURCE_PATH_SCP_BUNDLE).getString("emp_vac_sal_in_advance_calc_error");
                String txtMessage = "";
                if (calcMonthes.size() > 1)
                    txtMessage = "أشهر (";
                else
                    txtMessage = "شهر (";

                Calendar calDate = Calendar.getInstance();
                int salCalcMonth = 0;
                int salCalcYear = 0;
                int listIndex = 1;
                for (Date empCalcMonth : calcMonthes) {
                    calDate.setTime(empCalcMonth);
                    salCalcMonth = calDate.get(Calendar.MONTH);
                    salCalcYear = calDate.get(Calendar.YEAR);

                    salCalcMonth++;

                    txtMessage += salCalcYear + "/" + salCalcMonth;

                    if (listIndex < calcMonthes.size())
                        txtMessage += " - ";
                    listIndex++;
                }
                txtMessage += ")";

                finalMessage = finalMessage.replaceFirst("XXX", txtMessage);
                showCustomMsg = 1;
                this.setErrorMsg(finalMessage);
                setSettlmentResult(KEEP_DIV);
                disableExecute = true;
            } else
                disableExecute = false;
        } catch (DataBaseException e) {
            e.printStackTrace();
            disableExecute = true;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            disableExecute = true;
        }
        return (!disableExecute);
    }

    public void changeSettlmentType(ActionEvent ae) {
        if (settlmentType == null || settlmentType.equals(IScpConstants.SETTLMENT_REDUCED.toString())) {
            paymentType = IScpConstants.WITH_SALARY.toString();
        }
        selVacTypeCode = null;
        selVacType = null;
        //        if (year != null && month != null && settlmentType != null &&
        //            settlmentType.equals(IScpConstants.SETTLMENT_RETROACTIVELY.toString()) && getSelectedDTOS() != null &&
        //            getSelectedDTOS().size() == 1)
        //        if (year == null || month == null) {
        //            setCurrentMonthYear();
        //        }
        //        totalSettlmentValue = null;

        //        if (getSelectedDTOS() != null && getSelectedDTOS().size() == 1) {
        //            IVacEmployeeVacationsDTO dto = (IVacEmployeeVacationsDTO)getSelectedDTOS().get(0);


        //            validateInAdvanceOrReduced();//dto);
        //            if (settlmentType != null && !settlmentType.equals(IScpConstants.SETTLMENT_REDUCED.toString())) {
        //                try {
        //                    Long civilId = Long.valueOf(civilIdNotReal);
        //                    totalSettlmentValue =
        //                            SalClientFactory.getSalEmpSettelmentsClient().getEmpTotalSettelementsByMonthAndYear(civilId,
        //                                                                                                                Long.valueOf(year),
        //                                                                                                                Long.valueOf(month));
        //                } catch (Exception e) {
        //                    e.printStackTrace();
        //                }
        //            }
        //        }
    }


    public void preExecuteSettlment() {

        try {
            boolean isValid = validateInAdvanceOrReduced(); //dto);
            if(!isValid)return;

            if (settlmentType.equals(IScpConstants.SETTLMENT_IN_ADVANCE.toString())) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                // IVacEmployeeVacationsDTO vacEmployeeVacationsDTO = (IVacEmployeeVacationsDTO)getSelectedDTOS().get(0);
                Calendar calDate = Calendar.getInstance();
                calDate.setTime(dateFrom);
                int fromMonth = calDate.get(Calendar.MONTH) + 1;
                int day = calDate.get(Calendar.DAY_OF_MONTH);
                if (day == START_DAY_OF_MONTH) {
                    executeSettlment();
                    return;
                }

                int fromYear = calDate.get(Calendar.YEAR);
                String fromDateStr = day + "/" + fromMonth + "/" + fromYear;
                java.sql.Date fromDate = new java.sql.Date(dateFormat.parse(fromDateStr).getTime());
                String dateWithFirstDayOfMonthStr = START_DAY_OF_MONTH + "/" + fromMonth + "/" + fromYear;
                java.sql.Date dateWithFirstDayOfMonth = new java.sql.Date(dateFormat.parse(dateWithFirstDayOfMonthStr).getTime());
                String paymentMonthYear = month + "/" + year;
                String message =
                    getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "upfront_vac_confirm_msg");
                message = message.replaceFirst("#", dateWithFirstDayOfMonth + "");
                message = message.replaceFirst("#", fromDate + "");
                message = message.replaceFirst("#", paymentMonthYear + "");
                upFrontVacConfrmMsg = message;
                System.out.println(upFrontVacConfrmMsg);
                setSettlmentResult(SHOW_CONFIRM_DIV);
                //setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.delAlert);settingFoucsAtDivDelete();return false;");
            } else {
                executeSettlment();

            }

        } catch (Exception pe) {
            // TODO: Add catch code
            pe.printStackTrace();
        }
    }

    public void executeUpFrontSettlment() {
        SharedUtilBean sharedUtil = getSharedUtil();
        String successMsg = "";
        //        boolean isValid =  validateInAdvanceOrReduced();//dto);
        //        if(!isValid)return;
        boolean done = false;

        IVacEmployeeVacationsDTO resVacationsDTO = null;
        Long civilId = Long.valueOf(civilIdNotReal);//((IEmployeesEntityKey)vacEmployeeVacationsDTO.getEmployeesDTO().getCode()).getCivilId();
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        /*
        IVacEmployeeVacationsDTO vacEmployeeVacationsDTO = VacDTOFactory.createVacEmployeeVacationsDTO();
        vacEmployeeVacationsDTO.setVacVacationTypesKey(selVacTypeCode.toString() );
        vacEmployeeVacationsDTO.setFromDate(dateFrom);
        vacEmployeeVacationsDTO.setUntilDate(dateTo);
        IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
        employeesDTO.setCode(EmpEntityKeyFactory.createEmployeesEntityKey (civilId) );
        employeesDTO.setRealCivilId(realCivilId);
        employeesDTO.setMinCode(minCode);
        vacEmployeeVacationsDTO.setEmployeesDTO(employeesDTO);
        
        */
        //(IVacEmployeeVacationsDTO)getSelectedDTOS().get(0);
        //        Long realCivilId =  Long.valueOf(civilId);
        //vacEmployeeVacationsDTO.getEmployeesDTO().getRealCivilId(); //.getCode().getKey();
        //Long vacCode = null;
        //((IVacEmployeeVacationsEntityKey)vacEmployeeVacationsDTO.getCode()).getEmpvacCode();
        IVacEmployeeVacationsDTO vacEmployeeVacationsDTO = fillVacEmployeeVacationsDTO();
        try {
            IEmpVacSettlmentDTO empVacSettlmentDTO = SalDTOFactory.createEmpVacSettlmentDTO();
            empVacSettlmentDTO.setEmployeeVacationsDTO(vacEmployeeVacationsDTO);
            empVacSettlmentDTO.setYear(Long.valueOf(year));
            empVacSettlmentDTO.setMonth(Long.valueOf(month));
            empVacSettlmentDTO.setSettlmentType(Long.valueOf(settlmentType));
            empVacSettlmentDTO.setFirstPeroidOfMonth(true);

            //            IVacEmployeeVacationsDTO vacEmpVacForDurationDTO = (IVacEmployeeVacationsDTO)SalClientFactory.getSalEmpSettelmentsClient().getVacEmployeeVacationsDTOForDuration(empVacSettlmentDTO);
            //            empVacSettlmentDTO.setEmployeeVacationsDTO(vacEmpVacForDurationDTO);

            //empVacSettlmentDTO.setInssuranceFlag(inssuranceFlag);
            //call execute Method

            boolean failInExecution = false;
            if (settlmentType.equals(IScpConstants.SETTLMENT_IN_ADVANCE.toString())) {
                //**** start story CSC-17409 A.Nour
                if (paymentType != null)
                    empVacSettlmentDTO.setPayMethodCode(Long.parseLong(paymentType));

                //**** End story CSC-17409 A.Nour
                resVacationsDTO = (IVacEmployeeVacationsDTO)SalClientFactory.getSalEmpSettelmentsClient().executeUpFrontVacSettelementForDuration(empVacSettlmentDTO);
                SalClientFactory.getSalEmpSettelmentsClient().checkEmpVacSettExecution(minCode, realCivilId, civilId,
                                                                                       resVacationsDTO); //vacCode);
                successMsg = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "emp_vac_salary_success");
                done = true;
            }
            if (done) {
                fillEmpVac(Long.valueOf(civilIdNotReal));
                sharedUtil.setSuccessMsgValue(successMsg);
                setDivMode("1");
                setSettlmentResult(HIDE_DIV);
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            getSharedUtil().handleException(new Exception(), RESOURCE_PATH_SCP_BUNDLE, "emp_vac_error");
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            if (e instanceof NoEmpSalElementsInVac) {
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "emp_vac_no_elements");
            } else if (e instanceof EmpVacSalNotPayed) {
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "emp_vac_sal_not_calc");
            } else if (e instanceof NoBankAccountData) {
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "emp_has_not_account_no");
            } else if (e instanceof EmpVacSalCalculated) {
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "sal_calculated_in_this_duration");
            } else if (e instanceof VacDurationIntersectMultiOldDurations) {
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE,
                                                "VacDurationIntersectMultiOldDurations_Err_Msg");
            } else
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "emp_vac_error");
        } catch (Exception e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "emp_vac_error");
        }
    }
    private IVacEmployeeVacationsDTO fillVacEmployeeVacationsDTO() {
            Long civilId = Long.valueOf(civilIdNotReal);//((IEmployeesEntityKey)vacEmployeeVacationsDTO.getEmployeesDTO().getCode()).getCivilId();
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        IVacEmployeeVacationsDTO vacEmployeeVacationsDTO = VacDTOFactory.createVacEmployeeVacationsDTO();
        vacEmployeeVacationsDTO.setVacVacationTypesKey(selVacTypeCode.toString());
        vacEmployeeVacationsDTO.setFromDate(dateFrom);
        vacEmployeeVacationsDTO.setUntilDate(dateTo);
        IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
        employeesDTO.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(civilId));
        employeesDTO.setRealCivilId(realCivilId);
        employeesDTO.setMinCode(minCode);
        vacEmployeeVacationsDTO.setEmployeesDTO(employeesDTO);
        return vacEmployeeVacationsDTO;
    }
    public void executeSettlment() {
        SharedUtilBean sharedUtil = getSharedUtil();
        String successMsg = "";

        boolean done = false;
        IVacEmployeeVacationsDTO resVacationsDTO = null;
        Long civilId = Long.valueOf(civilIdNotReal);//((IEmployeesEntityKey)vacEmployeeVacationsDTO.getEmployeesDTO().getCode()).getCivilId();
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();

        /*
        IVacEmployeeVacationsDTO vacEmployeeVacationsDTO = VacDTOFactory.createVacEmployeeVacationsDTO();
        vacEmployeeVacationsDTO.setVacVacationTypesKey(selVacTypeCode.toString() );
        vacEmployeeVacationsDTO.setFromDate(dateFrom);
        vacEmployeeVacationsDTO.setUntilDate(dateTo);
        IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
        employeesDTO.setCode(EmpEntityKeyFactory.createEmployeesEntityKey (civilId) );
        employeesDTO.setRealCivilId(realCivilId);
        employeesDTO.setMinCode(minCode);
        vacEmployeeVacationsDTO.setEmployeesDTO(employeesDTO);
        */
        IVacEmployeeVacationsDTO vacEmployeeVacationsDTO = fillVacEmployeeVacationsDTO();


        //(IVacEmployeeVacationsDTO)getSelectedDTOS().get(0);
        //        Long realCivilId =  Long.valueOf(civilId);
        //vacEmployeeVacationsDTO.getEmployeesDTO().getRealCivilId(); //.getCode().getKey();
        Long vacCode = null;
        //((IVacEmployeeVacationsEntityKey)vacEmployeeVacationsDTO.getCode()).getEmpvacCode();

        try {
            IEmpVacSettlmentDTO empVacSettlmentDTO = SalDTOFactory.createEmpVacSettlmentDTO();
            empVacSettlmentDTO.setEmployeeVacationsDTO(vacEmployeeVacationsDTO);
            empVacSettlmentDTO.setYear(Long.valueOf(year));
            empVacSettlmentDTO.setMonth(Long.valueOf(month));
            empVacSettlmentDTO.setSettlmentType(Long.valueOf(settlmentType));

            //            IVacEmployeeVacationsDTO vacEmpVacForDurationDTO = (IVacEmployeeVacationsDTO)SalClientFactory.getSalEmpSettelmentsClient().getVacEmployeeVacationsDTOForDuration(empVacSettlmentDTO);
            //            empVacSettlmentDTO.setEmployeeVacationsDTO(vacEmpVacForDurationDTO);

            //empVacSettlmentDTO.setInssuranceFlag(inssuranceFlag);
            //call execute Method

            boolean failInExecution = false;
            if (settlmentType.equals(IScpConstants.SETTLMENT_IN_ADVANCE.toString())) {
                //**** start story CSC-17409 A.Nour
                if (paymentType != null)
                    empVacSettlmentDTO.setPayMethodCode(Long.parseLong(paymentType));
                //**** End story CSC-17409 A.Nour
                resVacationsDTO = (IVacEmployeeVacationsDTO)SalClientFactory.getSalEmpSettelmentsClient().executeUpFrontVacSettelementForDuration(empVacSettlmentDTO);
                SalClientFactory.getSalEmpSettelmentsClient().checkEmpVacSettExecution(minCode, realCivilId, civilId,
                                                                                       resVacationsDTO); //vacCode);
                successMsg = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "emp_vac_salary_success");
                done = true;
            } else {
                resVacationsDTO = (IVacEmployeeVacationsDTO)SalClientFactory.getSalEmpSettelmentsClient().executeReducedVacSettelementForDuration(empVacSettlmentDTO);
                SalClientFactory.getSalEmpSettelmentsClient().checkEmpVacSettExecution(minCode, realCivilId, civilId,resVacationsDTO);//vacCode);
                successMsg = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "emp_vac_salary_success");
                done = true;
            }
            if (done) {
                fillEmpVac(Long.valueOf(civilIdNotReal));
                sharedUtil.setSuccessMsgValue(successMsg);
                setDivMode("1");
                setSettlmentResult(HIDE_DIV);
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            getSharedUtil().handleException(new Exception(), RESOURCE_PATH_SCP_BUNDLE, "emp_vac_error");
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            if (e instanceof NoEmpSalElementsInVac) {
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "emp_vac_no_elements");
            } else if (e instanceof EmpVacSalNotPayed) {
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "emp_vac_sal_not_calc");
            } else if (e instanceof NoBankAccountData) {
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "emp_has_not_account_no");
            } else if (e instanceof EmpVacSalCalculated) {
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "sal_calculated_in_this_duration");
            } else if (e instanceof VacDurationIntersectMultiOldDurations) {
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE,
                                                "VacDurationIntersectMultiOldDurations_Err_Msg");
            } else
                getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "emp_vac_error");
        } catch (Exception e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, RESOURCE_PATH_SCP_BUNDLE, "emp_vac_error");
        }

        //        if (done) {
//            
        //            boolean checkEmpVacSettExec=false;
        //            vacCode =((IVacEmployeeVacationsEntityKey)resVacationsDTO.getCode()).getEmpvacCode();
        //            try {
        //                checkEmpVacSettExec = SalClientFactory.getSalEmpSettelmentsClient().checkEmpVacSettExecution(minCode, realCivilId, civilId,
        //                                                                               vacCode);
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //            }
        //            if(checkEmpVacSettExec){
        //                    sharedUtil.setSuccessMsgValue(successMsg);
        //                    setDivMode("1");
        //                }
        //            else if(resVacationsDTO !=null && resVacationsDTO.getChecked()) {
        //
        //                try {
        //                    VacClientFactory.getVacEmployeeVacationsClient().remove(resVacationsDTO);
        //                } catch (Exception e) {
        //                    e.printStackTrace();
//                } 
        //                getSharedUtil().handleException(new Exception(), RESOURCE_PATH_SCP_BUNDLE, "emp_vac_error");
        //            }
//            
        //        }
    }
    //**** start story CSC-17409 A.Nour
    public void calcDuration() {
        try {
            IVacEmployeeVacationsDTO selectedVac = (IVacEmployeeVacationsDTO)this.getMyDataTable().getRowData();
            ISalEmpMonSalariesDTO selected = selectedVac.getSalEmpMonSalariesDTO();
            //(ISalEmpMonSalariesDTO)this.getMonSalDataTable().getRowData();
            ISalEmpCalcSalSearchDTO salEmpCalcSalSearchDTO = SalDTOFactory.createSalEmpCalcSalSearchDTO();
            if (selected != null) {
                salEmpCalcSalSearchDTO.setYearCode(selected.getYearCode());
                salEmpCalcSalSearchDTO.setMonthCode(selected.getSalaryMonth());
                Long realCivilId = ((ISalEmpMonSalariesEntityKey)selected.getCode()).getCivilId();
                //salEmpCalcSalSearchDTO.setCivilId();
                salEmpCalcSalSearchDTO.setRealCivilId(Long.valueOf(realCivilId));
                salEmpCalcSalSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
                salEmpCalcSalSearchDTO.setUserCode(CSCSecurityInfoHelper.getUserCode());

                Calendar calendar = Calendar.getInstance();
                Integer claMonth = Integer.parseInt(selected.getSalaryMonth().toString());
                claMonth = claMonth - 1;
                calendar.set(Integer.parseInt(selected.getYearCode().toString()), claMonth, 1);
                salEmpCalcSalSearchDTO.setCalculationDate(new Date(calendar.getTime().getTime()));
                System.out.println("Date is " + salEmpCalcSalSearchDTO.getCalculationDate());

                salEmpCalcSalSearchDTO =
                        (ISalEmpCalcSalSearchDTO)((ISalEmpMonSalariesClient)getClient()).calcActualSalaryForEmployees(salEmpCalcSalSearchDTO);
                        // till database function updated 

                getVacDurations();
            }

            if (salEmpCalcSalSearchDTO != null && salEmpCalcSalSearchDTO.getSuccededCalcCount() > 0) {
                String successMsg = getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE, "calcDurationSuccessMsg");
                getSharedUtil().setSuccessMsgValue(successMsg);
            } else {
                this.setShowErrorMsg(true);
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE,
                                                                              "calcDurationFailMsg"));
            }
            //salEmpCalcSalSearchDTO.setFailedCalcCount(1L);

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            this.setShowErrorMsg(true);
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(RESOURCE_PATH_SCP_BUNDLE,"calcDurationFailMsg"));
        }
    }
    //**** End story CSC-17409 A.Nour
    public void setShowCustomMsg(int showCustomMsg) {
        this.showCustomMsg = showCustomMsg;
    }

    public int getShowCustomMsg() {
        return showCustomMsg;
    }

    public void setSettlmentResult(int settlmentResult) {
        this.settlmentResult = settlmentResult;
    }

    public int getSettlmentResult() {
        return settlmentResult;
    }

    public void setDisableExecute(boolean disableExecute) {
        this.disableExecute = disableExecute;
    }

    public boolean isDisableExecute() {
        return disableExecute;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    /* end CSC-16646 A.Nour */


    public void setUpFrontVacConfrmMsg(String upFrontVacConfrmMsg) {
        this.upFrontVacConfrmMsg = upFrontVacConfrmMsg;
    }

    public String getUpFrontVacConfrmMsg() {
        return upFrontVacConfrmMsg;
    }

    public void setVacTypeList1(List vacTypeList1) {
        this.vacTypeList1 = vacTypeList1;
    }

    public List getVacTypeList1() {
        return vacTypeList1;
    }

    public void setVacTypeList2(List vacTypeList2) {
        this.vacTypeList2 = vacTypeList2;
    }

    public List getVacTypeList2() {
        return vacTypeList2;
    }

    public void setNavigationBack(String navigationBack) {
        this.navigationBack = navigationBack;
    }

    public String getNavigationBack() {
        return navigationBack;
    }

    public void setSalaryMonth(Long salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public Long getSalaryMonth() {
        return salaryMonth;
    }

    public void setYearCode(Long yearCode) {
        this.yearCode = yearCode;
    }

    public Long getYearCode() {
        return yearCode;
    }

    public void setEmpHiredInMin(boolean empHiredInMin) {
        this.empHiredInMin = empHiredInMin;
    }

    public boolean isEmpHiredInMin() {
        return empHiredInMin;
    }


   
    public void setReducedSettVac(boolean reducedSettVac) {
        this.reducedSettVac = reducedSettVac;
    }

    public boolean isReducedSettVac() {
        return reducedSettVac;
    }
    private boolean checkReducedSettlmentVac() {
        if (salEmpMonList != null && !salEmpMonList.isEmpty()) {
            ISalEmpMonSalariesDTO salEmpMonSalariesDTO = salEmpMonList.get(0);
            Date fromDate = salEmpMonSalariesDTO.getFromDate();
            Long month = salEmpMonSalariesDTO.getSalaryMonth();
            Long year = salEmpMonSalariesDTO.getYearCode();
            Calendar fromDateCalender = Calendar.getInstance();
            fromDateCalender.setTime(fromDate);
            int fromMonth = fromDateCalender.get(Calendar.MONTH) + 1;
            int fromYear = fromDateCalender.get(Calendar.YEAR);
            //System.out.println(month + "="+fromMonth+"::"+year + "="+fromYear );
            if (month.intValue() == fromMonth && year.intValue() == fromYear) {
                return true;
            }
        }
        return false;
    }

    public void showReducedVacDiv(ActionEvent e) {
        try {
            //VacClientFactory.getVacEmpVacTrxsClient().getVacTRX
            //getVacTRX(Long civilId, Long empvacCode, Long trxTypeCode)
            setSelectedPageDTO((IVacEmployeeVacationsDTO)getSelectedDTOS().get(0));
            //CSC-18315 adedd by nora ismail
            IVacEmployeeVacationsEntityKey code = (IVacEmployeeVacationsEntityKey)((IVacEmployeeVacationsDTO)getSelectedPageDTO()).getCode();
            List<IVacEmpVacTrxsDTO> list = new ArrayList();
            try {
                 list = VacClientFactory.getVacEmpVacTrxsClient().getVacTRX(code.getCivilId(), code.getEmpvacCode(),
                                                                        IVACConstants.VAC_EMP_VAC_STATUS_APPROVED);
            } catch (Exception e1) {
                // TODO: Add catch code
                e1.printStackTrace();
            }
            if (!list.isEmpty()) {
                IVacEmpVacTrxsDTO vacEmpVacTrxsDTO = list.get(0);
                //((IVacEmployeeVacationsDTO)getSelectedPageDTO()).setFromDate(vacEmpVacTrxsDTO.getFromDate());
                ((IVacEmployeeVacationsDTO)getSelectedPageDTO()).setUntilDate(vacEmpVacTrxsDTO.getUntilDate());
            }

            try {
                 list = VacClientFactory.getVacEmpVacTrxsClient().getVacTRX(code.getCivilId(), code.getEmpvacCode(),
                                                                        IVACConstants.VAC_TRXTYPE_CUT);
            } catch (Exception e1) {
                // TODO: Add catch code
                e1.printStackTrace();
            }
            if (!list.isEmpty()) {
                IVacEmpVacTrxsDTO vacEmpVacTrxsDTO = list.get(0);
                ((IVacEmployeeVacationsDTO)getSelectedPageDTO()).setReturnDate(vacEmpVacTrxsDTO.getUntilDate());
            }
            if (((IVacEmployeeVacationsDTO)getSelectedPageDTO()).getReturnDate() != null) {
                //  ReplaceFromDate
                ((IVacEmployeeVacationsDTO)getSelectedPageDTO()).setReplaceFromDate((Date)SalClientFactory.getSalEmpMonSalariesClient().getAvailableCutDate(realCivilId,
                                                                                                                                                            ((IVacEmployeeVacationsDTO)getSelectedPageDTO()).getUntilDate(),
                                                                                                                                                            ((IVacEmployeeVacationsDTO)getSelectedPageDTO()).getReturnDate()));
            } else {
                ((IVacEmployeeVacationsDTO)getSelectedPageDTO()).setReplaceFromDate(null);
            }


            ((IVacEmployeeVacationsDTO)getSelectedPageDTO()).setReplaceEndDate(null);
        } catch (Exception f) {
            f.printStackTrace();
        }
    }

    public void executeVacCut() {


        boolean checkSalCalc = false;

        try {
            checkSalCalc = SalClientFactory.getSalEmpMonSalariesClient().checkEmpSalaryCalc(realCivilId,((IVacEmployeeVacationsDTO)getSelectedPageDTO()).getReplaceEndDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (checkSalCalc) {
            getSharedUtil().handleException(new Exception(), RESOURCE_PATH_SCP_BUNDLE, "cut_vac_date_calc_sal_msg");
            return;
        }

        try {
            Long civilId = Long.valueOf(salEmpSalaryElementsDTO.getEmployeesDTO().getCode().getKey());
            setSelectedPageDTO(SalClientFactory.getSalEmpMonSalariesClient().updateEmpMonSalary((IVacEmployeeVacationsDTO)getSelectedPageDTO(),salEmpMonList));
            SalClientFactory.getSalEmpSettelmentsClient().updatePayslipByDBackage(CSCSecurityInfoHelper.getLoggedInMinistryCode(), realCivilId, civilId,((IVacEmployeeVacationsDTO)getSelectedPageDTO()));
            getSelectedDTOS().clear();
            getSelectedDTOS().add(getSelectedPageDTO());
            refreshPage();
            getSharedUtil().handleSuccMsg(RESOURCE_PATH_SCP_BUNDLE, "cut_vac_sucess_msg");
        }

        catch (Exception e) {
            getSharedUtil().handleException(e, 
                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        }
    }


    public void refreshPage() {
        List totalList = new ArrayList();
        try {
                totalList = ((ISalEmpMonSalariesClient)getClient()).getEmpVacationsByCivilId(Long.valueOf(civilIdNotReal), salaryMonth, yearCode);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        setMyTableData(totalList);
        try {

            getVacDurations();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setServiceEnded(boolean serviceEnded) {
        this.serviceEnded = serviceEnded;
    }

    public boolean isServiceEnded() {
        return serviceEnded;
    }

    public void setVacHistoryList(List<IBasicDTO> vacHistoryList) {
        this.vacHistoryList = vacHistoryList;
    }

    public List<IBasicDTO> getVacHistoryList() {
        return vacHistoryList;
    }

    public void showVacTrxHistory(ActionEvent e) {
        try {
            IVacEmployeeVacationsDTO selected = (IVacEmployeeVacationsDTO)getSelectedDTOS().get(0);
            IVacEmployeeVacationsEntityKey key = (IVacEmployeeVacationsEntityKey)selected.getCode();
            vacHistoryList =
                    VacClientFactory.getVacEmpVacTrxsClient().getEmpVacTrxsByCode_SAL(key.getCivilId(), key.getEmpvacCode());
            //            vacHistoryList =
            //                    VacClientFactory.getVacEmpVacTrxsClient().getEmpVacTrxsByCode_SAL(335L,41978L);
//        

        } catch (Exception ex) {
            vacHistoryList = new ArrayList<IBasicDTO>();
            ex.printStackTrace();
        }
    }

    public String back() {
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());

        return integrationBean.goFrom();
    }

    public void setSharedNavigationBack(Boolean sharedNavigationBack) {
        this.sharedNavigationBack = sharedNavigationBack;
    }

    public Boolean getSharedNavigationBack() {
        return sharedNavigationBack;
    }
}
