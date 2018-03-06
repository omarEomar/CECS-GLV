<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<t:saveState value="#{pageBeanName.dto}"/>
<t:panelGrid align="center" id="treeAlertDelete"   forceId="true" border="0">
    <t:panelGroup >
        <t:panelGrid columns="2" rowClasses="standardTable_Header,standardTable_Row1"  columnClasses="deleteTreeCol1,deleteTreeCol2" width="575px" styleClass="grid-footer">
            <t:outputText value="#{globalResources.Code}"/>
            <t:outputText value="#{globalResources.TableName}"/>
            <t:outputText value="#{pageBeanName.dtoDetails.privateCode}"/>
            <t:outputText value="#{pageBeanName.dtoDetails.name}"/>
        </t:panelGrid>
        
    
    </t:panelGroup>
     <t:panelGrid columns="2" align="center">
          <t:commandButton forceId="true" id="OkButtonTreeDelAlertDiv" styleClass="cssButtonSmall" value="#{globalResources.ok}" action="#{pageBeanName.deleteItem}"
                    onblur="if(isVisibleComponent('delAlertTree'))document.getElementById('CancelButtonTreeDelAlertDiv').focus();"/>
           <t:commandButton forceId="true" id="CancelButtonTreeDelAlertDiv" styleClass="cssButtonSmall" value="#{globalResources.cancel}" onclick="hidTreeDiv('delete',window.blocker,window.delAlertTree,null); return false;"
                    onblur="if(isVisibleComponent('delAlertTree'))document.getElementById('OkButtonTreeDelAlertDiv').focus();"/>
    </t:panelGrid>
</t:panelGrid>
