<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<!--<style type="text/css">
.dataT-With-4-row-filter { /*to justify kidsdetailsdiv.jsp width*/
    height: 200px !important;
}
</style>-->
<htm:style>
.dataT-With-4-row-filter-wives{
    display: block;
    height: 305px !important;
    overflow: auto;    
}
</htm:style>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-4-row-filter-wives">
        <t:dataTable id="dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <%--t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{!detailBeanName.singleSelection}">
                <f:facet name="header"></f:facet>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{raiseWivesBean.selectedRadio}" onmousedown="toggleRadio(this);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedRadioButton}"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
            </t:column--%>
            <t:column id="MER_RAISE_CIVILID" sortable="true" width="12%">
                <f:facet name="header">
                    <t:commandSortHeader id="codeColumn" columnName="code" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <t:outputText id="header_code" value="#{resourcesBundle.MER_RAISE_CIVILID}"/>
                    </t:commandSortHeader>
                </f:facet>
                <t:outputText id="content_code" value="#{list.kwtCitizensResidentsDTO.civilId}"/>
            </t:column>
            <t:column id="MER_RAISE_CLMN_NAME" sortable="true" width="22%">
                <f:facet name="header">
                    <t:commandSortHeader id="nameColumn" columnName="name" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <t:outputText id="header_name" value="#{resourcesBundle.MER_RAISE_CLMN_NAME}"/>
                    </t:commandSortHeader>
                </f:facet>
                <t:outputText id="content_name" value="#{list.kwtCitizensResidentsDTO.fullName}"/>
            </t:column>
            <t:column id="MER_RAISE_CLMN2_NATIONALITY" sortable="true" width="8%">
                <f:facet name="header">
                    <t:commandSortHeader id="NATIONALITYColumn" columnName="NATIONALITY" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <t:outputText id="MER_RAISE_NATIONALITY"
                                      value="#{resourcesBundle.MER_RAISE_CLMN2_NATIONALITY}"/>
                    </t:commandSortHeader>
                </f:facet>
                <t:outputText id="MER_RAISE_NATIONALITY_CONTENT"
                              value="#{list.kwtCitizensResidentsDTO.countriesDTO.name}"/>
            </t:column>
            <t:column id="MER_RAISE_CLMN2_MARR_DATE" sortable="true" width="12%">
                <f:facet name="header">
                    <t:commandSortHeader id="MARR_DATEColumn" columnName="MARR_DATE" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <t:outputText id="MER_RAISE_MARR_DATE" value="#{resourcesBundle.MER_RAISE_CLMN2_MARR_DATE}"/>
                    </t:commandSortHeader>
                </f:facet>
                <t:outputText id="MER_RAISE_MARR_DATE_CONTENT" value="#{list.marriageDate}"
                              converter="TimeStampConverter"/>
            </t:column>
            <t:column id="MER_RAISE_CLMN_ALLOWANCE" sortable="true" width="12%">
                <f:facet name="header">
                    <t:commandSortHeader id="CLMN_ALLOWANCEColumn" columnName="CLMN_ALLOWANCE" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <t:outputText id="MER_RAISE_ALLOWANCE" value="#{resourcesBundle.MER_RAISE_CLMN_ALLOWANCE}"/>
                    </t:commandSortHeader>
                </f:facet>
                <t:outputText id="MER_RAISE_CLMN_ALLOWANCE_CONTENT" value="#{list.allowanceDate}"
                              converter="SqlDateConverter"/>
            </t:column>
            <%--<t:column id="MER_RAISE_CLMN_SUSPEND_ALLOWANCE" sortable="true" width="12%">
                <f:facet name="header">
                    <t:commandSortHeader id="SUSPEND_ALLOWANCEColumn" columnName="SUSPEND_ALLOWANCE" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <t:outputText id="MER_RAISE_SUSPEND_ALLOWANCE"
                                      value="#{resourcesBundle.MER_RAISE_CLMN_SUSPEND_ALLOWANCE}"/>
                    </t:commandSortHeader>
                </f:facet>
                <t:outputText id="MER_RAISE_CLMN_SUSPEND_ALLOWANCE_CONTENT" value="#{list.stopDate}"
                              converter="SqlDateConverter"/>
            </t:column>--%>
            <%--<t:column id="MER_RAISE_CLMN_SUSPEND_REASON" sortable="true" width="12%">
                <f:facet name="header">
                    <t:commandSortHeader id="CLMN_SUSPEND_REASONColumn" columnName="CLMN_SUSPEND_REASON" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <t:outputText id="MER_RAISE_SUSPEND_REASON"
                                      value="#{resourcesBundle.MER_RAISE_CLMN_SUSPEND_REASON}"/>
                    </t:commandSortHeader>
                </f:facet>
                <t:outputText id="MER_RAISE_CLMN_SUSPEND_REASON_content" value="#{list.salStopReasonsDTO.name}"/>
            </t:column>--%>
            <t:column id="status_column" sortable="false" width="5%">
                <f:facet name="header">
                    <t:commandSortHeader id="statusColumn" columnName="status" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true"
                                         >
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <t:outputText id="satus" value="#{globalResources.status}"/>
                    </t:commandSortHeader>
                </f:facet>
                <t:graphicImage id="graph1" border="0" value="/app/media/images/green-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{resourcesBundle.valid}')"
                                rendered="#{list.status == 1}"></t:graphicImage>
                <t:graphicImage id="graph2" border="0" value="/app/media/images/red-circle.gif" onmouseout="hideTip()"
                                onmouseover="doTooltip(event,' #{resourcesBundle.notValid}<br> #{resourcesBundle.stop_stmt} #{list.stopDate} <br> #{resourcesBundle.for_rason} #{list.salStopReasonsDTO.name}')"
                                rendered="#{list.status == 0}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid align="center" rendered="#{detailBeanName.listSize == 0}" styleClass="msg">
            <t:outputText value="#{resourcesBundle.addWivesValidation}"
                          rendered="#{detailBeanName.civilExist && detailBeanName.employeesDTO.citizensResidentsDTO.genderTypesDTO.gentypeCode == 1}"/>
            <t:outputText value="#{resourcesBundle.addWivesValidationNotDefined}"
                          rendered="#{detailBeanName.civilExist && detailBeanName.employeesDTO.citizensResidentsDTO.genderTypesDTO.gentypeCode == 3}"/>
            <t:outputText value="#{globalResources.global_noTableResults}"
                          rendered="#{!detailBeanName.civilExist || detailBeanName.employeesDTO.citizensResidentsDTO.genderTypesDTO.gentypeCode == 2}"/>
            <%-- <t:outputText value="#{resourcesBundle.empMaritalStatusSingle}"
                 rendered="#{maritalStatusBean.maritalStatusSingle}"/>--%>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
