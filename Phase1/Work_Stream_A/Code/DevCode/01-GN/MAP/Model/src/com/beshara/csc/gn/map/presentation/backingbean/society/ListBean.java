package com.beshara.csc.gn.map.presentation.backingbean.society;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class ListBean extends LookUpBaseBean {
    private static final Long oneLong = 1L;
    private static final String BUNDLE_NAME = "com.beshara.csc.gn.map.presentation.resources.map";
    private static final String CENTER_CODE = "1";
    private static final String SYSTEM_CODE = "2";
    private static final String OTHER_MIN_CODE = "3";
    private String selectedTypeCode;

    public ListBean() {
        setPageDTO(MapDTOFactory.createSocietiesDTO());
        setSelectedPageDTO(MapDTOFactory.createSocietiesDTO());
        setClient(MapClientFactory.getSocietiesClient());
        setSingleSelection(true);
        setSaveSortingState(true);
        setSelectedTypeCode(getSystemCode());
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        app.setShowContent1(true);
        app.setShowpaging(true);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(MapDTOFactory.createSocietiesDTO());
    }

    public void search() throws DataBaseException, NoResultException {
        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }

    public List getBaseSearchResult() throws DataBaseException {

        List searchResult = new ArrayList();

        try {
            if (getSearchType().intValue() == 0 && SYSTEM_CODE.equals(selectedTypeCode)) {
                searchResult = getClient().searchByCode(getSearchEntityObj());
            } else if (getSearchType().intValue() == 0 && CENTER_CODE.equals(selectedTypeCode)) {
                searchResult = MapClientFactory.getSocietiesClient().searchByMinCode(new Long(this.getSearchQuery()));
            } else if (getSearchType().intValue() == 1 && SYSTEM_CODE.equals(selectedTypeCode)) {
                searchResult =
                        MapClientFactory.getSocietiesClient().searchByNameAndStatus(formatSearchString(getSearchQuery()),
                                                                                    SYSTEM_CODE);
            } else if (getSearchType().intValue() == 1 && CENTER_CODE.equals(selectedTypeCode)) {
                searchResult =
                        MapClientFactory.getSocietiesClient().searchByNameAndStatus(formatSearchString(getSearchQuery()),
                                                                                    CENTER_CODE);
            }

        } catch (ItemNotFoundException e) { //this means no search results found
            searchResult = new ArrayList();
            setMyTableData(new ArrayList());
        } catch (NoResultException ne) { //this means no search results found
            searchResult = new ArrayList();
            setMyTableData(new ArrayList());
        } catch (Exception db) {
            searchResult = new ArrayList();
            setMyTableData(new ArrayList());
            //            ErrorDisplay errorDisplay =
            //                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            //
            //            errorDisplay.setErrorMsgKey(db.getMessage());
            //            errorDisplay.setSystemException(true);
            //            throw new DataBaseException(db.getMessage());

        }

        return searchResult;
    }

    public String goToAdd() {
        SocietyAddBean societyAddBean =
            (SocietyAddBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{societyAddBean}").getValue(FacesContext.getCurrentInstance());
        societyAddBean.setSelectedRadioValue(getSelectedTypeCode());
        societyAddBean.selectedValueChanged();
        return "societyadd";
    }

    public void fillDataTable() {
        getTotalList();
        resetPageIndex();
        setSorting(false);
        this.setSearchQuery("");
        this.setSearchType(0);
        this.setSearchMode(false);
        this.setSelectedRadio("");

    }

    public List getTotalList() {
        List totalList = null;
        try {
            if (getSelectedTypeCode() != null) {
                totalList = MapClientFactory.getSocietiesClient().getAllByFalg(Long.valueOf(getSelectedTypeCode()));
                setMyTableData(totalList);
            }
        } catch (SharedApplicationException ne) {
            totalList = new ArrayList();
            setMyTableData(new ArrayList());
        } catch (DataBaseException db) {
            getSharedUtil().handleException(db);
            setMyTableData(new ArrayList());
        } catch (Exception e) {
            getSharedUtil().handleException(e);
            setMyTableData(new ArrayList());
        }
        return totalList;
    }


    public void showEditDiv() {

        SharedUtilBean sharedUtil = getSharedUtil();
        if (this.getSelectedDTOS() != null && this.getSelectedDTOS().size() == 1) {
            IBasicDTO dto = this.getSelectedDTOS().get(0);
            if (oneLong.equals(((ISocietiesDTO)dto).getSocietiesStatus())) {
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "cannotEditTypeMin"));
            } else {

                try {
                    setSelectedPageDTO(getClient().getByIdInCenter(dto.getCode()));
                }

                catch (Exception e) {
                    e.printStackTrace();
                }
                setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupEditDiv);");
                setShowEdit(true);

            }
        } else {
            setSelectedPageDTO(new BasicDTO());
            setShowEdit(false);
        }
        if (!lock()) {
            return;
        }
        setPageMode(2);
        //reIntializeMsgs();
    }

    public String getCenterCode() {
        return CENTER_CODE;
    }

    public String getSystemCode() {
        return SYSTEM_CODE;
    }
    
    public String getOtherMinistriesCode() {
        return OTHER_MIN_CODE;
    }

    public void setSelectedTypeCode(String selectedTypeCode) {
        this.selectedTypeCode = selectedTypeCode;
    }

    public String getSelectedTypeCode() {
        return selectedTypeCode;
    }
}
