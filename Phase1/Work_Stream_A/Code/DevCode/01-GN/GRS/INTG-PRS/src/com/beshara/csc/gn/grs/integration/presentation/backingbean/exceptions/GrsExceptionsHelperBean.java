package com.beshara.csc.gn.grs.integration.presentation.backingbean.exceptions;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.ExceptionsSearchCriteriaDTO;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionRelationsDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.dto.IExceptionsDTO;
import com.beshara.csc.gn.grs.business.dto.IExceptionsSearchCriteriaDTO;
import com.beshara.csc.gn.grs.business.shared.IGrsConstants;
import com.beshara.csc.gn.grs.integration.presentation.backingbean.conditions.ConditionDetailsBean;
import com.beshara.csc.gn.grs.integration.presentation.backingbean.joincond.JoinConditionHelperBean;
import com.beshara.csc.gn.sec.business.client.SecClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;


/**
 * #Integration Mode
 *
 * #Using: Search For Exceptions
 * #search Criteria Mandatory: App Module, Transaction name, Condition
 * #search criteria optional: parameters

 *
 * @version 1.1
 * @author Heba.Ahmed A.almasry A.abdrabo A.sakr A.abdelhalim
 * modified by I.Omar
 * @since 06/04/2015
 */

public class GrsExceptionsHelperBean extends LookUpBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    /**************************************  CONSTANTS **********************************************************/

    public final String EMPTY_DEFAULT_VALUE = "";

    private final static String EXCEPTION_BY_CONDITION = "1";
    private final static String EXCEPTION_BY_LINE = "2";
    private static final String BUNDLE_NAME = "com.beshara.csc.gn.grs.presentation.resources.grs";
    
    
    private String viewDetailsPageURL;
    
    private JoinConditionHelperBean jcHelperBean;
    
    
    private String BEAN_NAME = "grsExceptionHelperBean";
    Map<String, String> temp = new HashMap();
 
    /**************************************  mandatory variables  **********************************************************/

    private Integer searchEmpResult;
    private String appModuleKey; // allow null for grs only
    private String selectedRadioValue;
    private boolean showLines;
    
    private boolean searchTransactions = false;
    private boolean searchConditions = false;
    private boolean linesButtonsEnabled = false;
    private boolean transactionsButtonsEnabled = false;
    private boolean conditionsButtonsEnabled = false;
    
    private boolean enableViewButton;

    /**************************************  filters variables  **********************************************************/
    
    private String selectedAppModuleKey = getVirtualConstValue();
    private List<IBasicDTO> appModuleList = new ArrayList<IBasicDTO>();

    private String selectedTransactionKey = getVirtualConstValue();
    private List<IBasicDTO> transactionList = new ArrayList<IBasicDTO>();

    private String selectedConditionsKey = getVirtualConstValue();
    private List<IBasicDTO> conditionsList = new ArrayList<IBasicDTO>();

    private List<IBasicDTO> availableLinesList = new ArrayList<IBasicDTO>();
    
    private List<String> toBeAddedLinesList = new ArrayList<String>();
    private List<IBasicDTO> addedLinesList = new ArrayList<IBasicDTO>();
    private List<String> toBeRemovedLinesList = new ArrayList<String>();

    private boolean loadLinesList = true;    



    /**************************************  SearchDTO & search criteria  **********************************************************/

     IExceptionsSearchCriteriaDTO searchCriteriaDTO = new ExceptionsSearchCriteriaDTO();
    
    private boolean viewMode;

    /**************************************  Constructors & initial Methods **********************************************************/
    public GrsExceptionsHelperBean() {
        super();
        getSharedUtil().init();
        setSelectedRadioValue(EXCEPTION_BY_CONDITION);
        setClient(GrsClientFactory.getExceptionsClient());
        setPageDTO(GrsDTOFactory.createExceptionsDTO());
        initLoveBaseBean();
        initJcHelperBean();
        viewMode = false;
        setMyTableData(new ArrayList<IBasicDTO>());
    }
    private void initJcHelperBean(){
        if(jcHelperBean == null){
            setJcHelperBean(new JoinConditionHelperBean());
            jcHelperBean.setContainerBeanName(BEAN_NAME);
        }
    }
    
    @Override
    public void initiateBeanOnce(){
        if(appModuleKey != null || !appModuleKey.equals("")){
        fillTransactionList();
        }
        }
    private void initLoveBaseBean(){
        if(getLovBaseBean()==null){
            setLovBaseBean(new LOVBaseBean());
            getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
            getLovBaseBean().setSearchTyp("0");
            getLovBaseBean().setCancelSearchMethod(BEAN_NAME + ".cancelSearchObjectTypeLovDiv");
        }
    }
     
    
    public void radioValueChanged(ActionEvent e) {
           if(EXCEPTION_BY_LINE.equals( getSelectedRadioValue())){
               setShowLines(true); 
           }else{
               setShowLines(false);
           }
       }
    
    public SharedUtilBean getSharedUtil() {
        return SharedUtilBean.getInstance();
    }

    public static GrsExceptionsHelperBean getInstance() {
        return (GrsExceptionsHelperBean)JSFHelper.getValue("grsExceptionHelperBean");
    }
    public String getExceptionByCondition() {
            return EXCEPTION_BY_CONDITION;
        }

        public String getExceptionByLine() {
            return EXCEPTION_BY_LINE;
        }

    
    /**************************************  Search Methods **********************************************************/ 
     public void reEnterAgain()  {
        viewMode = false;  
        selectedAppModuleKey = getVirtualConstValue();
        resetLovDiv();
        resetTransactions();
        setMyTableData(new ArrayList());
    }

    public void search() throws DataBaseException, NoResultException {
        if(!getSelectedConditionsKey().equals(getVirtualConstValue())){
            try {
                IExceptionsSearchCriteriaDTO searchDTO = GrsDTOFactory.createExceptionsSearchCriteriaDTO();
                searchDTO.setConditionCode(Long.valueOf(selectedConditionsKey));
                if(showLines){
                    searchDTO.setConditionLinesList(addedLinesList);
                }
                setMyTableData(GrsClientFactory.getExceptionsClient().getAllByConditionCodeAndLinesList(searchDTO));
                if(getMyTableData().size()> 0 && !viewMode)
                    viewMode = true;
            } catch (Exception e) {
                e.printStackTrace();
                setMyTableData(new ArrayList());
            }    
        }else{
            setMyTableData(new ArrayList());
        }
        
    }

    /**
     * reset Only Basic search criteria
     */

    private void resetExceptionsCriteriaDTO() {
        getSearchCriteriaDTO().setAppModuleCode(null);
        getSearchCriteriaDTO().setRelDesc(null);
        getSearchCriteriaDTO().setConditionCode(null);
        getSearchCriteriaDTO().setConditionName(null);
        getSearchCriteriaDTO().setConditionLinesList(null);
    }

    /**************************************  APP LAYOUT  ***********************************************************************************/

    /**
     * Mandatory Part to Be Added to tiles-def when apply
     * Reference: GRS Tiles Defenition: "grsExceptionIntegration.page"
     *
     */


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyListPage();
        app.setShowCustomDiv2(true);
        app.setShowContent1(true);
        app.setShowLovDiv(true);
        app.setShowpaging(true);
        return app;
    }

    /**************************************  GO ADD ***********************************************************************************/

    /**************************************  OPEN SEARCH DIVS ********************************************************************************/

    /**************************************  OPEN SEARCH APP MODULE DIV ***************************************************************/
   
    public String openSearchDiv1() {
        resetLovDiv();
        getLovBaseBean().setReturnMethodName(BEAN_NAME + ".returnAppModulesMethod");
        getLovBaseBean().setSearchMethod(BEAN_NAME + ".searchAppModulesLovDiv");
        getLovBaseBean().setCancelSearchMethod(BEAN_NAME + ".cancelSearchObjectTypeLovDiv");
        
        getLovBaseBean().getOkCommandButton()
        
            .setReRender("systemFilterationId,systemListId,lov_dataT_data_panel,lov_searchPanel,lov_searchPanel_radio,operationListId,errorCodeId1,conditionFilterationId,conditionListId,linesPanelId,errorCodeId3,okLoveButton2,okLoveButton3");
        return "";
    }

    /**************************************  OPEN SEARCH TRANSACTION DIV **************************************************************/
    public String openSearchDiv2() {

        resetLovDiv();
        setSearchTransactions(true);
        
        getLovBaseBean().setSearchTyp("1");
        getLovBaseBean().setReturnMethodName(BEAN_NAME + ".returnTransactionsMethod");
        getLovBaseBean().setSearchMethod(BEAN_NAME + ".searchTransactionsLovDiv");
        getLovBaseBean().setCancelSearchMethod(BEAN_NAME + ".cancelSearchTransactionLovDiv");
        getLovBaseBean().getOkCommandButton().setReRender("lov_dataT_data_panel,lov_searchPanel,lov_searchPanel_radio,operationListId,conditionFilterationId,conditionListId,linesPanelId,errorCodeId3,okLoveButton3");
        return "";
    }

    /**************************************  OPEN SEARCH CONDITION DIV ****************************************************************/

    public String openSearchDiv3() {
        resetLovDiv();
        setSearchConditions(true);
        getLovBaseBean().setReturnMethodName(BEAN_NAME + ".returnConditionsMethod");
        getLovBaseBean().setSearchMethod(BEAN_NAME + ".searchConditionsLovDiv");
        getLovBaseBean().setCancelSearchMethod(BEAN_NAME + ".cancelSearchObjectTypeLovDiv");
        getLovBaseBean().getOkCommandButton().setReRender("lov_dataT_data_panel,lov_searchPanel,lov_searchPanel_radio,conditionFilterationId,conditionListId,linesPanelId,errorCodeId3,okLoveButton3,myForm:content_details,OperationBar,myForm:viewTable");
        return "";
    }

    /**************************************  SEARCH METHODS IN DIV **********************************************************/

    public void returnAppModulesMethod() {
        if (getLovBaseBean().getSelectedDTOS() != null && !getLovBaseBean().getSelectedDTOS().isEmpty()) {
            setSelectedAppModuleKey(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey());
            fillTransactionList();
            resetLovDiv();
            setSelectedTransactionKey(getVirtualConstValue());
            resetConditions();
        }
    }


    public void returnTransactionsMethod() {
        if (getLovBaseBean().getSelectedDTOS() != null && !getLovBaseBean().getSelectedDTOS().isEmpty()) {
            setSelectedTransactionKey(getLovBaseBean().getSelectedDTOS().get(0).getName());
            fillConditionsList();
            resetLovDiv();
            resetLines();
            setSelectedConditionsKey(getVirtualConstValue());
        }

    }

    public String searchTransactionsLovDiv(Object searchType, Object searchQuery) {
        List<IBasicDTO> result = new ArrayList<IBasicDTO>();
        try {
            if (searchQuery != null && !searchQuery.equals("")) {
                String objectSearchQuery = searchQuery.toString();
                resetExceptionsCriteriaDTO();
                getSearchCriteriaDTO().setAppModuleCode(Long.valueOf(getSelectedAppModuleKey()));
                getSearchCriteriaDTO().setRelDesc(objectSearchQuery);
                result = GrsClientFactory.getConditionRelationsClient().getTransactionsCodeNameByModule(getSearchCriteriaDTO());
                if (result != null && result.size() != 0) {
                    getLovBaseBean().setMyTableData(result);
                    getLovBaseBean().setSearchTyp(searchType.toString());
                }
                else{
                    getLovBaseBean().setMyTableData(new ArrayList(0)); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }

        return "";
    }

    public void returnConditionsMethod() {
        if (getLovBaseBean().getSelectedDTOS() != null && !getLovBaseBean().getSelectedDTOS().isEmpty()) {
            setSelectedConditionsKey(((IConditionsDTO)getLovBaseBean().getSelectedDTOS().get(0)).getCode().getKey());
            resetLovDiv();
            resetLines();
        }

    }

    public String searchAppModulesLovDiv(Object searchType, Object searchQuery) {
        List<IBasicDTO> result = new ArrayList<IBasicDTO>();
        try {
            if (searchQuery != null && !searchQuery.equals("")) {
                String objectSearchQuery = searchQuery.toString();
                String objectSearchType = searchType.toString();

                if (objectSearchType.equals("0")) {
                    result = SecClientFactory.getSecApplicationModulesClient().getByCodeName(Long.valueOf(objectSearchQuery) ,null);
                } else {
                    result = SecClientFactory.getSecApplicationModulesClient().getByCodeName(null,objectSearchQuery);
                }
                if (result != null && result.size() != 0) {
                    getLovBaseBean().setMyTableData(result);
                    getLovBaseBean().setSearchTyp(searchType.toString());
                }
                else{
                    getLovBaseBean().setMyTableData(new ArrayList(0));    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }

        return "";
    }

    public String searchConditionsLovDiv(Object searchType, Object searchQuery) {
        List<IBasicDTO> result = new ArrayList<IBasicDTO>();
        try {
            if (searchQuery != null && !searchQuery.equals("")) {
                String objectSearchQuery = searchQuery.toString();
                String objectSearchType = searchType.toString();

               // setSearchCriteriaDTO(new ExceptionsSearchCriteriaDTO());
                resetExceptionsCriteriaDTO();
                getSearchCriteriaDTO().setRelDesc(selectedTransactionKey);

                if (objectSearchType.equals("0")) {
                    getSearchCriteriaDTO().setConditionCode(Long.valueOf(objectSearchQuery));
                } else {
                    getSearchCriteriaDTO().setConditionName(objectSearchQuery);
                }
                result =
                        GrsClientFactory.getConditionRelationsClient().getAllAndSearchConditionsByOpearationName(searchCriteriaDTO);
                if (result != null && result.size() != 0) {
                    getLovBaseBean().setMyTableData(result);
                    getLovBaseBean().setSearchTyp(searchType.toString());
                }
                else{
                    getLovBaseBean().setMyTableData(new ArrayList(0)); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }

        return "";
    }

    
    /**************************************  LINES SELECT METHODS **********************************************************/


    public void addSelectedElements() {
        if(toBeAddedLinesList.size() == getAvailableLinesListSize()){
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,"dontSelectAllLines"));
        }else{
            setLoadLinesList(false);
            IBasicDTO basicDTO = null;
            for (String key : toBeAddedLinesList) {
                basicDTO = this.getElementByKey(availableLinesList, key);
                getAvailableLinesList().remove(basicDTO);
                addedLinesList.add(basicDTO);
            }
            this.toBeAddedLinesList.clear();
            this.toBeRemovedLinesList.clear();
        }
    }

    public void removeSelectedElements() {
        setLoadLinesList(false);
        IBasicDTO basicDTO = null;
        for (String key : toBeRemovedLinesList) {
            basicDTO = this.getElementByKey(addedLinesList, key);
            addedLinesList.remove(basicDTO);
            getAvailableLinesList().add(basicDTO);
        }

        this.toBeAddedLinesList.clear();
        this.toBeRemovedLinesList.clear();
    }

    public void removeAllElements() {
        setLoadLinesList(true);
        addedLinesList.clear();
        this.toBeAddedLinesList.clear();
        this.toBeRemovedLinesList.clear();
    }
    
    private IBasicDTO getElementByKey(List<IBasicDTO> dataList, String key) {
        for (IBasicDTO basicDTO : dataList) {
            if (basicDTO.getCode().getKey().equals(key)) {
                return basicDTO;
            }
        }
        return null;
    }
    
    public boolean isEnableViewButton(){
      if(EXCEPTION_BY_LINE.equals( getSelectedRadioValue())){
          if(addedLinesList != null && getAddedLinesListSize() >=1){
             return true ; 
          }
      }else{
          if(getSelectedConditionsKey() != null && !getSelectedConditionsKey().equals(getVirtualConstValue())){
             return true ; 
          }
      }
      return false ;
    }
    /**
     * added by ahmedSakr 8-4-2015
     */
    public void stopExceptionValidityAlert() {
        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv2);");
    }

    public boolean isInActiveStatus() {
        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            IExceptionsDTO dto = (IExceptionsDTO)this.getSelectedDTOS().get(0);
            if (dto.getStatus().equals(IGrsConstants.CONDITION_STATUS_ACTIVE)) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public void editActiveStatus() {
        Date a = new Date();
        java.sql.Date date = new java.sql.Date(a.getTime());
        IExceptionsDTO exceptionsDTO = (IExceptionsDTO)getSelectedDTOS().get(0);
        exceptionsDTO.setStatus(IGrsConstants.CONDITION_STATUS_INACTIVE);
        exceptionsDTO.setUntilDate(date);

        try {
            if(getSelectedRadioValue().equals(getExceptionByLine())){
            Long conditionCode=Long.valueOf(getSelectedConditionsKey());
                GrsClientFactory.getExceptionsClient().cancelExceptionLines(exceptionsDTO,getAddedLinesList(),conditionCode);
            }else{
            GrsClientFactory.getExceptionsClient().update(exceptionsDTO);
            }
        } catch (DataBaseException e) {
        } catch (ItemNotFoundException e) {
        } catch (SharedApplicationException e) {
        }
        getSelectedDTOS().clear();
        if (getHighlightedDTOS() != null) {
            getHighlightedDTOS().clear();
        }
        getSharedUtil().setSuccessMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                                                          "stopExceptionDone"));

    }

    public String goAdd(){
           Long selctedConditionCode = Long.valueOf(getSelectedConditionsKey());
           IConditionRelationsDTO dto = null;
           try{
               dto =(IConditionRelationsDTO)GrsClientFactory.getConditionRelationsClient().getActiveConditionRelation(selctedConditionCode, selectedTransactionKey);
           }catch(Exception e){
               e.printStackTrace();
           }
           if(dto == null){
              getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                                                                         "cannotAddException"));
              return null;
           }else{
                ExceptionsAddBean exceptionsAddBean = (ExceptionsAddBean)evaluateValueBinding("exceptionsAddBean");
               ((IExceptionsDTO)exceptionsAddBean.getPageDTO()).setRTabrecSerial(dto.getTabrecSerial());
               //((IExceptionsDTO)exceptionsAddBean.getPageDTO()).setRTabrecSerial(dto.getRTabrecSerial());
               Long appModuleCode = Long.valueOf(selectedAppModuleKey);
               if(appModuleCode != null && appModuleCode.equals(IGrsConstants.APP_MODULE_AOE_CODE) ){ /* AOE module */
                    exceptionsAddBean.setSelectedExceptionTypeId("1");
                    exceptionsAddBean.setDisableExceptionType(true);
               }
               exceptionsAddBean.setSelectedAppModuleKey(appModuleCode);
               exceptionsAddBean.setSearchEmpResult(getSearchEmpResult());
               exceptionsAddBean.setShowLines(false);
               IBasicDTO basicDTO = null;
               if(selectedConditionsKey != null && !getVirtualConstValue().equals(getSelectedConditionsKey()) ){
                   basicDTO = this.getElementByKey(conditionsList, selectedConditionsKey);
                   exceptionsAddBean.setSelectedConditionDTO((IConditionsDTO)basicDTO);
               }
               if(selectedTransactionKey != null && !getVirtualConstValue().equals(selectedTransactionKey) ){
                   exceptionsAddBean.setSelectedOperationKey(selectedTransactionKey);
               }
               if(isShowLines()){
                   exceptionsAddBean.setLinesList(addedLinesList);
                   exceptionsAddBean.setShowLines(true);
               }else{
               exceptionsAddBean.setShowLines(false);    

              }
               return "addException";
           }
    }
    /**************************************  Getters & Setters ************************************************************************/

    public void setAppModuleKey(String appModuleKey) {
        this.appModuleKey = appModuleKey;
    }

    public String getAppModuleKey() {
        return appModuleKey;
    }

    public void setSelectedAppModuleKey(String selectedAppModuleKey) {
        this.selectedAppModuleKey = selectedAppModuleKey;
    }

    public String getSelectedAppModuleKey() {
        if (!appModuleKey.equals("0")) {
            return appModuleKey;
        }
        return selectedAppModuleKey;
    }

    public void setSelectedTransactionKey(String selectedTransactionKey) {
        this.selectedTransactionKey = selectedTransactionKey;
    }

    public String getSelectedTransactionKey() {
        // return "ATT excuse type join condition trancation";
        return selectedTransactionKey;
        }


    public void setAppModuleList(List<IBasicDTO> appModuleList) {
        this.appModuleList = appModuleList;
    }

    public List<IBasicDTO> getAppModuleList() {
        if (appModuleList == null || appModuleList.size() == 0) {
            try {
                appModuleList = SecClientFactory.getSecApplicationModulesClient().getLeavesCodeName();
            } catch (DataBaseException e) {
                appModuleList = new ArrayList<IBasicDTO>();
                transactionList = new ArrayList<IBasicDTO>();
                conditionsList = new ArrayList();
            } catch (Exception e) {
                appModuleList = new ArrayList<IBasicDTO>();
                transactionList = new ArrayList<IBasicDTO>();
                conditionsList = new ArrayList();
            }
        }

        return appModuleList;
    }

   
    
    private List getTransactionsListDB(){
        List<IBasicDTO> list = new ArrayList<IBasicDTO>();
        try{
            IExceptionsSearchCriteriaDTO searchDTO = new ExceptionsSearchCriteriaDTO();
            searchDTO.setAppModuleCode(Long.valueOf(getSelectedAppModuleKey()));
            list = GrsClientFactory.getConditionRelationsClient().getTransactionsCodeNameByModule(searchDTO); 
            for(IBasicDTO dto : list){
                temp.put(dto.getCode().getKey(), dto.getName() );
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return list;
    }
    
    private List getConditionsListDB(){
        
        try {
            resetExceptionsCriteriaDTO();
            getSearchCriteriaDTO().setRelDesc(selectedTransactionKey);
            return GrsClientFactory.getConditionRelationsClient().getAllAndSearchConditionsByOpearationName(searchCriteriaDTO);
        } catch (DataBaseException e) {
            return new ArrayList();
        } catch (SharedApplicationException e) {
            return new ArrayList();
        }
    }

    private List getLinesListDB() {

        try {
            return GrsClientFactory.getConditionDetailsClient().getLinesByConditionCode(Long.valueOf(getSelectedConditionsKey()));
        } catch (DataBaseException e) {
            return new ArrayList();
        } catch (SharedApplicationException e) {
            return new ArrayList();
        }
    }

    public void setTransactionList(List<IBasicDTO> transactionList) {
        this.transactionList = transactionList;
    }

    public List<IBasicDTO> getTransactionList() {
        return transactionList;
    }
    public void fillTransactionList(){
            if (!getSelectedAppModuleKey().equals(getVirtualConstValue())) {
                try {
                    transactionList = getTransactionsListDB();
                    setTransactionsButtonsEnabled(true);
                } catch (Exception e) {
                    transactionList = new ArrayList<IBasicDTO>();
                }
            } else {
                resetTransactions();
            }
        }
    public void setSelectedConditionsKey(String selectedConditionsKey) {
        this.selectedConditionsKey = selectedConditionsKey;
    } 

    public void setConditionsList(List<IBasicDTO> conditionsList) {
        this.conditionsList = conditionsList;
    }

    public List<IBasicDTO> getConditionsList() {
        return conditionsList;
    }
    
    private void fillConditionsList(){
            if (!getVirtualConstValue().equals(temp.get(selectedTransactionKey))) {
                try {
                    setConditionsList(new ArrayList());
                    setSelectedConditionsKey("");
                    conditionsList = getConditionsListDB();
                    setConditionsButtonsEnabled(true);
                } catch (Exception e) {
                    conditionsList = new ArrayList<IBasicDTO>();
                }
            } else {
                resetConditions();
            }
        }
    public List<IBasicDTO> getAvailableLinesList() {
       
        if(loadLinesList && !getSelectedConditionsKey().equals(getVirtualConstValue())){
            try{
                availableLinesList = getLinesListDB(); 
                setLinesButtonsEnabled(true);
            }catch(Exception e){
                availableLinesList = new ArrayList<IBasicDTO>(); 
            }

        }


        return availableLinesList;


    }

    public void selectMenuChanged(ActionEvent e) {

    }

    public void setShowLines(boolean showLines) {
        this.showLines = showLines;
    }

    public boolean isShowLines() {
        return showLines;
    }


    public void setToBeAddedLinesList(List<String> toBeAddedLinesList) {
        this.toBeAddedLinesList = toBeAddedLinesList;
    }

    public List<String> getToBeAddedLinesList() {
        return toBeAddedLinesList;
    }

    public void setToBeRemovedLinesList(List<String> toBeRemovedLinesList) {
        this.toBeRemovedLinesList = toBeRemovedLinesList;
    }

    public List<String> getToBeRemovedLinesList() {
        return toBeRemovedLinesList;
    }

    public void setAddedLinesList(List<IBasicDTO> addedLinesList) {
        this.addedLinesList = addedLinesList;
    }

    public List<IBasicDTO> getAddedLinesList() {
        return addedLinesList;
    }


    public String getVirtualConstValue() {
        return "";
    }



    public void resetLines(){
       availableLinesList = new ArrayList<IBasicDTO>();
       toBeAddedLinesList = new ArrayList<String>();
       addedLinesList = new ArrayList<IBasicDTO>();
       toBeRemovedLinesList = new ArrayList<String>();
        setLoadLinesList(true);
       setLinesButtonsEnabled(false);
    }
    public void resetConditions() {
        resetLines();
        setSelectedConditionsKey(getVirtualConstValue());
        setConditionsList(new ArrayList());
        setConditionsButtonsEnabled(false);
    }

    public void transactionChanged() {
        if (!selectedTransactionKey.equals(getVirtualConstValue())) {
            fillConditionsList();
        } else {
            resetConditions();
        }

    }

    public void resetTransactions() {
        setSelectedTransactionKey(getVirtualConstValue());
        if(appModuleKey == null || appModuleKey.equals("0")){
                setTransactionList(new ArrayList());    
            }
        setTransactionsButtonsEnabled(false);
        resetConditions();
    }

    public void appModuleChanged() {
        if (!selectedAppModuleKey.equals(getVirtualConstValue())) {
            fillTransactionList();
        } else {
            resetTransactions();
        }
        resetConditions();
    }
    public void setLinesButtonsEnabled(boolean linesButtonsEnabled) {
        this.linesButtonsEnabled = linesButtonsEnabled;
    }

    public boolean isLinesButtonsEnabled() {
        return linesButtonsEnabled;
    }


    public void setTransactionsButtonsEnabled(boolean transactionsButtonsEnabled) {
        this.transactionsButtonsEnabled = transactionsButtonsEnabled;
    }

    public boolean isTransactionsButtonsEnabled() {
        return transactionsButtonsEnabled;
    }

    public void setConditionsButtonsEnabled(boolean conditionsButtonsEnabled) {
        this.conditionsButtonsEnabled = conditionsButtonsEnabled;
    }

    public boolean isConditionsButtonsEnabled() {
        return conditionsButtonsEnabled;
    }

   


    /******************** Flags for the Status **************************************************/
    public String getActiveFlag() {
        return IGrsConstants.EXCEPTION_STATUS_ACTIVE.toString();
    }

    public String getPendingFlag() {
        return IGrsConstants.EXCEPTION_STATUS_PENDING.toString();
    }

    public String getFreezedFlag() {
        return IGrsConstants.EXCEPTION_STATUS_FREEZE.toString();
    }

    public String getInactiveFlag() {
        return IGrsConstants.EXCEPTION_STATUS_INACTIVE.toString();
    }

    public int getAvailableLinesListSize() {
        if (availableLinesList != null) {
            return availableLinesList.size();
        }
        return 0;
    }
    
    public int getAddedLinesListSize() {
        if (addedLinesList != null) {
            return addedLinesList.size();
        }
        return 0;
    }

    
    public void setLoadLinesList(boolean loadLinesListAgain) {
        this.loadLinesList = loadLinesListAgain;
    }

    
    /*
     * Cancel Search
     */

    public String cancelSearchObjectTypeLovDiv() {
        getLovBaseBean().setCodeTypeString(false);
        setSearchMode(false);
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().getSearchQuery();
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        return "";
    }
    
    public String cancelSearchTransactionLovDiv() {
        getLovBaseBean().setCodeTypeString(false);
        setSearchMode(false);
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().getSearchQuery();
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        return "";
    }
    
    public String resetLovDiv() {
        getLovBaseBean().setCodeTypeString(false);
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().getSearchQuery();
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        setSearchConditions(false);
        setSearchTransactions(false);
        return "";
    }
    
    public void setSearchCriteriaDTO(IExceptionsSearchCriteriaDTO searchCriteriaDTO) {
        this.searchCriteriaDTO = searchCriteriaDTO;
    }


    public boolean isLoadLinesList() {
        return loadLinesList;
    }

    public IExceptionsSearchCriteriaDTO getSearchCriteriaDTO() {
        return searchCriteriaDTO;
    }

    public void setSelectedRadioValue(String selectedRadioValue) {
        this.selectedRadioValue = selectedRadioValue;
    }

    public void setAvailableLinesList(List<IBasicDTO> availableLinesList) {
        this.availableLinesList = availableLinesList;
    }

    public String getSelectedRadioValue() {
        return selectedRadioValue;
    }


    public void setSearchTransactions(boolean searchTransactions) {
        this.searchTransactions = searchTransactions;
    }

    public boolean isSearchTransactions() {
        return searchTransactions;
    }

    public void setSearchConditions(boolean searchConditions) {
        this.searchConditions = searchConditions;
    }

    public boolean isSearchConditions() {
        return searchConditions;
    }

    public void setEnableViewButton(boolean enableViewButton) {
        this.enableViewButton = enableViewButton;
    }
    
   

  

    
    public String getSelectedConditionsKey() {
       return selectedConditionsKey;
    }


    public void setJcHelperBean(JoinConditionHelperBean jcHelperBean) {
        this.jcHelperBean = jcHelperBean;
    }

    public JoinConditionHelperBean getJcHelperBean() {
        return jcHelperBean;
    }

    public void setViewDetailsPageURL(String viewDetailsPageURL) {
        this.viewDetailsPageURL = viewDetailsPageURL;
    }

    public String getViewDetailsPageURL() {
        if(!getSelectedConditionsKey().equals(getVirtualConstValue())){
            return ConditionDetailsBean.constructPageURL(getSelectedConditionsKey(), false);    
        }
        return getVirtualConstValue();
    }

    public void setViewMode(boolean viewMode) {
        this.viewMode = viewMode;
    }

    public boolean isViewMode() {
        return viewMode;
    }

    public void setSearchEmpResult(int searchEmpResult) {
        this.searchEmpResult = searchEmpResult;
    }

    public int getSearchEmpResult() {
        if(searchEmpResult == null){
            return IGrsConstants.SHOW_EMP_ONLY;
        }
        return searchEmpResult;
    }


    public void setTemp(Map<String, String> temp) {
        this.temp = temp;
    }

    public Map<String, String> getTemp() {
        return temp;
    }
}
