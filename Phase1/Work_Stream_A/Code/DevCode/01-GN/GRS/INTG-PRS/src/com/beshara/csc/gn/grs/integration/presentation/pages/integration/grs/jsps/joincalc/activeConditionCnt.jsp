<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGroup forceId="true" id="grsJoinCalcActiveConditionTblPnl"
              styleClass="fullWidthScroll200">
    <t:dataTable forceId="true" id="grsJoinCalcActiveConditionTbl" var="list" value="#{joinCalcHelperBeanName.myTableData}"
                 forceIdIndexFormula="#{list.code.key}" footerClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered"
                 width="100%" align="top" dir="#{shared_util.pageDirection}" styleClass="grid-footer">
        <t:column id="code_column" sortable="false" width="15%">
            <f:facet name="header">
                <h:outputText id="header_code" value="#{grsIntgResources.divInfoCalcCode}"/>
            </f:facet>
            <h:outputText id="joincalc_code" value="#{list.calculationsDTO.code.key}"/>
        </t:column>
        <t:column id="name_column" sortable="false" width="55%">
            <f:facet name="header">
                <h:outputText id="header_name" value="#{grsIntgResources.divInfoCalcName}"/>
            </f:facet>
            <h:outputText id="joincalc_name" value="#{list.calculationsDTO.name}"/>
        </t:column>
        <t:column id="fromDate_column" sortable="false" width="15%">
            <f:facet name="header">
                <h:outputText id="header_fromDate" value="#{grsIntgResources.divInfoFromDate}"/>
            </f:facet>
            <h:outputText id="joincalc_fromDate" value="#{list.fromDate}" converter="SqlDateConverter"/>
        </t:column>
         <t:column id="value_column" sortable="false" width="15%">
            <f:facet name="header">
                <h:outputText id="header_value" value="#{grsIntgResources.value}"/>
            </f:facet>
            <h:outputText id="joincalc_value" value="#{joinCalcHelperBeanName.viewedValue}" />
        </t:column>
        
        <t:column id="details_column" sortable="false" width="15%">
            <f:facet name="header">
                <h:outputText id="header_details" value="#{grsIntgResources.calc_details}"/>
            </f:facet>
            <a4j:commandButton styleClass="cssButtonSmaller" id="joincalc_details" value="#{globalResources.Available}" disabled=="#{joinCalcHelperBeanName.viewedValue !=null}" 
                               actionListener="#{joinCalcHelperBeanName.viewCalculationDetails}" reRender="grsIntgerationHiddenValues"
                               oncomplete="openGrsIntgCalculationDetailsWindow();">
                <f:attribute name="calculationCode"
                             value="#{joinCalcHelperBeanName.listSize == 0 ? null : list.calculationsDTO.code.key }"/>
            </a4j:commandButton>
        </t:column>
    </t:dataTable>
</t:panelGroup>

<f:verbatim>
    <br/>
</f:verbatim>
