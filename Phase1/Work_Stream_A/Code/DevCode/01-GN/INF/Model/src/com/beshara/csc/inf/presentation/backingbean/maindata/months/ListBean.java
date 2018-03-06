package com.beshara.csc.inf.presentation.backingbean.maindata.months;

import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;


public class ListBean extends LookUpBaseBean{
    private boolean addEnabled;
    private boolean editEnabled;
    
    public ListBean() {
        setPageDTO(InfDTOFactory.createMonthsDTO());
        setSelectedPageDTO(InfDTOFactory.createMonthsDTO());
        setClient(InfClientFactory.getMonthsClient());
        setSingleSelection(true);
        setAddEnabled(Boolean.FALSE);
        setEditEnabled(Boolean.FALSE);
        setSaveSortingState(true);
    }
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createMonthsDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }

    public void setAddEnabled(boolean addEnabled) {
        this.addEnabled = addEnabled;
    }

    public boolean isAddEnabled() {
        return addEnabled;
    }

    public void setEditEnabled(boolean editEnabled) {
        this.editEnabled = editEnabled;
    }

    public boolean isEditEnabled() {
        return editEnabled;
    }
}
