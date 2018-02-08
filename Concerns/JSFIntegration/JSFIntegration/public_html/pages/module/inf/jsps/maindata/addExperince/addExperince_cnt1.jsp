<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:panelGrid forceId="true" id="MsgPanel" width="25%" align="center">
    <t:outputText forceId="true" id="error" value="#{pageBeanName.errorMsg}" styleClass="errMsg" rendered="#{detailBeanName.showErrorMsg}"/>
     <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg" rendered="#{pageBeanName.success}"/>
     <%-- t:outputText forceId="true" id="failureInAdd" value="#{resourcesBundle.unsucess_add_msg}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/--%>
</t:panelGrid>

<t:panelGrid rowClasses="row02,row01" cellpadding="3" columnClasses="colu1,colu2" cellspacing="0" width="100%" forceId="true" id="experiencePanel" columns="2">

   <t:outputLabel value="#{resourcesBundle.ministry_div_title_exp}" styleClass="lable01"/>
     <t:panelGroup>
              <t:inputText  forceId="true" id="ministryAdd" value="#{pageBeanName.pageDTO.ministriesDTO.name}"  disabled="true" styleClass="textboxmedium" style="width:350px;"/>   
              <f:verbatim>&nbsp;&nbsp;</f:verbatim>
              <a4j:commandButton value="..."   styleClass="cssButtonSmall" action="#{minHelperBeanName.openMinistriesDiv}" reRender="lov_dataT_data_panel,lovLinkStatusPanel,javaScriptCallerOutputText" id="searchMinistryBtn"  />
              <f:verbatim>&nbsp;&nbsp;</f:verbatim>
               <h:outputText value="*" styleClass="mandatoryAsterisk"/>
              <f:verbatim>&nbsp;&nbsp;</f:verbatim>
              <c2:requiredFieldValidator componentToValidate="ministryAdd" active="#{!experienceAddBean.validateSrchString}" errorMessage="#{globalResources.missingField}" display="dynamic"/>
     </t:panelGroup>
     
     <t:outputLabel value="#{resourcesBundle.job}" styleClass="lable01"/>
            <%--<t:panelGroup colspan="3">
                <t:inputText styleClass="textboxlarge" value="#{pageBeanName.supToJobName}" forceId="true"
                             id="supToJobName" disabled="true"/>
                <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                <a4j:commandButton value="#{globalResources.Available}"
                                   onblur="setFocusOnlyOnElement('myForm:supOnJobName_btn');" id="showSupToJob_btn"
                                   styleClass="cssButtonSmaller" reRender="customDiv1"
                                   action="#{pageBeanName.showSupToJob}"
                                   oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);"/>
            </t:panelGroup>--%>
     <t:panelGroup id="jobPanelGrp" forceId="true">
          <t:inputText disabled="true" tabindex="45631" styleClass="textboxmedium" forceId="true" id="jobName" value="#{pageBeanName.pageDTO.jobsDTO.name}" style="width:350px;"/>
          <f:verbatim>&nbsp;&nbsp;</f:verbatim>
          <a4j:commandButton value="#{globalResources.Available}" styleClass="cssButtonSmall" action="#{pageBeanName.showListOfValuesDiv}" reRender="customDiv1" oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);" />
          <%--<f:verbatim>&nbsp;&nbsp;</f:verbatim>--%>
          <%--<t:panelGroup id="reset_data_btn_id"  rendered="#{!empty pageBeanName.pageDTO.jobsDTO.name}" forceId="true">
            <a4j:commandButton  value="#{resourcesBundle.reSetButtonErase}" styleClass="cssButtonSmall" action="#{pageBeanName.reSetButtonErase}"
                reRender="jobPanelGrp"/>
        </t:panelGroup>--%>
     </t:panelGroup>
    
    
     <h:outputText  value="#{resourcesBundle.workCenter}" styleClass="lable01"/>
    <t:panelGroup colspan="3" forceId="true" id="workCenterPnl">
        <t:inputText disabled="true" readonly="true" tabindex="888888888" styleClass="textboxlarge" style="width: 295px;" forceId="true" id="workCenterName" value="#{pageBeanName.pageDTO.workCentersDTO.name}"/>
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        <a4j:commandButton  value="#{globalResources.Available}" action="#{pageBeanName.openWorkCentersIntegDiv}" disabled="#{pageBeanName.disableWorkCenterDiv}" id="workCenterDiv"
                                 styleClass="cssButtonSmaller" oncomplete="showWorkCenterIntegrationDiv();" reRender="OperationBar,dataT_data_panel,paging_panel,workCenterName,workCenterPnl,workcentersIntgSearchTbl,workcentersSearchCriteriaPnl,workcentersSearchPnl"/>
        <%--<f:verbatim>--%><!--&nbsp;&nbsp;--><%--</f:verbatim>--%>
        <%--<a4j:commandButton disabled="#{pageBeanName.pageDTO.workCentersDTO.name == null}" value="#{resourcesBundle.all}" styleClass="cssButtonSmall" reRender="OperationBar,dataT_data_panel,paging_panel,workCenterName,workCenterPnl,workcentersSearchPnl,workcentersSearchCriteriaPnl" action="#{pageBeanName.resetPageData}" />--%>
    </t:panelGroup>
  
  
     <t:outputLabel value="#{resourcesBundle.from_date}" styleClass="lable01"/>
     <t:panelGroup>
          <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="fromDate" value="#{pageBeanName.pageDTO.fromDate}" size="20" maxlength="200"
                           styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                           renderPopupButtonAsImage="true">
               <f:converter converterId="SqlDateConverter"/>
          </t:inputCalendar>
          <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <c2:requiredFieldValidator componentToValidate="fromDate" active="#{!experienceAddBean.validateSrchString}" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="fromDateRequiredValidator"/>
          <c2:dateFormatValidator componentToValidate="fromDate" active="#{!experienceAddBean.validateSrchString}" display="dynamic" errorMessage="#{resourcesBundle.Activate_Valid_Date_Message}" highlight="false" uniqueId="fromDateValidDateValidator"/>
     </t:panelGroup>
     <t:outputLabel value="#{resourcesBundle.unitl_date}" styleClass="lable01"/>
     <t:panelGroup>
          <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="untilDate" value="#{pageBeanName.pageDTO.untilDate}" size="20"
                          maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                           renderPopupButtonAsImage="true">
               <f:converter converterId="SqlDateConverter"/>
          </t:inputCalendar>
          <h:outputText value="*" styleClass="mandatoryAsterisk"/>
          <c2:requiredFieldValidator componentToValidate="untilDate" active="#{!experienceAddBean.validateSrchString}" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="untilDateRequiredValidator"/>
          <c2:dateFormatValidator componentToValidate="untilDate" active="#{!experienceAddBean.validateSrchString}" display="dynamic" errorMessage="#{resourcesBundle.Activate_Valid_Date_Message}" highlight="false" uniqueId="untilValidDateValidator"/>
          <c2:compareDateValidator componentToValidate="untilDate" active="#{!experienceAddBean.validateSrchString}" componentToCompare="fromDate" display="dynamic" errorMessage="#{resourcesBundle.jobSeekerXPDates}"
                           operator="after" highlight="true" uniqueId="ValidUntilDateIDIDNina"/>
          <%--<c2:compareDateValidator componentToValidate="untilDate" componentToCompare="regDate" active="#{!experienceAddBean.validateSrchString}" display="dynamic" errorMessage="#{resourcesBundle.regDate_untilDate_validation} ( #{pageBeanName.regDateLabel} )"
                            operator="before" highlight="false"  uniqueId="ValidUntilDateIDIDNinaaa"/>--%>
          
     </t:panelGroup>
</t:panelGrid>
<t:div id="navigationDiv" style="visibility:visible;" forceId="true">
<t:panelGrid columns="3" border="0" align="center">
     <h:commandButton styleClass="cssButtonSmall" id="SaveButton" value="#{globalResources.SaveButton}" action="#{pageBeanName.saveExperience}" onclick="reinitializeSaveMsg('successAddLockup');reinitializeSaveMsg('failureInAdd');return validatemyForm();"/>
     <h:panelGroup>
          <t:commandButton forceId="true" type="button"  id="SaveNewButton" onclick="reinitializeSaveMsg('successAddLockup');reinitializeSaveMsg('failureInAdd');validateSaveAndNewClientValidator();" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>
          <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="experiencePanel,MsgPanel" oncomplete="setFocusAtMyFirstElement();"/>
     </h:panelGroup>
     <t:commandButton  forceId="true" id="BackButtonManyToMany"  value="#{globalResources.back}" styleClass="cssButtonSmall" action="#{pageBeanName.back}" onblur="setFocusAtMyFirstElement();"/>
</t:panelGrid>
</t:div>

<%--<t:inputHidden value="#{pageBeanName.regDate}" id="regDate" forceId="true">
    <f:converter converterId="SqlDateConverter"/>
 </t:inputHidden>--%>
<script language="javascript" type="text/javascript">
function showWorkCenterIntegrationDiv() {
       
           changeVisibilityDiv(window.blocker,window.integrationDiv2);

    }
</script> 
