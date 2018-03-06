<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.ContainerTitle {
    min-width: 110px !important;
    width: auto;
}
</htm:style>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-1-row-filter">
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
                     rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});" renderedIfEmpty="false"
                     binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{!pageBeanName.singleSelection}">
                <f:facet name="header">
                    <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                        <f:attribute name="checkAll" value="true"/>
                        <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                        <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"
                                     oncomplete="setAll('checkAll', 'chk');" reRender="divEditLookup,OperationBar"/>
                    </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                                 oncomplete="checkCheckAll('chk');" reRender="divEditLookup,OperationBar"/>
                </t:selectBooleanCheckbox>
            </t:column>
            <t:column id="radio_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{pageBeanName.singleSelection}">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk2" value="#{pageBeanName.selectedRadio}"
                                  onkeyup="toggleRadioKeyUp(this);" onmousedown="toggleRadio(this);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="holidayDesc_column" sortable="false" width="50%">
                <f:facet name="header">
                    <t:commandLink id="sort_holidayDesc" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.hol_holiday_desc}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='holidayDesc') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='holidayDesc') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_holidayDesc" value="#{list.holidayDesc}"/>
            </t:column>
            <t:column id="fromDate_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_fromDate" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.hol_from_date}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_fromDate" value="#{list.fromDate}">
                    <f:converter converterId="SqlDateConverter"/>
                </h:outputText>
            </t:column>
            <t:column id="untilDate_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_untilDate" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.hol_until_date}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_untilDate" value="#{list.untilDate}">
                    <f:converter converterId="SqlDateConverter"/>
                </h:outputText>
            </t:column>
            <t:column id="status_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_status" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.hol_status}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ?
                 '/app/media/images/ascending-arrow.gif' : ''}" border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ?
                 '/app/media/images/descending-arrow.gif' :''}" border="0"/>
                    </t:commandLink>
                </f:facet>
                 <t:graphicImage value="/app/media/images/DataGrid_Icon_ok.gif" rendered="#{list.status== 1 }"
                                onmouseover="doTooltip(event,'#{resourcesBundle.valid_status }')" onmouseout="hideTip()"
                                border="0"/>
                <t:graphicImage value="/app/media/images/DataGrid_Icon_not.gif" rendered="#{list.status== 0 }"
                                onmouseover="doTooltip(event,'#{resourcesBundle.invalid_status}')" onmouseout="hideTip()"
                                border="0"/>
                          </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<t:saveState value="#{pageBeanName.singleSelection}"/>
<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.highlightedDTOS}"/>
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.sortAscending}"/>
<!-- Start Paging -->
<!--<t:saveState value="#{pageBeanName.currentPageIndex}"/>
<t:saveState value="#{pageBeanName.oldPageIndex}"/>
<t:saveState value="#{pageBeanName.sorting}"/>
<t:saveState value="#{pageBeanName.usingPaging}"/>
<t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
<t:saveState value="#{pageBeanName.resettedPageIndex}"/>
<t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
<t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>-->
<!-- End Paging -->
<script type="text/javascript">
  function FireButton(buttonId) {
      var button = document.getElementById(buttonId);
      button.click();

  }
</script>
