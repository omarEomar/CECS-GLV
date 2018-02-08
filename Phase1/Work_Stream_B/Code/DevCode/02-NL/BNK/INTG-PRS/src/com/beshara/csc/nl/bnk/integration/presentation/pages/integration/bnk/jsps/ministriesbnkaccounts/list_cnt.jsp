 
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-2-row-filter">
    <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}" status="notdefined"
                    reRender="divDeleteAlert,OperationBar,divEditLookup,scriptGeneratorPanel"
                    oncomplete="javascript:FireButton('editButton');">
        <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
        <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
        <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
        <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
    </a4j:jsFunction>
    <t:panelGroup forceId="true" id="dutiesDetail">
        <t:dataTable forceId="true" id="dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false"
                     binding="#{pageBeanName.myDataTable}" rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});"
                     rowOnMouseOver="javascript:addRowHighlight('dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column id="radio_column" styleClass="standardTable_Header3" width="5%">
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
                    <t:commandLink id="sort_bankBranchesDTO-banksDTO-name" forceId="true" styleClass="headerLink"
                                   value="#{integrationBundle.bank_name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bankBranchesDTO-banksDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bankBranchesDTO-banksDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_code" value="#{list.bankBranchesDTO.banksDTO.name}"/>
            </t:column>
            <t:column id="branch_col" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_bankBranchesDTO-name" forceId="true" styleClass="headerLink"
                                   value="#{integrationBundle.branch_name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bankBranchesDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='bankBranchesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_branch_name" value="#{list.bankBranchesDTO.name}"/>
            </t:column>
            <t:column id="accountNo_column" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_accountNo" forceId="true" styleClass="headerLink"
                                   value="#{integrationBundle.account_number}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='accountNo') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='accountNo') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_accountNo" value="#{list.accountNo}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_accountName" forceId="true" styleClass="headerLink"
                                   value="#{integrationBundle.account_name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='accountName') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='accountName') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_name" value="#{list.accountName}"/>
            </t:column>
            <t:column id="bankAccountType_column" sortable="false" width="15%">
                <f:facet name="header">
                    <t:commandLink id="sort_salBankAccountTypesDTO-name" forceId="true" styleClass="headerLink"
                                   value="#{integrationBundle.account_type}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salBankAccountTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='salBankAccountTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_BankAccountType" value="#{list.salBankAccountTypesDTO.name}"/>
            </t:column>
            <t:column id="status_column" sortable="false" width="5%">
                <f:facet name="header">
                    <t:commandLink id="sort_status" forceId="true" styleClass="headerLink"
                                   value="#{integrationBundle.status}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <%-- <h:outputText id="content_status" value="#{list.status==0? integrationBundle.invalid :
                     list.status==1 ? integrationBundle.valid : list.status==2 ? integrationBundle.freeze : ''}"/>--%>
                <t:graphicImage id="graph1" value="/app/media/images/green-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{integrationBundle.valid}')"
                                rendered="#{list.status == 1}"/>
                <t:graphicImage id="graph2" value="/app/media/images/red-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{integrationBundle.invalid}')"
                                rendered="#{list.status == 0}"/>
                <t:graphicImage id="graph3" value="/app/media/images/yellow-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{integrationBundle.freeze}')"
                                rendered="#{list.status == 2}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid rendered="#{empty pageBeanName.ministryAccountsList}" styleClass="msg">
            <h:outputText value="#{globalResources.global_noTableResults}"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGroup>
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.sortAscending}"/>
<t:saveState value="#{pageBeanName.myTableData}"/>