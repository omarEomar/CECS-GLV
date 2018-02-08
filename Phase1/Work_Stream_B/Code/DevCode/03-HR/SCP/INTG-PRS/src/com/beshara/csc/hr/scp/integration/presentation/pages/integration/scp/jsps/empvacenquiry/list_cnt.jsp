<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">

    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-5-row-filter-and-collapse-button" style="height: 120px ! important;">
       <%-- <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}"
                        status="notdefined" reRender="divDeleteAlert,OperationBar,divEditLookup,monsalGroupId,headerMonSalGroup"
                        oncomplete="javascript:FireButton('editButton');">
            <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
            <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
            <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
            <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
        </a4j:jsFunction>--%>
        <t:dataTable id="dataT_data" var="list" 
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                     sortAscending="#{pageBeanName.ascending}">
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{!pageBeanName.singleSelection}">
                <f:facet name="header">
                    <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                        <f:attribute name="checkAll" value="true"/>
                        <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                        <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"
                                     oncomplete="setAll('checkAll', 'chk');"
                                     reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                    </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                                 oncomplete="checkCheckAll('chk');"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectBooleanCheckbox>
            </t:column>
            <t:column id="radio_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{pageBeanName.singleSelection}">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar,monsalGroupId,headerMonSalGroup"/>
                </t:selectOneRadio>
            </t:column>
          
            <t:column id="code_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_code-empvacCode" forceId="true" styleClass="headerLink" value="#{scpIntgResources.vac_code}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-empvacCode') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-empvacCode') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.empvacCode}"/>
            </t:column>
            
            
            <t:column id="name_column" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_vacVacationTypesDTO-name" forceId="true" styleClass="headerLink" value="#{scpIntgResources.vac_type}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacVacationTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacVacationTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:inputText id="content_name" value="#{list.vacVacationTypesDTO.name}" readonly="true" styleClass="inputTextForDataTable"/>
            </t:column>
            
             <t:column id="vac_emp_from_date" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_fromDate" forceId="true" styleClass="headerLink" value="#{scpIntgResources.from_date}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="fromDate" value="#{list.fromDate}" converter="SqlDateConverter"/>
            </t:column>
            
              <t:column id="vac_emp_to_date" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_untilDate" forceId="true" styleClass="headerLink" value="#{scpIntgResources.until_date}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="untilDate" value="#{list.untilDate}" converter="SqlDateConverter"/>
            </t:column>
            
            <t:column id="vac_emp_status" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_statusCode" forceId="true" styleClass="headerLink" value="#{scpIntgResources.add_method}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='statusCode') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='statusCode') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="statusCode" value="#{list.statusCode==-1? scpIntgResources.financialSystem : scpIntgResources.managementSystem}"/>
            </t:column>

            <%--//**** start story CSC-17409 A.Nour--%>
             <t:column id="duration_calc_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:outputText value="#{scpIntgResources.calcDuration}" styleClass="headerLink"/>
                </f:facet>
                <t:commandButton id="duration_calcBTN" value="#{globalResources.Available}"
                       styleClass="cssButtonSmall" action="#{pageBeanName.calcDuration}"
                       disabled="#{pageBeanName.serviceEnded || list.salEmpMonSalariesDTO == null}" />
            </t:column>
            <%--//**** End story CSC-17409 A.Nour--%>

        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
       
   
    </t:panelGroup>
    <t:panelGroup colspan="4" forceId="true" id="headerMonSalGroup">
       <t:panelGroup colspan="4" style="background-color:#FFFFFF" rendered="#{!empty pageBeanName.salEmpMonList}">
       
        <htm:table width="100%">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
                </htm:td>
                <htm:td styleClass="group_title">
                    &nbsp; 
                    <t:outputLabel value="#{scpIntgResources.mon_sal_group_title}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
             
            <htm:tr>
                <htm:td colspan="2" height="1">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%"
                             height="1"/>
                </htm:td>
            </htm:tr>
        </htm:table>
    </t:panelGroup>
    </t:panelGroup>
    <t:panelGroup forceId="true" id="monsalGroupId" styleClass="dataT-With-5-row-filter-and-collapse-button" style="height: 161px ! important;">

             <t:dataTable  forceId="true" id="monSalDatatable" var="list" 
                             value="#{pageBeanName.salEmpMonList}" binding="#{pageBeanName.monSalDataTable}"
                             forceIdIndexFormula="#{list.code.key}" footerClass="grid-footer"
                             headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered" width="100%" align="top"
                             dir="#{shared_util.pageDirection}" styleClass="grid-footer" renderedIfEmpty="false">
            
            <t:column id="codex_column" sortable="false" width="10%">
                <f:facet name="header">
                  <t:outputText  value="#{scpIntgResources.salary_month}" styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="xcontent_code" value="#{list.salaryMonth} / #{list.yearCode}" />
            </t:column>
            
            
            <t:column id="namex_column" sortable="false" width="10%">
                <f:facet name="header">
                     <t:outputText value="#{scpIntgResources.from_date}" styleClass="headerLink"/>
                </f:facet>
               <h:outputText id="xfromDate" value="#{list.fromDate}" converter="SqlDateConverter"/>
            </t:column>
            
             <t:column id="vacx_emp_from_date" sortable="false" width="10%">
                <f:facet name="header">
                    <t:outputText value="#{scpIntgResources.until_date}" styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="xfromDate" value="#{list.untilDate}" converter="SqlDateConverter"/>
            </t:column>
            
              <t:column id="vacx_emp_to_date" sortable="false" width="10%">
                <f:facet name="header">
                    <t:outputText value="#{scpIntgResources.payment_type}" styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="xuntilDate" value="#{list.paymentStatusDTO.name}" />
            </t:column>
            <%--//**** start story CSC-17409 A.Nour--%>
              <t:column id="pay_method_col" sortable="false" width="10%">
                <f:facet name="header">
                    <t:outputText value="#{scpIntgResources.emp_vac_method}" styleClass="headerLink"/>
                </f:facet>
                <h:outputText id="pay_method" value="#{list.paymentMethodsDTO.name}" />
            </t:column>
            <%--//**** End story CSC-17409 A.Nour--%>
            
             <t:column id="details_column" sortable="false" width="10%">
                <f:facet name="header">
                    <%--//**** start story CSC-17409 A.Nour--%>
                    <t:outputText value="#{scpIntgResources.display}" styleClass="headerLink"/>
                    <%--//**** End story CSC-17409 A.Nour--%>
                </f:facet>
                <a4j:commandButton id="DetailBTN" value="#{globalResources.Available}"
                       styleClass="cssButtonSmall" action="#{pageBeanName.fillPaySlipData}"
                       oncomplete="javascript:changeVisibilityDiv(window.blocker,window.customDiv1);"
                       reRender="payslipDataPanel,customDiv2,payslipBtnPanel"/>
            </t:column>
            <%--//**** start story CSC-17409 A.Nour--%>
             <%--<t:column id="duration_calc_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:outputText value="#{scpIntgResources.calcDuration}" styleClass="headerLink"/>
                </f:facet>
                <t:commandButton id="duration_calcBTN" value="#{globalResources.Available}"
                       styleClass="cssButtonSmall" action="#{pageBeanName.calcDuration}"
                       disabled="#{list.paymentMethodsDTO.code.key != 5 || !(list.paymentStatusDTO.code.key == 7 || list.paymentStatusDTO.code.key == 17)}"
                       />
            </t:column>--%>
            <%--//**** End story CSC-17409 A.Nour--%>
        </t:dataTable>
       
       </t:panelGroup>
</t:panelGrid>
 
<!-- added by nora to enable single selection -->
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

<t:saveState  value="#{pageBeanName.salEmpMonList}"/>
<!-- End Paging -->
<script type="text/javascript">
  //foucsAddbuttonOnList();

  function foucsAddbuttonOnList() {
      if (document.getElementById('addButton') != null) {
          document.getElementById('addButton').focus();
      }
  }

  function FireButton(buttonId) {
      var button = document.getElementById(buttonId);
      button.click();

  }
</script>