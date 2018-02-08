<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<%--f:loadBundle basename="com.beshara.csc.hr.mer.presentation.resources.mer_#{shared_util.locale}" var="resourcesBundle"/--%>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="#{resourcesBundlePath}" var="resourcesBundle"/>
<htm:link rel="stylesheet" type="text/css" href="${shared_util.contextPath}/module/media/css/${globalResources.photoFolder}/Style.css" />
<htm:link rel="stylesheet" type="text/css" href="${shared_util.contextPath}/app/media/css/${globalResources.photoFolder}/Style.css" />
<htm:link rel="stylesheet" type="text/css" href="css/${shared_util.locale}/Style.css"/>
<script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/Uitl.js"></script>
<h:form >
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td style="padding:0px;">
                <htm:div styleClass="shadow"></htm:div>
            </htm:td>
        </htm:tr>
        <htm:tr>
            <htm:td>
                <htm:div styleClass="BodyContent" onclick="hideNavigationMenu();">
                    <htm:div styleClass="AboutSystem"></htm:div>
                    <htm:span styleClass="pannel_large_title"><h:outputText escape="false" value="#{resourcesBundle.module_div_title}"/></htm:span>
                    <htm:span styleClass="homepage_paragraph"><h:outputText escape="false" value="#{resourcesBundle.HomePageSubject}"/></htm:span>
                    <htm:div styleClass="QulImg"></htm:div>
                </htm:div>
            </htm:td>
        </htm:tr>
    </htm:table>
       
</h:form>

