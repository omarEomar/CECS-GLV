<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>


<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" columns="1">

  <t:panelGroup forceId="true" id="dataT_data_panel">
    
      <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
        <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
      </t:panelGrid>
      <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                   forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                   rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                   rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                   dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
        <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
          <h:selectOneRadio id="popup_radio_str" onclick="toggleRadio(this);" valueChangeListener="#{pageBeanName.radioBtnChecked}">
            <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
          </h:selectOneRadio>
        </t:column>
        <t:column id="code_column" sortable="true" width="10%">
          <f:facet name="header">
            <t:commandSortHeader id="codeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
              <f:facet name="ascending">
                <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
              </f:facet>
              <f:facet name="descending">
                <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
              </f:facet>
              <h:outputText id="header_code" value="#{resourcesBundle.lineCode}"/>
            </t:commandSortHeader>
          </f:facet>
          <h:outputText id="content_code" value="#{list.code.key}"/>
        </t:column>
        <t:column id="name_column" sortable="true" width="85%">
          <f:facet name="header">
            <t:commandSortHeader id="nameColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
              <f:facet name="ascending">
                <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
              </f:facet>
              <f:facet name="descending">
                <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
              </f:facet>
              <h:outputText id="header_name" value="#{resourcesBundle.LineName}"/>
            </t:commandSortHeader>
          </f:facet>
          <h:outputText id="content_name" value="#{list.name}"/>
        </t:column>
      </t:dataTable>
    <%-- t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}" binding="#{pageBeanName.reloadField}"/--%>
  </t:panelGroup>
</t:panelGrid>
<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.highlightedDTOS}"/>
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>

