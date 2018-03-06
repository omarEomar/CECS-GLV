<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divEditLookup" >
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error" rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>
    

    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg" />
    
    <h:panelGrid columns="1" rendered="#{pageBeanName.showEdit}" width="100%">
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" cellpadding="3" cellspacing="0" width="100%">
            <h:outputText value="#{globalResources.Code}" styleClass="lable01"/>                     
            <h:inputText value="#{pageBeanName.selectedPageDTO.code.cntrycityCode}" styleClass="textboxsmall" disabled="true"/>                     
        
            <h:outputText value="#{resourcesBundle.INF_CountryCitiesDTO_Name}" styleClass="lable01" />
            <h:panelGroup>
                <t:inputText id="NameEdit" value="#{pageBeanName.selectedPageDTO.name}" styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" forceId="true" onblur="setFocusOnlyOnElement('longitudeEdit');"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="NameEdit" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{citiesListBean.pageMode==2}"/>
            </h:panelGroup>         
            
            <t:outputText value="#{resourcesBundle.INF_CountryCitiesDTO_longitude}"  styleClass="lable01"/>
            <t:panelGroup>
            <t:inputText value="#{pageBeanName.selectedPageDTO.longitude}" styleClass="textbox" forceId="true" id="longitudeEdit"/>
               <c:regularExpressionValidator componentToValidate="longitudeEdit" pattern="/^[0-9]/" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" active="#{citiesListBean.pageMode==2}"/>
            </t:panelGroup>
            
            <t:outputText value="#{resourcesBundle.INF_CountryCitiesDTO_latitude}"  styleClass="lable01"/>
            <t:panelGroup>
            <t:inputText value="#{pageBeanName.selectedPageDTO.latitude}" styleClass="textbox" forceId="true" id="latitudeEdit"/>
              <c:regularExpressionValidator componentToValidate="latitudeEdit" pattern="/^[0-9]/" display="dynamic" errorMessage="#{globalResources.messageErrorForAdding}" highlight="false" active="#{citiesListBean.pageMode==2}"/>
            </t:panelGroup>
            <t:outputText value="#{resourcesBundle.INF_CountryCitiesDTO_capitalFlag}"  styleClass="lable01"/>
             <t:selectBooleanCheckbox value="#{pageBeanName.selectedPageDTO.booleanCapitalFlag}" />
             
        </h:panelGrid>
        
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}" onclick="return validatemyForm();"/>
            <%--a4j:commandButton id="CancelButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.back}"  action="#{pageBeanName.cancelEdit}" oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'NameEdit','myForm:errorEdit');" reRender="scriptPanelGroup"/--%>
            <t:panelGroup>
                <t:commandButton forceId="true" id="CancelButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="setFocusOnlyOnElement('NameEdit');" />
                <a4j:jsFunction name="cancelEditJs" action="#{pageBeanName.cancelEdit}" oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'NameEdit','myForm:errorEdit');" reRender="scriptPanelGroup"/>
            </t:panelGroup>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
