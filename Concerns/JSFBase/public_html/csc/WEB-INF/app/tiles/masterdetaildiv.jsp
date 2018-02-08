<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGrid columns="1"  align="center">
</t:panelGrid>
<t:panelGroup forceId="true" id="DivMasterDetail" styleClass="delDivScroll" style="width:600px;height: 200px;">
    <t:dataTable id="dataT_MasterDetail" var="list"
            value="#{pageBeanName.masterDetailList}" preserveDataModel="false"
            renderedIfEmpty="false" 
            footerClass="grid-footer" sortable="false"
            styleClass="grid-footer"
            headerClass="standardTable_Header"
            rowClasses="standardTable_Row1,standardTable_Row2"
            columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
            rowIndexVar="index"
            ><%-- width has been modified by css --%>
        <t:column  width="25%" >
            <f:facet name="header">
                <h:outputText id="code" value="#{globalResources.Code}"/>
            </f:facet>
            <h:outputText id="popup_code" value="#{list.code.key}"/>
        </t:column>
        <t:column  width="75%">
            <f:facet name="header">
                <h:outputText id="name" value="#{globalResources.TableName}"/>
            </f:facet>
            <h:outputText id="popup_name" value="#{list.name}"/>
        </t:column>
    </t:dataTable>
    <t:panelGrid columns="1" rendered="#{empty pageBeanName.masterDetailList}" align="center">
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </t:panelGrid>
</t:panelGroup>

<t:panelGrid align="center">
 <t:inputText style="width:0px; height:0px;  font-size: 0px;" forceId="true" id="focusComponent"/>
    <t:commandButton forceId="true" id="BackButtonMasterDetailDiv" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.masterDetailDiv,null,null); return false;" onblur="setFocusOnlyOnElement('BackButtonMasterDetailDiv');"/>
    <%--h:commandButton id="cancell" type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.masterDetailDiv,null,null);" styleClass="cssButtonSmall"/--%>
</t:panelGrid>