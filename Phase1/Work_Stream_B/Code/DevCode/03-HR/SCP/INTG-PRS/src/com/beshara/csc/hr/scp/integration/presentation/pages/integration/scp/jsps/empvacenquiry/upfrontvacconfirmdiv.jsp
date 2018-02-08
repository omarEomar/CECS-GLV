<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

<t:panelGrid columns="1" width="100%">
<t:outputText styleClass="msg warning" value="#{pageBeanName.upFrontVacConfrmMsg}"/>
    <t:panelGrid columns="3" align="center">
        <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts--%>
        <t:commandButton forceId="true" id="OkButtonUpFronttDiv" styleClass="cssButtonSmall"
                         value="#{globalResources.ok}" action="#{pageBeanName.executeUpFrontSettlment}"/>
        <t:commandButton forceId="true" id="CancelButtonUpFronttDiv" styleClass="cssButtonSmall"
                         value="#{globalResources.cancel}" action="#{pageBeanName.executeSettlment}"/>
        <t:commandButton forceId="true" id="backButtonCustomDiv2" styleClass="cssButtonSmall"
                         value="#{globalResources.back}"
                         onclick="hideLookUpDiv(window.blocker,window.customDiv2,null,null); return false;"/>
    </t:panelGrid>
</t:panelGrid>

<t:saveState value="#{pageBeanName.upFrontVacConfrmMsg}"/>