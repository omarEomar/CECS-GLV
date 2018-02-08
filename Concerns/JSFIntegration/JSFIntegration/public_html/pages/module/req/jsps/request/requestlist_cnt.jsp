<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

 
            <t:panelGroup forceId="true" id="dataT_data_panel">
                    <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"  rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"    forceIdIndexFormula="#{list.code.key}" 
                                 rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                                 renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                                 footerClass="grid-footer" styleClass="grid-footer"
                                 headerClass="standardTable_Header" 
                                 rowClasses="standardTable_Row1,standardTable_Row2"
                                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                                 width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
                            
                        <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                            <f:facet name="header">
                                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                                    <f:attribute name="checkAll" value="true"/>
                                    <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"  oncomplete="setAll('checkAll', 'chk');"  reRender="divDeleteAlert,OperationBar"/>
                                </t:selectBooleanCheckbox>
                            </f:facet>
                            <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}" disabled="#{list.booleanCompleteFlag}">
                                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}" oncomplete="checkCheckAll('chk');" reRender="divDeleteAlert,OperationBar" />
                            </t:selectBooleanCheckbox>
                        </t:column>
                        
                        <t:column id="code_column" sortable="false" width="7%">
                            <f:facet name="header">
                                <%--
                                <t:commandSortHeader id="codeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                    <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <h:outputText id="header_code" value="#{globalResources.Code}"/>
                                </t:commandSortHeader>
                                --%>
                                <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{pageBeanName.sortDataTable}">                         
                                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                                </t:commandLink>
                                
                            </f:facet>
                           <h:outputText id="content_code" value="#{list.code.key}"/>
                        </t:column>
                        
                         <t:column id="requestType_column" sortable="false" width="20%">
                            <f:facet name="header">
                                <%--
                                <t:commandSortHeader id="requestTypeColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                    <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <h:outputText id="requestType_name" value="#{resourcesBundle.typeTitle}"/>
                                </t:commandSortHeader>
                                --%>
                                <t:commandLink id="sort_typeDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.typeTitle}" actionListener="#{pageBeanName.sortDataTable}">                         
                                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='typeDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='typeDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                                </t:commandLink>
                            </f:facet>
                            <h:outputText id="content_requestType" value="#{list.typeDTO.name}"/>
                        </t:column>
                        
                         <t:column id="requestReason_column" sortable="false" width="20%">
                            <f:facet name="header">
                                <%--
                                <t:commandSortHeader id="requestReasonColumn" columnName="reasonname" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                    <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <h:outputText id="header_requestReason" value="#{resourcesBundle.request_reason}"/>
                                </t:commandSortHeader>
                                --%>
                                <t:commandLink id="sort_reasonDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.request_reason}" actionListener="#{pageBeanName.sortDataTable}">                         
                                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='reasonDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='reasonDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                                </t:commandLink>
                                
                            </f:facet>
                            <h:outputText id="content_name" value="#{list.reasonDTO.name}"/>
                        </t:column>
                        
                        <t:column id="requestDate_column" sortable="false" width="20%">
                            <f:facet name="header">
                                <%--
                                <t:commandSortHeader id="requestDateColumn" columnName="reqDatename" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                    <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <h:outputText id="header_requestDate" value="#{resourcesBundle.requestDate}" />
                                </t:commandSortHeader>
                                --%>
                                <t:commandLink id="sort_reqDate" forceId="true" styleClass="headerLink" value="#{resourcesBundle.requestDate}" actionListener="#{pageBeanName.sortDataTable}">                         
                                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='reqDate') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='reqDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                                </t:commandLink>
                                
                            </f:facet>
                            <t:outputLabel id="content_requestDate" value="#{list.reqDate}"  dir="ltr">
                                <f:converter converterId="TimeStampConverter"/>
                            </t:outputLabel>
                        </t:column>
                        
                         <t:column id="requestStatus_column" sortable="false" width="18%">
                            <f:facet name="header">
                                <%--
                                <t:commandSortHeader id="requestStatusColumn" columnName="trxStatusname" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                    <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <h:outputText id="header_requestStatus" value="#{resourcesBundle.statusTitleRev}"/>
                                </t:commandSortHeader>
                                --%>
                                <t:commandLink id="sort_trxStatusDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.statusTitleRev}" actionListener="#{pageBeanName.sortDataTable}">                         
                                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='trxStatusDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='trxStatusDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                                </t:commandLink>
                                
                            </f:facet>
                            <h:outputText id="content_requestStatus" value="#{list.trxStatusDTO.name}"/>
                        </t:column>
                        
                         <t:column id="completedOrNot_column" sortable="false" width="10%">
                            <f:facet name="header">
                                <%--
                                <t:commandSortHeader id="completedOrNotHeader_column" columnName="completedOrNot_column" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                    <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                    </f:facet>
                                    <h:outputText id="header_completedOrNot" value="#{resourcesBundle.statusTitle}"/>
                                </t:commandSortHeader>
                                --%>
                                <t:commandLink id="sort_isBooleanCompleteFlag" forceId="true" styleClass="headerLink" value="#{resourcesBundle.statusTitle}" actionListener="#{pageBeanName.sortDataTable}">                         
                                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='isBooleanCompleteFlag') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='isBooleanCompleteFlag') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                                </t:commandLink>
                                
                            </f:facet>
                            <h:outputText id="content_completedOrNot" value="#{list.booleanCompleteFlag  ? resourcesBundle.completed_reqest : resourcesBundle.notCompleted_reqest}"
                            />
                        </t:column>
                        
                    </t:dataTable>
<h:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }">
    <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg" />
</h:panelGrid>

</t:panelGroup> 
