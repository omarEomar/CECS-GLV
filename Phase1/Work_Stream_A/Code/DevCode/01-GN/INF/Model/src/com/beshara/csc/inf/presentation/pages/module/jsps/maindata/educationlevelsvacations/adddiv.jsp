<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divAddLookup">
    <t:saveState value="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"
                  rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" value="#{resourcesBundle[pageBeanName.errorMsg]}" styleClass="errMsg"
                  rendered="#{pageBeanName.showErrorMsg}"/>
    <htm:br rendered="#{pageBeanName.success || pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg"/>
    <t:panelGrid columns="2" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01">
        <t:outputText value="#{resourcesBundle.category} " styleClass="lable01"/>
        <t:inputText forceId="true" id="selCategoyName" styleClass="textboxlarge"
                     value='"#{pageBeanName.selCategoyName}" ' disabled="true"/>
        <t:outputText value="#{resourcesBundle.ministry} " styleClass="lable01"/>
        <t:inputText forceId="true" id="selMinistryName" styleClass="textboxlarge"
                     value='"#{pageBeanName.selMinistryName}" ' disabled="true"/>
        <t:outputText value="#{resourcesBundle.periodType} " styleClass="lable01"/>
        <t:inputText forceId="true" id="selperiodName" styleClass="textboxlarge"
                     value='"#{pageBeanName.selperiodName}" ' disabled="true"/>
        <h:outputText value="#{resourcesBundle.periodDesc}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText onkeypress="FireButtonClickOldStyle(event,'SaveButton')" onchange="trimBorders(add_periodDesc);"
                         forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="add_periodDesc"
                         value="#{pageBeanName.pageDTO.periodDesc}" styleClass="textboxlarge"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <h:outputText id="add_periodDesc_ErrorMessage" styleClass="errMsg"/>
        </t:panelGroup>
        <t:outputLabel value="#{resourcesBundle.year} "/>
        <t:panelGroup id="year_grp" forceId="true" colspan="3">
            <t:selectOneMenu forceId="true" id="year" styleClass="Dropdownbox" value="#{pageBeanName.pageDTO.yearCode}"
                             converter="javax.faces.Long">
                <t:selectItems itemLabel="#{year.code.key}" itemValue="#{year.code.yearCode}" var="year"
                               value="#{pageBeanName.yearList}"/>
            </t:selectOneMenu>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        <h:outputLabel value="#{resourcesBundle.bgtProgram}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="bgtProgram_id" styleClass="textboxsmall"
                         value="#{pageBeanName.pageDTO.prgCode}"
                         onkeypress="filterationInputOnKeyPress(event,this,'bgtProgram','bgtProgramErrMSGWrongCode')"
                         onkeyup="enabelFloatOnly(this);"
                         onblur="filterationInputOnBlur(event,this,'bgtProgram','bgtProgramErrMSGWrongCode');"></t:inputText>
            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
            <t:selectOneMenu forceId="true" id="bgtProgram" styleClass="DropdownboxMedium2"
                             value="#{pageBeanName.pageDTO.prgCode}" style="width: 255px;"
                             onchange="filterationDropboxOnChange(event,this,'bgtProgram_id','bgtProgramErrMSGWrongCode');">
                <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select}"/>
                <t:selectItems value="#{pageBeanName.bgtProgramsDTOList}" itemLabel="#{entry.name}"
                               itemValue="#{entry.code.key}" var="entry"/>
            </t:selectOneMenu>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:panelGroup colspan="1" forceId="true" id="bgtProgramErrMSG">
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:outputLabel id="bgtProgramErrMSGWrongCode" value="#{resourcesBundle.MessageForWrongCode}"
                               forceId="true" styleClass="mandatoryAsterisk" style="display:none"/>
                <h:outputText id="bgtProgramErrMSGWrongCodeRequired" styleClass="errMsg"/>
            </t:panelGroup>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.fromdate} " styleClass="lable01"/>
        <t:panelGroup forceId="true" id="addDivFromDatePnl">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" value="#{pageBeanName.pageDTO.fromDate}" id="fromDate" size="20"
                             maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" onkeypress="FireButtonClickOldStyle(event,'SaveButton')">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <h:outputText id="add_fromDate" styleClass="errMsg"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.validUntilDate} " styleClass="lable01"/>
        <t:panelGroup forceId="true" id="addDivuntilDatePnl">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" value="#{pageBeanName.pageDTO.untilDate}" id="untilDate" size="20"
                             maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" onkeypress="FireButtonClickOldStyle(event,'SaveButton')">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <h:outputText id="add_untilDate" styleClass="errMsg"/>
        </t:panelGroup>
    </t:panelGrid>
    <t:outputLabel style="font-size: 4pt;height:3px"/>
    <t:outputText forceId="true" id="invalidDateTitle" value="#{resourcesBundle.invalidDate}"
                  style="visibility:hidden"/>
    <t:outputText forceId="true" id="invalidFromUntilTitle" value="#{resourcesBundle.invalidFromUntil}"
                  style="visibility:hidden"/>
</t:panelGroup>
<t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center">
    <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}"
                     action="#{pageBeanName.save}"
                     onclick="settingFoucsAtDivAdd('add_periodDesc');return validateAddForm();"/>
    <h:panelGroup>
        <t:commandButton forceId="true" id="SaveNewButton" type="button"
                         onclick="clearMsgs();settingFoucsAtDivAdd('add_periodDesc');if(validateAddForm()){saveAndNew()}"
                         styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>
        <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup,OperationBar"
                        oncomplete="settingFoucsAtDivAdd('add_periodDesc');"/>
    </h:panelGroup>
    <h:panelGroup>
        <t:commandButton forceId="true" id="backButtonAddDiv" onblur="settingFoucsAtDivAdd('add_periodDesc');"
                         onclick="backJsFunction(); return false;" styleClass="cssButtonSmall"
                         value="#{globalResources.back}"/>
        <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}"
                        oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'add_periodDesc','myForm:error','add');settingFoucsAtListPage(); "
                        reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
    </h:panelGroup>
</t:panelGrid>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="fromDate,0,75:untilDate,0,75:editFromDate,0,75:editUntilDate,0,100" />

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
      if (document.getElementById('myForm:add_periodDesc_ErrorMessage') != null) {
          document.getElementById('myForm:add_periodDesc_ErrorMessage').innerHTML = "";
      }
      if (document.getElementById('myForm:bgtProgramErrMSGWrongCodeRequired') != null) {
          document.getElementById('myForm:bgtProgramErrMSGWrongCodeRequired').innerHTML = "";
      }
      if (document.getElementById('myForm:add_fromDate') != null) {
          document.getElementById('myForm:add_fromDate').innerHTML = "";
      }
      if (document.getElementById('myForm:add_untilDate') != null) {
          document.getElementById('myForm:add_untilDate').innerHTML = "";
      }
  }

  function validateAddForm() {
      var invalidDateTitle = document.getElementById('invalidDateTitle').innerHTML;
      var invalidFromUntilTitle = document.getElementById('invalidFromUntilTitle').innerHTML;
      var add_periodDesc = validateInputText('add_periodDesc', 'myForm:add_periodDesc_ErrorMessage', null, null, 'successAddLockup');
      var bgtProgram_id = true;
      if (document.getElementById('bgtProgramErrMSGWrongCode').style.display != "none") {
          bgtProgram_id = false;
      }
      else {
          bgtProgram_id = validateInputText('bgtProgram_id', 'myForm:bgtProgramErrMSGWrongCodeRequired', null, null, 'successAddLockup');
      }
      var fromDate = validateInputText('fromDate', 'myForm:add_fromDate', null, null, 'successAddLockup');
      if (fromDate)
          fromDate = isDate('fromDate', 'myForm:add_fromDate', invalidDateTitle, invalidDateTitle, 'true');
      var untilDate = validateInputText('untilDate', 'myForm:add_untilDate', null, null, 'successAddLockup');
      if (untilDate)
          untilDate = isDate('untilDate', 'myForm:add_untilDate', invalidDateTitle, invalidDateTitle, 'true');
      var compareDates = false;
      if (fromDate && untilDate)
          compareDates = compareOptionalTwoDates('fromDate', 'untilDate', 'myForm:add_untilDate', invalidDateTitle, invalidFromUntilTitle, 'true');

      if (add_periodDesc && bgtProgram_id && fromDate && untilDate && compareDates) {
          block();
          return true;
      }
      return false;
  }
</script>