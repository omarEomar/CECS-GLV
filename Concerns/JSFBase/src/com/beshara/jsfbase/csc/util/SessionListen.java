package com.beshara.jsfbase.csc.util;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionListen implements HttpSessionListener {

    public SessionListen() {
    }

    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        /*Set the max inactive interval by 30 mins*/
        session.setMaxInactiveInterval(60 * 30);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();

        /*remove user from online users list*/
        //Login loginUser = (Login) session.getAttribute("login");
        ApplicationUtilBean appBean = 
            (ApplicationUtilBean)session.getServletContext().getAttribute("application_util_bean");
        //appBean.removeUser(loginUser.getUserName);

        /*Free session resources (attributes)*/
        Enumeration attribues = session.getAttributeNames();
        while (attribues.hasMoreElements()) {
            String attributeName = (String)attribues.nextElement();
            session.setAttribute(attributeName, null);
        }
        session = null;
    }
}

