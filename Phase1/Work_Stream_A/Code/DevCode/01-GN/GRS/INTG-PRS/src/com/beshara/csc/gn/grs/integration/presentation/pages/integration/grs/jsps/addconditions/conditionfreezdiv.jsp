<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGrid columns="1" width="100%">
    <t:panelGroup forceId="true" id="divFreezAlert" >
        <h:outputText id="msgFreez" value="#{grsResources.freezWarningMsg}" rendered="#{pageBeanName.activeoperationsCount <= 0 }" styleClass="msg warning" />              
        <h:outputText id="msgPending" value="#{grsResources.bindWarningMsg}" rendered="#{pageBeanName.activeoperationsCount > 0 }" styleClass="msg warning"/>              
    </t:panelGroup>
    <t:panelGrid columns="2" align="center">
        <h:commandButton id="ok" value="#{globalResources.ok}" action="#{pageBeanName.freez}" styleClass="cssButtonSmall"/>
        <h:commandButton id="cancel" type="button" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.customDiv2,null,null);" styleClass="cssButtonSmall"/>
    </t:panelGrid>
</t:panelGrid>
