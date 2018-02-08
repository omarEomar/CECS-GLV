<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-5-row-filter" >
        <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}"
                        reRender="OperationBar,dataT_data_panel,paging_panel"
                        oncomplete="javascript:FireButton('editButton');">
            <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
            <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
            <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
            <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
        </a4j:jsFunction>        
        <t:dataTable forceId="true" id="dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false"
                     binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}"
                     rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});">
            <t:column id="radio_column" styleClass="standardTable_Header3" width="5%" >
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);" disabled="#{pageBeanName.pageMode == 1}">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
            </t:column>
            <%--<t:column id="code_column" sortable="false" width="5%">
                <f:facet name="header">
                    <t:commandLink id="sort_code" forceId="true" styleClass="headerLink"
                                   value="#{intgBundle.code}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>--%>
            <t:column id="caderName_col" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_caderName" forceId="true" styleClass="headerLink"
                                   value="#{intgBundle.cader}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='caderName') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='caderName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_caderName" value="#{list.caderName}"/>
            </t:column>
            
            <t:column id="groupName_col" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_groupName" forceId="true" styleClass="headerLink"
                                   value="#{intgBundle.group}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='groupName') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='groupName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_groupName" value="#{list.groupName}"/>
            </t:column>
            
            <t:column id="degreeName_col" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_degreeName" forceId="true" styleClass="headerLink"
                                   value="#{intgBundle.Degree}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='degreeName') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='degreeName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_degreeName" value="#{list.degreeName}"/>
            </t:column>
            
            <t:column id="budgetItem_col" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_bgtAccountsDTO-actName" forceId="true" styleClass="headerLink"
                                   value="#{intgBundle.budgetItem}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bgtAccountsDTO-actName') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bgtAccountsDTO-actName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_budgetItem" value="#{list.bgtAccountsDTO.actName}"/>
            </t:column>
            
            <t:column id="status_column" sortable="false" width="5%">
                <f:facet name="header">
                    <t:commandLink id="sort_status" forceId="true" styleClass="headerLink"
                                   value="#{globalResources.status}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                
                <t:graphicImage id="graph1" value="/app/media/images/green-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{intgBundle.valid}')"
                                rendered="#{list.status == 1}"/>
                <t:graphicImage id="graph2" value="/app/media/images/red-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{intgBundle.invalid}')"
                                rendered="#{list.status == 0}"/>
            </t:column>
            
            <t:column id="defaultFlag_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_defaultFlag" forceId="true" styleClass="headerLink"
                                   value="#{intgBundle.defaultValue}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='defaultFlag') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='defaultFlag') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:selectBooleanCheckbox id="default_value_" value="#{list.defaultFlag == 1}" disabled="true"/>
            </t:column>
            
        </t:dataTable>
        <t:panelGrid rendered="#{empty pageBeanName.myTableData}" styleClass="msg">
            <h:outputText value="#{globalResources.global_noTableResults}"/>
        </t:panelGrid>
</t:panelGroup>
