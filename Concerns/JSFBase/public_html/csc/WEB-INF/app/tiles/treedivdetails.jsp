<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<tiles:importAttribute scope="request"/>
<t:div styleClass="#{pageBeanName.divTreeDetails}" forceId="true" id="divTreeDetails">
    <t:saveState value="#{pageBeanName.dtoDetails}"/>
    <t:div styleclass="divDetailsHead">
        <t:commandButton id="divTreeDetailsclose" forceId="true"
                         onclick="hideLookUpDiv(window.blocker,window.divTreeDetails,'','','');cancelEditFunction();return false;"
                         styleClass="close"/>
    </t:div>
    <t:div styleclass="popup_body">
        <t:panelGroup id="treeViewAndEditDiv" forceId="true" style="width:400px;display:block">
            <t:panelGrid columns="2" width="100%" id="treeDetailsDiv" forceId="true" columnClasses="col1,col2"
                         cellpadding="2" cellspacing="0" rowClasses="row01,row02">
                <t:outputText value="#{resourcesBundle[code]}" styleClass="lable01"/>
                <t:inputText disabled="true" value="#{pageBeanName.dtoDetails.code.key}" styleClass="textboxsmall"/>
                <t:outputText value="#{resourcesBundle[name]}" styleClass="lable01"/>
                <t:inputText disabled="true" value="#{pageBeanName.dtoDetails.name}" styleClass="textboxmedium2" style="width:300px"/>
                <t:outputText value="#{resourcesBundle[leaf]}" styleClass="lable01"/>
                <t:panelGroup>
                    <t:selectBooleanCheckbox value="#{pageBeanName.dtoDetails.booleanLeafFlag}" disabled="true"
                                             forceId="true" id="LeafID"/>
                    <t:div style="visibility:hidden;height:1px;">
                        <t:inputText style="height:1px;" value="#{pageBeanName.dtoDetails.childernNumbers}" id="hasCHILD"
                                     forceId="true" disabled="true"/>
                    </t:div>
                </t:panelGroup>
               
            </t:panelGrid>
        </t:panelGroup>
    </t:div>
</t:div>
