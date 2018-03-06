package com.beshara.csc.inf.presentation.backingbean.maindata.countrygroup;


import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.ICountryGroupsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class ListBean extends LookUpBaseBean {

    private List groupCategoryList = new ArrayList();
    private Long selectedCatCode = getVirtualLongValue();

    public ListBean() {
        setPageDTO(InfDTOFactory.createCountryGroupsDTO());
        setSelectedPageDTO(InfDTOFactory.createCountryGroupsDTO());
        setClient(InfClientFactory.getCountryGroupsClient());
        setSingleSelection(true);
        setDivMainContent("infGrpCountryCnt");
        setSaveSortingState(true);
    }

    public void getAll() throws DataBaseException {

        if (selectedCatCode != null && !selectedCatCode.equals(getVirtualLongValue())) {
            try {
                setMyTableData(InfClientFactory.getCountryGroupsClient().getGroups(selectedCatCode));
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
        } else
            setMyTableData(new ArrayList());

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
        app.setShowContent1(true);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createCountryGroupsDTO());
    }

    public List getBaseSearchResult() throws DataBaseException {

        List searchResult = new ArrayList();

        try {
            if (getSearchType().intValue() == 0) {
                searchResult =
                        InfClientFactory.getCountryGroupsClient().searchGrpByCode(new Long(this.getSearchQuery()),
                                                                                  selectedCatCode);
            } else if (getSearchType().intValue() == 1) {
                searchResult =
                        InfClientFactory.getCountryGroupsClient().searchGrpByName(selectedCatCode, formatSearchString(getSearchQuery()));
            }
        } catch (ItemNotFoundException e) { //this means no search results found
            searchResult = new ArrayList();
            e.printStackTrace();
        } catch (NoResultException ne) { //this means no search results found
            searchResult = new ArrayList();
            ne.printStackTrace();
        } catch (Exception db) {
            searchResult = new ArrayList();
            db.printStackTrace();

        }

        return searchResult;
    }

    public void setGroupCategoryList(List groupCategoryList) {
        this.groupCategoryList = groupCategoryList;
    }

    public List getGroupCategoryList() {
        if (groupCategoryList == null || (groupCategoryList.size() == 0)) {
            try {
                groupCategoryList = InfClientFactory.getCountryGroupsClient().getCats();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                groupCategoryList = new ArrayList();
            } catch (Exception e) {
                groupCategoryList = new ArrayList();
                e.printStackTrace();
            }
        }
        return groupCategoryList;
    }


    public void setSelectedCatCode(Long selectedCatCode) {
        this.selectedCatCode = selectedCatCode;
    }

    public Long getSelectedCatCode() {
        return selectedCatCode;
    }

    public void filterByCategory(ActionEvent event) {
        event = null; //unused
        try {
            cancelSearch();
            //            getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void add() throws DataBaseException {
        if (selectedCatCode != null && !selectedCatCode.equals(getVirtualLongValue()) && getPageDTO() != null) {
            ICountryGroupsDTO parent = InfDTOFactory.createCountryGroupsDTO();
            parent.setCode(InfEntityKeyFactory.createCountryGroupsEntityKey(selectedCatCode));
            ((ICountryGroupsDTO)getPageDTO()).setCountryGroupsDTO(parent);
            super.add();

        }
    }

}
