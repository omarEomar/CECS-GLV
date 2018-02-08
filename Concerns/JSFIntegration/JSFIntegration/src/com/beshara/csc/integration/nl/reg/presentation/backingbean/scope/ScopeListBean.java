package com.beshara.csc.nl.reg.presentation.backingbean.scope;

import com.beshara.base.entity.*;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.client.IRegulationScopesClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegulationScopesDTO;
import com.beshara.csc.nl.reg.business.entity.ICancelReasonsEntityKey;
import com.beshara.csc.nl.reg.business.entity.IRegulationScopesEntityKey;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;

import java.util.ArrayList;

public class ScopeListBean extends LookUpBaseBean {
    public ScopeListBean() {
        setPageDTO(RegDTOFactory.createRegulationScopesDTO()); //set this the page dto 
        setSaveSortingState(true);
        super.setSelectedPageDTO(RegDTOFactory.createRegulationScopesDTO());
        super.setAddDivTitleBindingString("#{regResources.Add_Div_Scope_Title}");
        super.setEditDivTitleBindingString("#{regResources.Edit_Div_Scope_Title}");
        super.setDelAlertTitleBindingString("#{regResources.Delete_Alert_Div_Scope_Title}");
        super.setDelConfirmTitleBindingString("#{regResources.Delete_Confirm_Div_Scope_Title}");
        setClient((IRegulationScopesClient)RegClientFactory.getRegulationScopesClient());
        setDelConfirm("divSheardStyle1");
        setDelAlert("divSheardStyle1");
    }

    public void showHideCode() {
        showHideColumn("code_column");
    }

    public void showHideName() {
        showHideColumn("name_column");
    } // public void search ( ) throws DataBaseException , NoResultException { 
    // if ( this.getSearchType ( ) .intValue ( ) == 0 ) 
    // super.setSearchEntityObj ( new Long ( this.getSearchQuery ( ) ) ) ; 
    // super.search ( ) ; 
    // } 

    /** 
     * Purpose: this method is to set the setSearchEntityObj with the actual value * Creation/Modification History : * 1.1 - Developer Name: Amir Mosad * 1.2 - Date: 19-04-2009 * 1.3 - Creation/Modification:Creation * 1.4- Description: */
    public void search() throws DataBaseException, NoResultException {
        try {
            if (getSearchType().intValue() == 0)
                super.setSearchEntityObj(new Long(this.getSearchQuery()));
            super.search();
        } catch (Exception e) {
            e.printStackTrace();
            setSearchMode(true);
            setMyTableData(new ArrayList());
        }
    }
}
