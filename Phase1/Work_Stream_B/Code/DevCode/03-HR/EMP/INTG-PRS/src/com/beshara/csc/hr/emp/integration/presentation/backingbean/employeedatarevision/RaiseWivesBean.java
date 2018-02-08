package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;


//import com.beshara.csc.hr.mer.integration.presentation.backingbean.issuedecision.IssueDecisionBean;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.sal.business.client.ISalEmpWifesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpWifesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalRaiseDecisionParamsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalSheetDetailsSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.ISettDecisionDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalEmpWifesDTO;
import com.beshara.csc.hr.sal.business.dto.SalRaiseDecisionParamsDTO;
import com.beshara.csc.hr.sal.business.entity.ISalEmpWifesEntityKey;
import com.beshara.csc.hr.sal.business.shared.IMerConstant;
import com.beshara.csc.hr.sal.business.shared.exception.NoMarriedRaiseException;
import com.beshara.csc.hr.sal.business.shared.exception.NoSingleSocialRaiseException;
import com.beshara.csc.inf.business.client.IKwtCitizensResidentsClient;
import com.beshara.csc.inf.business.client.IMaritalStatusClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IMaritalStatusDTO;
import com.beshara.csc.inf.business.dto.IWifeSonInfoDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.IMaritalStatusEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.integration.presentation.backingbean.reg.DecisionJoinBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.sal.exception.MarriageDateOverlappedwithOldWifeRaiseException;
import com.beshara.csc.sharedutils.business.sal.exception.StopDateMustBeEnteredAtAddWifeRaiseException;
import com.beshara.csc.sharedutils.business.sal.exception.StopDateOverlappedwithOldWifeRaiseException;
import com.beshara.csc.sharedutils.business.sal.exception.ValidWifeRaiseExistException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.sql.Date;
import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlDataTable;


public class RaiseWivesBean extends MerRaiseMaintainBean {

    private static final String MER_RESOURCE = "com.beshara.csc.hr.emp.integration.presentation.resources.integration";
    @SuppressWarnings("compatibility:7121768167929312672")
    private static final long serialVersionUID = 1L;
    private List<IBasicDTO> currentDetails = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> currentDisplayDetails = new ArrayList<IBasicDTO>();
    private HtmlDataTable currentDataTable = new HtmlDataTable();
    private String divCivilId = "";
    private boolean divValidCivil = true;
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
    private ISalEmpWifesDTO salEmpWifesDTO = SalDTOFactory.createSalEmpWifesDTO();
    private String selectedSuspenionElements = "";
    private List wivesSuspensionReason = null;
    private MerRaiseMaintainBean merRaiseMaintainBean =
        (MerRaiseMaintainBean)evaluateValueBinding("merRaiseMaintainBean");
//    private DecisionMaintainBean decisionMaintainBean =
//        (DecisionMaintainBean)evaluateValueBinding("decisionMaintainBean");
    private boolean addWifeFemalFlag;
    private boolean divCivilExist;
    private boolean showErrorSaveAndNew = false;
    private String errSaveAndNewMsg = null;
    private boolean showSuspendPartAddDiv = false;
    private DecisionJoinBean decisionIntegration = new DecisionJoinBean();
    private boolean checkCalOrPaid;
    private boolean doSuspendWife;
    private IWifeSonInfoDTO wifeSonInfoDTO = InfDTOFactory.createWifeSonInfoDTO();
    private boolean maritalStatusIsSingle;
    private Date marriageDate;
    private boolean dataRecievedSuccessfully;

    //ws data fields added by Hany Omar
    private String replyMsgValue;

    public RaiseWivesBean() { //super ( ) ;
        setPageDTO(SalDTOFactory.createSalEmpWifesDTO());
        setMyTableData(new ArrayList());
        setSelectedPageDTO(SalDTOFactory.createSalEmpWifesDTO());
        ((ISalEmpWifesDTO)getSelectedPageDTO()).setKwtCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
        setClient(SalClientFactory.getSalEmpWifesClient());
        // setContent1Div ( "delDivScroll2" ) ;
        //  setDivMainContent("delDivScroll2");
        merRaiseMaintainBean.setDetailFilterMethod("raiseWivesBean.filterByCivilId");
        merRaiseMaintainBean.setDetailResetMethod("raiseWivesBean.reSetData");
        decisionIntegration.okMethodName = "raiseWivesBean.saveSelectedDecision";
        // setMasterDetailDiv("divREGIntegrate");
        //  setLookupAddDiv("lookupAddMerRaiseAdddiv");
        //  setLookupEditDiv("lookupAddMerRaiseAdddiv");
    }

    public AppMainLayout appMainLayoutBuilder() { // //super.appMainLayoutBuilder ( ) ;
        // AppMainLayout app = new AppMainLayout ( ) ;
        //
        // app.showLookupListPage ( ) ;
        // app.setShowSearch ( false ) ;
        // app.setShowWizardBar ( true ) ;
        // app.setShowContent1 ( true ) ;
        // app.setShowEmpSrchDiv ( true ) ;
        // app.setShowCustomDiv1 ( true ) ;
        // return app ;
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowSearch(false);
        app.setShowWizardBar(true);
        app.setShowContent1(true);
        app.setShowEmpSrchDiv(false);
        app.setShowLookupEdit(false);
        app.setShowCustomDiv1(false);
        app.setShowpaging(true);
        app.setShowMasterDetail(false);
       // app.setShowbar(false);
       // app.setShowIntegrationDiv1(true);
        return app;
    }

    public boolean validateWifeExistence() {
        boolean wifeAddedBefore = true;

        //Long empCivilId = Long.valueOf(merRaiseMaintainBean.getCivilId());
        Long empCivilId = ((IEmployeesEntityKey)merRaiseMaintainBean.getEmployeesDTO().getCode()).getCivilId();
        Long mrrgTimestamp = getMarriageDate().getTime();

        try {
            //check if wife exist or not
            List<ISalEmpWifesDTO> list =
                SalClientFactory.getSalEmpWifesClient().getValidEmpWives(Long.valueOf(getDivCivilId()));
            for (int i = 0; i < list.size(); i++) {
                //check if she related with the same employee
                Long husbandRalCivilId = ((IEmployeesDTO) ( (ISalEmpWifesDTO)list.get(i)).getEmployeesDTO()).getRealCivilId();
                if (empCivilId.equals(((ISalEmpWifesEntityKey)list.get(i).getCode()).getCivilId())) {
                    /** if she is relates to the same employee ...
                    then check if marriageDate came from screen same same wiz the one came from DB */
                    if (new Timestamp(mrrgTimestamp).equals(list.get(i).getMarriageDate())) {
                        setAddWifeFemalFlag(true);
                        SharedUtilBean shared_util = getSharedUtil();
                        String errMsg =
                            shared_util.messageLocator("com.beshara.csc.hr.mer.presentation.resources.mer", "Wife_Emp_Date_ADDED_BEFORE");
                        setReplyMsgValue(errMsg);
                        return false;
                    } else if (list.get(i).getStatus().equals(IMerConstant.ACTIVE)) {

                        setAddWifeFemalFlag(true);
                        SharedUtilBean shared_util = getSharedUtil();
                        String errMsg =
                            shared_util.messageLocator("com.beshara.csc.hr.mer.presentation.resources.mer", "Wife_Emp_Status_ADDED_BEFORE");
                        setReplyMsgValue(errMsg);
                        return false;
                    }
                } else if (list.get(i).getStatus().equals(IMerConstant.ACTIVE)) {
                    setAddWifeFemalFlag(true);
                    SharedUtilBean shared_util = getSharedUtil();
                    String errMsg =
                        shared_util.messageLocator("com.beshara.csc.hr.mer.presentation.resources.mer", "Wife_Another_Emp_ADDED_BEFORE");
                    errMsg = errMsg.replaceFirst("#", husbandRalCivilId.toString() );
                    setReplyMsgValue(errMsg);
                    return false;
                }

            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            return true;
        }

        return true;
    }

    public String getDivInfoByCivilId() { //IKwtCitizensResidentsClient iKwtCitizensResidentsClient = InfClientFactory.getKwtCitizensResidentsClient ( ) ;

        if (getDivCivilId() != null && getMarriageDate() != null) {

            if (!validateWifeExistence()) {
                return "";
            }

            Long realCivilId = Long.valueOf(merRaiseMaintainBean.getCivilId());
            Long empCivilId = ((IEmployeesEntityKey)merRaiseMaintainBean.getEmployeesDTO().getCode()).getCivilId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            SharedUtilBean shared_util = getSharedUtil();
            String errMsg =
                shared_util.messageLocator("com.beshara.csc.hr.mer.presentation.resources.mer", "WS_errorMsg");


            try {
                wifeSonInfoDTO =
                        SalClientFactory.getSalEmpWifesClient().validateWifeInfo(realCivilId, Long.valueOf(getDivCivilId()),
                                                                                 marriageDate);
                wifeSonInfoDTO.setEmpCivilId(empCivilId);
            } catch (DataBaseException e) {
                e.printStackTrace();
                setReplyMsgValue(errMsg);
                return "";
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                setReplyMsgValue(errMsg);
                return "";
            }
            Date wifeMarriageDate = wifeSonInfoDTO.getMarriageDate();
            java.util.Date mDate = null;
            Long mrrgTimestamp = null;
            if(wifeMarriageDate != null){
                String wifeMarriageDateString = sdf.format(wifeMarriageDate);
                try {
                    mDate = sdf.parse(wifeMarriageDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("++++++++++++mDate+++++++++++ : " + mDate);
                mrrgTimestamp = mDate.getTime();
            }
            try {
                Long replyMsgCode = (Long.parseLong( wifeSonInfoDTO.getReplyMsgCode()+"" ) );
                if (replyMsgCode == null ||  !(IMerConstant.VALID_REPLY_MSG_CODE.equals(replyMsgCode) ) ) {
                    setAddWifeFemalFlag(true);
                    wifeSonInfoDTO.setBrancheName("");
                    wifeSonInfoDTO.setMarriageDate(null);
                    setReplyMsgValue(errMsg);
                    return "";
                } else {
                    setDataRecievedSuccessfully(true);
                    setAddWifeFemalFlag(false);
                    setReplyMsgValue(wifeSonInfoDTO.getReplyMsg().substring(3));

                    ((ISalEmpWifesDTO)getPageDTO()).setStatus(0L);

                    IEmployeesDTO empDTO =
                        (IEmployeesDTO)EmpClientFactory.getEmployeesClient().getEmployeeInfoByElmType(empCivilId);
                    System.out.println("+++++++++++++++++++++++" + empDTO.getHireDate());
                    //get Emp HireDate
                    Date empHireDate = empDTO.getHireDate();
                    String empHireDateString = sdf.format(empHireDate);

                    //compare Hire with marriage (Date)
                    java.util.Date hDate = sdf.parse(empHireDateString);
                    System.out.println("++++++++++++hDate+++++++++++ : " + hDate);

                    Long hireTimestamp = hDate.getTime();
                    if (hDate.compareTo(mDate) > 0) {
                        wifeSonInfoDTO.setAllowanceDate(new Timestamp(hireTimestamp));
                    } else {
                        wifeSonInfoDTO.setAllowanceDate(new Timestamp(mrrgTimestamp));
                    }
                    if (wifeSonInfoDTO.getGenderCountryDTO() == null) {
                        setAddWifeFemalFlag(true);
                        setDataRecievedSuccessfully(false);
                        errMsg =
                            shared_util.messageLocator("com.beshara.csc.hr.mer.presentation.resources.mer", "WS_errorMsg_nationality");
                        setReplyMsgValue(errMsg);
                        setWifeSonInfoDTO(InfDTOFactory.createWifeSonInfoDTO());
                        setPageDTO(SalDTOFactory.createSalEmpWifesDTO());
                        return "";
                    }
                }
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setDivCivilExist(true);
        return "";
    }

    public void saveWifeInfo() {
        reIntializeMsgs();
        setPageDTO(getWifeSonInfoDTO());
        addWifeInfo();
    }

    public void addWifeInfo() {
        try {
            super.add();
            String msg = getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.global", "SuccessAdds");
            getSharedUtil().setSuccessMsgValue(msg);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public String suspendAction() {
        if (((ISalEmpWifesDTO)getSelectedDTOS().get(0)).getSalStopReasonsDTO() == null) {
            clearDTO();
            setEntityKey(getSelectedDTOS().get(0).getCode());
            salEmpWifesDTO = (ISalEmpWifesDTO)preEdit();
            setPageMode(3);
            // setJavaScriptCaller ( "changeVisibilityDiv ( window.blocker , window.customDiv1 ) ; " ) ;
        }
        return "";
    }

    public void saveSuspenion() {

        Long empCivilId =
            ((IKwtCitizensResidentsEntityKey)(getEmployeesDTO().getCitizensResidentsDTO().getCode())).getCivilId();

        //TODO: checkCalSalaryOrPaySalary
        if (validateCalSalaryOrPaySalaryForSuspend(empCivilId,
                                                   new Date(getSalEmpWifesDTO().getStopDate().getTime()))) {
            suspendWife();
        }
    }

    public void suspendWife() {
        reIntializeMsgs();
        doSuspendWife = false;
        SharedUtilBean shared_util = getSharedUtil();
        try {
            if (salEmpWifesDTO != null) {

                SalClientFactory.getSalEmpWifesClient().suspendWife(salEmpWifesDTO);
            }
            cancelSearch();
            getPageIndex((String)salEmpWifesDTO.getCode().getKey());
            if (super.getHighlightedDTOS() != null) {
                super.getHighlightedDTOS().add(salEmpWifesDTO);
            }
            super.setShowEdit(false);
            shared_util.handleSuccMsg(MER_RESOURCE, "MER_CHILD_SUSPEND_SUCESS_MSG");
        } catch (DataBaseException e) {
            shared_util.handleException(e, MER_RESOURCE, "cannotsuspendwife");
        } catch (Exception e) {
            e.printStackTrace();
            shared_util.handleException(e, MER_RESOURCE, "cannotsuspendwife");
        }
    }

    public void handleGeneralException(Exception e, String key) {
        SharedUtilBean shared_util = getSharedUtil();
        shared_util.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", key);
    }

    public String clearDTO() {
        setSelectedSuspenionElements(ISystemConstant.VIRTUAL_VALUE.toString());
        setSalEmpWifesDTO(SalDTOFactory.createSalEmpWifesDTO());
        setPageMode(0);
        return "";
    }

    public boolean saveRaiseForWives() {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            if (getKwtCitizensResidentsDTO() != null && getEmployeesDTO() != null) {
                getSalEmpWifesDTO().setKwtCitizensResidentsDTO(getKwtCitizensResidentsDTO());
                getSalEmpWifesDTO().setEmployeesDTO(getEmployeesDTO());
                IBasicDTO dto = getClient().add(getSalEmpWifesDTO());
                setPageDTO(dto);
                shared_util.setSuccessMsgValue("SuccessAdds");
                if (dto != null)
                    return true;
            } else
                getSharedUtil().setErrMsgValue("FailureInAdd");
        } catch (SharedApplicationException e) {
            handleGeneralException(e, "FailureInAdd");
        } catch (DataBaseException e) {
            handleGeneralException(e, "FailureInAdd");
        } catch (Exception e) {
            handleGeneralException(e, "FailureInAdd");
        }
        return false;
    }

    public void filterByCivilId() {
        if (!merRaiseMaintainBean.isFemaleGender())
            setMyTableData(getTotalList());
    }

    public void add() throws DataBaseException {
        ((ISalEmpWifesDTO)getPageDTO()).setEmployeeKey(getCivilId());
        if (getKwtCitizensResidentsDTO() != null && getKwtCitizensResidentsDTO().getCode() != null &&
            getKwtCitizensResidentsDTO().getCode().getKey() != null) {
            ((ISalEmpWifesDTO)getPageDTO()).setKwtCitizensResidentsKey(getKwtCitizensResidentsDTO().getCode().getKey().toString());
            boolean validWifeKey = true;
            try {
                SalClientFactory.getSalEmpWifesClient().validWifeRaise((ISalEmpWifesDTO)getPageDTO());
                validWifeKey = true;
            } catch (ValidWifeRaiseExistException e) {
                e.printStackTrace();
                validWifeKey = false;
                showErrorSaveAndNew = true;
                this.setErrorMsg("ValidWifeRaiseExistException");
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "ValidWifeRaiseExistException");
                if (e.getExtraInformation() != null && e.getExtraInformation().size() != 0) {
                    getSharedUtil().setErrMsgValue(getSharedUtil().getErrMsgValue() + " " +
                                                   e.getExtraInformation().get(0).toString() + " ) ");
                    errSaveAndNewMsg = getSharedUtil().getErrMsgValue();
                }
            } catch (StopDateMustBeEnteredAtAddWifeRaiseException e) {
                validWifeKey = false;
                showSuspendPartAddDiv = true;
                showErrorSaveAndNew = true;
                // String errMsgcontent=getSharedUtil ( ) .messageLocator ( "com.beshara.jsfbase.csc.msgresources.globalexceptions" , "StopDateMustBeEnteredAtAddWifeRaiseException" ) ;
                String errMsgcontent1 =
                    getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                   "StopDateMustBeEnteredAtAddWifeRaiseException1");
                String errMsgcontent2 =
                    getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                   "StopDateMustBeEnteredAtAddWifeRaiseException2");
                this.setJavaScriptCaller("changeVisibilityDiv ( window.blocker , window.lookupAddDiv ) ; settingFoucsAtDivAdd ( ) ; ");
                // getSharedUtil ( ) .handleException ( e , "com.beshara.jsfbase.csc.msgresources.globalexceptions" , "StopDateMustBeEnteredAtAddWifeRaiseException" ) ;
                if (e.getExtraInformation() != null && e.getExtraInformation().size() != 0 &&
                    e.getExtraInformation().get(0) != null) {
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date validUnitlDate = (Date)e.getExtraInformation().get(0);
                    String dt = df.format(validUnitlDate);
                    // validUnitlDate = Date.valueOf ( dt ) ;
                    System.out.println(dt);
                    String errMsgcontent = errMsgcontent1 + dt + errMsgcontent2;
                    errSaveAndNewMsg = errMsgcontent;
                }
            } catch (MarriageDateOverlappedwithOldWifeRaiseException e) {
                e.printStackTrace();
                validWifeKey = false;
                showErrorSaveAndNew = true;
                this.setErrorMsg("MarriageDateOverlappedwithOldWifeRaiseException");
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "MarriageDateOverlappedwithOldWifeRaiseException");
                if (e.getExtraInformation() != null && e.getExtraInformation().size() != 0 &&
                    e.getExtraInformation().get(0) != null && e.getExtraInformation().get(1) != null) {
                    Timestamp marriageDate = (Timestamp)e.getExtraInformation().get(0);
                    Timestamp stopDate = (Timestamp)e.getExtraInformation().get(1);
                    Date x = new Date(marriageDate.getTime());
                    Date y = new Date(stopDate.getTime());
                    getSharedUtil().setErrMsgValue(getSharedUtil().getErrMsgValue() + " ( " + x + " ) , ( " + y +
                                                   " ) ) ");
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String dt1 = df.format(x);
                    String dt2 = df.format(y);
                    // String errMsgcontent=getSharedUtil ( ) .messageLocator ( "com.beshara.jsfbase.csc.msgresources.globalexceptions" , "MarriageDateOverlappedwithOldWifeRaiseException" ) ;
                    getSharedUtil().setErrMsgValue(getSharedUtil().getErrMsgValue() + " ( " + dt1 + " ) , ( " + dt2 +
                                                   " ) ) ");
                    // getSharedUtil ( ) .setErrMsgValue ( getSharedUtil ( ) .getErrMsgValue ( ) + dt + errMsgcontent2 ) ;
                    errSaveAndNewMsg = getSharedUtil().getErrMsgValue();
                }
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                validWifeKey = false;
                this.setShowErrorMsg(true);
                this.setErrorMsg("FailureInAdd");
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "FailureInAdd");
            }

            if (validWifeKey)
                super.add();
        }
    }

    public void reInitializePageDTO() {
        setPageDTO(SalDTOFactory.createSalEmpWifesDTO());
    }

    public void reSetData() {
        setPageDTO(SalDTOFactory.createSalEmpWifesDTO());
        setMyTableData(new ArrayList());
        setSelectedPageDTO(SalDTOFactory.createSalEmpWifesDTO());
        ((ISalEmpWifesDTO)getSelectedPageDTO()).setKwtCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
        setPageMode(0);
        setSelectedRadio("");
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0)
            getSelectedDTOS().clear();
    }

    public void preAdd() {
        setPageMode(1);
        super.preAdd();
        setPageDTO(SalDTOFactory.createSalEmpWifesDTO());
        divValidCivil = true;
        divCivilExist = false;
        setMarriageDate(null);
        showSuspendPartAddDiv = false;
        setDivCivilId("");
        addWifeFemalFlag = false;
        dataRecievedSuccessfully = false;
        setKwtCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
        setWifeSonInfoDTO(InfDTOFactory.createWifeSonInfoDTO());
    }

    public void saveAndNew() throws DataBaseException {
        reIntializeMsgs();
        this.add();
        if (getSharedUtil().getSuccessMsgValue() != null && !getSharedUtil().getSuccessMsgValue().equals(""))
            this.setSuccess(true);
        if (!showSuspendPartAddDiv) {
            setDivCivilId("");
            divValidCivil = true;
            divCivilExist = false;
            addWifeFemalFlag = false;
            showSuspendPartAddDiv = false;
            setKwtCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
            this.reInitializePageDTO();
        }
    }

    public void save() {

        reIntializeMsgs();

        SalEmpWifesDTO wifeDTO = (SalEmpWifesDTO)getPageDTO();
        Long empCivilId =
            ((IKwtCitizensResidentsEntityKey)(getEmployeesDTO().getCitizensResidentsDTO().getCode())).getCivilId();

        Date allownceDate = new Date(wifeDTO.getAllowanceDate().getTime());
        try {

            Date maxAllowanceDate =
                SalClientFactory.getSalEmpWifesClient().getMaxAllowanceDateForValidWifes(getEmployeesDTO().getCode());
            if (maxAllowanceDate != null && maxAllowanceDate.compareTo(allownceDate) > 0) {
                doSuspendWife = false;
                checkCalOrPaid = false;
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(MER_RESOURCE,
                                                                              "maxAllownceDateIsGreaterThanAllownceDate"));
            } else {

                if (getEmployeesDTO().getStartWorkingDate() != null &&
                    getEmployeesDTO().getStartWorkingDate().compareTo(allownceDate) > 0) {
                    getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(MER_RESOURCE,
                                                                                  "allownceDateBeforeStartWorkingDate"));
                } else {
                    //TODO: checkCalSalaryOrPaySalary
                    if (validateCalSalaryOrPaySalary(empCivilId, allownceDate)) {
                        addWife();
                    }
                }
            }
        } catch (SharedApplicationException e) {
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(MER_RESOURCE, "cannotaddwife"));
        } catch (DataBaseException e) {
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(MER_RESOURCE, "cannotaddwife"));
        } catch (Exception e) {
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(MER_RESOURCE, "cannotaddwife"));
        }
    }

    public void addWife() {
        checkCalOrPaid = false;
        reIntializeMsgs();
        try {
            getSharedUtil().setErrMsgValue(null);
            this.add();
        } catch (Exception ex) {
            this.setErrorMsg(ex.getCause().getMessage());
        }

        if (getSharedUtil().getErrMsgValue() != null &&
            getSharedUtil().getErrMsgValue().equals("SystemFailureException")) {
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(MER_RESOURCE, "cannotaddwife"));
        }

        if (!showSuspendPartAddDiv) {
            this.reInitializePageDTO();
            reIntializeAddDivMsgs();
            setDivCivilId("");
        }
    }

    public void setCurrentDetails(List<IBasicDTO> currentDetails) {
        this.currentDetails = currentDetails;
    }

    public List<IBasicDTO> getCurrentDetails() {
        return currentDetails;
    }

    public void setCurrentDisplayDetails(List<IBasicDTO> currentDisplayDetails) {
        this.currentDisplayDetails = currentDisplayDetails;
    }

    public List<IBasicDTO> getCurrentDisplayDetails() {
        return currentDisplayDetails;
    }

    public void setCurrentDataTable(HtmlDataTable currentDataTable) {
        this.currentDataTable = currentDataTable;
    }

    public HtmlDataTable getCurrentDataTable() {
        return currentDataTable;
    }

    public void setDivCivilId(String divCivilId) {
        this.divCivilId = divCivilId;
    }

    public String getDivCivilId() {
        return divCivilId;
    }

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {
        return kwtCitizensResidentsDTO;
    }

    public void setSalEmpWifesDTO(ISalEmpWifesDTO salEmpWifesDTO) {
        this.salEmpWifesDTO = salEmpWifesDTO;
    }

    public ISalEmpWifesDTO getSalEmpWifesDTO() {
        return salEmpWifesDTO;
    }

    public void setSelectedSuspenionElements(String selectedSuspenionElements) {
        this.selectedSuspenionElements = selectedSuspenionElements;
    }

    public String getSelectedSuspenionElements() {
        return selectedSuspenionElements;
    }

    public void setWivesSuspensionReason(List wivesSuspensionReason) {
        this.wivesSuspensionReason = wivesSuspensionReason;
    }

    public List getWivesSuspensionReason() {
        //        if (wivesSuspensionReason == null) {
        //            try {
        //                ISalStopSrReasonsClient iSalStopSrReasonsClient = SalClientFactory.getSalStopSrReasonsClient();
        //                // return fillComboBox ( iSalStopSrReasonsClient ) ; an example of fillComboBox method
        //                wivesSuspensionReason = iSalStopSrReasonsClient.getCodeName();
        //            } catch (DataBaseException e) {
        //                e.printStackTrace();
        //                return wivesSuspensionReason;
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //                return wivesSuspensionReason;
        //            }
        //        }
        return wivesSuspensionReason;
    }

    public void setDivValidCivil(boolean divValidCivil) {
        this.divValidCivil = divValidCivil;
    }

    public boolean isDivValidCivil() {
        return divValidCivil;
    }

    public void setAddWifeFemalFlag(boolean addWifeFemalFlag) {
        this.addWifeFemalFlag = addWifeFemalFlag;
    }

    public boolean isAddWifeFemalFlag() {
        return addWifeFemalFlag;
    }

    public boolean isCivilExist() {
        return merRaiseMaintainBean.getCivilExist();
    }

    public boolean getCivilExist() {
        return merRaiseMaintainBean.getCivilExist();
    }

    public String getCivilId() {
        return merRaiseMaintainBean.getCivilId();
    }

    public boolean isValidCivilId() {
        return merRaiseMaintainBean.isValidCivilId();
    }

    public boolean isEmpHired() {
        return merRaiseMaintainBean.isEmpHired();
    }

    public boolean isPayrollInfoExist() {
        return merRaiseMaintainBean.isPayrollInfoExist();
    }

    public String getDegree() {
        return merRaiseMaintainBean.getDegree();
    }

    public IEmployeesDTO getEmployeesDTO() {
        return merRaiseMaintainBean.getEmployeesDTO();
    }

    public List getTotalList() {
        List totalList = new ArrayList();
        String civilIDLocal = this.getCivilId();
        if (this.getCivilExist() && civilIDLocal != null && !civilIDLocal.equals("")) {
            try {
                totalList =
                        //SalClientFactory.getSalEmpWifesClient().getEmployeeCurrentWifesByRealCivilId(merRaiseMaintainBean.getEmployeesDTO() );
                        SalClientFactory.getSalEmpWifesClient().getEmployeeValidWives(getEmployeesDTO().getCode());
                //EmpEntityKeyFactory.createEmployeesEntityKey(Long.valueOf(civilIDLocal)));
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return totalList;
    }

    public void setDivCivilExist(boolean divCivilExist) {
        this.divCivilExist = divCivilExist;
    }

    public boolean isDivCivilExist() {
        return divCivilExist;
    }

    public void setShowErrorSaveAndNew(boolean showErrorSaveAndNew) {
        this.showErrorSaveAndNew = showErrorSaveAndNew;
    }

    public boolean isShowErrorSaveAndNew() {
        return showErrorSaveAndNew;
    }

    public void setErrSaveAndNewMsg(String errSaveAndNewMsg) {
        this.errSaveAndNewMsg = errSaveAndNewMsg;
    }

    public String getErrSaveAndNewMsg() {
        return errSaveAndNewMsg;
    }

    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        boolean validWifeKey = true;
        try {
            SalClientFactory.getSalEmpWifesClient().validUpdateWifeRaise((ISalEmpWifesDTO)getSelectedPageDTO());
            validWifeKey = true;
        } catch (StopDateOverlappedwithOldWifeRaiseException e) {
            e.printStackTrace();
            validWifeKey = false;
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "StopDateOverlappedwithOldWifeRaiseException");
            if (e.getExtraInformation() != null && e.getExtraInformation().size() != 0 &&
                e.getExtraInformation().get(0) != null && e.getExtraInformation().get(1) != null) {
                Timestamp marriageDate = (Timestamp)e.getExtraInformation().get(0);
                Timestamp stopDate = (Timestamp)e.getExtraInformation().get(1);
                Date x = new Date(marriageDate.getTime());
                Date y = new Date(stopDate.getTime());
                getSharedUtil().setErrMsgValue(getSharedUtil().getErrMsgValue() + " ( " + x + " ) , ( " + y + " ) ) ");
                errSaveAndNewMsg = getSharedUtil().getErrMsgValue();
            }
        } catch (SharedApplicationException e) {
            getSharedUtil().setErrMsgValue("FailureInUpdate");
        }
        if (validWifeKey)
            super.edit();
    }

    public void setShowSuspendPartAddDiv(boolean showAddDivAgain) {
        this.showSuspendPartAddDiv = showAddDivAgain;
    }

    public boolean getShowSuspendPartAddDiv() {
        return showSuspendPartAddDiv;
    }

    public String addDecisoin() {
        getIntegrationBean().reInitializeBean();
        getIntegrationBean().setNavgationCaseFrom("raiseWivesNav");
        getIntegrationBean().setBeanNameTo("decisionListBean");
        getIntegrationBean().setActionTo("goAdd");
        getIntegrationBean().getHmBaseBeanFrom().put("raiseWivesBean", evaluateValueBinding("raiseWivesBean"));
        getIntegrationBean().getHmBaseBeanFrom().put("merRaiseMaintainBean",
                                                     evaluateValueBinding("merRaiseMaintainBean"));
        IEmployeesDTO emp = merRaiseMaintainBean.getEmployeesDTO();
        getIntegrationBean().getSelectedDTOFrom().add(emp);
        getIntegrationBean().setModuleFrom("sal");
        getIntegrationBean().setBeanNameFrom("raiseWivesBean");
        getIntegrationBean().setActionFrom("empAddDecisoin");
        getIntegrationBean().setInitializeMethod("raiseWivesBean.initializeDecisionDTO");
        return getIntegrationBean().goTO();
    }

    public String stopWivesRaiseDecision() {
//        IssueDecisionBean issueDecisionBean = IssueDecisionBean.getInstance();
//        String returnMethodName = "raiseWivesBean.returnRaiseDecision";
//        String successMethodName = "raiseWivesBean.successRaiseDecision";
//        String settDecSuccessMethodName = "raiseWivesBean.settDecisionSuccess";
//        issueDecisionBean.setUseSettelment(true);
//        ISalEmpWifesDTO wifdto = (ISalEmpWifesDTO)getSelectedDTOS().get(0);
//
//        ISalElementGuidesDTO elem = null;
//        try {
//            elem = (ISalElementGuidesDTO)((ISalEmpWifesClient)getClient()).getSocialRaiseAfterSuspenDecision(wifdto);
//            Long elmGuideCode = null;
//            if (elem == null) {
//                issueDecisionBean.setUseSettelment(false);
//            } else {
//                elmGuideCode = ((ISalElementGuidesEntityKey)elem.getCode()).getElmguideCode();
//                issueDecisionBean.setElmGuideCode(elmGuideCode);
//            }
//            ISalRaiseDecisionParamsDTO dto = fillDecisionParams();
//            dto.setAddRaise(false);
//            dto.setReasonElmType(IMerConstant.SOCIAL_RAISE_CODE);
//            dto.setDecisionTextKey("suspend_wife_decision_text");
//            dto.setRenderStopReasonGrd(true);
//            Long subjectCode = IMerConstant.Raise_Stop_Decision_Code;
//            try {
//                subjectCode = UtilBean.getMappedCodeBySysSettingCode(IMerConstant.Raise_Stop_Decision_Code);
//            } catch (Exception e) {
//                // TODO: Add catch code
//                e.printStackTrace();
//            }
//            dto.setDecSubjectCode(IMerConstant.Raise_Stop_Decision_Code);
//            issueDecisionBean.setDefaultEmpSuggSettMethod(false);
//            issueDecisionBean.setEmpSuggSettMethodName("raiseWivesBean.getSettDetails");
//            issueDecisionBean.init(fillSaveStateParams(), "raiseWivesNav", returnMethodName, successMethodName, dto,
//                                   getSelectedDTOS().get(0), "add_suspend_decision");
//            return IssueDecisionBean.PAGE_NAVIGATION_CASE;
//        } catch (SharedApplicationException e) {
//            e.printStackTrace();
//            if (e instanceof NoSingleSocialRaiseException) {
//                getSharedUtil().handleException(e, MER_RESOURCE, "NoSingleSocialRaiseException");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

    public List<IBasicDTO> getSettDetails(Long realCivilId, Long elmGuideCode, Date fromDate, Date toDate,
                                          Float amount, Long decisionType,
                                          Long empSalElmSerial) throws DataBaseException, SharedApplicationException {

        return SalClientFactory.getSalEmpSettelmentsClient().calcChildAndSocialRaiseEmpSuggSett(realCivilId,
                                                                                                elmGuideCode, fromDate,
                                                                                                toDate, amount,
                                                                                                decisionType,
                                                                                                empSalElmSerial,
                                                                                                IMerConstant.SOCIAL_RAISE_TYPE);

    }


    public String addWivesRaiseDecision() {
//        IssueDecisionBean issueDecisionBean = IssueDecisionBean.getInstance();
//        String returnMethodName = "raiseWivesBean.returnRaiseDecision";
//        String successMethodName = "raiseWivesBean.successRaiseDecision";
//        String settDecSuccessMethodName = "raiseWivesBean.settDecisionSuccess";
//        issueDecisionBean.setUseSettelment(true);
//        // start by Aly Nour was added in ver 1.18.4.9 CSC-12444 then droped down in ver 1.18.4.10 by cvs error
//        ISalEmpWifesDTO dto = (ISalEmpWifesDTO)getSelectedDTOS().get(0);
//        SharedUtilBean shared_util = getSharedUtil();
//        if (dto.getStopDate() != null) {
//            shared_util.handleException(null, MER_RESOURCE, "dec_msg");
//            return "";
//        }
//        if (dto.getStatus().equals(IMerConstant.ACTIVE)) {
//            shared_util.handleException(null, MER_RESOURCE, "grant_dec_done_msg");
//            return "";
//        }
//        // end by Aly Nour was added in ver 1.18.4.9 CSC-12444 then droped down in ver 1.18.4.10 by cvs error
//
//        ISalElementGuidesDTO elem = null;
//        try {
//            elem = (ISalElementGuidesDTO)((ISalEmpWifesClient)getClient()).getMarriedSalElementGuide(dto);
//            Long elmGuideCode = null;
//            if (elem == null) {
//                issueDecisionBean.setUseSettelment(false);
//            } else {
//                elmGuideCode = ((ISalElementGuidesEntityKey)elem.getCode()).getElmguideCode();
//                issueDecisionBean.setElmGuideCode(elmGuideCode);
//            }
//            issueDecisionBean.init(fillSaveStateParams(), "raiseWivesNav", returnMethodName, successMethodName,
//                                   fillDecisionParams(), dto, "add_raise_decision");
//            issueDecisionBean.setSettSuccessMethodName(settDecSuccessMethodName);
//            issueDecisionBean.setDefaultEmpSuggSettMethod(false);
//            issueDecisionBean.setEmpSuggSettMethodName("raiseWivesBean.getSettDetails");
//            return IssueDecisionBean.PAGE_NAVIGATION_CASE;
//        } catch (SharedApplicationException e) {
//            e.printStackTrace();
//            if (e instanceof NoSingleSocialRaiseException) {
//                getSharedUtil().handleException(e, MER_RESOURCE, "NoSingleSocialRaiseException");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
       return null;
    }

    public void returnRaiseDecision(Object params) {
        System.out.println("returned from add decision page");
        restoreSaveStateParams(params);
        filterByCivilId();
        if (params != null) {
            Map paramsMap = (Map)params;
            List list = ((List)paramsMap.get("selectedDTOS"));
            if (list != null && !list.isEmpty()) {
                IBasicDTO selectedDTO = (IBasicDTO)list.get(0);
                List<IBasicDTO> dTList = null;
                try {
                    dTList = getMyTableData();
                } catch (DataBaseException e) {
                    return;
                }
                for (IBasicDTO dto : dTList) {
                    if (dto.getCode().getKey().equals(selectedDTO.getCode().getKey())) {
                        getSelectedDTOS().add(dto);
                        break;
                    }
                }
            }

        }
        ((ISalEmpWifesDTO)getSelectedDTOS().get(0)).setStopDate(null);
        ((ISalEmpWifesDTO)getSelectedDTOS().get(0)).setSalStopReasonsDTO(null);
    }


    public void settDecisionSuccess(Object params) {
        restoreSaveStateParams(params);
        filterByCivilId();
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0) {
            getSelectedDTOS().clear();
            setSelectedRadio("");
        }
    }

    public Long successRaiseDecision(Object params, Object conditinsDTO, Object salEmpWifesDTO, Object raiseType,
                                     ISettDecisionDTO settDecDTO) throws SharedApplicationException {
        SharedUtilBean shared_util = getSharedUtil();
        Long empSalElmSerial = null;
        try {


            empSalElmSerial =
                    ((ISalEmpWifesClient)getClient()).makeWifeRaiseDecision((IBasicDTO)conditinsDTO, (IBasicDTO)salEmpWifesDTO,
                                                                            (Boolean)raiseType, settDecDTO);
            shared_util.handleSuccMsg(MER_RESOURCE, "Decision_Add_Success");
            restoreSaveStateParams(params);
            filterByCivilId();
            if ((Boolean)raiseType) {
                ((ISalEmpWifesDTO)getSelectedDTOS().get(0)).setStatus(IMerConstant.ACTIVE);

                IKwtCitizensResidentsDTO myKwtCitizensResidentsDTO =
                    (IKwtCitizensResidentsDTO)getEmployeesDTO().getCitizensResidentsDTO();
                IKwtCitizensResidentsClient kwtCitizensResidentsClient =
                    InfClientFactory.getKwtCitizensResidentsClient();
                //
                //
                Long marStatusCode =
                    ((IMaritalStatusEntityKey)myKwtCitizensResidentsDTO.getMaritalStatusDTO().getCode()).getMarstatusCode();
                //
                if (marStatusCode.equals(IMerConstant.M_STATUS_SINGLE)) // 1 if Single and 2 if married
                {
                    IMaritalStatusEntityKey maritalStatusEntityKey =
                        InfEntityKeyFactory.createMaritalStatusEntityKey(IMerConstant.M_STATUS_MARRIED);
                    IMaritalStatusDTO maritalStatusDTO;

                    IMaritalStatusClient maritalStatusClient = InfClientFactory.getMaritalStatusClient();
                    maritalStatusDTO = (IMaritalStatusDTO)maritalStatusClient.getById(maritalStatusEntityKey);
                    myKwtCitizensResidentsDTO.setMaritalStatusDTO(maritalStatusDTO);
                    kwtCitizensResidentsClient.updatekwtCitizensResidentMaritalStatus(myKwtCitizensResidentsDTO);
                }

            } else {
                ((ISalEmpWifesDTO)getSelectedDTOS().get(0)).setStatus(IMerConstant.NOT_ACTIVE);
            }


        } catch (SharedApplicationException e) {
            e.printStackTrace();
//            if (e instanceof DuplicateDecisionException) {
//                throw e;
//            }
            returnRaiseDecision(params);
            if (e instanceof NoSingleSocialRaiseException) {
                shared_util.handleException(e, MER_RESOURCE, "NoSingleSocialRaiseException");
            } else if (e instanceof NoMarriedRaiseException) {
                shared_util.handleException(e, MER_RESOURCE, "NoMarriedRaiseException");
            } else {
                shared_util.handleException(e, MER_RESOURCE, "save_unsuccessfull");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnRaiseDecision(params);
            shared_util.handleException(e, MER_RESOURCE, "save_unsuccessfull");
        }
        return empSalElmSerial;
    }

    private ISalRaiseDecisionParamsDTO fillDecisionParams() {
      ISalRaiseDecisionParamsDTO decisionParams = new SalRaiseDecisionParamsDTO();
//        ISalEmpWifesDTO salEmpWifesDTO = (ISalEmpWifesDTO)getSelectedDTOS().get(0);
//
//        decisionParams.setDecisionTextKey("add_wife_decision_text");
//        decisionParams.setEmployeesDTO(salEmpWifesDTO.getEmployeesDTO());
//        decisionParams.setFinancialDegree(getDegree());
//        decisionParams.setRelName(salEmpWifesDTO.getKwtCitizensResidentsDTO().getFullName());
//        Float raiseValue = null;
//        try {
//            raiseValue =
//                    ((ISalEmpWifesClient)getClient()).getMarriedRaiseValue(Long.valueOf(salEmpWifesDTO.getEmployeeKey()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        decisionParams.setRaiseValue(raiseValue);
//        decisionParams.setAllowanceDate(salEmpWifesDTO.getAllowanceDate());
//        decisionParams.setEndDate(salEmpWifesDTO.getStopDate());
//        decisionParams.setAddRaise(true);
//        Long subjectCode = IMerConstant.Raise_Grant_Decision_Code;
//        try {
//            subjectCode = UtilBean.getMappedCodeBySysSettingCode(IMerConstant.Raise_Grant_Decision_Code);
//        } catch (Exception e) {
//            // TODO: Add catch code
//            e.printStackTrace();
//        }
//        decisionParams.setDecSubjectCode(IMerConstant.Raise_Grant_Decision_Code);
        return decisionParams;
    }

    private Object fillSaveStateParams() {
        Map params = new HashMap();
        params.put("pageDTO", getPageDTO());
        params.put("wifeselectedDTOS", getSelectedDTOS());
        params.put("civilId", getCivilId());
        params.put("showSuspendPartAddDiv", showSuspendPartAddDiv);
        params.put("civilExist", isCivilExist());
        params.put("validCivilId", isValidCivilId());
        params.put("empHired", isEmpHired());
        params.put("payrollInfoExist", isPayrollInfoExist());
        params.put("degree", getDegree());
        params.put("employeesDTO", getEmployeesDTO());
        params.put("singleSelection", isSingleSelection());
        //params.put("myTableData", getPageDTO());
        params.put("wifehighlightedDTOS", getHighlightedDTOS());
        params.put("searchMode", isSearchMode());
        params.put("fullColumnName", getFullColumnName());
        params.put("sortAscending", isSortAscending());
        params.put("divValidCivil", divValidCivil);
        params.put("divCivilExist", divCivilExist);
        //params.put("hasSameParentID", hasSameParentID);
        params.put("divCivilId", divCivilId);
        params.put("wifeselectedRadio", getSelectedRadio());

        // merRaiseMaintainBean save states
        params.put("mrmb_detailFilterMethod", merRaiseMaintainBean.getDetailFilterMethod());
        params.put("mrmb_detailResetMethod", merRaiseMaintainBean.getDetailResetMethod());
        params.put("mrmb_notDefinedGenderTypeFlag", merRaiseMaintainBean.isNotDefinedGenderTypeFlag());
        params.put("mrmb_maritalStatusSingle", merRaiseMaintainBean.isMaritalStatusSingle());

//        // decisionMaintainBean save states
//        params.put("dmb_nextNavigationCase", decisionMaintainBean.getNextNavigationCase());
//        params.put("dmb_previousNavigationCase", decisionMaintainBean.getPreviousNavigationCase());
//        params.put("dmb_finishNavigationCase", decisionMaintainBean.getFinishNavigationCase());
//        params.put("dmb_currentNavigationCase", decisionMaintainBean.getCurrentNavigationCase());
//        params.put("dmb_currentStep", decisionMaintainBean.getCurrentStep());
//        params.put("dmb_maintainMode", decisionMaintainBean.getMaintainMode());

        return params;
    }

    private void restoreSaveStateParams(Object obj) {
        if (obj != null) {
            try {
                Map params = (Map)obj;

                setPageDTO((IBasicDTO)params.get("pageDTO"));
                setSelectedDTOS((List)params.get("wifeselectedDTOS"));
                setCivilId((String)params.get("civilId"));
                merRaiseMaintainBean.setCivilId((String)params.get("civilId"));
                setShowSuspendPartAddDiv((Boolean)params.get("showSuspendPartAddDiv"));
                setCivilExist((Boolean)params.get("civilExist"));
                merRaiseMaintainBean.setCivilExist((Boolean)params.get("civilExist"));
                setValidCivilId((Boolean)params.get("validCivilId"));
                merRaiseMaintainBean.setValidCivilId((Boolean)params.get("validCivilId"));
                setEmpHired((Boolean)params.get("empHired"));
                merRaiseMaintainBean.setEmpHired((Boolean)params.get("empHired"));
                setPayrollInfoExist((Boolean)params.get("payrollInfoExist"));
                merRaiseMaintainBean.setPayrollInfoExist((Boolean)params.get("payrollInfoExist"));
                setDegree((String)params.get("degree"));
                merRaiseMaintainBean.setDegree((String)params.get("degree"));
                setEmployeesDTO((IEmployeesDTO)params.get("employeesDTO"));
                merRaiseMaintainBean.setEmployeesDTO((IEmployeesDTO)params.get("employeesDTO"));
                setSingleSelection((Boolean)params.get("singleSelection"));
                setHighlightedDTOS((List)params.get("wifehighlightedDTOS"));
                setSearchMode((Boolean)params.get("searchMode"));
                setFullColumnName((String)params.get("fullColumnName"));
                setSortAscending((Boolean)params.get("sortAscending"));
                setDivValidCivil((Boolean)params.get("divValidCivil"));
                //setHasSameParentID((Boolean)params.get("hasSameParentID"));
                setDivCivilId((String)params.get("divCivilId"));
                setSelectedRadio((String)params.get("wifeselectedRadio"));

                // merRaiseMaintainBean save states
                merRaiseMaintainBean.setDetailFilterMethod((String)params.get("mrmb_detailFilterMethod"));
                merRaiseMaintainBean.setDetailResetMethod((String)params.get("mrmb_detailResetMethod"));
                merRaiseMaintainBean.setNotDefinedGenderTypeFlag((Boolean)params.get("mrmb_notDefinedGenderTypeFlag"));
                merRaiseMaintainBean.setMaritalStatusSingle((Boolean)params.get("mrmb_maritalStatusSingle"));
//
//                // decisionMaintainBean save states
//                decisionMaintainBean.setNextNavigationCase((String)params.get("dmb_nextNavigationCase"));
//                decisionMaintainBean.setPreviousNavigationCase((String)params.get("dmb_previousNavigationCase"));
//                decisionMaintainBean.setFinishNavigationCase((String)params.get("dmb_finishNavigationCase"));
//                decisionMaintainBean.setCurrentNavigationCase((String)params.get("dmb_currentNavigationCase"));
//                decisionMaintainBean.setCurrentStep((String)params.get("dmb_currentStep"));
//                decisionMaintainBean.setMaintainMode((Integer)params.get("dmb_maintainMode"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void initializeDecisionDTO() {
//        DecisionMaintainBean decisionMaintain = (DecisionMaintainBean)evaluateValueBinding("decisionMaintainBean");
//        if (decisionMaintain.getPageDTO() != null) {
//            DecisionMainDataMaintain decisionMainData =
//                (DecisionMainDataMaintain)evaluateValueBinding("decisionMainDataMaintainBean");
//            DecisionEmployeesMaintain decisionEmployeesMaintain =
//                (DecisionEmployeesMaintain)evaluateValueBinding("decisionEmployeesMaintainBean");
//            decisionEmployeesMaintain.setShowBarMainData(false);
//            ((IDecisionsDTO)decisionMaintain.getPageDTO()).setSubjectsDTOKey(IRegConstants.REGULATION_SUBJECT_APPOINTMENT_DECISION.toString()); //TODO
//            decisionMainData.setShowLovDivFlag(true);
//            try {
//                decisionMainData.setTypesDTOKey(getManagedConstantsBean().getDecisionAdministrationType());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    public String empAddDecisoin() {
//        if (getIntegrationBean().getSelectedDTOTo() != null && getIntegrationBean().getSelectedDTOTo().size() > 0) {
//            try {
//                RegClientFactory.getDecisionsClient().add(getIntegrationBean().getSelectedDTOTo().get(0));
//                getSharedUtil().handleSuccMsg("com.beshara.csc.hr.mer.presentation.resources.mer",
//                                              "Decision_Add_Success");
//            } catch (Exception e) {
//                getSharedUtil().handleException(e, "com.beshara.csc.hr.mer.presentation.resources.mer",
//                                                "Decision_Add_Failure");
//            }
//        }
        return "raiseWivesNav";
    }

    public void saveSelectedDecision() {
//        System.out.println("saveSelectedDecision");
//        if (decisionIntegration.decisionsDTO != null && decisionIntegration.decisionsDTO.getCode() != null &&
//            merRaiseMaintainBean.getEmployeesDTO() != null &&
//            merRaiseMaintainBean.getEmployeesDTO().getCode() != null) {
//            try {
//                List empDecisionList = new ArrayList();
//                IEmpDecisionsDTO empDecisionsDTO = RegDTOFactory.createEmpDecisionsDTO();
//                empDecisionsDTO.setEmployeesDTO(merRaiseMaintainBean.getEmployeesDTO());
//                empDecisionsDTO.setDecisionsDTO(decisionIntegration.decisionsDTO);
//                empDecisionList.add(empDecisionsDTO);
//                ResultDTO resultDTO;
//                resultDTO =
//                        (ResultDTO)(((List)RegClientFactory.getEmpDecisionsClient().joinToDecision(empDecisionList)).get(0));
//                if (resultDTO != null && resultDTO.getStatus().equals(ISystemConstant.ITEM_ADDED)) {
//                    getSharedUtil().handleSuccMsg("com.beshara.csc.hr.mer.presentation.resources.mer",
//                                                  "Decision_Join_Success");
//                } else if (resultDTO != null && resultDTO.getStatus().equals(ISystemConstant.ITEM_ALRAEDY_EXIST)) {
//                    getSharedUtil().handleException(new Exception(),
//                                                    "com.beshara.csc.hr.mer.presentation.resources.mer",
//                                                    "Decision_Already_Joined");
//                } else {
//                    getSharedUtil().handleException(new Exception(),
//                                                    "com.beshara.csc.hr.mer.presentation.resources.mer",
//                                                    "Decision_Join_Failure");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else
//            getSharedUtil().handleException(new Exception(), "com.beshara.csc.hr.mer.presentation.resources.mer",
//                                            "Decision_Join_Failure");
    }

    public void setDecisionIntegration(DecisionJoinBean decisionIntegration) {
        this.decisionIntegration = decisionIntegration;
    }

    public DecisionJoinBean getDecisionIntegration() {
        return decisionIntegration;
    }

    public void setCheckCalOrPaid(boolean checkCalOrPaid) {
        this.checkCalOrPaid = checkCalOrPaid;
    }

    public boolean isCheckCalOrPaid() {
        return checkCalOrPaid;
    }

    private boolean validateCalSalaryOrPaySalary(Long civilId, Date fromDate) {
        try {
            if (checkPayOrder(civilId, fromDate)) {
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(MER_RESOURCE, "salaryPayed"));
                checkCalOrPaid = true;

                return false;

            } else if (checkCalcSalary(civilId, fromDate)) {
                doSuspendWife = false;
                checkCalOrPaid = false;
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(MER_RESOURCE, "salaryCalculated"));
                return false;

            }
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e);
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e);
        }

        return true;
    }

    private boolean validateCalSalaryOrPaySalaryForSuspend(Long civilId, Date fromDate) {
        try {
            if (checkPayOrder(civilId, fromDate)) {
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(MER_RESOURCE, "salaryPayed"));
                doSuspendWife = true;

                return false;

            } else if (checkCalcSalary(civilId, fromDate)) {
                doSuspendWife = false;
                checkCalOrPaid = false;
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(MER_RESOURCE, "salaryCalculated"));
                return false;
            }
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e);
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e);
        }

        return true;
    }

    private boolean checkCalcSalary(Long civilId, Date fromDate) throws DataBaseException, SharedApplicationException {
        ISalEmpMonSearchCriteriaDTO dto = SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
        dto.setCivilId(civilId);
        dto.setFromDate(fromDate);

        return SalClientFactory.getSalElementValidationClient().validateCalcSalary(dto);
    }

    private boolean checkPayOrder(Long civilId, Date fromDate) throws DataBaseException, SharedApplicationException {
        ISalSheetDetailsSearchCriteriaDTO dto = SalDTOFactory.createSalSheetDetailsSearchCriteriaDTO();
        dto.setCivilId(civilId);
        dto.setFromDate(fromDate);

        return SalClientFactory.getSalElementValidationClient().validatePayOrder(dto);
    }

    public void clearErrSucessMsgs() {
        getSharedUtil().setErrMsgValue(null);
        getSharedUtil().setSuccessMsgValue(null);
        checkCalOrPaid = false;
    }

    public void setDoSuspendWife(boolean suspendAction) {
        this.doSuspendWife = suspendAction;
    }

    public boolean isDoSuspendWife() {
        return doSuspendWife;
    }

    public void setMaritalStatusIsSingle(boolean maritalStatusIsSingle) {
        this.maritalStatusIsSingle = maritalStatusIsSingle;
    }

    public boolean isMaritalStatusIsSingle() {
        return maritalStatusIsSingle;
    }

    public void setReplyMsgValue(String replyMsgValue) {
        this.replyMsgValue = replyMsgValue;
    }

    public String getReplyMsgValue() {
        return replyMsgValue;
    }

    public void setMarriageDate(Date marriageDate) {
        this.marriageDate = marriageDate;
    }

    public Date getMarriageDate() {
        return marriageDate;
    }

    public void setWifeSonInfoDTO(IWifeSonInfoDTO wifeSonInfoDTO) {
        this.wifeSonInfoDTO = wifeSonInfoDTO;
    }

    public IWifeSonInfoDTO getWifeSonInfoDTO() {
        return wifeSonInfoDTO;
    }

    public void setDataRecievedSuccessfully(boolean dataRecievedSuccessfully) {
        this.dataRecievedSuccessfully = dataRecievedSuccessfully;
    }

    public boolean isDataRecievedSuccessfully() {
        return dataRecievedSuccessfully;
    }

    public boolean isNotValidWife() {

        if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty() &&
            ((ISalEmpWifesDTO)getSelectedDTOS().get(0)).getStatus().equals(IMerConstant.NOT_ACTIVE)) {
            return false;
        }
        return true;
    }
    public static RaiseWivesBean getInstance() {
        return (RaiseWivesBean)JSFHelper.getValue("raiseWivesBean");
    }

}
