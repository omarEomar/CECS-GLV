package com.beshara.csc.inf.presentation.backingbean.maindata.approvalmakers;


import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

public class ListBean extends LookUpBaseBean{
    @SuppressWarnings("compatibility:1363948431298451683")
    private static final long serialVersionUID = -2083447614359694540L;
    public ListBean() {
        setClient(InfClientFactory.getApprovalMakersClient());
        setSelectedPageDTO(InfDTOFactory.createApprovalMakersDTO());
        setPageDTO(InfDTOFactory.createApprovalMakersDTO());
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
    this.setPageDTO(InfDTOFactory.createApprovalMakersDTO());
    }

    public void search() throws DataBaseException, NoResultException {
    if (getSearchType().intValue() == 0)
        super.setSearchEntityObj(new Long(this.getSearchQuery()));
    super.search();
    }

    public void cancelAdd() {
    super.cancelAdd(); 
    setPageMode(0);
    setSelectedRadio("");
    reInitializePageDTO();
    }


    public void cancelEdit() {
    setPageMode(0);
    }
}

