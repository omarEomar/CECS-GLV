<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
    <f:loadBundle basename="com.beshara.sec.web.jsf.resources.login" var="loginResources"/>
    <title>
        ${loginResources.notSecuredOperationbarPageTitle}
    </title>
    <h:form id="myForm">
        <t:panelGrid align="center">
            <h:graphicImage value="/app/media/images/CSC_operationb_bar_not_secured_msg.png"
                            style=" position: absolute; right: 250px; vertical-align: middle; margin-top:75px;"/>
            <t:panelGrid align="center"></t:panelGrid>
        </t:panelGrid>
    </h:form>
</f:view>
