package com.beshara.csc.hr.scp.integration.presentation.backingbean.empvacsettlmentenquiry;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBaseObject;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredInMinException;
import com.beshara.csc.hr.emp.integration.presentation.utils.EmployeeHelper;
import com.beshara.csc.hr.sal.business.client.ISalEmpSettelmentsClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSettDetailsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSettelmentsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSettelmentsSearchDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalEmpSettelmentsDTO;
import com.beshara.csc.hr.sal.business.entity.ISalEmpSettelmentsEntityKey;
import com.beshara.csc.hr.sal.business.entity.ISalSettlmentStatusEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEmpSettelmentsEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalSettlmentStatusEntityKey;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.hr.sal.business.shared.exception.EmpVacSalCalculated;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.hr.vac.business.entity.IVacEmployeeVacationsEntityKey;
import com.beshara.csc.inf.business.client.IYearsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.exception.EmployeeCivilIdNotExistException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;

import weblogic.ejb20.utils.OrderedSet;


public class EmpVacSettlmentEnquiryHelperBean extends LookUpBaseBean{
    
    @SuppressWarnings("compatibility:8854506677521561139")
    private static final long serialVersionUID = 1L;
    
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
    private boolean dateDisable;
    private String civilId;
    private String civilIdNotReal;
    private boolean empHiredInMin = true;
    private boolean empHired = true;
    private boolean payrollInfoExist = true;
    private String degree;
    private ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO;
    private List months;
    private List years;
    private String month;
    private String year;
    public static final String BEAN_NAME ="empVacSettlmentEnquiryHelperBean";
   
    private static final String RESOURCE_PATH_SCP_BUNDLE = "com.beshara.csc.hr.scp.integration.presentation.resources.integration";
    private static final String RESOURCE_KEY_MONTH_PRE = "month_key_";
    private Double totalSettlmentValue = null;
  //  private boolean enableEmpLovDiv;
   private boolean enableCancelSetlment=false; 
    EmployeeHelper employeeHelper = new EmployeeHelper();
    
    private HtmlDataTable vacTable = new HtmlDataTable();
    private List vacList = new ArrayList();
    private int vacListSize;
    private String vacSelectedRadio = "";
    private ISalEmpSettelmentsDTO selSalEmpSettelmentsDTO = null;
    private Boolean comeFromRetroactiveSettDiv = false;
    private Boolean sharedNavigationBack = false;
    
    public EmpVacSettlmentEnquiryHelperBean() {
        setDivMainContent("divMainContentFourRows");
        setClient(SalClientFactory.getSalEmpSettelmentsClient());
        salEmpSalaryElementsDTO = SalDTOFactory.createSalEmpSalaryElementsDTO();
        if (getEmpListOfValues() == null)
            setEmpListOfValues(new EmployeeListOfValues());
        if (month == null && year == null)
            setCurrentMonthYear();
        initEmpHelper();
    }

    public static EmpVacSettlmentEnquiryHelperBean getInstance() {
        return (EmpVacSettlmentEnquiryHelperBean)JSFHelper.getValue(BEAN_NAME);
    }
    
    public void selectedVacRadioButton(ActionEvent event) throws Exception {

//        if (usingPaging) {
//            getPagingBean().setPreUpdatedDataModel(true);
//        }

        //if (event.getNewValue() != null) {

            IClientDTO selected = (IClientDTO)this.getVacTable().getRowData();
            selSalEmpSettelmentsDTO = (ISalEmpSettelmentsDTO)selected;
            //this.getSelectedDTOS().add(selected);
        //}
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        setCheckAllFlag(false);
        setMyTableData(getVacSettList() );

    }
    
    public String backFromRetroactive() {
        if (sharedNavigationBack) {
            IntegrationBean integrationBean =
                (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
            return integrationBean.goFrom();
        }
        return "";
    }

    public void setCurrentMonthYear() {
        Calendar currentDate = Calendar.getInstance();
        month = Integer.toString(currentDate.get(Calendar.MONTH) + 1);
        year = Integer.toString(currentDate.get(Calendar.YEAR));
        System.out.println(month + "--" + year);
    }
    /**/
     public void setMonths(List months) {
         this.months = months;
     }

     public List getMonths() {
         loadMonths();
         return months;
     }
     public void setYears(List years) {
         this.years = years;
     }

     public List getYears() {
         loadYears();
         return years;
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
    

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();//super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowEmpSrchDiv(true);
        app.setShowSearch(false);
        app.setShowbar(true);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        
        app.setShowpaging(true);
        
        return app;
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

    public void initEmpHelper() {
        employeeHelper.setBackBeanNameFrom(BEAN_NAME );
        employeeHelper.setResetButtonMethod("reSetData");
        employeeHelper.setShowPayrollInfo(true);
    }

    public void filterByCivilId() {
        dateDisable = false;
        civilExist = false;
        if (civilId != null && !civilId.equals("")) {

            empHired = true;
            empHiredInMin = true;
            payrollInfoExist = true;
            // if (GlobalValidation.isValidCivilId(Long.parseLong(civilId))) {
            validCivilId = true;
            IEmployeesDTO dto = null;
            try {
                dto =EmployeeHelper.getHiredAndHavePayrollInfoEmpInMinstry(new Long(civilId), CSCSecurityInfoHelper.getLoggedInMinistryCode());
               // IEmployeesEntityKey empKey = (IEmployeesEntityKey)dto.getCode();
                setCivilIdNotReal(dto.getCode().getKey());

                salEmpSalaryElementsDTO = dto.getSalEmpSalaryElementsDTO();
                       
                ((ISalEmpSalaryElementsDTO)getSalEmpSalaryElementsDTO()).setEmployeesDTO(dto);
                
                setDegree(fillDegree());
                civilExist = true;
            } catch (EmployeeCivilIdNotExistException e) {
                validCivilId = false;
                e.printStackTrace();
            }catch (EmployeeNotHiredInMinException e) {
                empHiredInMin = false;
                e.printStackTrace();
            } catch (EmployeeNotHiredException e) {
                empHired = false;
                e.printStackTrace();
            } catch (EmployeePayrollInfoNotExistException e) {
                payrollInfoExist = false;
                e.printStackTrace();
            } catch (Exception e) {
                empHired = false;
                employeesDTO = EmpDTOFactory.createEmployeesDTO();
                e.printStackTrace();
            }

            //            } else {
            //
            //                validCivilId = false;
            //                civilExist = false;
            //                employeesDTO = EmpDTOFactory.createEmployeesDTO();
            //                //((ISalEmpSalaryElementsDTO)getSalEmpSalaryElementsDTO.()).setEmployeesDTO(EmpDTOFactory.createEmployeesDTO());
            //            }
        }

    }


    /*public void filterByCivilId() {
        dateDisable = false;
        if (civilId != null && !civilId.equals("")) {

            empHired = true;
            payrollInfoExist = true;
            if (GlobalValidation.isValidCivilId(Long.parseLong(civilId))) {
                validCivilId = true;
                civilExist = true;
                IEmployeesDTO dto = null;
                try {
                    dto = EmpClientFactory.getEmployeesClient().getByRealCivilId(new Long(civilId), CSCSecurityInfoHelper.getLoggedInMinistryCode());
                    IEmployeesEntityKey empKey = (IEmployeesEntityKey)dto.getCode();
                    setCivilIdNotReal(dto.getCode().getKey());
                    try {

                        empHired = EmpClientFactory.getEmployeesClient().isEmployeeHired(empKey);

                    } catch (SharedApplicationException e) {
                        empHired = false;
                        civilExist = false;
                        e.printStackTrace();
                    } catch (DataBaseException e) {
                        empHired = false;
                        civilExist = false;
                        e.printStackTrace();
                    } catch (Exception e) {
                        empHired = false;
                        civilExist = false;
                        e.printStackTrace();
                    }

                    if (empHired) {
                        Boolean employeeInMinistry = true;
                        try {
                            employeeInMinistry =
                                    EmpClientFactory.getEmployeesClient().isEmployeeInMinistry(Long.parseLong(civilId),
                                                                                               CSCSecurityInfoHelper.getLoggedInMinistryCode());

                        } catch (DataBaseException e) {
                            e.printStackTrace();
                        }

                        if (employeeInMinistry) {
                            try {
                                System.out.println("Raise Type > "+ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
                                salEmpSalaryElementsDTO =
                                        (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(Long.parseLong(civilId),
                                                                                                                                             ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
                                ((ISalEmpSalaryElementsDTO)getSalEmpSalaryElementsDTO()).setEmployeesDTO((IEmployeesDTO)EmpClientFactory.getEmployeesClient().getEmployeeInfoByElmType(new Long(civilIdNotReal)));
                                 setDegree(fillDegree());
                            } catch (SharedApplicationException e) {
                                civilExist = false;
                                payrollInfoExist = false;
                                e.printStackTrace();
                            } catch (DataBaseException e) {
                                civilExist = false;
                                payrollInfoExist = false;
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e) {
                    empHired = false;
                    civilExist = false;
                    e.printStackTrace();
                } 

            } else {

                validCivilId = false;
                civilExist = false;
                employeesDTO = EmpDTOFactory.createEmployeesDTO();
                //((ISalEmpSalaryElementsDTO)getSalEmpSalaryElementsDTO.()).setEmployeesDTO(EmpDTOFactory.createEmployeesDTO());
            }
        }

    }*/
    
    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

    public void setDateDisable(boolean dateDisable) {
        this.dateDisable = dateDisable;
    }

    public boolean isDateDisable() {
        return dateDisable;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
        return civilId;
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

    public String fillDegree() {
        String degree="";
            if (getSalEmpSalaryElementsDTO() != null && salEmpSalaryElementsDTO.getSalElementGuidesDTO()!=null&& salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFirstParent() !=null) {

                ISalElementGuidesDTO firstParent = null;

                try {
                    firstParent =
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getByCode(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getFirstParent());
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                } catch (DataBaseException e) {
                    e.printStackTrace();
                }

                degree = firstParent.getName();

                degree += " / ";

                degree +=
                        ((((ISalElementGuidesDTO)(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject())).getParentObject())).getName();

                degree += " / ";

                degree += ((salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject())).getName();

            //            degree += " / ";
            //
            //            degree += salEmpSalaryElementsDTO.getSalElementGuidesDTO().getName();
            }

            return degree;
        
        }
    public String getDegree() {      
        return degree;
    }
    
    public void setSalEmpSalaryElementsDTO(ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO) {
        this.salEmpSalaryElementsDTO = salEmpSalaryElementsDTO;
    }

    public ISalEmpSalaryElementsDTO getSalEmpSalaryElementsDTO() {

//        if (salEmpSalaryElementsDTO == null && civilId != null && civilExist) {
//
//            try {
//                //Changed by: Ayman Mahmoud, to solve the problem when the real civil id <> civil id
//                salEmpSalaryElementsDTO =
//                        (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(Long.parseLong(civilId),
//                                                                                                                             ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
//            } catch (DataBaseException e) {
//                System.out.println("Cann't get Raise for employee with civilId: " + civilId);
//                e.printStackTrace();
//            }
//        }


        return salEmpSalaryElementsDTO;
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
    
    
    
    public void getAll() throws DataBaseException {
        selSalEmpSettelmentsDTO = null;
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        setMyTableData(getVacSettList() );
        setVacSelectedRadio("");
        setSelectedRadio("");
        if (isUsingPaging()) {

            setUpdateMyPagedDataModel(true);
            setPagingRequestDTO(  new PagingRequestDTO("getAllWithPaging"));

        } else {

            //setMyTableData(getTotalList());
            setVacList(getTotalList());

            this.reinitializeSort();

            if (getSelectedDTOS() != null) {
                getSelectedDTOS().clear();
            }

            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().clear();
            }

        }

    }

    /**
     * @return
     */
    public List getTotalList() {
        List totalList = new ArrayList();
        totalSettlmentValue = null;
        if (civilExist && year != null && month != null) {
            try {
                ISalEmpSettelmentsSearchDTO searchDTO = SalDTOFactory.createSalEmpSettelmentsSearchDTO();
                searchDTO.setCivilId(Long.valueOf(civilIdNotReal));
                searchDTO.setYearCode(Long.valueOf(year));
                searchDTO.setYearMonth(Long.valueOf(month));
                totalList = ((ISalEmpSettelmentsClient)getClient()).getEmpSettVac_CashReplacement(searchDTO);
                
            } catch (Exception ne) {
                totalList = new ArrayList();
                ne.printStackTrace();
            }
        }
        return totalList;
    }

    public void removeAllVacSett() {
        SharedUtilBean shared_util = getSharedUtil();
        System.out.println("Calling removeAllVacSett Method()...");
        boolean removeSuccess = true;
        ISalEmpSettelmentsSearchDTO searchDTO = SalDTOFactory.createSalEmpSettelmentsSearchDTO();
        searchDTO.setCivilId(Long.valueOf(civilIdNotReal));
        searchDTO.setRealCivilId( ((IEmployeesDTO)getSalEmpSalaryElementsDTO().getEmployeesDTO()).getRealCivilId() );
        searchDTO.setYearCode(Long.valueOf(year));
        searchDTO.setYearMonth(Long.valueOf(month));

        IClientDTO selected = (IClientDTO)this.getVacTable().getRowData();
        ISalEmpSettelmentsDTO selSalEmpSettelmentsDTO = (ISalEmpSettelmentsDTO)selected;
        //this.getSelectedDTOS().add(selected);

        if(selSalEmpSettelmentsDTO != null && selSalEmpSettelmentsDTO.getCode() != null) {
            Long stltrxCode = ((ISalEmpSettelmentsEntityKey)selSalEmpSettelmentsDTO.getCode()).getStltrxCode();
            searchDTO.setStltrxCode(stltrxCode);
            if(selSalEmpSettelmentsDTO.getVacEmployeeVacationsDTO().getCode() != null && selSalEmpSettelmentsDTO.getVacEmployeeVacationsDTO().getCode() != null
                && selSalEmpSettelmentsDTO.getVacEmployeeVacationsDTO().getCode()!= null)
                searchDTO.setEmpVacCode(((IVacEmployeeVacationsEntityKey)selSalEmpSettelmentsDTO.getVacEmployeeVacationsDTO().getCode()).getEmpvacCode() );
        }
        try {
            removeSuccess = SalClientFactory.getSalEmpSettelmentsClient().removeAllVacSett(searchDTO );
            if(removeSuccess){
                selSalEmpSettelmentsDTO = null;
                setSelectedDTOS(new ArrayList<IBasicDTO>());
                setMyTableData(getVacSettList() );
                getAll();
                setSuccess(true);
                shared_util.setSuccessMsgValue("Deleted");
            }else {
                setShowErrorMsg(true);
                shared_util.setErrMsgValue(shared_util.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "delete_error"));
            }
            
        } catch (DataBaseException e) {e.printStackTrace();
            if(e.getMessage() != null && e.getMessage().equalsIgnoreCase("EntityHasRelation") ){
                setShowErrorMsg(true);
                shared_util.setErrMsgValue(shared_util.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "retroactive_vac_Salary_Calc_del_err_Msg"));
                }else{
                    shared_util.handleException(e);
                }
        } catch (SharedApplicationException e) {
            if(e instanceof EmpVacSalCalculated ){
                setShowErrorMsg(true);
                shared_util.setErrMsgValue(shared_util.messageLocator(RESOURCE_PATH_SCP_BUNDLE, "retroactive_vac_Salary_Calc_del_err_Msg"));
            }
            else shared_util.handleException(e);
            e.printStackTrace();
        }
    }

    public List getVacSettList() {
        List totalList = new ArrayList();
        totalSettlmentValue = null;
        if (civilExist && year != null && month != null) {
            try {
                ISalEmpSettelmentsSearchDTO searchDTO = SalDTOFactory.createSalEmpSettelmentsSearchDTO();
                searchDTO.setCivilId(Long.valueOf(civilIdNotReal));
                searchDTO.setYearCode(Long.valueOf(year));
                searchDTO.setYearMonth(Long.valueOf(month));
                if(selSalEmpSettelmentsDTO != null){
                    Long stltrxCode = ((ISalEmpSettelmentsEntityKey)selSalEmpSettelmentsDTO.getCode()).getStltrxCode();
                    searchDTO.setStltrxCode(stltrxCode);
                    totalList = ((ISalEmpSettelmentsClient)getClient()).searchEmpVacSettlment(searchDTO);
                }
                
                if(totalList != null && totalList.size() > 0) {
                    Long status = null;
                    totalSettlmentValue = 0D;
                    ISalEmpSettDetailsDTO salEmpSettDetailsDTO = null;
                    for (int i = 0; i < totalList.size() ; i++) {
                        salEmpSettDetailsDTO = (ISalEmpSettDetailsDTO)totalList.get(i);
                        status = ((SalSettlmentStatusEntityKey)salEmpSettDetailsDTO.getSalEmpSettelmentsDTO().getSalSettlmentStatusDTO().getCode()).getStlstatusCode();
                        if(status != null && status.equals(IScpConstants.SCP_SETT_SIGNED) ){
                            totalSettlmentValue +=  salEmpSettDetailsDTO.getValue();
                        }
                        System.out.println("getTotalList i = "+i + " , salEmpSettDetailsDTO.getValue() = " + salEmpSettDetailsDTO.getValue() );
                    }
                    System.out.println("getTotalList totalList.size() = "+totalList.size() + " , totalSettlmentValue = " + totalSettlmentValue);

                }
                
    //                try {
    //                    totalSettlmentValue =
    //                            SalClientFactory.getSalEmpSettelmentsClient().getEmpTotalSettelementsByMonthAndYear(Long.valueOf(civilIdNotReal),
    //                                                                                                                Long.valueOf(year),
    //                                                                                                                Long.valueOf(month));
    //                } catch (Exception e) {
    //                    e.printStackTrace();
    //                }
            } catch (Exception ne) {
                totalList = new ArrayList();
                ne.printStackTrace();
            }
        }
        return totalList;
    }
    
    public void reSetData(){
       reSetData(null);
    }
    
    public void reSetData(ActionEvent ae) {
        ae = null;
        dateDisable = false;
        setSalEmpSalaryElementsDTO(SalDTOFactory.createSalEmpSalaryElementsDTO());
        setCivilId(null);
        civilExist = false;
        empHired = true;
        empHiredInMin = true;
        setMyTableData(new ArrayList());
        setVacList(new ArrayList());
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        setSelSalEmpSettelmentsDTO(null);
        getHighlightedDTOS().clear();
        setErrorMsg(null);
        setCurrentMonthYear();
        degree="";
        totalSettlmentValue=null;
        
    }
    public void showSearchForEmployeeDiv() {
        dateDisable = false;
        EmployeeListOfValues empListOfValuesBean = ((EmployeeListOfValues)getEmpListOfValues());
        empListOfValuesBean.setEmpListOfValuesStyle("m2mAddDivClass");
        empListOfValuesBean.setReturnMethodName(BEAN_NAME + ".returnMethodAction");
        empListOfValuesBean.resetData();
        empListOfValuesBean.getOkCommandButton().setReRender("cnt1Panel,scriptpanel,OperationBar,dataT_data_panel,paging_panel,displayBtnPanel");
    }

    public void returnMethodAction() {
        if (getEmpListOfValues().getSelectedDTOS() != null && getEmpListOfValues().getSelectedDTOS().get(0) != null &&
            getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
            validCivilId = true;
            civilExist = true;
            empHired = true;
            empHiredInMin = true;
            IEmployeesDTO employeesDTO = (IEmployeesDTO)getEmpListOfValues().getSelectedDTOS().get(0);
            //civilId = ((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId().toString();
            civilId = employeesDTO.getRealCivilId().toString();
            employeeHelper.setRealCivilId(civilId);
            filterByCivilId();
           
        }
    }

    public void setTotalSettlmentValue(Double totalSettlmentValue) {
        this.totalSettlmentValue = totalSettlmentValue;
    }

    public Double getTotalSettlmentValue() {
        return totalSettlmentValue;
    }
    
   


    public void setEnableCancelSetlment(boolean enableCancelSetlment) {
        this.enableCancelSetlment = enableCancelSetlment;
    }

    public boolean isEnableCancelSetlment() {
        return enableCancelSetlment;
    }

//    public void selectedCheckboxs(ActionEvent event) throws Exception {
//        super.selectedCheckboxs(event);
//        checkStatus();
//    }

    
    
    public boolean checkStatus(){
        
            enableCancelSetlment = false;
            
            List list = getSelectedDTOS();
            for (ISalEmpSettDetailsDTO dto : (List<ISalEmpSettDetailsDTO>)list) {
                Long status =
                    ((ISalSettlmentStatusEntityKey)dto.getSalEmpSettelmentsDTO().getSalSettlmentStatusDTO().getCode()).getStlstatusCode();
                if (status.equals(IScpConstants.SETTLMENT_STATUS)) {
                    enableCancelSetlment = true;

                } else {
                    enableCancelSetlment = false;
                    return false;
                  
                }
            }
            return enableCancelSetlment;
        }
    
    @Override
    public void selectedCheckboxsAll(ActionEvent event) throws Exception {

        try {

            Set<IBasicDTO> selectedSet = new OrderedSet();
            selectedSet.addAll(getSelectedDTOS());

            Integer rowsCountPerPage = 300;
                //(Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());

            if (rowsCountPerPage == null) {

                throw new Exception("#{shared_util.noOfTableRows} return null");

            }

            int first = this.getMyDataTable().getFirst();

            for (int j = first; j < first + rowsCountPerPage.intValue(); j++) {

                this.getMyDataTable().setRowIndex(j);

                if (!this.getMyDataTable().isRowAvailable()) {
                    break;
                }

                IBasicDTO selected = (IBasicDTO)this.getMyDataTable().getRowData();

                if (this.isCheckAllFlag()) {

                    try {
                        selectedSet.add(selected);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    for (IBasicDTO item : selectedSet) {
                        if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                            selectedSet.remove(item);
                            break;
                        }
                    }

                }

            }

            getSelectedDTOS().clear();
            getSelectedDTOS().addAll(selectedSet);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void openCancelSettlment(){
            checkStatus();
        List<IBasicDTO> dTOS = getSelectedDTOS();
        if (!enableCancelSetlment) {
                getSharedUtil().handleException(new Exception(),RESOURCE_PATH_SCP_BUNDLE,"settlment_elem_status_invalid_msg");
           
                    return;
            }else{
                 setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.delAlert);");
            }
        }

//    public void selectedCheckboxsAll(ActionEvent event) throws Exception {
//
//         super.selectedCheckboxsAll(event);
//         checkStatus();
//    }

    public void delete() throws DataBaseException, ItemNotFoundException {
       // SharedUtilBean shared_util = getSharedUtil();
        System.out.println("Calling Cancel Settlment Method()...");
        boolean cancelSuccess = true;
        if (getSelectedDTOS() == null)
            setSelectedDTOS(loadSelectedDTOS());

        try {
            setDeleteStatusDTOS( SalClientFactory.getSalEmpSettDetailsClient().cancelSettlment(getSelectedDTOS()));
            for (IBaseObject dto : getDeleteStatusDTOS() ) {
                IResultDTO resultDTO = (ResultDTO)dto;
                if(! ISystemConstant.ITEM_UPDATED.equals(resultDTO.getStatus() ) ){
                    cancelSuccess = false;   
                }
            }

            if (getMyDataTable() != null && getMyDataTable().getRowCount() != 0) {
                getMyDataTable().setFirst(0);
            }
        }  catch (Exception e) {
    
            e.printStackTrace();
        }
        this.getSelectedDTOS().clear();
        setCheckAllFlag(false);
        //cancelSearch();
        
        restoreTablePosition();
        setMyTableData(getVacSettList() );
        
        if(cancelSuccess) { /* check if all child sett are cancelled , cancel their header also*/
            boolean allChildsCanceeled = true;
            for (int i = 0; i < getMyTableData().size() ; i++) {
                ISalEmpSettDetailsDTO salEmpSettDetailsDTO = (ISalEmpSettDetailsDTO)getMyTableData().get(i);
                Long status =
                    ((ISalSettlmentStatusEntityKey)salEmpSettDetailsDTO.getSalEmpSettelmentsDTO().getSalSettlmentStatusDTO().getCode()).getStlstatusCode();
                if(!status.equals(IScpConstants.CANCEL_SETTLMENT_STATUS)){allChildsCanceeled = false;break;}
            }
            if(allChildsCanceeled){/* all child sett are cancelled , cancel their header also*/
                SalEmpSettelmentsDTO salEmpSettelmentsDTO = (SalEmpSettelmentsDTO) (( ISalEmpSettDetailsDTO)getMyTableData().get(0) ).getSalEmpSettelmentsDTO();
                Long code = ((SalEmpSettelmentsEntityKey)salEmpSettelmentsDTO.getCode()).getStltrxCode();
                try {
                    SalClientFactory.getSalEmpSettelmentsClient().cancelHeaderSett(salEmpSettelmentsDTO);
                } catch (DataBaseException dbe) {
                    // TODO: Add catch code
                    dbe.printStackTrace();
                } catch (SharedApplicationException sae) {
                    // TODO: Add catch code
                    sae.printStackTrace();
                }
            }
        }

    }

    public void setEmployeeHelper(EmployeeHelper employeeHelper) {
        this.employeeHelper = employeeHelper;
    }

    public EmployeeHelper getEmployeeHelper() {
        return employeeHelper;
    }

    public void setVacTable(HtmlDataTable vacTable) {
        this.vacTable = vacTable;
    }

    public HtmlDataTable getVacTable() {
        return vacTable;
    }

    public void setVacList(List vacList) {
        this.vacList = vacList;
    }

    public List getVacList() {
        return vacList;
    }

    public void setVacListSize(int vacListSize) {
        this.vacListSize = vacListSize;
    }

    public int getVacListSize() {
        vacListSize = 0;
        if(vacList != null)vacListSize = vacList.size();
        return vacListSize;
    }

    public void setVacSelectedRadio(String vacSelectedRadio) {
        this.vacSelectedRadio = vacSelectedRadio;
    }

    public String getVacSelectedRadio() {
        return vacSelectedRadio;
    }

    public void setSelSalEmpSettelmentsDTO(ISalEmpSettelmentsDTO selSalEmpSettelmentsDTO) {
        this.selSalEmpSettelmentsDTO = selSalEmpSettelmentsDTO;
    }

    public ISalEmpSettelmentsDTO getSelSalEmpSettelmentsDTO() {
        return selSalEmpSettelmentsDTO;
    }

    public void setComeFromRetroactiveSettDiv(Boolean comeFromRetroactiveSettDiv) {
        this.comeFromRetroactiveSettDiv = comeFromRetroactiveSettDiv;
    }

    public Boolean getComeFromRetroactiveSettDiv() {
        return comeFromRetroactiveSettDiv;
    }

    public void setSharedNavigationBack(Boolean sharedNavigationBack) {
        this.sharedNavigationBack = sharedNavigationBack;
    }

    public Boolean getSharedNavigationBack() {
        return sharedNavigationBack;
    }
    
    public void setEmpHiredInMin(boolean empHiredInMin) {
        this.empHiredInMin = empHiredInMin;
    }

    public boolean isEmpHiredInMin() {
        return empHiredInMin;
    }
    
}
