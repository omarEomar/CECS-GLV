package com.beshara.jsfbase.csc.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ApplicationFilter implements Filter {
    public ApplicationFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, 
                         FilterChain chain) throws IOException, 
                                                   ServletException {

        /* read client browser type only once */
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();
        if (session.getAttribute("shared_util") == null) {
            String browserType = getBrowserType((HttpServletRequest)request);
            SharedUtilBean sharedBean = new SharedUtilBean();
            sharedBean.setBrowserType(browserType);
            session.setAttribute("shared_util", sharedBean);
        }

        /* read cookies if exist */

        /*Set the Encoding of the response to UTF-8*/
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }


    private String getBrowserType(HttpServletRequest req) {
        String browserType = "NoType";
        browserType = req.getHeader("USER-AGENT");

        if (browserType != null) {
            browserType = browserType.toLowerCase();
            if ((browserType.indexOf("msie") != -1)) {
                browserType = "Explorer";
            } else if ((browserType.indexOf("mozilla") != -1) && 
                       (browserType.indexOf("spoofer") == -1) && 
                       (browserType.indexOf("compatible") == -1)) {
                if (browserType.indexOf("firefox") != -1) {
                    browserType = "Firefox";
                } else if (browserType.indexOf("netscape") != -1) {
                    browserType = "Netscape";
                } else {
                    browserType = "Mozilla";
                }
            } else if (browserType.indexOf("opera") != -1) {
                browserType = "Opera";
            } else if (browserType.indexOf("safari") != -1) {
                browserType = "Safari";
            } else if (browserType.indexOf("konqueror") != -1) {
                browserType = "Konqueror";
            }
        }

        return browserType;
    }
}

