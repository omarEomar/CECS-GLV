package com.beshara.csc.gn.grs.presentation.backingbean.conditions;

import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;


public class conditionsBean extends ManyToManyDetailsMaintain {

    /**
     * Purpose: this method handle show and hide divs
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        app.setManyToMany(true);
        app.setShowNavigation(true);
        app.setShowsteps(true);
        return app;
    }
}
