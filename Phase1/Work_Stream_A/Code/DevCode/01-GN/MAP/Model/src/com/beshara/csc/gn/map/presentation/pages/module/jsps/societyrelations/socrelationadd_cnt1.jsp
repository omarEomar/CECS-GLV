<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
    
    <%-- start--%>
 
   <t:panelGroup forceId="true" id="data_pnl">
    <t:saveState value="#{pageBeanName.success}"/>
   <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"  style="width: 100%; display: table;"/>
    <h:outputText id="error" value="#{pageBeanName.errorMsg}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}" style="width: 100%; display: table;"/>
    
   
   <t:panelGrid columns="2" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01"  id="data_pnl_grd" forceId="true">
    <h:outputLabel value="#{mapResources.mapped_object_name}" styleClass="lable01"/>
    <t:panelGroup>
    <!-- add filter text -->
    <t:inputText forceId="true" id="objectTypeText" styleClass="textboxsmall" style="width:35px; margin-left:4px;" value="#{societyRelationAddBean.selectedObjectTypeId}"
                        onkeypress=" filterationInputOnKeyPress(event,this,'objectType','errorCodeId1',objectTypeChange,'soc_from');"
                        onblur=" filterationInputOnBlur(event,this,'objectType','errorCodeId1',objectTypeChange,'soc_from');"
                        onkeyup="enabelIntegerOnly(this);">
    </t:inputText>
    <t:selectOneMenu forceId="true" id="objectType" 
                     value="#{societyRelationAddBean.selectedObjectTypeId}"
                     styleClass="DropdownboxxLarge"
                    onchange="filterationDropboxOnChange(event,this,'objectTypeText','errorCodeId1',objectTypeChange);">
        <f:selectItem itemValue=""
                      itemLabel="#{mapResources.selectItem}"/>
        <t:selectItems id="object_type_items" itemLabel="#{object_type.name}" itemValue="#{object_type.code.key}"
                       var="object_type" value="#{societyRelationAddBean.objectType}"/>
        <a4j:jsFunction name="objectTypeChange"
                     reRender="soc_from,soc_to"/>
    </t:selectOneMenu>
    <h:outputText value="*" styleClass="mandatoryAsterisk" />
    <!-- search button -->
    <t:commandButton type="button"  onclick="goSearchLov();" id="okLoveButton"  styleClass="cssButtonSmall" value="..." >
    <a4j:jsFunction name="goSearchLov"  action="#{pageBeanName.showSearchListOfValuesDiv}" reRender="lovDivContainer,lov_dataT_data_panel,lov_searchPanel," 
    oncomplete="javascript:changeVisibilityDiv(window.blocker,window.divLov);settingFoucsAtLovDiv();"/>
 </t:commandButton>
    <f:verbatim><br/></f:verbatim>
    <c2:requiredFieldValidator componentToValidate="objectType"
                                  errorMessage="#{globalResources.missingField}" display="dynamic"/>
     <t:outputLabel id="errorCodeId1" value="#{mapResources.MessageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none"/>
    <!-- add search button -->
    
    </t:panelGroup>
    
    <h:outputLabel value="#{mapResources.soc1Name}" styleClass="lable01"/>
    <t:panelGroup>
    <h:selectOneMenu id="soc_from" 
                     value="#{societyRelationAddBean.selectedSocityFrom}" required="false"
                     styleClass="DropdownboxxLarge"
                     
                      style="width:205px !important;">
        <f:selectItem itemValue=""
                      itemLabel="#{mapResources.selectItem}"/>
        <t:selectItems itemLabel="#{soc_from.name}" itemValue="#{soc_from.code.key}" var="soc_from"
                       value="#{societyRelationAddBean.soc_from}"/>
        <a4j:support event="onchange"
                     reRender="soc_to"/>
    </h:selectOneMenu>
    <h:outputText value="*" styleClass="mandatoryAsterisk" />
    <c2:requiredFieldValidator componentToValidate="soc_from"
                                  errorMessage="#{globalResources.missingField}" display="dynamic"/>
    </t:panelGroup>
     <h:outputLabel value="#{mapResources.relationTypeName}" styleClass="lable01"/>
 <t:panelGroup>
    <t:selectOneMenu forceId="true" id="relationType" 
                     value="#{societyRelationAddBean.selectedRelationTypeCode}"
                     styleClass="DropdownboxxLarge" 
                      style="width:205px !important;">
        <f:selectItem itemValue=""
                      itemLabel="#{mapResources.selectItem}"/>
        <t:selectItems id="relationType_items" itemLabel="#{relation_type.name}" itemValue="#{relation_type.code.key}"
                       var="relation_type" value="#{societyRelationAddBean.soc_rel_types}"/>
    
    </t:selectOneMenu>
    <h:outputText value="*" styleClass="mandatoryAsterisk" />
    <c2:requiredFieldValidator componentToValidate="relationType"
                                  errorMessage="#{globalResources.missingField}" display="dynamic"/>
    </t:panelGroup>
    <h:outputLabel value="#{mapResources.soc2Name}" styleClass="lable01"/>
    <t:panelGroup>
    <t:selectOneMenu id="soc_to" styleClass="DropdownboxxLarge" value="#{societyRelationAddBean.selectedSocity2Id}"
                     forceId="true" 
                      style="width:205px !important;">
                    
        <f:selectItem itemValue=""
                      itemLabel="#{mapResources.selectItem}"/>
        <t:selectItems itemLabel="#{soc_to.name}" itemValue="#{soc_to.code.key}" var="soc_to"
                       value="#{societyRelationAddBean.soc_to}"/>
    
    </t:selectOneMenu>
  <h:outputText value="*" styleClass="mandatoryAsterisk" />
    <c2:requiredFieldValidator componentToValidate="soc_to"
                                  errorMessage="#{globalResources.missingField}" display="dynamic"/>
 </t:panelGroup>
    </t:panelGrid>
    </t:panelGroup>
    <t:panelGroup colspan="2" id="btn_pnl">
       <t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.addItem}" onclick="return validatemyForm();"/>            
            <h:panelGroup>
                <t:commandButton forceId="true" id="SaveNewButton" type="button"  styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}" onclick="if(validatemyForm()) {saveAndNew();} else {return false;}" />                
                <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.saveNewItem}" reRender="data_pnl,OperationBar" oncomplete="settingFoucsAtDivAdd();"/>
            </h:panelGroup>
                <t:commandButton forceId="true" id="backButtonAddDiv" action="#{pageBeanName.goBack}" onblur="settingFoucsAtDivAdd();"  styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                
      </t:panelGrid>
    </t:panelGroup>
    <%-- end--%>

