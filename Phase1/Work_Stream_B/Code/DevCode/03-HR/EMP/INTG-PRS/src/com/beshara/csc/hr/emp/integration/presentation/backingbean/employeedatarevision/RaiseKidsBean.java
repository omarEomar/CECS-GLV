package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;


//import com.beshara.csc.hr.mer.integration.presentation.backingbean.issuedecision.IssueDecisionBean;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.sal.business.client.ISalEmpChildrenClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpChildrenDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpWifesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalRaiseDecisionParamsDTO;
import com.beshara.csc.hr.sal.business.dto.ISettDecisionDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalEmpChildrenDTO;
import com.beshara.csc.hr.sal.business.dto.SalRaiseDecisionParamsDTO;
import com.beshara.csc.hr.sal.business.entity.SalEmpChildrenEntityKey;
import com.beshara.csc.hr.sal.business.shared.IMerConstant;
import com.beshara.csc.hr.sal.business.shared.exception.ChildRaiseMatchException;
import com.beshara.csc.hr.sal.business.shared.exception.NoValidChildrenToRaiseException;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.HandicapStatusDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.ReligionsDTO;
import com.beshara.csc.inf.business.entity.IHandicapStatusEntityKey;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.IReligionsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.integration.presentation.backingbean.reg.DecisionJoinBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.sal.exception.StopDateOverlappedwithOldChildRaiseException;
import com.beshara.csc.sharedutils.business.sal.exception.ValidEmpChildRaiseExistException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.util.ErrorDisplay;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.sql.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class RaiseKidsBean extends MerRaiseMaintainBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private boolean disableEdit = true;
    private static final int PAGE_MODE_SEARCH = 3;
    private static final String MER_RESOURCES = "com.beshara.csc.hr.mer.presentation.resources.mer";
    private static final String STEP_NAME = "raiseKidsNav";
    private String divCivilId = "";
    private boolean divValidCivil = true;
    // private ISalEmpChildrenDTO salEmpChildrenDTO = SalDTOFactory.createSalEmpChildrenDTO ( ) ;
    private ISalEmpChildrenDTO salEmpChildrenDTOforSuspDiv = SalDTOFactory.createSalEmpChildrenDTO();
    private String selectedKidsSuspenionElements = "";
    //    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
    private List kidsSuspensionReason = null;
    private String salStopCrReasons = ISystemConstant.VIRTUAL_VALUE.toString();
    private MerRaiseMaintainBean merRaiseMaintainBean =
        (MerRaiseMaintainBean)evaluateValueBinding("merRaiseMaintainBean");

//    private DecisionMaintainBean decisionMaintainBean =
//        (DecisionMaintainBean)evaluateValueBinding("decisionMaintainBean");
//    private DecisionMainDataMaintain decisionMainDataMaintainBean =
//        (DecisionMainDataMaintain)evaluateValueBinding("decisionMainDataMaintainBean");

    private boolean divCivilExist;
    private boolean hasSameParentID = false;
    private boolean showErrorSaveAndNew = false;
    private String errSaveAndNewMsg = null;
    private DecisionJoinBean decisionIntegration = new DecisionJoinBean();
    private boolean showSuspendPartAddDiv = false;

    private List<IBasicDTO> handicapStatusList = null; //new ArrayList<IBasicDTO>();
    private Long handicapstatusCode = getVirtualLongValue();
    private Long handicapstatusCodeEdit = getVirtualLongValue();
    //private ISalEmpSalaryElementsDTO salEmpSalaryElementsDTOHandicapped =
    //    SalDTOFactory.createSalEmpSalaryElementsDTO();
    private String searchTyp = "0";

    private String selMother = this.getVirtualConstValue();
    private String newChildMother = "";
    private String newChildMotherForEdit = "";
    private List empWives;


    public RaiseKidsBean() {
        this.setSearchTyp("0");
        setSelectedPageDTO(SalDTOFactory.createSalEmpChildrenDTO());
        setPageDTO(SalDTOFactory.createSalEmpChildrenDTO());
        setClient(SalClientFactory.getSalEmpChildrenClient());
        getSelectedPageDTO();
        try {
            if (handicapStatusList == null)
                handicapStatusList = InfClientFactory.getHandicapStatusClient().getAll();
        } catch (DataBaseException dbe) {
            // TODO: Add catch code
            dbe.printStackTrace();
        } catch (SharedApplicationException sae) {
            // TODO: Add catch code
            sae.printStackTrace();
        }

        ((SalEmpChildrenDTO)getPageDTO()).setKwtCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
        ((SalEmpChildrenDTO)getPageDTO()).getKwtCitizensResidentsDTO().setCapstatusCode(getVirtualLongValue());

        this.setEmployeesDTO(EmpDTOFactory.createEmployeesDTO());
        this.setEmpListOfValues(new EmployeeListOfValues());
        merRaiseMaintainBean.setDetailFilterMethod("raiseKidsBean.filterByCivilId");
        merRaiseMaintainBean.setDetailResetMethod("raiseKidsBean.reSetData");
        // setDivMainContent("delDivScroll2");
        decisionIntegration.okMethodName = "raiseKidsBean.saveSelectedDecision";
        //salEmpSalaryElementsDTOHandicapped = SalDTOFactory.createSalEmpSalaryElementsDTO();
        //  setMasterDetailDiv("divREGIntegrate");
        // setLookupAddDiv("lookupAddMerRaiseAdddiv");
        // setLookupEditDiv("lookupAddMerRaiseAdddiv");
        setKidsBeanFlag(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        //app.setShowSearch(false);
        app.setShowWizardBar(true);
        app.setShowContent1(true);
        app.setShowEmpSrchDiv(false);
        app.setShowLookupEdit(false);
        app.setShowCustomDiv1(false);
        app.setShowCustomDiv2(false);
        app.setShowMasterDetail(false);
       // app.setShowbar(t);
        // app.setShowIntegrationDiv1(true);
        return app;
    }

    /*public void updateChildrenAllowanceAction() {
        ISalEmpSalaryElementsClient salEmpSalaryElementsClient = SalClientFactory.getSalEmpSalaryElementsClient();
        Long civilId = new Long(merRaiseMaintainBean.getCivilId());


        try {
            if (civilId != null) {
                salEmpSalaryElementsClient.updateChildrenallowance(civilId);
                RaiseValueBean raiseValueBean = (RaiseValueBean)evaluateValueBinding("raiseValueBean");
                raiseValueBean.buildSalEmpRaiseDTOByCivilAndType(civilId);
                getSharedUtil().setSuccessMsgValue(getSharedUtil().messageLocator(MER_RESOURCES,
                                                                                  "successfullyAllowanceUpdate"));


            }

        } catch (NoElementsWithValidConditionException e) {
            getSharedUtil().handleException(e, MER_RESOURCES, "noElementsWithValidConditionExceptionKey");
        } catch (EmployeeHasNoChildrenException e) {
            getSharedUtil().handleException(e);
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e);
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e);
        }
    }*/

    /*public void selectedRadioButton(ValueChangeEvent event) throws Exception {
        if (event.getNewValue() != null) {
            setSelectedDTOS(new ArrayList<IBasicDTO>());
            IClientDTO selected = (IClientDTO)this.getCurrentDataTable().getRowData();
            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().add(selected);
        }
    }*/

    public void selectedRadioButton(ActionEvent event) throws Exception {
        super.selectedRadioButton(event);
        if (((SalEmpChildrenDTO)getSelectedDTOS().get(0)).getStopDate() != null ||
            ((SalEmpChildrenDTO)getSelectedDTOS().get(0)).getStatus() == 1) {
            setDisableEdit(true);
        } else {
            setDisableEdit(false);

        }
    }

    public void reSetData() {
        setMyTableData(new ArrayList());
        empWives = new ArrayList<IBasicDTO>();
        //salEmpSalaryElementsDTOHandicapped = SalDTOFactory.createSalEmpSalaryElementsDTO();
        setPageMode(0);
        getSelectedRadio();
        setSelectedRadio("");
        this.setHandicapstatusCode(getVirtualLongValue());
        this.setHandicapstatusCodeEdit(getVirtualLongValue());
        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0)
            getSelectedDTOS().clear();
    }

    public void filterByCivilId() {

        String currentEmpCivil = this.getCivilId();
        
        //        if(currentEmpCivil == null || currentEmpCivil.equals("")){
        //            empWives = new ArrayList<IBasicDTO>();
        //        }else{
        if (merRaiseMaintainBean.getEmployeesDTO() != null &&
            merRaiseMaintainBean.getEmployeesDTO().getCode() != null) {
            IEmployeesDTO currentEmp = merRaiseMaintainBean.getEmployeesDTO();
            //                EmpEntityKeyFactory.createEmployeesEntityKey(Long.valueOf(currentEmpCivil));
            try {
                if (!merRaiseMaintainBean.isFemaleGender()) {
                
                
                    // added by nora  CSC-19863
                    try {
                        empWives =
                                SalClientFactory.getSalEmpWifesClient().getEmployeeValidWives(merRaiseMaintainBean.getEmployeesDTO().getCode());

                    } catch (Exception dbe) {
                        // TODO: Add catch code
                        dbe.printStackTrace();
                    } 
                    /*
                    * old comment by nora
                    empWives =
                            SalClientFactory.getSalEmpWifesClient().getEmployeeCurrentWifesByRealCivilId(merRaiseMaintainBean.getEmployeesDTO());
                    */
                } else {
                    if (empWives == null)
                        empWives = new ArrayList<IBasicDTO>();

                    ISalEmpWifesDTO salEmpWifesDTO = SalDTOFactory.createSalEmpWifesDTO();
                    salEmpWifesDTO.setEmployeesDTO(currentEmp);
                    salEmpWifesDTO.setKwtCitizensResidentsDTO((IKwtCitizensResidentsDTO)currentEmp.getCitizensResidentsDTO());
                    empWives.add(salEmpWifesDTO);
                }

                //getEmployeeValidWives(currentEmp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            empWives = new ArrayList<IBasicDTO>();
        }

        setMyTableData(getTotalList());
    }

    public void openSuspendDiv() {
        clearDTO();

        showEditDiv();
        getSalEmpChildrenDTOforSuspDiv().setAllowanceDate(((ISalEmpChildrenDTO)getSelectedPageDTO()).getAllowanceDate());
        setPageMode(3);
    }

    @Override
    public void showEditDiv() {
        handicapstatusCodeEdit =
                Long.parseLong(((SalEmpChildrenDTO)getSelectedDTOS().get(0)).getKwtCitizensResidentsDTO().getHandicapStatusDTO().getCode().getKey());
        newChildMotherForEdit = ((SalEmpChildrenDTO)getSelectedDTOS().get(0)).getMotherDTO().getCivilId().toString();
        setPageMode(20);
        super.showEditDiv();
        setPageMode(20);
    }

    public String clearDTO() {
        setSelectedKidsSuspenionElements(getVirtualConstValue());
        setSalEmpChildrenDTOforSuspDiv(SalDTOFactory.createSalEmpChildrenDTO());
        return "";
    }

    public void saveSuspenion() throws DataBaseException {
        boolean validChildKey = true;
        ISalEmpChildrenDTO suspendDTO = (ISalEmpChildrenDTO)super.getSelectedPageDTO();
        if (salEmpChildrenDTOforSuspDiv != null) {
            suspendDTO.setStopDate(salEmpChildrenDTOforSuspDiv.getStopDate());
            suspendDTO.setStopReasonsKey(salEmpChildrenDTOforSuspDiv.getStopReasonsKey());
        }
        try {
            SalClientFactory.getSalEmpChildrenClient().validUpdateEmpChildernRaise(suspendDTO);
            validChildKey = true;
        } catch (StopDateOverlappedwithOldChildRaiseException e) {
            e.printStackTrace();
            validChildKey = false;
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "StopDateOverlappedwithOldChildRaiseException");
            if (e.getExtraInformation() != null && e.getExtraInformation().size() != 0 &&
                e.getExtraInformation().get(0) != null && e.getExtraInformation().get(1) != null) {
                Date allownDate = (Date)e.getExtraInformation().get(0);
                Date stopDate = (Date)e.getExtraInformation().get(1);
                getSharedUtil().setErrMsgValue(getSharedUtil().getErrMsgValue() + " ( " + allownDate + " ) , ( " +
                                               stopDate + " ) ) ");
                errSaveAndNewMsg = getSharedUtil().getErrMsgValue();
            }
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e, MER_RESOURCES, "MER_CHILD_SUSPEND_FAILURE_MSG");
        }
        if (validChildKey) {
            SharedUtilBean shared_util = getSharedUtil();
            try {
                SalClientFactory.getSalEmpChildrenClient().suspendChildern(suspendDTO);
                cancelSearch();
                if (super.isUsingPaging()) {
                    generatePagingRequestDTO((String)suspendDTO.getCode().getKey());
                } else {
                    getPageIndex((String)suspendDTO.getCode().getKey());
                }
                if (super.getHighlightedDTOS() != null) {
                    super.getHighlightedDTOS().add(suspendDTO);
                }
                super.setShowEdit(false);
                shared_util.handleSuccMsg(MER_RESOURCES, "MER_CHILD_SUSPEND_SUCESS_MSG");
            } catch (DataBaseException e) {
                shared_util.handleException(e, MER_RESOURCES, "MER_CHILD_SUSPEND_FAILURE_MSG");
            } catch (Exception e) {
                e.printStackTrace();
                shared_util.handleException(e, MER_RESOURCES, "MER_CHILD_SUSPEND_FAILURE_MSG");
            }
        }
    }

    public List getKidsSuspensionReason() {
        //        if (kidsSuspensionReason == null) {
        //            ISalStopCrReasonsClient iSalStopCrReasonsClient = SalClientFactory.getSalStopCrReasonsClient();
        //            try {
        //                kidsSuspensionReason = iSalStopCrReasonsClient.getCodeName();
        //            } catch (DataBaseException e) {
        //                e.printStackTrace();
        //                return kidsSuspensionReason;
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //                return kidsSuspensionReason;
        //            }
        //        }
        return kidsSuspensionReason;
    }

    public String issueFemaleDecision() {   
//        String returnMethodName = "raiseKidsBean.returnFemaleRaiseDecision";
//        String successMethodName = "raiseKidsBean.addFemaleRaiseDecision";
//        IssueDecisionBean issueDecisionBean = IssueDecisionBean.getInstance();
//
//        ISalRaiseDecisionParamsDTO salRaiseDecisionParamsDTO = _fillDecisionParams(true);
//
//        try {
//            List<IBasicDTO> list = getMyTableData();
//            ISalEmpChildrenDTO dto = (ISalEmpChildrenDTO)list.get(0);
//            dto.setAllowanceDate(null);
//            dto.setStopDate(null);
//       
//            issueDecisionBean.init(fillSaveStateParams(), "raiseKidsNav", returnMethodName, successMethodName,
//                                   salRaiseDecisionParamsDTO, list, "add_raise_decision");
//            return IssueDecisionBean.PAGE_NAVIGATION_CASE;
//        } catch (DataBaseException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    public void returnFemaleRaiseDecision(Object params) {
        System.out.println("returned from add dssss");
        restoreSaveStateParams(params);
        filterByCivilId();
    }

    public Long addFemaleRaiseDecision(Object params, Object conditinsDTO, Object salEmpChildDTO, Object raiseType,
                                       ISettDecisionDTO settDecDTO) throws SharedApplicationException {

        SharedUtilBean shared_util = getSharedUtil();
        try {
            ((ISalEmpChildrenClient)getClient()).makeFemaleChildRaiseDecision((IBasicDTO)conditinsDTO,
                                                                              (List)salEmpChildDTO, (Boolean)raiseType,
                                                                              settDecDTO);
            shared_util.handleSuccMsg(MER_RESOURCES, "Decision_Add_Success");
            restoreSaveStateParams(params);
            setDisableEdit(true);
            filterByCivilId();

        } catch (SharedApplicationException e) {
//            if (e instanceof DuplicateDecisionException) {
//                throw e;
//            } else 
            if (e instanceof NoValidChildrenToRaiseException) {
                returnFemaleRaiseDecision(params);
                shared_util.handleException(e, MER_RESOURCES, "NoValidChildrenToRaiseException");
            } else if (e instanceof ChildRaiseMatchException) {
                returnFemaleRaiseDecision(params);
                shared_util.handleException(e, MER_RESOURCES, "female_raise_not_changed");
            }else {
                returnFemaleRaiseDecision(params);
                shared_util.handleException(e, MER_RESOURCES, "save_unsuccessfull");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnFemaleRaiseDecision(params);
            shared_util.handleException(e, MER_RESOURCES, "save_unsuccessfull");
        }
        return null;
    }


    public void handleGeneralException(Exception e, String key) {
        SharedUtilBean shared_util = getSharedUtil();
        shared_util.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", key);
    }

    public void setDivCivilId(String divCivilId) {
        this.divCivilId = divCivilId;
    }

    public String getDivCivilId() {
        return divCivilId;
    } // public void setSalEmpChildrenDTO ( ISalEmpChildrenDTO salEmpChildrenDTO ) {
    // this.salEmpChildrenDTO = salEmpChildrenDTO ;
    // }
    //
    // public ISalEmpChildrenDTO getSalEmpChildrenDTO ( ) {
    // return salEmpChildrenDTO ;
    // }

    public void setSalEmpChildrenDTOforSuspDiv(ISalEmpChildrenDTO salEmpChildrenDTOforSuspDiv) {
        this.salEmpChildrenDTOforSuspDiv = salEmpChildrenDTOforSuspDiv;
    }

    public ISalEmpChildrenDTO getSalEmpChildrenDTOforSuspDiv() {
        return salEmpChildrenDTOforSuspDiv;
    }

    public void setSelectedKidsSuspenionElements(String selectedKidsSuspenionElements) {
        this.selectedKidsSuspenionElements = selectedKidsSuspenionElements;
    }

    public String getSelectedKidsSuspenionElements() {
        return selectedKidsSuspenionElements;
    }

    //    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
    //        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    //    }
    //
    //    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {
    //        return kwtCitizensResidentsDTO;
    //    }

    public void setKidsSuspensionReason(List kidsSuspensionReason) {
        this.kidsSuspensionReason = kidsSuspensionReason;
    }

    public void setSalStopCrReasons(String salStopCrReasons) {
        this.salStopCrReasons = salStopCrReasons;
    }

    public String getSalStopCrReasons() {
        return salStopCrReasons;
    } //=====================start add part ================================

    public void add() {
        try {
            reIntializeMsgs();
            KwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                (KwtCitizensResidentsDTO)((ISalEmpChildrenDTO)getPageDTO()).getKwtCitizensResidentsDTO();

            kwtCitizensResidentsDTO.setCivilId(Long.valueOf(getDivCivilId()));
            kwtCitizensResidentsDTO.setCapstatusCode(handicapstatusCode);
            IHandicapStatusEntityKey handicapStatusEntityKey =
                (IHandicapStatusEntityKey)InfEntityKeyFactory.createHandicapStatusEntityKey(handicapstatusCode);
            HandicapStatusDTO handicapStatusDTO =
                (HandicapStatusDTO)InfClientFactory.getHandicapStatusClient().getById(handicapStatusEntityKey);
            kwtCitizensResidentsDTO.setHandicapStatusDTO(handicapStatusDTO);

            // now set required values to default
            kwtCitizensResidentsDTO.setReligionCode(1L);
            IReligionsEntityKey religionsEntityKey =
                (IReligionsEntityKey)InfEntityKeyFactory.createReligionsEntityKey(1L);
            ReligionsDTO religionsDTO =
                (ReligionsDTO)InfClientFactory.getReligionsClient().getById(religionsEntityKey);
            kwtCitizensResidentsDTO.setReligionsDTO(religionsDTO);

            kwtCitizensResidentsDTO.setActiveFlag(1L);

            //            IKwtCitizensResidentsEntityKey motherEntityKey =
            //                InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(Long.valueOf(newChildMother));

            IKwtCitizensResidentsDTO motherDTO = null;
            if (merRaiseMaintainBean.isFemaleGender()) {
                IEmployeesDTO currentEmp = merRaiseMaintainBean.getEmployeesDTO();
                motherDTO = (IKwtCitizensResidentsDTO)currentEmp.getCitizensResidentsDTO();
            } else {
                motherDTO =
                        (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenInformation(Long.valueOf(newChildMother));
            }
            ((ISalEmpChildrenDTO)getPageDTO()).setMotherDTO(motherDTO);
            super.add();
            //            if (getSharedUtil().getSuccessMsgValue() != null && !getSharedUtil().getSuccessMsgValue().equals(""))
            //                this.setSuccess(true);
            if (!showSuspendPartAddDiv && !showErrorSaveAndNew) {
                this.reInitializePageDTO();
                setDivCivilId("");
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "FailureInAdd");
        } catch (Exception ex) {
            this.setShowErrorMsg(true);
            this.setErrorMsg(ex.getCause().getMessage());
            ex.printStackTrace();
            //showDiv ( ) ;
        }

    }

    public void cancelAdd() {
        resetKidsAddDivValues();
        super.cancelAdd();
    }

    public void reInitializePageDTO() {
        this.setPageDTO(SalDTOFactory.createSalEmpChildrenDTO());
        newChildMother = "";
        newChildMotherForEdit = "";
    }

    public void preAdd() {
        super.preAdd();
        setDisableEdit(true);
        //salEmpSalaryElementsDTOHandicapped = SalDTOFactory.createSalEmpSalaryElementsDTO();
        try { // if ( getSalEmpSalaryElementsDTOHandicapped ( ) != null && ( getYearHandicapped ( ) != null || getYearHandicapped ( ) != "" ) && ( getMonthHandicapped ( ) != null || getMonthHandicapped ( ) != "" ) )
            // {
            if (handicapStatusList == null)
                handicapStatusList = InfClientFactory.getHandicapStatusClient().getAll();
            //SalClientFactory.getSalElementGuidesClient().getCodeNameByTypeCode(ISalConstants.ELEMENT_GUIDE_TYPE_HANDICAPPED_CHILDREN_PROMOTION_CAT);
            // }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        resetKidsAddDivValues();
        setPageDTO(SalDTOFactory.createSalEmpChildrenDTO());
        ((SalEmpChildrenDTO)getPageDTO()).setKwtCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
        //((SalEmpChildrenDTO)getPageDTO() ).getKwtCitizensResidentsDTO().setCapstatusCode(getVirtualLongValue() );
        showSuspendPartAddDiv = false;
        newChildMother = "";
    }

    public void resetKidsAddDivValues() {
        this.reInitializePageDTO();
        reIntializeAddDivMsgs();
        divValidCivil = true;
        divCivilExist = false;
        hasSameParentID = false;
        setHandicapstatusCode(getVirtualLongValue());
        setHandicapstatusCodeEdit(getVirtualLongValue());
        setDivCivilId("");

    }

    public String getDivInfoByCivilId() {
        reIntializeMsgs();
        //        try {// this part just for test method checkEmpChildernExistAndValid by Aly Nour 21-12-2014
        //            Long civilId = new Long(getDivCivilId());
        //            ((ISalEmpChildrenClient)getClient()).checkEmpChildernExistAndValid(civilId);
        //        } catch (DataBaseException dbe) {
        //            // TODO: Add catch code
        //            dbe.printStackTrace();
        //        } catch (NumberFormatException nfe) {
        //            // TODO: Add catch code
        //            nfe.printStackTrace();
        //        } catch (SharedApplicationException sae) {
        //            // TODO: Add catch code
        //            sae.printStackTrace();
        //        } catch (RemoteException e) {
        //        }

        String childCivilId = getDivCivilId();
        if (childCivilId != null) {
            try {
                //                IKwtCitizensResidentsSearchDTO kwtCitizensResidentsSearchDTO =
                //                    InfDTOFactory.createKwtCitizensResidentsSearchDTO();
                //                kwtCitizensResidentsSearchDTO.setCivilId(Long.valueOf(childCivilId));
                ((ISalEmpChildrenDTO)getPageDTO()).setEmployeeKey(getCivilId());
                ((ISalEmpChildrenDTO)getPageDTO()).setEmployeesDTO(getEmployeesDTO());
                ((ISalEmpChildrenDTO)getPageDTO()).setKwtCitizensResidentsKey(getDivCivilId());
                boolean validChildernKey = true;
                try {
                    ISalEmpChildrenDTO salEmpChildrenDTO =
                        (ISalEmpChildrenDTO)SalClientFactory.getSalEmpChildrenClient().validAddEmpChildernRaise((ISalEmpChildrenDTO)getPageDTO());
                    setPageDTO(salEmpChildrenDTO);
                    divValidCivil = true;
                    divCivilExist = true;
                    if (childCivilId.equals(getCivilId()))
                        hasSameParentID = true;
                    else
                        hasSameParentID = false;

                    //handicapstatusCode = ((ISalEmpChildrenDTO)getPageDTO()).getKwtCitizensResidentsDTO().getCapstatusCode();
                    validChildernKey = true;
                } catch (ValidEmpChildRaiseExistException e) {
                    e.printStackTrace();
                    resetKidsAddDivValues();
                    setDivCivilId(childCivilId);
                    validChildernKey = false;
                    showErrorSaveAndNew = true;


                    //                    this.setErrorMsg("ValidEmpChildRaiseExistException");
                    //                    getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                    //                                                    "ValidEmpChildRaiseExistException");
                    if (e.getExtraInformation() != null && e.getExtraInformation().size() != 0) {
                        String msgContent1 = e.getExtraInformation().get(0).toString();
                        try {
                            String msgContent2 =
                                getSharedUtil().messageLocator(MER_RESOURCES, e.getExtraInformation().get(0).toString());
                            if (msgContent2 != null && !msgContent2.equals(""))
                                msgContent1 = msgContent2;

                        } catch (Exception e2) {
                            // TODO: Add catch code
                            e2.printStackTrace();
                            getSharedUtil().setErrMsgValue(msgContent1);
                        }
                        getSharedUtil().setErrMsgValue(msgContent1);
                        //getSharedUtil().getErrMsgValue() + " " + msgContent1 + " ) ");
                        errSaveAndNewMsg = getSharedUtil().getErrMsgValue();
                        //this.setErrorMsg(getSharedUtil().getErrMsgValue() );
                        //this.setShowErrorMsg(true);
                    }
                }
                //                catch (StopDateMustBeEnteredAtAddChildernRaiseException e) {
                //                    validChildernKey = false;
                //                    showSuspendPartAddDiv = true;
                //                    showErrorSaveAndNew = true;
                //                    String errMsgcontent1 =
                //                        getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.globalexceptions",
                //                                                       "StopDateMustBeEnteredAtAddChildernRaiseException1");
                //                    String errMsgcontent2 =
                //                        getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.globalexceptions",
                //                                                       "StopDateMustBeEnteredAtAddChildernRaiseException2");
                //                    this.setJavaScriptCaller("changeVisibilityDiv ( window.blocker , window.lookupAddDiv ) ; settingFoucsAtDivAdd ( ) ; ");
                //                    if (e.getExtraInformation() != null && e.getExtraInformation().size() != 0 &&
                //                        e.getExtraInformation().get(0) != null) {
                //                        Date validUnitlDate = (Date)e.getExtraInformation().get(0);
                //                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                //                        String dt = df.format(validUnitlDate);
                //                        String errMsgcontent = errMsgcontent1 + dt + errMsgcontent2;
                //                        errSaveAndNewMsg = errMsgcontent;
                //                    }
                //                } catch (AllowanceDateOverlappedwithOldChildRaiseException e) {
                //                    e.printStackTrace();
                //                    validChildernKey = false;
                //                    showErrorSaveAndNew = true;
                //                    this.setErrorMsg("AllowanceDateOverlappedwithOldChildRaiseException");
                //                    getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                //                                                    "AllowanceDateOverlappedwithOldChildRaiseException");
                //                    if (e.getExtraInformation() != null && e.getExtraInformation().size() != 0 &&
                //                        e.getExtraInformation().get(0) != null && e.getExtraInformation().get(1) != null) {
                //                        Date allownsDate = (Date)e.getExtraInformation().get(0);
                //                        Date stopDate = (Date)e.getExtraInformation().get(1);
                //                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                //                        String dt1 = df.format(allownsDate);
                //                        String dt2 = df.format(stopDate);
                //                        getSharedUtil().setErrMsgValue(getSharedUtil().getErrMsgValue() + " ( " + dt1 + " ) , ( " + dt2 +
                //                                                       " ) ) ");
                //                        errSaveAndNewMsg = getSharedUtil().getErrMsgValue();
                //                    }
                //                }
                catch (SharedApplicationException e) {
                    e.printStackTrace();
                    validChildernKey = false;
                    this.setShowErrorMsg(true);
                    this.setErrorMsg("FailureInAdd");
                    getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                    "FailureInAdd");
                }
                //                catch (RemoteException e) {
                //                    e.printStackTrace();
                //                    validChildernKey = false;
                //                    this.setShowErrorMsg(true);
                //                    this.setErrorMsg("FailureInAdd");
                //                    getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                //                                                    "FailureInAdd");
                //                }
                //                List list =
                //                    InfClientFactory.getKwtCitizensResidentsClient().searchForWife(kwtCitizensResidentsSearchDTO);
                //                ((ISalEmpChildrenDTO)getPageDTO()).setKwtCitizensResidentsDTO((IKwtCitizensResidentsDTO)list.get(0));
                //            } catch (SharedApplicationException e) {
                //                e.printStackTrace();
                //                divValidCivil = false;
                //                divCivilExist = false;
                //                hasSameParentID = false;
                //                return "";
                //            } catch (DataBaseException e) {
                //                e.printStackTrace();
                //                divValidCivil = false;
                //                divCivilExist = false;
                //                hasSameParentID = false;
                //                return "";
            } catch (Exception e) {
                e.printStackTrace();
                divValidCivil = false;
                divCivilExist = false;
                hasSameParentID = false;
                return "";
            }
        } else {
            divValidCivil = true;
            divCivilExist = false;
            hasSameParentID = false;
            showSuspendPartAddDiv = false;
        }
        return "";
    }

    public void saveAndNew() throws DataBaseException {
        //        reIntializeMsgs();
        this.add();
        //        super.add();
        if (getSharedUtil().getSuccessMsgValue() != null && !getSharedUtil().getSuccessMsgValue().equals(""))
            this.setSuccess(true);
        if (!showSuspendPartAddDiv && !showErrorSaveAndNew) {
            //            setDivCivilId("");
            divValidCivil = true;
            divCivilExist = false;
            hasSameParentID = false;
            setHandicapstatusCode(getVirtualLongValue());
            //            this.reInitializePageDTO();
        }
    } //===================== End add part ================================

    public void setDivValidCivil(boolean divCivilExist) {
        this.divValidCivil = divCivilExist;
    }

    public boolean isDivValidCivil() {
        return divValidCivil;
    }

    public boolean isCivilExist() {
        return merRaiseMaintainBean.getCivilExist();
    }

    public boolean getCivilExist() {
        return merRaiseMaintainBean.getCivilExist();
    }

    public String getCivilId() {
        return merRaiseMaintainBean.getCivilId();
    }

    public boolean isValidCivilId() {
        return merRaiseMaintainBean.isValidCivilId();
    }

    public boolean isEmpHired() {
        return merRaiseMaintainBean.isEmpHired();
    }

    public boolean isPayrollInfoExist() {
        return merRaiseMaintainBean.isPayrollInfoExist();
    }

    public String getDegree() {
        return merRaiseMaintainBean.getDegree();
    }

    public IEmployeesDTO getEmployeesDTO() {
        return merRaiseMaintainBean.getEmployeesDTO();
    }

    public List getTotalList() {
        List totalList = new ArrayList();
        this.setSearchMode(false);
        Long civilIDLocal = null;
        if (merRaiseMaintainBean.getEmployeesDTO() != null &&
            merRaiseMaintainBean.getEmployeesDTO().getCode() != null) {
            civilIDLocal = ((IEmployeesEntityKey)merRaiseMaintainBean.getEmployeesDTO().getCode()).getCivilId();
        }
        Long motherCivil = null;

        if (selMother != null && !selMother.equals(getVirtualConstValue()))
            motherCivil = Long.valueOf(selMother);

        if (this.getCivilExist() && civilIDLocal != null && !civilIDLocal.equals("") && getCivilId() != null) {
            try {
                //totalList = SalClientFactory.getSalEmpChildrenClient().getAllByParentWithChildStatus(Long.valueOf(civilIDLocal));
                /*updated by Aly Nour 27-08-2015 since entered is real civil id which  is unique for Emp
                 * updated again by Aly Nour 29-09-2015 since children raise allowance date is related to emp hired date in min
                 * */
                totalList =
                        SalClientFactory.getSalEmpChildrenClient().getKidsByFatherAndMother(Long.valueOf(civilIDLocal),
                            //Long.valueOf(getCivilId() ), //Long.valueOf(civilIDLocal),
                            motherCivil);
                this.setFullColumnName(null);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalList;
    }

    public void getKidsByMother(ActionEvent ae) {
        setMyTableData(getTotalList());
        setSelectedRadio("");
        setDisableEdit(true);
    }

    public void setDivCivilExist(boolean divCivilExist) {
        this.divCivilExist = divCivilExist;
    }

    public boolean isDivCivilExist() {
        return divCivilExist;
    }

    public void setHasSameParentID(boolean hasSameParentID) {
        this.hasSameParentID = hasSameParentID;
    }

    public boolean getHasSameParentID() {
        return hasSameParentID;
    }

    public void setShowErrorSaveAndNew(boolean showErrorSaveAndNew) {
        this.showErrorSaveAndNew = showErrorSaveAndNew;
    }

    public boolean isShowErrorSaveAndNew() {
        return showErrorSaveAndNew;
    }

    public void setErrSaveAndNewMsg(String errSaveAndNewMsg) {
        this.errSaveAndNewMsg = errSaveAndNewMsg;
    }

    public String getErrSaveAndNewMsg() {
        return errSaveAndNewMsg;
    }

    public void showDetails(ActionEvent event) throws Exception {
        //int indexOfSelectedDataInDataTable = getMyDataTable().getRowIndex();
        this.getSelectedDTOS().clear();
        setSelectedRadio("");
        IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();
        this.getSelectedDTOS().add(selected);
        this.setSelectedPageDTO(selected);
        // updated
        ISalEmpChildrenDTO empDto = (ISalEmpChildrenDTO)selected;
        try {

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        boolean validChildKey = true;
        try {
            SalClientFactory.getSalEmpChildrenClient().validUpdateEmpChildernRaise((ISalEmpChildrenDTO)getSelectedPageDTO());
            validChildKey = true;
        } catch (StopDateOverlappedwithOldChildRaiseException e) {
            e.printStackTrace();
            validChildKey = false;
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "StopDateOverlappedwithOldChildRaiseException");
            if (e.getExtraInformation() != null && e.getExtraInformation().size() != 0 &&
                e.getExtraInformation().get(0) != null && e.getExtraInformation().get(1) != null) {
                Date allownDate = (Date)e.getExtraInformation().get(0);
                Date stopDate = (Date)e.getExtraInformation().get(1);
                getSharedUtil().setErrMsgValue(getSharedUtil().getErrMsgValue() + " ( " + allownDate + " ) , ( " +
                                               stopDate + " ) ) ");
                errSaveAndNewMsg = getSharedUtil().getErrMsgValue();
            }
        } catch (SharedApplicationException e) {
            getSharedUtil().setErrMsgValue("FailureInUpdate");
        }
        if (validChildKey) {
            KwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                (KwtCitizensResidentsDTO)((ISalEmpChildrenDTO)getSelectedDTOS().get(0)).getKwtCitizensResidentsDTO();


            kwtCitizensResidentsDTO.setCapstatusCode(handicapstatusCodeEdit);
            IHandicapStatusEntityKey handicapStatusEntityKey =
                (IHandicapStatusEntityKey)InfEntityKeyFactory.createHandicapStatusEntityKey(handicapstatusCodeEdit);
            HandicapStatusDTO handicapStatusDTO =
                (HandicapStatusDTO)InfClientFactory.getHandicapStatusClient().getById(handicapStatusEntityKey);
            kwtCitizensResidentsDTO.setHandicapStatusDTO(handicapStatusDTO);

            ((ISalEmpChildrenDTO)getSelectedDTOS().get(0)).getKwtCitizensResidentsDTO().setHandicapStatusDTO(handicapStatusDTO);

            InfClientFactory.getKwtCitizensResidentsClient().updateWifeSon(kwtCitizensResidentsDTO);


            IKwtCitizensResidentsEntityKey motherEntityKey =
                InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(Long.valueOf(newChildMotherForEdit));
            KwtCitizensResidentsDTO motherDTO =
                (KwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenInformation(Long.valueOf(newChildMotherForEdit));
            ((ISalEmpChildrenDTO)getSelectedDTOS().get(0)).setMotherDTO(motherDTO);


            ISalEmpChildrenClient client = SalClientFactory.getSalEmpChildrenClient();
            client.update((ISalEmpChildrenDTO)getSelectedDTOS().get(0));
            setMyTableData(getTotalList());
            //  super.edit();

        }


    }

    public void setShowSuspendPartAddDiv(boolean showSuspendPartAddDiv) {
        this.showSuspendPartAddDiv = showSuspendPartAddDiv;
    }

    public boolean isShowSuspendPartAddDiv() {
        return showSuspendPartAddDiv;
    }

    public void save() {
        //        try {
        //            reIntializeMsgs();
        this.add();
        //            super.add();
        if (!showSuspendPartAddDiv && !showErrorSaveAndNew) {
            //                this.reInitializePageDTO();
            reIntializeAddDivMsgs();
            //                setDivCivilId("");
        }
        ////        }catch (DataBaseException e) {
        ////            e.printStackTrace();
        ////            this.setShowErrorMsg(true);
        ////            this.setErrorMsg("FailureInAdd");
        ////            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
        ////                                            "FailureInAdd");
        //        }catch (Exception ex) {
        //            this.setErrorMsg(ex.getCause().getMessage());
        //            //showDiv ( ) ;
        //        }
    }

    public String addDecisoin() {
        getIntegrationBean().reInitializeBean();
        getIntegrationBean().setNavgationCaseFrom("raiseKidsNav");
        getIntegrationBean().setBeanNameTo("decisionListBean");
        getIntegrationBean().setActionTo("goAdd");
        getIntegrationBean().getHmBaseBeanFrom().put("raiseKidsBean", evaluateValueBinding("raiseKidsBean"));
        getIntegrationBean().getHmBaseBeanFrom().put("merRaiseMaintainBean",
                                                     evaluateValueBinding("merRaiseMaintainBean"));
        IEmployeesDTO emp = merRaiseMaintainBean.getEmployeesDTO();
        getIntegrationBean().getSelectedDTOFrom().add(emp);
        getIntegrationBean().setModuleFrom("sal");
        getIntegrationBean().setBeanNameFrom("raiseKidsBean");
        getIntegrationBean().setActionFrom("empAddDecisoin");
        //getIntegrationBean().setInitializeMethod("raiseKidsBean.initializeDecisionDTO");
        return getIntegrationBean().goTO();
    }


    public String addSuspendRaiseDecision() {
//        IssueDecisionBean issueDecisionBean = IssueDecisionBean.getInstance();
//        //        RaiseDecisionBean raiseDecisionBean = RaiseDecisionBean.getInstance();
//        String returnMethodName = "raiseKidsBean.returnRaiseDecision";
//        String successMethodName = "raiseKidsBean.successAddRaiseDecision";
//        String settDecSuccessMethodName = "raiseKidsBean.settDecisionSuccess";
//
//        ISalRaiseDecisionParamsDTO dto = fillDecisionParams(false);
//
//        ISalEmpChildrenDTO salEmpChildDTO = ((ISalEmpChildrenDTO)getSelectedDTOS().get(0));
//        ISalElementGuidesDTO salElementGuidesDTO = null;
//        try {
//            salElementGuidesDTO =
//                    ((ISalElementGuidesDTO)((ISalEmpChildrenClient)getClient()).getExpectedElmGuideForStopChildRaise((IBasicDTO)salEmpChildDTO));
//        } catch (DataBaseException e) {
//            e.printStackTrace();
//        } catch (SharedApplicationException e) {
//            e.printStackTrace();
//        }
//        Long elmGuideCode = null;
//
//        if (salElementGuidesDTO != null) {
//            elmGuideCode = ((ISalElementGuidesEntityKey)salElementGuidesDTO.getCode()).getElmguideCode();
//        }
//        if (elmGuideCode == null) {
//            issueDecisionBean.setUseSettelment(false);
//        }
//        issueDecisionBean.setElmGuideCode(elmGuideCode);
//
//        if (!merRaiseMaintainBean.isFemaleGender()) {
//            issueDecisionBean.setUseSettelment(true);
//        }
//        
//        issueDecisionBean.setDefaultEmpSuggSettMethod(false);
//        issueDecisionBean.setEmpSuggSettMethodName("raiseKidsBean.getSettDetails");
//        
//        //        raiseDecisionBean
//        issueDecisionBean.init(fillSaveStateParams(), "raiseKidsNav", returnMethodName, successMethodName, dto,
//                               getSelectedDTOS().get(0), "add_suspend_decision");
//        issueDecisionBean.setSettSuccessMethodName(settDecSuccessMethodName)       return IssueDecisionBean.PAGE_NAVIGATION_CASE; //"addRaiseDecisionNav";

return null;    }

    public void settDecisionSuccess(Object params) {
        restoreSaveStateParams(params);
        filterByCivilId();
        getSelectedDTOS().clear();
        setSelectedRadio("");
    }

    public String addRaiseDecision() {
        //        RaiseDecisionBean raiseDecisionBean = RaiseDecisionBean.getInstance();
//        SharedUtilBean shared_util = getSharedUtil();
//        ISalEmpChildrenDTO salEmpChildrenDTO = (ISalEmpChildrenDTO)getSelectedDTOS().get(0);
//        // CSC-16298
//        if (salEmpChildrenDTO.getChildStopStatus().equals(IMerConstant.LEGEL_AGE)) {
//            shared_util.handleException(null, MER_RESOURCES, "kid_grown_up");
//            return "";
//
//        }
//
//        if (salEmpChildrenDTO.getChildStopStatus().equals(IMerConstant.IS_EMPLOYEE)) {
//            shared_util.handleException(null, MER_RESOURCES, "kid_employed_error");
//            return "";
//
//        }
//        //END CSC-16298
//        /* commented by A.nour for story CSC-17628*/
//        /*if (salEmpChildrenDTO.getStopDate() != null) {
//            shared_util.handleException(null, MER_RESOURCES, "dec_msg");
//            return "";
//        }*/
//        if (salEmpChildrenDTO.getStatus().equals(IMerConstant.ACTIVE)) {
//            shared_util.handleException(null, MER_RESOURCES, "grant_dec_done_msg");
//            return "";
//        }
//        String returnMethodName = "raiseKidsBean.returnRaiseDecision";
//        String successMethodName = "raiseKidsBean.successAddRaiseDecision";
//        IssueDecisionBean issueDecisionBean = IssueDecisionBean.getInstance();
//        //        String returnMethodName = "merEmpBenListBean.decisionReturn";
//        //        String successMethodName = "merEmpBenListBean.successAddDecision";
//
//        String settDecSuccessMethodName = "raiseKidsBean.settDecisionSuccess";
//        
//        if (!merRaiseMaintainBean.isFemaleGender()) {
//            issueDecisionBean.setUseSettelment(true);
//        }
//
//        issueDecisionBean.setDefaultEmpSuggSettMethod(false);
//        issueDecisionBean.setEmpSuggSettMethodName("raiseKidsBean.getSettDetails");
//
//        ISalEmpChildrenDTO salEmpChildDTO = ((ISalEmpChildrenDTO)getSelectedDTOS().get(0));
//        ISalElementGuidesDTO dto = null;
//
//        //        ISalEmpSalaryElementsDTO dto = (ISalEmpSalaryElementsDTO)getSelectedDTOS().get(0);
//        try {
//            dto =
//((ISalElementGuidesDTO)((ISalEmpChildrenClient)getClient()).getExpectedElmGuideForChildRaise((IBasicDTO)salEmpChildDTO));
//        } catch (DataBaseException e) {
//            e.printStackTrace();
//        } catch (SharedApplicationException e) {
//            e.printStackTrace();
//        }
//        Long elmGuideCode = null;
//
//        if (dto != null) {
//            elmGuideCode = ((ISalElementGuidesEntityKey)dto.getCode()).getElmguideCode();
//        }
//        if (elmGuideCode == null) {
//            issueDecisionBean.setUseSettelment(false);
//        }
//
//        issueDecisionBean.setElmGuideCode(elmGuideCode);
//        ISalRaiseDecisionParamsDTO salRaiseDecisionParamsDTO = fillDecisionParams(true);
//        salEmpChildDTO.setStopDate(null);
//        issueDecisionBean.init(fillSaveStateParams(), "raiseKidsNav", returnMethodName, successMethodName,
//                               salRaiseDecisionParamsDTO, salEmpChildDTO, "add_raise_decision");
//        issueDecisionBean.setSettSuccessMethodName(settDecSuccessMethodName);
//        //        return "addRaiseDecisionNav";
//        return IssueDecisionBean.PAGE_NAVIGATION_CASE;
        return null;
    }

    public List<IBasicDTO> getSettDetails(Long realCivilId, Long elmGuideCode, Date fromDate, Date toDate,
                                          Float amount, Long decisionType,
                                          Long empSalElmSerial) throws DataBaseException, SharedApplicationException {

        return SalClientFactory.getSalEmpSettelmentsClient().calcChildAndSocialRaiseEmpSuggSett(realCivilId,
                                                                                                elmGuideCode, fromDate,
                                                                                                toDate, amount,
                                                                                                decisionType,
                                                                                                empSalElmSerial,
                                                                                                IMerConstant.CHILD_RAISE_TYPE);

    }


    public void returnRaiseDecision(Object params) {
        System.out.println("returned from add dssss");
        restoreSaveStateParams(params);
        filterByCivilId();
        if (params != null) {
            Map paramsMap = (Map)params;
            List list = ((List)paramsMap.get("selectedDTOS"));
            if (list != null && !list.isEmpty()) {
                IBasicDTO selectedDTO = (IBasicDTO)list.get(0);

                List<IBasicDTO> dTList = null;
                try {
                    dTList = getMyTableData();
                } catch (DataBaseException e) {
                    return;
                }
                for (IBasicDTO dto : dTList) {
                    if (dto.getCode().getKey().equals(selectedDTO.getCode().getKey())) {
                        getSelectedDTOS().clear();
                        getSelectedDTOS().add(dto);
                        break;
                    }
                }
            }
        }

        //((ISalEmpChildrenDTO)getSelectedDTOS().get(0)).setStopDate(null);
        //((ISalEmpChildrenDTO)getSelectedDTOS().get(0)).setSalStopReasonsDTO(null);

    }

    public Long successAddRaiseDecision(Object params, Object conditinsDTO, Object salEmpChildDTO, Object raiseType,
                                        ISettDecisionDTO settDecDTO) throws SharedApplicationException {
        Long empSalElmSerial = null;

        SharedUtilBean shared_util = getSharedUtil();
        try {
            empSalElmSerial =
                    ((ISalEmpChildrenClient)getClient()).makeChildRaiseDecision((IBasicDTO)conditinsDTO, (IBasicDTO)salEmpChildDTO,
                                                                                (Boolean)raiseType, settDecDTO);
            shared_util.handleSuccMsg(MER_RESOURCES, "Decision_Add_Success");
            restoreSaveStateParams(params);
            setDisableEdit(true);
            filterByCivilId();
            if ((Boolean)raiseType) {
                ((ISalEmpChildrenDTO)getSelectedDTOS().get(0)).setStatus(IMerConstant.ACTIVE);
            } else {
                ((ISalEmpChildrenDTO)getSelectedDTOS().get(0)).setStatus(IMerConstant.NOT_ACTIVE);
            }

        } catch (SharedApplicationException e) {
//            if (e instanceof DuplicateDecisionException) {
//                throw e;
//            } 
 //           else
            {
                returnRaiseDecision(params);
                shared_util.handleException(e, MER_RESOURCES, "save_unsuccessfull");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnRaiseDecision(params);
            shared_util.handleException(e, MER_RESOURCES, "save_unsuccessfull");
        }
        return empSalElmSerial;
    }

    private ISalRaiseDecisionParamsDTO fillDecisionParams(boolean isAddRaise) {


        ISalRaiseDecisionParamsDTO decisionParams = new SalRaiseDecisionParamsDTO();
        ISalEmpChildrenDTO salEmpChildrenDTO = (ISalEmpChildrenDTO)getSelectedDTOS().get(0);

        decisionParams.setEmployeesDTO(salEmpChildrenDTO.getEmployeesDTO());
        decisionParams.setFinancialDegree(getDegree());
        decisionParams.setRelName(salEmpChildrenDTO.getKwtCitizensResidentsDTO().getFullName());
        decisionParams.setRaiseValue(getChildRaiseValue(salEmpChildrenDTO));
        decisionParams.setAllowanceDate(salEmpChildrenDTO.getAllowanceDate());
        if (salEmpChildrenDTO.getStopDate() == null) {
            decisionParams.setDisableAllowanceCal(Boolean.TRUE);
        } else {
            decisionParams.setDisableAllowanceCal(Boolean.FALSE);
        }
        if (merRaiseMaintainBean.isFemaleGender()) {
            decisionParams.setMotherGrantChildrenRaise(Boolean.TRUE);
            decisionParams.setDisableCal(Boolean.FALSE);
            Date lastStopDate = null;
            try {
                if (salEmpChildrenDTO.getStopDate() != null)
                    lastStopDate = (Date)getSharedUtil().deepCopyObject(salEmpChildrenDTO.getStopDate());
            } catch (Exception e) {
                e.printStackTrace();
            }
            decisionParams.setLastStopDate(lastStopDate);
        } else {
            decisionParams.setDisableCal(Boolean.TRUE);
            if (salEmpChildrenDTO.getStopDate() != null) {
                Date lastStopDate = null;
                try {
                    if (salEmpChildrenDTO.getStopDate() != null)
                        lastStopDate = (Date)getSharedUtil().deepCopyObject(salEmpChildrenDTO.getStopDate());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                decisionParams.setLastStopDate(lastStopDate);
                decisionParams.setMotherGrantChildrenRaise(Boolean.TRUE);
                decisionParams.setDisableCal(Boolean.FALSE);
            }
        }
        Long subjectCode = null;
        if (isAddRaise) {
            decisionParams.setEndDate(null);
            decisionParams.setAddRaise(true);
            decisionParams.setDecisionTextKey("add_son_decision_text");
            subjectCode = IMerConstant.Raise_Grant_Decision_Code;
            //            try {
            //                subjectCode = UtilBean.getMappedCodeBySysSettingCode(IMerConstant.Raise_Grant_Decision_Code);
            //            } catch (Exception e) {
            //                // TODO: Add catch code
            //                e.printStackTrace();
            //            }
        } else {
            decisionParams.setAddRaise(false);
            decisionParams.setDecisionTextKey("suspend_son_decision_text");
            decisionParams.setDisableCal(Boolean.FALSE);
            decisionParams.setReasonElmType(IMerConstant.CHILD_RAISE_CODE);
            decisionParams.setRenderStopReasonGrd(true);
            subjectCode = IMerConstant.Raise_Stop_Decision_Code;
            //            try {
            //                subjectCode = UtilBean.getMappedCodeBySysSettingCode(IMerConstant.Raise_Stop_Decision_Code);
            //            } catch (Exception e) {
            //                // TODO: Add catch code
            //                e.printStackTrace();
            //            }
        }
        decisionParams.setDecSubjectCode(subjectCode);
        return decisionParams;
    }

    private ISalRaiseDecisionParamsDTO _fillDecisionParams(boolean isAddRaise) {


        ISalRaiseDecisionParamsDTO decisionParams = new SalRaiseDecisionParamsDTO();


        decisionParams.setEmployeesDTO(getEmployeesDTO());
        decisionParams.setFinancialDegree(getDegree());
        decisionParams.setMotherGrantChildrenRaise(Boolean.TRUE);
        decisionParams.setDisableCal(Boolean.FALSE);
        decisionParams.setDisableAllowanceCal(false);

        Long subjectCode = null;
        decisionParams.setEndDate(null);
        decisionParams.setAddRaise(true);
        subjectCode = IMerConstant.Raise_Grant_Decision_Code;


        decisionParams.setDecSubjectCode(subjectCode);
        return decisionParams;
    }

    private Object fillSaveStateParams() {
        Map params = new HashMap();
        params.put("pageDTO", getPageDTO());
        params.put("selectedDTOS", getSelectedDTOS());
        params.put("civilId", getCivilId());
        params.put("showSuspendPartAddDiv", showSuspendPartAddDiv);
        params.put("civilExist", isCivilExist());
        params.put("validCivilId", isValidCivilId());
        params.put("empHired", isEmpHired());
        params.put("payrollInfoExist", isPayrollInfoExist());
        params.put("degree", getDegree());
        params.put("employeesDTO", getEmployeesDTO());
        params.put("singleSelection", isSingleSelection());
        //params.put("myTableData", getPageDTO());
        params.put("highlightedDTOS", getHighlightedDTOS());
        params.put("searchMode", isSearchMode());
        params.put("fullColumnName", getFullColumnName());
        params.put("sortAscending", isSortAscending());
        params.put("divValidCivil", divValidCivil);
        params.put("divCivilExist", divCivilExist);
        params.put("hasSameParentID", hasSameParentID);
        params.put("divCivilId", divCivilId);
        params.put("selectedRadio", getSelectedRadio());


        // merRaiseMaintainBean save states
        params.put("mrmb_detailFilterMethod", merRaiseMaintainBean.getDetailFilterMethod());
        params.put("mrmb_detailResetMethod", merRaiseMaintainBean.getDetailResetMethod());
        params.put("mrmb_notDefinedGenderTypeFlag", merRaiseMaintainBean.isNotDefinedGenderTypeFlag());
        params.put("mrmb_maritalStatusSingle", merRaiseMaintainBean.isMaritalStatusSingle());
        params.put("mrmb_femaleGender", merRaiseMaintainBean.isFemaleGender());

//        // decisionMaintainBean save states
//        params.put("dmb_nextNavigationCase", decisionMaintainBean.getNextNavigationCase());
//        params.put("dmb_previousNavigationCase", decisionMaintainBean.getPreviousNavigationCase());
//        params.put("dmb_finishNavigationCase", decisionMaintainBean.getFinishNavigationCase());
//        params.put("dmb_currentNavigationCase", decisionMaintainBean.getCurrentNavigationCase());
//        params.put("dmb_currentStep", decisionMaintainBean.getCurrentStep());
//        params.put("dmb_maintainMode", decisionMaintainBean.getMaintainMode());
//        params.put("disableEdit", isDisableEdit());
        return params;
    }

    private void restoreSaveStateParams(Object obj) {
        if (obj != null) {
            try {
                Map params = (Map)obj;
                setDisableEdit((Boolean)params.get("disableEdit"));
                setPageDTO((IBasicDTO)params.get("pageDTO"));
                setSelectedDTOS((List)params.get("selectedDTOS"));
                setCivilId((String)params.get("civilId"));
                merRaiseMaintainBean.setCivilId((String)params.get("civilId"));
                setShowSuspendPartAddDiv((Boolean)params.get("showSuspendPartAddDiv"));
                setCivilExist((Boolean)params.get("civilExist"));
                merRaiseMaintainBean.setCivilExist((Boolean)params.get("civilExist"));
                setValidCivilId((Boolean)params.get("validCivilId"));
                merRaiseMaintainBean.setValidCivilId((Boolean)params.get("validCivilId"));
                setEmpHired((Boolean)params.get("empHired"));
                merRaiseMaintainBean.setEmpHired((Boolean)params.get("empHired"));
                setPayrollInfoExist((Boolean)params.get("payrollInfoExist"));
                merRaiseMaintainBean.setPayrollInfoExist((Boolean)params.get("payrollInfoExist"));
                setDegree((String)params.get("degree"));
                merRaiseMaintainBean.setDegree((String)params.get("degree"));
                setEmployeesDTO((IEmployeesDTO)params.get("employeesDTO"));
                merRaiseMaintainBean.setEmployeesDTO((IEmployeesDTO)params.get("employeesDTO"));
                setSingleSelection((Boolean)params.get("singleSelection"));
                setHighlightedDTOS((List)params.get("highlightedDTOS"));
                setSearchMode((Boolean)params.get("searchMode"));
                setFullColumnName((String)params.get("fullColumnName"));
                setSortAscending((Boolean)params.get("sortAscending"));
                setDivValidCivil((Boolean)params.get("divValidCivil"));
                setHasSameParentID((Boolean)params.get("hasSameParentID"));
                setDivCivilId((String)params.get("divCivilId"));
                setSelectedRadio((String)params.get("selectedRadio"));

                // merRaiseMaintainBean save states
                merRaiseMaintainBean.setDetailFilterMethod((String)params.get("mrmb_detailFilterMethod"));
                merRaiseMaintainBean.setDetailResetMethod((String)params.get("mrmb_detailResetMethod"));
                merRaiseMaintainBean.setNotDefinedGenderTypeFlag((Boolean)params.get("mrmb_notDefinedGenderTypeFlag"));
                merRaiseMaintainBean.setMaritalStatusSingle((Boolean)params.get("mrmb_maritalStatusSingle"));
                merRaiseMaintainBean.setFemaleGender((Boolean)params.get("mrmb_femaleGender"));

//                // decisionMaintainBean save states
//                decisionMaintainBean.setNextNavigationCase((String)params.get("dmb_nextNavigationCase"));
//                decisionMaintainBean.setPreviousNavigationCase((String)params.get("dmb_previousNavigationCase"));
//                decisionMaintainBean.setFinishNavigationCase((String)params.get("dmb_finishNavigationCase"));
//                decisionMaintainBean.setCurrentNavigationCase((String)params.get("dmb_currentNavigationCase"));
//                decisionMaintainBean.setCurrentStep((String)params.get("dmb_currentStep"));
//                decisionMaintainBean.setMaintainMode((Integer)params.get("dmb_maintainMode"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private Float getChildRaiseValue(ISalEmpChildrenDTO salEmpChildrenDTO) {
        ISalElementGuidesDTO salElementGuidesDTO = null;
        Long capStatusCode = salEmpChildrenDTO.getKwtCitizensResidentsDTO().getCapstatusCode();
        //        Long elmGuidwCode = 0L;
        //        if (IMerConstant.HEALTH_CHILDREN_DEGREE.equals(capStatusCode) || capStatusCode.equals(1L))
        //            elmGuidwCode = IMerConstant.HEALTH_CHILDREN_DEGREE_ONECHILD;
        //        else if (IMerConstant.HANDI_CAPPED_CHILDREN_DEGREE1.equals(capStatusCode))
        //            elmGuidwCode = IMerConstant.HANDI_CAPPED_CHILDREN_DEGREE1_ONECHILD;
        //        else if (IMerConstant.HANDI_CAPPED_CHILDREN_DEGREE2.equals(capStatusCode))
        //            elmGuidwCode = IMerConstant.HANDI_CAPPED_CHILDREN_DEGREE2_ONECHILD;
        //        else if (IMerConstant.HANDI_CAPPED_CHILDREN_DEGREE3.equals(capStatusCode))
        //            elmGuidwCode = IMerConstant.HANDI_CAPPED_CHILDREN_DEGREE3_ONECHILD;

        try {
            //salElementGuidesDTO = (ISalElementGuidesDTO)
            Float value =
                SalClientFactory.getSalElementGuidesClient().getChildRaiseValueByHandiCapState(capStatusCode); //elmGuidwCode);
            return value;
        } catch (DataBaseException dbe) {
            // TODO: Add catch code
            dbe.printStackTrace();
        } catch (SharedApplicationException sae) {
            // TODO: Add catch code
            sae.printStackTrace();
        }
        //        if (salElementGuidesDTO != null && salElementGuidesDTO.getValue() != null) {
        //            return salElementGuidesDTO.getValue();
        //        }

        return null;

    }

    //    public void initializeDecisionDTO() {
    //        //DecisionMaintainBean decisionMaintain = (DecisionMaintainBean)evaluateValueBinding("decisionMaintainBean");
    //        RaiseKidsAddDecisionBean raiseKidsAddDecisionBean = (RaiseKidsAddDecisionBean)evaluateValueBinding("raiseKidsAddDecisionBean");
    //
    //        if (raiseKidsAddDecisionBean.getPageDTO() != null) {
    //            DecisionMainDataMaintain decisionMainData =
    //                (DecisionMainDataMaintain)evaluateValueBinding("decisionMainDataMaintainBean");
    //            DecisionEmployeesMaintain decisionEmployeesMaintain =
    //                (DecisionEmployeesMaintain)evaluateValueBinding("decisionEmployeesMaintainBean");
    //            decisionEmployeesMaintain.setShowBarMainData(false);
    //            ((IDecisionsDTO)raiseKidsAddDecisionBean.getPageDTO()).setSubjectsDTOKey(IRegConstants.REGULATION_SUBJECT_APPOINTMENT_DECISION.toString()); //TODO
    //            decisionMainData.setShowLovDivFlag(true);
    //            try {
    //                decisionMainData.setTypesDTOKey(getManagedConstantsBean().getDecisionAdministrationType());
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //    }

    public String empAddDecisoin() {
//        if (getIntegrationBean().getSelectedDTOTo() != null && getIntegrationBean().getSelectedDTOTo().size() > 0) {
//            try {
//                RegClientFactory.getDecisionsClient().add(getIntegrationBean().getSelectedDTOTo().get(0));
//                getSharedUtil().handleSuccMsg(MER_RESOURCES, "Decision_Add_Success");
//            } catch (Exception e) {
//                getSharedUtil().handleException(e, MER_RESOURCES, "Decision_Add_Failure");
//            } // HashMap map = getIntegrationBean ( ) .getHmBaseBeanFrom ( ) ;
//            // if ( map != null ) {
//            // raiseKidsBean raiseKidsBean =
//            // ( raiseKidsBean ) map.get ( "raiseKidsBean" ) ;
//            // if ( raiseKidsBean != null ) {
//            // merRaiseMaintainBean.setCivilId ( raiseKidsBean.getCivilId ( ) ) ;
//            // merRaiseMaintainBean.filterByCivilId ( ) ;
//            //
//            // }
//            // }
//        }
        return "raiseKidsNav";
    }

    public void saveSelectedDecision() {
//        System.out.println("saveSelectedDecision");
//        if (decisionIntegration.decisionsDTO != null && decisionIntegration.decisionsDTO.getCode() != null &&
//            merRaiseMaintainBean.getEmployeesDTO() != null &&
//            merRaiseMaintainBean.getEmployeesDTO().getCode() != null) {
//            try {
//                List empDecisionList = new ArrayList();
//                IEmpDecisionsDTO empDecisionsDTO = RegDTOFactory.createEmpDecisionsDTO();
//                empDecisionsDTO.setEmployeesDTO(merRaiseMaintainBean.getEmployeesDTO());
//                empDecisionsDTO.setDecisionsDTO(decisionIntegration.decisionsDTO);
//                empDecisionList.add(empDecisionsDTO);
//                ResultDTO resultDTO;
//                resultDTO =
//                        (ResultDTO)(((List)RegClientFactory.getEmpDecisionsClient().joinToDecision(empDecisionList)).get(0));
//                if (resultDTO != null && resultDTO.getStatus().equals(ISystemConstant.ITEM_ADDED)) {
//                    getSharedUtil().handleSuccMsg(MER_RESOURCES, "Decision_Join_Success");
//                } else if (resultDTO != null && resultDTO.getStatus().equals(ISystemConstant.ITEM_ALRAEDY_EXIST)) {
//                    getSharedUtil().handleException(new Exception(), MER_RESOURCES, "Decision_Already_Joined");
//                } else {
//                    getSharedUtil().handleException(new Exception(), MER_RESOURCES, "Decision_Join_Failure");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else
//            getSharedUtil().handleException(new Exception(), MER_RESOURCES, "Decision_Join_Failure");
    }

    public void setMerRaiseMaintainBean(MerRaiseMaintainBean merRaiseMaintainBean) {
        this.merRaiseMaintainBean = merRaiseMaintainBean;
    }

    public MerRaiseMaintainBean getMerRaiseMaintainBean() {
        return merRaiseMaintainBean;
    }

    public void setDecisionIntegration(DecisionJoinBean decisionIntegration) {
        this.decisionIntegration = decisionIntegration;
    }

    public DecisionJoinBean getDecisionIntegration() {
        return decisionIntegration;
    }

    //    public void setSalEmpSalaryElementsDTOHandicapped(ISalEmpSalaryElementsDTO salEmpSalaryElementsDTOHandicapped) {
    //        this.salEmpSalaryElementsDTOHandicapped = salEmpSalaryElementsDTOHandicapped;
    //    }
    //
    //    public ISalEmpSalaryElementsDTO getSalEmpSalaryElementsDTOHandicapped() {
    //        return salEmpSalaryElementsDTOHandicapped;
    //    }

    public void setHandicapStatusList(List<IBasicDTO> handicapStatusList) {
        this.handicapStatusList = handicapStatusList;
    }

    public List<IBasicDTO> getHandicapStatusList() {
        return handicapStatusList;
    }

    public void setHandicapstatusCode(Long handicapstatusCode) {
        this.handicapstatusCode = handicapstatusCode;
    }

    public Long getHandicapstatusCode() {
        return handicapstatusCode;
    }

//    public void setDecisionMaintainBean(DecisionMaintainBean decisionMaintainBean) {
//        this.decisionMaintainBean = decisionMaintainBean;
//    }
//
//    public DecisionMaintainBean getDecisionMaintainBean() {
//        return decisionMaintainBean;
//    }
//
//    public void setDecisionMainDataMaintainBean(DecisionMainDataMaintain decisionMainDataMaintainBean) {
//        this.decisionMainDataMaintainBean = decisionMainDataMaintainBean;
//    }
//
//    public DecisionMainDataMaintain getDecisionMainDataMaintainBean() {
//        return decisionMainDataMaintainBean;
//    }

    public boolean isNotValidChild() {

        if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty() &&
            ((ISalEmpChildrenDTO)getSelectedDTOS().get(0)).getStatus().equals(IMerConstant.NOT_ACTIVE)) {
            return false;
        }
        return true;
    }

    public void search() throws DataBaseException, NoResultException {
        this.setSearchMode(true);
        setSelectedDTOS(new ArrayList());
        setSelectedRadio("");
        List<SalEmpChildrenDTO> list = getTotalList();
        List<SalEmpChildrenDTO> searchList = new ArrayList();

        setSearchMode(true);
        String name;
        Long civil;
        Long civilsearchquery;
        if (searchTyp != null && searchTyp.equals("0")) {
            for (SalEmpChildrenDTO dto : list) {
                civil = ((SalEmpChildrenEntityKey)dto.getCode()).getChildCivilId();
                civilsearchquery = Long.parseLong(getSearchQuery());
                if (civil.equals(civilsearchquery))
                    searchList.add((SalEmpChildrenDTO)dto);
                System.out.println(getSearchQuery() + "    " +
                                   ((SalEmpChildrenEntityKey)dto.getCode()).getChildCivilId());

            }
        } else if (searchTyp != null && searchTyp.equals("1")) {
            for (SalEmpChildrenDTO dto : list) {
                name = ((SalEmpChildrenDTO)dto).getKwtCitizensResidentsDTO().getFirstName();

                if (name.contains(getSearchQuery()))
                    searchList.add((SalEmpChildrenDTO)dto);
            }


        }
        setMyTableData(searchList);
        //        try {
        //
        //            if (searchTyp != null && searchTyp.equals("0"))
        //                super.setSearchEntityObj(new Long(this.getSearchQuery()));
        //            super.search();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            setSearchMode(true);
        //            setMyTableData(new ArrayList());
        //        }
        reinitializeSort();
        setDisableEdit(true);
    }

    public List getBaseSearchResult() throws DataBaseException {

        List searchResult = new ArrayList();

        try {
            selMother = getVirtualConstValue();
            if (searchTyp != null && searchTyp.equals("0")) {
                searchResult = getClient().searchByCode(getSearchEntityObj());
            } else if (searchTyp != null && searchTyp.equals("1")) {
                searchResult =
                        ((ISalEmpChildrenClient)getClient()).searchByName(formatSearchString(getSearchQuery()), Long.parseLong(getCivilId()));
            }
        } catch (ItemNotFoundException e) { //this means no search results found
            setMyTableData(new ArrayList());
        } catch (NoResultException ne) { //this means no search results found
            setMyTableData(new ArrayList());
        } catch (Exception db) {

            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());

            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);
            throw new DataBaseException(db.getMessage());

        }

        return searchResult;
    }

    public void backSearchDiv() throws DataBaseException {
        System.out.println("Calling cancelSearch()...");
        // setDisableEdit(true);
        this.setSearchQuery("");
        this.setSearchTyp("0");
    }

    public void cancelSearch() {
        try {
            super.cancelSearch();
        } catch (DataBaseException e) {
        }
        setDisableEdit(true);

    }

    public void preSreach() {

        System.out.println("preSreach()");
        this.setSearchTyp("0");
        setPageMode(PAGE_MODE_SEARCH);
        //   setDisableEdit(true);
        // setSelectedRadio("");

    }

    public void setSearchTyp(String searchTyp) {
        this.searchTyp = searchTyp;
    }

    public String getSearchTyp() {
        return searchTyp;
    }

    public void setSelMother(String selMother) {
        this.selMother = selMother;
    }

    public String getSelMother() {
        return selMother;
    }

    public void setEmpWives(List empWives) {
        this.empWives = empWives;
    }

    public List getEmpWives() {
        return empWives;
    }

    public void setNewChildMother(String newChildMother) {
        this.newChildMother = newChildMother;
    }

    public String getNewChildMother() {
        return newChildMother;
    }

    public void setDisableEdit(boolean enableEdit) {
        this.disableEdit = enableEdit;
    }

    public boolean isDisableEdit() {
        return disableEdit;
    }

    public void setHandicapstatusCodeEdit(Long handicapstatusCodeEdit) {
        this.handicapstatusCodeEdit = handicapstatusCodeEdit;
    }

    public Long getHandicapstatusCodeEdit() {
        return handicapstatusCodeEdit;
    }

    public void setNewChildMotherForEdit(String newChildMotherForEdit) {
        this.newChildMotherForEdit = newChildMotherForEdit;
    }

    public String getNewChildMotherForEdit() {
        return newChildMotherForEdit;
    }
    
    public static RaiseKidsBean getInstance() {
        return (RaiseKidsBean)JSFHelper.getValue("raiseKidsBean");
    }
}
