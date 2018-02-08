<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<%-- edit by m.elsaied (fix issue key : NL-279 - 2. Delete 001.JPG)--%>
<t:panelGrid columns="1" width="100%">
    <t:panelGroup forceId="true" id="shortCutId">
        <t:dataTable id="dataT_shortCut" var="list" value="#{pageBeanName.deleteStatusDTOS}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top" rowIndexVar="index" dir="rtl">
            <t:column sortable="false" width="40%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="screenName" value="#{globalResources.ScreenName}"/>
                </f:facet>
                <%--<h:outputText id="shortCut_name" value="#{list.currentObject.name}"/>--%>
            </t:column>
            <t:column sortable="false" width="35%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="shortCutname" value="#{globalResources.ShortCutName}"/>
                </f:facet>
             <%--
                <h:outputText id="popup_status" value="#{globalResources[list.status]}" rendered="#{list.status!='Deleted'}" styleClass="errMsg"/>
                 <h:outputText id="popup_status2" value="#{globalResources[list.status]}" rendered="#{list.status=='Deleted'}" styleClass="sucessMsg"/>
            --%>
            </t:column>
        </t:dataTable>
    </t:panelGroup>
    <t:panelGrid align="center">
        <t:commandButton id="backshortCutButton" forceId="true"  type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.shortcutDiv,null,null);" styleClass="cssButtonSmall"/>

    </t:panelGrid>
</t:panelGrid>
