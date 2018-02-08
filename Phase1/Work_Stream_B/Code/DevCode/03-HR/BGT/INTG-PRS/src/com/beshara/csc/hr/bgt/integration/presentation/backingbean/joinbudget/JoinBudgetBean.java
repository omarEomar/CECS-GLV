package com.beshara.csc.hr.bgt.integration.presentation.backingbean.joinbudget;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.dto.TreeDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.client.IBgtAccountsClient;
import com.beshara.csc.hr.bgt.business.client.IBgtAcctElmguidesClient;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtAccountsDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtAcctElmguidesDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtAcctElmguidesEntityKey;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BesharaTree;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;
import com.beshara.jsfbase.csc.util.Helper;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;


public class JoinBudgetBean extends LookUpBaseBean {

    public static final String BEAN_NAME = "joinBudgetBean";
    public static final String BUNDULE_NAME = "com.beshara.csc.hr.bgt.integration.presentation.resources.integration";
    public static final String PAGE_NAVIGATION_CASE = "joinBudgetNav";
    private Long selectedElementCode;
    private String selectedElementName;
    private List caders;
    private Long selectedCader;
    private List groups;
    private Long selectedGroup;
    private List degrees;
    private Long selectedDegree;
    private TreeDivBean treeDivBean;
    private IBasicDTO selectedBudgetDTO;
    private boolean defaultFlag;
    private String backNavCase;
    private String backMethod;
    private Object savedDataObj;

    public JoinBudgetBean() {
        setPageDTO(BgtDTOFactory.createBgtAcctElmguidesDTO());
        setClient(BgtClientFactory.getBgtAcctElmguidesClient());
    }

    public static JoinBudgetBean getInstance() {
        return (JoinBudgetBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowTreediv(true);
        app.setShowSearch(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowCommonData(false);
        app.setShowshortCut(false);
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        return app;
    }

    public void initiateBeanOnce() {
        if (caders == null) {
            try {
                caders =
                        SalClientFactory.getSalElementGuidesClient().getCaderCodeNameByElmMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        selectedBudgetDTO = BgtDTOFactory.createBgtAccountsDTO();
        initTreeDivBean();
        setPageMode(0);
    }

    public void initPage(Long selectedElementCode, String selectedElementName, String backNavCase, String backMethod) {
        this.selectedElementCode = selectedElementCode;
        this.selectedElementName = selectedElementName;
        this.backNavCase = backNavCase;
        this.backMethod = backMethod;
    }

    public List getTotalList() {
        List totalList = null;
        try {
            totalList = ((IBgtAcctElmguidesClient)getClient()).getByElementCode(selectedElementCode);
        } catch (Exception e) {
            totalList = new ArrayList();
        }
        return totalList;

    }

    /*****************************************************************************************************************/
    public void initTreeDivBean() {
        getTreeDivBean().setBundleName(BUNDULE_NAME + "_");
        getTreeDivBean().setRootName("budgetItems");
        getTreeDivBean().setClient(BgtClientFactory.getBgtAccountsClient());
        getTreeDivBean().setPageDTO(BgtDTOFactory.createBgtAccountsDTO());
        getTreeDivBean().setDto(BgtDTOFactory.createBgtAccountsDTO());
        getTreeDivBean().setDtoDetails(BgtDTOFactory.createBgtAccountsDTO());
        getTreeDivBean().setUsingTreePaging(false);
        getTreeDivBean().setSelectedEntityKeyMethod(BEAN_NAME + ".getSelectedEntityKey");
        getTreeDivBean().setGoActionOkMethod(BEAN_NAME + ".getSelectedBudget");
        getTreeDivBean().getOkCommandButton().setReRender("selectedBudget");
        getTreeDivBean().setSelectionNo(0);
        getTreeDivBean().setSearchTreeMethod(BEAN_NAME + ".searchTree");
        getTreeDivBean().setCancelSearchMethod(BEAN_NAME + ".cancelSearchTreeDiv");
        getTreeDivBean().setIdChangeMethod(BEAN_NAME + ".idChange");
        getTreeDivBean().setDivName("divTree");
        setTreeDivTitleKey("join_budget_treedivtitle");
    }

    public void openTreeDiv() {
        initTreeDivBean();
       // try {
            getTreeDivBean().setDto(BgtDTOFactory.createBgtAccountsDTO());
            getTreeDivBean().setDtoDetails(BgtDTOFactory.createBgtAccountsDTO());

         //   List resultList = new ArrayList();

          //  resultList = BgtClientFactory.getBgtAccountsClient().getByStatus_CustomName(ISystemConstant.ACTIVE_FLAG);
          //  getTreeDivBean().setMyTableData(resultList);
       // } catch (DataBaseException e) {
       //     e.printStackTrace();
       //     getTreeDivBean().setMyTableData(new ArrayList());
     //   } catch (Exception e) {
         //   e.printStackTrace();
         //   getTreeDivBean().setMyTableData(new ArrayList());
      //  }
        getTreeDivBean().setTreeNodeBase(null);
        getTreeDivBean().setShowTreeContent(true);
        getTreeDivBean().setSearchMode(true);
        try {
            getTreeDivBean().cancelSearchTree();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IEntityKey getSelectedEntityKey(String nodeId) {
        return BgtEntityKeyFactory.createBgtAccountsEntityKey(Long.parseLong(nodeId));
    }

    public void idChange(ValueChangeEvent e) {

        if (getTreeDivBean().isUsingTreePaging()) {
            getTreeDivBean().setDtoDetails(new TreeDTO());
        } else {
            getTreeDivBean().initializeDtoDetails();
        }
        getTreeDivBean().setSuccess(false);
        getTreeDivBean().setSelectionNo(0);
        String value = (String)e.getNewValue();

        if (value != null && !value.equals("")) {
            String nodeCode = (String)e.getNewValue();

            // if there is composte key
            if (getTreeDivBean().getKeyIndex() > -1 && !getTreeDivBean().isUsingTreePaging()) {
                try {
                    nodeCode =
                            getTreeDivBean().getFullEntityKey(getTreeDivBean().getTreeNodeBase().getChildren(), nodeCode);

                } catch (Exception f) {
                    f.printStackTrace();
                }
            }

            if (nodeCode != null && !nodeCode.equals("0")) {

                SharedUtilBean shared_util = getSharedUtil();
                try {
                    getTreeDivBean().setEntityKey(BgtEntityKeyFactory.createBgtAccountsEntityKey(Long.parseLong(nodeCode)));
                    this.setAlreadyDeleted(false);

                    if (getTreeDivBean().getDtoDetails() == null ||
                        getTreeDivBean().getDtoDetails().getCode() == null ||
                        (!value.equals(getTreeDivBean().getDtoDetails().getCode().getKey().toString()))) {

                        getTreeDivBean().setDtoDetails((ITreeDTO)BgtClientFactory.getBgtAccountsClient().getById(getTreeDivBean().getEntityKey()));
                    }

                    if (getTreeDivBean().getDtoDetails() != null) {
                        if (getTreeDivBean().getDtoDetails().isBooleanLeafFlag()) {
                            getTreeDivBean().setEnabledNotLeaf(true);
                        } else {
                            getTreeDivBean().setEnabledNotLeaf(false);
                        }
                        if (getTreeDivBean().getDtoDetails().getChildernNumbers() != 0) {
                            getTreeDivBean().setEnabledNotHasChlid(true);
                        } else {
                            getTreeDivBean().setEnabledNotHasChlid(false);
                        }
                    }

                    if (getTreeDivBean().getDtoDetails() != null &&
                        getTreeDivBean().getDtoDetails().getParentCode() == null) {
                        getTreeDivBean().getDtoDetails().setParentCode(getEntityKey());
                    }

                    getTreeDivBean().setParentName(getTreeDivBean().getDtoDetails().getName());

                    getTreeDivBean().setParentLevel(nodeCode);
                    getTreeDivBean().setRenderEdit(true);
                    //RequestContext.getCurrentInstance().update("foo:bar");
                } catch (ItemNotFoundException ex) {
                    System.out.println("treebase bean::idchange::ItemNotFoundException=" + ex);
                    this.setAlreadyDeleted(true);
                } catch (DataBaseException ex) {
                    shared_util.setErrMsgValue(ex.getMessage());
                } catch (Exception ex) {
                    shared_util.setErrMsgValue(ex.getMessage());
                }
            } else {
                getTreeDivBean().setParentName(getTreeDivBean().getBundle().getString(getTreeDivBean().getRootName()));
                getTreeDivBean().setParentLevel(getTreeDivBean().getVirtualLevelCode().toString());
                getTreeDivBean().setEnabledRoot(true);
                getTreeDivBean().setEnabledNotLeaf(false);
                getTreeDivBean().setEnabledNotHasChlid(true);
            }
            getTreeDivBean().setSelectionNo(1);
        }
    }

    public void cancelSearchTreeDiv() {
        try {
            initTreeDivBean();
            getTreeDivBean().setSearchQuery("");
            getTreeDivBean().setSearchMode(false);
            getTreeDivBean().setSelectionNo(0);
            List resultList = BgtClientFactory.getBgtAccountsClient().getByStatus_CustomName(ISystemConstant.ACTIVE_FLAG);
            getTreeDivBean().setMyTableData(resultList);
            getTreeDivBean().setDtoDetails(BgtDTOFactory.createBgtAccountsDTO());
            getTreeDivBean().buildTree();
            getTreeDivBean().getHtmlTree().collapseAll();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchTree() throws DataBaseException, SharedApplicationException {
        IBgtAccountsClient client = BgtClientFactory.getBgtAccountsClient();
        List resultList = new ArrayList();
        getTreeDivBean().setDtoDetails(BgtDTOFactory.createBgtAccountsDTO());
        if(!treeDivBean.isEnableSearchByCode())
            getTreeDivBean().setSelectedRadio("");

        try {
            if (treeDivBean.isEnableSearchByCode() &&
                treeDivBean.getSearchType().equals(treeDivBean.getSearchTypeCode())) {
                System.out.println("searchTYPE BY CODE ==>>   " + treeDivBean.getSearchType());
                resultList = client.searchByActCode(treeDivBean.getSearchQuery());
            } else {
                System.out.println("searchTYPE BY NAME ==>>  " + treeDivBean.getSearchType());
                resultList = client.searchByName(getTreeDivBean().getSearchQuery());
            }

            getTreeDivBean().setMyTableData(resultList);
        } catch (DataBaseException dbe) {
            dbe.printStackTrace();
            getTreeDivBean().setMyTableData(new ArrayList());
        } catch (SharedApplicationException sae) {
            sae.printStackTrace();
            getTreeDivBean().setMyTableData(new ArrayList());
        }
    }


    public void getSelectedBudget() throws DataBaseException {
        if (getTreeDivBean().getDtoDetails() != null && getTreeDivBean().getDtoDetails().getCode() != null) {
            try {
                selectedBudgetDTO = (IBasicDTO)getSharedUtil().deepCopyObject(getTreeDivBean().getDtoDetails());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**********************************************************************************************************************/


    public void addBgtAcctElmguides() {
        try {
            try {
                boolean degreeAddedBefore =
                    ((IBgtAcctElmguidesClient)getClient()).checkElementHasDegree(selectedElementCode, selectedDegree,
                                                                                 null);
                if (degreeAddedBefore) {
                    getSharedUtil().handleException(null, BUNDULE_NAME, "degreeAddedBefore");
                    return;
                }
            } catch (Exception e) {
                ;
            }

            if (defaultFlag) {
                try {
                    boolean elementHasDefaultValueBefore =
                        ((IBgtAcctElmguidesClient)getClient()).checkElementHasDefaultFlag(selectedElementCode, null);
                    if (elementHasDefaultValueBefore) {
                        getSharedUtil().handleException(null, BUNDULE_NAME, "elementHasDefaultValueBefore");
                        return;
                    }
                } catch (Exception e) {
                    ;
                }
            }

            IBgtAcctElmguidesDTO pageDTO = (IBgtAcctElmguidesDTO)getPageDTO();
            pageDTO.setElementCode(selectedElementCode);
            pageDTO.setDegreeCode(selectedDegree);
            pageDTO.setFromDate(SharedUtils.getCurrentDate());
            pageDTO.setStatus(ISystemConstant.ACTIVE_FLAG);
            pageDTO.setBgtAccountsDTO((IBgtAccountsDTO)selectedBudgetDTO);
            if (defaultFlag)
                pageDTO.setDefaultFlag(ISystemConstant.ACTIVE_FLAG);
            else
                pageDTO.setDefaultFlag(ISystemConstant.NON_ACTIVE_FLAG);

            super.add();
            reset();
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "FailureInAdd");
        }
    }

    public void preUpdate() {

        try {
            IBasicDTO dto = getSelectedDTOS().get(0);
            IBgtAcctElmguidesDTO selectedDTO =
                (IBgtAcctElmguidesDTO)BgtClientFactory.getBgtAcctElmguidesClient().getById(dto.getCode());
            selectedElementCode = selectedDTO.getElementCode();
            selectedCader = selectedDTO.getCaderCode();
            getGroupsByCader(null);
            selectedGroup = selectedDTO.getGroupCode();
            getDegreesByGroup(null);
            selectedDegree = selectedDTO.getDegreeCode();
            selectedBudgetDTO = selectedDTO.getBgtAccountsDTO();
            if (selectedDTO.getDefaultFlag().equals(ISystemConstant.ACTIVE_FLAG))
                defaultFlag = true;
            else
                defaultFlag = false;

            setPageDTO(selectedDTO);
            setPageMode(1);
        } catch (Exception e) {
            setPageMode(0);
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "FailureInUpdate");
        }
    }

    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        SharedUtilBean sharedUtil = getSharedUtil();
        Long serial = ((IBgtAcctElmguidesEntityKey)getPageDTO().getCode()).getAcctElmguideSerial();
        try {
            boolean degreeAddedBefore =
                ((IBgtAcctElmguidesClient)getClient()).checkElementHasDegree(selectedElementCode, selectedDegree,
                                                                             serial);
            if (degreeAddedBefore) {
                getSharedUtil().handleException(null, BUNDULE_NAME, "degreeAddedBefore");
                return;
            }
        } catch (Exception e) {
            ;
        }

        if (defaultFlag) {
            try {
                boolean elementHasDefaultValueBefore =
                    ((IBgtAcctElmguidesClient)getClient()).checkElementHasDefaultFlag(selectedElementCode, serial);
                if (elementHasDefaultValueBefore) {
                    getSharedUtil().handleException(null, BUNDULE_NAME, "elementHasDefaultValueBefore");
                    return;
                }
            } catch (Exception e) {
                ;
            }
        }

        try {
            executeEdit();
            getAll();
            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().add(getPageDTO());
            }
            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            reset();
            this.getSelectedDTOS().clear();

        } catch (DataBaseException e) {
            sharedUtil.handleException(e);
        } catch (ItemNotFoundException item) {
            sharedUtil.handleException(item);
        } catch (SharedApplicationException e) {
            sharedUtil.handleException(e);
        } catch (Exception e) {
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
        }
        setSelectedRadio("");

    }

    protected IBasicDTO executeEdit() throws DataBaseException, SharedApplicationException {
        IBgtAcctElmguidesDTO pageDTO = (IBgtAcctElmguidesDTO)getPageDTO();
        pageDTO.setElementCode(selectedElementCode);
        pageDTO.setDegreeCode(selectedDegree);
        pageDTO.setBgtAccountsDTO((IBgtAccountsDTO)selectedBudgetDTO);
        if (defaultFlag)
            pageDTO.setDefaultFlag(ISystemConstant.ACTIVE_FLAG);
        else
            pageDTO.setDefaultFlag(ISystemConstant.NON_ACTIVE_FLAG);

        getClient().update(pageDTO);

        return pageDTO;
    }

    public String back() {
        if (backMethod != null && !backMethod.equals("")) {
            try {
                Object[] objects = new Object[2];
                objects[0] = selectedElementCode;
                objects[1] = savedDataObj;

                Class[] classes = new Class[2];
                classes[0] = Long.class;
                classes[1] = Object.class;
                executeMethodBindingWithParams(backMethod, objects, classes);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
        return backNavCase;
    }

    public void reset() {
        selectedCader = getVirtualLongValue();
        selectedGroup = getVirtualLongValue();
        groups = null;
        selectedDegree = getVirtualLongValue();
        degrees = null;
        selectedBudgetDTO = BgtDTOFactory.createBgtAccountsDTO();
        defaultFlag = false;
        setPageMode(0);
        getSelectedDTOS().clear();
        setSelectedRadio("");
    }

    public void getGroupsByCader(ActionEvent ae) {
        groups = null;
        degrees = null;
        selectedGroup = getVirtualLongValue();
        selectedDegree = getVirtualLongValue();
        if (selectedCader != null && !selectedCader.equals(getVirtualLongValue())) {
            try {
                groups = SalClientFactory.getSalElementGuidesClient().getGroupCodeName(selectedCader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getDegreesByGroup(ActionEvent ae) {
        degrees = null;
        selectedDegree = getVirtualLongValue();
        if (selectedGroup != null && !selectedGroup.equals(getVirtualLongValue())) {
            try {
                degrees = SalClientFactory.getSalElementGuidesClient().getDegreesCodeNameByGroup(selectedGroup);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setCaders(List caders) {
        this.caders = caders;
    }

    public List getCaders() {
        return caders;
    }

    public void setSelectedCader(Long selectedCader) {
        this.selectedCader = selectedCader;
    }

    public Long getSelectedCader() {
        return selectedCader;
    }

    public void setGroups(List groups) {
        this.groups = groups;
    }

    public List getGroups() {
        return groups;
    }

    public void setSelectedGroup(Long selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public Long getSelectedGroup() {
        return selectedGroup;
    }

    public void setDegrees(List degrees) {
        this.degrees = degrees;
    }

    public List getDegrees() {
        return degrees;
    }

    public void setSelectedDegree(Long selectedDegree) {
        this.selectedDegree = selectedDegree;
    }

    public Long getSelectedDegree() {
        return selectedDegree;
    }


    public void setTreeDivBean(TreeDivBean treeDivBean) {
        this.treeDivBean = treeDivBean;
    }

    public TreeDivBean getTreeDivBean() {
        if (treeDivBean == null) {
            treeDivBean = (TreeDivBean)JSFHelper.getValue("treeDivBean");
        }
        return treeDivBean;
    }

    public void setSelectedBudgetDTO(IBasicDTO selectedBudgetDTO) {
        this.selectedBudgetDTO = selectedBudgetDTO;
    }

    public IBasicDTO getSelectedBudgetDTO() {
        return selectedBudgetDTO;
    }

    public void setSelectedElementCode(Long selectedElementCode) {
        this.selectedElementCode = selectedElementCode;
    }

    public Long getSelectedElementCode() {
        return selectedElementCode;
    }

    public void setSelectedElementName(String selectedElementName) {
        this.selectedElementName = selectedElementName;
    }

    public String getSelectedElementName() {
        return selectedElementName;
    }

    public void setDefaultFlag(boolean DefaultFlag) {
        this.defaultFlag = DefaultFlag;
    }

    public boolean isDefaultFlag() {
        return defaultFlag;
    }

    public void setBackNavCase(String backNavCase) {
        this.backNavCase = backNavCase;
    }

    public String getBackNavCase() {
        return backNavCase;
    }

    public void setBackMethod(String backMethod) {
        this.backMethod = backMethod;
    }

    public String getBackMethod() {
        return backMethod;
    }

    public void setSavedDataObj(Object savedDataObj) {
        this.savedDataObj = savedDataObj;
    }

    public Object getSavedDataObj() {
        return savedDataObj;
    }
}
