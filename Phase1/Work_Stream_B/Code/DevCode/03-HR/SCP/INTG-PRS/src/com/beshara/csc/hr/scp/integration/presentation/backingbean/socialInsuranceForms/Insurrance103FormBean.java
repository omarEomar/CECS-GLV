package com.beshara.csc.hr.scp.integration.presentation.backingbean.socialInsuranceForms;


import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.sal.business.client.ISalSalarySheetsClient;
import com.beshara.csc.hr.sal.business.dto.ISalarySheetsSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.SalarySheetsSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.shared.ISALConstants;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class Insurrance103FormBean extends InsuranceFormBaseBean {

    
    @SuppressWarnings("compatibility:-1889947650281677931")
    private static final long serialVersionUID = 1L;
    
    /// by saad
    private String reportUrlLink;
    private String signature;
    private String signatureStr;
    private static final String REPORT_PARAM_HOLDER = "#p#";
    private static final String INSURANCE_REPORT_CODE_103F = "1040";
    private static final String INSURANCE_REPORT_CODE_103B = "1041";
    private static final String INSURANCE_REPORT_CODE_103E = "1042";
    //// end by saad


    private static final String BEAN_NAME = "insurrance103FormBean";
    private String signature1;

    public Insurrance103FormBean() {
        super();
        //setSelectedPageDTO(EmpDTOFactory.createSalSalarySheetsDTO());
    }
    private void fillSearchCriteria(){
        
            InsuranceFormBaseBean baseBean=InsuranceFormBaseBean.getInstance();
            setPageDTO(baseBean.getPageDTO());
        
        }
    public static Insurrance103FormBean getInstance() {
    
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (Insurrance103FormBean)app.createValueBinding("#{" + BEAN_NAME + "}").getValue(ctx);
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
        app.setShowCustomDiv1(false);
        //app.setShowCustomDiv2(true);
        //app.setShowCustomDiv3(true);
        //app.setShowIntegrationDiv1(true);
        app.setShowWizardBar(true);
        return app;
    }
    public void search() {
        
        List list  = new ArrayList();
        try {
            
            Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
    //            ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setBankCode(1L);
            setSelectedDTOS(new ArrayList());
            setSelectedRadio("");
            fillSearchCriteria();
           ((ISalarySheetsSearchCriteriaDTO)getPageDTO()).setFormType(IScpConstants.INSURRANCE_FORM_TYPE_103);
        
            list = ((ISalSalarySheetsClient)getClient()).getSocialInsuranceFormsData(getPageDTO());
           // salarySheets = (List)dataList.get(0);
           // salSalarySheetsDTO=SalDTOFactory.createSalSalarySheetsDTO();
          //  salSalarySheetsDTO = (ISalSalarySheetsDTO)dataList.get(1);
        } catch (Exception e) {
            e.printStackTrace();
            list = new ArrayList();
           // salSalarySheetsDTO=SalDTOFactory.createSalSalarySheetsDTO();
        }
        setMyTableData(list);
        repositionPage(0);
        
    }
    /**by saad****/
    
    public void printSocialInsuranceForm103() {
        reportUrlLink = ISALConstants.PRINT_INSURANCE_REPORT_FORM103_LINK;    
            
        ISalarySheetsSearchCriteriaDTO salarySheetsSearchCriteriaDTO = ( (ISalarySheetsSearchCriteriaDTO)getPageDTO() );
        Long minCode = salarySheetsSearchCriteriaDTO.getMinCode();       
        String month = salarySheetsSearchCriteriaDTO.getMonth();
        Long year = salarySheetsSearchCriteriaDTO.getYear();        
      
        SharedUtilBean sharedUtilBean = getSharedUtil();
        String strSign = "";
        String jobDesc="";
        
        String signature = ((SalarySheetsSearchCriteriaDTO)getPageDTO()).getSignature();
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
            if(strArr != null && strArr.length > 0){
                if(strArr.length > 1)
                    jobDesc = strArr[1]; 
                strSign = strArr[0]; 
                
            }   
        }
        
        IEmployeesDTO empDTO = (IEmployeesDTO)getSelectedDTOS().get(0);
        Long civilID = empDTO.getRealCivilId();  // 4th param
        Long hireStatusCode = (empDTO.getHireStatusKey() == null) ? null : Long.parseLong(empDTO.getHireStatusKey());
        String bnkName="";
        if(hireStatusCode != null && hireStatusCode.equals(IEMPConstants.Emp_With_Status_END_Service)){
            reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, INSURANCE_REPORT_CODE_103E);
        }else{
            Long count=0L;
            try {
                count = ((ISalSalarySheetsClient)getClient()).getCountForReportForm103(civilID);               
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
            if(count.equals(1L)){
                reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, INSURANCE_REPORT_CODE_103F);
            }
            else if (count>1L){
                reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, INSURANCE_REPORT_CODE_103B);
            }
        }
       
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, jobDesc);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, civilID.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, minCode.toString());
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, strSign);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, bnkName);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, month);
        reportUrlLink = reportUrlLink.replaceFirst(REPORT_PARAM_HOLDER, year.toString());
       
        
        System.out.println("generated params -----> " + reportUrlLink);

    }


    /***********/
    public void setReportUrlLink(String reportUrlLink) {
        this.reportUrlLink = reportUrlLink;
    }

    public String getReportUrlLink() {
        return reportUrlLink;
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

    public void setSignature1(String signature1) {
        this.signature1 = signature1;
    }

    public String getSignature1() {
        return signature1;
    }
}
