package com.beshara.csc.nl.qul.integration.presentation.backingbean.edulevels;


import com.beshara.base.dto.ITreeDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.nl.qul.business.client.QulClientFactory;
import com.beshara.csc.nl.qul.business.dto.IEducationLevelsDTO;
import com.beshara.csc.nl.qul.business.dto.QulDTOFactory;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.TreeDivBeanChild;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.BesharaTree;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.Helper;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;


public class QulEduLevelsIntegrationBean extends LookUpBaseBean {


    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private TreeDivBeanChild treeEduLevelsDivBean;

    private Long selectedCenterId;
    private String cancelSearchMethod;
    private Boolean enableSearch = true;
    private String returnMethodName;
    private String reRenderFields;
    private String intDivName;
    private String preOpenMethodName;
    private String containerBeanName;

    public QulEduLevelsIntegrationBean(String containerBeanName, String intDivName) {
        super();
        treeEduLevelsDivBean = new TreeDivBeanChild();
        this.containerBeanName = containerBeanName;
        this.intDivName = intDivName;
        initTreeDivBean();
    }

    public void initTreeDivBean() {
        //        treeEduLevelsDivBean.setBundleName("com.beshara.csc.hr.crs.presentation.resources.crs_");
        treeEduLevelsDivBean.setBundle(ResourceBundle.getBundle("com.beshara.csc.nl.qul.integration.presentation.resources.qulintegration"));
        initializeTreeDiv();
    }

    public void initializeTreeDiv() {
        treeEduLevelsDivBean.setRootName("eduLevel");
        treeEduLevelsDivBean.setClient(QulClientFactory.getEducationLevelsClient());
        treeEduLevelsDivBean.setPageDTO(QulDTOFactory.createEducationLevelsDTO());
        treeEduLevelsDivBean.setDto(QulDTOFactory.createEducationLevelsDTO());
        treeEduLevelsDivBean.setDtoDetails(QulDTOFactory.createEducationLevelsDTO());
        treeEduLevelsDivBean.setSelectedPageDTO(QulDTOFactory.createEducationLevelsDTO());

    }

    public void cancelTreeDivSearch() {
        getTree();
        resetTreeDivSearch();
        treeEduLevelsDivBean.setSearchMode(false);
        treeEduLevelsDivBean.setSearchQuery("");
        treeEduLevelsDivBean.getHtmlTree().collapsePath(new String[] { "0" });
    }

    public void getTree() {
        try {
            if (selectedCenterId != null) {
                List<IEducationLevelsDTO> temp =
                    (List)QulClientFactory.getCenterEduLevelsClient().getAllParentsByCenter(selectedCenterId);
                treeEduLevelsDivBean.setMyTableData(temp);
                treeEduLevelsDivBean.setTreeNodeBase(null);
                treeEduLevelsDivBean.setShowTreeContent(true);
                buildTree();
                treeEduLevelsDivBean.getHtmlTree().collapsePath(new String[] { "0" });
            }
        } catch (NoResultException ne) {
            treeEduLevelsDivBean.setMyTableData(new ArrayList());
        } catch (DataBaseException db) {
            treeEduLevelsDivBean.setMyTableData(new ArrayList());
            getSharedUtil().handleException(db);
        } catch (SharedApplicationException e) {
            treeEduLevelsDivBean.setMyTableData(new ArrayList());
            getSharedUtil().handleException(e);
        } catch (Exception e) {
            treeEduLevelsDivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        }
    }

    public void resetTreeDivSearch() {
        treeEduLevelsDivBean.setSelectionNo(0);
        treeEduLevelsDivBean.setSearchQuery("");
        treeEduLevelsDivBean.setSearchType(0);
        treeEduLevelsDivBean.setSearchMode(false);
    }

    public void OpenTreeDiv() {
        initializeTreeDiv();
        resetTreeDivSearch();
        if (cancelSearchMethod != null && !cancelSearchMethod.isEmpty()) {
            treeEduLevelsDivBean.setCancelSearchMethod(cancelSearchMethod);
        } else {
            treeEduLevelsDivBean.setCancelSearchMethod("qulEduLevelsIntegrationBean.cancelTreeDivSearch");
        }
        treeEduLevelsDivBean.setEnableSearchByCode(enableSearch);
        MethodBinding mb =
            FacesContext.getCurrentInstance().getApplication().createMethodBinding(getReturnMethodName(), null);
        treeEduLevelsDivBean.getOkCommandButton().setAction(mb);
        ValueBinding vb =
            FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulEduLevelsIntegrationBean.treeEduLevelsDivBean.selectionNo == 0 || !qulEduLevelsIntegrationBean.treeEduLevelsDivBean.enabledNotLeaf}");
        treeEduLevelsDivBean.getOkCommandButton().setValueBinding("disabled", vb);
        treeEduLevelsDivBean.getOkCommandButton().setReRender(getReRenderFields());
        getTree();
    }

    public void openIntDiv() {
        OpenTreeDiv();
        String ret = "";
        if (getPreOpenMethodName() != null && !getPreOpenMethodName().equals("")) {
            ret = (String)executeMethodBinding(getPreOpenMethodName(), null);
        }
    }

    public String closeIntDiv() {
        String ret = "";
        if (getReturnMethodName() != null && !getReturnMethodName().equals("")) {
            ret = (String)executeMethodBinding(getReturnMethodName(), null);
        }
        return ret;
    }

    public void setCancelSearchMethod(String cancelSearchMethod) {
        this.cancelSearchMethod = cancelSearchMethod;
    }

    public String getCancelSearchMethod() {
        return cancelSearchMethod;
    }

    public void setEnableSearch(Boolean enableSearch) {
        this.enableSearch = enableSearch;
    }

    public Boolean getEnableSearch() {
        return enableSearch;
    }

    public void setReturnMethodName(String returnMethodName) {
        this.returnMethodName = returnMethodName;
    }

    public String getReturnMethodName() {
        return returnMethodName;
    }

    public void setReRenderFields(String reRenderFields) {
        this.reRenderFields = reRenderFields;
    }

    public String getReRenderFields() {
        return reRenderFields;
    }

    public void setIntDivName(String intDivName) {
        this.intDivName = intDivName;
    }

    public String getIntDivName() {
        return intDivName;
    }

    public void setContainerBeanName(String containerBeanName) {
        this.containerBeanName = containerBeanName;
    }

    public String getContainerBeanName() {
        return containerBeanName;
    }

    public void setPreOpenMethodName(String preOpenMethodName) {
        this.preOpenMethodName = preOpenMethodName;
    }

    public String getPreOpenMethodName() {
        return preOpenMethodName;
    }

    protected BaseBean getContainerBean() {
        return (BaseBean)JSFHelper.getValue(getContainerBeanName());
    }


    public IEducationLevelsDTO getSelectedEduLevelDTO() {
        return (IEducationLevelsDTO)treeEduLevelsDivBean.getDtoDetails();
    }

    public void settreeEduLevelsDivBean(TreeDivBeanChild treeEduLevelsDivBean) {
        this.treeEduLevelsDivBean = treeEduLevelsDivBean;
    }

    public TreeDivBeanChild gettreeEduLevelsDivBean() {
        return treeEduLevelsDivBean;
    }

    public void setSelectedCenterId(Long selectedCenterId) {
        this.selectedCenterId = selectedCenterId;
    }

    public Long getSelectedCenterId() {
        return selectedCenterId;
    }


    public BesharaTree buildTree() throws DataBaseException {
        try {
            treeEduLevelsDivBean.setTreeList(treeEduLevelsDivBean.getMyTableData());
            String treeLevel = "0";

            BesharaTree rootNode =
                new BesharaTree("foo-folder", treeEduLevelsDivBean.getBundle().getString(treeEduLevelsDivBean.getRootName()),
                                "0", null, true, false, false, treeLevel);
            rootNode.setPrivateCode(treeLevel);
            treeEduLevelsDivBean.setTreeNodeBase(rootNode);

            for (int i = 0; i < treeEduLevelsDivBean.getTreeList().size(); i++) {
                treeLevel = "0";
                ITreeDTO item = (ITreeDTO)treeEduLevelsDivBean.getTreeList().get(i);
                if (item.getChildernNumbers() != 0) {
                    treeEduLevelsDivBean.setHasChild(true);
                } else {
                    treeEduLevelsDivBean.setHasChild(false);
                }
                BesharaTree treeNode = null;

                if (item.getChildernNumbers() > 0) {
                    treeLevel = "0:" + i;
                }

                if (treeEduLevelsDivBean.getKeyIndex() == -1) {
                    treeNode =
                            new BesharaTree("foo-folder", item.getName(), item.getCode().getKey(), null, treeEduLevelsDivBean.isHasChild(),
                                            item.isBooleanLeafFlag(), item.isBooleanLeafFlag(), treeLevel);
                } else if (treeEduLevelsDivBean.getKeyIndex() > -1) {
                    treeNode =
                            new BesharaTree("foo-folder", item.getName(), item.getCode().getKeys()[treeEduLevelsDivBean.getKeyIndex()].toString(),
                                            item.getCode().getKey().toString(), null,
                                            treeEduLevelsDivBean.isHasChild(), item.isBooleanLeafFlag(),
                                            item.isBooleanLeafFlag(), treeLevel);
                }

                treeNode.setPrivateCode(item.getCode().getKey());

                if (treeNode != null) {
                    treeNode.setParent(treeEduLevelsDivBean.getTreeNodeBase());
                    treeEduLevelsDivBean.getTreeNodeBase().getChildren().add(treeNode);
                    parseTree(item.getChildrenList(), item, treeNode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            treeEduLevelsDivBean.setTreeNodeBase(new BesharaTree("foo-folder",
                                                                 treeEduLevelsDivBean.getBundle().getString(treeEduLevelsDivBean.getRootName()),
                                                                 "0", null, true, false, false));
        }

        try {
            return treeEduLevelsDivBean.getTreeNodeBase();
        } catch (RemoteException e) {
            return null;
        } catch (Exception e) {
            return null;
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
                    treeEduLevelsDivBean.setHasChild(true);
                } else {
                    treeEduLevelsDivBean.setHasChild(false);
                }
                BesharaTree treeNode = null;
                if (treeEduLevelsDivBean.getKeyIndex() == -1) {
                    treeNode =
                            new BesharaTree("foo-folder", treeDTOElem.getName(), (String)treeDTOElem.getCode().getKey(),
                                            (String)treeDTOElem.getParentCode().getKey(),
                                            treeEduLevelsDivBean.isHasChild(), treeDTOElem.isBooleanLeafFlag(),
                                            treeDTOElem.isBooleanLeafFlag(), tempTreeLevel);
                } else if (treeEduLevelsDivBean.getKeyIndex() > -1) {
                    treeNode =
                            new BesharaTree("foo-folder", treeDTOElem.getName(), treeDTOElem.getCode().getKeys()[treeEduLevelsDivBean.getKeyIndex()].toString(),
                                            treeDTOElem.getCode().getKey().toString(),
                                            (String)treeDTOElem.getParentCode().getKey(),
                                            treeEduLevelsDivBean.isHasChild(), treeDTOElem.isBooleanLeafFlag(),
                                            treeDTOElem.isBooleanLeafFlag(), tempTreeLevel);
                }

                treeNode.setPrivateCode(treeDTOElem.getCode().getKey());

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

    public void highlightSearchedResult(List<BesharaTree> list, String searchedString, String facet) {
        for (BesharaTree child : list) {
            if (treeEduLevelsDivBean.getSearchType().intValue() == 0) {
                if (searchedString != null && !searchedString.equals("") &&
                    child.getPrivateCode().equals(searchedString)) {
                    child.setType(facet);
                    treeEduLevelsDivBean.setFounded(true);
                    setSelectedListSize(1);
                    treeEduLevelsDivBean.setSearchResultListSize(treeEduLevelsDivBean.getSearchResultListSize() + 1);
                } else {
                    child.setType("foo-folder");
                }
            } else {
                String childIdentifier = child.getIdentifier().trim().toLowerCase();
                String searchBy = searchedString.trim().toLowerCase();
                if (Helper.searchInTree(searchBy, childIdentifier, false)) {
                    child.setType(facet);
                    treeEduLevelsDivBean.setFounded(true);
                    treeEduLevelsDivBean.setSearchResultListSize(treeEduLevelsDivBean.getSearchResultListSize() + 1);
                    setSelectedListSize(1);
                } else {
                    child.setType("foo-folder");
                }
            }

            if (child.getChildCount() != 0) {
                highlightSearchedResult(child.getChildren(), searchedString, facet);
            }
        }
    }

    public void searchByCode(List<BesharaTree> list, String searchedString, String facet) {
        for (BesharaTree child : list) {
            if (searchedString != null && !searchedString.equals("") && child.getTreeId() != null &&
                child.getTreeId().equals(searchedString)) {
                child.setType(facet);
                treeEduLevelsDivBean.setFounded(true);
                setSelectedListSize(1);
                treeEduLevelsDivBean.setSearchResultListSize(treeEduLevelsDivBean.getSearchResultListSize() + 1);
            } else {
                child.setType("foo-folder");
            }
            if (child.getChildCount() != 0) {
                Pattern pattern = null;
                treeEduLevelsDivBean.highlightSearchedResultFromSpecificChar(child.getChildren(), searchedString,
                                                                             facet, pattern);
            }
        }

    }

    public void searchTreeFromSpecificChar() throws DataBaseException, RemoteException, Exception {
        treeEduLevelsDivBean.setSearchResultListSize(0);
        buildTree();
        List<BesharaTree> list = treeEduLevelsDivBean.getTreeNodeBase().getChildren();

        highlightSearchedResult(list, treeEduLevelsDivBean.getSearchQuery(), "person-highlight");
        if (!treeEduLevelsDivBean.isFounded()) {
            treeEduLevelsDivBean.getHtmlTree().collapseAll();
            treeEduLevelsDivBean.setSerachResult(true);
        }
        if (treeEduLevelsDivBean.isFounded()) {
            treeEduLevelsDivBean.getHtmlTree().expandAll();
        }
        treeEduLevelsDivBean.setSearchMode(true);
        treeEduLevelsDivBean.setSelectionNo(0);
    }

    public List getTotalList() {
        return new ArrayList();
    }
}
