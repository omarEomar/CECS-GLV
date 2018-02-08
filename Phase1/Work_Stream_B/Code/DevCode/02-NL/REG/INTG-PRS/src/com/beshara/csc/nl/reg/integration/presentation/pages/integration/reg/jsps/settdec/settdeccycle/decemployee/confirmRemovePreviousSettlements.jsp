<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="intgResources"/>
<%-- edit by m.elsaied (fix issue key : NL-279 - 2. Delete 001.JPG)--%>
<t:panelGrid columns="1" width="100%" align="center">
     <t:panelGroup forceId="true" id="divcustomDiv1"  style="width:600px;" >
           <t:panelGrid columns="2" align="center">
              <t:outputText value="#{intgResources.removePreviousSettlements_Confirm}" styleClass="msg" />
           </t:panelGrid>
    </t:panelGroup>
    <t:panelGrid columns="2" align="center">
        
        <t:commandButton forceId="true" id="OkButtondivcustomDiv1" onblur="if(isVisibleComponent('customDiv1'))document.getElementById('CancelButtondivcustomDiv1').focus();" styleClass="cssButtonSmall" value="#{globalResources.ok}" action="#{detailBeanName.removeAllPreviousSettlements}"/>
        <t:commandButton forceId="true" id="backButtonCustomDiv1" type="button" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.customDiv1,'','','');" styleClass="cssButtonSmall"/>
        
    </t:panelGrid>
</t:panelGrid>