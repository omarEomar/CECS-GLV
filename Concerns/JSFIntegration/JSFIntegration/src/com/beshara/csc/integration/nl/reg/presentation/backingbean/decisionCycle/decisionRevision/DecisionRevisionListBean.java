package com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionRevision;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.shared.SharedUtils;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationStatusDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegulationStatusEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.AbstractDecisionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.DecisionCycleMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.IncludedEmployeesList;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.details.DecisionCycleMainDataMaintain;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;

import java.util.ArrayList;
import java.util.List;


public class DecisionRevisionListBean extends AbstractDecisionListBean{
    public DecisionRevisionListBean() {
        setClient(RegClientFactory.getDecisionsClient());
        setPageDTO(RegDTOFactory.createDecisionsDTO());
        setSelectedPageDTO(RegDTOFactory.createDecisionsDTO());
        setDivMainContent("listDcisionMainContentRevisionAndExcute");
        setEditNavigationCase("decisionrevisionmaindataedit");
        setAddNavigationCase("decisionrevisionmaindata");
        setEditBeanName("decisionCycleMaintainBean");
        setAddBeanName("decisionCycleMaintainBean");
        setDelConfirmTitleBindingString("#{regResources.DelConfirmTitle}");
        setDelAlertTitleBindingString("#{regResources.DelAlertTitle}");
        setCustomDiv1("cancelDecisionDetailsDiv");
        getGetAllDTO().setCurrentStage(2L);
        getGetAllDTO().setRegStatusFlage(0L);
        
        getDecisionSearchDTO().setCurrentStage(2L);
        getDecisionSearchDTO().setRegStatusFlage(0L);
        setSaveSortingState(true);
    }
    
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app = super.appMainLayoutBuilder();
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        app.setShowCustomDiv1(true);
        app.setShowbar(true);
        app.setShowContent1(true);
        app.setShowSearch(false);
        return app;
    }
    
    public void search() throws DataBaseException, NoResultException {

        setSearchMode(true);
        setShowAllFlag(false);
        getDecisionSearchDTO().setCurrentStage(2L);
        getDecisionSearchDTO().setRegStatusFlage(0L);
        try {
            if (getDecisionSearchDTO() != null) {
                if (getDecisionSearchDTO().getName() != null) {
                    if (!(getDecisionSearchDTO().getName().equals(""))) {
                        getDecisionSearchDTO().setName(formatSearchString(getDecisionSearchDTO().getName()));
                    }
                }
                if (isUsingPaging()) {
                    setSorting(true);
                    setUpdateMyPagedDataModel(true);
                    generatePagingRequestDTO("decisionRevisionListBean","searchWithPaging");
                    setOldPageIndex(0);
                    setCurrentPageIndex(1);
                    unCheck();
                    if (getSelectedDTOS() != null)
                        getSelectedDTOS().clear();
                    if (getHighlightedDTOS() != null)
                        getHighlightedDTOS().clear();
                    this.repositionPage(0);
                    this.setStringSearchType("false");
                    setSelectedRadio("");
                    
                } else {
                    this.setMyTableData(getSearchResult());
                    if (getSelectedDTOS() != null)
                        getSelectedDTOS().clear();
                    if (getHighlightedDTOS() != null)
                        getHighlightedDTOS().clear();
                    unCheck();
                    this.repositionPage(0);
                    this.setStringSearchType("false");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public List getTotalList() {

        List<IBasicDTO> totalList = null;
        if (getClient() != null) {
            try {                
               if(isSearchMode()==true)
               {
               totalList = ((IDecisionsClient)getClient()).getAllWithParameter(getGetAllDTO());
               }
               else
               {
                totalList = new ArrayList();
               }
            } catch (SharedApplicationException ne) {
                totalList = new ArrayList();
                ne.printStackTrace();
            } catch (DataBaseException db) {
                getSharedUtil().handleException(db);
                totalList = new ArrayList();
            } catch (Exception e) {
                getSharedUtil().handleException(e);
                totalList = new ArrayList();
            }
        }
        return totalList;

    }

    public String goEdit() throws DataBaseException, 
                                  SharedApplicationException {

        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {

            DecisionCycleMaintainBean decisionMaintainBean = 
                (DecisionCycleMaintainBean)evaluateValueBinding("decisionCycleMaintainBean");
             decisionMaintainBean.setFinishNavigationCase("decisionrivisionlist");
            decisionMaintainBean.setLisBeanName("decisionRevisionListBean");
            decisionMaintainBean.setCurrentStag(2L);
            decisionMaintainBean.setMaintainMode(1);
            decisionMaintainBean.setPageDTO(this.getPageDTO());
            DecisionCycleMainDataMaintain decisionCycleMainDataMaintain = 
                (DecisionCycleMainDataMaintain)evaluateValueBinding("decisionCycleMainDataMaintainBean");
            decisionCycleMainDataMaintain.setEditorReturn("decisionrevisionmaindata");
            String addNavigationCase = super.goEdit();
            return addNavigationCase;
        }
        return null;
    }

    public void completeRevision() {
        IDecisionsDTO dto=(IDecisionsDTO)getSelectedDTOS().get(0);
        IRegulationStatusEntityKey key= RegEntityKeyFactory.createRegulationStatusEntityKey(3L);
        
        IRegulationStatusDTO regStatus=RegDTOFactory.createRegulationStatusDTO();
        regStatus.setCode(key);
        dto.setRegStatusDTO(regStatus);
        dto.setAuditDate(SharedUtils.getSqlDate());
        
        Boolean updateStatus;
        try {
            updateStatus = RegClientFactory.getDecisionsClient().update(dto);
            
        } catch (Exception e) {
            getSharedUtil().handleException(e,"com.beshara.csc.nl.reg.presentation.resources.reg","failed_revision");
        } finally {
            if (!isShowAllFlag()) {
                try {
                    search();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                showAllDecision();
            }

        }
        getSharedUtil().handleSuccMsg("com.beshara.csc.nl.reg.presentation.resources.reg","success_revision");
    }
    
    public String  displayDecisonPersons() throws SharedApplicationException, DataBaseException {
        IncludedEmployeesList includedEmployeesList=(IncludedEmployeesList)evaluateValueBinding("includedEmployeesList");
        includedEmployeesList.setSelectedDecision(getSelectedDTOS().get(0));
        includedEmployeesList.setBackNavigationCase("decisionrivisionlist");
        return "includedPersonsList";
    }

}
