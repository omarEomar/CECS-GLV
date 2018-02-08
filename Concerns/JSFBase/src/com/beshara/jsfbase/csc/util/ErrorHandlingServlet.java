package com.beshara.jsfbase.csc.util;

import java.io.IOException;

import javax.faces.webapp.FacesServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ErrorHandlingServlet extends HttpServlet {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private static final String INIT_PARAM_EXCEPTION_PAGE = "errorPage";

    private FacesServlet delegate;

    private String exceptionPage;

    public void init(ServletConfig servletConfig) throws ServletException {
        delegate = new FacesServlet();
        delegate.init(servletConfig);
        exceptionPage = 
                servletConfig.getInitParameter(INIT_PARAM_EXCEPTION_PAGE);
    }

    public void destroy() {
        delegate.destroy();
    }

    public ServletConfig getServletConfig() {
        return delegate.getServletConfig();
    }

    public String getServletInfo() {
        return delegate.getServletInfo();
    }

    public void service(ServletRequest request, 
                        ServletResponse response) throws ServletException, 
                                                         IOException {

        String incomingPage = 
            ((HttpServletRequest)request).getHeader("referer");
        Integer errorCode = 
            (Integer)request.getAttribute("javax.servlet.error.status_code");
        String uri = 
            (String)request.getAttribute("javax.servlet.error.request_uri");
        String msg = 
            (String)request.getAttribute("javax.servlet.error.message");

        HttpSession session = ((HttpServletRequest)request).getSession();
        try {
            if ((incomingPage == null || 
                 incomingPage.indexOf("errorpage.jsf") == -1) && 
                (uri != null && uri.indexOf("errorpage.jsf") == -1)) {

                if (errorCode != null) {
                    ErrorDisplay error = 
                        (ErrorDisplay)session.getAttribute("error_dissplay");

                    if (error == null) {
                        error = new ErrorDisplay();
                    }

                    if (error.isSystemException()) {
                        error.setSystemException(false);
                    } else {
                        error.setErrorMsgKey(errorCode + "");
                    }
                    error.setExceptionMsg(msg);
                    session.setAttribute("error_dissplay", error);

                    ((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + 
                                                                 exceptionPage);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + 
                                                         exceptionPage);
        }
    }
}
