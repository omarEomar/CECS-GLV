<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.dataT-With-4-row-filter-wives{
    display: block;
    height: 275px !important;
    overflow: auto;    
}
.lookupEditDivClass{width: 620px !important;}
</htm:style>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-4-row-filter-wives">
        <t:dataTable id="dataT_data" var="list"
                     value="#{detailBeanName.usingPaging ? detailBeanName.pagingBean.myPagedDataModel : detailBeanName.myTableData}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{detailBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowStyleClass="#{ list.childStopStatus==null ? null : list.childStopStatus != 0 ? 'standardTable_RowHighlighted_GRS_Rejected' : detailBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{detailBeanName.sortColumn}" sortAscending="#{detailBeanName.ascending}">
        
            <%--t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{!detailBeanName.singleSelection}">
                <f:facet name="header">
                   
                </f:facet>
              
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" onmousedown="toggleRadio(this);"
                                  onkeyup="toggleRadioKeyUpVersionTwo(this,event);" onfocus="changeBG(this)"
                                  onkeydown="onKeyDownRadio(event,this,'sort_status','myForm:resetData_btn_id',#{index},#{shared_util.noOfTableRows});"
                                  value="#{pageBeanName.selectedRadio}">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedRadioButton}"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
            </t:column--%>
            
            <t:column id="MER_RAISE_CIVILID" sortable="false" width="5%">
                <f:facet name="header">
                    <t:commandLink id="sort_code" forceId="true" styleClass="standardTable_Header2"
                                   value="#{resourcesBundle.MER_RAISE_CIVILID}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                    <%-- <t:commandSortHeader id="codeColumn" columnName="code" arrow="false"
                         styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending"> <t:graphicImage
                         id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                         </f:facet> <f:facet name="descending"> <t:graphicImage id="descendingArrow"
                         value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                         <h:outputText id="header_code" value="#{resourcesBundle.MER_RAISE_CIVILID}"/>
                         </t:commandSortHeader>--%>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.keys[0]}"/>
            </t:column>
            <t:column id="MER_RAISE_CLMN_NAME" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_kwtCitizensResidentsDTO-firstName" forceId="true"
                                   styleClass="standardTable_Header2" value="#{resourcesBundle.MER_RAISE_CLMN_NAME}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-firstName') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-firstName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                    <%-- <t:commandSortHeader id="nameColumn" columnName="kwtCitizensResidentsDTO-fullName"
                         arrow="false" styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending">
                         <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                         rendered="true" border="0"/> </f:facet> <f:facet name="descending"> <t:graphicImage
                         id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true"
                         border="0"/> </f:facet> <h:outputText id="header_name"
                         value="#{resourcesBundle.MER_RAISE_CLMN_NAME}"/> </t:commandSortHeader>--%>
                </f:facet>
                <h:outputText id="content_name" value="#{list.kwtCitizensResidentsDTO.firstName}"/>
            </t:column>
            <t:column id="MER_RAISE_TYPE" sortable="false" width="%5">
                <f:facet name="header">
                    <t:commandLink id="sort_kwtCitizensResidentsDTO-genderTypesDTO-name" forceId="true"
                                   styleClass="standardTable_Header2" value="#{resourcesBundle.MER_RAISE_TYPE}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-genderTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-genderTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                    <%-- <t:commandSortHeader id="MER_RAISE_TYPE_sort"
                         columnName="kwtCitizensResidentsDTO-genderTypesDTO-name" arrow="false"
                         styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending"> <t:graphicImage
                         id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                         </f:facet> <f:facet name="descending"> <t:graphicImage id="descendingArrow"
                         value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                         <h:outputText id="MER_RAISE_TYPE_TITLE" value="#{resourcesBundle.MER_RAISE_TYPE}"/>
                         </t:commandSortHeader>--%>
                </f:facet>
                <h:outputText id="MER_RAISE_TYPE_CONTENT" value="#{list.kwtCitizensResidentsDTO.genderTypesDTO.name}"/>
            </t:column>
            <t:column id="MER_RAISE_CLMN_BIRTH_DATE" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_kwtCitizensResidentsDTO-birthDate" forceId="true"
                                   styleClass="standardTable_Header2"
                                   value="#{resourcesBundle.MER_RAISE_CLMN_BIRTH_DATE}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-birthDate') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-birthDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                    <%-- <t:commandSortHeader id="MER_RAISE_CLMN_BIRTH_DATE_sort"
                         columnName="kwtCitizensResidentsDTO-birthDate" arrow="false" styleClass="standardTable_Header2"
                         immediate="true"> <f:facet name="ascending"> <t:graphicImage id="ascendingArrow"
                         value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/> </f:facet> <f:facet
                         name="descending"> <t:graphicImage id="descendingArrow"
                         value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                         <h:outputText id="MER_RAISE_CLMN_BIRTH_DATE_TITLE"
                         value="#{resourcesBundle.MER_RAISE_CLMN_BIRTH_DATE}"/> </t:commandSortHeader>--%>
                </f:facet>
                <h:outputText id="MER_RAISE_CLMN_BIRTH_DATE_CONTENT" value="#{list.kwtCitizensResidentsDTO.birthDate}"
                              converter="TimeStampConverter"/>
            </t:column>
            <t:column id="MER_RAISE_CLMN_ALLOWANCE" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_code-allowanceDate" forceId="true" styleClass="standardTable_Header2"
                                   value="#{resourcesBundle.MER_RAISE_CLMN_ALLOWANCE}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-allowanceDate') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-allowanceDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                    <%-- <t:commandSortHeader id="MER_RAISE_CLMN_ALLOWANCE_sort" columnName="code-allowanceDate"
                         arrow="false" styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending">
                         <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                         rendered="true" border="0"/> </f:facet> <f:facet name="descending"> <t:graphicImage
                         id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true"
                         border="0"/> </f:facet> <h:outputText id="MER_RAISE_ALLOWANCE"
                         value="#{resourcesBundle.MER_RAISE_CLMN_ALLOWANCE}"/> </t:commandSortHeader>--%>
                </f:facet>
                <h:outputText id="MER_RAISE_CLMN_ALLOWANCE_CONTENT" value="#{list.code.allowanceDate}"
                              converter="SqlDateConverter"/>
            </t:column>
            <t:column id="MER_RAISE_STATUS" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_kwtCitizensResidentsDTO-maritalStatusDTO-name" forceId="true"
                                   styleClass="standardTable_Header2" value="#{resourcesBundle.MER_RAISE_STATUS}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-maritalStatusDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-maritalStatusDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                    <%-- <t:commandSortHeader id="MER_RAISE_STATUS_sort"
                         columnName="kwtCitizensResidentsDTO-maritalStatusDTO-name" arrow="false"
                         styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending"> <t:graphicImage
                         id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                         </f:facet> <f:facet name="descending"> <t:graphicImage id="descendingArrow"
                         value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                         <h:outputText id="MER_RAISE_STATUS_TITLE" value="#{resourcesBundle.MER_RAISE_STATUS}"/>
                         </t:commandSortHeader>--%>
                </f:facet>
                <h:outputText id="MER_RAISE_STATUS_CONTENT"
                              value="#{list.kwtCitizensResidentsDTO.maritalStatusDTO.name}"/>
            </t:column>
            <%--<t:column id="MER_RAISE_CLMN_SUSPEND_ALLOWANCE" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_stopDate" forceId="true" styleClass="standardTable_Header2"
                                   value="#{resourcesBundle.MER_RAISE_CLMN_SUSPEND_ALLOWANCE}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='stopDate') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='stopDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                    --%><%-- <t:commandSortHeader id="MER_RAISE_CLMN_SUSPEND_ALLOWANCE_sort" columnName="stopDate"
                         arrow="false" styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending">
                         <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                         rendered="true" border="0"/> </f:facet> <f:facet name="descending"> <t:graphicImage
                         id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true"
                         border="0"/> </f:facet> <h:outputText id="MER_RAISE_SUSPEND_ALLOWANCE"
                         value="#{resourcesBundle.MER_RAISE_CLMN_SUSPEND_ALLOWANCE}"/> </t:commandSortHeader>--%><%--
                </f:facet>
                <h:outputText id="MER_RAISE_CLMN_SUSPEND_ALLOWANCE_CONTENT" value="#{list.stopDate}"
                              converter="SqlDateConverter"/>
            </t:column>--%>
            <%--<t:column id="MER_RAISE_CLMN_SUSPEND_REASON" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_salStopCrReasonsDTO-name" forceId="true" styleClass="standardTable_Header2"
                                   value="#{resourcesBundle.MER_RAISE_CLMN_SUSPEND_REASON}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salStopCrReasonsDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salStopCrReasonsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                    --%><%-- <t:commandSortHeader id="MER_RAISE_CLMN_SUSPEND_REASON_sort"
                         columnName="salStopCrReasonsDTO-name" arrow="false" styleClass="standardTable_Header2"
                         immediate="true"> <f:facet name="ascending"> <t:graphicImage id="ascendingArrow"
                         value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/> </f:facet> <f:facet
                         name="descending"> <t:graphicImage id="descendingArrow"
                         value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                         <h:outputText id="MER_RAISE_SUSPEND_REASON"
                         value="#{resourcesBundle.MER_RAISE_CLMN_SUSPEND_REASON}"/> </t:commandSortHeader>--%><%--
                </f:facet>
                <h:outputText id="MER_RAISE_CLMN_SUSPEND_REASON_content" value="#{list.salStopReasonsDTO.name}"/>
            </t:column>--%>
            <t:column id="MER_RAISE_CLMN_HEALTH_STATE" sortable="false" width="35%">
                <f:facet name="header">
                    <t:commandLink id="sort_kwtCitizensResidentsDTO-handicapStatusDTO-name" forceId="true"
                                   styleClass="standardTable_Header2"
                                   value="#{resourcesBundle.MER_RAISE_CLMN_HEALTH_STATE}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-handicapStatusDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='kwtCitizensResidentsDTO-handicapStatusDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                    <%-- <t:commandSortHeader id="MER_RAISE_CLMN_HEALTH_STATE_sort"
                         columnName="kwtCitizensResidentsDTO-handicapStatusDTO-name" arrow="false"
                         styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending"> <t:graphicImage
                         id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                         </f:facet> <f:facet name="descending"> <t:graphicImage id="descendingArrow"
                         value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                         <h:outputText id="MER_RAISE_CLMN_HEALTH_STATE_TITLE"
                         value="#{resourcesBundle.MER_RAISE_CLMN_HEALTH_STATE}"/> </t:commandSortHeader>--%>
                </f:facet>
                <h:outputText id="MER_RAISE_CLMN_HEALTH_STATE_CONTENT"
                              value="#{list.kwtCitizensResidentsDTO.handicapStatusDTO.name}"/>
            </t:column>
            
            <t:column id="CHILD_MOTHER" sortable="false" width="15%" rendered="#{pageBeanName.selMother==pageBeanName.virtualConstValue}">
                <f:facet name="header">
                    <t:commandLink id="sort_motherDTO-name" forceId="true"
                                   styleClass="standardTable_Header2"
                                   value="#{resourcesBundle.mer_mother_name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='motherDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='motherDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                    <%-- <t:commandSortHeader id="MER_RAISE_CLMN_HEALTH_STATE_sort"
                         columnName="kwtCitizensResidentsDTO-handicapStatusDTO-name" arrow="false"
                         styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending"> <t:graphicImage
                         id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                         </f:facet> <f:facet name="descending"> <t:graphicImage id="descendingArrow"
                         value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                         <h:outputText id="MER_RAISE_CLMN_HEALTH_STATE_TITLE"
                         value="#{resourcesBundle.MER_RAISE_CLMN_HEALTH_STATE}"/> </t:commandSortHeader>--%>
                </f:facet>
                <h:outputText id="CHILD_MOTHER_NAME"
                              value="#{list.motherDTO.name}"/>
            </t:column>            
            <t:column id="status_column" sortable="false" width="20%">
                <f:facet name="header">
                    <%-- <t:commandSortHeader id="MER_RAISE_CLMN_status_sort" columnName="status" arrow="false"
                         styleClass="standardTable_Header2" immediate="true"> <f:facet name="ascending"> <t:graphicImage
                         id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                         </f:facet> <f:facet name="descending"> <t:graphicImage id="descendingArrow"
                         value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/> </f:facet>
                         <h:outputText id="MER_RAISE_CLMN_status_TITLE" value="#{globalResources.status}"/>
                         </t:commandSortHeader>--%>
                    <t:commandLink id="sort_status" forceId="true" styleClass="standardTable_Header2"
                                   value="#{globalResources.status}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:graphicImage id="graph1" border="0" value="/app/media/images/green-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{resourcesBundle.valid}')"
                                rendered="#{list.status == 1}"></t:graphicImage>
                <t:graphicImage id="graph2" border="0" value="/app/media/images/red-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{resourcesBundle.notValid}<br> #{resourcesBundle.stop_stmt} #{list.stopDate} <br> #{resourcesBundle.for_rason} #{list.salStopReasonsDTO.name}')"
                                rendered="#{list.status == 0}"/>
            </t:column>
            <%-- t:column id="show_child_details" sortable="true" width="10%"> <f:facet name="header"><h:outputText
                 value="#{resourcesBundle.MER_RAISE_CLMN_child_details}"/></f:facet> <t:commandButton type="button"
                 id="show_child_details_link" value="#{resourcesBundle.MER_RAISE_CLMN_view}"> <a4j:support
                 event="onclick" actionListener="#{detailBeanName.showDetails}" reRender="customDiv2"
                 oncomplete="javascript:changeVisibilityDiv(window.blocker,window.customDiv2);"/> </t:commandButton>
                 </t:column--%>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ detailBeanName.listSize == 0 }" align="center" styleClass="msg">
            <h:outputText value="#{resourcesBundle.addWivesValidationNotDefined}"
                          rendered="#{detailBeanName.civilExist && detailBeanName.employeesDTO.citizensResidentsDTO.genderTypesDTO.gentypeCode == 3}"/>
            <%-- t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/--%>
            <h:outputText value="#{globalResources.global_noTableResults}"
                          rendered="#{!detailBeanName.civilExist || detailBeanName.employeesDTO.citizensResidentsDTO.genderTypesDTO.gentypeCode != 3}"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<script type="text/javascript">
  foucsAddbuttonOnList();

  function foucsAddbuttonOnList() {
      if (document.getElementById('addButton') != null) {
          document.getElementById('addButton').focus();
      }
  }
</script>
