<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

             
          <t:panelGroup forceId="true" id="dataT_data_panel"> 
                <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"  rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"    forceIdIndexFormula="#{list.code.key}" 
                              rowIndexVar="index" rows="#{shared_util.noOfTableRows}"
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
                                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"  oncomplete="setAll('checkAll', 'chk');"  reRender="OperationBar,divConfirmApprove"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}" >
                            <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}" oncomplete="checkCheckAll('chk');" reRender="OperationBar,divConfirmApprove" />
                        </t:selectBooleanCheckbox>
                    </t:column>
                    
                    
                    
                    <t:column id="code_column"  width="10%">
                        <f:facet name="header">                            
                                <h:outputText id="header_code" value="#{resourcesBundle.request_code}"/>                            
                        </f:facet>
                       <h:outputText id="content_code" value="#{list.code.key}"/>
                    </t:column>
                    
                    
                    
                    <t:column id="name_type"  width="20%">
                        <f:facet name="header">
                            <h:outputText id="header_type" value="#{resourcesBundle.request_name}"/>
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.typeDTO.name}"/>
                    </t:column>
                     
                    
                    <t:column id="reason_clmn" width="25%">
                        <f:facet name="header">
                            <h:outputText id="reason_title" value="#{resourcesBundle.request_reason}"/>
                        </f:facet>
                        <h:outputText id="reason_txt" value="#{list.reasonDTO.name}"/>
                    </t:column>
                    
                    
                    <t:column id="activation_date"  width="25%">
                        <f:facet name="header">
                            <h:outputText id="activation_date_txt" value="#{resourcesBundle.resuest_date}"/>
                        </f:facet>
                        <h:outputText id="activation_date_label" value="#{list.reqDate}" dir="ltr">
                        <f:converter converterId="TimeStampConverter"/>
                        </h:outputText>
                    </t:column>
                    
                    
                    <t:column id="status"  width="15%">
                        <f:facet name="header">
                            <h:outputText id="status_header" value="#{resourcesBundle.request_status}"/>
                        </f:facet>
                        
                        <h:outputText id="completed_label" value="#{resourcesBundle.completed_reqest}" rendered="#{list.completeFlag == 1}"/>
                    </t:column>
                    
                    
                    
                </t:dataTable>
<h:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }">
    <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg" />
</h:panelGrid>
<t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}"  binding="#{pageBeanName.reloadField}" />
</t:panelGroup>
        
              