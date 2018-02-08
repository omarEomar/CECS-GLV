<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%-- edit by m.elsaied (fix issue key : NL-279 - 2. Delete 001.JPG)--%>
<t:panelGrid columns="1" width="100%" border="0" align="center">
    
    <t:panelGroup forceId="true" id="divConfirmApprove"
                  style="width:90%;overflow:auto;direction:rtl;text-align:center; ">
                  <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
        <t:dataTable id="dataT_Delete" var="list"
                     value="#{pageBeanName.selectedDTOS}"
                     preserveDataModel="false" renderedIfEmpty="false"
                     footerClass="grid-footer" sortable="false"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="center" rowIndexVar="index" dir="rtl">
            <t:column width="25%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="code" value="#{globalResources.Code}"/>
                </f:facet>
                <h:outputText id="popup_code" value="#{list.code.key}"/>
            </t:column>
            <t:column width="75%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="name"
                                  value="#{globalResources.TableName}"/>
                </f:facet>
                <h:outputText id="popup_name" value="#{list.typeDTO.name}"/>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <%--t:panelGrid columns="2" align="center">
        <h:commandButton id="ok" value="#{globalResources.ok}"
                         action="#{pageBeanName.saveApprovel}"
                         styleClass="cssButtonSmall"/>
        <h:commandButton id="cancel" type="button"
                         value="#{globalResources.back}"
                         onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null);"
                         styleClass="cssButtonSmall"/>
    </t:panelGrid--%>
    <t:panelGrid columns="2" align="center">
        <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts --%>
        <t:commandButton forceId="true" id="OkButtonDelAlertDiv" onblur="document.getElementById('CancelButtonDelAlertDiv').focus();" styleClass="cssButtonSmall" value="#{globalResources.ok}" action="#{pageBeanName.saveApprovel}"/>
        <t:commandButton forceId="true" id="CancelButtonDelAlertDiv" onblur="document.getElementById('OkButtonDelAlertDiv').focus();" styleClass="cssButtonSmall" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null); return false;"/>
        <%--h:commandButton id="cancel" type="button" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall"/--%>
    </t:panelGrid>
</t:panelGrid>