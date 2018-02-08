package com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.dto.TreeDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.querybuilder.ArabicAlphabetConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyListBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.Helper;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.tableheader.ModuleObject;
import com.beshara.jsfbase.csc.util.tableheader.TableHeaderBean;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.tree2.HtmlTree;


public class TreeKwtWorkDataMany extends ManyToManyListBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    private boolean hasChild = false;
    private String parentLevel;
    private String parentName;
    private String levelName = "";
    private boolean booleanLeafFlag = true;
    private boolean renderEdit = false;
    private boolean success = false;
    private String rootName;
    private boolean founded = false;
    private HtmlTree htmlTree = new HtmlTree(); /* hold the binding value of tree2 in jsf */
    private Long virtualLevelCode = ISystemConstant.VIRTUAL_VALUE;
    private List treeList = new ArrayList(); /* list of available categories in the System */
    protected ResourceBundle bundle = null;
    private ResourceBundle globalResource = null;
    private BesharaKwtWorkDataTree treeNodeBase;
    private String selectedNodeId;
    private ITreeDTO dto = new TreeDTO();
    private ITreeDTO dtoManyToMany = new TreeDTO();
    private ITreeDTO dtoDetails = new TreeDTO();
    private String msgTreeAdd;
    private boolean serachResult = false;

    private boolean enabledNotHasChlid = true;
    private boolean enabledRoot = false;
    private boolean enabledNotLeaf = true;

    private int selectionNo;

    private int keyIndex = -1;

    // added by AMR_ABDO
    private String treeNodeNameForCollapseExpand = "0";

    //Paging in tree, by AMR_ABDO
    private boolean usingTreePaging;
    private PagingRequestDTO treeSearchPagingRequestDTO;
    private PagingRequestDTO treeListPagingRequestDTO;

    private IBasicDTO highLightedDTO;

    private Integer pagedTreeListSize;
    //End Paging in tree, by AMR_ABDO
    private String selectedNodeTreeLevelId;
    List<BesharaKwtWorkDataTree> expandedNodes = new ArrayList<BesharaKwtWorkDataTree>();
    String selectedStyleClass = "treepage-link";
    String nodeId;
    private int searchResultListSize;
    TableHeaderBean tableHeaderBean;
    List<ModuleObject> objects = new ArrayList<ModuleObject>();

    public TreeKwtWorkDataMany() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        tableHeaderBean = (TableHeaderBean)app.createValueBinding("#{TableHeaderBean}").getValue(fc);
    }


    public void resetTableHeaderStatus() {
        FacesContext.getCurrentInstance().getApplication().createValueBinding("#{TableHeaderBean.selectedCheckBoxesCount}").setValue(FacesContext.getCurrentInstance(),
                                                                                                                                     new Integer(0));
    }

    public void checkControlsHeaderStatus() {
        TableHeaderBean tableHeaderBean =
            (TableHeaderBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{TableHeaderBean}").getValue(FacesContext.getCurrentInstance());
        tableHeaderBean.setSelectedCheckBoxesCount(tableHeaderBean.getSelectedCheckBoxesCount() - 1);
    }

    /**
     * this method responsible for getting the root element[s] of the tree
     * @return BesharaKwtWorkDataTree treeNodeBase
     * @throws DataBaseException
     * @Updated-by AMR_ABDO, used in Paged Tree
     */

    public BesharaKwtWorkDataTree buildTree() throws DataBaseException {

        try {

            if (isUsingTreePaging()) {
                treeList = getMyTableData();
            } else {
                treeList = Helper.buildTree((List<IBasicDTO>)getMyTableData());
            }

            String treeLevel = "0";
            treeNodeBase =
                    new BesharaKwtWorkDataTree("foo-folder", getBundle().getString(rootName), "0", "0", null, true,
                                               false, false, treeLevel, null);

            for (int i = 0; i < treeList.size(); i++) {
                treeLevel = "0";
                ITreeDTO item = (ITreeDTO)treeList.get(i);
                if (item.getChildernNumbers() != 0) {
                    hasChild = true;
                } else {
                    hasChild = false;
                }
                BesharaKwtWorkDataTree treeNode = null;
                IKwtWorkDataTreeDTO _treeDTOElem = (IKwtWorkDataTreeDTO)item;
                //  if (item.getChildernNumbers() > 0) {
                treeLevel = "0:" + i;
                //  }

                treeNode =
                        new BesharaKwtWorkDataTree("foo-folder", item.getName(), item.getCode().getKeys()[0].toString(),
                                                   item.getCode().getKey().toString(), null, hasChild,
                                                   item.isBooleanLeafFlag(), item.isBooleanLeafFlag(), treeLevel,
                                                   _treeDTOElem);


                if (treeNode != null) {
                    treeNode.setParent(treeNodeBase);
                    treeNodeBase.getChildren().add(treeNode);
                    parseTree(item.getChildrenList(), item, treeNode);
                }
                //the highlightednode levelId
                //             if (isUsingTreePaging()) {
                //             if(getSelectedNodeId()!=null && getSelectedNodeId().equals(treeNode.getTreeId()))
                //                 setSelectedNodeTreeLevelId(treeNode.getTreeNodeLevelsID());
                //             }
            }
        } catch (Exception e) {
            e.printStackTrace();
            treeNodeBase =
                    new BesharaKwtWorkDataTree("foo-folder", getBundle().getString(getRootName()), "0", "0", null,
                                               true, false, false, 0, null);
        }

        return treeNodeBase;
    }

    /**
        this method responsible for parsing the list to build the tree Structure
     * @param treeList holds all the categories returned from the system
     * @param treeDTO  object from CatsDTO which holds the current node
     * @param treeNodeBase object from BesharaKwtWorkDataTree which holds the parent of the node to be drawed
     **/
    public BesharaKwtWorkDataTree parseTree(List treeList, ITreeDTO treeDTO,
                                            BesharaKwtWorkDataTree treeNodeBase) throws DataBaseException,
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
                    hasChild = true;
                } else {
                    hasChild = false;
                }
                BesharaKwtWorkDataTree treeNode = null;
                IKwtWorkDataTreeDTO _treeDTOElem = (IKwtWorkDataTreeDTO)treeDTOElem;
                treeNode =
                        new BesharaKwtWorkDataTree("foo-folder", treeDTOElem.getName(), treeDTOElem.getCode().getKeys()[0].toString(),
                                                   treeDTOElem.getCode().getKey().toString(),
                                                   (String)treeDTOElem.getParentCode().getKey(), hasChild,
                                                   treeDTOElem.isBooleanLeafFlag(), treeDTOElem.isBooleanLeafFlag(),
                                                   tempTreeLevel, _treeDTOElem);

                if (treeNode != null) {
                    treeNodeBase.getChildren().add(treeNode);
                    if (treeDTOElem.getChildernNumbers() != 0) {
                        parseTree(treeDTOElem.getChildrenList(), treeDTOElem, treeNode);
                    }
                }
                //                if (isUsingTreePaging()) {
                //                if(getSelectedNodeId()!=null && getSelectedNodeId().equals(treeNode.getTreeId()))
                //                    setSelectedNodeTreeLevelId(treeNode.getTreeNodeLevelsID());
                //                }

            }


        }
        return treeNodeBase;
    }


    /**
            this method overwriten from base bean
     **/
    public void refreshAll() {
        treeNodeBase = null;
    }

    public Integer getListSize() throws DataBaseException {
        // COMMENTED BY AMR ABDO ( Paging AT Tree )
        // COMMENTED BY AMR ABDO ( Paging AT Tree )
        //       if (isSearchMode()) {
        //            return searchResultListSize;
        //       } else {
        //            if (usingTreePaging) {
        //                return getPagedTreeListSize();
        //            } else {
        //                return super.getListSize();
        //            }
        //        }
        //added by taha abdul mejid to fix paged tree search recommended by ahmed abdul fatah
        if (usingTreePaging) {
            return getPagedTreeListSize();
        } else if (isSearchMode()) {
            return searchResultListSize;
        } else {
            return super.getListSize();


        }
    } 

    /**
     * Purpose:To Be Overided By User At Paging for get IEntityKey From NodeCode
     * Creation/Modification History :
     * 1.1 - Developer Name: Amr Abdo
     * 1.2 - Date: 14-7-2009
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public IEntityKey getSelectedEntityKey(String nodeId) {
        // For Remove Warning Only
        nodeId = null;
        return null;
    }

    /**
     * Purpose:return composite key of submitted item
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date: 22-1-2009
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public String getFullEntityKey(List<BesharaKwtWorkDataTree> list, String displayedKey) {
        for (BesharaKwtWorkDataTree child : list) {
            if (child.getTreeId().equals(displayedKey))
                return child.getDetailId();

            if (child.getChildCount() != 0) {
                String ret = getFullEntityKey((List<BesharaKwtWorkDataTree>)child.getChildren(), displayedKey);
                if (ret != null && !ret.equals(""))
                    return ret;
            }
        }
        return null;
    }

    public List getParentList(ITreeDTO nodeDTO) {
        List parentList = new ArrayList();
        if (nodeDTO != null) {
            for (int i = nodeDTO.getTreeLevel().intValue(); i > nodeDTO.getTreeLevel(); i--) {
                if (nodeDTO.getParentObject() != null) {
                    parentList.add(nodeDTO.getParentObject());
                }
            }
        }
        return parentList;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showTreePage();
        return app;
    }


    public void preAddTree() {
        setSuccess(false);
    }

    public String goAdd() {
        preAddTree();
        getDtoManyToMany().setParentObject(getDtoDetails());
        setPageDTO((IBasicDTO)getDtoManyToMany());
        setLovBaseBean(new LOVBaseBean());
        return super.goAdd();
        //return null;
    }


    public void preEditTree() {

        if (getSelectedNodeId() != null && !getSelectedNodeId().equals("0")) {
            // to get composte key
            if (keyIndex > -1 && !usingTreePaging) {
                try {
                    selectedNodeId =
                            getFullEntityKey((List<BesharaKwtWorkDataTree>)getTreeNodeBase().getChildren(), selectedNodeId);
                } catch (Exception f) {
                    f.printStackTrace();
                }
            }
        }
        if (getSelectedNodeId() != null && !getSelectedNodeId().equals("0")) {
            try {
                if (!usingTreePaging) {
                    setEntityKey(Helper.getEntityKey((List<IBasicDTO>)getMyTableData(), getSelectedNodeId()));
                } else {
                    setEntityKey(getSelectedEntityKey(getSelectedNodeId()));
                }
                setDto((ITreeDTO)preEdit());
                this.setAlreadyDeleted(false);

            } catch (ItemNotFoundException e) {
                this.setAlreadyDeleted(true);

            } catch (Exception e) {
                getSharedUtil().setErrMsgValue(e.getMessage());
                e.printStackTrace();
            }

        }
        if (getDto().getParentObject() == null) {
            IBasicDTO parent = new BasicDTO();
            parent.setName(getBundle().getString(rootName));
            getDto().setParentObject(parent);
        }
    }

    public String goEdit() throws DataBaseException, SharedApplicationException {
        preEditTree();
        getSelectedDTOS().clear();
        getSelectedDTOS().add((IBasicDTO)getDto());
        return super.goEdit();
        //  return null;
    }

    /**
     * @Used-in Paged Tree Div
     * @Auther AMR_ABDO
     */
    public void expand(ActionEvent event) {

        if (isUsingTreePaging()) {

            System.out.println("Entering expand()");
            UIComponent component = (UIComponent)event.getSource();

            while (!(component != null && component instanceof HtmlTree)) {
                component = component.getParent();
            }

            if (component != null) {

                HtmlTree tree = (HtmlTree)component;
                BesharaKwtWorkDataTree node = (BesharaKwtWorkDataTree)tree.getNode();

                // check that the node has been expanded before
                if (node.getChildren().size() == 0) {

                    PagingRequestDTO pagingRequestDTO = getTreeListPagingRequestDTO();

                    if (pagingRequestDTO != null) {

                        pagingRequestDTO.setParams(new Object[] { node });

                        String methodBindingExpression =
                            pagingRequestDTO.getBeanName() + "." + pagingRequestDTO.getMethodName();

                        PagingResponseDTO responseDTO =
                            (PagingResponseDTO)executeMethodBindingWithParams(methodBindingExpression,
                                                                              new Object[] { pagingRequestDTO },
                                                                              new Class[] { PagingRequestDTO.class });

                        List children = responseDTO.getResultList();
                        if (children != null && children.size() > 0) {
                            buildChildrenTree(node, children);
                        } else {
                            try {
                                getMyTableData();
                            } catch (DataBaseException e) {
                                e.printStackTrace();
                            }
                        }

                        node.setHasChild(true);
                        String nodePath = node.getPath();
                        if (node.isExpanded()) {
                            node.setExpanded(false);
                            tree.collapsePath(new String[] { nodePath });
                        } else {
                            node.setExpanded(true);
                            tree.expandPath(new String[] { nodePath });
                        }

                    }

                } else {
                    String nodePath = node.getPath();
                    if (node.isExpanded()) {
                        node.setExpanded(false);
                        tree.collapsePath(new String[] { nodePath });
                    } else {
                        node.setExpanded(true);
                        tree.expandPath(new String[] { nodePath });
                    }
                }

            }

        }

    }

    /**
     * Purpose: get All Parents of node and put its at a Stack
     * Creation/Modification History :
     * 1.1 - Developer Name: AMR_ABDO
     * 1.2 - Date: 22-7-2009
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public Stack getAllParents(ITreeDTO treeDTO) {
        Stack stack = new Stack();
        do {
            stack.push(treeDTO);
            treeDTO = (ITreeDTO)treeDTO.getParentObject();
        } while (treeDTO != null);

        return stack;
    }

    /**
     * Purpose: expand All Nodes For Specific Node
     * Creation/Modification History :
     * 1.1 - Developer Name: AMR_ABDO
     * 1.2 - Date: 22-7-2009
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void expandAllForSpecificNode(ITreeDTO treeDTO) {
        String nodeID = treeDTO.getCode().getKey().toString();
        try {
            treeDTO = (ITreeDTO)getClient().getById(treeDTO.getCode());
        } catch (SharedApplicationException e) {
            ;
        } catch (DataBaseException e) {
            ;
        }
        Stack stack = getAllParents(treeDTO);
        boolean highLigthChildNode = false;
        if (stack.size() == 1) {
            try {
                BesharaKwtWorkDataTree childNode = getSpecificNode(getTreeNodeBase().getChildren(), nodeID);
                // childNode.setType("person-highlight");
                //expandedNodes.add()
                SetSelectedNode(nodeID);
                //focus on the highlightednode
                setSelectedNodeTreeLevelId(childNode.getTreeNodeLevelsID());
            } catch (Exception e) {
                ;
            }
            getHtmlTree().expandPath(new String[] { "0" });
            return;
        }

        while (stack.size() > 1) {
            if (stack.size() == 2) {
                highLigthChildNode = true;
            }
            expandSpecificNode((ITreeDTO)stack.pop(), highLigthChildNode, nodeID);
        }
    }

    /**
     * Purpose: expand All Nodes For Specific Node List
     * Creation/Modification History :
     * 1.1 - Developer Name: AMR_ABDO
     * 1.2 - Date: 22-7-2009
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void expandAllForSpecificNodeList(List<ITreeDTO> listTreeDTO) {

        for (ITreeDTO treeDTO : listTreeDTO) {
            expandAllForSpecificNode(treeDTO);
        }
    }

    /**
     * Purpose: get Specific Node From Tree (BesharaKwtWorkDataTree)
     * Creation/Modification History :
     * 1.1 - Developer Name: AMR_ABDO
     * 1.2 - Date: 22-7-2009
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public BesharaKwtWorkDataTree getSpecificNode(List<BesharaKwtWorkDataTree> list, String displayedKey) {
        for (BesharaKwtWorkDataTree child : list) {
            if ((child.getDetailId() != null && child.getDetailId().equals(displayedKey)) ||
                (child.getTreeId() != null && child.getTreeId().equals(displayedKey)))
                return child;

            if (child.getChildCount() != 0) {
                BesharaKwtWorkDataTree ret = getSpecificNode(child.getChildren(), displayedKey);
                if (ret != null)
                    return ret;
            }
        }
        return null;
    }

    /**
     * Purpose: expand Specific Node by get(its children ) and highLihted it if at last level
     * Creation/Modification History :
     * 1.1 - Developer Name: AMR_ABDO
     * 1.2 - Date: 22-7-2009
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    protected void expandSpecificNode(ITreeDTO treeDTO, boolean highLigthChildNode, String childNodeID) {

        BesharaKwtWorkDataTree node = null;

        try {
            node = getSpecificNode(getTreeNodeBase().getChildren(), treeDTO.getCode().getKey().toString());
            treeNodeBase.setExpanded(true);
        } catch (Exception e) {
            ;
        }
        if (node.getChildren().size() == 0) {
            PagingRequestDTO pagingRequestDTO = getTreeListPagingRequestDTO();

            if (pagingRequestDTO != null) {

                pagingRequestDTO.setParams(new Object[] { node });

                String methodBindingExpression =
                    pagingRequestDTO.getBeanName() + "." + pagingRequestDTO.getMethodName();

                PagingResponseDTO responseDTO =
                    (PagingResponseDTO)executeMethodBindingWithParams(methodBindingExpression,
                                                                      new Object[] { pagingRequestDTO },
                                                                      new Class[] { PagingRequestDTO.class });

                List children = responseDTO.getResultList();
                if (children != null && children.size() > 0) {
                    buildChildrenTree(node, children);
                }
            }
        }

        if (highLigthChildNode) {
            try {
                BesharaKwtWorkDataTree childNode = getSpecificNode(getTreeNodeBase().getChildren(), childNodeID);
                //   childNode.setType("person-highlight");
                SetSelectedNode(childNodeID);
                //focus on the highlightednode
                setSelectedNodeTreeLevelId(childNode.getTreeNodeLevelsID());
            } catch (Exception e) {
                ;
            }
        }
        //            try{
        //                ValueBinding vBinding=FacesContext.getCurrentInstance().getApplication().createValueBinding("#{workCentersListBean.treeNodeBase}");
        //                getHtmlTree().setValueBinding("value",vBinding);

        getHtmlTree().expandPath(new String[] { "0" });
        //getHtmlTree().getDataModel().getTreeState().expandPath(new String[] { "0" });
        //            }catch(Exception ee){
        //                ee.printStackTrace();
        //            }

        node.setExpanded(true);
        if (node.getChildren() != null && node.getChildren().size() > 0) {
            node.setHasChild(true);
        }
        String nodePath = node.getPath();
        System.out.println("node path ------------------------>  " + nodePath);
        getHtmlTree().expandPath(new String[] { nodePath });
        //getHtmlTree().getDataModel().getTreeState().expandPath(new String[] { nodePath });

    }

    /**
     * @Used-in Paged Tree Div
     * @Auther AMR_ABDO
     */
    public void buildChildrenTree(BesharaKwtWorkDataTree parentNode, List childrenList) {

        try {
            String parentLevelId = parentNode.getTreeNodeLevelsID();
            int index = 0;
            for (ITreeDTO item : (List<ITreeDTO>)childrenList) {

                if (item.getChildernNumbers() != 0) {
                    hasChild = true;
                } else {
                    hasChild = false;
                }
                BesharaKwtWorkDataTree treeNode = null;
                IKwtWorkDataTreeDTO _treeDTOElem = (IKwtWorkDataTreeDTO)item;
                treeNode =
                        new BesharaKwtWorkDataTree("foo-folder", item.getName(), item.getCode().getKeys()[getKeyIndex()].toString(),
                                                   item.getCode().getKey().toString(), null, hasChild,
                                                   item.isBooleanLeafFlag(), item.isBooleanLeafFlag(), 0,
                                                   _treeDTOElem);


                if (treeNode != null) {
                    treeNode.setParent(parentNode);
                    parentNode.getChildren().add(treeNode);
                    parseTree(item.getChildrenList(), item, treeNode);
                }
                treeNode.setTreeNodeLevelsID(parentLevelId + ":" + index);
                index++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Purpose:to make   search by Code Or Name  with Specific Character
     * Creation/Modification History :
     * 1.1 - Developer Name: Amr GAlal
     * 1.2 - Date: 24-10-2010
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void searchTreeFromSpecificChar() throws DataBaseException, RemoteException, Exception {
        try {
            searchResultListSize = 0;
            buildTree();
            List<BesharaKwtWorkDataTree> list = getTreeNodeBase().getChildren();
            Pattern pattern = null;
            if (this.getSearchType().intValue() == 1) {
                pattern = getSimilerCharRegexPattern(this.getSearchQuery());
            } else {
                pattern = null;
            }
            highlightSearchedResultFromSpecificChar(list, this.getSearchQuery(), "person-highlight", pattern);
            if (!founded) {
                htmlTree.collapseAll();
                setSerachResult(true);
            }
            if (founded) {
                htmlTree.expandAll();
            }
            setSearchMode(true);
            setSelectionNo(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Purpose:to make   search by Code Or Name  with Specific Character and highlightSearchedResult
     * Creation/Modification History :
     * 1.1 - Developer Name: Amr GAlal
     * 1.2 - Date: 24-10-2010
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void highlightSearchedResultFromSpecificChar(List<BesharaKwtWorkDataTree> list, String searchedString,
                                                        String facet, Pattern pattern) {

        if (this.getSearchType().intValue() == 0) {
            searchByCode(list, searchedString, facet);
        } else {
            searchByName(list, searchedString, facet, pattern);
        }
    }


    public void searchByCode(List<BesharaKwtWorkDataTree> list, String searchedString, String facet) {
        for (BesharaKwtWorkDataTree child : list) {
            if (searchedString != null && !searchedString.equals("") && child.getTreeId().equals(searchedString)) {
                child.setType(facet);
                this.setFounded(true);
                setSelectedListSize(1);
                searchResultListSize += 1;
            } else {
                child.setType("foo-folder");
            }
            if (child.getChildCount() != 0) {
                Pattern pattern = null;
                highlightSearchedResultFromSpecificChar(child.getChildren(), searchedString, facet, pattern);
            }
        }

    }

    public void searchByName(List<BesharaKwtWorkDataTree> list, String searchedString, String facet, Pattern pattern) {
        for (BesharaKwtWorkDataTree child : list) {
            String childIdentifier = child.getIdentifier();
            Matcher fit = pattern.matcher(childIdentifier);
            if (fit.matches()) {
                child.setType(facet);
                this.setFounded(true);
                searchResultListSize += 1;
                setSelectedListSize(1);
            }
            if (child.getChildCount() != 0) {
                highlightSearchedResultFromSpecificChar(child.getChildren(), searchedString, facet, pattern);
            }
        }

    }

    public Pattern getSimilerCharRegexPattern(String searchedString) {
        String[] tokens = searchedString.split("[\\s%]+");
        String pattern = ".*";
        String temp = null;
        for (String token : tokens) {
            if (!token.equals("")) {
                temp = new String(token);
                String checkStartWith = temp.substring(0, 1);
                String checkEndWith = temp.substring(temp.length() - 1, temp.length());
                if ((checkStartWith.equals(ArabicAlphabetConstants.AlephWithOutHamza + "") ||
                     checkStartWith.equals(ArabicAlphabetConstants.AlephWithUpperHamza + "") ||
                     checkStartWith.equals(ArabicAlphabetConstants.AlephWithLowerHamza + "") ||
                     checkStartWith.equals(ArabicAlphabetConstants.AlephWithMadda + "")) && temp.length() >= 1) {
                    String aleph =
                        "[" + ArabicAlphabetConstants.AlephWithOutHamza + "" + ArabicAlphabetConstants.AlephWithUpperHamza +
                        "" + ArabicAlphabetConstants.AlephWithLowerHamza + "" +
                        ArabicAlphabetConstants.AlephWithMadda + "" + "]";
                    temp = temp.substring(1);
                    temp = aleph + temp;
                }
                if ((checkEndWith.equals(ArabicAlphabetConstants.Haa + "") ||
                     checkEndWith.equals(ArabicAlphabetConstants.TaaMarbota + "")) && temp.length() >= 1) {
                    String aleph =
                        "[" + ArabicAlphabetConstants.Haa + "" + ArabicAlphabetConstants.TaaMarbota + "" + "]";
                    temp = temp.substring(0, temp.length() - 1);
                    temp = temp + aleph;
                }
                if ((checkEndWith.equals(ArabicAlphabetConstants.Yaa + "") ||
                     checkEndWith.equals(ArabicAlphabetConstants.AlephMaksora + "")) && temp.length() >= 1) {
                    String aleph =
                        "[" + ArabicAlphabetConstants.Yaa + "" + ArabicAlphabetConstants.AlephMaksora + "" + "]";
                    temp = temp.substring(0, temp.length() - 1);
                    temp = temp + aleph;
                }
                pattern = pattern + temp + ".*";

                if (searchedString.startsWith("%") && !searchedString.endsWith("%")) {
                    pattern = pattern.substring(0, pattern.length() - 2);
                } else {
                    if (!searchedString.startsWith("%") && searchedString.endsWith("%")) {
                        pattern = pattern.substring(2);
                    }
                }
            }
        }


        Pattern searchStringPatten = Pattern.compile(pattern);
        return searchStringPatten;
    }

    public void searchTree() throws DataBaseException, RemoteException, Exception {
        searchResultListSize = 0;
        List list = getTreeNodeBase().getChildren();
        highlightSearchedResult((List<BesharaKwtWorkDataTree>)list, this.getSearchQuery(), "person-highlight");
        if (!founded) {
            htmlTree.collapseAll();
            setSerachResult(true);
        }
        if (founded) {
            htmlTree.expandAll();
        }
        setSearchMode(true);
        setSelectionNo(0);
    }

    /**
     * highlightSearchedResult() for node entityName searching  purpose
     */
    public void highlightSearchedResult(List<BesharaKwtWorkDataTree> list, String searchedString, String facet) {
        for (BesharaKwtWorkDataTree child : list) {
            if (this.getSearchType().intValue() == 0) {
                if (searchedString != null && !searchedString.equals("") && child.getTreeId().equals(searchedString)) {
                    child.setType(facet);
                    this.setFounded(true);
                    setSelectedListSize(1);
                    searchResultListSize += 1;
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
                    searchResultListSize += 1;
                } else {
                    child.setType("foo-folder");
                }
            }
            if (child.getChildCount() != 0) {
                highlightSearchedResult((List<BesharaKwtWorkDataTree>)child.getChildren(), searchedString, facet);
            }
        }

    }

    /**
     * highlightAdd/Edit Result() for node entityName searching  purpose
     */
    public void highlightAddEditResult(List<BesharaKwtWorkDataTree> list, String searchedString, String facet) {
        for (BesharaKwtWorkDataTree child : list) {
            Long nodeCode = Long.parseLong(child.getTreeId());
            Long searchedLong = Long.parseLong(searchedString);
            if (searchedLong.equals(nodeCode)) {
                child.setType(facet);
            } else {
                child.setType("foo-folder");
            }
            if (child.getChildCount() != 0) {
                highlightSearchedResult((List<BesharaKwtWorkDataTree>)child.getChildren(), searchedString, facet);
            }
        }
    }

    public void addNew() throws DataBaseException, Exception {
        saveItem();
        if (getPageDTO().getCode() != null) {
            List list = buildTree().getChildren();
            highlightAddEditResult((List<BesharaKwtWorkDataTree>)list, (String)getPageDTO().getCode().getKey(),
                                   "person-highlight");
        }
    }

    public void addAndNew() throws DataBaseException, Exception {
        success = false;
        saveItem();
        if (getErrorMsg() == null || (getErrorMsg() != null && getErrorMsg().equals(""))) {
            setMsgTreeAdd("SuccessAdds");
            success = true;
        }

    }

    public void saveItem() throws DataBaseException, Exception {
        ITreeDTO treeDTO = new TreeDTO();
        if (!parentLevel.equals("-100")) {
            if (isUsingTreePaging()) {
                setEntityKey(getSelectedEntityKey(parentLevel));
            } else {
                setEntityKey(Helper.getEntityKey((List<IBasicDTO>)getMyTableData(), parentLevel));
            }
            treeDTO.setCode(getEntityKey());
            dto.setParentObject(treeDTO);
        } else {
            dto.setParentObject(null);
        }
        dto.setName(levelName);
        dto.setBooleanLeafFlag(booleanLeafFlag);
        setPageDTO((IBasicDTO)dto);
        this.add();
        setLevelName("");
        setBooleanLeafFlag(true);
    }

    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        if (dto.getParentObject() != null && dto.getParentObject().getCode() == null) {
            dto.setParentObject(null);
        }
        setPageDTO((IBasicDTO)dto);
        super.edit();
        if (getPageDTO().getCode() != null) {
            setSelectedNodeId(null);
            List list;
            try {
                list = buildTree().getChildren();
                highlightAddEditResult((List<BesharaKwtWorkDataTree>)list, (String)getPageDTO().getCode().getKey(),
                                       "person-highlight");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void deleteItem() throws RemoteException, Exception {
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
        } catch (SharedApplicationException e) {
            //getSharedUtil().setErrMsgValue(e.getMessage());
            resultDTO.setDatabaseException(new DataBaseException(e.getMessage()));
            resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
            //resultDTO.setStatus(e.getMessage());
        } catch (DataBaseException e) {
            resultDTO.setDatabaseException(new DataBaseException(e.getMessage()));
            resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
        }
        getDeleteStatusDTOS().add(resultDTO);
        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");
        setSelectionNo(0);
        if (this.getSelectedDTOS() != null) { // as the edit button still active by Ashraf Gaber
            this.getSelectedDTOS().clear();
        }

        setLovBaseBean(new LOVBaseBean());
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
            setSelectionNo(0);
            buildTree();
        }
        cancelSearchTreeDiv();
    }

    public void cancelAddTree() throws DataBaseException, Exception {
        setLevelName("");
        setBooleanLeafFlag(true);
        getSharedUtil().setErrMsgValue(null);
        getSharedUtil().setSuccessMsgValue(null);
        if (!isSearchMode())
            cancelSearchTree();
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setGlobalResource(ResourceBundle globalResource) {
        this.globalResource = globalResource;
    }

    public ResourceBundle getGlobalResource() {
        return globalResource;
    }

    public void setTreeNodeBase(BesharaKwtWorkDataTree treeNodeBase) {
        this.treeNodeBase = treeNodeBase;
    }

    public BesharaKwtWorkDataTree getTreeNodeBase() throws DataBaseException, RemoteException, Exception {
        if (treeNodeBase == null) {
            buildTree();
            if (highLightedDTO != null) {
                expandAllForSpecificNode((ITreeDTO)highLightedDTO);
            }
        }
        return treeNodeBase;
    }

    public void setVirtualLevelCode(Long virtualLevelCode) {
        this.virtualLevelCode = virtualLevelCode;
    }

    public Long getVirtualLevelCode() {
        return virtualLevelCode;
    }

    public void setTreeList(List treeList) {
        this.treeList = treeList;
    }

    public List getTreeList() {
        return treeList;
    }

    public void setFounded(boolean founded) {
        this.founded = founded;
    }

    public boolean isFounded() {
        return founded;
    }

    public void setHtmlTree(HtmlTree htmlTree) {
        this.htmlTree = htmlTree;
    }

    public HtmlTree getHtmlTree() {
        return htmlTree;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public String getRootName() {
        return rootName;
    }

    public void setSelectedNodeId(String selectedNodeId) {
        this.selectedNodeId = selectedNodeId;
    }

    public String getSelectedNodeId() {
        return selectedNodeId;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setParentLevel(String parentLevel) {
        this.parentLevel = parentLevel;
    }

    public String getParentLevel() {
        return parentLevel;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName.trim();
    }

    public String getLevelName() {
        return levelName;
    }

    public void setBooleanLeafFlag(boolean booleanLeafFlag) {
        this.booleanLeafFlag = booleanLeafFlag;
    }

    public boolean isBooleanLeafFlag() {
        return booleanLeafFlag;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setDto(ITreeDTO dto) {
        this.dto = dto;
    }

    public ITreeDTO getDto() {
        return dto;
    }

    public void setRenderEdit(boolean renderEdit) {
        this.renderEdit = renderEdit;
    }

    public boolean isRenderEdit() {
        return renderEdit;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setDtoDetails(ITreeDTO dtoDetails) {
        this.dtoDetails = dtoDetails;
    }

    public ITreeDTO getDtoDetails() {
        return dtoDetails;
    }

    //    public void generateMenuIndexes(ModuleObject currentObj, String currentMenuItemIndex) {
    //
    //        ModuleObject obj = null;
    //
    //        List<ModuleObject> list = currentObj.getChildsList();
    //        if (list.size() == 0) {
    //            if (currentObj.getObjPage() != null) {
    //                getAllIndexes() += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
    //            }
    //            if (currentObj.getSelectionFlag().equals("0") && currentObj.getObjPage() != null) { //Enabled in all cases
    //                getCasesArray()[0] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
    //                getCasesArray()[1] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
    //                getCasesArray()[2] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
    //                getCasesArray()[3] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
    //            }
    //            if (currentObj.getSelectionFlag().equals("1") && currentObj.getObjPage() != null) { //Not Has Child
    //                getCasesArray()[1] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
    //            }
    //            if (currentObj.getSelectionFlag().equals("2") && currentObj.getObjPage() != null) { //Not Root
    //                getCasesArray()[2] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
    //            }
    //            if (currentObj.getSelectionFlag().equals("3") && currentObj.getObjPage() != null) { //Not Leaf
    //                getCasesArray()[3] += "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + ",";
    //            }
    //            //            if (currentObj.getSelectionFlag().equals("4") &&
    //            //                currentObj.getObjPage() != null) { //Leaf OnlYYYYY..
    //            //                casesArray[4] +=
    //            //                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() +
    //            //                        ",";
    //            //            }
    //
    //        }
    //
    //
    //        for (int i = 0; i < list.size(); i++) {
    //            obj = list.get(i);
    //            int index = i + 5;
    //            generateMenuIndexes(obj, currentMenuItemIndex + "-" + index);
    //        }
    //    }

    //    public String[] getCasesArray() {
    //        objects = tableHeaderBean.getObjects();
    //        if (getCasesArray() == null) {
    //            getCasesArray() = new String[10];
    //            for (int i = 0; i < getCasesArray().length; i++) {
    //                getCasesArray()[i] = "";
    //            }
    //
    //        }
    //        int countParent = -1;
    //        for (int i = 0; i < objects.size(); i++) {
    //            if (objects.get(i).getObjType().equals("menu")) {
    //                countParent++;
    //                generateMenuIndexes(objects.get(i), "" + countParent + "");
    //            }
    //        }
    //        return getCasesArray();
    //    }
    //

    // get list and size of list after search

    public void initializeNavigation() {
        try {
            setDataTableSearchResult(getLovBaseBean().getMyTableData());
            setSearchResultSize(getLovBaseBean().getMyTableData().size());
        } catch (DataBaseException e) {
            ;
        }

    }


    // navigateTreeSearchBackWord

    public void navigateTreeSearchBackWord() {
        if (getLovBaseBean().isSearchMode() == true) {
            if (getCurrentSelectedSearchIndex() - 1 > 0) {
                setCurrentSelectedSearchIndex(getCurrentSelectedSearchIndex() - 1);
                ITreeDTO treeDTO = (ITreeDTO)getDataTableSearchResult().get(getCurrentSelectedSearchIndex());
                expandAllForSpecificNode(treeDTO);
            } else {
                setCurrentSelectedSearchIndex(getSearchResultSize() - 1);
                expandAllForSpecificNode((ITreeDTO)getDataTableSearchResult().get(getCurrentSelectedSearchIndex()));
            }
        }

        else {
            try {
                cancelSearchTree();
            } catch (Exception e) {
                ;
            }
        }
    }

    // navigateTreeSearchForward

    public void navigateTreeSearchForward() {
        if (getLovBaseBean().isSearchMode() == true) {
            if (getCurrentSelectedSearchIndex() + 1 < getSearchResultSize()) {
                setCurrentSelectedSearchIndex(getCurrentSelectedSearchIndex() + 1);
                ITreeDTO treeDTO = (ITreeDTO)getDataTableSearchResult().get(getCurrentSelectedSearchIndex());
                expandAllForSpecificNode(treeDTO);
                //  getSelectedDTOS().add((treeDTO)getDto());
                //setSelectedDTOS((List)treeDTO);
            } else {
                setCurrentSelectedSearchIndex(0);
                expandAllForSpecificNode((ITreeDTO)getDataTableSearchResult().get(getCurrentSelectedSearchIndex()));
            }
        } else {
            try {
                cancelSearchTree();
            } catch (Exception e) {
                ;
            }
        }
    }


    //    public String[] getCasesArrayButton() {
    //        objects = tableHeaderBean.getObjects();
    //        if (getCasesArrayButton() == null) {
    //            setCasesArrayButton(new String[10]);
    //            for (int i = 0; i < getCasesArray().length; i++) {
    //                getCasesArrayButton()[i] = "";
    //            }
    //        }
    //
    //        for (int i = 0; i < objects.size(); i++) {
    //            if (objects.get(i).getObjType().equals("button")) { //BUTTONS
    //                getAllIndexesButton() += objects.get(i).getObjCode() + ",";
    //                if (objects.get(i).getSelectionFlag().equals("0")) { //anabled in all cases
    //                    getCasesArrayButton()[0] += objects.get(i).getObjCode() + ",";
    //                    getCasesArrayButton()[1] += objects.get(i).getObjCode() + ",";
    //                    getCasesArrayButton()[2] += objects.get(i).getObjCode() + ",";
    //                    getCasesArrayButton()[3] += objects.get(i).getObjCode() + ",";
    //                } else if (objects.get(i).getSelectionFlag().equals("1")) { //Not Has Child
    //                    getCasesArrayButton()[1] += objects.get(i).getObjCode() + ",";
    //                } else if (objects.get(i).getSelectionFlag().equals("2")) { // Not Root
    //                    getCasesArrayButton()[2] += objects.get(i).getObjCode() + ",";
    //                } else if (objects.get(i).getSelectionFlag().equals("3")) { //Not Leaf
    //                    getCasesArrayButton()[3] += objects.get(i).getObjCode() + ",";
    //                }
    //            }
    //        }
    //        return getCasesArrayButton();
    //    }

    public void setMsgTreeAdd(String msgTreeAdd) {
        this.msgTreeAdd = msgTreeAdd;
    }

    public String getMsgTreeAdd() {
        return msgTreeAdd;
    }

    public void setSerachResult(boolean serachResult) {
        this.serachResult = serachResult;
    }

    public boolean isSerachResult() {
        return serachResult;
    }

    public void setDtoManyToMany(ITreeDTO dtoManyToMany) {
        this.dtoManyToMany = dtoManyToMany;
    }

    public ITreeDTO getDtoManyToMany() {
        return dtoManyToMany;
    }

    public void setEnabledNotHasChlid(boolean enabledNotHasChlid) {
        this.enabledNotHasChlid = enabledNotHasChlid;
    }

    public boolean isEnabledNotHasChlid() {
        return enabledNotHasChlid;
    }

    public void setEnabledRoot(boolean enabledRoot) {
        this.enabledRoot = enabledRoot;
    }

    public boolean isEnabledRoot() {
        return enabledRoot;
    }

    public void setEnabledNotLeaf(boolean enabledNotLeaf) {
        this.enabledNotLeaf = enabledNotLeaf;
    }

    public boolean isEnabledNotLeaf() {
        return enabledNotLeaf;
    }

    public void setSelectionNo(int selectionNo) {
        this.selectionNo = selectionNo;
    }

    public int getSelectionNo() {
        return selectionNo;
    }

    /**
     * Purpose: this methods created to search in tree pages and highlight selected node
     * Creation/Modification History :
     * Developer Name:  A.Agamy
     * Date: 21/9/2014
     * Creation/Modification:Creation
     */
    public void showSearchTreeDiv() {
        String beanName = getSearchTreeDivBean().getBeanName();
        getSearchTreeDivBean().setCodeTypeString(true);
        getSearchTreeDivBean().setReturnMethodName(beanName + ".returnSearchTreeDiv");
        getSearchTreeDivBean().setSearchMethod(beanName + ".searchTreeDiv");
        getSearchTreeDivBean().setCancelSearchMethod(beanName + ".cancelSearchTreeDiv");
        getSearchTreeDivBean().setOnCompleteList(getSearchTreeDivBean().getOnCompleteList() +
                                                 " ; focusHighlightedNode ( ) ; ");
        getSearchTreeDivBean().setHighlightedDTOS(null);
        getSearchTreeDivBean().getSelectedDTOS().clear();
        getSearchTreeDivBean().setSelectedRadio("");
    }

    public void searchTreeDiv(Object searchType, Object searchQuery) {
        try {
            if (searchQuery != null && !searchQuery.equals("")) {
                if (searchType.toString().equals("0")) {
                    getSearchTreeDivBean().setMyTableData(getClient().searchByCode((new Long(searchQuery.toString()))));
                } else if (searchType.toString().equals("1")) {
                    getSearchTreeDivBean().setMyTableData(getClient().searchByName(searchQuery.toString()));
                }
            }
            initializeNavigation();
        } catch (DataBaseException e) {
            e.printStackTrace();
            getSearchTreeDivBean().setMyTableData(new ArrayList(0));
        } catch (Exception e) {
            e.printStackTrace();
            getSearchTreeDivBean().setMyTableData(new ArrayList(0));
        }
    }

    public String cancelSearchTreeDiv() {
        if (getSearchTreeDivBean() != null) {
            getSearchTreeDivBean().getSearchQuery();
            getSearchTreeDivBean().setSelectedRadio("");
            getSearchTreeDivBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
            getSearchTreeDivBean().setMyTableData(new ArrayList<IBasicDTO>());
            try {
                getSearchTreeDivBean().cancelSearch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String returnSearchTreeDiv() {
        getSearchTreeDivBean().setSearchMode(true);
        setSearchResultListSize(getSearchTreeDivBean().getSelectedDTOS().size());
        getHtmlTree().collapsePath(new String[] { "0" });
        expandAllForSpecificNodeList((List)getSearchTreeDivBean().getSelectedDTOS());
        setSearchMode(true);
        return "";
    }

    // end of search tree div methods

    /**
     * Purpose: this method created to highlight the added/edited dto (from wizard page) in tree listing page
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void highlightAddEdit(String backBeanName, String key) {


        try {

            this.getMyTableData();
            setSearchQuery(key);
            ValueBinding binding =
                FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + backBeanName +
                                                                                      ".treeNodeBase}");
            getHtmlTree().setValueBinding("value", binding);
            searchTree();
            setSearchMode(false);

        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void setKeyIndex(int keyIndex) {
        this.keyIndex = keyIndex;
    }

    public int getKeyIndex() {
        return keyIndex;
    }

    public void setTreeNodeNameForCollapseExpand(String treeNodeNameForCollapseExpand) {
        this.treeNodeNameForCollapseExpand = treeNodeNameForCollapseExpand;
    }

    public String getTreeNodeNameForCollapseExpand() {
        return treeNodeNameForCollapseExpand;
    }

    public void setUsingTreePaging(boolean usingTreePaging) {
        this.usingTreePaging = usingTreePaging;
    }

    public boolean isUsingTreePaging() {
        return usingTreePaging;
    }

    public void setTreeSearchPagingRequestDTO(PagingRequestDTO treeSearchPagingRequestDTO) {
        this.treeSearchPagingRequestDTO = treeSearchPagingRequestDTO;
    }

    public PagingRequestDTO getTreeSearchPagingRequestDTO() {
        return treeSearchPagingRequestDTO;
    }

    public void setTreeListPagingRequestDTO(PagingRequestDTO treeListPagingRequestDTO) {
        this.treeListPagingRequestDTO = treeListPagingRequestDTO;
    }

    public PagingRequestDTO getTreeListPagingRequestDTO() {
        return treeListPagingRequestDTO;
    }

    public void setHighLightedDTO(IBasicDTO highLightedDTO) {
        this.highLightedDTO = highLightedDTO;
    }

    public IBasicDTO getHighLightedDTO() {
        return highLightedDTO;
    }

    public void setPagedTreeListSize(Integer pagedTreeListSize) {
        this.pagedTreeListSize = pagedTreeListSize;
    }

    public Integer getPagedTreeListSize() {
        if (pagedTreeListSize == null) {
            return 0;
        }
        return pagedTreeListSize;
    }

    public void SetSelectedNode(String nodeCode) {


        if (nodeCode != null && !nodeCode.equals("")) {
            setHighLightedDTO(null);
            // if there is composte key
            if (getKeyIndex() > -1 && !isUsingTreePaging()) {
                try {
                    nodeCode =
                            getFullEntityKey((List<BesharaKwtWorkDataTree>)getTreeNodeBase().getChildren(), nodeCode);
                    setSelectedNodeId(nodeCode);

                } catch (Exception f) {
                    f.printStackTrace();
                }
            }

            if (nodeCode != null && !nodeCode.equals("0")) {

                SharedUtilBean shared_util = getSharedUtil();
                try {
                    if (isUsingTreePaging()) {
                        setEntityKey(getSelectedEntityKey(nodeCode));
                        setSelectedNodeId(nodeCode);
                    } else {
                        setEntityKey(Helper.getEntityKey((List<IBasicDTO>)getMyTableData(), nodeCode));
                        setSelectedNodeId(nodeCode);
                    }
                    this.setAlreadyDeleted(false);

                    setDtoDetails((ITreeDTO)this.preEdit());

                    if (getDtoDetails() != null) {
                        if (getDtoDetails().isBooleanLeafFlag()) {
                            setEnabledNotLeaf(true);
                        } else {
                            setEnabledNotLeaf(false);
                        }
                        if (getDtoDetails().getChildernNumbers() != 0) {
                            setEnabledNotHasChlid(true);
                        } else {
                            setEnabledNotHasChlid(false);
                        }
                    }

                    if (getDtoDetails() != null && getDtoDetails().getParentCode() == null) {
                        getDtoDetails().setParentCode(getEntityKey());
                    }
                    setRenderEdit(true);
                    setParentName(getDtoDetails().getName());
                    setParentLevel(nodeCode);
                } catch (ItemNotFoundException ex) {
                    System.out.println("treebase bean::idchange::ItemNotFoundException=" + ex);
                    this.setAlreadyDeleted(true);
                } catch (DataBaseException ex) {
                    shared_util.setErrMsgValue(ex.getMessage());
                } catch (Exception ex) {
                    shared_util.setErrMsgValue(ex.getMessage());
                }
            } else {
                setParentName(getBundle().getString(getRootName()));
                setParentLevel(getVirtualLevelCode().toString());
                setEnabledRoot(true);
                setEnabledNotLeaf(false);
                setEnabledNotHasChlid(true);
                //setDtoDetails(null);
                initializeDtoDetails();
                //getDtoDetails().setTreeLevel(virtualLevelCode.toString());

            }
            setSelectionNo(1);
        } else {
            setSelectedNodeId(null);
        }
    }

    public void initializeDtoDetails() {
        getDtoDetails().setCode(null);
        getDtoDetails().setParentObject(null);
        getDtoDetails().setParentCode(null);
        getDtoDetails().setName(getParentName());
    }

    public void setSelectedNodeTreeLevelId(String selectedNodeTreeLevelId) {
        this.selectedNodeTreeLevelId = selectedNodeTreeLevelId;
    }

    public String getSelectedNodeTreeLevelId() {
        return selectedNodeTreeLevelId;
    }

    public void setExpandedNodes(List<BesharaKwtWorkDataTree> expandedNodes) {
        this.expandedNodes = expandedNodes;
    }

    public List<BesharaKwtWorkDataTree> getExpandedNodes() {
        return expandedNodes;
    }

    public String getSelectedStyleClass() {

        BesharaKwtWorkDataTree node = (BesharaKwtWorkDataTree)getHtmlTree().getNode();
        if (node != null) {
            if (node.getDetailId() != null && node.getDetailId().equals(getSelectedNodeId()) ||
                node.getTreeId() != null && node.getTreeId().equals(getSelectedNodeId())) {
                return "HigthLighttreepage-link";
            }
        }

        return selectedStyleClass;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeId() {

        BesharaKwtWorkDataTree node = (BesharaKwtWorkDataTree)getHtmlTree().getNode();
        if (node == null) {
            nodeId = null;
        } else {
            if (node.getDetailId() == null || node.getDetailId().equals("")) {
                nodeId = node.getTreeId();
            } else {
                nodeId = node.getDetailId();
            }
        }
        return nodeId;
    }


    public void setSearchResultListSize(int searchResultListSize) {
        this.searchResultListSize = searchResultListSize;
    }

    public int getSearchResultListSize() {
        return searchResultListSize;
    }

}
