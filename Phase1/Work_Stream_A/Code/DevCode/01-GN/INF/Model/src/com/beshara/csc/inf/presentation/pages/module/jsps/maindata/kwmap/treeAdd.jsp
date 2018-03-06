<%-- modifiy by Kareem.Sayed--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %> 
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>
<tiles:importAttribute scope="request"/>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="exceptions"/>

<t:panelGroup id="treeAddDiv"  forceId="true">
    
    <t:saveState value="#{pageBeanName.parentLevel}"/>
    <t:saveState value="#{pageBeanName.parentName}"/>
    <t:saveState value="#{pageBeanName.success}"/>
        
    <t:saveState value="#{pageBeanName.showErrorMsg}"/>
    <t:saveState value="#{pageBeanName.errorMsg}"/>
    
    <t:outputText id="successMsgTreeAdd" forceId="true" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg" rendered="#{pageBeanName.success}"/>
    <h:outputText id="error" styleClass="errMsg" value="#{exceptions[pageBeanName.errorMsg]}" rendered="#{pageBeanName.showErrorMsg}"/>
    <htm:br rendered="#{pageBeanName.success || pageBeanName.showErrorMsg}"/>
    <h:outputText id="clientErrorMessage" styleClass="errMsg" />
   <%--h:message id="errorMsg" for="add_first_inputTxt" style="color:red"/--%>
    <t:panelGrid columns="2" border="0" width="100%" columnClasses="col1,col2" rowClasses="row01,row02" cellpadding="3" cellspacing="0">
           <h:outputText value="#{resourcesBundle.ParentMapName}" styleClass="lable01"/> 
           <t:inputText value="#{pageBeanName.parentName}" styleClass="textboxsmall" style="margin-left:15px" disabled="true"/> 
           
           
          <h:outputText value="#{resourcesBundle.mapName}" styleClass="lable01"/> 
            <t:panelGroup>
                <t:inputText maxlength="#{pageBeanName.nameMaxLength}" id="mapName" value="#{pageBeanName.pageDTO.name}" styleClass="textboxlarge" forceId="true"  />
                <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                <c:requiredFieldValidator componentToValidate="mapName" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{kwMapListBean.pageMode==1}"/>
            </t:panelGroup>

          <t:outputLabel value="#{resourcesBundle[leaf]}" id="outputLabelLeaf"  styleClass="lable01"/>
        <t:selectBooleanCheckbox forceId="true"  value="#{pageBeanName.booleanLeafFlag}" disabled="#{pageBeanName.disableStatusInAdd}" id="checkBoxLeaf" tabindex="5"/>
        
      </t:panelGrid>
      
 
     <t:panelGrid columns="3" border="0" align="center" >
        <t:commandButton forceId="true" id="saveButtonTreeAddDiv" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.addNew}" onclick="return validateInputText('mapName','treeform:clientErrorMessage',null,null,'successMsgTreeAdd');" tabindex="6"/>
        <h:panelGroup >
            <t:commandButton type="button" forceId="true" id="SaveNewButton" onclick="clearMsgs();settingFoucsAtDivAdd();validateSaveAndNew('mapName','treeform:clientErrorMessage',null,null,'successMsgTreeAdd');" styleClass="cssButtonSmall" value="#{globalResources.AddNewButton}" tabindex="7"/>
            <a4j:jsFunction name="saveAndNew" action="#{pageBeanName.addAndNew}" reRender="treeAddDiv" oncomplete="settingFoucsAtDivAdd();"/>
        </h:panelGroup>
        
        <t:commandButton forceId="true" id="backButtonTreeAddDiv" styleClass="cssButtonSmall" value="#{globalResources.back}" onblur="setFocusOnlyOnElement('add_first_inputTxt');" action="#{pageBeanName.cancelAddTree}" onclick="hidTreeDiv('add',window.blocker,window.divTreeAdd,'successMsgTreeAdd');" tabindex="8"/>
    
    </t:panelGrid>
</t:panelGroup>

<script language="javascript" type="text/javascript">
    function clearMsgs() {
        if(document.getElementById('treeform:clientErrorMessage') != null) {
            document.getElementById('treeform:clientErrorMessage').innerHTML = "";
        }
        
        if(document.getElementById('treeform:error') != null) {
            document.getElementById('treeform:error').innerHTML = "";
        }
        
        if(document.getElementById('successMsgTreeAdd') != null) {
            document.getElementById('successMsgTreeAdd').innerHTML = "";
        }
    }
</script>
