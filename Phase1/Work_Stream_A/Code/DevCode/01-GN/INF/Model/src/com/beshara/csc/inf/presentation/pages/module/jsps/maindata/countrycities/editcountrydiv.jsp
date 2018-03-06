<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divEditLookup" >
    <h:outputText id="errorEdit" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="error" rendered="#{ pageBeanName.errorMsg != null && pageBeanName.errorMsg != '' }"/>
    

    <h:outputText id="clientErrorMessageEdit" styleClass="errMsg" />
    <h:panelGrid columns="1" rendered="#{pageBeanName.showEdit}" width="100%">
        <h:panelGrid columns="2" border="0" rowClasses="row02,row01" cellpadding="3" cellspacing="0" width="100%">
            <h:outputText value="#{globalResources.Code}" styleClass="lable01"/>
            <h:inputText value="#{pageBeanName.selectedPageDTO.code.key}" styleClass="textboxsmall" disabled="true"/>
            <h:outputText value="#{resourcesBundle.INF_CountriesDTO_name}" styleClass="lable01"/>
            <h:panelGroup>
                <t:inputText id="NameEdit" value="#{pageBeanName.selectedPageDTO.name}" styleClass="textboxlarge"
                             maxlength="#{pageBeanName.nameMaxLength}" forceId="true"
                             onblur="setFocusOnlyOnElement('languageListEditID');"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="NameEdit" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{countriesListBean.pageMode==2}"/>
            </h:panelGroup>
            <t:outputText value="#{resourcesBundle.INF_CountriesDTO_languagesDTO_name}" styleClass="lable01"/>
            <t:panelGroup>
                <t:selectOneMenu id="languageListEditID" forceId="true" styleClass="DropdownboxMedium2"
                                 value="#{pageBeanName.selectedPageDTO.languageKey}">
                    <f:selectItem itemValue="#{pageBeanName.virtualConstValue}"
                                  itemLabel="#{resourcesBundle.select_one_item}"/>
                    <t:selectItems value="#{pageBeanName.languagesList}" var="languagesList"
                                   itemValue="#{languagesList.code.key}" itemLabel="#{languagesList.name}"/>
                </t:selectOneMenu>
            </t:panelGroup>
            <t:outputText value="#{resourcesBundle.INF_CountriesDTO_currenciesDTO_name}" styleClass="lable01"/>
            <t:panelGroup>
                <t:selectOneMenu id="currenciesListEditID" forceId="true" styleClass="DropdownboxMedium2"
                                 value="#{pageBeanName.selectedPageDTO.currencyKey}">
                    <f:selectItem itemValue="#{pageBeanName.virtualConstValue}"
                                  itemLabel="#{resourcesBundle.select_one_item}"/>
                    <t:selectItems value="#{pageBeanName.curreniesList}" var="curreniesList"
                                   itemValue="#{curreniesList.code.key}" itemLabel="#{curreniesList.name}"/>
                </t:selectOneMenu>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.male_nationality}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="maleNationalityEdit"
                             value="#{pageBeanName.maleNationalityEditDTO.name}" styleClass="textboxlarge" forceId="true"
                             onblur="setFocusOnlyOnElement('femaleNationality');"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="maleNationalityEdit" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{countriesListBean.pageMode==2}"/>
            </t:panelGroup>
            <h:outputText value="#{resourcesBundle.fmale_nationality}" styleClass="lable01"/>
            <t:panelGroup>
                <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="femaleNationalityEdit"
                             value="#{pageBeanName.femaleNationalityEditDTO.name}" styleClass="textboxlarge"
                             forceId="true"/>
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="femaleNationalityEdit" display="dynamic"
                                          errorMessage="#{globalResources.missingField}" highlight="false"
                                          active="#{countriesListBean.pageMode==2}"/>
            </t:panelGroup>
        </h:panelGrid>
        <t:panelGrid columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}" onclick="return validatemyForm();"/>
            <t:commandButton forceId="true" id="CancelButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.lookupEditDiv,'myForm:NameEdit','myForm:errorEdit'); return false;" onblur="setFocusOnlyOnElement('NameEdit');"/>
        </t:panelGrid>
    </h:panelGrid>
</t:panelGroup>
<t:saveState value="#{pageBeanName.showEdit}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
