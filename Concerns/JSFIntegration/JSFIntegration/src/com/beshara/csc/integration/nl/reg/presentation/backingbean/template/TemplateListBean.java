package com.beshara.csc.nl.reg.presentation.backingbean.template;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.reg.business.client.ITemplatesClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.ITemplatesSearchDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;


public class TemplateListBean extends LookUpBaseBean {
    private ITemplatesSearchDTO searchDTO = 
        RegDTOFactory.createTemplatesSearchDTO();

    public TemplateListBean() {
        setPageDTO(RegDTOFactory.createTemplatesDTO()); //set this the page dto 
        setSaveSortingState(true);
        super.setSelectedPageDTO(RegDTOFactory.createTemplatesDTO());
        super.setAddDivTitleBindingString("#{regResources.Add_Div_Template_Title}");
        super.setEditDivTitleBindingString("#{regResources.Edit_Div_Template_Title}");
        super.setDelAlertTitleBindingString("#{regResources.Delete_Alert_Div_Scope_Title}");
        super.setDelConfirmTitleBindingString("#{regResources.Delete_Confirm_Div_Scope_Title}");
        setClient((ITemplatesClient)RegClientFactory.getTemplatesClient());
        setNameMaxLength(380);
        setDelConfirm("divSheardStyle1");
        setDelAlert("divSheardStyle1");
    } // public void orderByCode ( ) { 
    // sort ( "code.key" ) ; 
    // } 
    // 
    // public void orderByName ( ) { 
    // sort ( "name" ) ; 
    // } 
    // 
    // public void orderByTitle ( ) 
    // { 
    // sort ( "templateTitle" ) ; 
    // } 
    // public void orderBySign ( ) 
    // { 
    // sort ( "templateSign" ) ; 
    // } 

    public void showHideCode() {
        showHideColumn("code_column");
    }

    public void showHideName() {
        showHideColumn("name_column");
    }

    public void reInitializePageDTO() {
        setPageDTO(RegDTOFactory.createTemplatesDTO());
    }

    public void showHideTitle() {
        showHideColumn("title_column");
    }

    public void showHideSign() {
        showHideColumn("sign_column");
    } // public void search ( ) throws DataBaseException , NoResultException { 
    // if ( this.getSearchType ( ) .intValue ( ) == 0 ) 
    // super.setSearchEntityObj ( ( new Long ( this.getSearchQuery ( ) ) ) ) ; 
    // super.search ( ) ; 
    // } 

    /** 
     * Purpose: this method is to set the setSearchEntityObj with the actual value * Creation/Modification History : * 1.1 - Developer Name: Amir Mosad * 1.2 - Date: 19-05-2009 * 1.3 - Creation/Modification:Creation * 1.4- Description: */
    public void search() throws DataBaseException, 
                                NoResultException { // ITemplatesSearchDTO searchDTO = RegDTOFactory.createTemplatesSearchDTO ( ) ; 
        // searchDTO.setTemplateCode ( 19L ) ; 
        // searchDTO.setTemplateName ( "555555555555555555" ) ; 
        // searchDTO.setTemplateTitle ( "as" ) ; 
        // searchDTO.setTemplateSign ( "as" ) ; 
        try {
            List<IBasicDTO> result = 
                RegClientFactory.getTemplatesClient().searchInCenter(searchDTO);
            this.setSearchMode(true);
            setMyTableData(result);
            if (getSelectedDTOS() != null) {
                getSelectedDTOS().clear();
            }
            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().clear();
            }
            unCheck();
            this.repositionPage(0);
            //added by nora to reset radio if list has radio column 
            setSelectedRadio("");
        } catch (Exception e) {
            e.printStackTrace();
            setSearchMode(true);
            unCheck();
            setMyTableData(new ArrayList());
        }
    }

    public void cancelSearch() throws DataBaseException {
        super.cancelSearch();
        searchDTO = RegDTOFactory.createTemplatesSearchDTO();
    }

    public void setSearchDTO(ITemplatesSearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public ITemplatesSearchDTO getSearchDTO() {
        return searchDTO;
    }
    public String openSearch() {
   if(!isSearchMode())
    {
    setSearchQuery("");
    searchDTO = RegDTOFactory.createTemplatesSearchDTO();
    }
    return null;
}
}