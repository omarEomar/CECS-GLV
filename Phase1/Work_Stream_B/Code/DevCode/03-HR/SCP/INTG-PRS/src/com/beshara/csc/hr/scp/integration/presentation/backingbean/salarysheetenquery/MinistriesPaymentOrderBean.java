package com.beshara.csc.hr.scp.integration.presentation.backingbean.salarysheetenquery;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.sal.business.client.ISalSheetDetailsClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.IDedMinistriesPaymentOrderDTO;
import com.beshara.csc.hr.sal.business.dto.ISalDeductToMinistriesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalSalarySheetsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalDeductToMinistriesEntityKey;
import com.beshara.csc.hr.sal.business.entity.ISalSalarySheetsEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalDeductToMinistriesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.hr.sal.business.shared.ISALConstants;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;


public class MinistriesPaymentOrderBean extends LookUpBaseBean {

    private static final String RESOURCE_BUNDLE = "com.beshara.csc.hr.scp.integration.presentation.resources.integration";
    private static final String BEAN_NAME = "ministriesPaymentOrderBean";
    @SuppressWarnings("compatibility:3787716068280778392")
    private static final long serialVersionUID = 1L;

    List<IBasicDTO> deductionDetailsList;

    private String reportUrlLink;
    private String reportWindowName;
    private String signature;
    private String otherSignature;
    private List<IBasicDTO> paymentOrderDetailsList = new ArrayList<IBasicDTO>();
    private int paymentOrderDetailsListSize;

    private String accountNo;
    private List accountNoList;
    private String selDedToMinCode;
    private ISalDeductToMinistriesDTO selDedToMin;
    private String reciever = "1";
    private String reciever1 = "1";
    private String reciever2 = "2";
    ISalSalarySheetsDTO salSalarySheetsDTO;

    private static final String REPORT_PARAM_HOLDER = "#p#";
    private static final String DETAILED_REPORT_CODE = "394";
    private static final String ACC_REPORT_CODE = "393";


    public MinistriesPaymentOrderBean() {
        setClient(SalClientFactory.getSalSheetDetailsClient());
        setPageDTO(SalDTOFactory.createDedMinistriesPaymentOrderDTO());
    }


    public AppMainLayout appMainLayoutBuilder() {

        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowSearch(false);
        app.setShowbar(true);
        app.setShowpaging(true);
        app.setShowdatatableContent(true);
        app.setShowCommonData(true);
        app.setShowshortCut(true);
        app.setShowContent1(true);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);

        return app;
    }

    public List getTotalList() {
        return new ArrayList();
    }


    public void getDedMinistriesDetailsBySheetCode() {
        try {
            //Long excludedMinCode =  (selDedToMinCode== null || selDedToMinCode.equals("") ) ? null : Long.valueOf(selDedToMinCode);
            
            deductionDetailsList =
                    ((ISalSheetDetailsClient)getClient()).getDedMinistriesDetailsBySheetCode_account(((ISalSalarySheetsEntityKey)salSalarySheetsDTO.getCode()).getSalsheetCode(),
                                                                                             selDedToMinCode);
        } catch (Exception e) {
            e.printStackTrace();
            deductionDetailsList = new ArrayList();
        }
        setMyTableData(deductionDetailsList);
    }

    public static MinistriesPaymentOrderBean getInstance() {
        return (MinistriesPaymentOrderBean)JSFHelper.getValue(BEAN_NAME);

    }

    public String backToSalarySheetEnquery() {
        return "Salary_Sheet_Enquery_Page";
    }


    public void printDetailedPaymentOrder() {
        reportUrlLink = ISALConstants.PRINT_PAYMENT_ORDER_DETAILED_LINK;
        IDedMinistriesPaymentOrderDTO dto = (IDedMinistriesPaymentOrderDTO)getMyDataTable().getRowData();


        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        String minAccount = selDedToMin.getAccountNo();
        String toMinAccount = ((ISalDeductToMinistriesEntityKey)dto.getSalDeductToMinistriesDTO().getCode()).getAccountNo();
        String toMin = dto.getSalDeductToMinistriesDTO().getMinistriesDTO().getCode().getKey();
        String dedValue = dto.getTotalDeductionValue().toString();
        String bnkCode = dto.getSalDeductToMinistriesDTO().getBankBranchesDTO().getBanksDTO().getCode().getKey();
        String month = salSalarySheetsDTO.getSalaryMonth().toString();
        String year = salSalarySheetsDTO.getYearsKey().toString();
        String sheetCode = salSalarySheetsDTO.getCode().getKey();
        
        SharedUtilBean sharedUtilBean = getSharedUtil();
        String strSign = "";
        if(signature.equals("-100"))
            strSign = otherSignature;
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


        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, DETAILED_REPORT_CODE);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, reciever);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, toMin);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, dedValue);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minAccount);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, toMinAccount);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, bnkCode);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, month);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, year);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, sheetCode);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, strSign);

        System.out.println("generated params -----> " + reportUrlLink);

    }

    public void printAccumulatedPaymentOrder() {
        reportUrlLink = ISALConstants.PRINT_PAYMENT_ORDER_ACC_LINK;

        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        String minAccount = selDedToMin.getAccountNo();
        String dedValue = "0";
        BigDecimal totalVal=BigDecimal.valueOf(0L);
        if(deductionDetailsList==null || deductionDetailsList.size()==0)
        {
            try {
                Long loggedInMinCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
                deductionDetailsList =
                        ((ISalSheetDetailsClient)getClient()).getDedMinistriesDetailsBySheetCode_account(((ISalSalarySheetsEntityKey)salSalarySheetsDTO.getCode()).getSalsheetCode(),
                                                                                                 selDedToMinCode);
            } catch (Exception e) {
                e.printStackTrace();
                deductionDetailsList = new ArrayList();
            }
        }
        if(deductionDetailsList!=null && deductionDetailsList.size()>0)
        {
            
            for(IBasicDTO dto:deductionDetailsList) {
                IDedMinistriesPaymentOrderDTO dto1=(IDedMinistriesPaymentOrderDTO)dto;
               totalVal=totalVal.add(dto1.getTotalDeductionValue()); 
            }
             
        }
        dedValue=totalVal.toString();
        String month = salSalarySheetsDTO.getSalaryMonth().toString();
        String year = salSalarySheetsDTO.getYearsKey().toString();
        String sheetCode = salSalarySheetsDTO.getCode().getKey();
        SharedUtilBean sharedUtilBean = getSharedUtil();
        String strSign = "";
        if(signature.equals("-100"))
            strSign = otherSignature;
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
        
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, ACC_REPORT_CODE);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, strSign);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, reciever);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, dedValue);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minAccount);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, year);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, month);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, sheetCode);
        System.out.println("generated params -----> " + reportUrlLink);

    }
    /////////////////////////// Getters && Setters  ///////////////////////////

    public void setDeductionDetailsList(List deductionDetails) {
        this.deductionDetailsList = deductionDetails;
    }

    public List getDeductionDetailsList() {
        return deductionDetailsList;
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

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setOtherSignature(String otherSignature) {
        this.otherSignature = otherSignature;
    }

    public String getOtherSignature() {
        return otherSignature;
    }

    public void setPaymentOrderDetailsList(List<IBasicDTO> paymentOrderDetailsList) {
        this.paymentOrderDetailsList = paymentOrderDetailsList;
    }

    public List<IBasicDTO> getPaymentOrderDetailsList() {
        return paymentOrderDetailsList;
    }

    public void setPaymentOrderDetailsListSize(int paymentOrderDetailsListSize) {
        this.paymentOrderDetailsListSize = paymentOrderDetailsListSize;
    }

    public int getPaymentOrderDetailsListSize() {
        return paymentOrderDetailsListSize;
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

    public void setSelDedToMinCode(String selDedToMinCode) {
        this.selDedToMinCode = selDedToMinCode;
    }

    public String getSelDedToMinCode() {
        return selDedToMinCode;
    }

    public void setSelDedToMin(ISalDeductToMinistriesDTO selDedToMin) {
        this.selDedToMin = selDedToMin;
    }

    public ISalDeductToMinistriesDTO getSelDedToMin() {
        return selDedToMin;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever1(String reciever1) {
        this.reciever1 = reciever1;
    }

    public String getReciever1() {
        return reciever1;
    }

    public void setReciever2(String reciever2) {
        this.reciever2 = reciever2;
    }

    public String getReciever2() {
        return reciever2;
    }


    public void setSalSalarySheetsDTO(ISalSalarySheetsDTO salSalarySheetsDTO) {
        this.salSalarySheetsDTO = salSalarySheetsDTO;
    }

    public ISalSalarySheetsDTO getSalSalarySheetsDTO() {
        return salSalarySheetsDTO;
    }
}
