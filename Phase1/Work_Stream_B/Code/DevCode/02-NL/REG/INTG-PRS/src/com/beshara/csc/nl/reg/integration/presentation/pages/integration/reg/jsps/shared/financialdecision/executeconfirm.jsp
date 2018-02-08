<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
 <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
<%-- edit by m.elsaied (fix issue key : NL-279 - 2. Delete 001.JPG)--%>
<t:panelGrid columns="1" width="100%" align="center">
     <t:panelGroup forceId="true" id="divcustomDiv1"  style="width:600px;" >
           <t:panelGrid columns="2" align="center">
              <t:outputText value="#{regResources.executeDecision_confirm }" styleClass="msg" />
           </t:panelGrid>
    </t:panelGroup>
    <t:panelGrid columns="2" align="center">
        <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts --%>
        <t:commandButton forceId="true" id="OkButtondivcustomDiv1" onblur="if(isVisibleComponent('customDiv1'))document.getElementById('CancelButtondivcustomDiv1').focus();" styleClass="cssButtonSmall" value="#{globalResources.ok}" action="#{pageBeanName.executeDecision}" />
        <%--<t:commandButton forceId="true" id="CancelButtondivcustomDiv1" onblur="if(isVisibleComponent('customDiv1'))document.getElementById('OkButtondivcustomDiv1').focus();" styleClass="cssButtonSmall" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null);block();" action="#{detailBeanName.cancelSearchMethod}"/>--%>
        <%--<h:commandButton id="cancel" type="button" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null);" styleClass="cssButtonSmall"/>--%>
        <t:commandButton forceId="true" id="backButtonCustomDiv1" type="button" styleClass="cssButtonSmall" value="#{globalResources.back}"  onclick="hideLookUpDiv(window.blocker,window.customDiv1,'','','');"/>
    </t:panelGrid>
</t:panelGrid>