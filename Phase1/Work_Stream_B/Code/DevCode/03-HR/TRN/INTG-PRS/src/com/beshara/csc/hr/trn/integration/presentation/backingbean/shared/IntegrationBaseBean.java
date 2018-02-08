package com.beshara.csc.hr.trn.integration.presentation.backingbean.trainingcourses;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.myfaces.custom.datascroller.HtmlDataScroller;


public class IntegrationBaseBean implements Serializable {

    private transient HtmlDataTable availableDataTable = new HtmlDataTable();
    private transient HtmlDataScroller dataScroller = new HtmlDataScroller();
    private transient HtmlAjaxCommandButton okCommandButton = new HtmlAjaxCommandButton();

    private boolean checkAllFlagAvailable;
    private List<IBasicDTO> selectedDTOSList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> availableDetails = new ArrayList<IBasicDTO>();

    private String containerBeanName;
    private String divId = "integrationDiv1";
    private String treeDivDetailsId = "divTreeDetails";
    private boolean treePage;
    private int availableListSize = 0;

    private String returnMethodName;
    private String preOpenMethodName;
    private boolean intgSearchMode;
    private boolean resetIntgDivAfterClose = true;
    private boolean resetIntgDivAfterHide = true;


    public IntegrationBaseBean() {
    }

    protected void writeShowDivJScript() {
        if (isTreePage()) {
            getContainerBean().setJavaScriptCaller("treeOperation(window." + getTreeDivDetailsId() +
                                                   ",window.blocker,window." + getDivId() + ",'backButtonIntegrationDiv1'); settingFoucsAtOrgIntgDiv();");
        } else {
            getContainerBean().setJavaScriptCaller("changeVisibilityDiv(window.blocker,window." + getDivId() +
                                                   ",'backButtonIntegrationDiv1'); settingFoucsAtOrgIntgDiv();");
        }
    }

    public void resetData() {
        setAvailableDetails(new ArrayList());
        if (getSelectedDTOSList() != null) {
            getSelectedDTOSList().clear();
        }
        setAvailableListSize(0);
        setIntgSearchMode(false);
        goFirstPage(availableDataTable);

    }
    public void goFirstPage(HtmlDataTable table) {
        if (table != null)
            table.setFirst(0);

    }
    public void scrollerAction(ActionEvent ae) {

        if (isTreePage()) {
            getContainerBean().setJavaScriptCaller("treeOperation(window." + getTreeDivDetailsId() +
                                                   ",window.blocker,window." + getDivId() + ",'minIntgDivBackBtn');");
        } else {
            getContainerBean().setJavaScriptCaller("changeVisibilityDiv(window.blocker,window." + getDivId() +
                                                   ",'minIntgDivBackBtn');");
        }
    }


    public void selectedCheckboxsForIntgDiv(ActionEvent event) throws Exception {

        System.out.println("selectedCheckboxs only one time");
        try {
            Set<IBasicDTO> selectedSet = new HashSet();
            selectedSet.addAll(getSelectedDTOSList());

            IClientDTO selected = (IClientDTO)getAvailableDataTable().getRowData();

            if (selected.getChecked()) {

                try {
                    selectedSet.add(selected);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                selected.setChecked(Boolean.TRUE);

                for (IBasicDTO item : selectedSet) {
                    if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                        selectedSet.remove(item);
                        break;
                    }
                }

                selected.setChecked(Boolean.FALSE);

            }

            getSelectedDTOSList().clear();
            getSelectedDTOSList().addAll(selectedSet);
            System.out.print(getSelectedDTOSList().size());
        } catch (Exception e) {

            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }

    }

    public void selectedCheckboxsAllForIntgDiv(ActionEvent event) throws Exception {

        try {
            Set<IBasicDTO> selectedSet = new HashSet();
            selectedSet.addAll(getSelectedDTOSList());

            Integer rowsCountPerPage =
                (Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());

            if (rowsCountPerPage == null) {

                throw new Exception("#{shared_util.noOfTableRows} return null");
            }
            int first = getAvailableDataTable().getFirst();

            for (int j = first; j < first + rowsCountPerPage.intValue(); j++) {

                getAvailableDataTable().setRowIndex(j);

                if (!getAvailableDataTable().isRowAvailable()) {
                    break;
                }

                IBasicDTO selected = (IBasicDTO)getAvailableDataTable().getRowData();

                if (isCheckAllFlagAvailable()) {

                    try {
                        selectedSet.add(selected);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    for (IBasicDTO item : selectedSet) {
                        if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                            selectedSet.remove(item);
                            break;
                        }
                    }

                }
            }

            getSelectedDTOSList().clear();
            getSelectedDTOSList().addAll(selectedSet);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Object executeMethodBinding(String methodBindingExepression, Object[] paramList) {

        MethodBinding methodBinding = null;
        if (paramList != null) {
            methodBinding =
                    FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + methodBindingExepression +
                                                                                           "}", new Class[] { });
        } else {
            methodBinding =
                    FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + methodBindingExepression +
                                                                                           "}", null);
        }
        return methodBinding.invoke(FacesContext.getCurrentInstance(), paramList);

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

    public String getVirtualConstValue() {
        return "-100";
    }

    public void setDivId(String divId) {
        this.divId = divId;
    }

    public String getDivId() {
        return divId;
    }

    public void setTreeDivDetailsId(String treeDivDetailsId) {
        this.treeDivDetailsId = treeDivDetailsId;
    }

    public String getTreeDivDetailsId() {
        return treeDivDetailsId;
    }

    public void setTreePage(boolean treePage) {
        this.treePage = treePage;
    }

    public boolean isTreePage() {
        return treePage;
    }

    public void setAvailableDataTable(HtmlDataTable availableDataTable) {
        this.availableDataTable = availableDataTable;
    }

    public HtmlDataTable getAvailableDataTable() {
        return availableDataTable;
    }

    public void setDataScroller(HtmlDataScroller dataScroller) {
        this.dataScroller = dataScroller;
    }

    public HtmlDataScroller getDataScroller() {
        return dataScroller;
    }

    public void setCheckAllFlagAvailable(boolean checkAllFlagAvailable) {
        this.checkAllFlagAvailable = checkAllFlagAvailable;
    }

    public boolean isCheckAllFlagAvailable() {
        return checkAllFlagAvailable;
    }

    public void setSelectedDTOSList(List<IBasicDTO> selectedDTOSList) {
        this.selectedDTOSList = selectedDTOSList;
    }

    public List<IBasicDTO> getSelectedDTOSList() {
        return selectedDTOSList;
    }

    public void setAvailableListSize(int availableListSize) {
        this.availableListSize = availableListSize;
    }

    public int getAvailableListSize() {
        if (availableDetails != null) {
            try {
                availableListSize = this.getAvailableDetails().size();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return availableListSize;
    }

    public void setAvailableDetails(List<IBasicDTO> availableDetails) {
        this.availableDetails = availableDetails;
    }

    public List<IBasicDTO> getAvailableDetails() {
        return availableDetails;
    }

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

    public void setPreOpenMethodName(String preOpenMethodName) {
        this.preOpenMethodName = preOpenMethodName;
    }

    public String getPreOpenMethodName() {
        return preOpenMethodName;
    }

    public void setResetIntgDivAfterClose(boolean resetIntgDivAfterClose) {
        this.resetIntgDivAfterClose = resetIntgDivAfterClose;
    }

    public boolean isResetIntgDivAfterClose() {
        return resetIntgDivAfterClose;
    }

    public void setResetIntgDivAfterHide(boolean resetIntgDivAfterHide) {
        this.resetIntgDivAfterHide = resetIntgDivAfterHide;
    }

    public boolean isResetIntgDivAfterHide() {
        return resetIntgDivAfterHide;
    }

    public void setIntgSearchMode(boolean intgSearchMode) {
        this.intgSearchMode = intgSearchMode;
    }

    public boolean isIntgSearchMode() {
        return intgSearchMode;
    }
}


