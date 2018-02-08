<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<t:panelGroup forceId="true" id="divEditLookup">
    
    <t:panelGroup rendered="#{detailBeanName.editDivShown}">
        
        <t:panelGrid columns="2" border="0" rowClasses="row01,row02"  width="100%" cellpadding="3" cellspacing="0" >
            <t:outputText value="#{regResources.SubjectName}" styleClass="lable01"/> 
            <t:panelGroup>
                <t:inputText onblur="document.getElementById('edit_inputTxtArea').focus();" onchange="trimBorders(edit_first_inputTxt);"  forceId="true" maxlength="#{pageBeanName.nameMaxLength}" id="edit_first_inputTxt" styleClass="textboxlarge" value="#{detailBeanName.pageDTO.decmaterialHeader}"/>
                <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                <htm:br/> 
                <c2:requiredFieldValidator componentToValidate="edit_first_inputTxt" highlight="false" display="dynamic" errorMessage="#{globalResources.missingField}" active="#{decisionMaterialsMaintainBean.editDivShown}"/>
            </t:panelGroup>
            
            <t:outputText value="#{regResources.SubjectTextArea}" styleClass="lable01"/> 
            <t:panelGroup>
                <t:inputTextarea  value="#{detailBeanName.pageDTO.decmaterialText}"   forceId="true"  id="edit_inputTxtArea"  rows="4" cols="32"/>
                <t:outputText value="*" styleClass="mandatoryAsterisk"/>
                <htm:br/>
                <c2:requiredFieldValidator componentToValidate="edit_inputTxtArea" highlight="false" display="dynamic" errorMessage="#{globalResources.missingField}" active="#{decisionMaterialsMaintainBean.editDivShown}"/>
            </t:panelGroup>
          
        </t:panelGrid>
               
        <t:outputLabel  style="font-size: 4pt;height:3px"/>  
        
        <t:panelGrid columns="2" border="0" align="center">
            <t:commandButton forceId="true" id="SaveEditButton" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{detailBeanName.EditChange}" onclick="return validatemyForm();"/>
            
            <t:panelGroup>
                <t:commandButton forceId="true" id="CancelButtonEdit" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="document.getElementById('edit_first_inputTxt').focus();" />
                <a4j:jsFunction name="backJsFunction" action="#{detailBeanName.hideEditDiv}"  oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'edit_first_inputTxt','myForm:error','edit');settingFoucsAtListPage(); " reRender="divEditLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
            </t:panelGroup>
        </t:panelGrid>
    
    </t:panelGroup>
    
</t:panelGroup>