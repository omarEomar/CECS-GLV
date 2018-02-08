package com.beshara.csc.hr.scp.integration.presentation.backingbean.socialinsuranceperoidcalc;


import com.beshara.base.deploy.SessionBeanProviderException;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredInMinException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeServiceNotEndedException;
import com.beshara.csc.hr.emp.integration.presentation.utils.EmployeeHelper;
import com.beshara.csc.hr.sal.business.client.ISalSalarySheetsClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.IPeroidCalcInsurDTO;
import com.beshara.csc.hr.sal.business.dto.ISalSalarySheetsDTO;
import com.beshara.csc.hr.sal.business.dto.SalSalarySheetsDTO;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeeLastDegNotMokafaaShamlaException;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.inf.business.exception.EmployeeCivilIdNotExistException;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;

import java.math.BigDecimal;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;


public class MainDataBean extends LookUpBaseBean {
    @SuppressWarnings("compatibility:-4842243461617027352")
    private static final long serialVersionUID = 1L;

    protected static final String RESOURCE_BUNDLE =
        "com.beshara.csc.hr.scp.integration.presentation.resources.integration";
    private static final String BEAN_NAME = "socialInsurPeroidCalcMainDataBean";

    protected List<IBasicDTO> categoryList;
    protected List<IBasicDTO> ministryList;
    private Long catCode;
    private Long minCode;
    private String regNo;
    private Long civilId;
    private Long realCivilId;
    private boolean empHired = true;
    private boolean payrollInfoExist = true;
    private boolean enableEmpLovDiv;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private IEmployeesDTO empDTO = EmpDTOFactory.createEmployeesDTO();
    EmployeeHelper employeeHelper = new EmployeeHelper();
    //=========== 2,3 part properties ================
    private Date fromDate;
    private Date todate;
    private String calcTemplateType;
    private Long peroidPerMonth = new Long(0);
    private Long yearPerMonth = new Long(0);
    private String tableNo;
    private String decNo;
    private Long yearPeroidInstallmentValue;
    private BigDecimal installmentValueDK;
    private BigDecimal totalPaymentValue = new BigDecimal(0);
    private boolean empHasInsurrance = true;

    public MainDataBean() {
        setClient(SalClientFactory.getSalSalarySheetsClient());
        if (getEmpListOfValues() == null)
            setEmpListOfValues(new EmployeeListOfValues());

        initEmpHelper();
        setContent1Div("module_tabs_cont1");
    }

    public void initiateBeanOnce() {

        if (categoryList == null || categoryList.size() == 0) {
            try {
                categoryList = OrgClientFactory.getCatsClient().getCatsByGovFlag(ISystemConstant.GOVERNMENT_FLAG);
            } catch (Exception e) {
                e.printStackTrace();
                categoryList = new ArrayList<IBasicDTO>();
            }
        }
    }

    public static MainDataBean getInstance() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (MainDataBean)app.createValueBinding("#{" + BEAN_NAME + "}").getValue(ctx);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();

        app.setShowSearch(false);
        app.setShowbar(false);
        app.setShowpaging(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowdatatableContent(false);

        //===========================
        app.setShowContent1(true);
        app.setShowWizardBar(true);
        app.setShowEmpSrchDiv(true);
        return app;
    }

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

            empEmployeeSearchDTO.setMinistryCode(minCode);
            List hireStatusList = new ArrayList();
            hireStatusList.add(IEMPConstant.EMP_STATUS_END_SERVICE);
            hireStatusList.add(IEMPConstant.EMP_STATUS_EMPLOYMENT);
            hireStatusList.add(IEMPConstants.EMP_STATUS_DELEGATION_TO_OUT);
            hireStatusList.add(IEMPConstants.EMP_STATUS_DELEGATION_OUT_TO_IT);
            hireStatusList.add(IEMPConstants.EMP_STATUS_FREEZING);

            empEmployeeSearchDTO.setEmpHireStatusList(hireStatusList);
            try {
                if (isUsingPaging()) {
                    empListOfValuesBean.setUpdateMyPagedDataModel(true);
                    empListOfValuesBean.setPagingRequestDTO(new PagingRequestDTO("filterSearchByEmpWithPaging"));
                    empListOfValuesBean.setOldPageIndex(0);
                    empListOfValuesBean.setCurrentPageIndex(1);
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


    public IEmployeesDTO simulateFilterbyCivilHelper(Long minCodeP) {
        IEmployeesDTO employeesDTO = null;
        resetMessages();

        if (employeeHelper.getRealCivilId() != null && !employeeHelper.getRealCivilId().equals("") &&
            minCodeP != null) {

            try {
                employeesDTO =
                        employeeHelper.getLastActiveEmpInMin(Long.valueOf(employeeHelper.getRealCivilId()), minCodeP);
                employeeHelper.setEmployeesDTO(employeesDTO);

                employeeHelper.setCivilExist(true);
                employeeHelper.setValidCivilId(true);
                return employeesDTO;

            } catch (EmployeeNotHiredInMinException e) {
                employeeHelper.setEmpHiredInMin(false);
                e.printStackTrace();
            } catch (EmployeeNotHiredException e) {
                employeeHelper.setEmpHired(false);
                e.printStackTrace();
            } catch (EmployeePayrollInfoNotExistException e) {
                employeeHelper.setPayrollInfoExist(false);
                e.printStackTrace();
            } catch (EmployeeCivilIdNotExistException e) {
                employeeHelper.setCivilExist(false);
                employeeHelper.setValidCivilId(false);
                e.printStackTrace();
            } catch (EmployeeServiceNotEndedException e) {
                employeeHelper.setCivilExist(false);
                employeeHelper.setEmpEndedService(false);
                e.printStackTrace();
            } catch (EmployeeLastDegNotMokafaaShamlaException e) {
                employeeHelper.setCivilExist(false);
                employeeHelper.setEmpLastDegNotMokafaaShamla(false);
                e.printStackTrace();
            } catch (Exception e) {
                employeeHelper.setCivilExist(false);
                employeeHelper.setEmpHired(false);
                e.printStackTrace();
            }


        }
        return null;
    }

    public void filterByCivilId() {

        resetMessages();
        empDTO = null;

        empDTO = simulateFilterbyCivilHelper(minCode);
        if (empDTO != null) {

            try {
                empHasInsurrance =
                        SalClientFactory.getSalEmpSalaryElementsClient().checkEmpHasInsurance(empDTO.getRealCivilId());
            } catch (Exception e) {
                e.printStackTrace();
                empHasInsurrance = false;
            }
            if (empHasInsurrance) {
                civilExist = true;
                validCivilId = true;
                setCivilId(((IEmployeesEntityKey)(empDTO.getCode())).getCivilId());

                setRealCivilId(empDTO.getRealCivilId());


            } else {
                employeeHelper.setCivilExist(false);
                empDTO = EmpDTOFactory.createEmployeesDTO();
            }


        } else {

            civilExist = false;
            validCivilId = false;
            empHired = false;
            empDTO = EmpDTOFactory.createEmployeesDTO();
        }


    }

    private void clearEmpDataMinistryChanged() {
        resetMessages();
        setCivilId(null);
        setRealCivilId(null);
        employeeHelper.setRealCivilId(null);
        empDTO = EmpDTOFactory.createEmployeesDTO();
    }

    public void changeMinistry() {
        clearEmpDataMinistryChanged();
        if (getMinCode() == null)
            setRegNo(null);
        else {
            ISalSalarySheetsClient salSalarySheetsClient = (ISalSalarySheetsClient)getClient();
            ISalSalarySheetsDTO salSalarySheetsDTO = new SalSalarySheetsDTO();
            salSalarySheetsDTO.setLogginMinCode(getMinCode());
            IBasicDTO resDTO = null;
            try {
                resDTO = salSalarySheetsClient.getRegisterationCodeByMinCode(salSalarySheetsDTO);
                String registerationCode = resDTO.getName();
                setRegNo(registerationCode);
            } catch (DataBaseException e) {
                e.printStackTrace();
                setRegNo(null);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                setRegNo(null);
            }
        }
    }


    public void filterByCategory(ActionEvent event) {
        if (event != null) {
            setMinCode(null);
            clearEmpDataMinistryChanged();
        }
        if (catCode != null) {
            try {
                ministryList = OrgClientFactory.getMinistriesClient().getAllByCategory(catCode);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                ministryList = new ArrayList<IBasicDTO>();
            } catch (DataBaseException e) {
                e.printStackTrace();
                ministryList = new ArrayList<IBasicDTO>();
            }
        } else {
            ministryList = new ArrayList<IBasicDTO>();
        }
    }

    public void initEmpHelper() {
        employeeHelper.setBackBeanNameFrom(BEAN_NAME);
        employeeHelper.setResetButtonMethod("reSetData");
        employeeHelper.setShowPayrollInfo(true);
        employeeHelper.setShowBankAccData(true);
    }

    public void reSetData() {
        reSetData(null);
    }

    public void initEmpDTO() {
        empDTO = EmpDTOFactory.createEmployeesDTO();
        empDTO.setBgtProgramsDTO(BgtDTOFactory.createBgtProgramsDTO());
        empDTO.setBgtTypesDTO(BgtDTOFactory.createBgtTypesDTO());
    }


    public void reSetData(ActionEvent ae) {
        ae = null;
        civilId = null;
        empHired = true;
        payrollInfoExist = true;
        enableEmpLovDiv = false;
        validCivilId = true;
        civilExist = false;

        reIntializeMsgs();

        initEmpDTO();

        realCivilId = null;
        getEmployeeHelper().setRealCivilId("");

    }

    private void resetEmpHeplerData() {
        employeeHelper.setEmpHired(true);
        employeeHelper.setEmpEndedService(true);
        employeeHelper.setEmpLastDegNotMokafaaShamla(true);
        employeeHelper.setEmpHiredInMin(true);
        employeeHelper.setPayrollInfoExist(true);
        employeeHelper.setValidCivilId(true);
        employeeHelper.setCivilExist(false);


    }

    private void resetMessages() {
        resetEmpHeplerData();
        empHired = true;
        payrollInfoExist = true;
        validCivilId = true;
        civilExist = false;
        empHasInsurrance = true;
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

    public void setCatCode(Long catCode) {
        this.catCode = catCode;
    }

    public Long getCatCode() {
        return catCode;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
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

    public void setEmpDTO(IEmployeesDTO empDTO) {
        this.empDTO = empDTO;
    }

    public IEmployeesDTO getEmpDTO() {
        return empDTO;
    }

    public void setEmployeeHelper(EmployeeHelper employeeHelper) {
        this.employeeHelper = employeeHelper;
    }

    public EmployeeHelper getEmployeeHelper() {
        return employeeHelper;
    }

    public void getAll() throws DataBaseException {
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setCalcTemplateType(String calcTemplateType) {
        this.calcTemplateType = calcTemplateType;
    }

    public String getCalcTemplateType() {
        return calcTemplateType;
    }

    public void setPeroidPerMonth(Long peroidPerMonth) {
        this.peroidPerMonth = peroidPerMonth;
    }

    public Long getPeroidPerMonth() {
        return peroidPerMonth;
    }

    public void setYearPerMonth(Long yearPerMonth) {
        this.yearPerMonth = yearPerMonth;
    }

    public Long getYearPerMonth() {
        return yearPerMonth;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setDecNo(String decNo) {
        this.decNo = decNo;
    }

    public String getDecNo() {
        return decNo;
    }

    public void setYearPeroidInstallmentValue(Long yearPeroidInstallmentValue) {
        this.yearPeroidInstallmentValue = yearPeroidInstallmentValue;
    }

    public Long getYearPeroidInstallmentValue() {
        return yearPeroidInstallmentValue;
    }

    public void setInstallmentValueDK(BigDecimal installmentValueDK) {
        this.installmentValueDK = installmentValueDK;
    }

    public BigDecimal getInstallmentValueDK() {
        return installmentValueDK;
    }

    public void setTotalPaymentValue(BigDecimal totalPaymentValue) {
        this.totalPaymentValue = totalPaymentValue;
    }

    public BigDecimal getTotalPaymentValue() {
        return totalPaymentValue;
    }

    public void restoreSaveStateParams(Object obj) {
        try {
            // System.out.println("restoreSaveStateParams.init");
            IPeroidCalcInsurDTO peroidCalcInsurDTO = (IPeroidCalcInsurDTO)obj;
            BeanUtils.copyProperties(this, peroidCalcInsurDTO);
            if (catCode != null) {
                filterByCategory(null);
            }
            employeeHelper.setRealCivilId(getRealCivilId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEmpHasInsurrance(boolean empHasInsurrance) {
        this.empHasInsurrance = empHasInsurrance;
    }

    public boolean isEmpHasInsurrance() {
        return empHasInsurrance;
    }

    public void updateTotalPaymentValue(ActionEvent event) {
        totalPaymentValue = new BigDecimal(0);
        if (yearPeroidInstallmentValue != null && installmentValueDK != null) {
            totalPaymentValue =
                    new BigDecimal(yearPeroidInstallmentValue).multiply(installmentValueDK).multiply(new BigDecimal(12));
        }
    }

}
