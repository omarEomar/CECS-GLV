<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
    #grsIntegrationRelatedConditionsTblPnl{ 
        display: block; height: 110px; overflow: auto;width:100% !important; 
    }
</htm:style>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.csc.integration.presentation.resources.integrate" var="integrateResources"/>
<htm:center>
    <t:outputText value="#{globalResources.relatedConditionsDivTitle}" styleClass="TitlePage"/>
</htm:center>
<t:panelGroup id="grsIntegrationRelatedConditionsDiv" forceId="true">
    <t:saveState value="#{pageBeanName.conditionDivService.relatedConditionsList}"/>
    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
        <t:panelGroup forceId="true" id="grsIntegrationRelatedConditionsTblPnl">
            <t:dataTable forceId="true" id="grsIntegrationRelatedConditionsTbl" var="list" value="#{pageBeanName.conditionDivService.relatedConditionsList}"
                         forceIdIndexFormula="#{list.code.key}" rowIndexVar="index"
                         renderedIfEmpty="false"
                         rowOnMouseOver="javascript:addRowHighlight('myForm:grsIntegrationRelatedConditionsTbl');" footerClass="grid-footer"
                         styleClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2"
                         columnClasses="standardTable_ColumnCentered"
                         width="100%" align="top" dir="#{shared_util.pageDirection}">
                <t:column id="code_column" sortable="false" width="5%">
                    <f:facet name="header">
                        <h:outputText id="header_code" value="#{integrateResources.divInfoConditionCode}"/>
                    </f:facet>
                    <h:outputText id="content_code" value="#{list.code.conditionCode}" converter="javax.faces.Long"/>
                </t:column>
                <t:column id="name_column" sortable="false" width="35%">
                    <f:facet name="header">
                        <h:outputText id="header_name" value="#{integrateResources.divInfoConditionName}"/>
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.conditionsDTO.name}"/>
                </t:column>
                <t:column id="fromDate_column" sortable="false" width="75%">
                    <f:facet name="header">
                        <h:outputText id="header_fromDate" value="#{integrateResources.divInfoFromDate}"/>
                    </f:facet>
                    <h:outputText id="content_fromDate" value="#{list.fromDate}" converter="SqlDateConverter"/>
                </t:column>
                <t:column id="untilDate_column" sortable="false" width="75%">
                    <f:facet name="header">
                        <h:outputText id="header_untilDate" value="#{integrateResources.divInfoUntilDate}"/>
                    </f:facet>
                    <h:outputText id="content_untilDate" value="#{list.untilDate}" converter="SqlDateConverter"/>
                </t:column>
            </t:dataTable>
            <t:panelGrid columns="1" rendered="#{ pageBeanName.conditionDivService.relatedConditionsListSize == 0 }" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid columns="1" border="0" width="100%" columnClasses="center">
        <t:panelGroup>
            <t:commandButton forceId="true" type="button" onclick="hideLookUpDiv();focusAfterBackFromRelatedConditionsDiv();"
                             id="relatedConditionsBackBtn"
                             styleClass="cssButtonSmall" value="#{globalResources.back}"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<script type="text/javascript">

      function focusAfterBackFromRelatedConditionsDiv() {
          if (typeof (setFocusAfterBackFromRelatedConditionsDiv) != 'undefined') {
              setFocusAfterBackFromRelatedConditionsDiv();
          }
      }
</script>
