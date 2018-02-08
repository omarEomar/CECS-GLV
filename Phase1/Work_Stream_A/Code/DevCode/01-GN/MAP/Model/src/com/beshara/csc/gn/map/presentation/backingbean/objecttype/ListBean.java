package com.beshara.csc.gn.map.presentation.backingbean.objecttype;

import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

public class ListBean extends LookUpBaseBean{
        public ListBean() {
            setPageDTO(MapDTOFactory.createObjectTypesDTO());
            setSelectedPageDTO(MapDTOFactory.createObjectTypesDTO());
            setClient(MapClientFactory.getObjectTypesClient());
            setSingleSelection(true);
            setSaveSortingState(true);
        }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(MapDTOFactory.createObjectTypesDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }
}
