package com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata.add.AddExperienceListBean;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.Helper;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;


public class WorkDataListBean extends TreeKwtWorkDataMany {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    protected static final Integer WIZARD_BAR_PAGE = 1;
    protected static final Integer NORMAL_PAGE = 2;
    private static final String BACK_BEAN_NAME = "workDataListBean";
    private Long civilId;
    private String citizinFullName;
    private IKwtCitizensResidentsDTO kwtCitizenDTO;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private int treeListSize = 0;
    String selectedStyleClass;
    Boolean fromTabs = false;
    String boxColorText;
    String panelGroupStyleClass;
    private boolean showBtn = true;
    private String navigationCase;
    private HashMap hmObjects = new HashMap();
    private String beanName;
    private String backAction;
    private boolean enableAdd = true;
    private boolean enableBack = true;
    private boolean saveInDB;
    private ArrayList<IKwtWorkDataTreeDTO> kwtWorkDataDTOList;
    private Integer pageType = 0;
    private String listPageinWizardBar;
    private String addExperIntegrationpage;
    private boolean viewOnly;
    public static final Long TRXTYPE_WORK_DATA_280 = Long.valueOf(280);
    public static final Long TRXTYPE_WORK_DATA_290 = Long.valueOf(290);
    public WorkDataListBean() {
        setClient(InfClientFactory.getKwtWorkDataClient());
        setSelectedPageDTO(InfDTOFactory.createKwtWorkDataTreeDTO());
        setPageDTO(InfDTOFactory.createKwtWorkDataTreeDTO());
        setDtoDetails(InfDTOFactory.createKwtWorkDataTreeDTO());
        setUsingTreePaging(true);
        setTreeListPagingRequestDTO(new PagingRequestDTO("workDataListBean", "getChildrenNodes"));
        setRootName("experiance");
        setBundle(ResourceBundle.getBundle("com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"));
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());

        setKeyIndex(0);
        if (integrationBean != null && integrationBean.getHmBaseBeanFrom().size() > 0) {

            if ((integrationBean.getHmBaseBeanFrom().get("workDataListBean")) != null) {
                if (((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")).getKwtWorkDataDTOList() !=
                    null) {
                    kwtWorkDataDTOList =
                            ((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")).getKwtWorkDataDTOList();

                }
                if (((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")).getCivilId() !=
                    null) {
                    civilId =
                            ((WorkDataListBean)integrationBean.getHmBaseBeanFrom().get("workDataListBean")).getCivilId();

                }
                if (((WorkDataListBean)(integrationBean.getHmBaseBeanFrom().get("workDataListBean"))).isViewOnly()) {
                    this.setShowBtn(false);
                    this.setCivilExist(true);
                    this.setEnableAdd(false);
                    this.setEnableBack(true);
                    this.setCitizinFullName(((WorkDataListBean)(integrationBean.getHmBaseBeanFrom().get("workDataListBean"))).getCitizinFullName());
                    this.setBackAction(((WorkDataListBean)(integrationBean.getHmBaseBeanFrom().get("workDataListBean"))).getBackAction());
                    this.setNavigationCase(((WorkDataListBean)(integrationBean.getHmBaseBeanFrom().get("workDataListBean"))).getNavigationCase());
                    this.setBeanName(((WorkDataListBean)(integrationBean.getHmBaseBeanFrom().get("workDataListBean"))).getBeanName());
                }
            }
            getHighlightedDTOS();

        }

    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowbar(true);
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowNavigation(false);

        return app;
    }

    public BesharaKwtWorkDataTree buildTree() throws DataBaseException {
        BesharaKwtWorkDataTree _treeNodeBase = null;
        try {
            if (isUsingTreePaging()) {
                if (getMyTableData() != null && getMyTableData().size() > 0) {
                    setTreeList(getMyTableData());
                } else {
                    if (civilId != null) {
                        setTreeList(InfClientFactory.getKwtWorkDataClient().getByCivilIdOrderByDate(civilId));
                    } else {
                        setTreeList(new ArrayList());
                    }
                }
            } else {
                setTreeList(Helper.buildTree((List<IBasicDTO>)getMyTableData()));
            }
            String treeLevel = "0";
            int freezRequest = 0;
            _treeNodeBase =
                    new BesharaKwtWorkDataTree("foo-folder", getBundle().getString(getRootName()), "0", "0", null,
                                               true, false, false, freezRequest, null);
            for (int i = 0; i < getTreeList().size(); i++) {
                treeLevel = "0";
                ITreeDTO item = (ITreeDTO)getTreeList().get(i);
                BesharaKwtWorkDataTree treeNode = null;
                freezRequest = 0;
                IKwtWorkDataTreeDTO _treeDTOElem = (IKwtWorkDataTreeDTO)item;
                treeLevel = "0:" + i;
                treeNode =
                        new BesharaKwtWorkDataTree("foo-folder", item.getName(), item.getCode().getKeys()[getKeyIndex()].toString(),
                                                   item.getCode().getKey().toString(), null, true,
                                                   item.isBooleanLeafFlag(), item.isBooleanLeafFlag(), freezRequest,
                                                   _treeDTOElem);

                if (treeNode != null) {
                    treeNode.setParent(_treeNodeBase);
                    _treeNodeBase.getChildren().add(treeNode);
                    parseTree(item.getChildrenList(), item, treeNode);
                }
            }
        } catch (Exception e) {
            setMyTableData(new ArrayList());
            e.printStackTrace();
            _treeNodeBase =
                    new BesharaKwtWorkDataTree("foo-folder", getBundle().getString(getRootName()), "0", "0", null,
                                               true, false, false, 0, null);
        }
        setTreeNodeBase(_treeNodeBase);
        if (getHtmlTree().getId() != null) {
            getHtmlTree().expandPath(new String[] { "0" });
        }
        return _treeNodeBase;
    }

    public void getAll() throws DataBaseException {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            if (civilId != null) {
                if (kwtWorkDataDTOList != null && kwtWorkDataDTOList.size() > 0) {
                    setMyTableData(InfClientFactory.getKwtWorkDataClient().getByCivilIdOrderByDate(civilId,
                                                                                                   kwtWorkDataDTOList));

                } else {
                    setMyTableData(InfClientFactory.getKwtWorkDataClient().getByCivilIdOrderByDate(civilId));
                }

            } else {
                setMyTableData(new ArrayList());
            }

            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();
        } catch (NoResultException e) {
            e.printStackTrace();
        } catch (SharedApplicationException f) {
            f.printStackTrace();
        } catch (DataBaseException db) {
            db.printStackTrace();
            shared_util.setErrMsgValue(db.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createKwtWorkDataTreeDTO());
    }

    public PagingResponseDTO getChildrenNodes(PagingRequestDTO pagingRequest) throws DataBaseException,
                                                                                     SharedApplicationException {
        BesharaKwtWorkDataTree node = (BesharaKwtWorkDataTree)pagingRequest.getParams()[0];
        List resultList = null;
        try {
            IKwtWorkDataTreeDTO kwtWorkDataDTO = node.getKwtWorkDataTreeDTO();
            if (node.getKwtWorkDataTreeDTO().getTreeLevel() == 1) {
                resultList =
                        InfClientFactory.getKwtWorkDataClient().getJobGroupping(Long.valueOf(kwtWorkDataDTO.getKwtCitizensResidentsDTO().getCode().getKey()),
                                                                                Long.valueOf(kwtWorkDataDTO.getMinistriesDTO().getCode().getKey()),
                                                                                kwtWorkDataDTO.getFromDate(),
                                                                                kwtWorkDataDTO.getUntilDate(),
                                                                                getKwtWorkDataDTOList());
            } else {
                String jobCode =
                    kwtWorkDataDTO.getJobsDTO() != null ? kwtWorkDataDTO.getJobsDTO().getCode().getKey() : kwtWorkDataDTO.getExtraJob();
                resultList =
                        InfClientFactory.getKwtWorkDataClient().getWorkCenterGroupping(Long.valueOf(kwtWorkDataDTO.getKwtCitizensResidentsDTO().getCode().getKey()),
                                                                                       Long.valueOf(kwtWorkDataDTO.getMinistriesDTO().getCode().getKey()),
                                                                                       jobCode,
                                                                                       kwtWorkDataDTO.getFromDate(),
                                                                                       kwtWorkDataDTO.getUntilDate(),
                                                                                       getKwtWorkDataDTOList());
            }

            for (int i = 0; i < resultList.size(); i++) {
                ITreeDTO item = (ITreeDTO)resultList.get(i);
                BesharaKwtWorkDataTree treeNode = null;
                IKwtWorkDataTreeDTO _treeDTOElem = (IKwtWorkDataTreeDTO)item;
                treeNode =
                        new BesharaKwtWorkDataTree("foo-folder", item.getName(), item.getCode().getKeys()[getKeyIndex()].toString(),
                                                   item.getCode().getKey().toString(), null, true,
                                                   item.isBooleanLeafFlag(), item.isBooleanLeafFlag(), 0,
                                                   _treeDTOElem);
                if (treeNode != null) {
                    treeNode.setParent(node);
                    node.getChildren().add(treeNode);
                }
            }

            resultList = new ArrayList();
        } catch (NoResultException e) {
            resultList = new ArrayList();
            e.printStackTrace();
        }
        getMyTableData().addAll(resultList);
        return new PagingResponseDTO(resultList);
    }

    public void init() {
        if ((civilId != null && !civilId.equals("") && civilExist == false)) {
            try {

                InfClientFactory.getKwtCitizensResidentsClient().checkCivilIdExist(civilId);
                kwtCitizenDTO =
                        (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenCodeName(civilId);
            } catch (Throwable e) {
                validCivilId = false;
                civilExist = false;
                civilId = null;
                setMyTableData(new ArrayList());
                try {
                    buildTree();
                } catch (DataBaseException f) {
                }
                e.printStackTrace();
                return;

            }

            if (kwtCitizenDTO != null) {
                setCitizinFullName(kwtCitizenDTO.getFullName());
                try {
                    buildTree();
                    getHtmlTree().expandAll();
                } catch (DataBaseException e) {
                    e.printStackTrace();
                }
                validCivilId = true;
                civilExist = true;
            } else {
                setCitizinFullName("");
                validCivilId = false;
                civilExist = true;
            }
        } else {
            reSetData();
        }
    }

    public void reSetData() {
        civilId = null;
        validCivilId = true;
        civilExist = false;
        setCivilId(null);
        setCitizinFullName(null);
        setKwtCitizenDTO(null);
        setMyTableData(new ArrayList());
        try {
            buildTree();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setCitizinFullName(String citizinFullName) {
        this.citizinFullName = citizinFullName;
    }

    public String getCitizinFullName() {
        return citizinFullName;
    }

    public void setKwtCitizenDTO(IKwtCitizensResidentsDTO kwtCitizenDTO) {
        this.kwtCitizenDTO = kwtCitizenDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizenDTO() {
        return kwtCitizenDTO;
    }


    public void setValidCivilId(boolean validCivilId) {
        this.validCivilId = validCivilId;
    }

    public boolean isValidCivilId() {
        return validCivilId;
    }

    public void setCivilExist(boolean civilExist) {
        this.civilExist = civilExist;
    }

    public boolean isCivilExist() {
        return civilExist;
    }

    public void setSelectedStyleClass(String selectedStyleClass) {
        this.selectedStyleClass = selectedStyleClass;
    }

    public String getSelectedStyleClass() {
        BesharaKwtWorkDataTree node = (BesharaKwtWorkDataTree)getHtmlTree().getNode();
        String style = "";
        if (node.getKwtWorkDataTreeDTO() != null) {
            if (node.getKwtWorkDataTreeDTO().getTreeLevel() == 1) {
                style = "TreeLevel1";
            } else if (node.getKwtWorkDataTreeDTO().getTreeLevel() == 2) {
                style = "TreeLevel2";
            } else if (node.getKwtWorkDataTreeDTO().getTreeLevel() == 3) {
                style = "TreeLevel3";
            }
            if (getFromTabs()) {
                style = style + " tabs-int";
            }
            return style;
        }
        return "treepage-link";
    }

    public String getPanelGroupStyleClass(String navigationCase) {
        return navigationCase;
    }

    public String back() {
        if ((getBeanName() != null && !getBeanName().equals("")) &&
            (getBackAction() != null && !getBackAction().equals(""))) {
            return (String)JSFHelper.callMethod(getBeanName() + "." + getBackAction());
        }
        return navigationCase;
    }


    public void setTreeListSize(int treeListSize) {
        this.treeListSize = treeListSize;
    }

    public int getTreeListSize() {
        return getTreeList().size();
    }

    public void setFromTabs(Boolean fromTabs) {
        this.fromTabs = fromTabs;
    }

    public Boolean getFromTabs() {
        return fromTabs;
    }

    public void setPanelGroupStyleClass(String panelGroupStyleClass) {
        this.panelGroupStyleClass = panelGroupStyleClass;
    }

    public String getPanelGroupStyleClass() {
        if (panelGroupStyleClass == null || panelGroupStyleClass.equals("")) {
            return "tree-area-tabs-With-1-row-filter";
        } else {
            return panelGroupStyleClass;
        }
    }

    public void setShowBtn(boolean showBtn) {
        this.showBtn = showBtn;
    }

    public boolean isShowBtn() {
        return showBtn;
    }

    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }

    public String getNavigationCase() {
        return navigationCase;
    }

    public void setHmObjects(HashMap hmObjects) {
        this.hmObjects = hmObjects;
    }

    public HashMap getHmObjects() {
        return hmObjects;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBackAction(String backAction) {
        this.backAction = backAction;
    }

    public String getBackAction() {
        return backAction;
    }

    public String goAdd() {
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());


        AddExperienceListBean addExperienceListBean = AddExperienceListBean.getInstance();
        addExperienceListBean.setRcivilId(getCivilId());
        addExperienceListBean.setTrxCodList(((List)integrationBean.getHmBaseBeanFrom().get("trxCodList")));
        addExperienceListBean.getTrxCodList().add(TRXTYPE_WORK_DATA_280);
        addExperienceListBean.getTrxCodList().add(TRXTYPE_WORK_DATA_290);
        addExperienceListBean.setIncludeTrx((Boolean)integrationBean.getHmBaseBeanFrom().get("includeTrx"));
        addExperienceListBean.setTrxTypesCode((Long)integrationBean.getHmBaseBeanFrom().get("trxTypesCode"));
        addExperienceListBean.setWorkDataListBean(this);

        if (pageType.equals(WIZARD_BAR_PAGE)) {
            integrationBean.getHmBaseBeanFrom().put("backPage", listPageinWizardBar);
            integrationBean.setNavgationCaseFrom(listPageinWizardBar);
            //            integrationBean.setBeanNameFrom("workDataListBean");
            //            integrationBean.setActionFrom("retFromIntigration");
            return getAddExperIntegrationpage();
        } else {
            integrationBean.getHmBaseBeanFrom().put("backPage", "workDataList");
            return "addExperinceIntegrationPage";
        }

    }

    public void setEnableAdd(boolean enableAdd) {
        this.enableAdd = enableAdd;
    }

    public boolean isEnableAdd() {
        return enableAdd;
    }

    public void setEnableBack(boolean enableBack) {
        this.enableBack = enableBack;
    }

    public boolean isEnableBack() {
        return enableBack;
    }

    public void setSaveInDB(boolean saveInDB) {
        this.saveInDB = saveInDB;
    }

    public boolean isSaveInDB() {
        return saveInDB;
    }

    public void setKwtWorkDataDTOList(ArrayList<IKwtWorkDataTreeDTO> kwtWorkDataDTOList) {
        this.kwtWorkDataDTOList = kwtWorkDataDTOList;
    }

    public ArrayList<IKwtWorkDataTreeDTO> getKwtWorkDataDTOList() {
        return kwtWorkDataDTOList;
    }

    public void setListPageinWizardBar(String listPageinWizardBar) {
        this.listPageinWizardBar = listPageinWizardBar;
    }

    public String getListPageinWizardBar() {
        return listPageinWizardBar;
    }

    public void setAddExperIntegrationpage(String addExperIntegrationpage) {
        this.addExperIntegrationpage = addExperIntegrationpage;
    }

    public String getAddExperIntegrationpage() {
        return addExperIntegrationpage;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }

    public Integer getPageType() {
        return pageType;
    }

    public static WorkDataListBean getInstance() {
        return (WorkDataListBean)JSFHelper.getValue(BACK_BEAN_NAME);
    }


    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }
}
