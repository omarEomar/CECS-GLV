<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<style>
        .fileUploadFakeInputText {
            background: none repeat scroll 0 0 #ffffff;
            border: 0 none;
            border-radius: 5px;
            box-shadow: 0 0 2px #000000;
            color: #1d7cbf;
            font-family: Tahoma;
            font-size: 12px;
            height: 23px;
            left: 0;
            padding-left: 5px;
            padding-right: 5px;
            position: absolute;
            right: 3px;
            top: 1px;
            width: 277px;
            z-index: 3;
        }

        .fileUploadFakeButton {
            right: 285px;
        }

        .fileUploadComponent {
            height: 25px;
            margin-right: 0;
            opacity: 0;
            padding-right: 0;
            width: 360px;
            z-index: 2;
        }
    </style>
<%-- <t:messages showDetail="true"/>--%>
<t:panelGrid columns="4" cellpadding="3" cellspacing="0" rowClasses="row02,row01" width="100%">
    <h:outputText value="#{infintegrationBundle.Civil_ID}" styleClass="divtext"/>
    <t:panelGroup colspan="1">
        <t:panelGrid columns="1">
            <t:panelGroup id="emp_panel" forceId="true">
                <t:inputText maxlength="12" forceId="true" id="CivilIdAdd" value="#{pageBeanName.civilId}"
                             styleClass="textbox" style="margin-right: 3px;" disabled="true"/>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
    <h:outputText value="#{infintegrationBundle.Employee_Name}" styleClass="divtext"/>
    <t:inputText styleClass="textboxmedium2" forceId="true" id="EmpName" style="margin-right: 3px; width: 300px;"
                 disabled="true" value="#{pageBeanName.empName}"/>
    <h:outputText value="#{infintegrationBundle.doctype}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
        <t:inputText maxlength="12" forceId="true" id="doctypeName" value="#{pageBeanName.doctypeName}"
                     styleClass="textboxmedium" style="margin-right: 3px;" disabled="true"
                     rendered="#{pageBeanName.disableSelectedDocType}"/>
        <t:selectOneMenu tabindex="5" forceId="true" id="doctypeMenu" styleClass="textboxmedium" onchange="submit()"
                         valueChangeListener="#{pageBeanName.fillAttachmentList}"
                         value="#{pageBeanName.selectedDocType}" rendered="#{!pageBeanName.disableSelectedDocType}">
            <f:selectItem itemLabel="#{infintegrationBundle.selectOne}" itemValue=""/>
            <t:selectItems value="#{pageBeanName.docTypeList}" itemLabel="#{list.name}" itemValue="#{list.code.key}"
                           var="list"/>
        </t:selectOneMenu>
        <t:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{!pageBeanName.disableSelectedDocType}"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c:requiredFieldValidator componentToValidate="doctypeMenu" display="dynamic"
                                  active="#{!addDocAttachmentsIntegrationBean.disableSelectedDocType}"
                                  errorMessage="#{globalResources.missingField}" highlight="false"/>
    </t:panelGroup>
    <h:outputText value="#{infintegrationBundle.docAttachmentsName}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
        <t:inputText styleClass="textboxmedium2" forceId="true" id="DocName" style="margin-right: 3px; width: 300px;"
                     value="#{pageBeanName.docName}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c:requiredFieldValidator componentToValidate="DocName" display="dynamic"
                                  errorMessage="#{globalResources.missingField}" highlight="false"/>
    </t:panelGroup>
    <%-- ///////////// upload start--%>
    <t:outputText value="#{infintegrationBundle.attach}" styleClass="lable01"/>
    <t:panelGroup colspan="3" id="attachmentPanel" forceId="true"
                  style="display:block;position: relative;min-height: 26px;">
        <div style="position: relative;"></div>
        <t:inputFileUpload id="myInputFileUpload" styleClass="fileUploadComponent"
                           onchange="document.getElementById('myInputText').value=this.value;" storage="file"
                           accept="image/*,application/pdf,application/doc"
                           value="#{addDocAttachmentsIntegrationBean.uploadedFile}"/>
        <t:inputText id="myInputText" onkeydown="disableEditing(event)" value="#{pageBeanName.fakeImageString}"
                     forceId="true" styleClass="fileUploadFakeInputText" onmouseout="hideTip()"
                     onmouseover="doTooltip(event,'(doc,docs,img,pdf,xlsx,txt)')"/>
        <h:commandButton styleClass="fileUploadFakeButton" value="#{infintegrationBundle.browse}"
                         onclick="return false;"/>
        <t:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <t:outputText value="#{infintegrationBundle.invalid_image_type}" styleClass="errMsg"
                      rendered="#{pageBeanName.invalidImageType}"/>
        <t:outputText value="#{infintegrationBundle.uploading_error}" styleClass="errMsg"
                      rendered="#{pageBeanName.uploadingError}"/>
        <c:customValidator componentToValidate="myInputText" errorMessage="#{globalResources.missingField}"
                           function="validateAttachment" display="dynamic"/>
        <%-- <c:requiredFieldValidator componentToValidate="myInputText" display="dynamic"
             errorMessage="#{globalResources.missingField}" highlight="false"/>--%>
        <%-- ///////////// scanne Startr--%>
        <%--<t:panelGroup style="display:block;position: absolute;left: 290px;top: -3px;">
            <f:verbatim>
                <applet code="com.beshara.infrastructure.scanner.Scanner" archive="${pageBeanName.archivePath}" height="30" width="90" align="top">
                    This browser does not support Applets.
                  
                    <param name="separate_jvm" value="true"
                           style="background: linear-gradient(#72C3E9, #208CBE, #2CA2D8); border-color: #1F87CF !important;"/>
                --%><%--<jsp:params>
                   <jsp:param name="param" value="${testManagedBean.param}"/>
                   <jsp:param name="java_codebase" value="${pageBeanName.archivePath}"/>
             </jsp:params>--%><%--
                </applet>
            </f:verbatim>
            <t:inputHidden id="appletHiddenField" forceId="true" value="#{pageBeanName.myAppletHiddenImg}"/>
            <t:inputHidden id="nodeSelectedForApplet" forceId="true" value="#{pageBeanName.nodeSelectedForApplet}"/>
            <t:inputHidden id="showErrorForNodeSelection" forceId="true"
                           value="#{pageBeanName.showErrorForNodeSelection}"/>
            <t:commandButton style="display:none;" id="appletCButton" forceId="true"
                             action="#{pageBeanName.appletSavingImgs}"/>
        </t:panelGroup>--%>
    </t:panelGroup>
    <fieldset id="financialYear">
        <t:outputText value="#{infintegrationBundle.fromDate}" styleClass="lable01"/>
        <t:panelGroup>
            <%-- <t:inputCalendar id="f_start_from" maxlength="10" value="#{pageBeanName.fromDate}"
                 monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass" dayCellClass="dayCellClass"
                 currentDayCellClass="currentDayCellClass" popupTodayString="#{infintegrationBundle.today} :"
                 popupWeekString="Wk" renderAsPopup="true" renderPopupButtonAsImage="true" popupDateFormat="dd/MM/yyyy"
                 alt="Calendar" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" title="Calendar"
                 forceId="true" styleClass="textboxmediumB"> <f:converter converterId="SqlDateConverter"/>
                 </t:inputCalendar>--%>
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             size="20" maxlength="10" styleClass="textboxmedium" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                             value="#{pageBeanName.fromDate}" forceId="true" id="f_start_from">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim>
                <br/>
            </f:verbatim>
            <c:requiredFieldValidator componentToValidate="f_start_from" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"/>
            <c:dateFormatValidator componentToValidate="f_start_from" display="dynamic"
                                   errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"/>
        </t:panelGroup>
        <t:outputText value="#{infintegrationBundle.untilDate}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputCalendar id="f_end_date" maxlength="10" value="#{pageBeanName.untilDate}"
                             monthYearRowClass="monthYearRowClass" weekRowClass="weekRowClass"
                             dayCellClass="dayCellClass" currentDayCellClass="currentDayCellClass"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" renderAsPopup="true"
                             renderPopupButtonAsImage="true" popupDateFormat="dd/MM/yyyy" alt="Calendar"
                             title="Calendar" forceId="true" styleClass="textboxmedium">
                <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            <c:compareDateValidator componentToValidate="f_end_date" componentToCompare="f_start_from" display="dynamic"
                                    operator="afterOrEqual" errorMessage="#{infintegrationBundle.compare_date}"
                                    highlight="false"/>
            <c:compareDateValidator componentToValidate="f_end_date" componentToCompare="f_start_from" display="dynamic"
                                    operator="equal" errorMessage="#{infintegrationBundle.compare_date}"
                                    highlight="false"/>
            <c:dateFormatValidator componentToValidate="f_end_date" display="dynamic"
                                   errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"/>
        </t:panelGroup>
    </fieldset>
</t:panelGrid>
<t:panelGroup style="text-align: center; display: block;">
    <t:commandButton forceId="true" id="addIntigrationButton" styleClass="cssButtonSmall" value="#{globalResources.add}"
                     action="#{pageBeanName.save}" onclick="return validatemyForm();"/>
</t:panelGroup>
<%--<t:saveState value="#{pageBeanName.enableFileNet}"/>--%>
<script type="text/javascript">
  function updateAppletHiddenFieldByImgsBytes(text) {
      //alert('enter updateAppletHiddenFieldByImgsBytes');
      document.getElementById('appletHiddenField').value = text;
      document.getElementById('appletCButton').click();
  }

  function getSelectedNodeForApplet() {
      //alert('enter getSelectedNodeForApplet');
      return 'true';
  }

  function validateAttachment() {
      var fileUploaderValue = document.getElementById('myInputText').value;
      //var scannerValue = document.getElementById('appletHiddenField').value;&& (scannerValue == null || scannerValue == "")
       
      if ((fileUploaderValue == null || fileUploaderValue == "") ) {
          return false;
      }
      return true;
  }
   function clearInPut() {
      var myInputText = document.getElementById('myInputText').value;
      //alert(myInputText);
      var scannerValue = document.getElementById('appletHiddenField').value;
      //alert(scannerValue);
       if ((myInputText == null || myInputText == "") && (scannerValue == null || scannerValue == "")){
          document.getElementById('myInputText').value = null;
          document.getElementById('appletHiddenField').value = null;
       }
      
  }
</script>
