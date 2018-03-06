<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:panelGroup forceId="true" id="detailsLinkPanel">
    <t:panelGrid align="center">
        <t:outputText forceId="true" id="titleLinkId" styleClass="TitlePage"
                      value="#{resourcesBundle.show_country_details}"/>
    </t:panelGrid>
    <t:panelGrid columns="2" border="0" rowClasses="row02,row01" cellpadding="3" cellspacing="0" width="100%">
        <h:outputText value="#{globalResources.Code}" styleClass="lable01"/>
        <h:inputText value="#{pageBeanName.selectedPageDTO.code.key}" styleClass="textboxsmall" disabled="true"/>
        <h:outputText value="#{resourcesBundle.INF_CountryCitiesDTO_Name}" styleClass="lable01"/>
        <h:panelGroup>
            <h:inputText value="#{pageBeanName.selectedPageDTO.name}" styleClass="textboxlarge"
                          forceId="true" disabled="true"/>
        </h:panelGroup>
        <%-- <t:outputText value="#{resourcesBundle.INF_CountriesDTO_languagesDTO_name}" styleClass="lable01"/>--%>
        <%-- <t:panelGroup> <t:selectOneMenu id="languageListEditID" forceId="true" styleClass="DropdownboxMedium2"
             value="#{pageBeanName.selectedPageDTO.languageKey}"> <f:selectItem
             itemValue="#{pageBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.select_one_item}"/>
             <t:selectItems value="#{pageBeanName.languagesList}" var="languagesList"
             itemValue="#{languagesList.code.key}" itemLabel="#{languagesList.name}"/> </t:selectOneMenu> </t:panelGroup>--%>
        <%-- <t:outputText value="#{resourcesBundle.INF_CountriesDTO_currenciesDTO_name}" styleClass="lable01"/>--%>
        <%-- <t:panelGroup> <t:selectOneMenu id="currenciesListEditID" forceId="true" styleClass="DropdownboxMedium2"
             value="#{pageBeanName.selectedPageDTO.currencyKey}"> <f:selectItem
             itemValue="#{pageBeanName.virtualConstValue}" itemLabel="#{resourcesBundle.select_one_item}"/>
             <t:selectItems value="#{pageBeanName.curreniesList}" var="curreniesList"
             itemValue="#{curreniesList.code.key}" itemLabel="#{curreniesList.name}"/> </t:selectOneMenu> </t:panelGroup>--%>
        <h:outputText value="#{resourcesBundle.male_nationality}" styleClass="lable01"/>
        <t:panelGroup>
            <h:inputText value="#{pageBeanName.maleNationalityEditDTO.name}" styleClass="textboxlarge" disabled="true"/>
        </t:panelGroup>
        <h:outputText value="#{resourcesBundle.fmale_nationality}" styleClass="lable01"/>
        <t:panelGroup>
            <h:inputText value="#{pageBeanName.femaleNationalityEditDTO.name}" styleClass="textboxlarge" disabled="true" />
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid columns="2" align="center" forceId="true" id="lovDiv_btnsPnlGrd">
        <%--<h:panelGroup>
            <t:commandButton forceId="true" id="CancelButtonDetails" type="button" onclick="backLovLinkFunction();"
                             styleClass="cssButtonSmall" value="#{globalResources.back}"/>
            <a4j:jsFunction name="backLovLinkFunction" 
                            oncomplete="hideLookUpDiv(window.blocker,window.customDiv1);"/>
        </h:panelGroup>--%>
        <t:commandButton forceId="true" id="backButtonCustomDiv1" styleClass="cssButtonSmall" 
        value="#{globalResources.back}" />
    </t:panelGrid>
</t:panelGroup>