package com.beshara.csc.hr.scp.integration.presentation.backingbean.salarysheetenquery;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.sal.business.client.ISalSalarySheetsClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalDeductToMinistriesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalSalarySheetsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalSheetAccumulativesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalarySheetsSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalSalarySheetsDTO;
import com.beshara.csc.hr.sal.business.entity.ISalSalarySheetsEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalDeductToMinistriesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.hr.sal.business.entity.SalSheetStatusEntityKey;
import com.beshara.csc.hr.sal.business.shared.ISALConstants;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.bnk.business.entity.IBanksEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class SalarySheetEnqueryBean extends LookUpBaseBean {
    private static final String RESOURCE_BUNDLE = "com.beshara.csc.hr.scp.integration.presentation.resources.integration";
    private static final String RESOURCE_KEY_MONTH_PRE = "month_key_";
    private transient HtmlDataTable payOrderDtlsDataTable = new HtmlDataTable();
    private List paymentList;
    private List months;
    private List years;
    private List salarySheets;
    private List decisionsList;
    private String reportUrlLink;
    private String reportWindowName;
    private String finalMessage;
    private List<IBasicDTO> paymentOrderDetailsList = new ArrayList<IBasicDTO>();
    // private List<IBasicDTO> paymentOrderCancelList = new ArrayList<IBasicDTO>();
    private int paymentOrderDetailsListSize;
    private Long totalEmpsCount = 0L;
    private BigDecimal totalEmpsMoney = BigDecimal.ZERO;
    private String signature;
    private String signatureStr;
    private String signature1;
    private String signatureStr1;
    private String accountNo;
    private String selDedToMinCode;
    private ISalDeductToMinistriesDTO selDedToMin;
    private List accountNoList;
    private Long reciever = 1L;
    private Long reciever1 = 1L;
    private Long reciever2 = 2L;


    private boolean cancelOrderDisabled = true;

    private static final String REPORT_PARAM_HOLDER = "#p#";
    private static final String INSURANCE_REPORT_CODE = "165";
    private static final String PAY_ORDER_REPORT_CODE = "392";
    private static final String SHEET_REPORT_CODE = "391";
    private static final String LOCAL_BANKS_REPORT_CODE = "829";
    private static final String ALL_LOCAL_BANKS_REPORT_CODE = "1131";
    private static final Long SOCIAL_INSUR_MIN_CODE = 73L;
    private Boolean insuranceRetrieval;
    //    /////////// Start CSC-12439 //////////////////////
    //   MinistriesPaymentOrderBean ministriesPaymentOrderBean = MinistriesPaymentOrderBean.getInstance();
    //    /////////// End CSC-12439 //////////////////////


    
    public SalarySheetEnqueryBean() {
        setPageDTO(SalDTOFactory.createSalarySheetsSearchCriteriaDTO());
        setClient(SalClientFactory.getSalSalarySheetsClient());
        setSelectedPageDTO(SalDTOFactory.createSalSalarySheetsDTO());
        setSingleSelection(true);
        setCustomDiv2TitleKey("local_Bank_Details_Payment");
      
       
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowSearch(false);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(true);
        //app.setShowbar(true);
        app.setShowDelConfirm(true);
        app.setShowContent1(true);
        app.setShowCustomDiv1(true);
        app.setShowCustomDiv2(true);
        app.setShowCustomDiv3(true);
        app.setShowIntegrationDiv1(true);
        app.setShowIntegrationDiv2(true);
        return app;
    }

    public void search() {
        try {
            Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
            ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setBankCode(1L);
            setSelectedDTOS(new ArrayList());
            setSelectedRadio("");
            ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setMinCode(minCode);
            salarySheets = ((ISalSalarySheetsClient)getClient()).search(getPageDTO());
        } catch (Exception e) {
            e.printStackTrace();
            salarySheets = new ArrayList();
        }
        setMyTableData(salarySheets);
        repositionPage(0);
    }    
    public void getAll() throws DataBaseException {
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

    public void showPayOrderDetails(ActionEvent ae) {
     
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
                
                Long bnkCode=((IBanksEntityKey)dto.getBanksDTO().getCode()).getBankCode();
                Long sheetCode=dto.getSalSheetCode();
                System.out.println("bnkCode :"+bnkCode +" sheetCode:"+sheetCode);
                Boolean fileCreated=false;
                try {
                    fileCreated =
                            SalClientFactory.getSalSalarySheetsClient().isSheetCreatedInFileWithSpecificBank(sheetCode,
                                                                                                             bnkCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dto.setFileCreated(fileCreated);
            }
            changeAlertMsg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showDecision(ActionEvent ae) {
        
    }
    public void printPaymentOrder() {
        ISalSheetAccumulativesDTO dto = (ISalSheetAccumulativesDTO)getPayOrderDtlsDataTable().getRowData();
        reportUrlLink = ISALConstants.PRINT_PAYMENT_ORDER_LINK;
        Long catCode = CSCSecurityInfoHelper.getLoggedInCategoryCode(); // 1st param
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode(); // 2nd param
        Long sheetCode = dto.getSalSheetCode(); // 3th param
        Long bankCode = dto.getBankCode(); // 4th param

        SharedUtilBean sharedUtilBean = getSharedUtil();
        String strSign = "";
        if(signature1.equals("-100"))
            strSign = signatureStr1;
        else if(signature1.equals("1"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "general_manager" );
        else if(signature1.equals("2"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "diwan_boss" );
        else if(signature1.equals("3"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "undersecretary" );
        else if(signature1.equals("4"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "public_works_and_baladya_minister" );
        else if(signature1.equals("5"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "diawn_amiry_agent" );
        
        
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, PAY_ORDER_REPORT_CODE);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, catCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, sheetCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, bankCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, strSign);

        System.out.println("generated params -----> " + reportUrlLink);

    }

    public void printSheet() {
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

    }

    public void printPaymentOrderInsurance() {
        reportUrlLink = ISALConstants.PRINT_PAYMENT_ORDER_INSURANCE_LINK;
        //Long catCode = CSCSecurityInfoHelper.getLoggedInCategoryCode(); // 1st param
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode(); // 2nd param
        //String minAccount = selDedToMin.getAccountNo(); // 3th param
        List<IBasicDTO> socialInsurDTOsList = null;
        String _paramsP_ACC_2 = null;   // input from DB HR_SAL_DEDUCT_TO_MINISTRIES , min_code= 73
        String _paramsP_ACC_1 = null;  // input from Div
        try {
            socialInsurDTOsList =
                    SalClientFactory.getSalDeductToMinistriesClient().getDeductToMinistriesByMinCode(SOCIAL_INSUR_MIN_CODE);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        if (socialInsurDTOsList != null && !socialInsurDTOsList.isEmpty() && socialInsurDTOsList.size() == 1) {
            _paramsP_ACC_2 = ((ISalDeductToMinistriesDTO)socialInsurDTOsList.get(0)).getAccountNo();
        }
        if (selDedToMinCode != null && !selDedToMinCode.trim().equals("")) {
            String keys[] = selDedToMinCode.split("\\*");
            _paramsP_ACC_1 = keys[2];
        }
        
        SharedUtilBean sharedUtilBean = getSharedUtil();
        String strSign = "";
        if(signature.equals("-100"))
            strSign = signatureStr;
        else if(signature.equals("1"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "general_manager" );
        else if(signature.equals("2"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "diwan_boss" );
        else if(signature.equals("3"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "undersecretary" );
        else if(signature.equals("4"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "public_works_and_baladya_minister" );
        else if(signature.equals("5"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "diawn_amiry_agent" );
        ISalSalarySheetsDTO salSheet = (ISalSalarySheetsDTO)getSelectedDTOS().get(0);
        Long sheetCode = ((ISalSalarySheetsEntityKey)salSheet.getCode()).getSalsheetCode(); // 4th param

        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, INSURANCE_REPORT_CODE);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, strSign);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, _paramsP_ACC_2);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, _paramsP_ACC_1);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, salSheet.getSalaryMonth().toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, reciever.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, sheetCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, salSheet.getYearsKey());
        


        System.out.println("generated params -----> " + reportUrlLink);

    }

    public void printCentralBankPayOrder() {
        reportUrlLink = ISALConstants.PRINT_LOCAL_BANKS_LINK;
        //Long catCode = CSCSecurityInfoHelper.getLoggedInCategoryCode(); // 1st param
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode(); // 2nd param
        SharedUtilBean sharedUtilBean = getSharedUtil();

        ISalSalarySheetsDTO salSheet = (ISalSalarySheetsDTO)getSelectedDTOS().get(0);
        Long sheetCode = ((ISalSalarySheetsEntityKey)salSheet.getCode()).getSalsheetCode(); // 4th param
        String accountNo = salSheet.getAccountNo();
        
        String strSign = "";
        if(signature1.equals("-100"))
            strSign = signatureStr1;
        else if(signature1.equals("1"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "general_manager" );
        else if(signature1.equals("2"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "diwan_boss" );
        else if(signature1.equals("3"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "undersecretary" );
        else if(signature1.equals("4"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "public_works_and_baladya_minister" );
        else if(signature1.equals("5"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "diawn_amiry_agent" );
        
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, LOCAL_BANKS_REPORT_CODE);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, strSign);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, accountNo);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, sheetCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, reciever.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());

        System.out.println("generated params -----> " + reportUrlLink);

    }
    
    public void printLocalBanksPayOrder() {
        reportUrlLink = ISALConstants.PRINT_ALL_LOCAL_BANKS_LINK;
        Long catCode = CSCSecurityInfoHelper.getLoggedInCategoryCode(); // 1st param
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode(); // 2nd param
        SharedUtilBean sharedUtilBean = getSharedUtil();

        ISalSalarySheetsDTO salSheet = (ISalSalarySheetsDTO)getSelectedDTOS().get(0);
        Long sheetCode = ((ISalSalarySheetsEntityKey)salSheet.getCode()).getSalsheetCode(); // 3th param
       
        
        String strSign = "";
        if(signature1.equals("-100"))
            strSign = signatureStr1;
        else if(signature1.equals("1"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "general_manager" );
        else if(signature1.equals("2"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "diwan_boss" );
        else if(signature1.equals("3"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "undersecretary" );
        else if(signature1.equals("4"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "public_works_and_baladya_minister" );
        else if(signature1.equals("5"))
            strSign = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, "diawn_amiry_agent" );
        
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, ALL_LOCAL_BANKS_REPORT_CODE);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, strSign);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, catCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, sheetCode.toString());
        System.out.println("generated params -----> " + reportUrlLink);

    }


    //########## start of CSC-12438 ###########
    //    public void resetInsurPayOrder(ActionEvent ae) {
    //        ae = null;
    //        reciever = 1L;
    //        selDedToMinCode = null;
    //        signature = null;
    //        signatureStr = null;
    //    }

    public void showInsurancePayOrder(ActionEvent ae) {
        ae = null;
        Long sheetNo = null;
        insuranceRetrieval=false;
        Long minCode= CSCSecurityInfoHelper.getLoggedInMinistryCode();
        Long monthCode=null;
        Long yearCode= null;
        signature = null;
        signatureStr = null;
        signature1 = null;
        signatureStr1 = null;
        totalEmpsCount = 0L;
        totalEmpsMoney = BigDecimal.ZERO;
        reciever = 1L;
        selDedToMinCode = null;
        selDedToMin = null;
        
        if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty()) {
            ISalSalarySheetsDTO salSheet = (ISalSalarySheetsDTO)getSelectedDTOS().get(0);
            sheetNo = ((ISalSalarySheetsEntityKey)salSheet.getCode()).getSalsheetCode(); 
            monthCode = salSheet.getSalaryMonth(); 
            yearCode = salSheet.getYearsDTO().getYearCode();
        }
       Long insuranceAmount=null;
        try {
            insuranceAmount =
                    SalClientFactory.getSalSalarySheetsClient().getInsuranceAmount(minCode,sheetNo,monthCode,yearCode);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        if(insuranceAmount!=null && insuranceAmount > 0L){
           insuranceRetrieval=true;
       }
        System.out.println(">>>>>  showInsurancePayOrder >>>> sheetNo = " + sheetNo);
        //        try {
        //            paymentOrderDetailsList =
        //                    SalClientFactory.getSalSheetAccumulativesClient().getBySheetCode(Long.valueOf(sheetNo));
        //            for (IBasicDTO dto1 : paymentOrderDetailsList) {
        //                ISalSheetAccumulativesDTO dto = (ISalSheetAccumulativesDTO)dto1;
        //                totalEmpsCount = totalEmpsCount + dto.getBankCount();
        //                totalEmpsMoney = totalEmpsMoney.add(dto.getBankTotal());
        //            }
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
    }
    //########## end of CSC-12438 ###########   
    public void setPaymentList(List paymentList) {
        this.paymentList = paymentList;
    }

    public List getPaymentList() {
        if (paymentList == null) {
            try {
                paymentList = SalClientFactory.getSalPaymentTypesClient().getPaymentTypes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paymentList;
    }

    public void setMonths(List months) {
        this.months = months;
    }

    public List getMonths() {
        if (months == null) {
            months = new ArrayList();
            IBasicDTO basicDto = null;
            String monthFromBun = null;
            SharedUtilBean sharedUtilBean = getSharedUtil();
            for (int i = 1; i <= 12; i++) {
                basicDto = new BasicDTO();
                basicDto.setCode(new EntityKey(new String[] { "" + i + "" }));
                monthFromBun = sharedUtilBean.messageLocator(RESOURCE_BUNDLE, RESOURCE_KEY_MONTH_PRE + i);
                basicDto.setName(monthFromBun);
                months.add(basicDto);
            }
        }
        return months;
    }

    public void setYears(List years) {
        this.years = years;
    }

    public List getYears() {
        if (years == null) {
            years = new ArrayList();
            try {
                years = InfClientFactory.getYearsClient().getCodeName();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return years;
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

    public void setReportUrlLink(String reportUrlLink) {
        this.reportUrlLink = reportUrlLink;
    }

    public String getReportUrlLink() {
        return reportUrlLink;
    }

    public void setReportWindowName(String reportWindowName) {
        this.reportWindowName = reportWindowName;
    }

    public String getReportWindowName() {
        return reportWindowName;
    }

    public void setPaymentOrderDetailsList(List<IBasicDTO> paymentOrderDetailsList) {
        this.paymentOrderDetailsList = paymentOrderDetailsList;
    }

    public List<IBasicDTO> getPaymentOrderDetailsList() {
        return paymentOrderDetailsList;
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

    public void setPaymentOrderDetailsListSize(int paymentOrderDetailsListSize) {
        this.paymentOrderDetailsListSize = paymentOrderDetailsListSize;
    }

    public int getPaymentOrderDetailsListSize() {
        return paymentOrderDetailsListSize;
    }

    public void setPayOrderDtlsDataTable(HtmlDataTable payOrderDtlsDataTable) {
        this.payOrderDtlsDataTable = payOrderDtlsDataTable;
    }

    public HtmlDataTable getPayOrderDtlsDataTable() {
        return payOrderDtlsDataTable;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignatureStr(String signatureStr) {
        this.signatureStr = signatureStr;
    }

    public String getSignatureStr() {
        return signatureStr;
    }

    public String redirectDedMinistriesPayOrder() {
        MinistriesPaymentOrderBean ministriesPaymentOrderBean = MinistriesPaymentOrderBean.getInstance();
        ministriesPaymentOrderBean.setSalSalarySheetsDTO((ISalSalarySheetsDTO)getSelectedDTOS().get(0));
        return "Ministries_Payment_Order_Page";
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

    public void fillSalDeductToMinistriesDTO() {
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
    }

    public void setSelDedToMin(ISalDeductToMinistriesDTO selDedToMin) {
        this.selDedToMin = selDedToMin;
    }

    public ISalDeductToMinistriesDTO getSelDedToMin() {
        return selDedToMin;
    }

    public void setSelDedToMinCode(String selDedToMinCode) {
        this.selDedToMinCode = selDedToMinCode;
    }

    public String getSelDedToMinCode() {
        return selDedToMinCode;
    }

    public void setReciever1(Long reciever1) {
        this.reciever1 = reciever1;
    }

    public Long getReciever1() {
        return reciever1;
    }

    public void setReciever2(Long reciever2) {
        this.reciever2 = reciever2;
    }

    public Long getReciever2() {
        return reciever2;
    }

    public void setReciever(Long reciever) {
        this.reciever = reciever;
    }

    public Long getReciever() {
        return reciever;
    }

    public void setFinalMessage(String finalMessage) {
        this.finalMessage = finalMessage;
    }

    public String getFinalMessage() {
        return finalMessage;
    }
    
    @Override
    public void selectedRadioButton(javax.faces.event.ActionEvent p1) {
        
        try {
            super.selectedRadioButton(p1);
            SalSalarySheetsDTO selected = (SalSalarySheetsDTO)getSelectedDTOS().get(0);
            
//            if(selected.getPaymentTypesDTO().getCode().getKey().equals("3"))
//            {
//                cancelOrderDisabled = true ;
//            }else{
//                
//               cancelOrderDisabled = false ;
//                }
                Long sheetCode = ((ISalSalarySheetsEntityKey)selected.getCode()).getSalsheetCode();
                decisionsList = ((ISalSalarySheetsClient)getClient()).GetDecisionBySheetCode(sheetCode);
            } catch (Exception e) {
                e.printStackTrace();
                decisionsList = new ArrayList();
            }
        
    }

    public void setCancelOrderDisabled(boolean cancelOrderDisabled) {
        this.cancelOrderDisabled = cancelOrderDisabled;
    }

    public boolean isCancelOrderDisabled() {
        return cancelOrderDisabled;
    }

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
    
    public void changePaymentType(ActionEvent ae) {
            ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setDecisionNo(null);
        }

    public void setDecisionsList(List decisionsList) {
        this.decisionsList = decisionsList;
    }

    public List getDecisionsList() {
        return decisionsList;
    }

    public void setInsuranceRetrieval(Boolean insuranceRetrieval) {
        this.insuranceRetrieval = insuranceRetrieval;
    }

    public Boolean getInsuranceRetrieval() {
        return insuranceRetrieval;
    }
}
