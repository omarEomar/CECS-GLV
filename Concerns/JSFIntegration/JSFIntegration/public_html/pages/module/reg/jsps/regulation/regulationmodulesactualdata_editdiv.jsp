<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:saveState value="#{detailBeanName.actualDataList}"/>
<t:saveState value="#{detailBeanName.availableColumns}"/>
<t:saveState value="#{detailBeanName.toBeDeleteedRows}"/>
<t:saveState value="#{detailBeanName.totalSize}"/>



<t:panelGroup forceId="true" id="divEditLookup">
    <t:panelGroup styleClass="relatedSysDivScroll" >
    <a4j:jsFunction  name="selectedRow" actionListener="#{detailBeanName.selectedRows}"  />
    
    
    
        <t:dataTable id="dataT_actualData" var="list" value="#{detailBeanName.actualDataList}"     
                             rowIndexVar="index" 
                             binding="#{detailBeanName.actualDataDataTable}" renderedIfEmpty="false" forceId="true"
                             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_actualData');"
                             footerClass="grid-footer" styleClass="grid-footer"
                             headerClass="standardTable_Header" 
                             rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                             width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true" >
                             
                             
                             
                                       
                    <%-- t:column id="check_column" styleClass="standardTable_Header2" width="5%">
                            <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}" >
                                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedRows}"
                                   reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                            </t:selectBooleanCheckbox>
                    </t:column--%>
                    
                    <%--t:column id="check_column" styleClass="standardTable_Header2" width="5%">
                        <f:facet name="header">
                          <h:outputText id="test_column" value="c1" />
                        </f:facet>                       
                          <h:outputText id="test_column_data" value="#{list.name}" />                       
                    </t:column--%>
                           
            </t:dataTable>
            
            <t:panelGrid columns="1" rendered="#{empty detailBeanName.actualDataList}" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
            </t:panelGroup>
            <h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
            
            <t:panelGrid columns="2" border="0" align="center">
            <t:commandButton forceId="true" id="jsfOkBtn"  action="#{detailBeanName.deleteRecords}" styleClass="cssButtonSmall" value="#{regResources.relatedSystemsDeleteBtnLabel}" rendered="#{!empty detailBeanName.toBeDeleteedRows}"  />
            
            <t:commandButton forceId="true" id="CancelButtonEdit" action="#{detailBeanName.hideDiv}" onblur="document.getElementById('jsfOkBtn').focus();"   styleClass="cssButtonSmall" value="#{globalResources.back}"
                             />
                             
            
        </t:panelGrid>
</t:panelGroup>


