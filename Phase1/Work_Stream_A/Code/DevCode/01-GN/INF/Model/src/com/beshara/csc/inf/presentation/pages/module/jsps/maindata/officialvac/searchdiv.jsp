    







<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:outputText styleClass="errMsg" forceId="true" id="outPutMsg"/>
<t:inputHidden forceId="true" id="firstDayToCompare3" value="#{pageBeanName.firstDayInYears}"/>
<t:inputHidden forceId="true" id="endDayToCompare3" value="#{pageBeanName.endDayInYears}"/>
<t:panelGrid columns="2" width="100%" id="searchPnl" forceId="true" rowClasses="row01,row02" cellpadding="3"
             cellspacing="0">
    <%-- h:outputText value="#{resourcesBundle.Year}" styleClass="lable01"/> <t:panelGroup > <t:selectOneMenu
         forceId="true" id="yearListSearch" value="#{pageBeanName.selectedSearchYear}" styleClass="textboxmedium"
         onblur="document.getElementById('typeListSearch').focus();" > <f:selectItem itemValue=""
         itemLabel="#{resourcesBundle.select}" /> <t:selectItems itemLabel="#{yearList.code.key}"
         itemValue="#{yearList.code.key}" var="yearList" value="#{pageBeanName.yearList}" /> </t:selectOneMenu>
         </t:panelGroup--%>
    <h:outputText value="#{resourcesBundle.officailVacType}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="yearListSearch" value="#{pageBeanName.selectedSearchType}"
                         styleClass="textboxmedium">
            <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select_one_item}"/>
            <t:selectItems itemLabel="#{typeList.name}" itemValue="#{typeList.code.key}" var="typeList"
                           value="#{pageBeanName.typeList}"/>
        </t:selectOneMenu>
    </t:panelGroup>
    <h:outputText id="reqFromDateLabelSearch" value="#{resourcesBundle.fromDate}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                         popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         forceId="true" id="search_pup_from_date" size="25" maxlength="250" styleClass="textbox"
                         currentDayCellClass="currentDayCell" value="#{pageBeanName.searchDTO.fromDate}"
                         renderAsPopup="true" align="#{globalResources.align}"
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
        <c2:compareDateValidator group="startDate" componentToValidate="search_pup_from_date"
                                 componentToCompare="firstDayToCompare3" display="dynamic"
                                 errorMessage="#{resourcesBundle.inf_Compare_Date_Message}" operator="after"
                                 highlight="false"/>
        <c2:compareDateValidator group="startDate" componentToValidate="search_pup_from_date"
                                 componentToCompare="endDayToCompare3" display="dynamic"
                                 errorMessage="#{resourcesBundle.inf_Compare_Date_Message}" operator="before"
                                 highlight="false"/>
        <c2:dateFormatValidator group="startDate" active="#{officialVacListBean.pageMode== 0}" highlight="false"
                                componentToValidate="search_pup_from_date" display="dynamic"
                                errorMessage="#{globalResources.InvalidDataEntryException}"/>
        <%-- onchange="return validateInputCalenderFormate('search_pup_from_date','null','null');"--%>
    </t:panelGroup>
    <h:outputText id="reqEndDateLabelSearch" value="#{resourcesBundle.untilDate}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                         popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                         forceId="true" id="search_pup_End_date" size="25" maxlength="250" styleClass="textbox"
                         currentDayCellClass="currentDayCell" value="#{pageBeanName.searchDTO.untilDate}"
                         renderAsPopup="true" align="#{globalResources.align}"
                         popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
        <c2:compareDateValidator group="endDate" componentToValidate="search_pup_End_date"
                                 componentToCompare="endDayToCompare3" display="dynamic"
                                 errorMessage="#{resourcesBundle.inf_Compare_Date_Message2}" operator="before"
                                 highlight="false"/>
        <c2:compareDateValidator group="endDate" componentToValidate="search_pup_End_date"
                                 componentToCompare="firstDayToCompare3" display="dynamic"
                                 errorMessage="#{resourcesBundle.inf_Compare_Date_Message2}" operator="after"
                                 highlight="false"/>
        <c2:dateFormatValidator group="endDate" active="#{officialVacListBean.pageMode== 0}" highlight="false"
                                componentToValidate="search_pup_End_date" display="dynamic"
                                errorMessage="#{globalResources.InvalidDataEntryException}"/>
        <c2:compareDateValidator group="startAndEndDate" active="#{officialVacListBean.pageMode== 0}"
                                 componentToValidate="search_pup_from_date" componentToCompare="search_pup_End_date"
                                 operator="before" display="dynamic"
                                 errorMessage="#{resourcesBundle.startgreaterThanEnd}"/>
    </t:panelGroup>
</t:panelGrid>
<t:panelGrid columns="2" align="center">
    <t:commandButton id="searchVac" value="#{globalResources.SearchButton}" forceId="true"
                     onclick=" return innerValidateSearchData('yearListSearch','search_pup_from_date','search_pup_End_date','outPutMsg','#{globalResources.enter_at_least_one_field}'); "
                     action="#{pageBeanName.searchVac}" styleClass="cssButtonSmall"/>
    <%-- t:commandButton forceId="true" id="customSearchBackBtn" type="button" value="#{globalResources.back}"
         onclick="hideLookUpDiv(window.blocker,window.divSearch);" styleClass="cssButtonSmall"
         onblur="setFocusAtYearListSearch();"/--%>
    <h:panelGroup>
        <t:commandButton forceId="true" id="customSearchBackBtn" type="button" onclick="cancelSearchJs()"
                         styleClass="cssButtonSmall" value="#{globalResources.back}"
                         onblur="if(isVisibleComponent('divSearch')){setFocusAtYearListSearch();}"/>
        <a4j:jsFunction name="cancelSearchJs" action="#{pageBeanName.resetSearchFilter}"
                        reRender="searchPnl,scriptpanel" oncomplete="hideLookUpDiv(window.blocker,window.divSearch);"/>
    </h:panelGroup>
</t:panelGrid>
<t:saveState value="#{pageBeanName.searchDTO}"/>
<t:saveState value="#{pageBeanName.selectedSearchYear}"/>
<t:saveState value="#{pageBeanName.selectedSearchType}"/>
<script type="text/javascript">
  //  function innerValidateSearchData(fieldsForcedIDsArray, output, msg) {
  //      var submit = false;
  //
  //      if (document.getElementById(output) != null)
  //          document.getElementById(output).innerHTML = '';
  //
  //      var fieldArrayIds = fieldsForcedIDsArray.split(',');
  //
  //      for (var j = 0;j < fieldArrayIds.length;j++) {
  //          var x = fieldArrayIds[j];
  //          if (document.getElementById(x) != null && document.getElementById(fieldArrayIds[j]).value != '') {
  //              submit = true;
  //              break;
  //          }
  //      }
  //
  //      if (submit == false && document.getElementById(output) != null)// all fileds empty so it will show error message
  //          document.getElementById(output).innerHTML = msg;
  //      else {
  //          return validatemyForm();
  //      }
  //      return submit;
  //  }
  function innerValidateSearchData(combo, startDate, endDate, output, msg) {
      var submit = false;

      var outputElm = document.getElementById(output);
      var comboElm = document.getElementById(combo);
      var startDateElm = document.getElementById(startDate);
      var endDateElm = document.getElementById(endDate);

      if (outputElm != null) {
          outputElm.innerHTML = '';
      }

      if (!(comboElm.value == "" && startDateElm.value == "" && endDateElm.value == "")) {
          submit = true;
      }

      // all fileds empty so it will show error message
      if (submit == false) {
          if (outputElm) {
              outputElm.innerHTML = msg;
          }
      }
      else {
          if (startDateElm.value != "") {
              if (endDateElm.value == "") {
                  return validatemyForm('startDate');
              }
              else {
                  return validatemyForm('startAndEndDate');
              }
          }
          else if (endDateElm.value != "" && startDateElm.value == "") {
              return validatemyForm('endDate');
          }
      }
      return submit;
  }
</script>