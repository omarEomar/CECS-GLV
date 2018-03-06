package com.beshara.csc.inf.presentation.backingbean.maindata.kwtmap;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.client.IKwMapClient;
import com.beshara.csc.inf.business.client.IKwStreetsClient;
import com.beshara.csc.inf.business.client.IStreetZonesClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwMapDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IKwMapEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
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

import javax.faces.event.ActionEvent;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * EducationLevelListBean help administrator to Maintain education Level IiInIfIoIrImIaItIiIoInI.I * EducationLevelListBean help the administrator to add , edit , delete and view list of education Levels * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * * <br> * * @author Beshara Group
 * @version 2.0
 * @since
 */
public class kwtMapListBean1 extends TreeBaseBeanMany {

    private static final String BACK_BEAN_NAME = "kwMapListBean";

    private List streetZoneList = new ArrayList();
    private List selectedStreetZoneList = new ArrayList();
    private boolean showLinkDiv = false;
    private boolean showSelectedLinkDiv;
    private int streetZoneListSize = 0;
    private int selectedStreetZoneListSize = 0;
    IStreetZonesClient streetZonesClient;
    IKwStreetsClient streetClient;
    private String selecteMapName;
    private String selecteMapCode;
    private KwStreetZoneLinkBean kwStreetZoneLinkBean;

    private boolean nextLevelRequire = true;
    private String enName;
    private boolean disableStatusInAdd;
    private boolean disableStatusInEdit;
    private int childrenCount = 0;
    private final static String DEFALT_LEVEL = "-100";
    boolean enableEdit;
    private IBasicDTO lastAddedItem;
    private boolean searchBtnDisabled = false;


    public kwtMapListBean1() {
        setPageDTO(InfDTOFactory.createKwMapDTO());
        setBundle(ResourceBundle.getBundle("com.beshara.csc.inf.presentation.resources.inf_" +
                                           getSharedUtil().getLocale()));
        setRootName("PARENT_MAP_NAME");
        setSelectedPageDTO(InfDTOFactory.createKwMapDTO());
        setEntityKey(InfEntityKeyFactory.createKwMapEntityKey());
        setLovBaseBean(new LOVBaseBean());
        setKwStreetZoneLinkBean(new KwStreetZoneLinkBean());
        getKwStreetZoneLinkBean().setMyTableData(new ArrayList<IBasicDTO>());
        String treeLevel = "0";
        setDto(InfDTOFactory.createKwMapDTO());
        setDtoDetails(InfDTOFactory.createKwMapDTO());
        setClient(InfClientFactory.getKwMapClient());
        streetZonesClient = InfClientFactory.getStreetZonesClient();
        streetClient = InfClientFactory.getKwStreetsClient();
        setNameMaxLength(380);
        setClient(InfClientFactory.getKwMapClient());
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMultiSelect(false);
        // setUsingTreePaging(true);
        setTreeListPagingRequestDTO(new PagingRequestDTO(BACK_BEAN_NAME, "getChildrenNodes"));
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMultiSelect(false);
        getLovBaseBean().setSearchTyp("0");
        setSaveSortingState(true);
        //        getHtmlTree().expandAll();
        //        getParentLevel();
    }

    public PagingResponseDTO getChildrenNodes(PagingRequestDTO pagingRequest) throws DataBaseException,
                                                                                     SharedApplicationException {

        BesharaTree node = (BesharaTree)pagingRequest.getParams()[0];
        List resultList = null;
        resultList = ((IKwMapClient)getClient()).getChildrenList(getSelectedEntityKey(node.getTreeId()));


        setMyTableData(resultList);


        return new PagingResponseDTO(resultList);

    }

    public IEntityKey getSelectedEntityKey(String nodeId) {
        return InfEntityKeyFactory.createKwMapEntityKey(nodeId);
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showTreePage();
        app.setShowDelAlertTree(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowCustomDiv1(true);
        app.setShowLovDiv(true);
        return app;
    }

    public void getRelatedStreets() {
        getKwStreetZoneLinkBean().setMyTableData(new ArrayList());
        getKwStreetZoneLinkBean().setSelectedDTOS(new ArrayList());
        getKwStreetZoneLinkBean().resetPageIndex();
        getKwStreetZoneLinkBean().setCheckAllFlag(false);
        getKwStreetZoneLinkBean().reIntializeMsgs();
        setShowLinkDiv(false);
        setShowSelectedLinkDiv(true);

        //        streetZoneList = getSelectedStreetZoneList();
        //        setMyTableData(getStreetZoneList());
        //        setShowLinkDiv(false);

        getKwStreetZoneLinkBean().setMyTableData(getStreetZoneList());

        //        setShowSelectedLinkDiv(false);
        try {
            if (getKwStreetZoneLinkBean().getMyTableData() == null ||
                getKwStreetZoneLinkBean().getMyTableData().size() == 0) {
                setShowSelectedLinkDiv(false);
            }
        } catch (DataBaseException e) {
        }
    }

    public void getUnRelatedStreets() {
        getKwStreetZoneLinkBean().setMyTableData(new ArrayList());
        getKwStreetZoneLinkBean().setSelectedDTOS(new ArrayList());
        getKwStreetZoneLinkBean().resetPageIndex();
        getKwStreetZoneLinkBean().setCheckAllFlag(false);
        getKwStreetZoneLinkBean().reIntializeMsgs();
        setShowLinkDiv(true);
        setShowSelectedLinkDiv(false);

        //        getLovBaseBean().setMyTableData(getStreetZoneList());
        getKwStreetZoneLinkBean().setMyTableData(getStreetZoneList());
        //        setShowLinkDiv(false);
        //        setShowSelectedLinkDiv(false);
    }

    public void cancelLink() {
        getKwStreetZoneLinkBean().setMyTableData(new ArrayList());
        getKwStreetZoneLinkBean().setSelectedDTOS(new ArrayList());
        getKwStreetZoneLinkBean().resetPageIndex();
        getKwStreetZoneLinkBean().setCheckAllFlag(false);
        getKwStreetZoneLinkBean().setPageMode(0);
        setShowLinkDiv(false);
        setShowSelectedLinkDiv(false);

        getKwStreetZoneLinkBean().setSelectedPageDTO(InfDTOFactory.createKwMapDTO());
        getKwStreetZoneLinkBean().reIntializeMsgs();
    }


    public void addNew() throws DataBaseException, Exception {
        super.addNew();
    }

    public void addAndNew() throws DataBaseException, Exception {
        super.addAndNew();
        lastAddedItem = (IBasicDTO)SharedUtilBean.deepCopyObject(getPageDTO());
    }


    public void cancelEdit() throws DataBaseException, SharedApplicationException {
        IEntityKey centerCode = getDtoDetails().getCode();
        setDtoDetails((ITreeDTO)getClient().getById(centerCode));
        this.setEnableEdit(false);
    }

    public void disableEdit() {
        this.setEnableEdit(false);
    }

    public void setEnableEdit(boolean enableEdit) {
        this.enableEdit = enableEdit;
    }

    public boolean isEnableEdit() {
        return enableEdit;
    }

    public void setLastAddedItem(IBasicDTO lastAddedItem) {
        this.lastAddedItem = lastAddedItem;
    }

    public IBasicDTO getLastAddedItem() {
        return lastAddedItem;
    }

    public void setNextLevelRequire(boolean nextLevelRequire) {
        this.nextLevelRequire = nextLevelRequire;
    }

    public boolean isNextLevelRequire() {
        return nextLevelRequire;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getEnName() {
        return enName;
    }

    public void setDisableStatusInAdd(boolean disableStatusInAdd) {
        this.disableStatusInAdd = disableStatusInAdd;
    }

    public boolean isDisableStatusInAdd() {
        return disableStatusInAdd;
    }

    public void setDisableStatusInEdit(boolean disableStatusInEdit) {
        this.disableStatusInEdit = disableStatusInEdit;
    }

    public boolean isDisableStatusInEdit() {
        return disableStatusInEdit;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void enableEdit() {
        setEnableEdit(true);
        this.preEditTree();
    }

    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        super.edit();
    }

    public void setStreetZoneList(List streetZoneList) {
        this.streetZoneList = streetZoneList;
    }

    public List getStreetZoneList() {
        if (getDtoDetails() != null && getDtoDetails().getCode() != null) {
            String mapCode = ((IKwMapEntityKey)(getDtoDetails().getCode())).getMapCode();
            setSelecteMapName(getDtoDetails().getName());
            setSelecteMapCode(mapCode);
            streetZoneList = new ArrayList();
            if (isShowLinkDiv() == true) {
                try {
                    streetZoneList.addAll(streetZonesClient.getNotSelectedByMapCode(mapCode));
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (showSelectedLinkDiv == true) {
                try {
                    streetZoneList.addAll(streetZonesClient.getByMapCode(mapCode));
                    //                    getLovBaseBean().setMyTableData(selectedStreetZoneList);
                    /*  IKwStreetsDTO kwStreetsDTO;
                    getKwStreetZoneLinkBean().getSelectedDTOS().clear();
                    for (int i = 0; i < streetZoneList.size(); i++) {
                        kwStreetsDTO = (IKwStreetsDTO)streetZoneList.get(i);
                        kwStreetsDTO.setChecked(true);
                        getKwStreetZoneLinkBean().getSelectedDTOS().add(kwStreetsDTO);
                    }
                    streetZoneList = new ArrayList();
                    setStreetZoneList(getKwStreetZoneLinkBean().getSelectedDTOS());
                    //                    getLovBaseBean().setMyTableData(new ArrayList());
*/
                } catch (DataBaseException e) {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return streetZoneList;
    }

    public void showSearchListOfValuesDiv() {
        if (isSearchMode() == true) {
            getLovBaseBean().setSearchMode(true);
            getLovBaseBean().setCleanDataTableFlage(true);
            setSearchBtnDisabled(false);
        } else {
            getLovBaseBean().setSearchMode(false);
            getLovBaseBean().setCleanDataTableFlage(false);
            getLovBaseBean().setSearchQuery("");
            setSearchBtnDisabled(true);
        }
        getLovBaseBean().setReturnMethodName(BACK_BEAN_NAME + ".returnKwMApLovDiv");
        getLovBaseBean().setSearchMethod(BACK_BEAN_NAME + ".search");
        getLovBaseBean().setCancelSearchMethod(BACK_BEAN_NAME + ".cancelSearchKwMapLovDiv");
        getLovBaseBean().setRenderedDropDown("lovDivOkTBtn,lovDiv_btnsPnlGrd,treeList,lov_dataT_data_panel,treeDivPanel ,divLov, OperationBar , theSelectedNodeId , selectedNodeTreeLevelId , treeDetailsDiv,treeAlertDelete");
        getLovBaseBean().setOnCompleteList(getLovBaseBean().getOnCompleteList() + " ; focusHighlightedNode ( ) ; ");
        getLovBaseBean().setHighlightedDTOS(null);
    }

    public String returnKwMApLovDiv() {
        getLovBaseBean().setSearchMode(true);
        setSearchResultListSize(getLovBaseBean().getSelectedDTOS().size());
        this.setCurrentSelectedSearchIndex(getLovBaseBean().getIndexOfSelectedDataInDataTable());
        getHtmlTree().collapsePath(new String[] { "0" });
        expandAllForSpecificNodeList((List)getLovBaseBean().getSelectedDTOS()); //((List<ITreeDTO>)getLovBaseBean().getSelectedDTOS());
        setSearchMode(true);

        List list = new ArrayList();
        try {
            list = buildTree().getChildren();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }

        IKwMapDTO kwMapDTO = (IKwMapDTO)getLovBaseBean().getSelectedDTOS().get(0);
        highlightAddEditResult(list, kwMapDTO.getCode().getKey(), "person-highlight");
        return "";
    }

    public void highlightAddEditResult(List<BesharaTree> list, String searchedString, String facet) {

        for (BesharaTree child : list) {
            String nodeCode = child.getTreeId();
            //            String searchedString =searchedString;
            if (searchedString.equalsIgnoreCase(nodeCode)) {
                if (child.getParent() != null && !child.getParent().getTreeId().equals("0")) {
                    expandAllForSpecificNode((ITreeDTO)child);
                }

                child.setType(facet);
            } else {
                child.setType("foo-folder");
            }
            if (child.getChildCount() != 0) {
                highlightSearchedResult((List<BesharaTree>)child.getChildren(), searchedString, facet);
            }

        }
    }

    public void highlightSearchedResult(List<BesharaTree> list, String searchedString, String facet) {
        for (BesharaTree child : list) {
            if (this.getSearchType().intValue() == 0) {
                if (searchedString != null && !searchedString.equals("") && child.getTreeId().equals(searchedString)) {
                    child.setType(facet);
                    this.setFounded(true);
                    setSelectedListSize(1);
                    setSearchResultListSize(1);
                } else {
                    child.setType("foo-folder");
                }
            } else {
                String childIdentifier = child.getIdentifier().trim().toLowerCase();
                String searchBy = searchedString.trim().toLowerCase();
                if (Helper.searchInTree(searchBy, childIdentifier, false)) {
                    child.setType(facet);
                    this.setFounded(true);
                    setSelectedListSize(1);
                    setSearchResultListSize(1);
                } else {
                    child.setType("foo-folder");
                }
            }
            if (child.getChildCount() != 0) {
                highlightSearchedResult((List<BesharaTree>)child.getChildren(), searchedString, facet);
            }
        }

    }

    public String cancelSearchKwMapLovDiv() {
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

    public void cancelSearchTree() throws DataBaseException, RemoteException, Exception {
        if (isUsingTreePaging()) {
            try {
                super.cancelSearch();
                setSelectionNo(0);
                SetSelectedNode(null);
                getHtmlTree().collapsePath(new String[] { "0" });
                buildTree();
            } catch (DataBaseException e) {
                ;
            }

        } else {
            super.cancelSearch();
            setSearchBtnDisabled(true);
            setSelectionNo(0);
            getLovBaseBean().setSearchMode(false);
            getLovBaseBean().setSearchQuery("");
            getLovBaseBean().getSearchQuery();
            getLovBaseBean().setSelectedRadio("");
            getLovBaseBean().setSearchTyp("0");
            getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
            getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
            buildTree();
            getHtmlTree().collapseAll();
            getHtmlTree().expandPath(new String[] { "0" });
        }
    }

    public void openCustomeLovDiv(ActionEvent ae) {
        Boolean manyToMany = (Boolean)evaluateValueBinding("appMainLayout.manyToMany");
        String beanNameBindingKey = "pageBeanName";
        if (manyToMany != null && manyToMany) {
            beanNameBindingKey = "detailBeanName";
        }
        BaseBean currentBaseBean = (BaseBean)evaluateValueBinding(beanNameBindingKey);
        currentBaseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");

    }

    public void saveStreetZone() {
        try {
            streetZonesClient.add(getKwStreetZoneLinkBean().getSelectedDTOS(), getSelecteMapCode());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List list = new ArrayList();
        try {
            list = buildTree().getChildren();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }

        //        IKwMapDTO kwMapDTO = (IKwMapDTO)getLovBaseBean().getSelectedDTOS().get(0);

        setSelectedDTOS(new ArrayList());
        setSelectedPageDTO(InfDTOFactory.createKwMapDTO());
        setDtoDetails(InfDTOFactory.createKwMapDTO());
        setSelectionNo(0);
        highlightAddEditResult(list, getSelecteMapCode(), "person-highlight");
        getSharedUtil().handleSuccMsg("com.beshara.csc.inf.presentation.resources.inf_" + getSharedUtil().getLocale(),
                                      "successJoinStreetZones");
    }


    public void removeStreetZone() {
        try {
            streetZonesClient.remove(getKwStreetZoneLinkBean().getSelectedDTOS(),
                                     ((IKwMapEntityKey)(getDtoDetails().getCode())).getMapCode());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        getSharedUtil().handleSuccMsg("com.beshara.csc.inf.presentation.resources.inf_" + getSharedUtil().getLocale(),
                                      "successUnJoinStreetZones");
    }

    public void setShowLinkDiv(boolean showLinkDiv) {
        this.showLinkDiv = showLinkDiv;
    }

    public boolean isShowLinkDiv() {
        return showLinkDiv;
    }

    public void setStreetZoneListSize(int streetZoneListSize) {
        this.streetZoneListSize = streetZoneListSize;
    }

    public int getStreetZoneListSize() {
        return streetZoneList.size();
    }

    public void setSelecteMapName(String selecteMapName) {
        this.selecteMapName = selecteMapName;
    }

    public String getSelecteMapName() {
        return selecteMapName;
    }

    public void setSelecteMapCode(String selecteMapCode) {
        this.selecteMapCode = selecteMapCode;
    }

    public String getSelecteMapCode() {
        return selecteMapCode;
    }

    public void setSelectedStreetZoneList(List selectedStreetZoneList) {
        this.selectedStreetZoneList = selectedStreetZoneList;
    }

    public String search(Object searchType, Object searchQuery) {
        setSearchBtnDisabled(true);

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

    public List getSelectedStreetZoneList() {
        return selectedStreetZoneList;
    }


    public void setShowSelectedLinkDiv(boolean showSelectedLinkDiv) {
        this.showSelectedLinkDiv = showSelectedLinkDiv;
    }

    public boolean isShowSelectedLinkDiv() {
        return showSelectedLinkDiv;
    }

    public void setSelectedStreetZoneListSize(int selectedStreetZoneListSize) {
        this.selectedStreetZoneListSize = selectedStreetZoneListSize;
    }

    public int getSelectedStreetZoneListSize() {
        return getSelectedStreetZoneList().size();
    }

    public void setKwStreetZoneLinkBean(KwStreetZoneLinkBean kwStreetZoneLinkBean) {
        this.kwStreetZoneLinkBean = kwStreetZoneLinkBean;
    }

    public KwStreetZoneLinkBean getKwStreetZoneLinkBean() {
        return kwStreetZoneLinkBean;
    }

    public BesharaTree buildTree() throws DataBaseException {
        BesharaTree besharaTree = super.buildTree();
        getHtmlTree().expandPath(new String[] { "0" });
        return besharaTree;
    }

    public void selectedRadioButton(ActionEvent actionEvent) throws Exception {
        getLovBaseBean().selectedRadioButton(actionEvent);
        setSearchBtnDisabled(false);
    }

    public void setSearchBtnDisabled(boolean searchBtnDisabled) {
        this.searchBtnDisabled = searchBtnDisabled;
    }

    public boolean isSearchBtnDisabled() {
        return searchBtnDisabled;
    }
}
