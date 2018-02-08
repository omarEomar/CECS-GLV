package com.beshara.jsfbase.csc.util;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Bean just a temp bean to invalidate session when user close the browser
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Amir Nasr    16-SEP-2007     Created
 *    <br>
 *    
 * @author       Beshara Group   
 * @version      1.0   
 * @since        16/09/2007   
 */
public class

SessionClearBean {
    private String title = "";

    public SessionClearBean() {
        FacesContext faces = FacesContext.getCurrentInstance();
        HttpSession session = 
            (HttpSession)faces.getExternalContext().getSession(true);
        session.invalidate();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
