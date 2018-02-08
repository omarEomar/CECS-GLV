<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view locale="ar">
    <f:loadBundle basename="com.beshara.sec.web.jsf.resources.login" var="loginResources"/>
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="../app/media/css/login.css"/>
            <meta http-equiv="CACHE-CONTROL" content="NO-CACHE"/>
            <meta http-equiv="EXPIRES" content="0"/>
            <title>صفحة الخروج</title>
        </head>
        <body>
            <jsp:useBean id="logoutBean" class="com.beshara.sec.web.jsf.bean.LogoutBean" scope="request"/>
            <div class="main_container">
                <div class="login_area">
                    <div class="login_full_logo">&nbsp;</div>
                    <div class="login_box" style="margin-top:60px;">
                        <div class="login_box_header_blank">&nbsp;</div>
                        <div class="login_box_body">
                            <div class="Message2">تم الخروج بنجاح</div>
                            <img src="${logoutBean.contextPath}/app/media/images/login/message/sucses-msg.png" width="85"
                                 height="85" align="left" border="0" style="margin-top:30px;margin-left:20px;"/>
                             
                            <h:form id="newForm">
                                <div style="float:right;width:92px;margin-right:163px;margin-top: -15px;">
                                    <h:commandButton value="" action="#{LogoutBean.goToIndex}"
                                                     styleClass="login_homepage_btn" id="logoutButton"/>
                                </div>
                            </h:form>
                        </div>
                    </div>
                    <div class="login_copyrights">
                        ${loginResources.copyright}
                    </div>
                </div>
            </div>
        </body>
    </html>
</f:view>