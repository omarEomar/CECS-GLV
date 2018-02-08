package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle;

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
import com.beshara.csc.nl.reg.business.dto.IRegualtionSubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationMinistriesDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegulationStatusEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.csc.sharedutils.business.util.constants.IRegConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyListBaseBean;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class AbstractRegulationsListBean extends ManyToManyListBaseBean {
    protected static final String BEAN_NAME = "regulationsAbstractListBean";
    protected static final String RESOURCE_BUNDLE_NAME_REG = 
        "com.beshara.csc.nl.reg.presentation.resources.reg";
    private boolean tabelPageHaveCanceled;
    private boolean validStatus = true;

    private String stringSearchType = "false";

    private IRegulationSearchDTO regulationsSearchDTO = 
        RegDTOFactory.createRegulationSearchDTO();

    private IRegulationsClient searchClient = 
        RegClientFactory.getRegulationsClient();

        private ITypesClient typesClient = RegClientFactory.getTypesClient();

        private IYearsClient yearsClient = InfClientFactory.getYearsClient();

        private IDecisionMakerTypesClient decisionMakersClient = 
            InfClientFactory.getDecisionMakerTypesClient();

        private IRegulationScopesClient scopesClient = 
            RegClientFactory.getRegulationScopesClient();

        private ICancelReasonsClient cancelReasonsClient = 
            RegClientFactory.getCancelReasonsClient();


        private List typesList = new ArrayList();
        private List yearsList = new ArrayList();
        private List decisionMakersList = new ArrayList();
        private List scopesList = new ArrayList();

        private List cancelReasonsList = new ArrayList();

        private boolean cancelDivDisplayed;
        

        private boolean suggestedFlag = false;

    private boolean disabledButtonFlag;
    private boolean showAllFlag;
    private IRegulationsDTO getAllDTO;
    private Long selectedRegTypeKey;
    private List<IBasicDTO> regStatusList = new ArrayList<IBasicDTO>();
    private IRegulationStatusClient regulationStatusClient = 
        RegClientFactory.getRegulationStatusClient();

    public AbstractRegulationsListBean() {
        setGetAllDTO(RegDTOFactory.createRegulationsDTO());
        setSaveSortingState(true);
        setUsingPaging(true);
        setSelectedRegTypeKey(IRegConstants.REGULATION_STATUS_SUGGESTION);
          
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
       app = super.appMainLayoutBuilder();
        return app;
    }
    public void setGetAllDTO(IRegulationsDTO getAllDTO) {
        this.getAllDTO = getAllDTO;
    }

    public IRegulationsDTO getGetAllDTO() {
        return getAllDTO;
    }
    public void setStringSearchType(String stringSearchType) {
        this.stringSearchType = stringSearchType;
    }

    public String getStringSearchType() {
        return stringSearchType;
     }


    public void setRegulationsSearchDTO(IRegulationSearchDTO regulationSearchDTO) {
      this.regulationsSearchDTO = regulationSearchDTO;
    }

    public IRegulationSearchDTO getRegulationsSearchDTO() {
        return regulationsSearchDTO;
}

    public void setTypesList(List typesList) {
       this.typesList = typesList;
    }

    public List getTypesList() throws DataBaseException, 
                                      SharedApplicationException {
if(typesList==null||typesList.size()==0){
        typesList = typesClient.getCodeName();
        }
   return typesList;
    }

    public void setYearsList(List yearsList) {
        this.yearsList = yearsList;
    }

    public List getYearsList() throws DataBaseException, 
                                      SharedApplicationException {
       if(yearsList==null||yearsList.size()==0){
        yearsList = yearsClient.getCodeName();
        }

        return yearsList;
   }

    public void setDecisionMakersList(List decisionMakersList) {
        this.decisionMakersList = decisionMakersList;
    }

    public List getDecisionMakersList() throws DataBaseException, 
                                               SharedApplicationException {
        if(decisionMakersList==null||decisionMakersList.size()==0){
        decisionMakersList = decisionMakersClient.getCodeName();
        }

        return decisionMakersList;
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
        ManyToManyMaintainBaseBean maintainBean = 
            (ManyToManyMaintainBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                                              this.getAddBeanName() + 
                                                                                                              "}").getValue(FacesContext.getCurrentInstance());
        if(maintainBean.getMaintainMode()==0)
        {
        dto.setRegulationDate(SharedUtils.getCurrentTimestamp());
        }
        setPageDTO(dto);

    }



        public PagingResponseDTO searchWithPaging(PagingRequestDTO requestDTO) {
            return new PagingResponseDTO(getSearchResult());
        }

        public List getSearchResult() {
            setShowAllFlag(false);
            List searchResult = null;
            try {
            if(getRegulationsSearchDTO().getCurrentStage()==1L && getSelectedRegTypeKey()!=null)
            {
                getRegulationsSearchDTO().setRegStatusFlage(getSelectedRegTypeKey());
            }
            
                searchResult = searchClient.searchWithParameter(regulationsSearchDTO);
                if( getSelectedRegTypeKey()==null)
                {
                   setSelectedRegTypeKey(getRegulationsSearchDTO().getRegStatusFlage()) ;
                }
            } catch (ItemNotFoundException e) { //this means no search results found
                searchResult = new ArrayList();
                e.printStackTrace();
            } catch (NoResultException e) { //this means no search results found
                searchResult = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                searchResult = new ArrayList();
                e.printStackTrace();
            }
            
            return searchResult;
        }

        public void cancelSearch() throws DataBaseException {

            System.out.println("Calling cancelSearch()...");
           if( getRegulationsSearchDTO().getRegStatusFlage()!=null)
         {   setSelectedRegTypeKey(getRegulationsSearchDTO().getRegStatusFlage());
         }  super.cancelSearch();
            this.setSelectedRadio("");
            this.setStringSearchType("false");
            regulationsSearchDTO =RegDTOFactory.createRegulationSearchDTO();
            this.setSearchType(0);
            this.setSearchMode(false);
            repositionPage(0);
            getMyDataTable().setFirst(0);
            resetPageIndex();
            
            this.getAll();
        }

        public String back() {

            setSearchQuery("");
            regulationsSearchDTO =RegDTOFactory.createRegulationSearchDTO();
            return null;
        }

       
        /*public void preCancelRegulation() {
         *             IRegulationsDTO selected = (IRegulationsDTO)getSelectedDTOS().get(0);
                if (((IRegulationStatusEntityKey)selected.getStatusDto().getCode()).getRegstatusCode().equals(ISystemConstant.REGULATION_STATUS_SUGGESTED)){
                    return;
                }
            loadSelectedDTOS();
        }*/

        public String getCustomDiv1Title() {
//            return BeansUtil.getResource(RESOURCE_BUNDLE_NAME_REG, 
//                                         RESOURCE_BUNDLE_KEY_CANCEL_REGULATION);
    return null;  
       }

        public void setCurrentDate(Date currentDate) {
            currentDate = null; // unused
        }

        public Date getCurrentDate() {
            return new Date((new java.util.Date()).getTime());
         }

        public Long getStatusCanceled() {
//            return ISystemConstant.REGULATION_STATUS_CANCELED;
       return null;  }

        public boolean isTabelPageHaveCanceled() throws DataBaseException {
//            int rowIndex = this.getMyDataTable().getFirst();
//            int lastRowIndex = rowIndex + this.getMyDataTable().getRows() - 1;
//            List myTableData = getMyTableData();
//            int maxRowIndex = myTableData.size() - 1;
//
//            if (lastRowIndex > maxRowIndex) {
//                lastRowIndex = maxRowIndex;
//            }
//
//            while (rowIndex <= lastRowIndex) {
//                IRegulationsDTO selected = 
//                    (IRegulationsDTO)myTableData.get(rowIndex);
//                if (((IRegulationStatusEntityKey)selected.getStatusDto().getCode()).getRegstatusCode().equals(ISystemConstant.REGULATION_STATUS_CANCELED)) {
//                    return true;
//                }
//                ++rowIndex;
//            }
//            return false;
       return false;  }

        public void setTabelPageHaveCanceled(boolean tabelPageHaveCanceled) {
            this.tabelPageHaveCanceled = tabelPageHaveCanceled;
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

                if (regstatusCode.equals(4)) {
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
                    AttachmentViewBean attachmentViewBean = (AttachmentViewBean)evaluateValueBinding("attachmentCycleViewBean");
                    attachmentViewBean.setSelectedRegulationsDTO((IRegulationsDTO)this.getSelectedDTOS().get(0));
                    attachmentViewBean.fillTablesList();
                    if (attachmentViewBean.getTablesList()==null || attachmentViewBean.getTablesList().size()==0)
                    {
                        getSharedUtil().handleSuccMsg(RESOURCE_BUNDLE_NAME_REG,"no_attachment_message");
                    return null;
                    }
                    return "VIEW_ATTACHMENT_PAGE_CYCLE";
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
           return null; 
   }

    public void setValidStatus(boolean validStatus) {
       this.validStatus = validStatus;
    }

    public boolean isValidStatus() {
       return validStatus;
 
   }
    public void setCancelReasonsList(List cancelReasonsList) {
       this.cancelReasonsList = cancelReasonsList;
    }

    public List getCancelReasonsList() throws DataBaseException, 
                                              SharedApplicationException {
if(cancelReasonsList==null||cancelReasonsList.size()==0)
      {
      cancelReasonsList = cancelReasonsClient.getCodeNameInCenter();
        }

        return cancelReasonsList;
   
   }
    public List getTotalList() {

        List<IBasicDTO> totalList = null;
        if (getClient() != null) {
            try {                
            getGetAllDTO().setRegStatusFlage(getSelectedRegTypeKey());
          totalList = ((IRegulationsClient)getClient()).getAllWithParameter(getGetAllDTO());
             } catch (NoResultException db) {
               // getSharedUtil().handleException(db);
                totalList = new ArrayList();
                db.printStackTrace();
            }catch (SharedApplicationException ne) {
                totalList = new ArrayList();
                ne.printStackTrace();
            }
            catch (DataBaseException db) {
                getSharedUtil().handleException(db);
                totalList = new ArrayList();
                db.printStackTrace();
            } catch (Exception e) {
                getSharedUtil().handleException(e);
                totalList = new ArrayList();
            }
        }
 

        return totalList;
 
    }
    public void fillDataTable()
    {
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
        getMyDataTable().setFirst(0);
        try {
            getAll();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }
    public void setSelectedRegTypeKey(Long selectedRegTypeKey) {
       this.selectedRegTypeKey = selectedRegTypeKey;
    }

    public Long getSelectedRegTypeKey() {
       return selectedRegTypeKey;

    }
    public void setRegStatusList(List<IBasicDTO> regStatusList) {
        this.regStatusList = regStatusList;
    }

    public List<IBasicDTO> getRegStatusList() throws DataBaseException {
    if(  regStatusList==null||regStatusList.size()==0)
         {  
         regStatusList=regulationStatusClient.getCodeNameCenter();
         }
        return regStatusList;
    }
    public void setDisabledButtonFlag(boolean disabledButtonFlag) {
        this.disabledButtonFlag = disabledButtonFlag;
    }

    public boolean isDisabledButtonFlag() {
        if(getSelectedRegTypeKey()==4L || getSelectedRegTypeKey()==0L )
        {
        setDisabledButtonFlag(false);
        }
        else
        {
        setDisabledButtonFlag(true);
        }
        return disabledButtonFlag;
    }
    public void setScopesList(List scopesList) {
        this.scopesList = scopesList;
    }

    public List getScopesList() throws DataBaseException {
        if(scopesList==null||scopesList.size()==0){
            scopesList = scopesClient.getCodeNameCenter();
        } 
     return scopesList;
    }
    public String goCopy() throws DataBaseException, 
                                  SharedApplicationException {

        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {

            RegulationCycleMaintainBean regulationMaintainBean = 
                RegulationCycleMaintainBean.getInstance();

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
    public void showAllregulations()
    {
    
         try {
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
             getMyDataTable().setFirst(0);
             setSearchMode(true);
             setShowAllFlag(true);
             setRegulationsSearchDTO( RegDTOFactory.createRegulationSearchDTO());
             getAll();
         } catch (DataBaseException e) {
             e.printStackTrace();
         }
     }

    public void setShowAllFlag(boolean showAllFlag) {
        this.showAllFlag = showAllFlag;
    }

    public boolean isShowAllFlag() {
        return showAllFlag;
    }
}
