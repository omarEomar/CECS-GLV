package com.beshara.csc.nl.reg.presentation.backingbean.subject;

import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.dto.TreeDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.locking.dto.ILockableItem;
import com.beshara.base.locking.exceptions.LockingException;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.nl.org.business.dto.IClassificationsDTO;
import com.beshara.csc.nl.reg.business.client.ISubjectsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.ISubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.dto.SubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.ValiditiesDTO;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.business.entity.ValiditiesEntityKey;
import com.beshara.csc.nl.reg.presentation.backingbean.copiesdata.CopiesDataDataList;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.SystemException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BesharaTree;
import com.beshara.jsfbase.csc.backingbean.TreeBaseBeanMany;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.Helper;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *   LevelListBean help administrator to Maintain Job Level information.
 *   LevelListBean help the administrator to add, edit ,delete and view list of Job Levels
 * <br><br>
 * 
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * Ahmed Abd El-fatah    21-Jun-2008     Created
 * Amr Abdo    14-Jun-2009     Updated
 * <br>
 * 
 * @author Beshara Group   
 * @version 2.0   
 * @since 
       
 */
public class SubjectListBean extends TreeBaseBeanMany {

    private List<IBasicDTO> validitiesList;
    private List<IBasicDTO> validitiesListEdit;
    private Long valityKey = ISystemConstant.REGULATION_VALIDITY_ALL;
    private Long valityKeyEdit;

    private String warnSpanText;
    private boolean hiding =false;
    private boolean inAdd;
    private boolean inEdit;
    private static final String BACK_BEAN_NAME = "subjectListBean";
    ISubjectsDTO pageDtoBuffer = RegDTOFactory.createSubjectsDTO();
    
    
    public SubjectListBean() {

        setBundle(ResourceBundle.getBundle("com.beshara.csc.nl.reg.presentation.resources.reg_" + 
                                           getSharedUtil().getLocale()));
        setRootName("updatedsubject");

        this.setClient((ISubjectsClient)RegClientFactory.getSubjectsClientForCenter());
        setPageDTO(RegDTOFactory.createSubjectsDTO());
        setDto(RegDTOFactory.createSubjectsDTO());
        setDtoDetails(RegDTOFactory.createSubjectsDTO());
        setNameMaxLength(380);  
        //////////////////////////////////////////////TREE
        setUsingTreePaging(true);
        setTreeListPagingRequestDTO(new PagingRequestDTO(BACK_BEAN_NAME, 
                                                         "getChildrenNodes"));
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMultiSelect(false);
        getLovBaseBean().setSearchTyp("0");
         
        initializeDtoDetails();
        setDelConfirm("divSheardStyle1");
        setDelAlert("divSheardStyle1");
    }
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app = super.appMainLayoutBuilder();
        app.setShowLovDiv(true);
        return app;
    }
    public String gToCopiesData() throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        CopiesDataDataList copiesDataDataList = 
            (CopiesDataDataList)app.createValueBinding("#{copiesDataDataListBean}").getValue(fc);
        try {
//            copiesDataDataList.setPageDTO((ITreeDTO)getSharedUtil().deepCopyObject(getDtoDetails()));
            copiesDataDataList.setPageDTO(getDtoDetails());
        } catch (Exception e) {  
            e.printStackTrace();
        }
        return "CopiesDataDataList";
    }
    public void viewWarnSpan(ActionEvent event) {
        // for remove warning only
        event = null;

        String key = 
            ((SubjectsDTO)getDtoDetails()).getValiditiesDTO().getCode().getKey().toString();
        if (!valityKeyEdit.equals(key) && 
            !getDtoDetails().getChildernNumbers().equals(0)) {
            setWarnSpanText("warnMessageValidFor");
        } else {
            setWarnSpanText(null);
        }
    }
    
   
    public void updateValiditiesListEdit() {

        try {
            valityKeyEdit = getVailtyKeyVal();
            validitiesListEdit = 
                    RegClientFactory.getValiditiesClient().getCodeName();

            for (IBasicDTO item: validitiesListEdit) {
                ValiditiesDTO validitiesDTOItem = (ValiditiesDTO)item;
                Long validitiesKeyValid = 
                    ((ValiditiesEntityKey)validitiesDTOItem.getCode()).getValidityCode();
                if (!(valityKeyEdit.equals(validitiesKeyValid) || 
                      validitiesKeyValid.equals(ISystemConstant.REGULATION_VALIDITY_ALL))) {
                    validitiesListEdit.remove(item);
                    break;
                }
            }

        } catch (DataBaseException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
        }
    }

    private Long getVailtyKeyVal() {
        SubjectsDTO subjectsDTO = (SubjectsDTO)getDtoDetails();
        return ((ValiditiesEntityKey)subjectsDTO.getValiditiesDTO().getCode()).getValidityCode();
    }
    
    
    public String goToModuleApplicationSubject(){
        ModuleApplicationSubjectBean moduleApplicationSubjectBean = (ModuleApplicationSubjectBean)evaluateValueBinding("moduleApplicationSubjectBean");
        //moduleApplicationSubjectBean.setMapregsubDTO(getDtoDetails());
        try {
            moduleApplicationSubjectBean.setMapregsubDTO((ITreeDTO)getSharedUtil().deepCopyObject(getDtoDetails()));    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "moduleapplicationsubjects";
    }

    public void setAddMode() {
        valityKey = ISystemConstant.REGULATION_VALIDITY_ALL;
        inAdd = true;
        if (!isEnabledRoot()) {
            valityKey = getVailtyKeyVal();
        }
    }

    public void preEditTree() {
       
            inEdit = true;
            setWarnSpanText(null);
            super.preEditTree();
            updateValiditiesListEdit();
            //TODO locking code
            if (!lock()) {
            return;
        }
        //setLastLockingAction("edit");
        //TODO locking code
        // propagate the locking status to the maintain bean
        // because it will be responsible to unlock the edited item if it was locked
    }


    public void edit() throws DataBaseException, ItemNotFoundException, 
                              SharedApplicationException {


        if (!valityKeyEdit.equals("")) {
            try {
                //TODO commented as he can choose any validity like at adding process
                //                ((SubjectsDTO)getDto()).getValiditiesDTO().setCode(Helper.getEntityKey(getValiditiesListEdit(), 
                //                                                                                       valityKeyEdit));
                //                setDtoDetails(new TreeDTO());
                ((SubjectsDTO)getDto()).getValiditiesDTO().setCode(new ValiditiesEntityKey(valityKeyEdit));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (getDto().getParentObject() != null && 
            getDto().getParentObject().getCode() == null) {
            getDto().setParentObject(null);
        }
        setPageDTO((BasicDTO)getDto());
        SharedUtilBean shared_util = getSharedUtil();

        try {
            //TODO locking code
            // be sure that we are still locking the edited item
            // if not cancel the edit operation and show an exception 
            // message to the user
            if (insureLocked()) {
                getClient().update(getPageDTO());
            }
        } catch (DataBaseException e) {
            //TODO locking code
            // unlock the edited item in update success or failure
            // so we added it in this finally block
            unlock();
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        } catch (ItemNotFoundException item) {
            //TODO locking code
            // unlock the edited item in update success or failure
            // so we added it in this finally block
            unlock();
            getSharedUtil().handleException(item, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        } catch (SharedApplicationException e) {
            //TODO locking code
            // unlock the edited item in update success or failure
            // so we added it in this finally block
            unlock();
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            e.getMessage());
        } catch (Exception e) {
            //TODO locking code
            // unlock the edited item in update success or failure
            // so we added it in this finally block
            unlock();
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        } finally {
            //TODO locking code
            // unlock the edited item in update success or failure
            // so we added it in this finally block
            unlock();
        }
        setDtoDetails((TreeDTO)getClient().getByIdInCenter(getDto().getCode()));
        cancelSearch();
        if (getHighlightedDTOS() != null)
            getHighlightedDTOS().add(getPageDTO());
        //resetTableHeaderStatus();

        shared_util.setSuccessMsgValue("SuccesUpdated");
        this.getSelectedDTOS().clear();
        //resetTableHeaderStatus();
        //Added By Yassmine to reset the value of radio button after Edit
        setSelectedRadio("");

        if (getPageDTO().getCode() != null) {
            setSelectedNodeId(null);
            setSelectionNo(0);
            List<BesharaTree> list;
            try {
                list = buildTree().getChildren();
                highlightAddEditResult(list, 
                                       (String)getPageDTO().getCode().getKey(), 
                                       "person-highlight");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
      
        try {
            buildTree();
            getTreeNodeBase().setExpanded(true);
            expandAllForSpecificNode((ITreeDTO)getPageDTO());
            String code = (String)getPageDTO().getCode().getKey();
            //getHtmlTree ( ) .expandPath ( new String[] { "0" } ) ; 
            setSelectedNodeId(code);
            setEnabledRoot(false);
            this.setJavaScriptCaller("javascript:showDivTreeDetails ( this , '" + 
                                     code + "' , window.divTreeDetails ) ; ");
              setLovBaseBean(new LOVBaseBean());
            getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
            getLovBaseBean().setMultiSelect(false);
            getLovBaseBean().setSearchTyp("0");
            
        }  catch (RemoteException e) {
            // TODO
        } catch (Exception e) {
            // TODO
        }

    }

    public void addNew() throws DataBaseException, Exception {
        updateValityKey();
        try{
        saveItem();
        setPageDtoBuffer((ISubjectsDTO)getPageDTO());
            if (getPageDTO().getCode() != null) {
                List<BesharaTree> list = buildTree().getChildren();
                highlightAddEditResult(list, 
                                       (String)getPageDTO().getCode().getKey(), 
                                       "person-highlight");
            }
            
            buildTree();
            getTreeNodeBase().setExpanded(true);
            expandAllForSpecificNode((ITreeDTO)getPageDTO());
            String code = (String)getPageDTO().getCode().getKey();
            //getHtmlTree ( ) .expandPath ( new String[] { "0" } ) ; 
            setSelectedNodeId(code);
            setEnabledRoot(false);
            this.setJavaScriptCaller("javascript:showDivTreeDetails ( this , '" + 
                                     code + "' , window.divTreeDetails ) ; ");
            fillPagedTreeListSize();
            setLovBaseBean(new LOVBaseBean());
            getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
            getLovBaseBean().setMultiSelect(false);
            getLovBaseBean().setSearchTyp("0");
        }
        catch(Exception e){
        e.printStackTrace();
            setSearchMode(false);
            getLovBaseBean().setSearchMode(false);
            getLovBaseBean().setSearchQuery("");
            getLovBaseBean().getSearchQuery();
            getLovBaseBean().setSelectedRadio("");
            getLovBaseBean().setSearchTyp("0");
            getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
            getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());    
            cancelSearchTree();
        }
      }


    public void addAndNew() throws DataBaseException, Exception {
        updateValityKey();
        super.addAndNew();
        cancelSearchTree();
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMultiSelect(false);
        getLovBaseBean().setSearchTyp("0");
     }

    public void updateValityKey() throws ItemNotFoundException, Exception {
        for (IBasicDTO item: validitiesList) {
            ValiditiesDTO validitiesDTOItem = (ValiditiesDTO)item;
            Long subjectKeyValid = 
                ((ValiditiesEntityKey)validitiesDTOItem.getCode()).getValidityCode();
            if (subjectKeyValid.equals(valityKey)) {
                ((SubjectsDTO)getDto()).setValiditiesDTO(item);
                break;
            }
        }
    }

    public void idChange(ValueChangeEvent e) {
        setEnabledRoot(false);
        super.idChange(e);
    }

    public void cancelAddTree() throws DataBaseException, Exception {
        inAdd = false;
        super.cancelAddTree();
           if (getPageDtoBuffer() != null&&getPageDtoBuffer().getCode() != null){
                List<BesharaTree> list = buildTree().getChildren();
                highlightAddEditResult(list, 
                                       (String)getPageDtoBuffer().getCode().getKey(), 
                                       "person-highlight");
            buildTree();
            getTreeNodeBase().setExpanded(true);
            expandAllForSpecificNode((ITreeDTO)getPageDtoBuffer());
            String code = (String)getPageDtoBuffer().getCode().getKey();
            //getHtmlTree ( ) .expandPath ( new String[] { "0" } ) ; 
            setSelectedNodeId(code);
            setEnabledRoot(false);
            this.setJavaScriptCaller("javascript:showDivTreeDetails ( this , '" + 
                                     code + "' , window.divTreeDetails ) ; ");
            setLovBaseBean(new LOVBaseBean());
            getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
            getLovBaseBean().setMultiSelect(false);
            getLovBaseBean().setSearchTyp("0");
                                     
            fillPagedTreeListSize();  
             }
            }

    public void cancelEdit() {
        inEdit = false;
        //TODO locking code
        unlock();
    }

    public void setValiditiesList(List<IBasicDTO> validitiesList) {
        this.validitiesList = validitiesList;
    }

    public List<IBasicDTO> getValiditiesList() {
        if (validitiesList == null) {
            try {
                validitiesList = 
                        RegClientFactory.getValiditiesClient().getCodeName();
            } catch (DataBaseException e) {
                getSharedUtil().setErrMsgValue(e.getMessage());
            }
        }
        return validitiesList;
    }

    public void setValiditiesListEdit(List<IBasicDTO> validitiesListEdit) {
        this.validitiesListEdit = validitiesListEdit;
    }

    public List<IBasicDTO> getValiditiesListEdit() {
        return validitiesListEdit;
    }

    public void setValityKey(Long valityKey) {
        this.valityKey = valityKey;
    }

    public Long getValityKey() {
        return valityKey;
    }

    public void setValityKeyEdit(Long valityKeyEdit) {
        this.valityKeyEdit = valityKeyEdit;
    }

    public Long getValityKeyEdit() {
        return valityKeyEdit;
    }

    public void setWarnSpanText(String warnSpanText) {
        this.warnSpanText = warnSpanText;
    }


    public String getWarnSpanText() {
        return warnSpanText;
    }

    public Long getRegValidityAll() {
        return ISystemConstant.REGULATION_VALIDITY_ALL;
    }

    public void setInAdd(boolean inAdd) {
        this.inAdd = inAdd;
    }

    public boolean isInAdd() {
        return inAdd;
    }

    public void setInEdit(boolean inEdit) {
        this.inEdit = inEdit;
    }

    public boolean isInEdit() {
        return inEdit;
    }

    public void setHiding(boolean hiding) {
        this.hiding = hiding;
    }

    public boolean isHiding() {
        SubjectsDTO subjectsDTO = (SubjectsDTO)getDtoDetails();
        long key = 
        ((ValiditiesEntityKey)subjectsDTO.getValiditiesDTO().getCode()).getValidityCode();
        if (key == 1){
            setHiding(true);
        }
        return hiding;
    }
    ////////////////////TREE

    public PagingResponseDTO getChildrenNodes(PagingRequestDTO pagingRequest) throws DataBaseException, 
                                                                                     SharedApplicationException {

        BesharaTree node = (BesharaTree)pagingRequest.getParams()[0];
        List resultList = null;
        try {
            resultList = 
                    ((ISubjectsClient)getClient()).getChildrenList(getSelectedEntityKey(node.getTreeId()));
        } catch (NoResultException e) {
            resultList = new ArrayList();
            e.printStackTrace();
        }

        setMyTableData(resultList);

        return new PagingResponseDTO(resultList);

    }

    public IEntityKey getSelectedEntityKey(String nodeId) {
        return RegEntityKeyFactory.createSubjectsEntityKey(new Long(nodeId));
    }

    public Integer getPagedTreeListSize() {
        if (super.getPagedTreeListSize() == 0) {
            fillPagedTreeListSize();
        }
        return super.getPagedTreeListSize();
    }

    public void fillPagedTreeListSize() {
        Integer TreePageSize = 0;
        try {
            TreePageSize = 
                    (((ISubjectsClient)getClient()).getTotalNumber());
        } catch (DataBaseException e) {
          e.printStackTrace();
        }

        setPagedTreeListSize(TreePageSize);
    }

    public void getAll() throws DataBaseException {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            setMyTableData(((ISubjectsClient)getClient()).getFirstLevel());
            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();
        } catch (NoResultException ne) { //this means no search results found
            setMyTableData(new ArrayList());
            ne.printStackTrace();
        } catch (DataBaseException db) {
            db.printStackTrace();
            shared_util.setErrMsgValue(db.getMessage());
        } catch (SharedApplicationException e) {
            shared_util.setErrMsgValue(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showSearchListOfValuesDiv() {
        if (isSearchMode() == true) {
            getLovBaseBean().setSearchMode(true);
            getLovBaseBean().setCleanDataTableFlage(true);
        } else {
            getLovBaseBean().setSearchMode(false);
            getLovBaseBean().setCleanDataTableFlage(false);
            getLovBaseBean().setSearchQuery("");
        }
        getLovBaseBean().setReturnMethodName(BACK_BEAN_NAME + 
                                             ".returnSubjectsLovDiv");
        getLovBaseBean().setSearchMethod(BACK_BEAN_NAME + ".search");
        getLovBaseBean().setCancelSearchMethod(BACK_BEAN_NAME + 
                                               ".cancelSearchSubjectsLovDiv");
        getLovBaseBean().setRenderedDropDown("treeList , OperationBar , theSelectedNodeId , selectedNodeTreeLevelId , treeDetailsDiv,treeAlertDelete");
        getLovBaseBean().setOnCompleteList(getLovBaseBean().getOnCompleteList() + 
                                           " ; focusHighlightedNode ( ) ; ");
        getLovBaseBean().setHighlightedDTOS(null);
    }
    public String search(Object searchType, Object searchQuery) {
        try {
            if (searchQuery != null && !searchQuery.equals("")) {
                if (searchType.toString().equals("0")) {
                    getLovBaseBean().setMyTableData(getClient().searchByCode((new Long(searchQuery.toString()))));
                } else if (searchType.toString().equals("1")) {
                    getLovBaseBean().setMyTableData(getClient().searchByName(searchQuery.toString()));
                }
            } 
            initializeNavigation();
        } catch (DataBaseException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }
        return "";
    }

    public String returnSubjectsLovDiv() {
        getLovBaseBean().setSearchMode(true);
        setSearchResultListSize(getLovBaseBean().getSelectedDTOS().size());
        this.setCurrentSelectedSearchIndex(getLovBaseBean().getIndexOfSelectedDataInDataTable());
        getHtmlTree().collapsePath(new String[] { "0" });
        expandAllForSpecificNodeList((List)getLovBaseBean().getSelectedDTOS());
        setSearchMode(true);
        return "";
    }
    public String cancelSearchSubjectsLovDiv() {
        setSearchMode(false);
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().getSearchQuery();
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        try {
            cancelSearchTree();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public void initializeDtoDetails() {
        setDtoDetails(RegDTOFactory.createSubjectsDTO());
    }

    public void deleteItem() throws RemoteException, Exception {
        try {
 
                boolean successDelete = false;
                IResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(getDtoDetails());
                try {
                    successDelete = getClient().remove(getDtoDetails());
                    //checkControlsHeaderStatus();
                    cancelSearchTree();
                    if (successDelete) {
                        //getSharedUtil().setSuccessMsgValue("SuccessDeleted");
                        resultDTO.setStatus("Deleted");
                    }
                        if (this.getSelectedDTOS() != 
                            null) { // as the edit button still active by Ashraf Gaber
                            this.getSelectedDTOS().clear();
                        }
                        setSelectionNo(0);
                    } catch (LockingException e) {
                        resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                        resultDTO.setBusinessException(new LockingException(e.getMessage()));
                        setEnabledRoot(false);
                    } catch (ItemNotFoundException e) {
                        resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                        setEnabledRoot(false);
                    } catch (NoResultException e) {
                        resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                        setEnabledRoot(false);
                    } catch (SharedApplicationException e) {
                        resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                        resultDTO.setBusinessException(new SharedApplicationException(e.getMessage()));
                    setEnabledRoot(false);
                    } catch (DataBaseException e) {
                        resultDTO.setDatabaseException(new DataBaseException(e.getMessage()));
                        resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                    } catch (RemoteException e) {
                        resultDTO.setDatabaseException(new DataBaseException(e.getMessage()));
                        resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                        setEnabledRoot(false);
                    } catch (TransactionException e) {
                        resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                        resultDTO.setDatabaseException(new DataBaseException(new SystemException(e).getSQLExceptionMessage()));
                        setEnabledRoot(false);
                    }
                getDeleteStatusDTOS().add(resultDTO);
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");
              setSearchMode(false);
            setLovBaseBean(new LOVBaseBean());
            fillPagedTreeListSize();
        } catch (Exception e) {
            e.printStackTrace();
            cancelSearchTree();
        }
    }

        /**
         * gets the item that will be used by the locking methods
         * @return the item that will be locked
         */
        protected ILockableItem getLockingItem() {
           if (getDto() != null ) {
               IBasicDTO dto = getDto();
               if (dto instanceof ILockableItem) {
                   return (ILockableItem)dto;
               }
           }
           return null;
       }

    public void setPageDtoBuffer(ISubjectsDTO pageDtoBuffer) {
        this.pageDtoBuffer = pageDtoBuffer;
    }

    public ISubjectsDTO getPageDtoBuffer() {
        return pageDtoBuffer;
    }
    public void saveItem() throws DataBaseException, Exception {
        ITreeDTO treeDTO = new TreeDTO();
        if (!getParentLevel().equals("-100")) {
            if (isUsingTreePaging()) {
                setEntityKey(getSelectedEntityKey(getParentLevel()));
            } else {
                setEntityKey(Helper.getEntityKey((List<IBasicDTO>)getMyTableData(), 
                                                 getParentLevel()));
            }
            treeDTO.setCode(getEntityKey());
            getDto().setParentObject(treeDTO);
        } else {
            getDto().setParentObject(null);
        }
        getDto().setName(getLevelName());
        getDto().setBooleanLeafFlag(isBooleanLeafFlag());
        setPageDTO((IBasicDTO)getDto());
        this.add();
         setPageDtoBuffer((ISubjectsDTO)getPageDTO());
         setLevelName("");
        setBooleanLeafFlag(true);
    }
}

