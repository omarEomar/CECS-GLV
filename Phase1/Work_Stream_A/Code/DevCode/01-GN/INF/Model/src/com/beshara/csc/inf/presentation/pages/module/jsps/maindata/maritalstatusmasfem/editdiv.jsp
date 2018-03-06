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
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%">
            <h:outputText value="#{globalResources.Code}" styleClass="lable01"/>                     
            <h:inputText value="#{pageBeanName.selectedPageDTO.code.key}" styleClass="textboxsmall" disabled="true"/>                     
        
            <h:outputText value="#{resourcesBundle.marital_status_mas_name}" styleClass="lable01" />
            <h:panelGroup>
                <t:inputText id="MasNameEdit" value="#{pageBeanName.selectedPageDTO.marstatusMasName}" styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" forceId="true" onblur="setFocusOnlyOnElement('FemNameEdit');"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="MasNameEdit" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{maritalStatusMasFemListBean.pageMode==2}"/>
            </h:panelGroup>         
            
             <t:outputText value="#{resourcesBundle.marital_status_fem_name}"  styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText id="FemNameEdit" value="#{pageBeanName.selectedPageDTO.marstatusFemName}" styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" forceId="true" onblur="setFocusOnlyOnElement('SaveButtonEdit');"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="FemNameEdit" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{maritalStatusMasFemListBean.pageMode==2}"/>
             </t:panelGroup>            
             
        </h:panelGrid>
        
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}" onclick="return validatemyForm();"/>
                        <t:panelGroup>
            <t:commandButton forceId="true" id="CancelButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="setFocusOnlyOnElement('MasNameEdit');" />
                <a4j:jsFunction name="cancelEditJs" action="#{pageBeanName.cancelEdit}" oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'NameEdit','myForm:errorEdit');" reRender="scriptPanelGroup"/>
            </t:panelGroup>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<f:verbatim><br/></f:verbatim>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
