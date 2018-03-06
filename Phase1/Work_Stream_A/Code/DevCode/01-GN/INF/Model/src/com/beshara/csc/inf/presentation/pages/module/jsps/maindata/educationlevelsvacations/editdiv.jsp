<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divEditLookup">
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error"
                  rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>
    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg"/>
    <h:panelGrid columns="1" rendered="#{pageBeanName.showEdit}" width="100%">
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">
            <h:outputText value="#{resourcesBundle.serial}" styleClass="lable01"/>
            <h:inputText value="#{pageBeanName.selectedPageDTO.serial}" styleClass="textboxsmall" disabled="true"/>
            <t:outputText value="#{resourcesBundle.category} " styleClass="lable01"/>
            <t:inputText styleClass="textboxlarge" value='"#{pageBeanName.selCategoyName}"' disabled="true"/>
            <t:outputText value="#{resourcesBundle.ministry} " styleClass="lable01"/>
            <t:inputText styleClass="textboxlarge" value='"#{pageBeanName.selMinistryName}"' disabled="true"/>
            <t:outputText value="#{resourcesBundle.periodType} " styleClass="lable01"/>
            <t:inputText styleClass="textboxlarge" value='"#{pageBeanName.selperiodName}"' disabled="true"/>
            <h:outputText value="#{resourcesBundle.periodDesc}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText onkeypress="FireButtonClickOldStyle(event,'SaveButtonEdit')"
                             onchange="trimBorders(edit_periodDesc);" forceId="true"
                             maxlength="#{pageBeanName.nameMaxLength}" id="edit_periodDesc"
                             value="#{pageBeanName.selectedPageDTO.periodDesc}" styleClass="textboxlarge"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <h:outputText id="edit_periodDesc_ErrorMessage" styleClass="errMsg"/>
            </t:panelGroup>
            <t:outputLabel value="#{resourcesBundle.year} "/>
            <h:outputText value="#{pageBeanName.selectedPageDTO.yearCode}" styleClass="lable01"/>
            <h:outputLabel value="#{resourcesBundle.bgtProgram}" styleClass="lable01"/>
            <h:outputText value="#{pageBeanName.selectedPageDTO.bgtProgramsDTO.name}" styleClass="lable01"/>
            <h:outputText value="#{resourcesBundle.fromdate} " styleClass="lable01"/>
            <t:panelGroup forceId="true" id="editDivFromDatePnl">
                <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" value="#{pageBeanName.selectedPageDTO.fromDate}" id="editFromDate"
                                 size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                                 renderAsPopup="true" align="#{globalResources.align}"
                                 popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" onkeypress="FireButtonClickOldStyle(event,'SaveButtonEdit')">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <h:outputText id="edit_fromDate" styleClass="errMsg"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.validUntilDate} " styleClass="lable01"/>
            <t:panelGroup forceId="true" id="editDivuntilDatePnl">
                <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                                 popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" value="#{pageBeanName.selectedPageDTO.untilDate}" id="editUntilDate"
                                 size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                                 renderAsPopup="true" align="#{globalResources.align}"
                                 popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" onkeypress="FireButtonClickOldStyle(event,'SaveButtonEdit')">
                    <f:converter converterId="SqlDateConverter"/>
                </t:inputCalendar>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <h:outputText id="edit_untilDate" styleClass="errMsg"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.status} " styleClass="lable01"/>
            <t:selectBooleanCheckbox forceId="true" value="#{pageBeanName.booleanStatus}" id="checkBoxLeaf" />
        </h:panelGrid>
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall"
                             value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}"
                             onclick="settingFoucsAtDivEdit();return validateEditForm();"/>
            <t:commandButton forceId="true" id="CancelButtonEdit"
                             onblur="if(isVisibleComponent('lookupEditDiv'))settingFoucsAtDivEdit();"
                             styleClass="cssButtonSmall" value="#{globalResources.back}">
                <a4j:support disableDefault="true" event="onclick" action="#{pageBeanName.unlock}"
                             oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,null,'myForm:errorEdit');settingFoucsAtListPage();return false;"
                             reRender="msgShow"/>
            </t:commandButton>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
<script language="javascript" type="text/javascript">
  function validateEditForm() {
      var invalidDateTitle = document.getElementById('invalidDateTitle').innerHTML;
      var invalidFromUntilTitle = document.getElementById('invalidFromUntilTitle').innerHTML;
      var edit_periodDesc = validateInputText('edit_periodDesc', 'myForm:edit_periodDesc_ErrorMessage', null, null, null);
      var editFromDate = validateInputText('editFromDate', 'myForm:edit_fromDate', null, null, null);
      if (editFromDate)
          editFromDate = isDate('editFromDate', 'myForm:edit_fromDate', invalidDateTitle, invalidDateTitle, 'true');
      var editUntilDate = validateInputText('editUntilDate', 'myForm:edit_untilDate', null, null, null);
      if (editUntilDate)
          editUntilDate = isDate('editUntilDate', 'myForm:edit_untilDate', invalidDateTitle, invalidDateTitle, 'true');
      var editCompareDates = false;
      if (editFromDate && editUntilDate)
          editCompareDates = compareOptionalTwoDates('editFromDate', 'editUntilDate', 'myForm:edit_untilDate', invalidDateTitle, invalidFromUntilTitle, 'true');

      if (edit_periodDesc && editFromDate && editUntilDate && editCompareDates) {
          block();
          return true;
      }
      return false;
  }
</script>