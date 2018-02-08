package com.beshara.csc.nl.reg.integration.presentation.backingbean.shared.financialdecision;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.transaction.TransactionException;
import com.beshara.common.shared.SharedUtils;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.ITemplatesClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegDecisionMaterialsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.IRegulationStatusEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegulationStatusEntityKey;
import com.beshara.csc.nl.reg.business.sharedUtil.IConstants;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.util.UtilBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.SystemException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.constants.IRegConstants;
import com.beshara.jsfbase.csc.backingbean.ManyToManyListBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.event.ActionEvent;


public abstract class FinancialDecisionBaseListBean extends ManyToManyListBaseBean {
    @SuppressWarnings("compatibility:9100080922733596613")
    private static final long serialVersionUID = 1L;

    private SharedUtilBean sharedUtil = getSharedUtil();
    private List<IBasicDTO> finDecisionStatusList;
    private Long selectedTypeKey;
    private IDecisionsDTO decisionDTO;
    private boolean cancelDescisionFlag = false;
    private IRegulationSearchDTO decisionSearchDTO;
    private List typesList;
    private List yearsList;
    private List<IBasicDTO> canceledDecisionslist;
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
    private boolean viewOnlyMode = true;
    private String stringSearchType = "false";
    private boolean showAllFlag;
    private Long selectedRegTypeKey;
    private boolean disabledButtonFlag;
    private boolean validDecisionForDelete;
    private boolean disapleButton = true;
    private Long systemSettingCode = null;
    protected static final String RESOURCE_PATH_INTG = "com.beshara.csc.nl.reg.integration.presentation.resources.integration";      
    protected static final String RESOURCE_PATH = "com.beshara.csc.nl.reg.presentation.resources.reg";
    
    private String reportUrl = "";
    private String templateName = "";
    private static final String JS_CHANGE_VISIBILITY_DIV_CUSTOM_DIV2 =
        "changeVisibilityDiv(window.blocker,window.customDiv2);";
    private List reportTemplatesList ;
    private String selectedRepTemplateKey = "";
    private boolean repTemplateDivDisplayed;
    private ITemplatesClient templatesClient = RegClientFactory.getTemplatesClient();
    private boolean showReportTemplatesCombo;
    private Long suggNum;
    private Long approvalNum;
    private Long auditNum;
    private int regSubject = 0;
    private boolean regSubjectCheckBox;
    private int persons = 0;
    private boolean personsCheckBox;
    private Date regDate = null;
    private boolean showRegDate  = false;
    Map reportsTemplate = new TreeMap();
    private boolean disableTemplatePrintDiv = true;
    final Integer ISSUED_DEC  = 0;
    final Integer ISSUED_DEC_WITH_DATE  = 1;
    final Integer RECOMMENDED_DEC  = 2;
   public final static Integer RECOMMENDED_DEC_WITH_DATE  = 3;
    // Start Apply Business_Pagging ..
    private String lisBeanName = "";
    private Long searchSubjectCode= null;
    private Boolean afterIssued = Boolean.FALSE;
    
    private Long STATUS_EXECUTED = 1L ;
    private Long STATUS_CANCELED = 2L ;

    // End Apply Business_Pagging ..
    
    private List<IBasicDTO> finDecisionStatusList2;


    public FinancialDecisionBaseListBean() {
        setClient(RegClientFactory.getDecisionsClient());
        setPageDTO(RegDTOFactory.createDecisionsDTO());
        setSelectedTypeKey(IRegConstants.REGULATION_STATUS_READY_REVISION);
        setSelectedPageDTO(RegDTOFactory.createDecisionsDTO());

        // Start Apply Business_Pagging ..
        setUsingPaging(true);
        setUsingBsnPaging(true);
        setSaveSortingState(true);
        setSearchDTO(RegDTOFactory.createRegulationSearchDTO());
        setFilterDTO(RegDTOFactory.createRegulationSearchDTO());
        // End Apply Business_Pagging ..

    }


    @Override
    public void initiateBeanOnce() {

        // Start Apply Business_Pagging ..
        decisionSearchDTO = RegDTOFactory.createRegulationSearchDTO();
        if(selectedTypeKey == null)
            setSelectedTypeKey(IRegConstants.REGULATION_STATUS_READY_REVISION);
        Long selFinDecisionTypeKey = getSelectedTypeKey();
        decisionSearchDTO.setRegStatusFlage(selFinDecisionTypeKey);
        if(selectedTypeKey.equals(STATUS_EXECUTED))
        {
            decisionSearchDTO.setRegStatusFlage2(STATUS_CANCELED);
        }else{
            decisionSearchDTO.setRegStatusFlage2(null);
        }
        setFilterDTO(decisionSearchDTO);
        getFilterDTO().setName("ddddddd");
        setUpdateMyPagedDataModel(true);
        // End Apply Business_Pagging ..
        fillFinDecisionStatusList();
        loadReportTemplates();

    }


    public void loadReportTemplates() {
        
//        reportsTemplate.put(1,
//                            "rep=831&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
//        reportsTemplate.put(2,
//                            "rep=832&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
//        reportsTemplate.put(3, "rep=833&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_REGYEAR=&_paramsP_REGTYPE=&_paramsP_REGNO=&_paramsP_WATER_MARK=");
//        reportsTemplate.put(4, "rep=834&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DECYEAR=&_paramsP_DATE=&_paramsP_REGYEAR=&_paramsP_REGTYPE=&_paramsP_REGNO=&_paramsP_WATER_MARK=");
//        reportsTemplate.put(5,
//                            "rep=835&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
//        reportsTemplate.put(6,
//                            "rep=836&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
//        reportsTemplate.put(7,
//                            "rep=837&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
//        reportsTemplate.put(8, "rep=838&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_REGYEAR=&_paramsP_REGTYPE=&_paramsP_REGNO=&_paramsP_WATER_MARK=");
//        reportsTemplate.put(9,
//                            "rep=839&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
        reportsTemplate.put(ISSUED_DEC.intValue(),
                            "rep=995&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
        reportsTemplate.put(ISSUED_DEC_WITH_DATE.intValue(),
                            "rep=996&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
        reportsTemplate.put(RECOMMENDED_DEC.intValue(),
                            "rep=997&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
        reportsTemplate.put(RECOMMENDED_DEC_WITH_DATE.intValue(),
                            "rep=1000&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
                            //"rep=998&_paramsP_PERSONS=&_paramsP_MAT=&_paramsP_DATE=&_paramsP_WATER_MARK=&_paramsP_DECYEAR=&_paramsP_DECTYPE=&_paramsP_DECNO=");
       
       
    }

    private String setRegNumber(IDecisionsDTO decisionsDTO) throws Exception {

        StringBuffer decisionDoneNumber = new StringBuffer("");
        Long decTypeCode = ((IDecisionsEntityKey)decisionsDTO.getCode()).getDectypeCode();
        Long decYear = ((IDecisionsEntityKey)decisionsDTO.getCode()).getDecyearCode();
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();

        String regSerial =
            ((IDecisionsClient)getClient()).getSerialByMinAndYearAndType(decTypeCode, decYear, minCode).toString();

        String yearCode = (decYear.toString()).substring(2);

        decisionDoneNumber.append(yearCode);
        decisionDoneNumber.append(minCode);
        decisionDoneNumber.append(decTypeCode);
        decisionDoneNumber.append("/");
        decisionDoneNumber.append(regSerial);
        decisionsDTO.setRegNum(decisionDoneNumber.toString());

        return decisionDoneNumber.toString();
    }
    public void selectedRegType(ActionEvent e) {
        e = null;
        setRegDate(null);
        Long calShow1 = 1L;
        Long calShow2 = 3L;
        
        if ((selectedRepTemplateKey != null &&
            selectedRepTemplateKey.equals(calShow1.toString()))/*||
            selectedRepTemplateKey != null &&
                        selectedRepTemplateKey.equals(calShow2.toString())*/ ) {
            setShowRegDate(true);
        } else {
            setShowRegDate(false);
        }

    }

    public void setSelectedTypeKey(Long selectedRegTypeKey) {
        this.selectedTypeKey = selectedRegTypeKey;
    }

    public Long getSelectedTypeKey() {
        return selectedTypeKey;
    }

    public void setDecisionDTO(IDecisionsDTO getAllDTO) {
        this.decisionDTO = getAllDTO;
    }

    public IDecisionsDTO getDecisionDTO() {
        return decisionDTO;
    }

    public void setCancelDescisionFlag(boolean cancelDescisionFlag) {
        this.cancelDescisionFlag = cancelDescisionFlag;
    }

    public boolean isCancelDescisionFlag() {
        return cancelDescisionFlag;
    }

    public void setDecisionSearchDTO(IRegulationSearchDTO decisionSearchDTO) {
        this.decisionSearchDTO = decisionSearchDTO;
    }

    public IRegulationSearchDTO getDecisionSearchDTO() {
        return decisionSearchDTO;
    }


    public void setCanceledDecisionslist(List<IBasicDTO> canceledDecisionslist) {
        this.canceledDecisionslist = canceledDecisionslist;
    }

    public List<IBasicDTO> getCanceledDecisionslist() {
        return canceledDecisionslist;
    }

    public void setValidDecision(boolean validDecision) {
        this.validDecision = validDecision;
    }

    public boolean isValidDecision() {
        return validDecision;
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

    public void setViewOnlyMode(boolean viewOnlyMode) {
        this.viewOnlyMode = viewOnlyMode;
    }

    public boolean isViewOnlyMode() {
        return viewOnlyMode;
    }

    public List getTypesList() {
        return typesList;
    }

    public void setTypesList(List typesList) {
        this.typesList = typesList;
    }

    public void setYearsList(List yearsList) {
        this.yearsList = yearsList;
    }

    public List getYearsList() {
        return yearsList;
    }

    public void setStringSearchType(String stringSearchType) {
        this.stringSearchType = stringSearchType;
    }

    public String getStringSearchType() {
        return stringSearchType;
    }

    public void setShowAllFlag(boolean showAllFlag) {
        this.showAllFlag = showAllFlag;
    }

    public boolean isShowAllFlag() {
        return showAllFlag;
    }

    public void setSelectedRegTypeKey(Long selectedRegTypeKey) {
        this.selectedRegTypeKey = selectedRegTypeKey;
    }

    public Long getSelectedRegTypeKey() {
        return selectedRegTypeKey;
    }

    public void setValidDecisionForDelete(boolean validDecisionForDelete) {
        this.validDecisionForDelete = validDecisionForDelete;
    }

    public boolean isValidDecisionForDelete() {

        return validDecisionForDelete;
    }

    public void setDisabledButtonFlag(boolean disabledButtonFlag) {
        this.disabledButtonFlag = disabledButtonFlag;
    }

    public boolean isDisabledButtonFlag() {
        setDisabledButtonFlag(false);
        return disabledButtonFlag;
    }


    public void setDisapleButton(boolean disapleButton) {
        this.disapleButton = disapleButton;
    }

    public boolean isDisapleButton() {
        return disapleButton;
    }

    public void selectedRadioButton(ActionEvent actionEvent) throws Exception {
        super.selectedRadioButton(actionEvent);
        disapleButton = true;
        validDecisionForDelete = false;
        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            Long regStatusCode =
                ((IRegulationStatusEntityKey)((IDecisionsDTO)getSelectedDTOS().get(0)).getRegStatusDTO().getCode()).getRegstatusCode();
            if (!regStatusCode.equals(IConstants.REG_APPROVED_STATUS)) {
                disapleButton = false;
                validDecisionForDelete = true;
            }
            if(regStatusCode.equals(IConstants.REG_CANCELED_STATUS) )
            {
                validDecisionForDelete = false;

            }
 
        }


    }

    /**
     * @param dtoForExecute
     */
    public abstract IBasicDTO issueDecision(IDecisionsDTO dtoForExecute) throws SharedApplicationException,
                                                                           DataBaseException;

    public void executeDecision() {

        String decisionDoneNumber = null;
        IDecisionsDTO dtoForExecute = (IDecisionsDTO)getSelectedDTOS().get(0);
        dtoForExecute.setDecisionDate(SharedUtils.getTimestamp() );
        Timestamp decAppDate = dtoForExecute.getDecisionAppliedDate();
        System.out.println("executeDecision SharedUtils.getTimestamp()) = " + SharedUtils.getTimestamp() + " , dtoForExecute.getDecisionAppliedDate() = " + dtoForExecute.getDecisionAppliedDate() );
        if(decAppDate == null || decAppDate.compareTo(SharedUtils.getTimestamp() ) < 0 )
        {
            dtoForExecute.setDecisionAppliedDate(SharedUtils.getTimestamp() );
        }
        dtoForExecute.setApprovalDate(com.beshara.common.shared.SharedUtils.getSqlDate());
        dtoForExecute.setUserCodeApproval(CSCSecurityInfoHelper.getUserCode());
        dtoForExecute.setUserCodeAudit(CSCSecurityInfoHelper.getUserCode());
        dtoForExecute.setAuditDate(com.beshara.common.shared.SharedUtils.getSqlDate());
        dtoForExecute.setDecCancelDate(null);
        dtoForExecute.setDecCancelAppliedDate(null);
        dtoForExecute.setDecCancelUser(null);
        try {
//            decisionDoneNumber = setRegNumber(dtoForExecute);
           IDecisionsDTO decisionsDTO =(IDecisionsDTO) issueDecision(dtoForExecute);
            try {
                getSelectedDTOS().clear();
                getSelectedDTOS().add(decisionsDTO);
                decisionDoneNumber = decisionsDTO.getRegNum();
                setSelectedRepTemplateKey(ISSUED_DEC_WITH_DATE+ "");
                setRegDate(decisionsDTO.getDecisionDate());
                setAfterIssued(Boolean.TRUE);
                setSelectedRadio("");
                openReport();
            } catch (Exception e) {
                // TODO: Add catch code
                e.printStackTrace();
            }
            // Start Apply Business_Pagging ..
            cancelSearch();
            // End Apply Business_Pagging ..
            getAll();
            String message =
                sharedUtil.messageLocator("com.beshara.csc.nl.reg.integration.presentation.resources.integration",
                                          "SuccesExcute");
            getSharedUtil().setSuccessMsgValue(message + " " + decisionDoneNumber);
            
            
        } catch (DataBaseException e) {
            sharedUtil.handleException(e);
        } catch (ItemNotFoundException item) {
            sharedUtil.handleException(item);
        } catch (SharedApplicationException e) {
            sharedUtil.handleException(e);
        } catch (Exception e) {
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
        }
        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            getSelectedDTOS().clear();
        }
        
    }

    public void preExecuteDecision() {

    }

    public List getTotalList() {

        List<IBasicDTO> totalList = new ArrayList<IBasicDTO>();
        Long selFinDecisionTypeKey = getSelectedTypeKey();
        if (systemSettingCode != null && selFinDecisionTypeKey != null) {
            try {
                Long subjectCode = UtilBean.getMappedCodeBySysSettingCode(systemSettingCode);


                //                    if(selFinDecisionTypeKey != null)
                //                    {

                //            Long userCode = CSCSecurityInfoHelper.getUserCode();
                //            getGetAllDTO().setCreatedBy(userCode);
                //totalList = ((IDecisionsClient)getClient()).getAllByRegStatusAndAppMod(selFinDecisionTypeKey,appModuleCode);
                if(selFinDecisionTypeKey.equals(STATUS_EXECUTED))
                {
                     totalList =
                             ((IDecisionsClient)getClient()).getAllByRegStatusAndSubCodes(selFinDecisionTypeKey, STATUS_CANCELED , subjectCode);    
                    
                 }else
                totalList =
                        ((IDecisionsClient)getClient()).getAllByRegStatusAndSubCode(selFinDecisionTypeKey, subjectCode);
                //                }else{
                //                    totalList = ((IDecisionsClient)getClient()).getAll();
                //                }
            } catch (SharedApplicationException ne) {
                totalList = new ArrayList();
                ne.printStackTrace();
            } catch (DataBaseException db) {
                //getSharedUtil().handleException(db);
                totalList = new ArrayList();
            } catch (Exception e) {
                getSharedUtil().handleException(e);
                e.printStackTrace();
                totalList = new ArrayList();
            }
        }
        return totalList;

    }
    
    public void fillDropDownListForSearch() {
        if (typesList == null) {
            fillTypesList();
        }
        if (yearsList == null) {
            fillYearsList();
        }
//        decisionSearchDTO = RegDTOFactory.createRegulationSearchDTO();
        decisionSearchDTO.setRegStatusFlage(getSelectedTypeKey());
        
        if(selectedTypeKey.equals(STATUS_EXECUTED ))
        {
            decisionSearchDTO.setRegStatusFlage2(STATUS_CANCELED);
        }else{
            decisionSearchDTO.setRegStatusFlage2(null);
            }
    }

    private void fillTypesList() {
        try {
            typesList = RegClientFactory.getTypesClient().getCodeName();
        } catch (DataBaseException e) {
            e.printStackTrace();
            typesList = new ArrayList();
        }
    }

    private void fillYearsList() {
        try {
            yearsList = InfClientFactory.getYearsClient().getCodeName();
        } catch (DataBaseException e) {
            yearsList = new ArrayList();
        } catch (SharedApplicationException e) {
            yearsList = new ArrayList();
        }
        if (yearsList == null) {
            yearsList = new ArrayList();
        }
    }

    private void fillFinDecisionStatusList() {
        if (finDecisionStatusList == null || finDecisionStatusList.size() == 0) {
            try {
                finDecisionStatusList = RegClientFactory.getRegulationStatusClient().getStatusForFinancialDecisions();
            } catch (SharedApplicationException e) {
                finDecisionStatusList = new ArrayList();
            } catch (DataBaseException e) {
                finDecisionStatusList = new ArrayList();
            }
        }
    }


    public void initializeObjectBeforeMaintain() {

        IDecisionsDTO dto = (IDecisionsDTO)getPageDTO();
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


    }


    // Update By A.AbdelHalim
    // for User Story CSC-14722
    // 12-OCT-2015

    public List getSearchResult() {
        List searchResult = null;
        setShowAllFlag(false);
        try {
            //decisionSearchDTO.setAppModuleCode(IConstants.APP_MODULE_CODE_FOR_MER);
            decisionSearchDTO.setSubjectCode(UtilBean.getMappedCodeBySysSettingCode(systemSettingCode));
            searchResult = RegClientFactory.getDecisionsClient().searchInFinancialDecisions(decisionSearchDTO);
            Long regStatusCode =
                ((RegulationStatusEntityKey)((IDecisionsDTO)searchResult.get(0)).getRegStatusDTO().getCode()).getRegstatusCode();
            setSelectedTypeKey(regStatusCode);
        } catch (ItemNotFoundException e) { //this means no search results found
            searchResult = new ArrayList();
            setSelectedTypeKey(getDecisionSearchDTO().getRegStatusFlage());
            e.printStackTrace();
        } catch (NoResultException e) { //this means no search results found
            searchResult = new ArrayList();
            setSelectedTypeKey(getDecisionSearchDTO().getRegStatusFlage());
            e.printStackTrace();
        } catch (Exception e) {
            searchResult = new ArrayList();
            setSelectedTypeKey(getDecisionSearchDTO().getRegStatusFlage());
            e.printStackTrace();
        }
        return searchResult;
    }

    public String back() {
        regSubject = getDecisionSearchDTO().getSubjectCode().intValue();
        Long subjectCode2=getDecisionSearchDTO().getSubjectCode2();
        decisionSearchDTO = RegDTOFactory.createRegulationSearchDTO();
        decisionSearchDTO.setSubjectCode(Long.valueOf(""+regSubject));
        decisionSearchDTO.setSubjectCode2(subjectCode2);
        decisionSearchDTO.setRegStatusFlage(getSelectedTypeKey());

        if (selectedTypeKey.equals(STATUS_EXECUTED)) {
            decisionSearchDTO.setRegStatusFlage2(STATUS_CANCELED);
        } else {
            decisionSearchDTO.setRegStatusFlage2(null);
        }
        return null;
    }
    public void showReportTemplateDiv() {
        loadReportTemList();
        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv2);");
        getSharedUtil().setErrMsgValue(null);
        setRepTemplateDivDisplayed(true);
        setSelectedRepTemplateKey("");
        setRegSubjectCheckBox(false);
        setRegSubject(0);
        setPersonsCheckBox(false);
        setPersons(0);
        setRegDate(null);
        setShowRegDate(false);
    }

    public void hideReportTemplateDiv() {
//        getSelectedDTOS().clear();
//        setSelectedRadio("");
//        disapleButton = true;
        setRepTemplateDivDisplayed(false);

    }
    public void loadReportTemList() {
        Long decStatusCode = null;
        String regFinancialStatus  = "";
        IBasicDTO selectedDTO = null;
        IBasicDTO reportsTitle ;
        EntityKey key ;
        try {
            if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
                selectedDTO = getSelectedDTOS().get(0);
                decStatusCode =
                        ((IRegulationStatusEntityKey)((IDecisionsDTO)selectedDTO).getRegStatusDTO().getCode()).getRegstatusCode();
                if (decStatusCode.equals(IRegConstants.REGULATION_STATUS_READY_REVISION)) {
                    reportTemplatesList =new ArrayList();
//                    reportsTitle  =  new BasicDTO();
//                    key= new EntityKey(RECOMMENDED_DEC);
//                    reportsTitle.setCode(key);
//                    reportsTitle.setName(getSharedUtil().messageLocator(RESOURCE_PATH_INTG,"reg_rep_not_issued"));
//                    reportTemplatesList.add(reportsTitle);
                    reportsTitle  =  new BasicDTO();
                    key= new EntityKey(RECOMMENDED_DEC_WITH_DATE);
                    reportsTitle.setCode(key);
                    reportsTitle.setName(getSharedUtil().messageLocator(RESOURCE_PATH_INTG,"reg_rep_not_issued_with_date"));
                    reportTemplatesList.add(reportsTitle);

                } else if (decStatusCode.equals(IRegConstants.REGULATION_STATUS_ACTIVE)) {
                    reportTemplatesList =new ArrayList();
                    reportsTitle  =  new BasicDTO();
                    key= new EntityKey(ISSUED_DEC);
                    reportsTitle.setCode(key);
                    reportsTitle.setName(getSharedUtil().messageLocator(RESOURCE_PATH_INTG,"reg_rep_issued"));
                    reportTemplatesList.add(reportsTitle);
                    reportsTitle  =  new BasicDTO();
                    key= new EntityKey(ISSUED_DEC_WITH_DATE);
                    reportsTitle.setCode(key);
                    reportsTitle.setName(getSharedUtil().messageLocator(RESOURCE_PATH_INTG,"reg_rep_issued_with_date"));
                    reportTemplatesList.add(reportsTitle);
                }
                
//                reportTemplatesList =
//                        templatesClient.getByValidityCodeAndStatusCodeForFinancialReports(null, FINANCIAL_REPORT_IN_REG, FINANCIAL_REPORT_IN_REG_With_Date);
            }

            if (reportTemplatesList == null  || reportTemplatesList.isEmpty() ) {
                setShowReportTemplatesCombo(false);
            } else {
    //                ITemplatesDTO templateDTO = RegDTOFactory.createTemplatesDTO();
    //                templateDTO.setCode(RegEntityKeyFactory.createTemplatesEntityKey(IConstants.DEC_TEMPLATE_CODE_FIRST_COPY));
    //                reportTemplatesList.remove(templateDTO);
                setShowReportTemplatesCombo(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            setShowReportTemplatesCombo(false);
        }
    }
    
    public void openReport() {
        openReport((IDecisionsDTO)getSelectedDTOS().get(0) );
    }
    public void openReport(IDecisionsDTO dto) {
        if (reportTemplatesList == null || reportTemplatesList.size() == 0) {
            loadReportTemList();
        }
        int template_key = Integer.parseInt(getSelectedRepTemplateKey());
        reportUrl = (String)reportsTemplate.get(template_key);
        
        templateName = getReportTemplateName(getSelectedRepTemplateKey());
        String P_WATER_MARK = "P_WATER_MARK";
        String P_DECNO = "P_DECNO";
        // 19-10-2015
        String P_DECYEAR = "P_DECYEAR";
        String P_DECTYPE = "P_DECTYPE";
        String P_DATE = "P_DATE";
        String P_MAT = "P_MAT";
        String P_PERSONS = "P_PERSONS";
        IDecisionsEntityKey decision_EK = (IDecisionsEntityKey)dto.getCode();
        if (reportUrl.indexOf(P_DECYEAR) != -1) {

            reportUrl = reportUrl.replaceFirst("P_DECYEAR" + "=", P_DECYEAR + "=" + decision_EK.getDecyearCode());
        }

        if (reportUrl.indexOf(P_DECTYPE) != -1) {
            reportUrl = reportUrl.replaceFirst("P_DECTYPE" + "=", P_DECTYPE + "=" + decision_EK.getDectypeCode());
        }

        //  end 19-10-2015

        if (reportUrl.indexOf(P_WATER_MARK) != -1) {
            reportUrl = reportUrl.replaceFirst(P_WATER_MARK + "=", P_WATER_MARK + "=" + templateName);
        }
       
        if (reportUrl.indexOf(P_DECNO) != -1 && (template_key == ISSUED_DEC  || template_key == RECOMMENDED_DEC)) {
            reportUrl =
                    reportUrl.replaceFirst("P_DECNO" + "=", P_DECNO + "=" + decision_EK.getDecisionNumber()); // the DecisionNumber of the PK.
        }
        if (reportUrl.indexOf(P_DECNO) != -1 && (template_key == ISSUED_DEC_WITH_DATE )) {
            reportUrl =
                                reportUrl.replaceFirst("P_DECNO" + "=", P_DECNO + "=" + decision_EK.getDecisionNumber() );
        }
        if (reportUrl.indexOf(P_DECNO) != -1 && (template_key == RECOMMENDED_DEC_WITH_DATE )) {
            //if(((IDecisionsDTO)getSelectedDTOS().get(0)).getRegAutoNumber() !=null){
            reportUrl =
                    reportUrl.replaceFirst("P_DECNO" + "=", P_DECNO + "=" + decision_EK.getDecisionNumber() );
            /*}else{
                    reportUrl =
                            reportUrl.replaceFirst("P_DECNO" + "=", P_DECNO + "=" + ((IDecisionsDTO)getSelectedDTOS().get(0)).getRegNum());    
            }*/
        }
        
        if (reportUrl.indexOf(P_DATE) != -1 && (template_key == ISSUED_DEC  || template_key == RECOMMENDED_DEC || template_key == RECOMMENDED_DEC_WITH_DATE ) ) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String decDate = sdf.format(dto.getDecisionDate());
            reportUrl = reportUrl.replaceFirst("P_DATE" + "=", P_DATE + "=" + decDate);
        }
        if (regDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String decDate = sdf.format(regDate);
            reportUrl = reportUrl.replaceFirst("P_DATE" + "=", P_DATE + "=" + decDate);
        }
        if (reportUrl.indexOf(P_MAT) != -1 &&  (template_key == ISSUED_DEC  || template_key == RECOMMENDED_DEC) ) {
            setRegSubject(0);// there is no Subject for Financial Decision by Moshoir..
            reportUrl = reportUrl.replaceFirst("P_MAT" + "=", P_MAT + "=" + getRegSubject());

        }
//        if (reportUrl.indexOf(P_MAT) != -1 &&  template_key !=FINANCIAL_REPORT_IN_REG.intValue() ) {
//            reportUrl = reportUrl.replaceFirst("P_MAT" + "=", P_MAT + "=" + getRegSubject());
//        }
        
        if (reportUrl.indexOf(P_PERSONS) != -1) {
            reportUrl = reportUrl.replaceFirst("P_PERSONS" + "=", P_PERSONS + "=" + getPersons());

        }
        setAfterIssued(Boolean.FALSE);
        System.out.println("reportUrl:\n"+reportUrl);
        String stringcaller = "openReportWindow('reportUrl')";
        
        setJavaScriptCaller(stringcaller);
        
    }
    private String getReportTemplateName(String templateKey) {
//        List<IBasicDTO> list = reportTemplatesList;
        String repTemplateName = "";
//        for (IBasicDTO dto : list) {
//            IBasicDTO templateDTO = dto;
//            if (templateKey.equals(templateDTO.getCode().getKey())) {
//                repTemplateName = templateDTO.getName();
//            }
//
//        }
        Long decStatusCode = null;
        IBasicDTO selectedDTO = null;
        try {
            if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
                selectedDTO = getSelectedDTOS().get(0);
                decStatusCode =
                        ((IRegulationStatusEntityKey)((IDecisionsDTO)selectedDTO).getRegStatusDTO().getCode()).getRegstatusCode();
                if(afterIssued)
                    repTemplateName = getSharedUtil().messageLocator(RESOURCE_PATH, "executedDecision");
                else {
                    if (decStatusCode.equals(IRegConstants.REGULATION_STATUS_READY_REVISION)) {
                        repTemplateName = getSharedUtil().messageLocator(RESOURCE_PATH, "copy_for_print");
                    } else if (decStatusCode.equals(IRegConstants.REGULATION_STATUS_ACTIVE)) {
                        
                        repTemplateName = getSharedUtil().messageLocator(RESOURCE_PATH, "copy_same_as_original");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            setShowReportTemplatesCombo(false);
        }
        
        return repTemplateName;
    }


    public abstract boolean executeDelete() throws DataBaseException, SharedApplicationException;

    public void deleteDiv() {
        boolean successDelete = false;
        IResultDTO resultDTO = new ResultDTO();
        resultDTO.setCurrentObject(getSelectedDTOS().get(0));
        try {
            // successDelete =RegClientFactory.getDecisionsClient().removeEntitlementsDecision(getSelectedDTOS().get(0));
            successDelete = executeDelete();
            if (successDelete) {
                ;
                resultDTO.setStatus("Deleted");
            }
            cancelSearch();
            setSelectedTypeKey(IRegConstants.REGULATION_STATUS_READY_REVISION);
        } catch (ItemNotFoundException e) {
            resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
        } catch (NoResultException e) {
            resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
        } catch (SharedApplicationException e) {
            resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
            resultDTO.setBusinessException(new SharedApplicationException(e.getMessage()));
        } catch (DataBaseException e) {
            resultDTO.setDatabaseException(new DataBaseException(e.getMessage()));
            resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
        } catch (TransactionException e) {
            resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
            resultDTO.setDatabaseException(new DataBaseException(new SystemException(e).getSQLExceptionMessage()));
        }
        getDeleteStatusDTOS().add(resultDTO);
        getSelectedDTOS().clear();
        fillDataTable();

        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");
    }

    public void showAllDecision() {
        try {
            setSorting(true);
            setUpdateMyPagedDataModel(true);
            setOldPageIndex(0);
            setCurrentPageIndex(1);
            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();
            this.repositionPage(0);
            this.setStringSearchType("false");
            setSelectedRadio("");
            getMyDataTable().setFirst(0);
            setSearchMode(true);
            setShowAllFlag(true);
            setDecisionSearchDTO(RegDTOFactory.createRegulationSearchDTO());
            getAll();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void cancelSearch() throws DataBaseException {
        // to keep the subject code..when cancel search to be used in the ressted DTO;
        regSubject = decisionSearchDTO.getSubjectCode().intValue();
        Long subjectCode2=getDecisionSearchDTO().getSubjectCode2();
        // create rest DTO with the new criteria "regStatusFlag" selected from the search Div
        decisionSearchDTO = RegDTOFactory.createRegulationSearchDTO();
        decisionSearchDTO.setSubjectCode(Long.valueOf(""+regSubject));
        decisionSearchDTO.setSubjectCode2(subjectCode2);
        decisionSearchDTO.setRegStatusFlage(getSelectedTypeKey());
        
        if(selectedTypeKey.equals(STATUS_EXECUTED) )
        {
            decisionSearchDTO.setRegStatusFlage2(STATUS_CANCELED);
        }else{
            decisionSearchDTO.setRegStatusFlage2(null);
            }
        
        setFilterDTO(decisionSearchDTO);
        setSearchDTO(RegDTOFactory.createRegulationSearchDTO());
        //..........
        
        this.setSearchQuery("");
        this.setSearchType(0);
        this.setSearchMode(false);
        this.setSelectedRadio("");
        if (isUsingPaging()) {
            getPagingBean().cancelSearch();
        } else {
            this.getAll();
            repositionPage(0);
        }
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
                // Start Apply Business_Pagging ..
                if (getSearchSubjectCode() != null) {
                    decisionSearchDTO.setSubjectCode(getSearchSubjectCode());
                }
                // End Apply Business_Pagging .              
                                    
                if (isUsingPaging()) {
                    // Start Apply Business_Pagging ..
                    setSearchDTO(decisionSearchDTO);
                    setUpdateMyPagedDataModel(true);
                    generatePagingRequestDTO(getLisBeanName(), "baseSearchWithPaging");
                    // End Apply Business_Pagging ..
                    setOldPageIndex(0);
                    setCurrentPageIndex(1);
                    getMyDataTable().setFirst(0);
                    unCheck();
                    setSelectedTypeKey(decisionSearchDTO.getRegStatusFlage());
                    if(decisionSearchDTO.getRegStatusFlage()==STATUS_CANCELED)
                     setSelectedTypeKey(STATUS_EXECUTED);
                } else {
                    this.setMyTableData(getSearchResult());
                }
                    if (getSelectedDTOS() != null)
                        getSelectedDTOS().clear();
                    if (getHighlightedDTOS() != null)
                        getHighlightedDTOS().clear();
                    unCheck();
                    this.repositionPage(0);
                    this.setStringSearchType("false");
                // Start Apply Business_Pagging ..
                    this.setSelectedRadio("");
                // End Apply Business_Pagging ..
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setFinDecisionStatusList(List<IBasicDTO> finDecisionStatusList) {
        this.finDecisionStatusList = finDecisionStatusList;
    }

    public List<IBasicDTO> getFinDecisionStatusList() {
        return finDecisionStatusList;
    }

    public void sortDataTable(ActionEvent event) {
        ((IRegulationSearchDTO)getFilterDTO()).setRegStatusFlage(getSelectedTypeKey());
        
        if(selectedTypeKey.equals(STATUS_EXECUTED) )
        {
           ((IRegulationSearchDTO)getFilterDTO()).setRegStatusFlage2(STATUS_CANCELED);
        }else{
            ((IRegulationSearchDTO)getFilterDTO()).setRegStatusFlage2(null);
            }
        super.sortDataTable(event);
    }
    
    public void fillDataTable() {
        getDecisionSearchDTO().setRegStatusFlage(getSelectedTypeKey());
        ((IRegulationSearchDTO)getFilterDTO()).setRegStatusFlage(getSelectedTypeKey());
        
        if(selectedTypeKey.equals(STATUS_EXECUTED ))
        {
            getDecisionSearchDTO().setRegStatusFlage2(STATUS_CANCELED);
           ((IRegulationSearchDTO)getFilterDTO()).setRegStatusFlage2(STATUS_CANCELED);
        }else{
            
                getDecisionSearchDTO().setRegStatusFlage2(null);
                ((IRegulationSearchDTO)getFilterDTO()).setRegStatusFlage2(null);
            }
            
        setSorting(true);
        setUpdateMyPagedDataModel(true);
        setOldPageIndex(0);
        setCurrentPageIndex(1);
        setCheckAllFlag(false);
        if (getSelectedDTOS() != null)
            getSelectedDTOS().clear();
        if (getHighlightedDTOS() != null)
            getHighlightedDTOS().clear();
        this.repositionPage(0);
        //        this.setStringSearchType("false");
        setSelectedRadio("");
        getMyDataTable().setFirst(0);
        try {
            getAll();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void setSystemSettingCode(Long systemSettingCode) {
        this.systemSettingCode = systemSettingCode;
    }

    public Long getSystemSettingCode() {
        return systemSettingCode;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setReportTemplatesList(List reportTemplatesList) {
        this.reportTemplatesList = reportTemplatesList;
    }

    public List getReportTemplatesList() {
        return reportTemplatesList;
    }

    public void setSelectedRepTemplateKey(String selectedRepTemplateKey) {
        this.selectedRepTemplateKey = selectedRepTemplateKey;
    }

    public String getSelectedRepTemplateKey() {
        return selectedRepTemplateKey;
    }

    public void setRepTemplateDivDisplayed(boolean repTemplateDivDisplayed) {
        this.repTemplateDivDisplayed = repTemplateDivDisplayed;
    }

    public boolean isRepTemplateDivDisplayed() {
        return repTemplateDivDisplayed;
    }

    public void setTemplatesClient(ITemplatesClient templatesClient) {
        this.templatesClient = templatesClient;
    }

    public ITemplatesClient getTemplatesClient() {
        return templatesClient;
    }

    public void setShowReportTemplatesCombo(boolean showReportTemplatesCombo) {
        this.showReportTemplatesCombo = showReportTemplatesCombo;
    }

    public boolean isShowReportTemplatesCombo() {
        return showReportTemplatesCombo;
    }

    public void setSuggNum(Long suggNum) {
        this.suggNum = suggNum;
    }
    public void showRegSubjects(ActionEvent e) {
        e = null;
        if (regSubjectCheckBox) {
            regSubject = 1;
        } else {
            regSubject = 0;
        }
    }

    public Long getSuggNum() {
        try {
            suggNum = ((IDecisionsClient)getClient()).suggNum();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        return suggNum;
    }

    public void setApprovalNum(Long approvalNum) {
        this.approvalNum = approvalNum;
    }

    public Long getApprovalNum() {
        try {
            approvalNum = ((IDecisionsClient)getClient()).approvalNum();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        return approvalNum;
    }

    public void setAuditNum(Long auditNum) {
        this.auditNum = auditNum;
    }

    public void showRegPersons(ActionEvent e) {
        e = null;
        if (personsCheckBox) {
            persons = 1;
        } else {
            persons = 0;
        }
    }
    public Long getAuditNum() {
        try {
            auditNum = ((IDecisionsClient)getClient()).auditNum();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        return auditNum;
    }


    public void setRegSubject(int regSubject) {
        this.regSubject = regSubject;
    }

    public int getRegSubject() {
        return regSubject;
    }

    public void setRegSubjectCheckBox(boolean regSubjectCheckBox) {
        this.regSubjectCheckBox = regSubjectCheckBox;
    }

    public boolean isRegSubjectCheckBox() {
        return regSubjectCheckBox;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersonsCheckBox(boolean personsCheckBox) {
        this.personsCheckBox = personsCheckBox;
    }

    public boolean isPersonsCheckBox() {
        return personsCheckBox;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setShowRegDate(boolean showRegDate) {
        this.showRegDate = showRegDate;
    }

    public boolean isShowRegDate() {
        return showRegDate;
    }

    public void setReportsTemplate(Map reportsTemplate) {
        this.reportsTemplate = reportsTemplate;
    }

    public Map getReportsTemplate() {
        return reportsTemplate;
    }

    public void setDisableTemplatePrintDiv(boolean disableTemplatePrintDiv) {
        this.disableTemplatePrintDiv = disableTemplatePrintDiv;
    }

    public boolean isDisableTemplatePrintDiv() {
        if(selectedTypeKey != null && (selectedTypeKey.equals(IRegConstants.REGULATION_STATUS_ACTIVE)
           || selectedTypeKey.equals(IRegConstants.REGULATION_STATUS_READY_REVISION) ) )
            disableTemplatePrintDiv = false;
        else disableTemplatePrintDiv = true;
        return disableTemplatePrintDiv;
    }

    public void setLisBeanName(String lisBeanName) {
        this.lisBeanName = lisBeanName;
    }

    public String getLisBeanName() {
        return lisBeanName;
    }

    public void setSearchSubjectCode(Long searchSubjectCode) {
        this.searchSubjectCode = searchSubjectCode;
    }

    public Long getSearchSubjectCode() {
        return searchSubjectCode;
    }

    public void setAfterIssued(Boolean afterIssued) {
        this.afterIssued = afterIssued;
    }

    public Boolean getAfterIssued() {
        return afterIssued;
    }


    public void preCancelDecision() {
    }
    
    
    public void cancelDecision() {
        IDecisionsClient client = RegClientFactory.getDecisionsClient();
        
        IDecisionsDTO selectedDTO = (IDecisionsDTO)getSelectedDTOS().get(0);
        selectedDTO.setDecCancelUser(CSCSecurityInfoHelper.getUserCode().toString());
        try {
            client.cancelDecision(selectedDTO);
            getSharedUtil().handleSuccMsg(RESOURCE_PATH, "dec_canceled_succ");
            fillDataTable();
        } catch (DataBaseException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, RESOURCE_PATH, "failed_to_cancel_dec");
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, RESOURCE_PATH, "failed_to_cancel_dec");

        }
    }


@Override
    public void changePageIndex(ActionEvent event) {

        getDecisionSearchDTO().setRegStatusFlage(getSelectedTypeKey());
        ((IRegulationSearchDTO)getFilterDTO()).setRegStatusFlage(getSelectedTypeKey());

        if (selectedTypeKey.equals(STATUS_EXECUTED)) {
            getDecisionSearchDTO().setRegStatusFlage2(STATUS_CANCELED);
            ((IRegulationSearchDTO)getFilterDTO()).setRegStatusFlage2(STATUS_CANCELED);
        } else {

            getDecisionSearchDTO().setRegStatusFlage2(null);
            ((IRegulationSearchDTO)getFilterDTO()).setRegStatusFlage2(null);
        }

        super.changePageIndex(event);
    }


    public void setFinDecisionStatusList2(List<IBasicDTO> finDecisionStatusList2) {
        this.finDecisionStatusList2 = finDecisionStatusList2;
    }

    public List<IBasicDTO> getFinDecisionStatusList2() {
        
        if (finDecisionStatusList2 == null || finDecisionStatusList2.size() == 0) {
            try {
                finDecisionStatusList2 = RegClientFactory.getRegulationStatusClient().getStatusForFinancialDecisions2();
            } catch (SharedApplicationException e) {
                finDecisionStatusList2 = new ArrayList();
            } catch (DataBaseException e) {
                finDecisionStatusList2 = new ArrayList();
            }
        }
        return finDecisionStatusList2;
    }
}
