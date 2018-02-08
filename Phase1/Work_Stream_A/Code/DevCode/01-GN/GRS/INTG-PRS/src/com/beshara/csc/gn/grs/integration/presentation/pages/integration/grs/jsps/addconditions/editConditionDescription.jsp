<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<f:loadBundle basename="com.beshara.csc.gn.grs.presentation.resources.grs" var="resourcesBundle"/>

<t:panelGroup forceId="true" id="EditNameAndDescriptionDIV" >
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error" rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>  
    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg" />
    <h:outputText id="EditNameANdDescriptionTitle" styleClass="popup_inner_title" value="#{resourcesBundle.editDescriptionAndName}"/>
   
     <h:panelGrid columns="1" width="100%">
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">
            <h:outputText value="#{resourcesBundle.Condition_Name}" styleClass="lable01"/>                     
            <h:panelGroup>
            <t:inputText value="#{pageBeanName.selectedConditionName}" styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" forceId="true" id="ConditionNameEditField"/>                     
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c2:requiredFieldValidator componentToValidate="ConditionNameEditField" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="ConditionNameEditFieldValidator"/>
            </h:panelGroup>
            <h:outputText value="#{resourcesBundle.Condition_Description}" styleClass="lable01" />
           <t:inputText id="edit_first_inputTxt" onblur="if(isVisibleComponent('lookupEditDiv'))document.getElementById('SaveButtonEditNameDesc').focus();" onchange="trimBorders(edit_first_inputTxt);"  value="#{pageBeanName.selectedConditionDescription}" styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" forceId="true"/>
                      
        </h:panelGrid>
        
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEditNameDesc" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.doUpdateConditionNameAndDescription}" onclick="return validatemyForm();"/>
            <t:commandButton forceId="true" id="backButtonCustomDiv3" type="button" value="#{globalResources.cancel}" onclick="hideLookUpDiv(window.blocker,window.customDiv3,null,null);" styleClass="cssButtonSmall"/>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
