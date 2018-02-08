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
                    <t:outputText value="#{regResources.loadForm}" styleClass="lable01"/>                
                </t:panelGroup>--%>
                <%--<t:panelGroup colspan="3" rendered="#{pageBeanName.currentStag==1 && pageBeanName.maintainMode==0}">
                    <t:commandButton  
                        id="showFormListId"
                        forceId="true"
                        value="..."   
                        styleClass="cssButtonSmall" 
                        onclick="block();"  
                        actionListener="#{detailBeanName.openSearchForms}"/>
                    <h:outputText value="#{regResources.loadFormHint}"  style="color:#B28105;font-weight:bold" />
                </t:panelGroup>--%>
                <h:outputText value="#{regResources.typeDecisionLabel}" styleClass="lable01"/>
                <t:panelGroup >
                    <t:inputText  forceId="true" id="maintain_regTypeAddFilterationId" styleClass="textboxsmall" style="width:35px; margin-left:4px;" value="#{pageBeanName.pageDTO.typesDTOKey}"
                        onkeypress=" filterationInputOnKeyPress(event,this,'maintain_regTypeAdd','errorCodeID1',maintain_regTypeAddOnChange,'maintain_regIssuanceYearAdd');"
                        onblur=" filterationInputOnBlur(event,this,'maintain_regTypeAdd','errorCodeID1',maintain_regTypeAddOnChange,'maintain_regIssuanceYearAdd');"
                        onkeyup="enabelIntegerOnly(this);" disabled="#{pageBeanName.maintainMode != 0}"/>
                        
                    <t:selectOneMenu forceId="true" id="maintain_regTypeAdd" styleClass="DropdownboxMedium2" 
                        value="#{pageBeanName.pageDTO.typesDTOKey}" disabled="#{pageBeanName.showOnly || pageBeanName.maintainMode != 0}"
                        onchange="filterationDropboxOnChange(event,this,'maintain_regTypeAddFilterationId','errorCodeID1',maintain_regTypeAddOnChange);" >
                        
                        <f:selectItem itemValue="" itemLabel="#{regResources.regulation_type_label}"/>
                        <t:selectItems value="#{detailBeanName.types}" itemLabel="#{type.name}" itemValue="#{type.code.key}" var="type"/>
                         <%--<a4j:support actionListener="#{detailBeanName.filterDecMkrBytype}" event="onchange"
                            reRender="decMKRPnGr,scriptGeneratorId"/>--%>
                                <a4j:jsFunction actionListener="#{detailBeanName.filterDecMkrBytype}" name="maintain_regTypeAddOnChange"
                            reRender="decMKRPnGr,scriptGeneratorId"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode == 0 && !pageBeanName.showOnly}"/>
                 
                    <f:verbatim>
                        <br/>
                    </f:verbatim>     
                     <t:outputLabel id="errorCodeID1" value="#{regResources.MessageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
                   <c2:requiredFieldValidator componentToValidate="maintain_regTypeAdd" active="#{!financeDecisionCycleMaintainBean.showOnly}" display="dynamic" errorMessage="#{regResources.choose_item}" highlight="false" uniqueId="maintain_regTypeAddID"/>
                </t:panelGroup>
                
                   <h:outputText value="#{regResources.decisions_decision_maker}"/>
    <t:panelGroup id="decMKRPnGr" forceId="true">
    
     <t:inputText disabled="#{detailBeanName.decMkrDisabled && (pageBeanName.pageDTO.typesDTOKey==null) || pageBeanName.showOnly}" forceId="true" id="maintain_financeDecisionMakerAddFilterationId" 
                    styleClass="textboxsmall" style="width:35px; margin-left:4px;" 
                    value="#{pageBeanName.pageDTO.decisionMakerDTOKey}"
                        onkeypress="filterationInputOnKeyPress(event,this,'maintain_financeDecisionMakerAdd','errorCodeID3',null,'');"
                        onblur="filterationInputOnBlur(event,this,'maintain_financeDecisionMakerAdd','errorCodeID3',null,'');"
                        onkeyup="enabelIntegerOnly(this);"/>
                        
        <t:selectOneMenu disabled="#{detailBeanName.decMkrDisabled && (pageBeanName.pageDTO.typesDTOKey==null) || pageBeanName.showOnly}" forceId="true"
        id="maintain_financeDecisionMakerAdd"
                    styleClass="textboxmedium" style="width: 180px ;" 
                    value="#{pageBeanName.pageDTO.decisionMakerDTOKey}"
                     onchange="filterationDropboxOnChange(event,this,'maintain_financeDecisionMakerAddFilterationId','errorCodeID3',null);"
                    >
            <f:selectItem itemValue="" itemLabel="#{regResources.regulation_decision_maker_label}"/>
            <t:selectItems value="#{detailBeanName.decisionMakers}" itemLabel="#{dMaker.name}"
                           itemValue="#{dMaker.code.key}" var="dMaker"/>
            
        </t:selectOneMenu>
        <h:outputText  value="*" styleClass="mandatoryAsterisk"  rendered="#{!pageBeanName.showOnly}"/>
        <f:verbatim>
              <br/>
        </f:verbatim>
         <t:outputLabel id="errorCodeID3" value="#{regResources.MessageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
        <c2:requiredFieldValidator componentToValidate="maintain_financeDecisionMakerAdd" display="dynamic"
                                   errorMessage="#{regResources.choose_item}" highlight="false"
                                   uniqueId="maintain_financeDecisionMakerAddID" active="#{!financeDecisionCycleMaintainBean.showOnly}"/>
    </t:panelGroup>
                
             <h:outputText value="#{regResources.issuance_year}" />
            <h:panelGroup >
                <t:selectOneMenu forceId="true" id="maintain_regIssuanceYearAdd" styleClass="textbox2" value="#{pageBeanName.pageDTO.yearsDTOKey}" disabled="#{pageBeanName.maintainMode != 0}">
                    <f:selectItem itemValue="" itemLabel="#{regResources.regulation_issuance_year_label}"/>
                    <t:selectItems value="#{detailBeanName.issuanceYears}" itemLabel="#{year.code.key}" itemValue="#{year.code.key}" var="year"/>
                </t:selectOneMenu>
                <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode == 0 && !pageBeanName.showOnly}"/>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:requiredFieldValidator active="#{!financeDecisionCycleMaintainBean.showOnly}" componentToValidate="maintain_regIssuanceYearAdd" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regIssuanceYearID"/>
            </h:panelGroup> 
                
                <%--<h:outputText value="#{regResources.decision_number}"/>--%>
                <%--<h:panelGroup >
                <t:inputText forceId="true" id="maintain_decisionNumber" maxlength="10" onkeyup="disableCharacters(this)" onmousemove="disableCharacters(this);" onchange="disableCharacters(this);"
                     styleClass="textboxmedium" value="#{pageBeanName.pageDTO.decisionNumber}"
                     disabled="#{pageBeanName.viewOnlyMode}"/>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator active="#{!financeDecisionCycleMaintainBean.showOnly}"
                                   componentToValidate="maintain_decisionNumber" display="dynamic"
                                   errorMessage="#{globalResources.missingField}" highlight="false"
                                   uniqueId="maintain_decisionNumberID"/>
    </h:panelGroup>--%>
                
                
                
              
    
                <h:outputText value="#{regResources.decision_date}" styleClass="lable01"/>
                 
                <t:panelGroup styleClass="clndr_maintain_decisionDate" >
                    <t:inputCalendar  title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.pageDTO.decisionDate}" id="clndr_maintain_decisionDate"
                           maxlength="10" styleClass="textboxmedium" currentDayCellClass="currentDayCell"
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" disabled="#{pageBeanName.showOnly}">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"  rendered="#{!pageBeanName.showOnly}"/>
                     <t:outputText forceId="true" id="vaildateIssuanceYearWithDecitionDateId" value="" styleClass="errMsg"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:dateFormatValidator componentToValidate="clndr_maintain_decisionDate"
                                    display="dynamic" active="#{!financeDecisionCycleMaintainBean.showOnly}"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
                    <c2:requiredFieldValidator componentToValidate="clndr_maintain_decisionDate" active="#{!financeDecisionCycleMaintainBean.showOnly}" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"  uniqueId="maintain_decisionDateID"/>
                </t:panelGroup>
                
                
                 <h:outputText value="#{regResources.decision_subject}" styleClass="lable01"/>
                
                <t:panelGroup >
                  <t:inputText forceId="true" id="maintain_decisionSubjectFilterationId" disabled="#{pageBeanName.showOnly}"
                    styleClass="textboxsmall" style="width:35px; margin-left:4px;" 
                    value="#{(pageBeanName.maintainMode != 0) ? pageBeanName.pageDTO.subjectsDTOKey : (detailBeanName.firstSubject)}"
                        onkeypress=" filterationInputOnKeyPress(event,this,'maintain_decisionSubject','errorCodeID2',null,'');"
                        onblur=" filterationInputOnBlur(event,this,'maintain_decisionSubject','errorCodeID2',null,'');"
                        onkeyup="enabelIntegerOnly(this);"/>
                        
                    <t:selectOneMenu forceId="true" id="maintain_decisionSubject" styleClass="DropdownboxMedium2"  disabled="#{pageBeanName.showOnly}"
                    value="#{pageBeanName.pageDTO.subjectsDTOKey}" 
                    onchange="filterationDropboxOnChange(event,this,'maintain_decisionSubjectFilterationId','errorCodeID2',null);">
                        <f:selectItem itemValue="" itemLabel="#{regResources.decision_subject_label}"/>
                        <t:selectItems value="#{detailBeanName.subjects}" itemLabel="#{subject.name}" itemValue="#{subject.code.key}" var="subject"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"  rendered="#{!pageBeanName.showOnly}"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <t:outputLabel id="errorCodeID2" value="#{regResources.MessageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
                    <c2:requiredFieldValidator componentToValidate="maintain_decisionSubject" active="#{!financeDecisionCycleMaintainBean.showOnly}" display="dynamic" errorMessage="#{regResources.choose_item}" highlight="false" uniqueId="maintain_decisionSubjectID"/>
                </t:panelGroup>
                
                <t:panelGroup colspan="2">
                         <t:selectOneRadio styleClass="radioButton_DataTable" forceId="true" id="multiOrSingleEmps_RadioId"
                                              value="#{pageBeanName.multiOrSingleEmps}"
                                              onchange="changeEmployeeElementsMode();"  disabled="#{pageBeanName.showOnly || pageBeanName.maintainMode != 0}">
                                    <f:selectItem itemLabel="#{regResources.One_Element_Multi_Employees_Label}" itemValue="1" />
                                    <f:selectItem itemLabel="#{regResources.Multi_Elements_One_Employee_Label}" itemValue="0" />
                        </t:selectOneRadio>
                       <a4j:jsFunction name="changeEmployeeElementsMode" action="#{pageBeanName.changeEmployeeElementsMode}"/>

                </t:panelGroup>
               
            <!--- End of Row 1-->
            <!--- Start of Row 2-->
                <h:outputText value="#{regResources.dec_title}" styleClass="lable01"/>
                <t:panelGroup colspan="3" >
                    <t:inputTextarea forceId="true" id="maintain_decisionTitle"  styleClass="TextBoxLarge2"  style="width: 600px ! important; height: 45px;" value="#{pageBeanName.pageDTO.decisionTitle}" disabled="#{pageBeanName.showOnly}"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"  rendered="#{!pageBeanName.showOnly}"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator active="#{!financeDecisionCycleMaintainBean.showOnly}" componentToValidate="maintain_decisionTitle" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_decisionTitleID"/>
                </t:panelGroup>

            <!--- End of Row 2-->

            <!--- Start of Row 3-->
               
                <%--<h:outputText value="#{regResources.regulation_template}" styleClass="lable01"/>--%>
                <%--<t:selectOneMenu forceId="true" id="maintain_decisionTemplate" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.templatesDTOKey}" disabled="#{pageBeanName.showOnly}">
                    <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValueString}" itemLabel="#{regResources.regulation_template_label}"/>
                    <t:selectItems value="#{detailBeanName.templates}" itemLabel="#{template.name}" itemValue="#{template.code.key}" var="template"/>
                </t:selectOneMenu>--%>
           
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
                <h:outputText value="#{regResources.textOFDecision}" styleClass="lable01"/>
                <t:panelGroup style="text-align:right;" colspan="3"  dir="rtl">
                    <%--<t:panelGrid columns="3">--%>
                    <t:inputTextarea  style="width: 600px ! important; overflow: auto!important;" forceId="true" id="maintain_decisionText" 
                        styleClass="textareaMedium masterinput_hooma" value="#{pageBeanName.pageDTO.decisionText}" 
                        rendered="#{!pageBeanName.showOnly}" />
                    <t:outputText escape="false"  rendered="#{pageBeanName.showOnly}" forceId="true" id="maintain_regTextforview"
                         value="#{pageBeanName.pageDTO.decisionText}" styleClass="textareaMedium masterinput_hooma" 
                         style="background-color: #dddddd ;display: block;width: 600px ! important;height: 250px !important; border: 1px solid #dddddd;border-radius: 5px;box-shadow: 0 0 6px #ffffff inset;color: #175179;overflow: auto!important;"/>
                       
                       <t:outputText value="*" styleClass="mandatoryAsterisk"  rendered="#{!pageBeanName.showOnly}"/>
                       <f:verbatim>
                        <br/>
                       </f:verbatim>
                        <%--<c2:requiredFieldValidator active="#{!financeDecisionCycleMaintainBean.showOnly}" componentToValidate="maintain_decisionText" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>--%>
                        <c2:customValidator componentToValidate="maintain_decisionText"  errorMessage="#{globalResources.missingField}"
                           function="validateEditor('maintain_decisionText');" display="dynamic"/>
                    <%--</t:panelGrid>--%>
                </t:panelGroup>
              <%--<t:panelGroup colspan="4"  style="text-align: center; display: block;">
                   <t:commandButton forceId="true" styleClass="cssButtonMedium"  id="editor" value="#{regResources.editorDectionLabel}"  action="#{financeDecisionCycleMainDataMaintainBean.openEditor}" disabled="true"/>
                   <t:commandButton forceId="true" styleClass="cssButtonMedium"  id="materialEditor"  disabled="true" value="#{regResources.materialEditorDectionLabel}"  action="#{financeDecisionCycleMainDataMaintainBean.openEditor}" />
              </t:panelGroup>--%>
             
            </t:panelGrid>
            </t:panelGroup>
            <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="clndr_maintain_decisionDate,210,25" />
            <t:inputText forceId="true" id="yearAndYearDateMsgErrorDecId" value="#{regResources.yearAndYearDateMsgErrorDecision}" disabled="true" styleClass="textboxnodefocus"/>
            
            
<script type="text/javascript">

initMCEEditor("maintain_decisionText");

//tinymce.activeEditor.getBody().contenteditable = false;

//tinyMCE.get('maintain_decisionText').getBody().setAttribute('contenteditable', false);

/*tinyMCE.init({
    setup: function(ed) {
        if ($('#'+ed.id).prop('readonly')) {
            ed.settings.readonly = true;
        }
    }
});*/

//tinymce.EditorManager.execCommand('mceToggleEditor', true, "maintain_decisionText");
//tinymce.get('editor_id').getDoc().designMode = 'Off';
/*for (editor_id in tinyMCE.editors) { 
    alert(editor_id);
    if (editor_id.length > 2) { //there are twelve editors in my project so ignore two-digit IDs
        tinyMCE.editors[editor_id].getBody().setAttribute('readonly', '1');
        tinymce.EditorManager.execCommand('mceRemoveControl', true, editor_id);
        tinymce.EditorManager.execCommand('mceRemoveEditor', true, editor_id);
        tinymce.EditorManager.execCommand('mceAddControl', true, editor_id);
        tinymce.EditorManager.execCommand('mceAddEditor', true, editor_id);
    }
}*/
  function setFocusAtMyFirstElement() {
      //document.getElementById("myForm:content1Div").scrollTop = 0;
      if (document.getElementById('maintain_regTypeAdd') != null)
          setFocusOnlyOnElement('maintain_regTypeAdd');
      else if (document.getElementById('maintain_decisionTitle') != null)
          setFocusOnlyOnElement('maintain_decisionTitle').focus();
  }
</script>