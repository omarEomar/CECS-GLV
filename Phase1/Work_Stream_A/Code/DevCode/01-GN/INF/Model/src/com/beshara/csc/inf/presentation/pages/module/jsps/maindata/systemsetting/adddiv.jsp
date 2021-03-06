<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>


<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:saveState value="#{pageBeanName.success}"/>

<t:panelGroup forceId="true" id="divAddLookup">
    
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>
   
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg" />
        
        <t:panelGrid columns="2" border="0" rowClasses="row02,row01" cellpadding="3" cellspacing="0" width="100%" >
                
            <h:outputText value="#{resourcesBundle.system_setting_name}" styleClass="lable01"/> 
            <t:panelGroup>
                <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="syssettingName" value="#{pageBeanName.pageDTO.syssettingName}" styleClass="textboxlarge" forceId="true"  />
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="syssettingName" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{systemSettingBean.pageMode==1}"/>
            </t:panelGroup>
          
            <t:outputText value="#{resourcesBundle.system_setting_amount}"  styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText  id="syssettingValue" value="#{pageBeanName.pageDTO.syssettingValue}" styleClass="textboxlarge" forceId="true" />
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="syssettingValue" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{systemSettingBean.pageMode==1}"/>
            </t:panelGroup>
    
        </t:panelGrid>
</t:panelGroup>

<t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.save}" onclick="return validatemyForm();"/>
            <h:panelGroup>
                <t:commandButton type="button" forceId="true" id="SaveNewButton" onclick="validateSaveAndNewClientValidator();" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup" oncomplete="setFocusOnlyOnElement('syssettingName');"/>
            </h:panelGroup>
            <h:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="settingFoucsAtDivAdd();"/>
                <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:syssettingName','myForm:error','add');foucsAddbuttonOnList();" reRender="editButton,divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize"/>
            </h:panelGroup>
</t:panelGrid>
        
