<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divEditLookup">
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error"
                  rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>
    <%--<h:outputText id="clientErrorMessageEdit" styleClass="errMsg"/>--%>
    <t:panelGrid columns="4" rendered="#{pageBeanName.showEdit}" border="0" rowClasses="row02,row01" width="100%"
                 cellpadding="3" cellspacing="0">
        <t:outputText value="#{resourcesBundle.hol_year_code}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputText forceId="true" id="yearCodeCodeEditId" styleClass="textbox"
                         value="#{pageBeanName.selectedYears}" disabled="true" 
                         onkeypress="filterationInputOnKeyPress(event,this,'yearCodeListEditId','errorCodeId_0');"
                         onblur="filterationInputOnBlur(event,this,'yearCodeListEditId','errorCodeId_0');"
                         onkeyup="enabelIntegerOnly(this);"/>
            <%--<t:selectOneMenu id="yearCodeListEditId" forceId="true" value="#{pageBeanName.selectedYears}"
                             styleClass="DropdownboxMedium" style="margin-right:8" disabled="true"
                             onchange="filterationDropboxOnChange(event,this,'yearCodeCodeEditId','errorCodeId_0');">
                <f:selectItem itemLabel="#{resourcesBundle.select}" itemValue=""/>
                <t:selectItems var="list" value="#{pageBeanName.yearsList}" itemLabel="#{list.name}"
                               itemValue="#{list.code.key}"/>
            </t:selectOneMenu>--%>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.hol_from_date}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" disabled="true"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="fromDateEditId" size="20" maxlength="200" styleClass="textboxmedium"
                             currentDayCellClass="currentDayCell" value="#{pageBeanName.selectedPageDTO.fromDate}"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c:dateFormatValidator componentToValidate="fromDateEditId"
                                   errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"
                                   group="editDivVG"/>
            <c:requiredFieldValidator componentToValidate="fromDateEditId"
                                      errorMessage="#{globalResources.missingField}" display="dynamic"
                                      group="editDivVG"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.hol_until_date}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="untilDateEditId" size="20" maxlength="200" styleClass="textboxmedium"
                             currentDayCellClass="currentDayCell" value="#{pageBeanName.selectedPageDTO.untilDate}"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c:dateFormatValidator componentToValidate="untilDateEditId"
                                   errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"
                                   group="editDivVG"/>
            <c:requiredFieldValidator componentToValidate="untilDateEditId"
                                      errorMessage="#{globalResources.missingField}" display="dynamic"
                                      group="editDivVG"/>
                                      
            <c:compareDateValidator componentToCompare="fromDateEditId" display="dynamic"
                                        componentToValidate="untilDateEditId" operator="afterOrEqual"
                                        errorMessage="#{resourcesBundle.fromDate_must_smallerThan_untilDate}" highlight="false"
                                         group="editDivVG" />
           <t:outputText forceId="true" id="notValidDateEditId" value="#{resourcesBundle.fromDate_untilDate_range} #{pageBeanName.selectedYears}" style="display:none" styleClass="errMsg"/>                            
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.hol_holiday_desc}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputTextarea forceId="true" id="holidayDescEditId" value="#{pageBeanName.selectedPageDTO.holidayDesc}"
                         cols="50" rows="3"  styleClass="masterinput_hooma"/>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c:requiredFieldValidator componentToValidate="holidayDescEditId" errorMessage="#{globalResources.missingField}"
                                      display="dynamic" group="editDivVG"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.hol_status}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:selectBooleanCheckbox forceId="true" id="statusEditId" value="#{pageBeanName.status}"
                                     title="#{resourcesBundle.hol_status}"/>
        </t:panelGroup>
        
    </t:panelGrid>
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall"
                         value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}"
                         onclick="settingFoucsAtDivEdit();if( validatemyForm('editDivVG')){return validateDatesEdit();}else{return false;}"/>
        <t:commandButton forceId="true" id="CancelButtonEdit"
                         onblur="if(isVisibleComponent('lookupEditDiv'))settingFoucsAtDivEdit();"
                         styleClass="cssButtonSmall" value="#{globalResources.back}">
            <a4j:support disableDefault="true" event="onclick" action="#{pageBeanName.unlock}"
                         oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv);settingFoucsAtListPage();return false;"
                         reRender="msgShow"/>
        </t:commandButton>
    </t:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
<script language="javascript" type="text/javascript">
function validateDatesEdit(){
      document.getElementById('notValidDateEditId').style.display = "none";
      var yearCode = document.getElementById('yearCodeCodeEditId').value;
      var fromDate = document.getElementById('fromDateEditId').value;
        fromDate = fromDate.substring(6, 10);
      var untilDate = document.getElementById('untilDateEditId').value;
        untilDate = untilDate.substring(6, 10);
      if( Number(fromDate) != Number(yearCode) || Number(untilDate) !=Number(yearCode) ){
         document.getElementById('notValidDateEditId').style.display = "inline";
         return false;
      }else{
          return true;
      }
      
  }
  
  </script>