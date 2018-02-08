<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>



<t:panelGrid columns="1" width="100%">
    <t:panelGroup forceId="true" id="vacHistoryPanel" styleClass="delDivScroll">
        <t:dataTable id="dataT_TablePanel" var="list" value="#{pageBeanName.vacHistoryList}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                 width="100%" align="top" rowIndexVar="index" dir="rtl">
                
            <t:column sortable="false" width="10%" > 
                <f:facet name="header">
                    <h:outputText styleClass="headerLink" value="#{scpIntgResources.fromDate}"/>
                </f:facet>
                <h:outputText  id="fromDate" value="#{list.fromDate}" converter="SqlDateConverter"/>
            </t:column>
            <t:column sortable="false" width="10%">
                <f:facet name="header">
                    <h:outputText styleClass="headerLink" value="#{scpIntgResources.untilDate}"/>
                </f:facet>
                <h:outputText id="untilDate" value="#{list.untilDate}"  converter="SqlDateConverter"/>
            </t:column>
            
             <t:column sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText  styleClass="headerLink" value="#{scpIntgResources.operation_type}"/>
                </f:facet>
                <h:outputText id="vactrxType" value="#{list.name}" />
            </t:column>
            
             <t:column sortable="false" width="10%" >
                <f:facet name="header">
                    <h:outputText styleClass="headerLink" value="#{scpIntgResources.operation_time}"/>
                </f:facet>
                <h:outputText id="vactrxType" value="#{list.trxDatetime}" converter="TimeStampConverter" />
             </t:column>
            
             <t:column sortable="false" width="10%" >
                <f:facet name="header">
                    <h:outputText styleClass="headerLink" value="#{scpIntgResources.operation_user_name}"/>
                </f:facet>
                <h:outputText id="vactrxType" value="#{list.engName}"  />
             </t:column>
            
        </t:dataTable>
    </t:panelGroup>
    <t:panelGrid align="center">
      
        <t:commandButton forceId="true" id="backButtonCustomDiv3"  styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.customDiv3);return false;"/>
     
    </t:panelGrid>
</t:panelGrid>
