<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration" var="grsIntgResources"/>
<htm:center>
    <t:outputText value="#{grsIntgResources.relatedConditionsDivTile}" styleClass="TitlePage"/>
    <f:verbatim><br/></f:verbatim>
</htm:center>

<t:panelGroup forceId="true" id="grsIntgRelatedConditionsCnt1" styleClass="divContent1FullWidth" rendered="#{jcHelperBeanName.relatedConditionsCnt1Path != null}">
    <jsp:include page="${jcHelperBeanName.relatedConditionsCnt1Path}" flush="true" />
</t:panelGroup>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
    <t:panelGroup forceId="true" id="grsIntgRelatedConditionsTblPnl" styleClass="#{jcHelperBeanName.relatedConditionsCntStyle}">
        <t:dataTable forceId="true" id="grsIntgRelatedConditionsTbl" var="list" 
                value="#{jcHelperBeanName.myTableData}"
                forceIdIndexFormula="#{list.code.key}" rowIndexVar="index"
                renderedIfEmpty="false"
                rowOnMouseOver="javascript:addRowHighlight('myForm:grsIntgRelatedConditionsTbl');" footerClass="grid-footer"
                styleClass="grid-footer" headerClass="standardTable_Header"
                rowClasses="standardTable_Row1,standardTable_Row2"
                columnClasses="standardTable_ColumnCentered"
                width="100%" align="top" dir="#{shared_util.pageDirection}">
            <t:column id="code_column" sortable="false" width="12%">
                <f:facet name="header">
                    <h:outputText id="header_code" value="#{grsIntgResources.divInfoConditionCode}"/>
                </f:facet>
                <h:outputText id="content_code" value="#{list.conditionsDTO.code.key}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="41%">
                <f:facet name="header">
                    <h:outputText id="header_name" value="#{grsIntgResources.divInfoConditionName}"/>
                </f:facet>
                <h:outputText id="content_name" value="#{list.conditionsDTO.name}"/>
            </t:column>
            <t:column id="fromDate_column" sortable="false" width="12%">
                <f:facet name="header">
                    <h:outputText id="header_fromDate" value="#{grsIntgResources.divInfoFromDate}"/>
                </f:facet>
                <h:outputText id="content_fromDate" value="#{list.fromDate}" converter="SqlDateConverter"/>
            </t:column>
            <t:column id="untilDate_column" sortable="false" width="12%">
                <f:facet name="header">
                    <h:outputText id="header_untilDate" value="#{grsIntgResources.divInfoUntilDate}"/>
                </f:facet>
                <h:outputText id="content_untilDate" value="#{list.untilDate}" converter="SqlDateConverter"/>
            </t:column>
            <t:column id="header_column" sortable="false" width="8%">
                <f:facet name="header">
                    <h:outputText id="header_status" value="#{globalResources.status}"/>
                </f:facet>
                <h:outputText styleClass="#{(list.status == managedConstantsBean.activeFlagConstant)? 'iconCircleGreen' : 'iconCircleGray'}" value="" 
                        onmouseover="doTooltip(event,'#{list.status == managedConstantsBean.activeFlagConstant ? grsIntgResources.Parameter_Status_Valid : grsIntgResources.Parameter_Status_InValid}')"
                        onmouseout="hideTip()"
                        style="display:block;width:75%"/> 
            </t:column>
            <t:column id="details_column" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="header_details" value="#{grsIntgResources.conditionDetails}"/>
                </f:facet>
                <a4j:commandButton styleClass="cssButtonSmaller" id="content_details" value="#{grsIntgResources.conditionDetails}"
                        actionListener="#{jcHelperBeanName.viewConditionDetails}" 
                        reRender="grsIntgHiddenValues" oncomplete="openGrsIntgConditionDetailsWindow();">
                     <f:attribute name="conditionCode" value="#{jcHelperBeanName.listSize == 0 ? null : list.conditionsDTO.code.key }"/>
                </a4j:commandButton>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{jcHelperBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
