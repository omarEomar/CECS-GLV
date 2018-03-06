<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>#treeDetailsDiv {width:470px}</htm:style>

<tiles:importAttribute scope="request"/>
<t:div styleClass="#{pageBeanName.divTreeDetails}" style="z-index:100;" forceId="true" id="divTreeDetails">      
    <t:saveState value="#{pageBeanName.dtoDetails}"/>    
    <t:div styleClass="divDetailsHead">
    <input type="image" src="${shared_util.contextPath}/app/media/images/icon_close.png" onclick="document.getElementById('cancelButton').click();" tabindex="1" />
    </t:div>
    <t:panelGroup colspan= "4" id="treeViewAndEditDiv" forceId="true" styleClass="popup_body">
        
        <t:panelGrid columns="2" width="100%" id="treeDetailsDiv" forceId="true" columnClasses="col1,col2"
                         cellpadding="3" cellspacing="0" rowClasses="row01,row02" style="text-align: right;font-weight: normal !important;">
                <t:outputText value="#{resourcesBundle[code]}" styleClass="lable01"/>
                <t:inputText disabled="true" value="#{pageBeanName.dtoDetails.code.key}" styleClass="textboxsmall"/>
                <t:outputText value="#{resourcesBundle[name]}" styleClass="lable01"/>
                <t:inputText disabled="true" value="#{pageBeanName.dtoDetails.name}" styleClass="textboxmedium"/>
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
    
    <%--buttons--%>
    <t:panelGrid columns="3" border="0" align="center" id="buttonsEditDiv" forceId="true">
    <%--save button--%>
    <t:commandButton
        rendered="#{pageBeanName.enableEdit}" 
        type="button"
        forceId="true" 
        id="SaveButton"
        styleClass="cssButtonSmall" 
        value="#{qulResources.saveButton}"  
        onclick="if(validatetreeform()){;refreshFunction();editFunction();hideBlocker();}">
        
        </t:commandButton>
    </t:panelGrid>
</t:panelGroup>
</t:div>
