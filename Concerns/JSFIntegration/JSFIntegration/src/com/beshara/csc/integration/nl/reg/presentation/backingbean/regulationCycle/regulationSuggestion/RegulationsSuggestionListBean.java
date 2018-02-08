package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.regulationSuggestion;

import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.nl.reg.business.client.IRegulationsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.regulation.RegulationMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.AbstractRegulationsListBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.AttachmentViewBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.RegulationCycleMaintainBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemCanNotBeDeletedException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.csc.sharedutils.business.util.constants.IRegConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;

import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;


public class RegulationsSuggestionListBean extends AbstractRegulationsListBean {

    private boolean disableCopyFlag;
    public RegulationsSuggestionListBean() {
        super.setClient(RegClientFactory.getRegulationsClient());
        setPageDTO(RegDTOFactory.createRegulationsDTO());
        setSelectedPageDTO(RegDTOFactory.createRegulationsDTO());
        if (RegulationMaintainBean.isIntegrationPage()) {
            setEditNavigationCase("regulationmaindataedit-integration");
            setAddNavigationCase("regulationmaindata-integration");
        } else {
            setEditNavigationCase("regulationcyclemaindataedit");
            setAddNavigationCase("regulationcyclemaindata");
        }
        setDivMainContent("sugesstionDivMainContentMany");
        setEditBeanName("regulationCycleMaintainBean");
        setAddBeanName("regulationCycleMaintainBean");
        setDelConfirmTitleBindingString("#{regResources.DelConfirmTitle}");
        setDelAlertTitleBindingString("#{regResources.DelAlertTitle}");
        setCustomDiv1("cancelDecisionDetailsDiv");
        setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));

        getGetAllDTO().setCurrentStage(1L);
        getGetAllDTO().setCreatedBy(CSCSecurityInfoHelper.getUserCode());
        getRegulationsSearchDTO().setCurrentStage(1L);
        getRegulationsSearchDTO().setCreatedBy(CSCSecurityInfoHelper.getUserCode());
        setSaveSortingState(true);
         //disableOperationBarButton();
    }
    
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
       app = super.appMainLayoutBuilder();
       app.setShowContent1(true);
        app.setShowSearch(true);
        return app;
    }
    
    public void search() throws DataBaseException, NoResultException {

        setSearchMode(true);
        getRegulationsSearchDTO().setCurrentStage(1L);
        getRegulationsSearchDTO().setCreatedBy(CSCSecurityInfoHelper.getUserCode());
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
                    generatePagingRequestDTO("regulationsSuggestionListBean","searchWithPaging");
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
     if(getSelectedRegTypeKey()==IRegConstants.REGULATION_STATUS_SUGGESTION || getSelectedRegTypeKey()==IRegConstants.REGULATION_STATUS_READY_REVISION )
     {
     setDisabledButtonFlag(false);
     }
     else
     {
     setDisabledButtonFlag(true);
     }
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

    public void toRevision() {
        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {
             try {
                ((IRegulationsClient)getClient()).toRevision(this.getSelectedDTOS().get(0));
                if(isSearchMode())
                {
             search();
                     }
                else
                {
                getMyDataTable().setFirst(0);
                if (getSelectedDTOS() != null)
                    getSelectedDTOS().clear();
                if (getHighlightedDTOS() != null)
                    getHighlightedDTOS().clear();
                unCheck();
                    setSorting(true);
                    setUpdateMyPagedDataModel(true);
                     setOldPageIndex(0);
                    setCurrentPageIndex(1);
                    if (getSelectedDTOS() != null)
                        getSelectedDTOS().clear();
                    if (getHighlightedDTOS() != null)
                        getHighlightedDTOS().clear();
                    this.repositionPage(0);
                    this.setStringSearchType("false");
                    setSelectedRadio("");
                    this.getAll();
                }
                getSharedUtil().handleSuccMsg(AbstractRegulationsListBean.RESOURCE_BUNDLE_NAME_REG,"send_to_revision");
            } catch (SharedApplicationException e) {
                getSharedUtil().handleSuccMsg(AbstractRegulationsListBean.RESOURCE_BUNDLE_NAME_REG,"dosent_send_to_revision");
                // TODO
            } catch (DataBaseException e) {
                getSharedUtil().handleSuccMsg(AbstractRegulationsListBean.RESOURCE_BUNDLE_NAME_REG,"dosent_send_to_revision");
                // TODO
            }

        }

    }

    public String gotoAttachmentsView() {

        try {
            if (this.getSelectedDTOS() != null && 
                this.getSelectedDTOS().size() == 1) {
                AttachmentViewBean attachmentViewBean = 
                    (AttachmentViewBean)evaluateValueBinding("attachmentCycleViewBean");
                attachmentViewBean.setBackNavigationCase("sugesstionregulationlist");
                return super.gotoAttachmentsView();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String goAdd() {

        RegulationCycleMaintainBean maintainBean = 
            (RegulationCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                                              this.getAddBeanName() + 
                                                                                                              "}").getValue(FacesContext.getCurrentInstance());
        maintainBean.setMaintainMode(0);
        this.initializeObjectBeforeMaintain();
        ((IRegulationsDTO)this.getPageDTO()).setRegulationNumber(0l);
        String [] s=SharedUtils.returnMonthYearFromTimeStampDate(SharedUtils.getCurrentTimestamp());
        ((IRegulationsDTO)this.getPageDTO()).setYearsDTOKey(s[1]);
        ((IRegulationsDTO)this.getPageDTO()).setRegulationAppliedDate(SharedUtils.getCurrentTimestamp());   
        ((IRegulationsDTO)this.getPageDTO()).setStatusDTOKey("4");
        ((IRegulationsDTO)this.getPageDTO()).setDecisionMakerDTOKey("0");
        ((IRegulationsDTO)this.getPageDTO()).setCurrentStage(1l);
        maintainBean.setFinishNavigationCase("sugesstionregulationlist");
        maintainBean.setCurrentStage(1l);
        maintainBean.setPageDTO(this.getPageDTO());
        
        return getAddNavigationCase();
    }
    public String goCopy() throws DataBaseException, 
                                  SharedApplicationException {

        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {

            RegulationCycleMaintainBean regulationMaintainBean = 
                RegulationCycleMaintainBean.getInstance();

            this.setPageDTO(getClient().getByIdInCenter(this.getSelectedDTOS().get(0).getCode()));
                 this.initializeObjectBeforeMaintain();
            this.initializeObjectBeforeCopying();
            regulationMaintainBean.setCopyFlage(true);
            regulationMaintainBean.setMaintainMode(1);
            regulationMaintainBean.setCurrentStage(1l);
            regulationMaintainBean.setFinishNavigationCase("sugesstionregulationlist");
            ((IRegulationsDTO) this.getPageDTO()).setRegulationDate(SharedUtils.getCurrentTimestamp());
            String [] s=SharedUtils.returnMonthYearFromTimeStampDate(SharedUtils.getCurrentTimestamp());
              ((IRegulationsDTO)this.getPageDTO()).setYearsDTOKey(s[1]);
              ((IRegulationsDTO)this.getPageDTO()).setRegulationAppliedDate(SharedUtils.getCurrentTimestamp());
            ((IRegulationsDTO)this.getPageDTO()).setRegulationNumber(0l);  
            ((IRegulationsDTO)this.getPageDTO()).setStatusDTOKey("4");
            ((IRegulationsDTO)this.getPageDTO()).setDecisionMakerDTOKey("0");
            ((IRegulationsDTO)this.getPageDTO()).setCurrentStage(1l);
            regulationMaintainBean.setPageDTO(this.getPageDTO());
            if (((IRegulationsDTO)this.getPageDTO()).getSubjectsDTOList() != 
                null && 
                ((IRegulationsDTO)this.getPageDTO()).getSubjectsDTOList().size() > 
                0)
                regulationMaintainBean.setCopyRegualationWithSubject(true);
            return this.getAddNavigationCase();

        }
        return null;
    }
    private void initializeObjectBeforeCopying() {
        IRegulationsDTO regulationsDTO = (IRegulationsDTO)this.getPageDTO();
        regulationsDTO.setCode(null);
        regulationsDTO.setRegulationNumber(null);
        regulationsDTO.setTypesDTOKey(null);
        regulationsDTO.setYearsDTOKey(null);
        regulationsDTO.setParentRegulationDTO(null);
        regulationsDTO.setRegulationImage(null);
        regulationsDTO.setRegCancelUser(null);
        regulationsDTO.setRegCancelDate(null);
        regulationsDTO.setCancelMakerDTO(null);
        regulationsDTO.setCancelReasonDTO(null);
        regulationsDTO.setRegulationCancelAppliedDate(null);
        regulationsDTO.setStatusDTOKey(ISystemConstant.REGULATION_STATUS_VALID + 
                                       "");
        regulationsDTO.setModuleRelationsDTOList(new ArrayList());
        regulationsDTO.setRegRegulationMaterialsDTOList(new ArrayList());
        regulationsDTO.setRegDedignTablesDTOList(new ArrayList());
        this.setPageDTO(regulationsDTO);
    }
    public String goView() throws DataBaseException, 
                                  SharedApplicationException {

        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {

            RegulationCycleMaintainBean maintainBean = 
                (RegulationCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                                                  this.getAddBeanName() + 
                                                                                                                  "}").getValue(FacesContext.getCurrentInstance());

            setPageDTO(getClient().getByIdInCenter(this.getSelectedDTOS().get(0).getCode()));
            maintainBean.setMaintainMode(2);
            this.initializeObjectBeforeMaintain();
            maintainBean.setCurrentStage(0l);
            maintainBean.setFinishNavigationCase("sugesstionregulationlist");
            maintainBean.setPageDTO(this.getPageDTO());
            return "regulationcyclemaindataview";

        }
        return null;
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
            maintainBean.setFinishNavigationCase("sugesstionregulationlist");
            maintainBean.setCurrentStage(1l);
            maintainBean.setPageDTO(this.getPageDTO());
            return getEditNavigationCase();

        }
        return null;
    }
    public void delete() throws DataBaseException, ItemNotFoundException {
        SharedUtilBean shared_util = getSharedUtil();
        System.out.println("Calling delete()...");
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ex = ctx.getExternalContext();
        HttpSession session = (HttpSession)ex.getSession(true);

        if (getSelectedDTOS() == null)
            setSelectedDTOS(loadSelectedDTOS());

        //            for (IBasicDTO dto: getSelectedDTOS()) {


        try {
           IRegulationsDTO regDto = (IRegulationsDTO)getClient().getByIdInCenter(this.getSelectedDTOS().get(0).getCode());
            this.getSelectedDTOS().clear();
            this.getSelectedDTOS().add(regDto);
            setDeleteStatusDTOS((getClient()).remove(this.getSelectedDTOS())); 
            if (getMyDataTable() != null && 
                getMyDataTable().getRowCount() != 0) {
                getMyDataTable().setFirst(0);
            }
        }
        catch (ItemCanNotBeDeletedException db) {
                   shared_util.setErrMsgValue("ItemCanNotBeDeletedException");
                   getSharedUtil().handleException(db);
               }        
        catch (DataBaseException db) {
            //shared_util.setErrMsgValue(db.getMessage());
            getSharedUtil().handleException(db);
        } catch (ItemNotFoundException item) {
            shared_util.setErrMsgValue("ItemCanNotBeDeletedException");
        } catch (SharedApplicationException e) {
            //shared_util.setErrMsgValue(e.getMessage());
            getSharedUtil().handleException(e);
        } catch (Exception e) {
            //getSharedUtil().handleException(e);
            e.printStackTrace();
        }


        //        }

        this.getSelectedDTOS().clear();
        session.setAttribute("selectedDTOS", null);
        cancelSearch();
        restoreTablePosition();


    }
}
