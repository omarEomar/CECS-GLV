package com.beshara.csc.inf.presentation.backingbean.maindata.specialcasetypes;

import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

public class ListBean extends LookUpBaseBean{    
    public ListBean() {
        setPageDTO(InfDTOFactory.createSpecialCaseTypesDTO());
        setSelectedPageDTO(InfDTOFactory.createSpecialCaseTypesDTO());
        setClient(InfClientFactory.getSpecialCaseTypesClient());
        setSingleSelection(true);
        setSaveSortingState(true);
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createSpecialCaseTypesDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }
}
