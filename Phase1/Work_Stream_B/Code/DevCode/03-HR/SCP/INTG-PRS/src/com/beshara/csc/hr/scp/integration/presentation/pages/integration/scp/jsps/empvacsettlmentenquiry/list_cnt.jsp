<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%--//**** Start story CSC-17922 A.Nour--%>
<htm:style>
.dataT-With-7-row-filter-and-collapse {
    height: 123px !important;
}

</htm:style>

<t:panelGroup style="height:100%;background-color:#ffffff;">
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
                    <%--<h:graphicImage url="/app/media/images/op_arrow.jpg" width="9" height="9"/>--%>
                </htm:td>
                <htm:td styleClass="group_title">
                    <t:outputLabel value="#{scpIntegrationBundle.retroactive_vac_cashReplacement_sett}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
            <htm:tr>
                <htm:td colspan="2" height="3" valign="top">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/>
                    <%--<h:graphicImage url="/app/media/images/seprator_group.jpg"  width="100%" height="1"/>--%>
                </htm:td>
            </htm:tr>
            
        </htm:table>
    </t:panelGroup>
    <t:panelGroup forceId="true" id="vac_dataT_data_panel" styleClass="dataT-With-5-row-filter-and-collapse-button" style="height: 100px ! important;">
       <%-- <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}"
                        status="notdefined" reRender="divDeleteAlert,OperationBar,divEditLookup,monsalGroupId,headerMonSalGroup"
                        oncomplete="javascript:FireButton('editButton');">
            <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
            <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
            <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
            <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
        </a4j:jsFunction>--%>
        <t:dataTable id="vac_dataT_data" var="list" 
                     value="#{pageBeanName.vacList}"
                     
                     forceIdIndexFormula="#{list.code.key}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.vacTable}"
                      footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                     sortAscending="#{pageBeanName.ascending}"
                           rowOnMouseOver="javascript:doTooltip(event,'#{scpIntegrationBundle.vacFromDate}:#{list.vacEmployeeVacationsDTO.fromDate},#{scpIntegrationBundle.vacToDate}:#{list.vacEmployeeVacationsDTO.untilDate}');"
                     rowOnMouseOut="hideTip();"
                     
                     >
                     <%--rowOnMouseOver="javascript:addRowHighlight('myForm:vac_dataT_data');"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"--%>
            <t:column id="radio_column" styleClass="standardTable_Header3" width="5%"
                      >
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk2" value="#{pageBeanName.vacSelectedRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedVacRadioButton}"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar,settPnl"/>
                </t:selectOneRadio>
            </t:column>
          
            <t:column id="code_column" sortable="false" width="10%">
                <f:facet name="header">
                    <h:outputText id="sort_code-key" value="#{scpIntegrationBundle.sett_code}" styleClass="headerLink" />
                    <%--<t:commandLink id="sort_code-key" forceId="true" styleClass="headerLink" value="#{scpIntegrationBundle.vac_code}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-empvacCode') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-empvacCode') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>--%>
                </f:facet>
                <h:outputText id="vac_sett_content_code" value="#{list.code.key}"/>
            </t:column>
            
            <t:column id="vac_code_column" sortable="false" width="10%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEmployeeVacationsDTO-code-empvacCode" value="#{scpIntegrationBundle.vac_code}" styleClass="headerLink" />
                    <%--<t:commandLink id="sort_vacEmployeeVacationsDTO-code-empvacCode" forceId="true" styleClass="headerLink" value="#{scpIntegrationBundle.vac_code}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacEmployeeVacationsDTO-code-empvacCode') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacEmployeeVacationsDTO-code-empvacCode') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>--%>
                </f:facet>
                <h:outputText id="vac_content_code" value="#{list.vacEmployeeVacationsDTO.code.empvacCode}" 
                                />
            </t:column>
            
            <t:column id="vac_name_column" sortable="false" width="20%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEmployeeVacationsDTO-vacVacationTypesDTO-name" value="#{scpIntegrationBundle.vac_type}" styleClass="headerLink" />
                    <%--<t:commandLink id="sort_vacEmployeeVacationsDTO-vacVacationTypesDTO-name" forceId="true" styleClass="headerLink" value="#{scpIntegrationBundle.vac_type}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacEmployeeVacationsDTO-vacVacationTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacEmployeeVacationsDTO-vacVacationTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>--%>
                </f:facet>
                <h:outputText  id="vac_content_name" value="#{list.vacEmployeeVacationsDTO.vacVacationTypesDTO.name}" styleClass="inputTextForDataTable"/>
            </t:column>
            
             <t:column id="vac_emp_from_date" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEmployeeVacationsDTO" value="#{scpIntegrationBundle.from_date}" styleClass="headerLink" />
                    <%--<t:commandLink id="sort_vacEmployeeVacationsDTO-fromDate" forceId="true" styleClass="headerLink" value="#{scpIntegrationBundle.from_date}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacEmployeeVacationsDTO-fromDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacEmployeeVacationsDTO-fromDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>--%>
                </f:facet>
                <h:outputText id="vac_fromDate"  value="#{list.newFromDate}" converter="TimeStampConverter" />
            </t:column>
            
              <t:column id="vac_emp_to_date" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="sort_vacEmployeeVacationsDTO" value="#{scpIntegrationBundle.until_date}" styleClass="headerLink" />
                    <%--<t:commandLink id="sort_vacEmployeeVacationsDTO-untilDate" forceId="true" styleClass="headerLink" value="#{scpIntegrationBundle.until_date}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacEmployeeVacationsDTO-untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacEmployeeVacationsDTO-untilDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>--%>
                </f:facet>
                <h:outputText id="vac_untilDate" value="#{list.newUntilDate}" converter="TimeStampConverter"/>
            </t:column>
            
      <t:column id="set_type_name_date" sortable="false" width="15%">
                <f:facet name="header">
                    <h:outputText id="sort_financialGuideName" value="#{scpIntegrationBundle.settlment_types}" styleClass="headerLink" />
                    <%--<t:commandLink id="sort_vacEmployeeVacationsDTO-untilDate" forceId="true" styleClass="headerLink" value="#{scpIntegrationBundle.until_date}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacEmployeeVacationsDTO-untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='vacEmployeeVacationsDTO-untilDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>--%>
                </f:facet>
                <h:outputText id="vac_set_type" value="#{list.financialGuideName}" />
            </t:column>


             <t:column id="duration_calc_column" sortable="false" width="10%"  rendered="#{!pageBeanName.comeFromRetroactiveSettDiv}">
                <f:facet name="header">
                    <t:outputText value="#{globalResources.DeleteButton}" styleClass="headerLink"/>
                </f:facet>
                <t:commandButton id="sett_delBTN" value="#{globalResources.DeleteButton}"
                       styleClass="cssButtonSmall" action="#{pageBeanName.removeAllVacSett}"
                       disabled="#{list.vacEmployeeVacationsDTO.code == null}" />
            </t:column>
            

        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.vacListSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
   
    </t:panelGroup>
<%--//**** End story CSC-17922 A.Nour--%>


<t:panelGrid id="settPnl" forceId="true"  border="0" cellpadding="0" cellspacing="0" width="100%">
<t:panelGroup style="height:100%;background-color:#ffffff;">
        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
                    <%--<h:graphicImage url="/app/media/images/op_arrow.jpg" width="9" height="9"/>--%>
                </htm:td>
                <htm:td styleClass="group_title">
                    <t:outputLabel value="#{scpIntegrationBundle.retroactive_vac_cashReplacement_sett_details}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
            <htm:tr>
                <htm:td colspan="2" height="3" valign="top">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/>
                    <%--<h:graphicImage url="/app/media/images/seprator_group.jpg"  width="100%" height="1"/>--%>
                </htm:td>
            </htm:tr>
            
        </htm:table>
    </t:panelGroup>

    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-7-row-filter-and-collapse">
        <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}"
                        status="notdefined" reRender="divDeleteAlert,OperationBar,divEditLookup"
                        oncomplete="javascript:FireButton('editButton');">
            <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
            <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
            <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
            <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
        </a4j:jsFunction>
        <t:dataTable id="dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}"  rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" 
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                     sortAscending="#{pageBeanName.ascending}" rows="300">
                     <%--rows="#{shared_util.noOfTableRows}" rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});"--%>
            
            
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{!pageBeanName.singleSelection && !pageBeanName.comeFromRetroactiveSettDiv}">
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
           
            <t:column id="guideCode_column" sortable="false" width="5%">
                <f:facet name="header">
                    <t:commandLink id="sort_salEmpSettelmentsDTO-guideCode" forceId="true" styleClass="headerLink" value="#{globalResources.Code}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-guideCode') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-guideCode') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_code" value="#{list.salEmpSettelmentsDTO.guideCode}"/>
            </t:column>
            
            <t:column id="name_column" sortable="false" width="25%">
                <f:facet name="header">
                    <t:commandLink id="sort_salEmpSettelmentsDTO-guideName" forceId="true" styleClass="headerLink" value="#{globalResources.name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-guideName') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-guideName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
         
                <h:inputText id="content_name"   onmouseover="doTooltip(event,' #{list.salEmpSettelmentsDTO.guideName}')"  onmouseout="hideTip()" value="#{list.salEmpSettelmentsDTO.guideName}" readonly="true" styleClass="inputTextForDataTable"/>
             
            </t:column>
            
             <t:column id="settlment_value" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_value" forceId="true" styleClass="headerLink" value="#{scpIntegrationBundle.settlment_value}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='value') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='value') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
               <h:outputText id="total_value" value="#{list.value}" converter="DoubleThreeDigitConverter"/>
            </t:column>
            
           <%--<t:column id="vac_emp_from_date" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_salEmpSettelmentsDTO-vacEmployeeVacationsDTO-fromDate" forceId="true" styleClass="headerLink" value="#{scpIntegrationBundle.vac_emp_from_date}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-vacEmployeeVacationsDTO-fromDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-vacEmployeeVacationsDTO-fromDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="fromDate" value="#{list.salEmpSettelmentsDTO.vacEmployeeVacationsDTO.fromDate}" converter="SqlDateConverter"/>
            </t:column>--%>
            
              <%--<t:column id="vac_emp_to_date" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_salEmpSettelmentsDTO-vacEmployeeVacationsDTO-untilDate" forceId="true" styleClass="headerLink" value="#{scpIntegrationBundle.vac_emp_to_date}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-vacEmployeeVacationsDTO-untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-vacEmployeeVacationsDTO-untilDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="untilDate" value="#{list.salEmpSettelmentsDTO.vacEmployeeVacationsDTO.untilDate}" converter="SqlDateConverter"/>
            </t:column>--%>
               
             <%--<t:column id="emp_vac_type" sortable="false" width="30%">
                <f:facet name="header">
                    <t:commandLink id="sort_salEmpSettelmentsDTO-vacEmployeeVacationsDTO-vacVacationTypesDTO-name" forceId="true" styleClass="headerLink" value="#{scpIntegrationBundle.emp_vac_reason}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-vacEmployeeVacationsDTO-vacVacationTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-vacEmployeeVacationsDTO-vacVacationTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:inputText id="vacVacationType_name" value="#{list.salEmpSettelmentsDTO.vacEmployeeVacationsDTO.vacVacationTypesDTO.name}" readonly="true" styleClass="inputTextForDataTable"/>
            </t:column>--%>
            <t:column id="salEmpSettelmentsDTO-salSettlmentStatusDTO-code-stlstatusCode_column" sortable="false" width="5%">
                <f:facet name="header">
                    <t:commandLink id="sort_salEmpSettelmentsDTO-salSettlmentStatusDTO-code-stlstatusCode" forceId="true"
                                   styleClass="headerLink" value="#{scpIntegrationBundle.status}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-salSettlmentStatusDTO-code-stlstatusCode') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salEmpSettelmentsDTO-salSettlmentStatusDTO-code-stlstatusCode') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:graphicImage id="graph1" value="/app/media/images/green-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.name}')"
                                rendered="#{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.code.key=='1'}"
                                />
                <t:graphicImage id="grey" value="/app/media/images/grey-circle.gif" onmouseout="hideTip()"
                                rendered="#{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.code.key=='-2'}"
                                onmouseover="doTooltip(event,' #{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.name}')"/>
                                
                <t:graphicImage id="yellow"   rendered="#{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.code.key=='-1'}" value="/app/media/images/yellow-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.name}')"/>
                
                 <t:graphicImage id="red"   rendered="#{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.code.key=='0'}" value="/app/media/images/red-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{list.salEmpSettelmentsDTO.salSettlmentStatusDTO.name}')"/>
                
               
            </t:column>
        </t:dataTable>
        <%--<t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>--%>
      
       

    </t:panelGroup>
    <t:panelGrid forceId="true" id="TotalSettlmentValue" columns="2" width="98%" rowClasses="row01,row02" cellspacing="0"
             cellpadding="3" align="center">
      
        <h:outputText value="#{scpIntegrationBundle.total_settlment_val}" styleClass="divtext"  style="font-weight: bold; display: block; width: 26px;"/>
        <t:inputText disabled="true" styleClass="textbox"    converter="DoubleThreeDigitConverter"  value="#{pageBeanName.totalSettlmentValue}"  />
      
    </t:panelGrid>
    
</t:panelGrid>

<script type="text/javascript">
  foucsAddbuttonOnList();

  function foucsAddbuttonOnList() {
      if (document.getElementById('addButton') != null) {
          document.getElementById('addButton').focus();
      }
  }

  function FireButton(buttonId) {
      var button = document.getElementById(buttonId);
      button.click();

  }
  checkAllCheckBox();
</script>


