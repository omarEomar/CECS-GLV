package com.beshara.csc.gn.inf.integration.presentation.backingbean.adddocAttachments;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

public class DocAttachmentsIntegrationBean {
    private String selectedimgHidden;


    public DocAttachmentsIntegrationBean() {
        HttpServletRequest req =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //String fullImagePath=req.getParameter("selectedImg")+"&&rf="+req.getParameter("rf")+"&&af="+req.getParameter("rf");
        String fullImagePath = (String)req.getSession().getAttribute("imgPathAttribute");
        selectedimgHidden = fullImagePath;


    }


    public void setSelectedimgHidden(String selectedimgHidden) {
        this.selectedimgHidden = selectedimgHidden;
    }

    public String getSelectedimgHidden() {
        return selectedimgHidden;
    }
}
