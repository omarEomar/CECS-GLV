package com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.details;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.sec.business.client.SecClientFactory;
import com.beshara.csc.gn.sec.business.dto.ISecWorkCenterUsersDTO;
import com.beshara.csc.gn.sec.business.dto.ISecWorkCenterUsersSearchDTO;
import com.beshara.csc.gn.sec.business.dto.SecDTOFactory;
import com.beshara.csc.gn.sec.business.entity.ISecWorkCenterUsersEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.DecisionMakerTypesDTO;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.WorkCentersDTO;
import com.beshara.csc.nl.reg.business.client.IDecisionsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.DecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegDecisionMaterialsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationStatusDTO;
import com.beshara.csc.nl.reg.business.dto.ITypesDTO;
import com.beshara.csc.nl.reg.business.dto.SubjectsDTO;
import com.beshara.csc.nl.reg.business.sharedUtil.IConstants;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.EditorBean;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.settlementdecisions.settlementdecisionsCycle.DecisionCycleMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class DecisionCycleMainDataMaintain extends ManyToManyDetailsMaintain {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private static final String BEAN_NAME = "settlementDecisionCycleMainDataMaintainBean";

    private List<IBasicDTO> types;
    private List<IYearsDTO> issuanceYears;
    private List<IRegulationStatusDTO> regStatusDTOList;
    private List<IBasicDTO> subjects;
    private List<IBasicDTO> decisionMakers;
    private List<IBasicDTO> templates;
    private boolean decMkrDisabled = true;
    private String yearsDTOKey;
    private String typesDTOKey;
    private String statusDTOKey;
    //  private UploadedFile decImageUploadedFile;

    private Long itemSelectionRequiredValue = ISystemConstant.VIRTUAL_VALUE;
    private String itemSelectionRequiredValueString = itemSelectionRequiredValue.toString();
    //    private String fakeImageString;
    //
    //    private boolean invalidImageType;
    //    private boolean uploadingError;

    private boolean dateDisabled;

    private boolean showLovDivFlag = false;

    private boolean validEntityKey = true;
    private boolean showMatrialMsg;
    private String returnFromEditor;
    private String subjectsKey;
    private String typesKey;
    private String firstSubject;

    private ISecWorkCenterUsersSearchDTO secWorkCenterUsersSearchDTO =
        SecDTOFactory.createSecWorkCenterUsersSearchDTO();
    //    private List<IBasicDTO> pageDtoBufferlist;
    private List<WorkCentersDTO> workCentersDTOList;
    private IMinistriesDTO pageDtoBuffer;
    private boolean divflag;
    private String editorReturn = null;
    private List<IBasicDTO> formsList;
    private String sortFormColumn;
    private boolean ascendingForm;
    private String selectedFormRadio;
    private IBasicDTO selectedForm;
    private boolean formsSearchMode;
    private transient HtmlDataTable formsDataTable = new HtmlDataTable();
    private int indexOfSelectedDataInFormsDataTable;
    DecisionCycleMaintainBean decisionMaintainBean;

    private Boolean invalidDateErr = false;
    
    public DecisionCycleMainDataMaintain() {
        setClient(RegClientFactory.getDecisionsClient());
        //        BeansUtil.setValue("decisionMaintainBean.content1Div","divContent1Fixed");
        setContent1Div("reg_tabs_cont");
        // added by nora for adding lov div
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList());
        setLookupEditDiv("m2mAddDivClass");
        setCustomDiv1TitleKey("loadForm");
        setAddDivTitleKeyFlage(true);
        decisionMaintainBean = (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
    }

    /**
     * @author Amr Abdel Halim
     * @since 06/10/2015
     * @Note created to enhance performance
     */
    @Override
    public void initiateBeanOnce() {

        if (decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_EDIT){
            setDecMkrDisabled(false); 
        }
        /////////////////////////////ddddd
        if (subjects == null || subjects.size() == 0) {
            //subjects = RegClientFactory.getSubjectsClient().getCodeName();
            try {
                subjects =
                        RegClientFactory.getSubjectsClient().getCodeNameByAppModuleAndSysSettingCode(IConstants.SYSTEM_SETTING_SETTLEMENT_CODE,IConstants.APP_MOUDLE_REG_CODE);
            } catch (Exception e) {
                e.printStackTrace();
                subjects = new ArrayList<IBasicDTO>();
            }

        }

        //***********************************
        //firstSubject
        if ((Boolean)BeansUtil.getValue("settlementDecisionCycleMaintainBean.maintainMode==0")) {
            // in Add Miode Only
            if (this.getSubjects() != null && this.getSubjects().size() == 1) {
                // set PageBean PageDTO to that Value
                DecisionCycleMaintainBean decisionMaintainBean =
                    (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
                if (decisionMaintainBean.getPageDTO() != null)
                    ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).setSubjectsDTOKey(((SubjectsDTO)(this.getSubjects().get(0))).getCode().getKey());
                firstSubject = ((SubjectsDTO)(this.getSubjects().get(0))).getCode().getKey();
            }
        }


        //***********************************
        // statusDTOKey
        if (statusDTOKey == null || statusDTOKey.length() == 0 ||
            statusDTOKey.equals(ISystemConstant.VIRTUAL_VALUE + "")) {
            statusDTOKey =
                    (String)BeansUtil.getValue("settlementDecisionCycleMaintainBean.pageDTO.regStatusDTO.code.key");
        }
        //**********************
        // types
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        if (types == null || types.size() == 0) {
            try {
                types =
                        RegClientFactory.getRegMinRegTypesClient().getTypesByMinCodeFLinked(minCode, ISystemConstant.REGULATION_VALIDITY_DECISION);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                System.out.println(" Exception Message--------------------------------------------->>" +
                                   e.getMessage());
                types = new ArrayList<IBasicDTO>();
            } catch (DataBaseException e) {
                e.printStackTrace();
                System.out.println(" Exception Message--------------------------------------------->>" +
                                   e.getMessage());
                types = new ArrayList<IBasicDTO>();
            }
        }


        //////////////////////////
        if ((Boolean)BeansUtil.getValue("settlementDecisionCycleMaintainBean.maintainMode==1") ||
            (Boolean)BeansUtil.getValue("settlementDecisionCycleMaintainBean.maintainMode==2")) {
            // Load on startup only in Edit Mode or View Mode
            DecisionCycleMaintainBean decisionCycleMaintainBean = DecisionCycleMaintainBean.getInstance();
            if (decisionCycleMaintainBean.getPageDTO() != null) {
                String typesKey = ((IDecisionsDTO)decisionCycleMaintainBean.getPageDTO()).getTypesDTOKey();

                if (typesKey == "" || typesKey == null) {
                    //setDecMkrDisabled(true);
                    decisionMakers = new ArrayList<IBasicDTO>();
                } else {
                    //setDecMkrDisabled(false);
                    try {
                        decisionMakers =
                                RegClientFactory.getRegMinRegTypesClient().getDecMkrsByMinCodeFLinked(minCode, Long.valueOf(typesKey));
                    } catch (SharedApplicationException e) {
                        e.printStackTrace();
                        System.out.println(" Exception Message--------------------------------------------->>" +
                                           e.getMessage());
                        decisionMakers = new ArrayList<IBasicDTO>();
                    } catch (DataBaseException e) {
                        e.printStackTrace();
                        System.out.println(" Exception Message--------------------------------------------->>" +
                                           e.getMessage());
                        decisionMakers = new ArrayList<IBasicDTO>();
                    }
                }
            } else {
                decisionMakers = new ArrayList<IBasicDTO>();
                setDecMkrDisabled(true);
            }
        }


        //////////////////////////

        if (issuanceYears == null || issuanceYears.size() == 0) {
            try {
                issuanceYears = InfClientFactory.getYearsClient().getCodeName();
            } catch (Exception e) {
                e.printStackTrace();
                issuanceYears = new ArrayList<IYearsDTO>();
            }
            if (issuanceYears == null) {
                issuanceYears = new ArrayList<IYearsDTO>();
            }
        }


        //////////////////////////////
        //        if (templates == null || templates.size() == 0) {
        //            try {
        //                templates = RegClientFactory.getTemplatesClient().getCodeName();
        //            } catch (DataBaseException e) {
        //                e.printStackTrace();
        //                templates = new ArrayList<IBasicDTO>();
        //            }
        //            if (templates == null)
        //                templates = new ArrayList<IBasicDTO>();
        //        }

        /////////////////////////////
        //        try {
        //            pageDtoBuffer =
        //                    (IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(OrgEntityKeyFactory.createMinistriesEntityKey(CSCSecurityInfoHelper.getLoggedInMinistryCode()));
        //            getSecWorkCenterUsersSearchDTO().setMinCode(((IMinistriesEntityKey)pageDtoBuffer.getCode()).getMinCode());
        //        } catch (SharedApplicationException e) {
        //            e.printStackTrace();
        //        } catch (DataBaseException e) {
        //            e.printStackTrace();
        //        }

        //////////////////////////////////
        //        if (regStatusDTOList == null || regStatusDTOList.size() == 0) {
        //            try {
        //                regStatusDTOList = (List)RegClientFactory.getRegulationStatusClient().getAll();
        //            } catch (DataBaseException e) {
        //                regStatusDTOList = new ArrayList<IRegulationStatusDTO>();
        //            } catch (SharedApplicationException e) {
        //                regStatusDTOList = new ArrayList<IRegulationStatusDTO>();
        //            }
        //            if (regStatusDTOList == null) {
        //                regStatusDTOList = new ArrayList<IRegulationStatusDTO>();
        //            }
        //        }
        //////////////////////////////////////

    }

    /**
     * @author Amr Abdel Halim
     * @since 05/10/2015
     * @Note created to enhance performance
     */
    public void loadRegStatusDTOList() {
        if (regStatusDTOList == null || regStatusDTOList.size() == 0) {
            try {
                regStatusDTOList = (List)RegClientFactory.getRegulationStatusClient().getAll();
            } catch (DataBaseException e) {
                regStatusDTOList = new ArrayList<IRegulationStatusDTO>();
            } catch (SharedApplicationException e) {
                regStatusDTOList = new ArrayList<IRegulationStatusDTO>();
            }
            if (regStatusDTOList == null) {
                regStatusDTOList = new ArrayList<IRegulationStatusDTO>();
            }
        }
    }

    public static DecisionCycleMainDataMaintain getInstance() {

        return (DecisionCycleMainDataMaintain)JSFHelper.getValue(BEAN_NAME);
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();

        app.showAddeditPage();
        app.setShowLookupAdd(true);
        app.setManyToMany(true);
        app.setShowNavigation(true);
        app.setShowsteps(true);
        //app.setShowCustomDiv2(Boolean.TRUE);
        //app.setShowCustomDiv1(Boolean.TRUE);
        // added by nora for Lov Div
        if (showLovDivFlag)
            app.setShowLovDiv(true);

        app.setShowLookupEdit(true);

        return app;
    }

    /**
     * Purpose:called when user open decision types love divBean
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void openDecisionTypes() {

        try {
            getLovBaseBean().setMyTableData(getTypes());
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        }
        getLovBaseBean().getOkCommandButton().setReRender("maintain_regTypeAdd");
        getLovBaseBean().setReturnMethodName("settlementDecisionCycleMainDataMaintainBean.addDecisionType");
        getLovBaseBean().setSearchMethod("settlementDecisionCycleMainDataMaintainBean.searchDecisionType");
        getLovBaseBean().setSelectedDTOS(new ArrayList());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setCancelSearchMethod("settlementDecisionCycleMainDataMaintainBean.cancelSearchLovDiv");
    }

    /**
     * Purpose:called when  user search by name or  code  @ decision type
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void searchDecisionType(Object searchType, Object searchQuery) {

        if (searchQuery != null && !searchQuery.toString().equals("") && searchType != null &&
            !searchType.equals("")) {
            getLovBaseBean().setSearchMode(true);
            try {

                //TODO set search decision type to fainance decisions

                if (searchType.toString().equals("0"))
                    getLovBaseBean().setMyTableData(RegClientFactory.getTypesClient().searchByCode(Long.valueOf(searchQuery.toString())));
                else if (searchType.toString().equals("1"))
                    getLovBaseBean().setMyTableData(RegClientFactory.getTypesClient().searchByName(searchQuery.toString()));
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            } catch (DataBaseException e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            } catch (Exception e) {
                getLovBaseBean().setMyTableData(new ArrayList());
                e.printStackTrace();
            }
        }
    }

    /**
     * Purpose:called when  user search by name or  code  @ decision subject
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void searchDecisionSubject(Object searchType, Object searchQuery) {
        //TODO set search decision type to fainance decisions
        if (searchQuery != null && !searchQuery.toString().equals("") && searchType != null &&
            !searchType.equals("")) {
            getLovBaseBean().setSearchMode(true);
            try {

                if (searchType.toString().equals("0"))
                    getLovBaseBean().setMyTableData(RegClientFactory.getSubjectsClient().searchByCode(Long.valueOf(searchQuery.toString())));
                else if (searchType.toString().equals("1"))
                    getLovBaseBean().setMyTableData(RegClientFactory.getSubjectsClient().searchByName(searchQuery.toString()));
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            } catch (DataBaseException e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList());
            } catch (Exception e) {
                getLovBaseBean().setMyTableData(new ArrayList());
                e.printStackTrace();
            }
        }
    }


    /**
     * Purpose:called when user open decision subjects love divBean
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void openDecisionSubjects() {
        try {
            getLovBaseBean().setMyTableData(getSubjects());
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        }
        getLovBaseBean().getOkCommandButton().setReRender("maintain_decisionSubject");
        getLovBaseBean().setReturnMethodName("settlementDecisionCycleMainDataMaintainBean.addDecisionSubject");
        getLovBaseBean().setSearchMethod("settlementDecisionCycleMainDataMaintainBean.searchDecisionSubject");
        getLovBaseBean().setSelectedDTOS(new ArrayList());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
    }

    /**
     * Purpose:called when user select one row from type lov divBean and click ok
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void addDecisionType() {
        if (getLovBaseBean().getSelectedDTOS() != null && getLovBaseBean().getSelectedDTOS().get(0) != null &&
            getLovBaseBean().getSelectedDTOS().get(0).getCode() != null) {
            try {
                setTypesDTOKey(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Purpose:called when user select one row from subject lov divBean and click ok
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:  28-12-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void addDecisionSubject() {

        if (getLovBaseBean().getSelectedDTOS() != null && getLovBaseBean().getSelectedDTOS().get(0) != null &&
            getLovBaseBean().getSelectedDTOS().get(0).getCode() != null) {
            try {
                DecisionCycleMaintainBean decisionMaintainBean =
                    (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
                if (decisionMaintainBean.getPageDTO() != null)
                    ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).setSubjectsDTOKey(getLovBaseBean().getSelectedDTOS().get(0).getCode().getKey().toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    //    public boolean validate() {
    //
    //        System.out.println("from main data Validate");
    //        boolean valid = true;
    //        if (decImageUploadedFile != null) {
    //
    //            String tempFileExtension = null;
    //            String tempFilePath = null;
    //            InputStream inStream = null;
    //            try {
    //                inStream = decImageUploadedFile.getInputStream();
    //            } catch (IOException e) {
    //                uploadingError = true;
    //                return false;
    //            }
    //            tempFileExtension = BeansUtil.mapImageTypeToExtension(decImageUploadedFile.getContentType());
    //            if (tempFileExtension == null) {
    //                invalidImageType = true;
    //                return false;
    //            }
    //            try {
    //                tempFilePath = BeansUtil.saveToTempFile(decImageUploadedFile.getInputStream(), tempFileExtension);
    //            } catch (IOException e) {
    //                uploadingError = true;
    //                return false;
    //            }
    //            Map<String, Object> detailsSavedStates =
    //                (Map<String, Object>)BeansUtil.getValue("financeDecisionCycleMaintainBean.detailsSavedStates");
    //            detailsSavedStates.put(BeansUtil.UPLOADED_IMAGE_BINDING_KEY, tempFilePath);
    //        }
    //        return valid;
    //    }

    /**
     * Purpose: cancel search
     * Creation/Modification History :
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  31-DEC-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public String cancelSearchLovDiv() {
        try {
            getLovBaseBean().setMyTableData(getTypes());
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        }
        return "";
    }


    public void setTypes(List<IBasicDTO> types) {
        this.types = types;
    }

    public List<IBasicDTO> getTypes() throws DataBaseException {
        return types;
    }

    /*by A.Nour 8/9/2014*/

    public void resetDecMkrSignBy(ActionEvent event) throws DataBaseException {
        DecisionCycleMaintainBean decisionCycleMaintainBean = DecisionCycleMaintainBean.getInstance();
        //String decisionMakerDTOName = ((DecisionsDTO)regulationMaintainBean.getPageDTO()).getDecisionMakerDTO().getDecmkrtypeName();// .getDecisionMakerDTOKey();
        String decisionMakerDTOCode = ((DecisionsDTO)decisionCycleMaintainBean.getPageDTO()).getDecisionMakerDTOKey();
        //InfDTOFactory.createDecisionMakerTypesDTO()
        try {
            DecisionMakerTypesDTO decisionMakerTypesDTO =
                (DecisionMakerTypesDTO)InfClientFactory.getDecisionMakerTypesClient().getById(InfEntityKeyFactory.createDecisionMakerTypesEntityKey(Long.parseLong(decisionMakerDTOCode)));
            String decisionMakerDTOName = decisionMakerTypesDTO.getDecmkrtypeName();
            //Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
            if (decisionMakerDTOName == null || decisionMakerDTOName.trim().equals("")) {

            } else {
                ((DecisionsDTO)decisionCycleMaintainBean.getPageDTO()).setSignBy(decisionMakerDTOName);
            }

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }


    public List<IBasicDTO> getDecisionMakers() {
        return decisionMakers;
    }

    public void filterDecMkrBytype(ActionEvent event) throws DataBaseException {

        DecisionCycleMaintainBean decisionCycleMaintainBean = DecisionCycleMaintainBean.getInstance();
        if (decisionCycleMaintainBean.getPageDTO() != null) {
            String typesKey = ((IDecisionsDTO)decisionCycleMaintainBean.getPageDTO()).getTypesDTOKey();
            Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
            if (typesKey == "" || typesKey == null) {
                setDecMkrDisabled(true);
                decisionMakers = new ArrayList<IBasicDTO>();
            } else {
                setDecMkrDisabled(false);
                try {
                    decisionMakers =
                            RegClientFactory.getRegMinRegTypesClient().getDecMkrsByMinCodeFLinked(minCode, Long.valueOf(typesKey));
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                    System.out.println(" Exception Message--------------------------------------------->>" +
                                       e.getMessage());
                }
            }
        } else {
            decisionMakers = new ArrayList<IBasicDTO>();
            setDecMkrDisabled(true);
        }
    }

    public void setIssuanceYears(List<IYearsDTO> issuanceYears) {
        this.issuanceYears = issuanceYears;
    }

    public List<IYearsDTO> getIssuanceYears() {
        return issuanceYears;
    }

    public void setSubjects(List<IBasicDTO> subjects) {
        this.subjects = subjects;
    }

    public List<IBasicDTO> getSubjects() {
        return subjects;
    }

    public void setDecisionMakers(List<IBasicDTO> decisionMakers) {
        this.decisionMakers = decisionMakers;
    }

    //    public List<IBasicDTO> getDecisionMakers() {
    ////        try {
    ////            filterDecMkrBytype(null);
    ////        } catch (DataBaseException e) {
    ////            e.printStackTrace();
    ////        }
    //        if(decisionMakers== null)
    //            return new ArrayList();
    //        return decisionMakers;
    //    }

    public void setTemplates(List<IBasicDTO> templates) {
        this.templates = templates;
    }

    public List<IBasicDTO> getTemplates() {
        return templates;
    }

    //    public void setDecImageUploadedFile(UploadedFile decImageUploadedFile) {
    //        this.decImageUploadedFile = decImageUploadedFile;
    //    }
    //
    //    public UploadedFile getDecImageUploadedFile() {
    //        return decImageUploadedFile;
    //    }

    public void setItemSelectionRequiredValue(Long itemSelectionRequiredValue) {
        this.itemSelectionRequiredValue = itemSelectionRequiredValue;
    }

    public Long getItemSelectionRequiredValue() {
        return itemSelectionRequiredValue;
    }

    public void setItemSelectionRequiredValueString(String itemSelectionRequiredValueString) {
        this.itemSelectionRequiredValueString = itemSelectionRequiredValueString;
    }

    public String getItemSelectionRequiredValueString() {
        return itemSelectionRequiredValueString;
    }

    //    public void setInvalidImageType(boolean invalidImageType) {
    //        this.invalidImageType = invalidImageType;
    //    }
    //
    //    public boolean isInvalidImageType() {
    //        return invalidImageType;
    //    }
    //
    //    public void setUploadingError(boolean uploadingError) {
    //        this.uploadingError = uploadingError;
    //    }
    //
    //    public boolean isUploadingError() {
    //        return uploadingError;
    //    }

    //    public void setFakeImageString(String fakeImageString) {
    //        this.fakeImageString = fakeImageString;
    //        if (fakeImageString != null && fakeImageString.length() > 1) {
    //            Map<String, Object> detailsSavedStates =
    //                (Map<String, Object>)BeansUtil.getValue("decisionCycleMaintainBean.detailsSavedStates");
    //            detailsSavedStates.put("fakeImageString", fakeImageString);
    //        }
    //    }
    //
    //    public String getFakeImageString() {
    //        //        if(fakeImageString == null){
    //        if (!(isInvalidImageType() || isUploadingError())) {
    //            Map<String, Object> detailsSavedStates =
    //                (Map<String, Object>)BeansUtil.getValue("decisionCycleMaintainBean.detailsSavedStates");
    //            fakeImageString = (String)detailsSavedStates.get("fakeImageString");
    //        }
    //        if (fakeImageString == null || (isInvalidImageType() || isUploadingError())) {
    //            //            if(fakeImageString == null){
    //            fakeImageString =
    //                    BeansUtil.getResource("com.beshara.csc.nl.reg.presentation.resources.reg", "image_unAvailable");
    //            String imageName = (String)BeansUtil.getValue("decisionCycleMaintainBean.pageDTO.decisionImage");
    //            if (imageName != null && imageName.length() > 1) {
    //                fakeImageString =
    //                        BeansUtil.getResource("com.beshara.csc.nl.reg.presentation.resources.reg", "image_available");
    //            }
    //        }
    //        //        }
    //        return fakeImageString;
    //    }

    public void setTypesDTOKey(String typesDTOKey) throws DataBaseException {
        try {
            if ((Boolean)BeansUtil.getValue("settlementDecisionCycleMaintainBean.maintainMode==1")) {
                return;
            }
            ITypesDTO typesDto = null;
            if (!(typesDTOKey == null || typesDTOKey.length() == 0 ||
                  typesDTOKey.equals(ISystemConstant.VIRTUAL_VALUE + ""))) {
                for (IBasicDTO type : getTypes()) {
                    if (type.getCode().getKey().equals(typesDTOKey)) {
                        typesDto = (ITypesDTO)type;
                        BeansUtil.setValue("settlementDecisionCycleMaintainBean.pageDTO.typesDTO", typesDto);

                        break;
                    }
                }
            }
            this.typesDTOKey = typesDTOKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTypesDTOKey() {
        return typesDTOKey;
    }

    public void setYearsDTOKey(String yearsDTOKey) throws DataBaseException {
        if ((Boolean)BeansUtil.getValue("settlementDecisionCycleMaintainBean.maintainMode==1")) {
            return;
        }
        IYearsDTO yearsDto = null;
        if (!(yearsDTOKey == null || yearsDTOKey.length() == 0 ||
              yearsDTOKey.equals(ISystemConstant.VIRTUAL_VALUE + ""))) {
            for (IYearsDTO year : getIssuanceYears()) {
                if (year.getCode().getKey().equals(yearsDTOKey)) {
                    yearsDto = year;
                    BeansUtil.setValue("settlementDecisionCycleMaintainBean.pageDTO.yearsDTO", yearsDto);

                    break;
                }
            }
        }
        this.yearsDTOKey = yearsDTOKey;
    }

    public String getYearsDTOKey() {
        return yearsDTOKey;
    }

    public void setDateDisabled(boolean dateDisabled) {
        this.dateDisabled = dateDisabled;
    }

    public boolean isDateDisabled() {
        if (getIntegrationBean() != null && getIntegrationBean().getModuleFrom() != null &&
            getIntegrationBean().getModuleFrom().equals("prm")) {
            dateDisabled = true;
        }
        return dateDisabled;
    }

    public void setShowLovDivFlag(boolean showLovDivFlag) {
        this.showLovDivFlag = showLovDivFlag;
    }

    public boolean isShowLovDivFlag() {
        return showLovDivFlag;
    }


    public void setValidEntityKey(boolean validEntityKey) {
        this.validEntityKey = validEntityKey;
    }

    public boolean isValidEntityKey() {
        return validEntityKey;
    }


    //////////////////////////////////////////// Start Editor Action By Amr Galal  ///////////////////////////////////////////////////////

    public String returnEditorAction() {
        return getEditorReturn();
    }

    public String openEditor() {
        DecisionCycleMaintainBean decisionMaintainBean =
            (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
        IDecisionsDTO decisionsDTO = (IDecisionsDTO)decisionMaintainBean.getPageDTO();
        EditorBean editorBean = (EditorBean)evaluateValueBinding("editorDecitionBean");
        editorBean.setData(decisionsDTO.getDecisionText());
        return "goToEditorDecitionCylcleList";
    }


    //    public void addMaterialInEditorView() {
    //        DecisionCycleMaintainBean decisionMaintainBean =
    //            (DecisionCycleMaintainBean)evaluateValueBinding("financeDecisionCycleMaintainBean");
    //        DecisionCycleMaterialsMaintain decisionMaterialsMaintain =
    //            (DecisionCycleMaterialsMaintain)evaluateValueBinding("decisionCycleMaterialsMaintainBean");
    //        IDecisionsDTO decisionsDTO = (IDecisionsDTO)decisionMaintainBean.getPageDTO();
    //        String material = "";
    //        for (int i = 0; i < decisionMaterialsMaintain.getCurrentDetails().size(); i++) {
    //            if (decisionMaterialsMaintain.getCurrentDetails().get(i).getStatusFlag() != null) {
    //                if (decisionMaterialsMaintain.getCurrentDetails().get(i).getStatusFlag() != 1L) {
    //                    material =
    //                            material + "<p><span style=font-size:small><strong>" + ((IRegDecisionMaterialsDTO)decisionMaterialsMaintain.getCurrentDetails().get(i)).getDecmaterialText() +
    //                            "</span></strong></p>";
    //                } else {
    //                    material = "";
    //                }
    //            } else {
    //                material =
    //                        material + "<p><span style=font-size:small><strong>" + ((IRegDecisionMaterialsDTO)decisionMaterialsMaintain.getCurrentDetails().get(i)).getDecmaterialText() +
    //                        "</span></strong></p>";
    //            }
    //        }
    //
    //        if (material.equals("")) {
    //            getSharedUtil().handleException(new Exception(), "com.beshara.csc.nl.reg.presentation.resources.reg",
    //                                            "noMaterialErrorMsg");
    //            // showMatrialMsg=true;
    //        } else {
    //            decisionsDTO.setDecisionText(material);
    //            // showMatrialMsg=false;
    //        }
    //    }

    //    public String parseEditorToText() {
    //
    //        DecisionCycleMaintainBean decisionMaintainBean =
    //            (DecisionCycleMaintainBean)evaluateValueBinding("decisionCycleMaintainBean");
    //        ;
    //        EditorBean editorBean = (EditorBean)evaluateValueBinding("editorDecitionBean");
    //        IDecisionsDTO decisionsDTO = (IDecisionsDTO)decisionMaintainBean.getPageDTO();
    //        // System.out.println(editorBean.getData());
    //        setReturnFromEditor(editorBean.getData());
    //        String hTMLString = editorBean.getData();
    //        //    hTMLString=hTMLString.replaceAll("&nsb;", "");
    //        //    hTMLString=hTMLString.replaceAll("&nbsp;", "");
    //        decisionsDTO.setDecisionText(hTMLString);
    //        return hTMLString;
    //    }
    //
    //    public String parseEditorAction() {
    //        parseEditorToText();
    //        return getEditorReturn();
    //    }


    public void setShowMatrialMsg(boolean showMatrialMsg) {
        this.showMatrialMsg = showMatrialMsg;
    }

    public boolean isShowMatrialMsg() {
        return showMatrialMsg;
    }

    public void setReturnFromEditor(String returnFromEditor) {
        this.returnFromEditor = returnFromEditor;
    }

    public String getReturnFromEditor() {
        return returnFromEditor;
    }


    //////////////////////////////////////////// End Editor Action By Amr Galal  ///////////////////////////////////////////////////////

    public void openSearchUser() {
        setSelectedForm(null);
        cancelSearchDiv();
        setDivflag(true);
        setJavaScriptCaller("javascript:changeVisibilityDiv ( window.blocker , window.lookupEditDiv ) ; ");
    }


    public void openSearchForms(ActionEvent ae) {
        ae = null;
        formsSearchMode = false;
        subjectsKey = "";
        typesKey = "";
        selectedForm = null;
        cancelSearchForms();
        try {
            formsList =
                    ((IDecisionsClient)getClient()).getDecisionsForms(new Long(1), CSCSecurityInfoHelper.getLoggedInMinistryCode());
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        setJavaScriptCaller("javascript:changeVisibilityDiv ( window.blocker , window.customDiv1 ); document.getElementById('regTypeAddSearch').focus();");
    }

    public void loadForm() {
        try {
            selectedForm = getClient().getById(selectedForm.getCode());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        prepareFormDTO((IDecisionsDTO)selectedForm);
        DecisionCycleMaintainBean decisionMaintainBean =
            (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
        //((IDecisionsDTO)selectedForm).setSelectedFormRadio( ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getSelectedFormRadio());
        decisionMaintainBean.setPageDTO(selectedForm);
    }

    public void prepareFormDTO(IDecisionsDTO decisionsDTO) {
        decisionsDTO.setCode(null);
        decisionsDTO.setTemplateFlag(null);
        decisionsDTO.setDecisionMakerTypesDTO(null);
        decisionsDTO.setDecisionDate(SharedUtils.getCurrentTimestamp());
        decisionsDTO.setDecisionAppliedDate(null);
        decisionsDTO.setYearsDTO(null);
        decisionsDTO.setUserCodeApproval(null);
        decisionsDTO.setApprovalDate(null);
        decisionsDTO.setMinCode(null);
        decisionsDTO.setLeafFlag(null);
        decisionsDTO.setTreeLevel(null);
        decisionsDTO.setRegStatusDTO(null);
        decisionsDTO.setDecisionNumber(null);
        if (decisionsDTO.getRegDecisionMaterialsDTOList() == null) {
            decisionsDTO.setRegDecisionMaterialsDTOList(new ArrayList<IRegDecisionMaterialsDTO>(0));
        }
        if (decisionsDTO.getDecisionRefrencesDTOList() == null) {
            decisionsDTO.setDecisionRefrencesDTOList(new ArrayList<IBasicDTO>(0));
        }
        decisionsDTO.setEmpDecisionsDTOList(new ArrayList<IBasicDTO>(0));

    }

    public void searchForms() {
        DecisionCycleMaintainBean decisionMaintainBean =
            (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
        String typeKey = typesKey;
        String subjKey = subjectsKey;
        Long dectypeCode;
        Long subjectCode;
        if (typeKey == null || typeKey.equals("")) {
            dectypeCode = null;
        } else {
            dectypeCode = Long.valueOf(typeKey);
        }
        if (subjKey == null || subjKey.equals("")) {
            subjectCode = null;
        } else {
            subjectCode = Long.valueOf(subjKey);
        }
        try {
            formsList = ((IDecisionsClient)getClient()).getAllByTypeAndSubject(dectypeCode, subjectCode);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            formsList = new ArrayList<IBasicDTO>(0);
        }
        formsSearchMode = true;
    }

    public void cancelSearchForms() {
        formsSearchMode = false;
        typesKey = "";
        subjectsKey = "";
        selectedForm = null;
        try {
            formsList =
                    ((IDecisionsClient)getClient()).getDecisionsForms(new Long(1), CSCSecurityInfoHelper.getLoggedInMinistryCode());
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public String cancelSearchDiv() {
        setSearchQuery("");
        setSearchMode(false);
        setSelectedRadio("");
        getSelectedDTOS().clear();
        setMyTableData(new ArrayList());
        secWorkCenterUsersSearchDTO = SecDTOFactory.createSecWorkCenterUsersSearchDTO();
        return "";
    }


    public void closeLovDiv() throws DataBaseException {
        DecisionCycleMaintainBean decisionMaintainBean =
            (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
        if (getSelectedDTOS() != null && getSelectedDTOS().get(0) != null) {
            ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getSecWorkCenterUsersDTO().setUserName(((ISecWorkCenterUsersDTO)getSelectedDTOS().get(0)).getUserName());
            ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getSecWorkCenterUsersDTO().setCivilname(((ISecWorkCenterUsersDTO)getSelectedDTOS().get(0)).getCivilname());
            ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getSecWorkCenterUsersDTO().setUserCode(((ISecWorkCenterUsersEntityKey)(((ISecWorkCenterUsersDTO)getSelectedDTOS().get(0)).getCode())).getUserCode());
        }
        secWorkCenterUsersSearchDTO = SecDTOFactory.createSecWorkCenterUsersSearchDTO();
        divflag = false;
    }

    public void openSearchDiv(ActionEvent event) {
        setJavaScriptCaller("javascript:changeVisibilityDiv ( window.blocker , window.lookupEditDiv ) ; ");
    }

    public void getAll() {

    }

    public void hideLovDiv() {
        cancelSearchDiv();
        setDivflag(false);
        setSelectedRadio("");
        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }
    }
    //     public void filterDataBygeha() {
    //
    //         if(getSecWorkCenterUsersSearchDTO().getMinCode()==null)
    //         {
    //         getSecWorkCenterUsersSearchDTO().setWrkCode(null);
    //         }
    //         setSearchQuery("");
    //         setSearchMode(false);
    //         getSelectedDTOS().clear();
    //         setMyTableData(new ArrayList());
    //         setJavaScriptCaller("javascript:changeVisibilityDiv ( window.blocker , window.lookupEditDiv ) ; ");
    //     }

    public String searchLovDiv() {
        try {
            List<ISecWorkCenterUsersDTO> Result =
                SecClientFactory.getSecWorkCenterUsersClientForCenter().SearchByCaretira(getSecWorkCenterUsersSearchDTO());
            setMyTableData(Result);
            setSelectedRadio("");
        } catch (DataBaseException e) {
            e.printStackTrace(); // TODO
            setMyTableData(new ArrayList());
            setSelectedRadio("");
        }
        setDivflag(true);
        setSearchMode(true);
        setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.lookupEditDiv);settingFoucsAtLovDiv();");

        return "";
    }

    public void setSecWorkCenterUsersSearchDTO(ISecWorkCenterUsersSearchDTO secWorkCenterUsersSearchDTO) {
        this.secWorkCenterUsersSearchDTO = secWorkCenterUsersSearchDTO;
    }

    public ISecWorkCenterUsersSearchDTO getSecWorkCenterUsersSearchDTO() {
        return secWorkCenterUsersSearchDTO;
    }

    //    public void setPageDtoBufferlist(List<IBasicDTO> pageDtoBufferlist) {
    //        this.pageDtoBufferlist = pageDtoBufferlist;
    //    }
    //
    //    public List<IBasicDTO> getPageDtoBufferlist() {
    //        try {
    //            pageDtoBufferlist =SecClientFactory.getSecWorkCenterUsersClientForCenter().GetDistnictMinCodeByUserCode(CSCSecurityInfoHelper.getUserCode());
    //        } catch (DataBaseException e) {
    //            pageDtoBufferlist=new ArrayList();
    //            e.printStackTrace();
    //        }
    //        return pageDtoBufferlist;
    //    }

    public void setWorkCentersDTOList(List<WorkCentersDTO> workCentersDTOList) {
        this.workCentersDTOList = workCentersDTOList;
    }

    public List<WorkCentersDTO> getWorkCentersDTOList() {
        //        try {
        //            workCentersDTOList =SecClientFactory.getSecWorkCenterUsersClientForCenter().getWorkCentersByMin(CSCSecurityInfoHelper.getLoggedInMinistryCode());
        //        } catch (DataBaseException e) {
        //            workCentersDTOList=new ArrayList();
        //            e.printStackTrace();
        //        }
        //        return workCentersDTOList;
        return new ArrayList<WorkCentersDTO>();
    }

    public void setPageDtoBuffer(IMinistriesDTO pageDtoBuffer) {
        this.pageDtoBuffer = pageDtoBuffer;
    }

    public IMinistriesDTO getPageDtoBuffer() {
        return pageDtoBuffer;
    }

    public void setDivflag(boolean divflag) {
        this.divflag = divflag;
    }

    public boolean isDivflag() {
        return divflag;
    }

    public void setRegStatusDTOList(List<IRegulationStatusDTO> regStatusDTOList) {
        this.regStatusDTOList = regStatusDTOList;
    }

    public List<IRegulationStatusDTO> getRegStatusDTOList() {
        return regStatusDTOList;
    }

    public void setStatusDTOKey(String statusDTOKey) throws DataBaseException, SharedApplicationException {
        if (!(statusDTOKey == null || statusDTOKey.length() == 0 ||
              statusDTOKey.equals(ISystemConstant.VIRTUAL_VALUE + ""))) {
            loadRegStatusDTOList();
            for (IRegulationStatusDTO status : getRegStatusDTOList()) {
                if (status.getCode().getKey().equals(statusDTOKey)) {
                    BeansUtil.setValue("settlementDecisionCycleMaintainBean.pageDTO.regStatusDTO", status);
                    break;
                }
            }
        }
        this.statusDTOKey = statusDTOKey;

    }

    public String getStatusDTOKey() {
        return statusDTOKey;
    }

    public void selectedFormRadioButton(ActionEvent event) throws Exception {
        indexOfSelectedDataInFormsDataTable = getFormsDataTable().getRowIndex();
        IClientDTO selected = (IClientDTO)getFormsDataTable().getRowData();
        setSelectedForm(selected);
    }

    public boolean validate() {
        if (com.beshara.csc.nl.reg.integration.presentation.backingbean.decisionFinanceCycle.DecisionCycleMaintainBean.isIntegrationPage()) {
            return true; //TODO
        }
        invalidDateErr = false;
        DecisionCycleMaintainBean decisionMaintainBean  = 
            (DecisionCycleMaintainBean)evaluateValueBinding("settlementDecisionCycleMaintainBean");
        IDecisionsDTO selectedDecisionDTO = (IDecisionsDTO)decisionMaintainBean.getPageDTO();
        if (selectedDecisionDTO.getDecisionDate() != null &&
            selectedDecisionDTO.getDecisionAppliedDate() != null &&
            selectedDecisionDTO.getDecisionDate().compareTo(selectedDecisionDTO.getDecisionAppliedDate()) >
            0) {
            invalidDateErr = true;
            return false;
        } 
        return true;
    }
    
    public void cancelFormSearch() {

    }

    public int getFormsListSize() {
        if (formsList != null) {
            return formsList.size();
        }
        return 0;
    }

    public void setEditorReturn(String editorReturn) {
        this.editorReturn = editorReturn;
    }

    public String getEditorReturn() {
        return editorReturn;
    }

    public void setDecMkrDisabled(boolean decMkrDisabled) {
        this.decMkrDisabled = decMkrDisabled;
    }

    public boolean isDecMkrDisabled() {
        return decMkrDisabled;
    }

    public void setFormsList(List<IBasicDTO> formsList) {
        this.formsList = formsList;
    }

    public List<IBasicDTO> getFormsList() {
        return formsList;
    }

    public void setSortFormColumn(String sortFormColumn) {
        this.sortFormColumn = sortFormColumn;
    }

    public String getSortFormColumn() {
        return sortFormColumn;
    }

    public void setAscendingForm(boolean ascendingForm) {
        this.ascendingForm = ascendingForm;
    }

    public boolean isAscendingForm() {
        return ascendingForm;
    }

    public void setSelectedFormRadio(String selectedFormRadio) {
        if (selectedFormRadio != null) {
            this.selectedFormRadio = selectedFormRadio;
        }
    }

    public String getSelectedFormRadio() {
        return selectedFormRadio;
    }

    public void setFormsSearchMode(boolean formsSearchMode) {
        this.formsSearchMode = formsSearchMode;
    }

    public boolean isFormsSearchMode() {
        return formsSearchMode;
    }

    public void setFormsDataTable(HtmlDataTable formsDataTable) {
        this.formsDataTable = formsDataTable;
    }

    public HtmlDataTable getFormsDataTable() {
        return formsDataTable;
    }

    public void setSelectedForm(IBasicDTO selectedForm) {
        this.selectedForm = selectedForm;
    }

    public IBasicDTO getSelectedForm() {
        return selectedForm;
    }

    public void setSubjectsKey(String subjectsKey) {
        this.subjectsKey = subjectsKey;
    }

    public String getSubjectsKey() {
        return subjectsKey;
    }

    public void setTypesKey(String typesKey) {
        this.typesKey = typesKey;
    }

    public String getTypesKey() {
        return typesKey;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getFirstSubject() {
        return this.firstSubject;
    }

    public void setInvalidDateErr(Boolean invalidDateErr) {
        this.invalidDateErr = invalidDateErr;
    }

    public Boolean getInvalidDateErr() {
        return invalidDateErr;
    }
}
