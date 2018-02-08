package com.beshara.jsfbase.csc.backingbean;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;


public class LookupMaintainBaseBean extends LookUpBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public LookupMaintainBaseBean() {
    }


    public void getAll() throws DataBaseException {
        // super.getAll();
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();

        return app;
    }
}
