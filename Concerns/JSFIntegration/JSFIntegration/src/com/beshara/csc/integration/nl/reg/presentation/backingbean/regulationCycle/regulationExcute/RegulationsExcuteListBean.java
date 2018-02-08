package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.regulationExcute;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.nl.reg.business.client.IRegulationsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegulationStatusEntityKey;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.AbstractRegulationsListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.RegulationCycleMaintainBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class RegulationsExcuteListBean extends AbstractRegulationsListBean{
    private boolean disableFlag;
    private boolean cancellationFailed;
    public RegulationsExcuteListBean() {
    
        super.setClient(RegClientFactory.getRegulationsClient());
        setPageDTO(RegDTOFactory.createRegulationsDTO());
        setSelectedPageDTO(RegDTOFactory.createRegulationsDTO());
        if (RegulationCycleMaintainBean.isIntegrationPage()) {
            setEditNavigationCase("regulationmaindataedit-integration");
            setAddNavigationCase("regulationmaindata-integration");
        } else {
            setEditNavigationCase("regulationcycleexcutemaindataedit");
            setAddNavigationCase("regulationcycleexcutemaindata");
        }

        setEditBeanName("regulationCycleMaintainBean");
        setAddBeanName("regulationCycleMaintainBean");
        setDelConfirmTitleBindingString("#{regResources.DelConfirmTitle}");
        setDelAlertTitleBindingString("#{regResources.DelAlertTitle}");
        setCustomDiv1("cancelDecisionDetailsDiv");
        setDivMainContent("divDecisionEmpMainContentManyCustom");
         getGetAllDTO().setCurrentStage(3L);
        getGetAllDTO().setRegStatusFlage(3L);
         getGetAllDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
        
        getRegulationsSearchDTO().setCurrentStage(3L);
        getRegulationsSearchDTO().setRegStatusFlage(3L);
         getRegulationsSearchDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
        setSaveSortingState(true);
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowCustomDiv1(true);
        app.setShowbar(true);
        app.setShowContent1(true);
        app.setShowSearch(false);
        return app;
    }
    
    public void search() throws DataBaseException, NoResultException {

        setSearchMode(true);
        setShowAllFlag(false);
        getRegulationsSearchDTO().setCurrentStage(3L);
        getRegulationsSearchDTO().setUserCodeFlage(CSCSecurityInfoHelper.getUserCode());
        getRegulationsSearchDTO().setRegStatusFlage(3L);
        
        try {
            if (getRegulationsSearchDTO() != null) {
                if (getRegulationsSearchDTO().getName() != null) {
                    if (!(getRegulationsSearchDTO().getName().equals(""))) {
                        getRegulationsSearchDTO().setName(formatSearchString(getRegulationsSearchDTO().getName()));
                    }
                }
                if (isUsingPaging()) {
                    setSorting(true);
                    setUpdateMyPagedDataModel(true);
                    generatePagingRequestDTO("regulationsExcuteListBean","searchWithPaging");
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
                totalList = ((IRegulationsClient)getClient()).getAllWithParameter(getGetAllDTO());
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
    public String goToCancelRegulation() {
        CancelRegulationListBean cancelRegulationListBean = (CancelRegulationListBean)evaluateValueBinding("cancelRegulationCycleListBean");
        try {
            cancelRegulationListBean.setPageDTO(getClient().getByIdInCenter(getSelectedDTOS().get(0).getCode()));
        } catch (SharedApplicationException e) {
          e.printStackTrace();  // TODO
        } catch (DataBaseException e) {
           e.printStackTrace(); // TODO
        }
        if(((IRegulationsDTO)getSelectedDTOS().get(0)).getRegCancelReasonsDTOList()!=null){
            cancelRegulationListBean.setCurrentDetails(((IRegulationsDTO)getSelectedDTOS().get(0)).getRegCancelReasonsDTOList());
            cancelRegulationListBean.setMyTableData(cancelRegulationListBean.getCurrentDetails());
        }else
        {
            cancelRegulationListBean.setCurrentDetails(new ArrayList());
        }
        try {
            cancelRegulationListBean.setAvailableDetails(getCancelReasonsList()) ;
            cancelRegulationListBean.setCancelReasonsList(cancelRegulationListBean.getAvailableDetails());
        } catch (DataBaseException e) {
          e.printStackTrace();  // TODO
        } catch (SharedApplicationException e) {
            e.printStackTrace();  // TODO
        }
        return"cancelregulationcyclelist";
    }

    public void setDisableFlag(boolean disableFlag) {
        this.disableFlag = disableFlag;
    }

    public boolean isDisableFlag() {
    if(getSelectedDTOS()!=null && getSelectedDTOS().size()==1)
     {   if(((IRegulationStatusEntityKey)((IRegulationsDTO)getSelectedDTOS().get(0)).getStatusDto().getCode()).getRegstatusCode()==1 )
        {
        setDisableFlag(true);
        }
        if(((IRegulationStatusEntityKey)((IRegulationsDTO)getSelectedDTOS().get(0)).getStatusDto().getCode()).getRegstatusCode()==3 )
        {
        setDisableFlag(false);
        }
        return disableFlag;
        }
        else
            return true;
    }
    public void cancelRegulation() throws DataBaseException {
                cancellationFailed = false;
                System.out.println("Calling cancelRegulation()...");
                try {
                    cancellationFailed =
                            !RegClientFactory.getRegulationsClient().cancelRegulationsList(getSelectedDTOS());
                    System.out.println("Canceled Regulation...");
                } catch (SharedApplicationException e) {
                    cancellationFailed = true;
                    System.err.println(e.getMessage());
                    setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                    return;
                }
    
                IBasicDTO canceldDTO = getSelectedDTOS().get(0);
                this.setSelectedRadio("");
                super.cancelSearch();
                this.setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));
                this.setPageDTO(canceldDTO);
                this.highlighDataTable(BEAN_NAME);
                if (this.getSelectedDTOS() != null) {
                    this.getSelectedDTOS().clear();
                }
                setCancelDivDisplayed(false); 
                getSharedUtil().handleSuccMsg(RESOURCE_BUNDLE_NAME_REG,
                                               "cancellation_succeeded");
    }
    public void setCancellationFailed(boolean cancellationFailed) {
        this.cancellationFailed = cancellationFailed;
    }

    public boolean isCancellationFailed() {
        return cancellationFailed;
    }
    public String goEdit() throws DataBaseException, 
                                  SharedApplicationException {

        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {

            RegulationCycleMaintainBean maintainBean = 
                (RegulationCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                                                  this.getAddBeanName() + 
                                                                                                                  "}").getValue(FacesContext.getCurrentInstance());

            setPageDTO(getClient().getByIdInCenter(this.getSelectedDTOS().get(0).getCode()));
            ((IRegulationsDTO)this.getPageDTO()).setCurrentStage(3l);
            maintainBean.setMaintainMode(1);
            this.initializeObjectBeforeMaintain();
            maintainBean.setFinishNavigationCase("executeregulationlist");
            maintainBean.setCurrentStage(3l);
            maintainBean.setPageDTO(this.getPageDTO());
            return getEditNavigationCase();

        }
        return null;
    }
}
