package com.beshara.jsfbase.csc.util.popup;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;

import javax.servlet.http.HttpServletRequest;

import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;


public class DeleteConfPopupBean implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private List itemsList = new ArrayList();
    private String loadMethod;

    public DeleteConfPopupBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ex = ctx.getExternalContext();
        HttpServletRequest req = (HttpServletRequest)ex.getRequest();

        loadMethod = req.getParameter("load_method");
        if (loadMethod != null && !loadMethod.equals("")) {
            load();
        }
    }

    private void load() {
        MethodBinding methodBinding = 
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                   loadMethod + 
                                                                                   "}", 
                                                                                   null);
        itemsList = 
                (List)methodBinding.invoke(FacesContext.getCurrentInstance(), 
                                           null);
    }

    public void closeWindow() {
        String javaScriptText = 
            "window.opener.blocker.style.visibility='hidden';window.close();";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        AddResource addResource = AddResourceFactory.getInstance(facesContext);
        addResource.addInlineScriptAtPosition(facesContext, 
                                              AddResource.HEADER_BEGIN, 
                                              javaScriptText);
    }


    public void setItemsList(List itemsList) {
        this.itemsList = itemsList;
    }

    public List getItemsList() {
        return itemsList;
    }

    public void setLoadMethod(String loadMethod) {
        this.loadMethod = loadMethod;
    }

    public String getLoadMethod() {
        return loadMethod;
    }
}
