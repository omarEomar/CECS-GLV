<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

 <t:panelGroup forceId="true" id="dataT_data_panel">
                    <t:dataTable id="dataT_data" var="list" value="#{detailBeanName.currentDisplayDetails}"
                                 forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" renderedIfEmpty="false" binding="#{detailBeanName.currentDataTable}"
                                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                                 rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%"
                                 align="top" dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
          <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header">
                    <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                        <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"
                                     oncomplete="setAll('checkAll', 'chk');" reRender="divDeleteAlert,OperationBar"/>
                    </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}"
                                 oncomplete="checkCheckAll('chk');" reRender="divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </t:column>
            <t:column id="code_column"  width="5%">
                <f:facet name="header">     
                     <t:outputText id="code_name_Id" value="#{globalResources.Code}"/>
                </f:facet>
                    <t:outputText id="content_code" value="#{list.code.keys[3] > 0 ? list.code.keys[3] : regResources.unDefineLabel }"/>
                </t:column>
                <t:column id="name_column"  width="70%">
                  <f:facet name="header">
                          <t:outputText id="name_row_Id" value="#{regResources.tableName}"/>
                 </f:facet>
                            
                            <t:outputText id="content_name" value="#{list.name}"/>
              </t:column>
              
                                  <t:column id="minor_list_count_column" sortable="true" width="10%">
                        <f:facet name="header" >
                      
                        </f:facet>
                            <t:graphicImage id="minorNumber2" value="/app/media/images/icon_details_number.gif" rendered="true" border="0" onmouseover="doTooltip(event,'#{regResources.noColumns}(#{list.noRegDesignTabColumnsDTOList})')" onmouseout="hideTip()"/> <%--   --%>

            </t:column>

                    </t:dataTable>
                    <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg" rendered="#{detailBeanName.currentListSize == 0}"/>
   </t:panelGroup>             