<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel">
        <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}"
                        reRender="divDeleteAlert,OperationBar,divEditLookup"
                        oncomplete="javascript:FireButton('editButton');">
            <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
            <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
            <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
            <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
        </a4j:jsFunction>
        <t:dataTable id="dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column id="radio_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{pageBeanName.singleSelection}">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                                  onkeyup="dataTableSelectOneRadio(this);" onmousedown="dataTableSelectOneRadio(this);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                                 reRender="divEditLookup,OperationBar"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="code_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_yearName" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.year_name}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='yearName') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='yearName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_name" value="#{list.yearName}"/>
            </t:column>
            <t:column id="start_date" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="startDate" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.start_date}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='startDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='startDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_start_date" value="#{list.startDate}">
                    <f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+2"/>
                </t:outputText>
            </t:column>
            <t:column id="end_date" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="endDate" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.end_date}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='endDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='endDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_end_date" value="#{list.endDate}">
                    <f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+2"/>
                </h:outputText>
            </t:column>
            <t:column id="budget_start_date" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="budgetStartDate" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.budget_start_date}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='budgetStartDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='budgetStartDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_budgetStartDate" value="#{list.budgetStartDate}">
                    <f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+2"/>
                </h:outputText>
            </t:column>
            <t:column id="budget_end_date" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="budgetEndDate" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.budget_end_date}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='budgetEndDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='budgetEndDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_budgetEndDate" value="#{list.budgetEndDate}">
                    <f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+2"/>
                </h:outputText>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<script type="text/javascript">
  /*------------------------------------ To enable single selection in dataTable -------------------------------------------------*/

  function dataTableSelectOneRadio(radio) {
      var radioId = radio.name.substring(radio.name.lastIndexOf(':'));

      for (var i = 0;i < radio.form.elements.length;i++) {
          var element = radio.form.elements[i];
          if (element.name != null || element.name == '') {
              if (element.name.substring(element.name.lastIndexOf(':')) == radioId) {
                  element.checked = false;
              }
          }
      }

      radio.checked = true;
  }
</script>
<t:saveState value="#{pageBeanName.singleSelection}"/>
<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.highlightedDTOS}"/>
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.sortAscending}"/>
<!-- Start Paging -->
<t:saveState value="#{pageBeanName.currentPageIndex}"/>
<t:saveState value="#{pageBeanName.oldPageIndex}"/>
<t:saveState value="#{pageBeanName.sorting}"/>
<t:saveState value="#{pageBeanName.usingPaging}"/>
<t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
<t:saveState value="#{pageBeanName.resettedPageIndex}"/>
<t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
<t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
<!-- End Paging -->
<script type="text/javascript">
  function FireButton(buttonId) {
      var button = document.getElementById(buttonId);
      button.click();

  }
</script>