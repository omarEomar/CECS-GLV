package com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.decisionExcute;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegulationStatusEntityKey;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.AbstractDecisionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.DecisionCycleMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.IncludedEmployeesList;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.details.DecisionCycleMainDataMaintain;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.constants.IRegConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;

import java.util.ArrayList;
import java.util.List;


public class DecisionExcuteListBean extends AbstractDecisionListBean{
    private boolean disableFlag;
    public DecisionExcuteListBean() {
    
        setClient(RegClientFactory.getDecisionsClient());
        setPageDTO(RegDTOFactory.createDecisionsDTO());
        setSelectedPageDTO(RegDTOFactory.createDecisionsDTO());
        setEditNavigationCase("decisionexecuionmaindataedit");
        setAddNavigationCase("decisionexecuionmaindata");
        setEditBeanName("decisionCycleMaintainBean");
        setAddBeanName("decisionCycleMaintainBean");
        setDelConfirmTitleBindingString("#{regResources.DelConfirmTitle}");
        setDelAlertTitleBindingString("#{regResources.DelAlertTitle}");
        setCustomDiv1("cancelDecisionDetailsDiv");
        setRegStatusConstant(IRegConstants.REGULATION_STATUS_REVISION);
        setDivMainContent("listDcisionMainContentRevisionAndExcute");
        getGetAllDTO().setCurrentStage(3L);
        getGetAllDTO().setRegStatusFlage(3L);
         getGetAllDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
        
        getDecisionSearchDTO().setCurrentStage(3L);
        getDecisionSearchDTO().setRegStatusFlage(3L);
         getDecisionSearchDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
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
        getDecisionSearchDTO().setCurrentStage(3L);
        getDecisionSearchDTO().setRegStatusFlage(3L);
        getDecisionSearchDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());

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
                    generatePagingRequestDTO("decisionExcuteListBean","searchWithPaging");
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
                    totalList=new ArrayList();
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
                                 SharedApplicationException{
        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {

       DecisionCycleMaintainBean decisionMaintainBean  =(DecisionCycleMaintainBean)evaluateValueBinding("decisionCycleMaintainBean");
        setPageDTO(getClient().getById(this.getSelectedDTOS().get(0).getCode()));
       decisionMaintainBean.setFinishNavigationCase("decisionexecustionlist");
       decisionMaintainBean.setLisBeanName("decisionExcuteListBean");
       decisionMaintainBean.setCurrentStag(3L);
        decisionMaintainBean.setMaintainMode(1);
         decisionMaintainBean.setPageDTO(this.getPageDTO());
       ((IDecisionsDTO)this.getPageDTO()).setCurrentStage(3l);
        DecisionCycleMainDataMaintain decisionCycleMainDataMaintain=(DecisionCycleMainDataMaintain)evaluateValueBinding("decisionCycleMainDataMaintainBean");
        decisionCycleMainDataMaintain.setEditorReturn("decisionexecuionmaindata");              
       String addNavigationCase = super.goEdit();
       return addNavigationCase;
            }
            return null; 
    }
    
    public String  displayDecisonPersons() throws SharedApplicationException, DataBaseException {
        IncludedEmployeesList includedEmployeesList=(IncludedEmployeesList)evaluateValueBinding("includedEmployeesList");
        includedEmployeesList.setSelectedDecision(getSelectedDTOS().get(0));
        includedEmployeesList.setBackNavigationCase("decisionexecustionlist");
        return "includedPersonsList";
    }

    public void setDisableFlag(boolean disableFlag) {
        this.disableFlag = disableFlag;
    }

    public boolean isDisableFlag() {
        if(getSelectedDTOS()!=null && getSelectedDTOS().size()==1)
         {   if(((IRegulationStatusEntityKey)((IDecisionsDTO)getSelectedDTOS().get(0)).getRegStatusDTO().getCode()).getRegstatusCode()==1 )
            {
            setDisableFlag(true);
            }
            if(((IRegulationStatusEntityKey)((IDecisionsDTO)getSelectedDTOS().get(0)).getRegStatusDTO().getCode()).getRegstatusCode()==3 )
            {
            setDisableFlag(false);
            }
            return disableFlag;
            }
            else
                return true;
    }
}
