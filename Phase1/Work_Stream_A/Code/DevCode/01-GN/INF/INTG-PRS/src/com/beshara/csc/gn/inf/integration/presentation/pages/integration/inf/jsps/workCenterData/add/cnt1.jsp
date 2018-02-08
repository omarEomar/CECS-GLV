<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%-- <htm:style> .divContent1Dynamic{background-color: #cddff3} </htm:style>--%>
<jsp:include page="/integration/org/jsps/ministry/searchministrieswithpagingdiv.jsp"/>
<t:saveState value="#{addExperienceListBean.activeDurationYears}"/>
<t:saveState value="#{addExperienceListBean.activeDurationMonths}"/>
<t:saveState value="#{addExperienceListBean.activeDurationDays}"/>
<t:saveState value="#{addExperienceListBean.renderDurationErrMsg}"/>
<t:panelGrid forceId="true" id="MsgPanel" align="center">
    <t:outputText forceId="true" id="error" value="#{pageBeanName.errorMsg}" styleClass="errMsg"
                  rendered="#{pageBeanName.showErrorMsg}"/>
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"
                  rendered="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="renderDurationErrMsgId" value="#{infIntegrationBundle.DurationErrMsg}"
                  styleClass="errMsg" rendered="#{pageBeanName.renderDurationErrMsg}"/>
</t:panelGrid>
<t:panelGroup forceId="true" id="divaddExperPage" style="width:100%">
    <t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="colu1,colu2" cellspacing="0" width="100%"
                 forceId="true" id="experienceCntPanel" columns="6">
        <t:panelGroup colspan="6">
            <t:panelGrid id="pnlgrd_dec_radio" columns="1" align="center">
                <t:selectOneRadio id="vacSicktypeRadioButton" value="#{pageBeanName.govTypeCode}" styleClass="lable01"
                                  converter="javax.faces.Long">
                    <a4j:support event="onclick" action="#{pageBeanName.prepareByGovType}"
                                 reRender="divaddExperPage, myFormGrp"/>
                    <f:selectItem itemLabel="#{infIntegrationBundle.gov_type}" itemValue="#{pageBeanName.govFlag}"/>
                    <f:selectItem itemLabel="#{infIntegrationBundle.non_gov_type}"
                                  itemValue="#{pageBeanName.nonGovFlag}"/>
                </t:selectOneRadio>
            </t:panelGrid>
        </t:panelGroup>
        <h:outputText id="category_name" value="#{infIntegrationBundle.category}" styleClass="lable01"/>
        <t:panelGroup colspan="2">
            <t:inputText forceId="true" id="categoryId" styleClass="filteration_input_text"
                         value="#{pageBeanName.selectedCategory}" maxlength="3"
                         onkeypress="filterationInputOnKeyPress(event,this,'categoryList','errorCodeId',changeCategoryVal,'ministryId');"
                         onblur="filterationInputOnBlur(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                         onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
                <a4j:jsFunction name="changeCategoryVal" actionListener="#{pageBeanName.filterByCategory}"
                                reRender="categoryList,ministryListID,ministryId,errorCodePanelId,errorCodePanelId2,workCenterPnl,myForm:searchMinistryBtn,minFilterationId,ministryName"/>
            </t:inputText>
            <t:selectOneMenu id="categoryList" forceId="true" styleClass="DropdownboxMedium2"
                             value="#{pageBeanName.selectedCategory}" style="width:240px"
                             onchange="filterationDropboxOnChange(event,this,'categoryId','errorCodeId',changeCategoryValD);">
                <f:selectItem itemValue="" itemLabel="#{infIntegrationBundle.selectOne}"/>
                <t:selectItems value="#{pageBeanName.categoryList}" var="categoryList"
                               itemValue="#{categoryList.code.key}" itemLabel="#{categoryList.name}"/>
                <a4j:jsFunction name="changeCategoryValD" actionListener="#{pageBeanName.filterByCategory}"
                                reRender="categoryId,ministryListID,ministryId,errorCodePanelId,errorCodePanelId2,workCenterPnl,myForm:searchMinistryBtn,minFilterationId,ministryName"/>
            </t:selectOneMenu>
            <%-- <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>--%>
            <t:panelGroup forceId="true" id="errorCodePanelId" style="display:block;width:250px;">
                <t:outputLabel id="errorCodeId" value="#{infIntegrationBundle.MessageForWrongCode}" forceId="true"
                               styleClass="error" style="display:none"/>
            </t:panelGroup>
        </t:panelGroup>
        <%-- minisry--%>
        <h:outputText id="ministry_name" value="#{infIntegrationBundle.ministry}" styleClass="lable01"/>
        <t:panelGroup colspan="2">
            <t:inputText forceId="true" id="minFilterationId" styleClass="filteration_input_text" maxlength="6"
                         disabled="#{pageBeanName.selectedCategory == null || pageBeanName.selectedCategory == ''}"
                         onkeypress="filterDivInputOnKeyPress(event,this,changeMinistry,'workCenterId');"
                         onkeyup="enabelIntegerOnly(this);" value="#{pageBeanName.selectedMinistry}">
                <a4j:jsFunction name="changeMinistry" actionListener="#{pageBeanName.changeMinistry}"
                                reRender="OperationBar,ministryName,errorCodePanelId2,workCenterPnl"/>
            </t:inputText>
            <f:verbatim>&nbsp;</f:verbatim>
            <t:inputText forceId="true" id="ministryName" disabled="true" styleClass="textboxmedium"
                         value="#{pageBeanName.selectedMiniName}"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <a4j:commandButton id="searchMinistryBtn" value="..." action="#{pageBeanName.openSearchMinistryDiv}"
                               styleClass="cssButtonSmall"
                               disabled="#{pageBeanName.selectedCategory == null || pageBeanName.selectedCategory == ''}"
                               reRender="searchMinistryDiv"
                               oncomplete="changeVisibilityDiv(window.blocker,window.searchMinistryDiv);"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c2:requiredFieldValidator componentToValidate="minFilterationId" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" highlight="false"/>
            <t:panelGroup forceId="true" id="errorCodePanelId2">
                <t:outputText id="errorCodeId2" value="#{infIntegrationBundle.MessageForWrongCode}" forceId="true"
                              styleClass="validation_error" style="color: #FF0000;"
                              rendered="#{pageBeanName.wrongMinCode}"/>
            </t:panelGroup>
        </t:panelGroup>
        <h:outputText value="#{infIntegrationBundle.workCenter}" styleClass="lable01"/>
        <t:panelGroup colspan="5" forceId="true" id="workCenterPnl">
            <t:inputText forceId="true" id="workCenterId" styleClass="textbox" value="#{pageBeanName.workCenterCode}"
                         onkeypress="changeworkCenterEvent(event);" onchange="changeworkCenterEvent(event);" maxlength="20"
                         disabled="#{pageBeanName.selectedMinistry == null || pageBeanName.selectedMinistry == ''}">
                <a4j:jsFunction name="changeworkCenter" reRender="workCenterPnl,errorWorkCenterGroupId"
                                actionListener="#{pageBeanName.changeWorkCenter}"/>
            </t:inputText>
            <f:verbatim>&nbsp;</f:verbatim>
            <t:inputText disabled="true" readonly="true" styleClass="textboxlarge" style="width: 400px;" forceId="true"
                         id="workCenterName" value="#{pageBeanName.pageDTO.workCentersDTO.name}"/>
            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
            <a4j:commandButton value="#{globalResources.Available}" action="#{pageBeanName.openWorkCentersIntegDiv}"
                               disabled="#{pageBeanName.selectedMinistry == null || pageBeanName.selectedMinistry == ''}"
                               id="workCenterDiv" styleClass="cssButtonSmaller"
                               oncomplete="showWorkCenterIntegrationDiv();"
                               reRender="integrationDiv1,OperationBar,dataT_data_panel,paging_panel,workCenterPnl,workcentersIntgSearchTbl,workcentersSearchCriteriaPnl,workcentersSearchPnl"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.govTypeCode==1}"/>
            <t:panelGroup colspan="1" id="errorWorkCenterGroupId" forceId="true">
                <c2:requiredFieldValidator componentToValidate="workCenterId" display="dynamic"
                                           active="#{addExperienceListBean.govTypeCode==1}"
                                           errorMessage="#{globalResources.missingField}" highlight="false"/>
                <t:outputLabel id="errorWorkCenterId" value="#{infIntegrationBundle.MessageForWrongCode}"
                               styleClass="errMsg" rendered="#{pageBeanName.errorWorkCenter}" forceId="true"/>
            </t:panelGroup>
        </t:panelGroup>
        <t:panelGroup colspan="6" id="job_typePG" forceId="true" rendered="#{pageBeanName.renderJobtypeRadio}">
            <t:selectOneRadio id="JobTypeRadioButton" value="#{pageBeanName.jobType}" styleClass="lable01">
                <a4j:support event="onclick" reRender="myFormGrp, divaddExperPage"/>
                <f:selectItem itemLabel="#{infIntegrationBundle.csc_jobs}" itemValue="0"/>
                <f:selectItem itemLabel="#{infIntegrationBundle.others_jobs}" itemValue="1"/>
            </t:selectOneRadio>
        </t:panelGroup>
        <t:outputText value="#{infIntegrationBundle.job}" styleClass="lable01"/>
        <t:panelGroup colspan="5" id="jobsPG" rendered="#{pageBeanName.jobType==0}">
            <t:inputText forceId="true" id="jobCode" styleClass="textbox" style="text-align: center !important;"
                         value="#{pageBeanName.jobCode}" onkeypress="changeJobsEvent(event);" maxlength="50"
                         onchange="changeJobsEvent(event);">
                <a4j:jsFunction name="changeJobs" reRender="divaddExperPage"
                                actionListener="#{pageBeanName.changeJobs}"/>
            </t:inputText>
            <f:verbatim>&nbsp;</f:verbatim>
            <t:inputText styleClass="textboxlarge" value="#{pageBeanName.jobName}" forceId="true" id="jobName"
                         disabled="true" style="width: 200px;"/>
            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
            <a4j:commandButton value="#{globalResources.Available}" id="showJob_btn" styleClass="cssButtonSmaller"
                               reRender="customDiv1" action="#{pageBeanName.showJobsDiv}"
                               oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <t:panelGroup colspan="1" id="errorJobIdPG" forceId="true">
                <c2:requiredFieldValidator componentToValidate="jobCode" display="dynamic"
                                           active="#{addExperienceListBean.jobType=='0'}"
                                           errorMessage="#{globalResources.missingField}" highlight="false"/>
                <t:outputLabel id="errorJobsId" value="#{infIntegrationBundle.MessageForWrongCode}" styleClass="errMsg"
                               rendered="#{pageBeanName.errorJobKey}" forceId="true"/>
            </t:panelGroup>
        </t:panelGroup>
        <t:panelGroup colspan="5" id="others_jobsPG" rendered="#{pageBeanName.jobType=='1'}">
            <t:inputText forceId="true" id="extraJob" styleClass="masterinput_hooma" maxlength="400"
                         value="#{pageBeanName.pageDTO.extraJob}" onchange="validateFromJobInput(event);"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c2:requiredFieldValidator componentToValidate="extraJob" display="dynamic"
                                       active="#{addExperienceListBean.jobType=='1'}"
                                       errorMessage="#{globalResources.missingField}" highlight="false"/>
        </t:panelGroup>
        <t:outputText value="#{infIntegrationBundle.other_job}" styleClass="lable01"
                      rendered="#{pageBeanName.renderTechJob}"/>
        <t:panelGroup colspan="5" id="otherjobsPG" rendered="#{pageBeanName.renderTechJob}">
            <t:inputText forceId="true" id="otherJobCode" styleClass="textbox" style="text-align: center !important;"
                         value="#{pageBeanName.otherJobCode}" onkeypress="changeOtherJobsEvent(event);" maxlength="50"
                         onchange="changeOtherJobsEvent(event);">
                <a4j:jsFunction name="changeotherJobs" reRender="divaddExperPage"
                                actionListener="#{pageBeanName.changeotherJobs}"/>
            </t:inputText>
            <f:verbatim>&nbsp;</f:verbatim>
            <t:inputText styleClass="textboxlarge" value="#{pageBeanName.otherJobName}" forceId="true" id="otherJobName"
                         disabled="true" style="width: 200px;"/>
            <f:verbatim>&nbsp;&nbsp;</f:verbatim>
            <a4j:commandButton value="#{globalResources.Available}" id="otherJobName_btn" styleClass="cssButtonSmaller"
                               reRender="customDiv1" action="#{pageBeanName.showOtherJobsDiv}"
                               oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"/>
            <t:panelGroup colspan="1" id="errorotherJobIdPG" forceId="true">
                <t:outputLabel id="errorotherJobsId" value="#{infIntegrationBundle.MessageForWrongCode}"
                               styleClass="errMsg" rendered="#{pageBeanName.errorOtherJobKey}" forceId="true"/>
            </t:panelGroup>
        </t:panelGroup>
        <t:outputLabel value="#{infIntegrationBundle.exeperience_fromDate}" styleClass="lable01"/>
        <t:panelGroup colspan="2" id="from_datePG">
            <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="fromDate" value="#{pageBeanName.pageDTO.fromDate}" size="20"
                             styleClass="textbox" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c2:requiredFieldValidator componentToValidate="fromDate" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" highlight="false"
                                       uniqueId="fromDateRequiredValidator"/>
            <c2:dateFormatValidator componentToValidate="fromDate" display="dynamic"
                                    errorMessage="#{infIntegrationBundle.Activate_Valid_Date_Message}" highlight="false"
                                    uniqueId="fromDateValidDateValidator"/>
        </t:panelGroup>
        <t:outputLabel value="#{infIntegrationBundle.unitl_date}" styleClass="lable01"/>
        <t:panelGroup colspan="2" id="unitl_date">
            <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="untilDate" value="#{pageBeanName.pageDTO.untilDate}" size="20"
                             styleClass="textbox" currentDayCellClass="currentDayCell" onblur="calcActualDaysPerRow();"
                             renderAsPopup="true" align="#{globalResources.align}" onchange="calcActualDaysPerRow();"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <a4j:jsFunction action="#{pageBeanName.calcActualDaysPerRow}" name="calcActualDaysPerRow"
                            reRender="divaddExperPage"/>
            <c2:requiredFieldValidator componentToValidate="untilDate" display="dynamic"
                                       errorMessage="#{globalResources.missingField}" highlight="false"
                                       uniqueId="untilDateRequiredValidator"/>
            <c2:dateFormatValidator componentToValidate="untilDate" display="dynamic"
                                    errorMessage="#{infIntegrationBundle.invalid_date}" highlight="false"
                                    uniqueId="untilValidDateValidator"/>
            <c2:compareDateValidator componentToValidate="untilDate" componentToCompare="fromDate" display="dynamic"
                                     errorMessage="#{infIntegrationBundle.compare_date}" operator="after"
                                     highlight="true" uniqueId="ValidUntilDateIDIDNina"/>
            <t:selectBooleanCheckbox value="#{pageBeanName.perFlag}" id="perFlag" forceId="true"/>
            <t:outputLabel value="#{infIntegrationBundle.PER_FLAG}" styleClass="lable01"/>
        </t:panelGroup>
        <t:outputLabel value="#{infIntegrationBundle.Actual_duration}" styleClass="lable01"
                       rendered="#{pageBeanName.renderExperDuration}"/>
        <t:panelGroup colspan="1" rendered="#{pageBeanName.renderExperDuration}">
            <t:inputText forceId="true" id="Active_duration_years" styleClass="filteration_input_text"
                         value="#{pageBeanName.activeDurationYears}" disabled="true"/>
            <t:outputLabel value="#{infIntegrationBundle.year_count}" styleClass="lable01"/>
        </t:panelGroup>
        <t:panelGroup colspan="1" rendered="#{pageBeanName.renderExperDuration}">
            <t:inputText forceId="true" id="Active_duration_Months" styleClass="filteration_input_text"
                         value="#{pageBeanName.activeDurationMonths}" disabled="true"/>
            <t:outputLabel value="#{infIntegrationBundle.month_count}" styleClass="lable01"/>
        </t:panelGroup>
        <t:panelGroup colspan="3" rendered="#{pageBeanName.renderExperDuration}">
            <t:inputText forceId="true" id="Active_duration_Days" styleClass="filteration_input_text"
                         value="#{pageBeanName.activeDurationDays}" disabled="true"/>
            <t:outputLabel value="#{infIntegrationBundle.day_count}" styleClass="lable01"/>
        </t:panelGroup>
        <t:outputLabel value="#{infIntegrationBundle.duration}" styleClass="lable01"
                       rendered="#{pageBeanName.renderExperDuration}"/>
        <t:panelGroup colspan="1" rendered="#{pageBeanName.renderExperDuration}">
            <t:inputText forceId="true" id="duration_years" styleClass="filteration_input_text" maxlength="2"
                         value="#{pageBeanName.pageDTO.actualExpYears}" onkeyup="enabelIntegerOnly(this);"
                         onchange="checkactualDurationOnBlur(this,'year', 'error_year', 'duration_month');"
                         onkeypress="validateYearsMonthDay(event,this, 'year', 'error_year', 'duration_month');"/>
            <t:outputLabel value="#{infIntegrationBundle.year_count}" styleClass="lable01"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:outputLabel value="#{infIntegrationBundle.error_year}" styleClass="error" style="display:none"
                           id="error_year" forceId="true"/>
        </t:panelGroup>
        <t:panelGroup colspan="1" rendered="#{pageBeanName.renderExperDuration}">
            <t:inputText forceId="true" id="duration_month" styleClass="filteration_input_text"
                         value="#{pageBeanName.pageDTO.actualExpMonths}" maxlength="2" onkeyup="enabelIntegerOnly(this);"
                         onchange="checkactualDurationOnBlur(this,'month', 'error_month', 'duration_days');"
                         onkeypress="validateYearsMonthDay(event,this, 'month', 'error_month', 'duration_days');"/>
            <t:outputLabel value="#{infIntegrationBundle.month_count}" styleClass="lable01"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:outputLabel value="#{infIntegrationBundle.error_month}" styleClass="error" style="display:none"
                           id="error_month" forceId="true"/>
        </t:panelGroup>
        <t:panelGroup colspan="1" rendered="#{pageBeanName.renderExperDuration}">
            <t:inputText forceId="true" id="duration_days" styleClass="filteration_input_text"
                         value="#{pageBeanName.pageDTO.actualExpDays}" maxlength="2" onkeyup="enabelIntegerOnly(this);"
                         onchange="checkactualDurationOnBlur(this, 'day', 'error_days');"
                         onkeypress="validateYearsMonthDay(event,this, 'day', 'error_days');"/>
            <t:outputLabel value="#{infIntegrationBundle.day_count}" styleClass="lable01"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <t:outputLabel value="#{infIntegrationBundle.error_days}" styleClass="error" style="display:none"
                           id="error_days" forceId="true"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<t:div id="navigationDiv" style="visibility:visible;background-color:#ffffff;" forceId="true">
    <t:panelGrid columns="3" border="0" align="center">
        <h:commandButton styleClass="cssButtonSmall" id="SaveButton" value="#{globalResources.SaveButton}"
                         action="#{pageBeanName.saveExperience}" onclick="return validatemyForm();"/>
        <h:panelGroup>
            <t:commandButton forceId="true" type="button" id="SaveNewButton"
                             onclick="validateSaveAndNewClientValidator();" styleClass="cssButtonSmall"
                             value="#{globalResources.AddNewButton}"/>
            <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}"
                            reRender="MsgPanel,divaddExperPage,pnlgrd_dec_radio,divMsg"
                            oncomplete="changeVisibilityMsg();setFocusAtMyFirstElement();"/>
        </h:panelGroup>
        <t:commandButton forceId="true" id="BackButtonManyToMany" value="#{globalResources.back}"
                         styleClass="cssButtonSmall" action="#{pageBeanName.back}"
                         onblur="setFocusAtMyFirstElement();"/>
    </t:panelGrid>
</t:div>
<%-- <t:inputHidden value="#{pageBeanName.regDate}" id="regDate" forceId="true"> <f:converter
     converterId="SqlDateConverter"/> </t:inputHidden>--%>
<script language="javascript" type="text/javascript">
  function showWorkCenterIntegrationDiv() {
      changeVisibilityDiv(window.blocker, window.integrationDiv1);

  }

  function changeworkCenterEvent(event) {
      if (event.keyCode == 13) {
          event.cancelBubble = true;
          event.returnValue = false;
          event.preventDefault();

          if (document.getElementById("workCenterId").value == null || document.getElementById("workCenterId").value == "") {
              document.getElementById("workCenterName").value = "";
          }
          else {
              changeworkCenter();
          }
      }
      return;
  }

  function changeJobsEvent(event) {
      if (event.keyCode == 13) {
          event.cancelBubble = true;
          event.returnValue = false;
          event.preventDefault();

          if (document.getElementById("jobCode").value == null || document.getElementById("jobCode").value == "") {
              document.getElementById("jobName").value = "";
          }
          else {
              changeJobs();
          }
      }
      return;
  }

  function changeOtherJobsEvent(event) {
      if (event.keyCode == 13) {
          event.cancelBubble = true;
          event.returnValue = false;
          event.preventDefault();

          if (document.getElementById("otherJobCode").value == null || document.getElementById("otherJobCode").value == "") {
              document.getElementById("otherJobName").value = "";
          }
          else {
              changeotherJobs();
          }
      }
      return;
  }

  function validateYearsMonthDay(e, inputtext, type, errMsgId, nextFocusId) {
      var event = e || window.event;
      var isValid = true;
      if (event.keyCode == 13) {
          var valueType = type;
          var errMsg = document.getElementById(errMsgId);
          errMsg.style.display = "none";
          trimBorders(inputtext.value);
          if (valueType != null) {
              if (valueType == "year" && inputtext.value != '' && inputtext.value > 99) {
                  isValid = false;
              }
              if (valueType == "month" && inputtext.value != '' && inputtext.value > 11) {
                  isValid = false;
              }
              if (valueType == "day" && inputtext.value != '' && inputtext.value > 30) {
                  isValid = false;
              }
          }
          if (isValid == false) {
              event.stopPropagation();
              event.preventDefault();
              errMsg.style.display = "inline";
              inputtext.focus();
              inputtext.select();
              return false;
          }
          else if (nextFocusId != null) {
              setFocusOnElement(nextFocusId);
          }
      }
  }

  function filterDivInputOnKeyPress(e, inputtext, ajaxFunction, nextFocusId) {
      var event = e || window.event;
      if (event.keyCode == 13) {
          trimBorders(inputtext.value);
          if (inputtext.value == '') {
              if (nextFocusId != null) {
                  setFocusOnElement(nextFocusId);
              }
              event.stopPropagation();
              event.preventDefault();
              return false;
          }
          if (ajaxFunction != null) {
              ajaxFunction();
              event.stopPropagation();
              event.preventDefault();
          }
          else {
              event.stopPropagation();
              event.preventDefault();
          }
          if (nextFocusId != null) {
              setFocusOnElement(nextFocusId);
          }

      }
  }

  function validateAllActualDuration() {
      var isValid = true;
      var duration_years = document.getElementById('duration_years');
      var error_year = document.getElementById('error_year');
      error_year.style.display = "none";
      var duration_month = document.getElementById('duration_month');
      var error_month = document.getElementById('error_month');
      error_month.style.display = "none";
      var duration_days = document.getElementById('duration_days');
      var error_days = document.getElementById('error_days');
      error_days.style.display = "none";
      if (duration_years.value != '' && duration_years.value > 99) {
          error_year.style.display = "inline";
          isValid = false;
      }
      if (duration_month.value != '' && duration_month.value > 11) {
          error_month.style.display = "inline";
          isValid = false;
      }
      if (duration_days.value != '' && duration_days.value > 30) {
          error_days.style.display = "inline";
          isValid = false;
      }
      return isValid;
  }

  function checkactualDurationOnBlur(inputtext, type, errMsgId, nextFocusId) {
      var isValid = true;
      var valueType = type;
      var errMsg = document.getElementById(errMsgId);
      errMsg.style.display = "none";
      trimBorders(inputtext.value);
      if (valueType != null) {
          if (valueType == "year" && inputtext.value != '' && inputtext.value > 99) {
              isValid = false;
          }
          if (valueType == "month" && inputtext.value != '' && inputtext.value > 11) {
              isValid = false;
          }
          if (valueType == "day" && inputtext.value != '' && inputtext.value > 30) {
              isValid = false;
          }
      }
      if (isValid == false) {
          errMsg.style.display = "inline";
          inputtext.focus();
          inputtext.select();
          return false;
      }
      else if (nextFocusId != null) {
          setFocusOnElement(nextFocusId);
      }
  }

</script>
