<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>


<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:saveState value="#{pageBeanName.nationalityDivDTO}"/>
<t:panelGroup forceId="true" id="divEditLookup" >
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error" rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>
    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg" />  
    <h:panelGrid columns="1" rendered="#{pageBeanName.showEdit}" width="100%" cellpadding="3" cellspacing="0">
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%">
            <h:outputText value="#{resourcesBundle.nationality_name}" styleClass="lable01" />
            <h:panelGroup>
                <t:inputText forceId="true" id="NameEdit" value="#{pageBeanName.nationalityDivDTO == null ? '' : pageBeanName.nationalityDivDTO.name}" styleClass="textboxlarge" maxlength="#{pageBeanName.nameMaxLength}" onblur="setFocusOnlyOnElement('SaveButtonEdit');"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            </h:panelGroup>         
        </h:panelGrid>
        
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.saveNationality}" onclick="return validateInputText('NameEdit','myForm:clientErrorMessageEdit',null,null,null);"/>
            <t:commandButton forceId="true" id="CancelButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,'NameEdit','myForm:errorEdit'); return false;" onblur="setFocusOnlyOnElement('NameEdit');"/>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<f:verbatim><br/></f:verbatim>
<t:saveState value="#{pageBeanName.showEdit}"/>

<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
