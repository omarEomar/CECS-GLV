package com.beshara.jsfbase.csc.util.wizardbar;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WizardStep implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private int status; //visited ,nonvisited,edited
    private int navigateStatus; // user allowed to navigate to this step or not
    private int ControlType; // the jsf control used to render this step (button or any thind else)
    private String action; // action to be executed when click this step
    private String title; //the title for this step
    private String mapKey;
    private String navigationCase;
    int order;
    boolean mandatory = false;
    boolean visited = false;
    boolean validated = false;
    String stepBeanName;
    private List<String> relevantSteps = 
        new ArrayList<String>(); //list of steps this step depend on 
    private Map dependancyMap = 
        new HashMap(); //this hold map of the steps which this step depend on and current status
    private String jsValidation;

    public WizardStep() {

    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setNavigateStatus(int navigateStatus) {
        this.navigateStatus = navigateStatus;
    }

    public int getNavigateStatus() {
        return navigateStatus;
    }

    public void setControlType(int controlType) {
        this.ControlType = controlType;
    }

    public int getControlType() {
        return ControlType;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }

    public String getMapKey() {
        return mapKey;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }

    public String getNavigationCase() {
        return navigationCase;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setStepBeanName(String stepBeanName) {
        this.stepBeanName = stepBeanName;
    }

    public String getStepBeanName() {
        return stepBeanName;
    }


    public void setRelevantSteps(List<String> relevantSteps) {
        this.relevantSteps = relevantSteps;
    }

    public List<String> getRelevantSteps() {
        return relevantSteps;
    }

    public void setDependancyMap(Map dependancyMap) {
        this.dependancyMap = dependancyMap;
    }

    public Map getDependancyMap() {
        return dependancyMap;
    }

    public void setJsValidation(String jsValidation) {
        this.jsValidation = jsValidation;
    }

    public String getJsValidation() {
        return jsValidation;
    }
}
