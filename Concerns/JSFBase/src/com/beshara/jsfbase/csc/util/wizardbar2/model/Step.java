package com.beshara.jsfbase.csc.util.wizardbar2.model;

import java.io.Serializable;


public class Step implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    //step-id
    private String extend = null;
    private String id = null;
    private String label = null;
    private String navigation = null;
    private String title = null;
    private String enabled = null;
    private String rendered = null;
    private String notVisitedStyleClass = null;
    private String currentStyleClass = null;
    private String visitedStyleClass = null;
    private String disabledStyleClass = null;
    private String onClick = null;
    private String onValidate = null;
    private String required = null;
    private String onSaveState = null;
    private NavBar navBar;
    private String disabled = null;
    private String onblur = null;
    private String onmousedown = null;

    public Step() {
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getExtend() {
        return extend;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    public String getNavigation() {
        return navigation;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public String getRendered() {
        return rendered;
    }

    public void setNotVisitedStyleClass(String notVisitedStyleClass) {
        this.notVisitedStyleClass = notVisitedStyleClass;
    }

    public String getNotVisitedStyleClass() {
        return notVisitedStyleClass;
    }

    public void setCurrentStyleClass(String currentStyleClass) {
        this.currentStyleClass = currentStyleClass;
    }

    public String getCurrentStyleClass() {
        return currentStyleClass;
    }

    public void setVisitedStyleClass(String visitedStyleClass) {
        this.visitedStyleClass = visitedStyleClass;
    }

    public String getVisitedStyleClass() {
        return visitedStyleClass;
    }

    public void setDisabledStyleClass(String disabledStyleClass) {
        this.disabledStyleClass = disabledStyleClass;
    }

    public String getDisabledStyleClass() {
        return disabledStyleClass;
    }

    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    public String getOnClick() {
        return onClick;
    }

    public void setOnValidate(String onValidate) {
        this.onValidate = onValidate;
    }

    public String getOnValidate() {
        return onValidate;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getRequired() {
        return required;
    }

    public void setOnSaveState(String onSaveState) {
        this.onSaveState = onSaveState;
    }

    public String getOnSaveState() {
        return onSaveState;
    }

    public void setNavBar(NavBar navBar) {
        this.navBar = navBar;
    }

    public NavBar getNavBar() {
        return navBar;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setOnblur(String onblur) {
        this.onblur = onblur;
    }

    public String getOnblur() {
        return onblur;
    }

    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }

    public String getOnmousedown() {
        return onmousedown;
    }
}
