package com.beshara.jsfbase.csc.util;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


public class ErrorDisplay implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String errorMsgKey;
    private boolean systemException;
    private String prePage;
    private String exceptionMsg;

    public ErrorDisplay() {
    }

    public void setErrorMsgKey(String errorMsgKey) {
        this.errorMsgKey = errorMsgKey;
    }

    public String getErrorMsgKey() {
        return errorMsgKey;
    }

    public void setSystemException(boolean systemException) {
        this.systemException = systemException;
    }

    public boolean isSystemException() {
        return systemException;
    }

    public void setPrePage(String prePage) {
        this.prePage = prePage;
    }

    public String getPrePage() {
        return prePage;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void back() {

        try {
            prePage = 
                    ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath() + 
                    "/home.jsf";
            FacesContext.getCurrentInstance().getExternalContext().redirect(prePage);

        } catch (Exception e) {
            e.printStackTrace();
            setPrePage(null);
        }

    }
}
