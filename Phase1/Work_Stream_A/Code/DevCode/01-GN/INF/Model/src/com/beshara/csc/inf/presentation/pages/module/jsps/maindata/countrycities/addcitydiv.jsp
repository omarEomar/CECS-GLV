<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>


<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>




<t:panelGroup forceId="true" id="divAddLookup">
     <t:saveState value="#{pageBeanName.success}"/>
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>

    <h:outputText id="error" value="#{pageBeanName.errorMsg}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg" />
    
   
    
      <t:panelGrid columns="2" border="0" rowClasses="row02,row01" cellpadding="3" cellspacing="0"
                   width="100%" >
                
                <h:outputText value="#{resourcesBundle.INF_CountryCitiesDTO_Name}" styleClass="lable01"/> 
                <t:panelGroup>
                    <t:inputText maxlength="#{pageBeanName.nameMaxLength}" forceId="true" id="Name" value="#{pageBeanName.pageDTO.name}" styleClass="textboxlarge" onblur="setFocusOnlyOnElement('longitudeAdd');"/>
                    <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                    <c:requiredFieldValidator componentToValidate="Name" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{citiesListBean.pageMode==1}"/>
                </t:panelGroup>
          
            <t:outputText value="#{resourcesBundle.INF_CountryCitiesDTO_longitude}"  styleClass="lable01"/>
            <t:panelGroup>
                 <t:inputText value="#{pageBeanName.pageDTO.longitude}" onkeyup="disableCharacters(this);" converter="javax.faces.Long" styleClass="textbox" forceId="true" id="longitudeAdd"/>
                 <c:regularExpressionValidator componentToValidate="longitudeAdd" pattern="/^[0-9]/" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" active="#{citiesListBean.pageMode==1}"/>
            </t:panelGroup>
            <t:outputText value="#{resourcesBundle.INF_CountryCitiesDTO_latitude}"  styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText value="#{pageBeanName.pageDTO.latitude}" onkeyup="disableCharacters(this);" converter="javax.faces.Long" styleClass="textbox" forceId="true" id="latitudeAdd"/>
                <c:regularExpressionValidator componentToValidate="latitudeAdd" pattern="/^[0-9]/" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false"  active="#{citiesListBean.pageMode==1}"/>
            </t:panelGroup>
            <t:outputText value="#{resourcesBundle.INF_CountryCitiesDTO_capitalFlag}"  styleClass="lable01"/>
             <t:selectBooleanCheckbox value="#{pageBeanName.pageDTO.booleanCapitalFlag}" />
        
    </t:panelGrid>
</t:panelGroup>

<t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.save}" onclick="return validatemyForm();"/>
            <h:panelGroup>
                <t:commandButton type="button" forceId="true" id="SaveNewButton" onclick="validateSaveAndNewClientValidator();"  styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup" oncomplete="setFocusOnlyOnElement('Name');"/>
            </h:panelGroup>
            <h:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="setFocusOnlyOnElement('Name');"/>
                <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error','add');" reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,scriptPanelGroup"/>
            </h:panelGroup>
</t:panelGrid>
        
