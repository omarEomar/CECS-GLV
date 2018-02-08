<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:panelGrid id="newBranchBankPanel" align="center" columns="4" border="0" cellpadding="3" cellspacing="0" rowClasses="row01,row02" width="100%" >

    <h:outputText value="#{integrationBundle.branch_bank_code}" styleClass="divtext"/>
    <t:inputText disabled="true" maxlength="5" forceId="true" id="bankBranchID" value="#{pageBeanName.bankDTO.code.key}" styleClass="textbox"  />
    
    <h:outputText value="#{integrationBundle.banks_name_label}" styleClass="divtext"/>
    <t:inputText styleClass="textboxmedium" forceId="true" id="bankBranchName" value="#{pageBeanName.bankDTO.name}" disabled="true"/>
</t:panelGrid>

<t:panelGroup forceId="true" id="branchAddPanel">
    
    <t:panelGrid columns="4" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01">
          
        <h:outputText value="#{integrationBundle.branch_Name}" styleClass="lable01"/> 
         <t:panelGroup>
            <t:inputText onfocus="this.select();" onchange="trimBorders(branchNameTxt);" forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="branchNameTxt" value="#{pageBeanName.branchName}" styleClass="textboxmedium"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>
            <f:verbatim><br/></f:verbatim>
            <t:outputText id="nameError" forceId="true" value="#{globalResources.missingField}" styleClass="errMsg" style="display:none;"/>            
         </t:panelGroup>
         
        <h:outputText value="#{integrationBundle.branch_email}" styleClass="lable01"/> 
         <t:panelGroup>
            <t:inputText onfocus="this.select();" onchange="trimBorders(branchMailTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="branchMailTxt" value="#{pageBeanName.branchEmail}" styleClass="textboxmedium"/>
            <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
            <t:outputText id="mailExample" forceId="true" value="#{integrationBundle.emailFormat}" styleClass="warningClass_cust1"/>
            <f:verbatim><br/></f:verbatim>
            <t:outputText id="mailError" forceId="true" value="#{integrationBundle.emailErrorMsg}" styleClass="errMsg" style="display:none;"/>            
         </t:panelGroup>         

        <h:outputText value="#{integrationBundle.branch_Tel}" styleClass="lable01"/> 
        <t:panelGroup>
            <t:inputText onfocus="this.select();" onchange="trimBorders(add_third_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="add_third_inputTxt" value="#{pageBeanName.branchTel}" styleClass="textboxmedium"/>
        </t:panelGroup>
        
        <h:outputText value="#{integrationBundle.branch_Fax}" styleClass="lable01"/> 
        <t:panelGroup>
            <t:inputText onfocus="this.select();" onchange="trimBorders(add_forth_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="add_forth_inputTxt" value="#{pageBeanName.branchFax}" styleClass="textboxmedium"/>
        </t:panelGroup>        
        
        <h:outputText value="#{integrationBundle.branches_governrate}" styleClass="lable01"/> 
        <t:selectOneMenu onfocus="this.select();" forceId="true" id="governrates" 
                     styleClass="textboxmedium2" value="#{pageBeanName.branchGovernorateCode}" >
        <f:selectItem itemLabel="#{integrationBundle.select}" itemValue=""/>
        <t:selectItems value="#{pageBeanName.governorateList}" itemLabel="#{governorateList.name}"
                     itemValue="#{governorateList.code.key}" var="governorateList" />
        <a4j:support actionListener="#{pageBeanName.changeGovernoratesEvent}" event="onchange"
                         reRender="districts,streets,scriptGenerator"/>                     
        </t:selectOneMenu>
        
        <h:outputText value="#{integrationBundle.branches_district}" styleClass="lable01"/> 
        <t:selectOneMenu onfocus="this.select();" forceId="true" id="districts" 
                     styleClass="textboxmedium2" value="#{pageBeanName.branchDistrictCode}" >
        <f:selectItem itemLabel="#{integrationBundle.select}" itemValue=""/>
        <t:selectItems value="#{pageBeanName.districtList}" itemLabel="#{districtList.name}"
                     itemValue="#{districtList.code.key}" var="districtList" />
        <a4j:support actionListener="#{pageBeanName.changeDistrictsEvent}" event="onchange"
                         reRender="sectors,streets,scriptGenerator"/>                     
        </t:selectOneMenu>        

        <h:outputText value="#{integrationBundle.branches_sector}" styleClass="lable01"/> 
        <t:selectOneMenu onfocus="this.select();" forceId="true" id="sectors" 
                     styleClass="textboxmedium2" value="#{pageBeanName.branchSectorCode}" >
        <f:selectItem itemLabel="#{integrationBundle.select}" itemValue=""/>
        <t:selectItems value="#{pageBeanName.sectorList}" itemLabel="#{sectorList.name}"
                     itemValue="#{sectorList.code.key}" var="sectorList" />
        <a4j:support actionListener="#{pageBeanName.changeSectorsEvent}" event="onchange"
                         reRender="streets,scriptGenerator"/>                     
        </t:selectOneMenu>
        
        <h:outputText value="#{integrationBundle.branches_streets}" styleClass="lable01"/> 
        <t:selectOneMenu onfocus="this.select();" forceId="true" id="streets" 
                     styleClass="textboxmedium2" value="#{pageBeanName.branchStreetCode}" >
        <f:selectItem itemLabel="#{integrationBundle.select}" itemValue=""/>
        <t:selectItems value="#{pageBeanName.streetList}" itemLabel="#{streetList.name}"
                     itemValue="#{streetList.code.key}" var="streetList" />
        </t:selectOneMenu>        
    <h:outputText value="#{integrationBundle.branches_building}" styleClass="lable01"/> 
        <t:panelGroup>
            <t:inputText onfocus="this.select();" onchange="trimBorders(building_no_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="building_no_inputTxt" value="#{pageBeanName.buildingNumber}" styleClass="textbox"/>
    </t:panelGroup>
    </t:panelGrid>
        
       <t:outputLabel  style="font-size: 4pt;height:3px"/>    
</t:panelGroup>
<%--<t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center">
    <a4j:commandButton type="button" id="buttonAddCoordinator" styleClass="cssButtonSmall" value="#{integrationBundle.branche_add_coordinat}" action="#{pageBeanName.addCoordinator}" 
       oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);" reRender="msgShowId,customDiv1"/>
</t:panelGrid>--%>
