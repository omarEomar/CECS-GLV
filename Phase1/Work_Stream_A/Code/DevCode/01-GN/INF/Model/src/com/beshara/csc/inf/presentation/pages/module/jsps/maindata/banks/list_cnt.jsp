<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel">
        <t:dataTable id="dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" width="100%" align="center"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                     sortAscending="#{pageBeanName.ascending}">
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
            <t:column id="ar_name_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_name" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.bank_name}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}"/>
            </t:column>
            <%-- <t:column id="eng_name" sortable="false" width="15%"> <f:facet name="header"> <t:commandLink
                 id="sort_engName" forceId="true" styleClass="headerLink" value="#{resourcesBundle.bank_eng_name}"
                 actionListener="#{pageBeanName.sortDataTable}"> <t:graphicImage
                 value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='engName') ?
                 '/app/media/images/ascending-arrow.gif' : ''}" border="0"/> <t:graphicImage
                 value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='engName') ?
                 '/app/media/images/descending-arrow.gif' :''}" border="0"/> </t:commandLink> </f:facet> <t:outputText
                 id="content_engName" value="#{list.engName}" > </t:outputText> </t:column>--%>
            <t:column id="min_name" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_minName" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.min_code}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='minName') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='minName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_min_name" value="#{list.minName}"></h:outputText>
            </t:column>
            <t:column id="hot_line" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_hotline" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.hot_line}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hotline') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='hotline') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_hotLine" value="#{list.hotline}"></h:outputText>
            </t:column>
            <t:column id="email" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_email" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.b_email}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='email') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='email') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_email" value="#{list.email}"></h:outputText>
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