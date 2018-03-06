<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.highlightedDTOS}"/>
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
<t:saveState value="#{pageBeanName.pageDTO}"/>
<t:saveState value="#{pageBeanName.myDataTable}"/>
<t:inputHidden value="#{pageBeanName.selectedListSize}"/>
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.sortAscending}"/>
<t:messages/>
<t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-1-row-filter">
    <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}"
                    reRender="divDeleteAlert,OperationBar,divEditLookup"
                    oncomplete="javascript:FireButton('editButton');">
        <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
        <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
        <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
        <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
    </a4j:jsFunction>
    <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.myTableData}"
                 rowStyleClass="#{pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                 forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                 renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                 rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                 styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2"
                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                 width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                 sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
        <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                    <f:attribute name="checkAll" value="true"/>
                    <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"
                                 oncomplete="setAll('checkAll', 'chk');" reRender="divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                             oncomplete="checkCheckAll('chk');" reRender="divDeleteAlert,OperationBar"/>
            </t:selectBooleanCheckbox>
        </t:column>
        <t:column id="code_column" sortable="false" width="10%">
            <f:facet name="header">
                <%-- <t:commandSortHeader id="codeColumn" columnName="code" arrow="false"
                     styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending"> <t:graphicImage
                     id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                     </f:facet> <f:facet name="descending"> <t:graphicImage id="descendingArrow"
                     value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                     <h:outputText id="header_code" value="#{globalResources.Code}"/> </t:commandSortHeader>--%>
                <t:commandLink id="sort_code-resdatSerial" forceId="true" styleClass="headerLink"
                               value="#{globalResources.Code}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-resdatSerial') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-resdatSerial') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_code" value="#{list.code.resdatSerial}"/>
        </t:column>
        <t:column id="name_column" sortable="false" width="75%">
            <f:facet name="header">
                <%-- <t:commandSortHeader id="nameColumn" columnName="name" arrow="false"
                     styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending"> <t:graphicImage
                     id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                     </f:facet> <f:facet name="descending"> <t:graphicImage id="descendingArrow"
                     value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                     <h:outputText id="header_name" value="#{globalResources.SearchName}"/> </t:commandSortHeader>--%>
                <t:commandLink id="sort_name" forceId="true" styleClass="headerLink"
                               value="#{globalResources.SearchName}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_name" value="#{list.name}"/>
        </t:column>
    </t:dataTable>
    <h:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }">
        <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </h:panelGrid>
</t:panelGroup>
<script type="text/javascript">
  function FireButton(buttonId) {
      var button = document.getElementById(buttonId);
      button.click();

  }
</script>
