<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
<t:saveState value="#{pageBeanName.success}"/>
<t:saveState value="#{pageBeanName.languagesList}"/>
<t:saveState value="#{pageBeanName.curreniesList}"/>
<t:panelGroup forceId="true" id="divAddLookup">
    <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"
                  rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg"
                  rendered="#{pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg"/>
    <h:messages style="color:red;margin:8px;"/>
    <t:panelGrid columns="2" border="0" rowClasses="row02,row01" cellpadding="3" cellspacing="0" width="100%">
        <h:outputText value="#{resourcesBundle.INF_CountriesDTO_name}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="Name" value="#{pageBeanName.pageDTO.name}"
                         styleClass="textboxlarge" forceId="true" onblur="setFocusOnlyOnElement('languageListID');"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="Name" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      active="#{countriesListBean.pageMode==1}"/>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.INF_CountriesDTO_languagesDTO_name}" styleClass="lable01"/>
        <t:panelGroup>
            <t:selectOneMenu id="languageListID" forceId="true" styleClass="DropdownboxMedium2"
                             value="#{pageBeanName.pageDTO.languageKey}">
                <f:selectItem itemValue="#{pageBeanName.virtualConstValue}"
                              itemLabel="#{resourcesBundle.select_one_item}"/>
                <t:selectItems value="#{pageBeanName.languagesList}" var="languagesList"
                               itemValue="#{languagesList.code.key}" itemLabel="#{languagesList.name}"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <t:outputText value="#{resourcesBundle.INF_CountriesDTO_currenciesDTO_name}" styleClass="lable01"/>
        <t:panelGroup>
            <t:selectOneMenu id="currenciesListID" forceId="true" styleClass="DropdownboxMedium2"
                             value="#{pageBeanName.pageDTO.currencyKey}">
                <f:selectItem itemValue="#{pageBeanName.virtualConstValue}"
                              itemLabel="#{resourcesBundle.select_one_item}"/>
                <t:selectItems value="#{pageBeanName.curreniesList}" var="curreniesList"
                               itemValue="#{curreniesList.code.key}" itemLabel="#{curreniesList.name}"/>
            </t:selectOneMenu>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.male_nationality}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="maleNationality"
                         value="#{pageBeanName.maleNationalityDTO.name}" styleClass="textboxlarge" forceId="true"
                         onblur="setFocusOnlyOnElement('femaleNationality');"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="maleNationality" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      active="#{countriesListBean.pageMode==1}"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.fmale_nationality}" styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="femaleNationality"
                         value="#{pageBeanName.femaleNationalityDTO.name}" styleClass="textboxlarge" forceId="true"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <c:requiredFieldValidator componentToValidate="femaleNationality" display="dynamic"
                                      errorMessage="#{globalResources.missingField}" highlight="false"
                                      active="#{countriesListBean.pageMode==1}"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<t:panelGrid columns="3" border="0" align="center">
    <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}"
                     action="#{pageBeanName.save}" onclick="return validatemyForm();"/>
    <h:panelGroup>
        <t:commandButton type="button" forceId="true" id="SaveNewButton"
                         onclick="validateSaveAndNewClientValidator();"
                         styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}"/>
        <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveAndNew}" reRender="divAddLookup,dataT_data_panel"
                        oncomplete="setFocusOnlyOnElement('Name');"/>
    </h:panelGroup>
    <h:panelGroup>
        <t:commandButton forceId="true" id="backButtonAddDiv" onclick="backJsFunction(); return false;"
                         styleClass="cssButtonSmall" value="#{globalResources.back}"
                         onblur="setFocusOnlyOnElement('Name');"/>
        <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}"
                        oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error','add');"
                        reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
    </h:panelGroup>
</t:panelGrid>