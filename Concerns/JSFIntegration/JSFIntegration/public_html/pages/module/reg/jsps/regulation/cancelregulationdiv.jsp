<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>

<t:panelGroup forceId="true" id="divCancelingReg" style="width:635px;">

    <t:panelGrid align="center">
          <t:outputLabel value="#{regResources.cancel_regulation}" styleClass="TitlePage"/>
    </t:panelGrid>

   <t:panelGrid border="0" columns="6" rowClasses="row01,row02" columnClasses="col2,col2,col2,col2,col2,col2" cellpadding="3" id="cancel_Div" forceId="true" cellspacing="0" width="100%">
    
     <t:panelGroup colspan="6" style="background-color:#FFFFFF">
        <htm:table width="100%">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg"  width="9" height="9"/>
                </htm:td>
                <htm:td styleClass="group_title">
                    &nbsp;
                    <t:outputLabel value="#{regResources.regulation_master_title}" styleClass="lable01"/>
                </htm:td>
            </htm:tr>
            <htm:tr>
                <htm:td colspan="2" height="1">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg"  width="100%" height="1"/>
                </htm:td>
            </htm:tr>
        </htm:table>
    </t:panelGroup>
    
    <h:outputText id="header_type" value="#{regResources.reg_type}" styleClass="lable01"/>
    <h:inputText id="reg_type" value="#{regulationListBean.selectedDTOS[0].typesDto.name}" disabled="true" styleClass="textbox2"/>
    <h:outputText id="header_reg_year" value="#{regResources.reg_year}" styleClass="lable01"/>
    <h:inputText id="reg_year" value="#{regulationListBean.selectedDTOS[0].yearsDto.code.key}"  disabled="true" styleClass="customTextbox2"/>
    <h:outputText id="header_reg_number" value="#{regResources.reg_number}" styleClass="lable01"/>
    <h:inputText id="reg_number" value="#{regulationListBean.selectedDTOS[0].code.regulationNumber}"  disabled="true" styleClass="customTextbox2"/>
    
    <h:outputText id="header_reg_title" value="#{regResources.reg_title}" styleClass="lable01"/>
    <t:panelGroup colspan="5">
       <t:inputText id="reg_title" value="#{regulationListBean.selectedDTOS[0].regulationTitle}" styleClass="supreTextBoxLarge" disabled="true" style="width:537px"/>
    </t:panelGroup>
    
           
    <t:panelGroup colspan="6" style="background-color:#FFFFFF">
        <htm:table width="100%">
            <htm:tr>
                <htm:td width="9">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg"
                             width="9" height="9"/>
                </htm:td>
                <htm:td styleClass="group_title">
                    &nbsp;
                    <t:outputLabel value="#{regResources.regulation_cancel_title}"  styleClass="lable01"/>
                </htm:td>
            </htm:tr>
            <htm:tr>
                <htm:td colspan="2" height="1">
                    <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg"
                             width="100%" height="1"/>
                </htm:td>
            </htm:tr>
        </htm:table>
    </t:panelGroup>
    
    </t:panelGrid>
    <t:panelGrid border="0" columns="3" rowClasses="row01,row02" columnClasses="col2,col2" cellpadding="3" id="cancel_Div_2" forceId="true" cellspacing="0" width="100%">
    
    <t:panelGroup colspan="1">                          
         <h:outputText id="header_decision_maker" value="#{regResources.cancel_decision_maker}" styleClass="divtext"/>
    </t:panelGroup>
    <t:panelGroup colspan="2" rendered="#{! empty regulationListBean.selectedDTOS}">
        <t:selectOneMenu forceId="true" id="DecisionMakerSelect" value="#{regulationListBean.selectedDTOS[0].cancelMakerDTOKey}" onblur="setFocusOnlyOnElement('CancelReasonsSelect');" styleClass="textboxmedium">
            <f:selectItem itemLabel="#{regResources.select_decision_maker}" itemValue="" />
            <t:selectItems var="decisionMaker" value="#{regulationListBean.decisionMakersList}" itemLabel="#{decisionMaker.name}" itemValue="#{decisionMaker.code.key}"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:requiredFieldValidator componentToValidate="DecisionMakerSelect" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="regCancelAppliedDateID"  active="#{regulationListBean.cancelDivDisplayed}"/>
    </t:panelGroup>
    <t:panelGroup colspan="1">
      <h:outputText id="header_cancel_reason" value="#{regResources.cancellation_reason}" styleClass="divtext"/>
    </t:panelGroup>
    <t:panelGroup colspan="2" rendered="#{! empty regulationListBean.selectedDTOS}">
        <t:selectOneMenu forceId="true" id="CancelReasonsSelect" value="#{regulationListBean.selectedDTOS[0].cancelReasonDTOKey}"  styleClass="textboxmedium">
            <f:selectItem itemLabel="#{regResources.select_cancel_reason}" itemValue="" />
            <t:selectItems var="cancelReason" value="#{regulationListBean.cancelReasonsList}" itemLabel="#{cancelReason.name}" itemValue="#{cancelReason.code.key}"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>                                    
        <f:verbatim>
            <br/>
        </f:verbatim>                                    
        <c2:requiredFieldValidator componentToValidate="CancelReasonsSelect" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="regCancelAppliedDateID" active="#{regulationListBean.cancelDivDisplayed}"/>
    </t:panelGroup>
    <t:panelGroup colspan="1">
    <h:outputText id="header_cancel_date" value="#{regResources.cancel_applied_date}" styleClass="divtext"/>
    </t:panelGroup>
    <t:panelGroup colspan="2" rendered="#{! empty regulationListBean.selectedDTOS}">
        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" styleClass="textbox" 
                forceId="true" id="regCancelAppliedDate" size="20" maxlength="#{pageBeanName.calendarTextLength}" renderAsPopup="true"
                currentDayCellClass="currentDayCell" value="#{regulationListBean.selectedDTOS[0].regulationCancelAppliedDate}"
                align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
            <f:converter converterId="TimeStampConverter"/>                                    
        </t:inputCalendar>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:dateFormatValidator componentToValidate="regCancelAppliedDate" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" uniqueId="search_regDateTo_divID" active="#{regulationListBean.cancelDivDisplayed}"/>
            
        <t:inputHidden id="currentRegDate" value="#{pageBeanName.selectedDTOS[0].regulationDate}">
            <f:converter converterId="TimeStampConverter"/>
        </t:inputHidden>
     
        <c2:compareDateValidator componentToValidate="currentRegDate" componentToCompare="regCancelAppliedDate" operator="before" display="dynamic" errorMessage="#{regResources.reg_cancel_applied_date_must_be_greater_than_reg_date_modefiedMSg}" highlight="false" uniqueId="search_regDateTo_divIDx" active="#{regulationListBean.cancelDivDisplayed}"/>
       
        <c2:requiredFieldValidator componentToValidate="regCancelAppliedDate" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="regCancelAppliedDateID"  active="#{regulationListBean.cancelDivDisplayed}"/>
       
    </t:panelGroup>
 
  
   </t:panelGrid>
   
   <t:panelGrid columns="2" align="center">
    <t:commandButton forceId="true" id="okButtonCustomDiv1" value="#{globalResources.ok}" onclick="doValidatemyForm=true;if(validatemyForm()){ block(); return true;}return false;" action="#{regulationListBean.cancelRegulation}" styleClass="cssButtonSmall"/>
    <h:panelGroup>
        <t:commandButton forceId="true" id="backButtonCustomDiv1" onblur="setFocusOnlyOnElement('DecisionMakerSelect');" onclick="cancelDivBackJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
        <a4j:jsFunction name="cancelDivBackJsFunction" action="#{regulationListBean.hideCancelDiv}" oncomplete="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.customDiv1,null,null); foucsAddbuttonOnList();"/>
    </h:panelGroup>
    <%--t:commandButton forceId="true" id="backButtonCustomDiv1" onblur="setFocusAtMyCustom1Div();" type="button" value="#{globalResources.back}" onclick="ignoremyFormValidation();hideLookUpDiv(window.blocker,window.customDiv1,null,null);" styleClass="cssButtonSmall"/--%>
</t:panelGrid>


   </t:panelGroup>
 <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="regCancelAppliedDate,400,250:search_regDateFrom,250,250:search_regDateTo,300,250" />
