package com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionSuggestion;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationStatusDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegulationStatusEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.AbstractDecisionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.DecisionCycleMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.details.DecisionCycleMainDataMaintain;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.constants.IRegConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;

import java.util.ArrayList;
import java.util.List;


public class DecisionSuggestionListBean extends AbstractDecisionListBean {
    private boolean disableCopyFlag;
    public DecisionSuggestionListBean() {
        setClient(RegClientFactory.getDecisionsClient());
        setPageDTO(RegDTOFactory.createDecisionsDTO());
        setSelectedPageDTO(RegDTOFactory.createDecisionsDTO());
        setDivMainContent("listDcisionMainContent");
        setEditNavigationCase("decisionsuggestionmaindataedit");
        setAddNavigationCase("decisionsuggestionmaindata");
        setEditBeanName("decisionCycleMaintainBean");
        setAddBeanName("decisionCycleMaintainBean");
        
        setDelConfirmTitleBindingString("#{regResources.DelConfirmTitle}");
        setDelAlertTitleBindingString("#{regResources.DelAlertTitle}");
        setCustomDiv1("cancelDecisionDetailsDiv");
        setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));
        
        setRegStatusConstant(IRegConstants.REGULATION_STATUS_SUGGESTION);
        getGetAllDTO().setCurrentStage(1L);
        getGetAllDTO().setCreatedBy(CSCSecurityInfoHelper.getUserCode());
        getDecisionSearchDTO().setCurrentStage(1L);
        getDecisionSearchDTO().setCreatedBy(CSCSecurityInfoHelper.getUserCode());
        
        //disableOperationBarButton();
    }
    
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app = super.appMainLayoutBuilder();
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        app.setShowCustomDiv1(true);
        app.setShowbar(true);
        app.setShowContent1(true);
    
        return app;
    }
    
    public void search() throws DataBaseException, NoResultException {

        setSearchMode(true);
        getDecisionSearchDTO().setCurrentStage(1L);
        getDecisionSearchDTO().setCreatedBy(CSCSecurityInfoHelper.getUserCode());
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
                    generatePagingRequestDTO("decisionSuggestionListBean","searchWithPaging");
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
    
 public void disableOperationBarButton()
 {
//     if(getSelectedRegTypeKey()==IRegConstants.REGULATION_STATUS_SUGGESTION || getSelectedRegTypeKey()==IRegConstants.REGULATION_STATUS_READY_REVISION )
//     {
//     setDisabledButtonFlag(false);
//     }
//     else
//     {
//     setDisabledButtonFlag(true);
//     }
 }
 
     public String goAdd() {
         
         DecisionCycleMaintainBean decisionMaintainBean  =(DecisionCycleMaintainBean)evaluateValueBinding("decisionCycleMaintainBean");
         decisionMaintainBean.setFinishNavigationCase("decisionsuggestionlist");
         decisionMaintainBean.setLisBeanName("decisionSuggestionListBean");
         decisionMaintainBean.setCurrentStag(1L);
         DecisionCycleMainDataMaintain decisionCycleMainDataMaintain=(DecisionCycleMainDataMaintain)evaluateValueBinding("decisionCycleMainDataMaintainBean");
         decisionCycleMainDataMaintain.setEditorReturn("decisionsuggestionmaindata");
         String addNavigationCase = super.goAdd();
         return addNavigationCase;
    }

    public String goEdit() throws DataBaseException, 
                                  SharedApplicationException{
        
        DecisionCycleMaintainBean decisionMaintainBean  =(DecisionCycleMaintainBean)evaluateValueBinding("decisionCycleMaintainBean");
        decisionMaintainBean.setFinishNavigationCase("decisionsuggestionlist");
        decisionMaintainBean.setLisBeanName("decisionSuggestionListBean");
        decisionMaintainBean.setCurrentStag(1L);
        DecisionCycleMainDataMaintain decisionCycleMainDataMaintain=(DecisionCycleMainDataMaintain)evaluateValueBinding("decisionCycleMainDataMaintainBean");
        decisionCycleMainDataMaintain.setEditorReturn("decisionsuggestionmaindata");        
        String addNavigationCase = super.goEdit();
        return addNavigationCase;
    }
    
    public String displayDecisionDetails() throws DataBaseException, 
                                  SharedApplicationException{
        
        DecisionCycleMaintainBean decisionMaintainBean  =(DecisionCycleMaintainBean)evaluateValueBinding("decisionCycleMaintainBean");
        decisionMaintainBean.setFinishNavigationCase("decisionsuggestionlist");
        decisionMaintainBean.setLisBeanName("decisionSuggestionListBean");
        decisionMaintainBean.setCurrentStag(1L);
        DecisionCycleMainDataMaintain decisionCycleMainDataMaintain=(DecisionCycleMainDataMaintain)evaluateValueBinding("decisionCycleMainDataMaintainBean");
        decisionCycleMainDataMaintain.setEditorReturn("decisionsuggestionmaindata");
        decisionMaintainBean.setShowOnly(true);
        String addNavigationCase = super.goEdit();
        return addNavigationCase;
    }
    public void sendToRevison() throws DataBaseException, 
                                       SharedApplicationException {
        IDecisionsDTO dto=(IDecisionsDTO)getSelectedDTOS().get(0);
        IRegulationStatusEntityKey key= RegEntityKeyFactory.createRegulationStatusEntityKey(0L);
        IRegulationStatusDTO regStatus=RegDTOFactory.createRegulationStatusDTO();
        regStatus.setCode(key);
        dto.setRegStatusDTO(regStatus);
        RegClientFactory.getDecisionsClient().update(dto);
        fillDataTable();
    }

    public void setDisableCopyFlag(boolean disableCopyFlag) {
        this.disableCopyFlag = disableCopyFlag;
    }

    public boolean isDisableCopyFlag() {
        if(getSelectedRegTypeKey()!=1L && getSelectedRegTypeKey()!=2L )
        {
        setDisableCopyFlag(true);
        }
        else
        {
        setDisableCopyFlag(false);
        }
        return disableCopyFlag;
    }
}
