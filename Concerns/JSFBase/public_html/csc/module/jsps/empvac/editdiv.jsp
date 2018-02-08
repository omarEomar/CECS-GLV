<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.VacEmpDivScroll2 {
    direction: rtl;
    display: block;
    height: 234px;
    overflow-y: auto;
    width: 100%;
}
</htm:style>
<script type="text/javascript">
    
    function setFocusAtFromDateEditx(){
        if(document.getElementById('currentOperationHF') != null){
            var currOperation = document.getElementById('currentOperationHF').value;
            if(currOperation == 'edit'){
                
               if(document.getElementById('fromDateEditx').disabled == false){ 
                    document.getElementById('fromDateEditx').focus();
                    document.getElementById('fromDateEditx').focus();
                }
                else {
                    document.getElementById('vacReason').focus();
                    document.getElementById('vacReason').focus();
                }
                
                
            } else if(currOperation == 'extend' || currOperation == 'cut' ){
                document.getElementById('newUntilDate').focus();
                document.getElementById('newUntilDate').focus();
            } else if(currOperation == 'cancel'){
                document.getElementById('extraInfoTxt').focus();
                document.getElementById('extraInfoTxt').focus();
            } else if(currOperation == 'inform'){
                document.getElementById('returnDate').focus();
                document.getElementById('returnDate').focus();
            } else {
                document.getElementById('SaveButtonEdit').focus();
                document.getElementById('SaveButtonEdit').focus();
            }
        }
        
    }
    function updateVacPeriod(){
        var fromDate = document.getElementById('fromDateEditx').value;
        var untilDate = document.getElementById('untilDateEdity').value;
        if(document.getElementById('balanceDeductedPeriodTxt')!=null){
            calcDeductedPeriod();
        } else {
            calcDaysBetween('fromDateEditx','untilDateEdity','vacationPeriodTxt');
        }
        document.getElementById('fromDateEditx').value = fromDate;
        document.getElementById('untilDateEdity').value = untilDate;
        
    }
    
    function updateVacPeriodAfterOp(){
        
        var fromDate = document.getElementById('fromDateEditx').value;
        var untilDate = document.getElementById('newUntilDate').value;
        if(document.getElementById('balanceDeductedPeriodTxtNew')!=null){
            
            calcDeductedPeriodAfterOp();
        } else {
            calcDaysBetween('fromDateEditx','newUntilDate','balanceDeductedPeriodTxtNew');
        }
        document.getElementById('fromDateEditx').value = fromDate;
        document.getElementById('newUntilDate').value = untilDate;
    }
    function updateNewDeductedPeriod(){
        
        var newUntilDate = document.getElementById('newUntilDate').value;
        calcNewDeductedPeriod();
        document.getElementById('newUntilDate').value = newUntilDate;
    }
    
    function updateDeductedPeriodAfterChangeStart(){
        
        var newStartDate = document.getElementById('newStartDate').value;
        calcNewDeductedPeriodForChangeStart();
        document.getElementById('newStartDate').value = newStartDate;
        calcDaysBetween('newStartDate','untilDateEdity','balanceDeductedPeriodTxtNew');
        alert(document.getElementById('balanceDeductedPeriodTxtNew').value);
        alert('period');
    }
    
    
    

</script>
<%-- calcuateDifferenceTwoDates(); --%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divEditLookup" styleClass="VacEmpDivScroll2" >
    <t:panelGrid onkeypress="FireButtonClick('SaveButtonEdit');" columns="4" width="100%" forceId="true" id="editVacDiv" rowClasses="row02,row01" cellpadding="3" cellspacing="0" >
                <h:outputText  value="#{resourcesBundle.civil_id}" />
                <t:inputText forceId="true" id="edit_civilId" styleClass="textboxmedium"  value="#{pageBeanName.listFilterDTO.civilId}" disabled="true"/>
                <h:outputText  value="#{resourcesBundle.VacHistory_empname}"/> 
                <t:inputText forceId="true" id="edit_name" styleClass="textboxmedium" disabled="true" value="#{pageBeanName.listFilterDTO.civilName}"/>
                
                 <h:outputText value="#{resourcesBundle.vac_type}" />
                 <t:panelGroup colspan="3">
                      <t:inputText forceId="true" id="edit_vacType" styleClass="textboxmedium" disabled="true"  value="#{(pageBeanName.selectedPageDTO.vacVacationTypesDTO!=null)?pageBeanName.selectedPageDTO.vacVacationTypesDTO.name:''}"/>
                  </t:panelGroup>
                  
                <h:outputText value="#{resourcesBundle.start_date_vac}"/>
                 <t:panelGroup  rendered="#{pageBeanName.currentOperation=='changeStart'}">
                    <t:inputText styleClass="textbox" rendered="#{pageBeanName.currentOperation=='changeStart'}" forceId="true" disabled="true" id="fromDateText1" value="#{pageBeanName.selectedPageDTO.fromDate}">
                        <f:converter converterId="SqlDateConverter" />
                    </t:inputText>
                </t:panelGroup>
                <t:panelGroup rendered="#{pageBeanName.currentOperation!='changeStart'}" styleClass="fromDateEditx">
                    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" 
                        onchange="updateVacPeriod();" 
                        disabled="#{!(pageBeanName.currentOperation=='edit' && pageBeanName.vacEmpVacStatusUnderDiscussionForEdit)}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg"  popupDateFormat="dd/MM/yyyy" forceId="true" id="fromDateEditx" value="#{pageBeanName.fromDate}"
                                    size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                     <f:converter converterId="SqlDateConverter" />
                   </t:inputCalendar>
                   <h:outputText rendered="#{(pageBeanName.currentOperation=='edit' && pageBeanName.vacEmpVacStatusUnderDiscussionForEdit)}"  value="*" styleClass="mandatoryAsterisk"/>
                   <%--f:verbatim><br/> </f:verbatim--%>
                   <c2:dateFormatValidator active="#{empVacListBean.pageMode == 2 && empVacListBean.currentOperation=='edit' && empVacListBean.vacEmpVacStatusUnderDiscussionForEdit}" highlight="false" componentToValidate="fromDateEditx" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
                   <c2:requiredFieldValidator componentToValidate="fromDateEditx" active="#{empVacListBean.pageMode == 2 && empVacListBean.currentOperation=='edit' && empVacListBean.vacEmpVacStatusUnderDiscussionForEdit}" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
                   <c2:compareDateValidator componentToValidate="fromDateEditx" componentToCompare="lastYearStartDate" active="#{empVacListBean.pageMode == 2  && empVacListBean.currentOperation=='edit' && empVacListBean.vacEmpVacStatusUnderDiscussionForEdit}" operator="afterOrEqual" display="dynamic" errorMessage="#{resourcesBundle.error_hireDate_requestDate}"/>
                   <c2:compareDateValidator componentToValidate="fromDateEditx" componentToCompare="vacationTypeStartDateEdit" active="#{empVacListBean.pageMode == 2  && empVacListBean.currentOperation=='edit' && empVacListBean.vacEmpVacStatusUnderDiscussionForEdit}" operator="afterOrEqual" display="dynamic" errorMessage="#{resourcesBundle.start_date_must_be_after_vacation_start}"/>
                
                </t:panelGroup>
               
                <h:outputText value="#{resourcesBundle.end_date_vac}" rendered="#{pageBeanName.currentOperation=='edit'|| pageBeanName.currentOperation=='extend' || pageBeanName.currentOperation=='cancel' || pageBeanName.currentOperation=='cut' || pageBeanName.currentOperation=='inform' || pageBeanName.currentOperation=='changeStart'}"/>
                <t:panelGroup  rendered="#{pageBeanName.currentOperation=='edit'|| pageBeanName.currentOperation=='extend' || pageBeanName.currentOperation=='cancel' || pageBeanName.currentOperation=='cut' || pageBeanName.currentOperation=='inform' || pageBeanName.currentOperation=='changeStart'}">
                    <t:inputText styleClass="textbox" rendered="#{pageBeanName.currentOperation=='extend' || pageBeanName.currentOperation=='cancel' || pageBeanName.currentOperation=='cut' || pageBeanName.currentOperation=='inform' || pageBeanName.currentOperation=='changeStart'}" forceId="true" disabled="true" id="untilDateText" value="#{pageBeanName.selectedPageDTO.untilDate}">
                        <f:converter converterId="SqlDateConverter" />
                    </t:inputText>
                    <t:panelGroup rendered="#{pageBeanName.currentOperation=='edit'}" styleClass="untilDateEdity">
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" 
                            onchange="updateVacPeriod();" onfocus="this.autocomplete=false;"
                            disabled="#{(pageBeanName.currentOperation=='edit' && !pageBeanName.vacEmpVacStatusUnderDiscussionForEdit)}" 
                            popupButtonImageUrl="/app/media/images/icon_calendar.jpg"  popupDateFormat="dd/MM/yyyy" 
                            forceId="true" id="untilDateEdity"  
                            value="#{pageBeanName.untilDate}"
                            size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" 
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" 
                            renderPopupButtonAsImage="true"
                            >
                            <%--onblur="if( (!isVisibleComponent('balanceDeductedPeriodTxt')) ){ if setFocusOnlyOnElement('SaveButtonEdit');}"
                            --%>
                         <f:converter converterId="SqlDateConverter" />
                       </t:inputCalendar>
                       <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{(pageBeanName.currentOperation=='edit' && pageBeanName.vacEmpVacStatusUnderDiscussionForEdit)}"/>
                       <%--f:verbatim><br/> </f:verbatim--%>
                       <c2:dateFormatValidator active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='edit' && empVacListBean.vacEmpVacStatusUnderDiscussionForEdit)}" highlight="false" componentToValidate="untilDateEdity" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
                       <c2:requiredFieldValidator componentToValidate="untilDateEdity" active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='edit' && empVacListBean.vacEmpVacStatusUnderDiscussionForEdit)}"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
                       <c2:compareDateValidator componentToValidate="fromDateEditx" componentToCompare="untilDateEdity"  active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='edit' && empVacListBean.vacEmpVacStatusUnderDiscussionForEdit)}" operator="before" display="dynamic" errorMessage="#{resourcesBundle.EndgreaterthanStart}"/>
                       <c2:compareDateValidator componentToValidate="untilDateEdity" componentToCompare="vacationTypeEndDateEdit" active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='edit' && empVacListBean.vacEmpVacStatusUnderDiscussionForEdit)}" operator="before" display="dynamic" errorMessage="#{resourcesBundle.end_date_must_be_before_vacation_end}"/>
                    </t:panelGroup>
                </t:panelGroup>
                <h:outputText value="#{resourcesBundle.vac_return_date}" rendered="#{pageBeanName.currentOperation=='inform'}"/> 
                <t:panelGroup rendered="#{pageBeanName.currentOperation=='inform'}" colspan="3">
                    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                        popupButtonImageUrl="/app/media/images/icon_calendar.jpg"  popupDateFormat="dd/MM/yyyy" 
                        forceId="true" id="returnDate"   onfocus="this.autocomplete=false;"
                        value="#{pageBeanName.returnDate}"
                        size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" 
                        renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" 
                        renderPopupButtonAsImage="true">
                     <f:converter converterId="SqlDateConverter" />
                   </t:inputCalendar>
                   <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                   <%--f:verbatim><br/> </f:verbatim--%>
                   <c2:dateFormatValidator active="#{empVacListBean.pageMode == 2  && empVacListBean.currentOperation=='inform'}" highlight="false" componentToValidate="returnDate" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
                   <c2:requiredFieldValidator componentToValidate="returnDate" active="#{empVacListBean.pageMode == 2  && empVacListBean.currentOperation=='inform'}"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
                   <c2:compareDateValidator componentToValidate="returnDate" componentToCompare="untilDateText"  active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='inform')}" operator="after" display="dynamic" errorMessage="#{resourcesBundle.return_date_must_be_after_end_end}"/>
                </t:panelGroup>
                
                <h:outputText value="#{resourcesBundle.vac_new_date}" rendered="#{pageBeanName.currentOperation=='cut' || pageBeanName.currentOperation=='extend'}"/> 
                <t:panelGroup rendered="#{pageBeanName.currentOperation=='cut' || pageBeanName.currentOperation=='extend'}" colspan="3" styleClass="newUntilDate">
                    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" 
                        onchange="updateNewDeductedPeriod();updateVacPeriodAfterOp();"  onfocus="this.autocomplete=false;"
                        popupButtonImageUrl="/app/media/images/icon_calendar.jpg"  popupDateFormat="dd/MM/yyyy" 
                        forceId="true" id="newUntilDate"  
                        onkeypress="if(window.event.keyCode == 13){window.event.keyCode=0;document.getElementById('SaveButtonEdit').focus();}"
                        value="#{pageBeanName.untilDate}"
                        size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" 
                        renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" 
                        renderPopupButtonAsImage="true">
                     <f:converter converterId="SqlDateConverter" />
                   </t:inputCalendar>
                   <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                   <%--f:verbatim><br/> </f:verbatim--%>
                   <c2:dateFormatValidator active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='cut' || empVacListBean.currentOperation=='extend')}" highlight="false" componentToValidate="newUntilDate" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
                   <c2:requiredFieldValidator componentToValidate="newUntilDate" active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='cut' || empVacListBean.currentOperation=='extend')}"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
                   <c2:compareDateValidator componentToValidate="newUntilDate" componentToCompare="untilDateText"  active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='extend')}" operator="after" display="dynamic" errorMessage="#{resourcesBundle.new_end_date_must_be_after_old_end}"/>
                   <c2:compareDateValidator componentToValidate="untilDateText" componentToCompare="newUntilDate"  active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='cut')}" operator="after" display="dynamic" errorMessage="#{resourcesBundle.new_end_date_must_be_before_old_end}"/>
                   <c2:compareDateValidator componentToValidate="newUntilDate" componentToCompare="vacationTypeEndDateEdit" active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='cut' || empVacListBean.currentOperation=='extend')}" operator="before" display="dynamic" errorMessage="#{resourcesBundle.end_date_must_be_before_vacation_end}"/>
                   <c2:compareDateValidator componentToValidate="fromDateEditx" componentToCompare="newUntilDate" active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='cut')}" operator="before" display="dynamic" errorMessage="#{resourcesBundle.EndgreaterthanStart}"/>
                </t:panelGroup>
                
                <h:outputText value="#{resourcesBundle.vac_new_start_date}" rendered="#{pageBeanName.currentOperation=='changeStart'}"/> 
                <t:panelGroup rendered="#{pageBeanName.currentOperation=='changeStart'}" colspan="3" styleClass="newUntilDate">
                    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" 
                        onchange="updateDeductedPeriodAfterChangeStart();"  onfocus="this.autocomplete=false;"
                        popupButtonImageUrl="/app/media/images/icon_calendar.jpg"  popupDateFormat="dd/MM/yyyy" 
                        forceId="true" id="newStartDate"  
                        onkeypress="if(window.event.keyCode == 13){window.event.keyCode=0;document.getElementById('SaveButtonEdit').focus();}"
                        value="#{pageBeanName.fromDate}"
                        size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" 
                        renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" 
                        renderPopupButtonAsImage="true">
                     <f:converter converterId="SqlDateConverter" />
                   </t:inputCalendar>
                   <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                   <%--f:verbatim><br/> </f:verbatim--%>
                   <c2:dateFormatValidator active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='changeStart')}" highlight="false" componentToValidate="newStartDate" display="dynamic" errorMessage="#{globalResources.InvalidDataEntryException}"/>
                   <c2:requiredFieldValidator componentToValidate="newStartDate" active="#{empVacListBean.pageMode == 2  && empVacListBean.currentOperation=='changeStart'}"  display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
                   <c2:compareDateValidator componentToValidate="newStartDate" componentToCompare="untilDateText" active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='changeStart')}" operator="before" display="dynamic" errorMessage="#{resourcesBundle.EndgreaterthanStart}"/>
                   <c2:compareValidator componentToValidate="newStartDate" componentToCompare="fromDateText1" active="#{empVacListBean.pageMode == 2  && (empVacListBean.currentOperation=='changeStart')}" operator="not" display="dynamic" errorMessage="#{resourcesBundle.dateEqualOldStartDate}"/>
                </t:panelGroup>
                
                
                
                
                <h:outputText value="#{resourcesBundle.actualVacationPeriod}"/> 
                <t:panelGroup colspan="3">
                <t:inputText forceId="true" id="vacationPeriodTxt" value="#{pageBeanName.jsVacationPeriod}" styleClass="textboxsmall" disabled="true"/>
                </t:panelGroup>
                
                <t:outputText value="#{resourcesBundle.periodBeforeExtend}" rendered="#{pageBeanName.renderBalanceDeductedPeriodDataForEdit && pageBeanName.currentOperation=='extend'}"/> 
                <t:outputText value="#{resourcesBundle.periodBeforeCut}" rendered="#{pageBeanName.renderBalanceDeductedPeriodDataForEdit && pageBeanName.currentOperation=='cut'}"/> 
                <t:outputText value="#{resourcesBundle.periodBeforeChangeStart}" rendered="#{pageBeanName.renderBalanceDeductedPeriodDataForEdit && pageBeanName.currentOperation=='changeStart'}"/> 
                <t:outputText value="#{resourcesBundle.balance_deducted_period}" rendered="#{pageBeanName.renderBalanceDeductedPeriodDataForEdit && pageBeanName.currentOperation=='edit'}"/> 
                
                <t:panelGroup colspan="3" rendered="#{pageBeanName.renderBalanceDeductedPeriodDataForEdit}">
                    <t:inputText forceId="true" id="balanceDeductedPeriodTxt" styleClass="textboxsmall" disabled="true" value="#{pageBeanName.selectedPageDTO.vacationPeriod}"/>
                    <a4j:jsFunction name="calcDeductedPeriod" action="#{pageBeanName.calculateVacationPeriodForEditDiv}" reRender="balanceDeductedPeriodTxt,vacationPeriodTxt"/>
                </t:panelGroup>
                
                <t:outputText value="#{resourcesBundle.periodAfterExtend}" rendered="#{pageBeanName.renderBalanceDeductedPeriodDataForEdit && pageBeanName.currentOperation=='extend'}"/> 
                <t:outputText value="#{resourcesBundle.periodAfterCut}" rendered="#{pageBeanName.renderBalanceDeductedPeriodDataForEdit && pageBeanName.currentOperation=='cut'}"/> 
                <t:outputText value="#{resourcesBundle.periodAfterChangeStart}" rendered="#{pageBeanName.renderBalanceDeductedPeriodDataForEdit && pageBeanName.currentOperation=='changeStart'}"/> 
                
                
                <t:panelGroup colspan="3" rendered="#{pageBeanName.renderBalanceDeductedPeriodDataForEdit && pageBeanName.currentOperation!='edit'}">
                    <t:inputText forceId="true" id="balanceDeductedPeriodTxtNew" styleClass="textboxsmall" disabled="true" value="#{pageBeanName.selectedPageDTO.periodAfterOperation}"/>
                    <a4j:jsFunction name="calcDeductedPeriodAfterOp" action="#{pageBeanName.calculatePeriodAfterOperation}" reRender="balanceDeductedPeriodTxtNew"/>
                </t:panelGroup>
                
                <t:outputText value="#{pageBeanName.currentOperation=='extend'?resourcesBundle.new_balance_deducted_period:resourcesBundle.new_balance_gained_period}" rendered="#{(pageBeanName.currentOperation=='cut' || pageBeanName.currentOperation=='extend' || pageBeanName.currentOperation=='cancel'  ) && pageBeanName.renderBalanceDeductedPeriodDataForEdit}"/> 
                <t:panelGroup colspan="3" rendered="#{(pageBeanName.currentOperation=='cut' || pageBeanName.currentOperation=='extend' || pageBeanName.currentOperation=='cancel') && pageBeanName.renderBalanceDeductedPeriodDataForEdit}">
                    <t:inputText forceId="true" id="newbalanceDeductedPeriodTxt" styleClass="textboxsmall" disabled="true" value="#{pageBeanName.newBalanceDeductedPeriod}"/>
                    <a4j:jsFunction name="calcNewDeductedPeriod" action="#{pageBeanName.currentOperation=='extend'?pageBeanName.calculateVacationNewExtendPeriodForEditDiv:pageBeanName.calculateVacationNewCutPeriodForEditDiv}" reRender="newbalanceDeductedPeriodTxt,vacationPeriodTxt"/>
                </t:panelGroup>
                
                <t:outputText id ="deductPeriodForChangeStartId" forceId="true" value="#{pageBeanName.periodDeduct?resourcesBundle.new_balance_deducted_period:resourcesBundle.new_balance_gained_period}" rendered="#{pageBeanName.currentOperation=='changeStart' && pageBeanName.renderBalanceDeductedPeriodDataForEdit}"/> 
                
                <t:panelGroup colspan="3" rendered="#{(pageBeanName.currentOperation=='changeStart') && pageBeanName.renderBalanceDeductedPeriodDataForEdit}">
                
                    <t:inputText forceId="true" id="balancePeriodForChangeStartTxt" styleClass="textboxsmall" disabled="true" value="#{pageBeanName.newBalanceDeductedPeriod}"/>
                    <a4j:jsFunction name="calcNewDeductedPeriodForChangeStart" action="#{pageBeanName.calculateNewPeriodChangeStartForEditDiv}" reRender="balancePeriodForChangeStartTxt,vacationPeriodTxt,newbalanceDeductedPeriodTxt,balanceDeductedPeriodTxtNew,deductPeriodForChangeStartId "/>
                </t:panelGroup>
            
                
                <h:outputText value="#{resourcesBundle.request_date_vac}" rendered="#{pageBeanName.currentOperation=='cancel' || pageBeanName.currentOperation=='edit'}"/>
                <t:panelGroup rendered="#{pageBeanName.currentOperation=='cancel' || pageBeanName.currentOperation=='edit'}" >
                    <t:inputText title="#{globalResources.inputCalendarHelpText}" disabled="true" value="#{pageBeanName.selectedPageDTO.requestDatetime}" styleClass="textbox">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputText>
                    <%--t:inputCalendar title="#{globalResources.inputCalendarHelpText}" disabled="true"  popupButtonImageUrl="/app/media/images/icon_calendar.jpg"  popupDateFormat="dd/MM/yyyy" forceId="true" id="reqDateEdit" value="#{pageBeanName.selectedPageDTO.requestDatetime}"
                                    size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                     <f:converter converterId="TimeStampConverter" />
                   </t:inputCalendar--%>
                </t:panelGroup>
                
                <h:outputText value="#{resourcesBundle.salary_type}" rendered="#{pageBeanName.currentOperation=='cancel' || pageBeanName.currentOperation=='edit'}"/>
                 <t:panelGroup rendered="#{pageBeanName.currentOperation=='cancel' || pageBeanName.currentOperation=='edit'}">
                     <t:inputText    styleClass="textboxmedium" disabled="true"  value="#{(pageBeanName.selectedPageDTO.requiredSalary==pageBeanName.normalSalary)?resourcesBundle.normal_salary :(pageBeanName.selectedPageDTO.requiredSalary==pageBeanName.offeredSalary)? resourcesBundle.offered_salary:(pageBeanName.selectedPageDTO.requiredSalary==pageBeanName.vacPaymentTypeNotDeserveSalary)?resourcesBundle.vacPaymentTypeNotSalary:resourcesBundle.mandatory_offered_salary}"/>
                 </t:panelGroup>
                
                <h:outputText value="#{resourcesBundle.exception_reason}" rendered="#{pageBeanName.sickVacationtypeSelectedForEdit}"/>
                <t:panelGroup colspan="3" rendered="#{pageBeanName.sickVacationtypeSelectedForEdit}">
                    <t:inputText forceId="true" id="exceptionReason" styleClass="textboxmedium" disabled="true"  value="#{(pageBeanName.selectedPageDTO.vacExceptionReasonsDTO!=null) ? pageBeanName.selectedPageDTO.vacExceptionReasonsDTO.name:''}"/>
                </t:panelGroup>
                  
                <h:outputText value="#{resourcesBundle.vac_reason}" rendered="#{pageBeanName.currentOperation=='edit'}" />
                <t:panelGroup colspan="3" rendered="#{pageBeanName.currentOperation=='edit'}" >
                    <t:inputText forceId="true" id="vacReason" styleClass="textboxlarge" value="#{pageBeanName.vacReasonTxt}"/>                        
                </t:panelGroup>
                

                
                <h:outputText value="#{resourcesBundle.addtional_notes}"/>
                <t:panelGroup colspan="3">
                  <t:inputTextarea forceId="true" id="extraInfoTxt"  cols="60" rows="3"  value="#{pageBeanName.vacNotes}"/>
                  <%--t:inputTextarea forceId="true" id="extraInfoTxt"  cols="50" rows="3"  value="#{pageBeanName.currentOperation=='edit' ? pageBeanName.vacNotes : pageBeanName.selectedPageDTO.vacNotes}"/--%>
                </t:panelGroup>
                
         </t:panelGrid>

    <t:inputHidden forceId="true" id="vacationTypeStartDateEdit" rendered="#{pageBeanName.selectedPageDTO.vacVacationTypesDTO != null}" value="#{pageBeanName.selectedPageDTO.vacVacationTypesDTO.fromDate}">
        <f:converter converterId="TimeStampConverter"/>
    </t:inputHidden>
    <t:inputHidden forceId="true" id="vacationTypeEndDateEdit" rendered="#{pageBeanName.selectedPageDTO.vacVacationTypesDTO != null}" value="#{pageBeanName.selectedPageDTO.vacVacationTypesDTO.untilDate}">
        <f:converter converterId="TimeStampConverter"/>
    </t:inputHidden>
    <t:inputHidden forceId="true" id="currentOperationHF" value="#{pageBeanName.currentOperation}"/>
</t:panelGroup>                 
                  
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{resourcesBundle.execute}" action="#{pageBeanName.editAndMAkeDecision}" onclick="x= validatemyForm();if(x){block();} return x;"/>
        
        <t:panelGroup>
            <t:commandButton forceId="true" id="CancelButtonEdit" onblur="setFocusAtFromDateEditx();" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelEdit}" oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,null,null);" reRender="scriptpanel"/>
        </t:panelGroup>
    </t:panelGrid>
    
    <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="fromDateEditx,90,60:untilDateEdity,90,60" />
    <t:inputHidden forceId="true" id="currentYearStartDate" value="#{pageBeanName.currentYearStartDate}">
        <f:converter converterId="TimeStampConverter"/>
    </t:inputHidden>
    <t:inputHidden forceId="true" id="lastYearStartDate" value="#{pageBeanName.lastYearStartDate}">
        <f:converter converterId="TimeStampConverter"/>
    </t:inputHidden>

