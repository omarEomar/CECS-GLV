<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-1-row-filter">
    <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}"
                    reRender="divDeleteAlert,OperationBar,divEditLookup"
                    oncomplete="javascript:FireButton('editButton');">
        <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
        <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
        <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
        <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
    </a4j:jsFunction>
    <t:dataTable cellpadding="0" cellspacing="1" id="dataT_data" var="list" value="#{pageBeanName.myTableData}"
                 rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                 forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                 renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                 rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});"
                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                 styleClass="grid-footer" headerClass="standardTable_Header"
                 rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="" width="100%" align="center"
                 dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                 sortAscending="#{pageBeanName.ascending}">
        <t:column id="radio_column" styleClass="standardTable_Header3" width="3%">
            <f:facet name="header"/>
            <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                              onkeypress="FireButtonClick('myForm:okBtn')" onmousedown="toggleRadio(this);"
                              onkeydown="toggleRadio(this);">
                <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                             reRender="divDeleteAlert,OperationBar"/>
            </t:selectOneRadio>
        </t:column>
        <t:column id="officialvac_Type_column" sortable="false" width="35%">
            <f:facet name="header">
                <t:commandLink id="sort_holidayTypesDTO-name" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.officailVacType}"
                               actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='holidayTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='holidayTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_officialvac_Type" value="#{list.holidayTypesDTO.name}"/>
        </t:column>
        <t:column id="officialvac_fromDate" sortable="false" width="20%">
            <f:facet name="header">
                <t:commandLink id="sort_fromDate" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.fromDate}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fromDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_officialvac_fromDate" value="#{list.fromDate}">
                <f:converter converterId="SqlDateConverter"/>
            </h:outputText>
        </t:column>
        <t:column id="officialvac_untilDate" sortable="false" width="20%">
            <f:facet name="header">
                <t:commandLink id="sort_untilDate" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.untilDate}" actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='untilDate') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <h:outputText id="content_officialvac_untilDate" value="#{list.untilDate}">
                <f:converter converterId="SqlDateConverter"/>
            </h:outputText>
        </t:column>
        <t:column id="officialvac_status" sortable="false" width="20%">
            <f:facet name="header">
                <t:commandLink id="sort_status" forceId="true" styleClass="headerLink"
                               value="#{resourcesBundle.officialVac_status}"
                               actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='status') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>
            </f:facet>
            <t:graphicImage value="/app/media/images/DataGrid_Icon_green_flag.gif" rendered="#{list.status== 1 }"
                            onmouseover="doTooltip(event,'#{resourcesBundle.officialVac_valid }')"
                            onmouseout="hideTip()" border="0"/>
            <t:graphicImage value="/app/media/images/DataGrid_Icon_grey_flag.gif" rendered="#{list.status== 0 }"
                            onmouseover="doTooltip(event,'#{resourcesBundle.officialVac_not_active}')"
                            onmouseout="hideTip()" border="0"/>
        </t:column>
    </t:dataTable>
    <t:panelGrid columns="1" align="center" rendered="#{ pageBeanName.listSize == 0 }">
        <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </t:panelGrid>
    <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}"
                   binding="#{pageBeanName.reloadField}"/>
</t:panelGroup>