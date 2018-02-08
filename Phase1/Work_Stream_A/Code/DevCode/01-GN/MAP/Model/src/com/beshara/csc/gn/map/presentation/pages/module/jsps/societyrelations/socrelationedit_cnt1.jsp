<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
    
    <%-- start--%>
 
   <t:panelGroup forceId="true" id="data_Editpnl">
    <t:panelGrid columns="2" border="0" width="100%" cellpadding="0" cellspacing="0" rowClasses="row02,row01"  id="data_Editpnl_grd" forceId="true">
    <h:outputLabel value="#{mapResources.mapped_object_name}" styleClass="lable01"/>
    <t:panelGroup>
   <t:selectOneMenu forceId="true" id="objectTypeId" 
                     value="#{societyRelationAddBean.selectedObjectTypeId}"
                     disabled="true"
                     styleClass="DropdownboxxTooLarge">
        <f:selectItem itemValue=""
                      itemLabel="#{mapResources.selectItem}"/>
        <t:selectItems id="object_type_itemsId" itemLabel="#{object_type.name}" itemValue="#{object_type.code.key}"
                       var="object_type" value="#{societyRelationAddBean.objectType}"/>
        <a4j:support event="onchange"
                     reRender="soc_from,soc_to"/>
    </t:selectOneMenu>
    <%--<h:outputText value="*" styleClass="mandatoryAsterisk" />--%>
    <%--<c2:requiredFieldValidator componentToValidate="objectTypeId"
                                  errorMessage="#{globalResources.missingField}" display="dynamic"/>--%>
    </t:panelGroup>
    
    <h:outputLabel value="#{mapResources.soc1Name}" styleClass="lable01"/>
    <t:panelGroup>
    <h:selectOneMenu id="soc_fromId" 
                     value="#{societyRelationAddBean.selectedSocityFrom}" required="false"
                     styleClass="DropdownboxxLarge"
                     disabled="true"
                      style="width:205px !important;">
        <f:selectItem itemValue=""
                      itemLabel="#{mapResources.selectItem}"/>
        <t:selectItems itemLabel="#{soc_from.name}" itemValue="#{soc_from.code.key}" var="soc_from"
                       value="#{societyRelationAddBean.soc_from}"/>
        <a4j:support event="onchange"
                     reRender="soc_to"/>
    </h:selectOneMenu>
    <%--<h:outputText value="*" styleClass="mandatoryAsterisk" />--%>
    <%--<c2:requiredFieldValidator componentToValidate="soc_fromId"
                                  errorMessage="#{globalResources.missingField}" display="dynamic"/>--%>
    </t:panelGroup>
     <h:outputLabel value="#{mapResources.relationTypeName}" styleClass="lable01"/>
 <t:panelGroup>
    <t:selectOneMenu forceId="true" id="relationTypeId" 
                     value="#{societyRelationAddBean.selectedRelationTypeCode}"
                     styleClass="DropdownboxxLarge" 
                      style="width:205px !important;" >
        <f:selectItem itemValue=""
                      itemLabel="#{mapResources.selectItem}"/>
        <t:selectItems id="relationType_itemsId" itemLabel="#{relation_type.name}" itemValue="#{relation_type.code.key}"
                       var="relation_type" value="#{societyRelationAddBean.soc_rel_types}"/>
    
    </t:selectOneMenu>
    <h:outputText value="*" styleClass="mandatoryAsterisk" />
    <c2:requiredFieldValidator componentToValidate="relationTypeId"
                                  errorMessage="#{globalResources.missingField}" display="dynamic"/>
    </t:panelGroup>
    <h:outputLabel value="#{mapResources.soc2Name}" styleClass="lable01"/>
    <t:panelGroup>
    <t:selectOneMenu id="soc_toId" styleClass="DropdownboxxLarge" value="#{societyRelationAddBean.selectedSocity2Id}"
                     forceId="true" 
                      style="width:205px !important;" disabled="true">
                    
        <f:selectItem itemValue=""
                      itemLabel="#{mapResources.selectItem}"/>
        <t:selectItems itemLabel="#{soc_to.name}" itemValue="#{soc_to.code.key}" var="soc_to"
                       value="#{societyRelationAddBean.soc_to}"/>
    
    </t:selectOneMenu>
  <%--<h:outputText value="*" styleClass="mandatoryAsterisk" />--%>
    <%--<c2:requiredFieldValidator componentToValidate="soc_toId"
                                  errorMessage="#{globalResources.missingField}" display="dynamic"/>--%>
 </t:panelGroup>
</t:panelGrid></t:panelGroup>
    <t:panelGroup colspan="2" id="btn_pnl">
       <t:panelGrid styleClass="lovDiv_btnsPnlGrd" columns="3" border="0" align="center">
            <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.editItem}" onclick="return validatemyForm();"/>            
                <t:commandButton forceId="true" id="backButtonAddDiv" action="#{pageBeanName.goBack}" onblur="settingFoucsAtDivAdd();"  styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                
      </t:panelGrid>
    </t:panelGroup>
  

