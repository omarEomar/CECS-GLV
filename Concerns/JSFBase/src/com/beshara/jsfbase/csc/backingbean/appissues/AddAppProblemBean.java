package com.beshara.jsfbase.csc.backingbean.appissues;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.dto.TreeDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.flm.flm.business.FileManager;
import com.beshara.csc.flm.flm.business.dto.FileDTO;
import com.beshara.csc.flm.flm.business.dto.TransactionDTO;
import com.beshara.csc.gn.nfc.business.client.NtfClientFactory;
import com.beshara.csc.gn.nfc.business.dto.INotificationsDTO;
import com.beshara.csc.gn.nfc.business.dto.IUsersDTO;
import com.beshara.csc.gn.nfc.business.dto.NotificationsDTO;
import com.beshara.csc.gn.nfc.business.dto.UsersDTO;
import com.beshara.csc.gn.sup.business.client.ISupCoordinatorsClient;
import com.beshara.csc.gn.sup.business.client.ISupProblemSourcesClient;
import com.beshara.csc.gn.sup.business.client.ISupProblemsClient;
import com.beshara.csc.gn.sup.business.client.SupClientFactory;
import com.beshara.csc.gn.sup.business.dto.ISupCoordinatorsDTO;
import com.beshara.csc.gn.sup.business.dto.ISupMachinesDTO;
import com.beshara.csc.gn.sup.business.dto.ISupProblemsDTO;
import com.beshara.csc.gn.sup.business.dto.SupDTOFactory;
import com.beshara.csc.gn.sup.business.entity.ISupCoordinatorsEntityKey;
import com.beshara.csc.gn.sup.business.entity.ISupProblemsEntityKey;
import com.beshara.csc.gn.sup.business.entity.SupCoordinatorsEntityKey;
import com.beshara.csc.gn.sup.business.shared.ISupConstants;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;
import com.beshara.jsfbase.csc.util.Helper;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.sec.web.jsf.client.ModuleClient;

import java.io.IOException;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.custom.datascroller.HtmlDataScroller;
import org.apache.myfaces.custom.fileupload.UploadedFile;


public class AddAppProblemBean extends LookUpBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    private static final String RESOURCE_BUNDLE =  "com.beshara.jsfbase.csc.msgresources.global";
    public static final String BEAN_NAME = "addAppProblemBean";
    private Long catCode;
    private Long minCode = null;
    private List<IBasicDTO> ministryList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> categoryList;
    private Long problemImportanceCode = ISupConstants.LOW_PERIORITY;
    private List<IBasicDTO> problemImportanceList;
    private String SYSTEM_PROBLRM = "3";
    private String MACHINE_PROBLRM = "2";
    private String NETWORK_PROBLRM = "1";
    private String problemType = "3";
    private HtmlDataTable myMachinesDataTable;
    private List machinesList;
    private String machineSearchValue;
    private boolean machineSearchMode;
    private String machineSearchType = "0";
    private List<IBasicDTO> selectedMachiesDTOS = new ArrayList<IBasicDTO>();
    private List problemCatList;
    private Long problemCatCode = ISupConstants.CAT_PROBLEM;
    private HtmlDataScroller machinesScroller;
    private HtmlDataScroller modulesScroller;

    private Long selectedMachineCode;
    private String selectedMachineDesc;
    private boolean wrongMachineSerial;
    private String selectedMachineRadio;
    private List<IBasicDTO> modulesDTOS = new ArrayList<IBasicDTO>();
        private List problemSourcesList = null;
    private Long selectedProblemSourceCode;
    private HtmlDataTable modulesDataTable;
    private List<IBasicDTO> selectedModulesDTOS = new ArrayList<IBasicDTO>();
    private String moduleSearchType = "0";
    private String moduleSearchValue;
    private boolean moduleSearchMode;
    private String selectedModuleRadio;
    private String coordinatorName;
    private ISupCoordinatorsDTO coordinatorDTO;
    private Long selectedModuleCode;
    private String selectedModuleDesc;
    private boolean wrongModuleSerial;
    private boolean disableMinCat;

    private Long loggedMin;
    private Long loggedCat;
    private int PAGE_MODE_EDIT = 2;

        private String applicantName ;
    //------------file upload =============

    //attachments
    private String fakeImageString;
    private String relativeFileName;
    private UploadedFile uploadedFile;
    private TransactionDTO filesTransaction;
    private FileDTO file;
    private boolean uploadingError;
    private boolean invalidImageType;
    private String currentExtension = "jpg";
    private String fileFullPath;
    private String relativePath;

    private String selectedImagePath = "";
    TreeDivBean treedivBean = (TreeDivBean)evaluateValueBinding("treeDivBean");
    
    private Object savedDataObj;
    private Boolean isDiwan;
    private Boolean editModuleCode=false;
    private List<IBasicDTO> attachmentList;
    private List coordList = new ArrayList() ;
    private String selectedCoordCode;
    public static final String SUPPORT_ADMINISTRATION = "0213275";
    public static final String DEVELOP_ADMINISTRATION = "0213273";

      private AttachmentsListBean attachmentsListBean=AttachmentsListBean.getInstance();

    public AddAppProblemBean() {
        setPageDTO(SupDTOFactory.createSupProblemsDTO());
        setSelectedPageDTO(SupDTOFactory.createSupProblemsDTO());
        setSearchDTO(SupDTOFactory.createSupProblemsDTO());
        setClient(SupClientFactory.getSupProblemsClient());
        setCustomDiv1("m2mEditDivClass");
        setCustomDiv2("m2mEditDivClass");
        setCustomDiv1TitleKey("module_div_title");
        setCustomDiv2TitleKey("module_div_title");
        initTreeDivBean();
    }

    public void getAll() {
        setMyTableData(new ArrayList());
    }

    @Override
    public void initiateBeanOnce() {
        setDisableMinCat(false);
        filterByCategory(null);
        loggedMin = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        loggedCat = CSCSecurityInfoHelper.getLoggedInCategoryCode();

        setCatCode(loggedCat);
        filterByCategory(null);

        setMinCode(loggedMin);

        setDisableMinCat(true);
    //Diwan 
    if(getPageMode() != PAGE_MODE_EDIT)
        selectedProblemSourceCode=ISupConstants.CSC_PROBLEM_SOURCE;
    
        if(attachmentList==null)
                         attachmentList=new ArrayList<IBasicDTO>();
    
        initAttachment();
    }

    public static AddAppProblemBean getInstance() {
        return (AddAppProblemBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowSearch(false);
        app.setShowbar(true);
        app.setShowTreediv(true);
        app.setShowContent1(true);
        app.setShowCustomDiv1(true);
        app.setShowCustomDiv2(true);
        app.setShowdatatableContent(false);

        return app;
    }



    //======================= Start Tree Part =========================
        public void initTreeDivBean() {

        treedivBean.setBundleName(RESOURCE_BUNDLE + "_");
        treedivBean.setRootName("modules_title");
        treedivBean.setClient((ISupProblemsClient)getClient());
        treedivBean.setPageDTO(new TreeDTO());
        treedivBean.setDto(new TreeDTO());
        treedivBean.setDtoDetails(new TreeDTO());
        treedivBean.setMyTableData(new ArrayList());
        treedivBean.setSelectedEntityKeyMethod(BEAN_NAME + ".getSelectedIEntityKey");
        treedivBean.setGoActionOkMethod(BEAN_NAME + ".setSelectedTypeCode");
        treedivBean.getOkCommandButton().setReRender("typeNamePanelId,typeFilterationId,OperationBar");
        treedivBean.setSelectionNo(0);
        //treedivBean.setSearchTreeMethod(BEAN_NAME + ".searchTree");

        treedivBean.setCancelSearchMethod(BEAN_NAME + ".cancelTreeSearch");
        treedivBean.setIdChangeMethod(BEAN_NAME + ".idChange");

    }
        
    public void openTreeDiv() throws DataBaseException {
        cancelTreeSearch();
        getTypesTree();
        treedivBean.setEnabledRoot(true);
    }
    public void cancelTreeSearch() {
        try {

            if (treedivBean.isUsingTreePaging()) {
                treedivBean.setSearchQuery("");
                treedivBean.setSearchType(0);
                treedivBean.setSearchMode(false);
            } else {
                super.cancelSearch();
                treedivBean.setSelectionNo(0);
                treedivBean.buildTree();
                treedivBean.getHtmlTree().collapseAll();
            }
            treedivBean.setDtoDetails(new TreeDTO());
            treedivBean.setSelectedRadio("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getTypesTree() {

        try {
            modulesDTOS = ((ISupProblemsClient)getClient()).getModulesTree(new TreeDTO(),false);
            treedivBean.setMyTableData(modulesDTOS);
            treedivBean.buildTree();
            treedivBean.getHtmlTree().expandAll();
        } catch (SharedApplicationException e) {
            treedivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        } catch (DataBaseException e) {
            treedivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        } catch (Exception e) {
            treedivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        }
    }

    public void setSelectedTypeCode() {

        setWrongModuleSerial(false);
        setWrongMachineSerial(false);

        if (treedivBean.getDtoDetails() != null && treedivBean.getDtoDetails().getCode() != null) {
            ITreeDTO selectedDTO = treedivBean.getDtoDetails();

            setSelectedModuleCode(Long.valueOf(selectedDTO.getCode().getKey()));
            setSelectedModuleDesc(selectedDTO.getName());
            setCoordinatorName(null);

            getCoordData(1L, minCode, selectedModuleCode);
        } else {
            setSelectedModuleCode(null);
            setSelectedModuleDesc(null);
        }

    }
    
    public void idChange(ValueChangeEvent e) {

        if (treedivBean.isUsingTreePaging()) {
            treedivBean.setDtoDetails(new TreeDTO());
        } else {
            treedivBean.initializeDtoDetails();
        }
        treedivBean.setSuccess(false);
        treedivBean.setSelectionNo(0);
        String value = (String)e.getNewValue();

        if (value != null && !value.equals("")) {
            String nodeCode = (String)e.getNewValue();

            // if there is composte key
            if (treedivBean.getKeyIndex() > -1 && !treedivBean.isUsingTreePaging()) {
                try {
                    nodeCode = treedivBean.getFullEntityKey(treedivBean.getTreeNodeBase().getChildren(), nodeCode);

                } catch (Exception f) {
                    f.printStackTrace();
                }
            }

            if (nodeCode != null && !nodeCode.equals("0")) {

                SharedUtilBean shared_util = getSharedUtil();
                try {
                    if (treedivBean.isUsingTreePaging()) {
                        treedivBean.setEntityKey(treedivBean.getSelectedEntityKey(nodeCode));
                    } else {
                        treedivBean.setEntityKey(Helper.getEntityKey((List<IBasicDTO>)treedivBean.getMyTableData(),
                                                                     nodeCode));
                    }

                    this.setAlreadyDeleted(false);

                    if (treedivBean.getDtoDetails() == null || treedivBean.getDtoDetails().getCode() == null ||
                        (!value.equals(treedivBean.getDtoDetails().getCode().getKey().toString()))) {
                        ModuleClient.getInstance().getByPK(Long.parseLong(treedivBean.getEntityKey().getKey()));
                        treedivBean.setDtoDetails((ITreeDTO)SupClientFactory.getSupProblemsClient().getSecModuleNodeById(treedivBean.getEntityKey(),false));
                    }

                    if (treedivBean.getDtoDetails() != null) {
                        if (treedivBean.getDtoDetails().isBooleanLeafFlag()) {
                            treedivBean.setEnabledNotLeaf(true);
                        } else {
                            treedivBean.setEnabledNotLeaf(false);
                        }
                        if (treedivBean.getDtoDetails().getChildernNumbers() != 0) {
                            treedivBean.setEnabledNotHasChlid(true);
                        } else {
                            treedivBean.setEnabledNotHasChlid(false);
                        }
                    }

                    if (treedivBean.getDtoDetails() != null && treedivBean.getDtoDetails().getParentCode() == null) {
                        treedivBean.getDtoDetails().setParentCode(getEntityKey());
                    }

                    treedivBean.setParentName(treedivBean.getDtoDetails().getName());

                    treedivBean.setParentLevel(nodeCode);
                    treedivBean.setRenderEdit(true);
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
                treedivBean.setParentName(treedivBean.getBundle().getString(treedivBean.getRootName()));
                treedivBean.setParentLevel(treedivBean.getVirtualLevelCode().toString());
                treedivBean.setEnabledRoot(true);
                treedivBean.setEnabledNotLeaf(false);
                treedivBean.setEnabledNotHasChlid(true);
            }
            treedivBean.setSelectionNo(1);
        }

    }

    public void getModuleNodeByCode(ActionEvent ae) {
        setCoordinatorName(null);
        setWrongModuleSerial(false);
        setCoordList(new ArrayList());
        setCoordinatorDTO(null);
        try {
            IEntityKey enteredNodeEK = new EntityKey(selectedModuleCode);
            ITreeDTO returnedTreeDTO = ((ISupProblemsClient)getClient()).getSecModuleNodeById(enteredNodeEK,false);
            if (returnedTreeDTO != null) {

                setSelectedModuleDesc(returnedTreeDTO.getName());
                getCoordData(1L, minCode, selectedModuleCode);
            } else {
                setWrongModuleSerial(true);
                setSelectedModuleDesc("");
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            setWrongModuleSerial(true);
            setSelectedModuleDesc("");
            setSelectedModuleCode(null);

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setWrongModuleSerial(true);
            setSelectedModuleDesc("");
            setSelectedModuleCode(null);
        }
    }
    
    //======================= End Tree Part ===========================
    public void setCatCode(Long catCode) {
        this.catCode = catCode;
    }

    public Long getCatCode() {
        return catCode;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setMinistryList(List<IBasicDTO> ministryList) {
        this.ministryList = ministryList;
    }

    public List<IBasicDTO> getMinistryList() {
        return ministryList;
    }

    public void setCategoryList(List<IBasicDTO> categoryList) {
        this.categoryList = categoryList;
    }

    public List<IBasicDTO> getCategoryList() {
        if (categoryList == null || categoryList.size() == 0) {
            try {
                categoryList = OrgClientFactory.getCatsClient().getCatsByGovFlag(ISystemConstant.GOVERNMENT_FLAG);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
        return categoryList;
    }

    public void filterByCategory(ActionEvent event) {
        minCode = null;
        if(getPageMode() != PAGE_MODE_EDIT && event!=null){
                
                       coordinatorDTO = null;        
                   
                   setCoordList(new ArrayList());
               }
        if (catCode != null) {
            try {
                ministryList = OrgClientFactory.getMinistriesClient().getAllByCategory(catCode);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                ministryList = new ArrayList<IBasicDTO>();
            } catch (DataBaseException e) {
                e.printStackTrace();
                ministryList = new ArrayList<IBasicDTO>();
            }
        } else {
            ministryList = new ArrayList<IBasicDTO>();
        }
    }

    public void setProblemImportanceCode(Long problemImportanceCode) {
        this.problemImportanceCode = problemImportanceCode;
    }

    public Long getProblemImportanceCode() {
        return problemImportanceCode;
    }

    public void setProblemImportanceList(List<IBasicDTO> problemImportanceList) {
        this.problemImportanceList = problemImportanceList;
    }

    public List<IBasicDTO> getProblemImportanceList() {

        if (problemImportanceList == null || problemImportanceList.size() == 0) {
            try {
                problemImportanceList = SupClientFactory.getSupProblemPrioritiesClient().getAll();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }


        return problemImportanceList;
    }


    public void changeProblemType(ActionEvent ae) {

        setCoordinatorName(null);
        setCoordinatorDTO(null);
        setCoordList(new ArrayList());
        setSelectedMachineCode(null);
        setSelectedMachineDesc(null);
        setSelectedMachiesDTOS(new ArrayList());

        setSelectedModuleCode(null);
        setSelectedModuleDesc(null);
        setSelectedModulesDTOS(new ArrayList());
        setWrongModuleSerial(false);
        setWrongMachineSerial(false);

        System.out.println(problemType);
        if (problemType.equals(NETWORK_PROBLRM)) {
            //       ISupCoordinatorsClient coordClient =  SupClientFactory.getSupCoordinatorsClient();
            //            try {
            //                List list = coordClient.getCoordinatorForMove( 4L , minCode) ;
            //                if(list != null && list.size() > 0){
            //                coordinatorDTO = (ISupCoordinatorsDTO)list.get(0);
            //                setCoordinatorName(coordinatorDTO.getName());
            //                }
            //            } catch (DataBaseException e) {
            //            } catch (SharedApplicationException e) {
            //            }
            getCoordData(4L, minCode, null);
        }
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setSYSTEM_PROBLRM(String SYSTEM_PROBLRM) {
        this.SYSTEM_PROBLRM = SYSTEM_PROBLRM;
    }

    public String getSYSTEM_PROBLRM() {
        return SYSTEM_PROBLRM;
    }

    public void setMACHINE_PROBLRM(String MACHINE_PROBLRM) {
        this.MACHINE_PROBLRM = MACHINE_PROBLRM;
    }

    public String getMACHINE_PROBLRM() {
        return MACHINE_PROBLRM;
    }

    public void showMachinesDiv() {
        System.out.println("showMachinesDiv");
        setPageDTO(SupDTOFactory.createSupProblemsDTO());
        setSelectedMachineRadio("");
        setSelectedMachiesDTOS(new ArrayList());
        setMachineSearchMode(false);
        setMachineSearchValue(null);
        if (machinesScroller != null)
            machinesScroller.getUIData().setFirst(0);
        try {
            machinesList = SupClientFactory.getSupMachinesClient().getAll();
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
    }

    public void loadPagingData(ActionEvent ae) {
        try {
            machinesList = SupClientFactory.getSupMachinesClient().getAll();
            setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv2);");
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
    }


    public void setMyMachinesDataTable(HtmlDataTable myMachinesDataTable) {
        this.myMachinesDataTable = myMachinesDataTable;
    }

    public HtmlDataTable getMyMachinesDataTable() {
        return myMachinesDataTable;
    }

    public void setMachinesList(List machinesList) {
        this.machinesList = machinesList;
    }

    public List getMachinesList() {
        return machinesList;
    }

    public void setMachineSearchValue(String machineSearchValue) {
        this.machineSearchValue = machineSearchValue;
    }

    public String getMachineSearchValue() {
        return machineSearchValue;
    }

    public void searchMachines() {
        try {
            if (machineSearchType.equals("0"))
                machinesList = SupClientFactory.getSupMachinesClient().searchByCode(machineSearchValue);
            else
                machinesList = SupClientFactory.getSupMachinesClient().searchByName(machineSearchValue);
            setMachineSearchMode(true);
            machinesScroller.getUIData().setFirst(0);
        } catch (DataBaseException e) {
            machinesList = new ArrayList();
            setMachineSearchMode(true);
        } catch (SharedApplicationException e) {
            machinesList = new ArrayList();
            setMachineSearchMode(true);
        }
    }

    public void loadModulesDivPagingData(ActionEvent ae) {
        try {
            modulesDTOS = ((ISupProblemsClient)getClient()).getModules(SupDTOFactory.createSupProblemsDTO());
            setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }


    }

    public void showModulesDiv() {
        System.out.println("showModulesDiv");
        setSelectedModuleRadio("");
        setSelectedModulesDTOS(new ArrayList());
        setModuleSearchValue(null);
        setModuleSearchMode(false);
        try {
            modulesDTOS = ((ISupProblemsClient)getClient()).getModules(SupDTOFactory.createSupProblemsDTO());
            if (modulesScroller != null)
                modulesScroller.getUIData().setFirst(0);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
    }

    public void searchModules() {
        ISupProblemsDTO supProblemsDTO = SupDTOFactory.createSupProblemsDTO();
        try {
            if (moduleSearchType.equals("0")) {
                Long modCode = -1L;
                try {
                    modCode = Long.parseLong(moduleSearchValue);
                } catch (NumberFormatException nfe) {
                    // TODO: Add catch code
                    nfe.printStackTrace();
                }
                supProblemsDTO.setAppmoduleCode(modCode);
            } else {
                supProblemsDTO.setName(moduleSearchValue);
            }
            modulesScroller.getUIData().setFirst(0);
            modulesDTOS = ((ISupProblemsClient)getClient()).getModules(supProblemsDTO);
            setModuleSearchMode(true);
        } catch (DataBaseException e) {
            setModuleSearchMode(true);

        } catch (SharedApplicationException e) {
            setModuleSearchMode(true);

        }
    }

    public void cancelSearchModules() {
        try {
            modulesDTOS = ((ISupProblemsClient)getClient()).getModules(SupDTOFactory.createSupProblemsDTO());
            setModuleSearchMode(false);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }

    public void selectedModuleRadioButton(ActionEvent event) throws Exception {
        this.getSelectedModulesDTOS().clear();
        IBasicDTO selected = (IBasicDTO)this.getModulesDataTable().getRowData();
        this.getSelectedModulesDTOS().add(selected);
    }

    public void onCloseModulesDiv() {
        setWrongModuleSerial(false);
        setWrongMachineSerial(false);
        if (getSelectedModulesDTOS() != null && getSelectedModulesDTOS().size() > 0) {
            IBasicDTO selectedDTO = (IBasicDTO)getSelectedModulesDTOS().get(0);
            setSelectedModuleCode(Long.valueOf(selectedDTO.getCode().getKey()));
            setSelectedModuleDesc(selectedDTO.getName());
            setCoordinatorName(null);
            //            ISupCoordinatorsClient coordClient =  SupClientFactory.getSupCoordinatorsClient();
            //            try {
            //                List list = coordClient.getCoordinatorForMove( 1L , minCode) ;
            //                if(list != null && list.size() > 0){
            //                coordinatorDTO = (ISupCoordinatorsDTO)list.get(0);
            //                setCoordinatorName(coordinatorDTO.getName());
            //                }
            //            } catch (DataBaseException e) {
            //            } catch (SharedApplicationException e) {
            //            }
            getCoordData(1L, minCode, selectedModuleCode);
        } else {
            setSelectedModuleCode(null);
            setSelectedModuleDesc(null);
        }
    }

    public void getModuleByCode(ActionEvent ae) {
        setCoordinatorName(null);
        setWrongModuleSerial(false);
        setCoordList(new ArrayList());
        try {
            ISupProblemsDTO supProblemsDTO = SupDTOFactory.createSupProblemsDTO();
            supProblemsDTO.setAppmoduleCode(selectedModuleCode);
            modulesDTOS = ((ISupProblemsClient)getClient()).getModules(supProblemsDTO);
            if (modulesDTOS != null && modulesDTOS.size() > 0) {
                IBasicDTO dto = (IBasicDTO)modulesDTOS.get(0);
                setSelectedModuleDesc(dto.getName());

                //                ISupCoordinatorsClient coordClient =  SupClientFactory.getSupCoordinatorsClient();
                //
                //                List list = coordClient.getCoordinatorForMove( 1L , minCode) ;
                //                if(list != null && list.size() > 0){
                //                coordinatorDTO = (ISupCoordinatorsDTO)list.get(0);
                //                setCoordinatorName(coordinatorDTO.getName());
                //                }
                getCoordData(1L, minCode, selectedModuleCode);
            } else {
                setWrongModuleSerial(true);
                setSelectedModuleDesc("");
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            setWrongModuleSerial(true);
            setSelectedModuleDesc("");
            setSelectedModuleCode(null);

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setWrongModuleSerial(true);
            setSelectedModuleDesc("");
            setSelectedModuleCode(null);
        }
    }


    public void setMachineSearchMode(boolean machineSearchMode) {
        this.machineSearchMode = machineSearchMode;
    }

    public boolean getMachineSearchMode() {
        return machineSearchMode;
    }
   
    
    public void cancelSearchMachines() {
        try {
            machinesList = SupClientFactory.getSupMachinesClient().getAll();
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        setMachineSearchMode(false);
        setMachineSearchValue(null);
    }

    public void setMachineSearchType(String machineSearchType) {
        this.machineSearchType = machineSearchType;
    }

    public String getMachineSearchType() {
        return machineSearchType;
    }

    public void selectedMachinesRadioButton(ActionEvent event) throws Exception {
        this.getSelectedMachiesDTOS().clear();
        IClientDTO selected = (IClientDTO)this.getMyMachinesDataTable().getRowData();
        this.getSelectedMachiesDTOS().add(selected);
    }


    public void setSelectedMachiesDTOS(List<IBasicDTO> selectedMachiesDTOS) {
        this.selectedMachiesDTOS = selectedMachiesDTOS;
    }

    public List<IBasicDTO> getSelectedMachiesDTOS() {
        return selectedMachiesDTOS;
    }

    //    public String back() {
    //        return BACK_NAV_CASE;
    //    }

    public void setProblemCatList(List problemCatList) {
        this.problemCatList = problemCatList;
    }

    public List getProblemCatList() {
        if (problemCatList == null || problemCatList.size() == 0) {
            try {
                problemCatList = SupClientFactory.getSupProblemCatsClient().getAll();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
        return problemCatList;
    }

    public void setProblemCatCode(Long problemCatCode) {
        this.problemCatCode = problemCatCode;
    }

    public Long getProblemCatCode() {
        return problemCatCode;
    }

    public void setNETWORK_PROBLRM(String NETWORK_PROBLRM) {
        this.NETWORK_PROBLRM = NETWORK_PROBLRM;
    }

    public String getNETWORK_PROBLRM() {
        return NETWORK_PROBLRM;
    }

    public void setSelectedMachineCode(Long selectedMachineCode) {
        this.selectedMachineCode = selectedMachineCode;
    }

    public Long getSelectedMachineCode() {
        return selectedMachineCode;
    }

    public void getMachineByCode(ActionEvent ae) {

        setCoordinatorName(null);
        setWrongMachineSerial(false);
        setCoordList(new ArrayList());
        setCoordinatorDTO(null);
        try {
            ISupMachinesDTO dto =
                (ISupMachinesDTO)SupClientFactory.getSupMachinesClient().searchByCode(selectedMachineCode).get(0);
            setSelectedMachineDesc(dto.getMachineDesc());

            //               ISupCoordinatorsClient coordClient =  SupClientFactory.getSupCoordinatorsClient();
            //            List list = coordClient.getCoordinatorForMove( 3L , minCode) ;
            //            if(list != null && list.size() > 0){
            //            coordinatorDTO = (ISupCoordinatorsDTO)list.get(0);
            //            setCoordinatorName(coordinatorDTO.getName());
            //            }
            getCoordData(3L, minCode, null);

        } catch (DataBaseException e) {
            e.printStackTrace();
            setWrongMachineSerial(true);
            setSelectedMachineDesc("");
            setSelectedMachineCode(null);

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setWrongMachineSerial(true);
            setSelectedMachineDesc("");
            setSelectedMachineCode(null);
        }
    }

    public void setSelectedMachineDesc(String selectedMachineDesc) {
        this.selectedMachineDesc = selectedMachineDesc;
    }

    public String getSelectedMachineDesc() {
        return selectedMachineDesc;
    }
    public void getCoordinatorByLoggedInUserCode() {
           try {
               if (CSCSecurityInfoHelper.getUser() != null && CSCSecurityInfoHelper.getUser().getCode() != null) {
                   Long loggedInUser = CSCSecurityInfoHelper.getUser().getCode();

                   ISupCoordinatorsClient coordinatorsClient = SupClientFactory.getSupCoordinatorsClient();
                   ISupCoordinatorsDTO loggedInUserCoordinatorsDTO;
                   loggedInUserCoordinatorsDTO = coordinatorsClient.getCoordinatorByUserCode(loggedInUser);
                   coordinatorDTO = loggedInUserCoordinatorsDTO;
                   if (coordinatorDTO != null && coordinatorDTO.getCode() != null) {
                       setCoordinatorName(coordinatorDTO.getName());
                       selectedCoordCode = coordinatorDTO.getCode().getKey();
    //                    loadProblemSourcesList();
                   }

               }
           } catch (DataBaseException e) {
               e.printStackTrace();
           } catch (SharedApplicationException e) {
               e.printStackTrace();
           }
       }

    public void onCloseMachinesDiv() {
        setWrongModuleSerial(false);
        setWrongMachineSerial(false);
        ISupMachinesDTO selectedDTO = (ISupMachinesDTO)getSelectedMachiesDTOS().get(0);
        setSelectedMachineCode(Long.valueOf(selectedDTO.getMachineSerial()));
        setSelectedMachineDesc(selectedDTO.getMachineDesc());
        setCoordinatorName(null);

        //        ISupCoordinatorsClient coordClient =  SupClientFactory.getSupCoordinatorsClient();
        //
        //        try {
        //            List list = coordClient.getCoordinatorForMove( 3L , minCode) ;
        //            if(list != null && list.size() > 0){
        //            coordinatorDTO = (ISupCoordinatorsDTO)list.get(0);
        //            setCoordinatorName(coordinatorDTO.getName());
        //            }
        //        } catch (DataBaseException e) {
        //        } catch (SharedApplicationException e) {
        //        }
        getCoordData(3L, minCode, null);
    }

    public void setWrongMachineSerial(boolean wrongMachineSerial) {
        this.wrongMachineSerial = wrongMachineSerial;
    }

    public boolean isWrongMachineSerial() {
        return wrongMachineSerial;
    }

    public void save() {

        ISupProblemsDTO dto = (ISupProblemsDTO)getPageDTO();


        dto.setPlmtypeCode(Long.valueOf(getProblemType()));
        dto.setPlmcatCode(getProblemCatCode());
        dto.setUserCode(CSCSecurityInfoHelper.getUserCode());
        dto.setMinCode(getMinCode());
        dto.setPlmsourceCode(selectedProblemSourceCode);
        
        dto.setAttachmentList(attachmentsListBean.getAttachmentList());
        dto.setPlmstatusCode(ISupConstants.SUP_PROBLEM_STATUS_SENT_DONE);
        dto.setPlmpriortyCode(getProblemImportanceCode());
        if (problemType.equals(SYSTEM_PROBLRM)) {
            dto.setAppmoduleCode(getSelectedModuleCode());
            dto.setMachineSerial(null);
        } else if (problemType.equals(MACHINE_PROBLRM)) {
            dto.setMachineSerial(getSelectedMachineCode().toString());
            dto.setAppmoduleCode(null);
        } else if (problemType.equals(NETWORK_PROBLRM)) {
            dto.setMachineSerial(null);
            dto.setAppmoduleCode(null);
        }
        //dto.setContactDetails(contactDetails);
        //dto.setAppmoduleCode(appmoduleCode);
        try {
//            addAttachment();
//            if (getFile() != null && getFile().getCode() != null) {
//                dto.setImagePath(getFile().getCode().getKey());
//            }
            if (coordinatorDTO == null || coordinatorDTO.getCode() == null) {

                getSharedUtil().handleException(null, RESOURCE_BUNDLE, "coord_not_found");
                return;
            }

            dto.setProblemDate(SharedUtils.getCurrentDate());
            dto.setProbelmReceviedDate(SharedUtils.getCurrentDate());
            IBasicDTO addedDTO = ((ISupProblemsClient)getClient()).addProblemAndMove(dto, coordinatorDTO);
            //add(dto);
            getSharedUtil().handleSuccMsg(RESOURCE_BUNDLE, "add_done_succ");


            // Notification by Saad
            try {
                ISupProblemsDTO problemsDTO =
                    (ISupProblemsDTO)SupClientFactory.getSupProblemsClient().getById(addedDTO.getCode());
                notifyUsers(problemsDTO, coordinatorDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // end by saad
            reset();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }

    }

    private void reset() {
        setPageDTO(SupDTOFactory.createSupProblemsDTO());
        initiateBeanOnce();
        problemType = SYSTEM_PROBLRM;
        problemImportanceCode = ISupConstants.LOW_PERIORITY;
        problemCatCode = ISupConstants.CAT_PROBLEM;
        
        setCoordinatorName(null);
        setCoordinatorDTO(null);

        setSelectedMachineCode(null);
        setSelectedMachineDesc(null);
        setSelectedMachiesDTOS(new ArrayList());

        setSelectedModuleCode(null);
        setSelectedModuleDesc(null);
        setSelectedModulesDTOS(new ArrayList());
        setWrongModuleSerial(false);
        setWrongMachineSerial(false);
        
        uploadedFile = null;
        fakeImageString = "";
        coordList = new ArrayList() ;
        selectedCoordCode=null;
        initAttachment();
    }
    private void initAttachment(){
        
            attachmentsListBean.setMoveSerial(1L);
            attachmentsListBean.setAtctypeCode(2L);   
            if (getPageMode() == PAGE_MODE_EDIT) {
                ISupProblemsDTO dto = (ISupProblemsDTO)getPageDTO();
                attachmentsListBean.setProblemCode(((ISupProblemsEntityKey)dto.getCode()).getProblemCode());
            }
            attachmentsListBean.setDisableSelectedDocType(true);
            //attachmentsListBean.setModuleName("sup"); 
            attachmentsListBean.setSaveInDB(false);
            attachmentsListBean.addDocAttachment(new ArrayList<IBasicDTO>());
            attachmentsListBean.setForShowAttachmentOnly(false);
            attachmentsListBean.setAttachmentList(new ArrayList<IBasicDTO>());
        }

    private void notifyUsers(ISupProblemsDTO problemsDTO, ISupCoordinatorsDTO coordinatorDTO) {

        INotificationsDTO notificationsDTO = new NotificationsDTO();
        List nfcUsersList = new ArrayList();
        IUsersDTO nfcUserDTO = new UsersDTO();
        try {
            notificationsDTO.setRefTabrecSerial(problemsDTO.getTabrecSerial());
            String dtlMsg = "";
            nfcUserDTO.setCode(new com.beshara.csc.gn.nfc.business.entity.UsersEntityKey(coordinatorDTO.getUserCode()));
            nfcUsersList.add(nfcUserDTO);
            try {
                ISupCoordinatorsDTO mainCoordinatorsDTO =
                    SupClientFactory.getSupCoordinatorsClient().getMainCoordinatorByCenterCode(coordinatorDTO.getSupcenterCode());
                if (mainCoordinatorsDTO != null && mainCoordinatorsDTO.getCode() != null &&
                    !mainCoordinatorsDTO.getUserCode().equals(coordinatorDTO.getUserCode())) {
                    nfcUserDTO = new UsersDTO();
                    nfcUserDTO.setCode(new com.beshara.csc.gn.nfc.business.entity.UsersEntityKey(mainCoordinatorsDTO.getUserCode()));
                    nfcUsersList.add(nfcUserDTO);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            notificationsDTO.setUsers(nfcUsersList);

            dtlMsg =
                    getSharedUtil().getResourceBundle(RESOURCE_BUNDLE).getString("problemNotificationMsg").replaceFirst("#",
                                                                                                                        problemsDTO.getCode().getKey());
            dtlMsg = dtlMsg.replaceFirst("&", problemsDTO.getStatusName());
            notificationsDTO.setDetailedMsg(dtlMsg);
            notificationsDTO.setBriefMsg(dtlMsg);
            notificationsDTO.setFromTime(new Timestamp(SharedUtils.getCurrentDate().getTime()));
            //notificationsDTO.setToTime(new Timestamp(SharedUtils.getCurrentDate().getTime()));
            System.out.println("Start addNFC");
            // START ADD NEW
            NtfClientFactory.getNotificationsClient().addSysNotification(notificationsDTO);
            System.out.println("End addNFC");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSelectedMachineRadio(String selectedMachineRadio) {
        this.selectedMachineRadio = selectedMachineRadio;
    }

    public String getSelectedMachineRadio() {
        return selectedMachineRadio;
    }

    public void setModulesDTOS(List<IBasicDTO> modulesDTOS) {
        this.modulesDTOS = modulesDTOS;
    }

    public List<IBasicDTO> getModulesDTOS() {
        return modulesDTOS;
    }

    public void setModulesDataTable(HtmlDataTable modulesDataTable) {
        this.modulesDataTable = modulesDataTable;
    }

    public HtmlDataTable getModulesDataTable() {
        return modulesDataTable;
    }

    public void setModuleSearchType(String moduleSearchType) {
        this.moduleSearchType = moduleSearchType;
    }

    public String getModuleSearchType() {
        return moduleSearchType;
    }

    public void setModuleSearchValue(String moduleSearchValue) {
        this.moduleSearchValue = moduleSearchValue;
    }

    public String getModuleSearchValue() {
        return moduleSearchValue;
    }

    public void setModuleSearchMode(boolean moduleSearchMode) {
        this.moduleSearchMode = moduleSearchMode;
    }

    public boolean isModuleSearchMode() {
        return moduleSearchMode;
    }

    public void setSelectedModulesDTOS(List<IBasicDTO> selectedModulesDTOS) {
        this.selectedModulesDTOS = selectedModulesDTOS;
    }

    public List<IBasicDTO> getSelectedModulesDTOS() {
        return selectedModulesDTOS;
    }

    public void setSelectedModuleRadio(String selectedModuleRadio) {
        this.selectedModuleRadio = selectedModuleRadio;
    }

    public String getSelectedModuleRadio() {
        return selectedModuleRadio;
    }

    public void setSelectedModuleCode(Long selectedModuleCode) {
        this.selectedModuleCode = selectedModuleCode;
    }

    public Long getSelectedModuleCode() {
        return selectedModuleCode;
    }

    public void setSelectedModuleDesc(String selectedModuleDesc) {
        this.selectedModuleDesc = selectedModuleDesc;
    }

    public String getSelectedModuleDesc() {
        return selectedModuleDesc;
    }

    public void setWrongModuleSerial(boolean wrongModuleSerial) {
        this.wrongModuleSerial = wrongModuleSerial;
    }

    public boolean isWrongModuleSerial() {
        return wrongModuleSerial;
    }


    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorDTO(ISupCoordinatorsDTO coordinatorDTO) {
        this.coordinatorDTO = coordinatorDTO;
    }

    public ISupCoordinatorsDTO getCoordinatorDTO() {
        return coordinatorDTO;
    }

    public void setDisableMinCat(boolean disableMinCat) {
        this.disableMinCat = disableMinCat;
    }

    public boolean isDisableMinCat() {
        return disableMinCat;
    }

    public void setPAGE_MODE_EDIT(int PAGE_MODE_EDIT) {
        this.PAGE_MODE_EDIT = PAGE_MODE_EDIT;
    }

    public int getPAGE_MODE_EDIT() {
        return PAGE_MODE_EDIT;
    }

    public void getCoordData(Long responsibiltyTypeCode, Long ministryCode, Long appModuleCode) {

        ISupCoordinatorsClient coordClient = SupClientFactory.getSupCoordinatorsClient();
        try {
           coordList = coordClient.getCoordinatorForMove(responsibiltyTypeCode, ministryCode, appModuleCode);
            if (coordList != null && coordList.size() > 0) {
                coordinatorDTO = (ISupCoordinatorsDTO)coordList.get(0);
                setCoordinatorName(coordinatorDTO.getName());
                selectedCoordCode = coordinatorDTO.getCode().getKey(); 
            } else {
                coordinatorDTO = null;
                setCoordinatorName(null);
                setCoordList(new ArrayList());
            }
        } catch (Exception e) {
            setCoordList(new ArrayList());
            e.printStackTrace();
        } 

    }

    public void setModulesScroller(HtmlDataScroller modulesScroller) {
        this.modulesScroller = modulesScroller;
    }

    public HtmlDataScroller getModulesScroller() {
        return modulesScroller;
    }

    public void setMachinesScroller(HtmlDataScroller machinesScroller) {
        this.machinesScroller = machinesScroller;
    }

    public HtmlDataScroller getMachinesScroller() {
        return machinesScroller;
    }

    public void changeMinistry() {
        if (problemType.equals(SYSTEM_PROBLRM)) {
            getCoordData(1L, minCode, selectedModuleCode);
        } else if (problemType.equals(MACHINE_PROBLRM)) {
            getCoordData(3L, minCode, null);
        } else if (problemType.equals(NETWORK_PROBLRM))
            getCoordData(4L, minCode, null);
    }

    public void setLoggedMin(Long loggedMin) {
        this.loggedMin = loggedMin;
    }

    public Long getLoggedMin() {
        return loggedMin;
    }

    public void setLoggedCat(Long loggedCat) {
        this.loggedCat = loggedCat;
    }

    public Long getLoggedCat() {
        return loggedCat;
    }

    //========================== file upload ==========================

    private void insureFilesTransactionCreated() throws DataBaseException, SharedApplicationException {
        if (filesTransaction == null) {
            FileManager fileManager = FileManager.getInstance();
            filesTransaction = new TransactionDTO();
            filesTransaction.setModule(fileManager.getModule());
            filesTransaction.setCategory("doc");
            filesTransaction = fileManager.addTransaction(filesTransaction);
        }
    }

    public FileDTO uploadFile() throws DataBaseException, SharedApplicationException, IOException {
        insureFilesTransactionCreated();
        FileDTO file = new FileDTO();
        file.setTransaction(filesTransaction);
        file.setName(uploadedFile.getName());
        file.setSize(uploadedFile.getSize());
        file.setContentType(uploadedFile.getContentType());
        FileManager fileManager = FileManager.getInstance();
        //previewFileDisabled = true;
        return fileManager.uploadFile(file, uploadedFile.getInputStream());
    }

   /**public void addAttachment() {

        try {
            if (uploadedFile != null)
                file = uploadFile();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }**/
   

    public boolean validate() {
        if (uploadedFile != null) {

            try {
                uploadedFile.getInputStream();
            } catch (IOException e) {
                uploadingError = true;
                return false;
            }
        }
        return true;
    }

    public void setFakeImageString(String fakeImageString) {
        this.fakeImageString = fakeImageString;
    }

    public String getFakeImageString() {
        return fakeImageString;
    }

    public void setRelativeFileName(String relativeFileName) {
        this.relativeFileName = relativeFileName;
    }

    public String getRelativeFileName() {
        return relativeFileName;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setFilesTransaction(TransactionDTO filesTransaction) {
        this.filesTransaction = filesTransaction;
    }

    public TransactionDTO getFilesTransaction() {
        return filesTransaction;
    }

    public void setFile(FileDTO file) {
        this.file = file;
    }

    public FileDTO getFile() {
        return file;
    }

    public void setUploadingError(boolean uploadingError) {
        this.uploadingError = uploadingError;
    }

    public boolean isUploadingError() {
        return uploadingError;
    }

    public void setInvalidImageType(boolean invalidImageType) {
        this.invalidImageType = invalidImageType;
    }

    public boolean isInvalidImageType() {
        return invalidImageType;
    }

    public void setCurrentExtension(String currentExtension) {
        this.currentExtension = currentExtension;
    }

    public String getCurrentExtension() {
        return currentExtension;
    }

    public void setFileFullPath(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }

    public String getFileFullPath() {
        return fileFullPath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setSelectedImagePath(String selectedImagePath) {
        this.selectedImagePath = selectedImagePath;
    }

    public String getSelectedImagePath() {
        return selectedImagePath;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantName() {
        try {
            applicantName = ((ISupProblemsClient)getClient()).getUserName(CSCSecurityInfoHelper.getUserCode()) ;
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        return applicantName;
    }

    public void setTreedivBean(TreeDivBean treedivBean) {
        this.treedivBean = treedivBean;
    }

    public TreeDivBean getTreedivBean() {
        return treedivBean;
    }

    public void setProblemSourcesList(List problemSourcesList) {
        this.problemSourcesList = problemSourcesList;
    }

    public List getProblemSourcesList() {
        try {
            if (problemSourcesList == null) {
                ISupProblemSourcesClient supProblemSourcesClient = SupClientFactory.getSupProblemSourcesClient();
                problemSourcesList = supProblemSourcesClient.getAllActive();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return problemSourcesList;
    }

    public void setSelectedProblemSourceCode(Long selectedProblemSourceCode) {
        this.selectedProblemSourceCode = selectedProblemSourceCode;
    }

    public Long getSelectedProblemSourceCode() {
        return selectedProblemSourceCode;
    }
    public void setAttachmentList(List<IBasicDTO> attachmentList) {
        this.attachmentList = attachmentList;
    }
    
    public List<IBasicDTO> getAttachmentList() {
        return attachmentList;
    }


    public void setCoordList(List coordList) {
         this.coordList = coordList;
     }

     public List getCoordList() {
         return coordList;
     }

     public void setSelectedCoordCode(String selectedCoordCode) {
         this.selectedCoordCode = selectedCoordCode;
     }

     public String getSelectedCoordCode() {
         return selectedCoordCode;
     }


     public void  changeCoord(ActionEvent ae){
          String[] crdCode = selectedCoordCode.split(Pattern.quote("*"));
          ISupCoordinatorsEntityKey enKey = new SupCoordinatorsEntityKey(crdCode[0] , Long.valueOf(crdCode[1]));
         ISupCoordinatorsClient crdClient = SupClientFactory.getSupCoordinatorsClient();


         try {
             coordinatorDTO = (ISupCoordinatorsDTO)crdClient.getById(enKey);
         } catch (DataBaseException e) {
         } catch (SharedApplicationException e) {
         }
     }
     
    public void addAttachment() {
    
        //AttachmentsListBean supAttachmentsListBean=(AttachmentsListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{appAttachmentsListBean}").getValue(FacesContext.getCurrentInstance());
        
        String backNavCase = "addproblrmNAV";
        String backMethod = BEAN_NAME + ".back";
        Map savedDataMap = buildSavedDataObj();
        attachmentsListBean.setSavedDataObj(savedDataMap);
        attachmentsListBean.setBackMethod(backMethod);
        attachmentsListBean.setBackNavCase(backNavCase);  
        attachmentsListBean.setMoveSerial(1L);
        attachmentsListBean.setAtctypeCode(2L);   
        if (getPageMode() == PAGE_MODE_EDIT) {
            ISupProblemsDTO dto = (ISupProblemsDTO)getPageDTO();
            attachmentsListBean.setProblemCode(((ISupProblemsEntityKey)dto.getCode()).getProblemCode());
        }
        attachmentsListBean.setDisableSelectedDocType(true);
        attachmentsListBean.setModuleName("sup"); 
        attachmentsListBean.setSaveInDB(false);
        attachmentsListBean.addDocAttachment(attachmentList);
       // if(isDiwan)
       //     supAttachmentsListBean.setForShowAttachmentOnly(true);
      //  else
            attachmentsListBean.setForShowAttachmentOnly(false);
       /// setJavaScriptCaller("openPopupWindow('http://127.0.0.1:7101/sup/app/jsps/appissues/supattachments/supattachmentslist.jsf?problemCode="+((ISupProblemsEntityKey)(ISupProblemsDTO)getPageDTO().getCode()).getProblemCode()+"', 950,600, (screen.availWidth - 950)/2 , (screen.availHeight - 500)/2);");

    }
    public Map buildSavedDataObj() {
        Map<String, Object> savedDataObj = new HashMap<String, Object>();
        /*try {
            savedDataObj.put("myTableData", getMyTableData());
        } catch (DataBaseException e) {
        }
        savedDataObj.put("highlightedDTOS", getHighlightedDTOS());
        savedDataObj.put("searchMode", isSearchMode());
        savedDataObj.put("selectedDTOS", getSelectedDTOS());
        savedDataObj.put("searchDTO", getSearchDTO());
        savedDataObj.put("sortAscending", isSortAscending());        
        savedDataObj.put("selectedRadio", getSelectedRadio());   */     
        savedDataObj.put("isDiwan", false);
        savedDataObj.put("attachmentList", attachmentList);
        savedDataObj.put("savedDataObj", getSavedDataObj());
        
        savedDataObj.put("problemImportanceCode", problemImportanceCode);
        savedDataObj.put("problemType", problemType);
        savedDataObj.put("machineSearchValue", machineSearchValue);
        savedDataObj.put("machineSearchMode", machineSearchMode);
        savedDataObj.put("machineSearchType", machineSearchType);
        savedDataObj.put("selectedMachiesDTOS", selectedMachiesDTOS);
        savedDataObj.put("problemCatCode", problemCatCode);
        savedDataObj.put("selectedMachineCode", selectedMachineCode);
        savedDataObj.put("wrongMachineSerial", wrongMachineSerial);
        savedDataObj.put("selectedMachineDesc", selectedMachineDesc);
        savedDataObj.put("moduleSearchType", moduleSearchType);
        savedDataObj.put("moduleSearchValue", moduleSearchValue);
        savedDataObj.put("moduleSearchMode", moduleSearchMode);
        savedDataObj.put("selectedModuleRadio",selectedModuleRadio);
        savedDataObj.put("selectedModulesDTOS", selectedModulesDTOS);
        savedDataObj.put("selectedModuleCode", selectedModuleCode);
        savedDataObj.put("selectedModuleDesc", selectedModuleDesc);
        savedDataObj.put("wrongModuleSerial", wrongModuleSerial);
        savedDataObj.put("coordinatorDTO", coordinatorDTO);
        savedDataObj.put("coordinatorName", coordinatorName);
        savedDataObj.put("SYSTEM_PROBLRM", SYSTEM_PROBLRM);
        savedDataObj.put("MACHINE_PROBLRM", MACHINE_PROBLRM);
        savedDataObj.put("NETWORK_PROBLRM", NETWORK_PROBLRM);
        savedDataObj.put("disableMinCat", disableMinCat);
        savedDataObj.put("pageMode", getPageMode());
        savedDataObj.put("pageDTO", getPageDTO());
        savedDataObj.put("applicantName", applicantName);
        savedDataObj.put("selectedProblemSourceCode", selectedProblemSourceCode);
        savedDataObj.put("editModuleCode", editModuleCode);  
    //        savedDataObj.put("ministryList", ministryList);
    //        savedDataObj.put("categoryList", categoryList);
        savedDataObj.put("coordList", coordList);  
        savedDataObj.put("selectedCoordCode", selectedCoordCode);  
        
        return savedDataObj;
    }
    public void restoreSavedDataObj(Object obj) {
        Map<String, Object> savedDataObj = (Map<String, Object>)obj;
       
        /*if (savedDataObj.get("myTableData") != null)
            setMyTableData((List)savedDataObj.get("myTableData"));
        if (savedDataObj.get("highlightedDTOS") != null)
            setHighlightedDTOS((List)savedDataObj.get("highlightedDTOS"));

        if (savedDataObj.get("searchMode") != null)
        setSearchMode((Boolean)savedDataObj.get("searchMode"));
        if (savedDataObj.get("selectedDTOS") != null)
        setSelectedDTOS((List)savedDataObj.get("selectedDTOS"));
        if (savedDataObj.get("searchDTO") != null)
        setSearchDTO((IBasicDTO)savedDataObj.get("searchDTO"));        
        if (savedDataObj.get("sortAscending") != null)
        setSortAscending((Boolean)savedDataObj.get("sortAscending")); 
        if (savedDataObj.get("selectedRadio") != null)
        setSelectedRadio((String)savedDataObj.get("selectedRadio"));  */    
        //if (savedDataObj.get("isDiwan") != null)
        setIsDiwan(false);        
        if (savedDataObj.get("savedDataObj") != null)
        setSavedDataObj(savedDataObj.get("savedDataObj"));  
        if (savedDataObj.get("attachmentList") != null)
        setAttachmentList((List)savedDataObj.get("attachmentList"));  
        
        
        problemImportanceCode = (Long)savedDataObj.get("problemImportanceCode");
        problemType = (String)savedDataObj.get("problemType");
        machineSearchValue = (String)savedDataObj.get("machineSearchValue");
        machineSearchMode=(Boolean)savedDataObj.get("machineSearchMode");
        machineSearchType = (String)savedDataObj.get("machineSearchType");
        selectedMachiesDTOS = (List<IBasicDTO>)savedDataObj.get("selectedMachiesDTOS");
        problemCatCode = (Long)savedDataObj.get("problemCatCode");
        selectedMachineCode = (Long)savedDataObj.get("selectedMachineCode");
        wrongMachineSerial=(Boolean)savedDataObj.get("wrongMachineSerial");
        selectedMachineDesc = (String)savedDataObj.get("selectedMachineDesc");
        moduleSearchType = (String)savedDataObj.get("moduleSearchType");
        moduleSearchValue = (String)savedDataObj.get("moduleSearchValue");
        moduleSearchMode=(Boolean)savedDataObj.get("moduleSearchMode");
        selectedModuleRadio = (String)savedDataObj.get("selectedModuleRadio");
        selectedModulesDTOS = (List<IBasicDTO>)savedDataObj.get("selectedModulesDTOS");
        selectedModuleCode = (Long)savedDataObj.get("selectedModuleCode");
        selectedModuleDesc = (String)savedDataObj.get("selectedModuleDesc");
        wrongModuleSerial=(Boolean)savedDataObj.get("wrongModuleSerial");
        coordinatorDTO = (ISupCoordinatorsDTO)savedDataObj.get("coordinatorDTO");
        coordinatorName = (String)savedDataObj.get("coordinatorName");
        SYSTEM_PROBLRM = (String)savedDataObj.get("SYSTEM_PROBLRM");
        MACHINE_PROBLRM = (String)savedDataObj.get("MACHINE_PROBLRM");
        NETWORK_PROBLRM = (String)savedDataObj.get("NETWORK_PROBLRM");
        disableMinCat=(Boolean)savedDataObj.get("disableMinCat");
        setPageMode((Integer)savedDataObj.get("pageMode"));
        setPageDTO((IBasicDTO)savedDataObj.get("pageDTO"));
        applicantName = (String)savedDataObj.get("applicantName");
        selectedProblemSourceCode = (Long)savedDataObj.get("selectedProblemSourceCode");
        editModuleCode = (Boolean)savedDataObj.get("editModuleCode");
    //        ministryList = (List)savedDataObj.get("ministryList");
    //        categoryList = (List)savedDataObj.get("categoryList");
        coordList = (List)savedDataObj.get("coordList");
        selectedCoordCode = (String)savedDataObj.get("selectedCoordCode");
    }


    public void setSavedDataObj(Object savedDataObj) {
        this.savedDataObj = savedDataObj;
    }

    public Object getSavedDataObj() {
        return savedDataObj;
    }

    public void setEditModuleCode(Boolean editModuleCode) {
        this.editModuleCode = editModuleCode;
    }

    public Boolean getEditModuleCode() {
        return editModuleCode;
    }

    public void setIsDiwan(Boolean isDiwan) {
        this.isDiwan = isDiwan;
    }

    public Boolean getIsDiwan() {
        return isDiwan;
    }

    public void setAttachmentsListBean(AttachmentsListBean attachmentsListBean) {
        this.attachmentsListBean = attachmentsListBean;
    }

    public AttachmentsListBean getAttachmentsListBean() {
        return attachmentsListBean;
    }
}
