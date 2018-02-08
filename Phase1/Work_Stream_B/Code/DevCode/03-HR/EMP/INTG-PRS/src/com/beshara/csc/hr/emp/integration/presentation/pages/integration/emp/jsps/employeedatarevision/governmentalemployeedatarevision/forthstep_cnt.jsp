<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<t:saveState value="#{pageBeanName.myTableData}"/>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-2-row-filter">
    <t:dataTable id="dataListT_data" var="list" renderedIfEmpty="false" value="#{pageBeanName.myTableData}" rowIndexVar="index" binding="#{pageBeanName.myDataTable}"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataListT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                 dir="#{shared_util.pageDirection}" preserveSort="true">
        
        <t:column width="5%">
            <f:facet name="header">
                <h:outputText id="serial" value="#{resourcesBundle.serial}" styleClass="standardTable_Header2"/>
            </f:facet>
            <h:outputText id="content_serial" value="#{index+1}"/>
        </t:column>
        
        <t:column width="10%">
            <f:facet name="header">
                <h:outputText id="fromDate" value="#{resourcesBundle.fromDate}" styleClass="standardTable_Header2"/>
            </f:facet>
            <h:outputText id="content_fromDate" value="#{list.fromDate}">
                <f:converter converterId="TimeStampConverter"/>
            </h:outputText>
        </t:column>
        
        <t:column width="10%">
            <f:facet name="header">
                <h:outputText id="untilDate" value="#{resourcesBundle.untilDate}" styleClass="standardTable_Header2"/>
            </f:facet>
            <h:outputText id="content_untilDate" value="#{list.untilDate}">
                <f:converter converterId="TimeStampConverter"/>
            </h:outputText>
        </t:column>
        
        <t:column width="25%">
            <f:facet name="header">
                <h:outputText id="jobs" value="#{resourcesBundle.job}" styleClass="standardTable_Header2"/>
            </f:facet>
            <h:outputText id="content_jobs" value="#{list.jobsDTO.name}"/>
        </t:column>
        
        <t:column width="20%">
            <f:facet name="header">
                <h:outputText id="ministries" value="#{resourcesBundle.ministry}" styleClass="standardTable_Header2"/>
            </f:facet>
            <h:outputText id="content_ministries" value="#{list.workCentersDTO.ministriesDTO.name}"/>
        </t:column>
        <t:column width="30%">
            <f:facet name="header">
                <t:panelGrid columnClasses="govempCol1,govempCol2,govempCol3" columns="3" border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
                    <t:panelGroup forceId="true" id="periodPnl" onmouseover="resetRowBgColor();" colspan="3" style="width:100%;text-align: center;">
                        <h:outputText id="period" value="#{resourcesBundle.hiredPeriod}" styleClass="standardTable_Header2"/>
                    </t:panelGroup>
                    <t:panelGroup forceId="true" id="dayPnl" onmouseover="resetRowBgColor();" style="width:100%;text-align: center;">
                        <h:outputText id="day" value="#{resourcesBundle.day}" styleClass="standardTable_Header2"/>
                    </t:panelGroup>
                    <t:panelGroup forceId="true" id="monthPnl" onmouseover="resetRowBgColor();" style="width:100%;text-align: center;">
                        <h:outputText id="month" value="#{resourcesBundle.month}" styleClass="standardTable_Header2"/>
                    </t:panelGroup>
                    <t:panelGroup forceId="true" id="yearPnl" onmouseover="resetRowBgColor();" style="width:100%;text-align: center;">
                        <h:outputText id="year" value="#{resourcesBundle.year}" styleClass="standardTable_Header2"/>
                    </t:panelGroup>
                </t:panelGrid>
            </f:facet>
            <t:panelGrid columns="3" columnClasses="govempCol1,govempCol2,govempCol3" border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
                <h:outputText id="content_day" value="#{pageBeanName.serviceDays}" styleClass="divText"/>
                <h:outputText id="content_month" value="#{pageBeanName.serviceMonths}" styleClass="divText"/>
                <h:outputText id="content_year" value="#{pageBeanName.serviceYears}" styleClass="divText"/>
            </t:panelGrid>
        </t:column>
    </t:dataTable>
   
   
    <t:panelGrid align="center" rendered="#{empty pageBeanName.dataList}" styleClass="msg">
        <h:outputText value="#{globalResources.global_noTableResults}"/>
    </t:panelGrid>
</t:panelGroup>
</t:panelGrid>

<script type="text/javascript">
    
    function resetRowBgColor()
    {
        document.getElementById('periodPnl').style.backgroundColor='#094079';
        document.getElementById('dayPnl').style.backgroundColor='#094079';
        document.getElementById('monthPnl').style.backgroundColor='#094079';
        document.getElementById('yearPnl').style.backgroundColor='#094079';
    }
    
</script>
