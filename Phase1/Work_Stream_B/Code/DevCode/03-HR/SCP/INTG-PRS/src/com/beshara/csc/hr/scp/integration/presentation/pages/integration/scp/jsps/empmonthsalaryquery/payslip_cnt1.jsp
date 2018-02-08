<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<htm:style>#test10 tbody tr td { vertical-align: top; } .paySlipTabel2 { height: 160px; }</htm:style>
<t:messages showDetail="true"/>
<t:panelGrid columns="4" width="100%" forceId="true" id="mainDataEmpPanel"
             rowClasses="row01,row02" cellpadding="0" cellspacing="0">
    <%-- <jsp:include page="/integration/emp/jsps/empData/empDataCivilName.jsp"/>--%>
    <%--Start row01 --%>
    <h:outputText value="#{resourcesBundle.civil_id}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText onkeypress="FireButtonClickOldStyle(event , 'filterByCivilButton')" readonly="#{pageBeanName.civilExist}"
                     maxlength="12" forceId="true" id="CivilIdAdd" value="#{pageBeanName.employeeHelper.realCivilId}"
                     styleClass="textbox"/>
        <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:panelGroup>
            <t:commandButton forceId="true" id="filterByCivilButton" type="button" value="#{globalResources.Available}"
                             styleClass="cssButtonSmall" onclick="resetMsgInAdd(); searchAndvalidate();"
                             rendered="#{!pageBeanName.civilExist}"/>
            <a4j:jsFunction name="searchLines" action="#{pageBeanName.filterByCivilId}"
                            reRender="mainDataEmpPanel,scriptGenerator,displayBtnPanel,empCurrentSalInfoPanel,paymentMethodPnl"
                            />
        </t:panelGroup>
        <a4j:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}"
                           oncomplete="setFocusOnlyOnElement('CivilIdAdd');" rendered="#{pageBeanName.civilExist}"
                           styleClass="cssButtonSmall" actionListener="#{pageBeanName.reSetData}"
                           reRender="mainDataEmpPanel,scriptGenerator,displayBtnPanel,empCurrentSalInfoPanel,datePanel,paySlipAllDataPanel"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton type="button" id="Search" styleClass="cssButtonSmall" value="#{globalResources.SearchButton}"
                           oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);setFocusOnElement('civil_div_searchText');"
                           action="#{pageBeanName.showSearchForEmployeeDiv}" reRender="lovEmp"
                           disabled="#{pageBeanName.civilExist}" rendered="#{!pageBeanName.civilExist}"/>
        
        <f:verbatim ><br/></f:verbatim>
        <c2:requiredFieldValidator componentToValidate="CivilIdAdd" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"
                                   uniqueId="CivilIdAddUniqueId"/>
        <c2:regularExpressionValidator componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/"
                                       errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false"
                                       display="dynamic"/>
        <t:outputText forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}"
                      rendered="#{!pageBeanName.employeeHelper.validCivilId}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHired" value="#{resourcesBundle.emp_not_hired}"
                      rendered="#{!pageBeanName.employeeHelper.empHired}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="payrollInfoExist" value="#{resourcesBundle.emp_payroll_info_not_exist}"
                      rendered="#{!pageBeanName.employeeHelper.payrollInfoExist}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHiredInMin" value="#{resourcesBundle.emp_not_hired_in_min}"
                      rendered="#{!pageBeanName.employeeHelper.empHiredInMin}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empEndedService" value="#{resourcesBundle.emp_not_ended_service}"
                      rendered="#{!pageBeanName.employeeHelper.empEndedService}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="empLastDegNotMokafaaShamla"
                      value="#{resourcesBundle.empLastDegNotMokafaaShamla}"
                      rendered="#{!pageBeanName.employeeHelper.empLastDegNotMokafaaShamla}" styleClass="errMsg"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.employee_name}" styleClass="divtext"/>
    <t:inputText styleClass="textboxlarge" forceId="true" id="EmpName" disabled="true"
                 value="#{pageBeanName.employeesDTO.citizensResidentsDTO.fullName}"/>
    <%-- End row01 --%> 
    
    <%-- Start row02 --%>  
    <h:outputText value="#{resourcesBundle.month}" styleClass="divtext"/>
    <t:panelGroup>
        <t:panelGroup>
            <t:selectOneMenu forceId="true" id="monthMenu" styleClass="textboxsmall2" value="#{pageBeanName.month}">
                <!--onchange="recalculateMonSal();"-->
                <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}"
                               var="months"/>
      <a4j:support event="onchange" actionListener="#{pageBeanName.changeMonth}" 
       reRender="displayBtnPanel,mainDataEmpPanel,scriptGenerator,displayBtnPanel,empCurrentSalInfoPanel,datePanel,paySlipAllDataPanel"/>

            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <%--a4j:jsFunction name="recalculateMonSal" action="#{pageBeanName.recalculateMonSal}"
                            reRender="mainDataEmpPanel,paySlipAllDataPanel" oncomplete=""/--%>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.year}" styleClass="divtext"/>
        <t:panelGroup>
            <t:selectOneMenu forceId="true" id="yearMenu" styleClass="textboxsmall2" style="margin: 0px 11px 0px -2px;"
                             value="#{pageBeanName.year}" >
                <!--onchange="recalculateMonSal();"-->
                <t:selectItems value="#{pageBeanName.years}" itemLabel="#{years.code.key}" itemValue="#{years.code.key}"
                               var="years"/>
             <a4j:support event="onchange" actionListener="#{pageBeanName.changeMonth}" 
       reRender="displayBtnPanel,mainDataEmpPanel,scriptGenerator,displayBtnPanel,empCurrentSalInfoPanel,datePanel,paySlipAllDataPanel"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
    </t:panelGroup>
     <t:panelGroup id="empCurrentSalInfoPanel" forceId="true" colspan="2">
    <a4j:commandButton id="display_btn_id" value="#{resourcesBundle.display}" style="margin:0 15px 0;"
                                   disabled="#{!pageBeanName.civilExist}" action="#{pageBeanName.fillPaySlipData}"
                                   styleClass="cssButtonSmall"
                                   reRender="paymentMethodPnl,displayBtnPanel,empCurrentSalInfoPanel,paySlipAllDataPanel,mainDataEmpPanel"
                                   onblur="setFocusOnlyOnElement('myForm:resetData_btn_id');"
                                   oncomplete="adjustDataTable('meritsDiv',150); adjustDataTable('deductionsDiv',150);"/>
          
                <t:commandButton type="button" id="empCurrentSalInfo" styleClass="cssButtonSmall"
                                 value="#{resourcesBundle.emp_current_sal_info}" disabled="#{!pageBeanName.civilExist}">
                    <a4j:support event="onclick" reRender="currentempsalinfoPanel" action="#{pageBeanName.openEmpCurrentInfoDiv}"
                                 oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"/>
                </t:commandButton>
                
                  <t:commandButton id="showDetails_btn_id" value="#{resourcesBundle.show_basic_emp_data_btn}"  disabled="#{!pageBeanName.civilExist}"
                     action="#{pageBeanName.viewEmpDetails}"
                   styleClass="cssButtonSmall"/>
           </t:panelGroup>
      <%-- End row02 --%> 
      
      <%--Start row03 --%>          
    <h:outputText value="#{resourcesBundle.finance_degree}" styleClass="divtext"/>
    <t:inputText disabled="true" styleClass="textboxlarge" style="width:378px;" forceId="true" id="degree"
                 value="#{pageBeanName.monYearDegree}"/>
    <h:outputText value="#{resourcesBundle.work_center}" styleClass="divtext"/>
    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="WorkCenter"
                 value="#{pageBeanName.monYearWorkCenter}"/>
    <%--End  row03 --%>
    
    <%--Start  row04 --%>
    <h:outputText value="#{resourcesBundle.budgetProgram}" styleClass="divtext"/>
    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="budgetProgram"
                 value="#{pageBeanName.monYearBgtPrg}" style="width:378px;"/>
                    
    <h:outputText value="#{resourcesBundle.budgetType}" styleClass="divtext"/>
    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="budgetType"
                 value="#{pageBeanName.monYearBgtType}"/>
    <%--Start row07 --%>
    <h:outputText value="#{resourcesBundle.gender_type}" styleClass="divtext"/>
    <t:panelGroup>
              <t:inputText disabled="true" styleClass="textbox" forceId="true" style="margin-left: 20px;" id="Type"
                     value="#{pageBeanName.employeesDTO.citizensResidentsDTO.genderTypesDTO.name}"/>
        <%-- <t:outputText styleClass="textbox" forceId="true" id="Type"
             value="#{pageBeanName.kwtCitizen.genderTypesDTO.name}" />--%>
              <h:outputText value="#{resourcesBundle.marital_status}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText disabled="true" styleClass="textbox" style="margin: 0px 15px 0px 0px;" forceId="true" id="SocialStatus"
                     value="#{pageBeanName.employeesDTO.citizensResidentsDTO.maritalStatusDTO.name}"/>
    </t:panelGroup>
    </t:panelGroup> 
    <h:outputText value="#{resourcesBundle.job_name}" styleClass="divtext"/>
    <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="JobName"
                 value="#{pageBeanName.monYearJob}"/>
     
    <%--End row04 --%>
    
    <%--Start row05 --%>
    <h:outputText value="#{resourcesBundle.sal_status}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText disabled="true" styleClass="textbox" forceId="true" style="margin-left: 20px;" id="salStatus"
                     value="#{pageBeanName.monSalDTO.paymentStatusDTO.name}"/>
        <h:outputText value="#{resourcesBundle.sal_calc_date}" styleClass="divtext"/>
        <t:panelGroup>
            <t:inputText disabled="true" styleClass="textbox" forceId="true" id="salCalcDate"
                         style="margin: 0px 5px 0px 0px;" value="#{pageBeanName.monSalDTO.calcDate}">
                <f:convertDateTime pattern="dd/MM/yyyy" timeZone="GMT+2"/>
            </t:inputText>
        </t:panelGroup>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.sheet_no}" styleClass="divtext"/>
    <t:inputText disabled="true" styleClass="textbox" style="margin: 0px 0px 0px 10px;" forceId="true" id="sheet_no"
                 value="#{paySlipMaintainQueryBean.sheetCode}"/>
    <%--End row05 --%>
    
    <%--Start row06 --%>
    <h:outputText value="#{resourcesBundle.bank_name}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText disabled="true" styleClass="textbox" forceId="true" id="bankName"
                     value="#{pageBeanName.monSalDTO.bankBranchesDTO.banksDTO.name}"/>
        <h:outputText value="#{resourcesBundle.branch_name}" styleClass="divtext"
                      style="display: inline; margin: 0px 20px 0px 74px;"/>
        <t:panelGroup>
            <t:inputText disabled="true" styleClass="textbox" forceId="true" id="branchName"
                         value="#{pageBeanName.monSalDTO.bankBranchesDTO.name}"/>
        </t:panelGroup>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.account_no}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText disabled="true" styleClass="textboxlarge" forceId="true" id="accountNo"
                     value="#{pageBeanName.monSalDTO.accountChekNo}"/>
        <t:inputHidden forceId="true" id="civilExistHidden" value="#{pageBeanName.civilExist}"/>
    </t:panelGroup>
    <%--End row06 --%>
   
    
    <%--End row07 --%>
    <%--<h:outputText value="#{resourcesBundle.marital_status}" styleClass="divtext"/>--%>
    <%--<t:panelGroup>
        <t:inputText disabled="true" styleClass="textbox" forceId="true" id="SocialStatus"
                     value="#{pageBeanName.employeesDTO.citizensResidentsDTO.maritalStatusDTO.name}"/>
    </t:panelGroup>--%>
    
    <%--Start row08 --%>
    <t:panelGroup colspan="4" forceId="true" id="paymentMethodPnl" >
        <t:panelGrid align="center" columns="2">                                               
            <t:panelGroup  rendered="#{pageBeanName.showPaymentMethod}">
                <h:outputText value="#{resourcesBundle.Financial_Dues_payment_method}" styleClass="divtext"/>  
                <t:selectOneMenu forceId="true" id="paymentMethod"
                             styleClass="textbox" value="#{pageBeanName.paymentMethodCode}" >
                    <t:selectItems value="#{pageBeanName.paymentMethods}" itemLabel="#{paymentMethods.name}"
                             itemValue="#{paymentMethods.code.key}" var="paymentMethods" />
                  <!-- CSC-19378 Old code action="#{pageBeanName.reset_Data}"   commented by Nora Ismail -->    
                <a4j:support event="onchange"   action="#{pageBeanName.fillPaySlipData2}" oncomplete="adjustDataTable('meritsDiv',150); adjustDataTable('deductionsDiv',150);"
                reRender="displayBtnPanel,mainDataEmpPanel,scriptGenerator,displayBtnPanel,empCurrentSalInfoPanel,datePanel,paySlipAllDataPanel" />
                </t:selectOneMenu>
            </t:panelGroup>
            <t:panelGroup forceId="true" id="displayBtnPanel">
                <a4j:commandButton id="settlementDetailng" value="#{resourcesBundle.displaySettlement}"
                                   rendered="#{pageBeanName.settler}" styleClass="cssButtonSmall"
                                   action="#{pageBeanName.showAllSettlementsDicisionsDiv}"
                                   oncomplete="javascript:changeVisibilityDiv(window.blocker,window.customDiv2);"
                                   reRender="customDiv2, mainDataEmpPanel"/>
                <t:commandButton id="displayIntervals" forceId="true" value="#{resourcesBundle.displayIntervals}"
                                 styleClass="cssButtonSmall" action="#{pageBeanName.showEmpIntervals}"
                                 rendered="#{pageBeanName.hasIntervals}"/>
                <%-- rendered="#{pageBeanName.hasIntervals}"--%>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
    <%--End row08 --%>

</t:panelGrid>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="paySlipAllDataPanel" style="width:100%;">
    
         <t:panelGrid columns="1" rendered="#{pageBeanName.showPaySlipPanel==3}" align="center">
            <t:outputLabel value="#{resourcesBundle.ERR_MSG_P1} #{pageBeanName.month}/#{pageBeanName.year} #{resourcesBundle.ERR_MSG_P2} #{pageBeanName.advancedPaymentMonthYear}  "  styleClass="msg"/>
         
        </t:panelGrid>
    
        <t:panelGrid columns="2" rendered="#{pageBeanName.showPaySlipPanel==2}" align="center">
            <t:outputLabel value="#{resourcesBundle.emp_mon_salary_query_no_data}" rendered="#{pageBeanName.paymentMethodCode=='3' && !pageBeanName.empEndedService }" styleClass="msg"/>
            <t:outputLabel value="#{resourcesBundle.emp_Ended_Serviec_no_data} : #{pageBeanName.empDTO.endJobDate}" rendered="#{pageBeanName.paymentMethodCode=='3' &&  pageBeanName.empEndedService }" styleClass="msg"/>
            <t:outputLabel value="#{resourcesBundle.emp_separated_mon_salary_query_no_data}" rendered="#{pageBeanName.paymentMethodCode=='5'}" styleClass="msg"/>
        </t:panelGrid>
        <t:panelGroup rendered="#{pageBeanName.showPaySlipPanel==1}" style="width:100%;">
            <t:panelGrid columns="1" width="100%" forceId="true" id="settlementDataPanel" cellpadding="0"
                         cellspacing="0">
                <t:panelGrid columns="3" width="100%" cellpadding="0" cellspacing="0">
                    <htm:table width="100%">
                        <htm:tr>
                            <htm:td width="9">
                                <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9"
                                         height="9"/>
                            </htm:td>
                            <htm:td styleClass="group_title">
                                &nbsp;
                                <t:outputLabel value="#{resourcesBundle.merits}" styleClass="lable01"/>
                            </htm:td>
                        </htm:tr>
                         
                        <htm:tr>
                            <htm:td colspan="2" height="1">
                                <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg"
                                         width="100%" height="1"/>
                            </htm:td>
                        </htm:tr>
                    </htm:table>
                    <f:verbatim/>
                    <htm:table width="100%">
                        <htm:tr>
                            <htm:td width="9">
                                <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9"
                                         height="9"/>
                            </htm:td>
                            <htm:td styleClass="group_title">
                                &nbsp;
                                <t:outputLabel value="#{resourcesBundle.deductions}" styleClass="lable01"/>
                            </htm:td>
                        </htm:tr>
                         
                        <htm:tr>
                            <htm:td colspan="2" height="1">
                                <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg"
                                         width="100%" height="1"/>
                            </htm:td>
                        </htm:tr>
                    </htm:table>
                </t:panelGrid>
                <t:panelGrid columns="3" width="100%" rowClasses="row02,row01" cellpadding="0" cellspacing="0"
                             style="vertical-align: top" id="test10" forceId="true">
                    <%-- ********************************************** el este7qaqaaaat yasta ;)
                         ***************************************--%>
                    <htm:td style="vertical-align: top;">
                        <t:panelGroup forceId="true" id="meritsDiv_panel"
                                       style="width: 435px; display: block; vertical-align: top;  height: 156px; overflow: auto; background-color: #ffffff;">
                            <t:panelGroup forceId="true" id="meritsDiv" colspan="3">
                                <t:dataTable forceId="true" id="mdataT_delete" var="list"
                                             value="#{pageBeanName.meritsList}" rowIndexVar="index"
                                             renderedIfEmpty="false"
                                             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_delete');"
                                             footerClass="grid-footer" styleClass="grid-footer"
                                             headerClass="standardTable_Header"
                                             rowClasses="standardTable_Row1,standardTable_Row2" width="100%"
                                             align="center" dir="#{shared_util.pageDirection}"
                                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column">
                                    <t:column id="mcode_column" width="10%">
                                        <f:facet name="header">
                                            <h:outputText id="header_mcode" value="#{globalResources.Code}"/>
                                        </f:facet>
                                        <h:outputText id="content_mcode" value="#{list.code.key}"/>
                                    </t:column>
                                    <t:column id="mname_column" width="65%">
                                        <f:facet name="header">
                                            <h:outputText id="header_mname" value="#{resourcesBundle.description}"/>
                                        </f:facet>
                                        <h:outputText id="content_mname" value="#{list.name}"/>
                                    </t:column>
                                    <t:column id="mvalue_column" width="25%">
                                        <f:facet name="header">
                                            <t:outputText id="header_mvalue" value="#{resourcesBundle.value}"/>
                                        </f:facet>
                                        <h:outputText id="content_mvalue" value="#{list.bigValue}"
                                                      converter="BigDecimalThreeDigitConverter"/>
                                    </t:column>
                                </t:dataTable>
                                <%-- <t:panelGrid columns="1" rendered="#{empty pageBeanName.meritsList }"
                                     align="center"> <t:outputText value="#{resourcesBundle.no__result_for_merits}"
                                     styleClass="msg"/> </t:panelGrid>--%>
                            </t:panelGroup>
                            <%-- <t:panelGrid columns="2" align="#{globalResources.revAlign}" id="totalMeritsPanel">
                                 <h:outputText value="#{resourcesBundle.merits_total}" styleClass="divtext"/>
                                 <t:inputText styleClass="textbox" forceId="true" disabled="true" id="meritsTotal"
                                 value="#{pageBeanName.totalMerits}" converter="javax.faces.BigDecimal"/> </t:panelGrid>--%>
                        </t:panelGroup>
                    </htm:td>
                    <f:verbatim/>
                    <htm:td style="vertical-align: top;">
                        <%-- ********************************************** el esteqta3aaaat yasta ;)
                             ***************************************--%>
                         
                        <t:panelGroup forceId="true" id="deductionsDiv_panel"
                                       style="width: 435px; display: block; vertical-align: top;  height: 156px; overflow: auto; background-color: #ffffff;">
                            <t:panelGroup forceId="true" id="deductionsDiv">
                                <t:dataTable forceId="true" id="ddataT_delete" var="list"
                                             value="#{pageBeanName.deductionsList}" rowIndexVar="index"
                                             renderedIfEmpty="false"
                                             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_delete');"
                                             footerClass="grid-footer" styleClass="grid-footer"
                                             headerClass="standardTable_Header"
                                             rowClasses="standardTable_Row1,standardTable_Row2" width="100%"
                                             align="center" dir="#{shared_util.pageDirection}"
                                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                                             binding="#{pageBeanName.deductionTable}">
                                    <t:column id="dcode_column" width="10%">
                                        <f:facet name="header">
                                            <h:outputText id="header_dcode" value="#{globalResources.Code}"/>
                                        </f:facet>
                                        <h:outputText id="content_dcode" value="#{list.elementGuidesDTO.code.key}"/>
                                    </t:column>
                                    <t:column id="dname_column" width="65%">
                                        <f:facet name="header">
                                            <h:outputText id="header_dname" value="#{resourcesBundle.description}"/>
                                        </f:facet>
                                        <h:outputText id="content_dname" value="#{list.elementGuidesDTO.name}"/>
                                        <f:verbatim>&nbsp;</f:verbatim>
                                        <t:commandButton id="_dedDetailsId" action="#{pageBeanName.queryDeduction}"
                                                         styleClass="info-icon-btn" onmouseout="hideTip()" value=" "
                                                         rendered="#{list.elmSerial != null && list.elementGuidesDTO.salElementTypesDTO.code.elmtypeCode == 35}"
                                                         onmouseover="doTooltip(event,' #{resourcesBundle.deductionEnquiry}')"/>
                                    </t:column>
                                    <t:column id="dvalue_column" width="25%">
                                        <f:facet name="header">
                                            <h:outputText id="header_dvalue" value="#{resourcesBundle.value}"/>
                                        </f:facet>
                                        <t:outputText id="content_dvalue" value="#{list.value}"
                                                      converter="BigDecimalThreeDigitConverter"/>
                                    </t:column>
                                </t:dataTable>
                                <%-- <t:panelGrid columns="1" rendered="#{empty pageBeanName.deductionsList }"
                                     align="center"> <t:outputText value="#{resourcesBundle.no_result_for_deductions}"
                                     styleClass="msg"/> </t:panelGrid>--%>
                            </t:panelGroup>
                            <%-- <t:panelGrid columns="3" align="#{globalResources.revAlign}" id="deductionsTotalPanel">
                                 <h:outputText value="#{resourcesBundle.deductions_total}" styleClass="divtext"/>
                                 <f:verbatim>&nbsp;&nbsp;</f:verbatim> <t:inputText styleClass="textbox" forceId="true"
                                 disabled="true" id="deductionsTotal" value="#{pageBeanName.totalDeductions}"
                                 converter="javax.faces.BigDecimal"/> </t:panelGrid>--%>
                        </t:panelGroup>
                    </htm:td>
                </t:panelGrid>
                <t:panelGrid columns="6" width="100%" rowClasses="row01,row02" cellpadding="0" cellspacing="0"
                             id="actualSalaryPanel">
                    <t:panelGrid columns="2" align="right" id="totalMeritsPanel" rowClasses="row01,row02">
                        <h:outputText value="#{resourcesBundle.merits_total}" style="font-weight: bold;"
                                      styleClass="divtext"/>
                        <t:inputText styleClass="textbox" forceId="true" disabled="true" id="meritsTotal"
                                     value="#{pageBeanName.totalMerits}" converter="BigDecimalThreeDigitConverter"/>
                    </t:panelGrid>
                    <t:panelGrid columns="2" align="center" rowClasses="row01,row02">
                        <h:outputText value="#{resourcesBundle.actual_sal}" style="font-weight: bold;"
                                      styleClass="divtext"/>
                        <t:inputText styleClass="textbox" forceId="true" disabled="true" id="actualPaid"
                                     value="#{pageBeanName.actualSalary}" converter="BigDecimalThreeDigitConverter"/>
                    </t:panelGrid>
                    <t:panelGrid columns="2" align="left" id="deductionsTotalPanel" rowClasses="row01,row02">
                        <h:outputText value="#{resourcesBundle.deductions_total}" style="font-weight: bold;"
                                      styleClass="divtext"/>
                        <t:inputText styleClass="textbox" forceId="true" disabled="true" id="deductionsTotal"
                                     value="#{pageBeanName.totalDeductions}" converter="BigDecimalThreeDigitConverter"/>
                    </t:panelGrid>
                </t:panelGrid>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGroup>
</t:panelGrid>
