<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>


<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

<htm:br/>
<t:panelGroup forceId="true" id="decisionCopyPanel">
    <t:outputText styleClass="DecisionCopyMassage" value="#{regResources.decision_copy_confirm_message2}"/>
    <htm:br/><htm:br/>
    <t:panelGrid columns="3" border="0" >
        <t:commandButton  action="#{pageBeanName.copyDecisionWithEmployees}" styleClass="cssButtonMedium" value="#{regResources.decision_copy_confirm_ok}" forceId="true" id="copy_decision_ok_btn" />
        <t:commandButton  action="#{pageBeanName.copyDecisionWithoutEmployees}" styleClass="cssButtonMedium" value="#{regResources.decision_copy_confirm_cancel}" />
        <t:commandButton forceId="true" id="backButtonCustomDiv1" type="button" styleClass="cssButtonSmall" value="#{globalResources.back}"  onclick="hideLookUpDiv(window.blocker,window.customDiv1,'','','');" onblur="document.getElementById('copy_decision_ok_btn').focus();"/>
    </t:panelGrid>
</t:panelGroup>
<htm:br/>