package com.beshara.jsfbase.csc.util.popup;

import com.beshara.csc.sharedutils.business.util.PopupDTO;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;


public class PopupBean implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private List<PopupDTO> popupList = new ArrayList<PopupDTO>();
    private String popupSearchMethod;
    private String popupMethod;
    private String searchString;
    private boolean[] checkboxes;
    private boolean pageCheckBoxType;
    private transient HtmlDataTable table;
    private Long longRadioSelected;
    private String stringRadioSelected;
    private String popupReturnMethod;
    private boolean searchMode = false;
    private String formName;
    private String choosen = ",";

    public PopupBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ex = ctx.getExternalContext();
        HttpServletRequest req = (HttpServletRequest)ex.getRequest();

        if (req.getParameter("popup_load_method") != null && 
            req.getParameter("popup_search_method") != null) {
            popupMethod = req.getParameter("popup_load_method");
            popupSearchMethod = req.getParameter("popup_search_method");
            popupReturnMethod = req.getParameter("popup_return_method");
            String type = req.getParameter("page_type");
            formName = req.getParameter("form_name");
            if (type != null && type.equals("check"))
                pageCheckBoxType = true;
            else
                pageCheckBoxType = false;

            viewAll();
            checkboxes = new boolean[popupList.size()];
        }
    }


    public void setPopupList(List<PopupDTO> popupList) {
        this.popupList = popupList;
    }

    public List<PopupDTO> getPopupList() {
        return popupList;
    }

    public void setPopupSearchMethod(String popupSearchMethod) {
        this.popupSearchMethod = popupSearchMethod;
    }

    public String getPopupSearchMethod() {
        return popupSearchMethod;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setPopupMethod(String popupMethod) {
        this.popupMethod = popupMethod;
    }

    public String getPopupMethod() {
        return popupMethod;
    }

    public void setCheckboxes(boolean[] checkboxes) {
        this.checkboxes = checkboxes;
    }

    public boolean[] getCheckboxes() {
        return checkboxes;
    }

    public void setPageCheckBoxType(boolean pageCheckType) {
        this.pageCheckBoxType = pageCheckType;
    }

    public boolean isPageCheckBoxType() {
        return pageCheckBoxType;
    }

    public void setTable(HtmlDataTable table) {
        this.table = table;
    }

    public HtmlDataTable getTable() {
        return table;
    }

    public void setLongRadioSelected(Long radioSelected) {
        if (radioSelected != null && radioSelected.intValue() != 0) {
            this.longRadioSelected = radioSelected;
        }
    }

    public Long getLongRadioSelected() {
        return longRadioSelected;
    }

    public void setStringRadioSelected(String stringRadioSelected) {
        if (stringRadioSelected != null && !stringRadioSelected.equals("")) {
            this.stringRadioSelected = stringRadioSelected;
        }
    }

    public String getStringRadioSelected() {
        return stringRadioSelected;
    }


    public void callReturn() {
        try {
            MethodBinding methodBinding = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                       popupReturnMethod + 
                                                                                       "}", 
                                                                                       new Class[] { String.class });
            methodBinding.invoke(FacesContext.getCurrentInstance(), 
                                 new Object[] { choosen });
            closeWindow();
        } catch (Exception ex) {
            closeWindow();
        }
    }

    private void closeWindow() {
        String javaScriptText = 
            "window.opener.blocker.style.visibility='hidden';window.opener.top.frames['body'].location.reload(true);window.close();";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        AddResource addResource = AddResourceFactory.getInstance(facesContext);
        addResource.addInlineScriptAtPosition(facesContext, 
                                              AddResource.HEADER_BEGIN, 
                                              javaScriptText);
    }

    public void search() {
        if (popupSearchMethod != null) {
            MethodBinding methodBinding = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                       popupSearchMethod + 
                                                                                       "}", 
                                                                                       new Class[] { String.class });
            popupList = 
                    (List<PopupDTO>)methodBinding.invoke(FacesContext.getCurrentInstance(), 
                                                         new Object[] { searchString });
            setLongRadioSelected(null);
            setStringRadioSelected(null);
            reposionPage(0);
            setSearchMode(true);
        }
    }

    private void reposionPage(int firstPos) {
        if (table != null)
            table.setFirst(firstPos);
        FacesContext.getCurrentInstance().renderResponse();
    }

    public void viewAll() {
        if (popupMethod != null) {
            MethodBinding methodBinding = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                       popupMethod + 
                                                                                       "}", 
                                                                                       null);
            popupList = 
                    (List<PopupDTO>)methodBinding.invoke(FacesContext.getCurrentInstance(), 
                                                         null);

            checkboxes = new boolean[popupList.size()];
            setLongRadioSelected(null);
            setSearchString(null);
            setStringRadioSelected(null);
            setSearchMode(false);
        }
    }

    /**
     * @return the List Size, using to indicate that the table scroller will be visable or not.
     */
    public Integer getListSize() {
        return popupList.size();
    }

    public void saveLongRadioState(ValueChangeEvent event) {
        Long newValue = (Long)event.getNewValue();
        if (newValue != null && longRadioSelected != null && 
            newValue.intValue() != longRadioSelected.intValue()) {
            setLongRadioSelected(newValue);
        }
    }

    public void saveStringRadioState(ValueChangeEvent event) {
        String newValue = (String)event.getNewValue();
        if (newValue != null && stringRadioSelected != null && 
            !newValue.equals(stringRadioSelected)) {
            setStringRadioSelected(newValue);
        }
    }

    public void setPopupReturnMethod(String popupReturnMethod) {
        this.popupReturnMethod = popupReturnMethod;
    }

    public String getPopupReturnMethod() {
        return popupReturnMethod;
    }

    public void setSearchMode(boolean searchMode) {
        this.searchMode = searchMode;
    }

    public boolean isSearchMode() {
        return searchMode;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormName() {
        return formName;
    }

    public void setChoosen(String choosen) {
        this.choosen = choosen;
    }

    public String getChoosen() {
        return choosen;
    }
}
