package com.beshara.csc.inf.presentation.backingbean.maindata.dmlstatmenttypes;

import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;


public class ListBean extends LookUpBaseBean{
    @SuppressWarnings("compatibility:1363948431298451683")
    private static final long serialVersionUID = -2083447614359694540L;
    private boolean addEnabled;
    private boolean editEnabled;
    public ListBean() {
        setClient(InfClientFactory.getDmlStatmentTypesClient());
        setSelectedPageDTO(InfDTOFactory.createDmlStatmentTypesDTO());
        setPageDTO(InfDTOFactory.createDmlStatmentTypesDTO());
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
    this.setPageDTO(InfDTOFactory.createDmlStatmentTypesDTO());
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

