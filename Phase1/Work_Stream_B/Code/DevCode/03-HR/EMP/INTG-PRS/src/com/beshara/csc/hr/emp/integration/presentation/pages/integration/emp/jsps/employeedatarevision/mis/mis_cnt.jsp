<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGroup id="misGroupList" forceId="true" style="background-color:#ffffff;" colspan="6">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
        <htm:tr>
            <htm:td width="9">
                <t:graphicImage value="/app/media/images/op_arrow.jpg" width="9" height="9" border="0"/>
            </htm:td>
            <htm:td style="background: none repeat scroll 0% 0% white;">
                <t:outputLabel value="#{resourcesBundle.mis_name}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <t:graphicImage value="/app/media/images/seprator_group.jpg" width="100%" height="1" border="0"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
<t:panelGroup id="firstTablePG" forceId="true" styleClass="dataT-With-7-row-filter-and-collapse" style="margin-left: 7px;">
    <t:dataTable id="dataT_firstTable" var="list" value="#{pageBeanName.misDataTableLst}" preserveDataModel="false"
                 renderedIfEmpty="false" footerClass="grid-footer" sortable="false" styleClass="grid-footer"
                 headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2" width="100%"
                 align="center" rowIndexVar="index">
        <t:column id="planCode_column" sortable="false" width="10%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.planCode}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="planCode_content" value="#{list.planCode}"/>
        </t:column>
        <t:column id="missionCode_column" sortable="false" width="10%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.missionCode}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="missionCode_content" value="#{list.missionCode}"/>
        </t:column>
        <t:column id="missionName_column" sortable="false" width="50%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.missionName}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="missionName_content" value="#{list.missionName}"/>
        </t:column>
        <t:column id="cntryName_column" sortable="false" width="10%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.cntryName}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="cntryName_content" value="#{list.cntryName}"/>
        </t:column>
        <t:column id="candStatusName_column" sortable="false" width="20%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.candStatusName}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="candStatusName_content" value="#{list.candStatusName}"/>
        </t:column>
    </t:dataTable>
    <t:panelGrid columns="1" rendered="#{pageBeanName.misLstSize == 0}" align="center">
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </t:panelGrid>
</t:panelGroup>
<t:panelGroup id="vacGroupList" forceId="true" style="background-color:#ffffff;" colspan="6">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
        <htm:tr>
            <htm:td width="9">
                <t:graphicImage value="/app/media/images/op_arrow.jpg" width="9" height="9" border="0"/>
            </htm:td>
            <htm:td style="background: none repeat scroll 0% 0% white;">
                <t:outputLabel value="#{resourcesBundle.vac_name}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <t:graphicImage value="/app/media/images/seprator_group.jpg" width="100%" height="1" border="0"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
<t:panelGroup id="secondTablePG" forceId="true" styleClass="dataT-With-7-row-filter-and-collapse" style="margin-left: 7px;">
    <t:dataTable id="dataT_secondTable" var="list" value="#{pageBeanName.vacDataTableLst}" preserveDataModel="false"
                 renderedIfEmpty="false" footerClass="grid-footer" sortable="false" styleClass="grid-footer"
                 headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2" width="100%"
                 align="center" rowIndexVar="index">
        <t:column id="planCode1_column" sortable="false" width="10%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.planCode}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="planCode1_content" value="#{list.planCode}"/>
        </t:column>
        <t:column id="missionCode1_column" sortable="false" width="10%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.missionCode}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="missionCode1_content" value="#{list.missionCode}"/>
        </t:column>
        <t:column id="missionName1_column" sortable="false" width="50%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.missionName}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="missionName1_content" value="#{list.missionName}"/>
        </t:column>
        <t:column id="cntryName1_column" sortable="false" width="10%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.cntryName}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="cntryName1_content" value="#{list.cntryName}"/>
        </t:column>
        <t:column id="candStatusName1_column" sortable="false" width="20%">
            <f:facet name="header">
                <t:outputText value="#{resourcesBundle.candStatusName}" styleClass="headerLink"/>
            </f:facet>
            <h:outputText id="candStatusName1_content" value="#{list.candStatusName}"/>
        </t:column>
    </t:dataTable>
    <t:panelGrid columns="1" rendered="#{pageBeanName.vacLstSize == 0}" align="center">
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </t:panelGrid>
</t:panelGroup>