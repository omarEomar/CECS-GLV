package com.beshara.csc.hr.scp.integration.presentation.backingbean.exchangerequestform;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredInMinException;
import com.beshara.csc.hr.emp.integration.presentation.utils.EmployeeHelper;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.shared.ISALConstants;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.inf.business.exception.EmployeeCivilIdNotExistException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class ExchangeRequestForm extends LookUpBaseBean {

    private static final String REPORT_PARAM_HOLDER = "#p#";
    private static final String EXCHANGE_REQUEST_REPORT_CODE = "1039";
    private String reportUrlLink;
    private IEmployeesDTO employeeDTO;
    private Long realCivilId;
    private boolean civilExist = false;
    private boolean validCivilId = true;
    private String civilIdNotReal;
    private boolean empHired = true;
    private boolean empHiredInMin = true;
    private boolean payrollInfoExist = true;
    private String degree;
    EmployeeHelper employeeHelper = new EmployeeHelper();
    public static final String BEAN_NAME = "exchangeRequestFormBean";
    private static final String SEARCH_BY_CODE = "0";
    private static final String SEARCH_BY_NAME = "1";
    private String law_permit;
    private Float exchangeValue;

    private Long year;
    private Long month;
    private Long day;


    public ExchangeRequestForm() {
        setPageDTO(SalDTOFactory.createSalarySheetsSearchCriteriaDTO());
        setClient(SalClientFactory.getSalSalarySheetsClient());
        setSelectedPageDTO(SalDTOFactory.createSalSalarySheetsDTO());
        setLaw_permit("1");
        if (getEmpListOfValues() == null)
            setEmpListOfValues(new EmployeeListOfValues());
        initEmpHelper();
    }


    @Override
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowContent1(true);
        app.setShowEmpSrchDiv(true);
        app.setShowbar(false);
        //         app.setShowpaging(false);
        //         app.setShowCommonData(true);
        //         app.setShowshortCut(true);
        app.setShowSearch(false);

        return app;
    }


    public void getAll() {


    }

    public void changeExchangeValueWay(ActionEvent ae) {
        exchangeValue = null;
    }

    public void printExchangeRequestForm() {
        reportUrlLink = ISALConstants.PRINT_EXCHANGE_REQUEST_LINK;
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, EXCHANGE_REQUEST_REPORT_CODE);
        if (year != null)
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, year.toString());
        else
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, "");

        if (month != null)
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, month.toString());
        else
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, "");

        if (day != null)
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, day.toString());
        else
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, "");

        if (exchangeValue != null)
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, exchangeValue.toString());
        else
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, "");

        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, realCivilId.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        System.out.println("generated params -----> " + reportUrlLink);

    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setCivilExist(boolean civilExist) {
        this.civilExist = civilExist;
    }

    public boolean isCivilExist() {
        return civilExist;
    }

    public void setValidCivilId(boolean validCivilId) {
        this.validCivilId = validCivilId;
    }

    public boolean isValidCivilId() {
        return validCivilId;
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

    public void setEmpHiredInMin(boolean empHiredInMin) {
        this.empHiredInMin = empHiredInMin;
    }

    public boolean isEmpHiredInMin() {
        return empHiredInMin;
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

    private void resetMessages() {
        empHired = true;
        empHiredInMin = true;
        payrollInfoExist = true;
        validCivilId = true;
        civilExist = false;
        year = null;
        month = null;
        day = null;
        setExchangeValue(null);
        setLaw_permit("1");
    }

    public void initEmpHelper() {
        employeeHelper.setBackBeanNameFrom(BEAN_NAME);
        employeeHelper.setResetButtonMethod("reSetData");
    }


    public void filterByCivilId() {
        resetMessages();
        //        if (civilId != null && !civilId.equals("")) {

        IEmployeesDTO dto = null;
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

            dto = employeeHelper.getLastActiveEmp(Long.valueOf(realCivilId));
            if (dto != null) {
                setEmployeeDTO(dto);
                validCivilId = true;
                civilExist = true;
                setCivilIdNotReal(dto.getCode().getKey());

                //                            (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(Long.parseLong(civilId),
                //                                                                                                                                 ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);

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
        empListOfValuesBean.setSearchMethod("exchangeRequestFormBean.searchEmpLovDiv");
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
        setRealCivilId(null);
        civilExist = false;
        validCivilId = true;
        empHired = true;
        empHiredInMin = true;
        //        setMyTableData(new ArrayList());
        //        setSelectedDTOS(new ArrayList<IBasicDTO>());
        //        getHighlightedDTOS().clear();
        setErrorMsg(null);
        //        setSelectedRadio("");

    }

    public void setEmployeeDTO(IEmployeesDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public IEmployeesDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeHelper(EmployeeHelper employeeHelper) {
        this.employeeHelper = employeeHelper;
    }

    public EmployeeHelper getEmployeeHelper() {
        return employeeHelper;
    }

    public void setLaw_permit(String law_permit) {
        this.law_permit = law_permit;
    }

    public String getLaw_permit() {
        return law_permit;
    }

    public void setExchangeValue(Float exchangeValue) {
        this.exchangeValue = exchangeValue;
    }

    public Float getExchangeValue() {
        return exchangeValue;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getYear() {
        return year;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getMonth() {
        return month;
    }


    public void setDay(Long day) {
        this.day = day;
    }

    public Long getDay() {
        return day;
    }

    public void setReportUrlLink(String reportUrlLink) {
        this.reportUrlLink = reportUrlLink;
    }

    public String getReportUrlLink() {
        return reportUrlLink;
    }
}
