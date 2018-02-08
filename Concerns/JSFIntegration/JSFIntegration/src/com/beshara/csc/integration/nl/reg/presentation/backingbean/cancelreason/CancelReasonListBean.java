package com.beshara.csc.nl.reg.presentation.backingbean.cancelreason;

import com.beshara.csc.nl.reg.business.client.ICancelReasonsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;


public class CancelReasonListBean extends LookUpBaseBean {
    public CancelReasonListBean() {
        setPageDTO(RegDTOFactory.createCancelReasonsDTO());
        setSaveSortingState(true);
        super.setSelectedPageDTO(RegDTOFactory.createCancelReasonsDTO());
        setClient((ICancelReasonsClient)RegClientFactory.getCancelReasonsClientForCenter());
        setAddDivTitleBindingString("#{regResources.cancelReason_AddDivTitle}"); // pass the title of the add div ex: "# { bundle.key } " 
        setEditDivTitleBindingString("#{regResources.cancelReason_EditDivTitle}");
        setDelAlertTitleBindingString("#{regResources.cancelreason_DelAlertTitle}");
        setDelConfirmTitleBindingString("#{regResources.cancelreason_DelConfirmTitle}"); // 
        setDelConfirm("divSheardStyle1");
        setDelAlert("divSheardStyle1");
    }

    public void orderByCode() {
        sort("code.key");
    }

    public void orderByName() {
        sort("name");
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
     * Purpose: this method is to set the setSearchEntityObj with the actual value * Creation/Modification History : * 1.1 - Developer Name: Amir Mosad * 1.2 - Date: 19-05-2009 * 1.3 - Creation/Modification:Creation * 1.4- Description: */
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
