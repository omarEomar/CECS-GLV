<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

 <t:panelGroup forceId="true" id="dataT_data_panel">
        <t:dataTable id="dataT_data" var="list" 
                    value="#{detailBeanName.currentDisplayDetails}"
                     rowIndexVar="index" renderedIfEmpty="false" binding="#{detailBeanName.currentDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" 
                     headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" 
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%"
                     align="top" dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" 
                     sortAscending="#{pageBeanName.ascending}">
                        
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%" rendered="#{pageBeanName.maintainMode!=2}">
                <f:facet name="header">
                    <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                        <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"
                                     oncomplete="setAll('checkAll', 'chk');" reRender="divDeleteAlert,OperationBar"/>
                    </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}"
                                 oncomplete="checkCheckAll('chk');"
                                 reRender="divEditLookup,divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </t:column>
            <t:column id="code_column"  width="10%">
                <f:facet name="header"> 
        
                    <%--t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{detailBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink--%>
                    <t:outputText id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}"/>
                                
                </f:facet>
                <t:outputText id="content_code" value="#{list.code.keys[4] > 0 ? list.code.keys[4] : regResources.unDefineLabel}"/>
            </t:column>
            <t:column id="name_column"  width="65%">
                <f:facet name="header">
                    
                    <%--t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{regResources.columnName}" actionListener="#{detailBeanName.sortDataTable}}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink--%>
                    <t:outputText id="sort_name" forceId="true" styleClass="headerLink" value="#{regResources.columnName}"/>
                </f:facet>                            
                <t:outputText id="content_name"  value="#{list.name}"/>
            </t:column>
            <t:column id="validitiesDTO-name_column"  width="20%">
                <f:facet name="header">
                            
                     <%--t:commandLink id="sort_validitiesDTO-name" forceId="true" styleClass="headerLink" value="#{regResources.regulation_References_type}" actionListener="#{detailBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='validitiesDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='validitiesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink--%>
                    <t:outputText id="sort_validitiesDTO-name" forceId="true" styleClass="headerLink" value="#{regResources.regulation_References_type}"/>
                </f:facet>
                <t:outputText id="content_validity" value="#{list.destabcolumnType==detailBeanName.STRING_DATATYPE? regResources.str_value : regResources.int_value}"/>
            </t:column>       
        </t:dataTable>
        <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg" rendered="#{detailBeanName.currentListSize == 0}"/>
   </t:panelGroup>             