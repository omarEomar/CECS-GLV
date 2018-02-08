package com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle;


//import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionListBean;
//import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionExcute.DecisionExcuteListBean;
//import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionRevision.DecisionRevisionListBean;

//import com.beshara.csc.nl.reg.presentation.backingbean.decision.DecisionListBean;
//import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionExcute.DecisionExcuteListBean;
//import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionRevision.DecisionRevisionListBean;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.shared.SharedUtils;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.IRegulationStatusClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationStatusDTO;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.business.exception.DecionNumberYearTypeAlreadyAddedBefor;
import com.beshara.csc.nl.reg.business.sharedUtil.IConstants;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.SettlementDecisionsListBean;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.details.DecisionCycleEmployeesMaintain;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.details.DecisionCycleMainDataMaintain;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.constants.IRegConstants;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;


public class DecisionCycleMaintainBean extends ManyToManyMaintainBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private static final String BUNDLE = "com.beshara.csc.nl.reg.presentation.resources.reg";
    private static final String BEAN_NAME = "settlementDecisionCycleMaintainBean";
    private static final String INTEGRATION_PAGE_SUFFIX = "-integration";
    private Map<String, Object> detailsSavedStates;
    String finishNavigationCase = null;
    private boolean cancelDecisionMode;
    private boolean copyDecisionWithEmployeesMode = false;
    //    private boolean _reviewMode = false;
    private String lisBeanName = null;
    private String backMethodName = null;
    private String backNavCase = null;

    private Long currentStag = 1L;
    private boolean showOnly = false;
    private boolean dataLoadedBefore;

    SharedUtilBean sharedUtil = getSharedUtil();

    private Object[] savedDataObjects = new Object[1];
    

    public DecisionCycleMaintainBean() {
        //TODO change clientName
        super.setClient(RegClientFactory.getDecisionsClient());


        setCurrentStep("settlementdecisionadd");

        setNextNavigationCase("settlementdecisioncycleemployeesmaintain");
        setFinishNavigationCase("settlementdecisionslist");
        //            setFinishNavigationCase("decisionlist");

    }

    public static DecisionCycleMaintainBean getInstance() {
        return (DecisionCycleMaintainBean)JSFHelper.getValue(BEAN_NAME);
    }

    public static boolean isIntegrationPage() {
        try {
            String url =
                ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString();
            return url != null && url.contains(INTEGRATION_PAGE_SUFFIX);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getNavigationCase(String mapKey) {
        String nc = super.getNavigationCase(mapKey);
        if (isCancelDecisionMode()) {
            //nc += "cancel";
        }
        if (getMaintainMode() == 1) {
            if (isIntegrationPage()) {
                nc = nc.substring(0, nc.indexOf("-")) + "edit" + INTEGRATION_PAGE_SUFFIX;
            } else {
                nc += "edit";
            }
        }


        return nc;
    }

    public boolean isRenderFinish() {
        //        if (copyDecisionWithEmployeesMode)
        //            return true;
        //        else
        return true;
    }

    public boolean isCancelDecisionMode() {

        return false;
        //
        //        if (getPageDTO() != null &&
        //            (((IDecisionsDTO)getPageDTO()).getDecisionsDTOList() != null && ((IDecisionsDTO)getPageDTO()).getDecisionsDTOList().size() !=
        //             0)) {
        //
        //            DecisionListBean decisionListBean = (DecisionListBean)evaluateValueBinding("decisionListBean");
        //
        //            if (decisionListBean.isCancelDescisionFlag() == false) {
        //
        //                setCurrentStep("financedecisionadd");
        //                setNextNavigationCase("decisionemployeesmaintain");
        //            } else if (decisionListBean.isCancelDescisionFlag() == true && cancelDecisionMode == false) {
        //                setCurrentStep("canceldecisionadd");
        //                setNextNavigationCase("decisionreferencesmaintain");
        //                setFinishNavigationCase("decisionlist");
        //                cancelDecisionMode = true;
        //            }
        //
        //            //                if (getCurrentStep().equals("canceldecisionadd")){
        //            //                    if(isIntegrationPage()){
        //            //                        setNextNavigationCase("decisionreferencesmaintain"+INTEGRATION_PAGE_SUFFIX);
        //            //                    }else{
        //            //                        setNextNavigationCase("decisionreferencesmaintain");
        //            //                    }
        //            //                    cancelDecisionMode = true;
        //            //                }
        //
        //            //                if (!getCurrentStep().equals("cancelreferencesadd")){
        //            //                    setCurrentStep("canceldecisionadd");
        //            //                    if(isIntegrationPage()){
        //            //                        setNextNavigationCase("decisionreferencesmaintain"+INTEGRATION_PAGE_SUFFIX);
        //            //                    }else{
        //            //                        setNextNavigationCase("decisionreferencesmaintain");
        //            //                    }
        //            //                    cancelDecisionMode = true;
        //            //                } else{
        //            //                    setNextNavigationCase(null);
        //            //                    if(isIntegrationPage()){
        //            //                        setPreviousNavigationCase("decisionmaindatacancel"+INTEGRATION_PAGE_SUFFIX);
        //            //                    }else{
        //            //                        setPreviousNavigationCase("decisionmaindatacancel");
        //            //                    }
        //            //                }
        //
        //        }
        //
        //        return cancelDecisionMode;
    }


    public boolean isAddDecisionMode() {
        //        return getMaintainMode() == 0 && !isCancelDecisionMode();
        return true;
    }

    public IntegrationBean getIntegrationBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (IntegrationBean)app.createValueBinding("#{integrationBean}").getValue(ctx);
    }

    public String finish() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        DecisionCycleEmployeesMaintain decisionCycleEmployeesMaintain =
            (DecisionCycleEmployeesMaintain)evaluateValueBinding("settlementDecisionCycleEmployeesMaintainBean  ");
        if (getPageDTO().getCode() == null) {
            getPageDTO().setCode(RegEntityKeyFactory.createDecisionsEntityKey());
        }

        List empDecisionsDTOList = decisionCycleEmployeesMaintain.getCurrentDetails();
        IDecisionsDTO dtoForSave = (IDecisionsDTO)getPageDTO();
        if (getMaintainMode() == this.MAINTAIN_MODE_ADD) {
            //List<IBasicDTO> empDecisionsDTOList = new ArrayList<IBasicDTO>();
            List<IBasicDTO> salElementGuideList ;
            for(IEmpDecisionsDTO empDecisionDTO : (List<IEmpDecisionsDTO>) empDecisionsDTOList ){
//                salElementGuideList = new ArrayList<IBasicDTO>();
//                salElementGuideList.addAll(decisionCycleEmployeesMaintain.getBenfitsList());
//                salElementGuideList.addAll(decisionCycleEmployeesMaintain.getDeductionsList());
//                dtoForSave.setSalElmGuidesDTOList(salElementGuideList); // will be commented
//                dtoForSave.setYearCode(decisionCycleEmployeesMaintain.getSettYearsKey());
//                dtoForSave.setYearMonth(decisionCycleEmployeesMaintain.getSettMonthKey());
                //create empDecisionsDTOList and set in dtoForSave
                //IEmpDecisionsDTO empDecisionDTO = RegDTOFactory.createEmpDecisionsDTO();
                Long realCivilId=((IEmployeesDTO)empDecisionDTO.getEmployeesDTO()).getRealCivilId();
                IEntityKey iEntityKey =
                    RegEntityKeyFactory.createEmpDecisionsEntityKey(dtoForSave.getDectypeCode(), dtoForSave.getDecyearCode(),
                                                                    dtoForSave.getDecisionNumber(),realCivilId);
                empDecisionDTO.setCode(iEntityKey);
               // empDecisionDTO.setEmployeesDTO(decisionCycleEmployeesMaintain.getEmployeesDTO()); 
                //empDecisionDTO.setSalElmGuideDTOList(salElementGuideList);
                //empDecisionsDTOList.add(empDecisionDTO);
            }
            dtoForSave.setEmpDecisionsDTOList((List<IBasicDTO>)empDecisionsDTOList);
            setPageDTO(dtoForSave);
        } else if (getMaintainMode() == this.MAINTAIN_MODE_EDIT) {


            for(IEmpDecisionsDTO empDecisionDTO : (List<IEmpDecisionsDTO>) empDecisionsDTOList){
                if (empDecisionDTO.getStatusFlag() != null && empDecisionDTO.getStatusFlag().equals(0L)) {
                   
                    Long realCivilId = ((IEmployeesDTO)empDecisionDTO.getEmployeesDTO()).getRealCivilId();
                    IEntityKey iEntityKey =
                        RegEntityKeyFactory.createEmpDecisionsEntityKey(dtoForSave.getDectypeCode(),
                                                                        dtoForSave.getDecyearCode(),
                                                                        dtoForSave.getDecisionNumber(), realCivilId);
                    empDecisionDTO.setCode(iEntityKey);
                }
                
            }
           /* List<IBasicDTO> salElementGuideList = new ArrayList<IBasicDTO>();
            decisionCycleEmployeesMaintain.updateStatusBeforeModification();
            salElementGuideList.addAll(decisionCycleEmployeesMaintain.getBenfitsList());
            salElementGuideList.addAll(decisionCycleEmployeesMaintain.getDeductionsList());
            dtoForSave.setSalElmGuidesDTOList(salElementGuideList);
            dtoForSave.setYearCode(decisionCycleEmployeesMaintain.getSettYearsKey());
            dtoForSave.setYearMonth(decisionCycleEmployeesMaintain.getSettMonthKey());*/
            /*//create empDecisionsDTOList and set in dtoForSave
            IEmpDecisionsDTO empDecisionDTO = RegDTOFactory.createEmpDecisionsDTO();
            IEntityKey iEntityKey =
                RegEntityKeyFactory.createEmpDecisionsEntityKey(dtoForSave.getDectypeCode(), dtoForSave.getDecyearCode(),
                                                                dtoForSave.getDecisionNumber(),
                                                                decisionCycleEmployeesMaintain.getCivilID());
            empDecisionDTO.setCode(iEntityKey);
            empDecisionDTO.setEmployeesDTO(decisionCycleEmployeesMaintain.getEmployeesDTO());
            List<IBasicDTO> empDecisionsDTOList = new ArrayList<IBasicDTO>();
            empDecisionsDTOList.add(empDecisionDTO);
            dtoForSave.setEmpDecisionsDTOList(empDecisionsDTOList);*/
            setPageDTO(dtoForSave);
        }

        return defaultAddDecisoin();


    }


    public float calcValue(Integer denar, Integer fels) {
        Float calculatedValue = null;
        String value = "";
        fels = fels != null ? fels : 0;
        if (denar != null && fels != null) {
            value = denar + "." + fels;
        }
        calculatedValue = Float.parseFloat(value);
        return calculatedValue;
    }

    public String partiallySave() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        IDecisionsDTO decisionsDTO = (IDecisionsDTO)getPageDTO();
        decisionsDTO.setRegStatusFlage(IRegConstants.REGULATION_STATUS_SUGGESTION);
        ((IDecisionsDTO)getPageDTO()).setCurrentStage(IConstants.REG_SUGGESTION_STAGE);
        return finish();
    }

    public String totallySave() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        ((IDecisionsDTO)getPageDTO()).setCurrentStage(IConstants.REG_SUGGESTION_STAGE);
        IDecisionsDTO decisionsDTO = (IDecisionsDTO)getPageDTO();
        decisionsDTO.setRegStatusFlage(IConstants.REG_SUGGESTED_STATUS);
        IRegulationStatusClient regulationStatusClient = RegClientFactory.getRegulationStatusClient();
        IRegulationStatusDTO regStatusDTO =
            (IRegulationStatusDTO)regulationStatusClient.getById(RegEntityKeyFactory.createRegulationStatusEntityKey(IRegConstants.REGULATION_STATUS_READY_REVISION));
        decisionsDTO.setRegStatusDTO(regStatusDTO);
        decisionsDTO.setUserCodeSugg(CSCSecurityInfoHelper.getUserCode());
        decisionsDTO.setSuggDate(SharedUtils.getSqlDate());
        return finish();
    }

    public String defaultAddDecisoin() throws DataBaseException, ItemNotFoundException, SharedApplicationException {


        ((IDecisionsDTO)getPageDTO()).setMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        //        DecisionEmployeesModel decisionEmployeesModelSessionBean =
        //            (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");
        //        finishNavigationCase = null;
        String currentstepId = getCurrentStep();
        try {
            if (getFinishButtonOverride(currentstepId) == 1) {
                setRenderFinish(getFinishButtonStatus(currentstepId));
            } else if (getFinishButtonOverride(currentstepId) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }

            //   decisionEmployeesModelSessionBean.sendListtoBussiens();
            if (validateStep(currentstepId, null)) {
                if (this.getMaintainMode() == this.MAINTAIN_MODE_ADD) {


                    this.add();
                    if (decNumYearAdded == 1) {
                        return "settlementdecisionslist";
                    }

                } else if (this.getMaintainMode() == this.MAINTAIN_MODE_EDIT) {

                    this.edit();
                    //   setPageDTO(super.getClient().getById(getPageDTO().getCode()));
                }

                updateStepData(currentstepId);

            } else
                return null;

        }
        //        catch(DecionNumberYearTypeAlreadyAddedBefor d){
        //            d.printStackTrace();
        //            DecisionCycleMainDataMaintain decisionCycleMainDataMaintain = DecisionCycleMainDataMaintain.getInstance();
        //            decisionCycleMainDataMaintain.getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,"DecNumberYearTypeAlreadyAddedBefor"));
        //            return null;
        //        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }


        if (getFinishNavigationCase() != null && !isShowErrorMsg()) {


            //  if (maintainBean.getCurrentStag() == 1) {

            //TODO change to new list bean name
            BaseBean baseBean = (BaseBean)JSFHelper.getValue(lisBeanName);

            if (lisBeanName != null && backMethodName != null) {
                Object[] paramsArray = new Object[1];
                paramsArray[0] = detailsSavedStates;

                String method = lisBeanName + "." + backMethodName;
                executeMethodBindingWithParams(method, paramsArray, new Class[] { Map.class });
            }

            //TODO change to new list bean name
            highlighDataTable(lisBeanName);
            if (this.getMaintainMode() == this.MAINTAIN_MODE_ADD)
                baseBean.getSharedUtil().setSuccessMsgValue("SuccessAdds");
            else if (this.getMaintainMode() == this.MAINTAIN_MODE_EDIT)
                baseBean.getSharedUtil().setSuccessMsgValue("SuccesUpdated");

        }
        if (backNavCase != null)
            return backNavCase;

        return null;
    }

    public void edit() throws DataBaseException {
        // handleDecisionImage();
        SharedUtilBean sharedUtil = getSharedUtil();
        DecisionCycleEmployeesMaintain decisionCycleEmployeesMaintain =
            (DecisionCycleEmployeesMaintain)evaluateValueBinding("settlementDecisionCycleEmployeesMaintainBean  ");
        try {
            /**updated by A.Nour to call removeAllPreviousSettlements from DecisionsSessionBean for bussiness log issue*/
//            ((IDecisionsClient)getClient()).removeAllPreviousSettlements(getPageDTO());
            setPageDTO(((IDecisionsClient)getClient()).addNewSettelmentsAfterRemoveOldForEmpList(getPageDTO(), true));
            
            SettlementDecisionsListBean settlementDecisionsListBean  = 
                (SettlementDecisionsListBean)evaluateValueBinding("settlementDecisionsListBean");
//            settlementDecisionsListBean.getHighlightedDTOS().clear();
//            settlementDecisionsListBean.getHighlightedDTOS().add(getPageDTO());
                       
            if (settlementDecisionsListBean.isUsingPaging()) {

                settlementDecisionsListBean.setUpdateMyPagedDataModel(true);
                settlementDecisionsListBean.setRepositionTable(true);
                settlementDecisionsListBean.getPagingBean().clearDTOS();
                if (settlementDecisionsListBean.getPagingRequestDTO() == null) {
                    settlementDecisionsListBean.setPagingRequestDTO(new PagingRequestDTO());
                }
                settlementDecisionsListBean.generatePagingRequestDTO((String)getPageDTO().getCode().getKey());

            } else {
                settlementDecisionsListBean.getAll();
                settlementDecisionsListBean.getPageIndex((String)getPageDTO().getCode().getKey());
            }

            settlementDecisionsListBean.setSearchQuery("");
            settlementDecisionsListBean.setSearchType(0);
            settlementDecisionsListBean.setSearchMode(false);
            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            settlementDecisionsListBean.getSelectedDTOS().clear();

            settlementDecisionsListBean.getPagingBean().cancelSearch();
            settlementDecisionsListBean.setSorting(true);
            //            financialDecisionsListBean.setBsnSortingColumnName("o.decyearCode");
            settlementDecisionsListBean.setRepositionTable(true);
            settlementDecisionsListBean.setSortAscending(false);
            settlementDecisionsListBean.setSearchMode(false);
            settlementDecisionsListBean.setRecordAdded(true);

            IDecisionsDTO decisionsDTO = (IDecisionsDTO)getPageDTO();
            settlementDecisionsListBean.setSelectedRepTemplateKey(settlementDecisionsListBean.RECOMMENDED_DEC_WITH_DATE+ "");
            settlementDecisionsListBean.getSelectedDTOS().clear();
            settlementDecisionsListBean.getSelectedDTOS().add(decisionsDTO) ;
            settlementDecisionsListBean.loadReportTemplates();
            decisionsDTO.setDecisionAppliedDate(decisionsDTO.getDecisionDate());
            settlementDecisionsListBean.openReport(decisionsDTO);



            Long rowNum = 0L;
            try {

                rowNum = getClient().getRowNum((IDecisionsDTO)getPageDTO());
            } catch (DataBaseException dbe) {
                // TODO: Add catch code
                dbe.printStackTrace();
            } catch (SharedApplicationException sae) {
                // TODO: Add catch code
                sae.printStackTrace();
            }

            settlementDecisionsListBean.setCurrentPageIndex(rowNum.intValue());
            

        } catch (DataBaseException e) {
            sharedUtil.handleException(e);
        } catch (ItemNotFoundException item) {
            sharedUtil.handleException(item);
        } catch (SharedApplicationException e) {
            sharedUtil.handleException(e);
        } catch (Exception e) {
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
        }
        //added by nora to reset radio if list has radio column
        setSelectedRadio("");
    }


    public String back() {
        if (getIntegrationBean() != null && getIntegrationBean().getModuleFrom() != null &&
            !getIntegrationBean().getModuleFrom().equals("")) {
            return getIntegrationBean().cancelGoTO();
        }
        //   DecisionEmployeesModel decisionEmployeesModelSessionBean =
        //      (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");
        //   decisionEmployeesModelSessionBean.resetSessionData();
        String finishNavigationCase = super.back();

        if (finishNavigationCase != null) {
            // DecisionCycleMaintainBean maintainBean =
            //   (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{settlementDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
            //            FacesContext ctx = FacesContext.getCurrentInstance();
            //            Application app = ctx.getApplication();
            //            SettlementDecisionsListBean settlementDecisionsListBean =
            //                (SettlementDecisionsListBean)app.createValueBinding("#{settlementDecisionsListBean}").getValue(ctx);
            //            settlementDecisionsListBean.restoreSaveStatesMap(detailsSavedStates);
            //            //settlementDecisionsListBean.setSearchMode(true);
            //            // decisionListBean.setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
            //            //  decisionListBean.setStringSearchType("false");
            //            settlementDecisionsListBean.setSelectedRadio("");
            //            settlementDecisionsListBean.resetPageIndex();
            //            //settlementDecisionsListBean.unCheck();
            //            if (settlementDecisionsListBean.getSelectedDTOS() != null)
            //                settlementDecisionsListBean.getSelectedDTOS().clear();
            //            if (settlementDecisionsListBean.getHighlightedDTOS() != null)
            //                settlementDecisionsListBean.getHighlightedDTOS().clear();
            //            settlementDecisionsListBean.repositionPage(0);
            //            //  decisionListBean.setStringSearchType("false");
            //            settlementDecisionsListBean.setSelectedRadio("");
            //                    decisionListBean.getGetAllDTO().setCurrentStage(3L);
            //                    decisionListBean.getGetAllDTO().setRegStatusFlage(3L);
            //                    decisionListBean.getGetAllDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());

            //            try {
            //                settlementDecisionsListBean.getAll();
            //            } catch (DataBaseException e) {
            //                // TODO
            //            }

            if (lisBeanName != null && backMethodName != null) {
                Object[] paramsArray = new Object[1];
                paramsArray[0] = detailsSavedStates;

                String method = lisBeanName + "." + backMethodName;
                executeMethodBindingWithParams(method, paramsArray, new Class[] { Map.class });
            }
            //            if (maintainBean.getCurrentStag() == 2) {
            //                DecisionRevisionListBean decisionListBean =
            //                    (DecisionRevisionListBean)app.createValueBinding("#{decisionRevisionListBean}").getValue(ctx);
            //                if (!decisionListBean.isShowAllFlag()) {
            //                    decisionListBean.setSearchMode(true);
            //                    decisionListBean.getDecisionSearchDTO().setCurrentStage(2L);
            //                    decisionListBean.getDecisionSearchDTO().setRegStatusFlage(0L);
            //                    try {
            //                        if (decisionListBean.getDecisionSearchDTO() != null) {
            //                            if (decisionListBean.getDecisionSearchDTO().getName() != null) {
            //                                if (!(decisionListBean.getDecisionSearchDTO().getName().equals(""))) {
            //                                    decisionListBean.getDecisionSearchDTO().setName(formatSearchString(decisionListBean.getDecisionSearchDTO().getName()));
            //                                }
            //                            }
            //
            //                            decisionListBean.generatePagingRequestDTO("decisionRevisionListBean", "searchWithPaging");
            //                            decisionListBean.setStringSearchType("false");
            //                            decisionListBean.setSelectedRadio("");
            //                        }
            //                    } catch (Exception e) {
            //                        e.printStackTrace();
            //                    }
            //                } else {
            //                    decisionListBean.setSearchMode(true);
            //                    decisionListBean.setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
            //                    decisionListBean.setStringSearchType("false");
            //                    decisionListBean.setSelectedRadio("");
            //                    decisionListBean.getGetAllDTO().setCurrentStage(2L);
            //                    decisionListBean.getGetAllDTO().setRegStatusFlage(0L);
            //                    try {
            //                        decisionListBean.getAll();
            //                    } catch (DataBaseException e) {
            //                        // TODO
            //                    }
            //                }
            //                decisionListBean.resetPageIndex();
            //                decisionListBean.unCheck();
            //                if (decisionListBean.getSelectedDTOS() != null)
            //                    decisionListBean.getSelectedDTOS().clear();
            //                if (decisionListBean.getHighlightedDTOS() != null)
            //                    decisionListBean.getHighlightedDTOS().clear();
            //                decisionListBean.repositionPage(0);
            //                decisionListBean.setStringSearchType("false");
            //                decisionListBean.setSelectedRadio("");
            //            }
            //            if (maintainBean.getCurrentStag() == 3) {


            //                if (!decisionListBean.isShowAllFlag()) {
            //                    decisionListBean.setSearchMode(true);
            //                    decisionListBean.getDecisionSearchDTO().setCurrentStage(3L);
            //                    decisionListBean.getDecisionSearchDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
            //                    decisionListBean.getDecisionSearchDTO().setRegStatusFlage(3L);
            //
            //                    try {
            //                        if (decisionListBean.getDecisionSearchDTO() != null) {
            //                            if (decisionListBean.getDecisionSearchDTO().getName() != null) {
            //                                if (!(decisionListBean.getDecisionSearchDTO().getName().equals(""))) {
            //                                    decisionListBean.getDecisionSearchDTO().setName(formatSearchString(decisionListBean.getDecisionSearchDTO().getName()));
            //                                }
            //                            }
            //                            if (decisionListBean.isUsingPaging()) {
            //                                decisionListBean.generatePagingRequestDTO("decisionExcuteListBean",
            //                                                                          "searchWithPaging");
            //                                decisionListBean.setStringSearchType("false");
            //                                decisionListBean.setSelectedRadio("");
            //                            }
            //                        }
            //                    } catch (Exception e) {
            //                        e.printStackTrace();
            //                    }
            //
            //                } else {

        }

        //            }

        if (backNavCase != null)
            return backNavCase;

        return null;
    }
    private int decNumYearAdded = 0;

    public void add() throws DataBaseException {
        // handleDecisionImage();
        SharedUtilBean sharedUtil = getSharedUtil();
        decNumYearAdded = 0;
        try {
            setPageDTO(((IDecisionsClient)getClient()).addDecisionSettelmentForEmpList(getPageDTO(), true));
            FacesContext ctx = FacesContext.getCurrentInstance();
            Application app = ctx.getApplication();
            SettlementDecisionsListBean settlementDecisionsListBean =
                (SettlementDecisionsListBean)app.createValueBinding("#{settlementDecisionsListBean}").getValue(ctx);
            if (settlementDecisionsListBean.isUsingPaging()) {

                settlementDecisionsListBean.getPagingBean().clearDTOS();

                settlementDecisionsListBean.generatePagingRequestDTO((String)getPageDTO().getCode().getKey());

            } else {
                settlementDecisionsListBean.getAll();
                settlementDecisionsListBean.getPageIndex((String)getPageDTO().getCode().getKey());
            }

            
            settlementDecisionsListBean.setSearchQuery("");
            settlementDecisionsListBean.setSearchType(0);
            settlementDecisionsListBean.setSearchMode(false);
            sharedUtil.setSuccessMsgValue("SuccessAdds");
            settlementDecisionsListBean.getPagingBean().cancelSearch();
            settlementDecisionsListBean.setSorting(true);
            //            settlementDecisionsListBean.setBsnSortingColumnName("o.decyearCode");
            settlementDecisionsListBean.setRepositionTable(true);
            settlementDecisionsListBean.setSortAscending(false);
            settlementDecisionsListBean.setSearchMode(false);
            settlementDecisionsListBean.setRecordAdded(true);



            IDecisionsDTO decisionsDTO = (IDecisionsDTO)getPageDTO();
            settlementDecisionsListBean.setSelectedRepTemplateKey(settlementDecisionsListBean.RECOMMENDED_DEC_WITH_DATE+ "");
            settlementDecisionsListBean.getSelectedDTOS().clear();
            settlementDecisionsListBean.getSelectedDTOS().add(decisionsDTO) ;
            settlementDecisionsListBean.loadReportTemplates();
            decisionsDTO.setDecisionAppliedDate(decisionsDTO.getDecisionDate());
            settlementDecisionsListBean.openReport(decisionsDTO);



            Long rowNum = 0L;
            try {

                rowNum = getClient().getRowNum(getPageDTO());
            } catch (DataBaseException dbe) {
                // TODO: Add catch code
                dbe.printStackTrace();
            } catch (SharedApplicationException sae) {
                // TODO: Add catch code
                sae.printStackTrace();
            }
            
            settlementDecisionsListBean.setCurrentPageIndex(rowNum.intValue());
            

        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);

            //            sharedUtil.handleException(e,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInAdd");
            sharedUtil.handleException(e);
            this.setErrorMsg(sharedUtil.getErrMsgValue());
        } catch (DecionNumberYearTypeAlreadyAddedBefor e) {
            DecisionCycleMainDataMaintain decisionCycleMainDataMaintain = DecisionCycleMainDataMaintain.getInstance();
            decisionCycleMainDataMaintain.getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,
                                                                                                        "DecNumberYearTypeAlreadyAddedBefor"));
            e.printStackTrace();
            decNumYearAdded = 1;


        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg("FailureInAdd");


        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");

        }
        //added by nora to reset radio if list has radio column
        setSelectedRadio("");
    }


    //    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
    //        String decisionDoneNumber = null;
    // //       handleDecisionImage();
    //
    //
    //        try {
    //            if (getCurrentStag() != 3L) {
    //                ((IDecisionsClient)getClient()).updateDecison(getPageDTO());
    //            } else {
    //
    //                IDecisionsDTO dtoForEdit = (IDecisionsDTO)getPageDTO();
    //                dtoForEdit.setApprovalDate(SharedUtils.getSqlDate());
    //                dtoForEdit.setUserCodeApproval(CSCSecurityInfoHelper.getUserCode());
    //                decisionDoneNumber = setRegNumber(dtoForEdit);
    //                ((IDecisionsClient)getClient()).excuteDecison(dtoForEdit);
    //            }
    //            cancelSearch();
    //
    //            if (isUsingPaging()) {
    //
    //                setUpdateMyPagedDataModel(true);
    //                setRepositionTable(true);
    //                getPagingBean().clearDTOS();
    //                if (getPagingRequestDTO() == null) {
    //                    setPagingRequestDTO(new PagingRequestDTO());
    //                }
    //                getPagingRequestDTO().setRepositionKey((String)getPageDTO().getCode().getKey());
    //
    //            }
    //
    //            if (getHighlightedDTOS() != null) {
    //                getHighlightedDTOS().add(getPageDTO());
    //            }
    //
    //            if (getCurrentStag() != 3L) {
    //                sharedUtil.setSuccessMsgValue("SuccesUpdated");
    //            } else {
    //                String message =
    //                    sharedUtil.messageLocator("com.beshara.csc.nl.reg.presentation.resources.reg", "SuccesExcute");
    //                getSharedUtil().setSuccessMsgValue(message + " " + decisionDoneNumber);
    //            }
    //            this.getSelectedDTOS().clear();
    //
    //        } catch (DataBaseException e) {
    //            sharedUtil.handleException(e);
    //        } catch (ItemNotFoundException item) {
    //            sharedUtil.handleException(item);
    //        } catch (SharedApplicationException e) {
    //            sharedUtil.handleException(e);
    //        } catch (Exception e) {
    //            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
    //        }
    //        //Added By Yassmine to reset the value of radio button after Edit
    //        setSelectedRadio("");
    //    }

    public String navigateToStep(String targetStep) {

        String nCase = getNavigationCase(targetStep);
        this.updateStepDependancies(getCurrentStep());

        setNextNavigationCase(getNextNavigationCase(targetStep));
        setPreviousNavigationCase(getNavigationCase(getCurrentStep()));
        setValidated(getCurrentStep());
        setVisited(getCurrentStep());
        setVisited(targetStep);

        if (getFinishButtonOverride(targetStep) == 1) {
            setRenderFinish(getFinishButtonStatus(targetStep));
        } else {
            setRenderFinish(true);
        }


        setCurrentStep(targetStep);
        getWizardBar().setCurrentStep(targetStep);

        return nCase;

    }

    public String saveDecision() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        //handleDecisionImage();
        SharedUtilBean sharedUtil = getSharedUtil();

        //        DecisionEmployeesModel decisionEmployeesModelSessionBean =
        //            (DecisionEmployeesModel)evaluateValueBinding("specialExtradecisionEmployeesModel");

        String decisionText = ((IDecisionsDTO)getPageDTO()).getDecisionText();

        if (decisionText == null || decisionText.trim().equals("")) {
            String beanName = getWizardStep("settlementdecisionadd").getStepBeanName();
            DecisionCycleMainDataMaintain decisionCycleMainDataMaintain =
                (DecisionCycleMainDataMaintain)evaluateValueBinding(beanName);
            decisionCycleMainDataMaintain.getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE,
                                                                                                        "decision_text_error"));
            return navigateToStep("settlementdecisionadd");
        }

        try {

            //        decisionEmployeesModelSessionBean.sendListtoBussiens();

            ((IDecisionsClient)getClient()).updateDecison(getPageDTO());

            cancelSearch();

            if (isUsingPaging()) {

                setUpdateMyPagedDataModel(true);
                setRepositionTable(true);
                getPagingBean().clearDTOS();
                if (getPagingRequestDTO() == null) {
                    setPagingRequestDTO(new PagingRequestDTO());
                }
                getPagingRequestDTO().setRepositionKey((String)getPageDTO().getCode().getKey());

            }

            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().add(getPageDTO());
            }

            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            this.getSelectedDTOS().clear();

        } catch (DataBaseException e) {
            sharedUtil.handleException(e);
            //            sharedUtil.handleException(e,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInUpdate");
        } catch (ItemNotFoundException item) {
            sharedUtil.handleException(item);
            //            sharedUtil.handleException(item,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInUpdate");
        } catch (SharedApplicationException e) {
            //            sharedUtil.handleException(e,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInUpdate");
            sharedUtil.handleException(e);
        } catch (Exception e) {
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
        }
        //Added By Yassmine to reset the value of radio button after Edit
        setSelectedRadio("");
        // decisionEmployeesModelSessionBean.resetSessionData();
        return getFinishNavigationCase();
    }

    public void setDetailsSavedStates(Map<String, Object> detailsSavedStates) {
        this.detailsSavedStates = detailsSavedStates;
    }

    public Map<String, Object> getDetailsSavedStates() {
        if (detailsSavedStates == null) {
            detailsSavedStates = new HashMap<String, Object>();
        }
        return detailsSavedStates;
    }

    //    private void handleDecisionImage() {
    //        String tempFilePath = (String)getDetailsSavedStates().get(BeansUtil.UPLOADED_IMAGE_BINDING_KEY);
    //        if (tempFilePath != null) {
    //            String imageFileName = getCurrentDecisionKey();
    //            try {
    //                String relativeFileName =
    //                    BeansUtil.saveRegImage(tempFilePath, RegConfig.getInstance().getDecisionUploadedImagesPath(),
    //                                           imageFileName);
    //                ((IDecisionsDTO)getPageDTO()).setDecisionImage(relativeFileName);
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }
    //    }

    //    private String getCurrentDecisionKey() {
    //        String decisionNumber = "";
    //        String decisionTypeCode = "";
    //        String issuanceYearCode = "";
    //
    //        if (getMaintainMode() == 0) { // add
    //            decisionNumber = ((IDecisionsDTO)getPageDTO()).getDecisionNumber().toString();
    //        } else if (getMaintainMode() == 1) { // edit
    //            decisionNumber = ((IDecisionsEntityKey)(getPageDTO().getCode())).getDecisionNumber().toString();
    //        }
    //        decisionTypeCode = ((IDecisionsDTO)getPageDTO()).getTypesDTO().getCode().getKey().toString();
    //        issuanceYearCode = ((IDecisionsDTO)getPageDTO()).getYearsDTO().getCode().getKey().toString();
    //
    //        return decisionNumber + "_" + decisionTypeCode + "_" + issuanceYearCode;
    //    }

    //    public void setCancelDecisionMode(boolean cancelDecisionMode) {
    //        this.cancelDecisionMode = cancelDecisionMode;
    //    }
    //

    public void setCopyDecisionWithEmployeesMode(boolean copyDecisionWithEmployeesMode) {
        this.copyDecisionWithEmployeesMode = copyDecisionWithEmployeesMode;
    }

    public boolean isCopyDecisionWithEmployeesMode() {
        return copyDecisionWithEmployeesMode;
    }


    public void setLisBeanName(String lisBeanName) {
        this.lisBeanName = lisBeanName;
    }

    public String getLisBeanName() {
        return lisBeanName;
    }

    //    public void setCurrentStag(Long currentStag) {
    //        this.currentStag = currentStag;
    //    }
    //
    //    public Long getCurrentStag() {
    //        return currentStag;
    //    }

    public void setShowOnly(boolean showOnly) {
        this.showOnly = showOnly;
    }

    public boolean isShowOnly() {
        return showOnly;
    }

    public void navigate(ActionEvent actionEvent) {
        String stepKey = actionEvent.getComponent().getId();
        String currentStepKey = getCurrentStep();
        this.updateStepDependancies(currentStepKey);
        String nCase = null;
        if (validateStep(currentStepKey, stepKey) && checkStepRelevants(stepKey)) {
            //added for finish button status
            setValidated(currentStepKey);
            setVisited(currentStepKey);
            setVisited(stepKey);
            //set the finish button status
            setCurrentStep(stepKey);
            getWizardBar().setCurrentStep(stepKey);
            if (getFinishButtonOverride(currentStepKey) == 1) {
                setRenderFinish(getFinishButtonStatus(stepKey));
            } else if (getFinishButtonOverride(currentStepKey) == 2) {
                setRenderFinish(false);
            } else {
                setRenderFinish(true);
            }

            //set the save button status


            //
            nCase = getNavigationCase(stepKey);
            setNextNavigationCase(getNextNavigationCase(stepKey));
            setPreviousNavigationCase(getPreviousNavigationCase(stepKey));
            System.out.println(stepKey);
        }

        if (nCase != null)
            handleNavigation(nCase);
    }
    
    public String previous() {

    //        System.out.println("presentation:Before:ManyToManyMaintainBaseBean.previous()"+previousNavigationCase+new Date(System.currentTimeMillis()).toString());
        String currentStep = getCurrentStep();
        String nCase = getPreviousNavigationCase();
        this.updateStepDependancies(currentStep);
        //
        nCase = getNavigationCase(this.getAvailablePreviousStep(currentStep));
        //

        String targetStep = getPreviousStep(currentStep);
        if (targetStep != null)
            //validateStep(currentStep, targetStep) && 
            if (checkStepRelevants(targetStep)) {

                setNextNavigationCase(getNavigationCase(currentStep));
                setPreviousNavigationCase(getPreviousNavigationCase(getPreviousStep(currentStep)));

                // setNextNavigationCase(getNavigationCase(this.getAvailableNextStep(targetStep)));
                // setPreviousNavigationCase(getNavigationCase(this.getAvailablePreviousStep(targetStep)));


                setVisited(getPreviousStep(currentStep));
                setValidated(currentStep);
                setVisited(currentStep);
                if (getFinishButtonOverride(getPreviousStep(currentStep)) == 
                    1) {
                    setRenderFinish(getFinishButtonStatus(getPreviousStep(currentStep)));
                } else if (getFinishButtonOverride(getPreviousStep(currentStep)) == 
                           2) {
                    setRenderFinish(false);
                } else {
                    setRenderFinish(true);
                }


                setCurrentStep(getPreviousStep(currentStep));
                getWizardBar().setCurrentStep(currentStep);
    //                System.out.println("presentation:Before:ManyToManyMaintainBaseBean.previous()"+previousNavigationCase+new Date(System.currentTimeMillis()).toString());
                
                return nCase;
            }
    //        System.out.println("presentation:Before:ManyToManyMaintainBaseBean.previous()"+previousNavigationCase+new Date(System.currentTimeMillis()).toString());
        
        return null;
    }

    public void openConfirmSave() {

    }


    //    private String setRegNumber(IDecisionsDTO decisionsDTO) throws RemoteException {
    //
    //        StringBuffer decisionDoneNumber = new StringBuffer("");
    //        Long decTypeCode = ((IDecisionsEntityKey)decisionsDTO.getCode()).getDectypeCode();
    //        Long decYear = ((IDecisionsEntityKey)decisionsDTO.getCode()).getDecyearCode();
    //        Long minCode = 13l;
    //
    //        // return serial number
    //        String regSerial =
    //            ((IDecisionsClient)getClient()).getSerialByMinAndYearAndType(decTypeCode, decYear, minCode).toString();
    //
    //        String yearCode = (decYear.toString()).substring(2);
    //
    //        decisionDoneNumber.append(yearCode);
    //        decisionDoneNumber.append(minCode);
    //        decisionDoneNumber.append(decTypeCode);
    //        decisionDoneNumber.append("/");
    //        decisionDoneNumber.append(regSerial);
    //        decisionsDTO.setRegNum(decisionDoneNumber.toString());
    //
    //        return decisionDoneNumber.toString();
    //    }

    //    public void setReviewMode(boolean _reviewMode) {
    //        this._reviewMode = _reviewMode;
    //    }
    //
    //    public boolean isReviewMode() {
    //        return _reviewMode;
    //    }

    public void setCurrentStag(Long currentStag) {
        this.currentStag = currentStag;
    }

    public Long getCurrentStag() {
        return currentStag;
    }


    public void setDecNumYearAdded(int decNumYearAdded) {
        this.decNumYearAdded = decNumYearAdded;
    }

    public int getDecNumYearAdded() {
        return decNumYearAdded;
    }

    public void setDataLoadedBefore(boolean dataLoadedBefore) {
        this.dataLoadedBefore = dataLoadedBefore;
    }

    public boolean isDataLoadedBefore() {
        return dataLoadedBefore;
    }

    public void setBackMethodName(String backMethodName) {
        this.backMethodName = backMethodName;
    }

    public String getBackMethodName() {
        return backMethodName;
    }

    public void setBackNavCase(String backNavCase) {
        this.backNavCase = backNavCase;
    }

    public String getBackNavCase() {
        return backNavCase;
    }

    public void setSavedDataObjects(Object[] savedDataObjects) {
        this.savedDataObjects = savedDataObjects;
    }

    public Object[] getSavedDataObjects() {
        return savedDataObjects;
    }
}
