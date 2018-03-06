<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divAddLookup">
    <t:saveState value="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"
                  rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg"
                  rendered="#{pageBeanName.showErrorMsg}"/>
    <htm:br rendered="#{pageBeanName.success || pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg"/>
    <t:panelGrid columns="4" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01">
        <t:outputText value="#{resourcesBundle.hol_year_code}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputText forceId="true" id="yearCodeCodeId" styleClass="textbox"
                         value="#{pageBeanName.selectedYears}" disabled="true"
                         onkeypress="filterationInputOnKeyPress(event,this,'yearCodeListId','errorCodeId0');"
                         onblur="filterationInputOnBlur(event,this,'yearCodeListId','errorCodeId0');"
                         onkeyup="enabelIntegerOnly(this);"/>
            <%--<t:selectOneMenu id="yearCodeListId" forceId="true" value="#{pageBeanName.selectedYears}"
                             styleClass="DropdownboxMedium" style="margin-right:8" disabled="true"
                             onchange="filterationDropboxOnChange(event,this,'yearCodeCodeId','errorCodeId0');">
                <f:selectItem itemLabel="#{resourcesBundle.select_year}" itemValue=""/>
                <t:selectItems var="list" value="#{pageBeanName.yearsList}" itemLabel="#{list.code.key}"
                               itemValue="#{list.code.key}"/>
            </t:selectOneMenu>--%>
            <t:panelGroup colspan="1" forceId="true" id="errorCodePanelId0">
                <t:outputLabel id="errorCodeId0" value="#{resourcesBundle.MessageForWrongCode}" forceId="true"
                               styleClass="errMsg" style="display:none"/>
            </t:panelGroup>
        </t:panelGroup>
        
        <t:outputText value="#{resourcesBundle.hol_from_date}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="fromDateId" size="20" maxlength="200" styleClass="textboxmedium"
                             currentDayCellClass="currentDayCell" value="#{pageBeanName.pageDTO.fromDate}"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c:dateFormatValidator componentToValidate="fromDateId"
                                   errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"
                                   group="addDivVG"/>
            <c:requiredFieldValidator componentToValidate="fromDateId" errorMessage="#{globalResources.missingField}"
                                      display="dynamic" group="addDivVG"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.hol_until_date}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="untilDateId" size="20" maxlength="200" styleClass="textboxmedium"
                             currentDayCellClass="currentDayCell" value="#{pageBeanName.pageDTO.untilDate}"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c:dateFormatValidator componentToValidate="untilDateId"
                                   errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"
                                   group="addDivVG"/>
            <c:requiredFieldValidator componentToValidate="untilDateId" errorMessage="#{globalResources.missingField}"
                                      display="dynamic" group="addDivVG"/>                         
             <c:compareDateValidator componentToCompare="fromDateId" display="dynamic"
                                        componentToValidate="untilDateId" operator="afterOrEqual"
                                        errorMessage="#{resourcesBundle.fromDate_must_smallerThan_untilDate}" highlight="false"
                                         group="addDivVG"/>
             <t:outputText forceId="true" id="notValidDateId" value="#{resourcesBundle.fromDate_untilDate_range} #{pageBeanName.selectedYears}" style="display:none" styleClass="errMsg"/>                            
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.hol_holiday_desc}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputTextarea forceId="true" id="holidayDescId" value="#{pageBeanName.pageDTO.holidayDesc}"
                         cols="50" rows="3"  styleClass="masterinput_hooma"/>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c:requiredFieldValidator componentToValidate="holidayDescId" errorMessage="#{globalResources.missingField}"
                                      display="dynamic" group="addDivVG"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<t:panelGrid styleClass="addDiv_btnsPnlGrd" columns="3" border="0" align="center">
    <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}"
                     action="#{pageBeanName.save}" onclick="settingFoucsAtDivAdd();if( validatemyForm('addDivVG')){return validateDates()}{return false;}"/>
    <h:panelGroup>
        <t:commandButton forceId="true" id="SaveNewButton" type="button"
                         onclick="clearMsgs();settingFoucsAtDivAdd();validSaveAndNew();" styleClass="cssButtonSmall"
                         value="#{globalResources.AddNewButton}"/>
        <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup,OperationBar"
                        oncomplete="settingFoucsAtDivAdd();"/>
    </h:panelGroup>
    <h:panelGroup>
        <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd();"
                         onclick="backJsFunction();" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
        <a4j:jsFunction name="backJsFunction"
                        oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv);settingFoucsAtListPage(); "
                        reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
    </h:panelGroup>
</t:panelGrid>
<script language="javascript" type="text/javascript">
  function clearMsgs() {
      if (document.getElementById('myForm:error') != null) {
          document.getElementById('myForm:error').innerHTML = "";
      }

      if (document.getElementById('myForm:clientErrorMessage') != null) {
          document.getElementById('myForm:clientErrorMessage').innerHTML = "";
      }

      if (document.getElementById('successAddLockup') != null) {
          document.getElementById('successAddLockup').innerHTML = "";
      }
  }

  function validSaveAndNew() {
   document.getElementById('notValidDateId').style.display = "none";
      var valid = validatemyForm('addDivVG');
      if (valid == true) {
          if(validateDates()){
              saveAndNew();
              return true;
          }else{
          document.getElementById('notValidDateId').style.display = "inline";
              return false;
          }
          
          return true;
      }
      else {
          return false;
      }
  }
  
  function validateDates(){
      document.getElementById('notValidDateId').style.display = "none";
      var yearCode = document.getElementById('yearCodeCodeId').value;
      var fromDate = document.getElementById('fromDateId').value;
        fromDate = fromDate.substring(6, 10);
      var untilDate = document.getElementById('untilDateId').value;
        untilDate = untilDate.substring(6, 10);
      if( Number(fromDate) != Number(yearCode) || Number(untilDate) !=Number(yearCode) ){
         document.getElementById('notValidDateId').style.display = "inline";
         return false;
      }else{
          return true;
      }
      
  }
  
</script>