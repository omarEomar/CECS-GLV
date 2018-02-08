<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<%--<t:panelGrid cellpadding="0" cellspacing="0" align="right" border="1" width="100%">
        <t:inputText value="#{globalResources.SuccessAdds}" styleClass="msg" readonly="true"/>
        <t:commandButton type="button" value="#{globalResources.back}" styleClass="cssButtonSmall" onclick="window.close()"></t:commandButton>
</t:panelGrid>--%>

<f:loadBundle basename="com.beshara.csc.gn.req.presentation.resources.req" var="resourcesBundle"/>
<htm:table width="100%" border="0" cellpadding="0" cellspacing="0">
    <htm:tr>
        <htm:td align="center"><t:outputText  value="#{resourcesBundle.complete_data_confirm_msg}" styleClass="msg" style="width:400px"/></htm:td>
    </htm:tr>
    <htm:tr>
        <htm:td align="center">
            <t:commandButton id="complete_button" value="#{resourcesBundle.request_completed}" styleClass="cssButtonMedium" action="#{pageBeanName.saveReqCompleted}"  /><t:outputText value=" "/>
            <t:commandButton id="not_complete_button" value="#{resourcesBundle.request_process}" styleClass="cssButtonMedium" action="#{pageBeanName.saveReq}"  />
            <t:outputText value=" "/>
            <t:commandButton   styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.lookupAddDiv,'null','null');return false;" />
         </htm:td>
    </htm:tr>
</htm:table>
