package com.beshara.jsfbase.csc.util.wizardbar2.config.impl.xml.adapter;

import com.beshara.jsfbase.csc.util.wizardbar2.model.NavBar;
import com.beshara.jsfbase.csc.util.wizardbar2.model.NavButton;

import java.util.Map;


public class NavBarXMLAdaper extends NavBar {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private NavBar navBar;

    public NavBarXMLAdaper() {
    }

    public NavBarXMLAdaper(NavBar navBar) {
        this.navBar = navBar;
    }

    /**
     * @return navBar.get the extend
     */
    @Override
    public String getExtend() {
        return navBar.getExtend();
    }

    /**
     * @param extend the extend to set
     */
    @Override
    public void setExtend(String extend) {
        navBar.setExtend(extend);
    }

    /**
     * @return navBar.get the rendered
     */
    @Override
    public String getRendered() {
        return navBar.getRendered();
    }

    /**
     * @param rendered the rendered to set
     */
    @Override
    public void setRendered(String rendered) {
        navBar.setRendered(rendered);
    }

    /**
     * @return navBar.get the styleClass
     */
    @Override
    public String getStyleClass() {
        return navBar.getStyleClass();
    }

    /**
     * @param styleClass the styleClass to set
     */
    @Override
    public void setStyleClass(String styleClass) {
        navBar.setStyleClass(styleClass);
    }

    /**
     * @return navBar.get the navBarButtons
     */
    @Override
    public Map<String, NavButton> getNavBarButtons() {
        return navBar.getNavBarButtons();
    }

    /**
     * @param navBarButtons the navBarButtons to set
     */
    @Override
    public void setNavBarButtons(Map<String, NavButton> navBarButtons) {
        navBar.setNavBarButtons(navBarButtons);
    }
}
