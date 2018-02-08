<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%-- <t:outputText id="dedEnquiryErrorMsg" forceId="true" style="display:none;color:red;margin-right:10px;"
     styleClass="output_label" value="#{intgBundle.dedEnquiryErrorMsg}"/>--%>
<%-- <t:inputHidden forceId="true" id="cm_smCustomAction" value="1#nav_2_1_Btn,2#nav_2_2_Btn"/>--%>
<t:panelGrid columns="4" id="main_panel" rowClasses="row02,row01" cellpadding="3" cellspacing="0" width="100%" border="0">
    <!-- first row -->
    <t:outputText value="#{intgBundle.elementGuideId}" styleClass="output_label "style="white-space:nowrap !important;"/>
    <t:panelGroup>
        <t:inputText value="#{pageBeanName.pageDTO.elementGuideId}" disabled="true" styleClass="textboxsmall" style="margin-left:1px;"/>             
        <t:inputText style="width: 193px;" value="#{pageBeanName.pageDTO.elementGuideName}" disabled="true" styleClass="textboxmedium2"/>
    </t:panelGroup>
    <t:outputText value="#{intgBundle.secandaryGuideId}" styleClass="output_label "
                  style="white-space:nowrap !important; "/>
     <t:panelGroup>   
         <t:inputText value="#{pageBeanName.pageDTO.secandaryGuideId}" disabled="true" styleClass="textboxsmall" style="margin-left:1px;"/>    
         <t:inputText style="width: 193px;" value="#{pageBeanName.pageDTO.secandaryGuideName}" disabled="true" styleClass="textboxmedium2"/>
    </t:panelGroup>  
    <t:outputText value="#{intgBundle.empCategoryName}" styleClass="output_label "/>
    <t:panelGroup>
      <t:inputText value="#{pageBeanName.pageDTO.empCategoryId}" disabled="true" styleClass="textboxsmall" style="margin-left:1px;"/>    
      <t:inputText style="width: 193px;" value="#{pageBeanName.pageDTO.empCategoryName}" disabled="true" styleClass="textboxmedium2"/>
   </t:panelGroup>
    <!-- second row -->
    <t:outputText value="#{intgBundle.empMinistryName}" styleClass="output_label "/>
     <t:panelGroup>
      <t:inputText value="#{pageBeanName.pageDTO.empMinistryId}" disabled="true" styleClass="textboxsmall" style="margin-left:1px;"/>    
      <t:inputText style="width: 193px;" value="#{pageBeanName.pageDTO.empMinistryName}" disabled="true" styleClass="textboxmedium2"/>
    </t:panelGroup>
    <t:outputText value="#{intgBundle.empCivilId}" styleClass="output_label "/>
    <%-- <t:panelGroup > <t:inputText forceId="true" id="empCivilId" value="#{pageBeanName.pageDTO.empRCivilId}"
         disabled="true" onkeypress="enabelIntegerOnly(this);" onkeyup="enabelIntegerOnly(this);" maxlength="12" />
         <t:outputText style="display:none;color:red;" styleClass="output_label"
         value="#{intgBundle.mustEnterGreaterThan12Digit}" id="empCivilIdMsg" forceId="true" /> </t:panelGroup>--%>
    <t:inputText forceId="true" id="empCivilId" value="#{pageBeanName.pageDTO.empRCivilId}" disabled="true" styleClass="textboxmedium2"/>
    <t:outputText value="#{intgBundle.empName}" styleClass="output_label "/>
    <t:inputText disabled="true" forceId="true" id="empNameId" value="#{pageBeanName.pageDTO.empName}" styleClass="textboxmedium2"/>
    <t:outputText value="#{intgBundle.dedRef}" styleClass="output_label nowraped_txt"/>
    <t:inputText value="#{pageBeanName.pageDTO.id}" id="deductionRefId" forceId="true" disabled="true" styleClass="textboxmedium2"/>
    <!-- third row -->
    <t:outputText value="#{intgBundle.serialNo}" styleClass="output_label "/>
    <t:inputText value="#{pageBeanName.pageDTO.serial}" id="serialNo" forceId="true" disabled="true" styleClass="textboxmedium2"/>
    <t:outputText value="#{intgBundle.decisionNo}" styleClass="output_label "/>
    <t:inputText value="#{pageBeanName.pageDTO.decisionNo}" id="decisionNo" forceId="true" disabled="true" styleClass="textboxmedium2"/>
    <t:outputText value="#{intgBundle.decisionDate}" styleClass="output_label "/>
    <t:inputCalendar id="decisionDateId" maxlength="10" value="#{pageBeanName.pageDTO.decisionDate}"
                     monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass" dayCellClass="dayCellClass"
                     currentDayCellClass="currentDayCellClass" popupTodayString="Today is :" popupWeekString="Wk"
                     renderAsPopup="true" popupButtonImageUrl="/resources/images/icon_calendar.jpg"
                     renderPopupButtonAsImage="true" popupDateFormat="dd/MM/yyyy"
                     onkeypress="validateFullDateOnEvent(this);" onkeyup="validateFullDateOnEvent(this);" alt="Calendar"
                     title="Calendar" styleClass="textboxmedium2" forceId="true" disabled="true"></t:inputCalendar>
    <t:outputText value="#{intgBundle.dedStartDate}" styleClass="output_label "/>
    <!--style="width:100px;" style="white-space:nowrap !important;"
            -->
    <t:inputText id="_startDateValue" forceId="true" value="#{pageBeanName.pageDTO.dedStartDate}" disabled="true"
                 maxlength="7" onkeypress="validateDateOnEvent(this);" onkeyup="validateDateOnEvent(this);" styleClass="textboxmedium2"
                 converter="MonthYearConverter2"/>
    <t:outputText value="#{intgBundle.end_payment_date}" styleClass="output_label "/>
    <t:inputText id="_EndDateValue" forceId="true" value="#{pageBeanName.pageDTO.dedEndDate}" disabled="true"
                 maxlength="7" onkeypress="validateDateOnEvent(this);" onkeyup="validateDateOnEvent(this);" styleClass="textboxmedium2"
                 converter="MonthYearConverter2"/>
    <!-- fourth row -->
    <t:outputText value="#{intgBundle.totalDeducted}" styleClass="output_label nowraped_txt"/>
    <t:inputText id="_totalDeductedValue" maxlength="14" forceId="true" value="#{pageBeanName.pageDTO.totalDeductValue}" styleClass="textboxmedium2"
                 disabled="true" onkeypress="enabelFloatOnly(this);" onkeyup="enabelFloatOnly(this);"/>
    <t:outputText value="#{intgBundle.installmentValue}" styleClass="output_label"/>
    <t:inputText id="installmentValue" maxlength="14" forceId="true" value="#{pageBeanName.pageDTO.installmentValue}" styleClass="textboxmedium2"
                 disabled="true" onkeypress="enabelFloatOnly(this);" onkeyup="enabelFloatOnly(this);"/>
    <t:outputText value="#{intgBundle.installmentsCount}" styleClass="output_label"/>
    <t:inputText id="installmentNo" maxlength="14" forceId="true" value="#{pageBeanName.pageDTO.installmentNo}" styleClass="textboxmedium2"
                 disabled="true" onkeypress="enabelIntegerOnly(this);" onkeyup="enabelIntegerOnly(this);"/>
    <t:outputText value="#{intgBundle.installmentFreq}" styleClass="output_label"/>
    <t:selectOneMenu value="#{pageBeanName.pageDTO.installmentFrequency}" disabled="true" converter="javax.faces.Long" styleClass="textboxmedium2">
        <f:selectItem id="requestRegisteration_selectDeductionFrequency" itemLabel="#{intgBundle.selectitem}"
                      itemValue=""/>
        <f:selectItem id="requestRegisteration_monthly" itemLabel="#{intgBundle.monthly}"
                      itemValue="#{pageBeanName.monthly}"/>
        <f:selectItem id="requestRegisteration_twoMonths" itemLabel="#{intgBundle.twoMonths}"
                      itemValue="#{pageBeanName.twoMonths}"/>
        <f:selectItem id="requestRegisteration_threeMonths" itemLabel="#{intgBundle.threeMonths}"
                      itemValue="#{pageBeanName.threeMonths}"/>
        <f:selectItem id="requestRegisteration_fourMonths" itemLabel="#{intgBundle.fourMonths}"
                      itemValue="#{pageBeanName.fourMonths}"/>
        <f:selectItem id="requestRegisteration_halfYear" itemLabel="#{intgBundle.halfYear}"
                      itemValue="#{pageBeanName.halfYear}"/>
        <f:selectItem id="requestRegisteration_yearly" itemLabel="#{intgBundle.yearly}"
                      itemValue="#{pageBeanName.yearly}"/>
    </t:selectOneMenu>
    <!-- fifth row -->
    <t:outputText id="ministrytype" value="#{intgBundle.type}" styleClass="output_label"/>
    <t:selectOneMenu id="ministrytypevalue" value="#{pageBeanName.pageDTO.dedForGovFlag}" disabled="true" styleClass="textboxmedium2">
        <f:selectItem id="selectministrytype1" itemLabel="#{intgBundle.governmental}" itemValue="1"/>
        <f:selectItem id="selectministrytype2" itemLabel="#{intgBundle.nongovernmental}" itemValue="2"/>
        <a4j:support event="onchange" actionListener="#{pageBeanName.updateCategories}" reRender="my_dataTable"/>
    </t:selectOneMenu>
    <t:outputText id="maker_category" value="#{intgBundle.deductedforcat}" styleClass="output_label"/>
     <t:panelGroup>
      <t:inputText value="#{pageBeanName.pageDTO.deductForCatId}" disabled="true" styleClass="textboxsmall" style="margin-left:1px;"/>    
      <t:inputText style="width: 193px;" value="#{pageBeanName.pageDTO.deductForCatName}" disabled="true" styleClass="textboxmedium2"/>
      </t:panelGroup>
    <t:outputText id="maker_ministry" value="#{intgBundle.deductedformini}" styleClass="output_label"/>
    <t:panelGroup>
      <t:inputText value="#{pageBeanName.pageDTO.deductForMinId}" disabled="true" styleClass="textboxsmall" style="margin-left:1px;"/>    
      <t:inputText style="width: 193px;" value="#{pageBeanName.pageDTO.deductForMinName}" forceId="true" id="selectedMinistryNameId" styleClass="textboxmedium2"
                 disabled="true"/>
      </t:panelGroup>           
    <t:outputText value="#{intgBundle.accountno}" styleClass="output_label "/>
    <t:inputText forceId="true" value="#{pageBeanName.pageDTO.accountNo}" disabled="true" styleClass="textboxmedium2"/>
    <!-- sixth row -->
    <t:outputText value="#{intgBundle.bank}" styleClass="output_label "/>
     <t:panelGroup>
      <t:inputText value="#{pageBeanName.pageDTO.bankId}" disabled="true" styleClass="textboxsmall" style="margin-left:1px;"/>    
      <t:inputText style="width: 193px;" forceId="true" value="#{pageBeanName.pageDTO.bankName}" disabled="true" styleClass="textboxmedium2"/>
     </t:panelGroup>
    <t:outputText value="#{intgBundle.branch}" styleClass="output_label "/>
      <t:panelGroup>
          <t:inputText value="#{pageBeanName.pageDTO.branchId}" disabled="true" styleClass="textboxsmall" style="margin-left:1px;"/>    
          <t:inputText style="width: 193px;" forceId="true" value="#{pageBeanName.pageDTO.branchName}" disabled="true" styleClass="textboxmedium2"/>
     </t:panelGroup>
    <t:outputText value="#{intgBundle.status}" styleClass="output_label "/>
    <t:panelGroup>
    <t:selectOneMenu forceId="true" id="deductionValid" value="#{pageBeanName.pageDTO.status}" styleClass="textboxmedium2"
                     converter="javax.faces.Long" disabled="true">
        <f:selectItem itemLabel="#{intgBundle.all}" itemValue="#{pageBeanName.virtualConstValue}"/>
        <f:selectItem itemLabel="#{intgBundle.validRequest}" itemValue="#{pageBeanName.privatee}"/>
        <f:selectItem itemLabel="#{intgBundle.nonValid}" itemValue="#{pageBeanName.general}"/>
    </t:selectOneMenu>
    </t:panelGroup>
    <t:outputText value="#{intgBundle.ded_req_no_label}" styleClass="output_label"/>
     <t:panelGroup colspan="2"><t:inputText id="ded_req_no_label_alias"  forceId="true" value="#{pageBeanName.pageDTO.loginMinistry}" styleClass="textboxmedium2" disabled="true" />
     </t:panelGroup>
    
     <t:outputText value="#{intgBundle.dedAlias}" styleClass="output_label"/>
     <t:inputText id="IssuerRefNO_alias"  forceId="true" value="#{pageBeanName.pageDTO.issuerRefNo}" styleClass="textboxmedium2" disabled="true" />
 
     <t:outputText value="#{intgBundle.ded_status_type}" styleClass="output_label"/>
     <t:panelGroup>
        <t:inputText id="progress_status_ded1"  forceId="true" value="#{intgBundle.limited_installment}" rendered="#{pageBeanName.pageDTO.dedStatus == '1'}" styleClass="textboxmedium2" disabled="true" />
        <t:inputText style="width: 193px;" id="progress_status_ded2"  forceId="true" value="#{intgBundle.unlimited_installment}" rendered="#{pageBeanName.pageDTO.dedStatus == '0'}" styleClass="textboxmedium2" disabled="true" />
     </t:panelGroup>
        
    <%-- <t:selectBooleanCheckbox forceId="true" id="deductionValid" value="#{pageBeanName.pageDTO.valid}"
         disabled="true" />--%>
    <t:outputText value="#{intgBundle.notes}" styleClass="output_label "/>
    <t:panelGroup colspan="7">
        <t:inputText value="#{pageBeanName.pageDTO.notes}" id="notes" forceId="true" styleClass="textboxxlarge"
                     disabled="true" style="width:666px;"/>
    </t:panelGroup>
    <%-- <t:outputText value="#{intgBundle.custody_type}" styleClass="output_label " rendered="#{pageBeanName.resultMode
         && pageBeanName.pageDTO.secandaryGuideId == pageBeanName.custody}"/>--%>
    <%-- <t:selectOneMenu forceId="true" id="custodyList" value="#{pageBeanName.pageDTO.custodyCode}"
         style="width:130px" styleClass="margin_t5" disabled="true" rendered="#{pageBeanName.resultMode &&
         pageBeanName.pageDTO.secandaryGuideId == pageBeanName.custody}"> <f:selectItem
         itemLabel="#{intgBundle.selectitem}" itemValue=""/> <f:selectItem itemLabel="#{intgBundle.hired_emps}"
         itemValue="1"/> <f:selectItem itemLabel="#{intgBundle.advance_teachers}" itemValue="2"/> <f:selectItem
         itemLabel="#{intgBundle.advance_cleaners}" itemValue="3"/> </t:selectOneMenu>--%>
    <t:outputText value="#{intgBundle.p_civil_id}" styleClass="output_label "
                  rendered="#{pageBeanName.resultMode && (pageBeanName.pageDTO.elementGuideId == pageBeanName.expense)}"/>
    <t:inputText forceId="true" id="claimCivilId" value="#{pageBeanName.pageDTO.claimCivilId}" disabled="true" styleClass="textboxmedium2"
                 rendered="#{pageBeanName.resultMode && (pageBeanName.pageDTO.elementGuideId == pageBeanName.expense)}"/>
    <t:outputText value="#{intgBundle.claim_name}" styleClass="output_label "
                  rendered="#{pageBeanName.resultMode && (pageBeanName.pageDTO.elementGuideId == pageBeanName.expense)}"/>
    <t:inputText forceId="true" id="claim_name" value="#{pageBeanName.pageDTO.claimName}" disabled="true" styleClass="textboxmedium2"
                 rendered="#{pageBeanName.resultMode && (pageBeanName.pageDTO.elementGuideId == pageBeanName.expense)}"/>
    <t:outputText value="#{intgBundle.exe_file_no}" styleClass="output_label "
                  rendered="#{pageBeanName.resultMode && (pageBeanName.pageDTO.elementGuideId == pageBeanName.expense)}"/>
    <t:inputText forceId="true" id="exe_file_no" value="#{pageBeanName.pageDTO.exeFileNo}" disabled="true" styleClass="textboxmedium2"
                 rendered="#{pageBeanName.resultMode && (pageBeanName.pageDTO.elementGuideId == pageBeanName.expense)}"/>
</t:panelGrid>