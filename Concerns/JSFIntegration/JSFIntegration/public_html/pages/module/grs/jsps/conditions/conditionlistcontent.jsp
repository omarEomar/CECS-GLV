<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
               <t:saveState value="#{pageBeanName.myTableData}"/>
               <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
               <t:saveState value="#{pageBeanName.searchMode}"/>
               <t:saveState value="#{pageBeanName.selectedDTOS}"/>
               <t:saveState value="#{conditionListBean.parameterText}"/>
               <t:saveState value="#{conditionListBean.lineCodeText}"/>
               <t:saveState value="#{conditionListBean.searchConditionsDTO}"/>
               
<t:panelGroup >
                  <h:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }">
                     <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
                  </h:panelGrid>
                  <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                               forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                               rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                               rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%"
                               align="center" dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
                     <t:column id="check_column" styleClass="standardTable_Header3" width="2%">
                        <f:facet name="header">
                           <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                              <f:attribute name="checkAll" value="true"/>
                              <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                              <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"
                                           oncomplete="setAll('checkAll', 'chk');"
                                           reRender="divDeleteAlert,OperationBar"/>
                           </t:selectBooleanCheckbox>
                        </f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                           <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                                        
                                        reRender="divDeleteAlert,OperationBar"/>
                        </t:selectBooleanCheckbox>
                     </t:column>
                     <t:column id="code_column" sortable="false" width="8%">
                        <f:facet name="header">
                           <%--<t:commandSortHeader id="codeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                              <f:facet name="ascending">
                                 <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                              </f:facet>
                              <f:facet name="descending">
                                 <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                              </f:facet>
                              <h:outputText id="header_code" value="#{globalResources.Code}"/>
                           </t:commandSortHeader>--%>
                    <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                        </f:facet>
                        <h:outputText id="content_code" value="#{list.code.key}"/>
                     </t:column>
                     <t:column id="name_column" sortable="false" width="40%">
                        <f:facet name="header">
                           <%--<t:commandSortHeader id="conditionNameColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                              <f:facet name="ascending">
                                 <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                              </f:facet>
                              <f:facet name="descending">
                                 <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                              </f:facet>
                              <h:outputText id="header_name" value="#{grsResources.Condition_Name}"/>
                           </t:commandSortHeader>--%>
                    <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{grsResources.Condition_Name}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.name}"/>
                     </t:column>
                     <t:column id="desc_column" sortable="false" width="50%">
                        <f:facet name="header">
                           <%--<t:commandSortHeader id="descColumn" columnName="descColumn" arrow="false" styleClass="standardTable_Header2" immediate="true">
                              <f:facet name="ascending">
                                 <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                              </f:facet>
                              <f:facet name="descending">
                                 <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                              </f:facet>
                              <h:outputText id="header_desc" value="#{grsResources.Condition_Description}"/>
                           </t:commandSortHeader>--%>
                    <t:commandLink id="sort_conditionDesc" forceId="true" styleClass="headerLink" value="#{grsResources.Condition_Description}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='conditionDesc') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='conditionDesc') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                        </f:facet>
                        <h:outputText id="content_desc" value="#{list.conditionDesc}"/>
                     </t:column>
                  </t:dataTable>
               </t:panelGroup>
            