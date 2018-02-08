package com.beshara.csc.gn.inf.integration.presentation.backingbean.countries;


import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.inf.integration.presentation.shared.IConstants;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


/**
 * @author I.Omar
 * @since 21-01-2015
 * */
public class CountriesIntegrationBean extends LookUpBaseBean {
    @SuppressWarnings("compatibility:-3760491046269944703")
    private static final long serialVersionUID = 1L;

    private String divSearchType = IConstants.SEARCH_TYPE_CODE;
    private String okButtonMethod;
    private String containerBeanName;
    private String divName = "integrationDiv1";
    
    private List<String> excludedCntryCodeList = new ArrayList(); 

    public CountriesIntegrationBean() {
        setSingleSelection(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowdatatableContent(true);
        return app;
    }

    public void openCountriesDiv(ActionEvent ae) {
        this.getAll();
        getContainerBean().setJavaScriptCaller("changeVisibilityDiv(window.blocker,window."+divName+");");
    }

    public void back() throws DataBaseException {
        this.cancelSearch();
        if(getSelectedDTOS() != null){
            getSelectedDTOS().clear();
        }
    }

    public void cancelSearch() throws DataBaseException {
        this.setSearchQuery("");
        this.setDivSearchType("0");
        this.setSearchMode(false);
        this.setSelectedRadio("");
        getSelectedDTOS().clear();
        if (isUsingPaging()) {
            getPagingBean().cancelSearch();
        } else {
            this.getAll();
            repositionPage(0);
        }
    }

    public void selectCountry() {
        if (getOkButtonMethod() != null) {
            JSFHelper.callMethod(getOkButtonMethod());
        }
        try {
            this.cancelSearch();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    public void getAll() {
        try {
            setMyTableData(InfClientFactory.getCountriesClient().getAllWithoutExcludedList(excludedCntryCodeList));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
        } catch (DataBaseException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
        }
    }

    public void search() {
        setSearchMode(true);
        try {
            if (divSearchType.equals(IConstants.SEARCH_TYPE_CODE)) {
                setMyTableData(InfClientFactory.getCountriesClient().searchCountries(Long.parseLong(getSearchQuery()) , excludedCntryCodeList));

            } else if (divSearchType.equals(IConstants.SEARCH_TYPE_NAME)) {
                setMyTableData(InfClientFactory.getCountriesClient().searchCountries(getSearchQuery() , excludedCntryCodeList));
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
        }

    }

    public void setOkButtonMethod(String okButtonMethod) {
        this.okButtonMethod = okButtonMethod;
    }

    public String getOkButtonMethod() {
        return okButtonMethod;
    }

    public void setDivSearchType(String divSearchType) {
        this.divSearchType = divSearchType;
    }

    public String getDivSearchType() {
        return divSearchType;
    }

    protected BaseBean getContainerBean() {
        return (BaseBean)JSFHelper.getValue(getContainerBeanName());
    }

    public void setContainerBeanName(String containerBeanName) {
        this.containerBeanName = containerBeanName;
    }

    public String getContainerBeanName() {
        return containerBeanName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public String getDivName() {
        return divName;
    }

    public void setExcludedCntryCodeList(List<String> excludedCntryCodeList) {
        this.excludedCntryCodeList = excludedCntryCodeList;
    }

    public List<String> getExcludedCntryCodeList() {
        return excludedCntryCodeList;
    }
}
