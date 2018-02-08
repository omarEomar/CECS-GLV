package com.beshara.jsfbase.csc.util.wizardbar2.model;

import java.io.Serializable;

import java.util.Map;


public class NavBar implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    //wizard-id.navBar

    private String extend = null;
    private String rendered = null;
    private String styleClass = null;
    private Map<String, NavButton> navBarButtons = null;

    public NavBar() {
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getExtend() {
        return extend;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public String getRendered() {
        return rendered;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setNavBarButtons(Map<String, NavButton> navBarButtons) {
        this.navBarButtons = navBarButtons;
    }

    public Map<String, NavButton> getNavBarButtons() {
        return navBarButtons;
    }
}
