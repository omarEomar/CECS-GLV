<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<t:panelGroup forceId="true" id="divLinkLookup">
  <t:panelGroup forceId="true" id="linkLookupForm">
   <t:panelGrid align="center">
        <t:outputText styleClass="TitlePage" value="#{resourcesBundle.link_mar_status_type}" />
    </t:panelGrid>
    <t:outputText forceId="true" id="success" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"
                  rendered="#{pageBeanName.success}"/>
                  
    <t:outputText forceId="true" id="alreadyLinked" 
            value="#{resourcesBundle.FailureInLink}" 
            styleClass="errMsg"
            rendered="#{pageBeanName.alreadyLinked}"/>
    <t:panelGrid columns="2" border="0" 
            rowClasses="row02,row01" 
            width="100%" cellpadding="3" 
            cellspacing="0" 
            rendered="#{pageBeanName.showLinkDiv}">
        <t:outputText value="#{resourcesBundle.type}" styleClass="lable01"/>
        <t:panelGroup>
           <t:selectOneMenu id="genderTypesListID" forceId="true"  
                styleClass="DropdownboxMedium2" 
                value="#{pageBeanName.selectedGender}" onblur="alert('11');document.getElementById('nameLinkAddId').focus();">
                     <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select_one_item}" />
                     <t:selectItems value="#{pageBeanName.genderTypesList}" 
                            var="genderTypesList" 
                            itemValue="#{genderTypesList.code.key}" 
                            itemLabel="#{genderTypesList.name}" /> 
            </t:selectOneMenu>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="genderTypesListID" 
                    display="dynamic" errorMessage="#{resourcesBundle.select_one_filed}"/>
        </t:panelGroup>
        
        <t:outputText value="#{resourcesBundle.naming}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" id="nameLinkAddId" 
                    value="#{pageBeanName.genderMaritalDTO.genmarName}" 
                    styleClass="textboxlarge" />
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
             <c:requiredFieldValidator componentToValidate="nameLinkAddId" 
                    display="dynamic" 
                    errorMessage="#{globalResources.missingField}"/>
        </t:panelGroup>
        
    </t:panelGrid>
    </t:panelGroup>
<f:verbatim><br/></f:verbatim>
   
    <t:panelGrid columns="3" border="0" align="center">
        <t:commandButton forceId="true" id="SaveButtonID" 
            onclick="return validatemyForm();" 
            styleClass="cssButtonSmall" 
            value="#{globalResources.SaveButton}" 
            action="#{pageBeanName.saveLinkage}" />
        <t:panelGroup>
            <t:commandButton forceId="true" id="SaveNewButtonId" 
                    type="button" 
                    onclick="if(validatemyForm()){saveAndNewFunction();}" 
                    styleClass="cssButtonSmall" 
                    value="#{globalResources.AddNewButton}"/>
            <a4j:jsFunction name="saveAndNewFunction" 
                    action="#{pageBeanName.linkAndNew}" 
                    reRender="divLinkLookup" 
                    oncomplete="setFocusOnlyOnElement('genderTypesListID');"/>
        </t:panelGroup>
        <t:panelGroup>
            <t:commandButton 
                forceId="true" 
                id="backButtonCustomDiv1" 
                onclick="backLinkJsFunction(); return false;" 
                styleClass="cssButtonSmall" 
                value="#{globalResources.back}"
                 onblur="settingFoucsAtLinkDiv();" />
                   <a4j:jsFunction name="backLinkJsFunction" 
                action="#{pageBeanName.cancelLinkage}" 
                oncomplete="hideLookUpDiv(window.blocker,window.customDiv1);foucsAddbuttonOnList();" 
                reRender="divLinkLookup,dataT_data_panel,noOfRecords,paging_panel,listSize"/>
                
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>


         
                