package com.beshara.jsfbase.csc.util.wizardbar2.config.impl.xml.adapter;

import com.beshara.jsfbase.csc.util.wizardbar2.model.Step;
import com.beshara.jsfbase.csc.util.wizardbar2.model.Wizard;

import java.util.Map;


public class WizardXMLAdapter extends Wizard {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Wizard wizard;

    public WizardXMLAdapter() {
    }

    @Override
    public void setExtend(String extend) {
        wizard.setExtend(extend);
    }

    @Override
    public String getExtend() {
        return wizard.getExtend();
    }

    @Override
    public void setId(String id) {
        wizard.setId(id);
    }

    @Override
    public String getId() {
        return wizard.getId();
    }

    @Override
    public void setRendered(String rendered) {
        wizard.setRendered(rendered);
    }

    @Override
    public String getRendered() {
        return wizard.getRendered();
    }

    @Override
    public void setNotVisitedStyleClass(String notVisitedStyleClass) {
        wizard.setNotVisitedStyleClass(notVisitedStyleClass);
    }

    @Override
    public String getNotVisitedStyleClass() {
        return wizard.getNotVisitedStyleClass();
    }

    @Override
    public void setCurrentStyleClass(String currentStyleClass) {
        wizard.setCurrentStyleClass(currentStyleClass);
    }

    @Override
    public String getCurrentStyleClass() {
        return wizard.getCurrentStyleClass();
    }

    @Override
    public void setVisitedStyleClass(String visitedStyleClass) {
        wizard.setVisitedStyleClass(visitedStyleClass);
    }

    @Override
    public String getVisitedStyleClass() {
        return wizard.getVisitedStyleClass();
    }

    @Override
    public void setDisabledStyleClass(String disabledStyleClass) {
        wizard.setDisabledStyleClass(disabledStyleClass);
    }

    @Override
    public String getDisabledStyleClass() {
        return wizard.getDisabledStyleClass();
    }

    @Override
    public void setOnClick(String onClick) {
        wizard.setOnClick(onClick);
    }

    @Override
    public String getOnClick() {
        return wizard.getOnClick();
    }

    @Override
    public void setOnValidate(String onValidate) {
        wizard.setOnValidate(onValidate);
    }

    @Override
    public String getOnValidate() {
        return wizard.getOnValidate();
    }

    public void setNavBar(NavBarXMLAdaper navBar) {
        wizard.setNavBar(navBar);
    }

    @Override
    public NavBarXMLAdaper getNavBar() {
        return (NavBarXMLAdaper)wizard.getNavBar();
    }

    @Override
    public void setBackNavigation(String backNavigation) {
        wizard.setBackNavigation(backNavigation);
    }

    @Override
    public String getBackNavigation() {
        return wizard.getBackNavigation();
    }

    @Override
    public void setOnFinish(String onFinish) {
        wizard.setOnFinish(onFinish);
    }

    @Override
    public String getOnFinish() {
        return wizard.getOnFinish();
    }

    @Override
    public void setOnCancel(String onCancel) {
        wizard.setOnCancel(onCancel);
    }

    @Override
    public String getOnCancel() {
        return wizard.getOnCancel();
    }

    @Override
    public void setStepMap(Map<String, Step> stepMap) {
        wizard.setStepMap(stepMap);
    }

    @Override
    public Map<String, Step> getStepMap() {
        return wizard.getStepMap();
    }

    @Override
    public void setDir(String dir) {
        wizard.setDir(dir);
    }

    @Override
    public String getDir() {
        return wizard.getDir();
    }
}
