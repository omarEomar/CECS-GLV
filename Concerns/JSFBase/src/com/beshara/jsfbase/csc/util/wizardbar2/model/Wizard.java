package com.beshara.jsfbase.csc.util.wizardbar2.model;

import java.io.Serializable;

import java.util.Map;


public class Wizard implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    /*the id of the wizard this one will extend*/
    private String extend = null;
    private String id = null;
    private String rendered = null;
    private String dir = null;
    /*define wizardbar level defaults for step attributes can be ovirriden in the step level */
    private String notVisitedStyleClass = null;
    private String currentStyleClass = null;
    private String visitedStyleClass = null;
    private String disabledStyleClass = null;
    private String onClick = null;
    /*method that will be called when before exiting the step*/
    private String onValidate = null;
    /*navigation case will be used if oncanel or onfinish return null or not provided*/
    private String backNavigation = null;
    /*method to be executed when clicking wizard finish button*/
    private String onFinish = null;
    /*method to be executed when clicking wizard cancel button*/
    private String onCancel = null;
    /*wizard bar steps map*/
    private Map<String, Step> stepMap = null;
    /*wizard */
    private NavBar navBar;
    /*will be called when steps navitated format method(wizardinfo) will be called when navigation successful*/
    private String septsNavigationListener;
    /*will be called when custom button called format method (wizardinfo) will be called when navigation allowed*/
    private String defaultButtonsNavigationListener;

    public Wizard() {
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

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public String getRendered() {
        return rendered;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDir() {
        return dir;
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

    public void setBackNavigation(String backNavigation) {
        this.backNavigation = backNavigation;
    }

    public String getBackNavigation() {
        return backNavigation;
    }

    public void setOnFinish(String onFinish) {
        this.onFinish = onFinish;
    }

    public String getOnFinish() {
        return onFinish;
    }

    public void setOnCancel(String onCancel) {
        this.onCancel = onCancel;
    }

    public String getOnCancel() {
        return onCancel;
    }

    public void setStepMap(Map<String, Step> stepMap) {
        this.stepMap = stepMap;
    }

    public Map<String, Step> getStepMap() {
        return stepMap;
    }

    public void setNavBar(NavBar navBar) {
        this.navBar = navBar;
    }

    public NavBar getNavBar() {
        return navBar;
    }

    public void setSeptsNavigationListener(String septsNavigationListener) {
        this.septsNavigationListener = septsNavigationListener;
    }

    public String getSeptsNavigationListener() {
        return septsNavigationListener;
    }

    public void setDefaultButtonsNavigationListener(String defaultButtonsNavigationListener) {
        this.defaultButtonsNavigationListener = 
                defaultButtonsNavigationListener;
    }

    public String getDefaultButtonsNavigationListener() {
        return defaultButtonsNavigationListener;
    }
}
