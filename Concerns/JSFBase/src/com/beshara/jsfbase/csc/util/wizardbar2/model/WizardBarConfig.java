package com.beshara.jsfbase.csc.util.wizardbar2.model;

import java.io.Serializable;

import java.util.Map;


public class WizardBarConfig implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    /*define file level defaults for wizard bar attributes can be ovirriden in the wizard level */

    /*styleclass of the wizard bar container*/
    private String styleClass = null;
    /*direction of the wizard bar*/
    private String dir = null;

    /*define file level defaults for wizard step attributes can be ovirriden in the wizard level or step level*/

    /* styleclass for the not visited step*/
    private String notVisitedStyleClass = null;

    /*styleclass for the active step*/
    private String currentStyleClass = null;

    /*styleclass for the visited step*/
    private String visitedStyleClass = null;

    /*styleclass for the disabled step  enabled=false*/
    private String disabledStyleClass = null;

    /* javascript function to be called when clicking the step*/
    private String onClick = null;
    private Map<String, Wizard> wizardMap;

    public WizardBarConfig() {
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getStyleClass() {
        return styleClass;
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

    public void setWizardMap(Map<String, Wizard> wizardMap) {
        this.wizardMap = wizardMap;
    }

    public Map<String, Wizard> getWizardMap() {
        return wizardMap;
    }
}
