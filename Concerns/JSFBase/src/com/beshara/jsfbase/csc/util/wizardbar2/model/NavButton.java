package com.beshara.jsfbase.csc.util.wizardbar2.model;

import java.io.Serializable;


public class NavButton implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    //button-id
    private String extend = null;
    private String id = null;
    private String label = null;
    private String title = null;
    private String navType = null; // allowed values step|custom
    private String navigation = 
        null; //possible values next|prev|finish|cancel|step-id|custom
    private String rendered = null;
    private String enabled = null;
    private String onClick = null;
    private String styleClass = null;
    private String disabledStyleClass = null;
    private String disabled = null;
    private String onblur = null;
    private String onmousedown = null;

    public NavButton() {
    }

    /**
     * @return the extend
     */
    public String getExtend() {
        return extend;
    }

    /**
     * @param extend the extend to set
     */
    public void setExtend(String extend) {
        this.extend = extend;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the navType
     */
    public String getNavType() {
        return navType;
    }

    /**
     * @param navType the navType to set
     */
    public void setNavType(String navType) {
        this.navType = navType;
    }

    /**
     * @return the navigation
     */
    public String getNavigation() {
        return navigation;
    }

    /**
     * @param navigation the navigation to set
     */
    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    /**
     * @return the rendered
     */
    public String getRendered() {
        return rendered;
    }

    /**
     * @param rendered the rendered to set
     */
    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    /**
     * @return the enabled
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the onClick
     */
    public String getOnClick() {
        return onClick;
    }

    /**
     * @param onClick the onClick to set
     */
    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    /**
     * @return the styleClass
     */
    public String getStyleClass() {
        return styleClass;
    }

    /**
     * @param styleClass the styleClass to set
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return the disabledStyleClass
     */
    public String getDisabledStyleClass() {
        return disabledStyleClass;
    }

    /**
     * @param disabledStyleClass the disabledStyleClass to set
     */
    public void setDisabledStyleClass(String disabledStyleClass) {
        this.disabledStyleClass = disabledStyleClass;
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
