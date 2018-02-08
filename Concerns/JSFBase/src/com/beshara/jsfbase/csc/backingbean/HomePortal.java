package com.beshara.jsfbase.csc.backingbean;

import com.beshara.base.client.BasicClientImpl;
import com.beshara.base.dto.BasicDTO;


public class HomePortal extends BaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public HomePortal() {

        setClient(new BasicClientImpl());
        setPageDTO(new BasicDTO());
        setSelectedPageDTO(new BasicDTO());
        setContent1Div("divContent1Fixed6");
        //        setUsingPortal(true);


    }

    public AppMainLayoutPortal appMainLayoutPortalBuilder() {
        AppMainLayoutPortal app;
        app = super.appMainLayoutPortalBuilder();
        app.setShowTitlePic(false);
        app.setShowContent1(true);


        return app;
    }
}
