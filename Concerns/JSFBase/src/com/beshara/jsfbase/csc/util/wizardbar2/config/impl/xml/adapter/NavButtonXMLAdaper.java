package com.beshara.jsfbase.csc.util.wizardbar2.config.impl.xml.adapter;

import com.beshara.jsfbase.csc.util.wizardbar2.model.NavButton;


public class NavButtonXMLAdaper extends NavButton {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private NavButton navButton;

    public NavButtonXMLAdaper() {
    }

    /**
     * @return the extend
     */
    @Override
    public String getExtend() {
        return navButton.getExtend();
    }

    /**
     * @param extend the extend to set
     */
    @Override
    public void setExtend(String extend) {

        navButton.setExtend(extend);

    }

    /**
     * @return the id
     */
    @Override
    public String getId() {
        return navButton.getId();
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(String id) {
        navButton.setId(id);
    }

    /**
     * @return the label
     */
    @Override
    public String getLabel() {
        return navButton.getLabel();
    }

    /**
     * @param label the label to set
     */
    @Override
    public void setLabel(String label) {
        navButton.setLabel(label);
    }

    /**
     * @return the title
     */
    @Override
    public String getTitle() {
        return navButton.getTitle();
    }

    /**
     * @param title the title to set
     */
    @Override
    public void setTitle(String title) {
        navButton.setTitle(title);
    }

    /**
     * @return the navType
     */
    @Override
    public String getNavType() {
        return navButton.getNavType();
    }

    /**
     * @param navType the navType to set
     */
    @Override
    public void setNavType(String navType) {
        navButton.setNavType(navType);
    }

    /**
     * @return the navigation
     */
    @Override
    public String getNavigation() {
        return navButton.getNavigation();
    }

    /**
     * @param navigation the navigation to set
     */
    @Override
    public void setNavigation(String navigation) {
        navButton.setNavigation(navigation);
    }

    /**
     * @return the rendered
     */
    @Override
    public String getRendered() {
        return navButton.getRendered();
    }

    /**
     * @param rendered the rendered to set
     */
    @Override
    public void setRendered(String rendered) {
        navButton.setRendered(rendered);
    }

    /**
     * @return the enabled
     */
    @Override
    public String getEnabled() {
        return navButton.getEnabled();
    }

    /**
     * @param enabled the enabled to set
     */
    @Override
    public void setEnabled(String enabled) {
        navButton.setEnabled(enabled);
    }

    /**
     * @return the onClick
     */
    @Override
    public String getOnClick() {
        return navButton.getOnClick();
    }

    /**
     * @param onClick the onClick to set
     */
    @Override
    public void setOnClick(String onClick) {
        navButton.setOnClick(onClick);
    }

    /**
     * @return the styleClass
     */
    @Override
    public String getStyleClass() {
        return navButton.getStyleClass();
    }

    /**
     * @param styleClass the styleClass to set
     */
    @Override
    public void setStyleClass(String styleClass) {
        navButton.setStyleClass(styleClass);
    }

    /**
     * @return the disabledStyleClass
     */
    @Override
    public String getDisabledStyleClass() {
        return navButton.getDisabledStyleClass();
    }

    /**
     * @param disabledStyleClass the disabledStyleClass to set
     */
    @Override
    public void setDisabledStyleClass(String disabledStyleClass) {
        navButton.setDisabledStyleClass(disabledStyleClass);
    }
}
