package com.beshara.jsfbase.csc.util.wizardbar2.config.impl.xml.adapter;

import com.beshara.jsfbase.csc.util.wizardbar2.model.Wizard;
import com.beshara.jsfbase.csc.util.wizardbar2.model.WizardBarConfig;

import java.util.Map;


public class WizardBarConfigXMLAdapter extends WizardBarConfig {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private WizardBarConfig wizardBarConfig;

    public WizardBarConfigXMLAdapter() {
    }

    @Override
    public void setStyleClass(String styleClass) {
        wizardBarConfig.setStyleClass(styleClass);
    }

    @Override
    public String getStyleClass() {
        return wizardBarConfig.getStyleClass();
    }

    @Override
    public void setDir(String dir) {
        wizardBarConfig.setDir(dir);
    }

    @Override
    public String getDir() {
        return wizardBarConfig.getDir();
    }

    @Override
    public void setNotVisitedStyleClass(String notVisitedStyleClass) {
        wizardBarConfig.setNotVisitedStyleClass(notVisitedStyleClass);
    }

    @Override
    public String getNotVisitedStyleClass() {
        return wizardBarConfig.getNotVisitedStyleClass();
    }

    @Override
    public void setCurrentStyleClass(String currentStyleClass) {
        wizardBarConfig.setCurrentStyleClass(currentStyleClass);
    }

    @Override
    public String getCurrentStyleClass() {
        return wizardBarConfig.getCurrentStyleClass();
    }

    @Override
    public void setVisitedStyleClass(String visitedStyleClass) {
        wizardBarConfig.setVisitedStyleClass(visitedStyleClass);
    }

    @Override
    public String getVisitedStyleClass() {
        return wizardBarConfig.getVisitedStyleClass();
    }

    @Override
    public void setDisabledStyleClass(String disabledStyleClass) {
        wizardBarConfig.setDisabledStyleClass(disabledStyleClass);
    }

    @Override
    public String getDisabledStyleClass() {
        return wizardBarConfig.getDisabledStyleClass();
    }

    @Override
    public void setOnClick(String onClick) {
        wizardBarConfig.setOnClick(onClick);
    }

    @Override
    public String getOnClick() {
        return wizardBarConfig.getOnClick();
    }

    @Override
    public void setWizardMap(Map<String, Wizard> wizardMap) {
        wizardBarConfig.setWizardMap(wizardMap);
    }

    @Override
    public Map<String, Wizard> getWizardMap() {
        return wizardBarConfig.getWizardMap();
    }
}
