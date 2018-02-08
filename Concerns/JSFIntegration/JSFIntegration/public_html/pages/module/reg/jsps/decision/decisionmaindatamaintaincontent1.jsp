<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

        <t:panelGrid width="100%" cellpadding="0" cellspacing="0" align="center">
             <t:outputText value="#{regResources.invalid_decision_key}" styleClass="errMsg"  rendered="#{!detailBeanName.validEntityKey}"/>
         </t:panelGrid>   
            <t:panelGrid columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0">
            <!--- Start of Row 1-->
                <h:outputText value="#{regResources.type}"/>
                <!-- ADD -->
                <%--t:panelGroup rendered="#{pageBeanName.addDecisionMode}">
                    <t:selectOneMenu forceId="true" id="maintain_regTypeAdd" styleClass="DropdownboxMedium2" value="#{detailBeanName.typesDTOKey}">
                        <f:selectItem itemValue="" itemLabel="#{regResources.regulation_type_label}"/>
                        <t:selectItems value="#{detailBeanName.types}" itemLabel="#{type.name}" itemValue="#{type.code.key}" var="type"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                     <t:panelGroup rendered="#{decisionMainDataMaintainBean.showLovDivFlag}" >
                     <a4j:commandButton type="button"  value="..." styleClass="cssButtonSmall" oncomplete="changeVisibilityDiv(window.blocker,window.divLov);" action="#{detailBeanName.openDecisionTypes}"
                      reRender="lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_errorConsole,lov_searchRadioBtn"/>   </t:panelGroup> 
                    <f:verbatim>
                        <br/>
                    </f:verbatim>              
                    <c2:requiredFieldValidator active="#{decisionMaintainBean.addDecisionMode}" componentToValidate="maintain_regTypeAdd" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regTypeID"/>
                </t:panelGroup--%>
                
                <t:panelGroup rendered="#{pageBeanName.maintainMode==0}">
                    <t:selectOneMenu forceId="true" id="maintain_regTypeAdd" styleClass="DropdownboxLarge" value="#{pageBeanName.pageDTO.typesDTOKey}">
                        <f:selectItem itemValue="" itemLabel="#{regResources.regulation_type_label}"/>
                        <t:selectItems value="#{detailBeanName.types}" itemLabel="#{type.name}" itemValue="#{type.code.key}" var="type"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>              
                    <c2:requiredFieldValidator active="#{decisionMaintainBean.addDecisionMode || decisionMaintainBean.cancelDecisionMode}" componentToValidate="maintain_regTypeAdd" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regTypeID"/>
                </t:panelGroup>
                <!-- CANCEL -->
                <h:panelGroup rendered="#{!pageBeanName.addDecisionMode}">
                <t:inputText forceId="true" disabled="true" readonly="true" id="maintain_regTypeEditCancel" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.typesDTO.name}"/>
                    <%--t:selectOneMenu readonly="true" id="maintain_regTypeEditCancel" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.typesDTOKey}">
                        <t:selectItems value="#{detailBeanName.types}" itemLabel="#{type.name}" itemValue="#{type.code.key}" var="type"/>
                    </t:selectOneMenu--%>
                </h:panelGroup>
                <%--<h:panelGroup rendered="#{!pageBeanName.addDecisionMode}">
                <t:inputText forceId="true" disabled="true" readonly="true" id="maintain_regTypeEditCancel" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.typesDTO.name}"/>
                    t:selectOneMenu readonly="true" id="maintain_regTypeEditCancel" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.typesDTOKey}">
                        <t:selectItems value="#{detailBeanName.types}" itemLabel="#{type.name}" itemValue="#{type.code.key}" var="type"/>
                    </t:selectOneMenu
                </h:panelGroup>--%>
                <!-- EDIT -->
                <h:panelGroup rendered="#{pageBeanName.maintainMode==1}">
                    <%--t:selectOneMenu readonly="true" id="maintain_regTypeEdit" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.typesDTOKey}">
                        <t:selectItems value="#{detailBeanName.types}" itemLabel="#{type.name}" itemValue="#{type.code.key}" var="type"/>
                    </t:selectOneMenu --%>
                    <t:inputText forceId="true" disabled="true" readonly="true" id="maintain_regTypeEditEdit" styleClass="textboxlarge" value="#{pageBeanName.pageDTO.typesDTO.name}"/>
                </h:panelGroup>
                
                <h:outputText value="#{regResources.issuance_year}" />
                
                <h:panelGroup rendered="#{pageBeanName.maintainMode==0}">
                    <t:selectOneMenu forceId="true" id="maintain_regIssuanceYearAdd" styleClass="textbox2" value="#{pageBeanName.pageDTO.yearsDTOKey}">
                        <f:selectItem itemValue="" itemLabel="#{regResources.regulation_issuance_year_label}"/>
                        <t:selectItems value="#{detailBeanName.issuanceYears}" itemLabel="#{year.code.key}" itemValue="#{year.code.key}" var="year"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator active="#{decisionMaintainBean.maintainMode==0}" componentToValidate="maintain_regIssuanceYearAdd" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regIssuanceYearID"/>
                </h:panelGroup>
                <t:inputText rendered="#{pageBeanName.maintainMode==1}" forceId="true" disabled="true" readonly="true" id="maintain_regIssuanceYearEdit" styleClass="decisiontextboxmedium" value="#{pageBeanName.pageDTO.yearsDTO.code.key}"/>
                <%--t:selectOneMenu rendered="#{pageBeanName.maintainMode==1}" readonly="true" forceId="true" id="maintain_regIssuanceYearEdit" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.yearsDTOKey}">
                    <t:selectItems value="#{detailBeanName.issuanceYears}" itemLabel="#{year.name}" itemValue="#{year.code.key}" var="year"/>
                </t:selectOneMenu--%>
            <!--- End of Row 1-->
            <!--- Start of Row 2-->
                <h:outputText value="#{regResources.decision_number}"/>
                <h:panelGroup rendered="#{pageBeanName.maintainMode==0}">
                    <t:inputText forceId="true" id="maintain_decisionNumber" maxlength="10" onkeyup="disableCharacters(this)" styleClass="textbox" value="#{pageBeanName.pageDTO.decisionNumber}"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator active="#{decisionMaintainBean.maintainMode==0}" componentToValidate="maintain_decisionNumber" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_decisionNumberID"/>
                </h:panelGroup>
                <h:panelGroup rendered="#{pageBeanName.maintainMode==1}">
                    <t:inputText forceId="true" disabled="true" readonly="true" id="maintain_decisionNumberEdit" styleClass="textbox" value="#{detailBeanName.masterEntityKey.decisionNumber}"/>
                </h:panelGroup>              
                 <t:panelGroup colspan="2"/>
                <h:outputText value="#{regResources.decision_description}" />
                <t:panelGroup colspan="3">
                    <t:inputText forceId="true" id="maintain_decisionTitle" maxlength="400" styleClass="regTiTleTextBoxDecision" value="#{pageBeanName.pageDTO.decisionTitle}"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator active="true" componentToValidate="maintain_decisionTitle" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_decisionTitleID"/>
                </t:panelGroup>
                
            <!--- End of Row 2-->            
                <h:outputText rendered="#{pageBeanName.maintainMode==1}" value="#{regResources.auto_number}"/>
                <t:inputText rendered="#{pageBeanName.maintainMode==1}" disabled="true" readonly="true" forceId="true" id="maintain_autoNumberEdit" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.regAutoNumber}"/>
                <h:outputText rendered="#{pageBeanName.maintainMode==1}" value="#{regResources.status}"/>
                <t:inputText rendered="#{pageBeanName.maintainMode==1 && pageBeanName.pageDTO.canceledDecision}" disabled="true" readonly="true" forceId="true" styleClass="decisiontextboxmedium" value="#{regResources.decision_status_text_canceled}"/>
                <t:inputText rendered="#{pageBeanName.maintainMode==1 && pageBeanName.pageDTO.validDecision}" disabled="true" readonly="true" forceId="true" styleClass="decisiontextboxmedium" value="#{regResources.decision_status_text_valid}"/>
                <t:inputText rendered="#{pageBeanName.maintainMode==1 && pageBeanName.pageDTO.cancelDecision}" disabled="true" readonly="true" forceId="true" styleClass="decisiontextboxmedium" value="#{regResources.decision_status_text_cancel}"/>
            <!--- Start of Row 3-->
                <h:outputText value="#{regResources.subject}"/>
                
                <h:panelGroup rendered="#{!pageBeanName.cancelDecisionMode}">
                    <t:selectOneMenu forceId="true" id="maintain_decisionSubject" styleClass="DropdownboxLarge" value="#{pageBeanName.pageDTO.subjectsDTOKey}">
                        <f:selectItem itemValue="" itemLabel="#{regResources.decision_subject_label}"/>
                        <t:selectItems value="#{detailBeanName.subjects}" itemLabel="#{subject.name}" itemValue="#{subject.code.key}" var="subject"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <t:panelGroup rendered="#{decisionMainDataMaintainBean.showLovDivFlag}" >
                     <a4j:commandButton type="button"  value="..." styleClass="cssButtonSmall" oncomplete="changeVisibilityDiv(window.blocker,window.divLov);" action="#{detailBeanName.openDecisionSubjects}"
                      reRender="lov_dataT_data_panel,lov_paging_panel,lov_searchPanel,lov_errorConsole,lov_searchRadioBtn"/></t:panelGroup> 
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator active="#{!decisionMaintainBean.cancelDecisionMode}" componentToValidate="maintain_decisionSubject" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_decisionSubjectID"/>
                </h:panelGroup>
                <%--t:inputText rendered="#{pageBeanName.cancelDecisionMode}" forceId="true" disabled="true" readonly="true" id="maintain_decisionSubjectCancel" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.subjectsDTO.name}"/>
                <h:panelGroup rendered="#{pageBeanName.cancelDecisionMode}">
                    <t:selectOneMenu forceId="true" readonly="true" id="maintain_decisionSubjectCancel" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.subjectsDTOKey}">
                        <t:selectItems value="#{detailBeanName.subjects}" itemLabel="#{subject.name}" itemValue="#{subject.code.key}" var="subject"/>
                    </t:selectOneMenu>
                </h:panelGroup--%>
                
                <h:outputText value="#{regResources.decisions_decision_maker}" />
                <h:panelGroup rendered="#{!pageBeanName.cancelDecisionMode}">
                    <t:selectOneMenu forceId="true" id="maintain_decisionDecisionMakerAdd" styleClass="DropdownboxLarge" value="#{pageBeanName.pageDTO.decisionMakerDTOKey}">
                        <f:selectItem itemValue="" itemLabel="#{regResources.regulation_decision_maker_label}"/>
                        <t:selectItems value="#{detailBeanName.decisionMakers}" itemLabel="#{dMaker.name}" itemValue="#{dMaker.code.key}" var="dMaker"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator active="#{!decisionMaintainBean.cancelDecisionMode}" componentToValidate="maintain_decisionDecisionMakerAdd" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_decisionDecisionMakerID"/>
                </h:panelGroup>
                <%--t:inputText rendered="#{pageBeanName.cancelDecisionMode}" forceId="true" disabled="true" readonly="true" id="maintain_decisionDecisionMakerCancel" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.decisionMakerTypesDTO.name}"/>
                <h:panelGroup rendered="#{pageBeanName.cancelDecisionMode}">
                    <t:selectOneMenu forceId="true" readonly="true" id="maintain_decisionDecisionMakerCancel" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.decisionMakerDTOKey}">
                        <t:selectItems value="#{detailBeanName.decisionMakers}" itemLabel="#{dMaker.name}" itemValue="#{dMaker.code.key}" var="dMaker"/>
                    </t:selectOneMenu>
                </h:panelGroup--%>
            <!--- End of Row 3-->
            <!--- Start of Row 4-->
                <h:outputText value="#{regResources.decision_date}"/>
                <h:panelGroup>
                    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.pageDTO.decisionDate}" id="clndr_maintain_decisionDate"
                           maxlength="10" styleClass="textbox" currentDayCellClass="currentDayCell"
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" disabled="#{detailBeanName.dateDisabled}">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                     <t:outputText forceId="true" id="vaildateIssuanceYearWithDecitionDateId" value="" styleClass="errMsg"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:dateFormatValidator componentToValidate="clndr_maintain_decisionDate"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"
                                    active="#{!detailBeanName.dateDisabled}"/>
                    <c2:requiredFieldValidator componentToValidate="clndr_maintain_decisionDate" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"  uniqueId="maintain_decisionDateID" active="#{!detailBeanName.dateDisabled}"/>
                </h:panelGroup>
                
                <h:outputText value="#{regResources.applying_date}"/>
                <h:panelGroup>
                    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.pageDTO.decisionAppliedDate}" id="clndr_maintain_decisionAppliedDate"
                            maxlength="10" styleClass="textbox" currentDayCellClass="currentDayCell"
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" disabled="#{detailBeanName.dateDisabled}">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:dateFormatValidator componentToValidate="clndr_maintain_decisionAppliedDate"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"
                                    active="#{!detailBeanName.dateDisabled}"/>
                    <c2:requiredFieldValidator componentToValidate="clndr_maintain_decisionAppliedDate" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"  uniqueId="maintain_decisionAppliedDateID" active="#{!detailBeanName.dateDisabled}"/>
                    <c2:compareDateValidator componentToValidate="clndr_maintain_decisionDate" componentToCompare="clndr_maintain_decisionAppliedDate" operator="before" display="dynamic" errorMessage="#{regResources.error_message_decision_date_must_be_applied_date}" highlight="false" active="#{!detailBeanName.dateDisabled}"/>
                </h:panelGroup>
            <!--- End of Row 4-->
            <!--- Start of Row 5-->
                <h:outputText value="#{regResources.decision_text}"/>
                <t:panelGroup colspan="3">
                <t:panelGroup>
                    <t:inputTextarea forceId="true" id="maintain_decisionText" value="#{pageBeanName.pageDTO.decisionText}" styleClass="decisionMainDataTextArea"/>
                    <%--cols="72" rows="3"  --%>
                    <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator componentToValidate="maintain_decisionText" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"  uniqueId="maintain_regTextID"/>
                </t:panelGroup>
                </t:panelGroup>
            <!--- End of Row 5-->
            <!--- Start of Row 6-->
                <h:outputText value="#{regResources.regulation_image}"/>
                <t:panelGroup>
                    <f:verbatim><div style="position: relative;"></f:verbatim>
                        <t:inputFileUpload id="myInputFileUpload" styleClass="fileUploadComponent" onchange="document.getElementById('myInputText').value=this.value;" storage="file" accept="image/*" value="#{detailBeanName.decImageUploadedFile}" onblur="document.getElementById('maintain_decisionTemplate').focus();" />
                        <t:inputText id="myInputText" onkeydown="disableEditing(event)" value="#{detailBeanName.fakeImageString}" forceId="true" styleClass="fileUploadFakeInputText"/>
                        <f:verbatim>&nbsp;</f:verbatim>
                        <h:commandButton styleClass="fileUploadFakeButton" value="#{regResources.browse}" onclick="return false;"/>
                        <%--t:inputHidden forceId="true" id="fakeImageString" value="#{detailBeanName.fakeImageString}"/--%>
                    <f:verbatim></div></f:verbatim>
                    <t:outputText value="#{regResources.invalid_image_type}" styleClass="errMsg" rendered="#{detailBeanName.invalidImageType}"/>
                    <t:outputText value="#{regResources.uploading_error}" styleClass="errMsg" rendered="#{detailBeanName.uploadingError}"/>                                            
                </t:panelGroup>

            <!--- End of Row 6-->
            <!--- Start of Row 7-->
                <h:outputText value="#{regResources.regulation_template}" />
                <t:selectOneMenu forceId="true" id="maintain_decisionTemplate" styleClass="decisionDropdownboxMedium2" value="#{pageBeanName.pageDTO.templatesDTOKey}">
                    <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValueString}" itemLabel="#{regResources.regulation_template_label}"/>
                    <t:selectItems value="#{detailBeanName.templates}" itemLabel="#{template.name}" itemValue="#{template.code.key}" var="template"/>
                </t:selectOneMenu>
            <!--- End of Row 7-->
            </t:panelGrid>
             <f:verbatim><br/></f:verbatim>
             <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="clndr_maintain_decisionDate,210,100:clndr_maintain_decisionAppliedDate,175,100" />
             <t:inputText forceId="true" id="yearAndYearDateMsgErrorDecId" value="#{regResources.yearAndYearDateMsgErrorDecision}" disabled="true" styleClass="textboxnodefocus"/>
 <script type="text/javascript">   
  setFocusAtMyFirstElement();
   function setFocusAtMyFirstElement(){
        document.getElementById("myForm:content1Div").scrollTop = 0;
       if(document.getElementById('maintain_regTypeAdd') !=null)
             setFocusOnlyOnElement('maintain_regTypeAdd');
       else if(document.getElementById('maintain_decisionTitle') !=null)
            setFocusOnlyOnElement('maintain_decisionTitle').focus();
     }   
 </script>
 