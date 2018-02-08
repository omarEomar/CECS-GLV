package com.beshara.csc.nl.reg.integration.presentation.backingbean.util;

public class RegConfig {
    private static final String BEAN_NAME = "RegConfig";

    private String regulationUploadedImagesPath; //"uploadedImages/regulation/";
    private String decisionUploadedImagesPath; //"uploadedImages/decision/";

    public RegConfig() {
    }

    public static RegConfig getInstance() {
        return (RegConfig)BeansUtil.getValue(BEAN_NAME);
    }

    public void setRegulationUploadedImagesPath(String regulationUploadedImagesPath) {
        this.regulationUploadedImagesPath = regulationUploadedImagesPath;
    }

    public String getRegulationUploadedImagesPath() {
        return regulationUploadedImagesPath;
    }

    public void setDecisionUploadedImagesPath(String decisionUploadedImagesPath) {
        this.decisionUploadedImagesPath = decisionUploadedImagesPath;
    }

    public String getDecisionUploadedImagesPath() {
        return decisionUploadedImagesPath;
    }
}
