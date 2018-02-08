package com.beshara.jsfbase.csc.backingbean;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;


/**
 *this bean responsible for maintain the page useing tree
 *use this bean when implement Add\Edit note of tree page 
 *
 */
public class TreeMaintainBaseBean extends TreeBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public TreeMaintainBaseBean() {
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
