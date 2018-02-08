<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:panelGroup forceId="true" id="dataT_data_panel" style="height: 115px;">
         <t:dataTable id="dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     forceIdIndexFormula="#{list.code.key}" rowIndexVar="index"
                      rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer-totale"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top"  dir="#{shared_util.pageDirection}"  
                     preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                     sortAscending="#{pageBeanName.ascending}">
            <t:column id="code_column" sortable="false" width="10%" footercolspan="6">
                <f:facet name="header">
                    <t:commandLink id="sort_code-key" forceId="true" styleClass="headerLink" value="#{globalResources.Code}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-key') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-key') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText  id="content_code" value="#{list.code.key}"/>
                 <f:facet name="footer"    >
                    <h:outputText   forceId="true" id="content_totalServicePeriod" value="#{resourcesBundle.total_service_peroid}" style="font-family: Arial; font-size: 16px; font-weight: bold;" /> 
                </f:facet>
            </t:column>
            <t:column id="ministriesDTO_name_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_ministriesDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.ministry}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministriesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='ministriesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_ministriesDTO_name" value="#{list.ministriesDTO.name}"/>
            </t:column>
            <t:column id="jobsDTO_name_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_jobsDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.job_name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobsDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_jobsDTO_name" value="#{list.jobsDTO.name}"/>
            </t:column>
             <t:column id="workCentersDTO_name_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_workCentersDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.work_center}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCentersDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCentersDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_workCentersDTO_name" value="#{list.workCentersDTO.name}"/>
            </t:column>
             <t:column id="fromDate_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_fromDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.fromDate}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_fromDate" value="#{list.fromDate}" converter="SqlDateConverter"/>
            </t:column>
                 <t:column id="untilDate_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_untilDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.untilDate}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_untilDate" value="#{list.untilDate}" converter="SqlDateConverter"/>
            </t:column>
                 <t:column id="serviceDays_column" sortable="false" width="8%">
                <f:facet name="header">
                    <t:commandLink id="sort_serviceDays" forceId="true" styleClass="headerLink" value="#{resourcesBundle.days_for_dma2em}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='serviceDays') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='serviceDays') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_serviceDays" value="#{list.serviceDays}"/>
                 <f:facet name="footer" >
                    <h:outputText   forceId="true" id="content_totalServicePeriodDays" value="#{pageBeanName.totalServiceMonths}" style="font-family: Arial; font-size: 16px; font-weight: bold;" /> 
                </f:facet>
            </t:column>
                 <t:column id="serviceMonths_column" sortable="false" width="8%">
                <f:facet name="header">
                    <t:commandLink id="sort_serviceMonths" forceId="true" styleClass="headerLink" value="#{resourcesBundle.months_for_dma2em}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='serviceMonths') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='serviceMonths') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_serviceMonths" value="#{list.serviceMonths}"/>
                 <f:facet name="footer" >
                    <h:outputText   forceId="true" id="content_totalServicePeriodMonths" value="#{pageBeanName.totalServiceMonths}" style="font-family: Arial; font-size: 16px; font-weight: bold;" /> 
                </f:facet>
            </t:column>
                 <t:column id="serviceYears_column" sortable="false" width="8%">
                <f:facet name="header">
                    <t:commandLink id="sort_serviceYears" forceId="true" styleClass="headerLink" value="#{resourcesBundle.years_for_dma2em}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='serviceYears') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='serviceYears') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_serviceYears" value="#{list.serviceYears}"/>
                <f:facet name="footer" >
                    <h:outputText   forceId="true" id="content_totalServicePeriodYears" value="#{pageBeanName.totalServiceYears}" style="font-family: Arial; font-size: 16px; font-weight: bold;" /> 
                </f:facet>
            </t:column>
            
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{empty pageBeanName.myTableData}" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
<htm:script>


  checkCollapsed();

 

</htm:script>
           