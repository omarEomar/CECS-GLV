<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" id="relatedDecisionsMainPanel" align="center" forceId="true">    
    <t:panelGroup forceId="true" id="relatedDecisionsCustomDiv" styleClass="delDivScroll"  >
        <t:dataTable id="relatedDecisions_data" var="list1" value="#{pageBeanName.decisionsList}"                     
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" 
                     rowOnMouseOver="javascript:addRowHighlight('myForm:salCalcException_data');"
                     footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column id="name1_column" sortable="false" width="50%">
                <f:facet name="header">
                    <h:outputText id="sort_name1" forceId="true" styleClass="headerLink"
                                  value="#{resourcesBundle.dec_no}"/>
                </f:facet>
                <h:outputText id="content_name1" value="#{list1.regNum}"/>
            </t:column>
            
        </t:dataTable>
    </t:panelGroup>
    
</t:panelGrid>
<t:panelGrid columns="1" border="0" align="center"  width="100%" style="text-align: center;">
        <t:commandButton id="backButtonIntegrationDiv2" forceId="true" 
                         styleClass="cssButtonSmall" value="#{globalResources.back}" 
                         onclick="hideLookUpDiv(window.blocker,window.integrationDiv2);return false;">
        </t:commandButton>

</t:panelGrid>

