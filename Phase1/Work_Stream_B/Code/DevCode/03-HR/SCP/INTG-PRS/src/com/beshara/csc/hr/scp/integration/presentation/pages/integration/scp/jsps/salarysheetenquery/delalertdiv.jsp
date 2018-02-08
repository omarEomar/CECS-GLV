<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%><%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="resourcesBundle"/>
<t:panelGrid columns="1" width="100%">
     <t:panelGroup forceId="true" id="divDelete" styleClass="" style="display:block;width: 100%;height: 100px !important;">
        <%--<t:dataTable id="dataT_Delete" var="list" value="#{pageBeanName.selectedDTOS}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"

                styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"

             
                columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top" rowIndexVar="index" dir="rtl">
            <t:column width="25%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="code" value="#{globalResources.Code}"/>
                </f:facet>
                --%><%--<h:outputText id="popup_code" value="#{list.code.key}"/>--%><%--
                <h:outputText id="lck1" value="#{list.code.keys[1]}" rendered="#{list.code.keys[1]!=null}"/>
                <h:outputText id="lck2" value="#{list.code.key}" rendered="#{list.code.keys[1]==null}"/>
            </t:column>
            <t:column width="75%" defaultSorted="true">
                <f:facet name="header">
                    <h:outputText id="name" value="#{globalResources.TableName}"/>
                </f:facet>
                <h:outputText id="popup_name" value="#{list.name}"/>
            </t:column>
        </t:dataTable>--%>
        <h:outputText styleClass="msg warning" value="#{pageBeanName.finalMessage}"/>
    </t:panelGroup>
    
    <t:panelGrid columns="2" align="center">
        <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts --%>
        <t:commandButton forceId="true" id="OkButtonDelAlert"  styleClass="cssButtonSmall" value="#{globalResources.ok}" action="#{pageBeanName.cancelPayOrder}"/>
        <t:commandButton forceId="true" id="backButtonIntegrationDiv1"  styleClass="cssButtonSmall" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.integrationDiv1,null,null); return false;"/>
        <%--<t:commandButton forceId="true" id="backButtonCustomDiv3"  styleClass="cssButtonSmall" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.customDiv3,null,null);changeVisibilityDiv(window.blocker,window.customDiv1); return false;"/>--%>
        <%--h:commandButton id="cancel" type="button" value="#{globalResources.cancel}" onclick="if(isVisibleComponent('delAlert'))hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall"/--%>
    </t:panelGrid>
</t:panelGrid>
