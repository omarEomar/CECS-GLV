<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="searchDiv">
    <t:outputText styleClass="errMsg" forceId="true" id="errorMessage"/>
    <%-- <h:outputText id="clientErrorMessage" styleClass="errMsg"/>--%>
    <t:panelGrid columns="4" rendered="#{yearsBean.pageMode==3}" border="0" width="100%" rowClasses="row01,row02"
                 cellpadding="0" cellspacing="0">
        <h:outputText value="#{resourcesBundle.year_code}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="yearCodeSearch" value="#{pageBeanName.yearCode}"
                         styleClass="textboxmediumB" forceId="true" onkeypress="return runSearch(event)"
                         onblur="setFocusOnlyOnElement('yearNameSaerch');"/>
            <t:panelGroup>
                <c:regularExpressionValidator componentToValidate="yearCodeSearch" display="dynamic"
                                              pattern="/^[0-9+]*$/" errorMessage="#{resourcesBundle.invalid_year}"
                                              highlight="false" active="#{yearsBean.pageMode==3}"/>
            </t:panelGroup>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.year_name}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="yearNameSaerch"
                         onkeypress="return runSearch(event)" value="#{pageBeanName.pageDTO.yearName}"
                         styleClass="textboxmediumB" forceId="true"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.start_date_from}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputCalendar id="f_start_from_search" maxlength="10" value="#{pageBeanName.startDate}"
                             monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass"
                             dayCellClass="dayCellClass" currentDayCellClass="currentDayCellClass"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                             onkeypress="return runSearch(event)" popupTodayString="#{resourcesBundle.today} :"
                             popupWeekString="Wk" renderAsPopup="true" renderPopupButtonAsImage="true"
                             popupDateFormat="dd/MM/yyyy" alt="Calendar" title="Calendar" forceId="true"
                             styleClass="textboxmediumB"></t:inputCalendar>
            <t:panelGroup style="padding-right:5px !important;">
                <c:dateFormatValidator componentToValidate="f_start_from_search" display="dynamic"
                                       errorMessage="#{resourcesBundle.invalid_date}" highlight="false"
                                       active="#{yearsBean.pageMode==3}"/>
            </t:panelGroup>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.end_date_to}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputCalendar id="f_end_date_search" maxlength="10" value="#{pageBeanName.endDate}"
                             monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass"
                             dayCellClass="dayCellClass" currentDayCellClass="currentDayCellClass"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                             onkeypress="return runSearch(event)" popupTodayString="#{resourcesBundle.today} :"
                             popupWeekString="Wk" renderAsPopup="true" renderPopupButtonAsImage="true"
                             popupDateFormat="dd/MM/yyyy" alt="Calendar" title="Calendar" forceId="true"
                             styleClass="textboxmediumB"></t:inputCalendar>
            <t:panelGroup style="padding-right:5px !important;">
                <c:dateFormatValidator componentToValidate="f_end_date_search" display="dynamic"
                                       errorMessage="#{resourcesBundle.invalid_date}" highlight="false"
                                       active="#{yearsBean.pageMode==3}"/>
                <c:compareDateValidator componentToValidate="f_end_date_search" componentToCompare="f_start_from_search"
                                        display="dynamic" operator="after"
                                        errorMessage="#{resourcesBundle.compare_date}" highlight="false"
                                        active="#{yearsBean.pageMode==3}"/>
            </t:panelGroup>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid columns="2" border="0" align="center">
        <t:commandButton id="searchButton2" value="#{globalResources.SearchButton}" type="submit"
                         onclick="return validateSearchform();" action="#{pageBeanName.search}"
                         styleClass="cssButtonSmall"/>
        <h:panelGroup>
            <%--<t:commandButton forceId="true" id="customSearchBackBtn" onblur="settingFoucsAtDivSearch();"
                             onclick="backJsFunction(); return false;" styleClass="cssButtonSmall"
                             value="#{globalResources.back}"/>--%>
            <%--<a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelSearch}"
                            oncomplete="hideLookUpDiv(window.blocker,window.divSearch,null,null,null);foucsAddbuttonOnList();"
                            reRender="divSearch,scriptGenerator"/>--%>
             <t:commandButton forceId="true" id="customSearchBackBtn" onblur="settingFoucsAtSearchDiv();"
                 type="button" value="#{globalResources.back}"
                 onclick="hideLookUpDiv(window.blocker,window.divSearch,null,null,null);" styleClass="cssButtonSmall"/>
        </h:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<f:verbatim>
    <br/>
</f:verbatim>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID"
               value="f_start_from_search,70,110:f_end_date_search,70,110"/>
<script language="javascript" type="text/javascript">
  function runSearch(e) {
      if (e.keyCode == 13) {
          doClick('myForm:searchButton2', null);
      }
  }

  settingFoucsAtDivSearch();

  function validateSearchform() {
      if (document.getElementById('yearCodeSearch').value != "" || document.getElementById('yearNameSaerch').value != "" || document.getElementById('f_start_from_search').value != "" || document.getElementById('f_end_date_search').value != "") {
          if (validatemyForm()) {
              return true;
          }
          else {
              return false;
          }
      }
      else {
          document.getElementById('errorMessage').innerHTML = '${globalResources.selectOneRadio}';
          return false;
      }
  }
</script>