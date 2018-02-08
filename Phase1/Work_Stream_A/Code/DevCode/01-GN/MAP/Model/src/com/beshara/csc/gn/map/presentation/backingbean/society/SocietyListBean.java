package com.beshara.csc.gn.map.presentation.backingbean.society;

import com.beshara.csc.gn.map.business.client.ISocietiesClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;


public class SocietyListBean extends LookUpBaseBean {
    public SocietyListBean() throws Exception {
        setPageDTO(MapDTOFactory.createSocietiesDTO());
        super.setSelectedPageDTO(MapDTOFactory.createSocietiesDTO());
        setClient((ISocietiesClient)MapClientFactory.getSocietiesClient());
        super.setAddDivTitleBindingString("# { mapResources.society_add_new_society_title } "); // pass the title of the add div ex: "# { bundle.key } " 
        super.setEditDivTitleBindingString("# { mapResources.society_update_title } ");
        super.setDelAlertTitleBindingString("# { mapResources.society_delete_one_society_title } ");
        super.setDelConfirmTitleBindingString("# { mapResources.society_delete_confirm_society_title } "); // 
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
