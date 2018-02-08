<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regResources"/>
<f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="intgResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>



<t:panelGroup forceId="true" id="tablesPanel" styleClass="descionCancelDivScroll">
    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" columns="1">
        <htm:table width="100%">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
                </htm:td>
                <htm:td styleClass="group_title">
                    &nbsp; 
                    <t:outputLabel value="#{intgResources.benifits}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
             
            <htm:tr>
                <htm:td colspan="2" height="1">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%"
                             height="1"/>
                </htm:td>
            </htm:tr>
        </htm:table>
        <t:panelGroup forceId="true" id="benfitsList_panel"
                      style="display: block;height: 135px;overflow-x: hidden;overflow-y: auto; width: 100%;">
            <t:dataTable id="dataT_data" var="list" value="#{settlementDetailsBean.benifitList2}"
                         binding="#{settlementDetailsBean.myDataTable}" forceIdIndexFormula="#{list.code.key}"
                         rowIndexVar="index" renderedIfEmpty="false" footerClass="grid-footer" styleClass="grid-footer"
                         headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                         width="100%" align="top" dir="#{shared_util.pageDirection}">
                <t:column id="code_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <t:outputText id="sort_code" forceId="true" styleClass="headerLink"
                                      value="#{globalResources.Code}"/>
                    </f:facet>
                    <h:outputText id="content_code" value="#{list.salElementGuidesDTO.code.key}"/>
                    <%-- <h:outputText id="financial_code" value="#{list.salElementGuidesDTO.financialGuide}"/>--%>
                </t:column>
                <t:column id="name_column" sortable="false" width="40%">
                    <f:facet name="header">
                        <t:outputText id="sort_name" forceId="true" styleClass="headerLink"
                                      value="#{intgResources.section}"/>
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.salElementGuidesDTO.name}"/>
                </t:column>
                <%-- <t:column id="count_column" sortable="false" width="82%"> <f:facet name="header"> <t:commandLink
                     id="sort_count" forceId="true" styleClass="headerLink" value="#{intgResources.Number}"
                     actionListener="#{settlementDetailsBean.sortDataTable}"> <t:graphicImage
                     value="#{(settlementDetailsBean.sortAscending&&settlementDetailsBean.fullColumnName=='countGuide')
                     ? '/app/media/images/ascending-arrow.gif' : ''}" border="0"/> <t:graphicImage
                     value="#{(!settlementDetailsBean.sortAscending&&settlementDetailsBean.fullColumnName=='countGuide')
                     ? '/app/media/images/descending-arrow.gif' :''}" border="0"/> </t:commandLink> </f:facet>
                     <h:outputText id="content_count" value="#{list.countGuide}"/> </t:column>--%>
                <t:column id="value_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <t:outputText id="value_count" forceId="true" styleClass="headerLink"
                                      value="#{intgResources.value}"/>
                    </f:facet>
                    <h:outputText id="content_value" value="#{list.salElementGuidesDTO.value}"/>
                </t:column>
                <t:column id="autoSattelment_column" width="10%">
                    <f:facet name="header">
                        <t:panelGroup>
                            <t:outputText id="sort_autoSattelment" value="#{intgResources.autoSattelment} "/>
                        </t:panelGroup>
                    </f:facet>
                    <t:outputText forceId="true" id="autoSattelment_number"
                                  value="#{list.salElementGuidesDTO.autoSettelmentValue}"/>
                </t:column>
                <t:column id="sign_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <t:outputText id="signId" forceId="true" styleClass="headerLink" value="#{intgResources.sign}"/>
                    </f:facet>
                    <t:selectOneMenu forceId="true" id="signCombo" styleClass="textbox" style="width:40px;"
                                     value="#{list.salElementGuidesDTO.signal}" onchange="applyAllElmGuidesInsurRatiosMapsFn();"
                                     disabled="#{settlementDetailsBean.maintainMode == 2}">
                        <f:selectItem itemLabel="+" itemValue="+"/>
                        <f:selectItem itemLabel="-" itemValue="-"/>
                    </t:selectOneMenu>
                </t:column>
                <t:column id="enteredvalue_column" width="20%">
                    <f:facet name="header">
                        <t:panelGroup>
                            <t:outputText id="sort_valueInDetail"
                                          value="#{intgResources.financialEmpValueNum}  #{intgResources.fels_Denar}"/>
                        </t:panelGroup>
                    </f:facet>
                    <t:inputText forceId="true" id="fels_number" value="#{list.salElementGuidesDTO.felsValue}"
                                 onchange="applyAllElmGuidesInsurRatiosMapsFn();" onkeyup="disableCharacters(this);"
                                 maxlength="3" styleClass="textbox" style="width:35px;"
                                 disabled="#{settlementDetailsBean.maintainMode == 2}"/>
                    <t:inputText forceId="true" id="denar_number" value="#{list.salElementGuidesDTO.denarValue}"
                                 onchange="applyAllElmGuidesInsurRatiosMapsFn();" onkeyup="disableCharacters(this);"
                                 maxlength="4" styleClass="filteration_input_text"
                                 style=" width: 45px;  margin-right: 4px; margin-left: 1px;"
                                 disabled="#{settlementDetailsBean.maintainMode == 2}"/>
                </t:column>
            </t:dataTable>
            <t:panelGrid columns="1" rendered="#{empty settlementDetailsBean.benifitList2}" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
            <a4j:jsFunction name="applyAllElmGuidesInsurRatiosMapsFn"
                            action="#{settlementDetailsBean.applyAllElmGuidesInsurRatiosMaps}"
                            reRender="deductionsList_panel,deductionsList_data,deductions_paging_panel"
                            oncomplete="keepDivUsingCustHeight('hideDivImgId', 'pg_main_Employee_Details', 'tablesPanel');"/>
        </t:panelGroup>
        <htm:table width="100%">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
                </htm:td>
                <htm:td styleClass="group_title">
                    &nbsp; 
                    <t:outputLabel value="#{intgResources.deductions}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
             
            <htm:tr>
                <htm:td colspan="2" height="1">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%"
                             height="1"/>
                </htm:td>
            </htm:tr>
        </htm:table>
        <t:panelGroup forceId="true" id="deductionsList_panel"
                      style="display: block;height: 135px;overflow-x: hidden;overflow-y: auto; width: 100%;">
            <t:dataTable id="deductionsList_data" var="list" value="#{settlementDetailsBean.deductionsList2}"
                         forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" renderedIfEmpty="false"
                         footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2"
                         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                         width="100%" align="top" dir="#{shared_util.pageDirection}">
                <t:column id="deductioncode_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <t:outputText id="deductionsort_code" forceId="true" styleClass="headerLink"
                                      value="#{globalResources.Code}"/>
                    </f:facet>
                    <h:outputText id="deductioncontent_code" value="#{list.salElementGuidesDTO.code.key}"/>
                </t:column>
                <t:column id="deductionname_column" sortable="false" width="40%">
                    <f:facet name="header">
                        <t:outputText id="sort_name" forceId="true" styleClass="headerLink"
                                      value="#{intgResources.section}"/>
                    </f:facet>
                    <h:outputText id="deductioncontent_name" value="#{list.salElementGuidesDTO.name}"/>
                </t:column>
                <%-- <t:column id="deductioncount_column" sortable="false" width="82%"> <f:facet name="header">
                     <t:commandLink id="sort_count" forceId="true" styleClass="headerLink"
                     value="#{intgResources.Number}" actionListener="#{settlementDetailsBean.sortDataTable}">
                     <t:graphicImage
                     value="#{(settlementDetailsBean.sortAscending&&settlementDetailsBean.fullColumnName=='countGuide')
                     ? '/app/media/images/ascending-arrow.gif' : ''}" border="0"/> <t:graphicImage
                     value="#{(!settlementDetailsBean.sortAscending&&settlementDetailsBean.fullColumnName=='countGuide')
                     ? '/app/media/images/descending-arrow.gif' :''}" border="0"/> </t:commandLink> </f:facet>
                     <h:outputText id="deductioncontent_count" value="#{list.countGuide}"/> </t:column>--%>
                <t:column id="deductionvalue_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <t:outputText id="value_count2" forceId="true" styleClass="headerLink"
                                      value="#{intgResources.value}"/>
                    </f:facet>
                    <h:outputText id="deductioncontent_value" value="#{list.salElementGuidesDTO.value}"/>
                </t:column>
                <t:column id="dedAutoSattelment_column" width="10%">
                    <f:facet name="header">
                        <t:panelGroup>
                            <t:outputText id="sort_dedAutoSattelment" value="#{intgResources.autoSattelment} "/>
                        </t:panelGroup>
                    </f:facet>
                    <t:outputText forceId="true" id="dedAutoSattelment_number"
                                  value="#{list.salElementGuidesDTO.autoSettelmentValue}"/>
                </t:column>
                <t:column id="deductionSign_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <t:outputText id="deductionSignId" forceId="true" styleClass="headerLink"
                                      value="#{intgResources.sign}"/>
                    </f:facet>
                    <t:selectOneMenu forceId="true" id="deductionSignCombo" styleClass="textbox" style="width:40px;"
                                     value="#{list.salElementGuidesDTO.signal}"
                                     disabled="#{settlementDetailsBean.maintainMode == 2}">
                        <f:selectItem itemLabel="-" itemValue="-"/>
                        <f:selectItem itemLabel="+" itemValue="+"/>
                    </t:selectOneMenu>
                </t:column>
                <t:column id="deductionEnteredvalue_column" width="20%">
                    <f:facet name="header">
                        <t:panelGroup>
                            <t:outputText id="sort_deductionValueInDetail"
                                          value="#{intgResources.financialEmpValueNum}  #{intgResources.fels_Denar}"/>
                        </t:panelGroup>
                    </f:facet>
                    <t:inputText forceId="true" id="deductionFels_number" value="#{list.salElementGuidesDTO.felsValue}"
                                 onkeyup="disableCharacters(this);" maxlength="3" styleClass="textbox"
                                 style="width:35px;" disabled="#{settlementDetailsBean.maintainMode == 2}"/>
                    <t:inputText forceId="true" id="deductionDenar_number"
                                 value="#{list.salElementGuidesDTO.denarValue}" onkeyup="disableCharacters(this);"
                                 maxlength="4" styleClass="filteration_input_text"
                                 style=" width: 45px;  margin-right: 4px; margin-left: 1px;"
                                 disabled="#{settlementDetailsBean.maintainMode == 2}"/>
                </t:column>
            </t:dataTable>
            <t:panelGrid columns="1" rendered="#{empty settlementDetailsBean.deductionsList2}" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
        <t:panelGroup colspan="2" style="text-align:center;">
            <t:panelGrid forceId="true" id="errorPNGR" width="100%" columns="1" border="0">
                <t:outputText styleClass="regErrMsg" forceId="true" id="valueChangeErrorMsg"
                              value="#{intgResources.oneValueChange}" style="display:none; width: 100%; height: 15px;"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid columns="4" rowClasses="row02,row01" width="99%" cellpadding="3" cellspacing="0" border="0">    
        <h:outputText value="#{intgResources.sett_month}"/>
        <t:selectOneMenu forceId="true" id="settmonthCombo" styleClass="textboxmedium" style="margin-left: 10px;"
                         value="#{settlementDetailsBean.settMonthKey}"
                         disabled="#{settlementDetailsBean.dto.statusFlag ==null || settlementDetailsBean.maintainMode == 2}">
            <t:selectItems value="#{settlementDetailsBean.monthsList}" var="month" itemValue="#{month.code.key}"
                           itemLabel="#{month.name}"/>
        </t:selectOneMenu>
        <h:outputText value="#{intgResources.sett_year}"/>
        <t:selectOneMenu forceId="true" id="settyearCombo" styleClass="textboxmedium" style="margin: 0px 10px;"
                         value="#{settlementDetailsBean.settYearsKey}"
                         disabled="#{settlementDetailsBean.dto.statusFlag ==null || settlementDetailsBean.maintainMode == 2}">
            <t:selectItems value="#{settlementDetailsBean.yearsList}" var="year" itemValue="#{year.code.key}"
                           itemLabel="#{year.code.key}"/>
        </t:selectOneMenu>
        <%-- <t:panelGroup colspan="4"/>--%>
    </t:panelGrid>
    <t:panelGrid columns="3" align="center" width="200px" cellpadding="3" cellspacing="0" border="0">
        <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" disabled="#{settlementDetailsBean.maintainMode == 2}"
                         value="#{globalResources.SaveButton}" action="#{settlementDetailsBean.saveSett}"/>
        <t:commandButton forceId="true" id="backButtonAddDiv" action="#{settlementDetailsBean.back}"
                         styleClass="cssButtonSmall" value="#{globalResources.back}"/>
    </t:panelGrid>
</t:panelGroup>