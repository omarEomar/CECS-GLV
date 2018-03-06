<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:saveState value="#{pageBeanName.selectedGrpCountry}"/>
<t:saveState value="#{pageBeanName.selectedCountry}"/>
<t:saveState value="#{pageBeanName.countryCats}"/>
<t:saveState value="#{pageBeanName.groupCountry}"/>
<t:panelGrid width="100%" columns="4" align="center" rowClasses="row01,row02" cellpadding="3" cellspacing="0">
    <t:outputText value="#{resourcesBundle.catGrp}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu id="countryCats" forceId="true" styleClass="DropdownboxMedium2"
                         value="#{pageBeanName.selectedGrpCountry}" converter="javax.faces.Long">
            <f:selectItem itemValue="#{pageBeanName.virtualLongValue}" itemLabel="#{resourcesBundle.select_one_item}"/>
            <t:selectItems value="#{pageBeanName.countryCats}" var="groupCategoryList"
                           itemValue="#{groupCategoryList.code.cntrygrpCode}" itemLabel="#{groupCategoryList.name}"/>
            <a4j:support actionListener="#{pageBeanName.filterByCategory}" event="onchange"
                         reRender="OperationBar,groupCountry,dataT_data_panel,paging_panel,divSearch"/>
        </t:selectOneMenu>
    </t:panelGroup>
    <t:outputText value="#{resourcesBundle.countryGrp}" styleClass="lable01"/>
    <t:panelGroup>
        <t:selectOneMenu id="groupCountry" forceId="true" styleClass="DropdownboxMedium2"
                         value="#{pageBeanName.selectedCountry}" converter="javax.faces.Long">
            <f:selectItem itemValue="#{pageBeanName.virtualLongValue}" itemLabel="#{resourcesBundle.select_one_item}"/>
            <t:selectItems value="#{pageBeanName.groupCountry}" var="groupCountry"
                           itemValue="#{groupCountry.code.cntrygrpCode}" itemLabel="#{groupCountry.name}"/>
            <a4j:support event="onchange" actionListener="#{pageBeanName.filterByGrp}"
                         reRender="OperationBar,dataT_data_panel,paging_panel,divSearch"/>
        </t:selectOneMenu>
    </t:panelGroup>
</t:panelGrid>