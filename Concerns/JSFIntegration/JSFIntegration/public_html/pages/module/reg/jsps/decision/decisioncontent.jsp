<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

                
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                
                <t:dataTable id="dataT_data" var="list" value="#{decisionListBean.myTableData}"   
                             forceIdIndexFormula="#{list.code.key}" 
                             rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                             renderedIfEmpty="false" binding="#{decisionListBean.myDataTable}"
                             rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                             footerClass="grid-footer" styleClass="grid-footer"
                             headerClass="standardTable_Header" 
                             rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                             width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true" 
                             sortColumn="#{decisionListBean.sortColumn}" sortAscending="#{decisionListBean.ascending}">
                        
                    
                    <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                        <f:facet name="header">
                            <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}" >
                                <f:attribute name="checkAll" value="true"/>
                                <f:attribute name="listSize" value="#{decisionListBean.listSize}"/>
                                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"  oncomplete="setAll('checkAll', 'chk');"  reRender="divDeleteAlert,OperationBar"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        
                        <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}" >
                            <a4j:support event="onclick" oncomplete="checkAllCheckBox();" actionListener="#{pageBeanName.selectedCheckboxs}" reRender="validDecision,cancelDescision,divDeleteAlert,OperationBar" />
                        </t:selectBooleanCheckbox>
                    </t:column>
                    
                    <t:column id="typesDTO-name_column" width="10%">
                        <f:facet name="header">
                            <t:commandLink id="sort_typesDTO-name" forceId="true" styleClass="headerLink" value="#{regResources.type}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='typesDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='typesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            
                            <%--t:commandSortHeader id="typeColumn" columnName="typeColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_type" value="#{regResources.type}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                       <h:outputText id="dec_type" value="#{list.typesDTO.name}"/>
                    </t:column>
                    
                    <t:column id="yearsDTO-name_column" width="10%" styleClass="standardTable_ColumnCentered">
                        <f:facet name="header">
                            <t:commandLink id="sort_yearsDTO-name" forceId="true" styleClass="headerLink" value="#{regResources.reg_year}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='yearsDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='yearsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="yearColumn" columnName="yearColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_dec_year" value="#{regResources.reg_year}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="dec_year" value="#{list.yearsDTO.code.key}"/>
                    </t:column>
                    
                    <t:column id="code-decisionNumber_column" width="10%">
                        <f:facet name="header">
                            <t:commandLink id="sort_code-decisionNumber" forceId="true" styleClass="headerLink" value="#{regResources.dec_number}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-decisionNumber') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-decisionNumber') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="numberColumn" columnName="numberColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_dec_number" value="#{regResources.dec_number}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                       <h:outputText id="dec_number" value="#{list.code.decisionNumber}"/>
                    </t:column>
                    
                    <t:column id="decisionTitle_column" width="10%"  rendered="false" styleClass="standardTable_Column">
                        <f:facet name="header">
                            <t:commandLink id="sort_decisionTitle" forceId="true" styleClass="headerLink" value="#{regResources.dec_title}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionTitle') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionTitle') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="titleColumn" columnName="titleColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_dec_title" value="#{regResources.dec_title}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                       <h:outputText id="dec_title" value="#{list.decisionTitle}"  />
                    </t:column>
                    
                    <t:column id="decisionMakerTypesDTO-name_column" width="10%"  rendered="false">
                        <f:facet name="header">
                            <t:commandLink id="sort_decisionMakerTypesDTO-name" forceId="true" styleClass="headerLink" value="#{regResources.dec_decision_maker}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionMakerTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionMakerTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="decision_makerColumn" columnName="decision_makerColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_decision_maker" value="#{regResources.dec_decision_maker}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                       <h:outputText id="decision_maker" value="#{list.decisionMakerTypesDTO.name}"/>
                    </t:column>
                    
                    <t:column id="decisionDate_column" width="10%">
                        <f:facet name="header">
                            <t:commandLink id="sort_decisionDate" forceId="true" styleClass="headerLink" value="#{regResources.dec_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="dec_dateColumn" columnName="dec_dateColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_dec_date" value="#{regResources.dec_date}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                       <t:outputText converter="TimeStampConverter"  id="dec_date" value="#{list.decisionDate}"/>
                    </t:column>
                    
                    
                    <t:column id="decisionAppliedDate_column" width="10%">
                        <f:facet name="header">
                            <t:commandLink id="sort_decisionAppliedDate" forceId="true" styleClass="headerLink" value="#{regResources.apply_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionAppliedDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionAppliedDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="apply_dateColumn" columnName="apply_dateColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_apply_date" value="#{regResources.apply_date}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                        <t:outputText converter="TimeStampConverter"  id="apply_date" value="#{list.decisionAppliedDate}"/>
                    </t:column>

                    <%--
                    <t:column id="cancel_date_column" width="10%">
                        <f:facet name="header">
                            <t:commandSortHeader id="cancel_dateColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_cancel_date" value="#{regResources.cancel_date}"/>
                            </t:commandSortHeader>
                        </f:facet>
                        <t:outputText converter="TimeStampConverter" id="cancel_date" value="#{list.regCancelDate}"/>
                    </t:column>
                    --%>
                    
                    <t:column id="status_column" width="10%" styleClass="standardTable_ColumnCentered">
                        <f:facet name="header">
                            <t:commandLink id="sort_status" forceId="true" styleClass="headerLink" value="#{regResources.status}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <f:param name="listExpression" value="decisionListBean.myTableData"/>
                                <f:param name="currentSortingRowIndex" value="decisionListBean.currentSortingRowIndex"/>
                                <f:param name="elExpression" value="(decisionListBean.myTableData[decisionListBean.currentSortingRowIndex].canceledDecision?managedConstantsBean.decisionStatusCanceled:(decisionListBean.myTableData[decisionListBean.currentSortingRowIndex].validDecision?managedConstantsBean.decisionStatusValid:managedConstantsBean.decisionStatusCancel))"/>
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandLink id="sort_6list-canceledDecision9" forceId="true" styleClass="headerLink" value="#{regResources.status}" actionListener="#{pageBeanName.sortDataTable}">
                                <f:param value="" name=""/>
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="statusColumn" propertyName="statusCode" columnName="statusCode" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_status" value="#{regResources.status}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="dec_status_valid" value="#{(list.canceledDecision? regResources.decision_status_text_canceled : (list.validDecision?regResources.decision_status_text_valid:regResources.decision_status_text_cancel) )}" />
                    </t:column>                    
                    
                </t:dataTable>
                
                <h:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }">
                    <h:outputText styleClass="msg" value="#{globalResources.global_noTableResults}" />
                </h:panelGrid>
                <t:inputHidden value="#{decisionListBean.cancelDescisionFlag}" forceId="true" id="cancelDescision"/>
                <t:inputHidden value="#{decisionListBean.validDecision}" forceId="true" id="validDecision"/>
                
