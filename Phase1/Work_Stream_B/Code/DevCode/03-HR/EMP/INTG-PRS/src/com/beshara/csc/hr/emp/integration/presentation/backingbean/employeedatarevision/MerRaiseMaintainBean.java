package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredInMinException;
import com.beshara.csc.hr.emp.integration.presentation.utils.EmployeeHelper;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.shared.IMerConstant;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.exception.EmployeeCivilIdNotExistException;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBeanLocal;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.util.wizardbar2.state.WizardInfo;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class MerRaiseMaintainBean  extends LookUpBaseBeanLocal{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

   




    private static final Long MINISTRY_CODE = 13L;
    private static final String SEARCH_BY_CODE = "0";
    private static final String SEARCH_BY_NAME = "1";
    private boolean civilExist;
    private boolean femaleGender;
    private String civilId;
    private boolean validCivilId = true;
    private boolean empHired = true;
    private boolean empValidNatSal = true;
    private boolean payrollInfoExist = true;
    private String degree;
    private IEmployeesDTO employeesDTO;
    private boolean enableEmpLovDiv;
    private boolean showPaySlipPanel = false;
    private String detailFilterMethod = null;
    private String detailResetMethod = null;
    private boolean notDefinedGenderTypeFlag;
    private boolean maritalStatusSingle;
    private static final int MARITAL_STATUS_MARRIED = 2;
    private Boolean kidsBeanFlag = false;
    private boolean empHiredInMin = true;

    public MerRaiseMaintainBean() {
        setMyTableData(new ArrayList());
        setPageDTO(SalDTOFactory.createSalEmpSalaryElementsDTO());
        setClient(SalClientFactory.getSalEmpSalaryElementsClient());
        employeesDTO = EmpDTOFactory.createEmployeesDTO();
        setEmpListOfValues(new EmployeeListOfValues());
        setContent1Div("module_tabs_cont1");
        if (employeesDTO.getCitizensResidentsDTO() == null) {
            IKwtCitizensResidentsDTO dto = InfDTOFactory.createKwtCitizensResidentsDTO();
            dto.setGenderTypesDTO(InfDTOFactory.createGenderTypesDTO());
            dto.setMaritalStatusDTO(InfDTOFactory.createMaritalStatusDTO());
            employeesDTO.setCitizensResidentsDTO((IKwtCitizensResidentsDTO)dto);
        }
        if (employeesDTO.getWorkCenterDTO() == null) {
            employeesDTO.setWorkCenterDTO(new BasicDTO());
        }
        if (employeesDTO.getJobDTO() == null) {
            employeesDTO.setJobDTO(JobDTOFactory.createJobsDTO());
        }

    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        // app.showAddeditPage ( ) ;
        // app.setShowPagedTreediv ( true ) ;
        // app.setShowScirptGenerator ( true ) ;
        // app.setShowdatatableContent ( true ) ;
        app.setShowContent1(true);
        app.setShowLookupAdd(true);
        app.setShowEmpSrchDiv(true);
        app.setShowScirptGenerator(true);
        app.setShowWizardBar(true);
        return app;
    }

    public void showSearchForEmployeeDiv() {
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        employeeListOfValuesBean.setReturnMethodName("merRaiseMaintainBean.returnMethodAction");
        employeeListOfValuesBean.setSearchMethod("merRaiseMaintainBean.searchEmpLovDiv");
        employeeListOfValuesBean.getOkCommandButton().setReRender("mainDataEmpPanel , scriptGenerator , displayBtnPanel , dataT_data_panel , OperationBar , paging_panel");
        employeeListOfValuesBean.resetData();
        enableEmpLovDiv = true;
    }

    public String searchEmpLovDiv(Object searchType,
                                  Object searchQuery) { // evaluateValueBinding ( detailBeanName ) I.I
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        try {
            IEmpEmployeeSearchDTO searchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
            searchDTO.setEmployment(true);
            if (searchType.toString().equals(SEARCH_BY_CODE)) {
                searchDTO.setCivilId(Long.valueOf((String)searchQuery));
            } else if (searchType.toString().equals(SEARCH_BY_NAME)) {
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

    /*public void selectedRadioButton(ValueChangeEvent event) throws Exception {
            super.selectedRadioButton(event );
        }*/

    public void returnMethodAction() {
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        List<IBasicDTO> selectedDTOsList = employeeListOfValuesBean.getSelectedDTOS();
        if (selectedDTOsList != null && selectedDTOsList.get(0) != null && selectedDTOsList.get(0).getCode() != null) {
            setCivilId(((IEmployeesDTO)selectedDTOsList.get(0)).getRealCivilId().toString());
            filterByCivilId();
        }
        enableEmpLovDiv = false;
    }

    private void resetMessages() {
        empHired = true;
        empHiredInMin = true;
        payrollInfoExist = true;
        empValidNatSal = true;
        validCivilId = true;
        civilExist = false;
    }

    public void filterByCivilId() {
        showPaySlipPanel = false;
        resetMessages();
        if (civilId != null) {
            try {
                employeesDTO = EmployeeHelper.getHiredAndHavePayrollInfoEmp(Long.valueOf(civilId));
                //employeesDTO = EmployeeHelper.getLastActiveEmp(Long.valueOf(civilId));
                

                IEmployeesEntityKey empKey = (IEmployeesEntityKey)employeesDTO.getCode();
                Long genderType = ((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getGentypeCode();
                if (genderType.equals(1L)) {
                    femaleGender = true;
                    //                    employeesDTO = EmpDTOFactory.createEmployeesDTO();
                    //                    return;
                }
                Long empNationality =
                    ((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getNationality();
                if (!empNationality.equals(IMerConstant.EMP_NAT_KWT)) {
                    empValidNatSal = false;
                    civilExist = false;
                    employeesDTO = EmpDTOFactory.createEmployeesDTO();
                    return;
                }
                /* updated by Aly Nour as salDecFlag come from EmployeeHelper and no need to hit DB again */
                Long salDecFlag = employeesDTO.getSalEmpSalaryElementsDTO().getSalElementGuidesDTO().getSalByDecesionFlag();
                    //SalClientFactory.getSalEmpSalaryElementsClient().checkDecFlagByCivilAndType(empKey.getCivilId());
                if (salDecFlag != null && salDecFlag != 0) {
                    empValidNatSal = false;
                    civilExist = false;
                    employeesDTO = EmpDTOFactory.createEmployeesDTO();
                    return;
                }

                degree = getFullDegreeName(employeesDTO.getSalEmpSalaryElementsDTO(), Long.valueOf(this.getCivilId()));


                //get Marital status
                maritalStatusSingle =
                        (Integer.parseInt(((IKwtCitizensResidentsDTO)((IEmployeesDTO)employeesDTO).getCitizensResidentsDTO()).getMaritalStatusDTO().getCode().getKey().toString()) !=
                         MARITAL_STATUS_MARRIED);
               /* CSC-19300
                MaritalStatus maritalStatus = (MaritalStatus)evaluateValueBinding("maritalStatusBean");
                maritalStatus.setMaritalStatusSingle(maritalStatusSingle);
                */ 
                civilExist = true;
                validCivilId = true;

            } catch (EmployeeNotHiredInMinException e) {
                empHiredInMin = false;
                employeesDTO = EmpDTOFactory.createEmployeesDTO();
            } catch (EmployeeNotHiredException e) {
                empHired = false;
                employeesDTO = EmpDTOFactory.createEmployeesDTO();
            } catch (EmployeePayrollInfoNotExistException e) {
                payrollInfoExist = false;
                employeesDTO = EmpDTOFactory.createEmployeesDTO();
            } catch (EmployeeCivilIdNotExistException e) {
                civilExist = false;
                validCivilId = false;
                employeesDTO = EmpDTOFactory.createEmployeesDTO();
            } catch (Exception e) {
                civilExist = false;
                validCivilId = false;
                employeesDTO = EmpDTOFactory.createEmployeesDTO();
                e.printStackTrace();
            }


        }

        if (employeesDTO != null) {
            String detailMethodId = getDetailFilterMethod();
            if (detailMethodId != null && !detailMethodId.equals(""))
                executeMethodBinding(detailMethodId, null);
            //handle not defined gender type
            try {

                if (((IKwtCitizensResidentsDTO)((IEmployeesDTO)getEmployeesDTO()).getCitizensResidentsDTO()).getGenderTypesDTO().getCode().getKey().toString().equals("3")) {
                    setNotDefinedGenderTypeFlag(true);
                } else {
                    setNotDefinedGenderTypeFlag(false);
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }

    public void reSetData(ActionEvent event) {
        showPaySlipPanel = false;
        employeesDTO = EmpDTOFactory.createEmployeesDTO();
        if (employeesDTO.getCitizensResidentsDTO() == null) {
            IKwtCitizensResidentsDTO dto = InfDTOFactory.createKwtCitizensResidentsDTO();
            dto.setGenderTypesDTO(InfDTOFactory.createGenderTypesDTO());
            dto.setMaritalStatusDTO(InfDTOFactory.createMaritalStatusDTO());
            employeesDTO.setCitizensResidentsDTO((IKwtCitizensResidentsDTO)dto);
        }
        if (employeesDTO.getWorkCenterDTO() == null) {
            employeesDTO.setWorkCenterDTO(new BasicDTO());
        }
        if (employeesDTO.getJobDTO() == null) {
            employeesDTO.setJobDTO(JobDTOFactory.createJobsDTO());
        }

        degree = null;
        civilId = null;
        empHired = true;
        empHiredInMin = true;
        payrollInfoExist = true;
        enableEmpLovDiv = false;
        empValidNatSal = true;
        validCivilId = true;
        civilExist = false;
        femaleGender = false;
        setMyTableData(new ArrayList());
        String resetDataDetail = getDetailResetMethod();
        if (resetDataDetail != null && !resetDataDetail.equals(""))
            executeMethodBinding(resetDataDetail, null);
        setNotDefinedGenderTypeFlag(false);
        maritalStatusSingle = false;
        /* CSC-19300
        MaritalStatus maritalStatus = (MaritalStatus)evaluateValueBinding("maritalStatusBean");
        maritalStatus.setMaritalStatusSingle(false);
*/

        //        RaiseValueBean raiseValue = (RaiseValueBean)evaluateValueBinding("raiseValueBean");
        //        raiseValue.setTotalRaiseChildren(null);
        //        raiseValue.setTotalRaiseSocialChildren(null);
    }


    public String getFullDegreeName(ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO,
                                    Long civilID) throws DataBaseException, SharedApplicationException {
        //return SalClientFactory.getSalElementGuidesClient().getFullDegreeName(civilID);

        String degree = null;
        ISalElementGuidesDTO firstParent = null; // is cader object
        if (salEmpSalaryElementsDTO != null) {
            firstParent =
                    (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getByCode(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFirstParent());
            degree = firstParent.getName();
            degree += " / ";
            degree +=
                    ((((ISalElementGuidesDTO)(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject())).getParentObject())).getName();
            degree += " / ";
            degree += ((salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject())).getName();
            //            degree += " / ";
            //            degree += salEmpSalaryElementsDTO.getSalElementGuidesDTO().getName();

        }
        //        salEmpSalaryElementsDTO.getSalElementGuidesDTO().setFullName(degree);


        return degree;
    }


    public void setCivilExist(boolean civilExist) {
        this.civilExist = civilExist;
    }

    public boolean getCivilExist() {
        return civilExist;
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

    public boolean isCivilExist() {
        return civilExist;
    }

    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

    public void setEnableEmpLovDiv(boolean enableEmpLovDiv) {
        this.enableEmpLovDiv = enableEmpLovDiv;
    }

    public boolean isEnableEmpLovDiv() {
        return enableEmpLovDiv;
    }

    public void setShowPaySlipPanel(boolean showPaySlipPanel) {
        this.showPaySlipPanel = showPaySlipPanel;
    }

    public boolean isShowPaySlipPanel() {
        return showPaySlipPanel;
    }

    public void setDetailFilterMethod(String detailFilterMethod) {
        this.detailFilterMethod = detailFilterMethod;
    }

    public String getDetailFilterMethod() {
        return detailFilterMethod;
    }

    public void navigateSteps(WizardInfo wizardInfo) {
        System.out.println("sfds");
        if (wizardInfo.getTargetStep() != null && wizardInfo.getTargetStep().equals("step1"))
            executeMethodBinding("raiseValueBean.filterByCivilId", null);
        else if (wizardInfo.getTargetStep() != null && wizardInfo.getTargetStep().equals("step2"))
            executeMethodBinding("raiseWivesBean.filterByCivilId", null);
        else if (wizardInfo.getTargetStep() != null && wizardInfo.getTargetStep().equals("step3"))
            executeMethodBinding("raiseKidsBean.filterByCivilId", null);
    }

    public void setDetailResetMethod(String detailResetMethod) {
        this.detailResetMethod = detailResetMethod;
    }

    public String getDetailResetMethod() {
        return detailResetMethod;
    }

    public void setNotDefinedGenderTypeFlag(boolean notDefinedGenderTypeFlag) {
        this.notDefinedGenderTypeFlag = notDefinedGenderTypeFlag;
    }

    public boolean isNotDefinedGenderTypeFlag() {
        return notDefinedGenderTypeFlag;
    }

    public void setMaritalStatusSingle(boolean maritalStatus) {
        this.maritalStatusSingle = maritalStatus;
    }

    public boolean isMaritalStatusSingle() {
        return maritalStatusSingle;
    }

    public void setKidsBeanFlag(Boolean kidsBeanFlag) {
        this.kidsBeanFlag = kidsBeanFlag;
    }

    public Boolean getKidsBeanFlag() {
        return kidsBeanFlag;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setEmpValidNatSal(boolean empValidNatSal) {
        this.empValidNatSal = empValidNatSal;
    }

    public boolean isEmpValidNatSal() {
        return empValidNatSal;
    }

    public void setEmpHiredInMin(boolean empHiredInMin) {
        this.empHiredInMin = empHiredInMin;
    }

    public boolean isEmpHiredInMin() {
        return empHiredInMin;
    }

    public void setFemaleGender(boolean femaleGender) {
        this.femaleGender = femaleGender;
    }

    public boolean isFemaleGender() {
        return femaleGender;
    }
    
    public static MerRaiseMaintainBean getInstance() {
        return (MerRaiseMaintainBean)JSFHelper.getValue("merRaiseMaintainBean");
    }

}
