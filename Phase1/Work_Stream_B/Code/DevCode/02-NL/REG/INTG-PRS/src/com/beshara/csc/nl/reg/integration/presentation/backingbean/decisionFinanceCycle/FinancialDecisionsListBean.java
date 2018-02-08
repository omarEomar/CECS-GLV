package com.beshara.csc.nl.reg.integration.presentation.backingbean.decisionFinanceCycle;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.sharedUtil.IConstants;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.decisionFinanceCycle.details.DecisionCycleMainDataMaintain;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.shared.financialdecision.FinancialDecisionBaseListBean;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.util.UtilBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FinancialDecisionsListBean extends FinancialDecisionBaseListBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    public static final String BEAN_NAME = "financialDecisionsListBean";

    @Override
    public void initiateBeanOnce() {

        super.initiateBeanOnce();
        Long subjectCode = UtilBean.getMappedCodeBySysSettingCode(IConstants.SYSTEM_SETTING_FINANCIAL_MERITS_CODE);
        Long subjectCode2 = UtilBean.getMappedCodeBySysSettingCode(IConstants.SYSTEM_SETTING_FINANCIAL_MERITS_MIS_CODE);
        getDecisionSearchDTO().setSubjectCode(subjectCode);
        getDecisionSearchDTO().setSubjectCode2(subjectCode2);
        this.setSystemSettingCode(IConstants.SYSTEM_SETTING_FINANCIAL_MERITS_CODE);

    }

    public FinancialDecisionsListBean() {

        /*start by aly nour to get reg by status*/

        super();
        setLisBeanName("financialDecisionsListBean");
        Long subjectCode = UtilBean.getMappedCodeBySysSettingCode(IConstants.SYSTEM_SETTING_FINANCIAL_MERITS_CODE);
        setSearchSubjectCode(subjectCode);
        //setSelectedRegTypeKey(0L);
        /*end by aly nour to get reg by status*/
        setDivSearch("m2meditDivClass");

        setSaveSortingState(true);
        super.setSelectedPageDTO(RegDTOFactory.createDecisionsDTO());
        //        if (DecisionMaintainBean.isIntegrationPage()) {
        //            setEditNavigationCase("decisionemployeesmaintainedit-integration");
        //            setAddNavigationCase("decisionemployeesmaintain-integration");
        //        } else {
        //            setEditNavigationCase("decisionmaindataedit");
        //setAddNavigationCase("financedecisioncyclemaindata");
        //        }
        //        setEditBeanName("decisionMaintainBean");
        setAddBeanName("financeDecisionCycleMaintainBean");
        //        setDelConfirmTitleBindingString("#{regResources.DelConfirmTitle}");
        //        setDelAlertTitleBindingString("#{regResources.DelAlertTitle}");

        //        setCustomDiv1("cancelDecisionDetailsDiv");

        setAddNavigationCase("financedecisionsuggestionmaindataadd");
        setEditNavigationCase("financedecisionsuggestionmaindataadd");
        setAddBeanName("financeDecisionCycleMaintainBean");


        //        setUsingPaging(false);
        //  setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));
        setIndexArray(new boolean[] { true, true, true, false, false, true, true, true });


        setCustomDiv2("m2mAddDivClass");

    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowCustomDiv1(true);
        app.setShowCustomDiv2(true);
        app.setShowCustomDiv3(true);

        return app;
    }

    //this to initialize the arrays of the dto in case of add


    //    public String goAdd() {
    //        String addNavigationCase = super.goAdd();
    //
    //        DecisionCycleMaintainBean decisionMainDataMaintain = (DecisionMainDataMaintain)evaluateValueBinding("financeDecisionCycleMainDataMaintainBean");
    //        decisionMainDataMaintain.setDecMkrDisabled(true);
    //
    //        if (getIntegrationBean() != null &&
    //            (getIntegrationBean().getInitializeMethod() != null &&
    //             getIntegrationBean().getInitializeMethod() != "")) {
    //
    //            executeMethodBinding(getIntegrationBean().getInitializeMethod(),
    //                                 null);
    //        }
    //        return addNavigationCase;
    //    }

    //    private List<IBasicDTO> getFinancialSubject(){
    //        IEntityKey ek = RegEntityKeyFactory.createSubjectsEntityKey(UtilBean.getMappedCodeBySysSettingCode(IConstants.SYSTEM_SETTING_FINANCIAL_MERITS_CODE));
    //
    //        try {
    //            return RegClientFactory.getSubjectsClient().getChildrenList(ek);
    //        } catch (DataBaseException e) {
    //            return null;
    //        } catch (SharedApplicationException e) {
    //            return null;
    //        }
    //    }
    //

    public String goAdd() {

        DecisionCycleMaintainBean decisionMaintainBean =
            (DecisionCycleMaintainBean)evaluateValueBinding("financeDecisionCycleMaintainBean");
        decisionMaintainBean.setFinishNavigationCase("financeDecisionCycleEmployeesMaintainBean");
        decisionMaintainBean.setLisBeanName("financialDecisionsListBean");
        decisionMaintainBean.setCurrentStag(1L);
        DecisionCycleMainDataMaintain decisionCycleMainDataMaintain =
            (DecisionCycleMainDataMaintain)evaluateValueBinding("financeDecisionCycleMainDataMaintainBean");

        decisionMaintainBean.setPageDTO(this.getPageDTO());
        ((IDecisionsDTO)this.getPageDTO()).setDecisionDate(SharedUtils.getCurrentTimestamp());
        ((IDecisionsDTO)this.getPageDTO()).setEmpDecisionsDTOList(new ArrayList<IBasicDTO>());
        // ((IDecisionsDTO)this.getPageDTO()).setSubjectsDTO(RegDTOFactory.createSubjectsDTO());
        //((IDecisionsDTO)this.getPageDTO()).getSubjectsDTO().setSubjectsDTOList(getFinancialSubject());
        decisionCycleMainDataMaintain.setEditorReturn("financedecisionsuggestionmaindataadd");
        decisionMaintainBean.setMultiOrSingleEmps(decisionMaintainBean.OneElementMultiEmps);
        this.initializeObjectBeforeMaintain();
        decisionMaintainBean.setMaintainMode(0);
        //String addNavigationCase = super.goAdd();
        //return addNavigationCase;
        return getAddNavigationCase();
    }


    public String goEdit() throws DataBaseException, SharedApplicationException {

        DecisionCycleMaintainBean decisionMaintainBean =
            (DecisionCycleMaintainBean)evaluateValueBinding("financeDecisionCycleMaintainBean");
        decisionMaintainBean.setFinishNavigationCase("financeDecisionCycleEmployeesMaintainBean");
        decisionMaintainBean.setLisBeanName("financialDecisionsListBean");
        decisionMaintainBean.setCurrentStag(1L);
        DecisionCycleMainDataMaintain decisionCycleMainDataMaintain =
            (DecisionCycleMainDataMaintain)evaluateValueBinding("financeDecisionCycleMainDataMaintainBean");
        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {


            //setPageDTO(getClient().getById(this.getSelectedDTOS().get(0).getCode()));
            setPageDTO(((IDecisionsClient)getClient()).getByIdForFinMerit(this.getSelectedDTOS().get(0).getCode()));
            IDecisionsDTO dto = (IDecisionsDTO)getPageDTO();
            if(dto.getViewFlag().equals(Long.parseLong(decisionMaintainBean.OneEmpMultiElements)) && dto.getEmpDecisionsDTOList()!= null && !dto.getEmpDecisionsDTOList().isEmpty()){
                IEmpDecisionsDTO empDecDTO = (IEmpDecisionsDTO)dto.getEmpDecisionsDTOList().get(0);
                if(empDecDTO.getEmployeesDTO()!= null){
                        dto.setName(empDecDTO.getEmployeesDTO().getCode().getKey());//Emp CivilID Or Person RealCivilID
                        dto.setEngName(((IKwtCitizensResidentsEntityKey)((IEmployeesDTO)empDecDTO.getEmployeesDTO()).getCitizensResidentsDTO().getCode()).getCivilId().toString());//RealCivil
                }
            }
            decisionMaintainBean.setMaintainMode(1);
            decisionMaintainBean.setPageDTO(this.getPageDTO());
            decisionMaintainBean.setMultiOrSingleEmps(((IDecisionsDTO)getPageDTO()).getViewFlag().toString());
            // ((IDecisionsDTO)this.getPageDTO()).setEmpDecisionsDTOList(new ArrayList());
            //decisionCycleMainDataMaintain.setEditorReturn("financedecisionsuggestionmaindataadd");
            this.initializeObjectBeforeMaintain();
            return getEditNavigationCase();

        }
        return null;
    }

    //
    //    public String goViewDecisionDetail() throws DataBaseException,
    //                                  SharedApplicationException {
    //
    //        DecisionCycleMaintainBean decisionMaintainBean  =
    //            (DecisionCycleMaintainBean)evaluateValueBinding("financeDecisionCycleMaintainBean");
    //        decisionMaintainBean.setFinishNavigationCase("financeDecisionCycleEmployeesMaintainBean");
    //        decisionMaintainBean.setLisBeanName("financialDecisionsListBean");
    //        decisionMaintainBean.setCurrentStag(1L);
    //        DecisionCycleMainDataMaintain decisionCycleMainDataMaintain =
    //            (DecisionCycleMainDataMaintain)evaluateValueBinding("financeDecisionCycleMainDataMaintainBean");
    //        if (this.getSelectedDTOS() != null &&
    //            this.getSelectedDTOS().size() == 1) {
    //
    //
    //            //setPageDTO(getClient().getById(this.getSelectedDTOS().get(0).getCode()));
    //            setPageDTO(((IDecisionsClient)getClient()).getByIdSimple(this.getSelectedDTOS().get(0).getCode()));
    //            decisionMaintainBean.setMaintainMode(decisionMaintainBean.MAINTAIN_MODE_EDIT);
    //            decisionMaintainBean.setShowOnly(true);
    //            decisionMaintainBean.setPageDTO(this.getPageDTO());
    //
    //            // ((IDecisionsDTO)this.getPageDTO()).setEmpDecisionsDTOList(new ArrayList());
    //            //decisionCycleMainDataMaintain.setEditorReturn("financedecisionsuggestionmaindataadd");
    //           this.initializeObjectBeforeMaintain();
    //            return getEditNavigationCase();
    //
    //        }
    //        return null;
    //    }
    //
    // Update By A.AbdelHalim
    // for User Story CSC-14722
    // 12-OCT-2015


    //    public boolean isDisapleButton(){
    //        if(getSelectedDTOS()!=null && getSelectedDTOS().size() != 0){
    //            Long regStatusCode =((IRegulationStatusEntityKey)((IDecisionsDTO)getSelectedDTOS().get(0)).getRegStatusDTO().getCode()).getRegstatusCode();
    //            if(regStatusCode.equals(IConstants.REG_APPROVED_STATUS)){
    //                return true;
    //            }else{
    //                return false;
    //            }
    //        }else{
    //            return false ;
    //        }
    //
    //    }


    @Override
    public boolean executeDelete() throws DataBaseException, SharedApplicationException {
        IDecisionsDTO selectedDTO = (IDecisionsDTO)getSelectedDTOS().get(0);
        selectedDTO.setSystemSettingCode(IConstants.SYSTEM_SETTING_FINANCIAL_MERITS_CODE);
        return RegClientFactory.getDecisionsClient().removeFincialDecision(selectedDTO);
    }

    @Override
    public IBasicDTO issueDecision(IDecisionsDTO dtoForExecute) throws SharedApplicationException, DataBaseException {
        dtoForExecute.setSystemSettingCode(IConstants.SYSTEM_SETTING_FINANCIAL_MERITS_CODE);
        IDecisionsDTO decisionsDTO =
            (IDecisionsDTO)((IDecisionsClient)getClient()).executeFinantialDecision(dtoForExecute);
        return decisionsDTO;
        //        ((IDecisionsClient)getClient()).executeDecisionChangeStatusOnly(dtoForExecute);
        //        // update Sal_emp_mon_salaries set pay_status=-1
        //        ((IDecisionsClient)getClient()).updateStatusSalMonSalaries(dtoForExecute.getCode());
    }

    public String viewDecisoinDetail() {

        try {
            //            ISalFinDues dto = (ISalFinDues)this.getMyDataTable().getRowData();
            //            IDecisionsEntityKey decisionCode = RegEntityKeyFactory.createDecisionsEntityKey(dto.getDecisionKey());
            //            System.out.println("decisionKey>>" + decisionCode);
            //            IBasicDTO decisionDTO = RegClientFactory.getDecisionsClient().getByIdForFinMerit(decisionCode);
            //            decisionMaintainBean.setPageDTO(decisionDTO);

            DecisionCycleMaintainBean decisionMaintainBean =
                (DecisionCycleMaintainBean)evaluateValueBinding("financeDecisionCycleMaintainBean");
            decisionMaintainBean.setFinishNavigationCase("financeDecisionCycleEmployeesMaintainBean");
            decisionMaintainBean.setLisBeanName(getLisBeanName());
            decisionMaintainBean.setCurrentStag(1L);
            decisionMaintainBean.setMaintainMode(decisionMaintainBean.MAINTAIN_MODE_VIEW_ONLY);
            decisionMaintainBean.setShowOnly(true);
            decisionMaintainBean.setBackMethodName(BEAN_NAME+".back2");
            decisionMaintainBean.setBackNavCase("financialdecisionslist");
            Map savedDataObjects = fillSaveStatesMap();
            Object[] objects = new Object[1];
            objects[0] = savedDataObjects;
            decisionMaintainBean.setSavedDataObjects(objects);  
            //decisionMaintainBean.init(fillSaveStateParams(), "finMeritsEnquiryPage", "merFinDuesSearchBean.backDecisionDetail");
            if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
                //setPageDTO(getClient().getById(this.getSelectedDTOS().get(0).getCode()));
                setPageDTO(((IDecisionsClient)getClient()).getByIdForFinMerit(this.getSelectedDTOS().get(0).getCode()));
                decisionMaintainBean.setPageDTO(this.getPageDTO());
                decisionMaintainBean.setMultiOrSingleEmps(((IDecisionsDTO)getPageDTO()).getViewFlag().toString());
                // ((IDecisionsDTO)this.getPageDTO()).setEmpDecisionsDTOList(new ArrayList());
                //decisionCycleMainDataMaintain.setEditorReturn("financedecisionsuggestionmaindataadd");
                this.initializeObjectBeforeMaintain();
                return getEditNavigationCase();
            }
            //return "financedecisionsuggestionmaindataadd";
        } catch (Exception sae) {
            // TODO: Add catch code
            sae.printStackTrace();
        }
        return null;

    }
    public void back2(Object map) {

        restoreSaveStatesMap(map);
        //setSearchMode(false);
        //fillDataTable();
    }

    private Map fillSaveStatesMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("selectedTypeKey", getSelectedTypeKey());
        map.put("decisionSearchDTO", getDecisionSearchDTO());
        map.put("fullColumnName", getFullColumnName());
        map.put("sortAscending", isSortAscending());
        map.put("saveSortingState", isSaveSortingState());
        map.put("sortColumn", getSortColumn());
        map.put("systemSettingCode", getSystemSettingCode());
        return map;
    }

    public void restoreSaveStatesMap(Object obj) {
        Map<String, Object> map = (Map<String, Object>)obj;
        if (map.get("selectedTypeKey") != null)
            setSelectedTypeKey((Long)map.get("selectedTypeKey"));
        if (map.get("decisionSearchDTO") != null)
            setDecisionSearchDTO((IRegulationSearchDTO)map.get("decisionSearchDTO"));
        if (map.get("fullColumnName") != null)
            setFullColumnName((String)map.get("fullColumnName"));
        if (map.get("sortAscending") != null)
            setSortAscending((Boolean)map.get("sortAscending"));
        if (map.get("saveSortingState") != null)
            setSaveSortingState((Boolean)map.get("saveSortingState"));
        if (map.get("sortColumn") != null)
            setSortColumn((String)map.get("sortColumn"));
        if (map.get("systemSettingCode") != null)
            setSystemSettingCode((Long)map.get("systemSettingCode"));
    }

    @Override
    public void cancelDecision() {
        IDecisionsClient client = RegClientFactory.getDecisionsClient();
        IDecisionsDTO selectedDTO = (IDecisionsDTO)getSelectedDTOS().get(0);
        boolean isValid = true;
        try {
            isValid = client.checkMonSalStatusBeforeCancel(((IDecisionsEntityKey)selectedDTO.getCode()));
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        if (!isValid) {
            getSharedUtil().handleException(null, RESOURCE_PATH, "cant_cancel_this_dec");
            return;
        }
        super.cancelDecision();
    }
}
