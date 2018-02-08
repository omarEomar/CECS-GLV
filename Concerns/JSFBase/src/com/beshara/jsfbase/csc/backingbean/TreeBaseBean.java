package com.beshara.jsfbase.csc.backingbean;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.dto.TreeDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.querybuilder.ArabicAlphabetConstants;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;
import com.beshara.jsfbase.csc.util.Helper;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.tableheader.ModuleObject;
import com.beshara.jsfbase.csc.util.tableheader.TableHeaderBean;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.tree2.HtmlTree;


/**
 *this bean responsible for parsing the list to build the tree Structure
 *use this bean when implement tree page
 * must be set rootName Attribute into Constructor
 */
public class TreeBaseBean extends BaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;



    HttpSession session = 
        (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    protected boolean hasChild = false;
    protected String parentLevel;
    private String parentName;
    private String levelName = "";
    private boolean booleanLeafFlag = true;
    private boolean renderEdit = false;
    private boolean success = false;
    protected String rootName;
    private boolean founded = false;
    private HtmlTree htmlTree = 
        new HtmlTree(); /* hold the binding value of tree2 in jsf */
    private Long virtualLevelCode = ISystemConstant.VIRTUAL_VALUE;
    protected List treeList = 
        new ArrayList(); /* list of available categories in the System */
    private ResourceBundle bundle = null;
    private ResourceBundle globalResource = null;
    protected BesharaTree treeNodeBase;
    private String selectedNodeId;
    private ITreeDTO dto = new TreeDTO();
    private ITreeDTO dtoDetails = new TreeDTO();
    private String msgTreeAdd;
    private boolean serachResult = false;

    private boolean enabledNotHasChlid = true;
    private boolean enabledRoot = false;
    private boolean enabledNotLeaf = true;

    private int selectionNo;

    protected int keyIndex = -1;

    // added by AMR ABDO
    private String treeNodeNameForCollapseExpand = "0";

    //Paging in tree, by MLotfy
    private boolean usingTreePaging;
    private PagingRequestDTO treeSearchPagingRequestDTO;
    private PagingRequestDTO treeListPagingRequestDTO;

    private int searchResultListSize;

    public TreeBaseBean() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        tableHeaderBean = 
                (TableHeaderBean)app.createValueBinding("#{TableHeaderBean}").getValue(fc);
    }


    /**
     * this method responsible for getting the root element[s] of the tree
     * @return BesharaTree treeNodeBase
     * @throws DataBaseException 
     * @Updated-by MLotfy, used in Paged Tree Div
     */
    public BesharaTree buildTree() throws DataBaseException {

        try {
            getMyTableData();
            if (isUsingTreePaging()) {
                treeList = getMyTableData();
            } else {
                treeList = Helper.buildTree(getMyTableData());
            }

            String treeLevel = "0";
            treeNodeBase = 
                    new BesharaTree("foo-folder", getBundle().getString(rootName), 
                                    "0", null, true, false, false, treeLevel);

            for (int i = 0; i < treeList.size(); i++) {
                treeLevel = "0";
                ITreeDTO item = (ITreeDTO)treeList.get(i);
                if (item.getChildernNumbers() != 0) {
                    hasChild = true;
                } else {
                    hasChild = false;
                }
                BesharaTree treeNode = null;

                if (item.getChildernNumbers() > 0) {
                    treeLevel = "0:" + i;
                }

                if (keyIndex == -1)
                    treeNode = 
                            new BesharaTree("foo-folder", item.getName(), (String)item.getCode().getKey(), 
                                            null, hasChild, 
                                            item.isBooleanLeafFlag(), 
                                            item.isBooleanLeafFlag(), 
                                            treeLevel);
                else if (keyIndex > -1) {
                    treeNode = 
                            new BesharaTree("foo-folder", item.getName(), item.getCode().getKeys()[keyIndex].toString(), 
                                            item.getCode().getKey().toString(), 
                                            null, hasChild, 
                                            item.isBooleanLeafFlag(), 
                                            item.isBooleanLeafFlag(), 
                                            treeLevel);
                }

                if (treeNode != null) {
                    treeNode.setParent(treeNodeBase);
                    treeNodeBase.getChildren().add(treeNode);
                    parseTree(item.getChildrenList(), item, treeNode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            treeNodeBase = 
                    new BesharaTree("foo-folder", getBundle().getString(rootName), 
                                    "0", null, true, false, false);
        }

        return treeNodeBase;
    }

    public HtmlForm getFrm() {

        if (getUsingPortal()) {
            if (this.appMainLayoutPortalBuilder() != null) {
                setCurrentApplictionMainLayoutPortal(this.appMainLayoutPortalBuilder());
            }
        } else {
            if (this.appMainLayoutBuilder() != null) {
                setCurrentApplictionMainLayout(this.appMainLayoutBuilder());
            }
        }
        this.initiateBeanOnce();
        return frm;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showTreePage();
        return app;
    }

    public AppMainLayoutPortal appMainLayoutPortalBuilder() {
        AppMainLayoutPortal app = new AppMainLayoutPortal();
        app.showTreePage();
        return app;
    }

    public void setFrm(HtmlForm frm) {

        this.frm = frm;
    }


    /**
        this method responsible for parsing the list to build the tree Structure
     * @param treeList holds all the categories returned from the system
     * @param treeDTO  object from ITreeDTO which holds the current node
     * @param treeNodeBase object from BesharaTree which holds the parent of the node to be drawed
     * @throws DataBaseException ,RemoteException 
     **/
    public BesharaTree parseTree(List treeList, ITreeDTO treeDTO, 
                                 BesharaTree treeNodeBase) throws DataBaseException, 
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
                BesharaTree treeNode = null;
                if (keyIndex == -1)
                    treeNode = 
                            new BesharaTree("foo-folder", treeDTOElem.getName(), 
                                            (String)treeDTOElem.getCode().getKey(), 
                                            (String)treeDTOElem.getParentCode().getKey(), 
                                            hasChild, 
                                            treeDTOElem.isBooleanLeafFlag(), 
                                            treeDTOElem.isBooleanLeafFlag(), 
                                            tempTreeLevel);

                else if (keyIndex > -1)
                    treeNode = 
                            new BesharaTree("foo-folder", treeDTOElem.getName(), 
                                            treeDTOElem.getCode().getKeys()[keyIndex].toString(), 
                                            treeDTOElem.getCode().getKey().toString(), 
                                            (String)treeDTOElem.getParentCode().getKey(), 
                                            hasChild, 
                                            treeDTOElem.isBooleanLeafFlag(), 
                                            treeDTOElem.isBooleanLeafFlag(), 
                                            tempTreeLevel);

                if (treeNode != null) {
                    treeNode.setParent(treeNodeBase);
                    treeNodeBase.getChildren().add(treeNode);
                    if (treeDTOElem.getChildernNumbers() != 0) {
                        parseTree(treeDTOElem.getChildrenList(), treeDTOElem, 
                                  treeNode);
                    }
                }

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


    /**
        this method responsible for Listener to change select node
     **/

    /**
     * Purpose:
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date: 22-1-2009
     * 1.3 - Creation/Modification:Modification      
     * 1.4-  Description: 
     * to enable  display one item from composte key uaing specific index
     */
    public void idChange(ValueChangeEvent e) {
        if (isUsingTreePaging()) {
            setDtoDetails(new TreeDTO());
        } else {
            initializeDtoDetails();
        }
        success = false;
        this.setSelectionNo(0);
        String value = (String)e.getNewValue();
        if (value != null && !value.equals("")) {
            String nodeCode = (String)e.getNewValue();

            // if there is composte key
            if (keyIndex > -1 && !usingTreePaging) {
                try {
                    nodeCode = 
                            getFullEntityKey(getTreeNodeBase().getChildren(), 
                                             nodeCode);

                } catch (Exception f) {
                    f.printStackTrace();
                }
            }

            if (nodeCode != null && !nodeCode.equals("0")) {

                SharedUtilBean shared_util = getSharedUtil();
                try {
                    if (usingTreePaging) {
                        setEntityKey(getSelectedEntityKey(nodeCode));
                    } else {
                        setEntityKey(Helper.getEntityKey((List<IBasicDTO>)getMyTableData(), 
                                                         nodeCode));
                    }

                    this.setAlreadyDeleted(false);

                    if (getDtoDetails() == null || 
                        getDtoDetails().getCode() == null || 
                        (!value.equals(getDtoDetails().getCode().getKey().toString()))) {

                        setDtoDetails((ITreeDTO)preEdit());
                    }

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

                    if (getDtoDetails() != null && 
                        getDtoDetails().getParentCode() == null) {
                        getDtoDetails().setParentCode(getEntityKey());
                    }
                    renderEdit = true;
                    setParentName(getDtoDetails().getName());
                    
                    setParentLevel(nodeCode);
                } catch (ItemNotFoundException ex) {
                    System.out.println("treebase bean::idchange::ItemNotFoundException=" + 
                                       ex);
                    this.setAlreadyDeleted(true);
                } catch (DataBaseException ex) {
                    shared_util.setErrMsgValue(ex.getMessage());
                } catch (Exception ex) {
                    shared_util.setErrMsgValue(ex.getMessage());
                }
            } else {
                setParentName(getBundle().getString(rootName));
                setParentLevel(virtualLevelCode.toString());
                setEnabledRoot(true);
                setEnabledNotLeaf(false);
                setEnabledNotHasChlid(true);
            }
            this.setSelectionNo(1);
        }
    }

    public void initializeDtoDetails() {
        getDtoDetails().setCode(null);
        getDtoDetails().setParentObject(null);
        getDtoDetails().setParentCode(null);
        //getDtoDetails().setName(getParentName());
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
    public String getFullEntityKey(List<BesharaTree> list, 
                                   String displayedKey) {
        for (BesharaTree child: list) {
            if (child.treeId.equals(displayedKey))
                return child.getDetailId();

            if (child.getChildCount() != 0) {
                String ret = 
                    getFullEntityKey(child.getChildren(), displayedKey);
                if (ret != null && !ret.equals(""))
                    return ret;
            }
        }
        return null;
    }


    public void preAddTree() {
        setSuccess(false);
    }

    public void preEditTree() {

        if (getSelectedNodeId() != null && !getSelectedNodeId().equals("0")) {
            // to get composte key 
            if (keyIndex > -1) {
                try {
                    selectedNodeId = 
                            getFullEntityKey(getTreeNodeBase().getChildren(), 
                                             selectedNodeId);
                } catch (Exception f) {
                    f.printStackTrace();
                }
            }
        }

        if (getSelectedNodeId() != null && !getSelectedNodeId().equals("0")) {

            try {
                setEntityKey(Helper.getEntityKey(getMyTableData(), 
                                                 getSelectedNodeId()));
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
            parent.setEngName(getDtoDetails().getEngName());
            getDto().setParentObject(parent);
        }
    }


    public void searchTree() throws DataBaseException, RemoteException, 
                                    Exception {
        searchResultListSize = 0;
        List<BesharaTree> list = getTreeNodeBase().getChildren();
        highlightSearchedResult(list, this.getSearchQuery(), 
                                "person-highlight");
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
     * Purpose:to make   search by Code Or Name  with Specific Character 
     * Creation/Modification History :
     * 1.1 - Developer Name: Amr GAlal
     * 1.2 - Date: 24-10-2010
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void searchTreeFromSpecificChar() throws DataBaseException, RemoteException, 
                                    Exception {
        searchResultListSize = 0;
		buildTree();
        List<BesharaTree> list = getTreeNodeBase().getChildren();
        Pattern pattern=null;
        if (this.getSearchType().intValue() == 1) {
         pattern = getSimilerCharRegexPattern(this.getSearchQuery());
        }
        else
        {
            pattern=null;
        }
        highlightSearchedResultFromSpecificChar(list, this.getSearchQuery(),"person-highlight",pattern);
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
     * Purpose:to make   search by Code Or Name  with Specific Character and highlightSearchedResult
     * Creation/Modification History :
     * 1.1 - Developer Name: Amr GAlal
     * 1.2 - Date: 24-10-2010
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
     public void highlightSearchedResultFromSpecificChar(List<BesharaTree> list, 
                                            String searchedString, String facet ,Pattern pattern ) {
            
                if (this.getSearchType().intValue() == 0) {
                    searchByCode(list, searchedString , facet);
                } 
                else {
                      searchByName( list, searchedString , facet, pattern);
                  } 
            }
       
        
       public void searchByCode(List<BesharaTree> list,String searchedString ,String facet)
        {
                for (BesharaTree child: list) {
                if (searchedString != null && !searchedString.equals("") && child.treeId.equals(searchedString)) {
                    child.setType(facet);
                    this.setFounded(true);
                    setSelectedListSize(1);
                    searchResultListSize += 1;
                } else {
                    child.setType("foo-folder");
                }
                if (child.getChildCount() != 0) {
                      Pattern pattern=null;
                      highlightSearchedResultFromSpecificChar(child.getChildren(), searchedString,facet,pattern);
                      }
            }
            
        }
        
    public void searchByName(List<BesharaTree> list,String searchedString ,String facet,Pattern pattern)
     {
        for (BesharaTree child: list) {
        String childIdentifier =child.getIdentifier();
        Matcher fit = pattern.matcher(childIdentifier);
        if (fit.matches()) {
             child.setType(facet);
             this.setFounded(true);
             searchResultListSize += 1;
             setSelectedListSize(1);
             }
             if (child.getChildCount() != 0) {
                   highlightSearchedResultFromSpecificChar(child.getChildren(), searchedString,facet,pattern);
                   }
         }
        
     }
    
   public  Pattern  getSimilerCharRegexPattern(String searchedString)
    {
        String[] tokens=searchedString.split("[\\s%]+");
        String pattern = ".*";
        String temp=null;
        for(String token : tokens){
         if(!token.equals(""))
          {
                    temp=new String (token) ;
                    String checkStartWith=temp.substring(0,1);
                    String checkEndWith=temp.substring(temp.length()-1,temp.length());
                    if((checkStartWith.equals(ArabicAlphabetConstants.AlephWithOutHamza+"")||checkStartWith.equals(ArabicAlphabetConstants.AlephWithUpperHamza+"")|| checkStartWith.equals(ArabicAlphabetConstants.AlephWithLowerHamza+"") || checkStartWith.equals(ArabicAlphabetConstants.AlephWithMadda+""))  && temp.length()>=1){
                    String aleph ="["+ArabicAlphabetConstants.AlephWithOutHamza+""+ArabicAlphabetConstants.AlephWithUpperHamza+""+ArabicAlphabetConstants.AlephWithLowerHamza+""+ArabicAlphabetConstants.AlephWithMadda+""+"]" ;
                    temp=temp.substring(1); 
                    temp = aleph+temp;
                    }
                    if((checkEndWith.equals(ArabicAlphabetConstants.Haa+"") || checkEndWith.equals(ArabicAlphabetConstants.TaaMarbota+"")) && temp.length()>=1){
                        String aleph ="["+ArabicAlphabetConstants.Haa+""+ArabicAlphabetConstants.TaaMarbota+"" +"]";
                        temp=temp.substring(0,temp.length()-1);
                        temp = temp+aleph;
                    }
                    if((checkEndWith.equals(ArabicAlphabetConstants.Yaa+"") || checkEndWith.equals(ArabicAlphabetConstants.AlephMaksora+"")) && temp.length()>=1){
                        String aleph ="["+ArabicAlphabetConstants.Yaa+""+ArabicAlphabetConstants.AlephMaksora+"" +"]";
                        temp=temp.substring(0,temp.length()-1);
                        temp = temp+aleph;
                    }
                    pattern = pattern + temp +".*";
                
                if(searchedString.startsWith("%") && !searchedString.endsWith("%"))
                {
                    pattern = pattern.substring(0,pattern.length()-2);
                }
                else{
                    if(!searchedString.startsWith("%") && searchedString.endsWith("%"))
                    {
                       pattern = pattern.substring(2);
                    } 
                }
          }
        }
        
    
        Pattern searchStringPatten = Pattern.compile(pattern);
        return searchStringPatten;
    }
        
    public void highlightSearchedResult(List<BesharaTree> list, 
                                        String searchedString, String facet) {
        for (BesharaTree child: list) {
            if (this.getSearchType().intValue() == 0) {
                if (searchedString != null && !searchedString.equals("") && 
                    child.treeId.equals(searchedString)) {
                    child.setType(facet);
                    this.setFounded(true);
                    setSelectedListSize(1);
                    searchResultListSize += 1;
                } else {
                    child.setType("foo-folder");
                }
            } else {
                String childIdentifier = 
                    child.getIdentifier().trim().toLowerCase();
                String searchBy = searchedString.trim().toLowerCase();
                if (Helper.searchInTree(searchBy, childIdentifier, false)) {
                    child.setType(facet);
                    this.setFounded(true);
                    searchResultListSize += 1;
                    setSelectedListSize(1);
                } else {
                    child.setType("foo-folder");
                }
            }
            if (child.getChildCount() != 0) {
                highlightSearchedResult(child.getChildren(), searchedString, 
                                        facet);
            }
        }


    }


    public Integer getListSize() throws DataBaseException {
        // COMMENTED BY AMR ABDO ( Paging AT Tree )
        if (isSearchMode()) {
            return searchResultListSize;
        } else
            return super.getListSize();
    }

    /**
     *     highlightAdd/Edit Result() for node name searching  purpose
     * */
    public void highlightAddEditResult(List<BesharaTree> list, 
                                       String searchedString, String facet) {
        for (BesharaTree child: list) {
            Long nodeCode = Long.parseLong(child.treeId);
            Long searchedLong = Long.parseLong(searchedString);
            if (searchedLong.equals(nodeCode)) {
                child.setType(facet);
            } else {
                child.setType("foo-folder");
            }
            if (child.getChildCount() != 0) {
                highlightSearchedResult(child.getChildren(), searchedString, 
                                        facet);
            }
        }
    }

    public void addNew() throws DataBaseException, Exception {
        saveItem();
        //setDtoDetails(new TreeDTO());
		initializeDtoDetails();
        if (getPageDTO().getCode() != null) {
            List<BesharaTree> list = buildTree().getChildren();
            highlightAddEditResult(list, 
                                   (String)getPageDTO().getCode().getKey(), 
                                   "person-highlight");
        }
    }

    public void addAndNew() throws DataBaseException, Exception {
        success = false;
        saveItem();

        if (getErrorMsg() == null || 
            (getErrorMsg() != null && getErrorMsg().equals(""))) {
            setMsgTreeAdd("SuccessAdds");
            success = true;
        }

    }

    public void saveItem() throws DataBaseException, Exception {
        ITreeDTO treeDTO = new TreeDTO();
        if (!parentLevel.equals("-100")) {
            setEntityKey(Helper.getEntityKey(getMyTableData(), parentLevel));
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

    public void edit() throws DataBaseException, ItemNotFoundException, 
                              SharedApplicationException {

        if (dto.getParentObject() != null && 
            dto.getParentObject().getCode() == null) {
            dto.setParentObject(null);
        }
        setPageDTO((IBasicDTO)dto);
        super.edit();
        //setDtoDetails(new TreeDTO());
		initializeDtoDetails();
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
            resultDTO.setStatus(e.getMessage());
        } catch (DataBaseException e) {
            resultDTO.setDatabaseException(new DataBaseException(e.getMessage()));
            resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
        }
        getDeleteStatusDTOS().add(resultDTO);
        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");
        setSelectionNo(0);
    }

    public void cancelSearchTree() throws DataBaseException, RemoteException, 
                                          Exception {
        if (isUsingTreePaging()) {
            this.setSearchQuery("");
            this.setSearchType(0);
            this.setSearchMode(false);
        } else {
            super.cancelSearch();
            setSelectionNo(0);
            buildTree();
            getHtmlTree().collapseAll();
        }
    }

    public void cancelAddTree() throws DataBaseException, Exception {
        setLevelName("");
        setBooleanLeafFlag(true);
        getSharedUtil().setErrMsgValue(null);
        getSharedUtil().setSuccessMsgValue(null);
        setSelectionNo(0);
        if(!isSearchMode())
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

    public void setTreeNodeBase(BesharaTree treeNodeBase) {
        this.treeNodeBase = treeNodeBase;
    }

    public BesharaTree getTreeNodeBase() throws DataBaseException, 
                                                RemoteException, Exception {
        if (treeNodeBase == null) {
            buildTree();
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
//        System.out.println("selected Node Id=====" + selectedNodeId);
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

    public void generateMenuIndexes(ModuleObject currentObj, 
                                    String currentMenuItemIndex) {
        String ret = "";
        ModuleObject obj = null;

        List<ModuleObject> list = currentObj.getChildsList();
        if (list.size() == 0) {
            if (currentObj.getObjPage() != null) {
                allIndexes += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
            }
            if (currentObj.getSelectionFlag().equals("0") && 
                currentObj.getObjPage() != null) { //Enabled in all cases
                casesArray[0] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
                casesArray[1] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
                casesArray[2] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
                casesArray[3] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
            }
            if (currentObj.getSelectionFlag().equals("1") && 
                currentObj.getObjPage() != null) { //Not Has Child
                casesArray[1] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
            }
            if (currentObj.getSelectionFlag().equals("2") && 
                currentObj.getObjPage() != null) { //Not Root
                casesArray[2] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
            }
            if (currentObj.getSelectionFlag().equals("3") && 
                currentObj.getObjPage() != null) { //Not Leaf
                casesArray[3] += 
                        "" + currentMenuItemIndex + "-2-" + currentObj.getObjPage() + 
                        ",";
            }

        }


        for (int i = 0; i < list.size(); i++) {
            obj = list.get(i);
            int index = i + 5;
            generateMenuIndexes(obj, currentMenuItemIndex + "-" + index);
        }
    }

    public String[] getCasesArray() {
        objects = tableHeaderBean.getObjects();
        if (casesArray == null) {
            casesArray = new String[10];
            for (int i = 0; i < casesArray.length; i++) {
                casesArray[i] = "";
            }

        }
        int countParent = -1;
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getObjType().equals("menu")) {
                countParent++;
                generateMenuIndexes(objects.get(i), "" + countParent + "");
            }
        }
        return casesArray;
    }

    public String[] getCasesArrayButton() {
        objects = tableHeaderBean.getObjects();
        if (casesArrayButton == null) {
            casesArrayButton = new String[10];
            for (int i = 0; i < casesArray.length; i++) {
                casesArrayButton[i] = "";
            }
        }

        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getObjType().equals("button")) { //BUTTONS
                allIndexesButton += objects.get(i).getObjCode() + ",";
                if (objects.get(i).getSelectionFlag().equals("0")) { //anabled in all cases
                    casesArrayButton[0] += objects.get(i).getObjCode() + ",";
                    casesArrayButton[1] += objects.get(i).getObjCode() + ",";
                    casesArrayButton[2] += objects.get(i).getObjCode() + ",";
                    casesArrayButton[3] += objects.get(i).getObjCode() + ",";
                } else if (objects.get(i).getSelectionFlag().equals("1")) { //Not Has Child
                    casesArrayButton[1] += objects.get(i).getObjCode() + ",";
                } else if (objects.get(i).getSelectionFlag().equals("2")) { // Not Root
                    casesArrayButton[2] += objects.get(i).getObjCode() + ",";
                } else if (objects.get(i).getSelectionFlag().equals("3")) { //Not Leaf
                    casesArrayButton[3] += objects.get(i).getObjCode() + ",";
                }
            }
        }
        return casesArrayButton;
    }

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
//        System.out.println("enabledNotLeaf=====" + enabledNotLeaf);
        return enabledNotLeaf;
    }

    public void setSelectionNo(int selectionNo) {
        this.selectionNo = selectionNo;
    }

    public int getSelectionNo() {
        return selectionNo;
    }


    /**
     * Purpose: this method created to highlight the added/edited dto (from separate add edit page) in tree listing page
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
                FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                      backBeanName + 
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


    /**
     * Purpose: this method created to highlight the added/edited dto (from separate add edit page) in tree listing page
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date: 
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void highlightTreeNode(String nodeKey) {


        try {

            setSearchQuery(nodeKey);
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


    //this method override to use getAllInCenter() method

    public List getTotalList() {

        List totalList = null;

        try {

            IBasicDTO filterDTO = getFilterDTO();

            if (filterDTO == null) {
                totalList = getClient().getAllInCenter();
            } else {
                totalList = getClient().getAllInCenter(filterDTO);
            }

        } catch (SharedApplicationException ne) {
            totalList = new ArrayList();
            ne.printStackTrace();
        } catch (DataBaseException db) {
            getSharedUtil().handleException(db);
        } catch (Exception e) {
            getSharedUtil().handleException(e);
        }

        return totalList;

    }

    //this method override to use searchByCodeInCenter(Object code) and searchByNameInCenter(String name) methods

    public List getBaseSearchResult() throws DataBaseException {

        List searchResult = new ArrayList();

        try {
            if (getSearchType().intValue() == 0) {
                searchResult = 
                        getClient().searchByCodeInCenter(getSearchEntityObj());
            } else if (getSearchType().intValue() == 1) {
                searchResult = 
                        getClient().searchByNameInCenter(formatSearchString(getSearchQuery()));
            }
        } catch (ItemNotFoundException e) { //this means no search results found
            setMyTableData(new ArrayList(0));
        } catch (NoResultException ne) { //this means no search results found
            setMyTableData(new ArrayList());
        } catch (Exception db) {

            ErrorDisplay errorDisplay = 
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_display}").getValue(FacesContext.getCurrentInstance());

            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);
            throw new DataBaseException(db.getMessage());

        }

        return searchResult;
    }

    //this method override to use getByIdInCenter(IEntityKey id) method

    public IBasicDTO preEdit() {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            return getClient().getByIdInCenter(getEntityKey());
        } catch (ItemNotFoundException e) {
            shared_util.setErrMsgValue("itemALreadyDeleted");
        } catch (DataBaseException e) {
            //shared_util.setErrMsgValue("SystemFailureException");
            getSharedUtil().handleException(e);
        } catch (SharedApplicationException e) {
            //shared_util.setErrMsgValue(e.getMessage());
            getSharedUtil().handleException(e);
        }
        return null;
    }

    //this method override to use getByIdInCenter(IEntityKey id) method

    public void showEditDiv() {
        setPageMode(2);
        reIntializeMsgs();
        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {
            //            selectedPageDTO=this.getSelectedDTOS().get(0);
            IBasicDTO dto = this.getSelectedDTOS().get(0);
            try {
                setSelectedPageDTO(getClient().getByIdInCenter(dto.getCode()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            setShowEdit(true);

            //javaScriptCaller = "changeVisibilityDiv(window.blocker,window.lookupEditDiv);";


        } else {
            setSelectedPageDTO(new BasicDTO());
            setShowEdit(false);

        }


    }

    /**
     * @Used-in Paged Tree Div
     * @Auther MLotfy
     */
    public void expand(ActionEvent event) {

        if (isUsingTreePaging()) {
            setDtoDetails(new TreeDTO());
//            System.out.println("Entering expand()");
            UIComponent component = (UIComponent)event.getSource();

            while (!(component != null && component instanceof HtmlTree)) {
                component = component.getParent();
            }

            if (component != null) {

                HtmlTree tree = (HtmlTree)component;
                BesharaTree node = (BesharaTree)tree.getNode();

                // check that the node has been expanded before
                if (!node.isHasChild()) {

                    PagingRequestDTO pagingRequestDTO = 
                        getTreeListPagingRequestDTO();

                    if (pagingRequestDTO != null) {

                        List tempTreeList = null;
                        try {
                            tempTreeList = getMyTableData();
                        } catch (DataBaseException e) {
                            e.printStackTrace();
                        }

                        pagingRequestDTO.setParams(new Object[] { node });

                        String methodBindingExpression = 
                            pagingRequestDTO.getBeanName() + "." + 
                            pagingRequestDTO.getMethodName();

                        PagingResponseDTO responseDTO = 
                            (PagingResponseDTO)executeMethodBindingWithParams(methodBindingExpression, 
                                                                              new Object[] { pagingRequestDTO }, 
                                                                              new Class[] { PagingRequestDTO.class });

                        List children = responseDTO.getResultList();
                        if (children != null && children.size() > 0) {
                            buildChildrenTree(node, children);
                        } else {
                            setMyTableData(tempTreeList);
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
     * @Used-in Paged Tree Div
     * @Auther MLotfy
     */
    private void buildChildrenTree(BesharaTree parentNode, List childrenList) {

        try {
            for (ITreeDTO item: (List<ITreeDTO>)childrenList) {
                if (item.getChildernNumbers() != 0) {
                    hasChild = true;
                } else {
                    hasChild = false;
                }
                BesharaTree treeNode = null;
                if (keyIndex == -1)
                    treeNode = 
                            new BesharaTree("foo-folder", item.getName(), (String)item.getCode().getKey(), 
                                            null, hasChild, 
                                            item.isBooleanLeafFlag(), 
                                            item.isBooleanLeafFlag());
                else if (keyIndex > -1) {
                    treeNode = 
                            new BesharaTree("foo-folder", item.getName(), item.getCode().getKeys()[keyIndex].toString(), 
                                            item.getCode().getKey().toString(), 
                                            null, hasChild, 
                                            item.isBooleanLeafFlag(), 
                                            item.isBooleanLeafFlag());
                }

                if (treeNode != null) {
                    parentNode.setLeaf(false);
                    treeNode.setParent(parentNode);
                    parentNode.getChildren().add(treeNode);
                    parseTree(item.getChildrenList(), item, treeNode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public int getSearchResultListSize() {
        return searchResultListSize;
    }

    public void setSearchResultListSize(int searchResultListSize) {
        this.searchResultListSize = searchResultListSize;
    }
}
