<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
                
                <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"   
                             forceIdIndexFormula="#{list.code.key}" 
                             rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                             renderedIfEmpty="false" binding="#{regulationListBean.myDataTable}"
                             rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                             footerClass="grid-footer" styleClass="grid-footer"
                             headerClass="standardTable_Header" 
                             rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                             width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true" 
                             sortColumn="#{regulationListBean.sortColumn}" sortAscending="#{regulationListBean.ascending}">                        
                    
                    <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                        <%--f:facet name="header">
                            <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}" disabled="#{pageBeanName.tabelPageHaveCanceled}">
                                <f:attribute name="checkAll" value="true"/>
                                <f:attribute name="listSize" value="#{regulationListBean.listSize}"/>
                                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"  oncomplete="setAll('checkAll', 'chk');"  reRender="divDeleteAlert,OperationBar,divCancelingReg"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        
                        <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}" disabled="#{list.statusDto.code.regstatusCode == pageBeanName.statusCanceled}"> 
                            <a4j:support event="onclick" oncomplete="checkAllCheckBox();" actionListener="#{pageBeanName.selectedCheckboxs}" reRender="divDeleteAlert,OperationBar,divCancelingReg,scriptGeneratorID,SuggestedORNot,validStatus" />
                        </t:selectBooleanCheckbox--%>
                        <f:facet name="header"/>
                        <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);" onkeypress="toggleRadio(this);">
                           <f:selectItem itemLabel=" " itemValue="#{list.code.key}"/>
                           <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" reRender="divDeleteAlert,OperationBar,divCancelingReg,scriptGeneratorID,SuggestedORNot,validStatus"/>
                        </t:selectOneRadio>
                    </t:column>
                    
                    <t:column id="typesDto-name_column" rendered="#{pageBeanName.indexArray[0]}" width="10%">
                        <f:facet name="header">
                            <t:commandLink id="sort_typesDto-name" forceId="true" styleClass="headerLink" value="#{regResources.reg_type}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='typesDto-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='typesDto-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="typeColumn" columnName="typeColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_type" value="#{regResources.reg_type}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                       <h:outputText id="reg_type" value="#{list.typesDto.name}"/>
                    </t:column>
                    
                    <t:column id="yearsDto-name_column" rendered="#{pageBeanName.indexArray[1]}" width="10%">
                        <f:facet name="header">
                            <t:commandLink id="sort_yearsDto-name" forceId="true" styleClass="headerLink" value="#{regResources.reg_year}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='yearsDto-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='yearsDto-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="yearColumn" columnName="yearColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_reg_year" value="#{regResources.reg_year}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="reg_year" value="#{list.yearsDto.code.key}"/>
                    </t:column>
                    
                    <t:column id="code-regulationNumber_column" rendered="#{pageBeanName.indexArray[2]}" width="10%">
                        <f:facet name="header">
                            <t:commandLink id="sort_code-regulationNumber" forceId="true" styleClass="headerLink" value="#{regResources.reg_number}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-regulationNumber') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-regulationNumber') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="numberColumn" columnName="numberColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_reg_number" value="#{regResources.reg_number}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                       <h:outputText id="reg_number" value="#{list.code.regulationNumber}"/>
                    </t:column>
                    
                    <t:column id="regulationTitle_column" width="10%" rendered="#{pageBeanName.indexArray[3]}">
                        <f:facet name="header">
                            <t:commandLink id="sort_regulationTitle" forceId="true" styleClass="headerLink" value="#{regResources.reg_title}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regulationTitle') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regulationTitle') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="titleColumn" columnName="titleColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_reg_title" value="#{regResources.reg_title}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                       <h:outputText id="reg_title" value="#{list.regulationTitle}"/>
                    </t:column>
                    
                    <t:column id="decisionMakerDTO-name_column" width="10%" rendered="#{pageBeanName.indexArray[4]}">
                        <f:facet name="header">
                            <t:commandLink id="sort_decisionMakerDTO-name" forceId="true" styleClass="headerLink" value="#{regResources.decision_maker}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionMakerDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionMakerDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="decision_makerColumn" columnName="decision_makerColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_decision_maker" value="#{regResources.decision_maker}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                       <h:outputText id="decision_maker" value="#{list.decisionMakerDTO.name}"/>
                    </t:column>
                    
                    <t:column id="regulationDate_column" rendered="#{pageBeanName.indexArray[5]}" width="10%">
                        <f:facet name="header">
                            <t:commandLink id="sort_regulationDate" forceId="true" styleClass="headerLink" value="#{regResources.reg_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regulationDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regulationDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="reg_dateColumn" columnName="reg_dateColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_reg_date" value="#{regResources.reg_date}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                       <t:outputText converter="TimeStampConverter"  id="reg_date" value="#{list.regulationDate}"/>
                    </t:column>
                    
                    
                    <t:column id="regulationAppliedDate_column" rendered="#{pageBeanName.indexArray[6]}" width="10%">
                        <f:facet name="header">
                            <t:commandLink id="sort_regulationAppliedDate" forceId="true" styleClass="headerLink" value="#{regResources.apply_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regulationAppliedDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regulationAppliedDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="apply_dateColumn" columnName="apply_dateColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_apply_date" value="#{regResources.apply_date}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                        <t:outputText converter="TimeStampConverter"  id="apply_date" value="#{list.regulationAppliedDate}"/>
                    </t:column>

                    <t:column id="regCancelDate_column" width="10%" rendered="#{pageBeanName.indexArray[7]}">
                        <f:facet name="header">
                            <t:commandLink id="sort_regCancelDate" forceId="true" styleClass="headerLink" value="#{regResources.cancel_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regCancelDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regCancelDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="cancel_dateColumn" columnName="cancel_dateColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_cancel_date" value="#{regResources.cancel_date}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                        <t:outputText converter="TimeStampConverter" id="cancel_date" value="#{list.regCancelDate}"/>
                    </t:column>
                    
                    <t:column id="regulationCancelAppliedDate_column" width="10%" rendered="#{pageBeanName.indexArray[8]}">
                        <f:facet name="header">
                            <t:commandLink id="sort_regulationCancelAppliedDate" forceId="true" styleClass="headerLink" value="#{regResources.cancel_applied_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regulationCancelAppliedDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regulationCancelAppliedDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="cancel_applied_dateColumn" columnName="cancel_applied_date_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_cancel_applied_date" value="#{regResources.cancel_applied_date}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                        <t:outputText converter="TimeStampConverter" id="cancel_applied_date" value="#{list.regulationCancelAppliedDate}"/>
                    </t:column>
                    
                    
                    <t:column id="statusDto-name_column" rendered="#{pageBeanName.indexArray[9]}" width="10%">
                        <f:facet name="header">
                            <t:commandLink id="sort_statusDto-name" forceId="true" styleClass="headerLink" value="#{regResources.status}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='statusDto-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='statusDto-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            <%--t:commandSortHeader id="statusColumn" columnName="statusColumn_sort" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_status" value="#{regResources.status}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="status" value="#{list.statusDto.name}"/>
                    </t:column>
                    
                </t:dataTable>
                
                <h:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }">
                    <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
                </h:panelGrid>
                <t:panelGroup>
                    <t:inputHidden value="#{pageBeanName.suggestedFlag}" id="SuggestedORNot" forceId="true"/>
                    <t:inputHidden value="#{pageBeanName.validStatus}" id="validStatus" forceId="true"/>
                </t:panelGroup>
            