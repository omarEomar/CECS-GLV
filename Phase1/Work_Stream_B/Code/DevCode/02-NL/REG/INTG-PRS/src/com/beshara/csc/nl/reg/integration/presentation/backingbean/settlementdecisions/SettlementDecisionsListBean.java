package com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.entity.IDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.sharedUtil.IConstants;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.DecisionCycleMaintainBean;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.shared.financialdecision.FinancialDecisionBaseListBean;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.util.UtilBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//---------------------Added by AhmedSakr 5/1/2015 -----------------

public class SettlementDecisionsListBean extends FinancialDecisionBaseListBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    
    public static final String BEAN_NAME = "settlementDecisionsListBean";

    public SettlementDecisionsListBean() {
        super();
        setLisBeanName("settlementDecisionsListBean");
        setSaveSortingState(true);
        
        Long subjectCode = UtilBean.getMappedCodeBySysSettingCode(IConstants.SYSTEM_SETTING_SETTLEMENT_CODE);
        setSearchSubjectCode(subjectCode);
        
        //getDecisionSearchDTO().setRegStatusFlage(getSelectedTypeKey());

//        setUsingPaging(false);
        // setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));
        setIndexArray(new boolean[] { true, true, true, false, false, true, true, true });


        setAddBeanName("settlementDecisionCycleMaintainBean");


        setAddNavigationCase("settlementdecisionsuggestionmaindataadd");
        setEditNavigationCase("settlementdecisionsuggestionmaindataadd");
        setDivSearch("m2meditDivClass");
        setCustomDiv2("m2mAddDivClass");

        //  getFinDecisionStatusList();
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowbar(true);
        app.setShowpaging(true);
        app.setShowdatatableContent(true);
        app.setShowContent1(true);
        app.setShowCustomDiv1(true);
        //app.setShowSearch(true);
        app.setShowCustomDiv2(true);
        app.setShowCustomDiv3(true);

        return app;
    }


    @Override
    public void initiateBeanOnce() {
        super.initiateBeanOnce();
        Long subjectCode = UtilBean.getMappedCodeBySysSettingCode(IConstants.SYSTEM_SETTING_SETTLEMENT_CODE);
        getDecisionSearchDTO().setSubjectCode(subjectCode);
        
        this.setSystemSettingCode(IConstants.SYSTEM_SETTING_SETTLEMENT_CODE);
    }


    public String goAdd() {

        DecisionCycleMaintainBean decisionMaintainBean = DecisionCycleMaintainBean.getInstance();
        decisionMaintainBean.setFinishNavigationCase("settlementDecisionCycleEmployeesMaintainBean");
        decisionMaintainBean.setLisBeanName("settlementDecisionsListBean");
        decisionMaintainBean.setBackMethodName("backFromAddEdit");
        decisionMaintainBean.setBackNavCase("settlementdecisionslist");
        decisionMaintainBean.setCurrentStag(1L);
        //        DecisionCycleMainDataMaintain decisionCycleMainDataMaintain =
        //            (DecisionCycleMainDataMaintain)evaluateValueBinding("settlementDecisionCycleMainDataMaintainBean");


        decisionMaintainBean.setPageDTO(this.getPageDTO());
        ((IDecisionsDTO)this.getPageDTO()).setDecisionDate(SharedUtils.getCurrentTimestamp());
        ((IDecisionsDTO)this.getPageDTO()).setEmpDecisionsDTOList(new ArrayList<IBasicDTO>());
        //decisionCycleMainDataMaintain.setEditorReturn("settlementdecisionsuggestionmaindataadd");
        this.initializeObjectBeforeMaintain();
        decisionMaintainBean.setMaintainMode(0);
        decisionMaintainBean.setDetailsSavedStates(fillSaveStatesMap());
        //String addNavigationCase = super.goAdd();
        //return addNavigationCase;
        return getAddNavigationCase();
    }

    public String goEdit() throws DataBaseException, SharedApplicationException {

        DecisionCycleMaintainBean decisionMaintainBean = DecisionCycleMaintainBean.getInstance();
        decisionMaintainBean.setLisBeanName("settlementDecisionsListBean");
        decisionMaintainBean.setBackMethodName("backFromAddEdit");
        decisionMaintainBean.setBackNavCase("settlementdecisionslist");
        decisionMaintainBean.setCurrentStag(1L);
        //       DecisionCycleMainDataMaintain decisionCycleMainDataMaintain =DecisionCycleMainDataMaintain.getInstance();
        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {

            setPageDTO(((IDecisionsClient)getClient()).LoadSettDecDataForEdit(this.getSelectedDTOS().get(0)));
            //((IDecisionsClient)getClient()).getByIdSimple(this.getSelectedDTOS().get(0).getCode()));
            //setPageDTO(getClient().getById(this.getSelectedDTOS().get(0).getCode()));
            decisionMaintainBean.setMaintainMode(1);
            decisionMaintainBean.setPageDTO(this.getPageDTO());

            // ((IDecisionsDTO)this.getPageDTO()).setEmpDecisionsDTOList(new ArrayList());

            //decisionCycleMainDataMaintain.setEditorReturn("settlementdecisionsuggestionmaindataadd");
            this.initializeObjectBeforeMaintain();

            //decisionMaintainBean.setPageDTO(this.getPageDTO());
            //            String returnedString =   super.goEdit();
            //            return returnedString;
//            decisionMaintainBean.setDetailsSavedStates(fillSaveStatesMap());

            return getEditNavigationCase();


        }
        return null;
    }

    public void backFromAddEdit(Map map) {

        //restoreSaveStatesMap(map);
        //setSearchMode(false);
        //fillDataTable();
    }

    private Map<String, Object> fillSaveStatesMap() {
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

    public void restoreSaveStatesMap(Map<String, Object> map) {
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
    public IBasicDTO issueDecision(IDecisionsDTO dtoForExecute) throws SharedApplicationException, DataBaseException {

        dtoForExecute.setSystemSettingCode(IConstants.SYSTEM_SETTING_SETTLEMENT_CODE);
        IDecisionsDTO decisionsDTO =(IDecisionsDTO)((IDecisionsClient)getClient()).executeFinantialDecision(dtoForExecute);
        return decisionsDTO;
        //        ((IDecisionsClient)getClient()).executeDecisionChangeStatusOnly(dtoForExecute);
        //        // update Sal_emp_settelments set status=-1
        //        ((IDecisionsClient)getClient()).updateStatusSalEmpSettelments(dtoForExecute.getCode());
    }

    @Override
    public boolean executeDelete() throws SharedApplicationException, DataBaseException {
        IDecisionsDTO selectedDTO = (IDecisionsDTO)getSelectedDTOS().get(0);
        selectedDTO.setSystemSettingCode(IConstants.SYSTEM_SETTING_SETTLEMENT_CODE);
        return ((IDecisionsClient)getClient()).removeFincialDecisionForEmpList(selectedDTO);
    }
    
    
    @Override
    public void cancelDecision() {
        IDecisionsClient client = RegClientFactory.getDecisionsClient();
        IDecisionsDTO selectedDTO = (IDecisionsDTO)getSelectedDTOS().get(0);
        boolean isValid = true;
        try {
            isValid = client.checkSettlementStatusBeforeCancel(((IDecisionsEntityKey)selectedDTO.getCode()));
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        if (!isValid) {
            getSharedUtil().handleException(null, RESOURCE_PATH, "cant_cancel_this_dec_sett");
            return;
        }
        super.cancelDecision();
    }
    
    public String viewDecisoinDetail() {

        try {
            
            DecisionCycleMaintainBean decisionMaintainBean =
                (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
            decisionMaintainBean.setFinishNavigationCase("settlementDecisionCycleEmployeesMaintainBean");
            decisionMaintainBean.setLisBeanName(getLisBeanName());
            decisionMaintainBean.setCurrentStag(1L);
            decisionMaintainBean.setMaintainMode(decisionMaintainBean.MAINTAIN_MODE_VIEW_ONLY);
            decisionMaintainBean.setShowOnly(true);
            decisionMaintainBean.setBackMethodName("back2");
            decisionMaintainBean.setBackNavCase("settlementdecisionslist");
            Map savedDataObjects = fillSaveStatesMap();
            Object[] objects = new Object[1];
            objects[0] = savedDataObjects;
            decisionMaintainBean.setSavedDataObjects(objects);  

            if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
                IBasicDTO dto = RegClientFactory.getDecisionsClient().LoadSettDecDataForEdit(this.getSelectedDTOS().get(0));
                setPageDTO(dto);
                decisionMaintainBean.setPageDTO(this.getPageDTO());

                return getEditNavigationCase();
            }
        } catch (Exception sae) {
            // TODO: Add catch code
            sae.printStackTrace();
        }
        return null;

    }
    
    public void back2(Map map) {

        restoreSaveStatesMap(map);
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
}
