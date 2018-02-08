<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGrid columns="4" width="100%" forceId="true" id="mainDataEmpPanel1" rowClasses="row01,row02" cellpadding="3"
             cellspacing="0" border="0">
    <h:outputText id="category_name" value="#{resourcesBundle.category}" styleClass="lable01"/>
    <t:panelGroup colspan="3">
        <t:panelGrid columns="5" width="100%" rowClasses="row01,row02" cellpadding="3" cellspacing="0" border="0">
            <t:panelGroup>
                <t:inputText forceId="true" id="categoryId" styleClass="filteration_input_text"
                             disabled="#{socialInsurPeroidCalcJobCategoryBean.civilExist || socialInsurPeroidCalcJobCategoryBean.tabIndex != 1}"
                             value="#{socialInsurPeroidCalcJobCategoryBean.catCode}"
                             onkeypress="filterationInputOnKeyPress(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                             onblur="filterationInputOnBlur(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                             onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
                    <a4j:jsFunction name="changeCategoryVal"
                                    actionListener="#{socialInsurPeroidCalcJobCategoryBean.filterByCategory}"
                                    oncomplete="resetMinistry();"
                                    reRender="ministryGroupPanel,errorCodePanelId,errorCodePanelId2,mainDataEmpPanel"/>
                </t:inputText>
                <t:selectOneMenu id="categoryList" forceId="true" styleClass="textboxsmall2"
                                 disabled="#{socialInsurPeroidCalcJobCategoryBean.civilExist || socialInsurPeroidCalcJobCategoryBean.tabIndex != 1}"
                                 value="#{socialInsurPeroidCalcJobCategoryBean.catCode}"
                                 onchange="filterationDropboxOnChange(event,this,'categoryId','errorCodeId',changeCategoryValD);">
                    <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                    <t:selectItems value="#{socialInsurPeroidCalcJobCategoryBean.categoryList}" var="categoryList"
                                   itemValue="#{categoryList.code.catCode}" itemLabel="#{categoryList.name}"/>
                    <a4j:jsFunction name="changeCategoryValD"
                                    actionListener="#{socialInsurPeroidCalcJobCategoryBean.filterByCategory}"
                                    oncomplete="resetMinistry();"
                                    reRender="ministryGroupPanel,errorCodePanelId,errorCodePanelId2,mainDataEmpPanel"/>
                </t:selectOneMenu>
                <%-- h:outputLabel value="*" styleClass="mandatoryAsterisk"/--%>
                <t:panelGroup forceId="true" id="errorCodePanelId" style="display:block;width:50px;">
                    <t:outputLabel id="errorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                                   styleClass="error" style="display:none"/>
                </t:panelGroup>
                <%-- c:requiredFieldValidator group="pageValidation" componentToValidate="categoryList"
                     display="dynamic" errorMessage="#{globalResources.missingField}" /--%>
            </t:panelGroup>
            <%-- minisry--%>
            <h:outputText id="ministry_name" value="#{resourcesBundle.ministry}" styleClass="lable01"/>
            <t:panelGroup forceId="true" id="ministryGroupPanel">
                <t:inputText forceId="true" id="ministryId" styleClass="filteration_input_text"
                             value="#{socialInsurPeroidCalcJobCategoryBean.minCode}"
                             disabled="#{socialInsurPeroidCalcJobCategoryBean.civilExist || socialInsurPeroidCalcJobCategoryBean.catCode == null || socialInsurPeroidCalcJobCategoryBean.tabIndex != 1}"
                             onkeypress="filterationInputOnKeyPress(event,this,'ministryListID','errorCodeId2',changeMinistryFun);"
                             onblur="filterationInputOnBlur(event,this,'ministryListID','errorCodeId2',changeMinistryFun);"
                             onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;"></t:inputText>
                <t:selectOneMenu id="ministryListID" forceId="true" styleClass="textboxsmall2"
                                 value="#{socialInsurPeroidCalcJobCategoryBean.minCode}"
                                 disabled="#{socialInsurPeroidCalcJobCategoryBean.civilExist || socialInsurPeroidCalcJobCategoryBean.catCode == null || socialInsurPeroidCalcJobCategoryBean.tabIndex != 1}"
                                 onchange="filterationDropboxOnChange(event,this,'ministryId','errorCodeId',changeMinistryFun);">
                    <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                    <t:selectItems value="#{socialInsurPeroidCalcJobCategoryBean.ministryList}" var="ministryList"
                                   itemValue="#{ministryList.code.minCode}" itemLabel="#{ministryList.name}"/>
                    <a4j:jsFunction name="changeMinistryFun"
                                    action="#{socialInsurPeroidCalcJobCategoryBean.changeMinistry}"
                                    reRender="reg_no,mainDataEmpPanel"/>
                </t:selectOneMenu>
                <%-- h:outputLabel value="*" styleClass="mandatoryAsterisk"/--%>
                <t:panelGroup forceId="true" id="errorCodePanelId2" style="display:block;width:50px;">
                    <t:outputLabel id="errorCodeId2" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                                   styleClass="error" style="display:none"/>
                </t:panelGroup>
            </t:panelGroup>
            <%-- c:requiredFieldValidator group="pageValidation" componentToValidate="ministryListID" display="dynamic"
                 errorMessage="#{globalResources.missingField}" /--%>
            <h:outputText value="#{resourcesBundle.reg_no}" styleClass="divtext"/>
            <t:panelGroup>
                <t:inputText styleClass="textboxsmall2" forceId="true" id="reg_no" disabled="true"
                             value="#{socialInsurPeroidCalcJobCategoryBean.regNo}"/>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.civil_id}" styleClass="divtext"/>
    <t:panelGroup forceId="true" id="civilIDGroupId">
        <t:inputText onkeypress="FireButtonClick('filterByCivilButton')"
                     readonly="#{socialInsurPeroidCalcJobCategoryBean.minCode == null || socialInsurPeroidCalcJobCategoryBean.civilExist}"
                     maxlength="12" forceId="true" id="CivilIdAdd"
                     value="#{socialInsurPeroidCalcJobCategoryBean.employeeHelper.realCivilId}"
                     styleClass="textboxsmall2"/>
        <t:outputLabel value="*" styleClass="mandatoryAsterisk"/>
        <t:panelGroup>
            <t:commandButton disabled="#{socialInsurPeroidCalcJobCategoryBean.minCode == null }" forceId="true"
                             id="filterByCivilButton" type="button" value="#{globalResources.Available}"
                             styleClass="cssButtonSmaller"
                             onclick="if(validatemyForm('civilValidation')) searchEmp_fun(); else{return false;}"
                             rendered="#{!socialInsurPeroidCalcJobCategoryBean.civilExist}"/>
            <a4j:jsFunction name="searchEmp_fun" action="#{socialInsurPeroidCalcJobCategoryBean.filterByCivilId}"
                            reRender="mainDataEmpPanel,scriptGenerator,displayBtnPanel,empCurrentSalInfoPanel,paymentMethodPnl"
                            oncomplete="setFocusOnlyOnElement('monthMenu');"/>
        </t:panelGroup>
        <t:commandButton id="resetData_btn_id" value="#{globalResources.reSetButton}"
                         disabled="#{socialInsurPeroidCalcJobCategoryBean.tabIndex != 1}"
                         rendered="#{socialInsurPeroidCalcJobCategoryBean.civilExist }" styleClass="cssButtonSmaller"
                         actionListener="#{socialInsurPeroidCalcJobCategoryBean.reSetData}"/>
        <f:verbatim>&nbsp;</f:verbatim>
        <a4j:commandButton type="button" id="Search" styleClass="cssButtonSmaller"
                           value="#{globalResources.SearchButton}"
                           oncomplete="changeVisibilityDiv(window.blocker,window.lovEmp);setFocusOnElement('civil_div_searchText');"
                           action="#{socialInsurPeroidCalcJobCategoryBean.showSearchForEmployeeDiv}" reRender="lovEmp"
                           disabled="#{socialInsurPeroidCalcJobCategoryBean.minCode == null || socialInsurPeroidCalcJobCategoryBean.civilExist}"
                           rendered="#{!socialInsurPeroidCalcJobCategoryBean.civilExist}"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c:requiredFieldValidator componentToValidate="CivilIdAdd" display="dynamic" group="civilValidation"
                                  errorMessage="#{globalResources.missingField}" highlight="false"
                                  uniqueId="CivilIdAddUniqueId"/>
        <c:regularExpressionValidator componentToValidate="CivilIdAdd" pattern="/^[0-9]{12}$/" group="civilValidation"
                                      errorMessage="#{globalResources.civil_no_not_less_than_12}" highlight="false"
                                      display="dynamic"/>
        <t:outputText forceId="true" id="invalCivilID" value="#{globalResources.civiliderror}"
                      rendered="#{!socialInsurPeroidCalcJobCategoryBean.employeeHelper.validCivilId}"
                      styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHired" value="#{resourcesBundle.emp_not_hired}"
                      rendered="#{!socialInsurPeroidCalcJobCategoryBean.employeeHelper.empHired}" styleClass="errMsg"/>
        <t:outputText forceId="true" id="payrollInfoExist" value="#{resourcesBundle.emp_payroll_info_not_exist}"
                      rendered="#{!socialInsurPeroidCalcJobCategoryBean.employeeHelper.payrollInfoExist}"
                      styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHiredInMin" value="#{resourcesBundle.emp_not_hired_in_min}"
                      rendered="#{!socialInsurPeroidCalcJobCategoryBean.employeeHelper.empHiredInMin}"
                      styleClass="errMsg"/>
        <t:outputText forceId="true" id="empEndedService" value="#{resourcesBundle.emp_not_ended_service}"
                      rendered="#{!socialInsurPeroidCalcJobCategoryBean.employeeHelper.empEndedService}"
                      styleClass="errMsg"/>
        <t:outputText forceId="true" id="empHasInsurrance" value="#{resourcesBundle.emp_has_insurrance_msg}"
                      rendered="#{!socialInsurPeroidCalcJobCategoryBean.empHasInsurrance}" styleClass="errMsg"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.employee_name}" styleClass="divtext"/>
    <t:panelGroup>
        <t:inputText styleClass="textbox" forceId="true" id="EmpName" disabled="true"
                     value="#{socialInsurPeroidCalcJobCategoryBean.empDTO.citizensResidentsDTO.fullName}"/>
    </t:panelGroup>
    <h:outputText value="#{resourcesBundle.start_working_date}" styleClass="divtext"/>
    <t:inputText styleClass="textboxsmall2" forceId="true" id="start_working_date" disabled="true"
                 value="#{socialInsurPeroidCalcJobCategoryBean.empDTO.startWorkingDate}" converter="SqlDateConverter"/>
    <h:outputText value="#{resourcesBundle.nationality_date}" styleClass="divtext"/>
    <t:inputText styleClass="textbox" forceId="true" id="nationality_date" disabled="true"
                 value="#{socialInsurPeroidCalcJobCategoryBean.empDTO.citizensResidentsDTO.nationalityDate}"
                 converter="TimeStampConverter"/>
    <h:outputText value="#{resourcesBundle.save_code}" styleClass="divtext"/>
    <t:panelGroup colspan="4">
        <t:panelGrid columns="6" width="100%" cellpadding="3" cellspacing="0" border="0">
            <t:inputText value="#{socialInsurPeroidCalcJobCategoryBean.saveCode}" styleClass="textboxsmall2"/>
            <h:outputText value="#{resourcesBundle.from_date_param}" styleClass="divtext"/>
            <t:panelGroup>
                <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" id="fromdatec" size="20" maxlength="200" styleClass="textboxsmall2"
                                 currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                 renderPopupButtonAsImage="true"
                                 value="#{socialInsurPeroidCalcJobCategoryBean.fromDate}">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <c:dateFormatValidator componentToValidate="fromdatec" display="dynamic"
                                       errorMessage="#{globalResources.messageErrorForAdding}"
                                       group="dateValidationGroup"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.to_date_param}" styleClass="divtext"/>
            <t:panelGroup>
                <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" id="todatec" size="20" maxlength="200" styleClass="textboxsmall2"
                                 currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                 renderPopupButtonAsImage="true" value="#{socialInsurPeroidCalcJobCategoryBean.todate}">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <c:dateFormatValidator componentToValidate="todatec" display="dynamic"
                                       errorMessage="#{globalResources.messageErrorForAdding}"
                                       group="dateValidationGroup"/>
                <c:compareDateValidator componentToValidate="fromdatec" componentToCompare="todatec" display="dynamic"
                                        group="dateValidationGroup"
                                        errorMessage="#{resourcesBundle.EndLessThanStartErrMSG}" operator="before"/>
            </t:panelGroup>
            <t:commandButton forceId="true" id="viewAll" value="#{resourcesBundle.show}" styleClass="cssButtonSmall"
                             action="#{pageBeanName.getAll}" onclick="return validateRequiredata();"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<t:panelGroup style="height:100%;background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td width="9">
                <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
                 
                <%-- <h:graphicImage url="/app/media/images/op_arrow.jpg" width="9" height="9"/>--%>
            </htm:td>
            <htm:td styleClass="group_title">
                <t:outputLabel value="#{resourcesBundle.calc_template}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="3" valign="top">
                <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
<t:panelGrid columns="4" width="100%" forceId="true" id="mainDataEmpPanel2" rowClasses="row01,row02" cellpadding="3"
             cellspacing="0" border="0">
    <t:panelGroup colspan="4">
        <t:panelGrid columns="4" cellpadding="3" cellspacing="0" border="0" width="100%">
            <h:outputText value="#{resourcesBundle.collect_type_template}" styleClass="divtext"/>
            <t:selectOneMenu id="calc_template_dp" forceId="true" styleClass="Dropdownbox"
                             value="#{socialInsurPeroidCalcJobCategoryBean.calcTemplateType}">
                <f:selectItem itemValue="1" itemLabel="#{resourcesBundle.template_1}"/>
                <f:selectItem itemValue="2" itemLabel="#{resourcesBundle.template_2}"/>
                <f:selectItem itemValue="3" itemLabel="#{resourcesBundle.template_3}"/>
                <f:selectItem itemValue="4" itemLabel="#{resourcesBundle.template_4}"/>
                <f:selectItem itemValue="5" itemLabel="#{resourcesBundle.template_5}"/>
            </t:selectOneMenu>
            <h:outputText value="#{resourcesBundle.total_calc_peroid}" styleClass="divtext"/>
            <t:panelGroup>
                <h:outputText value="#{resourcesBundle.months_for_dma2em}" styleClass="divtext"/>
                <t:panelGroup>
                    <t:inputText style="margin-right: 5px;" styleClass="textboxsmall" forceId="true" id="month_no"
                                 onkeyup="enabelIntegerIncludeZero(this);"
                                 value="#{socialInsurPeroidCalcJobCategoryBean.peroidPerMonth}"/>
                    <c:rangeValidator componentToValidate="month_no" minValue="0" maxValue="12"
                                      group="month_print_combining"
                                      errorMessage="#{resourcesBundle.min_max_month_value}" highlight="false"
                                      display="dynamic"/>
                </t:panelGroup>
                <h:outputText value="#{resourcesBundle.years_for_dma2em}" styleClass="divtext"/>
                <t:inputText style="margin-right: 5px;" styleClass="textboxsmall" forceId="true" id="year_no"
                             onkeyup="enabelIntegerIncludeZero(this);"
                             value="#{socialInsurPeroidCalcJobCategoryBean.yearPerMonth}"/>
                <a4j:commandButton disabled="#{socialInsurPeroidCalcJobCategoryBean.minCode == null || !socialInsurPeroidCalcJobCategoryBean.civilExist}"
                                   style="margin-right: 5px;" id="print_combining_btn"
                                   value="#{resourcesBundle.print_combining_btn}" styleClass="cssButtonSmall"
                                   action="#{socialInsurPeroidCalcJobCategoryBean.printTemplate}" reRender="reportUrlId"
                                   oncomplete="openReportWindow('reportUrlId');"/>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<t:panelGroup style="height:100%;background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
        <htm:tr>
            <htm:td width="9">
                <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
                 
                <%-- <h:graphicImage url="/app/media/images/op_arrow.jpg" width="9" height="9"/>--%>
            </htm:td>
            <htm:td styleClass="group_title">
                <t:outputLabel value="#{resourcesBundle.payment_method_template}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="3" valign="top">
                <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
<t:panelGrid columns="8" width="100%" forceId="true" id="mainDataEmpPanel3" rowClasses="row01,row02" cellpadding="3"
             cellspacing="0" border="0">
            <h:outputText value="#{resourcesBundle.monthly_table_no}" styleClass="divtext"/>
            <t:inputText styleClass="textboxsmall" forceId="true" id="monthly_table_no"
                         value="#{socialInsurPeroidCalcJobCategoryBean.tableNo}"/>
            <h:outputText value="#{resourcesBundle.dec_no_payment}" styleClass="divtext"/>
            <t:inputText styleClass="textboxsmall" forceId="true" id="dec_no_payment"
                         value="#{socialInsurPeroidCalcJobCategoryBean.decNo}"/>
            <h:outputText value="#{resourcesBundle.year_calc_peroid}" styleClass="divtext"/>
            <t:inputText styleClass="textboxsmall" forceId="true" id="year_calc_peroid"
                         value="#{socialInsurPeroidCalcJobCategoryBean.yearPeroidInstallmentValue}"
                         onkeyup="enabelFloatOnly(this);">
                <a4j:support event="onchange"
                             actionListener="#{socialInsurPeroidCalcJobCategoryBean.updateTotalPaymentValue}"
                             reRender="totalPaymentValueGroupID"/>
            </t:inputText>
            <h:outputText value="#{resourcesBundle.installment_value_dk}" styleClass="divtext"/>
            <t:inputText styleClass="textboxsmall" forceId="true" id="installment_value_dk"
                         value="#{socialInsurPeroidCalcJobCategoryBean.installmentValueDK}"
                         onkeyup="enabelFloatOnly(this);">
                <a4j:support event="onchange"
                             actionListener="#{socialInsurPeroidCalcJobCategoryBean.updateTotalPaymentValue}"
                             reRender="totalPaymentValueGroupID"/>
            </t:inputText>
            
            <h:outputText value="#{resourcesBundle.total_instalments_dk}" styleClass="divtext"/>
            <t:panelGroup forceId="true" id="totalPaymentValueGroupID">
                <t:inputText styleClass="textboxsmall" forceId="true" id="total_instalments_dk" style="width:80px;"
                             value="#{socialInsurPeroidCalcJobCategoryBean.totalPaymentValue}" disabled="true"
                             converter="BigDecimalNumberMaskConverter"/>
            </t:panelGroup>
            <a4j:commandButton disabled="#{socialInsurPeroidCalcJobCategoryBean.minCode == null || !socialInsurPeroidCalcJobCategoryBean.civilExist}"
                               style="margin-right: 5px;" id="print_payment_method_template"
                               value="#{resourcesBundle.print_payment_method_template}" styleClass="cssButtonSmall"
                               action="#{socialInsurPeroidCalcJobCategoryBean.printPaymentMethodTemplate}"
                               reRender="reportUrlId" oncomplete="openReportWindow('reportUrlId');"/>
            <t:panelGroup colspan="5" rendered="#{socialInsurPeroidCalcJobCategoryBean.tabIndex==2}">
                <a4j:commandButton onmouseout="hideTip();"
                                   onmouseover="doTooltip(event, '#{resourcesBundle.emp_vac_absence_days_btn_tooltip} ' )"
                                   disabled="#{socialInsurPeroidCalcJobCategoryBean.minCode == null || !socialInsurPeroidCalcJobCategoryBean.civilExist}"
                                   style="margin-right: 5px;" id="emp_vac_absence_days_btn"
                                   value="#{resourcesBundle.emp_vac_absence_days}" styleClass="cssButtonSmall"
                                   action="#{socialInsurPeroidCalcJobCategoryBean.printEmpVacAbsenceDays}"
                                   reRender="reportUrlId" oncomplete="openReportWindow('reportUrlId');"/>
            </t:panelGroup>
            <t:panelGroup colspan="5" rendered="#{socialInsurPeroidCalcJobCategoryBean.tabIndex==3}">
                <%-- disabled="#{pageBeanName.listSize == 0 }"--%>
                <t:commandButton value="#{resourcesBundle.print_change_insurance_btn}" styleClass="cssButtonSmall"
                                 action=""/>
                <t:commandButton value="#{resourcesBundle.print_last_change_salary_btn}" styleClass="cssButtonSmall"
                                 action=""/>
                <t:commandButton value="#{resourcesBundle.print_last_payed_salary_btn}" styleClass="cssButtonSmall"
                                 action=""/>
            </t:panelGroup>
</t:panelGrid>