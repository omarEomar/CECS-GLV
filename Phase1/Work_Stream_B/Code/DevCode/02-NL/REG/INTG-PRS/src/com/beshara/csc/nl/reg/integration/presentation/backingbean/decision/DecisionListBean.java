package com.beshara.csc.nl.reg.integration.presentation.backingbean.decision;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.IDecisionMakerTypesClient;
import com.beshara.csc.inf.business.client.IYearsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.IRegulationStatusClient;
import com.beshara.csc.nl.reg.business.client.ISubjectsClient;
import com.beshara.csc.nl.reg.business.client.ITypesClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegDecisionMaterialsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.decision.details.DecisionEmployeesModelSessionBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyListBaseBean;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class DecisionListBean extends ManyToManyListBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private String stringSearchType = "false";

    private IRegulationSearchDTO decisionSearchDTO = 
        RegDTOFactory.createRegulationSearchDTO();

    private IDecisionsClient searchClient = 
        RegClientFactory.getDecisionsClient();

    private ITypesClient typesClient = RegClientFactory.getTypesClient();

    private IYearsClient yearsClient = InfClientFactory.getYearsClient();

    private IDecisionMakerTypesClient decisionMakersClient = 
        InfClientFactory.getDecisionMakerTypesClient();

    //private IRegulationStatusClient statusesClient = RegClientFactory.getRegulationStatusClient();

    private ISubjectsClient subjectsClient = 
        RegClientFactory.getSubjectsClientForCenter();


    private List typesList = new ArrayList();
    private List yearsList = new ArrayList();
    private List decisionMakersList = new ArrayList();
    //private List statusesList = new ArrayList();
    private List subjectsList = new ArrayList();

    private List<IBasicDTO> canceledDecisionslist = new ArrayList<IBasicDTO>();

    private boolean cancelDescisionFlag = false;

    private boolean validDecision = false;

    private boolean typeColumnVisible = true;
    private boolean yearColumnVisible = true;
    private boolean numberColumnVisible = true;
    private boolean titleColumnVisible;
    private boolean decisionMakerColumnVisible;
    private boolean dateColumnVisible = true;
    private boolean applyDateColumnVisible = true;
    private boolean statusColumnVisible = true;
    private boolean cancelledDecisionFlag = false;
    private IRegulationStatusClient regStatusClient = 
        RegClientFactory.getRegulationStatusClientForCenter();
    private List regStatusList = new ArrayList();
    public DecisionListBean() {

        super.setClient(RegClientFactory.getDecisionsClient());
        super.setPageDTO(RegDTOFactory.createDecisionsDTO());
        setSaveSortingState(true);
        super.setSelectedPageDTO(RegDTOFactory.createDecisionsDTO());
        /*if (DecisionMaintainBean.isIntegrationPage()) {
            setEditNavigationCase("decisionemployeesmaintainedit-integration");
            setAddNavigationCase("decisionemployeesmaintain-integration");
        } else*/ {
            setEditNavigationCase("decisionmaindataedit");
            setAddNavigationCase("decisionmaindata");
        }
        setEditBeanName("decisionMaintainBean");
        setAddBeanName("decisionMaintainBean");
        setDelConfirmTitleBindingString("#{regResources.DelConfirmTitle}");
        setDelAlertTitleBindingString("#{regResources.DelAlertTitle}");

        setCustomDiv1("cancelDecisionDetailsDiv");

        setUsingPaging(true);
        setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));
        setIndexArray(new boolean[] { true, true, true, false, false, true, 
                                      true, true });
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        String viewId = 
            FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (viewId.equals("/module/jsps/decision/cancelDecisionDetails.jsp") || 
            viewId.equals("/module/jsps/decision-integration/cancelDecisionDetails.jsp")) {

            app.showAddeditPage();
            app.setShowCustomDiv1(true);
            app.setShowNavigation(true);
        } else {
            app = super.appMainLayoutBuilder();
            app.setShowCustomDiv1(true);
        }
        return app;
    }
    // ************************** Start Constructors ***************************

    public void setStringSearchType(String stringSearchType) {
        this.stringSearchType = stringSearchType;
    }

    public String getStringSearchType() {
        return stringSearchType;
    }


    public void setDecisionSearchDTO(IRegulationSearchDTO regulationSearchDTO) {
        this.decisionSearchDTO = regulationSearchDTO;
    }

    public IRegulationSearchDTO getDecisionSearchDTO() {
        return decisionSearchDTO;
    }

    public void setTypesList(List typesList) {
        this.typesList = typesList;
    }

    public List getTypesList() throws DataBaseException, 
                                      SharedApplicationException {

        typesList = typesClient.getCodeName();
        if (typesList == null) {
            typesList = new ArrayList();
        }

        return typesList;
    }

    public void setYearsList(List yearsList) {
        this.yearsList = yearsList;
    }

    public List getYearsList() throws DataBaseException, 
                                      SharedApplicationException {

        yearsList = yearsClient.getCodeName();
        if (yearsList == null) {
            yearsList = new ArrayList();
        }

        return yearsList;
    }

    public void setDecisionMakersList(List decisionMakersList) {
        this.decisionMakersList = decisionMakersList;
    }

    public List getDecisionMakersList() throws DataBaseException, 
                                               SharedApplicationException {

        decisionMakersList = decisionMakersClient.getCodeName();
        if (decisionMakersList == null) {
            decisionMakersList = new ArrayList();
        }

        return decisionMakersList;
    }
    /*
    public void setStatusesList(List statusesList) {
        this.statusesList = statusesList;
    }

    public List getStatusesList() throws DataBaseException,
                                         SharedApplicationException {

        statusesList = statusesClient.getCodeName();
        if (statusesList == null) {
            statusesList = new ArrayList();
        }

        return statusesList;
    }
*/

    public Long getStatusCanceledCode() {
        System.out.println("ISystemConstant.DECISION_STATUS_CANCELED=" + 
                           ISystemConstant.DECISION_STATUS_CANCELED);
        return ISystemConstant.DECISION_STATUS_CANCELED;
    }

    public Long getStatusCancelDecisionCode() {
        System.out.println("ISystemConstant.DECISION_STATUS_CANCEL=" + 
                           ISystemConstant.DECISION_STATUS_CANCEL);
        return ISystemConstant.DECISION_STATUS_CANCEL;
    }

    public Long getStatusValidCode() {
        System.out.println("ISystemConstant.DECISION_STATUS_VALID=" + 
                           ISystemConstant.DECISION_STATUS_VALID);
        return ISystemConstant.DECISION_STATUS_VALID;
    }

    public void setSubjectsList(List subjectsList) {
        this.subjectsList = subjectsList;
    }

    public List getSubjectsList() throws DataBaseException, 
                                         SharedApplicationException {

        subjectsList = subjectsClient.getCodeName();
        if (subjectsList == null) {
            subjectsList = new ArrayList();
        }

        return subjectsList;
    }

    // ************************** End Constructors *****************************

    //this to initialize the arrays of the dto in case of add

    public void initializeObjectBeforeMaintain() {

        IDecisionsDTO dto = (IDecisionsDTO)getPageDTO();
        DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean =(DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
        decisionEmployeesModelSessionBean.resetSessionData();
        if (dto.getDecisionRefrencesDTOList() == null) {
            dto.setDecisionRefrencesDTOList(new ArrayList());
        }

        if (dto.getEmpDecisionsDTOList() == null) {
            dto.setEmpDecisionsDTOList(new ArrayList());
        }
        if (dto.getRegDecisionMaterialsDTOList() == null) {
            dto.setRegDecisionMaterialsDTOList(new ArrayList<IRegDecisionMaterialsDTO>());
        }
        setPageDTO(dto);
        if (getIntegrationBean() != null && 
            getIntegrationBean().getModuleFrom() != null && 
            getIntegrationBean().getModuleFrom().equals("prm")) {
            ((IDecisionsDTO)getPageDTO()).setDecisionDate((Timestamp)getIntegrationBean().getHmObjects().get("decisionDate"));
            ((IDecisionsDTO)getPageDTO()).setDecisionAppliedDate((Timestamp)getIntegrationBean().getHmObjects().get("decisionAppliedDate"));
        }

    }

    public void showHideRegType() {
        typeColumnVisible = !typeColumnVisible;
        showHideColumn("typesDTO-name_column");
    }

    public void showHideRegYear() {
        yearColumnVisible = !yearColumnVisible;
        showHideColumn("yearsDTO-name_column");
    }

    public void showHideDecNumber() {
        numberColumnVisible = !numberColumnVisible;
        showHideColumn("code-decisionNumber_column");
    }

    public void showHideDecTitle() {
        titleColumnVisible = !titleColumnVisible;
        showHideColumn("decisionTitle_column");
    }

    public void showHideDecisionMaker() {
        decisionMakerColumnVisible = !decisionMakerColumnVisible;
        showHideColumn("decisionMakerTypesDTO-name_column");
    }

    public void showHideDecDate() {
        dateColumnVisible = !dateColumnVisible;
        showHideColumn("decisionDate_column");
    }

    public void showHideApplyDate() {
        applyDateColumnVisible = !applyDateColumnVisible;
        showHideColumn("decisionAppliedDate_column");
    }

    public void showHideCancelDate() {
        showHideColumn("cancel_date_column");
    }

    public void showHideStatus() {
        statusColumnVisible = !statusColumnVisible;
        showHideColumn("status_column");
    }

    public void search() throws DataBaseException, NoResultException {

        this.setSearchMode(true);

        try {
            if (decisionSearchDTO != null) {
                if (decisionSearchDTO.getName() != null) {
                    if (!(decisionSearchDTO.getName().equals(""))) {
                        decisionSearchDTO.setName(formatSearchString(decisionSearchDTO.getName()));
                    }
                }

                if (isUsingPaging()) {

                    generatePagingRequestDTO("decisionListBean", 
                                             "searchWithPaging");
                    resetPageIndex();
                    unCheck();

                } else {
                    this.setMyTableData(getSearchResult());
                    if (getSelectedDTOS() != null)
                        getSelectedDTOS().clear();
                    if (getHighlightedDTOS() != null)
                        getHighlightedDTOS().clear();
                    unCheck();


                    this.repositionPage(0);
                    this.setStringSearchType("false");

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public PagingResponseDTO searchWithPaging(PagingRequestDTO requestDTO) {
        return new PagingResponseDTO(getSearchResult());
    }

    public List getSearchResult() {
        List searchResult = null;
        try {
            searchResult = searchClient.search(decisionSearchDTO);

        } catch (ItemNotFoundException e) { //this means no search results found
            searchResult = new ArrayList();
            e.printStackTrace();
        } catch (NoResultException e) { //this means no search results found
            searchResult = new ArrayList();
            e.printStackTrace();
        } catch (Exception e) {
            searchResult = new ArrayList();
            e.printStackTrace();
        }
        return searchResult;
    }

    public void cancelSearch() throws DataBaseException {

        System.out.println("Calling cancelSearch()...");
        this.setSearchQuery("");
        this.setStringSearchType("false");
        decisionSearchDTO = RegDTOFactory.createRegulationSearchDTO();
        this.setSearchType(0);
        this.getAll();
        this.setSearchMode(false);

    }

    public String back() {
   if(!isSearchMode())
       {
       setSearchQuery("");
       decisionSearchDTO = RegDTOFactory.createRegulationSearchDTO();
       }return null;

    }

    public String viewCancelDecisionDetails() {
        IDecisionsDTO decision = (IDecisionsDTO)getSelectedDTOS().get(0);
        try {
            if (decision.isCancelDecision()) {
                //IDecisionsDTO dto=(IDecisionsDTO)getClient().getById(decision.getCode());
                canceledDecisionslist = 
                        ((IDecisionsClient)getClient()).getCanceledDecisionList(decision.getCode());
                setContent1Div("divContent1Fixed");
                return "cancelDecisionDetails";
            }
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String goBack() {
        setContent1Div("divContent1Dynamic");
        /*if (DecisionMaintainBean.isIntegrationPage()) {
            return "decisionlist-integration";
        } else */{
            return "decisionlist";
        }
    }

    /**
     * Purpose:to validate where menu item of القرار المعدلenable used by cancelDecision
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora
     * 1.2 - Date:   
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public boolean enableDecisionsModfication(List<IDecisionsDTO> list) {
        System.out.println("tetst");
        for (IDecisionsDTO dto: list)
            if (!(dto.isValidDecision() || dto.isCancelDecision()))
                return false;
        return true;

    }

    public String cancelDecision() {

        if (enableDecisionsModfication((List)getSelectedDTOS())) {

            List<IBasicDTO> selectedDTOs = getSelectedDTOS();
            //IDecisionsDTO toBeCanceledDecision = null;
            try {
                List<IDecisionsDTO> toBeCanceledDecision = new ArrayList();
                if (selectedDTOs != null && selectedDTOs.size() != 0) {
                    for (int i = 0; i < selectedDTOs.size(); i++) {
                        //                        if (!((IDecisionsDTO)getClient().getById(selectedDTOs.get(i).getCode())).isCancelDecision()) {
                        //                            toBeCanceledDecision.add((IDecisionsDTO)getClient().getById(selectedDTOs.get(i).getCode()));
                        //                        } else {
                        //                            return null;
                        //                        }

                        toBeCanceledDecision.add((IDecisionsDTO)getClient().getById(selectedDTOs.get(i).getCode()));

                    }
                    //            if(toBeCanceledDecision.isCancelDecision()){
                    //                return null;
                    //            }
                } else {
                    return null;
                }

                IDecisionsDTO cancelDecision = 
                    RegDTOFactory.createDecisionsDTO();
                //cancelDecision.setIDecisionsDTO(toBeCanceledDecision);
                cancelDecision.setDecisionsDTOList(toBeCanceledDecision);
                //cancelDecision.setTypesDTO(toBeCanceledDecision.getTypesDTO());
                //cancelDecision.setSubjectsDTO(toBeCanceledDecision.getSubjectsDTO());
                //cancelDecision.setDecisionMakerTypesDTO(toBeCanceledDecision.getDecisionMakerTypesDTO());
                setCancelDescisionFlag(true);
                setPageDTO(cancelDecision);

                /*if (DecisionMaintainBean.isIntegrationPage()) {
                    setEditNavigationCase("decisionmaindatacancel-integration");
                    setAddNavigationCase("decisionmaindata-integration");
                } else */{
                    setEditNavigationCase("decisionmaindatacancel");
                    setAddNavigationCase("decisionmaindata");
                }
            } catch (SharedApplicationException e) {
                getSharedUtil().handleException(e);
                return null;
            } catch (DataBaseException e) {
                getSharedUtil().handleException(e);
                return null;
            } catch (Exception e) {
                getSharedUtil().handleException(e);
                return null;
            }

            return goAdd();
        } else
            getSharedUtil().handleException(new Exception(), 
                                            "com.beshara.csc.nl.reg.integration.presentation.resources.integration", 
                                            "decisions_modfication_error_message");

        return null;
    }

    /**
     * Purpose:to handle initialize method if bean used for integeration with other modules
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  25-12-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: overrided
     */
    public String goEdit() throws DataBaseException, 
                                  SharedApplicationException {
     
            if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {

            ManyToManyMaintainBaseBean maintainBean = (ManyToManyMaintainBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" + 
                                                                                                                  this.getAddBeanName() + 
                                                                                                                  "}").getValue(FacesContext.getCurrentInstance());

            setPageDTO(((IDecisionsClient)getClient()).getAllAndFirstPageOfEmpById(this.getSelectedDTOS().get(0).getCode()));
            maintainBean.setMaintainMode(1);
            this.initializeObjectBeforeMaintain();
            maintainBean.setPageDTO(this.getPageDTO());
 //           DecisionEmployeesMaintain decisionEmployeesMaintainBean =(DecisionEmployeesMaintain)evaluateValueBinding("decisionEmployeesMaintainBean");
            //decisionEmployeesMaintainBean.generatePagingRequestDTO("decisionEmployeesMaintainBean","initEmpBean");
          //  decisionEmployeesMaintainBean.setPagingRequestDTO(new PagingRequestDTO("decisionEmployeesMaintainBean","initEmpBean"));
//           if(((IDecisionsDTO)getPageDTO()).getEmpDecisionsDTOList()!=null)
//           {    List list= new ArrayList();
//                for(int i=0; i<((IDecisionsDTO)getPageDTO()).getEmpDecisionsDTOList().size();i++)
//                {
//                    list.add(((IEmpDecisionsDTO)((IDecisionsDTO)getPageDTO()).getEmpDecisionsDTOList().get(i)));
//                }
//               decisionEmployeesModelSessionBean.getOriginalMap().put(((IEmpDecisionsDTO)((IDecisionsDTO)getPageDTO()).getEmpDecisionsDTOList().get(0)).getPageNo(),list );
//           }
           if (getIntegrationBean() != null && 
            (getIntegrationBean().getInitializeMethod() != null && 
             getIntegrationBean().getInitializeMethod() != "")) {
            executeMethodBinding(getIntegrationBean().getInitializeMethod(),null);
        }
    }
        return getEditNavigationCase();
    
}

    public void setCancelDescisionFlag(boolean cancelDescisionFlag) {
        this.cancelDescisionFlag = cancelDescisionFlag;
    }

    public boolean isCancelDescisionFlag() {
        return cancelDescisionFlag;
    }

    public void selectedCheckboxs(ActionEvent event) throws Exception {
        super.selectedCheckboxs(event);
        if (this.getSelectedDTOS().size() == 1) {
            IDecisionsDTO selectedDecDto = 
                (IDecisionsDTO)this.getSelectedDTOS().get(0);
            if (selectedDecDto.isCancelDecision()) {
                setCancelDescisionFlag(false);
            } else {
                setCancelDescisionFlag(true);
            }
            if (selectedDecDto.isValidDecision()) {
                setValidDecision(true);
            } else {
                setValidDecision(false);
            }
            if (selectedDecDto.isCanceledDecision())
                setCancelDescisionFlag(true);
            else
                setCancelDescisionFlag(false);

        }
    }

    public void setValidDecision(boolean validDecision) {
        this.validDecision = validDecision;
    }

    public boolean isValidDecision() {
        return validDecision;
    }

    /**
     * Purpose:to handle initialize method if bean used for integeration with other modules
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  25-12-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: overrided
     */
    public String goAdd() {
        String addNavigationCase = super.goAdd();
        DecisionEmployeesModelSessionBean decisionEmployeesModelSessionBean =(DecisionEmployeesModelSessionBean)evaluateValueBinding("decisionEmployeesModelSessionBean");
        decisionEmployeesModelSessionBean.resetSessionData();
        if (getIntegrationBean() != null && 
            (getIntegrationBean().getInitializeMethod() != null && 
             getIntegrationBean().getInitializeMethod() != "")) {
            executeMethodBinding(getIntegrationBean().getInitializeMethod(), null);
        }
        return addNavigationCase;
    }

    public void setCanceledDecisionslist(List<IBasicDTO> canceledDecisionslist) {
        this.canceledDecisionslist = canceledDecisionslist;
    }

    public List<IBasicDTO> getCanceledDecisionslist() {
        return canceledDecisionslist;
    }

    public String copyDecisionWithEmployees() throws DataBaseException, 
                                                     SharedApplicationException {
        super.goEdit();
        ManyToManyMaintainBaseBean maintainBean = 
            (ManyToManyMaintainBaseBean)evaluateValueBinding(this.getAddBeanName());
        maintainBean.setMaintainMode(0);
        maintainBean.getPageDTO().setCode(null);
        ((IDecisionsDTO)maintainBean.getPageDTO()).setTypesDTO(null);
        ((IDecisionsDTO)maintainBean.getPageDTO()).setYearsDTO(null);
        ((IDecisionsDTO)maintainBean.getPageDTO()).setDecisionsDTOList(null);
        ((IDecisionsDTO)maintainBean.getPageDTO()).setDecisionsDTO(null);
        ((IDecisionsDTO)getPageDTO()).setDecisionImage(null);
        ((DecisionMaintainBean)maintainBean).setCopyDecisionWithEmployeesMode(true);
        ((IDecisionsDTO)maintainBean.getPageDTO()).setRegDecisionMaterialsDTOList(new ArrayList<IRegDecisionMaterialsDTO>());
        return getAddNavigationCase();
    }


    public String copyDecisionWithoutEmployees() throws DataBaseException, 
                                                        SharedApplicationException {
        super.goEdit();
        ManyToManyMaintainBaseBean maintainBean = 
            (ManyToManyMaintainBaseBean)evaluateValueBinding(this.getAddBeanName());
        maintainBean.setMaintainMode(0);
        maintainBean.getPageDTO().setCode(null);
        ((IDecisionsDTO)maintainBean.getPageDTO()).setTypesDTO(null);
        ((IDecisionsDTO)maintainBean.getPageDTO()).setYearsDTO(null);
        ((IDecisionsDTO)maintainBean.getPageDTO()).setDecisionsDTOList(null);
        ((IDecisionsDTO)maintainBean.getPageDTO()).setDecisionsDTO(null);
        ((IDecisionsDTO)getPageDTO()).setDecisionImage(null);
        ((IDecisionsDTO)maintainBean.getPageDTO()).setEmpDecisionsDTOList(new ArrayList<IBasicDTO>());
        ((IDecisionsDTO)maintainBean.getPageDTO()).setRegDecisionMaterialsDTOList(new ArrayList<IRegDecisionMaterialsDTO>());
        return getAddNavigationCase();
    }


    public void setTypeColumnVisible(boolean typeColumnVisible) {
        this.typeColumnVisible = typeColumnVisible;
    }

    public boolean isTypeColumnVisible() {
        return typeColumnVisible;
    }

    public void setYearColumnVisible(boolean yearColumnVisible) {
        this.yearColumnVisible = yearColumnVisible;
    }

    public boolean isYearColumnVisible() {
        return yearColumnVisible;
    }

    public void setNumberColumnVisible(boolean numberColumnVisible) {
        this.numberColumnVisible = numberColumnVisible;
    }

    public boolean isNumberColumnVisible() {
        return numberColumnVisible;
    }

    public void setTitleColumnVisible(boolean titleColumnVisible) {
        this.titleColumnVisible = titleColumnVisible;
    }

    public boolean isTitleColumnVisible() {
        return titleColumnVisible;
    }

    public void setDecisionMakerColumnVisible(boolean decisionMakerColumnVisible) {
        this.decisionMakerColumnVisible = decisionMakerColumnVisible;
    }

    public boolean isDecisionMakerColumnVisible() {
        return decisionMakerColumnVisible;
    }

    public void setDateColumnVisible(boolean dateColumnVisible) {
        this.dateColumnVisible = dateColumnVisible;
    }

    public boolean isDateColumnVisible() {
        return dateColumnVisible;
    }

    public void setApplyDateColumnVisible(boolean applyDateColumnVisible) {
        this.applyDateColumnVisible = applyDateColumnVisible;
    }

    public boolean isApplyDateColumnVisible() {
        return applyDateColumnVisible;
    }

    public void setStatusColumnVisible(boolean statusColumnVisible) {
        this.statusColumnVisible = statusColumnVisible;
    }

    public boolean isStatusColumnVisible() {
        return statusColumnVisible;
    }

    public void setCancelledDecisionFlag(boolean cancelledDecisionFlag) {
        this.cancelledDecisionFlag = cancelledDecisionFlag;
    }

    public boolean isCancelledDecisionFlag() {
        return cancelledDecisionFlag;
    }

    public void setRegStatusList(List regStatusList) {
        this.regStatusList = regStatusList;
    }

    public List getRegStatusList() throws DataBaseException, 
                                             SharedApplicationException {

         if (regStatusList == null) {
                            regStatusList = new ArrayList();
                        }
         if(regStatusList.size()==0){
            regStatusList = regStatusClient.getCodeName();
            }

            return regStatusList;
        }

    
}
