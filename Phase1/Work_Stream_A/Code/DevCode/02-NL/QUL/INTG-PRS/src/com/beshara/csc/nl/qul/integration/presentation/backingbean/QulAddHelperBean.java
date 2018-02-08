package com.beshara.csc.nl.qul.integration.presentation.backingbean;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.qul.business.client.ICenterQualificationsClient;
import com.beshara.csc.nl.qul.business.client.QulClientFactory;
import com.beshara.csc.nl.qul.business.dto.ICenterQualificationsSearchCriteriaDTO;
import com.beshara.csc.nl.qul.business.dto.ICentersDTO;
import com.beshara.csc.nl.qul.business.dto.IEducationLevelsDTO;
import com.beshara.csc.nl.qul.business.dto.QulDTOFactory;
import com.beshara.csc.nl.qul.business.entity.ICentersEntityKey;
import com.beshara.csc.nl.qul.business.entity.QulEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.BesharaTree;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;


public abstract class QulAddHelperBean extends LookUpBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;



    private boolean showEduLevelsTree;
    private ITreeDTO centerDTO;
    private ITreeDTO levelDTO;
    private TreeDivBean treeDivBean = (TreeDivBean)evaluateValueBinding("treeDivBean");
    private String selectedCenterName = "";
    private boolean enableSearchByCode = false;
    private String goActionOkMethod;
    private List qulCentersList = new ArrayList();
    private String qulCenterCode;
    private String qulCountryCode;
    private boolean showTreeContent = false;
    private String cancelSearchMethod;
    private List certificatesList;
    private Long centerCode;
    private String searchTreeMethod;
    public static String beanName;
    private Boolean qulCenterTreeDiv = true;
    private String selectedCountryId = ISystemConstant.KUWAIT_NATIONALITY.toString();
    private ICenterQualificationsSearchCriteriaDTO centerQualificationsSearchCriteriaDTO =
        QulDTOFactory.createCenterQualificationsSearchCriteriaDTO();


    public QulAddHelperBean() {

    }

    public QulAddHelperBean(String beanName) {
        this.beanName = beanName;
    }

    public void getTree() {
        try {

            if (selectedCountryId != null) {
                treeDivBean.setMyTableData(null);
                ICountriesEntityKey key =
                    InfEntityKeyFactory.createCountriesEntityKey(Long.valueOf(selectedCountryId));
                List temp = QulClientFactory.getCentersClient().getAllByCountryInCenter(key);

                treeDivBean.setMyTableData(temp);
                treeDivBean.setTreeNodeBase(null);
                treeDivBean.setShowTreeContent(true);
            }
        } catch (NoResultException ne) {
            setMyTableData(new ArrayList());
        } catch (DataBaseException db) {
            setMyTableData(new ArrayList());
            getSharedUtil().handleException(db);
        } catch (SharedApplicationException e) {
            setMyTableData(new ArrayList());
            getSharedUtil().handleException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract IEntityKey getCountryCode();

    public BesharaTree buildTree() throws DataBaseException, RemoteException, Exception {
        try {

            //  if (isUsingTreePaging()) {
            treeDivBean.setTreeList(getMyTableData());
            //            } else {
            //                if (getStartTreeLevel().equals(ISystemConstant.FIRST_LEVEL_IN_TREE)) {
            //                    setTreeList(Helper.buildTree(getMyTableData()));
            //                } else {
            //                    setTreeList(Helper.buildTree(getMyTableData(),
            //                                                 getStartTreeLevel()));
            //                }
            //
            //            }

            String treeLevel = "0";

            BesharaTree rootNode =
                new BesharaTree("foo-folder", treeDivBean.getBundle().getString(treeDivBean.getRootName()), "0", null,
                                true, false, false, treeLevel);
            rootNode.setPrivateCode(treeLevel);
            treeDivBean.setTreeNodeBase(rootNode);

            for (int i = 0; i < treeDivBean.getTreeList().size(); i++) {
                treeLevel = "0";
                ITreeDTO item = (ITreeDTO)treeDivBean.getTreeList().get(i);
                if (item.getChildernNumbers() != 0) {
                    treeDivBean.setHasChild(true);
                } else {
                    treeDivBean.setHasChild(false);
                }
                BesharaTree treeNode = null;

                if (item.getChildernNumbers() > 0) {
                    treeLevel = "0:" + i;
                }

                if (treeDivBean.getKeyIndex() == -1) {
                    treeNode =
                            new BesharaTree("foo-folder", item.getName(), (String)item.getCode().getKey(), null, treeDivBean.isHasChild(),
                                            item.isBooleanLeafFlag(), item.isBooleanLeafFlag(), treeLevel);
                } else if (treeDivBean.getKeyIndex() > -1) {
                    treeNode =
                            new BesharaTree("foo-folder", item.getName(), item.getCode().getKeys()[treeDivBean.getKeyIndex()].toString(),
                                            item.getCode().getKey().toString(), null, treeDivBean.isHasChild(),
                                            item.isBooleanLeafFlag(), item.isBooleanLeafFlag(), treeLevel);
                }

                treeNode.setPrivateCode(((IEducationLevelsDTO)item).getPrivateCode());

                if (treeNode != null) {
                    treeNode.setParent(treeDivBean.getTreeNodeBase());
                    treeDivBean.getTreeNodeBase().getChildren().add(treeNode);
                    parseTree(item.getChildrenList(), item, treeNode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            treeDivBean.setTreeNodeBase(new BesharaTree("foo-folder",
                                                        treeDivBean.getBundle().getString(treeDivBean.getRootName()),
                                                        "0", null, true, false, false));
        }

        return treeDivBean.getTreeNodeBase();
    }
    
    public void cancelSearchTree() throws DataBaseException, RemoteException, Exception {

        if (treeDivBean.isUsingTreePaging()) {
            setUsingPaging(false);
        }

        if (cancelSearchMethod != null) {

            executeMethodBinding(cancelSearchMethod, null);

        } else {

            genericCancelSerch();

        }
    }
    
    
    void genericCancelSerch() throws DataBaseException, RemoteException, Exception {

        if (treeDivBean.getClientValueBinding() != null) {
            setClient((IBasicClient)evaluateValueBinding(treeDivBean.getClientValueBinding()));
        }
        treeDivBean.cancelSearchTree();

    }

    public void searchTreeFromSpecificChar() throws DataBaseException, RemoteException, Exception {


        if (!enableSearchByCode) {
            super.setSearchType(1);
        }

        if (treeDivBean.isUsingTreePaging()) {
            setSearchMode(true);
            setUsingPaging(true);
            setPagingRequestDTO(treeDivBean.getTreeSearchPagingRequestDTO());
            resetPageIndex();
            setUpdateMyPagedDataModel(true);
        } else {
            treeDivBean.setTreeNodeBase(null);
            treeDivBean.searchTreeFromSpecificChar();
        }

        if (searchTreeMethod != null && !searchTreeMethod.equals("")) {
            try {
                executeMethodBinding(searchTreeMethod, null);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }
    
    public void searchTree() throws DataBaseException, RemoteException, Exception {


        if (!enableSearchByCode) {
            super.setSearchType(1);
        }

        if (treeDivBean.isUsingTreePaging()) {

            setSearchMode(true);
            setUsingPaging(true);

            setPagingRequestDTO(treeDivBean.getTreeSearchPagingRequestDTO());
            resetPageIndex();
            setUpdateMyPagedDataModel(true);

        } else {
            treeDivBean.searchTree();
        }

        if (searchTreeMethod != null && !searchTreeMethod.equals("")) {
            try {
                executeMethodBinding(searchTreeMethod, null);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }

    public BesharaTree parseTree(List treeList, ITreeDTO treeDTO, BesharaTree treeNodeBase) throws DataBaseException,
                                                                                                   RemoteException {
        String treeLevel = treeNodeBase.getTreeNodeLevelsID();
        for (int i = 0; i < treeList.size(); i++) {

            String tempTreeLevel = treeLevel;
            if (treeNodeBase.getChildCount() > 0) {
                tempTreeLevel = treeLevel + ":" + i;
            }
            ITreeDTO treeDTOElem = (ITreeDTO)treeList.get(i);

            if (treeDTOElem.getParentCode() != null &&
                (treeDTOElem.getParentCode().getKey().equals(treeDTO.getCode().getKey()))) {
                if (treeDTOElem.getChildernNumbers() != 0) {
                    treeDivBean.setHasChild(true);
                } else {
                    treeDivBean.setHasChild(false);
                }
                BesharaTree treeNode = null;
                if (treeDivBean.getKeyIndex() == -1) {
                    treeNode =
                            new BesharaTree("foo-folder", treeDTOElem.getName(), (String)treeDTOElem.getCode().getKey(),
                                            (String)treeDTOElem.getParentCode().getKey(), treeDivBean.isHasChild(),
                                            treeDTOElem.isBooleanLeafFlag(), treeDTOElem.isBooleanLeafFlag(),
                                            tempTreeLevel);
                } else if (treeDivBean.getKeyIndex() > -1) {
                    treeNode =
                            new BesharaTree("foo-folder", treeDTOElem.getName(), treeDTOElem.getCode().getKeys()[treeDivBean.getKeyIndex()].toString(),
                                            treeDTOElem.getCode().getKey().toString(),
                                            (String)treeDTOElem.getParentCode().getKey(), treeDivBean.isHasChild(),
                                            treeDTOElem.isBooleanLeafFlag(), treeDTOElem.isBooleanLeafFlag(),
                                            tempTreeLevel);
                }

                treeNode.setPrivateCode(((IEducationLevelsDTO)treeDTOElem).getPrivateCode());

                if (treeNode != null) {
                    treeNode.setParent(treeNodeBase);
                    treeNodeBase.getChildren().add(treeNode);
                    if (treeDTOElem.getChildernNumbers() != 0) {
                        parseTree(treeDTOElem.getChildrenList(), treeDTOElem, treeNode);
                    }
                }

            }
        }
        return treeNodeBase;
    }

        //22

    public void searchByCode(List<BesharaTree> list, String searchedString, String facet) {
        for (BesharaTree child : list) {
            if (searchedString != null && !searchedString.equals("") && child.getTreeId() != null &&
                child.getTreeId().equals(searchedString)) {
                child.setType(facet);
                treeDivBean.setFounded(true);
                setSelectedListSize(1);
                treeDivBean.setSearchResultListSize(treeDivBean.getSearchResultListSize() + 1);
            } else {
                child.setType("foo-folder");
            }
            if (child.getChildCount() != 0) {
                Pattern pattern = null;
                treeDivBean.highlightSearchedResultFromSpecificChar(child.getChildren(), searchedString, facet,
                                                                    pattern);
            }
        }

    }
    
    public void goActionOk(ActionEvent e) {
        setShowTreeContent(false);
        if (treeDivBean.isUsingTreePaging()) {
            setUsingPaging(false);
            setSearchMode(false);
        }

        if (goActionOkMethod != null && !goActionOkMethod.equals("")) {
            try {
                executeMethodBinding(goActionOkMethod, null);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }
    
    public void setShowTreeContent(boolean showTreeContent) {
        this.showTreeContent = showTreeContent;
    }

    public boolean isShowTreeContent() {
        return showTreeContent;
    }

    public void resetTreeDivSearch() {
        treeDivBean.setSelectionNo(0);
        treeDivBean.setSearchQuery("");
        treeDivBean.setSearchType(0);
        treeDivBean.setSearchMode(false);
//        treeDivBean.getHtmlTree().collapsePath(new String[] { "0" });
    }

    public void initializeTreeDiv() {
        treeDivBean.setRootName("ListingCentersPage");
        setClient(QulClientFactory.getCentersClient());
        setPageDTO(QulDTOFactory.createCentersDTO());
        treeDivBean.setDto(QulDTOFactory.createCentersDTO());
        treeDivBean.setDtoDetails(QulDTOFactory.createCentersDTO());
        //        treeDivBean.setClientValueBinding("centerqualificationbean.centerClient");
        treeDivBean.setClientValueBinding(getBeanName() + ".centerClient");
    }
    

    public void cancelTreeDivSearch() {
        getTree();
        resetTreeDivSearch();
        treeDivBean.setSearchMode(false);
        treeDivBean.setSearchQuery("");
        treeDivBean.getHtmlTree().collapsePath(new String[] { "0" });
    }

    public void setShowEduLevelsTree(boolean showEduLevelsTree) {
        this.showEduLevelsTree = showEduLevelsTree;
    }

    public boolean isShowEduLevelsTree() {
        return showEduLevelsTree;
    }

    public void setQulCenterTreeDiv(Boolean qulCenterTreeDiv) {
        this.qulCenterTreeDiv = qulCenterTreeDiv;
    }

    public Boolean getQulCenterTreeDiv() {
        return qulCenterTreeDiv;
    }
    
    public void setSearchTreeMethod(String searchTreeMethod) {
        this.searchTreeMethod = searchTreeMethod;
    }

    public String getSearchTreeMethod() {
        return searchTreeMethod;
    }

    public void setSelectedCountryId(String selectedCountryId) {
        this.selectedCountryId = selectedCountryId;
    }

    public String getSelectedCountryId() {
        return selectedCountryId;
    }

    private void initializeLevelTreeDiv() {
        treeDivBean.setRootName("eduLevel");
        setClient(QulClientFactory.getEducationLevelsClient());
        treeDivBean.setClientValueBinding(getBeanName() + ".levelsClient");
        setPageDTO(QulDTOFactory.createEducationLevelsDTO());
        treeDivBean.setDto(QulDTOFactory.createEducationLevelsDTO());
        treeDivBean.setDtoDetails(QulDTOFactory.createEducationLevelsDTO());
        setEntityKey(QulEntityKeyFactory.createEducationLevelsEntityKey(null));
        setQulCenterTreeDiv(false);
    }

    public StringBuilder buildTreeLevelAsString(ITreeDTO dto) {

        List<String> strList = new ArrayList<String>();
        strList.add(dto.getName());
        while (dto.getParentObject() != null && dto.getParentObject().getCode() != null) {
            strList.add(dto.getParentObject().getName());
            dto = (ITreeDTO)dto.getParentObject();
        }

        StringBuilder str = new StringBuilder();
        for (int x = strList.size() - 1; x >= 0; x--) {
            str.append(strList.get(x) + " / ");
        }
        str.delete(str.length() - 2, str.length());
        return str;
    }

    public void resetLevelsTreeDivSearch() {
        treeDivBean.setSelectionNo(0);
        setSearchQuery("");
        setSearchType(0);
        setSearchMode(false);
        //            eduLevelsTreeDivBean.getHtmlTree().collapsePath(new String[] { "0" });
    }

    private void getLevelTree() {
        setMyTableData(getEducationLevels());
        treeDivBean.setTreeNodeBase(null);
        setShowTreeContent(true);
    }

    public void callCustomBuiltTree() {
        try {
            treeDivBean.buildTree();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }


    public void preOpenTrreLevelDiv() {

    }


    /* public void OpenTreeLevelDiv() {
        showEduLevelsTree = true;
        initializeLevelTreeDiv();
        resetLevelsTreeDivSearch();
        MethodBinding cancelMethodBinding =
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + BEAN_NAME +
                                                                                   ".cancelTreeDivLevelSearch}", null);
        treeDivBean.getCancelSearchCommandButton().setAction(cancelMethodBinding);
        setEnableSearchByCode(true);
        MethodBinding mb =
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + BEAN_NAME + ".addLevelType}",
                                                                                   null);
        treeDivBean.getOkCommandButton().setAction(mb);
        treeDivBean.getOkCommandButton().setReRender("qul_level,dataT_data_panel,OperationBar,paging_panel,cer_level,xyz");
        ValueBinding vb =
            FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + BEAN_NAME + ".eduLevelsTreeDivBean.selectionNo == 0 || !" +
                                                                                  BEAN_NAME +
                                                                                  ".eduLevelsTreeDivBean.enabledNotLeaf}");
        treeDivBean.getOkCommandButton().setValueBinding("disabled", vb);

        getLevelTree();
        callCustomBuiltTree();

    } */

    public void postOpenTrreLevelDiv() {

    }

    public void OpenTreeDiv() {
        showEduLevelsTree = false;
        initializeTreeDiv();
        resetTreeDivSearch();
        setQulCenterTreeDiv(true);
        MethodBinding cancelMethodBinding =
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + getBeanName() +
                                                                                   ".cancelTreeDivSearch}", null);
        treeDivBean.getCancelSearchCommandButton().setAction(cancelMethodBinding);
        treeDivBean.setEnableSearchByCode(true);
        MethodBinding mb =
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + getBeanName() +
                                                                                   ".addQulCenter}", null);
        treeDivBean.getOkCommandButton().setAction(mb);
        treeDivBean.getOkCommandButton().setReRender("open_Level_Div,missionTypeNameID,dataT_data_panel,OperationBar,paging_panel,validationGroup,search_Button");

        ValueBinding vb =
            FacesContext.getCurrentInstance().getApplication().createValueBinding("#{treeDivBean.selectionNo == 0 || !treeDivBean.enabledNotLeaf}");

        treeDivBean.getOkCommandButton().setValueBinding("disabled", vb);

        getTree();
    }


    /* public void doOpenTrreLevelDiv() {
        preOpenTrreLevelDiv();
        OpenTreeLevelDiv();
        postOpenTrreLevelDiv();

    } */

    public void initTreeDivBean() {
        treeDivBean.setBundleName("com.beshara.csc.hr.crs.presentation.resources.crs_");
        initializeTreeDiv();
        setMyTableData(new ArrayList());
    }

    public void searchCertificates() {

        if (getCenterDTO() != null)
            centerQualificationsSearchCriteriaDTO.setCenterCode(((ICentersEntityKey)getCenterDTO().getCode()).getCenterCode());
        if (getLevelDTO() != null) {
            centerQualificationsSearchCriteriaDTO.setLevelCode(getLevelDTO().getCode());

        }
        try {
            setCertificatesList(((ICenterQualificationsClient)getClient()).searchQulCenterBySrchCriteriaDTO(centerQualificationsSearchCriteriaDTO));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setCertificatesList(null);
        } catch (DataBaseException e) {
            e.printStackTrace();
            setCertificatesList(null);
        }

    }


    public List<IEducationLevelsDTO> getEducationLevels() {
        try {
            ICentersDTO centerDTO = (ICentersDTO)treeDivBean.getDtoDetails();
            List<IEducationLevelsDTO> list =
                (List)QulClientFactory.getCenterEduLevelsClient().getAllParentsByCenter(((ICentersEntityKey)centerDTO.getCode()).getCenterCode());
            return list;
        } catch (SharedApplicationException e) {
            return new ArrayList<IEducationLevelsDTO>();
        } catch (DataBaseException e) {
            return new ArrayList<IEducationLevelsDTO>();
        }
    }

    public void setCenterDTO(ITreeDTO centerDTO) {
        this.centerDTO = centerDTO;
    }

    public ITreeDTO getCenterDTO() {
        return centerDTO;
    }

    public void setLevelDTO(ITreeDTO levelDTO) {
        this.levelDTO = levelDTO;
    }

    public ITreeDTO getLevelDTO() {
        return levelDTO;
    }

    public void setCertificatesList(List certificatesList) {
        this.certificatesList = certificatesList;
    }

    public List getCertificatesList() {
        return certificatesList;
    }

    public void setQulCenterCode(String qulCenterCode) {
        this.qulCenterCode = qulCenterCode;
    }

    public String getQulCenterCode() {
        return qulCenterCode;
    }

    public void setSelectedCenterName(String selectedCenterName) {
        this.selectedCenterName = selectedCenterName;
    }

    public String getSelectedCenterName() {
        return selectedCenterName;
    }

    public void setQulCountryCode(String qulCountryCode) {
        this.qulCountryCode = qulCountryCode;
    }

    public String getQulCountryCode() {
        return qulCountryCode;
    }

    public void setCenterCode(Long centerCode) {
        this.centerCode = centerCode;
    }

    public Long getCenterCode() {
        return centerCode;
    }

    public void setQulCentersList(List qulCentersList) {
        this.qulCentersList = qulCentersList;
    }

    public List getQulCentersList() {
        if ((qulCountryCode != null &&
             !qulCountryCode.equals(getManagedConstantsBean().getVirtualStringValueConstant()))) {
            try {
                setQulCentersList(QulClientFactory.getCentersClient().getAllByCountry(InfEntityKeyFactory.createCountriesEntityKey(Long.parseLong(qulCountryCode))));
            } catch (SharedApplicationException e) {
                qulCentersList = new ArrayList();
                e.printStackTrace();
            } catch (DataBaseException e) {
                qulCentersList = new ArrayList();
                e.printStackTrace();
            }
        }
        return qulCentersList;
    }
    
    public void setEnableSearchByCode(boolean enableSearchByCode) {
        this.enableSearchByCode = enableSearchByCode;
    }

    public boolean isEnableSearchByCode() {
        return enableSearchByCode;
    }
    
    public void setCancelSearchMethod(String cancelSearchMethod) {
        this.cancelSearchMethod = cancelSearchMethod;
    }

    public String getCancelSearchMethod() {
        return cancelSearchMethod;
    }
    
    public void setGoActionOkMethod(String goActionOkMethod) {
        this.goActionOkMethod = goActionOkMethod;
    }

    public String getGoActionOkMethod() {
        return goActionOkMethod;
    }


    public static void setBeanName(String beanName) {
        QulAddHelperBean.beanName = beanName;
}

    public static String getBeanName() {
        return beanName;
    }


    
}


