<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
  

<f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration"  var="intgBundle"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:panelGroup forceId="true" id="divEditLookup" >

   <t:panelGrid columns="4" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01">
     
       <t:outputText value="#{scpIntgResources.vac_start_date}" styleClass="lable01"/>
       <t:inputText id="cutvacFromDate" styleClass="textbox" disabled="true" forceId="true" converter="SqlDateConverter" value="#{pageBeanName.selectedPageDTO.fromDate}" />
       <t:outputText value="#{scpIntgResources.vac_end_date}" styleClass="lable01"/>
       <t:inputText id="cutUntilDate" disabled="true" styleClass="textbox" forceId="true" converter="SqlDateConverter" value="#{pageBeanName.selectedPageDTO.untilDate}" />
        <t:outputText value="#{intgBundle.emp_vac_cut_date}" styleClass="lable01" rendered="#{pageBeanName.selectedPageDTO.returnDate !=null}"/>
        <t:inputText id="emp_vac_cut_date" disabled="true" styleClass="textbox" forceId="true" converter="SqlDateConverter" rendered="#{pageBeanName.selectedPageDTO.returnDate !=null}" value="#{pageBeanName.selectedPageDTO.returnDate}" />

        <t:outputText value="#{scpIntgResources.cut_date}" styleClass="lable01"/>
        <t:panelGroup >
            <t:inputCalendar forceId="true" id="nvac_until_date" title="#{globalResources.inputCalendarHelpText}"
                popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                value="#{pageBeanName.selectedPageDTO.replaceEndDate}" size="20"
                maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox"
                currentDayCellClass="currentDayCell" renderAsPopup="true"
                renderPopupButtonAsImage="true" align="#{globalResources.align}"
                popupLeft="#{shared_util.calendarpopupLeft}">
            <f:converter converterId="SqlDateConverter"/>
            </t:inputCalendar>
            
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim> <br/> </f:verbatim>
          
            <c:requiredFieldValidator componentToValidate="nvac_until_date" group="cutdivgroup" 
               display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
            
            <c:compareDateValidator componentToValidate="cutUntilDate" componentToCompare="nvac_until_date"
                operator="after" display="dynamic" group="cutdivgroup" 
                errorMessage="#{scpIntgResources.cut_vac_to_date_msg}"
               />
              <c:compareDateValidator componentToValidate="nvac_until_date" componentToCompare="availableCutDate"
                operator="before" display="dynamic" group="cutdivgroup" 
                errorMessage="#{intgBundle.new_cut_date_available_msg}#{pageBeanName.selectedPageDTO.replaceFromDate}"
               />  
             <c:compareDateValidator componentToValidate="cutvacFromDate" componentToCompare="nvac_until_date"
                operator="before" display="dynamic" group="cutdivgroup" 
                errorMessage="#{scpIntgResources.cut_vac_from_date_msg}#{pageBeanName.selectedPageDTO.replaceFromDate}"
               />
             <c:dateFormatValidator componentToValidate="nvac_until_date" display="dynamic"
               errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"
                 group="cutdivgroup" />
                 
   
    </t:panelGroup>
    <t:panelGroup colspan="2" rendered="#{pageBeanName.selectedPageDTO.returnDate ==null}"/>
 
    </t:panelGrid>


        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.executeVacCut}" onclick="return validatemyForm('cutdivgroup');"/>
          
            <t:commandButton forceId="true" id="CancelButtonEdit" onblur="if(isVisibleComponent('lookupEditDiv'))settingFoucsAtDivEdit();" styleClass="cssButtonSmall" value="#{globalResources.back}">
                <a4j:support disableDefault="true" event="onclick" action="#{pageBeanName.unlock}" oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'edit_first_inputTxt','myForm:errorEdit');settingFoucsAtListPage();return false;" reRender="msgShow"/>
            </t:commandButton>
        </t:panelGrid>
      
    <t:inputHidden id="availableCutDate"  styleClass="textbox" forceId="true" converter="SqlDateConverter" value="#{pageBeanName.selectedPageDTO.replaceFromDate}" />    
</t:panelGroup>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
