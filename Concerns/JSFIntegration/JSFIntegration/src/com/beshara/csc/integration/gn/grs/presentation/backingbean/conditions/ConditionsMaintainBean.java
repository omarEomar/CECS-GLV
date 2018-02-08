package com.beshara.csc.gn.grs.presentation.backingbean.conditions;

import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.IConditionsClient;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


public class ConditionsMaintainBean extends ManyToManyMaintainBaseBean {
    private static final String BEAN_NAME = "conditionsMaintainBean";

    public ConditionsMaintainBean() {

        setClient((IConditionsClient)GrsClientFactory.getConditionsClient());
        setCurrentStep("conditionmantian"); //map key for first step(exist in wizard bar)
        setNextNavigationCase("conditionbaseLines"); //map key for second step(exist in wizard bar)
        setFinishNavigationCase("condition_list"); //navigation case exist in faces config
    }


    public static ConditionsMaintainBean getInstance() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (ConditionsMaintainBean)app.createValueBinding("#{" + 
                                                              BEAN_NAME + 
                                                              "}").getValue(ctx);
    }

    public String finish() throws DataBaseException, ItemNotFoundException, 
                                  SharedApplicationException {


        ConditionLineDetailBean conditionLineDetailBean = 
            (ConditionLineDetailBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{conditionLineDetailBean}").getValue(FacesContext.getCurrentInstance());
        
        if (conditionLineDetailBean.getCurrentListSize() != 0) {
            if (getIntegrationBean() != null && 
                getIntegrationBean().getModuleFrom() != null && 
                !getIntegrationBean().getModuleFrom().equals("")) {
                getIntegrationBean().getSelectedDTOTo().add(getPageDTO());
                return getIntegrationBean().goFrom();
            } else {
                return defaultAddCondition();
            }
        }
        return null;

    }

    public String defaultAddCondition() throws DataBaseException, 
                                               ItemNotFoundException, 
                                               SharedApplicationException {

        String defultFinish = super.finish();
        ConditionListBean listPage = 
            (ConditionListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{conditionListBean}").getValue(FacesContext.getCurrentInstance());
        listPage.getMyTableData();

        if (getPageDTO().getCode() != null) {
            listPage.getPageIndex((String)getPageDTO().getCode().getKey());
            listPage.getHighlightedDTOS().add(getPageDTO());
        }

        return defultFinish;
    }

    public String back() {
        if (getIntegrationBean() != null && 
            getIntegrationBean().getModuleFrom() != null && 
            !getIntegrationBean().getModuleFrom().equals("")) {
            return getIntegrationBean().cancelGoTO();
        }
        return super.back();
    }

}
