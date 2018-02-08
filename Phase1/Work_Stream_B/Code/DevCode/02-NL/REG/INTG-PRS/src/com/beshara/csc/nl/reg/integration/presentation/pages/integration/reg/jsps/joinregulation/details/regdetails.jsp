<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
    <htm:link rel="SHORTCUT ICON" href="#{shared_util.contextPath}/app/media/images/favicon.ico" type="image/x-icon"/>
    <htm:link rel="stylesheet" href="../../../media/css/ar/csc_emp_data_report_ar.css" type="text/css"/>
    <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration"
                  var="regIntgResources"/>
    <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <h:form id="myForm">
        <t:aliasBean alias="#{pageBeanName}" value="#{regDetails}">
            <t:saveState value="#{pageBeanName}"/>
            <htm:div id="report_main_container" styleClass="report_main_container">
                <htm:div styleClass="report_header">
                    <htm:div styleClass="header_data">
                        <htm:p style="float:right;">
                            <h:outputText value="#{pageBeanName.moduleTitle}"/>
                        </htm:p>
                        <htm:p style="float:left;">
                            <t:outputText style="margin-left:10px;"
                                          value="#{regIntgResources.printed_in} : #{pageBeanName.date}"></t:outputText>
                             
                            <t:outputText style="margin-left:10px;"
                                          value="#{regIntgResources.hour} : #{pageBeanName.hour}"></t:outputText>
                             
                            <t:outputText style="margin-left:10px;"
                                          value="#{regIntgResources.by} : #{pageBeanName.empName}"></t:outputText>
                        </htm:p>
                    </htm:div>
                </htm:div>
                <htm:div styleClass="middle_part">
                    <htm:div styleClass="report_contents">
                        <htm:div styleClass="report_title">
                            <h:outputText value="#{pageBeanName.pageTitle}"/>
                        </htm:div>
                        <htm:div styleClass="reoprt_body">
                            <%-- <t:outputText styleClass="emp_rec_no" value="#{regIntgResources.emp_count} :
                                 #{pageBeanName.empCount}"></t:outputText>--%>
                             
                            <htm:span styleClass="report_table_panel" id="dataT_data_panel">
                                <h:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%"
                                             cellpadding="3" cellspacing="0">
                                    <h:outputLabel value="#{regIntgResources.reg_type}" styleClass="report_td_key"/>
                                    <h:outputLabel value="#{pageBeanName.regDTO.typesDto.name}"
                                                   styleClass="report_td_value"/>
                                    <h:outputLabel value="#{regIntgResources.reg_issuer}" styleClass="report_td_key"/>
                                    <h:outputLabel value="#{pageBeanName.regDTO.decisionMakerDTO.decmkrtypeName}"
                                                   styleClass="report_td_value"/>
                                    <h:outputLabel value="#{regIntgResources.reg_num}" styleClass="report_td_key"/>
                                    <h:outputLabel value="#{pageBeanName.regDTO.code.regulationNumber}"
                                                   styleClass="report_td_value"/>
                                   <h:outputLabel value="#{regIntgResources.reg_year}" styleClass="report_td_key"/>
                                    <h:outputLabel value="#{pageBeanName.regDTO.yearsDto.name}"
                                                   styleClass="report_td_value"/>
                                    <h:outputLabel value="#{regIntgResources.reg_date}" styleClass="report_td_key"/>
                                    <h:outputLabel value="#{pageBeanName.regDTO.regulationDate}"
                                                   styleClass="report_td_value">
                                        <f:converter converterId="TimeStampConverter"/>
                                    </h:outputLabel>
                                    <h:outputLabel value="#{regIntgResources.reg_apply_date}"
                                                   styleClass="report_td_key"/>
                                    <h:outputLabel value="#{pageBeanName.regDTO.regulationAppliedDate}"
                                                   styleClass="report_td_value">
                                        <f:converter converterId="TimeStampConverter"/>
                                    </h:outputLabel>
                                    <h:outputLabel value="#{regIntgResources.reg_title}" styleClass="report_td_key"/>
                                    <h:outputLabel value="#{pageBeanName.regDTO.regulationTitle}"
                                                   styleClass="report_td_value"/>
                                    <h:outputLabel value="#{regIntgResources.reg_text}" styleClass="report_td_key"/>
                                    <h:outputText value="#{pageBeanName.regDTO.regulationText}" escape="false"
                                                   styleClass="preformatted"/>
                                </h:panelGrid><h:outputLabel value="#{regIntgResources.reg_materials}"
                                                             styleClass="sector_title"/><t:panelGroup>
                                    <t:panelGrid columns="1" rendered="#{pageBeanName.materialsDTOListSize == 0}"
                                                 align="center">
                                        <t:outputText value="#{regIntgResources.no_materials}" styleClass="msg"/>
                                    </t:panelGrid>
                                    <t:dataTable id="dataT_data_materials" var="list"
                                                 value="#{pageBeanName.regDTO.regRegulationMaterialsDTOList}"
                                                 rowStyleClass="" forceIdIndexFormula="#{list.code.key}"
                                                 rowIndexVar="index" renderedIfEmpty="false"
                                                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                                                 footerClass="grid-footer" styleClass="grid-footer"
                                                 headerClass="standardTable_Header"
                                                 rowClasses="standardTable_Row1,standardTable_Row2"
                                                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                                                 width="100%" align="top" dir="#{shared_util.pageDirection}"
                                                 preserveSort="true">
                                        <t:column id="code_column" sortable="false" width="8%">
                                            <f:facet name="header">
                                                <t:commandLink id="sort_code" forceId="true" styleClass="headerLink"
                                                               value="#{globalResources.Code}" onclick="return false"></t:commandLink>
                                            </f:facet>
                                            <h:outputText id="content_code" value="#{list.code.regmaterialCode}"/>
                                        </t:column>
                                        <t:column id="name_column" sortable="false" width="20%">
                                            <f:facet name="header">
                                                <t:commandLink id="sort_name" forceId="true" styleClass="headerLink"
                                                               value="#{regIntgResources.material_header}"
                                                               onclick="return false"></t:commandLink>
                                            </f:facet>
                                            <h:outputText id="content_name" value="#{list.regmaterialHeader}"
                                                          styleClass="inputTextForDataTable"/>
                                        </t:column>
                                        <t:column id="text_column" sortable="false" width="62%">
                                            <f:facet name="header">
                                                <t:commandLink id="sort_text" forceId="true" styleClass="headerLink"
                                                               value="#{regIntgResources.material_text}"
                                                               onclick="return false"></t:commandLink>
                                            </f:facet>
                                            <h:outputText id="content_text" value="#{list.regmaterialText}"
                                                          styleClass="inputTextForDataTable"/>
                                        </t:column>
                                    </t:dataTable>
                                </t:panelGroup><h:outputLabel value="#{regIntgResources.reg_subjects}"
                                                              styleClass="sector_title"/><t:panelGroup>
                                    <t:panelGrid columns="1" rendered="#{pageBeanName.subjectsDTOListSize == 0}"
                                                 align="center">
                                        <t:outputText value="#{regIntgResources.no_subjects}" styleClass="msg"/>
                                    </t:panelGrid>
                                    <t:dataTable id="dataT_data_subjects" var="list"
                                                 value="#{pageBeanName.regDTO.subjectsDTOList}" rowStyleClass=""
                                                 forceIdIndexFormula="#{list.code.key}" rowIndexVar="index"
                                                 renderedIfEmpty="false"
                                                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                                                 footerClass="grid-footer" styleClass="grid-footer"
                                                 headerClass="standardTable_Header"
                                                 rowClasses="standardTable_Row1,standardTable_Row2"
                                                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                                                 width="100%" align="top" dir="#{shared_util.pageDirection}"
                                                 preserveSort="true">
                                        <t:column id="code_sub_column" sortable="false" width="8%">
                                            <f:facet name="header">
                                                <t:commandLink id="sort_code_sub" forceId="true" styleClass="headerLink"
                                                               value="#{globalResources.Code}" onclick="return false"></t:commandLink>
                                            </f:facet>
                                            <h:outputText id="content_code_sub" value="#{list.subjectsDTO.code.key}"/>
                                        </t:column>
                                        <t:column id="name_sub_column" sortable="false" width="20%">
                                            <f:facet name="header">
                                                <t:commandLink id="sort_name_sub" forceId="true" styleClass="headerLink"
                                                               value="#{globalResources.name}" onclick="return false"></t:commandLink>
                                            </f:facet>
                                            <h:outputText id="content_name_sub" value="#{list.subjectsDTO.name}"
                                                          styleClass="inputTextForDataTable"/>
                                        </t:column>
                                    </t:dataTable>
                                </t:panelGroup><h:outputLabel value="#{regIntgResources.attachments}"
                                                              styleClass="sector_title"/><t:panelGroup>
                                    <t:panelGrid columns="1" rendered="#{pageBeanName.dedignTablesDTOListSize == 0}"
                                                 align="center">
                                        <t:outputText value="#{regIntgResources.no_attachments}" styleClass="msg"/>
                                    </t:panelGrid>
                                    <t:dataTable id="dataT_data_att_parent" var="table"
                                                 value="#{pageBeanName.regDTO.regDedignTablesDTOList}" rowStyleClass=""
                                                 forceIdIndexFormula="#{list.code.key}" rowIndexVar="tabIndex"
                                                 renderedIfEmpty="false"
                                                 rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                                                 footerClass="grid-footer" styleClass="grid-footer"
                                                 headerClass="standardTable_Header"
                                                 rowClasses="standardTable_Row1,standardTable_Row2"
                                                 columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                                                 width="100%" align="top" dir="#{shared_util.pageDirection}"
                                                 preserveSort="true">
                                        <t:column id="code_att_parent_column" sortable="false" width="100%">
                                            <h:panelGrid columns="1" border="0" rowClasses="row02,row01" width="100%"
                                                         cellpadding="3" cellspacing="0">
                                                <h:outputLabel value="#{table.header1Text}" styleClass="report_td_key"/>
                                                <h:outputLabel value="#{table.header2Text}" styleClass="report_td_key"/>
                                            </h:panelGrid>
                                            <t:dataTable id="dataT_data_sub_table" var="columns"
                                                         value="#{table.regDesignTabColumnsDTOList[tabIndex].regDesignTabRecordsDTOList}"
                                                         rowStyleClass="" forceIdIndexFormula="#{list.code.key}"
                                                         rowIndexVar="colIndex" renderedIfEmpty="false"
                                                         rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                                                         footerClass="grid-footer" styleClass="grid-footer"
                                                         headerClass="standardTable_Header"
                                                         rowClasses="standardTable_Row1,standardTable_Row2"
                                                         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                                                         width="100%" align="top" dir="#{shared_util.pageDirection}"
                                                         preserveSort="true">
                                                <t:columns id="code_sub_table_column" var="col"
                                                           value="#{table.regDesignTabColumnsDTOList}">
                                                    <f:facet name="header">
                                                        <h:commandLink id="sort_code_sub_table" forceId="true"
                                                                       styleClass="headerLink" value="#{col.name}"
                                                                       onclick="return false"></h:commandLink>
                                                    </f:facet>
                                                    <h:outputText id="content_code_sub_table"
                                                                  value="#{col.regDesignTabRecordsDTOList[colIndex].value}"/>
                                                </t:columns>
                                            </t:dataTable>
                                            <h:panelGrid columns="1" border="0" rowClasses="row02,row01" width="100%"
                                                         cellpadding="3" cellspacing="0">
                                                <h:outputLabel value="#{table.footerText}" styleClass="report_td_key"/>
                                                <h:outputLabel value="#{table.comments}" styleClass="report_td_key"/>
                                            </h:panelGrid>
                                        </t:column>
                                    </t:dataTable>
                                </t:panelGroup></htm:span>
                        </htm:div>
                    </htm:div>
                </htm:div>
                <htm:div styleClass="report_footer">
                    <t:outputText styleClass="copyrights" value="#{regIntgResources.copyrights}"></t:outputText>
                </htm:div>
            </htm:div>
            <%-- ---------------%>
        </t:aliasBean>
    </h:form>
</f:view>
