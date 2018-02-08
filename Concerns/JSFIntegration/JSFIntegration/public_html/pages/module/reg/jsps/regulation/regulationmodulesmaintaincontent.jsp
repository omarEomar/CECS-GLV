<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

              <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false"
                           value="#{detailBeanName.customCurrentDetails}"
                           rowIndexVar="index" binding="#{detailBeanName.currentDataTable}"
                           rowOnMouseOver="javascript:addRowHighlight('regForm:dataT_data');"
                           footerClass="grid-footer" styleClass="grid-footer"
                           headerClass="standardTable_Header"
                           rowClasses="standardTable_Row1,standardTable_Row2"
                           columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered"
                           width="100%" align="center"
                           dir="#{shared_util.pageDirection}" preserveSort="true">
                   <t:column id="check_column_" width="5%" rendered="#{pageBeanName.maintainMode!=2}">
                        <f:facet name="header">
                            <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,OperationBar"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}">                                            
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar"/>
                        </t:selectBooleanCheckbox>
                   </t:column>
                   
                   <t:column id="table_name_column" width="30%">
                        <f:facet name="header">
                            <h:outputText id="header_tableName" value="#{regResources.regulation_modules_table_name}"/>
                             <%--t:commandSortHeader id="newsNameColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                  <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <h:outputText id="header_tableName" value="#{regResources.regulation_modules_table_name}"/>
                             </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="table_name" value="#{list.tableDesc}"/>
                   </t:column>
                   
                   <t:column id="table_rec_count_column" width="30%">
                        <f:facet name="header">
                            <h:outputText id="header_recordsCount" value="#{regResources.regulation_modules_table_rec_count}"/>
                             <%--t:commandSortHeader id="newsNameColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                  <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <h:outputText id="header_recordsCount" value="#{regResources.regulation_modules_table_rec_count}"/>
                             </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="table_records_count" value="#{list.recordsCount}"/>
                   </t:column>
                   
              </t:dataTable>
			  <h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
            <h:panelGrid columns="1" rendered="#{ detailBeanName.customCurrentDetailsSize == 0 && pageBeanName.maintainMode!=2 }">
                    <h:outputText styleClass="msg" value="#{globalResources.global_noTableResults}"/>
            </h:panelGrid>     
        