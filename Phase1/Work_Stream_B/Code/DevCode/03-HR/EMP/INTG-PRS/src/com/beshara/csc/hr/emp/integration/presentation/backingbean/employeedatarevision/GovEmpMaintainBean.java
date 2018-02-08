package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;


import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.util.wizardbar2.state.WizardInfo;

import java.util.ArrayList;
import java.util.List;


public class GovEmpMaintainBean extends BaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    public static final String BEAN_NAME = "govEmpMaintainBean";
    public static final String METHOD_NAME_VIEW = "view";
    public static final String MAP_KEY_CIVIL_ID = "CIVIL_ID";
    public static final String MAP_KEY_MINISTRY_CODE = "MINISTRY_CODE";
    private Long civilID;
    private Long realCivilId;
    private String empName;
    private Long ministryCode;
    private List<Long> empHireStatusList = new ArrayList<Long>();
    private boolean preSelectedCivilId;
    private boolean enableTabs;
    private String bundleName;
    private String statusMsgKey;
    private String titlePageKey = "Gov_emp_data_revision_title";

    public GovEmpMaintainBean() {
    }

    public static GovEmpMaintainBean getInstance() {
        return (GovEmpMaintainBean)JSFHelper.getValue(BEAN_NAME);
    }

    public static Long getEmpCivilId() {
        return getInstance().civilID;
    }

    public String view() {
        Object param = getIntegrationBean().getHmObjects().get(MAP_KEY_CIVIL_ID);
        if (param != null) {
            this.civilID = Long.parseLong(param.toString());
        }
        this.preSelectedCivilId = true;
        FirstStepBean firstStepBean = FirstStepBean.getInstance();
        firstStepBean.setCivilId(civilID.toString());
        param = getIntegrationBean().getHmObjects().get(MAP_KEY_MINISTRY_CODE);
        if (param != null) {
            setMinistryCode(Long.parseLong(param.toString()));
        } else {
            setMinistryCode(null);
        }

        return firstStepBean.init();
    }

    public String back() {
        return getIntegrationBean().goFrom();
    }

    public void navigateSteps(WizardInfo wizardInfo) {
        if (wizardInfo.getTargetStep() != null) {
            if (wizardInfo.getTargetStep().equals("step1")) {
                FirstStepBean.getInstance().init();
            } else if (wizardInfo.getTargetStep().equals("step2")) {
                SecondStepBean.getInstance().init();
                //executeMethodBinding("govEmpSecondStepBean.init", null);
            } else if (wizardInfo.getTargetStep().equals("step3")) {
                ThirdStepBean.getInstance().init();
            } else if (wizardInfo.getTargetStep().equals("step4")) {
                ForthStepBean.getInstance();
            } else if (wizardInfo.getTargetStep().equals("step5")) {
                FifthStepBean.getInstance().init();
            } else if (wizardInfo.getTargetStep().equals("step6")) {
                SixthStepBean.getInstance().init();
            }else if (wizardInfo.getTargetStep().equals("WivesStep")){
                
                MerRaiseMaintainBean merRaiseMaintainBean = MerRaiseMaintainBean.getInstance();
                merRaiseMaintainBean.setCivilId("" + this.realCivilId);
                merRaiseMaintainBean.filterByCivilId();  
                executeMethodBinding("raiseWivesBean.filterByCivilId", null);
              
            }
            else if ( wizardInfo.getTargetStep().equals("KidsStep")){
                MerRaiseMaintainBean merRaiseMaintainBean = MerRaiseMaintainBean.getInstance();
                merRaiseMaintainBean.setCivilId("" + this.realCivilId);
                merRaiseMaintainBean.filterByCivilId();  
                executeMethodBinding("raiseKidsBean.filterByCivilId", null);
            }
        }
    }

    public void setCivilID(Long civilID) {
        this.civilID = civilID;
    }

    public Long getCivilID() {
        return civilID;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpHireStatusList(List<Long> empHireStatusList) {
        this.empHireStatusList = empHireStatusList;
    }

    public List<Long> getEmpHireStatusList() {
        return empHireStatusList;
    }

    public void setPreSelectedCivilId(boolean preSelectedCivilId) {
        this.preSelectedCivilId = preSelectedCivilId;
    }

    public boolean isPreSelectedCivilId() {
        return preSelectedCivilId;
    }

    public void setEnableTabs(boolean enableTabs) {
        this.enableTabs = enableTabs;
    }

    public boolean isEnableTabs() {
        return enableTabs;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setStatusMsgKey(String statusMsgKey) {
        this.statusMsgKey = statusMsgKey;
    }

    public String getStatusMsgKey() {
        return statusMsgKey;
    }

    public void setTitlePageKey(String titlePageKey) {
        this.titlePageKey = titlePageKey;
    }

    public String getTitlePageKey() {
        return titlePageKey;
    }

    public void setMinistryCode(Long ministryCode) {
        this.ministryCode = ministryCode;
    }

    public Long getMinistryCode() {
        return ministryCode;
    }

    public void setRealCivilId(Long realCivilID) {
        this.realCivilId = realCivilID;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }
}
