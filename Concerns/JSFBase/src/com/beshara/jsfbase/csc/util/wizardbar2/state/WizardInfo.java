package com.beshara.jsfbase.csc.util.wizardbar2.state;

public class WizardInfo {

    private String currentStep;
    private String targetStep;
    private String defaultButtonId;
    private String defaultActionName;

    public WizardInfo() {

    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    public String getCurrentStep() {
        return currentStep;
    }

    public void setTargetStep(String targetStep) {
        this.targetStep = targetStep;
    }

    public String getTargetStep() {
        return targetStep;
    }

    public void setDefaultButtonId(String defaultButtonId) {
        this.defaultButtonId = defaultButtonId;
    }

    public String getDefaultButtonId() {
        return defaultButtonId;
    }

    public void setDefaultActionName(String defaultActionName) {
        this.defaultActionName = defaultActionName;
    }

    public String getDefaultActionName() {
        return defaultActionName;
    }
}
