package com.beshara.csc.hr.scp.integration.presentation.backingbean.socialInsuranceForms;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.sal.business.client.ISalSalarySheetsClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalSalarySheetsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalarySheetsSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalarySheetsSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.entity.ISalSalarySheetsEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalSheetStatusEntityKey;
import com.beshara.csc.hr.sal.business.shared.ISALConstants;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class SocialInsuranceFormsBean extends InsuranceFormBaseBean {

    @SuppressWarnings("compatibility:-1569973881076076459")
    private static final long serialVersionUID = 1L;
    private List salarySheets;
    
    private ISalSalarySheetsDTO salSalarySheetsDTO;
    
    private String finalMessage;
//    private List<IBasicDTO> paymentOrderDetailsList = new ArrayList<IBasicDTO>();
//    private int paymentOrderDetailsListSize;
    private Long totalEmpsCount = 0L;
    private BigDecimal totalEmpsMoney = BigDecimal.ZERO;
   
    private String signatureStr;
    private String signature1;
    private String signatureStr1;
    private String accountNo;
    private String selDedToMinCode;
//    private ISalDeductToMinistriesDTO selDedToMin;
    private List accountNoList;
//    private Long reciever = 1L;
//    private Long reciever1 = 1L;
//    private Long reciever2 = 2L;


//    private boolean cancelOrderDisabled ;

    private static final String BEAN_NAME = "socialInsuranceFormsBean";
    private Boolean IS_INSURANCE_REPORT_CODE_166_REP;
    
    private Long mode=IScpConstants.INSURRANCE_FORM_TYPE_166;
     
//    private static final String PAY_ORDER_REPORT_CODE = "392";
//    private static final String SHEET_REPORT_CODE = "391";
//    private static final String LOCAL_BANKS_REPORT_CODE = "829";
//    private static final Long SOCIAL_INSUR_MIN_CODE = 73L;

        public static SocialInsuranceFormsBean getInstance() {
        
            FacesContext ctx = FacesContext.getCurrentInstance();
            Application app = ctx.getApplication();
            return (SocialInsuranceFormsBean)app.createValueBinding("#{" + BEAN_NAME + "}").getValue(ctx);
        }
    public SocialInsuranceFormsBean() {
         super();
        setCustomDiv1("m2mEditDivClass");
        setCustomDiv1TitleKey("module_div_title");
      
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowSearch(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        //app.setShowDelAlert(true);
        app.setShowbar(true);
        //app.setShowDelConfirm(true);
        app.setShowContent1(true);
        app.setShowCustomDiv1(true);
        //app.setShowCustomDiv2(true);
        //app.setShowCustomDiv3(true);
        //app.setShowIntegrationDiv1(true);
        app.setShowWizardBar(true);
        return app;
    }

 
  
    
    private void fillSearchCriteria(){
        
            InsuranceFormBaseBean baseBean=InsuranceFormBaseBean.getInstance();
            setPageDTO(baseBean.getPageDTO());
        
        }
        
        
    public void search() {
        try {
            salarySheets = new ArrayList();
            Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
   
            setSelectedDTOS(new ArrayList());
            setSelectedRadio("");
  
           fillSearchCriteria();
          ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setFormType(IScpConstants.INSURRANCE_FORM_TYPE_166);
            List dataList = ((ISalSalarySheetsClient)getClient()).getSocialInsuranceFormsData(getPageDTO());
            salarySheets = (List)dataList.get(0);
            salSalarySheetsDTO=SalDTOFactory.createSalSalarySheetsDTO();
            salSalarySheetsDTO = (ISalSalarySheetsDTO)dataList.get(1);
        } catch (Exception e) {
            e.printStackTrace();
            salarySheets = new ArrayList();
            salSalarySheetsDTO=SalDTOFactory.createSalSalarySheetsDTO();
        }
        setMyTableData(salarySheets);
        repositionPage(0);
        
    }

    public boolean isForm103() {
        if (mode .equals( IScpConstants.INSURRANCE_FORM_TYPE_103)) {
            return true;

        }
        return false;
    }
    
   

    public void changeAlertMsg() {
        finalMessage = getSharedUtil().getResourceBundle(RESOURCE_BUNDLE).getString("PayOrderCancel_Alert");
        Long month = ((ISalSalarySheetsDTO)getSelectedDTOS().get(0)).getSalaryMonth();
        Long year = ((ISalSalarySheetsDTO)getSelectedDTOS().get(0)).getYearsDTO().getYearCode();
        String name = ((IBasicDTO)getMonths().get((int)(long)month - 1)).getName();
        finalMessage = finalMessage.replaceFirst("X", name);
        finalMessage = finalMessage.replaceFirst("Y", year.toString());
        //getSharedUtil().setErrMsgValue(finalMessage);
    }

    public void update() throws DataBaseException, SharedApplicationException {
        super.setSelectedPageDTO(getSelectedDTOS().get(0));
        IEntityKey key = new SalSheetStatusEntityKey(IScpConstants.STATUS_INACTIVE);
        ((ISalSalarySheetsDTO)super.getSelectedPageDTO()).getSalSheetStatusDTO().setCode(key);
        super.executeEdit();
        search();
        getSharedUtil().handleSuccMsg(RESOURCE_BUNDLE, "PayOrderCancel_Success");
    }

    public void cancelPayOrder() {
        try {
            super.setSelectedPageDTO(getSelectedDTOS().get(0));
            IEntityKey key = new SalSheetStatusEntityKey(IScpConstants.STATUS_INACTIVE);
            ((ISalSalarySheetsDTO)super.getSelectedPageDTO()).getSalSheetStatusDTO().setCode(key);
            SalClientFactory.getSalSalarySheetsClient().cancelPayOrder(getSelectedPageDTO());
            search();
            getSharedUtil().handleSuccMsg(RESOURCE_BUNDLE, "PayOrderCancel_Success");
        } catch (Exception e) {
            e.printStackTrace();
            getSharedUtil().handleException(e);
        }
    }

    /*public void showPayOrderDetails(ActionEvent ae) {
     
        ae = null;
        Long sheetNo = null;
        signature = null;
        signatureStr = null;
        signature1 = null;
        signatureStr1 = null;
        totalEmpsCount = 0L;
        totalEmpsMoney = BigDecimal.ZERO;

        if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty()) {
            sheetNo = ((ISalSalarySheetsEntityKey)(getSelectedDTOS().get(0)).getCode()).getSalsheetCode();
        }
        System.out.println(">>>>>  showPayOrderDetails >>>> sheetNo = " + sheetNo);
        try {
            paymentOrderDetailsList =
                    SalClientFactory.getSalSheetAccumulativesClient().getBySheetCode(Long.valueOf(sheetNo));
            //paymentOrderCancelList = (List<IBasicDTO>)getSharedUtil().deepCopyObject(paymentOrderDetailsList);
            // setMyDataTable((HtmlDataTable)paymentOrderDetailsList);
            for (IBasicDTO dto1 : paymentOrderDetailsList) {
                ISalSheetAccumulativesDTO dto = (ISalSheetAccumulativesDTO)dto1;
                totalEmpsCount = totalEmpsCount + dto.getBankCount();
                totalEmpsMoney = totalEmpsMoney.add(dto.getBankTotal());
            }
            changeAlertMsg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
   /* public void printPaymentOrder() {
        ISalSheetAccumulativesDTO dto = (ISalSheetAccumulativesDTO)getPayOrderDtlsDataTable().getRowData();
        reportUrlLink = ISALConstants.PRINT_PAYMENT_ORDER_LINK;
        Long catCode = CSCSecurityInfoHelper.getLoggedInCategoryCode(); // 1st param
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode(); // 2nd param
        Long sheetCode = dto.getSalSheetCode(); // 3th param
        Long bankCode = dto.getBankCode(); // 4th param

        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, PAY_ORDER_REPORT_CODE);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, catCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, sheetCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, bankCode.toString());
        System.out.println("generated params -----> " + reportUrlLink);

    }*/

    /*public void printSheet() {
        ISalSheetAccumulativesDTO dto = (ISalSheetAccumulativesDTO)getPayOrderDtlsDataTable().getRowData();
        reportUrlLink = ISALConstants.PRINT_SHEET_LINK;
        Long catCode = CSCSecurityInfoHelper.getLoggedInCategoryCode(); // 1st param
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode(); // 2nd param
        Long yearCode = ((ISalSalarySheetsDTO)getSelectedDTOS().get(0)).getYearsDTO().getYearCode(); // 3th param
        Long monthCode = ((ISalSalarySheetsDTO)getSelectedDTOS().get(0)).getSalaryMonth(); // 4th param
        Long sheetCode = dto.getSalSheetCode(); // 5th param
        Long bankCode = dto.getBankCode(); // 6th param

        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, SHEET_REPORT_CODE);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, catCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, yearCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, monthCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, sheetCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, bankCode.toString());
        System.out.println("generated params -----> " + reportUrlLink);

    }*/

    public void printSocialInsuranceForm166() {
        reportUrlLink = ISALConstants.PRINT_SPECIAL_INSTALLMENTS_AND_PARTICIPANTS_PAY_ORDER_LINK;    
        System.out.println(reportUrlLink);
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();        
        ISalarySheetsSearchCriteriaDTO salarySheetsSearchCriteriaDTO = ( (ISalarySheetsSearchCriteriaDTO)getPageDTO() );
        minCode = salarySheetsSearchCriteriaDTO.getMinCode();       
        String month = salarySheetsSearchCriteriaDTO.getMonth();
        Long year = salarySheetsSearchCriteriaDTO.getYear();        
        Double mainInsuranceParticipation = salarySheetsSearchCriteriaDTO.getMainInsuranceParticipation()!=null ? salarySheetsSearchCriteriaDTO.getMainInsuranceParticipation() : 0D;
        Double supplementaryInsuranceParticipation = salarySheetsSearchCriteriaDTO.getSupplementaryInsuranceParticipation()!=null ? salarySheetsSearchCriteriaDTO.getSupplementaryInsuranceParticipation() : 0D;
        Double specialInstallments = salarySheetsSearchCriteriaDTO.getSpecialInstallments()!=null ? salarySheetsSearchCriteriaDTO.getSpecialInstallments() : 0D;
        Double increasePensionsFundParticipation = salarySheetsSearchCriteriaDTO.getIncreasePensionsFundParticipation()!=null ? salarySheetsSearchCriteriaDTO.getIncreasePensionsFundParticipation() : 0D;
        Double financialRewardFundParticipation = salarySheetsSearchCriteriaDTO.getFinancialRewardFundParticipation()!=null ? salarySheetsSearchCriteriaDTO.getFinancialRewardFundParticipation() : 0D;

        SharedUtilBean sharedUtilBean = getSharedUtil();
        String signature = ((SalarySheetsSearchCriteriaDTO)getPageDTO()).getSignature();
        String strSign = "";
        String jobName=""; 
        if(signature == null || signature.equals("-100")){
            //strSign = signatureStr;
        }
        else if(signature.equals("1"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "emp1" );
        else if(signature.equals("2"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "emp2" );
        else if(signature.equals("3"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "emp3" );
        else if(signature.equals("4"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "emp4" );
        else if(signature.equals("5"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "emp5" );

        if(strSign!=null && strSign.length()>0 && !strSign.equals("")){
            String[] strArr = strSign.split("-");
            if(strArr != null && strArr.length > 1){
                strSign = strArr[0]; 
                jobName=strArr[1]; 
            }   
        }
        
        ISalSalarySheetsDTO salSheet = (ISalSalarySheetsDTO)getSelectedDTOS().get(0);
        Long sheetCode = ((ISalSalarySheetsEntityKey)salSheet.getCode()).getSalsheetCode(); // 4th param
        String bnkName=salSheet.getBnkName();

        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, INSURANCE_REPORT_CODE_166);
        //if(signature1 == null)signature1 = "";
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, jobName);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, strSign);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, bnkName);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, month);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, year.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, sheetCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, specialInstallments.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, financialRewardFundParticipation.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, increasePensionsFundParticipation.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, supplementaryInsuranceParticipation.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, mainInsuranceParticipation.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        
        System.out.println("generated params -----> " + reportUrlLink);

    }
    
    public void printSocialInsuranceForm167() {
        reportUrlLink = ISALConstants.PRINT_SPECIAL_INSTALLMENTS_AND_REPLACEMENT_PAY_ORDER_LINK;    
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();        
        ISalarySheetsSearchCriteriaDTO salarySheetsSearchCriteriaDTO = ( (ISalarySheetsSearchCriteriaDTO)getPageDTO() );
        minCode = salarySheetsSearchCriteriaDTO.getMinCode();       
        String month = salarySheetsSearchCriteriaDTO.getMonth();
        Long year = salarySheetsSearchCriteriaDTO.getYear();        
       

        SharedUtilBean sharedUtilBean = getSharedUtil();
        String signature = ((SalarySheetsSearchCriteriaDTO)getPageDTO()).getSignature();
        String strSign = "";
        if(signature == null || signature.equals("-100")){
            //strSign = signatureStr;
        }
        else if(signature.equals("1"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "emp1" );
        else if(signature.equals("2"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "emp2" );
        else if(signature.equals("3"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "emp3" );
        else if(signature.equals("4"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "emp4" );
        else if(signature.equals("5"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "emp5" );
        
        if(strSign!=null && strSign.length()>0 && !strSign.equals("")){
            String[] strArr = strSign.split("-");
            if(strArr != null && strArr.length > 1){
                strSign = strArr[1]; 
            }   
        }
        
        ISalSalarySheetsDTO salSheet = (ISalSalarySheetsDTO)getSelectedDTOS().get(0);
        Long sheetCode = ((ISalSalarySheetsEntityKey)salSheet.getCode()).getSalsheetCode(); // 4th param
        //String bnkName=salSheet.getBnkName();
 
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, INSURANCE_REPORT_CODE_167);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        //if(signature1 == null)signature1 = "";
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, strSign);       
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, month);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, year.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, sheetCode.toString()); 
        System.out.println("generated params -----> " + reportUrlLink);

    }

    /*public void printLocalBanksPayOrder() {
        reportUrlLink = ISALConstants.PRINT_LOCAL_BANKS_LINK;
        //Long catCode = CSCSecurityInfoHelper.getLoggedInCategoryCode(); // 1st param
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode(); // 2nd param
        SharedUtilBean sharedUtilBean = getSharedUtil();

        ISalSalarySheetsDTO salSheet = (ISalSalarySheetsDTO)getSelectedDTOS().get(0);
        Long sheetCode = ((ISalSalarySheetsEntityKey)salSheet.getCode()).getSalsheetCode(); // 4th param
        String accountNo = salSheet.getAccountNo();
        
        String strSign = "";
        if(signature.equals("-100"))
            strSign = signatureStr;
        else if(signature.equals("1"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "general_manager" );
        else if(signature.equals("2"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "diwan_boss" );
        else if(signature.equals("3"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "undersecretary" );
        
        
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, LOCAL_BANKS_REPORT_CODE);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, strSign);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, accountNo);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, sheetCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, reciever.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());

        System.out.println("generated params -----> " + reportUrlLink);

    }*/

    //########## start of CSC-12438 ###########
    //    public void resetInsurPayOrder(ActionEvent ae) {
    //        ae = null;
    //        reciever = 1L;
    //        selDedToMinCode = null;
    //        signature = null;
    //        signatureStr = null;
    //    }

    public void showInsuranceForm166(ActionEvent ae) {
        if(getPageDTO()!=null)
        {
            ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setSupplementaryInsuranceParticipation(0D);
            ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setFinancialRewardFundParticipation(0D);
            ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setIncreasePensionsFundParticipation(0D);
            ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setMainInsuranceParticipation(0D);
            ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setSpecialInstallments(0D);
        }
        ae = null;
        Long sheetNo = null;
        //signature = null;
        signatureStr = null;
        //signature1 = null;
        signatureStr1 = null;
        totalEmpsCount = 0L;
        totalEmpsMoney = BigDecimal.ZERO;      
        selDedToMinCode = null;
        IS_INSURANCE_REPORT_CODE_166_REP=true;
//        //selDedToMin = null;
        if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty()) {
            sheetNo = ((ISalSalarySheetsEntityKey)(getSelectedDTOS().get(0)).getCode()).getSalsheetCode();
        }
        System.out.println(">>>>>  showInsurancePayOrder >>>> sheetNo = " + sheetNo);
       
    }
    
    public void showInsuranceForm167(ActionEvent ae) {
      
        ae = null;
        Long sheetNo = null;
        //signature = null;
        signatureStr = null;
        //signature1 = null;
        signatureStr1 = null;
        totalEmpsCount = 0L;
        totalEmpsMoney = BigDecimal.ZERO;
        //reciever = 1L;
        selDedToMinCode = null;
        IS_INSURANCE_REPORT_CODE_166_REP=false;
        //selDedToMin = null;
        if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty()) {
            sheetNo = ((ISalSalarySheetsEntityKey)(getSelectedDTOS().get(0)).getCode()).getSalsheetCode();
        }
        System.out.println(">>>>>  showInsurancePayOrder >>>> sheetNo = " + sheetNo);
       
    }


   

    public void setSalarySheets(List salarySheets) {
        this.salarySheets = salarySheets;
    }

    public List getSalarySheets() {
        return salarySheets;
    }

    @Override
    public Integer getListSize() {
        if (salarySheets != null) {
            return salarySheets.size();
        }
        return 0;
    }

    
  


    public void setTotalEmpsCount(Long totalEmpsCount) {
        this.totalEmpsCount = totalEmpsCount;
    }

    public Long getTotalEmpsCount() {
        return totalEmpsCount;
    }

    public void setTotalEmpsMoney(BigDecimal totalEmpsMoney) {
        this.totalEmpsMoney = totalEmpsMoney;
    }

    public BigDecimal getTotalEmpsMoney() {
        return totalEmpsMoney;
    }

  

    public void setSignatureStr(String signatureStr) {
        this.signatureStr = signatureStr;
    }

    public String getSignatureStr() {
        return signatureStr;
    }


    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNoList(List accountNoList) {
        this.accountNoList = accountNoList;
    }

    public List getAccountNoList() {
        if (accountNoList == null || accountNoList.isEmpty()) {
            try {
                accountNoList =
                        SalClientFactory.getSalDeductToMinistriesClient().getDeductToMinistriesByMinCode(CSCSecurityInfoHelper.getLoggedInMinistryCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return accountNoList;
    }

    /*public void fillSalDeductToMinistriesDTO() {
        if (selDedToMinCode != null && !selDedToMinCode.trim().equals("")) {
            String keys[] = selDedToMinCode.split("\\*");

            SalDeductToMinistriesEntityKey sdtmEK =
                (SalDeductToMinistriesEntityKey)SalEntityKeyFactory.createSalDeductToMinistriesEntityKey(new Long(keys[0]),
                                                                                                         new Long(keys[1]),
                                                                                                         keys[2]);
            try {
                selDedToMin =
                        (ISalDeductToMinistriesDTO)SalClientFactory.getSalDeductToMinistriesClient().getById(sdtmEK);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        } else {
            selDedToMin = null;
        }
    }*/

    public void setSelDedToMinCode(String selDedToMinCode) {
        this.selDedToMinCode = selDedToMinCode;
    }

    public String getSelDedToMinCode() {
        return selDedToMinCode;
    }

    public void setFinalMessage(String finalMessage) {
        this.finalMessage = finalMessage;
    }

    public String getFinalMessage() {
        return finalMessage;
    }
    
    /*@Override
    public void selectedRadioButton(javax.faces.event.ActionEvent p1) {
        
        try {
            super.selectedRadioButton(p1);
            SalSalarySheetsDTO selected = (SalSalarySheetsDTO)getSelectedDTOS().get(0);
            
            if(selected.getPaymentTypesDTO().getCode().getKey().equals("3"))
            {
                cancelOrderDisabled = true ;
            }else{
                
               cancelOrderDisabled = false ;
                }
        } catch (Exception e) {
            
        }
    }*/

    public void setSignature1(String signature1) {
        this.signature1 = signature1;
    }

    public String getSignature1() {
        return signature1;
    }

    public void setSignatureStr1(String signatureStr1) {
        this.signatureStr1 = signatureStr1;
    }

    public String getSignatureStr1() {
        return signatureStr1;
    }
    
  

    public Long getMultiMeritFlag() {
        return IScpConstants.MON_SAL_PAY_TYPE_OTHERS;
    }
    
//    public void changePaymentType(ActionEvent ae) {
//            ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setDecisionNo(null);
//        }

   

   
    public void setSalSalarySheetsDTO(ISalSalarySheetsDTO salSalarySheetsDTO) {
        this.salSalarySheetsDTO = salSalarySheetsDTO;
    }

    public ISalSalarySheetsDTO getSalSalarySheetsDTO() {
        return salSalarySheetsDTO;
    }

    public void setIS_INSURANCE_REPORT_CODE_166_REP(Boolean IS_INSURANCE_REPORT_CODE_166_REP) {
        this.IS_INSURANCE_REPORT_CODE_166_REP = IS_INSURANCE_REPORT_CODE_166_REP;
    }

    public Boolean getIS_INSURANCE_REPORT_CODE_166_REP() {
        return IS_INSURANCE_REPORT_CODE_166_REP;
    }

    public void setMode(Long mode) {
        this.mode = mode;
    }

    public Long getMode() {
        return mode;
    }
   
}
