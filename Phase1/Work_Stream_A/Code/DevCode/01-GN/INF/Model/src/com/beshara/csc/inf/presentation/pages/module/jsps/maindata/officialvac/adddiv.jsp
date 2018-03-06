<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
        
    
<t:panelGroup forceId="true" id="divAddLookup">

    <t:saveState value="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>

    <t:outputText forceId="true" id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
    
    <%--<c2:compareValidator active="true"
                                    highlight="false" componentToValidate="orignalNumberOfDays"
                                    componentToCompare="NumberOfDays" operator="eq" display="dynamic"
                                    errorMessage="#{resourcesBundle.wrongNumberOfDays}"/>--%>
                                    
    <t:panelGrid columns="2" rowClasses="row01,row02" cellpadding="3" cellspacing="0" width="100%">
        <h:outputText value="#{resourcesBundle.Year}" styleClass="lable01"/>
        <t:inputText id="NameAdd" forceId="true" value="#{pageBeanName.yearName}" readonly="true" styleClass="textbox"
                     disabled="true"/>
        <h:outputText value="#{resourcesBundle.officailVacType}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText styleClass="textbox" disabled="true" rendered="#{pageBeanName.editMode}"
                         value="#{pageBeanName.pageDTO.holidayTypesDTO.name}"/>
            <t:selectOneMenu forceId="true" id="typeListAdd" value="#{pageBeanName.selectedType}"
                             rendered="#{!pageBeanName.editMode}" styleClass="textboxmedium"
                             onchange="changeSelectedVacType();">
                <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                              itemLabel="#{resourcesBundle.select_one_item}"/>
                <t:selectItems itemLabel="#{typeList.name}" itemValue="#{typeList.code.key}" var="typeList"
                               value="#{pageBeanName.typeListAdd}"/>
                <a4j:jsFunction action="#{pageBeanName.selectedTypeChange}" name="changeSelectedVacType"
                                reRender="orignalNumberOfDays,vacDuration,fromDateAdd,untilDateAdd"/>
            </t:selectOneMenu>
            <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{!pageBeanName.editMode}"/>
            <t:outputText value="&lt;br/>" escape="false"/>
            <c2:compareValidator active="#{!officialVacListBean.editMode && officialVacListBean.pageMode== 1}"
                                 highlight="false" componentToValidate="typeListAdd" componentToCompare="virtualvalueId"
                                 operator="not" display="dynamic" errorMessage="#{globalResources.missingField}"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.off_vav_no_of_days}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText id="vacDuration" forceId="true" value="#{pageBeanName.selectedTypeDays}" readonly="true" styleClass="textbox" disabled="true"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.fromDate}" styleClass="lable01"/>
        <t:panelGroup>
            <%-- onchange="javascript :y = true; if (y== false){ return false;} else { return
                 checkDateEqualSelectedYear('fromDateAdd','NameAdd','outPutMsgAdd','#{resourcesBundle.selectedDateMustbeWithinSelectedYear}');}"--%>
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="fromDateAdd" size="25" maxlength="10" styleClass="textbox"
                             currentDayCellClass="currentDayCell" value="#{pageBeanName.pageDTO.fromDate}"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                             onkeyup="if(checkDate()){calcUntilDate();}else{return false ;}"
                             onchange="if(checkDate()){calcUntilDate();}else{return false ;}">
                <f:converter converterId="SqlDateConverter"/>
                <a4j:jsFunction name="calcUntilDate" action="#{pageBeanName.calcUntilDate}" reRender="untilDateAdd,divAddLookup,btnID"/>
            </t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:outputText styleClass="errMsg" forceId="true" id="outPutMsgAdd"/>
            
            <t:outputText value="&lt;br />" escape="false"/>
            <t:outputText id="errorToDateMessage1" forceId="true" styleClass="errMsg" value="#{globalResources.InvalidDataEntryException}" style="display:none;"/>
            <c2:requiredFieldValidator active="#{officialVacListBean.pageMode== 1 || officialVacListBean.pageMode==2}"
                                       componentToValidate="fromDateAdd" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" highlight="false"/>
            <c2:dateFormatValidator active="#{officialVacListBean.pageMode== 1 || officialVacListBean.pageMode==2}"
                                    highlight="false" componentToValidate="fromDateAdd" display="dynamic"
                                    errorMessage="#{globalResources.InvalidDataEntryException}"/>
            <c2:compareDateValidator componentToValidate="fromDateAdd" componentToCompare="firstDayToCompare"
                                     display="dynamic" errorMessage="#{resourcesBundle.inf_Compare_Date_Message}"
                                     active="#{officialVacListBean.pageMode== 1 || officialVacListBean.pageMode==2}"
                                     operator="afterOrEqual" highlight="false"/>
            <c2:compareDateValidator componentToValidate="fromDateAdd" componentToCompare="endDayToCompare"
                                     display="dynamic" errorMessage="#{resourcesBundle.inf_Compare_Date_Message}"
                                     active="#{officialVacListBean.pageMode== 1 || officialVacListBean.pageMode==2}"
                                     operator="before" highlight="false"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.untilDate}" styleClass="divtext"/>
        <t:panelGroup>
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="untilDateAdd"
                             size="25" maxlength="10"
                             styleClass="textbox" currentDayCellClass="currentDayCell"
                             value="#{pageBeanName.pageDTO.untilDate}" renderAsPopup="true"
                             align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                             renderPopupButtonAsImage="true" disabled="true">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <%--
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:outputText styleClass="errMsg" forceId="true" id="outPutMsgAddUntill"/>
            <t:outputText value="&lt;br />" escape="false"/>
            
            <c2:requiredFieldValidator componentToValidate="untilDateAdd"
                                       active="#{officialVacListBean.pageMode== 1 || officialVacListBean.pageMode==2}"
                                       display="dynamic" errorMessage="#{globalResources.missingField}"
                                       highlight="false"/>
            <c2:compareDateValidator componentToValidate="fromDateAdd" componentToCompare="untilDateAdd"
                                     active="#{officialVacListBean.pageMode== 1 || officialVacListBean.pageMode==2}"
                                     operator="before" display="dynamic"
                                     errorMessage="#{resourcesBundle.startgreaterThanEnd}"/>
            <c2:dateFormatValidator active="#{officialVacListBean.pageMode== 1 || officialVacListBean.pageMode==2}"
                                    highlight="false" componentToValidate="untilDateAdd" display="dynamic"
                                    errorMessage="#{globalResources.InvalidDataEntryException}"/>
            <c2:compareDateValidator componentToValidate="untilDateAdd" componentToCompare="endDayToCompare"
                                     display="dynamic" errorMessage="#{resourcesBundle.inf_Compare_Date_Message2}"
                                     operator="before" highlight="false"/>
            <c2:compareDateValidator componentToValidate="untilDateAdd" componentToCompare="firstDayToCompare"
                                     display="dynamic" errorMessage="#{resourcesBundle.inf_Compare_Date_Message2}"
                                     operator="after" highlight="false"/>
            --%>                         
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.officialVac_valid}" styleClass="divtext"
                      rendered="#{pageBeanName.editMode}"/>
        <t:selectBooleanCheckbox forceId="true" styleClass="radio1" id="radioBtn"
                                 value="#{pageBeanName.pageDTO.statusBoolean}" rendered="#{pageBeanName.editMode}">
            <f:selectItem itemLabel="#{resourcesBundle.officialVac_valid}" itemValue="#{true}"/>
            <f:selectItem itemLabel="#{resourcesBundle.officialVac_not_active}" itemValue="#{false}"/>
        </t:selectBooleanCheckbox>
    </t:panelGrid>
    <t:panelGrid forceId="true" id="btnID" columns="3" border="0" align="center">                         
        <t:commandButton  forceId="true" id="SaveButton" onclick="return validateForSave();" styleClass="cssButtonSmall" rendered="#{!pageBeanName.editMode}" value="#{globalResources.SaveButton}" action="#{pageBeanName.saveOfficialVac}"/>
        <t:commandButton forceId="true" id="SaveButtonEdit" onclick= "return validateSaveAndNewClientValidator1();"  styleClass="cssButtonSmall" rendered="#{pageBeanName.editMode}" value="#{globalResources.SaveButton}" action="#{pageBeanName.editOfficialVac}" />
        <h:panelGroup rendered="#{!pageBeanName.editMode}" >
            <t:commandButton forceId="true" id="SaveNewButton" type="button" onclick="return validateForSaveAndNew();"  styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>
            <a4j:jsFunction name="saveAndNew"  action="#{pageBeanName.saveAndNew}" reRender="divAddLookup,scriptpanel,OperationBar" oncomplete="settingFoucsAtDivAdd();"/>
        </h:panelGroup>
        
        <t:commandButton forceId="true" id="backButtonAddDiv" value="#{globalResources.back}" styleClass="cssButtonSmall" action="#{pageBeanName.cancelAdd}" onblur="settingFoucsAtDivAdd();">
        <%--<a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}" 
        oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:typeListAdd','myForm:error','add');foucsAddbuttonOnList();" 
        reRender="editButton,divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize"/>--%>
        </t:commandButton>
        <%--t:commandButton  value="#{globalResources.back}" styleClass="cssButtonSmall" action="#{pageBeanName.cancelAdd}" rendered="#{pageBeanName.editMode}" onblur=""/--%>
    </t:panelGrid>

</t:panelGroup>
<t:inputHidden forceId="true" id="firstDayToCompare" value="#{pageBeanName.firstDayInYears}" />
  
<t:inputHidden forceId="true" id="endDayToCompare" value="#{pageBeanName.endDayInYears}"/>
  
  <%--<t:outputText forceId="true" id="firstDayInss" value="#{pageBeanName.firstDayInYears}"/>--%>
  <%--<t:outputText forceId="true" id="endDayInss" value="#{pageBeanName.endDayInYears}"/>--%>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="fromDateAdd,100,100:untilDateAdd,100,100:search_pup_from_date,100,130:search_pup_End_date,100,130"/>



<t:inputHidden id="orignalNumberOfDays" value="#{pageBeanName.selectedTypeDays}" />   
  <h:inputHidden  id="NumberOfDays" styleClass="divtext"/>
 

<script type="text/javascript">
  function clearMsgFields() {
      var component = document.getElementById('error');
      if (component != null) {
          component.innerText = '';
      }
      component = document.getElementById('successAddLockup');
      if (component != null) {
          component.innerText = '';
      }
      component = document.getElementById('outPutMsgAdd');
      if (component != null) {
          component.innerText = '';
      }
      component = document.getElementById('outPutMsgAddUntill');
      if (component != null) {
          component.innerText = '';
      }
  }

 

  function validateForSave() {
      var x = checkDate();
      if(!x){
          return false ;
      }else{
       document.getElementById('errorToDateMessage1').style.display = 'none';
      calcDaysBetween("fromDateAdd", "untilDateAdd", "myForm:NumberOfDays")
      var y = validatemyForm();
      return  y;
      }
  }

  function validateForSaveAndNew() {
      var x = checkDate();
      if(!x){
          return false ;
      }else{
       document.getElementById('errorToDateMessage1').style.display = 'none';
       var y = validatemyForm();
       if (y) {
          saveAndNew();
       }
       else {
          return false;
       }
      }
      return true;
  }

  function validateSaveAndNewClientValidator1() {
      var x = checkDate();
      if(!x){
          return false ;
      }else{
       document.getElementById('errorToDateMessage1').style.display = 'none';
       calcDaysBetween("fromDateAdd", "untilDateAdd", "myForm:NumberOfDays");
       var validForm = validatemyForm();
       if (validForm) {
           saveAndNew();
       }
       else {
          return false;
       }
      }

      return true;
  }

  function onBlurBackBtn() {
      if (document.getElementById('SaveButton') != null) {
          setFocusAtTypeListAdd();
      }
      else {
          setFocusAtFromDateAdd();
      }
  }

  /********************************************* calc days  ***********************************************************/

  function checkDateEqualSelectedYear(dateId, compareYearID, output, errorMsg) {

      var startDate;
      var compareDateYear;
      var sdy;

      document.getElementById('outPutMsgAdd').innerHTML = '';
      document.getElementById('outPutMsgAddUntill').innerHTML = '';

      return checkCurrentSelectedYear(dateId, compareYearID, output, errorMsg);

  }

  function checkCurrentSelectedYear(dateId, compareYearID, output, errorMsg) {
      document.getElementById('outPutMsgAdd').innerHTML = '';
      document.getElementById('outPutMsgAddUntill').innerHTML = '';

      if (document.getElementById(dateId) != null && document.getElementById(compareYearID) != null && document.getElementById(dateId).value != '') {
          startDate = document.getElementById(dateId).value;
          compareDateYear = document.getElementById(compareYearID).value;
          sdy = startDate.substring(startDate.lastIndexOf("/") + 1, startDate.length);
          if (eval(sdy) != eval(compareDateYear)) {
              document.getElementById(output).innerHTML = errorMsg;
              //document.getElementById(dateId).value='';
              return false;
          }
          else {
              document.getElementById(output).innerHTML = '';
          }
      }

      return true;
  }
  
  function checkDate() {
  document.getElementById('errorToDateMessage1').style.display = 'none';
  var testStr = document.getElementById('fromDateAdd');
  if(typeof (testStr) != 'undefined' && testStr != null && testStr.value!=''){
   var pattern = /\b(\d{2})\/(\d{2})\/(\d{4})\b/;
   var result = testStr.value.match(pattern);
   if (result != null){
      return true;
   }
   else{
       document.getElementById('errorToDateMessage1').style.display = 'inline';
       document.getElementById('outPutMsgAdd').innerHTML = '';
       document.getElementById('untilDateAdd').innerHTML='';
       return false;
   }
  } 
  return true ;
}
</script>



