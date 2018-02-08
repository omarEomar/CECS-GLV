<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

<tiles:importAttribute scope="request"/>
<t:panelGroup id="treeEditDiv" forceId="true">
    <t:saveState value="#{pageBeanName.dto}"/>
    <t:saveState value="#{pageBeanName.dto.parentCode}"/>
    <t:saveState value="#{pageBeanName.parentName}"/>
    <t:saveState value="#{pageBeanName.success}"/>
    <t:saveState value="#{pageBeanName.renderEdit}"/>
    <%-- edit by m.elsaied (fix issue key : NL-279 - 2. add 001.JPG)--%>
    <%-- <f:verbatim><br /></f:verbatim>--%>
    <%-- <center><h:outputText value="#{pageBeanName.editDivTitle}"
         style="font-weight:bold" /></center>--%>
    <%-- <f:verbatim><br /></f:verbatim>--%>
    <%-- modifiy width by sherif.omar--%>
    <f:verbatim><span id="msgEdit" class="errMsg"/></f:verbatim>
    
    <t:panelGrid columns="2" width="100%" rendered="#{pageBeanName.renderEdit}" columnClasses="col1,col2" rowClasses="row02,row01,row02,row01" cellpadding="0" cellspacing="0">
        <t:outputLabel value="#{resourcesBundle[code]}"  styleClass="lable01"/>
        <%-- <t:outputLabel value="#{pageBeanName.dto.code.key}"
             styleClass="lable01"/>--%>
        <t:inputText value="#{pageBeanName.dto.code.key}" styleClass="textboxsmall" disabled="true"/>
        <t:outputLabel value="#{resourcesBundle[name]}"  styleClass="lable01"/>
        <t:panelGroup>
            <t:inputText forceId="true" onchange="trimBorders(edit_first_inputTxt);" id="edit_first_inputTxt" value="#{pageBeanName.dto.name}" maxlength="#{pageBeanName.nameMaxLength}"  styleClass="textboxtreelarge" onblur="setFocusOnlyOnElement('checkBoxLeafEdit')"/>
            <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        <t:outputLabel value="#{resourcesBundle[parent]}"  styleClass="lable01"/>
        <t:inputText disabled="true" value="#{pageBeanName.dto.parentObject.name}" styleClass="textboxmedium"/>
        <t:outputLabel value="#{resourcesBundle[leaf]}"  styleClass="lable01"/>
        <t:selectBooleanCheckbox value="#{pageBeanName.dto.booleanLeafFlag}" disabled="#{pageBeanName.dto.childernNumbers != 0}" forceId="true"   id="checkBoxLeafEdit" />
    </t:panelGrid>
    <t:panelGroup colspan="2">
        <f:verbatim><center></center></f:verbatim>
        <t:panelGrid columns="2" border="0" align="center">
            <%-- updated by Ashraf Gaber 24/2/09 to handel keyboard shortcuts --%>
            <t:commandButton forceId="true" id="saveButtonTreeEditDiv" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}" onclick="return validateInputText('edit_first_inputTxt','msgEdit',null,null,null);"/>
            <%--h:commandButton styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.edit}" onclick="return validateInputText('edit_first_inputTxt','msgEdit',null,null,null);"/--%>
            <t:commandButton onblur="settingFoucsAtDivEdit();" forceId="true" id="CancelButtonTreeEditDiv" styleClass="cssButtonSmall" value="#{globalResources.back}" onclick="hidTreeDiv('edit',window.blocker,window.divTreeEdit,null); return false;"/>
            <%--h:commandButton styleClass="cssButtonSmall" value="#{globalResources.back}" type="button" onclick="hidTreeDiv('edit',window.blocker,window.divTreeEdit,null);"/--%>
            <%-- modifiy width by sherif.omar--%>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGroup>
