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
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="center" rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});"
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
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="code_column" sortable="false" width="20%">
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
            <t:column id="name_column" sortable="false" width="45%">
                <f:facet name="header">
                    <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}"/>
            </t:column>
            <%--<t:column id="handicapRatio_column" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_handicapRatio" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.INF_HandicapStatusDTO_handicapRatio}%"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='handicapRatio') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='handicapRatio') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_handicapRatio" value="#{list.handicapRatio}"/>
            </t:column>--%>
            <%-- <t:column id="handicap_Case" sortable="false" width="20%"> <f:facet name="header"> <t:commandLink
                 id="case" forceId="true" styleClass="headerLink" value="#{resourcesBundle.Case}"
                 actionListener="#{pageBeanName.sortDataTable}"> <t:graphicImage
                 value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='handicapRatio') ?
                 '/app/media/images/ascending-arrow.gif' : ''}" border="0"/> <t:graphicImage
                 value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='handicapRatio') ?
                 '/app/media/images/descending-arrow.gif' :''}" border="0"/> </t:commandLink> </f:facet> <t:panelGrid
                 columns="1" align="center"> <t:graphicImage value="/module/media/images/auditstatus/0.gif" border="0"
                 rendered="#{list.auditStatus==0}" onmouseout="hideTip()"
                 onmouseover="doTooltip(event,'&nbsp;&nbsp;&nbsp;&nbsp;#{resourcesBundle.audit_status_0}')" />
                 <t:graphicImage value="/module/media/images/auditstatus/1.gif" border="0"
                 rendered="#{list.auditStatus==1}" onmouseout="hideTip()"
                 onmouseover="doTooltip(event,'&nbsp;&nbsp;&nbsp;&nbsp;#{resourcesBundle.audit_status_1}')"/>
                 <t:graphicImage value="/module/media/images/auditstatus/2.gif" border="0"
                 rendered="#{list.auditStatus==2}" onmouseout="hideTip()"
                 onmouseover="doTooltip(event,'&nbsp;&nbsp;&nbsp;&nbsp;#{resourcesBundle.audit_status_2}')"/>
                 <t:graphicImage value="/module/media/images/auditstatus/3.gif" border="0"
                 rendered="#{list.auditStatus==3}" onmouseout="hideTip()"
                 onmouseover="doTooltip(event,'&nbsp;&nbsp;&nbsp;&nbsp;#{resourcesBundle.audit_status_3}')"/>
                 <t:graphicImage value="/module/media/images/auditstatus/4.gif" border="0"
                 rendered="#{list.auditStatus==4}" onmouseout="hideTip()"
                 onmouseover="doTooltip(event,'&nbsp;&nbsp;&nbsp;&nbsp;#{resourcesBundle.audit_status_4}')"/>
                 </t:panelGrid> </t:column>--%>
            <t:inputHidden forceId="true" id="toolTipXYpositionField" value="left,top,width,height"/>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
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
<!-- End Paging -->