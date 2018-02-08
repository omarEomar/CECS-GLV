<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<tiles:importAttribute scope="request"/>
<t:panelGroup forceId="true"  id="QulDataTable">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="#{detailBeanName.dataTablestyleClass}">
    <t:panelGrid rendered="#{detailBeanName.listSize == 0}" styleClass="msg" style="height:auto">
        <h:outputText value="#{globalResources.global_noTableResults}"/>
    </t:panelGrid>
        <t:dataTable id="dataT_data" var="list" value="#{detailBeanName.kwtCitizenDTO.personsInformationDTOList}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     rowStyleClass="#{ detailBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     renderedIfEmpty="false" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                     footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     sortColumn="#{detailBeanName.sortColumn}" sortAscending="#{detailBeanName.ascending}"
                     binding="#{detailBeanName.myDataTable}" width="100%" align="top" dir="#{shared_util.pageDirection}"
                     preserveSort="true">
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%" rendered="#{detailBeanName.saveInDB}">
                <f:facet name="header"></f:facet>
                <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{detailBeanName.selectedRadio}"
                                  onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUpVersionTwo(this,event);">
                    <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedRadioButton}"
                                 reRender="OperationBar"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="code_column" sortable="true" width="10%">
                <f:facet name="header">
                    <t:commandSortHeader id="codeColumn" columnName="name" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <h:outputText value="#{qulintegration.certificateCode}"/>
                    </t:commandSortHeader>
                </f:facet>
                <h:outputText id="content_name" value="#{list.centerQualificationsDTO.code.cntqualificationCode}"/>
            </t:column>
            <t:column id="name_column" sortable="true" width="30%">
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
                        <h:outputText value="#{qulintegration.certificateName}"/>
                    </t:commandSortHeader>
                </f:facet>
                <h:outputText id="content_name" value="#{list.centerQualificationsDTO.name}"/>
            </t:column>
            <t:column id="name2_column" sortable="true" width="40%">
                <f:facet name="header">
                    <t:commandSortHeader id="name2Column" columnName="name2" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <h:outputText value="#{qulintegration.approved_certification}"/>
                    </t:commandSortHeader>
                </f:facet>
                <h:outputText id="content_name2"
                              value="#{list.personQualificationsDTO==null ? qulintegration.not_approved_certification : list.personQualificationsDTO.qualificationsDTO.name}"/>
            </t:column>
            <t:column id="qul_date_column" sortable="true" width="10%">
                <f:facet name="header">
                    <t:commandSortHeader id="qulDateColumn" columnName="qulDateColumn" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <h:outputText value="#{qulintegration.qul_date}"/>
                    </t:commandSortHeader>
                </f:facet>
                <h:outputText id="qul_date_name" value="#{list.untilDate}">
                    <f:converter converterId="SqlDateConverter"/>
                </h:outputText>
            </t:column>
            <t:column id="qul_degree_column" sortable="true" width="10%">
                <f:facet name="header">
                    <t:commandSortHeader id="qulDegreeColumn" columnName="qulDegreeColumn" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <h:outputText value="#{qulintegration.qul_degree}"/>
                    </t:commandSortHeader>
                </f:facet>
                <h:outputText id="qul_degree_name" value="#{list.gradeValue}"/>
            </t:column>
            <t:column id="qul_center_column" sortable="true" width="10%">
                <f:facet name="header">
                    <t:commandSortHeader id="qulCenterColumn" columnName="qulCenterColumn" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <h:outputText value="#{qulintegration.qul_center}"/>
                    </t:commandSortHeader>
                </f:facet>
                <h:outputText id="qul_center_name" value="#{list.centerQualificationsDTO.centersDTO.name}"/>
            </t:column>
            <t:column id="qul_country_column" sortable="true" width="10%">
                <f:facet name="header">
                    <t:commandSortHeader id="qulCountryColumn" columnName="qulCountryColumn" arrow="false"
                                         styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <h:outputText value="#{qulintegration.qul_country}"/>
                    </t:commandSortHeader>
                </f:facet>
                <h:outputText id="qul_country_name" value="#{list.centerQualificationsDTO.centersDTO.countries.name}"/>
            </t:column>
            <t:column id="crsRegistrationOrder_column" sortable="true" width="25%">
                <f:facet name="header">
                    <t:commandSortHeader id="crsRegistrationOrder_Column" columnName="crsRegistrationOrder_column"
                                         arrow="false" styleClass="standardTable_Header2" immediate="true">
                        <f:facet name="ascending">
                            <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <f:facet name="descending">
                            <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif"
                                            rendered="true" border="0"/>
                        </f:facet>
                        <h:outputText value="#{qulintegration.qual_status}"/>
                    </t:commandSortHeader>
                </f:facet>
                <%--<h:outputText id="crsRegistrationOrder_name" value="#{list.personQualificationsDTO.crsRegistrationOrder == 1 ? '1' : '2'}" style="visibility:hidden;"/>--%>
                <t:graphicImage id="qulRegistrationMainOne" value="/app/media/images/DataGrid_Icon_green_flag.gif" 
                                onmouseover="doTooltip(event,'#{qulintegration.accepted_qual}') "
                                onmouseout="hideTip()"
                                rendered="#{list.personQualificationsDTO != null}" border="0"/>
                <%--<t:graphicImage id="qulRegistrationMainOne1" value="/app/media/images/DataGrid_Icon_grey_flag.gif"
                                onmouseover="doTooltip(event,'#{qulintegration.accepted_qual}') "
                                onmouseout="hideTip()"
                                rendered="#{list.personQualificationsDTO != null && list.personQualificationsDTO.crsRegistrationOrder != 1}"
                                border="0"/>--%>
                <t:graphicImage id="qulRegistrationMainOne2" value="/app/media/images/DataGrid_Icon_red_flag.gif"
                                onmouseover="doTooltip(event,'#{qulintegration.refused_qual}') "
                                onmouseout="hideTip()"
                                rendered="#{list.personQualificationsDTO == null}" border="0"/>
            </t:column>
            
            <%--<t:column width="120">
                <f:facet name="header">
                    <t:outputText id="delete" value="#{infintegrationBundle.delete}"/>
                </f:facet>
                <t:commandButton styleClass="deleteRowDatatable" id="deleteAttachment" value="delete"
                                 action="#{detailBeanName.deleteQualification}"/>
            </t:column>--%>
            
        </t:dataTable>
    </t:panelGroup>
</t:panelGroup>
