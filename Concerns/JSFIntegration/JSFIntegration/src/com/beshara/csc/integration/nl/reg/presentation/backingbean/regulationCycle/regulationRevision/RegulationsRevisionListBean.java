package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.regulationRevision;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.IRegulationsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.AbstractDecisionListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulation.RegulationMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.AbstractRegulationsListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.AttachmentViewBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.RegulationCycleMaintainBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.csc.sharedutils.business.util.constants.IRegConstants;

import com.beshara.jsfbase.csc.backingbean.AppMainLayout;

import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class RegulationsRevisionListBean extends AbstractRegulationsListBean{
    public RegulationsRevisionListBean() {
        super.setClient(RegClientFactory.getRegulationsClient());
        setPageDTO(RegDTOFactory.createRegulationsDTO());
        setSelectedPageDTO(RegDTOFactory.createRegulationsDTO());
        if (RegulationMaintainBean.isIntegrationPage()) {
            setEditNavigationCase("regulationmaindataedit-integration");
            setAddNavigationCase("regulationmaindata-integration");
        } else {
            setEditNavigationCase("regulationcyclerevisionmaindataedit");
            setAddNavigationCase("regulationcyclerevisionmaindata");
        }

        setEditBeanName("regulationCycleMaintainBean");
        setAddBeanName("regulationCycleMaintainBean");
        setDelConfirmTitleBindingString("#{regResources.DelConfirmTitle}");
        setDelAlertTitleBindingString("#{regResources.DelAlertTitle}");
        setCustomDiv1("cancelDecisionDetailsDiv");
        setDivMainContent("divDecisionEmpMainContentManyCustom");
        
        setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));
        getGetAllDTO().setCurrentStage(2L);
        getGetAllDTO().setRegStatusFlage(0L);
        
        getRegulationsSearchDTO().setCurrentStage(2L);
        getRegulationsSearchDTO().setRegStatusFlage(0L);
        setSaveSortingState(true);
    }
    
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowSearch(false);
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        return app;
    }
    
    public void search() throws DataBaseException, NoResultException {
        setShowAllFlag(false);
        setSearchMode(true);
        getRegulationsSearchDTO().setCurrentStage(2L);
         getRegulationsSearchDTO().setRegStatusFlage(0L);
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
                    generatePagingRequestDTO("regulationsRevisionListBean","searchWithPaging");
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
    public String gotoAttachmentsView() {

        try {
            if (this.getSelectedDTOS() != null && 
                this.getSelectedDTOS().size() == 1) {
                AttachmentViewBean attachmentViewBean = 
                    (AttachmentViewBean)evaluateValueBinding("attachmentCycleViewBean");
                attachmentViewBean.setBackNavigationCase("revisionregulationlist");
                return super.gotoAttachmentsView();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void toCreation() {
        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {
              try {
               ((IRegulationsClient)getClient()).toCreation(this.getSelectedDTOS().get(0));
                if (!isShowAllFlag()) {
                    search();
                } else {
                    setSearchMode(true);
                    setRegulationsSearchDTO(RegDTOFactory.createRegulationSearchDTO());
                    setStringSearchType("false");
                    setSelectedRadio("");
                    getGetAllDTO().setCurrentStage(2L);
                     getGetAllDTO().setRegStatusFlage(0L);
                    setSorting(true);
                    setUpdateMyPagedDataModel(true);
                    if (getSelectedDTOS() != null)
                        getSelectedDTOS().clear();
                    if (getHighlightedDTOS() != null)
                        getHighlightedDTOS().clear();
                    unCheck();
                    setOldPageIndex(0);
                    setCurrentPageIndex(1);
                    this.repositionPage(0);
                    getAll();
                }
             
                getSharedUtil().handleSuccMsg(AbstractRegulationsListBean.RESOURCE_BUNDLE_NAME_REG,"success_revision");
            } catch (SharedApplicationException e) {
                getSharedUtil().handleSuccMsg(AbstractRegulationsListBean.RESOURCE_BUNDLE_NAME_REG,"failed_revision");
                // TODO
            } catch (DataBaseException e) {
                getSharedUtil().handleSuccMsg(AbstractRegulationsListBean.RESOURCE_BUNDLE_NAME_REG,"failed_revision");
                // TODO
            }

        }

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
                         maintainBean.setMaintainMode(1);
                         this.initializeObjectBeforeMaintain();
                         maintainBean.setFinishNavigationCase("revisionregulationlist");
                         maintainBean.setCurrentStage(2l);
                         maintainBean.setPageDTO(this.getPageDTO());
                         return getEditNavigationCase();

                     }
                     return null;
                 }
}
