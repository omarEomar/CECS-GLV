package com.beshara.csc.integration.hrm.emp.backingbean;


import com.beshara.base.deploy.SessionBeanProviderException;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.nl.reg.presentation.backingbean.decision.details.advanceSearch.AdvanceEmployeesAddBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.datascroller.ScrollerActionEvent;


public class EmployeeSearchBean extends LOVBaseBean {
    private String empListOfValuesStyle = "empListOfValuesStyle";
    private boolean searchAtAllEmployees;
    private Long ministryCode;
    private boolean cantLocateSession;
    private AdvanceEmployeesAddBean advanceEmployeesAddBean =
        (AdvanceEmployeesAddBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
                                                                                                       "advanceEmployeesAddBean" +
                                                                                                       "}").getValue(FacesContext.getCurrentInstance());

    public EmployeeSearchBean() {
        super.setMyTableData(new ArrayList());
        super.setSelectedDTOS(new ArrayList<IBasicDTO>());
        super.setSelectedRadio("");
        super.setSearchTyp("1");
        super.setSearchQuery("");
        super.setSearchMode(false);
        super.getOkCommandButton().setReRender("continerDiv,SaveButton");
    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {
        return new PagingResponseDTO(new ArrayList());
    }

    public void openLovDiv(ActionEvent ae) {
        Boolean manyToMany = (Boolean)evaluateValueBinding("appMainLayout.manyToMany");
        String beanNameBindingKey = "pageBeanName";
        if (manyToMany != null && manyToMany) {
            beanNameBindingKey = "detailBeanName";
        }

        BaseBean currentBaseBean = (BaseBean)evaluateValueBinding(beanNameBindingKey);
        currentBaseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");


        if (isUsingPaging() && ae != null && ae instanceof ScrollerActionEvent) {
            super.changePageIndex(ae);
        }
    }

    /**by Ashraf Gaber to reset LOV attributes*/
    public void resetData() {
        super.resetData();
        setCantLocateSession(false);
    }

    public void hideLovDiv() {
        setMyTableData(new ArrayList<IBasicDTO>());
        setSelectedRadio("");
        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }
        resetData();
    }

    public String goToAdvancedSearch() {
        try {
            advanceEmployeesAddBean.setSearchMethod("advanceEmployeesAddBean.filterSearchByEmpWithPaging");
            advanceEmployeesAddBean.setShowResultWithinPage(false);
            advanceEmployeesAddBean.setShowCategoryCB(true);
            advanceEmployeesAddBean.setShowMinistryCB(true);
            advanceEmployeesAddBean.setShowWorkEndDate(false);
            advanceEmployeesAddBean.setShowHireStatus(false);
            advanceEmployeesAddBean.setShowCurrentHireStatus(false);
            advanceEmployeesAddBean.setShowWorkCenterLovDiv(true);
            advanceEmployeesAddBean.setShowJobLovDiv(true);
            advanceEmployeesAddBean.setUsingBsnPaging(true);
            advanceEmployeesAddBean.setUsingPaging(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Advanced_Search_Employees";
    }

    public String searchEmployees() {
        setSelectedRadio("");
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        Object[] params = new Object[2];
        params[0] = getSearchTyp();
        params[1] = getSearchQuery();
        setSearchMode(true);
        resetPageIndex();
        if (isUsingPaging()) {
            setUpdateMyPagedDataModel(true);
        }

        if (!"".equals(getSearchMethod()) && getSearchMethod() != null) {
            return super.searchLovDiv();
        } else {
            searchForEmployee(getSearchTyp(), getSearchQuery());
            return "";
        }

    }

    public String cancelSearchLovDiv() {
        setSearchMode(false);
        setSelectedRadio("");
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        setSearchQuery("");
        setSearchTyp("1");

        setCantLocateSession(false);

        if (isUsingPaging()) {
            getPagingBean().updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 0));
        }

        if (!"".equals(getCancelSearchMethod()) && getCancelSearchMethod() != null) {
            return (String)executeMethodBinding(getCancelSearchMethod(), null);
        } else {
            super.setMyTableData(new ArrayList());
            super.setSelectedDTOS(new ArrayList<IBasicDTO>());
        }


        return "";
    }


    public void searchForEmployee(Object searchType, Object searchQuery) {
        if (searchQuery != null && !searchQuery.toString().equals("") && getSearchTyp() != null &&
            !searchType.equals("")) {
            super.setSearchMode(true);
            IEmpEmployeeSearchDTO empEmployeeSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
            if (getSearchTyp().toString().equals("1"))
                empEmployeeSearchDTO.setCivilId(Long.valueOf(searchQuery.toString()));
            else if (getSearchTyp().toString().equals("0"))
                empEmployeeSearchDTO.setEmpName(searchQuery.toString());
            try {
                setMyTableData(EmpClientFactory.getEmployeesClient().getAllEmployeesByPremittedMinistries(empEmployeeSearchDTO));
            } catch (SessionBeanProviderException e) { // TODO
                e.printStackTrace();
                super.setMyTableData(new ArrayList());
                cantLocateSession = true;
                super.setSearchMode(false);
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                super.setMyTableData(new ArrayList());
                super.setSearchMode(false);
            } catch (DataBaseException e) {
                e.printStackTrace();
                super.setMyTableData(new ArrayList());
                super.setSearchMode(false);
            }
        } else {
            super.setErrorMsgValue(getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.global",
                                                                  "missingField"));
            super.setSearchMode(false);
        }
        super.repositionPage(0);
        super.setSelectedDTOS(new ArrayList<IBasicDTO>(0));

    }


    public void setEmpListOfValuesStyle(String empListOfValuesStyle) {
        this.empListOfValuesStyle = empListOfValuesStyle;
    }

    public String getEmpListOfValuesStyle() {
        return empListOfValuesStyle;
    }


    public void setSearchAtAllEmployees(boolean searchAtAllEmployees) {
        this.searchAtAllEmployees = searchAtAllEmployees;
    }

    public boolean isSearchAtAllEmployees() {
        return searchAtAllEmployees;
    }

    public void setMinistryCode(Long ministryCode) {
        this.ministryCode = ministryCode;
    }

    public Long getMinistryCode() {
        return ministryCode;
    }

    public void setCantLocateSession(boolean cantLocateSession) {
        this.cantLocateSession = cantLocateSession;
    }

    public boolean isCantLocateSession() {
        return cantLocateSession;
    }

    public void setAdvanceEmployeesAddBean(AdvanceEmployeesAddBean advanceEmployeesAddBean) {
        this.advanceEmployeesAddBean = advanceEmployeesAddBean;
    }

    public AdvanceEmployeesAddBean getAdvanceEmployeesAddBean() {
        return advanceEmployeesAddBean;
    }
}
