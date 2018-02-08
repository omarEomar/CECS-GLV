package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;

import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;


public class EighthStepBean extends LookupMaintainBaseBean{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public EighthStepBean() {
        setContent1Div("module_tabs_cont");
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
         app.setShowWizardBar(true);
         return app;
    }
}
