<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
    <t:saveState value="#{detailBeanName.currentDisplayDetails}"/>
    

<t:panelGroup forceId="true" id="dataT_data_panel" styleClass="delDivScroll" style="width:100%">
    <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false" value="#{detailBeanName.currentDisplayDetails}" rowIndexVar="index" binding="#{detailBeanName.currentDataTable}"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer,delDivScroll" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top"
                 dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{detailBeanName.sortColumn}" sortAscending="#{detailBeanName.ascending}">
        <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}" oncomplete="setAll('checkAll', 'chk2');" reRender="divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}">
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar"/>
                <%-- <a4j:jsFunction name="checkTableFunction" actionListener="#{detailBeanName.selectedCurrent}"
                     oncomplete="updateButtonsStatusTable();updateMenuItemsStatusTable('myForm_myMenu_menu',myForm_myMenu_menu,'hbl');" reRender="divDeleteAlert,selectedNumber,searchMode" />--%>
            </t:selectBooleanCheckbox>
        </t:column>
        <t:column id="Field_Name" sortable="false" width="20%">
            <f:facet name="header">
                <%--<t:commandSortHeader id="nameColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText value="#{globalResources.Code}"/>
                </t:commandSortHeader>--%>
                <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
            </f:facet>
            <h:outputText id="content_name" value="#{list.code.key}"/>
        </t:column>
        <t:column id="typeTitle" sortable="true" width="60%">
            <f:facet name="header">
                <%--<t:commandSortHeader id="typeTitleName" columnName="name2" arrow="false" styleClass="standardTable_Header2" immediate="true">
                    <f:facet name="ascending">
                        <t:graphicImage id="ascendingArrow1" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <f:facet name="descending">
                        <t:graphicImage id="descendingArrow2" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                    </f:facet>
                    <h:outputText id="header_name" value="#{globalResources.name}"/>
                </t:commandSortHeader>--%>
                <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.name}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_tt" value="#{list.name}"/>
        </t:column>
    </t:dataTable>
    <t:panelGrid rendered="#{detailBeanName.currentListSize == 0}" styleClass="msg">
        <h:outputText value="#{globalResources.global_noTableResults}"/>
        <h:outputText value="#{globalResources.global_noTableResults_m2m}"/>
    </t:panelGrid>
</t:panelGroup>