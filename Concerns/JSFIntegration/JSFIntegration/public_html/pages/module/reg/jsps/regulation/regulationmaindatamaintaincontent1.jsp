<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

        <t:messages/>
            <f:verbatim><br/></f:verbatim>            
            <t:panelGrid columns="4" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0">
            <!--- Start of Row 1-->
                <h:outputText value="#{regResources.type}"/>
                <t:inputText rendered="#{(pageBeanName.maintainMode==1 && pageBeanName.copyFlage==false) ||  pageBeanName.maintainMode==2 }" forceId="true" disabled="true" readonly="true" id="maintain_regTypeEdit" styleClass="textboxlarge" value="#{pageBeanName.pageDTO.typesDto.name}"/>
                <%--t:selectOneMenu readonly="true" forceId="true" id="maintain_regTypeEdit" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.typesDTOKey}" rendered="#{pageBeanName.maintainMode==1}">
                    <t:selectItems value="#{detailBeanName.types}" itemLabel="#{type.name}" itemValue="#{type.code.key}" var="type"/>
                </t:selectOneMenu--%>
                <h:panelGroup rendered="#{pageBeanName.maintainMode==0 || pageBeanName.copyFlage==true}">
                    <t:selectOneMenu forceId="true" id="maintain_regType" styleClass="DropdownboxLarge" disabled="#{pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2}" value="#{pageBeanName.pageDTO.typesDTOKey}" onchange="clearErrMsgs();">
                        <f:selectItem itemValue="" itemLabel="#{regResources.regulation_type_label}"/>
                        <t:selectItems value="#{detailBeanName.types}" itemLabel="#{type.name}" itemValue="#{type.code.key}" var="type"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"  rendered="#{pageBeanName.maintainMode==0}"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>              
                    <c2:requiredFieldValidator active="#{regulationMaintainBean.maintainMode==0 ||  regulationMaintainBean.copyFlage==true}" componentToValidate="maintain_regType" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regTypeID"/>
                </h:panelGroup>
                
                <h:outputText value="#{regResources.issuance_year}" />
                <t:inputText rendered="#{(pageBeanName.maintainMode==1 && pageBeanName.copyFlage==false) ||  pageBeanName.maintainMode==2  }" forceId="true" disabled="true" readonly="true" id="maintain_regIssuanceYearEdit" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.yearsDto.code.key}"/>
                <%--t:selectOneMenu readonly="true" forceId="true" id="maintain_regIssuanceYearEdit" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.yearsDTOKey}" rendered="#{pageBeanName.maintainMode==1}">
                        <t:selectItems value="#{detailBeanName.issuanceYears}" itemLabel="#{year.name}" itemValue="#{year.code.key}" var="year"/>
                </t:selectOneMenu--%>
                <h:panelGroup rendered="#{pageBeanName.maintainMode==0 || pageBeanName.copyFlage==true}">
                   <t:selectOneMenu forceId="true" id="maintain_regIssuanceYear" styleClass="Dropdownbox" value="#{pageBeanName.pageDTO.yearsDTOKey}" disabled="#{pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2}" onchange="clearErrMsgs();">
                        <f:selectItem itemValue="" itemLabel="#{regResources.regulation_issuance_year_label}"/>
                        <t:selectItems value="#{detailBeanName.issuanceYears}" itemLabel="#{year.code.key}" itemValue="#{year.code.key}" var="year"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"  rendered="#{pageBeanName.maintainMode==0}"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator active="#{regulationMaintainBean.maintainMode==0 || regulationMaintainBean.copyFlage==true}" componentToValidate="maintain_regIssuanceYear" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regIssuanceYearID"/>
                </h:panelGroup>
            <!--- End of Row 1-->
            <!--- Start of Row 2-->
              <h:outputText value="#{regResources.regulation_number}"/>
                <h:panelGroup rendered="#{(pageBeanName.maintainMode==1 && pageBeanName.copyFlage==false) || pageBeanName.maintainMode==2 }">
                    <t:inputText disabled="true" readonly="true" forceId="true"  id="maintain_regNumberEdit" styleClass="textbox" value="#{detailBeanName.masterEntityKey.regulationNumber}"/>
                </h:panelGroup>
                <%--h:panelGroup rendered="#{pageBeanName.maintainMode==1}"--%> 
                    <h:outputText value="#{regResources.auto_number}"  rendered="#{(pageBeanName.maintainMode==1 && pageBeanName.copyFlage==false) || pageBeanName.maintainMode==2 }"/>                   
                    <t:inputText disabled="true" readonly="true" forceId="true"  id="maintain_regAutoNumberEdit" styleClass="textbox" value="#{pageBeanName.pageDTO.regAutoNumber}" rendered="#{(pageBeanName.maintainMode==1 && pageBeanName.copyFlage==false) || pageBeanName.maintainMode==2 }"/>
                <%--/h:panelGroup--%>
                <h:panelGroup rendered="#{pageBeanName.maintainMode==0 ||  pageBeanName.copyFlage==true}">
                    <t:inputText forceId="true" id="maintain_regNumber" maxlength="10" onkeypress="keyPressNumber(false,event);" onkeyup="disableCharacters(this)" styleClass="textbox" value="#{pageBeanName.pageDTO.regulationNumber}"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator active="#{regulationMaintainBean.maintainMode==0 ||  regulationMaintainBean.copyFlage==true}" componentToValidate="maintain_regNumber" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regNumberID"/>
                </h:panelGroup>
                <t:panelGroup colspan="2" rendered="#{pageBeanName.maintainMode==0 ||  pageBeanName.copyFlage==true}">
                    <t:outputText forceId="true" id="invalidRegulationKeyErrMsg" value="#{regResources.regulation_existing_record}" styleClass="errMsg" rendered="#{(pageBeanName.maintainMode==0 || pageBeanName.copyFlage==true) && detailBeanName.invalidRegulationKey}"/>
                </t:panelGroup>
                
            <!--- End of Row 2-->    
                <h:outputText value="#{regResources.regulation_description}"/>
                <t:panelGroup colspan="3">
                    <t:inputText forceId="true" id="maintain_regTitle" maxlength="400" styleClass="regTiTleTextBox" value="#{pageBeanName.pageDTO.regulationTitle}" disabled="#{pageBeanName.maintainMode==2}"/>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode!=2}" />
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator componentToValidate="maintain_regTitle" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regTitleID"/>
                </t:panelGroup>
            
            <!--- Start of Row 3-->
                <h:outputText value="#{regResources.status}"/>
                <t:inputText rendered="#{detailBeanName.disableStatusMenu && pageBeanName.copyFlage==false}" forceId="true" disabled="true" readonly="true" id="maintain_regStatusReadOnly" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.statusDto.name}"/>
                <%--t:selectOneMenu rendered="#{detailBeanName.disableStatusMenu}" forceId="true" id="maintain_regStatusReadOnly" readonly="true" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.statusDTOKey}">
                    <t:selectItems value="#{detailBeanName.status}" itemLabel="#{status.name}" itemValue="#{status.code.key}" var="status"/>
                </t:selectOneMenu--%>                    
                <h:panelGroup rendered="#{!detailBeanName.disableStatusMenu || pageBeanName.copyFlage==true}">
                    <t:selectOneMenu forceId="true" id="maintain_regStatus" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.statusDTOKey}" disabled="#{pageBeanName.maintainMode==2}" >
                        <t:selectItems value="#{detailBeanName.status}" itemLabel="#{status.name}" itemValue="#{status.code.key}" var="status"/>
                    </t:selectOneMenu>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode!=2}" />
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator active="#{!regulationMainDataMaintainBean.disableStatusMenu}" componentToValidate="maintain_regStatus" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regStatusID"/>
                </h:panelGroup>
                
                <h:outputText value="#{regResources.regulation_scope}" />
                <h:panelGroup>
                    <t:selectOneMenu forceId="true" id="maintain_regScope" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.regulationScopeDTOKey}" disabled="#{pageBeanName.maintainMode==2}">
                        <f:selectItem itemValue="" itemLabel="#{regResources.regulation_scope_label}"/>
                        <t:selectItems value="#{detailBeanName.scopes}" itemLabel="#{scope.name}" itemValue="#{scope.code.key}" var="scope"/>    
                    </t:selectOneMenu>
                    
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode!=2}" />
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator componentToValidate="maintain_regScope" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regScopeID"/>
                </h:panelGroup>
            <!--- End of Row 3-->
            <h:outputText value="#{regResources.regulation_decision_maker}" />
            <h:panelGroup>
                <t:selectOneMenu forceId="true" id="maintain_regDecisionMaker" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.decisionMakerDTOKey}" disabled="#{pageBeanName.maintainMode==2}">
                    <f:selectItem itemValue="" itemLabel="#{regResources.regulation_decision_maker_label}"/>
                    <t:selectItems value="#{detailBeanName.decisionMakers}" itemLabel="#{dMaker.name}" itemValue="#{dMaker.code.key}" var="dMaker"/>
                </t:selectOneMenu>
                <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode!=2}" />
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <c2:requiredFieldValidator componentToValidate="maintain_regDecisionMaker" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="maintain_regDecisionMakerID"/>
            </h:panelGroup>
            <t:panelGroup colspan="2"/>
            <!--- Start of Row 4-->
                <h:outputText value="#{regResources.regulation_parent}"/>
             <t:panelGroup colspan="3" forceId="true" id="regParentPanelID">
                    <t:inputText forceId="true"  id="maintain_regParent" disabled="true" styleClass="#{pageBeanName.maintainMode==0?'regParentTiTleTextBox':'regTiTleTextBox'}" value="#{(pageBeanName.pageDTO.parentRegulationDTO != null) ? pageBeanName.pageDTO.parentRegulationDTO.regulationTitle :''}"/>
                    <%-- styleClass="#{pageBeanName.maintainMode==0?'regParentTiTleTextBox':'regTiTleTextBox'}" --%>
                    <f:verbatim> &nbsp;</f:verbatim>
                    <%--t:commandButton forceId="true" rendered="#{pageBeanName.maintainMode==0}" styleClass="cssButtonSmall" id="btn_regParentOpenDiv" value="..." onclick="javascript:changeVisibilityDiv(window.blocker,window.lookupAddDiv); setFocusOnlyOnElement('div_maintain_regNumber'); return(false);"/--%>
                    <t:commandButton forceId="true" styleClass="cssButtonSmall" id="btn_regParentOpenDiv" value="..." onclick="openSearchAvailableModefiedReg(); return false;" rendered="#{pageBeanName.maintainMode!=2}">
                        <a4j:jsFunction name="openSearchAvailableModefiedReg" actionListener="#{detailBeanName.openSearchAvailableModefiedReg}" oncomplete="javascript:changeVisibilityDiv(window.blocker,window.lookupAddDiv); setFocusOnlyOnElement('div_maintain_regNumber'); return(false);" rendered="#{pageBeanName.maintainMode==0}" reRender="regulationParentSelectionDiv"/>
                    </t:commandButton>
                    <f:verbatim> &nbsp;&nbsp;</f:verbatim>
                   <a4j:commandButton id="reset_data_btn_id" value="#{regResources.reSetButtonErase}"  rendered="#{pageBeanName.pageDTO.parentRegulationDTO != null && pageBeanName.maintainMode!=2}" styleClass="cssButtonSmall"  action="#{detailBeanName.resetRegParent}"  
                                            reRender="regParentPanelID"/>
                </t:panelGroup>
                
            <!--- End of Row 4-->
            
            <!--- Start of Row 5-->
                <h:outputText value="#{regResources.regulation_date}"/>
                <h:panelGroup>
                    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.pageDTO.regulationDate}" id="clndr_maintain_regDate"
                            size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" 
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" disabled="#{pageBeanName.maintainMode==2}">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode!=2}" />
                     <t:outputText forceId="true" id="vaildateIssuanceYearWithRegDateId" value="" styleClass="errMsg"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator componentToValidate="clndr_maintain_regDate" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"  uniqueId="maintain_regDateID"/>
                    <c2:dateFormatValidator componentToValidate="clndr_maintain_regDate" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="maintain_regDateFormatID"/>
                </h:panelGroup>
                
                <h:outputText value="#{regResources.regulation_applied_date}"/>
                <h:panelGroup>
                    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.pageDTO.regulationAppliedDate}" id="clndr_maintain_regAppliedDate"
                            size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" 
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true" disabled="#{pageBeanName.maintainMode==2}">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode!=2}" />
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator componentToValidate="clndr_maintain_regAppliedDate" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"  uniqueId="maintain_regAppliedDateID"/>
                    <c2:dateFormatValidator componentToValidate="clndr_maintain_regAppliedDate" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="maintain_regAppliedDateFormatID"/>
                    <%-- modified by m.elsaied 
                    <c2:compareDateValidator componentToValidate="clndr_maintain_regDate" componentToCompare="clndr_maintain_regAppliedDate" operator="before" display="dynamic" errorMessage="#{regResources.error_message_regulartion_date_must_be_applied_date}" highlight="false"/>
                     --%>
                    <c2:compareDateValidator componentToValidate="clndr_maintain_regDate" componentToCompare="clndr_maintain_regAppliedDate" operator="before" display="dynamic" errorMessage="#{regResources.error_message_regulartion_date_must_be_applied_date}" highlight="false"/>
                </h:panelGroup>
            <!--- End of Row 5-->
            
            <!--######################################## just 4 editing mode -->
                <h:outputText rendered="#{(pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2) && pageBeanName.copyFlage==false && detailBeanName.canceledRegulation}" value="#{regResources.cancellation_reason}"/>
                <t:inputText rendered="#{(pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2) && pageBeanName.copyFlage==false && detailBeanName.canceledRegulation}" disabled="true" readonly="true" forceId="true" id="maintain_cancellation_reasonEdit" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.cancelReasonDTO.name}"/>
                
                <h:outputText rendered="#{(pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2) && pageBeanName.copyFlage==false && detailBeanName.canceledRegulation}" value="#{regResources.cancellation_date}"/>
                <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" onblur="return validateInputCalenderFormate(this,'null','null');" rendered="#{(pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2) &&  pageBeanName.copyFlage==false && detailBeanName.canceledRegulation}" disabled="true" readonly="true" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{pageBeanName.pageDTO.regCancelDate}" id="clndr_maintain_cancellation_dateEdit"
                            size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" 
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                        <f:converter converterId="TimeStampConverter"/>
                </t:inputCalendar>
                
                <h:outputText rendered="#{(pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2) && pageBeanName.copyFlage==false && detailBeanName.canceledRegulation}" value="#{regResources.regulation_cancellation_decision_maker}"/>
                <t:inputText rendered="#{(pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2) &&  pageBeanName.copyFlage==false && detailBeanName.canceledRegulation}" forceId="true" disabled="true" readonly="true" id="maintain_reg_cancellation_DecisionMakerEdit" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.cancelMakerDTO.name}"/>
                <%--t:selectOneMenu rendered="#{pageBeanName.maintainMode==1 && detailBeanName.canceledRegulation}" readonly="true" forceId="true" id="maintain_reg_cancellation_DecisionMakerEdit" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.cancelMakerDTO.name}">
                    <t:selectItems value="#{detailBeanName.decisionMakers}" itemLabel="#{dMaker.name}" itemValue="#{dMaker.code.key}" var="dMaker"/>
                </t:selectOneMenu--%>
                
                <t:panelGroup colspan="2" rendered="#{(pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2) &&  pageBeanName.copyFlage==false && detailBeanName.canceledRegulation}">
                <f:verbatim/>
                <f:verbatim/>
                </t:panelGroup>
            <!--######################################## just 4 editing mode -->
            
            <!--- Start of Row 6-->
                <h:outputText value="#{regResources.regulation_text}"/>
                <t:panelGroup colspan="3" >
                <t:panelGroup>
                    <t:inputTextarea forceId="true" id="maintain_regText" value="#{pageBeanName.pageDTO.regulationText}" styleClass="regulationMainDataTextArea" disabled="#{pageBeanName.maintainMode==2}"/>
                    <%--cols="76"  rows="3"  --%>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode!=2}" />
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <c2:requiredFieldValidator componentToValidate="maintain_regText" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"  uniqueId="maintain_regTextID"/>
                </t:panelGroup>
                </t:panelGroup>
            <!--- End of Row 6-->
            <!--- Start of Row 7-->
                <h:outputText value="#{regResources.regulation_image}"/>
                <t:panelGroup>
                    <f:verbatim><div style="position: relative;"></f:verbatim>
                        <t:inputFileUpload id="myInputFileUpload" onblur="setFocusOnElement('maintain_regTemplate');" styleClass="fileUploadComponent" onchange="document.getElementById('myInputText').value=this.value;" storage="file" accept="image/*" value="#{detailBeanName.regImageUploadedFile}"/>
                        <t:inputText id="myInputText" onkeydown="disableEditing(event)" value="#{detailBeanName.fakeImageString}" forceId="true" styleClass="fileUploadFakeInputText"/>
                        <f:verbatim>&nbsp;</f:verbatim>
                        <h:commandButton styleClass="fileUploadFakeButton" value="#{regResources.browse}" onclick="return false;" rendered="#{pageBeanName.maintainMode!=2}"/>
                        <%--t:inputHidden forceId="true" id="fakeImageString" value="#{detailBeanName.fakeImageString}"/--%>
                    <f:verbatim></div></f:verbatim>
                    <t:outputText value="#{regResources.invalid_image_type}" styleClass="errMsg" rendered="#{detailBeanName.invalidImageType}"/>
                    <t:outputText value="#{regResources.uploading_error}" styleClass="errMsg" rendered="#{detailBeanName.uploadingError}"/>                                            
                </t:panelGroup>
                
                <h:outputText value="#{regResources.regulation_template}" />
                <t:selectOneMenu forceId="true" id="maintain_regTemplate" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.templateDTOKey}" onblur="setFocusOnlyOnElement('NextButtonManyToMany'" disabled="#{pageBeanName.maintainMode==2}">
                    <f:selectItem itemValue="#{detailBeanName.itemSelectionRequiredValueString}" itemLabel="#{regResources.regulation_template_label}"/>
                    <t:selectItems value="#{detailBeanName.templates}" itemLabel="#{template.name}" itemValue="#{template.code.key}" var="template"/>
                </t:selectOneMenu>
                <%--h:panelGroup style="padding-left:50px;">
                    <h:outputText value="#{regResources.regulation_publication_on_website}"/>
                    <t:selectBooleanCheckbox forceId="true" id="maintain_publicationFlag" value="#{pageBeanName.pageDTO.pulishOnWebsite}"/>
                </h:panelGroup--%>
            <!--- End of Row 7-->
            <!--- Start of Row 8-->
                
            <!--- End of Row 8-->
            </t:panelGrid>
            <f:verbatim><br/></f:verbatim>

<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="div_clndr_maintain_regDate,350,170:div_clndr_maintain_regAppliedDate,350,170:clndr_maintain_regDate,210,180:clndr_maintain_regAppliedDate,150,197" />

<t:inputHidden forceId="true" id="pageBeanNameMaintainMode" value="#{pageBeanName.maintainMode}" />
<t:inputText forceId="true" id="yearAndYearDateMsgErrorId" value="#{regResources.yearAndYearDateMsgError}" disabled="true" styleClass="textboxnodefocus"/>

<script type="text/javascript"> 
    function clearErrMsgs(){        
        if(document.getElementById('invalidRegulationKeyErrMsg') != null){
            document.getElementById('invalidRegulationKeyErrMsg').innerText='';
        }
    }
</script>