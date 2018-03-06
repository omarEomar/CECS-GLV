package com.beshara.csc.inf.presentation.backingbean.maindata.kwtmap;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.util.ArrayList;

import javax.faces.event.ActionEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import org.apache.myfaces.component.html.ext.HtmlCommandButton;
import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


public class KwStreetZoneLinkBean extends BaseBean {
    private String returnMethodName;
    private HtmlAjaxCommandButton okCommandButton = new HtmlAjaxCommandButton();

    private String searchMethod;
    private String cancelSearchMethod;
    private String searchTyp = "1";
    private String renderedDropDown = "";
    private String errorMsgValue = "";
    private Long keyIndex;
    public boolean multiSelect;
    private int pageIndexLov = 0;
    private HtmlDataScroller dataScroller = new HtmlDataScroller();
    private String onCompleteList = "hideLookUpDiv(window.blocker,window.divLov)";

    // Added By AMR_ABDO for accept Code of type String
    private boolean codeTypeString;
    private boolean cleanDataTableFlage = false;

    private boolean resetDivAfterClose;
    private boolean resetDivAfterHide;

    private String lovDivLabelSearch = "";
    private HtmlCommandButton okBtnLovDiv;

    public KwStreetZoneLinkBean() {
        setSaveSortingState(true);
    }
    //called when user select Ok button

    public String closeLovDiv() {
        String ret = "";
        if (returnMethodName != null && !returnMethodName.equals("")) {
            ret = (String)executeMethodBinding(returnMethodName, null);
        }
        if (resetDivAfterClose) {
            resetData();
        }
        return ret;
    }

    public void hideLovDiv() {

        if (cleanDataTableFlage == false) {
            setMyTableData(new ArrayList<IBasicDTO>());
        }
        setSelectedRadio("");
        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }
        if (resetDivAfterHide || resetDivAfterClose) {
            resetData();
        }
    }

    /**by Ashraf Gaber to reset LOV attributes*/
    public void resetData() {
        if (isUsingPaging()) {
            getPagingBean().updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 0));
        }
        setMyTableData(new ArrayList());
        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }
        setSelectedRadio("");
        setSearchQuery("");
        setSearchMode(false);
        setSearchTyp("1");
        resetPageIndex();
    }

    //called when user use scrolling in lov div

    public void openLovDiv(ActionEvent ae) {
        Boolean manyToMany = (Boolean)evaluateValueBinding("appMainLayout.manyToMany");
        String beanNameBindingKey = "pageBeanName";
        if (manyToMany != null && manyToMany) {
            beanNameBindingKey = "detailBeanName";
        }

        BaseBean currentBaseBean = (BaseBean)evaluateValueBinding(beanNameBindingKey);
        currentBaseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.divLov);");
        //if(!isMultiSelect()){
        Integer listSize = (Integer)evaluateValueBinding(beanNameBindingKey + ".lovBaseBean.selectedListSize");
        Boolean flage = listSize == 0 ? true : false;
        if (getOkBtnLovDiv() != null)
            getOkBtnLovDiv().setDisabled(flage);
        //}
        //Paging
        if (isUsingPaging()) {
            super.changePageIndex(ae);
        }

    }

    /**
     * Purpose: set search type and its value and excute search method in the calling bean
     * Creation/Modification History :
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  25-DEC-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public String searchLovDiv() {
        Object[] params = new Object[2];
        params[0] = getSearchTyp();
        params[1] = getSearchQuery();
        setSearchMode(true);
        setSearchMode(true);
        this.setSelectedRadio("");
        this.setSelectedDTOS(new ArrayList<IBasicDTO>());

        //        try {
        //            if (getListSize() == 0)
        //                setSelectedRadio("");
        //        } catch (DataBaseException e) {
        //            e.printStackTrace();
        //        }

        String returnValue =
            (String)executeMethodBindingWithParams(searchMethod, params, new Class[] { Object.class, Object.class });

        this.repositionPage(0);

        //        if (returnValue == "" || returnValue == null){
        //            setSelectedRadio("");
        //            setSelectedDTOS(new ArrayList<IBasicDTO>());
        //        }

        return returnValue;

    }

    /**
     * Purpose: cancel search and call cancel search method in the calling bean
     * Creation/Modification History :
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  28-DEC-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public String cancelSearchLovDiv() {
        setSearchMode(false);
        setSearchQuery("");
        searchTyp = "1";
        setSelectedRadio("");
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        return (String)executeMethodBinding(cancelSearchMethod, null);
    }


    //----------------------Setters & Getters----------------------------------------------------

    public void setReturnMethodName(String returnMethodName) {
        this.returnMethodName = returnMethodName;
    }

    public String getReturnMethodName() {
        return returnMethodName;
    }

    public void setOkCommandButton(HtmlAjaxCommandButton okCommandButton) {
        this.okCommandButton = okCommandButton;
    }

    public HtmlAjaxCommandButton getOkCommandButton() {
        return okCommandButton;
    }

    public void setSearchMethod(String searchMethod) {
        this.searchMethod = searchMethod;
    }

    public String getSearchMethod() {
        return searchMethod;
    }

    public void setSearchTyp(String searchTyp) {
        this.searchTyp = searchTyp;
    }

    public String getSearchTyp() {
        return searchTyp;
    }

    public void setCancelSearchMethod(String cancelSearchMethod) {
        this.cancelSearchMethod = cancelSearchMethod;
    }

    public String getCancelSearchMethod() {
        return cancelSearchMethod;
    }

    public void setRenderedDropDown(String renderedDropDown) {
        this.renderedDropDown = renderedDropDown;
    }

    public String getRenderedDropDown() {
        return renderedDropDown;
    }


    public void setErrorMsgValue(String errorMsgValue) {
        this.errorMsgValue = errorMsgValue;
    }

    public String getErrorMsgValue() {
        return errorMsgValue;
    }

    public void setKeyIndex(Long keyIndex) {
        this.keyIndex = keyIndex;
    }

    public Long getKeyIndex() {
        return keyIndex;
    }

    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
    }

    public void setPageIndexLov(int pageIndexLov) {
        this.pageIndexLov = pageIndexLov;
    }

    public int getPageIndexLov() {
        if (dataScroller != null) {
            pageIndexLov = dataScroller.getPageIndex();
        }
        return pageIndexLov;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setOnCompleteList(String onCompleteList) {
        this.onCompleteList = onCompleteList;
    }

    public String getOnCompleteList() {
        return onCompleteList;
    }

    public void setCodeTypeString(boolean codeTypeString) {
        this.codeTypeString = codeTypeString;
    }

    public boolean isCodeTypeString() {
        return codeTypeString;
    }

    public void setCleanDataTableFlage(boolean cleanDataTableFlage) {
        this.cleanDataTableFlage = cleanDataTableFlage;
    }

    public boolean isCleanDataTableFlage() {
        return cleanDataTableFlage;
    }

    public void setLovDivLabelSearch(String lovDivLabelSearch) {
        this.lovDivLabelSearch = lovDivLabelSearch;
    }

    public String getLovDivLabelSearch() {
        return lovDivLabelSearch;
    }

    public void setResetDivAfterClose(boolean resetDivAfterClose) {
        this.resetDivAfterClose = resetDivAfterClose;
    }

    public boolean isResetDivAfterClose() {
        return resetDivAfterClose;
    }

    public void setResetDivAfterHide(boolean resetDivAfterHide) {
        this.resetDivAfterHide = resetDivAfterHide;
    }

    public boolean isResetDivAfterHide() {
        return resetDivAfterHide;
    }

    public void setOkBtnLovDiv(HtmlCommandButton okBtnLovDiv) {
        this.okBtnLovDiv = okBtnLovDiv;
    }

    public HtmlCommandButton getOkBtnLovDiv() {
        return okBtnLovDiv;
    }
}
