<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.textareaMedium {
    width: 513px !important;
}

.textboxlarge2 {
    width: 517px !important;
}

.clndr_maintain_decisionDate  .jscalendar-DB-table-style{
    left: 55px;
    position: fixed;
    top: 82px;
    width: 250px;
}
#maintain_decisionText_ifr{
    height: 250px !important;
}
</htm:style>
  
<h:messages/>  

         <t:panelGroup id="mainPanelGroup" forceId="true">
            <t:panelGrid columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0">
            <!--- Start of Row 1-->
                <%--<t:panelGroup rendered="#{pageBeanName.currentStag==1 && pageBeanName.maintainMode==0}">
                    <t:outputText value="#{intgResources.loadForm}" styleClass="lable01"/>                
                </t:panelGroup>--%>
                <%--<t:panelGroup colspan="3" rendered="#{pageBeanName.currentStag==1 && pageBeanName.maintainMode==0}">
                    <t:commandButton  
                        id="showFormListId"
                        forceId="true"
                        value="..."   
                        styleClass="cssButtonSmall" 
                        onclick="block();"  
                        actionListener="#{detailBeanName.openSearchForms}"/>
                    <h:outputText value="#{intgResources.loadFormHint}"  style="color:#B28105;font-weight:bold" />
                </t:panelGroup>--%>
                <h:outputText value="#{intgResources.typeDecisionLabel}" styleClass="lable01"/>
                <t:panelGroup >
                  <t:inputText forceId="true" id="maintain_regTypeAddFilterationId" styleClass="textboxsmall" style="width:35px; margin-left:4px;" value="#{pageBeanName.pageDTO.typesDTOKey}"
                        onkeypress=" filterationInputOnKeyPress(event,this,'maintain_regTypeAdd','errorCodeID1',maintain_regTypeAddOnChange,'maintain_regIssuanceYearAdd');"
                        onblur=" filterationInputOnBlur(event,this,'maintain_regTypeAdd','errorCodeID1',maintain_regTypeAddOnChange,'maintain_regIssuanceYearAdd');"
                        onkeyup="enabelIntegerOnly(this);" disabled="#{pageBeanName.maintainMode != 0}"/>
                        
                    <t:selectOneMenu forceId="true" id="maintain_regTypeAdd" styleClass="DropdownboxMedium2" 
                    value="#{pageBeanName.pageDTO.typesDTOKey}" disabled="#{pageBeanName.showOnly || pageBeanName.maintainMode != 0 }"
                     onchange="filterationDropboxOnChange(event,this,'maintain_regTypeAddFilterationId','errorCodeID1',maintain_regTypeAddOnChange);"
                    >
                        <f:selectItem itemValue="" itemLabel="#{intgResources.regulation_type_label}"/>
                        <t:selectItems value="#{detailBeanName.types}" itemLabel="#{type.name}" itemValue="#{type.code.key}" var="type"/>
                         <%--<a4j:support actionListener="#{detailBeanName.filterDecMkrBytype}" event="onchange"
                            reRender="decMKRPnGr,scriptGeneratorId"/>--%>
                               <a4j:jsFunction actionListener="#{detailBeanName.filterDecMkrBytype}" name="maintain_regTypeAddOnChange"
                            reRender="decMKRPnGr,scriptGeneratorId"/>
                     
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode == 0}"/>
                 
                    <f:verbatim>
                        <br/>
                    </f:verbatim>     
                     <t:outputLabel id="errorCodeID1" value="#{intgResources.MessageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
                   <c2:requiredFieldValidator componentToValidate="maintain_regTypeAdd" active="#{settlementDecisionCycleMainDataMaintainBean.divflag==false && pageBeanName.maintainMode != 2}" display="dynamic" errorMessage="#{intgResources.choose_item}" highlight="false" uniqueId="maintain_regTypeAddID"/>
                </t:panelGroup>
                
                 <h:outputText value="#{intgResources.issuance_year}" />
                
                <h:panelGroup>
                    <t:selectOneMenu forceId="true" id="maintain_regIssuanceYearAdd" styleClass="textbox2" value="#{pageBeanName.pageDTO.yearsDTOKey}" disabled="#{pageBeanName.maintainMode != 0}">
                        <f:selectItem itemValue="" itemLabel="#{intgResources.regulation_issuance_year_label}"/>
                        <t:selectItems value="#{detailBeanName.issuanceYears}" itemLabel="#{year.code.key}" itemValue="#{year.code.key}" var="year"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode == 0}"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator active="#{settlementDecisionCycleMainDataMaintainBean.divflag==false && pageBeanName.maintainMode != 2}" componentToValidate="maintain_regIssuanceYearAdd" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regIssuanceYearID"/>
                </h:panelGroup> 
                
                <%--<h:outputText value="#{intgResources.decision_number}"/>--%>
                <%--<h:panelGroup >
                <t:inputText forceId="true" id="maintain_decisionNumber" maxlength="10" onkeyup="disableCharacters(this)" onmousemove="disableCharacters(this);" onchange="disableCharacters(this);"
                     styleClass="textboxmedium" value="#{pageBeanName.pageDTO.decisionNumber}"
                     disabled="#{pageBeanName.viewOnlyMode}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator active="#{settlementDecisionCycleMainDataMaintainBean.divflag==false}"
                                   componentToValidate="maintain_decisionNumber" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"
                                   uniqueId="maintain_decisionNumberID"/>
    </h:panelGroup>--%>
                
                                
                <h:outputText value="#{intgResources.decision_date}" styleClass="lable01"/>
                 
                <t:panelGroup styleClass="clndr_maintain_decisionDate" colspan="3">
                    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.pageDTO.decisionDate}" id="clndr_maintain_decisionDate"
                           maxlength="10" styleClass="textboxmedium" currentDayCellClass="currentDayCell"
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" disabled="#{pageBeanName.showOnly || pageBeanName.maintainMode == 2}">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode != 2}"/>
                     <t:outputText forceId="true" id="vaildateIssuanceYearWithDecitionDateId" value="" styleClass="errMsg"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:dateFormatValidator componentToValidate="clndr_maintain_decisionDate"
                                    display="dynamic" active="#{settlementDecisionCycleMainDataMaintainBean.divflag==false}"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
                    <c2:requiredFieldValidator componentToValidate="clndr_maintain_decisionDate" active="#{settlementDecisionCycleMainDataMaintainBean.divflag==false && pageBeanName.maintainMode != 2}" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"  uniqueId="maintain_decisionDateID"/>
                    <t:outputText  forceId="true" id="FinancialdescDatErrMsgId" value="#{regResources.Financial_desc_Date_Err_Msg}" rendered="#{detailBeanName.invalidDateErr}" styleClass="errMsg"/>
                </t:panelGroup>
                
            <!--- End of Row 1-->
            <!--- Start of Row 2-->
                <h:outputText value="#{intgResources.dec_title}" styleClass="lable01"/>
                <t:panelGroup colspan="3">
                    <t:inputTextarea forceId="true" id="maintain_decisionTitle"  styleClass="TextBoxLarge2"  style="width: 600px ! important; height: 45px;" value="#{pageBeanName.pageDTO.decisionTitle}" disabled="#{pageBeanName.showOnly || pageBeanName.maintainMode == 2}"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode != 2}"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator active="#{settlementDecisionCycleMainDataMaintainBean.divflag==false && pageBeanName.maintainMode != 2}" componentToValidate="maintain_decisionTitle" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_decisionTitleID"/>
                </t:panelGroup>

            <!--- End of Row 2-->

            <!--- Start of Row 3-->
                <h:outputText value="#{intgResources.decision_subject}" styleClass="lable01"/>
                
                <h:panelGroup>
                
                 <t:inputText forceId="true" id="maintain_decisionSubjectFilterationId" 
                    disabled="#{pageBeanName.showOnly || pageBeanName.maintainMode == 2}"
                    styleClass="textboxsmall" style="width:35px; margin-left:4px;" 
                    value="#{pageBeanName.pageDTO.subjectsDTOKey}"
                        onkeypress=" filterationInputOnKeyPress(event,this,'maintain_decisionSubject','errorCodeID2',null,'');"
                        onblur=" filterationInputOnBlur(event,this,'maintain_decisionSubject','errorCodeID2',null,'');"
                        onkeyup="enabelIntegerOnly(this);"/>
                        
                    <t:selectOneMenu forceId="true" id="maintain_decisionSubject" styleClass="DropdownboxMedium2"
                    value="#{pageBeanName.pageDTO.subjectsDTOKey}" disabled="#{pageBeanName.showOnly || pageBeanName.maintainMode == 2}"
                    onchange="filterationDropboxOnChange(event,this,'maintain_decisionSubjectFilterationId','errorCodeID2',null);">
                        <f:selectItem itemValue="" itemLabel="#{intgResources.decision_subject_label}"/>
                        <t:selectItems value="#{detailBeanName.subjects}" itemLabel="#{subject.name}" itemValue="#{subject.code.key}" var="subject"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode != 2}"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <t:outputLabel id="errorCodeID2" value="#{intgResources.MessageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
                    <c2:requiredFieldValidator componentToValidate="maintain_decisionSubject" active="#{settlementDecisionCycleMainDataMaintainBean.divflag==false && pageBeanName.maintainMode != 2}" display="dynamic" errorMessage="#{intgResources.choose_item}" highlight="false" uniqueId="maintain_decisionSubjectID"/>
                </h:panelGroup>
                <%--<h:outputText value="#{intgResources.regulation_template}" styleClass="lable01"/>--%>
                <%--<t:selectOneMenu forceId="true" id="maintain_decisionTemplate" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.templatesDTOKey}" disabled="#{pageBeanName.showOnly}">
                    <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValueString}" itemLabel="#{intgResources.regulation_template_label}"/>
                    <t:selectItems value="#{detailBeanName.templates}" itemLabel="#{template.name}" itemValue="#{template.code.key}" var="template"/>
                </t:selectOneMenu>--%>
            <h:outputText value="#{intgResources.decisions_decision_maker}"/>
    <h:panelGroup id="decMKRPnGr">
    
     <t:inputText disabled="#{(detailBeanName.decMkrDisabled && (pageBeanName.pageDTO.typesDTOKey==null)) || pageBeanName.showOnly || pageBeanName.maintainMode == 2}" forceId="true" id="maintain_financeDecisionMakerAddFilterationId" 
                    styleClass="textboxsmall" style="width:35px; margin-left:4px;" 
                    value="#{pageBeanName.pageDTO.decisionMakerDTOKey}"
                        onkeypress=" filterationInputOnKeyPress(event,this,'maintain_financeDecisionMakerAdd','errorCodeID3',null,'');"
                        onblur=" filterationInputOnBlur(event,this,'maintain_financeDecisionMakerAdd','errorCodeID3',null,'');"
                        onkeyup="enabelIntegerOnly(this);"/>
                        
        <t:selectOneMenu disabled="#{(detailBeanName.decMkrDisabled && (pageBeanName.pageDTO.typesDTOKey==null)) || pageBeanName.showOnly || pageBeanName.maintainMode == 2}" forceId="true" id="maintain_financeDecisionMakerAdd"
                    styleClass="textbox_175"
                    value="#{pageBeanName.pageDTO.decisionMakerDTOKey}"
           onchange="filterationDropboxOnChange(event,this,'maintain_financeDecisionMakerAddFilterationId','errorCodeID3',null);"
           >
            <f:selectItem itemValue="" itemLabel="#{intgResources.regulation_decision_maker_label}"/>
            <t:selectItems value="#{detailBeanName.decisionMakers}" itemLabel="#{dMaker.name}"
                           itemValue="#{dMaker.code.key}" var="dMaker"/>
            
        </t:selectOneMenu>
        <h:outputText rendered="#{!detailBeanName.decMkrDisabled && pageBeanName.maintainMode != 2}" value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
              <br/>
        </f:verbatim>
         <t:outputLabel id="errorCodeID3" value="#{intgResources.MessageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
         
        <c2:requiredFieldValidator componentToValidate="maintain_financeDecisionMakerAdd" display="dynamic"
                                    active="#{settlementDecisionCycleMainDataMaintainBean.decMkrDisabled==false && pageBeanName.maintainMode != 2}"  errorMessage="#{intgResources.choose_item}" highlight="false"
                                   uniqueId="maintain_financeDecisionMakerAddID"/>
    </h:panelGroup>
    
            <!--- End of Row 3-->
            <!--- Start of Row 4-->
            <%--<t:outputText value="#{resourcesBundle.maker_user_name}" styleClass="lable01"/>--%>
            <%--<t:panelGroup>
                <t:inputText  styleClass="textboxmediumB" forceId="true" disabled="true" id="UserName" value="#{pageBeanName.pageDTO.secWorkCenterUsersDTO.userName}"/>
                <f:verbatim>&nbsp;</f:verbatim>
                <t:commandButton  
                    value="..."   
                    styleClass="cssButtonSmall" 
                    onclick="block();"  
                    action="#{detailBeanName.openSearchUser}"  
                    disabled="#{pageBeanName.showOnly}"/>
            </t:panelGroup>--%>
            <%--<t:outputText value="#{resourcesBundle.EmpName}" styleClass="lable01"/>--%>
            <%--<t:panelGroup>
                <t:inputText  styleClass="textboxmedium" disabled="true" forceId="true" id="Empname" value="#{pageBeanName.pageDTO.secWorkCenterUsersDTO.civilname}"/>
            </t:panelGroup>--%>
            <!--- End of Row 4-->
            <!--- Start of Row 5-->            
                <h:outputText value="#{intgResources.textOFDecision}" styleClass="lable01"/>
                <t:panelGroup style="text-align:right;" colspan="3"  dir="rtl">
                    <%--<t:panelGrid columns="3">--%>
                        <t:inputTextarea  style="width: 600px ! important;" forceId="true" id="maintain_decisionText" 
                        styleClass="textareaMedium masterinput_hooma" value="#{pageBeanName.pageDTO.decisionText}" 
                        rendered="#{!pageBeanName.showOnly}" />
                    <t:outputText escape="false" rendered="#{pageBeanName.showOnly}" forceId="true" id="maintain_regTextforview"
                         value="#{pageBeanName.pageDTO.decisionText}" styleClass="textareaMedium masterinput_hooma" 
                         style="background-color: #dddddd ;display: block;width: 600px ! important;height: 250px !important; border: 1px solid #dddddd;border-radius: 5px;box-shadow: 0 0 6px #ffffff inset;color: #175179;"/>

                       <t:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode != 2}"/>
                       <f:verbatim>
                        <br/>
                       </f:verbatim>
                        
                        <%--<c2:requiredFieldValidator active="#{settlementDecisionCycleMainDataMaintainBean.divflag==false && pageBeanName.maintainMode != 2}" componentToValidate="maintain_decisionText" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>--%>
                        <c2:customValidator componentToValidate="maintain_decisionText"  errorMessage="#{globalResources.missingField}"
                           function="validateEditor('maintain_decisionText');" display="dynamic"/>
                    <%--</t:panelGrid>--%>
                </t:panelGroup>
              <%--<t:panelGroup colspan="4"  style="text-align: center; display: block;">
                   <t:commandButton forceId="true" styleClass="cssButtonMedium"  id="editor" value="#{intgResources.editorDectionLabel}"  action="#{settlementDecisionCycleMainDataMaintainBean.openEditor}" disabled="true"/>
                   <t:commandButton forceId="true" styleClass="cssButtonMedium"  id="materialEditor"  disabled="true" value="#{intgResources.materialEditorDectionLabel}"  action="#{settlementDecisionCycleMainDataMaintainBean.openEditor}" />
              </t:panelGroup>--%>
             
            </t:panelGrid>
            </t:panelGroup>
            <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="clndr_maintain_decisionDate,210,25" />
            <t:inputText forceId="true" id="yearAndYearDateMsgErrorDecId" value="#{intgResources.yearAndYearDateMsgErrorDecision}" disabled="true" styleClass="textboxnodefocus"/>
            
            
<script type="text/javascript">

initMCEEditor("maintain_decisionText");

  function setFocusAtMyFirstElement() {
      //document.getElementById("myForm:content1Div").scrollTop = 0;
      if (document.getElementById('maintain_regTypeAdd') != null)
          setFocusOnlyOnElement('maintain_regTypeAdd');
      else if (document.getElementById('maintain_decisionTitle') != null)
          setFocusOnlyOnElement('maintain_decisionTitle').focus();
  }
</script>