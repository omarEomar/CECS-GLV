package com.beshara.csc.gn.map.presentation.backingbean.objecttype;

import com.beshara.csc.gn.map.business.client.IObjectTypesClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;


public class ObjectTypeListBean extends LookUpBaseBean {
    public ObjectTypeListBean() throws Exception {
        setPageDTO(MapDTOFactory.createObjectTypesDTO());
        super.setSelectedPageDTO(MapDTOFactory.createObjectTypesDTO());
        setClient((IObjectTypesClient)MapClientFactory.getObjectTypesClient());
        super.setAddDivTitleBindingString("# { mapResources.AddingObjectTypesPage } "); // pass the title of the add div ex: "# { bundle.key } " 
        super.setEditDivTitleBindingString("# { mapResources.EditingObjectTypesPage } ");
        super.setDelAlertTitleBindingString("# { mapResources.DelAlertObjectTypesPage } ");
        super.setDelConfirmTitleBindingString("# { mapResources.DelConfirmObjectTypesPage } "); // 
        setSaveSortingState(true);
    }

    public void showHideCode() {
        showHideColumn("code_column");
    }

    public void showHideName() {
        showHideColumn("name_column");
    }

    public void search() throws DataBaseException, NoResultException {
        try {
            if (getSearchType().intValue() == 
                0) //0: is the flag of code search //1: is the flag of name search 
                super.setSearchEntityObj(new Long(this.getSearchQuery()));
            super.search();
        } catch (Exception e) {
            e.printStackTrace();
            setSearchMode(true);
            setMyTableData(new ArrayList());
        }
    }
}
