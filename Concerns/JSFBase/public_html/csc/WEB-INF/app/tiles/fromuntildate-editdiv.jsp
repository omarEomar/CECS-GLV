<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divEditLookup" >
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error" rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>  
    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg" />
   
     <h:panelGrid columns="1" rendered="#{pageBeanName.showEdit}" width="100%">
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">
            <h:outputText value="#{globalResources.Code}" styleClass="lable01"/>                     
            <h:inputText value="#{pageBeanName.selectedPageDTO.code.key}" styleClass="textboxsmall" disabled="true"/>                     
        
            <h:outputText value="#{globalResources.SearchName}" styleClass="lable01" />
            <h:panelGroup>
                <t:inputText id="edit_first_inputTxt" onblur="if(isVisibleComponent('lookupEditDiv'))document.getElementById('SaveButtonEdit').focus();" onchange="trimBorders(edit_first_inputTxt);"  value="#{pageBeanName.selectedPageDTO.name}" styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" forceId="true"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="edit_first_inputTxt" display="dynamic"  errorMessage="#{globalResources.missingField}"/>
            </h:panelGroup> 
    <!-- fromDate-untilDate-statusFlag -->
        <t:outputText value="#{globalResources.from_Date}" styleClass="lable01"/>
        <t:panelGroup >
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="validFromDateEditId" size="20" maxlength="200" styleClass="textboxmedium" 
                             style="width:188px;"
                             currentDayCellClass="currentDayCell" value="#{pageBeanName.selectedPageDTO.validFromDate}"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                <f:converter converterId="TimeStampConverter"/>
            </t:inputCalendar>
             <c:dateFormatValidator componentToValidate="validFromDateEditId"
                                   errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"/>
        </t:panelGroup>
        <t:outputText value="#{globalResources.until_date}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}"
                             popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true" id="validUntilDateEditId" size="20" maxlength="200" styleClass="textboxmedium"
                             style="width:188px;"
                             currentDayCellClass="currentDayCell" value="#{pageBeanName.selectedPageDTO.validUntilDate}"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                <f:converter converterId="TimeStampConverter"/>
            </t:inputCalendar>
             <c:dateFormatValidator componentToValidate="validUntilDateEditId"
                                   errorMessage="#{globalResources.messageErrorForAdding}" display="dynamic"/>
            <c:compareDateValidator componentToValidate="validUntilDateEditId" componentToCompare="validFromDateEditId"
                                    display="dynamic" errorMessage="#{globalResources.duration_error}"
                                    operator="afterOrEqual" highlight="false" />
        </t:panelGroup>
        <%--<t:outputText value="#{globalResources.valid}" styleClass="lable01"/>--%>
        <%--<t:panelGroup>
        <t:selectBooleanCheckbox forceId="true" id="validFlagEditId" value="#{pageBeanName.selectedPageDTO.validDateFlag}" ></t:selectBooleanCheckbox>
        </t:panelGroup>--%>
<!-- end fromUntilDate -->
        </h:panelGrid>
        
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}"
            action="#{pageBeanName.edit}" onclick="settingFoucsAtDivEdit();return validatemyForm();"/>
          
            <t:commandButton forceId="true" id="CancelButtonEdit" onblur="if(isVisibleComponent('lookupEditDiv'))settingFoucsAtDivEdit();" styleClass="cssButtonSmall" value="#{globalResources.back}">
                <a4j:support disableDefault="true" event="onclick" action="#{pageBeanName.unlock}" oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'edit_first_inputTxt','myForm:errorEdit');settingFoucsAtListPage();return false;" reRender="msgShow"/>
            </t:commandButton>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
