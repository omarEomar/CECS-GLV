package com.beshara.jsfbase.csc.util.tableheader;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;


public class ConstantMethodBinding extends MethodBinding {
    private String action;
    
    public ConstantMethodBinding(String action) {
        this.action = action;
    }

    public Class getType(FacesContext facesContext) {
        return String.class;
    }

    public Object invoke(FacesContext facesContext, Object[] objects) {
        return action;
    }
}
