<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:saveState value="#{pageBeanName.success}"/>
<t:saveState value="#{pageBeanName.budgetStartAddDate}"/>
<t:saveState value="#{pageBeanName.budgetEndAddDate}"/>
<t:panelGroup forceId="true" id="divAddLookup" onkeydown="TriggeredKey(this)">
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"
                  rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg"
                  rendered="#{pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg"/>
    <t:panelGrid columns="4" rendered="#{yearsBean.pageMode==1}" border="0" width="100%" rowClasses="row01,row02"
                 columnClasses="divCol1,divCol2" cellpadding="0" cellspacing="0">
        <h:outputText value="#{resourcesBundle.year_code}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="yearCode" value="#{pageBeanName.yearCode}"
                         styleClass="textboxmediumB" forceId="true"
                         onblur="setFocusOnlyOnElement('yearName'); fillYearStartAndEndDateADD();"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:panelGroup>
                <c:requiredFieldValidator componentToValidate="yearCode" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{yearsBean.pageMode==1}"/>
                <c:regularExpressionValidator componentToValidate="yearCode" display="dynamic" pattern="/^[0-9+]*$/"
                                              errorMessage="#{resourcesBundle.invalid_year}" highlight="false"
                                              active="#{yearsBean.pageMode==1}"/>
            </t:panelGroup>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.year_name}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="yearName"
                         value="#{pageBeanName.pageDTO.yearName}" styleClass="textboxmediumB" forceId="true"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="yearName" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      active="#{yearsBean.pageMode==1}"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.start_date}" styleClass="lable01"/>
        <t:inputText id="start_from" forceId="true" disabled="true" styleClass="textboxmediumB"/>
        <%-- <t:panelGroup> <t:inputCalendar id="start_from" maxlength="10" value="#{pageBeanName.startDate}"
             monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass" dayCellClass="dayCellClass"
             currentDayCellClass="currentDayCellClass" popupTodayString="#{resourcesBundle.today} :" disabled="true"
             popupWeekString="Wk" renderAsPopup="true" renderPopupButtonAsImage="true" popupDateFormat="yyyy/MM/dd"
             alt="Calendar" title="Calendar" forceId="true" styleClass="textboxsmall"> </t:inputCalendar> </t:panelGroup>--%>
        <t:outputText value="#{resourcesBundle.end_date}" styleClass="lable01"/>
        <t:inputText id="end_date" forceId="true" disabled="true" styleClass="textboxmediumB"
                     style="margin-right:-5px"/>
        <%-- <t:panelGroup> <t:inputCalendar id="end_date" maxlength="10" value="#{pageBeanName.endDate}"
             monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass" dayCellClass="dayCellClass"
             currentDayCellClass="currentDayCellClass" popupTodayString="#{resourcesBundle.today} :" disabled="true"
             popupWeekString="Wk" renderAsPopup="true" renderPopupButtonAsImage="true" popupDateFormat="yyyy/MM/dd"
             alt="Calendar" title="Calendar" forceId="true" styleClass="textboxsmall" > </t:inputCalendar>
             </t:panelGroup>--%>
    </t:panelGrid>
    <htm:fieldset id="financialYear">
        <htm:legend>
            <h:outputText value="#{resourcesBundle.financial_year}" styleClass="lable01"/>
        </htm:legend>
        <t:panelGrid columns="4" rendered="#{yearsBean.pageMode==1}" width="100%" cellpadding="0" cellspacing="0"
                     rowClasses="row01," columnClasses="divCol1,divCol2,divCol1_inf2,divCol2">
            <t:outputText value="#{resourcesBundle.start_date}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputCalendar id="f_start_from" maxlength="10" value="#{pageBeanName.budgetStartAddDate}"
                                 monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass"
                                 dayCellClass="dayCellClass" currentDayCellClass="currentDayCellClass"
                                 popupTodayString="#{resourcesBundle.today} :" popupWeekString="Wk" renderAsPopup="true"
                                 renderPopupButtonAsImage="true" popupDateFormat="dd/MM/yyyy" alt="Calendar"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" title="Calendar"
                                 forceId="true" styleClass="textboxmediumB"></t:inputCalendar>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            </t:panelGroup>
            <t:outputText value="#{resourcesBundle.end_date}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputCalendar id="f_end_date" maxlength="10" value="#{pageBeanName.budgetEndAddDate}"
                                 monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass"
                                 dayCellClass="dayCellClass" currentDayCellClass="currentDayCellClass"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                                 popupTodayString="#{resourcesBundle.today} :" popupWeekString="Wk" renderAsPopup="true"
                                 renderPopupButtonAsImage="true" popupDateFormat="dd/MM/yyyy" alt="Calendar"
                                 title="Calendar" forceId="true" styleClass="textboxmediumB"></t:inputCalendar>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            </t:panelGroup>
            <t:outputText/>
            <t:panelGroup>
                <c:requiredFieldValidator componentToValidate="f_start_from" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{yearsBean.pageMode==1}"/>
                <c:dateFormatValidator componentToValidate="f_start_from" display="dynamic"
                                       errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"
                                       active="#{yearsBean.pageMode==1}"/>
            </t:panelGroup>
            <t:outputText/>
            <t:panelGroup>
                <c:requiredFieldValidator componentToValidate="f_end_date" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{yearsBean.pageMode==1}"/>
                <c:compareDateValidator componentToValidate="f_end_date" componentToCompare="f_start_from"
                                        display="dynamic" operator="after"
                                        errorMessage="#{resourcesBundle.compare_date}" highlight="false"
                                        active="#{yearsBean.pageMode==1}"/>
                <c:dateFormatValidator componentToValidate="f_end_date" display="dynamic"
                                       errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"
                                       active="#{yearsBean.pageMode==1}"/>
            </t:panelGroup>
        </t:panelGrid>
    </htm:fieldset>
</t:panelGroup>
<t:panelGrid columns="3" border="0" align="center">
    <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}"
                     onclick="return validatemyForm();" action="#{yearsBean.save}"/>
    <h:panelGroup>
        <t:commandButton type="button" forceId="true" id="SaveNewButton" onclick="validateSaveAndNewClientValidator();"
                         styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>
        <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup"
                        oncomplete="setFocusOnlyOnElement('yearCode');"/>
    </h:panelGroup>
    <h:panelGroup>
        <t:commandButton forceId="true" id="backButtonAddDiv" onclick="backJsFunction(); return false;"
                         styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="settingFoucsAtDivAdd();"/>
        <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}"
                        oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:yearCode','myForm:error','add');foucsAddbuttonOnList();"
                        reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,editButton"/>
    </h:panelGroup>
</t:panelGrid>
<script language="javascript" type="text/javascript">
  function fillYearStartAndEndDateADD() {
      var yearId = document.getElementById("yearCode");
      var yearValue = yearId.value;
      var nextYear = parseInt(yearValue) + 1;

      var numbers = /^[0-9+]*$/;

      var startDate = document.getElementById("start_from");
      var endDate = document.getElementById("end_date");
      var budgetStartAddDate = document.getElementById("f_start_from");
      var budgetEndAddDate = document.getElementById("f_end_date");

      if (yearValue != null && yearValue != "" && yearValue.match(numbers)) {
          //startDate.value = yearValue + "/01/01";
          startDate.value = "01/01/" + yearValue;

          // endDate.value = yearValue + "/12/31";
          endDate.value = "31/12/" + yearValue;

          // budgetStartAddDate.value = yearValue + "/04/01";
          budgetStartAddDate.value = "01/04/" + yearValue;

          budgetEndAddDate.value = "31/03/" + nextYear;

          // budgetEndAddDate.value = nextYear + "/03/31";
          return;
      }
      else {
          startDate.value = "";
          endDate.value = "";
          budgetStartAddDate.value = "";
          budgetEndAddDate.value = "";
          return;
      }
  }

  function stopRKey(evt) {
      var evt = (evt) ? evt : ((event) ? event : null);
      var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
      if ((evt.keyCode == 13) && (node.type == "text")) {
          return false;
      }
  }

  document.onkeypress = stopRKey;
</script>