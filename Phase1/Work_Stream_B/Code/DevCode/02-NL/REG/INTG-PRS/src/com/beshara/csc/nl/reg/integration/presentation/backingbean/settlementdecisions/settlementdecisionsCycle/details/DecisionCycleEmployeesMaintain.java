package com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.details;


//import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.AbstractDecisionListBean;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredInMinException;
import com.beshara.csc.hr.emp.integration.presentation.utils.EmployeeHelper;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.exception.EmployeeCivilIdNotExistException;
import com.beshara.csc.integration.hrm.emp.backingbean.EmployeeSearchBean;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.IEmpDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.DecisionCycleMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulation.details.RegulationModulesMaintain;
import com.beshara.csc.nl.reg.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;


public class DecisionCycleEmployeesMaintain extends ManyToManyDetailsMaintain {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    
    private static final String BUNDLE = "com.beshara.csc.nl.reg.integration.presentation.resources.integration";
    //    private static final Long KUWITY = 1L;
    //    private static final Long NON_KUWITY = 0L;
    //    private static final Long NATIONLALTY_KUWITY =
    //        ISystemConstant.KUWAIT_NATIONALITY;
    //    private Long added = ISystemConstant.ADDED_ITEM;
    //    private Long kuwityType;
    //    private IEmployeeSearchDTO employeeSearchDTO =
    //        EmpDTOFactory.createEmployeeSearchDTO();

    //  private HtmlDataScroller dataScroller;
    //   private Long itemSelectionRequiredValue = ISystemConstant.VIRTUAL_VALUE;
    private boolean cancelDecisionMode;
    private boolean isSearchMode;
    private boolean showErrMsg;
    //  private List<IBasicDTO> elementGuideList = new ArrayList<IBasicDTO>();
    //  private String selectedElementCode;
    //   private Long valueNumber;
    private IEmpDecisionsDTO empDecisionsDTOSlelected = RegDTOFactory.createEmpDecisionsDTO();
    private List<IEmpDecisionsDTO> empDecisionsDTOSlelectedList = new ArrayList<IEmpDecisionsDTO>();
    private boolean addEmpDecisionsDTOModule = true;
    //private boolean deleteIEmpDecisionsDTO = true; // remove it we will handle deletion with another way added by nora
    //    RegulationModulesMaintain regulationModulesMaintainBean =
    //        (RegulationModulesMaintain)evaluateValueBinding("regulationModulesMaintainBean");
    //    AdvanceEmpSearch advanceEmployeesAddBean =
    //        (AdvanceEmpSearch)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
    //                                                                                                "specialExtraEmpDecisionCycleAddBean" +
    //                                                                                                "}").getValue(FacesContext.getCurrentInstance());

    //   private boolean showBarMainData =
    //      true; // added by nora for handling integeration to show /hide operationBar
    //  private List unDeleteEmpList = new ArrayList();
    //    private Long valueNum;
    //    private String valueChr;
    //    private float felsValue ;
    //    private float denarValue;
    //    private Date valueDate;
    //    private Long empInfTypeCode;
    //    private Boolean booleanInformEmpFlag;
    //    private Long informEmpFlag;
    //    private Date informEmpDate;
    //    private String notes;
    //  private List<IBasicDTO> informTypeList = new ArrayList<IBasicDTO>();
    // private boolean activeValidationOnAdd = false;
    // private boolean activeValidationOnEdit = false;
    //    /////////////////////////////////////////////////////////////////////////////////////
    private Long civilID;
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;
    private IEmpEmployeeSearchDTO empEmployeeSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
    private EmployeeSearchBean employeeSearchBean;
    private boolean addedEmpSuccessfully;
    //   private int countOfAllEmpDecision = 0;
    //  private static final String BEAN_NAME = "settlementDecisionCycleEmployeesMaintainBean";
    private String civil_exist;
    //    private String elementGuideKey  ;
    //    private boolean showElementGuideErrMsg;
    private String empName;
   
    IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
    private boolean disapleDetailsButton = true;
    private boolean showPagingBar;
    private boolean resetButton;

 
  
    DecisionCycleMaintainBean decisionMaintainBean;
 
   
    

    private boolean empHired = true;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private boolean payrollInfoExist = true;
    private boolean empHiredInMin = true;
    private Boolean empAddedBefore=false;
  
                                                 
  
    /*CSC-15730 A.Nour*/

    public DecisionCycleEmployeesMaintain() {

        setClient(RegClientFactory.getEmpDecisionsClient());
        //   setEmpDecisionsDTOSlelected(RegDTOFactory.createEmpDecisionsDTO());
        setSaveSortingState(false);
        //        if (kuwityType == null) {
        //            kuwityType = KUWITY;
        //            employeeSearchDTO.setNationality(NATIONLALTY_KUWITY);
        //        }
        //        //setDivMainContentMany("divDECEmployeesMainContent");
        //        if (DecisionCycleMaintainBean.isIntegrationPage()) {
        //            setDivMainContentMany("divContent1FullSizeWithScrollAndNavigationBtnsAndWizard");
        //        } else {
        setDivMainContentMany("divDecisionEmpMainContentManyCustom");
        //        }
        //        setLookupEditDiv("m2mAddDivClass");
        setContent1Div("reg_tabs_cont1");
        setLovDiv("m2mAddDivClass");

        //        setUsingPaging(true);
        //        setUsingBsnPaging(true);
        //        setPagingRequestDTO(new PagingRequestDTO("initEmpBean"));
        // initEmpSearchBean();
        setResetButton(false);
       

     
        /*// Load in Edit Mode
        decisionMaintainBean =
        (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
        //        DecisionEmployeesModel decisionEmployeesModelSessionBean =
        //            (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");
        //
        if (decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_EDIT && !decisionMaintainBean.isDataLoadedBefore()) {
            loadDataFromDatabase();
            decisionMaintainBean.setDataLoadedBefore(true);
        }*/
        //setCurrentDetails(new ArrayList());
        decisionMaintainBean = (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
    }

    public void initiateBeanOnce() {
        // Load in Edit Mode or View Mode
        if ((decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_EDIT ||
             decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_VIEW_ONLY) &&
            !decisionMaintainBean.isDataLoadedBefore()) {
         //   loadDataFromDatabase();
         //   decisionMaintainBean.setDataLoadedBefore(true);
        }
       // loadYearsList();
      //  LoadMonthsList();
    }

   

    //entered
    //    private void initEmpSearchBean(){
    //        if(getEmployeeSearchBean()==null){
    //            setEmployeeSearchBean(new EmployeeSearchBean());
    //        }
    //    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();

        String viewId = 
            FacesContext.getCurrentInstance().getViewRoot().getViewId();
      //  if (viewId.equals("/integration/reg/jsps/settdec/settdeccycle/decemployee/decisionemployeesmaintain.jsp")) {
            
            app.showManyToManyMaintain();
            //app.setManyToMany(true);
            app.setShowDelConfirm(false);
            app.setShowSearch(false);
            app.setShowLookupAdd(false);
            
            app.setShowDelConfirm(false);
            
            app.setShowpaging(true);
            app.setShowbar(true);
            app.setShowDelAlert(true);
            app.setShowdatatableContent(true);
            app.setShowNavigation(true);
            //app.setShowCustomDiv2(Boolean.TRUE);
            app.setShowCustomDiv1(false);
            app.setShowContent1(true);
            
       
       
        return app;
    }

    //    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {
    //        return new PagingResponseDTO();
    //    }

    //    public PagingResponseDTO initEmpBean(PagingRequestDTO request) {
    //        PagingResponseDTO responseDTO = new PagingResponseDTO();
    //        try{
    //
    //        DecisionCycleMaintainBean decisionMaintainBean =
    //            (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
    ////        /*DecisionEmployeesModel decisionEmployeesModelSessionBean =
    ////            (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");
    ////
    //            /*if(decisionMaintainBean.getMaintainMode() == 1){
    //                // Load Data Here
    //                //IDecisionsDTO CurrentDecision = (IDecisionsDTO)DAO().getById(decisionDTO.getCode());
    //                currentLoadedDecision = ((IDecisionsDTO)decisionMaintainBean.getPageDTO());
    //                if (currentLoadedDecision != null && currentLoadedDecision.getEmpDecisionsDTOList() != null && currentLoadedDecision.getEmpDecisionsDTOList().size() != 0) {
    //                    List<IEmpDecisionsDTO> list = (List)currentLoadedDecision.getEmpDecisionsDTOList();
    //                    Long civilId = ((IEmpDecisionsEntityKey)list.get(0).getCode()).getCivilId();
    //                    setCivilID(civilId);
    //                    findEmployeeByCivilIDFromEmployees();
    //                    this.viewTable();
    //                    this.loadBenefitsFromDataBase();
    //                    this.loadDeductionFromDataBase();
    //
    //                }
    //            }    */
    //        if (((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList() !=
    //            null &&
    //            ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().size() >
    //            0  && ((IEmpDecisionsDTO)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(0)).getCountOfAllPage()!= null) {
    //            responseDTO.setResultList(((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList());
    //            responseDTO.setTotalListSize(((IEmpDecisionsDTO)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(0)).getCountOfAllPage().intValue());
    //            List list = new ArrayList();
    //            for (int i = 0;
    //                 i < ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().size();
    //                 i++) {
    //                list.add(((IEmpDecisionsDTO)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(i)));
    //
    //            }
    //
    //        }
    //        //else {
    //
    //            if (getSelectedCurrentDetails() != null &&
    //                getSelectedCurrentDetails().size() > 0) {
    //                for (int y = 0; y < getSelectedCurrentDetails().size(); y++) {
    //                    for (int i = 0; i < getCurrentDetails().size(); i++) {
    //                        if (((IEmployeesEntityKey)getSelectedCurrentDetails().get(y).getCode()).getCivilId().equals(((IEmpDecisionsEntityKey)getCurrentDetails().get(i).getCode()).getCivilId()))
    //                            {
    //                            ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setChecked(true);
    //                            }
    //                            else
    //                            {
    //                                ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setChecked(false);
    //                            }
    //                    }
    //                }
    //
    //            }
    //            responseDTO.setResultList(getCurrentDetails());
    //            responseDTO.setTotalListSize(getCurrentListSize());
    //            setUpdateMyPagedDataModel(true);
    //        if (getSelectedCurrentDetails() != null &&
    //            getSelectedCurrentDetails().size() > 0) {
    //            for (int y = 0; y < getSelectedCurrentDetails().size(); y++) {
    //                for (int i = 0; i < getCurrentDetails().size(); i++) {
    //                    if (((IEmployeesEntityKey)getSelectedCurrentDetails().get(y).getCode()).getCivilId().equals(((IEmpDecisionsEntityKey)getCurrentDetails().get(i).getCode()).getCivilId()))
    //                        {
    //                        ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setChecked(true);
    //                        }
    //                        else
    //                        {
    //                            ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setChecked(false);
    //                        }
    //                }
    //            }
    //
    //        }
    //        //responseDTO.setResultList(getCurrentDetails());
    //        responseDTO.setResultList(getCurrentDisplayDetails());
    //        responseDTO.setTotalListSize(getCurrentListSize());
    //        setUpdateMyPagedDataModel(true);
    //
    //           // }
    //            }catch(Exception e){
    //                e.printStackTrace();
    //                return null;
    //            }
    //        return responseDTO;
    //
    //    }

    //    public void changeKuwityType() {
    //        if (employeeSearchDTO.getNationality() == KUWITY) {
    //            resetNonKuwityData();
    //            employeeSearchDTO.setNationality(NATIONLALTY_KUWITY);
    //        } else {
    //            employeeSearchDTO.setNationality(null);
    //        }
    //    }
    //
    //    private void resetNonKuwityData() {
    //        employeeSearchDTO.setNationality(null);
    //        employeeSearchDTO.setPassportNo(null);
    //        employeeSearchDTO.setResidentTypeCode(null);
    //        employeeSearchDTO.setEndResidentDate(null);
    //    }

    //    public void reSetData() {
    //        setAvailableDetails(new ArrayList<IBasicDTO>());
    //        currentDetails = new ArrayList<IBasicDTO>();
    //        getSelectedCurrentDetails().clear();
    //        setSelectedDTOS(new ArrayList());
    //
    //    }

    //    public List getCurrentDisplayDetails() {
    // use this injection into integration (by ahmed abdel fatah)
    //        if (getIntegrationBean() != null &&
    //            getIntegrationBean().getSelectedDTOFrom() != null &&
    //            getIntegrationBean().getSelectedDTOFrom().size() != 0) {
    //            getSelectedAvailableDetails().addAll(getIntegrationBean().getSelectedDTOFrom());
    //            super.add();
    //        }
    //        /////////////////////////////////
    //        if (!isCancelDecisionMode()) {
    //            return super.getCurrentDisplayDetails();
    //        }
    //
    //        List<IBasicDTO> currentDisplayed = null;
    //        DecisionCycleMaintainBean decisionBean =
    //            (DecisionCycleMaintainBean)BeansUtil.getValue("settlementDecisionCycleMaintainBean");
    //        if (decisionBean != null ) {
    //            IDecisionsDTO decision = (IDecisionsDTO)decisionBean.getPageDTO();
    //            if (decision != null) {
    //                IDecisionsDTO canceledDecision = decision.getDecisionsDTO();
    //                if ((Boolean)BeansUtil.getValue("settlementDecisionCycleMaintainBean.maintainMode == 1")) {
    //                    try {
    //                        canceledDecision =
    //                                (IDecisionsDTO)(RegClientFactory.getDecisionsClient().getById(canceledDecision.getCode()));
    //                    } catch (SharedApplicationException e) {
    //                        e.printStackTrace();
    //                    } catch (DataBaseException e) {
    //                        e.printStackTrace();
    //                    }
    //                }
    //                if (canceledDecision != null) {
    //                    currentDisplayed =
    //                            canceledDecision.getEmpDecisionsDTOList();
    //                    setCurrentDisplayDetails(currentDisplayed);
    //                }
    //            }
    //        }
    //        return currentDisplayed;
    //    }
    //entered
    //    public void cancelSearchAvailable() throws DataBaseException,
    //                                               SharedApplicationException {
    //        setAvailableDetails(new ArrayList<IBasicDTO>());
    //        setSearchMode(false);
    //        getSelectedCurrentDetails().clear();
    //    }

    //    currently not used

    //    public void getListAvailable() throws DataBaseException {
    //        DecisionCycleMaintainBean decisionBean =
    //            (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{settlementDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
    //        if (decisionBean != null && isCancelDecisionMode()) {
    //            IDecisionsDTO decision = (IDecisionsDTO)decisionBean.getPageDTO();
    //            if (decision != null) {
    //                IDecisionsDTO canceledDecision = decision.getDecisionsDTO();
    //                if (canceledDecision != null) {
    //                    setAvailableDetails(canceledDecision.getEmpDecisionsDTOList());
    //                }
    //            }
    //        }
    //    }

  
   


   
    public void add() {
        super.add();
        navigateToDecision();
    }

    public void cancel() {
        setSelectedAvailableDetails(new ArrayList<IBasicDTO>());
        navigateToDecision();
    }

    private void navigateToDecision() {
        setDivMainContent("divDECEmployeesMainContent");
        FacesContext fContext = FacesContext.getCurrentInstance();
        Application app = fContext.getApplication();

        app.createValueBinding("#{wizardbar.currentStep}").setValue(fContext, "settlementemployeesadd");

        String pageNavigation = "settlementDecisionCycleEmployeesMaintainBean";
        //        if ((Boolean)app.createValueBinding("#{decisionMaintainBean.cancelDecisionMode}").getValue(fContext)) {
        //            pageNavigation += "cancel";
        //        }

        if ((Boolean)app.createValueBinding("#{settlementDecisionCycleMaintainBean.maintainMode == 1}").getValue(fContext)) {
            pageNavigation += "edit";
        }

        setAvailableDetails(new ArrayList<IBasicDTO>());
        app.getNavigationHandler().handleNavigation(fContext, null, pageNavigation);
    }

    //modified by I.Omar
    //    public void delete() {
    //        try {
    //            DecisionEmployeesModel decisionEmployeesModelSessionBean =
    //                (DecisionEmployeesModel)evaluateValueBinding("decisionEmployeesModel");
    //            if (getCurrentDetails() == null)
    //                setCurrentDetails(new ArrayList<IBasicDTO>());
    //            DecisionCycleMaintainBean decisionBean =
    //                (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{decisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
    //            decisionBean.getPageDTO();
    //            if (getEmpDecisionsDTOSlelected() != null) {
    //                for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
    //                    IEmpDecisionsDTO dto =
    //                        (IEmpDecisionsDTO)decisionEmployeesModelSessionBean.getAddElementMap().get(((IEmployeesEntityKey)getSelectedCurrentDetails().get(i).getCode()).getCivilId());
    //                    if (dto.getStatusFlag() == null) {
    //                        dto.setStatusFlag(deleted.longValue());
    //                        dto.setDisableFlage(true);
    //                        getSelectedCurrentDetails().remove(i);
    //                        i--;
    //                    }
    //                    if (dto.getStatusFlag() != null) {
    //                        if (dto.getStatusFlag().equals(IConstants.REG_UNFINISHED_STATUS)) {
    //                            if (decisionEmployeesModelSessionBean.getAddElementMap().containsKey(((IEmpDecisionsEntityKey)dto.getCode()).getCivilId())) {
    //                                getCurrentDetails().remove(dto);
    //                                dto.setStatusFlag(deleted.longValue());
    //                                dto.setDisableFlage(true);
    //                            }
    //                            getSelectedCurrentDetails().remove(i);
    //                            i--;
    //                        }
    //                        if (dto.getStatusFlag().longValue() ==
    //                            added.longValue()) {
    //                            getCurrentDetails().remove(dto);
    //                            dto.setStatusFlag(IConstants.REG_UNCATEGORIZED_STATUS);
    //                            dto.setDisableFlage(true);
    //                            getSelectedCurrentDetails().remove(i);
    //                            i--;
    //                        }
    //                    }
    //                }
    ////                setCurrentDetails(decisionEmployeesModelSessionBean.getOriginalMap().get(new Long(getCurrentPageIndex())));
    //            }
    //            this.restoreDetailsTablePosition(this.getCurrentDataTable(),
    //                                             this.getCurrentDetails());
    //            this.resetSelection();
    //            PagingRequestDTO request = null;
    //            initEmpBean(request);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }


    //        public List<IBasicDTO> searchAvailable(Object employeSearchDTO) {
    //            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
    //            try {
    //                //employeeSearchDTO.setMasterCode(getMasterEntityKey() == null ? new IDecisionsEntityKey() : getMasterEntityKey());
    //                //setAvailableDetails(((IEmpDecisionsClient)getClient()).filterAvailableEntities (employeeSearchDTO));
    //                //List<IBasicDTO> list =  (((IEmpDecisionsClient)getClient()).filterAvailableEntities (new IEmployeeSearchDTO()));
    //                list =((IEmpDecisionsClient)getClient()).filterAvailableEntities((IEmployeeSearchDTO)employeSearchDTO);
    //                setAvailableDetails(list); //new IEmployeeSearchDTO()));
    //                setSearchMode(true);
    //            } catch (SharedApplicationException e) {
    //                setAvailableDetails(new ArrayList<IBasicDTO>());
    //                e.printStackTrace();
    //            } catch (Exception ex) {
    //                setAvailableDetails(new ArrayList<IBasicDTO>());
    //                ex.printStackTrace();
    //            }
    //            return list;
    //        }

    //    public void showSearchForEmployeeDiv() {
    //        employeeSearchBean.setReturnMethodName(BEAN_NAME + ".returnMethodAction");
    //        employeeSearchBean.resetData();
    //        employeeSearchBean.getAdvanceEmployeesAddBean().setBackMethod("settlementDecisionCycleEmployeesMaintainBean.backFromSearch");
    //        //if you need to make multi selection set singleSelection = flase
    //        //employeeSearchBean.getAdvanceEmployeesAddBean().setSingleSelection(false);
    //        employeeSearchBean.getAdvanceEmployeesAddBean().setReturnMethod("settlementDecisionCycleEmployeesMaintainBean.employeeAddMethod");
    //        employeeSearchBean.getAdvanceEmployeesAddBean().setSingleSelection(false);
    //        employeeSearchBean.getOkCommandButton().setReRender("customDiv1,empInquiryCnt1Pnl,dataT_data_panel");
    //        //to send list of hireStatus remove comment
    ////                    List<Long> hirstatusCodeList = new ArrayList<Long>();
    ////                    hirstatusCodeList.add(IEMPConstant.EMP_STATUS_EMPLOYMENT);
    ////                    hirstatusCodeList.add(IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO);
    ////                    hirstatusCodeList.add(IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM);
    ////                    hirstatusCodeList.add(IEMPConstant.EMP_STATUS_DELEGATION);
    ////                    hirstatusCodeList.add(IEMPConstant.EMP_STATUS_LOANINIG);
    ////                    hirstatusCodeList.add(IEMPConstant.EMP_STATUS_MISSION);
    ////                    hirstatusCodeList.add(IEMPConstant.EMP_STATUS_GRANT);
    ////                    hirstatusCodeList.add(IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM);
    ////                    hirstatusCodeList.add(IEMPConstant.EMP_STATUS_PRISONER_LOST);
    ////                    hirstatusCodeList.add(IEMPConstant.EMP_STATUS_VACATION);
    ////                    hirstatusCodeList.add(IEMPConstant.EMP_STATUS_CANDIDATE);
    ////                    employeeSearchBean.getAdvanceEmployeesAddBean().getEmployeeSearchDTO().setHireStatusList(hirstatusCodeList);
    //        //employeeSearchBean.getOkCommandButton().setReRender("scriptPanelGn,customDiv1,civilId,searchBtn,resetBtn,searchLovDiv,name,navigationpanel,cntPanel");
    //    }

    public String closeDiv() {
        employeeSearchBean.setResetDivAfterClose(true);
        IEmployeesDTO selectedEmployeesDTO = ((IEmployeesDTO)employeeSearchBean.getSelectedDTOS().get(0));
        setCivilID(selectedEmployeesDTO.getRealCivilId());
        findEmployeeByCivilIDFromEmployees();
        return null;
    }

    //    public String backFromSearch() {
    //        this.getWizardBar().setCurrentStep("financeemployeesadd");
    //        return "settlementDecisionCycleEmployeesMaintainBean";
    //    }
    //    public String employeeAddMethod() {
    //        return "settlementDecisionCycleEmployeesMaintainBean";
    //    }

    public boolean validate() {
        if (DecisionCycleMaintainBean.isIntegrationPage()) {
            return true; //TODO
        }
        // will loop on all emps and check if list of salElementGuides correct or no
        
        if (!validateSettlements()) {
          
                String errMsg = getSharedUtil().messageLocator(BUNDLE, "one_emps_has_no_sett_msg");
                getSharedUtil().setErrMsgValue(errMsg);
       

            return false;
        }

  /*      if (!validateSettDate()) {
            return false;
        }
        boolean valid = validateSattlementListExist();
        if (!valid) {
            showErrMsg = true;
            return valid;
        }
*/
        return true;
    }
    private boolean validateSettlements() {
        boolean valid = true;
        for(IBasicDTO row:currentDetails){
            IEmpDecisionsDTO empDecisionsDTO = (IEmpDecisionsDTO)row;
            if(empDecisionsDTO.getStatusFlag() !=null && empDecisionsDTO.getStatusFlag().equals(added) ){
                valid=false;
            if(empDecisionsDTO.getSalElmGuideDTOList()==null || empDecisionsDTO.getSalElmGuideDTOList().isEmpty()){
                
                return false;
                }
             List<IBasicDTO> list = empDecisionsDTO.getSalElmGuideDTOList();
      //  if (benfitsList != null && !benfitsList.isEmpty()) {
                for (IBasicDTO obj : list) {
                    ISalElementGuidesDTO dto = (ISalElementGuidesDTO)obj;
                    if ((dto.getDenarValue() != null && !dto.getDenarValue().trim().equals("") &&
                         !dto.getDenarValue().trim().equals("0")) ||
                        (dto.getFelsValue() != null && !dto.getFelsValue().trim().equals("") &&
                         !dto.getFelsValue().trim().equals("0"))) {
                        valid = true;
                        break;
                    }
                }
            }
       // }

//        if (!valid && deductionsList != null && !deductionsList.isEmpty()) {
//            for (ISalElementGuidesDTO dto : deductionsList) {
//                if ((dto.getDenarValue() != null && !dto.getDenarValue().trim().equals("") &&
//                     !dto.getDenarValue().trim().equals("0")) ||
//                    (dto.getFelsValue() != null && !dto.getFelsValue().trim().equals("") &&
//                     !dto.getFelsValue().trim().equals("0"))) {
//                    valid = true;
//                    break;
//                }
//            }
//        }


        }
    
        return valid;
    }




    //    public String navigateAdd() {
    //        try {
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setSearchMethod("specialExtraEmpDecisionCycleAddBean.filterSearchByEmpWithPaging");
    //             employeeSearchBean.getAdvanceEmployeesAddBean().setShowResultWithinPage(false);
    //             employeeSearchBean.getAdvanceEmployeesAddBean().setShowCategoryCB(false);
    //             employeeSearchBean.getAdvanceEmployeesAddBean().setShowMinistryCB(false);
    //             employeeSearchBean.getAdvanceEmployeesAddBean().setShowWorkEndDate(false);
    //             employeeSearchBean.getAdvanceEmployeesAddBean().setShowHireStatus(false);
    //             employeeSearchBean.getAdvanceEmployeesAddBean().setShowCurrentHireStatus(false);
    //             employeeSearchBean.getAdvanceEmployeesAddBean().setShowWorkCenterLovDiv(true);
    //             employeeSearchBean.getAdvanceEmployeesAddBean().setShowJobLovDiv(true);
    //
    //            //////////////// add by amr galal to Apply B.Paging //////////////////
    //             employeeSearchBean.getAdvanceEmployeesAddBean().setUsingBsnPaging(true);
    //             employeeSearchBean.getAdvanceEmployeesAddBean().setUsingPaging(true);
    //            /////////////////////////////////////////////////////////////////////
    //
    //            List employeesList = getCurrentDetails();
    //            List empList = new ArrayList();
    //            if (employeesList == null)
    //                setCurrentDetails(new ArrayList<IBasicDTO>());
    //
    //            for (IEmpDecisionsDTO evalEmp:
    //                 (ArrayList<IEmpDecisionsDTO>)employeesList) {
    //                IEmployeesDTO emp = (IEmployeesDTO)evalEmp.getEmployeesDTO();
    //                emp.setStatusFlag(evalEmp.getStatusFlag());
    //                empList.add(emp);
    //            }
    //             employeeSearchBean.getAdvanceEmployeesAddBean().setCurrentDetails(empList);
    ////            DecisionCycleMaintainBean decisionBean =
    ////                (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{settlementDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
    ////
    ////            FinancialDecisionsListBean decisionListBean =
    ////                (FinancialDecisionsListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{financialDecisionsListBean}").getValue(FacesContext.getCurrentInstance());
    //
    //            //if(getPageDTO()!=null && getPageDTO().getCode()!=null)
    // //           if (decisionBean.getPageDTO() != null)
    ////                 employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(0,
    ////                                                    decisionBean.getPageDTO());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(1,
    ////                                                decisionBean.getMaintainMode());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(2,
    ////                                                getWizardBar().getConfigurationId());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(3,
    ////                                                getWizardBar().getWizardSteps());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(4,
    ////                                                decisionBean.isShowPrevious());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(5,
    ////                                                decisionBean.isRenderFinish());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(6,
    ////                                                decisionBean.isRenderSave());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(7,
    ////                                                decisionBean.getNextNavigationCase());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(8,
    ////                                                decisionBean.getPreviousNavigationCase());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(9,
    ////                                                decisionBean.getFinishNavigationCase());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(10,
    ////                                                decisionBean.getCurrentNavigationCase());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(11, employeesList);
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(12,
    ////                                                decisionListBean.getIndexArray());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(13,
    ////                                                decisionListBean.getSortColumn());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(14,
    ////                                                decisionListBean.isSaveSortingState());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(15,
    ////                                                decisionListBean.isSortAscending());
    ////             employeeSearchBean.getAdvanceEmployeesAddBean().getSavedData().add(16,
    ////                                                decisionListBean.getFullColumnName());
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //
    //        return "Advanced_Add_Employees_decisionFinance_cycle";
    //    }

    //    public void addFinancial() {
    //        DecisionEmployeesModel decisionEmployeesModelSessionBean =
    //            (DecisionEmployeesModel)evaluateValueBinding("decisionEmployeesModel");
    //        if (getCurrentDetails() != null && getCurrentDetails().size() != 0) {
    //            for (int i = 0; i < getCurrentDetails().size(); i++) {
    //                if (getEmpDecisionsDTOSlelected().getCode() ==
    //                    getCurrentDetails().get(i).getCode()) {
    //                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setValueNum(valueNum);
    //                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setValueDate(valueDate);
    //                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setValueChr(valueChr);
    //                    if (((IEmpDecisionsDTO)getCurrentDetails().get(i)).getStatusFlag() !=  null) {
    //                        if (((IEmpDecisionsDTO)getCurrentDetails().get(i)).getStatusFlag() == 4L) {
    //                            ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setStatusFlag(null);
    //                        }
    //                    }
    //                }
    //
    //
    //            }
    //            if (decisionEmployeesModelSessionBean.getAddElementMap().containsKey(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId())) {
    //                IEmpDecisionsDTO dto =  (IEmpDecisionsDTO)decisionEmployeesModelSessionBean.getAddElementMap().get(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId());
    //                dto.setValueChr(valueChr);
    //                dto.setValueDate(valueDate);
    //                dto.setValueNum(valueNum);
    //                if(dto.getStatusFlag()!=null)
    //                {
    //                    if(dto.getStatusFlag()==4L)
    //                    {
    //                    dto.setStatusFlag(null);
    //                    }
    //                }
    //                //decisionEmployeesModelSessionBean.getAddElementMap().put(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId(),dto);
    //            }
    //        }
    //        //decisionEmployeesModelSessionBean.getOriginalMap().put(new Long(getCurrentPageIndex()),  getCurrentDetails());
    //
    //        setActiveValidationOnAdd(false);
    //
    //    }
    //
    //
    //    public void addInformationEmp() {
    //        DecisionEmployeesModel decisionEmployeesModelSessionBean =
    //            (DecisionEmployeesModel)evaluateValueBinding("decisionEmployeesModel");
    //        if (getCurrentDetails() != null && getCurrentDetails().size() != 0) {
    //            for (int i = 0; i < getCurrentDetails().size(); i++) {
    //                if (getEmpDecisionsDTOSlelected().getCode() ==
    //                    getCurrentDetails().get(i).getCode()) {
    //                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setNotes(notes);
    //                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setEmpInfTypeCode(empInfTypeCode);
    //                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setBooleanInformEmpFlag(booleanInformEmpFlag);
    //                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setInformEmpDate(informEmpDate);
    //                    if (((IEmpDecisionsDTO)getCurrentDetails().get(i)).getStatusFlag() !=
    //                        null) {
    //                        if (((IEmpDecisionsDTO)getCurrentDetails().get(i)).getStatusFlag() ==
    //                            4L) {
    //                            ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setStatusFlag(null);
    //                        }
    //                    }
    //                }
    //            }
    //
    //            if (decisionEmployeesModelSessionBean.getAddElementMap().containsKey(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId())) {
    //                IEmpDecisionsDTO dto =  (IEmpDecisionsDTO)decisionEmployeesModelSessionBean.getAddElementMap().get(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId());
    //                dto.setNotes(notes);
    //                dto.setEmpInfTypeCode(empInfTypeCode);
    //                dto.setBooleanInformEmpFlag(booleanInformEmpFlag);
    //                dto.setInformEmpDate(informEmpDate);
    //                if(dto.getStatusFlag()!=null)
    //                {
    //                    if(dto.getStatusFlag()==4L)
    //                    {
    //                    dto.setStatusFlag(null);
    //                    }
    //                }
    //                //decisionEmployeesModelSessionBean.getAddElementMap().put(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId(),dto);
    //            }
    //        }
    //        //decisionEmployeesModelSessionBean.getOriginalMap().put(new Long(getCurrentPageIndex()), getCurrentDetails());
    //        setActiveValidationOnEdit(false);
    //    }
    //
    //    public void openAddDiv() {
    //        setActiveValidationOnAdd(true);
    //        if (getEmpDecisionsDTOSlelected() != null) {
    //            setValueDate(getEmpDecisionsDTOSlelected().getValueDate());
    //            setValueChr(getEmpDecisionsDTOSlelected().getValueChr());
    //            setValueNum(getEmpDecisionsDTOSlelected().getValueNum());
    //        }
    //    }
    //
    //    public void openEditDiv() {
    //        setActiveValidationOnEdit(true);
    //        if (getEmpDecisionsDTOSlelected() != null) {
    //            setNotes(getEmpDecisionsDTOSlelected().getNotes());
    //            setEmpInfTypeCode(getEmpDecisionsDTOSlelected().getEmpInfTypeCode());
    //            setBooleanInformEmpFlag(getEmpDecisionsDTOSlelected().isBooleanInformEmpFlag());
    //            setInformEmpDate(getEmpDecisionsDTOSlelected().getInformEmpDate());
    //        }
    //    }
    //
    //    public void cancelAdd() {
    //        setActiveValidationOnAdd(false);
    //        setValueDate(null);
    //        setValueChr(null);
    //        setValueNum(null);
    //    }
    //
    //    public void cancelEdit() {
    //        setActiveValidationOnEdit(false);
    //        setNotes(null);
    //        setEmpInfTypeCode(null);
    //        setBooleanInformEmpFlag(null);
    //        setInformEmpDate(null);
    //    }
    //
    //    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
    //        if (isCancelDecisionMode()) {
    //            return dto; // as i got it from the canceled decision there is no need for the mapping
    //        }
    //        return ((IEmpDecisionsDTO)dto).getEmployeesDTO();
    //    }
    //
    //    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
    //        if (isCancelDecisionMode()) {
    //            return dto; // as i got it from the canceled decision there is no need for the mapping
    //        }
    //
    //        IEmpDecisionsDTO empIDecisionsDTO =
    //            RegDTOFactory.createEmpDecisionsDTO();
    ////        ISalElementGuidesEntityKey ek = SalEntityKeyFactory.createSalElementGuidesEntityKey();
    ////        ISalElementGuidesDTO salDto = new SalElementGuidesDTO();
    ////        salDto.setCode(ek);
    ////        empIDecisionsDTO.setElementGuidesDTO(salDto);
    //        empIDecisionsDTO.setEmployeesDTO((IEmployeesDTO)dto);
    //        IDecisionsDTO decisionsDTO = null;
    //        IDecisionsEntityKey decisionsEntityKey = null;
    //        IEmpDecisionsEntityKey empDecisionsEntityKey = null;
    //
    //        if (this.getMasterEntityKey() != null) {
    //            decisionsEntityKey =
    //                    (IDecisionsEntityKey)this.getMasterEntityKey();
    //            decisionsDTO = RegDTOFactory.createDecisionsDTO();
    //            decisionsDTO.setCode(decisionsEntityKey);
    //            empIDecisionsDTO.setDecisionsDTO(decisionsDTO);
    //
    //        }
    //
    //        if (decisionsEntityKey != null) {
    //            empDecisionsEntityKey =
    //                    RegEntityKeyFactory.createEmpDecisionsEntityKey(decisionsEntityKey.getDectypeCode(),
    //                                                                    decisionsEntityKey.getDecyearCode(),
    //                                                                    decisionsEntityKey.getDecisionNumber(),
    //                                                                    Long.valueOf(dto.getCode().getKey().toString()));
    //        } else {
    //            empDecisionsEntityKey =
    //                    RegEntityKeyFactory.createEmpDecisionsEntityKey(null, null,
    //                                                                    null,
    //                                                                    Long.valueOf(dto.getCode().getKey().toString()));
    //        }
    //        empIDecisionsDTO.setCode(empDecisionsEntityKey);
    //
    //        return empIDecisionsDTO;
    //    }
    //
    //    public void setIEmployeeSearchDTO(IEmployeeSearchDTO employeeSearchDTO) {
    //        this.employeeSearchDTO = employeeSearchDTO;
    //    }
    //
    //    public IEmployeeSearchDTO getIEmployeeSearchDTO() {
    //        return employeeSearchDTO;
    //    }

    //    public Long getKuwity() {
    //        return KUWITY;
    //    }
    //
    //    public Long getNonKuwity() {
    //        return NON_KUWITY;
    //    }
    ////////////////////////////////////////////////
    //    public void setMaritalStatusTyps(List<IBasicDTO> maritalStatusTyps) {
    //        this.maritalStatusTyps = maritalStatusTyps;
    //    }
    //
    //    public List<IBasicDTO> getMaritalStatusTyps() {
    //        if (maritalStatusTyps.size() == 0) {
    //            try {
    //                maritalStatusTyps =
    //                    InfClientFactory.getMaritalStatusClient().getCodeName();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        return maritalStatusTyps;
    //    }
    //
    //    public void setGenderTyps(List<IBasicDTO> genderTyps) {
    //        this.genderTyps = genderTyps;
    //    }
    //
    //    public List<IBasicDTO> getGenderTyps() {
    //        if (genderTyps.size() == 0) {
    //            try {
    //                genderTyps = InfClientFactory.getGenderTypesClient().getCodeName();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        return genderTyps;
    //    }
    //
    //    public void setRelgionTyps(List<IBasicDTO> relgionTyps) {
    //        this.relgionTyps = relgionTyps;
    //    }
    //
    //    public List<IBasicDTO> getRelgionTyps() {
    //        if (relgionTyps.size() == 0) {
    //            try {
    //                relgionTyps = InfClientFactory.getReligionsClient().getCodeName();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        return relgionTyps;
    //    }
    //
    //    public void setItemSelectionRequiredValue(Long itemSelectionRequiredValue) {
    //        this.itemSelectionRequiredValue = itemSelectionRequiredValue;
    //    }
    //
    //    public Long getItemSelectionRequiredValue() {
    //        return itemSelectionRequiredValue;
    //    }
    //
    //    public void setGovernments(List<IBasicDTO> governments) {
    //        this.governments = governments;
    //    }
    //
    //    public List<IBasicDTO> getGovernments()  {
    //        if (governments.size() == 0) {
    //            try {
    //                governments = InfClientFactory.getKwMapClient().getCodeName();
    //            }  catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        return governments;
    //    }
    //
    //    public void setCapTyps(List<IBasicDTO> capTyps) {
    //        this.capTyps = capTyps;
    //    }
    //
    //    public List<IBasicDTO> getCapTyps()  {
    //        if (capTyps.size() == 0) {
    //            try {
    //                capTyps = InfClientFactory.getHandicapStatusClient().getCodeName();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        return capTyps;
    //    }
    //
    //    public void setSpecialCaseTyps(List<IBasicDTO> specialCaseTyps) {
    //        this.specialCaseTyps = specialCaseTyps;
    //    }
    //
    //    public List<IBasicDTO> getSpecialCaseTyps() {
    //        if (specialCaseTyps.size() == 0) {
    //            try {
    //                specialCaseTyps =
    //                    InfClientFactory.getSpecialCaseTypesClient().getCodeName();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        return specialCaseTyps;
    //    }
    //
    //    public void setCategories(List<IBasicDTO> categories) {
    //        this.categories = categories;
    //    }
    //
    //    public List<IBasicDTO> getCategories() throws DataBaseException {
    //        if (categories.size() == 0) {
    //            categories = OrgClientFactory.getCatsClient().getCodeName();
    //        }
    //        return categories;
    //    }
    //
    //    public void fillMinistries() {
    //        try {
    //            ministries =
    //                    OrgClientFactory.getMinistriesClient().getAllByCategory(categoryID);
    //        } catch (SharedApplicationException e) {
    //            ministries = new ArrayList<IBasicDTO>();
    //            e.printStackTrace();
    //        } catch (DataBaseException e) {
    //            ministries = new ArrayList<IBasicDTO>();
    //            e.printStackTrace();
    //        } catch (Exception e) {
    //            ministries = new ArrayList<IBasicDTO>();
    //            e.printStackTrace();
    //        }
    //    }
    //
    //    public void setMinistries(List<IBasicDTO> ministries) {
    //        this.ministries = ministries;
    //    }
    //
    //    public void setWorkMinistries(List<IBasicDTO> workMinistries) {
    //        this.workMinistries = workMinistries;
    //    }
    //
    //    public List<IBasicDTO> getWorkMinistries() {
    //        return workMinistries;
    //    }
    //
    //    public void fillWorkMinistries() {
    //        try {
    //            workMinistries =
    //                    OrgClientFactory.getWorkCentersClient().getAllByMinistry(OrgEntityKeyFactory.createMinistriesEntityKey(ministryID));
    //        } catch (SharedApplicationException e) {
    //            workMinistries = new ArrayList<IBasicDTO>();
    //            e.printStackTrace();
    //        } catch (DataBaseException e) {
    //            workMinistries = new ArrayList<IBasicDTO>();
    //            e.printStackTrace();
    //        } catch (Exception e) {
    //            workMinistries = new ArrayList<IBasicDTO>();
    //            e.printStackTrace();
    //        }
    //    }
    //
    //    public List<IBasicDTO> getMinistries() {
    //        return ministries;
    //    }
    //
    //    public void setHireTypes(List<IBasicDTO> hireTypes) {
    //        this.hireTypes = hireTypes;
    //    }
    //
    //    public List<IBasicDTO> getHireTypes() throws DataBaseException {
    //        if (hireTypes.size() == 0) {
    //            hireTypes =
    //                    (List<IBasicDTO>)EmpClientFactory.getHireTypesClient().getCodeName();
    //        }
    //        return hireTypes;
    //    }
    //
    //    public void setHireStatuses(List<IBasicDTO> hireStatuses) {
    //        this.hireStatuses = hireStatuses;
    //    }
    //
    //    public List<IBasicDTO> getHireStatuses() throws DataBaseException {
    //        if (hireStatuses.size() == 0) {
    //            hireStatuses =
    //                    EmpClientFactory.getHireStatusClient().getCodeName();
    //        }
    //        return hireStatuses;
    //    }
    //
    //    public void setHireCurrentStatuses(List<IBasicDTO> hireCurrentStatuses) {
    //        this.hireCurrentStatuses = hireCurrentStatuses;
    //    }
    //
    //    public List<IBasicDTO> getHireCurrentStatuses() throws DataBaseException {
    //        if (hireCurrentStatuses.size() == 0) {
    //            hireCurrentStatuses =
    //                    EmpClientFactory.getHireStagesClient().getCodeName();
    //        }
    //        return hireCurrentStatuses;
    //    }
    //
    //    public void setTechnicalJobs(List<IJobsDTO> technicalJobs) {
    //        this.technicalJobs = technicalJobs;
    //    }
    //
    //    public List<IJobsDTO> getTechnicalJobs() throws DataBaseException {
    //        if (technicalJobs.size() == 0) {
    //            technicalJobs = JobClientFactory.getJobsClient().getCodeName();
    //        }
    //        return technicalJobs;
    //    }
    //
    //    public void setBanks(List<IBasicDTO> banks) {
    //        this.banks = banks;
    //    }

    //    public List<IBasicDTO> getBanks() throws DataBaseException {
    //        if (banks.size() == 0) {
    //            banks = InfClientFactory.getBanksClient().getCodeName();
    //        }
    //        return banks;
    //    }
    //
    //    public void setBranches(List<IBasicDTO> branches) {
    //        this.branches = branches;
    //    }
    //
    //    public List<IBasicDTO> getBranches() {
    //        return branches;
    //    }

    //    public void fillBranches() {
    //        try {
    //            branches =
    //                    InfClientFactory.getBankBranchesClient().getCodeName(bankID);
    //        } catch (DataBaseException e) {
    //            branches = new ArrayList<IBasicDTO>();
    //            e.printStackTrace();
    //        } catch (Exception e) {
    //            branches = new ArrayList<IBasicDTO>();
    //            e.printStackTrace();
    //        }
    //    }
    ////////////////////////////////////////////////
    ///  public void setJobCategories(List<IBasicDTO> jobCategories) {
    //        this.jobCategories = jobCategories;
    //    }

    ///   public List<IBasicDTO> getJobCategories() {
    //        return jobCategories;
    //    }
    //
    //    public void setJobGroupes(List<IBasicDTO> jobGroupes) {
    //        this.jobGroupes = jobGroupes;
    //    }
    //
    //    public List<IBasicDTO> getJobGroupes() {
    //        return jobGroupes;
    //    }
    //
    //    public void setCurrentDegrees(List<IBasicDTO> currentDegrees) {
    //        this.currentDegrees = currentDegrees;
    //    }
    //
    //    public List<IBasicDTO> getCurrentDegrees() {
    //        return currentDegrees;
    //    }
    //
    //    public void setBudgetTypes(List<IBasicDTO> budgetTypes) {
    //        this.budgetTypes = budgetTypes;
    //    }
    //
    //    public List<IBasicDTO> getBudgetTypes() {
    //        return budgetTypes;
    //    }
    //
    //    public void setNationalties(List<IBasicDTO> nationalties) {
    //        this.nationalties = nationalties;
    //    }
    //
    //    public List<IBasicDTO> getNationalties() {
    //        if (nationalties.size() == 0) {
    //            try {
    //                nationalties = InfClientFactory.getCountriesClient().getCodeName();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        return nationalties;
    //    }
    //
    //    public void setResdientTypes(List<IBasicDTO> resdientTypes) {
    //        this.resdientTypes = resdientTypes;
    //    }
    //
    //    public List<IBasicDTO> getResdientTypes()  {
    //        if (resdientTypes.size() == 0) {
    //            try {
    //                resdientTypes =
    //                    InfClientFactory.getResidentTypeClient().getCodeName();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        return resdientTypes;
    //    }

    //    public void setCategoryID(Long categoryID) {
    //        this.categoryID = categoryID;
    //    }
    //
    //    public Long getCategoryID() {
    //        return categoryID;
    //    }
    //
    //    public void setMinistryID(Long ministryID) {
    //        this.ministryID = ministryID;
    //    }
    //
    //    public Long getMinistryID() {
    //        return ministryID;
    //    }
    //
    //    public void setBankID(Long bankID) {
    //        this.bankID = bankID;
    //    }
    //
    //    public Long getBankID() {
    //        return bankID;
    //    }

    //    public void setKuwityType(Long kuwityType) {
    //        this.kuwityType = kuwityType;
    //    }
    //
    //    public Long getKuwityType() {
    //        return kuwityType;
    //    }


    public void setIsSearchMode(boolean isSearchMode) {
        this.isSearchMode = isSearchMode;
    }

    public boolean isIsSearchMode() {
        return isSearchMode;
    }
    //

    public void setShowErrMsg(boolean showErrMsg) {
        this.showErrMsg = showErrMsg;
    }

    public boolean isShowErrMsg() {
        return showErrMsg;
    }

    //    public void setDataScroller(HtmlDataScroller dataScroller) {
    //        this.dataScroller = dataScroller;
    //    }
    //
    //    public HtmlDataScroller getDataScroller() {
    //        return dataScroller;
    //    }

    //    public void setPageIndexAdd(Integer pageIndexAdd) {
    //        pageIndexAdd = 0;
    //    }
    //
    //    public Integer getPageIndexAdd() {
    //        if (dataScroller != null) {
    //            return dataScroller.getPageIndex();
    //        }
    //
    //        return 0;
    //    }
    //
    //    /**
    //     * Purpose: Overrwite this medthod to get row data without maping to dto
    //     * Creation/Modification History :
    //     * 1.1 - Developer Name: Ahmed Abd El-Fatah
    //     * 1.2 - Date:  Aug 27, 2008
    //     * 1.3 - Creation/Modification:
    //     * 1.4-  Description:
    //     * @return
    //     * @throws Exception
    //     */
    //    public void selectedCurrent(ActionEvent event) throws Exception {
    //        super.selectedCurrent(event);
    //
    //        Set s = new HashSet();
    //        s.addAll(this.getEmpDecisionsDTOSlelectedList());
    //        ClientDTO selected =
    //            (ClientDTO)this.getCurrentDataTable().getRowData();
    //        if (selected.getChecked()) {
    //
    //            try {
    //                s.add((selected));
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //        if (!(selected.getChecked())) {
    //            s.remove((selected));
    //        }
    //
    //        this.getEmpDecisionsDTOSlelectedList().clear();
    //        this.getEmpDecisionsDTOSlelectedList().addAll(s);
    //
    //        if (getEmpDecisionsDTOSlelectedList().size() == 1) {
    //            if ((getEmpDecisionsDTOSlelectedList().get(0).getTableName() ==
    //                 null) ||
    //                getEmpDecisionsDTOSlelectedList().get(0).getStatusFlag() !=
    //                null) {
    //                setEmpDecisionsDTOSlelected(getEmpDecisionsDTOSlelectedList().get(0));
    //                setAddEmpDecisionsDTOModule(false);
    //            }
    //            //            if(getIEmpDecisionsDTOSlelectedList().get(0).getStatusFlag() != null){
    //            //                setDeleteIEmpDecisionsDTO(false);
    //            //            }
    //        }
    //    }

    public void setEmpDecisionsDTOSlelected(IEmpDecisionsDTO empDecisionsDTOSlelected) {
        this.empDecisionsDTOSlelected = empDecisionsDTOSlelected;
    }

    public IEmpDecisionsDTO getEmpDecisionsDTOSlelected() {
        return empDecisionsDTOSlelected;
    }

    public void setAddEmpDecisionsDTOModule(boolean addEmpDecisionsDTOModule) {
        this.addEmpDecisionsDTOModule = addEmpDecisionsDTOModule;
    }

    public boolean isAddEmpDecisionsDTOModule() {
        return addEmpDecisionsDTOModule;
    }

    //
    //    /**
    //     * Purpose: assign employee to eg Module
    //     * Creation/Modification History :
    //     * 1.1 - Developer Name: Ahmed Abd El-Fatah
    //     * 1.2 - Date:  Aug 27, 2008
    //     * 1.3 - Creation/Modification:
    //     * 1.4-  Description:
    //     * @return
    //     * @throws
    //     */
    //    public void empIDecisionsDTOModule() {
    //        regulationModulesMaintainBean.getSelectedAvailableDetailsRowIndices().get(0);
    //        int rowIndex =
    //            regulationModulesMaintainBean.getSelectedAvailableDetailsRowIndices().get(0);
    //        Vector rowData =
    //            (Vector)regulationModulesMaintainBean.getCustomAvailableDetails().get(rowIndex);
    //        Long serial = getRowSerial(rowData);
    //        getEmpDecisionsDTOSlelected().setTableName(regulationModulesMaintainBean.getSelectedTableKey());
    //        getEmpDecisionsDTOSlelected().setTabrecSerialRef(serial);
    //        resetSelection();
    //    }
    //
    //    private Long getRowSerial(Vector row) {
    //        return Long.parseLong(row.get(row.size() - 1).toString());
    //    }

    public void setEmpDecisionsDTOSlelectedList(List<IEmpDecisionsDTO> empIDecisionsDTOSlelectedList) {
        this.empDecisionsDTOSlelectedList = empIDecisionsDTOSlelectedList;
    }

    public List<IEmpDecisionsDTO> getEmpDecisionsDTOSlelectedList() {
        return empDecisionsDTOSlelectedList;
    }

    //    /**
    //     * Purpose: ReInitialize RegulationModulesMaintainBean
    //     * Creation/Modification History :
    //     * 1.1 - Developer Name: Ahmed Abd El-Fatah
    //     * 1.2 - Date:  Aug 27, 2008
    //     * 1.3 - Creation/Modification:
    //     * 1.4-  Description:
    //     * @return
    //     * @throws
    //     */

    public void resetRegModuleDiv() {
        FacesContext.getCurrentInstance().getApplication().createValueBinding("#{regulationModulesMaintainBean}").setValue(FacesContext.getCurrentInstance(),
                                                                                                                           new RegulationModulesMaintain());
    }

    //    public void setShowBarMainData(boolean showBarMainData) {
    //        this.showBarMainData = showBarMainData;
    //    }
    //
    //    public boolean isShowBarMainData() {
    //        return showBarMainData;
    //    }


    /**
     * Purpose: will be called from the employee search bean after selecteding employee and added
     * Creation/Modification History :
     * 1.1 - Developer Name:  OmarEzzEldin
     * 1.2 - Date:
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    //   public String employeeAddMethod() {

    //        try {
    //            setCancelDecisionMode(false);
    ////            AdvanceEmpSearch advanceEmployeesAddBean =
    ////                (AdvanceEmpSearch)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
    //                                                                                                        "specialExtraEmpDecisionCycleAddBean" +
    //                                                                                                        "}").getValue(FacesContext.getCurrentInstance());
    //
    ////            DecisionEmployeesModel decisionEmployeesModelSessionBean =
    ////                (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");
    //
    //            DecisionCycleMaintainBean decisionBean =
    //                (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{settlementDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
    //            SpecialExtraDecisionListBean decisionListBean =
    //                (SpecialExtraDecisionListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{specialExtraDecisionListBean}").getValue(FacesContext.getCurrentInstance());
    //
    ////            List<IEmployeesDTO> employeesList =
    ////                (List)advanceEmployeesAddBean.getCurrentDetails();
    //
    //            List<IBasicDTO> empDecisionListList = null ;
    //                //(List<IBasicDTO>)advanceEmployeesAddBean.getSavedData().get(11);
    ////            setCurrentDetails(empDecisionListList);
    //            if (employeesList != null && employeesList.size() > 0) {
    //                for (IEmployeesDTO emp: employeesList) {
    //                    if (emp.getStatusFlag() != null &&
    //                        emp.getStatusFlag().equals(added) &&
    //                        getCurrentSelectedDetail(emp) == null) {
    //                        IEmpDecisionsDTO empIDecisionsDTO =
    //                            (IEmpDecisionsDTO)mapToDetailDTO(emp);
    //                        empIDecisionsDTO.setBooleanInformEmpFlag(false);
    //                        empIDecisionsDTO.setStatusFlag(emp.getStatusFlag());
    //                        empDecisionListList.add(empIDecisionsDTO);
    ////                        if (!decisionEmployeesModelSessionBean.checkExistData(emp)) {
    ////                            setCurrentPageIndex(decisionEmployeesModelSessionBean.determineNoOfPaqe());
    ////                            decisionEmployeesModelSessionBean.navigateToNextPage(new Long(getCurrentPageIndex()));
    ////                            decisionEmployeesModelSessionBean.addNewElement(empIDecisionsDTO);
    ////                        }
    //                    }
    //                }
    ////                setCurrentDetails(empDecisionListList);
    //            }
    //
    ////            decisionBean.setPageDTO((IDecisionsDTO)advanceEmployeesAddBean.getSavedData().get(0));
    ////            ((IDecisionsDTO)decisionBean.getPageDTO()).setEmpDecisionsDTOList(getCurrentDetails());
    //
    //            if (decisionBean.getPageDTO() != null &&
    //                decisionBean.getPageDTO().getCode() != null)
    //                setMasterEntityKey(decisionBean.getPageDTO().getCode());
    //
    ////            Integer mode = (Integer)advanceEmployeesAddBean.getSavedData().get(1);
    ////            getWizardBar().setWizardSteps((Map)advanceEmployeesAddBean.getSavedData().get(3));
    ////            getWizardBar().setConfigurationId(advanceEmployeesAddBean.getSavedData().get(2).toString());
    ////            decisionBean.setMaintainMode(mode.intValue());
    //
    ////            decisionBean.setShowPrevious(((Boolean)advanceEmployeesAddBean.getSavedData().get(4)).booleanValue());
    ////            decisionBean.setRenderFinish(((Boolean)advanceEmployeesAddBean.getSavedData().get(5)).booleanValue());
    ////            decisionBean.setRenderSave(((Boolean)advanceEmployeesAddBean.getSavedData().get(6)).booleanValue());
    ////            if (advanceEmployeesAddBean.getSavedData().get(7) != null)
    ////                decisionBean.setNextNavigationCase(advanceEmployeesAddBean.getSavedData().get(7).toString());
    ////
    ////            if (advanceEmployeesAddBean.getSavedData().get(8) != null)
    ////                decisionBean.setPreviousNavigationCase(advanceEmployeesAddBean.getSavedData().get(8).toString());
    ////            if (advanceEmployeesAddBean.getSavedData().get(9) != null)
    ////                decisionBean.setFinishNavigationCase(advanceEmployeesAddBean.getSavedData().get(9).toString());
    ////            if (advanceEmployeesAddBean.getSavedData().get(10) != null)
    ////                decisionBean.setCurrentNavigationCase(advanceEmployeesAddBean.getSavedData().get(10).toString());
    ////            /////////////////////////////////////////////
    ////            if (advanceEmployeesAddBean.getSavedData().get(12) != null)
    ////                decisionListBean.setIndexArray((boolean[])advanceEmployeesAddBean.getSavedData().get(12));
    ////            if (advanceEmployeesAddBean.getSavedData().get(13) != null)
    ////                decisionListBean.setSortColumn(advanceEmployeesAddBean.getSavedData().get(13).toString());
    ////            if (advanceEmployeesAddBean.getSavedData().get(14) != null)
    ////                decisionListBean.setSaveSortingState(((Boolean)advanceEmployeesAddBean.getSavedData().get(14)).booleanValue());
    ////            if (advanceEmployeesAddBean.getSavedData().get(15) != null)
    ////                decisionListBean.setSortAscending(((Boolean)advanceEmployeesAddBean.getSavedData().get(15)).booleanValue());
    ////            if (advanceEmployeesAddBean.getSavedData().get(16) != null)
    ////                decisionListBean.setFullColumnName(advanceEmployeesAddBean.getSavedData().get(16).toString());
    ////
    //
    //            decisionBean.setCurrentStep("settlementemployeesadd");
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        this.getWizardBar().setCurrentStep("settlementemployeesadd");
    //        return "settlementDecisionCycleEmployeesMaintainBean";

    //   }
    //
    public String backFromSearch() {

        setCancelDecisionMode(false);

        //        List<IBasicDTO> employeesList1 = new ArrayList<IBasicDTO>();
        //        if (advanceEmployeesAddBean.getSavedData().get(11) != null) {
        //            setCurrentDetails((List<IBasicDTO>)advanceEmployeesAddBean.getSavedData().get(11));
        //        } else {
        //            setCurrentDetails(new ArrayList<IBasicDTO>());
        //        }
        //         PagingRequestDTO request = null;
        //         initEmpBean(request);
        if (getCurrentPageIndex() != 1) {
            getCurrentDataTable().setFirst(getCurrentPageIndex());
        }
        setUpdateMyPagedDataModel(true);
        //        SettlementDecisionsListBean decisionListBean =
        //            (SettlementDecisionsListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{settlementDecisionsListBean}").getValue(FacesContext.getCurrentInstance());
        DecisionCycleMaintainBean decisionBean =
            (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{settlementDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
        //        if (decisionBean != null)
        //            decisionBean.setPageDTO((IDecisionsDTO)advanceEmployeesAddBean.getSavedData().get(0));
        ((IDecisionsDTO)decisionBean.getPageDTO()).setEmpDecisionsDTOList(getCurrentDetails());

        //        Integer mode = (Integer)advanceEmployeesAddBean.getSavedData().get(1);
        //        getWizardBar().setWizardSteps((Map)advanceEmployeesAddBean.getSavedData().get(3));
        //        getWizardBar().setConfigurationId(advanceEmployeesAddBean.getSavedData().get(2).toString());
        //        decisionBean.setMaintainMode(mode.intValue());
        //
        //        decisionBean.setShowPrevious(((Boolean)advanceEmployeesAddBean.getSavedData().get(4)).booleanValue());
        //        decisionBean.setRenderFinish(((Boolean)advanceEmployeesAddBean.getSavedData().get(5)).booleanValue());
        //        decisionBean.setRenderSave(((Boolean)advanceEmployeesAddBean.getSavedData().get(6)).booleanValue());
        //        if (advanceEmployeesAddBean.getSavedData().get(7) != null)
        //            decisionBean.setNextNavigationCase(advanceEmployeesAddBean.getSavedData().get(7).toString());
        //
        //        if (advanceEmployeesAddBean.getSavedData().get(8) != null)
        //            decisionBean.setPreviousNavigationCase(advanceEmployeesAddBean.getSavedData().get(8).toString());
        //        if (advanceEmployeesAddBean.getSavedData().get(9) != null)
        //            decisionBean.setFinishNavigationCase(advanceEmployeesAddBean.getSavedData().get(9).toString());
        //        if (advanceEmployeesAddBean.getSavedData().get(10) != null)
        //            decisionBean.setCurrentNavigationCase(advanceEmployeesAddBean.getSavedData().get(10).toString());
        //
        //
        //        decisionListBean.setIndexArray((boolean[])advanceEmployeesAddBean.getSavedData().get(12));
        //        decisionListBean.setSortColumn(advanceEmployeesAddBean.getSavedData().get(13).toString());
        //        decisionListBean.setSaveSortingState(((Boolean)advanceEmployeesAddBean.getSavedData().get(14)).booleanValue());
        //        decisionListBean.setSortAscending(((Boolean)advanceEmployeesAddBean.getSavedData().get(15)).booleanValue());
        //        if (advanceEmployeesAddBean.getSavedData().get(16) != null) {
        //            decisionListBean.setFullColumnName(advanceEmployeesAddBean.getSavedData().get(16).toString());
        //        }
        decisionBean.setCurrentStep("settlementemployeesadd");
        this.getWizardBar().setCurrentStep("settlementemployeesadd");
        return "settlementDecisionCycleEmployeesMaintainBean";
    }
    //
    //    public void setAdvanceEmployeesAddBean(AdvanceEmpSearch advanceEmployeesAddBean) {
    //        this.advanceEmployeesAddBean = advanceEmployeesAddBean;
    //    }
    //
    //    public AdvanceEmpSearch getAdvanceEmployeesAddBean() {
    //        return advanceEmployeesAddBean;
    //    }

    //    public void setUnDeleteEmpList(List unDeleteList) {
    //        this.unDeleteEmpList = unDeleteList;
    //    }
    //
    //    public List getUnDeleteEmpList() {
    //        return unDeleteEmpList;
    //    }

    //    public void setValueNum(Long valueNum) {
    //        this.valueNum = valueNum;
    //    }
    //
    //    public Long getValueNum() {
    //        return valueNum;
    //    }
    //
    //    public void setValueChr(String valueChr) {
    //        this.valueChr = valueChr;
    //    }
    //
    //    public String getValueChr() {
    //        return valueChr;
    //    }
    //
    //    public void setValueDate(Date valueDate) {
    //        this.valueDate = valueDate;
    //    }
    //
    //    public Date getValueDate() {
    //        return valueDate;
    //    }
    //
    //    public void setEmpInfTypeCode(Long empInfTypeCode) {
    //        this.empInfTypeCode = empInfTypeCode;
    //    }
    //
    //    public Long getEmpInfTypeCode() {
    //        return empInfTypeCode;
    //    }
    //
    //    public void setBooleanInformEmpFlag(Boolean booleanInformEmpFlag) {
    //        this.booleanInformEmpFlag = booleanInformEmpFlag;
    //    }
    //
    //    public Boolean getBooleanInformEmpFlag() {
    //        return booleanInformEmpFlag;
    //    }
    //
    //    public void setInformEmpFlag(Long informEmpFlag) {
    //        this.informEmpFlag = informEmpFlag;
    //    }
    //
    //    public Long getInformEmpFlag() {
    //        return informEmpFlag;
    //    }
    //
    //    public void setInformEmpDate(Date informEmpDate) {
    //        this.informEmpDate = informEmpDate;
    //    }
    //
    //    public Date getInformEmpDate() {
    //        return informEmpDate;
    //    }
    //
    //    public void setNotes(String notes) {
    //        this.notes = notes;
    //    }
    //
    //    public String getNotes() {
    //        return notes;
    //    }

    //    public void setInformTypeList(List<IBasicDTO> informTypeList) {
    //        this.informTypeList = informTypeList;
    //    }
    //
    //    public List<IBasicDTO> getInformTypeList() {
    //        try {
    //
    //            informTypeList =
    //                    RegClientFactory.getRegEmpInformTypesClient().getAll();
    //
    //        } catch (SharedApplicationException e) {
    //            informTypeList = new ArrayList();
    //            e.printStackTrace();
    //        } catch (DataBaseException e) {
    //            informTypeList = new ArrayList();
    //            e.printStackTrace();
    //        }
    //        return informTypeList;
    //    }

    //    public void setActiveValidationOnAdd(boolean activeValidationOnAdd) {
    //        this.activeValidationOnAdd = activeValidationOnAdd;
    //    }
    //
    //    public boolean isActiveValidationOnAdd() {
    //        return activeValidationOnAdd;
    //    }
    //
    //    public void setActiveValidationOnEdit(boolean activeValidationOnEdit) {
    //        this.activeValidationOnEdit = activeValidationOnEdit;
    //    }
    //
    //    public boolean isActiveValidationOnEdit() {
    //        return activeValidationOnEdit;
    //    }

    public void setCivilID(Long civilID) {
        this.civilID = civilID;
    }

    public Long getCivilID() {
        return civilID;
    }

    public void findEmployeeByCivilIDFromEmployeesForEdit(IEmpDecisionsDTO empDecisionsDTO){//Long civilId) {
        setResetButton(true);
        try {
            //IEmployeesEntityKey employeesEntityKey = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
            employeesDTO = (IEmployeesDTO)empDecisionsDTO.getEmployeesDTO();
                    //(IEmployeesDTO)EmpClientFactory.getEmployeesClient().getById(employeesEntityKey);
            if (employeesDTO != null) {
                setCivilID(employeesDTO.getRealCivilId() );
                setEmpName(employeesDTO.getName());//(IKwtCitizensResidentsDTO)getCitizensResidentsDTO()).getFullName());
            }
         //   setDisapleDetailsButton(false);
            setShowErrMsg(false);
            setCivil_exist("");
        } catch (Exception e) {
            e.printStackTrace();
            setEmpName("");
         //   setDisapleDetailsButton(true);
            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "civil_not_found_center"));
            //    findPersonByCivilIDFromEmployees();
        }
    }


    public void resetScreen() {
        setResetButton(false);
        civilExist = false;
        empHired = true;
        empHiredInMin = true;
        payrollInfoExist = true;
        validCivilId  = true;
        setEmpName("");
     //   setDisapleDetailsButton(true);
        setCivil_exist("");
//        setBenfitsList(new ArrayList<ISalElementGuidesDTO>());
//        setBenifitList2(new ArrayList<SalEGDTO>());
//        setDeductionsList(new ArrayList<ISalElementGuidesDTO>());
//        setDeductionsList2(new ArrayList<SalEGDTO>());
        setShowPagingBar(false);
        setCivilID(null);
    }
    //    public void findPersonByCivilIDFromEmployees() {
    //         try {
    ////             DecisionEmployeesModel decisionEmployeesModelSessionBean =
    ////                 (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");
    //             IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
    //                 (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getById(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(getCivilID()));
    //             IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
    //             employeesDTO.setCitizensResidentsDTO(kwtCitizensResidentsDTO);
    //             employeesDTO.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId()));
    //             IHireStatusDTO hireStatusDTO = EmpDTOFactory.createHireStatusDTO();
    //             hireStatusDTO.setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.NOT_EMPLOYEE));
    //             employeesDTO.setHireStatusDTO(hireStatusDTO);
    //             if (currentDetails == null)
    //                 currentDetails = new ArrayList<IBasicDTO>();
    //             IBasicDTO dto = employeesDTO;
    //
    //            // decisionEmployeesModelSessionBean.setExist(decisionEmployeesModelSessionBean.checkExistData(dto));
    //            boolean exist = checkExistData(dto);
    //            if (!exist) {
    //                 IBasicDTO mdto = this.mapToDetailDTO(dto);
    //                 mdto.setStatusFlag(added);
    //                 ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
    //                // decisionEmployeesModelSessionBean.addNewElement(mdto);
    //                List objList = new ArrayList();
    //                 if(getCurrentDetails() != null && getCurrentDetails().size() >0){
    //                     objList = getCurrentDetails();
    //                 }
    //                objList.add(mdto);
    //                setCurrentDetails(objList);
    //                setUpdateMyPagedDataModel(true);
    //             }else {
    //                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,"employee_added_before"));
    //                setCivil_exist(getSharedUtil().messageLocator(BUNDLE,"employee_added_before"));
    //                //setCivilID(null);
    //            }
    //
    //
    //             // goFirstPage(this.getAvailableDataTable());
    //             this.restoreDetailsTablePosition(this.getAvailableDataTable(),
    //                                              availableDetails);
    //             this.resetSelection();
    //            // try {
    ////             if (decisionEmployeesModelSessionBean.isExist()) {
    ////               //  setJavaScriptCaller("changeVisibilityMsg();");
    ////                 //getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,"employee_added_before"));
    ////
    ////                 setCivil_exist(getSharedUtil().messageLocator(BUNDLE,"employee_added_before"));
    ////                 getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,"employee_added_before"));
    ////
    ////
    ////
    ////             }else{
    ////                 setCivilID(null);
    ////                 setCivil_exist("");
    ////             }
    //            setCivilID(null);
    //            setCivil_exist("");
    //             try{
    //                 this.cancelSearchAvailable();
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //             //setCivilID(null);
    //         } catch (ItemNotFoundException e) {
    //             setCivilID(null);
    //             getSharedUtil().setErrMsgValue("civil_not_found_center");
    //             setCivil_exist(getSharedUtil().messageLocator(BUNDLE,"civil_not_found_center"));
    //             e.printStackTrace();
    //         } catch (SharedApplicationException e) {
    //             e.printStackTrace();
    //             // TODO
    //         } catch (DataBaseException e) {
    //             e.printStackTrace();
    //             setCivil_exist(getSharedUtil().messageLocator(BUNDLE,"civil_not_found_center"));
    //             // TODO
    //         }
    //     }

    public boolean checkExistData(IBasicDTO dto) {
        boolean exist = false;
        DecisionCycleMaintainBean decisionBean =
            (DecisionCycleMaintainBean)BeansUtil.getValue("settlementDecisionCycleMaintainBean");
        Long civil = new Long(dto.getCode().getKey());

        try {
            exist =
                    RegClientFactory.getEmpDecisionsClient().checkEixstEmpInDecision(decisionBean.getPageDTO().getCode(),
                                                                                     civil);
        } catch (SharedApplicationException e) {
            exist = false;
            e.printStackTrace();
        } catch (DataBaseException e) {
            exist = false;
            e.printStackTrace();
        }
        return exist;

    }
    //
    //    public void showSearchForEmployeeDiv() {
    //         employeeListOfValuesBean.setReturnMethodName("settlementDecisionCycleEmployeesMaintainBean.returnMethodAction");
    //         employeeListOfValuesBean.resetData();
    //         employeeListOfValuesBean.setUsingBsnPaging(true);
    //         employeeListOfValuesBean.setUsingPaging(true);
    //         employeeListOfValuesBean.setSearchInfCenter(false);
    //     }
    //
    //    public void returnMethodAction1() {
    //        if (getEmpListOfValues().getSelectedDTOS() != null &&
    //            getEmpListOfValues().getSelectedDTOS().get(0) != null &&
    //            getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
    //            List<IBasicDTO> selectedAvailableDetails =
    //                getEmpListOfValues().getSelectedDTOS();
    //            if (currentDetails == null)
    //                currentDetails = new ArrayList<IBasicDTO>();
    //
    //            DecisionEmployeesModel decisionEmployeesModelSessionBean =
    //                (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");
    //            if (selectedAvailableDetails != null)
    ////                for (int i = 0; i < selectedAvailableDetails.size(); i++) {
    ////                    IBasicDTO dto = selectedAvailableDetails.get(i);
    ////                    decisionEmployeesModelSessionBean.setExist(decisionEmployeesModelSessionBean.checkExistData(dto));
    ////                    if (!decisionEmployeesModelSessionBean.isExist()) {
    ////                        IBasicDTO mdto = this.mapToDetailDTO(dto);
    ////                        mdto.setStatusFlag(added);
    ////                        ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
    ////                        decisionEmployeesModelSessionBean.addNewElement(mdto);
    ////                        i--;
    ////                    }
    ////                }
    //            // goFirstPage(this.getAvailableDataTable());
    //            //            this.restoreDetailsTablePosition(this.getAvailableDataTable(),  availableDetails);
    //            //            this.resetSelection();
    //            try {
    //                this.cancelSearchAvailable();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //            setCivilID(null);
    //
    //        }
    //
    //    }


    //    public void returnMethodAction() {
    //        if (employeeSearchBean.getSelectedDTOS() != null &&
    //            employeeSearchBean.getSelectedDTOS().get(0) != null &&
    //            employeeSearchBean.getSelectedDTOS().get(0).getCode() != null) {
    //            List<IBasicDTO> selectedAvailableDetails =
    //                employeeSearchBean.getSelectedDTOS();
    //            if (currentDetails == null)
    //                currentDetails = new ArrayList<IBasicDTO>();
    //
    //            DecisionEmployeesModel decisionEmployeesModelSessionBean =
    //                (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");
    //            if (selectedAvailableDetails != null)
    ////                for (int i = 0; i < selectedAvailableDetails.size(); i++) {
    ////                    IBasicDTO dto = selectedAvailableDetails.get(i);
    ////                    decisionEmployeesModelSessionBean.setExist(decisionEmployeesModelSessionBean.checkExistData(dto));
    ////                    if (!decisionEmployeesModelSessionBean.isExist()) {
    ////                        IBasicDTO mdto = this.mapToDetailDTO(dto);
    ////                        mdto.setStatusFlag(added);
    ////                        ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
    ////                        decisionEmployeesModelSessionBean.addNewElement(mdto);
    ////                        i--;
    ////                    }
    ////                }
    //            // goFirstPage(this.getAvailableDataTable());
    //            //            this.restoreDetailsTablePosition(this.getAvailableDataTable(),  availableDetails);
    //            //            this.resetSelection();
    //            try {
    //                this.cancelSearchAvailable();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //            setCivilID(null);
    //
    //        }
    //
    //    }


    //
    //    ///////////////////////////////////////////////////////////////// add by amr galal //////////////////////////////////////////////////////////

    public void getNextPage(ActionEvent event) {
        //         DecisionEmployeesModel decisionEmployeesModelSessionBean =
        //             (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");
        // setCurrentPageIndex(decisionEmployeesModelSessionBean.determineNoOfPaqe());
        changePageIndex(event);
        //  decisionEmployeesModelSessionBean.navigateToNextPage(new Long(getCurrentPageIndex()));
    }


    //     public int getCurrentListSize() {
    //         DecisionCycleMaintainBean decisionMaintainBean =
    //             (DecisionCycleMaintainBean)BeansUtil.getValue("settlementDecisionCycleMaintainBean");
    //         if(getCurrentDetails() != null && getCurrentDetails().size() !=0 ){
    //             countOfAllEmpDecision = getCurrentDetails().size();
    //         }
    //         DecisionEmployeesModel decisionEmployeesModelSessionBean =
    //             (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");
    //
    //         if (decisionEmployeesModelSessionBean.getOriginalMap().size() == 0L &&
    //             decisionEmployeesModelSessionBean.getNewCurrentListSize() == 0) {
    //             if (decisionMaintainBean.getMaintainMode() == 0) {
    //                 countOfAllEmpDecision = 0;
    //             } else {
    //                 if(!((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().isEmpty()){
    //                    countOfAllEmpDecision =
    //                         ((IEmpDecisionsDTO)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(0)).getCountOfAllPage().intValue();
    //                 }
    //                 else{
    //                     countOfAllEmpDecision = 0;
    //                 }
    //             }
    //         } else if (decisionEmployeesModelSessionBean.getOriginalMap().size() !=
    //                    0L &&
    //                    decisionEmployeesModelSessionBean.getNewCurrentListSize() ==
    //                    0) {
    //             if(!((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().isEmpty()){
    //                countOfAllEmpDecision =
    //                     ((IEmpDecisionsDTO)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(0)).getCountOfAllPage().intValue();
    //             }
    //             else{
    //                 countOfAllEmpDecision = 0;
    //             }
    //             //countOfAllEmpDecision =
    //               //      ((IEmpDecisionsDTO)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(0)).getCountOfAllPage().intValue();
    //         } else {
    //             countOfAllEmpDecision =
    //                     decisionEmployeesModelSessionBean.getNewCurrentListSize();
    //         }
    //         return countOfAllEmpDecision;
    //     }

    public void setEmpEmployeeSearchDTO(IEmpEmployeeSearchDTO empEmployeeSearchDTO) {
        this.empEmployeeSearchDTO = empEmployeeSearchDTO;
    }

    public IEmpEmployeeSearchDTO getEmpEmployeeSearchDTO() {
        return empEmployeeSearchDTO;
    }

    public void setAddedEmpSuccessfully(boolean addedEmpSuccessfully) {
        this.addedEmpSuccessfully = addedEmpSuccessfully;
    }

    public boolean isAddedEmpSuccessfully() {
        return addedEmpSuccessfully;
    }

    //     public void setCountOfAllEmpDecision(int countOfAllEmpDecision) {
    //         this.countOfAllEmpDecision = countOfAllEmpDecision;
    //     }
    //
    //     public int getCountOfAllEmpDecision() {
    //         return countOfAllEmpDecision;
    //     }

    public void setCancelDecisionMode(boolean cancelDecisionMode) {
        this.cancelDecisionMode = cancelDecisionMode;
    }

    public boolean isCancelDecisionMode() {
        return cancelDecisionMode;
    }


    public void setEmployeeSearchBean(EmployeeSearchBean employeeSearchBean) {
        this.employeeSearchBean = employeeSearchBean;
    }

    public EmployeeSearchBean getEmployeeSearchBean() {
        return employeeSearchBean;
    }

    //    public void setElementGuideList(List<IBasicDTO> elementGuideList) {
    //        this.elementGuideList = elementGuideList;
    //    }
    //
    //    public List<IBasicDTO> getElementGuideList() {
    //        try{
    //          if ( elementGuideList.size() == 0 ) {
    //              Long elementTypeCode = IConstants.ELEMENT_TYPE_CODE_FOR_SPEC_EXTRA_DEC;
    //              elementGuideList =
    //                    SalClientFactory.getSalElementGuidesClient().getCodeNameByTypeCode(elementTypeCode);
    //          }
    //        }catch(Exception e){
    //            e.printStackTrace();
    //            elementGuideList = new ArrayList<IBasicDTO>();
    //        }
    //
    //        return elementGuideList;
    //    }


    public void validateVal(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String val = (String)value;
        if (val == null || val.equals("")) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Value does not exists");
            message.setSummary("Insert Value");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    //    public void setSelectedElementCode(String selectedElementCode) {
    //        this.selectedElementCode = selectedElementCode;
    //    }
    //
    //    public String getSelectedElementCode() {
    //        return selectedElementCode;
    //    }

    //    public void setValueNumber(Long valueNumber) {
    //        this.valueNumber = valueNumber;
    //    }
    //
    //    public Long getValueNumber() {
    //        return valueNumber;
    //    }

    //    public void setBsnPagingResponseDTO(com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO) {
    //        this.bsnPagingResponseDTO = bsnPagingResponseDTO;
    //    }
    //
    //    public com.beshara.base.paging.impl.PagingResponseDTO getBsnPagingResponseDTO() {
    //        return bsnPagingResponseDTO;
    //    }

    public void setCivil_exist(String civil_exist) {
        this.civil_exist = civil_exist;
    }

    public String getCivil_exist() {
        return civil_exist;
    }

    //    public void setFelsValue(float felsValue) {
    //        this.felsValue = felsValue;
    //    }
    //
    //    public float getFelsValue() {
    //        return felsValue;
    //    }
    //
    //    public void setDenarValue(float denarValue) {
    //        this.denarValue = denarValue;
    //    }
    //
    //    public float getDenarValue() {
    //        return denarValue;
    //    }

    //    public void setElementGuideKey(String elementGuideKey) {
    //        this.elementGuideKey = elementGuideKey;
    //    }
    //
    //    public String getElementGuideKey() {
    //        return elementGuideKey;
    //    }
    //
    //    public void setShowElementGuideErrMsg(boolean showElementGuideErrMsg) {
    //        this.showElementGuideErrMsg = showElementGuideErrMsg;
    //    }
    //
    //    public boolean isShowElementGuideErrMsg() {
    //        return showElementGuideErrMsg;
    //    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

  
    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

  

    public void setShowPagingBar(boolean disaplePagingBar) {
        this.showPagingBar = disaplePagingBar;
    }

    public boolean isShowPagingBar() {
        return showPagingBar;
    }

    public void setResetButton(boolean resetButton) {
        this.resetButton = resetButton;
    }

    public boolean isResetButton() {
        return resetButton;
    }
  

  


   

    
    public void showRemoveAllConfirmation() {
        setJavaScriptCaller("\"javascript:changeVisibilityDiv(window.blocker,window.customDiv1);return false;");
    }

   
    public void setDecisionMaintainBean(DecisionCycleMaintainBean decisionMaintainBean) {
        this.decisionMaintainBean = decisionMaintainBean;
    }

    public DecisionCycleMaintainBean getDecisionMaintainBean() {
        return decisionMaintainBean;
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

    public void setPayrollInfoExist(boolean payrollInfoExist) {
        this.payrollInfoExist = payrollInfoExist;
    }

    public boolean isPayrollInfoExist() {
        return payrollInfoExist;
    }

    public void setEmpHiredInMin(boolean empHiredInMin) {
        this.empHiredInMin = empHiredInMin;
    }

    public boolean isEmpHiredInMin() {
        return empHiredInMin;
    }

    public void setEmpHired(boolean empHired) {
        this.empHired = empHired;
    }

    public boolean isEmpHired() {
        return empHired;
    }
    /** start of change  Nora ismail 13-3-2016 **/
    public List<IBasicDTO> getCurrentDisplayDetails() {

        currentDisplayDetails = new ArrayList<IBasicDTO>(0);

        if (currentDetails != null) {
            for (IBasicDTO dto : currentDetails) {
                if (dto.getStatusFlag() == null)
                    currentDisplayDetails.add(dto);
                if (dto.getStatusFlag() != null && dto.getStatusFlag().longValue() == added.longValue())
                    currentDisplayDetails.add(dto);
            }
        }
        return currentDisplayDetails;

    }
    
    
    public void selectedCurrentAll(ActionEvent event) throws Exception {
        event = null; // unused
        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedCurrentDetails());


            int first = this.getCurrentDataTable().getFirst();

            Integer rowsCountPerPage=(Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());
            //Integer rowsCountPerPage = getCurrentDataTable().getRowCount();
            if (rowsCountPerPage == null) {

                throw new Exception("#{shared_util.noOfTableRows} return null");
            }
            int count = rowsCountPerPage.intValue();
            System.out.println((first + count));
            for (int j = first; j < first + count; j++) {
                System.out.println(j);
                this.getCurrentDataTable().setRowIndex(j);
                if (!this.getCurrentDataTable().isRowAvailable())
                    break;

                IBasicDTO selected = (IBasicDTO)this.getCurrentDataTable().getRowData();
                 System.out.println(selected.getCode().getKey());
                if (isCheckAllFlag() == true) {
                    s.add(this.mapToCodeNameDTO(selected));

                }


                if (isCheckAllFlag() == false) {
                    s.remove(this.mapToCodeNameDTO(selected));
                }


            }

            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().addAll(s);


        } catch (Exception e) {

            System.out.println(e.toString());

        }


    }

    //using hashset to solve the problem of duplicate selected items in the datatable  added by  aboelhassan hamed applied in the crs module

    public void selectedCurrent(ActionEvent event) throws Exception {
        event = null; // unused
        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedCurrentDetails());


            IClientDTO selected = (IClientDTO)this.getCurrentDataTable().getRowData();


            if (selected.getChecked()) {


                try {
                    s.add(this.mapToCodeNameDTO(selected));

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            if (!(selected.getChecked())) {


                s.remove(this.mapToCodeNameDTO(selected));


            }

            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().addAll(s);


        } catch (Exception e) {
            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }


    }
    
    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
       
        return ((IEmpDecisionsDTO)dto).getEmployeesDTO();
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
     
        IEmpDecisionsDTO empIDecisionsDTO = RegDTOFactory.createEmpDecisionsDTO();
        empIDecisionsDTO.setEmployeesDTO((IEmployeesDTO)dto);
        IDecisionsDTO decisionsDTO = null;
        IDecisionsEntityKey decisionsEntityKey = null;
        IEmpDecisionsEntityKey empDecisionsEntityKey = null;

        if (this.getMasterEntityKey() != null) {
            decisionsEntityKey = (IDecisionsEntityKey)this.getMasterEntityKey();
            decisionsDTO = RegDTOFactory.createDecisionsDTO();
            decisionsDTO.setCode(decisionsEntityKey);
            empIDecisionsDTO.setDecisionsDTO(decisionsDTO);

        }

        if (decisionsEntityKey != null) {
            empDecisionsEntityKey =
                    RegEntityKeyFactory.createEmpDecisionsEntityKey(decisionsEntityKey.getDectypeCode(),
                                                                    decisionsEntityKey.getDecyearCode(),
                                                                    decisionsEntityKey.getDecisionNumber(),
                                                                    Long.valueOf(dto.getCode().getKey().toString()));
        } else {
            empDecisionsEntityKey =
                    RegEntityKeyFactory.createEmpDecisionsEntityKey(null, null, null, Long.valueOf(dto.getCode().getKey().toString()));
        }
        empIDecisionsDTO.setCode(empDecisionsEntityKey);

        return empIDecisionsDTO;
    }
    public void cancelSearchAvailable() throws DataBaseException, SharedApplicationException {
        setAvailableDetails(new ArrayList<IBasicDTO>());
        setSearchMode(false);
        getSelectedCurrentDetails().clear();
    }
    
    
    
    public void findEmployeeByCivilIDFromEmployees() {
        setResetButton(true);
        empAddedBefore=false;
        civilExist = false;
        validCivilId  = true;
        empHired = true;
        empHiredInMin = true;
        payrollInfoExist = true;
        setEmpName("");
        try {
            employeesDTO = EmployeeHelper.getHiredAndHavePayrollInfoEmp(getCivilID());
                    //(IEmployeesDTO)EmpClientFactory.getEmployeesClient().getByRealCivilIdAllMinistries(getCivilID());
            if (employeesDTO != null) {
                setEmpName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getFullName());
            }
           
           int resMode=civilExistBefore(getCivilID());
            
            if(resMode==1){
                    empAddedBefore=true;
                    return;
                }
            else if(resMode ==0){
                 addItemToCurrentDetail(employeesDTO);
            }
          //  setDisapleDetailsButton(false);
            setShowErrMsg(false);
            setCivilID(null);
            setCivil_exist("");
            
        }   catch (EmployeeNotHiredInMinException e) {
            empHiredInMin = false;
            setResetButton(false);
            setEmpName("");
        }   catch (EmployeeNotHiredException e) {
            empHired = false; 
            setResetButton(false);
            setEmpName("");
        }   catch (EmployeeCivilIdNotExistException e) {
            civilExist = false;
            setResetButton(false);
            validCivilId = false;
            setEmpName("");
        }  catch (EmployeePayrollInfoNotExistException e) {
            payrollInfoExist = false;
            setResetButton(false);
            setEmpName("");
        } catch (Exception e) {
            e.printStackTrace();
            setResetButton(false);
            setEmpName("");
       //     setDisapleDetailsButton(true);
            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "civil_not_found_center"));
            //    findPersonByCivilIDFromEmployees();
        }

        try {
            cancelSearchAvailable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private int civilExistBefore(Long realCivilId) {

        if (!currentDetails.isEmpty()) {
            for (IBasicDTO dto : currentDetails) {
                Long currentRealCivilId =
                    ((IKwtCitizensResidentsEntityKey)((IEmployeesDTO)((IEmpDecisionsDTO)dto).getEmployeesDTO()).getCitizensResidentsDTO().getCode()).getCivilId();
                if (currentRealCivilId.equals(realCivilId)) {
                    if (dto.getStatusFlag() == null || dto.getStatusFlag().equals(added)) {
                        return 1; // means civil exist
                    } else if (dto.getStatusFlag().equals(deleted)) {
                        dto.setStatusFlag(null);
                        return 2;// deleted then added 
                    }
                }
            }
        }
        return 0; // means new Civil
    }
    private void addItemToCurrentDetail(IEmployeesDTO employeesDTO){
            IBasicDTO mdto = this.mapToDetailDTO(employeesDTO);
             mdto.setStatusFlag(added);
        if (currentDetails == null) {
            currentDetails = new ArrayList();
        }
             currentDetails.add(mdto);
        }

    
    public void showDeleteDiv(){}
    public void delete() {
        List<IBasicDTO> list = currentDetails;
        setHighlightedDTOS(new ArrayList());
        try {
            if (currentDetails == null)
                setCurrentDetails(new ArrayList<IBasicDTO>());
 
            Long _civilId = null;
            for (IBasicDTO bassicDTO : getSelectedCurrentDetails()) {
                IEmployeesDTO dto = (IEmployeesDTO)bassicDTO;
                if (dto.getStatusFlag() == null) {
                    for (IBasicDTO basDTO : list) {
                        IEmpDecisionsDTO empDecDTO = (IEmpDecisionsDTO)basDTO;
                        if (dto.getCode() != null) {
                            _civilId = Long.parseLong(dto.getCode().getKey());
                        } else if (dto.getCitizensResidentsDTO() != null) {
                            _civilId = Long.parseLong(dto.getCitizensResidentsDTO().getCode().getKey());
                        }
                        if (((IEmpDecisionsEntityKey)empDecDTO.getCode()).getCivilId().equals(_civilId)) {
                            if (empDecDTO.getStatusFlag() == null) {
                                empDecDTO.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                                getSelectedCurrentDetails().remove(empDecDTO);
                            }
                            if (empDecDTO.getStatusFlag().longValue() == ISystemConstant.ADDED_ITEM.longValue()) {
                                getCurrentDetails().remove(empDecDTO);
                                getSelectedCurrentDetails().remove(empDecDTO);
                            }
                            break;
                        }
                    }
                }
            }
            this.restoreDetailsTablePosition(this.getCurrentDataTable(), this.getCurrentDetails());
            this.resetSelection();
//            PagingResponseDTO responseDTO = new PagingResponseDTO();
//            responseDTO.setResultList(getCurrentDisplayDetails());
//            responseDTO.setTotalListSize(getCurrentListSize());
//            this.getPagingBean().updateMyPadgedDataModel(responseDTO);
//          getCurrentDataTable().setValue(getCurrentDisplayDetails());
            getCurrentDataTable().setFirst(0);
            cancelSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    public void cancelSearch() throws DataBaseException {
        setSearchMode(false);
        getHighlightedDTOS().clear();
    }

    public void getPageIndex(String key, List totalList) throws DataBaseException {

        int rows = getSharedUtil().getNoOfTableRows();

        int totalListSize = 0;

        if (isUsingPaging()) {
            totalListSize = getPagingBean().getTotalListSize();
        } else {
            totalListSize = getCurrentDisplayDetails().size();
        }

        if (totalListSize > 0 && getCurrentDataTable() != null) {

            int noOfPages = 0;

            if (isUsingPaging()) {
                noOfPages = getPagingBean().getPagesCount();
            } else {
                noOfPages = ((totalListSize - 2) / rows) + 1;
            }

            int index = 0;

            if (!isUsingPaging()) {
                int curIndex=getCurrentPageIndex();
                if (totalList != null) { // using paging

                    index = getItemIndex(key, totalList);
                    int x=index;
                    curIndex = ++x / rows;

                    if (index % rows > 0) {
                        ++curIndex;
                    }
                   setCurrentPageIndex(curIndex);
                    setOldPageIndex( curIndex);

                } else {
                    index = getItemIndex(key);
                }
            }

            if (isUsingPaging()) {

                int firstRow = 0;

                if (isUsingBsnPaging()) {
                    firstRow = (getCurrentPageIndex() - 1) * rows;
                } else {
                    if (totalListSize > 0) {

                        int tempPagesCount = index / rows;

                        if (index % rows > 0) {
                            ++tempPagesCount;
                        }

                        firstRow = (tempPagesCount - 1) * rows;

                    }
                }

                getCurrentDataTable().setFirst(firstRow);

            } else {

                for (int i = 0; i < noOfPages; i++) {

                    if (index == rows * i) {
                        getCurrentDataTable().setFirst(rows * i);
                    } else if (index == (rows * (i + 1))) {
                        getCurrentDataTable().setFirst(rows * (i + 1));
                    } else if (index > rows * i && index < rows * (i + 1)) {
                        getCurrentDataTable().setFirst(rows * i);
                    }

                }

            }
        }

    }
    public int getItemIndex(String key, List totalList) throws DataBaseException {

        int index = 0;

        for (int b = 0; b < totalList.size(); b++) {

            IEmpDecisionsDTO dto = (IEmpDecisionsDTO)totalList.get(b);
            String currentRealCivilId= ((IEmployeesDTO)dto.getEmployeesDTO()).getCitizensResidentsDTO().getCode().getKey();

            if (currentRealCivilId != null && key != null &&currentRealCivilId.equals(key)) {

                index = b;
                break;
            }
        }
        System.out.println(index);
        return index;

    }
    
    public String gotoSettlementDetail(){

        try {
           
            com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.details.SettlementDetailsBean settlementDetailsBean =
                (com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.details.SettlementDetailsBean)evaluateValueBinding("settlementDetailsBean");
            IEmpDecisionsDTO dto = (IEmpDecisionsDTO)this.getCurrentDataTable().getRowData();
            settlementDetailsBean.initData((IEmpDecisionsDTO)getSharedUtil().deepCopyObject(dto),
                    decisionMaintainBean.getMaintainMode());
            return "sett_Detail_Page";
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        return null;
        }
   
    public void updateCurrentDetail(IEmpDecisionsDTO dto){  
        String realCivilId=  ((IEmployeesDTO)((IEmpDecisionsDTO)dto).getEmployeesDTO()).getCitizensResidentsDTO().getCode().getKey();
        try {
            int index= this.getItemIndex(realCivilId,currentDetails);
            currentDetails.set(index, dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEmpAddedBefore(Boolean empAddedBefore) {
        this.empAddedBefore = empAddedBefore;
    }

    public Boolean getEmpAddedBefore() {
        return empAddedBefore;
    }
}
