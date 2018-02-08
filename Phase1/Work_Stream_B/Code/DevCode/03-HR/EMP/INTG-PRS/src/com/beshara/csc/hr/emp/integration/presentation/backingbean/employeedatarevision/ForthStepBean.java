package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;


import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.kwtworkdata.WorkDataListBean;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import javax.faces.context.FacesContext;


public class ForthStepBean extends LookupMaintainBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    WorkDataListBean workDataListBean = new WorkDataListBean();
    private static final String BEAN_NAME = "govEmpForthStepBean";
    GovEmpMaintainBean maintainBean = GovEmpMaintainBean.getInstance();

    public ForthStepBean() {
        //        setDivMainContent("divContentWithFullWizardAndThreeRowsContent1");
        //            setContent1Div("module_tabs_cont");
        //        workDataListBean.setCivilId(GovEmpMaintainBean.getEmpCivilId());
        //        workDataListBean.setFromTabs(true);
        //        try {
        //            workDataListBean.getAll();
        //        } catch (DataBaseException e) {
        //            e.printStackTrace();
        //        }

        setContent1Div("module_tabs_cont1");
    }

    public ForthStepBean(WorkDataListBean _workDataListBean) {
        setContent1Div("module_tabs_cont1");
        this.workDataListBean = _workDataListBean;
    }

    public static ForthStepBean getInstance() {
        return (ForthStepBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowWizardBar(true);
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        return app;
    }

    public void setWorkDataListBean(WorkDataListBean workDataListBean) {
        this.workDataListBean = workDataListBean;
    }

    public WorkDataListBean getWorkDataListBean() {
        return workDataListBean;
    }

    public void initiateBeanOnce() {
        Long civilId = Long.valueOf(maintainBean.getRealCivilId());

        Integer WIZARD_BAR_PAGE = 1;
        workDataListBean.setPageType(WIZARD_BAR_PAGE);
        workDataListBean.setAddExperIntegrationpage("govempforthstep");
        workDataListBean.setListPageinWizardBar("GovEmpDataRevisionWizBarID");
        workDataListBean.setSaveInDB(false);
        workDataListBean.setEnableAdd(true);
        workDataListBean.setEnableBack(false);
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        integrationBean.getHmBaseBeanFrom().put("includeTrx", false);
        integrationBean.getHmBaseBeanFrom().put("trxTypesCode", 10L);
        integrationBean.getHmBaseBeanFrom().put("govEmpMaintainBean", GovEmpMaintainBean.getInstance());
        integrationBean.getHmBaseBeanFrom().put("govEmpForthStepBean", this);
        workDataListBean.setCivilId(civilId);
        workDataListBean.setFromTabs(true);
//        try {
//            workDataListBean.getAll();
//            workDataListBean.buildTree();
//        } catch (DataBaseException e) {
//            workDataListBean.setMyTableData(new ArrayList());
//            e.printStackTrace();
//        }
        //        workDataListBean.setPanelGroupStyleClass("manytomany_treeDivPanel");
        workDataListBean.setPanelGroupStyleClass("tree-area-tabs-With-1-row-filter");

        integrationBean.getHmBaseBeanFrom().put("workDataListBean", workDataListBean);
    }

}
