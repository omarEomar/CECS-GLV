<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%----------------------------- start Data table---------------------%>
 <t:panelGrid forceId="true" id="resultsPnl" border="0" cellpadding="0" cellspacing="0" width="100%" columns="1">
      <t:panelGroup forceId="true" id="dataT_data_panel" 
                  style="height: 58px;" >
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 && (pageBeanName.searchTypeFlag == pageBeanName.searchByBulkFlag)}"
                     align="center">
            <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.searchResult}"
                     rowStyleClass="standardTable_RowHighlighted_GRS" forceIdIndexFormula="#{list.code.key}"
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false"
                     binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_Column,standardTable_ColumnCentered"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}"
                     varDetailToggler="detailToggler">
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{!pageBeanName.singleSelection}">
                <f:facet name="header">
                    <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                        <f:attribute name="checkAll" value="true"/>
                        <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                        <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"
                                     oncomplete="setAll('checkAll', 'chk');"
                                     reRender="buttonsPG"/>
                    </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                                 oncomplete="checkCheckAll('chk');"
                                 reRender="buttonsPG"/>
                </t:selectBooleanCheckbox>
            </t:column>
            <t:column id="code_column" sortable="false" width="20%">
                <f:facet name="header">
                    <h:outputText id="header_code" value="#{globalResources.globalCivilId}"/>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="60%">
                <f:facet name="header">
                    <h:outputText id="header_name" value="#{globalResources.globalName}"/>
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
        
        <%--ddddddd --%>
        
        
    </t:panelGroup>
</t:panelGrid>

            <%----------------------------- end Data table ---------------------%>