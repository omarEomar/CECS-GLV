package com.beshara.csc.nl.reg.presentation.backingbean.decision.details;

import com.beshara.base.dto.ClientDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStatusDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.bnk.business.client.BnkClientFactory;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.IEmpDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.details.advanceSearch.AdvanceEmployeesAddBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulation.details.RegulationModulesMaintain;
import com.beshara.csc.nl.reg.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.sql.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


public class DecisionEmployeesMaintain extends ManyToManyDetailsMaintain {

    private static final Long KUWITY = 1L;
    private static final Long NON_KUWITY = 0L;
    private static final Long NATIONLALTY_KUWITY = 
        ISystemConstant.KUWAIT_NATIONALITY;
    private Long added = ISystemConstant.ADDED_ITEM;
    private Long kuwityType;
    private IEmployeeSearchDTO employeeSearchDTO = 
        EmpDTOFactory.createEmployeeSearchDTO();
    private List<IBasicDTO> maritalStatusTyps = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> genderTyps = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> relgionTyps = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> governments = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> capTyps = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> specialCaseTyps = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> nationalties = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> resdientTypes = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> categories = new ArrayList<IBasicDTO>();
    private Long categoryID;
    private List<IBasicDTO> ministries = new ArrayList<IBasicDTO>();
    private Long ministryID;
    private List<IBasicDTO> workMinistries = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> hireTypes = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> hireStatuses = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> hireCurrentStatuses = new ArrayList<IBasicDTO>();
    private List<IJobsDTO> technicalJobs = new ArrayList<IJobsDTO>();
    private List<IBasicDTO> banks = new ArrayList<IBasicDTO>();
    private Long bankID;
    private List<IBasicDTO> branches = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> jobCategories = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> jobGroupes = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> currentDegrees = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> budgetTypes = new ArrayList<IBasicDTO>();
    private HtmlDataScroller dataScroller;
    private Long itemSelectionRequiredValue = ISystemConstant.VIRTUAL_VALUE;
    private boolean cancelDecisionMode;
    private boolean isSearchMode;
    private boolean showErrMsg;
    private IEmpDecisionsDTO empDecisionsDTOSlelected = 
        RegDTOFactory.createEmpDecisionsDTO();
    private List<IEmpDecisionsDTO> empDecisionsDTOSlelectedList = 
        new ArrayList<IEmpDecisionsDTO>();
    private boolean addEmpDecisionsDTOModule = true;
    //private boolean deleteIEmpDecisionsDTO = true; // remove it we will handle deletion with another way added by nora
    RegulationModulesMaintain regulationModulesMaintainBean = 
        (RegulationModulesMaintain)evaluateValueBinding("regulationModulesMaintainBean");
    AdvanceEmployeesAddBean advanceEmployeesAddBean = 
        (AdvanceEmployeesAddBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                                       "advanceEmployeesAddBean" + 
                                                                                                       "}").getValue(FacesContext.getCurrentInstance());
    private boolean showBarMainData = 
        true; // added by nora for handling integeration to show /hide operationBar
    private List unDeleteEmpList = new ArrayList();
    private Long valueNum;
    private String valueChr;
    private Date valueDate;
    private Long empInfTypeCode;
    private Boolean booleanInformEmpFlag;
    private Long informEmpFlag;
    private Date informEmpDate;
    private String notes;
    private List<IBasicDTO> informTypeList = new ArrayList<IBasicDTO>();
    private boolean activeValidationOnAdd = false;
    private boolean activeValidationOnEdit = false;
    /////////////////////////////////////////////////////////////////////////////////////
    private Long civilID;
    private EmployeeListOfValuesPaging employeeListOfValuesBean;
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;
    private IEmpEmployeeSearchDTO empEmployeeSearchDTO = 
        EmpDTOFactory.createEmpEmployeeSearchDTO();
    private boolean addedEmpSuccessfully;
    private int countOfAllEmpDecision = 0;

    public DecisionEmployeesMaintain() {
        setClient(RegClientFactory.getEmpDecisionsClient());
        setEmpDecisionsDTOSlelected(RegDTOFactory.createEmpDecisionsDTO());

        setSaveSortingState(true);
        if (kuwityType == null) {
            kuwityType = KUWITY;
            employeeSearchDTO.setNationality(NATIONLALTY_KUWITY);
        }
        //setDivMainContentMany("divDECEmployeesMainContent");
        if (DecisionMaintainBean.isIntegrationPage()) {
            setDivMainContentMany("divContent1FullSizeWithScrollAndNavigationBtnsAndWizard");
        } else {
            setDivMainContentMany("divDecisionEmpMainContentManyCustom");
        }
        setLookupEditDiv("empInformDivClass");
        setLovDiv("divCustom");
        ///////////////////////////////////////////////////
        setEmpListOfValues(new EmployeeListOfValuesPaging());
        employeeListOfValuesBean = 
                (EmployeeListOfValuesPaging)getEmpListOfValues();
        setUsingPaging(true);
        setUsingBsnPaging(true);
        setPagingRequestDTO(new PagingRequestDTO("initEmpBean"));
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        String viewId = 
            FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (viewId.equals("/module/reg/jsps/decision/selectdecisionemployee.jsp")) {
            //app.showManyToManyMaintain();
            app.setShowContent1(true);
            app.setShowdatatableContent(true);
            app.setManyToMany(true);
            //app.setShowCommonData(true);

            app.setShowCustomDiv1(true);
        } else if (viewId.equals("/module/reg/jsps/decision/decisionemployeesmaintaincancel.jsp")) {
            app.showManyToManyMaintain();
            app.setShowContent1(false);
            app.setShowDelConfirm(false);
            app.setShowSearch(false);
            app.setShowbar(false);
        }
        if (viewId.equals("/module/jsps/decision/selectdecisionemployee.jsp") || 
            viewId.equals("/module/jsps/decision-integration/selectdecisionemployee.jsp")) {
            //app.showManyToManyMaintain();
            app.setShowContent1(true);
            app.setShowdatatableContent(true);
            app.setManyToMany(true);
            //app.setShowCommonData(true);

            app.setShowCustomDiv1(true);
        } else if (viewId.equals("/module/jsps/decision/decisionemployeesmaintaincancel.jsp") || 
                   viewId.equals("/module/jsps/decision-integration/decisionemployeesmaintaincancel.jsp")) {
            app.showManyToManyMaintain();
            app.setShowContent1(false);
            app.setShowDelConfirm(false);
            app.setShowSearch(false);
            app.setShowbar(false);
        } else {
            app.showManyToManyMaintain();
            app.setShowContent1(true);
            app.setShowDelConfirm(false);
            app.setShowSearch(false);
            app.setShowDelConfirm(true);
            app.setShowLookupEdit(true);
            //  app.setShowCustomDiv2(true);
            app.setShowLookupAdd(true);
            app.setShowCustomDiv1(true);
            app.setShowEmpSrchDivPaging(true);
            app.setShowLovDiv(true);
            app.setShowpaging(true);
            if (!showBarMainData) {
                app.setShowbar(false);
            }
        }
        //by Ashraf Gaber to hide the operationBar while integration page opened
        if (DecisionMaintainBean.isIntegrationPage()) {
            app.setShowbar(false);
            app.setShowContent1(false);
        }
        return app;
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {
        return new PagingResponseDTO();
    }

    public PagingResponseDTO initEmpBean(PagingRequestDTO request) {
        DecisionMaintainBean decisionMaintainBean = 
            (DecisionMaintainBean)evaluateValueBinding("decisionMaintainBean");
        DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean = 
            (DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
        PagingResponseDTO responseDTO = new PagingResponseDTO();
        if (((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList() != 
            null && 
            ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().size() > 
            0 && 
            decisionEmployeesModelSessionBean.getOriginalMap().size() == 0) {
            responseDTO.setResultList(((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList());
            responseDTO.setTotalListSize(((IEmpDecisionsDTO)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(0)).getCountOfAllPage().intValue());
            List list = new ArrayList();
            for (int i = 0; 
                 i < ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().size(); 
                 i++) {
                list.add(((IEmpDecisionsDTO)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(i)));
                decisionEmployeesModelSessionBean.getAddElementMap().put(((IEmpDecisionsEntityKey)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(i).getCode()).getCivilId(), 
                                                                         (((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(i)));
            }
            decisionEmployeesModelSessionBean.getOriginalMap().put(((IEmpDecisionsDTO)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(0)).getPageNo(), 
                                                                   list);
        } else {

            if (getSelectedCurrentDetails() != null && 
                getSelectedCurrentDetails().size() > 0) {
                for (int y = 0; y < getSelectedCurrentDetails().size(); y++) {
                    for (int i = 0; i < getCurrentDetails().size(); i++) {
                        if (((IEmployeesEntityKey)getSelectedCurrentDetails().get(y).getCode()).getCivilId().equals(((IEmpDecisionsEntityKey)getCurrentDetails().get(i).getCode()).getCivilId()))
                            {
                            ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setChecked(true);
                            }
                            else
                            {
                                ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setChecked(false);
                            }
                    }
                }

            }
            responseDTO.setResultList(getCurrentDetails());
            responseDTO.setTotalListSize(getCurrentListSize());
            setUpdateMyPagedDataModel(true);
        }
        return responseDTO;
    }

    public void changeKuwityType() {
        if (employeeSearchDTO.getNationality() == KUWITY) {
            resetNonKuwityData();
            employeeSearchDTO.setNationality(NATIONLALTY_KUWITY);
        } else {
            employeeSearchDTO.setNationality(null);
        }
    }

    private void resetNonKuwityData() {
        employeeSearchDTO.setNationality(null);
        employeeSearchDTO.setPassportNo(null);
        employeeSearchDTO.setResidentTypeCode(null);
        employeeSearchDTO.setEndResidentDate(null);
    }

    public List getCurrentDisplayDetails() {
        // use this injection into integration (by ahmed abdel fatah) 
        if (getIntegrationBean() != null && 
            getIntegrationBean().getSelectedDTOFrom() != null && 
            getIntegrationBean().getSelectedDTOFrom().size() != 0) {
            getSelectedAvailableDetails().addAll(getIntegrationBean().getSelectedDTOFrom());
            super.add();
        }
        if (!isCancelDecisionMode()) {
            return super.getCurrentDisplayDetails();
        }
        List<IBasicDTO> currentDisplayed = null;
        DecisionMaintainBean decisionBean = 
            (DecisionMaintainBean)BeansUtil.getValue("decisionMaintainBean");
        if (decisionBean != null && isCancelDecisionMode()) {
            IDecisionsDTO decision = (IDecisionsDTO)decisionBean.getPageDTO();
            if (decision != null) {
                IDecisionsDTO canceledDecision = decision.getDecisionsDTO();
                if ((Boolean)BeansUtil.getValue("decisionMaintainBean.maintainMode == 1")) {
                    try {
                        canceledDecision = 
                                (IDecisionsDTO)(RegClientFactory.getDecisionsClient().getById(canceledDecision.getCode()));
                    } catch (SharedApplicationException e) {
                        e.printStackTrace();
                    } catch (DataBaseException e) {
                        e.printStackTrace();
                    }
                }
                if (canceledDecision != null) {
                    currentDisplayed = 
                            canceledDecision.getEmpDecisionsDTOList();
                    setCurrentDisplayDetails(currentDisplayed);
                }
            }
        }
        return currentDisplayed;
    }

    public void cancelSearchAvailable() throws DataBaseException, 
                                               SharedApplicationException {
        setAvailableDetails(new ArrayList<IBasicDTO>());
        setSearchMode(false);
        getSelectedCurrentDetails().clear();
    }

    //    currently not used

    public void getListAvailable() throws DataBaseException {
        DecisionMaintainBean decisionBean = 
            (DecisionMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{decisionMaintainBean}").getValue(FacesContext.getCurrentInstance());
        if (decisionBean != null && isCancelDecisionMode()) {
            IDecisionsDTO decision = (IDecisionsDTO)decisionBean.getPageDTO();
            if (decision != null) {
                IDecisionsDTO canceledDecision = decision.getDecisionsDTO();
                if (canceledDecision != null) {
                    setAvailableDetails(canceledDecision.getEmpDecisionsDTOList());
                }
            }
        }
    }

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

        app.createValueBinding("#{wizardbar.currentStep}").setValue(fContext, 
                                                                    "employeesadd");

        String pageNavigation = "decisionemployeesmaintain";
        if ((Boolean)app.createValueBinding("#{decisionMaintainBean.cancelDecisionMode}").getValue(fContext)) {
            pageNavigation += "cancel";
        }

        if ((Boolean)app.createValueBinding("#{decisionMaintainBean.maintainMode == 1}").getValue(fContext)) {
            pageNavigation += "edit";
        }

        setAvailableDetails(new ArrayList<IBasicDTO>());
        app.getNavigationHandler().handleNavigation(fContext, null, 
                                                    pageNavigation);
    }

    public void delete() {
        try {
            DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean = 
                (DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
            if (getCurrentDetails() == null)
                setCurrentDetails(new ArrayList<IBasicDTO>());
            DecisionMaintainBean decisionBean = 
                (DecisionMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{decisionMaintainBean}").getValue(FacesContext.getCurrentInstance());
            decisionBean.getPageDTO();
            if (getEmpDecisionsDTOSlelected() != null) {
                for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                    IEmpDecisionsDTO dto = 
                        (IEmpDecisionsDTO)decisionEmployeesModelSessionBean.getAddElementMap().get(((IEmployeesEntityKey)getSelectedCurrentDetails().get(i).getCode()).getCivilId());
                    if (dto.getStatusFlag() == null) {
                        dto.setStatusFlag(deleted.longValue());
                        dto.setDisableFlage(true);
                        getSelectedCurrentDetails().remove(i);
                        i--;
                    }
                    if (dto.getStatusFlag() != null) {
                        if (dto.getStatusFlag() == 4L) {
                            if (decisionEmployeesModelSessionBean.getAddElementMap().containsKey(((IEmpDecisionsEntityKey)dto.getCode()).getCivilId())) {
                                dto.setStatusFlag(deleted.longValue());
                                dto.setDisableFlage(true);
                            }
                            getSelectedCurrentDetails().remove(i);
                            i--;
                        }
                        if (dto.getStatusFlag().longValue() == 
                            added.longValue()) {

                            dto.setStatusFlag(5L);
                            dto.setDisableFlage(true);
                            getSelectedCurrentDetails().remove(i);
                            i--;
                        }
                    }
                }
                setCurrentDetails(decisionEmployeesModelSessionBean.getOriginalMap().get(new Long(getCurrentPageIndex())));
            }
            this.restoreDetailsTablePosition(this.getCurrentDataTable(), 
                                             this.getCurrentDetails());
            this.resetSelection();
            PagingRequestDTO request = null;
            initEmpBean(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //    public List<IBasicDTO> searchAvailable(Object employeSearchDTO) {
    //        List<IBasicDTO> list = new ArrayList<IBasicDTO>();
    //        try {
    //            //employeeSearchDTO.setMasterCode(getMasterEntityKey() == null ? new IDecisionsEntityKey() : getMasterEntityKey());
    //            //setAvailableDetails(((IEmpDecisionsClient)getClient()).filterAvailableEntities (employeeSearchDTO));
    //            //List<IBasicDTO> list =  (((IEmpDecisionsClient)getClient()).filterAvailableEntities (new IEmployeeSearchDTO()));
    //            list =((IEmpDecisionsClient)getClient()).filterAvailableEntities((IEmployeeSearchDTO)employeSearchDTO);
    //            setAvailableDetails(list); //new IEmployeeSearchDTO()));
    //            setSearchMode(true);
    //        } catch (SharedApplicationException e) {
    //            setAvailableDetails(new ArrayList<IBasicDTO>());
    //            e.printStackTrace();
    //        } catch (Exception ex) {
    //            setAvailableDetails(new ArrayList<IBasicDTO>());
    //            ex.printStackTrace();
    //        }
    //        return list;
    //    }

    public boolean validate() {
        if (DecisionMaintainBean.isIntegrationPage()) {
            return true; //TODO 
        }
        boolean valid = (getCountOfAllEmpDecision() > 0);
        if (!valid) {
            showErrMsg = true;
        }
        return valid;
    }

    public String navigateAdd() {
        try {
            advanceEmployeesAddBean.setReturnMethod("decisionEmployeesMaintainBean.employeeAddMethod");
            advanceEmployeesAddBean.setBackMethod("decisionEmployeesMaintainBean.backFromSearch");
            advanceEmployeesAddBean.setSearchMethod("advanceEmployeesAddBean.filterSearchByEmpWithPaging");
            advanceEmployeesAddBean.setShowResultWithinPage(false);
            advanceEmployeesAddBean.setShowCategoryCB(false);
            advanceEmployeesAddBean.setShowMinistryCB(false);
            advanceEmployeesAddBean.setShowWorkEndDate(false);
            advanceEmployeesAddBean.setShowHireStatus(false);
            advanceEmployeesAddBean.setShowCurrentHireStatus(false);
            advanceEmployeesAddBean.setShowWorkCenterLovDiv(true);
            advanceEmployeesAddBean.setShowJobLovDiv(true);

            //////////////// add by amr galal to Apply B.Paging ////////////////// 
            advanceEmployeesAddBean.setUsingBsnPaging(true);
            advanceEmployeesAddBean.setUsingPaging(true);
            /////////////////////////////////////////////////////////////////////

            List employeesList = getCurrentDetails();
            List empList = new ArrayList();
            if (employeesList == null)
                setCurrentDetails(new ArrayList<IBasicDTO>());

            for (IEmpDecisionsDTO evalEmp: 
                 (ArrayList<IEmpDecisionsDTO>)employeesList) {
                IEmployeesDTO emp = (IEmployeesDTO)evalEmp.getEmployeesDTO();
                emp.setStatusFlag(evalEmp.getStatusFlag());
                empList.add(emp);
            }
            advanceEmployeesAddBean.setCurrentDetails(empList);
            DecisionMaintainBean decisionBean = 
                (DecisionMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{decisionMaintainBean}").getValue(FacesContext.getCurrentInstance());

            DecisionListBean decisionListBean = 
                (DecisionListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{decisionListBean}").getValue(FacesContext.getCurrentInstance());

            //if(getPageDTO()!=null && getPageDTO().getCode()!=null)
            if (decisionBean.getPageDTO() != null)
                advanceEmployeesAddBean.getSavedData().add(0, 
                                                           decisionBean.getPageDTO());
            advanceEmployeesAddBean.getSavedData().add(1, 
                                                       decisionBean.getMaintainMode());
            advanceEmployeesAddBean.getSavedData().add(2, 
                                                       getWizardBar().getConfigurationId());
            advanceEmployeesAddBean.getSavedData().add(3, 
                                                       getWizardBar().getWizardSteps());
            advanceEmployeesAddBean.getSavedData().add(4, 
                                                       decisionBean.isShowPrevious());
            advanceEmployeesAddBean.getSavedData().add(5, 
                                                       decisionBean.isRenderFinish());
            advanceEmployeesAddBean.getSavedData().add(6, 
                                                       decisionBean.isRenderSave());
            advanceEmployeesAddBean.getSavedData().add(7, 
                                                       decisionBean.getNextNavigationCase());
            advanceEmployeesAddBean.getSavedData().add(8, 
                                                       decisionBean.getPreviousNavigationCase());
            advanceEmployeesAddBean.getSavedData().add(9, 
                                                       decisionBean.getFinishNavigationCase());
            advanceEmployeesAddBean.getSavedData().add(10, 
                                                       decisionBean.getCurrentNavigationCase());
            advanceEmployeesAddBean.getSavedData().add(11, employeesList);
            advanceEmployeesAddBean.getSavedData().add(12, 
                                                       decisionListBean.getIndexArray());
            advanceEmployeesAddBean.getSavedData().add(13, 
                                                       decisionListBean.getSortColumn());
            advanceEmployeesAddBean.getSavedData().add(14, 
                                                       decisionListBean.isSaveSortingState());
            advanceEmployeesAddBean.getSavedData().add(15, 
                                                       decisionListBean.isSortAscending());
            advanceEmployeesAddBean.getSavedData().add(16, 
                                                       decisionListBean.getFullColumnName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Advanced_Add_Employees";
    }

    public void addFinancial() {
        DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean = 
            (DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
        if (getCurrentDetails() != null && getCurrentDetails().size() != 0) {
            for (int i = 0; i < getCurrentDetails().size(); i++) {
                if (getEmpDecisionsDTOSlelected().getCode() == 
                    getCurrentDetails().get(i).getCode()) {
                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setValueNum(valueNum);
                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setValueDate(valueDate);
                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setValueChr(valueChr);
                    if (((IEmpDecisionsDTO)getCurrentDetails().get(i)).getStatusFlag() !=  null) {
                        if (((IEmpDecisionsDTO)getCurrentDetails().get(i)).getStatusFlag() == 4L) {
                            ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setStatusFlag(null);
                        }
                    }
                }


            }
            if (decisionEmployeesModelSessionBean.getAddElementMap().containsKey(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId())) {
                IEmpDecisionsDTO dto =  (IEmpDecisionsDTO)decisionEmployeesModelSessionBean.getAddElementMap().get(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId());
                dto.setValueChr(valueChr);
                dto.setValueDate(valueDate);
                dto.setValueNum(valueNum);
                if(dto.getStatusFlag()!=null)
                {
                    if(dto.getStatusFlag()==4L)
                    {
                    dto.setStatusFlag(null);
                    }
                }
                //decisionEmployeesModelSessionBean.getAddElementMap().put(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId(),dto);
            }
        }
        //decisionEmployeesModelSessionBean.getOriginalMap().put(new Long(getCurrentPageIndex()),  getCurrentDetails());

        setActiveValidationOnAdd(false);

    }

    public void addInformationEmp() {
        DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean = 
            (DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
        if (getCurrentDetails() != null && getCurrentDetails().size() != 0) {
            for (int i = 0; i < getCurrentDetails().size(); i++) {
                if (getEmpDecisionsDTOSlelected().getCode() == 
                    getCurrentDetails().get(i).getCode()) {
                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setNotes(notes);
                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setEmpInfTypeCode(empInfTypeCode);
                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setBooleanInformEmpFlag(booleanInformEmpFlag);
                    ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setInformEmpDate(informEmpDate);
                    if (((IEmpDecisionsDTO)getCurrentDetails().get(i)).getStatusFlag() != 
                        null) {
                        if (((IEmpDecisionsDTO)getCurrentDetails().get(i)).getStatusFlag() == 
                            4L) {
                            ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setStatusFlag(null);
                        }
                    }
                }
            }

            if (decisionEmployeesModelSessionBean.getAddElementMap().containsKey(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId())) {
                IEmpDecisionsDTO dto =  (IEmpDecisionsDTO)decisionEmployeesModelSessionBean.getAddElementMap().get(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId());
                dto.setNotes(notes);
                dto.setEmpInfTypeCode(empInfTypeCode);
                dto.setBooleanInformEmpFlag(booleanInformEmpFlag);
                dto.setInformEmpDate(informEmpDate);
                if(dto.getStatusFlag()!=null)
                {
                    if(dto.getStatusFlag()==4L)
                    {
                    dto.setStatusFlag(null);
                    }
                }
                //decisionEmployeesModelSessionBean.getAddElementMap().put(((IEmpDecisionsEntityKey)getEmpDecisionsDTOSlelected().getCode()).getCivilId(),dto);
            }
        }
        //decisionEmployeesModelSessionBean.getOriginalMap().put(new Long(getCurrentPageIndex()), getCurrentDetails());
        setActiveValidationOnEdit(false);
    }

    public void openAddDiv() {
        setActiveValidationOnAdd(true);
        if (getEmpDecisionsDTOSlelected() != null) {
            setValueDate(getEmpDecisionsDTOSlelected().getValueDate());
            setValueChr(getEmpDecisionsDTOSlelected().getValueChr());
            setValueNum(getEmpDecisionsDTOSlelected().getValueNum());
        }
    }

    public void openEditDiv() {
        setActiveValidationOnEdit(true);
        if (getEmpDecisionsDTOSlelected() != null) {
            setNotes(getEmpDecisionsDTOSlelected().getNotes());
            setEmpInfTypeCode(getEmpDecisionsDTOSlelected().getEmpInfTypeCode());
            setBooleanInformEmpFlag(getEmpDecisionsDTOSlelected().isBooleanInformEmpFlag());
            setInformEmpDate(getEmpDecisionsDTOSlelected().getInformEmpDate());
        }
    }

    public void cancelAdd() {
        setActiveValidationOnAdd(false);
        setValueDate(null);
        setValueChr(null);
        setValueNum(null);
    }

    public void cancelEdit() {
        setActiveValidationOnEdit(false);
        setNotes(null);
        setEmpInfTypeCode(null);
        setBooleanInformEmpFlag(null);
        setInformEmpDate(null);
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        if (isCancelDecisionMode()) {
            return dto; // as i got it from the canceled decision there is no need for the mapping
        }
        return ((IEmpDecisionsDTO)dto).getEmployeesDTO();
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        if (isCancelDecisionMode()) {
            return dto; // as i got it from the canceled decision there is no need for the mapping
        }

        IEmpDecisionsDTO empIDecisionsDTO = 
            RegDTOFactory.createEmpDecisionsDTO();
        empIDecisionsDTO.setEmployeesDTO((IEmployeesDTO)dto);
        IDecisionsDTO decisionsDTO = null;
        IDecisionsEntityKey decisionsEntityKey = null;
        IEmpDecisionsEntityKey empDecisionsEntityKey = null;
        if (this.getMasterEntityKey() != null) {
            decisionsEntityKey = 
                    (IDecisionsEntityKey)this.getMasterEntityKey();
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
                    RegEntityKeyFactory.createEmpDecisionsEntityKey(null, null, 
                                                                    null, 
                                                                    Long.valueOf(dto.getCode().getKey().toString()));
        }
        empIDecisionsDTO.setCode(empDecisionsEntityKey);
        return empIDecisionsDTO;
    }

    public void setIEmployeeSearchDTO(IEmployeeSearchDTO employeeSearchDTO) {
        this.employeeSearchDTO = employeeSearchDTO;
    }

    public IEmployeeSearchDTO getIEmployeeSearchDTO() {
        return employeeSearchDTO;
    }

    public Long getKuwity() {
        return KUWITY;
    }

    public Long getNonKuwity() {
        return NON_KUWITY;
    }

    public void setMaritalStatusTyps(List<IBasicDTO> maritalStatusTyps) {
        this.maritalStatusTyps = maritalStatusTyps;
    }

    public List<IBasicDTO> getMaritalStatusTyps() throws DataBaseException {
        if (maritalStatusTyps.size() == 0) {
            maritalStatusTyps = 
                    InfClientFactory.getMaritalStatusClient().getCodeName();
        }
        return maritalStatusTyps;
    }

    public void setGenderTyps(List<IBasicDTO> genderTyps) {
        this.genderTyps = genderTyps;
    }

    public List<IBasicDTO> getGenderTyps() throws DataBaseException {
        if (genderTyps.size() == 0) {
            genderTyps = InfClientFactory.getGenderTypesClient().getCodeName();
        }
        return genderTyps;
    }

    public void setRelgionTyps(List<IBasicDTO> relgionTyps) {
        this.relgionTyps = relgionTyps;
    }

    public List<IBasicDTO> getRelgionTyps() throws DataBaseException {
        if (relgionTyps.size() == 0) {
            relgionTyps = InfClientFactory.getReligionsClient().getCodeName();
        }
        return relgionTyps;
    }

    public void setItemSelectionRequiredValue(Long itemSelectionRequiredValue) {
        this.itemSelectionRequiredValue = itemSelectionRequiredValue;
    }

    public Long getItemSelectionRequiredValue() {
        return itemSelectionRequiredValue;
    }

    public void setGovernments(List<IBasicDTO> governments) {
        this.governments = governments;
    }

    public List<IBasicDTO> getGovernments() throws DataBaseException {
        if (governments.size() == 0) {
            governments = InfClientFactory.getKwMapClient().getCodeName();
        }
        return governments;
    }

    public void setCapTyps(List<IBasicDTO> capTyps) {
        this.capTyps = capTyps;
    }

    public List<IBasicDTO> getCapTyps() throws DataBaseException {
        if (capTyps.size() == 0) {
            capTyps = InfClientFactory.getHandicapStatusClient().getCodeName();
        }
        return capTyps;
    }

    public void setSpecialCaseTyps(List<IBasicDTO> specialCaseTyps) {
        this.specialCaseTyps = specialCaseTyps;
    }

    public List<IBasicDTO> getSpecialCaseTyps() throws DataBaseException {
        if (specialCaseTyps.size() == 0) {
            specialCaseTyps = 
                    InfClientFactory.getSpecialCaseTypesClient().getCodeName();
        }
        return specialCaseTyps;
    }

    public void setCategories(List<IBasicDTO> categories) {
        this.categories = categories;
    }

    public List<IBasicDTO> getCategories() throws DataBaseException {
        if (categories.size() == 0) {
            categories = OrgClientFactory.getCatsClient().getCodeName();
        }
        return categories;
    }

    public void fillMinistries() {
        try {
            ministries = 
                    OrgClientFactory.getMinistriesClient().getAllByCategory(categoryID);
        } catch (SharedApplicationException e) {
            ministries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        } catch (DataBaseException e) {
            ministries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        } catch (Exception e) {
            ministries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        }
    }

    public void setMinistries(List<IBasicDTO> ministries) {
        this.ministries = ministries;
    }

    public void setWorkMinistries(List<IBasicDTO> workMinistries) {
        this.workMinistries = workMinistries;
    }

    public List<IBasicDTO> getWorkMinistries() {
        return workMinistries;
    }

    public void fillWorkMinistries() {
        try {
            workMinistries = 
                    OrgClientFactory.getWorkCentersClient().getAllByMinistry(OrgEntityKeyFactory.createMinistriesEntityKey(ministryID));
        } catch (SharedApplicationException e) {
            workMinistries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        } catch (DataBaseException e) {
            workMinistries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        } catch (Exception e) {
            workMinistries = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        }
    }

    public List<IBasicDTO> getMinistries() {
        return ministries;
    }

    public void setHireTypes(List<IBasicDTO> hireTypes) {
        this.hireTypes = hireTypes;
    }

    public List<IBasicDTO> getHireTypes() throws DataBaseException {
        if (hireTypes.size() == 0) {
            hireTypes = 
                    (List<IBasicDTO>)EmpClientFactory.getHireTypesClient().getCodeName();
        }
        return hireTypes;
    }

    public void setHireStatuses(List<IBasicDTO> hireStatuses) {
        this.hireStatuses = hireStatuses;
    }

    public List<IBasicDTO> getHireStatuses() throws DataBaseException {
        if (hireStatuses.size() == 0) {
            hireStatuses = 
                    EmpClientFactory.getHireStatusClient().getCodeName();
        }
        return hireStatuses;
    }

    public void setHireCurrentStatuses(List<IBasicDTO> hireCurrentStatuses) {
        this.hireCurrentStatuses = hireCurrentStatuses;
    }

    public List<IBasicDTO> getHireCurrentStatuses() throws DataBaseException {
        if (hireCurrentStatuses.size() == 0) {
            hireCurrentStatuses = 
                    EmpClientFactory.getHireStagesClient().getCodeName();
        }
        return hireCurrentStatuses;
    }

    public void setTechnicalJobs(List<IJobsDTO> technicalJobs) {
        this.technicalJobs = technicalJobs;
    }

    public List<IJobsDTO> getTechnicalJobs() throws DataBaseException {
        if (technicalJobs.size() == 0) {
            technicalJobs = JobClientFactory.getJobsClient().getCodeName();
        }
        return technicalJobs;
    }

    public void setBanks(List<IBasicDTO> banks) {
        this.banks = banks;
    }

    public List<IBasicDTO> getBanks() throws DataBaseException {
        if (banks.size() == 0) {
            try {
                banks = BnkClientFactory.getBanksClient().getCodeName();
            } catch (SharedApplicationException e) {
                banks = new ArrayList<IBasicDTO>();
                e.printStackTrace();
            }
        }
        return banks;
    }

    public void setBranches(List<IBasicDTO> branches) {
        this.branches = branches;
    }

    public List<IBasicDTO> getBranches() {
        return branches;
    }

    public void fillBranches() {
        try {
            branches = 
                    BnkClientFactory.getBankBranchesClient().getCodeName(bankID);
        } catch (DataBaseException e) {
            branches = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        } catch (Exception e) {
            branches = new ArrayList<IBasicDTO>();
            e.printStackTrace();
        }
    }

    public void setJobCategories(List<IBasicDTO> jobCategories) {
        this.jobCategories = jobCategories;
    }

    public List<IBasicDTO> getJobCategories() {
        return jobCategories;
    }

    public void setJobGroupes(List<IBasicDTO> jobGroupes) {
        this.jobGroupes = jobGroupes;
    }

    public List<IBasicDTO> getJobGroupes() {
        return jobGroupes;
    }

    public void setCurrentDegrees(List<IBasicDTO> currentDegrees) {
        this.currentDegrees = currentDegrees;
    }

    public List<IBasicDTO> getCurrentDegrees() {
        return currentDegrees;
    }

    public void setBudgetTypes(List<IBasicDTO> budgetTypes) {
        this.budgetTypes = budgetTypes;
    }

    public List<IBasicDTO> getBudgetTypes() {
        return budgetTypes;
    }


    public void setNationalties(List<IBasicDTO> nationalties) {
        this.nationalties = nationalties;
    }

    public List<IBasicDTO> getNationalties() throws DataBaseException {
        if (nationalties.size() == 0) {
            nationalties = InfClientFactory.getCountriesClient().getCodeName();
        }
        return nationalties;
    }

    public void setResdientTypes(List<IBasicDTO> resdientTypes) {
        this.resdientTypes = resdientTypes;
    }

    public List<IBasicDTO> getResdientTypes() throws DataBaseException {
        if (resdientTypes.size() == 0) {
            resdientTypes = 
                    InfClientFactory.getResidentTypeClient().getCodeName();
        }
        return resdientTypes;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setMinistryID(Long ministryID) {
        this.ministryID = ministryID;
    }

    public Long getMinistryID() {
        return ministryID;
    }

    public void setBankID(Long bankID) {
        this.bankID = bankID;
    }

    public Long getBankID() {
        return bankID;
    }

    public void setKuwityType(Long kuwityType) {
        this.kuwityType = kuwityType;
    }

    public Long getKuwityType() {
        return kuwityType;
    }

    public void setCancelDecisionMode(boolean cancelDecisionMode) {
        this.cancelDecisionMode = cancelDecisionMode;
    }

    public boolean isCancelDecisionMode() {
        return cancelDecisionMode;
    }

    public void setIsSearchMode(boolean isSearchMode) {
        this.isSearchMode = isSearchMode;
    }

    public boolean isIsSearchMode() {
        return isSearchMode;
    }

    public void setShowErrMsg(boolean showErrMsg) {
        this.showErrMsg = showErrMsg;
    }

    public boolean isShowErrMsg() {
        return showErrMsg;
    }

    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
    }

    public void setPageIndexAdd(Integer pageIndexAdd) {
        pageIndexAdd = 0;
    }

    public Integer getPageIndexAdd() {
        if (dataScroller != null) {
            return dataScroller.getPageIndex();
        }

        return 0;
    }

    /**
     * Purpose: Overrwite this medthod to get row data without maping to dto
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Aug 27, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description: 
     * @return 
     * @throws Exception
     */
    public void selectedCurrent(ActionEvent event) throws Exception {
        super.selectedCurrent(event);

        Set s = new HashSet();
        s.addAll(this.getEmpDecisionsDTOSlelectedList());
        ClientDTO selected =  (ClientDTO)this.getCurrentDataTable().getRowData();
        if (selected.getChecked()) {

            try {
                s.add((selected));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!(selected.getChecked())) {
            s.remove((selected));
        }
        this.getEmpDecisionsDTOSlelectedList().clear();
        this.getEmpDecisionsDTOSlelectedList().addAll(s);
        if (getEmpDecisionsDTOSlelectedList().size() == 1) {
            if ((getEmpDecisionsDTOSlelectedList().get(0).getTableName() == null) || getEmpDecisionsDTOSlelectedList().get(0).getStatusFlag() !=  null) {
                setEmpDecisionsDTOSlelected(getEmpDecisionsDTOSlelectedList().get(0));
                setAddEmpDecisionsDTOModule(false);
            }
            //            if(getIEmpDecisionsDTOSlelectedList().get(0).getStatusFlag() != null){
            //                setDeleteIEmpDecisionsDTO(false);
            //            }
        }
    }

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


    /**
     * Purpose: assign employee to eg Module 
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Aug 27, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public void empIDecisionsDTOModule() {
        regulationModulesMaintainBean.getSelectedAvailableDetailsRowIndices().get(0);
        int rowIndex = 
            regulationModulesMaintainBean.getSelectedAvailableDetailsRowIndices().get(0);
        Vector rowData = 
            (Vector)regulationModulesMaintainBean.getCustomAvailableDetails().get(rowIndex);
        Long serial = getRowSerial(rowData);
        getEmpDecisionsDTOSlelected().setTableName(regulationModulesMaintainBean.getSelectedTableKey());
        getEmpDecisionsDTOSlelected().setTabrecSerialRef(serial);
        resetSelection();
    }

    private Long getRowSerial(Vector row) {
        return Long.parseLong(row.get(row.size() - 1).toString());
    }

    public void setEmpDecisionsDTOSlelectedList(List<IEmpDecisionsDTO> empIDecisionsDTOSlelectedList) {
        this.empDecisionsDTOSlelectedList = empIDecisionsDTOSlelectedList;
    }

    public List<IEmpDecisionsDTO> getEmpDecisionsDTOSlelectedList() {
        return empDecisionsDTOSlelectedList;
    }

    /**
     * Purpose: ReInitialize RegulationModulesMaintainBean
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Aug 27, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public void resetRegModuleDiv() {
        FacesContext.getCurrentInstance().getApplication().createValueBinding("#{regulationModulesMaintainBean}").setValue(FacesContext.getCurrentInstance(), 
                                                                                                                           new RegulationModulesMaintain());
    }

    public void setShowBarMainData(boolean showBarMainData) {
        this.showBarMainData = showBarMainData;
    }

    public boolean isShowBarMainData() {
        return showBarMainData;
    }


    /**
     * Purpose: will be called from the employee search bean after selecteding employee and added
     * Creation/Modification History :
     * 1.1 - Developer Name:  OmarEzzEldin
     * 1.2 - Date:   
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public String employeeAddMethod() {
        try {
            setCancelDecisionMode(false);
            AdvanceEmployeesAddBean advanceEmployeesAddBean = 
                (AdvanceEmployeesAddBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                                               "advanceEmployeesAddBean" + 
                                                                                                               "}").getValue(FacesContext.getCurrentInstance());
            DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean = 
                (DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");

            DecisionMaintainBean decisionBean = 
                (DecisionMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{decisionMaintainBean}").getValue(FacesContext.getCurrentInstance());
            DecisionListBean decisionListBean = 
                (DecisionListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{decisionListBean}").getValue(FacesContext.getCurrentInstance());

            List<IEmployeesDTO> employeesList = 
                (List)advanceEmployeesAddBean.getCurrentDetails();

            List<IBasicDTO> empDecisionListList = 
                (List<IBasicDTO>)advanceEmployeesAddBean.getSavedData().get(11);
            //setCurrentDetails(empDecisionListList);
            if (employeesList != null && employeesList.size() > 0) {
                for (IEmployeesDTO emp: employeesList) {
                    if (emp.getStatusFlag() != null && 
                        emp.getStatusFlag().equals(added) && 
                        getCurrentSelectedDetail(emp) == null) {
                        IEmpDecisionsDTO empIDecisionsDTO = 
                            (IEmpDecisionsDTO)mapToDetailDTO(emp);
                        empIDecisionsDTO.setBooleanInformEmpFlag(false);
                        empIDecisionsDTO.setStatusFlag(emp.getStatusFlag());
                        empDecisionListList.add(empIDecisionsDTO);
                        if (!decisionEmployeesModelSessionBean.checkExistData(emp)) {
                            setCurrentPageIndex(decisionEmployeesModelSessionBean.determineNoOfPaqe());
                            decisionEmployeesModelSessionBean.navigateToNextPage(new Long(getCurrentPageIndex()));
                            decisionEmployeesModelSessionBean.addNewElement(empIDecisionsDTO);
                        }
                    }
                }
                // setCurrentDetails(empDecisionListList);
            }

            if (decisionBean.getPageDTO() != null && 
                decisionBean.getPageDTO().getCode() != null)
                setMasterEntityKey(decisionBean.getPageDTO().getCode());

            Integer mode = 
                (Integer)advanceEmployeesAddBean.getSavedData().get(1);
            getWizardBar().setWizardSteps((Map)advanceEmployeesAddBean.getSavedData().get(3));
            getWizardBar().setConfigurationId(advanceEmployeesAddBean.getSavedData().get(2).toString());
            decisionBean.setMaintainMode(mode.intValue());

            decisionBean.setShowPrevious(((Boolean)advanceEmployeesAddBean.getSavedData().get(4)).booleanValue());
            decisionBean.setRenderFinish(((Boolean)advanceEmployeesAddBean.getSavedData().get(5)).booleanValue());
            decisionBean.setRenderSave(((Boolean)advanceEmployeesAddBean.getSavedData().get(6)).booleanValue());
            if (advanceEmployeesAddBean.getSavedData().get(7) != null)
                decisionBean.setNextNavigationCase(advanceEmployeesAddBean.getSavedData().get(7).toString());

            if (advanceEmployeesAddBean.getSavedData().get(8) != null)
                decisionBean.setPreviousNavigationCase(advanceEmployeesAddBean.getSavedData().get(8).toString());
            if (advanceEmployeesAddBean.getSavedData().get(9) != null)
                decisionBean.setFinishNavigationCase(advanceEmployeesAddBean.getSavedData().get(9).toString());
            if (advanceEmployeesAddBean.getSavedData().get(10) != null)
                decisionBean.setCurrentNavigationCase(advanceEmployeesAddBean.getSavedData().get(10).toString());
            /////////////////////////////////////////////
            if (advanceEmployeesAddBean.getSavedData().get(12) != null)
                decisionListBean.setIndexArray((boolean[])advanceEmployeesAddBean.getSavedData().get(12));
            if (advanceEmployeesAddBean.getSavedData().get(13) != null)
                decisionListBean.setSortColumn(advanceEmployeesAddBean.getSavedData().get(13).toString());
            if (advanceEmployeesAddBean.getSavedData().get(14) != null)
                decisionListBean.setSaveSortingState(((Boolean)advanceEmployeesAddBean.getSavedData().get(14)).booleanValue());
            if (advanceEmployeesAddBean.getSavedData().get(15) != null)
                decisionListBean.setSortAscending(((Boolean)advanceEmployeesAddBean.getSavedData().get(15)).booleanValue());
            if (advanceEmployeesAddBean.getSavedData().get(16) != null)
                decisionListBean.setFullColumnName(advanceEmployeesAddBean.getSavedData().get(16).toString());


            decisionBean.setCurrentStep("employeesadd");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getWizardBar().setCurrentStep("employeesadd");

        //        this.setJavaScriptCaller("javascript: oamSubmitForm('myForm','myForm:scroll_paginglast',null,[['myForm:scroll_paging','last']]);");
        //        PagingRequestDTO request = null;
        //        initEmpBean(request);
        return "decisionemployeesmaintain";
    }

    public String backFromSearch() {

        setCancelDecisionMode(false);

        //        List<IBasicDTO> employeesList1 = new ArrayList<IBasicDTO>();
        //        if (advanceEmployeesAddBean.getSavedData().get(11) != null) {
        //            setCurrentDetails((List<IBasicDTO>)advanceEmployeesAddBean.getSavedData().get(11));
        //        } else {
        //            setCurrentDetails(new ArrayList<IBasicDTO>());
        //        }
        PagingRequestDTO request = null;
        initEmpBean(request);
        if (getCurrentPageIndex() != 1) {
            getCurrentDataTable().setFirst(getCurrentPageIndex());
        }
        setUpdateMyPagedDataModel(true);
        DecisionListBean decisionListBean = 
            (DecisionListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{decisionListBean}").getValue(FacesContext.getCurrentInstance());
        DecisionMaintainBean decisionBean = 
            (DecisionMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{decisionMaintainBean}").getValue(FacesContext.getCurrentInstance());
        if (decisionBean != null)
            decisionBean.setPageDTO((IDecisionsDTO)advanceEmployeesAddBean.getSavedData().get(0));
        ((IDecisionsDTO)decisionBean.getPageDTO()).setEmpDecisionsDTOList(getCurrentDetails());

        Integer mode = (Integer)advanceEmployeesAddBean.getSavedData().get(1);
        getWizardBar().setWizardSteps((Map)advanceEmployeesAddBean.getSavedData().get(3));
        getWizardBar().setConfigurationId(advanceEmployeesAddBean.getSavedData().get(2).toString());
        decisionBean.setMaintainMode(mode.intValue());

        decisionBean.setShowPrevious(((Boolean)advanceEmployeesAddBean.getSavedData().get(4)).booleanValue());
        decisionBean.setRenderFinish(((Boolean)advanceEmployeesAddBean.getSavedData().get(5)).booleanValue());
        decisionBean.setRenderSave(((Boolean)advanceEmployeesAddBean.getSavedData().get(6)).booleanValue());
        if (advanceEmployeesAddBean.getSavedData().get(7) != null)
            decisionBean.setNextNavigationCase(advanceEmployeesAddBean.getSavedData().get(7).toString());

        if (advanceEmployeesAddBean.getSavedData().get(8) != null)
            decisionBean.setPreviousNavigationCase(advanceEmployeesAddBean.getSavedData().get(8).toString());
        if (advanceEmployeesAddBean.getSavedData().get(9) != null)
            decisionBean.setFinishNavigationCase(advanceEmployeesAddBean.getSavedData().get(9).toString());
        if (advanceEmployeesAddBean.getSavedData().get(10) != null)
            decisionBean.setCurrentNavigationCase(advanceEmployeesAddBean.getSavedData().get(10).toString());


        decisionListBean.setIndexArray((boolean[])advanceEmployeesAddBean.getSavedData().get(12));
        decisionListBean.setSortColumn(advanceEmployeesAddBean.getSavedData().get(13).toString());
        decisionListBean.setSaveSortingState(((Boolean)advanceEmployeesAddBean.getSavedData().get(14)).booleanValue());
        decisionListBean.setSortAscending(((Boolean)advanceEmployeesAddBean.getSavedData().get(15)).booleanValue());
        if (advanceEmployeesAddBean.getSavedData().get(16) != null) {
            decisionListBean.setFullColumnName(advanceEmployeesAddBean.getSavedData().get(16).toString());
        }
        decisionBean.setCurrentStep("employeesadd");
        this.getWizardBar().setCurrentStep("employeesadd");
        return "decisionemployeesmaintain";
    }

    public void setAdvanceEmployeesAddBean(AdvanceEmployeesAddBean advanceEmployeesAddBean) {
        this.advanceEmployeesAddBean = advanceEmployeesAddBean;
    }

    public AdvanceEmployeesAddBean getAdvanceEmployeesAddBean() {
        return advanceEmployeesAddBean;
    }

    public void setUnDeleteEmpList(List unDeleteList) {
        this.unDeleteEmpList = unDeleteList;
    }

    public List getUnDeleteEmpList() {
        return unDeleteEmpList;
    }

    public void setValueNum(Long valueNum) {
        this.valueNum = valueNum;
    }

    public Long getValueNum() {
        return valueNum;
    }

    public void setValueChr(String valueChr) {
        this.valueChr = valueChr;
    }

    public String getValueChr() {
        return valueChr;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setEmpInfTypeCode(Long empInfTypeCode) {
        this.empInfTypeCode = empInfTypeCode;
    }

    public Long getEmpInfTypeCode() {
        return empInfTypeCode;
    }

    public void setBooleanInformEmpFlag(Boolean booleanInformEmpFlag) {
        this.booleanInformEmpFlag = booleanInformEmpFlag;
    }

    public Boolean getBooleanInformEmpFlag() {
        return booleanInformEmpFlag;
    }

    public void setInformEmpFlag(Long informEmpFlag) {
        this.informEmpFlag = informEmpFlag;
    }

    public Long getInformEmpFlag() {
        return informEmpFlag;
    }

    public void setInformEmpDate(Date informEmpDate) {
        this.informEmpDate = informEmpDate;
    }

    public Date getInformEmpDate() {
        return informEmpDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setInformTypeList(List<IBasicDTO> informTypeList) {
        this.informTypeList = informTypeList;
    }

    public List<IBasicDTO> getInformTypeList() {
        try {

            informTypeList = 
                    RegClientFactory.getRegEmpInformTypesClient().getAll();

        } catch (SharedApplicationException e) {
            informTypeList = new ArrayList();
            e.printStackTrace();
        } catch (DataBaseException e) {
            informTypeList = new ArrayList();
            e.printStackTrace();
        }
        return informTypeList;
    }

    public void setActiveValidationOnAdd(boolean activeValidationOnAdd) {
        this.activeValidationOnAdd = activeValidationOnAdd;
    }

    public boolean isActiveValidationOnAdd() {
        return activeValidationOnAdd;
    }

    public void setActiveValidationOnEdit(boolean activeValidationOnEdit) {
        this.activeValidationOnEdit = activeValidationOnEdit;
    }

    public boolean isActiveValidationOnEdit() {
        return activeValidationOnEdit;
    }

    public void setCivilID(Long civilID) {
        this.civilID = civilID;
    }

    public Long getCivilID() {
        return civilID;
    }

    public void findEmployeeByCivilIDFromEmployees() {
        try {
            IEmployeesDTO employeesDTO = 
                (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getById(EmpEntityKeyFactory.createEmployeesEntityKey(getCivilID()));
            DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean = 
                (DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");

            if (currentDetails == null)
                currentDetails = new ArrayList<IBasicDTO>();
            IBasicDTO dto = employeesDTO;

            decisionEmployeesModelSessionBean.setExist(decisionEmployeesModelSessionBean.checkExistData(dto));
            if (!decisionEmployeesModelSessionBean.isExist()) {
                IBasicDTO mdto = this.mapToDetailDTO(dto);
                mdto.setStatusFlag(added);
                ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
                decisionEmployeesModelSessionBean.addNewElement(mdto);
            } else {
                getSharedUtil().handleException(new Exception(), 
                                                "com.beshara.csc.nl.reg.presentation.resources.reg", 
                                                "employee_added_before");
                setCivilID(null);
            }
            setCivilID(null);
            try {
                this.cancelSearchAvailable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ItemNotFoundException e) {
            setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.window.customDiv1);document.getElementById('OkButtondivcustomDiv1').focus();");
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            // TODO
        } catch (DataBaseException e) {
            e.printStackTrace();
            // TODO
        }
    }

    public void findPersonByCivilIDFromEmployees() {
        try {
            DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean = 
                (DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = 
                (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getByIdInCenter(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(getCivilID()));
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
            employeesDTO.setCitizensResidentsDTO(kwtCitizensResidentsDTO);
            employeesDTO.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId()));
            IHireStatusDTO hireStatusDTO = EmpDTOFactory.createHireStatusDTO();
            hireStatusDTO.setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.NOT_EMPLOYEE));
            employeesDTO.setHireStatusDTO(hireStatusDTO);
            if (currentDetails == null)
                currentDetails = new ArrayList<IBasicDTO>();
            IBasicDTO dto = employeesDTO;

            decisionEmployeesModelSessionBean.setExist(decisionEmployeesModelSessionBean.checkExistData(dto));
            if (!decisionEmployeesModelSessionBean.isExist()) {
                IBasicDTO mdto = this.mapToDetailDTO(dto);
                mdto.setStatusFlag(added);
                ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
                decisionEmployeesModelSessionBean.addNewElement(mdto);
            }


            // goFirstPage(this.getAvailableDataTable());
            this.restoreDetailsTablePosition(this.getAvailableDataTable(), 
                                             availableDetails);
            this.resetSelection();
            if (decisionEmployeesModelSessionBean.isExist()) {
                getSharedUtil().handleException(new Exception(), 
                                                "com.beshara.csc.nl.reg.presentation.resources.reg", 
                                                "employee_added_before");
                setCivilID(null);
            }
            try {
                this.cancelSearchAvailable();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setCivilID(null);
        } catch (ItemNotFoundException e) {
            setCivilID(null);
            getSharedUtil().handleException(e, 
                                            "com.beshara.csc.nl.reg.presentation.resources.reg", 
                                            "civil_not_found_center");
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            // TODO
        } catch (DataBaseException e) {
            e.printStackTrace();
            // TODO
        }
    }

    public void showSearchForEmployeeDiv() {
        employeeListOfValuesBean.setReturnMethodName("decisionEmployeesMaintainBean.returnMethodAction");
        employeeListOfValuesBean.resetData();
        employeeListOfValuesBean.setUsingBsnPaging(true);
        employeeListOfValuesBean.setUsingPaging(true);
        employeeListOfValuesBean.setSearchInfCenter(false);
    }

    public void returnMethodAction() {
        if (getEmpListOfValues().getSelectedDTOS() != null && 
            getEmpListOfValues().getSelectedDTOS().get(0) != null && 
            getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
            List<IBasicDTO> selectedAvailableDetails = 
                getEmpListOfValues().getSelectedDTOS();
            if (currentDetails == null)
                currentDetails = new ArrayList<IBasicDTO>();

            DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean = 
                (DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
            if (selectedAvailableDetails != null)
                for (int i = 0; i < selectedAvailableDetails.size(); i++) {
                    IBasicDTO dto = selectedAvailableDetails.get(i);
                    decisionEmployeesModelSessionBean.setExist(decisionEmployeesModelSessionBean.checkExistData(dto));
                    if (!decisionEmployeesModelSessionBean.isExist()) {
                        IBasicDTO mdto = this.mapToDetailDTO(dto);
                        mdto.setStatusFlag(added);
                        ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
                        decisionEmployeesModelSessionBean.addNewElement(mdto);
                        i--;
                    }
                }
            // goFirstPage(this.getAvailableDataTable());
            //            this.restoreDetailsTablePosition(this.getAvailableDataTable(),  availableDetails);
            //            this.resetSelection();
            try {
                this.cancelSearchAvailable();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setCivilID(null);

        }

    }


    ///////////////////////////////////////////////////////////////// add by amr galal //////////////////////////////////////////////////////////

    public void getNextPage(ActionEvent event) {
        DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean = 
            (DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
        setCurrentPageIndex(decisionEmployeesModelSessionBean.determineNoOfPaqe());
        changePageIndex(event);
        decisionEmployeesModelSessionBean.navigateToNextPage(new Long(getCurrentPageIndex()));
    }


    public int getCurrentListSize() {
        DecisionMaintainBean decisionMaintainBean = 
            (DecisionMaintainBean)BeansUtil.getValue("decisionMaintainBean");
        DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean = 
            (DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");

        if (decisionEmployeesModelSessionBean.getOriginalMap().size() == 0L && 
            decisionEmployeesModelSessionBean.getNewCurrentListSize() == 0) {
            if (decisionMaintainBean.getMaintainMode() == 0) {
                countOfAllEmpDecision = 0;
            } else {
                countOfAllEmpDecision = 
                        ((IEmpDecisionsDTO)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(0)).getCountOfAllPage().intValue();
            }
        } else if (decisionEmployeesModelSessionBean.getOriginalMap().size() != 
                   0L && 
                   decisionEmployeesModelSessionBean.getNewCurrentListSize() == 
                   0) {
            countOfAllEmpDecision = 
                    ((IEmpDecisionsDTO)((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().get(0)).getCountOfAllPage().intValue();
        } else {
            countOfAllEmpDecision = 
                    decisionEmployeesModelSessionBean.getNewCurrentListSize();
        }
        return countOfAllEmpDecision;
    }

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

    public void setCountOfAllEmpDecision(int countOfAllEmpDecision) {
        this.countOfAllEmpDecision = countOfAllEmpDecision;
    }

    public int getCountOfAllEmpDecision() {
        return countOfAllEmpDecision;
    }
}
