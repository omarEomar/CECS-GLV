package com.beshara.jsfbase.csc.util.wizardbar2.config.impl.xml.adapter;

import com.beshara.jsfbase.csc.util.wizardbar2.model.Step;


public class StepXMLAdapter extends Step {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Step step;

    public StepXMLAdapter() {
    }

    @Override
    public void setExtend(String extend) {
        step.setExtend(extend);
    }

    @Override
    public String getExtend() {
        return step.getExtend();
    }

    @Override
    public void setId(String id) {
        step.setId(id);
    }

    @Override
    public String getId() {
        return step.getId();
    }

    @Override
    public void setLabel(String label) {
        step.setLabel(label);
    }

    @Override
    public String getLabel() {
        return step.getLabel();
    }

    @Override
    public void setTitle(String title) {
        step.setTitle(title);
    }

    @Override
    public String getTitle() {
        return step.getTitle();
    }

    @Override
    public void setNavigation(String navigation) {
        step.setNavigation(navigation);
    }

    @Override
    public String getNavigation() {
        return step.getNavigation();
    }

    @Override
    public void setEnabled(String enabled) {
        step.setEnabled(enabled);
    }

    @Override
    public String getEnabled() {
        return step.getEnabled();
    }

    @Override
    public void setRendered(String rendered) {
        step.setRendered(rendered);
    }

    @Override
    public String getRendered() {
        return step.getRendered();
    }

    @Override
    public void setNotVisitedStyleClass(String notVisitedStyleClass) {
        step.setNotVisitedStyleClass(notVisitedStyleClass);
    }

    @Override
    public String getNotVisitedStyleClass() {
        return step.getNotVisitedStyleClass();
    }

    @Override
    public void setCurrentStyleClass(String currentStyleClass) {
        step.setCurrentStyleClass(currentStyleClass);
    }

    @Override
    public String getCurrentStyleClass() {
        return step.getCurrentStyleClass();
    }

    @Override
    public void setVisitedStyleClass(String visitedStyleClass) {
        step.setVisitedStyleClass(visitedStyleClass);
    }

    @Override
    public String getVisitedStyleClass() {
        return step.getVisitedStyleClass();
    }

    @Override
    public void setDisabledStyleClass(String disabledStyleClass) {
        step.setDisabledStyleClass(disabledStyleClass);
    }

    @Override
    public String getDisabledStyleClass() {
        return step.getDisabledStyleClass();
    }

    @Override
    public void setOnClick(String onClick) {
        step.setOnClick(onClick);
    }

    @Override
    public String getOnClick() {
        return step.getOnClick();
    }

    @Override
    public void setOnValidate(String onValidate) {
        step.setOnValidate(onValidate);
    }

    @Override
    public String getOnValidate() {
        return step.getOnValidate();
    }

    @Override
    public void setRequired(String required) {
        step.setRequired(required);
    }

    @Override
    public String getRequired() {
        return step.getRequired();
    }

    @Override
    public void setOnSaveState(String onSaveState) {
        step.setOnSaveState(onSaveState);
    }

    @Override
    public String getOnSaveState() {
        return step.getOnSaveState();
    }

    public void setNavBar(NavBarXMLAdaper navBar) {
        step.setNavBar(navBar);
    }

    @Override
    public NavBarXMLAdaper getNavBar() {
        return (NavBarXMLAdaper)step.getNavBar();
    }
}
