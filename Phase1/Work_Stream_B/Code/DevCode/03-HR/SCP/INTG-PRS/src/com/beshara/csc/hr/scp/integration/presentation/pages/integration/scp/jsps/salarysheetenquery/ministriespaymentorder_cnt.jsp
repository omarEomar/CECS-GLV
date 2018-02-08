<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="cv"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="#{ (pageBeanName.selDedToMinCode == null || empty pageBeanName.selDedToMinCode )? 'dataT-With-4-row-filter' : 'dataT-With-5-row-filter' }">
        <%-- <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}"
             status="notdefined" reRender="divDeleteAlert,OperationBar,divEditLookup"
             oncomplete="javascript:FireButton('editButton');"> <a4j:actionparam name="JS_PARAM_INDEX"
             assignTo="#{pageBeanName.clickedRowIndex}"/> <a4j:actionparam name="JS_PARAM_TYPE"
             assignTo="#{pageBeanName.selectionComponentType}"/> <a4j:actionparam name="tableBinding"
             value="pageBeanName.myDataTable"/> <a4j:actionparam name="clickedDTOList"
             value="pageBeanName.selectedDTOS"/> </a4j:jsFunction>--%>
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="itemIndex"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}" rowOnMouseOut="javascript:hideTip();"
                     rowOnMouseOver="javascript: if(#{list.salDeductToMinistriesDTO.accountName!=null}) doTooltip(event,'#{resourcesBundle.account_name} : #{list.salDeductToMinistriesDTO.accountName}');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <%--<t:column id="radio_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
            </t:column>--%>
            <t:column id="ministry_column" sortable="false" width="30%">
                <f:facet name="header">
                    <t:commandLink id="sort_salDeductToMinistriesDTO-ministriesDTO-name" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.ministryName}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salDeductToMinistriesDTO-ministriesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salDeductToMinistriesDTO-ministriesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_ministry" value="#{list.salDeductToMinistriesDTO.ministriesDTO.catsDTO.name} / #{list.salDeductToMinistriesDTO.ministriesDTO.name}"/>
            </t:column>
            <t:column id="totalDeduction_column" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_totalDeductionValue" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.total_deduction}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='totalDeductionValue') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='totalDeductionValue') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_totalDeduction" value="#{list.totalDeductionValue}"/>
            </t:column>
            <t:column id="bnkName_column" sortable="false" width="30%">
                <f:facet name="header">
                    <t:commandLink id="sort_salDeductToMinistriesDTO-bankBranchesDTO-banksDTO-name" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.bank_name}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salDeductToMinistriesDTO-bankBranchesDTO-banksDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salDeductToMinistriesDTO-bankBranchesDTO-banksDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_bankName" value="#{list.salDeductToMinistriesDTO.bankBranchesDTO.banksDTO.name}"/>
            </t:column>
             <t:column id="accountNumber_column" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_salDeductToMinistriesDTO-code-accountNo" forceId="true" styleClass="headerLink"
                                   value="#{resourcesBundle.account_number}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salDeductToMinistriesDTO-code-accountNo') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salDeductToMinistriesDTO-code-accountNo') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_accountNumber" value="#{list.salDeductToMinistriesDTO.code.accountNo}"/>
            </t:column>
             <t:column id="btns_transferred_column" sortable="false" width="35%">
                <f:facet name="header"></f:facet>
                <%--<t:commandButton id="print_details_btn" forceId="true" value="#{resourcesBundle.detailed_payment_order_print}" styleClass="cssButtonSmall"
                                 type="button" onclick="if(validatemyForm()){openDetailedPayOrderReport();}else{return false;}" >
                    <a4j:jsFunction name="openDetailedPayOrderReport" action="#{pageBeanName.printDetailedPaymentOrder}"
                                 reRender="reportUrlId"
                                 oncomplete="openReportWindow('reportUrlId');"/>
                </t:commandButton>--%>
                <a4j:commandButton id="print_details_btn"  value="#{resourcesBundle.detailed_payment_order_print}" styleClass="cssButtonSmall"
                                onclick="if(!validatemyForm()) return false;" action="#{pageBeanName.printDetailedPaymentOrder}" reRender="reportUrlId" oncomplete="openReportWindow('reportUrlId');"/>                            
                
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
    <t:inputHidden id="reportUrlId" forceId="true" value="#{pageBeanName.reportUrlLink}"/>
</t:panelGrid>
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
