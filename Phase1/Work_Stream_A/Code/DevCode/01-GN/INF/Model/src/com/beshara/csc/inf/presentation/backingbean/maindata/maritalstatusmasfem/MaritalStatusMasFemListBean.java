package com.beshara.csc.inf.presentation.backingbean.maindata.maritalstatusmasfem;


import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

public class MaritalStatusMasFemListBean extends LookUpBaseBean {
    @SuppressWarnings("compatibility:-7717916734619081223")
    private static final long serialVersionUID = -7680945092606036533L;


    public MaritalStatusMasFemListBean() {
        setClient(InfClientFactory.getMaritalStatusClient());
        setSelectedPageDTO(InfDTOFactory.createMaritalStatusDTO());
        setPageDTO(InfDTOFactory.createMaritalStatusDTO());
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
        this.setPageDTO(InfDTOFactory.createMaritalStatusDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }

    public void cancelAdd() {
        super.cancelAdd();
        setPageMode(0);
        reInitializePageDTO();
    }


    public void cancelEdit() {
        setPageMode(0);
    }
}
