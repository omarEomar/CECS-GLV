package com.beshara.csc.inf.presentation.backingbean.maindata.groupcountries;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.ICountryGroupsClient;
import com.beshara.csc.inf.business.client.IGenderCountryClient;
import com.beshara.csc.inf.business.client.IGenderTypesClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.ICountryGroupsDTO;
import com.beshara.csc.inf.business.dto.IGenderCountryDTO;
import com.beshara.csc.inf.business.dto.IGenderTypesDTO;
import com.beshara.csc.inf.business.dto.IGroupCountriesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class GroupCountriesListBean extends LookUpBaseBean {
    private Long selectedCountry = getVirtualLongValue();
    private Long selectedGrpCountry = getVirtualLongValue();
    private List countryCats = null;
    private List groupCountry = null;
    private IGenderCountryDTO nationalityDivDTO = InfDTOFactory.createGenderCountryDTO();
    private Long genderType;
    List<IBasicDTO> deletedRows;


    public GroupCountriesListBean() {

        setLovDiv("customLovDiv");
        setPageDTO(InfDTOFactory.createGroupCountriesDTO());
        setSelectedPageDTO(InfDTOFactory.createGroupCountriesDTO());
        setClient(InfClientFactory.getGroupCountriesClient());

        // init LovBaseBean
        setLovBaseBean(new LOVBaseBean());
        fillLOVDataTable();
        getLovBaseBean().setReturnMethodName("groupCountriesListBean.closeLovDiv");
        getLovBaseBean().setSearchMethod("groupCountriesListBean.searchLovDiv");
        getLovBaseBean().setCancelSearchMethod("groupCountriesListBean.cancelSearchLovDiv");
        setDivMainContent("infGrpCountryCnt2");
        setSaveSortingState(true);
    }

    public void fillLOVDataTable() {

        if (selectedGrpCountry != null && !selectedGrpCountry.equals(getVirtualLongValue()))
            try {
                getLovBaseBean().setMyTableData(InfClientFactory.getGroupCountriesClient().getAvailableCountries(selectedGrpCountry));
            } catch (SharedApplicationException e) {
                getLovBaseBean().setMyTableData(new ArrayList());
                e.printStackTrace();
            } catch (Exception e) {
                getLovBaseBean().setMyTableData(new ArrayList());
                e.printStackTrace();
            }
        else
            getLovBaseBean().setMyTableData(new ArrayList());
        // for repostioning el table every time
        getLovBaseBean().getMyDataTable().setFirst(0);
    }

    public String closeLovDiv() {
        if (getLovBaseBean().getSelectedDTOS() != null && getLovBaseBean().getSelectedDTOS().size() > 0 &&
            selectedCountry != null && selectedCountry != getVirtualLongValue()) {
            ICountryGroupsDTO countryGroupsDTO = InfDTOFactory.createCountryGroupsDTO();
            countryGroupsDTO.setCode(InfEntityKeyFactory.createCountryGroupsEntityKey(selectedCountry));
            try {
                List<IBasicDTO> addedList =
                    InfClientFactory.getGroupCountriesClient().addCountries(countryGroupsDTO, getLovBaseBean().getSelectedDTOS());
                getAll();
                getSharedUtil().handleSuccMsg("com.beshara.jsfbase.csc.msgresources.global_ar", "SuccessAdds");
                if (getHighlightedDTOS() != null) {
                    setHighlightedDTOS(addedList);
                }
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "FailureInAdd");
            } catch (Exception e) {
                e.printStackTrace();
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "FailureInAdd");
            }
        }
        getLovBaseBean().setCheckAllFlag(false);
        getLovBaseBean().cancelSearchLovDiv();
        setCheckAllFlag(false);
        return "";
    }

    public String openLovDiv() {
        getLovBaseBean().setCheckAllFlag(false);
        getLovBaseBean().cancelSearchLovDiv();
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        return "";
    }

    public String searchLovDiv(Object searchType, Object searchQuery) {
        try {
            if (searchType.toString().equals("0")) {
                Long code = Long.valueOf((String)searchQuery);
                getLovBaseBean().setMyTableData(InfClientFactory.getGroupCountriesClient().searchAvailableCountriesByCode(selectedGrpCountry,
                                                                                                                          code));
            } else if (searchType.toString().equals("1"))
                getLovBaseBean().setMyTableData(InfClientFactory.getGroupCountriesClient().searchAvailableCountriesByName(selectedGrpCountry,
                                                                                                                          formatSearchString((String)searchQuery)));

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        } catch (DataBaseException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList());
        }


        return "";
    }

    public String cancelSearchLovDiv() {
        fillLOVDataTable();
        return "";
    }

    public void showMaleEditDiv() {

        reIntializeMsgs();
        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
            setSelectedPageDTO(this.getSelectedDTOS().get(0));
            setNationalityDivDTO(getNationalityByTypeAndCountryCode(ISystemConstant.GENDER_MALE));
            setGenderType(ISystemConstant.GENDER_MALE);
        }


    }

    private IGenderCountryDTO getNationalityByTypeAndCountryCode(Long GENDER) {
        Long cntryGrp = Long.parseLong(getSelectedDTOS().get(0).getCode().getKeys()[1].toString());
        IGenderCountryClient genderCountryClient = InfClientFactory.getGenderCountryClient();
        try {

            return (IGenderCountryDTO)genderCountryClient.getById(InfEntityKeyFactory.createGenderCountryEntityKey(GENDER,
                                                                                                                   cntryGrp));
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            nationalityDivDTO = InfDTOFactory.createGenderCountryDTO();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return InfDTOFactory.createGenderCountryDTO();
    }

    public void showFemaleEditDiv() {
        reIntializeMsgs();
        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
            setSelectedPageDTO(this.getSelectedDTOS().get(0));
            setNationalityDivDTO(getNationalityByTypeAndCountryCode(ISystemConstant.GENDER_FEMALE));
            setGenderType(ISystemConstant.GENDER_FEMALE);
        }

    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showLookupListPage();
        app.setShowContent1(true);
        app.setShowLovDiv(true);
        app.setShowLookupAdd(false);
        return app;
    }

    public void filterByCategory(ActionEvent event) {
        event = null;
        if (isSearchMode()) {
            try {
                super.cancelSearch();
                setSelectedCountry(getVirtualLongValue());
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
        setMyTableData(new ArrayList());
        setGroupCountry(new ArrayList());
        getSelectedDTOS().clear();
        //        setGroupCountry(new ArrayList());
        if (getSelectedGrpCountry() != null && !getSelectedGrpCountry().equals(getVirtualLongValue())) {
            try {
                ICountryGroupsClient countryGroupsClient = InfClientFactory.getCountryGroupsClient();
                setGroupCountry(countryGroupsClient.getGroups(getSelectedGrpCountry()));

            } catch (Exception e) {
                e.printStackTrace();
                setGroupCountry(new ArrayList());
                setSelectedCountry(getVirtualLongValue());
            }
        } else {
            setSelectedCountry(getVirtualLongValue());
        }
    }

    public void filterByGrp(ActionEvent event) {

        event = null;
        if (isSearchMode()) {
            try {
                super.cancelSearch();
                //                setSelectedCountry(getVirtualLongValue());
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
        try {
            this.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            setGroupCountry(new ArrayList());
        }
        this.repositionPage(0);

    }

    public void saveNationality() {
        try {
            nationalityDivDTO.setCountriesDTO(((IGroupCountriesDTO)getSelectedDTOS().get(0)).getCountriesDTO());
            IGenderTypesClient genderTypesClient = InfClientFactory.getGenderTypesClient();
            nationalityDivDTO.setGenderTypesDTO((IGenderTypesDTO)genderTypesClient.getById(InfEntityKeyFactory.createGenderTypesEntityKey(getGenderType())));
            if (nationalityDivDTO != null && nationalityDivDTO.getCode() == null)
                InfClientFactory.getGenderCountryClient().add(nationalityDivDTO);

            else if (nationalityDivDTO != null && nationalityDivDTO.getCode() != null)
                InfClientFactory.getGenderCountryClient().update(nationalityDivDTO);
            cancelSearch();
            if (super.getHighlightedDTOS() != null) {
                super.getHighlightedDTOS().add(this.getSelectedPageDTO());
            }
            getSharedUtil().handleSuccMsg("com.beshara.csc.inf.presentation.resources.inf", "saving_natiality_sucess");
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, "com.beshara.csc.inf.presentation.resources.inf",
                                            "saving_natiality_failure");
        } catch (Exception e) {
            e.printStackTrace();
            getSharedUtil().handleException(e, "com.beshara.csc.inf.presentation.resources.inf",
                                            "saving_natiality_failure");
        }
    }

    public void getAll() throws DataBaseException {

        if (getSelectedCountry() != null && !getSelectedCountry().equals(getVirtualLongValue())) {
            try {

                setMyTableData(InfClientFactory.getGroupCountriesClient().getCountries(getSelectedCountry()));
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


    public void setSelectedGrpCountry(Long selectedGrpCountry) {
        this.selectedGrpCountry = selectedGrpCountry;
    }

    public Long getSelectedGrpCountry() {
        return selectedGrpCountry;
    }

    public void setCountryCats(List countryGroups) {
        this.countryCats = countryGroups;
    }

    public List getCountryCats() {
        if (countryCats == null) {
            ICountryGroupsClient countryGroupsClient = InfClientFactory.getCountryGroupsClient();
            try {
                countryCats = countryGroupsClient.getCats();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                countryCats = new ArrayList();
            } catch (DataBaseException e) {
                e.printStackTrace();
                countryCats = new ArrayList();
            }
        }

        return countryCats;
    }


    public void setGroupCountry(List groupCountry) {
        this.groupCountry = groupCountry;
    }

    public List getGroupCountry() {
        return groupCountry;
    }

    public void setSelectedCountry(Long selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public Long getSelectedCountry() {
        return selectedCountry;
    }

    public void setNationalityDivDTO(IGenderCountryDTO nationalityDivDTO) {
        this.nationalityDivDTO = nationalityDivDTO;
    }

    public IGenderCountryDTO getNationalityDivDTO() {
        return nationalityDivDTO;
    }

    public List getBaseSearchResult() throws DataBaseException {

        List searchResult = new ArrayList();

        try {
            if (getSearchType().intValue() == 0) {
                searchResult =
                        InfClientFactory.getGroupCountriesClient().searchByCode(selectedCountry, new Long(this.getSearchQuery()));
            } else if (getSearchType().intValue() == 1) {
                searchResult =
                        InfClientFactory.getGroupCountriesClient().searchByName(selectedCountry, formatSearchString(getSearchQuery()));
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

    public void setGenderType(Long genderType) {
        this.genderType = genderType;
    }

    public Long getGenderType() {
        return genderType;
    }

    public void setDeletedRows(List<IBasicDTO> deletedRows) {
        this.deletedRows = deletedRows;
    }

    public List<IBasicDTO> getDeletedRows() {
        return deletedRows;
    }
    public boolean isEnableButton(){
        if(selectedCountry.equals(getVirtualLongValue())){
            return true;
        }else{
            return false;
        }
    }
}
