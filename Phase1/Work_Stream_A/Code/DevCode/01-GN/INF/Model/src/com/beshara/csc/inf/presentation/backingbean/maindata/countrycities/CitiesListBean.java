package com.beshara.csc.inf.presentation.backingbean.maindata.countrycities;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.ICountriesDTO;
import com.beshara.csc.inf.business.dto.ICountryCitiesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.inf.CountryAlreadyHasCapital;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.MasterDetailBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class CitiesListBean extends MasterDetailBaseBean {
    private static final String BUNDLE_NAME = "com.beshara.csc.inf.presentation.resources.inf";
    public CitiesListBean() {

        this.setClient(InfClientFactory.getCountryCitiesClient());
        this.setMasterBackBeanName("countriesListBean");
        this.setPageDTO(InfDTOFactory.createCountryCitiesDTO());
        this.setMasterUseCase("Counrty_Page");
        this.setSelectedPageDTO(InfDTOFactory.createCountryCitiesDTO());
        setDivMainContent("infGrpCountryCnt");
        setSaveSortingState(true);
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        return app;
    }


    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createCountryCitiesDTO());
    }

    public List getBaseSearchResult() throws DataBaseException {

        List searchResult = new ArrayList();
        if (this.getMasterDTO() != null && this.getMasterDTO().getCode() != null) {
            try {
                if (getSearchType().intValue() == 0) {
                    searchResult =
                            InfClientFactory.getCountryCitiesClient().searchByCode(((ICountriesEntityKey)(getMasterDTO()).getCode()).getCntryCode(),
                                                                                   new Long(this.getSearchQuery()));
                } else if (getSearchType().intValue() == 1) {
                    searchResult =
                            InfClientFactory.getCountryCitiesClient().searchByName(((ICountriesEntityKey)(getMasterDTO()).getCode()).getCntryCode(),
                                                                                   formatSearchString(getSearchQuery()));
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
        }
        return searchResult;
    }

    public void getAll() throws DataBaseException {

        if (this.getMasterDTO() != null && this.getMasterDTO().getCode() != null) {
            try {
                setMyTableData(InfClientFactory.getCountryCitiesClient().getCities(((ICountriesEntityKey)getMasterDTO().getCode()).getCntryCode()));
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


    public void add() throws DataBaseException {

        if (this.getMasterDTO() != null && this.getMasterDTO().getCode() != null) {
            ((ICountryCitiesDTO)getPageDTO()).setCountriesDTO((ICountriesDTO)this.getMasterDTO());

            try {

                setPageDTO(getClient().add(getPageDTO()));

                if (isUsingPaging()) {

                    getPagingBean().clearDTOS();

                    generatePagingRequestDTO((String)getPageDTO().getCode().getKey());

                } else {
                    cancelSearch();
//                    getAll();
                    getPageIndex((String)getPageDTO().getCode().getKey());
                }

                if (getHighlightedDTOS() != null) {
                    getHighlightedDTOS().add(getPageDTO());
                }

                getSharedUtil().setSuccessMsgValue("SuccessAdds");

            }

            catch (CountryAlreadyHasCapital e) {
                this.setShowErrorMsg(true);
                //this.setErrorMsg("FailureInAdd");
                getSharedUtil().handleException(e, "com.beshara.csc.inf.presentation.resources.inf",
                                                "CountryAlreadyHasCapital");
                this.setErrorMsg(getSharedUtil().getErrMsgValue());
            }

            catch (DataBaseException e) {
                this.setShowErrorMsg(true);
                String msg=getSharedUtil().getResourceBundle(BUNDLE_NAME).getString("Entered_name_already_exist");
                getSharedUtil().setErrMsgValue(msg);
                this.setErrorMsg(msg);
            } catch (SharedApplicationException e) {
                this.setShowErrorMsg(true);
                this.setErrorMsg("FailureInAdd");
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "FailureInAdd");
            } catch (Exception e) {
                this.setShowErrorMsg(true);
                this.setErrorMsg("FailureInAdd");
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "FailureInAdd");
            }
            //added by nora to reset radio if list has radio column
            setSelectedRadio("");

        }
    }

    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            super.getClient().update(super.getSelectedPageDTO());
            this.getAll();
            if (this.getHighlightedDTOS() != null)
                this.getHighlightedDTOS().add(super.getSelectedPageDTO());
            super.setSelectedPageDTO(null);
            super.setShowEdit(false);
            super.setSelectedDTOS(new ArrayList<IBasicDTO>());
            shared_util.setSuccessMsgValue("SuccesUpdated");
        } catch (CountryAlreadyHasCapital e) {
            getSharedUtil().handleException(e, "com.beshara.csc.inf.presentation.resources.inf",
                                            "CountryAlreadyHasCapital");
        } catch (DataBaseException e) {
            shared_util.handleException(e);
        } catch (ItemNotFoundException item) {
            shared_util.handleException(item);
            //shared_util.setSuccessMsgValue("FailureInUpdate");
        }

        setSelectedRadio("");

    }

    public void cancelAdd() {
        super.cancelAdd();
        setPageMode(0);
        reInitializePageDTO();

    }


    public void cancelEdit() {
        setPageMode(0);
    }

    public String back() {
        String back = super.back();
        CountriesListBean bean =
            (CountriesListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
                                                                                                     this.getMasterBackBeanName() +
                                                                                                     "}").getValue(FacesContext.getCurrentInstance());
        bean.setSelectedDTOS(new ArrayList<IBasicDTO>());
        //        bean.getSelectedDTOS().add(getMasterDTO());
        //        bean.setSelectedPageDTO(getMasterDTO());
        bean.setHighlightedDTOS(new ArrayList<IBasicDTO>());
        bean.getHighlightedDTOS().add(getMasterDTO());
        return back;
    }
}
