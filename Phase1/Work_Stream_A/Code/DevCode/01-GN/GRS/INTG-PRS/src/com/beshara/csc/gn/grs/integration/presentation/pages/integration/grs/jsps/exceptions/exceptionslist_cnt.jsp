<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.sortAscending}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.selectedRadio}"/>


<t:panelGrid id="dataT_data_panel_Grid" border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="myDataTableId" style="display:block;overflow:auto;height:215px;">
        <t:messages showDetail="true"/>
  
        <t:dataTable id="dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ?  pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                <%--<f:facet name="header">
                </f:facet>--%>
                <%--<t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>--%>
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
                            oncomplete="checkCheckAll('chk');" 
                             reRender="divDeleteAlert,OperationBar,cancelValidityDiv"/>
            </t:selectBooleanCheckbox>
            </t:column>
            <%--<t:column id="code_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_code" forceId="true" styleClass="headerLink"
                                   value="#{globalResources.Code}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                --%><%--<h:outputText id="content_code" value="#{list.civilId}"/>--%><%--
            </t:column>--%>
            <t:column id="civilNumber_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_infKwtCitizenResidntDTO-civilId" forceId="true" styleClass="headerLink" value="#{grsIntgResources.exceptionCivilNumber}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='infKwtCitizenResidntDTO-civilId') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='infKwtCitizenResidntDTO-civilId') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="civilNumber" value="#{list.infKwtCitizenResidntDTO.civilId}"/>
            </t:column>
            
            <t:column id="name_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_infKwtCitizenResidntDTO-name" forceId="true" styleClass="headerLink" value="#{grsIntgResources.exceptionCivilName}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='infKwtCitizenResidntDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='infKwtCitizenResidntDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="CivilName" value="#{list.infKwtCitizenResidntDTO.name}"/>
            </t:column>
            <t:column id="exceptionReason_column" sortable="false" width="25%">
                <f:facet name="header">
                    <t:commandLink id="sort_excptionReasonsDTO-name" forceId="true" styleClass="headerLink" value="#{grsIntgResources.exeptionReason}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='excptionReasonsDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='excptionReasonsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_name" value="#{list.excptionReasonsDTO.name}"/>
            </t:column>
            
             <t:column id="fromDate_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_fromDate" forceId="true" styleClass="headerLink" value="#{grsIntgResources.from}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="fromDate" value="#{list.fromDate}">
                    <f:converter converterId="SqlDateConverter"/>
                </h:outputText>
            </t:column>
            
            <t:column id="exceptionTo_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_untilDate" forceId="true" styleClass="headerLink" value="#{grsIntgResources.to}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="untilDate" value="#{list.untilDate}">
                 <f:converter converterId="SqlDateConverter"/>
                </h:outputText>
            </t:column>
            
            <t:column id="exceptionType_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_exceptionType" forceId="true" styleClass="headerLink" value="#{grsIntgResources.exceptionType}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='exceptionType') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='exceptionType') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:graphicImage url="#{list.exceptionType == pageBeanName.activeFlag ? '/app/media/images/DataGrid_Icon_green_flag.gif' : '/app/media/images/DataGrid_Icon_red_flag.gif'}"
                            onmouseover="doTooltip(event,'#{list.exceptionType == pageBeanName.activeFlag ? grsIntgResources.exceptionTypeValid : grsIntgResources.exceptionTypeInValid}')"
                            onmouseout="hideTip()" border="0"/>
            <h:inputHidden value="#{list.exceptionType}"/>
            </t:column>
            
            <t:column id="status_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_status" forceId="true" styleClass="headerLink" value="#{grsIntgResources.status}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:graphicImage url="#{list.status == pageBeanName.activeFlag ? '/app/media/images/DataGrid_Icon_green_flag.gif' : '/app/media/images/DataGrid_Icon_red_flag.gif'}"
                            onmouseover="doTooltip(event,'#{list.status == pageBeanName.activeFlag ? grsIntgResources.Parameter_Status_Valid : (list.status == pageBeanName.freezedFlag ? grsIntgResources.Parameter_Status_Freezed : (list.status == pageBeanName.pendingFlag ? grsIntgResources.Parameter_Status_Bending : grsIntgResources.Parameter_Status_InValid))}')"
                            onmouseout="hideTip()" border="0"/>
            <h:inputHidden value="#{list.status}"/>
                <%--<h:outputText id="content_name" value="#{list.name}"/>--%>
            </t:column>
            
           
            
            <%--<t:column id="jobFreez_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_jobFreez" forceId="true" styleClass="headerLink"
                                   value="#{jobResources.Main_Data_Job_Freezz_Label}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobFreez') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobFreez') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                                        
                 <t:graphicImage id="greenFlag"  value="/app/media/images/DataGrid_Icon_green_flag.gif" border="0" onmouseout="hideTip()" 
                                    onmouseover="doTooltip(event,' #{jobResources.lable_main_Data_Job_Freez_Type_first}')" rendered="#{list.jobFreez==0}"/>
                                    
                <t:graphicImage id="redFlag" value="/app/media/images/DataGrid_Icon_red_flag.gif" border="0" onmouseout="hideTip()" 
                                    onmouseover="doTooltip(event,' #{jobResources.lable_main_Data_Job_Freez_Type_third}')" rendered="#{list.jobFreez==2}"/>
            </t:column>--%>
        </t:dataTable>
        
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        
        
        <f:verbatim>
            <script language="javascript" type="text/javascript">
            //   adjustDataTable('myDataTableId');
            </script>
        </f:verbatim>
    </t:panelGroup>
</t:panelGrid>
