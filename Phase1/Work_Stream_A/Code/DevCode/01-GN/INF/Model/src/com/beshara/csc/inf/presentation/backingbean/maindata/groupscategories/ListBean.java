package com.beshara.csc.inf.presentation.backingbean.maindata.groupscategories;


import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;


public class ListBean extends LookUpBaseBean {

    public ListBean() {
        setPageDTO(InfDTOFactory.createCountryGroupsDTO());
        setSelectedPageDTO(InfDTOFactory.createCountryGroupsDTO());
        setClient(InfClientFactory.getCountryGroupsClient());
        setSingleSelection(true);
        setSaveSortingState(true);
    }

    public void getAll() throws DataBaseException {
        try {
            setMyTableData(InfClientFactory.getCountryGroupsClient().getCats());
        } catch (SharedApplicationException ne) {
            setMyTableData(new ArrayList());
            ne.printStackTrace();
        } catch (DataBaseException db) {
            setMyTableData(new ArrayList());
            db.printStackTrace();
        } catch (Exception e) {
            setMyTableData(new ArrayList());
            e.printStackTrace();
        }
        this.reinitializeSort();

        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }

        if (getHighlightedDTOS() != null) {
            getHighlightedDTOS().clear();
        }
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        return app;
    }
    
    
    public void save(){
        try {
            super.save();
        }
        catch (Exception e) {
                    this.setShowErrorMsg(true);
                    this.setErrorMsg(e.getMessage());
                    getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", this.getErrorMsg());

        }
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createCountryGroupsDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }
}
