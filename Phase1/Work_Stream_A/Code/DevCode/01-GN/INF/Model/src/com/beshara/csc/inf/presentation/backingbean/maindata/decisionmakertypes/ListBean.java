package com.beshara.csc.inf.presentation.backingbean.maindata.decisionmakertypes;


import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

public class ListBean extends LookUpBaseBean{    
    public ListBean() {
        setPageDTO(InfDTOFactory.createDecisionMakerTypesDTO());
        setSelectedPageDTO(InfDTOFactory.createDecisionMakerTypesDTO());
        setClient(InfClientFactory.getDecisionMakerTypesClient());
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
        this.setPageDTO(InfDTOFactory.createDecisionMakerTypesDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }
}


