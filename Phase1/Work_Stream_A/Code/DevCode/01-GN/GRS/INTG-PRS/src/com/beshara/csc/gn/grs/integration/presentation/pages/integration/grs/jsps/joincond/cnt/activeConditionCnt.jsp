<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGroup forceId="true" id="grsIntegrationActiveConditionTblPnl"
              styleClass="#{jcHelperBeanName.relatedConditionsCntStyle}">
    <t:dataTable forceId="true" id="grsIntegrationActiveConditionTbl" var="list" value="#{jcHelperBeanName.myTableData}"
                 forceIdIndexFormula="#{list.code.key}" footerClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered"
                 width="100%" align="top" dir="#{shared_util.pageDirection}" styleClass="grid-footer">
        <t:column id="code_column" sortable="false" width="15%">
            <f:facet name="header">
                <h:outputText id="header_code" value="#{grsIntgResources.divInfoConditionCode}"/>
            </f:facet>
            <h:outputText id="content_code" value="#{list.conditionsDTO.code.key}"/>
        </t:column>
        <t:column id="name_column" sortable="false" width="55%">
            <f:facet name="header">
                <h:outputText id="header_name" value="#{grsIntgResources.divInfoConditionName}"/>
            </f:facet>
            <h:outputText id="content_name" value="#{list.conditionsDTO.name}"/>
        </t:column>
        <t:column id="fromDate_column" sortable="false" width="15%">
            <f:facet name="header">
                <h:outputText id="header_fromDate" value="#{grsIntgResources.divInfoFromDate}"/>
            </f:facet>
            <h:outputText id="content_fromDate" value="#{list.fromDate}" converter="SqlDateConverter"/>
        </t:column>
        <t:column id="details_column" sortable="false" width="15%">
            <f:facet name="header">
                <h:outputText id="header_details" value="#{grsIntgResources.conditionDetails}"/>
            </f:facet>
            <a4j:commandButton styleClass="cssButtonSmaller" id="content_details" value="#{globalResources.Available}"
                               actionListener="#{jcHelperBeanName.viewConditionDetails}" reRender="grsIntgHiddenValues"
                               oncomplete="openGrsIntgConditionDetailsWindow();">
                <f:attribute name="conditionCode"
                             value="#{jcHelperBeanName.listSize == 0 ? null : list.conditionsDTO.code.key }"/>
            </a4j:commandButton>
        </t:column>
    </t:dataTable>
</t:panelGroup>
<f:verbatim>
    <br/>
</f:verbatim>