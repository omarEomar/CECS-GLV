package com.beshara.csc.nl.reg.integration.presentation.backingbean.decisionFinanceCycle.details;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStatusDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredInMinException;
import com.beshara.csc.hr.emp.integration.presentation.utils.EmployeeHelper;
import com.beshara.csc.hr.sal.business.client.ISalElementGuidesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.hr.sal.business.shared.ISALConstants;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.exception.EmployeeCivilIdNotExistException;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.EmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.IEmpDecisionsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.business.sharedUtil.IConstants;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.decisionFinanceCycle.DecisionCycleMaintainBean;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.util.UtilBean;
import com.beshara.csc.nl.reg.presentation.backingbean.decisionCycle.details.DecisionEmployeesModel;
import com.beshara.csc.nl.reg.presentation.backingbean.util.BeansUtil;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BesharaTree;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.sql.Date;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


public class DecisionCycleEmployeesMaintain extends ManyToManyDetailsMaintain {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private static final String BUNDLE = "com.beshara.csc.nl.reg.integration.presentation.resources.integration";
    private static final String GLOBAL_BUNDLE = "com.beshara.jsfbase.csc.msgresources.global";
    private static final Long KUWITY = 1L;
    private static final Long NON_KUWITY = 0L;
    private static final Long NATIONLALTY_KUWITY = ISystemConstant.KUWAIT_NATIONALITY;
    private Long added = ISystemConstant.ADDED_ITEM;
    private Long kuwityType;
    private IEmployeeSearchDTO employeeSearchDTO = EmpDTOFactory.createEmployeeSearchDTO();

    //    private List<IBasicDTO> hireTypes;
    //    private List<IBasicDTO> hireStatuses;
    //    private List<IBasicDTO> hireCurrentStatuses;
    private HtmlDataScroller dataScroller;
    private boolean cancelDecisionMode;
    private boolean isSearchMode;
    private boolean showErrMsg;
    private List<IBasicDTO> elementGuideList;
    private List<IBasicDTO> elementGuideTypeList;
    private String selectedElementCode;
    private Long valueNumber;
    private IEmpDecisionsDTO empDecisionsDTOSlelected = RegDTOFactory.createEmpDecisionsDTO();
    private List<IEmpDecisionsDTO> empDecisionsDTOSlelectedList = new ArrayList<IEmpDecisionsDTO>();
    private boolean addEmpDecisionsDTOModule = true;
    //    RegulationModulesMaintain regulationModulesMaintainBean =
    //        (RegulationModulesMaintain)evaluateValueBinding("regulationModulesMaintainBean");

    private boolean showBarMainData = true; // added by nora for handling integeration to show /hide operationBar
    private List unDeleteEmpList;
    private Long valueNum;
    private String valueChr;
    private Date valueDate;
    private Long empInfTypeCode;
    private Boolean booleanInformEmpFlag;
    private Long informEmpFlag;
    private Date informEmpDate;
    private String notes;
    private boolean activeValidationOnAdd = false;
    private boolean activeValidationOnEdit = false;
    //    /////////////////////////////////////////////////////////////////////////////////////
    private Long civilID;
    private String empFullName;
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;
    //  private IEmpEmployeeSearchDTO empEmployeeSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
    //    private EmployeeSearchBean employeeSearchBean;
    private boolean addedEmpSuccessfully;
    private int countOfAllEmpDecision = 0;
    private static final String BEAN_NAME = "financeDecisionCycleEmployeesMaintainBean";
    private String civil_exist;
    private boolean deleteBtnDisabled = true;
    private String elementGuideCode = ISystemConstant.VIRTUAL_VALUE.toString();
    private String selectedElementGuideCode;
    private boolean enableElementGuideCombo = true;
    private boolean showElementGuideErrMsg;
    private String month;
    private String year;
    private int numberOfRecordsDisplayed;
    private List<IBasicDTO> salEmpMonSalaries = new ArrayList();
    private PagingResponseDTO tempResponse;

    private static final String SEARCH_BY_CODE = "0";
    private static final String SEARCH_BY_NAME = "1";
    private static final String SEARCH_SPECIAL_CHARACTER = "%";

    /**by Saad**/
    TreeDivBean treedivBean;
    private String id;
    private boolean benefitRewardCodeExists = false;
    private boolean benefitRewardCodeWrong;
    private boolean elemGuideCodeParentMinJoinFlag = false;
    private String benefitConst = ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_PAY_CAT.toString();
    private String rewardConst = ISalConstants.ELEMENT_GUIDE_TYPE_REWARD_PAY_CAT.toString();
    private String selectedElementGuideName = "";
    private boolean validElmguide = true;
    private Boolean showErrorMsg1 = false;
    ///////////
    private static final int CIVIL_NOT_EXIST = 0;
    private static final int CIVIL_IS_NOT_HIRED_IN_MIN = 1;
    private static final int CIVIL_IS_PERSON = 2;
    private static final int CIVIL_IS_EMP_HAS_NO_ACTIVE_BNK_ACCCOUNT = 3;
    private static final int CIVIL_IS_HAS_NO_PAYROLL_INFO = 4;
    private static final int CIVIL_IS_VALID_EMP = 5;

    EmployeeHelper employeeHelper = new EmployeeHelper();
    public final static Long  ELEMENT_GUIDE_TYPE_MULTI_FIN_TYPE_LEAF= ISALConstants.ELEMENT_GUIDE_TYPE_SPECMER_TYPE_NEW_LEAF;
    private String felsValue;
    private String dinarValue;
    private Float totalValue = 0F;
    List<IBasicDTO> currentSalVariedElmGuideDTOList = new ArrayList<IBasicDTO>();
    List<IBasicDTO> currentDisplayedSalVariedElmGuideDTOList = new ArrayList<IBasicDTO>();
    private transient HtmlDataTable salVariedElmGuidesDataTable = new HtmlDataTable();
    private Boolean civilExist=false;
    private String elmGuideExist;
    public DecisionCycleEmployeesMaintain() {

        setClient(RegClientFactory.getEmpDecisionsClient());
        setSaveSortingState(true);
        if (kuwityType == null) {
            kuwityType = KUWITY;
            employeeSearchDTO.setNationality(NATIONLALTY_KUWITY);
        }
        setDivMainContentMany("divDecisionEmpMainContentManyCustom");
        setContent1Div("reg_tabs_cont1");
        setLovDiv("m2mEditDivClass");
        //setUsingPaging(true);
        // setUsingBsnPaging(true);// modified by nora
        if (getEmpListOfValues() == null)
            setEmpListOfValues(new EmployeeListOfValues());
        getEmpListOfValues().setMyTableData(new ArrayList());

        /***by saad***/
        setTreeDivTitleKey("chooseRaiseTitle");
        setPageMode(1);
        initTreeDivBean();
        //////
        setPagingRequestDTO(new PagingRequestDTO("initEmpBean"));
    }

    /*** by saad **/
    public void initTreeDivBean() {
        treedivBean = (TreeDivBean)evaluateValueBinding("treeDivBean");
        treedivBean.setUsingTreePaging(true);
        treedivBean.setRootName("Merits_Rewards");
        treedivBean.setBundleName("com.beshara.csc.nl.reg.integration.presentation.resources.integration_");
        treedivBean.setClient(SalClientFactory.getSalElementGuidesClient());
        treedivBean.setPageDTO(SalDTOFactory.createSalElementGuidesDTO());
        treedivBean.setDto(SalDTOFactory.createSalElementGuidesDTO());
        treedivBean.setDtoDetails(SalDTOFactory.createSalElementGuidesDTO());
        treedivBean.setMyTableData(new ArrayList());
        treedivBean.generateTreeListPagingRequestDTO("financeDecisionCycleEmployeesMaintainBean", "getChildrenNodes");
        treedivBean.generateTreeSearchPagingRequestDTO("financeDecisionCycleEmployeesMaintainBean", "searchTree");
        setTreeDivTitleKey("chooseRaiseTitle");
        treedivBean.setShowTreeContent(true);
        //treedivBean.getHtmlTree().collapsePath(new String[] { "0" });
        treedivBean.setSearchQuery("");
        treedivBean.setSelectedRadio("");
        treedivBean.setGoActionOkMethod("financeDecisionCycleEmployeesMaintainBean.addEmpMerRew");
        treedivBean.getOkCommandButton().setReRender("scriptPanel,dtl_sal_elm_guide,errorcode,error,scriptGenerator,OperationBar,paging_panel");
        treedivBean.setCancelSearchMethod("financeDecisionCycleEmployeesMaintainBean.cancelTreeDegreeSearch");
        treedivBean.setSelectedEntityKeyMethod("financeDecisionCycleEmployeesMaintainBean.getSelectedIEntityKey");
    }

    public void cancelTreeDegreeSearch() {

        treedivBean.setSearchMode(false);
        treedivBean.setSearchQuery("");
        treedivBean.setSearchType(0);
        treedivBean.setDtoDetails(SalDTOFactory.createSalElementGuidesDTO());
        treedivBean.setSelectedRadio("");
        obtainMeritRewards();
    }

    /**
     * Purpose:called when user press button to open degree div
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:   26/8/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void openTreeDiv() throws DataBaseException {
        try {
            validElmguide = true;
            treedivBean.cancelSearchTree();
            //obtainBenRewardsBySelectedBenReward();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            treedivBean.setDtoDetails(SalDTOFactory.createSalElementGuidesDTO());
            treedivBean.setSelectedRadio("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IEntityKey getSelectedIEntityKey(String nodeId) {
        return SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.parseLong(nodeId));
    }

    /**
     * Purpose:called when user press ok  button in tree div
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:   26-Aug-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void addEmpMerRew() {
        if (treedivBean.getDtoDetails() != null && treedivBean.getDtoDetails().getCode() != null) {
            showErrorMsg1 = false;
            setSelectedElementGuideCode((treedivBean.getDtoDetails()).getCode().getKey().toString());
            setSelectedElementGuideName((treedivBean.getDtoDetails()).getName());
            //fillSalElementGuide();
        }
    }


    /**
     * Purpose:  fill tree div
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:   26-Aug-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void obtainMeritRewards() {
        try {
            ISalElementGuidesClient client = SalClientFactory.getSalElementGuidesClient();
            List resultList = client.searchMerReward();
            treedivBean.setMyTableData(resultList);
            treedivBean.buildTree();
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

    /**
     * Purpose:fill salElementGuidesDTO that will be used later in  process
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar , Ali Agamy
     * 1.2 - Date:   31-Aug-2008 , 24-Mar-2014
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    /*public void fillSalElementGuide() {
//        elmGuideMinValue = null;
//        elmGuideMaxValue = null;
        reIntializeMsgs();
        if (id != null && !id.equals("")) {
            ISalElementGuidesDTO salElementGuidesDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElmguideMinDTO salElemGuidesMinDTO = SalDTOFactory.createSalElmguideMinDTO();
            try {
                IBasicDTO dto =
                    SalClientFactory.getSalElementGuidesClient().getByIdNotLinkWitgCond(Long.parseLong(getId()));
                if (dto != null) {
                    salElementGuidesDTO = (ISalElementGuidesDTO)dto;

                    if ((salElementGuidesDTO.getSalElementTypesDTO().getCode().getKey().equals(benefitConst)) ||
                        (salElementGuidesDTO.getSalElementTypesDTO().getCode().getKey().equals(rewardConst))) {

                        //Ayman
                        //CSC_MER141
                        //Check that choosen Ben/Reward is joined with the current loggedIn ministry
                        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
                        Long elmguideCode = Long.parseLong(salElementGuidesDTO.getParentCode().getKey());
//                        if (salElementGuidesDTO.getMaxValue() != null) {
//                            setElmGuideMaxValue(salElementGuidesDTO.getMaxValue());
//                        }
//                        if (salElementGuidesDTO.getMinValue() != null) {
//                            setElmGuideMinValue(salElementGuidesDTO.getMinValue());
//                        }
                        salElemGuidesMinDTO =
                                (ISalElmguideMinDTO)SalClientFactory.getSalElmguideMinClient().getManualValidByElmGuideAndMinCode(elmguideCode,
                                                                                                                                  minCode);
                        if (salElemGuidesMinDTO != null) {
                            ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(salElementGuidesDTO);
                            benefitRewardCodeExists = true;
                            elemGuideCodeParentMinJoinFlag = false;
                            setSuccess(false);
                            setshowErrorMsg1(false);
                        } else {
                            ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(SalDTOFactory.createSalElementGuidesDTO());
                            benefitRewardCodeWrong = true;
                            elemGuideCodeParentMinJoinFlag = true;
                            setSuccess(false);
                            setshowErrorMsg1(false);
                        }

                    } else {
                        ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(SalDTOFactory.createSalElementGuidesDTO());
                        benefitRewardCodeWrong = true;
                        setSuccess(false);
                        setshowErrorMsg1(false);
                    }
                } else {
                    benefitRewardCodeWrong = true;
                    ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(null);
                }

            } catch (SharedApplicationException e) {
                benefitRewardCodeWrong = true;
                e.printStackTrace();
                ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(null);

            } catch (Exception e) {
                benefitRewardCodeWrong = true;
                e.printStackTrace();
                ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(null);
            }
        }
    }
*/
    public PagingResponseDTO getChildrenNodes(PagingRequestDTO pagingRequest) throws DataBaseException,
                                                                                     SharedApplicationException {

        BesharaTree node = (BesharaTree)pagingRequest.getParams()[0];
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        ISalElementGuidesClient client = SalClientFactory.getSalElementGuidesClient();
        //TODO            ISalElementGuidesClient client =
        //                SalClientFactoryOld.getSalElementGuidesClient();

        List resultList = client.getManualMerRewardCodeName(Long.parseLong(node.getTreeId()), minCode);

        treedivBean.setMyTableData(resultList);

        return new PagingResponseDTO(resultList);

    }

    public PagingResponseDTO searchTree(PagingRequestDTO pagingRequest) throws DataBaseException,
                                                                               SharedApplicationException {

        // Remove Warning Only
        if (pagingRequest == null) {
        }

        ISalElementGuidesClient client = SalClientFactory.getSalElementGuidesClient();
        //TODO            ISalElementGuidesClient client =
        //                SalClientFactoryOld.getSalElementGuidesClient();

        List resultList = null;
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        try {
            //            if (selectedBenReward.equals(BENEFIT)) {
            //                resultList =
            //                        client.searchManualBenRewardByNameAndType(treedivBean.getSearchQuery(), ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_PAY_CAT,
            //                                                                  minCode);
            //            } else if (selectedBenReward.equals(REWARD)) {
            //                resultList =
            //                        client.searchManualBenRewardByNameAndType(treedivBean.getSearchQuery(), ISalConstants.ELEMENT_GUIDE_TYPE_REWARD_PAY_CAT,
            //                                                                  minCode);
            //            }
            resultList = client.searchManualMerRewardByName(treedivBean.getSearchQuery(), minCode);
            return new PagingResponseDTO(resultList);
            //        } catch (DataBaseException dbe) {
            //            dbe.printStackTrace();
            //            return new PagingResponseDTO(new ArrayList());
            //        } catch (SharedApplicationException sae) {
            //            sae.printStackTrace();
            //            return new PagingResponseDTO(new ArrayList());
        } catch (Exception dbe) {
            dbe.printStackTrace();
            return new PagingResponseDTO(new ArrayList());
        }
    }

    public void searchElmguideForSpecificTypes(ActionEvent event) {
       setElmGuideExist("");
        showErrorMsg1 = false;
        if (selectedElementGuideCode.equals("") || selectedElementGuideCode.isEmpty()) {
            selectedElementGuideName = "";
            return;
        }
        List<IBasicDTO> list = new ArrayList();
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        try {
            /* in view mode get selectedElementGuideName direct by selectedElementGuideCode */
            if ((Boolean)BeansUtil.getValue("financeDecisionCycleMaintainBean.showOnly") ||
                (Boolean)BeansUtil.getValue("financeDecisionCycleMaintainBean.maintainMode == 2")) {
                ISalElementGuidesDTO salElementGuidesDTO = (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCodeBasics(Long.valueOf(selectedElementGuideCode));
                if(salElementGuidesDTO != null)selectedElementGuideName = salElementGuidesDTO.getName();
            } else {
                list = SalClientFactory.getSalElementGuidesClient().searchManualMerRewardByElmguideCode(
                                                                Long.valueOf(selectedElementGuideCode), minCode);
                if (!list.isEmpty()) {
                    selectedElementGuideName = list.get(0).getName();
                } else {
                    selectedElementGuideName = "";
                    showErrorMsg1 = true;
                }
            }
        } catch (DataBaseException e) {
            selectedElementGuideName = "";
            showErrorMsg1 = true;
        } catch (SharedApplicationException e) {
            selectedElementGuideName = "";
            showErrorMsg1 = true;
        } catch (Exception e) {
            selectedElementGuideName = "";
            showErrorMsg1 = true;
        }
        System.out.println(selectedElementGuideCode);
        System.out.println(showErrorMsg1);
    }

    public void reSetPage() {
        reIntializeMsgs();
        benefitRewardCodeExists = false;
        benefitRewardCodeWrong = false;
        setId(null);
        setPageDTO(SalDTOFactory.createSalEmpSalaryElementsDTO());
        //        elmGuideMinValue = null;
        //        elmGuideMaxValue = null;
    }

    public void vaildateElmguide() {
        Long[] element_guide_types =
            new Long[] { ISalConstants.ELEMENT_GUIDE_TYPE_REWARD_PAY_CAT, ELEMENT_GUIDE_TYPE_MULTI_FIN_TYPE_LEAF };
        validElmguide = false;
        if (treedivBean != null) {
            try {
                String elmtypecodeStr =
                    ((SalElementGuidesDTO)treedivBean.getDtoDetails()).getSalElementTypesDTO().getCode().getKey();
                Long elmtypeCode = Long.valueOf(elmtypecodeStr);
                for (int i = 0; i < element_guide_types.length; ++i) {
                    if (elmtypeCode == element_guide_types[i]) {
                        validElmguide = true;
                        break;
                    }
                }
            } catch (Exception e) {
                validElmguide = false;
            }
            if (validElmguide)
                treedivBean.goActionOk(null);

        }
    }

    /** end by saad **/
    public void initiateBeanOnce() {

        //initEmpSearchBean();

        //loadElementGuideTypeList();
        //loadElementTypeCode();

        //        Calendar currentDate = Calendar.getInstance();
        //        month = Integer.toString(currentDate.get(Calendar.MONTH) + 1);
        //        year = Integer.toString(currentDate.get(Calendar.YEAR));

        setElementGuideCode();
        DecisionCycleMaintainBean decisionMaintainBean =
            (DecisionCycleMaintainBean)evaluateValueBinding("financeDecisionCycleMaintainBean");
        if (decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_EDIT
            || decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_VIEW_ONLY ) {
            getTotal_Value();
            IDecisionsDTO pageDTO = (IDecisionsDTO)decisionMaintainBean.getPageDTO();
            if (pageDTO.getViewFlag().equals(Long.parseLong(decisionMaintainBean.OneEmpMultiElements))) {
                try {
                    IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                        (IKwtCitizensResidentsDTO)((IEmployeesDTO)((IEmpDecisionsDTO)pageDTO.getEmpDecisionsDTOList().get(0)).getEmployeesDTO()).getCitizensResidentsDTO();
                    empFullName = kwtCitizensResidentsDTO.getFullName();
                    civilID = kwtCitizensResidentsDTO.getCivilId();
                    civilExist = true;
                    civil_exist="";
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        System.out.println(month + "--" + year);
        //loadHireCurrentStatuses();
    }

    public void loadElementGuideTypeList() {
        try {
            Long elementType = IConstants.ELEMENT_CODE_FOR_MER;
            elementGuideTypeList = SalClientFactory.getSalElementGuidesClient().getCodeNameByTypeCode(elementType);
        } catch (Exception e) {
            e.printStackTrace();
            elementGuideTypeList = new ArrayList<IBasicDTO>();
        }
    }

    public void loadElementTypeCode() {
        try {
            Long elementTypeCode = IConstants.ELEMENT_TYPE_CODE_FOR_MER;
            elementGuideList =
                    SalClientFactory.getSalElementGuidesClient().getAllByTypeAndCodeDegree(elementTypeCode, Long.valueOf(elementGuideCode));
        } catch (Exception e) {
            e.printStackTrace();
            elementGuideList = new ArrayList<IBasicDTO>();
        }
    }

    //    public void loadHireCurrentStatuses() {
    //
    //        try {
    //            hireCurrentStatuses = EmpClientFactory.getHireStagesClient().getCodeName();
    //        } catch (DataBaseException e) {
    //            e.printStackTrace();
    //        }
    //    }

    //    private void initEmpSearchBean() {
    //        if (getEmployeeSearchBean() == null) {
    //            setEmployeeSearchBean(new EmployeeSearchBean());
    //        }
    //    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();

        app.setShowDelConfirm(false);
        app.setShowSearch(false);
        app.setShowLookupAdd(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowpaging(false);
        app.setShowdatatableContent(false);

        //app.setShowCustomDiv2(Boolean.TRUE);
        //app.setShowCustomDiv1(true);
        app.setShowbar(true);
        //FacesContext.getCurrentInstance().getViewRoot().getViewId();
        app.showManyToManyMaintain();
        app.setShowContent1(true);
        app.setShowEmpSrchDiv(true);
        app.setShowPagedTreediv(true);
        app.setShowScirptGenerator(true);
        app.setShowdatatableContent(true);
        return app;
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {
        return new PagingResponseDTO();
    }

    private void setElementGuideCode() {
        DecisionCycleMaintainBean decisionMaintainBean =
            (DecisionCycleMaintainBean)evaluateValueBinding("financeDecisionCycleMaintainBean");
        if (decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_EDIT
            || decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_VIEW_ONLY) {
            //            IRegFinancialSearchDTO regFinancialSearchDTO = null;
            //            IRegFinancialSearchDTO regPersonFinancialSearchDTO = null;
            //            List<IBasicDTO> payislipList = new ArrayList<IBasicDTO>();
            try {
                //                regFinancialSearchDTO =
                //                        RegClientFactory.getRegEmpDecisionDtlsClient().getRelatedEmpSalElements(decisionMaintainBean.getPageDTO());
                //                if (regFinancialSearchDTO.getTabrecSerials().size() == 0) {
                //                    regPersonFinancialSearchDTO =
                //                            RegClientFactory.getRegPersonDecisionDtlsClient().getRelatedPersonSalElements(decisionMaintainBean.getPageDTO());
                //                    payislipList =
                //                            SalClientFactory.getSalEmpMonSalariesClient().getAllByTabrecSerials(regPersonFinancialSearchDTO);
                //                } else {
                //                    payislipList =
                //                            SalClientFactory.getSalEmpMonSalariesClient().getAllByTabrecSerials(regFinancialSearchDTO);
                //                }
                //
                //                if (payislipList == null || payislipList.isEmpty()) {
                //                    SalClientFactory.getSalEmpMonSalariesClient().getAllByTabrecSerials(regPersonFinancialSearchDTO);
                //                }

                IDecisionsDTO pageDTO = (IDecisionsDTO)decisionMaintainBean.getPageDTO();
                if (pageDTO != null) {
                    // setElementGuideCode(((SalEmpPayslipsDTO)((SalEmpMonSalariesDTO)payislipList.get(0)).getPayslipsDetailsList().get(0)).getElementGuidesDTO().getElmguideCodeDegree().toString());
                    if ((selectedElementGuideCode == null || selectedElementGuideCode.equals(""))&& pageDTO.getViewFlag() != null && pageDTO.getViewFlag().equals(Long.parseLong(decisionMaintainBean.OneElementMultiEmps))) {
                        Long elmguideCode = pageDTO.getElemGuideCode();
                        if (elmguideCode != null) {
                            setSelectedElementGuideCode(elmguideCode + "");
                        } else {
                            selectedElementGuideCode = "";
                        }
                    }
                    if (month == null || month.equals(""))
                        setMonth(((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getSalMonthCode() + "");
                    if (year == null || year.equals(""))
                        setYear(((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getSalYearCode() + "");

                    setEnableElementGuideCombo(false);
                    
                    if(pageDTO.getViewFlag().equals(0L)){
                        currentSalVariedElmGuideDTOList = ( (EmpDecisionsDTO)pageDTO.getEmpDecisionsDTOList().get(0)).getSalElmGuideDTOList();
                    }
                    
                }


             
                if (selectedElementGuideName == null || selectedElementGuideName.equals(""))
                    searchElmguideForSpecificTypes(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            // Calendar currentDate = Calendar.getInstance();
            if (month == null)
                month = "";
            if (year == null)
                year = "";
        }
    }

    public PagingResponseDTO initEmpBean(PagingRequestDTO request) {
        PagingResponseDTO responseDTO = new PagingResponseDTO();
        try {
            DecisionCycleMaintainBean decisionMaintainBean =
                (DecisionCycleMaintainBean)evaluateValueBinding("financeDecisionCycleMaintainBean");
            //        if (((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList() != null &&
            //            ((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList().size() > 0 &&
            //              tempResponse == null) {
            //   responseDTO.setResultList(((IDecisionsDTO)decisionMaintainBean.getPageDTO()).getEmpDecisionsDTOList());
            //                if (decisionMaintainBean.getMaintainMode() == decisionMaintainBean.MAINTAIN_MODE_EDIT) {
            //                    IRegFinancialSearchDTO regFinancialSearchDTO = null;
            //                    IRegFinancialSearchDTO regPersonFinancialSearchDTO = null;
            //                    tempResponse = new PagingResponseDTO();
            //                    List<IBasicDTO> salPersonMonSalaries = new ArrayList();
            //                    try {
            //                        regFinancialSearchDTO =
            //                                RegClientFactory.getRegEmpDecisionDtlsClient().getRelatedEmpSalElements(decisionMaintainBean.getPageDTO());
            //                       regPersonFinancialSearchDTO =
            //                             RegClientFactory.getRegPersonDecisionDtlsClient().getRelatedPersonSalElements(decisionMaintainBean.getPageDTO());
            //                        boolean found = false;
            //                        int dotIndex = 0;
            //                        for (int i = 0; i < getCurrentDetails().size(); i++) {
            //                            //regFinancialSearchDTO.getTabrecSerials().size()+regPersonFinancialSearchDTO.getTabrecSerials().size()
            //                            Long civilId = ((IEmpDecisionsEntityKey)getCurrentDetails().get(i).getCode()).getCivilId();
            //                            regFinancialSearchDTO.setCivilId(civilId);
            //                            regPersonFinancialSearchDTO.setCivilId(civilId);
            //                            setSalEmpMonSalaries(SalClientFactory.getSalEmpMonSalariesClient().getAllByTabrecSerials(regFinancialSearchDTO));
            //                            String denar = "";
            //                            String fels = "";
            //                            found = false;
            //                            for (int j = 0; j < regFinancialSearchDTO.getTabrecSerials().size(); j++) {
            //                                if (salEmpMonSalaries != null && !salEmpMonSalaries.isEmpty() &&
            //                                    civilId.equals(((SalEmpMonSalariesEntityKey)((SalEmpMonSalariesDTO)(salEmpMonSalaries.get(j))).getCode()).getCivilId())) {
            //                                    String elementValue =
            //                                        ((SalEmpMonSalariesDTO)(salEmpMonSalaries.get(j))).getValueAsFloat().toString();
            //                                    dotIndex = elementValue.indexOf('.');
            //                                    denar = elementValue.substring(0, dotIndex);
            //                                    fels = elementValue.substring(dotIndex + 1, elementValue.length());
            //                                    found = true;
            //                                    break;
            //                                }
            //                            }
            //                            if (!found) { // he is person not employee
            //                                salPersonMonSalaries =
            //                                        SalClientFactory.getSalEmpMonSalariesClient().getAllByTabrecSerials(regPersonFinancialSearchDTO);
            //                                for (int k = 0; k < regPersonFinancialSearchDTO.getTabrecSerials().size(); k++) {
            //                                    if (salPersonMonSalaries != null && !salPersonMonSalaries.isEmpty()) {
            //                                        if (civilId.equals(((SalEmpMonSalariesEntityKey)((SalEmpMonSalariesDTO)(salPersonMonSalaries.get(k))).getCode()).getCivilId())) {
            //                                            String elementValue =
            //                                                ((SalEmpMonSalariesDTO)(salPersonMonSalaries.get(k))).getValueAsFloat().toString();
            //                                            dotIndex = elementValue.indexOf('.');
            //                                            denar = elementValue.substring(0, dotIndex);
            //                                            fels = elementValue.substring(dotIndex + 1, elementValue.length());
            //                                            break;
            //                                        }
            //                                    }
            //
            //                                }
            //                            }
            //                            ((EmpDecisionsDTO)responseDTO.getResultList().get(i)).setDenarValue(denar);
            //                            ((EmpDecisionsDTO)responseDTO.getResultList().get(i)).setFelsValue(fels);
            //                        }
            //                    } catch (DataBaseException e) {
            //                    } catch (SharedApplicationException e) {
            //                    }
            //}
            //     }
            if (getSelectedCurrentDetails() != null && getSelectedCurrentDetails().size() > 0) {
                for (int y = 0; y < getSelectedCurrentDetails().size(); y++) {
                    for (int i = 0; i < getCurrentDetails().size(); i++) {
                        if (((IEmployeesEntityKey)getSelectedCurrentDetails().get(y).getCode()).getCivilId().equals(((IEmpDecisionsEntityKey)getCurrentDetails().get(i).getCode()).getCivilId())) {
                            ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setChecked(true);
                        } else {
                            ((IEmpDecisionsDTO)getCurrentDetails().get(i)).setChecked(false);
                        }
                    }
                }

            }
            responseDTO.setResultList(getCurrentDisplayDetails());
            responseDTO.setTotalListSize(getCurrentListSize());
            // setUpdateMyPagedDataModel(true);
        } catch (Exception e) {
            e.printStackTrace();
            new PagingResponseDTO(new ArrayList());
        }
        return responseDTO;

    }

    public void changeKuwityType() {
        if (employeeSearchDTO.getNationality() == KUWITY) {
            resetNonKuwityData();
            employeeSearchDTO.setNationality(NATIONLALTY_KUWITY);
        } else {
            employeeSearchDTO.setNationality(null);
        }
    }

    private void resetNonKuwityData() {
        employeeSearchDTO.setNationality(null);
        employeeSearchDTO.setPassportNo(null);
        employeeSearchDTO.setResidentTypeCode(null);
        employeeSearchDTO.setEndResidentDate(null);
    }

    public void reSetData() {
        setAvailableDetails(new ArrayList<IBasicDTO>());
        currentDetails = new ArrayList<IBasicDTO>();
        getSelectedCurrentDetails().clear();
        setSelectedDTOS(new ArrayList());

    }

    public void resetEmpData(ActionEvent e){
        empFullName=null;
        civilID=null;
        civilExist=false;
    }
    public List getCurrentDisplayDetailsold() {
        if (getIntegrationBean() != null && getIntegrationBean().getSelectedDTOFrom() != null &&
            getIntegrationBean().getSelectedDTOFrom().size() != 0) {
            getSelectedAvailableDetails().addAll(getIntegrationBean().getSelectedDTOFrom());
            super.add();
        }
        if (!isCancelDecisionMode()) {
            return super.getCurrentDisplayDetails();
        }
        List<IBasicDTO> currentDisplayed = null;
        DecisionCycleMaintainBean decisionBean =
            (DecisionCycleMaintainBean)BeansUtil.getValue("financeDecisionCycleMaintainBean");
        if (decisionBean != null) {
            IDecisionsDTO decision = (IDecisionsDTO)decisionBean.getPageDTO();
            if (decision != null) {
                IDecisionsDTO canceledDecision = decision.getDecisionsDTO();
                if ((Boolean)BeansUtil.getValue("financeDecisionMaintainBean.maintainMode == 1")) {
                    try {
                        canceledDecision =
                                (IDecisionsDTO)(RegClientFactory.getDecisionsClient().getById(canceledDecision.getCode()));
                    } catch (SharedApplicationException e) {
                        e.printStackTrace();
                    } catch (DataBaseException e) {
                        e.printStackTrace();
                    }
                }
                if (canceledDecision != null) {
                    currentDisplayed = canceledDecision.getEmpDecisionsDTOList();
                    setCurrentDisplayDetails(currentDisplayed);
                }
            }
        }
        return currentDisplayed;
    }

    public void cancelSearchAvailable() throws DataBaseException, SharedApplicationException {
        setAvailableDetails(new ArrayList<IBasicDTO>());
        setSearchMode(false);
        getSelectedCurrentDetails().clear();
    }

    public List<IBasicDTO> getCurrentDisplayDetails() {

        currentDisplayDetails = new ArrayList<IBasicDTO>(0);

        if (currentDetails != null) {
            for (IBasicDTO dto : currentDetails) {
                if (dto.getStatusFlag() == null)
                    currentDisplayDetails.add(dto);
                if (dto.getStatusFlag() != null && dto.getStatusFlag().longValue() == added.longValue())
                    currentDisplayDetails.add(dto);
                if (dto.getStatusFlag() != null && dto.getStatusFlag().longValue() == IConstants.UPDATED_ITEM.longValue())
                    currentDisplayDetails.add(dto);
            }
        }
        return currentDisplayDetails;

    }

    public void getListAvailable() throws DataBaseException {
        DecisionCycleMaintainBean decisionBean =
            (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{financeDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
        IDecisionsDTO decision = (IDecisionsDTO)decisionBean.getPageDTO();
        if (decision != null) {
            IDecisionsDTO canceledDecision = decision.getDecisionsDTO();
            if (canceledDecision != null) {
                setAvailableDetails(canceledDecision.getEmpDecisionsDTOList());
            }
        }
    }

    public void add() {
        super.add();
        navigateToDecision();
    }

    public void cancel() {
        setSelectedAvailableDetails(new ArrayList<IBasicDTO>());
        navigateToDecision();
    }

    private void navigateToDecision() {
        setDivMainContent("divDECEmployeesMainContent");
        FacesContext fContext = FacesContext.getCurrentInstance();
        Application app = fContext.getApplication();

        app.createValueBinding("#{wizardbar.currentStep}").setValue(fContext, "financeemployeesadd");

        String pageNavigation = "financedecisioncycleemployeesmaintain";
        if ((Boolean)app.createValueBinding("#{financeDecisionCycleMaintainBean.maintainMode == 1}").getValue(fContext)) {
            pageNavigation += "edit";
        }
        setAvailableDetails(new ArrayList<IBasicDTO>());
        app.getNavigationHandler().handleNavigation(fContext, null, pageNavigation);
    }
    public void showDeleteDiv() {
        System.out.println(getSelectedCurrentDetails());
    }


    public void generalDelete() {
        DecisionCycleMaintainBean decisionBean =
            (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{financeDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
        if (!decisionBean.getMultiOrSingleEmps().isEmpty() && decisionBean.getMultiOrSingleEmps().equals(decisionBean.OneEmpMultiElements)) {
            deleteElementGuide();
        } else {
            delete();
        }
    }
    public void delete() {
        List<IBasicDTO> list = getCurrentDetails();
        setHighlightedDTOS(new ArrayList());
        try {
            if (getCurrentDetails() == null)
                setCurrentDetails(new ArrayList<IBasicDTO>());
            DecisionCycleMaintainBean decisionBean =
                (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{financeDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
            decisionBean.getPageDTO();
            Long _civilId = null;
            for (IBasicDTO bassicDTO : getSelectedCurrentDetails()) {
                IEmployeesDTO dto = (IEmployeesDTO)bassicDTO;
                if (dto.getStatusFlag() == null) {
                    for (IBasicDTO basDTO : list) {
                        IEmpDecisionsDTO empDecDTO = (IEmpDecisionsDTO)basDTO;
                        if (dto.getCode() != null) {
                            _civilId = Long.parseLong(dto.getCode().getKey());
                        } else if (dto.getCitizensResidentsDTO() != null) {
                            _civilId = Long.parseLong(dto.getCitizensResidentsDTO().getCode().getKey());
                        }
                        if (((IEmpDecisionsEntityKey)empDecDTO.getCode()).getCivilId().equals(_civilId)) {
                            if (empDecDTO.getStatusFlag() == null) {
                                empDecDTO.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                                getSelectedCurrentDetails().remove(empDecDTO);
                            }
                            if (empDecDTO.getStatusFlag().longValue() == ISystemConstant.ADDED_ITEM.longValue()) {
                                getCurrentDetails().remove(empDecDTO);
                                getSelectedCurrentDetails().remove(empDecDTO);
                            }
                            break;
                        }
                    }
                }
            }
            getTotal_Value();
            this.restoreDetailsTablePosition(this.getCurrentDataTable(), this.getCurrentDetails());
            this.resetSelection();
            PagingResponseDTO responseDTO = new PagingResponseDTO();
            responseDTO.setResultList(getCurrentDisplayDetails());
            responseDTO.setTotalListSize(getCurrentListSize());
            this.getPagingBean().updateMyPadgedDataModel(responseDTO);
            // getCurrentDataTable().setValue(getCurrentDisplayDetails());
            getCurrentDataTable().setFirst(0);
            cancelSearch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteElementGuide() {
        List<IBasicDTO> list = getCurrentSalVariedElmGuideDTOList();
        setHighlightedDTOS(new ArrayList());
        try {
            if (getCurrentSalVariedElmGuideDTOList() == null)
                setCurrentSalVariedElmGuideDTOList(new ArrayList<IBasicDTO>());
            DecisionCycleMaintainBean decisionBean =
                (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{financeDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
            decisionBean.getPageDTO();
            Long _elmguideCode = null;
            if (getSelectedCurrentDetails() != null && getSelectedCurrentDetails().size() > 0) {

                for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                    IBasicDTO bassicDTO = getSelectedCurrentDetails().get(i);            
                    ISalElementGuidesDTO dto = (ISalElementGuidesDTO)bassicDTO;
                if (dto.getStatusFlag() == null || dto.getStatusFlag().equals(added)) {
                    for (IBasicDTO basDTO : list) {
                        ISalElementGuidesDTO salDTO = (ISalElementGuidesDTO)basDTO;
                        if (dto.getCode() != null) {
                            _elmguideCode = Long.parseLong(dto.getCode().getKey());
                        }
                        if (((ISalElementGuidesEntityKey)salDTO.getCode()).getElmguideCode().equals(_elmguideCode)) {
                            if (salDTO.getStatusFlag() == null) {
                                salDTO.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                                getSelectedCurrentDetails().remove(salDTO);
                                i--;
                            }
                            if (salDTO.getStatusFlag().longValue() == ISystemConstant.ADDED_ITEM.longValue()) {
                                getCurrentSalVariedElmGuideDTOList().remove(salDTO);
                                getSelectedCurrentDetails().remove(salDTO);
                                i--;
                            }
                            break;
                        }
                    }
                }
            }
        }
            getTotal_ValueForOneEmpMultiElements();
            getSalVariedElmGuidesDataTable().setFirst(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //    public void showSearchForEmployeeDiv() {
    //        employeeSearchBean.setReturnMethodName(BEAN_NAME + ".returnMethodAction");
    //        employeeSearchBean.resetData();
    //        employeeSearchBean.getAdvanceEmployeesAddBean().setBackMethod("financeDecisionCycleEmployeesMaintainBean.backFromSearch");
    //        employeeSearchBean.getAdvanceEmployeesAddBean().setReturnMethod("financeDecisionCycleEmployeesMaintainBean.employeeAddMethod");
    //        employeeSearchBean.getAdvanceEmployeesAddBean().setSingleSelection(false);
    //        employeeSearchBean.getOkCommandButton().setReRender("customDiv1,empInquiryCnt1Pnl,dataT_data_panel");
    //    }

    //    public String closeDiv() {
    //        employeeSearchBean.setResetDivAfterClose(true);
    //        IEmployeesDTO selectedEmployeesDTO = ((IEmployeesDTO)employeeSearchBean.getSelectedDTOS().get(0));
    //        setCivilID(selectedEmployeesDTO.getRealCivilId());
    //        findEmployeeByCivilIDFromEmployees();
    //        return null;
    //    }


    public boolean validate() {
        DecisionCycleMaintainBean decisionBean =
            (DecisionCycleMaintainBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{financeDecisionCycleMaintainBean}").getValue(FacesContext.getCurrentInstance());
        
        if (DecisionCycleMaintainBean.isIntegrationPage()) {
            return true; //TODO
        }
        if (!decisionBean.getMultiOrSingleEmps().isEmpty() && decisionBean.getMultiOrSingleEmps().equals(decisionBean.OneEmpMultiElements)) {
            if (!validateDinarNumberForElementGuide()) {
                return false;
            }
        } else {
            if (!validateDinarNumber()) {
                return false;
            }
        }
        if (decisionBean.getMultiOrSingleEmps().isEmpty() || decisionBean.getMultiOrSingleEmps().equals(decisionBean.OneElementMultiEmps)) {
        boolean validEmpCount = (getCountOfAllEmpDecision() > 0);
        boolean validateElementGuide = validateElementGuideValue();
        if (!validEmpCount && !validateElementGuide) {
            showErrMsg = true;
            setCivil_exist("");
            setShowElementGuideErrMsg(true);
            return validEmpCount;
        } else if (validEmpCount && !validateElementGuide) {
            setShowElementGuideErrMsg(true);
            return validateElementGuide;
        } else if (!validEmpCount && validateElementGuide) {
            showErrMsg = true;
            setCivil_exist("");
            return validEmpCount;
         }
        }
        return true;
    }

    /**
     * @description : to validate if the Denar value Is empty or not, as it's mandatory field,
     * and JavaScript cannot handle this situation when the "myDataTable" Viewed in pages...
     * @since: 7-December-2015
     * @Auther : N.Osama
     */
    private boolean validateDinarNumber() {
        boolean valid = true;

        List displayedResultList = null;
        try {
            displayedResultList = getCurrentDisplayDetails();

            if (displayedResultList != null && !displayedResultList.isEmpty()) {
                for (Object dto1 : displayedResultList) {
                    EmpDecisionsDTO dto = (EmpDecisionsDTO)dto1;
                    //                    System.out.println("civilId>>"+((IEmpDecisionsEntityKey)dto.getCode()). getCivilId());
                    //                    System.out.println("FelsValue>>"+dto.getFelsValue());
                    //                    System.out.println("DenarValue>>"+dto.getDenarValue());
                    if ((dto.getFelsValue() == null || dto.getFelsValue().isEmpty() ||  dto.getFelsValue().equals("0"))&&(dto.getDenarValue() == null || dto.getDenarValue().isEmpty() ||  dto.getDenarValue().equals("0"))) {
                        valid = false;
                        dto.setErrorValueMessage(getSharedUtil().messageLocator(GLOBAL_BUNDLE, "missingField"));
                    } else {
                        dto.setErrorValueMessage(null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }
    
    private boolean validateDinarNumberForElementGuide() {
        boolean valid = true;

        List displayedResultList = null;
        try {
            displayedResultList = getCurrentDisplayedSalVariedElmGuideDTOList();

            if (displayedResultList != null && !displayedResultList.isEmpty()) {
                for (Object dto1 : displayedResultList) {
                    ISalElementGuidesDTO salDTO = (ISalElementGuidesDTO)dto1;
                   
                    if ((salDTO.getFelsValue() == null || salDTO.getFelsValue().isEmpty() ||  salDTO.getFelsValue().equals("0"))&&(salDTO.getDenarValue() == null || salDTO.getDenarValue().isEmpty() ||  salDTO.getDenarValue().equals("0"))) {
                        valid = false;
                        salDTO.setOtherDesc(getSharedUtil().messageLocator(GLOBAL_BUNDLE, "missingField"));
                    } else {
                        salDTO.setOtherDesc(null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }
    
    public void getTotal_Value() {
        totalValue = 0F;

        List displayedResultList = null;
        try {
            displayedResultList = getCurrentDisplayDetails();

            if (displayedResultList != null && !displayedResultList.isEmpty()) {
                for (Object dto1 : displayedResultList) {
                    EmpDecisionsDTO dto = (EmpDecisionsDTO)dto1;
                    if ((dto.getFelsValue() != null && !dto.getFelsValue().isEmpty()) || (dto.getDenarValue() != null &&
                        !dto.getDenarValue().isEmpty())) {
                        Float calculatedVal = calcValue(dto.getDenarValue(), dto.getFelsValue());
                        totalValue = totalValue + calculatedVal;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void  getTotal_ValueForOneEmpMultiElements() {
        totalValue = 0F;

        List displayedResultList = null;
        try {
            displayedResultList = getCurrentDisplayedSalVariedElmGuideDTOList();

            if (displayedResultList != null && !displayedResultList.isEmpty()) {
                for (Object dto1 : displayedResultList) {
                    ISalElementGuidesDTO dto = (ISalElementGuidesDTO)dto1;
                    if ((dto.getFelsValue() != null && !dto.getFelsValue().isEmpty()) || (dto.getDenarValue() != null &&
                        !dto.getDenarValue().isEmpty())) {
                        Float calculatedVal = calcValue(dto.getDenarValue(), dto.getFelsValue());
                        dto.setValue(calculatedVal);
                        totalValue = totalValue + calculatedVal;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        return totalValue;
    }

    public void adjustTotalValue() {
        try {
            EmpDecisionsDTO dto = (EmpDecisionsDTO)getCurrentDataTable().getRowData();
            if (dto.getFelsValue() != null && !dto.getFelsValue().isEmpty() && dto.getDenarValue() != null &&
                !dto.getDenarValue().isEmpty()) {
                Float currentCalculatedVal = calcValue(dto.getDenarValue(), dto.getFelsValue());
                Float originalCalculatedVal = calcValue(dto.getDenarValueOriginal(), dto.getFelsValueOriginal());
                Float diffValue = currentCalculatedVal - originalCalculatedVal;
                totalValue = totalValue + diffValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public float calcValue(String denar, String fels) {
        Float calculatedValue = null;
        fels = fels != null ? fels : "0";
        String value = "";
        if (denar != null && fels != null) {
            value = denar + "." + fels;
        }
        if (value != null && !value.isEmpty())
            calculatedValue = Float.parseFloat(value);
        else
            calculatedValue = 0F;
        return calculatedValue;
    }

    public boolean validateElementGuideValue() {
        if (selectedElementGuideCode != null &&
            !ISystemConstant.VIRTUAL_VALUE.toString().equals(selectedElementGuideCode)) {
            return true;
        }
        return false;
    }

    //    public String navigateAdd() {
    //        try {
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setSearchMethod("financeEmpDecisionCycleAddBean.filterSearchByEmpWithPaging");
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setShowResultWithinPage(false);
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setShowCategoryCB(false);
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setShowMinistryCB(false);
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setShowWorkEndDate(false);
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setShowHireStatus(false);
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setShowCurrentHireStatus(false);
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setShowWorkCenterLovDiv(true);
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setShowJobLovDiv(true);
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setUsingBsnPaging(true);
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setUsingPaging(true);
    //            List employeesList = getCurrentDetails();
    //            List empList = new ArrayList();
    //            if (employeesList == null)
    //                setCurrentDetails(new ArrayList<IBasicDTO>());
    //            for (IEmpDecisionsDTO evalEmp : (ArrayList<IEmpDecisionsDTO>)employeesList) {
    //                IEmployeesDTO emp = (IEmployeesDTO)evalEmp.getEmployeesDTO();
    //                emp.setStatusFlag(evalEmp.getStatusFlag());
    //                empList.add(emp);
    //            }
    //            employeeSearchBean.getAdvanceEmployeesAddBean().setCurrentDetails(empList);
    //
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        return "Advanced_Add_Employees_decisionFinance_cycle";
    //    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        if (isCancelDecisionMode()) {
            return dto; // as i got it from the canceled decision there is no need for the mapping
        }
        return ((IEmpDecisionsDTO)dto).getEmployeesDTO();
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        if (isCancelDecisionMode()) {
            return dto; // as i got it from the canceled decision there is no need for the mapping
        }
        IEmpDecisionsDTO empIDecisionsDTO = RegDTOFactory.createEmpDecisionsDTO();
        empIDecisionsDTO.setEmployeesDTO((IEmployeesDTO)dto);
        IDecisionsDTO decisionsDTO = null;
        IDecisionsEntityKey decisionsEntityKey = null;
        IEmpDecisionsEntityKey empDecisionsEntityKey = null;

        if (this.getMasterEntityKey() != null) {
            decisionsEntityKey = (IDecisionsEntityKey)this.getMasterEntityKey();
            decisionsDTO = RegDTOFactory.createDecisionsDTO();
            decisionsDTO.setCode(decisionsEntityKey);
            empIDecisionsDTO.setDecisionsDTO(decisionsDTO);

        }

        if (decisionsEntityKey != null) {
            empDecisionsEntityKey =
                    RegEntityKeyFactory.createEmpDecisionsEntityKey(decisionsEntityKey.getDectypeCode(),
                                                                    decisionsEntityKey.getDecyearCode(),
                                                                    decisionsEntityKey.getDecisionNumber(),
                                                                    Long.valueOf(dto.getCode().getKey().toString()));
        } else {
            empDecisionsEntityKey =
                    RegEntityKeyFactory.createEmpDecisionsEntityKey(null, null, null, Long.valueOf(dto.getCode().getKey().toString()));
        }
        empIDecisionsDTO.setCode(empDecisionsEntityKey);

        return empIDecisionsDTO;
    }

    public void setIEmployeeSearchDTO(IEmployeeSearchDTO employeeSearchDTO) {
        this.employeeSearchDTO = employeeSearchDTO;
    }

    public IEmployeeSearchDTO getIEmployeeSearchDTO() {
        return employeeSearchDTO;
    }

    public Long getKuwity() {
        return KUWITY;
    }

    public Long getNonKuwity() {
        return NON_KUWITY;
    }

    //    public void setHireTypes(List<IBasicDTO> hireTypes) {
    //        this.hireTypes = hireTypes;
    //    }
    //
    //    public List<IBasicDTO> getHireTypes() throws DataBaseException {
    //
    //        return hireTypes;
    //    }

    //    public void setHireStatuses(List<IBasicDTO> hireStatuses) {
    //        this.hireStatuses = hireStatuses;
    //    }
    //
    //    public List<IBasicDTO> getHireStatuses() throws DataBaseException {
    //        if (hireStatuses.size() == 0) {
    //            hireStatuses = EmpClientFactory.getHireStatusClient().getCodeName();
    //        }
    //        return hireStatuses;
    //    }
    //
    //    public void setHireCurrentStatuses(List<IBasicDTO> hireCurrentStatuses) {
    //        this.hireCurrentStatuses = hireCurrentStatuses;
    //    }
    //
    //    public List<IBasicDTO> getHireCurrentStatuses() throws DataBaseException {
    //
    //        return hireCurrentStatuses;
    //    }

    public void setKuwityType(Long kuwityType) {
        this.kuwityType = kuwityType;
    }

    public Long getKuwityType() {
        return kuwityType;
    }


    public void setIsSearchMode(boolean isSearchMode) {
        this.isSearchMode = isSearchMode;
    }

    public boolean isIsSearchMode() {
        return isSearchMode;
    }

    public void setShowErrMsg(boolean showErrMsg) {
        this.showErrMsg = showErrMsg;
    }

    public boolean isShowErrMsg() {
        return showErrMsg;
    }

    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
    }

    public void setPageIndexAdd(Integer pageIndexAdd) {
        pageIndexAdd = 0;
    }

    public Integer getPageIndexAdd() {
        if (dataScroller != null) {
            return dataScroller.getPageIndex();
        }

        return 0;
    }

    public void setEmpDecisionsDTOSlelected(IEmpDecisionsDTO empDecisionsDTOSlelected) {
        this.empDecisionsDTOSlelected = empDecisionsDTOSlelected;
    }

    public IEmpDecisionsDTO getEmpDecisionsDTOSlelected() {
        return empDecisionsDTOSlelected;
    }

    public void setAddEmpDecisionsDTOModule(boolean addEmpDecisionsDTOModule) {
        this.addEmpDecisionsDTOModule = addEmpDecisionsDTOModule;
    }

    public boolean isAddEmpDecisionsDTOModule() {
        return addEmpDecisionsDTOModule;
    }


    public void setEmpDecisionsDTOSlelectedList(List<IEmpDecisionsDTO> empIDecisionsDTOSlelectedList) {
        this.empDecisionsDTOSlelectedList = empIDecisionsDTOSlelectedList;
    }

    public List<IEmpDecisionsDTO> getEmpDecisionsDTOSlelectedList() {
        return empDecisionsDTOSlelectedList;
    }


    //    public void resetRegModuleDiv() {
    //        FacesContext.getCurrentInstance().getApplication().createValueBinding("#{regulationModulesMaintainBean}").setValue(FacesContext.getCurrentInstance(),
    //                                                                                                                           new RegulationModulesMaintain());
    //    }

    public void setShowBarMainData(boolean showBarMainData) {
        this.showBarMainData = showBarMainData;
    }

    public boolean isShowBarMainData() {
        return showBarMainData;
    }

    public void setUnDeleteEmpList(List unDeleteList) {
        this.unDeleteEmpList = unDeleteList;
    }

    public List getUnDeleteEmpList() {
        return unDeleteEmpList;
    }

    public void setValueNum(Long valueNum) {
        this.valueNum = valueNum;
    }

    public Long getValueNum() {
        return valueNum;
    }

    public void setValueChr(String valueChr) {
        this.valueChr = valueChr;
    }

    public String getValueChr() {
        return valueChr;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setEmpInfTypeCode(Long empInfTypeCode) {
        this.empInfTypeCode = empInfTypeCode;
    }

    public Long getEmpInfTypeCode() {
        return empInfTypeCode;
    }

    public void setBooleanInformEmpFlag(Boolean booleanInformEmpFlag) {
        this.booleanInformEmpFlag = booleanInformEmpFlag;
    }

    public Boolean getBooleanInformEmpFlag() {
        return booleanInformEmpFlag;
    }

    public void setInformEmpFlag(Long informEmpFlag) {
        this.informEmpFlag = informEmpFlag;
    }

    public Long getInformEmpFlag() {
        return informEmpFlag;
    }

    public void setInformEmpDate(Date informEmpDate) {
        this.informEmpDate = informEmpDate;
    }

    public Date getInformEmpDate() {
        return informEmpDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }


    public void setActiveValidationOnAdd(boolean activeValidationOnAdd) {
        this.activeValidationOnAdd = activeValidationOnAdd;
    }

    public boolean isActiveValidationOnAdd() {
        return activeValidationOnAdd;
    }

    public void setActiveValidationOnEdit(boolean activeValidationOnEdit) {
        this.activeValidationOnEdit = activeValidationOnEdit;
    }

    public boolean isActiveValidationOnEdit() {
        return activeValidationOnEdit;
    }

    public void setCivilID(Long civilID) {
        this.civilID = civilID;
    }

    public Long getCivilID() {
        return civilID;
    }

    //    public void findEmployeeByCivilIDFromEmployeesold() {
//    
    //        boolean employeeExist = true;
    //        List displayList =getCurrentDisplayDetails();
    //        List fullList=getCurrentDetails();
    //        for (IBasicDTO dto1 : displayList) {
    //            if ((((IEmpDecisionsEntityKey)dto1.getCode()).getCivilId().equals(getCivilID()))) {
    //                setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "employee_added_before"));
    //                employeeExist = true;
    //                setShowErrMsg(false);
    //                return;
    //            }
    //        }
    //        if (employeeExist) {
    //            for (IBasicDTO dto2 : fullList) {
    //                if ((((IEmpDecisionsEntityKey)dto2.getCode()).getCivilId().equals(getCivilID()))) {
    //                    dto2.setStatusFlag(null);
    //                    ((EmpDecisionsDTO)dto2).setFelsValue("0");
    //                    ((EmpDecisionsDTO)dto2).setDenarValue("0");
    //                    employeeExist = false;
    //                    PagingResponseDTO responseDTO = new PagingResponseDTO();
    //                    responseDTO.setResultList(displayList);
    //                    responseDTO.setTotalListSize(getCurrentListSize());
    //                    this.getPagingBean().updateMyPadgedDataModel(responseDTO);
    //                    break;
    //                }
    //            }
    //        }
    //
    //        if (employeeExist) {
    //
    //            try {
    //                if (getCivilID() == null) {
    //                   // getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE, "missingCivilID"));
    //                    setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "missingCivilID"));
    //                    setShowErrMsg(true);
    //                    return;
    //                }
    //                IEmployeesDTO employeesDTO = null;
    //                try {
    //                    employeesDTO =
    //                            (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getByRealCivilIdAllMinistries(getCivilID());
    //                } catch (Exception re) {
    //                    // TODO: Add catch code
    //                    re.printStackTrace();
    //                }
    //                if (employeesDTO == null) {
    //                    findPersonByCivilIDFromEmployees();
    //                    return;
    //                }
    //
    //                Long checkBankData = checkEmployeeHasBankAccountData(getCivilID());
    //                if (checkBankData == 0) {
    //                    setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "noBankAccountData"));
    //                    return;
    //                }
    //                if (currentDetails == null)
    //                    currentDetails = new ArrayList<IBasicDTO>();
    //                IBasicDTO dto = employeesDTO;
    //                //boolean exist = checkExistData(dto);
    //                //            if (!exist) {
    //                IBasicDTO mdto = this.mapToDetailDTO(dto);
    //                mdto.setStatusFlag(added);
    //                ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
    //                List objList = new ArrayList();
    //                if (getCurrentDetails() != null && getCurrentDetails().size() > 0) {
    //                    objList = getCurrentDetails();
    //                }
    //                objList.add(mdto);
    //                ((EmpDecisionsDTO)mdto).setFelsValue("0");
    //                ((EmpDecisionsDTO)mdto).setDenarValue("0");
    //                setCurrentDetails(objList);
    //                PagingResponseDTO responseDTO = new PagingResponseDTO();
    //                responseDTO.setResultList(getCurrentDisplayDetails());
    //                responseDTO.setTotalListSize(getCurrentListSize());
    //                this.getPagingBean().updateMyPadgedDataModel(responseDTO);
    //                setCivilID(null);
    //                setCivil_exist("");
    //                //            //} else {
    //                //                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE, "employee_added_before"));
    //                //                setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "employee_added_before"));
    //                //            }
    //                try {
    //                    this.cancelSearchAvailable();
    //                } catch (Exception e) {
    //                    e.printStackTrace();
    //                }
    //            } catch (Exception nf) {
    //                nf.printStackTrace();
    //                // search in INF_KWT_CITIZEN
    //                findPersonByCivilIDFromEmployees();
    //            }
    //        }
    //    }


    private int civilExistBefore(Long realCivilId) {
        if (!currentDetails.isEmpty()) {
            for (IBasicDTO dto : currentDetails) {
            Long currentRealCivilId= ((IKwtCitizensResidentsEntityKey)((IEmployeesDTO)((IEmpDecisionsDTO)dto).getEmployeesDTO()).getCitizensResidentsDTO().getCode()).getCivilId();
                if (currentRealCivilId.equals(realCivilId)) {
                    if (dto.getStatusFlag() == null || dto.getStatusFlag().equals(added)) {
                    return 1;// means civil exist 
                    }

                    dto.setStatusFlag(null);
                    ((EmpDecisionsDTO)dto).setFelsValue(felsValue);
                    ((EmpDecisionsDTO)dto).setDenarValue(dinarValue);

                    PagingResponseDTO responseDTO = new PagingResponseDTO();
                    responseDTO.setResultList(getCurrentDisplayDetails());
                    responseDTO.setTotalListSize(getCurrentListSize());
                    this.getPagingBean().updateMyPadgedDataModel(responseDTO);
                    setCivilID(null);
                    setCivil_exist("");

                    try {
                        this.cancelSearchAvailable();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 2; //  means it was deleted and added again
                }
            }
        }
        return 0; // means new Civil
    }

    private int elementExistBefore(Long elmguideCode) {
        if (!currentSalVariedElmGuideDTOList.isEmpty()) {
            for (IBasicDTO dto : currentSalVariedElmGuideDTOList) {
                Long currentElmguideCode = ((ISalElementGuidesEntityKey)dto.getCode()).getElmguideCode();
                if (currentElmguideCode.equals(elmguideCode)) {
                    if (dto.getStatusFlag() == null || dto.getStatusFlag().equals(added)) {
                        return 1; // means exist
                    }

                    dto.setStatusFlag(null);
                    ((ISalElementGuidesDTO)dto).setFelsValue(felsValue);
                    ((ISalElementGuidesDTO)dto).setDenarValue(dinarValue);

                    setCivil_exist("");

                    return 2; // means it was deleted and added again
                }
            }
        }
        return 0; // means new
    }
    
    public void findEmployeeByCivilIDFromEmployees() {
        setElmGuideExist("");
        DecisionCycleMaintainBean decisionMaintainBean =
            (DecisionCycleMaintainBean)evaluateValueBinding("financeDecisionCycleMaintainBean");
        
        if (currentDetails == null) {
            currentDetails = new ArrayList<IBasicDTO>();
        }

        int currentCivilStatus = CIVIL_IS_VALID_EMP;
        IEmployeesDTO employeesDTO = null;
        try {
            employeesDTO = employeeHelper.getHiredAndHavePayrollInfoEmpInMinstry(getCivilID(), null);
            //getHiredAndHavePayrollInfoEmp(getCivilID());
        } catch (EmployeeNotHiredInMinException e) {
            currentCivilStatus = CIVIL_IS_NOT_HIRED_IN_MIN;
            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "CIVIL_IS_NOT_HIRED_IN_MIN"));
            return;
        } catch (EmployeeNotHiredException e) {
            currentCivilStatus = CIVIL_IS_PERSON;
        } catch (EmployeePayrollInfoNotExistException e) {
            currentCivilStatus = CIVIL_IS_HAS_NO_PAYROLL_INFO;
            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "CIVIL_IS_HAS_NO_PAYROLL_INFO"));
            return;
        } catch (EmployeeCivilIdNotExistException e) {
            currentCivilStatus = CIVIL_IS_PERSON;
            //            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "civil_not_found_center"));
            //            return ;
        } catch (Exception e) {
            currentCivilStatus = CIVIL_NOT_EXIST;
            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "civil_not_found_center"));
            return;
        }
        if (currentCivilStatus == CIVIL_IS_VALID_EMP) {
            Long checkBankData = checkEmployeeHasBankAccountData(getCivilID());
            if (checkBankData == 0) {
                setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "noBankAccountData"));
                currentCivilStatus = CIVIL_IS_EMP_HAS_NO_ACTIVE_BNK_ACCCOUNT;
                return;
            }
            if(decisionMaintainBean.getMultiOrSingleEmps().isEmpty() || decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneElementMultiEmps) ){
                    int status = civilExistBefore(getCivilID());
                    if (status == 1) {
                        setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "employee_added_before"));
                        setShowErrMsg(false);
                        return;
                    }else if (status == 2) {
                        getTotal_Value();
                        return;
                    }
            }else if (!decisionMaintainBean.getMultiOrSingleEmps().isEmpty() && decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneEmpMultiElements)) {
                        int status = elementExistBefore(Long.valueOf(selectedElementGuideCode));
                        if (status == 1) {
                            setElmGuideExist(getSharedUtil().messageLocator(BUNDLE, "element_added_before"));
                            setShowErrMsg(false);
                            return;
                        }else if (status == 2) {
                            getTotal_ValueForOneEmpMultiElements();
                        return;
                    }
            }
            
            IBasicDTO mdto = this.mapToDetailDTO(employeesDTO);
            mdto.setStatusFlag(added);
            ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
            ((EmpDecisionsDTO)mdto).setFelsValue(felsValue);
            ((EmpDecisionsDTO)mdto).setDenarValue(dinarValue);
            empFullName = ((IKwtCitizensResidentsDTO )employeesDTO.getCitizensResidentsDTO()).getFullName();
//            currentDetails.add(mdto);
            if (currentSalVariedElmGuideDTOList != null) {

                if (!decisionMaintainBean.getMultiOrSingleEmps().isEmpty() &&
                    decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneElementMultiEmps)) {
                    
                    currentSalVariedElmGuideDTOList.clear();
                   
                }else if (!decisionMaintainBean.getMultiOrSingleEmps().isEmpty() &&
                    decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneEmpMultiElements)) {
                    
                    currentDetails.clear();
                   
                }
                ISalElementGuidesEntityKey ek =
                    SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.valueOf(selectedElementGuideCode));
                ISalElementGuidesDTO salDto = new SalElementGuidesDTO();
                salDto.setCode(ek);
                if((dinarValue != null && !dinarValue.isEmpty() )||( felsValue != null && !felsValue.isEmpty())){
                    salDto.setValue(calcValue(dinarValue, felsValue));
                    salDto.setDenarValue(dinarValue);
                    salDto.setFelsValue(felsValue);
                }
                salDto.setName(selectedElementGuideName);
                salDto.setStatusFlag(added);
                currentSalVariedElmGuideDTOList.add(salDto);
                if (!decisionMaintainBean.getMultiOrSingleEmps().isEmpty() &&
                    decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneEmpMultiElements)) {
                    currentDetails.clear();
                } 
                ((EmpDecisionsDTO)mdto).setElementGuidesDTO(salDto);
                    currentDetails.add(mdto);
            }
            PagingResponseDTO responseDTO = new PagingResponseDTO();
            responseDTO.setResultList(getCurrentDisplayDetails());
            responseDTO.setTotalListSize(getCurrentListSize());
            //  int currentPIndex = (getCurrentListSize() / 14) + 1;
            //  setCurrentPageIndex(currentPIndex);
            // repositionPage(currentPIndex);
            this.getPagingBean().updateMyPadgedDataModel(responseDTO);

            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().clear();
                getHighlightedDTOS().add(mdto);
            }

            String civilCode = employeesDTO.getCitizensResidentsDTO().getCode().getKey();

            try {
                getPageIndex(civilCode, getCurrentDisplayDetails());
            } catch (DataBaseException dbe) {
                // TODO: Add catch code
                dbe.printStackTrace();
            }
            setCivil_exist("");
            try {
                this.cancelSearchAvailable();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // return;
            if (decisionMaintainBean.getMultiOrSingleEmps().isEmpty() ||
                decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneElementMultiEmps)) {
                    setCivilID(null);
                    felsValue = "";
                    dinarValue = "";
            }

        }
        if (currentCivilStatus == CIVIL_IS_PERSON) {
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = null;
            try {
                kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenCodeName(getCivilID());
                empFullName = kwtCitizensResidentsDTO.getFullName();
                employeesDTO = EmpDTOFactory.createEmployeesDTO();
                employeesDTO.setCitizensResidentsDTO(kwtCitizensResidentsDTO);
                employeesDTO.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId()));
                IHireStatusDTO hireStatusDTO = EmpDTOFactory.createHireStatusDTO();
                hireStatusDTO.setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.NOT_EMPLOYEE));
                employeesDTO.setHireStatusDTO(hireStatusDTO);
                try {
                    IEmployeesDTO empDTO= EmpClientFactory.getEmployeesClient().getByRealCivilIdBasicDataAndMinistryName(getCivilID(),null);
                    if (empDTO != null && empDTO.getWorkCenterDTO() != null)
                        employeesDTO.setWorkCenterDTO(empDTO.getWorkCenterDTO());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                if(decisionMaintainBean.getMultiOrSingleEmps().isEmpty() || decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneElementMultiEmps) ){
                        int status = civilExistBefore(getCivilID());
                        if (status == 1) {
                            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "employee_added_before"));
                            setShowErrMsg(false);
                            return;
                        }else if (status == 2) {
                            getTotal_Value();
                            return;
                        }
                }else if (!decisionMaintainBean.getMultiOrSingleEmps().isEmpty() && decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneEmpMultiElements)) {
                            int status = elementExistBefore(Long.valueOf(selectedElementGuideCode));
                            if (status == 1) {
                                setElmGuideExist(getSharedUtil().messageLocator(BUNDLE, "element_added_before"));
                                setShowErrMsg(false);
                                return;
                            }else if (status == 2) {
                            getTotal_ValueForOneEmpMultiElements();
                            return;
                        }
                }
                IBasicDTO mdto = this.mapToDetailDTO(employeesDTO);
                ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
                 mdto.setStatusFlag(added);
                ((EmpDecisionsDTO)mdto).setFelsValue(felsValue);
                ((EmpDecisionsDTO)mdto).setDenarValue(dinarValue);
                if (currentSalVariedElmGuideDTOList != null) {

                  

                    if (!decisionMaintainBean.getMultiOrSingleEmps().isEmpty() &&
                        decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneElementMultiEmps)) {
                        
                        currentSalVariedElmGuideDTOList.clear();
                       
                    }else if (!decisionMaintainBean.getMultiOrSingleEmps().isEmpty() &&
                        decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneEmpMultiElements)) {
                        
                        currentDetails.clear();
                       
                    }
                    ISalElementGuidesEntityKey ek =
                        SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.valueOf(selectedElementGuideCode));
                    ISalElementGuidesDTO salDto = new SalElementGuidesDTO();
                    salDto.setCode(ek);
                    if((dinarValue != null && !dinarValue.isEmpty() )||( felsValue != null && !felsValue.isEmpty())){
                        salDto.setValue(calcValue(dinarValue, felsValue));
                        salDto.setDenarValue(dinarValue);
                        salDto.setFelsValue(felsValue);
                    }
                    salDto.setName(selectedElementGuideName);
                    currentSalVariedElmGuideDTOList.add(salDto);
                    if (!decisionMaintainBean.getMultiOrSingleEmps().isEmpty() &&
                        decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneEmpMultiElements)) {
                        currentDetails.clear();
                    } 
                        currentDetails.add(mdto);
                }
                PagingResponseDTO responseDTO = new PagingResponseDTO();
                responseDTO.setResultList(getCurrentDisplayDetails());
                responseDTO.setTotalListSize(getCurrentListSize());
                //int currentPIndex = (getCurrentListSize() / 14 )+ 1;
                //repositionPage(currentPIndex);
                //setRepositionTable(true);
                //setCurrentPageIndex(currentPIndex);
                this.getPagingBean().updateMyPadgedDataModel(responseDTO);
                this.restoreDetailsTablePosition(this.getAvailableDataTable(), availableDetails);
                //restoreTablePosition();
                this.resetSelection();
                if (getHighlightedDTOS() != null) {
                    getHighlightedDTOS().clear();
                    getHighlightedDTOS().add(mdto);
                }

                String civilCode = getCivilID().toString();//employeesDTO.getCitizensResidentsDTO().getCode().getKey();
                try {
                    getPageIndex(civilCode, getCurrentDisplayDetails());
                } catch (DataBaseException dbe) {
                    // TODO: Add catch code
                    dbe.printStackTrace();
                }
              
                try {
                    this.cancelSearchAvailable();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                currentCivilStatus = CIVIL_NOT_EXIST;
                setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "civil_not_found_center"));
                return;
            }
        }
        if (!decisionMaintainBean.getMultiOrSingleEmps().isEmpty() &&
            decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneEmpMultiElements)) {
            selectedElementGuideCode = "";
            selectedElementGuideName = "";
        } else {
            setCivilID(null);
            setCivil_exist("");
        }

        felsValue = "";
        dinarValue = "";
        if (decisionMaintainBean.getMultiOrSingleEmps().isEmpty() ||
            decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneElementMultiEmps)) {

            getTotal_Value();
        } else if (!decisionMaintainBean.getMultiOrSingleEmps().isEmpty() &&
                   decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneEmpMultiElements)) {

            getTotal_ValueForOneEmpMultiElements();

        }

    }
    public void getEmployeeByCivilIDFromEmployees() {

        int currentCivilStatus = CIVIL_IS_VALID_EMP;
        IEmployeesDTO employeesDTO = null;
        civilExist=false;
        try {
            employeesDTO = employeeHelper.getHiredAndHavePayrollInfoEmpInMinstry(getCivilID(), null);            
            //getHiredAndHavePayrollInfoEmp(getCivilID());
        } catch (EmployeeNotHiredInMinException e) {
            currentCivilStatus = CIVIL_IS_NOT_HIRED_IN_MIN;
            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "CIVIL_IS_NOT_HIRED_IN_MIN"));
            return;
        } catch (EmployeeNotHiredException e) {
            currentCivilStatus = CIVIL_IS_PERSON;
        } catch (EmployeePayrollInfoNotExistException e) {
            currentCivilStatus = CIVIL_IS_HAS_NO_PAYROLL_INFO;
            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "CIVIL_IS_HAS_NO_PAYROLL_INFO"));
            return;
        } catch (EmployeeCivilIdNotExistException e) {
            currentCivilStatus = CIVIL_IS_PERSON;
            //            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "civil_not_found_center"));
            //            return ;
        } catch (Exception e) {
            currentCivilStatus = CIVIL_NOT_EXIST;
            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "civil_not_found_center"));
            return;
        }
        if (currentCivilStatus == CIVIL_IS_VALID_EMP) {
            Long checkBankData = checkEmployeeHasBankAccountData(getCivilID());
            if (checkBankData == 0) {
                setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "noBankAccountData"));
                currentCivilStatus = CIVIL_IS_EMP_HAS_NO_ACTIVE_BNK_ACCCOUNT;
                return;
            }
            empFullName = ((IKwtCitizensResidentsDTO )employeesDTO.getCitizensResidentsDTO()).getFullName();
            civilExist=true;
           
            setCivil_exist("");
            try {
                this.cancelSearchAvailable();
            } catch (Exception e) {
                e.printStackTrace();
            }
           

        }
        if (currentCivilStatus == CIVIL_IS_PERSON) {
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = null;
            try {
                kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenCodeName(getCivilID());
                empFullName = kwtCitizensResidentsDTO.getFullName();
                setCivil_exist("");
                civilExist=true;
                employeesDTO = EmpDTOFactory.createEmployeesDTO();
                employeesDTO.setCitizensResidentsDTO(kwtCitizensResidentsDTO);
                employeesDTO.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId()));
                IHireStatusDTO hireStatusDTO = EmpDTOFactory.createHireStatusDTO();
                hireStatusDTO.setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.NOT_EMPLOYEE));
                employeesDTO.setHireStatusDTO(hireStatusDTO);
                try {
                    IEmployeesDTO empDTO= EmpClientFactory.getEmployeesClient().getByRealCivilIdBasicDataAndMinistryName(getCivilID(),null);
                    if (empDTO != null && empDTO.getWorkCenterDTO() != null)
                        employeesDTO.setWorkCenterDTO(empDTO.getWorkCenterDTO());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                try {
                    this.cancelSearchAvailable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            } catch (Exception e) {
                currentCivilStatus = CIVIL_NOT_EXIST;
                setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "civil_not_found_center"));
                return;
            }
            
        }
            
        IBasicDTO mdto = this.mapToDetailDTO(employeesDTO);
        ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
        currentDetails.clear();

        currentDetails.add(mdto);
    }
    //    public boolean checkExistData(IBasicDTO dto) {
    //        boolean exist = false;
    //        DecisionCycleMaintainBean decisionBean =
    //            (DecisionCycleMaintainBean)BeansUtil.getValue("financeDecisionCycleMaintainBean");
    //        Long civil = new Long(dto.getCode().getKey());
    //        try {
    //            exist =
    //                    RegClientFactory.getEmpDecisionsClient().checkEixstEmpInDecision(decisionBean.getPageDTO().getCode(),
    //                                                                                     civil);
    //        } catch (SharedApplicationException e) {
    //            exist = false;
    //            e.printStackTrace();
    //        } catch (DataBaseException e) {
    //            exist = false;
    //            e.printStackTrace();
    //        }
    //        return exist;
    //    }

    //    public void findPersonByCivilIDFromEmployees() {
    //        try {
    //            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
    //                (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getById(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(getCivilID()));
    //            // check if employee has BankAccountData
    //            //            Long checkBankData = checkEmployeeHasBankAccountData(getCivilID());
    //            //            if(checkBankData==0){
    //            //                setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "noBankAccountData"));
    //            //                return;
    //            //            }
    //            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
    //            employeesDTO.setCitizensResidentsDTO(kwtCitizensResidentsDTO);
    //            employeesDTO.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId()));
    //            IHireStatusDTO hireStatusDTO = EmpDTOFactory.createHireStatusDTO();
    //            hireStatusDTO.setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.NOT_EMPLOYEE));
    //            employeesDTO.setHireStatusDTO(hireStatusDTO);
    //            if (currentDetails == null)
    //                currentDetails = new ArrayList<IBasicDTO>();
    //            IBasicDTO dto = employeesDTO;
    //            boolean exist = checkExistData(dto);
    //            if (!exist) {
    //                IBasicDTO mdto = this.mapToDetailDTO(dto);
    //                mdto.setStatusFlag(added);
    //                ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
    //                List objList = new ArrayList();
    //                if (getCurrentDetails() != null && getCurrentDetails().size() > 0) {
    //                    objList = getCurrentDetails();
    //                }
    //                ((EmpDecisionsDTO)mdto).setFelsValue("0");
    //                ((EmpDecisionsDTO)mdto).setDenarValue("0");
    //                objList.add(mdto);
    //                setCurrentDetails(objList);
    //                PagingResponseDTO responseDTO = new PagingResponseDTO();
    //                responseDTO.setResultList(getCurrentDisplayDetails());
    //                responseDTO.setTotalListSize(getCurrentListSize());
    //                this.getPagingBean().updateMyPadgedDataModel(responseDTO);
    //            } else {
    //                //getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE, "employee_added_before"));
    //                setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "employee_added_before"));
    //            }
    //            this.restoreDetailsTablePosition(this.getAvailableDataTable(), availableDetails);
    //            this.resetSelection();
    //            setCivilID(null);
    //            setCivil_exist("");
    //            try {
    //                this.cancelSearchAvailable();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        } catch (ItemNotFoundException e) {
    //            //setCivilID(null);
    //            //getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE, "civil_not_found_center"));
    //            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "civil_not_found_center"));
    //            e.printStackTrace();
    //        } catch (SharedApplicationException e) {
    //            e.printStackTrace();
    //            // TODO
    //        } catch (DataBaseException e) {
    //            e.printStackTrace();
    //            setCivil_exist(getSharedUtil().messageLocator(BUNDLE, "civil_not_found_center"));
    //            // TODO
    //        }
    //    }

    /*
 * check if employee has BankAccount Data
 */

    public Long checkEmployeeHasBankAccountData(Long civilId) {
        Long exist = 0L;
        try {
            exist = RegClientFactory.getEmpDecisionsClient().checkEmployeeHasBankAccountData(civilId);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public void returnMethodAction1() {
        if (getEmpListOfValues().getSelectedDTOS() != null && getEmpListOfValues().getSelectedDTOS().get(0) != null &&
            getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
            List<IBasicDTO> selectedAvailableDetails = getEmpListOfValues().getSelectedDTOS();
            if (currentDetails == null)
                currentDetails = new ArrayList<IBasicDTO>();
            DecisionEmployeesModel decisionEmployeesModelSessionBean =
                (DecisionEmployeesModel)evaluateValueBinding("financedecisionEmployeesModel");
            if (selectedAvailableDetails != null)
                for (int i = 0; i < selectedAvailableDetails.size(); i++) {
                    IBasicDTO dto = selectedAvailableDetails.get(i);
                    decisionEmployeesModelSessionBean.setExist(decisionEmployeesModelSessionBean.checkExistData(dto));
                    if (!decisionEmployeesModelSessionBean.isExist()) {
                        IBasicDTO mdto = this.mapToDetailDTO(dto);
                        mdto.setStatusFlag(added);
                        ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
                        decisionEmployeesModelSessionBean.addNewElement(mdto);
                        i--;
                    }
                }
            this.resetSelection();
            try {
                this.cancelSearchAvailable();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setCivilID(null);
        }
    }


    //    public void returnMethodAction() {
    //        if (employeeSearchBean.getSelectedDTOS() != null && employeeSearchBean.getSelectedDTOS().get(0) != null &&
    //            employeeSearchBean.getSelectedDTOS().get(0).getCode() != null) {
    //            List<IBasicDTO> selectedAvailableDetails = employeeSearchBean.getSelectedDTOS();
    //            if (currentDetails == null)
    //                currentDetails = new ArrayList<IBasicDTO>();
    //            DecisionEmployeesModel decisionEmployeesModelSessionBean =
    //                (DecisionEmployeesModel)evaluateValueBinding("financedecisionEmployeesModel");
    //            if (selectedAvailableDetails != null)
    //                for (int i = 0; i < selectedAvailableDetails.size(); i++) {
    //                    IBasicDTO dto = selectedAvailableDetails.get(i);
    //                    decisionEmployeesModelSessionBean.setExist(decisionEmployeesModelSessionBean.checkExistData(dto));
    //                    if (!decisionEmployeesModelSessionBean.isExist()) {
    //                        IBasicDTO mdto = this.mapToDetailDTO(dto);
    //                        mdto.setStatusFlag(added);
    //                        ((IEmpDecisionsDTO)mdto).setBooleanInformEmpFlag(false);
    //                        decisionEmployeesModelSessionBean.addNewElement(mdto);
    //                        i--;
    //                    }
    //                }
    //          this.resetSelection();
    //            try {
    //                this.cancelSearchAvailable();
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //            setCivilID(null);
    //        }
    //    }

    public void removeCurrentFromAvailable() {
        System.out.println("removing current");
        if (currentDetails != null && availableDetails != null)
            for (int j = 0; j < currentDetails.size(); j++) {
                IBasicDTO dto = currentDetails.get(j);
                IBasicDTO codeNameDTO = this.mapToCodeNameDTO(dto);
                boolean exist = false;
                for (int i = 0; i < availableDetails.size(); i++) {
                    IBasicDTO availableDTO = availableDetails.get(i);
                    exist = true;
                    availableDetails.remove(availableDTO);
                }
                if (!exist && dto.getStatusFlag() != null && dto.getStatusFlag().longValue() == deleted.longValue()) {
                    if (isSearchMode() == true && containSubString(codeNameDTO.getName()))
                        availableDetails.add(codeNameDTO);
                    else if (isSearchMode() == false)
                        availableDetails.add(codeNameDTO);
                }
            }
    }

    public void getNextPage(ActionEvent event) {
        DecisionEmployeesModel decisionEmployeesModelSessionBean =
            (DecisionEmployeesModel)evaluateValueBinding("financedecisionEmployeesModel");
        setCurrentPageIndex(decisionEmployeesModelSessionBean.determineNoOfPaqe());
        changePageIndex(event);
        decisionEmployeesModelSessionBean.navigateToNextPage(new Long(getCurrentPageIndex()));
    }


    public int getCurrentListSize() {
        DecisionCycleMaintainBean decisionMaintainBean =
            (DecisionCycleMaintainBean)BeansUtil.getValue("financeDecisionCycleMaintainBean");
        if (!decisionMaintainBean.getMultiOrSingleEmps().isEmpty() &&
            decisionMaintainBean.getMultiOrSingleEmps().equals(decisionMaintainBean.OneEmpMultiElements)) {
            if (currentSalVariedElmGuideDTOList != null && currentSalVariedElmGuideDTOList.size() != 0) {
                countOfAllEmpDecision = currentDisplayedSalVariedElmGuideDTOList.size();
            } else {
                countOfAllEmpDecision = 0;
            }
        } else {
            if (getCurrentDetails() != null && getCurrentDetails().size() != 0) {
                countOfAllEmpDecision = getCurrentDisplayDetails().size();
            } else {
                countOfAllEmpDecision = 0;
            }
        }
        return countOfAllEmpDecision;
    }

    //    public void setEmpEmployeeSearchDTO(IEmpEmployeeSearchDTO empEmployeeSearchDTO) {
    //        this.empEmployeeSearchDTO = empEmployeeSearchDTO;
    //    }
    //
    //    public IEmpEmployeeSearchDTO getEmpEmployeeSearchDTO() {
    //        return empEmployeeSearchDTO;
    //    }

    public void setAddedEmpSuccessfully(boolean addedEmpSuccessfully) {
        this.addedEmpSuccessfully = addedEmpSuccessfully;
    }

    public boolean isAddedEmpSuccessfully() {
        return addedEmpSuccessfully;
    }

    public void setCountOfAllEmpDecision(int countOfAllEmpDecision) {
        this.countOfAllEmpDecision = countOfAllEmpDecision;
    }


    public int getCountOfAllEmpDecision() {
        return countOfAllEmpDecision;
    }


    public void setCancelDecisionMode(boolean cancelDecisionMode) {
        this.cancelDecisionMode = cancelDecisionMode;
    }

    public boolean isCancelDecisionMode() {
        return cancelDecisionMode;
    }


    //    public void setEmployeeSearchBean(EmployeeSearchBean employeeSearchBean) {
    //        this.employeeSearchBean = employeeSearchBean;
    //    }
    //
    //    public EmployeeSearchBean getEmployeeSearchBean() {
    //        return employeeSearchBean;
    //    }

    public void setElementGuideList(List<IBasicDTO> elementGuideList) {
        this.elementGuideList = elementGuideList;
    }

    public List<IBasicDTO> getElementGuideList() {
        return elementGuideList;
    }


    public void validateVal(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String val = (String)value;
        if (val == null || val.equals("")) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Value does not exists");
            message.setSummary("Insert Value");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public void enableSecondCombo() {
        if (!ISystemConstant.VIRTUAL_VALUE.equals(getSelectedElementCode())) {
            setEnableElementGuideCombo(false);
            loadElementTypeCode();
        } else {
            setEnableElementGuideCombo(true);
        }
    }

    public void showSearchForEmployeeDiv() {
        EmployeeListOfValues empListOfValuesBean = ((EmployeeListOfValues)getEmpListOfValues());
        empListOfValuesBean.setUseCustomSearch(true);
        empListOfValuesBean.setSearchMethod(BEAN_NAME + ".searchEmpCandidates");
        empListOfValuesBean.setReturnMethodName(BEAN_NAME + ".returnMethodAction");
        if (!isSearchMode()) {
            empListOfValuesBean.resetData();
        }
        empListOfValuesBean.setHighlightedDTOS(null);
        empListOfValuesBean.getOkCommandButton().setReRender("dataT_data_panel,OperationBar,paging_panel");
    }

    public void returnMethodAction() {
        getHighlightedDTOS().clear();
        if (getEmpListOfValues().getSelectedDTOS() != null && getEmpListOfValues().getSelectedDTOS().get(0) != null) {
            //             IEmployeesDTO employeesDTO = (IEmployeesDTO)getEmpListOfValues().getSelectedDTOS().get(0);
            IKwtCitizensResidentsDTO residentsDTO =
                (IKwtCitizensResidentsDTO)((IEmployeesDTO)getEmpListOfValues().getSelectedDTOS().get(0)).getCitizensResidentsDTO();
            Long empCandidatesCivilID = ((IKwtCitizensResidentsEntityKey)residentsDTO.getCode()).getCivilId();
            //((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId();
            if (getHighlightedDTOS() != null) {
                try {
                    int i = 0;
                    for (IBasicDTO dto1 : getCurrentDisplayDetails()) {
                        IEmpDecisionsDTO dto = (IEmpDecisionsDTO)dto1;
                        residentsDTO =
                                (IKwtCitizensResidentsDTO)((IEmployeesDTO)dto.getEmployeesDTO()).getCitizensResidentsDTO();

                        Long civilID = ((IKwtCitizensResidentsEntityKey)residentsDTO.getCode()).getCivilId();
                        //((IEmployeesEntityKey)dto.getEmployeesDTO().getCode()).getCivilId();
                        if (civilID.equals(empCandidatesCivilID)) {
                            setSearchMode(true);
                            highlighDataTableCustom(dto);
                            //setCurrentSortingRowIndex(i);
                            return;
                        }
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isRowHighlight(IBasicDTO currentRowObject) {

        for (IBasicDTO dt : getHighlightedDTOS()) {
            String civilCode=((IEmployeesDTO)((IEmpDecisionsDTO)dt).getEmployeesDTO()).getCitizensResidentsDTO().getCode().getKey();
            String highlightCivilCode=((IEmployeesDTO)((IEmpDecisionsDTO)currentRowObject).getEmployeesDTO()).getCitizensResidentsDTO().getCode().getKey();
            if (civilCode != null && highlightCivilCode != null &&
                highlightCivilCode.equals(civilCode)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSelected() {
        if (getCurrentDataTable() != null && getHighlightedDTOS() != null) {
            IBasicDTO dto = (IBasicDTO)getCurrentDataTable().getRowData();
            return isRowHighlight(dto);
        }
        return false;

    }

    public void cancelSearch() throws DataBaseException {
        setSearchMode(false);
        EmployeeListOfValues empListOfValuesBean = ((EmployeeListOfValues)getEmpListOfValues());
        empListOfValuesBean.resetData();
        getHighlightedDTOS().clear();
    }

    public String searchEmpCandidates(Object searchType, Object searchQuery) {
        if (searchQuery != null && !searchQuery.toString().equals("") && searchType != null &&
            !searchType.equals("")) {
            List<IBasicDTO> searchResult = new ArrayList<IBasicDTO>();
            try {
                getEmpListOfValues().setSearchMode(true);
                if (searchType.toString().equals(SEARCH_BY_CODE)) {
                    searchResult = searchByCode(searchQuery.toString());
                } else if (searchType.toString().equals(SEARCH_BY_NAME)) {
                    searchResult = searchByName(searchQuery.toString());
                }
            } catch (DataBaseException e) {
                getEmpListOfValues().setMyTableData(new ArrayList());
                getEmpListOfValues().setSearchMode(false);
            }
            getEmpListOfValues().setMyTableData(searchResult);
        } else {
            getEmpListOfValues().setSearchMode(false);
        }

        getEmpListOfValues().repositionPage(0);
        getEmpListOfValues().setSelectedDTOS(new ArrayList<IBasicDTO>(0));
        return "";
    }

    private List<IBasicDTO> searchByCode(String searchQuery) throws DataBaseException {
        List<IBasicDTO> searchResult = new ArrayList<IBasicDTO>();
        for (IBasicDTO dto1 : getCurrentDisplayDetails()) {
            IEmpDecisionsDTO dto = (IEmpDecisionsDTO)dto1;
            IKwtCitizensResidentsDTO residentsDTO =
                (IKwtCitizensResidentsDTO)((IEmployeesDTO)dto.getEmployeesDTO()).getCitizensResidentsDTO();

            IEmployeesDTO employeesDTO = (IEmployeesDTO)dto.getEmployeesDTO();
            String realcivilID = residentsDTO.getCode().getKey().toString();
            //employeesDTO.getRealCivilId() !=null ? String.valueOf(employeesDTO.getRealCivilId()):null;

            if (realcivilID == null) {
                continue;
            }
            if (searchQuery.equals(realcivilID)) {
                searchResult.add(employeesDTO); //dto);
                return searchResult;
            }
        }
        return searchResult;
    }

    private List<IBasicDTO> searchByName(String searchQuery) throws DataBaseException {
        List<IBasicDTO> searchResult = new ArrayList<IBasicDTO>();

        for (IBasicDTO dto1 : getCurrentDisplayDetails()) {

            IEmpDecisionsDTO dto = (IEmpDecisionsDTO)dto1;
            //            IKwtCitizensResidentsDTO residentsDTO =
            //                (IKwtCitizensResidentsDTO)((IEmployeesDTO)dto.getEmployeesDTO()).getCitizensResidentsDTO();
            IEmployeesDTO employeesDTO = (IEmployeesDTO)dto.getEmployeesDTO();
            IKwtCitizensResidentsDTO residentsDTO = (IKwtCitizensResidentsDTO)(employeesDTO).getCitizensResidentsDTO();
            String name = null;
            //             BasicDataBean basicDataBean = (BasicDataBean)evaluateValueBinding("allowanceAdd");
            //             if (!basicDataBean.getEditMode()) {
            //                 name = residentsDTO.getName();
            //             } else {
            name = residentsDTO.getFullName();
            //             }

            if (name == null) {
                continue;
            }
            String pureSearchQuery = searchQuery.replaceAll(SEARCH_SPECIAL_CHARACTER, "").trim();
            Pattern pattern = UtilBean.getSimilerCharRegexPattern(pureSearchQuery);
            Matcher fit = pattern.matcher(name);
            if (fit.matches()) {
                searchResult.add(employeesDTO); //dto);
            }
            //            String pureSearchQuery =
            //                searchQuery.replaceAll(SEARCH_SPECIAL_CHARACTER, "").trim();
            //            if ((searchQuery.startsWith(SEARCH_SPECIAL_CHARACTER) &&
            //                 searchQuery.endsWith(SEARCH_SPECIAL_CHARACTER) &&
            //                 name.contains(pureSearchQuery)) ||
            //                (searchQuery.startsWith(SEARCH_SPECIAL_CHARACTER) &&
            //                 name.endsWith(pureSearchQuery)) ||
            //                (searchQuery.endsWith(SEARCH_SPECIAL_CHARACTER) &&
            //                 name.startsWith(pureSearchQuery)) ||
            //                searchQuery.equals(name)) {
            //
            //                searchResult.add(dto);
            //            }
        }
        return searchResult;
    }


    public void setSelectedElementCode(String selectedElementCode) {
        this.selectedElementCode = selectedElementCode;
    }

    public String getSelectedElementCode() {
        return selectedElementCode;
    }

    public void setValueNumber(Long valueNumber) {
        this.valueNumber = valueNumber;
    }

    public Long getValueNumber() {
        return valueNumber;
    }

    public void setBsnPagingResponseDTO(com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO) {
        this.bsnPagingResponseDTO = bsnPagingResponseDTO;
    }

    public com.beshara.base.paging.impl.PagingResponseDTO getBsnPagingResponseDTO() {
        return bsnPagingResponseDTO;
    }

    public void setCivil_exist(String civil_exist) {
        this.civil_exist = civil_exist;
    }

    public String getCivil_exist() {
        return civil_exist;
    }


    public void setDeleteBtnDisabled(boolean deleteBtnEnabled) {
        this.deleteBtnDisabled = deleteBtnEnabled;
    }

    public boolean isDeleteBtnDisabled() {
        return (this.getSelectedDTOS() == null || this.getSelectedDTOS().size() == 0);
    }


    public void setElementGuideTypeList(List<IBasicDTO> elementGuideTypeList) {
        this.elementGuideTypeList = elementGuideTypeList;
    }

    public List<IBasicDTO> getElementGuideTypeList() {
        return elementGuideTypeList;
    }

    public void setElementGuideCode(String elementGuidecode) {
        this.elementGuideCode = elementGuidecode;
    }

    public String getElementGuideCode() {
        return elementGuideCode;
    }

    public void setSelectedElementGuideCode(String selectedElementGuideCode) {
        this.selectedElementGuideCode = selectedElementGuideCode;
    }

    public String getSelectedElementGuideCode() {
        return selectedElementGuideCode;
    }

    public void setEnableElementGuideCombo(boolean enableElementGuideCombo) {
        this.enableElementGuideCombo = enableElementGuideCombo;
    }

    public boolean isEnableElementGuideCombo() {
        return enableElementGuideCombo;
    }

    public void setShowElementGuideErrMsg(boolean showElementGuideErrMsg) {
        this.showElementGuideErrMsg = showElementGuideErrMsg;
    }

    public boolean isShowElementGuideErrMsg() {
        return showElementGuideErrMsg;
    }

    public boolean isDisapleButton() {
        if (getSelectedCurrentDetails() != null && getSelectedCurrentDetails().size() != 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setNumberOfRecordsDisplayed(int numberOfRecordsDisplayed) {
        this.numberOfRecordsDisplayed = numberOfRecordsDisplayed;
    }

    public int getNumberOfRecordsDisplayed() {
        numberOfRecordsDisplayed = 0;
        List currentDispDetails = this.getCurrentDisplayDetails();
        if (currentDispDetails != null && currentDispDetails.size() > 0) {
            numberOfRecordsDisplayed = currentDispDetails.size();
        }
        return numberOfRecordsDisplayed;
    }

    public void setSalEmpMonSalaries(List<IBasicDTO> salEmpMonSalaries) {
        this.salEmpMonSalaries = salEmpMonSalaries;
    }

    public List<IBasicDTO> getSalEmpMonSalaries() {
        return salEmpMonSalaries;
    }

    public void setTempResponse(PagingResponseDTO tempResponse) {
        this.tempResponse = tempResponse;
    }

    public PagingResponseDTO getTempResponse() {
        return tempResponse;
    }

    public void setTreedivBean(TreeDivBean treedivBean) {
        this.treedivBean = treedivBean;
    }

    public TreeDivBean getTreedivBean() {
        return treedivBean;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBenefitRewardCodeExists(boolean benefitRewardCodeExists) {
        this.benefitRewardCodeExists = benefitRewardCodeExists;
    }

    public boolean isBenefitRewardCodeExists() {
        return benefitRewardCodeExists;
    }

    public void setBenefitRewardCodeWrong(boolean benefitRewardCodeWrong) {
        this.benefitRewardCodeWrong = benefitRewardCodeWrong;
    }

    public boolean isBenefitRewardCodeWrong() {
        return benefitRewardCodeWrong;
    }

    public void setElemGuideCodeParentMinJoinFlag(boolean elemGuideCodeParentMinJoinFlag) {
        this.elemGuideCodeParentMinJoinFlag = elemGuideCodeParentMinJoinFlag;
    }

    public boolean isElemGuideCodeParentMinJoinFlag() {
        return elemGuideCodeParentMinJoinFlag;
    }

    public void setBenefitConst(String benefitConst) {
        this.benefitConst = benefitConst;
    }

    public String getBenefitConst() {
        return benefitConst;
    }

    public void setRewardConst(String rewardConst) {
        this.rewardConst = rewardConst;
    }

    public String getRewardConst() {
        return rewardConst;
    }

    public void setSelectedElementGuideName(String selectedElementGuideName) {
        this.selectedElementGuideName = selectedElementGuideName;
    }

    public String getSelectedElementGuideName() {
        return selectedElementGuideName;
    }

    public void setValidElmguide(boolean validElmguide) {
        this.validElmguide = validElmguide;
    }

    public boolean isValidElmguide() {
        return validElmguide;
    }



    public void highlighDataTableCustom(IBasicDTO dto) {
        if (dto != null && dto.getCode() != null) {
            String highlightCivilCode=((IEmployeesDTO)((IEmpDecisionsDTO)dto).getEmployeesDTO()).getCitizensResidentsDTO().getCode().getKey();
            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().clear();
                getHighlightedDTOS().add(dto);
            }
            //            if (isUsingPaging()) {
            //                setUpdateMyPagedDataModel(true);
            //                setRepositionTable(true);
            //                PagingRequestDTO requestDTO = getPagingRequestDTO();
            //                if (requestDTO == null) {
            //                    requestDTO = new PagingRequestDTO();
            //                }
            //                requestDTO.setRepositionKey(dto.getCode().getKey());
            //                setPagingRequestDTO(requestDTO);
            //            } else {
            try {
                getPageIndex(highlightCivilCode, getCurrentDisplayDetails());

                //                int totalListSize = 0;
                //                int firstRow = 0;
                //
                //                if (isUsingPaging()) {
                //                    totalListSize = getPagingBean().getTotalListSize();
                //                } else {
                //                    totalListSize = getCurrentDisplayDetails().size();
                //                }
                //
                //                if (totalListSize > 0 && getCurrentDataTable() != null) {
                //                    int rows = 11; //getSharedUtil().getNoOfTableRows();
                //
                //                    //                    int noOfPages = getPagingBean().getPagesCount();
                //                    int index = getItemIndex(highlightCivilCode, getCurrentDisplayDetails());
                //                    //                    currentPageIndex = ++index / rows;
                //                    //
                //                    //
                //                    //                    if (index % rows > 0) {
                //                    //                        ++currentPageIndex;
                //                    //                    }
                //                    if (totalListSize > 0) {
                //
                //                        int tempPagesCount = index / rows;
                //
                //                        if (index % rows > 0) {
                //                            ++tempPagesCount;
                //                        }
                //
                //                        firstRow = (tempPagesCount - 1) * rows;
                //
                //                    }
                //                    getCurrentDataTable().setFirst(firstRow);
                //                    //getCurrentDataTable().setRowIndex(firstRow + (index % rows) - 1 );
                //                }
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //            }
        }
    }


    public void getPageIndex(String key, List totalList) throws DataBaseException {

        int rows = getSharedUtil().getNoOfTableRows();

        int totalListSize = 0;

        if (isUsingPaging()) {
            totalListSize = getPagingBean().getTotalListSize();
        } else {
            totalListSize = getCurrentDisplayDetails().size();
        }

        if (totalListSize > 0 && getCurrentDataTable() != null) {

            int noOfPages = 0;

            if (isUsingPaging()) {
                noOfPages = getPagingBean().getPagesCount();
            } else {
                noOfPages = ((totalListSize - 2) / rows) + 1;
            }

            int index = 0;

            if (!isUsingPaging()) {
                int curIndex = getCurrentPageIndex();
                if (totalList != null) { // using paging

                    index = getItemIndex(key, totalList);
                    int x = index;
                    curIndex = ++x / rows;

                    if (index % rows > 0) {
                        ++curIndex;
                    }
                    setCurrentPageIndex(curIndex);
                    setOldPageIndex(curIndex);

                } else {
                    index = getItemIndex(key);
                }
            }

            if (isUsingPaging()) {

                int firstRow = 0;

                if (isUsingBsnPaging()) {
                    firstRow = (getCurrentPageIndex() - 1) * rows;
                } else {
                    if (totalListSize > 0) {

                        int tempPagesCount = index / rows;

                        if (index % rows > 0) {
                            ++tempPagesCount;
                        }

                        firstRow = (tempPagesCount - 1) * rows;

                    }
                }

                getCurrentDataTable().setFirst(firstRow);

            } else {

                for (int i = 0; i < noOfPages; i++) {

                    if (index == rows * i) {
                        getCurrentDataTable().setFirst(rows * i);
                    } else if (index == (rows * (i + 1))) {
                        getCurrentDataTable().setFirst(rows * (i + 1));
                    } else if (index > rows * i && index < rows * (i + 1)) {
                        getCurrentDataTable().setFirst(rows * i);
                    }

                }

            }
        }

    }
    public int getItemIndex(String key, List totalList) throws DataBaseException {

        int index = 0;

        for (int b = 0; b < totalList.size(); b++) {

            IEmpDecisionsDTO dto = (IEmpDecisionsDTO)totalList.get(b);
            String currentRealCivilId= ((IEmployeesDTO)dto.getEmployeesDTO()).getCitizensResidentsDTO().getCode().getKey();

            if (currentRealCivilId != null && key != null && currentRealCivilId.equals(key)) {

                index = b;
                break;
            }
        }
        System.out.println(index);
        return index;

    }

    public void setshowErrorMsg1(Boolean showErrorMsg1) {
        this.showErrorMsg1 = showErrorMsg1;
    }

    public Boolean getshowErrorMsg1() {
        return showErrorMsg1;
    }


    public void selectedCurrentAll(ActionEvent event) throws Exception {
        event = null; // unused
        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedCurrentDetails());


            int first = this.getCurrentDataTable().getFirst();

            Integer rowsCountPerPage=(Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());
            //Integer rowsCountPerPage = getCurrentDataTable().getRowCount();
            if (rowsCountPerPage == null) {

                throw new Exception("#{shared_util.noOfTableRows} return null");
            }
            int count = rowsCountPerPage.intValue();
            System.out.println((first + count));
            for (int j = first; j < first + count; j++) {
                System.out.println(j);
                this.getCurrentDataTable().setRowIndex(j);
                if (!this.getCurrentDataTable().isRowAvailable())
                    break;

                IBasicDTO selected = (IBasicDTO)this.getCurrentDataTable().getRowData();
                System.out.println(selected.getCode().getKey());
                if (isCheckAllFlag() == true) {
                    s.add(this.mapToCodeNameDTO(selected));

                }


                if (isCheckAllFlag() == false) {
                    s.remove(this.mapToCodeNameDTO(selected));
                }


            }

            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().addAll(s);


        } catch (Exception e) {

            System.out.println(e.toString());

        }


    }
    public void selectedCurrentAllElements(ActionEvent event) throws Exception {
        event = null; // unused
        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedCurrentDetails());


            int first = this.getSalVariedElmGuidesDataTable().getFirst();

            Integer rowsCountPerPage=(Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());
            //Integer rowsCountPerPage = getCurrentDataTable().getRowCount();
            if (rowsCountPerPage == null) {

                throw new Exception("#{shared_util.noOfTableRows} return null");
            }
            int count = rowsCountPerPage.intValue();
            System.out.println((first + count));
            for (int j = first; j < first + count; j++) {
                System.out.println(j);
                this.getSalVariedElmGuidesDataTable().setRowIndex(j);
                if (!this.getSalVariedElmGuidesDataTable().isRowAvailable())
                    break;

                IBasicDTO selected = (IBasicDTO)this.getSalVariedElmGuidesDataTable().getRowData();
                System.out.println(selected.getCode().getKey());
                if (isCheckAllFlag() == true) {
                    s.add(selected);

                }


                if (isCheckAllFlag() == false) {
                    s.remove(selected);
                }


            }

            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().addAll(s);


        } catch (Exception e) {

            System.out.println(e.toString());

        }


    }

    //using hashset to solve the problem of duplicate selected items in the datatable  added by  aboelhassan hamed applied in the crs module

    public void selectedCurrent(ActionEvent event) throws Exception {
        event = null; // unused
        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedCurrentDetails());


            IClientDTO selected = (IClientDTO)this.getCurrentDataTable().getRowData();


            if (selected.getChecked()) {


                try {
                    s.add(this.mapToCodeNameDTO(selected));

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            if (!(selected.getChecked())) {


                s.remove(this.mapToCodeNameDTO(selected));


            }

            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().addAll(s);


        } catch (Exception e) {
            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }


    }

    public void selectedCurrentElement(ActionEvent event) throws Exception {
        event = null; // unused
        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedCurrentDetails());


            IClientDTO selected = (IClientDTO)this.getSalVariedElmGuidesDataTable().getRowData();


            if (selected.getChecked()) {


                try {
                    s.add(selected);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            if (!(selected.getChecked())) {


                s.remove(selected);


            }

            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().addAll(s);


        } catch (Exception e) {
            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }


    }


    public void setTotalValue(Float totalValue) {
        this.totalValue = totalValue;
    }

    public Float getTotalValue() {
        return totalValue;
    }

    public void setFelsValue(String felsValue) {
        this.felsValue = felsValue;
    }

    public String getFelsValue() {
        return felsValue;
    }

    public void setDinarValue(String dinarValue) {
        this.dinarValue = dinarValue;
    }

    public String getDinarValue() {
        return dinarValue;
    }

   

    public void setSalVariedElmGuidesDataTable(HtmlDataTable salVariedElmGuidesDataTable) {
        this.salVariedElmGuidesDataTable = salVariedElmGuidesDataTable;
    }

    public HtmlDataTable getSalVariedElmGuidesDataTable() {
        return salVariedElmGuidesDataTable;
    }

    public void setEmpFullName(String empFullName) {
        this.empFullName = empFullName;
    }

    public String getEmpFullName() {
        return empFullName;
    }

    public void setCurrentSalVariedElmGuideDTOList(List<IBasicDTO> currentSalVariedElmGuideDTOList) {
        this.currentSalVariedElmGuideDTOList = currentSalVariedElmGuideDTOList;
    }

    public List<IBasicDTO> getCurrentSalVariedElmGuideDTOList() {
        if(currentSalVariedElmGuideDTOList== null){
            currentSalVariedElmGuideDTOList =new ArrayList<IBasicDTO>();
        }
            
        return currentSalVariedElmGuideDTOList;
    }

    public void setCurrentDisplayedSalVariedElmGuideDTOList(List<IBasicDTO> currentDisplayedSalVariedElmGuideDTOList) {
        this.currentDisplayedSalVariedElmGuideDTOList = currentDisplayedSalVariedElmGuideDTOList;
    }

    public List<IBasicDTO> getCurrentDisplayedSalVariedElmGuideDTOList() {
        currentDisplayedSalVariedElmGuideDTOList = new ArrayList<IBasicDTO>(0);

        if (currentSalVariedElmGuideDTOList != null) {
            for (IBasicDTO dto : currentSalVariedElmGuideDTOList) {
                if (dto.getStatusFlag() == null)
                    currentDisplayedSalVariedElmGuideDTOList.add(dto);
                if (dto.getStatusFlag() != null && dto.getStatusFlag().longValue() == added.longValue())
                    currentDisplayedSalVariedElmGuideDTOList.add(dto);
                if (dto.getStatusFlag() != null && dto.getStatusFlag().longValue() == IConstants.UPDATED_ITEM.longValue())
                    currentDisplayedSalVariedElmGuideDTOList.add(dto);
            }
        }
        return currentDisplayedSalVariedElmGuideDTOList;
    }

    public void setCivilExist(Boolean civilExist) {
        this.civilExist = civilExist;
    }

    public Boolean getCivilExist() {
        return civilExist;
    }

    public void setElmGuideExist(String elmGuideExist) {
        this.elmGuideExist = elmGuideExist;
    }

    public String getElmGuideExist() {
        return elmGuideExist;
    }    
}
