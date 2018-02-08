package com.beshara.jsfbase.csc.backingbean;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.locking.LockingHelper;
import com.beshara.base.locking.dto.ILockableItem;
import com.beshara.base.locking.dto.ILockingRequestDTO;
import com.beshara.base.paging2.IPagingPositionDTO;
import com.beshara.base.paging2.IPagingResponseDTO;
import com.beshara.base.paging2.IPagingSortPropertyDTO;
import com.beshara.base.paging2.impl.PagingPositionDTO;
import com.beshara.base.paging2.impl.PagingSortPropertyDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.ManagedConstantsBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.SharedUtilBeanPortal;
import com.beshara.jsfbase.csc.util.tableheader.ModuleObject;
import com.beshara.jsfbase.csc.util.tableheader.TableHeaderBean;
import com.beshara.jsfbase.csc.util.wizardbar.WizardBar;

import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajax4jsf.ajax.html.HtmlAjaxFunction;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.component.html.ext.HtmlInputHidden;
import org.apache.myfaces.custom.column.HtmlSimpleColumn;
import org.apache.myfaces.custom.datascroller.HtmlDataScroller;
import org.apache.myfaces.custom.datascroller.ScrollerActionEvent;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import weblogic.ejb20.utils.OrderedSet;


//import com.beshara.csc.presentation.backingbean.AppMainLayoutPortal;


/**
 * this parent of all beans
 * this bean responsible for handling main operatin and any methods using fogoToListr global in application
 * must be if creat any bean and not use any child (any base benas) the your bean extends BaseBean
 *
 */
public class BaseBean implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    //TODO locking code
    public static final String DEF_LOCKING_ACTION = "edit";

    private String virtualConstValue = String.valueOf(ISystemConstant.VIRTUAL_VALUE.longValue());
    private Long virtualLongValue = ISystemConstant.VIRTUAL_VALUE.longValue();
    TableHeaderBean tableHeaderBean;
    private AppMainLayout currentApplictionMainLayout;
    transient HtmlForm frm; //by sherif.omar each derived class will add the getter and setter for this attrbuite
    String allIndexes = "";
    String allIndexesButton = "";
    boolean sortAscending;
    String fullColumnName;
    List<ModuleObject> objects = new ArrayList<ModuleObject>();

    String[] casesArray;
    private String casesArrayConcatinated = "";
    private String selectedRadio = "";
    String[] casesArrayButton;
    private String casesArrayConcatinatedButon = "";
    private boolean filterMode = false;
    private Object searchEntityObj;
    private boolean searchMode;
    private boolean alreadyDeleted;
    private int selectedListSize;
    private boolean selected;
    private String addDivTitleBindingString = null;
    private String editDivTitleBindingString = null;
    private String delAlertTitleBindingString = null;
    private String delConfirmTitleBindingString = null;
    private String addDivTitle;
    private String editDivTitle;
    private String delAlertTitle;
    private String delConfirmTitle;
    private IEntityKey entityKey;
    private transient IBasicClient client;
    private IBasicDTO pageDTO;
    private IBasicDTO filterDTO = null;
    private IBasicDTO selectedPageDTO = new BasicDTO();
    private List<IBasicDTO> selectedDTOS = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> highlightedDTOS = new ArrayList<IBasicDTO>();
    private transient HtmlDataTable myDataTable = new HtmlDataTable();
    private List myTableData = null;
    private String sortColumn = "";
    private boolean ascending = true;
    private Integer searchType = new Integer(0); //to hold search type selected by user code(0),name(1)
    private String searchQuery = ""; //hold the search data entered by user
    private boolean showEdit;
    private List<IResultDTO> deleteStatusDTOS = new ArrayList<IResultDTO>();
    private String javaScriptCaller;
    private boolean showErrorMsg;
    private String errorMsg;
    private boolean success;
    private boolean checkAllFlag;
    transient HtmlInputHidden reloadField = new HtmlInputHidden();

    /* Attributes for divs css added by ahmed abd el-fatah 06/05/2008*/

    /*
     * General Divs
     */
    private String divMsg = "divMsg";
    private String iframeBlock = "divIframe";
    private String divSearch = "divSearch";
    private String delAlert = "delDivsStyle";
    private String delConfirm = "delDivsStyle";
    private String content1Div = "divContent1Dynamic"; //if fixed use divContent1Fixed
    private String contentDiv = "";
    private String divMainContent = "divMainContent";
    private String divMainContentMany = "divMainContentMany";


    /*
     * Integration Divs
     * by Islam Omar 01-07-2014
     */
    private String integrationDiv1 = "divSearch";
    private String integrationDiv2 = "divSearch";


    /*
     * Many to Many Divs
     */
    private String divMsgMany = "divMsg";
    private String divSearchMany = "divSearch";
    private String divAddMany = "divSearch";
    private String divDelMany = "divSearch";
    private String divConfirmMany = "divSearch";

    /*
     * Lookup Divs
     */
    private String lookupAddDiv = "lookupAddDivClass";
    private String lookupEditDiv = "lookupEditDivClass";

    /*
     * Master Details Divs
     */
    private String masterDetailDiv = "divSearch";

    /*
     * Tree Divs
     */
    private String divTreeAdd = "divSearch";
    private String divTreeEdit = "divSearch";
    private String delAlertTree = "divSearch";
    private String divTreeDetails = "divDetails";


    /*
     * REG Module
     */
    private String customDiv1 = "divSearch";
    private String customDiv2 = "divSearch";
    private String customDiv3 = "divSearch";

    /*
     * LovDiv
     * */
    private LOVBaseBean lovBaseBean;
    /*
     * used to search in Trees (showed in divs)
     * added by ali agamy
     * */
    private LOVBaseBean searchTreeDivBean;
    private String lovDiv = "divSheardStyle1";

    /*
     * Search Validation Type wheter is the default validation or otherwise.
     * if it equals 0 --- defaultSearch
     * if it equals 1 --- code in search box will allow characters.
     */
    private String seachValidationType = "0";

    /* this attribute will control the max length of name attribute in the add/edit div  default value  180
   */
    private int nameMaxLength = 180;

    private int calendarTextLength = 10;

    private int currentSortingRowIndex; //Created to be used for the sorting using EL

    private BaseBean empListOfValues;


    // ************************* Start Paging **********************************

    private static final String FIRST = "first";
    private static final String NEXT = "next";
    private static final String FASTFORWARD = "fastf";

    private static final String PREVIOUS = "previous";
    private static final String LAST = "last";
    private static final String FASTREWIND = "fastr";

    private int currentPageIndex = 1;
    private int oldPageIndex = 0;

    private boolean saveSortingState; // by Ashraf Gaber, 08-02-10

    private boolean sorting;
    private boolean usingPaging;
    private boolean usingBsnPaging;
    private boolean updateMyPagedDataModel;
    private boolean repositionTable;
    private boolean sortedTable;
    private boolean resettedPageIndex;

    private PagingRequestDTO pagingRequestDTO;

    private PagingBean pagingBean;

    private String bsnSortingColumnName;

    // *************************** End Paging **********************************

    private String titlePage;

    // added by Nora to enable single selection
    private boolean singleSelection = false;

    private int pageMode = 0; //0 listing 1 add 2 edit   used to render the client validators
    // to handle the error messages in the divs

    String empLovDivTitleKey;
    String lovDivTitleKey;
    String masterDeailTitleKey;
    String customDiv1TitleKey;
    String customDiv2TitleKey;
    String customDiv3TitleKey;
    String integrationDiv1TitleKey;
    String integrationDiv2TitleKey;
    String treeDivTitleKey;
    String editDivTitleKey;
    String addDivTitleKey;
    String searchDivTitleKey = "srch_popup_title";
    String addTreeDivTitleKey;
    String editTreeDivTitleKey;
    private boolean editDivTitleKeyFlage = false;
    private boolean addDivTitleKeyFlage = false;
    private boolean searchDivTitleKeyFlage = false;
    private boolean addTreeDivTitleKeyFlage = false;
    private boolean editTreeDivTitleKeyFlage = false;
    String deleteDivTitleKey;
    //abo selectedRowIndex
    private int indexOfSelectedDataInDataTable;

    private List dataTableSearchResult;
    private int searchResultSize;
    private int currentSelectedSearchIndex = 0;

    //Attributes for Portal
    private Boolean usingPortal;

    /**
     * @Desc Moving Up/Down of Data Table records by arrows in a column of it.
     * @Author MLotfy
     **/
    private String dataTableDTOOrderMethodName;
    private Class[] dataTableDTOOrderMethodPramTypes;
    private Object[] dataTableDTOOrderMethodArgslist = new Object[1];
    private boolean[] indexArray;
    private String treeDivStyle = "divSheardStyle1";
    private boolean subtitle = false;

    //used in row dbl click action handling
    private int clickedRowIndex;
    private String selectionComponentType; //0:checkbox, 1:radio

    //TODO locking code

    /**
     * save the last locking action to be used with insureLocked/unlock methods
     */
    private String lastLockingAction;

    /**
     * this value will be setted to 1 only if the locking was failed
     * and won't be presisted between submits
     */
    private transient String lockingFailed = "";

    /*Message div solution*/
    private Long msgDivTimeoutPeriod = 3000L; //Message visibility Period in millisecond

    private boolean addWithoutSelect = false;
    private int dataTableFirst;

    public Long msgDivHeight = 0L;

    // new divs add by A.Agamy
    private String conditionActivationIntgDivStyleClass = "divSearch";
    private String conditionActivationIntgDivTitleKey;

    private String retroactiveSettDetailsDivStyleClass = "divSearch";
    private String retroactiveSettDetailsDivTitleKey;

    // progress bar
    // msg value when we need to pass dynamic msg to progrees bar.
    private String progressMsgVal;
    /*
    * @author TechnicalTeam[D.AbdeLHady]
    * @since 04/08/2015
    * @param Pagging
    * @return
    * @throws DAOException
    * @Note used enhance Pagging code
    */
    private boolean askForSorting;
    private com.beshara.base.paging2.IPagingResponseDTO pagingResponseDTO;
    // used in case of custom bussiness paging and need to be set with true in add function
    private boolean recordAdded;
    // developer must set it in search function , in default usage of bussiness paging
    private IBasicDTO searchDTO = null;

    public BaseBean() {


        getSharedUtil().init();
    }

    public BaseBean(IBasicDTO _pageDTO) {
        this.pageDTO = _pageDTO;

    }


    /*---------------------------------Setter And Getter Methods----------------------------------*/

    public void setPageDTO(IBasicDTO pageDTO) {
        this.pageDTO = pageDTO;
    }

    public IBasicDTO getPageDTO() {
        return pageDTO;
    }

    public void setMyDataTable(HtmlDataTable myDataTable) {
        this.myDataTable = myDataTable;
    }

    public HtmlDataTable getMyDataTable() {
        return myDataTable;
    }

    public void setMyTableData(List myTableData) {
        this.myTableData = myTableData;
    }

    public void setFrm(HtmlForm frm) {

        this.frm = frm;
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        return app;
    }

    public AppMainLayoutPortal appMainLayoutPortalBuilder() {
        AppMainLayoutPortal app = new AppMainLayoutPortal();
        app.showLookupListPage();
        return app;
    }

    public HtmlForm getFrm() {

        if (getUsingPortal()) {
            if (this.appMainLayoutPortalBuilder() != null)
                setCurrentApplictionMainLayoutPortal(this.appMainLayoutPortalBuilder());
        } else {
            if (this.appMainLayoutBuilder() != null)
                setCurrentApplictionMainLayout(this.appMainLayoutBuilder());
        }
        this.initiateBeanOnce();
        return frm;
    }

    public List getMyTableData() throws DataBaseException {

        if (usingPaging) {

            //            System.out.println("getMyTableData With Paging");

            try {

                return (List)getPagingBean().getMyPagedDataModel().getWrappedData();

            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList();

            }

        } else {

            //            System.out.println("getMyTableData Without Paging");

            if (myTableData == null) {
                this.getAll();
            }

            if (myTableData == null) {
                myTableData = new ArrayList();
            }
            if (saveSortingState && !sortedTable) {
                sort(myTableData, sortColumn, sortAscending, true);
            }

            return myTableData;

        }

    }


    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery.trim();
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSelectedDTOS(List<IBasicDTO> selectedDTOS) {
        this.selectedDTOS = selectedDTOS;
    }

    public List<IBasicDTO> getSelectedDTOS() {
        return selectedDTOS;
    }

    public void setClient(IBasicClient client) {
        this.client = client;
    }

    public IBasicClient getClient() {
        return client;
    }

    public void setEntityKey(IEntityKey entityKey) {
        this.entityKey = entityKey;
    }

    public IEntityKey getEntityKey() {
        return entityKey;
    }
    /*---------------------------------Business Methods----------------------------------*/

    //Paging

    public void getAll() throws DataBaseException {

        if (usingPaging) {

            setUpdateMyPagedDataModel(true);
            pagingRequestDTO = new PagingRequestDTO("getAllWithPaging");

        } else {

            setMyTableData(this.getTotalList());

            this.reinitializeSort();

            if (selectedDTOS != null) {
                selectedDTOS.clear();
            }

            if (highlightedDTOS != null) {
                highlightedDTOS.clear();
            }

        }

    }


    /*
    * @author TechnicalTeam[D.AbdeLHady]
    * @since 04/08/2015
    * @param Pagging
    * @return
    * @Note used in getAll in case bussiness paging applied
    */

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO pagingRequestDTO) {
        IPagingResponseDTO bsnResponseDTO = getAllResultsWithPaging();
        if (bsnResponseDTO == null) {
            return new PagingResponseDTO(getTotalList());
        } else {
            return getPagingResponseDTO(bsnResponseDTO);
        }
    }

    /*
    * @author TechnicalTeam[D.AbdeLHady]
    * @since 04/08/2015
    * @param Pagging
    * @return
    * @Note return data filtered with paging, re-index to page which contain newly added record
    */

    private com.beshara.base.paging2.IPagingResponseDTO getAllResultsWithPaging() {
        com.beshara.base.paging2.IPagingRequestDTO bsnPagingRequestDTO = initiatePagingRequestDTO();
        int pageIndex = getCurrentPageIndex();
        if (isRecordAdded()) {
            float ceil = (float)pageIndex / (float)getSharedUtil().getNoOfTableRows();
            int currentPage = (int)Math.ceil(ceil);
            bsnPagingRequestDTO.getPosition().setNumber(currentPage);
            if (currentPage == 0) {
                setCurrentPageIndex(1);
            } else {
                setCurrentPageIndex(currentPage);
            }
            setRecordAdded(false);
        }
        try {
            if (getFilterDTO() == null) {
                pagingResponseDTO = getClient().getAllWithPaging(bsnPagingRequestDTO);
            } else {
                pagingResponseDTO = getClient().getAllWithPaging(bsnPagingRequestDTO, getFilterDTO());
            }
        } catch (NoResultException e) {
            pagingResponseDTO = new com.beshara.base.paging2.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            //            getSharedUtil().setErrMsgValue(e.getMessage());
            pagingResponseDTO = null;
            e.printStackTrace();
        } catch (DataBaseException e) {
            //            getSharedUtil().setErrMsgValue(e.getMessage());
            pagingResponseDTO = null;
            e.printStackTrace();
        }
        return pagingResponseDTO;
    }

    /**
     * @author TechnicalTeam[D.AbdeLHady]
     * @since 04/08/2015
     * @param bsnResponseDTO
     * @return
     * @Note used to return saved data originally sent by developer after paging execution
     */
    private PagingResponseDTO getPagingResponseDTO(IPagingResponseDTO bsnResponseDTO) {
        PagingResponseDTO pagingResponseDTO = null;
        if (bsnResponseDTO.getResult() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            List parameters = new ArrayList();
            parameters.add(bsnResponseDTO.getTotalRowsCount().getValue());
            Object[] params = new Object[] { parameters };
            getPagingRequestDTO().setParams(params);
            pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getResult());
            pagingResponseDTO.setTotalListSize(bsnResponseDTO.getTotalRowsCount().getValue());
            if (getCurrentPageIndex() == 1) {
                getPagingRequestDTO().setParams(new Object[] { bsnResponseDTO.getTotalRowsCount().getValue() });
            }
        }
        return pagingResponseDTO;
    }

    /**
     *  @author TechnicalTeam[D.AbdeLHady]
     *  @since 04/08/2015
     *  @Note used to return saved data originally sent by developer after paging execution
     * @return
     */
    private com.beshara.base.paging2.IPagingRequestDTO initiatePagingRequestDTO() {
        com.beshara.base.paging2.IPagingRequestDTO bsnPagingRequestDTO =
            new com.beshara.base.paging2.impl.PagingRequestDTO();
        int pageIndex = getCurrentPageIndex();
        IPagingPositionDTO pagingPositionDTO = new PagingPositionDTO(getSharedUtil().getNoOfTableRows(), pageIndex);
        bsnPagingRequestDTO.setPosition(pagingPositionDTO);
        if (isSorting() || isAskForSorting()) {
            setAskForSorting(true);
            IPagingSortPropertyDTO PagingSortPropertyDTO =
                new PagingSortPropertyDTO(getBsnSortingColumnName(), isSortAscending());
            bsnPagingRequestDTO.setSortProperties(new ArrayList());
            bsnPagingRequestDTO.getSortProperties().add(PagingSortPropertyDTO);
        }
        return bsnPagingRequestDTO;
    }


    public void preAdd() {
        //        System.out.println("Calling preAdd()...");
        setSuccess(false);
        setShowErrorMsg(false);
        //setSearchMode(false);
    }

    //Paging
    /*
    * @author TechnicalTeam[D.AbdeLHady]
    * @since 04/08/2015
    * @param
    * @return
    * @throws DataBaseException
    * @Note updated to return to correct current page after add
    */

    public void add() throws DataBaseException {
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            pageDTO = executeAdd();
            if (usingPaging) {
                setRecordAdded(true);
                generatePagingRequestDTO((String)getPageDTO().getCode().getKey());
                Long rowNum = 0L;
                try {
                    rowNum = getClient().getRowNum(getPageDTO());
                } catch (UnsupportedOperationException e) {
                    rowNum = 0L;
                }catch (DataBaseException e) {
                    rowNum = 0L;
                }
                setCurrentPageIndex(rowNum.intValue());
            } else {
                getAll();
                getPageIndex((String)getPageDTO().getCode().getKey());
            }
            if (highlightedDTOS != null) {
                highlightedDTOS.add(pageDTO);
            }
            this.setSearchQuery("");
            this.setSearchType(0);
            this.setSearchMode(false);
            sharedUtil.setSuccessMsgValue("SuccessAdds");

        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg(sharedUtil.getErrMsgValue());
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            sharedUtil.handleException(e);
            this.setErrorMsg("FailureInAdd");
        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");

        }
        //added by nora to reset radio if list has radio column
        setSelectedRadio("");
    }

    protected IBasicDTO executeAdd() throws DataBaseException, SharedApplicationException {
        return client.add(pageDTO);
    }

    public IBasicDTO preEdit() {

        SharedUtilBean shared_util = getSharedUtil();
        try {
            return client.getById(entityKey);
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

    //Paging

    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        SharedUtilBean sharedUtil = getSharedUtil();

        try {

            executeEdit();

            cancelSearch();

            if (usingPaging) {

                updateMyPagedDataModel = true;
                repositionTable = true;
                getPagingBean().clearDTOS();
                if (pagingRequestDTO == null) {
                    pagingRequestDTO = new PagingRequestDTO();
                }
                pagingRequestDTO.setRepositionKey((String)getPageDTO().getCode().getKey());

            }

            if (highlightedDTOS != null) {
                highlightedDTOS.add(pageDTO);
            }

            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            this.getSelectedDTOS().clear();

        } catch (DataBaseException e) {
            sharedUtil.handleException(e);
            //            sharedUtil.handleException(e,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInUpdate");
        } catch (ItemNotFoundException item) {
            sharedUtil.handleException(item);
            //            sharedUtil.handleException(item,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInUpdate");
        } catch (SharedApplicationException e) {
            //            sharedUtil.handleException(e,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInUpdate");
            sharedUtil.handleException(e);
        } catch (Exception e) {
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
        }
        //Added By Yassmine to reset the value of radio button after Edit
        setSelectedRadio("");

    }

    protected IBasicDTO executeEdit() throws DataBaseException, SharedApplicationException {
        client.update(pageDTO);
        return pageDTO;
    }

    public void delete() throws DataBaseException, ItemNotFoundException {
        SharedUtilBean shared_util = getSharedUtil();
        //        System.out.println("Calling delete()...");
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ex = ctx.getExternalContext();
        HttpSession session = (HttpSession)ex.getSession(true);

        if (getSelectedDTOS() == null)
            setSelectedDTOS(loadSelectedDTOS());

        //            for (IBasicDTO dto: getSelectedDTOS()) {


        try {
            deleteStatusDTOS = client.remove(selectedDTOS);
            if (getMyDataTable() != null && getMyDataTable().getRowCount() != 0) {
                getMyDataTable().setFirst(0);
            }
        } catch (DataBaseException db) {
            //shared_util.setErrMsgValue(db.getMessage());
            getSharedUtil().handleException(db);
        } catch (ItemNotFoundException item) {
            shared_util.setErrMsgValue("ItemCanNotBeDeletedException");
        } catch (SharedApplicationException e) {
            //shared_util.setErrMsgValue(e.getMessage());
            getSharedUtil().handleException(e);
        } catch (Exception e) {
            //getSharedUtil().handleException(e);
            e.printStackTrace();
        }


        //        }

        this.selectedDTOS.clear();
        session.setAttribute("selectedDTOS", null);
        cancelSearch();
        restoreTablePosition();


    }
    //this to delete and show div incase div is used

    public void deleteDiv() throws DataBaseException, ItemNotFoundException {
        this.delete();
        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");

    }

    public static String formatSearchString(String inputString) {
        //        return (inputString.contains((CharSequence)"%")) ? inputString :
        //               inputString + "%";
        return inputString;
    }

    //Paging

    public void search() throws DataBaseException, NoResultException {

        //        System.out.println("Calling search()...");
        this.setSearchMode(true);

        if (usingPaging) {

            setUpdateMyPagedDataModel(true);

            pagingRequestDTO = new PagingRequestDTO("baseSearchWithPaging");

            setOldPageIndex(0);
            setCurrentPageIndex(1);

        } else {

            List searchResult = this.getBaseSearchResult();

            setMyTableData(searchResult);

        }
        if (selectedDTOS != null) {
            selectedDTOS.clear();
        }

        if (highlightedDTOS != null) {
            highlightedDTOS.clear();
        }
        this.repositionPage(0);
        //added by nora to reset radio if list has radio column
        setSelectedRadio("");
    }

    //Paging
    /*
    * @author TechnicalTeam[D.AbdeLHady]
    * @since 04/08/2015
    * @param Pagging
    * @return PagingResponseDTO
    * @Note used enhance Pagging code
    */

    public PagingResponseDTO baseSearchWithPaging(PagingRequestDTO pagingRequestDTO) {
        IPagingResponseDTO bsnResponseDTO = searchResultsWithPaging();
        if (!resettedPageIndex) {
            resetPageIndex();
            resettedPageIndex = true;
        }
        if (bsnResponseDTO == null) {
            List searchResult = null;
            try {
                searchResult = getBaseSearchResult();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
            getPagingBean().preUpdateDataModel();
            return new PagingResponseDTO(searchResult);
        } else {
            return getPagingResponseDTO(bsnResponseDTO);
        }
    }


    /*
    * @author TechnicalTeam[D.AbdeLHady]
    * @since 04/08/2015
    * @param Pagging
    * @return PagingResponseDTO
    * @Note used enhance Pagging code
    */

    private com.beshara.base.paging2.IPagingResponseDTO searchResultsWithPaging() {
        com.beshara.base.paging2.IPagingRequestDTO bsnPagingRequestDTO = initiatePagingRequestDTO();
        try {
            pagingResponseDTO = getClient().searchWithPaging(bsnPagingRequestDTO, getSearchDTO());
        } catch (NoResultException e) {
            pagingResponseDTO = new com.beshara.base.paging2.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        } catch (DataBaseException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        }
        return pagingResponseDTO;
    }


    public List getBaseSearchResult() throws DataBaseException {

        List searchResult = new ArrayList();

        try {
            if (getSearchType().intValue() == 0) {
                searchResult = getClient().searchByCode(getSearchEntityObj());
            } else if (getSearchType().intValue() == 1) {
                searchResult = getClient().searchByName(formatSearchString(getSearchQuery()));
            }
        } catch (ItemNotFoundException e) { //this means no search results found
            myTableData = new ArrayList();
        } catch (NoResultException ne) { //this means no search results found
            myTableData = new ArrayList();
        } catch (Exception db) {

            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());

            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);
            throw new DataBaseException(db.getMessage());

        }

        return searchResult;
    }

    //Paging

    public void cancelSearch() throws DataBaseException {

        //        System.out.println("Calling cancelSearch()...");

        this.setSearchQuery("");
        this.setSearchType(0);
        this.setSearchMode(false);
        this.setSelectedRadio("");
        if (usingPaging) {
            getPagingBean().cancelSearch();
        } else {
            this.getAll();
            repositionPage(0);
        }


    }

    public void cancelAdd() {

        //        System.out.println("Calling cancelAdd()...");
        this.getPageDTO().setName("");
        this.getPageDTO().setEngName("");
        reIntializeMsgs();
        // added by a.nouman to solve select redio issue after close addDiv
        setSelectedRadio("");
        try {
            unCheck();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose:method used to  reIntialize sucess and error message,need to be called in the begin of save method , as in case user made saveANDNew then save .
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  12-Oct-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void reIntializeMsgs() {
        reIntializeAddDivMsgs();
        reIntializeDivMsgs();
    }

    /**
     * Purpose:method used to  reIntialize sucess and error message appears within Add Div
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Khalid El Seadawy
     * 1.2 - Date:  21-Oct-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void reIntializeAddDivMsgs() {
        // for save & new messages inside div ( add div )
        setSuccess(false);
        setErrorMsg("");
        setShowErrorMsg(false);
    }

    /**
     * Purpose:method used to  reIntialize sucess and error message appears msg Div
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Khalid El Seadawy
     * 1.2 - Date:  21-Oct-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void reIntializeDivMsgs() {
        // for error or success message within msg div
        getSharedUtil().setErrMsgValue(null);
        getSharedUtil().setSuccessMsgValue(null);
    }

    //reload data after popup operations

    public void reloadList(ValueChangeEvent event) throws Exception {

        String newValue = (String)event.getNewValue();
        //        System.out.println(newValue);
        if (newValue != null && !newValue.equals(null) && !newValue.equals("")) {
            this.getAll();
            reloadField.setValue("");
        }
    }

    //Paging

    public void selectedRadioButton(ValueChangeEvent event) throws Exception {

        if (usingPaging) {
            getPagingBean().setPreUpdatedDataModel(true);
        }

        if (event.getNewValue() != null) {

            setSelectedDTOS(new ArrayList<IBasicDTO>());
            IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();
            this.getSelectedDTOS().add(selected);

        }

    }

    // using hashset to solve the problem of the duplicate selected items in the datatable added by abolehassan hamed applied in the crs module

    public void selectedRadioButton(ActionEvent event) throws Exception {
        indexOfSelectedDataInDataTable = getMyDataTable().getRowIndex();
        this.getSelectedDTOS().clear();
        IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();
        this.getSelectedDTOS().add(selected);
    }

    //Paging

    public void selectedCheckboxs(ActionEvent event) throws Exception {

        //        System.out.println("selectedCheckboxs only one time");

        try {

            Set<IBasicDTO> selectedSet = new OrderedSet();
            selectedSet.addAll(selectedDTOS);

            IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();

            if (selected.getChecked()) {

                try {
                    selectedSet.add(selected);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                selected.setChecked(Boolean.TRUE);

                for (IBasicDTO item : selectedSet) {
                    if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                        selectedSet.remove(item);
                        break;
                    }
                }

                selected.setChecked(Boolean.FALSE);

            }

            selectedDTOS.clear();
            selectedDTOS.addAll(selectedSet);
            System.out.print(selectedDTOS.size());
        } catch (Exception e) {

            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }

    }

    // using hashset to solve the problem of the duplicate selected items in the datatable added by abolehassan hamed applied in the crs module

    //Paging

    public void selectedCheckboxsAll(ActionEvent event) throws Exception {

        try {

            Set<IBasicDTO> selectedSet = new OrderedSet();
            selectedSet.addAll(selectedDTOS);

            Integer rowsCountPerPage =
                (Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());

            if (rowsCountPerPage == null) {

                throw new Exception("#{shared_util.noOfTableRows} return null");

            }

            int first = this.getMyDataTable().getFirst();

            for (int j = first; j < first + rowsCountPerPage.intValue(); j++) {

                this.getMyDataTable().setRowIndex(j);

                if (!this.getMyDataTable().isRowAvailable()) {
                    break;
                }

                IBasicDTO selected = (IBasicDTO)this.getMyDataTable().getRowData();

                if (this.isCheckAllFlag()) {

                    try {
                        selectedSet.add(selected);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    for (IBasicDTO item : selectedSet) {
                        if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                            selectedSet.remove(item);
                            break;
                        }
                    }

                }

            }

            selectedDTOS.clear();
            selectedDTOS.addAll(selectedSet);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void refreshAll() {
        try {
            this.getAll();
        } catch (DataBaseException e) {
            System.out.println("baseBean::refreshAll::catch::DataBaseException=" + e);
        }
    }
    //function called by the confirm delete popup to load the selected dtos

    public List<IBasicDTO> loadSelectedDTOS() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ex = ctx.getExternalContext();
        HttpSession session = (HttpSession)ex.getSession(true);
        return ((List<IBasicDTO>)session.getAttribute("selectedDTOS"));
    }

    //Paging

    public Integer getListSize() throws DataBaseException {

        if (usingPaging) {

            PagingBean pagingBean = getPagingBean();

            try {
                if (pagingBean.getMyPagedDataModel() == null) {

                    pagingBean.getMyPagedDataModel();

                }
            } catch (NoResultException e) {
                e.printStackTrace();
            }

            return pagingBean.getTotalListSize();

        } else {

            if (this.getMyTableData() != null && myTableData != null) {
                return myTableData.size();
            }

            return 0;

        }

    }

    /**
     * Purpose: this method is used to handle table row double click,
     * clickedDTOList: The binding of the list which i will add the clicked row to it (mainly pageBeanName.selectedDTOS)
     * tableBinding: The binding of the table which i will use to get row data (mainly pageBeanName.myDataTable)
     * action: which i will call to manipulate the clickedDTO
     * Creation/Modification History :
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  Sep 26, 2010
     * @param actionEvent : i will get all the parameters from this ActionEvent
     * updated by A.agamy / Heba ahmed
     * Date 12/8/2015
     */
    public void dblClickAction(ActionEvent actionEvent) throws Exception {
        java.util.Map<java.lang.String, javax.faces.component.UIParameter> paramMap = JSFHelper.getParams(actionEvent);
        String clickedDTOListBinding = paramMap.get("clickedDTOList").getValue().toString();
        List<IBasicDTO> clickedDTOList = (List<IBasicDTO>)JSFHelper.getValue(clickedDTOListBinding);
        if (clickedDTOList != null) {
            if ((getSelectionComponentType().equals("0") && clickedDTOList.size() != 0)) { // checkbox case

                // Added by MLotfy,
                // If you have in your data table multible checked check boxes, then
                // you tried to make double click action on a new row, then all the
                // old checked rows before will be unchecked, and the new checked row
                // will be only check, then fire the action on this new checked row.
                for (IClientDTO dto : (List<IClientDTO>)((List)clickedDTOList)) {
                    dto.setChecked(false);
                }
                clickedDTOList.clear();
            }
        }
        String tableBinding = paramMap.get("tableBinding").getValue().toString();
        HtmlDataTable myDataTable = (HtmlDataTable)JSFHelper.getValue(tableBinding);
        myDataTable.setRowIndex(getClickedRowIndex());
        clickedDTOList = new ArrayList<IBasicDTO>();
        IClientDTO basicDTO = (IClientDTO)myDataTable.getRowData();
        basicDTO.setChecked(true);
        clickedDTOList.add(basicDTO);
        JSFHelper.setValue(clickedDTOListBinding, clickedDTOList);
        //        Object actionParam = paramMap.get("action");

        //        if (actionParam != null) {
        //
        //            String action = (String)((HtmlActionParameter)actionParam).getValue();
        //            String actionOutput = (String)JSFHelper.callMethod(action);
        //            Object ajaxOncomplete = ((HtmlAjaxFunction)actionEvent.getSource()).getAttributes().get("reRender");
        //            if (ajaxOncomplete == null || ajaxOncomplete.toString().length() == 0) {
        //                JSFHelper.handleNavigation(actionOutput);
        //            }
        //            String rerender = ajaxOncomplete.toString();
        //            rerender = rerender + ",javaScriptCallerOutputText";
        //            ((HtmlAjaxFunction)actionEvent.getSource()).setReRender(rerender);
        //
        //        }

        String reRender = null;
        Object reRenderObj = ((HtmlAjaxFunction)actionEvent.getSource()).getAttributes().get("reRender");
        if (reRenderObj == null || reRenderObj.toString().length() == 0) {
            reRender = "javaScriptCallerOutputText";
        } else {
            reRender = reRenderObj.toString() + ",javaScriptCallerOutputText";
        }


        ((HtmlAjaxFunction)actionEvent.getSource()).setReRender(reRender);
        setJavaScriptCaller("fireEditButton();");

    }


    /**
     * Purpose: this method is used to get the ID from the commandlink tag OR from the operatuibbar menuitem tag and to get the ascending type (ascending or descending)
     * and then pass it to the sort method
     * Creation/Modification History :
     * 1.1 - Developer Name: Mohamed Galala
     * 1.2 - Date:  Oct 19, 2008
     * 1.3 - Creation/Modification: Modification done to get the specific id whether it is from JSF command link page or from menuitem in operation bar
     * 1.4-  Description: the method check the start and end of the id been got from the event, if it ends with "__btn" or starts with "sort_"
     * and then get the remaining part of the id as the full actual path for the value shall be sorted.
     */
    public void sortDataTable(ActionEvent event) {

        if (usingPaging) {
            sorting = true;
            updateMyPagedDataModel = true;
            bsnSortingColumnName = (String)getRequestParameter("bsnSortingColumnName");
        }
        try {
            //get the id value for the commandlink (JSF page) OR menuitem (operationbar)
            setFullColumnName((String)event.getComponent().getId());

            //used to sort using EL expressoins only
            String listExpression = (String)getRequestParameter("listExpression");
            String currentSortingRowIndex = (String)getRequestParameter("currentSortingRowIndex");
            String elExpression = (String)getRequestParameter("elExpression");
            boolean useElExpression = false;
            if (listExpression != null && listExpression.length() > 0 && elExpression != null &&
                elExpression.length() > 0) {
                useElExpression = true;
            }

            //check if the id from commandlink, then id value is what is after "sort_"
            if (fullColumnName != null && fullColumnName.startsWith("sort")) {

                setFullColumnName(fullColumnName.substring(fullColumnName.indexOf("_") + 1,
                                                           fullColumnName.length())); // to get attribute within DTO to sort list according to it
            }
            //check if the id from menuitem, then id value is what is before"__btn"
            else if (fullColumnName != null && fullColumnName.endsWith("btn")) {

                setFullColumnName(fullColumnName.substring(0,
                                                           fullColumnName.indexOf("__"))); // to get attribute within DTO to sort list according to it
            }

            //sortColumnAttribute carry the value of the id as an abstract value without any prefix or suffix
            String sortColumnAttribute =
                fullColumnName == null ? "code" : fullColumnName; // default sort column , in case no sort column passed

            sortAscending = !sortAscending;

            if (!isUsingBsnPaging()) {

                if (useElExpression) {
                    this.sort(listExpression, currentSortingRowIndex, elExpression, sortAscending);
                } else {
                    this.sort(sortColumnAttribute, sortAscending);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Purpose: this method is used to sort using the expression language (EL)
     * Creation/Modification History :
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  Jan 15, 2009
     * @param listExpression : tabel list binding expression, ex. myListBean.myTableData
     * @param currentSortingRowIndex :which will carry the current row index at the sorting time
     * @param elExpression : the el which will be evaluated, cant handel the resources till now, ex. myListBean.myTableData[myListBean.currentSortingRowIndex].myAttribute
     * @param sortAscending : true/false
     */
    public void sort(String listExpression, String currentSortingRowIndex, String elExpression,
                     boolean sortAscending) {
        List totalList = new ArrayList();
        if (usingPaging) {
            PagingResponseDTO responseDTO = getPagingBean().getPagingResponse();
            if (responseDTO != null && responseDTO.getResultList() != null) {
                totalList = responseDTO.getResultList();
            }
        } else {
            totalList = myTableData;
        }

        IBasicDTO[] array =
            (IBasicDTO[])totalList.toArray(new IBasicDTO[0]); //get the data values in the datatable and put it in an array of IBasicDTO
        Arrays.sort(array, new DtoComparator(listExpression, currentSortingRowIndex, elExpression, sortAscending));

        List sortedList = Arrays.asList(array);

        if (usingPaging) {
            getPagingBean().updateMyPadgedDataModel(sortedList);
        } else {
            myTableData.clear();
            myTableData.addAll(sortedList);
        }
        sortedTable = true;
        myDataTable.setSortAscending(sortAscending);
    }

    //Paging

    public void sort(String columnName) {
        sort(columnName, true);
    }

    public void sort(String columnName, boolean sortAscending) {
        List totalList = new ArrayList();
        if (usingPaging) {
            PagingResponseDTO responseDTO = getPagingBean().getPagingResponse();
            if (responseDTO != null && responseDTO.getResultList() != null) {
                totalList = responseDTO.getResultList();
            }
        } else {
            totalList = myTableData;
        }
        sort(totalList, columnName, sortAscending, true);
    }

    /**
     * Purpose: this method is used to sort the passed list
     * Creation/Modification History :
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  Feb 09, 2010
     * @param totalList : tabel list
     * @param columnName
     * @param sortAscending : true/false
     * @param updateDataModel : update list model or No
     */
    public List sort(List totalList, String columnName, boolean sortAscending, boolean updateDataModel) {
        if (totalList == null) {
            totalList = new ArrayList();
        }
        List sortedTotalList = new ArrayList();
        if (totalList.size() != 0 && !(columnName == null || columnName.length() == 0)) {

            setSortColumn(columnName);
            IBasicDTO[] array = (IBasicDTO[])totalList.toArray(new IBasicDTO[0]);
            Arrays.sort(array, new DtoComparator(sortColumn, sortAscending));
            sortedTotalList = Arrays.asList(array);
        } else {
            for (Object o : totalList) {
                sortedTotalList.add(o);
            }
        }
        if (updateDataModel) {
            if (usingPaging) {
                resetPageIndex();
                getPagingBean().updateMyPadgedDataModel(sortedTotalList);
            } else {
                myTableData.clear();
                myTableData.addAll(sortedTotalList);
            }

            myDataTable.setSortAscending(sortAscending);
        }
        sortedTable = true;

        return sortedTotalList;
    }

    public WizardBar getWizardBar() {

        FacesContext ctx = FacesContext.getCurrentInstance();
        WizardBar bar = (WizardBar)ctx.getApplication().createValueBinding("#{wizardbar}").getValue(ctx);
        return bar;

    }

    public void setWizardBar(WizardBar bar) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getApplication().createValueBinding("#{wizardbar}").setValue(ctx, bar);
    }

    //Paging

    public void unCheck() {
        setCheckAllFlag(false);
        if (usingPaging) {

            List pagedList = null;

            try {
                pagedList = (List)getPagingBean().getMyPagedDataModel().getWrappedData();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (NoResultException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < pagedList.size(); i++) {

                if (((IClientDTO)pagedList.get(i)).getChecked() != null &&
                    ((IClientDTO)pagedList.get(i)).getChecked().booleanValue()) {

                    ((IClientDTO)pagedList.get(i)).setChecked(Boolean.FALSE);

                }
            }

        } else {

            for (int i = 0; i < myTableData.size(); i++) {

                if (((IClientDTO)myTableData.get(i)).getChecked() != null &&
                    ((IClientDTO)myTableData.get(i)).getChecked().booleanValue()) {

                    ((IClientDTO)myTableData.get(i)).setChecked(Boolean.FALSE);

                }
            }

        }

        if (selectedDTOS != null) {
            selectedDTOS.clear();
        }

    }

    public void showHideColumn(String columnName) {
        HtmlSimpleColumn column = (HtmlSimpleColumn)myDataTable.findComponent(columnName);
        if (column.isRendered()) {
            column.setRendered(false);
        } else {
            column.setRendered(true);
        }
    }

    public void repositionPage(int firstPos) {
        setSortedTable(false);
        if (myDataTable != null)
            myDataTable.setFirst(firstPos);

    }


    /**
     * Purpose: return index of object that have passed key
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   28/8/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */

    //Paging
    public void getPageIndex(String key) throws DataBaseException {

        getPageIndex(key, null);

    }

    //Paging

    public void getPageIndex(String key, List totalList) throws DataBaseException {

        int rows = getSharedUtil().getNoOfTableRows();

        int totalListSize = 0;

        if (usingPaging) {
            totalListSize = getPagingBean().getTotalListSize();
        } else {
            totalListSize = getMyTableData().size();
        }

        if (totalListSize > 0 && getMyDataTable() != null) {

            int noOfPages = 0;

            if (usingPaging) {
                noOfPages = getPagingBean().getPagesCount();
            } else {
                noOfPages = ((totalListSize - 2) / rows) + 1;
            }

            int index = 0;

            if (!usingBsnPaging) {
                if (totalList != null) { // using paging

                    index = getItemIndex(key, totalList);
                    currentPageIndex = ++index / rows;

                    if (index % rows > 0) {
                        ++currentPageIndex;
                    }

                    oldPageIndex = currentPageIndex;

                } else {
                    index = getItemIndex(key);
                }
            }

            if (usingPaging) {

                int firstRow = 0;

                if (usingBsnPaging) {
                    firstRow = (currentPageIndex - 1) * rows;
                } else {
                    if (totalListSize > 0) {

                        int tempPagesCount = index / rows;

                        if (index % rows > 0) {
                            ++tempPagesCount;
                        }

                        firstRow = (tempPagesCount - 1) * rows;

                    }
                }

                getMyDataTable().setFirst(firstRow);

            } else {

                for (int i = 0; i < noOfPages; i++) {

                    if (index == rows * i) {
                        getMyDataTable().setFirst(rows * i);
                    } else if (index == (rows * (i + 1))) {
                        getMyDataTable().setFirst(rows * (i + 1));
                    } else if (index > rows * i && index < rows * (i + 1)) {
                        getMyDataTable().setFirst(rows * i);
                    }

                }

            }
        }

    }

    /**
     * Purpose: return index of object that have passed key
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   28/8/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    //Paging
    public int getItemIndex(String key) throws DataBaseException {

        return getItemIndex(key, getMyTableData());

    }

    //Paging

    public int getItemIndex(String key, List totalList) throws DataBaseException {

        int index = 0;

        for (int b = 0; b < totalList.size(); b++) {

            IBasicDTO basicDTO = (IBasicDTO)totalList.get(b);

            if (basicDTO.getCode() != null && key != null && basicDTO.getCode().getKey().equals(key)) {

                index = b;
                break;
            }
        }

        return index;

    }

    public SharedUtilBean getSharedUtil() {
        return SharedUtilBean.getInstance();
    }

    public void setSelectedPageDTO(IBasicDTO selectedPageDTO) {

        if (selectedPageDTO != null)
            this.selectedPageDTO = selectedPageDTO;

    }

    public IBasicDTO getSelectedPageDTO() {


        return selectedPageDTO;

    }

    public void setShowEdit(boolean showEdit) {
        this.showEdit = showEdit;
    }

    public boolean isShowEdit() {

        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
            showEdit = true;


        } else {

            showEdit = false;

        }


        return showEdit;
    }


    public void resetTableHeaderStatus() {

        FacesContext.getCurrentInstance().getApplication().createValueBinding("#{TableHeaderBean.selectedCheckBoxesCount}").setValue(FacesContext.getCurrentInstance(),
                                                                                                                                     new Integer(0));

    }

    public void setDeleteStatusDTOS(List<IResultDTO> deleteStatusDTOS) {
        this.deleteStatusDTOS = deleteStatusDTOS;
    }

    public List<IResultDTO> getDeleteStatusDTOS() {
        return deleteStatusDTOS;
    }

    public void setJavaScriptCaller(String javaScriptCaller) {
        this.javaScriptCaller = javaScriptCaller;
    }

    public String getJavaScriptCaller() {
        return javaScriptCaller;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setReloadField(HtmlInputHidden reloadField) {
        this.reloadField = reloadField;
    }

    public HtmlInputHidden getReloadField() {
        return reloadField;
    }

    //Paging

    public void restoreTablePosition() {

        if (usingPaging) {

            getPagingBean().restoreTablePosition();

        } else {

            int beforeIndex = myDataTable.getFirst();
            int afterIndex = beforeIndex - myDataTable.getRows();
            if (beforeIndex != 0 && myTableData.size() % beforeIndex == 0) {
                if (afterIndex >= 0) {
                    myDataTable.setFirst(afterIndex);
                } else {
                    myDataTable.setFirst(0);
                }
            }

        }

    }


    public void showEditDiv() {
        reIntializeMsgs();
        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
            //            selectedPageDTO=this.getSelectedDTOS().get(0);
            IBasicDTO dto = this.getSelectedDTOS().get(0);
            try {
                selectedPageDTO = client.getById(dto.getCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
            showEdit = true;

            //javaScriptCaller = "changeVisibilityDiv(window.blocker,window.lookupEditDiv);";


        } else {
            selectedPageDTO = new BasicDTO();
            showEdit = false;

        }


    }

    public void hideEditDiv() {

        showEdit = false;
        javaScriptCaller = "hideDivEdit();";

        //selectedPageDTO = new IBasicDTO();

    }

    public void writeClientJavaScript(String script) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        AddResource addResource = AddResourceFactory.getInstance(facesContext);
        addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, script);


    }

    public void save() {
        try {

            this.add();
            this.reInitializePageDTO();
        } catch (Exception ex) {
            this.setShowErrorMsg(true);
            this.setErrorMsg(ex.getCause().getMessage());
        }
    }

    public void reInitializePageDTO() {
        pageDTO = new BasicDTO();

    }

    public void checkControlsHeaderStatus() {
        TableHeaderBean tableHeaderBean =
            (TableHeaderBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{TableHeaderBean}").getValue(FacesContext.getCurrentInstance());
        tableHeaderBean.setSelectedCheckBoxesCount(tableHeaderBean.getSelectedCheckBoxesCount() - 1);
    }

    public void setAddDivTitleBindingString(String addDivTitleBindingString) {
        this.addDivTitleBindingString = addDivTitleBindingString;
    }

    public String getAddDivTitleBindingString() {
        return addDivTitleBindingString;
    }

    public void setEditDivTitleBindingString(String editDivTitleBindingString) {
        this.editDivTitleBindingString = editDivTitleBindingString;
    }

    public String getEditDivTitleBindingString() {
        return editDivTitleBindingString;
    }

    public void setDelAlertTitleBindingString(String delAlertTitleBindingString) {
        this.delAlertTitleBindingString = delAlertTitleBindingString;
    }

    public String getDelAlertTitleBindingString() {
        return delAlertTitleBindingString;
    }

    public void setDelConfirmTitleBindingString(String delConfirmTitleBindingString) {
        this.delConfirmTitleBindingString = delConfirmTitleBindingString;
    }

    public String getDelConfirmTitleBindingString() {
        return delConfirmTitleBindingString;
    }

    public String getAddDivTitle() {

        addDivTitle = (String)this.getBindingValue(this.getAddDivTitleBindingString());
        return addDivTitle;
    }

    public String getEditDivTitle() {
        editDivTitle = (String)this.getBindingValue(this.getEditDivTitleBindingString());
        return editDivTitle;
    }

    public String getDelAlertTitle() {

        delAlertTitle = (String)this.getBindingValue(this.getDelAlertTitleBindingString());
        return delAlertTitle;
    }

    public String getDelConfirmTitle() {
        delConfirmTitle = (String)this.getBindingValue(this.getDelConfirmTitleBindingString());
        return delConfirmTitle;
    }

    public Object getBindingValue(String bindingExpression) {
        if (bindingExpression != null) {
            Object ob =
                FacesContext.getCurrentInstance().getApplication().createValueBinding(bindingExpression).getValue(FacesContext.getCurrentInstance());
            return ob;
        } else {
            return "";
        }

    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        if (myDataTable != null && highlightedDTOS != null) {
            IBasicDTO dto = (IBasicDTO)myDataTable.getRowData();
            return isRowHighlight(dto);
        }
        return selected;

    }

    /**
     * Purpose:check if current row highlighted or not
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   28/8/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public boolean isRowHighlight(IBasicDTO currentRowObject) {

        for (IBasicDTO dt : highlightedDTOS) {
            if (dt.getCode() != null && currentRowObject.getCode() != null &&
                dt.getCode().getKey().equals(currentRowObject.getCode().getKey())) {
                return true;
            }
        }
        return false;
    }

    public void setHighlightedDTOS(List<IBasicDTO> highlightedDTOS) {
        this.highlightedDTOS = highlightedDTOS;
    }

    public List<IBasicDTO> getHighlightedDTOS() {
        return highlightedDTOS;
    }

    public void setSearchMode(boolean searchMode) {
        this.searchMode = searchMode;
    }

    public boolean isSearchMode() {
        return searchMode;
    }

    public void setSelectedListSize(int selectedListSize) {
        this.selectedListSize = selectedListSize;
    }

    public int getSelectedListSize() {
        if (this.getSelectedDTOS() != null) {

            return this.getSelectedDTOS().size();

        }

        return selectedListSize;
    }
    //yasmine work


    public void setAllIndexes(String allIndexes) {
        this.allIndexes = allIndexes;
    }

    public String getAllIndexes() {
        return allIndexes;
    }

    public void setCasesArray(String[] casesArray) {
        this.casesArray = casesArray;
    }

    public String[] getCasesArray() {
        return casesArray;
    }

    public void setCasesArrayConcatinated(String casesArrayConcatinated) {
        this.casesArrayConcatinated = casesArrayConcatinated;
    }

    public String getCasesArrayConcatinated() {
        if (casesArrayConcatinated.equals("")) {
            getCasesArray();
            for (int i = 0; i < casesArray.length; i++) {
                casesArrayConcatinated += casesArray[i] + "$";
            }
        }
        return casesArrayConcatinated;
    }

    public void setCasesArrayButton(String[] casesArrayButton) {
        this.casesArrayButton = casesArrayButton;
    }

    public String[] getCasesArrayButton() {
        return casesArrayButton;
    }

    public void setCasesArrayConcatinatedButon(String casesArrayConcatinatedButon) {
        this.casesArrayConcatinatedButon = casesArrayConcatinatedButon;
    }

    public String getCasesArrayConcatinatedButon() {
        if (casesArrayConcatinatedButon.equals("")) {
            getCasesArrayButton();
            for (int i = 0; i < casesArrayButton.length; i++) {
                casesArrayConcatinatedButon += casesArrayButton[i] + "$";
            }
        }
        return casesArrayConcatinatedButon;
    }

    public void setAllIndexesButton(String allIndexesButton) {
        this.allIndexesButton = allIndexesButton;
    }

    public String getAllIndexesButton() {
        return allIndexesButton;
    }

    public void setSearchEntityObj(Object searchEntityObj) {
        this.searchEntityObj = searchEntityObj;
    }

    public Object getSearchEntityObj() {
        return searchEntityObj;
    }

    public void setCheckAllFlag(boolean checkAllFlag) {
        this.checkAllFlag = checkAllFlag;
    }

    public boolean isCheckAllFlag() {
        return checkAllFlag;
    }

    public void setShowErrorMsg(boolean showErrorMsg) {
        this.showErrorMsg = showErrorMsg;
    }

    public boolean isShowErrorMsg() {
        return showErrorMsg;
    }

    public void setAlreadyDeleted(boolean alreadyDeleted) {
        this.alreadyDeleted = alreadyDeleted;
    }

    public boolean isAlreadyDeleted() {
        return alreadyDeleted;
    }

    public void setDivMsg(String divMsg) {
        this.divMsg = divMsg;
    }

    public String getDivMsg() {
        return divMsg;
    }

    public void setIframeBlock(String iframeBlock) {
        this.iframeBlock = iframeBlock;
    }

    public String getIframeBlock() {
        return iframeBlock;
    }

    public void setDivSearch(String divSearch) {
        this.divSearch = divSearch;
    }

    public String getDivSearch() {
        return divSearch;
    }

    public void setDelAlert(String delAlert) {
        this.delAlert = delAlert;
    }

    public String getDelAlert() {
        return delAlert;
    }

    public void setDelConfirm(String delConfirm) {
        this.delConfirm = delConfirm;
    }

    public String getDelConfirm() {
        return delConfirm;
    }

    public void setContent1Div(String content1Div) {
        this.content1Div = content1Div;
    }

    public String getContent1Div() {
        return content1Div;
    }

    public void setDivMsgMany(String divMsgMany) {
        this.divMsgMany = divMsgMany;
    }

    public String getDivMsgMany() {
        return divMsgMany;
    }

    public void setDivSearchMany(String divSearchMany) {
        this.divSearchMany = divSearchMany;
    }

    public String getDivSearchMany() {
        return divSearchMany;
    }

    public void setDivAddMany(String divAddMany) {
        this.divAddMany = divAddMany;
    }

    public String getDivAddMany() {
        return divAddMany;
    }

    public void setDivDelMany(String divDelMany) {
        this.divDelMany = divDelMany;
    }

    public String getDivDelMany() {
        return divDelMany;
    }

    public void setDivConfirmMany(String divConfirmMany) {
        this.divConfirmMany = divConfirmMany;
    }

    public String getDivConfirmMany() {
        return divConfirmMany;
    }

    public void setLookupAddDiv(String lookupAddDiv) {
        this.lookupAddDiv = lookupAddDiv;
    }

    public String getLookupAddDiv() {
        return lookupAddDiv;
    }

    public void setLookupEditDiv(String lookupEditDiv) {
        this.lookupEditDiv = lookupEditDiv;
    }

    public String getLookupEditDiv() {
        return lookupEditDiv;
    }

    public void setMasterDetailDiv(String masterDetailDiv) {
        this.masterDetailDiv = masterDetailDiv;
    }

    public String getMasterDetailDiv() {
        return masterDetailDiv;
    }

    public void setDivTreeAdd(String divTreeAdd) {
        this.divTreeAdd = divTreeAdd;
    }

    public String getDivTreeAdd() {
        return divTreeAdd;
    }

    public void setDivTreeEdit(String divTreeEdit) {
        this.divTreeEdit = divTreeEdit;
    }

    public String getDivTreeEdit() {
        return divTreeEdit;
    }

    public void setDelAlertTree(String delAlertTree) {
        this.delAlertTree = delAlertTree;
    }

    public String getDelAlertTree() {
        return delAlertTree;
    }

    public void setDivTreeDetails(String divTreeDetails) {
        this.divTreeDetails = divTreeDetails;
    }

    public String getDivTreeDetails() {
        return divTreeDetails;
    }

    public void setDivMainContent(String divMainContent) {
        this.divMainContent = divMainContent;
    }

    public String getDivMainContent() {
        return divMainContent;
    }

    public void setDivMainContentMany(String divMainContentMany) {
        this.divMainContentMany = divMainContentMany;
    }

    public String getDivMainContentMany() {
        return divMainContentMany;
    }

    public void setSeachValidationType(String seachValidationType) {
        this.seachValidationType = seachValidationType;
    }

    public String getSeachValidationType() {
        return seachValidationType;
    }

    public void setCustomDiv1(String customDiv1) {
        this.customDiv1 = customDiv1;
    }

    public String getCustomDiv1() {
        return customDiv1;
    }

    public void setCustomDiv2(String customDiv2) {
        this.customDiv2 = customDiv2;
    }

    public String getCustomDiv2() {
        return customDiv2;
    }

    public void setFilterDTO(IBasicDTO filterDTO) {
        this.filterDTO = filterDTO;
    }

    public IBasicDTO getFilterDTO() {
        return filterDTO;
    }

    public void setSelectedRadio(String selectedRadio) {
        if (selectedRadio != null) {
            this.selectedRadio = selectedRadio;
        }
    }

    public String getSelectedRadio() {
        return selectedRadio;
    }

    public void setFilterMode(boolean filterMode) {
        this.filterMode = filterMode;
    }

    public boolean isFilterMode() {
        return filterMode;
    }

    public void setCurrentApplictionMainLayout(AppMainLayout currentApplictionMainLayout) {
        FacesContext.getCurrentInstance().getApplication().createValueBinding("#{appMainLayout}").setValue(FacesContext.getCurrentInstance(),
                                                                                                           currentApplictionMainLayout);
        //        this.currentApplictionMainLayout = currentApplictionMainLayout;
    }

    public void setCurrentApplictionMainLayoutPortal(AppMainLayoutPortal currentApplictionMainLayoutPortal) {
        FacesContext.getCurrentInstance().getApplication().createValueBinding("#{appMainLayoutPortal}").setValue(FacesContext.getCurrentInstance(),
                                                                                                                 currentApplictionMainLayoutPortal);
        //        this.currentApplictionMainLayoutPortal = currentApplictionMainLayoutPortal;
    }

    public AppMainLayout getCurrentApplictionMainLayout() {
        return (AppMainLayout)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{appMainLayout}").getValue(FacesContext.getCurrentInstance());
    }

    public IntegrationBean getIntegrationBean() {
        return IntegrationBean.getInstance();
    }

    public SharedUtilBeanPortal getSharedUtilBeanPortal() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (SharedUtilBeanPortal)app.createValueBinding("#{sharedUtilBeanPortal}").getValue(ctx);
    }

    /**
     * Purpose: this method created to evaluate the valuebinding expression and return the value
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public Object evaluateValueBinding(String valueBindingExpression) {

        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return app.createValueBinding("#{" + valueBindingExpression + "}").getValue(ctx);

    }

    /**
     * Purpose: this method created to set the value of the valuebinding expression passed to it
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void setValueBinding(String valueBindingExpression, Object value) {

        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        app.createValueBinding("#{" + valueBindingExpression + "}").setValue(ctx, value);

    }

    /**
     * Purpose: this method created to execute the method binding expression and return the results
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public Object executeMethodBinding(String methodBindingExepression, Object[] paramList) {

        MethodBinding methodBinding = null;
        if (paramList != null) {
            methodBinding =
                    FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + methodBindingExepression +
                                                                                           "}", new Class[] { });
        } else {

            methodBinding =
                    FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + methodBindingExepression +
                                                                                           "}", null);

        }

        return methodBinding.invoke(FacesContext.getCurrentInstance(), paramList);

    }

    /**
     * Purpose: this method created to execute the method binding expression and return the results
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public Object executeMethodBindingWithParams(String methodBindingExepression, Object[] paramList,
                                                 Class[] paramTypesList) {

        MethodBinding methodBinding = null;
        if (paramList != null) {
            methodBinding =
                    FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + methodBindingExepression +
                                                                                           "}", paramTypesList);
        } else {

            methodBinding =
                    FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + methodBindingExepression +
                                                                                           "}", null);

        }

        return methodBinding.invoke(FacesContext.getCurrentInstance(), paramList);

    }

    /**
     * Purpose: this method created to evaluate the method binding expression and return method binding object
     * Creation/Modification History :
     * 1.1 - Developer Name:  aboelhassan hamed
     * 1.2 - Date:  25-06-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public MethodBinding evaluateMethodBinding(String methodBindingExepression, Class[] paramTypesList) {

        MethodBinding methodBinding = null;

        if (paramTypesList != null) {
            methodBinding =
                    FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + methodBindingExepression +
                                                                                           "}", paramTypesList);

        } else {

            methodBinding =
                    FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + methodBindingExepression +
                                                                                           "}", null);

        }

        return methodBinding;

    }


    /**
     * Purpose:Highlighting method used when add and edit in pages not divs and when list page is dataTable.
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Yassmine Ali
     * 1.2 - Date:  20-Aug-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: send to it your bean name as a parameter.
     */
    /*public void highlighDataTable(String beanName) {
        BaseBean listBean = (BaseBean)evaluateValueBinding(beanName);
        if (listBean != null) {
            List baseBeanList =
                (List)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
                                                                                            beanName +
                                                                                            ".myTableData}").getValue(FacesContext.getCurrentInstance());
            if (getPageDTO() != null && getPageDTO().getCode() != null) {
                if (listBean.getHighlightedDTOS() != null) {
                    listBean.getHighlightedDTOS().clear();
                    listBean.getHighlightedDTOS().add(getPageDTO());
                }
                try {
                    listBean.getPageIndex((String)getPageDTO().getCode().getKey());
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    //Paging
    public void highlighDataTable(String beanName) {

        BaseBean listBean = (BaseBean)evaluateValueBinding(beanName);

        if (listBean != null) {

            if (getPageDTO() != null && getPageDTO().getCode() != null) {

                if (listBean.getHighlightedDTOS() != null) {
                    listBean.getHighlightedDTOS().clear();
                    listBean.getHighlightedDTOS().add(getPageDTO());
                }

                if (listBean.isUsingPaging()) {

                    listBean.setUpdateMyPagedDataModel(true);
                    listBean.setRepositionTable(true);

                    PagingRequestDTO requestDTO = listBean.getPagingRequestDTO();
                    if (requestDTO == null) {
                        requestDTO = new PagingRequestDTO();
                    }
                    requestDTO.setRepositionKey((String)getPageDTO().getCode().getKey());
                    listBean.setPagingRequestDTO(requestDTO);

                } else {

                    try {
                        listBean.getPageIndex((String)getPageDTO().getCode().getKey(), null);
                    } catch (DataBaseException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        }

    }

    /**
     * Purpose:method used to  reIntialize sucess and error message,need to be called in the begin of save method , as in case user made saveANDNew then save .
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  12-Oct-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void setVirtualLongValue(Long virtualLongValue) {
        this.virtualLongValue = virtualLongValue;
    }

    public Long getVirtualLongValue() {
        return virtualLongValue;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    public void setFullColumnName(String fullColumnName) {
        this.fullColumnName = fullColumnName;
    }

    public String getFullColumnName() {
        return fullColumnName;
    }

    public void setVirtualConstValue(String virtualConstValue) {
        this.virtualConstValue = virtualConstValue;
    }

    public String getVirtualConstValue() {
        return virtualConstValue;
    }


    public void setNameMaxLength(int nameMaxLength) {
        this.nameMaxLength = nameMaxLength;
    }

    public int getNameMaxLength() {
        return nameMaxLength;
    }

    public void setCalendarTextLength(int calendarTextLength) {
        this.calendarTextLength = calendarTextLength;
    }

    public int getCalendarTextLength() {
        return calendarTextLength;
    }

    public void reinitializeSort() {
        if (!saveSortingState) {
            setFullColumnName("");
            setSortAscending(true);
        } else {
            sortedTable = false;
        }
    }

    /**
     * Purpose:method used to  get instance from managedConstantsBean if you need to access constants in your backing bean
     * Creation/Modification History :Creation
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  05-Nov-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public ManagedConstantsBean getManagedConstantsBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (ManagedConstantsBean)app.createValueBinding("#{managedConstantsBean}").getValue(ctx);
    }

    public void returnMethod() {

    }

    public void setLovBaseBean(LOVBaseBean lovBaseBean) {
        this.lovBaseBean = lovBaseBean;
    }

    public LOVBaseBean getLovBaseBean() {
        return lovBaseBean;
    }

    public void setLovDiv(String lovDiv) {
        this.lovDiv = lovDiv;
    }

    public String getLovDiv() {
        return lovDiv;
    }

    public void setCurrentSortingRowIndex(int currentSortingRowIndex) {
        this.currentSortingRowIndex = currentSortingRowIndex;
    }

    public int getCurrentSortingRowIndex() {
        return currentSortingRowIndex;
    }

    public Object getRequestParameter(String name) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    /**
     * Purpose:build ministry combox box list @ Major and minor ministries
     * @Param ministryList : return list from database using your local method
     * @Param appended_char :parameter that will be append @ minor ministry @ combo box
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date: 20-1-2009
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public List buildMinistryTree(List ministryList, String appended_char) {

        List ministryTreeList = new ArrayList();
        Map allElement = new HashMap();
        String key = null;


        try {
            for (IBasicDTO element : (ArrayList<IBasicDTO>)ministryList) {
                if (((ITreeDTO)element).getTreeLevel().equals(ISystemConstant.FIRST_LEVEL_IN_TREE) &&
                    (((ITreeDTO)element).getChildernNumbers() != null) &&
                    (((ITreeDTO)element).getChildernNumbers() > 1))
                    ministryTreeList.add(element);

                key = element.getCode().getKey();
                allElement.put(key, element);
            }
            for (IBasicDTO element : (ArrayList<IBasicDTO>)ministryList) {
                if (!((ITreeDTO)element).getTreeLevel().equals(ISystemConstant.FIRST_LEVEL_IN_TREE)) {

                    key = ((ITreeDTO)element).getParentCode().getKey();
                    ITreeDTO parent = (ITreeDTO)allElement.get(key);
                    ((ITreeDTO)element).setParentObject(parent);

                    if (parent != null && (parent.getChildernNumbers() != null) && (parent.getChildernNumbers() == 1))
                        ministryTreeList.add((element));

                    else if (parent != null && (parent.getChildernNumbers() != null) &&
                             (parent.getChildernNumbers() > 1)) {

                        String spaceString = "";
                        //String spaceString = "Â Â Â Â Â Â­Â­Â»Â ";

                        if (appended_char != null && appended_char.trim().length() != 0) {
                            if (((ITreeDTO)element).getTreeLevel() != null)
                                for (int i = 0; i < ((ITreeDTO)element).getTreeLevel(); i++)
                                    spaceString += appended_char;
                        }
                        String name = spaceString + element.getName();
                        element.setName(name);
                        int index = -1;
                        index = ministryTreeList.indexOf(parent);
                        if (index != -1) {
                            index += 1;
                            ministryTreeList.add(index, element);
                        }

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ministryTreeList;
    }

    /** update the pageIndex, called from DataScroller component
     * @author MLotfy
     * @param event
     */
    //Paging
    public void changePageIndex(ActionEvent event) {

        updateMyPagedDataModel = true;

        ScrollerActionEvent scrollerAction = (ScrollerActionEvent)event;

        HtmlDataScroller scroller = (HtmlDataScroller)scrollerAction.getComponent();
        if (scrollerAction.getPageIndex() >
            0) { // in case user clicked on page_number(1, 2, ...) not arrow("next", "previous", ...)

            currentPageIndex = scrollerAction.getPageIndex();

        } else { // in case user clicked on arrow("next", "previous", ...) not page_number(1, 2, ...)

            String scrollerFacet = scrollerAction.getScrollerfacet();
            int pagesCount = scroller.getPageCount();
            int fastStep = scroller.getFastStep();


            boolean canChangePageIndex = true;

            if (oldPageIndex == 1 || oldPageIndex == 0) {

                if (scrollerFacet.equals(FIRST) || scrollerFacet.equals(PREVIOUS) ||
                    scrollerFacet.equals(FASTREWIND)) {

                    canChangePageIndex = false;
                }

            } else if (oldPageIndex == pagesCount) {

                if (scrollerFacet.equals(NEXT) || scrollerFacet.equals(LAST) || scrollerFacet.equals(FASTFORWARD)) {

                    canChangePageIndex = false;
                }

            }

            if (oldPageIndex == 0) {
                ++oldPageIndex;
            }

            if (canChangePageIndex) {

                if (scrollerFacet.equals(FIRST)) {
                    currentPageIndex = 1;
                } else if (scrollerFacet.equals(NEXT)) {
                    currentPageIndex = oldPageIndex + 1;
                } else if (scrollerFacet.equals(FASTFORWARD)) {

                    if (oldPageIndex + fastStep > pagesCount) {
                        currentPageIndex = pagesCount;
                        scroller.getUIData().setFirst(((currentPageIndex - 1) * getSharedUtil().getNoOfTableRows()));
                    } else {
                        currentPageIndex = oldPageIndex + fastStep;
                    }

                } else if (scrollerFacet.equals(PREVIOUS)) {
                    currentPageIndex = oldPageIndex - 1;
                } else if (scrollerFacet.equals(LAST)) {
                    currentPageIndex = pagesCount;
                } else if (scrollerFacet.equals(FASTREWIND)) {

                    if (oldPageIndex - fastStep < 1) {
                        currentPageIndex = 1;
                    } else {
                        currentPageIndex = oldPageIndex - fastStep;
                    }

                }

            }
        }
        //System.err.println(": " + ((currentPageIndex-1) * getSharedUtil().getNoOfTableRows()));
        oldPageIndex = currentPageIndex;

    }

    //Paging

    public void resetPageIndex() {

        if (sorting) {

            if (oldPageIndex > getPagingBean().getPagesCount()) {

                setCurrentPageIndex(1);
                setOldPageIndex(0);
                getMyDataTable().setFirst(0);

            }

        } else {

            setCurrentPageIndex(1);
            setOldPageIndex(0);
            getMyDataTable().setFirst(0);

        }

    }

    //Paging

    /**
     * This method is used in case the developer wants to create
     * a PagingRequestDTO to reposition a data table at
     * a specific index using a pageDTO key.
     *
     * @param repositionKey pageDTO key where the developer wants to reposition data table at it.
     */
    public void generatePagingRequestDTO(String repositionKey) {

        setUpdateMyPagedDataModel(true);
        setRepositionTable(true);

        if (pagingRequestDTO == null) {
            pagingRequestDTO = new PagingRequestDTO();
        }

        pagingRequestDTO.setRepositionKey(repositionKey);

    }

    public void generatePagingRequestDTO(String beanName, String methodName) {

        setUpdateMyPagedDataModel(true);
        pagingRequestDTO = new PagingRequestDTO(beanName, methodName);

    }

    public void generatePagingRequestDTO(String beanName, String methodName, IBasicDTO searchDTO) {

        setUpdateMyPagedDataModel(true);
        pagingRequestDTO = new PagingRequestDTO(beanName, methodName, searchDTO);

    }

    //Paging

    public List getTotalList() {

        List totalList = null;
        if (client != null) {
            try {

                IBasicDTO filterDTO = getFilterDTO();

                if (filterDTO == null) {
                    totalList = client.getAll();
                } else {
                    totalList = client.getAll(filterDTO);
                }

            } catch (SharedApplicationException ne) {
                totalList = new ArrayList();
                ne.printStackTrace();
            } catch (DataBaseException db) {
                getSharedUtil().handleException(db);
            } catch (Exception e) {
                getSharedUtil().handleException(e);
            }
        }
        return totalList;

    }

    public void setEmpListOfValues(BaseBean empListOfValues) {
        this.empListOfValues = empListOfValues;
    }

    public BaseBean getEmpListOfValues() {
        return empListOfValues;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setOldPageIndex(int oldPageIndex) {
        this.oldPageIndex = oldPageIndex;
    }

    public int getOldPageIndex() {
        return oldPageIndex;
    }

    public void setSorting(boolean sorting) {
        this.sorting = sorting;
    }

    public boolean isSorting() {
        return sorting;
    }

    public void setUsingPaging(boolean usingPaging) {
        this.usingPaging = usingPaging;
    }

    public boolean isUsingPaging() {
        return usingPaging;
    }

    public void setUpdateMyPagedDataModel(boolean updateMyPagedDataModel) {
        this.updateMyPagedDataModel = updateMyPagedDataModel;
    }

    public boolean isUpdateMyPagedDataModel() {
        return updateMyPagedDataModel;
    }

    public void setRepositionTable(boolean repositionTable) {
        this.repositionTable = repositionTable;
    }

    public boolean isRepositionTable() {
        return repositionTable;
    }

    public void setSortedTable(boolean sortedTable) {
        this.sortedTable = sortedTable;
    }

    public boolean isSortedTable() {
        return sortedTable;
    }

    public void setResettedPageIndex(boolean resettedPageIndex) {
        this.resettedPageIndex = resettedPageIndex;
    }

    public boolean isResettedPageIndex() {
        return resettedPageIndex;
    }

    public void setPagingRequestDTO(PagingRequestDTO pagingRequestDTO) {
        this.pagingRequestDTO = pagingRequestDTO;
    }

    public PagingRequestDTO getPagingRequestDTO() {
        return pagingRequestDTO;
    }

    public void setPagingBean(PagingBean pagingBean) {
        this.pagingBean = pagingBean;
    }

    public PagingBean getPagingBean() {

        if (pagingBean == null) {
            pagingBean = new PagingBean(this);
        }

        return pagingBean;

    }

    public void setTitlePage(String titlePage) {
        this.titlePage = titlePage;
    }

    public String getTitlePage() {
        try {
            String key =
                (((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getAttribute("titlepage")).toString();
            titlePage =
                    ((SharedUtilBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util}").getValue(FacesContext.getCurrentInstance())).getDynamicValue(key).toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return titlePage;
    }

    public void setSingleSelection(boolean singleSelection) {
        this.singleSelection = singleSelection;
    }

    public boolean isSingleSelection() {
        return singleSelection;
    }

    public void setPageMode(int pageMode) {
        this.pageMode = pageMode;
    }

    public int getPageMode() {
        return pageMode;
    }

    public void setUsingBsnPaging(boolean usingBsnPaging) {
        this.usingBsnPaging = usingBsnPaging;
    }

    public boolean isUsingBsnPaging() {
        return usingBsnPaging;
    }

    public void setBsnSortingColumnName(String bsnSortingColumnName) {
        this.bsnSortingColumnName = bsnSortingColumnName;
    }

    public String getBsnSortingColumnName() {
        return bsnSortingColumnName;
    }
    // new style for the shortcut div
    private String lookupshortCutDiv = "divSearch";

    public void setLookupshortCutDiv(String lookupshortCutDiv) {
        this.lookupshortCutDiv = lookupshortCutDiv;
    }

    public String getLookupshortCutDiv() {
        return lookupshortCutDiv;
    }


    public void setEmpLovDivTitleKey(String empLovDivTitleKey) {
        this.empLovDivTitleKey = empLovDivTitleKey;
    }

    public String getEmpLovDivTitleKey() {
        return empLovDivTitleKey;
    }

    public void setLovDivTitleKey(String lovDivTitleKey) {
        this.lovDivTitleKey = lovDivTitleKey;
    }

    public String getLovDivTitleKey() {
        return lovDivTitleKey;
    }

    public void setTreeDivTitleKey(String treeDivTitleKey) {
        this.treeDivTitleKey = treeDivTitleKey;
    }

    public String getTreeDivTitleKey() {
        return treeDivTitleKey;
    }

    public void setEditDivTitleKey(String editDivTitleKey) {
        this.editDivTitleKey = editDivTitleKey;
    }

    public String getEditDivTitleKey() {
        return editDivTitleKey;
    }

    public void setEditDivTitleKeyFlage(boolean editDivTitleKeyFlage) {
        this.editDivTitleKeyFlage = editDivTitleKeyFlage;
    }

    public boolean isEditDivTitleKeyFlage() {
        return editDivTitleKeyFlage;
    }

    public void setAddDivTitleKeyFlage(boolean addDivTitleKeyFlage) {
        this.addDivTitleKeyFlage = addDivTitleKeyFlage;
    }

    public boolean isAddDivTitleKeyFlage() {
        return addDivTitleKeyFlage;
    }

    public void setAddDivTitleKey(String addDivTitleKey) {
        this.addDivTitleKey = addDivTitleKey;
    }

    public String getAddDivTitleKey() {
        return addDivTitleKey;
    }

    public void setDeleteDivTitleKey(String deleteDivTitleKey) {
        this.deleteDivTitleKey = deleteDivTitleKey;
    }

    public String getDeleteDivTitleKey() {
        return deleteDivTitleKey;
    }

    public void setMasterDeailTitleKey(String masterDeailTitleKey) {
        this.masterDeailTitleKey = masterDeailTitleKey;
    }

    public String getMasterDeailTitleKey() {
        return masterDeailTitleKey;
    }

    public void setCustomDiv1TitleKey(String customDiv1TitleKey) {
        this.customDiv1TitleKey = customDiv1TitleKey;
    }

    public String getCustomDiv1TitleKey() {
        return customDiv1TitleKey;
    }

    public void setCustomDiv2TitleKey(String customDiv2TitleKey) {
        this.customDiv2TitleKey = customDiv2TitleKey;
    }

    public String getCustomDiv2TitleKey() {
        return customDiv2TitleKey;
    }

    public void setIndexOfSelectedDataInDataTable(int indexOfSelectedDataInDataTable) {
        this.indexOfSelectedDataInDataTable = indexOfSelectedDataInDataTable;
    }

    public int getIndexOfSelectedDataInDataTable() {
        return indexOfSelectedDataInDataTable;
    }

    public void setSearchDivTitleKey(String searchDivTitleKey) {
        this.searchDivTitleKey = searchDivTitleKey;
    }

    public String getSearchDivTitleKey() {
        return searchDivTitleKey;
    }

    public void setSearchDivTitleKeyFlage(boolean searchDivTitleKeyFlage) {
        this.searchDivTitleKeyFlage = searchDivTitleKeyFlage;
    }

    public boolean isSearchDivTitleKeyFlage() {
        return searchDivTitleKeyFlage;
    }

    public void setDataTableSearchResult(List dataTableSearchResult) {
        this.dataTableSearchResult = dataTableSearchResult;
    }

    public List getDataTableSearchResult() {
        return dataTableSearchResult;
    }

    public void setSearchResultSize(int searchResultSize) {
        this.searchResultSize = searchResultSize;
    }

    public int getSearchResultSize() {
        return searchResultSize;
    }

    public void setCurrentSelectedSearchIndex(int currentSelectedSearchIndex) {
        this.currentSelectedSearchIndex = currentSelectedSearchIndex;
    }

    public int getCurrentSelectedSearchIndex() {
        return currentSelectedSearchIndex;
    }

    public void setAddTreeDivTitleKeyFlage(boolean addTreeDivTitleKeyFlage) {
        this.addTreeDivTitleKeyFlage = addTreeDivTitleKeyFlage;
    }

    public boolean isAddTreeDivTitleKeyFlage() {
        return addTreeDivTitleKeyFlage;
    }

    public void setEditTreeDivTitleKeyFlage(boolean editTreeDivTitleKeyFlage) {
        this.editTreeDivTitleKeyFlage = editTreeDivTitleKeyFlage;
    }

    public boolean isEditTreeDivTitleKeyFlage() {
        return editTreeDivTitleKeyFlage;
    }

    public void setAddTreeDivTitleKey(String addTreeDivTitleKey) {
        this.addTreeDivTitleKey = addTreeDivTitleKey;
    }

    public String getAddTreeDivTitleKey() {
        return addTreeDivTitleKey;
    }

    public void setEditTreeDivTitleKey(String editTreeDivTitleKey) {
        this.editTreeDivTitleKey = editTreeDivTitleKey;
    }

    public String getEditTreeDivTitleKey() {
        return editTreeDivTitleKey;
    }


    public void setUsingPortal(Boolean usingPortal) {
        this.usingPortal = usingPortal;
    }

    public Boolean getUsingPortal() {
        usingPortal = getSharedUtilBeanPortal().isEnabledPortal();
        if (usingPortal == null) {
            usingPortal = false;
        }
        return usingPortal;
    }

    /**
     * ***********************  START SORTING DATA TABLE ***********************
     *                                                                         *
     * @Desc Moving Up/Down of Data Table records by arrows in a column of it. *
     *                                                                         *
     * @Author MLotfy                                                          *
     *                                                                         *
     * *************************************************************************
     **/

    /**
     * set parameters needed to apply moving up/down of data table records.
     *
     * @param dataTableDTOOrderMethodName name of method that set order of DTO that represents a record in the data table.
     * @param dataTableDTOOrderMethodPramTypes types of input parameters of the order method.
     *
     * @Author MLotfy
     */

    public void initDataTableOrder(String dataTableDTOOrderMethodName, Class[] dataTableDTOOrderMethodPramTypes) {

        this.dataTableDTOOrderMethodName = dataTableDTOOrderMethodName;
        this.dataTableDTOOrderMethodPramTypes = dataTableDTOOrderMethodPramTypes;
    }

    public void moveUp() {

        //        System.out.println("Moving up data tabel element...........");

        int rowIndex = getMyDataTable().getRowIndex();
        if (rowIndex > 0) {
            swap(rowIndex, rowIndex - 1);
        }
    }

    public void moveDown() {

        //        System.out.println("Moving down data tabel element.........");

        int rowIndex = getMyDataTable().getRowIndex();
        try {
            if (rowIndex < getMyTableData().size() - 1) {
                swap(rowIndex, rowIndex + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveFirst() {

        //        System.out.println("Moving data tabel element to first...........");

        int rowIndex = getMyDataTable().getRowIndex();
        if (rowIndex > 0) {
            List<IBasicDTO> dataList = null;
            try {
                dataList = (List<IBasicDTO>)getMyTableData();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
            for (int i = rowIndex; i > 0; --i) {
                swap(i, i - 1, dataList);
            }
            reOrderList(dataList);
        }
    }

    public void moveLast() {

        //        System.out.println("Moving data tabel element to last...........");

        int rowIndex = getMyDataTable().getRowIndex();

        List<IBasicDTO> dataList = null;
        try {
            dataList = (List<IBasicDTO>)getMyTableData();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }

        int listSize = dataList.size();
        if (rowIndex < listSize - 1) {
            for (int i = rowIndex; i < listSize - 1; ++i) {
                swap(i, i + 1, dataList);
            }
            reOrderList(dataList);
        }
    }

    public void reOrderList(List<IBasicDTO> dataList) {

        if (!(dataList == null || dataList.isEmpty())) {

            long index = 1;
            String dataTableDTOName = dataList.get(0).getClass().getName();
            for (IBasicDTO dto : dataList) {
                try {
                    Class cls = Class.forName(dataTableDTOName);
                    if (cls != null) {
                        try {

                            Method method =
                                cls.getMethod(dataTableDTOOrderMethodName, dataTableDTOOrderMethodPramTypes);

                            dataTableDTOOrderMethodArgslist[0] = index++;

                            method.invoke(dto, dataTableDTOOrderMethodArgslist);

                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

        }
    }

    protected void swap(int index1, int index2, List<IBasicDTO> dataList) {
        if (!(dataList == null || dataList.isEmpty())) {
            IBasicDTO obj2 = dataList.get(index2);
            dataList.set(index2, dataList.get(index1));
            dataList.set(index1, obj2);
        }
    }

    protected void swap(int index1, int index2) {

        List<IBasicDTO> dataList = null;

        try {
            dataList = (List<IBasicDTO>)getMyTableData();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }

        swap(index1, index2, dataList);
        reOrderList(dataList);

    }

    /***************************  END SORTING DATA TABLE **********************/
    public void setTreeDivStyle(String treeDivStyle) {
        this.treeDivStyle = treeDivStyle;
    }

    public String getTreeDivStyle() {
        return treeDivStyle;
    }

    public void setSaveSortingState(boolean saveSortingState) {
        this.saveSortingState = saveSortingState;
    }

    public boolean isSaveSortingState() {
        return saveSortingState;
    }

    /////////////// new solution of show and hide of Column ///////////////////////

    public void setIndexArray(boolean[] indexArray) {
        this.indexArray = indexArray;
    }

    public boolean[] getIndexArray() {
        return indexArray;
    }


    public void showHideColumnByIndex(ActionEvent event) {

        String[] indexColum = event.getComponent().getId().split("_");
        int indexOfColum = Integer.parseInt(indexColum[indexColum.length - 2]);
        swapColumnVisability(indexOfColum);
    }

    public void swapColumnVisability(int index) {
        if (getIndexArray()[index]) {
            getIndexArray()[index] = false;
            //indexArray.set(index,false);

        } else {
            getIndexArray()[index] = true;

        }

    }

    //////////////////////////// End new solution of show and hide of Column ///////////////////////////////////////

    public void setSubtitle(boolean subtitle) {
        this.subtitle = subtitle;
    }

    public boolean isSubtitle() {
        return subtitle;
    }


    public void setClickedRowIndex(int clickedRowIndex) {
        this.clickedRowIndex = clickedRowIndex;
    }

    public int getClickedRowIndex() {
        return clickedRowIndex;
    }

    public void setSelectionComponentType(String selectionComponentType) {
        this.selectionComponentType = selectionComponentType;
    }

    public String getSelectionComponentType() {
        return selectionComponentType;
    }

    //
    //TODO locking code
    //

    public void setLastLockingAction(String lastLockingAction) {
        this.lastLockingAction = lastLockingAction;
    }

    public String getLastLockingAction() {
        return lastLockingAction;
    }

    /**
     * This is a fake method for binding this attribute with the hidden field
     * @param lockingFailed
     */
    public void setLockingFailed(String lockingFailed) {
    }

    public String getLockingFailed() {
        return lockingFailed;
    }

    /**
     * checks if the locking is enabled for the current item or not
     * @return
     */
    protected boolean isLockingEnabled() {
        //TODO enable Locking
        /*ILockableItem lockingItem = getLockingItem();
        return lockingItem == null ? false : LockingHelper.isLockingEnabled(lockingItem);*/
        return false;
    }

    /**
     * gets the item that will be used by the locking methods
     * @return the item that will be locked
     */
    protected ILockableItem getLockingItem() {
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0) {
            IBasicDTO dto = getSelectedDTOS().get(0);
            if (dto instanceof ILockableItem) {
                return (ILockableItem)dto;
            }
        }
        return null;
    }

    /**
     * create a locking request from insureLocked/unlock methods
     * @return
     */
    protected ILockingRequestDTO createLockingRequest() {
        return createLockingRequest(lastLockingAction);
    }

    /**
     * create a locking request for the lock method
     * @param action
     * @return
     */
    protected ILockingRequestDTO createLockingRequest(String action) {

        return LockingHelper.createRequest(getLockingItem(), action);
    }

    /**
     * locks the item that was returned from getLockingItem() method
     * with the default action/timeout
     * @return true if the item was locked, otherwise it will returns false
     * and handles the locking exception to show its message to the user
     */
    protected boolean lock() {
        return lock(DEF_LOCKING_ACTION);
    }

    /**
     * locks the item that was returned from getLockingItem() method
     * @return true if the item was locked, otherwise it will returns false
     * and handles the locking exception to show its message to the user
     */
    protected boolean lock(String action) {
        // if locking not enabled simulate that the item was locked
        if (!isLockingEnabled()) {
            return true;
        }

        try {
            getClient().lock(createLockingRequest(action));
        } catch (Exception e) {
            // set 1 to indicate that the locking was failed
            // this value is checked in executeAfterLock javascript function
            // in Utils.js, and saved in a hidden field in msg.jsp
            lockingFailed = "1";
            getSharedUtil().handleException(e);
            return false;
        }

        // save the last action to be used
        // with insureLocked and unlock methods
        lastLockingAction = action;

        return true;
    }

    /**
     * insures if the locking item is already locked or not
     * @return true if the item is already locked, otherwise it will returns
     * false and resets the locking status(lastLockingAction) to indicate
     * that we aren't holding a lock for the locking item, and handles the locking exception
     * to show its message to the user
     * @note You must cancel the current operation if this method returns false
     * because the lock was removed from the locking server
     */
    protected boolean insureLocked() {
        // if locking not enabled simulate that the item is already locked
        if (!isLockingEnabled()) {
            return true;
        }

        try {
            getClient().insureLocked(createLockingRequest());
        } catch (Exception e) {
            // reset the last action
            // because the locked item was removed from the locking server
            lastLockingAction = "";
            getSharedUtil().handleException(e);
            return false;
        }

        return true;
    }

    /**
     * unlocks the locking item, and resets the locking status(lastLockingAction)
     */
    public void unlock() {
        // if locking not enabled simulate that the item was unlocked
        if (!isLockingEnabled()) {
            return;
        }

        try {
            getClient().unlock(createLockingRequest());
        } catch (Exception e) {
            ;
        }

        lastLockingAction = "";

    }

    public void openSearchDiv() {
        if (!isSearchMode()) {
            this.setSearchQuery("");
            this.setSearchType(0);
        }
    }

    public void setCustomDiv3(String customDiv3) {
        this.customDiv3 = customDiv3;
    }

    public String getCustomDiv3() {
        return customDiv3;
    }

    public void setCustomDiv3TitleKey(String customDiv3TitleKey) {
        this.customDiv3TitleKey = customDiv3TitleKey;
    }

    public String getCustomDiv3TitleKey() {
        return customDiv3TitleKey;
    }

    public void setIntegrationDiv1(String integrationDiv1) {
        this.integrationDiv1 = integrationDiv1;
    }

    public String getIntegrationDiv1() {
        return integrationDiv1;
    }

    public void setIntegrationDiv2(String integrationDiv2) {
        this.integrationDiv2 = integrationDiv2;
    }

    public String getIntegrationDiv2() {
        return integrationDiv2;
    }

    public void setIntegrationDiv1TitleKey(String integrationDiv1TitleKey) {
        this.integrationDiv1TitleKey = integrationDiv1TitleKey;
    }

    public String getIntegrationDiv1TitleKey() {
        return integrationDiv1TitleKey;
    }

    public void setIntegrationDiv2TitleKey(String integrationDiv2TitleKey) {
        this.integrationDiv2TitleKey = integrationDiv2TitleKey;
    }

    public String getIntegrationDiv2TitleKey() {
        return integrationDiv2TitleKey;
    }

    /*Message div solution*/

    public void setMsgDivTimeoutPeriod(Long msgDivTimeoutPeriod) {
        this.msgDivTimeoutPeriod = msgDivTimeoutPeriod;
    }

    public Long getMsgDivTimeoutPeriod() {
        if (msgDivTimeoutPeriod != null && msgDivTimeoutPeriod > 0) {
            return msgDivTimeoutPeriod;
        } else {
            return 0L;
        }
    }

    public void setSearchTreeDivBean(LOVBaseBean searchTreeDivBean) {
        this.searchTreeDivBean = searchTreeDivBean;
    }

    public LOVBaseBean getSearchTreeDivBean() {
        return searchTreeDivBean;
    }

    /**
     * Purpose: this method is used to handle return from add/edit page to list page,
     * keeping on the same pageIndex and selected radio
     * Creation/Modification History :
     * 1.1 - Developer Name: A.Agamy
     * 1.2 - Date:  22/09/2014
     * @param beanName
     */
    public void returnOnSelectedPage(String beanName) {
        try {
            BaseBean listBean = (BaseBean)evaluateValueBinding(beanName);
            List<IBasicDTO> list = (List<IBasicDTO>)SharedUtilBean.deepCopyObject(listBean.getSelectedDTOS());
            if (listBean.isUsingPaging()) {

                listBean.setUpdateMyPagedDataModel(true);
                listBean.setRepositionTable(true);

                PagingRequestDTO requestDTO = listBean.getPagingRequestDTO();
                if (requestDTO == null) {
                    requestDTO = new PagingRequestDTO();
                }
                requestDTO.setRepositionKey(listBean.getSelectedDTOS().get(0).getCode().getKey());
                if (listBean.isAddWithoutSelect()) {
                    listBean.getSelectedDTOS().clear();
                    listBean.setSelectedRadio("");
                }
                listBean.setPagingRequestDTO(requestDTO);

            } else {
                listBean.getPageIndex(listBean.getSelectedDTOS().get(0).getCode().getKey(), null);
            }

            if (!listBean.isAddWithoutSelect()) {
                listBean.setSelectedDTOS(list);
                listBean.setSelectedRadio(list.get(0).getCode().getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAddWithoutSelect(boolean addWithoutSelect) {
        this.addWithoutSelect = addWithoutSelect;
    }

    public boolean isAddWithoutSelect() {
        return addWithoutSelect;
    }

    public void setDataTableFirst(int dataTableFirst) {
        this.dataTableFirst = dataTableFirst;
    }

    public int getDataTableFirst() {
        return dataTableFirst;
    }

    public void setMsgDivHeight(Long msgDivHeight) {
        this.msgDivHeight = msgDivHeight;
    }

    public Long getMsgDivHeight() {
        return msgDivHeight;
    }

    public String getMsgDivHeightStyle() {
        if (msgDivHeight != null && msgDivHeight > 0) {
            return "height: " + msgDivHeight + "px !important;";
        }
        return "";
    }

    public void setContentDiv(String contentDiv) {
        this.contentDiv = contentDiv;
    }

    public String getContentDiv() {
        return contentDiv;
    }

    public void setConditionActivationIntgDivStyleClass(String conditionActivationIntgDivStyleClass) {
        this.conditionActivationIntgDivStyleClass = conditionActivationIntgDivStyleClass;
    }

    public String getConditionActivationIntgDivStyleClass() {
        return conditionActivationIntgDivStyleClass;
    }

    public void setConditionActivationIntgDivTitleKey(String conditionActivationIntgDivTitleKey) {
        this.conditionActivationIntgDivTitleKey = conditionActivationIntgDivTitleKey;
    }

    public String getConditionActivationIntgDivTitleKey() {
        return conditionActivationIntgDivTitleKey;
    }

    public void setRetroactiveSettDetailsDivStyleClass(String retroactiveSettDetailsDivStyleClass) {
        this.retroactiveSettDetailsDivStyleClass = retroactiveSettDetailsDivStyleClass;
    }

    public String getRetroactiveSettDetailsDivStyleClass() {
        return retroactiveSettDetailsDivStyleClass;
    }

    public void setRetroactiveSettDetailsDivTitleKey(String retroactiveSettDetailsDivTitleKey) {
        this.retroactiveSettDetailsDivTitleKey = retroactiveSettDetailsDivTitleKey;
    }

    public String getRetroactiveSettDetailsDivTitleKey() {
        return retroactiveSettDetailsDivTitleKey;
    }

    public void setProgressMsgVal(String progressMsgVal) {
        this.progressMsgVal = progressMsgVal;
    }

    public String getProgressMsgVal() {
        return progressMsgVal;
    }

    public void initiateBeanOnce() {
    }
    /*
    * @author TechnicalTeam[D.AbdeLHady]
    * @since 04/08/2015
    * @param Pagging
    * @return
    * @throws DAOException
    * @Note used enhance Pagging code
    */

    public void setAskForSorting(boolean askForSorting) {
        this.askForSorting = askForSorting;
    }

    public boolean isAskForSorting() {
        return askForSorting;
    }

    public void setPagingResponseDTO(IPagingResponseDTO pagingResponseDTO) {
        this.pagingResponseDTO = pagingResponseDTO;
    }

    public IPagingResponseDTO getPagingResponseDTO() {
        return pagingResponseDTO;
    }

    public void setRecordAdded(boolean recordAdded) {
        this.recordAdded = recordAdded;
    }

    public boolean isRecordAdded() {
        return recordAdded;
    }

    public void setSearchDTO(IBasicDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public IBasicDTO getSearchDTO() {
        return searchDTO;
    }
}
