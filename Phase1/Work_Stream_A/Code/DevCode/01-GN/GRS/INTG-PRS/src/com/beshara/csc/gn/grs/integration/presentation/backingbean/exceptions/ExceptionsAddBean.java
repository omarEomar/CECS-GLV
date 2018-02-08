package com.beshara.csc.gn.grs.integration.presentation.backingbean.exceptions;


import com.beshara.base.deploy.SessionBeanProviderException;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.ExceptionsSearchCriteriaDTO;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionResultsSearchDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.dto.IEmpKtwCitizenDTO;
import com.beshara.csc.gn.grs.business.dto.IExceptionsDTO;
import com.beshara.csc.gn.grs.business.dto.IExcptionReasonsDTO;
import com.beshara.csc.gn.grs.business.dto.ILinesDTO;
import com.beshara.csc.gn.grs.business.dto.IParameterTypesDTO;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.business.shared.IGrsConstants;
import com.beshara.csc.gn.grs.business.shared.exception.ExceptionAlreadyExist;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IVacEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeServiceNotEndedException;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeeLastDegNotMokafaaShamlaException;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.org.integration.presentation.backingbean.workcenters.WorkCentersHelperBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class ExceptionsAddBean extends LookUpBaseBean{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

   
   /**************************************  start variables **********************************************************/
   private static final String BEAN_NAME = "exceptionsAddBean";
   protected final static String PAGE_NAV_KEY = "condition_results_page";
   private static final String BUNDLE_NAME = "com.beshara.csc.gn.grs.integration.presentation.resources.integration";
   protected final static String SEARCH_BY_CIVIL_ID = "1";
   protected final static String SEARCH_BY_BULK = "2";
   private String searchTypeFlag = SEARCH_BY_CIVIL_ID;

   private Long civilId;
   private String selectedCategoryId = getVirtualConstValue();
   private String selectedMinistryId = getVirtualConstValue();
   private String selectedWorkCenterId = getVirtualConstValue();
   private String selectedCaderId = getVirtualConstValue();
   private String selectedGroupId = getVirtualConstValue();
   private String selectedDegreeId = getVirtualConstValue();
   private String selectedRaiseId = getVirtualConstValue();
   private String selectedBgtProgramId = getVirtualConstValue();
   private String selectedBgtTypeId = getVirtualConstValue();
   private String selectedCondId = getVirtualConstValue();
   private String selectedWorkCenterName;

   private List<IBasicDTO> categoriesList;
   private List<IBasicDTO> ministriesList;
   private List<IBasicDTO> caderList;
   private List<IBasicDTO> groupsList;
   private List<IBasicDTO> degreesList;
   private List<IBasicDTO> raisesList;
   private List<IBasicDTO> bgtProgramsList;
   private List<IBasicDTO> bgtTypesList;
   private List<IBasicDTO> exceptionReasonList ;
   List<IBasicDTO> searchResult = new ArrayList<IBasicDTO>();
   private String selectedExeReasonKey;
   private Long selectedAppModuleKey;
    private String selectedOperationKey;
   
   private List<IBasicDTO> linesList ;
   private boolean showLines;

   private boolean activeButton;
   private String civilName;
   private int searchEmpResult;

   private Boolean searchInEmpMode = false;


   private List<IBasicDTO> conditionsList;
   
   private IConditionsDTO selectedConditionDTO;
   
   private String conditionName="";

   private String resultTableStyleClass = "dataT-With-3-row-filter-and-collapse";
   private WorkCentersHelperBean wcIntegrationBean;

   private String filterBy;    
   private String empName ;
   private String selectedExceptionTypeId;
   private boolean disableExceptionType;
   private boolean reEnterCivilId;
   private EmployeeListOfValues empListOfValues;

    /************************************** end variables **********************************************************/      
    public ExceptionsAddBean() {
        
       setPageDTO(GrsDTOFactory.createExceptionsDTO()); 
        setSelectedPageDTO(GrsDTOFactory.createExceptionsDTO());
       setClient(GrsClientFactory.getExceptionsClient());
        initLoveBaseBean();
        initIntegration();
        setEmpListOfValues(new EmployeeListOfValues());
    }
    
    public void hideEmpLovDiv() {
        getEmpListOfValues().setMyTableData(new ArrayList<IBasicDTO>());
        getEmpListOfValues().setSelectedRadio("");
        if (getEmpListOfValues().getSelectedDTOS() != null) {
            getEmpListOfValues().getSelectedDTOS().clear();
        }
        getEmpListOfValues().resetData();
        
    }
    
    private void resetEmpDiv( IVacEmployeeSearchDTO searchDTO){
        getEmpListOfValues().setSearchMode(false);
        getEmpListOfValues().setSearchTyp("1");
        searchDTO.setCivilId(null);
        searchDTO.setEmpName(null);
        getEmpListOfValues().setSearchQuery("");
        
    }
    public void showSearchForEmployeeDiv() {


        if (searchEmpResult == Integer.valueOf(IGrsConstants.SEARCH_EXCEPTION_SERVICE_ENDED_EMPLOYEE.toString())) {

            getEmpListOfValues().setSearchMethod(BEAN_NAME + ".searchEmpCandidates");
        } else {

            getEmpListOfValues().setSearchMethod("exceptionsAddBean.searchEmpResults");
        }
        getEmpListOfValues().setReturnMethodName("exceptionsAddBean.returnMethodAction");
       
        getEmpListOfValues().resetData();
        getEmpListOfValues().setResetDivAfterClose(true);
        getEmpListOfValues().setSearchTyp("1");
        getEmpListOfValues().setSearchQuery("");
        getEmpListOfValues().setMyTableData(new ArrayList());
        getEmpListOfValues().setSelectedDTOS(null);
        getEmpListOfValues().setUseCustomSearch(true);
        getEmpListOfValues().getOkCommandButton().setReRender("divMsg,msgShow,civilIdInTxt,nameId,buttonsPG,namePG  ,civilidButton ");    }
    public void ShowSearchDiv(ActionEvent e){
        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1)");
    }
    public void returnMethodAction(){
            if(getEmpListOfValues().getSelectedDTOS() !=null && 
               getEmpListOfValues().getSelectedDTOS().size() != 0){
                if(searchEmpResult==Integer.valueOf(IGrsConstants.SEARCH_EXCEPTION_SERVICE_ENDED_EMPLOYEE.toString())){
                    IEmployeesDTO selectedDTO = (IEmployeesDTO)getEmpListOfValues().getSelectedDTOS().get(0);

                    setCivilId(Long.valueOf( selectedDTO.getRealCivilId()));
                    setCivilName(selectedDTO.getName());
                }else{
             IKwtCitizensResidentsDTO selectedDTO = (IKwtCitizensResidentsDTO)getEmpListOfValues().getSelectedDTOS().get(0);
                    setCivilId(Long.valueOf( selectedDTO.getCode().getKey()));
                    setCivilName(selectedDTO.getFullName());
                }
               
                loadData();
            }
        
    }
    
    public void resetEmpData(){
        setReEnterCivilId(false);
        setCivilName(null);
        setCivilId(null);
    }
    
//    private boolean empExistInSelectedDTOSList(IBasicDTO dto){
//        if(getSelectedDTOS()!= null && !getSelectedDTOS().isEmpty()){
//            int i = getSelectedDTOS().indexOf(dto);
//            if(i>=0){
//                return true;
//            }
//            return false;
//            }
//        return false;
//    }
    
    
    public void cancelSearchDiv() throws Exception {
        getEmpListOfValues().resetData();
        getEmpListOfValues().setResetDivAfterClose(true);
        getEmpListOfValues().setSearchTyp("1");
        getEmpListOfValues().setSearchQuery("");
        getEmpListOfValues().setMyTableData(new ArrayList());
        getEmpListOfValues().setSelectedDTOS(null);
    }
    
    public void searchEmpResults(Object searchType, Object searchQuery) {
        
        IKwtCitizensResidentsDTO kwtCitizensDTO = null; 
        List<IBasicDTO> resultSearch = new ArrayList<IBasicDTO>();
        List<IBasicDTO> list = new ArrayList<IBasicDTO>();
        Long searchcategoryType = new Long(searchEmpResult); 
        
        if (searchQuery != null && !searchQuery.toString().equals("") &&
            searchType != null && !searchType.equals("")) {
           getEmpListOfValues().setSearchMode(true);
           
                if (searchType.toString().equals("0")) {
                    
                    try {
                        resultSearch = GrsClientFactory.getExceptionsClient().searchEmployeeKwtCitizenCodeName(searchQuery.toString(),Long.valueOf(searchType.toString()), searchcategoryType);       
                           
                           if(resultSearch != null && resultSearch.get(0)!=null && IGrsConstants.SEARCH_EXCEPTION_EMPLOYEE.equals(searchcategoryType)){
                               kwtCitizensDTO = (IKwtCitizensResidentsDTO)((IEmployeesDTO)resultSearch.get(0)).getCitizensResidentsDTO(); 
                               list.add(kwtCitizensDTO);
                               getEmpListOfValues().setMyTableData(list);
                           }else if(resultSearch != null && resultSearch.get(0)!=null && !IGrsConstants.SEARCH_EXCEPTION_EMPLOYEE.equals(searchcategoryType)){
                               kwtCitizensDTO = (IKwtCitizensResidentsDTO)resultSearch.get(0); 
                               list.add(kwtCitizensDTO);
                               getEmpListOfValues().setMyTableData(list);
                           }else{
                               getEmpListOfValues().setMyTableData(new ArrayList<IBasicDTO>());
                               return ;
                           }
                       }catch(Exception e){
                          e.printStackTrace();
                           getEmpListOfValues().setMyTableData(new ArrayList());
                       }
                  
                }else {
                
                try {
                    resultSearch = GrsClientFactory.getExceptionsClient().searchEmployeeKwtCitizenCodeName(searchQuery.toString(),Long.valueOf(searchType.toString()), searchcategoryType);       
                       
                       if(resultSearch != null &&  IGrsConstants.SEARCH_EXCEPTION_EMPLOYEE.equals(searchcategoryType)){
                           for(IBasicDTO dto :resultSearch){
                               kwtCitizensDTO = (IKwtCitizensResidentsDTO)((IEmployeesDTO)dto).getCitizensResidentsDTO(); 
                               list.add(kwtCitizensDTO);
                           }
                           getEmpListOfValues().setMyTableData(list);
                       }else if(resultSearch != null && resultSearch.get(0)!=null && !IGrsConstants.SEARCH_EXCEPTION_EMPLOYEE.equals(searchcategoryType)){
                           for(IBasicDTO dto : resultSearch){
                               kwtCitizensDTO = (IKwtCitizensResidentsDTO)dto; 
                               list.add(kwtCitizensDTO);
                           }
                           getEmpListOfValues().setMyTableData(list);
                       }else{
                           getEmpListOfValues().setMyTableData(new ArrayList<IBasicDTO>());
                           return ;
                       }
                   }catch(Exception e){
                      e.printStackTrace();
                       getEmpListOfValues().setMyTableData(new ArrayList());
                   }
              
            }
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
        public AppMainLayout appMainLayoutBuilder() {
            AppMainLayout app = super.appMainLayoutBuilder();
            app.setShowNavigation(false);
            app.setShowSearch(false);
            app.setShowLookupAdd(false);
            app.setShowLookupEdit(false);
            app.setShowDelAlert(false);
            app.setShowDelConfirm(true);
            app.setShowbar(false);
            app.setShowContent1(true);
            app.setShowpaging(true);
            app.setShowLovDiv(true);
            app.setShowIntegrationDiv1(true);
            app.setShowCustomDiv1(true);
         
            return app;
        }

        public static ExceptionsAddBean getInstance() {
            return (ExceptionsAddBean)JSFHelper.getValue(BEAN_NAME);
        }

        public void searchTypeChanged(ActionEvent e) {
            if (searchTypeFlag.equals(SEARCH_BY_CIVIL_ID)) {
                setResultTableStyleClass("dataT-With-4-row-filter-and-collapse");
                searchResult=new ArrayList<IBasicDTO>();
            } else {
                setResultTableStyleClass("dataT-With-6-row-filter-and-collapse");
            }
            try {
                cancelSearch();
                resetValues();
                setReEnterCivilId(false);
            } catch (DataBaseException f) {
                f.printStackTrace();
            }
        }

        public List getTotalList() {
            return new ArrayList();
        }
    public String getVirtualConstValue() {
        return "";
    }

        private void resetValues(){
            setSelectedExeReasonKey(getVirtualConstValue());
            ((IExceptionsDTO)getPageDTO()).setFromDate(null);
            setSelectedExceptionTypeId(getVirtualConstValue());
        }
        public void cancelSearch() throws DataBaseException {
            searchResult=new ArrayList<IBasicDTO>();
            super.cancelSearch();
            setCivilId(null);
            setSelectedCategoryId(getVirtualConstValue());
            setSelectedMinistryId(getVirtualConstValue());
            setSelectedWorkCenterId(getVirtualConstValue());
            setSelectedWorkCenterName(null);
            setSelectedCaderId(getVirtualConstValue());
            setSelectedGroupId(getVirtualConstValue());
            setSelectedDegreeId(getVirtualConstValue());
            setSelectedRaiseId(getVirtualConstValue());
            setSelectedBgtProgramId(getVirtualConstValue());
            setSelectedBgtTypeId(getVirtualConstValue());
            setFilterBy(getVirtualConstValue());
            setMinistriesList(null);
            setGroupsList(null);
            setDegreesList(null);
            setRaisesList(null);

        }


        public void selectedCategoryChanged(ActionEvent ae) {
            System.out.println("Inside conditionResultsBean.selectedCategoryChanged" + ae);
            setSelectedMinistryId(getVirtualConstValue());
            setMinistriesList(null);
            setSelectedWorkCenterId(getVirtualConstValue());
            setSelectedWorkCenterName(null);
        }

        public void selectedMinistryChanged(ActionEvent ae) {
            System.out.println("Inside conditionResultsBean.selectedMinistryChanged" + ae);
            setSelectedWorkCenterId(getVirtualConstValue());
            setSelectedWorkCenterName(null);
        }

        public void selectedCaderChanged(ActionEvent ae) {
            System.out.println("Inside conditionResultsBean.selectedCaderChanged" + ae);
            setSelectedGroupId(getVirtualConstValue());
            setGroupsList(null);
            setSelectedDegreeId(getVirtualConstValue());
            setDegreesList(null);
            setSelectedRaiseId(getVirtualConstValue());
            setRaisesList(null);
        }

        public void selectedGroupChanged(ActionEvent ae) {
            System.out.println("Inside conditionResultsBean.selectedGroupChanged" + ae);
            setSelectedDegreeId(getVirtualConstValue());
            setDegreesList(null);
            setSelectedRaiseId(getVirtualConstValue());
            setRaisesList(null);
        }

        public void selectedDegreeChanged(ActionEvent ae) {
            System.out.println("Inside conditionResultsBean.selectedDegreeChanged" + ae);
            setSelectedRaiseId(getVirtualConstValue());
            setRaisesList(null);
        }
        
        public void loadData(){
           setReEnterCivilId(true);
          if(civilId != null){
              //search in emp = 0
              //search in infKwtCitizin = 1
              // search all = 2
              Long searchType = new Long(searchEmpResult);
              IEmpKtwCitizenDTO dto;
            try {
                if (GlobalValidation.isValidCivilId(civilId)) {
                                    dto = (IEmpKtwCitizenDTO)GrsClientFactory.getExceptionsClient().searchEmployeeByRealCivilId(civilId, searchType);
                if(dto.getEmployeeDTO() == null && dto.getInfKwtCitizenDTO() == null){
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                                                                                               "civilNotExist"));
                    return ;
                }else if(IGrsConstants.SEARCH_EXCEPTION_KWT_CITIZEN.equals(searchType) &&
                    dto.getEmployeeDTO() != null){
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                                                                               "civilIdBelongToEmployee"));
                    return ;
                }
                
                
                if(dto.getEmployeeDTO() != null){
                    setCivilName(((IKwtCitizensResidentsDTO)dto.getEmployeeDTO().getCitizensResidentsDTO()).getFullName());
                }else{
                    setCivilName(dto.getInfKwtCitizenDTO().getFullName());
                }
                setActiveButton(true);   
                }else{
                        getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                                                                                                   "civilNotExist"));
                    }
            } catch (EmployeeLastDegNotMokafaaShamlaException e) {
                System.out.println("no mokaf2a");
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                                                                           "empLastDegNotMokafaaShamla"));
            } catch (EmployeeServiceNotEndedException w) {
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                                                                           "emp_not_ended_service"));
         }catch(EmployeePayrollInfoNotExistException e){
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                                                                               "emp_payroll_info_not_exist"));
//                getEmpListOfValues().setRenderedDropDown("namePG ,buttonsPG,msgShow ,civilidButton");
                }
            catch (Exception e) {
              getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                                                                                         "civilNotExist"));
              System.out.println("exception");
                e.printStackTrace();
          }
        }
        }
    /**************************************  Getters & Setters ************************************************************************/
        public void setCategoriesList(List<IBasicDTO> categoriesList) {
            this.categoriesList = categoriesList;
        }

        public List<IBasicDTO> getCategoriesList() {
            if(categoriesList ==null){
                try {
                    categoriesList =
                            OrgClientFactory.getCatsClient().getCodeNameByGovFlag(ISystemConstant.GOVERNMENT_FLAG);
                } catch (DataBaseException e) {
                    e.printStackTrace();
                    categoriesList = new ArrayList<IBasicDTO>(0);
                }
            }
            return categoriesList;
        }

        public void setMinistriesList(List<IBasicDTO> ministriesList) {
            this.ministriesList = ministriesList;
        }
        
        /***
         * added by ahmedSakr 15-4-2015
         * To open ExeptionReasons Search DIV
         * */

         public String openSearchDiv1() {
             resetLovDiv();
             getLovBaseBean().setReturnMethodName(BEAN_NAME + ".returnExceptionReasonMethod");
             getLovBaseBean().setSearchMethod(BEAN_NAME + ".searchExeptionReasonsLovDiv");
             getLovBaseBean().setCancelSearchMethod(BEAN_NAME + ".cancelSearchObjectTypeLovDiv");
             getLovBaseBean().getOkCommandButton().setReRender("exeptionReasonId,exeptionReasonFilterationId,namePG ,buttonsPG,msgShow ,civilidButton ");
             return "";
         }
    public void returnExceptionReasonMethod() {
        if (getLovBaseBean().getSelectedDTOS() != null && !getLovBaseBean().getSelectedDTOS().isEmpty()) {
            setSelectedExeReasonKey(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey());
            resetLovDiv();
        }
    }
    public String searchExeptionReasonsLovDiv(Object searchType, Object searchQuery) {
        List<IBasicDTO> result = new ArrayList<IBasicDTO>();
        try {
            if (searchQuery != null && !searchQuery.equals("")) {
                String objectSearchQuery = searchQuery.toString();
                String objectSearchType = searchType.toString();
                ExceptionsSearchCriteriaDTO searchDTO = new ExceptionsSearchCriteriaDTO();
                searchDTO.setAppModuleCode(selectedAppModuleKey);
                
                
                if (objectSearchType.equals("0")) {
                    searchDTO.setExceptionReasonsCode(Long.valueOf(objectSearchQuery));
                } else {
                    searchDTO.setExceptionReasonsName(objectSearchQuery);
                }
                result = GrsClientFactory.getExcptionReasonsClient().getExceptionReasonsByModule(searchDTO);
                if (result != null && result.size() != 0) {
                    getLovBaseBean().setMyTableData(result);
                    getLovBaseBean().setSearchTyp(searchType.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }

        return "";
    }

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
    public String resetLovDiv() {
        getLovBaseBean().setCodeTypeString(false);
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().getSearchQuery();
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        return "";
    }
    /**
     *
     *added by AhmedSakr 16-4-2015 
     * 
     */
     public List<IBasicDTO> getBaseSearchResult() throws DataBaseException {
         try {
             IConditionResultsSearchDTO searchDTO = GrsDTOFactory.createConditionResultsSearchDTO();
                 if (!getVirtualConstValue().equals(getSelectedWorkCenterId())) {
                     searchDTO.setWorkCenterCode(getSelectedWorkCenterId());
                 } else if (!getVirtualConstValue().equals(getSelectedMinistryId())) {
                     searchDTO.setMinistryCode(Long.parseLong(getSelectedMinistryId()));
                 }
                 if (!getVirtualConstValue().equals(getSelectedDegreeId())) {
                     searchDTO.setDegreeCode(Long.parseLong(getSelectedDegreeId()));
                 } else if (!getVirtualConstValue().equals(getSelectedGroupId())) {
                     searchDTO.setGroupCode(Long.parseLong(getSelectedGroupId()));
                 } else if (!getVirtualConstValue().equals(getSelectedCaderId())) {
                     searchDTO.setCaderCode(Long.parseLong(getSelectedCaderId()));
                 }
                 if (!getVirtualConstValue().equals(getSelectedBgtTypeId())) {
                     searchDTO.setBgtTypesCode(Long.parseLong(getSelectedBgtTypeId()));
                 }
                 if (!getVirtualConstValue().equals(getSelectedCondId())) {
                     searchDTO.setConditionCode(Long.valueOf(getSelectedCondId()));
                 }
                 searchResult = GrsClientFactory.getConditionsClient().getConditionResults_Enhanced(null, searchDTO);           
         } catch (Exception e) {
             e.printStackTrace();
         }
         return searchResult;
     }
  
        public List<IBasicDTO> getMinistriesList() {
            if (ministriesList == null && !getVirtualConstValue().equals(selectedCategoryId)) {
                try {
                    ministriesList =
                            OrgClientFactory.getMinistriesClient().getAllMinistriesByCategoryLeaves(Long.valueOf(selectedCategoryId));
                 
                } catch (Exception e) {
                    e.printStackTrace();
                    ministriesList = new ArrayList<IBasicDTO>(0);
                }
            }
            return ministriesList;
        }

        public void setCaderList(List<IBasicDTO> caderList) {
            this.caderList = caderList;
        }

        public List<IBasicDTO> getCaderList() {
            if(caderList ==null){
                try {
                    caderList = GrsClientFactory.getConditionsClient().getSalCaderCodeName();
                } catch (Exception e) {
                    e.printStackTrace();
                    caderList = new ArrayList<IBasicDTO>(0);
                }
            }
            return caderList;
        }

        public void setGroupsList(List<IBasicDTO> groupsList) {
            this.groupsList = groupsList;
        }

        public List<IBasicDTO> getGroupsList() {
            if (groupsList == null && !getVirtualConstValue().equals(selectedCaderId)) {
                try {
                    groupsList = GrsClientFactory.getConditionsClient().getSalGroupsCodeName(Long.valueOf(selectedCaderId));
                } catch (Exception e) {
                    e.printStackTrace();
                    groupsList = new ArrayList<IBasicDTO>(0);
                }
            }
            return groupsList;
        }

        public void setDegreesList(List<IBasicDTO> degreesList) {
            this.degreesList = degreesList;
        }

        public List<IBasicDTO> getDegreesList() {
            if (degreesList == null && !getVirtualConstValue().equals(selectedGroupId)) {
                try {
                    degreesList = GrsClientFactory.getConditionsClient().getSalDegreesCodeName(Long.valueOf(selectedGroupId));
                } catch (Exception e) {
                    e.printStackTrace();
                    degreesList = new ArrayList<IBasicDTO>(0);
                }
            }
            return degreesList;
        }

        public void setRaisesList(List<IBasicDTO> raisesList) {
            this.raisesList = raisesList;
        }

        public List<IBasicDTO> getRaisesList() {
            if (raisesList == null && !getVirtualConstValue().equals(selectedDegreeId)) {
                try {
                    raisesList = GrsClientFactory.getConditionsClient().getSalRaisesCodeName(Long.valueOf(selectedDegreeId));
                } catch (Exception e) {
                    e.printStackTrace();
                    raisesList = new ArrayList<IBasicDTO>(0);
                }
            }
            return raisesList;
        }

        public void setBgtProgramsList(List<IBasicDTO> bgtProgramsList) {
            this.bgtProgramsList = bgtProgramsList;
        }

        public List<IBasicDTO> getBgtProgramsList() {
            if(bgtProgramsList ==null){
                try {
                    if(!getSelectedMinistryId().equals(getVirtualConstValue())){
                        Long minCode=Long.valueOf(getSelectedMinistryId());
                        bgtProgramsList = GrsClientFactory.getConditionsClient().getBgtProgramsCodeByMinCode(minCode);
                    }else{
                    bgtProgramsList = GrsClientFactory.getConditionsClient().getBgtProgramsCodeName();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    bgtProgramsList = new ArrayList<IBasicDTO>(0);
               
            }
            }
            return bgtProgramsList;
        }

        public void setBgtTypesList(List<IBasicDTO> bgtTypesList) {
            this.bgtTypesList = bgtTypesList;
        }

        public List<IBasicDTO> getBgtTypesList() {
            if(bgtTypesList ==null){
                try {
                    bgtTypesList = GrsClientFactory.getConditionsClient().getBgtTypesCodeName();
                } catch (Exception e) {
                    e.printStackTrace();
                    bgtTypesList = new ArrayList<IBasicDTO>(0);
                }
            }
            return bgtTypesList;
        }

        public void setSelectedCategoryId(String selectedCategoryId) {
            this.selectedCategoryId = selectedCategoryId;
        }

        public String getSelectedCategoryId() {
            return selectedCategoryId;
        }

        public void setSelectedMinistryId(String selectedMinistryId) {
            this.selectedMinistryId = selectedMinistryId;
        }

        public String getSelectedMinistryId() {
            return selectedMinistryId;
        }

        public void setSelectedWorkCenterId(String selectedWorkCenterId) {
            this.selectedWorkCenterId = selectedWorkCenterId;
        }

        public String getSelectedWorkCenterId() {
            return selectedWorkCenterId;
        }

        public void setSelectedWorkCenterName(String selectedWorkCenterName) {
            this.selectedWorkCenterName = selectedWorkCenterName;
        }

        public String getSelectedWorkCenterName() {
            return selectedWorkCenterName;
        }

        public void setSelectedCaderId(String selectedCaderId) {
            this.selectedCaderId = selectedCaderId;
        }

        public String getSelectedCaderId() {
            return selectedCaderId;
        }

        public void setSelectedGroupId(String selectedGroupId) {
            this.selectedGroupId = selectedGroupId;
        }

        public String getSelectedGroupId() {
            return selectedGroupId;
        }

        public void setSelectedDegreeId(String selectedDegreeId) {
            this.selectedDegreeId = selectedDegreeId;
        }

        public String getSelectedDegreeId() {
            return selectedDegreeId;
        }

        public void setSelectedRaiseId(String selectedRaiseId) {
            this.selectedRaiseId = selectedRaiseId;
        }

        public String getSelectedRaiseId() {
            return selectedRaiseId;
        }

        public void setSelectedBgtProgramId(String selectedBgtProgramId) {
            this.selectedBgtProgramId = selectedBgtProgramId;
        }

        public String getSelectedBgtProgramId() {
            return selectedBgtProgramId;
        }

        public void setSelectedBgtTypeId(String selectedBgtTypeId) {
            this.selectedBgtTypeId = selectedBgtTypeId;
        }

        public String getSelectedBgtTypeId() {
            return selectedBgtTypeId;
        }

        public void setConditionsList(List<IBasicDTO> conditionsList) {
            this.conditionsList = conditionsList;
        }

        public List<IBasicDTO> getConditionsList() {
            return conditionsList;
        }

        public void setCivilId(Long civilId) {
            this.civilId = civilId;
        }

        public Long getCivilId() {
            return civilId;
        }


        public void setSearchTypeFlag(String searchTypeFlag) {
            this.searchTypeFlag = searchTypeFlag;
        }

        public String getSearchTypeFlag() {
            return searchTypeFlag;
        }

        public void setResultTableStyleClass(String resultTableStyleClass) {
            this.resultTableStyleClass = resultTableStyleClass;
        }

        public String getResultTableStyleClass() {
            return resultTableStyleClass;
        }

        public void setWcIntegrationBean(WorkCentersHelperBean wcIntegrationBean) {
            this.wcIntegrationBean = wcIntegrationBean;
        }

        public WorkCentersHelperBean getWcIntegrationBean() {
            return wcIntegrationBean;
        }

        public void initIntegration() {
            wcIntegrationBean = new WorkCentersHelperBean(BEAN_NAME, "integrationDiv1", false, false, true, true, null);
            wcIntegrationBean.getOkCommandButton().setReRender("workcenters,searchingPnl,pageStyleClass,selectedWorkCenterName,paging_panel,resultsPnl,dataT_data_panel");
            wcIntegrationBean.setReturnMethodName(BEAN_NAME + ".linkWorkCenter");
            wcIntegrationBean.setPreOpenMethodName(BEAN_NAME + ".openSearchWorkCenter");
            wcIntegrationBean.setSingleSelectionFlag(true);
        }

        public void linkWorkCenter() {
            List<IWorkCentersDTO> linkedWrkCenterlist = new ArrayList<IWorkCentersDTO>(0);
            if (wcIntegrationBean.getSelectedDTOSList() != null && wcIntegrationBean.getSelectedDTOSList().size() != 0) {
                for (IBasicDTO dto : wcIntegrationBean.getSelectedDTOSList()) {
                    IWorkCentersDTO minDTO = ((IWorkCentersDTO)dto);
                    minDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
                    linkedWrkCenterlist.add(minDTO);
                }
                if (wcIntegrationBean.getMinistryDTO().getWorkCentersDTOList() != null &&
                    wcIntegrationBean.getMinistryDTO().getWorkCentersDTOList().size() != 0) {
                    wcIntegrationBean.getMinistryDTO().getWorkCentersDTOList().addAll(linkedWrkCenterlist);
                    wcIntegrationBean.getMinistryDTO().setWorkCentersListSize(wcIntegrationBean.getMinistryDTO().getWorkCentersDTOList().size());
                } else {
                    wcIntegrationBean.getMinistryDTO().setWorkCentersDTOList(linkedWrkCenterlist);
                    wcIntegrationBean.getMinistryDTO().setWorkCentersListSize(wcIntegrationBean.getMinistryDTO().getWorkCentersDTOList().size());
                }
                selectedWorkCenterId = wcIntegrationBean.getSelectedDTOSList().get(0).getCode().getKey();
                selectedWorkCenterName = wcIntegrationBean.getSelectedDTOSList().get(0).getName();
            }
        }

        public void openSearchWorkCenter() {
            try {
                IMinistriesDTO dto = null;
                List<IWorkCentersDTO> exList = new ArrayList<IWorkCentersDTO>();
                if (selectedMinistryId != null && !selectedMinistryId.equals(getVirtualConstValue())) {
                    dto =
                    (IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(OrgEntityKeyFactory.createMinistriesEntityKey(Long.valueOf(selectedMinistryId)));
                    wcIntegrationBean.setMinistryDTO(dto);
                    if (dto.getWorkCentersDTOList() != null && dto.getWorkCentersDTOList().size() != 0) {
                        for (IWorkCentersDTO wrkDTO : dto.getWorkCentersDTOList()) {
                            if (wrkDTO != null)
                                exList.add(wrkDTO);
                        }
                        wcIntegrationBean.setExecludedList(exList);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        

        public void setSelectedCondId(String selectedCondId) {
            this.selectedCondId = selectedCondId;
        }

        public String getSelectedCondId() {
            return selectedCondId;
        }

        public void filterByCategory(ActionEvent ae) {

        }


        public void setFilterBy(String filterBy) {
            this.filterBy = filterBy;
        }

        public String getFilterBy() {
            return filterBy;
        }
        
        public String getSearchByCivilIdFlag() {
            return SEARCH_BY_CIVIL_ID;
        }

        public String getSearchByBulkFlag() {
            return SEARCH_BY_BULK;
        }
        
        public void setLinesList(List<IBasicDTO> linesList) {
            this.linesList = linesList;
        }

        public List<IBasicDTO> getLinesList() {
            return linesList;
        }

        public void setEmpName(String empName) {
            this.empName = empName;
        }

        public String getEmpName() {
            return empName;
        }

        public void setConditionName(String conditionName) {
            this.conditionName = conditionName;
        }

        public String getConditionName() {
            return conditionName;
        }

    public void setExceptionReasonList(List<IBasicDTO> exceptionReasonList) {
        this.exceptionReasonList = exceptionReasonList;
    }

    public List<IBasicDTO> getExceptionReasonList() {
        if (exceptionReasonList == null ) {
                try {
                   ExceptionsSearchCriteriaDTO searchDTO = new ExceptionsSearchCriteriaDTO() ;
                    searchDTO.setAppModuleCode(selectedAppModuleKey);
                    exceptionReasonList = GrsClientFactory.getExcptionReasonsClient().getExceptionReasonsByModule(searchDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                    exceptionReasonList = new ArrayList<IBasicDTO>(0);
                }
            }
            
        return exceptionReasonList;
    }

    public void setSelectedExeReasonKey(String selectedExeReasonKey) {
        this.selectedExeReasonKey = selectedExeReasonKey;
    }

    public String getSelectedExeReasonKey() {
        return selectedExeReasonKey;
    }

    public void setSelectedAppModuleKey(Long selectedAppModuleKey) {
        this.selectedAppModuleKey = selectedAppModuleKey;
    }

    public Long getSelectedAppModuleKey() {
        return selectedAppModuleKey;
    }

    public void setShowLines(boolean showLines) {
        this.showLines = showLines;
    }

    public boolean isShowLines() {
        return showLines;
    }
    public void setActiveButton(boolean activeButton) {
        this.activeButton = activeButton;
    }

    public boolean isActiveButton() {
        if(SEARCH_BY_BULK.equals(getSearchByBulkFlag())){
            if(getSelectedDTOS()!= null && !getSelectedDTOS().isEmpty()){
                return true;
            }
        }
        return activeButton;
    }

    public void setSearchEmpResult(int searchEmpResult) {
        this.searchEmpResult = searchEmpResult;
    }

    public int getSearchEmpResult() {
        return searchEmpResult;
    }
    
    
    public String goADD(){
        return "";
    }


    public void setSelectedConditionDTO(IConditionsDTO selectedConditionDTO) {
        this.selectedConditionDTO = selectedConditionDTO;
    }

    public IConditionsDTO getSelectedConditionDTO() {
        return selectedConditionDTO;
    }


    public void setCivilName(String civilName) {
        this.civilName = civilName;
    }

    public String getCivilName() {
        return civilName;
    }

    public void setSearchResult(List<IBasicDTO> searchResult) {
        this.searchResult = searchResult;
    }
    
    public List<IBasicDTO> getSearchResult(){
        return searchResult;
    }

    public String Add()  {
        IExceptionsDTO pageExceptionDTO = (IExceptionsDTO)getPageDTO();
        if(showLines && linesList != null && !linesList.isEmpty()){
            pageExceptionDTO.setLinesDTO((ILinesDTO)linesList.get(0));
        }
        pageExceptionDTO.setConditionsDTO(selectedConditionDTO);
        pageExceptionDTO.setExceptionType(Long.valueOf(selectedExceptionTypeId));
        pageExceptionDTO.setExcptionReasonsDTO((IExcptionReasonsDTO)this.getElementByKey(exceptionReasonList,
                                                                            selectedExeReasonKey));
        try {
            pageExceptionDTO.setParameterTypesDTO((IParameterTypesDTO)GrsClientFactory.getParameterTypesClient().getById(GrsEntityKeyFactory.createParameterTypesEntityKey(IGrsConstants.PARAM_TYPE_EMPLOYEE)));
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        pageExceptionDTO.setStatus(IGrsConstants.EXCEPTION_STATUS_ACTIVE);
        pageExceptionDTO.setUserCode(CSCSecurityInfoHelper.getUserCode());
        List<IExceptionsDTO> listDTO = new ArrayList();
        // add lines
        if (showLines) {
            if (linesList.size() == 1) {

                listDTO.add(pageExceptionDTO);
                
            } else {
                IExceptionsDTO _dtoForAdd = null;
                for (IBasicDTO lineDTO : linesList) {
                    try {
                        _dtoForAdd = (IExceptionsDTO)getSharedUtil().deepCopyObject(pageExceptionDTO);
                        _dtoForAdd.setLinesDTO((ILinesDTO)lineDTO);
                        listDTO.add(_dtoForAdd);
                        
                    } catch (Exception e) {

                    }

                }
                
            }


            // add Condition
        }else{
            listDTO.add(pageExceptionDTO);
        }

        // add ONE Employee/Person
        if(getSearchTypeFlag().equals(SEARCH_BY_CIVIL_ID)){


            // add MANY Employee/Person
            try {
                setPageDTO(GrsClientFactory.getExceptionsClient().addException(listDTO,Long.valueOf(getCivilId())));
                getSharedUtil().setSuccessMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                                                                                "SuccessAdds"));
            } catch (SharedApplicationException e) {
                if(e instanceof ExceptionAlreadyExist){
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.gn.grs.integration.presentation.resources.integration",
                                                                                                                                    e.getMessage()));    
                }else{
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.sharedutils.presentation.msgresources.global",
                                                                                                                                    e.getMessage()));
                }
                
            } catch (DataBaseException e) {
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.csc.sharedutils.presentation.msgresources.global",
                                                                                                                                e.getMessage()));
                
            }
        }else{
            List<Long> civilIDList = new ArrayList();
            if(getSelectedDTOS()!= null && !getSelectedDTOS().isEmpty()){
                for(IBasicDTO dto: getSelectedDTOS()){
                    civilIDList.add(Long.valueOf((dto.getCode().getKey())));
                }
            }
            List<IResultDTO> list= new ArrayList();
            try {
                list = GrsClientFactory.getExceptionsClient().addGroupException(listDTO,civilIDList);
                if(list.size()>0){
                    setDeleteStatusDTOS(list);
                    setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);"); 
                    return null;    
                }else{
                    getSharedUtil().setSuccessMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "SuccessAdds"));    
                }
                
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
           
            
        }
        
      

        
         GrsExceptionsHelperBean grsExceptionBean =
             (GrsExceptionsHelperBean)evaluateValueBinding("grsExceptionHelperBean");

        
         
             if (grsExceptionBean != null) {
            try {
                grsExceptionBean.search();


            } catch (DataBaseException e) {
            } catch (NoResultException e) {
            }
            try {
                if (getPageDTO()!= null &&  getPageDTO().getCode() != null) {
                    grsExceptionBean.getPageIndex((String)getPageDTO().getCode().getKey());
                    grsExceptionBean.getHighlightedDTOS().add(getPageDTO());
                    highlighDataTable("grsExceptionHelperBean");
                    grsExceptionBean.setRepositionTable(true);
                    grsExceptionBean.setSortedTable(false);
                }
            } catch (DataBaseException e) {
            }


        }
    

    
        return "view_exceptions_list";
    }

    public String back(){
        return "view_exceptions_list";
    }
    
    private IBasicDTO getElementByKey(List<IBasicDTO> dataList, String key) {
        for (IBasicDTO basicDTO : dataList) {
            if (basicDTO.getCode().getKey().equals(key)) {
                return basicDTO;
            }
        }
        return null;
    }

    public void setSelectedExceptionTypeId(String selectedExceptionTypeId) {
        this.selectedExceptionTypeId = selectedExceptionTypeId;
    }

    public String getSelectedExceptionTypeId() {
        return selectedExceptionTypeId;
    }

    public void setEmpListOfValues(EmployeeListOfValues empListOfValues) {
        this.empListOfValues = empListOfValues;
    }

    public EmployeeListOfValues getEmpListOfValues() {
        return empListOfValues;
    }



    public void setSearchInEmpMode(Boolean searchInEmpMode) {
        this.searchInEmpMode = searchInEmpMode;
    }

    public Boolean getSearchInEmpMode() {
        return searchInEmpMode;
    }

    public void setSelectedOperationKey(String selectedOperationKey) {
        this.selectedOperationKey = selectedOperationKey;
    }

    public String getSelectedOperationKey() {
        return selectedOperationKey;
    }

    public void setReEnterCivilId(boolean reEnterCivilId) {
        this.reEnterCivilId = reEnterCivilId;
    }

    public boolean isReEnterCivilId() {
        return reEnterCivilId;
    }
    
    
  public void searchEmpCandidates(Object searchType, Object searchQuery) {
            EmployeeListOfValues empListOfValuesBean = ((EmployeeListOfValues)getEmpListOfValues());
            if (searchQuery != null && !searchQuery.toString().equals("") && searchType != null &&
                !searchType.equals("")) {
                empListOfValuesBean.setSearchMode(true);
                IEmpEmployeeSearchDTO empEmployeeSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
                //                empEmployeeSearchDTO.setEmployment(!searchAtAllEmployees); // employment default value was true so i negated the searchAtAllEmployees attribute
                if (searchType.toString().equals("0"))
                    empEmployeeSearchDTO.setCivilId(Long.valueOf(searchQuery.toString()));
                else if (searchType.toString().equals("1"))
                    empEmployeeSearchDTO.setEmpName(searchQuery.toString());

                empEmployeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
                List hireStatusList = new ArrayList();
                hireStatusList.add(IEMPConstant.EMP_STATUS_END_SERVICE);
                empEmployeeSearchDTO.setEmpHireStatusList(hireStatusList);
                try {
                    if (isUsingPaging()) {
                        setUpdateMyPagedDataModel(true);
                        setPagingRequestDTO(new PagingRequestDTO("filterSearchByEmpWithPaging"));
                        setOldPageIndex(0);
                        setCurrentPageIndex(1);
                    } else {
                        empListOfValuesBean.setMyTableData(EmpClientFactory.getEmployeesClient().simpleSearch(empEmployeeSearchDTO));
                    }
                } catch (SessionBeanProviderException e) {
                    e.printStackTrace();
                    empListOfValuesBean.setMyTableData(new ArrayList());
                    //                    cantLocateSession = true;
                    empListOfValuesBean.setSearchMode(false);
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                    empListOfValuesBean.setMyTableData(new ArrayList());
                    empListOfValuesBean.setSearchMode(false);
                } catch (DataBaseException e) {
                    e.printStackTrace();
                    empListOfValuesBean.setMyTableData(new ArrayList());
                    empListOfValuesBean.setSearchMode(false);
                }
            } else {
                ResourceBundle bundle =
                    ResourceBundle.getBundle("com.beshara.jsfbase.csc.msgresources.global", FacesContext.getCurrentInstance().getViewRoot().getLocale());
                empListOfValuesBean.setErrorMsgValue(bundle.getString("missingField"));
                empListOfValuesBean.setSearchMode(false);
            }
            empListOfValuesBean.repositionPage(0);
            empListOfValuesBean.setSelectedDTOS(new ArrayList<IBasicDTO>(0));
        }

    public void setDisableExceptionType(boolean disableExceptionType) {
        this.disableExceptionType = disableExceptionType;
    }

    public boolean isDisableExceptionType() {
        return disableExceptionType;
    }
}


