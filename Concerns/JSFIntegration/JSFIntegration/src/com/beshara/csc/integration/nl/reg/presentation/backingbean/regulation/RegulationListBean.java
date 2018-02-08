package com.beshara.csc.nl.reg.presentation.backingbean.regulation;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.IDecisionMakerTypesClient;
import com.beshara.csc.inf.business.client.IYearsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.reg.business.client.ICancelReasonsClient;
import com.beshara.csc.nl.reg.business.client.IRegulationScopesClient;
import com.beshara.csc.nl.reg.business.client.IRegulationStatusClient;
import com.beshara.csc.nl.reg.business.client.IRegulationsClient;
import com.beshara.csc.nl.reg.business.client.ITypesClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IModuleRelationsDTO;
import com.beshara.csc.nl.reg.business.dto.IPublishDTO;
import com.beshara.csc.nl.reg.business.dto.IREGDedignTablesDTO;
import com.beshara.csc.nl.reg.business.dto.IREGRegulationMaterialsDTO;
import com.beshara.csc.nl.reg.business.dto.IRefrencesDTO;
import com.beshara.csc.nl.reg.business.dto.IRegCancelReasonsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegualtionSubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationMinistriesDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegulationStatusEntityKey;
import com.beshara.csc.nl.reg.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyListBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class RegulationListBean extends ManyToManyListBaseBean {
    protected static final String BEAN_NAME = "regulationListBean";
    private static final String RESOURCE_BUNDLE_NAME_REG = 
        "com.beshara.csc.nl.reg.presentation.resources.reg";
    private static final String RESOURCE_BUNDLE_KEY_CANCELLATION_SUCCEDED = 
        "cancellation_succeeded";
    private static final String RESOURCE_BUNDLE_KEY_CANCEL_REGULATION = 
        "cancel_regulation";
    private static final String RESOURCE_BUNDLE_KEY_REGULATION_MODIFICATION_ERROR_MESSAGE = 
        "regulation_modfication_error_message";
    private static final String JS_CHANGE_VISIBILITY_DIV_CUSTOM_DIV1 = 
        "changeVisibilityDiv(window.blocker,window.customDiv1);";

    private String stringSearchType = "false";
    private boolean tabelPageHaveCanceled;

    private IRegulationSearchDTO regulationSearchDTO = 
       RegDTOFactory.createRegulationSearchDTO();

    private IRegulationsClient iRegulationsClient = 
        RegClientFactory.getRegulationsClient();

    private ITypesClient typesClient = RegClientFactory.getTypesClient();

    private IYearsClient yearsClient = InfClientFactory.getYearsClient();

    private IDecisionMakerTypesClient decisionMakersClient = 
        InfClientFactory.getDecisionMakerTypesClient();

    private IRegulationStatusClient statusesClient = 
        RegClientFactory.getRegulationStatusClient();

    private IRegulationScopesClient scopesClient = 
        RegClientFactory.getRegulationScopesClient();

    private ICancelReasonsClient cancelReasonsClient = 
        RegClientFactory.getCancelReasonsClient();


    private List typesList = new ArrayList();
    private List yearsList = new ArrayList();
    private List decisionMakersList = new ArrayList();
    private List statusesList = new ArrayList();
    private List scopesList = new ArrayList();

    private List cancelReasonsList = new ArrayList();

    private boolean cancelDivDisplayed;
    private boolean cancellationFailed;

    private boolean suggestedFlag = false;
    private boolean validStatus = true;

    private boolean typeColumnVisible = true;
    private boolean yearColumnVisible = true;
    private boolean numberColumnVisible = true;
    private boolean titleColumnVisible;
    private boolean decisionMakerColumnVisible;
    private boolean dateColumnVisible = true;
    private boolean applyDateColumnVisible = true;
    private boolean cancelDateColumnVisible;
    private boolean applyCancelDateColumnVisible;
    private boolean statusColumnVisible = true;

    private static final String  VIEW_ATTACHMENT_NC="VIEW_ATTACHMENT_PAGE";
    public RegulationListBean() {

        super.setClient(RegClientFactory.getRegulationsClient());
        super.setPageDTO(RegDTOFactory.createRegulationsDTO());
        setSaveSortingState(true);
        super.setSelectedPageDTO(RegDTOFactory.createRegulationsDTO());

        if (RegulationMaintainBean.isIntegrationPage()) {
            setEditNavigationCase("regulationmaindataedit-integration");
            setAddNavigationCase("regulationmaindata-integration");
        } else {
            setEditNavigationCase("regulationmaindataedit");
            setAddNavigationCase("regulationmaindata");
        }

        setEditBeanName("regulationMaintainBean");
        setAddBeanName("regulationMaintainBean");
        setDelConfirmTitleBindingString("#{regResources.DelConfirmTitle}");
        setDelAlertTitleBindingString("#{regResources.DelAlertTitle}");
        setCustomDiv1("customDeleteDiv");
        setIndexArray(new boolean[] { true, true, true, false, false, true, 
                                      true, false, false, true });
        setUsingPaging(true);
        setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));
        
    }

    public String goView() throws DataBaseException, 
                                  SharedApplicationException {
        setSelectedDTOS(getIntegrationBean().getSelectedDTOFrom());
        setPageDTO(getIntegrationBean().getSelectedDTOFrom().get(0));
        RegulationMaintainBean regulationMaintainBean = 
            RegulationMaintainBean.getInstance();
        super.goEdit();
        regulationMaintainBean.setMaintainMode(2); // view mode
        return getEditNavigationCase();
    }

    public void setValidStatus(boolean validStatus) {
        this.validStatus = validStatus;
    }

    public boolean isValidStatus() {
        return validStatus;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyListPage();
        

        return app;
    }

    public static RegulationListBean getInstance() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (RegulationListBean)app.createValueBinding("#{" + BEAN_NAME + 
                                                          "}").getValue(ctx);
    }

    public String goCopy() throws DataBaseException, 
                                  SharedApplicationException {

        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {

            RegulationMaintainBean regulationMaintainBean = 
                RegulationMaintainBean.getInstance();

            this.setPageDTO(getClient().getById(this.getSelectedDTOS().get(0).getCode()));

            this.initializeObjectBeforeMaintain();
            this.initializeObjectBeforeCopying();
            regulationMaintainBean.setCopyFlage(true);
            regulationMaintainBean.setMaintainMode(1);
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

    public void setStringSearchType(String stringSearchType) {
        this.stringSearchType = stringSearchType;
    }

    public String getStringSearchType() {
        return stringSearchType;
    }


    public void setRegulationSearchDTO(IRegulationSearchDTO regulationSearchDTO) {
        this.regulationSearchDTO = regulationSearchDTO;
    }

    public IRegulationSearchDTO getRegulationSearchDTO() {
        return regulationSearchDTO;
    }

    public void setTypesList(List typesList) {
        this.typesList = typesList;
    }

    public List getTypesList() throws DataBaseException, 
                                      SharedApplicationException {

        typesList = typesClient.getCodeNameCenter();
        if (typesList == null) {
            typesList = new ArrayList();
        }

        return typesList;
    }

    public void setYearsList(List yearsList) {
        this.yearsList = yearsList;
    }

    public List getYearsList() throws DataBaseException, 
                                      SharedApplicationException {

        yearsList = yearsClient.getCodeNameInCenter();
        if (yearsList == null) {
            yearsList = new ArrayList();
        }

        return yearsList;
    }

    public void setDecisionMakersList(List decisionMakersList) {
        this.decisionMakersList = decisionMakersList;
    }

    public List getDecisionMakersList() throws DataBaseException, 
                                               SharedApplicationException {

        decisionMakersList = decisionMakersClient.getCodeName();
        if (decisionMakersList == null) {
            decisionMakersList = new ArrayList();
        }

        return decisionMakersList;
    }

    public void setStatusesList(List statusesList) {
        this.statusesList = statusesList;
    }

    public List getStatusesList() throws DataBaseException, 
                                         SharedApplicationException {

        statusesList = statusesClient.getCodeNameCenter();
        if (statusesList == null) {
            statusesList = new ArrayList();
        }

        return statusesList;
    }

    public void setScopesList(List scopesList) {
        this.scopesList = scopesList;
    }

    public List getScopesList()  
                                        {

        try {
            scopesList = scopesClient.getCodeNameCenter();
        } catch (Exception e) {
          e.printStackTrace();
        }
        if (scopesList == null) {
            scopesList = new ArrayList();
        }

        return scopesList;
    }

    public void setCancelReasonsList(List cancelReasonsList) {
        this.cancelReasonsList = cancelReasonsList;
    }

    public List getCancelReasonsList() throws DataBaseException, 
                                              SharedApplicationException {

        cancelReasonsList = cancelReasonsClient.getCodeNameInCenter();
        if (cancelReasonsList == null) {
            cancelReasonsList = new ArrayList();
        }

        return cancelReasonsList;
    }

    // ************************** End Constructors *****************************

    //this to initialize the arrays of the dto in case of add

    public void initializeObjectBeforeMaintain() {

        IRegulationsDTO dto = (IRegulationsDTO)getPageDTO();
        if (dto.getPublishDTOList() == null) {
            dto.setPublishDTOList(new ArrayList<IPublishDTO>());
        }

        if (dto.getMinistriesDTOList() == null) {
            dto.setMinistriesDTOList(new ArrayList<IRegulationMinistriesDTO>());
        }

        if (dto.getSubjectsDTOList() == null) {
            dto.setSubjectsDTOList(new ArrayList<IRegualtionSubjectsDTO>());
        }

        if (dto.getReferencesDTOList() == null) {
            dto.setReferencesDTOList(new ArrayList<IRefrencesDTO>());
        }

        if (dto.getModuleRelationsDTOList() == null) {
            dto.setModuleRelationsDTOList(new ArrayList<IModuleRelationsDTO>());
        }

        if (dto.getRegDedignTablesDTOList() == null) {
            dto.setRegDedignTablesDTOList(new ArrayList<IREGDedignTablesDTO>());
        }

        if (dto.getRegRegulationMaterialsDTOList() == null) {
            dto.setRegRegulationMaterialsDTOList(new ArrayList<IREGRegulationMaterialsDTO>());
        }


        setPageDTO(dto);

    }

    public void showHideRegType() {
        typeColumnVisible = !typeColumnVisible;
        showHideColumn("typesDto-name_column");
    }

    public void showHideRegYear() {
        yearColumnVisible = !yearColumnVisible;
        showHideColumn("yearsDto-name_column");
    }

    public void showHideRegNumber() {
        numberColumnVisible = !numberColumnVisible;
        showHideColumn("code-regulationNumber_column");
    }

    public void showHideRegTitle() {
        titleColumnVisible = !titleColumnVisible;
        showHideColumn("regulationTitle_column");
    }

    public void showHideDecisionMaker() {
        decisionMakerColumnVisible = !decisionMakerColumnVisible;
        showHideColumn("decisionMakerDTO-name_column");
    }

    public void showHideRegDate() {
        dateColumnVisible = !dateColumnVisible;
        showHideColumn("regulationDate_column");
    }

    public void showHideApplyDate() {
        applyDateColumnVisible = !applyDateColumnVisible;
        showHideColumn("regulationAppliedDate_column");
    }

    public void showHideCancelDate() {
        cancelDateColumnVisible = !cancelDateColumnVisible;
        showHideColumn("regCancelDate_column");
    }

    public void showHideCancelApplyDate() {
        applyCancelDateColumnVisible = !applyCancelDateColumnVisible;
        showHideColumn("regulationCancelAppliedDate_column");
    }

    public void showHideStatus() {
        statusColumnVisible = !statusColumnVisible;
        showHideColumn("statusDto-name_column");
    }

    public void search() throws DataBaseException, NoResultException {

        System.out.println("Calling search()...");
        this.setSearchMode(true);

        if (isUsingPaging()) {

            setUpdateMyPagedDataModel(true);

            setPagingRequestDTO(new PagingRequestDTO(BEAN_NAME, 
                                                     "searchWithPaging"));

            setOldPageIndex(0);
            setCurrentPageIndex(1);

        } else {

            setMyTableData(getSearchResult());

        }
        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }

        if (getHighlightedDTOS() != null) {
            getHighlightedDTOS().clear();
        }
        this.setSelectedRadio("");
        this.repositionPage(0);
        this.setStringSearchType("false");
    }

    public PagingResponseDTO searchWithPaging(PagingRequestDTO requestDTO) {
        requestDTO = null; //unused
        List searchResult = getSearchResult();

        getPagingBean().preUpdateDataModel();

        if (!isResettedPageIndex()) {
            resetPageIndex();
            setResettedPageIndex(true);
        }

        return new PagingResponseDTO(searchResult);
    }

    public List<IBasicDTO> getSearchResult() {
        List<IBasicDTO> result = new ArrayList<IBasicDTO>();
        try {

            if (regulationSearchDTO != null) {
                if (regulationSearchDTO.getName() != null) {
                    if (!(regulationSearchDTO.getName().equals(""))) {
                        regulationSearchDTO.setName(formatSearchString(regulationSearchDTO.getName()));
                    }
                }
            }

            result = iRegulationsClient.searchCenter(regulationSearchDTO);
        } catch (Exception db) {
            this.setStringSearchType("false");
        }
        return result;
    }

    public void cancelSearch() throws DataBaseException {

        System.out.println("Calling cancelSearch()...");
        super.cancelSearch();
        this.setSelectedRadio("");
        this.setStringSearchType("false");
        regulationSearchDTO =RegDTOFactory.createRegulationSearchDTO();
        System.out.println("SelectedListSize====" + getSelectedListSize());

    }

    public String back() throws DataBaseException {

        setSearchQuery("");
        setRegulationSearchDTO(RegDTOFactory.createRegulationSearchDTO());
        this.cancelSearch();
        setJavaScriptCaller("ignoremyFormValidation();hideLookUpDiv(window.blocker,window.divSearch,null,null);foucsAddbuttonOnList();");
        return null;

    }

    public void cancelRegulation() throws DataBaseException {
        cancellationFailed = false;
        System.out.println("Calling cancelRegulation()...");
        try {
            cancellationFailed = 
                    !iRegulationsClient.cancelRegulationsList(getSelectedDTOS());
            System.out.println("Canceled Regulation...");
        } catch (SharedApplicationException e) {
            cancellationFailed = true;
            System.err.println(e.getMessage());
            setJavaScriptCaller(JS_CHANGE_VISIBILITY_DIV_CUSTOM_DIV1);
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
        cancelDivDisplayed = false;
        getSharedUtil().handleSuccMsg(RESOURCE_BUNDLE_NAME_REG, 
                                      RESOURCE_BUNDLE_KEY_CANCELLATION_SUCCEDED);
    }

    /*public void preCancelRegulation() {
     *             IRegulationsDTO selected = (IRegulationsDTO)getSelectedDTOS().get(0);
            if (((IRegulationStatusEntityKey)selected.getStatusDto().getCode()).getRegstatusCode().equals(ISystemConstant.REGULATION_STATUS_SUGGESTED)){
                return;
            }
        loadSelectedDTOS();
    }*/

    public String getCustomDiv1Title() {
        return BeansUtil.getResource(RESOURCE_BUNDLE_NAME_REG, 
                                     RESOURCE_BUNDLE_KEY_CANCEL_REGULATION);
    }

    public void setCurrentDate(Date currentDate) {
        currentDate = null; // unused
    }

    public Date getCurrentDate() {
        return new Date((new java.util.Date()).getTime());
    }

    public Long getStatusCanceled() {
        return ISystemConstant.REGULATION_STATUS_CANCELED;
    }

    public boolean isTabelPageHaveCanceled() throws DataBaseException {
        int rowIndex = this.getMyDataTable().getFirst();
        int lastRowIndex = rowIndex + this.getMyDataTable().getRows() - 1;
        List myTableData = getMyTableData();
        int maxRowIndex = myTableData.size() - 1;

        if (lastRowIndex > maxRowIndex) {
            lastRowIndex = maxRowIndex;
        }

        while (rowIndex <= lastRowIndex) {
            IRegulationsDTO selected = 
                (IRegulationsDTO)myTableData.get(rowIndex);
            if (((IRegulationStatusEntityKey)selected.getStatusDto().getCode()).getRegstatusCode().equals(ISystemConstant.REGULATION_STATUS_CANCELED)) {
                return true;
            }
            ++rowIndex;
        }
        return false;
    }

    public void setTabelPageHaveCanceled(boolean tabelPageHaveCanceled) {
        this.tabelPageHaveCanceled = tabelPageHaveCanceled;
    }

    public void setCancellationFailed(boolean cancellationFailed) {
        this.cancellationFailed = cancellationFailed;
    }

    public boolean isCancellationFailed() {
        return cancellationFailed;
    }

    public void setSuggestedFlag(boolean suggestedFlag) {
        this.suggestedFlag = suggestedFlag;
    }

    public boolean isSuggestedFlag() {
        return suggestedFlag;
    }

    public void selectedCheckboxs(ActionEvent event) throws Exception {
        super.selectedCheckboxs(event);
        if (this.getSelectedDTOS().size() == 1) {
            IRegulationsDTO selectedRegDto = 
                (IRegulationsDTO)this.getSelectedDTOS().get(0);

            if (((IRegulationStatusEntityKey)selectedRegDto.getStatusDto().getCode()).getRegstatusCode().equals(ISystemConstant.REGULATION_STATUS_SUGGESTED)) {
                setSuggestedFlag(false);
            } else {
                setSuggestedFlag(true);
            }
        }

        if (this.getSelectedDTOS().size() >= 1) {
            int i = 0;
            while (getSelectedDTOS().size() > i) {
                if (((IRegulationStatusEntityKey)((IRegulationsDTO)getSelectedDTOS().get(i)).getStatusDto().getCode()).getRegstatusCode().equals(ISystemConstant.REGULATION_STATUS_VALID)) {
                    setValidStatus(true);
                    break;
                } else {
                    setValidStatus(false);
                }
                i++;
            }
        }
    }

    public void selectedRadioButton(ActionEvent event) throws Exception {
        super.selectedRadioButton(event);

        if (this.getSelectedDTOS().size() == 1) {
            IRegulationsDTO selectedRegDto = 
                (IRegulationsDTO)this.getSelectedDTOS().get(0);
            Long regstatusCode = 
                Long.parseLong(selectedRegDto.getStatusDto().getCode().getKey().toString());

            if (regstatusCode.equals(ISystemConstant.REGULATION_STATUS_SUGGESTED)) {
                setSuggestedFlag(true);
            } else {
                setSuggestedFlag(false);
            }
            if (regstatusCode.equals(ISystemConstant.REGULATION_STATUS_VALID)) {
                setValidStatus(true);
            } else {
                setValidStatus(false);
            }
        }
    }

    public boolean isEnableCancelMenuItem() {
        return isValidStatus() || isSuggestedFlag();
    }

    public void setTypeColumnVisible(boolean typeColumnVisible) {
        this.typeColumnVisible = typeColumnVisible;
    }

    public boolean isTypeColumnVisible() {
        return typeColumnVisible;
    }

    public void setYearColumnVisible(boolean yearColumnVisible) {
        this.yearColumnVisible = yearColumnVisible;
    }

    public boolean isYearColumnVisible() {
        return yearColumnVisible;
    }

    public void setNumberColumnVisible(boolean numberColumnVisible) {
        this.numberColumnVisible = numberColumnVisible;
    }

    public boolean isNumberColumnVisible() {
        return numberColumnVisible;
    }

    public void setTitleColumnVisible(boolean titleColumnVisible) {
        this.titleColumnVisible = titleColumnVisible;
    }

    public boolean isTitleColumnVisible() {
        return titleColumnVisible;
    }

    public void setDecisionMakerColumnVisible(boolean decisionMakerColumnVisible) {
        this.decisionMakerColumnVisible = decisionMakerColumnVisible;
    }

    public boolean isDecisionMakerColumnVisible() {
        return decisionMakerColumnVisible;
    }

    public void setDateColumnVisible(boolean dateColumnVisible) {
        this.dateColumnVisible = dateColumnVisible;
    }

    public boolean isDateColumnVisible() {
        return dateColumnVisible;
    }

    public void setApplyDateColumnVisible(boolean applyDateColumnVisible) {
        this.applyDateColumnVisible = applyDateColumnVisible;
    }

    public boolean isApplyDateColumnVisible() {
        return applyDateColumnVisible;
    }

    public void setCancelDateColumnVisible(boolean cancelDateColumnVisible) {
        this.cancelDateColumnVisible = cancelDateColumnVisible;
    }

    public boolean isCancelDateColumnVisible() {
        return cancelDateColumnVisible;
    }

    public void setApplyCancelDateColumnVisible(boolean applyCancelDateColumnVisible) {
        this.applyCancelDateColumnVisible = applyCancelDateColumnVisible;
    }

    public boolean isApplyCancelDateColumnVisible() {
        return applyCancelDateColumnVisible;
    }

    public void setStatusColumnVisible(boolean statusColumnVisible) {
        this.statusColumnVisible = statusColumnVisible;
    }

    public boolean isStatusColumnVisible() {
        return statusColumnVisible;
    }

    public String goToCancelRegulation() {
        CancelRegulationListBean cancelRegulationListBean = (CancelRegulationListBean)evaluateValueBinding("cancelRegulationListBean");
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
        return"cancelregulationlist";
    }

    public void hideCancelDiv() {
        setCancelDivDisplayed(false);
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

    public void setCancelDivDisplayed(boolean cancelDivDisplayed) {
        this.cancelDivDisplayed = cancelDivDisplayed;
    }

    public boolean isCancelDivDisplayed() {
        return cancelDivDisplayed;
    }
    
    public String gotoAttachmentsView(){
        try {
            if (this.getSelectedDTOS() != null &&  this.getSelectedDTOS().size() == 1) {
                AttachmentViewBean attachmentViewBean = (AttachmentViewBean)evaluateValueBinding("attachmentViewBean");
                attachmentViewBean.setSelectedRegulationsDTO((IRegulationsDTO)this.getSelectedDTOS().get(0));
                attachmentViewBean.fillTablesList();
                return "VIEW_ATTACHMENT_PAGE";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
       return null; 
    }
}
