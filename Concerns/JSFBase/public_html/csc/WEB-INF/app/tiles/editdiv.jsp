<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
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
            </h:panelGroup>         
        </h:panelGrid>
        
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}" onclick="settingFoucsAtDivEdit();return validateInputText('edit_first_inputTxt','myForm:clientErrorMessageEdit',null,null,null);"/>
            <%--t:commandButton forceId="true" id="CancelButtonEdit" onblur="if(isVisibleComponent('lookupEditDiv'))settingFoucsAtDivEdit();" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,'edit_first_inputTxt','myForm:errorEdit');settingFoucsAtListPage();return false;" /--%>
            <%-- TODO locking code: we converted this button to be ajax to call the unlock method --%>
            <t:commandButton forceId="true" id="CancelButtonEdit" onblur="if(isVisibleComponent('lookupEditDiv'))settingFoucsAtDivEdit();" styleClass="cssButtonSmall" value="#{globalResources.back}">
                <a4j:support disableDefault="true" event="onclick" action="#{pageBeanName.unlock}" oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'edit_first_inputTxt','myForm:errorEdit');settingFoucsAtListPage();return false;" reRender="msgShow"/>
            </t:commandButton>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
