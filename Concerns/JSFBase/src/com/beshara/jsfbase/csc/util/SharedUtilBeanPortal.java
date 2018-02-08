package com.beshara.jsfbase.csc.util;

import java.io.Serializable;

public class SharedUtilBeanPortal implements Serializable {
    @SuppressWarnings("compatibility:-3808997138620826979")
    private static final long serialVersionUID = 1L;
    
    private boolean enabledPortal = false;

    public SharedUtilBeanPortal() {
    }

    public void setEnabledPortal(boolean enabledPortal) {
        this.enabledPortal = enabledPortal;
    }

    public boolean isEnabledPortal() {
        return enabledPortal;
    }
}
