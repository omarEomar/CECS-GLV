package com.beshara.csc.hr.scp.integration.presentation.backingbean.socialInsuranceForms;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalarySheetsSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;
import com.beshara.jsfbase.csc.util.wizardbar2.state.WizardInfo;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class InsuranceFormBaseBean   extends LookUpBaseBean{
 
 
    protected static final String RESOURCE_BUNDLE = "com.beshara.csc.hr.scp.integration.presentation.resources.integration";
    protected static final String RESOURCE_KEY_MONTH_PRE = "month_key_";
    @SuppressWarnings("compatibility:5845851001243108510")
    private static final long serialVersionUID = 1L;
    //    private transient HtmlDataTable payOrderDtlsDataTable = new HtmlDataTable();
    protected List<IBasicDTO> categoryList ;
    protected List<IBasicDTO> ministryList ;
    protected List months;
    protected List years;
    
    protected static final String REPORT_PARAM_HOLDER = "#p#";
    protected static final String INSURANCE_REPORT_CODE_166 = "1012";
    protected static final String INSURANCE_REPORT_CODE_167 = "1031";
    protected String reportUrlLink;
    protected String reportWindowName;
    private static final String BEAN_NAME = "insuranceFormBaseBean";
    
    protected String signature;
    //private List
    
    public InsuranceFormBaseBean() {
        setPageDTO(SalDTOFactory.createSalarySheetsSearchCriteriaDTO());
        setClient(SalClientFactory.getSalSalarySheetsClient());
        setSelectedPageDTO(SalDTOFactory.createSalSalarySheetsDTO());
        setSingleSelection(true);
        setContent1Div("module_tabs_cont1");
        
        ministryList = new ArrayList<IBasicDTO>();
        
        
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

    
    public void initiateBeanOnce() {
       

    }
    
    
    public void filterByCategory(ActionEvent event) {

         ( (ISalarySheetsSearchCriteriaDTO)getPageDTO() ).setMinCode(null);
        if ( ( (ISalarySheetsSearchCriteriaDTO)getPageDTO() ).getCatCode() != null) {
            try {
                ministryList = OrgClientFactory.getMinistriesClient().getAllByCategory(((ISalarySheetsSearchCriteriaDTO)getPageDTO()).getCatCode());
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                ministryList = new ArrayList<IBasicDTO>();
            } catch (DataBaseException e) {
                e.printStackTrace();
                ministryList = new ArrayList<IBasicDTO>();
            }
        } else {
            ministryList = new ArrayList<IBasicDTO>();
        }
    }
    public void getAll() throws DataBaseException {
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
    public void setCategoryList(List<IBasicDTO> categoryList) {
        this.categoryList = categoryList;
    }

    public List<IBasicDTO> getCategoryList() {
        if (categoryList == null || categoryList.size() == 0) {
            try {
                categoryList = OrgClientFactory.getCatsClient().getCatsByGovFlag(ISystemConstant.GOVERNMENT_FLAG);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
        return categoryList;
    }

    public void setMinistryList(List<IBasicDTO> ministryList) {
        this.ministryList = ministryList;
    }

    public List<IBasicDTO> getMinistryList() {
        return ministryList;
    }
    
    public static InsuranceFormBaseBean getInstance() {
        
            FacesContext ctx = FacesContext.getCurrentInstance();
            Application app = ctx.getApplication();
            return (InsuranceFormBaseBean)app.createValueBinding("#{" + BEAN_NAME + "}").getValue(ctx);
        }
    public void navigateSteps(WizardInfo wizardInfo) {
            System.out.println("sfds");
      
            if (wizardInfo.getTargetStep() != null && wizardInfo.getTargetStep().equals("step1"))
                executeMethodBinding("socialInsuranceFormsBean.search", null);
            else if (wizardInfo.getTargetStep() != null && wizardInfo.getTargetStep().equals("step2"))
                executeMethodBinding("insurrance103FormBean.search", null);
           
    }    
    
    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }
}
